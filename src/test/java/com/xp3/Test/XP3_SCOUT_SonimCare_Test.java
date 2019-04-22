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
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTestInterruptedException;
import com.relevantcodes.extentreports.LogStatus;
import com.xp3.Utils.Locators_BaseUtil;
import com.xp3.Utils.Locators_SonimCare;
import com.xp3.Utils.XP3_SCOUT_SonimCare_Util;
import com.xp5S.util.DataProviders;
import com.xp5S.util.ExcelConstants;
import com.xp5S.util.ExcelReader;
import com.xp5S.util.GetMethods;
import com.xp5S.util.appiumService;

import io.appium.java_client.android.AndroidKeyCode;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class XP3_SCOUT_SonimCare_Test extends XP3_SCOUT_SonimCare_Util{

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
		extent = new ExtentReports("src/test/resources/extentreport/XP3_SCOUT_SonimCare_TestReport.html", true); 
		fetch_Devices_Details();
		try {
			appiumService.TOTAL_NUM_OF_TESTCASES=GetMethods.TotalTestcase("XP3_TC_", this.getClass());
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
		Locators_SonimCare loc=new Locators_SonimCare(aDriver);
		Locators_BaseUtil loc1 =new Locators_BaseUtil(aDriver);
		PageFactory.initElements(new AppiumFieldDecorator(getDriver()),loc1);
		PageFactory.initElements(new AppiumFieldDecorator(getDriver()),loc);
		excel=new ExcelReader(ExcelConstants.XP5S_XL_PATH);
	}

	//================================================Test cases==============================================
	@Test(priority=1,dataProvider="XP3_SonimCare", dataProviderClass=DataProviders.class)
	public void XP3_TC_001_Launch_SonimCare_Application_from_SCOUTAPP(Hashtable<String, String> data) throws AWTException, InterruptedException, IOException, ParseException {

		APP_LOGS.info("=========== XP3_TC_001_Launch_SonimCare_Application_from_SCOUTAPP============");
		startAdbLog("XP3_SonimCare_001");
		Launch_SonimCare();
		validateLaunchScoutApp(Locators_SonimCare.SonimCare, "SonimCare");
	}
	
	@Test(priority=2,dataProvider="XP3_SonimCare", dataProviderClass=DataProviders.class)
	public void XP3_TC_002_Validate_pressence_of_all_options_in_Sonimcare(Hashtable<String, String> data) throws AWTException, InterruptedException, IOException, ParseException {

		APP_LOGS.info("=========== XP3_TC_002_Validate_pressence_of_all_options_in_Sonimcare============");
		startAdbLog("XP3_SonimCare_002");
		Launch_SonimCare();
		validatePressence_of_AllOptions();
	}
	
	@Test(priority=3,dataProvider="XP3_SonimCare", dataProviderClass=DataProviders.class)
	public void XP3_TC_003_Validate_self_diagnosis_welcome_screen_in_Sonimcare(Hashtable<String, String> data) throws AWTException, InterruptedException, IOException, ParseException {

		APP_LOGS.info("=========== XP3_TC_003_Validate_self_diagnosis_welcome_screen_in_Sonimcare============");
		startAdbLog("XP3_SonimCare_003");
		Launch_SonimCare();
		navigate_and_validate_self_diagnosis_welcome_screen();
	}
	
	@Test(priority=4,dataProvider="XP3_SonimCare", dataProviderClass=DataProviders.class)
	public void XP3_TC_004_Validate_Wi_Fi_in_Self_Diagnosis(Hashtable<String, String> data) throws AWTException, InterruptedException, IOException, ParseException {

		APP_LOGS.info("=========== XP3_TC_004_Validate_Wi_Fi_in_Self_Diagnosis============");
		startAdbLog("XP3_SonimCare_004");
		Launch_SonimCare();
		navigate_to_module(Locators_SonimCare.wi_fi, "WI-FI");
		testWifiModule_and_validate();
	}
	
	@Test(priority=5,dataProvider="XP3_SonimCare", dataProviderClass=DataProviders.class)
	public void XP3_TC_005_Validate_Bluetooth_in_Self_Diagnosis(Hashtable<String, String> data) throws AWTException, InterruptedException, IOException, ParseException {

		APP_LOGS.info("=========== XP3_TC_005_Validate_Bluetooth_in_Self_Diagnosis============");
		startAdbLog("XP3_SonimCare_005");
		Launch_SonimCare();
		navigate_to_module(Locators_SonimCare.Bluetooth, "BLUETOOTH");
		testBluetoothModule_and_validate();
	}
	
	@Test(priority=6,dataProvider="XP3_SonimCare", dataProviderClass=DataProviders.class)
	public void XP3_TC_006_Validate_Back_Camera_in_Self_Diagnosis(Hashtable<String, String> data) throws AWTException, InterruptedException, IOException, ParseException {

		APP_LOGS.info("=========== XP3_TC_006_Validate_Back_Camera_in_Self_Diagnosis============");
		startAdbLog("XP3_SonimCare_006");
		aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_HOME);
		if(isElementExist(Locators_SonimCare.Camera)) {
			Launch_SonimCare();
			navigate_to_module(Locators_SonimCare.Back_Camera, "BACK CAMERA");
			test_BackCamera_Module_and_validate();
			Launch_SonimCare();
			navigate_to_module(Locators_SonimCare.Back_Camera, "BACK CAMERA");
			validate_add_image_cancel_functionality();
		}else {
			test.log(LogStatus.SKIP, "Device does not support Camera");
			test.log(LogStatus.SKIP, "Test case status is Skipped");
		}
	} 

	@Test(priority=7,dataProvider="XP3_SonimCare", dataProviderClass=DataProviders.class)
	public void XP3_TC_007_Validate_FlashLight_in_Self_Diagnosis(Hashtable<String, String> data) throws AWTException, InterruptedException, IOException, ParseException {

		APP_LOGS.info("=========== XP3_TC_007_Validate_FlashLight_in_Self_Diagnosis============");
		startAdbLog("XP3_SonimCare_007");
		Launch_SonimCare();
		navigate_to_module(Locators_SonimCare.Flashlight, "FLASHLIGHT");
		test_flashlight_Module_and_validate();
	}
	
	@Test(priority=8,dataProvider="XP3_SonimCare", dataProviderClass=DataProviders.class)
	public void XP3_TC_008_Validate_Display_in_Self_Diagnosis(Hashtable<String, String> data) throws AWTException, InterruptedException, IOException, ParseException {

		APP_LOGS.info("=========== XP3_TC_008_Validate_Display_in_Self_Diagnosis============");
		startAdbLog("XP3_SonimCare_008");
		Launch_SonimCare();
		navigate_to_module(Locators_SonimCare.Display, "DISPLAY");
		test_display_Module_and_validate();
	}
	
	@Test(priority=9,dataProvider="XP3_SonimCare", dataProviderClass=DataProviders.class)
	public void XP3_TC_09_Validate_Battery_in_Self_Diagnosis(Hashtable<String, String> data) throws AWTException, InterruptedException, IOException, ParseException {

		APP_LOGS.info("=========== XP3_TC_009_Validate_Battery_in_Self_Diagnosis============");
		startAdbLog("XP3_SonimCare_009");
		Launch_SonimCare();
		navigate_to_module(Locators_SonimCare.Battery, "BATTERY");
		test_Battery_Module_and_validate();
	}
	
	@Test(priority=10,dataProvider="XP3_SonimCare", dataProviderClass=DataProviders.class)
	public void XP3_TC_010_Validate_Vibration_in_Self_Diagnosis(Hashtable<String, String> data) throws AWTException, InterruptedException, IOException, ParseException {

		APP_LOGS.info("=========== XP3_TC_010_Validate_Vibration_in_Self_Diagnosis============");
		startAdbLog("XP3_SonimCare_010");
		Launch_SonimCare();
		navigate_to_module(Locators_SonimCare.Vibration, "VIBRATION");
		test_vibration_Module_and_validate();
	}
	
	@Test(priority=11,dataProvider="XP3_SonimCare", dataProviderClass=DataProviders.class)
	public void XP3_TC_011_Validate_Storage_Info_in_Self_Diagnosis(Hashtable<String, String> data) throws AWTException, InterruptedException, IOException, ParseException {

		APP_LOGS.info("=========== XP3_TC_011_Validate_Storage_Info_in_Self_Diagnosis============");
		startAdbLog("XP3_SonimCare_011");
		Launch_SonimCare();
		navigate_to_storageInfo("CLEAR CACHE MEMORY");
		test_Storage_Info_Module_and_validate();
	}
	
	@Test(priority=12,dataProvider="XP3_SonimCare", dataProviderClass=DataProviders.class)
	public void XP3_TC_012_Validate_Clear_Cache_Memory_in_Self_Diagnosis(Hashtable<String, String> data) throws AWTException, InterruptedException, IOException, ParseException {

		APP_LOGS.info("=========== XP3_TC_012_Validate_Clear_Cache_Memory_in_Self_Diagnosis============");
		startAdbLog("XP3_SonimCare_012");
		Launch_SonimCare();
		navigate_to_module(Locators_SonimCare.Clear_Cache_Memory, "CLEAR CACHE MEMORY");
		test_clear_cache_memory_Module_and_validate();
	}
	
	@Test(priority=13,dataProvider="XP3_SonimCare", dataProviderClass=DataProviders.class)
	public void XP3_TC_013_Validate_No_Option_In_WiFi_module(Hashtable<String, String> data) throws AWTException, InterruptedException, IOException, ParseException {

		APP_LOGS.info("=========== XP3_TC_013_Validate_No_Option_In_WiFi_module============");
		startAdbLog("XP3_SonimCare_013");
		Launch_SonimCare();
		navigate_to_module(Locators_SonimCare.wi_fi, "WI-FI");
		validate_wifi_no_option();
	}
	
	@Test(priority=14,dataProvider="XP3_SonimCare", dataProviderClass=DataProviders.class)
	public void XP3_TC_014_Validate_No_Option_In_Bluetooth_module(Hashtable<String, String> data) throws AWTException, InterruptedException, IOException, ParseException {

		APP_LOGS.info("=========== XP3_TC_014_Validate_No_Option_In_Bluetooth_module============");
		startAdbLog("XP3_SonimCare_014");
		Launch_SonimCare();
		navigate_to_module(Locators_SonimCare.Bluetooth, "BLUETOOTH");
		validate_bluetooth_no_option();
	}
	
	@Test(priority=15,dataProvider="XP3_SonimCare", dataProviderClass=DataProviders.class)
	public void XP3_TC_015_Validate_No_Option_In_BackCamera_module(Hashtable<String, String> data) throws AWTException, InterruptedException, IOException, ParseException {

		APP_LOGS.info("=========== XP3_TC_015_Validate_No_Option_In_BackCamera_module============");
		startAdbLog("XP3_SonimCare_015");
		aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_HOME);
		if(isElementExist(Locators_SonimCare.Camera)) {
			Launch_SonimCare();
			navigate_to_module(Locators_SonimCare.Back_Camera, "BACK CAMERA");
			validate_back_camera_no_option();
		}else {
			test.log(LogStatus.SKIP, "Device does not support Camera");
			test.log(LogStatus.SKIP, "Test case status is Skipped");
		}
	}
	
	@Test(priority=16,dataProvider="XP3_SonimCare", dataProviderClass=DataProviders.class)
	public void XP3_TC_016_Validate_No_Option_In_Flashlight_module(Hashtable<String, String> data) throws AWTException, InterruptedException, IOException, ParseException {

		APP_LOGS.info("=========== XP3_TC_016_Validate_No_Option_In_Flashlight_module============");
		startAdbLog("XP3_SonimCare_016");
		Launch_SonimCare();
		navigate_to_module(Locators_SonimCare.Flashlight, "FLASHLIGHT");
		validate_flashlight_no_option();
	}
	
	@Test(priority=17,dataProvider="XP3_SonimCare", dataProviderClass=DataProviders.class)
	public void XP3_TC_017_Validate_No_Option_In_Display_module(Hashtable<String, String> data) throws AWTException, InterruptedException, IOException, ParseException {

		APP_LOGS.info("=========== XP3_TC_017_Validate_No_Option_In_Display_module============");
		startAdbLog("XP3_SonimCare_017");
		Launch_SonimCare();
		navigate_to_module(Locators_SonimCare.Display, "DISPLAY");
		validate_display_no_option();
	}
	
	@Test(priority=18,dataProvider="XP3_SonimCare", dataProviderClass=DataProviders.class)
	public void XP3_TC_018_Validate_No_Option_In_Battery_module(Hashtable<String, String> data) throws AWTException, InterruptedException, IOException, ParseException {

		APP_LOGS.info("=========== XP3_TC_018_Validate_No_Option_In_Battery_module============");
		startAdbLog("XP3_SonimCare_018");
		Launch_SonimCare();
		navigate_to_module(Locators_SonimCare.Battery, "BATTERY");
		validate_battery_no_option();
	}
	
	@Test(priority=19,dataProvider="XP3_SonimCare", dataProviderClass=DataProviders.class)
	public void XP3_TC_019_Validate_No_Option_In_Vibration_module(Hashtable<String, String> data) throws AWTException, InterruptedException, IOException, ParseException {

		APP_LOGS.info("=========== XP3_TC_019_Validate_No_Option_In_Vibration_module============");
		startAdbLog("XP3_SonimCare_019");
		Launch_SonimCare();
		navigate_to_module(Locators_SonimCare.Vibration, "VIBRATION");
		validate_vibration_no_option();
	}
	
	@Test(priority=20,dataProvider="XP3_SonimCare", dataProviderClass=DataProviders.class)
	public void XP3_TC_020_Validate_Battery_diagnosis_welcome_screen_in_Sonimcare(Hashtable<String, String> data) throws AWTException, InterruptedException, IOException, ParseException {

		APP_LOGS.info("=========== XP3_TC_020_Validate_Battery_diagnosis_welcome_screen_in_Sonimcare============");
		startAdbLog("XP3_SonimCare_020");
		Launch_SonimCare();
		navigate_and_validate_battery_diagnosis_welcome_screen();
	}
	
	@Test(priority=21,dataProvider="XP3_SonimCare", dataProviderClass=DataProviders.class)
	public void XP3_TC_021_Validate_Battery_Information_In_Battery_Diagnosis(Hashtable<String, String> data) throws AWTException, InterruptedException, IOException, ParseException {

		APP_LOGS.info("=========== XP3_TC_021_Validate_Battery_Information_In_Battery_Diagnosis============");
		startAdbLog("XP3_SonimCare_021");
		Launch_SonimCare();
		validate_battery_information();
	}
	
	@Test(priority=22,dataProvider="XP3_SonimCare", dataProviderClass=DataProviders.class)
	public void XP3_TC_022_Validate_Battery_Service_History_In_Battery_Diagnosis(Hashtable<String, String> data) throws AWTException, InterruptedException, IOException, ParseException {

		APP_LOGS.info("=========== XP3_TC_022_Validate_Battery_Service_History_In_Battery_Diagnosis============");
		startAdbLog("XP3_SonimCare_022");
		Launch_SonimCare();
		validateBatteryServiceHistory();
	}
	
	@Test(priority=23,dataProvider="XP3_SonimCare", dataProviderClass=DataProviders.class)
	public void XP3_TC_023_Validate_Diagnosis_Report(Hashtable<String, String> data) throws AWTException, InterruptedException, IOException, ParseException {

		APP_LOGS.info("=========== XP3_TC_023_Validate_Diagnosis_Report============");
		startAdbLog("XP3_SonimCare_023");
		disableCellularData();
		ConnectSecuredWifi();
		Launch_SonimCare();
		validate_diagnosis_report();
	}
	
	
	
	
	
}
