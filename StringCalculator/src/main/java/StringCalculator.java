public class StringCalculator {

    private int calledCount;

    public int add(String s) {
        calledCount++;
        int[] args = new InputParser(s).getNumberArgs();
        return new Summation(args).getSum();
    }


    public int getCalledCount() {
        return calledCount;
    }
}
