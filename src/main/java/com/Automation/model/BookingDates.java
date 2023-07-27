package com.Automation.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BookingDates {

    @JsonProperty("checkin")
    private String checkin;

    @JsonProperty("checkout")
    private String checkout;
}
