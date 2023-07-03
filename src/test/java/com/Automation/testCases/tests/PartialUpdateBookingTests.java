package com.Automation.testCases.tests;

import com.Automation.controllers.BookingController;
import com.Automation.model.PartialUpdateBookingRequest;
import com.Automation.testCases.dataProvider.BookingDataProvider;
import com.Automation.util.CreateBooking;
import com.Automation.util.GetAuth;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import io.restassured.response.Response;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import java.util.HashMap;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
@RunWith(DataProviderRunner.class)
public class PartialUpdateBookingTests {
    private static final Logger LOG = LoggerFactory.getLogger(PartialUpdateBookingTests.class);
    BookingController bookingController = new BookingController();
    GetAuth getAuth = new GetAuth();
    CreateBooking createBooking = new CreateBooking();

    @Test
    @UseDataProvider(value ="updateBookingDetails" , location = BookingDataProvider.class)
    @DisplayName("Updating the first and last name")
    public void updateFirstAndLastName(Object UpdateRequest) throws Exception {
        PartialUpdateBookingRequest partialUpdateBookingRequest = PartialUpdateBookingRequest.class.cast(UpdateRequest);
        // Adding the headers in the request
        HashMap<String, String> headerValue = new HashMap<>();
        headerValue.put("accept", "application/json");
        headerValue.put("cookie", "token="+getAuth.getAuth());
        Response response = bookingController.patchBooking(partialUpdateBookingRequest,headerValue,createBooking.getBookingId());

        LOG.info("ASSERTING THE API RESPONSE");
        assertEquals(200, response.getStatusCode());
        assertEquals("Amanda",response.path("firstname"));
        assertEquals("Amanda",response.path("lastname"));
        assertNotNull(response.path("totalprice"));
    }

    @Test
    @UseDataProvider(value ="updateBookingDetails" , location = BookingDataProvider.class)
    @DisplayName("Update Fail without Auth Token")
    public void updateFailWithoutAuthToken(Object UpdateRequest) throws Exception {
        PartialUpdateBookingRequest partialUpdateBookingRequest = PartialUpdateBookingRequest.class.cast(UpdateRequest);
        // Adding the headers in the request
        HashMap<String, String> headerValue = new HashMap<>();
        headerValue.put("accept", "application/json");
        Response response = bookingController.patchBooking(partialUpdateBookingRequest,headerValue,createBooking.getBookingId());

        LOG.info("ASSERTING THE API RESPONSE");
        assertEquals(403, response.getStatusCode());
    }

}