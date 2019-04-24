package com.TestAutomation.MA.Utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

import org.apache.log4j.chainsaw.Main;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class DateUtil {
	public static String currentDay;
	public static String currentMonth;
	public static String currentYear;
/*
 * For Months
 * Jan 31, Feb 28, Mar 31, Apr 30, May 31, Jun 30, Jul 31, Aug 31, Sep 30, Oct 31, Nov 30, Dec 31
 * 
 */
	HashMap<String,Integer> daysInMonths =  new HashMap<String,Integer>();
	HashMap<Integer,String> monthCodes = new HashMap<Integer,String>();
	
	public void returnMonthsCode(){
		
	}
	public void select7thDayFromToday(WebElement dateElem){
		String dateValue=dateElem.getAttribute("aria-label");
		
		String dtParts[] = dateValue.split(" ");
		
		String mm = dtParts[1];
		int dt = Integer.parseInt(dtParts[2]);
		int future_dt = dt+7;
		int yr = Integer.parseInt(dtParts[3]);;
		
		daysInMonths.put("Jan", 31);
		if(isALeapYr(dtParts[dtParts.length-1])){
			daysInMonths.put("Feb", 29);
		}else{
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
		
		
		//if(mm!="Dec" && (dt<24 || dt < 23)){
		if(mm!="Dec" && dt+7<=daysInMonths.get(mm)){	
		//Select the dt + 7 days
			WebDriver driver=null;
			
			String xpath = "//div[contains(@aria-label,'"+mm+"')]/div[@class='dateInnerCell']/p[contains(text(),'"+future_dt+"')]";
			driver.findElement(By.xpath(xpath)).click();
			
		//	SOLIDxpath = //div[contains(@aria-label,'Apr')]/div[@class='dateInnerCell']/p[contains(text(),'23')]
			
			//  xpath = //div[contains(@aria-label,'+mm+')]/div[@class='dateInnerCell']/p[contains(text(),'+dt+')]
			
		}else{
		// to click on  calendar   //div[@aria-label='Wed Jan 01 2020']
			String xpath = "//div[contains(@aria-label,'Jan')]/div[@class='dateInnerCell']/p[contains(text(),'"+future_dt+"')]";
			//driver.findElement(By.xpath(xpath)).click();
			
			
		}
		
		
		
	}
	
	public static boolean isALeapYr(String yr){
		boolean leap = false;		
		int year = Integer.parseInt(yr);

        if(year % 4 == 0)
        {
            if( year % 100 == 0)
            {
                // year is divisible by 400, hence the year is a leap year
                if ( year % 400 == 0)
                    leap = true;
                else
                    leap = false;
            }
            else
                leap = true;
        }
        else
            leap = false;
		
		return leap;
	}
	
	
	public static String getCurrentDate(){
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		LocalDate localDate = LocalDate.now();
		String todaysDate = localDate.toString();
		System.out.println("Today's Date is : "+localDate.toString()+"nd lo ");
		String[] dts = todaysDate.split("-");		
		currentDay = dts[2];
		currentMonth=dts[1];
		currentYear=dts[0];
		System.out.println("FROM DATE UTIL"+dtf.format(localDate)); 
		
		return todaysDate;
	}
	
	public String getCurrentyear(){
		return currentYear;
	}
	public String getCurrentMonth(){
		return currentMonth;
	}
	public String getCurrentDay(){
		return currentDay;
	}
}
