package com.ovidiomiranda.framework.runner;

import com.ovidiomiranda.framework.core.ui.driver.DriverManager;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriverException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

/**
 * Initial configurations.
 *
 * <p> We need to create a class called TestRunner class to run the tests. This class will use the
 * TestNG annotation @Test, which tells TestNG what is the test runner class.
 */
@Test
@CucumberOptions(glue = {"com.ovidiomiranda.framework"}, features = {
    "src/test/resources/features"}, plugin = {"pretty",
    "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm", "rerun:build/target/rerun.txt"})
public class TestRunner extends AbstractTestNGCucumberTests {

  private static final Logger LOGGER = LogManager.getLogger(TestRunner.class);

  /**
   * After all scenarios.
   */

  @AfterClass
  public void afterAllScenarios() {
    try {
      DriverManager.getInstance().getDriver().quit();
    } catch (WebDriverException e) {
      LOGGER.info("Driver not closed(WebDriverException)");
    }
  }
}
