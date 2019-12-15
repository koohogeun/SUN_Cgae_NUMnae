package listener.main;

import java.util.Hashtable;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.ParseTreeListener;
import org.antlr.v4.runtime.tree.ParseTreeProperty;
import org.antlr.v4.runtime.tree.TerminalNode;

import generated.MiniCBaseListener;
import generated.MiniCParser;
import generated.MiniCParser.ExprContext;
import generated.MiniCParser.Fun_declContext;
import generated.MiniCParser.Local_declContext;
import generated.MiniCParser.ParamsContext;
import generated.MiniCParser.ProgramContext;
import generated.MiniCParser.StmtContext;
import generated.MiniCParser.Type_specContext;
import generated.MiniCParser.Var_declContext;

import static listener.main.BytecodeGenListenerHelper.*;
import static listener.main.SymbolTable.*;

public class BytecodeGenListener extends MiniCBaseListener implements ParseTreeListener {
    ParseTreeProperty<String> newTexts = new ParseTreeProperty<String>();
    SymbolTable symbolTable = new SymbolTable();

    int tab = 0;
    int label = 0;

    // program	: decl+

    @Override
    public void enterFun_decl(MiniCParser.Fun_declContext ctx) {
        symbolTable.initFunDecl();

        String fname = getFunName(ctx);
        ParamsContext params;

        if (fname.equals("main")) {
            symbolTable.putLocalVar("args", Type.INTARRAY);
        } else {
            symbolTable.putFunSpecStr(ctx);
            params = (MiniCParser.ParamsContext) ctx.getChild(3);
            symbolTable.putParams(params);
        }
    }


    // var_decl	: type_spec IDENT ';' | type_spec IDENT '=' LITERAL ';'|type_spec IDENT '[' LITERAL ']' ';'
    @Override
    public void enterVar_decl(MiniCParser.Var_declContext ctx) {
        String varName = ctx.IDENT().getText();

        if (isArrayDecl(ctx)) {
            symbolTable.putGlobalVar(varName, Type.INTARRAY);
        } else if (isDeclWithInit(ctx)) {
            symbolTable.putGlobalVarWithInitVal(varName, Type.INT, initVal(ctx));
        } else { // simple decl
            symbolTable.putGlobalVar(varName, Type.INT);
        }
    }


    @Override
    public void enterLocal_decl(MiniCParser.Local_declContext ctx) {
        if (isArrayDecl(ctx)) {
            symbolTable.putLocalVar(getLocalVarName(ctx), Type.INTARRAY);
        } else if (isDeclWithInit(ctx)) {
            symbolTable.putLocalVarWithInitVal(getLocalVarName(ctx), Type.INT, initVal(ctx));
        } else { // simple decl
            symbolTable.putLocalVar(getLocalVarName(ctx), Type.INT);
        }
    }


    @Override
    public void exitProgram(MiniCParser.ProgramContext ctx) {
        String classProlog = getFunProlog();

        String fun_decl = "", var_decl = "";

        for (int i = 0; i < ctx.getChildCount(); i++) {
            if (isFunDecl(ctx, i))
                fun_decl += newTexts.get(ctx.decl(i));
            else
                var_decl += newTexts.get(ctx.decl(i));
        }

        newTexts.put(ctx, classProlog + var_decl + fun_decl);

        System.out.println(newTexts.get(ctx));
    }


    // decl	: var_decl | fun_decl
    @Override
    public void exitDecl(MiniCParser.DeclContext ctx) {
        String decl = "";
        if (ctx.getChildCount() == 1) {
            if (ctx.var_decl() != null)                //var_decl
                decl += newTexts.get(ctx.var_decl());
            else                            //fun_decl
                decl += newTexts.get(ctx.fun_decl());
        }
        newTexts.put(ctx, decl);
    }

