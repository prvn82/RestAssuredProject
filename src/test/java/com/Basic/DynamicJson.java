package com.Basic;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DynamicJson {



    @Test(dataProvider = "AddBookData")
    public void Addbook(String isbn, String aisle){

        RestAssured.baseURI="http://216.10.245.166";
        String s = RestAssured.given().header("Content-Type", "application/json").body(payload.AddbookPayload(isbn,aisle))
                .when().post("Library/Addbook.php").then().log().all().statusCode(200).extract().response().asString();

        JsonPath jp = new JsonPath(s);
        String bookID = jp.getString("Msg");
        System.out.println("ID of new created ID is "+bookID);

        System.out.println("Adding");

        System.out.println("Addingw");
        System.out.println("Addingw");
       // bookID.concat()

    }


    @DataProvider(name = "AddBookData")
    public Object[][] getdata(){

        return new Object[][]{{"shshs","dhdhhd"},{"smd","sh"}};
    }

    
}
