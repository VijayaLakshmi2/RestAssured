package com.pratian.apiassignment.tests.memberservice;

import java.sql.ResultSet;
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
public class SearchMemberResultPost extends TestBase{		

		
		@DataProvider(name = "getdata")
		public static String[][] getMemberData() {
			Excel excel= new Excel(properties.getProperty("ExcelPath"));
			String[][] queries=excel.getdata("SearchMemberResult");
			return queries;
		    	  
		}
		
		//Get Data from DB
		public ResultSet getDataFromDB() throws Exception{
			String query="select count(*) [count] from [searchmember] where subscriberid='12307034' and isactive=1";
			DBConnection db= new DBConnection();
			ResultSet result=db.getDeatailsFromDB(query,"jdbc:sqlserver://192.61.99.201\\QA;databaseName=MH_BG_DB_TEST;username=test-memberhouse;password=password@123456");
			return result;
		}
		
		//Get Search Member Results
		@Test(dataProvider = "getdata")
		public void postSearchMemberResult(String body) throws Exception{
			
			String rowcount = null;

			PostServices services=new PostServices();
			
			Response response=services.postActivityByBodyAsString(properties.getProperty("MemberBaseURI"),properties.getProperty("SearchMemberResult"), body);
			
			//System.out.println(response.body().asString());
			
			ResultSet result=getDataFromDB();

			while (result.next()) {
				 rowcount=result.getString("count");		 
				 }
			
			Assert.assertEquals(response.getStatusCode(), 200);
			
			//Verifying Row count from DB and Response Body
			Assert.assertEquals(response.body().asString(), rowcount);
			
			
		}
}
