package com.logitopia.jmortar.core.object.annotation;

import com.logitopia.jmortar.core.object.exception.InvalidAnnotationException;

import java.lang.reflect.Field;
import java.util.List;

/**
 * An searcher that will look through the runtime to find classes that contain an annotation.
 */
public interface AnnotationSearcher {

    /**
     * Search for a given annotation in the classes present at runtime.
     *
     * @param annotation The annotation to find.
     * @return A list of the classes that contain that annotation.
     * @throws InvalidAnnotationException
     * @throws ClassNotFoundException     when the model class attached to the annotation does not appear to be valid.
     */
    List<Class> search(Class annotation) throws InvalidAnnotationException, ClassNotFoundException;

    /**
     * Search for a given annotation in the classes present at runtime. In this method count out the results that
     * contain the given annotation.
     *
     * @param annotation The annotation to find.
     * @param without    An annotation that we filter results by.
     * @return A list of the classes that contain that annotation.
     * @throws InvalidAnnotationException
     * @throws ClassNotFoundException     when the model class attached to the annotation does not appear to be valid.
     */
    List<Class> search(Class annotation, Class without) throws InvalidAnnotationException, ClassNotFoundException;

    /**
     * Search for a list of given annotations in the classes present at runtime.
     *
     * @param annotations The annotations to find.
     * @return A list of classes that contain the given annotations.
     * @throws InvalidAnnotationException
     * @throws ClassNotFoundException     when the model class attached to the annotation does not appear to be valid.
     */
    List<Class> search(List<Class> annotations) throws InvalidAnnotationException, ClassNotFoundException;

    /**
     * Search for a list of given annotations in the classes present at runtime. In this method count out the results
     * that contain the given annotation.
     *
     * @param annotations The annotations to find.
     * @param without Annotations we will filter results by.
     * @return A list of classes that contain the given annotations.
     * @throws InvalidAnnotationException
     * @throws ClassNotFoundException     when the model class attached to the annotation does not appear to be valid.
     */
    List<Class> search(List<Class> annotations, List<Class> without) throws InvalidAnnotationException, ClassNotFoundException;

    /**
     * Search for a list of fields that are annotated with a given annotation.
     *
     * @param annotation    The annotation to find.
     * @param clazzToSearch The class to search for the listed annotation.
     * @return A list of fields that are contained within the given class that are annotated with the provided annotation.
     * @throws InvalidAnnotationException
     */
    List<Field> searchClassForAnnotatedFields(Class annotation, Class clazzToSearch) throws InvalidAnnotationException;

    /**
     * Search for a list of field names that are annotated with a given annotation.
     *
     * @param annotation    The annotation to find.
     * @param clazzToSearch The class to search for the listed annotation.
     * @return A list of field names that are contained within the given class that are annotated with the provided annotation.
     * @throws InvalidAnnotationException
     */
    List<String> searchClassForAnnotatedFieldNames(Class annotation, Class clazzToSearch) throws InvalidAnnotationException;
}
