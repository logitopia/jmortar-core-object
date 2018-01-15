package com.logitopia.jmortar.core.object.fixtures;

@FilteredAnnotation
@TestAnnotation
public class TestModelWithFilteredAnnotation {

    /**
     * The name of something.
     */
    private String name;

    /**
     * Default Constructor.
     *
     * @param newName The name to construct with.
     */
    public TestModelWithFilteredAnnotation(final String newName) {
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
