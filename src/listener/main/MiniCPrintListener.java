package listener.main;

import generated.MiniCParser;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ParseTreeListener;
import org.antlr.v4.runtime.tree.ParseTreeProperty;

import java.util.Iterator;

public class MiniCPrintListener extends generated.MiniCBaseListener {
    ParseTreeProperty<String> newTexts = new ParseTreeProperty<String>();

    boolean isBinaryOperation(MiniCParser.ExprContext ctx) {
        return ctx.getChildCount() == 3 &&
                ctx.getChild(1) != ctx.expr() && ctx.expr().size() == 2;
    }


    @Override
    public void exitExpr(MiniCParser.ExprContext ctx) {
        String s1 = null, s2 = null, op = null;

        // IDENT인지 아닌지를 확인해준다.
        if (ctx.IDENT() != null) {
            // IDENT 하나만 있는 경우
            if (ctx.getChildCount() == 1) {
                newTexts.put(ctx, ctx.IDENT().getText());
            }
            // 배열인 경우
            else if (ctx.getChildCount() == 3) {
                newTexts.put(ctx, ctx.IDENT().getText() + " " + ctx.getChild(1).getText() + " " + newTexts.get(ctx.expr(0)));
            }
            // 괄호가 포함된 경우. 함수와 같은 경우를 생각할 수 있음
            else if (ctx.args() != null) {
                newTexts.put(ctx, ctx.IDENT().getText() + ctx.getChild(1).getText() + newTexts.get(ctx.args()) + ctx.getChild(3).getText());
            }
            // 변수에 값을 대입하는 경우
            else if (ctx.expr() != null && ctx.getChildCount() == 4) {
                newTexts.put(ctx, ctx.IDENT().getText() + ctx.getChild(1).getText() + newTexts.get(ctx.expr(0)) + ctx.getChild(3).getText());
            }
            // 배열의 특정 인덱스에 값을 대입하는 경우
            else if (ctx.getChildCount() == 6) {
                newTexts.put(ctx, ctx.IDENT().getText() + ctx.getChild(1).getText() + newTexts.get(ctx.expr(0)) + ctx.getChild(3).getText() + " " +
                        ctx.getChild(4).getText() + " " + ctx.getChild(5).getText());
            }
        }
        // 리터럴인 경우
        else if (ctx.LITERAL() != null) {
            newTexts.put(ctx, ctx.LITERAL().getText());
        }
        // 단항 연산인 경우
        else if (ctx.expr() != null && ctx.getChildCount() == 2) {
            newTexts.put(ctx, ctx.getChild(0).getText() + newTexts.get(ctx.expr(0)));
        }

        // 이진 연산인 경우
        else if (isBinaryOperation(ctx)) {
            // 예: expr '+' expr
            s1 = newTexts.get(ctx.expr(0));
            s2 = newTexts.get(ctx.expr(1));
            op = ctx.getChild(1).getText();
            newTexts.put(ctx, s1 + " " + op + " " + s2);
        }
        // 괄호안에 식이 포함된 경우.
        else if (ctx.getChild(0) != ctx.expr() && ctx.getChildCount() == 3) {
            newTexts.put(ctx, ctx.getChild(0).getText() + newTexts.get(ctx.expr(0)) + ctx.getChild(2).getText());
        }
    }

    @Override
    public void exitProgram(MiniCParser.ProgramContext ctx) {
        String program = "",  dot = "";
        String programList[] = null;
        int count = ctx.getChildCount();
        // 모든 decl을 하나의 문자열로 합쳐준다.
        for (int i = 0; i < count; i++) {
            program += newTexts.get(ctx.getChild(i));
        }
        // 라인별로 처리할 수 있도록 줄마다 나누어서 배열에 저장한다.
        programList = program.split("\n");
        program = "";
        // if, while 문 뒤에 문장이 하나만 오는 경우를 확인하기 위해서 사용하는 변수이다.
        boolean isSpecialStat = false;
        // ....을 이용해 들여쓰기를 추가하기 위한 코드이다
        // 반복을 하면서 각 라인들을 더해준다
        for (String line: programList) {
            // if, while 뒤에 오는 것이 여는 괄호가 아니면 들여쓰기를 추가한다.
            if (isSpecialStat && !line.contains("{")) {
                dot += "....";
            }
            // 여는 괄호가 올 경우 false로 만들어 원래 흐름대로 진행되도록 만든다.
            else if (isSpecialStat && line.contains("{")) {
                isSpecialStat = false;
            }
            // 닫는 괄호가 나오면 들여쓰기를 줄여준다.
            if (line.contains("}"))
                if (dot.length() <= 4)
                    dot = "";
                else
                    dot = dot.substring(0, dot.length()-4);
            program += dot + line + "\n";
            // 들여쓰기를 했으면 다음 문장부터는 다시 원래대로 돌려야하기 때문에 들여쓰기를 줄여준다.
            if (isSpecialStat) {
                isSpecialStat = false;
                if (dot.length() <= 4)
                    dot = "";
                else
                    dot = dot.substring(0, dot.length()-4);
            }
            // 여는 괄호가 나오면 들여쓰기를 추가해준다.
            if (line.contains("{"))
                dot += "....";
            // if나 while문이 나온것을 체크해둔다.
            if (line.contains("if") || line.contains("while"))
                isSpecialStat = true;

        }
        System.out.println(program);
        newTexts.put(ctx, program);
    }

