package StringCalculator;

import java.util.Arrays;

class InputParser {
    private static final String DEFAULT_DELIMITER = "(,|\\n)";

    private final String s;

    InputParser(String s) {
        this.s = s;
    }

    int[] parse() {
        String delim = DEFAULT_DELIMITER;
        String numPart = s;
        if (hasCustomDelimiter(s)) {
            final int endOfDelim = s.indexOf('\n');
            delim = s.substring(2,endOfDelim);
            numPart = s.substring(1 + endOfDelim);
        }
        return getNumbers(delim, numPart);
    }

    private int[] getNumbers(String delim, String numPart) {
        return Arrays.stream(numPart.split(delim))
                .filter(a -> !a.isEmpty())
                .mapToInt(Integer::parseInt)
                .toArray();
    }

    private static boolean hasCustomDelimiter(String s) {
        return s.startsWith("//");
    }
}
