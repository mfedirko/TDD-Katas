package Tennis;

public class Player {
    private final int points;
    private final String name;

    public Player(int points, String name) {
        this.points = points;
        this.name = name;
    }

    public int getPoints() {
        return points;
    }

    public String getName() {
        return name;
    }

    boolean hasDefeated(Player playerTwo) {
        return getPoints() >= 4
                && getPoints() >= playerTwo.getPoints() + 2;
    }

    boolean hasAdvantageTo(Player playerTwo) {
        return getPoints() == playerTwo.getPoints() + 1 && playerTwo.getPoints() >= 3;
    }

    boolean hasDeuceWith(Player playerTwo) {
        return getPoints() == playerTwo.getPoints() && getPoints() >= 3;
    }
}