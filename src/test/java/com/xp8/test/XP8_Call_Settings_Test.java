package com.xp8.test;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeClass;
//import org.testng.annotations.AfterMethod;
import java.awt.AWTException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Hashtable;
import java.util.Properties;
import java.util.Timer;
import java.util.TimerTask;

import org.json.simple.parser.ParseException;
import org.openqa.selenium.interactions.touch.TouchActions;
import org.openqa.selenium.support.PageFactory;
import org.testng.ITestResult;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.google.common.collect.ImmutableMap;
import com.graphics.gui.JsonFileReaderAndWriter;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTestInterruptedException;
import com.relevantcodes.extentreports.LogStatus;
import com.xp5S.util.GetMethods;
import com.xp5S.util.appiumService;
import com.xp8.util.BaseUtil;
import com.xp8.util.DataProviders;
import com.xp8.util.ExcelConstants;
import com.xp8.util.ExcelReader;
import com.xp8.util.Locators_BaseUtil;
import com.xp8.util.Locators_CallSetting;
import com.xp8.util.Locators_SMS_DeviceStability;
import com.xp8.util.Locators_XP8_Sanity;
import com.xp8.util.XP8_Call_Settings_Util;

import io.appium.java_client.MobileBy;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidKeyCode;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class XP8_Call_Settings_Test extends XP8_Call_Settings_Util {
	public ExcelReader excel;
	Properties properties;
	public static ExtentTestInterruptedException testexception;
	public Timer timer;

	@BeforeSuite
	public void beforeSuite() throws FileNotFoundException, InterruptedException, IOException, ParseException  
	{
		
		Calendar cal = new GregorianCalendar();
		int month = cal.get(Calendar.MONTH);
		int year = cal.get(Calendar.YEAR);
		int sec = cal.get(Calendar.SECOND);
		int min = cal.get(Calendar.MINUTE);
		int date = cal.get(Calendar.DATE);
		int day = cal.get(Calendar.HOUR_OF_DAY);
		
		
		extent = new ExtentReports("src/test/resources/extentreport/XP8_Call_Settings_TestReport.html", true); //Provide Desired Report Directory Location and Name
		extent.loadConfig(new File("src/test/resources/StorageFile/ReportsConfig.xml"));
		extent.addSystemInfo("Build #",JsonFileReaderAndWriter.primaryDevFirmwareReader())
		.addSystemInfo("Product",JsonFileReaderAndWriter.primaryDevModelReader())
		.addSystemInfo("Operator", JsonFileReaderAndWriter.primaryDevOperatorReader());			
		fetch_Devices_Details();	


		try {
			appiumService.TOTAL_NUM_OF_TESTCASES=GetMethods.TotalTestcase("XP8_TC", this.getClass());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@BeforeMethod()
	public  void beforeMethod(Method method) 
	{
		test = extent.startTest( (this.getClass().getSimpleName() +" :: "+  method.getName()),method.getName()); //Test Case Start Here
		
		//String BuildNumber=BaseUtil.fetchDeviceProperty("adb shell getprop ro.build.id");
		//test.assignCategory("Build #:"+BuildNumber);
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
		{	String screenshot_path=captureScreenshot(method.getName());
		String image= test.addScreenCapture(screenshot_path);		
		test.log(LogStatus.FAIL,result.getThrowable());						
		}
		extent.endTest(test);
		extent.flush();
	}

	@BeforeTest
	public void setupSys() throws MalformedURLException, InterruptedException, FileNotFoundException, AWTException{
		
		properties=loadDriverAndProperties();
		Locators_CallSetting loc=new Locators_CallSetting(aDriver);
		Locators_BaseUtil loc1 = new Locators_BaseUtil(aDriver);	
		PageFactory.initElements(new AppiumFieldDecorator(aDriver),loc);
		PageFactory.initElements(new AppiumFieldDecorator(aDriver),loc1);
		excel=new ExcelReader(ExcelConstants.XP5S_XL_PATH);	
		
	}
	//============================================================Test Case=======================================================================================
	
	
	
	@Test(priority=1,dataProvider="XP8_Call_Settings", dataProviderClass=DataProviders.class)
	public void XP8_TC_01_Call_Settings(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{  
		SoftAssert sa=new SoftAssert();
		//Navigate to call settings
		APP_LOGS.info("===========XP8_TC_01_Call_Settings============");
		if (p_b_No.contains("-10.")||p_b_No.contains("-00.")||p_b_No.contains("-11.")||p_b_No.contains("-15.")||p_b_No.contains("-29.")||p_b_No.contains("-26.")) {
		clearRecentApps();
	    launch_an_app("phone");
		navigate_to_call_settings();
		clickBackButton(4);
		//Validating call settings are navigated
		validate_navigatetocallsettings(sa);
		}
		
		sa.assertAll();
	}
	

	@Test(priority=2,dataProvider="XP8_Call_Settings", dataProviderClass=DataProviders.class)
	public void XP8_TC_02_Call_Settings_ValidateSettings_and_Displayoption(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		//Verify the behaviour of Call settings dispaly options
		SoftAssert sa=new SoftAssert();
		
		APP_LOGS.info("===========XP8_TC_02_Call_Settings_ValidateSettings_and_Displayoption============");
		if (p_b_No.contains("-10.")||p_b_No.contains("-00.")||p_b_No.contains("-11.")||p_b_No.contains("-15.")||p_b_No.contains("-29.")||p_b_No.contains("-26.")) {
	    launch_an_app("contacts");
		//Delete the contact in contact app
		deleteIfContactsPresent(sa);
		minWait();
		clearRecentApps();
		launch_an_app("contacts");
		minWait();
		//Set default saving account in contact app
		setDefaultSavingAccount();
		launch_an_app("phone");
		//Add contact from contact pages
		AddContactFromContactsPage(data.get("name"),data.get("lastname"), data.get("phone"));
		//Validate the saved contact
		validateSavedContact(data.get("name"), "from Contacts page",sa);
		add_Contact(data.get("Newname"),data.get("Newlastname"), data.get("Newphone"));
		validateSavedContact2(data.get("Newname"), "from Contacts page",sa);
		add_Contact(data.get("Thirdname"),data.get("Thirdlastname"), data.get("Thirdphone"));
		validateSavedContact2(data.get("Thirdname"), "from Contacts page",sa);
		//Validate settings and display options in call screening
		validateSettingsAndDisplayOptions(sa);
		}
		sa.assertAll();

	}


	@Test(priority=3,dataProvider="XP8_Call_Settings", dataProviderClass=DataProviders.class)
	public void XP8_TC_03_Call_Settings_ValidateSounds_and_VibrationSuboption(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		SoftAssert sa=new SoftAssert();
		APP_LOGS.info("=========== XP8_TC_03_Call_Settings_ValidateSounds_and_VibrationSuboption============");
		if (p_b_No.contains("-10.")||p_b_No.contains("-00.")||p_b_No.contains("-11.")||p_b_No.contains("-29.")||p_b_No.contains("-26.")) {
		launch_an_app("phone");
		//Navigate to sounds and vibration option in call settings
		navigateToSettingsAndElement(Locators_CallSetting.soundsAndVibration);
		minWait();
		//Validate phone ringtone
		validatePhoneRingtone(sa);
		minWait();
		//Validate sounds and vibration SubOptions
		validateSoundsAndVibrationSubOptions(sa);
		minWait();
		clickBackButton(3);
		}
		else if(p_b_No.contains("-15.")){
			launch_an_app("phone");
			navigateToSettingsAndElement(Locators_CallSetting.soundsAndVibration);
			minWait();
			validatePhoneRingtone_vzw(sa);
			validateSoundsAndVibrationSubOptions_vzw(sa);
		}
		sa.assertAll();
	}
	@Test(priority=4,dataProvider="XP8_Call_Settings", dataProviderClass=DataProviders.class)
	public void XP8_TC_04_Call_Settings_Validate_Quickresponses(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException
	{
		SoftAssert sa=new SoftAssert();
		APP_LOGS.info("===========XP8_TC_04_Call_Settings_Validate_Quickresponses===========");
		if (p_b_No.contains("-10.")||p_b_No.contains("-00.")||p_b_No.contains("-11.")||p_b_No.contains("-15.")||p_b_No.contains("-29.")||p_b_No.contains("-26.")) {
		launch_an_app("phone");
		//Navigate to Quickresponse option in call settings
		navigateToSettingsAndElement(Locators_CallSetting.quickResponseOpt);
		minWait();
		//Default quickresponses are restored
		Restore_default_QuickResponses();
		//Validate quick response list
		getAndValidateQuickResponsesList(sa);
		minWait();
		//Edit and validate quick response list
		editAndValidateQuickResponse(data.get("textMessage"),sa);
		minWait();
		clickBackButton(4);
		}
		sa.assertAll();
	}
	@Test(priority=5,dataProvider="XP8_Call_Settings", dataProviderClass=DataProviders.class)
	public void  XP8_TC_05_Call_Settings_Validate_Speeddialsettings(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException
	{

		APP_LOGS.info("===========XP8_Call_Settings_05===========");
		SoftAssert sa=new SoftAssert();
		if (p_b_No.contains("-10.")||p_b_No.contains("-00.")||p_b_No.contains("-11.")||p_b_No.contains("-29.")||p_b_No.contains("-26.")) {
		launch_an_app("phone");
		//Navigate to speed dial setting option in call setting
		navigateToSettingsAndElement(Locators_CallSetting.speedDialSettingsOpt);
		//Validating presence of voice mail in speed dial setting
		presenceOfVoicemailInSpeedDailSettings(sa);
		//Add contact in speed dial setting
		addContactInSpeedDailSettings(data.get("phone"),sa);
		//Replace contact in speed dial setting
		replaceContactInSpeedDailSettings(data.get("name"),sa);
		//Delete contact in speed dial setting
		deleteContactInSpeedDailSettings(sa);
		addContactInSpeedDailSettings(data.get("phone"),sa);
		Runtime.getRuntime().exec("adb -s "+ p_Id +" shell input keyevent 4");
		//Validating speed dial setting no is working properly
		launch_an_app("phone");
		minWait();
		clickBtn(Locators_CallSetting.callericon);
		minWait();
		TouchAction action = new TouchAction(aDriver);
		action.longPress(Locators_CallSetting.twoindialpad).release().perform();
		minWait();
		clickBtn(Locators_CallSetting.endcallicon);
		minWait();
		launch_an_app("phone");
		navigateToSettingsAndElement(Locators_CallSetting.speedDialSettingsOpt);
		deleteContactInSpeedDailSettings_post(sa);
		clickBackButton(3);
		}
		else if(p_b_No.contains("-15.")){
			launch_an_app("phone");
			navigateToSettingsAndElement(Locators_CallSetting.callssettingopt);
			presenceOfCallingAccounts(sa);
			presenceOfFixeddialingnumbers(sa);
			presenceOfCallForwarding(sa);
			presenceOfAdditionalSettings(sa);
			presenceOfCallbarring(sa);
		}
		sa.assertAll();


	}
	
	@Test(priority=6,dataProvider="XP8_Call_Settings", dataProviderClass=DataProviders.class)
	public void  XP8_TC_06_Call_Settings_Call_Screening_black_white_list(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException
	{
		SoftAssert sa = new SoftAssert();{
			APP_LOGS.info("===========XP8_Call_Settings_06===========");
			if (p_b_No.contains("-10.")||p_b_No.contains("-00.")||p_b_No.contains("-11.")||p_b_No.contains("-29.")||p_b_No.contains("-26.")) {
			//Deleting no in white list
			launch_an_app("phone");
			minWait();
			navigateToSettingsAndElement(Locators_CallSetting.Callscreening);
			minWait();
			clickBtn(Locators_CallSetting.Managewhitelist);
			minWait();
			deleting_no_in_whitelist();
			clickBackButton(1);
			clickBtn(Locators_CallSetting.Manageblacklist);
			minWait();
			deleting_no_in_blacklist();
			launch_an_app("phone");
			//Navigate to call screening in call settings
			navigateToSettingsAndElement(Locators_CallSetting.Callscreening);
			minWait();
			//Verify adding numbers to Black list in CallScreening
			BlacklistincallScreening(data.get("phoneno4"));
			minWait();
			//Verify unblockingnumbers  from Black list(CallScreening)
			UnblockincallScreening();
			minWait();
			//clickBackButton(4);
			//Adding and validating new number with + prefix to block list
			validate_blocknumber_with_values(data.get("phoneno1"),sa);
			minWait();
			UnblockincallScreening();
			minWait();
			//adding new number with +91 prefix to block list.
			validate_blocknumber_with_values(data.get("phoneno2"),sa);	
			minWait();
			UnblockincallScreening();
			minWait();
			//adding international no to blacklist
			validate_blocknumber_with_values(data.get("phoneno11"),sa);	
			minWait();
			UnblockincallScreening();
			minWait();
			// adding new number with 0 prefix to block list
			validate_blocknumber_with_values(data.get("phoneno3"),sa);
			minWait();
			UnblockincallScreening();
			minWait();
			//Deleting unblocking numbers
			clickBackButton(1);
			}
			else if(p_b_No.contains("-15.")){
				launch_an_app("phone");
				minWait();
				navigateToSettingsAndElement(Locators_CallSetting.vzwblockedno);
				minWait();
				deleting_no_in_blacklist();
				launch_an_app("phone");
				//Navigate to call screening in call settings
				navigateToSettingsAndElement(Locators_CallSetting.vzwblockedno);
				minWait();
				//Verify adding numbers to Black list in CallScreening
				 BlacklistincallScreening_vzw(data.get("phoneno4"));
				minWait();
				//Verify unblockingnumbers  from Black list(CallScreening)
				UnblockincallScreening();
				minWait();
				//clickBackButton(4);
				//Adding and validating new number with + prefix to block list
				validate_blocknumber_with_values(data.get("phoneno1"),sa);
				minWait();
				UnblockincallScreening();
				minWait();
				//adding new number with +91 prefix to block list.
				validate_blocknumber_with_values(data.get("phoneno2"),sa);	
				minWait();
				UnblockincallScreening();
				minWait();
				//adding international no to blacklist
				validate_blocknumber_with_values(data.get("phoneno11"),sa);	
				minWait();
				UnblockincallScreening();
				minWait();
				// adding new number with 0 prefix to block list
				validate_blocknumber_with_values(data.get("phoneno3"),sa);
				minWait();
				UnblockincallScreening();
			}
			sa.assertAll();
		}
	}
	@Test(priority=7,dataProvider="XP8_Call_Settings", dataProviderClass=DataProviders.class)
	public void  XP8_TC_07_Call_Settings_Call_Screening_black_white_list_2(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException
	{
		SoftAssert sa = new SoftAssert();{
			APP_LOGS.info("===========XP8_Call_Settings_07===========");
			if (p_b_No.contains("-10.")||p_b_No.contains("-00.")||p_b_No.contains("-11.")||p_b_No.contains("-29.")||p_b_No.contains("-26.")) {
			// Call Screening option is present under call settings
			validate_Callscreening_under_Callsettings(sa);
			minWait();
			launch_an_app("phone");
			minWait();
			navigateToSettingsAndElement(Locators_CallSetting.Callscreening);
			minWait();
			clickBtn(Locators_CallSetting.Manageblacklist);
			minWait();
			//Deleting all no in blacklist
			deleting_blacklist();
			//Verify Cancel  options while adding number to Black list
			validate_cancel_option_blacklist(data.get("phoneno1"),sa);
			minWait();
			// number field is displayed on selecting "ADD A NUMBER" option under Black List
			validate_addno_option_under_callsettings_blacklist(sa);
			minWait();
			launch_an_app("phone");
			navigateToSettingsAndElement(Locators_CallSetting.Callscreening);
			minWait();	
			clickBtn(Locators_CallSetting.Manageblacklist);
			minWait();
			//Adding maximum numbers to Black list
			for(int k=1;k<=10; k++){
				addingno_in_blacklist_callscreening(data.get("phoneno"+k));
				minWait();
			}
			//Validate scrolling through blocked numbers
			validatescrollingno_in_blacklist(data.get("phoneno1"),sa);
			minWait();		
			launch_an_app("phone");
			minWait();
			navigateToSettingsAndElement(Locators_CallSetting.Callscreening);
			minWait();
			clickBtn(Locators_CallSetting.Manageblacklist);
			minWait();
			//Deleting all no in blacklist
			deleting_blacklist();
			
			// clickBackButton(1);
			launch_an_app("phone");
			minWait();
			navigateToSettingsAndElement(Locators_CallSetting.Callscreening);
			minWait();
			clickBtn(Locators_CallSetting.Managewhitelist);
			minWait();
			//Verify Cancel  options while adding number to white list
			validate_cancel_option_whitelist(data.get("phoneno1"),sa);
			minWait();
			// number field is displayed on selecting "ADD A NUMBER" option under White List
			validate_addno_option_under_callsettings_Whitelist(sa);
			minWait();
			//Verify removing numbers from white list
			launch_an_app("phone");
			minWait();
			navigateToSettingsAndElement(Locators_CallSetting.Callscreening);
			minWait();
			clickBtn(Locators_CallSetting.Managewhitelist);
			minWait();
			//Verify adding maximum numbers to White list
			for(int k=1;k<=10; k++){
				addContactNumWhitelist(data.get("phoneno"+k));
				minWait();
			}
			//Verify scrolling through white list numbers
			validatescrollingno_in_whitelist(data.get("phoneno1"),sa);
			minWait();
		}
			else if(p_b_No.contains("-15.")){
				launch_an_app("phone");
				minWait();
				navigateToSettingsAndElement(Locators_CallSetting.vzwblockedno);
				minWait();
				deleting_blacklist();
				validate_cancel_option_blacklist_vzw(data.get("phoneno1"), sa);
				validate_addno_option_under_callsettings_blacklist(sa);
				minWait();
				launch_an_app("phone");
				navigateToSettingsAndElement(Locators_CallSetting.vzwblockedno);
				minWait();
				for(int k=1;k<=10; k++){
					addingno_in_blacklist_callscreening(data.get("phoneno"+k));
					minWait();
				}
				//Validate scrolling through blocked numbers
				validatescrollingno_in_blacklist(data.get("phoneno1"),sa);
				minWait();	
				launch_an_app("phone");
				minWait();
				navigateToSettingsAndElement(Locators_CallSetting.vzwblockedno);
				minWait();
				deleting_blacklist();
		}
		sa.assertAll();
		}

	}
	@Test(priority=8,dataProvider="XP8_Call_Settings", dataProviderClass=DataProviders.class)
	public void  XP8_TC_08_Call_Settings_Call_Screening(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException
	{
		SoftAssert sa = new SoftAssert();{
			APP_LOGS.info("===========XP8_Call_Settings_08===========");
			if (p_b_No.contains("-10.")||p_b_No.contains("-00.")||p_b_No.contains("-11.")||p_b_No.contains("-29.")||p_b_No.contains("-26.")) {
			launch_an_app("phone");
			minWait(); 
			navigateToSettingsAndElement(Locators_CallSetting.Callscreening);
			minWait();
			clickBtn(Locators_CallSetting.Managewhitelist);
			minWait();
			deleting_no_in_whitelist();
			//"Screening incoming call Setting" option is highlighted (not greyed out)
			validate_screening_incoming_call_highlighted(sa);
			minWait();
			launch_an_app("phone");
			minWait();
			navigateToSettingsAndElement(Locators_CallSetting.Callscreening);
			minWait();
			clickBtn(Locators_CallSetting.Manageblacklist);
			minWait();
			//Deleting all no in blacklist
			deleting_blacklist();
			minWait();
			launch_an_app("phone");
			navigateToSettingsAndElement(Locators_CallSetting.Callscreening);
			minWait();
			//the behavior  by pressing Back key once while adding number to the blocked list.
			validate_pressing_backkey_once(data.get("phoneno1"),sa);
			minWait();
			////the behavior  by pressing Back key twice while adding number to the blocked list.
			validate_pressing_backkey_twice(data.get("phoneno1"),sa);
			//the behavior  by pressing Home key (Short press) while adding number to the blocked list.
			validate_pressing_Shortpress_HomeKey(data.get("phoneno1"),sa);
			minWait();
			//the behavior  by pressing Home key (LONG PRESS)while adding number to the blocked list.
			/*validate_pressing_Longpress_HomeKey(data.get("phoneno1"),sa);
			minWait();*/
			//the behavior  by pressing Recent apps key while adding number to the blocked list
			validate_pressing_Recentsapp_Key(data.get("phoneno1"),sa);
			minWait();
			launch_an_app("phone");
			minWait();
			navigateToSettingsAndElement(Locators_CallSetting.Callscreening);
			minWait();
			clickBtn(Locators_CallSetting.Manageblacklist);
			minWait();
			//Deleting all no in blacklist
			deleting_blacklist();
		}
			else if(p_b_No.contains("-15.")){
				launch_an_app("phone");
				minWait();
				navigateToSettingsAndElement(Locators_CallSetting.vzwblockedno);
				minWait();
				deleting_blacklist();
				minWait();
				validate_pressing_backkey_once_vzw(data.get("phoneno1"), sa);
				minWait();
				validate_pressing_backkey_twice_vzw(data.get("phoneno1"), sa);
				minWait();
				validate_pressing_Shortpress_HomeKey_vzw(data.get("phoneno1"), sa);
				minWait();
				validate_pressing_Recentsapp_Key_vzw(data.get("phoneno1"), sa);
				
			}
		}
		sa.assertAll();

	}

	@Test(priority=9,dataProvider="XP8_Call_Settings", dataProviderClass=DataProviders.class)
	public void  XP8_TC_09_Call_Settings_block_contactapp(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException

	// blocking  numbers from Contact app

	{
		SoftAssert sa = new SoftAssert();
		{
			APP_LOGS.info("===========XP8_Call_Settings_09===========");
			if (p_b_No.contains("-10.")||p_b_No.contains("-00.")||p_b_No.contains("-11.")||p_b_No.contains("-29.")||p_b_No.contains("-26.")) {
			clearRecentApps();
			launch_an_app("phone");
			minWait();
			navigateToSettingsAndElement(Locators_CallSetting.Callscreening);
			minWait();
			clickBtn(Locators_CallSetting.Manageblacklist);
			minWait();
			//Deleting all no in blacklist
			deleting_blacklist();
			minWait();
			launch_an_app("contacts");
			minWait();
			//Block no in contact app
			blockno_in_contactapp(refNum);
			minWait();
			//Validating Blockingno from contacts app is reflected in Black lsit under Call screening
			validate_blockno_in_blacklist(sa);
		}
			else if(p_b_No.contains("-15.")){
				launch_an_app("phone");
				minWait();
				navigateToSettingsAndElement(Locators_CallSetting.vzwblockedno);
				minWait();
				deleting_blacklist();
				minWait();
				launch_an_app("contacts");
				minWait();
				//Block no in contact app
				blockno_in_contactapp(refNum);
				minWait();
				validate_blockno_in_blacklist_vzw(sa);
			}
		}
		sa.assertAll();
	}

	@Test(priority=10,dataProvider="XP8_Call_Settings", dataProviderClass=DataProviders.class)
	public void  XP8_TC_10_Call_Settings_Unblock_contactapp(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException

	// unblocking  numbers from Contact app

	{
		SoftAssert sa = new SoftAssert();
		{
			APP_LOGS.info("===========XP8_Call_Settings_10===========");
			if (p_b_No.contains("-10.")||p_b_No.contains("-00.")||p_b_No.contains("-11.")||p_b_No.contains("-29.")||p_b_No.contains("-26.")) {
			clearRecentApps();
			launch_an_app("contacts");
			minWait();
			//Unblock in contact app
			unblockno_in_contactapp(refNum);
			minWait();
			clickBackButton(4);
			launch_an_app("phone");
			minWait();
			//Validating UnBlockingno from contacts app is reflected in Black lsit under Call screening
			validate_Unblockno_in_blacklist(sa);
			minWait();
		}
			else if(p_b_No.contains("-15.")){
				clearRecentApps();
				launch_an_app("contacts");
				minWait();
				//Unblock in contact app
				unblockno_in_contactapp(refNum);
				minWait();
				clickBackButton(4);
				minWait();
				validate_Unblockno_in_blacklist_vzw(sa);
			}
		}
		sa.assertAll();
	}
	@Test(priority=11,dataProvider="XP8_Call_Settings", dataProviderClass=DataProviders.class)
	public void  XP8_TC_11_Call_Settings_block_Unblock_Calldetails(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException

	// Verify blocking and unblocking numbers from Call details
	{
		SoftAssert sa = new SoftAssert();
		{
			APP_LOGS.info("===========XP8_Call_Settings_11===========");
			
			if (p_b_No.contains("-10.")||p_b_No.contains("-00.")||p_b_No.contains("-11.")||p_b_No.contains("-29.")||p_b_No.contains("-26.")) {
			clearRecentApps();
			minWait();
			launch_an_app("phone");
			minWait();
			navigateToSettingsAndElement(Locators_CallSetting.Callscreening);
			minWait();
			clickBtn(Locators_CallSetting.Manageblacklist);
			minWait();
			//Deleting all no in blacklist
			deleting_blacklist();
			minWait();
			clearcallhistory();
			minWait();
			make_Call_from_PrmyDev();
			customWait(3000);
			endCall_PrmyDevice();
			minWait();
			blockno_in_Calldetails();
			minWait();
			//Validating Blockingno from contacts app is reflected in Black lsit under Call screening
			validate_blockno_in_blacklist(sa);
			minWait();
			clearRecentApps();
			minWait();
			//Unblock in calldetails
			Unblockno_in_calldetails();
			minWait();
			//Validating UnBlockingno from contacts app is reflected in Black lsit under Call screening
			validate_Unblockno_in_blacklist(sa);
			minWait();
			//Validate the no is blocked or unblocked from callhistory
			clearcallhistory();
			minWait();
			make_Call_from_PrmyDev();
			customWait(3000);
			endCall_PrmyDevice();
			clickBackButton(2);
			minWait();
			launch_an_app("phone");
			validateBlockAndUnblockNumber(data.get("status"),("refNum"),sa);
			minWait(); 
			System.out.println("coming out");
			//Validate cancel option in call details block no
			launch_an_app("phone");
			minWait();
			navigateToSettingsAndElement(Locators_CallSetting.Callscreening);
			minWait();
			clickBtn(Locators_CallSetting.Manageblacklist);
			minWait();
			//Deleting all no in blacklist
			deleting_blacklist();
			minWait();
			clearRecentApps();
			launch_an_app("phone");
			minWait();
			clickBtn(Locators_CallSetting.Callhistorytab);
			minWait();
			clickBtn(Locators_CallSetting.dialerfirstno);
			validate_canceloptionin_calldetailsblockno(sa);
			minWait();
			clickBackButton(3);
			System.out.println("coming out");
			//Validate cancel options in call details Unblock no
			clearRecentApps();
			launch_an_app("phone");
			minWait();
			clickBtn(Locators_CallSetting.Callhistorytab);
			minWait();
			clickBtn(Locators_CallSetting.dialerfirstno);
			customWait(2000);
			
			validate_canceloptionin_calldetailsUnblockno(sa);
			minWait();
		}
			else if(p_b_No.contains("-15.")){
				clearRecentApps();
				minWait();
				launch_an_app("phone");
				minWait();
				navigateToSettingsAndElement(Locators_CallSetting.vzwblockedno);
				minWait();
				//Deleting all no in blacklist
				deleting_blacklist();
				minWait();
				clearcallhistory_vzw();
				minWait();
				make_Call_from_PrmyDev();
				customWait(3000);
				endCall_PrmyDevice();
				minWait();
				blockno_in_Calldetails_vzw();
				minWait();
				//Validating Blockingno from contacts app is reflected in Black lsit under Call screening
				validate_blockno_in_blacklist_vzw(sa);
				minWait();
				clearRecentApps();
				minWait();
				//Unblock in calldetails
				//Verizon_Unblockno_in_calldetails();
				minWait();
				//Validating UnBlockingno from contacts app is reflected in Black lsit under Call screening
				//validate_Unblockno_in_blacklist_vzw(sa);
				minWait();
				//Validate the no is blocked or unblocked from callhistory
				clearcallhistory_vzw();
				minWait();
				make_Call_from_PrmyDev();
				customWait(3000);
				endCall_PrmyDevice();
				clickBackButton(2);
				minWait();
				launch_an_app("phone");
				validateBlockAndUnblockNumber_vzw(data.get("status"),("refNum"),sa);
				minWait(); 
				System.out.println("coming out");
				//Validate cancel option in call details block no
				launch_an_app("phone");
				minWait();
				navigateToSettingsAndElement(Locators_CallSetting.vzwblockedno);
				minWait();
				
				//Deleting all no in blacklist
				deleting_blacklist();
				minWait();
				clearRecentApps();
			
			}
		}
		sa.assertAll();


	}
	@Test(priority=12,dataProvider="XP8_Call_Settings", dataProviderClass=DataProviders.class)
	public void  XP8_TC_12_Call_Settings_Call_Screening_Screeningincomingcallsetting_option1(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException

	//calls  is received from blacklist no only on disabling screening incoming call setting option
	//Block black list is selected in screening incoming call setting option
	{
		SoftAssert sa = new SoftAssert();
		{
			APP_LOGS.info("===========XP8_Call_Settings_12===========");
			if (p_b_No.contains("-10.")||p_b_No.contains("-00.")||p_b_No.contains("-11.")||p_b_No.contains("-29.")||p_b_No.contains("-26.")) {
			launch_an_app("phone");
			minWait();
			navigateToSettingsAndElement(Locators_CallSetting.Callscreening);
			minWait();
			clickBtn(Locators_CallSetting.Manageblacklist);
			minWait();
			//Deleting all no in blacklist
			deleting_blacklist();
			minWait();
			launch_an_app("phone");
			minWait();
			//Disabling Screening incoming call
			disabling_Screeningincomingcalls_callscreening();
			minWait();
			clickBtn(Locators_CallSetting.Manageblacklist);
			minWait();
			//Adding no in blacklist
			addingno_in_blacklist_callscreening(refNum);
			minWait();
			clickBackButton(4);
			minWait();
			//Validating call is received in primary device
			clearcallhistory();
			minWait();
			clickBackButton(4);
			make_Call_from_RefDev();
			customWait(6000);
			recieve_Call_PrimaryDev_O();
			customWait(6000);
			endCall_RefDevice();
			customWait(3000);
			validate_calllog_callreceived(sa);
			minWait();
			Unblockno_in_calldetails();
			minWait();
		}
			else if(p_b_No.contains("-15.")){
				aDriver.pressKeyCode(AndroidKeyCode.HOME);
			}
		}
		sa.assertAll();
	}

	@Test(priority=13,dataProvider="XP8_Call_Settings", dataProviderClass=DataProviders.class)
	public void  XP8_TC_13_Call_Settings_Call_Screening_Screeningincomingcallsettingoption_blockblacklist(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException

	//Verify calls and messages are not received from black list on selecting 'Block black list"

	{
		SoftAssert sa = new SoftAssert();
		{
			APP_LOGS.info("===========XP8_Call_Settings_13===========");
			if (p_b_No.contains("-10.")||p_b_No.contains("-00.")||p_b_No.contains("-11.")||p_b_No.contains("-29.")||p_b_No.contains("-26.")) {
			clearRecentApps();
			launch_an_app("phone");
			minWait();
			navigateToSettingsAndElement(Locators_CallSetting.Callscreening);
			minWait();
			clickBtn(Locators_CallSetting.Manageblacklist);
			minWait();
			//Deleting all no in blacklist
			deleting_blacklist();
			minWait();
			launch_an_app("phone");
			minWait();
			//Enable and selecting block black list in Screening incoming call settings
			enabling_Screeningincomingcalls_callscreening(data.get("phoneno"));
			clickBtn(Locators_CallSetting.Manageblacklist);
			minWait();
			addingno_in_blacklist_callscreening(refNum);
			minWait();
			clickBackButton(1);
			minWait();
			clickBtn(Locators_CallSetting.Managewhitelist);
			minWait();
			//Add contact no in whitelist
			addContactNumWhitelist(refNum);
			minWait();
			//Validating calls and messages are not received from black list on selecting 'Block black list"
			clickBackButton(4);
			clearcallhistory();
			minWait();
			make_Call_from_RefDev();
			customWait(6000);
			endCall_RefDevice();
			customWait(3000);
			delete_SMS(sa);
			minWait();
			sendSMS_fromRefDevice("Hi");
			minWait();
			validate_calllog_callnotreceived(sa);
			minWait();
			validate_NotSentMessage(sa);
			minWait();
			}
			else if(p_b_No.contains("-15.")){
				aDriver.pressKeyCode(AndroidKeyCode.HOME);
			}
		}
		sa.assertAll();
	}
	@Test(priority=14,dataProvider="XP8_Call_Settings", dataProviderClass=DataProviders.class)
	public void  XP8_TC_14_Call_Settings_Call_Screening_Screeningincomingcallsettingoption_Allowonlycontacts(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException

	//Verify calls and messages are  received from contacts on selecting "Allow only contacts"
	{
		SoftAssert sa = new SoftAssert();
		{
			APP_LOGS.info("===========XP8_Call_Settings_14===========");
			if (p_b_No.contains("-10.")||p_b_No.contains("-00.")||p_b_No.contains("-11.")||p_b_No.contains("-29.")||p_b_No.contains("-26.")) {
           launch_an_app("phone");
			minWait();
			//Enable and selecting allow only contacts in Screening incoming call settings
			enabling_Screeningincomingcalls_allowonlycontacts("phoneno");
			minWait();
			clickBtn(Locators_CallSetting.Manageblacklist);
			minWait();
			deleting_no_in_blacklist();
			clickBackButton(1);
			launch_an_app("phone");
			minWait();
			navigateToSettingsAndElement(Locators_CallSetting.Callscreening);
			minWait();
			clickBtn(Locators_CallSetting.Manageblacklist);
			minWait();
			addingno_in_blacklist_callscreening(refNum);
			minWait();
			clickBackButton(1);
			deleting_no_whitelist();

			//Adding contact no in whitelist
			addContactNumWhitelist(refNum);
			minWait();
			clickBackButton(4);
			clearcallhistory();
			minWait();
			 launch_an_app("contacts");
				//Delete the contact in contact app
		    deleteIfContactsPresent(sa);
			minWait();
			launch_an_app("contacts");
			minWait();
			setDefaultSavingAccount();
			//deleteIfContactsPresent(sa);
			minWait();
			//Adding contact for call screening
			add_contact_for_callscreening(data.get("name"),data.get("lastname"), refNum);
			minWait();
			make_Call_from_RefDev();
			customWait(6000);
			recieve_Call_PrimaryDev_O();
			customWait(6000);
			endCall_RefDevice();
			customWait(3000);
			//Validating calls and messages are  received from contacts on selecting "Allow only contacts"
			validate_calllog_contactcallreceived(sa);
			minWait();
			}
			else if(p_b_No.contains("-15.")){
				aDriver.pressKeyCode(AndroidKeyCode.HOME);
			}
		}
		sa.assertAll();
	}

	@Test(priority=15,dataProvider="XP8_Call_Settings", dataProviderClass=DataProviders.class)
	public void  XP8_TC_15_Call_Settings_Call_Screening_Screeningincomingcallsettingoption_Allowwhitelist(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException

	//Verify calls are  received from contacts on selecting "Allow white list"
	{
		SoftAssert sa = new SoftAssert();
		{
			APP_LOGS.info("===========XP8_Call_Settings_15===========");
			if (p_b_No.contains("-10.")||p_b_No.contains("-00.")||p_b_No.contains("-11.")||p_b_No.contains("-29.")||p_b_No.contains("-26.")) {
			launch_an_app("phone");
			minWait();
			navigateToSettingsAndElement(Locators_CallSetting.Callscreening);
			minWait();
			clickBtn(Locators_CallSetting.Manageblacklist);
			minWait();
			//Deleting all no in blacklist
			deleting_blacklist();
			minWait();
			launch_an_app("phone");
			minWait();
			navigateToSettingsAndElement(Locators_CallSetting.Callscreening);
			minWait();
			clickBtn(Locators_CallSetting.Managewhitelist);
			minWait();
			deleting_no_in_whitelist();
			launch_an_app("phone");
			minWait();
			//Enable and selecting allow white list in Screening incoming call settings
			enabling_Screeningincomingcalls_allowwhitelist();
			minWait();
			clickBtn(Locators_CallSetting.Manageblacklist);
			minWait();
			addingno_in_blacklist_callscreening(refNum);
			minWait();
			clickBackButton(1);
			minWait();
			clickBtn(Locators_CallSetting.Managewhitelist);
			minWait();
			addContactNumWhitelist(refNum);
			minWait();
			clickBackButton(4);
			//Add contact for call screening
			clearcallhistory();
			minWait();
			 launch_an_app("contacts");
			//Delete the contact in contact app
		    deleteIfContactsPresent(sa);
			minWait();
			launch_an_app("contacts");
			minWait();
			setDefaultSavingAccount();
			add_contact_for_callscreening(data.get("name"),data.get("lastname"), refNum);
			minWait();
			make_Call_from_RefDev();
			recieve_Call_PrimaryDev_O();
			customWait(6000);
			endCall_RefDevice();
			customWait(10000);
			clearRecentApps();
			//Validating calls are  received from contacts on selecting "Allow white list"
			validate_calllog_contactcallreceived(sa);
			minWait();
			}
			else if(p_b_No.contains("-15.")){
				aDriver.pressKeyCode(AndroidKeyCode.HOME);
			}
		}
		sa.assertAll();
	}

	@Test(priority=16,dataProvider="XP8_Call_Settings", dataProviderClass=DataProviders.class)
	public void  XP8_TC_16_Call_Settings_Call_Screening_Screeningoutgoingcalls(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException

	//Verify enabling/disabling Screening outgoing calls
	{
		SoftAssert sa = new SoftAssert();
		{
			APP_LOGS.info("===========XP8_Call_Settings_16===========");
			if (p_b_No.contains("-10.")||p_b_No.contains("-00.")||p_b_No.contains("-11.")||p_b_No.contains("-29.")||p_b_No.contains("-26.")) {
			clearRecentApps();
			launch_an_app("phone");

			//Validating  on enabling Screening outgoing calls
			validation_Screeningoutgoingcall_enabled(sa);
			minWait();
			clickBackButton(4);
			minWait();
			clearcallhistory();
			minWait();
			make_Call_from_PrmyDev();
			customWait(10000);
			endCall_PrmyDevice();
			minWait();
			//validating receiving calls from contact saved no
			validate_calllog_contactcallreceived(sa);
			minWait();
			//Validating screening outgoing call disabled
			launch_an_app("phone");
			navigateToSettingsAndElement(Locators_CallSetting.Callscreening);
			minWait();
			validation_Screeningoutgoingcall_disabled(sa);
			minWait();
			}
			else if(p_b_No.contains("-15.")){
			aDriver.pressKeyCode(AndroidKeyCode.HOME);
			}
		}
	}

	@Test(priority=17,dataProvider="XP8_Call_Settings", dataProviderClass=DataProviders.class)
	public void  XP8_TC_17_Call_Settings_Call_Screening_contactsScreeningoutgoingcalls(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException

	//Verify enabling/disabling Screening outgoing calls
	{
		SoftAssert sa = new SoftAssert();
		{
			APP_LOGS.info("===========XP8_Call_Settings_17===========");
			if (p_b_No.contains("-10.")||p_b_No.contains("-00.")||p_b_No.contains("-11.")||p_b_No.contains("-29.")||p_b_No.contains("-26.")) {
			clearRecentApps();
            launch_an_app("contacts");
			minWait();
			deleteIfContactsPresent(sa);
			minWait();
			launch_an_app("contacts");
			setDefaultSavingAccount();
			minWait();
			launch_an_app("phone");
			AddContactFromContactsPage(data.get("name"),data.get("lastname"),refNum);
			//Verify initiating calls to saved contacts on enabling Screening outgoing calls
			validation_initiatingcall_Screeningoutgoingcall_enabled(data.get("name"),data.get("lastname"), refNum,sa);
			minWait();
			make_Call_from_PrmyDev();
			customWait(6000);
			endCall_PrmyDevice();
			minWait();
			validate_calllog_contactcallreceived(sa);
			minWait();
			}
			else if(p_b_No.contains("-15.")){
				aDriver.pressKeyCode(AndroidKeyCode.HOME);
			}

		}
		sa.assertAll();
	}
	@Test(priority=18,dataProvider="XP8_Call_Settings", dataProviderClass=DataProviders.class)
	public void  XP8_TC_18_validate_Editedquickresponse_displayproperly_duringcallscreen(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException

	//To verify user edited quick response should display properly on the screen while sending during a call screen

	{
		SoftAssert sa = new SoftAssert();
		{
			APP_LOGS.info("===========XP8_Call_Settings_18===========");
			if (p_b_No.contains("-10.")||p_b_No.contains("-00.")||p_b_No.contains("-11.")||p_b_No.contains("-15.")||p_b_No.contains("-29.")||p_b_No.contains("-26.")) {
			clearRecentApps();
			delete_SMS(sa);
			launch_an_app("phone");
			navigateToSettingsAndElement(Locators_CallSetting.quickResponseOpt);
			minWait();
			//validateQuickResponses(data.get("textMessage"),sa); 
			Restore_default_QuickResponses();
			minWait();
			//Edit and validate quick response
			editAndValidateQuickResponse(data.get("textMessage"),sa);
			minWait();
			//editing quick responses are display properly in call screen
			Editedquickresponse_displayproperly_duringcallscreen();
			customWait(10000);
			customWait(10000);
			//Validating editing quick responses are display properly in call screen
			validate_Editedquickresponse_displayproperly_duringcallscreen(sa);
			minWait();
			clickBackButton(4);
			}
		}
		sa.assertAll();
	}
	@Test(priority=19,dataProvider="XP8_Call_Settings", dataProviderClass=DataProviders.class)
	public void XP8_TC_19_PostCondition_CallSettings(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException
	{
		APP_LOGS.info("===========XP8_Stability_004============");
		startAdbLog("XP8_Stability_004");
		SoftAssert sa3 = new SoftAssert();
		if (p_b_No.contains("-10.")||p_b_No.contains("-00.")) {
			
			delete_SMS(sa3);
			launch_an_app("phone");
			navigateToSettingsAndElement(Locators_CallSetting.quickResponseOpt);
			minWait();
			//validateQuickResponses(data.get("textMessage"),sa); 
			Restore_default_QuickResponses();
			minWait();
			clearcallhistory();
			launch_an_app("contacts");
			deleteIfContactsPresent(sa3);
			sleeptime_30sec();
			
		
		}else if(p_b_No.contains("-15.")) {
			launch_APP(Locators_CallSetting.MessagePlus);
			delete_SMS_O(sa3);
			launch_an_app("phone");
			navigateToSettingsAndElement(Locators_CallSetting.quickResponseOpt);
			minWait();
			//validateQuickResponses(data.get("textMessage"),sa); 
			Restore_default_QuickResponses();
			clearcallhistory_vzw();
			launch_an_app("contacts");
			deleteIfContactsPresent(sa3);
			sleeptime_30sec();
			
		}else if(p_b_No.contains("-26.")||p_b_No.contains("-29.")||p_b_No.contains("-11.")||p_b_No.contains("-31.")||p_b_No.contains("-18.")||p_b_No.contains("-12.")){
	       launch_APP(Locators_CallSetting.messages);
	       delete_SMS_SL(sa3);
	       launch_an_app("phone");
			navigateToSettingsAndElement(Locators_CallSetting.quickResponseOpt);
			minWait();
			//validateQuickResponses(data.get("textMessage"),sa); 
			Restore_default_QuickResponses();
			clearcallhistory();
			launch_an_app("contacts");
			deleteIfContactsPresent(sa3);
			deleteContactInSpeedDailSettings_post(sa3);
			sleeptime_30sec();
		}
		else {
			
			delete_SMS(sa3);
			clearcallhistory();
			 launch_an_app("phone");
			 navigateToSettingsAndElement(Locators_CallSetting.quickResponseOpt);
				minWait();
			//validateQuickResponses(data.get("textMessage"),sa); 
				Restore_default_QuickResponses();
				clearcallhistory();
				launch_an_app("contacts");
				deleteIfContactsPresent(sa3);
				sleeptime_30sec();
		}
	}
}