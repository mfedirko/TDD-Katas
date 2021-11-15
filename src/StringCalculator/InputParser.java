package StringCalculator;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class InputParser {
    public static final String DEFAULT_DELIMITER = "(,|\n)";
    private static final String DELIMITER_GROUP = "delim";
    private static final String NUMBERS_GROUP = "nums";
    private static final String BOX_DELIMITER_GROUP = "box";
    /*
     pattern for single-character delimiter (without box brackets)
      Ex: //;\n1;2;3;4
     */
    private static final Pattern CUSTOM_DELIMITER_PATTERN_NONBOX = Pattern.compile("^//(?<delim>.)\n(?<nums>.*)", Pattern.DOTALL);
    /*
       patterns for delimiter within box brackets: []
        Ex: //[***]\n1***2***4***9
       also supports multiple delimiters
        Ex: //[%%%][**][----][;]\n1**2**3%%%4;2;9
     */
    private static final Pattern INDIVIDUAL_BOX_DELIMITER_PATTERN = Pattern.compile("\\[(?<box>[^\\[]+)\\]");
    private static final Pattern CUSTOM_DELIMITER_PATTERN_BOX = Pattern.compile("^//(?<delim>(" + INDIVIDUAL_BOX_DELIMITER_PATTERN.pattern() + ")+)\n(?<nums>.*)", Pattern.DOTALL);

    private final String origInput;
    private Matcher delimMatcher;
    private boolean isBoxBracketDelimiter;

    InputParser(String input) {
        this.origInput = input;
    }

    int[] getNumberArgs() {
        if ("".equals(origInput)) {
            return new int[]{0};
        }
        String numberInput = getNumberInput();
        String delimiter = getDelimiter();
        return Arrays.stream(numberInput.split(delimiter))
                .mapToInt(Integer::parseInt)
                .toArray();
    }
    private String getNumberInput() {
        if (hasCustomDelimiter(origInput)) {
            return removeDelimiterPrefix(origInput);
        } else {
            return origInput;
        }
    }

    private String getDelimiter() {
        if (hasCustomDelimiter(origInput)) {
            return extractCustomDelimiter(origInput);
        } else {
            return DEFAULT_DELIMITER;
        }
    }

    private String removeDelimiterPrefix(String s) {
        Matcher matcher = getCustomDelimMatcher(s);
        if (!matcher.matches()) throw new IllegalStateException("No custom delimiter");
        return matcher.group(NUMBERS_GROUP);
    }

    private String extractCustomDelimiter(String s) {
        Matcher matcher = getCustomDelimMatcher(s);
        if (!matcher.matches()) throw new IllegalStateException("No custom delimiter");
        String delimiter = matcher.group(DELIMITER_GROUP);
        if (isBoxBracketDelimiter) {
            return extractBoxDelimiterPattern(delimiter);
        } else {
            return Pattern.quote(delimiter);
        }
    }

    private String extractBoxDelimiterPattern(String delimiter) {
        Matcher multipleDelimMatcher = INDIVIDUAL_BOX_DELIMITER_PATTERN.matcher(delimiter);
        if (multipleDelimMatcher.find()) {
            StringBuilder sb = new StringBuilder("(");
            sb.append(Pattern.quote(multipleDelimMatcher.group(BOX_DELIMITER_GROUP)));
            while (multipleDelimMatcher.find()) {
                sb.append("|");
                sb.append(Pattern.quote(multipleDelimMatcher.group(BOX_DELIMITER_GROUP)));
            }
            sb.append(")");
            return sb.toString();
        } else {
            throw new IllegalStateException("Expected delimiter in box brackets format but not found. Ex: //[%]\n1%2%3");
        }
    }


    private boolean hasCustomDelimiter(String s) {
        return getCustomDelimMatcher(s).matches();
    }

    private Matcher getCustomDelimMatcher(String s) {
        if (delimMatcher == null) {
            delimMatcher = CUSTOM_DELIMITER_PATTERN_NONBOX.matcher(s);
            if (!delimMatcher.matches()) {
                delimMatcher = CUSTOM_DELIMITER_PATTERN_BOX.matcher(s);
                isBoxBracketDelimiter = true;
            }
        }
        return delimMatcher;
    }
}