package com.xp5S.test;

import java.awt.AWTException;
import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException ;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.util.Hashtable;
import java.util.Properties;

import javax.swing.JOptionPane;

import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


import com.graphics.gui.CountIncrementFile;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.ExtentTestInterruptedException;
import com.relevantcodes.extentreports.LogStatus;
import com.sun.mail.iap.CommandFailedException;
import com.xp5S.util.BaseUtil;
import com.xp5S.util.Calculator_Util;
import com.xp5S.util.DataProviders;
import com.xp5S.util.ExcelConstants;
import com.xp5S.util.ExcelReader;
import com.xp5S.util.Locators_BaseUtil;
import com.xp5S.util.Locators_Calculator;
import com.xp5S.util.Locators_SonimCare;


import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.AndroidKeyCode;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class CalculatorTest extends Calculator_Util {
	public ExcelReader excel;
	Properties properties;
	public static ExtentReports extent;
	public static ExtentTest test;
	public static ExtentTestInterruptedException testexception;
	//public AndroidDriver<AndroidElement> aDriver;



	@BeforeSuite
	public void beforeSuite() throws IOException, ParseException  
	{
		extent = new ExtentReports("src/test/resources/extentreport/CalculatorTestReport.html", true); 


	}

	@BeforeMethod()
	public  void beforeMethod(Method method) throws InterruptedException 	
	{

		test = extent.startTest( (this.getClass().getSimpleName() +" :: "+  method.getName()),method.getName());
		String BuildNumber=BaseUtil.fetchDeviceProperty("adb shell getprop ro.build.id");
		test.assignCategory("Build #:"+BuildNumber);


	}


	@AfterMethod()
	public void tearDown(ITestResult result,Method method) throws IOException, ParseException , InterruptedException
	{
		if(result.getStatus()==ITestResult.FAILURE)
		{			

			String screenshot_path=captureScreenshot(method.getName());
			String image= test.addScreenCapture(screenshot_path);
			test.log(LogStatus.FAIL,result.getThrowable());
			test.log(LogStatus.FAIL, "screenshot for failure: "+test.addScreenCapture(image));
			clearRecentApps();
			launchCalculator();

		}
		extent.endTest(test);
		extent.flush();
	}





/*
	@AfterMethod
	public void setProgressBar_TestResult() throws FileNotFoundException, IOException, ParseException , ParseException {
		int count =CountIncrementFile.getCount(10);
		ToolFrame.progressBar.setValue(count);
		count=count+10;
		CountIncrementFile.putCount(count);



	}
*/


	@BeforeTest
	public void setupSys() throws MalformedURLException, InterruptedException, FileNotFoundException, AWTException{
		properties=loadDriverAndProperties();
		Locators_Calculator loc=new Locators_Calculator(aDriver);
		Locators_BaseUtil loc1 =new Locators_BaseUtil(aDriver);
		PageFactory.initElements(new AppiumFieldDecorator(getDriver()),loc1);		
		PageFactory.initElements(new AppiumFieldDecorator(getDriver()),loc);
		excel=new ExcelReader(ExcelConstants.XP5S_XL_PATH);		
	}

	@AfterTest
	public void EndTest() {
		extent.flush();
		extent.close();
	}


	@Test(priority=1,dataProvider="CalculatorTest", dataProviderClass=DataProviders.class)
	public void Xp5S_Calculator_01_Validate_Calculator_Launch(Hashtable<String, String> data, Method m) throws InterruptedException, AWTException, IOException, ParseException 
	{
		APP_LOGS.info("===========Xp5S_Calculator_01============");
		startAdbLog(" Xp5S_Calculator_01");
		recordVideo(" Xp5S_Calculator_01");	
		launchCalculator();
		validateCalculatorLaunch();
		 
		test.log(LogStatus.PASS, "Test case status is Pass");
	}

	@Test(priority=2,dataProvider="CalculatorTest", dataProviderClass=DataProviders.class)
	public void Xp5S_Calculator_02_Validate_Basic_functions_with_Decimalpt(Hashtable<String, String> data, Method m) throws InterruptedException, AWTException, IOException, ParseException 
	{		
		APP_LOGS.info("===========Xp5S_Calculator_02============");
		startAdbLog("Xp5S_Calculator_02");
		recordVideo("Xp5S_Calculator_02");
		launchCalculator();
		validateBasicOperationWithDecimalPnt();
		 
		customWait(2000);
		test.log(LogStatus.PASS, "Test case status is Pass");
	}


	@Test(priority=3,dataProvider="CalculatorTest", dataProviderClass=DataProviders.class)
	public void Xp5S_Calculator_03_Validate_Basic_functions_without_Decimalpt(Hashtable<String, String> data, Method m) throws InterruptedException, AWTException, IOException, ParseException 
	{		   
		APP_LOGS.info("===========Xp5S_Calculator_03============");
		startAdbLog("Xp5S_Calculator_03");
		recordVideo("Xp5S_Calculator_03");
		validate_BasicOperationWithoutDecimalpt();
		customWait(4000);
		 
		customWait(2000);
		test.log(LogStatus.PASS, "Test case status is Pass");
	}


	@Test(priority=4,dataProvider="CalculatorTest", dataProviderClass=DataProviders.class)
	public void XP5S_Calculator_04_Validate_Functionality_Allkeys_Shortpress(Hashtable<String, String> data, Method m) throws InterruptedException, AWTException, IOException, ParseException 
	{
		APP_LOGS.info("===========XP8_Calculator_04============");
		startAdbLog("XP8_Calculator_04");  
		recordVideo("XP8_Calculator_04");
		validateShortPressNum();
		validateShortPressAdvanceKey();
		customWait(4000);
		 
		test.log(LogStatus.PASS, "Test case status is Pass");		
	}

	@Test(priority=5,dataProvider="CalculatorTest", dataProviderClass=DataProviders.class)
	public void XP5S_Calculator_05_Validate_Functionality_Allnumberkeys_Longpress(Hashtable<String, String> data, Method m) throws InterruptedException, AWTException, IOException, ParseException 
	{
		APP_LOGS.info("===========XP8_Calculator_05============");
		startAdbLog("XP8_Calculator_05");
		recordVideo("XP8_Calculator_05");
		launchCalculator();
		validateLongPress();
		test.log(LogStatus.PASS, "Test case status is Pass");		
	}

	@Test(priority=6,dataProvider="CalculatorTest", dataProviderClass=DataProviders.class)
	public void XP5S_Calculator_06_Validate_Operation_with_Zero(Hashtable<String, String> data, Method m) throws InterruptedException, AWTException, IOException, ParseException 
	{		
		APP_LOGS.info("===========XP8_Calculator_06============");
		startAdbLog("XP8_Calculator_06");
		recordVideo("XP8_Calculator_06");
		launchCalculator();
		validate_BasicOperationWithZero();
		test.log(LogStatus.PASS, "Test case status is Pass");		
	}

	@Test(priority=7, dataProvider="CalculatorTest", dataProviderClass=DataProviders.class)
	public void XP5S_Calculator_07_Validate_InfiniteOperation(Hashtable<String, String> data, Method m) throws InterruptedException, AWTException, IOException, ParseException 
	{		
		APP_LOGS.info("===========XP8_Calculator_07============");
		startAdbLog("XP8_Calculator_07");
		recordVideo("XP8_Calculator_07");
		launchCalculator();
		firstclick();
		click_Division();
		click_Number(0);
		click_DPAD_Center();
		validate_InfiniteResult();
		test.log(LogStatus.PASS, "Test case status is Pass");	
	}	

	@Test(priority=8, dataProvider="CalculatorTest", dataProviderClass=DataProviders.class)
	public void XP5S_Calculator_08_Validate_PowerOperation(Hashtable<String, String> data, Method m) throws InterruptedException, AWTException, IOException, ParseException 
	{		
		APP_LOGS.info("===========XP8_Calculator_08============");
		startAdbLog("XP8_Calculator_08");
		recordVideo("XP8_Calculator_08");
		launchCalculator();
		navigateTo_AdvancePanel();
		i=firstclick();
		click_OnPowerIcon();
		j=secondclick();
		clickOn_Equal();
		validate_PowerOperation();
		test.log(LogStatus.PASS, "Test case status is Pass");	
	}	

	@Test(priority=9, dataProvider="CalculatorTest", dataProviderClass=DataProviders.class)
	public void XP5S_Calculator_09_Validate_Pie_Value(Hashtable<String, String> data, Method m) throws InterruptedException, AWTException, IOException, ParseException 
	{		
		APP_LOGS.info("===========XP8_Calculator_09============");
		startAdbLog("XP8_Calculator_09");
		recordVideo("XP8_Calculator_09");
		launchCalculator();navigateTo_AdvancePanel();
		clickBtn(Locators_Calculator.pie);
		clickOn_Equal();	
		validate_PieValue();		
		test.log(LogStatus.PASS, "Test case status is Pass");	
	}

	@Test(priority=10, dataProvider="CalculatorTest", dataProviderClass=DataProviders.class)
	public void XP5S_Calculator_10_Validate_ExponentialValue(Hashtable<String, String> data, Method m) throws InterruptedException, AWTException, IOException, ParseException 
	{		
		APP_LOGS.info("===========XP8_Calculator_10============");
		startAdbLog("XP8_Calculator_10");
		recordVideo("XP8_Calculator_10");
		launchCalculator();
		navigateTo_AdvancePanel();
		clickBtn(Locators_Calculator.exponential);
		clickOn_Equal();
		validate_ExponentialValue();
		test.log(LogStatus.PASS, "Test case status is Pass");	
	}



}
