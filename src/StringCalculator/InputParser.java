package StringCalculator;

import java.util.Arrays;

class InputParser {
    private static final String CUSTOM_DELIMITER_PREFIX = "//";
    private static final char CUSTOM_DELIMITER_SUFFIX = '\n';
    private static final String DEFAULT_DELIMITER = "(,|\\n)";

    private final String input;

    InputParser(String input) {
        this.input = input;
    }

    int[] parse() {
        String delim = DEFAULT_DELIMITER;
        String numPart = input;
        if (hasCustomDelimiter(input)) {
            final int startOfDelim = CUSTOM_DELIMITER_PREFIX.length();
            final int endOfDelim = input.indexOf(CUSTOM_DELIMITER_SUFFIX);
            delim = input.substring(startOfDelim, endOfDelim);
            numPart = input.substring(1 + endOfDelim);
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
        return s.startsWith(CUSTOM_DELIMITER_PREFIX);
    }
}
