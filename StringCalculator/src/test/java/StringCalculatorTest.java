import org.junit.jupiter.api.Assertions;
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
    void sumForLiteral() {
        int res = calculator.add("5");

        assertEquals(5, res);
    }

}
