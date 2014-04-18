Feature: Add ToDo

  @VIP
  Scenario: As a valid user I can add new ToDo
    Given I am at "MainActivity" screen
    When I select "Add ToDo" from the menu
    Then I wait for the "NewToDoActivity" screen to appear
    When I enter text "buy bike" into field with id "task_et"
    And I enter text "5" into field with id "priority_et"
    And I press "Add"
    Then I wait for the "MainActivity" screen to appear
    And I see "buy bike"
