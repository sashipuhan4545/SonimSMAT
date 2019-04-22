
package com.xp8.test;

import java.awt.AWTException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.util.Hashtable;
import java.util.Properties;
import java.util.Timer;
import java.util.TimerTask;

import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.LogStatus;
import com.xp5S.util.GetMethods;
import com.xp5S.util.appiumService;
import com.xp8.util.DataProviders;
import com.xp8.util.ExcelConstants;
import com.xp8.util.ExcelReader;
import com.xp8.util.Locators_BaseUtil;
import com.xp8.util.Locators_XP8_Sanity;
import com.xp8.util.XP8_Sanity_Util;

import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class XP8_Dev_Sanity_Test_O extends XP8_Sanity_Util {

	public ExcelReader excel;
	Properties properties;
	public Timer timer;

	@BeforeSuite
	public void beforeSuite() throws FileNotFoundException, InterruptedException, IOException, ParseException {
		extent = new ExtentReports("src/test/resources/extentreport/XP8_Dev_Sanity_Test_O.html", true);
		extent.loadConfig(new File("src/test/resources/StorageFile/ReportsConfig.xml"));
		extent.addSystemInfo("Appium", "1.8.1").addSystemInfo("Environment", "TEST");
		fetch_Devices_Details();
		

		try {

			appiumService.TOTAL_NUM_OF_TESTCASES=GetMethods.TotalTestcase("XP8_", this.getClass());

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@BeforeTest
	public void setUpSys() throws FileNotFoundException, MalformedURLException, InterruptedException, AWTException {
		properties=loadDriverAndProperties();
		Locators_XP8_Sanity loc1 = new Locators_XP8_Sanity(aDriver);
		Locators_BaseUtil loc2   = new Locators_BaseUtil(aDriver);
		PageFactory.initElements(new AppiumFieldDecorator(aDriver), loc1);
		PageFactory.initElements(new AppiumFieldDecorator(aDriver), loc2);
		excel = new ExcelReader(ExcelConstants.XP5S_XL_PATH);
	}

	@BeforeTest
	public void timer() {
		timer = new Timer();
		timer.schedule(new TimerTask() {

			@Override
			public void run() {
				if(isElementExist(Locators_XP8_Sanity.batteryFullorAppKey)) {
					clickBtn(Locators_XP8_Sanity.OK);
				}				
			}
		}, 0, 10*(10*1));
	}	

	@BeforeMethod
	public void beforeMethod(Method m) {
		test = extent.startTest(this.getClass().getSimpleName()+" :: "+m.getName(),m.getName());
	}

	@AfterMethod
	public void afterMethod(ITestResult result, Method m) throws IOException, InterruptedException {
		if (result.getStatus()==ITestResult.FAILURE) {
			String ss_path=captureScreenshot(m.getName());
			test.addScreenCapture(ss_path);
			test.log(LogStatus.FAIL,result.getThrowable());
			//clearRecentApps_O();
		}
		extent.endTest(test);
		extent.flush();
	}

	



	//================================================== Test Scripts ==================================================


	//======================================================== Test Scripts ========================================================

	

	@Test(priority=1,dataProvider="XP8DevSanity", dataProviderClass=DataProviders.class)
	public void XP8_Sanity_01_Check_Able_to_Change_NetworkType(Hashtable<String, String> dt) throws InterruptedException, AWTException, IOException, ParseException {

		APP_LOGS.info("====================== XP8_Dev_Sanity_01 =======================");
		acceptPlayprotect();
		unlock_Phone();
		clearRecentApps_O();
		launch_APP(Locators_XP8_Sanity.settings);
		precondition_O();
		if (!p_b_No.contains("-10")) {
			launch_APP(Locators_XP8_Sanity.settings);
			clickOn_Networks_and_Internet();
			scrollToText("Mobile network");
			minWait();
			clickBtn(Locators_XP8_Sanity.down_Triangle);
			minWait();
			scrollToText("Preferred network type");
			minWait();
			scrollToTextContains("LTE");
			minWait();
			validate_Locators1(Locators_XP8_Sanity.PreferrednetworkType_LTE);
			scrollToText("Preferred network type");
			minWait();
			scrollToTextContains("3G");
			minWait();
			validate_Locators1(Locators_XP8_Sanity.PreferrednetworkType_3G);
			scrollToText("Preferred network type");
			minWait();
			scrollToTextContains("2G");
			minWait();
			validate_Locators1(Locators_XP8_Sanity.PreferrednetworkType_2G);
		}	else {
			test.log(LogStatus.FAIL, "NOT Applicable for AT&T");
		}
	}

	@Test(priority=2,dataProvider="XP8DevSanity", dataProviderClass=DataProviders.class)
	public void XP8_Sanity_02_IMS_Registration_Status(Hashtable<String, String> dt) throws InterruptedException, AWTException, IOException, ParseException {

		APP_LOGS.info("====================== XP8_Dev_Sanity_02 =======================");
		try {
			launch_APP(Locators_XP8_Sanity.settings);
			clickOn_Networks_and_Internet();
			scrollToText("Mobile network");
			minWait();
			clickBtn(Locators_XP8_Sanity.down_Triangle);
			minWait();
			scrollToText("Preferred network type");
			minWait();
			scrollToTextContains("LTE");
			minWait();
		} catch (Exception e) {
		}
		String fN = startRIL_Log();
		launch_APP(Locators_XP8_Sanity.settings);
		clickOn_Networks_and_Internet();
		scrollTo("Airplane mode");
		ON_Switch("Airplane mode");minWait();
		clickBtn(Locators_XP8_Sanity.OK);minWait();
		customWait(4000);
		OFF_Switch("Airplane mode");
		customWait(8000);
		validate_RIL_Logs(fN, "RIL_REQUEST_IMS_REGISTRATION_STATE {1, 1} [SUB0]","Failure may also be because Operator is NOT IMS Registered(NOT VoLTE).");
	}

	@Test(priority=3,dataProvider="XP8DevSanity",dataProviderClass=DataProviders.class)
	public void XP8_Sanity_03_Mobile_Data_EnableDisable_from_Quick_Setting(Hashtable<String, String> dt) throws AWTException, InterruptedException, IOException {

		APP_LOGS.info("====================== XP8_Dev_Sanity_03 =======================");
		launch_APP(Locators_XP8_Sanity.settings);
		clickOn_Networks_and_Internet();
		wiFi_OFF();	// Used Contains Text.
		scrollToText("Mobile network");
		OFF_Switch("data");
		clickBtn(Locators_XP8_Sanity.OK);
		swipe_NotificationBar();
		swipe_QuickPanel_SecondPage();
		enable_Shortcuts_In_QuickPanel(Locators_XP8_Sanity.mobileData_OffState_QuickPanel_O);
		launch_APP(Locators_XP8_Sanity.chrome);
		clearChromePermission();
		clear_SearchBox();
		validate_And_BrowseIn_Chrome(dt.get("url"), Locators_XP8_Sanity.googleCoIn_Text, Locators_XP8_Sanity.google_Logo, Locators_XP8_Sanity.google_Logo);
		swipe_NotificationBar();
		swipe_QuickPanel_SecondPage();
		disable_Shortcuts_QuickPanel(Locators_XP8_Sanity.mobileData_OnState_QuickPanel_O);
		pressKeyCode(22);pressKeyCode(22);
		pressKeyCode(23);
		if (p_b_No.contains("-15.")||p_b_No.contains("-00.")) {
			pressKeyCode(22);
			pressKeyCode(22);
			pressKeyCode(23);
		} else if (p_b_No.contains("-10.")) {
			minWait();
			Runtime.getRuntime().exec("adb -s "+p_Id+" shell input tap 703 1212");
			minWait();
		}
		launch_APP(Locators_XP8_Sanity.chrome);
		clear_SearchBox();
		validate_MobileData_Disable_O();
		clickOnHomeBtn();minWait();
		clickBtn(Locators_XP8_Sanity.OK_2);
		clickOnHomeBtn();
	}

	@Test(priority=4,dataProvider="XP8DevSanity", dataProviderClass=DataProviders.class)
	public void XP8_Sanity_04_Verify_OperatorName_LockScreen_And_HomeScreen(Hashtable<String, String> dt) throws InterruptedException, AWTException, IOException {

		APP_LOGS.info("====================== XP8_Dev_Sanity_04 =======================");
		launch_APP(Locators_XP8_Sanity.settings);
		scrollToText("Security & location");
		scrollToText("Screen lock");minWait();
		clickBtn(Locators_XP8_Sanity.swipeLock);
		launch_APP(Locators_XP8_Sanity.settings);
		clickOn_Networks_and_Internet();
		scrollToText("Mobile network");
		minWait();
		clickBtn(Locators_XP8_Sanity.down_Triangle);
		minWait();
		scrollToText("Preferred network type");
		minWait();
		scrollToTextContains("LTE");
		launch_APP(Locators_XP8_Sanity.settings);
		clickOn_Networks_and_Internet();
		scrollToText("Mobile network");
		minWait();
		clickBtn(Locators_XP8_Sanity.down_Triangle);
		customWait(2000);
		aDriver.swipe(540, 1700, 540, 1300, 1000);
		scrollToText("Access Point Names");
		validate_APN_Diaplay();
		validate_OperatorName_Lockscreen();
		unlock_Phone();
	}

	@Test(priority=5,dataProvider="XP8DevSanity", dataProviderClass=DataProviders.class)
	public void XP8_Sanity_05_Verify_Enable_And_Disable_FlightMode(Hashtable<String, String> dt) throws InterruptedException, AWTException, IOException, ParseException {

		APP_LOGS.info("===================== XP8_Dev_Sanity_05 =======================");
		launch_APP(Locators_XP8_Sanity.settings);
		clickOn_Networks_and_Internet();
		scrollTo("Airplane mode");
		ON_Switch("Airplane mode");
		clickBtn(Locators_XP8_Sanity.OK);
		launch_APP(Locators_XP8_Sanity.phone);
		dailCallUsingDailPad(refNum);
		validate_Airplane_Enable(refNum);
		launch_APP(Locators_XP8_Sanity.settings);
		clickOn_Networks_and_Internet();
		scrollTo("Airplane mode");
		OFF_Switch("Airplane mode");
		launch_APP(Locators_XP8_Sanity.phone);
		dailCallUsingDailPad(refNum);
		validate_Airplane_Disable(refNum);
		end_Call();
	}

	@Test(priority=6,dataProvider="XP8DevSanity", dataProviderClass= DataProviders.class)
	public void XP8_Sanity_06_Launch_all_Apps_Present_in_MenuTray(Hashtable<String, String> dt) throws InterruptedException, IOException {

		APP_LOGS.info("===================== XP8_Dev_Sanity_06 ======================");
		launch_APP(Locators_XP8_Sanity.settings);
		clickOn_Networks_and_Internet();
		scrollTo("Airplane mode");
		OFF_Switch("Airplane mode");
		setUp_And_Enable_WiFi();

		WebDriverWait wt = new WebDriverWait(aDriver, 30);

		launch_APP(Locators_XP8_Sanity.calculator);
		validate_Locators(Locators_XP8_Sanity.equal);
		launch_APP(Locators_XP8_Sanity.Calendar);
		validate_Locators(Locators_XP8_Sanity.google_Calendar);
		launch_APP(Locators_XP8_Sanity.camera);
		clearCameraPermission();
		validate_Locators(Locators_XP8_Sanity.capturePicture);
		launch_APP(Locators_XP8_Sanity.chrome);
		customWait(4000); 
		validate_Locators(Locators_XP8_Sanity.ACCEPTCONTINUE);
		launch_APP(Locators_XP8_Sanity.clock);
		validate_Locators(Locators_XP8_Sanity.CLOCK);
		launch_APP(Locators_XP8_Sanity.contacts);
		validate_Locators(Locators_XP8_Sanity.add_NewContact);
		launch_APP(Locators_XP8_Sanity.downloads);
		validate_Locators(Locators_XP8_Sanity.downloads);
		launch_APP(Locators_XP8_Sanity.duo);
		validate_Locators(Locators_XP8_Sanity.High_quality_video_calling);
		//launch_APP(Locators_XP8_Sanity.fileManager);
		//clearAllow();
		//validate_Locators1(Locators_XP8_Sanity.material_toolbar_FileManager);
		launch_APP(Locators_XP8_Sanity.fmRadio);
		validate_Locators(Locators_XP8_Sanity.fm_OnOff_Button);
		clickBtn(Locators_XP8_Sanity.fm_OnOff_Button);
		launch_APP(Locators_XP8_Sanity.gmail);
		validate_Locators(Locators_XP8_Sanity.Welcome_to_Gmail);
		launch_APP(Locators_XP8_Sanity.google);
		clickBtn(Locators_XP8_Sanity.NO_THANKS1);
		validate_Locators(Locators_XP8_Sanity.Search_Google);		
		launch_APP(Locators_XP8_Sanity.keep); minWait();
		try { wt.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[contains(@text,'Checking info')]"))); } catch (Exception e) {}
		customWait(2000);
		validate_Locators(Locators_XP8_Sanity.headingText_Google);
		launch_APP(Locators_XP8_Sanity.maps);
		validate_Locators(Locators_XP8_Sanity.Make_it_yourMap);
		launch_APP(Locators_XP8_Sanity.phone);
		validate_Locators(Locators_XP8_Sanity.dailerPad);
		launch_APP(Locators_XP8_Sanity.photos);
		validate_Locators(Locators_XP8_Sanity.googlePhotos_uses_FaceGrouping);
		launch_APP(Locators_XP8_Sanity.PlayMovies_TV);
		try {wt.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@text='GET STARTED']")));	} catch (Exception e) {	}
		validate_Locators(Locators_XP8_Sanity.welcome_title_PlayMoviesTV);
		launch_APP(Locators_XP8_Sanity.PlayMusic);
		customWait(4000);
		validate_Locators(Locators_XP8_Sanity.ToUse_GooglePlayMusic);
		//launch_APP(Locators_XP8_Sanity.PlayStore);
		//wt.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[contains(@text,'Checking info')]")));
		//validate_Locators(Locators_XP8_Sanity.Use_your_Google_account);
		launch_APP(Locators_XP8_Sanity.Security);
		validate_Locators(Locators_XP8_Sanity.Please_choose_follow);
		launch_APP(Locators_XP8_Sanity.settings);
		validate_Locators(Locators_XP8_Sanity.search_Settings);
		launch_APP(Locators_XP8_Sanity.sonimScout);
		validate_Locators(Locators_XP8_Sanity.scout_Heading);
		launch_APP(Locators_XP8_Sanity.soundRecorder);
		validate_Locators(Locators_XP8_Sanity.recordButton);
		launch_APP(Locators_XP8_Sanity.Voice_Activation);
		clearAllow();
		validate_Locators(Locators_XP8_Sanity.when_you_turn_On_Voice_Activation);
		launch_APP(Locators_XP8_Sanity.WiPowerAgent);
		validate_Locators(Locators_XP8_Sanity.Hello_from_WiPower);
		launch_APP(Locators_XP8_Sanity.youTube);
		clickBtn(Locators_XP8_Sanity.NOT_NOW);
		validate_Locators(Locators_XP8_Sanity.youtube_Logo);
		if (p_b_No.contains("-10.")) {
			launch_APP(Locators_XP8_Sanity.drive);
			validate_Locators(Locators_XP8_Sanity.with_your_GoogleAccount);
			launch_APP(Locators_XP8_Sanity.messaging);
			clearSMSPermissions();
			validate_Locators(Locators_XP8_Sanity.add_NewSMS);
			//launch_APP(Locators_XP8_Sanity.Device_Help);
			//validate_Locators(Locators_XP8_Sanity.add_NewSMS);
			launch_APP(Locators_XP8_Sanity.Visual_Voicemail);
			validate_Locators(Locators_XP8_Sanity.Play_messages_in_any_order);
		} else if(p_b_No.contains("-00.")) {
			launch_APP(Locators_XP8_Sanity.drive);
			validate_Locators(Locators_XP8_Sanity.Use_your_Google_account);
			launch_APP(Locators_XP8_Sanity.messaging);
			clearSMSPermissions();
			validate_Locators(Locators_XP8_Sanity.add_NewSMS);
			//launch_APP(Locators_XP8_Sanity.QTI_Logkit);
			//customWait(5000);   clickBtn(Locators_XP8_Sanity.CONTINUE);
			//validate_Locators(Locators_XP8_Sanity.Recent_captures_and_events);
		} else if(p_b_No.contains("-15.")) {
			launch_APP(Locators_XP8_Sanity.drive);
			validate_Locators(Locators_XP8_Sanity.a_SafePlace_for_all_your_files);
			launch_APP(Locators_XP8_Sanity.MessagePlus);
			customWait(3000);
			validate_Locators(Locators_XP8_Sanity.StartMessaging);
			launch_APP(Locators_XP8_Sanity.Hum);
			validate_Locators(Locators_XP8_Sanity.welcome_To_Hum);
			launch_APP(Locators_XP8_Sanity.Music);
			clearAllow();
			validate_Locators(Locators_XP8_Sanity.Artists);
			launch_APP(Locators_XP8_Sanity.MyVerizon);
			clearAllow();  clickBtn(Locators_XP8_Sanity.ADVANCE);
			clearAllow(); 
			try { wt.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(@text,'Get Started')]"))); } catch (Exception e) {}
			validate_Locators(Locators_XP8_Sanity.Get_Started);
			launch_APP(Locators_XP8_Sanity.OmaDMTest);
			validate_Locators(Locators_XP8_Sanity.SDMFOTA);
			launch_APP(Locators_XP8_Sanity.SecurityAndPrivacy);
			validate_Locators(Locators_XP8_Sanity.Continue);
			//launch_APP(Locators_XP8_Sanity.Snapdragon_Gallery);
			//clearAllow();
			//validate_Locators(Locators_XP8_Sanity.Timeline);
		}
		sf1.assertAll();
	}

	@Test(priority=7,dataProvider="XP8DevSanity", dataProviderClass=DataProviders.class)
	public void XP8_Sanity_07_Check_User_can_Add_Google_Account(Hashtable<String, String> dt) throws InterruptedException, AWTException, IOException, ParseException {

		APP_LOGS.info("======================XP8_Dev_Sanity_07=======================");
		clearRecentApps_O();
		launch_APP(Locators_XP8_Sanity.settings);
		scrollToText("Users & accounts");
		navigateTo_AddGoogleAccount();
		add_GoogleAccount(dt.get("emailId"), dt.get("password"));
		validate_GoogleAcccount(dt.get("emailId"));
	}

	@Test(priority=8,dataProvider="XP8DevSanity", dataProviderClass=DataProviders.class)
	public void XP8_Sanity_08_Check_able_to_Download_APK_from_Playstore(Hashtable<String, String> dt) throws InterruptedException, AWTException, IOException, ParseException {

		APP_LOGS.info("======================XP8_Sanity_08=======================");
		clearRecentApps_O();
		launch_APP(Locators_XP8_Sanity.PlayStore);
		customWait(3000);
		install_App(dt.get("appName"));
		validate_Installed_App(dt.get("appName"));
		clearRecentApps_O();
		launch_APP(Locators_XP8_Sanity.PlayStore);
		unInstall_App(dt.get("appName"));
	}

	@Test(priority=9,dataProvider="XP8DevSanity", dataProviderClass=DataProviders.class)
	public void XP8_Sanity_09_Check_able_to_make_Voice_calls(Hashtable<String, String> dt) throws InterruptedException, AWTException, IOException {

		APP_LOGS.info("===================== XP8_Dev_Sanity_09 =======================");
		launch_APP(Locators_XP8_Sanity.settings);
		scrollToText("Security & location");
		scrollToText("Screen lock");minWait();
		clickBtn(Locators_XP8_Sanity.noneLock);
		launch_APP(Locators_XP8_Sanity.phone);
		dailCallUsingDailPad(refNum);
		customWait(6000);
		end_Call();
		validate_Num_In_CallLog(refNum);
		clear_Call_History_O();
	}

	@Test(priority=10,dataProvider="XP8DevSanity", dataProviderClass=DataProviders.class)
	public void XP8_Sanity_10_Check_able_to_receive_VoiceCalls(Hashtable<String, String> dt) throws InterruptedException, AWTException, IOException {

		APP_LOGS.info("===================== XP8_Dev_Sanity_10 =======================");
		endCall_RefDevice();
		make_Call_from_RefDev();
		customWait(10000);
		recieve_Call_PrimaryDev_O();
		customWait(5000);
		endCall_RefDevice();
		launch_APP(Locators_XP8_Sanity.phone);
		validate_Num_In_CallLog(refNum);
		clear_Call_History();
	}

	@Test(priority=11,dataProvider="XP8DevSanity", dataProviderClass=DataProviders.class)
	public void XP8_Sanity_11_Check_able_to_Reject_VoiceCalls(Hashtable<String, String> dt) throws InterruptedException, AWTException, IOException {

		APP_LOGS.info("===================== XP8_Dev_Sanity_11 =======================");
		launch_APP(Locators_XP8_Sanity.phone);
		clear_Call_History();
		endCall_RefDevice();
		make_Call_from_RefDev();
		customWait(10000);
		aDriver.pressKeyCode(6);
		customWait(5000);
		//endCall_RefDevice();
		launch_APP(Locators_XP8_Sanity.phone);
		validate_Num_In_CallLog(refNum);
		clear_Call_History();
	}

	@Test(priority=12,dataProvider="XP8DevSanity",dataProviderClass=DataProviders.class)
	public void XP8_Sanity_12_Check_Able_to_do_InCall_functionality(Hashtable<String, String> dt) throws AWTException, InterruptedException, IOException, ParseException {

		APP_LOGS.info("======================XP8_Dev_Sanity_12 =======================");
		endCall_RefDevice();
		String fN=startAdbLog();
		launch_APP(Locators_XP8_Sanity.phone);
		dailCallUsingDailPad(refNum);
		customWait(6000);
		clickBtn(Locators_XP8_Sanity.speaker_call);minWait();
		clickBtn(Locators_XP8_Sanity.speaker_call);minWait();
		clickBtn(Locators_XP8_Sanity.mute_call);minWait();
		clickBtn(Locators_XP8_Sanity.mute_call);minWait();
		end_Call();
		customWait(9000);
		validate_ADB_Logs(fN,"AUDIO_ROUTE, Entering state ActiveSpeakerRoute: ICA.sAR->CARSM.pM_USER_SWITCH_SPEAKER","AUDIO_ROUTE, Leaving state ActiveSpeakerRoute: ICA.sAR->CARSM.pM_USER_SWITCH_BASELINE_ROUTE->CARSM.pM_USER_SWITCH_HEADSET");
		validate_ADB_Logs(fN,"turning on mute: true","turning on mute: false");
	}

	@Test(priority=13,dataProvider="XP8DevSanity",dataProviderClass=DataProviders.class)
	public void XP8_Sanity_13_MT_Call_Reject_with_SMS_Notification(Hashtable<String, String> dt) throws AWTException, InterruptedException, IOException, ParseException {

		APP_LOGS.info("======================XP8_Dev_Sanity_13 =======================");
		endCall_RefDevice();
		WebDriverWait wait = new WebDriverWait(aDriver, 80);
		endCall_RefDevice();
		if(p_b_No.contains("-10.")||p_b_No.contains("-00.")) {
			launch_APP(Locators_XP8_Sanity.messaging);
			clearSMSPermissions();
			delete_All_SMS();
			make_Call_from_RefDev();
			customWait(8000);
			reject_Call_With_SMS_O(dt.get("message"));
			customWait(4000);

			validate_SentMessage();
			delete_SMS();
		}else if (p_b_No.contains("-15.")) {
			launch_APP(Locators_XP8_Sanity.MessagePlus);
			//delete_All_SMS1();
			delete_SMS_O();
			customWait(3000);
			make_Call_from_RefDev();
			customWait(6000);
			reject_Call_With_SMS_O(dt.get("message")); minWait();

			wait.until(ExpectedConditions.visibilityOf(Locators_XP8_Sanity.firstSMS_InList_O));	
			if(isElementExist(Locators_XP8_Sanity.firstSMS_InList_O))
				clickBtn(Locators_XP8_Sanity.firstSMS_InList_O);
			validate_SentMessage_O();
			delete_SMS_O();
		}
	}

	@Test(priority=14,dataProvider="XP8DevSanity",dataProviderClass=DataProviders.class)
	public void XP8_Sanity_14_Send_SMS_during_ongoing_Voice_call(Hashtable<String, String> dt) throws AWTException, InterruptedException, IOException, ParseException {

		APP_LOGS.info("======================XP8_Dev_Sanity_14 =======================");
		endCall_RefDevice();
		launch_APP(Locators_XP8_Sanity.phone);
		dailCallUsingDailPad(refNum);
		customWait(3000);
		if(p_b_No.contains("-10.")||p_b_No.contains("-00.")){
			launch_APP(Locators_XP8_Sanity.messaging);
			create_NewSMS(refNum,dt.get("message"));
			clickOn_Send();
			validate_SentMessage();
			delete_SMS();
		}else if (p_b_No.contains("-15.")) {
			launch_APP(Locators_XP8_Sanity.MessagePlus);
			clearSMSPermissions_O();
			create_NewSMS_O(refNum,dt.get("message"));
			clickOn_Send_O();
			validate_SentMessage_O();
			delete_SMS_O();
		}
	}

	@Test(priority=15,dataProvider="XP8DevSanity",dataProviderClass=DataProviders.class)
	public void XP8_Sanity_15_Check_the_Call_history_for_details(Hashtable<String, String> dt) throws AWTException, InterruptedException, IOException, ParseException {

		APP_LOGS.info("======================XP8_Dev_Sanity_15 =======================");
		endCall_RefDevice();
		launch_APP(Locators_XP8_Sanity.phone);
		clear_Call_History();
		dailCallUsingDailPad(refNum);
		end_Call();
		launch_APP(Locators_XP8_Sanity.phone);
		clickOn_CallBtn_O();
		customWait(5000);
		validate_Locators1(Locators_XP8_Sanity.speaker_call);
		end_Call();
		customWait(4000);
		validate_Num_In_CallLog(refNum);
		clear_Call_History_O();
	}

	@Test(priority=16,dataProvider="XP8DevSanity", dataProviderClass=DataProviders.class)
	public void XP8_Sanity_16_Verify_Able_to_Send_SMS(Hashtable<String, String> dt) throws InterruptedException, AWTException, IOException {

		APP_LOGS.info("===================== XP8_Dev_Sanity_16 =======================");
		endCall_RefDevice();
		if (p_b_No.contains("-10.")||p_b_No.contains("-00.")) {
			launch_APP(Locators_XP8_Sanity.messaging);
			create_NewSMS(refNum, dt.get("message"));
			clickOn_Send();
			validate_SentMessage();
			delete_SMS();
		} else if(p_b_No.contains("-15.")) {
			launch_APP(Locators_XP8_Sanity.MessagePlus);
			create_NewSMS_O(refNum,dt.get("message"));
			clickOn_Send_O();
			validate_SentMessage_O();
			delete_SMS_O();
		}
	}

	@Test(priority=17,dataProvider="XP8DevSanity", dataProviderClass=DataProviders.class)
	public void XP8_Sanity_17_Check_Able_Receive_SMS(Hashtable<String, String> dt) throws InterruptedException, AWTException, IOException, ParseException {

		APP_LOGS.info("===================== XP8_Sanity_17 =======================");
		if (p_b_No.contains("-10.")||p_b_No.contains("-00.")) {
			launch_APP(Locators_XP8_Sanity.messaging);
			minWait();
			sendSMS_fromRefDevice(dt.get("message"));
			validate_RecievedMessage();
			delete_SMS();
		} else if(p_b_No.contains("-15.")) {
			launch_APP(Locators_XP8_Sanity.MessagePlus);
			minWait();
			sendSMS_fromRefDevice(dt.get("message"));
			validate_RecievedMessage_O();
			delete_SMS_O();
		}			
	}

	@Test(priority=18,dataProvider="XP8DevSanity",dataProviderClass=DataProviders.class)
	public void XP8_Sanity_18_Check_able_to_send_multi_page_SMS(Hashtable<String, String> dt) throws InterruptedException, AWTException, IOException {

		APP_LOGS.info("======================XP8_Dev_Sanity_18=======================");
		if(p_b_No.contains("-10.")||p_b_No.contains("-00.")) {
			launch_APP(Locators_XP8_Sanity.messaging);
			create_NewSMS(refNum,dt.get("message"));
			clickOn_Send();
			validate_SentMessage();
			delete_SMS();
		} else if (p_b_No.contains("-15.")) {
			launch_APP(Locators_XP8_Sanity.MessagePlus);
			create_NewSMS_O(refNum,dt.get("message"));
			clickOn_Send_O();
			validate_SentMessage_O();
			delete_SMS_O();
		}
	}

	@Test(priority=19,dataProvider="XP8DevSanity",dataProviderClass=DataProviders.class)
	public void XP8_Sanity_19_Check_able_to_Recieve_multi_page_SMS(Hashtable<String, String> dt) throws InterruptedException, AWTException, IOException {

		APP_LOGS.info("======================XP8_Dev_Sanity_19 =======================");
		/*Need to write the SCRIPT */
		if (p_b_No.contains("-10.")||p_b_No.contains("-30.")) {
			launch_APP(Locators_XP8_Sanity.messaging);
			minWait();
			sendSMS_fromRefDevice(dt.get("message"));			
			validate_RecievedMessage();
			delete_SMS();
		} else if(p_b_No.contains("-11.")||p_b_No.contains("-12.")||p_b_No.contains("-18.")||p_b_No.contains("-26.")||p_b_No.contains("-29.")) {
			launch_APP(Locators_XP8_Sanity.messages);
			minWait();
			sendSMS_fromRefDevice(dt.get("message"));
			validate_RecievedMessage();
			delete_SMS1();
		}
	}

	@Test(priority=20,dataProvider="XP8DevSanity", dataProviderClass=DataProviders.class)
	public void XP8_Sanity_20_Check_able_to_save_SMS_to_Draft_and_Delete(Hashtable<String, String> dt) throws InterruptedException, AWTException, IOException, ParseException {

		APP_LOGS.info("===================== XP8_Dev_Sanity_20======================="); 
		if (p_b_No.contains("-10.")||p_b_No.contains("-00.")) {
			launch_APP(Locators_XP8_Sanity.messaging);
			create_NewSMS(refNum, dt.get("message"));
			clickOn_BackBtn();
			clickOn_BackBtn();
			validate_Locators1(Locators_XP8_Sanity.draft);
			clickBtn(Locators_XP8_Sanity.draft);
			clickOn_Send();
			validate_SentMessage();
			delete_SMS();
		} else if(p_b_No.contains("-15.")) {

			launch_APP(Locators_XP8_Sanity.MessagePlus);
			create_NewSMS_O(refNum,dt.get("message"));
			clickOn_BackBtn();
			clickOn_BackBtn();
			validate_Locators1(Locators_XP8_Sanity.draft);
			clickBtn(Locators_XP8_Sanity.draft);
			clickOn_Send_O();
			validate_SentMessage_O();
			delete_SMS_O();
		}
	}

	@Test(priority=21,dataProvider="XP8DevSanity", dataProviderClass=DataProviders.class)
	public void XP8_Sanity_21_Check_able_to_send_MMS(Hashtable<String, String> dt) throws InterruptedException, AWTException, IOException, ParseException {

		APP_LOGS.info("===================== XP8_Dev_Sanity_21 =======================");
		launch_APP(Locators_XP8_Sanity.settings);
		clickOn_Networks_and_Internet();
		scrollToText("Mobile network");
		ON_Switch("data");
		if (p_b_No.contains("-10.")||p_b_No.contains("-00.")) {
			launch_APP(Locators_XP8_Sanity.messaging);
			navigateTo_NewSMS();
			enter_Num_ToField(refNum);
			add_Picture_O();
			enterText_MessageField(dt.get("message"));
			clickOn_Send();
			validate_SentMessage();
			delete_SMS();
		} else if(p_b_No.contains("-15.")) {
			launch_APP(Locators_XP8_Sanity.MessagePlus);		
			create_NewSMS_O(refNum, dt.get("message"));
			capturePic_MMS_O();
			clickOn_Send_O();
			validate_SentMessage_O();
			delete_SMS_O();
		}
	}

	@Test(priority=22,dataProvider="XP8DevSanity", dataProviderClass=DataProviders.class)
	public void XP8_Sanity_22_Check_able_to_save_MMS_to_draft_and_Delete(Hashtable<String, String> dt) throws InterruptedException, AWTException, IOException, ParseException {

		APP_LOGS.info("===================== XP8_Dev_Sanity_22 =======================");
		if (p_b_No.contains("-10.")||p_b_No.contains("-00.")) {
			launch_APP(Locators_XP8_Sanity.messaging);
			create_NewSMS(refNum, dt.get("message"));
			clickOn_BackBtn();
			clickOn_BackBtn();
			validate_Locators1(Locators_XP8_Sanity.draft);
			clickBtn(Locators_XP8_Sanity.draft);
			clickOn_Send();
			validate_SentMessage();
			delete_SMS();
		} else if(p_b_No.contains("-15.")) {
			launch_APP(Locators_XP8_Sanity.MessagePlus);
			create_NewSMS_O(refNum,dt.get("message"));
			clickOn_BackBtn();
			clickOn_BackBtn();
			validate_Locators1(Locators_XP8_Sanity.draft);
			clickBtn(Locators_XP8_Sanity.draft);
			clickOn_Send_O();
			validate_SentMessage_O();
			delete_SMS_O();
		}
	}

	@Test(priority=23,dataProvider="XP8DevSanity", dataProviderClass=DataProviders.class)
	public void XP8_Sanity_23_Check_able_to_Send_and_Receive_Email(Hashtable<String, String> dt) throws InterruptedException, AWTException, IOException, ParseException {

		APP_LOGS.info("===================== XP8_Dev_Sanity_23 =======================");
		launch_APP(Locators_XP8_Sanity.gmail);
		clear_GmailPermission();
		deleteAllmails();
		click_NewMail();
		compose_NewMail(dt.get("TO"), dt.get("subject"), dt.get("mailContent"));
		send_Mail();
		customWait(5000);
		validate_Mail(dt.get("subject"));
		deleteAllmails();
	}

	@Test(priority=24,dataProvider="XP8DevSanity", dataProviderClass=DataProviders.class)
	public void XP8_Sanity_24_Ensure_User_can_BrowseInternet(Hashtable<String, String> dt) throws InterruptedException, AWTException, IOException {

		APP_LOGS.info("===================== XP8_Dev_Sanity_24 =======================");
		launch_APP(Locators_XP8_Sanity.settings);
		clickOn_Networks_and_Internet();
		wiFi_OFF();
		scrollToText("Mobile network");
		ON_Switch("data");
		launch_APP(Locators_XP8_Sanity.chrome);
		clear_SearchBox();
		validate_And_BrowseIn_Chrome(dt.get("url"), Locators_XP8_Sanity.googleCoIn_Text, Locators_XP8_Sanity.google_Logo, Locators_XP8_Sanity.google_Logo);
	}	

	@Test(priority=25,dataProvider="XP8DevSanity", dataProviderClass=DataProviders.class)
	public void XP8_Sanity_25_Check_able_to_connect_to_available_SSID_Browse(Hashtable<String, String> dt) throws InterruptedException, AWTException, IOException {

		APP_LOGS.info("===================== XP8_Dev_Sanity_25 =======================");
		launch_APP(Locators_XP8_Sanity.settings);
		clickOn_Networks_and_Internet();
		scrollToText("Mobile network");
		OFF_Switch("data");
		clickBtn(Locators_XP8_Sanity.OK);
		clickOn_BackBtn();
		scrollToTextContains("Wi");
		ON_Switch("Off");
		setUp_And_Enable_WiFi();
		launch_APP(Locators_XP8_Sanity.chrome);
		clearChromePermission();
		clear_SearchBox();
		validate_And_BrowseIn_Chrome(dt.get("url"), Locators_XP8_Sanity.googleCoIn_Text, Locators_XP8_Sanity.google_Logo, Locators_XP8_Sanity.google_Logo);
	}

	@Test(priority=26, dataProvider="XP8DevSanity", dataProviderClass= DataProviders.class)
	public void XP8_Sanity_26_Enable_Disable_Wifi_form_DeviceSettings(Hashtable<String, String> dt) throws InterruptedException, IOException {

		APP_LOGS.info("===================== XP8_Dev_Sanity_26 ======================");
		launch_APP(Locators_XP8_Sanity.settings);
		clickOn_Networks_and_Internet();
		scrollToTextContains("Wi");
		ON_Switch("Off");
		setUp_And_Enable_WiFi();
		launch_APP(Locators_XP8_Sanity.chrome);
		clear_SearchBox();
		validate_And_BrowseIn_Chrome(dt.get("url"), Locators_XP8_Sanity.googleCoIn_Text, Locators_XP8_Sanity.google_Logo, Locators_XP8_Sanity.google_Logo);
		launch_APP(Locators_XP8_Sanity.settings);
		clickOn_Networks_and_Internet();
		wiFi_OFF();
		launch_APP(Locators_XP8_Sanity.chrome);
		clear_SearchBox();
		validate_MobileData_Disable();
	}

	@Test(priority=27, dataProvider="XP8DevSanity", dataProviderClass= DataProviders.class)
	public void XP8_Sanity_27_Enable_Disable_Wifi_form_Notification(Hashtable<String, String> dt) throws InterruptedException, IOException {

		APP_LOGS.info("===================== XP8_Dev_Sanity_27 ======================");
		launch_APP(Locators_XP8_Sanity.settings);
		clickOn_Networks_and_Internet();
		wiFi_OFF();
		scrollToText("Mobile network");
		OFF_Switch("data");
		clickBtn(Locators_XP8_Sanity.OK);
		swipe_NotificationBar();
		enable_Shortcuts_In_QuickPanel(Locators_XP8_Sanity.wifi_OffState_QuickPanel_O);
		customWait(5000);
		launch_APP(Locators_XP8_Sanity.chrome);
		clear_SearchBox();
		validate_And_BrowseIn_Chrome_O(dt.get("url"), Locators_XP8_Sanity.googleCoIn_Text, Locators_XP8_Sanity.google_Logo, Locators_XP8_Sanity.google_Logo);
		swipe_NotificationBar();
		disable_Shortcuts_QuickPanel(Locators_XP8_Sanity.wifi_OnState_QuickPanel_O);
		customWait(3000);
		launch_APP(Locators_XP8_Sanity.chrome);
		clear_SearchBox();
		validate_MobileData_Disable();
	}

	@Test(priority=28,dataProvider="XP8DevSanity", dataProviderClass= DataProviders.class)
	public void XP8_Sanity_28_Check_able_to_StreamVideo_via_YouTube(Hashtable<String, String> dt) throws InterruptedException, IOException {

		APP_LOGS.info("===================== XP8_Dev_Sanity_28 ======================");
		launch_APP(Locators_XP8_Sanity.settings);
		clickOn_Networks_and_Internet();
		scrollToText("Mobile network");
		ON_Switch("Mobile data");
		launch_APP(Locators_XP8_Sanity.youTube);
		customWait(8000);
		clickBtn(Locators_XP8_Sanity.firstVideo_YT);		
		customWait(6000);
		validate_Locators1(Locators_XP8_Sanity.playerView_YT);
	}

	@Test(priority=29, dataProvider="XP8DevSanity", dataProviderClass= DataProviders.class)
	public void XP8_Sanity_29_Enable_Disable_And_Scan_BT_Devices(Hashtable<String, String> dt) throws InterruptedException, IOException {

		APP_LOGS.info("===================== XP8_Dev_Sanity_29 ======================");
		Runtime.getRuntime().exec("adb -s "+r_Id+" shell input tap 777 1083"); // To Clear Battery full Popup if present.
		enable_BT_RefDevice();
		launch_APP(Locators_XP8_Sanity.settings);
		scrollToText("Connected devices");
		scrollToText("Bluetooth");
		OFF_Switch("On");
		ON_Switch("Off");
		customWait(4000);
		clickOnText("Pair new device");
		validate_BT_Devices();
		customWait(2000);
		clickOn_BackBtn();
		OFF_Switch("On");
		customWait(2000);
		validate_Locators1(Locators_XP8_Sanity.BT_Disable_Text);
		enable_BT_RefDevice(); // With the same Method we can Disable the BT.
	}

	@Test(priority=30,dataProvider="XP8DevSanity", dataProviderClass=DataProviders.class)
	public void XP8_Sanity_30_Device_is_Charging_if_USB_connected(Hashtable<String, String> dt) throws InterruptedException, AWTException, IOException {

		APP_LOGS.info("===================== XP8_Dev_Sanity_30 =======================");
		launch_APP(Locators_XP8_Sanity.settings);
		navigateTo_Battery();
		validate_BatteryCharging_Or_Full_O();
	}

	@Test(priority=31,dataProvider="XP8DevSanity", dataProviderClass=DataProviders.class)
	public void XP8_Sanity_31_Check_if_SDCard_get_Detected(Hashtable<String, String> dt) throws InterruptedException, AWTException, IOException {

		APP_LOGS.info("===================== XP8_Dev_Sanity_31 =======================");
		launch_APP(Locators_XP8_Sanity.settings);
		navigateTo_Storage();
		validate_StorageSpace_O("64");
	}

	@Test(priority=32,dataProvider="XP8DevSanity", dataProviderClass=DataProviders.class)
	public void XP8_Sanity_32_IMEI_Displsy_By_Dialing_USSD_code(Hashtable<String, String> dt) throws InterruptedException, AWTException, IOException {

		APP_LOGS.info("===================== XP8_Dev_Sanity_32 =======================");
		launch_APP(Locators_XP8_Sanity.phone);
		dailNumber(dt.get("ussd"));
		validate_Locators1(Locators_XP8_Sanity.imei_Popup);
		clickBtn(Locators_XP8_Sanity.OK);
	}

	@Test(priority=33,dataProvider="XP8DevSanity", dataProviderClass=DataProviders.class)
	public void XP8_Sanity_33_Add_Contact_in_Phonebook_with_all_Field(Hashtable<String, String> dt) throws InterruptedException, AWTException, IOException {

		APP_LOGS.info("===================== XP8_Dev_Sanity_33 =======================");
		launch_APP(Locators_XP8_Sanity.contacts);
		setDefaultAccount_Contact_O();
		deleteContact_O(dt.get("contactName"));
		add_NewContact_withAllFields_O(dt.get("contactName"),dt.get("lastName"),dt.get("phoneticlastName"),dt.get("phoneticmiddleName"),
				dt.get("phoneticName"), dt.get("nickName"),dt.get("company"), dt.get("title"),refNum,dt.get("SIP"),dt.get("email"),
				dt.get("address"),dt.get("IM"),dt.get("webSite"),dt.get("relationship"),dt.get("notes"));
		validate_Added_Contact_O(dt.get("contactName"));
	}

	@Test(priority=34,dataProvider="XP8DevSanity",dataProviderClass=DataProviders.class)
	public void XP8_Sanity_34_Add_contact_into_SIM_onlyWith_Name_PhoneNumber(Hashtable<String, String> data) throws InterruptedException, IOException, AWTException {

		APP_LOGS.info("======================XP8_Dev_Sanity_34=======================");
		launch_APP(Locators_XP8_Sanity.contacts);
		deleteContact_O(data.get("contactName"));
		//For Below Method user should enter 1 to save for phone memory and 2 to save for SIM memory in First Argument.
		add_NewContact(2,data.get("contactName"),refNum,data.get("contactEmail"),data.get("address"));
		validate_Added_Contact_O(data.get("contactName"));
	}

	@Test(priority=35,dataProvider="XP8DevSanity", dataProviderClass=DataProviders.class)
	public void XP8_Sanity_35_Check_Can_create_Group_in_Contacts(Hashtable<String, String> dt) throws InterruptedException, AWTException, IOException {

		APP_LOGS.info("===================== XP8_Dev_Sanity_35 =======================");
		launch_APP(Locators_XP8_Sanity.contacts);
		deleteLabel_IfPresent(dt.get("groupName"));
		navigate_Create_Label();
		create_Label(dt.get("groupName"));
		validate_LabelName(dt.get("groupName"));
		delete_Label();
	}

	@Test(priority=36,dataProvider="XP8DevSanity", dataProviderClass=DataProviders.class)
	public void XP8_Sanity_36_Check_user_can_delete_contact(Hashtable<String, String> dt) throws InterruptedException, AWTException, IOException {

		APP_LOGS.info("===================== XP8_Dev_Sanity_36=======================");
		launch_APP(Locators_XP8_Sanity.contacts);
		deleteContact_O(dt.get("contactName1"));
		deleteContact_O(dt.get("contactName2"));
	}

	@Test(priority=37,dataProvider="XP8DevSanity", dataProviderClass=DataProviders.class)
	public void XP8_Sanity_37_Check_for_Back_Camera_Working(Hashtable<String, String> dt) throws InterruptedException, AWTException, IOException {

		APP_LOGS.info("===================== XP8_Dev_Sanity_37 =======================");
		String fN = startAdbLog();
		launch_APP(Locators_XP8_Sanity.camera);
		clearCameraPermission();
		clickOnCapture();
		customWait(10000);
		validate_ADB_Logs(fN, "com.android.camera.NEW_PICTURE");
	}

	@Test(priority=38,dataProvider="XP8DevSanity", dataProviderClass=DataProviders.class)
	public void XP8_Sanity_38_Check_for_Front_Camera_Working(Hashtable<String, String> dt) throws InterruptedException, AWTException, IOException {

		APP_LOGS.info("===================== XP8_Dev_Sanity_38 =======================");
		String fN = startAdbLog();
		launch_APP(Locators_XP8_Sanity.camera);
		changeTo_Front_Back_Cam_O();
		clickOnCapture();
		changeTo_Front_Back_Cam_O();
		customWait(10000);
		validate_ADB_Logs(fN, "com.android.camera.NEW_PICTURE");
	}

	@Test(priority=39,dataProvider="XP8DevSanity",dataProviderClass=DataProviders.class)
	public void XP8_Sanity_39_Check_can_record_video_using_Front_Camera(Hashtable<String, String> data) throws InterruptedException, IOException, AWTException {

		APP_LOGS.info("====================== XP8_Dev_Sanity_39 =======================");
		String fN = startAdbLog();
		clearRecentApps_O();
		launch_APP(Locators_XP8_Sanity.camera);
		clearCameraPermission();
		clickVideoButton();
		customWait(8000);
		clickVideoButton();
		customWait(8000);
		validate_ADB_Logs(fN, "encoder (video) stopped");
	}

	@Test(priority=40,dataProvider="XP8DevSanity",dataProviderClass=DataProviders.class)
	public void XP8_Sanity_40_Check_can_record_video_using_Back_Camera(Hashtable<String, String> data) throws InterruptedException, IOException, AWTException {

		APP_LOGS.info("====================== XP8_Dev_Sanity_40 =======================");
		String fN = startAdbLog();
		clearRecentApps_O();
		launch_APP(Locators_XP8_Sanity.camera);
		changeTo_FrontCam();
		clickVideoButton();
		customWait(8000);
		clickVideoButton();
		customWait(8000);
		validate_ADB_Logs(fN, "encoder (video) stopped");//com.android.camera.NEW_VIDEO
	}

	@Test(priority=41,dataProvider="XP8DevSanity", dataProviderClass=DataProviders.class)
	public void XP8_Sanity_41_Check_able_to_change_the_SleepTime(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException {

		APP_LOGS.info("===================== XP8_Dev_Sanity_41 =======================");
		launch_APP(Locators_XP8_Sanity.settings);
		clickOn_Dispaly();
		clickBtn(Locators_XP8_Sanity.down_Triangle);
		clickOn_Sleep();
		clickBtn(Locators_XP8_Sanity.tenMin_Sleep);minWait();
		validate_Locators1(Locators_XP8_Sanity.tenMinutes_Inactivity);
		clickOn_Sleep();
		clickBtn(Locators_XP8_Sanity.thirtyMin_Sleep);minWait();
		validate_Locators1(Locators_XP8_Sanity.thirtyMinutes_Inactivity);
	}

	@Test(priority=42,dataProvider="XP8DevSanity", dataProviderClass=DataProviders.class)
	public void XP8_Sanity_42_Check_able_to_change_the_Ringtone(Hashtable<String, String> dt) throws InterruptedException, AWTException, IOException, ParseException {

		APP_LOGS.info("===================== XP8_Dev_Sanity_42 =======================");
		launch_APP(Locators_XP8_Sanity.settings);
		clickOn_Sound();
		clickOn_PhoneRingtone();
		select_Ringtone(dt.get("rintoneName"));
		validate_RingtoneChange(Locators_XP8_Sanity.bigEasy_ringtone);
	}

	@Test(priority=43,dataProvider="XP8DevSanity", dataProviderClass=DataProviders.class)
	public void XP8_Sanity_43_Check_able_to_Enable_Disable_Location(Hashtable<String, String> dt) throws InterruptedException, AWTException, IOException, ParseException {

		APP_LOGS.info("===================== XP8_Dev_Sanity_43 =======================");
		String fN=startAdbLog();
		launch_APP(Locators_XP8_Sanity.settings);
		scrollToText("Security & location");
		scrollToText("Location");
		OFF_Switch("On");
		minWait();
		clickBtn(Locators_XP8_Sanity.close);
		minWait();
		ON_Switch("Off");customWait(5000);
		OFF_Switch("On");customWait(12000);

		SoftAssert sf = new SoftAssert();
		String string1 = "Network location enabled.";
		boolean check1 = searchString(string1,fN);
		String string2 = "Network location disabled.";
		boolean check2 = searchString(string2,fN);	
		if (check1&&check2) {
			check=true;
			sf.assertTrue(check, "TestCase Valiation is PASS");
			test.log(LogStatus.PASS, "Test case Status is Pass.");
			test.log(LogStatus.INFO, "Validated from ADB Logs : "+string1+", "+string2);
		} else {
			String string3 = "location enabled :false";
			boolean check3 = searchString(string3,fN);
			String string4 = "location enabled :true";
			boolean check4 = searchString(string4,fN);
			if (check3&&check4) {
				check=true;
				sf.assertTrue(check, "TestCase Valiation is PASS");
				test.log(LogStatus.PASS, "Test case Status is Pass.");
				test.log(LogStatus.INFO, "Validated from ADB Logs : "+string1+", "+string2);
			} else {
				sf.fail();
				test.log(LogStatus.FAIL,"Test Case Status is Fail.");
				test.log(LogStatus.INFO, "Validation failed from ADB Logs.");
			}
		}
		sf.assertAll();
	}

	@Test(priority=44,dataProvider="XP8DevSanity", dataProviderClass=DataProviders.class)
	public void XP8_Sanity_44_Check_able_to_launch_Calendar_Add_an_event(Hashtable<String, String> dt) throws InterruptedException, AWTException, IOException, ParseException {

		APP_LOGS.info("===================== XP8_Dev_Sanity_44 =======================");
		launch_APP(Locators_XP8_Sanity.Calendar);
		clear_Calender_Permission();
		add_CalenderEvent(dt.get("eventName"));
		customWait(5000);
		validate_CalenderEvent(dt.get("eventName"));
		customWait(8000);
		delete_calenderEvent(dt.get("eventName"));
		launch_APP(Locators_XP8_Sanity.settings);
		scrollToText("Users & accounts");
		remove_GoogleAcccount_O();
	}

	@Test(priority=45,dataProvider="XP8DevSanity",dataProviderClass=DataProviders.class)
	public void XP8_Sanity_45_Check_Sound_Recording_is_Working(Hashtable<String, String> data) throws InterruptedException, IOException, AWTException {

		APP_LOGS.info("======================XP8_Dev_Sanity_45 =======================");
		launch_APP(Locators_XP8_Sanity.settings);
		scrollToText("Users & accounts");
		remove_GoogleAcccount_O();
		launch_APP(Locators_XP8_Sanity.soundRecorder);
		clearAllow();
		clickOn_Record();
		clearAllow();
		customWait(20000);
		clickOn_StopRecord();
		clickOn_Save(data.get("fileName"));
		validate_SoundRecList(data.get("fileName"));
	}

	@Test(priority=46,dataProvider="XP8DevSanity",dataProviderClass=DataProviders.class)
	public void XP8_Sanity_46_Check_able_to_play_Music_file(Hashtable<String, String> data) throws InterruptedException, IOException, AWTException {

		APP_LOGS.info("======================XP8_Dev_Sanity_46 =======================");
		launch_APP(Locators_XP8_Sanity.PlayMusic);
		customWait(4000);
		clickBtn(Locators_XP8_Sanity.SKIP);minWait();
		clearAllow();   customWait(3000);
		Runtime.getRuntime().exec("adb -s "+p_Id+" shell input tap 380 486");
		minWait();
		clickBtn(Locators_XP8_Sanity.audioRecords_Music_1); customWait(3000);
		validate_Locators1(Locators_XP8_Sanity.autoFile_Recorded_File);
		clickBtn(Locators_XP8_Sanity.autoFile_Recorded_File);
		customWait(5000);
		validate_Locators1(Locators_XP8_Sanity.play_pause_header);
		launch_APP(Locators_XP8_Sanity.soundRecorder);
		clickOn_RecordList();
		delete_SoundRecList();
	}

	@Test(priority=47,dataProvider="XP8DevSanity",dataProviderClass=DataProviders.class)
	public void XP8_Sanity_47_Check_able_to_launch_Clock_Add_Alarm(Hashtable<String, String> data) throws InterruptedException, IOException, AWTException {

		APP_LOGS.info("======================XP8_Dev_Sanity_47=======================");
		String fN = startAdbLog();
		launch_APP(Locators_XP8_Sanity.clock);
		addAndDelete_Alarm();
		customWait(8000);
		validate_ADB_Logs(fN, "Created new alarm instance: AlarmInstance");
	}

	@Test(priority=48,dataProvider="XP8DevSanity",dataProviderClass=DataProviders.class)
	public void XP8_Sanity_48_Check_able_to_launch_SCOUT_application(Hashtable<String, String> data) throws InterruptedException, IOException, AWTException {

		APP_LOGS.info("======================XP8_Sanity_48=======================");
		launch_APP(Locators_XP8_Sanity.sonimScout);
		minWait();
		validate_Locators1(Locators_XP8_Sanity.scout_Heading);
	}

	@Test(priority=49,dataProvider="XP8DevSanity",dataProviderClass=DataProviders.class)
	public void XP8_Sanity_49_Validate_Turn_On_Off_FM(Hashtable<String, String> data) throws AWTException, InterruptedException, IOException {

		APP_LOGS.info("======================XP8_Sanity_49=======================");
		String fN=startAdbLog();
		launch_APP(Locators_XP8_Sanity.fmRadio);
		FM_ON();
		customWait(3000);
		FM_OFF();
		customWait(2000);
		FM_ON();
		customWait(3000);
		FM_OFF();
		customWait(10000); 
		//validate_ADB_Logs(fN, "FMRxStarting ---> NEW-STATE 1: FMRxOn", "FMTurningOff ---> NEW-STATE : FMOff");
		validate_ADB_Logs(fN, "UART Flow On", "UART Flow Off");
	}

	@Test(priority=50,dataProvider="XP8DevSanity",dataProviderClass=DataProviders.class)
	public void XP8_Sanity_50_Validate_Tune_FMStation(Hashtable<String, String> data) throws AWTException, InterruptedException, IOException {

		APP_LOGS.info("======================XP8_Sanity_50=======================");
		String fN = startAdbLog();
		launch_APP(Locators_XP8_Sanity.fmRadio);
		FM_ON();
		customWait(3000);
		scan_AllStations();
		FM_OFF();
		customWait(10000);
		validate_ADB_Logs(fN, "searchStations: CURRENT-STATE : FMRxOn ---> NEW-STATE : SearchInProg");
		clickOnHomeBtn();
	}

	@Test(priority=51,dataProvider="XP8DevSanity",dataProviderClass=DataProviders.class)
	public void XP8_Sanity_51_Launch_Application_With_PTT_Key(Hashtable<String, String> data) throws InterruptedException, IOException, AWTException {

		APP_LOGS.info("====================== XP8_Sanity_51 =======================");
		if (!p_b_No.contains("-26.")) {
			launch_APP(Locators_XP8_Sanity.settings);
			navigateprogrammablekey();
			validate_ProgrammableKeySummaryText();
			press_PTT_Key();
			validate_Locators1(Locators_XP8_Sanity.dailerPad);
		} else {
			test.log(LogStatus.INFO, "NOT applicable for SL.");
		}
	}

}
