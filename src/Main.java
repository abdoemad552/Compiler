import java.io.IOException;
import java.util.*;
import java.util.regex.*;

public class Main {
    private static final String KEYWORD             = "keyword";
    private static final String IDENTIFIER          = "identifier";
    private static final String NUMERIC_LITERAL     = "numericLiteral";
    private static final String STRING_LITERAL      = "stringLiteral";
    private static final String CHARACTER_LITERAL   = "characterLiteral";
    private static final String PREPROCESSOR        = "preprocessor";
    private static final String COMMENT             = "comment";
    private static final String OPERATOR            = "operator";
    private static final String SPECIAL_CHARACTER   = "specialCharacter";
    private static final String WHITE_SPACE         = "whiteSpace";
    private static final String UNKNOWN             = "unknown";

    private static final String EOF = "__end__";

    private static String getInputCode() {
        System.out.println("Enter code (enter `" + EOF + "` to finish input):");

        StringBuilder inputCode = new StringBuilder();
        try {
            int inputChar;
            while ((inputChar = System.in.read()) != -1) {
                inputCode.append((char) inputChar);
                if (
                    inputCode.length() >= EOF.length() &&
                        inputCode.substring(inputCode.length() - EOF.length()).equals(EOF)
                ) break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return inputCode.substring(0, inputCode.length() - EOF.length());
    }

    private static List<Token> tokenize(String inputCode) {
        Matcher matcher = TokenPattern.getAllInOnePattern().matcher(inputCode);

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
            } else if (matcher.group(COMMENT) != null) {
                tokens.add(new Token(COMMENT, matcher.group(COMMENT)));
            } else if (matcher.group(PREPROCESSOR) != null) {
                tokens.add(new Token(PREPROCESSOR, matcher.group(PREPROCESSOR)));
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

        return tokens;
    }

    public static void main(String[] args) {
        String inputCode = getInputCode();
        List<Token> tokens = tokenize(inputCode);
        for (Token token : tokens) {
            if (token.getName().equals(WHITE_SPACE)) continue;
            System.out.println(token);
        }

        //TODO: Take this list of tokens to the next stage which is parsing...
    }
}