/*package AllGMSTC;

import java.awt.AWTException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
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
import com.graphics.gui.ToolFrame;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.ExtentTestInterruptedException;
import com.relevantcodes.extentreports.LogStatus;
import com.xp5S.util.Calculator_Util;
import com.xp5S.util.DataProviders;
import com.xp5S.util.ExcelConstants;
import com.xp5S.util.ExcelReader;
import com.xp5S.util.FM_Util;
import com.xp5S.util.Locators_BaseUtil;
import com.xp5S.util.Locators_Calculator;
import com.xp5S.util.Locators_FM;
import com.xp5S.util.Locators_SoundRec;
import com.xp5S.util.SoundRecorder_Util;
import com.xp5S.util.Calculator_Util;


import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class SelectAllPre_bundleApps extends SoundRecorder_Util {
	public ExcelReader excel;
	Properties properties;
	public static ExtentReports extent;
	public static ExtentTest test;
	public static ExtentTestInterruptedException testexception;
	public AndroidDriver<AndroidElement> aDriver;

	@BeforeSuite
	public void beforeSuite() throws IOException 
	{
		extent = new ExtentReports("src/test/resources/extentreport/AllPre_bundle_Apps_TestReport.html", true); 


		
	}

	@BeforeMethod()
	public  void beforeMethod(Method method) throws InterruptedException 	
	{

		test = extent.startTest( (this.getClass().getSimpleName() +" :: "+  method.getName()),method.getName()); //Test Case Start Here
		test.assignAuthor(""); //Test Script Author Name
		extent.loadConfig(new File(System.getProperty("user.dir")+"//ReportsConfig.xml"));
		extent.addSystemInfo("Appium Version", "1.2.7").addSystemInfo(
				"Environment", "TEST");
		
		test.assignCategory("XP5S AllPreBundledApps--Report");
	}
	


	@AfterMethod
	public void setProgressBar_TestResult() throws FileNotFoundException, IOException, ParseException {

		

		int count =CountIncrementFile.getCount(2);  
		if(count==74) {
			count=count+26;
			CountIncrementFile.putCount(count);

		}
		else {
			
			ToolFrame.progressBar.setValue(count);
			count=count+2;
			CountIncrementFile.putCount(count);
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
		Locators_Calculator loc=new Locators_Calculator(aDriver);

		Locators_FM fm=new Locators_FM(aDriver);
		Locators_SoundRec Sr=new Locators_SoundRec(aDriver);
		Locators_BaseUtil loc1 =new Locators_BaseUtil(aDriver);
		PageFactory.initElements(new AppiumFieldDecorator(getDriver()),loc1);	
		//public AndroidDriver<AndroidElement> aDriver;
		PageFactory.initElements(new AppiumFieldDecorator(getDriver()),fm);
		PageFactory.initElements(new AppiumFieldDecorator(getDriver()),Sr);
		PageFactory.initElements(new AppiumFieldDecorator(getDriver()),loc);
		excel=new ExcelReader(ExcelConstants.XP5S_XL_PATH);		
	}


	@Test(priority=1,dataProvider="CalculatorTest", dataProviderClass=DataProviders.class)
	public void Xp5S_Calculator_01_Validate_Calculator_Launch(Hashtable<String, String> data, Method m) throws InterruptedException, AWTException, IOException
	{
		Calculator_Util cal=new Calculator_Util();
		APP_LOGS.info("===========Xp5S_Calculator_01============");
		startAdbLog(" Xp5S_Calculator_01");
		recordVideo(" Xp5S_Calculator_01");		
		cal.launchCalculator();
		cal.validateCalculatorLaunch();
		 
		test.log(LogStatus.PASS, "Test case status is Passed");
	}

	@Test(priority=2,dataProvider="CalculatorTest", dataProviderClass=DataProviders.class)
	public void Xp5S_Calculator_02_Validate_Basic_functions_with_Decimalpt(Hashtable<String, String> data, Method m) throws InterruptedException, AWTException, IOException
	{
		Calculator_Util cal =new Calculator_Util();
		APP_LOGS.info("===========Xp5S_Calculator_02============");
		startAdbLog("Xp5S_Calculator_02");
		recordVideo("Xp5S_Calculator_02");
		cal.launchCalculator();
		cal.validateBasicOperationWithDecimalPnt();
		customWait(4000);
		 
		customWait(2000);
		test.log(LogStatus.PASS, "Test case status is Passed");
	}

	@Test(priority=3,dataProvider="CalculatorTest", dataProviderClass=DataProviders.class)
	public void Xp5S_Calculator_03_Validate_Basic_functions_without_Decimalpt(Hashtable<String, String> data, Method m) throws InterruptedException, AWTException, IOException
	{
		Calculator_Util cal =new Calculator_Util();
		APP_LOGS.info("===========Xp5S_Calculator_03============");
		startAdbLog("Xp5S_Calculator_03");
		recordVideo("Xp5S_Calculator_03");
		cal.launchCalculator();
		cal.validate_BasicOperationWithoutDecimalpt();
		customWait(4000);
		 
		customWait(2000);
		test.log(LogStatus.PASS, "Test case status is Passed");
	}


	@Test(priority=4,dataProvider="CalculatorTest", dataProviderClass=DataProviders.class)
	public void XP5S_Calculator_04_Validate_Functionality_Allkeys_Shortpress(Hashtable<String, String> data, Method m) throws InterruptedException, AWTException, IOException
	{
		Calculator_Util cal =new Calculator_Util();
		APP_LOGS.info("===========XP5S_Calculator_04============");
		startAdbLog("XP5s_Calculator_04");  
		recordVideo("XP5s_Calculator_04");
		cal.launchCalculator();
		cal.validateShortPressNum();
		cal.validateShortPressAdvanceKey();
		customWait(4000);
		 
		customWait(2000);
		test.log(LogStatus.PASS, "Test case status is Passed");		
	}

	@Test(priority=5,dataProvider="CalculatorTest", dataProviderClass=DataProviders.class)
	public void XP5S_Calculator_05_Validate_Functionality_Allnumberkeys_Longpress(Hashtable<String, String> data, Method m) throws InterruptedException, AWTException, IOException
	{
		Calculator_Util cal =new Calculator_Util();
		APP_LOGS.info("===========XP5S_Calculator_05============");
		startAdbLog("XP5s_Calculator_05");
		recordVideo("XP5s_Calculator_05");
		cal.launchCalculator();
		cal.validateLongPress();
		test.log(LogStatus.PASS, "Test case status is Passed");		
	}

	@Test(priority=6,dataProvider="CalculatorTest", dataProviderClass=DataProviders.class)
	public void XP5S_Calculator_06_Validate_Operation_with_Zero(Hashtable<String, String> data, Method m) throws InterruptedException, AWTException, IOException
	{
		Calculator_Util cal =new Calculator_Util();
		APP_LOGS.info("===========XP5S_Calculator_06============");
		startAdbLog("XP5s_Calculator_06");
		recordVideo("XP5s_Calculator_06");
		cal.launchCalculator();
		cal.validate_BasicOperationWithZero();
		test.log(LogStatus.PASS, "Test case status is Passed");		
	}


	@Test(priority=7, dataProvider="CalculatorTest", dataProviderClass=DataProviders.class)
	public void XP5S_Calculator_07_Validate_InfiniteOperation(Hashtable<String, String> data, Method m) throws InterruptedException, AWTException, IOException
	{		
		Calculator_Util cal =new Calculator_Util();
		APP_LOGS.info("===========XP5S_Calculator_07============");
		startAdbLog("XP5s_Calculator_07");
		recordVideo("XP5s_Calculator_07");
		cal.launchCalculator();
		cal.firstclick();
		cal.click_Division();
		cal.click_Number(0);
		cal.click_DPAD_Center();
		cal.validate_InfiniteResult();
		test.log(LogStatus.PASS, "Test case status is Pass");	
	}	

	@Test(priority=8, dataProvider="CalculatorTest", dataProviderClass=DataProviders.class)
	public void XP5S_Calculator_08_Validate_PowerOperation(Hashtable<String, String> data, Method m) throws InterruptedException, AWTException, IOException
	{		
		Calculator_Util cal =new Calculator_Util();
		APP_LOGS.info("===========XP5S_Calculator_08============");
		startAdbLog("XP5s_Calculator_08");
		recordVideo("XP5s_Calculator_08");
		cal.launchCalculator();
		cal.navigateTo_AdvancePanel();
		cal.numbers_PowerOperation();
		cal.clickOn_Equal();
		cal.validate_PowerOperation();
		test.log(LogStatus.PASS, "Test case status is Pass");	
	}	

	@Test(priority=9, dataProvider="CalculatorTest", dataProviderClass=DataProviders.class)
	public void XP5S_Calculator_09_Validate_Pie_Value(Hashtable<String, String> data, Method m) throws InterruptedException, AWTException, IOException
	{		
		Calculator_Util cal =new Calculator_Util();
		APP_LOGS.info("===========XP5S_Calculator_09============");
		startAdbLog("XP5s_Calculator_09");
		recordVideo("XP5s_Calculator_09");
		cal.launchCalculator();
		cal.navigateTo_AdvancePanel();
		cal.clickBtn(Locators_Calculator.pie);
		cal.clickOn_Equal();	
		cal.validate_PieValue();		
		test.log(LogStatus.PASS, "Test case status is Pass");	
	}

	@Test(priority=10, dataProvider="CalculatorTest", dataProviderClass=DataProviders.class)
	public void XP5S_Calculator_10_Validate_ExponentialValue(Hashtable<String, String> data, Method m) throws InterruptedException, AWTException, IOException
	{		
		Calculator_Util cal =new Calculator_Util();
		APP_LOGS.info("===========XP5s_Calculator_10============");
		startAdbLog("XP5s_Calculator_10");
		recordVideo("XP5s_Calculator_10");
		cal.launchCalculator();
		cal.navigateTo_AdvancePanel();
		cal.clickBtn(Locators_Calculator.exponential);
		cal.clickOn_Equal();
		cal.validate_ExponentialValue();
		test.log(LogStatus.PASS, "Test case status is Pass");	
	}

	@Test(priority=11,dataProvider="FMTest", dataProviderClass=DataProviders.class)
	public void Xp5S_FM_01_Validate_Launch_and_Exit_FM(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{

		APP_LOGS.info("===========Xp5S_FM_01============");
		startAdbLog("Xp5S_FM_01");
		recordVideo("Xp5S_FM_01");
		clearRecentApps();
		customWait(4000);
		FM_Util fmutil=new FM_Util();
		fmutil.launchFM();
		fmutil.validateFMLaunch();
		test.log(LogStatus.PASS, "Test case status is Passed");
	}

	@Test(priority=12,dataProvider="FMTest", dataProviderClass=DataProviders.class)
	public void Xp5S_FM_02_Validate_TurnON_OFF_FM(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		FM_Util fmutil=new FM_Util();
		APP_LOGS.info("===========Xp5S_FM_02============");
		startAdbLog("Xp5S_FM_02");
		recordVideo("Xp5S_FM_02");
		clearRecentApps();
		customWait(4000);
		fmutil.launchFM();
		fmutil.turnOFF_FM();
		fmutil.validateOFF_FM();
		test.log(LogStatus.PASS, "Test case status is Passed");
	}

	@Test(priority=13,dataProvider="FMTest", dataProviderClass=DataProviders.class)
	public void Xp5S_FM_03_Validate_Start_Stop_Save_Recording(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		FM_Util fmutil=new FM_Util();
		APP_LOGS.info("===========Xp5S_FM_03============");
		startAdbLog("FM_Adb");
		recordVideo("Xp5S_FM_03");
		clearRecentApps();
		customWait(4000);
		fmutil.launchFM();
		fmutil.StartStopRecording();
		fmutil.	validateRecording("FM_Adb");
		test.log(LogStatus.PASS, "Test case status is Passed");
	}

	@Test(priority=14,dataProvider="FMTest", dataProviderClass=DataProviders.class)
	public void Xp5S_FM_04_FM_validate_Sleep_DurationTime_Presence(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		FM_Util fmutil=new FM_Util();
		APP_LOGS.info("===========Xp5S_FM_04============");
		startAdbLog("Xp5S_FM_04");
		recordVideo("Xp5S_FM_04");
		clearRecentApps();
		customWait(4000);
		fmutil.launchFM();
		fmutil.sleepTimeSet();
		fmutil.validatePresenceSleepTimeDurtn();
		test.log(LogStatus.PASS, "Test case status is Passed");
	}

	@Test(priority=15,dataProvider="FMTest", dataProviderClass=DataProviders.class)
	public void Xp5S_FM_05_FM_Verify_SleepTime_Set_and_Cancel(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		FM_Util fmutil=new FM_Util();
		APP_LOGS.info("===========Xp5S_FM_05============");
		startAdbLog("FM_Adb");
		recordVideo("Xp5S_FM_05");
		clearRecentApps();
		customWait(4000);
		fmutil.launchFM();
		fmutil.sleepTimeSet();
		fmutil.validateSleepSetNCancelSleep("FM_Adb");
		test.log(LogStatus.PASS, "Test case status is Passed");
	}


	@Test(priority=16,dataProvider="FMTest", dataProviderClass=DataProviders.class)
	public void Xp5S_FM_06_Validate_Default_Regional_Band_Country_Target(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		FM_Util fmutil=new FM_Util();
		APP_LOGS.info("===========Xp5S_FM_06============");
		startAdbLog("Xp5S_FM_06");
		recordVideo("Xp5S_FM_06");
		clearRecentApps();
		customWait(4000);
		fmutil.launchFM();
		fmutil.selectSetting();
		fmutil.validateDefaultChannel();
		 
		customWait(2000);
		test.log(LogStatus.PASS, "Test case status is Passed");
	}	


	@Test(priority=17,dataProvider="FMTest", dataProviderClass=DataProviders.class)
	public void Xp5S_FM_07_FM_Validate_Restored_to_DefaultSettings(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		FM_Util fmutil=new FM_Util();
		APP_LOGS.info("===========Xp5S_FM_07============");
		startAdbLog("Xp5S_FM_07");
		recordVideo("Xp5S_FM_07");
		clearRecentApps();
		customWait(4000);
		fmutil.launchFM();
		fmutil.selectSetting();
		fmutil.changeSettingOptn();
		fmutil.validateResortDefaultSettg();
		test.log(LogStatus.PASS, "Test case status is Passed");
	}

	@Test(priority=18,dataProvider="FMTest", dataProviderClass=DataProviders.class)
	public void Xp5S_FM_08_FM_Validate_Scan_and_SaveChannel(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		FM_Util fmutil=new FM_Util();
		APP_LOGS.info("===========Xp5S_FM_08============");
		startAdbLog("Xp5S_FM_08");
		recordVideo("Xp5S_FM_08");
		clearRecentApps();
		customWait(4000);
		fmutil.launchFM();
		fmutil.validatescanAllStations();
		test.log(LogStatus.PASS, "Test case status is Passed");
	}

	@Test(priority=19,dataProvider="FMTest", dataProviderClass=DataProviders.class)
	public void Xp5S_FM_09_FM_Verify_Replace_SaveChannel(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		FM_Util fmutil=new FM_Util();
		APP_LOGS.info("===========Xp5S_FM_09============");
		startAdbLog("Xp5S_FM_09");
		recordVideo("Xp5S_FM_09");
		clearRecentApps();
		customWait(4000);
		fmutil.launchFM();
		fmutil.validatereplaceChannel();
		test.log(LogStatus.PASS, "Test case status is Passed");
	}


	@Test(priority=20,dataProvider="FMTest", dataProviderClass=DataProviders.class)
	public void Xp5S_FM_10_FM_Verify_StartStopRecording_LoudSpeaker(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		FM_Util fmutil=new FM_Util();
		APP_LOGS.info("===========Xp5S_FM_10============");
		startAdbLog("Xp5S_FM_10");
		recordVideo("Xp5S_FM_10");
		clearRecentApps();
		customWait(4000);
		fmutil.launchFM();
		fmutil.validateStartStopRecLoudSpeaker();	
		test.log(LogStatus.PASS, "Test case status is Passed");
	}


	@Test(priority=21,dataProvider="FMTest", dataProviderClass=DataProviders.class)
	public void Xp5S_FM_11_FM_Verify_ExistingChannel_ADD_SaveChannel(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		FM_Util fmutil=new FM_Util();
		APP_LOGS.info("===========Xp5S_FM_11============");
		startAdbLog("Xp5S_FM_11");
		recordVideo("Xp5S_FM_11");
		clearRecentApps();
		customWait(4000);
		fmutil.launchFM();
		fmutil.addExitPersetsChannel();
		fmutil.validateExistingChannel();
		test.log(LogStatus.PASS, "Test case status is Passed");
	}

	@Test(priority=22,dataProvider="FMTest", dataProviderClass=DataProviders.class)
	public void Xp5S_FM_12_FM_Validate_Remane_Saved_Channel(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		FM_Util fmutil=new FM_Util();
		APP_LOGS.info("===========Xp5S_FM_12============");
		startAdbLog("Xp5S_FM_12");
		recordVideo("Xp5S_FM_12");
		clearRecentApps();
		customWait(4000);
		fmutil.launchFM();
		fmutil.addExitPersetsChannel();
		fmutil.validateRemaneSavedChannel();
		fmutil.deleteSavedChannel();
		test.log(LogStatus.PASS, "Test case status is Passed");
	}


	@Test(priority=23,dataProvider="FMTest", dataProviderClass=DataProviders.class)
	public void Xp5S_FM_13_FM_ChangeChannel_Forward_And_Back_Button(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		FM_Util fmutil=new FM_Util();
		APP_LOGS.info("===========Xp5S_FM_13============");
		startAdbLog("Xp5S_FM_13");
		recordVideo("Xp5S_FM_13");
		clearRecentApps();
		customWait(4000);
		fmutil.launchFM();
		fmutil.validateForwardBackwrdStations();
		test.log(LogStatus.PASS, "Test case status is Passed");
	}


	@Test(priority=24,dataProvider="FMTest", dataProviderClass=DataProviders.class)
	public void Xp5S_FM_14_Verify_Push_FM_Background(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{

		FM_Util fmutil=new FM_Util();
		APP_LOGS.info("===========Xp5S_FM_14============");
		startAdbLog("FM_Adb");
		recordVideo("Xp5S_FM_14");
		clearRecentApps();
		customWait(4000);
		fmutil.launchFM();
		fmutil.pushBackgrdFM();
		fmutil.validateFMOn("FM_Adb");
		test.log(LogStatus.PASS, "Test case status is Passed");
	}	

	@Test(priority=25,dataProvider="FMTest", dataProviderClass=DataProviders.class)
	public void Xp5S_FM_15_Verify_FM_OpenFM_from_Notification(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		FM_Util fmutil=new FM_Util();
		APP_LOGS.info("===========Xp5S_FM_15============");
		startAdbLog("Xp5S_FM_15");
		recordVideo("Xp5S_FM_15");
		clearRecentApps();
		customWait(4000);
		fmutil.launchFM();
		fmutil.pushBackgrdFM();
		fmutil.launchNotification();
		fmutil.validateFMLaunch();
		test.log(LogStatus.PASS, "Test case status is Passed");
	}

	@Test(priority=26,dataProvider="FMTest", dataProviderClass=DataProviders.class)
	public void Xp5S_FM_16_Verify_Headset_Speaker_Enable(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		FM_Util fmutil=new FM_Util();
		APP_LOGS.info("===========Xp5S_FM_16============");
		startAdbLog("FM_Adb");
		recordVideo("Xp5S_FM_16");
		clearRecentApps();
		customWait(4000);
		fmutil.launchFM();
		fmutil.switchToHeadsetSpeakermode();
		fmutil.validateModes("FM_Adb");
		test.log(LogStatus.PASS, "Test case status is Passed");
	}

	@Test(priority=27,dataProvider="FMTest", dataProviderClass=DataProviders.class)
	public void Xp5S_FM_17_verify_VolumeUp_Down(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		FM_Util fmutil=new FM_Util();
		APP_LOGS.info("===========Xp5S_FM_17============");
		startAdbLog("FM_Adb");
		recordVideo("Xp5S_FM_17");
		clearRecentApps();
		customWait(4000);
		fmutil.launchFM();
		fmutil.volumeUpandDown();
		fmutil.validateVolumeUpDow("FM_Adb");	
		test.log(LogStatus.PASS, "Test case status is Passed");
	}

	@Test(priority=28,dataProvider="SoundRec", dataProviderClass=DataProviders.class)
	public void Xp5S_SR_01_Launch_and_Exit_SoundRecorder(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{

		APP_LOGS.info("===========Xp5S_SoundRecorder_01============");
		startAdbLog("Xp5S_SoundRecorder_01");
		recordVideo("Xp5S_SoundRecorder_01");
		clearRecentApps();
		launchSounchRec();
		validateSoundRecorderLaunch();
		customWait(4000);
		test.log(LogStatus.PASS, "Test case status is Passed");
	}

	@Test(priority=29,dataProvider="SoundRec", dataProviderClass=DataProviders.class)
	public void Xp5S_SR_02_Launch_and_Select_ListOpt_Sounds(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{

		APP_LOGS.info("===========Xp5S_SoundRecorder_02============");
		startAdbLog("Xp5S_SoundRecorder_02");
		recordVideo("Xp5S_SoundRecorder_02");
		clearRecentApps();
		customWait(4000);
		launchSounchRec();
		selectListOptn();
		validateListPage();
		customWait(4000);
		test.log(LogStatus.PASS, "Test case status is Passed");
	}


	@Test(priority=30,dataProvider="SoundRec", dataProviderClass=DataProviders.class)
	public void Xp5S_SR_03_Record_and_Save_Sound(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{

		APP_LOGS.info("===========Xp5S_SoundRecorder_03============");
		startAdbLog("Xp5S_SoundRecorder_03");
		recordVideo("Xp5S_SoundRecorder_03");
		clearRecentApps();
		customWait(4000);
		launchSounchRec();
		recordSound();
		validateRecordedSoundSaved();
		deleteSavedRecorder();
		test.log(LogStatus.PASS, "Test case status is Passed");
	}


	@Test(priority=31,dataProvider="SoundRec", dataProviderClass=DataProviders.class)
	public void Xp5S_SR_04_Record_and_Discard_Sound(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{

		APP_LOGS.info("===========Xp5S_SoundRecorder_04============");
		startAdbLog("Xp5S_SoundRecorder_04");
		recordVideo("Xp5S_SoundRecorder_04");
		clearRecentApps();
		customWait(4000);
		launchSounchRec();
		recordSound();
		DiscardSound();
		validateRecordedSoundDiscarded();
		test.log(LogStatus.PASS, "Test case status is Passed");
	}

	@Test(priority=32,dataProvider="SoundRec", dataProviderClass=DataProviders.class)
	public void Xp5S_SR_05_Record_and_Delete_Sound(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{

		APP_LOGS.info("===========Xp5S_SoundRecorder_05============");
		startAdbLog("Xp5S_SoundRecorder_05");
		recordVideo("Xp5S_SoundRecorder_05");
		clearRecentApps();
		customWait(4000);
		launchSounchRec();
		recordSound();
		SaveSoundRecord();
		deleteSavedRecorder();
		validateSoundRecordDeletion();
		test.log(LogStatus.PASS, "Test case status is Passed");
	}

	@Test(priority=33,dataProvider="SoundRec", dataProviderClass=DataProviders.class)
	public void Xp5S_SR_06_Record_and_Edit_Sound(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{

		APP_LOGS.info("===========Xp5S_SoundRecorder_06============");
		startAdbLog("Xp5S_SoundRecorder_06");
		recordVideo("Xp5S_SoundRecorder_06");
		clearRecentApps();
		customWait(4000);
		launchSounchRec();
		recordSound();
		SaveSoundRecord();
		editSoundRecordName();
		validateSoundRecordEdit();
		deleteSavedRecorder();
		test.log(LogStatus.PASS, "Test case status is Passed");
	}

	@Test(priority=34,dataProvider="SoundRec", dataProviderClass=DataProviders.class)
	public void Xp5S_SR_07_DeleteMutiple_Sound(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{

		APP_LOGS.info("===========Xp5S_SoundRecorder_07============");
		startAdbLog("Xp5S_SoundRecorder_07");
		recordVideo("Xp5S_SoundRecorder_07");
		clearRecentApps();
		customWait(4000);
		launchSounchRec();
		addMultipleSoundRec();
		deleteMulSoundRec(); 
		validateMulDeleteSoundRec();
		test.log(LogStatus.PASS, "Test case status is Passed");
	}


	@Test(priority=35,dataProvider="SoundRec", dataProviderClass=DataProviders.class)
	public void Xp5S_SR_08_Pause_and_Resume_SoundRecording(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{

		APP_LOGS.info("===========Xp5S_SoundRecorder_08============");
		startAdbLog("Xp5S_SoundRecorder_08");
		recordVideo("Xp5S_SoundRecorder_08");
		clearRecentApps();
		customWait(4000);
		launchSounchRec();
		ValidatePauseAndResume();
		test.log(LogStatus.PASS, "Test case status is Passed");
	}

	@Test(priority=36,dataProvider="SoundRec", dataProviderClass=DataProviders.class)
	public void Xp5S_SR_09_Selectall_and_DeselectAll_Sounds(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{

		APP_LOGS.info("===========Xp5S_SoundRecorder_09============");
		startAdbLog("Xp5S_SoundRecorder_09");
		recordVideo("Xp5S_SoundRecorder_09");
		clearRecentApps();
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


	@Test(priority=37,dataProvider="SoundRec", dataProviderClass=DataProviders.class)
	public void Xp5S_SR_10_Select_and_Play_SoundRecordingList(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{

		APP_LOGS.info("===========Xp5S_SoundRecorder_10============");
		startAdbLog("Xp5S_SoundRecorder_10");
		recordVideo("Xp5S_SoundRecorder_10");
		clearRecentApps();
		customWait(4000);
		launchSounchRec();
		recordSound();
		playSoundRecord();
		validateRecSoundPlay();
		deleteSavedRecorder();
		test.log(LogStatus.PASS, "Test case status is Passed");
	}


	@Test(priority=38,dataProvider="SoundRec", dataProviderClass=DataProviders.class)
	public void Xp5S_SR_11_SoundRec_In_SilentMode(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{

		APP_LOGS.info("===========Xp5S_SoundRecorder_11============");
		startAdbLog("Xp5S_SoundRecorder_11");
		recordVideo("Xp5S_SoundRecorder_11");
		clearRecentApps();
		customWait(4000);
		launchSounchRec();
		deviceSilentMode();
		validateSoundRecording();
		test.log(LogStatus.PASS, "Test case status is Passed");
	}

}
*/