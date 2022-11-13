// Generated from java-escape by ANTLR 4.11.1
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link DanglingElseParser}.
 */
public interface DanglingElseListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link DanglingElseParser#program}.
	 * @param ctx the parse tree
	 */
	void enterProgram(DanglingElseParser.ProgramContext ctx);
	/**
	 * Exit a parse tree produced by {@link DanglingElseParser#program}.
	 * @param ctx the parse tree
	 */
	void exitProgram(DanglingElseParser.ProgramContext ctx);
	/**
	 * Enter a parse tree produced by {@link DanglingElseParser#statements}.
	 * @param ctx the parse tree
	 */
	void enterStatements(DanglingElseParser.StatementsContext ctx);
	/**
	 * Exit a parse tree produced by {@link DanglingElseParser#statements}.
	 * @param ctx the parse tree
	 */
	void exitStatements(DanglingElseParser.StatementsContext ctx);
	/**
	 * Enter a parse tree produced by {@link DanglingElseParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStatement(DanglingElseParser.StatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link DanglingElseParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStatement(DanglingElseParser.StatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link DanglingElseParser#ifStatement}.
	 * @param ctx the parse tree
	 */
	void enterIfStatement(DanglingElseParser.IfStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link DanglingElseParser#ifStatement}.
	 * @param ctx the parse tree
	 */
	void exitIfStatement(DanglingElseParser.IfStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link DanglingElseParser#cond}.
	 * @param ctx the parse tree
	 */
	void enterCond(DanglingElseParser.CondContext ctx);
	/**
	 * Exit a parse tree produced by {@link DanglingElseParser#cond}.
	 * @param ctx the parse tree
	 */
	void exitCond(DanglingElseParser.CondContext ctx);
	/**
	 * Enter a parse tree produced by {@link DanglingElseParser#whileStatement}.
	 * @param ctx the parse tree
	 */
	void enterWhileStatement(DanglingElseParser.WhileStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link DanglingElseParser#whileStatement}.
	 * @param ctx the parse tree
	 */
	void exitWhileStatement(DanglingElseParser.WhileStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link DanglingElseParser#beginStatement}.
	 * @param ctx the parse tree
	 */
	void enterBeginStatement(DanglingElseParser.BeginStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link DanglingElseParser#beginStatement}.
	 * @param ctx the parse tree
	 */
	void exitBeginStatement(DanglingElseParser.BeginStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link DanglingElseParser#assignStatement}.
	 * @param ctx the parse tree
	 */
	void enterAssignStatement(DanglingElseParser.AssignStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link DanglingElseParser#assignStatement}.
	 * @param ctx the parse tree
	 */
	void exitAssignStatement(DanglingElseParser.AssignStatementContext ctx);
}