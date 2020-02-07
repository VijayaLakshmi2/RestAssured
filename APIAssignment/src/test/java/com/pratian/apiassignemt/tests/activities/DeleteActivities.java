package com.pratian.apiassignemt.tests.activities;

import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.pratian.apiassignment.baseclass.TestBase;
import com.pratian.apiassignment.services.DeleteServices;
import com.pratian.apiassignment.utilities.Excel;

import io.restassured.response.Response;

@Listeners(com.pratian.apiassignment.testlisteners.TestListener.class)
public class DeleteActivities extends TestBase{
	
Map<String, String> map=new HashMap<String, String>();
	
	@DataProvider(name = "getdata")
	public static Object[][] getActivityId() {
		Excel excel= new Excel(properties.getProperty("ExcelPath"));
		Object[][] queries=excel.readData("DeleteActivitiesById");
		return queries;
	    	  
	}
	
	//Delete Activities by ID
	@Test(dataProvider = "getdata")
	public void delete(Map<String, String> queryParams){
		
		DeleteServices services=new DeleteServices();
		
		Response response=services.deleteActivityByQueryParam(properties.getProperty("FakeRestAPIBaseURI"),properties.getProperty("Activities"), queryParams);
		
		Assert.assertEquals(response.getStatusCode(), 200);
		
	}

}
