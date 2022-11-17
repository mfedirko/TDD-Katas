public class Point {
    private final int x;
    private final int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public static int manhattanDistance(Point p1, Point p2) {
        return absoluteLineDistance(absoluteLineDistance(p1.x, p2.x), -absoluteLineDistance(p1.y, p2.y));
    }

    private static int absoluteLineDistance(long p1, long p2) {
        if (p2 > p1) {
            long temp = p1;
            p1 = p2;
            p2 = temp;
        }
        checkForIntOverflow(p1, p2);
        return (int)(p1 - p2);
    }

    private static void checkForIntOverflow(long p1, long p2) {
        if (p1 - p2 > Integer.MAX_VALUE) throw new IllegalArgumentException("Integer overflow");
    }
}
