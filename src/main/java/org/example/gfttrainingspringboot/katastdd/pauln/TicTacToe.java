package org.example.gfttrainingspringboot.katastdd.pauln;

public class TicTacToe {
    private char[][] board = new char[3][3];
    private char lastPlayer = 'O';
    private char winner = '\0';
    private int moveCount = 0;

    public void play(int row, int col) {
        if (winner != '\0' || moveCount == 9) throw new RuntimeException("Game over");
        if (row < 0 || row > 2 || col < 0 || col > 2) throw new RuntimeException("Outside board");
        if (board[row][col] != '\0') throw new RuntimeException("Position occupied");

        char currentPlayer = getNextPlayer();
        board[row][col] = currentPlayer;
        lastPlayer = currentPlayer;
        moveCount++;

        checkWinner(row, col);
    }

    public char getNextPlayer() {
        return (lastPlayer == 'X') ? 'O' : 'X';
    }

    private void checkWinner(int r, int c) {
        if (board[r][0] == board[r][1] && board[r][0] == board[r][2]) winner = board[r][0];
        else if (board[0][c] == board[1][c] && board[0][c] == board[2][c]) winner = board[0][c];
        else if (board[0][0] != '\0' && board[0][0] == board[1][1] && board[0][0] == board[2][2]) winner = board[0][0];
        else if (board[0][2] != '\0' && board[0][2] == board[1][1] && board[0][2] == board[2][0]) winner = board[0][2];
    }

    public String getWinner() {
        return winner == '\0' ? null : String.valueOf(winner);
    }
}