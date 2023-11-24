package com.minsait.Subject.util;

public enum Types {
    COMMON_CORE("Common_Core"),
    ELECTIVE("Elective"),
    SPECIALIZATION("Specialization");
    private final String type;
    Types(String type) {
        this.type = type;
    }
    public String getType() {
        return type;
    }
}
