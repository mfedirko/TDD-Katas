import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GameTest {
    @Test
    void initialScoreIsZero() {
        Game game = new Game();
        assertEquals(0, game.score());
    }

    @Test
    void scoreOne_forOnePinKnockedDown() {
        Game game = new Game();
        game.roll(1);
        skipRemainingRolls(game, 19);

        assertEquals(1, game.score());
    }

    @Test
    void scoreTwo_forTwoPinKnockedDown() {
        Game game = new Game();
        game.roll(2);
        skipRemainingRolls(game, 19);

        assertEquals(2, game.score());
    }

    @Test
    void scoreFour_forTwoAndTwoPinKnockedDown() {
        Game game = new Game();
        game.roll(2);
        game.roll(2);
        skipRemainingRolls(game, 18);

        assertEquals(4, game.score());
    }

    @Test
    void scoreTen_forNineAndOneAndZeroPinKnockedDown() {
        Game game = new Game();
        game.roll(9);
        game.roll(1);
        skipRemainingRolls(game, 18);

        assertEquals(10, game.score());
    }

    @Test
    void spareFive() {
        Game game = new Game();
        game.roll(3);
        game.roll(7);
        game.roll(5);
        skipRemainingRolls(game, 17);

        assertEquals(20, game.score());
    }

    @Test
    void spare_middleFrames() {
        Game game = new Game();
        skipRemainingRolls(game, 6);
        game.roll(3);
        game.roll(7);
        game.roll(5);
        skipRemainingRolls(game, 11);


        assertEquals(3 + 7 + 5 + 5, game.score());
    }

    @Test
    void noSpare_forTenPinsAcrossFrames() {
        Game game = new Game();
        skipRemainingRolls(game, 7);
        game.roll(3);
        game.roll(7);
        game.roll(3);
        skipRemainingRolls(game, 10);


        assertEquals(3 + 7 + 3, game.score());
    }

    @Test
    void strike_forFrameWithTenRoll() {
        Game game = new Game();
        game.roll(10);

        game.roll(2);
        game.roll(3);
        skipRemainingRolls(game, 16);

        assertEquals(10 + 2 + 3 + 2 + 3 , game.score());
    }

    @Test
    void strikeThenSpare() {
        Game game = new Game();
        game.roll(10);

        game.roll(2);
        game.roll(8);

        game.roll(4);

        skipRemainingRolls(game, 15);

        assertEquals(10 + 2 + 8 + 2 + 8 + 4 + 4 , game.score());
    }

    @Test
    void strikeThenZeroNextRoll() {
        Game game = new Game();
        game.roll(10);

        game.roll(0);
        game.roll(8);

        game.roll(2);

        skipRemainingRolls(game, 15);

        assertEquals(10 + 8 + 8 + 2 , game.score());
    }

    @Test
    void score300_forPerfectGame() {
        Game game = new Game();
        for (int i = 0; i < 12; i++) {
            game.roll(10);
        }

        assertEquals(300 , game.score());
    }

    @Test
    void maxThreeBallsInTenthFrame() {
        Game game = new Game();
        for (int i = 0; i < 20; i++) {
            game.roll(10);
        }

        assertEquals(300 , game.score());
    }

    @Test
    void ignoreRollsAfterTenFrames() {
        Game game = new Game();
        for (int i = 0; i < 20; i++) {
            game.roll(3);
        }

        assertEquals(3 * 20, game.score());
    }


    private void skipRemainingRolls(Game game, int remaining) {
        for (int i = 0; i < remaining; i++) {
            game.roll(0);
        }
    }
}
