package StringCalculator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StringCalculator {
    public int add(String s) {
        return getSum(parseInput(s));
    }

    private int[] parseInput(String s) {
        String delim = "(,|\\n)";
        String nums = s;
        if (s.startsWith("//")) {
            delim = s.substring(2,3);
            nums = s.substring(4);
        }
        return Arrays.stream(nums.split(delim))
                .filter(a -> !a.isEmpty())
                .mapToInt(Integer::parseInt)
                .toArray();
    }

    private int getSum(int[] args) {
        checkForNegatives(args);
        return Arrays.stream(args)
                .sum();
    }

    private void checkForNegatives(int[] args) {
        List<Integer> negatives = new ArrayList<>();
        Arrays.stream(args)
                .filter(a -> a < 0)
                .forEach(negatives::add);
        if (!negatives.isEmpty()) {
            throw new IllegalArgumentException("Negative input not allowed. Found: " + negatives);
        }
    }

    public int getCalledCount() {
        return 0;
    }
}
