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
        if (points == 0) {
            return "Love";
        } else if (points == 1) {
            return "Fifteen";
        } else if (points == 2) {
            return "Thirty";
        } else if (points == 3) {
            return "Forty";
        }
        throw new IllegalStateException("Invalid usage of translate(" + points + "). " +
                " Either deuce, advantage or winner must be present for points > 3");
    }

}
