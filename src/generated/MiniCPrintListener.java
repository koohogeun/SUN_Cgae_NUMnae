package generated;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ParseTreeProperty;
import java.util.StringTokenizer;
import generated.MiniCParser.*;

public class MiniCPrintListener extends MiniCBaseListener {
	ParseTreeProperty<String> newTexts = new ParseTreeProperty<String>();

	boolean isBinaryOperation(MiniCParser.ExprContext ctx) {
		// expr '+' expr
		return ctx.getChildCount() == 3 && ctx.getChild(1) != ctx.expr();
		// && ctx.getChild(0) == ctx.expr() && ctx.getChild(2) == ctx.expr() ;
	}

	boolean isFrontOperator(MiniCParser.ExprContext ctx) {
		// '++'expr
		return ctx.getChildCount() == 2 && ctx.getChild(0) != ctx.expr();
	}

	boolean iscloseExpr(MiniCParser.ExprContext ctx) {
		return ctx.getChild(1) == ctx.expr() && ctx.getChildCount() == 3;
	}

	boolean isDeclaration(MiniCParser.ExprContext ctx) {
		// IDENT ( expr ) , IDENT [ args ]
		return ctx.getChildCount() == 4 && (ctx.getChild(0) == ctx.IDENT());
	}

	boolean isAssignment(MiniCParser.ExprContext ctx) {
		return ctx.getChildCount() == 6 && (ctx.getChild(0) == ctx.IDENT()) && ctx.getChild(2) == ctx.expr()
				&& ctx.getChild(5) == ctx.expr();
	}

	boolean isIDENT(MiniCParser.ExprContext ctx) {
		return ctx.getChildCount() == 1 && ctx.getChild(0) == ctx.IDENT();
	}

	boolean is_IF_function(MiniCParser.If_stmtContext ctx) {
		// if '(' expr ')' stmt
		return ctx.getChildCount() == 5;
	}

	boolean is_IF_ELSE_function(MiniCParser.If_stmtContext ctx) {
		// if '(' expr ')' stmt else stmt
		return ctx.getChildCount() == 7;
	}

	boolean is_var_decl_1(MiniCParser.Var_declContext ctx) {
		// type_spec INDENT ;
		return ctx.getChildCount() == 3 && ctx.getChild(2).getText().equals(";");
	}

	boolean is_var_decl_2(MiniCParser.Var_declContext ctx) {
		// type_spec IDENT '=' LITERAL ';'
		return ctx.getChildCount() == 5 && ctx.getChild(2).getText().equals("=")
				&& ctx.getChild(4).getText().equals(";");
	}

	boolean is_var_decl_3(MiniCParser.Var_declContext ctx) {
		// type_spec IDENT '[' LITERAL ']' ';'
		return ctx.getChildCount() == 6 && ctx.getChild(2).getText().equals("[")
				&& ctx.getChild(4).getText().equals("]") && ctx.getChild(5).getText().equals(";");
	}

	boolean isexpr_stmt(MiniCParser.Expr_stmtContext ctx) {
		return ctx.getChildCount() == 2 && ctx.getChild(1).getText().equals(";");
	}

	boolean islocal_decl1(MiniCParser.Local_declContext ctx) {
		// type_spec IDENT ';'
		return ctx.getChildCount() == 3 && ctx.getChild(0) == ctx.type_spec() && ctx.getChild(2).getText().equals(";");
	}

	boolean islocal_decl2(MiniCParser.Local_declContext ctx) {
		// type_spec IDENT '=' LITERAL ';'
		return ctx.getChildCount() == 5 && ctx.getChild(0) == ctx.type_spec() && ctx.getChild(1) == ctx.IDENT()
				&& ctx.getChild(2).getText().equals("=") && ctx.getChild(3) == ctx.LITERAL()
				&& ctx.getChild(4).getText().equals(";");

	}

	boolean islocal_decl3(MiniCParser.Local_declContext ctx) {
		// type_spec IDENT '[' LITERAL ']' ';'
		return ctx.getChildCount() == 6 && ctx.getChild(0) == ctx.type_spec() && ctx.getChild(1) == ctx.IDENT()
				&& ctx.getChild(2).getText().equals("[") && ctx.getChild(3) == ctx.LITERAL()
				&& ctx.getChild(4).getText().equals("]") && ctx.getChild(5).getText().equals(";");

	}

