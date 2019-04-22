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
import com.xp5S.util.AppUpdater_Util;
import com.xp5S.util.BaseUtil;
import com.xp5S.util.DataProviders;
import com.xp5S.util.ExcelConstants;
import com.xp5S.util.ExcelReader;
import com.xp5S.util.Locators_AppUpdater;
import com.xp5S.util.Locators_BaseUtil;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class AppUpdaterTest_ATT extends AppUpdater_Util{

	Locators_AppUpdater locators;
	public ExcelReader excel;
	Properties properties;
	public static ExtentReports extent;
	public static ExtentTest test;
	public static ExtentTestInterruptedException testexception;
	//	public AndroidDriver<AndroidElement> aDriver;

	@BeforeSuite
	public void beforeSuite()
	{
		extent = new ExtentReports("src/test/resources/extentreport/XP5S_ScoutApps_AppUpdater_TestReport.html", true);

	}

	@BeforeMethod()
	public  void beforeMethod(Method method) throws InterruptedException 	
	{

		test = extent.startTest( (this.getClass().getSimpleName() +" :: "+  method.getName()),method.getName()); //Test Case Start Here
		test.assignAuthor(""); //Test Script Author Name
		extent.loadConfig(new File(System.getProperty("user.dir")+"//ReportsConfig.xml"));
		extent.addSystemInfo("Appium Version", "1.2.7").addSystemInfo(
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
			//			// clear screen
			clearRecentApps();
		}
		extent.endTest(test);
		extent.flush();
	}


	

	@BeforeTest
	public void setupSys() throws MalformedURLException, InterruptedException, FileNotFoundException, AWTException{
		properties=loadDriverAndProperties();
		Locators_AppUpdater loc=new Locators_AppUpdater(aDriver);
		Locators_BaseUtil loc1 =new Locators_BaseUtil(aDriver);
		PageFactory.initElements(new AppiumFieldDecorator(getDriver()),loc);
		PageFactory.initElements(new AppiumFieldDecorator(getDriver()),loc1);
		excel=new ExcelReader(ExcelConstants.XP5S_XL_PATH);		
	}

	@Test(priority=1,dataProvider="AppUpdaterTest", dataProviderClass=DataProviders.class)
	public void AppUpdater_01_Validate_PresenceOfAppUpdater(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException  {

		APP_LOGS.info("===========AppUpdaterTest_01============");
		startAdbLog("AppUpdaterTest_01");
		recordVideo("AppUpdaterTest_01"); 
		clearRecentApps();
		launch_application(Locators_AppUpdater.sonim_scout_AppLauncher);
		validate_appUpdater_Presence();
		test.log(LogStatus.PASS, "Test case status is Passed");
	}
	
	@Test(priority=2,dataProvider="AppUpdaterTest", dataProviderClass=DataProviders.class)
	public void AppUpdater_02_Validate_AppUpdater_Homescreen(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException  {

		APP_LOGS.info("===========AppUpdaterTest_02============");
		startAdbLog("AppUpdaterTest_02");
		recordVideo("AppUpdaterTest_02"); 
		launch_application(Locators_AppUpdater.sonim_scout_AppLauncher);
		launchAppUpdater();
		validate_AppUpdaterHomeScreen();	
		test.log(LogStatus.PASS, "Test case status is Passed");		
	}
	
	@Test(priority=3,dataProvider="AppUpdaterTest", dataProviderClass=DataProviders.class)
	public void AppUpdater_03_Validate_CheckForUpdate_button_presence(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException  {

		APP_LOGS.info("===========AppUpdaterTest_03============");
		startAdbLog("AppUpdaterTest_03");
		recordVideo("AppUpdaterTest_03"); 
		launch_application(Locators_AppUpdater.sonim_scout_AppLauncher);
		launchAppUpdater();
		validate_checkForUpdates_btn();		
		test.log(LogStatus.PASS, "Test case status is Passed");	
	}
	
	@Test(priority=4,dataProvider="AppUpdaterTest", dataProviderClass=DataProviders.class)
	public void AppUpdater_04_validate_checkingForUpdateProgress_Screen(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException  {

		APP_LOGS.info("===========AppUpdaterTest_04============");
		startAdbLog("AppUpdaterTest_04");
		recordVideo("AppUpdaterTest_04"); 
		launch_application(Locators_AppUpdater.sonim_scout_AppLauncher);
		launchAppUpdater();
		validate_checkingForUpdateProgress();
		test.log(LogStatus.PASS, "Test case status is Passed");		
	}
	
	@Test(priority=5,dataProvider="AppUpdaterTest", dataProviderClass=DataProviders.class)
	public void AppUpdater_05_validate_updateConfirmation_dialong(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException  {

		APP_LOGS.info("===========AppUpdaterTest_05============");
		startAdbLog("AppUpdaterTest_05");
		recordVideo("AppUpdaterTest_05"); 
		clearRecentApps();
		launch_application(Locators_AppUpdater.sonim_scout_AppLauncher);
		launchAppUpdater();
		click_checkForUpdateBtn();
		validate_UpdateConfirmationDialog();
		test.log(LogStatus.PASS, "Test case status is Passed");		
	}
	
	@Test(priority=6,dataProvider="AppUpdaterTest", dataProviderClass=DataProviders.class)
	public void AppUpdater_06_validate_succesful_app_Installation(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException  {
		
		APP_LOGS.info("===========AppUpdaterTest_06============");
		startAdbLog("AppUpdaterTest_06");
		recordVideo("AppUpdaterTest_06"); 
		launch_application(Locators_AppUpdater.sonim_scout_AppLauncher);
		launchAppUpdater();
		click_checkForUpdateBtn();
		installApp();
		appUpdaterSuccessAlert();
		validate_succesfull_appInstallation("Sonim BLE Connect");
		uninstallUpdates(Locators_AppUpdater.sonimBLEconnect_AllApps);
		test.log(LogStatus.PASS, "Test case status is Passed");
	}
	
	@Test(priority=7,dataProvider="AppUpdaterTest", dataProviderClass=DataProviders.class)
	public void AppUpdater_07_validate_appInstallation_In_Homscreen(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException  {
		
		// Validates successful download and installation of app while in sonimcare homescreen.
		APP_LOGS.info("===========AppUpdaterTest_07============");
		startAdbLog("AppUpdaterTest_07");
		recordVideo("AppUpdaterTest_07"); 
		clearRecentApps();
		launch_application(Locators_AppUpdater.sonim_scout_AppLauncher);
		launchAppUpdater();
		click_checkForUpdateBtn();
		click_HomeSoftKey();		
		validate_succesfull_appInstallation("Sonim BLE Connect");
		uninstallUpdates(Locators_AppUpdater.sonimBLEconnect_AllApps);
		test.log(LogStatus.PASS, "Test case status is Passed");		
	}	
	
	@Test(priority=8,dataProvider="AppUpdaterTest", dataProviderClass=DataProviders.class)
	public void AppUpdater_08_validate_appUpdater_AboutScreen(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException  {
		
		APP_LOGS.info("===========AppUpdaterTest_08==============");
		startAdbLog("AppUpdaterTest_08");
		recordVideo("AppUpdaterTest_08"); 
		launch_application(Locators_AppUpdater.sonim_scout_AppLauncher);
		launchAppUpdater();
		validate_aboutScreen();
		test.log(LogStatus.PASS, "Test case status is Passed");		
	}
	
	@Test(priority=9,dataProvider="AppUpdaterTest", dataProviderClass=DataProviders.class)
	public void AppUpdater_09_validate_lastCheckedText(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException  {
		
		APP_LOGS.info("===========AppUpdaterTest_09==============");
		startAdbLog("AppUpdaterTest_09");
		recordVideo("AppUpdaterTest_09"); 
		launch_application(Locators_AppUpdater.sonim_scout_AppLauncher);
		launchAppUpdater();
		test.log(LogStatus.PASS, "Test case status is Passed");		
	}

	@Test(priority=10,dataProvider="AppUpdaterTest", dataProviderClass=DataProviders.class)
	public void AppUpdater_10_validate_UptoDateScreen(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException  
	{		
		APP_LOGS.info("===========AppUpdaterTest_10==============");
		startAdbLog("AppUpdaterTest_10");
		recordVideo("AppUpdaterTest_10");		 
		clearRecentApps();
		launch_application(Locators_AppUpdater.sonim_scout_AppLauncher);
		launchAppUpdater();
		click_checkForUpdateBtn();
		installApp();
		appUpdaterSuccessAlert();customWait(5000);
		pressCenter();customWait(6000);
		validate_uptoDate_Screen();
		uninstallUpdates(Locators_AppUpdater.sonimBLEconnect_AllApps);
		test.log(LogStatus.PASS, "Test case status is Passed");
	}
	
}
