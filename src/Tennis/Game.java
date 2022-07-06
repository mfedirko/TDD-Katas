package Tennis;

import java.util.Optional;

public class Game {
    private final Player playerOne;
    private final Player playerTwo;

    public Game(Player playerOne, Player playerTwo) {
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
    }

    boolean hasDeuce() {
        return playerOne.hasDeuceWith(playerTwo);
    }

    Optional<String> getAdvantage() {
        if (playerTwo.hasAdvantageTo(playerOne)) {
            return Optional.of(playerTwo.getName());
        } else if (playerOne.hasAdvantageTo(playerTwo)) {
            return Optional.of(playerOne.getName());
        }
        return Optional.empty();
    }

    Optional<String> getWinner() {
        if (playerOne.hasDefeated(playerTwo)) {
            return Optional.of(playerOne.getName());
        } else if (playerTwo.hasDefeated(playerOne)) {
            return Optional.of(playerTwo.getName());
        }
        return Optional.empty();
    }

    int getPlayerTwoPoints() {
        return playerTwo.getPoints();
    }

    int getPlayerOnePoints() {
        return playerOne.getPoints();
    }
}
