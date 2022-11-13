grammar DanglingElse;
import Lexer;

program     : statements;
statements  : statement+;
statement   : ifStatement | whileStatement | beginStatement | assignStatement;

ifStatement : IF cond THEN statement (ELSE statement)?;
cond        : ID EQ ID | IF GT ID | ID LT ID;

whileStatement  : WHILE ID DO statement;

beginStatement  : BEGIN statements END;

assignStatement : ID EQ ID;
