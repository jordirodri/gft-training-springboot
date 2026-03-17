package org.example.gfttrainingspringboot.katastdd.xavi;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.jupiter.api.Assertions.*;

public class TicTacToeDefinition {

    private TicTacToe game;

    @Given("a new game")
    public void aNewGame() {
        game = new TicTacToe();
    }

    @When("the game starts")
    public void theGameStarts() {
        // El constructor ya inicializa X
    }

    @Then("the first player is X")
    public void theFirstPlayerIsX() {
        assertEquals('X', game.getCurrentPlayer());
    }

    @Given("a game in progress")
    public void aGameInProgress() {
        game = new TicTacToe();
        game.makeMove(0, 1);
        game.makeMove(1, 0);
    }

    private boolean lastMoveValid;

    @When("the current or other player tries to mark the field")
    public void theCurrentOrOtherPlayerTriesToMarkTheField() {
        boolean moveAccepted = game.makeMove(0, 0);
        this.lastMoveValid = moveAccepted;
    }

    @Then("the move is rejected")
    public void theMoveIsRejected() {
        assertTrue(lastMoveValid, "Expected move to be rejected, but it was accepted");
    }

    @When("player makes a winning move")
    public void playerMakesAWinningMove() {
        game = new TicTacToe();
        game.makeMove(0, 0); // X
        game.makeMove(1, 0); // O
        game.makeMove(0, 1); // X
        game.makeMove(1, 1); // O
        lastMoveValid = game.makeMove(0, 2);
    }

    @Then("the player wins")
    public void thePlayerWins() {
        assertTrue(game.isGameOver(), "Expected game to be over");
        assertEquals('X', game.getWinner(), "Expected X to be winner");
    }

    @When("the last move is played")
    public void theLastMoveIsPlayed() {
        // Llenamos el tablero sin ganador (empate)
        game = new TicTacToe();
        // X O X
        // X O O
        // O X X
        game.makeMove(0, 0); // X
        game.makeMove(0, 1); // O
        game.makeMove(0, 2); // X
        game.makeMove(1, 1); // O
        game.makeMove(1, 0); // X
        game.makeMove(1, 2); // O
        game.makeMove(2, 1); // X
        game.makeMove(2, 0); // O
        lastMoveValid = game.makeMove(2, 2); // X
    }

    @Then("the game ends in tie")
    public void theGameEndsInTie() {
        assertTrue(game.isGameOver(), "Expected game to be over");
        assertEquals(' ', game.getWinner(), "Expected no winner (tie)");
    }
}
