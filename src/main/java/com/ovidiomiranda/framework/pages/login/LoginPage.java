package com.ovidiomiranda.framework.pages.login;

import static com.ovidiomiranda.framework.core.ui.CommonWebActions.clickElement;
import static com.ovidiomiranda.framework.core.ui.CommonWebActions.enterTextField;

import com.ovidiomiranda.framework.core.ui.BasePage;
import com.ovidiomiranda.framework.core.ui.driver.DriverManager;
import com.ovidiomiranda.framework.core.utils.property.PropertiesManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Represents the 'Login' page and all the components displayed there.
 *
 * @author Ovidio Miranda
 */
public class LoginPage extends BasePage {

  private static final Logger LOGGER = LogManager.getLogger(LoginPage.class);
  public static final String BASE_URL = PropertiesManager.getInstance().getBaseUrl();
  public static final String LOGIN_URL = BASE_URL + "/login";
  @FindBy(id = "user")
  private WebElement emailTextField;

  @FindBy(css = "#login.button")
  private WebElement continueButton;

  @FindBy(id = "password")
  private WebElement passwordTextField;

  @FindBy(id = "login-submit")
  private WebElement loginButton;

  /**
   * Initializes a new instance of the LoginPage class.
   */
  public LoginPage() {
    super();
    setWebDriverUrl(LOGIN_URL);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void waitUntilPageIsLoaded() {
  }

  /**
   * Sets of URL attribute.
   *
   * @param url the url.
   */
  public void setWebDriverUrl(final String url) {
    DriverManager.getInstance().getDriver().get(url);
    LOGGER.info("Set URL: {}", url);
  }

  /**
   * Enters 'Email' text field.
   *
   * @param email the email.
   */
  public void enterEmailTextField(final String email) {
    enterTextField(emailTextField, email);
  }

  /**
   * Enters 'Password' text field.
   *
   * @param password string value.
   */
  public void enterPassword(final String password) {
    enterTextField(passwordTextField, password);
  }

  /**
   * Clicks 'Log in' button.
   */
  public void clickLogInButton() {
    clickElement(loginButton);
  }

  /**
   * Clicks 'Continue' button.
   */
  public void clickContinueButton() {
    clickElement(continueButton);
  }

  /**
   * Logs in to the Trello portal.
   *
   * @param user     the user.
   * @param password its password.
   */
  public void logIn(final String user, final String password) {
    enterEmailTextField(user);
    clickContinueButton();
    enterPassword(password);
    clickLogInButton();
  }
}
