package com.Automation.controllers;

import com.Automation.base.BaseSetUp;
import com.Automation.constants.EndPoint;
import com.Automation.model.CreateBookingRequest;
import com.Automation.model.UpdateBookingRequest;
import com.Automation.util.ConfigReader;
import io.restassured.response.Response;
import java.util.HashMap;

public class BookingController {

        Response response=null;
        BaseSetUp baseSetUp= new BaseSetUp();
        private static final ConfigReader configReader = new ConfigReader();
        public Response postBooking(CreateBookingRequest createBookingRequest, HashMap<String, String> headerParam) throws Exception {
            final String url = configReader.getApplicationUrl() + EndPoint.BOOKING_DETAILS.CREATE_BOOKING;
            return baseSetUp.postApi(url, createBookingRequest, "application/json",headerParam);
    }
        public Response postAuth(String requestBody) throws Exception {
            final String url = configReader.getApplicationUrl() + EndPoint.BOOKING_DETAILS.AUTH_BOOKING;
            return baseSetUp.postApiForAuth(url, requestBody, "application/json");
    }
        public Response patchBooking(UpdateBookingRequest updateBookingRequest, HashMap<String, String> headerParam,String Param) throws Exception {
            final String url = configReader.getApplicationUrl() + EndPoint.BOOKING_DETAILS.UPDATE_BOOKING;
            return baseSetUp.patchApiWithPathParam(url, updateBookingRequest, "application/json",headerParam,Param);
    }
        public Response getBookingWithQueryParam( HashMap<String, String> headerParam,HashMap<String,String> queryParam) throws Exception {
            final String url = configReader.getApplicationUrl() + EndPoint.BOOKING_DETAILS.GET_BOOKING;
            return baseSetUp.getApiwithQueryParam(url,"application/json",headerParam,queryParam);
    }
      public Response getBookingWithoutQueryParam( HashMap<String, String> headerParam) throws Exception {
            final String url = configReader.getApplicationUrl() + EndPoint.BOOKING_DETAILS.GET_BOOKING;
            return baseSetUp.getApi(url,"application/json",headerParam);
    }
        public Response getBookingWithoutParams( HashMap<String, String> headerParam ) throws Exception {
            final String url = configReader.getApplicationUrl() + EndPoint.BOOKING_DETAILS.GET_BOOKING_PATH_PARAM;
            return baseSetUp.getApi(url,"application/json",headerParam);
    }
        public Response getBookingWithPathParam( HashMap<String, String> headerParam,String pathParam) throws Exception {
            final String url = configReader.getApplicationUrl() + EndPoint.BOOKING_DETAILS.GET_BOOKING_PATH_PARAM;
            return baseSetUp.getApiWithPathParam(url,"application/json",headerParam,pathParam);
    }
}
