package StringCalculator;

public class StringCalculator {
    private CallCounter callCounter = new CallCounter();

    public int add(String s) {
        callCounter.increment();
        return Sum.of(new InputParser(s).parse());
    }

    public int getCalledCount() {
        return callCounter.getCalledCount();
    }

}
