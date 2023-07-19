package com.Automation.testCases.tests;

import com.Automation.controllers.BookingController;
import com.Automation.model.CreateBookingRequest;
import com.Automation.testCases.dataProvider.BookingDataProvider;
import com.Automation.util.Header;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.junit.jupiter.api.DisplayName;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.HashMap;

@RunWith(DataProviderRunner.class)
public class GetBookingTests {
    private static final Logger LOG = LoggerFactory.getLogger(GetBookingTests.class);
    BookingController bookingController = new BookingController();

    @Test
    @DisplayName("validating the Get Booking without Filter")
    public void getBookingDetailsWithoutFilter() {
        // Adding the headers in the request
        HashMap<String, String> headerValue = new HashMap<>();
        headerValue.put(Header.ACCEPT.getValue(), Header.JSON.getValue());
        Response response = bookingController.getBookingWithoutQueryParam(headerValue);

        LOG.info("ASSERTING THE API RESPONSE");
        assertEquals(200, response.getStatusCode());
        Assert.assertNotNull(response);
        Assert.assertNotNull(response.path("bookingid"));
    }

    @Test
    @UseDataProvider(value = "createBookingDetails", location = BookingDataProvider.class)
    @DisplayName("validating the Get Booking with one parameter")
    public void getBookingDetailsWithParameter(Object CreateRequest) {
        CreateBookingRequest createBookingRequest = CreateBookingRequest.class.cast(CreateRequest);
        // Adding the headers in the request
        HashMap<String, String> headerValue = new HashMap<>();
        headerValue.put(Header.ACCEPT.getValue(), Header.JSON.getValue());
        // Create a bookingId and get the bookingId from the response and pass it in the get Api request
        Response response = bookingController.postBooking(createBookingRequest, headerValue);
        // Make a get request with the bookingid obtained from the post request
        Response getresponse = bookingController.getBookingWithPathParam(headerValue, response.path("bookingid"));

        LOG.info("ASSERTING THE API RESPONSE");
        assertEquals(200, getresponse.getStatusCode());
        assertEquals(createBookingRequest.getFirstname(), getresponse.path("firstname"));
        assertEquals(createBookingRequest.getLastname(), getresponse.path("lastname"));
        assertEquals(createBookingRequest.getTotalprice(), getresponse.path("totalprice"));
        assertEquals(createBookingRequest.getDepositpaid(), getresponse.path("depositpaid"));
        assertEquals(createBookingRequest.getBookingdates().getCheckin(), getresponse.path("bookingdates.checkin"));
        assertEquals(createBookingRequest.getBookingdates().getCheckout(), getresponse.path("bookingdates.checkout"));

    }

    @Test
    @DisplayName("validating the Get Booking with two parameters")
    public void getBookingDetailsWithFirstNameAndLastName() {

        HashMap<String, String> headerValue = new HashMap<>();
        headerValue.put(Header.ACCEPT.getValue(), Header.JSON.getValue());
        // Adding the Query Parameters in the request
        HashMap<String, String> queryParam = new HashMap<>();
        queryParam.put("firstname", "Josh");
        queryParam.put("lastname", "Allen");
        Response getresponse = bookingController.getBookingWithQueryParam(headerValue, queryParam);

        LOG.info("ASSERTING THE API RESPONSE");
        assertEquals(200, getresponse.getStatusCode());
        assertNotNull(getresponse);

    }

    @Test
    @UseDataProvider(value = "createBookingDetails", location = BookingDataProvider.class)
    @DisplayName("Checking the response wih application/javascript as header")
    public void checkingOtherHeaderTypes(Object CreateRequest) {
        CreateBookingRequest createBookingRequest = CreateBookingRequest.class.cast(CreateRequest);
        // Adding the headers in the request
        HashMap<String, String> headerValue = new HashMap<>();
        headerValue.put(Header.ACCEPT.getValue(), Header.JSON.getValue());
        // Create a bookingId and get the bookingId from the response and pass it in the get Api request
        Response response = bookingController.postBooking(createBookingRequest, headerValue);
        // Make a get request with the bookingid obtained from the post request
        headerValue.put("accept", Header.JAVASCRIPT.getValue());
        Response getresponse = bookingController.getBookingWithPathParam(headerValue, response.path("bookingid"));

        LOG.info("ASSERTING THE API RESPONSE");
        assertEquals(418, getresponse.getStatusCode());
        Assert.assertNotNull(getresponse);
    }
}