grammar MiniC;


@header { 
package generated;
}
program	: decl+			;
decl		: var_decl		
		| fun_decl		;
var_decl	:  type_spec IDENT ';' 
		| type_spec IDENT '=' LITERAL ';'	
		| type_spec IDENT arr_decl ';'	;
type_spec	: VOID				
		| INT				;
fun_decl	: type_spec IDENT '(' params ')' compound_stmt ;
params		: param (',' param)*		
		| VOID				
		|			;
param		: type_spec IDENT		
		| type_spec IDENT arr_parm	;
stmt		: expr_stmt			
		| compound_stmt			
		| if_stmt			
		| while_stmt			
		| return_stmt			;
expr_stmt	: expr ';'			;
while_stmt	: WHILE '(' expr ')' stmt	;
compound_stmt: '{' local_decl* stmt* '}'	;
local_decl	: type_spec IDENT ';'
		| type_spec IDENT '=' LITERAL ';'	
		| type_spec IDENT arr_decl ';'	;
if_stmt		: IF '(' expr ')' stmt		
		| IF '(' expr ')' stmt ELSE stmt 		;
return_stmt	: RETURN ';'			
		| RETURN expr ';'				;
expr	:  LITERAL				
	| '(' expr ')'				 
	| IDENT				 
	| IDENT arr
	| IDENT '(' args ')'			
	| '-' expr				 
	| '+' expr				 
	| '--' expr				 
	| '++' expr				 
	| expr '*' expr				 
	| expr '/' expr				 
	| expr '%' expr				 
	| expr '+' expr				 
	| expr '-' expr				 
	| expr EQ expr				
	| expr NE expr				 
	| expr LE expr				 
	| expr '<' expr				 
	| expr GE expr				 
	| expr '>' expr				 
	| '!' expr					 
	| expr AND expr				 
	| expr OR expr				
	| IDENT '=' expr			
	| IDENT arr_decl '=' expr		;
args	: expr (',' expr)*			 
	|					 ;

arr      : arr_stmt+;
arr_stmt : '[' expr ']';

arr_decl      : arr_decl_stmt+;
arr_decl_stmt : '[' LITERAL ']';

arr_parm      : arr_parm_stmt+;
arr_parm_stmt : '[' ']';


VOID: 'void';
INT: 'int';

WHILE: 'while';
IF: 'if';
ELSE: 'else';
RETURN: 'return';
OR: 'or';
    AND: 'and';
LE: '<=';
GE: '>=';
EQ: '==';
NE: '!=';

IDENT  : [a-zA-Z_]
        (   [a-zA-Z_]
        |  [0-9]
        )*;


LITERAL:   DecimalConstant     |   OctalConstant     |   HexadecimalConstant     ;


DecimalConstant
    :   '0'
	|   [1-9] [0-9]*
    ;

OctalConstant
    :   '0'[0-7]*
    ;

HexadecimalConstant
    :   '0' [xX] [0-9a-fA-F] +
    ;

WS  :   (   ' '
        |   '\t'
        |   '\r'
        |   '\n'
        )+
	-> channel(HIDDEN)	 
    ;
