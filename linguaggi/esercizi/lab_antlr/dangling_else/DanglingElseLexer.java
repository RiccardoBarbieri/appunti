// Generated from java-escape by ANTLR 4.11.1
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue"})
public class DanglingElseLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.11.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		ID=1, WS=2, IF=3, THEN=4, ELSE=5, BEGIN=6, END=7, WHILE=8, DO=9, EQ=10, 
		GT=11, LT=12, INT=13, SHOW_CALL=14, CLEAR_CALL=15, NEWLINE=16;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"ID", "WS", "IF", "THEN", "ELSE", "BEGIN", "END", "WHILE", "DO", "EQ", 
			"GT", "LT", "INT", "SHOW_CALL", "CLEAR_CALL", "NEWLINE"
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


	public DanglingElseLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "DanglingElse.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\u0004\u0000\u0010l\u0006\uffff\uffff\u0002\u0000\u0007\u0000\u0002\u0001"+
		"\u0007\u0001\u0002\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004"+
		"\u0007\u0004\u0002\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007"+
		"\u0007\u0007\u0002\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b"+
		"\u0007\u000b\u0002\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0002"+
		"\u000f\u0007\u000f\u0001\u0000\u0004\u0000#\b\u0000\u000b\u0000\f\u0000"+
		"$\u0001\u0001\u0004\u0001(\b\u0001\u000b\u0001\f\u0001)\u0001\u0001\u0001"+
		"\u0001\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0003\u0001\u0003\u0001"+
		"\u0003\u0001\u0003\u0001\u0003\u0001\u0004\u0001\u0004\u0001\u0004\u0001"+
		"\u0004\u0001\u0004\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001"+
		"\u0005\u0001\u0005\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001"+
		"\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001"+
		"\b\u0001\b\u0001\b\u0001\t\u0001\t\u0001\n\u0001\n\u0001\u000b\u0001\u000b"+
		"\u0001\f\u0004\fU\b\f\u000b\f\f\fV\u0001\r\u0001\r\u0001\r\u0001\r\u0001"+
		"\r\u0001\r\u0001\r\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001"+
		"\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000f\u0003\u000fi\b"+
		"\u000f\u0001\u000f\u0001\u000f\u0000\u0000\u0010\u0001\u0001\u0003\u0002"+
		"\u0005\u0003\u0007\u0004\t\u0005\u000b\u0006\r\u0007\u000f\b\u0011\t\u0013"+
		"\n\u0015\u000b\u0017\f\u0019\r\u001b\u000e\u001d\u000f\u001f\u0010\u0001"+
		"\u0000\u0003\u0001\u0000az\u0003\u0000\t\n\r\r  \u0001\u000009o\u0000"+
		"\u0001\u0001\u0000\u0000\u0000\u0000\u0003\u0001\u0000\u0000\u0000\u0000"+
		"\u0005\u0001\u0000\u0000\u0000\u0000\u0007\u0001\u0000\u0000\u0000\u0000"+
		"\t\u0001\u0000\u0000\u0000\u0000\u000b\u0001\u0000\u0000\u0000\u0000\r"+
		"\u0001\u0000\u0000\u0000\u0000\u000f\u0001\u0000\u0000\u0000\u0000\u0011"+
		"\u0001\u0000\u0000\u0000\u0000\u0013\u0001\u0000\u0000\u0000\u0000\u0015"+
		"\u0001\u0000\u0000\u0000\u0000\u0017\u0001\u0000\u0000\u0000\u0000\u0019"+
		"\u0001\u0000\u0000\u0000\u0000\u001b\u0001\u0000\u0000\u0000\u0000\u001d"+
		"\u0001\u0000\u0000\u0000\u0000\u001f\u0001\u0000\u0000\u0000\u0001\"\u0001"+
		"\u0000\u0000\u0000\u0003\'\u0001\u0000\u0000\u0000\u0005-\u0001\u0000"+
		"\u0000\u0000\u00070\u0001\u0000\u0000\u0000\t5\u0001\u0000\u0000\u0000"+
		"\u000b:\u0001\u0000\u0000\u0000\r@\u0001\u0000\u0000\u0000\u000fD\u0001"+
		"\u0000\u0000\u0000\u0011J\u0001\u0000\u0000\u0000\u0013M\u0001\u0000\u0000"+
		"\u0000\u0015O\u0001\u0000\u0000\u0000\u0017Q\u0001\u0000\u0000\u0000\u0019"+
		"T\u0001\u0000\u0000\u0000\u001bX\u0001\u0000\u0000\u0000\u001d_\u0001"+
		"\u0000\u0000\u0000\u001fh\u0001\u0000\u0000\u0000!#\u0007\u0000\u0000"+
		"\u0000\"!\u0001\u0000\u0000\u0000#$\u0001\u0000\u0000\u0000$\"\u0001\u0000"+
		"\u0000\u0000$%\u0001\u0000\u0000\u0000%\u0002\u0001\u0000\u0000\u0000"+
		"&(\u0007\u0001\u0000\u0000\'&\u0001\u0000\u0000\u0000()\u0001\u0000\u0000"+
		"\u0000)\'\u0001\u0000\u0000\u0000)*\u0001\u0000\u0000\u0000*+\u0001\u0000"+
		"\u0000\u0000+,\u0006\u0001\u0000\u0000,\u0004\u0001\u0000\u0000\u0000"+
		"-.\u0005i\u0000\u0000./\u0005f\u0000\u0000/\u0006\u0001\u0000\u0000\u0000"+
		"01\u0005t\u0000\u000012\u0005h\u0000\u000023\u0005e\u0000\u000034\u0005"+
		"n\u0000\u00004\b\u0001\u0000\u0000\u000056\u0005e\u0000\u000067\u0005"+
		"l\u0000\u000078\u0005s\u0000\u000089\u0005e\u0000\u00009\n\u0001\u0000"+
		"\u0000\u0000:;\u0005b\u0000\u0000;<\u0005e\u0000\u0000<=\u0005g\u0000"+
		"\u0000=>\u0005i\u0000\u0000>?\u0005n\u0000\u0000?\f\u0001\u0000\u0000"+
		"\u0000@A\u0005e\u0000\u0000AB\u0005n\u0000\u0000BC\u0005d\u0000\u0000"+
		"C\u000e\u0001\u0000\u0000\u0000DE\u0005w\u0000\u0000EF\u0005h\u0000\u0000"+
		"FG\u0005i\u0000\u0000GH\u0005l\u0000\u0000HI\u0005e\u0000\u0000I\u0010"+
		"\u0001\u0000\u0000\u0000JK\u0005d\u0000\u0000KL\u0005o\u0000\u0000L\u0012"+
		"\u0001\u0000\u0000\u0000MN\u0005=\u0000\u0000N\u0014\u0001\u0000\u0000"+
		"\u0000OP\u0005>\u0000\u0000P\u0016\u0001\u0000\u0000\u0000QR\u0005<\u0000"+
		"\u0000R\u0018\u0001\u0000\u0000\u0000SU\u0007\u0002\u0000\u0000TS\u0001"+
		"\u0000\u0000\u0000UV\u0001\u0000\u0000\u0000VT\u0001\u0000\u0000\u0000"+
		"VW\u0001\u0000\u0000\u0000W\u001a\u0001\u0000\u0000\u0000XY\u0005s\u0000"+
		"\u0000YZ\u0005h\u0000\u0000Z[\u0005o\u0000\u0000[\\\u0005w\u0000\u0000"+
		"\\]\u0005(\u0000\u0000]^\u0005)\u0000\u0000^\u001c\u0001\u0000\u0000\u0000"+
		"_`\u0005c\u0000\u0000`a\u0005l\u0000\u0000ab\u0005e\u0000\u0000bc\u0005"+
		"a\u0000\u0000cd\u0005r\u0000\u0000de\u0005(\u0000\u0000ef\u0005)\u0000"+
		"\u0000f\u001e\u0001\u0000\u0000\u0000gi\u0005\r\u0000\u0000hg\u0001\u0000"+
		"\u0000\u0000hi\u0001\u0000\u0000\u0000ij\u0001\u0000\u0000\u0000jk\u0005"+
		"\n\u0000\u0000k \u0001\u0000\u0000\u0000\u0005\u0000$)Vh\u0001\u0006\u0000"+
		"\u0000";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}