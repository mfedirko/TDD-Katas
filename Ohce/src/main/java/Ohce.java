import java.time.Clock;
import java.util.function.Consumer;

public class Ohce {
    private final Consumer<String> output;
    private final Clock clock;
    private final String user;

    private boolean exited;

    public Ohce(Consumer<String> output, Clock clock, String user) {
        this.output = output;
        this.clock = clock;
        this.user = user;
        printGreeting();
    }

    private void printGreeting() {
        int hour = clock.instant().atZone(clock.getZone()).getHour();
        final String greeting = getGreeting(hour);
        print(greeting);
    }

    private String getGreeting(int hour) {
        final String greeting;
        if (isNightTime(hour)) {
            greeting = "¡Buenas noches " + user + "!";
        } else if (isDayTime(hour)) {
            greeting = "¡Buenos días " + user + "!";
        } else { // evening
            greeting = "¡Buenas tardes " + user + "!";
        }
        return greeting;
    }

    private boolean isDayTime(int hour) {
        return hour >= 6 && hour < 12;
    }

    private boolean isNightTime(int hour) {
        return hour >= 20 || hour < 6;
    }

    public void read(String line) {
        if (isExited()) {
            return;
        }
        if (line.isEmpty()) {
            return;
        }
        if (isExitCommand(line)) {
            exit();
            return;
        }

        String reversed = reverse(line);
        print(reversed);
        if (isPalindrome(line)) {
            print("¡Bonita palabra!");
        }
    }

    private void exit() {
        print("Adios " + user);
        this.exited = true;
    }

    private boolean isExitCommand(String line) {
        return "Stop!".equals(line);
    }

    private boolean isPalindrome(String line) {
        return reverse(line).equals(line);
    }

    private String reverse(String line) {
        return new StringBuilder(line).reverse().toString();
    }

    private void print(String line) {
        output.accept(line);
    }

    public boolean isExited() {
        return exited;
    }
}
