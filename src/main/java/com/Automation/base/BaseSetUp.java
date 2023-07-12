package com.Automation.base;

import com.Automation.model.CreateBookingRequest;
import io.restassured.response.Response;
import java.util.HashMap;
import java.util.Map;
import static io.restassured.RestAssured.given;
public class BaseSetUp {
    public Response response = null;
    public Response postApi(String url, CreateBookingRequest requestbody, String contentType, HashMap<String, ? extends Object> headerParameters) {
        response= given().contentType(contentType).
                body(requestbody).
                headers(headerParameters).
                        when().
                        log().all().
                        post(url).
                        then().
                        extract().response();
        return response;
    }

    public Response postApiForAuth(String url, String requestbody, String contentType) {
        response= given().contentType(contentType).
                body(requestbody).
                        when().
                        log().all().
                        post(url).
                        then().
                        extract().response();
        return response;
    }
    public Response postApiForBookingId(String url, String requestbody, String contentType) {
        response= given().contentType(contentType).
                body(requestbody).
                when().
                log().all().
                post(url).
                then().
                extract().response();
        return response;
    }

    public Response getApi(String url, String contentType, HashMap<String, ? extends Object> headerParameters) {
        response= given().contentType(contentType).
                headers(headerParameters).
                when().
                log().all().
                get(url).
                then().
                extract().response();
        return response;
    }
    public Response getApiWithPathParam(String url, String contentType, HashMap<String, ? extends Object> headerParameters,Integer pathParam) {
        response= given().contentType(contentType).
                headers(headerParameters).
                when().
                log().all().
                get(url,pathParam).
                then().
                extract().response();
        return response;
    }
    public Response patchApiWithPathParam(String url, CreateBookingRequest requestbody, String contentType, HashMap<String, ? extends Object> headerParameters, Integer pathParam) {
        response= given().contentType(contentType).
                body(requestbody).
                headers(headerParameters).
                when().
                log().all().
                patch(url,pathParam).
                then().
                extract().response();
        return response;
    }

    public Response putApiWithPathParam(String url, CreateBookingRequest requestbody, String contentType, HashMap<String, ? extends Object> headerParameters, Integer pathParam) {
        response= given().contentType(contentType).
                body(requestbody).
                headers(headerParameters).
                when().
                log().all().
                put(url,pathParam).
                then().
                extract().response();
        return response;
    }

    public Response deleteApi(String url, String contentType, HashMap<String, ? extends Object> headerParameters,Integer pathParam) {
        response= given().contentType(contentType).
                headers(headerParameters).
                when().
                log().all().
                delete(url,pathParam).
                then().
                extract().response();
        return response;
    }
    public Response getApiwithQueryParam(String url, String contentType, Map<String,? extends Object> headerParameters, Map<String,? extends Object> queryParam){
        response = given().contentType(contentType).
                headers(headerParameters).
                queryParams(queryParam).when().log().all()
                .get(url).then().extract().response();
        return response;
    }
}
