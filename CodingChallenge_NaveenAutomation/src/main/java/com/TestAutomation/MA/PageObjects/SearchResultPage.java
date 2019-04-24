package com.TestAutomation.MA.PageObjects;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.TestAutomation.MA.HelperClasses.JavaScriptHelper;
import com.TestAutomation.MA.HelperClasses.VerificationHelper;
import com.TestAutomation.MA.HelperClasses.WaitHelper;
import com.TestAutomation.MA.config.Constants;
import com.TestAutomation.MA.testBase.TestBase;

public class SearchResultPage {
	public static final Logger log = Logger.getLogger(SearchResultPage.class.getName());
	private WaitHelper wh;

	private WebDriver driver;

	@FindBy(xpath = "//div[@id='search-widget']")
	private WebElement search_widget;

	@FindBy(xpath = "//div[@id='ow_domrt-jrny']/div/div[@class='fli-list splitVw-listing']")
	private WebElement departureFlightResults;

	@FindBy(xpath = "//div[@id='rt-domrt-jrny']/div/div[@class='fli-list splitVw-listing']")
	private WebElement ReturningFlightResults;

	@FindBy(xpath = "//label[starts-with(@for,'filter_stop')]/span[text()='Non Stop']")
	public WebElement filterLabel_NonStop;

	@FindBy(xpath = "//label[starts-with(@for,'filter_stop')]/span[text()='1 Stop']")
	public WebElement filterLabel_1Stop;

	@FindBy(xpath = "//label[@for='filter_stop0']//span[@class='box']/span[@class='check']") 
	private WebElement checkBox_NonStop;

	@FindBy(xpath = "//label[@for='filter_stop1']//span[@class='box']/span[@class='check']")
	private WebElement checkBox_1Stop;

	@FindBy(xpath = "//div[@class='splitVw-footer-right ']//span[@class='INR']/../..")
	private WebElement footerReturnFlightCharge;

	@FindBy(xpath = "//div[@class='splitVw-footer-left ']//span[@class='INR']/../..")
	private WebElement footerDepartureFlightCharge;

	@FindBy(xpath = "//span[@class='splitVw-total-fare']//span[@class='INR']/../..")
	private WebElement footerTotalFlightCharge;

