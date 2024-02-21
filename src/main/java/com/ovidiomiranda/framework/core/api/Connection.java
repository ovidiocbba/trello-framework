package com.ovidiomiranda.framework.core.api;

import static java.lang.String.format;

import com.ovidiomiranda.framework.core.log.RestAssuredRequestFilter;
import com.ovidiomiranda.framework.core.utils.property.PropertiesManager;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

/**
 * Class to manage the connection to Trello.
 *
 * @author Ovidio Miranda
 */
public class Connection {

  private static final String API_URL = PropertiesManager.getInstance().getApiUrl();
  private static final String AUTHORIZATION = "Authorization";
  private static final String API_KEY = PropertiesManager.getInstance().getApiKey();
  private static final String API_TOKEN = PropertiesManager.getInstance().getApiToken();
  private static Connection connection = new Connection();
  private final RequestSpecification requestSpecification;

  /**
   * This is the constructor for que Connection singleton class.
   */
  private Connection() {
    requestSpecification = new RequestSpecBuilder().setBaseUri(API_URL)
        .addHeader(AUTHORIZATION, getAuthorization()).setContentType(ContentType.JSON).build();
  }

  /**
   * Gets the Authorization.
   *
   * @return the Authorization header.
   */
  private static String getAuthorization() {
    return format("OAuth oauth_consumer_key=\"%s\", oauth_token=\"%s\"", API_KEY, API_TOKEN);
  }

  /**
   * This method Instance the connection if this does not exist.
   *
   * @return a connection.
   */
  public static Connection getInstance() {
    if (connection == null) {
      connection = new Connection();
    }
    return connection;
  }

  /**
   * This method return the Request Specification instance.
   *
   * @return the Request Specification instance.
   */
  public RequestSpecification getRequestSpecification() {
    return requestSpecification.filter(new RestAssuredRequestFilter());
  }
}
