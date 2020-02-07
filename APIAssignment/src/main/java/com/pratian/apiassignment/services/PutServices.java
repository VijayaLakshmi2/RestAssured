package com.pratian.apiassignment.services;

import java.util.Map;

import org.json.simple.JSONObject;

import com.pratian.apiassignment.baseclass.TestBase;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class PutServices extends TestBase{

	/*public PutServices()
	{
		RestAssured.baseURI = properties.getProperty("BaseURI");
	}*/
	
	//Put Activity By 1 or more Query Params and Body
	public Response putActivityByQueryParam(String baseURI,String abstractionName,Map<String,String> queryParams,JSONObject body)
	{
		RestAssured.baseURI=baseURI;
		Response response=RestAssured.given()
				.header("Content-Type", "application/json")
				.queryParams(queryParams)
				.body(body.toJSONString())
				.post(abstractionName)
				.then().extract().response();
		return response;
	}
}
