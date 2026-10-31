%{
#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include "parser.tab.h"

extern FILE *scanner_out;
%}

ws [ \t\n]+
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
comma ","

at "@"
increment "++"
decrement "--"

plusequal "+="
minusequal "-="
starequal "*="
divisonequal "/="
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

{cluster} {
	fprintf(scanner_out, "<CLUSTER, %s>\n", yytext);
	yylval.sval = strdup(yytext);
	return CLUSTER_TOKEN;	
}

{class} {
	fprintf(scanner_out, "<CLASS, %s>\n", yytext);
	yylval.sval = strdup(yytext);
	return CLASS_TOKEN;
}
{method} {
	fprintf(scanner_out, "<METHOD, %s>\n", yytext);
	yylval.sval = strdup(yytext);
	return METHOD_TOKEN;
}

{if} {
	fprintf(scanner_out, "<IF, %s>\n", yytext);
	yylval.sval = strdup(yytext);
	return IF;
}

{else} {
	fprintf(scanner_out, "<ELSE, %s>\n", yytext);
	yylval.sval = strdup(yytext);
	return ELSE;
}

{while} {
	fprintf(scanner_out, "<WHILE, %s>\n", yytext);
	yylval.sval = strdup(yytext);
	return WHILE;
}

{integer} {
	fprintf(scanner_out, "<INTEGER, %s>\n", yytext);
	yylval.sval = strdup(yytext);
	return INT;
}

{float} {
	fprintf(scanner_out, "<FLOAT, %s>\n", yytext);
	yylval.sval = strdup(yytext);
	return FLOAT;
}

{integer_literal} {
	fprintf(scanner_out, "<INT_LITERAL, %s>\n", yytext);
	yylval.ival = atoi(yytext);
	return INTEGER_LITERAL;
}

{float_literal} {
	fprintf(scanner_out, "<FLOAT_LITERAL, %s>\n", yytext);
	yylval.fval = atof(yytext);
	return FLOAT_LITERAL;
}

{string_literal} {
	fprintf(scanner_out, "<STRING_LITERAL, %s>\n", yytext);
	yylval.sval = strdup(yytext);
	return STRING_LITERAL;
}

{id} {
        fprintf(scanner_out, "<IDENTIFIER, %s>\n", yytext);
        yylval.sval = strdup(yytext);
        return ID;
}

{semicolon} {
	fprintf(scanner_out, "<SEMICOLON, %s>\n", yytext);
	return ';';
}

{comma} {
	fprintf(scanner_out, "<COMMA, %s>\n", yytext);
	return ',';
}

{lparen} {
	fprintf(scanner_out, "<LPAREN, %s>\n", yytext);
	return '(';	
}

{rparen} {
	fprintf(scanner_out, "<RPAREN, %s>\n", yytext);
	return ')';
}

{lbrace} {
	fprintf(scanner_out, "<LBRACE, %s>\n", yytext);
	return '{';
}

{rbrace} {
	fprintf(scanner_out, "<RBRACE, %s>\n", yytext);
	return '}';
}

{at} {
	fprintf(scanner_out, "<AT, %s>\n", yytext);
	return '@';
}

{increment} {
	fprintf(scanner_out, "<INCREMENT, %s>\n", yytext);
	yylval.sval = strdup(yytext);
	return INCREMENT;
}

{decrement} {
	fprintf(scanner_out, "<DECREMENT, %s>\n", yytext);
	yylval.sval = strdup(yytext);
	return DECREMENT;
}

{plusequal} {
	fprintf(scanner_out, "<PLUS_EQUAL, %s>\n", yytext);
	yylval.sval = strdup(yytext);
	return PLUS_EQUAL;
}

{minusequal} {
	fprintf(scanner_out, "<MINUS_EQUAL, %s>\n", yytext);
	yylval.sval = strdup(yytext);
	return MINUS_EQUAL;	
}

{starequal} {
	fprintf(scanner_out, "<STAR_EQUAL, %s>\n", yytext);
	yylval.sval = strdup(yytext);
	return STAR_EQUAL;
}
{divisonequal} {
	fprintf(scanner_out, "<DIVISION_EQUAL, %s>\n", yytext);
	yylval.sval = strdup(yytext);
	return DIVISON_EQUAL;	
}
{moduloequal} {
	fprintf(scanner_out, "<MODULO_EQUAL, %s>\n", yytext);
	yylval.sval = strdup(yytext);
	return MODULO_EQUAL;
}

{equal} {
	fprintf(scanner_out, "<EQUAL, %s>\n", yytext);
	yylval.sval = strdup(yytext);
	return EQUAL;	
}

{lte} {
	fprintf(scanner_out, "<LTE, %s>\n", yytext);
	yylval.sval = strdup(yytext);
	return LTE;
}

{gte} {
	fprintf(scanner_out, "<GTE, %s>\n", yytext);
	yylval.sval = strdup(yytext);
        return GTE;
}

{ne} {
	fprintf(scanner_out, "<NE, %s>\n", yytext);
	yylval.sval = strdup(yytext);
        return NOT_EQUAL;
}

{lt} {
	fprintf(scanner_out, "<LT, %s>\n", yytext);
	return '<';
}

{gt} {
	fprintf(scanner_out, "<GT, %s>\n", yytext);
	return '>';
}

{and} {
	fprintf(scanner_out, "<AND, %s>\n", yytext);
	yylval.sval = strdup(yytext);
	return AND;
}

{or} {
	fprintf(scanner_out, "<OR, %s>\n", yytext);
	yylval.sval = strdup(yytext);
	return OR;
}

{not} {
	fprintf(scanner_out, "<NOT, %s>\n", yytext);
	yylval.sval = strdup(yytext);
	return NOT;
}

{assign} {
	fprintf(scanner_out, "<ASSIGN, %s>\n", yytext);
	return '=';
}

{plus} {
	fprintf(scanner_out, "<PLUS, %s>\n", yytext);
	return '+';
}
{minus} {
	fprintf(scanner_out, "<MINUS, %s>\n", yytext);
	return '-';	
}

{product} {
	fprintf(scanner_out, "<PRODUCT, %s>\n", yytext);
	return '*';
}

{quotient} {
	fprintf(scanner_out, "<QUOTIENT, %s>\n", yytext);
	return '/';	
}
{modulo} {
	fprintf(scanner_out, "<MODULO, %s>\n", yytext);
	return '%';
}

. {
	fprintf(scanner_out, "Lexical error.\n");
	return -1;
}

%%

int yywrap() {return 1;}
