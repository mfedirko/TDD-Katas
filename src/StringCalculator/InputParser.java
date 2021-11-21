package StringCalculator;

import java.util.Arrays;

class InputParser {
    static int[] parseInput(String s) {
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
}