    /*
     * stmt, compound_stmt, if stmt, while_stmt, return_stmt 중 어떤 것인지 확인하여 추가해준다.
     * 각각의 stmt에 대해서 이미 netTexts에 추가되어 있기 때문에 get을 이용해서 가져와준다.
     */
    // stmt	: expr_stmt | compound_stmt | if_stmt | while_stmt | return_stmt
    @Override
    public void exitStmt(MiniCParser.StmtContext ctx) {
        String stmt = "";
        if (ctx.getChildCount() > 0) {
            if (ctx.expr_stmt() != null)                // expr_stmt
                stmt += newTexts.get(ctx.expr_stmt());
            else if (ctx.compound_stmt() != null)    // compound_stmt
                stmt += newTexts.get(ctx.compound_stmt());
            else if (ctx.if_stmt() != null)          // if_stmt
                stmt += newTexts.get(ctx.if_stmt());
            else if (ctx.while_stmt() != null)       // while_stmt
                stmt += newTexts.get(ctx.while_stmt());
            else if (ctx.return_stmt() != null)     // return_stmt
                stmt += newTexts.get(ctx.return_stmt());
            else if (ctx.for_stmt() != null)
            	stmt += newTexts.get(ctx.for_stmt());
        }
        newTexts.put(ctx, stmt);
    }

    // expr_stmt	: expr ';'
    @Override
    public void exitExpr_stmt(MiniCParser.Expr_stmtContext ctx) {
        String stmt = "";
        if (ctx.getChildCount() == 2) {
            stmt += newTexts.get(ctx.expr());    // expr
        }
        newTexts.put(ctx, stmt);
    }

    /*
     * while_stmt를 추가해주는 함수이다.
     * 전체적인 코드는 if_stmt와 대부분 동일하다.
     * 조건을 검사하고, 조건을 만족할 경우 코드를 실행하는 부분은 같다.
     * 조건을 검사하는 부분 밖에 레이블을 추가하여서 코드를 실행하고 goto 로 분기해 반복이 되도록 하였다.
     * 조건을 만족하지 않을 경우 가장 바깥의 레이블로 이동하도록 하였다.
     */
    // while_stmt	: WHILE '(' expr ')' stmt
    @Override
    public void exitWhile_stmt(MiniCParser.While_stmtContext ctx) {
        String stmt = "";
        String outer = symbolTable.newLabel();
        String condExpr = newTexts.get(ctx.expr());
        String thenStmt = newTexts.get(ctx.stmt());

        String lend = symbolTable.newLabel();
        stmt += outer + ": " + "\n"
                + condExpr + "\n"
                + "ifeq " + lend + "\n"
                + thenStmt + "\n"
                + "goto " + outer + "\n"
                + lend + ":" + "\n";
        newTexts.put(ctx, stmt);
    }

    /*
     * 함수가 선언될 때 필요한 function header 와 함수가 가지는 compound stmt를 추가해주는 함수이다.
     * 함수의 끝 부분에 .end method 를 붙여 함수의 끝임을 알려준다.
     */
    @Override
    public void exitFun_decl(MiniCParser.Fun_declContext ctx) {
        String stmt = funcHeader(ctx, getFunName(ctx));
        if (ctx.compound_stmt() != null) {    // compound_stmt
            stmt += newTexts.get(ctx.compound_stmt());
        }
        if (isVoidF(ctx))
            stmt += "return\n";
        else
            stmt += "ireturn\n";
        stmt += ".end method\n";
        newTexts.put(ctx, stmt);
    }


    private String funcHeader(MiniCParser.Fun_declContext ctx, String fname) {
        return ".method public static " + symbolTable.getFunSpecStr(fname) + "\n"
                + "\t" + ".limit stack " + getStackSize(ctx) + "\n"
                + "\t" + ".limit locals " + getLocalVarSize(ctx) + "\n";

    }


    @Override
    public void exitVar_decl(MiniCParser.Var_declContext ctx) {
        String varName = ctx.IDENT().getText();
        String varDecl = "";
        if (isDeclWithInit(ctx)) {
            varDecl += "putfield " + varName + "\n";
            // v. initialization => Later! skip now..:
        }
        newTexts.put(ctx, varDecl);
    }


