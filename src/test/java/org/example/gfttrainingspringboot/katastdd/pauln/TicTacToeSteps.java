package org.example.gfttrainingspringboot.katastdd.pauln;

import io.cucumber.java.en.*;
import org.example.gfttrainingspringboot.katastdd.pauln.TicTacToe;

import static org.assertj.core.api.Assertions.*;

public class TicTacToeSteps {
    private TicTacToe game;
    private Exception lastException;

    @Given("a new Tic Tac Toe game")
    public void setup() {
        game = new TicTacToe();
        lastException = null;
    }

    @Then("the next player should be {string}")
    public void checkNextPlayer(String p) {
        assertThat(game.getNextPlayer()).isEqualTo(p.charAt(0));
    }

    @And("player plays at {int},{int}")
    public void play(int r, int c) {
        game.play(r, c);
    }

    @When("player tries to play at {int},{int}")
    public void tryPlay(int r, int c) {
        try {
            game.play(r, c);
        } catch (Exception e) {
            lastException = e;
        }
    }

    @Then("an error {string} is raised")
    public void checkError(String msg) {
        assertThat(lastException).isNotNull().hasMessage(msg);
    }

    @Then("player {string} is the winner")
    public void checkWinner(String p) {
        assertThat(game.getWinner()).isEqualTo(p);
    }
}