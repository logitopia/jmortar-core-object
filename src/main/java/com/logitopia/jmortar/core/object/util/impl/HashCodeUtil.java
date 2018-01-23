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
package com.logitopia.jmortar.core.object.util.impl;

import java.lang.reflect.Array;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The class <tt>HashCodeUtil</tt> represents a utility that can be used for
 * automatically generating hash codes.
 */
public final class HashCodeUtil {

  /**
   * The initial seed for the hash.
   */
  public static final int SEED = 21;

  /**
   * An odd prime for the initialization.
   */
  private static final int ODD_PRIME = 37;

  /**
   * The logger for this class.
   */
  public static final Logger LOG
          = LoggerFactory.getLogger(HashCodeUtil.class);

  /**
   * Compute a hash code for a boolean value.
   *
   * @param aSeed The initial seed value.
   * @param aBoolean The boolean to be hashed.
   * @return int The hash code generated.
   */
  public static int hash(final int aSeed, final boolean aBoolean) {
    int result;
    int booleanVal = 0;

    LOG.info("Hashing boolean.");
    if (aBoolean) {
      booleanVal = 1;
    }

    result = firstTerm(aSeed) + booleanVal;

    return result;
  }

  /**
   * Compute a hash code for an integer value.
   *
   * @param aSeed The initial seed value.
   * @param aInt The integer to be hashed.
   * @return int The hash that was generated from this method.
   */
  public static int hash(int aSeed, int aInt) {
    /*
     * Implementation Note
     * Note that byte and short are handled by this method, through
     * implicit conversion.
     */
    LOG.info("Hashing integer");
    return firstTerm(aSeed) + aInt;
  }

  /**
   * Compute a hash code for an object.
   * 
   * If <tt>aObject</tt> is an array, then each element may be a primitive or a
   * possibly-null object.
   *
   * @param aSeed The seed to build the hash from.
   * @param aObject The object to generate the hash from.
   *
   * @return int The computed hash.
   */
  public static int hash(final int aSeed, final Object aObject) {
    int result = aSeed;
    LOG.info("Hashing an object.");
    
    if (aObject == null) {
      /* A null object will be hashed against zero */
      result = hash(result, 0);
    } else if (!isArray(aObject)) {
      /* Hash against the returned from the aObject */
      result = hash(result, aObject.hashCode());
    } else {
      int length = Array.getLength(aObject);
      for (int idx = 0; idx < length; ++idx) {
        Object item = Array.get(aObject, idx);
        /* If an item in the array references the array itself, prevent infinite
         looping. */
        if (!(item == aObject)) {
          result = hash(result, item);
        }
      }
    }
    return result;
  }

  /**
   * The first term generator.
   *
   * @param aSeed The seed to generate the first term from.
   * @return int The generated seed.
   */
  private static int firstTerm(final int aSeed) {
    return ODD_PRIME * aSeed;
  }

  /**
   * Check to see if the class is an array.
   *
   * @param aObject The object to check for array.
   * @return boolean The result of the check.
   */
  private static boolean isArray(final Object aObject) {
    return aObject.getClass().isArray();
  }
}
