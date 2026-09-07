package scanner;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Lexer {
    Path path;
    private final List<Token> tokenList = new ArrayList<>();
    private final List<Data> symbolTable = new ArrayList<>();

    private static final Map<Character, TokenType> SINGLE_CHARACTER_TOKEN =
            Map.ofEntries(
                    Map.entry('+', TokenType.ADDITION),
                    Map.entry('-', TokenType.SUBTRACTION),
                    Map.entry('*', TokenType.MULTIPLICATION),
                    Map.entry('/', TokenType.DIVISION),
                    Map.entry('%', TokenType.MODULO),
                    Map.entry('=', TokenType.EQUAL),
                    Map.entry(';', TokenType.SEMICOLON),
                    Map.entry('{', TokenType.LBRACE),
                    Map.entry('}', TokenType.RBRACE),
                    Map.entry('(', TokenType.LPAREN),
                    Map.entry(')', TokenType.RPAREN),
                    Map.entry('<', TokenType.LT),
                    Map.entry('>', TokenType.GT),
                    Map.entry('@', TokenType.AT)
            );

    private static final Map<String, TokenType> TWO_CHARACTER_TOKEN =
            Map.ofEntries(
                    Map.entry("==", TokenType.EQUALITY),
                    Map.entry("<=", TokenType.LTE),
                    Map.entry(">=", TokenType.GTE),
                    Map.entry("+=", TokenType.PLUS_EQUAL),
                    Map.entry("-=", TokenType.MINUS_EQUAL),
                    Map.entry("*=", TokenType.STAR_EQUAL),
                    Map.entry("/=", TokenType.DIV_EQUAL),
                    Map.entry("%=", TokenType.MODULO_EQUAL),
                    Map.entry("++", TokenType.INCREMENT),
                    Map.entry("--", TokenType.DECREMENT)
            );

    private static final Map<String, TokenType> KEYWORD_MAP =
            Map.ofEntries(
                    Map.entry("int", TokenType.INT),
                    Map.entry("float", TokenType.FLOAT),
                    Map.entry("if", TokenType.IF),
                    Map.entry("else", TokenType.ELSE),
                    Map.entry("else if", TokenType.ELIF),
                    Map.entry("for", TokenType.FOR),
                    Map.entry("while", TokenType.WHILE),
                    Map.entry("class", TokenType.CLASS),
                    Map.entry("method", TokenType.METHOD),
                    Map.entry("string", TokenType.STRING)
            );

    public Lexer(Path path) {
        this.path = path;
    }

    public List<Token> scan() throws IOException {
        String fileContent = Files.readString(path);
        Path outputPath = Path.of("/Users/vedavyaasmr/IdeaProjects/ClusterOrientedProgrammingLanguage/output.txt");

        scanner(fileContent);

        StringBuilder output = new StringBuilder();
        output.append("------------------------------------------------------------\n");
        output.append(String.format("%-6s %-20s %-15s\n",
                "LINE", "TOKEN TYPE", "LEXEME"));
        output.append("------------------------------------------------------------\n");

        int i = 0;
        for (Token token : tokenList) {
            output.append(token).append("\n");
            if (token.getTokenType().equals(TokenType.IDENTIFIER)) {
                Type type = null;
                if (i > 0) {
                    TokenType prevType = tokenList.get(i - 1).getTokenType();
                    if (prevType == TokenType.INT) type = Type.INTEGER;
                    else if (prevType == TokenType.FLOAT) type = Type.FLOAT;
                    else if (prevType == TokenType.STRING) type = Type.STRING;
                }

                boolean exists = false;
                for (Data d : symbolTable) {
                    if (d.lexeme().equals(token.lexeme)) {
                        exists = true;
                        break;
                    }
                }

                if (!exists && type != null) {
                    Object value = null;
                    int j = i + 1;
                    while (j < tokenList.size() && tokenList.get(j).getTokenType() != TokenType.SEMICOLON) {
                        if (tokenList.get(j).getTokenType() == TokenType.INT_LITERAL ||
                            tokenList.get(j).getTokenType() == TokenType.FLOAT_LITERAL ||
                            tokenList.get(j).getTokenType() == TokenType.STRING_LITERAL) {
                            value = tokenList.get(j).tokenValue;
                            break;
                        }
                        j++;
                    }
                    symbolTable.add(new Data(token.lexeme, type, value, Scope.GLOBAL));
                }
            }
            i++;
        }

        output.append("------------------------------------------------------------");

        Files.writeString(outputPath, output.toString());

        return tokenList;
    }

    public void scanner(String fileContent) {
        int current = 0;
        long line = 1;

        while (current < fileContent.length()) {
            char currentChar = fileContent.charAt(current);

            //comments
            if (currentChar == '/') {
                int start = current;
                current++;
                if (fileContent.charAt(current) == '/') {
                    while (current < fileContent.length() && fileContent.charAt(current) != '\n') {
                        current++;
                    }
                    continue;
                } else if (fileContent.charAt(current) == '*') {
                    while (fileContent.charAt(current) != '*' || fileContent.charAt(current + 1) != '/') {
                        current++;
                        if (fileContent.charAt(current) == '\n') line++;
                    }
                    current += 2;
                    continue;
                } else {
                    current = start;
                }
            }

            if (current + 1 < fileContent.length()) {
                String op = "" + currentChar + fileContent.charAt(current + 1);
                TokenType tokenType2 = TWO_CHARACTER_TOKEN.get(op);

                if (tokenType2 != null) {
                    addSimpleToken(tokenType2, line);
                    current += 2;
                    continue;
                }
            }

            TokenType tokenType = SINGLE_CHARACTER_TOKEN.get(currentChar);
            if (tokenType != null) {
                addSimpleToken(tokenType, line);
                current++;
                continue;
            }

            if (currentChar == '\n') {
                line++;
                current++;
                continue;
            }

            if (Character.isWhitespace(currentChar)) {
                current++;
                continue;
            }

            if (currentChar == '"') {
                int start = current + 1;
                current++;

                while (current < fileContent.length() &&
                        fileContent.charAt(current) != '"') {

                    if (fileContent.charAt(current) == '\n') {
                        line++;
                    }

                    current++;
                }

                if (current >= fileContent.length()) {
                    throw new LexicalException(
                            "Unterminated string literal at line " + line
                    );
                }

                String lexeme = fileContent.substring(start, current);

                createToken(
                        TokenType.STRING_LITERAL,
                        lexeme,
                        lexeme,
                        line
                );
                current++;
                continue;
            }

            //numeric
            if (Character.isDigit(currentChar)) {
                int start = current;
                int seenDot = 0;

                while (current < fileContent.length() &&
                        (Character.isDigit(fileContent.charAt(current)) ||
                                (fileContent.charAt(current) == '.' && seenDot == 0))) {
                    if (fileContent.charAt(current) == '.') seenDot++;
                    current++;
                }

                String lexeme = fileContent.substring(start, current);

                if (lexeme.contains(".")) {
                    addNumericLiteralToken(TokenType.FLOAT_LITERAL, lexeme, line);
                } else {
                    addNumericLiteralToken(TokenType.INT_LITERAL, lexeme, line);
                }

                continue;
            }

            //id or keyword
            if (Character.isLetter(currentChar)) {
                int start = current;

                while (current < fileContent.length() &&
                        Character.isLetterOrDigit(fileContent.charAt(current))) {
                    current++;
                }

                String lexeme = fileContent.substring(start, current);

                TokenType keyword = KEYWORD_MAP.get(lexeme);

                if (keyword != null) {
                    addSimpleToken(keyword, line);
                } else {
                    addStringLiteralToken(lexeme, line);
                }

                continue;
            }

            throw new LexicalException(
                    "Unexpected character '" + currentChar +
                            "' at line " + line);
        }
        tokenList.add(new Token(TokenType.EOF, "EOF", null, line + 1));
    }

    private void addSimpleToken(TokenType tokenType, long line) {
        createToken(tokenType, tokenType.getLexeme(), null, line);
    }

    private void addNumericLiteralToken(TokenType tokenType, String lexeme, long line) {
        //only for float, int and numeric data types
        Number value = null;
        if (tokenType.equals(TokenType.INT_LITERAL)) value = Integer.parseInt(lexeme);
        else if (tokenType.equals(TokenType.FLOAT_LITERAL)) value = Float.parseFloat(lexeme);
        createToken(tokenType, lexeme, value, line);
    }

    private void addStringLiteralToken(String lexeme, long line) {
        createToken(TokenType.IDENTIFIER, lexeme, null, line);
    }

    private void createToken(TokenType tokenType, String lexeme, Object tokenValue, long line) {
        Token token = new Token(tokenType, lexeme, tokenValue, line);
        tokenList.add(token);
    }

    public List<Data> getSymbolTable() {
        return symbolTable;
    }
}
