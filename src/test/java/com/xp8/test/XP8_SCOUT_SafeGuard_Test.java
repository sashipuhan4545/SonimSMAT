package com.xp8.test;

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

import com.graphics.gui.JsonFileReaderAndWriter;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTestInterruptedException;
import com.relevantcodes.extentreports.LogStatus;
import com.xp5S.util.BaseUtil;
import com.xp5S.util.GetMethods;
import com.xp5S.util.appiumService;
import com.xp8.util.DataProviders;
import com.xp8.util.ExcelConstants;
import com.xp8.util.ExcelReader;
import com.xp8.util.Locators_BaseUtil;
import com.xp8.util.XP8_Locators_ContactTransfer;
import com.xp8.util.XP8_Locators_SafeGuard;
import com.xp8.util.XP8_SCOUT_Contact_Transfer_Util;
import com.xp8.util.XP8_SCOUT_SafeGuard_Util;

import io.appium.java_client.android.AndroidKeyCode;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class XP8_SCOUT_SafeGuard_Test extends XP8_SCOUT_SafeGuard_Util{

	public ExcelReader excel;
	Properties properties;
	public static ExtentTestInterruptedException testexception;
	public int  itr =3;
	boolean value = false;

	@BeforeSuite
	public void beforeSuite() throws FileNotFoundException, InterruptedException, IOException, ParseException, ParseException  
	{
		extent = new ExtentReports("src/test/resources/extentreport/XP8_SCOUT_SafeGuard_TestReport.html", true); //Provide Desired Report Directory Location and Name

		//extent.loadConfig(new File(System.getProperty("user.dir")+"//ReportsConfig.xml"));

		extent.loadConfig(new File("src/test/resources/StorageFile/ReportsConfig.xml"));
		extent.addSystemInfo("Build #",JsonFileReaderAndWriter.primaryDevFirmwareReader())
		.addSystemInfo("Product",JsonFileReaderAndWriter.primaryDevModelReader())
		.addSystemInfo("Operator", JsonFileReaderAndWriter.primaryDevOperatorReader());		

		fetch_Devices_Details();	
	}
	
	
	@BeforeSuite
	public void numofTestCases() throws ClassNotFoundException {
		
		appiumService.TOTAL_NUM_OF_TESTCASES=GetMethods.TotalTestcase("XP8_TC", this.getClass());
	}

	@BeforeMethod()
	public  void beforeMethod(Method method) 
	{
		test = extent.startTest( (this.getClass().getSimpleName() +" :: "+  method.getName()),method.getName()); //Test Case Start Here
		test.assignAuthor(""); //Test Script Author Name
		String BuildNumber=BaseUtil.fetchDeviceProperty("adb shell getprop ro.build.id");
		test.assignCategory("Build #:"+BuildNumber);
	}


	@BeforeClass
	public void copyFilesToDevice() throws IOException, ParseException {
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
	public void tearDown(ITestResult result,Method method) throws IOException, ParseException, InterruptedException
	{
		if(result.getStatus()==ITestResult.FAILURE)
		{	
		String screenshot_path=captureScreenshot(method.getName());
		String image= test.addScreenCapture(screenshot_path);	
		test.log(LogStatus.FAIL,result.getThrowable());						
		}
		
		extent.endTest(test);
		extent.flush();
	}


	@BeforeTest
	public void setupSys() throws MalformedURLException, InterruptedException, FileNotFoundException, AWTException{
		properties=loadDriverAndProperties();
		XP8_Locators_SafeGuard loc=new XP8_Locators_SafeGuard(aDriver);
		Locators_BaseUtil loc1 = new Locators_BaseUtil(aDriver);	
		PageFactory.initElements(new AppiumFieldDecorator(aDriver),loc);
		PageFactory.initElements(new AppiumFieldDecorator(aDriver),loc1);
		excel=new ExcelReader(ExcelConstants.XP5S_XL_PATH);	
	}
//============================================Test case=============================================================================
	@Test(priority=1,dataProvider="XP8_SafeGuard", dataProviderClass=DataProviders.class)
	public void XP8_TC_001_SG_Launch_Safeguard_Application_from_SCOUTAPP(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException, ParseException
	{
		APP_LOGS.info("=========== XP8_TC_001_SG_Launch_Safeguard_Application_from_SCOUTAPP============");
		startAdbLog("XP8_SafeGuard_001");
		Launch_SafeGuard(XP8_Locators_SafeGuard.sonim_scout_AppLauncher);
		validateLaunchScoutApp(XP8_Locators_SafeGuard.Safeguard, "SafeGuard");
	}
	
	@Test(priority=2,dataProvider="XP8_SafeGuard", dataProviderClass=DataProviders.class)
	public void XP8_TC_002_SG_Check_default_Activation_Status_of_Safeguard(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException, ParseException
	{
		APP_LOGS.info("=========== XP8_TC_002_SG_Check_default_Activation_Status_of_Safeguard============");
		startAdbLog("XP8_SafeGuard_002");
		Launch_SafeGuard(XP8_Locators_SafeGuard.sonim_scout_AppLauncher);
		checkStatus();
	}
	
	@Test(priority=3,dataProvider="XP8_SafeGuard", dataProviderClass=DataProviders.class)
	public void XP8_TC_003_SG_Check_default_status_of_All_options_when_activation_is_OFF(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException, ParseException
	{
		APP_LOGS.info("=========== XP8_TC_003_SG_Check_default_status_of_All_options_when_activation_is_OFF============");
		startAdbLog("XP8_SafeGuard_003");
		Launch_SafeGuard(XP8_Locators_SafeGuard.sonim_scout_AppLauncher);
		checkStatusOfAllOptions();
	}
	
	@Test(priority=4,dataProvider="XP8_SafeGuard", dataProviderClass=DataProviders.class)
	public void XP8_TC_004_SG_Fetch_and_display_Version_and_default_PIN(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException, ParseException
	{
		APP_LOGS.info("=========== XP8_TC_004_SG_Fetch_and_display_Version_and_default_PIN============");
		startAdbLog("XP8_SafeGuard_004");
		Launch_SafeGuard(XP8_Locators_SafeGuard.sonim_scout_AppLauncher);
		fetchDetails();
	}
	
	@Test(priority=5,dataProvider="XP8_SafeGuard", dataProviderClass=DataProviders.class)
	public void XP8_TC_005_SG_Turn_On_SafeGuard_Activation_and_validate(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException, ParseException
	{
		APP_LOGS.info("=========== XP8_TC_005_SG_Turn_On_SafeGuard_Activation_and_validate============");
		startAdbLog("XP8_SafeGuard_005");
		Launch_SafeGuard(XP8_Locators_SafeGuard.sonim_scout_AppLauncher);
		turn_On_SG();
		validateActivationONStatus();
	}
	
	@Test(priority=6,dataProvider="XP8_SafeGuard", dataProviderClass=DataProviders.class)
	public void XP8_TC_006_SG_Select_All_Applications_SafeGuard_and_validate(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException, ParseException
	{
		APP_LOGS.info("=========== XP8_TC_006_SG_Select_All_Applications_SafeGuard_and_validate============");
		startAdbLog("XP8_SafeGuard_006");
		Launch_SafeGuard(XP8_Locators_SafeGuard.sonim_scout_AppLauncher);
		if(XP8_Locators_SafeGuard.Activation_switch.getText().equals("OFF")) {
			turn_On_SG();
		}
		selectAllApplications();
		launch_and_validate_safeguarded_apps();
	}
	
	@Test(priority=7,dataProvider="XP8_SafeGuard", dataProviderClass=DataProviders.class)
	public void XP8_TC_007_SG_UnselectAll_Apps_Select_An_Application_SafeGuard_and_validate(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException, ParseException
	{
		APP_LOGS.info("=========== XP8_TC_007_SG_UnselectAll_Apps_Select_An_Application_SafeGuard_and_validate============");
		startAdbLog("XP8_SafeGuard_007");
		launchSafeGuardedApps("scout");
		customWait(3000);
		clickBtn(XP8_Locators_SafeGuard.setUpTab);
		minWait();
		clickBtn(XP8_Locators_SafeGuard.Safeguard);
		minWait();
		unselectAllApplications();
		selectAnAppAndvalidate();
		Launch_SafeGuard(XP8_Locators_SafeGuard.sonim_scout_AppLauncher);
		unselectAllApplications();
	}
	
	@Test(priority=8,dataProvider="XP8_SafeGuard", dataProviderClass=DataProviders.class)
	public void XP8_TC_008_SG_Select_All_Features_SafeGuard_and_validate(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException, ParseException
	{
		APP_LOGS.info("=========== XP8_TC_008_SG_Select_All_Features_SafeGuard_and_validate============");
		startAdbLog("XP8_SafeGuard_008");
		Launch_SafeGuard(XP8_Locators_SafeGuard.sonim_scout_AppLauncher);
		if(XP8_Locators_SafeGuard.Activation_switch.getText().equals("OFF")) {
			turn_On_SG();
		}
		selectAllFeatures();
		launch_and_validate_safeguarded_features();
	}
	
	@Test(priority=9,dataProvider="XP8_SafeGuard", dataProviderClass=DataProviders.class)
	public void XP8_TC_009_SG_UnselectAll_Features_Select_A_Feature_SafeGuard_and_validate(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException, ParseException
	{
		APP_LOGS.info("=========== XP8_TC_009_SG_UnselectAll_Features_Select_A_Feature_SafeGuard_and_validate============");
		startAdbLog("XP8_SafeGuard_009");
		Launch_SafeGuard(XP8_Locators_SafeGuard.sonim_scout_AppLauncher);
		if(XP8_Locators_SafeGuard.Activation_switch.getText().equals("OFF")) {
			turn_On_SG();
		}
		unselectAllfeatures();
		select_a_feature_and_validate();
		Launch_SafeGuard(XP8_Locators_SafeGuard.sonim_scout_AppLauncher);
		unselectAllfeatures();
	}
	
	@Test(priority=10,dataProvider="XP8_SafeGuard", dataProviderClass=DataProviders.class)
	public void XP8_TC_010_SG_Change_PIN_and_validate(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException, ParseException
	{
		APP_LOGS.info("=========== XP8_TC_010_SG_Change_PIN_and_validate============");
		startAdbLog("XP8_SafeGuard_010");
		Launch_SafeGuard(XP8_Locators_SafeGuard.sonim_scout_AppLauncher);
		changePIN("1234","4321");
		validateChangePIN();
		changePIN("4321","1234");
	}
	
	@Test(priority=11,dataProvider="XP8_SafeGuard", dataProviderClass=DataProviders.class)
	public void XP8_TC_011_SG_Validate_Application_PIN_Timeout(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException, ParseException
	{
		APP_LOGS.info("=========== XP8_TC_011_SG_Validate_Application_PIN_Timeout============");
		startAdbLog("XP8_SafeGuard_010");
		Launch_SafeGuard(XP8_Locators_SafeGuard.sonim_scout_AppLauncher);
		selectAnAppAndvalidate();
		validate_application_PIN_timeout_Immediate();
		validate_application_PIN_timeout_30_Seconds();
		validate_application_PIN_timeout_1_minute(); 
		Launch_SafeGuard(XP8_Locators_SafeGuard.sonim_scout_AppLauncher);
		unselectAllApplications();
		clickBtn(XP8_Locators_SafeGuard.Activation_switch);
		enter_Pin();
		clickBackButton(3);
	}
	
	
}
