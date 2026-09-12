public class Main {

    public static void main(String[] args) {

        String input =
            "let a = 10 + 20 * 3; " +
            "let b = 100 / 5; " +
            "print a + b;";

        Parser p = new Parser(input.getBytes());

        p.parse();
        System.out.println(p.output());

        Interpretador i = new Interpretador(p.output());
        i.run();
    }
}
