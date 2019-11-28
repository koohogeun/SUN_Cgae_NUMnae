package listener.main;

import java.util.Hashtable;

import generated.MiniCParser;
import generated.MiniCParser.ExprContext;
import generated.MiniCParser.Fun_declContext;
import generated.MiniCParser.If_stmtContext;
import generated.MiniCParser.Local_declContext;
import generated.MiniCParser.ParamContext;
import generated.MiniCParser.ParamsContext;
import generated.MiniCParser.Type_specContext;
import generated.MiniCParser.Var_declContext;
import listener.main.SymbolTable;
import listener.main.SymbolTable.VarInfo;

public class BytecodeGenListenerHelper {
	// <boolean functions>
	
	static boolean isFunDecl(MiniCParser.ProgramContext ctx, int i) {
		return ctx.getChild(i).getChild(0) instanceof MiniCParser.Fun_declContext;
	}
	
	// type_spec IDENT '[' ']'
	static boolean isArrayParamDecl(ParamContext param) {
		return param.getChildCount() == 4;
	}
	
	// global vars
	static int initVal(Var_declContext ctx) {
		return Integer.parseInt(ctx.LITERAL().getText());
	}

	// var_decl	: type_spec IDENT '=' LITERAL ';
	static boolean isDeclWithInit(Var_declContext ctx) {
		return ctx.getChildCount() == 5 ;
	}
	// var_decl	: type_spec IDENT '[' LITERAL ']' ';'
	static boolean isArrayDecl(Var_declContext ctx) {
		return ctx.getChildCount() == 6;
	}

	// <local vars>
	// local_decl	: type_spec IDENT '[' LITERAL ']' ';'
	static int initVal(Local_declContext ctx) {
		return Integer.parseInt(ctx.LITERAL().getText());
	}

	static boolean isArrayDecl(Local_declContext ctx) {
		return ctx.getChildCount() == 6;
	}
	
	static boolean isDeclWithInit(Local_declContext ctx) {
		return ctx.getChildCount() == 5 ;
	}
	
	static boolean isVoidF(Fun_declContext ctx) {
		return getTypeText((Type_specContext)ctx.getChild(0)).equals("V");
	}
	
	static boolean isIntReturn(MiniCParser.Return_stmtContext ctx) {
		return ctx.getChildCount() ==3;
	}


	static boolean isVoidReturn(MiniCParser.Return_stmtContext ctx) {
		return ctx.getChildCount() == 2;
	}
	
	// <information extraction>
	static String getStackSize(Fun_declContext ctx) {
		return "32";
	}
	static String getLocalVarSize(Fun_declContext ctx) {
		return "32";
	}
	static String getTypeText(Type_specContext typespec) {
		/*
		 * typespec을 확인해서 int인 경우에 I를 반환하고,
		 * void인 경우에 V를 반환한다.
		 */
		if (typespec.getText().equals("int")) {
			return "I";
		} else if (typespec.getText().equals("void")) {
			return "V";
		}
		return null;
	}

	// params
	static String getParamName(ParamContext param) {
		// <Fill in>
		// <Fill in>
		return null;
	}
	
	static String getParamTypesText(ParamsContext params) {
		String typeText = "";
		
		for(int i = 0; i < params.param().size(); i++) {
			MiniCParser.Type_specContext typespec = (MiniCParser.Type_specContext)  params.param(i).getChild(0);
			typeText += getTypeText(typespec); // + ";";
		}
		return typeText;
	}

	// 로컬 변수의 이름을 반환해준다.
	static String getLocalVarName(Local_declContext local_decl) {
		return local_decl.getChild(1).getText();
	}

	// 함수의 이름을 반환한다.
	static String getFunName(Fun_declContext ctx) {
		return ctx.children.get(1).getText();
	}

	// 함수의 이름을 반환한다.
	static String getFunName(ExprContext ctx) {
		// <Fill in>
		return ctx.IDENT().getText();
	}
	
	static boolean noElse(If_stmtContext ctx) {
		return ctx.getChildCount() <= 5;
	}

	/*
	 * 함수의 prolog를 만들어준다.
	 * 클래스를 선언해주고 super class의 생성자를 정의한다.
	 */
	static String getFunProlog() {
		String prolog = "";
		// return ".class public Test .....
		prolog += ".class public " + getCurrentClassName() + "\n";
		prolog += ".super java/lang/Object\n; strandard initializer\n";
		prolog += ".method public <init>()V\n" + "aload_0\n";
		// ...
		// invokenonvirtual java/lang/Object/<init>()
		prolog += "invokenonvirtual java/lang/Object/<init>()V\n";
		// return
		prolog += "return\n";
		// .end method"
		prolog += ".end method\n";
		return prolog;
	}
	
	static String getCurrentClassName() {
		return "Test";
	}
}
