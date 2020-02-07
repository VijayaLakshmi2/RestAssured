package com.pratian.apiassignment.services;

import java.util.Map;

import com.pratian.apiassignment.baseclass.TestBase;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class DeleteServices extends TestBase {
	
	/*public DeleteServices()
	{
		RestAssured.baseURI = properties.getProperty("BaseURI");
	}*/
	
	//Delete Activity by 1 or more Query params
	public Response deleteActivityByQueryParam(String baseURI,String abstractionName,Map<String,String> queryParams)
	{
		RestAssured.baseURI=baseURI;
		Response response=RestAssured.given()
				.queryParams(queryParams)
				.delete(abstractionName)
				.then().extract().response();
		return response;
	}

	//Delete Activity by 1 or more path params
	public Response deleteActivityByPathParam(String baseURI,String abstractionName,Map<String,String> pathParams)
	{
		RestAssured.baseURI=baseURI;
		Response response=RestAssured.given()
				.pathParams(pathParams)
				.delete(abstractionName)
				.then().extract().response();
		return response;
	}

}
