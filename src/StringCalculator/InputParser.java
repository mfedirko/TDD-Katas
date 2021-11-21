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
        return getNumbers(getDelimiter(), getNumbersPart());
    }

    private String getNumbersPart() {
        if (hasCustomDelimiter(input)) {
            return input.substring(1 + getEndOfDelimeter());
        } else {
            return input;
        }
    }

    private int getEndOfDelimeter() {
        return input.indexOf(CUSTOM_DELIMITER_SUFFIX);
    }

    private int getStartOfDelimiter() {
        return CUSTOM_DELIMITER_PREFIX.length();
    }

    private String getDelimiter() {
        if (hasCustomDelimiter(input)) {
            return input.substring(getStartOfDelimiter(), getEndOfDelimeter());
        } else {
            return DEFAULT_DELIMITER;
        }
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
