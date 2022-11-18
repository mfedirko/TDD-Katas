import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StringCalculatorTest {

    @Test
    void canAdd() {
        StringCalculator calculator = new StringCalculator();
        int res = calculator.add("");
    }

    @Test
    void sum0_forEmptyStr() {
        StringCalculator calculator = new StringCalculator();

        int res = calculator.add("");

        assertEquals(0, res);
    }

}
