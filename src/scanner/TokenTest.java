package scanner;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
public class TokenTest {
    public static void main(String[] args) throws IOException {
        Lexer lexer = new Lexer(Path.of("input.txt"));
        List<Token> list = lexer.scan();
        for (int i=40; i<80; i++) {
            if (i >= list.size()) break;
            System.out.println(i + ": " + list.get(i).getTokenType() + " " + list.get(i).getLexeme());
        }
    }
}
