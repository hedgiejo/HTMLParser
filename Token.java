public class Token{
    public enum TokenType {STRING,KEYWORD,EOI,INVALID}

    private TokenType type;
    private String val;

    Token (TokenType t, String v) {
        type = t; val = v;
    }

    TokenType getTokenType() {return type;}
    String getTokenValue() {return val;}

    public static String typeToString (TokenType tp) {
        String s = "";

        switch (tp) {
        case STRING: s = "String"; break;
        case KEYWORD: s = "Keyword"; break;
        case EOI: s = "EOI"; break;
        case INVALID: s ="Invalid"; break;
        }
        return s;
    }
}
