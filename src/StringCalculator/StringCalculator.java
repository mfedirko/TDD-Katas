package StringCalculator;

import java.util.Arrays;

public class StringCalculator {
    public int add(String s) {
        String[] args = parseInput(s);
        return getSum(args);
    }

    private String[] parseInput(String s) {
        String delim = "(,|\\n)";
        String nums = s;
        if (s.startsWith("//")) {
            delim = s.substring(2,3);
            nums = s.substring(4);
        }
        return nums.split(delim);
    }

    private int getSum(String[] args) {
        return Arrays.stream(args)
                .filter(a -> !a.isEmpty())
                .mapToInt(Integer::parseInt)
                .sum();
    }
}
