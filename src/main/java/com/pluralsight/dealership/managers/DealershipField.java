package com.pluralsight.dealership.managers;

public enum DealershipField {
    NAME("Name"),
    ADDRESS("Address"),
    PHONE("Phone");

    private final String columnName;

    DealershipField(String columnName) {
        this.columnName = columnName;
    }

    public String getColumnName() {
        return columnName;
    }
}
