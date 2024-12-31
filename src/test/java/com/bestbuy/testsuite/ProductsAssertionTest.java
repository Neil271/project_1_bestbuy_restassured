package com.bestbuy.testsuite;

import com.bestbuy.constant.EndPoints;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class ProductsAssertionTest{
    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "http://localhost:3030";
        response = given().log().all()
                .when()
                .get(EndPoints.GET_ALL_LIST_PRODUCTS)
                .then().log().all().statusCode(200);
    }
    //11. Verify the if the total is equal to 51967
    @Test
    public void test001(){
      response.body("total",equalTo(51967));
    }
    //12. Verify the if the stores of limit is equal to 10
    @Test
    public void test002(){
        response.body("limit",equalTo(10));
    }
    //13. Check the single ‘Name’ in the Array list (Duracell - AA Batteries (8-Pack))
    @Test
    public void test003(){
        response.body("data.name",hasItem("Duracell - AA Batteries (8-Pack)"));
    }
    //14. Check the multiple ‘Names’ in the ArrayList Duracell - AA Batteries (8-Pack),Duracell - C Batteries (4-Pack)
    @Test
    public void test004(){
        response.body("data.name",hasItems("Duracell - AA Batteries (8-Pack)","Duracell - C Batteries (4-Pack)"));
    }
    //15. Verify the productId=48530 inside categories of the third name is “Household Batteries”
    @Test
    public void test005(){
        response.body("data[0].categories.findAll{it.name=='Household Batteries'}.id",hasItem("pcmcat303600050001"));
        //response.body("data.findAll{it.categories.name=='Household Batteries'}",hasItem(hasEntry("id",48530)));
    }
    //16. Verify the categories second name = “Housewares” of productID = 127687
    @Test
    public void test006(){
        response.body("data.findAll{it.id==127687}.categories[0].get(1).name",equalTo("Housewares"));
    }
    //17. Verify the price = 4.99 of third product
    @Test
    public void test007(){
        response.body("data[2].price",equalTo(4.99F));
    }
    //18. Verify the Product name = Duracell - D Batteries (4-Pack) of 4th product
    @Test
    public void test008(){
        response.body("data[4].name",equalTo("Duracell - D Batteries (4-Pack)"));
    }
    //19. Verify the ProductId = 346575 for the 9th product
    @Test
    public void test009(){
        response.body("data[8].id",equalTo(346575));
    }
    //20. Verify the prodctId = 346575 have 5 categories
    @Test
    public void test010(){
        response.body("data[8].categories",hasSize(5));
    }
}
