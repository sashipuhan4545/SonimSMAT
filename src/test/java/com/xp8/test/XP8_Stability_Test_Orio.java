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
import com.mongodb.util.JSON;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTestInterruptedException;
import com.relevantcodes.extentreports.LogStatus;
import com.xp5S.util.GetMethods;
import com.xp5S.util.appiumService;
import com.xp8.util.DataProviders;
import com.xp8.util.ExcelConstants;
import com.xp8.util.ExcelReader;

import com.xp8.util.Locators_BaseUtil;
import com.xp8.util.Locators_DeviceStability;

import com.xp8.util.Locators_XP8_Sanity;
import com.xp8.util.XP8_DeviceStability_Util_orio;

import application.AllQA;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.AndroidKeyCode;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class XP8_Stability_Test_Orio extends XP8_DeviceStability_Util_orio {

	public ExcelReader excel;
	Properties properties;
	public static ExtentTestInterruptedException testexception;
	public int itr = AllQA.NUMOFCALLS;
	boolean value = false;
	public String pw =AllQA.WIFIPASSWORD;

//	@BeforeSuite
//	public void beforeSuite() throws FileNotFoundException, InterruptedException, IOException, ParseException, ParseException  
//	{
//		extent = new ExtentReports("src/test/resources/extentreport/XP8_DeviceStability_Orio_TestReport.html", true); //Provide Desired Report Directory Location and Name
//
//		extent.loadConfig(new File(System.getProperty("user.dir")+"//ReportsConfig.xml"));
//
//		extent.loadConfig(new File("src/test/resources/StorageFile/ReportsConfig.xml"));
//		extent.addSystemInfo("Build #",JsonFileReaderAndWriter.primaryDevFirmwareReader())
//		.addSystemInfo("Product",JsonFileReaderAndWriter.primaryDevModelReader())
//		.addSystemInfo("Operator", JsonFileReaderAndWriter.primaryDevOperatorReader());		
//
//		fetch_Devices_Details();	
//	}

	@BeforeSuite
	public void beforeSuite() throws FileNotFoundException, InterruptedException, IOException, ParseException {
		extent = new ExtentReports("src/test/resources/extentreport/XP8_Device_Stability_Orio_TestReport.html", true); // Provide
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

	@BeforeSuite
	public void numofTestCases() throws ClassNotFoundException {
		
		appiumService.TOTAL_NUM_OF_TESTCASES=GetMethods.TotalTestcase("XP8_TC", this.getClass());
	}

//	@BeforeMethod()
//	public  void beforeMethod(Method method) 
//	{
//		test = extent.startTest( (this.getClass().getSimpleName() +" :: "+  method.getName()),method.getName()); //Test Case Start Here
//		test.assignAuthor(""); //Test Script Author Name
//		String BuildNumber=BaseUtil.fetchDeviceProperty("adb shell getprop ro.build.id");
//		test.assignCategory("Build #:"+BuildNumber);
//	}

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
		clearRecentApps();
		extent.endTest(test);
		extent.flush();
	}

	@BeforeTest
	public void setupSys() throws MalformedURLException, InterruptedException, FileNotFoundException, AWTException {
		properties = loadDriverAndProperties();
		Locators_DeviceStability loc = new Locators_DeviceStability(aDriver);
		Locators_BaseUtil loc1 = new Locators_BaseUtil(aDriver);
		PageFactory.initElements(new AppiumFieldDecorator(aDriver), loc);
		PageFactory.initElements(new AppiumFieldDecorator(aDriver), loc1);
		excel = new ExcelReader(ExcelConstants.XP5S_XL_PATH);

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

	@Test(priority = 0, dataProvider = "XP8_Stability", dataProviderClass = DataProviders.class)
	public void XP8_TC_0_Precondition(Hashtable<String, String> data)
			throws InterruptedException, AWTException, IOException, ParseException, ParseException {
		APP_LOGS.info("===========XP8_Stability_01============");
		startAdbLog("XP8_Stability_01");
		SoftAssert sa1 = new SoftAssert();
		clearRecentApps();
		performAction();
	

		deleteall_SMS();
		delete_contact();
		delete_Call_From_Call_History();
	}

	@Test(priority = 1, dataProvider = "XP8_Stability", dataProviderClass = DataProviders.class)
	public void XP8_TC_01_Stability_Validate_Contact_Creation(Hashtable<String, String> data)
			throws InterruptedException, AWTException, IOException, ParseException, ParseException {
		APP_LOGS.info("===========XP8_Stability_01============");
		startAdbLog("XP8_Stability_01");
		SoftAssert sa1 = new SoftAssert();
		clearRecentApps();
		launch_an_app("contacts");
		for (int i = 1; i <= itr; i++) {
			
			if (p_b_No.contains("-29.")) {

				createContact("Test" + i, refNum);
				validateContactCreation(i, sa1);
			} else if (p_b_No.contains("-15.")) {

				createContact("Test" + i, refNum);
				validateContactCreation(i, sa1);
			}

			else if (p_b_No.contains("-11.")) {

				createContact("Test" + i, refNum);
				validateContactCreation(i, sa1);
			} else if (p_b_No.contains("-26.")) {

				createContact("Test" + i, refNum);
				validateContactCreation(i, sa1);
			}

			else if (p_b_No.contains("-10.") || p_b_No.contains("-00.")) {

				createContact("Test" + i, refNum);
				validateContactCreation(i, sa1);

			}
		}	
		sa1.assertAll();
	}


 
	@Test(priority = 2, dataProvider = "XP8_Stability", dataProviderClass = DataProviders.class)
	public void XP8_TC_02_Stability_Validate_Send_MO_IMS_SMS(Hashtable<String, String> data)
			throws InterruptedException, AWTException, IOException, ParseException {
		APP_LOGS.info("===========XP8_Stability_02============");
		startAdbLog("XP8_Stability_02");
		SoftAssert sa2 = new SoftAssert();
		clearRecentApps();
		
 String fN = startRIL_Log();
 
 System.err.println(value);
value =  validate_RIL_Logs(fN, "RIL_REQUEST_IMS_REGISTRATION_STATE {1, 1} [SUB0]","Failure may also be because Operator is NOT IMS Registered(NOT VoLTE).");

//	value =  validate_RIL_Logs(fN, "RIL_REQUEST_IMS_REGISTRATION_STATE {1, 1} [SUB0]","Failure may also be because Operator is NOT IMS Registered(NOT VoLTE).");
		
		System.out.println(value);
		if (value) {
			for (int i = 1; i <= itr; i++) {

				if (p_b_No.contains("-10.") || p_b_No.contains("-00.")) {
					
					launch_APP(Locators_DeviceStability.Messageing);
					clearSMSPermissions();
					navigateTo_NewSMS();
					enter_Num_ToField("918553317682");
					enterText_MessageField("Automation Test");
					clickOn_Send();
					validate_SentMessage(i, sa2);

				} else if (p_b_No.contains("-15.")) {
					System.out.println("vwz");
					launch_APP(Locators_DeviceStability.MessagePlus);
					clearSMSPermissions();
					navigateTo_NewSMS();
					enter_Num_ToField(refNum);
					enterText_MessageField("Automation Test");
					clickOn_Send();
					validate_SentMessage(i, sa2);

				
				} else if (p_b_No.contains("-29.")) {
					System.out.println("sprint");
					launch_APP(Locators_DeviceStability.messages);
					clearSMSPermissions();
					navigateTo_NewSMS();
					enter_Num_ToField(refNum);
					enterText_MessageField("Automation Test");
					clickOn_Send();
					validate_SentMessage(i, sa2);
				}

				else if (p_b_No.contains("-11")) {
					System.out.println("bell");
					launch_APP(Locators_DeviceStability.messages);
					clearSMSPermissions();
					navigateTo_NewSMS();
					enter_Num_ToField(refNum);
					enterText_MessageField("Automation Test");
					clickOn_Send();
					validate_SentMessage(i, sa2);

					
				} else if (p_b_No.contains("-26")) {
					System.out.println("SL");
					launch_APP(Locators_DeviceStability.messages);
					clearSMSPermissions();
					navigateTo_NewSMS();
					enter_Num_ToField(refNum);
					enterText_MessageField("Automation Test");
					clickOn_Send();
					validate_SentMessage(i, sa2);

				} else {
					System.out.println("Executing Else part");
					clearSMSPermissions();
					navigateTo_NewSMS();
					enter_Num_ToField(refNum);
					enterText_MessageField("Automation Test");
					clickOn_Send();
					validate_SentMessage(i, sa2);

				}
			}
		} else {
			test.log(LogStatus.SKIP, "NT: IMS is not Enabled");
	}
	
		sa2.assertAll();
	}
	
	@Test(priority = 3, dataProvider = "XP8_Stability", dataProviderClass = DataProviders.class)
	public void XP8_TC_03_Stability_Validate_Send_MO_Non_IMS_SMS(Hashtable<String, String> data)
			throws InterruptedException, AWTException, IOException, ParseException {
		APP_LOGS.info("===========XP8_Stability_03============");
		startAdbLog("XP8_Stability_03");
		SoftAssert sa3 = new SoftAssert();
		clearRecentApps();
		System.out.println(value);
		if (!value) {
			for (int i = 1; i <= itr; i++) {
				if (p_b_No.contains("-10.") || p_b_No.contains("-00.")) {
                  
					launch_APP(Locators_DeviceStability.Messageing);
					clearSMSPermissions();
					navigateTo_NewSMS();
					enter_Num_ToField("918553317682");
					System.out.println("Enter Text");
					enterText_MessageField("Automation Test");
					clickOn_Send();
					validate_SentMessage(i, sa3);
					
				} else if (p_b_No.contains("-15.")) {
					launch_APP(Locators_DeviceStability.MessagePlus);
					clearSMSPermissions();
					navigateTo_NewSMS();
					enter_Num_ToField(refNum);
					System.out.println("Enter Text");
					enterText_MessageField("Automation Test");
					clickOn_Send();
					validate_SentMessage(i, sa3);
				} else if (p_b_No.contains("-29.")) {
				
					System.out.println("sprint");
					launch_APP(Locators_DeviceStability.messages);
					clearSMSPermissions();
					navigateTo_NewSMS();
					enter_Num_ToField(refNum);
					System.out.println("Enter Text");
					enterText_MessageField("Automation Test");
					clickOn_Send();
					validate_SentMessage(i, sa3);
				} else if (p_b_No.contains("-11.")) {
					System.out.println("bell");
					launch_APP(Locators_DeviceStability.messages);
					clearSMSPermissions();
					navigateTo_NewSMS();
					enter_Num_ToField(refNum);
					System.out.println("Enter Text");
					enterText_MessageField("Automation Test");
					clickOn_Send();
					validate_SentMessage(i, sa3);
				} else if (p_b_No.contains("-26.")) {
					System.out.println("SL");
					launch_APP(Locators_DeviceStability.messages);
					clearSMSPermissions();
					navigateTo_NewSMS();
					enter_Num_ToField(refNum);
					System.out.println("Enter Text");
					enterText_MessageField("Automation Test");
					clickOn_Send();
					validate_SentMessage(i, sa3);
					
				} else {
					System.out.println("Executing Else part");
					launch_APP(Locators_DeviceStability.messaging);
					clearSMSPermissions();
					navigateTo_NewSMS_O();
					create_NewSMS(refNum, data.get("typeMessage"));
					clickOn_Send();
					validate_SentMessage(i, sa3);
				}
			}
		} else {
			test.log(LogStatus.SKIP, "NT: IMS is Enabled");
		}
		
		sa3.assertAll();
	}

	@Test(priority = 4, dataProvider = "XP8_Stability", dataProviderClass = DataProviders.class)
	public void XP8_TC_04_Stability_Validate_MO_SMS_Maximum_NumberOfCharacters(Hashtable<String, String> data)
			throws InterruptedException, AWTException, IOException, ParseException {
		APP_LOGS.info("===========XP8_Stability_04============");
		startAdbLog("XP8_Stability_04");
		SoftAssert sa4 = new SoftAssert();
		clearRecentApps();
		for (int i = 1; i <= itr; i++) {

			if (p_b_No.contains("-10.") || p_b_No.contains("-00.")) {
				System.out.println("AT&T");
				launch_APP(Locators_DeviceStability.Messageing);
				clearSMSPermissions();
				navigateTo_NewSMS();
				enter_Num_ToField("918553317682");
				System.out.println("Enter Text");
				enterText_MessageField(data.get("typeMessage3"));
				clickOn_Send();
				validate_SentMessage(i, sa4);
			} else if (p_b_No.contains("-15.")) {
				launch_APP(Locators_DeviceStability.MessagePlus);
				clearSMSPermissions();
				navigateTo_NewSMS();
				enter_Num_ToField(refNum);
				System.out.println("Enter Text");
				enterText_MessageField(data.get("typeMessage3"));
				clickOn_Send();
				validate_SentMessage(i, sa4);
			} else if (p_b_No.contains("-29.")) {
				System.out.println("sprint");
				launch_APP(Locators_DeviceStability.messages);
				clearSMSPermissions();
				navigateTo_NewSMS();
				enter_Num_ToField(refNum);
				System.out.println("Enter Text");
				enterText_MessageField(data.get("typeMessage3"));
				clickOn_Send();
				validate_SentMessage(i, sa4);
					}

			else if (p_b_No.contains("-11.")) {
				System.out.println("bell");
				launch_APP(Locators_DeviceStability.messages);
				clearSMSPermissions();
				navigateTo_NewSMS();
				enter_Num_ToField(refNum);
				System.out.println("Enter Text");
				enterText_MessageField(data.get("typeMessage3"));
				clickOn_Send();
				validate_SentMessage(i, sa4);
			}

			else if (p_b_No.contains("-26.")) {
				System.out.println("SL");
				launch_APP(Locators_DeviceStability.messages);
				clearSMSPermissions();
				navigateTo_NewSMS();
				enter_Num_ToField(refNum);
				System.out.println("Enter Text");
				enterText_MessageField(data.get("typeMessage3"));
				clickOn_Send();
				validate_SentMessage(i, sa4);
			} else {
				System.out.println("Executing Else part");
				launch_APP(Locators_DeviceStability.messaging);
				clearSMSPermissions();
				navigateTo_NewSMS();
				enter_Num_ToField(refNum);
				System.out.println("Enter Text");
				enterText_MessageField(data.get("typeMessage3"));
				clickOn_Send();
				validate_SentMessage(i, sa4);
			}
		}
		
		sa4.assertAll();
	}

	@Test(priority = 5, dataProvider = "XP8_Stability", dataProviderClass = DataProviders.class)
	public void XP8_TC_05_Stability_Validate_MT_SMS(Hashtable<String, String> data)
			throws InterruptedException, AWTException, IOException, ParseException {
		APP_LOGS.info("===========XP8_Stability_05============");
		startAdbLog("XP8_Stability_05");
		SoftAssert sa5 = new SoftAssert();
		clearRecentApps();
		for (int i = 1; i <= itr; i++) {

			if (p_b_No.contains("-10.") || p_b_No.contains("-00.")) {
				launch_APP(Locators_DeviceStability.Messageing);
				sendSMS_fromRefDevice(data.get("typeMessage1"));
				minWait();
				validate_ReceivedMessage(i, sa5);
		maxWait();
			} else if (p_b_No.contains("-15.")) {
				launch_APP(Locators_DeviceStability.MessagePlus);
				sendSMS_fromRefDevice(data.get("typeMessage1"));
				minWait();
				validate_RecievedMessage_O(i, sa5);
			} else if (p_b_No.contains("-29.")) {
				System.out.println("sprint");
				launch_APP(Locators_DeviceStability.messages);
				sendSMS_fromRefDevice(data.get("typeMessage1"));
				minWait();
				validate_ReceivedMessage_b(i, sa5);
			} else if (p_b_No.contains("-11.")) {
				System.out.println("bell");
				launch_APP(Locators_DeviceStability.messages);
				sendSMS_fromRefDevice(data.get("typeMessage1"));
				minWait();
				validate_ReceivedMessage_b(i, sa5);
			} else if (p_b_No.contains("-26.")) {
				System.out.println("SL");
				launch_APP(Locators_DeviceStability.messages);
				sendSMS_fromRefDevice(data.get("typeMessage1"));
				minWait();
				validate_ReceivedMessage_b(i, sa5);
			} else {
				System.out.println("Executing Else part");
				launch_APP(Locators_DeviceStability.messaging);
				navigateTo_NewSMS_O();
				create_NewSMS(pryNum, data.get("typeMessage1"));
				clickOn_Send();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
				validate_RecievedMessage_O(i, sa5);
			}
		}
		sa5.assertAll();
	}


	@Test(priority = 6, dataProvider = "XP8_Stability", dataProviderClass = DataProviders.class)
	public void XP8_TC_06_Stability_Validate_MOVoiceCall_CSFB_from_Contacts(Hashtable<String, String> data)
			throws InterruptedException, AWTException, IOException, ParseException {
		APP_LOGS.info("===========XP8_Stability_08============");
		startAdbLog("XP8_Stability_08");
		SoftAssert sa6 = new SoftAssert();
	minWait();
		clearRecentApps();
		String fN = startRIL_Log();
		value =  validate_RIL_Logs(fN, "RIL_REQUEST_IMS_REGISTRATION_STATE {1, 1} [SUB0]","Failure may also be because Operator is NOT IMS Registered(NOT VoLTE).");
		System.out.println(value);
		if (!value) {
			for (int i = 1; i <= itr; i++) {
				if (p_b_No.contains("-10.") || p_b_No.contains("-00.")) {
					launch_an_app("contacts");
					searchContact("Test" + i);
					initiateCall();
					validateCallLog_Orio("called", i, "contacts", sa6);
				} else if (p_b_No.contains("-15.")) {
					launch_an_app("contacts");
					searchContact("Test" + i);
					initiateCall();
					validateCallLog_Orio("called", i, "contacts", sa6);
				} else if (p_b_No.contains("-11.")) {
					 
					launch_an_app("contacts");
					searchContact("Test" + i);
					initiateCall();
					validateCallLog_Orio("called", i, "contacts", sa6);
				} else if (p_b_No.contains("-29.")) {
					launch_an_app("contacts");
					searchContact("Test" + i);
					initiateCall();
					validateCallLog_Orio("called", i, "contacts", sa6);
				} else if (p_b_No.contains("-26.")) {
					launch_an_app("contacts");
					searchContact("Test" + i);
					initiateCall();
					validateCallLog_Orio("called", i, "contacts", sa6);
				} else {
					launch_an_app("contacts");
					searchContact("Test" + i);
					initiateCall();
					validateCallLog_Orio("called", i, "contacts", sa6);
				}
			}
		} else {
			test.log(LogStatus.SKIP, "NT: IMS is Enabled");
		}
		sa6.assertAll();

	}
   
	@Test(priority = 7, dataProvider = "XP8_Stability", dataProviderClass = DataProviders.class)
	public void XP8_TC_07_Stability_Validate_MOVoiceCall_CSFB_from_CallHistory(Hashtable<String, String> data)
			throws InterruptedException, AWTException, IOException, ParseException {
		APP_LOGS.info("===========XP8_Stability_09============");
		startAdbLog("XP8_Stability_09");
		SoftAssert sa7 = new SoftAssert();
		clearRecentApps();

		System.out.println(value);
		if (!value) {
			for (int i = 1; i <= itr; i++) {

				if (p_b_No.contains("-10.") || p_b_No.contains("-00.")) {
					launch_an_app("phone");
					callHistory();
					validateCallLog_Orio("called", i, "contacts", sa7);
				} else if (p_b_No.contains("-15.")) {
					launch_an_app("phone");
					callHistory();
					validateCallLog_Orio("called", i, "contacts", sa7);
				} else if (p_b_No.contains("-29.")) {
					launch_an_app("phone");
					callHistory();
					validateCallLog_Orio("called", i, "contacts", sa7);
				} else if (p_b_No.contains("-11.")) {
					launch_an_app("phone");
					callHistory();
					validateCallLog_Orio("called", i, "contacts", sa7);
				} else if (p_b_No.contains("-26.")) {
					launch_an_app("phone");
					callHistory();
					validateCallLog_Orio("called", i, "contacts", sa7);
				} else {
					launch_an_app("phone");
					callHistory();
					validateCallLog_Orio("called", i, "contacts", sa7);
				}
			}
		} else {
			test.log(LogStatus.SKIP, "NT: IMS is Enabled");
		}
		sa7.assertAll();

	}
	@Test(priority = 8, dataProvider = "XP8_Stability", dataProviderClass = DataProviders.class)
	public void XP8_TC_08_Stability_Validate_MO_IMSVoiceCall_from_Contacts(Hashtable<String, String> data)
			throws InterruptedException, AWTException, IOException, ParseException {
		APP_LOGS.info("===========XP8_Stability_10============");
		startAdbLog("XP8_Stability_10");
		SoftAssert sa8 = new SoftAssert();
		clearRecentApps();
		System.out.println(value);
		if (value) {
			for (int i = 1; i <= itr; i++) {

				if (p_b_No.contains("-10.") || p_b_No.contains("-00.")) {
					launch_an_app("contacts");
					searchContact("Test" + i);
					initiateCall();
					validateCallLog_Orio("called", i, "contacts", sa8);
				} else if (p_b_No.contains("-29.")) {
					launch_an_app("contacts");
					searchContact("Test" + i);
					initiateCall();
					validateCallLog_Orio("called", i, "contacts", sa8);

				} else if (p_b_No.contains("-11.")) {
					launch_an_app("contacts");
					searchContact("Test" + i);
					initiateCall();
					validateCallLog_Orio("called", i, "contacts", sa8);

				}

				else if (p_b_No.contains("-15.")) {
					launch_an_app("contacts");
					searchContact("Test" + i);
					initiateCall();
					validateCallLog_Orio("called", i, "contacts", sa8);
				} else if (p_b_No.contains("-26.")) {
					launch_an_app("contacts");
					searchContact("Test" + i);
					initiateCall();
					validateCallLog_Orio("called", i, "contacts", sa8);
				} else {
					launch_an_app("contacts");
					searchContact("Test" + i);
					initiateCall();
					validateCallLog_Orio("called", i, "contacts", sa8);
				}
			}
		} else {
			test.log(LogStatus.SKIP, "NT: IMS is not Enabled");
		}
		sa8.assertAll();
	}
	@Test(priority = 9, dataProvider = "XP8_Stability", dataProviderClass = DataProviders.class)
	public void XP8_TC_09_Stability_Validate_MO_IMSVoiceCall_from_CallHistory(Hashtable<String, String> data)
			throws InterruptedException, AWTException, IOException, ParseException {
		APP_LOGS.info("===========XP8_Stability_11============");
		startAdbLog("XP8_Stability_11");
		SoftAssert sa9 = new SoftAssert();
		clearRecentApps();
		System.out.println(value);
		if (value) {
			for (int i = 1; i <= itr; i++) {

				if (p_b_No.contains("-10.") || p_b_No.contains("-00.")) {
					launch_an_app("phone");
					selectPage(Locators_DeviceStability.callHistry_notification);
					callHistory();
					validateCallLog("called", i, "contacts", sa9);
				} else if (p_b_No.contains("-15.")) {
					launch_an_app("phone");
					selectPage(Locators_DeviceStability.callHistry_notification);
					callHistory();
					validateCallLog_Orio("called", i, "contacts", sa9);
				} else if (p_b_No.contains("-29.")) {
					launch_an_app("settings");
					clickOn_Networks_and_Internet();
					scrollToText("Airplane mode");
					launch_an_app("phone");
					selectPage(Locators_DeviceStability.callHistry_notification);
					callHistory();
					validateCallLog_Orio("called", i, "contacts", sa9);
				} else if (p_b_No.contains("-26.")) {
					launch_an_app("phone");
					selectPage(Locators_DeviceStability.callHistry_notification);
					callHistory();
					validateCallLog_Orio("called", i, "contacts", sa9);
				}

				else if (p_b_No.contains("-11.")) {
					launch_an_app("phone");
					selectPage(Locators_DeviceStability.callHistry_notification);
					callHistory();
					validateCallLog_Orio("called", i, "contacts", sa9);
				} else {
					launch_an_app("phone");
					selectPage(Locators_DeviceStability.callHistry_notification);
					callHistory();
					validateCallLog("called", i, "contacts", sa9);
				}
			}
		} else {
			test.log(LogStatus.SKIP, "NT: IMS is not Enabled");
		}
		sa9.assertAll();
	}

	
	@Test(priority = 10, dataProvider = "XP8_Stability", dataProviderClass = DataProviders.class)
	public void XP8_TC_10_Stability_Validate_WiFi_ConnectDisconnect_SSID(Hashtable<String, String> data)
			throws InterruptedException, AWTException, IOException, ParseException {
		APP_LOGS.info("===========XP8_Stability_12============");
		startAdbLog("XP8_Stability_12");
		recordVideo("XP8_Stability_12");
		SoftAssert sa10 = new SoftAssert();
		chrome_Clear();
		clearRecentApps();
		if (p_b_No.contains("-10.") || p_b_No.contains("-00.")) {
			launch_an_app("settings");
	       clickOn_Networks_and_Internet();
    		scrollToText("Mobile network");
			disable_MobileData();
			clickOn_BackBtn();
			clickOn_Wifi();
			enable_Wifi();
			minWait();
			checkWifiConnected();
			for (int j = 1; j <= itr; j++) {
				launch_an_app("settings");
				clickOn_Networks_and_Internet();
				clickOn_Wifi();
				selectSSIDwifi();
				enterPassword(pw);
				validateWifiConnect(j, sa10);
				launch_an_app("settings");
				clickOn_Networks_and_Internet();
				clickOn_Wifi();			
				selectSSIDwifi();
				disconnectSSIDifConnected();
				minWait();
			}
		} else if (p_b_No.contains("-29.")) {
			System.out.println("sprint");
			launch_an_app("settings");
			clickOn_Networks_and_Internet();
			scrollToText("Mobile network");
			disable_MobileData();

			clickOn_BackBtn();
			clickOn_Wifi();
			enable_Wifi();
			minWait();
			checkWifiConnected();
			for (int j = 1; j <= itr; j++) {
				launch_an_app("settings");
				clickOn_Networks_and_Internet();
				clickOn_Wifi();
				selectSSIDwifi();
				enterPassword(pw);
				validateWifiConnect(j, sa10);
				launch_an_app("settings");
				clickOn_Networks_and_Internet();
				clickOn_Wifi();
				selectSSIDwifi();
				disconnectSSIDifConnected();

			}
		} else if (p_b_No.contains("-11.")) {
			System.out.println("BELL");
			launch_an_app("settings");
			clickOn_Networks_and_Internet();
			scrollToText("Mobile network");
			disable_MobileData();

			clickOn_BackBtn();
			clickOn_Wifi();
			minWait();
			checkWifiConnected();
			for (int j = 1; j <= itr; j++) {
				launch_an_app("settings");
				clickOn_Networks_and_Internet();
				clickOn_Wifi();
				selectSSIDwifi();
				enterPassword(pw);
				validateWifiConnect(j, sa10);
				launch_an_app("settings");
				clickOn_Networks_and_Internet();
				clickOn_Wifi();
				
				selectSSIDwifi();
				disconnectSSIDifConnected();
			}
		} else if (p_b_No.contains("-26.")) {
			System.out.println("SL");
			launch_an_app("settings");
			clickOn_Networks_and_Internet();
			scrollToText("Mobile network");
			disable_MobileData();

			clickOn_BackBtn();
			clickOn_Wifi();
			enableFeature(Locators_DeviceStability.enabled, Locators_DeviceStability.disabled,
					Locators_DeviceStability.switch_Title);
			minWait();
			checkWifiConnected();
			for (int j = 1; j <= itr; j++) {
				launch_an_app("settings");
				clickOn_Networks_and_Internet();
				clickOn_Wifi();
				selectSSIDwifi();
				enterPassword(pw);
				validateWifiConnect(j, sa10);
				launch_an_app("settings");
				clickOn_Networks_and_Internet();
				clickOn_Wifi();
				selectSSIDwifi();
				disconnectSSIDifConnected();
			}
		} else if (p_b_No.contains("-15.")) {
			System.out.println("vzw");
			launch_an_app("settings");
			clickOn_Networks_and_Internet();
			scrollToText("Mobile network");
			disable_MobileData();
			clickOn_BackBtn();
			clickOn_Wifi();
			enable_Wifi();
			minWait();
			checkWifiConnected();
			for (int j = 1; j <= itr; j++) {
				launch_an_app("settings");
				clickOn_Networks_and_Internet();
				clickOn_Wifi();
				selectSSIDwifi();
				enterPassword(pw);
				validateWifiConnect(j, sa10);
				launch_an_app("settings");
				clickOn_Networks_and_Internet();
				clickOn_Wifi();
				
				selectSSIDwifi();
				disconnectSSIDifConnected();
			}
		}

		sa10.assertAll();

	}

	@Test(priority = 11, dataProvider = "XP8_Stability", dataProviderClass = DataProviders.class)
	public void XP8_TC_11_Stability_Validate_WiFi_Turn_ON_OFF(Hashtable<String, String> data)
			throws InterruptedException, AWTException, IOException, ParseException {
		APP_LOGS.info("===========XP8_Stability_13============");
		startAdbLog("XP8_Stability_13");
		recordVideo("XP8_Stability_13");
		SoftAssert sa11 = new SoftAssert();
		chrome_Clear();
		clearRecentApps();
		if (p_b_No.contains("-10.") || p_b_No.contains("-00.")) {
			 
			launch_an_app("settings");
			clickOn_Networks_and_Internet();
			scrollToText("Mobile network");
			disable_MobileData();
			minWait();
			clickOn_BackBtn();
			clickOn_Wifi();
			enable_Wifi();
			selectSSIDwifi();
			enterPassword(pw);
			for (int j = 1; j <= itr; j++) {
				System.out.println("In Loop");
				launch_an_app("settings");
				clickOn_Networks_and_Internet();
				clickOn_Wifi();
				enable_Wifi();
				launch_an_app("browser");
				clearChromePermission();
				minWait();
				validate_And_BrowseIn_Chrome(j, sa11);
				launch_an_app("settings");
				clickOn_Networks_and_Internet();
				clickOn_Wifi();
				disable_Wifi();

				launch_an_app("browser");
				clearChromePermission();
				enter_URL();
				validate_Page_IsNotLoaded_InChrome_A(j, sa11);
				customWait(3000);
				if (isElementExist(Locators_DeviceStability.alert_OKBtn)) {
					clickBtn(Locators_DeviceStability.alert_OKBtn);
					System.out.println("Im Clicking Ok 3rd ");
				}
			}
			} else if (p_b_No.contains("-15.")) {
			
			launch_an_app("settings");
			clickOn_Networks_and_Internet();
			scrollToText("Mobile network");
			disable_MobileData();
			minWait();
			clickOn_BackBtn();
			clickOn_Wifi();
			enable_Wifi();
			selectSSIDwifi();
			enterPassword(pw);
			for (int j = 1; j <= itr; j++) {
				System.out.println("In Loop");
				launch_an_app("settings");
				clickOn_Networks_and_Internet();
				clickOn_Wifi();
				enable_Wifi();
				launch_an_app("browser");
				clearChromePermission_vzw();
				minWait();
				validate_And_BrowseIn_Chrome(j, sa11);
				launch_an_app("settings");
				clickOn_Networks_and_Internet();
				clickOn_Wifi();
				disable_Wifi();

				launch_an_app("browser");
				clearChromePermission_vzw();
				
				validate_Page_IsNotLoaded_InChrome(j, sa11);
				customWait(3000);
				if (isElementExist(Locators_DeviceStability.alert_OKBtn)) {
					clickBtn(Locators_DeviceStability.alert_OKBtn);
					System.out.println("Im Clicking Ok 3rd ");
				}
			}
			} else if (p_b_No.contains("-26.")) {
			 
			launch_an_app("settings");
			clickOn_Networks_and_Internet();
			scrollToText("Mobile network");
			disable_MobileData();
			minWait();
			clickOn_BackBtn();
			clickOn_Wifi();
			enable_Wifi();
			selectSSIDwifi();
			enterPassword(pw);
			for (int j = 1; j <= itr; j++) {
				System.out.println("In Loop");
				launch_an_app("settings");
				clickOn_Networks_and_Internet();
				clickOn_Wifi();
				enable_Wifi();
				launch_an_app("browser");
				clearChromePermission();
				minWait();
				validate_And_BrowseIn_Chrome(j, sa11);
				launch_an_app("settings");
				clickOn_Networks_and_Internet();
				clickOn_Wifi();
				disable_Wifi();

				launch_an_app("browser");
				clearChromePermission();
				
				validate_Page_IsNotLoaded_InChrome(j, sa11);
				customWait(3000);
				if (isElementExist(Locators_DeviceStability.alert_OKBtn)) {
					clickBtn(Locators_DeviceStability.alert_OKBtn);
					System.out.println("Im Clicking Ok 3rd ");
				}
			}
		} else if (p_b_No.contains("-29.")) {
			 
			launch_an_app("settings");
			clickOn_Networks_and_Internet();
			scrollToText("Mobile network");
			disable_MobileData();
			minWait();
			clickOn_BackBtn();
			clickOn_Wifi();
			enable_Wifi();
			selectSSIDwifi();
			enterPassword(pw);
			for (int j = 1; j <= itr; j++) {
				System.out.println("In Loop");
				launch_an_app("settings");
				clickOn_Networks_and_Internet();
				clickOn_Wifi();
				enable_Wifi();
				launch_an_app("browser");
				clearChromePermission();
				minWait();
				validate_And_BrowseIn_Chrome(j, sa11);
				launch_an_app("settings");
				clickOn_Networks_and_Internet();
				clickOn_Wifi();
				disable_Wifi();

				launch_an_app("browser");
				clearChromePermission();
				
				validate_Page_IsNotLoaded_InChrome(j, sa11);
				customWait(3000);
				if (isElementExist(Locators_DeviceStability.alert_OKBtn)) {
					clickBtn(Locators_DeviceStability.alert_OKBtn);
					System.out.println("Im Clicking Ok 3rd ");
				}
			}		}

		else if (p_b_No.contains("-11.")) {
			 
			launch_an_app("settings");
			clickOn_Networks_and_Internet();
			scrollToText("Mobile network");
			disable_MobileData();
			minWait();
			clickOn_BackBtn();
			clickOn_Wifi();
			enable_Wifi();
			selectSSIDwifi();
			enterPassword(pw);
			for (int j = 1; j <= itr; j++) {
				System.out.println("In Loop");
				launch_an_app("settings");
				clickOn_Networks_and_Internet();
				clickOn_Wifi();
				enable_Wifi();
				launch_an_app("browser");
				clearChromePermission();
				minWait();
				validate_And_BrowseIn_Chrome(j, sa11);
				launch_an_app("settings");
				clickOn_Networks_and_Internet();
				clickOn_Wifi();
				disable_Wifi();

				launch_an_app("browser");
				clearChromePermission();
				
				validate_Page_IsNotLoaded_InChrome(j, sa11);
				customWait(3000);
				if (isElementExist(Locators_DeviceStability.alert_OKBtn)) {
					clickBtn(Locators_DeviceStability.alert_OKBtn);
					System.out.println("Im Clicking Ok 3rd ");
				}
			}		}

		sa11.assertAll();
	}
	@Test(priority = 12, dataProvider = "XP8_Stability", dataProviderClass = DataProviders.class)
	public void XP8_TC_12_Stability_Validate_Browse_Visit_WebPage(Hashtable<String, String> data)
			throws InterruptedException, AWTException, IOException, ParseException {
		APP_LOGS.info("===========XP8_Stability_14============");
		startAdbLog("XP8_Stability_14");
		recordVideo("XP8_Stability_14");
		SoftAssert sa12 = new SoftAssert();
		chrome_Clear();
		clearRecentApps();
		if (p_b_No.contains("-10.") || p_b_No.contains("-00.")) {
			 
			launch_an_app("settings");
			clickOn_Networks_and_Internet();
			scrollToText("Mobile network");
			enable_Mobiledata();
			clickOn_BackBtn();
			clickOn_Wifi();
            enable_Wifi();
			launch_an_app("browser");

			clearChromePermission();
			for (int i = 1; i <= itr; i++) {
				validate_And_BrowseIn_Chrome(i, sa12);
				customWait(5000);
			}
		}

		else if (p_b_No.contains("-15.")) {
			 
			launch_an_app("settings");
			clickOn_Networks_and_Internet();
			scrollToText("Mobile network");
			enable_Mobiledata();
			clickOn_BackBtn();
			clickOn_Wifi();
            enable_Wifi();
			launch_an_app("browser");

			clearChromePermission_vzw();
			for (int i = 1; i <= itr; i++) {
				validate_And_BrowseIn_Chrome(i, sa12);
				customWait(5000);
			}
		} else if (p_b_No.contains("-29.")) {
			launch_an_app("settings");
			clickOn_Networks_and_Internet();
			scrollToText("Mobile network");
			enable_Mobiledata();
			clickOn_BackBtn();
			clickOn_Wifi();
            enable_Wifi();
			launch_an_app("browser");

			clearChromePermission();
			for (int i = 1; i <= itr; i++) {
				validate_And_BrowseIn_Chrome(i, sa12);
				customWait(5000);
			}
		} else if (p_b_No.contains("-26.")) {
			 
			launch_an_app("settings");
			clickOn_Networks_and_Internet();
			scrollToText("Mobile network");

           enable_Mobiledata();
			clickOn_BackBtn();
			clickOn_Wifi();
            enable_Wifi();
			launch_an_app("browser");

			clearChromePermission();
			for (int i = 1; i <= itr; i++) {
				validate_And_BrowseIn_Chrome(i, sa12);
				customWait(5000);
			}
		} else if (p_b_No.contains("-11.")) {
			 
			launch_an_app("settings");
			clickOn_Networks_and_Internet();
			scrollToText("Mobile network");

           enable_Mobiledata();
			clickOn_BackBtn();
			clickOn_Wifi();
			
			
            enable_Wifi();
			launch_an_app("browser");

			clearChromePermission();
			for (int i = 1; i <= itr; i++) {
				validate_And_BrowseIn_Chrome(i, sa12);
				customWait(5000);
			}
		}
		sa12.assertAll();

	}
	@Test(priority = 13, dataProvider = "XP8_Stability", dataProviderClass = DataProviders.class)
	public void XP8_TC_13_Post_Condition(Hashtable<String, String> data)
			throws InterruptedException, AWTException, IOException, ParseException {
		APP_LOGS.info("===========XP8_Stability_14============");
		startAdbLog("XP8_Stability_14");
		recordVideo("XP8_Stability_14");
		SoftAssert sa12 = new SoftAssert();
		deleteall_SMS();
		delete_Call_From_Call_History();
		delete_contact();
	}
	
}
