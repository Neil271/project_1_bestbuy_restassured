package com.bestbuy.testbase;

import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;

public class TestBase {

    @BeforeClass
    public void inIt() {
        RestAssured.baseURI = "http://localhost:3030";
    }
}
