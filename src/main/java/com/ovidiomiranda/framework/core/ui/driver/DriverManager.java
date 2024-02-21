package com.ovidiomiranda.framework.core.ui.driver;

import com.ovidiomiranda.framework.core.utils.property.PropertiesInput;
import com.ovidiomiranda.framework.core.utils.property.PropertiesManager;
import java.time.Duration;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * DriverManager class that applies Singleton pattern to instance WebDriver only once.
 *
 * @author Ovidio Miranda
 */
public final class DriverManager {

  private static DriverManager driverManager;
  private WebDriver driver;
  private WebDriverWait wait;

  /**
   * This method is the constructor class.
   */
  private DriverManager() {
    DriverType driverType = DriverType.valueOf(
        PropertiesManager.getInstance().getProperties(PropertiesInput.BROWSER));
    driver = DriverFactory.getDriverManager(driverType);
    maximize();
    long timeOutInSeconds = Integer.parseInt(
        PropertiesManager.getInstance().getProperties(PropertiesInput.EXPLICIT_WAIT));
    wait = new WebDriverWait(driver, Duration.ofSeconds(timeOutInSeconds));
  }

  /**
   * This method instance the singleton object.
   *
   * @return singleton instance.
   */
  public static DriverManager getInstance() {
    if (driverManager == null) {
      driverManager = new DriverManager();
    }
    return driverManager;
  }

  /**
   * Gets WebDriver.
   *
   * @return WebDriver instance.
   */
  public WebDriver getDriver() {
    return driver;
  }

  /**
   * Gets WebDriverWait.
   *
   * @return WebDriverWait instance.
   */
  public WebDriverWait getWebDriverWait() {
    return wait;
  }

  /**
   * Maximizes the current window if it is not already maximized.
   */
  public void maximize() {
    driver.manage().window().maximize();
  }

  /**
   * Minimize the current window.
   */
  public void minimize() {
    Point p = driver.manage().window().getPosition();
    Dimension d = driver.manage().window().getSize();
    driver.manage().window()
        .setPosition(new Point(d.getHeight() - p.getX(), d.getWidth() - p.getY()));
  }
}
