package de.duc.nguyen.occ.catalogfilter.models.sort;

public enum SortableField {

    URL("url"),
    LABEL("label"),
    DEFAULT("DEFAULT");

    public final String value;

    SortableField(String label) {
        this.value = label;
    }

    public static SortableField fromString(String text) throws SortInvalidPropertyException {
        for (SortableField field : SortableField.values()) {
            if (field.value.equals(text)) {
                return field;
            }
        }
        throw new SortFieldInvalidException("Sorting on field '" + text + "' is not supported");
    }
}
