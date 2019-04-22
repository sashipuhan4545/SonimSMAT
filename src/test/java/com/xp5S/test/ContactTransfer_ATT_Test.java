package com.xp5S.test;

import java.awt.AWTException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException ;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.util.Hashtable;
import java.util.Properties;

import javax.swing.JOptionPane;

import org.json.simple.parser.ParseException;
import org.openqa.selenium.support.PageFactory;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.graphics.gui.CountIncrementFile;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.ExtentTestInterruptedException;
import com.relevantcodes.extentreports.LogStatus;
import com.xp5S.util.BaseUtil;
import com.xp5S.util.ContactTransfer_Util;
import com.xp5S.util.DataProviders;
import com.xp5S.util.ExcelConstants;
import com.xp5S.util.ExcelReader;
import com.xp5S.util.Locators_BaseUtil;
import com.xp5S.util.Locators_ContactTransfer;
import com.xp5S.util.Locators_SonimCare;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import junit.framework.Assert;

public class ContactTransfer_ATT_Test extends ContactTransfer_Util {

	Locators_SonimCare locators;
	public ExcelReader excel;
	Properties properties;
	public static ExtentReports extent;
	public static ExtentTest test;
	public static ExtentTestInterruptedException testexception;
	//public AndroidDriver<AndroidElement> aDriver;

	@BeforeSuite
	public void beforeSuite() throws IOException, ParseException  
	{
		extent = new ExtentReports("src/test/resources/extentreport/XP5S_ScoutApps_ContactTransfer_TestReport.html", true); 


	}

	@BeforeMethod()
	public  void beforeMethod(Method method) throws InterruptedException 
	{

		test = extent.startTest( (this.getClass().getSimpleName() +" :: "+  method.getName()),method.getName()); //Test Case Start Here
		test.assignAuthor(""); //Test Script Author Name
		extent.loadConfig(new File(System.getProperty("user.dir")+"//ReportsConfig.xml"));
		extent.addSystemInfo("Appium Version", "1.2.7").addSystemInfo("Environment", "TEST");
		String BuildNumber=BaseUtil.fetchDeviceProperty("adb shell getprop ro.build.id");
		test.assignCategory("Build #:"+BuildNumber);

	}

	@BeforeClass
	public void copyFilesToDevice() throws IOException, ParseException  {

		Runtime.getRuntime().exec("adb push src/test/resources/contactTransferFile/i475.csv /storage/emulated/0/Alarms");
		Runtime.getRuntime().exec("adb push src/test/resources/contactTransferFile/i475.mdb /storage/emulated/0/Alarms");
		Runtime.getRuntime().exec("adb push src/test/resources/contactTransferFile/i475.vcf /storage/emulated/0/Alarms");

	}

	@AfterMethod()
	public void tearDown(ITestResult result,Method method) throws IOException, ParseException , InterruptedException
	{
		if(result.getStatus()==ITestResult.FAILURE)
		{			

			String screenshot_path=captureScreenshot(method.getName());
			String image= test.addScreenCapture(screenshot_path);
			test.log(LogStatus.FAIL,result.getThrowable());
			test.log(LogStatus.FAIL, "screenshot for failure:" +test.addScreenCapture(image));
			clearRecentApps();
			launch_application(Locators_ContactTransfer.sonim_scout_AppLauncher);
		}
		extent.endTest(test);
		extent.flush();
	}

	/*@AfterMethod
	public void setProgressBar_TestResult()  {


		int count =CountIncrementFile.getCount(4);  
		if(count==80) {
			count=count+20;
			CountIncrementFile.putCount(count);

		}
		else {

			ToolFrame.progressBar.setValue(count);
			count=count+4;
			CountIncrementFile.putCount(count);
		}	
	}*/


	@BeforeTest
	public void setupSys() throws MalformedURLException, InterruptedException, FileNotFoundException, AWTException{
		properties=loadDriverAndProperties();
		Locators_ContactTransfer loc=new Locators_ContactTransfer(aDriver);
		Locators_BaseUtil loc1 =new Locators_BaseUtil(aDriver);
		PageFactory.initElements(new AppiumFieldDecorator(getDriver()),loc1);
		PageFactory.initElements(new AppiumFieldDecorator(getDriver()),loc);
		excel=new ExcelReader(ExcelConstants.XP5S_XL_PATH);		
	}

