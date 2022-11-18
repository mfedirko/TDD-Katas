public class StringCalculator {
    public int add(String s) {
        if (s.isEmpty()) return 0;
        if (s.contains(",")) {
            String[] nums = s.split(",");
            return Integer.parseInt(nums[0]) + Integer.parseInt(nums[1]);
        }
        return Integer.parseInt(s);
    }
}
