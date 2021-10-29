package com.Basic;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.hamcrest.Matcher;
import org.hamcrest.text.MatchesPattern;
import org.testng.Assert;

public class postPlace {

    public static void main(String[] args) {


        RestAssured.baseURI="https://rahulshettyacademy.com";
        String response = RestAssured.given().log().all().queryParam("key", "qaclick123").body(payload.getPlacePayload())
                .when().post("maps/api/place/add/json").then().log().all().assertThat().statusCode(200).extract().response().asString();


        System.out.println("Response is "+response);

        // Add place, Update Address of new place and Validate New address

        JsonPath js = new JsonPath(response);
        System.out.println("Created place ID is "+ js.getString("place_id"));

        RestAssured.given().log().all().queryParam("key", "qaclick123").
                body(payload.getUpdatePayload(js.getString("place_id"),"Pakistan")).when().put("/maps/api/place/update/json")
                .then().log().all().assertThat().statusCode(200).body("msg", MatchesPattern.matchesPattern("Address successfully updated"));

        response=RestAssured.given().queryParam("key", "qaclick123").queryParam("place_id",js.getString("place_id"))
                .when().get("/maps/api/place/get/json").then().assertThat().statusCode(200).extract().asString();

        js = new JsonPath(response);
        Assert.assertEquals(js.getString("address"),"Pakistan","Wrong address");


    }
}
