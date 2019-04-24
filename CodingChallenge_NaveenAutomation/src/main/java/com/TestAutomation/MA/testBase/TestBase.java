package com.TestAutomation.MA.testBase;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

import com.TestAutomation.MA.config.Constants;
import com.TestAutomation.MA.customListener.WebEventListener;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;

/**
 * @Author : Sachin Hiremath
 *
 */
public class TestBase implements Constants{
	public static final Logger log = Logger.getLogger(TestBase.class.getName());
	public Properties OR=new Properties();
	public File f1;
	public FileInputStream file;
	public WebDriver dr;
	String url = null;
	public static ITestResult result;
	// public static ExtentHtmlReporter htmlreporter;
	public static ExtentReports extent;
	public static ExtentTest test;

	public EventFiringWebDriver driver;
	public WebEventListener eventListener;
	static Properties p = new Properties();
//	public WebDriver getDriver() {
//		return dr;
//	}

	

	static {
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ssss");
		final String filePath = System.getProperty("user.dir") + "/src/main/java/com/TestAutomation/MA/ExecutionReport/" + "_"+ formater.format(calendar.getTime()) + ".html";
		ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(filePath);
		htmlReporter.config().setDocumentTitle("Regression Reports");
		htmlReporter.config().setReportName("Automation Reports By Sachin Hiremath");
		htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
		htmlReporter.config().setTheme(Theme.STANDARD);

		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		extent.setSystemInfo("OS", System.getProperty("os.name"));
		extent.setSystemInfo("USER", System.getProperty("user.name"));

		try {
			String env = getExecEnv();
			extent.setSystemInfo("ENVIRONMENT", env);
			String brw = getExecBrw();
			extent.setSystemInfo("EXECUTION BROWSER", brw);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("FAILED TO GET ENV And BRW");
		}

	}

	public static String getExecBrw() throws Exception {
		//Properties p = new Properties();
		p.load(new FileInputStream(System.getProperty("user.dir") + "/src/main/java/com/TestAutomation/MA/config/config.properties"));	
		return  p.getProperty("ExecutionBrowser");
	}

	public static String getExecEnv() throws Exception {
		//Properties p = new Properties();
		p.load(new FileInputStream(
				System.getProperty("user.dir") + "/src/main/java/com/TestAutomation/MA/config/config.properties"));
		return p.getProperty("ENV");
	}

	@BeforeClass
	public void init() throws IOException {
		loadPropertiesFile();
		String log4jConfpath = "Log4j.properties";
		PropertyConfigurator.configure(log4jConfpath);
		//String browser = new Config(OR).getExecutionBrowser();
		String browser=OR.getProperty("ExecutionBrowser");
		
		selectBrowser(browser);
		System.out.println("You have selected *" + browser + "* browser for Test Execution");
		getUrl();

	}

	public void loadPropertiesFile() throws IOException {
		//OR = new Properties();
		f1 = new File(System.getProperty("user.dir") + "/src/main/java/com/TestAutomation/MA/config/config.properties");
		file = new FileInputStream(f1);
		OR.load(file);
		System.out.println("Loaded the Prop file");
		log.info("loading config.properties");
	}

