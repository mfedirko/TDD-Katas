package StringCalculator;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class StringCalculatorTest {
    private StringCalculator calc = new StringCalculator();

    @Test
    public void whenEmptyStr_ret0() {
        int sum = calc.add("");

        assertEquals(0, sum);
    }
}
