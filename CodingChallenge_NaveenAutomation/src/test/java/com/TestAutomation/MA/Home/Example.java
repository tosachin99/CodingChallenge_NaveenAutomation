package com.TestAutomation.MA.Home;

import org.testng.annotations.Test;

import com.TestAutomation.MA.HelperClasses.JavaScriptHelper;
import com.TestAutomation.MA.PageObjects.HomePage;
import com.TestAutomation.MA.PageObjects.SearchResultPage;
import com.TestAutomation.MA.testBase.TestBase;

public class Example extends TestBase{
	@Test
	public void TC_002() throws InterruptedException{
		test = extent.createTest(getClass().getSimpleName(), "Search Example of MMT Website").assignAuthor("Sachin").assignCategory("Smoke");
	/*	
		HomePage homePage = new HomePage(driver);
		SearchResultPage searchResPage = new SearchResultPage(driver);
		JavaScriptHelper jsHelper= new JavaScriptHelper(driver);
		
		homePage.verifyTitle();		
		homePage.lnk_Flights.click();
		homePage.radio_RoundTrip.click();
		homePage.enterFromCity("Delhi");
		homePage.enterToCity("Bangalore");
		homePage.tile_departure.click();
		homePage.selectDepartureDtAndReturnDt_with7DaysGap();
		homePage.btn_Search.click();
		searchResPage.waitForsearchPageLoad();
		jsHelper.constantScrollingTillBottom();
		jsHelper.scrollUpVertically();
		searchResPage.displayNumberOfDepartureFlights();
		searchResPage.displayNumberOfReturnFlights();
		
		searchResPage.checkOrUncheckNonStop(true);
		searchResPage.verifyFilterAppliedCorrectly(searchResPage.filterLabel_NonStop);
		jsHelper.constantScrollingTillBottom();
		searchResPage.displayNumberOfDepartureFlights();
		searchResPage.displayNumberOfReturnFlights();
		jsHelper.scrollUpVertically();
		searchResPage.checkOrUncheckNonStop(true);
		*/
		
	//Now Select the Non Stop radio Option and Print the Respective Flights
	System.out.println("******************************** For Example NonStop ****************************************");
	}
	
}
