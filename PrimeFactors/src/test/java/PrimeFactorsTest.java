import org.hamcrest.CoreMatchers;
import org.junit.Test;

import java.util.*;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

public class PrimeFactorsTest {
    private PrimeFactors pf;

    @Test
    public void factorsOf1() {
        PrimeFactors factors = new PrimeFactors(1);

        List<Integer> res = factors.getFactors();

        assertEquals(0, res.size());
    }

    @Test
    public void factorsOf2() {
        PrimeFactors factors = new PrimeFactors(2);

        List<Integer> res = factors.getFactors();

        assertConsistsOf(res, 2);
    }

    @Test
    public void factorsOf3() {
        PrimeFactors factors = new PrimeFactors(3);

        List<Integer> res = factors.getFactors();

        assertConsistsOf(res, 3);
    }

    @Test
    public void factorsOf4() {
        PrimeFactors factors = new PrimeFactors(4);

        List<Integer> res = factors.getFactors();

        assertConsistsOf(res, 2, 2);
    }

    @Test
    public void factorsOf6() {
        PrimeFactors factors = new PrimeFactors(6);

        List<Integer> res = factors.getFactors();

        assertConsistsOf(res, 2, 3);
    }

    @Test
    public void factorsOf8() {
        PrimeFactors factors = new PrimeFactors(8);

        List<Integer> res = factors.getFactors();

        assertConsistsOf(res, 2, 2, 2);
    }

    @Test
    public void factorsOf9() {
        PrimeFactors factors = new PrimeFactors(9);

        List<Integer> res = factors.getFactors();

        assertConsistsOf(res, 3, 3);
    }

    @Test
    public void factorsOf18() {
        PrimeFactors factors = new PrimeFactors(18);

        List<Integer> res = factors.getFactors();

        assertConsistsOf(res, 2, 3, 3);
    }

    @Test
    public void factorsOf25() {
        PrimeFactors factors = new PrimeFactors(25);

        List<Integer> res = factors.getFactors();

        assertConsistsOf(res, 5, 5);
    }

    @Test
    public void factorsOf64() {
        PrimeFactors factors = new PrimeFactors(64);

        List<Integer> res = factors.getFactors();

        assertConsistsOf(res, 2, 2, 2, 2, 2, 2);
    }

    @Test
    public void factorsOf74() {
        PrimeFactors factors = new PrimeFactors(74);

        List<Integer> res = factors.getFactors();

        assertConsistsOf(res, 2, 37);
    }

    @Test
    public void factorsOf1_000_000() {
        PrimeFactors factors = new PrimeFactors(1_000_000);

        List<Integer> res = factors.getFactors();

        assertConsistsOf(res, 2, 5, 2, 5, 2, 5, 2, 5, 2, 5, 2, 5);
    }
    @Test
    public void largePrime_15_485_863() {
        PrimeFactors factors = new PrimeFactors(15_485_863);

        List<Integer> res = factors.getFactors();

        assertConsistsOf(res, 15_485_863);

    }

    private void assertConsistsOf(List<Integer> list, Integer... items) {
        List<Integer> exp = Arrays.asList(items);
        exp.sort(Comparator.naturalOrder());
        list.sort(Comparator.naturalOrder());
        assertEquals(exp, list);
    }

}
