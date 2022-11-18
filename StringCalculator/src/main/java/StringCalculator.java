import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collector;
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
            return sum(nums, delimiter);
        }
        return sum(s, ",|\n");
    }

    private int sum(String s, String separator) {
        String[] nums = s.split(separator);
        checkForNegatives(nums);

        return getNumStream(nums).reduce(0, Integer::sum);
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
