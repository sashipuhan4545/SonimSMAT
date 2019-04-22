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

import com.aosp.Utils.DataProviders;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTestInterruptedException;
import com.relevantcodes.extentreports.LogStatus;
import com.xp3.Utils.Locators_BaseUtil;
import com.xp3.Utils.Locators_DevSanity;
import com.xp3.Utils.XP3_QuickSanity_Util;
import com.xp5S.util.ExcelConstants;
import com.xp5S.util.ExcelReader;
import com.xp5S.util.GetMethods;
import com.xp5S.util.appiumService;

import application.AllQA;
import application.SalesTeam;
import io.appium.java_client.android.AndroidKeyCode;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import javafx.application.Platform;

public class XP3_QuickSanity_Test  extends XP3_QuickSanity_Util{
	
	public ExcelReader excel;
	Properties properties;
	public static ExtentTestInterruptedException testexception;


	@BeforeSuite
	public void beforeSuite() throws IOException, InterruptedException, ParseException 
	{
		extent = new ExtentReports("src/test/resources/extentreport/XP3_Sanity.html", true); 
		fetch_Devices_Details();

	}
	
	@BeforeSuite
	public void numofTestCases() throws ClassNotFoundException {


		//int numberOfTestCases = GetMethods.TotalTestcase("XP3_", this.getClass());
		appiumService.TOTAL_NUM_OF_TESTCASES=GetMethods.TotalTestcase("XP3_", this.getClass());
		
	//	appiumService.PROGRESSPERCENTAGE_CONSTANT=GetMethods.percentPerCase(numberOfTestCases);
		
     //	System.out.println("Percentage completion per case =" + GetMethods.percentPerCase(numberOfTestCases));


	}
	
	
	

	@BeforeMethod()
	public  void beforeMethod(Method method) throws InterruptedException 
	{

		test = extent.startTest( (this.getClass().getSimpleName() +" :: "+  method.getName()),method.getName()); //Test Case Start Here
		test.assignAuthor(""); //Test Script Author Name
		extent.loadConfig(new File(System.getProperty("user.dir")+"//ReportsConfig.xml"));
		extent.addSystemInfo("Appium Version", "1.2.7").addSystemInfo("Environment", "TEST");
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
		clearRecentApps();
	}
	
	


	/*
	@Test(priority=1,dataProvider="XP3_QuickSanity", dataProviderClass=DataProviders.class)
	public void XP3_TC_01_QuickSanity_check_Enable_Disable_AirplaneMode(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException 
	{
		APP_LOGS.info("=========== XP3_TC_01_QuickSanity============");
		startAdbLog("XP3_QuickSanity_01");		
		enableData();
		enableAirplaneMode();
		//enableSwitch(Locators_DevSanity.enabled_Airplane,Locators_DevSanity.disabled_Airplane,Locators_DevSanity.switch_widget);
		customWait(2000);
		launch_an_app("browser");	
		customWait(5000);
		enterUrl("www.google.com");
		validateAirplaneSB();			
		test.log(LogStatus.PASS, "Test case status is Passed");
	}
	
	
	@Test(priority=3,dataProvider="XP3_QuickSanity", dataProviderClass=DataProviders.class)
	public void XP3_TC_03_QuickSanity_LaunchApp_Menu_and_LaunchAllApp_from_ApplicationMenu(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException {

		APP_LOGS.info("=========== XP3_TC_03_QuickSanity============");
		startAdbLog("XP3_QuickSanity_03");
		customWait(500);
	    validateAppMenuLaunch();	
	    customWait(500);
		validateLaunch_All_Applications();		
		customWait(3000);
		test.log(LogStatus.PASS, "Test case status is Passed");
	}
	
*/
	
