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
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.graphics.gui.JsonFileReaderAndWriter;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTestInterruptedException;
import com.relevantcodes.extentreports.LogStatus;
import com.xp5S.util.BaseUtil;
import com.xp5S.util.GetMethods;
import com.xp5S.util.Locators_ContactTransfer;
import com.xp5S.util.appiumService;
import com.xp8.util.DataProviders;
import com.xp8.util.ExcelConstants;
import com.xp8.util.ExcelReader;
import com.xp8.util.Locators_BaseUtil;
import com.xp8.util.XP8_Locators_ContactTransfer;
import com.xp8.util.XP8_SCOUT_Contact_Transfer_Util;

import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class XP8_SCOUT_Contact_Transfer_Test extends XP8_SCOUT_Contact_Transfer_Util{

	public ExcelReader excel;
	Properties properties;
	public static ExtentTestInterruptedException testexception;
	public int  itr =3;
	boolean value = false;

	@BeforeSuite
	public void beforeSuite() throws FileNotFoundException, InterruptedException, IOException, ParseException, ParseException  
	{
		extent = new ExtentReports("src/test/resources/extentreport/XP8_SCOUT_Contact_Transfer_TestReport.html", true); //Provide Desired Report Directory Location and Name

		//extent.loadConfig(new File(System.getProperty("user.dir")+"//ReportsConfig.xml"));

		extent.loadConfig(new File("src/test/resources/StorageFile/ReportsConfig.xml"));
		extent.addSystemInfo("Build #",JsonFileReaderAndWriter.primaryDevFirmwareReader())
		.addSystemInfo("Product",JsonFileReaderAndWriter.primaryDevModelReader())
		.addSystemInfo("Operator", JsonFileReaderAndWriter.primaryDevOperatorReader());		

		fetch_Devices_Details();	
	}
	
	
	@BeforeSuite
	public void numofTestCases() throws ClassNotFoundException {
		
		appiumService.TOTAL_NUM_OF_TESTCASES=GetMethods.TotalTestcase("XP8_TC", this.getClass());
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
	public void copyFilesToDevice() throws IOException, ParseException {
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
	public void tearDown(ITestResult result,Method method) throws IOException, ParseException, InterruptedException
	{
		if(result.getStatus()==ITestResult.FAILURE)
		{	
		String screenshot_path=captureScreenshot(method.getName());
		String image= test.addScreenCapture(screenshot_path);	
		test.log(LogStatus.FAIL,result.getThrowable());						
		}
		
		extent.endTest(test);
		extent.flush();
	}


	@BeforeTest
	public void setupSys() throws MalformedURLException, InterruptedException, FileNotFoundException, AWTException{
		properties=loadDriverAndProperties();
		XP8_Locators_ContactTransfer loc=new XP8_Locators_ContactTransfer(aDriver);
		Locators_BaseUtil loc1 = new Locators_BaseUtil(aDriver);	
		PageFactory.initElements(new AppiumFieldDecorator(aDriver),loc);
		PageFactory.initElements(new AppiumFieldDecorator(aDriver),loc1);
		excel=new ExcelReader(ExcelConstants.XP5S_XL_PATH);	
		preconditionSetUp();
	}
//============================================Test case=============================================================================
	@Test(priority=1,dataProvider="XP8_ContactTransfer", dataProviderClass=DataProviders.class)
	public void XP8_TC_001_CT_Launch_Contact_Transfer_Application_from_SCOUTAPP(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException, ParseException
	{
		APP_LOGS.info("=========== XP8_TC_001_CT_Launch_Contact_Transfer_Application_from_SCOUTAPP============");
		startAdbLog("XP8_ContactTransfer_001");
		Launch_Contact_Transfer(XP8_Locators_ContactTransfer.sonim_scout_AppLauncher);
		validateLaunchScoutApp(XP8_Locators_ContactTransfer.ContactTransferTitle, "Contact Transfer");
	}

	@Test(priority=2,dataProvider="XP8_ContactTransfer", dataProviderClass=DataProviders.class)
	public void XP8_TC_002_CT_Verify_Pressence_of_all_transfer_modes(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException, ParseException
	{
		APP_LOGS.info("=========== XP8_TC_002_CT_Verify_Pressence_of_all_transfer_modes============");
		startAdbLog("XP8_ContactTransfer_002");
		Launch_Contact_Transfer(XP8_Locators_ContactTransfer.sonim_scout_AppLauncher);
		validateTransferModesPresent();
	}
	
	@Test(priority=3,dataProvider="XP8_ContactTransfer", dataProviderClass=DataProviders.class)
	public void XP8_TC_003_CT_Import_Contacts_Via_MDB(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException, ParseException
	{
		APP_LOGS.info("=========== XP8_TC_003_CT_Import_Contacts_Via_MDB============");
		startAdbLog("XP8_ContactTransfer_003");
		Launch_Contact_Transfer(XP8_Locators_ContactTransfer.sonim_scout_AppLauncher);
		select_Transfer_Mode("mdb");
		selectContactfileToImport();
		validateAllContactImport("XP8_ContactTransfer_003","mdb");
		clickBackButton(4);
	}
	
	@Test(priority=4,dataProvider="XP8_ContactTransfer", dataProviderClass=DataProviders.class)
	public void XP8_TC_004_CT_Import_Contacts_Via_VCF(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException, ParseException
	{
		APP_LOGS.info("=========== XP8_TC_004_CT_Import_Contacts_Via_VCF============");
		startAdbLog("XP8_ContactTransfer_004");
		Launch_Contact_Transfer(XP8_Locators_ContactTransfer.sonim_scout_AppLauncher);
		select_Transfer_Mode("vcf");
		selectContactfileToImport();
		customWait(40000);
		validateAllContactImport("XP8_ContactTransfer_004","vcf");
		clickBackButton(4);
	}
	
	@Test(priority=5,dataProvider="XP8_ContactTransfer", dataProviderClass=DataProviders.class)
	public void XP8_TC_005_CT_Import_Contacts_Via_CSV(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException, ParseException
	{
		APP_LOGS.info("=========== XP8_TC_005_CT_Import_Contacts_Via_CSV============");
		startAdbLog("XP8_ContactTransfer_005");
		Launch_Contact_Transfer(XP8_Locators_ContactTransfer.sonim_scout_AppLauncher);
		select_Transfer_Mode("csv");
		selectContactfileToImport();
		validateAllContactImport("XP8_ContactTransfer_005","csv");
		clickBackButton(4);
	}
	
	@Test(priority=6,dataProvider="XP8_ContactTransfer", dataProviderClass=DataProviders.class)
	public void XP8_TC_006_CT_Import_Contacts_of_UMFI_Format(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException, ParseException
	{
		APP_LOGS.info("=========== XP8_TC_006_CT_Import_Contacts_of_UMFI_Format============");
		startAdbLog("XP8_ContactTransfer_006");
		Launch_Contact_Transfer(XP8_Locators_ContactTransfer.sonim_scout_AppLauncher);
		select_Transfer_Mode("vcf");
		selectUFMIfileToImport();
		customWait(30000);
		validateUFMIContactImport("XP8_ContactTransfer_006","vcf");
		clickBackButton(4);
	}
	
	@Test(priority=7,dataProvider="XP8_ContactTransfer", dataProviderClass=DataProviders.class)
	public void XP8_TC_007_CT_Push_and_Launch_Contact_Transfer_application_from_background(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException, ParseException
	{
		APP_LOGS.info("=========== XP8_TC_007_CT_Push_and_Launch_Contact_Transfer_application_from_background============");
		startAdbLog("XP8_ContactTransfer_007");
		Launch_Contact_Transfer(XP8_Locators_ContactTransfer.sonim_scout_AppLauncher);
		customWait(2000);
		push_to_background_and_verify();
		minWait();
		launch_from_background_and_verify();
		clearRecentApps();
	}
	
	@Test(priority=8,dataProvider="XP8_ContactTransfer", dataProviderClass=DataProviders.class)
	public void XP8_TC_008_CT_Press_any_key_while_contacts_transfer_in_progress_and_verify(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException, ParseException
	{
		APP_LOGS.info("=========== XP8_TC_008_CT_Press_any_key_while_contacts_transfer_in_progress_and_verify============");
		startAdbLog("XP8_ContactTransfer_008");
		Launch_Contact_Transfer(XP8_Locators_ContactTransfer.sonim_scout_AppLauncher);
		customWait(2000);
		select_Transfer_Mode("vcf");
		selectContactfileToImport();
		validate_key_press();
	}
	
	@Test(priority=9,dataProvider="XP8_ContactTransfer", dataProviderClass=DataProviders.class)
	public void XP8_TC_009_CT_Enable_Bluetooth_and_Scan_for_the_devices(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException, ParseException
	{
		APP_LOGS.info("=========== XP8_TC_009_CT_Enable_Bluetooth_and_Scan_for_the_devices============");
		startAdbLog("XP8_ContactTransfer_009");
		launch_an_app("settings");
		disableBluetooth();
		Launch_Contact_Transfer(XP8_Locators_ContactTransfer.sonim_scout_AppLauncher);
		select_Transfer_Mode("Bluetooth");
		enable_BT_and_verify_from_CT();
		customWait(2000);
		scan_for_BT_devices_verify();
	}
	
	@Test(priority=10,dataProvider="XP8_ContactTransfer", dataProviderClass=DataProviders.class)
	public void XP8_TC_010_CT_Validate_Deny_Option_in_Contact_Transfer(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException, ParseException
	{
		APP_LOGS.info("=========== XP8_TC_010_CT_Validate_Deny_Option_in_Contact_Transfer============");
		startAdbLog("XP8_ContactTransfer_010");
		launch_an_app("settings");
		disableBluetooth();
		Launch_Contact_Transfer(XP8_Locators_ContactTransfer.sonim_scout_AppLauncher);
		select_Transfer_Mode("Bluetooth");
		customWait(2000);
		deny_and_verify();
	}
	
	@Test(priority=11,dataProvider="XP8_ContactTransfer", dataProviderClass=DataProviders.class)
	public void XP8_TC_011_CT_Validate_Search_Field_and_selectAll_Option(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException, ParseException
	{
		APP_LOGS.info("=========== XP8_TC_011_CT_Validate_Search_Field_and_selectAll_Option============");
		startAdbLog("XP8_ContactTransfer_011");
		Launch_Contact_Transfer(XP8_Locators_ContactTransfer.sonim_scout_AppLauncher);
		select_Transfer_Mode("mdb");
		validateSearchfield(data.get("contactName"));
		validateSelectAll();
	}
	
	@Test(priority=12,dataProvider="XP8_ContactTransfer", dataProviderClass=DataProviders.class)
	public void XP8_TC_012_CT_Validate_Contact_Transfer_from_notification_bar(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException, ParseException
	{
		APP_LOGS.info("=========== XP8_TC_012_CT_Validate_Contact_Transfer_from_notification_bar============");
		startAdbLog("XP8_ContactTransfer_012");
		Launch_Contact_Transfer(XP8_Locators_ContactTransfer.sonim_scout_AppLauncher);
		select_Transfer_Mode("vcf");
		selectContactfileToImport();
		verify_Notification_bar_when_import_in_progress();
		customWait(3000);
		verify_succes_icon_in_Notification_bar();
		deleteFiles();
	}
	

}