	@Override
	public void exitExpr(MiniCParser.ExprContext ctx) { // postorder
		
		String s1 = null, s2 = null, op = null;

		if (isBinaryOperation(ctx)) {
			String temp = null;
			if (ctx.getChild(0) instanceof MiniCParser.ExprContext
					&& ctx.getChild(2) instanceof MiniCParser.ExprContext) {
				// expr 'op' expr
				s1 = newTexts.get(ctx.expr(0));
				s2 = newTexts.get(ctx.expr(1));
				op = ctx.getChild(1).getText();
				temp = s1 + " " + op + " " + s2;
				newTexts.put(ctx, s1 + " " + op + " " + s2);
			} else {
				// terminal context + ' = ' + expr
				temp = ctx.getChild(0).getText() + " " + ctx.getChild(1).getText() + " " + newTexts.get(ctx.expr(0));
				newTexts.put(ctx, temp);
			}
		} else if (isFrontOperator(ctx)) {
			// 예: ++expr
			s1 = newTexts.get(ctx.expr(0));
			op = ctx.getChild(0).getText();
			newTexts.put(ctx, op + s1);
		} else if (iscloseExpr(ctx)) {
			// ( expr )
			s1 = newTexts.get(ctx.expr(0));
			newTexts.put(ctx, "(" + s1 + ")");
		} else if (isDeclaration(ctx)) {
			// IDENT '[' expr ']'
			if (ctx.getChild(2).equals(ctx.expr()) && ctx.getChild(1).getText().equals("[")
					&& ctx.getChild(3).getText().equals("]")) {
				String temp = newTexts.get(ctx.expr(0));
				StringBuilder lines = new StringBuilder();
				lines.append(ctx.IDENT() + "[" + temp + "]");
				newTexts.put(ctx, lines.toString());
			}
			// IDENT '(' args ')'
			if (ctx.getChild(2).equals(ctx.args()) && ctx.getChild(1).getText().equals("(")
					&& ctx.getChild(3).getText().equals(")")) {
				String temp = newTexts.get(ctx.args());
				StringBuilder lines = new StringBuilder();
				lines.append(ctx.IDENT() + "(" + temp + ")");
				newTexts.put(ctx, lines.toString());
			}
		} else if (isIDENT(ctx)) {
			// IDENT
			String temp = ctx.getChild(0).getText();
			newTexts.put(ctx, temp);
		} else if (isAssignment(ctx)) {
			// IDENT '[' expr ']' '=' expr
			StringBuilder lines = new StringBuilder();
			s1 = newTexts.get(ctx.expr(0));
			s2 = newTexts.get(ctx.expr(1));
			lines.append(newTexts.get(ctx.IDENT()) + " " + "[" + s1 + "] " + newTexts.get(ctx.getChild(4)) + " " + s2);
			newTexts.put(ctx, lines.toString());
		} else if ((ctx.getChildCount() == 3) && (ctx.getChild(2).equals(ctx.expr()))
				&& ctx.getChild(0).equals(ctx.IDENT())) {
			// IDENT '=' expr
			s1 = newTexts.get(ctx.IDENT());
			s2 = newTexts.get(ctx.expr(0));
			newTexts.put(ctx, s1 + " = " + s2);
		} else if (ctx.getChildCount() == 1 && ctx.getChild(0) == ctx.LITERAL()) {
			// LITERAL
			newTexts.put(ctx, ctx.getText());
		}
	}

	@Override
	public void exitCompound_stmt(Compound_stmtContext ctx) {
		// { local_decl* stmt* }
		StringBuilder lines = new StringBuilder();
		lines.append("{\n");
		for (int i = 0; i < ctx.local_decl().size(); i++) {
			lines.append("...." + newTexts.get(ctx.local_decl(i)) + "\n");
		}
		for (int i = 0; i < ctx.stmt().size(); i++) {
			lines.append("...." + newTexts.get(ctx.stmt(i)) + "\n");
		}
		lines.append("}\n");
		newTexts.put(ctx, lines.toString());
	}

