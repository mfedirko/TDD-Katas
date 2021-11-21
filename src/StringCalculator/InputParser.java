package StringCalculator;

import java.util.Arrays;

class InputParser {
    private final String s;

    InputParser(String s) {
        this.s = s;
    }

    int[] parse() {
        String delim = "(,|\\n)";
        String nums = s;
        if (hasCustomDelimiter(s)) {
            final int endOfDelim = s.indexOf('\n');
            delim = s.substring(2,endOfDelim);
            nums = s.substring(1 + endOfDelim);
        }
        return Arrays.stream(nums.split(delim))
                .filter(a -> !a.isEmpty())
                .mapToInt(Integer::parseInt)
                .toArray();
    }

    private static boolean hasCustomDelimiter(String s) {
        return s.startsWith("//");
    }
}
