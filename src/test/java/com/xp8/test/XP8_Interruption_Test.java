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
import org.junit.Ignore;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.graphics.gui.JsonFileReaderAndWriter;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTestInterruptedException;
import com.relevantcodes.extentreports.LogStatus;
import com.xp5S.util.GetMethods;

import com.xp5S.util.appiumService;
import com.xp8.util.DataProviders;
import com.xp8.util.ExcelConstants;
import com.xp8.util.ExcelReader;
import com.xp8.util.Locators_BaseUtil;
import com.xp8.util.Locators_Stability;
import com.xp8.util.Locators_XP8_CallHistory;
import com.xp8.util.XP8_Interruption_Util;

import OCR.Read_File;
import OCR.my_main;

import io.appium.java_client.android.AndroidKeyCode;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

public class XP8_Interruption_Test extends XP8_Interruption_Util{
	public ExcelReader excel;
	Properties properties;
	private Process a;
	public static ExtentTestInterruptedException testexception;



	@BeforeSuite
	public void beforeSuite() throws FileNotFoundException, InterruptedException,  IOException, ParseException, ParseException, ParseException, ParseException, ParseException, ParseException 
	{
		extent = new ExtentReports("src/test/resources/extentreport/XP8_Interruption_TestReport.html", true); //Provide Desired Report Directory Location and Name
		extent.loadConfig(new File("src/test/resources/StorageFile/ReportsConfig.xml"));
		extent.addSystemInfo("Build #",JsonFileReaderAndWriter.primaryDevFirmwareReader())
		.addSystemInfo("Product",JsonFileReaderAndWriter.primaryDevModelReader())
		.addSystemInfo("Operator", JsonFileReaderAndWriter.primaryDevOperatorReader());		
		fetch_Devices_Details();	

	}

	@BeforeSuite
	public void numofTestCases() throws ClassNotFoundException, IOException, InterruptedException {


		//int numberOfTestCases = GetMethods.TotalTestcase("XP8_TC", this.getClass());

		appiumService.TOTAL_NUM_OF_TESTCASES=GetMethods.TotalTestcase("XP8_TC", this.getClass());


	}


	@BeforeMethod()
	public  void beforeMethod(Method method) 
	{
		test = extent.startTest( (this.getClass().getSimpleName() +" :: "+  method.getName()),method.getName()); //Test Case Start Here
		//test.assignAuthor("JK"); //Test Script Author Name
		System.out.println("Before Method");
	}


