package org.example.gfttrainingspringboot.katastdd.pauln;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TicTacToeTest {

    @Test
    void xAlwaysStarts() {
        TicTacToe game = new TicTacToe();

        assertEquals('X', game.getNextPlayer());
        assertNull(game.getWinner());
    }

    @Test
    void alternatesTurnAfterEachPlay() {
        TicTacToe game = new TicTacToe();

        game.play(0, 0);
        assertEquals('O', game.getNextPlayer());

        game.play(0, 1);
        assertEquals('X', game.getNextPlayer());
    }

    @Test
    void throwsWhenPositionIsAlreadyOccupied() {
        TicTacToe game = new TicTacToe();
        game.play(1, 1);

        RuntimeException ex = assertThrows(RuntimeException.class, () -> game.play(1, 1));

        assertEquals("Position occupied", ex.getMessage());
    }

    @Test
    void throwsWhenPlayIsOutsideBoard() {
        TicTacToe game = new TicTacToe();

        RuntimeException ex1 = assertThrows(RuntimeException.class, () -> game.play(-1, 0));
        RuntimeException ex2 = assertThrows(RuntimeException.class, () -> game.play(0, 3));

        assertEquals("Outside board", ex1.getMessage());
        assertEquals("Outside board", ex2.getMessage());
    }

    @Test
    void detectsWinnerInRow() {
        TicTacToe game = new TicTacToe();

        game.play(0, 0); // X
        game.play(1, 0); // O
        game.play(0, 1); // X
        game.play(1, 1); // O
        game.play(0, 2); // X

        assertEquals("X", game.getWinner());
    }

    @Test
    void detectsWinnerInColumn() {
        TicTacToe game = new TicTacToe();

        game.play(0, 0); // X
        game.play(0, 1); // O
        game.play(1, 0); // X
        game.play(1, 1); // O
        game.play(2, 0); // X

        assertEquals("X", game.getWinner());
    }

    @Test
    void detectsWinnerInDiagonal() {
        TicTacToe game = new TicTacToe();

        game.play(0, 0); // X
        game.play(0, 1); // O
        game.play(1, 1); // X
        game.play(0, 2); // O
        game.play(2, 2); // X

        assertEquals("X", game.getWinner());
    }

    @Test
    void detectsWinnerInInverseDiagonalAndBlocksFurtherMoves() {
        TicTacToe game = new TicTacToe();

        game.play(0, 2); // X
        game.play(0, 0); // O
        game.play(1, 1); // X
        game.play(0, 1); // O
        game.play(2, 0); // X

        assertEquals("X", game.getWinner());

        RuntimeException ex = assertThrows(RuntimeException.class, () -> game.play(2, 2));
        assertEquals("Game over", ex.getMessage());
    }

    @Test
    void blocksMovesWhenBoardIsFull() {
        TicTacToe game = new TicTacToe();

        game.play(0, 0); // X
        game.play(0, 1); // O
        game.play(0, 2); // X
        game.play(1, 1); // O
        game.play(1, 0); // X
        game.play(1, 2); // O
        game.play(2, 1); // X
        game.play(2, 0); // O
        game.play(2, 2); // X

        RuntimeException ex = assertThrows(RuntimeException.class, () -> game.play(1, 1));
        assertEquals("Game over", ex.getMessage());
    }
}

