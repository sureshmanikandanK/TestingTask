package com.APITesting;



import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

import static io.restassured.RestAssured.given;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;





@Test



public class JSONSchemaValidator {

	

	public void testget() {

		baseURI ="http://localhost:4040/";

		

		given().get("/user").

		

		then().

		assertThat().body(matchesJsonSchemaInClasspath("Schema.json")).statusCode(200);

		

	}



}