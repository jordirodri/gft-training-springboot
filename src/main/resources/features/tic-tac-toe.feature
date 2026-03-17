Feature: Tic tac toe game
  Scenario: X always plays first
    Given a new game
    When the game starts
    Then the first player is X

  Scenario: Players cannot play on an occupied position
    Given a game in progress
    When the current or other player tries to mark the field
    Then the move is rejected

  Scenario: Player wins when a row, column or diagonal is fully marked
    Given a game in progress
    When player makes a winning move
    Then the player wins

  Scenario: All fields are taken
    Given a game in progress
    When the last move is played
    Then the game ends in tie