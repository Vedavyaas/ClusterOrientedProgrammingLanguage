%{
#include <stdio.h>

int yyerror(const char*);
int yylex(void);

extern FILE *yyin;
extern FILE *yyout;

%}

%union {
	int ival;
	float fval;
	char *sval;
}

%token <ival> INTEGER_LITERAL
%token <fval> FLOAT_LITERAL
%token <sval> STRING_LITERAL

%token INT
%token FLOAT
%token ID

%token INCREMENT
%token DECREMENT

%token EQUALITY
%token NOT_EQUAL
%token LTE
%token GTE

%left '+' '-'
%left '*' '/' '%'
%left "||"
%left "&&"
%left '!'
%right INCREMENT DECREMENT


%%

P : {fprintf(yyout, "Null. \n");}
  ;

%%

int yyerror(const char *msg) {
	printf("Error : %s", msg);
	return 0;
}

int main() {
	yyin = fopen("input.txt", "r");
	yyout = fopen("output.txt", "w");

	yyparse();
	return 0;
}
