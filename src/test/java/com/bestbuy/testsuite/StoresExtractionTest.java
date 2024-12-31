package com.bestbuy.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.given;

public class StoresExtractionTest {
    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 3030;
        response = given()
                .when()
                .get("/stores")
                .then().statusCode(200);
    }

    // Extract the limit
    @Test
    public void test001() {
        int limit = response.extract().path("limit");

        System.out.println("The value of limit is : " + limit);
    }

    // Extract the total
    @Test
    public void test002() {
        int total = response.extract().path("total");

        System.out.println("The value of total is : " + total);
    }

    // Extract the name of 5th store
    @Test
    public void test003() {
        String name = response.extract().path("data[4].name");

        System.out.println("The name of 5th store is : " + name);
    }

    // Extract the names of all the store
    @Test
    public void test004() {
        List<String> names = response.extract().path("data.name");

        System.out.println("The names of all the stores are : " + names);
    }

    // Extract the  storeId of all the store
    @Test
    public void test005() {
        List<String> storeIds = response.extract().path("data.id");

        System.out.println("The  storeId of all the stores are : " + storeIds);
    }

    // Print the size of the data list
    @Test
    public void test006() {
        List<String> dataList = response.extract().path("data");

        System.out.println("The  size of the dataList is : " + dataList.size());
    }

    // Get all the value of the store where store name = St Cloud
    @Test
    public void test007() {
        List<String> dataList = response.extract().path("data.findAll{it.name == 'St Cloud'}");

        System.out.println("All the value of the store where store name = St Cloud is : " + dataList);
    }

    // Get the address of the store where store name = Rochester
    @Test
    public void test008() {
        List<String> address = response.extract().path("data.findAll{it.name == 'Rochester'}.address");

        System.out.println("The address of the store where store name = Rochester is : " + address);
    }

    // Get all the services of 8th store
    @Test
    public void test009() {
        List<String> services = response.extract().path("data[7].services");

        System.out.println("All the services of 8th store are : " + services);
    }

    // Get storeservices of the store where service name = Windows Store
    @Test
    public void test010() {
        List<HashMap<String, ?>> services = response.extract().path("data.services*.findAll{it.name=='Windows Store'}.storeservices");
        System.out.println("storeservices of the store where service name = Windows Store are : " + services);
    }

    // Get all the storeId of all the store
    @Test
    public void test011() {
        List<Object> storeIds = response.extract().path("data.services.storeservices.storeId");

        System.out.println("All the storeId of all the store are : " + storeIds);
    }

    // Get id of all the store
    @Test
    public void test012() {
        List<Object> storeIds = response.extract().path("data.id");

        System.out.println("Id of all the store are : " + storeIds);
    }

    // Get id of all the store
    @Test
    public void test013() {
        List<Object> name = response.extract().path("data.findAll{it.state == 'ND'}.name");

        System.out.println("Store names Where state = ND are : " + name);
    }

    // Find the Total number of services for the store where store name = Rochester
    @Test
    public void test014() {
        List<HashMap<String, ?>> data = response.extract().path("data.find{it.name == 'Rochester'}.services");

        System.out.println("The Total number of services for the store where store name = Rochester are : " + data.size());
    }

    // Find the createdAt for all services whose name = “Windows Store”
    @Test
    public void test015() {
        List<String> data = response.extract().path("data.services*.findAll{it.name == 'Windows Store'}.storeservices.createdAt");

        System.out.println("The createdAt for all services whose name = “Windows Store” are : " + data);
    }

    // Find the name of all services Where store name = “Fargo”
    @Test
    public void test016() {
        List<String> data = response.extract().path("data.findAll{it.name == 'Fargo'}.services.name");

        System.out.println("The name of all services Where store name = “Fargo” : " + data);
    }

    // Find the zip of all the store
    @Test
    public void test017() {
        List<String> data = response.extract().path("data.zip");

        System.out.println("The zip of all the store are " + data);
    }

    // Find the zip of store name = Roseville
    @Test
    public void test018() {
        List<String> data = response.extract().path("data.findAll{it.name == 'Roseville'}.zip");

        System.out.println("The zip of the store name = Roseville are " + data);
    }

    // Find the storeservices details of the service name = Magnolia Home Theater
    @Test
    public void test019() {
        List<String> data = response.extract().path("data.services*.find{it.name == 'Magnolia Home Theater'}.storeservices");

        System.out.println("The storeservices details of the service name = Magnolia Home Theater are " + data);
    }

    // Find the lat of all the stores
    @Test
    public void test020() {
        List<String> data = response.extract().path("data.lat");

        System.out.println("The lat of all the stores are " + data);
    }

}
