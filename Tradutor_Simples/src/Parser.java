public class Parser {

    private Scanner scan;
    private char currentToken;

    public Parser(byte[] input) {

        scan = new Scanner(input);

        currentToken = scan.nextToken();
    }

    private void nextToken() {

        currentToken = scan.nextToken();
    }

    private void match(char t) {

        if (currentToken == t) {

            nextToken();

        } else {

            throw new Error("syntax error");
        }
    }

    public void parse() {

        expr();
    }

    private void expr() {

        term();
        oper();
    }

    private void oper() {

        if (currentToken == '+') {

            match('+');

            term();

            System.out.println("add");

            oper();

        } else if (currentToken == '-') {

            match('-');

            term();

            System.out.println("sub");

            oper();
        }
    }

    private void term() {

        digit();
        termOper();
    }

    private void termOper() {

        if (currentToken == '*') {

            match('*');

            digit();

            System.out.println("mul");

            termOper();

        } else if (currentToken == '/') {

            match('/');

            digit();

            System.out.println("div");

            termOper();
        }
    }

    private void digit() {

        if (Character.isDigit(currentToken)) {

            System.out.println("push " + currentToken);

            match(currentToken);

        } else {

            throw new Error("syntax error");
        }
    }
}