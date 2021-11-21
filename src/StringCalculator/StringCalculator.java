package StringCalculator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
        List<Integer> negatives = new ArrayList<>();
        int sum = Arrays.stream(args)
                .filter(a -> !a.isEmpty())
                .mapToInt(Integer::parseInt)
                .peek(a -> {
                    if (a < 0) {
                        negatives.add(a);
                    }
                })
                .sum();
        if (!negatives.isEmpty()) {
            throw new IllegalArgumentException("Negative input not allowed. Found: " + negatives);
        }
        return sum;
    }
}
