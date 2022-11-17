import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ManhattanDistanceTest {
    // ctrl+alt+L
    @Test
    public void samePoint() {
        int dist = Point.manhattanDistance(new Point(0, 0), new Point(0, 0));
        assertEquals(0, dist);
    }

    @Test
    public void horizontal() {
        int dist = Point.manhattanDistance(new Point(0, 0), new Point(10, 0));
        assertEquals(10, dist);
    }
    @Test
    public void horizontalOppositeOrder() {
        int dist = Point.manhattanDistance(new Point(10, 0), new Point(0, 0));
        assertEquals(10, dist);
    }
    @Test
    public void verticalDistance() {
        int dist = Point.manhattanDistance(new Point(0, 0), new Point(0, 20));
        assertEquals(20, dist);
    }
    @Test
    public void verticalDistanceOpposite() {
        int dist = Point.manhattanDistance(new Point(0, 20), new Point(0, 0));
        assertEquals(20, dist);
    }
    @Test
    public void verticalAndHorizontalDistance() {
        int dist = Point.manhattanDistance(new Point(25, 20), new Point(50, 40));
        assertEquals(25 + 20, dist);
    }
    @Test
    public void verticalAndHorizontalOppositeDistance() {
        int dist = Point.manhattanDistance(new Point(50, 40), new Point(25, 20));
        assertEquals(25 + 20, dist);
    }
    @Test
    public void verticalAndHorizontalNegativeDistance() {
        int dist = Point.manhattanDistance(new Point(-25, -10), new Point(-10, -5));
        assertEquals(15 + 5, dist);
    }
    @Test
    public void verticalAndHorizontalNegativePositiveDistance() {
        int dist = Point.manhattanDistance(new Point(-25, -10), new Point(10, 5));
        assertEquals(35 + 15, dist);
    }
    @Test(expected = IllegalArgumentException.class)
    public void intOverflow1() {
        int dist = Point.manhattanDistance(new Point(Integer.MIN_VALUE, -10), new Point(10, 5));
    }
    @Test(expected = IllegalArgumentException.class)
    public void intOverflow2() {
        int dist = Point.manhattanDistance(new Point(Integer.MIN_VALUE, -10), new Point(10, Integer.MAX_VALUE));
    }
    @Test(expected = IllegalArgumentException.class)
    public void intOverflow3() {
        int dist = Point.manhattanDistance(new Point(Integer.MIN_VALUE, -10), new Point(Integer.MIN_VALUE, Integer.MAX_VALUE));
    }
    @Test(expected = IllegalArgumentException.class)
    public void intOverflow4() {
        int dist = Point.manhattanDistance(new Point(Integer.MAX_VALUE, 0), new Point(-1, 0));
    }

    @Test(expected = IllegalArgumentException.class)
    public void intOverflow5() {
        int dist = Point.manhattanDistance(new Point(Integer.MAX_VALUE - 5, 0), new Point(0, -5000000));
        System.out.println(dist);
    }
}
