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
import org.testng.asserts.SoftAssert;

import com.graphics.gui.JsonFileReaderAndWriter;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTestInterruptedException;
import com.relevantcodes.extentreports.LogStatus;
import com.xp5S.util.BaseUtil;
import com.xp5S.util.GetMethods;
import com.xp5S.util.appiumService;
import com.xp8.util.DataProviders;
import com.xp8.util.ExcelConstants;
import com.xp8.util.ExcelReader;
import com.xp8.util.Locators_BaseUtil;
import com.xp8.util.Locators_DeviceStability;
import com.xp8.util.Locators_PhoneDialer;
import com.xp8.util.Locators_PhoneDialer_old;
import com.xp8.util.Locators_SMS_DeviceStability;
import com.xp8.util.XP8_PhoneDialer_util;


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
	
		extent.loadConfig(new File(System.getProperty("user.dir")+"//ReportsConfig.xml"));
		extent.addSystemInfo("Build #",JsonFileReaderAndWriter.primaryDevFirmwareReader())
		.addSystemInfo("Product",JsonFileReaderAndWriter.primaryDevModelReader())
		.addSystemInfo("Operator", JsonFileReaderAndWriter.primaryDevOperatorReader());		
		fetch_Devices_Details();	
		
		try {
			appiumService.TOTAL_NUM_OF_TESTCASES=GetMethods.TotalTestcase("XP8_PhoneDialer", this.getClass());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	

	@BeforeMethod()
	public  void beforeMethod(Method method) 
	{
		test = extent.startTest( (this.getClass().getSimpleName() +" :: "+  method.getName()),method.getName()); //Test Case Start Here
		test.assignAuthor(""); //Test Script Author Name
		String BuildNumber=BaseUtil.fetchDeviceProperty("adb shell getprop ro.build.id");
		test.assignCategory("Build #:"+BuildNumber);
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
		SoftAssert sa=new SoftAssert();
		APP_LOGS.info("===========XP8_PhoneDialer_001_Validate_Phone_Application_Launch============");
		startAdbLog("XP8_PhoneDialer_001");
		if (p_b_No.contains("-10.")||p_b_No.contains("-00.")||p_b_No.contains("-15.")||p_b_No.contains("-11.")||p_b_No.contains("-29.")||p_b_No.contains("-12.")||p_b_No.contains("-26.")) {
		clearRecentApps();
		clearcallhistory();
		launch_an_app("contacts");
		setDefaultSavingAccount();
		clearRecentApps();
		deleteIfContactsPresent(sa);
		validatePhoneAppLaunch(sa);
		}
		sa.assertAll();
	}

	@Test(priority=2,dataProvider="XP8_PhoneDialer", dataProviderClass=DataProviders.class)
	public void XP8_PhoneDialer_002_Validate_Phone_App_Home_Page_Without_call_Log(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException
	{
		SoftAssert sa=new SoftAssert();
		APP_LOGS.info("===========XP8_PhoneDialer_002_Validate_Phone_App_Home_Page_Without_call_Log============");
		startAdbLog("XP8_PhoneDialer_002");
		if (p_b_No.contains("-10.")||p_b_No.contains("-00.")||p_b_No.contains("-15.")||p_b_No.contains("-11.")||p_b_No.contains("-29.")||p_b_No.contains("-12.")||p_b_No.contains("-26.")) {
		launch_an_app("phone");
		validatePhoneAppHomePage(sa);
		}
		sa.assertAll();
	}

	@Test(priority=3,dataProvider="XP8_PhoneDialer", dataProviderClass=DataProviders.class)
	public void XP8_PhoneDialer_003_Validate_Make_A_Call(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException
	{
		SoftAssert sa=new SoftAssert();
		APP_LOGS.info("===========XP8_PhoneDialer_003_Validate_Make_A_Call============");
		startAdbLog("XP8_PhoneDialer_003");	
		if (p_b_No.contains("-10.")||p_b_No.contains("-00.")||p_b_No.contains("-15.")||p_b_No.contains("-11.")||p_b_No.contains("-12.")||p_b_No.contains("-26.")) {
		clearRecentApps();
		clearcallhistory();
		launch_an_app("phone");
         validateMakeACall();
		reciveCallInRefDevice(refNum);
		customWait(2000);
		endCall_RefDevice();
		clickBackButton(1);
		validateCallFromCallLog(sa);
		clickBackButton(1);
		}
		else if(p_b_No.contains("-29.")){
			clearRecentApps();
			clearcallhistory();
			launch_an_app("phone");
			validateMakeACall_sprint();
			reciveCallInRefDevice(refNum);
			customWait(2000);
			endCall_RefDevice();
			clickBackButton(1);
			validateCallFromCallLog(sa);
			clickBackButton(1);
			
		}
		sa.assertAll();
	}

	@Test(priority=4,dataProvider="XP8_PhoneDialer", dataProviderClass=DataProviders.class)
	public void XP8_PhoneDialer_004_Save_contact_from_phone_dialer(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException
	{
		SoftAssert sa=new SoftAssert();
		APP_LOGS.info("===========XP8_PhoneDialer_004_Save_contact_from_phone_dialer============");
		startAdbLog("XP8_PhoneDialer_004");
		if (p_b_No.contains("-10.")||p_b_No.contains("-00.")||p_b_No.contains("-15.")||p_b_No.contains("-11.")||p_b_No.contains("-29.")||p_b_No.contains("-12.")||p_b_No.contains("-26.")) {
		clearRecentApps();
		launch_an_app("phone");
		validateCreateNewContactFromCallLog(data.get("name"));
	    CreateNewContactAndSave(data.get("name"));
		customWait(2000);
		validateSavedContact(data.get("name"),data.get("location"),sa);
		minWait();
		clickBackButton(1);
		minWait();
		validateCreateNewContactFromDialpad(data.get("phone"),data.get("test"));
		validate_number_saved_with_plusSymbol(sa);
		minWait();
		validate_contacts_added_in_list(sa);
		}
		sa.assertAll();
	}

	@Test(priority=5,dataProvider="XP8_PhoneDialer", dataProviderClass=DataProviders.class)
	public void XP8_PhoneDialer_005_Validate_favourite_or_frequent_contact_list(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException
	{
		SoftAssert sa=new SoftAssert();
		APP_LOGS.info("===========XP8_PhoneDialer_005_Validate_favourite_or_frequently_contact_list============");
		startAdbLog("XP8_PhoneDialer_005");	
		if (p_b_No.contains("-10.")||p_b_No.contains("-00.")||p_b_No.contains("-15.")||p_b_No.contains("-11.")||p_b_No.contains("-29.")||p_b_No.contains("-12.")||p_b_No.contains("-26.")) {
		launch_an_app("phone");
		validate_frequent_list();
		minWait();
		validate_Fav_contacts_added_in_list(sa);
		minWait();
		validate_clear_frequents(sa);
		}
		sa.assertAll();
	}

	@Test(priority=6,dataProvider="XP8_PhoneDialer", dataProviderClass=DataProviders.class)
	public void XP8_PhoneDialer_006_Validate_Search_option(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException
	{
		SoftAssert sa=new SoftAssert();
		APP_LOGS.info("===========XP8_PhoneDialer_006_Validate_Search_option============");
		startAdbLog("XP8_PhoneDialer_006");	
		if (p_b_No.contains("-10.")||p_b_No.contains("-00.")||p_b_No.contains("-15.")||p_b_No.contains("-11.")||p_b_No.contains("-29.")||p_b_No.contains("-12.")||p_b_No.contains("-26.")) {
		launch_an_app("phone");
		validate_search(data.get("contactName"),sa);
		validate_recent_contact_in_list(data.get("contactName"),sa);
		}
		sa.assertAll();
	}
	
	@Test(priority=7,dataProvider="XP8_PhoneDialer", dataProviderClass=DataProviders.class)
	public void XP8_PhoneDialer_007_Validate_Send_SMS_from_recent_call_Log(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException
	{
		SoftAssert sa=new SoftAssert();
		APP_LOGS.info("===========XP8_PhoneDialer_007_Validate_Send_SMS_from_recent_call_Log============");
		startAdbLog("XP8_PhoneDialer_007");	
		if (p_b_No.contains("-10.")||p_b_No.contains("-00.")||p_b_No.contains("-11.")||p_b_No.contains("-12.")) {
		launch_an_app("phone");
		validate_send_message(data.get("textMessage"),sa);
		System.out.println("message sent");
		}
		else if(p_b_No.contains("-29.")||p_b_No.contains("-26.")){
			launch_an_app("phone");
			validate_send_message_sprint(data.get("textMessage"),sa);
			System.out.println("message sent");
		}
		else if(p_b_No.contains("-15.")){
			
			validate_send_message_verizon(data.get("textMessage"), sa);
			System.out.println("message sent");
			
		}
		sa.assertAll();
	}
	
	@Test(priority=8,dataProvider="XP8_PhoneDialer", dataProviderClass=DataProviders.class)
	public void XP8_PhoneDialer_008_Validate_Add_New_Contact(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException
	{
		SoftAssert sa=new SoftAssert();
		APP_LOGS.info("===========XP8_PhoneDialer_008_Validate_Add_New_Contact============");
		startAdbLog("XP8_PhoneDialer_008");	
		if (p_b_No.contains("-10.")||p_b_No.contains("-00.")||p_b_No.contains("-15.")||p_b_No.contains("-11.")||p_b_No.contains("-29.")||p_b_No.contains("-12.")||p_b_No.contains("-26.")) {
		clearRecentApps();
		launch_an_app("settings");
		remove_GoogleAcccount();
		launch_an_app("phone");    
		validate_add_new_contact(data.get("phone"),data.get("name"));
		//validateSavedContact(data.get("name"),"",sa);
		clickBackButton(2);
		validate_link_contact(sa);
		validate_unlink_contact(sa);
		System.out.println("take photo method is started");
		launch_an_app("phone"); 
		validate_take_photo(sa);
		minWait();
		validate_remove_photo(sa);
		minWait();
		validate_Choose_photo(sa);
		clearRecentApps();
		launch_an_app("phone");
		validateKeepEditingAndDiscard(data.get("number"));
		KeepEditingAndDiscard(Locators_PhoneDialer.CancelOpt, Locators_PhoneDialer.nameEditFld,sa);
		minWait();
		clickBackButton(1);
		minWait();
		KeepEditingAndDiscard(Locators_PhoneDialer.discardBtn, Locators_PhoneDialer.dialpadEditFld,sa);
		minWait();
		clickBackButton(2);
		}
		sa.assertAll();
	}
	
	@Test(priority=9,dataProvider="XP8_PhoneDialer", dataProviderClass=DataProviders.class)
	public void XP8_PhoneDialer_009_Validate_Call_blocking_and_unblocking_functionality(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException
	{
		SoftAssert sa=new SoftAssert();
		APP_LOGS.info("===========XP8_PhoneDialer_009_Validate_Call_blocking_and_unblocking_functionality============");
		startAdbLog("XP8_PhoneDialer_009");
		if (p_b_No.contains("-10.")||p_b_No.contains("-00.")||p_b_No.contains("-15.")||p_b_No.contains("-11.")||p_b_No.contains("-29.")||p_b_No.contains("-12.")||p_b_No.contains("-26.")) {
		clearRecentApps();
		launch_an_app("phone");
		validate_block_option_in_call_log(sa);	
		navigate_to_block_number_in_call_settings();
		blockNumberAndValidate(sa);
		launch_an_app("phone");
		unblockNumberAndValidate(sa);
		clickBackButton(4);
		}
		sa.assertAll();
	}
	
	
	
	
	
}
