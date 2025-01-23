import java.io.*;
import java.util.*;
import java.util.regex.*;

public class Main {

    private static String getInputCode() {
        String EOF = "__end__";

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
        Matcher matcher = Pattern
            .compile(TokenRegex.all(), Pattern.MULTILINE)
            .matcher(inputCode);

        List<Token> tokens = new ArrayList<>();
        while (matcher.find()) {
            for (TokenRegex regex : TokenRegex.values()) {
                if (matcher.group(regex.group()) != null) {
                    tokens.add(new Token(regex.group(), matcher.group()));
                    break;
                }
            }
        }

        return tokens;
    }

    public static void main(String[] args) {
        String inputCode = getInputCode();
        List<Token> tokens = tokenize(inputCode);
        for (Token token : tokens) {
            if (token.getGroup().equals(TokenRegex.WHITE_SPACE.group())) continue;
            System.out.println(token);
        }

        //TODO: Take this list of tokens to the next stage which is parsing...
    }
}