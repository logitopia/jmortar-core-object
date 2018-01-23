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

package com.logitopia.jmortar.core.object.annotation;

import com.logitopia.jmortar.core.object.exception.InvalidAnnotationException;
import com.logitopia.jmortar.core.object.fixtures.*;
import com.logitopia.jmortar.core.test.AbstractUnitTest;
import org.junit.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * A unit test of <tt>SpringAnnotationSearcher</tt> that uses a tests the spring implementation of
 * <tt>AnnotationSearcher</tt>.
 */
public final class SpringAnnotationSearcherUnitTest
        extends AbstractUnitTest<SpringAnnotationSearcher> {

    /**
     * The logger for this class.
     */
    public static final Logger LOG
            = LoggerFactory.getLogger(SpringAnnotationSearcherUnitTest.class);

    /**
     * Default Constructor.
     */
    public SpringAnnotationSearcherUnitTest() {
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
        setSubject(new SpringAnnotationSearcher());
    }

    /**
     * {@inheritDoc}
     */
    @After
    public void tearDown() {
    }

    /**
     * Test that, given basic positive inputs, we get basic positive outputs.
     */
    @Test
    public void testBasicSuccess() {
        LOG.info("Test basic success");

        try {
            List<Class> result = getSubject().search(TestAnnotation.class);

            assertNotNull("Is the result null", result);
            assertEquals("Is the list of expected size", 2, result.size());

            Class resultClass = result.get(0);
            assertEquals("Does the result class match expectation", TestModelWithAnnotation.class, resultClass);
        } catch (InvalidAnnotationException e) {
            fail(e.getMessage());
        } catch (ClassNotFoundException e) {
            fail(e.getMessage());
        }
    }

    /**
     * Test that, given an annotation to filter on, we get basic positive outputs.
     */
    @Test
    public void testAnnotationFilterSuccess() {
        LOG.info("Test annotation filter success");

        try {
            List<Class> result = getSubject().search(TestAnnotation.class, FilteredAnnotation.class);

            assertNotNull("Is the result null", result);
            assertEquals("Is the list of expected size", 1, result.size());

            Class resultClass = result.get(0);
            assertEquals("Does the result class match expectation", TestModelWithAnnotation.class, resultClass);
        } catch (InvalidAnnotationException e) {
            fail(e.getMessage());
        } catch (ClassNotFoundException e) {
            fail(e.getMessage());
        }
    }

    /**
     * Test that, given basic positive inputs, we get basic positive outputs.
     */
    @Test
    public void testMultipleAnnotationSuccess() {
        LOG.info("Test multiple annotation success");

        List<Class> testAnnotations = new ArrayList<>();
        testAnnotations.add(TestAnnotation.class);
        testAnnotations.add(TestDifferentAnnotation.class);

        try {
            List<Class> result = getSubject().search(testAnnotations);

            assertNotNull("Is the result null", result);
            assertEquals("Is the list of expected size", 3, result.size());

            assertTrue("Does the list contain the TestModelWithAnnotation", result.contains(TestModelWithAnnotation.class));
            assertTrue("Does the list contain the TestModelWithDifferentAnnotation", result.contains(TestModelWithDifferentAnnotation.class));
        } catch (InvalidAnnotationException e) {
            fail(e.getMessage());
        } catch (ClassNotFoundException e) {
            fail(e.getMessage());
        }
    }

    /**
     * Test that, if we search for multiple annotations and we filter on the results, we get an expected outcome.
     */
    @Test
    public void testMultipleAnnotationFilteredSuccess() {
        LOG.info("Test multiple annotation filtered success");

        List<Class> testAnnotations = new ArrayList<>();
        testAnnotations.add(TestAnnotation.class);
        testAnnotations.add(TestDifferentAnnotation.class);

        List<Class> testWithout = new ArrayList<>();
        testWithout.add(FilteredAnnotation.class);

        try {
            List<Class> result = getSubject().search(testAnnotations, testWithout);

            assertNotNull("Is the result null", result);
            assertEquals("Is the list of expected size", 2, result.size());

            assertTrue("Does the list contain the TestModelWithAnnotation", result.contains(TestModelWithAnnotation.class));
            assertTrue("Does the list contain the TestModelWithDifferentAnnotation", result.contains(TestModelWithDifferentAnnotation.class));
        } catch (InvalidAnnotationException e) {
            fail(e.getMessage());
        } catch (ClassNotFoundException e) {
            fail(e.getMessage());
        }
    }

    /**
     * Test that, given basic positive inputs, we get basic positive outputs.
     */
    @Test
    public void testFieldAnnotationSearchSuccess() {
        LOG.info("Test field annotation search success.");

        try {
            List<Field> result = getSubject().searchClassForAnnotatedFields(FieldAnnotation.class, TestModelWithFieldAnnotation.class);

            assertNotNull("Is the result null", result);
            assertEquals("Is the list of expected size", 1, result.size());

            Field keyField = result.get(0);
            assertEquals("Has the search found the correct field?", "name", keyField.getName());
        } catch (InvalidAnnotationException e) {
            fail(e.getMessage());
        }
    }

    /**
     * Test that, given basic positive inputs, we get basic positive outputs.
     */
    @Test
    public void testFieldNameAnnotationSearchSuccess() {
        LOG.info("Test field name annotation search success.");

        try {
            List<String> result = getSubject().searchClassForAnnotatedFieldNames(FieldAnnotation.class, TestModelWithFieldAnnotation.class);

            assertNotNull("Is the result null", result);
            assertEquals("Is the list of expected size", 1, result.size());

            String keyField = result.get(0);
            assertEquals("Has the search found the correct field?", "name", keyField);
        } catch (InvalidAnnotationException e) {
            fail(e.getMessage());
        }
    }
}