	/*
	 * **** Select the Browser.
	 */
	@SuppressWarnings("deprecation")
	public void selectBrowser(String browser) {
		if (System.getProperty("os.name").contains("Window")) {
			System.out.println("Your Operating System is Windows");
			if (browser.equalsIgnoreCase("chrome")) {
				System.setProperty("webdriver.chrome.driver",
						System.getProperty("user.dir") + "/drivers/chromedriver.exe");
				log.info("creating object of :" + browser.toString());
				dr = new ChromeDriver();
				driver = new EventFiringWebDriver(dr);
			
				eventListener = new WebEventListener();
				driver.register(eventListener);

			} else if (browser.equalsIgnoreCase("firefox")) {
				DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
				capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,true);
				System.setProperty("webdridrver.gecko.driver",System.getProperty("user.dir") + "/drivers/geckodriver.exe");
				log.info("creating object of :" + browser);
				dr = new FirefoxDriver(capabilities);
				driver = new EventFiringWebDriver(dr);
				eventListener = new WebEventListener();
				driver.register(eventListener);

			} else if (browser.equalsIgnoreCase("IE")) {
				DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
				capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,true);
				capabilities.setCapability("ignoreZoomSetting", true);
				System.setProperty("webdriver.ie.driver",System.getProperty("user.dir") + "/drivers/IEDriverServer.exe");
				log.info("creating object of :" + browser);
				dr = new InternetExplorerDriver(capabilities);
				//driver.manage().deleteAllCookies();
				driver = new EventFiringWebDriver(dr);
				eventListener = new WebEventListener();
				driver.register(eventListener);
				driver.findElement(By.tagName("html")).sendKeys(Keys.chord(Keys.CONTROL, "0"));
				
			} else {
				System.out.println("The browser choosed is incorrect");
			}
	/*
	 * Execution in case of Mac System.
	 */
		} else if (System.getProperty("os.name").contains("Mac")) {
			System.out.println("Your Operating System is MAC");
			log.info("Your OS is MAC");
			if (browser.equalsIgnoreCase("chrome")) {
				System.out.println("The Current Dir is ::::::::: "+System.getProperty("user.dir"));
				System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/drivers/chromedriver");
				log.info("creating object of :" + browser.toString());
				
				/*ChromeOptions options = new ChromeOptions();
				options.addArguments("--incognito");
				DesiredCapabilities capabilities = DesiredCapabilities.chrome();
				capabilities.setCapability(ChromeOptions.CAPABILITY, options);*/
				dr = new ChromeDriver();
				driver = new EventFiringWebDriver(dr);
				driver.manage().deleteAllCookies();
				dr.manage().deleteAllCookies();
				eventListener = new WebEventListener();
				driver.register(eventListener);

			} else if (browser.equalsIgnoreCase("firefox")) {
				DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
				capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,
						true);
				System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "/drivers/geckodriver");
				log.info("creating object of :" + browser);
				dr = new FirefoxDriver(capabilities);
				driver = new EventFiringWebDriver(dr);
				eventListener = new WebEventListener();
				driver.register(eventListener);

			} else if (browser.equalsIgnoreCase("IE")) {
				DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
				capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,
						true);
				System.setProperty("webdriver.ie.driver", System.getProperty("user.dir") + "/drivers/IEDriverServer");
				log.info("creating object of :" + browser);
				dr = new InternetExplorerDriver(capabilities);
				driver = new EventFiringWebDriver(dr);
				eventListener = new WebEventListener();
				driver.register(eventListener);
			} else {
				System.out.println("The browser choosed is incorrect");
			}

		}

	}

	public void getUrl() throws FileNotFoundException, IOException {	
		p.load(new FileInputStream(System.getProperty("user.dir") + "/src/main/java/com/TestAutomation/MA/config/config.properties"));
		driver.get(p.getProperty("SIT1"));
		log.info("navigating to :-" + p.getProperty("SIT1"));
		driver.manage().window().maximize();
		log.info("Navigated to the App URL and Maximized the Window");
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	}

	public String captureScreen(String fileName) {
		if (fileName == "") {
			fileName = "blank";
		}
		File destFile = null;
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_sss");

		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

		try {
			String reportDirectory = new File(System.getProperty("user.dir")).getAbsolutePath() + "/src/main/java/com/TestAutomation/MA/ExecutionReport/Screenshots/";
			destFile = new File(reportDirectory + fileName + "_" + formater.format(calendar.getTime()) + ".png");
			FileUtils.copyFile(scrFile, destFile);
			// This will help us to link the screen shot in testNG report
			Reporter.log("<a href='" + destFile.getAbsolutePath() + "'> <img src='" + destFile.getAbsolutePath()
					+ "' height='100' width='100'/> </a>");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return destFile.toString();
	}

	@AfterMethod
	public synchronized void getresult(ITestResult result) throws IOException {
		/*
		 * if (result.getStatus() == ITestResult.STARTED) { test.log(Status.INFO,
		 * result.getName() + " Test Execution has started"); //this block of code is
		 * never executed. }
		 */
		if (result.getStatus() == ITestResult.SUCCESS) {
			test.pass(MarkupHelper.createLabel(result.getName() + " Test Case Passed", ExtentColor.GREEN));

		} else if (result.getStatus() == ITestResult.SKIP) {

			test.skip(MarkupHelper.createLabel(result.getName() + " Test Case SKIPPED ", ExtentColor.YELLOW));

		} else if (result.getStatus() == ITestResult.FAILURE) {

			test.log(Status.ERROR, result.getName() + " Test is Failed    ---" + result.getThrowable());
			String screen = captureScreen(result.getName());
			test.fail("details & Failure Screenshot : ",
					MediaEntityBuilder.createScreenCaptureFromPath(screen).build());

		} else {

			System.out.println("Status Unknown Check once again");
			test.log(Status.INFO, "Status Unknown Check once again");
		}
		// extent.flush();
	}

	@AfterClass(alwaysRun = true)
	public void endTest() {
		closeBrowser();
	}

	public void closeBrowser() {
		driver.quit();
		log.info("Browser Closed");
		extent.flush();
	}

	public void log(String data) {
		log.info(data);
		Reporter.log(data);
		test.log(Status.INFO, data);
	}

}
