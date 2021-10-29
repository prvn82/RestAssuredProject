package com.Basic;

import io.restassured.path.json.JsonPath;
import org.testng.Assert;

public class Complex {

    public static void main(String[] args) {

        JsonPath js = new JsonPath(payload.complexJson());
        int size= js.getInt("courses.size()");

        System.out.println(js.getInt("dashboard.purchaseAmount"));

      //  System.out.println((String) js.get("courses[0].title"));

        for (int i=0;i<=size-1;i++){
            System.out.println("Course title is "+(String) js.get("courses["+i+"].title"));
        }

        System.out.println("********");

        int sum=0;

        for (int i=0;i<=size-1;i++){
           sum=sum+ js.getInt("courses["+i+"].price")*js.getInt("courses["+i+"].copies");
        }

        System.out.println(sum);

        Assert.assertEquals(sum,js.getInt("dashboard.purchaseAmount"),"wrong");


    }
}
