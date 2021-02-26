package de.duc.nguyen.occ.catalogfilter.models.sort;

public enum SortDirection {

    ASC("asc"),
    DESC("desc"),
    DEFAULT("default");

    public final String value;

    SortDirection(String label) {
        this.value = label;
    }

    public static SortDirection fromString(String text) {
        for (SortDirection field : SortDirection.values()) {
            if (field.value.equals(text)) {
                return field;
            }
        }
        return DEFAULT;
    }
}
