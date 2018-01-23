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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The <tt>ComparatorUtil</tt> class is an implementation of a utility that
 * performs services for comparators.
 *
 * @author Stephen Cheesley &lt;stephen.cheesley@gmail.com&gt;
 */
public final class ComparatorUtil {

  /**
   * Equals comparison operation.
   */
  public static final String COMPARATOR_EQUALS = "==";

  /**
   * Not Equals comparison operation.
   */
  public static final String COMPARATOR_NEQUALS = "!=";

  /**
   * More than comparison operation.
   */
  public static final String COMPARATOR_MORE = ">";

  /**
   * Less than comparison operation.
   */
  public static final String COMPARATOR_LESS = "<";

  /**
   * More than OR equal to comparison operation.
   */
  public static final String COMPARATOR_MORE_EQ = ">=";

  /**
   * Less than OR equal to comparison operation.
   */
  public static final String COMPARATOR_LESS_EQ = "<=";

  /**
   * Like (search) comparison operation.
   */
  public static final String COMPARATOR_LIKE = "LIKE";

  /**
   * The logger for this class.
   */
  public static final Logger LOG
          = LoggerFactory.getLogger(ComparatorUtil.class);

  /**
   * The default constructor is private as this is a utility class.
   */
  private ComparatorUtil() {
  }

  /**
   * Compare two integers by the comparator passed.
   *
   * @param comparator The comparator to use for compare.
   * @param subject1 The subject to compare.
   * @param subject2 The value to compare to.
   * @return The result of the compare.
   */
  private static boolean compareInteger(final String comparator,
          final Integer subject1,
          final Integer subject2) {
    boolean result = false;

    switch (comparator) {
      case COMPARATOR_LESS:
        /* Less than */
        result = subject1 < subject2;
        break;
      case COMPARATOR_LESS_EQ:
        /* Less than or Equal To */
        result = subject1 <= subject2;
        break;
      case COMPARATOR_MORE:
        /* More than */
        result = subject1 > subject2;
        break;
      case COMPARATOR_MORE_EQ:
        /* More than or Equal To */
        result = subject1 >= subject2;
        break;
    }

    return result;
  }

  /**
   * Compare two doubles by the comparator passed.
   *
   * @param comparator The comparator to use for compare.
   * @param subject1 The subject to compare.
   * @param subject2 The value to compare to.
   * @return The result of the compare.
   */
  private static boolean compareDouble(final String comparator,
          final Double subject1,
          final Double subject2) {
    boolean result = false;

    switch (comparator) {
      case COMPARATOR_LESS:
        /* Less than */
        result = subject1 < subject2;
        break;
      case COMPARATOR_LESS_EQ:
        /* Less than or Equal To */
        result = subject1 <= subject2;
        break;
      case COMPARATOR_MORE:
        /* More than */
        result = subject1 > subject2;
        break;
      case COMPARATOR_MORE_EQ:
        /* More than or Equal To */
        result = subject1 >= subject2;
        break;
    }

    return result;
  }

  /**
   * Compare two strings using the comparator provided.
   *
   * @param comparator The comparator to use.
   * @param subject1 The value for comparison.
   * @param subject2 The value to compare against.
   * @return The result of the comparison.
   */
  private static boolean compareString(final String comparator,
          final String subject1,
          final String subject2) {
    boolean result = false;

    switch (comparator) {
      case COMPARATOR_LIKE:
        result = subject1.contains(subject2);
        break;
    }

    return result;
  }

  /**
   * Compare the two objects based on the comparator passed.
   *
   * @param comparator The comparator.
   * @param subject1 The basis for comparison.
   * @param subject2 The object to compare.
   * @return The result of the compare.
   */
  public static boolean compare(final String comparator,
          final Object subject1,
          final Object subject2) {
    boolean result = false;

    switch (comparator) {
      case COMPARATOR_EQUALS:
        /* Equals */
        result = subject1.equals(subject2);
        break;
      case COMPARATOR_NEQUALS:
        /* Not Equals */
        result = !subject1.equals(subject2);
        break;
      default:
        /* Check the remaining comparators. */
        if (subject1 instanceof Integer && subject2 instanceof Integer) {
          Integer int1 = (Integer) subject1;
          Integer int2 = (Integer) subject2;
          result = compareInteger(comparator, int1, int2);
        }
        if (subject1 instanceof Double && subject2 instanceof Double) {
          Double dbl1 = (Double) subject1;
          Double dbl2 = (Double) subject2;
          result = compareDouble(comparator, dbl1, dbl2);
        }
        if (subject1 instanceof String && subject2 instanceof String) {
          String str1 = (String) subject1;
          String str2 = (String) subject2;
          result = compareString(comparator, str1, str2);
        }
        break;
    }

    return result;
  }
}
