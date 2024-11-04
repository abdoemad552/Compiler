import java.util.regex.Pattern;

enum TokenPattern {
    NOTHING(""),
    KEYWORD(
        "(?<keyword>\\b(void|char|short|int|long|float|double|const|if|else|switch|case|default|for|while|break|continue|return|sizeof|static|unsigned)\\b)"
    ),
    IDENTIFIER(
        "(?<identifier>\\b([a-zA-Z_][a-zA-Z0-9_]*)\\b)"
    ),
    NUMERIC_LITERAL(
        "(?<numericLiteral>\\b((\\d*\\.\\d+|\\d+\\.\\d*|\\d+)([Ee][+-]?\\d+)?)\\b)"
    ),
    STRING_LITERAL(
        "(?<stringLiteral>\"([^\"\\\\]|\\\\[\\s\\S])*\")"
    ),
    CHARACTER_LITERAL(
        "(?<characterLiteral>'([^'\\\\]|\\\\.)')"
    ),
    OPERATOR(
        "(?<operator>&&|(\\|\\|)|(\\+\\+)|--|(<<|>>|[-+*/%=!<>&^|~])=?)"
    ),
    SPECIAL_CHARACTER(
        "(?<specialCharacter>[;{}\\[\\](),.:])"
    ),
    WHITE_SPACE(
        "(?<whiteSpace>\\s+)"
    ),
    COMMENT(
        "(?<comment>//.*?$|/\\*[\\s\\S]*?\\*/)"
    ),
    UNKNOWN(
        "(?<unknown>.+)"
    );

    private static final Pattern allInOnePattern;

    private String pattern;

    private TokenPattern(String pattern) {
        this.pattern = pattern;
    }

    public String getPattern() {
        return pattern;
    }

    public static Pattern getAllInOnePattern() {
        return allInOnePattern;
    }

    static {
        allInOnePattern = Pattern.compile(String.join("|",
            TokenPattern.KEYWORD            .getPattern(),
            TokenPattern.IDENTIFIER         .getPattern(),
            TokenPattern.NUMERIC_LITERAL    .getPattern(),
            TokenPattern.STRING_LITERAL     .getPattern(),
            TokenPattern.CHARACTER_LITERAL  .getPattern(),
            TokenPattern.COMMENT            .getPattern(),
            TokenPattern.OPERATOR           .getPattern(),
            TokenPattern.SPECIAL_CHARACTER  .getPattern(),
            TokenPattern.WHITE_SPACE        .getPattern(),
            TokenPattern.UNKNOWN            .getPattern()
        ), Pattern.MULTILINE);
    }
}