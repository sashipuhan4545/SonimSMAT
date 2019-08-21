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
import com.xp8.util.Locators_CallSetting;
import com.xp8.util.Locators_Data_Setting;
import com.xp8.util.XP8_Data_Setting_Util;

import io.appium.java_client.android.AndroidKeyCode;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class XP8_Data_Setting_Test extends XP8_Data_Setting_Util {

	public ExcelReader excel;
	Properties properties;
	public static ExtentTestInterruptedException testexception;

	@BeforeSuite
	public void beforeSuite() throws FileNotFoundException, InterruptedException, IOException, ParseException,
			ParseException, ParseException, ParseException, ParseException, ParseException {
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
																													// Case
																													// Start
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
		Locators_Data_Setting loc = new Locators_Data_Setting(aDriver);
		Locators_BaseUtil loc1 = new Locators_BaseUtil(aDriver);
		PageFactory.initElements(new AppiumFieldDecorator(aDriver), loc);
		PageFactory.initElements(new AppiumFieldDecorator(aDriver), loc1);
		excel = new ExcelReader(ExcelConstants.XP5S_XL_PATH);
	}
//----------------------test-----------------------------

	@Test(priority = 1, dataProvider = "XP8_Data_Setting", dataProviderClass = DataProviders.class)
	public void XP8_TC_01_Navigate_APN_Screen(Hashtable<String, String> data)
			throws InterruptedException, AWTException, IOException {
		SoftAssert sa = new SoftAssert();
		APP_LOGS.info("===========XP8_TC_01_Data_Setting============");
		clearRecentApps();
		launch_an_app("settings");
		// navigate to APN screen
		navigate_to_AccessPointName();
		// Validating APN Screen are navigated
		validate_navigatetoAPNscreen(sa);
		VerifyingAlltheFieldinAPNScreen(sa);

		sa.assertAll();
	}

	@Test(priority = 2, dataProvider = "XP8_Data_Setting", dataProviderClass = DataProviders.class)
	public void XP8_TC_02_ADD_Edit_Delete_APN(Hashtable<String, String> data)
			throws InterruptedException, AWTException, IOException {
		SoftAssert sa = new SoftAssert();

		APP_LOGS.info("===========XP8_TC_02_Data_Setting============");
		launch_an_app("settings");
		navigate_to_AccessPointName();
		// add,edit & delete APN
		AddAPNnumber(data.get("name1"), data.get("APN1"));
		// validate added APN
		validate_Added_APN(sa);
		editAPNnumber(data.get("name"), data.get("APN"));
		// validate edited APN
		validate_edited_APN(sa);
		deleteAPNnumber();
		// validate deleted APN
		validate_deleted_APN(sa);
		sa.assertAll();
	}

	@Test(priority = 3, dataProvider = "XP8_Data_Setting", dataProviderClass = DataProviders.class)
	public void XP8_TC_03_Add_Maximum_APNs(Hashtable<String, String> data)
			throws InterruptedException, AWTException, IOException {
		SoftAssert sa = new SoftAssert();
		APP_LOGS.info("===========XP8_TC_03_Data_Setting============");
		clearRecentApps();
		launch_an_app("settings");
		navigate_to_AccessPointName();
		// added maximum apn
		AddAPNnumber(data.get("name1"), data.get("APN1"));
		webwait(Locators_Data_Setting.apnsframe, 30);
		validate_Added_APN(sa);
		AddAPNnumber(data.get("name2"), data.get("APN2"));
		webwait(Locators_Data_Setting.apnsframe, 30);
		validate_Added_APN(sa);
		AddAPNnumber(data.get("name3"), data.get("APN3"));
		webwait(Locators_Data_Setting.apnsframe, 30);
		validate_Added_APN(sa);
		AddAPNnumber(data.get("name4"), data.get("APN4"));
		webwait(Locators_Data_Setting.apnsframe, 30);
		validate_Added_APN(sa);
		sa.assertAll();
	}

	@Test(priority = 4, dataProvider = "XP8_Data_Setting", dataProviderClass = DataProviders.class)
	public void XP8_TC_04_Authentication_Type_APN(Hashtable<String, String> data)
			throws InterruptedException, AWTException, IOException {
		SoftAssert sa = new SoftAssert();
		APP_LOGS.info("===========XP8_TC_04_Data_Setting============");
		clearRecentApps();
		launch_an_app("settings");
		navigate_to_AccessPointName();
		// different APN fields
		differentAPNField(sa, data.get("name"), data.get("APN"), data.get("proxy"), data.get("port"),
				data.get("Username"), data.get("Password"), data.get("Server"), data.get("MMSC"));
		// authentication Type
		Authenticationtype(data.get("pap"));
		maxWait();
		// validate authentication type
		validate_Authenticationtype(sa);
		save_APN();
		sa.assertAll();
	}

	@Test(priority = 5, dataProvider = "XP8_Data_Setting", dataProviderClass = DataProviders.class)
	public void XP8_TC_05_Resettodefault(Hashtable<String, String> data)
			throws InterruptedException, AWTException, IOException {
		SoftAssert sa = new SoftAssert();
		APP_LOGS.info("===========XP8_TC_05_Data_Setting============");
		clearRecentApps();
		launch_an_app("settings");
		navigate_to_AccessPointName();
		// reset the APN
		reset_To_Default();
		webwait(Locators_Data_Setting.apnsframe, 30);
		// Validate the reste APN
		validate_Reset_to_Default(sa);
		sa.assertAll();
	}

	@Test(priority = 6, dataProvider = "XP8_Data_Setting", dataProviderClass = DataProviders.class)
	public void XP8_TC_06_save_Access_Point_APN_without_Entering_APN(Hashtable<String, String> data)
			throws InterruptedException, AWTException, IOException {
		SoftAssert sa = new SoftAssert();
		APP_LOGS.info("===========XP8_TC_06_Data_Setting============");
		clearRecentApps();
		launch_an_app("settings");
		// validate and save the APN without entering apn number
		save_APN_without_Entering_APN(data.get("errormsg"), sa);

		sa.assertAll();

	}

	@Test(priority = 7, dataProvider = "XP8_Data_Setting", dataProviderClass = DataProviders.class)
	public void XP8_TC_07_addapn_without_name_and_apnno(Hashtable<String, String> data)
			throws InterruptedException, AWTException, IOException {
		SoftAssert sa = new SoftAssert();
		APP_LOGS.info("===========XP8_TC_07_Data_Setting============");
		clearRecentApps();
		launch_an_app("settings");
		navigate_to_AccessPointName();
		// save and validate the apn without entering data
		addapn_Without_Entering_Name_and_Apnno();
		validate_error_Msg(data.get("errormsg"), sa);
		sa.assertAll();
	}

	@Test(priority = 8, dataProvider = "XP8_Data_Setting", dataProviderClass = DataProviders.class)
	public void XP8_TC_08_lock_Unlock_Screen(Hashtable<String, String> data)
			throws InterruptedException, AWTException, IOException {
		SoftAssert sa = new SoftAssert();
		APP_LOGS.info("===========XP8_TC_08_Data_Setting============");
		clearRecentApps();
		launch_an_app("settings");

		navigate_to_AccessPointName();
		// lock and unlock the Screen
		lock_Unlock_Screen(data.get("name1"), data.get("APN1"), sa);

		sa.assertAll();
	}

	@Test(priority = 9, dataProvider = "XP8_Data_Setting", dataProviderClass = DataProviders.class)
	public void XP8_TC_09_add_APN_Click_Backbutton(Hashtable<String, String> data)
			throws InterruptedException, AWTException, IOException {
		SoftAssert sa = new SoftAssert();
		APP_LOGS.info("===========XP8_TC_08_Data_Setting============");
		clearRecentApps();
		launch_an_app("settings");
		navigate_to_AccessPointName();
		// while saving APN click back button
		add_APN_Click_Backbutton(sa, data.get("name1"), data.get("APN"), data.get("expectedtext"));
		sa.assertAll();
	}

	@Test(priority = 10, dataProvider = "XP8_Data_Setting", dataProviderClass = DataProviders.class)
	public void XP8_TC_10_enable_Disable_Airplane_Mode(Hashtable<String, String> data)
			throws InterruptedException, AWTException, IOException {
		SoftAssert sa = new SoftAssert();
		// enable and disable the airplane mode
		APP_LOGS.info("===========XP8_TC_08_Data_Setting============");
		clearRecentApps();
		launch_an_app("settings");
		enable_Disable_Airplane_Mode(sa, data.get("networkon"));
		sa.assertAll();
	}

	@Test(priority = 11, dataProvider = "XP8_Data_Setting", dataProviderClass = DataProviders.class)
	public void XP8_TC_11_APN_protocol_And_Bearer(Hashtable<String, String> data)
			throws InterruptedException, AWTException, IOException {
		SoftAssert sa = new SoftAssert();
		// while adding apn select apn protocol type
		APP_LOGS.info("===========XP8_TC_08_Data_Setting============");
		clearRecentApps();
		launch_an_app("settings");
		navigate_to_AccessPointName();
		APN_protocol(data.get("name1"), data.get("APN1"), "IPv6", sa);

		save_APN();
		sa.assertAll();
	}

	@Test(priority = 12, dataProvider = "XP8_Data_Setting", dataProviderClass = DataProviders.class)
	public void XP8_TC_12_APN_protocol_IPv4_Or_IPv6(Hashtable<String, String> data)
			throws InterruptedException, AWTException, IOException {
		SoftAssert sa = new SoftAssert();
		APP_LOGS.info("===========XP8_TC_08_Data_Setting============");
		clearRecentApps();
		launch_an_app("settings");
		navigate_to_AccessPointName();
		APN_protocol(data.get("name1"), data.get("APN1"), data.get("ipv4oripv6"), sa);
		save_APN();
		sa.assertAll();
	}

	@Test(priority = 13, dataProvider = "XP8_Data_Setting", dataProviderClass = DataProviders.class)
	public void XP8_TC_13_Bearer(Hashtable<String, String> data)
			throws InterruptedException, AWTException, IOException {
		SoftAssert sa = new SoftAssert();
		// while adding APN Select bearer
		APP_LOGS.info("===========XP8_TC_08_Data_Setting============");
		clearRecentApps();
		launch_an_app("settings");
		navigate_to_AccessPointName();
		Bearer(data.get("name1"), data.get("APN1"));
		validate_Bearer(sa);
		save_APN();
		sa.assertAll();
	}

	@Test(priority = 14, dataProvider = "XP8_Data_Setting", dataProviderClass = DataProviders.class)
	public void XP8_TC_14_Data_Enabled(Hashtable<String, String> data)
			throws InterruptedException, AWTException, IOException {
		SoftAssert sa = new SoftAssert();
		// enable the data
		APP_LOGS.info("===========XP8_TC_08_Data_Setting============");
		clearRecentApps();
		launch_an_app("settings");
		enabled_Data_Access(sa);
		// launch the browser
		Launch_The_Browser(sa);
		sa.assertAll();
	}

	@Test(priority = 16, dataProvider = "XP8_Data_Setting", dataProviderClass = DataProviders.class)
	public void XP8_TC_16_enable_Data(Hashtable<String, String> data)
			throws InterruptedException, AWTException, IOException {
		SoftAssert sa = new SoftAssert();
		// disable airplane mode
		APP_LOGS.info("===========XP8_TC_08_Data_Setting============");
		clearRecentApps();
		launch_an_app("settings");
		disable_Airplane();
		enable_data(sa);
		verifying_MobileNtwrk(sa);
		Launch_The_Browser(sa);
		launch_an_app("gmail");
		sa.assertAll();
	}

	@Test(priority = 17, dataProvider = "XP8_Data_Setting", dataProviderClass = DataProviders.class)
	public void XP8_TC_17_validate_LTEservice(Hashtable<String, String> data)
			throws InterruptedException, AWTException, IOException {
		SoftAssert sa = new SoftAssert();
		// navigate and validate the LTEServices opt
		APP_LOGS.info("===========XP8_TC_08_Data_Setting============");
		clearRecentApps();
		launch_an_app("settings");
		network_Settingsopt();
		Validate_LTEservice(sa);
		sa.assertAll();
	}

	@Test(priority = 18, dataProvider = "XP8_Data_Setting", dataProviderClass = DataProviders.class)
	public void XP8_TC_18_Edit_SaveorCancel_APN(Hashtable<String, String> data)
			throws InterruptedException, AWTException, IOException {
		SoftAssert sa = new SoftAssert();
		// while editing apn click on cancel button
		APP_LOGS.info("===========XP8_TC_08_Data_Setting============");
		clearRecentApps();
		launch_an_app("settings");
		navigate_to_AccessPointName();
		AddAPNnumber(data.get("name1"), data.get("APN"));
		validate_Added_APN(sa);
		editAPNnumber(data.get("name"), data.get("APN"));
		validate_edited_APN(sa);
		CancelAPN(data.get("name"), data.get("APN"));
		validate_CancelAPN(sa);
		sa.assertAll();
	}

	@Test(priority = 19, dataProvider = "XP8_Data_Setting", dataProviderClass = DataProviders.class)
	public void XP8_TC_19_APN_Roaming_Protocol(Hashtable<String, String> data)
			throws InterruptedException, AWTException, IOException {
		SoftAssert sa = new SoftAssert();
		// while adding apn select APN Protocol
		APP_LOGS.info("===========XP8_TC_08_Data_Setting============");
		clearRecentApps();
		launch_an_app("settings");
		navigate_to_AccessPointName();
		APN_protocol(data.get("name1"), data.get("APN1"), data.get("ipv4oripv6"), sa);
		APN_Roaming_Protocol(data.get("ipv4oripv6"), sa);
		save_APN();
		sa.assertAll();
	}

	@Test(priority = 20, dataProvider = "XP8_Data_Setting", dataProviderClass = DataProviders.class)
	public void XP8_TC_20_view_And_Select_APNs(Hashtable<String, String> data)
			throws InterruptedException, AWTException, IOException {
		SoftAssert sa = new SoftAssert();
		// view and select the apn in APNs Screen
		APP_LOGS.info("===========XP8_TC_08_Data_Setting============");
		clearRecentApps();
		launch_an_app("settings");
		navigate_to_AccessPointName();
		AddAPNnumber(data.get("name1"), data.get("APN1"));
		view_And_Select_APNs(data.get("name1"));
		validate_View_And_Select_APNs(sa);

		sa.assertAll();
	}

	@Test(priority = 21, dataProvider = "XP8_Data_Setting", dataProviderClass = DataProviders.class)
	public void XP8_TC_21_Added_APN_To_The_List(Hashtable<String, String> data)
			throws InterruptedException, AWTException, IOException {
		SoftAssert sa = new SoftAssert();
		// add apn to the APNs List
		APP_LOGS.info("===========XP8_TC_08_Data_Setting============");
		clearRecentApps();
		launch_an_app("settings");
		navigate_to_AccessPointName();
		AddAPNnumber(data.get("name1"), data.get("APN1"));
		validate_Added_APN_To_The_List(sa);

		sa.assertAll();
	}

	@Test(priority = 22, dataProvider = "XP8_Data_Setting", dataProviderClass = DataProviders.class)
	public void XP8_TC_22_discard_APNs(Hashtable<String, String> data)
			throws InterruptedException, AWTException, IOException {
		SoftAssert sa = new SoftAssert();
		// discard the apn from the list
		APP_LOGS.info("===========XP8_TC_08_Data_Setting============");
		clearRecentApps();
		launch_an_app("settings");
		navigate_to_AccessPointName();
		reset_To_Default();
		webwait(Locators_Data_Setting.apnsframe, 30);
		discard_APNs(data.get("name1"));
		Validate_Discard_APNs(sa);
		sa.assertAll();
	}
}
