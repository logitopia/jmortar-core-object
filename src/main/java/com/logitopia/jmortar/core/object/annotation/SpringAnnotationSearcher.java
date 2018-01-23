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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * An <tt>AnnotationSearcher</tt> that uses a Spring-based implementation.
 */
@Component("springAnnotationSearcher")
public final class SpringAnnotationSearcher implements AnnotationSearcher {

    /**
     * The logger for this class.
     */
    public static final Logger LOG
            = LoggerFactory.getLogger(SpringAnnotationSearcher.class);

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Class> search(Class annotation) throws InvalidAnnotationException, ClassNotFoundException {
        if (!annotation.isAnnotation()) {
            throw new InvalidAnnotationException("The class [" + annotation.getName() + "] is NOT an annotation.");
        }
        List<Class> result = new ArrayList<>();

        // ::.. Calibrate the searcher ..::
        ClassPathScanningCandidateComponentProvider provider = createComponentScanner(annotation);

        // ::.. Run the Search ..::
        for (BeanDefinition beanDef : provider.findCandidateComponents("")) {
            Class model = Class.forName(beanDef.getBeanClassName());
            result.add(model);
        }

        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Class> search(Class annotation, Class without)
            throws InvalidAnnotationException, ClassNotFoundException {
        if (!annotation.isAnnotation()) {
            throw new InvalidAnnotationException("The class [" + annotation.getName() + "] is NOT an annotation.");
        } else if (!without.isAnnotation()) {
            throw new InvalidAnnotationException("The class [" + without.getName() + "] is NOT an annotation.");
        }
        List<Class> result = new ArrayList<>();

        // ::.. Calibrate the searcher ..::
        ClassPathScanningCandidateComponentProvider provider = createComponentScanner(annotation);

        // ::.. Run the Search ..::
        for (BeanDefinition beanDef : provider.findCandidateComponents("")) {
            Class model = Class.forName(beanDef.getBeanClassName());
            if(!model.isAnnotationPresent(without)) {
                result.add(model);
            }
        }

        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Class> search(List<Class> annotations) throws InvalidAnnotationException, ClassNotFoundException {
        for (Class annotation:annotations) {
            if (!annotation.isAnnotation()) {
                throw new InvalidAnnotationException("The class [" + annotation.getName() + "] is NOT an annotation.");
            }
        }
        List<Class> result = new ArrayList<>();

        // ::.. Calibrate the searcher ..::
        ClassPathScanningCandidateComponentProvider provider = createComponentScanner(annotations);

        // ::.. Run the Search ..::
        for (BeanDefinition beanDef : provider.findCandidateComponents("")) {
            Class model = Class.forName(beanDef.getBeanClassName());
            result.add(model);
        }

        return result;
    }

    @Override
    public List<Class> search(List<Class> annotations, List<Class> without) throws InvalidAnnotationException, ClassNotFoundException {
        for (Class annotation:annotations) {
            if (!annotation.isAnnotation()) {
                throw new InvalidAnnotationException("The class [" + annotation.getName() + "] is NOT an annotation.");
            }
        }
        List<Class> result = new ArrayList<>();

        // ::.. Calibrate the searcher ..::
        ClassPathScanningCandidateComponentProvider provider = createComponentScanner(annotations);

        // ::.. Run the Search ..::
        for (BeanDefinition beanDef : provider.findCandidateComponents("")) {
            Class model = Class.forName(beanDef.getBeanClassName());
            // TODO - Update this to loop through the annotations ... optimize by not looping above
            for (Class filter:without) {
                // Check the filter class
                if (!filter.isAnnotation()) {
                    throw new InvalidAnnotationException("The class [" + filter.getName() + "] is NOT an annotation.");
                }

                // Only return it if it is not present
                if(!model.isAnnotationPresent(filter)) {
                    result.add(model);
                }
            }
//            result.add(model);
        }

        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Field> searchClassForAnnotatedFields(Class annotation, Class clazzToSearch) throws InvalidAnnotationException {
        if (!annotation.isAnnotation()) {
            throw new InvalidAnnotationException("The class [" + annotation.getName() + "] is NOT an annotation.");
        }

        List<Field> result = new ArrayList<>();

        for(Field field : clazzToSearch.getDeclaredFields()){
            if (field.isAnnotationPresent(annotation)) {
                result.add(field);
            }
        }

        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<String> searchClassForAnnotatedFieldNames(Class annotation, Class clazzToSearch) throws InvalidAnnotationException {
        if (!annotation.isAnnotation()) {
            throw new InvalidAnnotationException("The class [" + annotation.getName() + "] is NOT an annotation.");
        }

        List<String> result = new ArrayList<>();

        for(Field field : clazzToSearch.getDeclaredFields()){
            if (field.isAnnotationPresent(annotation)) {
                result.add(field.getName());
            }
        }

        return result;
    }

    /**
     * Create the Spring component scanner.
     *
     * @param annotation The annotation that we want to search for.
     * @return The Spring <tt>ClassPathScanningCandidateComponentProvider</tt> that has been calibrate.
     */
    private ClassPathScanningCandidateComponentProvider createComponentScanner(final Class annotation) {
        ClassPathScanningCandidateComponentProvider provider
                = new ClassPathScanningCandidateComponentProvider(false);
        provider.addIncludeFilter(new AnnotationTypeFilter(annotation));
        return provider;
    }

    /**
     * Create the Spring component scanner for multiple annotations.
     *
     * @param annotations The annotations that we want to search for.
     * @return The Spring <tt>ClassPathScanningCandidateComponentProvider</tt> that has been calibrated.
     */
    private ClassPathScanningCandidateComponentProvider createComponentScanner(final List<Class> annotations) {
        ClassPathScanningCandidateComponentProvider provider
                = new ClassPathScanningCandidateComponentProvider(false);
        for (Class annotation: annotations) {
            provider.addIncludeFilter(new AnnotationTypeFilter(annotation));
        }
        return provider;
    }
}
