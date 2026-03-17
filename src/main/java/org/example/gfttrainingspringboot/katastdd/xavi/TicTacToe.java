package org.example.gfttrainingspringboot.katastdd.xavi;

public class TicTacToe {

    private char[][] board;
    private char currentPlayer;
    private char winner;
    private boolean gameOver;
    private int movesCount;

    public TicTacToe() {
        board = new char[3][3];
        currentPlayer = 'X'; // X always starts
        winner = ' ';
        gameOver = false;
        movesCount = 0;

        // Initialize board with empty spaces
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = ' ';
            }
        }
    }

    public char getCurrentPlayer() {
        return currentPlayer;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public char getWinner() {
        return winner;
    }

    /**
     * Player makes a move at (row, col)
     * Rows and columns are 0-based
     * Returns true if move is successful, false if invalid
     */
    public boolean makeMove(int row, int col) {
        if (gameOver) {
            return false; // No moves allowed after game ends
        }
        if (row < 0 || row > 2 || col < 0 || col > 2) {
            return false; // Invalid coordinates
        }
        if (board[row][col] != ' ') {
            return false; // Field already marked
        }

        board[row][col] = currentPlayer;
        movesCount++;

        if (checkWin(row, col)) {
            winner = currentPlayer;
            gameOver = true;
        } else if (movesCount == 9) {
            // All fields taken, tie
            gameOver = true;
        } else {
            // Switch player
            currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
        }

        return true;
    }

    /**
     * Check if current player has won after the last move
     */
    private boolean checkWin(int row, int col) {
        // Check row
        if (board[row][0] == currentPlayer &&
                board[row][1] == currentPlayer &&
                board[row][2] == currentPlayer) {
            return true;
        }

        // Check column
        if (board[0][col] == currentPlayer &&
                board[1][col] == currentPlayer &&
                board[2][col] == currentPlayer) {
            return true;
        }

        // Check diagonal top-left to bottom-right
        if (row == col &&
                board[0][0] == currentPlayer &&
                board[1][1] == currentPlayer &&
                board[2][2] == currentPlayer) {
            return true;
        }

        // Check diagonal top-right to bottom-left
        if (row + col == 2 &&
                board[0][2] == currentPlayer &&
                board[1][1] == currentPlayer &&
                board[2][0] == currentPlayer) {
            return true;
        }

        return false;
    }

    /**
     * Optional: print board (for debugging)
     */
    public void printBoard() {
        for (int i = 0; i < 3; i++) {
            System.out.println(board[i][0] + "|" + board[i][1] + "|" + board[i][2]);
            if (i < 2) System.out.println("-+-+-");
        }
    }

    /**
     * Check if a specific field is empty
     */
    public boolean isEmpty(int row, int col) {
        return board[row][col] == ' ';
    }

    /**
     * Get value of a specific field
     */
    public char getField(int row, int col) {
        return board[row][col];
    }
}