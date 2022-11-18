public class StringCalculator {
    public int add(String s) {
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
        int tot = 0;
        for (int i = 0; i < nums.length; i++) {
            tot += Integer.parseInt(nums[i]);
        }
        return tot;
    }
}
