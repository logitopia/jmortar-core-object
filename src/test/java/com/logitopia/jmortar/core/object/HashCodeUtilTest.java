/* 
 * Software is the property of Stephen Cheesley.
 * All Rights Reserved.
 */
package com.logitopia.jmortar.core.object;

import com.logitopia.jmortar.core.object.util.impl.HashCodeUtil;
import static junit.framework.Assert.assertTrue;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The test class <tt>HashCodeUtilTest</tt> tests for TODO ...
 *
 * @author s.cheesley
 */
public class HashCodeUtilTest {

  /**
   * The logger for this class.
   */
  public static final Logger LOG
          = LoggerFactory.getLogger(HashCodeUtilTest.class);

  /**
   * Odd prime number for predicting test values.
   */
  private static final int ODD_PRIME = 37;

  /*
   * Default Constructor.
   */
  public HashCodeUtilTest() {
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
  }

  /**
   * {@inheritDoc}
   */
  @After
  public void tearDown() {
  }

  /**
   * Test resultant hash code for a given boolean value.
   */
  @Test
  public void testHashBoolean() {
    LOG.info("Testing boolean hash...");
    int start = HashCodeUtil.SEED;
    int expected = (ODD_PRIME * start) + 1;

    boolean test = true;
    int result = HashCodeUtil.hash(HashCodeUtil.SEED, test);

    assertTrue("Outcome test", result == expected);
  }

  /**
   * Test resultant hash code for given integer.
   */
  @Test
  public void testHashInt() {
    LOG.info("Testing integer hash...");
    int test = 456289;
    int start = HashCodeUtil.SEED;
    int expected = (ODD_PRIME * start) + test;
    int result = HashCodeUtil.hash(HashCodeUtil.SEED, test);

    assertTrue("Outcome test", result == expected);
  }

  /**
   * Test the hash code for given String.
   */
  @Test
  public void testHashString() {
    LOG.info("Testing string hash...");
    String test = "Chuck Norris";
    int start = HashCodeUtil.SEED;
    int expected = (ODD_PRIME * start) + test.hashCode();
    int result = HashCodeUtil.hash(HashCodeUtil.SEED, test);

    assertTrue("Outcome test", result == expected);
  }

}
