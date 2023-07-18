package com.Automation.testCases.tests;

import com.Automation.controllers.BookingController;
import com.Automation.model.CreateBookingRequest;
import com.Automation.testCases.dataProvider.BookingDataProvider;
import com.Automation.util.CreateBooking;
import com.Automation.util.GetAuth;
import com.Automation.util.Header;
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
    public void updateFirstOrLastName(Object UpdateRequest)  {
        CreateBookingRequest createBookingRequest = CreateBookingRequest.class.cast(UpdateRequest);
        // Adding the headers in the request
        HashMap<String, String> headerValue = new HashMap<>();
        headerValue.put("accept", Header.JSON.getValue());
        headerValue.put("cookie", "token="+getAuth.getAuth());
        Response response = bookingController.patchBooking(createBookingRequest,headerValue,createBooking.getBookingId());

        LOG.info("ASSERTING THE API RESPONSE");
        assertEquals(200, response.getStatusCode());
        if(createBookingRequest.getFirstname()!= null){
            assertEquals(createBookingRequest.getFirstname(), response.path("firstname"));
        }
        if(createBookingRequest.getLastname() != null){
            assertEquals(createBookingRequest.getLastname(), response.path("lastname"));
        }
        if(createBookingRequest.getAdditionalneeds()!= null){
            assertEquals(createBookingRequest.getAdditionalneeds(), response.path("additionalneeds"));
        }
        if(createBookingRequest.getTotalprice() != null){
            assertEquals(createBookingRequest.getTotalprice(), response.path("totalprice"));
        }
        if(createBookingRequest.getDepositpaid() != null){
            assertEquals(createBookingRequest.getDepositpaid(), response.path("depositpaid"));
        }
        if(createBookingRequest.getBookingdates() != null){
            assertEquals(createBookingRequest.getBookingdates().getCheckin(), response.path("bookingdates.checkin"));
            assertEquals(createBookingRequest.getBookingdates().getCheckout(), response.path("bookingdates.checkout"));
        }
    }

    @Test
    @UseDataProvider(value ="updateBookingDetails" , location = BookingDataProvider.class)
    @DisplayName("Update Fail without Auth Token")
    public void updateFailWithoutAuthToken(Object UpdateRequest) {
        CreateBookingRequest createBookingRequest = CreateBookingRequest.class.cast(UpdateRequest);
        // Adding the headers in the request
        HashMap<String, String> headerValue = new HashMap<>();
        headerValue.put("accept", Header.JSON.getValue());
        Response response = bookingController.patchBooking(createBookingRequest,headerValue,createBooking.getBookingId());

        LOG.info("ASSERTING THE API RESPONSE");
        assertEquals(403, response.getStatusCode());
    }

}