import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StringCalculatorTest {
    StringCalculator calculator = new StringCalculator();

    @Test
    void sum0_forEmptyStr() {
        int res = calculator.add("");

        assertEquals(0, res);
    }

    @Test
    void sumForSingleNumber() {
        int res = calculator.add("5");

        assertEquals(5, res);
    }

    @Test
    void sumForMultipleNumbers() {
        int res = calculator.add("2,4,6,8,10,12,24");

        assertEquals(2+4+6+8+10+12+24, res);
    }

    @Test
    void newlinesBetweenNumber() {
        int res = calculator.add("2\n4\n6\n8\n10\n12\n24");

        assertEquals(2+4+6+8+10+12+24, res);
    }

    @Test
    void newlinesAndCommasBetweenNumber() {
        int res = calculator.add("2\n4,6,8\n10,12\n24");

        assertEquals(2+4+6+8+10+12+24, res);
    }

    @Test
    void customDelimiter() {
        int res = calculator.add("//;\n2;4;6;8;10;12;24");

        assertEquals(2+4+6+8+10+12+24, res);
    }

    @Test
    void customDelimiterOfAsterisk() {
        int res = calculator.add("//*\n1*2*3");

        assertEquals(6, res);
    }

    @Test
    void customDelimiterOfNewline() {
        int res = calculator.add("//\n\n1\n2\n3");

        assertEquals(6, res);
    }

    @Test
    void negativeNumberNotAllowed() {
        Assertions.assertThatThrownBy(() -> calculator.add("1,-2,3"))
                .hasMessageContaining("-2");
    }

    @Test
    void multipleNegativeNumbersNotAllowed() {
        Assertions.assertThatThrownBy(() -> calculator.add("1,-2,5,-9,3"))
                .hasMessageContainingAll("-2", "-9");
    }

    @Test
    void whenNotCalled_thenCalledCountIsZero() {
        int times = calculator.getCalledCount();

        assertEquals(0, times);
    }

    @Test
    void whenCalled_thenCalledCountIsIncremented() {
        calculator.add("//;\n2;4;6;8;10;12;24");
        calculator.add("1,2,3");
        int times = calculator.getCalledCount();

        assertEquals(2, times);
    }

    @Test
    void whenNumbersGreaterThan1000_thenIgnored() {
        int res = calculator.add("1,1021,2,3");

        assertEquals(6, res);
    }

    @Test
    @Disabled
    void threeCharDelimiter() {
        int res = calculator.add("//[***]\n1***2***3");

        assertEquals(6, res);
    }

    @Test
    @Disabled
    void fourCharDelimiter() {
        int res = calculator.add("//[****]\n1****2****3");

        assertEquals(6, res);
    }
}
