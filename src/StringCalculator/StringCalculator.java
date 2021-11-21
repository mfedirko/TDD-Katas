package StringCalculator;

public class StringCalculator {
    public int add(String s) {
        if (s.contains(",")) {
            String[] args = s.split(",");
            return Integer.parseInt(args[0]) + Integer.parseInt(args[1]);
        }
        if (!"".equals(s)) {
            return Integer.parseInt(s);
        }
        return 0;
    }
}
