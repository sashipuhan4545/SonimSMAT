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
import com.aosp.Utils.XP5S_Dev_Sanity_Util;
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


public class XP5S_Dev_QA_Sanity_Test  extends XP5S_Dev_Sanity_Util{
	
	public ExcelReader excel;
	Properties properties;
	public static ExtentTestInterruptedException testexception;


	@BeforeSuite
	public void beforeSuite() throws IOException 
	{
		extent = new ExtentReports("src/test/resources/extentreport/XP5S_Dev_Sanity_TestReport.html", true); 
		
		try {
			appiumService.TOTAL_NUM_OF_TESTCASES=GetMethods.TotalTestcase("XP5S", this.getClass());
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
		fetch_Devices_Details();
		
	}
	
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
	
	@Test(priority=1,dataProvider="XP5_Dev_Sanity", dataProviderClass=DataProviders.class)
	public void XP5S_TC_001_DevSanity_LaunchApp_Menu_and_LaunchAllApp_from_ApplicationMenu(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException {

		
		/*OCR.Read_File.takeScreenShotForOcr("2");
		Thread.sleep(2000);
		OCR.my_main.validate_Using_OCR("1.png");
		*/
		
		APP_LOGS.info("=========== XP5S_TC_001_DevSanity============");
		startAdbLog("XP5_Dev_Sanity_01");
		recordVideo("XP5_Dev_Sanity_01");
		customWait(500);
		clearRecentApps();	
	    validateAppMenuLaunch();	
	    customWait(500);
		validateLaunch_All_Applications();		
		customWait(3000);
		test.log(LogStatus.PASS, "Test case status is Passed");
	}
	
	@Test(priority=2,dataProvider="XP5_Dev_Sanity", dataProviderClass=DataProviders.class)
	public void XP5S_TC_002_DevSanity_check_able_to_Change_NetworkType(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException {

		APP_LOGS.info("=========== XP5S_TC_002_DevSanity============");
		startAdbLog("XP5S_Dev_Sanity_02");
		recordVideo("XP5S_Dev_Sanity_02");
		customWait(500);
		launch_an_app("settings");
		navigateToSettingsFeature(Locators_DevSanity.display_feature,"Display");	
		setSleepMax();
		clearRecentApps();	
		launch_an_app("settings");
		clickBtn(Locators_DevSanity.wifi);
	    customWait(2000);		
		disableFeature(Locators_DevSanity.enabled, Locators_DevSanity.disabled);
		customWait(3000);
		String XP5deviceModelNumber=JsonFileReaderAndWriter.primaryDevFirmwareReader();
		System.out.println(XP5deviceModelNumber);
		if(XP5deviceModelNumber.contains("-10.")||XP5deviceModelNumber.contains("-26.")) {
			launch_an_app("settings");
			navigateToSettingsFeature(Locators_DevSanity.AboutPhone,"About phone");	
			validateLTESelection("LTE");
		}		
		else if(XP5deviceModelNumber.contains("-30.")) {
			navigateTo_NetworkSettings();
			changePreferedNetworkType_Reskin();
		}		
		else if(XP5deviceModelNumber.contains("-29.")) {
			navigateTo_NetworkSettings();
			changePreferedNetworkType_Sprint();
		}
		else {			
			navigateTo_NetworkSettings();
			changePreferedNetworkType();			
		}		
		test.log(LogStatus.PASS, "Test case status is Passed");
	}
	
	@Test(priority=3,dataProvider="XP5_Dev_Sanity", dataProviderClass=DataProviders.class)
	public void XP5S_TC_003_DevSanity_check_IMS_Registration_Status(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException {

		APP_LOGS.info("=========== XP5S_TC_003_DevSanity============");
		startLog("XP5S_Dev_Sanity_03");
		recordVideo("XP5S_Dev_Sanity_03");
		customWait(500);
		performAction();
		customWait(2000);
		validateIMSLog("RIL_REQUEST_IMS_REGISTRATION_STATE {1, 1} [SUB0]","XP5S_Dev_Sanity_03");
		customWait(3000);
		test.log(LogStatus.PASS, "Test case status is Passed");
	}
	
	@Test(priority=4,dataProvider="XP5_Dev_Sanity", dataProviderClass=DataProviders.class)
	public void XP5S_TC_004_DevSanity_check_Enable_Disable_AirplaneMode(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException 
	{
		APP_LOGS.info("=========== XP5S_TC_004_DevSanity============");
		startAdbLog("XP5_Dev_Sanity_04");
		recordVideo("XP5_Dev_Sanity_04");	
		String XP5deviceModelNumber=JsonFileReaderAndWriter.primaryDevOperatorReader();
		if(XP5deviceModelNumber.contains("-35.")) {	
			enableData();
			selectMoreOptions();
			enableSwitch(Locators_DevSanity.enabled_Airplane,Locators_DevSanity.disabled_Airplane,Locators_DevSanity.switch_widget);
			customWait(5000);
			launch_an_app("browser");	
			customWait(2000);
			enterUrl("www.google.com");
			validateAirplaneSB();			
		}
		else {		
		selectMoreOptions();
		validateEnabledisableFlightmode();
		test.log(LogStatus.PASS, "Test case status is Passed");
		}
	}
	
	
	@Test(priority=5,dataProvider="XP5_Dev_Sanity", dataProviderClass=DataProviders.class)
	public void XP5S_TC_005_DevSanity_check_Able_to_Make_VoiceCall(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException 
	{
		APP_LOGS.info("=========== XP5S_TC_005_DevSanity============");
		startAdbLog("XP5_Dev_Sanity_05");
		recordVideo("XP5_Dev_Sanity_05");
		String XP5deviceModelNumber=JsonFileReaderAndWriter.primaryDevOperatorReader();
		if(XP5deviceModelNumber.contains("-35.")) {	
			test.log(LogStatus.SKIP, "Test case doesn't support");
		}
		else {
			MO_CALL_Sanity();
			disconnectVoiceCall();
	
		test.log(LogStatus.PASS, "Test case status is Passed");
		}
	}
	
	@Test(priority=6,dataProvider="XP5_Dev_Sanity", dataProviderClass=DataProviders.class)
	public void XP5S_TC_006_DevSanity_check_Able_to_Receive_VoiceCall(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException 
	{
		APP_LOGS.info("=========== XP5S_TC_006_DevSanity============");
		startAdbLog("XP5_Dev_Sanity_06");
		recordVideo("XP5_Dev_Sanity_06");
		String XP5deviceModelNumber=JsonFileReaderAndWriter.primaryDevOperatorReader();
		if(XP5deviceModelNumber.contains("-35.")) {	
			test.log(LogStatus.SKIP, "Test case doesn't support");
		}
		else {
			 MT_XP5Call();
			 disconnectVoiceCall();
	
		test.log(LogStatus.PASS, "Test case status is Passed");
		}
	}
	
	@Test(priority=7,dataProvider="XP5_Dev_Sanity", dataProviderClass=DataProviders.class)
	public void XP5S_TC_007_DevSanity_check_Able_to_Reject_VoiceCall(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException 
	{
		APP_LOGS.info("=========== XP5S_TC_007_DevSanity============");
		startAdbLog("XP5_Dev_Sanity_07");
		recordVideo("XP5_Dev_Sanity_07");
		String XP5deviceModelNumber=JsonFileReaderAndWriter.primaryDevOperatorReader();
		if(XP5deviceModelNumber.contains("-35.")) {	
			test.log(LogStatus.SKIP, "Test case doesn't support");
		}
		else {
			reject_XP5Call();
	
		test.log(LogStatus.PASS, "Test case status is Passed");
		}
	}
	
	@Test(priority=8,dataProvider="XP5_Dev_Sanity", dataProviderClass=DataProviders.class)
	public void XP5S_TC_008_DevSanity_Validate_In_Call_Functionality(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException 
	{
		APP_LOGS.info("=========== XP5S_TC_008_DevSanity============");
		startLog("XP5_Dev_Sanity_08");
		recordVideo("XP5_Dev_Sanity_08");
		String XP5deviceModelNumber=JsonFileReaderAndWriter.primaryDevOperatorReader();
		if(XP5deviceModelNumber.contains("-35.")) {	
			test.log(LogStatus.SKIP, "Test case doesn't support");
		}
		else {
			launch_an_app("phone");
		   inCallFunctionality();
			test.log(LogStatus.PASS, "Test case status is Passed");		
		}
	}
	
	
	@Test(priority=9,dataProvider="XP5_Dev_Sanity", dataProviderClass=DataProviders.class)
	public void XP5S_TC_009_DevSanity_MT_Call_Reject_With_SMS_Notification(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException 
	{
		APP_LOGS.info("=========== XP5S_TC_009_DevSanity============");
		startAdbLog("XP5_Dev_Sanity_09");
		recordVideo("XP5_Dev_Sanity_09");
		String XP5deviceModelNumber=JsonFileReaderAndWriter.primaryDevOperatorReader();
		if(XP5deviceModelNumber.contains("-35.")) {	
			test.log(LogStatus.SKIP, "Test case doesn't support");
		}
		else {
			initiateCallRef();
			rejectWithSMS();
			test.log(LogStatus.PASS, "Test case status is Passed");		
	
	
		}
	}
	
	
	@Test(priority=10,dataProvider="XP5_Dev_Sanity", dataProviderClass=DataProviders.class)
	public void XP5S_TC_010_DevSanity_Send_SMS_During_Ongoing_Voice_Call(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException 
	{
		APP_LOGS.info("=========== XP5S_TC_010_DevSanity============");
		startAdbLog("XP5_Dev_Sanity_10");
		recordVideo("XP5_Dev_Sanity_10");
		clearRecentApps();
		String XP5deviceModelNumber=JsonFileReaderAndWriter.primaryDevOperatorReader();
		if(XP5deviceModelNumber.contains("-35.")) {	
			test.log(LogStatus.SKIP, "Test case doesn't support");
		}
		else {
			launch_an_app("phone");
			sendSMSWhenInCall(data.get("text"));
			test.log(LogStatus.PASS, "Test case status is Passed");	
		}
	}
	
	@Test(priority=11,dataProvider="XP5_Dev_Sanity", dataProviderClass=DataProviders.class)
	public void XP5S_TC_011_DevSanity_Validate_Call_History_Details(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException 
	{
		APP_LOGS.info("=========== XP5S_TC_011_DevSanity============");
		startAdbLog("XP5_Dev_Sanity_11");
		recordVideo("XP5_Dev_Sanity_11");
		String XP5deviceModelNumber=JsonFileReaderAndWriter.primaryDevOperatorReader();
		if(XP5deviceModelNumber.contains("-35.")) {	
			test.log(LogStatus.SKIP, "Test case doesn't support");
		}
		else {
			launch_an_app("phone");
			callHistoryDetails();
			test.log(LogStatus.PASS, "Test case status is Passed");	
		}
	}
	
	
	
	@Test(priority=12,dataProvider="XP5_Dev_Sanity", dataProviderClass=DataProviders.class)
	public void XP5S_TC_012_DevSanity_check_Send_SMS(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException 
	{
		APP_LOGS.info("=========== XP5S_TC_012_DevSanity============");
		startAdbLog("XP5_Dev_Sanity_12");
		recordVideo("XP5_Dev_Sanity_12");
		String XP5deviceModelNumber=JsonFileReaderAndWriter.primaryDevOperatorReader();
		System.out.println(XP5deviceModelNumber);
		if(XP5deviceModelNumber.contains("-35.")) {	
			test.log(LogStatus.SKIP, "Test case doesn't support");
		}
		else {
		launch_an_app("messaging");
		delete_All_Threads();
		composeMsg(data.get("composeMessage"),AllQA.REFERENCEDEVMDN);
		send() ;
		validateMsgSent("Sent SMS");	
		test.log(LogStatus.PASS, "Test case status is Passed");
		}
	}
	
	@Test(priority=13,dataProvider="XP5_Dev_Sanity", dataProviderClass=DataProviders.class)
	public void XP5S_TC_013_DevSanity_check_Send_MultiPage_SMS(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException 
	{
		APP_LOGS.info("=========== XP5S_TC_013_DevSanity============");
		startAdbLog("XP5_Dev_Sanity_13");
		recordVideo("XP5_Dev_Sanity_13");
		String XP5deviceModelNumber=JsonFileReaderAndWriter.primaryDevOperatorReader();
		System.out.println(XP5deviceModelNumber);
		if(XP5deviceModelNumber.contains("-35.")) {	
			test.log(LogStatus.SKIP, "Test case doesn't support");
		}
		else {
		launch_an_app("messaging");
		composeMsg(data.get("composeMessage"),AllQA.REFERENCEDEVMDN);
		send() ;
		validateMsgSent("Sent SMS");	
		test.log(LogStatus.PASS, "Test case status is Passed");
		}
	}
	
	@Test(priority=14,dataProvider="XP5_Dev_Sanity", dataProviderClass=DataProviders.class)
	public void XP5S_TC_014_DevSanity_check_Receive_SMS(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException 
	{
		APP_LOGS.info("=========== XP5S_TC_014_DevSanity============");
		startAdbLog("XP5_Dev_Sanity_14");
		recordVideo("XP5_Dev_Sanity_14");
		String XP5deviceModelNumber=JsonFileReaderAndWriter.primaryDevOperatorReader();
		if(XP5deviceModelNumber.contains("-35.")) {	
			test.log(LogStatus.SKIP, "Test case doesn't support");
		}
		else {
		launch_an_app("messaging");
		sendSMS_fromRefDevice();
		validateMsgSent("received SMS");	
		test.log(LogStatus.PASS, "Test case status is Passed");
		}
	}
	
	@Test(priority=15,dataProvider="XP5_Dev_Sanity", dataProviderClass=DataProviders.class)
	public void XP5S_TC_015_DevSanity_check_SMS_able_to_save_Draft(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException 
	{
		APP_LOGS.info("=========== XP5S_TC_015_DevSanity============");
		startAdbLog("XP5_Dev_Sanity_15");
		recordVideo("XP5_Dev_Sanity_15");
		String XP5deviceModelNumber=JsonFileReaderAndWriter.primaryDevOperatorReader();
		if(XP5deviceModelNumber.contains("-35.")) {	
			test.log(LogStatus.SKIP, "Test case doesn't support");
		}
		else {
	    launch_an_app("messaging");
		composeMsg(data.get("composeMessage"),AllQA.REFERENCEDEVMDN);
		customWait(1000);
		aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
		customWait(3000);
		validateMsgDraft(AllQA.REFERENCEDEVMDN,"SMS");	
		test.log(LogStatus.PASS, "Test case status is Passed");
		}
	}
	
	@Test(priority=16,dataProvider="XP5_Dev_Sanity", dataProviderClass=DataProviders.class)
	public void XP5S_TC_016_DevSanity_check_SMS_able_To_Delete(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException 
	{
		APP_LOGS.info("=========== XP5S_TC_016_DevSanity============");
		startAdbLog("XP5_Dev_Sanity_16");
		recordVideo("XP5_Dev_Sanity_16");
		String XP5deviceModelNumber=JsonFileReaderAndWriter.primaryDevOperatorReader();
		if(XP5deviceModelNumber.contains("-35.")) {	
			test.log(LogStatus.SKIP, "Test case doesn't support");
		}
		else {
	    launch_an_app("messaging");
	    deleteMsg();
	    validateDeleteSMS("SMS");
		test.log(LogStatus.PASS, "Test case status is Passed");
		}
	}
	
	
	@Test(priority=17,dataProvider="XP5_Dev_Sanity", dataProviderClass=DataProviders.class)
	public void XP5S_TC_017_DevSanity_check_MMS_able_To_Send(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException 
	{
		APP_LOGS.info("=========== XP5S_TC_017_DevSanity============");
		startAdbLog("XP5_Dev_Sanity_17");
		recordVideo("XP5_Dev_Sanity_17");
		String XP5deviceModelNumber=JsonFileReaderAndWriter.primaryDevOperatorReader();
		if(XP5deviceModelNumber.contains("-35.")) {	
			test.log(LogStatus.SKIP, "Test case doesn't support");
		}
		else {
	    launch_an_app("messaging");
	    navigateTo_NewMessage();
		navigateAttachOthers();
		clickOnAttach_Options(Locators_DevSanity.capturePicture);
		capturePic_ADD();
		validate_AnyAdded_Attachment(Locators_DevSanity.image_Attachment);
		enter_ToField(data.get("cellNo"));
		type_Message(data.get("typeMessage"));
		send_SMS();
		press_BackKey();
		validateMsgSent("Sent MMS");	
		press_BackKey();
	
		test.log(LogStatus.PASS, "Test case status is Passed");
		}
	}
	
	@Test(priority=18,dataProvider="XP5_Dev_Sanity", dataProviderClass=DataProviders.class)
	public void XP5S_TC_018_DevSanity_check_MMS_able_to_save_Draft(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException 
	{
		APP_LOGS.info("=========== XP5S_TC_018_DevSanity============");
		startAdbLog("XP5_Dev_Sanity_18");
		recordVideo("XP5_Dev_Sanity_18");
		String XP5deviceModelNumber=JsonFileReaderAndWriter.primaryDevOperatorReader();
		if(XP5deviceModelNumber.contains("-35.")) {	
			test.log(LogStatus.SKIP, "Test case doesn't support");
		}
		else {
	    launch_an_app("messaging");
	    navigateTo_NewMessage();
		navigateAttachOthers();
		clickOnAttach_Options(Locators_DevSanity.capturePicture);
		capturePic_ADD();
		validate_AnyAdded_Attachment(Locators_DevSanity.image_Attachment);
		enter_ToField(data.get("cellNo"));
		type_Message(data.get("typeMessage"));
		customWait(1000);
		aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
		customWait(3000);
		validateMsgDraft("9686138707","MMS");	
		test.log(LogStatus.PASS, "Test case status is Passed");
		}
	}
	
	@Test(priority=19,dataProvider="XP5_Dev_Sanity", dataProviderClass=DataProviders.class)
	public void XP5S_TC_019_DevSanity_check_MMS_able_to_Delete(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException 
	{
		APP_LOGS.info("=========== XP5S_TC_019_DevSanity============");
		startAdbLog("XP5_Dev_Sanity_19");
		recordVideo("XP5_Dev_Sanity_19");
		String XP5deviceModelNumber=JsonFileReaderAndWriter.primaryDevOperatorReader();
		if(XP5deviceModelNumber.contains("-35.")) {	
			test.log(LogStatus.SKIP, "Test case doesn't support");
		}
		else {		
			    launch_an_app("messaging");
			    deleteMsg();
			    validateDeleteSMS("MMS");
		test.log(LogStatus.PASS, "Test case status is Passed");
		}
	}
	
	
	@Test(priority=20,dataProvider="XP5_Dev_Sanity", dataProviderClass=DataProviders.class)
	public void XP5S_TC_020_DevSanity_Connect_WiFi_SSID_Browse(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException {

		APP_LOGS.info("=========== XP5S_TC_020_DevSanity============");
		startAdbLog("XP5_Dev_Sanity_20");
		recordVideo("XP5_Dev_Sanity_20");
		clearRecentApps();
		String XP5deviceModelNumber=JsonFileReaderAndWriter.primaryDevOperatorReader();
		if(XP5deviceModelNumber.contains("-35.")) {	
			test.log(LogStatus.SKIP, "Test case doesn't support");
		}
		else {
			launch_an_app("settings");
			disableCellularData();
			back();
			launch_an_app("settings");
			ConnectSecuredWifi();
			validateDisableEnableWiFi();
			test.log(LogStatus.PASS, "Test case status is Passed");
		}
	
	}
	
	@Test(priority=21,dataProvider="XP5_Dev_Sanity", dataProviderClass=DataProviders.class)
	public void XP5S_TC_021_DevSanity_Disable_Enable_WiFi_DeviceSettings(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException {

		APP_LOGS.info("=========== XP5S_TC_021_DevSanity============");
		startAdbLog("XP5_Dev_Sanity_21");
		recordVideo("XP5_Dev_Sanity_21");
		clearRecentApps();
		String XP5deviceModelNumber=JsonFileReaderAndWriter.primaryDevOperatorReader();
		if(XP5deviceModelNumber.contains("-35.")) {	
			test.log(LogStatus.SKIP, "Test case doesn't support");
		}
		else {
			launch_an_app("settings");
			disableCellularData();
			validateDisableEnableWiFi();
			test.log(LogStatus.PASS, "Test case status is Passed");
		}
	
	}

	
	@Test(priority=22,dataProvider="XP5_Dev_Sanity", dataProviderClass=DataProviders.class)
	public void XP5S_TC_022_DevSanity_Disable_Enable_WiFi_QuickSettings(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException {

		APP_LOGS.info("=========== XP5S_TC_022_DevSanity============");
		startAdbLog("XP5_Dev_Sanity_22");
		recordVideo("XP5_Dev_Sanity_22");
		String XP5deviceModelNumber=JsonFileReaderAndWriter.primaryDevOperatorReader();
		if(XP5deviceModelNumber.contains("-35.")) {	
			test.log(LogStatus.SKIP, "Test case doesn't support");
		}
		else {
			selectQuickSettingPanel();
			validateEnableDisable(Locators_DevSanity.wifi,"Wi-Fi",Locators_DevSanity.enabled_wifi,Locators_DevSanity.disabled_wifi);
			test.log(LogStatus.PASS, "Test case status is Passed");
		}
	
	}

	
	@Test(priority=23,dataProvider="XP5_Dev_Sanity", dataProviderClass=DataProviders.class)
	public void XP5S_TC_023_DevSanity_check_Browser_using_MobileData(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException 
	{
		APP_LOGS.info("=========== XP5S_TC_023_DevSanity============");
		startAdbLog("XP5_Dev_Sanity_17");
		recordVideo("XP5_Dev_Sanity_17");
		String XP5deviceModelNumber=JsonFileReaderAndWriter.primaryDevOperatorReader();
		if(XP5deviceModelNumber.contains("-35.")) {	
			test.log(LogStatus.SKIP, "Test case doesn't support");
		}
		else {
			enableData();
			launch_an_app("browser");		
			validateBrowseInternet();
		test.log(LogStatus.PASS, "Test case status is Passed");
		}
	}
	
	
	
	@Test(priority=24,dataProvider="XP5_Dev_Sanity", dataProviderClass=DataProviders.class)
	public void XP5S_TC_024_DevSanity_Disable_Enable_BT_DeviceSettings(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException 
	{
		APP_LOGS.info("=========== XP5S_TC_024_DevSanity============");
		startAdbLog("XP5_Dev_Sanity_24");
		recordVideo("XP5_Dev_Sanity_24");
		launch_an_app("settings");
		validateDisableEnableBT();
		test.log(LogStatus.PASS, "Test case status is Passed");
	}
	
	@Test(priority=25,dataProvider="XP5_Dev_Sanity", dataProviderClass=DataProviders.class)
	public void XP5S_TC_025_DevSanity_Scan_BT_NearBy_Devices(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException 
	{
		APP_LOGS.info("=========== XP5S_TC_025_DevSanity============");
		startAdbLog("XP5_Dev_Sanity_25");
		recordVideo("XP5_Dev_Sanity_25");
		enable_BT_RefDevice();
		launch_an_app("settings");
		validateScanBluetooth();
		enable_BT_RefDevice();
		test.log(LogStatus.PASS, "Test case status is Passed");
	}

	
	@Test(priority=26,dataProvider="XP5_Dev_Sanity", dataProviderClass=DataProviders.class)
	public void XP5S_TC_026_DevSanity_check_Charging_Over_USB(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException 
	{
		APP_LOGS.info("=========== XP5S_TC_026_DevSanity============");
		startAdbLog("XP5_Dev_Sanity_26");
		recordVideo("XP5_Dev_Sanity_26");
		launch_an_app("settings");
		ScrollToElement(Locators_DevSanity.settings_frame, "About phone");
		validate_phone_Charging();	
		test.log(LogStatus.PASS, "Test case status is Passed");
	}
	
	@Test(priority=27,dataProvider="XP5_Dev_Sanity", dataProviderClass=DataProviders.class)
	public void XP5S_TC_027_DevSanity_check_SD_Card_Detected(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException 
	{
		APP_LOGS.info("=========== XP5S_TC_027_DevSanity============");
		startAdbLog("XP5_Dev_Sanity_27");
		recordVideo("XP5_Dev_Sanity_27");		
		launch_an_app("fileExplorer");
		navigateToSDCard();
		validateSDCardDetection();
		test.log(LogStatus.PASS, "Test case status is Passed");
	}

	@Test(priority=28,dataProvider="XP5_Dev_Sanity", dataProviderClass=DataProviders.class)
	public void XP5S_TC_028_DevSanity_check_SD_Card_Size(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException 
	{
		APP_LOGS.info("=========== XP5S_TC_028_DevSanity============");
		startAdbLog("XP5_Dev_Sanity_28");
		recordVideo("XP5_Dev_Sanity_28");
		launch_an_app("fileExplorer");
		selectProperties();
		fetchSDcardSize();
		test.log(LogStatus.PASS, "Test case status is Pass");
	}
	

	@Test(priority=29,dataProvider="XP5_Dev_Sanity", dataProviderClass=DataProviders.class)
	public void XP5S_TC_029_DevSanity_checking_Device_IMEI(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException 
	{
		APP_LOGS.info("=========== XP5S_TC_029_DevSanity============");
		startAdbLog("XP5_Dev_Sanity_29");
		recordVideo("XP5_Dev_Sanity_29");
		String XP5deviceModelNumber=JsonFileReaderAndWriter.primaryDevOperatorReader();
		if(XP5deviceModelNumber.contains("-35.")) {	
			test.log(LogStatus.SKIP, "Test case doesn't support");
		}
		else {
		deviceIMEI();
		validateIMEIdisplayed();	
		test.log(LogStatus.PASS, "Test case status is Passed");
		}
	}
	
	
	@Test(priority=30,dataProvider="XP5_Dev_Sanity", dataProviderClass=DataProviders.class)
	public void XP5S_TC_030_Add_Contact_In_Phonebook_With_All_Fields(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException 
	{
		APP_LOGS.info("=========== XP5S_TC_030_DevSanity============");
		startAdbLog("XP5_Dev_Sanity_30");
		recordVideo("XP5_Dev_Sanity_30");
		String XP5deviceModelNumber=JsonFileReaderAndWriter.primaryDevOperatorReader();
		if(XP5deviceModelNumber.contains("-35.")) {	
			test.log(LogStatus.SKIP, "Test case doesn't support");
		}
		else {
			launch_an_app("contacts");
			deleteIfContactsPresent();
			addContactWithAllFields(data.get("Name prefix"), data.get("First name"), data.get("Middle name"), data.get("Last name"), data.get("Name suffix"), data.get("Phonetic last name"), data.get("Phonetic middle name"), data.get("Phonetic first name"), data.get("Nickname"), data.get("Company"), data.get("Title"),data.get("SIP"), data.get("Email"), data.get("Address"), data.get("IM"), data.get("Website"), data.get("Notes"));
			customWait(2000);
		    test.log(LogStatus.PASS, "Test case status is Passed");
		}
	}
	
	
	@Test(priority=31,dataProvider="XP5_Dev_Sanity", dataProviderClass=DataProviders.class)
	public void XP5S_TC_031_Add_Contact_In_SIM_Memory_With_Name_And_Phone_Field(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException 
	{
		APP_LOGS.info("=========== XP5S_TC_031_DevSanity============");
		startAdbLog("XP5_Dev_Sanity_31");
		recordVideo("XP5_Dev_Sanity_31");
		String XP5deviceModelNumber=JsonFileReaderAndWriter.primaryDevOperatorReader();
		if(XP5deviceModelNumber.contains("-35.")) {	
			test.log(LogStatus.SKIP, "Test case doesn't support");
		}
		else {
			launch_an_app("contacts");
			addContactAndCopyToSIM(data.get("name"));
		    test.log(LogStatus.PASS, "Test case status is Passed");
		}
	}
	
	@Test(priority=32,dataProvider="XP5_Dev_Sanity", dataProviderClass=DataProviders.class)
	public void XP5S_TC_032_Delete_Contact(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException 
	{
		APP_LOGS.info("=========== XP5S_TC_032_DevSanity============");
		startAdbLog("XP5_Dev_Sanity_32");
		recordVideo("XP5_Dev_Sanity_32");
		String XP5deviceModelNumber=JsonFileReaderAndWriter.primaryDevOperatorReader();
		if(XP5deviceModelNumber.contains("-35.")) {	
			test.log(LogStatus.SKIP, "Test case doesn't support");
		}
		else {
			launch_an_app("contacts");
			deleteContact(data.get("name1"), data.get("name2"));
		    test.log(LogStatus.PASS, "Test case status is Passed");
		}
	}
	
	@Test(priority=33,dataProvider="XP5_Dev_Sanity", dataProviderClass=DataProviders.class)
	public void XP5S_TC_033_DevSanity_check_back_Camera_with_Picture(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException 
	{
		APP_LOGS.info("=========== XP5S_TC_033_DevSanity============");
		startAdbLog("XP5S_Dev_Sanity_33");
		recordVideo("XP5S_Dev_Sanity_33");	
		cameraPic();
		validateBackCamera("android.hardware.action.NEW_PICTURE","Camera","XP5S_Dev_Sanity_33");
		test.log(LogStatus.PASS, "Test case status is Passed");
	}
	
	@Test(priority=34,dataProvider="XP5_Dev_Sanity", dataProviderClass=DataProviders.class)
	public void XP5S_TC_034_DevSanity_check_back_Camera_with_Video(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException 
	{
		APP_LOGS.info("=========== XP5S_TC_034_DevSanity============");
		startAdbLog("XP5S_Dev_Sanity_34");
		recordVideo("XP5S_Dev_Sanity_34");	
		launch_an_app("gallery");
		takeCameraVideo(1);
		press_Enter();
		validateBackCamera("android.hardware.action.NEW_VIDEO","Camera","XP5S_Dev_Sanity_34");
		test.log(LogStatus.PASS, "Test case status is Passed");
	}
	
	@Test(priority=35,dataProvider="XP5_Dev_Sanity", dataProviderClass=DataProviders.class)
	public void XP5S_TC_035_DevSanity_check_Sound_Recorder(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException 
	{
		APP_LOGS.info("=========== XP5S_TC_035_DevSanity============");
		startAdbLog("XP5_Dev_Sanity_35");
		recordVideo("XP5_Dev_Sanity_35");	
		
		soundRecorderLaunch();
		deleteSavedRecorder();
		recordSound();
		validateRecordedSoundSaved();		
		test.log(LogStatus.PASS, "Test case status is Passed");
	}
	
	@Test(priority=36,dataProvider="XP5_Dev_Sanity", dataProviderClass=DataProviders.class)
	public void XP5S_TC_036_DevSanity_check_able_To_Play_Music(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException 
	{
		APP_LOGS.info("=========== XP5S_TC_036_DevSanity============");
		startAdbLog("XP5_Dev_Sanity_36");
		recordVideo("XP5_Dev_Sanity_36");	
		playMusic();	
		validate_music_player();
		test.log(LogStatus.PASS, "Test case status is Passed");
	}
	
	
	
	@Test(priority=37,dataProvider="XP5_Dev_Sanity", dataProviderClass=DataProviders.class)
	public void XP5S_TC_037_DevSanity_check_Able_to_Change_SleepTime(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException 
	{
		APP_LOGS.info("=========== XP5S_TC_037_DevSanity============");
		startAdbLog("XP5_Dev_Sanity_37");
		recordVideo("XP5_Dev_Sanity_37");	
		launch_an_app("settings");
		navigateToSettingsFeature(Locators_DevSanity.display_feature,"Display");			
		validateChaneSleepTime();
		
	}
	
	
	@Test(priority=38,dataProvider="XP5_Dev_Sanity", dataProviderClass=DataProviders.class)
	public void XP5S_TC_038_DevSanity_check_Able_to_Change_Ringtone(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException 
	{
		APP_LOGS.info("=========== XP5S_TC_038_DevSanity============");
		startAdbLog("XP5_Dev_Sanity_38");
		recordVideo("XP5_Dev_Sanity_38");	
		launch_an_app("settings");
		navigateToSettingsFeature(Locators_DevSanity.sound_feature,"Sound");			
		ValidatechangeRingtone();		
	}
	
	
	
	@Test(priority=39,dataProvider="XP5_Dev_Sanity", dataProviderClass=DataProviders.class)
	public void XP5S_TC_039_DevSanity_check_Able_to_Enable_Disable_Location(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException 
	{
		APP_LOGS.info("=========== XP5S_TC_039_DevSanity============");
		startAdbLog("XP5_Dev_Sanity_39");
		recordVideo("XP5_Dev_Sanity_39");	
		launch_an_app("settings");
		navigateToSettingsFeature(Locators_DevSanity.Location_feature,"Location");
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
	}
	
	
	@Test(priority=40,dataProvider="XP5_Dev_Sanity", dataProviderClass=DataProviders.class)
	public void XP5S_TC_040_Add_An_calendar_Event(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException {

		APP_LOGS.info("===========XP5S_Dev_Sanity_40============");
		startAdbLog("XP5S_Dev_Sanity_40");
		checkOperatorAndlaunchCalendar();
		minWait();
		addCalendarEvent(data.get("eventName"),data.get("location"));
		test.log(LogStatus.PASS, "Test case status is Passed");		
	}
	
	@Test(priority=41,dataProvider="XP5_Dev_Sanity", dataProviderClass=DataProviders.class)
	public void XP5S_TC_041_Add_An_Alarm_Event(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException {

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
	public void XP5S_TC_042_Validate_Snooze_And_Dismiss_Alarm(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException {

		
		APP_LOGS.info("===========XP5S_TC_042============");
		startAdbLog("XP5S_Dev_Sanity_42");
		launchClock();
		minWait();
		snoozeTheAlarm();
		minWait();
		dismissTheAlarm();
		test.log(LogStatus.PASS, "Test case status is Passed");		
	}
	
	
	@Test(priority=43,dataProvider="XP5_Dev_Sanity", dataProviderClass=DataProviders.class)
	public void XP5S_TC_043_Validate_LaunchScout_All_Apps(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException {

		APP_LOGS.info("===========XP5S_TC_043============");
		startAdbLog("XP5S_TC_043");
		launch_an_app("scout");
		validateScoutAppLaunch();
		validatelaunchAllSCoutApps();		
		test.log(LogStatus.PASS, "Test case status is Passed");		
	}
	
	
	
	
	
}