    @Override
    public void exitLocal_decl(MiniCParser.Local_declContext ctx) {
        String varDecl = "";

        if (isDeclWithInit(ctx)) {
            String vId = symbolTable.getVarId(ctx);
            varDecl += "ldc " + ctx.LITERAL().getText() + "\n"
                    + "istore_" + vId + "\n";
        } else if (isDeclArr(ctx)) {
            String vId = symbolTable.getVarId(ctx);
            String index = newTexts.get(ctx.getChild(2));
            String numIndex = Integer.toString(ctx.getChild(2).getChildCount());
            int numIndexInt = ctx.getChild(2).getChildCount();
            String dimType = "[";
            dimType = new String(new char[numIndexInt]).replace("\0", dimType);
            varDecl += index
                    + "multianewarray " + dimType + "I " + numIndex + "\n"
                    + "astore_" + vId + "\n";
        }

        newTexts.put(ctx, varDecl);
    }

    /*
     * co   mpound statement를 추가해주는 함수이다.
     * local decl 과 stmt 를 여러개 가질 수 있기 때문에 foreach 문을 사용하여서
     * 가지고 있는 local decl과 stmt들을 모두 추가해준다.
     */
    // compound_stmt	: '{' local_decl* stmt* '}'
    @Override
    public void exitCompound_stmt(MiniCParser.Compound_stmtContext ctx) {
        String stmt = "";
        for (Local_declContext local_declContext : ctx.local_decl()) {
            stmt += newTexts.get(local_declContext);
        }
        for (StmtContext stmtContext : ctx.stmt()) {
            stmt += newTexts.get(stmtContext);
        }
        newTexts.put(ctx, stmt);

    }

    // if_stmt	: IF '(' expr ')' stmt | IF '(' expr ')' stmt ELSE stmt;
    @Override
    public void exitIf_stmt(MiniCParser.If_stmtContext ctx) {
        String stmt = "";
        String condExpr = newTexts.get(ctx.expr());
        String thenStmt = newTexts.get(ctx.stmt(0));

        String lend = symbolTable.newLabel();
        String lelse = symbolTable.newLabel();

        if (noElse(ctx)) {
            stmt += condExpr + "\n"
                    + "ifeq " + lend + "\n"
                    + thenStmt + "\n"
                    + lend + ":" + "\n";
        } else {
            String elseStmt = newTexts.get(ctx.stmt(1));
            stmt += condExpr + "\n"
                    + "ifeq " + lelse + "\n"
                    + thenStmt + "\n"
                    + "goto " + lend + "\n"
                    + lelse + ": \n" + elseStmt + "\n"
                    + lend + ":" + "\n";
        }

        newTexts.put(ctx, stmt);
    }

    /*
     * return stmt를 처리하는 함수이다. expr이 있는 경우 값을 load해서 return 해주고,
     * 그렇지 않은 경우는 그냥 return을 해준다.
     */
    // return_stmt	: RETURN ';' | RETURN expr ';'
    @Override
    public void exitReturn_stmt(MiniCParser.Return_stmtContext ctx) {
        // <(4) Fill here>
        String stmt = "";
        if (ctx.getChildCount() == 3)
            stmt += newTexts.get(ctx.getChild(1));
        newTexts.put(ctx, stmt);
    }


