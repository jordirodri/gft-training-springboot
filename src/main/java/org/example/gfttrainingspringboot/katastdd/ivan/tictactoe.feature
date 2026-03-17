Feature: Tic Tac Toe

  Scenario: X Wins with a complete diagonal

    Given the board is empty
    When X plays at position 1,1
    And O plays at position 0,0
    And X plays at position 2,0
    And O plays at position 0,1
    And X plays at position 0,2
    Then the winner is "X"
  
  Scenario: 0 Wins with a complete column
    Given the board is empty
    When X plays at position 1,1
    And O plays at position 0,0
    And X plays at position 2,2
    And O plays at position 1,0
    And X plays at position 1,2
    And O plays at position 2,0
    Then the winner is "O"

  Scenario: X wins with a complete row
    Given the board is empty
    When X plays at position 0,0
    And O plays at position 1,0
    And X plays at position 0,1
    And O plays at position 1,1
    And X plays at position 0,2
    Then the winner is "X"

  Scenario: Draw when the board is full
    Given the board is empty
    When X plays at position 0,0
    And O plays at position 0,1
    And X plays at position 0,2
    And O plays at position 1,0
    And X plays at position 1,1
    And O plays at position 2,0
    And X plays at position 1,2
    And O plays at position 2,2
    And X plays at position 2,1
    Then the result is a draw

  Scenario: Cannot play on an already taken position
    Given the board is empty
    When X plays at position 0,0
    Then playing O at position 0,0 throws an exception

  Scenario: Cannot play after the game has ended
    Given the board is empty
    When X plays at position 0,0
    And O plays at position 1,0
    And X plays at position 0,1
    And O plays at position 1,1
    And X plays at position 0,2
    Then playing O at position 2,0 throws an exception