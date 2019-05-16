package com.xp8.test;

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
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

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
import com.xp8.util.Locators_PhoneDialer;
import com.xp8.util.Locators_PhoneDialer_old;
import com.xp8.util.Locators_XP8_CallHistory;
import com.xp8.util.XP8_CallHistory_Utils;

import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import net.sourceforge.tess4j.TesseractException;

public class XP8_CallHistory extends XP8_CallHistory_Utils {


	public ExcelReader excel;
	Properties properties;
	public static ExtentTestInterruptedException testexception;
	public int  itr =30;
	boolean value;

	@BeforeSuite
	public void beforeSuite() throws FileNotFoundException, InterruptedException,  IOException, ParseException, ParseException, ParseException, ParseException, ParseException, ParseException 
	{
		extent = new ExtentReports("src/test/resources/extentreport/XP8_CallHistory_TestReport.html", true); //Provide Desired Report Directory Location and Name
		//	extent.loadConfig(new File("src/test/resources/StorageFile/ReportsConfig.xml"));
		extent.loadConfig(new File(System.getProperty("user.dir")+"//ReportsConfig.xml"));
		extent.addSystemInfo("Build #",JsonFileReaderAndWriter.primaryDevFirmwareReader())
		.addSystemInfo("Product",JsonFileReaderAndWriter.primaryDevModelReader())
		.addSystemInfo("Operator", JsonFileReaderAndWriter.primaryDevOperatorReader());fetch_Devices_Details();	

	}

	@BeforeSuite
	public void numofTestCases() throws ClassNotFoundException {

		appiumService.TOTAL_NUM_OF_TESTCASES=GetMethods.TotalTestcase("XP8_Ca", this.getClass());


	}


