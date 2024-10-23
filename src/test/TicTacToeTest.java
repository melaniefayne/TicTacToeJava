package test;

import main.Player;
import main.TicTacToe;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TicTacToeTest {
    private TicTacToe game;

    @BeforeEach
    public void setUp() {
        game = new TicTacToe(); // Initialize a new game before each test
    }

    @Test
    public void testInitialPlayer() {
        Assertions.assertEquals(Player.X, game.getCurrentPlayer(), "The first player should be 'M'");
    }

    @Test
    public void testValidMove() {
        Assertions.assertTrue(game.choose(0, 0), "The move should be valid");
        Assertions.assertEquals(Player.O, game.getCurrentPlayer(), "The next player should be 'D'");
    }

    @Test
    public void testInvalidMoveOutsideBounds() {
        Assertions.assertFalse(game.choose(-1, 0), "Move outside the board should be invalid");
        Assertions.assertFalse(game.choose(3, 0), "Move outside the board should be invalid");
    }

    @Test
    public void testInvalidMoveAlreadyTaken() {
        game.choose(0, 0); // X
        Assertions.assertFalse(game.choose(0, 0), "Cannot choose an already taken spot");
    }

    @Test
    public void testWinConditionRow() {
        game.choose(0, 0); // X
        game.choose(1, 0); // O
        game.choose(0, 1); // X
        game.choose(1, 1); // O
        game.choose(0, 2); // X - X wins
        Assertions.assertTrue(game.didWin(Player.X), Player.X + " should win");
    }

    @Test
    public void testWinConditionColumn() {
        game.choose(0, 0); // X
        game.choose(0, 1); // O
        game.choose(1, 0); // X
        game.choose(1, 1); // O
        game.choose(2, 0); // X - X wins
        Assertions.assertTrue(game.didWin(Player.X), Player.X + " should win");
    }

    @Test
    public void testWinConditionDiagonal() {
        game.choose(0, 0); // X
        game.choose(0, 1); // O
        game.choose(1, 1); // X
        game.choose(1, 0); // O
        game.choose(2, 2); // X - X wins
        Assertions.assertTrue(game.didWin(Player.X), Player.X + " should win");
    }

    @Test
    public void testTieCondition() {
        game.choose(0, 0); // X
        game.choose(1, 0); // O
        game.choose(1, 1); // X
        game.choose(2, 2); // O
        game.choose(0, 1); // X
        game.choose(0, 2); // O
        game.choose(2, 0); // X
        game.choose(2, 1); // O
        game.choose(1, 2); // X should result in a tie
        Assertions.assertTrue(game.didTie(), "The game should be a tie");
    }

    @Test
    public void testNotDone() {
        game.choose(0, 0); // X
        Assertions.assertTrue(game.notDone(), "The game should not be done");
        game.choose(1, 1); // O
        game.choose(0, 1); // X
        game.choose(2, 2); // O
        game.choose(0, 2); // X - X wins
        Assertions.assertFalse(game.notDone(), "The game should be done");
    }
}
