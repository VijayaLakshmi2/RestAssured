package com.pratian.apiassignment.testlisteners;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentManager {
	
	private static ExtentReports extent;
	private static DateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
	private static Date date = new Date();
	private static String fileDate = dateFormat.format(date);
	private static String reportFileName = "Test-Automaton-Report"+  fileDate+ ".html";
	private static String fileSeperator = System.getProperty("file.separator");
	private static String reportFilepath = System.getProperty("user.dir") + fileSeperator + "TestReport";
	private static String reportFileLocation = reportFilepath + fileSeperator + reportFileName;

	/**
	 * Gets the single instance of ExtentManager.
	 *
	 * @return single instance of ExtentManager
	 */
	public static ExtentReports getInstance() {
		if (extent == null)
			createInstance();
		return extent;
	}

	/**
	 * Creates the instance of extent report
	 *
	 * @return the extent reports
	 */
	// Create an extent report instance
	public static ExtentReports createInstance() {
		String fileName = getReportPath(reportFilepath);
		ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(fileName);
		//htmlReporter.config().setTestViewChartLocation(ChartLocation.BOTTOM);
		htmlReporter.config().setChartVisibilityOnOpen(true);
		htmlReporter.config().setTheme(Theme.STANDARD);
		htmlReporter.config().setDocumentTitle(reportFileName);
		htmlReporter.config().setEncoding("utf-8");
		htmlReporter.config().setReportName(reportFileName);
		htmlReporter.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm a '('zzz')'");

		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		// Set environment details
		extent.setSystemInfo("OS", "Windows");
		extent.setSystemInfo("AUT", "QA");

		return extent;
	}

	/**
	 * Gets the report path.
	 *
	 * @param path the path
	 * @return the report path
	 */
	// Create the report path
	private static String getReportPath(String path) {
		File testDirectory = new File(path);
		if (!testDirectory.exists()) {
			if (testDirectory.mkdir()) {
				return reportFileLocation;
			} else {
				System.out.println("Failed to create directory: " + path);
				return System.getProperty("user.dir");
			}
		} else {
			System.out.println("Directory already exists: " + path);
		}
		return reportFileLocation;
	}

	/**
	 * Gets the report location.
	 *
	 * @return the report location
	 */
	public static String getReportLocation() {
		return reportFileLocation;
	}


}
