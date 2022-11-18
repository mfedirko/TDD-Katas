import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class StringCalculator {
    public static final String DEFAULT_DELIMITER = ",|\n";
    private int calledCount;

    public int getCalledCount() {
        return calledCount;
    }

    public int add(String s) {
        calledCount++;
        if (s.isEmpty()) return 0;
        String nums = removeDelimiterPrefix(s);
        Pattern delimiterPattern = getDelimiter(s);
        return sum(nums, delimiterPattern);

    }

    private Pattern getDelimiter(String s) {
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

    private String removeDelimiterPrefix(String s) {
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

    private int sum(String s, Pattern separator) {
        String[] nums = separator.split(s);
        checkForNegatives(nums);

        return getNumStream(nums)
                .filter(n -> n < 1001)
                .reduce(0, Integer::sum);
    }

    private void checkForNegatives(String[] nums) {
        List<Integer> negatives = findNegatives(nums);
        if (!negatives.isEmpty()) {
            throw new IllegalArgumentException("Negative number: " + negatives);
        }
    }

    private List<Integer> findNegatives(String[] nums) {
        return getNumStream(nums)
                .filter(n -> n < 0)
                .boxed()
                .collect(Collectors.toList());
    }

    private IntStream getNumStream(String[] nums) {
        return Arrays.stream(nums)
                .mapToInt(Integer::parseInt);
    }
}
