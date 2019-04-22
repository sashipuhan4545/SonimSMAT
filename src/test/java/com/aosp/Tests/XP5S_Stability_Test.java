package com.aosp.Tests;

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
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aosp.Utils.DataProviders;
import com.aosp.Utils.Locators_Stability;
import com.aosp.Utils.XP5S_Data_Utils;
import com.aosp.Utils.XP5S_Stability_Util;
import com.graphics.gui.JsonFileReaderAndWriter;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTestInterruptedException;
import com.relevantcodes.extentreports.LogStatus;
import com.xp5S.util.BaseUtil;

import com.xp5S.util.ExcelConstants;
import com.xp5S.util.ExcelReader;
import com.xp5S.util.GetMethods;
import com.xp5S.util.Locators_BaseUtil;
import com.xp5S.util.appiumService;

import application.AllQA;
import application.SalesTeam;
import io.appium.java_client.android.AndroidKeyCode;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class XP5S_Stability_Test extends XP5S_Stability_Util {

	Properties properties;
	public ExcelReader excel;
	public static ExtentTestInterruptedException testexception;
	public static int itr=30;
	public boolean  value= false;

	@BeforeSuite
	public void beforeSuite() throws IOException, ParseException , ParseException, InterruptedException 
	{
		extent = new ExtentReports("src/test/resources/extentreport/XP5S_DeviceStability_TestReport.html", true);
		fetch_Devices_Details();
	}
	
	@BeforeSuite
	public void numofTestCases() throws ClassNotFoundException {

      //  int numberOfTestCases = GetMethods.TotalTestcase("XP5S", this.getClass());
		appiumService.TOTAL_NUM_OF_TESTCASES=GetMethods.TotalTestcase("XP5S", this.getClass());
	}


	@BeforeMethod()
	public  void beforeMethod(Method method) throws InterruptedException, FileNotFoundException, IOException, ParseException , ParseException 
	{
		test = extent.startTest( (this.getClass().getSimpleName() +" :: "+  method.getName()),method.getName()); //Test Case Start Here
		test.assignAuthor(""); //Test Script Author Name
		extent.loadConfig(new File("src/test/resources/StorageFile/ReportsConfig.xml"));
	
		extent.addSystemInfo("Build #",JsonFileReaderAndWriter.primaryDevFirmwareReader())
		.addSystemInfo("Product",JsonFileReaderAndWriter.primaryDevModelReader())
		.addSystemInfo("Operator", JsonFileReaderAndWriter.primaryDevOperatorReader())
		.addSystemInfo("PrimaryDevice MDN", AllQA.PRIMARYDEVMDN)
        .addSystemInfo("ReferenceDeviceMDN", AllQA.REFERENCEDEVMDN);

	}

	@AfterMethod()
	public void tearDown(ITestResult result,Method method) throws IOException, ParseException , ParseException , InterruptedException
	{
		if(result.getStatus()==ITestResult.FAILURE)
		{
			String screenshot_path=captureScreenshot(method.getName());		
			test.log(LogStatus.FAIL,result.getThrowable());
			test.log(LogStatus.FAIL, "screenshot for failure:" +test.addScreenCapture(screenshot_path));
			clearRecentApps();
		}
		extent.endTest(test);
		extent.flush();
	}

	@BeforeSuite
	public  void clearAdbLogsFromLogDir()  
	{
		try {
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
		}catch(Exception e) {
			System.out.println("Some problem occurs in clearAdbLogFromDir");
		}
		  
	}

	@BeforeTest
	public void setupSys() throws MalformedURLException, InterruptedException, FileNotFoundException, AWTException{
		properties=loadDriverAndProperties();
		Locators_Stability loc=new Locators_Stability(aDriver);
		Locators_BaseUtil loc1 =new Locators_BaseUtil(aDriver);

		PageFactory.initElements(new AppiumFieldDecorator(getDriver()),loc1);
		PageFactory.initElements(new AppiumFieldDecorator(getDriver()),loc);
		excel=new ExcelReader(ExcelConstants.XP5S_XL_PATH);		
	}


	@Test(priority=1,dataProvider="XP5S_Stability", dataProviderClass=DataProviders.class)
	public void XP5S_TC_01_Stability_Validate_Contact_Creation(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException , ParseException {

		APP_LOGS.info("=========== XP5S_TC_01_Stability============");
		startAdbLog("XP5S_TC_01_Stability");
		recordVideo("XP5S_TC_01_Stability");
		SoftAssert sa1 = new SoftAssert();
		clearRecentApps();
		launch_an_app("contacts");
		deleteContact();
		customWait(1000);		
		for (int i = 1; i <=itr; i++) {
			launch_an_app("contacts");
			createContact("Test",  i, refNum);
			validateContactCreate(i,sa1);
			customWait(3000);
		}	
		sa1.assertAll();
		test.log(LogStatus.PASS, "Test case status is Passed");
	}

	@Test(priority=2,dataProvider="XP5S_Stability", dataProviderClass=DataProviders.class)
	public void XP5S_TC_02_Stability_Validate_Send_MO_IMS_SMS(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException  {

		APP_LOGS.info("=========== XP5S_TC_02_Stability============");
		startLog("XP5S_TC_02_Stability");
		SoftAssert sa2 = new SoftAssert();
		String fN = startRIL_Log();
		performAction();
	    value =  validate_RIL_Logs(fN, "RIL_REQUEST_IMS_REGISTRATION_STATE {1, 1} [SUB0]","Failure may also be because Operator is NOT IMS Registered(NOT VoLTE).");
		System.out.println(value);
	    if (value) {
			for (int i = 1; i <= itr; i++) {
				launch_an_app("messaging");
				delete_All_Threads();
				navigateTo_NewMessage();
				type_New_Message(refNum, data.get("typeMessage"));
				send_SMS();
				validate_Sent_SMS(i,sa2);
				press_BackKey();
				customWait(2000);
			} 
		} else {
			test.log(LogStatus.SKIP, "NT : NOT a IMS enabled");
		}
	    sa2.assertAll();
	}

	@Test(priority=3,dataProvider="XP5S_Stability", dataProviderClass=DataProviders.class)
	public void XP5S_TC_03_Stability_Validate_Send_MO_Non_IMS_SMS(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException  {

		APP_LOGS.info("=========== XP5S_TC_03_Stability============");
		startLog("XP5S_TC_03_Stability");;
		recordVideo("XP5S_TC_03_Stability");
		SoftAssert sa3 = new SoftAssert();
		System.out.println(value);
		if(!value) {
		for (int i = 1; i <=itr; i++) {
			launch_an_app("messaging");
			delete_All_Threads();
			navigateTo_NewMessage();
			type_New_Message(refNum, data.get("typeMessage"));
			send_SMS();
			validate_Sent_SMS(i,sa3);
		    press_BackKey();
			customWait(2000);
		 } 
	   } else {
		   test.log(LogStatus.SKIP, "NT : NOT a IMS enabled");
	   }   
		sa3.assertAll();
	}

	

	@Test(priority=4,dataProvider="XP5S_Stability", dataProviderClass=DataProviders.class)
	public void XP5S_TC_04_Stability_MO_SMS_Maximum_NumberOfCharacters(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException  {

		APP_LOGS.info("=========== XP5S_TC_04_Stability============");
		startAdbLog("XP5S_TC_04_Stability");
		recordVideo("XP5S_TC_04_Stability");
		customWait(1000);
		SoftAssert sa4 = new SoftAssert();
		for (int i = 1; i <=itr; i++) {
			launch_an_app("messaging");
			delete_All_Threads();
			navigateTo_NewMessage();
			type_New_Message(refNum, data.get("typeMessage1"));
			customWait(1000);
			validate_CharacterAndPageNumber(Locators_Stability.zero_Characters_FirstPage,data.get("expectedChar&PageNum1"));
			send_SMS();
			validate_Sent_SMS(i,sa4);
			press_BackKey();
			customWait(2000);
		}
		sa4.assertAll();
	}

	@Test(priority=5,dataProvider="XP5S_Stability", dataProviderClass=DataProviders.class)
	public void XP5S_TC_05_Stability_MT_SMS(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException  {

		APP_LOGS.info("=========== XP5S_TC_05_Stability============");
		startAdbLog("XP5S_TC_05_Stability");
		recordVideo("XP5S_TC_05_Stability");
		SoftAssert sa5 = new SoftAssert();
		customWait(1000);
		for (int i = 1; i <=itr; i++) {
			launch_an_app("messaging");
			delete_All_Threads();
			launch_an_app("messaging");
			sendSMS_fromRefDevice();
			validate_Sent_SMS(i,sa5);
			press_BackKey();
			customWait(2000);
		}
		sa5.assertAll();
	}

	@Test(priority=6,dataProvider="XP5S_Stability", dataProviderClass=DataProviders.class)
	public void XP5S_TC_06_Stability_MO_MMS_With_Picture(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException  {

		APP_LOGS.info("=========== XP5S_TC_06_Stability============");
		startAdbLog("XP5S_TC_06_Stability");
		recordVideo("XP5S_TC_06_Stability");
		SoftAssert sa6 = new SoftAssert();
		customWait(1000);
		Runtime.getRuntime().exec("adb -s "+p_Id+" shell svc data enable");
		for (int i = 1; i <=itr; i++) {
			launch_an_app("messaging");
			delete_All_Threads();
			navigateTo_NewMessage();
			navigateAttachOthers();
			clickOnAttach_Options(Locators_Stability.capturePicture);
			capturePic_ADD();
			validate_AnyAdded_Attachment(Locators_Stability.image_Attachment);		
			type_Message(data.get("typeMessage"));
			enter_ToField(refNum);
			send_SMS();
			press_BackKey();
			validate_Sent_SMS(i,sa6);
			press_BackKey();
			customWait(2000);
		}
		sa6.assertAll();
	}

	@Test(priority=7,dataProvider="XP5S_Stability", dataProviderClass=DataProviders.class)
	public void XP5S_TC_07_Stability_MO_MMS_With_Video(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException  {

		APP_LOGS.info("=========== XP5S_TC_07_Stability============");
		startAdbLog("XP5S_TC_07_Stability");
		recordVideo("XP5S_TC_07_Stability");
		SoftAssert sa7 = new SoftAssert();
		customWait(1000);
		Runtime.getRuntime().exec("adb -s "+p_Id+" shell svc data enable");
		customWait(2000);	
		for (int i = 1; i <=itr; i++) {
			launch_an_app("messaging");
			delete_All_Threads();
			navigateTo_NewMessage();
			navigateAttachOthers();
			clickOnAttach_Options(Locators_Stability.captureVideo);
			captureVideo_ADD();
			validate_AnyAdded_Attachment(Locators_Stability.video_Attachment);
			customWait(2000);			
			type_Message(data.get("typeMessage"));
			enter_ToField(refNum);
			send_SMS();
			press_BackKey();
			validate_Sent_SMS(i,sa7);
			press_BackKey();
			customWait(2000);
		}
		sa7.assertAll();
	}

	@Test(priority=8,dataProvider="XP5S_Stability", dataProviderClass=DataProviders.class)
	public void XP5S_TC_08_Stability_Validate_MOVoiceCall_CSFB_from_Contacts(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException  
	{
		APP_LOGS.info("=========== XP5S_TC_08_Stability============");
		startLog("XP5S_TC_08_Stability");
		recordVideo("XP5S_TC_08_Stability");
		SoftAssert sa8 = new SoftAssert();
		System.out.println(value);
		if(!value) {
		for (int i = 1; i <=itr; i++) {
			launch_an_app("contacts");
			performCall();		
			validateCall("called","XP5S_TC_08_Stability", i,"contacts",sa8);
			customWait(3000);
		 } 
		   } else {
			   test.log(LogStatus.SKIP, "NT : IMS is Enabled");
		   }   	
		sa8.assertAll();
		test.log(LogStatus.PASS, "Test case status is Passed");
	}


	@Test(priority=9,dataProvider="XP5S_Stability", dataProviderClass=DataProviders.class)
	public void XP5S_TC_09_Stability_Validate_MOVoiceCall_CSFB_from_CallHistory(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException  
	{
		APP_LOGS.info("=========== XP5S_TC_09_Stability============");
		startLog("XP5S_TC_09_Stability");
		recordVideo("XP5S_TC_09_Stability");	
		SoftAssert sa9 = new SoftAssert();
		System.out.println(value);
		if(!value) {
			for (int i = 1; i <=itr; i++) {
				launch_an_app("phone");
				callHistory();
				validateCall("called","XP5S_TC_29_Stability", i,"Call History",sa9);
				customWait(3000);	

			} 
		} else {
			test.log(LogStatus.SKIP, "NT :IMS is enabled");
		}   
		sa9.assertAll();
		test.log(LogStatus.PASS, "Test case status is Passed");
	}


	@Test(priority=10,dataProvider="XP5S_Stability", dataProviderClass=DataProviders.class)
	public void XP5S_TC_10_Stability_Validate_MO_IMSVoiceCall_from_Contacts(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException  
	{
		APP_LOGS.info("=========== XP5S_TC_10_Stability============");
		startLog("XP5S_TC_10_Stability");
		recordVideo("XP5S_TC_10_Stability");
		SoftAssert sa10 = new SoftAssert();
		System.out.println(value);
		if(value) {
			for (int i = 1; i <=itr; i++) {
				launch_an_app("contacts");
				performCall();
				validateCall("called","XP5S_TC_10_Stability", i,"contacts",sa10);	
				customWait(3000);	
			}						 
		} else {
			
				test.log(LogStatus.SKIP, "IMS is not enabled");
			
		}   
		sa10.assertAll();
		
		test.log(LogStatus.PASS, "Test case status is Passed");
	}


	@Test(priority=11,dataProvider="XP5S_Stability", dataProviderClass=DataProviders.class)
	public void XP5S_TC_11_Stability_Validate_MO_IMSVoiceCall_from_CallHistory(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException  
	{
		APP_LOGS.info("=========== XP5S_TC_11_Stability============");
		startLog("XP5S_TC_11_Stability");
		recordVideo("XP5S_TC_11_Stability");	
		SoftAssert sa11 = new SoftAssert();
		System.out.println(value);
		if(value) {
			for (int i = 1; i <=itr; i++) {
				launch_an_app("phone");
				callHistory();			
				validateCall("called","XP5S_TC_11_Stability", i,"Call History",sa11);
				customWait(2000);
			}				
		} else {
				test.log(LogStatus.SKIP, "IMS is not enabled");		 
		}
		sa11.assertAll();
		test.log(LogStatus.PASS, "Test case status is Passed");
	}



	@Test(priority=12,dataProvider="XP5S_Stability", dataProviderClass=DataProviders.class)
	public void XP5S_TC_12_Stability_Validate_WiFi_ConnectDisconnect_SSID(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException  
	{
		APP_LOGS.info("=========== XP5S_TC_12_Stability============");
		startLog("XP5S_TC_12_Stability");
		recordVideo("XP5S_TC_12_Stability");
		SoftAssert sa12 = new SoftAssert();
		clearRecentApps();
		launch_an_app("settings");
		selectWIFIOptn();
		enableFeature(Locators_Stability.enabled,Locators_Stability.disabled,Locators_Stability.switch_Title);
		minWait();
		disconnectSSIDifConnected();
		for (int i = 1; i <=itr; i++) {
			launch_an_app("settings");
			selectWIFIOptn();
			selectRefresh();
			selectSSIDwifi();		   
			enterPassword();
			validateWifiConnect(i,sa12);
			selectWIFIOptn();
			disconnectSSIDifConnected();
			customWait(3000);
		}	
		sa12.assertAll();
		test.log(LogStatus.PASS, "Test case status is Passed");
	}


	@Test(priority=13,dataProvider="XP5S_Stability", dataProviderClass=DataProviders.class)
	public void XP5S_TC_13_Stability_Validate_WiFi_Turn_ON_OFF(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException  
	{
		startLog("XP5S_TC_13_Stability");
		recordVideo("XP5S_TC_13_Stability");
		SoftAssert sa13 = new SoftAssert();
		launch_an_app("settings");	  
		for (int i = 1; i <=itr; i++) {
			selectWIFIOptn();
			validate_ON_OFF_WiFiFeature(Locators_Stability.WiFi_feature,i,sa13);
			customWait(3000);
		}	
		sa13.assertAll();
		test.log(LogStatus.PASS, "Test case status is Passed");
	}

	@Test(priority=14,dataProvider="XP5S_Stability", dataProviderClass=DataProviders.class)
	public void XP5S_TC_14_Stability_Validate_Browse_Visit_WebPage(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException  
	{
		startAdbLog("XP5S_TC_14_Stability");
		recordVideo("XP5S_TC_14_Stability");
		SoftAssert sa14 = new SoftAssert();
		launch_an_app("browser");	  
		for (int i = 1; i<=11; i++) {
			enterUrl(data.get("WebSite"+i));
			validateUrlEntered(i,sa14);
			customWait(5000);
		}			   
		test.log(LogStatus.PASS, "Test case status is Passed");
	}

	@Test(priority=15,dataProvider="XP5S_Stability", dataProviderClass=DataProviders.class)
	public void XP5S_TC_15_Stability_Validate_Image_Capture_Delete(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException  
	{
		startAdbLog("XP5S_TC_15_Stability");
		recordVideo("XP5S_TC_15_Stability");
		SoftAssert sa15 = new SoftAssert();
		launch_an_app("gallery");
		clearAllAlbums();	
		for (int i = 1; i<=itr; i++) {
			cameraPic();
			launch_an_app("gallery");
			navigateToCameraFolder();
			deleteImage();
			launch_an_app("gallery");
			validateGalleryFolder(Locators_Stability.Gallery_Camera,"Image",i,sa15);
			press_BackKey();
		}		
		sa15.assertAll();
		test.log(LogStatus.PASS, "Test case status is Passed");
	}


	@Test(priority=16,dataProvider="XP5S_Stability", dataProviderClass=DataProviders.class)
	public void XP5S_TC_16_Stability_Validate_Video_Capture_Delete(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException  
	{
		startAdbLog("XP5S_TC_16_Stability");
		recordVideo("XP5S_TC_16_Stability");
		SoftAssert sa16 = new SoftAssert();
		APP_LOGS.info("Started =====================XP5S_TC_16_Stability============");
		launch_an_app("gallery");
		//		   clearAllAlbums();	
		for (int i = 1; i<=itr; i++) {	 
			launch_an_app("gallery");
			takeCameraVideo(2);
			launch_an_app("gallery");
			navigateToVideoFolder();
			validateDeleteVideo("Video", i,sa16);
		}	
		sa16.assertAll();
		test.log(LogStatus.PASS, "Test case status is Passed");
	}



}