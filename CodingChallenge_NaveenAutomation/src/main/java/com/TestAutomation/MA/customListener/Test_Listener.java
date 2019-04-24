package com.TestAutomation.MA.customListener;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

public class Test_Listener  implements ITestListener{

	@Override
	public void onFinish(ITestContext arg0) {
		System.out.println("Execution Completed");
		Reporter.log("Execution Completed",true);
	}

	@Override
	public void onStart(ITestContext arg0) {
		// TODO Auto-generated method stub
	
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestFailure(ITestResult arg0) {
		// TODO Auto-generated method stub
		System.out.println("There is a Failure boss");
		Reporter.log("There is a Failure boss",true);
	}

	@Override
	public void onTestSkipped(ITestResult arg0) {
		System.out.println("There is a Failure boss");
		Reporter.log("This Test is Skipped",true);
		
	}

	@Override
	public void onTestStart(ITestResult arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestSuccess(ITestResult arg0) {
		Reporter.log("Oyeee Test Success",true);
		
	}

}
