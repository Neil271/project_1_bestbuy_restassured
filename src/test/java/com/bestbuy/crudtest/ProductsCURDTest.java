package com.bestbuy.crudtest;

import com.bestbuy.constant.EndPoints;
import com.bestbuy.model.BestBuyPojo1;
import com.bestbuy.testbase.TestBase;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class ProductsCURDTest extends TestBase {

    static int id;
    static String createName="Duracell - AAA Batteries (4-Pack)";
    static String createType="HardGood";
    static Double createPrice=4.99;
    static String createUpc="041333424019";
    static int createShipping=0;
    static String createDescription="Compatible with select electronic devices; AAA size; DURALOCK Power Preserve technology; 4-pack";
    static String createManufacturer="Duracell";
    static String createModel="MN2400B4Z";
    static String createUrl="http://www.bestbuy.com/site/duracell-aaa-batteries-4-pack/43900.p?id=1051384074145&skuId=43900&cmp=RMXCC";
    static String createImage="http://img.bbystatic.com/BestBuy_US/images/products/4390/43900_sa.jpg";

    @Test(priority = 1)
    public void getListOfProductsRecord() {
        ValidatableResponse response = given().log().all()
                .header("Content-Type", "application/json")
                .when()
                .get(EndPoints.GET_ALL_LIST_PRODUCTS)
                .then().log().all().statusCode(200);
        id = response.extract().path("data[0].id");
    }

    @Test(priority = 2)
    public void getProductsById() {
        ValidatableResponse response = given().log().all()
                .header("Content-Type", "application/json")
                .pathParam("id", id)
                .when()
                .get(EndPoints.GET_PRODUCTS_BY_ID)
                .then().log().all().statusCode(200);
    }

    @Test(priority = 3)
    public void createProductsRecord() {

        BestBuyPojo1 bestBuyPojo1 = new BestBuyPojo1();
        bestBuyPojo1.setName(createName);
        bestBuyPojo1.setType(createType);
        bestBuyPojo1.setPrice(createPrice);
        bestBuyPojo1.setUpc(createUpc);
        bestBuyPojo1.setShipping(createShipping);
        bestBuyPojo1.setDescription(createDescription);
        bestBuyPojo1.setManufacturer(createManufacturer);
        bestBuyPojo1.setModel(createModel);
        bestBuyPojo1.setUrl(createUrl);
        bestBuyPojo1.setImage(createImage);

        ValidatableResponse response = given().log().all()
                .header("Content-Type", "application/json")
                .when()
                .body(bestBuyPojo1)
                .post(EndPoints.CREATE_PRODUCTS_RECORD)
                .then().log().all().statusCode(201);
    }

    @Test(priority = 4)
    public void updateProductsRecord(){
        BestBuyPojo1 bestBuyPojo1 = new BestBuyPojo1();
        bestBuyPojo1.setName(createName+"updated");
        bestBuyPojo1.setType(createType+"updated");
        bestBuyPojo1.setPrice(createPrice);
        bestBuyPojo1.setUpc(createUpc);
        bestBuyPojo1.setShipping(createShipping);
        bestBuyPojo1.setDescription(createDescription);
        bestBuyPojo1.setManufacturer(createManufacturer+"updated");
        bestBuyPojo1.setModel(createModel+"updated");
        bestBuyPojo1.setUrl(createUrl);
        bestBuyPojo1.setImage(createImage);

        ValidatableResponse response = given().log().all()
                .header("Content-Type", "application/json")
                .pathParam("id", id)
                .when()
                .body(bestBuyPojo1)
                .put(EndPoints.UPDATE_PRODUCTS_RECORD)
                .then().log().all().statusCode(200);
    }

    @Test(priority = 5)
    public void partiallyUpdateRecord(){
        BestBuyPojo1 bestBuyPojo1 = new BestBuyPojo1();
        bestBuyPojo1.setName(createName);
        bestBuyPojo1.setType(createType);
        bestBuyPojo1.setPrice(createPrice);
        bestBuyPojo1.setUpc(createUpc+"updated");
        bestBuyPojo1.setShipping(createShipping);
        bestBuyPojo1.setDescription(createDescription);
        bestBuyPojo1.setManufacturer(createManufacturer);
        bestBuyPojo1.setModel(createModel);
        bestBuyPojo1.setUrl(createUrl);
        bestBuyPojo1.setImage(createImage);

        ValidatableResponse response = given().log().all()
                .header("Content-Type", "application/json")
                .pathParam("id", id)
                .when()
                .body(bestBuyPojo1)
                .patch(EndPoints.PARTIALLY_UPDATE_PRODUCTS_RECORD)
                .then().log().all().statusCode(200);
    }
    @Test(priority = 6)
    public void deleteRecord() {
        given().log().all()
                .header("Content-Type", "application/json")
                .pathParam("id", id)
                .when()
                .delete(EndPoints.DELETE_PRODUCTS_RECORD)
                .then().log().all()
                .statusCode(200);
    }

}
