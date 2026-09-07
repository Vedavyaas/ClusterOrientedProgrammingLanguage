package parser;

import scanner.Token;
import scanner.TokenType;

import java.util.List;
import java.util.Stack;

public class ParserConstruction {
    PredictiveParsingTable predictiveParsingTable = new PredictiveParsingTable();

    public void addProduction() {

        // PROGRAM
        predictiveParsingTable.add(TableRowHeader.PROGRAM, TokenType.AT,
                "ANNOTATION CLASS_DECL PROGRAM");
        predictiveParsingTable.add(TableRowHeader.PROGRAM, TokenType.EOF,
                "E");

        // ANNOTATION
        predictiveParsingTable.add(TableRowHeader.ANNOTATION, TokenType.AT,
                "AT IDENTIFIER LPAREN STRING_LITERAL RPAREN");

        // CLASS
        predictiveParsingTable.add(TableRowHeader.CLASS_DECL, TokenType.CLASS,
                "CLASS IDENTIFIER LBRACE CLASS_BODY RBRACE");

        // CLASS_BODY
        predictiveParsingTable.add(TableRowHeader.CLASS_BODY, TokenType.METHOD,
                "METHOD_DECL CLASS_BODY");
        predictiveParsingTable.add(TableRowHeader.CLASS_BODY, TokenType.EQUAL,
                "INITIALIZATION CLASS_BODY");
        predictiveParsingTable.add(TableRowHeader.CLASS_BODY, TokenType.INT,
                "DECLARATION CLASS_BODY");
        predictiveParsingTable.add(TableRowHeader.CLASS_BODY, TokenType.FLOAT,
                "DECLARATION CLASS_BODY");
        predictiveParsingTable.add(TableRowHeader.CLASS_BODY, TokenType.STRING,
                "DECLARATION CLASS_BODY");
        predictiveParsingTable.add(TableRowHeader.CLASS_BODY, TokenType.IDENTIFIER,
                "EXPRESSION CLASS_BODY");
        predictiveParsingTable.add(TableRowHeader.CLASS_BODY, TokenType.FOR,
                "LOOPING_STATEMENT CLASS_BODY");
        predictiveParsingTable.add(TableRowHeader.CLASS_BODY, TokenType.WHILE,
                "LOOPING_STATEMENT CLASS_BODY");
        predictiveParsingTable.add(TableRowHeader.CLASS_BODY, TokenType.IF,
                "CONDITIONAL_STATEMENT CLASS_BODY");
        predictiveParsingTable.add(TableRowHeader.CLASS_BODY, TokenType.RBRACE,
                "E");

        // METHOD
        predictiveParsingTable.add(TableRowHeader.METHOD_DECL, TokenType.METHOD,
                "METHOD IDENTIFIER LBRACE METHOD_BODY RBRACE");

        // METHOD_BODY
        predictiveParsingTable.add(TableRowHeader.METHOD_BODY, TokenType.EQUAL,
                "INITIALIZATION METHOD_BODY");
        predictiveParsingTable.add(TableRowHeader.METHOD_BODY, TokenType.INT,
                "DECLARATION METHOD_BODY");
        predictiveParsingTable.add(TableRowHeader.METHOD_BODY, TokenType.FLOAT,
                "DECLARATION METHOD_BODY");
        predictiveParsingTable.add(TableRowHeader.METHOD_BODY, TokenType.STRING,
                "DECLARATION METHOD_BODY");
        predictiveParsingTable.add(TableRowHeader.METHOD_BODY, TokenType.IDENTIFIER,
                "EXPRESSION METHOD_BODY");
        predictiveParsingTable.add(TableRowHeader.METHOD_BODY, TokenType.FOR,
                "LOOPING_STATEMENT METHOD_BODY");
        predictiveParsingTable.add(TableRowHeader.METHOD_BODY, TokenType.WHILE,
                "LOOPING_STATEMENT METHOD_BODY");
        predictiveParsingTable.add(TableRowHeader.METHOD_BODY, TokenType.IF,
                "CONDITIONAL_STATEMENT METHOD_BODY");
        predictiveParsingTable.add(TableRowHeader.METHOD_BODY, TokenType.RBRACE,
                "E");

        // DECLARATION
        predictiveParsingTable.add(TableRowHeader.DECLARATION, TokenType.INT,
                "TYPE IDENTIFIER INITIALIZATION SEMICOLON");
        predictiveParsingTable.add(TableRowHeader.DECLARATION, TokenType.FLOAT,
                "TYPE IDENTIFIER INITIALIZATION SEMICOLON");
        predictiveParsingTable.add(TableRowHeader.DECLARATION, TokenType.STRING,
                "TYPE IDENTIFIER INITIALIZATION SEMICOLON");

        // INITIALIZATION
        predictiveParsingTable.add(TableRowHeader.INITIALIZATION, TokenType.EQUAL,
                "EQUAL LITERAL");
        predictiveParsingTable.add(TableRowHeader.INITIALIZATION, TokenType.SEMICOLON,
                "E");

        // LOOPING_STATEMENT
        predictiveParsingTable.add(TableRowHeader.LOOPING_STATEMENT, TokenType.FOR,
                "FOR LPAREN LOOP_INITIALIZATION SEMICOLON CONDITION SEMICOLON INCREMENT_STMT RPAREN LBRACE LOOP_BODY RBRACE");
        predictiveParsingTable.add(TableRowHeader.LOOPING_STATEMENT, TokenType.WHILE,
                "WHILE LPAREN CONDITION RPAREN LBRACE LOOP_BODY RBRACE");

        // CONDITIONAL_STATEMENT
        predictiveParsingTable.add(TableRowHeader.CONDITIONAL_STATEMENT, TokenType.IF,
                "IF LPAREN CONDITION RPAREN LBRACE CDTN_BODY RBRACE CONDITIONAL_STATEMENT1");

        // CONDITIONAL_STATEMENT1
        predictiveParsingTable.add(TableRowHeader.CONDITIONAL_STATEMENT1, TokenType.ELSE,
                "ELSE CONDITIONAL_STATEMENT2");
        predictiveParsingTable.add(TableRowHeader.CONDITIONAL_STATEMENT1, TokenType.RBRACE,
                "E");

        // CONDITIONAL_STATEMENT2
        predictiveParsingTable.add(TableRowHeader.CONDITIONAL_STATEMENT2, TokenType.LBRACE,
                "LBRACE CDTN_BODY RBRACE");
        predictiveParsingTable.add(TableRowHeader.CONDITIONAL_STATEMENT2, TokenType.IF,
                "CONDITIONAL_STATEMENT");
        predictiveParsingTable.add(TableRowHeader.CONDITIONAL_STATEMENT2, TokenType.RBRACE,
                "E");

        // CONDITION
        predictiveParsingTable.add(TableRowHeader.CONDITION, TokenType.IDENTIFIER,
                "IDENTIFIER COMPARISON_OPERATOR CONDITION_RHS");

        // CONDITION_RHS
        predictiveParsingTable.add(TableRowHeader.CONDITION_RHS, TokenType.IDENTIFIER, "IDENTIFIER");
        predictiveParsingTable.add(TableRowHeader.CONDITION_RHS, TokenType.INT_LITERAL, "LITERAL");
        predictiveParsingTable.add(TableRowHeader.CONDITION_RHS, TokenType.FLOAT_LITERAL, "LITERAL");
        predictiveParsingTable.add(TableRowHeader.CONDITION_RHS, TokenType.STRING_LITERAL, "LITERAL");

        // CDTN_BODY
        predictiveParsingTable.add(TableRowHeader.CDTN_BODY, TokenType.EQUAL,
                "INITIALIZATION CDTN_BODY");
        predictiveParsingTable.add(TableRowHeader.CDTN_BODY, TokenType.INT,
                "DECLARATION CDTN_BODY");
        predictiveParsingTable.add(TableRowHeader.CDTN_BODY, TokenType.FLOAT,
                "DECLARATION CDTN_BODY");
        predictiveParsingTable.add(TableRowHeader.CDTN_BODY, TokenType.STRING,
                "DECLARATION CDTN_BODY");
        predictiveParsingTable.add(TableRowHeader.CDTN_BODY, TokenType.IDENTIFIER,
                "EXPRESSION CDTN_BODY");
        predictiveParsingTable.add(TableRowHeader.CDTN_BODY, TokenType.FOR,
                "LOOPING_STATEMENT CDTN_BODY");
        predictiveParsingTable.add(TableRowHeader.CDTN_BODY, TokenType.WHILE,
                "LOOPING_STATEMENT CDTN_BODY");
        predictiveParsingTable.add(TableRowHeader.CDTN_BODY, TokenType.IF,
                "CONDITIONAL_STATEMENT CDTN_BODY");
        predictiveParsingTable.add(TableRowHeader.CDTN_BODY, TokenType.RBRACE,
                "E");

        // LOOP_BODY
        predictiveParsingTable.add(TableRowHeader.LOOP_BODY, TokenType.EQUAL,
                "INITIALIZATION LOOP_BODY");
        predictiveParsingTable.add(TableRowHeader.LOOP_BODY, TokenType.INT,
                "DECLARATION LOOP_BODY");
        predictiveParsingTable.add(TableRowHeader.LOOP_BODY, TokenType.FLOAT,
                "DECLARATION LOOP_BODY");
        predictiveParsingTable.add(TableRowHeader.LOOP_BODY, TokenType.STRING,
                "DECLARATION LOOP_BODY");
        predictiveParsingTable.add(TableRowHeader.LOOP_BODY, TokenType.IDENTIFIER,
                "EXPRESSION LOOP_BODY");
        predictiveParsingTable.add(TableRowHeader.LOOP_BODY, TokenType.FOR,
                "LOOPING_STATEMENT LOOP_BODY");
        predictiveParsingTable.add(TableRowHeader.LOOP_BODY, TokenType.WHILE,
                "LOOPING_STATEMENT LOOP_BODY");
        predictiveParsingTable.add(TableRowHeader.LOOP_BODY, TokenType.IF,
                "CONDITIONAL_STATEMENT LOOP_BODY");
        predictiveParsingTable.add(TableRowHeader.LOOP_BODY, TokenType.RBRACE,
                "E");

        // INCREMENT
        predictiveParsingTable.add(TableRowHeader.INCREMENT_STMT, TokenType.IDENTIFIER,
                "IDENTIFIER INC_OPERATOR");

        // INC_OPERATOR
        predictiveParsingTable.add(TableRowHeader.INC_OPERATOR, TokenType.INCREMENT,
                "INCREMENT");
        predictiveParsingTable.add(TableRowHeader.INC_OPERATOR, TokenType.DECREMENT,
                "DECREMENT");

        // COMPARISON_OPERATOR
        predictiveParsingTable.add(TableRowHeader.COMPARISON_OPERATOR, TokenType.EQUALITY,
                "EQUALITY");
        predictiveParsingTable.add(TableRowHeader.COMPARISON_OPERATOR, TokenType.NOT_EQUAL,
                "NOT_EQUAL");
        predictiveParsingTable.add(TableRowHeader.COMPARISON_OPERATOR, TokenType.LT,
                "LT");
        predictiveParsingTable.add(TableRowHeader.COMPARISON_OPERATOR, TokenType.GT,
                "GT");
        predictiveParsingTable.add(TableRowHeader.COMPARISON_OPERATOR, TokenType.LTE,
                "LTE");
        predictiveParsingTable.add(TableRowHeader.COMPARISON_OPERATOR, TokenType.GTE,
                "GTE");

        // EXPRESSION
        predictiveParsingTable.add(TableRowHeader.EXPRESSION, TokenType.IDENTIFIER,
                "IDENTIFIER EXPRESSION_TAIL");

        // EXPRESSION_TAIL
        predictiveParsingTable.add(TableRowHeader.EXPRESSION_TAIL, TokenType.EQUAL, "ASSIGNMENT_OPERATOR EXPRESSION_RHS SEMICOLON");
        predictiveParsingTable.add(TableRowHeader.EXPRESSION_TAIL, TokenType.PLUS_EQUAL, "ASSIGNMENT_OPERATOR EXPRESSION_RHS SEMICOLON");
        predictiveParsingTable.add(TableRowHeader.EXPRESSION_TAIL, TokenType.MINUS_EQUAL, "ASSIGNMENT_OPERATOR EXPRESSION_RHS SEMICOLON");
        predictiveParsingTable.add(TableRowHeader.EXPRESSION_TAIL, TokenType.STAR_EQUAL, "ASSIGNMENT_OPERATOR EXPRESSION_RHS SEMICOLON");
        predictiveParsingTable.add(TableRowHeader.EXPRESSION_TAIL, TokenType.DIV_EQUAL, "ASSIGNMENT_OPERATOR EXPRESSION_RHS SEMICOLON");
        predictiveParsingTable.add(TableRowHeader.EXPRESSION_TAIL, TokenType.MODULO_EQUAL, "ASSIGNMENT_OPERATOR EXPRESSION_RHS SEMICOLON");

        predictiveParsingTable.add(TableRowHeader.EXPRESSION_TAIL, TokenType.INCREMENT, "INC_OPERATOR SEMICOLON");
        predictiveParsingTable.add(TableRowHeader.EXPRESSION_TAIL, TokenType.DECREMENT, "INC_OPERATOR SEMICOLON");
        predictiveParsingTable.add(TableRowHeader.EXPRESSION_TAIL, TokenType.SEMICOLON, "SEMICOLON");

        // EXPRESSION_RHS
        predictiveParsingTable.add(TableRowHeader.EXPRESSION_RHS, TokenType.INT_LITERAL, "LITERAL");
        predictiveParsingTable.add(TableRowHeader.EXPRESSION_RHS, TokenType.FLOAT_LITERAL, "LITERAL");
        predictiveParsingTable.add(TableRowHeader.EXPRESSION_RHS, TokenType.STRING_LITERAL, "LITERAL");
        predictiveParsingTable.add(TableRowHeader.EXPRESSION_RHS, TokenType.IDENTIFIER, "IDENTIFIER");

        // LOOP_INITIALIZATION
        predictiveParsingTable.add(TableRowHeader.LOOP_INITIALIZATION, TokenType.INT,
                "TYPE IDENTIFIER EQUAL LITERAL");
        predictiveParsingTable.add(TableRowHeader.LOOP_INITIALIZATION, TokenType.FLOAT,
                "TYPE IDENTIFIER EQUAL LITERAL");
        predictiveParsingTable.add(TableRowHeader.LOOP_INITIALIZATION, TokenType.STRING,
                "TYPE IDENTIFIER EQUAL LITERAL");

        // ASSIGNMENT_OPERATOR
        predictiveParsingTable.add(TableRowHeader.ASSIGNMENT_OPERATOR, TokenType.EQUAL,
                "EQUAL");
        predictiveParsingTable.add(TableRowHeader.ASSIGNMENT_OPERATOR, TokenType.PLUS_EQUAL,
                "PLUS_EQUAL");
        predictiveParsingTable.add(TableRowHeader.ASSIGNMENT_OPERATOR, TokenType.MINUS_EQUAL,
                "MINUS_EQUAL");
        predictiveParsingTable.add(TableRowHeader.ASSIGNMENT_OPERATOR, TokenType.DIV_EQUAL,
                "DIV_EQUAL");
        predictiveParsingTable.add(TableRowHeader.ASSIGNMENT_OPERATOR, TokenType.STAR_EQUAL,
                "STAR_EQUAL");
        predictiveParsingTable.add(TableRowHeader.ASSIGNMENT_OPERATOR, TokenType.MODULO_EQUAL,
                "MODULO_EQUAL");

        // TYPE
        predictiveParsingTable.add(TableRowHeader.TYPE, TokenType.INT,
                "INT");
        predictiveParsingTable.add(TableRowHeader.TYPE, TokenType.FLOAT,
                "FLOAT");
        predictiveParsingTable.add(TableRowHeader.TYPE, TokenType.STRING,
                "STRING");

        predictiveParsingTable.add(TableRowHeader.LITERAL, TokenType.INT_LITERAL,
                "INT_LITERAL");
        predictiveParsingTable.add(TableRowHeader.LITERAL, TokenType.FLOAT_LITERAL,
                "FLOAT_LITERAL");
        predictiveParsingTable.add(TableRowHeader.LITERAL, TokenType.STRING_LITERAL,
                "STRING_LITERAL");
    }

