import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class StringCalculator {
    private int calledCount;
    private final DelimiterParser delimiterParser;

    public StringCalculator() {
        delimiterParser = new DelimiterParser();
    }

    public int getCalledCount() {
        return calledCount;
    }

    public int add(String s) {
        calledCount++;
        if (s.isEmpty()) return 0;
        String nums = delimiterParser.removeDelimiterPrefix(s);
        Pattern delimiterPattern = delimiterParser.getDelimiter(s);
        return sum(nums, delimiterPattern);

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
