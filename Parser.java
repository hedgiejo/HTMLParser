/*Parser class to parse the HTML keywords and output results in a nicely formatted tabbed version of hierarchy tree*/
class Parser {

    Lexer lexer;
    Token token;
    int tab_n;

    // Parser constructor for retrieving tokens from the Lexer class
    public Parser(String s) {
        lexer = new Lexer(s + "$");
        token = lexer.nextToken();
    }

    // initial run on call
    public void run() {
        webpage();
    }

    // retrieve next token 
    private void next() {
        token = lexer.nextToken();
    }

    // first code to be run in Parser, checks <body> </body> opening and closing keywords
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
	// if token type is Invalid, return an error
        if(token.getTokenType()==Token.TokenType.INVALID){
            error(token.getTokenType());
            next();
        }
    }

    // text function: checks the TEXT grammar and recursively calls itself on any nonterminals
    public void text() {
        tab_n++;
	if (tab_n== 0)
		return;
	// if the token is string, print the string value on screen
        if(token.getTokenType().equals(Token.TokenType.STRING)) {
            tab();
            System.out.println(token.getTokenValue());
            next();
        }
	// if the token is a keyword "<b>", print the value and call itself to check for other strings within
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
	// report error otherwise
        else {   
            error(Token.TokenType.INVALID);
            next();
        }
    }

    // tab function: tab the current line tab_n times
    private void tab() {
        if(tab_n !=0){
              for(int i = 0;i<tab_n;i++)
                    System.out.print("\t");
            }
    }

    // matches the string for type comparison and output result. otherwise, report an error
    private String match (Token.TokenType tp) {
        String value = token.getTokenValue();
        if (token.getTokenType() == tp)
            token = lexer.nextToken();
        else error(tp);
        return value;
    }

    // output and error message for incorrect token type expected
    private void error (Token.TokenType tp) {
        System.err.println("Syntax error: expecting: " + Token.typeToString(tp) + "; saw: "
                           + Token.typeToString(token.getTokenType()));
        System.exit(1);
    }
}
