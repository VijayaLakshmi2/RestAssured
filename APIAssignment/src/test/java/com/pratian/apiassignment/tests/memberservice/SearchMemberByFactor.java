package com.pratian.apiassignment.tests.memberservice;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

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
	public void postMemberByFactor(String body) throws Exception{
		
		String firstName = null;
		String lastName = null;
		
		String firstName1 = null;
		String lastName1 = null;

		PostServices services=new PostServices();
		
		Response response=services.postActivityByBodyAsString(properties.getProperty("MemberBaseURI"),properties.getProperty("searchMemberByFactor"), body);
		
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
