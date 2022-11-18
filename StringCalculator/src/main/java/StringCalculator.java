public class StringCalculator {
    public int add(String s) {
        if (s.isEmpty()) return 0;
        if (s.contains(",")) {
            String[] nums = s.split(",");
            int tot = 0;
            for (int i = 0; i < nums.length; i++) {
                tot += Integer.parseInt(nums[i]);
            }
            return tot;
        }
        return Integer.parseInt(s);
    }
}
