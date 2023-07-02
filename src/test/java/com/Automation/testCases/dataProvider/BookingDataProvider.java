package com.Automation.testCases.dataProvider;

import com.Automation.base.BaseClassProvider;
import com.Automation.model.CreateBookingRequest;
import com.tngtech.java.junit.dataprovider.DataProvider;

public class BookingDataProvider extends BaseClassProvider {
    
    @DataProvider
    public static Object[][] createBookingDetails() {
        return testDataGenerator("src/test/resources/tests_data/CreateBookingCreationData.yaml",
                CreateBookingRequest[].class);
    }
}
