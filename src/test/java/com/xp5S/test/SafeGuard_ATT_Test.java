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
import com.xp5S.util.Locators;
import com.xp5S.util.Locators_BaseUtil;
import com.xp5S.util.Locators_Safeguard;
import com.xp5S.util.SafeGuard_Util;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class SafeGuard_ATT_Test extends SafeGuard_Util{

	Locators locators;
	public static ExcelReader excel;
	Properties properties;

	public static ExtentReports extent;
	public static ExtentTest test;

	public static ExtentTestInterruptedException testexception;
	//public AndroidDriver<AndroidElement> aDriver;


	@BeforeSuite
	public void beforeSuite() 
	{
		//		extent = new ExtentReports("src/test/resources/extentreport/CalculatorTestReport.html", true);
		extent = new ExtentReports("src/test/resources/extentreport/XP5S_ScoutApps_Safeguard_TestReport.html", true);//Provide Desired Report Directory Location and Name

	}


	@BeforeMethod()
	public  void beforeMethod(Method method) throws InterruptedException 
	{	
		test = extent.startTest( (this.getClass().getSimpleName() +" :: "+  method.getName()),method.getName()); //Test Case Start Here
		test.assignAuthor(" "); //Test Script Author Name
		extent.loadConfig(new File(System.getProperty("user.dir")+"//ReportsConfig.xml"));
	    extent.addSystemInfo("Appium Version", "1.2.7").addSystemInfo("Environment", "TEST").addSystemInfo("Model Type", "ScoutApps");
        String BuildNumber=BaseUtil.fetchDeviceProperty("adb shell getprop ro.build.id");
	    test.assignCategory("XP5S SafeGuard -Report Build #:"+BuildNumber);
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
			//Here In case of TC failure ,This will clear the reccent APP and 
			

			customWait(2000);
			clearRecentApps();

		}
		extent.endTest(test);
		extent.flush();
	}


	/*@AfterMethod
	public void setProgressBar_TestResult() {
		int count =CountIncrementFile.getCount(6);  
		if(count==96) {
			count=count+4;
			CountIncrementFile.putCount(count);

		}
		else {

			ToolFrame.progressBar.setValue(count);
			count=count+6;
			CountIncrementFile.putCount(count);
		}
	}
*/
	@BeforeTest
	public void setupSys() throws MalformedURLException, InterruptedException, FileNotFoundException, AWTException{
		properties=loadDriverAndProperties();
		Locators_Safeguard loc=new Locators_Safeguard(aDriver);
		Locators_BaseUtil loc1 =new Locators_BaseUtil(aDriver);
		PageFactory.initElements(new AppiumFieldDecorator(getDriver()),loc1);
		PageFactory.initElements(new AppiumFieldDecorator(getDriver()),loc);
		excel=new ExcelReader(ExcelConstants.XP5S_XL_PATH);
	}


	@Test(priority=1,dataProvider="Safe_Guard_Data_Provider", dataProviderClass=DataProviders.class)
	public void XP5s_01_Validate_Presence_Of_SG_In_Scout(Hashtable<String, String> data) throws Exception
	{
		APP_LOGS.info("===========SafeGuardTest_01============");
		startAdbLog("SafeGuardTest_01");
		recordVideo("SafeGuardTest_01");
		sf_app_Presence_Validation();
		test.log(LogStatus.PASS, "TestCase Passed");		
	}	
	 
	@Test(priority=2,dataProvider="Safe_Guard_Data_Provider", dataProviderClass=DataProviders.class)
	public void XP5s_02_Default_State_SG_In_Scout(Hashtable<String, String> data) throws InterruptedException, IOException, ParseException , AWTException
	{
		APP_LOGS.info("===========SafeGuardTest_02============");
		startAdbLog("SafeGuardTest_02");
		recordVideo("SafeGuardTest_02");
		sf_app_Verify_Defualt_state();	
		test.log(LogStatus.PASS, "TestCase Passed");
	}
	
	@Test(priority=3,dataProvider="Safe_Guard_Data_Provider", dataProviderClass=DataProviders.class)
	public void XP5s_03_Verify_Version_Help_Default_State_SG(Hashtable<String, String> data) throws InterruptedException, IOException, ParseException , AWTException
	{
		APP_LOGS.info("===========SafeGuardTest_03============");
		startAdbLog("SafeGuardTest_03");
		recordVideo("SafeGuardTest_03");
		verify_Version_Help_In_Default_State();
		test.log(LogStatus.PASS, "TestCase Passed");
	}	
	
	@Test(priority=4,dataProvider="Safe_Guard_Data_Provider", dataProviderClass=DataProviders.class)
	public void XP5s_04_Navigate_To_Help_Fetch_Pin(Hashtable<String, String> data) throws InterruptedException, IOException, ParseException , AWTException
	{
		APP_LOGS.info("===========SafeGuardTest_04============");
		startAdbLog("SafeGuardTest_04");
		recordVideo("SafeGuardTest_04");
		navigate_To_Help_Fetch_Pin_Check_Version();
		test.log(LogStatus.PASS, "TestCase Passed");
	}
	
	@Test(priority=5,dataProvider="Safe_Guard_Data_Provider", dataProviderClass=DataProviders.class)
	public void XP5s_05_Verify_Default_States_Of_All_Options_SG(Hashtable<String, String> data) throws InterruptedException, IOException, ParseException , AWTException
	{
		APP_LOGS.info("===========SafeGuardTest_05============");
		startAdbLog("SafeGuardTest_05");
		recordVideo("SafeGuardTest_05");
		clearRecentApps();
		verify_Default_States_Of_All_Options_SG();
		test.log(LogStatus.PASS, "TestCase Passed");
	}
	
	@Test(priority=6,dataProvider="Safe_Guard_Data_Provider", dataProviderClass=DataProviders.class)
	public void XP5s_06_Verify_Default_State_Of_All_Apps_In_App_Option(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException 
	{
		APP_LOGS.info("===========SafeGuardTest_06============");
		startAdbLog("SafeGuardTest_06");
		recordVideo("SafeGuardTest_06");
		verify_Default_State_Of_All_Apps_In_App_Option();		
		test.log(LogStatus.PASS, "TestCase Passed");
	}
	
	@Test(priority=7,dataProvider="Safe_Guard_Data_Provider", dataProviderClass=DataProviders.class)
	public void XP5s_07_Select_All_Apps_And_Check(Hashtable<String, String> data) throws InterruptedException, IOException, ParseException , AWTException
	{
		APP_LOGS.info("===========SafeGuardTest_07============");
		startAdbLog("SafeGuardTest_07");
		recordVideo("SafeGuardTest_07");
		clearRecentApps();
		select_All_Apps_And_Check();
		test.log(LogStatus.PASS, "TestCase Passed");
	}	
	
	@Test(priority=8,dataProvider="Safe_Guard_Data_Provider", dataProviderClass=DataProviders.class)
	public void XP5s_08_Unselect_All_Apps_And_Check(Hashtable<String, String> data) throws InterruptedException, IOException, ParseException , AWTException
	{
		APP_LOGS.info("===========SafeGuardTest_08============");
		startAdbLog("SafeGuardTest_08");
		recordVideo("SafeGuardTest_08");
		clearRecentApps();
		Unselect_All_Apps_And_Check();
		test.log(LogStatus.PASS, "TestCase Passed");
	}
	
	/*@Test(priority=9,dataProvider="Safe_Guard_Data_Provider", dataProviderClass=DataProviders.class)
	public void XP5s_09_Select_FewApps_And_Check(Hashtable<String, String> data) throws InterruptedException, IOException, ParseException , AWTException
	{
		APP_LOGS.info("===========SafeGuardTest_09============");
		startAdbLog("SafeGuardTest_09");
		recordVideo("SafeGuardTest_09");
		select_few_And_Check();
		test.log(LogStatus.PASS, "TestCase Passed");
	}*/
	
	@Test(priority=9,dataProvider="Safe_Guard_Data_Provider", dataProviderClass=DataProviders.class)
	public void XP5s_10_Select_All_In_Feature_Check(Hashtable<String, String> data) throws InterruptedException, IOException, ParseException , AWTException
	{
		APP_LOGS.info("===========SafeGuardTest_10============");
		startAdbLog("SafeGuardTest_10");
		recordVideo("SafeGuardTest_10");
		clearRecentApps();
		check_Features_Tab();
		test.log(LogStatus.PASS, "TestCase Passed");	
    }
	
   @Test(priority=10,dataProvider="Safe_Guard_Data_Provider", dataProviderClass=DataProviders.class)
   public void XP5s_11_Unselect_All_Features_And_Check(Hashtable<String, String> data) throws InterruptedException, IOException, ParseException , AWTException
   {
	   APP_LOGS.info("===========SafeGuardTest_11============");
	   startAdbLog("SafeGuardTest_11");
	   recordVideo("SafeGuardTest_11");
	   clearRecentApps();
	   Unselect_All_Features_And_Check();
	   test.log(LogStatus.PASS, "TestCase Passed");
   }	   

   @Test(priority=11,dataProvider="Safe_Guard_Data_Provider", dataProviderClass=DataProviders.class)
   public void XP5s_12_Application_Pin_TimeOut(Hashtable<String, String> data) throws InterruptedException, IOException, ParseException , AWTException
   {
	  APP_LOGS.info("===========SafeGuardTest_12============");
	  startAdbLog("SafeGuardTest_12");
	  recordVideo("SafeGuardTest_12");
	  clearRecentApps();
	  special_Case_Select();
	  application_Pin_30Sec_TimeOut();
	  test.log(LogStatus.PASS, "TestCase Passed");
   }  
   
   @Test(priority=12,dataProvider="Safe_Guard_Data_Provider", dataProviderClass=DataProviders.class)
   public void XP5s_13_App_Pin_Time_Out_One_Min(Hashtable<String, String> data) throws InterruptedException, IOException, ParseException , AWTException
   {
	   APP_LOGS.info("===========SafeGuardTest_13============");
	   startAdbLog("SafeGuardTest_13");
	   recordVideo("SafeGuardTest_13");
	   clearRecentApps();
	   pin_Time_Out_Set_To_1_Min();
	   test.log(LogStatus.PASS, "TestCase Passed");
   }	
   
   @Test(priority=13,dataProvider="Safe_Guard_Data_Provider", dataProviderClass=DataProviders.class)
   public void XP5s_14_Min_Code_Verification(Hashtable<String, String> data) throws InterruptedException, IOException, ParseException , AWTException
   {
	   APP_LOGS.info("===========SafeGuardTest_14============");
	   startAdbLog("SafeGuardTest_14");
	   recordVideo("SafeGuardTest_14");
	   clearRecentApps();
	   min_Code_Verification();
	   test.log(LogStatus.PASS, "TestCase Passed");
   }	

   @Test(priority=14,dataProvider="Safe_Guard_Data_Provider", dataProviderClass=DataProviders.class)
   public void XP5s_15_Max_Limit(Hashtable<String, String> data) throws IOException, ParseException , AWTException, InterruptedException
   {
	   APP_LOGS.info("===========SafeGuardTest_15============");
	   startAdbLog("SafeGuardTest_15");
	   recordVideo("SafeGuardTest_15");
	   clearRecentApps();
	   max_Limit();
	   test.log(LogStatus.PASS, "TestCase Passed");
   }	

   @Test(priority=15,dataProvider="Safe_Guard_Data_Provider", dataProviderClass=DataProviders.class)
   public void XP5s_16_Chane_Pin(Hashtable<String, String> data) throws InterruptedException, IOException, ParseException , AWTException
   {
	   APP_LOGS.info("===========SafeGuardTest_16============");
	   startAdbLog("SafeGuardTest_16");
	   recordVideo("SafeGuardTest_16");
	   clearRecentApps();
	   change_Pin("1234","4545","4545");
	   change_Pin("4545","1234","1234");
	   test.log(LogStatus.PASS, "TestCase Passed");
   }	   
   
   @Test(priority=16,dataProvider="Safe_Guard_Data_Provider", dataProviderClass=DataProviders.class)
   public void XP5s_17_Check_Invalid_Pin(Hashtable<String, String> data) throws InterruptedException, IOException, ParseException , AWTException
   {
	   APP_LOGS.info("===========SafeGuardTest_17============");
	   startAdbLog("SafeGuardTest_17");
	   recordVideo("SafeGuardTest_17");
	   clearRecentApps();
	   Check_Invalid_Pin();         
	   test.log(LogStatus.PASS, "TestCase Passed");
   }	   
   
   @Test(priority=17,dataProvider="Safe_Guard_Data_Provider", dataProviderClass=DataProviders.class)
   public void XP5s_18_Pin_Acccepts_Only_Num(Hashtable<String, String> data) throws InterruptedException, IOException, ParseException , AWTException
   {
	   APP_LOGS.info("===========SafeGuardTest_18============");
	   startAdbLog("SafeGuardTest_18");
	   recordVideo("SafeGuardTest_18");
	   clearRecentApps();
	   Pin_Acccepts_Only_Num();
	   turn_Off_On_SG();
	   test.log(LogStatus.PASS, "TestCase Passed");
   }	
	
}
