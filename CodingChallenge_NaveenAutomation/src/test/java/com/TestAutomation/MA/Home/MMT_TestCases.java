package com.TestAutomation.MA.Home;

import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.Test;

import com.TestAutomation.MA.HelperClasses.JavaScriptHelper;
import com.TestAutomation.MA.PageObjects.HomePage;
import com.TestAutomation.MA.PageObjects.SearchResultPage;
import com.TestAutomation.MA.testBase.TestBase;

public class MMT_TestCases extends TestBase{

	@Test
	public void TC_001() throws Exception{
		test = extent.createTest(getClass().getSimpleName(), "Selenium Coding challenge 2 -- By Naveen").assignAuthor("Sachin").assignCategory("Smoke");
		HomePage homePage = new HomePage(driver);
		SearchResultPage searchResPage = new SearchResultPage(driver);
		JavaScriptHelper jsHelper= new JavaScriptHelper(driver);
		
		homePage.verifyTitle();		
		homePage.selectFlightsSection();
		homePage.selectRoundTrip();
		homePage.enterFromCity("Delhi");
		homePage.enterToCity("Bangalore");
		homePage.selectDeparture_tile();
		homePage.selectDepartureDtAndReturnDt_with7DaysGap();
		homePage.clickSearchFlights();
	
	//	String url = "https://www.makemytrip.com/flight/search?tripType=R&itinerary=DEL-BLR-25/04/2019_BLR-DEL-30/04/2019&paxType=A-1_C-0_I-0&cabinClass=E&sTime=1556039699309&forwardFlowRequired=true&pot=undefined&cmp=undefined&intl=undefined";
	//	driver.navigate().to(url);
		
		searchResPage.waitForsearchPageLoad();
		jsHelper.constantScrollingTillBottom();
		jsHelper.scrollUpVertically();
		searchResPage.displayNumberOfDepartureFlights();
		searchResPage.displayNumberOfReturnFlights();

	System.out.println("******************************** For Filter NonStop ****************************************");
		
		searchResPage.checkOrUncheckNonStop(true);
		searchResPage.verifyFilterAppliedCorrectly(searchResPage.filterLabel_NonStop);
		jsHelper.constantScrollingTillBottom();
		searchResPage.displayNumberOfDepartureFlights();
		searchResPage.displayNumberOfReturnFlights();
		
	System.out.println("******************************** For Filter 1Stop ****************************************");	
		jsHelper.scrollUpVertically();
		searchResPage.checkOrUncheckNonStop(true);
		searchResPage.checkOrUncheck1Stop(true);
		searchResPage.verifyFilterAppliedCorrectly(searchResPage.filterLabel_1Stop);
		jsHelper.constantScrollingTillBottom();
		searchResPage.displayNumberOfDepartureFlights();
		searchResPage.displayNumberOfReturnFlights();
		
	System.out.println("**********Now Finally selecting the Flights from both tables and verifying the Fares********");	
	//Note The below Method Passes or Fails based on parameters passed as per the the Availability of Flights for the day at that time
	jsHelper.scrollUpVertically();	
	searchResPage.verifyAmountMatchAsPerSelected(1,2);
		//searchResPage.verifyAmountMatchAsPerSelected(2,4);
		//searchResPage.verifyAmountMatchAsPerSelected(6,5);
	}
	
}
