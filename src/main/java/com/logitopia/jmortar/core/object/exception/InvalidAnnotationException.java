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
