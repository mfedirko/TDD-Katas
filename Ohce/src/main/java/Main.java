import java.time.Clock;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Ohce ohce = new Ohce(System.out::println, Clock.systemDefaultZone(), getUsername(args));
        processUserInput(ohce);
    }

    private static void processUserInput(Ohce ohce) {
        try (Scanner sc = new Scanner(System.in)) {
            while (sc.hasNextLine() && !ohce.isExited()) {
                ohce.read(sc.nextLine());
            }
        }
    }

    private static String getUsername(String[] args) {
        if (args.length < 1) {
            throw new IllegalArgumentException("Username argument is missing!\nUsage: ohce username");
        }
        return args[0];
    }
}