    @Override
    public void exitExpr(MiniCParser.ExprContext ctx) {
        //System.out.println(ctx.getText() +"  "+ ctx.getChildCount());
        String expr = "";
        if (ctx.getChildCount() <= 0) {
            newTexts.put(ctx, "");
            return;
        }

        if (ctx.getChildCount() == 1) { // IDENT | LITERAL
            if (ctx.IDENT() != null) {
                String idName = ctx.IDENT().getText();
                if (symbolTable.getVarType(idName) == Type.INT) {
                    expr += "iload_" + symbolTable.getVarId(idName) + " \n";
                }
                //else	// Type int array => Later! skip now..
                //	expr += "           lda " + symbolTable.get(ctx.IDENT().getText()).value + " \n";
            } else if (ctx.LITERAL() != null) {
                String literalStr = ctx.LITERAL().getText();
                expr += "ldc " + literalStr + " \n";
            }
        } else if (ctx.getChildCount() == 2) { // UnaryOperation
            expr = handleUnaryExpr(ctx, newTexts.get(ctx) + expr);

        } else if (ctx.getChildCount() == 3) {
            if (ctx.getChild(0).getText().equals("(")) {        // '(' expr ')'
                expr = newTexts.get(ctx.expr(0));

            } else if (ctx.getChild(1).getText().equals("=")) {    // IDENT '=' expr
            	if(ctx.expr(0).arr()!=null) {	// IDENT '=' array
            		int child_count = ctx.expr(0).arr().getChildCount();
            		expr += "aload " + symbolTable.getVarId(ctx.expr(0).IDENT().getText()) + "\n";
            		for(int i = 0; i< child_count-1 ; i++) {
            			expr += newTexts.get(ctx.expr(0).arr().getChild(i)) + "aaload \n";
            		}
            		expr += newTexts.get(ctx.expr(0).arr().getChild(child_count-1)) + "iaload \n";
            		expr += "istore " + symbolTable.getVarId(ctx.IDENT().getText()) + "\n";
            	}else {
			expr = newTexts.get(ctx.expr(0));
            		if(!ctx.getChild(2).getChild(1).getText().equals(".*"))
            			expr += "istore_" + symbolTable.getVarId(ctx.IDENT().getText()) + " \n";
            	}
                
            } else {                                            // binary operation
                expr = handleBinExpr(ctx, expr);

            }
        }
        // IDENT '(' args ')' |  IDENT '[' expr ']' => IDENT arr
        else if (ctx.getChildCount() == 4) {
            if (ctx.args() != null) {        // function calls
                expr = handleFunCall(ctx, expr);
            } else if (ctx.getChild(1) instanceof MiniCParser.ArrContext) {    //IDENT arr_decl '=' expr
                String array_varID = symbolTable.getVarId(ctx.IDENT().getText());
                expr += "aload " + array_varID + "\n";
                int arr_child = ctx.getChild(1).getChildCount();        //count '[]'
                for (int i = 0; i < arr_child; i++) {
                    String Test = newTexts.get(ctx.arr().getChild(i).getChild(1));
                    String current_index = ctx.arr().getChild(i).getChild(1).getText();    //arr_decl()은 [ Literal ]이기 때문에 getText()써도 됨.
                    expr += "iconst_" + current_index + "\naaload\n";
                }
                expr += "iconst_" + ctx.arr().getChild(arr_child - 1).getChild(1).getText() + "\n" + newTexts.get(ctx.expr(0))
                        + "iastore\n";
            } else if (ctx.getChild(1) instanceof MiniCParser.Arr_declContext) {    //IDENT arr_decl '=' expr
                String array_varID = symbolTable.getVarId(ctx.IDENT().getText());
                expr += "aload " + array_varID + "\n";
                int arr_decl_child = ctx.arr_decl().getChildCount();        //count '[]'
                for (int i = 0; i < arr_decl_child - 1; i++) {
                    String current_index = ctx.arr_decl().getChild(i).getChild(1).getText();    //arr_decl()은 [ Literal ]이기 때문에 getText()써도 됨.
                    expr += "iconst_" + current_index + "\naaload\n";
                }
                expr += "iconst_" + ctx.arr_decl().getChild(arr_decl_child - 1).getChild(1).getText() + "\n" + newTexts.get(ctx.expr(0))
                        + "iastore\n";
            }
            else { // expr
                // Arrays: TODO
            }
        }
        // IDENT '[' expr ']' '=' expr
        else { // Arrays: TODO			*/
        }
        newTexts.put(ctx, expr);
    }


