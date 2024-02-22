package com.ovidiomiranda.framework.core.utils;

import static com.ovidiomiranda.framework.core.utils.Messages.VALUES_ARE_NOT_EQUAL;
import static com.ovidiomiranda.framework.core.utils.StringUtils.deleteLastCharacter;
import static java.lang.String.format;
import static org.testng.AssertJUnit.assertEquals;

import org.testng.annotations.Test;

/**
 * Unit tests for 'String Utils' class.
 *
 * @author Ovidio Miranda
 */
public class StringUtilsTest {

  /**
   * Test to delete the last character of a given string.
   */
  @Test
  public void deleteLastCharacterFromString() {
    final String actual = deleteLastCharacter("1.0}");
    final String expected = "1.0";
    assertEquals(format(VALUES_ARE_NOT_EQUAL, expected, actual), expected, actual);
  }
}
