package StringCalculator;

public class StringCalculator {

    private int calledCount;

    public int add(String s) {
        calledCount++;
        if ("".equals(s)) {
            return 0;
        }
        int[] args = new InputParser(s).getNumberArgs();
        return new Summation().getSum(args);
    }


    public int getCalledCount() {
        return calledCount;
    }
}
