package com.pratian.apiassignemt.tests.activities;

import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.pratian.apiassignment.baseclass.TestBase;
import com.pratian.apiassignment.services.PostServices;
import com.pratian.apiassignment.utilities.Excel;

import io.restassured.response.Response;

@Listeners(com.pratian.apiassignment.testlisteners.TestListener.class)
public class PostActivity extends TestBase{
	

	Map<String, String> map=new HashMap<String, String>();
	
	@DataProvider(name = "getdata")
	public static Object[][] getActivtyInfo() {
		Excel excel= new Excel(properties.getProperty("ExcelPath"));
		Object[][] queries=excel.readData("PostActivities");
		return queries;
	    	  
	}
	
	//Post Activity by ID,Title,DueDate,Completed details
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Test(dataProvider = "getdata")
	public void post(Map<String, String> body){
		
		JSONObject requestParams=new JSONObject();
		
		for(Map.Entry entry:body.entrySet()){
		   // System.out.print(entry.getKey() + " : " + entry.getValue());
		    requestParams.put(entry.getKey(), entry.getValue());
		}

		PostServices services=new PostServices();
		
		Response response=services.postActivityByBody(properties.getProperty("FakeRestAPIBaseURI"),properties.getProperty("Activities"), requestParams);
		
		Assert.assertEquals(response.getStatusCode(), 200);
		
		Assert.assertTrue(response.jsonPath().get("ID").toString().equalsIgnoreCase(body.get("ID")));
		
		Assert.assertTrue(response.jsonPath().get("Title").toString().equalsIgnoreCase(body.get("Title")));
		
		Assert.assertTrue(response.jsonPath().get("DueDate").toString().equalsIgnoreCase(body.get("DueDate")));
		
		Assert.assertTrue(response.jsonPath().get("Completed").toString().equalsIgnoreCase(body.get("Completed")));
	
	}
}
