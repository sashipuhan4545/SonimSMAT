package com.xp3.Test;

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
import com.aosp.Utils.DataProviders;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTestInterruptedException;
import com.relevantcodes.extentreports.LogStatus;
import com.xp3.Utils.Locators_BaseUtil;
import com.xp3.Utils.Locators_DevSanity;
import com.xp3.Utils.Locators_Sanity;
import com.xp3.Utils.XP3_Device_Sanity_Util;
import com.xp5S.util.ExcelConstants;
import com.xp5S.util.ExcelReader;
import com.xp5S.util.GetMethods;
import com.xp5S.util.appiumService;
import io.appium.java_client.android.AndroidKeyCode;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class XP3_Device_Sanity extends XP3_Device_Sanity_Util {

	public ExcelReader excel;
	Properties properties;
	public static ExtentTestInterruptedException testexception;
	public String msg= "Th1$ me$$age c0nt@1n$ 5pec1@l ch@*@c%er$ & n^mer@1$#";

	@BeforeSuite
	public void beforeSuite() throws IOException, InterruptedException, ParseException {

		extent = new ExtentReports("src/test/resources/extentreport/XP3_Device_Sanity.html", true);
		fetch_Devices_Details();
		try {
			appiumService.TOTAL_NUM_OF_TESTCASES=GetMethods.TotalTestcase("XP3_TC", this.getClass());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	@BeforeMethod()
	public  void beforeMethod(Method method) throws InterruptedException {

		test = extent.startTest( (this.getClass().getSimpleName() +" :: "+  method.getName()),method.getName()); //Test Case Start Here
		test.assignAuthor(""); //Test Script Author Name
		extent.loadConfig(new File(System.getProperty("user.dir")+"//ReportsConfig.xml"));
		extent.addSystemInfo("Appium Version", "1.2.7").addSystemInfo("Environment", "TEST");
	}

	@AfterMethod()
	public void tearDown(ITestResult result,Method method) throws IOException, InterruptedException	{

		System.out.println("TEAR DOWN---------------");
		if(result.getStatus()==ITestResult.SUCCESS_PERCENTAGE_FAILURE)	{
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
	public void setupSys() throws MalformedURLException, InterruptedException, FileNotFoundException, AWTException {
		properties=loadDriverAndProperties();
		Locators_Sanity loc=new Locators_Sanity(aDriver);
		Locators_BaseUtil loc1 =new Locators_BaseUtil(aDriver);
		Locators_DevSanity loc2 = new Locators_DevSanity(aDriver);
		PageFactory.initElements(new AppiumFieldDecorator(getDriver()),loc);
		PageFactory.initElements(new AppiumFieldDecorator(getDriver()),loc1);
		PageFactory.initElements(new AppiumFieldDecorator(getDriver()),loc2);
		excel=new ExcelReader(ExcelConstants.XP5S_XL_PATH);
	}


	//============================================================================================================================================
	//======================================================= Test cases Start Here ==============================================================
	//============================================================================================================================================

	
	@Test(priority=1,dataProvider="XP3_Device_Sanity", dataProviderClass=DataProviders.class)
	public void XP3_TC_001_Sanity_LaunchApp_Menu_and_LaunchAllApp_from_ApplicationMenu(Hashtable<String, String> data) throws AWTException, InterruptedException, IOException, ParseException {

		APP_LOGS.info("=========== XP3_TC_001_Device_Sanity============");
		SoftAssert s = softAssert();
		//startAdbLog("XP3_DeviceSanity_001");
		clearRecentApps();
		minWait();
		validateAppMenuLaunch(s);
		minWait();
		validateLaunch_All_Applications(s);
		customWait(3000);
		s.assertAll();
	}

	@Test(priority=2,dataProvider="XP3_Device_Sanity", dataProviderClass=DataProviders.class)
	public void XP3_TC_002_Sanity_check_Baseband_Version(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException {

		APP_LOGS.info("=========== XP3_TC_002_Device_Sanity============");
		SoftAssert s = softAssert();
		//startAdbLog("XP3_DeviceSanity_002");
		navigateToApplication("Settings");
		checkBasebandVersion(s);
		s.assertAll();
	}

	@Test(priority=3,dataProvider="XP3_Device_Sanity", dataProviderClass=DataProviders.class)
	public void XP3_TC_003_Sanity_check_Network_Type(Hashtable<String, String> data) throws AWTException, InterruptedException, IOException, ParseException {

		APP_LOGS.info("=========== XP3_TC_003_Device_Sanity============");
		SoftAssert s= softAssert();
		//startAdbLog("XP3_DeviceSanity_003");
		navigateToApplication(data.get("Application"));
		if(p_b_No.contains("-10.")) {
			changeNetworkType(s);
		} else {
			changeNetworkTypeSP(s);
		}
		s.assertAll();
	}

	@Test(priority=4,dataProvider="XP3_Device_Sanity", dataProviderClass=DataProviders.class)
	public void XP3_TC_004_Sanity_check_Able_to_Make_VoiceCall(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException {

		APP_LOGS.info("=========== XP3_TC_004_QuickSanity============");
		//startAdbLog("XP3_DeviceSanity_004");
		MO_CALL_Sanity();
		test.log(LogStatus.PASS, "Test case status is Passed");
	}

	@Test(priority=5,dataProvider="XP3_Device_Sanity", dataProviderClass=DataProviders.class)
	public void XP3_TC_005_Sanity_check_Able_to_Receive_VoiceCall(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException {

		APP_LOGS.info("=========== XP3_TC_005_DevSanity============");
		//startAdbLog("XP3_DeviceSanity_005");
		MT_CALL_Sanity();
		test.log(LogStatus.PASS, "Test case status is Passed");
	}

	@Test(priority=6,dataProvider="XP3_Device_Sanity", dataProviderClass=DataProviders.class)
	public void XP3_TC_006_Sanity_check_Able_to_Reject_VoiceCall(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException {

		APP_LOGS.info("=========== XP3_TC_006_DevSanity============");
		//startAdbLog("XP3_DeviceSanity_006");
		MT_CALL_REJECT_Sanity();
		test.log(LogStatus.PASS, "Test case status is Passed");
	}

	@Test(priority=7,dataProvider="XP3_Device_Sanity", dataProviderClass=DataProviders.class)
	public void XP3_TC_007_Sanity_Validate_In_Call_Functionality(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException {

		APP_LOGS.info("=========== XP3_TC_007_DevSanity============");
		//startLog("XP3_DeviceSanity_007");
		launch_an_app("phone");
		inCallFunctionality();
		test.log(LogStatus.PASS, "Test case status is Passed");		
	}

	@Test(priority=8,dataProvider="XP3_Device_Sanity", dataProviderClass=DataProviders.class)
	public void XP3_TC_008_Sanity_Send_SMS_During_Ongoing_Voice_Call(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException {

		APP_LOGS.info("=========== XP3_TC_008_Sanity============");
		SoftAssert s = softAssert();
		//startAdbLog("XP3_DeviceSanity_008");
		launch_an_app("phone");
		minWait();
		sendSMSWhenInCall(s,data.get("MessageBody"));
		test.log(LogStatus.PASS, "Test case status is Passed");
		s.assertAll();
	}

	@Test(priority=9,dataProvider="XP3_Device_Sanity", dataProviderClass=DataProviders.class)
	public void XP3_TC_009_Sanity_Call_Reject_With_SMS(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException {

		APP_LOGS.info("=========== XP3_TC_009_Device_Sanity============");
		startAdbLog("XP3_DeviceSanity_009");
		//navigateToApplication(data.get("Application"));
		CallRejectWithSMS();
		test.log(LogStatus.PASS, "Test case status is Passed");
	}

	@Test(priority=10,dataProvider="XP3_Device_Sanity", dataProviderClass=DataProviders.class)
	public void XP3_TC_010_Sanity_MO_VoiceCall_from_CallHistory(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException {

		APP_LOGS.info("=========== XP3_TC_010_Device_Sanity============");
		//startAdbLog("XP3_DeviceSanity_010");
		navigateToApplication(data.get("Application"));
		makeCallFromHistory();
		test.log(LogStatus.PASS, "Test case status is Passed");
	}

	@Test(priority=11,dataProvider="XP3_Device_Sanity", dataProviderClass=DataProviders.class)
	public void XP3_TC_011_Sanity_Call_History_details(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException {

		APP_LOGS.info("=========== XP3_TC_011_Device_Sanity============");
		//startAdbLog("XP3_Sanity_011");
		launch_an_app("phone");
		clearCallLogs();
		callHistoryDetails();
		test.log(LogStatus.PASS, "Test case status is Passed");
	}

	@Test(priority=12,dataProvider="XP3_Device_Sanity", dataProviderClass=DataProviders.class)
	public void XP3_TC_012_Sanity_check_Send_SMS(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException {

		APP_LOGS.info("=========== XP3_TC_012_Device_Sanity============");
		SoftAssert s = softAssert();
		//startAdbLog("XP3_DeviceSanity_012");
		launch_an_app("messaging");
		delete_All_Threads();
		composeMsg(data.get("composeMessage"),refNum);
		send();
		validateMsgSent(s);
		s.assertAll();
	}

	@Test(priority=13,dataProvider="XP3_Device_Sanity", dataProviderClass=DataProviders.class)
	public void XP3_TC_013_Sanity_Send_SMS_SavedContact(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException {

		APP_LOGS.info("=========== XP3_TC_013_Device_Sanity============");
		SoftAssert s = softAssert();
		//startAdbLog("XP3_DeviceSanity_013");
		AddContactFromHistory(data.get("contactName"));
		sendSMSContact(data.get("contactName"), data.get("MessageBody"));
		validateSendMessage(s);
		s.assertAll();
	}

	@Test(priority=14,dataProvider="XP3_Device_Sanity", dataProviderClass=DataProviders.class)
	public void XP3_TC_014_Sanity_Send_SMS_With_AlphaNumeric_SpecialCharacters(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException {

		APP_LOGS.info("=========== XP3_TC_014_Device_Sanity============");
		SoftAssert s= softAssert();
		//startAdbLog("XP3_DeviceSanity_014");
		//AddContactFromHistory(data.get("contactName"));
		sendSMSContact(data.get("contactName"), data.get("MessageBody"));
		validateSendMessage(s);
		DeleteContact(data.get("contactName"));
		s.assertAll();
	}

	@Test(priority=15,dataProvider="XP3_Device_Sanity", dataProviderClass=DataProviders.class)
	public void XP3_TC_015_Sanity_check_Send_MultiPage_SMS(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException {

		APP_LOGS.info("=========== XP3_TC_015_Device_Sanity============");
		SoftAssert s= softAssert();
		//startAdbLog("XP3_DeviceSanity_015");
		launch_an_app("messaging");
		composeMsg(data.get("composeMessage"),refNum);
		send() ;
		validateMsgSent(s);	
		s.assertAll();
	}

	@Test(priority=16,dataProvider="XP3_Device_Sanity", dataProviderClass=DataProviders.class)
	public void XP3_TC_016_Sanity_Save_SMS_as_Draft(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException {

		APP_LOGS.info("=========== XP3_TC_016_Device_Sanity============");
		//startAdbLog("XP3_DeviceSanity_016");
		navigateToApplication(data.get("Application"));
		saveMessageDraft(data.get("MessageBody"));
	}

	@Test(priority=17,dataProvider="XP3_Device_Sanity", dataProviderClass=DataProviders.class)
	public void XP3_TC_017_Sanity_Send_MMS(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException {

		APP_LOGS.info("=========== XP3_TC_017_Sanity_Send_MMS============");
		SoftAssert s= softAssert();
		//startAdbLog("XP3_Sanity_017");
		launch_an_app("messaging");
		navigateTo_NewMessage();
		navigateAttachOthers();
		clickOnAttach_Options(Locators_Sanity.capturePicture);
		capturePic_ADD();
		validate_AnyAdded_Attachment(Locators_Sanity.image_Attachment1);
		enter_ToField(refNum);
		type_Message(data.get("composeMessage"));
		send() ;
		validateMsgSent(s);
		press_BackKey();
		test.log(LogStatus.PASS, "Test case status is Passed");
	}

	@Test(priority=18,dataProvider="XP3_Device_Sanity", dataProviderClass=DataProviders.class)
	public void XP3_TC_018_Sanity_Delete_MMS(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException {

		APP_LOGS.info("=========== XP3_TC_018_Sanity_Delete_MMS============");
		//startAdbLog("XP3_Sanity_018");
		launch_an_app("messaging");
		deleteMsg();
		validateDeleteSMS("MMS");
		test.log(LogStatus.PASS, "Test case status is Passed");
	}

	@Test(priority=19,dataProvider="XP3_Device_Sanity", dataProviderClass=DataProviders.class)
	public void XP3_TC_019_Sanity_Create_Contact_with_all_Fields_In_PhoneMemory(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException {

		APP_LOGS.info("=========== XP3_TC_019_Device_Sanity============");
		//startAdbLog("XP3_DeviceSanity_019");
		navigateToApplication(data.get("Application"));
		createContactAllFields(data.get("Name"), data.get("PhoneticLast"),data.get("PhoneticMiddle"), data.get("PhoneticFirst"), data.get("Nickname"), data.get("Company"), data.get("Title"), data.get("Phone"), data.get("SIP"), data.get("Email"), data.get("Address"), data.get("IM"), data.get("Website"), data.get("Notes"));
		//DeleteContact(data.get("Name"));
	}

	@Test(priority=20,dataProvider="XP3_Device_Sanity", dataProviderClass=DataProviders.class)
	public void XP3_TC_020_Sanity_Delete_Saved_Contacts(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException {

		APP_LOGS.info("=========== XP3_TC_020_Device_Sanity============");
		//startAdbLog("XP3_DeviceSanity_020");
		navigateToApplication(data.get("Application"));
		deleteContact(data.get("Name"));
		test.log(LogStatus.PASS, "Test case status is Passed");
	}

	@Test(priority=21,dataProvider="XP3_Device_Sanity", dataProviderClass=DataProviders.class)
	public void XP3_TC_021_Sanity_Enable_Disable_MobileData_DeviceSettings(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException {

		APP_LOGS.info("=========== XP3_TC_021_Sanity_Enable_Disable_MobileData_DeviceSettings============");
		//startAdbLog("XP3_Sanity_021");
		launch_an_app("settings");
		if(p_b_No.contains("-10.")) {
			enableData();
		} else {
			enableData_SP();
		}
		launch_an_app("browser");	
		validateBrowseInternet();
		test.log(LogStatus.PASS, "Test case status is Passed");
	}

	@Test(priority=22,dataProvider="XP3_Device_Sanity", dataProviderClass=DataProviders.class)
	public void XP3_TC_022_Sanity_Connection_Secured_Wifi_Enable_Disable_Wifi_DeviceSettings(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException {

		APP_LOGS.info("=========== XP3_TC_022_Sanity_Connection_Secured_Wifi_Enable_Disable_Wifi_DeviceSettings============");
		//startAdbLog("XP3_Sanity_022");
		clearRecentApps();
		launch_an_app("settings");
		if(p_b_No.contains("-10.")) {
			disableCellularData();
		} else {
			disableCellularData_SP();
		}
		//back();
		//launch_an_app("settings");
		ConnectSecuredWifi();
		minWait();
		validateDisableEnableWiFi();
		//test.log(LogStatus.PASS, "Test case status is Passed");
	}

	@Test(priority=23,dataProvider="XP3_Device_Sanity", dataProviderClass=DataProviders.class)
	public void XP3_TC_023_Sanity_check_Enable_Disable_AirplaneMode(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException {

		APP_LOGS.info("=========== XP3_TC_023_Sanity_check_Enable_Disable_AirplaneMode ============");
		//startAdbLog("XP3_Sanity_23");
		clearRecentApps();
		enableData();
		enableAirplaneMode();
		//enableSwitch(Locators_DevSanity.enabled_Airplane,Locators_DevSanity.disabled_Airplane,Locators_DevSanity.switch_widget);
		customWait(2000);
		launch_an_app("browser");
		customWait(5000);
		validateAirplaneSB();
		test.log(LogStatus.PASS, "Test case status is Passed");
	}

	@Test(priority=24,dataProvider="XP3_Device_Sanity", dataProviderClass=DataProviders.class)
	public void XP3_TC_024_Sanity_Enable_Disable_Bluetooth_Devicesettings(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException {

		APP_LOGS.info("=========== XP3_TC_024_Device_Sanity============");
		//startAdbLog("XP3_DeviceSanity_024");
		navigateToApplication(data.get("Application"));
		//enableBluetooth();
		scanForDevices();
		disableBluetooth();
		test.log(LogStatus.PASS, "Test case status is Passed");
	}

	@Test(priority=25,dataProvider="XP3_Device_Sanity", dataProviderClass=DataProviders.class)
	public void XP3_TC_025_Sanity_Enable_Disable_Location_Devicesettings(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException {

		APP_LOGS.info("=========== XP3_TC_025_Sanity_Enable_Disable_Location_Devicesettings============");
		//startAdbLog("XP3_Sanity_025");
		clearRecentApps();
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

	@Test(priority=26,dataProvider="XP3_Device_Sanity", dataProviderClass=DataProviders.class)
	public void XP3_TC_026_Sanity_Enable_Disable_WiFi_QuickSettings(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException {

		APP_LOGS.info("=========== XP3_TC_026_Sanity_Enable_Disable_WiFi_QuickSettings============");
		//startAdbLog("XP3_Sanity_026");
		selectQuickSettingPanel();
		//validateEnableDisable(Locators_Sanity.wifi,"Wi-Fi",Locators_Sanity.enabled_wifi,Locators_Sanity.disabled_wifi);
		validateQuickSetting("Wi-Fi");
		test.log(LogStatus.PASS, "Test case status is Passed");
	}

	@Test(priority=27,dataProvider="XP3_Device_Sanity", dataProviderClass=DataProviders.class)
	public void XP3_TC_027_Sanity_Enable_Disable_Bluetooth_QuickSettings(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException {

		APP_LOGS.info("=========== XP3_TC_027_Sanity_Enable_Disable_Bluetooth_QuickSettings============");
		//startAdbLog("XP3_Sanity_027");
		selectQuickSettingPanel();
		//validateEnableDisable(Locators_Sanity.bluetooth,"Bluetooth",Locators_Sanity.enabled_Bt,Locators_Sanity.disabled_Bt);
		validateQuickSetting("Bluetooth");
		test.log(LogStatus.PASS, "Test case status is Passed");
	}

	@Test(priority=28,dataProvider="XP3_Device_Sanity", dataProviderClass=DataProviders.class)
	public void XP3_TC_028_Sanity_Enable_Disable_Airplane_Mode_QuickSettings(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException {

		APP_LOGS.info("=========== XP3_TC_028_Sanity_Enable_Disable_AirplaneMode_QuickSettings============");
		//startAdbLog("XP3_Sanity_028");
		selectQuickSettingPanel();
		scrollText("Airplane mode");
		//validateEnableDisable(Locators_Sanity.Flightmode,"Airplane Mode",Locators_Sanity.enabled_FlightMode,Locators_Sanity.disabled_Flightmode);		
		validateQuickSetting("Airplane mode");
		test.log(LogStatus.PASS, "Test case status is Passed");
	}

	@Test(priority=29,dataProvider="XP3_Device_Sanity", dataProviderClass=DataProviders.class)
	public void XP3_TC_029_Sanity_Charging_Over_USB(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException {

		APP_LOGS.info("=========== XP3_TC_029_Sanity_Charging_Over_USB_BatteryLevel============");
		//startAdbLog("XP3_Sanity_029");
		launch_an_app("settings");
		scrollToText("System");
		minWait();
		validate_phone_Charging();	
		test.log(LogStatus.PASS, "Test case status is Passed");
	}


	@Test(priority=30,dataProvider="XP3_Device_Sanity", dataProviderClass=DataProviders.class)
	public void XP3_TC_030_Sanity_Date_Time_and_TimeZone(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException 
	{
		APP_LOGS.info("=========== XP3_TC_030_Device_Sanity============");
		//startAdbLog("XP3_DeviceSanity_030");
		clearRecentApps();
		//validate_Date_Time_in_NativeSettings();
		navigateToApplication(data.get("Application"));
		validate_getDate_Time_TimeZone();
		test.log(LogStatus.PASS, "Test case status is Passed");
	}

	@Test(priority=31,dataProvider="XP3_Device_Sanity", dataProviderClass=DataProviders.class)
	public void XP3_TC_031_Validate_Edit_Deletion_APns(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException
	{
		APP_LOGS.info("===========XP3_TC_031_Device_Sanity============");
		//startAdbLog("XP3_DeviceSanity_031");
		//editAPN();
		navigateTo_APN();
		validateEditionDeletionApn();
		test.log(LogStatus.PASS, "Test case status is Passed");
	}	

	@Test(priority=32,dataProvider="XP3_Device_Sanity", dataProviderClass=DataProviders.class)
	public void XP3_TC_032_Validate_Restore_Default_APns(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException
	{
		APP_LOGS.info("===========XP3_TC_032_Device_Sanity============");
		//startAdbLog("XP3_DeviceSanity_032");
		//recordVideo("Sanity_28");
		clearRecentApps();
		navigateTo_APN();
		validateRestoreAPn();
		test.log(LogStatus.PASS, "Test case status is Passed");
	}

	@Test(priority=33,dataProvider="XP3_Device_Sanity", dataProviderClass=DataProviders.class)
	public void XP3_TC_033_Sanity_Internal_Storage_Space(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException {

		APP_LOGS.info("=========== XP3_TC_033_Sanity_Internal_Storage_Space============");
		//startAdbLog("XP3_Sanity_033");
		//navigateToApplication("Settings");
		launch_an_app("settings");
		scrollToText("Storage");
		validateInternalStorage();
	}

	@Test(priority=34,dataProvider="XP3_Device_Sanity", dataProviderClass=DataProviders.class)
	public void XP3_TC_034_Sanity_VolumeKey_Validation_Up_Down(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException {

		APP_LOGS.info("=========== XP3_TC_034_Sanity_VolumeKey_Validation_Up_Down============");
		//startAdbLog("XP3_Sanity_034");
		volumeUpKey();
		customWait(2000);
		startAdbLog("XP3_Sanity_034_1");
		volumeDownKey();
		test.log(LogStatus.PASS, "Test case status is Passed");
	}

	@Test(priority=35,dataProvider="XP3_Device_Sanity", dataProviderClass=DataProviders.class)
	public void XP3_TC_035_Sanity_PTT_Key_Validation(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException {

		APP_LOGS.info("=========== XP3_TC_035_Sanity_PTT_Key_Validation============");
		//startAdbLog("XP3_Sanity_035");
		launch_an_app("settings");
		customWait(2000);
		navigateToPTTKey();
		minWait();
		selectApplicationAndLaunch();
		minWait();
		validateApplicationlaunch();
	}

	@Test(priority=36,dataProvider="XP3_Device_Sanity", dataProviderClass=DataProviders.class)
	public void XP3_TC_036_Sanity_OtherKey_Validation_Green_RED_Clear_Back_Recent_Menu_Navigation(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException {

		APP_LOGS.info("=========== XP3_TC_036_Sanity_OtherKey_Validation_Green_RED_Clear_Back_Recent_Menu_Navigation============");
		//startAdbLog("XP3_Sanity_036");
		SoftAssert s = new SoftAssert();
		validateHomeKey(s);
		minWait();
		validateGreen_Red_Key(s);
		minWait();
		validate_Menu_Back_Clear_Key(s);
		minWait();
		validateRecentKey(s);
		minWait();
		validateNavigationKeys(s);
		s.assertAll();
	}

	@Test(priority=37,dataProvider="XP3_Device_Sanity", dataProviderClass=DataProviders.class)
	public void XP3_TC_037_Sanity_FM_Radio_ON_OFF(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException {

		APP_LOGS.info("=========== XP3_TC_037_Sanity_FM_Radio_ON_OFF============");
		//startAdbLog("XP3_Sanity_037");
		if(p_b_No.contains("-10.")) {
			selectFMApp();
		}
		else {
			launch_an_app("fm");
		}
		turnOFF_FM();
		validateOn_OFF_FM();
		test.log(LogStatus.PASS, "Test case status is Passed");
	}

	@Test(priority=38,dataProvider="XP3_Device_Sanity", dataProviderClass=DataProviders.class)
	public void XP3_TC_038_Sanity_Browse_Using_MobileData(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException {

		APP_LOGS.info("=========== XP3_TC_038_Sanity_Browse_Using_MobileData============");
		//startAdbLog("XP3_Sanity_038");
		disableWifi();
		enableData();
		customWait(10000);
		launch_an_app("browser");		
		validateBrowseInternet();
		test.log(LogStatus.PASS, "Test case status is Passed");
	}

	@Test(priority=39,dataProvider="XP3_Device_Sanity", dataProviderClass=DataProviders.class)
	public void XP3_TC_039_Sanity_LoadHomePage_Browser_MobileData(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException {

		APP_LOGS.info("=========== XP3_TC_039_Sanity_LoadHomePage_Browser_MobileData ============");
		//startAdbLog("XP3_Sanity_039");
		disableWifi();
		enableData();
		launch_an_app("browser");	
		validateHomePageLoadedBrowser();
		test.log(LogStatus.PASS, "Test case status is Passed");
	}

	@Test(priority=40,dataProvider="XP3_Device_Sanity", dataProviderClass=DataProviders.class)
	public void XP3_TC_040_Sanity_Browse_Using_WiFi_Data(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException {

		APP_LOGS.info("=========== XP3_TC_040_Sanity_Browse_Using_WiFi_Data ============");
		//startAdbLog("XP3_Sanity_040");
		launch_an_app("settings");
		if(p_b_No.contains("-10.")) {
			disableCellularData();
		}
		else {
			disableCellularData_SP();
		}
		ConnectSecuredWifi();
		validateBrowseWithWiFi();		
	}

	@Test(priority=41,dataProvider="XP3_Device_Sanity", dataProviderClass=DataProviders.class)
	public void XP3_TC_041_Sanity_LoadHomePage_Browser_WiFiData(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException {

		APP_LOGS.info("=========== XP3_TC_041_Sanity_LoadHomePage_Browser_WiFiData ============");
		//startAdbLog("XP3_Sanity_041");
		launch_an_app("settings");
		if(p_b_No.contains("-10.")) {
			disableCellularData();
		}
		else {
			disableCellularData_SP();
		}
		validateHomePageLoadedBrowser();
	}

	@Test(priority=42,dataProvider="XP3_Device_Sanity", dataProviderClass=DataProviders.class)
	public void XP3_TC_042_Sanity_Record_Media_Using_VoiceRecorder(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException {

		APP_LOGS.info("=========== XP3_TC_042_Sanity_Record_Media_Using_VoiceRecorder ============");
		//startAdbLog("XP3_Sanity_042");
		launch_an_app("soundRecorder");
		deleteSavedRecorder();
		recordSound();
		validateRecordedSoundSaved();		
		test.log(LogStatus.PASS, "Test case status is Passed");
	}

	@Test(priority=43,dataProvider="XP3_Device_Sanity", dataProviderClass=DataProviders.class)
	public void XP3_TC_043_Sanity_TakePicture_With_Back_Camera(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException {

		APP_LOGS.info("=========== XP3_TC_043_Sanity_TakePicture_With_Back_Camera ============");
		String log = startAdbLog();
		cameraPic();
		validateBackCamera("takePicture:","Camera",log);
		test.log(LogStatus.PASS, "Test case status is Passed");
	}

	@Test(priority=44,dataProvider="XP3_Device_Sanity", dataProviderClass=DataProviders.class)
	public void XP3_TC_044_Sanity_TakeVideo_With_Back_Camera(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException {

		APP_LOGS.info("=========== XP3_TC_044_Sanity_TakeVideo_With_Back_Camera ============");
		String log = startAdbLog();
		launch_an_app("gallery");
		takeCameraVideo(1);
		press_Enter();
		validateBackCamera("PROFILE_FIRST_RECORD_FRAME","Camera",log);
	}

	@Test(priority=45,dataProvider="XP3_Device_Sanity", dataProviderClass=DataProviders.class)
	public void XP3_TC_045_Sanity_PlayMusic_from_MusicPlayer(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException {
		
		APP_LOGS.info("=========== XP3_TC_045_Sanity_PlayMusic_from_MusicPlayer============");
		//startAdbLog("XP3_Sanity_045");
		playMusic();
		validate_music_player();
		test.log(LogStatus.PASS, "Test case status is Passed");
	}

	@Test(priority=46,dataProvider="XP3_Device_Sanity", dataProviderClass=DataProviders.class)
	public void XP3_TC_046_Sanity_check_Able_to_Change_SleepTime(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException {
		APP_LOGS.info("=========== XP3_TC_046_Sanity_check_Able_to_Change_SleepTime ============");
		//startAdbLog("XP3_Sanity_046");	
		navigateToApplication("Settings");
		validateChangeSleepTime();
		test.log(LogStatus.PASS, "Test case status is Passed");
	}

	@Test(priority=47,dataProvider="XP3_Device_Sanity", dataProviderClass=DataProviders.class)
	public void XP3_TC_047_Sanity_CopyPaste_file(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException {

		APP_LOGS.info("=========== XP3_TC_047_Sanity_CopyPaste_file ============");
		//startAdbLog("XP3_Sanity_047");
		SoftAssert s = new SoftAssert();
		copy_file_to_Folder();
		validate_file_copy(s);
		s.assertAll();
	}

	@Test(priority=48,dataProvider="XP3_Device_Sanity", dataProviderClass=DataProviders.class)
	public void XP3_TC_048_Sanity_Add_Event_Calender(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, org.apache.http.ParseException, ParseException {

		APP_LOGS.info("=========== XP3_TC_048_Sanity_Add_Event_Calender ============");
		//startAdbLog("XP3_Sanity_048");
		checkOperatorAndlaunchCalendar();
		minWait();
		addCalendarEvent(data.get("eventName"), data.get("location"));
		test.log(LogStatus.PASS, "Test case status is Passed");
	}
	 
	@Test(priority=49,dataProvider="XP3_Device_Sanity", dataProviderClass=DataProviders.class)
	public void XP3_TC_049_Sanity_Add_AlarmEvent_Clock(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, org.apache.http.ParseException, ParseException {

		APP_LOGS.info("=========== XP3_TC_049_Sanity_Add_AlarmEvent_Clock============");
		//startAdbLog("XP3_Sanity_049");
		launchClock();
		minWait();
		addAlarm();
		minWait();
		validateAddedAlarm();
	}

	@Test(priority=50,dataProvider="XP3_Device_Sanity", dataProviderClass=DataProviders.class)
	public void XP3_TC_050_Sanity_Snooze_And_Dismiss_Alarm(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, org.apache.http.ParseException, ParseException {

		APP_LOGS.info("=========== XP3_TC_050_Sanity_Snooze_And_Dismiss_Alarm============");
		//startAdbLog("XP3_Sanity_050");
		launchClock();
		minWait();
		snoozeTheAlarm();
		minWait();
		dismissTheAlarm();
		test.log(LogStatus.PASS, "Test case status is Passed");	
	}

	@Test(priority=51,dataProvider="XP3_Device_Sanity", dataProviderClass=DataProviders.class)
	public void XP3_TC_051_Sanity_Snooze_Alarm_during_OngoingVoiceCall(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, org.apache.http.ParseException, ParseException {

		APP_LOGS.info("=========== XP3_TC_051_Sanity_Snooze_Alarm_during_OngoingVoiceCall============");
		//startAdbLog("XP3_Sanity_051");
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


	//	@Test(priority=28,dataProvider="XP3_Device_Sanity", dataProviderClass=DataProviders.class)
	//	public void XP3_TC_028_Sanity_Connection_Secured_Wifi(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException 
	//	{
	//		APP_LOGS.info("===========XP3_TC_028_Device_Sanity============");
	//		startAdbLog("XP3_DeviceSanity_028");
	//		//recordVideo("Sanity_18");
	//		clearRecentApps();
	//		selectSettings(Locators_Sanity.wifi);
	//		ConnectSecuredWifi();
	//		validateWifiConnect();	
	//		customWait(4000);
	//		test.log(LogStatus.PASS, "Test case status is Passed");
	//	}	 
	//	
	//	@Test(priority=27,dataProvider="XP3_Device_Sanity", dataProviderClass=DataProviders.class)
	//	public void XP3_TC_027_Sanity_Enable_Disable_MobileData_DeviceSettings(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException {
	//
	//		APP_LOGS.info("=========== XP3_TC_027_Device_Sanity============");
	//		startAdbLog("XP3_DeviceSanity_027");
	//		clearRecentApps();
	//		disableData();
	//		launch_an_app("browser");
	//		validateBrowseNoInternet();
	//		enableData();
	//		launch_an_app("browser");		
	//		validateBrowseInternet();
	//	}

}
