package com.pratian.apiassignment.services;

import java.util.Map;

import com.pratian.apiassignment.baseclass.TestBase;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class GetServices extends TestBase{
	
	/*public GetServices()
	{
		RestAssured.baseURI = properties.getProperty("BaseURI");
	}*/
	
	//Get Activity by 1 or more Query params
	public Response getActivitiesByQueryParam(String baseURI,String abstractionName,Map<String,String> queryParams)
	{
		RestAssured.baseURI=baseURI;
		Response response=RestAssured.given()
				.queryParams(queryParams)
				.get(abstractionName);
		return response;
	}

	//Get Activities
	public Response getActivities(String baseURI,String abstractionName)
	{
		RestAssured.baseURI=baseURI;
		Response response=RestAssured.given()
				.get(abstractionName);
		return response;
	}
}
