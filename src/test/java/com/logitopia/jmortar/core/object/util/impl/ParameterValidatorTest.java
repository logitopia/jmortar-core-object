/* 
 * Software is the property of Stephen Cheesley.
 * All Rights Reserved.
 */
package com.logitopia.jmortar.core.object.util.impl;

import com.logitopia.jmortar.core.object.util.impl.test.TestAppender;
import java.util.List;
import org.apache.log4j.Level;
import org.apache.log4j.spi.LoggingEvent;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.impl.Log4jLoggerAdapter;

/**
 * The <tt>ParameterValidatorTest</tt> unit test class is a unit test of the
 * <tt>...</tt> module.
 *
 * @author &lt;Stephen Cheesley stephen.cheesley@gmail.com&gt;
 */
public class ParameterValidatorTest {

  /**
   * The logger for this class.
   */
  public static final Logger LOG
          = LoggerFactory.getLogger(ParameterValidatorTest.class);
  
  /**
   * The appender that will be used to capture and test LOG output.
   */
  private TestAppender testAppender;

  /**
   * Default Constructor.
   */
  public ParameterValidatorTest() {
  }

  /**
   * {@inheritDoc}
   */
  @BeforeClass
  public static void setUpClass() {
  }

  /**
   * {@inheritDoc}
   */
  @AfterClass
  public static void tearDownClass() {
  }

  /**
   * {@inheritDoc}
   */
  @Before
  public void setUp() {
    this.testAppender = new TestAppender();
    org.apache.log4j.Logger.getRootLogger().addAppender(testAppender);
  }

  /**
   * {@inheritDoc}
   */
  @After
  public void tearDown() {
  }

  /**
   * Test that we can validate parameters that are null.
   */
  @Test
  public void testValidateNullParameter() {
    LOG.info("testValidateNullParameter");
    
    org.apache.log4j.Logger.getRootLogger().setLevel(Level.TRACE);
    testAppender.reset();
    String expectedException = "Object.testMethod was passed a null parameter @ "
            + "parameter1";
    
    String parameter1 = null;
    Boolean parameter2 = true;

    try {
      ParameterValidator.validateParameters(
              new byte[]{ParameterValidator.NULL_VALIDATE},
              new Object[]{parameter1, parameter2},
              new String[]{"parameter1", "parameter2"},
              true,
              Object.class,
              "testMethod");
    } catch (IllegalArgumentException ex) {
      assertEquals("test exception message", expectedException, ex.getMessage());
    }

    /* Test Logging */
    String expectedLogDebugMsg = "parameter1 is null from call to testMethod";
    String expectedLogTraceMsg = "[Object.testMethod] The parameter parameter1 is null.";
    
    List<LoggingEvent> loggingEvents = testAppender.getAppends();
    assertEquals("logging event size", 2, loggingEvents.size());
    
    LoggingEvent debugEvent = loggingEvents.get(0);
    assertNotNull("Debug logging event null check", debugEvent);
    assertEquals("Debug logging event level", Level.DEBUG, debugEvent.getLevel());
    assertEquals("Debug logging event message", expectedLogDebugMsg,
            debugEvent.getMessage().toString());
    
    LoggingEvent traceEvent = loggingEvents.get(1);
    assertNotNull("Trace logging event null check", traceEvent);
    assertEquals("Trace logging event level", Level.TRACE, traceEvent.getLevel());
    assertEquals("Trace logging event message", expectedLogTraceMsg,
            traceEvent.getMessage().toString());
  }
  
  /**
   * Test that we can validate parameters that are null.
   */
  @Test
  public void testValidateEmptyStringParameter() {
    LOG.info("testValidateNullParameter");
    
    org.apache.log4j.Logger.getRootLogger().setLevel(Level.TRACE);
    testAppender.reset();
    String expectedException = "Object.testMethod was passed an empty parameter"
            + " @ parameter1";
    
    String parameter1 = "";
    Boolean parameter2 = true;

    try {
      ParameterValidator.validateParameters(
              new byte[]{ParameterValidator.STR_EMPTY_VALIDATE},
              new Object[]{parameter1, parameter2},
              new String[]{"parameter1", "parameter2"},
              true,
              Object.class,
              "testMethod");
    } catch (IllegalArgumentException ex) {
      assertEquals("test exception message", expectedException, ex.getMessage());
    }

    /* Test Logging */
    String expectedLogDebugMsg = "parameter1 is 'empty (blank)' from call to testMethod";
    String expectedLogTraceMsg = "[Object.testMethod] The parameter parameter1 is empty / blank.";
    
    List<LoggingEvent> loggingEvents = testAppender.getAppends();
    assertEquals("logging event size", 2, loggingEvents.size());
    
    LoggingEvent debugEvent = loggingEvents.get(0);
    assertNotNull("Debug logging event null check", debugEvent);
    assertEquals("Debug logging event level", Level.DEBUG, debugEvent.getLevel());
    assertEquals("Debug logging event message", expectedLogDebugMsg,
            debugEvent.getMessage().toString());
    
    LoggingEvent traceEvent = loggingEvents.get(1);
    assertNotNull("Trace logging event null check", traceEvent);
    assertEquals("Trace logging event level", Level.TRACE, traceEvent.getLevel());
    assertEquals("Trace logging event message", expectedLogTraceMsg,
            traceEvent.getMessage().toString());
  }
}
