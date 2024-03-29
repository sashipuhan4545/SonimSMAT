package com.xp3.Test;

import java.awt.AWTException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException ;
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
import org.testng.asserts.SoftAssert;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.ExtentTestInterruptedException;
import com.relevantcodes.extentreports.LogStatus;
import com.xp3.Utils.Locators_BaseUtil;
import com.xp3.Utils.Locators_DeviceStability;
import com.xp3.Utils.XP3_Stability_Util;
import com.xp5S.util.DataProviders;
import com.xp5S.util.ExcelConstants;
import com.xp5S.util.ExcelReader;
import com.xp5S.util.GetMethods;
import com.xp5S.util.appiumService;

import application.AllQA;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class XP3_Device_QA_Stability_Test extends XP3_Stability_Util{


	public ExcelReader excel;
	Properties properties;
	public static ExtentTestInterruptedException testexception;
	public AndroidDriver<AndroidElement> aDriver;


	public int itr =AllQA.NUMOFCALLS;
	boolean value = false;

	@BeforeSuite
	public void beforeSuite() throws FileNotFoundException, InterruptedException, IOException, ParseException , ParseException {

		extent = new ExtentReports("src/test/resources/extentreport/XP3_Device_Stability_TestReport.html", true); //Provide Desired Report Directory Location and Name
		fetch_Devices_Details();
	}

	
	@BeforeMethod()
	public  void beforeMethod(Method method) throws InterruptedException {

		test = extent.startTest( (this.getClass().getSimpleName() +" :: "+  method.getName()),method.getName()); //Test Case Start Here
		test.assignAuthor(""); //Test Script Author Name
		extent.loadConfig(new File(System.getProperty("user.dir")+"//ReportsConfig.xml"));
		extent.addSystemInfo("Appium Version", "1.2.7").addSystemInfo("Environment", "TEST");		
	}

	@BeforeSuite
	public void numofTestCases() throws ClassNotFoundException {

		//int numberOfTestCases = GetMethods.TotalTestcase("Validate", this.getClass());
		appiumService.TOTAL_NUM_OF_TESTCASES=GetMethods.TotalTestcase("XP3_TC", this.getClass());
	}


	@AfterMethod()
	public void tearDown(ITestResult result,Method method) throws IOException, ParseException , InterruptedException	{

		if(result.getStatus()==ITestResult.FAILURE)	{	

			String screenshot_path=captureScreenshot(method.getName());
			String image= test.addScreenCapture(screenshot_path);
			test.log(LogStatus.FAIL,result.getThrowable());
			test.log(LogStatus.FAIL, "screenshot for failure: "+test.addScreenCapture(image));
			clearRecentApps();
		}
		extent.endTest(test);
		extent.flush();
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

	@BeforeTest
	public void setupSys() throws MalformedURLException, InterruptedException, FileNotFoundException, AWTException{
		properties=loadDriverAndProperties();
		Locators_DeviceStability loc=new Locators_DeviceStability(aDriver);
		Locators_BaseUtil loc1=new Locators_BaseUtil(aDriver);
		PageFactory.initElements(new AppiumFieldDecorator(getDriver()),loc);
		PageFactory.initElements(new AppiumFieldDecorator(getDriver()),loc1);
		excel=new ExcelReader(ExcelConstants.XP5S_XL_PATH);		
	}


	@Test(priority=1,dataProvider="DeviceStability", dataProviderClass=DataProviders.class)
	public void XP3_TC_001_Stability_Stability_Validate_Contact_Creation(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException  
	{
		APP_LOGS.info("===========XP3_Device_Stability============");
		startAdbLog("XP3_Device_Stability_01");
		SoftAssert sa1 = new SoftAssert();
		System.out.println("Hello Executing XP3 Product");
		navigateToApplication(data.get("Application"));
		deleteContact();
		customWait(1000);		
		for (int i = 1; i <=itr; i++) {
			navigateToApplication(data.get("Application"));
			createContact("Test",  i, refNum);
			validateContactCreate(i,sa1,"Test");
			customWait(3000);
		}		
		sa1.assertAll();
	}	


	@Test(priority=2,dataProvider="DeviceStability", dataProviderClass=DataProviders.class)
	public void XP3_TC_002_Stability_Validate_Send_MO_IMS_SMS(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException , ParseException 
	{
		APP_LOGS.info("===========XP3_Device_Stability============");
		startAdbLog("XP3_Device_Stability_02");
		SoftAssert sa2 = new SoftAssert();
		String fN = startRIL_Log();
		performAction();
		value =  validate_RIL_Logs(fN, "RIL_REQUEST_IMS_REGISTRATION_STATE {1, 1} [SUB0]","Failure may also be because Operator is NOT IMS Registered(NOT VoLTE).");
		System.out.println(value);
		if (value) {
			for (int i = 1; i <= itr; i++) {
				navigateToApplication(data.get("Application"));
				delete_All_Threads();
				navigateTo_NewMessage();
				type_New_Message(refNum, data.get("typeMessage"));
				send_SMS(i);
				validate_Sent_SMS(i,sa2);
				press_BackKey();
				customWait(2000);
			} 
		} else {
			test.log(LogStatus.SKIP, "NT : NOT a IMS enabled");
		}
		sa2.assertAll();
	}	


	@Test(priority=3,dataProvider="DeviceStability", dataProviderClass=DataProviders.class)
	public void XP3_TC_003_Stability_Validate_Send_MO_Non_IMS_SMS(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException  
	{
		APP_LOGS.info("===========XP3_Device_Stability============");
		startAdbLog("XP3_Device_Stability_03");
		SoftAssert sa3 = new SoftAssert();
		System.out.println(value);
		if (!value) {
			for (int i = 1; i <= itr; i++) {
				navigateToApplication(data.get("Application"));
				delete_All_Threads();
				navigateTo_NewMessage();
				type_New_Message(refNum, data.get("typeMessage"));
				send_SMS(i);
				validate_Sent_SMS(i,sa3);
				press_BackKey();
				customWait(2000);
			} 
		} else {
			test.log(LogStatus.SKIP, "NT :IMS enabled");
		}
		sa3.assertAll();
	}	


	@Test(priority=4,dataProvider="DeviceStability", dataProviderClass=DataProviders.class)
	public void XP3_TC_004_Stability_MO_SMS_Maximum_NumberOfCharacters(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException , ParseException 
	{
		APP_LOGS.info("===========XP3_Device_Stability============");
		startAdbLog("XP3_Device_Stability_04");	
		SoftAssert sa4 = new SoftAssert();
		for (int i = 1; i <=itr; i++) {
			navigateToApplication(data.get("Application"));
			delete_All_Threads();
			navigateTo_NewMessage();
			type_New_Message(refNum, data.get("typeMessage1"));
			customWait(1000);
			validate_CharacterAndPageNumber(Locators_DeviceStability.zero_Characters_FirstPage,data.get("expectedChar&PageNum1"),i,sa4);
			send_SMS(i);
			validate_Sent_SMS(i,sa4);
			press_BackKey();
			customWait(2000);
		}
		sa4.assertAll();
	}	


	@Test(priority=5,dataProvider="DeviceStability", dataProviderClass=DataProviders.class)
	public void XP3_TC_005_Stability_MT_SMS(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException  
	{
		APP_LOGS.info("===========XP3_Device_Stability============");
		startAdbLog("XP3_Device_Stability_05");	
		SoftAssert sa5 = new SoftAssert();
		for (int i = 1; i <=itr; i++) {		
			sendSMS_fromRefDevice();
			navigateToApplication(data.get("Application"));
			validateMsgSent("received SMS",sa5);	
			press_BackKey();
			customWait(2000);
		}
		sa5.assertAll();
	}	

	@Test(priority=6,dataProvider="DeviceStability", dataProviderClass=DataProviders.class)
	public void XP3_TC_006_Stability_MO_MMS_With_Picture(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException , ParseException 
	{
		APP_LOGS.info("===========XP3_Device_Stability============");
		startAdbLog("XP3_Device_Stability_06");	
		SoftAssert sa6 = new SoftAssert();
		Runtime.getRuntime().exec("adb -s "+p_Id+" shell svc data enable");
		for (int i = 1; i <=itr; i++) {
			launch_an_app("messaging");
			delete_All_Threads();
			navigateTo_NewMessage();
			navigateAttachOthers();
			clickOnAttach_Options(Locators_DeviceStability.capturePicture);
			capturePic_ADD();		
			type_Message(data.get("typeMessage"));
			changeToNumberMode();
			enter_ToField(refNum);
			send_SMS(i);
			press_BackKey();
			validate_Sent_SMS(i,sa6);
			press_BackKey();
			customWait(2000);
		}
		sa6.assertAll();
	}	


	@Test(priority=7,dataProvider="DeviceStability", dataProviderClass=DataProviders.class)
	public void XP3_TC_007_Stability_MO_MMS_With_Video(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException  
	{
		APP_LOGS.info("===========XP3_Device_Stability============");
		startAdbLog("XP3_Device_Stability_07");
		SoftAssert sa7 = new SoftAssert();
		for (int i = 1; i <=itr; i++) {
			launch_an_app("messaging");
			delete_All_Threads();
			navigateTo_NewMessage();
			navigateAttachOthers();
			clickOnAttach_Options(Locators_DeviceStability.captureVideo);
			captureVideo_ADD();
			customWait(2000);			
			type_Message(data.get("typeMessage"));
			changeToNumberMode();
			enter_ToField(refNum);
			send_SMS(i);
			press_BackKey();
			validate_Sent_SMS(i,sa7);
			press_BackKey();
			customWait(2000);
		}		
		sa7.assertAll();
	}	


	@Test(priority=8,dataProvider="DeviceStability", dataProviderClass=DataProviders.class)
	public void XP3_TC_008_Stability_Validate_MOVoiceCall_CSFB_from_Contacts(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException  
	{
		APP_LOGS.info("===========XP3_Device_Stability============");
		startAdbLog("XP3_Device_Stability_08");
		SoftAssert sa8 = new SoftAssert();
		String fN = startRIL_Log();
		performAction();
		value =  validate_RIL_Logs(fN, "RIL_REQUEST_IMS_REGISTRATION_STATE {1, 1} [SUB0]","Failure may also be because Operator is NOT IMS Registered(NOT VoLTE).");
		System.out.println(value);
		if(!value) {
			for (int i = 1; i <=itr; i++) {
				navigateToApplication(data.get("Application"));
				dailCallUsingDailPad(refNum);
				validateCall_Orio("called", i,"contacts",sa8);
				press_BackKey();
				customWait(3000);
			} 
		} else {
			test.log(LogStatus.SKIP, "NT : IMS is Enabled");
		}   	
		sa8.assertAll();
	}	


	@Test(priority=9,dataProvider="DeviceStability", dataProviderClass=DataProviders.class)
	public void XP3_TC_009_Stability_Validate_MOVoiceCall_CSFB_from_CallHistory(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException  
	{
		APP_LOGS.info("===========XP3_Device_Stability============");
		startAdbLog("XP3_Device_Stability_09");	
		SoftAssert sa9 = new SoftAssert();
		System.out.println(value);
		if(!value) {
			for (int i = 1; i <=itr; i++) {
				navigateToApplication(data.get("Application"));
				callHistory_Orio();
				validateCall_Orio("called", i,"Call History",sa9);
				press_BackKey();
				customWait(3000);
				press_BackKey();
			} 
		} else {
			test.log(LogStatus.SKIP, "NT : IMS is Enabled");
		} 
		sa9.assertAll();
	}	

	@Test(priority=10,dataProvider="DeviceStability", dataProviderClass=DataProviders.class)
	public void XP3_TC_010_Stability_Validate_MO_IMSVoiceCall_from_Contacts(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException  
	{
		APP_LOGS.info("===========XP3_Device_Stability============");
		startAdbLog("XP3_Device_Stability_10");	
		SoftAssert sa10 = new SoftAssert();
		System.out.println(value);
		if(value) {
			for (int i = 1; i <=itr; i++) {
				navigateToApplication(data.get("Application"));
				dailCallUsingDailPad(refNum);
				validateCall_Orio("called", i,"contacts",sa10);
				press_BackKey();
				customWait(3000);
			} 
		} else {
			test.log(LogStatus.SKIP, "NT : IMS is not Enabled");
		} 
		sa10.assertAll();
	}	

	@Test(priority=11,dataProvider="DeviceStability", dataProviderClass=DataProviders.class)
	public void XP3_TC_011_Stability_Validate_MO_IMSVoiceCall_from_CallHistory(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException  
	{
		APP_LOGS.info("===========XP3_Device_Stability============");
		startAdbLog("XP3_Device_Stability_11");	
		SoftAssert sa11 = new SoftAssert();
		System.out.println(value);
		if(value) {
			for (int i = 1; i <=itr; i++) {
				navigateToApplication(data.get("Application"));
				callHistory_Orio();
				validateCall_Orio("called", i,"Call History",sa11);
				press_BackKey();
				customWait(3000);
				press_BackKey();
			} 
		} else {
			test.log(LogStatus.SKIP, "NT : IMS is not Enabled");
		}   	
		sa11.assertAll();
	}	

	@Test(priority=12,dataProvider="DeviceStability", dataProviderClass=DataProviders.class)
	public void XP3_TC_012_Stability_Validate_WiFi_ConnectDisconnect_SSID(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException  
	{
		APP_LOGS.info("===========XP3_Device_Stability============");
		startAdbLog("XP3_Device_Stability_12");	
		SoftAssert sa12 = new SoftAssert();
		clearRecentApps();
		navigateToApplication(data.get("Application"));
		selectOptn_Orio("Network & Internet",Locators_DeviceStability.NetworkInternet) ;
		selectWIFIOptn();
		enable(Locators_DeviceStability.enabled_wifi,Locators_DeviceStability.disabled_wifi);		
		minWait();
		checkWifiConnected();
		for (int i = 1; i <=itr; i++) {
			navigateToApplication(data.get("Application"));
			selectOptn_Orio("Network & Internet",Locators_DeviceStability.NetworkInternet) ;
			selectWIFIOptn();
			selectSSIDwifi();		   
			enterPassword();
			validateWifiConnect_Orio(i,sa12);
			disconnectSSIDifConnected1();
			customWait(3000);
		}	
		sa12.assertAll();
	}

	@Test(priority=13,dataProvider="DeviceStability", dataProviderClass=DataProviders.class)
	public void XP3_TC_013_Stability_Validate_WiFi_Turn_ON_OFF(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException  
	{
		APP_LOGS.info("===========XP3_Device_Stability============");
		startAdbLog("XP3_Device_Stability_13");	
		SoftAssert sa13 = new SoftAssert();
		//		clearRecentApps();
		navigateToApplication(data.get("Application"));
		selectOptn_Orio("Network & Internet",Locators_DeviceStability.NetworkInternet) ;	
		for (int i = 1; i <=itr; i++) {
			selectWIFIOptn();
			customWait(3000);
			enable(Locators_DeviceStability.enabled_wifi,Locators_DeviceStability.disabled_wifi);		
			minWait();
			validate_ON_OFF_WiFiFeature(Locators_DeviceStability.WiFi_feature,i,sa13);
			customWait(3000);
		}		
		sa13.assertAll();
	}


	@Test(priority=14,dataProvider="DeviceStability", dataProviderClass=DataProviders.class)
	public void XP3_TC_014_Stability_Validate_Browse_Visit_WebPage(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException  
	{
		APP_LOGS.info("===========XP3_Device_Stability============");
		startAdbLog("XP3_Device_Stability_14");	
		SoftAssert sa14 = new SoftAssert();
		clearRecentApps();
		navigateToApplication("Settings");
		selectOptn_Orio("Network & Internet",Locators_DeviceStability.NetworkInternet) ;
		selectWIFIOptn();
		customWait(2000);
		enable(Locators_DeviceStability.enabled_wifi,Locators_DeviceStability.disabled_wifi);	
		selectSSIDwifi();		   
		enterPassword();
		customWait(3000);
		navigateToApplication(data.get("Application"));
		for (int i = 1; i<=11; i++) {
			enterUrl(data.get("WebSite"+i));
			validateUrlEntered(i,sa14);
			customWait(5000);
		}	
		sa14.assertAll();
	}

}
