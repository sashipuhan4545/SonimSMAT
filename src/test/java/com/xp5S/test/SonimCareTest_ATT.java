package com.xp5S.test;

import java.awt.AWTException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException ;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.util.Hashtable;
import java.util.Properties;

import javax.swing.JOptionPane;

import org.json.simple.parser.ParseException;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.graphics.gui.CountIncrementFile;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.ExtentTestInterruptedException;
import com.relevantcodes.extentreports.LogStatus;
import com.xp5S.util.BaseUtil;
import com.xp5S.util.DataProviders;
import com.xp5S.util.ExcelConstants;
import com.xp5S.util.ExcelReader;
import com.xp5S.util.Locators_BaseUtil;
import com.xp5S.util.Locators_SonimCare;

import com.xp5S.util.SonimCare_Util;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;


public class SonimCareTest_ATT extends SonimCare_Util {


	Locators_SonimCare locators;
	public ExcelReader excel;
	Properties properties;
	public static ExtentReports extent;
	public static ExtentTest test;
	public static ExtentTestInterruptedException testexception;
	//	public AndroidDriver<AndroidElement> aDriver;

	@BeforeSuite
	public void beforeSuite() 
	{
		extent = new ExtentReports("src/test/resources/extentreport/XP5S_SonimCare_TestReport.html", true); //Provide Desired Report Directory Location and Name

	}


	/*@AfterMethod()
	public void setProgressBar_TestResult(Method m,ITestResult result) {


		int count =CountIncrementFile.getCount(4);  

		ToolFrame.progressBar.setValue(count);
		count=count+4;
		CountIncrementFile.putCount(count);	
	}*/

	@BeforeMethod()
	public  void beforeMethod(Method method) throws InterruptedException 
	{

		test = extent.startTest( (this.getClass().getSimpleName() +" :: "+  method.getName()),method.getName()); //Test Case Start Here
		test.assignAuthor(""); //Test Script Author Name
		extent.loadConfig(new File(System.getProperty("user.dir")+"//ReportsConfig.xml"));
		extent.addSystemInfo("Appium Version", "1.1").addSystemInfo(
				"Environment", "TEST");
		String BuildNumber=BaseUtil.fetchDeviceProperty("adb shell getprop ro.build.id");
		test.assignCategory("Build #:"+BuildNumber);


	}

