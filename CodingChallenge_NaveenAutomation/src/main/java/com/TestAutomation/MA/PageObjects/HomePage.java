package com.TestAutomation.MA.PageObjects;

import java.util.HashMap;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import com.TestAutomation.MA.HelperClasses.WaitHelper;
import com.TestAutomation.MA.Utils.DateUtil;
import com.TestAutomation.MA.config.Constants;

public class HomePage {
	private WaitHelper wh;
	public static final Logger log = Logger.getLogger(HomePage.class.getName());
	private WebDriver driver;

	@FindBy(xpath = "//span[text()='Flights']")
	private WebElement lnk_Flights;

	@FindBy(xpath = "//li[contains(text(),'Round Trip')]")
	private WebElement radio_RoundTrip;

	@FindBy(xpath = "//li[contains(text(),'Oneway')]")
	private WebElement radio_Oneway;

	@FindBy(xpath = "//li[contains(text(),'Multi City')]")
	private WebElement radio_MultiCity;

	// @FindBy(xpath="//div[@class='DayPicker-Day DayPicker-Day--start
	// DayPicker-Day--selected DayPicker-Day--today']")
	@FindBy(xpath = "//div[@class='DayPicker-Week']/div[contains(@class,'today')]")
	private WebElement currentDateTile;

	@FindBy(xpath = "//label[contains(@for,'departure')]")
	private WebElement tile_departure;

	@FindBy(xpath = "//label[contains(@for,'return')]")
	private WebElement tile_return;

	@FindBy(id = "fromCity")
	private WebElement txt_FromCity;

	@FindBy(id = "toCity")
	private WebElement txt_toCity;

	@FindBy(xpath = "//input[@placeholder='To']")
	private WebElement srch_to;

	@FindBy(xpath = "//input[@placeholder='From']")
	private WebElement srch_from;

	@FindBy(xpath = "//a[@class='primaryBtn font24 latoBlack widgetSearchBtn ']")
	private WebElement btn_Search;

	/** 
	 * Constructor
	 * @param driver
	 * This Method Also Instantiate the WaitHelper class for once to use across POM
	 */
	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		wh = new WaitHelper(driver);
	}

	/**
	 * @author sachin.hiremath
	 * This Method verifies the Title of Application
	 */
	public void verifyTitle() {
		Assert.assertEquals(driver.getTitle(), Constants.LandingPageTitle);
		System.out.println(driver.getTitle());
	}

	/**
	 * Selects flight section
	 */
	public void selectFlightsSection() {
		lnk_Flights.click();
	}

	public void selectRoundTrip() {
		radio_RoundTrip.click();
	}

	public void selectReturn_tile() {
		tile_return.click();
	}

	public void selectDeparture_tile() {
		tile_departure.click();
	}

	public void clickSearchFlights() {
		btn_Search.click();
	}

	/**This Method Selects the Source Place
	 * @author sachin.hiremath
	 * @param frm_city
	 */
	public void enterFromCity(String frm_city) {
		txt_FromCity.click();
		wh.waitForElementVisible(srch_from, Constants.EXPLICIT_WAIT, Constants.POLLING_WAIT);
		srch_from.click();
		txt_FromCity.sendKeys(frm_city);
		driver.findElement(By.xpath("//div[@class='makeFlex hrtlCenter']/div/p[contains(text(),'" + frm_city + "')]"))
				.click();
	}

	/** This Method Selects the Destination Place 
	 * @author sachin.hiremath
	 * @param to_city
	 */
	public void enterToCity(String to_city) {
		txt_toCity.click();
		wh.waitForElementVisible(srch_to, Constants.EXPLICIT_WAIT, Constants.POLLING_WAIT);
		srch_to.click();
		txt_toCity.sendKeys(to_city);
		driver.findElement(By.xpath("//div[@class='makeFlex hrtlCenter']/div/p[contains(text(),'" + to_city + "')]")).click();
	}

	
	/**
	 * @author sachin.hiremath
	 * @category Date
	 * @Functionality This Method selects the Current From Date and 7th Day date as return date.
	 */
	public void selectDepartureDtAndReturnDt_with7DaysGap() {
		HashMap<String, Integer> daysInMonths = new HashMap<String, Integer>();
		currentDateTile.click();
		String dateValue = currentDateTile.getAttribute("aria-label");
		String dtParts[] = dateValue.split(" ");
		String mm = dtParts[1];
		System.out.println("Val of mm is " + mm);
		int dt = Integer.parseInt(dtParts[2]);
		System.out.println("Val of Dt is " + dt);
		int future_dt = dt + 7;
		System.out.println("Val of future_dt is " + future_dt);

		daysInMonths.put("Jan", 31);
		if (DateUtil.isALeapYr(dtParts[dtParts.length - 1])) {
			daysInMonths.put("Feb", 29);
		} else {
			daysInMonths.put("Feb", 28);
		}
		daysInMonths.put("Mar", 31);
		daysInMonths.put("Apr", 30);
		daysInMonths.put("May", 31);
		daysInMonths.put("Jun", 30);
		daysInMonths.put("Jul", 31);
		daysInMonths.put("Aug", 31);
		daysInMonths.put("Sep", 30);
		daysInMonths.put("Oct", 31);
		daysInMonths.put("Nov", 30);
		daysInMonths.put("Dec", 31);

		if (mm != "Dec" && dt + 7 <= daysInMonths.get(mm)) {
			String xpath_date = "//div[contains(@aria-label,'" + mm + "')]/div[@class='dateInnerCell']/p[contains(text(),'"
					+ future_dt + "')]";
			driver.findElement(By.xpath(xpath_date)).click();
			System.out.println("I have clicked on the Furture Date " + future_dt);

		} else {
			if (future_dt > daysInMonths.get(mm)) {
				future_dt = future_dt - daysInMonths.get(mm);
				System.out.println("***** The Latest Future Date is " + future_dt);
			}
			String CurDt = DateUtil.getCurrentDate();
			String[] dts = CurDt.split("-");

			String CodeMonth = dts[1];
			String nextMnthName = null;

			switch (CodeMonth) {
			case "01":
				nextMnthName = "Feb";
				break;

			case "02":
				nextMnthName = "Mar";
				break;

			case "03":
				nextMnthName = "Apr";
				break;

			case "04":
				nextMnthName = "May";
				break;

			case "05":
				nextMnthName = "Jun";
				break;

			case "06":
				nextMnthName = "Jul";
				break;

			case "07":
				nextMnthName = "Aug";
				break;

			case "08":
				nextMnthName = "Sep";
				break;

			case "09":
				nextMnthName = "Oct";
				break;

			case "10":
				nextMnthName = "Nov";
				break;

			case "11":
				nextMnthName = "Dec";
				break;

			case "12":
				nextMnthName = "Jan";
				break;

			}

			String xpath = "//div[contains(@aria-label,'" + nextMnthName+ "')]/div[@class='dateInnerCell']/p[contains(text(),'" + future_dt + "')]";
				
			driver.findElement(By.xpath(xpath)).click();
		}
	}

}