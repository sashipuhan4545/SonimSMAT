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
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTestInterruptedException;
import com.relevantcodes.extentreports.LogStatus;
import com.xp8.util.DataProviders;
import com.xp8.util.ExcelConstants;
import com.xp8.util.ExcelReader;
import com.xp8.util.Locators_BaseUtil;
import com.xp8.util.Locators_CallSetting;
import com.xp8.util.XP8_Call_Settings_Util;

import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class XP8_Call_Settings_Test extends XP8_Call_Settings_Util {
	public ExcelReader excel;
	Properties properties;
	public static ExtentTestInterruptedException testexception;


	@BeforeSuite
	public void beforeSuite() throws FileNotFoundException, InterruptedException, IOException, ParseException  
	{
		extent = new ExtentReports("src/test/resources/extentreport/XP8_Call_Settings_TestReport.html", true); //Provide Desired Report Directory Location and Name
		extent.loadConfig(new File(System.getProperty("user.dir")+"//ReportsConfig.xml"));
		extent.addSystemInfo("Appium", "1.2.7").addSystemInfo("Environment", "TEST");
		fetch_Devices_Details();	
	}

	@BeforeMethod()
	public  void beforeMethod(Method method) 
	{
		test = extent.startTest( (this.getClass().getSimpleName() +" :: "+  method.getName()),method.getName()); //Test Case Start Here
		test.assignAuthor("Farheen Taj"); //Test Script Author Name
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
		APP_LOGS.info("===========XP8_TC_01_Call_Settings============");
		clearRecentApps();
		launch_an_app("phone");
		navigate_to_call_settings();
	}

	@Test(priority=2,dataProvider="XP8_Call_Settings", dataProviderClass=DataProviders.class)
	public void XP8_TC_02_Call_Settings_ValidateSettings_and_Displayoption(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========XP8_TC_02_Call_Settings_ValidateSettings_and_Displayoption============");
		launch_an_app("contacts");
		deleteIfContactsPresent();
		launch_an_app("phone");
		validateAddContactFromContactsPage(data.get("name"),data.get("lastname"), data.get("phone"));
		add_Contact(data.get("Newname"),data.get("Newlastname"), data.get("Newphone"));
		add_Contact(data.get("Thirdname"),data.get("Thirdlastname"), data.get("Thirdphone"));

		validateSettingsAndDisplayOptions();

	}


	@Test(priority=3,dataProvider="XP8_Call_Settings", dataProviderClass=DataProviders.class)
	public void XP8_TC_03_Call_Settings_ValidateSounds_and_VibrationSuboption(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("=========== XP8_TC_03_Call_Settings_ValidateSounds_and_VibrationSuboption============");
		launch_an_app("phone");
		navigateToSettingsAndElement(Locators_CallSetting.soundsAndVibration);
		minWait();
		validatePhoneRingtone();
		minWait();
		validateSoundsAndVibrationSubOptions();
		minWait();
		clickBackButton(3);
		test.log(LogStatus.PASS, "Test case Status is PASS");
	}
	@Test(priority=4,dataProvider="XP8_Call_Settings", dataProviderClass=DataProviders.class)
	public void XP8_TC_04_Call_Settings_Validate_Quickresponses(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException
	{
		APP_LOGS.info("===========XP8_TC_04_Call_Settings_Validate_Quickresponses===========");
		//("XP8_PhoneDialer_25");
		launch_an_app("phone");
		navigateToSettingsAndElement(Locators_CallSetting.quickResponseOpt);
		minWait();
		validateQuickResponses(data.get("textMessage"));

		test.log(LogStatus.PASS, "Test case Status is PASS");
	}
	@Test(priority=5,dataProvider="XP8_Call_Settings", dataProviderClass=DataProviders.class)
	public void  XP8_TC_05_Call_Settings_Validate_Speeddialsettings(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException
	{
		APP_LOGS.info("===========XP8_Call_Settings_05===========");
		//("XP8_PhoneDialer_26");
		launch_an_app("phone");
		navigateToSettingsAndElement(Locators_CallSetting.speedDialSettingsOpt);
		minWait();
		assignSpeedDialNumber(data.get("phone"), data.get("name"));
		test.log(LogStatus.PASS, "Test case Status is PASS");
		clickBackButton(3);
	}
	@Test(priority=6,dataProvider="XP8_Call_Settings", dataProviderClass=DataProviders.class)
	public void  XP8_TC_06_Call_Settings_Call_Screening_black_white_list(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException
	{
		SoftAssert sa1 = new SoftAssert();{
			APP_LOGS.info("===========XP8_Call_Settings_06===========");
			//("XP8_PhoneDialer_26");
			launch_an_app("phone");
			navigateToSettingsAndElement(Locators_CallSetting.Callscreening);
			minWait();
			BlacklistincallScreening(data.get("phoneno"),data.get("phoneno2"));
			minWait();
			UnblockincallScreening(data.get("phone"));
			minWait();
			blockAndUnblockNumber(sa1);
			minWait();
			clickBackButton(4);
			//adding new number with + prefix to block list
			validateBlockAndUnblockNumber(data.get("status"),data.get("phoneno"),sa1);
			minWait();
			validate_blocknumber_with_values("+",sa1);
			minWait();
			//adding new number with +91 prefix to block list.
			validateBlockAndUnblockNumber(data.get("status"),data.get("phoneno2"),sa1);	
			minWait();
			validate_blocknumber_with_values("+91",sa1);
			minWait();
			// adding new number with 0 prefix to block list
			validateBlockAndUnblockNumber(data.get("status"),data.get("phoneno3"),sa1);
			minWait();
			validate_blocknumber_with_values("0",sa1);
			minWait();
			//Verify unblocking numbers
			deleting_no_in_blacklist();
			minWait();
			clickBackButton(1);
			//Adding maximum numbers to Black list
			for(int k=1;k<=10; k++){
				Addingno_in_blacklist_callscreening(data.get("phoneno"+k));
				minWait();
			}
			minWait();
			//Validate scrolling through blocked numbers
			for(int K=1;K<=10; K++){
				validatescrollingno_in_blacklist(data.get("phoneno"+K),K,sa1);
				minWait();
			}
			minWait();
			//Verify Cancel  options while adding number to Black list

			validate_cancel_option(data.get("phoneno"),sa1);
			minWait();
			// number field is displayed on selecting "ADD A NUMBER" option under Black List
			validate_addno_option_under_callsettings_blacklist(data.get("phoneno"),sa1);
			minWait();
			// number field is displayed on selecting "ADD A NUMBER" option under White List
			validate_addno_option_under_callsettings_Whitelist(data.get("phoneno"),sa1);
			minWait();
			//Verify removing numbers from white list
			for(int j=1; j<=10; j++){
				deleting_no_in_whitelist(data.get("phoneno"+j));
			}
			minWait();
			//Verify adding maximum numbers to White list
			for(int k=1;k<=10; k++){
				addContactNumWhitelist(data.get("phoneno"+k));
				minWait();
			}
			//Verify scrolling through white list numbers

			for(int K=1;K<=10; K++){
				validatescrollingno_in_whitelist(data.get("phoneno"+K),K,sa1);
				minWait();
			}
		}

	}
	@Test(priority=7,dataProvider="XP8_Call_Settings", dataProviderClass=DataProviders.class)
	public void  XP8_TC_07_Call_Settings_Call_Screening(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException
	{
		SoftAssert sa2 = new SoftAssert();{
			APP_LOGS.info("===========XP8_Call_Settings_07===========");
			//("XP8_PhoneDialer_26");
			launch_an_app("phone");
			navigateToSettingsAndElement(Locators_CallSetting.Callscreening);
			minWait();
			//the behavior  by pressing Back key once while adding number to the blocked list.
			validate_pressing_backkey_once(data.get("phoneno"),sa2);
			minWait();
			////the behavior  by pressing Back key twice while adding number to the blocked list.
			validate_pressing_backkey_twice(data.get("phoneno"),sa2);
			minWait();
			//the behavior  by pressing Home key (Short press) while adding number to the blocked list.
			validate_pressing_Shortpress_HomeKey(data.get("phoneno"),sa2);
			minWait();
			//the behavior  by pressing Home key (LONG PRESS)while adding number to the blocked list.
			validate_pressing_Longpress_HomeKey(data.get("phoneno"),sa2);
			minWait();
			//the behavior  by pressing Recent apps key while adding number to the blocked list
			validate_pressing_Recentsapp_Key(data.get("phoneno"),sa2);
			minWait();
			//"Screening incoming call Setting" option is highlighted (not greyed out)
			validate_screening_incoming_call_highlighted(sa2);
			minWait();
			// Call Screening option is present under call settings
			validate_Callscreening_under_Callsettings(sa2);
			minWait();

		}
		sa2.assertAll();

	}



	@Test(priority=8,dataProvider="XP8_Call_Settings", dataProviderClass=DataProviders.class)
	public void  XP8_TC_08_Call_Settings_Call_Screening_Screeningincomingcallsetting_option(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException

	//calls and message are received from blacklist no only on selecting screening incoming call setting option
	//Block black list is selected in screening incoming call setting option
	{
		SoftAssert sa3 = new SoftAssert();
		{
			APP_LOGS.info("===========XP8_Call_Settings_08===========");
			launch_an_app("phone");
			minWait();
		/*	Screeningincomingcalls_callscreening();
			minWait();
			Screeningincomingcallsetting_option2();
			minWait();
//			clickBackButton(4);
			//make_Call_from_RefDev();
			//recieve_Call_PrimaryDev_O();
			//endCall_RefDevice();
			//customWait(3000);
			//	make_Call_from_PrmyDev();
			//validateCallLog_Orio();

			/*launch_APP(Locators_CallSetting.MessagePlus);
			clearSMSPermissions();
			navigateTo_NewSMS_O();
			create_NewSMS(refNum, data.get("typeMessage"));
			clickOn_Send();
			validate_SentMessage_O(sa3) ;
			delete_SMS();*/
			sendSMS_fromRefDevice(data.get("Message"));
			//validate_RecievedMessage();
		}

		sa3.assertAll();
	}
	@Test(priority=9,dataProvider="XP8_Call_Settings", dataProviderClass=DataProviders.class)
	public void  XP8_TC_09_Call_Settings_block_contactapp(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException

	// blocking  numbers from Contact app

	{
		SoftAssert sa4 = new SoftAssert();
		{
			APP_LOGS.info("===========XP8_Call_Settings_09===========");
			clearRecentApps();
			launch_an_app("phone");
			minWait();
			navigateToSettingsAndElement(Locators_CallSetting.Callscreening);
			minWait();
			deleting_no_in_blacklist();
			minWait();
			clickBackButton(4);
			minWait();
			launch_an_app("contacts");
			minWait();
			blockno_in_contactapp(refNum);
			minWait();
			validate_blockno_in_blacklist(sa4);
		}
		sa4.assertAll();
	}

	@Test(priority=10,dataProvider="XP8_Call_Settings", dataProviderClass=DataProviders.class)
	public void  XP8_TC_10_Call_Settings_Unblock_contactapp(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException

	// unblocking  numbers from Contact app

	{
		SoftAssert sa5 = new SoftAssert();
		{
			APP_LOGS.info("===========XP8_Call_Settings_10===========");
			clearRecentApps();
			launch_an_app("phone");
			minWait();
			Unblockno_in_contactapp(refNum);
			minWait();
			clickBackButton(4);
			validate_Unblockno_in_blacklist(sa5);
			minWait();
		}
		sa5.assertAll();
	}
	@Test(priority=11,dataProvider="XP8_Call_Settings", dataProviderClass=DataProviders.class)
	public void  XP8_TC_11_Call_Settings_block_Unblock_Calldetails(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException

	// Verify blocking numbers from Call details
{
		SoftAssert sa6 = new SoftAssert();
		{
			APP_LOGS.info("===========XP8_Call_Settings_10===========");
			clearRecentApps();
			launch_an_app("phone");
			minWait();
			/*blockno_in_Calldetails();
			minWait();
			validate_blockno_in_blacklist(sa6);
			minWait();
			Unblockno_in_calldetails();
			minWait();
			validate_Unblockno_in_blacklist(sa6);
			minWait();
			validateBlockAndUnblockNumber(data.get("status"),("refNum"),sa6);
			minWait(); */
			validateCancel_option_calldialer();
			minWait();
		}
		sa6.assertAll();


}
}



