Feature: ToDos on main activity

  Scenario: As a valid user I can see ToDos at main activity
    Given I am at "MainActivity" screen
    Then I see "ToDo"
    And  I see "buy milk"
