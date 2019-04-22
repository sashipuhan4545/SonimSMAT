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
import com.xp5S.util.FM_Util;
import com.xp5S.util.Locators_BaseUtil;
import com.xp5S.util.Locators_FM;
import com.xp5S.util.Locators_SoundRec;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class FM_Test extends FM_Util{



	public ExcelReader excel;
	Properties properties;
	public static ExtentReports extent;
	public static ExtentTest test;
	public static ExtentTestInterruptedException testexception;
	//	public AndroidDriver<AndroidElement> aDriver;

	@BeforeSuite
	public void beforeSuite() 
	{
		extent = new ExtentReports("src/test/resources/extentreport/FM_TestReport.html", true); //Provide Desired Report Directory Location and Name

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

/*	@AfterMethod
	public void setProgressBar_TestResult() {

		int count =CountIncrementFile.getCount(5);  
		if(count==80) {
			count=count+20;
			CountIncrementFile.putCount(count);

		}
		else {

			ToolFrame.progressBar.setValue(count);
			count=count+5;
			CountIncrementFile.putCount(count);
		}

	}*/

	@AfterMethod()
	public void tearDown(ITestResult result,Method method) throws IOException, ParseException , InterruptedException
	{
		if(result.getStatus()==ITestResult.FAILURE)
		{			

			String screenshot_path=captureScreenshot(method.getName());
			String image= test.addScreenCapture(screenshot_path);
			test.log(LogStatus.FAIL,result.getThrowable());
			test.log(LogStatus.FAIL, "screenshot for failure: "+test.addScreenCapture(image));
			clearRecentApps();
			customWait(2000);
			launchFM();
		}
		extent.endTest(test);
		extent.flush();
	}

	@BeforeTest
	public void setupSys() throws MalformedURLException, InterruptedException, FileNotFoundException, AWTException{
		properties=loadDriverAndProperties();
		Locators_FM loc=new Locators_FM(aDriver);
		Locators_BaseUtil loc1 =new Locators_BaseUtil(aDriver);
		PageFactory.initElements(new AppiumFieldDecorator(getDriver()),loc1);
		PageFactory.initElements(new AppiumFieldDecorator(getDriver()),loc);
		excel=new ExcelReader(ExcelConstants.XP5S_XL_PATH);		
	}


	@Test(priority=1,dataProvider="FMTest", dataProviderClass=DataProviders.class)
	public void Xp5S_FM_01_Validate_Launch_and_Exit_FM(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException 
	{
		APP_LOGS.info("===========Xp5S_FM_01============");
		startAdbLog("Xp5S_FM_01");
		recordVideo("Xp5S_FM_01");
		launchFM();
		validateFMLaunch();
		 
		test.log(LogStatus.PASS, "Test case status is Passed");
	}

	@Test(priority=2,dataProvider="FMTest", dataProviderClass=DataProviders.class)
	public void Xp5S_FM_02_Validate_TurnON_OFF_FM(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException 
	{
		APP_LOGS.info("===========Xp5S_FM_02============");
		startAdbLog("Xp5S_FM_02");
		recordVideo("Xp5S_FM_02");
		customWait(4000);
		launchFM();
		turnOFF_FM();
		validateOFF_FM();
		 
		test.log(LogStatus.PASS, "Test case status is Passed");
	}

	@Test(priority=3,dataProvider="FMTest", dataProviderClass=DataProviders.class)
	public void Xp5S_FM_03_Validate_Start_Stop_Save_Recording(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException 
	{
		APP_LOGS.info("===========Xp5S_FM_03============");
		startAdbLog("FM_Adb");
		recordVideo("Xp5S_FM_03");
		customWait(4000);		
		StartStopRecording();
		validateRecording("FM_Adb");
		 
		test.log(LogStatus.PASS, "Test case status is Passed");
	}

	@Test(priority=4,dataProvider="FMTest", dataProviderClass=DataProviders.class)
	public void Xp5S_FM_04_FM_validate_Sleep_DurationTime_Presence(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException 
	{
		APP_LOGS.info("===========Xp5S_FM_04============");
		startAdbLog("Xp5S_FM_04");
		recordVideo("Xp5S_FM_04");
		customWait(4000);
		sleepTimeSet();
		validatePresenceSleepTimeDurtn();
		 
		test.log(LogStatus.PASS, "Test case status is Passed");
	}

	@Test(priority=5,dataProvider="SonimCareTest", dataProviderClass=DataProviders.class)
	public void Xp5S_FM_05_FM_Verify_SleepTime_Set_and_Cancel(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException 
	{
		APP_LOGS.info("===========Xp5S_FM_05============");
		startAdbLog("FM_Adb");
		recordVideo("Xp5S_FM_05");
		customWait(4000);
		launchFM();
		sleepTimeSet();
		validateSleepSetNCancelSleep("FM_Adb");
		 
		test.log(LogStatus.PASS, "Test case status is Passed");
	}


	@Test(priority=6,dataProvider="SonimCareTest", dataProviderClass=DataProviders.class)
	public void Xp5S_FM_06_Validate_Default_Regional_Band_Country_Target(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException 
	{
		APP_LOGS.info("===========Xp5S_FM_06============");
		startAdbLog("Xp5S_FM_06");
		recordVideo("Xp5S_FM_06");
		customWait(4000);
		selectSetting();
		validateDefaultChannel();
		 
		test.log(LogStatus.PASS, "Test case status is Passed");
	}


	@Test(priority=7,dataProvider="SonimCareTest", dataProviderClass=DataProviders.class)
	public void Xp5S_FM_07_FM_Validate_Restored_to_DefaultSettings(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException 
	{
		APP_LOGS.info("===========Xp5S_FM_07============");
		startAdbLog("Xp5S_FM_07");
		recordVideo("Xp5S_FM_07");
		customWait(4000);
		launchFM();
		selectSetting();
		changeSettingOptn();
		validateResortDefaultSettg();
		 
		test.log(LogStatus.PASS, "Test case status is Passed");
	}

	@Test(priority=8,dataProvider="FMTest", dataProviderClass=DataProviders.class)
	public void Xp5S_FM_08_FM_Validate_Scan_and_SaveChannel(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException 
	{
		APP_LOGS.info("===========Xp5S_FM_08============");
		startAdbLog("Xp5S_FM_08");
		recordVideo("Xp5S_FM_08");
		customWait(4000);
		launchFM();
		validatescanAllStations(); 
		 		
		test.log(LogStatus.PASS, "Test case status is Passed");

	}

	@Test(priority=9,dataProvider="FMTest", dataProviderClass=DataProviders.class)
	public void Xp5S_FM_09_FM_Verify_Replace_SaveChannel(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException 
	{
		APP_LOGS.info("===========Xp5S_FM_09============");
		startAdbLog("Xp5S_FM_09");
		recordVideo("Xp5S_FM_09");
		customWait(4000);
		launchFM();
		validatereplaceChannel();
		 
		test.log(LogStatus.PASS, "Test case status is Passed");	
	}

	@Test(priority=10,dataProvider="FMTest", dataProviderClass=DataProviders.class)
	public void Xp5S_FM_10_FM_Verify_StartStopRecording_LoudSpeaker(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException 
	{
		APP_LOGS.info("===========Xp5S_FM_10============");
		startAdbLog("FM_Adb");
		recordVideo("Xp5S_FM_10");
		customWait(4000);
		launchFM();
		validateStartStopRecLoudSpeaker();		
		 
		customWait(2000);
		test.log(LogStatus.PASS, "Test case status is Passed");
	}


	@Test(priority=11,dataProvider="FMTest", dataProviderClass=DataProviders.class)
	public void Xp5S_FM_11_FM_Verify_ExistingChannel_ADD_SaveChannel(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException 
	{
		APP_LOGS.info("===========Xp5S_FM_11============");
		startAdbLog("FM_Adb");
		recordVideo("Xp5S_FM_11");
		customWait(4000);
		launchFM();
		addExitPersetsChannel();
		validateExistingChannel();
		test.log(LogStatus.PASS, "Test case status is Passed");
	}

	@Test(priority=12,dataProvider="FMTest", dataProviderClass=DataProviders.class)
	public void Xp5S_FM_12_FM_Validate_Remane_Saved_Channel(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException 
	{
		APP_LOGS.info("===========Xp5S_FM_12============");
		startAdbLog("Xp5S_FM_12");
		recordVideo("Xp5S_FM_12");
		customWait(4000);
		launchFM();
		addExitPersetsChannel();
		validateRemaneSavedChannel();
		deleteSavedChannel();
		 
		customWait(2000);
		test.log(LogStatus.PASS, "Test case status is Passed");
	}


	@Test(priority=13,dataProvider="FMTest", dataProviderClass=DataProviders.class)
	public void Xp5S_FM_13_FM_ChangeChannel_Forward_And_Back_Button(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException 
	{
		APP_LOGS.info("===========Xp5S_FM_13============");
		startAdbLog("Xp5S_FM_13");
		recordVideo("Xp5S_FM_13");
		customWait(4000);
		launchFM();
		validateForwardBackwrdStations();
		 
		test.log(LogStatus.PASS, "Test case status is Passed");
	}


	@Test(priority=14,dataProvider="FMTest", dataProviderClass=DataProviders.class)
	public void Xp5S_FM_14_Verify_Push_FM_Background(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException 
	{
		APP_LOGS.info("===========Xp5S_FM_14============");
		startAdbLog("FM_Adb");
		recordVideo("Xp5S_FM_14");
		customWait(4000);
		launchFM();
		pushBackgrdFM();
		validateFMOn("FM_Adb");
		 
		test.log(LogStatus.PASS, "Test case status is Passed");
	}	

	@Test(priority=15,dataProvider="FMTest", dataProviderClass=DataProviders.class)
	public void Xp5S_FM_15_Verify_FM_OpenFM_from_Notification(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException 
	{
		APP_LOGS.info("===========Xp5S_FM_15============");
		startAdbLog("Xp5S_FM_15");
		recordVideo("Xp5S_FM_15");
		customWait(4000);
		launchFM();
		pushBackgrdFM();
		launchNotification();
		validateFMLaunch();
		 
		test.log(LogStatus.PASS, "Test case status is Passed");
	}

	@Test(priority=16,dataProvider="SonimCareTest", dataProviderClass=DataProviders.class)
	public void Xp5S_FM_16_Verify_Headset_Speaker_Enable(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException 
	{
		APP_LOGS.info("===========Xp5S_FM_16============");
		startAdbLog("FM_Adb");
		recordVideo("Xp5S_FM_16");
		customWait(4000);
		launchFM();
		switchToHeadsetSpeakermode();
		validateModes("FM_Adb");
		 
		test.log(LogStatus.PASS, "Test case status is Passed");
	}

	@Test(priority=17,dataProvider="FMTest", dataProviderClass=DataProviders.class)
	public void Xp5S_FM_17_verify_VolumeUp_Down(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException 
	{
		APP_LOGS.info("===========Xp5S_FM_17============");
		startAdbLog("FM_Adb");
		recordVideo("Xp5S_FM_17");
		customWait(4000);
		launchFM();
		volumeUpandDown();
		validateVolumeUpDow("FM_Adb");
		 
		test.log(LogStatus.PASS, "Test case status is Passed");
	}
}
