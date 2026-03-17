Feature: Tic Tac Toe Game

  Scenario: Game starts with X
    Given tic tac toe game starts
    Then first player is X

  Scenario: Next player becomes O
    Given tic tac toe game starts
    When next player plays
    Then current player is O

  Scenario: Player cannot play twice on same position
    Given tic tac toe game starts
    When player plays on position 0 0
    And player plays on an already played position
    Then exception is thrown