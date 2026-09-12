public class Parser {

    private StringBuilder output = new StringBuilder();
    private Scanner scan;
    private Token currentToken;

    public Parser(byte[] input) {

        scan = new Scanner(input);

        currentToken = scan.nextToken();
    }

    private void nextToken() {

        currentToken = scan.nextToken();
    }

    private void match(TokenType t) {

        if (currentToken.type == t) {

            nextToken();

        } else {

            throw new Error("syntax error");
        }
    }

    public void parse() {

        statements();
    }

    private void expr() {

        term();
        oper();
    }

    private void oper() {

        if (currentToken.type == TokenType.PLUS) {

            match(TokenType.PLUS);

            term();

            emit("add");

            oper();

        } else if (currentToken.type == TokenType.MINUS) {

            match(TokenType.MINUS);

            term();

            emit("sub");

            oper();
        }
    }

    private void term() {

        factor();
        termOper();
    }

    private void termOper() {

        if (currentToken.type == TokenType.MULT) {

            match(TokenType.MULT);

            factor();

            emit("mul");

            termOper();

        } else if (currentToken.type == TokenType.DIV) {

            match(TokenType.DIV);

            factor();

            emit("div");

            termOper();
        }
    }

    private void number() {

        emit("push " + currentToken.lexeme);

        match(TokenType.NUMBER);
    }

    private void factor() {

        if (currentToken.type == TokenType.NUMBER) {

            number();

        } else if (currentToken.type == TokenType.IDENT) {

            emit("push " + currentToken.lexeme);

            match(TokenType.IDENT);

        } else {

            throw new Error("syntax error");
        }
    }


    private void letStatement() {

        match(TokenType.LET);

        String id = currentToken.lexeme;

        match(TokenType.IDENT);

        match(TokenType.EQ);

        expr();

        emit("pop " + id);

        match(TokenType.SEMICOLON);
    }
    
    private void printStatement() {

        match(TokenType.PRINT);

        expr();

        emit("print");

        match(TokenType.SEMICOLON);
    }

    private void statement() {

        if (currentToken.type == TokenType.PRINT) {

            printStatement();

        } else if (currentToken.type == TokenType.LET) {

            letStatement();

        } else {

            throw new Error("syntax error");
        }
    }

    private void statements() {

        while (currentToken.type != TokenType.EOF) {

            statement();
        }
    }

    private void emit(String command) {

        output.append(command);
        output.append(System.lineSeparator());
    }

    public String output() {

        return output.toString();
    }
}