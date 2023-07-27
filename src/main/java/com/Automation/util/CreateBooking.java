package com.Automation.util;

import com.Automation.controllers.BookingController;
import io.restassured.response.Response;

public class CreateBooking {
    BookingController bookingController = new BookingController();

    public Response bookingResponse() {
        String requestBody = "{\n" +
                "    \"firstname\": \"Peter\",\n" +
                "    \"bookingdates\": {\n" +
                "        \"checkin\": \"2018-01-01\",\n" +
                "        \"checkout\": \"2019-01-01\"\n" +
                "    },\n" +
                "    \"lastname\": \"Jenson\",\n" +
                "    \"totalprice\": 150,\n" +
                "    \"depositpaid\": true,\n" +
                "    \"additionalneeds\": \"Breakfast\"\n" +
                "}";
        Response response = bookingController.postBooking(requestBody);
        return response;
    }
}