	@Override
	public void exitProgram(ProgramContext ctx) {
		for(int i = 0; i< ctx.getChildCount(); i++) {
			newTexts.put(ctx, newTexts.get(ctx.getChild(i)) + "\n");
		}
		StringBuilder lines = new StringBuilder();
		for (int i = 0; i < ctx.getChildCount(); i++) {
			lines.append(newTexts.get(ctx.getChild(i)));
		}
		System.out.println(lines);
	}

	@Override
	public void exitDecl(DeclContext ctx) {
		StringBuilder lines = new StringBuilder();
		for (int i = 0; i < ctx.getChildCount(); i++) {
			lines.append(newTexts.get(ctx.getChild(i)));
		}
		newTexts.put(ctx, lines.toString());
	}

	@Override
	public void exitVar_decl(Var_declContext ctx) {
		StringBuilder lines = new StringBuilder();

		if (is_var_decl_1(ctx)) {
			// type_spec INDENT ;
			String temp = null;
			temp = newTexts.get(ctx.type_spec()) + " " + ctx.IDENT().getText() + ";";
			lines.append(temp);
			newTexts.put(ctx, lines.toString());
		} else if (is_var_decl_2(ctx)) {
			// type_spec IDENT '=' LITERAL ';'
			String temp = null;
			temp = newTexts.get(ctx.type_spec()) + " " + ctx.IDENT().getText() + " " + ctx.getChild(2).getText() + " "
					+ ctx.LITERAL().getText() + ctx.getChild(4).getText();
			lines.append(temp);
			newTexts.put(ctx, lines.toString());
		} else if (is_var_decl_3(ctx)) {
			// type_spec IDENT '[' LITERAL ']' ';'
			String temp = null;
			temp = newTexts.get(ctx.type_spec()) + " " + ctx.IDENT().getText() + " " + ctx.getChild(2).getText() + " "
					+ ctx.LITERAL().getText() + ctx.getChild(4).getText() + ctx.getChild(5).getText();
			lines.append(temp);
			newTexts.put(ctx, lines.toString());
		}
	}

	@Override
	public void exitType_spec(Type_specContext ctx) {
		newTexts.put(ctx, ctx.getText());
	}

	@Override
	public void exitFun_decl(Fun_declContext ctx) {
		// type_spec IDENT '(' params ')' compound_stmt
		StringBuilder lines = new StringBuilder();
		String temp = null;

		temp = newTexts.get(ctx.type_spec()) + " " + ctx.IDENT() + "(" + newTexts.get(ctx.params()) + ")" + "\n"
				+ newTexts.get(ctx.compound_stmt());
		lines.append(temp);

		newTexts.put(ctx, lines.toString());
	}

	@Override
	public void exitParams(ParamsContext ctx) {
		StringBuilder lines = new StringBuilder();
		if (ctx == ctx.VOID()) {
			lines.append(ctx.VOID());
			System.out.println(lines);
		} else if (ctx.getText() == "") {
			lines.append("");
			newTexts.put(ctx, lines.toString());
		} else{
			for (int i = 0; i < ctx.getChildCount()-1; i+=2) {
				lines.append(newTexts.get(ctx.getChild(i)) + ", ");
			}
			lines.append(newTexts.get(ctx.getChild(ctx.getChildCount()-1)));
			newTexts.put(ctx, lines.toString());
		}
		
	}

	@Override
	public void exitParam(ParamContext ctx) {
		StringBuilder lines = new StringBuilder();
		if (ctx.getChildCount() == 4) {
			// type_spec IDENT '[' ']'
			lines.append(ctx.type_spec().getText() + " " + ctx.IDENT() + "[]");
			newTexts.put(ctx, lines.toString());
		} else {
			lines.append(ctx.type_spec().getText() + " " + ctx.IDENT());
			newTexts.put(ctx, lines.toString());
		}
		// newTexts.put(ctx, ctx.);
	}

