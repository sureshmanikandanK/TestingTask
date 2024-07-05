package com.APITesting;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;

import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class OrganicHomePage {

	//@Test
	public void HomePageApiTest()
	{
	   Response response = get("http://localhost:3000");
	   System.out.println(response.getStatusCode());
		System.out.println(response.getTime());
		System.out.println(response.getBody().asString());
		System.out.println(response.getStatusLine());
		System.out.println(response.getHeader("content-type"));
		
		int statuscode = response.getStatusCode();
		Assert.assertEquals(statuscode,200);
	}
	
	//@Test
    public void GETApiTest2() {
		
		baseURI = "http://localhost:8888";
		given().get("/foodMenu").then().statusCode(200).body("[0].id", equalTo("3")).log().all();
		//given().get("/user").then().statusCode(200).body("id[2].userId",equalTo(3)).log().all();
		
		
		
	}
    //@Test
	public void POSTRequest() 

	{

		Map<String,Object> map = new HashMap<String,Object>();

		JSONObject request = new JSONObject(map);

		request.put("userId","Monica");

		request.put("userPass","Software Developer");
		
		System.out.println(request.toJSONString());
		
		baseURI = "http://localhost:8888";
		
		given().header("Content-Type","application/json").contentType(ContentType.JSON).accept(ContentType.JSON).
		body(request.toJSONString()).post("/user").then().statusCode(201).log().all();

		

	}
    
  // @Test
	public void PUTRequest() {
		JSONObject request = new JSONObject();

		request.put("userId","Monica");

		request.put("userPass","Software Engineer");

		System.out.println(request.toJSONString());

		baseURI = "http://localhost:8888";

		given().header("Content-Type","application/json").contentType(ContentType.JSON).accept(ContentType.JSON).
		body(request.toJSONString()).put("/user/b73a").then().statusCode(200).log().all();
	}
   @Test
    public void PATCHRequest() {
    	JSONObject request = new JSONObject();
    	request.put("userId","Sathish@gmail.com");

		request.put("userPass","Sathish@12345");
		System.out.println(request.toJSONString());

		baseURI = "http://localhost:8888";
		given().header("Content-Type","application/json").contentType(ContentType.JSON).accept(ContentType.JSON).
		body(request.toJSONString()).patch("/user/503f").then().statusCode(200).log().all();
		
    }
   
   public void DeleteRequest() {
	   {
		   baseURI = "http://localhost:8888";
		   when().delete("/user/503").then().statusCode(200).log().all();
	   }
	 
   }
}


