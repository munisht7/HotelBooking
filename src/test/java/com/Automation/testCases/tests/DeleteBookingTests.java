package com.Automation.testCases.tests;

import com.Automation.controllers.BookingController;
import com.Automation.model.CreateBookingRequest;
import com.Automation.testCases.dataProvider.BookingDataProvider;
import com.Automation.util.GetAuth;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import io.restassured.response.Response;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.HashMap;
import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(DataProviderRunner.class)
public class DeleteBookingTests {
    private static final Logger LOG = LoggerFactory.getLogger(DeleteBookingTests.class);
    BookingController bookingController = new BookingController();
    GetAuth getAuth = new GetAuth();

    @Test
    @UseDataProvider(value = "createBookingDetails", location = BookingDataProvider.class)
    @DisplayName("Validating the delete booking with different set of data ")
    public void deleteBookingTest(Object CreateRequest) throws Exception {
        CreateBookingRequest createBookingRequest = CreateBookingRequest.class.cast(CreateRequest);
        HashMap<String, String> headerValue= new HashMap<>();
        headerValue.put("accept","application/json");
        // Create a bookingId and get the bookingId from the response and pass it in the delete Api request
        Response response = bookingController.postBooking(createBookingRequest,headerValue);
        // Adding a cookie header for the auth
        headerValue.put("cookie", "token="+getAuth.getAuth());
        // Make a request for Delete API
        Response deleteResponse= bookingController.deleteBooking(headerValue,response.path("bookingid"));
        LOG.info("ASSERTING THE API RESPONSE");
        assertEquals(201, deleteResponse.getStatusCode());
        // checking the get response to validate that the deleted booking is not fetched
        Response getresponse = bookingController.getBookingWithPathParam(headerValue,response.path("bookingid"));
        LOG.info("ASSERTING 404 TO CHECK THAT DELETED BOOKING IS NOT BEING FETCHED");
        assertEquals(404, getresponse.getStatusCode());
        assertEquals("Not Found", getresponse.body().print());
    }

    @Test
    @UseDataProvider(value = "createBookingDetails", location = BookingDataProvider.class)
    @DisplayName("Validating the delete booking without Auth Token ")
    public void deleteBookingWIthoutAuthToken(Object CreateRequest) throws Exception {
        CreateBookingRequest createBookingRequest = CreateBookingRequest.class.cast(CreateRequest);
        HashMap<String, String> headerValue= new HashMap<>();
        headerValue.put("accept","application/json");
        // Create a bookingId and get the bookingId from the response and pass it in the delete Api request
        Response response = bookingController.postBooking(createBookingRequest,headerValue);
        //Make a request for Delete API
        Response deleteResponse= bookingController.deleteBooking(headerValue,response.path("bookingid"));

        LOG.info("ASSERTING THE API RESPONSE");
        assertEquals(403, deleteResponse.getStatusCode());
    }

}
