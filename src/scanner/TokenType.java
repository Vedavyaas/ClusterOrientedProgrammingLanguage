package scanner;

public enum TokenType {
    //literals
    INT_LITERAL,
    FLOAT_LITERAL,
    STRING_LITERAL,

    //keywords
    INT("int"),
    FLOAT("float"),
    STRING("string"),


    IF("if"),
    ELSE("else"),
    ELIF("else if"),
    FOR("for"),
    WHILE("while"),


    //operators
    EQUAL("="),
    ADDITION("+"),
    SUBTRACTION("-"),
    MULTIPLICATION("*"),
    DIVISION("/"),
    MODULO("%"),
    PLUS_EQUAL("+="),
    MINUS_EQUAL("-="),
    STAR_EQUAL("*="),
    DIV_EQUAL("/="),
    MODULO_EQUAL("%="),
    EQUALITY("=="),
    NOT_EQUAL("!="),
    LT("<"),
    GT(">"),
    LTE("<="),
    GTE(">="),
    INCREMENT("++"),
    DECREMENT("--"),
    AT("@"),
    DOUBLE_QUOTE("\""),

    IDENTIFIER,

    //cluster/object oriented keywords
    CLASS("class"),
    METHOD("method"),

    //end
    SEMICOLON(";"),
    LBRACE("{"),
    RBRACE("}"),
    LPAREN("("),
    RPAREN(")"),

    EOF("EOF");

    private final String lexeme;

    public String getLexeme() {
        return lexeme;
    }

    TokenType(String lexeme) {
        this.lexeme = lexeme;
    }

    TokenType() {
        this.lexeme = null;
    }
}
