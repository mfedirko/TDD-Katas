package StringCalculator;

import java.util.Arrays;

public class StringCalculator {
    public int add(String s) {
        String[] args = s.split("(,|\\n)");
        return Arrays.stream(args)
                .filter(a -> !a.isEmpty())
                .mapToInt(Integer::parseInt)
                .sum();
    }
}