    @Override
    // var decl과 fun decl을 그대로 추가해준다.
    public void exitDecl(MiniCParser.DeclContext ctx) {
        newTexts.put(ctx, newTexts.get(ctx.getChild(0)));
    }

    @Override
    public void exitVar_decl(MiniCParser.Var_declContext ctx) {
        String type = null, ident = null, literal = null;
        type = ctx.type_spec().getChild(0).getText();
        ident = ctx.IDENT().getText();
        switch (ctx.getChildCount()) {
            case 3:
                // 초기화 없이 변수 선언만 된 경우
                newTexts.put(ctx, type + " " + ident + ctx.getChild(2).getText() + "\n");
                break;
            case 5:
                // 초기화 하면서 변수가 선언된 경우
                newTexts.put(ctx, type + " " + ident + " " +ctx.getChild(2).getText() + " " + ctx.LITERAL().getText() + ctx.getChild(4).getText() + "\n");
                break;
            case 6:
                // 배열을 선언하는 경우
                newTexts.put(ctx, type + " " + ident + ctx.getChild(2).getText() + ctx.LITERAL().getText() + ctx.getChild(4).getText() + ctx.getChild(5).getText() + "\n");
                break;
        }

    }

    @Override
    public void exitType_spec(MiniCParser.Type_specContext ctx) {
        super.exitType_spec(ctx);
    }

    @Override
    public void exitFun_decl(MiniCParser.Fun_declContext ctx) {
        String type = null, ident = null, params = null, compound = null;
        type = ctx.type_spec().getChild(0).getText();
        ident = ctx.IDENT().getText();
        // 맵으로부터 파라미터 읽어온다.
        params = newTexts.get(ctx.params());
        // 맵으로부터 compound stmt 불러온다.
        compound = newTexts.get(ctx.compound_stmt());
        // 합쳐서 맵에 추가해준다.
        newTexts.put(ctx, type + " " + ident + ctx.getChild(2).getText() + params + ctx.getChild(4).getText() + "\n" + compound);

    }

    @Override
    public void exitParams(MiniCParser.ParamsContext ctx) {
        String params = null;
        switch (ctx.getChildCount()) {
            case 0:
                // 파라미터가 없는 경우
                params = "";
                break;
            case 1:
                // 파라미터가 void인 경우
                params = ctx.VOID().getText();
                break;
            default:
                // 파라미터가 한개 이상인 경우
                MiniCParser.ParamContext paramContext = ctx.param().get(0);
                params = newTexts.get(paramContext);
                // 모든 파라미터를 추가해준다.
                for (int i = 1; i < ctx.param().size(); i++) {
                    paramContext = ctx.param().get(i);
                    params += ", " + newTexts.get(paramContext);
                }
                break;
        }
        newTexts.put(ctx, params);

    }

    @Override
    public void exitParam(MiniCParser.ParamContext ctx) {
        String type = null, ident = null;
        type = ctx.type_spec().getChild(0).getText();
        ident = ctx.IDENT().getText();
        switch (ctx.getChildCount()) {
            // 파라미터가 배열이 아닌 변수인 경우
            case 2:
                newTexts.put(ctx, type + " " + ident);
                break;
            // 파리미터가 변수인 경우
            case 4:
                newTexts.put(ctx, type + " " + ident + ctx.getChild(2).getText() + ctx.getChild(3).getText());
                break;
        }
    }

    @Override
    public void exitStmt(MiniCParser.StmtContext ctx) {
        String stmt = "";
        // expr stmt, compund stmt, if stmt, while stmt, return stmt 를 그대로 추가
        newTexts.put(ctx, newTexts.get(ctx.getChild(0)));
    }

    @Override
    public void exitExpr_stmt(MiniCParser.Expr_stmtContext ctx) {
        // expr에 세미콜론을 더해서 추가해준다.
        newTexts.put(ctx, newTexts.get(ctx.getChild(0)) + ctx.getChild(1).getText());
    }

