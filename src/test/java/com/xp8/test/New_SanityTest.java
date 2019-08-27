package com.xp8.test;

import java.awt.AWTException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Properties;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import org.json.simple.parser.ParseException;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTestInterruptedException;
import com.relevantcodes.extentreports.LogStatus;
import com.xp5S.util.GetMethods;
import com.xp5S.util.appiumService;
import com.xp8.util.DataProviders;
import com.xp8.util.ExcelConstants;
import com.xp8.util.ExcelReader;

import com.xp8.util.Locators_BaseUtil;
import com.xp8.util.New_SanityLocators;
import com.xp8.util.New_SanityLocators;
import com.xp8.util.New_SanityLocators;
import com.xp8.util.New_SanityLocators;
import com.xp8.util.New_SanityUtil;

import application.AllQA;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.AndroidKeyCode;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class New_SanityTest extends New_SanityUtil {

	public static ExtentTestInterruptedException testexception;
	public ExcelReader excel;
	Properties properties;
	public Timer timer1;

	public static String ssid = AllQA.SSID;
	public static String pwsd = AllQA.WIFIPASSWORD;

//test
	@BeforeSuite
	public void beforeSuite() throws FileNotFoundException, InterruptedException, IOException, ParseException {

		extent = new ExtentReports("src/test/resources/extentreport/XP8_Device_QA_Sanity_Test_O.html", true);
		extent.loadConfig(new File("src/test/resources/StorageFile/ReportsConfig.xml"));
		extent.addSystemInfo("Appium", "1.1").addSystemInfo("Environment", "TEST");
		fetch_Devices_Details();

		Runtime.getRuntime()
				.exec("adb -s " + p_Id + " push src/test/resources/StorageFile/Kalimba.mp3 /storage/emulated/0");
	}

	@BeforeTest
	public void setUpSys() throws FileNotFoundException, MalformedURLException, InterruptedException, AWTException {
		properties = loadDriverAndProperties();
		New_SanityLocators loc1 = new New_SanityLocators(aDriver);
		Locators_BaseUtil loc2 = new Locators_BaseUtil(aDriver);
		PageFactory.initElements(new AppiumFieldDecorator(aDriver), loc1);
		PageFactory.initElements(new AppiumFieldDecorator(aDriver), loc2);
		excel = new ExcelReader(ExcelConstants.XP5S_XL_PATH);
	}

	@BeforeTest
	public void timer() {
		timer1 = new Timer();
		timer1.schedule(new TimerTask() {

			@Override
			public void run() {
				if (isElementExist(New_SanityLocators.batteryFullorAppKey)) {
					New_SanityLocators.OK.click();
				}
			}
		}, 0, 10 * (100 * 1));

		New_SanityLocators loc1 = new New_SanityLocators(aDriver);
		Locators_BaseUtil loc2 = new Locators_BaseUtil(aDriver);
		PageFactory.initElements(new AppiumFieldDecorator(aDriver), loc1);
		PageFactory.initElements(new AppiumFieldDecorator(aDriver), loc2);
	}

	@BeforeClass
	public void deleteADBLogs() {

		File dir = new File("");
		if (dir.isDirectory() == false) {
			return;
		}
		File[] files = dir.listFiles();
		for (File file : files) {
			try {
				file.delete();
			} catch (Exception e) {
			}
		}
	}

	@BeforeSuite
	public void numofTestCases() throws ClassNotFoundException {

		appiumService.TOTAL_NUM_OF_TESTCASES = GetMethods.TotalTestcase("XP8_DeviceSanity", this.getClass());

	}

	@BeforeMethod
	public void beforeMethod(Method method) throws InterruptedException, IOException {
		test = extent.startTest((/* this.getClass().getSimpleName() +" :: "+ */method.getName()), method.getName()); // Test
																														// Case
																														// Start
																														// Here
		// test.assignAuthor("Chandan. A"); //Test Script Author Name.

		clearRecentApps();
	}

	@AfterMethod
	public void afterMethod(Method method, ITestResult result) throws IOException, InterruptedException {
		if (result.getStatus() == ITestResult.FAILURE) {
			String screenshotPath = captureScreenshot(method.getName());
			test.addScreenCapture(screenshotPath);
			test.log(LogStatus.FAIL, result.getThrowable());
			clearRecentApps();
		}
		extent.endTest(test);
		extent.flush();

		clearRecentApps();
	}

	// ========================================= Test Method ============================================

	
	@Test(priority = 1, dataProvider = "XP8_NewSanityTest", dataProviderClass = DataProviders.class)
	public void XP8_DeviceSanity_001_Check_Switching_of_camera_FandB(Hashtable<String, String> dt)
			throws InterruptedException, IOException, AWTException {

		APP_LOGS.info(
				"===================== XP8_DeviceSanity_001_Check_Switching_of_camera_FandB ======================");
		String fN = startAdbLog();
		clearCamera_cache();
		launch_an_app("camera");
		clearCameraPermission();
		restore_DefaultStng();
		clickOnCapture();
		customWait(3000);
		changeTo_Front_Back_Cam_O();
		customWait(3000);
		clickOnCapture();
		validate_ADB_Logs(fN, "com.android.camera.NEW_PICTURE");
	}

	@Test(priority = 2, dataProvider = "XP8_NewSanityTest", dataProviderClass = DataProviders.class)
	public void XP8_DeviceSanity_002_Check_Switching_of_camVideo_FandB(Hashtable<String, String> dt)
			throws InterruptedException, IOException, AWTException {

		APP_LOGS.info(
				"===================== XP8_DeviceSanity_002_Check_Switching_of_camVideo_FandB ======================");
		String fN = startAdbLog();
		clearCamera_cache();
		launch_an_app("camera");
		clearCameraPermission();
		restore_DefaultStng();
		clickVideoButton();
		customWait(8000);
		clickVideoButton();
		customWait(8000);
		validate_ADB_Logs(fN, "encoder (video) stopped");
		// Testtin for Front Cam
		changeTo_FrontCam();
		clickVideoButton();
		customWait(8000);
		clickVideoButton();
		customWait(8000);
		validate_ADB_Logs(fN, "encoder (video) stopped");// com.android.camera.NEW_VIDEO
	}

	@Test(priority = 3, dataProvider = "XP8_NewSanityTest", dataProviderClass = DataProviders.class)
	public void XP8_DeviceSanity_003_LaunchAndCheck_CalculatorOperation(Hashtable<String, String> dt)
			throws InterruptedException, IOException, AWTException {

		APP_LOGS.info("===================== XP8_DeviceSanity_003_LaunchAndCheck_CalculatorOperation ======================");

		SoftAssert sa = new SoftAssert();
		launch_An_App(dt.get("App_Package"), dt.get("App_Activity")); // launching Calculator App
		validateCalcLaunch(sa);
		basicOperationwithoutdecimalpt(sa);			

		sa.assertAll();
	}

	@Test(priority = 4, dataProvider = "XP8_NewSanityTest", dataProviderClass = DataProviders.class)
	public void XP8_DeviceSanity_004_CheckClockMenus(Hashtable<String, String> dt)
			throws InterruptedException, IOException, AWTException {

		APP_LOGS.info("===================== XP8_DeviceSanity_004_CheckClockMenus ======================");

		SoftAssert sa = new SoftAssert();
		launch_An_App(dt.get("SystemSetting_Package"), dt.get("SystemSetting_Activity")); // launching System Setting
		turnOnAutomatic_date_Time();
		clearClockData();
		launch_An_App(dt.get("Clock_Package"), dt.get("Clock_Activity")); // launching System Setting
		validateClock(sa);
		sa.assertAll();
	}

	@Test(priority = 5, dataProvider = "XP8_NewSanityTest", dataProviderClass = DataProviders.class) // 156
	public void XP8_DeviceSanity_005_Internal_StorageSpaceCapacity(Hashtable<String, String> dt)
			throws InterruptedException, IOException, AWTException {

		APP_LOGS.info(
				"===================== XP8_DeviceSanity_005_Internal_StorageSpaceCapacity ======================");

		SoftAssert sa = new SoftAssert();
		launch_An_App(dt.get("SystemStorage_Package"), dt.get("SystemStorage_Activity")); // launching System Storage
																							// Setting
		scrollToText("Storage");
		validate_InternalStorage(sa, dt.get("PRD")); // space given in product requirements document
		sa.assertAll();
	}

	@Test(priority = 6, dataProvider = "XP8_NewSanityTest", dataProviderClass = DataProviders.class) // 157
	public void XP8_DeviceSanity_006_FM_turnOn_turnOff(Hashtable<String, String> dt)
			throws InterruptedException, IOException, AWTException {

		APP_LOGS.info("===================== XP8_DeviceSanity_006_FM_turnOn_turnOff ======================");

		SoftAssert sa = new SoftAssert();
		clearFMdata();
		launch_An_App(dt.get("FM_Package"), dt.get("FM_Activity")); // launching FM
		validateFM_ON_Off(sa);
		sa.assertAll();
	}

	@Test(priority = 7, dataProvider = "XP8_NewSanityTest", dataProviderClass = DataProviders.class) // 158
	public void XP8_DeviceSanity_007_FM_TuneToAnyFM_Station(Hashtable<String, String> dt)
			throws InterruptedException, IOException, AWTException {

		APP_LOGS.info("===================== XP8_DeviceSanity_007_FM_TuneToAnyFM_Station ======================");

		SoftAssert sa = new SoftAssert();
		clearFMdata();
		customWait(3000);
		launch_An_App(dt.get("FM_Package"), dt.get("FM_Activity")); // launching FM
		validateTuneTOFM_Station(sa);
		sa.assertAll();
	}

	@Test(priority = 8, dataProvider = "XP8_NewSanityTest", dataProviderClass = DataProviders.class) // 160
	public void XP8_DeviceSanity_008_Wifi_TurnOnTurnOffFromQuickStng(Hashtable<String, String> dt)
			throws InterruptedException, IOException, AWTException {

		APP_LOGS.info(
				"===================== XP8_DeviceSanity_008_Wifi_TurnOnTurnOffFromQuickStng ======================");
		SoftAssert sa = new SoftAssert();
		turnOff_Mobiledata();
		resetQuickStng();
		customWait(2000);
		launch_An_App(dt.get("N_I_Package"), dt.get("N_I_Activity")); // launching Wifi Stng
		clickBtn(New_SanityLocators.Wifi_wifiLnk);
		turnOnWifi();
		customWait(3000);
		remove_connectedNtwrk();
		connect_to_WiFi(ssid, pwsd);
		customWait(3000);
		turnOffWifi();
		minWait();
		turnOnWifi_quickStng();
		clearChromeCache();
		Launch_App("browser");
		clearChromePermission();
		validate_And_BrowseIn_Chrome(sa);
		turnOffWifi_quickStng();
		clearChromeCache();
		Launch_App("browser");
		clearChromePermission();
		validate_And_BrowseIn_Chrome_Disconnect(sa);
		turnOffWifi_adb();
		sa.assertAll();
	}

	@Test(priority = 9, dataProvider = "XP8_NewSanityTest", dataProviderClass = DataProviders.class) // 161
	public void XP8_DeviceSanity_009_Bluetooth_TurnOnTurnOffFromQuickStng(Hashtable<String, String> dt)
			throws InterruptedException, IOException, AWTException {

		APP_LOGS.info(
				"===================== XP8_DeviceSanity_009_Bluetooth_TurnOnTurnOffFromQuickStng ======================");
		SoftAssert sa = new SoftAssert();
		resetQuickStng();
		customWait(2000);
		turnOff_Bluetooth();
		turnOn_Blutooth_quickStng();
		validate_Blutooth_TurnOn(sa);
		turnOff_Blutooth_quickStng();
		validate_Blutooth_TurnOff(sa);
		disable_bluetooth();
		sa.assertAll();
	}

	@Test(priority = 10, dataProvider = "XP8_NewSanityTest", dataProviderClass = DataProviders.class) // 165
	public void XP8_DeviceSanity_0010_Device_increase_DecreaseBrightness(Hashtable<String, String> dt)
			throws InterruptedException, IOException, AWTException {

		APP_LOGS.info(
				"===================== XP8_DeviceSanity_0010_Device_increase_DecreaseBrightness ======================");
		SoftAssert sa = new SoftAssert();
		increaseDevice_Brightness();
		validate_MaximumBrightness(sa);
		decreaseDevice_Brightness();
		validate_MinimumBrightness(sa);
		sa.assertAll();
	}

	@Test(priority = 11, dataProvider = "XP8_NewSanityTest", dataProviderClass = DataProviders.class) // 118
	public void XP8_DeviceSanity_0011_Open_URL_in_browser(Hashtable<String, String> dt)
			throws InterruptedException, IOException, AWTException {

		APP_LOGS.info("===================== XP8_DeviceSanity_0011_Open_URL_in_browser ======================");
		SoftAssert sa = new SoftAssert();
		customWait(2000);
		turnOff_Mobiledata();
		launch_An_App(dt.get("N_I_Package"), dt.get("N_I_Activity")); // launching network setting
		clickBtn(New_SanityLocators.Wifi_wifiLnk);
		turnOnWifi();
		customWait(3000);
		remove_connectedNtwrk();
		connect_to_WiFi(ssid, pwsd);
		customWait(3000);
		clearChromeCache();
		Launch_App("browser");
		clearChromePermission();
		enterurl(dt.get("amazon"));
		validate_webPage(New_SanityLocators.amazon_SearchBx, New_SanityLocators.amazon_logoTxt, dt.get("amazon"), sa);
		customWait(3000);
		enterurl(dt.get("facebook"));
		validate_webPage(New_SanityLocators.facebook_LogoTxt, New_SanityLocators.facebook_SearchBx, dt.get("facebook"),sa);
		turnOffWifi_adb();
		sa.assertAll();
	}

	@Test(priority = 12, dataProvider = "XP8_NewSanityTest", dataProviderClass = DataProviders.class) // 167
	public void XP8_DeviceSanity_0012_FlashLight_ON_Off(Hashtable<String, String> dt)
			throws InterruptedException, IOException, AWTException {

		APP_LOGS.info("===================== XP8_DeviceSanity_0012_FlashLight_ON_Off ======================");
		SoftAssert sa = new SoftAssert();
		resetQuickStng();
		turnOn_FlashLight();
		validateFlashLight_turnOn(sa);
		turnOff_FlashLight();
		validateFlashLight_turnOff(sa);
		sa.assertAll();
	}

	
	@Test(priority = 13, dataProvider = "XP8_NewSanityTest", dataProviderClass = DataProviders.class) // 131
	public void XP8_DeviceSanity_0013_checkDeviceInformation_WithSwrealese_and_stng(Hashtable<String, String> dt)
			throws InterruptedException, IOException, AWTException {

		APP_LOGS.info(
				"===================== XP8_DeviceSanity_0013_checkDeviceInformation_WithSwrealese_and_stng ======================");
		SoftAssert sa = new SoftAssert();
		launch_An_App(dt.get("CallDialer_Package"), dt.get("CallDialer_Activity"));
		dailNumberforEnggMenu(dt.get("num"));
		LinkedList<String> al = checkInf_inSwRealease();
		close_CIT();
		Launch_App("Setting");
		scrollToText("System");
		scrollToTextClick("About phone");
		validateSWinf_withStng(sa, al);
		sa.assertAll();
	}

	@Test(priority = 14, dataProvider = "XP8_NewSanityTest", dataProviderClass = DataProviders.class) // 133
	public void XP8_DeviceSanity_0014_checkDate_Time_timezone(Hashtable<String, String> dt)
			throws InterruptedException, IOException, AWTException {

		APP_LOGS.info("===================== XP8_DeviceSanity_0014_checkDate_Time_timezone ======================");
		SoftAssert sa = new SoftAssert();
		Launch_App("Setting"); // launching System Setting
		scrollToText("System");
		turnOnAutomatic_date_Time();
		validateDate_Time(sa, dt.get("timeZone"));
		sa.assertAll();
	}


	@Test(priority=15,dataProvider="XP8_NewSanityTest", dataProviderClass= DataProviders.class)//135
	public void XP8_DeviceSanity_0015_checkCharging_StatusOfDevice(Hashtable<String, String> dt) throws InterruptedException, IOException, AWTException {

		APP_LOGS.info("===================== XP8_DeviceSanity_0015_checkCharging_StatusOfDevice ======================");
		SoftAssert sa = new SoftAssert();
		Launch_App("Setting"); // launching System Setting
		scrollToText("Battery");
		validateBatteryStatus(sa);
	
	}

	@Test(priority = 16, dataProvider = "XP8_NewSanityTest", dataProviderClass = DataProviders.class) // 139
	public void XP8_DeviceSanity_0016_checkEngg_Menus(Hashtable<String, String> dt)
			throws InterruptedException, IOException, AWTException {
		APP_LOGS.info("===================== XP8_DeviceSanity_0016_checkEngg_Menus ======================");
		SoftAssert sa = new SoftAssert();
		disable_AirplaneMode();
		launch_An_App(dt.get("CallDialer_Package"), dt.get("CallDialer_Activity"));
		dailNumberforEnggMenu(dt.get("gps"));
		validate_EnggMenu(sa, dt.get("gps"), New_SanityLocators.EnggMode_gpsTxt);
		dailNumberforEnggMenu(dt.get("network"));
		validate_EnggMenu(sa, dt.get("network"), New_SanityLocators.EnggMode_networkTxt);
		dailNumberforEnggMenu(dt.get("enggMode"));
		validate_EnggMenu(sa, dt.get("enggMode"), New_SanityLocators.EnggMode_enggTxt);
		dailNumberforEnggMenu(dt.get("cit"));
		validate_EnggMenu(sa, dt.get("cit"), New_SanityLocators.EnggMode_CitTxt);
		dailNumberforEnggMenu(dt.get("internalLog"));
		validate_EnggMenu(sa, dt.get("internalLog"), New_SanityLocators.EnggMode_loggerTxt);
		sa.assertAll();
	}

	@Test(priority = 17, dataProvider = "XP8_NewSanityTest", dataProviderClass = DataProviders.class) // 106
	public void XP8_DeviceSanity_0017_check_EnableDisableDataService(Hashtable<String, String> dt)
			throws InterruptedException, IOException, AWTException {
		APP_LOGS.info("===================== XP8_DeviceSanity_0017_check_EnableDisableDataService ======================");
		SoftAssert sa = new SoftAssert();
		disable_AirplaneMode();
		turnOff_Mobiledata();
		clearChromeCache();
		Launch_App("browser");
		clearChromePermission();
		validate_And_BrowseIn_Chrome_Disconnect(sa);
		turnOn_Mobiledata();
		Launch_App("browser");
		validate_And_BrowseIn_Chrome(sa);
		turnOff_Mobiledata();
		sa.assertAll();
	}

	@Test(priority = 18, dataProvider = "XP8_NewSanityTest", dataProviderClass = DataProviders.class) // 121
	public void XP8_DeviceSanity_0018_Add_Delete_APN(Hashtable<String, String> dt)
			throws InterruptedException, IOException, AWTException {
		APP_LOGS.info("===================== XP8_DeviceSanity_0018_Add_Delete_APN ======================");
		SoftAssert sa = new SoftAssert();
		disable_AirplaneMode();
		navigateTo_Apn();
		add_Apn(dt.get("APN_name"), dt.get("APN"), dt.get("APN_type"));
		validate_AddedApn(sa, dt.get("APN_name"), dt.get("APN"));
		delete_Apn(dt.get("APN_name"));
		validate_DeletedApn(sa, dt.get("APN_name"));
		sa.assertAll();
	}

	@Test(priority = 19, dataProvider = "XP8_NewSanityTest", dataProviderClass = DataProviders.class) // 116
	public void XP8_DeviceSanity_0019_Browser_Url_usingIPv4(Hashtable<String, String> dt)
			throws InterruptedException, IOException, AWTException {
		APP_LOGS.info("===================== XP8_DeviceSanity_0019_Browser_Url_usingIPv4 ======================");
		SoftAssert sa = new SoftAssert();
		disable_AirplaneMode();
		navigateTo_Apn();
		setIPv4_InApn();
		turnOn_Mobiledata();
		validat_browse_Url(sa);
		reset_APN();
		turnOff_Mobiledata();
		sa.assertAll();
	}

	@Test(priority = 20, dataProvider = "XP8_NewSanityTest", dataProviderClass = DataProviders.class) // 162
	public void XP8_DeviceSanity_0020_turnOn_turnOff_dataServices(Hashtable<String, String> dt)
			throws InterruptedException, IOException, AWTException {
		APP_LOGS.info("===================== XP8_DeviceSanity_0020_turnOn_turnOff_dataServices ======================");
		SoftAssert sa = new SoftAssert();
		disable_AirplaneMode();
		check_mobileNtwrkEnabled(sa);
		disable_wifi();
		turnOff_Mobiledata();
		resetQuickStng();
		turnOff_DataServiceQuickStng();
		validate_dataServiceOff(sa);
		turnOn_DataServiceQuickStng();
		validate_dataServiceOn(sa);
		turnOff_Mobiledata();
		sa.assertAll();
	}

	@Test(priority = 21, dataProvider = "XP8_NewSanityTest", dataProviderClass = DataProviders.class) // 84
	public void XP8_DeviceSanity_0021_MO_calls_From_CallLog(Hashtable<String, String> dt)
			throws InterruptedException, IOException, AWTException {
		APP_LOGS.info("===================== XP8_DeviceSanity_0021_MO_calls_From_CallLog ======================");
		SoftAssert sa = new SoftAssert();
		endCall_RefDevice();
		deleteContact_ifExsist();
		make_Call_from_RefDev();
		customWait(5000);
		endCall_RefDevice();
		launch_An_App(dt.get("CallDialer_Package"), dt.get("CallDialer_Activity"));
		callfrom_CallHistory();
		customWait(5000);
		end_Call();
		validate_callHistory(sa,refNum);
		sa.assertAll();
	}

	@Test(priority = 22, dataProvider = "XP8_NewSanityTest", dataProviderClass = DataProviders.class) // 08
	public void XP8_DeviceSanity_0022_checkAirplane_Enable_Disable(Hashtable<String, String> dt)
			throws InterruptedException, IOException, AWTException {

		APP_LOGS.info(
				"===================== XP8_DeviceSanity_0022_checkAirplane_Enable_Disable ======================");
		SoftAssert sa = new SoftAssert();
		launch_An_App(dt.get("N_I_Package"), dt.get("N_I_Activity")); // launching network setting
		turnOn_AirplaneMode(); // to enable flight mode
		launch_an_app("phone");
		makeCall(refNum);
		validate_Airplane_Enable(sa);
		launch_An_App(dt.get("N_I_Package"), dt.get("N_I_Activity")); // launching network setting
		turnOff_AirplaneMode();
		launch_an_app("phone");
		makeCall(refNum);
		validate_Airplane_Disable(sa);
		end_Call();
		endCall_RefDevice();
		sa.assertAll();
	}

	@Test(priority = 23, dataProvider = "XP8_NewSanityTest", dataProviderClass = DataProviders.class) // 178
	public void XP8_DeviceSanity_0023_connect_wifi_to_Secured_network(Hashtable<String, String> dt)
			throws InterruptedException, IOException, AWTException {

		APP_LOGS.info(
				"===================== XP8_DeviceSanity_0023_connect_wifi_to_Secured_network ======================");
		SoftAssert sa = new SoftAssert();
		launch_An_App(dt.get("N_I_Package"), dt.get("N_I_Activity")); // launching network setting
		clickBtn(New_SanityLocators.Wifi_wifiLnk);
		turnOnWifi();
		customWait(3000);
		remove_connectedNtwrk();
		customWait(3000);
		validate_ableToCntWifi(sa, ssid, pwsd);
		turnOffWifi_adb();
		sa.assertAll();
	}

	@Test(priority = 24, dataProvider = "XP8_NewSanityTest", dataProviderClass = DataProviders.class) // 185
	public void XP8_DeviceSanity_0024_check_charging_while_connect_to_usb(Hashtable<String, String> dt)
			throws InterruptedException, IOException, AWTException {

		APP_LOGS.info(
				"===================== XP8_DeviceSanity_0024_check_charging_while_connect_to_usb ======================");
		SoftAssert sa = new SoftAssert();
		launch_an_app("settings");
		clickOn_Battery();
		validate_DeviceCharging(sa);
		sa.assertAll();
	}

	@Test(priority = 25, dataProvider = "XP8_NewSanityTest", dataProviderClass = DataProviders.class) // 178
	public void XP8_DeviceSanity_0025_check_user_able_to_adjust_Brigthness(Hashtable<String, String> dt)
			throws InterruptedException, IOException, AWTException {

		APP_LOGS.info(
				"===================== XP8_DeviceSanity_0025_check_user_able_to_adjust_Brigthness ======================");
		SoftAssert sa = new SoftAssert();
		int percent;
		increaseDevice_Brightness(percent = 0);
		validate_Brightness(sa, percent);
		increaseDevice_Brightness(percent = 25);
		validate_Brightness(sa, percent);
		increaseDevice_Brightness(percent = 50);
		validate_Brightness(sa, percent);
		increaseDevice_Brightness(percent = 75);
		validate_Brightness(sa, percent);
		increaseDevice_Brightness(percent = 100);
		validate_Brightness(sa, percent);
		sa.assertAll();
	}

	

	/////////////////////////// gobi sanity//////////////////////////////////////////

	@Test(priority= 26,dataProvider="XP8_NewSanityTest", dataProviderClass= DataProviders.class)
	public void XP8_DeviceSanity_26_Check_animation_during_call(Hashtable<String, String> data) throws InterruptedException, IOException, AWTException, ParseException 
	{
		SoftAssert sa=new SoftAssert();//88
		APP_LOGS.info("===================== DeviceSanity_26 ======================");
		startAdbLog("DeviceSanity_26");
		clearRecentApps();
		launch_an_app("contacts");
		deleteContacts();
		addcontactsTO_Phone_Make_call(data.get("name1"),data.get("email1"), data.get("Address1"), sa);
		sa.assertAll();
	}
	 
	@Test(priority= 27,dataProvider="XP8_NewSanityTest", dataProviderClass= DataProviders.class)
	public void XP8_DeviceSanity_27_Check_Mute_Unmute_Hold_Retrieve_Loudspeaker_in_VoiceCall(Hashtable<String, String> data) throws InterruptedException, IOException, AWTException, ParseException 
	{
		SoftAssert sa=new SoftAssert();//89
		APP_LOGS.info("===================== DeviceSanity_27 ======================");
		startAdbLog("DeviceSanity_27");
		clearRecentApps();
		make_Call_from_RefDev();
		recieve_Call_PrimaryDev_O();
		minWait();
		check_different_option(sa);
		endCall_RefDevice();
		sa.assertAll();
	}
	
	@Test(priority= 28,dataProvider="XP8_NewSanityTest", dataProviderClass= DataProviders.class)
	public void XP8_DeviceSanity_28_Check_Call_rejection_with_SMS_notification(Hashtable<String, String> data) throws InterruptedException, IOException, AWTException, ParseException 
	{
		SoftAssert sa=new SoftAssert();//90
		APP_LOGS.info("===================== DeviceSanity_28 ======================");
		startAdbLog("DeviceSanity_28");
		clearRecentApps();
		make_Call_from_RefDev();
		customWait(10000);
		endCall_PrimeDevice();
		minWait();
		endCall_RefDevice();
		customWait(5000);
		Runtime.getRuntime().exec("adb -s "+p_Id+" shell service call isms 7 i32 0 s16 \"com.android.mms.service\" s16 "+refNum+" s16 \"null\" s16 \"SMAT\" s16 \"null\" s16 \"null\"");
		customWait(5000);
		test.log(LogStatus.PASS, "Call rejection with SMS is done");
		sa.assertAll();
	}
	
	@Test(priority= 29,dataProvider="XP8_NewSanityTest", dataProviderClass= DataProviders.class)
	public void XP8_DeviceSanity_29_send_Receive_MO_MT_SMS(Hashtable<String, String> data) throws InterruptedException, IOException, AWTException, ParseException 
	{
		SoftAssert sa=new SoftAssert();//100
		APP_LOGS.info("===================== DeviceSanity_29 ======================");
		startAdbLog("DeviceSanity_29");
		clearRecentApps();
		launch_an_app("contacts");
		deleteContacts();
		Runtime.getRuntime().exec("adb -s "+p_Id+" shell input keyevent KEYCODE_HOME");
		minWait();
		Runtime.getRuntime().exec("adb -s "+p_Id+" shell pm clear com.android.mms"); // clear Duo cache
		minWait();
		launch_an_app("messaging");
		send_messaging(sa);
		receive_messaging(sa);
		sa.assertAll();
	}
	
	@Test(priority= 30,dataProvider="XP8_NewSanityTest", dataProviderClass= DataProviders.class)
	public void XP8_DeviceSanity_30_send_MSISDN_mailId_url_simultanously(Hashtable<String, String> data) throws InterruptedException, IOException, AWTException, ParseException 
	{
		SoftAssert sa=new SoftAssert();//101
		APP_LOGS.info("===================== DeviceSanity_30 ======================");
		startAdbLog("DeviceSanity_30");
		clearRecentApps();
		launch_an_app("contacts");
		deleteContacts();
		Runtime.getRuntime().exec("adb -s "+p_Id+" shell input keyevent KEYCODE_HOME");
		minWait();
		Runtime.getRuntime().exec("adb -s "+p_Id+" shell pm clear com.android.mms"); // clear Duo cache
		minWait();
		launch_an_app("messaging");
		send_MSISDN_mailId_url_simultanously(sa);
		sa.assertAll();
	}
	
	@Test(priority= 31,dataProvider="XP8_NewSanityTest", dataProviderClass= DataProviders.class)
	public void XP8_DeviceSanity_31_send_SMS_to_New_number_Saved_Contact(Hashtable<String, String> data) throws InterruptedException, IOException, AWTException, ParseException 
	{
		SoftAssert sa=new SoftAssert();//102
		APP_LOGS.info("===================== DeviceSanity_31 ======================");
		startAdbLog("DeviceSanity_31");
		clearRecentApps();
		Runtime.getRuntime().exec("adb -s "+p_Id+" shell pm clear com.android.contacts"); // clear contacts cache
		launch_an_app("contacts");
		deleteContacts();
		addcontactsTO_Phone(sa);  
		Runtime.getRuntime().exec("adb -s "+p_Id+" shell pm clear com.android.mms"); // clear Messaging cache
		minWait();
		launch_an_app("messaging");
		send_SMS_to_Saved_Contact(sa);
		launch_an_app("contacts");
		deleteContacts();
		aDriver.pressKeyCode(AndroidKeyCode.HOME);
		Runtime.getRuntime().exec("adb -s "+p_Id+" shell pm clear com.android.contacts"); // clear contacts cache
		clearRecentApps();
		launch_an_app("messaging");
		send_messaging(sa);
		sa.assertAll();
	}
	
	@Test(priority= 32,dataProvider="XP8_NewSanityTest", dataProviderClass= DataProviders.class)
	public void XP8_DeviceSanity_32_Launch_Playstore_and_verify_app_installation(Hashtable<String, String> data) throws InterruptedException, IOException, AWTException, ParseException 
	{
		SoftAssert sa=new SoftAssert();//140
		APP_LOGS.info("===================== DeviceSanity_32 ======================");
		startAdbLog("DeviceSanity_32");
		clearRecentApps();
		
		launch_an_app("settings");
		remove_GoogleAcccount_Orio(sa);
		launch_an_app("settings");
		navigateTo_AddGoogleAccount_Orio(sa);
		add_GoogleAccount(data.get("emailId"), data.get("password"),sa);	
		test.log(LogStatus.PASS, "Preconditions are Set");
		Runtime.getRuntime().exec("adb -s "+p_Id+" shell pm clear com.android.vending"); //  Clear Playstore cache
		minWait();
		CheckInstalled_App(data.get("appName"));
		customWait(4000);
		launch_APP(New_SanityLocators.PlayStore);
		customWait(4000);
		install_App(data.get("appName"),New_SanityLocators.HuluApp1);
		validate_Installed_App(data.get("appName"),sa);	
		
		launch_an_app("settings");
		remove_GoogleAcccount_Orio(sa);
		sa.assertAll();
	}

	@Test(priority= 33,dataProvider="XP8_NewSanityTest", dataProviderClass= DataProviders.class)
	public void XP8_DeviceSanity_33_launch_PlayMsic_and_play_pause_anymusic(Hashtable<String, String> data) throws InterruptedException, IOException, AWTException, ParseException 
	{   WebDriverWait wait = new WebDriverWait(aDriver, 120);
		SoftAssert sa=new SoftAssert();//141
		APP_LOGS.info("===================== DeviceSanity_33 ======================");
		startAdbLog("DeviceSanity_33");
		//clearRecentApps();
		aDriver.quit();       //   
		customWait(2000);
		Runtime.getRuntime().exec("adb -s "+p_Id+" reboot");   //
		TimeUnit.SECONDS.sleep(60);                                //
	    aDriver=new AndroidDriver<AndroidElement>(new URL("http://127.0.0.1:4723/wd/hub"),capabilities);  //
	    minWait();
	    setUpSys(); // initialize locator	    
	    Runtime.getRuntime().exec("adb -s "+p_Id+"shell svc power stayon true");
	    minWait();	   
	    Runtime.getRuntime().exec("adb -s "+p_Id+"shell input keyevent 82");
	    minWait();
	    aDriver.pressKeyCode(AndroidKeyCode.BACK);
	    wait.until(ExpectedConditions.visibilityOf(New_SanityLocators.app_List));
	    Runtime.getRuntime().exec("adb -s "+p_Id+" shell pm clear  com.google.android.music"); //  Clear music cache
		launch_an_app("playMusic");
		PlayandPause_music(sa);
		sa.assertAll();	
	}
	
	@Test(priority= 34,dataProvider="XP8_NewSanityTest", dataProviderClass= DataProviders.class)
	public void XP8_DeviceSanity_34_Verify_Gmail_configuration(Hashtable<String, String> data) throws InterruptedException, IOException, AWTException, ParseException 
	{
		SoftAssert sa=new SoftAssert();//142
		APP_LOGS.info("===================== DeviceSanity_34 ======================");
		startAdbLog("DeviceSanity_34");
		clearRecentApps();
		
		launch_an_app("settings");
		remove_GoogleAcccount_Orio(sa);
		launch_an_app("settings");
		navigateTo_AddGoogleAccount_Orio(sa);
		add_GoogleAccount(data.get("emailId"), data.get("password"),sa);	
		test.log(LogStatus.PASS, "Preconditions are Set");
		Runtime.getRuntime().exec("adb -s "+p_Id+" shell pm clear com.google.android.gm"); //  Clear gmail cache
		launch_an_app("gmail");
		Configure_Gmail(sa);
		launch_an_app("settings");
		remove_GoogleAcccount_Orio(sa);
		sa.assertAll();
	}
	
	@Test(priority= 35,dataProvider="XP8_NewSanityTest", dataProviderClass= DataProviders.class)
	public void XP8_DeviceSanity_35_Launch_Chrome_and_browse_URL(Hashtable<String, String> data) throws InterruptedException, IOException, AWTException, ParseException 
	{
		SoftAssert sa=new SoftAssert();//143
		APP_LOGS.info("===================== DeviceSanity_35 ======================");
		startAdbLog("DeviceSanity_35");
		clearRecentApps();
		Runtime.getRuntime().exec("adb -s "+p_Id+" shell pm clear com.android.chrome"); //  Clear chrome cache
		minWait();
		launch_an_app("browser");
		Configure_browser_URL(sa);
		sa.assertAll();
	}
	
	@Test(priority= 36,dataProvider="XP8_NewSanityTest", dataProviderClass= DataProviders.class)
	public void XP8_DeviceSanity_36_Launch_Photos_and_view_ImageVideo(Hashtable<String, String> data) throws InterruptedException, IOException, AWTException, ParseException 
	{
		SoftAssert sa=new SoftAssert();//144
		APP_LOGS.info("===================== DeviceSanity_36 ======================");
		startAdbLog("DeviceSanity_36");
		clearRecentApps();
		Runtime.getRuntime().exec("adb -s "+p_Id+" shell pm clear com.google.android.apps.photos"); // clear Photos cache
		launch_an_app("camera");
		Configure_camera(sa);
		launch_an_app("photos");
		Configure_PHOTOS(sa);
		sa.assertAll();
	}
	
	@Test(priority= 37,dataProvider="XP8_NewSanityTest", dataProviderClass= DataProviders.class)
	public void XP8_DeviceSanity_37_Launch_Youtube_and_Stream_video(Hashtable<String, String> data) throws InterruptedException, IOException, AWTException, ParseException 
	{
		SoftAssert sa=new SoftAssert();//145
		APP_LOGS.info("===================== DeviceSanity_37 ======================");
		startAdbLog("DeviceSanity_37");
		clearRecentApps();
		
		launch_an_app("settings");
		remove_GoogleAcccount_Orio(sa);
		launch_an_app("settings");
		navigateTo_AddGoogleAccount_Orio(sa);
		add_GoogleAccount(data.get("emailId"), data.get("password"),sa);	
		test.log(LogStatus.PASS, "Preconditions are Set");
		
		Runtime.getRuntime().exec("adb -s "+p_Id+" shell pm clear com.google.android.youtube"); // clear youtube cache
		
		launch_an_app("youtube");
		Search_and_play(sa);

		launch_an_app("settings");
		remove_GoogleAcccount_Orio(sa);
		sa.assertAll();
	}

	@Test(priority= 38,dataProvider="XP8_NewSanityTest", dataProviderClass= DataProviders.class)
	public void XP8_DeviceSanity_38_Launch_calander_and_check_event_notification(Hashtable<String, String> data) throws InterruptedException, IOException, AWTException, ParseException 
	{
		SoftAssert sa=new SoftAssert();//147
		APP_LOGS.info("===================== DeviceSanity_38 ======================");
		startAdbLog("DeviceSanity_38");
		clearRecentApps();
		
		launch_an_app("settings");
		remove_GoogleAcccount_Orio(sa);
		launch_an_app("settings");
		navigateTo_AddGoogleAccount_Orio(sa);
		add_GoogleAccount(data.get("emailId"), data.get("password"),sa);	
		test.log(LogStatus.PASS, "Preconditions are Set");
		
		Runtime.getRuntime().exec("adb -s "+p_Id+" shell pm clear com.google.android.calendar"); // clear calendar cache
		
		launch_an_app("calendar");
		Configure_calendar(sa);
		launch_an_app("calendar");
		Check_for_notification(sa); 
		launch_an_app("settings");
		remove_GoogleAcccount_Orio(sa);
		sa.assertAll();
	}
	
	@Test(priority= 39,dataProvider="XP8_NewSanityTest", dataProviderClass= DataProviders.class)
	public void XP8_DeviceSanity_39_Launch_Filemanager_and_browse_files(Hashtable<String, String> data) throws InterruptedException, IOException, AWTException, ParseException 
	{
		SoftAssert sa=new SoftAssert();//153
		APP_LOGS.info("===================== DeviceSanity_39 ======================");
		startAdbLog("DeviceSanity_39");
		clearRecentApps();
		Runtime.getRuntime().exec("adb -s "+p_Id+" shell pm clear org.codeaurora.snapcam"); // clear cam cache
		launch_an_app("camera");
		click_picture();
		Runtime.getRuntime().exec("adb -s "+p_Id+" shell pm clear com.cyanogenmod.filemanager"); // clear filemanager cache
		launch_an_app("fileManager");
		browse_files();
		sa.assertAll();
	}
	
	@Test(priority= 40,dataProvider="XP8_NewSanityTest", dataProviderClass= DataProviders.class)
	public void XP8_DeviceSanity_40_Launch_Sound_recorder_and_check_recording(Hashtable<String, String> data) throws InterruptedException, IOException, AWTException, ParseException 
	{
		SoftAssert sa=new SoftAssert();//154
		APP_LOGS.info("===================== DeviceSanity_40 ======================");
		startAdbLog("DeviceSanity_40");
		clearRecentApps();
		Runtime.getRuntime().exec("adb -s "+p_Id+" shell pm clear com.android.soundrecorder"); // clear soundrecorder cache
		launch_an_app("soundRecorder");
		start_record();
		Runtime.getRuntime().exec("adb -s "+p_Id+" shell pm clear com.cyanogenmod.filemanager"); // clear filemanager cache
		launch_an_app("fileManager");
		browse_Audio_files();
		sa.assertAll();
	}
	
	@Test(priority= 41,dataProvider="XP8_NewSanityTest", dataProviderClass= DataProviders.class)
	public void XP8_DeviceSanity_41_Turn_ON_and_OFF_Autorotate_from_QuickSettings(Hashtable<String, String> data) throws InterruptedException, IOException, AWTException, ParseException 
	{
		SoftAssert sa=new SoftAssert();//164
		APP_LOGS.info("===================== DeviceSanity_41 ======================");
		startAdbLog("DeviceSanity_41");
		clearRecentApps();
		Runtime.getRuntime().exec("adb -s "+p_Id+" shell cmd statusbar expand-settings"); // expand quick settings
		check_Autorotate();
		sa.assertAll();
	}

	@Test(priority= 42,dataProvider="XP8_NewSanityTest", dataProviderClass= DataProviders.class)
	public void XP8_DeviceSanity_42_Ensure_SIM_PIN_lock_could_be_enabled_FromSettings(Hashtable<String, String> data) throws InterruptedException, IOException, AWTException, ParseException 
	{
		SoftAssert sa=new SoftAssert();//186
		APP_LOGS.info("===================== DeviceSanity_42 ======================");
		startAdbLog("DeviceSanity_42");
		clearRecentApps();
		launch_an_app("settings");
		Lock_UnLock_SIM_card();
		sa.assertAll();
	}

	@Test(priority= 43,dataProvider="XP8_NewSanityTest", dataProviderClass= DataProviders.class)
	public void XP8_DeviceSanity_43_Add_contact_to_SIMcard(Hashtable<String, String> data) throws InterruptedException, IOException, AWTException, ParseException 
	{
		SoftAssert sa=new SoftAssert();//188
		APP_LOGS.info("===================== DeviceSanity_43 ======================");
		startAdbLog("DeviceSanity_43");
		clearRecentApps();
		launch_an_app("contacts");
		deleteContacts();
		addcontactsTO_SIM(data.get("name"), data.get("Phone1"), data.get("Phone2"), data.get("email"),sa);
		sa.assertAll();
	}

	/////// 43  & 44 can be merge(dependent)  ///////////////

	@Test(priority= 44,dataProvider="XP8_NewSanityTest", dataProviderClass= DataProviders.class)
	public void XP8_DeviceSanity_44_user_can_delete_desired_contact_from_SIMcard(Hashtable<String, String> data) throws InterruptedException, IOException, AWTException, ParseException 
	{
		SoftAssert sa=new SoftAssert();//189
		APP_LOGS.info("===================== DeviceSanity_44 ======================");
		startAdbLog("DeviceSanity_44");
		clearRecentApps();
		launch_an_app("contacts");
		deleteSIMContacts();
		sa.assertAll();
	}

	@Test(priority= 45,dataProvider="XP8_NewSanityTest", dataProviderClass= DataProviders.class)
	public void XP8_DeviceSanity_45_Add_new_contact_to_PHONE(Hashtable<String, String> data) throws InterruptedException, IOException, AWTException, ParseException 
	{
		SoftAssert sa=new SoftAssert();//190
		APP_LOGS.info("===================== DeviceSanity_45 ======================");
		startAdbLog("DeviceSanity_45");
		clearRecentApps();
		launch_an_app("contacts");
		deleteContacts();
		addcontactsTO_Phone(data.get("name"), data.get("Phone1"),data.get("email"), data.get("Address"));
		sa.assertAll();
	}

	@Test(priority= 46,dataProvider="XP8_NewSanityTest", dataProviderClass= DataProviders.class)
	public void XP8_DeviceSanity_46_make_Call_to_any_contact_PHONEBook(Hashtable<String, String> data) throws InterruptedException, IOException, AWTException, ParseException 
	{
		SoftAssert sa=new SoftAssert();//191
		APP_LOGS.info("===================== DeviceSanity_46 ======================");
		startAdbLog("DeviceSanity_46");
		clearRecentApps();
		launch_an_app("contacts");
		deleteContacts();
		addcontactsTO_Phone(data.get("name1"), data.get("Phone1"),data.get("email1"), data.get("Address1"));
		clearRecentApps();
		launch_an_app("contacts");
		addcontactsTO_SIM(data.get("name2"), data.get("Phone1"), data.get("Phone2"), data.get("email2"),sa);
		//	launch_an_app("contacts");
		Make_call_to_contact();
		sa.assertAll();
	}

	@Test(priority= 47,dataProvider="XP8_NewSanityTest", dataProviderClass= DataProviders.class)
	public void XP8_DeviceSanity_47_user_can_delete_desired_contact_from_PHONE(Hashtable<String, String> data) throws InterruptedException, IOException, AWTException, ParseException 
	{
		SoftAssert sa=new SoftAssert();//192
		APP_LOGS.info("===================== DeviceSanity_47 ======================");
		startAdbLog("DeviceSanity_47");
		clearRecentApps();
		launch_an_app("contacts");
		deletePHONEContacts();
		sa.assertAll();
	}

	@Test(priority= 48,dataProvider="XP8_NewSanityTest", dataProviderClass= DataProviders.class)
	public void XP8_DeviceSanity_48_Launch_SonimScout_Check_Module(Hashtable<String, String> data) throws InterruptedException, IOException, AWTException, ParseException 
	{
		SoftAssert sa=new SoftAssert();//198
		APP_LOGS.info("===================== DeviceSanity_48 ======================");
		startAdbLog("DeviceSanity_48");
		clearRecentApps();
		//		String fN = startAdbLog();		
		//		launch_An_App(dt.get("App_Package"),dt.get("App_Activity")); // launching camera screen
		launch_an_app("scout");
		Check_for_Scoutmodule();
		sa.assertAll();
	}
	
	/////////////////////////////Gobi Test cases Completed ///////////////////////////////////
	
	@Test(priority = 49, dataProvider = "XP8_NewSanityTest", dataProviderClass = DataProviders.class) // 178
	public void XP8_DeviceSanity_0049_check_user_able_to_receiveMT_Call_whileBrowsing(Hashtable<String, String> dt)
			throws InterruptedException, IOException, AWTException {

		APP_LOGS.info("===================== XP8_DeviceSanity_0049_check_user_able_to_receiveMT_Call_whileBrowsing ======================");
		SoftAssert sa = new SoftAssert();
	
		  customWait(2000);
		  launch_An_App(dt.get("N_I_Package"),dt.get("N_I_Activity")); // launching  network setting
		  clickBtn(New_SanityLocators.Wifi_wifiLnk); 
		  turnOnWifi();
		  customWait(3000);
		  remove_connectedNtwrk();
		  customWait(3000);
		  connect_to_WiFi(ssid, pwsd);
		  customWait(3000); clearChromeCache();
		  Launch_App("browser"); 
		  clearChromePermission(); 
		  validate_And_BrowseIn_Chrome(sa); 
		  make_Call_from_RefDev();
		  customWait(5000);
		  recieve_Call_PrimaryDev_O(); 
		  customWait(5000); 
		  end_Call(); 
		  customWait(3000);
		  launch_an_app("phone");
		  validate_callHistory(sa,refNum);
		  sa.assertAll();
	}
	
	/*@Test(priority = 16, dataProvider = "XP8_NewSanityTest", dataProviderClass = DataProviders.class) // 137
	public void XP8_DeviceSanity_0020_Enable_Disable_AirplaneMode_In_QuickStng(Hashtable<String, String> dt)
			throws InterruptedException, IOException, AWTException {
		APP_LOGS.info("===================== XP8_DeviceSanity_0020_Enable_Disable_AirplaneMode_In_QuickStng ======================");
		SoftAssert sa = new SoftAssert();
		launch_APP(New_SanityLocators.phone);
		swipe_NotificationBar();
		swipe_QuickPanel_SecondPage();
		enable_Shortcuts_In_QuickPanel(New_SanityLocators.airplane_OffState_QuickPanel_O);
		launch_APP(New_SanityLocators.phone);
		dialCallUsingDialPad(refNum);
		validate_Airplane_Enable(refNum);
		swipe_NotificationBar();
		swipe_QuickPanel_SecondPage();
		disable_Shortcuts_QuickPanel(New_SanityLocators.airplane_OnState_QuickPanel_O);
		launch_APP(New_SanityLocators.phone);
		dialCallUsingDialPad(refNum);
		validate_Airplane_Disable(refNum);
		end_Call();
		sa.assertAll();
	}*/
	
	/*@Test(priority = 50, dataProvider = "XP8_NewSanityTest", dataProviderClass = DataProviders.class) // 82
	public void XP8_DeviceSanity_0050_RejectMT_call_With_SMS(Hashtable<String, String> dt)
			throws InterruptedException, IOException, AWTException {
		APP_LOGS.info("===================== XP8_DeviceSanity_0021_RejectMT_call_With_SMS ======================");
		SoftAssert sa = new SoftAssert();
		endCall_RefDevice();
		launch_An_App(dt.get("message_package"), dt.get("message_activity"));
		make_Call_from_RefDev();
		customWait(2000);
		reject_Call_With_SMS_O(dt.get("message"));
		customWait(4000);
		endCall_RefDevice();
		validate_SentMessage(sa);
		delete_SMS();
		sa.assertAll();
	}*/
	
}
