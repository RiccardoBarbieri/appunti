// Generated from java-escape by ANTLR 4.11.1
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue"})
public class DanglingElseParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.11.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		ID=1, WS=2, IF=3, THEN=4, ELSE=5, BEGIN=6, END=7, WHILE=8, DO=9, EQ=10, 
		GT=11, LT=12, INT=13, SHOW_CALL=14, CLEAR_CALL=15, NEWLINE=16;
	public static final int
		RULE_program = 0, RULE_statements = 1, RULE_statement = 2, RULE_ifStatement = 3, 
		RULE_cond = 4, RULE_whileStatement = 5, RULE_beginStatement = 6, RULE_assignStatement = 7;
	private static String[] makeRuleNames() {
		return new String[] {
			"program", "statements", "statement", "ifStatement", "cond", "whileStatement", 
			"beginStatement", "assignStatement"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, null, null, "'if'", "'then'", "'else'", "'begin'", "'end'", "'while'", 
			"'do'", "'='", "'>'", "'<'", null, "'show()'", "'clear()'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "ID", "WS", "IF", "THEN", "ELSE", "BEGIN", "END", "WHILE", "DO", 
			"EQ", "GT", "LT", "INT", "SHOW_CALL", "CLEAR_CALL", "NEWLINE"
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
	public String getGrammarFileName() { return "java-escape"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public DanglingElseParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ProgramContext extends ParserRuleContext {
		public StatementsContext statements() {
			return getRuleContext(StatementsContext.class,0);
		}
		public ProgramContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_program; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DanglingElseListener ) ((DanglingElseListener)listener).enterProgram(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DanglingElseListener ) ((DanglingElseListener)listener).exitProgram(this);
		}
	}

	public final ProgramContext program() throws RecognitionException {
		ProgramContext _localctx = new ProgramContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_program);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(16);
			statements();
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

	@SuppressWarnings("CheckReturnValue")
	public static class StatementsContext extends ParserRuleContext {
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public StatementsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statements; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DanglingElseListener ) ((DanglingElseListener)listener).enterStatements(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DanglingElseListener ) ((DanglingElseListener)listener).exitStatements(this);
		}
	}

	public final StatementsContext statements() throws RecognitionException {
		StatementsContext _localctx = new StatementsContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_statements);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(19); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(18);
				statement();
				}
				}
				setState(21); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( ((_la) & ~0x3f) == 0 && ((1L << _la) & 330L) != 0 );
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

	@SuppressWarnings("CheckReturnValue")
	public static class StatementContext extends ParserRuleContext {
		public IfStatementContext ifStatement() {
			return getRuleContext(IfStatementContext.class,0);
		}
		public WhileStatementContext whileStatement() {
			return getRuleContext(WhileStatementContext.class,0);
		}
		public BeginStatementContext beginStatement() {
			return getRuleContext(BeginStatementContext.class,0);
		}
		public AssignStatementContext assignStatement() {
			return getRuleContext(AssignStatementContext.class,0);
		}
		public StatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DanglingElseListener ) ((DanglingElseListener)listener).enterStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DanglingElseListener ) ((DanglingElseListener)listener).exitStatement(this);
		}
	}

	public final StatementContext statement() throws RecognitionException {
		StatementContext _localctx = new StatementContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_statement);
		try {
			setState(27);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case IF:
				enterOuterAlt(_localctx, 1);
				{
				setState(23);
				ifStatement();
				}
				break;
			case WHILE:
				enterOuterAlt(_localctx, 2);
				{
				setState(24);
				whileStatement();
				}
				break;
			case BEGIN:
				enterOuterAlt(_localctx, 3);
				{
				setState(25);
				beginStatement();
				}
				break;
			case ID:
				enterOuterAlt(_localctx, 4);
				{
				setState(26);
				assignStatement();
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

	@SuppressWarnings("CheckReturnValue")
	public static class IfStatementContext extends ParserRuleContext {
		public TerminalNode IF() { return getToken(DanglingElseParser.IF, 0); }
		public CondContext cond() {
			return getRuleContext(CondContext.class,0);
		}
		public TerminalNode THEN() { return getToken(DanglingElseParser.THEN, 0); }
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public TerminalNode ELSE() { return getToken(DanglingElseParser.ELSE, 0); }
		public IfStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ifStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DanglingElseListener ) ((DanglingElseListener)listener).enterIfStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DanglingElseListener ) ((DanglingElseListener)listener).exitIfStatement(this);
		}
	}

	public final IfStatementContext ifStatement() throws RecognitionException {
		IfStatementContext _localctx = new IfStatementContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_ifStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(29);
			match(IF);
			setState(30);
			cond();
			setState(31);
			match(THEN);
			setState(32);
			statement();
			setState(35);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,2,_ctx) ) {
			case 1:
				{
				setState(33);
				match(ELSE);
				setState(34);
				statement();
				}
				break;
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

	@SuppressWarnings("CheckReturnValue")
	public static class CondContext extends ParserRuleContext {
		public List<TerminalNode> ID() { return getTokens(DanglingElseParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(DanglingElseParser.ID, i);
		}
		public TerminalNode EQ() { return getToken(DanglingElseParser.EQ, 0); }
		public TerminalNode IF() { return getToken(DanglingElseParser.IF, 0); }
		public TerminalNode GT() { return getToken(DanglingElseParser.GT, 0); }
		public TerminalNode LT() { return getToken(DanglingElseParser.LT, 0); }
		public CondContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cond; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DanglingElseListener ) ((DanglingElseListener)listener).enterCond(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DanglingElseListener ) ((DanglingElseListener)listener).exitCond(this);
		}
	}

	public final CondContext cond() throws RecognitionException {
		CondContext _localctx = new CondContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_cond);
		try {
			setState(46);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(37);
				match(ID);
				setState(38);
				match(EQ);
				setState(39);
				match(ID);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(40);
				match(IF);
				setState(41);
				match(GT);
				setState(42);
				match(ID);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(43);
				match(ID);
				setState(44);
				match(LT);
				setState(45);
				match(ID);
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

	@SuppressWarnings("CheckReturnValue")
	public static class WhileStatementContext extends ParserRuleContext {
		public TerminalNode WHILE() { return getToken(DanglingElseParser.WHILE, 0); }
		public TerminalNode ID() { return getToken(DanglingElseParser.ID, 0); }
		public TerminalNode DO() { return getToken(DanglingElseParser.DO, 0); }
		public StatementContext statement() {
			return getRuleContext(StatementContext.class,0);
		}
		public WhileStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_whileStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DanglingElseListener ) ((DanglingElseListener)listener).enterWhileStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DanglingElseListener ) ((DanglingElseListener)listener).exitWhileStatement(this);
		}
	}

	public final WhileStatementContext whileStatement() throws RecognitionException {
		WhileStatementContext _localctx = new WhileStatementContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_whileStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(48);
			match(WHILE);
			setState(49);
			match(ID);
			setState(50);
			match(DO);
			setState(51);
			statement();
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

	@SuppressWarnings("CheckReturnValue")
	public static class BeginStatementContext extends ParserRuleContext {
		public TerminalNode BEGIN() { return getToken(DanglingElseParser.BEGIN, 0); }
		public StatementsContext statements() {
			return getRuleContext(StatementsContext.class,0);
		}
		public TerminalNode END() { return getToken(DanglingElseParser.END, 0); }
		public BeginStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_beginStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DanglingElseListener ) ((DanglingElseListener)listener).enterBeginStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DanglingElseListener ) ((DanglingElseListener)listener).exitBeginStatement(this);
		}
	}

	public final BeginStatementContext beginStatement() throws RecognitionException {
		BeginStatementContext _localctx = new BeginStatementContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_beginStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(53);
			match(BEGIN);
			setState(54);
			statements();
			setState(55);
			match(END);
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

	@SuppressWarnings("CheckReturnValue")
	public static class AssignStatementContext extends ParserRuleContext {
		public List<TerminalNode> ID() { return getTokens(DanglingElseParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(DanglingElseParser.ID, i);
		}
		public TerminalNode EQ() { return getToken(DanglingElseParser.EQ, 0); }
		public AssignStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_assignStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DanglingElseListener ) ((DanglingElseListener)listener).enterAssignStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DanglingElseListener ) ((DanglingElseListener)listener).exitAssignStatement(this);
		}
	}

	public final AssignStatementContext assignStatement() throws RecognitionException {
		AssignStatementContext _localctx = new AssignStatementContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_assignStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(57);
			match(ID);
			setState(58);
			match(EQ);
			setState(59);
			match(ID);
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

	public static final String _serializedATN =
		"\u0004\u0001\u0010>\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002"+
		"\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007\u0001"+
		"\u0000\u0001\u0000\u0001\u0001\u0004\u0001\u0014\b\u0001\u000b\u0001\f"+
		"\u0001\u0015\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0003\u0002"+
		"\u001c\b\u0002\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003"+
		"\u0001\u0003\u0003\u0003$\b\u0003\u0001\u0004\u0001\u0004\u0001\u0004"+
		"\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004"+
		"\u0003\u0004/\b\u0004\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005"+
		"\u0001\u0005\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0007"+
		"\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0000\u0000\b\u0000\u0002"+
		"\u0004\u0006\b\n\f\u000e\u0000\u0000<\u0000\u0010\u0001\u0000\u0000\u0000"+
		"\u0002\u0013\u0001\u0000\u0000\u0000\u0004\u001b\u0001\u0000\u0000\u0000"+
		"\u0006\u001d\u0001\u0000\u0000\u0000\b.\u0001\u0000\u0000\u0000\n0\u0001"+
		"\u0000\u0000\u0000\f5\u0001\u0000\u0000\u0000\u000e9\u0001\u0000\u0000"+
		"\u0000\u0010\u0011\u0003\u0002\u0001\u0000\u0011\u0001\u0001\u0000\u0000"+
		"\u0000\u0012\u0014\u0003\u0004\u0002\u0000\u0013\u0012\u0001\u0000\u0000"+
		"\u0000\u0014\u0015\u0001\u0000\u0000\u0000\u0015\u0013\u0001\u0000\u0000"+
		"\u0000\u0015\u0016\u0001\u0000\u0000\u0000\u0016\u0003\u0001\u0000\u0000"+
		"\u0000\u0017\u001c\u0003\u0006\u0003\u0000\u0018\u001c\u0003\n\u0005\u0000"+
		"\u0019\u001c\u0003\f\u0006\u0000\u001a\u001c\u0003\u000e\u0007\u0000\u001b"+
		"\u0017\u0001\u0000\u0000\u0000\u001b\u0018\u0001\u0000\u0000\u0000\u001b"+
		"\u0019\u0001\u0000\u0000\u0000\u001b\u001a\u0001\u0000\u0000\u0000\u001c"+
		"\u0005\u0001\u0000\u0000\u0000\u001d\u001e\u0005\u0003\u0000\u0000\u001e"+
		"\u001f\u0003\b\u0004\u0000\u001f \u0005\u0004\u0000\u0000 #\u0003\u0004"+
		"\u0002\u0000!\"\u0005\u0005\u0000\u0000\"$\u0003\u0004\u0002\u0000#!\u0001"+
		"\u0000\u0000\u0000#$\u0001\u0000\u0000\u0000$\u0007\u0001\u0000\u0000"+
		"\u0000%&\u0005\u0001\u0000\u0000&\'\u0005\n\u0000\u0000\'/\u0005\u0001"+
		"\u0000\u0000()\u0005\u0003\u0000\u0000)*\u0005\u000b\u0000\u0000*/\u0005"+
		"\u0001\u0000\u0000+,\u0005\u0001\u0000\u0000,-\u0005\f\u0000\u0000-/\u0005"+
		"\u0001\u0000\u0000.%\u0001\u0000\u0000\u0000.(\u0001\u0000\u0000\u0000"+
		".+\u0001\u0000\u0000\u0000/\t\u0001\u0000\u0000\u000001\u0005\b\u0000"+
		"\u000012\u0005\u0001\u0000\u000023\u0005\t\u0000\u000034\u0003\u0004\u0002"+
		"\u00004\u000b\u0001\u0000\u0000\u000056\u0005\u0006\u0000\u000067\u0003"+
		"\u0002\u0001\u000078\u0005\u0007\u0000\u00008\r\u0001\u0000\u0000\u0000"+
		"9:\u0005\u0001\u0000\u0000:;\u0005\n\u0000\u0000;<\u0005\u0001\u0000\u0000"+
		"<\u000f\u0001\u0000\u0000\u0000\u0004\u0015\u001b#.";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}