	@Override
	public void exitStmt(StmtContext ctx) {
		StringBuilder lines = new StringBuilder();
		if (newTexts.get(ctx.expr_stmt()) != null) {
			// all kind of Expr_stmt
			for (int i = 0; i < ctx.expr_stmt().getChildCount() - 1; i++) {
				lines.append(newTexts.get(ctx.expr_stmt()));
			}
			newTexts.put(ctx, lines.toString());
		} else if (newTexts.get(ctx.compound_stmt()) != null) {
			// { .... } 같이 생긴 stmt
			for (int i = 0; i < ctx.compound_stmt().getChildCount(); i++) {
				newTexts.put(ctx, "...." + newTexts.get(ctx.compound_stmt().getChild(0).getChild(i)));
			}
		} else if (newTexts.get(ctx.if_stmt()) != null) {
			// if문을 빠져나갈때 작동
			if (ctx.if_stmt().getChildCount() == 5) {
				// IF '(' expr ')' stmt
				for (int i = 0; i < ctx.if_stmt().getChildCount(); i++) {
					if (ctx.if_stmt().getChild(i) == ctx.if_stmt().IF()) {
						lines.append(ctx.if_stmt().IF());
					} else if (ctx.if_stmt().getChild(i) == ctx.if_stmt().expr()) {
						lines.append("(" + newTexts.get(ctx.if_stmt().getChild(i)) + ")\n");
					} else if (ctx.if_stmt().getChild(i) == ctx.if_stmt().stmt(0)) {
						String temp = newTexts.get(ctx.if_stmt().stmt(0).getChild(0));
						StringTokenizer st = new StringTokenizer(temp, "\n");
						if (ctx.if_stmt().stmt(0).getChild(0).getChildCount() == 2) {
							// if ( expr ) { stmt(1-child) }
							while (st.hasMoreElements()) {
								String t1 = st.nextToken();
								if (t1 == "\r\n")
									continue;
								lines.append("........" + t1);
							}
						} else {
							while (st.hasMoreElements()) {
								String t1 = st.nextToken();
								lines.append("...." + t1 + "\n");
							}

						}
					}
				}
			} else {//if ( expr ) stmt else stmt
				for (int i = 0; i < ctx.if_stmt().getChildCount(); i++) {
					if (ctx.if_stmt().getChild(i) == ctx.if_stmt().IF()) {
						lines.append(ctx.if_stmt().IF());
					} else if (ctx.if_stmt().getChild(i) == ctx.if_stmt().expr()) {
						lines.append("(" + newTexts.get(ctx.if_stmt().getChild(i)) + ")\n");
					} else if (ctx.if_stmt().getChild(i) == ctx.if_stmt().stmt(0)) {
						String temp = newTexts.get(ctx.if_stmt().stmt(0).getChild(0));
						StringTokenizer st = new StringTokenizer(temp, "\n");
						if (ctx.if_stmt().stmt(0).getChild(0).getChildCount() == 2) {
							// if ( expr ) { stmt(1-child) }
							while (st.hasMoreElements()) {
								String t1 = st.nextToken();
								if (t1 == "\r\n")
									continue;
								lines.append("........" + t1);
							}
						} else {
							while (st.hasMoreElements()) {
								String t1 = st.nextToken();
								if (t1 == "")
									continue;
								lines.append("...." + t1 + "\n");
							}
						}
						lines.append("...." + ctx.if_stmt().ELSE() + "\n");
						temp = newTexts.get(ctx.if_stmt().stmt(1).getChild(0));
						st = new StringTokenizer(temp, "\n");
						if (ctx.if_stmt().stmt(1).getChild(0).getChildCount() == 2) {
							// if ( expr ) { stmt(1-child) }
							while (st.hasMoreElements()) {
								String t1 = st.nextToken();
								if (t1 == "\r\n")
									continue;
								lines.append("........" + t1);
							}
						} else {
							while (st.hasMoreElements()) {
								String t1 = st.nextToken();
								if (t1 == "")
									continue;
								lines.append("...." + t1 + "\n");
							}
						}
					}
				}
			}
			newTexts.put(ctx, lines.toString());
		} else if (newTexts.get(ctx.while_stmt()) != null) {
			// while문을 nested구조로 만듦
			lines.append(ctx.while_stmt().WHILE() + "(" + newTexts.get(ctx.while_stmt().expr()) + ")\n");
			String temp = newTexts.get(ctx.while_stmt().stmt().getChild(0));
			StringTokenizer st = new StringTokenizer(temp, "\n");
			while (st.hasMoreElements()) {
				String t1 = st.nextToken();
				if (t1 == "")
					continue;
				lines.append("...." + t1 + "\n");
			}
			newTexts.put(ctx, lines.toString());
		} else if (newTexts.get(ctx.return_stmt()) != null) {
			// return 문
			newTexts.put(ctx, newTexts.get(ctx.return_stmt()));
		}
	}

