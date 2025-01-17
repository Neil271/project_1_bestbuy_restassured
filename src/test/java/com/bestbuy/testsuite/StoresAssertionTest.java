package com.bestbuy.testsuite;

import com.bestbuy.constant.EndPoints;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class StoresAssertionTest {
    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "http://localhost:3030";
        response = given().log().all()
                .when()
                .get(EndPoints.GET_ALL_LIST_STORES)
                .then().log().all().statusCode(200);
    }

    //1. Verify the if the total is equal to 1567
    @Test
    public void test001(){
        response.body("total",equalTo(1567));
    }
    //2. Verify the if the stores of limit is equal to 10
    @Test
    public void test002(){
        response.body("limit",equalTo(10));
    }
    //3. Check the single ‘Name’ in the Array list (Inver Grove Heights)
    @Test
    public void test003(){
        response.body("data[0].name",equalTo("Minnetonka"));
    }
    //4. Check the multiple ‘Names’ in the ArrayList (Roseville, Burnsville, Maplewood)
    @Test
    public void test004(){
        response.body("data.name",hasItems("Minnetonka","Inver Grove Heights","Roseville"));
    }
    //5. Verify the storied=7 inside storeservices of the third store of second services
    @Test
    public void test005(){
        response.body("data[2].services[1].storeservices.storeId",equalTo(7));
    }
    //6. Check hash map values ‘createdAt’ inside storeservices map where store name = Roseville
    @Test
    public void test006(){
        response.body("data[2].services.storeservices.createdAt",hasItem("2016-11-17T17:57:09.417Z"));
    }
    //7. Verify the state = MN of forth store
    @Test
    public void test007(){
        response.body("data",hasItem(hasEntry("state","MN")));
    }
    //8. Verify the store name = Rochester of 9th store
    @Test
    public void test008(){
        response.body("data[8].name",equalTo("Rochester"));
    }

    //9. Verify the storeId = 11 for the 6th store
    @Test
    public void test009(){
        response.body("data[5].id",equalTo(11));
    }

    //10. Verify the serviceId = 4 for the 7th store of forth service
    @Test
    public void test010(){
        response.body("data[6].services[3].id",equalTo(4));
    }
}
