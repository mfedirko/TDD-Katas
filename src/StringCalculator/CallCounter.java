package StringCalculator;

class CallCounter {
    private int calledCount = 0;

    int getCalledCount() {
        return calledCount;
    }

    void increment() {
        calledCount++;
    }
}