    @Override
    public void exitWhile_stmt(MiniCParser.While_stmtContext ctx) {
        // while문 추가
        newTexts.put(ctx, ctx.WHILE().getText() + " " + ctx.getChild(1).getText() + newTexts.get(ctx.expr()) + ctx.getChild(3).getText() + "\n" + newTexts.get(ctx.stmt()));
    }

    @Override
    public void exitCompound_stmt(MiniCParser.Compound_stmtContext ctx) {
        String compound = "";
        // local decl이 0개 이상 있을 수 있다.
        // 떄문에 1개 이상 존재하는 경우에만 추가를 해준다.
        // 인덱스를 증가시키며 새로운 context를 받아가며 추가해준다.
        if (ctx.local_decl().size() != 0) {
            MiniCParser.Local_declContext localDeclContext;
            for (int i = 0; i < ctx.local_decl().size(); i++) {
                localDeclContext = ctx.local_decl().get(i);
                compound += newTexts.get(localDeclContext) + "\n";
            }
        }
        // stmt 역시 0개 이상 있을 수 있다.
        // 동일한 방법으로 개수를 확인해주고, 존재한다면 모두 추가해준다.
        if (ctx.stmt().size() != 0) {
            MiniCParser.StmtContext stmtContext;
            compound +=  newTexts.get(ctx.stmt().get(0));
            for (int i = 1; i < ctx.stmt().size(); i++) {
                stmtContext = ctx.stmt().get(i);
                compound += "\n" + newTexts.get(stmtContext);
            }
        }
        // 만들어진 문자열을 맵에 추가해준다.
        newTexts.put(ctx, ctx.getChild(0).getText() + "\n" + compound + "\n" + ctx.getChild(ctx.getChildCount()-1));
    }

    // 전역변수인 var decl와 동일한 구현이다.
    @Override
    public void exitLocal_decl(MiniCParser.Local_declContext ctx) {
        String type = null, ident = null, literal = null;
        type = ctx.type_spec().getChild(0).getText();
        ident = ctx.IDENT().getText();
        switch (ctx.getChildCount()) {
            case 3:
                newTexts.put(ctx, type + " " + ident + ctx.getChild(2).getText());
                break;
            case 5:
                newTexts.put(ctx, type + " " + ident + " " +ctx.getChild(2).getText() + " " + ctx.LITERAL().getText() + ctx.getChild(4).getText());
                break;
            case 6:
                newTexts.put(ctx, type + " " + ident + ctx.getChild(2).getText() + ctx.LITERAL().getText() + ctx.getChild(4).getText() + ctx.getChild(5).getText());
                break;
        }
    }

    @Override
    public void exitIf_stmt(MiniCParser.If_stmtContext ctx) {
        // else 문이 없는 경우.
        if (ctx.getChildCount() == 5) {
            newTexts.put(ctx, ctx.IF().getText() + " " + ctx.getChild(1).getText() + newTexts.get(ctx.expr()) + ctx.getChild(3) + "\n" + newTexts.get(ctx.stmt(0)));
        }
        // else 문이 있는 경우
        else {
            newTexts.put(ctx, ctx.IF().getText() + " " + ctx.getChild(1).getText() + newTexts.get(ctx.expr()) + ctx.getChild(3) + "\n" + newTexts.get(ctx.stmt(0))+
                    " " + ctx.ELSE().getText() + "\n" + newTexts.get(ctx.stmt(1)));
        }
    }

    @Override
    public void exitReturn_stmt(MiniCParser.Return_stmtContext ctx) {
        // return 값이 존재하지 않는 경우
        if (ctx.expr() == null) {
            newTexts.put(ctx, ctx.RETURN().getText() + ctx.getChild(1).getText());
        }
        // return 값이 존재하는 경우
        else {
            newTexts.put(ctx, ctx.RETURN().getText() + " " + newTexts.get(ctx.expr()) + ctx.getChild(2).getText());
        }
    }

    @Override
    public void exitArgs(MiniCParser.ArgsContext ctx) {
        String args = "";
        // arg도 0개 이상 존재할 수 있다.
        // 1개 이상 존재하는 경우 모든 arg를 추가해서 맵에 추가해준다.
        if (ctx.getChildCount() != 0) {
            MiniCParser.ExprContext exprContext = ctx.expr(0);
            args = newTexts.get(exprContext);
            for (int i = 1; i < ctx.expr().size(); i++) {
                exprContext = ctx.expr().get(i);
                args += ", " + newTexts.get(exprContext);
            }
        }
        newTexts.put(ctx, args);
    }

    @Override
    public void exitEveryRule(ParserRuleContext ctx) {
        super.exitEveryRule(ctx);
    }
}
