package com.ovidiomiranda.framework.stepdefinition;

import static com.ovidiomiranda.framework.core.api.RequestType.POST;
import static com.ovidiomiranda.framework.core.api.utils.Mapper.mapValues;
import static com.ovidiomiranda.framework.core.api.utils.ResponseBodyValidator.validateBodyValues;
import static com.ovidiomiranda.framework.core.api.utils.ResponseBodyValidator.validateSchema;
import static com.ovidiomiranda.framework.core.utils.DataInterpreter.builtEndPoint;

import com.ovidiomiranda.framework.core.api.RequestManager;
import com.ovidiomiranda.framework.core.api.RequestType;
import com.ovidiomiranda.framework.core.context.Context;
import com.ovidiomiranda.framework.core.utils.property.PropertiesManager;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;
import java.util.Map;
import org.testng.Assert;

/**
 * Common request steps are used in all tests.
 *
 * @author Ovidio Miranda
 */
public class RequestSteps {

  private Response response;
  private Context context;

  /**
   * Constructor of Request Step Definitions.
   *
   * @param contextToSet Context to set.
   */
  public RequestSteps(final Context contextToSet) {
    this.context = contextToSet;
  }

  /**
   * Sends a POST request with body.
   *
   * @param endpoint resource endpoint.
   * @param body     json data.
   */
  @And("a POST request to {string} with the following Json data:")
  public void sendsPostRequest(final String endpoint, final String body) {
    response = RequestManager.post(endpoint, body);
  }

  /**
   * Sends a POST request with body.
   *
   * @param method is the request type.
   * @param param    is the specified end point.
   * @param expected is the map body content.
   */
  @And("a {requestType} request to {string} with the following values:")
  public void sendsPostRequest(final RequestType method, final String param,
      final Map<String, String> expected) {
    final String endpoint = builtEndPoint(param, context);
    final String body = mapValues(expected, context);
    response = POST.equals(method) ? RequestManager.post(endpoint, body)
        : RequestManager.put(endpoint, body);
  }

  /**
   * Sends a GET request with body.
   *
   * @param endpoint resource endpoint.
   * @param body     json data.
   */
  @And("a GET request to {string} with the following Json data:")
  public void sendsGetRequest(final String endpoint, final String body) {
    response = RequestManager.get(endpoint, body);
  }

  /**
   * Verifies status code.
   *
   * @param expectedStatusCode Int status code
   */
  @Then("the status code should be {int}")
  public void verifiesStatusCode(final int expectedStatusCode) {
    Assert.assertEquals(response.statusCode(), expectedStatusCode);
  }

  /**
   * Verifies response body should be the expected response.
   *
   * @param expectedResponseBody expected response body.
   */
  @And("verifies the response body should be:")
  public void verifiesResponseBody(final String expectedResponseBody) {
    Assert.assertEquals(response.body().prettyPrint(), expectedResponseBody);
  }

  /**
   * Verifies response body with json schema.
   *
   * @param schemaPath schema path.
   */
  @And("verifies response body should match with {string} JSON schema")
  public void verifiesResponseBodyJsonSchema(final String schemaPath) {
    validateSchema(response, PropertiesManager.getInstance().getSchemasPath() + schemaPath);
  }

  /**
   * Verifies response body values.
   *
   * @param expectedValues expected response values.
   */
  @And("verifies the response contain the following values:")
  public void verifiesResponseBodyValues(final Map<String, String> expectedValues) {
    context.saveData(response.asString());
    validateBodyValues(context.getData(), expectedValues);
  }

  /**
   * Step definition to store the response data to a shared variable.
   *
   * @param name the name of the shared variable.
   */
  @And("stored as {string}")
  public void storedAs(final String name) {
    System.out.println(response.jsonPath().getMap(""));
    context.saveDataCollection(name, response.jsonPath().getMap(""));
  }
}