    private String handleUnaryExpr(MiniCParser.ExprContext ctx, String expr) {
        String l1 = symbolTable.newLabel();
        String l2 = symbolTable.newLabel();
        String lend = symbolTable.newLabel();

        if (expr.equals("null"))
            expr = "";
        expr += newTexts.get(ctx.expr(0));

        switch (ctx.getChild(0).getText()) {
            case "-":
                expr += "ineg \n";
                break;
            case "--":
                expr += "ldc 1" + "\n"
                        + "isub" + "\n"
                        + "istore_" + symbolTable.getVarId(ctx.getChild(1).getText()) + "\n";
                if (ctx.parent instanceof MiniCParser.ArgsContext)
                    expr += "iload_" + symbolTable.getVarId(ctx.getChild(1).getText()) + "\n";
                break;
            case "++":
                expr += "ldc 1" + "\n"
                        + "iadd" + "\n"
                        + "istore_" + symbolTable.getVarId(ctx.getChild(1).getText()) + "\n";
                if (ctx.parent instanceof MiniCParser.ArgsContext)
                    expr += "iload_" + symbolTable.getVarId(ctx.getChild(1).getText()) + "\n";
                break;
            case "!":
                expr += "ifeq " + l2 + "\n"
                        + l1 + ": " + "ldc 0" + "\n"
                        + "goto " + lend + "\n"
                        + l2 + ": " + "ldc 1" + "\n"
                        + lend + ": " + "\n";
                break;
        }
        return expr;
    }


