import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class StringCalculator {
    private int calledCount;

    public int getCalledCount() {
        return calledCount;
    }

    public int add(String s) {
        calledCount++;
        if (s.isEmpty()) return 0;
        if (s.startsWith("//")) {
            final int prefixLength = 4;
            final String nums = s.substring(prefixLength);
            final String delimiter = s.substring(2, 3);
            return sum(nums, Pattern.compile(Pattern.quote(delimiter)));
        }
        return sum(s, Pattern.compile(",|\n"));
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
