Feature: Tic Tac Toe Game

  Scenario: X always plays first
    Given a new Tic Tac Toe game
    Then the next player should be "X"

  Scenario: Players cannot play on a played position
    Given a new Tic Tac Toe game
    And player plays at 1,1
    When player tries to play at 1,1
    Then an error "Position occupied" is raised

  Scenario: Player wins in a row
    Given a new Tic Tac Toe game
    And player plays at 0,0
    And player plays at 1,0
    And player plays at 0,1
    And player plays at 1,1
    When player plays at 0,2
    Then player "X" is the winner

  Scenario: No moves allowed after game is won
    Given a new Tic Tac Toe game
    And player plays at 0,0
    And player plays at 1,0
    And player plays at 0,1
    And player plays at 1,1
    And player plays at 0,2
    When player tries to play at 2,2
    Then an error "Game over" is raised

  Scenario: No moves allowed when the board is full
    Given a new Tic Tac Toe game
    And player plays at 0,0
    And player plays at 0,1
    And player plays at 0,2
    And player plays at 1,1
    And player plays at 1,0
    And player plays at 1,2
    And player plays at 2,1
    And player plays at 2,0
    And player plays at 2,2
    When player tries to play at 1,1
    Then an error "Game over" is raised