	@AfterMethod()
	public void tearDown(ITestResult result,Method method) throws IOException, ParseException , InterruptedException
	{
		if(result.getStatus()==ITestResult.FAILURE)
		{			

			String screenshot_path=captureScreenshot(method.getName());
			String image= test.addScreenCapture(screenshot_path);
			test.log(LogStatus.FAIL,result.getThrowable());
			test.log(LogStatus.FAIL, "screenshot for failure: "+test.addScreenCapture(image));
			// clear screen
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


	@Test(priority=1,dataProvider="SonimCareTest", dataProviderClass=DataProviders.class)
	public void SonimCare_01_Validate_SonimCare_HomeScreen(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException  {

		APP_LOGS.info("===========SonimCare_01============");

		startAdbLog("SonimCare_01_ATT");
		recordVideo("SonimCare_01_ATT");
		clearRecentApps();
		launch_application(Locators_SonimCare.sonim_scout_AppLauncher);
		validate_Current_Screen(Locators_SonimCare.sonimCareTitle,data.get("screenTitle"));
		customWait(4000);
		 
		customWait(2000);
		test.log(LogStatus.PASS, "Test case status is Passed");
	}

	@Test(priority=2,dataProvider="SonimCareTest", dataProviderClass=DataProviders.class)
	public void SonimCare_02_Validate_Wifi_Scan_Test(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException  {

		APP_LOGS.info("===========SonimCare_02============");

		startAdbLog("SonimCare_02_ATT");
		recordVideo("SonimCare_02_ATT");
		clearRecentApps();
		launch_application(Locators_SonimCare.sonim_scout_AppLauncher);
		launchSonimCare();
		select_subApplication_sonimCare(Locators_SonimCare.selfDiagnosisTest);
		navigateTo_TestAll_screen();
		navigateTo_Individual_feature("wi-fi");
		click_On_coninueBtn_featureScreen();
		validate_wifiScan();
		customWait(4000);
		 
		customWait(2000);
		test.log(LogStatus.PASS, "Test case status is Passed");
	}

	@Test(priority=3,dataProvider="SonimCareTest", dataProviderClass=DataProviders.class)
	public void SonimCare_03_Validate_BackCamera_Scan_Test(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException  {

		APP_LOGS.info("===========SonimCare_03============");

		startAdbLog("SonimCare_03_ATT");
		recordVideo("SonimCare_03_ATT");
		clearRecentApps();
		launch_application(Locators_SonimCare.sonim_scout_AppLauncher);
		launchSonimCare();
		select_subApplication_sonimCare(Locators_SonimCare.selfDiagnosisTest);
		navigateTo_TestAll_screen();
		navigateTo_Individual_feature("Back camera");
		click_On_coninueBtn_featureScreen();
		validate_backCamera(data.get("confirmationMessage"));
		customWait(4000);
		 
		customWait(2000);
		test.log(LogStatus.PASS, "Test case status is Passed");
	}

	@Test(priority=4,dataProvider="SonimCareTest", dataProviderClass=DataProviders.class)
	public void SonimCare_04_Validate_Battery_Scan_Test(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException  {

		APP_LOGS.info("===========SonimCare_04============");

		startAdbLog("SonimCare_04_ATT");
		recordVideo("SonimCare_04_ATT");
		clearRecentApps();
		launch_application(Locators_SonimCare.sonim_scout_AppLauncher);
		launchSonimCare();
		select_subApplication_sonimCare(Locators_SonimCare.selfDiagnosisTest);
		navigateTo_TestAll_screen();
		navigateTo_Individual_feature("Battery");
		click_On_coninueBtn_featureScreen();
		validate_BatteryTest(data.get("bat_Status"), data.get("bat_status_value_1"),data.get("bat_status_value_2"), data.get("bat_Electricity"), data.get("bat_Electricity_value"));
		//stopAdb();
		customWait(4000);
		 
		customWait(2000);
		test.log(LogStatus.PASS, "Test case status is Passed");
	}

	@Test(priority=5,dataProvider="SonimCareTest", dataProviderClass=DataProviders.class)
	public void SonimCare_05_Validate_Clear_CacheMemory_Test(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException  {

		APP_LOGS.info("===========SonimCare_05============");

		startAdbLog("SonimCare_05_ATT");
		recordVideo("SonimCare_05_ATT");
		clearRecentApps();
		launch_application(Locators_SonimCare.sonim_scout_AppLauncher);
		launchSonimCare();
		select_subApplication_sonimCare(Locators_SonimCare.selfDiagnosisTest);
		navigateTo_TestAll_screen();
		navigateTo_Individual_feature("CLEAR CACHE MEMORY");
		click_On_coninueBtn_featureScreen();
		validate_ClearCacheMemory();
		//stopAdb();
		customWait(4000);
		 
		customWait(2000);
		test.log(LogStatus.PASS, "Test case status is Passed");
	}

	@Test(priority=6,dataProvider="SonimCareTest", dataProviderClass=DataProviders.class)
	public void SonimCare_06_Validate_Bluetooth_Scan_Test(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException  {

		APP_LOGS.info("===========SonimCare_06============");

		startAdbLog("SonimCare_06_ATT");
		recordVideo("SonimCare_06_ATT");
		clearRecentApps();
		launch_application(Locators_SonimCare.sonim_scout_AppLauncher);
		launchSonimCare();
		select_subApplication_sonimCare(Locators_SonimCare.selfDiagnosisTest);
		navigateTo_TestAll_screen();
		navigateTo_Individual_feature("Bluetooth");
		click_On_coninueBtn_featureScreen();
		validate_bluetooth_scan();
		//stopAdb();
		customWait(4000);
		 
		customWait(2000);
		test.log(LogStatus.PASS, "Test case status is Passed");
	}

	@Test(priority=7,dataProvider="SonimCareTest", dataProviderClass=DataProviders.class)
	public void SonimCare_07_Validate_Battery_diagnosis_Screen(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException  {

		APP_LOGS.info("===========SonimCare_07===========");

		startAdbLog("SonimCare_07_ATT");
		recordVideo("SonimCare_07_ATT");
		clearRecentApps();
		launch_application(Locators_SonimCare.sonim_scout_AppLauncher);
		launchSonimCare();
		select_subApplication_sonimCare(Locators_SonimCare.batteryDiagnosis);
		click_On_coninueBtn_Bat_Scan();
		validate_batteryDiagnosis();
		//stopAdb();
		customWait(4000);
		 
		customWait(2000);
		test.log(LogStatus.PASS, "Test case status is Passed");
	}

	@Test(priority=8,dataProvider="SonimCareTest", dataProviderClass=DataProviders.class)
	public void SonimCare_08_Validate_Battery_scanTest_Values(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException  {

		APP_LOGS.info("===========SonimCare_08============");

		startAdbLog("SonimCare_08_ATT");
		recordVideo("SonimCare_08_ATT");
		clearRecentApps();
		launch_application(Locators_SonimCare.sonim_scout_AppLauncher);
		launchSonimCare();
		select_subApplication_sonimCare(Locators_SonimCare.batteryDiagnosis);
		click_On_coninueBtn_Bat_Scan();
		validate_battery_service_history();
		//stopAdb();
		customWait(4000);
		 
		customWait(2000);
		test.log(LogStatus.PASS, "Test case status is Passed");
	}

	@Test(priority=9,dataProvider="SonimCareTest", dataProviderClass=DataProviders.class)
	public void SonimCare_09_Validate_Battery_Service_History(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException  {

		APP_LOGS.info("===========SonimCare_09============");

		startAdbLog("SonimCare_09_ATT");
		recordVideo("SonimCare_09_ATT");
		clearRecentApps();
		launch_application(Locators_SonimCare.sonim_scout_AppLauncher);
		launchSonimCare();
		select_subApplication_sonimCare(Locators_SonimCare.batteryDiagnosis);
		click_On_coninueBtn_Bat_Scan();
		validate_battery_service_history();
		//stopAdb();
		customWait(4000);
		 
		customWait(2000);
		test.log(LogStatus.PASS, "Test case status is Passed");
	}



	@Test(priority=10,dataProvider="SonimCareTest", dataProviderClass=DataProviders.class)
	public void SonimCare_10_Validate_SonimCare_Video_Feature(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException  {

		APP_LOGS.info("===========SonimCare_10============");

		startAdbLog("SonimCare_10_ATT");
		recordVideo("SonimCare_10_ATT");
		clearRecentApps();
		launch_application(Locators_SonimCare.sonim_scout_AppLauncher);
		launchSonimCare();
		select_subApplication_sonimCare(Locators_SonimCare.videos);
		validate_Youtube_Navigation();
		//stopAdb();
		customWait(4000);
		 
		customWait(2000);
		test.log(LogStatus.PASS, "Test case status is Passed");
	}


	@Test(priority=11,dataProvider="SonimCareTest", dataProviderClass=DataProviders.class)
	public void SonimCare_11_Validate_Wifi_No_btn_Functionality(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException  {

		APP_LOGS.info("===========SonimCare_11============");

		startAdbLog("SonimCare_11_ATT");
		recordVideo("SonimCare_11_ATT");
		clearRecentApps();
		launch_application(Locators_SonimCare.sonim_scout_AppLauncher);
		launchSonimCare();
		select_subApplication_sonimCare(Locators_SonimCare.selfDiagnosisTest);
		navigateTo_TestAll_screen();
		navigateTo_Individual_feature("wi-fi");
		click_On_coninueBtn_featureScreen();
		clickBtn(Locators_SonimCare.scanForMoreNetworksBtn);
		validate_No_btn_feature("wi-fi");
		//stopAdb();
		customWait(4000);
		 
		customWait(2000);
		test.log(LogStatus.PASS, "Test case status is Passed");
	}

	@Test(priority=12,dataProvider="SonimCareTest", dataProviderClass=DataProviders.class)
	public void SonimCare_12_Validate_Bluetooth_No_btn_Functionality(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException  {

		APP_LOGS.info("===========SonimCare_12============");

		startAdbLog("SonimCare_12_ATT");
		recordVideo("SonimCare_12_ATT");
		clearRecentApps();
		launch_application(Locators_SonimCare.sonim_scout_AppLauncher);
		launchSonimCare();
		select_subApplication_sonimCare(Locators_SonimCare.selfDiagnosisTest);
		navigateTo_TestAll_screen();
		navigateTo_Individual_feature("bluetooth");
		click_On_coninueBtn_featureScreen();
		validate_No_btn_feature("bluetooth");
		//stopAdb();
		customWait(4000);
		 
		customWait(2000);
		test.log(LogStatus.PASS, "Test case status is Passed");
	}

	@Test(priority=13,dataProvider="SonimCareTest", dataProviderClass=DataProviders.class)
	public void SonimCare_13_Validate_Back_Camera_No_btn_Functionality(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException  {

		APP_LOGS.info("===========SonimCare_13============");

		startAdbLog("SonimCare_13_ATT");
		recordVideo("SonimCare_13_ATT");
		clearRecentApps();
		launch_application(Locators_SonimCare.sonim_scout_AppLauncher);
		launchSonimCare();
		select_subApplication_sonimCare(Locators_SonimCare.selfDiagnosisTest);
		navigateTo_TestAll_screen();
		navigateTo_Individual_feature("back camera");
		click_On_coninueBtn_featureScreen();
		capture_image();
		validate_No_btn_feature("back camera");
		//stopAdb();
		customWait(4000);
		 
		customWait(2000);
		test.log(LogStatus.PASS, "Test case status is Passed");
	}

	@Test(priority=14,dataProvider="SonimCareTest", dataProviderClass=DataProviders.class)
	public void SonimCare_14_Validate_Flash_light_No_btn_Functionality(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException  {

		APP_LOGS.info("===========SonimCare_14============");

		startAdbLog("SonimCare_14_ATT");
		recordVideo("SonimCare_14_ATT");
		clearRecentApps();
		launch_application(Locators_SonimCare.sonim_scout_AppLauncher);
		launchSonimCare();
		select_subApplication_sonimCare(Locators_SonimCare.selfDiagnosisTest);
		navigateTo_TestAll_screen();
		navigateTo_Individual_feature("flashlight");
		click_On_coninueBtn_featureScreen();
		turnOn_flash_light();
		validate_No_btn_feature("flash light");
		//stopAdb();
		customWait(4000);
		 
		customWait(2000);
		test.log(LogStatus.PASS, "Test case status is Passed");
	}

	@Test(priority=15,dataProvider="SonimCareTest", dataProviderClass=DataProviders.class)
	public void SonimCare_15_Validate_Display_No_btn_Functionality(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException  {

		APP_LOGS.info("===========SonimCare_15============");

		startAdbLog("SonimCare_15");
		recordVideo("SonimCare_15");
		clearRecentApps();
		launch_application(Locators_SonimCare.sonim_scout_AppLauncher);
		launchSonimCare();
		select_subApplication_sonimCare(Locators_SonimCare.selfDiagnosisTest);
		navigateTo_TestAll_screen();
		navigateTo_Individual_feature("display");
		click_On_coninueBtn_featureScreen();
		validate_No_btn_feature("display");
		//stopAdb();
		customWait(4000);
		 
		customWait(2000);
		test.log(LogStatus.PASS, "Test case status is Passed");
	}

	@Test(priority=16,dataProvider="SonimCareTest", dataProviderClass=DataProviders.class)
	public void SonimCare_16_Validate_Battery_No_btn_Functionality(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException  {

		APP_LOGS.info("===========SonimCare_16============");

		startAdbLog("SonimCare_16_ATT");
		recordVideo("SonimCare_16_ATT");
		clearRecentApps();
		launch_application(Locators_SonimCare.sonim_scout_AppLauncher);
		launchSonimCare();
		select_subApplication_sonimCare(Locators_SonimCare.selfDiagnosisTest);
		navigateTo_TestAll_screen();
		navigateTo_Individual_feature("Battery");
		click_On_coninueBtn_featureScreen();
		validate_No_btn_feature("Battery");
		stopAdb();
		test.log(LogStatus.PASS, "Test case status is Passed");
	}

	@Test(priority=17,dataProvider="SonimCareTest", dataProviderClass=DataProviders.class)
	public void SonimCare_17_Validate_HyperLinks_Alerts_wifiFeature(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException  {

		APP_LOGS.info("===========SonimCare_17============");

		startAdbLog("SonimCare_17_ATT");
		recordVideo("SonimCare_17_ATT");
		clearRecentApps();
		launch_application(Locators_SonimCare.sonim_scout_AppLauncher);
		launchSonimCare();
		select_subApplication_sonimCare(Locators_SonimCare.selfDiagnosisTest);
		navigateTo_TestAll_screen();
		navigateTo_Individual_feature("wi-fi");
		click_On_coninueBtn_featureScreen();
		validate_wifi_links("wi-fi");
		stopAdb();
		test.log(LogStatus.PASS, "Test case status is Passed");
	}

	@Test(priority=18,dataProvider="SonimCareTest", dataProviderClass=DataProviders.class)
	public void SonimCare_18_Validate_HyperLinks_Alerts_battery_Feature(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException  {

		APP_LOGS.info("===========SonimCare_18============");

		startAdbLog("SonimCare_18_ATT");
		recordVideo("SonimCare_18_ATT");
		clearRecentApps();
		launch_application(Locators_SonimCare.sonim_scout_AppLauncher);
		launchSonimCare();
		select_subApplication_sonimCare(Locators_SonimCare.selfDiagnosisTest);
		navigateTo_TestAll_screen();
		navigateTo_Individual_feature("battery");
		click_On_coninueBtn_featureScreen();
		validate_battery_links("battery");
		//stopAdb();
		customWait(4000);
		 
		customWait(2000);
		test.log(LogStatus.PASS, "Test case status is Passed");
	}

	@Test(priority=19,dataProvider="SonimCareTest", dataProviderClass=DataProviders.class)
	public void SonimCare_19_Validate_HyperLinks_Alerts_Self_Diagnosis_Screen(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException  {

		APP_LOGS.info("===========SonimCare_19============");

		startAdbLog("SonimCare_19_ATT");
		recordVideo("SonimCare_19_ATT");
		clearRecentApps();
		launch_application(Locators_SonimCare.sonim_scout_AppLauncher);
		launchSonimCare();
		select_subApplication_sonimCare(Locators_SonimCare.selfDiagnosisTest);
		pressCenterKey();
		validate_hyperTexts_alert_selfDiagnosisPage();
		//stopAdb();
		customWait(4000);
		 
		customWait(2000);
		test.log(LogStatus.PASS, "Test case status is Passed");
	}

	@Test(priority=20,dataProvider="SonimCareTest", dataProviderClass=DataProviders.class)
	public void SonimCare_20_Validate_Device_info_Self_Diagnosis_Screen(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException  {

		APP_LOGS.info("===========SonimCare_20============");

		startAdbLog("SonimCare_20_ATT");
		recordVideo("SonimCare_20_ATT");
		clearRecentApps();
		launch_application(Locators_SonimCare.sonim_scout_AppLauncher);
		launchSonimCare();
		select_subApplication_sonimCare(Locators_SonimCare.selfDiagnosisTest);
		pressCenterKey();
		validate_Device_Info_Self_Diagnosis_screen();
		//stopAdb();
		customWait(4000);
		 
		customWait(2000);
		test.log(LogStatus.PASS, "Test case status is Passed");
	}

	@Test(priority=21,dataProvider="SonimCareTest", dataProviderClass=DataProviders.class)
	public void SonimCare_21_Validate_Wifi_info_wifi_feature_Screen(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException  {

		APP_LOGS.info("===========SonimCare_21============");

		startAdbLog("SonimCare_21_ATT");
		recordVideo("SonimCare_21_ATT");
		clearRecentApps();
		launch_application(Locators_SonimCare.sonim_scout_AppLauncher);
		launchSonimCare();
		select_subApplication_sonimCare(Locators_SonimCare.selfDiagnosisTest);
		navigateTo_TestAll_screen();
		navigateTo_Individual_feature("wi-fi");
		click_On_coninueBtn_featureScreen();
		validate_wifi_info_wifi_feature_screen("wi-fi");
		//stopAdb();
		customWait(4000);
		 
		customWait(2000);
		test.log(LogStatus.PASS, "Test case status is Passed");
	}


	@Test(priority=22,dataProvider="SonimCareTest", dataProviderClass=DataProviders.class)
	public void SonimCare_22_Validate_battery_info_battery_feature_Screen(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException  {

		APP_LOGS.info("===========SonimCare_22============");

		startAdbLog("SonimCare_22_ATT");
		recordVideo("SonimCare_22_ATT");
		clearRecentApps();
		launch_application(Locators_SonimCare.sonim_scout_AppLauncher);
		launchSonimCare();
		select_subApplication_sonimCare(Locators_SonimCare.selfDiagnosisTest);
		navigateTo_TestAll_screen();
		navigateTo_Individual_feature("battery");
		click_On_coninueBtn_featureScreen();
		validate_battery_info_battery_feature_screen();
		//stopAdb();
		customWait(4000);
		 
		customWait(2000);
		test.log(LogStatus.PASS, "Test case status is Passed");
	}
	/*
	@Test(priority=23,dataProvider="SonimCareTest", dataProviderClass=DataProviders.class)
	public void SonimCare_23_Validate_bluetooth_name_address_info(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException  {

		startAdbLog("SonimCare_23");
		recordVideo("SonimCare_23");
		clearRecentApps();
		launchApp("SCOUT_PACKAGE", "SCOUT_ACTIVITY");
		launchSonimCare();
		select_subApplication_sonimCare(Locators_SonimCare.selfDiagnosisTest);
		navigateTo_TestAll_screen();
		navigateTo_Individual_feature("bluetooth");
		click_On_coninueBtn_featureScreen();
		validate_bluetooth_Name_address_values("bluetooth");
		//stopAdb();
		customWait(4000);
		 
		customWait(2000);
		test.log(LogStatus.PASS, "Test case status is Passed");
	}*/

	@Test(priority=23,dataProvider="SonimCareTest", dataProviderClass=DataProviders.class)
	public void SonimCare_23_validate_diagnosis_report_dialog(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException  {

		APP_LOGS.info("===========SonimCare_23============");		

		startAdbLog("SonimCare_23_ATT");
		recordVideo("SonimCare_23_ATT");
		clearRecentApps();
		launch_application(Locators_SonimCare.sonim_scout_AppLauncher);
		launchSonimCare();
		select_subApplication_sonimCare(Locators_SonimCare.selfDiagnosisTest);
		navigateTo_TestAll_screen();
		navigateTo_Individual_feature("bluetooth");
		click_On_coninueBtn_featureScreen();
		selectNOButton("bluetooth");
		validate_diagnosis_report_dialog();	
		//stopAdb();
		customWait(4000);
		 
		customWait(2000);
		test.log(LogStatus.PASS, "Test case status is Passed");
	}

	@Test(priority=24,dataProvider="SonimCareTest", dataProviderClass=DataProviders.class)
	public void SonimCare_24_Validate_diagnosis_report_delivery(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException  {

		APP_LOGS.info("===========SonimCare_24============");

		startAdbLog("SonimCare_24_ATT");
		recordVideo("SonimCare_24_ATT");
		clearRecentApps();
		launch_application(Locators_SonimCare.sonim_scout_AppLauncher);
		launchSonimCare();		
		select_subApplication_sonimCare(Locators_SonimCare.selfDiagnosisTest);
		navigateTo_TestAll_screen();
		navigateTo_Individual_feature("bluetooth");
		click_On_coninueBtn_featureScreen();
		selectNOButton("bluetooth");
		validate_diagnosis_report_delivery("SonimCare_24");	
		test.log(LogStatus.PASS, "Test case status is Passed");
	}

	@Test(priority=25,dataProvider="SonimCareTest", dataProviderClass=DataProviders.class)
	public void SonimCare_25_validate_sonimScout_client_presence(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException  {

		APP_LOGS.info("===========SonimCare_25============");

		startAdbLog("SonimCare_25_ATT");
		recordVideo("SonimCare_25_ATT");
		clearRecentApps();
		validate_sonimScout_client_presence();
		customWait(4000);
		 
		customWait(2000);
		test.log(LogStatus.PASS, "Test case status is Passed");
	}

}
