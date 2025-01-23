import java.util.regex.Pattern;

public enum TokenRegex {
    KEYWORD(
        "keyword", "\\b(void|char|short|int|long|float|double|const|if|else|switch|case|default|for|while|break|continue|return|sizeof|static|unsigned)\\b"
    ),
    IDENTIFIER(
        "identifier", "\\b([a-zA-Z_][a-zA-Z0-9_]*)\\b"
    ),
    NUMERIC_LITERAL(
        "numericLiteral", "\\b((\\d*\\.\\d+|\\d+\\.\\d*|\\d+)([Ee][+-]?\\d+)?)\\b"
    ),
    STRING_LITERAL(
        "stringLiteral", "\"([^\"\\\\]|\\\\[\\s\\S])*\""
    ),
    CHARACTER_LITERAL(
        "characterLiteral", "'([^'\\\\]|\\\\.)'"
    ),
    PREPROCESSOR(
        "preprocessor", "^# *include *(<\\w+\\.h>|\"\\w+\\.h\")$"
    ),
    COMMENT(
        "comment", "//.*?$|/\\*[\\s\\S]*?\\*/"
    ),
    OPERATOR(
        "operator", "&&|(\\|\\|)|(\\+\\+)|--|(<<|>>|[-+*/%=!<>&^|~])=?"
    ),
    SPECIAL_CHARACTER(
        "specialCharacter", "[;{}\\[\\](),.:]"
    ),
    WHITE_SPACE(
        "whiteSpace", "\\s+"
    ),
    UNKNOWN(
        "unknown", ".+"
    );

    private static final String all;

    private String group;
    private String regex;

    private TokenRegex(String group, String regex) {
        this.group = group;
        this.regex = regex;
    }

    public String regex() {
        return regex;
    }

    public String group() {
        return group;
    }

    @Override
    public String toString() {
        return "(?<" + this.group + ">" + regex + ")";
    }

    public static String all() {
        return all;
    }

    static {
        all = String.join("|",
            TokenRegex.KEYWORD.toString(),
            TokenRegex.IDENTIFIER.toString(),
            TokenRegex.NUMERIC_LITERAL.toString(),
            TokenRegex.STRING_LITERAL.toString(),
            TokenRegex.CHARACTER_LITERAL.toString(),
            TokenRegex.PREPROCESSOR.toString(),
            TokenRegex.COMMENT.toString(),
            TokenRegex.OPERATOR.toString(),
            TokenRegex.SPECIAL_CHARACTER.toString(),
            TokenRegex.WHITE_SPACE.toString(),
            TokenRegex.UNKNOWN.toString()
        );
    }
}