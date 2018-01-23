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
package com.logitopia.jmortar.core.object.util.impl.test;

import java.util.LinkedList;
import java.util.List;
import org.apache.log4j.Appender;
import org.apache.log4j.Layout;
import org.apache.log4j.Logger;
import org.apache.log4j.spi.ErrorHandler;
import org.apache.log4j.spi.Filter;
import org.apache.log4j.spi.LoggingEvent;

/**
 * The <tt>TestAppender</tt> class is an implementation of a log4j Appender that
 * stores input for validation.
 *
 * @author &lt;Stephen Cheesley stephen.cheesley@gmail.com&gt;
 */
public class TestAppender implements Appender {

  /**
   * The logger for this class.
   */
  public static final Logger LOG
          = Logger.getLogger(TestAppender.class);

  /**
   * The logging events that are appended.
   */
  private List<LoggingEvent> appends;

  /**
   * Default Constructor.
   */
  public TestAppender() {
    appends = new LinkedList<>();
  }

  /**
   * Get the appends made to this appender.
   *
   * @return The LoggingEvents appended.
   */
  public List<LoggingEvent> getAppends() {
    return appends;
  }
  
  /**
   * Reset the logger.
   */
  public void reset() {
    appends.clear();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void addFilter(Filter filter) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Filter getFilter() {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void clearFilters() {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void close() {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void doAppend(LoggingEvent le) {
    this.appends.add(le);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String getName() {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void setErrorHandler(ErrorHandler eh) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ErrorHandler getErrorHandler() {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void setLayout(Layout layout) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Layout getLayout() {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void setName(String string) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  /**
   * {@inheritDoc}
   */
  /**
   * {@inheritDoc}
   */
  @Override
  public boolean requiresLayout() {
    throw new UnsupportedOperationException("Not supported yet.");
  }
}