	@BeforeClass
	public void copyFilesToDevice() throws  IOException, ParseException, ParseException, ParseException, ParseException, ParseException, ParseException  {
		System.out.println("Executing clear log screen");
		System.out.println("Before Class");
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
	public void tearDown(ITestResult result,Method method) throws  IOException, ParseException, ParseException, ParseException, ParseException, ParseException, ParseException , InterruptedException
	{
		if(result.getStatus()==ITestResult.FAILURE)
		{

			String screenshot_path=captureScreenshot(method.getName());
			String image= test.addScreenCapture(screenshot_path);		
			test.log(LogStatus.FAIL,result.getThrowable());	
			clearRecentApps();
		}
		extent.endTest(test);
		extent.flush();
	}


	@BeforeTest
	public void setupSys() throws MalformedURLException, InterruptedException, FileNotFoundException, AWTException{
		properties=loadDriverAndProperties();
		Locators_XP8_CallHistory loc=new Locators_XP8_CallHistory(aDriver);
		Locators_BaseUtil loc1 = new Locators_BaseUtil(aDriver);	
		PageFactory.initElements(new AppiumFieldDecorator(aDriver),loc);
		PageFactory.initElements(new AppiumFieldDecorator(aDriver),loc1);
		excel=new ExcelReader(ExcelConstants.XP5S_XL_PATH);	
	}

	/*@AfterTest
	public void clearContactsAndCallHistory(SoftAssert SA) throws InterruptedException, IOException{
		deleteContacts(SA);
		launch_an_app("phone");
		validateClearCallHistory(SA);
		aDriver.pressKeyCode(AndroidKeyCode.HOME);
	}
	 */

	//=========================================================Test Scripts================================================================

	@Test(priority=0,dataProvider="XP8_Interruption", dataProviderClass=DataProviders.class)
	public void XP8_TC_00_Pre_Conditions(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException
	{
		SoftAssert SA = new SoftAssert();
		SoftAssert sa = new SoftAssert();
		APP_LOGS.info("===========XP8_Interruption_PRE============");
		clearRecentApps();
		lockscreen();
		Pre_Condition_Set_Sleeptime_30min();
		Runtime.getRuntime().exec("adb -s "+p_Id+" shell am start -a android.settings.AIRPLANE_MODE_SETTINGS");
		checkairplanemode(sa);
		Runtime.getRuntime().exec("adb -s "+p_Id+" install .\\src\\test\\resources\\StorageFile\\XP8memoryfill.apk");
		preconditionMemoryStorageCheck(SA);
		//=====================Adding Contacts==================================//
		deleteContacts(pryNum, SA);
		aDriver.pressKeyCode(AndroidKeyCode.HOME);
		deleteContacts(refNum, SA);
		aDriver.pressKeyCode(AndroidKeyCode.HOME);
		launch_an_app("phone");
		validateClearCallHistory(SA);
		clickBackButton(1);
		minWait();
		enterNumberInDialpad(pryNum, SA);
		clickBackButton(2);
		aDriver.pressKeyCode(AndroidKeyCode.HOME);
		launch_an_app("phone");
		minWait();
		enterNumberInDialpad(refNum, SA);
		clickBackButton(2);
		aDriver.pressKeyCode(AndroidKeyCode.HOME);
		make_Call_from_RefDev();
		customWait(10000);
		endCall_RefDevice();
	}

	@Test(priority=100,dataProvider="XP8_Interruption", dataProviderClass=DataProviders.class)
	public void XP8_TC_100_Post_Conditions(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException
	{
		APP_LOGS.info("===========XP8_Interruption_POST============");
		SoftAssert SA = new SoftAssert();
		clearRecentApps();
		postconditionMemoryStorageCheck();
		minWait();
		Post_Condition_Set_Sleeptime_30sec();
		aDriver.pressKeyCode(AndroidKeyCode.HOME);
		deleteSMSInPostCondition();
		aDriver.pressKeyCode(AndroidKeyCode.HOME);
		deleteContacts(pryNum, SA);
		aDriver.pressKeyCode(AndroidKeyCode.HOME);
		deleteContacts(refNum, SA);
		aDriver.pressKeyCode(AndroidKeyCode.HOME);
		launch_an_app("phone");
		validateClearCallHistory(SA);
		clickBackButton(3);
		SA.assertAll();
	}

	@Test(priority=1,dataProvider="XP8_Interruption", dataProviderClass=DataProviders.class)
	public void XP8_TC_01_Validate_Call_Interruptions(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException, TesseractException
	{
		APP_LOGS.info("===========XP8_Interruption_01============");
		SoftAssert SA = new SoftAssert();
		clearRecentApps();
		Runtime.getRuntime().exec("adb -s "+p_Id+" shell pm clear com.google.android.deskclock");
		validateCallInterruption();
		minWait();
		String cmd1 = "adb -s "+r_Id+" shell service call isms 7 i32 0 s16 \"com.android.mms.service\" s16 "+pryNum+" s16 \"null\" s16 \"HiTest\" s16 \"null\" s16 \"null\"";
		Runtime.getRuntime().exec(cmd1);
		customWait(2000);
		recieveSMSFromReferenceDevice();
		addAlarm();
		timerExpire();
		endCall_RefDevice();
		//validateCallDetails(SA);
		SA.assertAll();
	}

	@Test(priority=2,dataProvider="XP8_Interruption", dataProviderClass=DataProviders.class)
	public void XP8_TC_02_Validate_SMS_Interruptions(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException
	{
		APP_LOGS.info("===========XP8_Interruption_02============");
		SoftAssert SA = new SoftAssert();
		clearRecentApps();
		launch_an_app("messaging");
		minWait();
		clickBtn(Locators_XP8_CallHistory.contact_two);
		minWait();
		validateCallInterruption();
		customWait(10000);
		endCall_RefDevice();
		minWait();
		String cmd1 = "adb -s "+r_Id+" shell service call isms 7 i32 0 s16 \"com.android.mms.service\" s16 "+pryNum+" s16 \"null\" s16 \"HiTest\" s16 \"null\" s16 \"null\"";
		Runtime.getRuntime().exec(cmd1);
		customWait(2000);
		recieveSMSFromReferenceDevice();
		Runtime.getRuntime().exec("adb -s "+p_Id+" shell pm clear com.google.android.deskclock");
		minWait();
		addAlarm();
		timerExpire();
		SA.assertAll();
	}

	@Test(priority=3,dataProvider="XP8_Interruption", dataProviderClass=DataProviders.class)
	public void XP8_TC_03_Validate_Browser_Interruptions(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException
	{
		APP_LOGS.info("===========XP8_Interruption_03============");
		SoftAssert SA = new SoftAssert();
		clearRecentApps();
		launch_an_app("browser");
		if(isElementExist(multi_Loc_Strategy(Locators_XP8_CallHistory.ACCEPTCONTINUE, Locators_Stability.ACCEPTCONTINUEByID, Locators_Stability.ACCEPTCONTINUEByResourceId, Locators_Stability.ACCEPTCONTINUEByText, Locators_Stability.ACCEPTCONTINUEByXpath, 332, 1764))){
			clearChromePermission();		
		}
		minWait();
		validateCallInterruption();
		customWait(10000);
		endCall_RefDevice();
		minWait();
		String cmd1 = "adb -s "+r_Id+" shell service call isms 7 i32 0 s16 \"com.android.mms.service\" s16 "+pryNum+" s16 \"null\" s16 \"HiTest\" s16 \"null\" s16 \"null\"";
		Runtime.getRuntime().exec(cmd1);
		customWait(2000);
		recieveSMSFromReferenceDevice();
		Runtime.getRuntime().exec("adb -s "+p_Id+" shell pm clear com.google.android.deskclock");
		minWait();
		addAlarm();
		timerExpire();
		SA.assertAll();
	}

	@Test(priority=4,dataProvider="XP8_Interruption", dataProviderClass=DataProviders.class)
	public void XP8_TC_04_Validate_Contacts_Interruptions(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException
	{
		APP_LOGS.info("===========XP8_Interruption_04============");
		SoftAssert SA = new SoftAssert();
		clearRecentApps();
		launch_an_app("contacts");
		minWait();
		validateCallInterruption();
		customWait(10000);
		endCall_RefDevice();
		minWait();
		String cmd1 = "adb -s "+r_Id+" shell service call isms 7 i32 0 s16 \"com.android.mms.service\" s16 "+pryNum+" s16 \"null\" s16 \"HiTest\" s16 \"null\" s16 \"null\"";
		Runtime.getRuntime().exec(cmd1);
		customWait(2000);
		recieveSMSFromReferenceDevice();
		Runtime.getRuntime().exec("adb -s "+p_Id+" shell pm clear com.google.android.deskclock");
		minWait();
		addAlarm();
		timerExpire();
		SA.assertAll();
	}

	@Test(priority=5,dataProvider="XP8_Interruption", dataProviderClass=DataProviders.class)
	public void XP8_TC_05_Validate_Dailer_Interruptions(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException
	{
		APP_LOGS.info("===========XP8_Interruption_05============");
		SoftAssert SA = new SoftAssert();
		clearRecentApps();
		launch_an_app("phone");
		minWait();
		if(isElementExist(multi_Loc_Strategy(Locators_XP8_CallHistory.dialpadBtnInUiSelector, Locators_XP8_CallHistory.dialpadBtn, null, null, null, 0, 0))){
			System.out.println("Phone Dailer is available");
			test.log(LogStatus.PASS,"Phone Dailer is available");
			minWait();
			enterTextToInputField(multi_Loc_Strategy(Locators_XP8_CallHistory.keypadDailedNumber, Locators_XP8_CallHistory.dialpadEditFld, null, null, null, 0, 0), "+91123456789");
			minWait();
			validateCallInterruption();
			customWait(10000);
			endCall_RefDevice();
			minWait();
			String cmd1 = "adb -s "+r_Id+" shell service call isms 7 i32 0 s16 \"com.android.mms.service\" s16 "+pryNum+" s16 \"null\" s16 \"HiTest\" s16 \"null\" s16 \"null\"";
			Runtime.getRuntime().exec(cmd1);
			customWait(2000);
			recieveSMSFromReferenceDevice();
			Runtime.getRuntime().exec("adb -s "+p_Id+" shell pm clear com.google.android.deskclock");
			minWait();
			addAlarm();
			timerExpire();
			SA.assertAll();
		}
		else{
			System.out.println("Phone Dailer is not available");
			test.log(LogStatus.FAIL,"Phone Dailer is not available");
		}
	}

	@Test(priority=6,dataProvider="XP8_Interruption", dataProviderClass=DataProviders.class)
	public void XP8_TC_06_Validate_Camera_Interruptions(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException
	{
		APP_LOGS.info("===========XP8_Interruption_06============");
		SoftAssert SA = new SoftAssert();
		clearRecentApps();
		launch_an_app("camera");
		minWait();
		validateCallInterruption();
		customWait(10000);
		endCall_RefDevice();
		minWait();
		String cmd1 = "adb -s "+r_Id+" shell service call isms 7 i32 0 s16 \"com.android.mms.service\" s16 "+pryNum+" s16 \"null\" s16 \"HiTest\" s16 \"null\" s16 \"null\"";
		Runtime.getRuntime().exec(cmd1);
		customWait(2000);
		recieveSMSFromReferenceDevice();
		Runtime.getRuntime().exec("adb -s "+p_Id+" shell pm clear com.google.android.deskclock");
		minWait();
		addAlarm();
		timerExpire();
		SA.assertAll();
	}

	@Test(priority=7,dataProvider="XP8_Interruption", dataProviderClass=DataProviders.class)
	public void XP8_TC_07_Validate_Gallery_Interruptions(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException
	{
		APP_LOGS.info("===========XP8_Interruption_07============");
		SoftAssert SA = new SoftAssert();
		clearRecentApps();
		launch_an_app("photos");
		if(isElementExist(Locators_BaseUtil.confirmBtn)){
			clickBtn(Locators_BaseUtil.confirmBtn);
			minWait();
			clickBtn(Locators_BaseUtil.skipBtn);
		}
		minWait();
		validateCallInterruption();
		customWait(10000);
		endCall_RefDevice();
		minWait();
		String cmd1 = "adb -s "+r_Id+" shell service call isms 7 i32 0 s16 \"com.android.mms.service\" s16 "+pryNum+" s16 \"null\" s16 \"HiTest\" s16 \"null\" s16 \"null\"";
		Runtime.getRuntime().exec(cmd1);
		customWait(2000);
		recieveSMSFromReferenceDevice();
		Runtime.getRuntime().exec("adb -s "+p_Id+" shell pm clear com.google.android.deskclock");
		minWait();
		addAlarm();
		timerExpire();
		SA.assertAll();
	}

	@Test(priority=8,dataProvider="XP8_Interruption", dataProviderClass=DataProviders.class)
	public void XP8_TC_08_Validate_Sound_Recorder_Interruptions(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException
	{
		APP_LOGS.info("===========XP8_Interruption_08============");
		SoftAssert SA = new SoftAssert();
		clearRecentApps();
		launch_an_app("soundRecorder");
		minWait();
		validateCallInterruption();
		customWait(10000);
		endCall_RefDevice();
		minWait();
		String cmd1 = "adb -s "+r_Id+" shell service call isms 7 i32 0 s16 \"com.android.mms.service\" s16 "+pryNum+" s16 \"null\" s16 \"HiTest\" s16 \"null\" s16 \"null\"";
		Runtime.getRuntime().exec(cmd1);
		customWait(2000);
		recieveSMSFromReferenceDevice();
		Runtime.getRuntime().exec("adb -s "+p_Id+" shell pm clear com.google.android.deskclock");
		minWait();
		addAlarm();
		timerExpire();
		SA.assertAll();
	}

	@Test(priority=9,dataProvider="XP8_Interruption", dataProviderClass=DataProviders.class)
	public void XP8_TC_09_Validate_YouTube_Interruptions(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException
	{
		APP_LOGS.info("===========XP8_Interruption_09============");
		SoftAssert SA = new SoftAssert();
		clearRecentApps();
		launch_an_app("youTube");
		customWait(5000);
		clickBtn(multi_Loc_Strategy(Locators_XP8_CallHistory.youTubeVideoSelect, null, null, null, null, 539, 1493));
		minWait();
		validateCallInterruption();
		customWait(10000);
		endCall_RefDevice();
		minWait();
		String cmd1 = "adb -s "+r_Id+" shell service call isms 7 i32 0 s16 \"com.android.mms.service\" s16 "+pryNum+" s16 \"null\" s16 \"HiTest\" s16 \"null\" s16 \"null\"";
		Runtime.getRuntime().exec(cmd1);
		customWait(2000);
		recieveSMSFromReferenceDevice();
		Runtime.getRuntime().exec("adb -s "+p_Id+" shell pm clear com.google.android.deskclock");
		minWait();
		addAlarm();
		minWait();
		timerExpire();
		SA.assertAll();
	}

	@Test(priority=10,dataProvider="XP8_Interruption", dataProviderClass=DataProviders.class)
	public void XP8_TC_10_Validate_Play_Music_Interruptions(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException
	{
		APP_LOGS.info("===========XP8_Interruption_10============");
		SoftAssert SA = new SoftAssert();
		clearRecentApps();
		launch_an_app("playMusic");
		minWait();
		if(!isElementExist(Locators_XP8_CallHistory.noMusicAvailable)){
			if(isElementExist(Locators_XP8_CallHistory.NoThanksBtnMusic)){
				clickBtn(Locators_XP8_CallHistory.NoThanksBtnMusic);
				minWait();
				clickBtn(Locators_XP8_CallHistory.SkipBtnMusic);
				minWait();
				clickBtn(Locators_XP8_CallHistory.SkipBtnMusic);
				minWait();
			}
			else if(isElementExist(Locators_XP8_CallHistory.SkipBtnMusic)){
				clickBtn(Locators_XP8_CallHistory.SkipBtnMusic);
			}
			else{
				System.out.println("Music app launched");
				APP_LOGS.info("Music app launched");
				test.log(LogStatus.PASS, "Music app launched");
			}
			clickBtn(Locators_XP8_CallHistory.musicPlayButton);

			WebDriverWait wait = new WebDriverWait(aDriver, 120);
			wait.until(ExpectedConditions.visibilityOf(Locators_XP8_CallHistory.musicPauseStopButton));

			if(isElementExist(Locators_XP8_CallHistory.musicPauseStopButton)){
				System.out.println("Music Playing successfully");
				APP_LOGS.info("Music Playing successfully");
				test.log(LogStatus.PASS, "Music Playing successfully");
				minWait();
				validateCallInterruption();
				customWait(10000);
				endCall_RefDevice();
				minWait();
				String cmd1 = "adb -s "+r_Id+" shell service call isms 7 i32 0 s16 \"com.android.mms.service\" s16 "+pryNum+" s16 \"null\" s16 \"HiTest\" s16 \"null\" s16 \"null\"";
				Runtime.getRuntime().exec(cmd1);
				customWait(2000);
				recieveSMSFromReferenceDevice();
				Runtime.getRuntime().exec("adb -s "+p_Id+" shell pm clear com.google.android.deskclock");
				minWait();
				addAlarm();
				minWait();
				timerExpire();
				minWait();
				launch_an_app("playMusic");
				minWait();
				if(isElementExist(Locators_XP8_CallHistory.musicPauseEnabled)){
					clickBtn(Locators_XP8_CallHistory.musicPauseEnabled);
					minWait();
					System.out.println("Music stopped");
					test.log(LogStatus.PASS, "Music stopped");
				}
				else{
					System.out.println("Music stopped");
					test.log(LogStatus.PASS, "Music stopped");
				}
				//clickBtn(Locators_XP8_CallHistory.musicPauseStopButton);
			}
			else{
				System.out.println("Music app isn't launched");
				APP_LOGS.info("Music app isn't launched");
				test.log(LogStatus.FAIL, "Music app isn't launched");
			}
		}
		else{
			System.out.println("Music app doesnt have songs");
			APP_LOGS.info("Music app doesnt have songs");
			test.log(LogStatus.INFO, "Music app doesnt have songs");
			minWait();
			validateCallInterruption();
			customWait(10000);
			endCall_RefDevice();
			minWait();
			String cmd1 = "adb -s "+r_Id+" shell service call isms 7 i32 0 s16 \"com.android.mms.service\" s16 "+pryNum+" s16 \"null\" s16 \"HiTest\" s16 \"null\" s16 \"null\"";
			Runtime.getRuntime().exec(cmd1);
			customWait(2000);
			recieveSMSFromReferenceDevice();
			Runtime.getRuntime().exec("adb -s "+p_Id+" shell pm clear com.google.android.deskclock");
			minWait();
			addAlarm();
			minWait();
			timerExpire();
		}
		SA.assertAll();
	}

	@Test(priority=11,dataProvider="XP8_Interruption", dataProviderClass=DataProviders.class)
	public void XP8_TC_11_Validate_FM_Interruptions(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException
	{
		APP_LOGS.info("===========XP8_Interruption_11============");
		SoftAssert SA = new SoftAssert();
		clearRecentApps();
		launch_an_app("fm");
		minWait();
		if(!isElementExist(Locators_XP8_CallHistory.FMHeadSetError)){
			System.out.println("Headset connected");
			APP_LOGS.info("Headset connected");
			test.log(LogStatus.INFO, "Headset connected");
			if(!isElementExist(Locators_XP8_CallHistory.fmFrequency)){
				System.out.println("FM isn't ON");
				APP_LOGS.info("FM isn't ON");
				test.log(LogStatus.INFO, "FM isn't ON");
				minWait();
				clickBtn(Locators_XP8_CallHistory.fmPowerONOFF);

			}
			else{
				System.out.println("FM is ON");
				APP_LOGS.info("FM is ON");
				test.log(LogStatus.INFO, "FM is ON");
				minWait();
			}
			minWait();
			validateCallInterruption();
			customWait(10000);
			endCall_RefDevice();
			minWait();
			String cmd1 = "adb -s "+r_Id+" shell service call isms 7 i32 0 s16 \"com.android.mms.service\" s16 "+pryNum+" s16 \"null\" s16 \"HiTest\" s16 \"null\" s16 \"null\"";
			Runtime.getRuntime().exec(cmd1);
			customWait(2000);
			recieveSMSFromReferenceDevice();
			Runtime.getRuntime().exec("adb -s "+p_Id+" shell pm clear com.google.android.deskclock");
			minWait();
			addAlarm();
			minWait();
			timerExpire();
			minWait();
			launch_an_app("fm");
			minWait();
			clickBtn(Locators_XP8_CallHistory.fmPowerONOFF);
		}
		else{
			System.out.println((Locators_XP8_CallHistory.FMHeadSetError).getText());
			APP_LOGS.info("Headset isnt connected");
			test.log(LogStatus.FAIL, (Locators_XP8_CallHistory.FMHeadSetError).getText());
		}
		SA.assertAll();
	}

	@Test(priority=12,dataProvider="XP8_Interruption", dataProviderClass=DataProviders.class)
	public void XP8_TC_12_Validate_Maps_Interruptions(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException
	{
		APP_LOGS.info("===========XP8_Interruption_12============");
		SoftAssert SA = new SoftAssert();
		clearRecentApps();
		launch_an_app("maps");
		minWait();
		validateCallInterruption();
		customWait(10000);
		endCall_RefDevice();
		minWait();
		String cmd1 = "adb -s "+r_Id+" shell service call isms 7 i32 0 s16 \"com.android.mms.service\" s16 "+pryNum+" s16 \"null\" s16 \"HiTest\" s16 \"null\" s16 \"null\"";
		Runtime.getRuntime().exec(cmd1);
		customWait(2000);
		recieveSMSFromReferenceDevice();
		Runtime.getRuntime().exec("adb -s "+p_Id+" shell pm clear com.google.android.deskclock");
		minWait();
		addAlarm();
		minWait();
		timerExpire();
		SA.assertAll();
	}

	@Test(priority=13,dataProvider="XP8_Interruption", dataProviderClass=DataProviders.class)
	public void XP8_TC_13_Validate_File_Manager_Interruptions(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException, TesseractException
	{
		APP_LOGS.info("===========XP8_Interruption_13============");
		SoftAssert SA = new SoftAssert();
		clearRecentApps();
		launch_an_app("fileManager");
		minWait();
		validateCallInterruption();
		customWait(10000);
		endCall_RefDevice();
		minWait();
		String cmd1 = "adb -s "+r_Id+" shell service call isms 7 i32 0 s16 \"com.android.mms.service\" s16 "+pryNum+" s16 \"null\" s16 \"HiTest\" s16 \"null\" s16 \"null\"";
		Runtime.getRuntime().exec(cmd1);
		customWait(2000);
		recieveSMSFromReferenceDevice();
		Runtime.getRuntime().exec("adb -s "+p_Id+" shell pm clear com.google.android.deskclock");
		minWait();
		addAlarm();
		minWait();
		timerExpire();
		SA.assertAll();
	}

	@Test(priority=14,dataProvider="XP8_Interruption", dataProviderClass=DataProviders.class)
	public void XP8_TC_14_Validate_Gmail_Interruptions(Hashtable<String, String> data) throws Exception
	{
		APP_LOGS.info("===========XP8_Interruption_14============");
		SoftAssert SA = new SoftAssert();
		clearRecentApps();
		gmailAccess();
		minWait();
		validateCallInterruption();
		customWait(10000);
		endCall_RefDevice();
		minWait();
		String cmd1 = "adb -s "+r_Id+" shell service call isms 7 i32 0 s16 \"com.android.mms.service\" s16 "+pryNum+" s16 \"null\" s16 \"HiTest\" s16 \"null\" s16 \"null\"";
		Runtime.getRuntime().exec(cmd1);
		minWait();
		recieveSMSFromReferenceDevice();
		Runtime.getRuntime().exec("adb -s "+p_Id+" shell pm clear com.google.android.deskclock");
		minWait();
		addAlarm();
		minWait();
		timerExpire();
		SA.assertAll();
	}

}