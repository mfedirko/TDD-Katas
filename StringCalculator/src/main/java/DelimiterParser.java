import java.util.regex.Matcher;
import java.util.regex.Pattern;

class DelimiterParser {
    public static final String DEFAULT_DELIMITER = ",|\n";

    public Pattern getDelimiter(String s) {
        Pattern delimiterPattern;
        if (s.startsWith("//[")) {
            delimiterPattern = getCustomMultiDelimiterPattern(s);
        } else if (s.startsWith("//")) {
            delimiterPattern = getCustomSingleDelimiterPattern(s);
        } else {
            delimiterPattern = Pattern.compile(DEFAULT_DELIMITER);
        }
        return delimiterPattern;
    }


    public String removeDelimiterPrefix(String s) {
        String nums;
        if (s.startsWith("//[")) {
            nums = s.substring(s.indexOf("\n") + 1);
        } else if (s.startsWith("//")) {
            nums = s.substring(4);
        } else {
            nums = s;
        }
        return nums;
    }

    private Pattern getCustomMultiDelimiterPattern(String s) {
        Pattern delimiterPattern;
        Pattern delimiterGroupPattern = Pattern.compile("\\[(?<delim>[^]]+)]");
        Matcher delimGroupMatcher = delimiterGroupPattern.matcher(s);
        StringBuilder delimBuilder = new StringBuilder();
        while (delimGroupMatcher.find()) {
            String delim = delimGroupMatcher.group("delim");
            if (delimBuilder.length() > 0) delimBuilder.append("|");
            delimBuilder.append(Pattern.quote(delim));
        }
        delimiterPattern = Pattern.compile(delimBuilder.toString());
        return delimiterPattern;
    }

    private Pattern getCustomSingleDelimiterPattern(String s) {
        Pattern delimiterPattern;
        final String delimiter = s.substring(2, 3);
        delimiterPattern = Pattern.compile(Pattern.quote(delimiter));
        return delimiterPattern;
    }
}