	/*@FindBy(xpath="//span[@class='deal-tag']/following::span[1]")
	WebElement dealTag_Price;
	
	@FindBy(xpath= "//p[@class='disc-applied']")//"//span[@class='deal-tag']")
	WebElement dealTag;*/
	/**
	 * @author sachin.hiremath Constructor for SearchResultPage
	 * @param driver
	 *            This Method Also Instantiate the WaitHelper class for once to
	 *            use across POM
	 */
	public SearchResultPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		wh = new WaitHelper(driver);
	}

	/*public boolean checkIfDealExists(){
		if(dealTag.isDisplayed()){
			return true;
		}
		return false;
	}*/
	
	/**
	 * WaitFor the Search Page to be Loaded Fully
	 */
	public void waitForsearchPageLoad() {
		wh.waitForElementVisible(search_widget, Constants.EXPLICIT_WAIT, Constants.POLLING_WAIT);
		VerificationHelper.verifyElementPresent(search_widget);
	}

	/**
	 * @author sachin.hiremath THIS Method displays only the Departure Flights
	 *         available
	 */
	public void displayNumberOfDepartureFlights() {
		List<WebElement> DepElems = driver
				.findElements(By.xpath("//div[@id='ow_domrt-jrny']/div/div[@class='fli-list splitVw-listing']"));
		System.out.println("Total number of Available Departure Flights are : " + DepElems.size());
		log.info("Total number of Available Departure Flights are : " + DepElems.size());
		for (int i = 1; i <= DepElems.size(); i++) {
			String flightName = driver
					.findElement(By.xpath("//div[@id='ow_domrt-jrny']/div/div[@class='fli-list splitVw-listing'][" + i
							+ "]//span[@class='airlineInfo-sctn']"))
					.getText();
			log.info(i + " Flight of Departure is " + flightName);
			System.out.println(i + " Flight of Departure is " + flightName);
		}
	}

	/**
	 * @author sachin.hiremath 
	 * THIS Method displays only the Return Flights available
	 *         
	 */
	public void displayNumberOfReturnFlights() {
		List<WebElement> returnElems = driver
				.findElements(By.xpath("//div[@id='rt-domrt-jrny']/div/div[@class='fli-list splitVw-listing']"));
		System.out.println("Total number of Available Return Flights are : " + returnElems.size());
		log.info("Total number of Available Return Flights are : " + returnElems.size());
		
		for (int i = 1; i <= returnElems.size(); i++) {
			String flightName = driver
					.findElement(By.xpath("//div[@id='rt-domrt-jrny']/div/div[@class='fli-list splitVw-listing'][" + i
							+ "]//span[@class='airlineInfo-sctn']"))
					.getText();
			System.out.println(i + " Flight of Return is " + flightName);
			log.info(i + " Flight of Return is " + flightName);
		}
	}

	/**
	 * This Method Checks or Unchecks the Filter NonStop base on Provided Param value
	 * @author sachin.hiremath
	 * @param checkingRequired
	 *            {@value} boolean
	 */
	public void checkOrUncheckNonStop(boolean checkingRequired) {
		if (checkingRequired == true) {
			filterLabel_NonStop.click();
		} else if (checkBox_NonStop.isSelected() && checkingRequired == false) {
			checkBox_NonStop.click();
		} else {
			System.out.println("NonStop CheckBox is Already Unchecked");
			log.info("NonStop CheckBox is Already Unchecked");
		}
	}

	/**
	 * This Method Checks or Unchecks the Filter 1Stop base on Provided Param value
	 * @author sachin.hiremath
	 * @param checkingRequired {@value} boolean
	 *            
	 */
	public void checkOrUncheck1Stop(boolean checkingRequired) {
		if (checkingRequired == true) {
			checkBox_1Stop.click();
		} else if (checkBox_1Stop.isSelected() && checkingRequired == false) {
			checkBox_1Stop.click();
		} else {
			System.out.println("1Stop CheckBox is Already Unchecked");
			log.info("1Stop CheckBox is Already Unchecked");
		}
	}

	/**
	 * This Method If the Filter is Applied Properly and applied Filter name appears in Filter bar
	 * @author sachin.hiremath
	 * @param selectedFilter
	 */
	public void verifyFilterAppliedCorrectly(WebElement selectedFilter) {
		wh.waitForElement(driver, search_widget, Constants.EXPLICIT_WAIT);
		WebElement el = driver.findElement(By.xpath("//div[@class='fli-filter-applied inlineB']/span"));
		el.getText().contains(selectedFilter.getText());
		log.info("DDDDDDDDDDDDDDEEEBBBUUUUUGGGGG :: " + el.getText());
		Assert.assertEquals(el.getText().contains(selectedFilter.getText()), true);
	}

	/**
	 * This Method Selects the Flights based upon the Index Passed in Departure & return Flights list
	 * @author sachin.hiremath
	 * @param indexOfDepartureFlight
	 * @param indexOfReturnFlight
	 */
	public void selectDepartureAndReturnFlightsByRowIndex(int indexOfDepartureFlight, int indexOfReturnFlight) {
		WebElement elem1 = driver.findElement(
				By.cssSelector("#ow_domrt-jrny .fli-list:nth-child(" + indexOfDepartureFlight + ") .splitVw-outer"));
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", elem1);

		JavaScriptHelper jsHelper = new JavaScriptHelper(driver);
		//jsHelper.scrollIntoViewAndClick(elem1);
		WebElement elem2 = driver.findElement(
				By.cssSelector("#rt-domrt-jrny .fli-list:nth-child(" + indexOfReturnFlight + ") .splitVw-outer"));
		executor.executeScript("arguments[0].click();", elem2);
		//jsHelper.scrollIntoViewAndClick(elem2);
	}

	/**
	 * @author sachin.hiremath
	 * @param indexOfDepartureFlight Integer
	 * @param indexOfReturnFlight Integer
	 * @throws Exception is Thrown on Values not Matching in the Footer bar with the Selected Flights fare
	 * 
	 * NOTE: Note The below Method Passes or Fails based on parameters passed as per the the Availability of Flights for the day
	 */
	public void verifyAmountMatchAsPerSelected(int indexOfDepartureFlight, int indexOfReturnFlight) throws Exception {
		selectDepartureAndReturnFlightsByRowIndex(indexOfDepartureFlight, indexOfReturnFlight);
		log.info("############The Total FARE Value is "
				+ driver.findElement(By.xpath("//span[contains(@class,'splitVw-total-fare')]")).getText());

		String xpath_Amt_RetFlight = "//div[@id='rt-domrt-jrny']/div/div[@class='fli-list splitVw-listing']["
				+ indexOfReturnFlight + "]//span[@class='INR']/../..";
		String xpath_Amt_DepFlight = "//div[@id='ow_domrt-jrny']/div/div[@class='fli-list splitVw-listing']["
				+ indexOfDepartureFlight + "]//span[@class='INR']/../..";
		String DepFlightPrice = driver.findElement(By.xpath(xpath_Amt_DepFlight)).getText().replace("Rs ", "")
				.replaceAll(",", "");
		String RetFlightPrice = driver.findElement(By.xpath(xpath_Amt_RetFlight)).getText().replace("Rs ", "")
				.replaceAll(",", "");

		int priceOfDep = Integer.parseInt(DepFlightPrice);
		int priceOfRet = Integer.parseInt(RetFlightPrice);
		int totalCost = priceOfDep + priceOfRet;
		
		/*if(checkIfDealExists()){
			int dealPrice = Integer.parseInt(dealTag_Price.getText().replace("Rs ", ""));
			System.out.println("!!!!!!!    Deal Price "+dealTag_Price.getText().replace("Rs ", ""));
		    totalCost = priceOfDep + priceOfRet+dealPrice;
		}*/
		
		System.out.println(" $$$$$$$ priceOfDep   " + priceOfDep + "  priceOfRet " + priceOfRet +" so the Total "+totalCost+" $$$$$  ");
		

		System.out.println("Prices of Departure Flight " + priceOfDep + " and Return Flight " + priceOfRet
				+ " AND TOTAL COST = " + totalCost);
		log.info("Prices of Departure Flight" + priceOfDep + " and Return Flight " + priceOfRet
				+ " AND TOTAL COST = " + totalCost);
		int footerDepPrice = Integer
				.parseInt(footerDepartureFlightCharge.getText().replaceAll(",", "").replace("Rs ", ""));
		int footerRetPrice = Integer
				.parseInt(footerReturnFlightCharge.getText().replaceAll(",", "").replace("Rs ", ""));
		int footerTotalPrice = Integer
				.parseInt(footerTotalFlightCharge.getText().replaceAll(",", "").replace("Rs ", ""));
		System.out.println("Prices of Footer Departure Flight " + footerDepPrice + " and Return Flight Footer "
				+ footerRetPrice + " AND TOTAL FOOTER COST = " + footerTotalPrice);
		log.info("Prices of Footer Departure Flight " + footerDepPrice + " and Return Flight Footer "
				+ footerRetPrice + " AND TOTAL FOOTER COST = " + footerTotalPrice);
		Assert.assertEquals(priceOfDep, footerDepPrice);
		Assert.assertEquals(priceOfRet, footerRetPrice);
		Assert.assertEquals(totalCost, footerTotalPrice);
		
		if (!(priceOfDep == footerDepPrice && priceOfRet == footerRetPrice)) {	
			throw new Exception("The Amounts Does not Match as per Selection");
		}else{
			System.out.println("$$$$$$$$$$$ YAAHOOOO CODING CHALLENGE 2 COMPLETED $$$$$$$$");
		}

	}

}
