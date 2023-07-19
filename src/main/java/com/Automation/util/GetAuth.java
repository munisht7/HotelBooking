package com.Automation.util;

import com.Automation.controllers.BookingController;
import io.restassured.response.Response;

public class GetAuth {
    BookingController bookingController = new BookingController();

    public String getAuth() {
        String requestBody = "{\n" +
                "    \"username\": \"admin\",\n" +
                "    \"password\": \"password123\"\n" +
                "}";
        Response response = bookingController.postAuth(requestBody);
        return response.path("token");
    }
}
