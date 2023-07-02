# HotelBooking

## Pre-requisites for Setup Configuration
The following components are required to get started with automation:
* Install Java(JDK)
* Install IntelliJ/Eclipse
* Install Maven
* Configuring RestAssured with IntelliJ

## Getting Started
* Copy the repository into your local machine using the github url -> https://github.com/munisht7/HotelBooking.git.
* Checkout **main** branch for the latest changes.

## Built With
* Maven - Dependency management
* JUnit - Testing framework

## Defining the properties
* Properties can be configured in the **config.properties** file, refer to the path : **src/main/java/com/Automation/config/config.properties**
* For example, if you declare the following property in your config.properties file:
  `applicationUrl="https://restful-booker.herokuapp.com"`
  the property applicationUrl will be set to v3 version of the swagger API's.

## Run tests locally
* Right click one of the feature files at **src/test/java/com.apiAutomation.testCases/tests**
* Select **"Run"** or **"Debug"** to start the test.

## Run tests using terminal
* Execute the test cases through terminal using maven by using the following command : mvn test

## Run the JUnit Suite
* Right click one of the feature files at **src/test/resources/JUnit.xml**
* Select **"Run"** or **"Debug"** to start the test.


## Referred to the following links for the project

* JUnit Documentation : https://junit.org/junit5/docs/current/user-guide/
* Maven Download : https://maven.apache.org/download.cgi
* Maven Documentation : https://maven.apache.org/guides/
* API Documentation: http://restful-booker.herokuapp.com/apidoc/index.html#api-Booking-GetBookings
