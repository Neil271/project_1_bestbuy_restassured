package com.bestbuy.crudtest;

import com.bestbuy.constant.EndPoints;
import com.bestbuy.model.BestBuyPojo;
import com.bestbuy.testbase.TestBase;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class StoresCURDTest extends TestBase {

    static int id;
    static String createName = "palakk";
    static String createType = "BigBox233";
    static String createAddress = "135135644 Ridgedale Dr";
    static String createAddress2 = "";
    static String createCity = "Sussex";
    static String createState = "MN";
    static String createZip = "56788";

    @Test(priority = 1)
    public void getListOfStoresRecord() {
        ValidatableResponse response = given().log().all()
                .header("Content-Type", "application/json")
                .when()
                .get(EndPoints.GET_ALL_LIST_STORES)
                .then().log().all().statusCode(200);
        id = response.extract().path("data[0].id");
        //response.body("data[0].id",equalTo(id));

    }

    @Test(priority = 2)
    public void getStoresById() {
        ValidatableResponse response = given().log().all()
                .header("Content-Type", "application/json")
                .pathParam("id", 8931)
                .when()
                .get(EndPoints.GET_STORES_BY_ID)
                .then().log().all().statusCode(200);
    }

    @Test(priority = 3)
    public void createRecordForStore() {

        BestBuyPojo bestBuyPojo = new BestBuyPojo();
        bestBuyPojo.setName(createName);
        bestBuyPojo.setType(createType);
        bestBuyPojo.setAddress(createAddress);
        bestBuyPojo.setAddress2(createAddress2);
        bestBuyPojo.setCity(createCity);
        bestBuyPojo.setState(createState);
        bestBuyPojo.setZip(createZip);

        ValidatableResponse response = given().log().all()
                .header("Content-Type", "application/json")
                .when()
                .body(bestBuyPojo)
                .post(EndPoints.CREATE_RECORD)
                .then().log().all().statusCode(201);
    }

    @Test(priority = 4)
    public void updateStoresRecord() {
        BestBuyPojo bestBuyPojo = new BestBuyPojo();
        bestBuyPojo.setName(createName + "updated");
        bestBuyPojo.setType(createType + "updated");
        bestBuyPojo.setAddress(createAddress + "updated");
        bestBuyPojo.setAddress2(createAddress2);
        bestBuyPojo.setCity(createCity + "updated");
        bestBuyPojo.setState(createState);
        bestBuyPojo.setZip(createZip);

        ValidatableResponse response = given().log().all()
                .header("Content-Type", "application/json")
                .pathParam("id", id)
                .when()
                .body(bestBuyPojo)
                .put(EndPoints.UPDATE_RECORD)
                .then().log().all().statusCode(200);
    }

    @Test(priority = 5)
    public void partiallyUpdateStoresRecord() {
        BestBuyPojo bestBuyPojo = new BestBuyPojo();
        bestBuyPojo.setName(createName);
        bestBuyPojo.setType(createType);
        bestBuyPojo.setAddress(createAddress);
        bestBuyPojo.setAddress2(createAddress2);
        bestBuyPojo.setCity(createCity + "updated");
        bestBuyPojo.setState(createState);
        bestBuyPojo.setZip(createZip);

        ValidatableResponse response = given().log().all()
                .header("Content-Type", "application/json")
                .pathParam("id", id)
                .when()
                .body(bestBuyPojo)
                .patch(EndPoints.PARTIALLY_UPDATE_RECORD)
                .then().log().all().statusCode(200);
    }

    @Test(priority = 6)
    public void deleteRecord() {
        given().log().all()
                .header("Content-Type", "application/json")
                .pathParam("id", id)
                .when()
                .delete(EndPoints.DELETE_RECORD)
                .then().log().all()
                .statusCode(200);
    }
}
