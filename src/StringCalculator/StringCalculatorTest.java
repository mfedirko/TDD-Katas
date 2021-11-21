package StringCalculator;

import org.junit.Test;

public class StringCalculatorTest {
    private StringCalculator calc = new StringCalculator();

    @Test
    public void nothing() {
        int sum = calc.add("");
    }
}
