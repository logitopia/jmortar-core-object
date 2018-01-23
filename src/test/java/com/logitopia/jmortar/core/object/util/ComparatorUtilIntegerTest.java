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

/**
 * Title: ComparatorUtilIntegerTest.java
 *
 * Author: Stephen Cheesley stephen.cheesley@gmail.com Date Created: 17-Aug-2015
 *
 * This code is the intellectual property of Logitopia Technologies.
 */
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The <tt>ComparatorUtilIntegerTest</tt> unit test class is a unit test of the
 * <tt>ComparatorUtil</tt> module.
 *
 * @author &lt;Stephen Cheesley stephen.cheesley@gmail.com&gt;
 */
public final class ComparatorUtilIntegerTest {

  /**
   * The logger for this class.
   */
  public static final Logger LOG
          = LoggerFactory.getLogger(ComparatorUtilIntegerTest.class);

  /**
   * Default Constructor.
   */
  public ComparatorUtilIntegerTest() {
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
   * Test that, when given two identical integers the comparator utility
   * correctly compares them.
   */
  @Test
  public void testEquals() {
    LOG.info("Testing integer equality");
    
    String comparator = "==";
    Integer subject1 = 4;
    Integer subject2 = 4;
    
    boolean result = ComparatorUtil.compare(comparator, subject1, subject2);
    assertTrue("Result value test", result);
  }
  
  /**
   * Test that, when given two different integers the comparator utility
   * correctly compares them.
   */
  @Test
  public void testNotEquals() {
    LOG.info("Testing integer inequality");
    
    String comparator = "!=";
    Integer subject1 = 4;
    Integer subject2 = 5;
    
    boolean result = ComparatorUtil.compare(comparator, subject1, subject2);
    assertTrue("Result value test", result);
  }
  
  /**
   * Test that we get an expected output from the greater than comparator.
   */
  @Test
  public void testGreaterThan() {
    LOG.info("Testing integer greater than");
    
    String comparator = ">";
    Integer subject1 = 6;
    Integer subject2 = 5;
    
    boolean result = ComparatorUtil.compare(comparator, subject1, subject2);
    assertTrue("Result value test", result);
  }
  
  /**
   * Test that we get an expected output from the greater than comparator.
   */
  @Test
  public void testGreaterThanOrEqualTo() {
    LOG.info("Testing integer greater than or equal to");
    
    String comparator = ">=";
    Integer subject1 = 6;
    Integer subject2 = 6;
    
    boolean result = ComparatorUtil.compare(comparator, subject1, subject2);
    assertTrue("Result value test", result);
  }
}
