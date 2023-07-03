package com.Automation.testCases.tests;

import com.Automation.controllers.BookingController;
import com.Automation.model.CreateBookingRequest;
import com.Automation.model.UpdateBookingRequest;
import com.Automation.testCases.dataProvider.BookingDataProvider;
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
public class UpdateBookingTests {

    private static final Logger LOG = LoggerFactory.getLogger(UpdateBookingTests.class);
    BookingController bookingController = new BookingController();

    @Test
    @UseDataProvider(value ="updateBookingDetails" , location = BookingDataProvider.class)
    @DisplayName("Updating of the first and last name")
    public void updateFirstAndLastName(Object UpdateRequest) throws Exception {

        UpdateBookingRequest updateBookingRequest = UpdateBookingRequest.class.cast(UpdateRequest);

        // Adding the headers in the request
        HashMap<String, String> headerValue = new HashMap<>();
        headerValue.put("accept", "application/json");
        headerValue.put("cookie", "token="+getAuth());

        Response response = bookingController.patchBooking(updateBookingRequest,headerValue,"1");

        LOG.info("ASSERTING THE API RESPONSE");
        assertEquals(200, response.getStatusCode());
        assertEquals("Amanda",response.path("firstname"));
        assertEquals("Amanda",response.path("lastname"));
        assertNotNull(response.path("totalprice"));
    }


    public String getAuth()throws Exception{
        String requestBody="{\n" +
                "    \"username\": \"admin\",\n" +
                "    \"password\": \"password123\"\n" +
                "}";

        Response response= bookingController.postAuth(requestBody);
        return response.path("token");
    }

}