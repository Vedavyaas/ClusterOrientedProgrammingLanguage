import parser.ParserConstruction;
import scanner.Data;
import scanner.Lexer;
import scanner.Token;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        Path path = Path.of("/Users/vedavyaasmr/IdeaProjects/ClusterOrientedProgrammingLanguage/input.txt");
        Lexer lexer = new Lexer(path);
        List<Token> tokenList = lexer.scan();
        List<Data> symbolTable = lexer.getSymbolTable();

        symbolTable.forEach(System.out::println);

        ParserConstruction parserConstruction = new ParserConstruction();
        parserConstruction.parse(tokenList);
    }
}