/*package com.xp5S.test;

import java.awt.AWTException;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.util.Hashtable;
import java.util.Properties;

import javax.swing.JOptionPane;

import org.json.simple.parser.ParseException;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.graphics.gui.CountIncrementFile;
import com.graphics.gui.JsonFileReaderAndWriter;
import com.graphics.gui.ReturnTestResult;
import com.graphics.gui.ToolFrame;
import com.lowagie.text.pdf.codec.Base64.InputStream;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.ExtentTestInterruptedException;
import com.relevantcodes.extentreports.LogStatus;
import com.xp5S.util.ExcelConstants;
import com.xp5S.util.ExcelReader;
import com.xp5S.util.Locators_BaseUtil;
import com.xp5S.util.Locators_Sanity;
import com.xp5S.util.Locators_SonimCare;
import com.xp5S.util.XP5S_Sanity_Util;
import com.xp5S.util.BaseUtil;
import com.xp5S.util.DataProviders;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.AndroidKeyCode;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class XP5S_Sanity_ATT_Test extends XP5S_Sanity_Util {


	public ExcelReader excel;
	Properties properties;
	public static ExtentReports extent;
	public static ExtentTest test;
	public static ExtentTestInterruptedException testexception;
	//public AndroidDriver<AndroidElement> aDriver;

	@BeforeSuite
	public void beforeSuite() 
	{

		extent = new ExtentReports("src/test/resources/extentreport/XP5S_SanityTestReport.html", true);

	}


	@BeforeMethod()
	public  void beforeMethod(Method method) throws InterruptedException 
	{

		test = extent.startTest( (this.getClass().getSimpleName() +" :: "+  method.getName()),method.getName()); //Test Case Start Here
		test.assignAuthor(""); //Test Script Author Name
		extent.loadConfig(new File(System.getProperty("user.dir")+"//ReportsConfig.xml"));
		extent.addSystemInfo("Appium Version", "1.2.7").addSystemInfo(
				"Environment", "TEST"); 
		String BuildNumber=BaseUtil.fetchDeviceProperty("adb shell getprop ro.build.id");
		test.assignCategory("Build #:"+BuildNumber);


	}


	@AfterMethod()
	public void setProgressBar_TestResult()
	{

		int count =CountIncrementFile.getCount(2);  
		if(count==74) {
			count=count+26;
			CountIncrementFile.putCount(count);

		}
		else {

			ToolFrame.progressBar.setValue(count);
			count=count+2;
			CountIncrementFile.putCount(count);
		}	
	}

	@AfterMethod()
	public void tearDown(ITestResult result,Method method) throws IOException, InterruptedException
	{
		if(result.getStatus()==ITestResult.FAILURE)
		{			

			String screenshot_path=captureScreenshot(method.getName());
			String image= test.addScreenCapture(screenshot_path);
			test.log(LogStatus.FAIL,result.getThrowable());
			test.log(LogStatus.FAIL, "screenshot for failure: "+test.addScreenCapture(image));
			clearRecentApps();


		}
		extent.endTest(test);
		extent.flush();
	}



	@BeforeTest
	public void setupSys() throws MalformedURLException, InterruptedException, FileNotFoundException, AWTException{
		properties=loadDriverAndProperties();
		Locators_Sanity loc=new Locators_Sanity(aDriver);
		Locators_BaseUtil loc1 =new Locators_BaseUtil(aDriver);
		PageFactory.initElements(new AppiumFieldDecorator(getDriver()),loc1);
		PageFactory.initElements(new AppiumFieldDecorator(getDriver()),loc);
		excel=new ExcelReader(ExcelConstants.XP5S_XL_PATH);		
	}


	@Test(priority=1,dataProvider="SanityTest", dataProviderClass=DataProviders.class)
	public void XP5S_Sanity_PTT_01_Validate_Date_Time_TimeZone(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException 
	{
		APP_LOGS.info("===========Sanity_01============");
		startAdbLog("Sanity_01");
		recordVideo("Sanity_01");
		clearRecentApps();
		navigateTo_DateAndTime();
		validate_getDate_Time_TimeZone();
		test.log(LogStatus.PASS, "Test case status is Passed");
	}	
	
	@Test(priority=2,dataProvider="SanityTest", dataProviderClass=DataProviders.class)
	public void XP5S_Sanity_PTT_02_Validate_enable_disable_Flightmode_Phonesettings(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException 
	{
		APP_LOGS.info("===========Sanity_02============");
		startAdbLog("Sanity_02");
		recordVideo("Sanity_02");
		clearRecentApps();
		selectMoreOptions();
		validateEnabledisableFlightmode();
		test.log(LogStatus.PASS, "Test case status is Passed");
	}	
	
	@Test(priority=3,dataProvider="SanityTest", dataProviderClass=DataProviders.class)
	public void XP5S_Sanity_PTT_03_Validate_Selection_LTE(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException 
	{
		APP_LOGS.info("===========Sanity_03============");
		startAdbLog("Sanity_03");
		recordVideo("Sanity_03");
		clearRecentApps();
		selectCellularNetwork();
		validateLTESelection();
		test.log(LogStatus.PASS, "Test case status is Passed");
	}
	
	@Test(priority=4,dataProvider="SanityTest", dataProviderClass=DataProviders.class)
	public void XP5S_Sanity_PTT_04_Validate_WIFI_Enable_Disable_quickSettings(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========Sanity_04============");
		startAdbLog("Sanity_04");
		recordVideo("Sanity_04");
		clearRecentApps();
		selectQuickSettingPanel();
		validateEnableDisable(Locators_Sanity.wifi);
		test.log(LogStatus.PASS, "Test case status is Passed");
	}	
	
	@Test(priority=5,dataProvider="SanityTest", dataProviderClass=DataProviders.class)
	public void XP5S_Sanity_PTT_05_Validate_Bluetooth_Enable_Disable_quickSettings(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========Sanity_05============");
		startAdbLog("Sanity_05");
		recordVideo("Sanity_05");
		clearRecentApps();
		selectQuickSettingPanel();
		validateEnableDisable(Locators_Sanity.bluetooth);
		test.log(LogStatus.PASS, "Test case status is Passed");
	}
	
	@Test(priority=6,dataProvider="SanityTest", dataProviderClass=DataProviders.class)
	public void XP5S_Sanity_PTT_06_Validate_AiroplaneMode_Enable_Disable_quickSettings(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	 {
		APP_LOGS.info("===========Sanity_06============");
		startAdbLog("Sanity_06");
		recordVideo("Sanity_06");
		clearRecentApps();
		selectQuickSettingPanel();
		validateEnableDisable(Locators_Sanity.Flightmode);
		test.log(LogStatus.PASS, "Test case status is Passed");
	}	 
	
	@Test(priority=7,dataProvider="SanityTest", dataProviderClass=DataProviders.class)
	public void XP5S_Sanity_PTT_07_Validate_Mute_Speaker_Volume_MOCall(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException 
	{
		APP_LOGS.info("===========Sanity_07============");
		startAdbLog("Sanity_07");
		recordVideo("Sanity_07");
		clearRecentApps();
		selectPhoneDailer();
		clearCallLogs();
		callinitiate("08040302037");
		validateMuteSpeakerVolumeFun();
		test.log(LogStatus.PASS, "Test case status is Passed");
	}
	
	@Test(priority=8,dataProvider="SanityTest", dataProviderClass=DataProviders.class)
	public void XP5S_Sanity_PTT_08_Validate_CallLogs_Outgoingcall_(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException 
	{
		APP_LOGS.info("===========Sanity_08============");
		startAdbLog("Sanity_08");
		recordVideo("Sanity_08");
		clearRecentApps();
		selectPhoneDailer();
		validateOutgoingcalls();
		test.log(LogStatus.PASS, "Test case status is Passed");
	}
	
	@Test(priority=9,dataProvider="SanityTest", dataProviderClass=DataProviders.class)
	public void XP5S_Sanity_PTT_09_Validate_initiatecall_from_Logs(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException 
	{	
		APP_LOGS.info("===========Sanity_09============");
		startAdbLog("Sanity_09");
		recordVideo("Sanity_09");
		clearRecentApps();
		selectPhoneDailer();
		validatecallfromCallLogs();
		test.log(LogStatus.PASS, "Test case status is Passed");
	}

	@Test(priority=10,dataProvider="SanityTest", dataProviderClass=DataProviders.class)
	public void XP5S_Sanity_PTT_10_Validate_Create_Contact(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException 
	{
		APP_LOGS.info("===========Sanity_10============");
		startAdbLog("Sanity_10");
		recordVideo("Sanity_10");
		clearRecentApps();
		createContact("Test","08040302037");
		validateContactCreate();
		deleteContact();
		validateDeleteSavedContact();
		test.log(LogStatus.PASS, "Test case status is Passed");
	}	
	
	@Test(priority=11,dataProvider="SanityTest", dataProviderClass=DataProviders.class)
	public void XP5S_Sanity_PTT_11_Validate_Call_Delete_SavedContact(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException 
	{
		APP_LOGS.info("===========Sanity_11============");
		startAdbLog("Sanity_11");
		recordVideo("Sanity_11");
		clearRecentApps();
		createContact("Testing","08040302038");
		validatecallSavedContact("Testing");
		deleteContact();
		test.log(LogStatus.PASS, "Test case status is Passed");
	}

	@Test(priority=12,dataProvider="SanityTest", dataProviderClass=DataProviders.class)
	public void XP5S_Sanity_PTT_12_Validate_SinglePage_Msg_NewNum(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException 
	{
		APP_LOGS.info("===========Sanity_12============");
		startAdbLog("Sanity_12");
		recordVideo("Sanity_12");
		clearRecentApps();
		selectMessagingApp();
		composeMsg(data.get("composeMessage"),data.get("num"));
		validateMsgSent("8861581396");
		deleteMsg();
		test.log(LogStatus.PASS, "Test case status is Passed");
	}
	
	@Test(priority=13,dataProvider="SanityTest", dataProviderClass=DataProviders.class)
	public void XP5S_Sanity_PTT_13_Validate_SinglePage_Msg_SavedContact(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException 
	{
		APP_LOGS.info("===========Sanity_13============");
		startAdbLog("Sanity_13");
		recordVideo("Sanity_13");
		clearRecentApps();
		createContact("Test","08040302038");
		selectMessagingApp();
		composeMsg(data.get("composeMessage"),"Test");
		validateMsgSent("Test");
		deleteMsg();
		deleteContact();
		test.log(LogStatus.PASS, "Test case status is Passed");
	}		
	
	@Test(priority=14,dataProvider="SanityTest", dataProviderClass=DataProviders.class)
	public void XP5S_Sanity_PTT_14_Validate_enable_disable_dataservice(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{	
		APP_LOGS.info("===========Sanity_14============");
		startAdbLog("Sanity_14");
		recordVideo("Sanity_14");
		clearRecentApps();
		launchSettings();
		enableDisableCellularData();
		selectBrowserApp();
		validateDataEnableDisable();
		test.log(LogStatus.PASS, "Test case status is Passed");
	}
	
	@Test(priority=15,dataProvider="SanityTest", dataProviderClass=DataProviders.class)
	public void XP5S_Sanity_PTT_15_Validate_enable_disable_DataRoamingService(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========Sanity_15============");
		startAdbLog("Sanity_15");
		recordVideo("Sanity_15");
		clearRecentApps();
		String XP5deviceModelNumber=BaseUtil.fetchDeviceProperty("adb shell getprop gsm.version.baseband");
			if(XP5deviceModelNumber.contains("ATT")) {
				launchSettings();
				enableDataRoaming();
				validateDataRoamingService();
			}
			else if (XP5deviceModelNumber.contains("SL")) {
				launchSettings();
				enableDataRoaming();
				validateDataRoamingService();	
			}		 
			else if (XP5deviceModelNumber.contains("SPR")) {	  
				test.log(LogStatus.SKIP, "Test case status is Skipped  because TC required Sprint Sim card to validate");	  	
			}
			else {
				launchSettings();
				enableDataRoaming();
				validateDataRoamingService();	
			}
		test.log(LogStatus.PASS, "Test case status is Passed");
	}
	
	@Test(priority=16,dataProvider="SanityTest", dataProviderClass=DataProviders.class)
	public void XP5S_Sanity_PTT_16_Add_and_Delete_APN(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException 
	{		
	  APP_LOGS.info("===========Sanity_16============");
	  startAdbLog("Sanity_16");
	  recordVideo("Sanity_16");
	  clearRecentApps();
	  String XP5deviceModelNumber=BaseUtil.fetchDeviceProperty("adb shell getprop gsm.version.baseband");
		if(XP5deviceModelNumber.contains("ATT")) {	
			add_APN(data.get("apnName"),data.get("apn"),data.get("apnType"));
			  validate_Add_Or_Delete_APN(data.get("apnName"));
			  delete_APN();
			  validateDeleteApn(data.get("apnName"));
			test.log(LogStatus.PASS, "Test case status is Passed");
		}
		else if (XP5deviceModelNumber.contains("SL")) {		  
			test.log(LogStatus.PASS, "Test case status is Passed  because TC doesnot support SL");	
		}	 
		else if (XP5deviceModelNumber.contains("SPR")) {	  
			test.log(LogStatus.PASS, "Test case status is Passed  because TC doesnot support Sprint");	  	
		}	
		else {
			add_APN(data.get("apnName"),data.get("apn"),data.get("apnType"));
			  validate_Add_Or_Delete_APN(data.get("apnName"));
			  delete_APN();
			  validateDeleteApn(data.get("apnName"));
			test.log(LogStatus.PASS, "Test case status is Passed");
		}
	 }	 
	
	 @Test(priority=17,dataProvider="SanityTest", dataProviderClass=DataProviders.class)
	 public void XP5S_Sanity_PTT_17_EnableMHS_And_Check_EntitlementError(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException 
	 {
		 APP_LOGS.info("===========Sanity_17============");
		 startAdbLog("Sanity_17");
		 recordVideo("Sanity_17");
		 clearRecentApps();
		 String XP5deviceModelNumber=BaseUtil.fetchDeviceProperty("adb shell getprop gsm.version.baseband");
		 	if(XP5deviceModelNumber.contains("ATT")) {
		 		turn_ON_MobileData();
		 		turn_ON_USBtethering();
		 		validate_Entitlement_Error();
		 		turn_ON_PortableWifi();
		 		validate_Entitlement_Error();
		 		turn_ON_BluetoothTethering();
		 		validate_Entitlement_Error();  
		 		test.log(LogStatus.PASS, "Test case status is Passed");		  
		 	}
		 	else if (XP5deviceModelNumber.contains("SL")) {		  
		 		test.log(LogStatus.PASS, "Test case status is Passed  because TC doesnot support SL");	
		 	}	 
		 	else if (XP5deviceModelNumber.contains("SPR")) {	  
		 		turn_ON_MobileData();
//				turn_ON_USBtethering();//bcz device disconnects//Not applicable for SPR
//		 		validate_Entitlement_Error_sprint();
		 		turn_ON_PortableWifi();
		 		validate_Entitlement_Error_sprint();
		 		turn_ON_BluetoothTethering();
		 		validate_Entitlement_Error_sprint();
		 		test.log(LogStatus.PASS, "Test case status is Passed");	  	
		 	} 
		 	else {		
		 		test.log(LogStatus.PASS, "Test case status is Passed  because TC doesnot support");	
		 	}
	 }	

	 @Test(priority=18,dataProvider="SanityTest", dataProviderClass=DataProviders.class)
	 public void XP5S_Sanity_PTT_18_Validate_Connection_secured_Wifi(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException 
	 {
		 APP_LOGS.info("===========Sanity_18============");
		 startAdbLog("Sanity_18");
		 recordVideo("Sanity_18");
		 clearRecentApps();
		 ConnectSecuredWifi();
		 validateWifiConnect();
		 customWait(4000);
		 test.log(LogStatus.PASS, "Test case status is Passed");
	 }	  

	 @Test(priority=19,dataProvider="SanityTest", dataProviderClass=DataProviders.class)
     public void XP5S_Sanity_PTT_19_Validate_Search_Bluetooth(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException 
	 {
		 APP_LOGS.info("===========Sanity_19============");
		 startAdbLog("Sanity_19");
		 recordVideo("Sanity_19");
		 clearRecentApps();
		 selectSettings(Locators_Sanity.bluetooth);
		 enableparticularSettings(Locators_Sanity.bluetooth_notavailable);		
		 customWait(4000);
		 test.log(LogStatus.PASS, "Test case status is Passed");
	 }
		
	@Test(priority=20,dataProvider="SanityTest", dataProviderClass=DataProviders.class)
	public void XP5S_Sanity_PTT_20_Validate_TurnOn_Off_FM(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========Sanity_20============");
		startAdbLog("Sanity_20");
		recordVideo("Sanity_20");
		clearRecentApps();
		selectFMApp();
		turnOFF_FM();
		validateOn_OFF_FM();
		test.log(LogStatus.PASS, "Test case status is Passed");
	}	

	@Test(priority=21,dataProvider="SanityTest", dataProviderClass=DataProviders.class)
	public void XP5S_Sanity_PTT_21_Validate_Replace_SaveChannel(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{	
		APP_LOGS.info("===========Sanity_21============");
		startAdbLog("Sanity_21");
		recordVideo("Sanity_21");
		clearRecentApps();
		selectFMApp();
		validatereplaceChannel();
		test.log(LogStatus.PASS, "Test case status is Passed");
	}	
		
	@Test(priority=22,dataProvider="SanityTest", dataProviderClass=DataProviders.class)
	public void XP5S_Sanity_PTT_22_Validate_LoadHomePage_Browser(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========Sanity_22============");
		startAdbLog("Sanity_22");
		recordVideo("Sanity_22");
		clearRecentApps();
		selectBrowserApp();
		validateHomePageLoadedBrowser();
		test.log(LogStatus.PASS, "Test case status is Passed");
	}
		
    @Test(priority=23,dataProvider="SanityTest", dataProviderClass=DataProviders.class)
	public void XP5S_Sanity_PTT_23_Validate_DisplaySearch_Word_EnterUrl_BrowserUrl(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========Sanity_23============");
		startAdbLog("Sanity_23");
		recordVideo("Sanity_23");
		clearRecentApps();
		selectBrowserApp();
		validateSearchWord("amazon");
		enterUrl("www.amazon.com");
		validateUrlEntered(Locators_Sanity.amazonLogo,Locators_Sanity.ebay,Locators_Sanity.yahoo,Locators_Sanity.facebook);
		test.log(LogStatus.PASS, "Test case status is Passed");
	}
	
	@Test(priority=24,dataProvider="SanityTest", dataProviderClass=DataProviders.class)
	public void XP5S_Sanity_PTT_24_Validate_DeafultPhone_Notification_Ringtone(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========Sanity_24============");
		startAdbLog("Sanity_24");
		recordVideo("Sanity_24");
		clearRecentApps();
		selectSettings(Locators_Sanity.Sound);
		ValidatedefaultSettings();
		test.log(LogStatus.PASS, "Test case status is Passed");
	}
	
	@Test(priority=25,dataProvider="SanityTest", dataProviderClass=DataProviders.class)
	public void XP5S_Sanity_PTT_25_Validate_Deafult_Language(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========Sanity_25============");
		startAdbLog("Sanity_25");
		recordVideo("Sanity_25");
		clearRecentApps();
		selectSettings(Locators_Sanity.defaultLaunguageOptn);
		validateDefaultLanguage();
		test.log(LogStatus.PASS, "Test case status is Passed");
	}
	
	@Test(priority=26,dataProvider="SanityTest", dataProviderClass=DataProviders.class)
	public void XP5S_Sanity_PTT_26_Validate_Preffered_NetworkMode_NotSupported(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========Sanity_26============");
		startAdbLog("Sanity_26");
		recordVideo("Sanity_26");
		clearRecentApps();		
		String XP5deviceModelNumber=BaseUtil.fetchDeviceProperty("adb shell getprop gsm.version.baseband");
		  	if(XP5deviceModelNumber.contains("ATT")) {
		  		selectSettings(Locators_Sanity.MobileNetwrks);
				navigateTo_APN();
				validatePrefferedNetwork(Locators_Sanity.Network_Operators);
		  	}
		  	else if (XP5deviceModelNumber.contains("SL")) {
		  		test.log(LogStatus.PASS, "Test case status is Passed  because TC doesnot support for SL ");	 
		  	}		 
		  	else if (XP5deviceModelNumber.contains("SPR")) {	
		  		SoftAssert sf = new SoftAssert();
		  		selectSettings(Locators_Sanity.moreOptn);
		  		minWait();
		  		clickBtn(Locators_Sanity.cellular_newtworksOptn);
		  		minWait();
				if(Locators_Sanity.PreferredNwType_Operators.isEnabled()) {
					minWait();	
					check = true;
					APP_LOGS.info(" Preffered Network mode is Supportable");
					sf.assertTrue(check, "Validation is Pass");
				}
				else{
					minWait();	
					APP_LOGS.info("Preffered Network mode is not Supportable");
					sf.fail();
				}	
				sf.assertAll();
				minWait();		  
				test.log(LogStatus.PASS, "Test case status is Passed");
		}
		test.log(LogStatus.PASS, "Preffered Network mode is not Supportable");
	}
	
	@Test(priority=27,dataProvider="SanityTest", dataProviderClass=DataProviders.class)
	public void XP5S_Sanity_PTT_27_Validate_Edition_Deletion_APns(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========Sanity_27============");
		startAdbLog("Sanity_27");
		recordVideo("Sanity_27");		
		clearRecentApps();
		selectSettings(Locators_Sanity.MobileNetwrks);
		navigateTo_APN();
		validateEditionDeletionApn();
		test.log(LogStatus.PASS, "Test case status is Passed");
	}	
	
	@Test(priority=28,dataProvider="SanityTest", dataProviderClass=DataProviders.class)
	public void XP5S_Sanity_PTT_28_Validate_Restore_Default_APns(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========Sanity_28============");
		startAdbLog("Sanity_28");
		recordVideo("Sanity_28");
		clearRecentApps();
		navigateTo_APN();
		validateRestoreAPn();
		test.log(LogStatus.PASS, "Test case status is Passed");
	}	
	
	@Test(priority=29,dataProvider="SanityTest", dataProviderClass=DataProviders.class)
	public void XP5S_Sanity_PTT_29_Validate_Press_Release_HardKeys(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========Sanity_29============");
		startAdbLog("Sanity_29");
		recordVideo("Sanity_29");
		clearRecentApps();
		validateselectHardKeys();
		test.log(LogStatus.PASS, "Test case status is Passed");
	}
	
	@Test(priority=30,dataProvider="SanityTest", dataProviderClass=DataProviders.class)
	public void XP5S_Sanity_PTT_30_Validate_SetDevice_ScreenLock(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========Sanity_30============");
		startAdbLog("Sanity_30");
		recordVideo("Sanity_30");
		clearRecentApps();
		selectSettings(Locators_Sanity.Security);
		setPinLock();
		validateSetPIN();
		test.log(LogStatus.PASS, "Test case status is Passed");
	}	
	
	@Test(priority=31,dataProvider="SanityTest", dataProviderClass=DataProviders.class)
	public void XP5S_Sanity_PTT_31_Validate_Launch_BasicOperation_Calculator(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========Sanity_31============");
		startAdbLog("Sanity_31");
		recordVideo("Sanity_31");
		clearRecentApps();
		selectApp(Locators_Sanity.calciApp);
		basicOperationwithoutdecimalpt();
		test.log(LogStatus.PASS, "Test case status is Passed");
	}

	@Test(priority=32,dataProvider="SanityTest", dataProviderClass=DataProviders.class)
	public void XP5S_Sanity_PTT_32_Validate_Launch_CheckEventCalender(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========Sanity_32============");
		startAdbLog("Sanity_32");
		recordVideo("Sanity_32");
		clearRecentApp(Locators_Sanity.RecorderApp);
		deleteEventCreated();
		selectCalenderApp();
		createNewEvent();
		deleteEventCreated();
		test.log(LogStatus.PASS, "Test case status is Passed");
	}	
	
	@Test(priority=33,dataProvider="SanityTest", dataProviderClass=DataProviders.class)
	public void XP5S_Sanity_PTT_33_Validate_Launch_FileExplorer(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{ 
		APP_LOGS.info("===========Sanity_33============");
		startAdbLog("Sanity_33");
		recordVideo("Sanity_33");
		clearRecentApps();
		capturePic();
		validateFileExplorer();
		test.log(LogStatus.PASS, "Test case status is Passed");
	}	

	@Test(priority=34,dataProvider="SanityTest", dataProviderClass=DataProviders.class)
	public void XP5S_Sanity_PTT_34_Validate_Launch_Recording(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{  
		APP_LOGS.info("===========Sanity_34============");
		startAdbLog("Sanity_34");
		recordVideo("Sanity_34");
		clearRecentApps();
		selectApp(Locators_Sanity.RecorderApp);
		recordSound();
		validateRecordedSoundSaved();
		selectListOptn();
		deleteSavedRecorder();
		test.log(LogStatus.PASS, "Test case status is Passed");
	}
	
	@Test(priority=35,dataProvider="SanityTest", dataProviderClass=DataProviders.class)
	public void XP5S_Sanity_PTT_35_Validate_Clock_Menu(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{  
		APP_LOGS.info("===========Sanity_35============");
		startAdbLog("Sanity_35");
		recordVideo("Sanity_35");
		clearRecentApps();
		selectApp(Locators_Sanity.clockApp);
		validateClockApp() ;
		test.log(LogStatus.PASS, "Test case status is Passed");
	}
	
	@Test(priority=36,dataProvider="SanityTest", dataProviderClass=DataProviders.class)
	public void XP5S_Sanity_PTT_36_Validate_Internal_Storage_Space(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{  
		APP_LOGS.info("===========Sanity_36============");
		startAdbLog("Sanity_36");
		recordVideo("Sanity_36");
		clearRecentApps();
		selectSettings(Locators_Sanity.Storage_Optn);
		validateInternalStorage();
		test.log(LogStatus.PASS, "Test case status is Passed");
	}
	
	@Test(priority=37,dataProvider="SanityTest", dataProviderClass=DataProviders.class)
	public void XP5S_Sanity_PTT_37_Validate_ThunderSoft_client(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========Sanity_37============");
		startAdbLog("Sanity_37");
		recordVideo("Sanity_37");
		clearRecentApps();
		String XP5deviceModelNumber=BaseUtil.fetchDeviceProperty("adb shell getprop gsm.version.baseband");
			if(XP5deviceModelNumber.contains("ATT")) {
				selectSettings(Locators_Sanity.AboutPhone);
				validateThundersoftUpdate();
			}
			else if (XP5deviceModelNumber.contains("SL")) {
				selectSettings(Locators_Sanity.AboutPhone);
				validateThundersoftUpdate();
			}
			else if (XP5deviceModelNumber.contains("SPR")) {	  
				test.log(LogStatus.PASS, "Test case status is Passed  because TC doesnot support for Sprint ");	 
			}
		test.log(LogStatus.PASS, "Test case status is Passed");
	}
	
	@Test(priority=38,dataProvider="SanityTest", dataProviderClass=DataProviders.class)
	public void XP5S_Sanity_PTT_38_Validate_SIM_PIN_Lock(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========Sanity_38============");
		startAdbLog("Sanity_38");
		recordVideo("Sanity_38");
		clearRecentApps();
		selectSettings(Locators_Sanity.Security);
		selectSetSIMLock();
		validateSIMLock();	
		test.log(LogStatus.PASS, "Test case status is Passed");
	}
}
*/