    public void generateParseTree(List<Token> tokenList) {
        Stack<Object> stack = new Stack<>();
        stack.push(TokenType.EOF);
        stack.push(TableRowHeader.PROGRAM);

        int ipPointer = 0;
        while (!stack.isEmpty()) {
            Token currentToken = tokenList.get(ipPointer);
            Object stackTop = stack.pop();

            if (stackTop instanceof TokenType) {
                if (stackTop.equals(TokenType.EOF) && currentToken.getTokenType().equals(TokenType.EOF)) {
                    System.out.println("Parsed. Valid input.");
                    break;
                } else {
                    if (stackTop.equals(currentToken.getTokenType())) ipPointer++;
                    else {
                        System.out.println("Syntax error, input doesnt match. Expected Token [" + stackTop.toString() + "] but found Token [" + currentToken.getTokenType() + " ('" + currentToken.getLexeme() + "')] at index " + ipPointer);
                        break;
                    }
                }
            } else {
                String production = predictiveParsingTable.getProduction((TableRowHeader) stackTop, currentToken.getTokenType());

                if (production == null) {
                    System.out.println("Missing Production Error! No rule for Non-Terminal [" + stackTop.toString()
                            + "] on Token [" + currentToken.getTokenType()
                            + " ('" + currentToken.getLexeme() + "')] at index " + ipPointer);
                    break;
                }

                if (production.equals("E")) {
                    continue;
                }

                String[] symbols = production.split(" ");
                for (int i = symbols.length - 1; i >= 0; i--) {
                    try {
                        TokenType tokenType = TokenType.valueOf(symbols[i]);
                        stack.push(tokenType);
                    } catch (Exception e) {
                        TableRowHeader tableRowHeader = TableRowHeader.valueOf(symbols[i]);
                        stack.push(tableRowHeader);
                    }
                }
            }
        }
    }

    public void parse(List<Token> tokenList) {
        addProduction();
        generateParseTree(tokenList);
    }
}
