package listener.main;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import generated.MiniCParser;
import generated.MiniCParser.Fun_declContext;
import generated.MiniCParser.Local_declContext;
import generated.MiniCParser.ParamsContext;
import generated.MiniCParser.Type_specContext;
import generated.MiniCParser.Var_declContext;
import listener.main.SymbolTable.Type;
import static listener.main.BytecodeGenListenerHelper.*;


		public class SymbolTable {
	enum Type {
		INT, INTARRAY, VOID, ERROR
	}
	
	static public class VarInfo {
		Type type; 
		int id;
		int initVal;
		
		public VarInfo(Type type,  int id, int initVal) {
			this.type = type;
			this.id = id;
			this.initVal = initVal;
		}
		public VarInfo(Type type,  int id) {
			this.type = type;
			this.id = id;
			this.initVal = 0;
		}
	}
	
	static public class FInfo {
		public String sigStr;
	}
	
	private Map<String, VarInfo> _lsymtable = new HashMap<>();	// local v.
	private Map<String, VarInfo> _gsymtable = new HashMap<>();	// global v.
	private Map<String, FInfo> _fsymtable = new HashMap<>();	// function 
	
		
	private int _globalVarID = 0;
	private int _localVarID = 0;
	private int _labelID = 0;
	private int _tempVarID = 0;
	
	SymbolTable(){
		initFunDecl();
		initFunTable();
	}
	
	void initFunDecl(){		// at each func decl
		_lsymtable.clear();
		_localVarID = 0;
		_labelID = 0;
		_tempVarID = 31;		
	}
	/*
	 *  로컬 변수를 테이블에 저장하는 함수이다.
	 *  변수가 저장되는 순서대로 ID를 증가시키면서 저장시켜준다.
	 */
	void putLocalVar(String varname, Type type){
		_lsymtable.put(varname, new VarInfo(type, _localVarID++));
	}

	// 초기값이 없는 글로벌 변수를 테이블에 저장한다.
	void putGlobalVar(String varname, Type type){
		_gsymtable.put(varname, new VarInfo(type, _globalVarID++));
	}

	// 초기값이 있는 로컬 변수를 테이블에 저장한다.
	void putLocalVarWithInitVal(String varname, Type type, int initVar){
		_lsymtable.put(varname, new VarInfo(type, _localVarID++, initVar));
	}
	// 초기값이 있는 글로벌 변수를 테이블에 저장한다.
	void putGlobalVarWithInitVal(String varname, Type type, int initVar){
		_gsymtable.put(varname, new VarInfo(type, _globalVarID++, initVar));
	
	}

	/*
	 * 파라미터를 테이블에 저장하는 함수이다.
	 * 들어오는 파라미터를 순차적으로 받아서 putLocalVar 함수를 이용해 테이블에 저장한다.
	 */
	void putParams(MiniCParser.ParamsContext params) {
		for(int i = 0; i < params.param().size(); i++) {
			MiniCParser.ParamContext p = params.param(i);
			String ptype = p.start.getText();
			String pname = p.stop.getText();
			putLocalVar(pname, Type.INT);
		}
	}
	
	private void initFunTable() {
		FInfo printlninfo = new FInfo();
		printlninfo.sigStr = "java/io/PrintStream/println(I)V";
		
		FInfo maininfo = new FInfo();
		maininfo.sigStr = "main([Ljava/lang/String;)V";
		_fsymtable.put("_print", printlninfo);
		_fsymtable.put("main", maininfo);
	}

	/*
	 * 함수의 spec을 반환해주는 함수이다.
	 */
	public String getFunSpecStr(String fname) {
		return _fsymtable.get(fname).sigStr;
	}

	public String getFunSpecStr(Fun_declContext ctx) {
		return getFunSpecStr(ctx.IDENT().getText());
	}
	/*
	 * 함수 테이블에 새로운 함수들 넣어주는 함수이다.
	 * 이름과 반환 타입, 그리고 파라미터들을 넣어준다.
	 */
	public String putFunSpecStr(Fun_declContext ctx) {
		String fname = getFunName(ctx);
		String argtype = "";
		String rtype = "";
		String res = "";
		ParamsContext paramsContext = (ParamsContext)ctx.children.get(3);
		for (MiniCParser.ParamContext param : paramsContext.param()) {
			argtype += getTypeText(param.type_spec());
		}
		rtype = getTypeText((Type_specContext)ctx.getChild(0));

		res =  fname + "(" + argtype + ")" + rtype;
		
		FInfo finfo = new FInfo();
		finfo.sigStr = res;
		_fsymtable.put(fname, finfo);
		
		return res;
	}
	/*
	 * 변수의 id를 반환한다.
	 * 테이블에서 변수이름으로 id를 찾아서 반환.
	 */
	String getVarId(String name){
		return Integer.toString(_lsymtable.get(name).id);
	}
	
	Type getVarType(String name){
		VarInfo lvar = (VarInfo) _lsymtable.get(name);
		if (lvar != null) {
			return lvar.type;
		}
		
		VarInfo gvar = (VarInfo) _gsymtable.get(name);
		if (gvar != null) {
			return gvar.type;
		}
		
		return Type.ERROR;	
	}
	String newLabel() {
		return "label" + _labelID++;
	}
	
	String newTempVar() {
		String id = "";
		return id + _tempVarID--;
	}

	// global
	public String getVarId(Var_declContext ctx) {
		// <Fill here>
		return null;
	}

	// local
	public String getVarId(Local_declContext ctx) {
		String sname = "";
		sname += getVarId(ctx.IDENT().getText());
		return sname;
	}
	
}
