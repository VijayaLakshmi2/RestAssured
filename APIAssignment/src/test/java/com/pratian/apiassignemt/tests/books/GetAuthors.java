package com.pratian.apiassignemt.tests.books;

import java.util.HashMap;
import java.util.Map;

import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.pratian.apiassignment.baseclass.TestBase;
import com.pratian.apiassignment.services.GetServices;
import com.pratian.apiassignment.utilities.Excel;

import io.restassured.response.Response;

public class GetAuthors extends TestBase{

Map<String, String> map=new HashMap<String, String>();
	
	@DataProvider(name = "getId")
	public static Object[][] authorId() {
		Excel excel= new Excel(properties.getProperty("ExcelPath"));
		Object[][] queries=excel.readData("GetAuthorId");
		return queries;
	    	  
	}
	
	@DataProvider(name = "getBookId")
	public static Object[][] bookId() {
		Excel excel= new Excel(properties.getProperty("ExcelPath"));
		Object[][] queries=excel.readData("GetBookId");
		return queries;
	    	  
	}
	
	//Get Authors by ID
	@Test(dataProvider = "getId")
	public void getAuthorById(Map<String, String> queryParams) throws ParseException{
		
		GetServices services=new GetServices();
		
		Response response=services.getActivitiesByQueryParam(properties.getProperty("FakeRestAPIBaseURI"),properties.getProperty("Authors"), queryParams);
		
		Assert.assertEquals(response.getStatusCode(), 200);
		
		Assert.assertTrue(response.jsonPath().get("ID").toString().equalsIgnoreCase(queryParams.get("ID")));

		
	}
	
	//Get Authors by BookID
	@Test(dataProvider = "getBookId")
	public void getAuthorByBookId(Map<String, String> queryParams) throws ParseException{
		
		GetServices services=new GetServices();
		
		Response response=services.getActivitiesByQueryParam(properties.getProperty("FakeRestAPIBaseURI"),properties.getProperty("Authors"), queryParams);
		
		Assert.assertEquals(response.getStatusCode(), 200);
	
		
	}
	
	@Test
	public void getActivities(){
		
		GetServices services=new GetServices();
		
		Response response=services.getActivities(properties.getProperty("FakeRestAPIBaseURI"),properties.getProperty("Authors"));
		
		Assert.assertEquals(response.getStatusCode(), 200);	
		
	}
}
