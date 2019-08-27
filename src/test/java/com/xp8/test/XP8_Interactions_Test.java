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
import com.xp5S.util.BaseUtil;
import com.xp5S.util.GetMethods;
import com.xp5S.util.appiumService;
import com.xp8.util.DataProviders;
import com.xp8.util.ExcelConstants;
import com.xp8.util.ExcelReader;
import com.xp8.util.Locators_BaseUtil;
import com.xp8.util.Locators_DeviceStability;
import com.xp8.util.Locators_Interactions;
import com.xp8.util.XP8_Interactions_Util;
import com.xp8.util.XP8_PhoneDialer_util;

import io.appium.java_client.android.AndroidKeyCode;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class XP8_Interactions_Test extends XP8_Interactions_Util{

	public ExcelReader excel;
	Properties properties;
	public static ExtentTestInterruptedException testexception;
	public int  itr =30;
	boolean value;

	@BeforeSuite
	public void beforeSuite() throws FileNotFoundException, InterruptedException,  IOException, ParseException, ParseException, ParseException, ParseException, ParseException, ParseException 
	{
		extent = new ExtentReports("src/test/resources/extentreport/XP8_Interactions_TestReport.html", true); //Provide Desired Report Directory Location and Name

		extent.loadConfig(new File(System.getProperty("user.dir")+"//ReportsConfig.xml"));
		extent.addSystemInfo("Build #",JsonFileReaderAndWriter.primaryDevFirmwareReader())
		.addSystemInfo("Product",JsonFileReaderAndWriter.primaryDevModelReader())
		.addSystemInfo("Operator", JsonFileReaderAndWriter.primaryDevOperatorReader());		
		fetch_Devices_Details();	

		try {
			appiumService.TOTAL_NUM_OF_TESTCASES=GetMethods.TotalTestcase("XP8_Interactions", this.getClass());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

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
	public void copyFilesToDevice() throws  IOException, ParseException, ParseException, ParseException, ParseException, ParseException, ParseException  {
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
		Locators_Interactions loc=new Locators_Interactions(aDriver);
		Locators_BaseUtil loc1 = new Locators_BaseUtil(aDriver);	
		PageFactory.initElements(new AppiumFieldDecorator(aDriver),loc);
		PageFactory.initElements(new AppiumFieldDecorator(aDriver),loc1);
		excel=new ExcelReader(ExcelConstants.XP5S_XL_PATH);	
	}

	//=====================================================Test cases===========================================================================

	@Test(priority=0,dataProvider="XP8_Interactions", dataProviderClass=DataProviders.class)
	public void XP8_Precondition_Interactions(Hashtable<String, String> data) throws InterruptedException, AWTException,  IOException, ParseException
	{

		SoftAssert sa=new SoftAssert();
		APP_LOGS.info("===========XP8_Interactions_000_Precondition============");
		startAdbLog("XP8_Interactions_000");
		if (p_b_No.contains("-10.")||p_b_No.contains("-00.")||p_b_No.contains("-15.")||p_b_No.contains("-11.")||p_b_No.contains("-29.")||p_b_No.contains("-12.")||p_b_No.contains("-26.")) {
			clearRecentApps();
			//Enabling mobiledata and wifi
			enable_mobiledata_wifi();
			aDriver.pressKeyCode(AndroidKeyCode.HOME);
			//Installing gaana
			Installing_gaana(sa);
			aDriver.pressKeyCode(AndroidKeyCode.HOME);
			//Installing skype
			Installing_skype(sa,data.get("skypephoneno"),data.get("skypetext"));
			aDriver.pressKeyCode(AndroidKeyCode.HOME);
			launch_an_app("contacts");
			//Delete the contact 
			deleteIfContactsPresent(sa);
			//Set default saving account
			launch_an_app("contacts");
			setDefaultSavingAccount();
			launch_an_app("messaging");
			enablemsg_notification(sa);

			sa.assertAll();
		}

	}


	@Test(priority=1,dataProvider="XP8_Interactions", dataProviderClass=DataProviders.class)
	public void XP8_Interactions_During_Voicecall(Hashtable<String, String> data) throws InterruptedException, AWTException,  IOException, ParseException
	{
		SoftAssert sa=new SoftAssert();
		APP_LOGS.info("===========XP8_Interactions_001_During_Voicecall============");
		startAdbLog("XP8_Interactions_001");
		if (p_b_No.contains("-10.")||p_b_No.contains("-00.")||p_b_No.contains("-15.")||p_b_No.contains("-11.")||p_b_No.contains("-29.")||p_b_No.contains("-12.")||p_b_No.contains("-26.")) {
			clearRecentApps();
			//Make call in primary device
			make_Call_from_PrmyDev();
			customWait(5000);
			//Receive call in reference device
			reciveCallInRefDevice(refNum, sa);
			aDriver.pressKeyCode(AndroidKeyCode.HOME);
			//During Voice call send sms
			launch_an_app("messaging");
			//create_NewSMS(refNum, data.get("typeMessage"));
			navigateTo_NewSMS_ATT();
			enterTextToInputField(Locators_Interactions.TO_Field_Text1_att,refNum);
		    aDriver.pressKeyCode(AndroidKeyCode.BACK);
		     System.out.println("Enter Text");
			enterText_MessageField(data.get("typeMessage"));
			clickOn_Send();
			validate_SentMessage(sa) ;
			aDriver.pressKeyCode(AndroidKeyCode.HOME);
			//During voice call create contact 
			launch_an_app("contacts");
			validate_createContactWithNameandPhone(data.get("name"),data.get("lastname"),data.get("phoneno"),sa);
			//During voice call receive sms
			sendSMS_fromRefDevice(data.get("typeMessage1"));
			System.out.println("reply for received msg");
			//During voice call reply for received sms
			//launch_an_app("messaging");
			reply_received_sms(data.get("typeMessage1"));
			clickBackButton(4);
			launch_an_app("messaging");
			Validate_reply_received_sms(sa);
			aDriver.pressKeyCode(AndroidKeyCode.HOME);
			//During voice call enable disable bluetooth
			enable_disable_BT();
			//During voice call enable disable wifi
			enable_disable_WiFi(); 
			//During voice call enable disable location
			enable_disable_location();
			//During voice call set alarm
			launch_APP(Locators_BaseUtil.clockApp);
			validate_set_alarm(sa);
			//During voice call set timer
			launch_APP(Locators_BaseUtil.clockApp);
			validate_set_timer(sa);
			launch_APP(Locators_Interactions.camera);
			//During voice call take photo
			taking_photo(sa);
			//During voice call take video
			take_video(sa);
			launch_APP(Locators_Interactions.photos);
			validate_gallery(sa);
			//During voice call access map
			launch_APP(Locators_Interactions.maps);
			validate_maps(sa);
			//During voice call use sound recorder
			launch_APP(Locators_Interactions.soundrecorder);
			validate_soundrecorder(sa);
			//During voice call access notification 
			//validate_accessnotification(sa);
			access_settingsmenu();
			//During voice call enable mobiledata and wifi
			//enable_mobiledata_wifi();
			aDriver.pressKeyCode(AndroidKeyCode.HOME);
			System.out.println("coming to gaana");
			//During voice call play music
			launch_APP(Locators_Interactions.gaana);
			Musicplay_gaana(sa);
			
			//During voice call browse
			launch_APP(Locators_Interactions.chrome);
			openbrowser_browse(sa,data.get("Web"));
			//During voice call use fmradio
			launch_an_app("fm");
			validate_fmradio(sa);
			aDriver.pressKeyCode(AndroidKeyCode.HOME);
			System.out.println("coming to skype");
			//During voice call skype chat
         	launch_APP(Locators_Interactions.skype);
			skype_chat(sa,data.get("skypephoneno"),data.get("skypetext"),data.get("typeMessage1"));
			//During voice call enable disable airplanemode
			checkairplanemode(sa);
			//endCall_PrmyDevice();
           clearRecentApps();


		}
		sa.assertAll();
	}

	@Test(priority=2,dataProvider="XP8_Interactions", dataProviderClass=DataProviders.class)
	public void XP8_Interactions_while_playingmusic(Hashtable<String, String> data) throws InterruptedException, AWTException,  IOException, ParseException
	{
		SoftAssert sa=new SoftAssert();
		APP_LOGS.info("===========XP8_Interactions_002_While_PlayingMusic============");
		startAdbLog("XP8_Interactions_002");
		if (p_b_No.contains("-10.")||p_b_No.contains("-00.")||p_b_No.contains("-15.")||p_b_No.contains("-11.")||p_b_No.contains("-29.")||p_b_No.contains("-12.")||p_b_No.contains("-26.")) {
			launch_an_app("contacts");
			deleteIfContactsPresent(sa);
			clearRecentApps();
			launch_APP(Locators_Interactions.gaana);
			Musicplay_gaana(sa);
			aDriver.pressKeyCode(AndroidKeyCode.HOME);
			make_Call_from_PrmyDev();
			customWait(3000);
			endCall_PrmyDevice();
			launch_an_app("messaging");
			//create_NewSMS(refNum, data.get("typeMessage"));
			navigateTo_NewSMS_ATT();
			enterTextToInputField(Locators_Interactions.TO_Field_Text1_att,refNum);
		    aDriver.pressKeyCode(AndroidKeyCode.BACK);
		     System.out.println("Enter Text");
			enterText_MessageField(data.get("typeMessage"));
			clickOn_Send();
			validate_SentMessage(sa) ;
			aDriver.pressKeyCode(AndroidKeyCode.HOME);
		
			//During voice call create contact 
			launch_an_app("contacts");
			validate_createContactWithNameandPhone(data.get("name"),data.get("lastname"),data.get("phoneno"),sa);
			//During voice call receive sms
			sendSMS_fromRefDevice(data.get("typeMessage1"));
			System.out.println("reply for received msg");
			//During voice call reply for received sms
			//launch_an_app("messaging");
			reply_received_sms(data.get("typeMessage1"));
			clickBackButton(4); 
			launch_an_app("messaging");
			Validate_reply_received_sms(sa);

			//During voice call enable disable bluetooth
			enable_disable_BT();
			//During voice call enable disable wifi
			enable_disable_WiFi(); 
			//During voice call enable disable location
			enable_disable_location();
			//During voice call set alarm
			launch_APP(Locators_BaseUtil.clockApp);
			validate_set_alarm(sa);
			//During voice call set timer
			launch_APP(Locators_BaseUtil.clockApp);
			validate_set_timer(sa);
			launch_APP(Locators_Interactions.camera);
			//During voice call take photo
			taking_photo(sa);
			//During voice call take video
			take_video(sa);
			launch_APP(Locators_Interactions.photos);
			validate_gallery(sa);
			//During voice call access map
			launch_APP(Locators_Interactions.maps);
			validate_maps(sa);
			//During voice call use sound recorder
			launch_APP(Locators_Interactions.soundrecorder);
			validate_soundrecorder_enabled(sa);
			//During voice call access notification 
			validate_accessnotification(sa);
			access_settingsmenu();
			//During voice call enable mobiledata and wifi
			enable_mobiledata_wifi();
			aDriver.pressKeyCode(AndroidKeyCode.HOME);
			//During voice call browse
			launch_APP(Locators_Interactions.chrome);
			openbrowser_browse(sa,data.get("Web"));
			
			//During voice call use fmradio
			launch_an_app("fm");
			validate_fmradio_on(sa);
			aDriver.pressKeyCode(AndroidKeyCode.HOME);
			fmradio_off();
			System.out.println("coming to skype");
			//During voice call skype chat
			launch_APP(Locators_Interactions.skype);
			skype_chat(sa,data.get("skypephoneno"),data.get("skypetext"),data.get("typeMessage1"));
			//During voice call enable disable airplanemode
			enable_disable_airplanemode();
			clearRecentApps();
		}
		sa.assertAll();
	}
	@Test(priority=3,dataProvider="XP8_Interactions", dataProviderClass=DataProviders.class)
	public void XP8_Interactions_while_playing_fmradio(Hashtable<String, String> data) throws InterruptedException, AWTException,  IOException, ParseException
	{
		SoftAssert sa=new SoftAssert();
		APP_LOGS.info("===========XP8_Interactions_003_While_PlayingFMradio============");
		startAdbLog("XP8_Interactions_003");
		if (p_b_No.contains("-10.")||p_b_No.contains("-00.")||p_b_No.contains("-15.")||p_b_No.contains("-11.")||p_b_No.contains("-29.")||p_b_No.contains("-12.")||p_b_No.contains("-26.")) {
			launch_an_app("contacts");
			deleteIfContactsPresent(sa);
			clearRecentApps();
			launch_an_app("fm");
			validate_fmradio_on(sa);
			make_Call_from_PrmyDev();
			customWait(3000);
			endCall_PrmyDevice();
			launch_an_app("messaging");
			//create_NewSMS(refNum, data.get("typeMessage"));
			navigateTo_NewSMS_ATT();
			enterTextToInputField(Locators_Interactions.TO_Field_Text1_att,refNum);
		    aDriver.pressKeyCode(AndroidKeyCode.BACK);
		     System.out.println("Enter Text");
			enterText_MessageField(data.get("typeMessage"));
			clickOn_Send();
			validate_SentMessage(sa) ;
			aDriver.pressKeyCode(AndroidKeyCode.HOME);
			launch_an_app("contacts");
			deleteIfContactsPresent(sa);
			//During voice call create contact 
			launch_an_app("contacts");
			validate_createContactWithNameandPhone(data.get("name"),data.get("lastname"),data.get("phoneno"),sa);
			//During voice call receive sms
			sendSMS_fromRefDevice(data.get("typeMessage1"));
			System.out.println("reply for received msg");
			//During voice call reply for received sms
			//launch_an_app("messaging");
			reply_received_sms(data.get("typeMessage1"));
	        clickBackButton(4);
			launch_an_app("messaging");
			Validate_reply_received_sms(sa);
			

			//During voice call enable disable bluetooth
			enable_disable_BT();
			//During voice call enable disable wifi
			enable_disable_WiFi(); 
			//During voice call enable disable location
			enable_disable_location();
			//During voice call set alarm
			launch_APP(Locators_BaseUtil.clockApp);
			validate_set_alarm(sa);
			//During voice call set timer
			validate_set_timer(sa);
			launch_APP(Locators_Interactions.camera);
			//During voice call take photo
			taking_photo(sa);
			//During voice call take video
			take_video(sa);
			launch_APP(Locators_Interactions.photos);
			validate_gallery(sa);
			//During voice call access map
			launch_APP(Locators_Interactions.maps);
			validate_maps(sa);
			//During voice call use sound recorder
			launch_APP(Locators_Interactions.soundrecorder);
			validate_soundrecorder_enabled(sa);
			//During voice call access notification 
			validate_accessnotification(sa);
			access_settingsmenu();
			//During voice call enable mobiledata and wifi
			enable_mobiledata_wifi();
			aDriver.pressKeyCode(AndroidKeyCode.HOME);
			//During voice call browse
			launch_APP(Locators_Interactions.chrome);
			openbrowser_browse(sa,data.get("Web"));
			
			//During voice call use fmradio
			launch_APP(Locators_Interactions.gaana);
			Musicplay_gaana(sa);
			aDriver.pressKeyCode(AndroidKeyCode.HOME);
			System.out.println("coming to skype");
			//During voice call skype chat
			launch_APP(Locators_Interactions.skype);
			skype_chat(sa,data.get("skypephoneno"),data.get("skypetext"),data.get("typeMessage1"));
			//During voice call enable disable airplanemode
			aDriver.pressKeyCode(AndroidKeyCode.HOME);
			fmradio_off();
			enable_disable_airplanemode();
			clearRecentApps();


		}
		sa.assertAll();
	}
	@Test(priority=4,dataProvider="XP8_Interactions", dataProviderClass=DataProviders.class)
	public void XP8_Interactions_while_user_browsers(Hashtable<String, String> data) throws InterruptedException, AWTException,  IOException, ParseException
	{
		SoftAssert sa=new SoftAssert();
		APP_LOGS.info("===========XP8_Interactions_004_While_user_browsers============");
		startAdbLog("XP8_Interactions_004");
		if (p_b_No.contains("-10.")||p_b_No.contains("-00.")||p_b_No.contains("-15.")||p_b_No.contains("-11.")||p_b_No.contains("-29.")||p_b_No.contains("-12.")||p_b_No.contains("-26.")) {
			launch_an_app("contacts");
			deleteIfContactsPresent(sa);
			clearRecentApps();
			enable_mobiledata_wifi();
			aDriver.pressKeyCode(AndroidKeyCode.HOME);
			//During voice call browse
			launch_APP(Locators_Interactions.chrome);
			openbrowser_browse(sa,data.get("Web"));
			aDriver.pressKeyCode(AndroidKeyCode.HOME);
			make_Call_from_PrmyDev();
			customWait(3000);
			endCall_PrmyDevice();
			launch_an_app("messaging");
			//create_NewSMS(refNum, data.get("typeMessage"));
			navigateTo_NewSMS_ATT();
			enterTextToInputField(Locators_Interactions.TO_Field_Text1_att,refNum);
		    aDriver.pressKeyCode(AndroidKeyCode.BACK);
		     System.out.println("Enter Text");
			enterText_MessageField(data.get("typeMessage"));
			clickOn_Send();
			validate_SentMessage(sa) ;
			aDriver.pressKeyCode(AndroidKeyCode.HOME);
			launch_an_app("contacts");
			deleteIfContactsPresent(sa);
			//During voice call create contact 
			launch_an_app("contacts");
			validate_createContactWithNameandPhone(data.get("name"),data.get("lastname"),data.get("phoneno"),sa);
			//During voice call receive sms
			sendSMS_fromRefDevice(data.get("typeMessage1"));
			System.out.println("reply for received msg");
			//During voice call reply for received sms
			//launch_an_app("messaging");
			reply_received_sms(data.get("typeMessage1"));
			clickBackButton(4);
			launch_an_app("messaging");
			Validate_reply_received_sms(sa);

			//During voice call enable disable bluetooth
			enable_disable_BT();
			//During voice call enable disable wifi
			enable_disable_WiFi(); 
			//During voice call enable disable location
			enable_disable_location();
			//During voice call set alarm
			launch_APP(Locators_BaseUtil.clockApp);
			validate_set_alarm(sa);
			//During voice call set timer
			validate_set_timer(sa);
			launch_APP(Locators_Interactions.camera);
			//During voice call take photo
			taking_photo(sa);
			//During voice call take video
			take_video(sa);
			launch_APP(Locators_Interactions.photos);
			validate_gallery(sa);
			//During voice call access map
			launch_APP(Locators_Interactions.maps);
			validate_maps(sa);
			//During voice call use sound recorder
			launch_APP(Locators_Interactions.soundrecorder);
			validate_soundrecorder_enabled(sa);
			//During voice call access notification 
			validate_accessnotification(sa);
			access_settingsmenu();
			//During voice call enable mobiledata and wifi
			enable_mobiledata_wifi();
			aDriver.pressKeyCode(AndroidKeyCode.HOME);
			launch_an_app("fm");
			validate_fmradio_on(sa);
			aDriver.openNotifications();
			aDriver.pressKeyCode(AndroidKeyCode.HOME);
			fmradio_off();
			//During voice call use fmradio
			launch_APP(Locators_Interactions.gaana);
			Musicplay_gaana(sa);
			aDriver.pressKeyCode(AndroidKeyCode.HOME);
			System.out.println("coming to skype");
			//During voice call skype chat
			launch_APP(Locators_Interactions.skype);
			skype_chat(sa,data.get("skypephoneno"),data.get("skypetext"),data.get("typeMessage1"));
			//During voice call enable disable airplanemode
			enable_disable_airplanemode();
			clearRecentApps();
		}
		sa.assertAll();
	}
	@Test(priority=5,dataProvider="XP8_Interactions", dataProviderClass=DataProviders.class)
	public void XP8_Interactions_while_sending_composing_sms(Hashtable<String, String> data) throws InterruptedException, AWTException,  IOException, ParseException
	{
		SoftAssert sa=new SoftAssert();
		APP_LOGS.info("===========XP8_Interactions_005_While_sending_composing_sms============");
		startAdbLog("XP8_Interactions_005");
		if (p_b_No.contains("-10.")||p_b_No.contains("-00.")||p_b_No.contains("-15.")||p_b_No.contains("-11.")||p_b_No.contains("-29.")||p_b_No.contains("-12.")||p_b_No.contains("-26.")) {
			launch_an_app("contacts");
			deleteIfContactsPresent(sa);
			clearRecentApps();
			launch_an_app("messaging");
			//create_NewSMS(refNum, data.get("typeMessage"));
			navigateTo_NewSMS_ATT();
			enterTextToInputField(Locators_Interactions.TO_Field_Text1_att,refNum);
		    aDriver.pressKeyCode(AndroidKeyCode.BACK);
		     System.out.println("Enter Text");
			enterText_MessageField(data.get("typeMessage"));
			clickOn_Send();
			validate_SentMessage(sa);
			aDriver.pressKeyCode(AndroidKeyCode.HOME);
			make_Call_from_PrmyDev();
			customWait(3000);
			endCall_PrmyDevice();
			launch_an_app("contacts");
			deleteIfContactsPresent(sa);
			//During voice call create contact 
			launch_an_app("contacts");
			validate_createContactWithNameandPhone(data.get("name"),data.get("lastname"),data.get("phoneno"),sa);
			//During voice call receive sms
			sendSMS_fromRefDevice(data.get("typeMessage1"));
			System.out.println("reply for received msg");
			//During voice call reply for received sms
			//launch_an_app("messaging");
			reply_received_sms(data.get("typeMessage1"));
			clickBackButton(4);
			launch_an_app("messaging");
			Validate_reply_received_sms(sa);
			enable_mobiledata_wifi();
			aDriver.pressKeyCode(AndroidKeyCode.HOME);
			//During voice call browse
			launch_APP(Locators_Interactions.chrome);
			openbrowser_browse(sa,data.get("Web"));
			
			aDriver.pressKeyCode(AndroidKeyCode.HOME);
			//During voice call enable disable bluetooth
			enable_disable_BT();
			//During voice call enable disable wifi
			enable_disable_WiFi(); 
			//During voice call enable disable location
			enable_disable_location();
			//During voice call set alarm
			launch_APP(Locators_BaseUtil.clockApp);
			validate_set_alarm(sa);
			//During voice call set timer
			validate_set_timer(sa);
			launch_APP(Locators_Interactions.camera);
			//During voice call take photo
			taking_photo(sa);
			//During voice call take video
			take_video(sa);
			launch_APP(Locators_Interactions.photos);
			validate_gallery(sa);
			//During voice call access map
			launch_APP(Locators_Interactions.maps);
			validate_maps(sa);
			//During voice call use sound recorder
			launch_APP(Locators_Interactions.soundrecorder);
			validate_soundrecorder_enabled(sa);
			//During voice call access notification 
			validate_accessnotification(sa);
			access_settingsmenu();
			//During voice call enable mobiledata and wifi
			enable_mobiledata_wifi();
			aDriver.pressKeyCode(AndroidKeyCode.HOME);
			launch_an_app("fm");
			validate_fmradio_on(sa);
			aDriver.openNotifications();
			aDriver.pressKeyCode(AndroidKeyCode.HOME);
			fmradio_off();
			//During voice call use fmradio
			launch_APP(Locators_Interactions.gaana);
			Musicplay_gaana(sa);
			aDriver.pressKeyCode(AndroidKeyCode.HOME);
			System.out.println("coming to skype");
			//During voice call skype chat
			launch_APP(Locators_Interactions.skype);
			skype_chat(sa,data.get("skypephoneno"),data.get("skypetext"),data.get("typeMessage1"));
			//During voice call enable disable airplanemode
			enable_disable_airplanemode();
			clearRecentApps();

		}
		sa.assertAll();
	}
	@Test(priority=6,dataProvider="XP8_Interactions", dataProviderClass=DataProviders.class)
	public void XP8_Interactions_while_streaming_youtube_videos(Hashtable<String, String> data) throws InterruptedException, AWTException,  IOException, ParseException
	{
		SoftAssert sa=new SoftAssert();
		APP_LOGS.info("===========XP8_Interactions_006_While_streaming_youtube_videos============");
		startAdbLog("XP8_Interactions_006");
		if (p_b_No.contains("-10.")||p_b_No.contains("-00.")||p_b_No.contains("-15.")||p_b_No.contains("-11.")||p_b_No.contains("-29.")||p_b_No.contains("-12.")||p_b_No.contains("-26.")) {
			launch_an_app("contacts");
			deleteIfContactsPresent(sa);
			clearRecentApps();
			enable_mobiledata_wifi();
			launch_APP(Locators_Interactions.youtube);
			youtube_streaming_videos();
			copy_youtubelink(sa);
			launch_APP(Locators_Interactions.chrome);
			openbrowser_browse(sa,data.get("Web"));
			share_browser_page(refNum, sa);
			clickOn_Send();
			clickBackButton(4);
			validate_chrome_sharemsg(sa);
			sendSMS_fromRefDevice(data.get("typeMessage1"));
			System.out.println("reply for received msg");
			reply_received_sms(data.get("typeMessage1"));
			clickBackButton(4);
			launch_an_app("messaging");
			Validate_reply_received_sms(sa);
			validate_accessnotification(sa);
			aDriver.pressKeyCode(AndroidKeyCode.HOME);
			enable_disable_BT();
			enable_disable_WiFi();
			enable_disable_location();
			launch_APP(Locators_Interactions.skype);
			skype_chat(sa,data.get("skypephoneno"),data.get("skypetext"),data.get("typeMessage1"));
			launch_APP(Locators_Interactions.gaana);
			Musicplay_gaana(sa);
			clearRecentApps();
			aDriver.pressKeyCode(AndroidKeyCode.HOME);
			launch_an_app("fm");
			validate_fmradio_on(sa);
			aDriver.pressKeyCode(AndroidKeyCode.HOME);
			fmradio_off();
			launch_an_app("camera");
			taking_photo(sa);
			launch_APP(Locators_Interactions.photos);
			validate_gallery(sa);
			launch_APP(Locators_Interactions.filemanager);
			aDriver.pressKeyCode(AndroidKeyCode.HOME);
			launch_APP(Locators_Interactions.soundrecorder);
			validate_soundrecorder_enabled(sa);
			enable_disable_airplanemode();
			clearRecentApps();

		}
		sa.assertAll();
	}
	@Test(priority=7,dataProvider="XP8_Interactions", dataProviderClass=DataProviders.class)
	public void XP8_Interactions_while_VOIP_Chat(Hashtable<String, String> data) throws InterruptedException, AWTException,  IOException, ParseException
	{
		SoftAssert sa=new SoftAssert();
		APP_LOGS.info("===========XP8_Interactions_007_While_VOIP_Chat============");
		startAdbLog("XP8_Interactions_007");
		if (p_b_No.contains("-10.")||p_b_No.contains("-00.")||p_b_No.contains("-15.")||p_b_No.contains("-11.")||p_b_No.contains("-29.")||p_b_No.contains("-12.")||p_b_No.contains("-26.")) {
			launch_an_app("contacts");
			deleteIfContactsPresent(sa);
			clearRecentApps();
			launch_APP(Locators_Interactions.skype);
			skype_chat(sa,data.get("skypephoneno"),data.get("skypetext"),data.get("typeMessage1"));
			sendSMS_fromRefDevice(data.get("typeMessage1"));
			System.out.println("reply for received msg");
			//During taking photo reply for received sms
			//launch_an_app("messaging");
			reply_received_sms(data.get("typeMessage1"));
			clickBackButton(4);
			launch_an_app("messaging");
			Validate_reply_received_sms(sa);
			validate_accessnotification(sa);
			aDriver.pressKeyCode(AndroidKeyCode.HOME);
			enable_disable_BT();
			enable_disable_WiFi();
			enable_disable_location();
			launch_APP(Locators_Interactions.gaana);
			Musicplay_gaana(sa);
			aDriver.pressKeyCode(AndroidKeyCode.HOME);
			launch_an_app("fm");
			validate_fmradio_on(sa);
			aDriver.pressKeyCode(AndroidKeyCode.HOME);
			fmradio_off();
			launch_an_app("camera");
			taking_photo(sa);
			launch_APP(Locators_Interactions.photos);
			validate_gallery(sa);
			launch_APP(Locators_Interactions.filemanager);
			aDriver.pressKeyCode(AndroidKeyCode.HOME);
			launch_APP(Locators_Interactions.soundrecorder);
			validate_soundrecorder_enabled(sa);
			launch_APP(Locators_Interactions.skype);
			skype_call();
			enable_disable_airplanemode();
			clearRecentApps();

		}
		sa.assertAll();
	}
	@Test(priority=8,dataProvider="XP8_Interactions", dataProviderClass=DataProviders.class)
	public void XP8_Interactions_while_adding_contact(Hashtable<String, String> data) throws InterruptedException, AWTException,  IOException, ParseException
	{
		SoftAssert sa=new SoftAssert();
		APP_LOGS.info("===========XP8_Interactions_008_While_adding_contact============");
		startAdbLog("XP8_Interactions_008");
		if (p_b_No.contains("-10.")||p_b_No.contains("-00.")||p_b_No.contains("-15.")||p_b_No.contains("-11.")||p_b_No.contains("-29.")||p_b_No.contains("-12.")||p_b_No.contains("-26.")) {
			
			launch_an_app("contacts");
			deleteIfContactsPresent(sa);
			//During voice call create contact 
			launch_an_app("contacts");
			validate_createContactWithNameandPhone(data.get("name"),data.get("lastname"),data.get("phoneno"),sa);
			sendSMS_fromRefDevice(data.get("typeMessage1"));
			System.out.println("reply for received msg");
			//During taking photo reply for received sms
			//launch_an_app("messaging");
			reply_received_sms(data.get("typeMessage1"));
			clickBackButton(4);
			launch_an_app("messaging");
			Validate_reply_received_sms(sa);
			validate_accessnotification(sa);
			enable_disable_BT();
			enable_disable_WiFi();
			enable_disable_location();
			enable_disable_data();
			launch_an_app("camera");
			taking_photo(sa);
			launch_APP(Locators_Interactions.skype);
			skype_chat(sa,data.get("skypephoneno"),data.get("skypetext"),data.get("typeMessage1"));
			launch_APP(Locators_Interactions.gaana);
			Musicplay_gaana(sa);
			aDriver.pressKeyCode(AndroidKeyCode.HOME);
			launch_an_app("fm");
			validate_fmradio_on(sa);
			aDriver.pressKeyCode(AndroidKeyCode.HOME);
			fmradio_off();
			enable_disable_airplanemode();
			clearRecentApps();
		}
		sa.assertAll();
	}
	@Test(priority=9,dataProvider="XP8_Interactions", dataProviderClass=DataProviders.class)
	public void XP8_Interactions_while_capturing_photo(Hashtable<String, String> data) throws InterruptedException, AWTException,  IOException, ParseException
	{
		SoftAssert sa=new SoftAssert();
		APP_LOGS.info("===========XP8_Interactions_009_While_capturing_photo============");
		startAdbLog("XP8_Interactions_009");
		if (p_b_No.contains("-10.")||p_b_No.contains("-00.")||p_b_No.contains("-15.")||p_b_No.contains("-11.")||p_b_No.contains("-29.")||p_b_No.contains("-12.")||p_b_No.contains("-26.")) {
			launch_an_app("contacts");
			deleteIfContactsPresent(sa);
			clearRecentApps();
			launch_an_app("camera");
			taking_photo(sa);
			sendSMS_fromRefDevice(data.get("typeMessage1"));
			System.out.println("reply for received msg");
			//During taking photo reply for received sms
			//launch_an_app("messaging");
			reply_received_sms(data.get("typeMessage1"));
		    clickBackButton(4);
			launch_an_app("messaging");
			Validate_reply_received_sms(sa);
			validate_accessnotification(sa);
			aDriver.pressKeyCode(AndroidKeyCode.HOME);
			enable_disable_BT();
			enable_disable_WiFi();
			enable_disable_location();
			launch_APP(Locators_Interactions.skype);
			skype_chat(sa,data.get("skypephoneno"),data.get("skypetext"),data.get("typeMessage1"));
			launch_APP(Locators_Interactions.gaana);
			Musicplay_gaana(sa);
			aDriver.pressKeyCode(AndroidKeyCode.HOME);
			launch_an_app("fm");
			validate_fmradio_on(sa);
			aDriver.pressKeyCode(AndroidKeyCode.HOME);
			fmradio_off();
			enable_disable_data();
			launch_APP(Locators_Interactions.photos);
			share_photos(sa);
			aDriver.pressKeyCode(AndroidKeyCode.HOME);
			enable_disable_airplanemode();
			clearRecentApps();
			

		}
		sa.assertAll();
	}
	@Test(priority=10,dataProvider="XP8_Interactions", dataProviderClass=DataProviders.class)
	public void XP8_Interactions_PostConditions(Hashtable<String, String> data) throws InterruptedException, AWTException,  IOException, ParseException
	{
		SoftAssert sa=new SoftAssert();
		APP_LOGS.info("===========XP8_Interactions_019_Interactions_Postcondition============");
		startAdbLog("XP8_Interactions_009");
		if (p_b_No.contains("-10.")||p_b_No.contains("-00.")||p_b_No.contains("-15.")||p_b_No.contains("-11.")||p_b_No.contains("-29.")||p_b_No.contains("-12.")||p_b_No.contains("-26.")) {
			clearRecentApps();
			Runtime.getRuntime().exec("adb -s "+p_Id+" uninstall com.skype.raider");
			customWait(5000);
			Runtime.getRuntime().exec("adb -s "+p_Id+" uninstall com.gaana");
			customWait(5000);
			launch_an_app("contacts");
			//Delete the contact 
			deleteIfContactsPresent(sa);
			launch_APP(Locators_Interactions.soundrecorder);
			delete_soundrecorder_list();
			launch_an_app("messaging");
			delete_msg();
			

}
		sa.assertAll();
		
	}
	
}
