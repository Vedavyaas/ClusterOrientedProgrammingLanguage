%{
#include <stdio.h>
#include <string.h>
#include <stdarg.h>
#include <stdlib.h>

int yyerror(const char*);
int yylex(void);

extern FILE *yyin;
extern FILE *yyout;
FILE *scanner_out;

typedef struct temp {
	int counter;
} temp;

temp temporary = {0};

char* newTemp() {
	temporary.counter++;

	char *str = malloc(21);	
	sprintf(str, "T%d", temporary.counter);
	
	return str;
}

void gen(const char *format, ...) {
	va_list args;

	va_start(args, format);
	vfprintf(yyout, format, args);
	fprintf(yyout, "\n");
	va_end(args);
}

%}

%union {
	int ival;
	float fval;
	char *sval;
}

%token <ival> INTEGER_LITERAL
%token <fval> FLOAT_LITERAL
%token <sval> STRING_LITERAL

%token <sval> CLUSTER_TOKEN
%token <sval> CLASS_TOKEN
%token <sval> METHOD_TOKEN

%token <sval> IF
%token <sval> ELSE
%token <sval> WHILE

%token <sval> INT
%token <sval> FLOAT
%token <sval> ID

%token <sval> PLUS_EQUAL
%token <sval> MINUS_EQUAL
%token <sval> STAR_EQUAL
%token <sval> DIVISON_EQUAL
%token <sval> MODULO_EQUAL

%token <sval> INCREMENT
%token <sval> DECREMENT

%token <sval> EQUAL
%token <sval> NOT_EQUAL
%token <sval> LTE
%token <sval> GTE

%token <sval> AND
%token <sval> OR
%token <sval> NOT

%left '='
%left '+' '-'
%left '*' '/' '%'
%left NOT AND OR
%left INCREMENT DECREMENT
%right INCREMENT_PRE DECREMENT_PRE
%left INCREMENT_POST DECREMENT_POST

%type <sval> EXPR
%type <sval> LEXPR
%type <sval> PROGRAM
%type <sval> TYPE

%%

PROGRAM : LEXPR PROGRAM
        | LEXPR
        ;

LEXPR : ID '=' EXPR ';' {
	gen("%s = %s", $1, $3);
}

      | ID PLUS_EQUAL EXPR ';' {
	char *t = newTemp();
        gen("%s = %s + %s", t, $1, $3);
        gen("%s = %s", $1, t);
}

      | ID MINUS_EQUAL EXPR ';' {
	char *t = newTemp();
   	gen("%s = %s - %s", t, $1, $3);
    	gen("%s = %s", $1, t);
}

      | ID STAR_EQUAL EXPR ';' {
	char *t = newTemp();
    	gen("%s = %s * %s", t, $1, $3);
    	gen("%s = %s", $1, t);
}

      | ID DIVISON_EQUAL EXPR ';' {
	char *t = newTemp();
    	gen("%s = %s / %s", t, $1, $3);
    	gen("%s = %s", $1, t);
}   

      | ID MODULO_EQUAL EXPR ';' {
	char *t = newTemp();
    	gen("%s = %s %% %s", t, $1, $3);
    	gen("%s = %s", $1, t);
}

      | TYPE ID '=' EXPR ';' {
	gen("%s = %s", $2, $4);
}

      | EXPR ';'
      | TYPE IDLIST ';'
      ;

EXPR : EXPR '+' EXPR {
	$$ = newTemp();
	gen("%s = %s + %s", $$, $1, $3);
}
     | EXPR '-' EXPR {
 	$$ = newTemp();       
        gen("%s = %s - %s", $$, $1, $3);
}
     | EXPR '*' EXPR {
        $$ = newTemp();               
        gen("%s = %s * %s", $$, $1, $3);
}
     | EXPR '/' EXPR {
        $$ = newTemp();               
        gen("%s = %s / %s", $$, $1, $3);
}
     | EXPR '%' EXPR {
        $$ = newTemp();               
        gen("%s = %s %% %s", $$, $1, $3);
}

     | '-' EXPR {
	$$ = newTemp();
	gen("%s = -%s", $$, $2);
}

     | '(' EXPR ')' {
	$$ = $2;
}

     | EXPR INCREMENT %prec INCREMENT_POST {
	$$ = newTemp();
	gen("%s = %s + 1", $$, $1);
	gen("%s = %s", $1, $$);
}

     | EXPR DECREMENT %prec DECREMENT_POST {
	$$ = newTemp();
	gen("%s = %s - 1", $$, $1);
	gen("%s = %s", $1, $$);
}

     | INCREMENT EXPR %prec INCREMENT_PRE {
	$$ = newTemp();
	gen("%s = %s + 1", $$, $2);
	gen("%s = %s", $2, $$);
}

     | DECREMENT EXPR %prec DECREMENT_PRE {
	$$ = newTemp();
	gen("%s = %s - 1", $$, $2);
	gen("%s = %s", $2, $$);
}

     | ID {
	$$ = strdup($1);
}

     | INTEGER_LITERAL {
	char buffer[32];
        snprintf(buffer, sizeof(buffer), "%d", $1);
        $$ = strdup(buffer);
}

     | FLOAT_LITERAL {
	char buffer[32];
        snprintf(buffer, sizeof(buffer), "%f", $1);
        $$ = strdup(buffer);
}	
     ;

IDLIST : ID
       | IDLIST ',' ID
       ;

TYPE : INT
     | FLOAT
     ;



%%



int yyerror(const char *msg) {
	printf("Error : %s", msg);
	return 0;
}

int main() {
	yyin = fopen("input.txt", "r");
	scanner_out = fopen("scanner.txt", "w");	
	yyout = fopen("output.txt", "w");

	yyparse();
	return 0;
}
