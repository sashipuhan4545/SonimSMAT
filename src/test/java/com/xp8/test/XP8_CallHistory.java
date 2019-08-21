package com.xp8.test;

import java.awt.AWTException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Hashtable;
import java.util.Properties;

import org.json.simple.parser.ParseException;
import org.openqa.selenium.support.PageFactory;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.graphics.gui.JsonFileReaderAndWriter;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTestInterruptedException;
import com.relevantcodes.extentreports.LogStatus;
import com.xp5S.util.GetMethods;
import com.xp5S.util.appiumService;
import com.xp8.util.DataProviders;
import com.xp8.util.ExcelConstants;
import com.xp8.util.ExcelReader;
import com.xp8.util.Locators_BaseUtil;
import com.xp8.util.Locators_XP8_CallHistory;
import com.xp8.util.XP8_CallHistory_Utils;


import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.AndroidKeyCode;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;


public class XP8_CallHistory extends XP8_CallHistory_Utils {


	public ExcelReader excel;
	Properties properties;
	public static ExtentTestInterruptedException testexception;



	@BeforeSuite
	public void beforeSuite() throws FileNotFoundException, InterruptedException,  IOException, ParseException, ParseException, ParseException, ParseException, ParseException, ParseException 
	{
		extent = new ExtentReports("src/test/resources/extentreport/XP8_CallHistory_TestReport.html", true); //Provide Desired Report Directory Location and Name
		extent.loadConfig(new File("src/test/resources/StorageFile/ReportsConfig.xml"));
		extent.addSystemInfo("Build #",JsonFileReaderAndWriter.primaryDevFirmwareReader())
		.addSystemInfo("Product",JsonFileReaderAndWriter.primaryDevModelReader())
		.addSystemInfo("Operator", JsonFileReaderAndWriter.primaryDevOperatorReader());		
		fetch_Devices_Details();	

	}

	@BeforeSuite
	public void numofTestCases() throws ClassNotFoundException {



		appiumService.TOTAL_NUM_OF_TESTCASES=GetMethods.TotalTestcase("XP8_TC", this.getClass());


	}


	@BeforeMethod()
	public  void beforeMethod(Method method) 
	{
		test = extent.startTest( (this.getClass().getSimpleName() +" :: "+  method.getName()),method.getName()); //Test Case Start Here
	}


	@BeforeClass
	public void copyFilesToDevice() throws  IOException, ParseException, ParseException, ParseException, ParseException, ParseException, ParseException  {
		System.out.println("Executing clear log screen");
		File dir = new File("src/test/resources/adbLogs");
		if(dir.isDirectory() == false) {
			System.out.println("Not a directory. Do nothing");
			return;
		}

		File[] listFiles = dir.listFiles();
		for(File file : listFiles){
			System.out.println("Deleting "+file.getName());
			file.delete();

		}

	}

	@AfterMethod()
	public void tearDown(ITestResult result,Method method) throws  IOException, ParseException, ParseException, ParseException, ParseException, ParseException, ParseException , InterruptedException
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


