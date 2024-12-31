package com.bestbuy.crudtest;

import com.bestbuy.model.ProductPojo;
import com.bestbuy.testbase.TestBaseProducts;
import com.bestbuy.utils.TestUtils;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class ProductsCRUDTest extends TestBaseProducts {
    static String name = "iPhone" + TestUtils.getRandomValue();
    static String type = "Mobile" + TestUtils.getRandomValue();
    static Double price = 1500.00;
    static String upc = TestUtils.getRandomValue();
    static Double shipping = 4.99;
    static String description = "iPhone 14 pro; 1500 mpx camera; Slim body";
    static String manufacturer = "Apple";
    static String model = "14 pro";
    static String url = "https://www.apple.com/uk/iphone-15-pro/";
    static String image = "https://www.apple.com/v/iphone-15-pro/c/images/overview/5x-zoom/pro-zoom_endframe__bpmc72f8qwgi_large.jpg";
    static int productId;

    @Test
    public void test001() {
        ProductPojo productPojo = new ProductPojo();
        productPojo.setName(name);
        productPojo.setType(type);
        productPojo.setPrice(price);
        productPojo.setUpc(upc);
        productPojo.setShipping(shipping);
        productPojo.setDescription(description);
        productPojo.setManufacturer(manufacturer);
        productPojo.setModel(model);
        productPojo.setUrl(url);
        productPojo.setImage(image);

        Response response = given().log().ifValidationFails()
                .header("Content-Type", "application/json")
                .when()
                .body(productPojo)
                .post();
        response.prettyPrint();
        response.then().log().ifValidationFails().statusCode(201);
    }

    @Test
    public void test002() {
        Response response = given()
                .when()
                .get();

        response.then().statusCode(200);

        response.prettyPrint();
    }

    @Test
    public void test003() {
        String name = ProductsCRUDTest.name + "Updated";
        String type = ProductsCRUDTest.type + "Updated";
        Double price = 1600.00;
        String upc = TestUtils.getRandomValue();
        Double shipping = 5.99;
        String description = ProductsCRUDTest.description + "Updated";
        String manufacturer = ProductsCRUDTest.manufacturer + "Updated";
        String model = ProductsCRUDTest.model + "Updated";
        String url = ProductsCRUDTest.url + "Updated";
        String image = ProductsCRUDTest.image + "Updated";

        ProductPojo productPojo = new ProductPojo();
        productPojo.setName(name);
        productPojo.setType(type);
        productPojo.setPrice(price);
        productPojo.setUpc(upc);
        productPojo.setShipping(shipping);
        productPojo.setDescription(description);
        productPojo.setManufacturer(manufacturer);
        productPojo.setModel(model);
        productPojo.setUrl(url);
        productPojo.setImage(image);

        Response response = given().log().ifValidationFails()
                .header("Content-Type", "application/json")
                .pathParam("id", 9999680)
                .when()
                .body(productPojo)
                .put("/{id}");
        response.prettyPrint();
        response.then().log().ifValidationFails().statusCode(200);

    }

    @Test
    public void test004(){
        given().log().ifValidationFails()
                .pathParam("id", 9999681)
                .when()
                .delete("/{id}")
                .then().log().ifValidationFails().statusCode(200);
        given()
                .pathParam("id", 9999681)
                .when()
                .get("/{id}")
                .then()
                .statusCode(404);
    }

}
