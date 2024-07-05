package com.APITesting;
import static io.restassured.RestAssured.*;

import static org.hamcrest.Matchers.*;



import java.util.*;



import org.json.simple.JSONObject;

import org.testng.Assert;

import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class PutPatchAndDeleteRequest {

	@Test
	public void PUTRequest() {
		JSONObject request = new JSONObject();

		request.put("name","Monica");

		request.put("job","Software Developer");

		System.out.println(request.toJSONString());

		baseURI = "https://reqres.in/api";

		given().header("Content-Type","application/json").contentType(ContentType.JSON).accept(ContentType.JSON).
		body(request.toJSONString()).post("/users/2").then().statusCode(200).log().all();
	}

}