	@BeforeTest
	public void setupSys() throws MalformedURLException, InterruptedException, FileNotFoundException, AWTException{
		properties=loadDriverAndProperties();
		Locators_XP8_CallHistory loc=new Locators_XP8_CallHistory(aDriver);
		Locators_BaseUtil loc1 = new Locators_BaseUtil(aDriver);	
		PageFactory.initElements(new AppiumFieldDecorator(aDriver),loc);
		PageFactory.initElements(new AppiumFieldDecorator(aDriver),loc1);
		excel=new ExcelReader(ExcelConstants.XP5S_XL_PATH);	
	}
	//=========================================================Test Scripts================================================================
	@Test(priority=1,dataProvider="XP8_CallHistory", dataProviderClass=DataProviders.class)
	public void XP8_TC_01_CallHistory_Validate_Saved_Contacts(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException
	{
		SoftAssert SA = new SoftAssert();
		APP_LOGS.info("===========XP8_CallHistory_01============");
		launch_an_app("phone");
		enterNumberInDialpad(pryNum, SA);
		clickBackButton(3);
		minWait();
		enterNumberInDialpad(refNum, SA);
		clickBackButton(3);
		SA.assertAll();
	}

	@Test(priority=2,dataProvider="XP8_CallHistory", dataProviderClass=DataProviders.class)
	public void XP8_TC_02_CallHistory_Validate_Phone_Application_Launch(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException
	{
		SoftAssert SA = new SoftAssert();
		APP_LOGS.info("===========XP8_CallHistory_02============");
		launch_an_app("phone");
		validatePhoneAppLaunch(SA);
		navigateTocallHistory(SA);
		validateCallLogList(SA);
		validateContactFromCallLog(SA);
		clickBackButton(3);
		validateCallFromCallLog(SA);
		SA.assertAll();
	}

	@Test(priority=3,dataProvider="XP8_CallHistory", dataProviderClass=DataProviders.class)
	public void XP8_TC_03_CallHistory_Validate_Call_History_Contact_Details(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException
	{
		SoftAssert SA= new SoftAssert();
		APP_LOGS.info("===========XP8_CallHistory_03============");
		launch_an_app("phone");
		validateCreateNewContactFromCallLog("UI Testing", SA);
		validate_frequent_list(SA);
		add_picture_to_contact(SA);
		aDriver.pressKeyCode(AndroidKeyCode.HOME);
		SA.assertAll();
	}

	@Test(priority=4,dataProvider="XP8_CallHistory", dataProviderClass=DataProviders.class)
	public void XP8_TC_04_CallHistory_Missed_Call_Log_List(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException
	{
		SoftAssert SA = new SoftAssert();
		APP_LOGS.info("===========XP8_CallHistory_04============");
		launch_an_app("phone");
		navigateTocallHistory(SA);
		click_Missed_Call_Log(SA);
		validateCallLogList(SA);
		validateContactFromCallLog(SA);
		clickBackButton(2);
		validate_Call_From_Missed_Call_Log(SA);
		minWait();
		clickBtn(Locators_XP8_CallHistory.contactDetails);
		minWait();
		validateSavedContact("UI Testing", "", SA);
		validateCreateNewContactFromCallLog("UI Testing", SA);
		clickBackButton(1);
		//customWait(7000);
		//validate_frequent_list(SA);
		//customWait(3000);
		//add_picture_to_contact(SA);
		minWait();
		aDriver.pressKeyCode(AndroidKeyCode.HOME);
		SA.assertAll();
	}

	@Test(priority=5,dataProvider="XP8_CallHistory", dataProviderClass=DataProviders.class)
	public void XP8_TC_05_CallHistory_Validate_Send_Message(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException
	{
		SoftAssert SA = new SoftAssert();
		APP_LOGS.info("===========XP8_CallHistory_05============");
		launch_an_app("phone");
		validatePhoneAppLaunch(SA);
		validate_send_message(SA);
		clickBackButton(2);
		customWait(3000);
		navigateTocallHistory(SA);
		validate_send_message(SA);
		clickBackButton(2);
		customWait(3000);
		click_Missed_Call_Log(SA);
		validate_send_message(SA);
		clickBackButton(5);
		SA.assertAll();
	}

	@Test(priority=6,dataProvider="XP8_CallHistory", dataProviderClass=DataProviders.class)
	public void XP8_TC_06_CallHistory_validate_Contact_Details_In_Dail_Pad(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException
	{
		SoftAssert SA = new SoftAssert();
		APP_LOGS.info("===========XP8_CallHistory_06============");
		launch_an_app("phone");
		enterNumberInDialpad(pryNum, SA);
		minWait();
		clickBackButton(4);
		aDriver.pressKeyCode(AndroidKeyCode.HOME);
		SA.assertAll();
	}

	@Test(priority=7,dataProvider="XP8_CallHistory", dataProviderClass=DataProviders.class)
	public void XP8_TC_07_CallHistory_validate_Call_Block_In_Call_Logs(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException
	{
		SoftAssert SA = new SoftAssert();
		APP_LOGS.info("===========XP8_CallHistory_07============");
		launch_an_app("phone");
		navigateTocallHistory(SA);
		validateCallBlock(SA);			
		aDriver.pressKeyCode(AndroidKeyCode.HOME);
		SA.assertAll();		
	}

	@Test(priority=8,dataProvider="XP8_CallHistory", dataProviderClass=DataProviders.class)
	public void XP8_TC_08_CallHistory_validate_Copy_And_Edit_Number_In_Call_Details(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException 
	{
		SoftAssert SA = new SoftAssert();
		APP_LOGS.info("===========XP8_CallHistory_08============");
		launch_an_app("phone");
		navigateTocallHistory(SA);
		copyNumberFromCallHistoryOpt(SA);
		customWait(2000);
		validateEditNumberBeforeCall(SA);
		aDriver.pressKeyCode(AndroidKeyCode.HOME);
		SA.assertAll();
	}

	@Test(priority=9,dataProvider="XP8_CallHistory", dataProviderClass=DataProviders.class)
	public void XP8_TC_09_CallHistory_validate_Call_Log_Icons_Call_Details(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException 
	{
		SoftAssert SA = new SoftAssert();
		APP_LOGS.info("===========XP8_CallHistory_09============");
		launch_an_app("phone");
		navigateTocallHistory(SA);
		validate_specific_call_details(SA);
		clickBackButton(1);
		validateNumberOfMissedCalls(SA);
		aDriver.pressKeyCode(AndroidKeyCode.HOME);
		SA.assertAll();		
	}

	@Test(priority=10,dataProvider="XP8_CallHistory", dataProviderClass=DataProviders.class)
	public void XP8_TC_10_CallHistory_Validate_Add_New_Contact_Picture(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException 
	{
		SoftAssert SA = new SoftAssert();
		APP_LOGS.info("===========XP8_CallHistory_10============");
		launch_an_app("phone");
		add_picture_to_contact(SA);
		aDriver.pressKeyCode(AndroidKeyCode.HOME);
		SA.assertAll();
	}

	@Test(priority=11,dataProvider="XP8_CallHistory", dataProviderClass=DataProviders.class)
	public void XP8_TC_11_CallHistory_Recent_App_Key(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException
	{
		SoftAssert SA = new SoftAssert();
		APP_LOGS.info("===========XP8_CallHistory_11============");
		aDriver.pressKeyCode(AndroidKeyCode.HOME);
		minWait();
		make_Call_from_RefDev();
		customWait(7000);
		clickRecentAppKey(1);
		customWait(3000);
		endCall_RefDevice();
		aDriver.pressKeyCode(AndroidKeyCode.HOME);
		SA.assertAll();
	}

	@Test(priority=12,dataProvider="XP8_CallHistory", dataProviderClass=DataProviders.class)
	public void XP8_TC_12_CallHistory_validate_Clear_Call_History(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException
	{
		SoftAssert SA = new SoftAssert();
		APP_LOGS.info("===========XP8_CallHistory_12============");
		launch_an_app("phone");
		validateClearCallHistory(SA);
		aDriver.pressKeyCode(AndroidKeyCode.HOME);
		SA.assertAll();
	}

	@Test(priority=13,dataProvider="XP8_CallHistory", dataProviderClass=DataProviders.class)
	public void XP8_TC_13_CallHistory_validate_Recently_Updated_Call_log(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException, java.text.ParseException
	{
		SoftAssert SA = new SoftAssert();
		APP_LOGS.info("===========XP8_CallHistory_13============");
		validate_Device_Date_Time_Format(SA);
		test.log(LogStatus.INFO, "=======VALIDATING_ALL_CALL_LOG=======");
		launch_an_app("phone");
		validatePhoneAppLaunch(SA);
		navigateTocallHistory(SA);
		minWait();
		/*make_Call_from_RefDev();
		customWait(5000);
		endCall_RefDevice();
		customWait(2000);*/
		get_A_Missed_Call(SA);
		callDetailsOpt(SA);
		minWait();
		validate_Update_Recently_ALL_Call(SA);
		test.log(LogStatus.INFO, "=======VALIDATING_MISSED_CALL_LOG=======");
		clickBackButton(1);
		click_Missed_Call_Log(SA);
		/*make_Call_from_RefDev();
		customWait(5000);
		endCall_RefDevice();
		customWait(2000);*/
		get_A_Missed_Call(SA);
		callDetailsOpt(SA);
		minWait();
		validate_Update_Recently_MISSED_Call(SA);
		SA.assertAll();
	}

	@Test(priority=14,dataProvider="XP8_CallHistory", dataProviderClass=DataProviders.class)
	public void XP8_TC_14_CallHistory_Current_Device_And_Call_Log_Date_Time(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException, java.text.ParseException
	{
		SoftAssert SA = new SoftAssert();
		APP_LOGS.info("===========XP8_CallHistory_14============");
		launch_an_app("phone");
		validatePhoneAppLaunch(SA);
		navigateTocallHistory(SA);
		validateCallLogList(SA);
		callDetailsOpt(SA);
		currentDeviceAndCallLogDateTime(SA);
		SA.assertAll();
	}
}

