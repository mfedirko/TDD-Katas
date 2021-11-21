package StringCalculator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Sum {
    static int getSum(int[] args) {
        checkForNegatives(args);
        return Arrays.stream(args)
                .sum();
    }
    private static void checkForNegatives(int[] args) {
        List<Integer> negatives = new ArrayList<>();
        Arrays.stream(args)
                .filter(a -> a < 0)
                .forEach(negatives::add);
        if (!negatives.isEmpty()) {
            throw new IllegalArgumentException("Negative input not allowed. Found: " + negatives);
        }
    }
}
