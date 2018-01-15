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
