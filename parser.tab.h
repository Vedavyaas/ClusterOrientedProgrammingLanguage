/* A Bison parser, made by GNU Bison 2.3.  */

/* Skeleton interface for Bison's Yacc-like parsers in C

   Copyright (C) 1984, 1989, 1990, 2000, 2001, 2002, 2003, 2004, 2005, 2006
   Free Software Foundation, Inc.

   This program is free software; you can redistribute it and/or modify
   it under the terms of the GNU General Public License as published by
   the Free Software Foundation; either version 2, or (at your option)
   any later version.

   This program is distributed in the hope that it will be useful,
   but WITHOUT ANY WARRANTY; without even the implied warranty of
   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
   GNU General Public License for more details.

   You should have received a copy of the GNU General Public License
   along with this program; if not, write to the Free Software
   Foundation, Inc., 51 Franklin Street, Fifth Floor,
   Boston, MA 02110-1301, USA.  */

/* As a special exception, you may create a larger work that contains
   part or all of the Bison parser skeleton and distribute that work
   under terms of your choice, so long as that work isn't itself a
   parser generator using the skeleton or a modified version thereof
   as a parser skeleton.  Alternatively, if you modify or redistribute
   the parser skeleton itself, you may (at your option) remove this
   special exception, which will cause the skeleton and the resulting
   Bison output files to be licensed under the GNU General Public
   License without this special exception.

   This special exception was added by the Free Software Foundation in
   version 2.2 of Bison.  */

/* Tokens.  */
#ifndef YYTOKENTYPE
# define YYTOKENTYPE
   /* Put the tokens into the symbol table, so that GDB and other debuggers
      know about them.  */
   enum yytokentype {
     INTEGER_LITERAL = 258,
     FLOAT_LITERAL = 259,
     STRING_LITERAL = 260,
     CLUSTER_TOKEN = 261,
     CLASS_TOKEN = 262,
     METHOD_TOKEN = 263,
     IF = 264,
     ELSE = 265,
     WHILE = 266,
     INT = 267,
     FLOAT = 268,
     ID = 269,
     PLUS_EQUAL = 270,
     MINUS_EQUAL = 271,
     STAR_EQUAL = 272,
     DIVISON_EQUAL = 273,
     MODULO_EQUAL = 274,
     INCREMENT = 275,
     DECREMENT = 276,
     EQUAL = 277,
     NOT_EQUAL = 278,
     LTE = 279,
     GTE = 280,
     AND = 281,
     OR = 282,
     NOT = 283,
     DECREMENT_PRE = 284,
     INCREMENT_PRE = 285,
     DECREMENT_POST = 286,
     INCREMENT_POST = 287
   };
#endif
/* Tokens.  */
#define INTEGER_LITERAL 258
#define FLOAT_LITERAL 259
#define STRING_LITERAL 260
#define CLUSTER_TOKEN 261
#define CLASS_TOKEN 262
#define METHOD_TOKEN 263
#define IF 264
#define ELSE 265
#define WHILE 266
#define INT 267
#define FLOAT 268
#define ID 269
#define PLUS_EQUAL 270
#define MINUS_EQUAL 271
#define STAR_EQUAL 272
#define DIVISON_EQUAL 273
#define MODULO_EQUAL 274
#define INCREMENT 275
#define DECREMENT 276
#define EQUAL 277
#define NOT_EQUAL 278
#define LTE 279
#define GTE 280
#define AND 281
#define OR 282
#define NOT 283
#define DECREMENT_PRE 284
#define INCREMENT_PRE 285
#define DECREMENT_POST 286
#define INCREMENT_POST 287




#if ! defined YYSTYPE && ! defined YYSTYPE_IS_DECLARED
typedef union YYSTYPE
#line 40 "parser.y"
{
	int ival;
	float fval;
	char *sval;
}
/* Line 1529 of yacc.c.  */
#line 119 "parser.tab.h"
	YYSTYPE;
# define yystype YYSTYPE /* obsolescent; will be withdrawn */
# define YYSTYPE_IS_DECLARED 1
# define YYSTYPE_IS_TRIVIAL 1
#endif

extern YYSTYPE yylval;

