package StringCalculator;

import java.util.Arrays;

public class Summation {

    public static final int MAX_NUM = 999;

    int getSum(int[] args) {
        checkForNegatives(args);
        return Arrays.stream(args)
                .filter(num -> num <= MAX_NUM)
                .sum();
    }

    private void checkForNegatives(int[] args) {
        int[] negatives = Arrays.stream(args)
                .filter(num -> num < 0)
                .toArray();
        if (negatives.length > 0) {
            throw buildNegativesException(negatives);
        }
    }

    private RuntimeException buildNegativesException(int[] negs) {
        StringBuilder msg = new StringBuilder("negatives not allowed: ");
        for (int i = 0, negsLength = negs.length; i < negsLength; i++) {
            int neg = negs[i];
            msg.append(neg);
            if (i < negsLength - 1) {
                msg.append(", ");
            }
        }
        return new IllegalArgumentException(msg.toString());
    }

}