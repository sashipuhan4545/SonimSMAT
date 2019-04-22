package com.xp5S.test;

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

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTestInterruptedException;
import com.relevantcodes.extentreports.LogStatus;
import com.xp5S.util.DataProviders;
import com.xp5S.util.ExcelConstants;
import com.xp5S.util.ExcelReader;
import com.xp5S.util.GetMethods;
import com.xp5S.util.Locators_BaseUtil;
import com.xp5S.util.SCOUT_Locators_SonimCare;
import com.xp5S.util.XP5S_SCOUT_SonimCare_Util;
import com.xp5S.util.appiumService;

import io.appium.java_client.android.AndroidKeyCode;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class XP5S_SCOUT_SonimCare_Test extends XP5S_SCOUT_SonimCare_Util{

	public ExcelReader excel;
	Properties properties;
	public static ExtentTestInterruptedException testexception;


	@BeforeClass
	public void copyFilesToDevice() throws IOException {
		System.out.println("Executing clear log screen");
		File dir = new File("src/test/resources/adbLogs");
		if(dir.isDirectory() == false) {
			System.out.println("Not a directory. Do nothing");
			return;
		}
		//Added comment to test
		File[] listFiles = dir.listFiles();
		for(File file : listFiles){
			System.out.println("Deleting "+file.getName());
			file.delete();
		}

	}

	@BeforeSuite
	public void beforeSuite() throws IOException, InterruptedException, ParseException 
	{
		extent = new ExtentReports("src/test/resources/extentreport/XP5S_SCOUT_SonimCare_TestReport.html", true); 
		fetch_Devices_Details();
		try {
			appiumService.TOTAL_NUM_OF_TESTCASES=GetMethods.TotalTestcase("XP5S_TC_", this.getClass());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@BeforeMethod()
	public  void beforeMethod(Method method) throws InterruptedException 
	{

		test = extent.startTest( (this.getClass().getSimpleName() +" :: "+  method.getName()),method.getName()); //Test Case Start Here
		test.assignAuthor(""); //Test Script Author Name
		extent.loadConfig(new File("src/test/resources/StorageFile/ReportsConfig.xml"));
		extent.addSystemInfo("Appium Version", "1.2.7").addSystemInfo("Environment", "TEST");
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
		SCOUT_Locators_SonimCare loc=new SCOUT_Locators_SonimCare(aDriver);
		Locators_BaseUtil loc1 =new Locators_BaseUtil(aDriver);
		PageFactory.initElements(new AppiumFieldDecorator(getDriver()),loc1);
		PageFactory.initElements(new AppiumFieldDecorator(getDriver()),loc);
		excel=new ExcelReader(ExcelConstants.XP5S_XL_PATH);
	}

	//================================================Test cases==============================================
	@Test(priority=1,dataProvider="XP5S_SonimCare", dataProviderClass=DataProviders.class)
	public void XP5S_TC_001_Launch_SonimCare_Application_from_SCOUTAPP(Hashtable<String, String> data) throws AWTException, InterruptedException, IOException, ParseException {

		APP_LOGS.info("=========== XP5S_TC_001_Launch_SonimCare_Application_from_SCOUTAPP============");
		startAdbLog("XP5S_SonimCare_001");
		Launch_SonimCare();
		validateLaunchScoutApp(SCOUT_Locators_SonimCare.SonimCare, "SonimCare");
	}
	
	@Test(priority=2,dataProvider="XP5S_SonimCare", dataProviderClass=DataProviders.class)
	public void XP5S_TC_002_Validate_pressence_of_all_options_in_Sonimcare(Hashtable<String, String> data) throws AWTException, InterruptedException, IOException, ParseException {

		APP_LOGS.info("=========== XP5S_TC_002_Validate_pressence_of_all_options_in_Sonimcare============");
		startAdbLog("XP5S_SonimCare_002");
		Launch_SonimCare();
		validatePressence_of_AllOptions();
	}
	
	@Test(priority=3,dataProvider="XP5S_SonimCare", dataProviderClass=DataProviders.class)
	public void XP5S_TC_003_Validate_self_diagnosis_welcome_screen_in_Sonimcare(Hashtable<String, String> data) throws AWTException, InterruptedException, IOException, ParseException {

		APP_LOGS.info("=========== XP5S_TC_003_Validate_self_diagnosis_welcome_screen_in_Sonimcare============");
		startAdbLog("XP5S_SonimCare_003");
		Launch_SonimCare();
		navigate_and_validate_self_diagnosis_welcome_screen();
	}
	
	@Test(priority=4,dataProvider="XP5S_SonimCare", dataProviderClass=DataProviders.class)
	public void XP5S_TC_004_Validate_Wi_Fi_in_Self_Diagnosis(Hashtable<String, String> data) throws AWTException, InterruptedException, IOException, ParseException {

		APP_LOGS.info("=========== XP5S_TC_004_Validate_Wi_Fi_in_Self_Diagnosis============");
		startAdbLog("XP5S_SonimCare_004");
		Launch_SonimCare();
		navigate_to_module(SCOUT_Locators_SonimCare.wi_fi, "WI-FI");
		testWifiModule_and_validate();
	}
	
	@Test(priority=5,dataProvider="XP5S_SonimCare", dataProviderClass=DataProviders.class)
	public void XP5S_TC_005_Validate_Bluetooth_in_Self_Diagnosis(Hashtable<String, String> data) throws AWTException, InterruptedException, IOException, ParseException {

		APP_LOGS.info("=========== XP5S_TC_005_Validate_Bluetooth_in_Self_Diagnosis============");
		startAdbLog("XP5S_SonimCare_005");
		Launch_SonimCare();
		navigate_to_module(SCOUT_Locators_SonimCare.Bluetooth, "BLUETOOTH");
		testBluetoothModule_and_validate();
	}
	
	@Test(priority=6,dataProvider="XP5S_SonimCare", dataProviderClass=DataProviders.class)
	public void XP5S_TC_006_Validate_Back_Camera_in_Self_Diagnosis(Hashtable<String, String> data) throws AWTException, InterruptedException, IOException, ParseException {

		APP_LOGS.info("=========== XP5S_TC_006_Validate_Back_Camera_in_Self_Diagnosis============");
		startAdbLog("XP5S_SonimCare_006");
		aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_HOME);
		if(isElementExist(SCOUT_Locators_SonimCare.Camera)) {
			Launch_SonimCare();
			navigate_to_module(SCOUT_Locators_SonimCare.Back_Camera, "BACK CAMERA");
			test_BackCamera_Module_and_validate();
			Launch_SonimCare();
			navigate_to_module(SCOUT_Locators_SonimCare.Back_Camera, "BACK CAMERA");
			validate_add_image_cancel_functionality();
		}else {
			test.log(LogStatus.SKIP, "Device does not support Camera");
			test.log(LogStatus.SKIP, "Test case status is Skipped");
		}
	} 

	@Test(priority=7,dataProvider="XP5S_SonimCare", dataProviderClass=DataProviders.class)
	public void XP5S_TC_007_Validate_FlashLight_in_Self_Diagnosis(Hashtable<String, String> data) throws AWTException, InterruptedException, IOException, ParseException {

		APP_LOGS.info("=========== XP5S_TC_007_Validate_FlashLight_in_Self_Diagnosis============");
		startAdbLog("XP5S_SonimCare_007");
		Launch_SonimCare();
		navigate_to_module(SCOUT_Locators_SonimCare.Flashlight, "FLASHLIGHT");
		test_flashlight_Module_and_validate();
	}
	
	@Test(priority=8,dataProvider="XP5S_SonimCare", dataProviderClass=DataProviders.class)
	public void XP5S_TC_008_Validate_Display_in_Self_Diagnosis(Hashtable<String, String> data) throws AWTException, InterruptedException, IOException, ParseException {

		APP_LOGS.info("=========== XP5S_TC_008_Validate_Display_in_Self_Diagnosis============");
		startAdbLog("XP5S_SonimCare_008");
		Launch_SonimCare();
		navigate_to_module(SCOUT_Locators_SonimCare.Display, "DISPLAY");
		test_display_Module_and_validate();
	}
	
	@Test(priority=9,dataProvider="XP5S_SonimCare", dataProviderClass=DataProviders.class)
	public void XP5S_TC_09_Validate_Battery_in_Self_Diagnosis(Hashtable<String, String> data) throws AWTException, InterruptedException, IOException, ParseException {

		APP_LOGS.info("=========== XP5S_TC_009_Validate_Battery_in_Self_Diagnosis============");
		startAdbLog("XP5S_SonimCare_009");
		Launch_SonimCare();
		navigate_to_module(SCOUT_Locators_SonimCare.Battery, "BATTERY");
		test_Battery_Module_and_validate();
	}
	
	@Test(priority=10,dataProvider="XP5S_SonimCare", dataProviderClass=DataProviders.class)
	public void XP5S_TC_010_Validate_Vibration_in_Self_Diagnosis(Hashtable<String, String> data) throws AWTException, InterruptedException, IOException, ParseException {

		APP_LOGS.info("=========== XP5S_TC_010_Validate_Vibration_in_Self_Diagnosis============");
		startAdbLog("XP5S_SonimCare_010");
		Launch_SonimCare();
		navigate_to_module(SCOUT_Locators_SonimCare.Vibration, "VIBRATION");
		test_vibration_Module_and_validate();
	}
	
	@Test(priority=11,dataProvider="XP5S_SonimCare", dataProviderClass=DataProviders.class)
	public void XP5S_TC_011_Validate_Storage_Info_in_Self_Diagnosis(Hashtable<String, String> data) throws AWTException, InterruptedException, IOException, ParseException {

		APP_LOGS.info("=========== XP5S_TC_011_Validate_Storage_Info_in_Self_Diagnosis============");
		startAdbLog("XP5S_SonimCare_011");
		Launch_SonimCare();
		navigate_to_storageInfo("CLEAR CACHE MEMORY");
		test_Storage_Info_Module_and_validate();
	}
	
	@Test(priority=12,dataProvider="XP5S_SonimCare", dataProviderClass=DataProviders.class)
	public void XP5S_TC_012_Validate_Clear_Cache_Memory_in_Self_Diagnosis(Hashtable<String, String> data) throws AWTException, InterruptedException, IOException, ParseException {

		APP_LOGS.info("=========== XP5S_TC_012_Validate_Clear_Cache_Memory_in_Self_Diagnosis============");
		startAdbLog("XP5S_SonimCare_012");
		Launch_SonimCare();
		navigate_to_module(SCOUT_Locators_SonimCare.Clear_Cache_Memory, "CLEAR CACHE MEMORY");
		test_clear_cache_memory_Module_and_validate();
	}
	
	@Test(priority=13,dataProvider="XP5S_SonimCare", dataProviderClass=DataProviders.class)
	public void XP5S_TC_013_Validate_No_Option_In_WiFi_module(Hashtable<String, String> data) throws AWTException, InterruptedException, IOException, ParseException {

		APP_LOGS.info("=========== XP5S_TC_013_Validate_No_Option_In_WiFi_module============");
		startAdbLog("XP5S_SonimCare_013");
		Launch_SonimCare();
		navigate_to_module(SCOUT_Locators_SonimCare.wi_fi, "WI-FI");
		validate_wifi_no_option();
	}
	
	@Test(priority=14,dataProvider="XP5S_SonimCare", dataProviderClass=DataProviders.class)
	public void XP5S_TC_014_Validate_No_Option_In_Bluetooth_module(Hashtable<String, String> data) throws AWTException, InterruptedException, IOException, ParseException {

		APP_LOGS.info("=========== XP5S_TC_014_Validate_No_Option_In_Bluetooth_module============");
		startAdbLog("XP5S_SonimCare_014");
		Launch_SonimCare();
		navigate_to_module(SCOUT_Locators_SonimCare.Bluetooth, "BLUETOOTH");
		validate_bluetooth_no_option();
	}
	
	@Test(priority=15,dataProvider="XP5S_SonimCare", dataProviderClass=DataProviders.class)
	public void XP5S_TC_015_Validate_No_Option_In_BackCamera_module(Hashtable<String, String> data) throws AWTException, InterruptedException, IOException, ParseException {

		APP_LOGS.info("=========== XP5S_TC_015_Validate_No_Option_In_BackCamera_module============");
		startAdbLog("XP5S_SonimCare_015");
		aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_HOME);
		if(isElementExist(SCOUT_Locators_SonimCare.Camera)) {
			Launch_SonimCare();
			navigate_to_module(SCOUT_Locators_SonimCare.Back_Camera, "BACK CAMERA");
			validate_back_camera_no_option();
		}else {
			test.log(LogStatus.SKIP, "Device does not support Camera");
			test.log(LogStatus.SKIP, "Test case status is Skipped");
		}
	}
	
	@Test(priority=16,dataProvider="XP5S_SonimCare", dataProviderClass=DataProviders.class)
	public void XP5S_TC_016_Validate_No_Option_In_Flashlight_module(Hashtable<String, String> data) throws AWTException, InterruptedException, IOException, ParseException {

		APP_LOGS.info("=========== XP5S_TC_016_Validate_No_Option_In_Flashlight_module============");
		startAdbLog("XP5S_SonimCare_016");
		Launch_SonimCare();
		navigate_to_module(SCOUT_Locators_SonimCare.Flashlight, "FLASHLIGHT");
		validate_flashlight_no_option();
	}
	
	@Test(priority=17,dataProvider="XP5S_SonimCare", dataProviderClass=DataProviders.class)
	public void XP5S_TC_017_Validate_No_Option_In_Display_module(Hashtable<String, String> data) throws AWTException, InterruptedException, IOException, ParseException {

		APP_LOGS.info("=========== XP5S_TC_017_Validate_No_Option_In_Display_module============");
		startAdbLog("XP5S_SonimCare_017");
		Launch_SonimCare();
		navigate_to_module(SCOUT_Locators_SonimCare.Display, "DISPLAY");
		validate_display_no_option();
	}
	
	@Test(priority=18,dataProvider="XP5S_SonimCare", dataProviderClass=DataProviders.class)
	public void XP5S_TC_018_Validate_No_Option_In_Battery_module(Hashtable<String, String> data) throws AWTException, InterruptedException, IOException, ParseException {

		APP_LOGS.info("=========== XP5S_TC_018_Validate_No_Option_In_Battery_module============");
		startAdbLog("XP5S_SonimCare_018");
		Launch_SonimCare();
		navigate_to_module(SCOUT_Locators_SonimCare.Battery, "BATTERY");
		validate_battery_no_option();
	}
	
	@Test(priority=19,dataProvider="XP5S_SonimCare", dataProviderClass=DataProviders.class)
	public void XP5S_TC_019_Validate_No_Option_In_Vibration_module(Hashtable<String, String> data) throws AWTException, InterruptedException, IOException, ParseException {

		APP_LOGS.info("=========== XP5S_TC_019_Validate_No_Option_In_Vibration_module============");
		startAdbLog("XP5S_SonimCare_019");
		Launch_SonimCare();
		navigate_to_module(SCOUT_Locators_SonimCare.Vibration, "VIBRATION");
		validate_vibration_no_option();
	}
	
	@Test(priority=20,dataProvider="XP5S_SonimCare", dataProviderClass=DataProviders.class)
	public void XP5S_TC_020_Validate_Battery_diagnosis_welcome_screen_in_Sonimcare(Hashtable<String, String> data) throws AWTException, InterruptedException, IOException, ParseException {

		APP_LOGS.info("=========== XP5S_TC_020_Validate_Battery_diagnosis_welcome_screen_in_Sonimcare============");
		startAdbLog("XP5S_SonimCare_020");
		Launch_SonimCare();
		navigate_and_validate_battery_diagnosis_welcome_screen();
	}
	
	@Test(priority=21,dataProvider="XP5S_SonimCare", dataProviderClass=DataProviders.class)
	public void XP5S_TC_021_Validate_Battery_Information_In_Battery_Diagnosis(Hashtable<String, String> data) throws AWTException, InterruptedException, IOException, ParseException {

		APP_LOGS.info("=========== XP5S_TC_021_Validate_Battery_Information_In_Battery_Diagnosis============");
		startAdbLog("XP5S_SonimCare_021");
		Launch_SonimCare();
		validate_battery_information();
	}
	
	@Test(priority=22,dataProvider="XP5S_SonimCare", dataProviderClass=DataProviders.class)
	public void XP5S_TC_022_Validate_Battery_Service_History_In_Battery_Diagnosis(Hashtable<String, String> data) throws AWTException, InterruptedException, IOException, ParseException {

		APP_LOGS.info("=========== XP5S_TC_022_Validate_Battery_Service_History_In_Battery_Diagnosis============");
		startAdbLog("XP5S_SonimCare_022");
		Launch_SonimCare();
		validateBatteryServiceHistory();
	}
	
	@Test(priority=23,dataProvider="XP5S_SonimCare", dataProviderClass=DataProviders.class)
	public void XP5S_TC_023_Validate_Diagnosis_Report(Hashtable<String, String> data) throws AWTException, InterruptedException, IOException, ParseException {

		APP_LOGS.info("=========== XP5S_TC_023_Validate_Diagnosis_Report============");
		startAdbLog("XP5S_SonimCare_023");
		disableCellularData();
		ConnectSecuredWifi();
		Launch_SonimCare();
		validate_diagnosis_report();
	}
	
}