	@Test(priority=1,dataProvider="ContactTransfer", dataProviderClass=DataProviders.class)
	public void XP5s_CT_01_Validate_ContactTransfer_HomeScreen(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException  {

		APP_LOGS.info("=========== ContactTransfer_01============");
		startAdbLog("ContactTransfer_01");
		recordVideo("ContactTransfer_01");
		clearRecentApps();
		launch_application(Locators_ContactTransfer.sonim_scout_AppLauncher);
		validate_Current_Screen(Locators_ContactTransfer.ContactTransferTitle,data.get("screenTitle"));
		test.log(LogStatus.PASS, "Test case status is Passed");
	}
	
	@Test(priority=2,dataProvider="ContactTransfer", dataProviderClass=DataProviders.class)
	public void XP5s_CT_02_Validate_Default_TransferMode(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException  {

		APP_LOGS.info("=========== ContactTransfer_02============");
		startAdbLog(" ContactTransfer_02");
		recordVideo("ContactTransfer_02");
//		clearRecentApps();
		launch_application(Locators_ContactTransfer.sonim_scout_AppLauncher);
		validate_Default_TransferMode();
		pressBackBtn();	
		test.log(LogStatus.PASS, "Test case status is Passed");
	}
	
	@Test(priority=3,dataProvider="ContactTransfer", dataProviderClass=DataProviders.class)
	public void XP5s_CT_03_Validate_About_ContactTransfer(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException  {

		APP_LOGS.info("=========== ContactTransfer_03============");
		startAdbLog("ContactTransfer_03");
		recordVideo("ContactTransfer_03");
//		clearRecentApps();
		launch_application(Locators_ContactTransfer.sonim_scout_AppLauncher);
		selectMoreOptions();
		validateAboutText();
		pressBackBtn();
		test.log(LogStatus.PASS, "Test case status is Passed");
	}
	
	@Test(priority=4,dataProvider="ContactTransfer", dataProviderClass=DataProviders.class)
	public void XP5s_CT_04_Validate_All_TransferMode_Present(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException  {

		APP_LOGS.info("=========== ContactTransfer_03 ============");
		startAdbLog("ContactTransfer_03");
		recordVideo("ContactTransfer_03");
//		clearRecentApps();
		launch_application(Locators_ContactTransfer.sonim_scout_AppLauncher);
		validateTransferModesPresent();
		pressBackBtn();
		test.log(LogStatus.PASS, "Test case status is Passed");
	}
	
	
	@Test(priority=5,dataProvider="ContactTransfer", dataProviderClass=DataProviders.class)
	public void XP5s_CT_05_ImportContact_Via_MDB(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException  
	{
		APP_LOGS.info("=========== ContactTransfer_05============");
		startAdbLog("ContactTransfer_AdbLog");
		recordVideo("ContactTransfer_05");
//		clearRecentApps();
		launch_application(Locators_ContactTransfer.sonim_scout_AppLauncher);
		appPermissions();	
		clearRecentApps();
		launch_application(Locators_ContactTransfer.sonim_scout_AppLauncher);
		select_Transfer_Mode("mdb");
		selectContactfileToImport(7);
		validateAllContactImport("ContactTransfer_AdbLog","mdb");
		pressBackBtn();
		test.log(LogStatus.PASS, "Test case status is Passed");
	}
	
	@Test(priority=6,dataProvider="ContactTransfer", dataProviderClass=DataProviders.class)
	public void XP5s_CT_06_ImportContact_Via_VCF(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException  
	{
		APP_LOGS.info("=========== ContactTransfer_06============");
		startAdbLog("ContactTransfer_AdbLog");
		recordVideo("ContactTransfer_06");
//		clearRecentApps();
		launch_application(Locators_ContactTransfer.sonim_scout_AppLauncher);  
		select_Transfer_Mode("vcf");
		selectContactfileToImport(7);
		validateAllContactImport("ContactTransfer_AdbLog","vcf");
		pressBackBtn();
		test.log(LogStatus.PASS, "Test case status is Passed");
	}
	
	@Test(priority=7,dataProvider="ContactTransfer", dataProviderClass=DataProviders.class)
	public void XP5s_CT_07_Validate__ImportContact_Via_CSV(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException 
	{
		APP_LOGS.info("=========== ContactTransfer_07============");
		startAdbLog("ContactTransfer_AdbLog");
		recordVideo("ContactTransfer_07");
//		clearRecentApps();
		launch_application(Locators_ContactTransfer.sonim_scout_AppLauncher);		
		select_Transfer_Mode("csv");
		selectContactfileToImport(7);
		validateAllContactImport("ContactTransfer_AdbLog","csv");
		pressBackBtn();
		test.log(LogStatus.PASS, "Test case status is Passed");
	}
	
	@Test(priority=8,dataProvider="ContactTransfer", dataProviderClass=DataProviders.class)
	public void XP5s_CT_08_Validate_ImportContact_Notification(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException 
	{
		APP_LOGS.info("=========== ContactTransfer_08============");
		startAdbLog("ContactTransfer_08");
		recordVideo("ContactTransfer_08");
//		clearRecentApps();
		launch_application(Locators_ContactTransfer.sonim_scout_AppLauncher);
		String XP5deviceModelNumber=BaseUtil.fetchDeviceProperty("adb shell getprop gsm.version.baseband");			 
		select_Transfer_Mode("csv");
		selectContactfileToImport(7);
		validateNotificationBar();
		pressBackBtn();
		test.log(LogStatus.PASS, "Test case status is Passed");
	}	
	
	@Test(priority=9,dataProvider="ContactTransfer", dataProviderClass=DataProviders.class)
	public void XP5s_CT_09_Validate_ImportContact_Cancel(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException  
	{
		APP_LOGS.info("=========== ContactTransfer_09============");
		startAdbLog("ContactTransfer_AdbLog");
		recordVideo("ContactTransfer_09");
//		clearRecentApps();
		launch_application(Locators_ContactTransfer.sonim_scout_AppLauncher);
		pressBackBtn();
		launch_application(Locators_ContactTransfer.sonim_scout_AppLauncher);
		select_Transfer_Mode("csv");
		selectContactfileToImport(7);
		validateCancelImport("ContactTransfer_AdbLog");
		pressBackBtn();
		test.log(LogStatus.PASS, "Test case status is Passed");
	}
	
	@Test(priority=10,dataProvider="ContactTransfer", dataProviderClass=DataProviders.class)
	public void XP5s_CT_10_Disable_MBD_While_Impoting_MDBfile(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException  
	{
		APP_LOGS.info("=========== ContactTransfer_10============");
		startAdbLog("ContactTransfer_10");
		recordVideo("ContactTransfer_10");
		clearRecentApps();
		launch_application(Locators_ContactTransfer.sonim_scout_AppLauncher);
		DisableFile("mdb", Locators_ContactTransfer.ImportMBDOptn,1);
		pressBackBtn();
		test.log(LogStatus.PASS, "Test case status is Passed");
	}	
	
	@Test(priority=11,dataProvider="ContactTransfer", dataProviderClass=DataProviders.class)
	public void XP5s_CT_11_Disable_VCF_While_Impoting_VCFfile(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException  
	{
		APP_LOGS.info("=========== ContactTransfer_11============");
		startAdbLog("ContactTransfer_11");
		recordVideo("ContactTransfer_11");
		clearRecentApps();
		launch_application(Locators_ContactTransfer.sonim_scout_AppLauncher);
		DisableFile("vcf", Locators_ContactTransfer.ImportVCFOptn,2);
		pressBackBtn();
		test.log(LogStatus.PASS, "Test case status is Passed");
	}	
	
	@Test(priority=12,dataProvider="ContactTransfer", dataProviderClass=DataProviders.class)
	public void XP5s_CT_12_Disable_CSV_While_Impoting_CSVfile(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException  
	{
		APP_LOGS.info("=========== ContactTransfer_12============");
		startAdbLog("ContactTransfer_12");
		recordVideo("ContactTransfer_12");
		clearRecentApps();
		launch_application(Locators_ContactTransfer.sonim_scout_AppLauncher);
		DisableFile("csv", Locators_ContactTransfer.ImportCSVOptn,3);
		pressBackBtn();
		test.log(LogStatus.PASS, "Test case status is Passed");
	}	 
	 
	@Test(priority=13,dataProvider="ContactTransfer", dataProviderClass=DataProviders.class)
	public void XP5s_CT_13_Disable_BT_While_Impoting_BTfile(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException  
	{
		APP_LOGS.info("=========== ContactTransfer_13============");
		startAdbLog("ContactTransfer_13");
		recordVideo("ContactTransfer_13");
		clearRecentApps();
		launch_application(Locators_ContactTransfer.sonim_scout_AppLauncher);
		DisableFile("Bluetooth", Locators_ContactTransfer.ImportBTOptn,0);
		pressBackBtn();
		test.log(LogStatus.PASS, "Test case status is Passed");
	}	

	@Test(priority=14,dataProvider="ContactTransfer", dataProviderClass=DataProviders.class)
	public void Xp5s_CT_14_Import_Few_Number_After_ImportingContact(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException  
	{
		APP_LOGS.info("=========== ContactTransfer_14============");
		startAdbLog("ContactTransfer_AdbLog");
		recordVideo("ContactTransfer_14");
//		clearRecentApps();
		launch_application(Locators_ContactTransfer.sonim_scout_AppLauncher);
		select_Transfer_Mode("vcf");
	    validateFewImportContact();
		pressBackBtn();
		test.log(LogStatus.PASS, "Test case status is Passed");
	}	
	
	@Test(priority=15,dataProvider="ContactTransfer", dataProviderClass=DataProviders.class)
	public void Xp5s_CT_15_AfterImport_Search_Import_UFMI_Format(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException  
	{
		APP_LOGS.info("=========== ContactTransfer_15============");
		startAdbLog("ContactTransfer_15");
		recordVideo("ContactTransfer_15");
//		clearRecentApps();
		launch_application(Locators_ContactTransfer.sonim_scout_AppLauncher);
		select_Transfer_Mode("csv");
		validateUFMIFormat();
		pressBackBtn();
		test.log(LogStatus.PASS, "Test case status is Passed");
	}	
	
	@Test(priority=16,dataProvider="ContactTransfer", dataProviderClass=DataProviders.class)
	public void XP5s_CT_16_Validate_Title_After_Selecting_ViaBluetooth(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException  
	{
		APP_LOGS.info("=========== ContactTransfer_16============");
		startAdbLog("ContactTransfer_16");
		recordVideo("ContactTransfer_16");
//		clearRecentApps();
		launch_application(Locators_ContactTransfer.sonim_scout_AppLauncher);
		select_Transfer_Mode("Bluetooth");
		validateBrowse_Title(Locators_ContactTransfer.Title_BTOptn,data.get("BrowseTitle"),"Bluetooth");
		pressBackBtn();
		test.log(LogStatus.PASS, "Test case status is Passed");
	}
	
	
	@Test(priority=17,dataProvider="ContactTransfer", dataProviderClass=DataProviders.class)
	public void XP5s_CT_17_Validate_Title_After_Selecting_ViaMDB(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException  
	{
		APP_LOGS.info("=========== ContactTransfer_17============");
		startAdbLog("ContactTransfer_17");
		recordVideo("ContactTransfer_17");
//		clearRecentApps();
		launch_application(Locators_ContactTransfer.sonim_scout_AppLauncher);
		select_Transfer_Mode("mdb");
		validateBrowse_Title(Locators_ContactTransfer.Title_MBDOptn,data.get("BrowseTitle"),"mdb");
		pressBackBtn();
		test.log(LogStatus.PASS, "Test case status is Passed");
	}

	@Test(priority=18,dataProvider="ContactTransfer", dataProviderClass=DataProviders.class)
	public void XP5s_CT_18_Validate_Title_After_Selecting_ViaVCF(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException  
	{
		APP_LOGS.info("=========== ContactTransfer_18============");
		startAdbLog("ContactTransfer_18");
		recordVideo("ContactTransfer_18");
//		clearRecentApps();
		launch_application(Locators_ContactTransfer.sonim_scout_AppLauncher);
		select_Transfer_Mode("vcf");
		validateBrowse_Title(Locators_ContactTransfer.Title_VCFOptn,data.get("BrowseTitle"),"vcf");
		pressBackBtn();
		test.log(LogStatus.PASS, "Test case status is Passed");
	}		
	
	@Test(priority=19,dataProvider="ContactTransfer", dataProviderClass=DataProviders.class)
	public void XP5s_CT_19_Validate_Title_After_Selecting_ViaCSV(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException  
	{
		APP_LOGS.info("=========== ContactTransfer_19============");
		startAdbLog("ContactTransfer_19");
		recordVideo("ContactTransfer_19");
//		clearRecentApps();
		launch_application(Locators_ContactTransfer.sonim_scout_AppLauncher);
		select_Transfer_Mode("csv");
		validateBrowse_Title(Locators_ContactTransfer.Title_CSVOptn, data.get("BrowseTitle"),"csv ");
		pressBackBtn();
		test.log(LogStatus.PASS, "Test case status is Passed");
	}
	
	@Test(priority=20,dataProvider="ContactTransfer", dataProviderClass=DataProviders.class)
	public void XP5s_CT_20_Validate_ReOpen_ContactTransferApp_NotificationBar(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException  
	{
		APP_LOGS.info("=========== ContactTransfer_20============");
		startAdbLog("ContactTransfer_20");
		recordVideo("ContactTransfer_20");
//		clearRecentApps();
		launch_application(Locators_ContactTransfer.sonim_scout_AppLauncher);  
		select_Transfer_Mode("csv");
		selectContactfileToImport(7);
		validateImportSummaryPageAfterReopen();
		pressBackBtn();
		test.log(LogStatus.PASS, "Test case status is Passed");
	}	
	
	@Test(priority=21,dataProvider="ContactTransfer", dataProviderClass=DataProviders.class)
	public void XP5s_CT_21_Validate_Default_Mode_MoreOptions(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException  
	{
		APP_LOGS.info("=========== ContactTransfer_21============");
		startAdbLog("ContactTransfer_21");
		recordVideo("ContactTransfer_21");
//		clearRecentApps();
		launch_application(Locators_ContactTransfer.sonim_scout_AppLauncher);
		validateDeafaultModeMoreOptions();
		pressBackBtn();
		test.log(LogStatus.PASS, "Test case status is Passed");
	}

}
