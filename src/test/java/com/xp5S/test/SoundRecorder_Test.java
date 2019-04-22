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
import com.xp5S.util.Locators_Calculator;
import com.xp5S.util.Locators_SoundRec;
import com.xp5S.util.SoundRecorder_Util;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class SoundRecorder_Test extends SoundRecorder_Util {


	public ExcelReader excel;
	Properties properties;
	public static ExtentReports extent;
	public static ExtentTest test;
	public static ExtentTestInterruptedException testexception;
	//public AndroidDriver<AndroidElement> aDriver;

	@BeforeSuite
	public void beforeSuite()
	{
		extent = new ExtentReports("src/test/resources/extentreport/SoundRecorderTestReport.html", true); //Provide Desired Report Directory Location and Name

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

	/*@AfterMethod()
	public void setProgressBar_TestResult()
	{
		int count =CountIncrementFile.getCount(9);  
		if(count==90) {
			count=count+10;
			CountIncrementFile.putCount(count);

		}
		else {

			ToolFrame.progressBar.setValue(count);
			count=count+9;
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
			launchSounchRec();

		}
		extent.endTest(test);
		extent.flush();
	}

	@BeforeTest
	public void setupSys() throws MalformedURLException, InterruptedException, FileNotFoundException, AWTException{
		properties=loadDriverAndProperties();
		Locators_SoundRec loc=new Locators_SoundRec(aDriver);
		Locators_BaseUtil loc1 =new Locators_BaseUtil(aDriver);
		PageFactory.initElements(new AppiumFieldDecorator(getDriver()),loc1);
		PageFactory.initElements(new AppiumFieldDecorator(getDriver()),loc);
		excel=new ExcelReader(ExcelConstants.XP5S_XL_PATH);		
	}

	@Test(priority=1,dataProvider="SoundRec", dataProviderClass=DataProviders.class)
	public void Xp5S_SR_01_Launch_and_Exit_SoundRecorder(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException 
	{		
		APP_LOGS.info("===========Xp5S_SoundRecorder_01============");
		startAdbLog("Xp5S_SoundRecorder_01");
		recordVideo("Xp5S_SoundRecorder_01");
		launchSounchRec();
		validateSoundRecorderLaunch();



		test.log(LogStatus.PASS, "Test case status is Passed");
	}

	@Test(priority=2,dataProvider="SoundRec", dataProviderClass=DataProviders.class)
	public void Xp5S_SR_02_Launch_and_Select_ListOpt_Sounds(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException 
	{		
		APP_LOGS.info("===========Xp5S_SoundRecorder_02============");
		startAdbLog("Xp5S_SoundRecorder_02");
		recordVideo("Xp5S_SoundRecorder_02");
		customWait(4000);
		launchSounchRec();
		selectListOptn();
		validateListPage();
		customWait(4000);		  
		test.log(LogStatus.PASS, "Test case status is Passed");
	}

	@Test(priority=3,dataProvider="SoundRec", dataProviderClass=DataProviders.class)
	public void Xp5S_SR_03_Record_and_Save_Sound(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException 
	{		
		APP_LOGS.info("===========Xp5S_SoundRecorder_03============");
		startAdbLog("Xp5S_SoundRecorder_03");
		recordVideo("Xp5S_SoundRecorder_03");
		customWait(4000);
		launchSounchRec();
		recordSound();
		validateRecordedSoundSaved();
		test.log(LogStatus.PASS, "Test case status is Passed");	
		deleteSavedRecorder();		  
		test.log(LogStatus.PASS, "Test case status is Passed");
	}	

	@Test(priority=4,dataProvider="SoundRec", dataProviderClass=DataProviders.class)
	public void Xp5S_SR_04_Record_and_Discard_Sound(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException 
	{		
		APP_LOGS.info("===========Xp5S_SoundRecorder_04============");
		startAdbLog("Xp5S_SoundRecorder_04");
		recordVideo("Xp5S_SoundRecorder_04");
		customWait(4000);
		launchSounchRec();
		recordSound();
		DiscardSound();
		validateRecordedSoundDiscarded();		  
		test.log(LogStatus.PASS, "Test case status is Passed");
	}

	@Test(priority=5,dataProvider="SoundRec", dataProviderClass=DataProviders.class)
	public void Xp5S_SR_05_Record_and_Delete_Sound(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException 
	{		
		APP_LOGS.info("===========Xp5S_SoundRecorder_05============");
		startAdbLog("Xp5S_SoundRecorder_05");
		recordVideo("Xp5S_SoundRecorder_05");
		customWait(4000);
		launchSounchRec();
		recordSound();
		SaveSoundRecord();
		deleteSavedRecorder();
		validateSoundRecordDeletion();		  
		test.log(LogStatus.PASS, "Test case status is Passed");
	}

	@Test(priority=6,dataProvider="SoundRec", dataProviderClass=DataProviders.class)
	public void Xp5S_SR_06_Record_and_Edit_Sound(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException 
	{		
		APP_LOGS.info("===========Xp5S_SoundRecorder_006============");
		startAdbLog("Xp5S_SoundRecorder_06");
		recordVideo("Xp5S_SoundRecorder_06");
		customWait(4000);
		launchSounchRec();
		recordSound();
		SaveSoundRecord();
		editSoundRecordName();
		validateSoundRecordEdit();
		test.log(LogStatus.PASS, "Test case status is Passed");
		deleteSavedRecorder();
	}

	@Test(priority=7,dataProvider="SoundRec", dataProviderClass=DataProviders.class)
	public void Xp5S_SR_07_DeleteMutiple_Sound(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException 
	{		
		APP_LOGS.info("===========Xp5S_SoundRecorder_07============");
		startAdbLog("Xp5S_SoundRecorder_07");
		recordVideo("Xp5S_SoundRecorder_07");
		customWait(4000);
		launchSounchRec();
		addMultipleSoundRec();
		deleteMulSoundRec(); 
		validateMulDeleteSoundRec();
		test.log(LogStatus.PASS, "Test case status is Passed");
	}	

	@Test(priority=8,dataProvider="SoundRec", dataProviderClass=DataProviders.class)
	public void Xp5S_SR_08_Pause_and_Resume_SoundRecording(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException 
	{		
		APP_LOGS.info("===========Xp5S_SoundRecorder_08============");
		startAdbLog("Xp5S_SoundRecorder_08");
		recordVideo("Xp5S_SoundRecorder_08");
		customWait(4000);
		launchSounchRec();
		ValidatePauseAndResume();
		test.log(LogStatus.PASS, "Test case status is Passed");
	}

	@Test(priority=9,dataProvider="SoundRec", dataProviderClass=DataProviders.class)
	public void Xp5S_SR_09_Selectall_and_DeselectAll_Sounds(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException 
	{		
		APP_LOGS.info("===========Xp5S_SoundRecorder_09============");
		startAdbLog("Xp5S_SoundRecorder_09");
		recordVideo("Xp5S_SoundRecorder_09");
		customWait(4000);
		launchSounchRec();
		addMultipleSoundRec();
		selectAllSoundRec();
		validateSelectAll();
		deslectAllSoundRec();
		validateDeselectAll();
		deleteMulSoundRec();
		test.log(LogStatus.PASS, "Test case status is Passed");
	}	

	@Test(priority=10,dataProvider="SoundRec", dataProviderClass=DataProviders.class)
	public void Xp5S_SR_10_Select_and_Play_SoundRecordingList(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException 
	{	
		APP_LOGS.info("===========Xp5S_SoundRecorder_10============");
		startAdbLog("Xp5S_SoundRecorder_10");
		recordVideo("Xp5S_SoundRecorder_10");
		customWait(4000);
		launchSounchRec();
		recordSound();
		playSoundRecord();
		validateRecSoundPlay();
		deleteSavedRecorder();
		test.log(LogStatus.PASS, "Test case status is Passed");
	}	

	@Test(priority=11,dataProvider="SoundRec", dataProviderClass=DataProviders.class)
	public void Xp5S_SR_11_SoundRec_In_SilentMode(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException 
	{		
		APP_LOGS.info("===========Xp5S_SoundRecorder_11============");
		startAdbLog("Xp5S_SoundRecorder_11");
		recordVideo("Xp5S_SoundRecorder_11");
		customWait(4000);
		launchSounchRec();
		deviceSilentMode();
		//		selectRecordBtn();
		validateSoundRecording();
		test.log(LogStatus.PASS, "Test case status is Passed");
	}
}
