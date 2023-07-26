package com.ovidiomiranda.framework.hooks;

import static com.ovidiomiranda.framework.core.api.utils.JsonString.formatJsonString;
import static com.ovidiomiranda.framework.hooks.HookOrder.CREATE_BOARD;
import static com.ovidiomiranda.framework.hooks.HookOrder.DELETE_BOARD;

import com.ovidiomiranda.framework.core.api.RequestManager;
import com.ovidiomiranda.framework.core.context.Context;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.restassured.response.Response;
import java.util.Date;

/**
 * The prerequisites related to 'Board'.
 *
 * @author Ovidio Miranda
 */
public class BoardHooks {

  private Context context;

  /**
   * Initializes a new instance of the BoardHooks class.
   *
   * @param contextToSet Context to set.
   */
  public BoardHooks(final Context contextToSet) {
    this.context = contextToSet;
  }

  /**
   * Creates a workspace.
   */
  @Before(value = "@CreateBoard", order = CREATE_BOARD)
  public void createBoard() {
    final String endpoint = "/boards";
    final String name = "AUT Board".concat(Long.toString(new Date().getTime()));
    final String idOrganizations = context.getDataCollection("Organization").get("id");
    final String body = formatJsonString(
        "{\"idOrganization\":\"" + idOrganizations + "\"," + "\"name\":\"" + name + "\"}");
    final Response response = RequestManager.post(endpoint, body);
    context.saveDataCollection("Board", response.jsonPath().getMap(""));
  }

  /**
   * Deletes a board.
   */
  @After(value = "@DeleteBoard", order = DELETE_BOARD)
  public void deleteBoard() {
    final String idBoard = context.getDataCollection("Board").get("id");
    final String endpoint = "/boards/" + idBoard;
    RequestManager.delete(endpoint);
  }
}
