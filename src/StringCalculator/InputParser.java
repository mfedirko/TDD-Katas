package StringCalculator;

import java.util.Arrays;

class InputParser {
    static int[] parseInput(String s) {
        String delim = "(,|\\n)";
        String nums = s;
        if (s.startsWith("//")) {
            final int endOfDelim = s.indexOf('\n');
            delim = s.substring(2,endOfDelim);
            nums = s.substring(1 + endOfDelim);
        }
        return Arrays.stream(nums.split(delim))
                .filter(a -> !a.isEmpty())
                .mapToInt(Integer::parseInt)
                .toArray();
    }
}
