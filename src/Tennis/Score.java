package Tennis;

public class Score {

    public static Score getScore(Game game) {
        return new Score(game);
    }

    private final Game game;


    public Score(Game game) {
        this.game = game;
    }

    @Override
    public String toString() {
        if (game.getWinner().isPresent()) {
            return "Game set, match " + game.getWinner().get();
        } else if (game.getAdvantage().isPresent()) {
            return "advantage " + game.getAdvantage().get();
        } else if (game.hasDeuce()) {
            return "deuce";
        } else {
            return String.format("%s-%s", translate(game.getPlayerOnePoints()), translate(game.getPlayerTwoPoints()));
        }
    }


    private String translate(int points) {
        switch (points) {
            case 0:
                return "Love";
            case 1:
                return "Fifteen";
            case 2:
                return "Thirty";
            case 3:
                return "Forty";
            default:
                throw new IllegalStateException("Invalid usage of translate(" + points + "). " +
                        " Either deuce, advantage or winner must be present for points > 3");
        }
    }

}
