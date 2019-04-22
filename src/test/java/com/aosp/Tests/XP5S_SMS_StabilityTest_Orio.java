package com.aosp.Tests;

import java.awt.AWTException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.util.Hashtable;
import java.util.Properties;

import org.json.simple.parser.ParseException;
import org.openqa.selenium.support.PageFactory;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aosp.Utils.Locators_Stability;
import com.aosp.Utils.XP5S_Stability_Util;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTestInterruptedException;
import com.relevantcodes.extentreports.LogStatus;
import com.xp5S.util.BaseUtil;
import com.xp5S.util.DataProviders;
import com.xp5S.util.ExcelConstants;
import com.xp5S.util.ExcelReader;
import com.xp5S.util.GetMethods;
import com.xp5S.util.Locators_BaseUtil;
import com.xp5S.util.appiumService;

import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class XP5S_SMS_StabilityTest_Orio extends XP5S_Stability_Util  {
	
	Properties properties;
	public ExcelReader excel;
	public static ExtentTestInterruptedException testexception;

	public int  itr =1;
	boolean value = false;

	@BeforeSuite
	public void beforeSuite() throws IOException, InterruptedException, ParseException
	{
		extent = new ExtentReports("src/test/resources/extentreport/XP5S_SMS_Stability_Orio_TestReport.html", true);
		fetch_Devices_Details();
	}

	@BeforeMethod()
	public  void beforeMethod(Method method) throws InterruptedException 
	{
		test = extent.startTest( (this.getClass().getSimpleName() +" :: "+  method.getName()),method.getName()); //Test Case Start Here
		test.assignAuthor(""); //Test Script Author Name
		extent.loadConfig(new File(System.getProperty("user.dir")+"//ReportsConfig.xml"));
		extent.addSystemInfo("Appium Version", "1.2.7").addSystemInfo("Environment", "TEST");
		String BuildNumber=BaseUtil.fetchDeviceProperty("adb shell getprop ro.build.id");
		test.assignCategory("Build #:"+BuildNumber);

	}

	@AfterMethod()
	public void tearDown(ITestResult result,Method method) throws IOException, InterruptedException
	{
		if(result.getStatus()==ITestResult.FAILURE)
		{
			String screenshot_path=captureScreenshot(method.getName());		
			test.log(LogStatus.FAIL,result.getThrowable());
			test.log(LogStatus.FAIL, "screenshot for failure:" +test.addScreenCapture(screenshot_path));
			clearRecentApps();
		}
		extent.endTest(test);
		extent.flush();
	}

	@BeforeSuite
	public void numofTestCases() throws ClassNotFoundException {		
		appiumService.TOTAL_NUM_OF_TESTCASES=GetMethods.TotalTestcase("XP5S_TC", this.getClass());

	}


	@BeforeTest
	public void setupSys() throws MalformedURLException, InterruptedException, FileNotFoundException, AWTException{
		properties=loadDriverAndProperties();
		Locators_Stability loc=new Locators_Stability(aDriver);
		Locators_BaseUtil loc1 =new Locators_BaseUtil(aDriver);
		PageFactory.initElements(new AppiumFieldDecorator(getDriver()),loc1);
		PageFactory.initElements(new AppiumFieldDecorator(getDriver()),loc);
		excel=new ExcelReader(ExcelConstants.XP5S_XL_PATH);		
	}
	
	@Test(priority=1,dataProvider="XP5S_Stability", dataProviderClass=DataProviders.class)
	public void XP5S_TC_001_Stability_Validate_Send_MO_SMS(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException {

		APP_LOGS.info("=========== XP5S_TC_01_Stability============");
		startLog("XP5S_TC_01_Stability");
		SoftAssert sa1 = new SoftAssert();
			for (int i = 1; i <= itr; i++) {
				launch_an_app("messaging");
				delete_All_Threads();
				navigateTo_NewMessage();
				type_New_Message(refNum, data.get("typeMessage"));
				send_SMS();
				validate_Sent_SMS(i,sa1);
				press_BackKey();
			}	
			sa1.assertAll();
	}

	
	@Test(priority=2,dataProvider="XP5S_Stability", dataProviderClass=DataProviders.class)
	public void XP5S_TC_002_Stability_MO_SMS_Maximum_NumberOfCharacters(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException {

		APP_LOGS.info("=========== XP5S_TC_02_Stability============");
		startAdbLog("XP5S_TC_02_Stability");
		SoftAssert sa2 = new SoftAssert();
		customWait(1000);
		for (int i = 1; i <=itr; i++) {
			launch_an_app("messaging");
			delete_All_Threads();
			navigateTo_NewMessage();
			type_New_Message(refNum, data.get("typeMessage1"));
			customWait(1000);
			validate_CharacterAndPageNumber(Locators_Stability.zero_Characters_FirstPage,data.get("expectedChar&PageNum1"));
			send_SMS();
			validate_Sent_SMS(i,sa2);
			press_BackKey();
			customWait(2000);
		}
		sa2.assertAll();
	}
	
	@Test(priority=3,dataProvider="XP5S_Stability", dataProviderClass=DataProviders.class)
	public void XP5S_TC_003_Stability_MT_SMS(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException {

		APP_LOGS.info("=========== XP5S_TC_03_Stability============");
		startAdbLog("XP5S_TC_03_Stability");
		SoftAssert sa3 = new SoftAssert();
		customWait(1000);
		for (int i = 1; i <=itr; i++) {
			launch_an_app("messaging");
			delete_All_Threads();
			sendSMS_fromRefDevice();
			validateMsgSent("received SMS",sa3);	
			press_BackKey();
			customWait(2000);
		}
		sa3.assertAll();
	}

}
