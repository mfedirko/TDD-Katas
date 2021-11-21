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
        return toNumArray(getDelimiter(), getNumbersPart());
    }

    private String getNumbersPart() {
        if (hasCustomDelimiter(input)) {
            return input.substring(1 + getEndOfDelimiter());
        } else {
            return input;
        }
    }

    private int getEndOfDelimiter() {
        return input.indexOf(CUSTOM_DELIMITER_SUFFIX);
    }

    private int getStartOfDelimiter() {
        return CUSTOM_DELIMITER_PREFIX.length();
    }

    private String getDelimiter() {
        if (hasCustomDelimiter(input)) {
            String delimiterPart = input.substring(getStartOfDelimiter(), getEndOfDelimiter());
            return delimiterPart.replace("][", "]|[");
        } else {
            return DEFAULT_DELIMITER;
        }
    }

    private int[] toNumArray(String delim, String numPart) {
        return Arrays.stream(numPart.split(delim))
                .filter(a -> !a.isEmpty())
                .mapToInt(Integer::parseInt)
                .toArray();
    }

    private static boolean hasCustomDelimiter(String s) {
        return s.startsWith(CUSTOM_DELIMITER_PREFIX);
    }
}
