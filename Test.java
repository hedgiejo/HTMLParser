public class Test {
    public static void main (String args[]) {

        // testing the lexer
        // Lexer lex = new Lexer ("<body> google <b><i><b> yahoo</b></i></b></body>");
        // Token tk = lex.nextToken();
        // while (tk.getTokenType() != Token.TokenType.EOI) {
        //     tk.print();
        //     System.out.print(" ");
        //     tk = lex.nextToken();
        // }
        // System.out.println("");

        /*Lexer lex = new Lexer ("<body>$");
        Token tk = lex.nextToken();
        while (tk.getTokenType() != Token.TokenType.EOI) {
            System.out.print(tk);
            System.out.print(" ");
            tk = lex.nextToken();
        }
        System.out.println("");
	*/
        // testing the parser
        //Parser parser = new Parser ("<body></body>");
        //parser.run();
        Parser parser = new Parser ("<body><b>a</b></body>");
        parser.run();
//        Parser parser = new Parser ("<body><i>d</i></body>$");
//        parser.run();
//        Parser parser = new Parser ("<body><b>cdb123</body>");
//        parser.run();
    }
}
