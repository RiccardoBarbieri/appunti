grammar Lexer;

ID  : [a-z]+;
WS  : [ \t\r\n]+ -> skip;

IF      : 'if';     THEN    : 'then';
ELSE    : 'else';   BEGIN   : 'begin';
END     : 'end';    WHILE   : 'while';
DO      : 'do';     EQ      : '=';
GT      :'>';       LT      : '<';

INT         : [0-9]+;
SHOW_CALL   : 'show()';
CLEAR_CALL  : 'clear()';
NEWLINE     : '\r'?'\n';
