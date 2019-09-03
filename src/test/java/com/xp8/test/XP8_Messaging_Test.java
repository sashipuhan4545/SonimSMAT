package com.xp8.test;

import java.awt.AWTException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Hashtable;
import java.util.Properties;

import org.json.simple.parser.ParseException;
import org.openqa.selenium.Point;
import org.openqa.selenium.support.PageFactory;
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
import com.xp8.util.DataProviders;
import com.xp8.util.ExcelConstants;
import com.xp8.util.ExcelReader;

import com.xp8.util.Locators_BaseUtil;
import com.xp8.util.Locators_Data_Setting;
import com.xp8.util.Locators_DeviceStability;
import com.xp8.util.Locators_Messaging;
import com.xp8.util.XP8_Messaging_Util;

import OCR.Read_File;
import OCR.my_main;
import application.AllQA;
import io.appium.java_client.android.AndroidKeyCode;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class XP8_Messaging_Test extends XP8_Messaging_Util {
	public ExcelReader excel;
	Properties properties;
	public static ExtentTestInterruptedException testexception;
	public int itr = 5;
	boolean value = false;
	public String pw = AllQA.WIFIPASSWORD;
	int n = 5;

	@BeforeSuite
	public void beforeSuite() throws FileNotFoundException, InterruptedException, IOException, ParseException {
		extent = new ExtentReports("src/test/resources/extentreport/XP8_Messaging_TestReport.html", true); // Provide
																														// Desired
																														// Report
																														// Directory
																														// Location
		extent.loadConfig(new File("src/test/resources/StorageFile/ReportsConfig.xml"));
		extent.addSystemInfo("Build #", JsonFileReaderAndWriter.primaryDevFirmwareReader())
				.addSystemInfo("Product", JsonFileReaderAndWriter.primaryDevModelReader())
				.addSystemInfo("Operator", JsonFileReaderAndWriter.primaryDevOperatorReader());
		// and
		// Name
		extent.loadConfig(new File(System.getProperty("user.dir") + "//ReportsConfig.xml"));
		extent.addSystemInfo("Appium", "1.2.7").addSystemInfo("Environment", "TEST");
		fetch_Devices_Details();
	}

	@BeforeMethod()
	public void beforeMethod(Method method) throws IOException, InterruptedException {
		test = extent.startTest((this.getClass().getSimpleName() + " :: " + method.getName()), method.getName()); // Test
																													// Case
																													// Start
		test.assignAuthor("Navya Shree"); // Test Script Author Name

	}

	@BeforeClass
	public void copyFilesToDevice() throws IOException, ParseException {
		System.out.println("Executing clear log screen");
		File dir = new File("src/test/resources/adbLogs");
		if (dir.isDirectory() == false) {
			System.out.println("Not a directory. Do nothing");
			return;
		}
		File[] listFiles = dir.listFiles();
		for (File file : listFiles) {
			System.out.println("Deleting " + file.getName());
			file.delete();
		}
	}

	@AfterMethod()
	public void tearDown(ITestResult result, Method method) throws IOException, ParseException, InterruptedException {
		if (result.getStatus() == ITestResult.FAILURE) {
			String screenshot_path = captureScreenshot(method.getName());
			String image = test.addScreenCapture(screenshot_path);
			test.log(LogStatus.FAIL, result.getThrowable());
		}
		extent.endTest(test);
		extent.flush();
	}

	@BeforeTest
	public void setupSys() throws MalformedURLException, InterruptedException, FileNotFoundException, AWTException {
		properties = loadDriverAndProperties();
		Locators_Messaging loc = new Locators_Messaging(aDriver);
		Locators_BaseUtil loc1 = new Locators_BaseUtil(aDriver);
		PageFactory.initElements(new AppiumFieldDecorator(aDriver), loc);
		PageFactory.initElements(new AppiumFieldDecorator(aDriver), loc1);
		excel = new ExcelReader(ExcelConstants.XP5S_XL_PATH);
		System.out.println("before Test");
	}

	@BeforeTest
	public void tclearAppdata() {
		System.out.println("inside");

		try {
			if (JsonFileReaderAndWriter.primaryDevFirmwareReader().contains("-10")) {
				System.out.println("before test");
				launchAppThroughABD("adb shell pm clear com.android.mms");
				System.out.println("exit from Before Test");
			}
		}

		catch (Exception e) {
			// TODO: handle exception
		}

	}
	// ----------------test------------------

	@Test(priority = 0, dataProvider = "XP8_Messaging", dataProviderClass = DataProviders.class)
	public void XP8_TC_00_XP8_Messaging_PreCondition(Hashtable<String, String> data)
			throws InterruptedException, AWTException, IOException {
		SoftAssert sa = new SoftAssert();
		APP_LOGS.info("===========XP8_Messaging============");
		clearRecentApps();
		deleteall_SMS();

		delete_contact();
		performAction();
		//deleteall_SMS();
  disable_Group_Messaging();
  validate_Precondition(sa);
	
	}

	@Test(priority = 1, dataProvider = "XP8_Messaging", dataProviderClass = DataProviders.class)
	public void XP8_TC_01_XP8_Messaging_Send_SMS_Use_NewNumber(Hashtable<String, String> data)
			throws InterruptedException, AWTException, IOException {
		SoftAssert sa = new SoftAssert();
		APP_LOGS.info("===========XP8_Messaging============");
		clearRecentApps();
		System.out.println("At&t");
		launch_APP(Locators_Messaging.Messaging);
		clearSMSPermissions();
		navigateTo_NewSMS();
		enter_Num_ToField(data.get("number1"));
		validate_New_Phno(sa, data.get("number1"));
		enterText_MessageField(data.get("typeMessage"));
		clickOn_Send();
		validate_SentMessage(sa);

		sa.assertAll();
	}

	@Test(priority = 2, dataProvider = "XP8_Messaging", dataProviderClass = DataProviders.class)
	public void XP8_TC_02_XP8_Messaging_Send_SMS_Using_SpecialCharacter(Hashtable<String, String> data)
			throws InterruptedException, AWTException, IOException {
		SoftAssert sa = new SoftAssert();
		APP_LOGS.info("===========XP8_Messaging============");
		clearRecentApps();

		launch_APP(Locators_Messaging.Messaging);
		clearSMSPermissions();
		navigateTo_NewSMS();
		enter_Num_ToField(refNum);
		enterText_MessageField(data.get("typeMessage"));
		validate_Special_Character(sa, data.get("typeMessage"));
		clickOn_Send();
		minWait();
		clickBtn(Locators_Messaging.forward_Text1);
		validate_SMS_Details(sa);
		aDriver.pressKeyCode(AndroidKeyCode.BACK);
		validate_SentMessage(sa);
		sa.assertAll();
	}

	@Test(priority = 3, dataProvider = "XP8_Messaging", dataProviderClass = DataProviders.class)
	public void XP8_TC_03_XP8_Messaging_Memory_Status_Notification_vibration(Hashtable<String, String> data)
			throws InterruptedException, AWTException, IOException {
		SoftAssert sa = new SoftAssert();
		APP_LOGS.info("===========XP8_Messaging============");
		clearRecentApps();

		launch_APP(Locators_Messaging.Messaging);
        wait(Locators_Messaging.MoreOptions, 80);
        Notifications();
        validate_Notifications_oPtion(sa);
		vibration_On(sa);
		memory_status();
		validate_Memory_status(sa);
		clickBtn(Locators_Messaging.OKBtbtn2);
		Restore_Default_Settings();
		Validate_Restore_Default_Settings(sa);
		sa.assertAll();
	}

	@Test(priority = 4, dataProvider = "XP8_Messaging", dataProviderClass = DataProviders.class)
	public void XP8_TC_04_XP8_Messaging_Send_SMS_Using_UpperCase_LowerCase(Hashtable<String, String> data)
			throws InterruptedException, AWTException, IOException {
		SoftAssert sa = new SoftAssert();
		APP_LOGS.info("===========XP8_Messaging============");
		clearRecentApps();

		launch_APP(Locators_Messaging.Messaging);
		clearSMSPermissions();
		navigateTo_NewSMS();
		enter_Num_ToField(refNum);
		enterText_MessageField(data.get("typeMessage"));
		validate_UpperCaseandLowerCase(sa, data.get("typeMessage"));
		clickOn_Send();
		delete_SMS();
		validate_delete_SMS(sa);

		sa.assertAll();
	}

	@Test(priority = 5, dataProvider = "XP8_Messaging", dataProviderClass = DataProviders.class)
	public void XP8_TC_05_XP8_Messaging_LongPress_OnMessageIcon(Hashtable<String, String> data)
			throws InterruptedException, AWTException, IOException {
		SoftAssert sa = new SoftAssert();
		APP_LOGS.info("===========XP8_Messaging============");
		clearRecentApps();
		clickOnAppList();
		scrollTo("Messaging");
		LongPress_On_Message_Icon();
		validate_New_Conversation_Screen(sa);

		sa.assertAll();
	}

	@Test(priority = 6, dataProvider = "XP8_Messaging", dataProviderClass = DataProviders.class)
	public void XP8_TC_06_XP8_Messaging_DeleteMessageTemplate(Hashtable<String, String> data)
			throws InterruptedException, AWTException, IOException {
		SoftAssert sa = new SoftAssert();
		APP_LOGS.info("===========XP8_Messaging============");
		clearRecentApps();
		launch_APP(Locators_Messaging.Messaging);
		clickBtn(Locators_Messaging.MoreOptions);

		clickBtn(Locators_Messaging.setting);
		message_Template();
		validate_message_Template(sa);

		sa.assertAll();
	}

	@Test(priority = 7, dataProvider = "XP8_Messaging", dataProviderClass = DataProviders.class)
	public void XP8_TC_07_XP8_Messaging_Send_SMS_Using_LowerCase(Hashtable<String, String> data)
			throws InterruptedException, AWTException, IOException {
		SoftAssert sa = new SoftAssert();
		APP_LOGS.info("===========XP8_Messaging============");
		clearRecentApps();

		launch_APP(Locators_Messaging.Messaging);
		clearSMSPermissions();
		navigateTo_NewSMS();
		enter_Num_ToField(refNum);
		enterText_MessageField(data.get("typeMessage"));
		validate_LowerCase(sa, data.get("typeMessage"));
		clickOn_Send();
		validate_SentMessage(sa);

		sa.assertAll();
	}

	@Test(priority = 8, dataProvider = "XP8_Messaging", dataProviderClass = DataProviders.class)
	public void XP8_TC_08_XP8_Messaging_Send_SMS_Using_UpperCase(Hashtable<String, String> data)
			throws InterruptedException, AWTException, IOException {
		SoftAssert sa = new SoftAssert();
		APP_LOGS.info("===========XP8_Messaging============");
		clearRecentApps();

		launch_APP(Locators_Messaging.Messaging);
		clearSMSPermissions();
		navigateTo_NewSMS();
		enter_Num_ToField(refNum);
		enterText_MessageField(data.get("typeMessage"));
		validate_upperCase(sa, data.get("typeMessage"));
		clickOn_Send();
		validate_SentMessage(sa);

		sa.assertAll();
	}

	@Test(priority = 9, dataProvider = "XP8_Messaging", dataProviderClass = DataProviders.class)
	public void XP8_TC_09_XP8_Messaging_Send_SMS_Press_HomeKey(Hashtable<String, String> data)
			throws InterruptedException, AWTException, IOException {
		APP_LOGS.info("===========XP8_Messaging============");
		SoftAssert sa = new SoftAssert();
		clearRecentApps();
		launch_APP(Locators_Messaging.Messaging);
		clearSMSPermissions();
		navigateTo_NewSMS();
		enter_Num_ToField(refNum);
		aDriver.pressKeyCode(AndroidKeyCode.HOME);
		minWait();
		Msg_clear_Screen();
		clickBtn(Locators_Messaging.Messaging);
		validate_Text_After_Pressing_Homekey(sa);
		enterText_MessageField(data.get("typeMessage"));
		clickOn_Send();
		validate_SentMessage(sa);

		sa.assertAll();
	}

	@Test(priority = 10, dataProvider = "XP8_Messaging", dataProviderClass = DataProviders.class)
	public void XP8_TC_10_XP8_Messaging_Send_SMS_And_ReceiveCall(Hashtable<String, String> data)
			throws InterruptedException, AWTException, IOException {
		SoftAssert sa = new SoftAssert();
		APP_LOGS.info("===========XP8_Messaging============");
		clearRecentApps();

		launch_APP(Locators_Messaging.Messaging);
		clearSMSPermissions();
		navigateTo_NewSMS();
		enter_Num_ToField(refNum);
		make_Call_from_RefDev();
		customWait(10000);
		receiveCallInpriDevice();
		customWait(5000);
		endCall_RefDevice();
    	wait(Locators_Messaging.type_text1, 20);
		enterText_MessageField(data.get("typeMessage"));
		clickOn_Send();
		validate_SentMessage(sa);

		sa.assertAll();
	}

	@Test(priority = 11, dataProvider = "XP8_Messaging", dataProviderClass = DataProviders.class)
	public void XP8_TC_11_XP8_Messaging_Send_SMS_From_Call_History(Hashtable<String, String> data)
			throws InterruptedException, AWTException, IOException {
		SoftAssert sa = new SoftAssert();
		APP_LOGS.info("===========XP8_Messaging============");
		clearRecentApps();

		initiateCall();
		launch_an_app("phone");
		callHistory();
		
		enterText_MessageField(data.get("typeMessage"));
		clickOn_Send();
		validate_SentMessage(sa);

		sa.assertAll();
	}

	@Test(priority = 12, dataProvider = "XP8_Messaging", dataProviderClass = DataProviders.class)
	public void XP8_TC_12_XP8_Messaging_Send_SMS_ByEntering_URL(Hashtable<String, String> data)
			throws InterruptedException, AWTException, IOException {
		SoftAssert sa = new SoftAssert();
		APP_LOGS.info("===========XP8_Messaging============");
		clearRecentApps();
		launch_an_app("settings");
		clickOn_Networks_and_Internet();
		scrollToText("Mobile network");
		enable_MobileData();

		launch_APP(Locators_Messaging.Messaging);
		clearSMSPermissions();
		navigateTo_NewSMS();
		enter_Num_ToField(refNum);
		enterText_MessageField("www.google.com");
		clickOn_Send();
		validate_Entered_URL(sa);
		clickBtn(Locators_Messaging.google_text);
		Click_ON_URL();
		clearChromePermission();
		minWait();
		validate_And_BrowseIn_Chrome(sa);

		sa.assertAll();
	}

	@Test(priority = 13, dataProvider = "XP8_Messaging", dataProviderClass = DataProviders.class)
	public void XP8_TC_13_XP8_Messaging_Send_SMS_In_Same_Thread(Hashtable<String, String> data)
			throws InterruptedException, AWTException, IOException {
		APP_LOGS.info("===========XP8_Messaging============");
		SoftAssert sa = new SoftAssert();
		clearRecentApps();

		launch_APP(Locators_Messaging.Messaging);
		clearSMSPermissions();
		navigateTo_NewSMS();
		enter_Num_ToField(refNum);
		enterText_MessageField(data.get("typeMessage"));
		clickOn_Send();
		validate_SentMessage(sa);
		validate_Thread(sa);

		sa.assertAll();
	}

	@Test(priority = 14, dataProvider = "XP8_Messaging", dataProviderClass = DataProviders.class)
	public void XP8_TC_14_XP8_Messaging_SendSMS_MultipleTimes(Hashtable<String, String> data)
			throws InterruptedException, AWTException, IOException {
		SoftAssert sa = new SoftAssert();
		APP_LOGS.info("===========XP8_Messaging============");
		clearRecentApps();
		launch_APP(Locators_Messaging.Messaging);

		clearSMSPermissions();
		navigateTo_NewSMS();
		enter_Num_ToField(refNum);
		for (int i = 0; i < n; i++) {
			enterText_MessageField(data.get("typeMessage"));
			clickOn_Send();

		}
		validate_SentMessage_multiple_Times(sa);

		sa.assertAll();
	}

	@Test(priority = 15, dataProvider = "XP8_Messaging", dataProviderClass = DataProviders.class)
	public void XP8_TC_15_XP8_Messaging_Enter_EmailAddress(Hashtable<String, String> data)
			throws InterruptedException, AWTException, IOException {
		SoftAssert sa = new SoftAssert();
		APP_LOGS.info("===========XP8_Messaging============");
		clearRecentApps();

		launch_APP(Locators_Messaging.Messaging);
		clearSMSPermissions();
		navigateTo_NewSMS();
		enter_Num_ToField("Sonim@gmail.com");

		System.out.println(data.get("number1"));
		validate_MMS(sa);
		enter_Num_ToField(refNum);
		validate_SMS(sa);

		enterText_MessageField(data.get("typeMessage"));
		clickOn_Send();
		validate_SentMessage(sa);

		sa.assertAll();
	}

	@Test(priority = 16, dataProvider = "XP8_Messaging", dataProviderClass = DataProviders.class)
	public void XP8_TC_16_XP8_Messaging_Forward_Edit_And_Send_SMS(Hashtable<String, String> data)
			throws InterruptedException, AWTException, IOException {
		APP_LOGS.info("===========XP8_Messaging============");
		SoftAssert sa = new SoftAssert();
		clearRecentApps();

		launch_APP(Locators_Messaging.Messaging);
		sendSMS_fromRefDevice();
		maxWait();
		ReceivedMessage();
		enter_Num_ToField(refNum);

		enterText_MessageField("editing");

		clickOn_Send();
		validate_ForwardMessage(sa);

		sa.assertAll();
	}

	@Test(priority = 17, dataProvider = "XP8_Messaging", dataProviderClass = DataProviders.class)
	public void XP8_TC_17_XP8_Messaging_intiate_call_from_Editor_Screen(Hashtable<String, String> data)
			throws InterruptedException, AWTException, IOException {
		SoftAssert sa = new SoftAssert();
		APP_LOGS.info("===========XP8_Messaging============");
		clearRecentApps();

		launch_APP(Locators_Messaging.Messaging);
		clearSMSPermissions();
		navigateTo_NewSMS();
		enter_Num_ToField(data.get("newnumber"));
		enterText_MessageField(data.get("typeMessage"));
		clickOn_Send();
		clickBtn(Locators_Messaging.call);
		customWait(10000);
		aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_ENDCALL);
		minWait();
		validate_Text_After_initiating_Call(sa);

		sa.assertAll();
	}

	@Test(priority = -18, dataProvider = "XP8_Messaging", dataProviderClass = DataProviders.class)
	public void XP8_TC_18_XP8_Messaging_Stored_Contact_In_Editor_Screen(Hashtable<String, String> data)
			throws InterruptedException, AWTException, IOException {
		SoftAssert sa = new SoftAssert();
		APP_LOGS.info("===========XP8_Messaging============");
		clearRecentApps();

		launch_APP(Locators_Messaging.Messaging);
		clearSMSPermissions();
		navigateTo_NewSMS();
		enter_Num_ToField(data.get("newnumber"));
		enterText_MessageField(data.get("typeMessage"));
		clickOn_Send();
		add_Contact_In_Editor_Screen();
		validate_added_Contact(sa);
		sa.assertAll();
	}

	@Test(priority = 19, dataProvider = "XP8_Messaging", dataProviderClass = DataProviders.class)
	public void XP8_TC_19_XP8_Messaging_Select_Deselect_Contacts(Hashtable<String, String> data)
			throws InterruptedException, AWTException, IOException {
		APP_LOGS.info("===========XP8_Messaging============");
		SoftAssert sa = new SoftAssert();
		clearRecentApps();
		launch_an_app("contacts");
		for (int i = 1; i < 5;i++) {
			Click_ON_Phone();
			createContact_A("Test" + i, data.get("number" + i));
			aDriver.pressKeyCode(AndroidKeyCode.BACK);
		}
		launch_APP(Locators_Messaging.Messaging);
		clearSMSPermissions();
		navigateTo_NewSMS();
		select_The_Contact();
		validate_Select_Contact(sa);
		delect_The_Contact();
		validate_Delected_Contact(sa);
		clickBtn(multi_Loc_Strategy(Locators_Messaging.OKBtn1, Locators_Messaging.OKBtn2, Locators_Messaging.OKBtn3,
				null, null, 0, 0));

		sa.assertAll();
	}

	@Test(priority = 20, dataProvider = "XP8_Messaging", dataProviderClass = DataProviders.class)
	public void XP8_TC_20_XP8_Messaging_Select_PredictiveNumber_ToSendSMS(Hashtable<String, String> data)
			throws InterruptedException, AWTException, IOException {
		APP_LOGS.info("===========XP8_Messaging============");
		SoftAssert sa = new SoftAssert();
		clearRecentApps();
		launch_APP(Locators_Messaging.Messaging);
		clearSMSPermissions();
		navigateTo_NewSMS();
		enter_Num1("91951354");
		System.out.println("enter number");
		takeScreenShot();
		Read_File.takeScreenShotForOcr("Test");
		my_main.validate_Using_OCR("Test.jpeg");
		validate_text(sa);
		aDriver.pressKeyCode(AndroidKeyCode.ENTER);
		aDriver.pressKeyCode(AndroidKeyCode.BACK);

		sa.assertAll();
	}

	@Test(priority = 21, dataProvider = "XP8_Messaging", dataProviderClass = DataProviders.class)
	public void XP8_TC_21_XP8_Messaging_Contact_Saved_InSIM(Hashtable<String, String> data)
			throws InterruptedException, AWTException, IOException {
		APP_LOGS.info("===========XP8_Messaging============");
		SoftAssert sa = new SoftAssert();
		clearRecentApps();

		launch_an_app("contacts");

		for (int i = 1; i < n; i++) {
			clickBtn(Locators_Messaging.AddtoContact);
			Click_ON_SIM();

			createContact_IN_SIM("Sonim" +i, data.get("number" + i));
			aDriver.pressKeyCode(AndroidKeyCode.BACK);
		}
		launch_APP(Locators_Messaging.Messaging);
		clearSMSPermissions();
		navigateTo_NewSMS();
		enterText_MessageField(data.get("typeMessage"));
		select_SIM_Contact();
		validate_Select_Contact(sa);
		clickBtn(Locators_Messaging.OKBtn1);
		wait(Locators_Messaging.type_text1, 80);
		clickOn_Send();
		validate_SentMessage(sa);

		sa.assertAll();
	}
	@Test(priority = 22, dataProvider = "XP8_Messaging", dataProviderClass = DataProviders.class)
	public void XP8_TC_22_XP8_Messaging_Send_Picture(Hashtable<String, String> data)
			throws InterruptedException, AWTException, IOException {
		SoftAssert sa = new SoftAssert();
		APP_LOGS.info("===========XP8_Messaging============");
		clearRecentApps();

		launch_APP(Locators_Messaging.Messaging);
		clearSMSPermissions();
		navigateTo_NewSMS();
		add_Picture();
		validate_add_Picture(sa);
		select_SIM_Contact();	
		clickBtn(Locators_Messaging.OKBtn1);
		minWait();
		clickOn_Send_P();
		validate_SentMessage(sa);

		sa.assertAll();
	}


	@Test(priority = 23, dataProvider = "XP8_Messaging", dataProviderClass = DataProviders.class)
	public void XP8_TC_23_XP8_Messaging_ScrollUP_ScrollDown_IN_ContactScreen(Hashtable<String, String> data)
			throws InterruptedException, AWTException, IOException {
		SoftAssert sa = new SoftAssert();
		APP_LOGS.info("===========XP8_Messaging============");
		clearRecentApps();

		launch_APP(Locators_Messaging.Messaging);
		clearSMSPermissions();
		navigateTo_NewSMS();
		Scroll_To_Contact();
		validate_Scroll_To_Contact(sa);

		sa.assertAll();
	}

	@Test(priority = 24, dataProvider = "XP8_Messaging", dataProviderClass = DataProviders.class)
	public void XP8_TC_24_XP8_Messaging_Send_SMS_Using_StoredContact(Hashtable<String, String> data)
			throws InterruptedException, AWTException, IOException {
		APP_LOGS.info("===========XP8_Messaging============");
		SoftAssert sa = new SoftAssert();
		clearRecentApps();
		launch_APP(Locators_Messaging.Messaging);
		clearSMSPermissions();
		navigateTo_NewSMS();
		enterText_MessageField(data.get("typeMessage"));
		enter_Name_To_Field("Te");
		takeScreenShot();
		Read_File.takeScreenShotForOcr("Tes");
		my_main.validate_Using_OCR("Test1.jpeg");
		validate_text(sa);
		aDriver.pressKeyCode(AndroidKeyCode.ENTER);

	         minWait();
			clickOn_Send();
			validate_SentMessage(sa);
			aDriver.pressKeyCode(AndroidKeyCode.BACK);

			

		sa.assertAll();
	}


	@Test(priority = 25, dataProvider = "XP8_Messaging", dataProviderClass = DataProviders.class)
	public void XP8_TC_25_XP8_Messaging_Use_SIM_Contacts_and_Phone_contacts_Send_SMS(Hashtable<String, String> data)
			throws InterruptedException, AWTException, IOException {
		APP_LOGS.info("===========XP8_Messaging============");
		SoftAssert sa = new SoftAssert();
		clearRecentApps();
		launch_APP(Locators_Messaging.Messaging);
		clearSMSPermissions();
		navigateTo_NewSMS();
		enterText_MessageField(data.get("typeMessage"));
		select_SIM_And_Phone_Contact();
		validate_Select_Contact_In_SIM_Phone(sa);
		clickBtn(Locators_Messaging.OKBtn1);
		clickOn_Send();
		validate_SentMessage(sa);
		sa.assertAll();
	}
	@Test(priority = 26, dataProvider = "XP8_Messaging", dataProviderClass = DataProviders.class)
	public void XP8_TC_26_XP8_Messaging_Send_SMS_Lock_Unlock_The_Devices(Hashtable<String, String> data)
			throws InterruptedException, AWTException, IOException {
		SoftAssert sa = new SoftAssert();
		APP_LOGS.info("===========XP8_Messaging============");
		clearRecentApps();
		launch_APP(Locators_Messaging.Messaging);
		clearSMSPermissions();
		navigateTo_NewSMS();
		lock_Unlock_Screen_A();
		validate_Lock_Unlock_Screen(sa);
		sa.assertAll();
		


		sa.assertAll();
	}
	@Test(priority = 27, dataProvider = "XP8_Messaging", dataProviderClass = DataProviders.class)
	public void XP8_TC_27_XP8_Messaging_Send_Group_SMS(Hashtable<String, String> data)
			throws InterruptedException, AWTException, IOException {
		APP_LOGS.info("===========XP8_Messaging============");
		SoftAssert sa = new SoftAssert();
		clearRecentApps();

		launch_APP(Locators_Messaging.Messaging);
		clearSMSPermissions();
		navigateTo_NewSMS();
		enterText_MessageField(data.get("typeMessage"));

		group_Coversation();
		validate_group_SMS(sa);
		clickBtn(Locators_Messaging.OKBtn1);
		
		clickOn_Send();
		validate_SentMessage(sa);

		sa.assertAll();
	}
	@Test(priority = 28, dataProvider = "XP8_Messaging", dataProviderClass = DataProviders.class)
	public void XP8_TC_28_XP8_Messaging_Add_NewReceipients(Hashtable<String, String> data)
			throws InterruptedException, AWTException, IOException {
		SoftAssert sa = new SoftAssert();
		APP_LOGS.info("===========XP8_Messaging============");
		clearRecentApps();
		launch_APP(Locators_Messaging.Messaging);

		clearSMSPermissions();
		navigateTo_NewSMS();
		enterText_MessageField(data.get("typeMessage"));
		group_Coversation();
		clickBtn(Locators_Messaging.OKBtn1);
		adding_new_Recipients();
		clickBtn(Locators_Messaging.OKBtn1);

		wait(Locators_Messaging.type_text1, 80);
		clickOn_Send();
		validate_New_And_Old_Recipients(sa);
		validate_SentMessage(sa);

		sa.assertAll();
	}
	@Test(priority = 29, dataProvider = "XP8_Messaging", dataProviderClass = DataProviders.class)
	public void XP8_TC_29_XP8_Messaging_check_Email_add_InContactList(Hashtable<String, String> data)
			throws InterruptedException, AWTException, IOException {
		SoftAssert sa = new SoftAssert();
		APP_LOGS.info("===========XP8_Messaging============");
		clearRecentApps();

		launch_APP(Locators_Messaging.Messaging);
		clearSMSPermissions();
		navigateTo_NewSMS();
		enter_Name_To_Field("sonimtech@gmail.com");
		takeScreenShot();
		Read_File.takeScreenShotForOcr("email");
		my_main.validate_Using_OCR("email.jpeg");
		enterText_MessageField(data.get("typeMessage"));
		validate_EmailID(sa);
		sa.assertAll();
	}
	
