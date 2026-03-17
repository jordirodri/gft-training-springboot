package com.tictactoe.steps;


import io.cucumber.java.en.*;
import org.example.gfttrainingspringboot.katastdd.paucollado.TicTacToe;
import org.junit.jupiter.api.Assertions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TicTacToeSteps {

    TicTacToe game;
    Exception exception;

    @Given("tic tac toe game starts")
    public void ticTacToeGameStarts() {
        game = new TicTacToe();
    }

    @Then("first player is X")
    public void firstPlayerIsX() {
        Assertions.assertEquals('X', game.getCurrentPlayer());
    }

    @When("next player plays")
    public void nextPlayerPlays() {
        game.play(0,0);
    }

    @Then("current player is O")
    public void currentPlayerIsO() {
        Assertions.assertEquals('O', game.getCurrentPlayer());
    }

    @When("player plays on position {int} {int}")
    public void playerPlaysOnPosition(int row, int col) {
        game.play(row, col);
    }

    @When("player plays on an already played position")
    public void playerPlaysOnAlreadyPlayedPosition() {
        try {
            game.play(0,0);
        } catch (Exception e) {
            exception = e;
        }
    }

    @Then("exception is thrown")
    public void exceptionIsThrown() {
        Assertions.assertNotNull(exception);
    }
}