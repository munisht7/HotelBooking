package com.Automation.testCases.tests;

import com.Automation.controllers.BookingController;
import com.Automation.model.CreateBookingRequest;
import com.Automation.testCases.dataProvider.BookingDataProvider;
import com.Automation.util.CreateBooking;
import com.Automation.util.GetAuth;
import com.Automation.util.Header;
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
public class UpdateBookingTests {
    private static final Logger LOG = LoggerFactory.getLogger(UpdateBookingTests.class);
    BookingController bookingController = new BookingController();
    GetAuth getAuth = new GetAuth();
    CreateBooking createBooking = new CreateBooking();

    @Test
    @UseDataProvider(value = "createBookingDetails", location = BookingDataProvider.class)
    @DisplayName("Create a new booking with different set of data ")
    public void updateBookingTest(Object CreateRequest) {

        CreateBookingRequest createBookingRequest = CreateBookingRequest.class.cast(CreateRequest);
        HashMap<String, String> headerValue = new HashMap<>();
        headerValue.put("accept", Header.JSON.getValue());
        headerValue.put("content-type", Header.JSON.getValue());
        headerValue.put("cookie", "token=" + getAuth.getAuth());
        Response response = bookingController.putBooking(createBookingRequest, headerValue, createBooking.getBookingId());

        LOG.info("ASSERTING THE API RESPONSE");
        assertEquals(200, response.getStatusCode());
        assertEquals(createBookingRequest.getFirstname(), response.path("firstname"));
        assertEquals(createBookingRequest.getLastname(), response.path("lastname"));
        assertEquals(createBookingRequest.getTotalprice(), response.path("totalprice"));
        assertEquals(createBookingRequest.getDepositpaid(), response.path("depositpaid"));
        assertEquals(createBookingRequest.getBookingdates().getCheckin(), response.path("bookingdates.checkin"));
        assertEquals(createBookingRequest.getBookingdates().getCheckout(), response.path("bookingdates.checkout"));
        if (createBookingRequest.getAdditionalneeds() != null) {
            assertEquals(createBookingRequest.getAdditionalneeds(), response.path("additionalneeds"));
        }
    }

    @Test
    @UseDataProvider(value = "createBookingDetails", location = BookingDataProvider.class)
    @DisplayName("Checking the response wih application/javascript as header")
    public void updateWithOtherHeaderTypes(Object CreateRequest) {

        CreateBookingRequest createBookingRequest = CreateBookingRequest.class.cast(CreateRequest);
        HashMap<String, String> headerValue = new HashMap<>();
        headerValue.put("accept", Header.JAVASCRIPT.getValue());
        headerValue.put("cookie", "token=" + getAuth.getAuth());
        Response response = bookingController.putBooking(createBookingRequest, headerValue, createBooking.getBookingId());

        LOG.info("ASSERTING THE API RESPONSE");
        assertEquals(418, response.getStatusCode());
        assertEquals("I'm a Teapot", response.body().print());
    }


    @Test
    @UseDataProvider(value = "createBookingDetailsForInternalServerErrors", location = BookingDataProvider.class)
    @DisplayName("Checking the response for 500")
    public void updateBookingTestWithoutMandatoryFields(Object CreateRequest) {

        CreateBookingRequest createBookingRequest = CreateBookingRequest.class.cast(CreateRequest);
        HashMap<String, String> headerValue = new HashMap<>();
        headerValue.put("accept", Header.JSON.getValue());
        headerValue.put("content-type", Header.JSON.getValue());
        headerValue.put("cookie", "token=" + getAuth.getAuth());
        Response response = bookingController.putBooking(createBookingRequest, headerValue, createBooking.getBookingId());

        LOG.info("ASSERTING THE API RESPONSE");
        assertEquals(400, response.getStatusCode());
        assertEquals("Bad Request", response.body().print());
    }
}
