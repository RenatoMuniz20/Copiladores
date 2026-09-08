public class Main {

    public static void main(String[] args) throws Exception {

        String input = "8+6/2-3*2";

        Parser p = new Parser(input.getBytes());

        p.parse();
    }
}
