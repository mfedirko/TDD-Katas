package FizzBuzz;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class FizzBuzzTest {
    private FizzBuzz fb;
    private List<String> lines;

    @Before
    public void setup() {
        fb = new FizzBuzz();
        lines = new ArrayList<>();
        fb.addLineConsumer(lines::add);
    }

    @Test
    public void prints1To100() {
        fb.execute();

        assertEquals(100, lines.size());
        assertEquals("1", lines.get(0));
        assertEquals("2", lines.get(1));
    }
    @Test
    public void whenDivisibleBy3_printFizz() {
        fb.execute();

        assertEquals("Fizz", lines.get(3 - 1));
        assertEquals("Fizz", lines.get(6 - 1));
        assertEquals("Fizz", lines.get(9 - 1));
        assertEquals("Fizz", lines.get(12 - 1));
        assertEquals("Fizz", lines.get(33 - 1));
        assertEquals("Fizz", lines.get(33 - 1));
        assertEquals("Fizz", lines.get(99 - 1));
    }

    @Test
    public void whenDivisibleBy5_printFizz() {
        fb.execute();

        assertEquals("Buzz", lines.get(5 - 1));
        assertEquals("Buzz", lines.get(10 - 1));
        assertEquals("Buzz", lines.get(20 - 1));
        assertEquals("Buzz", lines.get(35 - 1));
        assertEquals("Buzz", lines.get(50 - 1));
        assertEquals("Buzz", lines.get(95 - 1));
    }
    @Test
    public void whenDivisibleBy15_printFizz() {
        fb.execute();

        assertEquals("FizzBuzz", lines.get(15 - 1));
        assertEquals("FizzBuzz", lines.get(30 - 1));
        assertEquals("FizzBuzz", lines.get(45 - 1));
        assertEquals("FizzBuzz", lines.get(60 - 1));
        assertEquals("FizzBuzz", lines.get(75 - 1));
        assertEquals("FizzBuzz", lines.get(90 - 1));
    }

}
