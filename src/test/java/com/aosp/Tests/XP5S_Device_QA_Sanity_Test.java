package com.aosp.Tests;

import java.awt.AWTException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException ;
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

import com.aosp.Utils.AOSP_XP5S_Sanity_Util;
import com.aosp.Utils.DataProviders;
import com.aosp.Utils.Locators_Sanity;
import com.graphics.gui.JsonFileReaderAndWriter;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTestInterruptedException;
import com.relevantcodes.extentreports.LogStatus;


import com.xp5S.util.ExcelConstants;
import com.xp5S.util.ExcelReader;
import com.xp5S.util.GetMethods;
import com.xp5S.util.Locators_BaseUtil;
import com.xp5S.util.appiumService;

import application.AllQA;
import application.SalesTeam;
import io.appium.java_client.android.AndroidKeyCode;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class XP5S_Device_QA_Sanity_Test extends AOSP_XP5S_Sanity_Util{

	public ExcelReader excel;
	Properties properties;
	public static ExtentTestInterruptedException testexception;


	 @BeforeClass
	 public void copyFilesToDevice() throws IOException, ParseException  {
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
	 
	
	 
	@BeforeSuite
	public void beforeSuite() throws IOException, ParseException , InterruptedException, ParseException 
	{
		extent = new ExtentReports("src/test/resources/extentreport/XP5S_Device_QA_Sanity_TestReport.html", true); 
		fetch_Devices_Details();
		
		try {
			appiumService.TOTAL_NUM_OF_TESTCASES=GetMethods.TotalTestcase("XP5S_TC", this.getClass());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}

	@BeforeMethod()
	public  void beforeMethod(Method method) throws InterruptedException, FileNotFoundException, IOException, ParseException 
	{

		test = extent.startTest( (this.getClass().getSimpleName() +" :: "+  method.getName()),method.getName()); //Test Case Start Here
		test.assignAuthor(""); //Test Script Author Name
		extent.loadConfig(new File("src/test/resources/StorageFile/ReportsConfig.xml"));
		extent.addSystemInfo("Build #",JsonFileReaderAndWriter.primaryDevFirmwareReader())
		.addSystemInfo("Product",JsonFileReaderAndWriter.primaryDevModelReader())
		.addSystemInfo("Operator", JsonFileReaderAndWriter.primaryDevOperatorReader());

	}


	@AfterMethod()
	public void tearDown(ITestResult result,Method method) throws IOException, ParseException , InterruptedException
	{
		if(result.getStatus()==ITestResult.FAILURE)
		{			

			String screenshot_path=captureScreenshot(method.getName());
			String image= test.addScreenCapture(screenshot_path);
			test.log(LogStatus.FAIL,result.getThrowable());
			test.log(LogStatus.FAIL, "screenshot for failure:" +test.addScreenCapture(image));
			clearRecentApps();

		}
		extent.endTest(test);
		extent.flush();
	}


	@BeforeTest
	public void setupSys() throws InterruptedException, AWTException, IOException, ParseException , ParseException{
		properties=loadDriverAndProperties();
		Locators_Sanity loc=new Locators_Sanity(aDriver);
		Locators_BaseUtil loc1 =new Locators_BaseUtil(aDriver);
		PageFactory.initElements(new AppiumFieldDecorator(getDriver()),loc1);
		PageFactory.initElements(new AppiumFieldDecorator(getDriver()),loc);
		excel=new ExcelReader(ExcelConstants.XP5S_XL_PATH);
		startAdbLog("XP5S_Sanity_All");
	}

	//================================================Test cases==============================================
	@Test(priority=1,dataProvider="XP5_AOSP_Sanity", dataProviderClass=DataProviders.class)
	public void XP5S_TC_001_Sanity_LaunchApp_Menu_and_LaunchAllApp_from_ApplicationMenu(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException , ParseException {

		APP_LOGS.info("=========== XP5S_TC_001_Sanity_Validate_All_Apps_Launch============");
		startAdbLog("XP5S_Sanity_001");
		recordVideo("XP5S_Sanity_001");	
		clearRecentApps();
		customWait(500);
		validateAppMenuLaunch();	
		customWait(500);
		launch_an_app("settings");
		enableData();
		validateLaunch_All_Applications();		
		customWait(3000);
		test.log(LogStatus.PASS, "Test case status is Passed");
	}


	@Test(priority=2,dataProvider="XP5_AOSP_Sanity", dataProviderClass=DataProviders.class)
	public void XP5S_TC_002_Sanity_AboutPhone_Build_and_Baseband_Version(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException  {

		APP_LOGS.info("=========== XP5S_TC_002_Sanity_Validate_AboutPhone============");
		startAdbLog("XP5S_Sanity_002");
		recordVideo("XP5S_Sanity_002");		
		customWait(3000);
		launch_an_app("settings");
		navigateToSettingsFeature(Locators_Sanity.AboutPhone_feature,"About phone");	 
		navigateToOptions(Locators_Sanity.BasebandVersion,"Baseband version"); 
		navigateToOptions(Locators_Sanity.BuildNumber,"Build number"); 
		validateAboutPhone();
		test.log(LogStatus.PASS, "Test case status is Passed");
	}

	@Test(priority=3,dataProvider="XP5_AOSP_Sanity", dataProviderClass=DataProviders.class)
	public void XP5S_TC_003_Sanity_Check_NetworkType(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException , ParseException {

		APP_LOGS.info("=========== XP5S_TC_003_DevSanity_NetworkType============");
		startAdbLog("XP5S_Dev_Sanity_003");
		recordVideo("XP5S_Dev_Sanity_003");
		customWait(500);
		launch_an_app("settings");
		minWait();
		clickBtn(Locators_Sanity.wifi);
		customWait(2000);
		disableFeature(Locators_Sanity.enabled,Locators_Sanity.disabled);
		customWait(3000);		

		if(p_b_No.contains("-10.")||p_b_No.contains("-26.")) {

			launch_an_app("settings");
			navigateToSettingsFeature(Locators_Sanity.AboutPhone,"About phone");	
			validateLTESelection("LTE");
			
		}		
		else if(p_b_No.contains("-30.")) {
			navigateTo_NetworkSettings();
			changePreferedNetworkType_Reskin();
		}		
		else if(p_b_No.contains("-29.")) {
			navigateTo_NetworkSettings();
			changePreferedNetworkType_Sprint();
		}
		else {			
			navigateTo_NetworkSettings();
			changePreferedNetworkType();			
		}		
		test.log(LogStatus.PASS, "Test case status is Passed");
	}

	@Test(priority=4,dataProvider="XP5_AOSP_Sanity", dataProviderClass=DataProviders.class)
	public void XP5S_TC_004_Sanity_Diplaying_OperaterName_HomeScreen(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException , ParseException {


		APP_LOGS.info("=========== XP5S_TC_004_Sanity_OperaterName_HomeScreen============");
		startAdbLog("XP5S_Sanity_004");
		recordVideo("XP5S_Sanity_004");	
		aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_ENDCALL);
		minWait();
		displayOperatorName();
	}


	@Test(priority=5,dataProvider="XP5_AOSP_Sanity", dataProviderClass=DataProviders.class)
	public void XP5S_TC_005_Sanity_Make_MO_VoiceCall(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException , ParseException {

		APP_LOGS.info("=========== XP5S_TC_005_Sanity_Make_MO_VoiceCall============");
		startAdbLog("XP5S_Sanity_005");
		recordVideo("XP5S_Sanity_005");		
		customWait(3000);
		dailCallUsingDailPad(refNum);
		customWait(3000);
		endcall();		
		validate_Num_In_CallLog(refNum,"Outgoing");
		test.log(LogStatus.PASS, "Test case status is Passed");
	}

	@Test(priority=6,dataProvider="XP5_AOSP_Sanity", dataProviderClass=DataProviders.class)
	public void XP5S_TC_006_Sanity_Send_SMS_Ongoing_VoiceCall(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException , ParseException {


		APP_LOGS.info("=========== XP5S_TC_006_Sanity_Send_SMS_Ongoing_VoiceCall============");
		startAdbLog("XP5S_Sanity_006");
		recordVideo("XP5S_Sanity_006");		
		customWait(3000);
		launch_an_app("phone");
		dailCallUsingDailPad(refNum);
		customWait(1000);
		launch_an_app("messaging");
		composeMsg(data.get("composeMessage"),refNum);
		send() ;
		customWait(2000);
		OCR.Read_File.takeScreenShotForOcr("SMS_Sent");
		Thread.sleep(2000);
		OCR.my_main.validate_Using_OCR("SMS_Sent.png");
		validateMsgSent("Sent SMS");
		customWait(1000);
		endCall_RefDevice();	
		test.log(LogStatus.PASS, "Test case status is Passed");
	}


	@Test(priority=7,dataProvider="XP5_AOSP_Sanity", dataProviderClass=DataProviders.class)
	public void XP5S_TC_007_Sanity_Hold_and_Retreive_Call(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException , ParseException {

		APP_LOGS.info("=========== XP5S_TC_007_Sanity_Hold_and_Retreive_Call============");
		startLog("XP5S_Sanity_007");
		recordVideo("XP5S_Sanity_007");	
		customWait(1000);
		dailCallUsingDailPad(refNum);
		holdFunctionStatus();
		customWait(5000);
		endCall_RefDevice();	
		test.log(LogStatus.PASS, "Test case status is Passed");
	}

	@Test(priority=8,dataProvider="XP5_AOSP_Sanity", dataProviderClass=DataProviders.class)
	public void XP5S_TC_008_Sanity_In_Call_functionality_Mute_Speaker_ON_OFF(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException , ParseException {

		APP_LOGS.info("=========== XP5S_TC_008_Sanity_In_Call_functionality_Mute_Speaker_ON_OFF============");
		startLog("XP5S_Sanity_008");
		recordVideo("XP5S_Sanity_008");				
		launch_an_app("phone");
		inCallFunctionality();
		test.log(LogStatus.PASS, "Test case status is Passed");		
	}

	@Test(priority=9,dataProvider="XP5_AOSP_Sanity", dataProviderClass=DataProviders.class)
	public void XP5S_TC_009_Sanity_Make_MT_VoiceCall(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException , ParseException {

		APP_LOGS.info("=========== XP5S_TC_009_Sanity_Make_MT_VoiceCall============");
		startAdbLog("XP5S_Sanity_009");
		recordVideo("XP5S_Sanity_009");	
		endCall_RefDevice();
		make_Call_from_RefDev();
		customWait(3000);
		recieve_Call_PrimaryDev();
		customWait(5000);
		endCall_RefDevice();
		customWait(1000);
		validate_Num_In_CallLog(refNum,"Receive");
		test.log(LogStatus.PASS, "Test case status is Passed");
	}

	@Test(priority=10,dataProvider="XP5_AOSP_Sanity", dataProviderClass=DataProviders.class)
	public void XP5S_TC_010_Sanity_Reject_MT_Voice_Call(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException , ParseException {

		APP_LOGS.info("=========== XP5S_TC_010_Sanity_Reject_MT_Voice_Call============");
		startAdbLog("XP5S_Sanity_010");
		recordVideo("XP5S_Sanity_010");
		launch_an_app("phone");
		make_Call_from_RefDev();
		customWait(10000);
		endCall_PIDDevice();
		customWait(5000);
		endCall_RefDevice();
		customWait(1000);
		validate_Num_In_CallLog(refNum,"Reject");
		test.log(LogStatus.PASS, "Test case status is Passed");
	}

	@Test(priority=11,dataProvider="XP5_AOSP_Sanity", dataProviderClass=DataProviders.class)
	public void XP5S_TC_011_Sanity_MT_Voice_call_Reject_with_SMS_Notification(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException  {

		APP_LOGS.info("=========== XP5S_TC_011_Sanity_MT_Voice_call_Reject_with_SMS_Notification============");
		startAdbLog("XP5S_Sanity_011");
		recordVideo("XP5S_Sanity_011");
		launch_an_app("phone");
		make_Call_from_RefDev();
		rejectWithSMS();
		test.log(LogStatus.PASS, "Test case status is Passed");
	}

	@Test(priority=12,dataProvider="XP5_AOSP_Sanity", dataProviderClass=DataProviders.class)
	public void XP5S_TC_012_Sanity_MO_VoiceCall_from_CallHistory(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException , ParseException {

		APP_LOGS.info("=========== XP5S_TC_012_Sanity_MO_VoiceCall_from_CallHistory============");
		startAdbLog("XP5S_Sanity_012");
		recordVideo("XP5S_Sanity_012");
		launch_an_app("phone");
		callFromHistory() ;
		validate_Num_In_CallLog(refNum,"Call from Call Log");	
		test.log(LogStatus.PASS, "Test case status is Passed");
	}

	@Test(priority=13,dataProvider="XP5_AOSP_Sanity", dataProviderClass=DataProviders.class)
	public void XP5S_TC_013_Sanity_Call_History_details(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException , ParseException  {

		APP_LOGS.info("=========== XP5S_TC_013_Sanity_Call_History_details============");
		startAdbLog("XP5S_Sanity_013");
		recordVideo("XP5S_Sanity_013");
		launch_an_app("phone");
		clearCallLogs();
		callHistoryDetails();
		test.log(LogStatus.PASS, "Test case status is Passed");
	}

	@Test(priority=14,dataProvider="XP5_AOSP_Sanity", dataProviderClass=DataProviders.class)
	public void XP5S_TC_014_Sanity_Send_SMS_NewNumber(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException , ParseException  {

		APP_LOGS.info("=========== XP5S_TC_014_Sanity_Send_SMS_NewNumber============");
		startAdbLog("XP5S_Sanity_014");
		recordVideo("XP5S_Sanity_014");
		launch_an_app("messaging");
		delete_All_Threads();
		composeMsg(data.get("composeMessage"),refNum);
		send() ;
		validateMsgSent("Sent SMS to New Contact");	
		test.log(LogStatus.PASS, "Test case status is Passed");
	}

	@Test(priority=15,dataProvider="XP5_AOSP_Sanity", dataProviderClass=DataProviders.class)
	public void XP5S_TC_015_Sanity_Send_SMS_SavedContact(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException , ParseException , ParseException {

		APP_LOGS.info("=========== XP5S_TC_015_Sanity_Send_SMS_SavedContact============");
		startAdbLog("XP5S_Sanity_015");
		recordVideo("XP5S_Sanity_015");
		launch_an_app("contacts");
		createContact("Test", refNum);
		launch_an_app("messaging");
		delete_All_Threads();
		composeMsg(data.get("composeMessage"),refNum);
		send() ;
		validateMsgSent("Sent SMS to Saved Contact");	
		launch_an_app("contacts");
		deleteContact();
		test.log(LogStatus.PASS, "Test case status is Passed");
	}

	@Test(priority=16,dataProvider="XP5_AOSP_Sanity", dataProviderClass=DataProviders.class)
	public void XP5S_TC_016_Sanity_Send_SMS_With_AlphaNumeric_SpecialCharacters(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException , ParseException  {

		APP_LOGS.info("=========== XP5S_TC_016_Sanity_Send_SMS_With_AlphaNumeric_SpecialCharacters============");
		startAdbLog("XP5S_Sanity_016");
		recordVideo("XP5S_Sanity_016");
		launch_an_app("messaging");
		delete_All_Threads();
		composeMsg(data.get("composeMessage"),refNum);
		send() ;
		validateMsgSent("Sent AlphaNumeric and Special Characters SMS");	
		test.log(LogStatus.PASS, "Test case status is Passed");
	}

	@Test(priority=17,dataProvider="XP5_AOSP_Sanity", dataProviderClass=DataProviders.class)
	public void XP5S_TC_017_Sanity_Send_MultiPage_SMS(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException , ParseException  {

		APP_LOGS.info("=========== XP5S_TC_017_Sanity_Send_MultiPage_SMS============");
		startAdbLog("XP5S_Sanity_017");
		recordVideo("XP5S_Sanity_017");
		launch_an_app("messaging");
		delete_All_Threads();
		composeMsg(data.get("composeMessage"),refNum);
		send() ;
		validateMsgSent("Sent MultiPage SMS");	
		test.log(LogStatus.PASS, "Test case status is Passed");
	}

	@Test(priority=18,dataProvider="XP5_AOSP_Sanity", dataProviderClass=DataProviders.class)
	public void XP5S_TC_018_Sanity_Receive_SMS(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException , ParseException  {

		APP_LOGS.info("=========== XP5S_TC_018_Sanity_Receive_SMS============");
		startAdbLog("XP5S_Sanity_018");
		recordVideo("XP5S_Sanity_018");
		launch_an_app("messaging");
		sendSMS_fromRefDevice();
		validateMsgSent("received SMS");	
		test.log(LogStatus.PASS, "Test case status is Passed");
	}

	@Test(priority=19,dataProvider="XP5_AOSP_Sanity", dataProviderClass=DataProviders.class)
	public void XP5S_TC_019_Sanity_Save_SMS_as_Draft_and_Delete(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException , ParseException  {

		APP_LOGS.info("=========== XP5S_TC_019_Sanity_Save_SMS_as_Draft_and_Delete============");
		startAdbLog("XP5S_Sanity_019");
		recordVideo("XP5S_Sanity_019");
		launch_an_app("messaging");
		composeMsg(data.get("composeMessage"),refNum);
		customWait(1000);
		aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
		customWait(3000);
		validateMsgDraft(refNum,"SMS");	
		deleteMsg();
		validateDeleteSMS("SMS");
		test.log(LogStatus.PASS, "Test case status is Passed");
	}

	@Test(priority=20,dataProvider="XP5_AOSP_Sanity", dataProviderClass=DataProviders.class)
	public void XP5S_TC_020_Sanity_Send_MMS(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException , ParseException  {

		APP_LOGS.info("=========== XP5S_TC_020_Sanity_Send_MMS============");
		startAdbLog("XP5S_Sanity_020");
		recordVideo("XP5S_Sanity_020");
		launch_an_app("messaging");
		navigateTo_NewMessage();
		navigateAttachOthers();
		clickOnAttach_Options(Locators_Sanity.capturePicture);
		capturePic_ADD();
		validate_AnyAdded_Attachment(Locators_Sanity.image_Attachment);
		enter_ToField(refNum);
		type_Message(data.get("typeMessage"));
		send() ;
		validateMsgSent("Sent MMS");	
		press_BackKey();
		test.log(LogStatus.PASS, "Test case status is Passed");
	}

	@Test(priority=21,dataProvider="XP5_AOSP_Sanity", dataProviderClass=DataProviders.class)
	public void XP5S_TC_021_Sanity_Delete_MMS(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException , ParseException , ParseException {

		APP_LOGS.info("=========== XP5S_TC_021_Sanity_Delete_MMS============");
		startAdbLog("XP5S_Sanity_021");
		recordVideo("XP5S_Sanity_021");
		launch_an_app("messaging");
		deleteMsg();
		validateDeleteSMS("MMS");
		test.log(LogStatus.PASS, "Test case status is Passed");
	}

	@Test(priority=22,dataProvider="XP5_AOSP_Sanity", dataProviderClass=DataProviders.class)
	public void XP5S_TC_022_Sanity_Create_Contact_with_all_Fields_In_PhoneMemory(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException , ParseException  {

		APP_LOGS.info("=========== XP5S_TC_022_Sanity_Create_Contact_with_all_Fields_In_PhoneMemory============");
		startAdbLog("XP5S_Sanity_022");
		recordVideo("XP5S_Sanity_022");
		launch_an_app("contacts");
		deleteIfContactsPresent();
		addContactWithAllFields(data.get("Name prefix"), data.get("First name"), data.get("Middle name"), data.get("Last name"), data.get("Name suffix"), data.get("Phonetic last name"), data.get("Phonetic middle name"), data.get("Phonetic first name"), data.get("Nickname"), data.get("Company"), data.get("Title"),data.get("SIP"), data.get("Email"), data.get("Address"), data.get("IM"), data.get("Website"), data.get("Notes"));
		customWait(2000);
		test.log(LogStatus.PASS, "Test case status is Passed");
	}

	@Test(priority=23,dataProvider="XP5_AOSP_Sanity", dataProviderClass=DataProviders.class)
	public void XP5S_TC_023_Sanity_Create_Contact_Name_ContactNumber_SIM_Memory(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException , ParseException  {

		APP_LOGS.info("=========== XP5S_TC_023_Sanity_Create_Contact_Name_ContactNumber_SIM_Memory============");
		startAdbLog("XP5S_Sanity_023");
		recordVideo("XP5S_Sanity_023");
		launch_an_app("contacts");
		addContactAndCopyToSIM(data.get("name"));
		test.log(LogStatus.PASS, "Test case status is Passed");
	}


	@Test(priority=24,dataProvider="XP5_AOSP_Sanity", dataProviderClass=DataProviders.class)
	public void XP5S_TC_024_Sanity_Delete_Saved_Contacts(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException , ParseException  {

		APP_LOGS.info("=========== XP5S_TC_024_Sanity_Delete_Saved_Contacts============");
		startAdbLog("XP5S_Sanity_024");
		recordVideo("XP5S_Sanity_024");
		launch_an_app("contacts");
		deleteContact(data.get("name1"), data.get("name2"));
		test.log(LogStatus.PASS, "Test case status is Passed");
	}

	@Test(priority=25,dataProvider="XP5_AOSP_Sanity", dataProviderClass=DataProviders.class)
	public void XP5S_TC_025_Sanity_Enable_Disable_MobileData_DeviceSettings(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException , ParseException  {

		APP_LOGS.info("=========== XP5S_TC_025_Sanity_Enable_Disable_MobileData_DeviceSettings============");
		startAdbLog("XP5S_Sanity_025");
		recordVideo("XP5S_Sanity_025");
		launch_an_app("settings");
		enableData();
		launch_an_app("browser");		
		validateBrowseInternet();
		test.log(LogStatus.PASS, "Test case status is Passed");
	}


	@Test(priority=26,dataProvider="XP5_AOSP_Sanity", dataProviderClass=DataProviders.class)
	public void XP5S_TC_026_Sanity_Connection_Secured_Wifi_Enable_Disable_Wifi_DeviceSettings(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException , ParseException  {

		APP_LOGS.info("=========== XP5S_TC_026_Sanity_Connection_Secured_Wifi_Enable_Disable_Wifi_DeviceSettings============");
		startAdbLog("XP5S_Sanity_026");
		recordVideo("XP5S_Sanity_026");
		clearRecentApps();
		launch_an_app("settings");
		disableCellularData();
		back();
		launch_an_app("settings");
		ConnectSecuredWifi();
		minWait();
		validateDisableEnableWiFi();
		test.log(LogStatus.PASS, "Test case status is Passed");
	}


	@Test(priority=27,dataProvider="XP5_AOSP_Sanity", dataProviderClass=DataProviders.class)
	public void XP5S_TC_027_Sanity_Enable_Disable_Airplane_Devicesettings(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException , ParseException  {

		APP_LOGS.info("=========== XP5S_TC_027_Sanity_Enable_Disable_Airplane_Devicesettings============");
		startAdbLog("XP5S_Sanity_027");
		recordVideo("XP5S_Sanity_027");
		selectMoreOptions();
		validateEnabledisableFlightmode();
		test.log(LogStatus.PASS, "Test case status is Passed");
	}

	@Test(priority=28,dataProvider="XP5_AOSP_Sanity", dataProviderClass=DataProviders.class)
	public void XP5S_TC_028_Sanity_Enable_Disable_Bluetooth_Devicesettings(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException , ParseException  {

		APP_LOGS.info("=========== XP5S_TC_028_Sanity_Enable_Disable_Bluetooth_Devicesettings============");
		startAdbLog("XP5S_Sanity_028");
		recordVideo("XP5S_Sanity_028");
		launch_an_app("settings");
		validateDisableEnableBT();
		test.log(LogStatus.PASS, "Test case status is Passed");
	}

	@Test(priority=29,dataProvider="XP5_AOSP_Sanity", dataProviderClass=DataProviders.class)
	public void XP5S_TC_029_Sanity_Scan_BT_NearBy_Devices(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException  {

		APP_LOGS.info("=========== XP5S_TC_028_Sanity_Scan_BT_NearBy_Devices============");
		startAdbLog("XP5S_Sanity_029");
		recordVideo("XP5S_Sanity_029");
		enable_BT_RefDevice();
		launch_an_app("settings");
		validateScanBluetooth();
		enable_BT_RefDevice();
		test.log(LogStatus.PASS, "Test case status is Passed");
	}

	@Test(priority=30,dataProvider="XP5_AOSP_Sanity", dataProviderClass=DataProviders.class)
	public void XP5S_TC_030_Sanity_Enable_Disable_Location_Devicesettings(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException  {

		APP_LOGS.info("=========== XP5S_TC_030_Sanity_Enable_Disable_Location_Devicesettings============");
		startAdbLog("XP5S_Sanity_030");
		recordVideo("XP5S_Sanity_030");
		launch_an_app("settings");
		navigateToSettingsFeature(Locators_Sanity.Location_feature,"Location");
		disableFeature(Locators_Sanity.enabled,Locators_Sanity.disabled);
		if(isElementExist(Locators_Sanity.location_Agree)) {
			clickBtn(Locators_Sanity.location_Agree);
		}
		validateDisable(Locators_Sanity.disabled);
		enableFeature(Locators_Sanity.enabled,Locators_Sanity.disabled,Locators_Sanity.switch_Title);
		if(isElementExist(Locators_Sanity.location_Agree)) {
			clickBtn(Locators_Sanity.location_Agree);
		}
		validateEnable(Locators_Sanity.enabled);			
	}

	@Test(priority=31,dataProvider="XP5_AOSP_Sanity", dataProviderClass=DataProviders.class)
	public void XP5S_TC_031_Sanity_Enable_Disable_WiFi_QuickSettings(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException  {

		APP_LOGS.info("=========== XP5S_TC_031_Sanity_Enable_Disable_WiFi_QuickSettings============");
		startAdbLog("XP5S_Sanity_031");
		recordVideo("XP5S_Sanity_031");
		selectQuickSettingPanel();
		validateEnableDisable(Locators_Sanity.wifi,"Wi-Fi",Locators_Sanity.enabled_wifi,Locators_Sanity.disabled_wifi,Locators_Sanity.wifi_switch_QuickSet);
		test.log(LogStatus.PASS, "Test case status is Passed");			
	}

	@Test(priority=32,dataProvider="XP5_AOSP_Sanity", dataProviderClass=DataProviders.class)
	public void XP5S_TC_032_Sanity_Enable_Disable_Bluetooth_QuickSettings(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException  {

		APP_LOGS.info("=========== XP5S_TC_032_Sanity_Enable_Disable_Bluetooth_QuickSettings============");
		startAdbLog("XP5S_Sanity_032");
		recordVideo("XP5S_Sanity_032");
		selectQuickSettingPanel();
		validateEnableDisable(Locators_Sanity.bluetooth,"Bluetooth",Locators_Sanity.enabled_Bt,Locators_Sanity.disabled_Bt,Locators_Sanity.bluetooth_switch_QuickSet);
		test.log(LogStatus.PASS, "Test case status is Passed");	
	}

	@Test(priority=33,dataProvider="XP5_AOSP_Sanity", dataProviderClass=DataProviders.class)
	public void XP5S_TC_033_Sanity_Enable_Disable_AirplaneMode_QuickSettings(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException  {

		APP_LOGS.info("=========== XP5S_TC_033_Sanity_Enable_Disable_AirplaneMode_QuickSettings============");
		startAdbLog("XP5S_Sanity_033");
		recordVideo("XP5S_Sanity_033");
		selectQuickSettingPanel();
		validateEnableDisable(Locators_Sanity.Flightmode,"Airplane Mode",Locators_Sanity.enabled_FlightMode,Locators_Sanity.disabled_Flightmode,Locators_Sanity.airplane_switch_QuickSet);		
		test.log(LogStatus.PASS, "Test case status is Passed");	
	}

	@Test(priority=34,dataProvider="XP5_AOSP_Sanity", dataProviderClass=DataProviders.class)
	public void XP5S_TC_034_Sanity_Charging_Over_USB(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException  {

		APP_LOGS.info("=========== XP5S_TC_034_Sanity_Charging_Over_USB_BatteryLevel============");
		startAdbLog("XP5S_Sanity_034");
		recordVideo("XP5S_Sanity_034");
		launch_an_app("settings");
		ScrollToElement(Locators_Sanity.settings_frame, "About phone");
		validate_phone_Charging();	
		test.log(LogStatus.PASS, "Test case status is Passed");	
	}

	@Test(priority=35,dataProvider="XP5_AOSP_Sanity", dataProviderClass=DataProviders.class)
	public void XP5S_TC_035_Sanity_Date_Time_and_TimeZone(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException  {

		APP_LOGS.info("=========== XP5S_TC_035_Sanity_Date_Time_and_TimeZone============");
		startAdbLog("XP5S_Sanity_035");
		recordVideo("XP5S_Sanity_035");
		launch_an_app("settings");
		navigateTo_DateAndTime();
		validate_getDate_Time_TimeZone();
	}

	@Test(priority=36,dataProvider="XP5_AOSP_Sanity", dataProviderClass=DataProviders.class)
	public void XP5S_TC_036_Sanity_Add_EDIT_Delete_APN(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException  {

		APP_LOGS.info("=========== XP5S_TC_036_Sanity_Add_EDIT_Delete_APN============");
		startAdbLog("XP5S_Sanity_036");
		recordVideo("XP5S_Sanity_036");
		if(p_b_No.contains("-26.")||p_b_No.contains("-29.")) {
			test.log(LogStatus.PASS, "Test case status is Passed  because TC doesnot support the operator");
		}else {
			add_APN(data.get("apnName"),data.get("apn"),data.get("apnType"));
			validate_Add(data.get("apnName"));
			validateEditionDeletionApn();
			test.log(LogStatus.PASS, "Test case status is Passed");
		}
	}

	@Test(priority=37,dataProvider="XP5_AOSP_Sanity", dataProviderClass=DataProviders.class)
	public void XP5S_TC_037_Sanity_Internal_Storage_Space(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException  {

		APP_LOGS.info("=========== XP5S_TC_037_Sanity_Internal_Storage_Space============");
		startAdbLog("XP5S_Sanity_037");
		recordVideo("XP5S_Sanity_037");
		selectSettings(Locators_Sanity.Storage_Optn);
		validateInternalStorage();
		test.log(LogStatus.PASS, "Test case status is Passed");
	}

	@Test(priority=38,dataProvider="XP5_AOSP_Sanity", dataProviderClass=DataProviders.class)
	public void XP5S_TC_038_Sanity_VolumeKey_Validation_Up_Down(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException  {

		APP_LOGS.info("=========== XP5S_TC_038_Sanity_VolumeKey_Validation_Up_Down============");
		startAdbLog("XP5S_Sanity_038");
		recordVideo("XP5S_Sanity_038");
		volumeUpKey();
		customWait(2000);
		volumeDownKey();
		test.log(LogStatus.PASS, "Test case status is Passed");
	}

	@Test(priority=39,dataProvider="XP5_AOSP_Sanity", dataProviderClass=DataProviders.class)
	public void XP5S_TC_039_Sanity_PTT_Key_Validation(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException  {

		APP_LOGS.info("=========== XP5S_TC_039_Sanity_PTT_Key_Validation============");
		startAdbLog("XP5S_Sanity_039");
		  recordVideo("XP5S_Sanity_039"); 
		  if(p_b_No.contains("-26."))
		  {
		   test.log(LogStatus.INFO,"Operator not supported : Programmable key is not available");
		   test.log(LogStatus.SKIP, "Test case status is Skipped");
		  }else {
		  launch_an_app("settings");
		  customWait(2000);
		  navigateToPTTKey();
		  minWait();
		  selectApplicationAndLaunch();
		  minWait();
		  validateApplicationlaunch();
		  }
	}

	@Test(priority=40,dataProvider="XP5_AOSP_Sanity", dataProviderClass=DataProviders.class)
	public void XP5S_TC_040_Sanity_OtherKey_Validation_Green_RED_Clear_Back_Recent_Menu_Navigation(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException  {

		APP_LOGS.info("=========== XP5S_TC_040_Sanity_OtherKey_Validation_Green_RED_Clear_Back_Recent_Menu_Navigation============");
		startAdbLog("XP5S_Sanity_040");
		recordVideo("XP5S_Sanity_040");	
		validateHomeKey();
		minWait();
		validateGreen_Red_Key();
		minWait();
		validate_Menu_Back_Clear_Key();
		minWait();
		validateRecentKey();
		minWait();
		validateNavigationKeys();
	}

	@Test(priority=41,dataProvider="XP5_AOSP_Sanity", dataProviderClass=DataProviders.class)
	public void XP5S_TC_041_Sanity_FM_Radio_ON_OFF(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException  {

		APP_LOGS.info("=========== XP5S_TC_041_Sanity_FM_Radio_ON_OFF============");
		startAdbLog("XP5S_Sanity_041");
		recordVideo("XP5S_Sanity_041");
		selectFMApp();
		turnOFF_FM();
		validateOn_OFF_FM();
		test.log(LogStatus.PASS, "Test case status is Passed");
	}

	@Test(priority=42,dataProvider="XP5_AOSP_Sanity", dataProviderClass=DataProviders.class)
	public void XP5S_TC_042_Sanity_Browse_Using_MobileData(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException  {

		APP_LOGS.info("=========== XP5S_TC_042_Sanity_Browse_Using_MobileData============");
		startAdbLog("XP5S_Sanity_042");
		recordVideo("XP5S_Sanity_042");
		disableWifi();
		customWait(2000);
		enableData();
		customWait(10000);
		launch_an_app("browser");		
		validateBrowseInternet();
		test.log(LogStatus.PASS, "Test case status is Passed");
	}

	@Test(priority=43,dataProvider="XP5_AOSP_Sanity", dataProviderClass=DataProviders.class)
	public void XP5S_TC_043_Sanity_LoadHomePage_Browser_MobileData(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException  {

		APP_LOGS.info("=========== XP5S_TC_043_Sanity_LoadHomePage_Browser_MobileData============");
		startAdbLog("XP5S_Sanity_043");
		recordVideo("XP5S_Sanity_043");
		disableWifi();
		enableData();
		launch_an_app("browser");	
		validateHomePageLoadedBrowser();
		test.log(LogStatus.PASS, "Test case status is Passed");
	}

	@Test(priority=44,dataProvider="XP5_AOSP_Sanity", dataProviderClass=DataProviders.class)
	public void XP5S_TC_044_Sanity_Browse_Using_WiFi_Data(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException  {

		APP_LOGS.info("=========== XP5S_TC_044_Sanity_Browse_Using_WiFi_Data============");
		startAdbLog("XP5S_Sanity_044");
		recordVideo("XP5S_Sanity_044");
		launch_an_app("settings");
		disableCellularData();
		validateBrowseWithWiFi();
		test.log(LogStatus.PASS, "Test case status is Passed");
	}

	@Test(priority=45,dataProvider="XP5_AOSP_Sanity", dataProviderClass=DataProviders.class)
	public void XP5S_TC_045_Sanity_LoadHomePage_Browser_WiFiData(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException  {

		APP_LOGS.info("=========== XP5S_TC_045_Sanity_LoadHomePage_Browser_WiFiData============");
		startAdbLog("XP5S_Sanity_045");
		recordVideo("XP5S_Sanity_045");
		launch_an_app("settings");
		disableCellularData();
		validateHomePageLoadedBrowser();
		test.log(LogStatus.PASS, "Test case status is Passed");
	}

	@Test(priority=46,dataProvider="XP5_AOSP_Sanity", dataProviderClass=DataProviders.class)
	public void XP5S_TC_046_Sanity_Record_Media_Using_VoiceRecorder(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException  {

		APP_LOGS.info("=========== XP5S_TC_046_Sanity_Record_Media_Using_VoiceRecorder============");
		startAdbLog("XP5S_Sanity_046");
		recordVideo("XP5S_Sanity_046");
		launch_an_app("soundRecorder");
		deleteSavedRecorder();
		recordSound();
		validateRecordedSoundSaved();		
		test.log(LogStatus.PASS, "Test case status is Passed");
	}

	@Test(priority=47,dataProvider="XP5_AOSP_Sanity", dataProviderClass=DataProviders.class)
	public void XP5S_TC_047_Sanity_TakePicture_With_Back_Camera(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException  {

		APP_LOGS.info("=========== XP5S_TC_047_Sanity_TakePicture_With_Back_Camera============");
		startAdbLog("XP5S_Sanity_047");
		recordVideo("XP5S_Sanity_047");
		cameraPic();
		validateBackCamera("android.hardware.action.NEW_PICTURE","Camera","XP5S_Sanity_047");
		test.log(LogStatus.PASS, "Test case status is Passed");
	}

	@Test(priority=48,dataProvider="XP5_AOSP_Sanity", dataProviderClass=DataProviders.class)
	public void XP5S_TC_048_Sanity_TakeVideo_With_Back_Camera(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException  {

		APP_LOGS.info("=========== XP5S_TC_048_Sanity_TakeVideo_With_Back_Camera============");
		startAdbLog("XP5S_Sanity_048");
		recordVideo("XP5S_Sanity_048");
		launch_an_app("gallery");
		takeCameraVideo(1);
		press_Enter();
		validateBackCamera("android.hardware.action.NEW_VIDEO","Camera","XP5S_Sanity_048");
		test.log(LogStatus.PASS, "Test case status is Passed");
	}

	@Test(priority=49,dataProvider="XP5_AOSP_Sanity", dataProviderClass=DataProviders.class)
	public void XP5S_TC_049_Sanity_PlayMusic_from_MusicPlayer(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException  {

		APP_LOGS.info("=========== XP5S_TC_049_Sanity_PlayMusic_from_MusicPlayer============");
		startAdbLog("XP5S_Sanity_049");
		recordVideo("XP5S_Sanity_049");
		playMusic();	
		validate_music_player();
		test.log(LogStatus.PASS, "Test case status is Passed");
	}

	@Test(priority=50,dataProvider="XP5_AOSP_Sanity", dataProviderClass=DataProviders.class)
	public void XP5S_TC_050_Sanity_Change_SleepMode_Duration(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException  {

		APP_LOGS.info("=========== XP5S_TC_050_Sanity_Change_SleepMode_Duration============");
		startAdbLog("XP5S_Sanity_050");
		recordVideo("XP5S_Sanity_050");
		launch_an_app("settings");
		navigateToSettingsFeature(Locators_Sanity.display_feature,"Display");			
		validateChaneSleepTime();
		test.log(LogStatus.PASS, "Test case status is Passed");
	}

	@Test(priority=51,dataProvider="XP5_AOSP_Sanity", dataProviderClass=DataProviders.class)
	public void XP5S_TC_051_Sanity_Add_Event_Calender(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException , org.apache.http.ParseException, ParseException {

		APP_LOGS.info("=========== XP5S_TC_051_Sanity_Add_Event_Calender============");
		startAdbLog("XP5S_Sanity_051");
		recordVideo("XP5S_Sanity_051");
		checkOperatorAndlaunchCalendar();
		minWait();
		addCalendarEvent(data.get("eventName"), data.get("location"));
		test.log(LogStatus.PASS, "Test case status is Passed");	
	}

	@Test(priority=52,dataProvider="XP5_AOSP_Sanity", dataProviderClass=DataProviders.class)
	public void XP5S_TC_052_Sanity_Add_AlarmEvent_Clock(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException , org.apache.http.ParseException, ParseException {

		APP_LOGS.info("=========== XP5S_TC_052_Sanity_Add_AlarmEvent_Clock============");
		startAdbLog("XP5S_Sanity_052");
		recordVideo("XP5S_Sanity_052");
		launchClock();
		minWait();
		addAlarm();
		minWait();
		validateAddedAlarm();
		test.log(LogStatus.PASS, "Test case status is Passed");	
	}

	@Test(priority=53,dataProvider="XP5_AOSP_Sanity", dataProviderClass=DataProviders.class)
	public void XP5S_TC_053_Sanity_Snooze_And_Dismiss_Alarm(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException , org.apache.http.ParseException, ParseException {

		APP_LOGS.info("=========== XP5S_TC_053_Sanity_Snooze_And_Dismiss_Alarm============");
		startAdbLog("XP5S_Sanity_053");
		recordVideo("XP5S_Sanity_053");
		launchClock();
		minWait();
		snoozeTheAlarm();
		minWait();
		dismissTheAlarm();
		test.log(LogStatus.PASS, "Test case status is Passed");	
	}

	@Test(priority=54,dataProvider="XP5_AOSP_Sanity", dataProviderClass=DataProviders.class)
	public void XP5S_TC_054_Sanity_Snooze_Alarm_during_OngoingVoiceCall(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException , org.apache.http.ParseException, ParseException {

		APP_LOGS.info("=========== XP5S_TC_054_Sanity_Snooze_Alarm_during_OngoingVoiceCall============");
		startAdbLog("XP5S_Sanity_054");
		recordVideo("XP5S_Sanity_054");
		dailCallUsingDailPad(refNum);
		minWait();
		launchClock();
		minWait();
		snoozeTheAlarm();
		minWait();
		aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_ENDCALL);
		minWait();
		endcall();	
		test.log(LogStatus.PASS, "Test case status is Passed");	
	}


}
