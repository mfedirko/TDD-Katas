package StringCalculator;

import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class StringCalculatorTest {
    private StringCalculator calculator = new StringCalculator();

    @Test
    public void whenEmpty_returnZero() {
        final String input = "";

        int res = calculator.add(input);

        assertEquals(0, res);
    }

    @Test
    public void whenSingleNumber_returnSame() {
        final String input = "5";

        int res = calculator.add(input);

        assertEquals(5, res);
    }

    @Test
    public void whenSingleNumber_returnSame2() {
        final String input = "523";

        int res = calculator.add(input);

        assertEquals(523, res);
    }

    @Test
    public void whenTwoNumbers_thenSum() {
        final String input = "1,2";

        int res = calculator.add(input);

        assertEquals(3, res);
    }

    @Test
    public void whenThreeNumbers_thenSum() {
        final String input = "1,2,5";

        int res = calculator.add(input);

        assertEquals(1 + 2 + 5, res);
    }

    @Test
    public void whenFourNumbers_thenSum() {
        final String input = "1,2,5,6";

        int res = calculator.add(input);

        assertEquals(1 + 2 + 5 + 6, res);
    }

    @Test
    public void whenAnyAmountOfNumbers_thenSum() {
        final String input = "1,2,5,6,32,2,45,45,45,34,23,1,2";

        int res = calculator.add(input);

        assertEquals(1 + 2 + 5 + 6 + 32 + 2 + 45 + 45 + 45 + 34 + 23 + 1 + 2, res);
    }

    @Test
    public void newLinesBetweenNums() {
        final String input = "1\n2\n5\n6";

        int res = calculator.add(input);

        assertEquals(1 + 2 + 5 + 6, res);
    }

    @Test
    public void mixedCommasAndNewLinesBetweenNums() {
        final String input = "1,2\n5,6";

        int res = calculator.add(input);

        assertEquals(1 + 2 + 5 + 6, res);
    }

    @Test
    public void customDelimiter() {
        final String input = "//;\n1;2;3;5;6";

        int res = calculator.add(input);

        assertEquals(1 + 2 + 3 + 5 + 6, res);
    }

    @Test
    public void boxCustomDelimiter() {
        final String input = "//[***]\n1***2***3***5***6";

        int res = calculator.add(input);

        assertEquals(1 + 2 + 3 + 5 + 6, res);
    }

    @Test
    public void multipleDifferentBoxCustomDelimiters() {
        final String input = "//[*][%]\n1*2%3%5*6";

        int res = calculator.add(input);

        assertEquals(1 + 2 + 3 + 5 + 6, res);
    }

    @Test
    public void multipleDifferentMultiCharBoxCustomDelimiters() {
        final String input = "//[***][%%]\n1***2%%3%%5***6";

        int res = calculator.add(input);

        assertEquals(1 + 2 + 3 + 5 + 6, res);
    }

    @Test
    public void unusedBoxCustomDelimitersOk() {
        final String input = "//[***][;][%%]\n1;2;3;5;6";

        int res = calculator.add(input);

        assertEquals(1 + 2 + 3 + 5 + 6, res);
    }

    @Test
    public void newlineInCustomBoxDelimiter() {
        final String input = "//[,][\n]\n1,2,3\n4,5";

        int res = calculator.add(input);

        assertEquals(1 + 2 + 3 + 4 + 5, res);
    }

    @Test
    public void newlineInCustomNonboxDelimiter() {
        final String input = "//\n\n1\n2\n3\n4\n5";

        int res = calculator.add(input);

        assertEquals(1 + 2 + 3 + 4 + 5, res);
    }

    @Test
    public void whenNegativeNum_throwsException() {
        final String input = "1,2,-3,4,5";

        try {
            calculator.add(input);
            Assert.fail("Negative number exception expected");
        } catch (IllegalArgumentException ex) {
            String msg = ex.getMessage();
            assertThat(msg, allOf(containsString("negative"), containsString("-3")));
        }
    }

    @Test
    public void whenMultipleNegativeNums_exceptionListsAll() {
        final String input = "1,2,-3,-4,2,5,6,-5,2,8,8,2,5,-9,6";

        try {
            calculator.add(input);
            Assert.fail("Negative number exception expected");
        } catch (IllegalArgumentException ex) {
            String msg = ex.getMessage();
            assertThat(msg,
                    allOf(
                            containsString("negative"),
                            containsString("-3"),
                            containsString("-4"),
                            containsString("-5"),
                            containsString("-9")));
        }
    }


    @Test
    public void ignoreNumsGreaterThan1000() {
        final String input = "1,2\n5000,6,1002,3";

        int res = calculator.add(input);

        assertEquals(1 + 2 + 6 + 3, res);
    }

    // called count
    @Test
    public void initialCalledCountIsZero() {
        int cnt = calculator.getCalledCount();

        assertEquals(0, cnt);
    }

    @Test
    public void calledCount() {
        final int expCount = 35;
        for (int i = 0; i < expCount; i++) {
            calculator.add("");
        }

        int cnt = calculator.getCalledCount();

        assertEquals(expCount, cnt);
    }


}