    private String handleBinExpr(MiniCParser.ExprContext ctx, String expr) {
        String l2 = symbolTable.newLabel();
        String lend = symbolTable.newLabel();
	
	if(ctx.getChild(1).getText().equals(".*")) {//배열 연산
        	String parent_index = symbolTable.getVarId(ctx.parent.getChild(0).getText());
        	String child1_index = symbolTable.getVarId(ctx.getChild(0).getChild(0).getText());
        	String child2_index = symbolTable.getVarId(ctx.getChild(2).getChild(0).getText());
        	int vector = ctx.getChild(0).getChild(1).getChildCount();
        	String i1 = symbolTable.newTempVar();
        	String label1 = symbolTable.newLabel();
        	String label1_out = symbolTable.newLabel();
        	if(vector == 1) {//1차원 연산
        		expr += "ldc 0\n"
            			+ "istore " + i1 + "\n"
            			+ label1 + ": \n" 
        				+ "iload " + i1 + "\n"
        				+ "aload_" + parent_index + "\n"
            			+ "arraylength\n"
        				+ "if_icmpge " + label1_out + "\n"
        				+ "aload_" + parent_index + "\n"
        				+ "iload " + i1 + "\n"
        				+ "aload_" + child1_index + "\n"
        				+ "iload " + i1 + "\n"
        				+ "iaload\n"
        				+ "aload_" + child2_index + "\n"
        				+ "iload " + i1 + "\n"
        				+ "iaload\n"
        				+ "imul\n"
        				+ "iastore\n"
        				+ "iinc " + i1 + " 1\n"
        				+ "goto " + label1 + "\n"
        				+ label1_out + ": \n";
        	}else if(vector == 2) {//2차원 연산
        		String i2 = symbolTable.newTempVar();
        		String label2 = symbolTable.newLabel();
            	String label2_out = symbolTable.newLabel();
            	String i3 = symbolTable.newTempVar();
        		String label3 = symbolTable.newLabel();
            	String label3_out = symbolTable.newLabel();
            	String sum = symbolTable.newTempVar();
            	expr += "ldc 0\n"
            			+ "istore " + sum + "\n"
            			+ "ldc 0\n"
            			+ "istore " + i1 + "\n"
            			+ label1 + ": \n" 
        				+ "iload " + i1 + "\n"
        				+ "aload_" + child1_index + "\n"
            			+ "arraylength\n"
        				+ "if_icmpge " + label1_out + "\n"
        				
        				+ "ldc 0\n"
            			+ "istore " + i2 + "\n"
            			+ label2 + ": \n" 
        				+ "iload " + i2 + "\n"
        				+ "aload_" + child2_index + "\n"
        				+ "iconst_0\n"
        				+ "aaload\n"
            			+ "arraylength\n"
        				+ "if_icmpge " + label2_out + "\n"
        				
        				+ "ldc 0\n"
            			+ "istore " + i3 + "\n"
            			+ label3 + ": \n" 
        				+ "iload " + i3 + "\n"
        				+ "aload_" + child2_index + "\n"
            			+ "arraylength\n"
        				+ "if_icmpge " + label3_out + "\n"
        				
        				+ "iload " + sum + "\n"
        				
        				+ "aload_" + child1_index + "\n"
        				+ "iload " + i1 + "\n"
        				+ "aaload\n"
        				+ "iload " + i3 + "\n"
        				+ "iaload\n"
        				
						+ "aload_" + child2_index + "\n" 
						+ "iload " + i3 + "\n" 
						+ "aaload\n"
						+ "iload " + i2 + "\n" 
						+ "iaload\n"
        				
						+ "imul\n"
						+ "iadd\n"
						+ "istore " + sum + "\n"
        				
        				+ "iinc " + i3 + " 1\n"
						+ "goto " + label3 + "\n"
						+ label3_out + ": \n"
        				
						+ "aload_" + parent_index + "\n" 
						+ "iload " + i1 + "\n" 
						+ "aaload\n"
						+ "iload " + i3 + "\n" 
						+ "iload " + sum + "\n" 
						+ "iastore\n"
						
            			+ "ldc 0\n"
            			+ "istore " + sum + "\n"
            			
        				+ "iinc " + i2 + " 1\n"
						+ "goto " + label2 + "\n"
						+ label2_out + ": \n"
        				
        				+ "iinc " + i1 + " 1\n"
						+ "goto " + label1 + "\n"
						+ label1_out + ": \n";
            	
        	}else if(vector == 3) {//3차원 연산
        		
        	}
        	return expr;
        }
	    
        expr += newTexts.get(ctx.expr(0));
        expr += newTexts.get(ctx.expr(1));

        switch (ctx.getChild(1).getText()) {
            case "*":
                expr += "imul \n";
                break;
            case "/":
                expr += "idiv \n";
                break;
            case "%":
                expr += "irem \n";
                break;
            case "+":        // expr(0) expr(1) iadd
                expr += "iadd \n";
                break;
            case "-":
                expr += "isub \n";
                break;

            case "==":
                expr += "isub " + "\n"
                        + "ifeq " + l2 + "\n"
                        + "ldc 0" + "\n"
                        + "goto " + lend + "\n"
                        + l2 + ": \n" + "ldc 1" + "\n"
                        + lend + ": " + "\n";
                break;
            case "!=":
                expr += "isub " + "\n"
                        + "ifne " + l2 + "\n"
                        + "ldc 0" + "\n"
                        + "goto " + lend + "\n"
                        + l2 + ": \n" + "ldc 1" + "\n"
                        + lend + ": " + "\n";
                break;
            case "<=":
                expr += "isub " + "\n"            // ifle 명령어를 이용해 작거나 같은 경우에 분기하도록 한다.
                        + "ifle " + l2 + "\n"
                        + "ldc 0" + "\n"
                        + "goto " + lend + "\n"
                        + l2 + ": \n" + "ldc 1" + "\n"
                        + lend + ": " + "\n";
                break;
            case "<":
                expr += "isub " + "\n"
                        + "iflt " + l2 + "\n"   // iflt 명령어를 이용해 작은 경우에 분기하도록 한다.
                        + "ldc 0" + "\n"
                        + "goto " + lend + "\n"
                        + l2 + ": \n" + "ldc 1" + "\n"
                        + lend + ": " + "\n";
                break;

            case ">=":
                expr += "isub " + "\n"
                        + "ifge " + l2 + "\n"   // ifge 명령어를 이용해 크거나 같은 경우에 분기하도록 한다.
                        + "ldc 0" + "\n"
                        + "goto " + lend + "\n"
                        + l2 + ": \n" + "ldc 1" + "\n"
                        + lend + ": " + "\n";

                break;

            case ">":
                expr += "isub " + "\n"
                        + "ifgt " + l2 + "\n"   // ifgt 명령어를 이용해 큰 경우에 분기하도록 한다.
                        + "ldc 0" + "\n"
                        + "goto " + lend + "\n"
                        + l2 + ": \n" + "ldc 1" + "\n"
                        + lend + ": " + "\n";
                break;

            case "and":
                expr += "ifne " + lend + "\n"
                        + "pop" + "\n" + "ldc 0" + "\n"
                        + lend + ": " + "\n";
                break;
            /*
             * and 와 동일하게 피 연산자중 첫번 째 연산자는 if stmt를 통해서 0인지 비교가 된다.
             * 때문에 and와 or 연산에서는 stack에 top에 있는 피 연산자만 확인을 해주면 된다.
             * or logical operation 의 경우에는 둘 중 하나라도 0이 아니면 참이다.
             * 때문에 0이라면 바로 다음 분기문으로 이동시켜주고 0이 아니라면 1을 push 해서
             * 참인 경우의 stmt로 진입하도록 만들어준다.
             */
            case "or":
                expr += "ifeq " + lend + "\n"
                        + "pop" + "\n" + "ldc 1" + "\n"
                        + lend + ": " + "\n";
                break;


        }

        return expr;
    }

