package org.tictactoe;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.jupiter.api.Assertions.*;

public class TicTacToeSteps {

    private TicTacToe juego;

    @Given("the board is empty")
    public void boardIsEmpty() {
        juego = new TicTacToe();
    }

    @When("X plays at position {int},{int}")
    public void xPlays(int fila, int col) {
        juego.jugar(fila, col);
    }

    @And("O plays at position {int},{int}")
    public void oPlays(int fila, int col) {
        juego.jugar(fila, col);
    }

    @Then("the winner is {string}")
    public void verifyWinner(String expectedPlayer) {
        assertEquals(expectedPlayer.charAt(0), juego.getGanador());
    }

    @Then("the result is a draw")
    public void verifyDraw() {
        assertTrue(juego.isEmpate(), "Expected a draw");
    }

    @Then("playing O at position {int},{int} throws an exception")
    public void verifyException(int fila, int col) {
        try {
            juego.jugar(fila, col);
            fail("Expected an exception but none was thrown");
        } catch (IllegalArgumentException | IllegalStateException e) {
            // correct: occupied position or game already over
        }
    }
}