package com.Automation.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CreateBookingRequest {

    @Getter
    @Setter
    @JsonProperty("firstname")
    private String firstname;

    @Getter
    @Setter
    @JsonProperty("bookingdates")
    private Object bookingdates;

    @Getter
    @Setter
    @JsonProperty("lastname")
    private String lastname;

    @Getter
    @Setter
    @JsonProperty("totalprice")
    private Integer totalprice;

    @Getter
    @Setter
    @JsonProperty("depositpaid")
    private Boolean depositpaid;

    @Getter
    @Setter
    @JsonProperty("additionalneeds")
    private String additionalneeds;
}
