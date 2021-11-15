package StringCalculator;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class StringCalculatorTest {
    private StringCalculator calculator = new StringCalculator();

    @Test
    public void nothing() {
        int res = calculator.add("");
    }

    @Test
    public void whenEmpty_returnZero() {
        final String input = "";

        int res = calculator.add(input);

        assertEquals(0, res);
    }
}
