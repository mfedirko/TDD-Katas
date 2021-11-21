package StringCalculator;

public class StringCalculator {
    public int add(String s) {
        if ("".equals(s)) {
            return 0;
        }
        String[] args = s.split(",");
        int res = 0;
        for (String arg : args) {
            res += Integer.parseInt(arg);
        }
        return res;
    }
}
