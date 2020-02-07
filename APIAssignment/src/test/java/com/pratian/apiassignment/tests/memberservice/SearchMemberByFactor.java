package com.pratian.apiassignment.tests.memberservice;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.pratian.apiassignment.baseclass.TestBase;
import com.pratian.apiassignment.services.PostServices;
import com.pratian.apiassignment.utilities.DBConnection;
import com.pratian.apiassignment.utilities.Excel;

import io.restassured.response.Response;

@Listeners(com.pratian.apiassignment.testlisteners.TestListener.class)
public class SearchMemberByFactor extends TestBase{
	
	@DataProvider(name = "getdata")
	public static String[][] getMemberData() {
		Excel excel= new Excel(properties.getProperty("ExcelPath"));
		String[][] queries=excel.getdata("SearchMemberResult");
		return queries;
	    	  
	}
	
	//Get data from DB
	public ResultSet getDataFromDB() throws Exception{
		String query="select * from searchmember where subscriberid='12307034' and isactive=1";
		DBConnection db= new DBConnection();
		ResultSet result=db.getDeatailsFromDB(query,"jdbc:sqlserver://192.61.99.201\\QA;databaseName=MH_BG_DB_TEST;username=test-memberhouse;password=password@123456");
		return result;
	}
	
	
	//Get Search Member Results By Factor
	@SuppressWarnings({ "unchecked" })
	@Test(dataProvider = "getdata")
	public void post(String param1,String paramvalue1,String param2,String paramvalue2,
			String param3,String paramvalue3,String param4,String paramvalue4,String param5,
			String paramvalue5,String param6,String paramvalue6,String param7,String paramvalue7,
			String param8,String paramvalue8,String param9,String paramvalue9,String param10,
			String paramvalue10,String param11,String paramvalue11,String param12,String paramvalue12) throws Exception{
		
		String firstName = null;
		String lastName = null;
		
		String firstName1 = null;
		String lastName1 = null;
		
		JSONObject requestParams=new JSONObject();
		
		JSONObject searchParameter=new JSONObject();
		
		searchParameter.put(param1, paramvalue1);
		requestParams.put("searchParameter", searchParameter);
		
		requestParams.put(param2, paramvalue2);
		
		JSONArray defaultSortOptions = new JSONArray();
		
		JSONObject defaultSortOptions1=new JSONObject();
		defaultSortOptions1.put(param3, paramvalue3);
		defaultSortOptions1.put(param4, paramvalue4);
		defaultSortOptions1.put(param5, paramvalue5);
		
		defaultSortOptions.add(defaultSortOptions1);
		requestParams.put("defaultSortOptions", defaultSortOptions);
		
		requestParams.put(param6, paramvalue6);
		
		JSONObject paginationOption=new JSONObject();
		
		paginationOption.put(param7, paramvalue7);
		paginationOption.put(param8, paramvalue8);
		
		requestParams.put("paginationOption", paginationOption);
		
		requestParams.put(param9, paramvalue9);
		requestParams.put(param10, paramvalue10);
		requestParams.put(param11, paramvalue11);
		requestParams.put(param12, paramvalue12);

		PostServices services=new PostServices();
		
		Response response=services.postActivityByBody(properties.getProperty("MemberBaseURI"),properties.getProperty("searchMemberByFactor"), requestParams);
		
		// reading array of json response data into arraylist
		ArrayList<HashMap<String,String>> res=(ArrayList<HashMap<String,String>>) response.jsonPath().get();
		HashMap<String,String> data=res.get(0);
		lastName1=data.get("lastName");
		firstName1=data.get("firstName");
		
		ResultSet result=getDataFromDB();

		while (result.next()) {
			 firstName=result.getString("FirstName").toString();	
			 lastName=result.getString("LastName").toString();
			 }
		
		Assert.assertEquals(response.getStatusCode(), 200);
		
		// Verifying Last Name, First Name from DB and Response Body
		Assert.assertEquals(lastName1,lastName);
		Assert.assertEquals(firstName1,firstName);
		
		
	}

}
