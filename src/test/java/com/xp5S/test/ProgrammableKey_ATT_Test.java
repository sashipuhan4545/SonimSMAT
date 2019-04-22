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
import com.xp5S.util.BaseUtil;
import com.xp5S.util.DataProviders;
import com.xp5S.util.ExcelConstants;
import com.xp5S.util.ExcelReader;
import com.xp5S.util.Locators_BaseUtil;
import com.xp5S.util.Locators_ProgrammableKey;
import com.xp5S.util.Locators_SonimCare;
import com.xp5S.util.ProgrammableKey_Util;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class ProgrammableKey_ATT_Test extends ProgrammableKey_Util {

	Locators_SonimCare locators;
	public ExcelReader excel;
	Properties properties;
	public static ExtentReports extent;
	public static ExtentTest test;
	public static ExtentTestInterruptedException testexception;
	//public AndroidDriver<AndroidElement> aDriver;

	@BeforeSuite
	public void beforeSuite()
	{
		extent = new ExtentReports("src/test/resources/extentreport/XP5S_Scout_ProgrammableKeyTestReport.html", true);

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
			// clear screen
			

			clearRecentApps();

		}
		extent.endTest(test);
		extent.flush();
	}


	/*@AfterMethod
	public void setProgressBar_TestResult() {


		int count =CountIncrementFile.getCount(4);  
		if(count==92) {
			count=count+8;
			CountIncrementFile.putCount(count);

		}
		else {

			ToolFrame.progressBar.setValue(count);
			count=count+4;
			CountIncrementFile.putCount(count);
		}	
	}*/

	@BeforeTest
	public void setupSys() throws MalformedURLException, InterruptedException, FileNotFoundException, AWTException{
		properties=loadDriverAndProperties();
		Locators_ProgrammableKey loc=new Locators_ProgrammableKey(aDriver);
		Locators_BaseUtil loc1 =new Locators_BaseUtil(aDriver);
		PageFactory.initElements(new AppiumFieldDecorator(getDriver()),loc1);
		PageFactory.initElements(new AppiumFieldDecorator(getDriver()),loc);
		excel=new ExcelReader(ExcelConstants.XP5S_XL_PATH);		
	}

	@Test(priority=1,dataProvider="ProgrammableKeyTest", dataProviderClass=DataProviders.class)
	public void ProgrammableKey_01_Validate_ProgrammableKey_in_NativeSettings(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException  {

		APP_LOGS.info("===========ProgrammableKey_01============");
		startAdbLog("ProgrammableKey_01");
		recordVideo("ProgrammableKey_01");
		//clearRecentApps();		
		validate_ProgrammableKey_in_NativeSettings();
		 
		test.log(LogStatus.PASS, "Test case status is Passed");		
	}

	@Test(priority=2,dataProvider="ProgrammableKeyTest", dataProviderClass=DataProviders.class)
	public void ProgrammableKey_02_Validate_ProgrammableKeySummaryText(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException  {

		APP_LOGS.info("===========ProgrammableKey_02============");
		startAdbLog("ProgrammableKey_02");
		recordVideo("ProgrammableKey_02");
		//clearRecentApps();	
		scroll_to_ProgrammableKey();
		validate_ProgrammableKeySummaryText();
		 
		test.log(LogStatus.PASS, "Test case status is Passed");
	}

	@Test(priority=3,dataProvider="ProgrammableKeyTest", dataProviderClass=DataProviders.class)
	public void ProgrammableKey_03_Validate_SummaryText_Of_particularApp(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException  {

		APP_LOGS.info("===========ProgrammableKey_03============");
		startAdbLog("ProgrammableKey_03");
		recordVideo("ProgrammableKey_03");
		//clearRecentApps();
		scroll_to_ProgrammableKey();
		scrollToNoApplication();
		assign_app_to_ProgrammableKey(Locators_ProgrammableKey.camera_app);
		validate_ProgrammableKeySummaryText_of_ParticularApp("Camera");
		 
		test.log(LogStatus.PASS, "Test case status is Passed");
	}

	@Test(priority=4,dataProvider="ProgrammableKeyTest", dataProviderClass=DataProviders.class)
	public void ProgrammableKey_04_Validate_successful_LaunchOf_AppUpdater(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException  {

		APP_LOGS.info("===========ProgrammableKey_04============");
//		startAdbLog("ProgrammableKey_04");
//		recordVideo("ProgrammableKey_04");
		//clearRecentApps();
//		scroll_to_ProgrammableKey();
//		scrollToNoApplication();
//		assign_app_to_ProgrammableKey(Locators_ProgrammableKey.app_Updater_app);
//		validate_launchedApplication(Locators_ProgrammableKey.appUpdates_page, data.get("summaryText"));
//		 
		test.log(LogStatus.SKIP, "App Updater is removed from programableKey");
	}

	@Test(priority=5,dataProvider="ProgrammableKeyTest", dataProviderClass=DataProviders.class)
	public void ProgrammableKey_05_Validate_successful_LaunchOf_Calculator(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException  {

		APP_LOGS.info("===========ProgrammableKey_05============");
		startAdbLog("ProgrammableKey_05");
		recordVideo("ProgrammableKey_05");
		//clearRecentApps();
		scroll_to_ProgrammableKey();
		scrollToNoApplication();
		assign_app_to_ProgrammableKey(Locators_ProgrammableKey.calculator_app);
		validate_launchedApplication(Locators_ProgrammableKey.calc_Page, data.get("summaryText"));
		 
		test.log(LogStatus.PASS, "Test case status is Passed");
	}

	@Test(priority=6,dataProvider="ProgrammableKeyTest", dataProviderClass=DataProviders.class)
	public void ProgrammableKey_06_Validate_successful_LaunchOf_Calendar(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException  {

		APP_LOGS.info("===========ProgrammableKey_06============");
		startAdbLog("ProgrammableKey_06");
		recordVideo("ProgrammableKey_06");
		//clearRecentApps();
		scroll_to_ProgrammableKey();
		scrollToNoApplication();
		assign_app_to_ProgrammableKey(Locators_ProgrammableKey.calendar_app);
		validate_launchedApplication(Locators_ProgrammableKey.calendar_Page, data.get("summaryText"));
		 
		test.log(LogStatus.PASS, "Test case status is Passed");
	}

	@Test(priority=7,dataProvider="ProgrammableKeyTest", dataProviderClass=DataProviders.class)
	public void ProgrammableKey_07_Validate_successful_LaunchOf_Camera(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException  {

		APP_LOGS.info("===========ProgrammableKey_07============");
		startAdbLog("ProgrammableKey_07");
		recordVideo("ProgrammableKey_07");
		//clearRecentApps();
		scroll_to_ProgrammableKey();
		scrollToNoApplication();
		assign_app_to_ProgrammableKey(Locators_ProgrammableKey.camera_app);
		validate_launchedApplication(Locators_ProgrammableKey.camera_Page, data.get("summaryText"));
		 
		test.log(LogStatus.PASS, "Test case status is Passed");
	}

	@Test(priority=8,dataProvider="ProgrammableKeyTest", dataProviderClass=DataProviders.class)
	public void ProgrammableKey_08_Validate_successful_LaunchOf_Clock(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException  {

		APP_LOGS.info("===========ProgrammableKey_08============");
		startAdbLog("ProgrammableKey_08");
		recordVideo("ProgrammableKey_08");
		//clearRecentApps();
		scroll_to_ProgrammableKey();
		scrollToNoApplication();
		assign_app_to_ProgrammableKey(Locators_ProgrammableKey.clock_app);
		validate_launchedApplication(Locators_ProgrammableKey.clock_Page, data.get("summaryText"));
		 
		test.log(LogStatus.PASS, "Test case status is Passed");
	}

	@Test(priority=9,dataProvider="ProgrammableKeyTest", dataProviderClass=DataProviders.class)
	public void ProgrammableKey_09_Validate_successful_LaunchOf_ContactTransfer(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException  {

		APP_LOGS.info("===========ProgrammableKey_09============");
//		startAdbLog("ProgrammableKey_09");
//		recordVideo("ProgrammableKey_09");
//		//clearRecentApps();
//		scroll_to_ProgrammableKey();
//		scrollToNoApplication();
//		assign_app_to_ProgrammableKey(Locators_ProgrammableKey.contactTransfer_app);
//		validate_launchedApplication(Locators_ProgrammableKey.contact_transfer_page, data.get("summaryText"));
//		 
		test.log(LogStatus.SKIP, "Contact Transfer is removed from programableKey");
	}

	@Test(priority=10,dataProvider="ProgrammableKeyTest", dataProviderClass=DataProviders.class)
	public void ProgrammableKey_10_Validate_successful_LaunchOf_Contacts(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException  {

		APP_LOGS.info("===========ProgrammableKey_10============");
		startAdbLog("ProgrammableKey_10");
		recordVideo("ProgrammableKey_10");
		//clearRecentApps();
		scroll_to_ProgrammableKey();
		scrollToNoApplication();
		assign_app_to_ProgrammableKey(Locators_ProgrammableKey.contacts_app);
		validate_launchedApplication(Locators_ProgrammableKey.contacts_native_page, data.get("summaryText"));
		 
		test.log(LogStatus.PASS, "Test case status is Passed");
	}

	@Test(priority=11,dataProvider="ProgrammableKeyTest", dataProviderClass=DataProviders.class)
	public void ProgrammableKey_11_Validate_successful_LaunchOf_Downloads(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException  {

		APP_LOGS.info("===========ProgrammableKey_11============");
		startAdbLog("ProgrammableKey_11");
		recordVideo("ProgrammableKey_11");
		//clearRecentApps();
		scroll_to_ProgrammableKey();
		scrollToNoApplication();
		assign_app_to_ProgrammableKey(Locators_ProgrammableKey.downloads_app);
		validate_launchedApplication(Locators_ProgrammableKey.downloads_page, data.get("summaryText"));
		 
		test.log(LogStatus.PASS, "Test case status is Passed");
	}

	@Test(priority=12,dataProvider="ProgrammableKeyTest", dataProviderClass=DataProviders.class)
	public void ProgrammableKey_12_Validate_successful_LaunchOf_FileExplorer(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException  {

		APP_LOGS.info("===========ProgrammableKey_12============");
		startAdbLog("ProgrammableKey_12");
		recordVideo("ProgrammableKey_12");
		//clearRecentApps();
		scroll_to_ProgrammableKey();
		scrollToNoApplication();
		assign_app_to_ProgrammableKey(Locators_ProgrammableKey.fileExplorer_app);
		validate_launchedApplication(Locators_ProgrammableKey.fileExplorer_page, data.get("summaryText"));
		 
		test.log(LogStatus.PASS, "Test case status is Passed");
	}

	@Test(priority=13,dataProvider="ProgrammableKeyTest", dataProviderClass=DataProviders.class)
	public void ProgrammableKey_13_Validate_successful_LaunchOf_FMRadio(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException  {

		APP_LOGS.info("===========ProgrammableKey_13============");
		startAdbLog("ProgrammableKey_13");
		recordVideo("ProgrammableKey_13");
		//clearRecentApps();
		scroll_to_ProgrammableKey();
		scrollToNoApplication();
		assign_app_to_ProgrammableKey(Locators_ProgrammableKey.fmRadio_app);
		validate_launchedApplication(Locators_ProgrammableKey.fmRadio_page, data.get("summaryText"));
		 
		test.log(LogStatus.PASS, "Test case status is Passed");
	}

	@Test(priority=14,dataProvider="ProgrammableKeyTest", dataProviderClass=DataProviders.class)
	public void ProgrammableKey_14_Validate_successful_LaunchOf_Gallery(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException  {

		APP_LOGS.info("===========ProgrammableKey_14============");
		startAdbLog("ProgrammableKey_14");
		recordVideo("ProgrammableKey_14");
		//clearRecentApps();
		scroll_to_ProgrammableKey();
		scrollToNoApplication();
		assign_app_to_ProgrammableKey(Locators_ProgrammableKey.gallery_app);
		validate_launchedApplication(Locators_ProgrammableKey.gallery_page, data.get("summaryText"));
		 
		test.log(LogStatus.PASS, "Test case status is Passed");
	}

	@Test(priority=15,dataProvider="ProgrammableKeyTest", dataProviderClass=DataProviders.class)
	public void ProgrammableKey_15_Validate_successful_LaunchOf_MessagingApp(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException  {

		APP_LOGS.info("===========ProgrammableKey_15============");
		startAdbLog("ProgrammableKey_15");
		recordVideo("ProgrammableKey_15");
		//clearRecentApps();
		scroll_to_ProgrammableKey();
		scrollToNoApplication();
		assign_app_to_ProgrammableKey(Locators_ProgrammableKey.messaging_app);
		validate_launchedApplication(Locators_ProgrammableKey.messaging_page, data.get("summaryText"));
		 
		test.log(LogStatus.PASS, "Test case status is Passed");
	}

	@Test(priority=16,dataProvider="ProgrammableKeyTest", dataProviderClass=DataProviders.class)
	public void ProgrammableKey_16_Validate_successful_LaunchOf_MusicApp(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException  {

		APP_LOGS.info("===========ProgrammableKey_16============");
		startAdbLog("ProgrammableKey_16");
		recordVideo("ProgrammableKey_16");
		//clearRecentApps();
		scroll_to_ProgrammableKey();
		scrollToNoApplication();
		assign_app_to_ProgrammableKey(Locators_ProgrammableKey.music_app);
		validate_launchedApplication(Locators_ProgrammableKey.music_page, data.get("summaryText"));
		 
		test.log(LogStatus.PASS, "Test case status is Passed");
	}

	@Test(priority=17,dataProvider="ProgrammableKeyTest", dataProviderClass=DataProviders.class)
	public void ProgrammableKey_17_Validate_successful_LaunchOf_Phone(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException  {

		APP_LOGS.info("===========ProgrammableKey_17============");
		startAdbLog("ProgrammableKey_17");
		recordVideo("ProgrammableKey_17");
		//clearRecentApps();
		scroll_to_ProgrammableKey();
		scrollToNoApplication();
		assign_app_to_ProgrammableKey(Locators_ProgrammableKey.phone_app);
		validate_launchedApplication(Locators_ProgrammableKey.phone_page, data.get("summaryText"));
		 
		test.log(LogStatus.PASS, "Test case status is Passed");
	}

	@Test(priority=18,dataProvider="ProgrammableKeyTest", dataProviderClass=DataProviders.class)
	public void ProgrammableKey_18_Validate_successful_LaunchOf_Settings(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException  {

		APP_LOGS.info("===========ProgrammableKey_18============");
		startAdbLog("ProgrammableKey_18");
		recordVideo("ProgrammableKey_18");
		//clearRecentApps();
		scroll_to_ProgrammableKey();
		scrollToNoApplication();
		assign_app_to_ProgrammableKey(Locators_ProgrammableKey.settings_app);
		validate_launchedApplication(Locators_ProgrammableKey.setting_page, data.get("summaryText"));
		 
		test.log(LogStatus.PASS, "Test case status is Passed");
	}

	@Test(priority=19,dataProvider="ProgrammableKeyTest", dataProviderClass=DataProviders.class)
	public void ProgrammableKey_19_Validate_successful_LaunchOf_SonimBLE(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException  {

		APP_LOGS.info("===========ProgrammableKey_19============");
//		startAdbLog("ProgrammableKey_19");
//		recordVideo("ProgrammableKey_19");
//		//clearRecentApps();
//		scroll_to_ProgrammableKey();
//		scrollToNoApplication();
//		assign_app_to_ProgrammableKey(Locators_ProgrammableKey.sonimBLE_app);
//		validate_launchedApplication(Locators_ProgrammableKey.sonimBLE_page, data.get("summaryText"));
//		 
		test.log(LogStatus.SKIP, "Sonim BLE is removed from programableKey");
	}

	@Test(priority=20,dataProvider="ProgrammableKeyTest", dataProviderClass=DataProviders.class)
	public void ProgrammableKey_20_Validate_successful_LaunchOf_SonimScout(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException  {

		APP_LOGS.info("===========ProgrammableKey_20============");
		startAdbLog("ProgrammableKey_20");
		recordVideo("ProgrammableKey_20");
		//clearRecentApps();
		scroll_to_ProgrammableKey();
		scrollToNoApplication();
		assign_app_to_ProgrammableKey(Locators_ProgrammableKey.sonimScout_app);
		validate_launchedApplication(Locators_ProgrammableKey.sonimScout_page, data.get("summaryText"));
		 
		test.log(LogStatus.PASS, "Test case status is Passed");
	}

	@Test(priority=21,dataProvider="ProgrammableKeyTest", dataProviderClass=DataProviders.class)
	public void ProgrammableKey_21_Validate_successful_LaunchOf_SonimWarranty(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException  {

		APP_LOGS.info("===========ProgrammableKey_21============");
//		startAdbLog("ProgrammableKey_21");
//		recordVideo("ProgrammableKey_21");
//		//clearRecentApps();
//		scroll_to_ProgrammableKey();
//		scrollToNoApplication();
//		assign_app_to_ProgrammableKey(Locators_ProgrammableKey.sonimScoutWarranty_app);
//		validate_launchedApplication(Locators_ProgrammableKey.warrantyReg_page, data.get("summaryText"));
//		 
		test.log(LogStatus.SKIP, "SonimWarranty is removed from programableKey");
	}

	@Test(priority=22,dataProvider="ProgrammableKeyTest", dataProviderClass=DataProviders.class)
	public void ProgrammableKey_22_Validate_successful_LaunchOf_SonimCare(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException  {

		APP_LOGS.info("===========ProgrammableKey_22============");
//		startAdbLog("ProgrammableKey_22");
//		recordVideo("ProgrammableKey_22");
//		//clearRecentApps();
//		scroll_to_ProgrammableKey();
//		scrollToNoApplication();
//		assign_app_to_ProgrammableKey(Locators_ProgrammableKey.sonimCare_app);
//		validate_launchedApplication(Locators_ProgrammableKey.sonimCare_page, data.get("summaryText"));
//		 
		test.log(LogStatus.SKIP, "Sonim Care is removed from programableKey");
	}

	@Test(priority=23,dataProvider="ProgrammableKeyTest", dataProviderClass=DataProviders.class)
	public void ProgrammableKey_23_Validate_successful_LaunchOf_SonimSetupWizard(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException  {

		APP_LOGS.info("===========ProgrammableKey_23============");
//		startAdbLog("ProgrammableKey_23");
//		recordVideo("ProgrammableKey_23");
//		//clearRecentApps();
//		scroll_to_ProgrammableKey();
//		scrollToNoApplication();
//		assign_app_to_ProgrammableKey(Locators_ProgrammableKey.setUpWizard_app);
//		validate_launchedApplication(Locators_ProgrammableKey.setUpWizard_page, data.get("summaryText"));
//		 
		test.log(LogStatus.SKIP, "Sonim Setup Wizard is removed from programableKey");
	}

	@Test(priority=24,dataProvider="ProgrammableKeyTest", dataProviderClass=DataProviders.class)
	public void ProgrammableKey_24_Validate_successful_LaunchOf_SoundRecord(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException  {

		APP_LOGS.info("===========ProgrammableKey_24============");
		startAdbLog("ProgrammableKey_24");
		recordVideo("ProgrammableKey_24");
		//clearRecentApps();
		scroll_to_ProgrammableKey();
		scrollToNoApplication();
		assign_app_to_ProgrammableKey(Locators_ProgrammableKey.soundRecorder_app);
		validate_launchedApplication(Locators_ProgrammableKey.soundRecorder_page, data.get("summaryText"));
		 
		test.log(LogStatus.PASS, "Test case status is Passed");
	}
}
