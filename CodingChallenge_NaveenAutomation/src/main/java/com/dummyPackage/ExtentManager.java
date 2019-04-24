package com.dummyPackage;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.TestAutomation.MA.testBase.TestBase;
import com.aventstack.extentreports.ExtentReporter;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;


/*

import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.testng.ITestResult;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.test.automation.MA.testBase.TestBase;

public class ExtentManager extends TestBase {
	private static ExtentReports extent;
	Calendar calendar = Calendar.getInstance();
	SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_sss");
	final String filePath = System.getProperty("user.dir") + "/src/main/java/com/test/automation/MA/ExecutionReport/"
			+ formater.format(calendar.getTime()) + ".html";

	public synchronized static ExtentReports getReporter(String filePath) {
		if (extent == null) {
			extent = new ExtentReports(filePath, true);

			extent.addSystemInfo("Host Name", "SACH").addSystemInfo("EnvironmentSAC", "QA");
		}

		return extent;
	}
}


*/
/*
public class ExtentManager extends TestBase{
	
	private static ExtentReports extent;
	private static ExtentTest test;
	private static ExtentHtmlReporter htmlReporter;
	
	Calendar calendar = Calendar.getInstance();
	SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_sss");
	final String filePath = System.getProperty("user.dir") + "/src/main/java/com/TestAutomation/MA/ExecutionReport/"+ formater.format(calendar.getTime()) + ".html";
	
	public static ExtentReports GetExtent() {
		
		if (extent == null) 
			return extent;
			
			extent = new ExtentReports();
			extent.attachReporter(getHtmlReporter());
			return extent;	
		
	}

	private static ExtentReporter getHtmlReporter() {
		
		htmlReporter.config().setChartVisibilityOnOpen(true);
		htmlReporter.config().setDocumentTitle("Regression Reports");
		htmlReporter.config().setReportName("MyAccess Automation Reports By Sachin Hiremath");
		htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
		htmlReporter.config().setTheme(Theme.STANDARD);
		
		extent.setSystemInfo("OS", System.getProperty("os.name"));
        extent.setSystemInfo("USER", System.getProperty("user.name"));
		extent.setSystemInfo("BROWSER", " "+new Config(OR).getExecutionBrowser());
		extent.setSystemInfo("ENVIRONMENT"," "+new Config(OR).getENV());
		
		
		return htmlReporter;
	}
	
	public static ExtentTest createTest(String name, String description) {
		test = extent.createTest(name,description);
		return test;
		
	}
	
	
}*/