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

package com.logitopia.jmortar.core.object.exception;

/**
 * An <tt>Exception</tt> that was thrown, indicating that the class given wasn't either an annotation OR a valid
 * annotation.
 */
public class InvalidAnnotationException extends Exception {

    /**
     * Default Constructor. Create an exception giving a reason why it has been thrown.
     *
     * @param message The reason why the exception has been thrown.
     */
    public InvalidAnnotationException(final String message) {
        super(message);
    }
}
