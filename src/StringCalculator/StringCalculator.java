package StringCalculator;

public class StringCalculator {
    private CallCounter callCounter = new CallCounter();

    public int add(String s) {
        callCounter.increment();
        return Sum.getSum(InputParser.parseInput(s));
    }

    public int getCalledCount() {
        return callCounter.getCalledCount();
    }

}