	@BeforeMethod()
	public  void beforeMethod(Method method) 
	{
		test = extent.startTest( (this.getClass().getSimpleName() +" :: "+  method.getName()),method.getName()); //Test Case Start Here
		test.assignAuthor("JK"); //Test Script Author Name
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
	/*@Test(priority=1,dataProvider="XP8_CallHistory", dataProviderClass=DataProviders.class)
	public void XP8_CallHistory_01_Validate_Phone_Application_Launch(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException
	{
		APP_LOGS.info("===========XP8_CallHistory_01============");
		startAdbLog("XP8_CallHistory_01");
		launch_an_app("phone");
		validatePhoneAppLaunch();
		navigateTocallHistory();
		validateCallLogList();
		validateContactFromCallLog();
		clickBackButton(3);
		//System.out.println("Contact Details is Displayed");
		//validateCallFromCallLog();
		//System.out.println("Call was initiated");
	}

	@Test(priority=1,dataProvider="XP8_CallHistory", dataProviderClass=DataProviders.class)
	public void XP8_CallHistory_02_Validate_Call_History_Contact_Details(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException
	{
		APP_LOGS.info("===========XP8_CallHistory_02============");
		startAdbLog("XP8_CallHistory_02");
		validateCreateNewContactFromCallLog("UI Testing");
		//clickBackButton(1);
		minWait();
	}

	@Test(priority=1,dataProvider="XP8_CallHistory", dataProviderClass=DataProviders.class)
	public void XP8_CallHistory_03_Missed_Call_Log_List(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException
	{
		APP_LOGS.info("===========XP8_CallHistory_03============");
		//minWait();
		startAdbLog("XP8_CallHistory_03");
		launch_an_app("phone");
		navigateTocallHistory();
		validate_Missed_Call_Log();

		//validateCallLogList();
		//validateContactFromCallLog();

	}

	@Test(priority=1,dataProvider="XP8_CallHistory", dataProviderClass=DataProviders.class)
	public void XP8_CallHistory_04_Validate_Missed_Call_Contact_Details(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException
	{
		APP_LOGS.info("===========XP8_CallHistory_04============");
		//minWait();
		startAdbLog("XP8_CallHistory_04");
		launch_an_app("phone");
		navigateTocallHistory();
		validate_Missed_Call_Log();
		validate_Call_From_Missed_Call_Log();

	}*/

	/*@Test(priority=1,dataProvider="XP8_CallHistory", dataProviderClass=DataProviders.class)
	public void XP8_CallHistory_05_Validate_Call_From_Missed_Call_Log(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException
	{
		APP_LOGS.info("===========XP8_CallHistory_05============");
		//minWait();
		startAdbLog("XP8_CallHistory_05");
		launch_an_app("phone");
		navigateTocallHistory();
		validate_Missed_Call_Log();
		validate_Call_From_Missed_Call_Log();

	}*/

	/*@Test(priority=1,dataProvider="XP8_CallHistory", dataProviderClass=DataProviders.class)
	public void XP8_CallHistory_06_Validate_Contact_Details_From_Missed_Call_List(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException
	{
		APP_LOGS.info("===========XP8_CallHistory_06============");
		//minWait();
		startAdbLog("XP8_CallHistory_06");
		launch_an_app("phone");
		navigateTocallHistory();
		validate_Missed_Call_Log();
		validateContactFromCallLog();
		clickBackButton(4);
	}*/

	/*@Test(priority=1,dataProvider="XP8_CallHistory", dataProviderClass=DataProviders.class)
	public void XP8_CallHistory_07_Save_Unsaved_Contacts_From_Missed_Call_Log(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException
	{
		APP_LOGS.info("===========XP8_CallHistory_07============");
		//minWait();
		startAdbLog("XP8_CallHistory_07");
		launch_an_app("phone");
		navigateTocallHistory();
		validate_Missed_Call_Log();
		minWait();
		validateCreateNewContactFromCallLog("UI Testing");
	}*/

	/*@Test(priority=1,dataProvider="XP8_CallHistory", dataProviderClass=DataProviders.class)
	public void XP8_CallHistory_08_Make_Missed_Call_Contact_As_Favorite(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException
	{
		APP_LOGS.info("===========XP8_CallHistory_08============");
		//minWait();
		startAdbLog("XP8_CallHistory_08");
		launch_an_app("phone");
		navigateTocallHistory();
		validate_Missed_Call_Log();
		minWait();
		validateContactFromCallLog();
		validate_frequent_list();
	}*/
	/*
	@Test(priority=1,dataProvider="XP8_CallHistory", dataProviderClass=DataProviders.class)
	public void XP8_CallHistory_09_Validate_Send_Message(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException
	{
		APP_LOGS.info("===========XP8_CallHistory_09============");
		//minWait();
		startAdbLog("XP8_CallHistory_09");
		launch_an_app("phone");
		validate_send_message("Test message1 ");
		clickBackButton(2);
		customWait(5000);
		navigateTocallHistory();
		validate_send_message("Test message2 ");
		clickBackButton(2);
		customWait(7000);
		validate_Missed_Call_Log();
		validate_send_message("Test message3 ");
		clickBackButton(5);
	}*/

	/*@Test(priority=1,dataProvider="XP8_CallHistory", dataProviderClass=DataProviders.class)
	public void XP8_CallHistory_10_Validate_Call_Logs(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException
	{
		APP_LOGS.info("===========XP8_CallHistory_10============");
		//minWait();
		startAdbLog("XP8_CallHistory_10");
		launch_an_app("phone");
		//navigateTocallHistory();
		validateCallLogList();
		clickBackButton(3);
		validateCallFromCallLog();
	}*/

	/*@Test(priority=1,dataProvider="XP8_CallHistory", dataProviderClass=DataProviders.class)
	public void XP8_CallHistory_11_validate_Contact_Details_In_Dail_Pad(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException
	{
		APP_LOGS.info("===========XP8_CallHistory_11============");
		//minWait();
		startAdbLog("XP8_CallHistory_11");
		launch_an_app("phone");
		enterNumberInDialpad("789654321");
	}*/
	/*
	@Test(priority=1,dataProvider="XP8_CallHistory", dataProviderClass=DataProviders.class)
	public void XP8_CallHistory_12_validate_Clear_Call_History(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException
	{
		APP_LOGS.info("===========XP8_CallHistory_12============");
		//minWait();
		startAdbLog("XP8_CallHistory_12");
		launch_an_app("phone");
		validateClearCallHistory();
	}*/

	/*@Test(priority=1,dataProvider="XP8_CallHistory", dataProviderClass=DataProviders.class)
	public void XP8_CallHistory_13_validate_Permission_Popup_In_Call_Logs(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException
	{
		APP_LOGS.info("===========XP8_CallHistory_13============");
		startAdbLog("XP8_CallHistory_13");
		//launch_an_app("phone");
		validate_Permission_Popup_In_Call_Logs();
		//clickBackButton(1);		
	}*/

	@Test(priority=1,dataProvider="XP8_CallHistory", dataProviderClass=DataProviders.class)
	public void XP8_TC_014_CallHistory_validate_Call_Block_In_Call_Logs(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException
	{
		APP_LOGS.info("===========XP8_CallHistory_14============");
		//startAdbLog("XP8_CallHistory_14");
		launch_an_app("phone");
		navigateTocallHistory();
		validateCallBlock();
	}

	@Test(priority=1,dataProvider="XP8_CallHistory", dataProviderClass=DataProviders.class)
	public void XP8_TC_015_CallHistory_validate_Copy_And_Edit_Number_In_Call_Details(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException, TesseractException
	{
		APP_LOGS.info("===========XP8_CallHistory_15============");
		copyNumberFromCallHistoryOpt();
		customWait(7000);
		validateEditNumberBeforeCall();
	}
}
