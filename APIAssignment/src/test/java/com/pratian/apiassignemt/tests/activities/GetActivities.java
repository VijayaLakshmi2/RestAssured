package com.pratian.apiassignemt.tests.activities;

import java.util.HashMap;
import java.util.Map;

import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.pratian.apiassignment.baseclass.TestBase;
import com.pratian.apiassignment.services.GetServices;
import com.pratian.apiassignment.utilities.Excel;

import io.restassured.response.Response;

@Listeners(com.pratian.apiassignment.testlisteners.TestListener.class)
public class GetActivities extends TestBase{
	
	Map<String, String> map=new HashMap<String, String>();
	
	@DataProvider(name = "getdata")
	public static Object[][] getActivityId() {
		Excel excel= new Excel(properties.getProperty("ExcelPath"));
		Object[][] queries=excel.readData("GetActivitiesById");
		return queries;
	    	  
	}
	
	//Get Activities By ID
	@Test(dataProvider = "getdata")
	public void getActivitiesById(Map<String, String> queryParams) throws ParseException{
		
		GetServices services=new GetServices();
		
		Response response=services.getActivitiesByQueryParam(properties.getProperty("FakeRestAPIBaseURI"),properties.getProperty("Activities"), queryParams);
		
		Assert.assertEquals(response.getStatusCode(), 200);
		
		Assert.assertTrue(response.jsonPath().get("ID").toString().equalsIgnoreCase(queryParams.get("id")));

		
	}
	
	//Get all the Activities
	@Test
	public void getActivities(){
		
		GetServices services=new GetServices();
		
		Response response=services.getActivities(properties.getProperty("FakeRestAPIBaseURI"),properties.getProperty("Activities"));
		
		Assert.assertEquals(response.getStatusCode(), 200);	
		
	}
}
