package com.pratian.apiassignment.testlisteners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.Status;

public class TestListener implements ITestListener {
	
	public void onTestFailure(ITestResult result) {
		ExtentTestManager.getTest().log(Status.FAIL, result.getThrowable());
	}
	
	public void onTestSuccess(ITestResult result) {
		ExtentTestManager.getTest().log(Status.PASS, "Test passed");
	}
	
	public void onTestSkipped(ITestResult result) {
		ExtentTestManager.getTest().log(Status.SKIP, "Test Skipped");
	}
	
	public void onFinish(ITestContext context) {
		ExtentTestManager.endTest();
		ExtentManager.getInstance().flush();
	}

	public void onTestStart(ITestResult result) {
		ExtentTestManager.startTest(result.getMethod().getMethodName());
	}
	
	public static void reportLog(String message) {
		ExtentTestManager.getTest().log(Status.INFO, message);
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

}
