package com.Automation.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BaseModel {
    private String expectedResponseCode = "200";
    @JsonProperty("expectedResponseCode")
    public int getExpectedResponseCode() {
        return Integer.parseInt(this.expectedResponseCode);
    }
    @JsonProperty("expectedResponseCode")
    public void setExpectedResponseCode(String expectedResponseCode) {
        this.expectedResponseCode = expectedResponseCode;
    }
}