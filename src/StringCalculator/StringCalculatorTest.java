package StringCalculator;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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
        int sum = calc.add("23");

        assertEquals(23, sum);
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

    @Test
    public void negNumThrowsException() {
        try {
            calc.add("1,-3,4,5");
            Assert.fail("Expected exception for negative");
        } catch (Exception ex) {
            assertTrue("Expected exception to contain num -3",
                    ex.getMessage().contains("-3"));
        }
    }

    @Test
    public void whenMultipleNegNums_thenThrowsException() {
        try {
            calc.add("1,-3,-4,-7,5,2,3,-1,5");
            Assert.fail("Expected exception for negative");
        } catch (Exception ex) {
            assertTrue("Expected exception to contain num -3",
                    ex.getMessage().contains("-3"));
            assertTrue("Expected exception to contain num -4",
                    ex.getMessage().contains("-4"));
            assertTrue("Expected exception to contain num -7",
                    ex.getMessage().contains("-7"));
            assertTrue("Expected exception to contain num -1",
                    ex.getMessage().contains("-1"));
        }
    }

    @Test
    public void whenCalledZeroTimes_thenGetCalledCountIs0() {
        int c = calc.getCalledCount();

        assertEquals(0, c);
    }

    @Test
    public void whenCalledOneTimes_thenGetCalledCountIs1() {
        calc.add("");

        int c = calc.getCalledCount();

        assertEquals(1, c);
    }

    @Test
    public void whenCalledNTimes_thenGetCalledCountIsN() {
        final int n = 15;
        for (int i = 0; i < n; i++) {
            calc.add("");
        }

        int c = calc.getCalledCount();

        assertEquals(n, c);
    }

    @Test
    public void whenNumGreaterThan1000_thenIgnore() {
        int sum = calc.add("1,1001,43,2000,29");

        assertEquals(1 + 43 + 29, sum);
    }

    @Test
    public void multiLengthDelimiter() {
        int sum = calc.add("//[***]\n1***2***3");

        assertEquals(1 + 2 + 3, sum);
    }
}
