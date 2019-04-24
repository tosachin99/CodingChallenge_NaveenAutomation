package com.dummyPackage;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.TestAutomation.MA.HelperClasses.VerificationHelper;
import com.TestAutomation.MA.PageObjects.HomePage;
import com.TestAutomation.MA.testBase.TestBase;

public class GetRowColNums {

	@SuppressWarnings("deprecation")
	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub

		WebDriver driver;
		/*
		 * System.setProperty("webdriver.ie.driver",System.getProperty("user.dir") +
		 * "\\drivers\\IEDriverServer.exe"); driver= new InternetExplorerDriver();
		 */

		DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
		capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
		System.setProperty("webdriver.ie.driver", System.getProperty("user.dir") + "/drivers/IEDriverServer.exe");
		// log.info("creating object of :"+browser);
		driver = new InternetExplorerDriver(capabilities);

		driver.get("https://myaccess-sit-box.fmr.com:4020/AccessCENTRAL/request/Default.aspx");
		driver.manage().window().maximize();
		Thread.sleep(4000);
		// driver.findElement(By.css(.'tdInitiateRequest")).click();
		driver.findElement(By.cssSelector("#tdInitiateRequest")).click();
		Thread.sleep(4000);

		// to get the no of rows
		List<WebElement> rowCount = driver
				.findElements(By.xpath("//table[@id='requestsTable']//following-sibling::tr"));
		System.out.println("New ROw Count is" + rowCount.size());

		// to get the Col length
		List<WebElement> colLength = driver.findElements(By.xpath(".//*[@id='requestsTableOverflow']/div/table/thead/tr/th"));
		System.out.println("New Col Length is " + colLength.size());

		// to get the value of Cell
		// String st = "Directory Access to Network File Shares";
		String st = "UNIX UserID Creation";
		String val = driver.findElement(By.xpath("//table[@id='requestsTable']//following-sibling::tr//td[contains(text(),'" + st + "')]")).getText();
		System.out.println("The Cell value is :" + val);

		// to click ADd button
		// Working xpath1
		// *[@id='requestsTable']//following-sibling::tr//td[@class='srchResultsTblCellBold'
		// and contains(text(),'Directory Access to Network File
		// Shares')]//following-sibling::td//span[@id='t00071']
		// Working xpath2
		// *[@id='requestsTable']//following-sibling::tr//td[@class='srchResultsTblCellBold'
		// and contains(text(),'Data Control -
		// GEM')]//following-sibling::td//span[contains(text(),'Add')]
	}

}


//=====================Backup of Test case ========================

/*
 * Author a604848 
 */
/*
public class TC001_LoginToMA extends TestBase {

	HomePage homepage;
	
	@Test
	public void test_loginToMA() {
		log.info("=====================Starting Test loginToMA=========================");
		homepage = new HomePage(driver);
		//homepage.loginToMA(corpid);
		homepage.loginToMA(OR.getProperty("LoginCorpId"));
		Reporter.log("the val of cpid is "+OR.getProperty("LoginCorpId"));
		homepage.waitForElement(driver, homepage.landingArea, 10);
		homepage.verifyAllLinks();
		test.log(LogStatus.INFO, "Verified all links");
		VerificationHelper.verifyElementPresent(homepage.lnk_Drafts);
	    homepage.clickOnLeftNav_MyReqsLink(homepage.lnk_Drafts);
	    
		
		log.info("=====================Finished Test loginToMA=========================");
	}

*/













