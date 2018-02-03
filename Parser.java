class Parser {

    Lexer lexer;
    Token token;
    int tab_n;

    public Parser(String s) {
        lexer = new Lexer(s + "$");
        token = lexer.nextToken();
    }

    public void run() {
        webpage();
    }

    private void next() {
        token = lexer.nextToken();
    }
    public void webpage() {
        if((token.getTokenType()==Token.TokenType.KEYWORD) && (token.getTokenValue().equals("<body>"))) {
            System.out.println("<body>");
            next();
            while(token.getTokenType().equals(Token.TokenType.STRING) || token.getTokenType().equals(Token.TokenType.KEYWORD)){
	if(token.getTokenType().equals(Token.TokenType.KEYWORD) && (token.getTokenValue().equals("</body>"))) {
        	next();
            	match(Token.TokenType.EOI);
            	tab_n --;
           	System.out.println("</body>");
		return;
        	}
                text();
            }
        }
        else {
            error(Token.TokenType.KEYWORD);
        }
        if(token.getTokenType()==Token.TokenType.INVALID){
            error(token.getTokenType());
            next();
        }
    }

    public void text() {
        tab_n++;
	if (tab_n== 0)
		return;
        if(token.getTokenType().equals(Token.TokenType.STRING)) {
            tab();
            System.out.println(token.getTokenValue());
            next();
        }
        else if(token.getTokenType().equals(Token.TokenType.KEYWORD) && (token.getTokenValue().equals("<b>"))){
            tab();
            System.out.println("<b>");
            next();
            text();
            if(token.getTokenValue().equals("</b>")) {
                tab_n--;
                tab();
                System.out.println("</b>");
            }
            next();
        }
        else {   
            error(Token.TokenType.INVALID);
            next();
        }
    }

    private void tab() {
        if(tab_n !=0){
              for(int i = 0;i<tab_n;i++)
                    System.out.print("\t");
            }
    }

    private String match (Token.TokenType tp) {
        String value = token.getTokenValue();
        if (token.getTokenType() == tp)
            token = lexer.nextToken();
        else error(tp);
        return value;
    }

    private void error (Token.TokenType tp) {
        System.err.println("Syntax error: expecting: " + Token.typeToString(tp) + "; saw: "
                           + Token.typeToString(token.getTokenType()));
        System.exit(1);
    }
}
