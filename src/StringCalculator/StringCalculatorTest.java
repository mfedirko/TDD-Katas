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
    @Test
    public void when1_ret1() {
        int sum = calc.add("1");

        assertEquals(1, sum);
    }
    @Test
    public void when2_ret2() {
        int sum = calc.add("2");

        assertEquals(2, sum);
    }
}
