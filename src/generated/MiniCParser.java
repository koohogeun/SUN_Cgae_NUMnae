// Generated from C:/Users/deer9/Desktop/junior_second/compiler/5-2week/BytecodeGenerator2019-master/src\MiniC.g4 by ANTLR 4.7.2
 
package generated;

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class MiniCParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.7.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		T__17=18, T__18=19, VOID=20, INT=21, WHILE=22, IF=23, ELSE=24, RETURN=25, 
		OR=26, AND=27, LE=28, GE=29, EQ=30, NE=31, IDENT=32, LITERAL=33, DecimalConstant=34, 
		OctalConstant=35, HexadecimalConstant=36, WS=37;
	public static final int
		RULE_program = 0, RULE_decl = 1, RULE_var_decl = 2, RULE_type_spec = 3, 
		RULE_fun_decl = 4, RULE_params = 5, RULE_param = 6, RULE_stmt = 7, RULE_expr_stmt = 8, 
		RULE_while_stmt = 9, RULE_compound_stmt = 10, RULE_local_decl = 11, RULE_if_stmt = 12, 
		RULE_return_stmt = 13, RULE_expr = 14, RULE_args = 15, RULE_arr = 16, 
		RULE_arr_stmt = 17, RULE_arr_decl = 18, RULE_arr_decl_stmt = 19, RULE_arr_parm = 20, 
		RULE_arr_parm_stmt = 21;
	private static String[] makeRuleNames() {
		return new String[] {
			"program", "decl", "var_decl", "type_spec", "fun_decl", "params", "param", 
			"stmt", "expr_stmt", "while_stmt", "compound_stmt", "local_decl", "if_stmt", 
			"return_stmt", "expr", "args", "arr", "arr_stmt", "arr_decl", "arr_decl_stmt", 
			"arr_parm", "arr_parm_stmt"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "';'", "'='", "'('", "')'", "','", "'{'", "'}'", "'-'", "'+'", 
			"'--'", "'++'", "'*'", "'/'", "'%'", "'<'", "'>'", "'!'", "'['", "']'", 
			"'void'", "'int'", "'while'", "'if'", "'else'", "'return'", "'or'", "'and'", 
			"'<='", "'>='", "'=='", "'!='"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, "VOID", "INT", "WHILE", 
			"IF", "ELSE", "RETURN", "OR", "AND", "LE", "GE", "EQ", "NE", "IDENT", 
			"LITERAL", "DecimalConstant", "OctalConstant", "HexadecimalConstant", 
			"WS"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "MiniC.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public MiniCParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class ProgramContext extends ParserRuleContext {
		public List<DeclContext> decl() {
			return getRuleContexts(DeclContext.class);
		}
		public DeclContext decl(int i) {
			return getRuleContext(DeclContext.class,i);
		}
		public ProgramContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_program; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniCListener ) ((MiniCListener)listener).enterProgram(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniCListener ) ((MiniCListener)listener).exitProgram(this);
		}
	}

	public final ProgramContext program() throws RecognitionException {
		ProgramContext _localctx = new ProgramContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_program);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(45); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(44);
				decl();
				}
				}
				setState(47); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==VOID || _la==INT );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DeclContext extends ParserRuleContext {
		public Var_declContext var_decl() {
			return getRuleContext(Var_declContext.class,0);
		}
		public Fun_declContext fun_decl() {
			return getRuleContext(Fun_declContext.class,0);
		}
		public DeclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_decl; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniCListener ) ((MiniCListener)listener).enterDecl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniCListener ) ((MiniCListener)listener).exitDecl(this);
		}
	}

	public final DeclContext decl() throws RecognitionException {
		DeclContext _localctx = new DeclContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_decl);
		try {
			setState(51);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(49);
				var_decl();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(50);
				fun_decl();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Var_declContext extends ParserRuleContext {
		public Type_specContext type_spec() {
			return getRuleContext(Type_specContext.class,0);
		}
		public TerminalNode IDENT() { return getToken(MiniCParser.IDENT, 0); }
		public TerminalNode LITERAL() { return getToken(MiniCParser.LITERAL, 0); }
		public Arr_declContext arr_decl() {
			return getRuleContext(Arr_declContext.class,0);
		}
		public Var_declContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_var_decl; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniCListener ) ((MiniCListener)listener).enterVar_decl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniCListener ) ((MiniCListener)listener).exitVar_decl(this);
		}
	}

	public final Var_declContext var_decl() throws RecognitionException {
		Var_declContext _localctx = new Var_declContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_var_decl);
		try {
			setState(68);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,2,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(53);
				type_spec();
				setState(54);
				match(IDENT);
				setState(55);
				match(T__0);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(57);
				type_spec();
				setState(58);
				match(IDENT);
				setState(59);
				match(T__1);
				setState(60);
				match(LITERAL);
				setState(61);
				match(T__0);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(63);
				type_spec();
				setState(64);
				match(IDENT);
				setState(65);
				arr_decl();
				setState(66);
				match(T__0);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Type_specContext extends ParserRuleContext {
		public TerminalNode VOID() { return getToken(MiniCParser.VOID, 0); }
		public TerminalNode INT() { return getToken(MiniCParser.INT, 0); }
		public Type_specContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_type_spec; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniCListener ) ((MiniCListener)listener).enterType_spec(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniCListener ) ((MiniCListener)listener).exitType_spec(this);
		}
	}

	public final Type_specContext type_spec() throws RecognitionException {
		Type_specContext _localctx = new Type_specContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_type_spec);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(70);
			_la = _input.LA(1);
			if ( !(_la==VOID || _la==INT) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Fun_declContext extends ParserRuleContext {
		public Type_specContext type_spec() {
			return getRuleContext(Type_specContext.class,0);
		}
		public TerminalNode IDENT() { return getToken(MiniCParser.IDENT, 0); }
		public ParamsContext params() {
			return getRuleContext(ParamsContext.class,0);
		}
		public Compound_stmtContext compound_stmt() {
			return getRuleContext(Compound_stmtContext.class,0);
		}
		public Fun_declContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_fun_decl; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniCListener ) ((MiniCListener)listener).enterFun_decl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniCListener ) ((MiniCListener)listener).exitFun_decl(this);
		}
	}

	public final Fun_declContext fun_decl() throws RecognitionException {
		Fun_declContext _localctx = new Fun_declContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_fun_decl);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(72);
			type_spec();
			setState(73);
			match(IDENT);
			setState(74);
			match(T__2);
			setState(75);
			params();
			setState(76);
			match(T__3);
			setState(77);
			compound_stmt();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ParamsContext extends ParserRuleContext {
		public List<ParamContext> param() {
			return getRuleContexts(ParamContext.class);
		}
		public ParamContext param(int i) {
			return getRuleContext(ParamContext.class,i);
		}
		public TerminalNode VOID() { return getToken(MiniCParser.VOID, 0); }
		public ParamsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_params; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniCListener ) ((MiniCListener)listener).enterParams(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniCListener ) ((MiniCListener)listener).exitParams(this);
		}
	}

	public final ParamsContext params() throws RecognitionException {
		ParamsContext _localctx = new ParamsContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_params);
		int _la;
		try {
			setState(89);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,4,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(79);
				param();
				setState(84);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__4) {
					{
					{
					setState(80);
					match(T__4);
					setState(81);
					param();
					}
					}
					setState(86);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(87);
				match(VOID);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ParamContext extends ParserRuleContext {
		public Type_specContext type_spec() {
			return getRuleContext(Type_specContext.class,0);
		}
		public TerminalNode IDENT() { return getToken(MiniCParser.IDENT, 0); }
		public Arr_parmContext arr_parm() {
			return getRuleContext(Arr_parmContext.class,0);
		}
		public ParamContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_param; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniCListener ) ((MiniCListener)listener).enterParam(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniCListener ) ((MiniCListener)listener).exitParam(this);
		}
	}

	public final ParamContext param() throws RecognitionException {
		ParamContext _localctx = new ParamContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_param);
		try {
			setState(98);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,5,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(91);
				type_spec();
				setState(92);
				match(IDENT);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(94);
				type_spec();
				setState(95);
				match(IDENT);
				setState(96);
				arr_parm();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StmtContext extends ParserRuleContext {
		public Expr_stmtContext expr_stmt() {
			return getRuleContext(Expr_stmtContext.class,0);
		}
		public Compound_stmtContext compound_stmt() {
			return getRuleContext(Compound_stmtContext.class,0);
		}
		public If_stmtContext if_stmt() {
			return getRuleContext(If_stmtContext.class,0);
		}
		public While_stmtContext while_stmt() {
			return getRuleContext(While_stmtContext.class,0);
		}
		public Return_stmtContext return_stmt() {
			return getRuleContext(Return_stmtContext.class,0);
		}
		public StmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_stmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniCListener ) ((MiniCListener)listener).enterStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniCListener ) ((MiniCListener)listener).exitStmt(this);
		}
	}

	public final StmtContext stmt() throws RecognitionException {
		StmtContext _localctx = new StmtContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_stmt);
		try {
			setState(105);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__2:
			case T__7:
			case T__8:
			case T__9:
			case T__10:
			case T__16:
			case IDENT:
			case LITERAL:
				enterOuterAlt(_localctx, 1);
				{
				setState(100);
				expr_stmt();
				}
				break;
			case T__5:
				enterOuterAlt(_localctx, 2);
				{
				setState(101);
				compound_stmt();
				}
				break;
			case IF:
				enterOuterAlt(_localctx, 3);
				{
				setState(102);
				if_stmt();
				}
				break;
			case WHILE:
				enterOuterAlt(_localctx, 4);
				{
				setState(103);
				while_stmt();
				}
				break;
			case RETURN:
				enterOuterAlt(_localctx, 5);
				{
				setState(104);
				return_stmt();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Expr_stmtContext extends ParserRuleContext {
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public Expr_stmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr_stmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniCListener ) ((MiniCListener)listener).enterExpr_stmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniCListener ) ((MiniCListener)listener).exitExpr_stmt(this);
		}
	}

	public final Expr_stmtContext expr_stmt() throws RecognitionException {
		Expr_stmtContext _localctx = new Expr_stmtContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_expr_stmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(107);
			expr(0);
			setState(108);
			match(T__0);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class While_stmtContext extends ParserRuleContext {
		public TerminalNode WHILE() { return getToken(MiniCParser.WHILE, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public StmtContext stmt() {
			return getRuleContext(StmtContext.class,0);
		}
		public While_stmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_while_stmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniCListener ) ((MiniCListener)listener).enterWhile_stmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniCListener ) ((MiniCListener)listener).exitWhile_stmt(this);
		}
	}

	public final While_stmtContext while_stmt() throws RecognitionException {
		While_stmtContext _localctx = new While_stmtContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_while_stmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(110);
			match(WHILE);
			setState(111);
			match(T__2);
			setState(112);
			expr(0);
			setState(113);
			match(T__3);
			setState(114);
			stmt();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Compound_stmtContext extends ParserRuleContext {
		public List<Local_declContext> local_decl() {
			return getRuleContexts(Local_declContext.class);
		}
		public Local_declContext local_decl(int i) {
			return getRuleContext(Local_declContext.class,i);
		}
		public List<StmtContext> stmt() {
			return getRuleContexts(StmtContext.class);
		}
		public StmtContext stmt(int i) {
			return getRuleContext(StmtContext.class,i);
		}
		public Compound_stmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_compound_stmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniCListener ) ((MiniCListener)listener).enterCompound_stmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniCListener ) ((MiniCListener)listener).exitCompound_stmt(this);
		}
	}

	public final Compound_stmtContext compound_stmt() throws RecognitionException {
		Compound_stmtContext _localctx = new Compound_stmtContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_compound_stmt);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(116);
			match(T__5);
			setState(120);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==VOID || _la==INT) {
				{
				{
				setState(117);
				local_decl();
				}
				}
				setState(122);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(126);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__2) | (1L << T__5) | (1L << T__7) | (1L << T__8) | (1L << T__9) | (1L << T__10) | (1L << T__16) | (1L << WHILE) | (1L << IF) | (1L << RETURN) | (1L << IDENT) | (1L << LITERAL))) != 0)) {
				{
				{
				setState(123);
				stmt();
				}
				}
				setState(128);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(129);
			match(T__6);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Local_declContext extends ParserRuleContext {
		public Type_specContext type_spec() {
			return getRuleContext(Type_specContext.class,0);
		}
		public TerminalNode IDENT() { return getToken(MiniCParser.IDENT, 0); }
		public TerminalNode LITERAL() { return getToken(MiniCParser.LITERAL, 0); }
		public Arr_declContext arr_decl() {
			return getRuleContext(Arr_declContext.class,0);
		}
		public Local_declContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_local_decl; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniCListener ) ((MiniCListener)listener).enterLocal_decl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniCListener ) ((MiniCListener)listener).exitLocal_decl(this);
		}
	}

	public final Local_declContext local_decl() throws RecognitionException {
		Local_declContext _localctx = new Local_declContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_local_decl);
		try {
			setState(146);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,9,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(131);
				type_spec();
				setState(132);
				match(IDENT);
				setState(133);
				match(T__0);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(135);
				type_spec();
				setState(136);
				match(IDENT);
				setState(137);
				match(T__1);
				setState(138);
				match(LITERAL);
				setState(139);
				match(T__0);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(141);
				type_spec();
				setState(142);
				match(IDENT);
				setState(143);
				arr_decl();
				setState(144);
				match(T__0);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class If_stmtContext extends ParserRuleContext {
		public TerminalNode IF() { return getToken(MiniCParser.IF, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public List<StmtContext> stmt() {
			return getRuleContexts(StmtContext.class);
		}
		public StmtContext stmt(int i) {
			return getRuleContext(StmtContext.class,i);
		}
		public TerminalNode ELSE() { return getToken(MiniCParser.ELSE, 0); }
		public If_stmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_if_stmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniCListener ) ((MiniCListener)listener).enterIf_stmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniCListener ) ((MiniCListener)listener).exitIf_stmt(this);
		}
	}

	public final If_stmtContext if_stmt() throws RecognitionException {
		If_stmtContext _localctx = new If_stmtContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_if_stmt);
		try {
			setState(162);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,10,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(148);
				match(IF);
				setState(149);
				match(T__2);
				setState(150);
				expr(0);
				setState(151);
				match(T__3);
				setState(152);
				stmt();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(154);
				match(IF);
				setState(155);
				match(T__2);
				setState(156);
				expr(0);
				setState(157);
				match(T__3);
				setState(158);
				stmt();
				setState(159);
				match(ELSE);
				setState(160);
				stmt();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Return_stmtContext extends ParserRuleContext {
		public TerminalNode RETURN() { return getToken(MiniCParser.RETURN, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public Return_stmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_return_stmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniCListener ) ((MiniCListener)listener).enterReturn_stmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniCListener ) ((MiniCListener)listener).exitReturn_stmt(this);
		}
	}

	public final Return_stmtContext return_stmt() throws RecognitionException {
		Return_stmtContext _localctx = new Return_stmtContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_return_stmt);
		try {
			setState(170);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,11,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(164);
				match(RETURN);
				setState(165);
				match(T__0);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(166);
				match(RETURN);
				setState(167);
				expr(0);
				setState(168);
				match(T__0);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExprContext extends ParserRuleContext {
		public TerminalNode LITERAL() { return getToken(MiniCParser.LITERAL, 0); }
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode IDENT() { return getToken(MiniCParser.IDENT, 0); }
		public ArrContext arr() {
			return getRuleContext(ArrContext.class,0);
		}
		public ArgsContext args() {
			return getRuleContext(ArgsContext.class,0);
		}
		public Arr_declContext arr_decl() {
			return getRuleContext(Arr_declContext.class,0);
		}
		public TerminalNode EQ() { return getToken(MiniCParser.EQ, 0); }
		public TerminalNode NE() { return getToken(MiniCParser.NE, 0); }
		public TerminalNode LE() { return getToken(MiniCParser.LE, 0); }
		public TerminalNode GE() { return getToken(MiniCParser.GE, 0); }
		public TerminalNode AND() { return getToken(MiniCParser.AND, 0); }
		public TerminalNode OR() { return getToken(MiniCParser.OR, 0); }
		public ExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniCListener ) ((MiniCListener)listener).enterExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniCListener ) ((MiniCListener)listener).exitExpr(this);
		}
	}

	public final ExprContext expr() throws RecognitionException {
		return expr(0);
	}

	private ExprContext expr(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ExprContext _localctx = new ExprContext(_ctx, _parentState);
		ExprContext _prevctx = _localctx;
		int _startState = 28;
		enterRecursionRule(_localctx, 28, RULE_expr, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(204);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,12,_ctx) ) {
			case 1:
				{
				setState(173);
				match(LITERAL);
				}
				break;
			case 2:
				{
				setState(174);
				match(T__2);
				setState(175);
				expr(0);
				setState(176);
				match(T__3);
				}
				break;
			case 3:
				{
				setState(178);
				match(IDENT);
				}
				break;
			case 4:
				{
				setState(179);
				match(IDENT);
				setState(180);
				arr();
				}
				break;
			case 5:
				{
				setState(181);
				match(IDENT);
				setState(182);
				match(T__2);
				setState(183);
				args();
				setState(184);
				match(T__3);
				}
				break;
			case 6:
				{
				setState(186);
				match(T__7);
				setState(187);
				expr(20);
				}
				break;
			case 7:
				{
				setState(188);
				match(T__8);
				setState(189);
				expr(19);
				}
				break;
			case 8:
				{
				setState(190);
				match(T__9);
				setState(191);
				expr(18);
				}
				break;
			case 9:
				{
				setState(192);
				match(T__10);
				setState(193);
				expr(17);
				}
				break;
			case 10:
				{
				setState(194);
				match(T__16);
				setState(195);
				expr(5);
				}
				break;
			case 11:
				{
				setState(196);
				match(IDENT);
				setState(197);
				match(T__1);
				setState(198);
				expr(2);
				}
				break;
			case 12:
				{
				setState(199);
				match(IDENT);
				setState(200);
				arr_decl();
				setState(201);
				match(T__1);
				setState(202);
				expr(1);
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(247);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,14,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(245);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,13,_ctx) ) {
					case 1:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(206);
						if (!(precpred(_ctx, 16))) throw new FailedPredicateException(this, "precpred(_ctx, 16)");
						setState(207);
						match(T__11);
						setState(208);
						expr(17);
						}
						break;
					case 2:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(209);
						if (!(precpred(_ctx, 15))) throw new FailedPredicateException(this, "precpred(_ctx, 15)");
						setState(210);
						match(T__12);
						setState(211);
						expr(16);
						}
						break;
					case 3:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(212);
						if (!(precpred(_ctx, 14))) throw new FailedPredicateException(this, "precpred(_ctx, 14)");
						setState(213);
						match(T__13);
						setState(214);
						expr(15);
						}
						break;
					case 4:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(215);
						if (!(precpred(_ctx, 13))) throw new FailedPredicateException(this, "precpred(_ctx, 13)");
						setState(216);
						match(T__8);
						setState(217);
						expr(14);
						}
						break;
					case 5:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(218);
						if (!(precpred(_ctx, 12))) throw new FailedPredicateException(this, "precpred(_ctx, 12)");
						setState(219);
						match(T__7);
						setState(220);
						expr(13);
						}
						break;
					case 6:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(221);
						if (!(precpred(_ctx, 11))) throw new FailedPredicateException(this, "precpred(_ctx, 11)");
						setState(222);
						match(EQ);
						setState(223);
						expr(12);
						}
						break;
					case 7:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(224);
						if (!(precpred(_ctx, 10))) throw new FailedPredicateException(this, "precpred(_ctx, 10)");
						setState(225);
						match(NE);
						setState(226);
						expr(11);
						}
						break;
					case 8:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(227);
						if (!(precpred(_ctx, 9))) throw new FailedPredicateException(this, "precpred(_ctx, 9)");
						setState(228);
						match(LE);
						setState(229);
						expr(10);
						}
						break;
					case 9:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(230);
						if (!(precpred(_ctx, 8))) throw new FailedPredicateException(this, "precpred(_ctx, 8)");
						setState(231);
						match(T__14);
						setState(232);
						expr(9);
						}
						break;
					case 10:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(233);
						if (!(precpred(_ctx, 7))) throw new FailedPredicateException(this, "precpred(_ctx, 7)");
						setState(234);
						match(GE);
						setState(235);
						expr(8);
						}
						break;
					case 11:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(236);
						if (!(precpred(_ctx, 6))) throw new FailedPredicateException(this, "precpred(_ctx, 6)");
						setState(237);
						match(T__15);
						setState(238);
						expr(7);
						}
						break;
					case 12:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(239);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(240);
						match(AND);
						setState(241);
						expr(5);
						}
						break;
					case 13:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(242);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(243);
						match(OR);
						setState(244);
						expr(4);
						}
						break;
					}
					} 
				}
				setState(249);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,14,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class ArgsContext extends ParserRuleContext {
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public ArgsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_args; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniCListener ) ((MiniCListener)listener).enterArgs(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniCListener ) ((MiniCListener)listener).exitArgs(this);
		}
	}

	public final ArgsContext args() throws RecognitionException {
		ArgsContext _localctx = new ArgsContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_args);
		int _la;
		try {
			setState(259);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__2:
			case T__7:
			case T__8:
			case T__9:
			case T__10:
			case T__16:
			case IDENT:
			case LITERAL:
				enterOuterAlt(_localctx, 1);
				{
				setState(250);
				expr(0);
				setState(255);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__4) {
					{
					{
					setState(251);
					match(T__4);
					setState(252);
					expr(0);
					}
					}
					setState(257);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				break;
			case T__3:
				enterOuterAlt(_localctx, 2);
				{
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ArrContext extends ParserRuleContext {
		public List<Arr_stmtContext> arr_stmt() {
			return getRuleContexts(Arr_stmtContext.class);
		}
		public Arr_stmtContext arr_stmt(int i) {
			return getRuleContext(Arr_stmtContext.class,i);
		}
		public ArrContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniCListener ) ((MiniCListener)listener).enterArr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniCListener ) ((MiniCListener)listener).exitArr(this);
		}
	}

	public final ArrContext arr() throws RecognitionException {
		ArrContext _localctx = new ArrContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_arr);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(262); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(261);
					arr_stmt();
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(264); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,17,_ctx);
			} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Arr_stmtContext extends ParserRuleContext {
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public Arr_stmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arr_stmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniCListener ) ((MiniCListener)listener).enterArr_stmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniCListener ) ((MiniCListener)listener).exitArr_stmt(this);
		}
	}

	public final Arr_stmtContext arr_stmt() throws RecognitionException {
		Arr_stmtContext _localctx = new Arr_stmtContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_arr_stmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(266);
			match(T__17);
			setState(267);
			expr(0);
			setState(268);
			match(T__18);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Arr_declContext extends ParserRuleContext {
		public List<Arr_decl_stmtContext> arr_decl_stmt() {
			return getRuleContexts(Arr_decl_stmtContext.class);
		}
		public Arr_decl_stmtContext arr_decl_stmt(int i) {
			return getRuleContext(Arr_decl_stmtContext.class,i);
		}
		public Arr_declContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arr_decl; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniCListener ) ((MiniCListener)listener).enterArr_decl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniCListener ) ((MiniCListener)listener).exitArr_decl(this);
		}
	}

	public final Arr_declContext arr_decl() throws RecognitionException {
		Arr_declContext _localctx = new Arr_declContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_arr_decl);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(271); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(270);
				arr_decl_stmt();
				}
				}
				setState(273); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==T__17 );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Arr_decl_stmtContext extends ParserRuleContext {
		public TerminalNode LITERAL() { return getToken(MiniCParser.LITERAL, 0); }
		public Arr_decl_stmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arr_decl_stmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniCListener ) ((MiniCListener)listener).enterArr_decl_stmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniCListener ) ((MiniCListener)listener).exitArr_decl_stmt(this);
		}
	}

	public final Arr_decl_stmtContext arr_decl_stmt() throws RecognitionException {
		Arr_decl_stmtContext _localctx = new Arr_decl_stmtContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_arr_decl_stmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(275);
			match(T__17);
			setState(276);
			match(LITERAL);
			setState(277);
			match(T__18);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Arr_parmContext extends ParserRuleContext {
		public List<Arr_parm_stmtContext> arr_parm_stmt() {
			return getRuleContexts(Arr_parm_stmtContext.class);
		}
		public Arr_parm_stmtContext arr_parm_stmt(int i) {
			return getRuleContext(Arr_parm_stmtContext.class,i);
		}
		public Arr_parmContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arr_parm; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniCListener ) ((MiniCListener)listener).enterArr_parm(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniCListener ) ((MiniCListener)listener).exitArr_parm(this);
		}
	}

	public final Arr_parmContext arr_parm() throws RecognitionException {
		Arr_parmContext _localctx = new Arr_parmContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_arr_parm);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(280); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(279);
				arr_parm_stmt();
				}
				}
				setState(282); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==T__17 );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Arr_parm_stmtContext extends ParserRuleContext {
		public Arr_parm_stmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arr_parm_stmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniCListener ) ((MiniCListener)listener).enterArr_parm_stmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniCListener ) ((MiniCListener)listener).exitArr_parm_stmt(this);
		}
	}

	public final Arr_parm_stmtContext arr_parm_stmt() throws RecognitionException {
		Arr_parm_stmtContext _localctx = new Arr_parm_stmtContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_arr_parm_stmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(284);
			match(T__17);
			setState(285);
			match(T__18);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 14:
			return expr_sempred((ExprContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean expr_sempred(ExprContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 16);
		case 1:
			return precpred(_ctx, 15);
		case 2:
			return precpred(_ctx, 14);
		case 3:
			return precpred(_ctx, 13);
		case 4:
			return precpred(_ctx, 12);
		case 5:
			return precpred(_ctx, 11);
		case 6:
			return precpred(_ctx, 10);
		case 7:
			return precpred(_ctx, 9);
		case 8:
			return precpred(_ctx, 8);
		case 9:
			return precpred(_ctx, 7);
		case 10:
			return precpred(_ctx, 6);
		case 11:
			return precpred(_ctx, 4);
		case 12:
			return precpred(_ctx, 3);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\'\u0122\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\3\2\6\2\60\n\2\r\2"+
		"\16\2\61\3\3\3\3\5\3\66\n\3\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3"+
		"\4\3\4\3\4\3\4\3\4\5\4G\n\4\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\7\3"+
		"\7\3\7\7\7U\n\7\f\7\16\7X\13\7\3\7\3\7\5\7\\\n\7\3\b\3\b\3\b\3\b\3\b\3"+
		"\b\3\b\5\be\n\b\3\t\3\t\3\t\3\t\3\t\5\tl\n\t\3\n\3\n\3\n\3\13\3\13\3\13"+
		"\3\13\3\13\3\13\3\f\3\f\7\fy\n\f\f\f\16\f|\13\f\3\f\7\f\177\n\f\f\f\16"+
		"\f\u0082\13\f\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r"+
		"\3\r\3\r\3\r\5\r\u0095\n\r\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16"+
		"\3\16\3\16\3\16\3\16\3\16\5\16\u00a5\n\16\3\17\3\17\3\17\3\17\3\17\3\17"+
		"\5\17\u00ad\n\17\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20"+
		"\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20"+
		"\3\20\3\20\3\20\3\20\3\20\3\20\3\20\5\20\u00cf\n\20\3\20\3\20\3\20\3\20"+
		"\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20"+
		"\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20"+
		"\3\20\3\20\3\20\3\20\3\20\3\20\3\20\7\20\u00f8\n\20\f\20\16\20\u00fb\13"+
		"\20\3\21\3\21\3\21\7\21\u0100\n\21\f\21\16\21\u0103\13\21\3\21\5\21\u0106"+
		"\n\21\3\22\6\22\u0109\n\22\r\22\16\22\u010a\3\23\3\23\3\23\3\23\3\24\6"+
		"\24\u0112\n\24\r\24\16\24\u0113\3\25\3\25\3\25\3\25\3\26\6\26\u011b\n"+
		"\26\r\26\16\26\u011c\3\27\3\27\3\27\3\27\2\3\36\30\2\4\6\b\n\f\16\20\22"+
		"\24\26\30\32\34\36 \"$&(*,\2\3\3\2\26\27\2\u013a\2/\3\2\2\2\4\65\3\2\2"+
		"\2\6F\3\2\2\2\bH\3\2\2\2\nJ\3\2\2\2\f[\3\2\2\2\16d\3\2\2\2\20k\3\2\2\2"+
		"\22m\3\2\2\2\24p\3\2\2\2\26v\3\2\2\2\30\u0094\3\2\2\2\32\u00a4\3\2\2\2"+
		"\34\u00ac\3\2\2\2\36\u00ce\3\2\2\2 \u0105\3\2\2\2\"\u0108\3\2\2\2$\u010c"+
		"\3\2\2\2&\u0111\3\2\2\2(\u0115\3\2\2\2*\u011a\3\2\2\2,\u011e\3\2\2\2."+
		"\60\5\4\3\2/.\3\2\2\2\60\61\3\2\2\2\61/\3\2\2\2\61\62\3\2\2\2\62\3\3\2"+
		"\2\2\63\66\5\6\4\2\64\66\5\n\6\2\65\63\3\2\2\2\65\64\3\2\2\2\66\5\3\2"+
		"\2\2\678\5\b\5\289\7\"\2\29:\7\3\2\2:G\3\2\2\2;<\5\b\5\2<=\7\"\2\2=>\7"+
		"\4\2\2>?\7#\2\2?@\7\3\2\2@G\3\2\2\2AB\5\b\5\2BC\7\"\2\2CD\5&\24\2DE\7"+
		"\3\2\2EG\3\2\2\2F\67\3\2\2\2F;\3\2\2\2FA\3\2\2\2G\7\3\2\2\2HI\t\2\2\2"+
		"I\t\3\2\2\2JK\5\b\5\2KL\7\"\2\2LM\7\5\2\2MN\5\f\7\2NO\7\6\2\2OP\5\26\f"+
		"\2P\13\3\2\2\2QV\5\16\b\2RS\7\7\2\2SU\5\16\b\2TR\3\2\2\2UX\3\2\2\2VT\3"+
		"\2\2\2VW\3\2\2\2W\\\3\2\2\2XV\3\2\2\2Y\\\7\26\2\2Z\\\3\2\2\2[Q\3\2\2\2"+
		"[Y\3\2\2\2[Z\3\2\2\2\\\r\3\2\2\2]^\5\b\5\2^_\7\"\2\2_e\3\2\2\2`a\5\b\5"+
		"\2ab\7\"\2\2bc\5*\26\2ce\3\2\2\2d]\3\2\2\2d`\3\2\2\2e\17\3\2\2\2fl\5\22"+
		"\n\2gl\5\26\f\2hl\5\32\16\2il\5\24\13\2jl\5\34\17\2kf\3\2\2\2kg\3\2\2"+
		"\2kh\3\2\2\2ki\3\2\2\2kj\3\2\2\2l\21\3\2\2\2mn\5\36\20\2no\7\3\2\2o\23"+
		"\3\2\2\2pq\7\30\2\2qr\7\5\2\2rs\5\36\20\2st\7\6\2\2tu\5\20\t\2u\25\3\2"+
		"\2\2vz\7\b\2\2wy\5\30\r\2xw\3\2\2\2y|\3\2\2\2zx\3\2\2\2z{\3\2\2\2{\u0080"+
		"\3\2\2\2|z\3\2\2\2}\177\5\20\t\2~}\3\2\2\2\177\u0082\3\2\2\2\u0080~\3"+
		"\2\2\2\u0080\u0081\3\2\2\2\u0081\u0083\3\2\2\2\u0082\u0080\3\2\2\2\u0083"+
		"\u0084\7\t\2\2\u0084\27\3\2\2\2\u0085\u0086\5\b\5\2\u0086\u0087\7\"\2"+
		"\2\u0087\u0088\7\3\2\2\u0088\u0095\3\2\2\2\u0089\u008a\5\b\5\2\u008a\u008b"+
		"\7\"\2\2\u008b\u008c\7\4\2\2\u008c\u008d\7#\2\2\u008d\u008e\7\3\2\2\u008e"+
		"\u0095\3\2\2\2\u008f\u0090\5\b\5\2\u0090\u0091\7\"\2\2\u0091\u0092\5&"+
		"\24\2\u0092\u0093\7\3\2\2\u0093\u0095\3\2\2\2\u0094\u0085\3\2\2\2\u0094"+
		"\u0089\3\2\2\2\u0094\u008f\3\2\2\2\u0095\31\3\2\2\2\u0096\u0097\7\31\2"+
		"\2\u0097\u0098\7\5\2\2\u0098\u0099\5\36\20\2\u0099\u009a\7\6\2\2\u009a"+
		"\u009b\5\20\t\2\u009b\u00a5\3\2\2\2\u009c\u009d\7\31\2\2\u009d\u009e\7"+
		"\5\2\2\u009e\u009f\5\36\20\2\u009f\u00a0\7\6\2\2\u00a0\u00a1\5\20\t\2"+
		"\u00a1\u00a2\7\32\2\2\u00a2\u00a3\5\20\t\2\u00a3\u00a5\3\2\2\2\u00a4\u0096"+
		"\3\2\2\2\u00a4\u009c\3\2\2\2\u00a5\33\3\2\2\2\u00a6\u00a7\7\33\2\2\u00a7"+
		"\u00ad\7\3\2\2\u00a8\u00a9\7\33\2\2\u00a9\u00aa\5\36\20\2\u00aa\u00ab"+
		"\7\3\2\2\u00ab\u00ad\3\2\2\2\u00ac\u00a6\3\2\2\2\u00ac\u00a8\3\2\2\2\u00ad"+
		"\35\3\2\2\2\u00ae\u00af\b\20\1\2\u00af\u00cf\7#\2\2\u00b0\u00b1\7\5\2"+
		"\2\u00b1\u00b2\5\36\20\2\u00b2\u00b3\7\6\2\2\u00b3\u00cf\3\2\2\2\u00b4"+
		"\u00cf\7\"\2\2\u00b5\u00b6\7\"\2\2\u00b6\u00cf\5\"\22\2\u00b7\u00b8\7"+
		"\"\2\2\u00b8\u00b9\7\5\2\2\u00b9\u00ba\5 \21\2\u00ba\u00bb\7\6\2\2\u00bb"+
		"\u00cf\3\2\2\2\u00bc\u00bd\7\n\2\2\u00bd\u00cf\5\36\20\26\u00be\u00bf"+
		"\7\13\2\2\u00bf\u00cf\5\36\20\25\u00c0\u00c1\7\f\2\2\u00c1\u00cf\5\36"+
		"\20\24\u00c2\u00c3\7\r\2\2\u00c3\u00cf\5\36\20\23\u00c4\u00c5\7\23\2\2"+
		"\u00c5\u00cf\5\36\20\7\u00c6\u00c7\7\"\2\2\u00c7\u00c8\7\4\2\2\u00c8\u00cf"+
		"\5\36\20\4\u00c9\u00ca\7\"\2\2\u00ca\u00cb\5&\24\2\u00cb\u00cc\7\4\2\2"+
		"\u00cc\u00cd\5\36\20\3\u00cd\u00cf\3\2\2\2\u00ce\u00ae\3\2\2\2\u00ce\u00b0"+
		"\3\2\2\2\u00ce\u00b4\3\2\2\2\u00ce\u00b5\3\2\2\2\u00ce\u00b7\3\2\2\2\u00ce"+
		"\u00bc\3\2\2\2\u00ce\u00be\3\2\2\2\u00ce\u00c0\3\2\2\2\u00ce\u00c2\3\2"+
		"\2\2\u00ce\u00c4\3\2\2\2\u00ce\u00c6\3\2\2\2\u00ce\u00c9\3\2\2\2\u00cf"+
		"\u00f9\3\2\2\2\u00d0\u00d1\f\22\2\2\u00d1\u00d2\7\16\2\2\u00d2\u00f8\5"+
		"\36\20\23\u00d3\u00d4\f\21\2\2\u00d4\u00d5\7\17\2\2\u00d5\u00f8\5\36\20"+
		"\22\u00d6\u00d7\f\20\2\2\u00d7\u00d8\7\20\2\2\u00d8\u00f8\5\36\20\21\u00d9"+
		"\u00da\f\17\2\2\u00da\u00db\7\13\2\2\u00db\u00f8\5\36\20\20\u00dc\u00dd"+
		"\f\16\2\2\u00dd\u00de\7\n\2\2\u00de\u00f8\5\36\20\17\u00df\u00e0\f\r\2"+
		"\2\u00e0\u00e1\7 \2\2\u00e1\u00f8\5\36\20\16\u00e2\u00e3\f\f\2\2\u00e3"+
		"\u00e4\7!\2\2\u00e4\u00f8\5\36\20\r\u00e5\u00e6\f\13\2\2\u00e6\u00e7\7"+
		"\36\2\2\u00e7\u00f8\5\36\20\f\u00e8\u00e9\f\n\2\2\u00e9\u00ea\7\21\2\2"+
		"\u00ea\u00f8\5\36\20\13\u00eb\u00ec\f\t\2\2\u00ec\u00ed\7\37\2\2\u00ed"+
		"\u00f8\5\36\20\n\u00ee\u00ef\f\b\2\2\u00ef\u00f0\7\22\2\2\u00f0\u00f8"+
		"\5\36\20\t\u00f1\u00f2\f\6\2\2\u00f2\u00f3\7\35\2\2\u00f3\u00f8\5\36\20"+
		"\7\u00f4\u00f5\f\5\2\2\u00f5\u00f6\7\34\2\2\u00f6\u00f8\5\36\20\6\u00f7"+
		"\u00d0\3\2\2\2\u00f7\u00d3\3\2\2\2\u00f7\u00d6\3\2\2\2\u00f7\u00d9\3\2"+
		"\2\2\u00f7\u00dc\3\2\2\2\u00f7\u00df\3\2\2\2\u00f7\u00e2\3\2\2\2\u00f7"+
		"\u00e5\3\2\2\2\u00f7\u00e8\3\2\2\2\u00f7\u00eb\3\2\2\2\u00f7\u00ee\3\2"+
		"\2\2\u00f7\u00f1\3\2\2\2\u00f7\u00f4\3\2\2\2\u00f8\u00fb\3\2\2\2\u00f9"+
		"\u00f7\3\2\2\2\u00f9\u00fa\3\2\2\2\u00fa\37\3\2\2\2\u00fb\u00f9\3\2\2"+
		"\2\u00fc\u0101\5\36\20\2\u00fd\u00fe\7\7\2\2\u00fe\u0100\5\36\20\2\u00ff"+
		"\u00fd\3\2\2\2\u0100\u0103\3\2\2\2\u0101\u00ff\3\2\2\2\u0101\u0102\3\2"+
		"\2\2\u0102\u0106\3\2\2\2\u0103\u0101\3\2\2\2\u0104\u0106\3\2\2\2\u0105"+
		"\u00fc\3\2\2\2\u0105\u0104\3\2\2\2\u0106!\3\2\2\2\u0107\u0109\5$\23\2"+
		"\u0108\u0107\3\2\2\2\u0109\u010a\3\2\2\2\u010a\u0108\3\2\2\2\u010a\u010b"+
		"\3\2\2\2\u010b#\3\2\2\2\u010c\u010d\7\24\2\2\u010d\u010e\5\36\20\2\u010e"+
		"\u010f\7\25\2\2\u010f%\3\2\2\2\u0110\u0112\5(\25\2\u0111\u0110\3\2\2\2"+
		"\u0112\u0113\3\2\2\2\u0113\u0111\3\2\2\2\u0113\u0114\3\2\2\2\u0114\'\3"+
		"\2\2\2\u0115\u0116\7\24\2\2\u0116\u0117\7#\2\2\u0117\u0118\7\25\2\2\u0118"+
		")\3\2\2\2\u0119\u011b\5,\27\2\u011a\u0119\3\2\2\2\u011b\u011c\3\2\2\2"+
		"\u011c\u011a\3\2\2\2\u011c\u011d\3\2\2\2\u011d+\3\2\2\2\u011e\u011f\7"+
		"\24\2\2\u011f\u0120\7\25\2\2\u0120-\3\2\2\2\26\61\65FV[dkz\u0080\u0094"+
		"\u00a4\u00ac\u00ce\u00f7\u00f9\u0101\u0105\u010a\u0113\u011c";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}