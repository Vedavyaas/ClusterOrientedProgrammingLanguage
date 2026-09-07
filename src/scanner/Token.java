package scanner;

public class Token {
    TokenType tokenType;
    String lexeme;
    Object tokenValue;
    long line;

    public Token(TokenType tokenType, String lexeme, Object tokenValue, long line) {
        this.tokenType = tokenType;
        this.lexeme = lexeme;
        this.tokenValue = tokenValue;
        this.line = line;
    }

    public TokenType getTokenType() {
        return tokenType;
    }

    public String getLexeme() {
        return lexeme;
    }

    public Object getTokenValue() {
        return tokenValue;
    }

    public long getLine() {
        return line;
    }

    @Override
    public String toString() {
        return String.format("%-6d %-20s %-15s", line, tokenType.toString(), lexeme);
    }
}
