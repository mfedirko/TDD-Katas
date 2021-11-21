package StringCalculator;

public class StringCalculator {
    public int add(String s) {
        if ("".equals(s)) {
            return 0;
        }
        String[] args = s.split(",");
        if (args.length == 1) {
            int num1 = Integer.parseInt(args[0]);
            return num1;
        }
        if (args.length == 2) {
            int num1 = Integer.parseInt(args[0]);
            int num2 = Integer.parseInt(args[1]);
            return num1 + num2;
        }
        if (args.length == 3) {
            int num1 = Integer.parseInt(args[0]);
            int num2 = Integer.parseInt(args[1]);
            int num3 = Integer.parseInt(args[2]);
            return num1 + num2 + num3;
        }
        return 0;
    }
}
