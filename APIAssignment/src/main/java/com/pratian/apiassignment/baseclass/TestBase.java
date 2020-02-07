package com.pratian.apiassignment.baseclass;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import com.pratian.apiassignment.testlisteners.TestListener;

public class TestBase {
	public static Properties properties;
    public TestBase() {
	 try 
	 {
		 properties=new Properties();
		 FileInputStream fis=new FileInputStream("./src/main/resources/config.properties");
		 properties.load(fis);
	 }
	 catch(FileNotFoundException e)
	 {
		 e.printStackTrace();
	 }
	 catch(IOException e)
	 {
		 e.printStackTrace();
	 }
    }
    
    public  void log(String message)
    {
    	TestListener.reportLog(message);
    }
}