	@Test(priority=2,dataProvider="XP3_QuickSanity", dataProviderClass=DataProviders.class)
	public void XP3_TC_02_QuickSanity_check_Baseband_Version(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException 
	{
		APP_LOGS.info("=========== XP3_TC_02_QuickSanity============");
		try {
			startAdbLog("XP3_QuickSanity_02");
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_HOME);
			minWait();
			navigateUsingText("Settings");
			checkBasebandVersion();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

/*
	@Test(priority=4,dataProvider="XP3_QuickSanity", dataProviderClass=DataProviders.class)
	public void XP3_TC_04_QuickSanity_check_Able_to_Make_VoiceCall(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException 
	{
		
		
		
		APP_LOGS.info("=========== XP3_TC_04_QuickSanity============");
		startAdbLog("XP3_QuickSanity_04");
		MO_CALL_Sanity();
		test.log(LogStatus.PASS, "Test case status is Passed");
	}
	
	@Test(priority=5,dataProvider="XP3_QuickSanity", dataProviderClass=DataProviders.class)
	public void XP3_TC_05_QuickSanity_check_Able_to_Receive_VoiceCall(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException 
	{
		APP_LOGS.info("=========== XP5S_TC_05_DevSanity============");
		startAdbLog("XP5_Dev_Sanity_05");
		MT_CALL_Sanity();
		test.log(LogStatus.PASS, "Test case status is Passed");
	}
	
	@Test(priority=6,dataProvider="XP3_QuickSanity", dataProviderClass=DataProviders.class)
	public void XP3_TC_06_QuickSanity_check_Able_to_Reject_VoiceCall(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException 
	{
		APP_LOGS.info("=========== XP5S_TC_06_DevSanity============");
		startAdbLog("XP5_Dev_Sanity_06");
		MT_CALL_REJECT_Sanity();
		test.log(LogStatus.PASS, "Test case status is Passed");
	}
	
	
	@Test(priority=7,dataProvider="XP3_QuickSanity", dataProviderClass=DataProviders.class)
	public void XP3_TC_07_QuickSanity_Validate_In_Call_Functionality(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException 
	{
		APP_LOGS.info("=========== XP5S_TC_07_DevSanity============");
		startLog("XP3_QuickSanity_07");
		launch_an_app("phone");
		inCallFunctionality();
		test.log(LogStatus.PASS, "Test case status is Passed");		
	}
	
	
	@Test(priority=8,dataProvider="XP3_QuickSanity", dataProviderClass=DataProviders.class)
	public void XP3_TC_08_QuickSanity_Send_SMS_During_Ongoing_Voice_Call(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException 
	{
		APP_LOGS.info("=========== XP3_TC_08_QuickSanity============");
		startAdbLog("XP3_QuickSanity_08");
		launch_an_app("phone");
		minWait();
		sendSMSWhenInCall(data.get("text"));
		test.log(LogStatus.PASS, "Test case status is Passed");	
	}
	

	
	@Test(priority=9,dataProvider="XP3_QuickSanity", dataProviderClass=DataProviders.class)
	public void XP3_TC_09_QuickSanity_check_Send_SMS(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException 
	{
		APP_LOGS.info("=========== XP3_TC_09_QuickSanity============");
		startAdbLog("XP3_QuickSanity_09");
		launch_an_app("messaging");
		delete_All_Threads();
		composeMsg(data.get("composeMessage"),AllQA.REFERENCEDEVMDN);
		send();
		validateMsgSent("Sent SMS");	
		test.log(LogStatus.PASS, "Test case status is Passed");
	}
	
	
	@Test(priority=10,dataProvider="XP3_QuickSanity", dataProviderClass=DataProviders.class)
	public void XP3_TC_10_QuickSanity_check_Send_MultiPage_SMS(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException 
	{
		APP_LOGS.info("=========== XP3_TC_10_QuickSanity============");
		startAdbLog("XP3_QuickSanity_10");
		launch_an_app("messaging");
		composeMsg(data.get("composeMessage"),AllQA.REFERENCEDEVMDN);
		send() ;
		validateMsgSent("Sent SMS");	
		test.log(LogStatus.PASS, "Test case status is Passed");
	}

	
	
	@Test(priority=11,dataProvider="XP3_QuickSanity", dataProviderClass=DataProviders.class)
	public void XP3_TC_11_QuickSanity_check_Receive_SMS(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException 
	{
		APP_LOGS.info("=========== XP5S_TC_11_DevSanity============");
		startAdbLog("XP3_QuickSanity_11");
		launch_an_app("messaging");
		sendSMS_fromRefDevice();
		validateMsgSent("received SMS");	
		test.log(LogStatus.PASS, "Test case status is Passed");
	}
	

	
	@Test(priority=12,dataProvider="XP3_QuickSanity", dataProviderClass=DataProviders.class)
	public void XP3_TC_12_QuickSanity_check_SMS_able_To_Delete(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException 
	{
		APP_LOGS.info("=========== XP3_TC_12_QuickSanity============");
		startAdbLog("XP3_QuickSanity_12");
	    launch_an_app("messaging");
	    deleteMsg();
	    validateDeleteSMS("SMS");
		test.log(LogStatus.PASS, "Test case status is Passed");
	}
	

	
	@Test(priority=-13,dataProvider="XP3_QuickSanity", dataProviderClass=DataProviders.class)
	public void XP3_TC_13_QuickSanity_check_Browser_using_MobileData(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException 
	{
		APP_LOGS.info("=========== XP3_TC_13_DevSanity============");
		startAdbLog("XP3_QuickSanity_13");
		disableData();
		launch_an_app("browser");
		validateBrowseNoInternet();
		enableData();
		launch_an_app("browser");		
		validateBrowseInternet();
		test.log(LogStatus.PASS, "Test case status is Passed");
		
	}	
	
	
	
	
	@Test(priority=13,dataProvider="XP3_QuickSanity", dataProviderClass=DataProviders.class)
	public void XP3_TC_13_QuickSanity_check_Able_to_Change_SleepTime(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException 
	{
		APP_LOGS.info("=========== XP3_TC_13_QuickSanity============");
		startAdbLog("XP3_QuickSanity_13");	
		launch_an_app("settings");
		navigateToSettingsFeature(Locators_DevSanity.display_feature,"Display");			
		validateChaneSleepTime();
		
	}
	
	
	@Test(priority=14,dataProvider="XP3_QuickSanity", dataProviderClass=DataProviders.class)
	public void XP3_TC_14_QuickSanity_Add_An_Alarm_Event(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException {

		APP_LOGS.info("===========XP3_QuickSanity_14============");
		startAdbLog("XP3_QuickSanity_14");
		launchClock();
		minWait();
		addAlarm();
		minWait();
		validateAddedAlarm();
		test.log(LogStatus.PASS, "Test case status is Passed");		
	}
	
	*/
	
	
}
