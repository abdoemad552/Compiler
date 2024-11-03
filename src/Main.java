import java.io.IOException;
import java.util.*;
import java.util.regex.*;

public class Main {
    private static final String KEYWORD             = "keyword";
    private static final String IDENTIFIER          = "identifier";
    private static final String NUMERIC_LITERAL     = "numericLiteral";
    private static final String STRING_LITERAL      = "stringLiteral";
    private static final String CHARACTER_LITERAL   = "characterLiteral";
    private static final String OPERATOR            = "operator";
    private static final String SPECIAL_CHARACTER   = "specialCharacter";
    private static final String WHITE_SPACE         = "whiteSpace";
    private static final String UNKNOWN             = "unknown";

    public static void main(String[] args) {
        System.out.println("Enter code (press `Ctrl` + `D` (Linux/macOS) or `Ctrl` + `C` (Windows) to finish input):");

        StringBuilder inputCode = new StringBuilder();
        try {
            int inputChar;
            while ((inputChar = System.in.read()) != -1) {
                inputCode.append((char) inputChar);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        Pattern pattern = TokenPattern.getAllInOnePattern();
        Matcher matcher = pattern.matcher(inputCode.toString());

        List<Token> tokens = new ArrayList<>();
        while (matcher.find()) {
            if (matcher.group(KEYWORD) != null) {
                tokens.add(new Token(KEYWORD, matcher.group(KEYWORD)));
            } else if (matcher.group(IDENTIFIER) != null) {
                tokens.add(new Token(IDENTIFIER, matcher.group(IDENTIFIER)));
            } else if (matcher.group(NUMERIC_LITERAL) != null) {
                tokens.add(new Token(NUMERIC_LITERAL, matcher.group(NUMERIC_LITERAL)));
            } else if (matcher.group(STRING_LITERAL) != null) {
                tokens.add(new Token(STRING_LITERAL, matcher.group(STRING_LITERAL)));
            } else if (matcher.group(CHARACTER_LITERAL) != null) {
                tokens.add(new Token(CHARACTER_LITERAL, matcher.group(CHARACTER_LITERAL)));
            } else if (matcher.group(OPERATOR) != null) {
                tokens.add(new Token(OPERATOR, matcher.group(OPERATOR)));
            } else if (matcher.group(SPECIAL_CHARACTER) != null) {
                tokens.add(new Token(SPECIAL_CHARACTER, matcher.group(SPECIAL_CHARACTER)));
            } else if (matcher.group(WHITE_SPACE) != null) {
                tokens.add(new Token(WHITE_SPACE, matcher.group(WHITE_SPACE)));
            } else if (matcher.group(UNKNOWN) != null) {
                tokens.add(new Token(UNKNOWN, matcher.group(UNKNOWN)));
            }
        }

        for (Token token : tokens) {
            if (token.getName().equals(WHITE_SPACE)) continue;
            System.out.println(token);
        }
    }
}