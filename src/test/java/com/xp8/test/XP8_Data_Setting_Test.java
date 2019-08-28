package com.xp8.test;

import java.awt.AWTException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.util.Hashtable;
import java.util.Properties;

//import org.apache.poi.ss.formula.PlainCellCache.Loc;
import org.json.simple.parser.ParseException;
import org.junit.Ignore;
import org.openqa.selenium.ScreenOrientation;
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
import com.xp5S.util.GetMethods;
import com.xp5S.util.appiumService;
import com.xp8.util.DataProviders;
import com.xp8.util.ExcelConstants;
import com.xp8.util.ExcelReader;
//import com.xp8.util.GetMethods;

import com.xp8.util.Locators_BaseUtil;
import com.xp8.util.Locators_Data_Setting;
import com.xp8.util.XP8_Data_Setting_Util;


import OCR.Read_File;
import OCR.my_main;
import io.appium.java_client.android.AndroidKeyCode;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class XP8_Data_Setting_Test extends XP8_Data_Setting_Util {

	public ExcelReader excel;
	Properties properties;
	public static ExtentTestInterruptedException testexception;

	@BeforeSuite
	public void beforeSuite() throws FileNotFoundException, InterruptedException, IOException, ParseException,
			ParseException, ParseException, ParseException, ParseException, ParseException {
		System.out.println("before suite");
		extent = new ExtentReports("src/test/resources/extentreport/XP8_Data_Setting_Test.html", true); // Provide
																										// Desired
																										// Report
																										// Directory
																										// Location and
																										// Name
		extent.loadConfig(new File("src/test/resources/StorageFile/ReportsConfig.xml"));
		extent.addSystemInfo("Build #", JsonFileReaderAndWriter.primaryDevFirmwareReader())
				.addSystemInfo("Product", JsonFileReaderAndWriter.primaryDevModelReader())
				.addSystemInfo("Operator", JsonFileReaderAndWriter.primaryDevOperatorReader());
		fetch_Devices_Details();

	}

	@BeforeSuite
	public void numofTestCases() throws ClassNotFoundException {

		appiumService.TOTAL_NUM_OF_TESTCASES = GetMethods.TotalTestcase("XP8_TC", this.getClass());
	}

	@BeforeMethod()
	public void beforeMethod(Method method) {
		test = extent.startTest((this.getClass().getSimpleName() + " :: " + method.getName()), method.getName()); // Test
		disable_Airplane_Mode(); // Case
		// Here
	}

	@BeforeClass
	public void copyFilesToDevice() throws IOException, ParseException, ParseException, ParseException, ParseException,
			ParseException, ParseException {
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
	public void tearDown(ITestResult result, Method method) throws IOException, ParseException, ParseException,
			ParseException, ParseException, ParseException, ParseException, InterruptedException {
		if (result.getStatus() == ITestResult.FAILURE) {

			String screenshot_path = captureScreenshot(method.getName());
			String image = test.addScreenCapture(screenshot_path);
			test.log(LogStatus.FAIL, result.getThrowable());
			clearRecentApps();
		}
		extent.endTest(test);
		extent.flush();
	}

	@BeforeTest
	public void setupSys() throws MalformedURLException, InterruptedException, FileNotFoundException, AWTException {
		properties = loadDriverAndProperties();
		System.out.println("before test");
		Locators_Data_Setting loc = new Locators_Data_Setting(aDriver);
		Locators_BaseUtil loc1 = new Locators_BaseUtil(aDriver);
		PageFactory.initElements(new AppiumFieldDecorator(aDriver), loc);
		PageFactory.initElements(new AppiumFieldDecorator(aDriver), loc1);
		excel = new ExcelReader(ExcelConstants.XP5S_XL_PATH);
	}
//----------------------test-----------------------------
	@Test(priority = 0, dataProvider = "XP8_Data_Setting", dataProviderClass = DataProviders.class)
	public void XP8_TC_00_Data_Setting_Precondition(Hashtable<String, String> data)
			throws InterruptedException, AWTException, IOException {
		SoftAssert sa = new SoftAssert();
		APP_LOGS.info("===========XP8_TC_01_Data_Setting============");
		clearRecentApps();
		performAction();
		launch_an_app("settings");
		navigate_To_NetworkandInternet();
		navigate_To_MobileNetwork();
		navigate_to_AccessPointName();
		// reset the APN
		reset_To_Default();

		sa.assertAll();

	}

	@Test(priority = 1, dataProvider = "XP8_Data_Setting", dataProviderClass = DataProviders.class)
	public void XP8_TC_01_Data_Setting_Navigate_APN_Screen(Hashtable<String, String> data)
			throws InterruptedException, AWTException, IOException {
		SoftAssert sa = new SoftAssert();
		APP_LOGS.info("===========XP8_TC_01_Data_Setting============");
		clearRecentApps();
		launch_an_app("settings");
		navigate_To_NetworkandInternet();
		navigate_To_MobileNetwork();
		if (p_b_No.contains("-10.") || p_b_No.contains("-00.")) {
			// navigate to APN screen

			navigate_to_AccessPointName();
			// Validate APN Screen
			validate_navigatetoAPNscreen(sa);
			// verify all the field
			click_ON_APNSetting();
			validate_APNSettingopt(sa);
			click_on_Back();
			add_APN_Field();
			Validate_Add_APNfield(sa);
			discard_APN();
		} else if (p_b_No.contains("-11.")) {
			// navigate to APN screen
			navigate_to_AccessPointName();
			// Validate APN Screen
			validate_navigatetoAPNscreen(sa);
			// verify all the field
			click_ON_APNSetting();
			validate_APNSettingopt(sa);
			click_on_Back();
			add_APN_Field();
			Validate_Add_APNfield(sa);
			discard_APN();

		} else if (p_b_No.contains("-15.")) {
			// navigate to APN screen

			navigate_to_AccessPointName();
			// Validate APN Screen
			validate_navigatetoAPNscreen(sa);
			// verify all the field
			click_ON_APNSetting();
			validate_APNSettingopt(sa);
			click_on_Back();
			add_APN_Field();
			Validate_Add_APNfield(sa);
			discard_APN();

		} else if (p_b_No.contains("-26.")) {
			// navigate to APN screen

			navigate_to_AccessPointName();
			// Validate APN Screen
			validate_navigatetoAPNscreen(sa);
			// verify all the field
			click_ON_APNSetting();
			validate_APNSettingopt(sa);
			click_on_Back();

		} else if (p_b_No.contains("29")) {

			// navigate to APN screen

			navigate_to_AccessPointName();
			// Validate APN Screen
			validate_navigatetoAPNscreen(sa);
			// verify all the field
			click_ON_APNSetting();
			validate_APNSettingopt(sa);
			click_on_Back();
			add_APN_Field();
			Validate_Add_APNfield(sa);
			discard_APN();

		} else {
			navigate_to_AccessPointName();
			// Validate APN Screen
			validate_navigatetoAPNscreen(sa);
			// verify all the field
			click_ON_APNSetting();
			validate_APNSettingopt(sa);
			click_on_Back();
			add_APN_Field();
			Validate_Add_APNfield(sa);
			discard_APN();

		}

		sa.assertAll();

	}

	@Test(priority = 2, dataProvider = "XP8_Data_Setting", dataProviderClass = DataProviders.class)
	public void XP8_TC_02_Data_Setting_ADD_Edit_Delete_APN(Hashtable<String, String> data)
			throws InterruptedException, AWTException, IOException {
		SoftAssert sa = new SoftAssert();

		APP_LOGS.info("===========XP8_TC_02_Data_Setting============");
		clearRecentApps();
		launch_an_app("settings");
		navigate_To_NetworkandInternet();
		navigate_To_MobileNetwork();

		if (p_b_No.contains("-10.") || p_b_No.contains("-00.")) {
			// navigate to APN SCreen
			navigate_to_APN();
			// add APN to the APNs Scree
			add_APNName(data.get("name1"));
			add_APNNo(data.get("APN1"));
			save_APN();
			validate_Added_APN(sa);
			// edit added APN in APNs Screen
			editAPNnumber();
			add_APNName(data.get("name"));
			add_APNNo(data.get("APN"));
			save_APN();
			webwait(Locators_Data_Setting.apnsframe, 30);
			validate_edited_APN(sa);
			deleteAPNnumber();
			validate_deleted_APN(sa);
		} else if (p_b_No.contains("-11.")) {
			// navigate to APN SCreen
			navigate_to_APN();
			// add APN to the APNs Screen
			add_APNName(data.get("name1"));
			add_APNNo(data.get("APN1"));
			save_APN();
			wait(Locators_Data_Setting.apnsframe, 30);
			// verify APN is Added to the APN Screen
			validate_Added_APN(sa);
			// edit added APN in APNs Screen
			editAPNnumber();
			add_APNName(data.get("name"));
			add_APNNo(data.get("APN"));
			save_APN();
			wait(Locators_Data_Setting.apnsframe, 30);
			validate_edited_APN(sa);
			deleteAPNnumber();
			validate_deleted_APN(sa);

		} else if (p_b_No.contains("-15.")) {
			navigate_to_APN();
			// add APN to the APNs Screen
			add_APNName(data.get("name1"));
			add_APNNo(data.get("APN1"));
			save_APN();
			webwait(Locators_Data_Setting.apnsframe, 30);
			// verify APN is Added to the APN Screen
			validate_Added_APN(sa);
			// edit added APN in APNs Screen
			editAPNnumber();
			add_APNName(data.get("name"));
			add_APNNo(data.get("APN"));
			save_APN();
			webwait(Locators_Data_Setting.apnsframe, 30);
			validate_edited_APN(sa);
			deleteAPNnumber();
			validate_deleted_APN(sa);

		} else if (p_b_No.contains("-26.")) {
			// navigate to APN SCreen
			navigate_to_APN_A();
			// add APN to the APNs Screen
			
			Validate_Add_APNfield_A(sa);
		} else if (p_b_No.contains("29")) {

			// navigate to APN SCreen
			navigate_to_APN();
			// add APN to the APNs Screen
			add_APNName(data.get("name1"));
			add_APNNo(data.get("APN1"));
			save_APN();
			wait(Locators_Data_Setting.apnsframe, 30);
			// verify APN is Added to the APN Screen
			validate_Added_APN(sa);
			// edit added APN in APNs Screen
			editAPNnumber();
			add_APNName(data.get("name"));
			add_APNNo(data.get("APN"));
			save_APN();
			wait(Locators_Data_Setting.apnsframe, 30);
			validate_edited_APN(sa);
			deleteAPNnumber();
			validate_deleted_APN(sa);
		} else {
			// navigate to APN SCreen
			navigate_to_APN();
			// add APN to the APNs Screen
			add_APNName(data.get("name1"));
			add_APNNo(data.get("APN1"));
			save_APN();
			wait(Locators_Data_Setting.apnsframe, 30);
			// verify APN is Added to the APN Screen
			validate_Added_APN(sa);
			// edit added APN in APNs Screen
			editAPNnumber();
			add_APNName(data.get("name"));
			add_APNNo(data.get("APN"));
			save_APN();
			wait(Locators_Data_Setting.apnsframe, 30);
			validate_edited_APN(sa);
			deleteAPNnumber();
			validate_deleted_APN(sa);
		}

		sa.assertAll();
	}

	@Test(priority = 3, dataProvider = "XP8_Data_Setting", dataProviderClass = DataProviders.class)
	public void XP8_TC_03_Data_Setting_Add_Maximum_APNs(Hashtable<String, String> data)
			throws InterruptedException, AWTException, IOException {
		SoftAssert sa = new SoftAssert();
		APP_LOGS.info("===========XP8_TC_03_Data_Setting============");
		clearRecentApps();
		launch_an_app("settings");
		navigate_To_NetworkandInternet();
		navigate_To_MobileNetwork();
//		  disable_Airplane_Mode();
//		 boolean enabled=enable_data();
		int n = 5;
		if (p_b_No.contains("-10.") || p_b_No.contains("-00.")) {
			navigate_to_AccessPointName();

			for (int i = 1; i < n; i++) {
				add_APN_Field();
				add_APNName(data.get("name" + i));
				add_APNNo(data.get("APN" + i));
				save_APN();
			}
			validate_Added_Max_APN(sa);
		} else if (p_b_No.contains("-11.")) {
			navigate_to_AccessPointName();

			for (int i = 1; i < n; i++) {
				add_APN_Field();
				add_APNName(data.get("name" + i));
				add_APNNo(data.get("APN" + i));
				save_APN();
			}
			validate_Added_Max_APN(sa);
		} else if (p_b_No.contains("-15.")) {
			navigate_to_AccessPointName();

			for (int i = 1; i < n; i++) {
				add_APN_Field();
				add_APNName(data.get("name" + i));
				add_APNNo(data.get("APN" + i));
				save_APN();
			}
			validate_Added_Max_APN(sa);

		} else if (p_b_No.contains("-26.")) {
			navigate_to_APN_A();
			// add APN to the APNs Screen
			
			Validate_Add_APNfield_A(sa);

		} else if (p_b_No.contains("29")) {

			navigate_to_AccessPointName();
			for (int i = 1; i < n; i++) {
				add_APN_Field();
				add_APNName(data.get("name" + i));
				add_APNNo(data.get("APN" + i));
				save_APN();
			}

			validate_Added_Max_APN(sa);

		} else {

			navigate_to_AccessPointName();
			for (int i = 1; i < n; i++) {
				add_APN_Field();
				add_APNName(data.get("name" + i));
				add_APNNo(data.get("APN" + i));
				save_APN();
			}

			validate_Added_Max_APN(sa);

		}

		sa.assertAll();
	}
@Test(priority = 4, dataProvider = "XP8_Data_Setting", dataProviderClass = DataProviders.class)
	public void XP8_TC_04_Data_Setting_select_diff_Field_and_Authentication_Type_APN(Hashtable<String, String> data)
			throws InterruptedException, AWTException, IOException {
		SoftAssert sa = new SoftAssert();
		APP_LOGS.info("===========XP8_TC_04_Data_Setting============");
		clearRecentApps();
		launch_an_app("settings");
		navigate_To_NetworkandInternet();
		navigate_To_MobileNetwork();
		if (p_b_No.contains("-10.") || p_b_No.contains("-00.")) {
			navigate_to_APN();
			// Add different APN fields
			select_APNFields(data.get("name"), data.get("APN"), data.get("proxy"), data.get("port"),
					data.get("Username"), data.get("Password"), data.get("Server"), data.get("MMSC"));
			validate_differentField_In_APN(sa);
			// select authentication Type(pap).

			select_Authenticationtype(data.get("chap"), sa);
			// validate authentication type
			save_APN();

		} else if (p_b_No.contains("-11.")) {
			navigate_to_APN();
			// Add different APN fields
			select_APNFields(data.get("name"), data.get("APN"), data.get("proxy"), data.get("port"),
					data.get("Username"), data.get("Password"), data.get("Server"), data.get("MMSC"));
			validate_differentField_In_APN(sa);
			// select authentication Type(pap).
			select_Authenticationtype(data.get("chap"), sa);
			// validate authentication type
			save_APN();

		} else if (p_b_No.contains("-15.")) {
			navigate_to_APN();
			// Add different APN fields
			select_APNFields(data.get("name"), data.get("APN"), data.get("proxy"), data.get("port"),
					data.get("Username"), data.get("Password"), data.get("Server"), data.get("MMSC"));
			validate_differentField_In_APN(sa);
			// select authentication Type(pap).
			select_Authenticationtype(data.get("chap"), sa);
			// validate authentication type
			save_APN();

		} else if (p_b_No.contains("-26.")) {
			navigate_to_APN_A();
			// add APN to the APNs Screen
			
			Validate_Add_APNfield_A(sa);
		} else if (p_b_No.contains("29")) {
			navigate_to_APN();
			// Add different APN fields
			select_APNFields(data.get("name"), data.get("APN"), data.get("proxy"), data.get("port"),
					data.get("Username"), data.get("Password"), data.get("Server"), data.get("MMSC"));
			validate_differentField_In_APN(sa);
			// select authentication Type(pap).

			select_Authenticationtype(data.get("pap"), sa);
		
			// validate authentication type
			save_APN();
		} else {
			navigate_to_APN();
			// Add different APN fields
			select_APNFields(data.get("name"), data.get("APN"), data.get("proxy"), data.get("port"),
					data.get("Username"), data.get("Password"), data.get("Server"), data.get("MMSC"));
			validate_differentField_In_APN(sa);
			// select authentication Type(pap).

			select_Authenticationtype(data.get("pap"), sa);
		
			// validate authentication type
			save_APN();
		}

		sa.assertAll();
	}

	@Test(priority = 5, dataProvider = "XP8_Data_Setting", dataProviderClass = DataProviders.class)
	public void XP8_TC_5_Data_Setting_APN_Received_Call(Hashtable<String, String> data)
			throws InterruptedException, AWTException, IOException {
		SoftAssert sa = new SoftAssert();
		// discard the apn from the list
		APP_LOGS.info("===========XP8_TC_08_Data_Setting============");
		clearRecentApps();
		launch_an_app("settings");
		navigate_To_NetworkandInternet();
		navigate_To_MobileNetwork();
//		  disable_Airplane_Mode();
//		  boolean enabled=enable_data();

		if (p_b_No.contains("-10.") || p_b_No.contains("-00.")) {
			navigate_to_APN();
			add_APNName(data.get("name1"));
			make_Call_from_RefDev();
			customWait(5000);
			receiveCallInpriDevice();
			customWait(5000);
			endCall_RefDevice();
			waituntilnew(Locators_Data_Setting.APNno, 30);
			validate_Receive_Call_In_PriDev(sa);
			add_APNNo(data.get("APN1"));
			save_APN();
		} else if (p_b_No.contains("-11.")) {
			navigate_to_APN();
			add_APNName(data.get("name1"));
			make_Call_from_RefDev();
			customWait(5000);
			receiveCallInpriDevice();
			customWait(5000);
			endCall_RefDevice();
			waituntilnew(Locators_Data_Setting.APNno, 30);
			validate_Receive_Call_In_PriDev(sa);
			add_APNNo(data.get("APN1"));
			save_APN();

		} else if (p_b_No.contains("-15.")) {
			navigate_to_APN();
			add_APNName(data.get("name1"));
			make_Call_from_RefDev();
			customWait(5000);
			receiveCallInpriDevice();
			customWait(5000);
			endCall_RefDevice();
			waituntilnew(Locators_Data_Setting.APNno, 30);
			validate_Receive_Call_In_PriDev(sa);
			add_APNNo(data.get("APN1"));
			save_APN();

		} else if (p_b_No.contains("-26.")) {
			navigate_to_APN_A();
			// add APN to the APNs Screen
			
			Validate_Add_APNfield_A(sa);
		} else if (p_b_No.contains("29")) {
			navigate_to_APN();
			add_APNName(data.get("name1"));
			make_Call_from_RefDev();
			customWait(5000);
			receiveCallInpriDevice();
			customWait(5000);
			endCall_RefDevice();
			waituntilnew(Locators_Data_Setting.APNno, 30);
			validate_Receive_Call_In_PriDev(sa);
			add_APNNo(data.get("APN1"));
			save_APN();
		} else {
			navigate_to_APN();
			add_APNName(data.get("name1"));
			make_Call_from_RefDev();
			customWait(5000);
			receiveCallInpriDevice();
			customWait(5000);
			endCall_RefDevice();
			waituntilnew(Locators_Data_Setting.APNno, 30);
			validate_Receive_Call_In_PriDev(sa);
			add_APNNo(data.get("APN1"));
			save_APN();
		}

		sa.assertAll();
	}

	@Test(priority = 6, dataProvider = "XP8_Data_Setting", dataProviderClass = DataProviders.class)
	public void XP8_TC_06_Data_Setting_Resettodefault(Hashtable<String, String> data)
			throws InterruptedException, AWTException, IOException {
		SoftAssert sa = new SoftAssert();
		APP_LOGS.info("===========XP8_TC_05_Data_Setting============");
		clearRecentApps();
		launch_an_app("settings");
		navigate_To_NetworkandInternet();
		navigate_To_MobileNetwork();
//		  disable_Airplane_Mode();
//		  boolean enabled=enable_data();

		if (p_b_No.contains("-10.") || p_b_No.contains("-00.")) {
			navigate_to_AccessPointName();
			// reset the APN
			reset_To_Default();
			// Verify APN is reseted or not
			validate_Reset_to_Default(sa);
		} else if (p_b_No.contains("-11.")) {
			navigate_to_AccessPointName();
			// reset the APN
			reset_To_Default();
			// Verify APN is reseted or not
			validate_Reset_to_Default(sa);

		} else if (p_b_No.contains("-15.")) {
			navigate_to_AccessPointName();
			// reset the APN
			reset_To_Default();
			// Verify APN is reseted or not
			validate_Reset_to_Default(sa);

		} else if (p_b_No.contains("-26.")) {
			navigate_to_AccessPointName();
			// reset the APN
			reset_To_Default();
			// Verify APN is reseted or not
			validate_Reset_to_Default(sa);
		} else if (p_b_No.contains("29")) {
			navigate_to_AccessPointName();
			// reset the APN
			reset_To_Default();
			// Verify APN is reseted or not
			validate_Reset_to_Default(sa);

		} else {
			navigate_to_AccessPointName();
			// reset the APN
			reset_To_Default();
			// Verify APN is reseted or not
			validate_Reset_to_Default(sa);
		}

		sa.assertAll();
	}

	@Test(priority = 7, dataProvider = "XP8_Data_Setting", dataProviderClass = DataProviders.class)
	public void XP8_TC_07_Data_Setting_view_And_Selected_APNs(Hashtable<String, String> data)
			throws InterruptedException, AWTException, IOException {
		SoftAssert sa = new SoftAssert();
		// view and select the apn in APNs Screen
		APP_LOGS.info("===========XP8_TC_08_Data_Setting============");
		clearRecentApps();
		launch_an_app("settings");
		navigate_To_NetworkandInternet();
		navigate_To_MobileNetwork();
//		  disable_Airplane_Mode();
//		  boolean enabled=enable_data();
		if (p_b_No.contains("-10.") || p_b_No.contains("-00.")) {
			navigate_to_APN();
			add_APNName(data.get("name1"));
			add_APNNo(data.get("APN1"));
			save_APN();
			view_And_Select_APNs(data.get("name1"));
			validate_View_And_Select_APNs(sa);

		} else if (p_b_No.contains("-11.")) {
			navigate_to_APN();
			add_APNName(data.get("name1"));
			add_APNNo(data.get("APN1"));
			save_APN();
			view_And_Select_APNs(data.get("name1"));
			validate_View_And_Select_APNs(sa);

		} else if (p_b_No.contains("-15.")) {
			navigate_to_APN();
			add_APNName(data.get("name1"));
			add_APNNo(data.get("APN1"));
			save_APN();
			view_And_Select_APNs(data.get("name1"));
			validate_View_And_Select_APNs(sa);

		} else if (p_b_No.contains("-26.")) {
			navigate_to_APN_A();
			// add APN to the APNs Screen
			
			Validate_Add_APNfield_A(sa);
		} else if (p_b_No.contains("29")) {
			navigate_to_APN();
			add_APNName(data.get("name1"));
			add_APNNo(data.get("APN1"));
			save_APN();
			view_And_Select_APNs(data.get("name1"));
			validate_View_And_Select_APNs(sa);
		} else {
			navigate_to_APN();
			add_APNName(data.get("name1"));
			add_APNNo(data.get("APN1"));
			save_APN();
			view_And_Select_APNs(data.get("name1"));
			validate_View_And_Select_APNs(sa);
		}

		sa.assertAll();
	}

	@Test(priority = 8, dataProvider = "XP8_Data_Setting", dataProviderClass = DataProviders.class)
	public void XP8_TC_08_Data_Setting_APN_Added_To_The_List(Hashtable<String, String> data)
			throws InterruptedException, AWTException, IOException {
		SoftAssert sa = new SoftAssert();
		// add apn to the APNs List
		APP_LOGS.info("===========XP8_TC_08_Data_Setting============");
		clearRecentApps();
		launch_an_app("settings");
		navigate_To_NetworkandInternet();
		navigate_To_MobileNetwork();
//		  disable_Airplane_Mode();
//		  boolean enabled=enable_data();

		if (p_b_No.contains("-10.") || p_b_No.contains("-00.")) {
			navigate_to_AccessPointName();
			AddAPNnumber(data.get("name1"), data.get("APN1"));
			validate_Added_APN_To_The_List(sa);
			apn_Clk_Backbtn();
			validate_Click_Cackbtn(sa);

		} else if (p_b_No.contains("-11.")) {
			navigate_to_AccessPointName();
			AddAPNnumber(data.get("name1"), data.get("APN1"));
			validate_Added_APN_To_The_List(sa);
			apn_Clk_Backbtn();
			validate_Click_Cackbtn(sa);

		} else if (p_b_No.contains("-15.")) {
			navigate_to_AccessPointName();
			AddAPNnumber(data.get("name1"), data.get("APN1"));
			validate_Added_APN_To_The_List(sa);
			apn_Clk_Backbtn();
			validate_Click_Cackbtn(sa);

		} else if (p_b_No.contains("-26.")) {
			navigate_to_APN_A();
			// add APN to the APNs Screen
			
			Validate_Add_APNfield_A(sa);
		} else if (p_b_No.contains("29")) {
			navigate_to_AccessPointName();
			AddAPNnumber(data.get("name1"), data.get("APN1"));
			validate_Added_APN_To_The_List(sa);
			apn_Clk_Backbtn();
			validate_Click_Cackbtn(sa);

		} else {
			navigate_to_AccessPointName();
			AddAPNnumber(data.get("name1"), data.get("APN1"));
			validate_Added_APN_To_The_List(sa);
			apn_Clk_Backbtn();
			validate_Click_Cackbtn(sa);

		}

		sa.assertAll();
	}

	@Test(priority = 9, dataProvider = "XP8_Data_Setting", dataProviderClass = DataProviders.class)
	public void XP8_TC_09_Data_Setting_discard_APNs(Hashtable<String, String> data)
			throws InterruptedException, AWTException, IOException {
		SoftAssert sa = new SoftAssert();
		// discard the apn from the list
		APP_LOGS.info("===========XP8_TC_08_Data_Setting============");
		clearRecentApps();
		launch_an_app("settings");
		navigate_To_NetworkandInternet();
		navigate_To_MobileNetwork();
//		  disable_Airplane_Mode();
//		  boolean enabled=enable_data();

		if (p_b_No.contains("-10.") || p_b_No.contains("-00.")) {
			navigate_to_AccessPointName();
			wait(Locators_Data_Setting.apnsframe, 30);
			add_APN_Field();
			add_APNName(data.get("name1"));
			discard_APN();
			Validate_Discard_APNs(sa);

		} else if (p_b_No.contains("-11.")) {
			navigate_to_AccessPointName();
			reset_To_Default();
			wait(Locators_Data_Setting.apnsframe, 30);
			add_APN_Field();
			add_APNName(data.get("name1"));
			discard_APN();
			Validate_Discard_APNs(sa);

		} else if (p_b_No.contains("-15.")) {
			navigate_to_AccessPointName();
			wait(Locators_Data_Setting.apnsframe, 30);
			add_APN_Field();
			add_APNName(data.get("name1"));
			discard_APN();
			Validate_Discard_APNs(sa);


		} else if (p_b_No.contains("-26.")) {
			navigate_to_APN_A();
			// add APN to the APNs Screen
			
			Validate_Add_APNfield_A(sa);
		} else if (p_b_No.contains("29")) {
			navigate_to_AccessPointName();
			reset_To_Default();
			wait(Locators_Data_Setting.apnsframe, 30);
			add_APN_Field();
			add_APNName(data.get("name1"));
			discard_APN();
			Validate_Discard_APNs(sa);

		} else {
			navigate_to_AccessPointName();
			wait(Locators_Data_Setting.apnsframe, 30);
			add_APN_Field();
			add_APNName(data.get("name1"));
			discard_APN();
			Validate_Discard_APNs(sa);
		}

		sa.assertAll();
	}

	@Test(priority = 10, dataProvider = "XP8_Data_Setting", dataProviderClass = DataProviders.class)
	public void XP8_TC_10_Data_Setting_addapn_without_name_and_apnno(Hashtable<String, String> data)
			throws InterruptedException, AWTException, IOException {
		SoftAssert sa = new SoftAssert();
		APP_LOGS.info("===========XP8_TC_07_Data_Setting============");
		clearRecentApps();
		launch_an_app("settings");
		navigate_To_NetworkandInternet();
		navigate_To_MobileNetwork();

		if (p_b_No.contains("-10.") || p_b_No.contains("-00.")) {
			navigate_to_APN();
			save_APN();
			validate_error_Msg(sa);
			click_On_Ok();
			discard_APN();
		} else if (p_b_No.contains("-11.")) {
			navigate_to_APN();
			save_APN();
			validate_error_Msg(sa);
			click_On_Ok();
			discard_APN();

		} else if (p_b_No.contains("-15.")) {
			navigate_to_APN();
			save_APN();
			validate_error_Msg(sa);
			click_On_Ok();
			discard_APN();

		} else if (p_b_No.contains("-26.")) {
			navigate_to_APN_A();
			// add APN to the APNs Screen
			
			Validate_Add_APNfield_A(sa);
		} else if (p_b_No.contains("29")) {
			navigate_to_APN();
			save_APN();
			minWait();
			validate_error_Msg(sa);
			click_On_Ok();
			discard_APN();
		} else {
			navigate_to_APN();
			save_APN();
			validate_error_Msg(sa);
			click_On_Ok();
			discard_APN();
		}

		sa.assertAll();
	}
	@Test(priority = 11, dataProvider = "XP8_Data_Setting", dataProviderClass = DataProviders.class)
	public void XP8_TC_11_Data_Setting_Data_Setting_Edit_SaveorCancel_APN(Hashtable<String, String> data)
			throws InterruptedException, AWTException, IOException {
		SoftAssert sa = new SoftAssert();
		// while editing apn click on cancel button
		APP_LOGS.info("===========XP8_TC_08_Data_Setting============");
		clearRecentApps();
		launch_an_app("settings");
		navigate_To_NetworkandInternet();
		navigate_To_MobileNetwork();
//		  disable_Airplane_Mode();
//		  boolean enabled=enable_data();

		if (p_b_No.contains("-10.") || p_b_No.contains("-00.")) {
			navigate_to_AccessPointName();
			AddAPNnumber(data.get("name1"), data.get("APN"));
			validate_Added_APN(sa);
			editAPNnumber();
			add_APNName(data.get("name"));
			add_APNNo(data.get("APN"));
			save_APN();
			validate_edited_APN(sa);
			CancelAPN(data.get("name"), data.get("APN"));
			validate_CancelAPN(sa);
		} else if (p_b_No.contains("-11.")) {
			navigate_to_AccessPointName();
			AddAPNnumber(data.get("name1"), data.get("APN"));
			validate_Added_APN(sa);
			editAPNnumber();
			add_APNName(data.get("name"));
			add_APNNo(data.get("APN"));
			save_APN();
			validate_edited_APN(sa);
			CancelAPN(data.get("name"), data.get("APN"));
			validate_CancelAPN(sa);
		} else if (p_b_No.contains("-15.")) {
			navigate_to_AccessPointName();
			AddAPNnumber(data.get("name1"), data.get("APN"));
			validate_Added_APN(sa);
			editAPNnumber();
			add_APNName(data.get("name"));
			add_APNNo(data.get("APN"));
			save_APN();
			validate_edited_APN(sa);
			CancelAPN(data.get("name"), data.get("APN"));
			validate_CancelAPN(sa);

		} else if (p_b_No.contains("-26.")) {
			navigate_to_APN_A();
			// add APN to the APNs Screen
			
			Validate_Add_APNfield_A(sa);
		} else if (p_b_No.contains("29")) {
			navigate_to_AccessPointName();
			AddAPNnumber(data.get("name1"), data.get("APN"));
			validate_Added_APN(sa);
			editAPNnumber();
			add_APNName(data.get("name"));
			add_APNNo(data.get("APN"));
			save_APN();
			validate_edited_APN(sa);
			CancelAPN(data.get("name"), data.get("APN"));
			validate_CancelAPN(sa);

		} else {
			navigate_to_AccessPointName();
			AddAPNnumber(data.get("name1"), data.get("APN"));
			validate_Added_APN(sa);
			editAPNnumber();
			add_APNName(data.get("name"));
			add_APNNo(data.get("APN"));
			save_APN();
			validate_edited_APN(sa);
			CancelAPN(data.get("name"), data.get("APN"));
			validate_CancelAPN(sa);
		}

		sa.assertAll();
	}

	@Test(priority = 12, dataProvider = "XP8_Data_Setting", dataProviderClass = DataProviders.class)
	public void XP8_TC_12_Data_Setting_lock_Unlock_Screen(Hashtable<String, String> data)
			throws InterruptedException, AWTException, IOException {
		SoftAssert sa = new SoftAssert();
		APP_LOGS.info("===========XP8_TC_08_Data_Setting============");
		clearRecentApps();
		launch_an_app("settings");
		navigate_To_NetworkandInternet();
		navigate_To_MobileNetwork();

		if (p_b_No.contains("-10.") || p_b_No.contains("-00.")) {
			navigate_to_APN();
			add_APNName(data.get("name1"));
			// lock and unlock the Screen
			lock_Unlock_Screen_A();
			validate_Lock_Unlock_Screen(sa);
			add_APNNo(data.get("APN1"));
			save_APN();
		} else if (p_b_No.contains("-11.")) {
			navigate_to_APN();
			add_APNName(data.get("name1"));
			// lock and unlock the Screen
			lock_Unlock_Screen();
			validate_Lock_Unlock_Screen(sa);

			wait(Locators_Data_Setting.APNno, 10);
			add_APNNo(data.get("APN1"));
			save_APN();

		} else if (p_b_No.contains("-15.")) {
			navigate_to_APN();
			add_APNName(data.get("name1"));
			// lock and unlock the Screen
			lock_Unlock_Screen();
			validate_Lock_Unlock_Screen(sa);

			wait(Locators_Data_Setting.APNno, 10);
			add_APNNo(data.get("APN1"));
			save_APN();

		} else if (p_b_No.contains("-26.")) {
			navigate_to_APN_A();
			// add APN to the APNs Screen
			
			Validate_Add_APNfield_A(sa);

		} else if (p_b_No.contains("29")) {
			navigate_to_APN();
			add_APNName(data.get("name1"));
			// lock and unlock the Screen
			lock_Unlock_Screen();
			validate_Lock_Unlock_Screen(sa);

			wait(Locators_Data_Setting.APNno, 10);
			add_APNNo(data.get("APN1"));
			save_APN();

		} else {
			navigate_to_APN();
			add_APNName(data.get("name1"));
			// lock and unlock the Screen
			lock_Unlock_Screen_A();
			validate_Lock_Unlock_Screen(sa);
			add_APNNo(data.get("APN1"));
			save_APN();
		}

		sa.assertAll();
	}

	@Test(priority = 13, dataProvider = "XP8_Data_Setting", dataProviderClass = DataProviders.class)
	public void XP8_TC_13_Data_Setting_APN_protocol_And_Bearer(Hashtable<String, String> data)
			throws InterruptedException, AWTException, IOException {
		SoftAssert sa = new SoftAssert();
		// while adding apn select apn protocol type
		APP_LOGS.info("===========XP8_TC_08_Data_Setting============");
		clearRecentApps();
		launch_an_app("settings");
		navigate_To_NetworkandInternet();
		navigate_To_MobileNetwork();

		if (p_b_No.contains("-10.") || p_b_No.contains("-00.")) {
			navigate_to_APN();
			add_APNName(data.get("name1"));
			add_APNNo(data.get("APN1"));
			select_APN_protocol("IPv6", sa);
			select_Bearer();
			validate_Bearer(sa);
			click_on_Back();
			save_APN();
		} else if (p_b_No.contains("-11.")) {
			navigate_to_APN();
			add_APNName(data.get("name1"));
			add_APNNo(data.get("APN1"));
			select_APN_protocol("IPv6", sa);
			select_Bearer();
			validate_Bearer(sa);
			click_On_Ok();
			save_APN();

		} else if (p_b_No.contains("-15.")) {
			navigate_to_APN();
			add_APNName(data.get("name1"));
			add_APNNo(data.get("APN1"));
			select_APN_protocol("IPv6", sa);
			select_Bearer();
			validate_Bearer(sa);
			click_on_Back();
			save_APN();

		} else if (p_b_No.contains("-26.")) {
			navigate_to_APN_A();
			// add APN to the APNs Screen
			
			Validate_Add_APNfield_A(sa);


		} else if (p_b_No.contains("29.")) {
			navigate_to_APN();
			add_APNName(data.get("name1"));
			add_APNNo(data.get("APN1"));
			select_APN_protocol("IPv6", sa);
			select_Bearer();
			validate_Bearer(sa);
			click_On_Ok();
			save_APN();

		} else {
			navigate_to_APN();
			add_APNName(data.get("name1"));
			add_APNNo(data.get("APN1"));
			select_APN_protocol("IPv6", sa);
			select_Bearer();
			validate_Bearer(sa);
			click_on_Back();
			save_APN();
		}

		sa.assertAll();
	}

	@Test(priority =14, dataProvider = "XP8_Data_Setting", dataProviderClass = DataProviders.class)
	public void XP8_TC_14_Data_Setting_save_APN_without_Entering_APNNo(Hashtable<String, String> data)
			throws InterruptedException, AWTException, IOException {
		SoftAssert sa = new SoftAssert();
		APP_LOGS.info("===========XP8_TC_06_Data_Setting============");
		clearRecentApps();
		launch_an_app("settings");
		navigate_To_NetworkandInternet();
		navigate_To_MobileNetwork();
		if (p_b_No.contains("-10.") || p_b_No.contains("-00.")) {
			navigate_to_APN();
			add_APNName(data.get("name"));
			save_APN();
			// verify error msg is displayed
			Validate_APN_without_Entering_APN(data.get("errormsg"), sa);
			click_On_Ok();
			discard_APN();
		} else if (p_b_No.contains("-11.")) {
			navigate_to_APN();
			add_APNName(data.get("name"));
			save_APN();
//			waituntilnew(Locators_Data_Setting.errormsginapn, 30);
			// verify error msg is displayed
			save_APN_without_Entering_APN_b(data.get("errormsg_b"), sa);
			click_On_Ok();
			discard_APN();

		} else if (p_b_No.contains("-15.")) {
			navigate_to_APN();
			add_APNName(data.get("name"));
			save_APN();
//			waituntilnew(Locators_Data_Setting.errormsginapn, 30);
			// verify error msg is displayed
			Validate_APN_without_Entering_APN(data.get("errormsg"), sa);
			click_On_Ok();
			discard_APN();
		} else if (p_b_No.contains("-26.")) {
			navigate_to_APN_A();
			// add APN to the APNs Screen
			
			Validate_Add_APNfield_A(sa);
		} else if (p_b_No.contains("29")) {
			navigate_to_APN();
			add_APNName(data.get("name"));
			save_APN();
			// verify error msg is displayed
			Validate_APN_without_Entering_APN(data.get("errormsg"), sa);
			click_On_Ok();
			discard_APN();

		} else {
			navigate_to_APN();
			add_APNName(data.get("name"));
			save_APN();
			// verify error msg is displayed
			Validate_APN_without_Entering_APN(data.get("errormsg"), sa);
			click_On_Ok();
			discard_APN();
		}

		sa.assertAll();

	}

	@Test(priority = 15, dataProvider = "XP8_Data_Setting", dataProviderClass = DataProviders.class)
	public void XP8_TC_15_Data_Setting_add_APN_Click_Backbutton(Hashtable<String, String> data)
			throws InterruptedException, AWTException, IOException {
		SoftAssert sa = new SoftAssert();
		APP_LOGS.info("===========XP8_TC_08_Data_Setting============");
		clearRecentApps();
		launch_an_app("settings");
		navigate_To_NetworkandInternet();
		navigate_To_MobileNetwork();

		if (p_b_No.contains("-10.") || p_b_No.contains("-00.")) {
			navigate_to_APN();
			// while saving APN click back button
			add_APNName(data.get("name1"));
			add_APNNo(data.get("APN1"));
			add_APN_Click_Backbutton();
			Validate_add_APN_Then_Click_Back(sa, data.get("expectedtext"));
		} else if (p_b_No.contains("-11.")) {
			navigate_to_APN();
			// while saving APN click back button
			add_APNName(data.get("name1"));
			add_APNNo(data.get("APN1"));
			add_APN_Click_Backbutton();
			wait(Locators_Data_Setting.apnsframe, 30);
			Validate_add_APN_Then_Click_Back(sa, data.get("expectedtext"));

		} else if (p_b_No.contains("-15.")) {
			navigate_to_APN();
			// while saving APN click back button
			add_APNName(data.get("name1"));
			add_APNNo(data.get("APN1"));
			add_APN_Click_Backbutton();
			wait(Locators_Data_Setting.apnsframe, 30);
			Validate_add_APN_Then_Click_Back(sa, data.get("expectedtext"));


		} else if (p_b_No.contains("-26.")) {
			navigate_to_APN_A();
			// add APN to the APNs Screen
			
			Validate_Add_APNfield_A(sa);

		} else if (p_b_No.contains("29")) {
			navigate_to_APN();
			// while saving APN click back button
			add_APNName(data.get("name1"));
			add_APNNo(data.get("APN"));
			add_APN_Click_Backbutton();
			wait(Locators_Data_Setting.apnsframe, 30);
			Validate_add_APN_Then_Click_Back(sa, data.get("expectedtext"));

		} else {
			navigate_to_APN();
			// while saving APN click back button
			add_APNName(data.get("name1"));
			add_APNNo(data.get("APN"));
			add_APN_Click_Backbutton();
			wait(Locators_Data_Setting.apnsframe, 30);
			Validate_add_APN_Then_Click_Back(sa, data.get("expectedtext"));
		}

		sa.assertAll();
	}

	@Test(priority = 16, dataProvider = "XP8_Data_Setting", dataProviderClass = DataProviders.class)
	public void XP8_TC_16_Data_Setting_APN_protocol_IPv4_Or_IPv6(Hashtable<String, String> data)
			throws InterruptedException, AWTException, IOException {
		SoftAssert sa = new SoftAssert();
		APP_LOGS.info("===========XP8_TC_08_Data_Setting============");
		clearRecentApps();
		launch_an_app("settings");
		navigate_To_NetworkandInternet();
		navigate_To_MobileNetwork();
//		  disable_Airplane_Mode();
//		  boolean enabled=enable_data();

		if (p_b_No.contains("-10.") || p_b_No.contains("-00.")) {
			navigate_to_APN();
			add_APNName(data.get("name1"));
			add_APNNo(data.get("APN1"));
			select_APN_protocol(data.get("ipv4oripv6"), sa);
			save_APN();
		} else if (p_b_No.contains("-11.")) {
			navigate_to_APN();
			add_APNName(data.get("name1"));
			add_APNNo(data.get("APN1"));
			select_APN_protocol(data.get("ipv4oripv6"), sa);
			save_APN();

		} else if (p_b_No.contains("-15.")) {
			navigate_to_APN();
			add_APNName(data.get("name1"));
			add_APNNo(data.get("APN1"));
			select_APN_protocol(data.get("ipv4oripv6"), sa);
			save_APN();

		} else if (p_b_No.contains("-26.")) {
			navigate_to_APN_A();
			// add APN to the APNs Screen
			
			Validate_Add_APNfield_A(sa);

		} else if (p_b_No.contains("29")) {
			navigate_to_APN();
			add_APNName(data.get("name1"));
			add_APNNo(data.get("APN1"));
			select_APN_protocol(data.get("ipv4oripv6"), sa);
			save_APN();
		} else {
			navigate_to_APN();
			add_APNName(data.get("name1"));
			add_APNNo(data.get("APN1"));
			select_APN_protocol(data.get("ipv4oripv6"), sa);
			save_APN();
		}

		sa.assertAll();
	}

	@Test(priority =17, dataProvider = "XP8_Data_Setting", dataProviderClass = DataProviders.class)
	public void XP8_TC_17_Data_Setting_enable_Disable_Airplane_Mode(Hashtable<String, String> data)
			throws InterruptedException, AWTException, IOException {
		SoftAssert sa = new SoftAssert();
		// enable and disable the airplane mode
		APP_LOGS.info("===========XP8_TC_08_Data_Setting============");
		clearRecentApps();
		launch_an_app("settings");
		navigate_To_NetworkandInternet();

		if (p_b_No.contains("-10.") || p_b_No.contains("-00.")) {
			disable_AirplaneMode();
			Turn_On_Off_Airplane_Mode(4, sa);
			enable_data();
			launch_an_app("browser");
			clearChromePermission();
			enterurl();
			validate_Page_IsLoaded_InChrome(sa);
		} else if (p_b_No.contains("-11.")) {
			Turn_On_Off_Airplane_Mode(4, sa);
			launch_an_app("browser");
			clearChromePermission();
			enterurl();
			validate_Page_IsLoaded_InChrome(sa);

		} else if (p_b_No.contains("-15.")) {
			
			Turn_On_Off_Airplane_Mode_v(4, sa);
			launch_an_app("browser");
			clearChromePermission_vzw();
			enterurl();
			validate_Page_IsLoaded_InChrome(sa);

		} else if (p_b_No.contains("-26.")) {
			disable_Airplane_Mode();
			Turn_On_Off_Airplane_Mode(4, sa);
			launch_an_app("browser");
			clearChromePermission();
			enterurl();
			validate_Page_IsLoaded_InChrome_s(sa);
		} else if (p_b_No.contains("29")) {
			disable_Airplane_Mode();
			
			Turn_On_Off_Airplane_Mode(4, sa);
			launch_an_app("browser");
			clearChromePermission();
			enterurl();
			validate_And_BrowseIn_Chrome(sa);

		} else {
			disable_Airplane_Mode();
			wait(Locators_Data_Setting.airplanemodeswitchonoff, 30);
			Turn_On_Off_Airplane_Mode(4, sa);
			launch_an_app("browser");
			clearChromePermission();
			enterurl();
			validate_Page_IsLoaded_InChrome(sa);
		}

		sa.assertAll();
	}

	@Test(priority =18, dataProvider = "XP8_Data_Setting", dataProviderClass = DataProviders.class)
	public void XP8_TC_18_Data_Setting_Enable_Data(Hashtable<String, String> data)
			throws InterruptedException, AWTException, IOException {
		SoftAssert sa = new SoftAssert();
		APP_LOGS.info("===========XP8_TC_08_Data_Setting============");
		clearRecentApps();
		launch_an_app("settings");
		navigate_To_NetworkandInternet();
		navigate_To_MobileNetwork();

		if (p_b_No.contains("-10.") || p_b_No.contains("-00.")) {
			enable_data1();
			validate_enablemobile_data(sa);
			launch_an_app("browser");
			clearChromePermission();
			enterurl();
			validate_Page_IsLoaded_InChrome(sa);
		} else if (p_b_No.contains("-11.")) {
			enable_data1();
			validate_enablemobile_data(sa);
			launch_an_app("browser");
			clearChromePermission();
			enterurl();
			validate_Page_IsLoaded_InChrome(sa);
		} else if (p_b_No.contains("-15.")) {
			enable_data1();
			validate_enablemobile_data(sa);
			launch_an_app("browser");
			clearChromePermission_vzw();
			enterurl();
			validate_Page_IsLoaded_InChrome(sa);
		} else if (p_b_No.contains("-26.")) {
			enable_data1();
			validate_enablemobile_data(sa);
			launch_an_app("browser");
			clearChromePermission();
			enterurl();
			validate_Page_IsLoaded_InChrome(sa);

		} else if (p_b_No.contains("29")) {

			enable_data1();
			validate_enablemobile_data(sa);
			launch_an_app("browser");
			clearChromePermission();
			enterurl();
			validate_Page_IsLoaded_InChrome(sa);

		} else {
			enable_data1();
			validate_enablemobile_data(sa);
			launch_an_app("browser");
			clearChromePermission();
			enterurl();
			validate_Page_IsLoaded_InChrome(sa);
		}

		sa.assertAll();
	}

	@Test(priority = 19, dataProvider = "XP8_Data_Setting", dataProviderClass = DataProviders.class)
	public void XP8_TC_19_Data_Setting_Enable_Airplane(Hashtable<String, String> data)
			throws InterruptedException, AWTException, IOException {
		SoftAssert sa = new SoftAssert();
		// enable the data
		APP_LOGS.info("===========XP8_TC_08_Data_Setting============");
		clearRecentApps();
		chrome_Clear();
		launch_an_app("settings");
		navigate_To_NetworkandInternet();

		if (p_b_No.contains("-10.") || p_b_No.contains("-00.")) {
			enable_Airplane_Mode();
			validate_Mobile_network(sa);
			// launch the browser
			launch_an_app("browser");
			clearChromePermission();
			enterurl();
			validate_Page_IsNotLoaded_InChrome(sa);
		} else if (p_b_No.contains("-11.")) {
			enable_Airplane_Mode();
			validate_Mobile_network(sa);
			// launch the browser
			launch_an_app("browser");
			clearChromePermission();
			enterurl();
			validate_Page_IsNotLoaded_InChrome(sa);
		}

		else if (p_b_No.contains("-15.")) {
			enable_Airplane_Mode_O();
			validate_Mobile_network(sa);
			// launch the browser
			launch_an_app("browser");
			clearChromePermission_vzw();
			enterurl();
			validate_Page_IsNotLoaded_InChrome(sa);
		} else if (p_b_No.contains("-26.")) {
			enable_Airplane_Mode();
			validate_Mobile_network(sa);
			// launch the browser
			launch_an_app("browser");
			clearChromePermission();
			enterurl();
			validate_Page_IsNotLoaded_InChrome(sa);
		} else if (p_b_No.contains("29")) {
			enable_Airplane_Mode();
			validate_Mobile_network(sa);
			// launch the browser
			launch_an_app("browser");
			clearChromePermission();
			enterurl();
			validate_Page_IsNotLoaded_InChrome(sa);
		} else {
			enable_Airplane_Mode();
			validate_Mobile_network(sa);
			// launch the browser
			launch_an_app("browser");
			clearChromePermission();
			enterurl();
			validate_Page_IsNotLoaded_InChrome(sa);
		}

		sa.assertAll();
	}

	@Test(priority =20, dataProvider = "XP8_Data_Setting", dataProviderClass = DataProviders.class)
	public void XP8_TC_20_Data_Setting_enable_Data_Receive_Call(Hashtable<String, String> data)
			throws InterruptedException, AWTException, IOException {
		SoftAssert sa = new SoftAssert();
		// disable airplane mode
		APP_LOGS.info("===========XP8_TC_08_Data_Setting============");
		clearRecentApps();
		launch_an_app("settings");
		navigate_To_NetworkandInternet();
		navigate_To_MobileNetwork();

		if (p_b_No.contains("-10.") || p_b_No.contains("-00.")) {
			enable_data1();
			make_Call_from_RefDev();
			customWait(5000);
			receiveCallInpriDevice();
			customWait(5000);
			endCall_RefDevice();
			customWait(5000);
			validate_enablemobile_data_And_Receive_Call(sa);
			// validate_enablemobile_data(sa);
			launch_an_app("browser");
			clearChromePermission();
			enterurl();
			validate_Page_IsLoaded_InChrome(sa);
		} else if (p_b_No.contains("-11.")) {
			enable_data1();
			make_Call_from_RefDev();
			customWait(5000);
			receiveCallInpriDevice();
			customWait(5000);
			endCall_RefDevice();
			customWait(5000);
			validate_enablemobile_data_And_Receive_Call(sa);
			// validate_enablemobile_data(sa);
			launch_an_app("browser");
			clearChromePermission();
			enterurl();
			validate_Page_IsLoaded_InChrome(sa);

		} else if (p_b_No.contains("-15.")) {
			enable_data1();
			make_Call_from_RefDev();
			customWait(5000);
			receiveCallInpriDevice();
			customWait(5000);
			endCall_RefDevice();
			customWait(5000);
			validate_enablemobile_data_And_Receive_Call(sa);
			// validate_enablemobile_data(sa);
			launch_an_app("browser");
			clearChromePermission_vzw();
			enterurl();
			validate_Page_IsLoaded_InChrome(sa);

		} else if (p_b_No.contains("-26.")) {
			enable_data1();
			make_Call_from_RefDev();
			customWait(5000);
			receiveCallInpriDevice();
			customWait(5000);
			endCall_RefDevice();
			customWait(5000);
			validate_enablemobile_data_And_Receive_Call(sa);
			// validate_enablemobile_data(sa);
			launch_an_app("browser");
			clearChromePermission();
			enterurl();
			validate_Page_IsLoaded_InChrome(sa);
		} else if (p_b_No.contains("29")) {
			enable_data1();
			make_Call_from_RefDev();
			customWait(5000);
			receiveCallInpriDevice();
			customWait(5000);
			endCall_RefDevice();
			customWait(5000);
			validate_enablemobile_data_And_Receive_Call(sa);
			// validate_enablemobile_data(sa);
			launch_an_app("browser");
			clearChromePermission();
			enterurl();
			validate_Page_IsLoaded_InChrome(sa);
		} else {
			enable_data1();
			make_Call_from_RefDev();
			customWait(5000);
			receiveCallInpriDevice();
			customWait(5000);
			endCall_RefDevice();
			customWait(5000);
			validate_enablemobile_data_And_Receive_Call(sa);
			// validate_enablemobile_data(sa);
			launch_an_app("browser");
			clearChromePermission_vzw();
			enterurl();
			validate_Page_IsLoaded_InChrome(sa);
		}

		sa.assertAll();
	}

	@Test(priority = 21, dataProvider = "XP8_Data_Setting", dataProviderClass = DataProviders.class)
	public void XP8_TC_21_Data_Setting_validate_LTEservice(Hashtable<String, String> data)
			throws InterruptedException, AWTException, IOException {
		SoftAssert sa = new SoftAssert();
		// navigate and validate the LTEServices opt
		APP_LOGS.info("===========XP8_TC_08_Data_Setting============");
		clearRecentApps();
		launch_an_app("settings");
		navigate_To_NetworkandInternet();
		navigate_To_MobileNetwork();
		// boolean enabled=enable_data();
		
		
		

		if (p_b_No.contains("-10.") || p_b_No.contains("-00.")) {
			network_Settingsopt();
			Validate_LTEservice(sa);
		} else if (p_b_No.contains("-11.")) {
			network_Settingsopt();
			Validate_LTEservice_s(sa);
		} else if (p_b_No.contains("-15.")) {
			network_Settingsopt();
			Validate_LTEservice_s(sa);
		} else if (p_b_No.contains("-26.")) {
			network_Settingsopt();
			Validate_LTEservice_s(sa);
		} else if (p_b_No.contains("29")) {
			network_Settingsopt();
			Validate_LTEservice_s(sa);

		} else {
			network_Settingsopt();
			Validate_LTEservice(sa);
		}

		sa.assertAll();
	}

	@Test(priority = 22, dataProvider = "XP8_Data_Setting", dataProviderClass = DataProviders.class)
	public void XP8_TC_22_Data_Setting_APN_Roaming_Protocol(Hashtable<String, String> data)
			throws InterruptedException, AWTException, IOException {
		SoftAssert sa = new SoftAssert();
		// while adding apn select APN Protocol
		APP_LOGS.info("===========XP8_TC_08_Data_Setting============");
		clearRecentApps();
		launch_an_app("settings");
		navigate_To_NetworkandInternet();
		navigate_To_MobileNetwork();
//		  disable_Airplane_Mode();
//		  boolean enabled=enable_data();
//		
		if (p_b_No.contains("-10.") || p_b_No.contains("-00.")) {
			navigate_to_APN();
			add_APNName(data.get("name1"));
			add_APNNo(data.get("APN1"));
			select_APN_protocol(data.get("ipv4oripv6"), sa);
			customWait(5000);
			APN_Roaming_Protocol(data.get("ipv4oripv6"), sa);
			save_APN();
		} else if (p_b_No.contains("-11.")) {
			navigate_to_APN();
			add_APNName(data.get("name1"));
			add_APNNo(data.get("APN1"));
			select_APN_protocol(data.get("ipv4oripv6"), sa);
			customWait(5000);
			APN_Roaming_Protocol(data.get("ipv4oripv6"), sa);
			save_APN();
		} else if (p_b_No.contains("-15.")) {
			navigate_to_APN();
			add_APNName(data.get("name1"));
			add_APNNo(data.get("APN1"));
			select_APN_protocol(data.get("ipv4oripv6"), sa);
			customWait(5000);
			APN_Roaming_Protocol(data.get("ipv4oripv6"), sa);
			save_APN();
		} else if (p_b_No.contains("-26.")) {
			navigate_to_APN_A();
			// add APN to the APNs Screen
			
			Validate_Add_APNfield_A(sa);


		} else if (p_b_No.contains("29")) {
			navigate_to_APN();
			add_APNName(data.get("name1"));
			add_APNNo(data.get("APN1"));
			select_APN_protocol(data.get("ipv4oripv6"), sa);
			customWait(5000);
			APN_Roaming_Protocol(data.get("ipv4oripv6"), sa);
			save_APN();

		} else {
			navigate_to_APN();
			add_APNName(data.get("name1"));
			add_APNNo(data.get("APN1"));
			select_APN_protocol(data.get("ipv4oripv6"), sa);
			customWait(5000);
			APN_Roaming_Protocol(data.get("ipv4oripv6"), sa);
			save_APN();
		}

		sa.assertAll();
	}

	@Test(priority = 23, dataProvider = "XP8_Data_Setting", dataProviderClass = DataProviders.class)
	public void XP8_TC_23_Data_Setting_enable_disable_LTEservices(Hashtable<String, String> data)
			throws InterruptedException, AWTException, IOException {
		SoftAssert sa = new SoftAssert();
		// discard the apn from the list
		APP_LOGS.info("===========XP8_TC_08_Data_Setting============");
		clearRecentApps();
		launch_an_app("settings");
		navigate_To_NetworkandInternet();
		navigate_To_MobileNetwork();
		if (p_b_No.contains("-10.") || p_b_No.contains("-00.")) {
			Disable_LTEservices();
			enable_Disable_LTEservices();
			validate_Enable_Disable_LTEservices(sa);
		} else if (p_b_No.contains("-11.")) {
			enable_Disable_LTEservices();
			validate_Enable_Disable_LTEservices(sa);
		} else if (p_b_No.contains("-15.")) {
			enable_Disable_LTEservices();
			Validate_LTEservice_s(sa);
		} else if (p_b_No.contains("-26.")) {
			enable_Disable_LTEservices();
			Validate_LTEservice_s(sa);
		} else if (p_b_No.contains("29")) {
			enable_Disable_LTEservices();
			Validate_LTEservice_s(sa);
		} else {
			enable_Disable_LTEservices();
			Validate_LTEservice_s(sa);
		}

		sa.assertAll();
	}

	@Test(priority = 24, dataProvider = "XP8_Data_Setting", dataProviderClass = DataProviders.class)
	public void XP8_TC_24_Data_Setting_Postcondition(Hashtable<String, String> data)
			throws InterruptedException, AWTException, IOException {
		SoftAssert sa = new SoftAssert();
		APP_LOGS.info("===========XP8_TC_01_Data_Setting============");
		clearRecentApps();
		performAction();
		launch_an_app("settings");
		navigate_To_NetworkandInternet();
		navigate_To_MobileNetwork();
		navigate_to_AccessPointName();
		// reset the APN
		reset_To_Default();

		sa.assertAll();

	}

}
