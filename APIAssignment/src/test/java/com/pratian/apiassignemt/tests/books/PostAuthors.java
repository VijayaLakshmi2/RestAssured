package com.pratian.apiassignemt.tests.books;

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
public class PostAuthors extends TestBase{

		Map<String, String> map=new HashMap<String, String>();
		
		@DataProvider(name = "postData")
		public static Object[][] authors() {
			Excel excel= new Excel(properties.getProperty("ExcelPath"));
			Object[][] queries=excel.readData("PostAuthors");
			return queries;
		    	  
		}

		//Post Authors by ID, IDBook, FirstName, LastName
		@SuppressWarnings({ "unchecked", "rawtypes" })
		@Test(dataProvider = "postData")
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
			
		}
}
