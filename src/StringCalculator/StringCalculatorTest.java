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

    @Test
    public void whenN_retN() {
        int sum = calc.add("4324");

        assertEquals(4324, sum);
    }

    @Test
    public void when2Args_retSum() {
        int sum = calc.add("1,2");

        assertEquals(3, sum);
    }

    @Test
    public void when2Args_retSum_2() {
        int sum = calc.add("0,5");

        assertEquals(5, sum);
    }

    @Test
    public void when3Args_retSum() {
        int sum = calc.add("1,5,7");

        assertEquals(1 + 5 + 7, sum);
    }

    @Test
    public void when4Args_retSum() {
        int sum = calc.add("1,5,7,143");

        assertEquals(1 + 5 + 7 + 143, sum);
    }

    @Test
    public void whenNArgs_retSum() {
        int sum = calc.add("5,23,56,21,645,43");

        assertEquals(5 + 23 + 56 + 21 + 645 + 43, sum);
    }

    @Test
    public void whenSplitByNewline_retSum() {
        int sum = calc.add("5\n23\n56\n21\n645\n43");

        assertEquals(5 + 23 + 56 + 21 + 645 + 43, sum);
    }

    @Test
    public void whenSplitByMixedNewlineComma_retSum() {
        int sum = calc.add("5\n23,56\n21,645\n43");

        assertEquals(5 + 23 + 56 + 21 + 645 + 43, sum);
    }

    @Test
    public void customDelimiter() {
        int sum = calc.add("//;\n1;2");
        assertEquals(1 + 2, sum);
    }
}
