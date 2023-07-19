package com.Automation.util;

public enum Header {
    JSON("application/json"),
    JAVASCRIPT("application/javascript"),
    COOKIE("cookie"),
    CONTENT("content-type"),
    ACCEPT("accept");
    private final String value;

    Header(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}


