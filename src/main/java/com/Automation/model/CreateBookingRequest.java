package com.Automation.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CreateBookingRequest {
    @JsonProperty("firstname")
    private String firstname;
    public Object getBookingdates() {
        return bookingdates;
    }
    public void setBookingdates(Object bookingdates) {
        this.bookingdates = bookingdates;
    }
    @JsonProperty("bookingdates")
    private Object bookingdates;
    @JsonProperty("lastname")
    private String lastname;
    @JsonProperty("totalprice")
    private Integer totalprice;
    public Integer getTotalprice() {
        return totalprice;
    }
    public void setTotalprice(Integer totalprice) {
        this.totalprice = totalprice;
    }
    public Boolean getDepositpaid() {
        return depositpaid;
    }
    public void setDepositpaid(Boolean depositpaid) {
        this.depositpaid = depositpaid;
    }
    public String getFirstname() {
        return firstname;
    }
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }
    public String getLastname() {
        return lastname;
    }
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
    public String getAdditionalneeds() {
        return additionalneeds;
    }
    public void setAdditionalneeds(String additionalneeds) {
        this.additionalneeds = additionalneeds;
    }
    @JsonProperty("depositpaid")
    private Boolean depositpaid;

    @JsonProperty("additionalneeds")
    private String additionalneeds;
}
