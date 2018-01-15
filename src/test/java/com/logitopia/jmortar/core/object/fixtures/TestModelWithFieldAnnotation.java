package com.logitopia.jmortar.core.object.fixtures;

/**
 * A test model where the field has been annotated.
 */
public final class TestModelWithFieldAnnotation {

    @FieldAnnotation
    private String name;

    @NegativeFieldAnnotation
    private String description;

    private int amount;

    public TestModelWithFieldAnnotation(String name, String description, int amount) {
        this.name = name;
        this.description = description;
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getAmount() {
        return amount;
    }
}
