@Regression
Feature: List

  @TC-00005 @Smoke @Functional @CreateWorkspace @CreateBoard @DeleteBoard @DeleteWorkspace
  Scenario: Verify that a list can be created in a Board
    When a POST request to "/boards/[Board.id]/lists" with the following values:
      | name | AUT List |
    Then the status code should be 200
      And verifies response body should match with "list/createList.json" JSON schema
      And verifies the response contain the following values:
        | name   | AUT List |
        | closed | false    |

  @TC-00006 @Functional @CreateWorkspace @CreateBoard @CreateList @DeleteBoard @DeleteWorkspace
  Scenario: Verify that a list can be archived
    When a PUT request to "/lists/[List.id]/closed" with the following values:
      | value | true |
    Then the status code should be 200
      And verifies response body should match with "list/archiveOrUnarchiveList.json" JSON schema
      And verifies the response contain the following values:
        | closed | true |
