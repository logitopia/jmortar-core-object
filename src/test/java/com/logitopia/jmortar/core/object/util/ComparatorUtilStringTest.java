/*
 *     JMortar - Tools to make your Java life easier.
 *     Copyright (C) 2015-2018 Stephen Cheesley
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.logitopia.jmortar.core.object.util;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The <tt>ComparatorUtilStringTest</tt> unit test class is a unit test of the
 * <tt>ComparatorUtil</tt> module.
 *
 * @author &lt;Stephen Cheesley stephen.cheesley@gmail.com&gt;
 */
public final class ComparatorUtilStringTest {

  /**
   * The logger for this class.
   */
  public static final Logger LOG
          = LoggerFactory.getLogger(ComparatorUtilStringTest.class);

  /**
   * Default Constructor.
   */
  public ComparatorUtilStringTest() {
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
   * Test that, given equals Strings, the equals comparator returns an expected
   * result.
   */
  @Test
  public void testEquals() {
    LOG.info("Testing String equality.");

    String comparator = "==";
    String subject1 = "value";
    String subject2 = "value";

    boolean result = ComparatorUtil.compare(comparator, subject1, subject2);

    assertTrue("Response result", result);
  }

  /**
   * Test that, given Strings that are not equals, the not equals comparator
   * returns an expected result.
   */
  @Test
  public void testNotEquals() {
    LOG.info("Testing String inequality.");

    String comparator = "!=";
    String subject1 = "value";
    String subject2 = "notvalue";

    boolean result = ComparatorUtil.compare(comparator, subject1, subject2);

    assertTrue("Response result", result);
  }
  
  /**
   * Test that the first string contains the second string.
   */
  @Test
  public void testLike() {
    LOG.info("Testing String LIKE.");
    
    String comparator = "LIKE";
    String subject1 = "somewhere";
    String subject2 = "where";
    
    boolean result = ComparatorUtil.compare(comparator, subject1, subject2);
    
    assertTrue("Response result", result);
  }
}
