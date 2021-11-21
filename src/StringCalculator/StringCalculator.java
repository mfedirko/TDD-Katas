package StringCalculator;

public class StringCalculator {
    public int add(String s) {
        if (!"".equals(s)) {
            return Integer.parseInt(s);
        }
        return 0;
    }
}
