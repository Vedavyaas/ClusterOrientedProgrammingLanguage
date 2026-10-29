%{
#include <stdio.h>
#include "parser.tab.h"

%}

ws [ \t\n]
comment_one_line "//".*
comment_multi_line "/*"([^*]|\*+[^*/])*\*+"/"

cluster "Cluster"
class "Class"
method "method"

if "if"
else "else"
while "while"

integer "int"
float "float"

id [a-zA-Z][a-zA-Z0-9]*
integer_literal [0-9][0-9]*
float_literal [0-9][0-9]*\.[0-9][0-9]*
string_literal \"[^\"]*\"

lparen "("
rparen ")"
lbrace "{"
rbrace "}"
semicolon ";"

at "@"
increment "++"
decrement "--"

plusequal "+="
minusequal "-="
starequal "*="
divequal "/="
moduloequal "%="

equal "=="
lte "<="
gte ">="
ne "!="
lt "<"
gt ">"
not "!"
and "&&"
or "||"

assign "="

plus "+"
minus "-"
product "*"  
quotient "/"
modulo "%"

%%

({ws}|{comment_one_line}|{comment_multi_line}) {}

{cluster} {fprintf(yyout, "<CLUSTER, %s>\n", yytext);}
{class} {fprintf(yyout, "<CLASS, %s>\n", yytext);}
{method} {fprintf(yyout, "<METHOD, %s>\n", yytext);}

{if} {fprintf(yyout, "<IF, %s>\n", yytext);}
{else} {fprintf(yyout, "<ELSE, %s>\n", yytext);}
{while} {fprintf(yyout, "<WHILE, %s>\n", yytext);}

{integer} {fprintf(yyout, "<INTEGER, %s>\n", yytext);}
{float} {fprintf(yyout, "<FLOAT, %s>\n", yytext);}

{id} {fprintf(yyout, "<IDENTIFIER, %s>\n", yytext);}
{integer_literal} {fprintf(yyout, "<INT_LITERAL, %s>\n", yytext);}
{float_literal} {fprintf(yyout, "<FLOAT_LITERAL, %s>\n", yytext);}
{string_literal} {fprintf(yyout, "<STRING_LITERAL, %s>\n", yytext);}

{semicolon} {fprintf(yyout, "<SEMICOLON, %s>\n", yytext);}
{lparen} {fprintf(yyout, "<LPAREN, %s>\n", yytext);}
{rparen} {fprintf(yyout, "<RPAREN, %s>\n", yytext);}
{lbrace} {fprintf(yyout, "<LBRACE, %s>\n", yytext);}
{rbrace} {fprintf(yyout, "<RBRACE, %s>\n", yytext);}

{at} {fprintf(yyout, "<AT, %s>\n", yytext);}
{increment} {fprintf(yyout, "<INCREMENT, %s>\n", yytext);}
{decrement} {fprintf(yyout, "<DECREMENT, %s>\n", yytext);}
{plusequal} {fprintf(yyout, "<PLUS_EQUAL, %s>\n", yytext);}
{minusequal} {fprintf(yyout, "<MINUS_EQUAL, %s>\n", yytext);}
{starequal} {fprintf(yyout, "<STAR_EQUAL, %s>\n", yytext);}
{divequal} {fprintf(yyout, "<DIVISION_EQUAL, %s>\n", yytext);}
{moduloequal} {fprintf(yyout, "<MODULO_EQUAL, %s>\n", yytext);}

{equal} {fprintf(yyout, "<EQUAL, %s>\n", yytext);}
{lte} {fprintf(yyout, "<LTE, %s>\n", yytext);}
{gte} {fprintf(yyout, "<GTE, %s>\n", yytext);}
{ne} {fprintf(yyout, "<NE, %s>\n", yytext);}
{lt} {fprintf(yyout, "<LT, %s>\n", yytext);}
{gt} {fprintf(yyout, "<GT, %s>\n", yytext);}
{and} {fprintf(yyout, "<AND, %s>\n", yytext);}
{or} {fprintf(yyout, "<OR, %s>\n", yytext);}
{not} {fprintf(yyout, "<NOT, %s>\n", yytext);}
{assign} {fprintf(yyout, "<ASSIGN, %s>\n", yytext);}

{plus} {fprintf(yyout, "<PLUS, %s>\n", yytext);}
{minus} {fprintf(yyout, "<MINUS, %s>\n", yytext);}
{product} {fprintf(yyout, "<PRODUCT, %s>\n", yytext);}
{quotient} {fprintf(yyout, "<QUOTIENT, %s>\n", yytext);}
{modulo} {fprintf(yyout, "<MODULO, %s>\n", yytext);}

. {
	fprintf(yyout, "Lexical error.\n");
	return -1;
}

%%

int yywrap() {return 1;}