//	@Test(priority = 29, dataProvider = "XP8_Messaging", dataProviderClass = DataProviders.class)
//	public void XP8_TC_29_XP8_Messaging_Remove_Recipient_From_ToField(Hashtable<String, String> data)
//			throws InterruptedException, AWTException, IOException {
//		SoftAssert sa = new SoftAssert();
//		APP_LOGS.info("===========XP8_Messaging============");
//		clearRecentApps();
//
//		launch_APP(Locators_Messaging.Messaging);
//		clearSMSPermissions();
//		navigateTo_NewSMS();
//		enterText_MessageField(data.get("typeMessage"));
//
//		group_Coversation();
//		clickBtn(Locators_Messaging.OKBtn1);
//		wait(Locators_Messaging.TO_Field_phno1, 60);
//		clickBtn(Locators_Messaging.TO_Field_phno1);
//		aDriver.pressKeyCode(AndroidKeyCode.BACKSPACE);
//		aDriver.pressKeyCode(AndroidKeyCode.ENTER);
//		minWait();
//		clickOn_Send();
//		validate_SentMessage(sa);
//		validate_Remove_Recipient_From_To_Field(sa);
//
//		sa.assertAll();
//	}
	
	@Test(priority = 30, dataProvider = "XP8_Messaging", dataProviderClass = DataProviders.class)
	public void XP8_TC_30_XP8_Messaging_Reject_Call_With_SMS(Hashtable<String, String> data)
			throws InterruptedException, AWTException, IOException {
		SoftAssert sa = new SoftAssert();
		APP_LOGS.info("===========XP8_Messaging============");
		clearRecentApps();

		launch_APP(Locators_Messaging.Messaging);
		aDriver.openNotifications();
		make_Call_from_RefDev();
		customWait(15000);
		reject_Call_With_SMS_O("hi");
		customWait(5000);
		endCall_RefDevice();
	wait(Locators_Messaging.Messaging, 20);
		validate_SentMessage(sa);


		sa.assertAll();
	}

	
	@Test(priority = 31, dataProvider = "XP8_Messaging", dataProviderClass = DataProviders.class)
	public void XP8_TC_31_XP8_Messaging_Checking_Notifications_Option(Hashtable<String, String> data)
			throws InterruptedException, AWTException, IOException {
		SoftAssert sa = new SoftAssert();
		APP_LOGS.info("===========XP8_Messaging============");
		clearRecentApps();
		launch_APP(Locators_Messaging.Messaging);
		enable_Notification();
		validate_enable_Notification(sa);
		aDriver.pressKeyCode(AndroidKeyCode.BACK);
		aDriver.openNotifications();
		sendSMS_fromRefDevice();
		wait(Locators_Messaging.forward_Text_A, 80);
		reply_From_Notification_bar();
        enterTextToInputField1(Locators_Messaging.type_text2, data.get("typeMessage"));
         clickBtn(Locators_Messaging.send_Icon3);
		aDriver.pressKeyCode(AndroidKeyCode.BACK);
		validate_SentMessage(sa);
		sa.assertAll();
	}
	@Test(priority = 32, dataProvider = "XP8_Data_Setting", dataProviderClass = DataProviders.class)
	public void XP8_TC_32_XP8_Messaging_Postcondition(Hashtable<String, String> data)
			throws InterruptedException, AWTException, IOException {
		SoftAssert sa = new SoftAssert();
		APP_LOGS.info("===========XP8_TC_01_Data_Setting============");
		clearRecentApps();
		delete_contact();
		performAction();
		deleteall_SMS();
  disable_Group_Messaging();
  validate_Postcondition(sa);
		
		sa.assertAll();

	}

}
