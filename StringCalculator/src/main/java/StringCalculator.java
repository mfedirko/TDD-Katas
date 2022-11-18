public class StringCalculator {
    public int add(String s) {
        if (s.isEmpty()) return 0;
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
