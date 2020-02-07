package com.pratian.apiassignemt.tests.activities;

import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.pratian.apiassignment.baseclass.TestBase;
import com.pratian.apiassignment.services.PutServices;
import com.pratian.apiassignment.utilities.Excel;

import io.restassured.response.Response;

@Listeners(com.pratian.apiassignment.testlisteners.TestListener.class)
public class PutActivities extends TestBase{

Map<String, String> map=new HashMap<String, String>();
	
	@DataProvider(name = "getdata")
	public static Object[][] getActivityInfo() {
		Excel excel= new Excel(properties.getProperty("ExcelPath"));
		Object[][] queries=excel.readData("PutActivitiesById");
		return queries;
	    	  
	}

	//Put Activity By ID, Title, DueDate, Completed details
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Test(dataProvider = "getdata")
	public void put(Map<String, String> body){
		
		Map<String, String> querymap=new HashMap<String, String>();
		
		for(Map.Entry entry:body.entrySet()){
			   // System.out.print(entry.getKey() + " : " + entry.getValue());
			if(entry.getKey().toString().equalsIgnoreCase("id"))
				querymap.put(entry.getKey().toString(), entry.getValue().toString());
			}
		
		
		JSONObject requestParams=new JSONObject();
		
		for(Map.Entry entry:body.entrySet()){
		   // System.out.print(entry.getKey() + " : " + entry.getValue());
		    requestParams.put(entry.getKey(), entry.getValue());
		}
		
		PutServices services=new PutServices();
		
		Response response=services.putActivityByQueryParam(properties.getProperty("FakeRestAPIBaseURI"),properties.getProperty("Activities"),querymap, requestParams);
		
		Assert.assertEquals(response.getStatusCode(), 200);
		
		Assert.assertTrue(response.jsonPath().get("ID").toString().equalsIgnoreCase(body.get("ID")));
		
		Assert.assertTrue(response.jsonPath().get("Title").toString().equalsIgnoreCase(body.get("Title")));
		
		Assert.assertTrue(response.jsonPath().get("DueDate").toString().equalsIgnoreCase(body.get("DueDate")));
		
		Assert.assertTrue(response.jsonPath().get("Completed").toString().equalsIgnoreCase(body.get("Completed")));
	
		
	}
}
