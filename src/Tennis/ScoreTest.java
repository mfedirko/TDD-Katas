package Tennis;

import org.junit.Test;

import static org.junit.Assert.*;

public class ScoreTest {
    private static final String playerOneName = "P1";
    private static final String playerTwoName = "P2";

    @Test
    public void whenPlayer1HasAtLeastFourPointsTotal_andTwoPointsMoreThanOpponent_thenPlayer1Won() {
        assertEquals("Game set, match " + playerOneName,
                Score.getScore(new Game(new Player(4, playerOneName), new Player(2, playerTwoName))).toString());
    }

    @Test
    public void whenPlayer2HasAtLeastFourPointsTotal_andTwoPointsMoreThanOpponent_thenPlayer2Won() {
        assertEquals("Game set, match " + playerTwoName,
                Score.getScore(new Game(new Player(2, playerOneName), new Player(4, playerTwoName))).toString());
    }

    @Test
    public void whenNoWinner_thenDoesNotPrintWinner() {
        assertNotEquals("Game set, match " + playerTwoName,
                Score.getScore(new Game(new Player(0, playerOneName), new Player(2, playerTwoName))).toString());
        assertNotEquals("Game set, match " + playerOneName,
                Score.getScore(new Game(new Player(1, playerOneName), new Player(1, playerTwoName))).toString());
    }

    @Test
    public void whenZeroScore_thenPrintLove() {
        assertEquals("Love-Love",
                Score.getScore(new Game(new Player(0, playerOneName), new Player(0, playerTwoName))).toString());

    }

    @Test
    public void whenOneScore_thenPrintFifteen() {
        assertEquals("Fifteen-Love",
                Score.getScore(new Game(new Player(1, playerOneName), new Player(0, playerTwoName))).toString());

        assertEquals("Love-Fifteen",
                Score.getScore(new Game(new Player(0, playerOneName), new Player(1, playerTwoName))).toString());

        assertEquals("Fifteen-Fifteen",
                Score.getScore(new Game(new Player(1, playerOneName), new Player(1, playerTwoName))).toString());

    }

    @Test
    public void whenTwoScore_thenPrintThirty() {
        assertEquals("Thirty-Love",
                Score.getScore(new Game(new Player(2, playerOneName), new Player(0, playerTwoName))).toString());

        assertEquals("Love-Thirty",
                Score.getScore(new Game(new Player(0, playerOneName), new Player(2, playerTwoName))).toString());


        assertEquals("Thirty-Fifteen",
                Score.getScore(new Game(new Player(2, playerOneName), new Player(1, playerTwoName))).toString());

        assertEquals("Fifteen-Thirty",
                Score.getScore(new Game(new Player(1, playerOneName), new Player(2, playerTwoName))).toString());

        assertEquals("Thirty-Thirty",
                Score.getScore(new Game(new Player(2, playerOneName), new Player(2, playerTwoName))).toString());
    }

    @Test
    public void whenThreeScore_thenPrintForty() {
        assertEquals("Forty-Love",
                Score.getScore(new Game(new Player(3, playerOneName), new Player(0, playerTwoName))).toString());

        assertEquals("Love-Forty",
                Score.getScore(new Game(new Player(0, playerOneName), new Player(3, playerTwoName))).toString());


        assertEquals("Fifteen-Forty",
                Score.getScore(new Game(new Player(1, playerOneName), new Player(3, playerTwoName))).toString());

        assertEquals("Thirty-Forty",
                Score.getScore(new Game(new Player(2, playerOneName), new Player(3, playerTwoName))).toString());

        assertEquals("Forty-Thirty",
                Score.getScore(new Game(new Player(3, playerOneName), new Player(2, playerTwoName))).toString());
    }

    @Test
    public void whenAtLeastThreePointsEachPlayer_andEqualPoints_thenPrintDeuce() {
        assertEquals("deuce",
                Score.getScore(new Game(new Player(3, playerOneName), new Player(3, playerTwoName))).toString());

        assertEquals("deuce",
                Score.getScore(new Game(new Player(4, playerOneName), new Player(4, playerTwoName))).toString());

        assertEquals("deuce",
                Score.getScore(new Game(new Player(5, playerOneName), new Player(5, playerTwoName))).toString());

    }

    @Test
    public void whenAtLeastThreePointsEachPlayer_andPlayerHasOneMoreThanOpponent_thenPrintAdvantage() {
        assertEquals("advantage " + playerOneName,
                Score.getScore(new Game(new Player(4, playerOneName), new Player(3, playerTwoName))).toString());

        assertEquals("advantage " + playerTwoName,
                Score.getScore(new Game(new Player(3, playerOneName), new Player(4, playerTwoName))).toString());

        assertEquals("advantage " + playerOneName,
                Score.getScore(new Game(new Player(6, playerOneName), new Player(5, playerTwoName))).toString());

        assertEquals("advantage " + playerTwoName,
                Score.getScore(new Game(new Player(6, playerOneName), new Player(7, playerTwoName))).toString());

    }

}
