package com.pratian.apiassignment.services;

import org.json.simple.JSONObject;

import com.pratian.apiassignment.baseclass.TestBase;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class PostServices extends TestBase{

	/*public PostServices()
	{
		RestAssured.baseURI = properties.getProperty("BaseURI");
	}*/
	
	//Post Activity by Body
	public Response postActivityByBody(String baseURI,String abstractionName,JSONObject body)
	{
		RestAssured.baseURI=baseURI;
		Response response=RestAssured.given()
				.header("Content-Type", "application/json")
				.body(body.toJSONString())
				.post(abstractionName)
				.then().extract().response();
		return response;
	}
}
