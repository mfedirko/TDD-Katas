package StringCalculator;

import java.util.Arrays;

public class StringCalculator {
    public int add(String s) {
        String[] args = parseInput(s);
        return getSum(args);
    }

    private int getSum(String[] args) {
        return Arrays.stream(args)
                .filter(a -> !a.isEmpty())
                .mapToInt(Integer::parseInt)
                .sum();
    }

    private String[] parseInput(String s) {
        return s.split("(,|\\n)");
    }
}
