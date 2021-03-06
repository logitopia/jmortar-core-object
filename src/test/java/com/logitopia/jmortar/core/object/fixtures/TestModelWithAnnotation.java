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

package com.logitopia.jmortar.core.object.fixtures;

/**
 * A "model" class that is used for testing.
 */
@TestAnnotation
public class TestModelWithAnnotation {

    /**
     * The name of something.
     */
    private String name;

    /**
     * Default Constructor.
     *
     * @param newName The name to construct with.
     */
    public TestModelWithAnnotation(final String newName) {
        name = newName;
    }

    /**
     * Get the name of something.
     *
     * @return The name.
     */
    public String getName() {
        return name;
    }
}
