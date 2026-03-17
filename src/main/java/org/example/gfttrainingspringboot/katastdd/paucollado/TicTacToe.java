package org.example.gfttrainingspringboot.katastdd.paucollado;

public class TicTacToe {

    private char[][] board = new char[3][3];
    private char currentPlayer = 'X';
    private boolean gameFinished = false;

    public char getCurrentPlayer() {
        return currentPlayer;
    }

    public void play(int row, int col) {

        if (gameFinished) {
            throw new IllegalStateException("Game already finished");
        }

        if (board[row][col] != '\0') {
            throw new IllegalArgumentException("Position already played");
        }

        board[row][col] = currentPlayer;

        if (checkWinner(row, col)) {
            gameFinished = true;
            return;
        }

        switchPlayer();
    }

    private void switchPlayer() {
        currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
    }

    private boolean checkWinner(int row, int col) {

        if (board[row][0] == currentPlayer &&
                board[row][1] == currentPlayer &&
                board[row][2] == currentPlayer) {
            return true;
        }

        if (board[0][col] == currentPlayer &&
                board[1][col] == currentPlayer &&
                board[2][col] == currentPlayer) {
            return true;
        }

        if (board[0][0] == currentPlayer &&
                board[1][1] == currentPlayer &&
                board[2][2] == currentPlayer) {
            return true;
        }

        if (board[0][2] == currentPlayer &&
                board[1][1] == currentPlayer &&
                board[2][0] == currentPlayer) {
            return true;
        }

        return false;
    }
}