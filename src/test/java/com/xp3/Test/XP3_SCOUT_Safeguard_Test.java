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
import com.xp3.Utils.Locators_SafeGuard;
import com.xp3.Utils.XP3_SCOUT_Safeguard_Util;
import com.xp5S.util.DataProviders;
import com.xp5S.util.ExcelConstants;
import com.xp5S.util.ExcelReader;
import com.xp5S.util.GetMethods;
import com.xp5S.util.appiumService;

import io.appium.java_client.android.AndroidKeyCode;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class XP3_SCOUT_Safeguard_Test extends XP3_SCOUT_Safeguard_Util{

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
		extent = new ExtentReports("src/test/resources/extentreport/XP3_SCOUT_Safeguard_TestReport.html", true); 
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
		Locators_SafeGuard loc=new Locators_SafeGuard(aDriver);
		Locators_BaseUtil loc1 =new Locators_BaseUtil(aDriver);
		PageFactory.initElements(new AppiumFieldDecorator(getDriver()),loc1);
		PageFactory.initElements(new AppiumFieldDecorator(getDriver()),loc);
		excel=new ExcelReader(ExcelConstants.XP5S_XL_PATH);
	}

	//================================================Test cases==============================================
	@Test(priority=1,dataProvider="XP3_Safeguard", dataProviderClass=DataProviders.class)
	public void XP3_TC_001_SG_Launch_Safeguard_Application_from_SCOUTAPP(Hashtable<String, String> data) throws AWTException, InterruptedException, IOException, ParseException {

		APP_LOGS.info("=========== XP3_TC_001_SG_Launch_Safeguard_Application_from_SCOUTAPP============");
		startAdbLog("XP3_Safeguard_001");
		Launch_SafeGuard();
		validateLaunchScoutApp(Locators_SafeGuard.Safeguard, "SafeGuard");
	}

	@Test(priority=2,dataProvider="XP3_Safeguard", dataProviderClass=DataProviders.class)
	public void XP3_TC_002_SG_Check_default_Activation_Status_of_Safeguard(Hashtable<String, String> data) throws AWTException, InterruptedException, IOException, ParseException {

		APP_LOGS.info("=========== XP3_TC_002_SG_Check_default_Activation_Status_of_Safeguard============");
		startAdbLog("XP3_Safeguard_002");
		Launch_SafeGuard();
		checkStatus();
	}

	@Test(priority=3,dataProvider="XP3_Safeguard", dataProviderClass=DataProviders.class)
	public void XP3_TC_003_SG_Check_default_status_of_All_options_when_activation_is_OFF(Hashtable<String, String> data) throws AWTException, InterruptedException, IOException, ParseException {

		APP_LOGS.info("=========== XP3_TC_003_SG_Check_default_status_of_All_options_when_activation_is_OFF============");
		startAdbLog("XP3_Safeguard_003");
		Launch_SafeGuard();
		checkStatusOfAllOptions();
	}
	
	@Test(priority=4,dataProvider="XP3_Safeguard", dataProviderClass=DataProviders.class)
	public void XP3_TC_004_SG_Fetch_and_display_Version_and_default_PIN(Hashtable<String, String> data) throws AWTException, InterruptedException, IOException, ParseException {

		APP_LOGS.info("=========== XP3_TC_004_SG_Fetch_and_display_Version_and_default_PIN============");
		startAdbLog("XP3_Safeguard_004");
		Launch_SafeGuard();
		fetchDetails();
	}
	
	@Test(priority=5,dataProvider="XP3_Safeguard", dataProviderClass=DataProviders.class)
	public void XP3_TC_005_SG_Turn_On_SafeGuard_Activation_and_validate(Hashtable<String, String> data) throws AWTException, InterruptedException, IOException, ParseException {

		APP_LOGS.info("=========== XP3_TC_005_SG_Turn_On_SafeGuard_Activation_and_validate============");
		startAdbLog("XP3_Safeguard_005");
		Launch_SafeGuard();
		turn_On_SG();
		validateActivationONStatus();
	}
	
	@Test(priority=6,dataProvider="XP3_Safeguard", dataProviderClass=DataProviders.class)
	public void XP3_TC_006_SG_Select_All_Applications_SafeGuard_and_validate(Hashtable<String, String> data) throws AWTException, InterruptedException, IOException, ParseException {

		APP_LOGS.info("=========== XP3_TC_006_SG_Select_All_Applications_SafeGuard_and_validate============");
		startAdbLog("XP3_Safeguard_006");
		enableData();
		ConnectSecuredWifi();
		Launch_SafeGuard();
		if(Locators_SafeGuard.Activation_switch.getText().equals("OFF")) {
			turn_On_SG();
		}
		selectAllApplications();
		launch_and_validate_safeguarded_apps();
	}
	
	@Test(priority=7,dataProvider="XP3_Safeguard", dataProviderClass=DataProviders.class)
	public void XP3_TC_007_SG_UnselectAll_Apps_Select_An_Application_SafeGuard_and_validate(Hashtable<String, String> data) throws AWTException, InterruptedException, IOException, ParseException {

		APP_LOGS.info("=========== XP3_TC_007_SG_UnselectAll_Apps_Select_An_Application_SafeGuard_and_validate============");
		startAdbLog("XP3_Safeguard_007");
		launchSafeGuardedApps("scout");
		for(int i=0;i<2;i++) {
		aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_LEFT);
		minWait();
		}
		customWait(3000);
		navigateApps(Locators_SafeGuard.setUpTab,Locators_SafeGuard.Safeguard);
		unselectAllApplications();
		selectAnAppAndvalidate();
		Launch_SafeGuard();
		unselectAllApplications();
	}
	
	@Test(priority=8,dataProvider="XP3_Safeguard", dataProviderClass=DataProviders.class)
	public void XP3_TC_008_SG_Select_All_Features_SafeGuard_and_validate(Hashtable<String, String> data) throws AWTException, InterruptedException, IOException, ParseException {

		APP_LOGS.info("=========== XP3_TC_008_SG_Select_All_Features_SafeGuard_and_validate============");
		startAdbLog("XP3_Safeguard_008");
		Launch_SafeGuard();
		if(Locators_SafeGuard.Activation_switch.getText().equals("OFF")) {
			turn_On_SG();
		}
		selectAllFeatures();
		launch_and_validate_safeguarded_features();
	}
	
	@Test(priority=9,dataProvider="XP3_Safeguard", dataProviderClass=DataProviders.class)
	public void XP3_TC_009_SG_UnselectAll_Features_Select_A_Feature_SafeGuard_and_validate(Hashtable<String, String> data) throws AWTException, InterruptedException, IOException, ParseException {

		APP_LOGS.info("=========== XP3_TC_008_SG_Select_All_Features_SafeGuard_and_validate============");
		startAdbLog("XP3_Safeguard_008");
		Launch_SafeGuard();
		if(Locators_SafeGuard.Activation_switch.getText().equals("OFF")) {
			turn_On_SG();
		}
		unselectAllfeatures();
		select_a_feature_and_validate();
		Launch_SafeGuard();
		unselectAllfeatures();
	}
	
	@Test(priority=10,dataProvider="XP3_Safeguard", dataProviderClass=DataProviders.class)
	public void XP3_TC_010_SG_Change_PIN_and_validate(Hashtable<String, String> data) throws AWTException, InterruptedException, IOException, ParseException {

		APP_LOGS.info("=========== XP3_TC_010_SG_Change_PIN_and_validate============");
		startAdbLog("XP3_Safeguard_010");
		Launch_SafeGuard();
		changePIN("1234","4321");
		validateChangePIN();
		changePIN("4321","1234");
	}
	
	@Test(priority=11,dataProvider="XP3_Safeguard", dataProviderClass=DataProviders.class)
	public void XP3_TC_011_SG_Validate_Application_PIN_Timeout(Hashtable<String, String> data) throws AWTException, InterruptedException, IOException, ParseException {

		APP_LOGS.info("=========== XP3_TC_011_SG_Validate_Application_PIN_Timeout============");
		startAdbLog("XP3_Safeguard_011");
		Launch_SafeGuard();
		selectAnAppAndvalidate();
		validate_application_PIN_timeout_Immediate();
		validate_application_PIN_timeout_30_Seconds();
		validate_application_PIN_timeout_1_minute(); 
		Launch_SafeGuard();
		unselectAllApplications();
		clickBtn(Locators_SafeGuard.Activation_switch);
		enter_Pin();
		clickBackButton(3);
	}
	
	
	
	
	
	
	
	
	
	
	
}
