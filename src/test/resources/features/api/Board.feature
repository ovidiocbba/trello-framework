@Regression
Feature: Board

  @TC-00003 @Smoke @Functional @CreateWorkspace @DeleteBoard @DeleteWorkspace
  Scenario: Verify that a board can be created in a workspace
    When a POST request to "/boards" with the following values:
      | idOrganization | [Organization.id] |
      | name           | AUT Board         |
      | desc           | AUT Description   |
      And stored as "Board"
    Then the status code should be 200
      And verifies response body should match with "board/createBoard.json" JSON schema
      And verifies the response contain the following values:
        | name | AUT Board       |
        | desc | AUT Description |

  @TC-00004 @Smoke @Functional @CreateWorkspace @DeleteBoard @DeleteWorkspace
  Scenario: Create a new Board with one character for the name
    When a POST request to "/boards" with the following values:
      | idOrganization | [Organization.id] |
      | name           | A                 |
      And stored as "Board"
    Then the status code should be 200
      And verifies response body should match with "board/createBoard.json" JSON schema
      And verifies the response contain the following values:
        | name | A |
