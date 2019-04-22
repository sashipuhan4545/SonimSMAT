package com.xp3.Test;

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
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTestInterruptedException;
import com.relevantcodes.extentreports.LogStatus;
import com.xp3.Utils.Locators_BaseUtil;
import com.xp3.Utils.Locators_Contact_Transfer;
import com.xp3.Utils.XP3_SCOUT_Contact_Transfer_Util;
import com.xp5S.util.DataProviders;
import com.xp5S.util.ExcelConstants;
import com.xp5S.util.ExcelReader;
import com.xp5S.util.GetMethods;
import com.xp5S.util.appiumService;

import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class XP3_SCOUT_Contact_Transfer_Test extends XP3_SCOUT_Contact_Transfer_Util{

	public ExcelReader excel;
	Properties properties;
	public static ExtentTestInterruptedException testexception;


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

	@BeforeSuite
	public void beforeSuite() throws IOException, InterruptedException, ParseException 
	{
		extent = new ExtentReports("src/test/resources/extentreport/XP3_SCOUT_Contact_Transfer_TestReport.html", true); 
		fetch_Devices_Details();
		try {
			appiumService.TOTAL_NUM_OF_TESTCASES=GetMethods.TotalTestcase("XP3_TC_", this.getClass());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			//Added comment to test
		}


	}

	@BeforeMethod()
	public  void beforeMethod(Method method) throws InterruptedException 
	{

		test = extent.startTest( (this.getClass().getSimpleName() +" :: "+  method.getName()),method.getName()); //Test Case Start Here
		test.assignAuthor(""); //Test Script Author Name
		extent.loadConfig(new File("src/test/resources/StorageFile/ReportsConfig.xml"));
		extent.addSystemInfo("Appium Version", "1.2.7").addSystemInfo("Environment", "TEST");


	}


	@AfterMethod()
	public void tearDown(ITestResult result,Method method) throws IOException, InterruptedException
	{
		if(result.getStatus()==ITestResult.FAILURE)
		{			

			String screenshot_path=captureScreenshot(method.getName());
			String image= test.addScreenCapture(screenshot_path);
			test.log(LogStatus.FAIL,result.getThrowable());
			test.log(LogStatus.FAIL, "screenshot for failure:" +test.addScreenCapture(image));
			clearRecentApps();

		}
		extent.endTest(test);
		extent.flush();
	}


	@BeforeTest
	public void setupSys() throws MalformedURLException, InterruptedException, FileNotFoundException, AWTException{
		properties=loadDriverAndProperties();
		Locators_Contact_Transfer loc=new Locators_Contact_Transfer(aDriver);
		Locators_BaseUtil loc1 =new Locators_BaseUtil(aDriver);
		PageFactory.initElements(new AppiumFieldDecorator(getDriver()),loc1);
		PageFactory.initElements(new AppiumFieldDecorator(getDriver()),loc);
		excel=new ExcelReader(ExcelConstants.XP5S_XL_PATH);
		preconditionSetUp();
	}

	//================================================Test cases==============================================
	@Test(priority=1,dataProvider="XP3_ContactTransfer", dataProviderClass=DataProviders.class)
	public void XP3_TC_001_CT_Launch_Contact_Transfer_Application_from_SCOUTAPP(Hashtable<String, String> data) throws AWTException, InterruptedException, IOException, ParseException {

		APP_LOGS.info("=========== XP3_TC_001_CT_Launch_Contact_Transfer_Application_from_SCOUTAPP============");
		startAdbLog("XP3_ContactTransfer_001");
		Launch_Contact_Transfer();
		validateLaunchScoutApp(Locators_Contact_Transfer.ContactTransferTitle, "Contact Transfer");
	}

	@Test(priority=2,dataProvider="XP3_ContactTransfer", dataProviderClass=DataProviders.class)
	public void XP3_TC_002_CT_Verify_Pressence_of_all_transfer_modes(Hashtable<String, String> data) throws AWTException, InterruptedException, IOException, ParseException {

		APP_LOGS.info("=========== XP3_TC_002_CT_Verify_Pressence_of_all_transfer_modes============");
		startAdbLog("XP3_ContactTransfer_002");
		Launch_Contact_Transfer();
		validateTransferModesPresent();
	}

	@Test(priority=3,dataProvider="XP3_ContactTransfer", dataProviderClass=DataProviders.class)
	public void XP3_TC_003_CT_Import_Contacts_Via_MDB(Hashtable<String, String> data) throws AWTException, InterruptedException, IOException, ParseException {

		APP_LOGS.info("=========== XP3_TC_003_CT_Import_Contacts_Via_MDB============");
		startAdbLog("XP3_ContactTransfer_003");
		Launch_Contact_Transfer();
		select_Transfer_Mode("mdb");
		selectContactfileToImport();
		validateAllContactImport("XP3_ContactTransfer_003","mdb");
		clickBackButton(4);
	}

	@Test(priority=4,dataProvider="XP3_ContactTransfer", dataProviderClass=DataProviders.class)
	public void XP3_TC_004_CT_Import_Contacts_Via_VCF(Hashtable<String, String> data) throws AWTException, InterruptedException, IOException, ParseException {

		APP_LOGS.info("=========== XP3_TC_004_CT_Import_Contacts_Via_VCF============");
		startAdbLog("XP3_ContactTransfer_004");
		Launch_Contact_Transfer();
		select_Transfer_Mode("vcf");
		selectContactfileToImport();
		customWait(40000);
		validateAllContactImport("XP3_ContactTransfer_004","vcf");
		clickBackButton(4);
	}

	@Test(priority=5,dataProvider="XP3_ContactTransfer", dataProviderClass=DataProviders.class)
	public void XP3_TC_005_CT_Import_Contacts_Via_CSV(Hashtable<String, String> data) throws AWTException, InterruptedException, IOException, ParseException {

		APP_LOGS.info("=========== XP3_TC_005_CT_Import_Contacts_Via_CSV============");
		startAdbLog("XP3_ContactTransfer_005");
		Launch_Contact_Transfer();
		select_Transfer_Mode("csv");
		selectContactfileToImport();
		validateAllContactImport("XP3_ContactTransfer_005","csv");
		clickBackButton(4);
	}

	@Test(priority=6,dataProvider="XP3_ContactTransfer", dataProviderClass=DataProviders.class)
	public void XP3_TC_006_CT_Import_Contacts_of_UMFI_Format(Hashtable<String, String> data) throws AWTException, InterruptedException, IOException, ParseException {

		APP_LOGS.info("=========== XP3_TC_006_CT_Import_Contacts_of_UMFI_Format============");
		startAdbLog("XP3_ContactTransfer_006");
		Launch_Contact_Transfer();
		select_Transfer_Mode("vcf");
		selectUFMIfileToImport();
		validateUFMIContactImport("XP3_ContactTransfer_006","vcf");
		clickBackButton(4);
	}

	@Test(priority=7,dataProvider="XP3_ContactTransfer", dataProviderClass=DataProviders.class)
	public void XP3_TC_007_CT_Push_and_Launch_Contact_Transfer_application_from_background(Hashtable<String, String> data) throws AWTException, InterruptedException, IOException, ParseException {

		APP_LOGS.info("=========== XP3_TC_007_CT_Push_and_Launch_Contact_Transfer_application_from_background============");
		startAdbLog("XP3_ContactTransfer_007");
		Launch_Contact_Transfer();
		customWait(2000);
		push_to_background_and_verify();
		minWait();
		launch_from_background_and_verify();
	}

	@Test(priority=8,dataProvider="XP3_ContactTransfer", dataProviderClass=DataProviders.class)
	public void XP3_TC_008_CT_Press_any_key_while_contacts_transfer_in_progress_and_verify(Hashtable<String, String> data) throws AWTException, InterruptedException, IOException, ParseException {

		APP_LOGS.info("=========== XP3_TC_008_Press_any_key_while_contacts_transfer_in_progress_and_verify============");
		startAdbLog("XP3_ContactTransfer_008");
		Launch_Contact_Transfer();
		select_Transfer_Mode("vcf");
		selectContactfileToImport();
		customWait(2000);
		validate_key_press();
	}

	@Test(priority=9,dataProvider="XP3_ContactTransfer", dataProviderClass=DataProviders.class)
	public void XP3_TC_009_CT_Enable_Bluetooth_and_Scan_for_the_devices(Hashtable<String, String> data) throws AWTException, InterruptedException, IOException, ParseException {

		APP_LOGS.info("=========== XP3_TC_009_Enable_Bluetooth_and_Scan_for_the_devices============");
		startAdbLog("XP3_ContactTransfer_009");
		launch_an_app("settings");
		disableBluetooth();
		Launch_Contact_Transfer();
		select_Transfer_Mode("Bluetooth");
		enable_BT_and_verify_from_CT();
		customWait(2000);
		scan_for_BT_devices_verify();
	}

	@Test(priority=10,dataProvider="XP3_ContactTransfer", dataProviderClass=DataProviders.class)
	public void XP3_TC_010_CT_Validate_Deny_Option_in_Contact_Transfer(Hashtable<String, String> data) throws AWTException, InterruptedException, IOException, ParseException {

		APP_LOGS.info("=========== XP3_TC_010_Validate_Deny_Option_in_Contact_Transfer============");
		startAdbLog("XP3_ContactTransfer_010");
		launch_an_app("settings");
		disableBluetooth();
		Launch_Contact_Transfer();
		select_Transfer_Mode("Bluetooth");
		customWait(2000);
		deny_and_verify();
	}

	@Test(priority=11,dataProvider="XP3_ContactTransfer", dataProviderClass=DataProviders.class)
	public void XP3_TC_011_CT_Validate_Search_Field_and_selectAll_Option(Hashtable<String, String> data) throws AWTException, InterruptedException, IOException, ParseException {

		APP_LOGS.info("=========== XP3_TC_011_Validate_Search_Field_and_selectAll_Option============");
		startAdbLog("XP3_ContactTransfer_011");
		Launch_Contact_Transfer();
		select_Transfer_Mode("mdb");
		validateSearchfield(data.get("contactName"));
		validateSelectAll();
	}

	@Test(priority=12,dataProvider="XP3_ContactTransfer", dataProviderClass=DataProviders.class)
	public void XP3_TC_012_CT_Validate_Contact_Transfer_from_notification_bar(Hashtable<String, String> data) throws AWTException, InterruptedException, IOException, ParseException {

		APP_LOGS.info("=========== XP3_TC_012_Validate_Contact_Transfer_from_notification_bar============");
		startAdbLog("XP3_ContactTransfer_012");
		Launch_Contact_Transfer();
		select_Transfer_Mode("vcf");
		selectUFMIfileToImport();
		verify_Notification_bar_when_import_in_progress();
		customWait(3000);
		verify_succes_icon_in_Notification_bar();
		deleteFiles();
	}

}
