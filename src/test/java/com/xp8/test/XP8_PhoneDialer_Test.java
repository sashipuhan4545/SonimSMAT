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
import com.xp8.util.Locators_DeviceStability;
import com.xp8.util.Locators_PhoneDialer;
import com.xp8.util.Locators_PhoneDialer_old;
import com.xp8.util.XP8_PhoneDialer_util;

import SikuliHelper.SikuliConstants;
import SikuliHelper.SikuliHelper;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class XP8_PhoneDialer_Test extends XP8_PhoneDialer_util{

	public ExcelReader excel;
	Properties properties;
	public static ExtentTestInterruptedException testexception;
	public int  itr =30;
	boolean value;

	@BeforeSuite
	public void beforeSuite() throws FileNotFoundException, InterruptedException,  IOException, ParseException, ParseException, ParseException, ParseException, ParseException, ParseException 
	{
		extent = new ExtentReports("src/test/resources/extentreport/XP8_PhoneDialer_TestReport.html", true); //Provide Desired Report Directory Location and Name
	//	extent.loadConfig(new File("src/test/resources/StorageFile/ReportsConfig.xml"));
		extent.loadConfig(new File(System.getProperty("user.dir")+"//ReportsConfig.xml"));
		extent.addSystemInfo("Build #",JsonFileReaderAndWriter.primaryDevFirmwareReader())
		.addSystemInfo("Product",JsonFileReaderAndWriter.primaryDevModelReader())
		.addSystemInfo("Operator", JsonFileReaderAndWriter.primaryDevOperatorReader());		fetch_Devices_Details();	

	}

	@BeforeSuite
	public void numofTestCases() throws ClassNotFoundException {


		//int numberOfTestCases = GetMethods.TotalTestcase("XP8_TC", this.getClass());

		appiumService.TOTAL_NUM_OF_TESTCASES=GetMethods.TotalTestcase("XP8_TC", this.getClass());


	}


	@BeforeMethod()
	public  void beforeMethod(Method method) 
	{
		test = extent.startTest( (this.getClass().getSimpleName() +" :: "+  method.getName()),method.getName()); //Test Case Start Here
		test.assignAuthor("Farheen Taj"); //Test Script Author Name
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
		Locators_PhoneDialer loc=new Locators_PhoneDialer(aDriver);
		Locators_BaseUtil loc1 = new Locators_BaseUtil(aDriver);	
		PageFactory.initElements(new AppiumFieldDecorator(aDriver),loc);
		PageFactory.initElements(new AppiumFieldDecorator(aDriver),loc1);
		excel=new ExcelReader(ExcelConstants.XP5S_XL_PATH);	
	}

//=====================================================Test cases===========================================================================
	@Test(priority=1,dataProvider="XP8_PhoneDialer", dataProviderClass=DataProviders.class)
	public void XP8_PhoneDialer_001_Validate_Phone_Application_Launch(Hashtable<String, String> data) throws InterruptedException, AWTException,  IOException, ParseException
	{
		APP_LOGS.info("===========XP8_PhoneDialer_001_Validate_Phone_Application_Launch============");
		startAdbLog("XP8_PhoneDialer_001");
		launch_an_app("phone");
		beforeExecution();
		validatePhoneAppLaunch();
	}

	@Test(priority=2,dataProvider="XP8_PhoneDialer", dataProviderClass=DataProviders.class)
	public void XP8_PhoneDialer_002_Validate_Phone_App_Home_Page_Without_call_Log(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException
	{
		APP_LOGS.info("===========XP8_PhoneDialer_002_Validate_Phone_App_Home_Page_Without_call_Log============");
		startAdbLog("XP8_PhoneDialer_002");
		launch_an_app("phone");
		validatePhoneAppHomePage();
	}

	@Test(priority=3,dataProvider="XP8_PhoneDialer", dataProviderClass=DataProviders.class)
	public void XP8_PhoneDialer_003_Validate_Make_A_Call(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException
	{
		APP_LOGS.info("===========XP8_PhoneDialer_003_Validate_Make_A_Call============");
		startAdbLog("XP8_PhoneDialer_003");	
		launch_an_app("phone");
		validateMakeACall();
		clickBackButton(1);
		validateCallFromCallLog();
		clickBackButton(1);
	}

	@Test(priority=4,dataProvider="XP8_PhoneDialer", dataProviderClass=DataProviders.class)
	public void XP8_PhoneDialer_004_Save_contact_from_phone_dialer(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException
	{
		APP_LOGS.info("===========XP8_PhoneDialer_004_Save_contact_from_phone_dialer============");
		startAdbLog("XP8_PhoneDialer_004");	
		launch_an_app("phone");
		validateCreateNewContactFromCallLog(data.get("name"));
		minWait();
		validateCreateNewContactFromDialpad(data.get("phone"),data.get("test"));
		minWait();
		validate_number_saved_with_plusSymbol();
		minWait();
		validate_contacts_added_in_list();
	}

	@Test(priority=5,dataProvider="XP8_PhoneDialer", dataProviderClass=DataProviders.class)
	public void XP8_PhoneDialer_005_Validate_favourite_or_frequent_contact_list(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException
	{
		APP_LOGS.info("===========XP8_PhoneDialer_005_Validate_favourite_or_frequently_contact_list============");
		startAdbLog("XP8_PhoneDialer_005");	
		launch_an_app("phone");
		validate_frequent_list();
		minWait();
		validate_clear_frequents();
	}

	@Test(priority=6,dataProvider="XP8_PhoneDialer", dataProviderClass=DataProviders.class)
	public void XP8_PhoneDialer_006_Validate_Search_option(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException
	{
		APP_LOGS.info("===========XP8_PhoneDialer_006_Validate_Search_option============");
		startAdbLog("XP8_PhoneDialer_006");	
		launch_an_app("phone");
		validate_search(data.get("contactName"));
		validate_recent_contact_in_list(data.get("contactName"));
	}
	
	@Test(priority=7,dataProvider="XP8_PhoneDialer", dataProviderClass=DataProviders.class)
	public void XP8_PhoneDialer_007_Validate_Send_SMS_from_recent_call_Log(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException
	{
		APP_LOGS.info("===========XP8_PhoneDialer_007_Validate_Send_SMS_from_recent_call_Log============");
		startAdbLog("XP8_PhoneDialer_007");	
		launch_an_app("phone");
		validate_send_message(data.get("textMessage"));
	}
	
	@Test(priority=8,dataProvider="XP8_PhoneDialer", dataProviderClass=DataProviders.class)
	public void XP8_PhoneDialer_008_Validate_Add_New_Contact(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException
	{
		APP_LOGS.info("===========XP8_PhoneDialer_008_Validate_Add_New_Contact============");
		startAdbLog("XP8_PhoneDialer_008");	
		launch_an_app("settings");
		remove_GoogleAcccount();
		launch_an_app("phone");
		validate_add_new_contact(data.get("phone"),data.get("name"));
		validate_link_contact();
		add_picture_to_contact();
		validateKeepEditingAndDiscard(data.get("number"));
	}
	
	@Test(priority=9,dataProvider="XP8_PhoneDialer", dataProviderClass=DataProviders.class)
	public void XP8_PhoneDialer_009_Validate_Call_blocking_and_unblocking_functionality(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException
	{
		APP_LOGS.info("===========XP8_PhoneDialer_009_Validate_Call_blocking_and_unblocking_functionality============");
		startAdbLog("XP8_PhoneDialer_009");	
		launch_an_app("phone");
		validate_block_option_in_call_log();	
		navigate_to_block_number_in_call_settings();
		blockNumberAndValidate();
		unblockNumberAndValidate();
		clickBackButton(3);
	}
	
	
	
	
	
}
