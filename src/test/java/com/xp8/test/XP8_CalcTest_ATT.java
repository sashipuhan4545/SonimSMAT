/*package com.xp8.test;

import java.awt.AWTException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.util.Hashtable;
import java.util.Properties;

import javax.swing.JOptionPane;

import org.json.simple.parser.ParseException;
import org.openqa.selenium.support.PageFactory;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.graphics.gui.CountIncrementFile;
import com.graphics.gui.ToolFrame;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.ExtentTestInterruptedException;
import com.relevantcodes.extentreports.LogStatus;
import com.xp8.util.DataProviders;
import com.xp8.util.ExcelConstants;
import com.xp8.util.ExcelReader;
import com.xp8.util.Locators_BaseUtil;
import com.xp8.util.XP8_CalcUtil;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.AndroidKeyCode;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

import com.xp8.util.Locators_Calculator;

public class XP8_CalcTest_ATT extends XP8_CalcUtil {

    public ExcelReader excel;
	Properties properties;
	public static ExtentReports extent;
	public static ExtentTest test;
	public static ExtentTestInterruptedException testexception;
	//public AndroidDriver<AndroidElement> aDriver;
	
	@BeforeSuite
	public void beforeSuite() throws IOException 
	{
		extent = new ExtentReports("src/test/resources/extentreport/XP8_PreBundleApps_CalcTest_Report.html", true); //Provide Desired Report Directory Location and Name
		extent.loadConfig(new File(System.getProperty("user.dir")+"//ReportsConfig.xml"));
		extent.addSystemInfo("Appium", "1.2.7").addSystemInfo("Environment", "TEST");
		
		
	}
	
	@BeforeMethod()
	public  void beforeMethod(Method method) 
	{
		test = extent.startTest( (this.getClass().getSimpleName() +" :: "+  method.getName()),method.getName()); //Test Case Start Here
		test.assignAuthor("Farheen Taj"); //Test Script Author Name
	}
	
	@AfterMethod()
	public void tearDown(ITestResult result,Method method) throws IOException, InterruptedException
	{
		if(result.getStatus()==ITestResult.FAILURE)
		{	
		   String screenshot_path=captureScreenshot(method.getName());
		   String image= test.addScreenCapture(screenshot_path);		
			test.log(LogStatus.FAIL,result.getThrowable());			
			clearRecentApps();
		}
		extent.endTest(test);
		extent.flush();
	}
	
	
	@AfterMethod
	public void setProgressBar_TestResult(){

		
		  int count =CountIncrementFile.getCount(16); 
		
		  if(count==96) {
			  count=count+4;
			  CountIncrementFile.putCount(count);
			  }
		  else {
			    
				ToolFrame.progressBar.setValue(count);
			    count=count+16;
				CountIncrementFile.putCount(count);
		  }
		  	
	}
	
	@BeforeTest
	public void setupSys() throws MalformedURLException, InterruptedException, FileNotFoundException, AWTException{
		properties=loadDriverAndProperties();
		Locators_Calculator loc=new Locators_Calculator(aDriver);
		Locators_BaseUtil loc1 = new Locators_BaseUtil(aDriver);	
		PageFactory.initElements(new AppiumFieldDecorator(aDriver),loc);
		PageFactory.initElements(new AppiumFieldDecorator(aDriver),loc1);
		excel=new ExcelReader(ExcelConstants.XP5S_XL_PATH);	
		
	}
	
	
	@Test(priority=1,dataProvider="LaunchCalculatorApp", dataProviderClass=DataProviders.class)
	public void XP8_CALC_TC_0001_Launch_and_Exit(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========XP8_Calc_001============");
		 startAdbLog("XP8_Calculator_01");
		 recordVideo("XP8_Calculator_01");
		 launchCalc();
		 validateCalcLaunch();
		test.log(LogStatus.PASS, "Test case status is Passed");	
	}
	
	
	@Test(priority=2,dataProvider="LaunchCalculatorApp", dataProviderClass=DataProviders.class)
	public void XP8_CALC_TC_0002_Basic_functions_with_decimalpt(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========XP8_Calc_002============");
		startAdbLog("XP8_Calculator_02");
		recordVideo("XP8_Calculator_02");
		launchCalc();
		basicOperationwithdecimalpt();
		test.log(LogStatus.PASS, "Test case status is Passed");
	}
	
	
	@Test(priority=3,dataProvider="LaunchCalculatorApp", dataProviderClass=DataProviders.class)
	public void XP8_CALC_TC_0003_Basic_functions_without_decimalpt(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========XP8_Calc_003============");
		startAdbLog("XP8_Calculator_03");
		recordVideo("XP8_Calculator_03");
		launchCalc();
		basicOperationwithoutdecimalpt();
		test.log(LogStatus.PASS, "Test case status is Passed");	
	}

	@Test(priority=4,dataProvider="LaunchCalculatorApp", dataProviderClass=DataProviders.class)
	public void XP8_CALC_TC_0004_Functionality_Allkeys_shortpress(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========XP8_Calc_004============");
		startAdbLog("XP8_Calculator_004");
		recordVideo("XP8_Calculator_004");
		launchCalc();
		shortpress();
		test.log(LogStatus.PASS, "Test case status is Passed");		
	}	
	
	@Test(priority=5,dataProvider="LaunchCalculatorApp", dataProviderClass=DataProviders.class)
	public void XP8_CALC_TC_0005_Functionality_Allkeys_longpress(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========XP8_Calc_005============");
		startAdbLog("XP8_Calculator_005");
		recordVideo("XP8_Calculator_005");
		launchCalc();
		longPress();
		test.log(LogStatus.PASS, "Test case status is Passed");		
	}
	
	@Test(priority=6,dataProvider="LaunchCalculatorApp", dataProviderClass=DataProviders.class)
	public void XP8_CALC_TC_0006_Operation_with_Zero(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========XP8_Calc_006============");
		startAdbLog("XP8_Calculator_006");
		recordVideo("XP8_Calculator_006");
		launchCalc();
		basicOperationWithZero();
		test.log(LogStatus.PASS, "Test case status is Passed");		
	}
	
   
	
	@Test(priority=7,dataProvider="LaunchCalculatorApp", dataProviderClass=DataProviders.class)
	public void XP8_CALC_TC_0007_Digitpresent_Relaunch_ScreenOrientation(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========XP8_Calc_007============");
		startAdbLog("XP8_Calculator_007");
		recordVideo("XP8_Calculator_007");
		launchCalc();
		relaunch_ScreenOrientation();
		test.log(LogStatus.PASS, "Test case status is Passed");		
	}
	
	

}
*/