	@Override
	public void exitExpr_stmt(Expr_stmtContext ctx) {
		StringBuilder lines = new StringBuilder();
		// expr ';'
		if (isexpr_stmt(ctx)) {
			lines.append(newTexts.get(ctx.expr()) + ";");
		}
		newTexts.put(ctx, lines.toString());
	}

	@Override
	public void exitWhile_stmt(While_stmtContext ctx) {
		// WHILE '(' expr ')' stmt
		StringBuilder lines = new StringBuilder();

		lines.append(ctx.WHILE() + "(" + newTexts.get(ctx.expr()) + ")\n");
		for (int i = 0; i < ctx.stmt().getChildCount(); i++) {
			lines.append(newTexts.get(ctx.stmt().getChild(i)));
		}
		newTexts.put(ctx, lines.toString());
	}

	@Override
	public void exitLocal_decl(Local_declContext ctx) {
		//System.out.println(ctx.getText());
		StringBuilder lines = new StringBuilder();
		if (islocal_decl1(ctx)) {
			// type_spec IDENT ';'
			lines.append(newTexts.get(ctx.type_spec()) + " " + ctx.IDENT() + ";");
			newTexts.put(ctx, lines.toString());

		} else if (islocal_decl2(ctx)) {
			// type_spec IDENT '=' LITERAL ';'
			lines.append(newTexts.get(ctx.type_spec()) + " " + ctx.IDENT() + " = " + ctx.LITERAL() + ";");
			newTexts.put(ctx, lines.toString());
		} else if (islocal_decl3(ctx)) {
			// type_spec IDENT '[' LITERAL ']' ';'
			lines.append(newTexts.get(ctx.type_spec()) + " " + ctx.IDENT() + " [" + ctx.LITERAL() + "];");
			newTexts.put(ctx, lines.toString());
		}
	}

	@Override
	public void exitIf_stmt(If_stmtContext ctx) {
		StringBuilder line = new StringBuilder();
		if (is_IF_function(ctx)) {
			// if ( expr ) stmt
			line.append(ctx.IF());
			line.append("(" + newTexts.get(ctx.expr()) + ")\n");
			line.append("{\n");
			line.append("...." + newTexts.get(ctx.stmt(0)));
			line.append("\n}");
			newTexts.put(ctx, line.toString());
		} else {
			// IF '(' expr ')' stmt ELSE stmt
			line.append(ctx.IF());
			line.append("( " + newTexts.get(ctx.expr()) + " )\n");
			line.append("{\n");
			line.append("...." + newTexts.get(ctx.stmt(0)));
			line.append("\n}");
			line.append(ctx.ELSE() + "\n");
			line.append("{\n");
			line.append("...." + newTexts.get(ctx.stmt(1)));
			line.append("\n}");
			newTexts.put(ctx, line.toString());
		}
	}

	@Override
	public void exitReturn_stmt(Return_stmtContext ctx) {
		if (ctx.getChildCount() == 2) {
			// RETURN ';'
			newTexts.put(ctx, ctx.RETURN() + ";");
		} else {
			// RETURN expr ';'
			newTexts.put(ctx, ctx.RETURN() + " " + newTexts.get(ctx.expr()) + ";");

		}
	}

	@Override
	public void exitArgs(ArgsContext ctx) {
		StringBuilder lines = new StringBuilder();
		for (int i = 0; i < ctx.getChildCount(); i++) {
			String s = "";
			if(ctx.getChild(i) instanceof ExprContext)
				s = newTexts.get(ctx.getChild(i));
			else
				s = ", ";
			lines.append(s);
		}
		newTexts.put(ctx, lines.toString());
	}

	@Override
	public void exitEveryRule(ParserRuleContext ctx) {

	}
}
