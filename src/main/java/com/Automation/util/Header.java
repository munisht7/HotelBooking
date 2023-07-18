package com.Automation.util;

public enum Header {
        TEXT("text/plain"),
        HTML("text/html"),
        JSON("application/json"),
        XML("application/xml");

        private final String value;

        Header(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }


