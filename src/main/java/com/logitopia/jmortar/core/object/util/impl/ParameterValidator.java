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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The <tt>ParameterValidator</tt> class is an implementation of a static
 * utility that validates parameters passed to a given method. It will check
 * that the parameters passed for issues like, null values and empty strings.
 *
 * @author &lt;Stephen Cheesley stephen.cheesley@gmail.com&gt;
 */
public class ParameterValidator {

  /**
   * The logger for this class.
   */
  public static final Logger LOG
          = LoggerFactory.getLogger(ParameterValidator.class);

  /**
   * Indicates that a check for null parameter is required.
   */
  public static final byte NULL_VALIDATE = 1;

  /**
   * Indicates that a check for an empty String parameter is required.
   */
  public static final byte STR_EMPTY_VALIDATE = 2;

  /**
   * Validate the parameters passed.
   *
   * @param testTypes The type of validation checks to carry out.
   * @param parameters The parameters to carry out the checks on.
   * @param parameterNames The names of the parameters that are being checked.
   * @param throwExceptions Should exceptions be thrown if values are missing.
   * @param clazz The class containing the method being validated.
   * @param methodName The name of the method being validated.
   */
  public static final void validateParameters(final byte[] testTypes,
          final Object[] parameters,
          final String[] parameterNames,
          final boolean throwExceptions,
          final Class clazz,
          final String methodName) {

    for (int i = 0; i < parameters.length; i++) {
      /* Check NULL FIRST. */
      if (isCheckPresent(testTypes, NULL_VALIDATE)) {
        /* Validate NULL. */
        validateNullParameter(parameters[i],
                parameterNames[i],
                throwExceptions,
                clazz,
                methodName);
      }

      /* Check (if string) that it is not empty. */
      if (isCheckPresent(testTypes, STR_EMPTY_VALIDATE)) {
        validateEmptyStringParameter(parameters[i],
                parameterNames[i],
                throwExceptions,
                clazz,
                methodName);
      }
    }
  }

  /**
   * Is the given check present.
   *
   * @param testTypes The types to check through.
   * @param testType The type to check.
   * @return The result flag (true == present).
   */
  private static boolean isCheckPresent(final byte[] testTypes,
          final byte testType) {
    boolean result = false;

    for (byte type : testTypes) {
      if (type == testType) {
        result = true;
      }
    }

    return result;
  }

  /**
   * Validate that the given parameter is not null. Log the expected output and
   * throw the necessary exceptions.
   *
   * @param parameter The parameter to validate.
   * @param parameterName The parameter name to validate.
   * @param throwExceptions Flag indicates exception requirement.
   * @param clazz The class that contains the method.
   * @param methodName The method being passed parameters.
   */
  private static void validateNullParameter(final Object parameter,
          final String parameterName,
          final boolean throwExceptions,
          final Class clazz,
          final String methodName) {
    if (parameter == null) {
      String className = clazz.getSimpleName();
      if (LOG.isDebugEnabled()) {
        LOG.debug(parameterName + " is null from call to " + methodName);
      }
        
      if (LOG.isTraceEnabled()) {
        StringBuilder trcMsg = new StringBuilder();
        trcMsg.append("[").append(className).append(".")
                .append(methodName).append("] ")
                .append("The parameter ").append(parameterName)
                .append(" is null.");
        LOG.trace(trcMsg.toString());
      }

      if (throwExceptions) {
        StringBuilder exceptionMsg = new StringBuilder();
        exceptionMsg.append(className).append(".").append(methodName)
                .append(" was passed a null parameter @ ")
                .append(parameterName);
        throw new IllegalArgumentException(exceptionMsg.toString());
      }
    }
  }

  /**
   * Validates to see whether, if the parameter passed is a String, it does not
   * contain an empty value.
   *
   * @param parameter The parameter to be validated.
   * @param parameterName The name of the parameter being validated.
   * @param throwExceptions Flag indicates exception requirement.
   * @param clazz The class that contains the method.
   * @param methodName The name of the method that has been executed.
   */
  private static void validateEmptyStringParameter(final Object parameter,
          final String parameterName,
          final boolean throwExceptions,
          final Class clazz,
          final String methodName) {
    if (parameter instanceof String) {
      String param = (String) parameter;
      if (param.isEmpty()) {
        String className = clazz.getSimpleName();
        if (LOG.isDebugEnabled()) {
          LOG.debug(parameterName + " is 'empty (blank)' from call to "
                  + methodName);
        }

        if (LOG.isTraceEnabled()) {
          StringBuilder trcMsg = new StringBuilder();
          trcMsg.append("[").append(className).append(".")
                  .append(methodName).append("] ")
                  .append("The parameter ").append(parameterName)
                  .append(" is empty / blank.");
          LOG.trace(trcMsg.toString());
        }

        if (throwExceptions) {
          StringBuilder exceptionMsg = new StringBuilder();
          exceptionMsg.append(className).append(".").append(methodName)
                  .append(" was passed an empty parameter @ ")
                  .append(parameterName);
          throw new IllegalArgumentException(exceptionMsg.toString());
        }
      }
    }
  }
}
