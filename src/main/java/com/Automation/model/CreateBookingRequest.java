package com.Automation.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CreateBookingRequest {

    @JsonProperty("firstname")
    private String firstname;

    @JsonProperty("bookingdates")
    private BookingDatesRequest bookingdates;

    @JsonProperty("lastname")
    private String lastname;

    @JsonProperty("totalprice")
    private Integer totalprice;

    @JsonProperty("depositpaid")
    private Boolean depositpaid;

    @JsonProperty("additionalneeds")
    private String additionalneeds;


}
