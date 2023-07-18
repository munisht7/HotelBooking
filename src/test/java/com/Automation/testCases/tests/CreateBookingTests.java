package com.Automation.testCases.tests;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.HashMap;

import com.Automation.util.Header;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.Automation.controllers.BookingController;
import com.Automation.model.CreateBookingRequest;
import com.Automation.testCases.dataProvider.BookingDataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import io.restassured.response.Response;

@RunWith(DataProviderRunner.class)
public class CreateBookingTests {
    private static final Logger LOG = LoggerFactory.getLogger(CreateBookingTests.class);
    BookingController bookingController = new BookingController();

    @Test
    @UseDataProvider(value = "createBookingDetails", location =BookingDataProvider.class)
    @DisplayName("Create a new booking with different set of data ")
    public void createBookingTest(Object CreateRequest){

        CreateBookingRequest createBookingRequest = CreateBookingRequest.class.cast(CreateRequest);
        HashMap<String, String> headerValue= new HashMap<>();
        headerValue.put("accept", Header.JSON.getValue());
        Response response = bookingController.postBooking(createBookingRequest,headerValue);

        LOG.info("ASSERTING THE API RESPONSE");
        assertEquals(200, response.getStatusCode());
        assertNotNull(response.path("bookingid"));
        assertEquals(createBookingRequest.getFirstname(),response.path("booking.firstname"));
        assertEquals(createBookingRequest.getLastname(),response.path("booking.lastname"));
        assertEquals(createBookingRequest.getTotalprice(), response.path("booking.totalprice"));
        assertEquals(createBookingRequest.getDepositpaid(), response.path("booking.depositpaid"));
        assertNotNull( response.path("booking.bookingdates.checkin"));
        assertNotNull( response.path("booking.bookingdates.checkout"));
        if(createBookingRequest.getAdditionalneeds() != null) {
            assertEquals(createBookingRequest.getAdditionalneeds(), response.path("booking.additionalneeds"));
        }
    }
    @Test
    @UseDataProvider(value = "createBookingDetails", location =BookingDataProvider.class)
    @DisplayName("Checking the response wih application/javascript as header")
    public void createBookingWithOtherHeaderTypes(Object CreateRequest) {

        CreateBookingRequest createBookingRequest = CreateBookingRequest.class.cast(CreateRequest);
        HashMap<String, String> headerValue= new HashMap<>();
        headerValue.put("accept",Header.JAVASCRIPT.getValue());
        Response response = bookingController.postBooking(createBookingRequest,headerValue);

        LOG.info("ASSERTING THE API RESPONSE");
        assertEquals(418, response.getStatusCode());
    }
    @Test
    @UseDataProvider(value = "createBookingDetailsForInternalServerErrors", location =BookingDataProvider.class)
    @DisplayName("Checking the response for 500")
    public void createBookingTestWithoutMandatoryFields(Object CreateRequest){

        CreateBookingRequest createBookingRequest = CreateBookingRequest.class.cast(CreateRequest);
        HashMap<String, String> headerValue= new HashMap<>();
        headerValue.put("accept",Header.JSON.getValue());
        Response response = bookingController.postBooking(createBookingRequest,headerValue);
        LOG.info("ASSERTING THE API RESPONSE");
        assertEquals(500, response.getStatusCode());
    }
}
