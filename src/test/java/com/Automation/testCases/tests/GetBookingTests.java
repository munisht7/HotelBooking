package com.Automation.testCases.tests;

import com.Automation.controllers.BookingController;
import com.Automation.model.GetBookingRequest;
import com.tngtech.java.junit.dataprovider.UseDataProvider;

import io.restassured.response.Response;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.junit.jupiter.api.DisplayName;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;

public class GetBookingTests {
    
    private static final Logger LOG = LoggerFactory.getLogger(GetBookingTests.class);
    BookingController bookingController = new BookingController();

    @Test
    @DisplayName("validating the Get Booking without Filter")
    public void getBookingDetailsWithoutFilter() throws Exception {

        // Adding the headers in the request
        HashMap<String, String> headerValue = new HashMap<>();
        headerValue.put("accept", "application/json");

        Response response = bookingController.getBookingWithoutQueryParam(headerValue);

        LOG.info("ASSERTING THE API RESPONSE");
        assertEquals(200, response.getStatusCode());
        Assert.assertNotNull(response);
        Assert.assertNotNull(response.path("bookingid"));

    }

    @Test
    @DisplayName("validating the Get Booking with one paramter")
    public void getBookingDetailsWithParameter() throws Exception {

        // Adding the headers in the request
        HashMap<String, String> headerValue = new HashMap<>();
        headerValue.put("accept", "application/json");

        Response response = bookingController.getBookingWithPathParam(headerValue,"100");

        LOG.info("ASSERTING THE API RESPONSE");
        assertEquals(200, response.getStatusCode());
        assertNotNull(response.path("firstname"));
        assertNotNull(response.path("lastname"));
        assertNotNull(response.path("totalprice"));
        assertTrue(response.path("depositpaid"));
        assertNotNull(response.path("bookingdates"));

    }

    @Test
    @DisplayName("validating the Get Booking with two paramters")
    public void getBookingDetailsWithFirstNameAndLastName() throws Exception {

        // Adding the headers in the request
        HashMap<String, String> headerValue = new HashMap<>();
        headerValue.put("accept", "application/json");

        // Adding the Query Parameters in the request
        HashMap<String, String> queryParam = new HashMap<>();
        queryParam.put("firstname", "Josh");
        queryParam.put("lastname", "Allen");

        Response response = bookingController.getBookingWithQueryParam(headerValue,queryParam);

        LOG.info("ASSERTING THE API RESPONSE");
        assertEquals(200, response.getStatusCode());
        Assert.assertNotNull(response);
        Assert.assertNotNull(response.path("bookingid"));

    }

    @Test
    @DisplayName("Checking the response wih application/javascript as header")
    public void checkingOtherHeaderTypes() throws Exception {

        // Adding the headers in the request
        HashMap<String, String> headerValue = new HashMap<>();
        headerValue.put("accept", "application/javascript");

        Response response = bookingController.getBookingWithPathParam(headerValue,"1001");

        LOG.info("ASSERTING THE API RESPONSE");
        assertEquals(418, response.getStatusCode());
        Assert.assertNotNull(response);

    }
}