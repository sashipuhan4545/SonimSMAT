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
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aosp.Utils.DataProviders;
import com.aosp.Utils.Locators_DevSanity;
import com.aosp.Utils.XP5S_Dev_Sanity_O_Util;
import com.graphics.gui.JsonFileReaderAndWriter;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTestInterruptedException;
import com.relevantcodes.extentreports.LogStatus;

import com.xp5S.util.ExcelConstants;
import com.xp5S.util.ExcelReader;
import com.xp5S.util.GetMethods;
import com.xp5S.util.Locators_BaseUtil;
import com.xp5S.util.appiumService;

import io.appium.java_client.android.AndroidKeyCode;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class XP5S_Dev_Sanity_O_Test extends XP5S_Dev_Sanity_O_Util {

	public ExcelReader excel;
	Properties properties;
	public static ExtentTestInterruptedException testexception;


	@BeforeSuite
	public void beforeSuite() throws IOException, InterruptedException, ParseException 
	{
		extent = new ExtentReports("src/test/resources/extentreport/XP5S_Dev_Sanity_O_TestReport.html", true); 
		fetch_Devices_Details();
		
		
		try {
			
			appiumService.TOTAL_NUM_OF_TESTCASES=GetMethods.TotalTestcase("XP5S_", this.getClass());
			
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
		.addSystemInfo("Operator", JsonFileReaderAndWriter.primaryDevOperatorReader());	}

	@BeforeClass
	public void copyFilesToDevice() throws IOException {
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
	public void tearDown(ITestResult result,Method method) throws IOException, InterruptedException
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
	public void setupSys() throws MalformedURLException, InterruptedException, FileNotFoundException, AWTException{
		properties=loadDriverAndProperties();
		Locators_DevSanity loc=new Locators_DevSanity(aDriver);
		Locators_BaseUtil loc1 =new Locators_BaseUtil(aDriver);
		PageFactory.initElements(new AppiumFieldDecorator(getDriver()),loc1);
		PageFactory.initElements(new AppiumFieldDecorator(getDriver()),loc);
		excel=new ExcelReader(ExcelConstants.XP5S_XL_PATH);		
	}

	//=================================================== Test cases ===============================================================
	@Test(priority=1,dataProvider="XP5_Dev_Sanity", dataProviderClass=DataProviders.class)
	public void XP5S_TC_01_DevSanity_check_able_to_Change_NetworkType(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException {

		APP_LOGS.info("=========== XP5S_TC_01_DevSanity============");
		startAdbLog("XP5S_Dev_Sanity_01");
		customWait(500);
		launch_an_app("settings");
		minWait();
		navigateToNetworkAndInternetOptions();
		minWait();
		navigateToWifiOption();
		customWait(2000);
		disableFeature(Locators_DevSanity.enabled,Locators_DevSanity.disabled);
		customWait(3000);		

		launch_an_app("settings");
		if(p_b_No.contains("-15.")) {
			changePreferredNetworkTypeVZW();
			navigateToSystemSettings(Locators_DevSanity.systemInsettingsList);
			navigateToSettingsFeature(Locators_DevSanity.AboutPhone,"About phone");
			validateLTESelection("LTE");
		}else if(p_b_No.contains("-29.")) {
			navigateTo_NetworkSettings();
			changePreferedNetworkType_Sprint();
		} else {
			navigateTo_NetworkSettings();
			changePreferedNetworkType_Mainline();
		}
		test.log(LogStatus.PASS, "Test case status is Passed");
	}

	@Test(priority=2,dataProvider="XP5_Dev_Sanity", dataProviderClass=DataProviders.class)
	public void XP5S_TC_02_DevSanity_check_IMS_Registration_Status(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException {

		APP_LOGS.info("=========== XP5S_TC_02_DevSanity============");
		startLog("XP5S_Dev_Sanity_02");
		customWait(500);
		launch_an_app("settings");
		performAction();
		validateIMSLog("RIL_REQUEST_IMS_REGISTRATION_STATE {1, 1} [SUB0]","XP5S_Dev_Sanity_02");
		customWait(3000);
		test.log(LogStatus.PASS, "Test case status is Passed");
	}

	@Test(priority=3,dataProvider="XP5_Dev_Sanity", dataProviderClass=DataProviders.class)
	public void XP5S_TC_03_DevSanity_check_Enable_Disable_AirplaneMode(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException 
	{
		APP_LOGS.info("=========== XP5S_TC_03_DevSanity============");
		startAdbLog("XP5S_Dev_Sanity_03");
		launch_an_app("settings");	
		navigateToAirplaneMode();
		validateEnabledisableFlightmode();
		test.log(LogStatus.PASS, "Test case status is Passed");
	}

	@Test(priority=4,dataProvider="XP5_Dev_Sanity", dataProviderClass=DataProviders.class)
	public void XP5S_TC_04_DevSanity_LaunchApp_Menu_and_LaunchAllApp_from_ApplicationMenu(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException {

		APP_LOGS.info("=========== XP5S_TC_04_DevSanity============");
		startAdbLog("XP5S_Dev_Sanity_04");
		customWait(500);
		validateAppMenuLaunch();	
		customWait(500);
		enableData();
		validateLaunch_All_Applications();		
		customWait(3000);
		test.log(LogStatus.PASS, "Test case status is Passed");
	}

	@Test(priority=5,dataProvider="XP5_Dev_Sanity", dataProviderClass=DataProviders.class)
	public void XP5S_TC_05_DevSanity_check_Able_to_Make_VoiceCall(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException 
	{
		APP_LOGS.info("=========== XP5S_TC_05_DevSanity============");
		startAdbLog("XP5S_Dev_Sanity_05");
		launch_an_app("contacts");
		deleteIfContactsPresent();
		customWait(3000);
		dailCallUsingDailPad(refNum);
		customWait(3000);
		endcall();		
		validate_Num_In_CallLog(refNum,"Outgoing");
		test.log(LogStatus.PASS, "Test case status is Passed");
	}

	@Test(priority=6,dataProvider="XP5_Dev_Sanity", dataProviderClass=DataProviders.class)
	public void XP5S_TC_06_DevSanity_check_Able_to_Receive_VoiceCall(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException 
	{
		APP_LOGS.info("=========== XP5S_TC_06_DevSanity============");
		startAdbLog("XP5S_Dev_Sanity_06");
		endCall_RefDevice();
		make_Call_from_RefDev();
		customWait(3000);
		recieve_Call_PrimaryDev();
		customWait(5000);
		endCall_RefDevice();
		customWait(2000);
		launch_an_app("phone");
		validate_Num_In_CallLog(refNum,"Received");	
		test.log(LogStatus.PASS, "Test case status is Passed");
	}

	@Test(priority=7,dataProvider="XP5_Dev_Sanity", dataProviderClass=DataProviders.class)
	public void XP5S_TC_07_DevSanity_check_Able_to_Reject_VoiceCall(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException 
	{
		APP_LOGS.info("=========== XP5S_TC_07_DevSanity============");
		startAdbLog("XP5S_Dev_Sanity_07");
		launch_an_app("phone");
		make_Call_from_RefDev();
		customWait(10000);
		endCall_PIDDevice();
		customWait(5000);
		endCall_RefDevice();
		customWait(1000);
		validate_Num_In_CallLog(refNum,"Rejected");
		test.log(LogStatus.PASS, "Test case status is Passed");
	}

	@Test(priority=8,dataProvider="XP5_Dev_Sanity", dataProviderClass=DataProviders.class)
	public void XP5S_TC_08_DevSanity_Validate_In_Call_Functionality(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException 
	{
		APP_LOGS.info("=========== XP5S_TC_08_DevSanity============");
		startLog("XP5S_Dev_Sanity_08");
		recordVideo("XP5S_Dev_Sanity_08");
		launch_an_app("phone");
		inCallFunctionality();
		test.log(LogStatus.PASS, "Test case status is Passed");		
	}

	@Test(priority=9,dataProvider="XP5_Dev_Sanity", dataProviderClass=DataProviders.class)
	public void XP5S_TC_09_DevSanity_MT_Call_Reject_With_SMS_Notification(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException 
	{
		APP_LOGS.info("=========== XP5S_TC_09_DevSanity============");
		startAdbLog("XP5S_Dev_Sanity_09");
		launch_an_app("phone");
		make_Call_from_RefDev();
		rejectWithSMS();
		test.log(LogStatus.PASS, "Test case status is Passed");		
	}

	@Test(priority=10,dataProvider="XP5_Dev_Sanity", dataProviderClass=DataProviders.class)
	public void XP5S_TC_10_DevSanity_Send_SMS_During_Ongoing_Voice_Call(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException 
	{
		APP_LOGS.info("=========== XP5S_TC_10_DevSanity============");
		startAdbLog("XP5S_Dev_Sanity_10");
		launch_an_app("phone");
		customWait(3000);
		launch_an_app("phone");
		dailCallUsingDailPad(refNum);
		customWait(1000);
		launch_an_app("messaging");
		composeMsg(data.get("composeMessage"),refNum);
		send();
		customWait(2000);
		OCR.Read_File.takeScreenShotForOcr("SMS_Sent");
		Thread.sleep(2000);
		OCR.my_main.validate_Using_OCR("SMS_Sent.png");
		validateMsgSent("Sent");
		customWait(1000);
		endCall_RefDevice();	
		test.log(LogStatus.PASS, "Test case status is Passed");	
	}

	@Test(priority=11,dataProvider="XP5_Dev_Sanity", dataProviderClass=DataProviders.class)
	public void XP5S_TC_11_DevSanity_Validate_Call_History_Details(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException 
	{
		APP_LOGS.info("=========== XP5S_TC_11_DevSanity============");
		startAdbLog("XP5S_Dev_Sanity_11");
		launch_an_app("phone");
		clearCallLogs();
		callHistoryDetails();
		test.log(LogStatus.PASS, "Test case status is Passed");	
	}

	@Test(priority=12,dataProvider="XP5_Dev_Sanity", dataProviderClass=DataProviders.class)
	public void XP5S_TC_12_DevSanity_check_Send_SMS(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException 
	{
		APP_LOGS.info("=========== XP5S_TC_12_DevSanity============");
		startAdbLog("XP5S_Dev_Sanity_12");
		launch_an_app("messaging");
		delete_All_Threads();
		composeMsg(data.get("composeMessage"),refNum);
		send() ;
		validateMsgSent("Sent");	
		test.log(LogStatus.PASS, "Test case status is Passed");
	}

	@Test(priority=13,dataProvider="XP5_Dev_Sanity", dataProviderClass=DataProviders.class)
	public void XP5S_TC_13_DevSanity_check_Send_MultiPage_SMS(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException 
	{
		APP_LOGS.info("=========== XP5S_TC_13_DevSanity============");
		startAdbLog("XP5S_Dev_Sanity_13");
		launch_an_app("messaging");
		composeMsg(data.get("composeMessage"),refNum);
		send() ;
		validateMsgSent("Sent");	
		test.log(LogStatus.PASS, "Test case status is Passed");
	}

	@Test(priority=14,dataProvider="XP5_Dev_Sanity", dataProviderClass=DataProviders.class)
	public void XP5S_TC_14_DevSanity_check_Receive_SMS(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException 
	{
		APP_LOGS.info("=========== XP5S_TC_14_DevSanity============");
		startAdbLog("XP5S_Dev_Sanity_14");
		launch_an_app("messaging");
		sendSMS_fromRefDevice();
		validateMsgSent("received");	
		test.log(LogStatus.PASS, "Test case status is Passed");
	}

	@Test(priority=15,dataProvider="XP5_Dev_Sanity", dataProviderClass=DataProviders.class)
	public void XP5S_TC_15_DevSanity_check_SMS_able_to_save_Draft(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException 
	{
		APP_LOGS.info("=========== XP5S_TC_15_DevSanity============");
		startAdbLog("XP5S_Dev_Sanity_15");
		launch_an_app("messaging");
		composeMsg(data.get("composeMessage"),refNum);
		customWait(1000);
		aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
		customWait(3000);
		validateMsgDraft(refNum,"SMS");	
		test.log(LogStatus.PASS, "Test case status is Passed");
	}

	@Test(priority=16,dataProvider="XP5_Dev_Sanity", dataProviderClass=DataProviders.class)
	public void XP5S_TC_16_DevSanity_check_SMS_able_To_Delete(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException 
	{
		APP_LOGS.info("=========== XP5S_TC_16_DevSanity============");
		startAdbLog("XP5S_Dev_Sanity_16");
		launch_an_app("messaging");
		deleteMsg();
		validateDeleteSMS("SMS");
		test.log(LogStatus.PASS, "Test case status is Passed");
	}

	@Test(priority=17,dataProvider="XP5_Dev_Sanity", dataProviderClass=DataProviders.class)
	public void XP5S_TC_17_DevSanity_check_MMS_able_To_Send(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException 
	{
		APP_LOGS.info("=========== XP5S_TC_17_DevSanity============");
		startAdbLog("XP5S_Dev_Sanity_17");
		launch_an_app("messaging");
		navigateTo_NewMessage();
		navigateAttachOthers();
		clickOnAttach_Options(Locators_DevSanity.capturePicture);
		capturePic_ADD();
		validate_AnyAdded_Attachment(Locators_DevSanity.image_Attachment_oreo);
		enter_ToField(refNum);
		type_Message(data.get("typeMessage"));
		send_SMS();
		clickBackButton(1);
		validateMsgSent("Sent MMS");	
		clickBackButton(1);
		test.log(LogStatus.PASS, "Test case status is Passed");
	}

	@Test(priority=18,dataProvider="XP5_Dev_Sanity", dataProviderClass=DataProviders.class)
	public void XP5S_TC_18_DevSanity_check_MMS_able_to_save_Draft(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException 
	{
		APP_LOGS.info("=========== XP5S_TC_18_DevSanity============");
		startAdbLog("XP5S_Dev_Sanity_18");
		launch_an_app("messaging");
		navigateTo_NewMessage();
		navigateAttachOthers();
		clickOnAttach_Options(Locators_DevSanity.capturePicture);
		capturePic_ADD();
		validate_AnyAdded_Attachment(Locators_DevSanity.image_Attachment_oreo);
		enter_ToField(refNum);
		type_Message(data.get("typeMessage"));
		customWait(1000);
		aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
		customWait(3000);
		validateMsgDraft(refNum,"MMS");	
		test.log(LogStatus.PASS, "Test case status is Passed");
	}

	@Test(priority=19,dataProvider="XP5_Dev_Sanity", dataProviderClass=DataProviders.class)
	public void XP5S_TC_19_DevSanity_check_MMS_able_to_Delete(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException 
	{
		APP_LOGS.info("=========== XP5S_TC_19_DevSanity============");
		startAdbLog("XP5S_Dev_Sanity_19");
		launch_an_app("messaging");
		deleteMsg();
		validateDeleteSMS("MMS");
		test.log(LogStatus.PASS, "Test case status is Passed");
	}

	@Test(priority=20,dataProvider="XP5_Dev_Sanity", dataProviderClass=DataProviders.class)
	public void XP5S_TC_20_DevSanity_Connect_WiFi_SSID_Browse(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException {

		APP_LOGS.info("=========== XP5S_TC_20_DevSanity============");
		startAdbLog("XP5S_Dev_Sanity_20");
		launch_an_app("settings");
		disableCellularData();
		clickBackButton(3);
		launch_an_app("settings");
		ConnectSecuredWifi();
		validateDisableEnableWiFi();
		test.log(LogStatus.PASS, "Test case status is Passed");
	}

	@Test(priority=21,dataProvider="XP5_Dev_Sanity", dataProviderClass=DataProviders.class)
	public void XP5S_TC_21_DevSanity_Disable_Enable_WiFi_DeviceSettings(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException {

		APP_LOGS.info("=========== XP5S_TC_21_DevSanity============");
		startAdbLog("XP5S_Dev_Sanity_21");
		launch_an_app("settings");
		disableCellularData();
		validateDisableEnableWiFi();
		test.log(LogStatus.PASS, "Test case status is Passed");
	}

	@Test(priority=22,dataProvider="XP5_Dev_Sanity", dataProviderClass=DataProviders.class)
	public void XP5S_TC_22_DevSanity_Disable_Enable_WiFi_QuickSettings(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException {

		APP_LOGS.info("=========== XP5S_TC_22_DevSanity============");
		startAdbLog("XP5S_Dev_Sanity_22");
		selectQuickSettingPanel();
		validateEnableDisable(Locators_DevSanity.wifi,"Wi-Fi",Locators_DevSanity.enabled_wifi,Locators_DevSanity.disabled_wifi,Locators_DevSanity.wifi_switch_QuickSet);
		test.log(LogStatus.PASS, "Test case status is Passed");

	}

	@Test(priority=23,dataProvider="XP5_Dev_Sanity", dataProviderClass=DataProviders.class)
	public void XP5S_TC_23_DevSanity_check_Browser_using_MobileData(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException 
	{
		APP_LOGS.info("=========== XP5S_TC_23_DevSanity============");
		startAdbLog("XP5S_Dev_Sanity_23");
		disableWifi();
		customWait(2000);
		enableData();
		customWait(10000);
		launch_an_app("browser");		
		validateBrowseInternet();
		test.log(LogStatus.PASS, "Test case status is Passed");
	}

	@Test(priority=24,dataProvider="XP5_Dev_Sanity", dataProviderClass=DataProviders.class)
	public void XP5S_TC_24_DevSanity_Disable_Enable_BT_DeviceSettings(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException 
	{
		APP_LOGS.info("=========== XP5S_TC_24_DevSanity============");
		startAdbLog("XP5S_Dev_Sanity_24");
		launch_an_app("settings");
		validateDisableEnableBT();
		test.log(LogStatus.PASS, "Test case status is Passed");
	}

	@Test(priority=25,dataProvider="XP5_Dev_Sanity", dataProviderClass=DataProviders.class)
	public void XP5S_TC_25_DevSanity_Scan_BT_NearBy_Devices(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException 
	{
		APP_LOGS.info("=========== XP5S_TC_25_DevSanity============");
		startAdbLog("XP5S_Dev_Sanity_25");
		enable_BT_RefDevice();
		launch_an_app("settings");
		validateScanBluetooth();
		enable_BT_RefDevice();
		test.log(LogStatus.PASS, "Test case status is Passed");
	}

	@Test(priority=26,dataProvider="XP5_Dev_Sanity", dataProviderClass=DataProviders.class)
	public void XP5S_TC_26_DevSanity_check_Charging_Over_USB(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException 
	{
		APP_LOGS.info("=========== XP5S_TC_26_DevSanity============");
		startAdbLog("XP5S_Dev_Sanity_26");
		launch_an_app("settings");
		navigateToSystemSettings(Locators_DevSanity.systemInsettingsList);
		navigateToSettingsFeature(Locators_DevSanity.AboutPhone_feature,"About phone");
		validate_phone_Charging();	
		test.log(LogStatus.PASS, "Test case status is Passed");
	}

	@Test(priority=27,dataProvider="XP5_Dev_Sanity", dataProviderClass=DataProviders.class)
	public void XP5S_TC_27_DevSanity_check_SD_Card_Detected(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException 
	{
		APP_LOGS.info("=========== XP5S_TC_27_DevSanity============");
		startAdbLog("XP5S_Dev_Sanity_27");
		launch_an_app("fileExplorer");
		navigateToSDCard();
		validateSDCardDetection();
		test.log(LogStatus.PASS, "Test case status is Passed");
	}

	@Test(priority=28,dataProvider="XP5_Dev_Sanity", dataProviderClass=DataProviders.class)
	public void XP5S_TC_28_DevSanity_check_SD_Card_Size(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException 
	{
		APP_LOGS.info("=========== XP5S_TC_28_DevSanity============");
		startAdbLog("XP5S_Dev_Sanity_28");
		launch_an_app("fileExplorer");
		selectProperties();
		fetchSDcardSize();
		test.log(LogStatus.PASS, "Test case status is Pass");
	}

	@Test(priority=29,dataProvider="XP5_Dev_Sanity", dataProviderClass=DataProviders.class)
	public void XP5S_TC_29_DevSanity_checking_Device_IMEI(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException 
	{
		APP_LOGS.info("=========== XP5S_TC_29_DevSanity============");
		startAdbLog("XP5S_Dev_Sanity_29");
		deviceIMEI();
		validateIMEIdisplayed();	
		test.log(LogStatus.PASS, "Test case status is Passed");
	}

	@Test(priority=30,dataProvider="XP5_Dev_Sanity", dataProviderClass=DataProviders.class)
	public void XP5S_Dev_Sanity_30_Add_Contact_In_Phonebook_With_All_Fields(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException 
	{
		APP_LOGS.info("=========== XP5S_TC_30_DevSanity============");
		startAdbLog("XP5S_Dev_Sanity_30");
		launch_an_app("contacts");
		deleteIfContactsPresent();
		addContactWithAllFields(data.get("Name prefix"), data.get("First name"), data.get("Middle name"), data.get("Last name"), data.get("Name suffix"), data.get("Phonetic last name"), data.get("Phonetic middle name"), data.get("Phonetic first name"), data.get("Nickname"), data.get("Company"), data.get("Title"),data.get("SIP"), data.get("Email"), data.get("Address"), data.get("IM"), data.get("Website"), data.get("Notes"));
		customWait(2000);
		test.log(LogStatus.PASS, "Test case status is Passed");
	}

	@Test(priority=31,dataProvider="XP5_Dev_Sanity", dataProviderClass=DataProviders.class)
	public void XP5S_Dev_Sanity_31_Add_Contact_In_SIM_Memory_With_Name_And_Phone_Field(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException 
	{
		APP_LOGS.info("=========== XP5S_TC_31_DevSanity============");
		startAdbLog("XP5S_Dev_Sanity_31");
		launch_an_app("contacts");
		addContactAndCopyToSIM(data.get("name"));
		test.log(LogStatus.PASS, "Test case status is Passed");
	}

	@Test(priority=32,dataProvider="XP5_Dev_Sanity", dataProviderClass=DataProviders.class)
	public void XP5S_Dev_Sanity_32_Delete_Contact(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException 
	{
		APP_LOGS.info("=========== XP5S_TC_32_DevSanity============");
		startAdbLog("XP5S_Dev_Sanity_32");
		launch_an_app("contacts");
		deleteContact(data.get("name1"), data.get("name2"));
		test.log(LogStatus.PASS, "Test case status is Passed");
	}

	@Test(priority=33,dataProvider="XP5_Dev_Sanity", dataProviderClass=DataProviders.class)
	public void XP5S_TC_33_DevSanity_check_back_Camera_with_Picture(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException 
	{
		APP_LOGS.info("=========== XP5S_TC_33_DevSanity============");
		startAdbLog("XP5S_Dev_Sanity_33");
		cameraPic();
		validateCamera("Captured picture");
		//	validateBackCamera("android.hardware.action.NEW_PICTURE","Camera","XP5S_Dev_Sanity_33");
		test.log(LogStatus.PASS, "Test case status is Passed");
	}

	@Test(priority=34,dataProvider="XP5_Dev_Sanity", dataProviderClass=DataProviders.class)
	public void XP5S_TC_34_DevSanity_check_back_Camera_with_Video(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException 
	{
		APP_LOGS.info("=========== XP5S_TC_34_DevSanity============");
		startAdbLog("XP5S_Dev_Sanity_34");
		launch_an_app("gallery");
		takeCameraVideo(1);
		//press_Enter();
		validateCamera("Captured video");
		//validateBackCamera("android.hardware.action.NEW_VIDEO","Camera","XP5S_Dev_Sanity_34");
		test.log(LogStatus.PASS, "Test case status is Passed");
	}

	@Test(priority=35,dataProvider="XP5_Dev_Sanity", dataProviderClass=DataProviders.class)
	public void XP5S_TC_35_DevSanity_check_Sound_Recorder(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException 
	{
		APP_LOGS.info("=========== XP5S_TC_35_DevSanity============");
		startAdbLog("XP5S_Dev_Sanity_35");
		launch_an_app("soundRecorder");
		deleteSavedRecorder();
		recordSound();
		validateRecordedSoundSaved();		
		test.log(LogStatus.PASS, "Test case status is Passed");
	}

	@Test(priority=36,dataProvider="XP5_Dev_Sanity", dataProviderClass=DataProviders.class)
	public void XP5S_TC_36_DevSanity_check_able_To_Play_Music(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException 
	{
		APP_LOGS.info("=========== XP5S_TC_36_DevSanity============");
		startAdbLog("XP5S_Dev_Sanity_36");
		playMusic();	
		validate_music_player();
		test.log(LogStatus.PASS, "Test case status is Passed");
	}

	@Test(priority=37,dataProvider="XP5_Dev_Sanity", dataProviderClass=DataProviders.class)
	public void XP5S_TC_37_DevSanity_check_Able_to_Change_SleepTime(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException 
	{
		APP_LOGS.info("=========== XP5S_TC_37_DevSanity============");
		startAdbLog("XP5S_Dev_Sanity_37");
		launch_an_app("settings");
		navigateToSettingsFeature(Locators_DevSanity.display_feature,"Display");			
		validateChaneSleepTime();
		test.log(LogStatus.PASS, "Test case status is Passed");
	}

	@Test(priority=38,dataProvider="XP5_Dev_Sanity", dataProviderClass=DataProviders.class)
	public void XP5S_TC_38_DevSanity_check_Able_to_Change_Ringtone(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException 
	{
		APP_LOGS.info("=========== XP5S_TC_38_DevSanity============");
		startAdbLog("XP5S_Dev_Sanity_38");
		recordVideo("XP5S_Dev_Sanity_38");	
		launch_an_app("settings");
		navigateToSettingsFeature(Locators_DevSanity.sound_feature,"Sound");			
		ValidatechangeRingtone();	
		test.log(LogStatus.PASS, "Test case status is Passed");
	}

	@Test(priority=39,dataProvider="XP5_Dev_Sanity", dataProviderClass=DataProviders.class)
	public void XP5S_TC_39_DevSanity_check_Able_to_Enable_Disable_Location(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException 
	{
		APP_LOGS.info("=========== XP5S_TC_39_DevSanity============");
		startAdbLog("XP5S_Dev_Sanity_39");
		launch_an_app("settings");
		navigateToSettingsFeature(Locators_DevSanity.securityAndLocation,"Security & location");
		clickBtn(Locators_DevSanity.Location_feature);
		disableFeature(Locators_DevSanity.enabled,Locators_DevSanity.disabled);
		if(isElementExist(Locators_DevSanity.location_Agree)) {
			clickBtn(Locators_DevSanity.location_Agree);
		}
		validateDisable(Locators_DevSanity.disabled);
		enableFeature(Locators_DevSanity.enabled,Locators_DevSanity.disabled,Locators_DevSanity.switch_Title);
		if(isElementExist(Locators_DevSanity.location_Agree)) {
			clickBtn(Locators_DevSanity.location_Agree);
		}
		validateEnable(Locators_DevSanity.enabled);	
		test.log(LogStatus.PASS, "Test case status is Passed");
	}

	@Test(priority=40,dataProvider="XP5_Dev_Sanity", dataProviderClass=DataProviders.class)
	public void XP5S_Dev_Sanity_40_Add_An_calendar_Event(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException {

		APP_LOGS.info("===========XP5S_Dev_Sanity_40============");
		startAdbLog("XP5S_Dev_Sanity_40");
		checkOperatorAndlaunchCalendar();
		minWait();
		addCalendarEvent(data.get("eventName"), data.get("location"));
		test.log(LogStatus.PASS, "Test case status is Passed");		
	}

	@Test(priority=41,dataProvider="XP5_Dev_Sanity", dataProviderClass=DataProviders.class)
	public void XP5S_Dev_Sanity_41_Add_An_Alarm_Event(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException {

		APP_LOGS.info("===========XP5S_Dev_Sanity_41============");
		startAdbLog("XP5S_Dev_Sanity_41");
		launchClock();
		minWait();
		addAlarm();
		minWait();
		validateAddedAlarm();
		test.log(LogStatus.PASS, "Test case status is Passed");		
	}

	@Test(priority=42,dataProvider="XP5_Dev_Sanity", dataProviderClass=DataProviders.class)
	public void XP5S_Dev_Sanity_42_Validate_Snooze_And_Dismiss_Alarm(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException {

		APP_LOGS.info("===========XP5S_Dev_Sanity_42============");
		startAdbLog("XP5S_Dev_Sanity_42");
		launchClock();
		minWait();
		snoozeTheAlarm();
		minWait();
		dismissTheAlarm();
		test.log(LogStatus.PASS, "Test case status is Passed");		
	}

	@Test(priority=43,dataProvider="XP5_Dev_Sanity", dataProviderClass=DataProviders.class)
	public void XP5S_Dev_Sanity_43_Validate_LaunchScout_All_Apps(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException {

		APP_LOGS.info("===========XP5S_Dev_Sanity_43============");
		startAdbLog("XP5S_Dev_Sanity_43");
		launch_an_app("scout");
		validateScoutAppLaunch();
		launch_an_app("scout");
		validatelaunchAllSCoutApps();		
		test.log(LogStatus.PASS, "Test case status is Passed");		
	}

}
