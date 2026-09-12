public class Main {

    public static void main(String[] args) throws Exception {

        String input = "let preco = 100 + 25 * 2;";

        Parser p = new Parser(input.getBytes());

        p.parse();
    }
}
