class Lexer{
    private final String letters = "abcdefghijklmnopqrstuvmxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private final String digits = "0123456789";

    String stmt;
    int index = 0;
    char ch;
    String keyword="0";
    String key="0";

    public Lexer(String s){
        stmt = s; index=0; ch = nextChar();
    }

    public Token nextToken()
    {
        do {
            if(ch == '$')
                return new Token(Token.TokenType.EOI, "EOI");
            else if(ch == ' ')
                ch = nextChar();
            else if(Character.isLetter(ch) || Character.isDigit(ch))
            {
                String string = concat(letters + digits);
                return new Token(Token.TokenType.STRING, string);
            }
            else if(ch == ('<')) {
		if (keyword == "0"){
			keyword = Character.toString(ch);
			ch = nextChar();
		}
		else {
                keyword = keyword + Character.toString(ch);
                ch = nextChar();
		}

                while((ch != '>') && (ch != '$')) {
                    keyword = keyword + Character.toString(ch);
                    ch = nextChar();
                }
                keyword = keyword + Character.toString(ch);

		if (keyword.equals("<body>") || keyword.equals("</body>") || keyword.equals("<b>") || keyword.equals("</b>") || keyword.equals("<i>") || keyword.equals("</i>") || keyword.equals("<ul>") || keyword.equals("</ul>") || keyword.equals("<li>") || keyword.equals("</li>")) { 
		     ch = nextChar();
		     key = keyword;
		     keyword = "0";
		     return new Token(Token.TokenType.KEYWORD, key);
		}
                else {
                    ch = nextChar();
                    return new Token(Token.TokenType.INVALID, "Invalid token received.");
		}
            }
            else {
                    ch = nextChar();
                    return new Token(Token.TokenType.INVALID, "Invalid token received.");
            }
        } while (true);
    }

    private char nextChar() {
        char ch = stmt.charAt(index); index = index+1;
        return ch;
    }

    private String concat (String set) {
        StringBuffer r = new StringBuffer("");
        do { r.append(ch); ch = nextChar();
        } while (set.indexOf(ch) >= 0);
        return r.toString();
    }

    private boolean check(char c) {
        ch = nextChar();
        if (ch == c) {ch = nextChar(); return true;}
        else return false;
    }

    public void error (String msg) {
        System.err.println("\nError: location " + index + " " + msg);
        System.exit(1);
    }
}
