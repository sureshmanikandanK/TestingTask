package com.APITesting;
import static io.restassured.RestAssured.*;

import static org.hamcrest.Matchers.*;



import java.util.*;



import org.json.simple.JSONObject;

import org.testng.Assert;

import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class TestGetandPOSTRequest {

	
	@Test

	public void GETRequest1()

	{

		//1st Validation by ID

		baseURI = "https://reqres.in/api";

		given().get("/unknown").then().statusCode(200).body("data[1].id", equalTo(2)).log().all();

		

		//2nd validation by name

		given().get("/unknown").then().statusCode(200).body("data[1].name", equalTo("fuchsia rose")).

		body("data[1].id",equalTo(2)).log().all();

	}

	

	public void GETRequest2() 

	{

		//3rd validation by group

		

		baseURI = "https://reqres.in/api";

		

		given().get("/users?page=2").then().statusCode(200).body("data.firstname", hasItems("Lindsay","Tobias"));

	}

	

	public void POSTRequest() 

	{

		Map<String,Object> map = new HashMap<String,Object>();

		JSONObject request = new JSONObject(map);

		request.put("name","Monica");

		request.put("job","Software Developer");
		
		System.out.println(request.toJSONString());
		
		baseURI = "https://reqres.in/";
		
		given().header("Content-Type","application/json").contentType(ContentType.JSON).accept(ContentType.JSON).
		body(request.toJSONString()).post("/users").then().statusCode(201).log().all();

		

	}
}
