package com.Automation.controllers;

import com.Automation.base.BaseSetUp;
import com.Automation.constants.EndPoint;
import com.Automation.model.CreateBookingRequest;
import com.Automation.util.ConfigReader;
import com.Automation.util.Header;
import io.restassured.response.Response;
import java.util.HashMap;

public class BookingController {

        Response response=null;
        BaseSetUp baseSetUp= new BaseSetUp();
        private static final ConfigReader configReader = new ConfigReader();
        public Response postBooking(CreateBookingRequest createBookingRequest, HashMap<String, String> headerParam) {
            final String url = configReader.getApplicationUrl() + EndPoint.BOOKING_DETAILS.CREATE_BOOKING;
            return baseSetUp.postApi(url, createBookingRequest, Header.JSON.getValue(),headerParam);
    }

        public Response postAuth(String requestBody)  {
            final String url = configReader.getApplicationUrl() + EndPoint.BOOKING_DETAILS.AUTH_BOOKING;
            return baseSetUp.postApiForAuth(url, requestBody, Header.JSON.getValue());
    }

    public Response postBooking(String requestBody)  {
        final String url = configReader.getApplicationUrl() + EndPoint.BOOKING_DETAILS.CREATE_BOOKING;
        return baseSetUp.postApiForBookingId(url, requestBody, Header.JSON.getValue());
    }
        public Response patchBooking(CreateBookingRequest createBookingRequest, HashMap<String, String> headerParam, Integer Param)  {
            final String url = configReader.getApplicationUrl() + EndPoint.BOOKING_DETAILS.UPDATE_BOOKING;
            return baseSetUp.patchApiWithPathParam(url, createBookingRequest, Header.JSON.getValue(),headerParam,Param);
    }

    public Response putBooking(CreateBookingRequest createBookingRequest, HashMap<String, String> headerParam, Integer Param)  {
        final String url = configReader.getApplicationUrl() + EndPoint.BOOKING_DETAILS.UPDATE_BOOKING;
        return baseSetUp.putApiWithPathParam(url, createBookingRequest, Header.JSON.getValue(),headerParam,Param);
    }
        public Response getBookingWithQueryParam( HashMap<String, String> headerParam,HashMap<String,String> queryParam) {
            final String url = configReader.getApplicationUrl() + EndPoint.BOOKING_DETAILS.GET_BOOKING;
            return baseSetUp.getApiwithQueryParam(url,Header.JSON.getValue(),headerParam,queryParam);
    }
      public Response getBookingWithoutQueryParam( HashMap<String, String> headerParam) {
            final String url = configReader.getApplicationUrl() + EndPoint.BOOKING_DETAILS.GET_BOOKING;
            return baseSetUp.getApi(url,Header.JSON.getValue(),headerParam);
    }
        public Response getBookingWithPathParam( HashMap<String, String> headerParam,Integer pathParam) {
            final String url = configReader.getApplicationUrl() + EndPoint.BOOKING_DETAILS.GET_BOOKING_PATH_PARAM;
            return baseSetUp.getApiWithPathParam(url,Header.JSON.getValue(),headerParam,pathParam);
    }
        public Response deleteBooking(HashMap<String, String> headerParam,Integer pathParam){
            final String url = configReader.getApplicationUrl() + EndPoint.BOOKING_DETAILS.DELETE_BOOKING;
            return baseSetUp.deleteApi(url,Header.JSON.getValue(),headerParam,pathParam );
        }
}
