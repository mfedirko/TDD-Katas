import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class RomanNumeralsTest {

    @Test
    public void zeroIsEmpty() {
        String rn = new RomanNumeral(0).toString();
        assertEquals("", rn);
    }

    @Test(expected = IllegalArgumentException.class)
    public void boundaryRangeMin() {
        new RomanNumeral(-1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void boundaryRangeMax() {
        new RomanNumeral(4001);
    }

    @Test
    public void one() {
        RomanNumeral one = new RomanNumeral(1);

        assertEquals("I", one.toString());
    }

    @Test
    public void five() {
        RomanNumeral five = new RomanNumeral(5);

        assertEquals("V", five.toString());
    }

    @Test
    public void ten() {
        RomanNumeral ten = new RomanNumeral(10);

        assertEquals("X", ten.toString());
    }

    @Test
    public void allSingleSymbols() {
        List<Integer> baseTen = Arrays.asList(1, 5, 10, 50, 100, 500, 1000);
        List<String> roman = Arrays.asList("I", "V", "X", "L", "C", "D", "M");
        for (int i = 0; i < baseTen.size(); i++) {
            String expected = roman.get(i);
            String actual = new RomanNumeral(baseTen.get(i)).toString();
            assertEquals(expected, actual);
        }
    }

    @Test
    public void twoNumsDesc_15() {
        RomanNumeral fifteen = new RomanNumeral(15);

        assertEquals("XV", fifteen.toString());
    }

    @Test
    public void twoNumsDesc_51() {
        RomanNumeral num = new RomanNumeral(51);

        assertEquals("LI", num.toString());
    }

    @Test
    public void twoNumsDesc_110() {
        RomanNumeral num = new RomanNumeral(110);

        assertEquals("CX", num.toString());
    }

    @Test
    public void twoNumsDesc_1500() {
        RomanNumeral num = new RomanNumeral(1500);

        assertEquals("MD", num.toString());
    }

    @Test
    public void threeNumsDesc_16() {
        RomanNumeral num = new RomanNumeral(16);

        assertEquals("XVI", num.toString());
    }

    @Test
    public void threeNumsDesc_160() {
        RomanNumeral num = new RomanNumeral(160);

        assertEquals("CLX", num.toString());
    }

    @Test
    public void threeNumsDesc_1600() {
        RomanNumeral num = new RomanNumeral(1600);

        assertEquals("MDC", num.toString());
    }

    @Test
    public void threeNumRepeated_7() {
        RomanNumeral num = new RomanNumeral(7);

        assertEquals("VII", num.toString());
    }

    @Test
    public void threeNumRepeated_30() {
        RomanNumeral num = new RomanNumeral(30);

        assertEquals("XXX", num.toString());
    }

    @Test
    public void threeNumRepeated_2100() {
        RomanNumeral num = new RomanNumeral(2100);

        assertEquals("MMC", num.toString());
    }

    @Test
    public void max_4000() {
        RomanNumeral num = new RomanNumeral(4000);

        assertEquals("MMMM", num.toString());
    }

    @Test
    public void substitutionRules_4() {
        RomanNumeral num = new RomanNumeral(4);

        assertEquals("IV", num.toString());
    }

    @Test
    public void substitutionRules_9() {
        RomanNumeral num = new RomanNumeral(9);

        assertEquals("IX", num.toString());
    }

    @Test
    public void substitutionRules_40() {
        RomanNumeral num = new RomanNumeral(40);

        assertEquals("XL", num.toString());
    }

    @Test
    public void generalSubstitutionRules() {
        List<Integer> base10 = Arrays.asList(4, 9, 40, 90, 400, 900);
        List<String> expectedRoman = Arrays.asList("IV", "IX", "XL", "XC", "CD", "CM");

        for (int i = 0; i < base10.size(); i++) {
            RomanNumeral numeral = new RomanNumeral(base10.get(i));
            assertEquals(expectedRoman.get(i), numeral.toString());
        }
    }

    @Test
    public void acceptance() {
        List<Integer> base10 = Arrays.asList(
                2, 3, 4, 5, 6, 7, 8, 9,
                39, 40, 41, 42, 43, 44, 45, 46, 47, 48,
                24, 59, 142, 193, 424, 678, 972, 1474, 2598, 3999);
        List<String> expectedRoman = Arrays.asList(
                "II", "III", "IV", "V", "VI", "VII", "VIII", "IX",
                "XXXIX", "XL", "XLI", "XLII", "XLIII", "XLIV", "XLV", "XLVI", "XLVII", "XLVIII",
                "XXIV", "LIX", "CXLII", "CXCIII", "CDXXIV", "DCLXXVIII", "CMLXXII", "MCDLXXIV", "MMDXCVIII", "MMMCMXCIX");

        for (int i = 0; i < base10.size(); i++) {
            RomanNumeral numeral = new RomanNumeral(base10.get(i));
            assertEquals(expectedRoman.get(i), numeral.toString());
        }
    }

}
