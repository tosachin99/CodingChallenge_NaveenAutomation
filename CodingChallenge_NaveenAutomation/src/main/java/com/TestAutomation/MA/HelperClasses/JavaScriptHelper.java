package com.TestAutomation.MA.HelperClasses;

import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class JavaScriptHelper {

		private WebDriver driver;
		private Logger Log = Logger.getLogger(JavaScriptHelper.class);

		public JavaScriptHelper(WebDriver driver) {
			this.driver = driver;
			Log.debug("JavaScriptHelper : " + this.driver.hashCode());
		}

		public Object executeScript(String script) {
			JavascriptExecutor exe = (JavascriptExecutor) driver;
			Log.info(script);
			return exe.executeScript(script);
		}

		public Object executeScript(String script, Object... args) {
			JavascriptExecutor exe = (JavascriptExecutor) driver;
			Log.info(script);
			return exe.executeScript(script, args);
		}

		public void scrollToElement(WebElement element) {
			executeScript("window.scrollTo(arguments[0],arguments[1])", element.getLocation().x, element.getLocation().y);
			Log.info(element);
		}

		public void scrollToElemetAndClick(WebElement element) {
			scrollToElement(element);
			element.click();
			Log.info(element);
		}

		public void scrollIntoView(WebElement element) {
			executeScript("arguments[0].scrollIntoView()", element);
			Log.info(element);
		}

		public void scrollIntoViewAndClick(WebElement element) {
			scrollIntoView(element);
			element.click();
			Log.info(element);
		}

		public void scrollDownVertically() {
			executeScript("window.scrollTo(0, document.body.scrollHeight)");
		}

		public void scrollUpVertically() {
			executeScript("window.scrollTo(0, -document.body.scrollHeight)");
		}

		public void scrollDownByPixel() {
			executeScript("window.scrollBy(0,1500)");
		}

		public void scrollUpByPixel() {
			executeScript("window.scrollBy(0,-1500)");
		}

		public void ZoomInBypercentage() {
			executeScript("document.body.style.zoom='40%'");
		}

		public void ZoomBy100percentage() {
			executeScript("document.body.style.zoom='100%'");
		}

		public void constantScrollingTillBottom() {
		try {
			long lastHeight = (long) ((JavascriptExecutor) driver).executeScript("return document.body.scrollHeight");

			while (true) {
				((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight);");
				Thread.sleep(2000);

				long newHeight = (long) ((JavascriptExecutor) driver)
						.executeScript("return document.body.scrollHeight");
				if (newHeight == lastHeight) {
					break;
				}
				lastHeight = newHeight;
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
}
}