    private String handleFunCall(MiniCParser.ExprContext ctx, String expr) {
        String fname = getFunName(ctx);

        if (fname.equals("_print")) {        // System.out.println
            expr = "getstatic java/lang/System/out Ljava/io/PrintStream; " + "\n"
                    + newTexts.get(ctx.args())
                    + "invokevirtual " + symbolTable.getFunSpecStr("_print") + "\n";
        } else {
            expr = newTexts.get(ctx.args())
                    + "invokestatic " + getCurrentClassName() + "/" + symbolTable.getFunSpecStr(fname) + "\n";
        }

        return expr;

    }

    // args	: expr (',' expr)* | ;
    @Override
    public void exitArgs(MiniCParser.ArgsContext ctx) {

        //String argsStr = "\n";
        String argsStr = "";

        for (int i = 0; i < ctx.expr().size(); i++) {
            argsStr += newTexts.get(ctx.expr(i));
        }
        newTexts.put(ctx, argsStr);
    }

    @Override
    public void enterArr(MiniCParser.ArrContext ctx) {
        super.enterArr(ctx);
    }

    @Override
    public void exitArr(MiniCParser.ArrContext ctx) {
        super.exitArr(ctx);
        int arr_stmt_count = ctx.getChildCount();
        for (int i = 0; i < arr_stmt_count; i++) {
            newTexts.put(ctx.getChild(i), newTexts.get(ctx.getChild(i).getChild(1)));
        }
    }

    @Override
    public void enterArr_decl(MiniCParser.Arr_declContext ctx) {
        super.enterArr_decl(ctx);
    }

    @Override
    public void exitArr_decl(MiniCParser.Arr_declContext ctx) {
        super.exitArr_decl(ctx);
        String stmt = "";
        for (int i = 0; i < ctx.getChildCount(); i++) {
            stmt += newTexts.get(ctx.getChild(i)) + "\n";
        }
        newTexts.put(ctx, stmt);
    }

    @Override
    public void exitArr_decl_stmt(MiniCParser.Arr_decl_stmtContext ctx) {
        super.exitArr_decl_stmt(ctx);
        String stmt = "";
        stmt = "bipush " + ctx.LITERAL();
        newTexts.put(ctx, stmt);
    }

    @Override
    public void enterArr_parm(MiniCParser.Arr_parmContext ctx) {
        super.enterArr_parm(ctx);
    }

    @Override
    public void exitArr_parm(MiniCParser.Arr_parmContext ctx) {
        super.exitArr_parm(ctx);
    }
    @Override
    public void exitFor_stmt(MiniCParser.For_stmtContext ctx) {
    	// for '(' local_decl expr ';' expr ')' compound_stmt		=> childCount = 8
    	String outer = symbolTable.newLabel();
    	String repeat = symbolTable.newLabel();
    	String expr = "";
    	expr += newTexts.get(ctx.getChild(2))		//init variable
    			+ repeat + ": \n"					//repeat start
    			+ newTexts.get(ctx.getChild(3))		//is condition true?
    			+ "ifeq " + outer +"\n"
    			+ newTexts.get(ctx.compound_stmt())
    			+ newTexts.get(ctx.getChild(5))		//Progress condition
    			+ "goto " + repeat + "\n"
    			+ outer + ": \n";
    			
    	newTexts.put(ctx, expr);
    }
}
