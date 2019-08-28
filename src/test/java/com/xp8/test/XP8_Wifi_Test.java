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
import com.xp8.util.DataProviders;
import com.xp8.util.ExcelConstants;
import com.xp8.util.ExcelReader;
import com.xp8.util.Locators_BaseUtil;
import com.xp8.util.Locators_Wifi;
import com.xp8.util.XP8_Wifi_Util;

import OCR.Read_File;
import OCR.my_main;
import application.AllQA;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class XP8_Wifi_Test extends XP8_Wifi_Util {
	public ExcelReader excel;
	Properties properties;
	public static ExtentTestInterruptedException testexception;
	public String WIFI_SSID=AllQA.SSID;
	public String WIFI_PWD=AllQA.WIFIPASSWORD;	
	public String ONLINEMUSIC="httmps:://https://wynk.in";
	public String EMAIL="technologysonim@gmail.com";
	public String EMILPWD="123technologysonim@";
	public int itr=1;
	public String operator;
	public String URL1 = "www.google.com";
	public String URL2="www.amazon.com";
	public String EMERGENCYNO="112";
	@BeforeSuite
	public void beforeSuite() throws FileNotFoundException, InterruptedException, IOException, ParseException  
	{
		extent = new ExtentReports("src/test/resources/extentreport/XP8_Wifi_TestReport.html", true); //Provide Desired Report Directory Location and Name
		extent.loadConfig(new File("src/test/resources/StorageFile/ReportsConfig.xml"));
		extent.addSystemInfo("Build #",JsonFileReaderAndWriter.primaryDevFirmwareReader())
		.addSystemInfo("Product",JsonFileReaderAndWriter.primaryDevModelReader())
		.addSystemInfo("Operator", JsonFileReaderAndWriter.primaryDevOperatorReader());			
		fetch_Devices_Details();	
		operator=JsonFileReaderAndWriter.primaryDevFirmwareReader();
	}
	
	@BeforeMethod()
	public  void beforeMethod(Method method) throws InterruptedException, IOException 
	{
		test = extent.startTest( (this.getClass().getSimpleName() +" :: "+  method.getName()),method.getName()); //Test Case Start Here
		clearRecentApps();
	//	test.assignAuthor("Farheen Taj"); //Test Script Author Name

	}
	
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
	
	@AfterMethod()
	public void tearDown(ITestResult result,Method method) throws IOException, InterruptedException
	{
		
		
		if(result.getStatus()==ITestResult.FAILURE)
		{	String screenshot_path=captureScreenshot(method.getName());
		    String image= test.addScreenCapture(screenshot_path);		
		    test.log(LogStatus.FAIL,result.getThrowable());						
		}
		extent.endTest(test);
		extent.flush();
	}
	

	@BeforeTest
	public void setupSys() throws MalformedURLException, InterruptedException, FileNotFoundException, AWTException{
		properties=loadDriverAndProperties();
		 Locators_Wifi loc = new Locators_Wifi(aDriver);
		Locators_BaseUtil loc1 = new Locators_BaseUtil(aDriver);	
		PageFactory.initElements(new AppiumFieldDecorator(aDriver),loc);
		PageFactory.initElements(new AppiumFieldDecorator(aDriver),loc1);
		excel=new ExcelReader(ExcelConstants.XP5S_XL_PATH);	
		preCond_setSleepTime();
		aDriver.rotate(ScreenOrientation.PORTRAIT);System.out.println("Before test excuted");

	}
	
	//============================================================Test Case=======================================================================================

	 
	@Test(priority=1,dataProvider="XP8_Wifi", dataProviderClass=DataProviders.class)
	public void XP8_TC_01_Wifi_WrongPassword(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{  
		SoftAssert sa=new SoftAssert();
		//Giving Wrong Password and Checking the Wifi
		APP_LOGS.info("===========XP8_TC_01_Wifi_WrongPassword============");
	//	aDriver.rotate(ScreenOrientation.PORTRAIT);

		 for(int i=1;i<=itr;i++) {
		if(operator.contains("-10") || operator.contains("-11") || operator.contains("-15") || operator.contains("-26") || operator.contains("-29")) {
			launch_an_app("settings");
			clickOnNetwork_Internet();
			clickOn_Wifi_Lnk();
			turnOn_Wifi();
			remove_connectedNtwrk();
			connect_to_WiFi(WIFI_SSID,data.get("Password"));
			customWait(10000);
			validate_Password(sa);
		
		}else {																			//others
			launch_an_app("settings");
			clickOnNetwork_Internet();
			clickOn_Wifi_Lnk();
			turnOn_Wifi();
			remove_connectedNtwrk();
			connect_to_WiFi(WIFI_SSID,data.get("Password"));
			customWait(10000);
			validate_Password(sa);
		}
		 }sa.assertAll();
	}
	
	 
	@Test(priority=2,dataProvider="XP8_Wifi", dataProviderClass=DataProviders.class)
	public void XP8_TC_02_Wifi_Add_All_Network(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{  
		SoftAssert sa=new SoftAssert();
		//Adding All Type of Network in Wifi Manually
		APP_LOGS.info("===========XP8_TC_02_Wifi_Add_All_Network============");
		
		 for(int i=1;i<=itr;i++) {
	if(operator.contains("-10") || operator.contains("-11") || operator.contains("-15") || operator.contains("-26") || operator.contains("-29")) {
			launch_an_app("settings");
			clickOnNetwork_Internet();
			clickOn_Wifi_Lnk();
			turnOn_Wifi();
			clickOn_Addnetwork();
			Add_Network_security_None(data.get("SSID_Name_None"));
			clickOn_Addnetwork();
			Add_Network_security_WEP(data.get("SSID_Name_WEP"),data.get("Password_WEP"));
			clickOn_Addnetwork();
			Add_Network_security_WPA_PSK(data.get("SSID_Name_WPA"),data.get("Password_WPA"));
			clickOn_Addnetwork();
			Add_Network_security_802_1x_EAP(data.get("SSID_Name_802"),data.get("Password_802"));
			validate_AddNetwork(sa,data.get("SSID_Name_None"),data.get("SSID_Name_WEP"),data.get("SSID_Name_WPA"),data.get("SSID_Name_802"));
		}else {																	//others
			launch_an_app("settings");
			clickOnNetwork_Internet();
			clickOn_Wifi_Lnk();
			turnOn_Wifi();
			clickOn_Addnetwork();
			Add_Network_security_None(data.get("SSID_Name_None"));
			clickOn_Addnetwork();
			Add_Network_security_WEP(data.get("SSID_Name_WEP"),data.get("Password_WEP"));
			clickOn_Addnetwork();
			Add_Network_security_WPA_PSK(data.get("SSID_Name_WPA"),data.get("Password_WPA"));
			clickOn_Addnetwork();
			Add_Network_security_802_1x_EAP(data.get("SSID_Name_802"),data.get("Password_802"));
			validate_AddNetwork(sa,data.get("SSID_Name_None"),data.get("SSID_Name_WEP"),data.get("SSID_Name_WPA"),data.get("SSID_Name_802"));
		}	
		}sa.assertAll();
	}
	
	 
	@Test(priority=3,dataProvider="XP8_Wifi", dataProviderClass=DataProviders.class)
	public void XP8_TC_03_Wifi_ShowPassword(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{  
		SoftAssert sa=new SoftAssert();
		//Verifying Show Password
		APP_LOGS.info("===========XP8_TC_03_Wifi_ShowPassword============");
		
		 for(int i=1;i<=itr;i++) { 
		if(operator.contains("-10") || operator.contains("-11") || operator.contains("-15") || operator.contains("-26") || operator.contains("-29")) {
				launch_an_app("settings");
				clickOnNetwork_Internet();
				clickOn_Wifi_Lnk();
				turnOn_Wifi();
				remove_connectedNtwrk();
				validate_HidePassword(sa,WIFI_SSID,WIFI_PWD);
				validate_ShowPassword(sa,WIFI_PWD);	
		 }else {																		//others
				launch_an_app("settings");
				clickOnNetwork_Internet();
				clickOn_Wifi_Lnk();
				turnOn_Wifi();
				remove_connectedNtwrk();
				validate_HidePassword(sa,WIFI_SSID,WIFI_PWD);
				validate_ShowPassword(sa,WIFI_PWD); 
		 }
		 }sa.assertAll();
		
	}
	
	 
	@Test(priority=4,dataProvider="XP8_Wifi", dataProviderClass=DataProviders.class)
	public void XP8_TC_04_Wifi_Enable_FlightMode_ConnectWifi(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{  
		SoftAssert sa=new SoftAssert();
		//Connecting Wifi in Flight Mode
		APP_LOGS.info("===========XP8_TC_04_Wifi_Enable_FlightMode_ConnectWifi============");
		 for(int i=1;i<=itr;i++) {
	if(operator.contains("-10") || operator.contains("-11") || operator.contains("-15") || operator.contains("-26") || operator.contains("-29")) {
			launch_an_app("settings");
			clickOnNetwork_Internet();
			turnOn_AirplaneMode(); //to enable flight mode
			clickOn_Wifi_Lnk();
			turnOn_Wifi();
			remove_connectedNtwrk();
			connect_to_WiFi(WIFI_SSID,WIFI_PWD);
			verify_WifiConnected_Arplnmode_ON(sa);
			clickOn_BackBtn();
			turnOff_AirplaneMode(); // to disable flight mode
			clickOn_Wifi_Lnk();
			turnOn_Wifi();customWait(1000);
			verify_WifiConnected_Arplnmode_Off(sa);
		
		}else {																	//	others
			launch_an_app("settings");
			clickOnNetwork_Internet();
			turnOn_AirplaneMode(); //to enable flight mode
			clickOn_Wifi_Lnk();
			turnOn_Wifi();
			remove_connectedNtwrk();
			connect_to_WiFi(WIFI_SSID,WIFI_PWD);
			verify_WifiConnected_Arplnmode_ON(sa);
			clickOn_BackBtn();
			turnOff_AirplaneMode(); // to disable flight mode
			clickOn_Wifi_Lnk();
			turnOn_Wifi();customWait(1000);
			verify_WifiConnected_Arplnmode_Off(sa);
			
	}}
		sa.assertAll();
	}
	
	 
	@Test(priority=5,dataProvider="XP8_Wifi", dataProviderClass=DataProviders.class)
	public void XP8_TC_05_Wifi_AddSameSSid_Open(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{  
		SoftAssert sa=new SoftAssert();
		//Adding Same SSID Name For Open Network 
		APP_LOGS.info("===========XP8_TC_05_Wifi_AddSameSSid_Open============");
		
		 for(int i=1;i<=itr;i++) {
		if(operator.contains("-10") || operator.contains("-11") || operator.contains("-15") || operator.contains("-26") || operator.contains("-29")) {
				launch_an_app("settings");
				clickOnNetwork_Internet();
				clickOn_Wifi_Lnk();
				turnOn_Wifi();
				clickOn_Addnetwork();
				Add_Network_security_None(data.get("SSID_Name_None"));
				validate_NetworkIsSaved(sa,data.get("SSID_Name_None"),"Open Wifi");
				clickOn_Addnetwork();
				Add_Network_security_None(data.get("SSID_Name_None"));
				validate_sameNetworkIsSaved(sa,data.get("SSID_Name_None"));	
		 }else {																				//others
				launch_an_app("settings");
				clickOnNetwork_Internet();
				clickOn_Wifi_Lnk();
				turnOn_Wifi();
				clickOn_Addnetwork();
				Add_Network_security_None(data.get("SSID_Name_None"));
				validate_NetworkIsSaved(sa,data.get("SSID_Name_None"),"Open Wifi");
				clickOn_Addnetwork();
				Add_Network_security_None(data.get("SSID_Name_None"));
				validate_sameNetworkIsSaved(sa,data.get("SSID_Name_None"));
		 }
		 }
		sa.assertAll();
		 
	}
	
	 
	@Test(priority=6,dataProvider="XP8_Wifi", dataProviderClass=DataProviders.class)
	public void XP8_TC_06_Wifi_AddSameSSid_WEP(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{  
		SoftAssert sa=new SoftAssert();
		//Adding Same SSID Name For WEP  Network 
		APP_LOGS.info("===========XP8_TC_06_Wifi_AddSameSSid_WEP============");
		
		 for(int i=1;i<=itr;i++) {
	if(operator.contains("-10") || operator.contains("-11") || operator.contains("-15") || operator.contains("-26") || operator.contains("-29")) {
			launch_an_app("settings");
			clickOnNetwork_Internet();
			clickOn_Wifi_Lnk();
			turnOn_Wifi();
			clickOn_Addnetwork();
			Add_Network_security_WEP(data.get("SSID_Name_WEP"),data.get("Password"));
			validate_NetworkIsSaved(sa,data.get("SSID_Name_WEP"),"WEP secured Wifi");
			clickOn_Addnetwork();
			Add_Network_security_WEP(data.get("SSID_Name_WEP"),data.get("Password"));
			validate_sameNetworkIsSaved(sa,data.get("SSID_Name_WEP"));
		}else {																	//others
			launch_an_app("settings");
			clickOnNetwork_Internet();
			clickOn_Wifi_Lnk();
			turnOn_Wifi();
			clickOn_Addnetwork();
			Add_Network_security_WEP(data.get("SSID_Name_WEP"),data.get("Password"));
			validate_NetworkIsSaved(sa,data.get("SSID_Name_WEP"),"WEP secured Wifi");
			clickOn_Addnetwork();
			Add_Network_security_WEP(data.get("SSID_Name_WEP"),data.get("Password"));
			validate_sameNetworkIsSaved(sa,data.get("SSID_Name_WEP"));
		 }
		 }
		sa.assertAll();
		 
	}
	
	 
	@Test(priority=7,dataProvider="XP8_Wifi", dataProviderClass=DataProviders.class)
	public void XP8_TC_07_Wifi_ConnectWifi_From_QuickSetting(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{  
		SoftAssert sa=new SoftAssert();
		//Turning on Wifi using quick setting 
		APP_LOGS.info("===========XP8_TC_07_Wifi_ConnectWifi_From_QuickSetting============");
		 for(int i=1;i<=itr;i++) {
	if(operator.contains("-10") || operator.contains("-11") || operator.contains("-15") || operator.contains("-26") || operator.contains("-29")) {
			getNotificationWindow();
			clickOn_QuickWifiSetting();
			remove_ConnectedWifi_From_QuickSetting();
			connect_to_WiFi(WIFI_SSID,WIFI_PWD);
			getNotificationWindow();
			clickOn_QuickWifiSetting();
			verify_wifi_IsTurnedOn(sa);
			clickOn_WifiMoreSettings();
			verify_WifiScanningDevice(sa);
			verify_SignalLevel_IsDisplayed(sa);
			verify_SecurityType_IsDisplayed(sa);
			//verify_quickWifiConnected(sa);
		
		}else  {																//others
			getNotificationWindow();
			clickOn_QuickWifiSetting();
			remove_ConnectedWifi_From_QuickSetting();
			connect_to_WiFi(WIFI_SSID,WIFI_PWD);
			getNotificationWindow();
			clickOn_QuickWifiSetting();
			verify_wifi_IsTurnedOn(sa);
			clickOn_WifiMoreSettings();
			verify_WifiScanningDevice(sa);
			verify_SignalLevel_IsDisplayed(sa);
			verify_SecurityType_IsDisplayed(sa);
			//verify_quickWifiConnected(sa);
		 }
		 }
		sa.assertAll();
	}
	 
	@Test(priority=8,dataProvider="XP8_Wifi", dataProviderClass=DataProviders.class)
	public void XP8_TC_08_Wifi_20charPassIn_WPA(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{  
		SoftAssert sa=new SoftAssert();
		//Adding more than 20 character in WPA/WPA2 PSK
		APP_LOGS.info("===========XP8_TC_08_Wifi_20charPassIn_WPA============");
		 for(int i=1;i<=itr;i++) {
		if(operator.contains("-10") || operator.contains("-11") || operator.contains("-15") || operator.contains("-26") || operator.contains("-29")) {
			launch_an_app("settings");
			clickOnNetwork_Internet();
			clickOn_Wifi_Lnk();
			turnOn_Wifi();
			clickOn_Addnetwork();
			Add_Network_security_WPA_PSK(data.get("SSID_Name_WPA"),data.get("Password_WPA"));
			validate_NetworkIsSvdPassWith20Char(sa,data.get("SSID_Name_WPA"));
		
		}else  {																					//others
			launch_an_app("settings");
			clickOnNetwork_Internet();
			clickOn_Wifi_Lnk();
			turnOn_Wifi();
			clickOn_Addnetwork();
			Add_Network_security_WPA_PSK(data.get("SSID_Name_WPA"),data.get("Password_WPA"));
			validate_NetworkIsSvdPassWith20Char(sa,data.get("SSID_Name_WPA"));
		
		 }
		 }
		sa.assertAll();
		
	}
	
	 
	@Test(priority=9,dataProvider="XP8_Wifi", dataProviderClass=DataProviders.class)
	public void XP8_TC_09_Wifi_Scanning_IsPresent_InLocation(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{  
		SoftAssert sa=new SoftAssert();
		//Verify the scanning option is present under Location setting menu.
		APP_LOGS.info("===========XP8_TC_09_Wifi_Scanning_IsPresent_InLocation============");
		 for(int i=1;i<=itr;i++) {
	if(operator.contains("-10") || operator.contains("-11") || operator.contains("-15") || operator.contains("-26") || operator.contains("-29")) {
		launch_an_app("settings");
		clickOn_SecurityAndLocation();
		clickOn_Location();
		validate_ScanningOpt(sa);	
		}else  {																			//others
			launch_an_app("settings");
			clickOn_SecurityAndLocation();
			clickOn_Location();
			validate_ScanningOpt(sa);	
		 }
		 }
		sa.assertAll();
		 	
	}
	
	 
	@Test(priority=10,dataProvider="XP8_Wifi", dataProviderClass=DataProviders.class)
	public void XP8_TC_10_Wifi_Scanning_IsPresent_InWifi(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{  
		SoftAssert sa=new SoftAssert();
		//Verify  "Scanning Settings" option is present in Wi-Fi Settings window.
		APP_LOGS.info("===========XP8_TC_10_Wifi_Scanning_IsPresent_InWifi============");
		 for(int i=1;i<=itr;i++) {
	if(operator.contains("-10") || operator.contains("-11") || operator.contains("-15") || operator.contains("-26") || operator.contains("-29")) {
			launch_an_app("settings");
			clickOnNetwork_Internet();
			clickOn_Wifi_Lnk();
			turnOff_Wifi();
			tapOn_WifiScanningSetting();
			validate_ScanningSetting(sa);
		}else  {																	//others
			launch_an_app("settings");
			clickOnNetwork_Internet();
			clickOn_Wifi_Lnk();
			turnOff_Wifi();
			tapOn_WifiScanningSetting();
			validate_ScanningSetting(sa);

		 }
		 }
		sa.assertAll();
		 
	}

	 
	@Test(priority=11,dataProvider="XP8_Wifi", dataProviderClass=DataProviders.class)
	public void XP8_TC_11_Wifi_Forget_Cancel_ArePresent_InSavedNetworks(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{  
		SoftAssert sa=new SoftAssert();
		//Check Access point name with 'FORGET' and 'CANCEL' option sare displayed once user click on saved Network
		APP_LOGS.info("===========XP8_TC_11_Wifi_Forget_Cancel_ArePresent_InSavedNetworks============");
		 for(int i=1;i<=itr;i++) {
		if(operator.contains("-10") || operator.contains("-11") || operator.contains("-15") || operator.contains("-26") || operator.contains("-29")) {
			launch_an_app("settings");
			clickOnNetwork_Internet();
			clickOn_Wifi_Lnk();
			turnOn_Wifi();
			clickOn_Addnetwork();
			Add_Network_security_None(data.get("SSID_Name_None"));
			validate_NetworkIsSaved(sa,data.get("SSID_Name_None"),"");
			clickOn_Savednetwork();
			select_SSID(data.get("SSID_Name_None"));
			verify_FORGET_CANCEL_Displayed(sa);
	
		}else  {																	//others
			launch_an_app("settings");
			clickOnNetwork_Internet();
			clickOn_Wifi_Lnk();
			turnOn_Wifi();
			clickOn_Addnetwork();
			Add_Network_security_None(data.get("SSID_Name_None"));
			validate_NetworkIsSaved(sa,data.get("SSID_Name_None"),"");
			clickOn_Savednetwork();
			select_SSID(data.get("SSID_Name_None"));
			verify_FORGET_CANCEL_Displayed(sa);
		 }
		 }
		 sa.assertAll();
	}
	
	 
	@Test(priority=12,dataProvider="XP8_Wifi", dataProviderClass=DataProviders.class)
	public void XP8_TC_12_Wifi_Verify_Forget_Cancel_Functionality(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{  
		SoftAssert sa=new SoftAssert();
		//Functionality of 'FORGET' and 'CANCEL' option in Saved networks menu.
		APP_LOGS.info("===========XP8_TC_12_Wifi_Verify_Forget_Cancel_Functionality============");
		

		String SSIDNameNone= data.get("SSID_Name_None");
		 for(int i=1;i<=itr;i++) {
	if(operator.contains("-10") || operator.contains("-11") || operator.contains("-15") || operator.contains("-26") || operator.contains("-29")) {
			launch_an_app("settings");
			clickOnNetwork_Internet();
			clickOn_Wifi_Lnk();
			turnOn_Wifi();
			if(clickOn_Savednetwork()==true) {
				remove_OldSSID(SSIDNameNone);
			}
		    clickOn_Addnetwork();
			Add_Network_security_None(SSIDNameNone);
			validate_NetworkIsSaved(sa,SSIDNameNone,"");
			clickOn_Savednetwork();
			select_SSID(SSIDNameNone);
			validate_CnclBtn(sa,SSIDNameNone);
			select_SSID(SSIDNameNone);
			validate_FrgtBtn(sa,SSIDNameNone);
		}else  {																		//others
			launch_an_app("settings");
			clickOnNetwork_Internet();
			clickOn_Wifi_Lnk();
			turnOn_Wifi();
			if(clickOn_Savednetwork()==true) {
				remove_OldSSID(SSIDNameNone);
			}
		    clickOn_Addnetwork();
			Add_Network_security_None(SSIDNameNone);
			validate_NetworkIsSaved(sa,SSIDNameNone,"");
			clickOn_Savednetwork();
			select_SSID(SSIDNameNone);
			validate_CnclBtn(sa,SSIDNameNone);
			select_SSID(SSIDNameNone);
			validate_FrgtBtn(sa,SSIDNameNone);
			}
		 }
		sa.assertAll();
		 
	}
	@Test(priority=13,dataProvider="XP8_Wifi", dataProviderClass=DataProviders.class)
	public void XP8_TC_13_Wifi_Verify_MAC_Address_IsPresent(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{  
		SoftAssert sa=new SoftAssert();
		//MAC address is displayed under wi-fi setting.
		APP_LOGS.info("===========XP8_TC_13_Wifi_Verify_MAC_Address_IsPresent============");
		
		 for(int i=1;i<=itr;i++) {
	if(operator.contains("-10") || operator.contains("-11") || operator.contains("-15") || operator.contains("-26") || operator.contains("-29")) {
			launch_an_app("settings");
			clickOnNetwork_Internet();
			clickOn_Wifi_Lnk();
			turnOn_Wifi();
			clickOn_WifiPreferences();
			clickOn_Advanced();
			verify_MACAddress_IsPresent(sa);
	  }else  {																				//others
			launch_an_app("settings");
			clickOnNetwork_Internet();
			clickOn_Wifi_Lnk();
			turnOn_Wifi();
			clickOn_WifiPreferences();
			clickOn_Advanced();
			verify_MACAddress_IsPresent(sa);
		 }}
		sa.assertAll();
	}
	
	 
	@Test(priority=14,dataProvider="XP8_Wifi", dataProviderClass=DataProviders.class)
	public void XP8_TC_14_Wifi_Verify_IPV4_IPV6_Address_AreDisplayed(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{  
		SoftAssert sa=new SoftAssert();
		//Check IPV4 and IPV6 address are displayed under wi-fi setting.
		APP_LOGS.info("===========XP8_TC_14_Wifi_Verify_IPV4_IPV6_Address_AreDisplayed============");
		
		 for(int i=1;i<=itr;i++) {
	if(operator.contains("-10") || operator.contains("-11") || operator.contains("-15") || operator.contains("-26") || operator.contains("-29")) {
		launch_an_app("settings");
		clickOnNetwork_Internet();
		clickOn_Wifi_Lnk();
		turnOn_Wifi();
		remove_connectedNtwrk();
		connect_to_WiFi(WIFI_SSID,WIFI_PWD);
		clickOn_WifiPreferences();
		clickOn_Advanced();
		validate_IP_Address(sa);	
	}else {															//others
		launch_an_app("settings");
		clickOnNetwork_Internet();
		clickOn_Wifi_Lnk();
		turnOn_Wifi();
		remove_connectedNtwrk();
		connect_to_WiFi(WIFI_SSID,WIFI_PWD);
		clickOn_WifiPreferences();
		clickOn_Advanced();
		validate_IP_Address(sa);
		 }}
		sa.assertAll();
	}
	
	 
	@Test(priority=15,dataProvider="XP8_Wifi", dataProviderClass=DataProviders.class)
	public void XP8_TC_15_Wifi_DisableWifi_Verify_IPV4_IPV6_Address_AreDisplayed(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{  
		SoftAssert sa=new SoftAssert();
		//Check IPV4 and IPV6 address are displayed under wi-fi setting when wifi is turned Off
		APP_LOGS.info("===========XP8_TC_15_Wifi_DisableWifi_Verify_IPV4_IPV6_Address_AreDisplayed============");
		 for(int i=1;i<=itr;i++) {
				if(operator.contains("-10") || operator.contains("-11") || operator.contains("-15") || operator.contains("-26") || operator.contains("-29")) {
			launch_an_app("settings");
			clickOnNetwork_Internet();
			clickOn_Wifi_Lnk();
			turnOff_Wifi();
			clickOn_WifiPreferences();
			clickOn_Advanced();
			validate_IP_Address(sa);	
		}else  {																					//others
			launch_an_app("settings");
			clickOnNetwork_Internet();
			clickOn_Wifi_Lnk();
			turnOff_Wifi();
			clickOn_WifiPreferences();
			clickOn_Advanced();
			validate_IP_Address(sa);
		 }}
		sa.assertAll();
	}
	
	 
	@Test(priority=16,dataProvider="XP8_Wifi", dataProviderClass=DataProviders.class)
	public void XP8_TC_16_Wifi_Install_Certificate(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{  
		SoftAssert sa=new SoftAssert();
		//Checking certificates option is working fine under wi-fi advanced menu.
		APP_LOGS.info("===========XP8_TC_16_Wifi_Install_Certificate============");
		
		 for(int i=1;i<=itr;i++) {
				if(operator.contains("-10") || operator.contains("-11") || operator.contains("-15") || operator.contains("-26") || operator.contains("-29")) {
				launch_an_app("settings");
				clickOnNetwork_Internet();
				clickOn_Wifi_Lnk();
				clickOn_WifiPreferences();
				clickOn_Advanced();
				clickOn_InstallCerficate();
				verify_InstallCertificate(sa);
		 }else  {																	//others
				launch_an_app("settings");
				clickOnNetwork_Internet();
				clickOn_Wifi_Lnk();
				clickOn_WifiPreferences();
				clickOn_Advanced();
				clickOn_InstallCerficate();
				verify_InstallCertificate(sa);
				
		 }}
		sa.assertAll();
	}
	
	 
	@Test(priority=17,dataProvider="XP8_Wifi", dataProviderClass=DataProviders.class)
	public void XP8_TC_17_Wifi_LongPressOn_WifiSSID(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{  
		SoftAssert sa=new SoftAssert();
		//Connect to network option while user long tap on any of the SSID name.
		APP_LOGS.info("===========XP8_TC_17_Wifi_LongPressOn_WifiSSID============");
		
		 for(int i=1;i<=itr;i++) {
	if(operator.contains("-10") || operator.contains("-11") || operator.contains("-15") || operator.contains("-26") || operator.contains("-29")) {
			launch_an_app("settings");
			clickOnNetwork_Internet();
			clickOn_Wifi_Lnk();
			turnOn_Wifi();
			remove_connectedNtwrk();
			longPress_Ssid(WIFI_SSID);
			validate_ConnectToNetwork(sa);
	//		clickOn_ConnectToNetwork();
		}else  {																		//others
			launch_an_app("settings");
			clickOnNetwork_Internet();
			clickOn_Wifi_Lnk();
			turnOn_Wifi();
			remove_connectedNtwrk();
			longPress_Ssid(WIFI_SSID);
			validate_ConnectToNetwork(sa);
	//		clickOn_ConnectToNetwork();
		 }}
		sa.assertAll();
		}
	@Test(priority=18,dataProvider="XP8_Wifi", dataProviderClass=DataProviders.class)
	public void XP8_TC_18_Wifi_AdvanceOption_In_PasswordScreen(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{  
		SoftAssert sa=new SoftAssert();
		//Check the advanced options in wi-fi password screen
		APP_LOGS.info("===========XP8_TC_18_Wifi_AdvanceOption_In_PasswordScreen============");
		
		 for(int i=1;i<=itr;i++) {
	if(operator.contains("-10") || operator.contains("-11") || operator.contains("-15") || operator.contains("-26") || operator.contains("-29")) {
			launch_an_app("settings");
			clickOnNetwork_Internet();
			clickOn_Wifi_Lnk();
			turnOn_Wifi();
			remove_connectedNtwrk();
			select_SSID(data.get("SSID"));
			validate_AdvOpt(sa);
			validate_userAbleToClickAdvOpt(sa);
			validate_ProxyOpt(sa);
			clickOn_BackBtn();
			validate_AlloptSelectableInProxy(sa);
			validate_IpOpt(sa);
			clickOn_BackBtn();
			validate_AlloptSelectableInIP(sa);
		}else {																//others
			launch_an_app("settings");
			clickOnNetwork_Internet();
			clickOn_Wifi_Lnk();
			turnOn_Wifi();
			remove_connectedNtwrk();
			select_SSID(data.get("SSID"));
			validate_AdvOpt(sa);
			validate_userAbleToClickAdvOpt(sa);
			validate_ProxyOpt(sa);
			clickOn_BackBtn();
			validate_AlloptSelectableInProxy(sa);
			validate_IpOpt(sa);
			clickOn_BackBtn();
			validate_AlloptSelectableInIP(sa);
			 } }
		sa.assertAll();
	}
	@Test(priority=19,dataProvider="XP8_Wifi", dataProviderClass=DataProviders.class)
	public void XP8_TC_19_Wifi_SuffleWifiIcon(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{  
		SoftAssert sa=new SoftAssert();
		//Wifi icon reshuffle in quick settings menu
		APP_LOGS.info("===========XP8_TC_19_Wifi_SuffleWifiIcon============");
		 for(int i=1;i<=itr;i++) {
		if(operator.contains("-10") || operator.contains("-11") || operator.contains("-15") || operator.contains("-26") || operator.contains("-29")) {
			getNotificationWindow();
			clickOn_Edit();
			reset_Icons();
			int bfrSuffle[]=get_WifiLocation();
			move_WifiIcon();
			int aftrSuffle[]=get_WifiLocation();
			validate_WifiIcon_Moved(bfrSuffle,aftrSuffle,sa);
			reset_Icons();
		 }else {
			getNotificationWindow();
			clickOn_Edit();
			reset_Icons();
			int bfrSuffle[]=get_WifiLocation();
			move_WifiIcon();
			int aftrSuffle[]=get_WifiLocation();
			validate_WifiIcon_Moved(bfrSuffle,aftrSuffle,sa);
			reset_Icons();
		 }
		}
		 sa.assertAll();
	}		
	@Test(priority=20,dataProvider="XP8_Wifi", dataProviderClass=DataProviders.class)
	public void XP8_TC_20_Wifi_WifisCanBeSeenInNotification(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{ 
		SoftAssert sa=new SoftAssert();
	//Make sure available wifi's can be seen from quick settings 
	APP_LOGS.info("===========XP8_TC_20_Wifi_WifisCanBeSeenInNotification============");
	 for(int i=1;i<=itr;i++) {
	if(operator.contains("-10") || operator.contains("-11") || operator.contains("-15") || operator.contains("-26") || operator.contains("-29")) {
		getNotificationWindow();
		clickOn_QuickWifiSetting();
		verify_AvailableWifiNetworksDisplayed(sa);
	 }else {
		getNotificationWindow();
		clickOn_QuickWifiSetting();
		verify_AvailableWifiNetworksDisplayed(sa);
		 }
	 }sa.assertAll();
	}
	@Test(priority=21,dataProvider="XP8_Wifi", dataProviderClass=DataProviders.class)
	public void XP8_TC_21_Wifi_DeleteConnectedNetwork(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{  
		SoftAssert sa=new SoftAssert();
		//Delete Connected Network
		APP_LOGS.info("===========XP8_TC_21_Wifi_DeleteConnectedNetwork============");
		 for(int i=1;i<=itr;i++) {
	if(operator.contains("-10") || operator.contains("-11") || operator.contains("-15") || operator.contains("-26") || operator.contains("-29")) {
		launch_an_app("settings");
		clickOnNetwork_Internet();
		clickOn_Wifi_Lnk();
		turnOn_Wifi();
		remove_connectedNtwrk();
		select_SSID(WIFI_SSID);
		validate_PassIsShown(sa);
	}else {
		launch_an_app("settings");
		clickOnNetwork_Internet();
		clickOn_Wifi_Lnk();
		turnOn_Wifi();
		remove_connectedNtwrk();
		select_SSID(WIFI_SSID);
		validate_PassIsShown(sa);
	}
	}sa.assertAll();	
	}		 
	@Test(priority=22,dataProvider="XP8_Wifi", dataProviderClass=DataProviders.class)
	public void XP8_TC_22_Wifi_ConnectInLandscape(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{  
		SoftAssert sa=new SoftAssert();
		//Connecting Wifi in Lanscape mode
		APP_LOGS.info("===========XP8_TC_22_Wifi_ConnectInLandscape============");
		 
		 for(int i=1;i<=itr;i++) {
if(operator.contains("-10") || operator.contains("-11") || operator.contains("-15") || operator.contains("-26") || operator.contains("-29")) {
		launch_an_app("settings");
		clickOnNetwork_Internet();
		clickOn_Wifi_Lnk();
		aDriver.rotate(ScreenOrientation.LANDSCAPE);
		turnOn_Wifi();
		remove_connectedNtwrk();
		connect_to_WiFi_Landscape(WIFI_SSID,WIFI_PWD);
		validate_WifiConnected(sa);
		clearRecentApps();
		//verify_quickWifiConnected(sa);		
		customWait(10000);
		aDriver.rotate(ScreenOrientation.PORTRAIT);
}else {
		launch_an_app("settings");
		clickOnNetwork_Internet();
		clickOn_Wifi_Lnk();
		aDriver.rotate(ScreenOrientation.LANDSCAPE);
		turnOn_Wifi();
		remove_connectedNtwrk();
		connect_to_WiFi_Landscape(WIFI_SSID,WIFI_PWD);
		validate_WifiConnected(sa);	
		clearRecentApps();
		//verify_quickWifiConnected(sa);		
		customWait(10000);
		aDriver.rotate(ScreenOrientation.PORTRAIT);
        }
		 }sa.assertAll();
	}
	@Test(priority=23,dataProvider="XP8_Wifi", dataProviderClass=DataProviders.class)
	public void XP8_TC_23_Wifi_Verify_IPAddress_Same_InAboutPhone_WifiPreferences(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{  
		SoftAssert sa=new SoftAssert();
		//Checking Functioanality while user long tap on any of the wi-fi SSID name.
		APP_LOGS.info("===========XP8_TC_23_Wifi_Verify_IPAddress_Same_InAboutPhone_WifiPreferences============");
		
		 for(int i=1;i<=itr;i++) {
if(operator.contains("-10") || operator.contains("-11") || operator.contains("-15") || operator.contains("-26") || operator.contains("-29")) {
		launch_an_app("settings");
		clickOnNetwork_Internet();
		clickOn_Wifi_Lnk();
		turnOn_Wifi();
		remove_connectedNtwrk();
		customWait(3000);
		connect_to_WiFi(WIFI_SSID,WIFI_PWD);
		customWait(5000);
		clickOn_WifiPreferences();
		clickOn_Advanced();
		String IpAddress_Wifi=get_WifiIpAddress();
		clickBackButton(3);
		clickOn_System();
		clickOn_Aboutphone();
		clickOn_Status();	
		scrollToText("IP address");	
		validate_IPAddress_AbtPhn(IpAddress_Wifi,sa);
} else {
		launch_an_app("settings");
		clickOnNetwork_Internet();
		clickOn_Wifi_Lnk();
		turnOn_Wifi();
		remove_connectedNtwrk();
		customWait(3000);
		connect_to_WiFi(WIFI_SSID,WIFI_PWD);
		customWait(5000);
		clickOn_WifiPreferences();
		clickOn_Advanced();
		String IpAddress_Wifi=get_WifiIpAddress();
		clickBackButton(3);
		clickOn_System();
		clickOn_Aboutphone();
		clickOn_Status();	
		scrollToText("IP address");	
		validate_IPAddress_AbtPhn(IpAddress_Wifi,sa);
        }
		 }sa.assertAll();
	}
	@Test(priority=24,dataProvider="XP8_Wifi", dataProviderClass=DataProviders.class)
	public void XP8_TC_24_Wifi_ScanningTwoOptions(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{  
		SoftAssert sa=new SoftAssert();
		//Verify  "Scanning Settings" window contains  two options.
		APP_LOGS.info("===========XP8_TC_24_Wifi_ScanningTwoOptions============");
		 for(int i=1;i<=itr;i++) {
if(operator.contains("-10") || operator.contains("-11") || operator.contains("-15") || operator.contains("-26") || operator.contains("-29")) {
		launch_an_app("settings");
		clickOnNetwork_Internet();
		clickOn_Wifi_Lnk();
		turnOff_Wifi();
		tapOn_WifiScanningSetting();
		verify_TwoOptions_InScanningSettings(sa);
}else {
		launch_an_app("settings");
		clickOnNetwork_Internet();
		clickOn_Wifi_Lnk();
		turnOff_Wifi();
		tapOn_WifiScanningSetting();
		verify_TwoOptions_InScanningSettings(sa);
}
	}sa.assertAll();
	}	 
	@Test(priority=25,dataProvider="XP8_Wifi", dataProviderClass=DataProviders.class)
	public void XP8_TC_25_Wifi_ScanningAlwaysOption_IsNotPresent(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{  
		SoftAssert sa=new SoftAssert();
		//Verify the "Scanning Always Available" option is not present under Advanced Wifi Settings.
		APP_LOGS.info("===========XP8_TC_25_Wifi_ScanningAlwaysOption_IsNotPresent============");	
		 for(int i=1;i<=itr;i++) {
	if(operator.contains("-10") || operator.contains("-11") || operator.contains("-15") || operator.contains("-26") || operator.contains("-29")) {
		launch_an_app("settings");
		clickOnNetwork_Internet();
		clickOn_Wifi_Lnk();
		clickOn_WifiPreferences();
		clickOn_Advanced();
		verify_ScanningAlwaysAvailable_IsNotPresent(sa);	
	}else {
		launch_an_app("settings");
		clickOnNetwork_Internet();
		clickOn_Wifi_Lnk();
		clickOn_WifiPreferences();
		clickOn_Advanced();
		verify_ScanningAlwaysAvailable_IsNotPresent(sa);
	}
		 }sa.assertAll();
	}
	/*@Test(priority=26,dataProvider="XP8_Wifi", dataProviderClass=DataProviders.class)
	public void XP8_TC_26_Wifi_ScanningTwoOptionsDefaultBehaviour(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{  
		SoftAssert sa=new SoftAssert();
		//Ensure that  by default the Wi-Fi & Bluetooth scanning options should be is enabled.
		//Pre condition factory reset should be done before 
		APP_LOGS.info("===========XP8_TC_26_Wifi_ScanningTwoOptionsDefaultBehaviour============");
		 for(int i=1;i<=itr;i++) {
	if(operator.contains("-10") || operator.contains("-11") || operator.contains("-15") || operator.contains("-26") || operator.contains("-29")) {
		launch_an_app("settings");
		clickOnNetwork_Internet();
		ResetWifi_Mob_BT_stng();
		launch_an_app("settings");
		clickOnNetwork_Internet();
		clickOn_Wifi_Lnk();
		turnOff_Wifi();
		tapOn_WifiScanningSetting();
		validate_Wifi_BT_Enabled_ByDefault(sa);
	}else{
		launch_an_app("settings");
		clickOnNetwork_Internet();
		ResetWifi_Mob_BT_stng();
		launch_an_app("settings");
		clickOnNetwork_Internet();
		clickOn_Wifi_Lnk();
		turnOff_Wifi();
		tapOn_WifiScanningSetting();
		validate_Wifi_BT_Enabled_ByDefault(sa);
		}
	}
		sa.assertAll();
	}*/
	@Test(priority=27,dataProvider="XP8_Wifi", dataProviderClass=DataProviders.class)
	public void XP8_TC_27_Wifi_IPAndMacNotEditable(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{  
		SoftAssert sa=new SoftAssert();
		//Check IPV4 and IPV6 address are displayed under wi-fi setting.
		APP_LOGS.info("===========XP8_TC_27_Wifi_IPAndMacNotEditable============");
		
		 for(int i=1;i<=itr;i++) {
	if(operator.contains("-10") || operator.contains("-11") || operator.contains("-15") || operator.contains("-26") || operator.contains("-29")) {
			launch_an_app("settings");
			clickOnNetwork_Internet();
			clickOn_Wifi_Lnk();
			turnOn_Wifi();customWait(2000);
			remove_connectedNtwrk();customWait(2000);
			connect_to_WiFi(WIFI_SSID,WIFI_PWD);customWait(2000);
			clickOn_WifiPreferences();
			clickOn_Advanced();
			verify_IP_MAC_NotEditable(sa);
		}else {																			//others
			launch_an_app("settings");
			clickOnNetwork_Internet();
			clickOn_Wifi_Lnk();
			turnOn_Wifi();
			remove_connectedNtwrk();
			connect_to_WiFi(WIFI_SSID,WIFI_PWD);
			clickOn_WifiPreferences();
			clickOn_Advanced();
			verify_IP_MAC_NotEditable(sa);
		 } }
		sa.assertAll();
	}
	@Test(priority=28,dataProvider="XP8_Wifi", dataProviderClass=DataProviders.class)
	public void XP8_TC_28_Wifi_ScanningTwoOptionsFunctionality(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{  
		SoftAssert sa=new SoftAssert();
		//Ensure that  by default the Wi-Fi & Bluetooth scanning options should be is enabled.
		APP_LOGS.info("===========XP8_TC_28_Wifi_ScanningTwoOptionsFunctionality============");
		 for(int i=1;i<=itr;i++) {
	if(operator.contains("-10") || operator.contains("-11") || operator.contains("-15") || operator.contains("-26") || operator.contains("-29")) {
			launch_an_app("settings");
			clickOnNetwork_Internet();
			clickOn_Wifi_Lnk();
			turnOff_Wifi();
			tapOn_WifiScanningSetting();
			validate_ToggleScanningBtn(sa);	
		}else  {															//others
			launch_an_app("settings");
			clickOnNetwork_Internet();
			clickOn_Wifi_Lnk();
			turnOff_Wifi();
			tapOn_WifiScanningSetting();
			validate_ToggleScanningBtn(sa);	
		 }}
		sa.assertAll();
	}
	@Test(priority=29,dataProvider="XP8_Wifi", dataProviderClass=DataProviders.class)
	public void XP8_TC_29_Wifi_Enable_WifiScanning_InLocation_Verify_InSetting(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{  
		SoftAssert sa=new SoftAssert();
		//Verify the scanning option is present under Location setting menu.
		APP_LOGS.info("===========XP8_TC_29_Wifi_Enable_WifiScanning_InLocation_Verify_InSetting============");
		 for(int i=1;i<=itr;i++) {
		if(operator.contains("-10") || operator.contains("-11") || operator.contains("-15") || operator.contains("-26") || operator.contains("-29")) {
			launch_an_app("settings");
			clickOn_SecurityAndLocation();
			clickOn_Location();
			clickOn_Scanning();
			String result=enable_WiFiScanning();
			launch_an_app("settings");
			clickOnNetwork_Internet();
			clickOn_Wifi_Lnk();
			turnOff_Wifi();
			tapOn_WifiScanningSetting();
			verify_WifiScanning_IsEnabled_Wifi(sa,result);
		}else {																				//others
			launch_an_app("settings");
			clickOn_SecurityAndLocation();
			clickOn_Location();
			clickOn_Scanning();
			String result=enable_WiFiScanning();
			launch_an_app("settings");
			clickOnNetwork_Internet();
			clickOn_Wifi_Lnk();
			turnOff_Wifi();
			tapOn_WifiScanningSetting();
			verify_WifiScanning_IsEnabled_Wifi(sa,result);
		 }}
		sa.assertAll();
	}
	@Test(priority=30,dataProvider="XP8_Wifi", dataProviderClass=DataProviders.class)
	public void XP8_TC_30_Wifi_Disable_WifiScanning_InSetting_Verify_InLocation(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{  
		SoftAssert sa=new SoftAssert();
		//Verify Wi-Fi Scanning setting changes are getting updated properly in location settings .
		APP_LOGS.info("===========XP8_TC_30_Wifi_Disable_WifiScanning_InScanning_Verify_InLocation============");
		
		for(int i=1;i<=itr;i++) {	
		if(operator.contains("-10") || operator.contains("-11") || operator.contains("-15") || operator.contains("-26") || operator.contains("-29")) {
			launch_an_app("settings");
			clickOnNetwork_Internet();
			clickOn_Wifi_Lnk();
			turnOff_Wifi();
			tapOn_WifiScanningSetting();
			String result = disable_WiFiScanning();
			launch_an_app("settings");
			clickOn_SecurityAndLocation();
			clickOn_Location();
			clickOn_Scanning();
			verify_WifiScanning_IsDisabled_InLocation(sa,result);		
		 }else {										//others
			launch_an_app("settings");
			clickOnNetwork_Internet();
			clickOn_Wifi_Lnk();
			turnOff_Wifi();
			tapOn_WifiScanningSetting();
			String result = disable_WiFiScanning();
			launch_an_app("settings");
			clickOn_SecurityAndLocation();
			clickOn_Location();
			clickOn_Scanning();
			verify_WifiScanning_IsDisabled_InLocation(sa,result);		
					 }
		}sa.assertAll();
	}
	@Test(priority=31,dataProvider="XP8_Wifi", dataProviderClass=DataProviders.class)
	public void XP8_TC_31_Wifi_Enable_BTScanning_InLocation_Verify_InSettings(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{  
		SoftAssert sa=new SoftAssert();
		//Verify Bluetooth Scanning setting changes are getting updated properly Location settings.
		APP_LOGS.info("===========XP8_TC_31_Wifi_Enable_BTScanning_InLocation_Verify_InSettings============");	
		for(int i=1;i<=itr;i++) {
	if(operator.contains("-10") || operator.contains("-11") || operator.contains("-15") || operator.contains("-26") || operator.contains("-29")) {
			launch_an_app("settings");
			clickOn_SecurityAndLocation();
			clickOn_Location();
			clickOn_Scanning();
			String result=enable_BTScanning();
			launch_an_app("settings");
			clickon_ConnectedDevices();
			clickon_Bluetooth();
			turnOff_Bluetooth();
			tapOn_BTScanningSetting();
			verify_BTScanning_IsEnabled_InSetting(result,sa);

		}else {																//others
			launch_an_app("settings");
			clickOn_SecurityAndLocation();
			clickOn_Location();
			clickOn_Scanning();
			String result=enable_BTScanning();
			launch_an_app("settings");
			clickon_ConnectedDevices();
			clickon_Bluetooth();
			turnOff_Bluetooth();
			tapOn_BTScanningSetting();
			verify_BTScanning_IsEnabled_InSetting(result,sa);
		}
		}sa.assertAll();
	}
	@Test(priority=32,dataProvider="XP8_Wifi", dataProviderClass=DataProviders.class)
	public void XP8_TC_32_Wifi_DialEmergencyCall_VerifyWifi_ISNotDisabled(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{  
		SoftAssert sa=new SoftAssert();
		APP_LOGS.info("===========XP8_TC_32_Wifi_DialEmergencyCall_VerifyWifi_ISNotDisabled============");
		// Ensure that Wifi is not dsiabled when emergency number is dialled.

		 for(int i=1;i<=itr;i++) {
	if(operator.contains("-10") || operator.contains("-11") || operator.contains("-15") || operator.contains("-26") || operator.contains("-29")) {
			launch_an_app("settings");
			clickOnNetwork_Internet();
			clickOn_Wifi_Lnk();
			turnOn_Wifi();
			remove_connectedNtwrk();
			connect_to_WiFi(WIFI_SSID,WIFI_PWD);
			launch_an_app("phone");
			makeCall(EMERGENCYNO);
			getNotificationWindow();
			verify_WifiIsNotDisabled_WhileDiallingEmergency(sa);
	
	 }else {																			//others
			launch_an_app("settings");
			clickOnNetwork_Internet();
			clickOn_Wifi_Lnk();
			turnOn_Wifi();
			remove_connectedNtwrk();
			connect_to_WiFi(WIFI_SSID,WIFI_PWD);
			launch_an_app("phone");
			makeCall(EMERGENCYNO);
			getNotificationWindow();
			verify_WifiIsNotDisabled_WhileDiallingEmergency(sa);
		 }
		 } sa.assertAll();
	}
	@Test(priority=33,dataProvider="XP8_Wifi", dataProviderClass=DataProviders.class)
	public void XP8_TC_33_Hotspot_SetUp_WifiHotspot_Open(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{  
		SoftAssert sa=new SoftAssert();
		//setup Wifi hotspot Open
		APP_LOGS.info("===========XP8_TC_33_Hotspot_SetUp_WifiHotspot_Open============");
		 for(int i=1;i<=itr;i++) {
	if(operator.contains("-10") || operator.contains("-11") || operator.contains("-15") || operator.contains("-26") || operator.contains("-29")) {
			launch_an_app("settings");
			clickOnNetwork_Internet();
			clickOn_HotspotLnk();
			clickOn_setUpWiFiHotspot();
			setup_WiFiHotspot_Open(data.get("SSID"));
			validate_WiFiHotspot_setupOpen(data.get("SSID"),sa);
	 }else {
			launch_an_app("settings");
			clickOnNetwork_Internet();
			clickOn_HotspotLnk();
			clickOn_setUpWiFiHotspot();
			setup_WiFiHotspot_Open(data.get("SSID"));
			validate_WiFiHotspot_setupOpen(data.get("SSID"),sa);
	 }
		 }sa.assertAll();
	}
	@Test(priority=34,dataProvider="XP8_Wifi", dataProviderClass=DataProviders.class)
	public void XP8_TC_34_Hotspot_SetUp_WifiHotspot_Secure(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{  
		SoftAssert sa=new SoftAssert();
		//setup Secure Wifi hotspot
		APP_LOGS.info("===========XP8_TC_34_Hotspot_SetUp_WifiHotspot_Secure============");
		 for(int i=1;i<=itr;i++) {
	if(operator.contains("-10") || operator.contains("-11") || operator.contains("-15") || operator.contains("-26") || operator.contains("-29")) {
		launch_an_app("settings");
		clickOnNetwork_Internet();
		clickOn_HotspotLnk();
		clickOn_setUpWiFiHotspot();
		setup_WiFiHotspot_Secure(data.get("SSID"),data.get("Password"));
		validate_WiFiHotspot_setupSecure(data.get("SSID"),sa);
	}else {
		launch_an_app("settings");
		clickOnNetwork_Internet();
		clickOn_HotspotLnk();
		clickOn_setUpWiFiHotspot();
		setup_WiFiHotspot_Secure(data.get("SSID"),data.get("Password"));
		validate_WiFiHotspot_setupSecure(data.get("SSID"),sa);
	}
		 }sa.assertAll();
	}
	@Test(priority=35,dataProvider="XP8_Wifi", dataProviderClass=DataProviders.class)
	public void XP8_TC_35_Wifi_TurnOnMobileData_UseWifi(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{  
		SoftAssert sa=new SoftAssert();
		//
		APP_LOGS.info("===========XP8_TC_35_Wifi_TurnOnMobileData_UseWifi============");
		 for(int i=1;i<=itr;i++) {

if(operator.contains("-10") || operator.contains("-11") || operator.contains("-15") || operator.contains("-26") || operator.contains("-29")) {
		launch_an_app("settings");
		clickOnNetwork_Internet();
		clickOn_MobileNtwrk();
		turnOn_MobileNtwrk();
		clickBackButton(1);
		clickOn_DataUsage();
		String str = Locators_Wifi.dataUsageDetails.getText();
		int beforeWifi = get_MobileDataUsage();
		launch_an_app("settings");
		clickOnNetwork_Internet();
		clickOn_Wifi_Lnk();
		turnOn_Wifi();
		remove_connectedNtwrk();
		connect_to_WiFi(WIFI_SSID, WIFI_PWD);
		clearRecentApps();
		launchYoutube();
		clickOn_Search();
		search_Video("sonim xp8");
		play_Video();
		wait_TenMinutes(str);
		launch_an_app("settings");
		clickOnNetwork_Internet();
		clickOn_DataUsage();
		int afterWifi = get_MobileDataUsage();
		verify_MobileData_NotUsed(beforeWifi,afterWifi,sa);
	}else {
		launch_an_app("settings");
		clickOnNetwork_Internet();
		clickOn_MobileNtwrk();
		turnOn_MobileNtwrk();
		clickBackButton(1);
		clickOn_DataUsage();
		String str = Locators_Wifi.dataUsageDetails.getText();
		int beforeWifi = get_MobileDataUsage();
		launch_an_app("settings");
		clickOnNetwork_Internet();
		clickOn_Wifi_Lnk();
		turnOn_Wifi();
		remove_connectedNtwrk();
		connect_to_WiFi(WIFI_SSID, WIFI_PWD);
		clearRecentApps();
		launchYoutube();
		clickOn_Search();
		search_Video("sonim xp8");
		play_Video();
		wait_TenMinutes(str);
		launch_an_app("settings");
		clickOnNetwork_Internet();
		clickOn_DataUsage();
		int afterWifi = get_MobileDataUsage();
		verify_MobileData_NotUsed(beforeWifi,afterWifi,sa);
	}
		}sa.assertAll();
	}	
	@Test(priority=36,dataProvider="XP8_Wifi", dataProviderClass=DataProviders.class)
	public void XP8_TC_36_Wifi_HideInternalStorage_IsNotPresent(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{  
		SoftAssert sa=new SoftAssert();
		//Make sure "Hide internal storage" option not present in wifi settings
		APP_LOGS.info("===========XP8_TC_36_Wifi_HideInternalStorage_IsNotPresent============");
		 for(int i=1;i<=itr;i++) {	
	if(operator.contains("-10") || operator.contains("-11") || operator.contains("-15") || operator.contains("-26") || operator.contains("-29")) {
		launch_an_app("settings");
		clickOnNetwork_Internet();
		clickOn_Wifi_Lnk();
		clickOn_WifiPreferences();
		clickOn_Advanced();
		clickOn_InstallCerficate();
		Validate_navigatedToStorageLocation(sa);
		verify_HideInternalStorage_IsNotPresent(sa);
	 }else {
			launch_an_app("settings");
			clickOnNetwork_Internet();
			clickOn_Wifi_Lnk();
			clickOn_WifiPreferences();
			clickOn_Advanced();
			clickOn_InstallCerficate();
			Validate_navigatedToStorageLocation(sa);
			verify_HideInternalStorage_IsNotPresent(sa);
	 }
			 }
		sa.assertAll();
	}
	@Test(priority=37,dataProvider="XP8_Wifi", dataProviderClass=DataProviders.class)
	public void XP8_TC_37_Wifi_ConnectWifi_VerifyWifi_SSIDSignalLevelSecurityType(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{  
		SoftAssert sa=new SoftAssert();
		//Verify Bluetooth Scanning setting changes are getting updated properly in Bluetooth  settings.
		APP_LOGS.info("===========XP8_TC_37_Wifi_ConnectWifi_VerifyWifi_SSIDSignalLevelSecurityType============");
		
		
		launch_an_app("settings");
		clickOnNetwork_Internet();
		clickOn_Wifi_Lnk();
		turnOn_Wifi();
		for(int i=1;i<=itr;i++) {
	if(operator.contains("-10") || operator.contains("-11") || operator.contains("-15") || operator.contains("-26") || operator.contains("-29")) {
		remove_connectedNtwrk();
		connect_to_WiFi(WIFI_SSID, WIFI_PWD);
		verify_WifiSSID_IsDisplayed(WIFI_SSID,sa);
		verify_SignalLevel_IsDisplayed(sa);
		verify_SecurityType_IsDisplayed(sa);
	}else {
		remove_connectedNtwrk();
		connect_to_WiFi(WIFI_SSID, WIFI_PWD);
		verify_WifiSSID_IsDisplayed(WIFI_SSID,sa);
		verify_SignalLevel_IsDisplayed(sa);
		verify_SecurityType_IsDisplayed(sa);
	}
		}sa.assertAll();
	
	}
	@Test(priority=38,dataProvider="XP8_Wifi", dataProviderClass=DataProviders.class)
	public void XP8_TC_38_Wifi_Verify_AutoConnect_IsPresent(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{  
		SoftAssert sa=new SoftAssert();
		//Wi-Fi auto connect option provided under Wi-Fi settings.
		APP_LOGS.info("===========XP8_TC_38_Wifi_Verify_AutoConnect_IsPresent============");
		 for(int i=1;i<=itr;i++) {
	if(operator.contains("-10") || operator.contains("-11") || operator.contains("-15") || operator.contains("-26") || operator.contains("-29")) {
		launch_an_app("settings");
		clickOnNetwork_Internet();
		clickOn_Wifi_Lnk();
		turnOff_Wifi();
		verify_AutoConnect_IsPresent(operator,sa); 
	}else {
		launch_an_app("settings");
		clickOnNetwork_Internet();
		clickOn_Wifi_Lnk();
		turnOff_Wifi();
		verify_AutoConnect_IsPresent(operator,sa);
	}
		 }sa.assertAll();
	} 
	@Test(priority=39,dataProvider="XP8_Wifi", dataProviderClass=DataProviders.class)
	public void XP8_TC_39_Wifi_Verify_LongPressOnConnectedSSID(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{  
		SoftAssert sa=new SoftAssert();
		//Verify the DUT behavior when user tapped on connected Wi-Fi SSID.
		APP_LOGS.info("===========XP8_TC_39_Wifi_Verify_LongPressOnConnectedSSID============");
		launch_an_app("settings");
		clickOnNetwork_Internet();
		clickOn_Wifi_Lnk();
		turnOn_Wifi();
		remove_connectedNtwrk();
		connect_to_WiFi(WIFI_SSID,WIFI_PWD);
		for(int i=1;i<=itr;i++) {
	if(operator.contains("-10") || operator.contains("-11") || operator.contains("-15") || operator.contains("-26") || operator.contains("-29")) {
		longPressOn_ConnectedSSID(WIFI_SSID,WIFI_PWD);
		validate_LongPressOn_ConnectedSSID(sa);
		clickOn_BackBtn();
	}else {
		longPressOn_ConnectedSSID(WIFI_SSID,WIFI_PWD);
		validate_LongPressOn_ConnectedSSID(sa);
		clickOn_BackBtn();
	}
		}sa.assertAll();
	}
	@Test(priority=40,dataProvider="XP8_Wifi", dataProviderClass=DataProviders.class)
	public void XP8_TC_40_Wifi_ConnectCharger_ConnectWifi(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{  
		SoftAssert sa=new SoftAssert();
		//Check whether user can able to connect WI-FI network when DUT is connected with charger/USB cable.
		APP_LOGS.info("===========XP8_TC_40_Wifi_ConnectCharger_ConnectWifi============");
		
		 for(int i=1;i<=itr;i++) {
	if(operator.contains("-10") || operator.contains("-11") || operator.contains("-15") || operator.contains("-26") || operator.contains("-29")) {
		launch_an_app("settings");
		clickOn_Battery();
		validate_DeviceCharging(sa);
		minWait();
		clickBackButton(1);
		clickOnNetwork_Internet();
		clickOn_Wifi_Lnk();
		turnOn_Wifi();
		remove_connectedNtwrk();
		connect_to_WiFi(WIFI_SSID,WIFI_PWD);
		validate_ableToCntWifi(sa);
	}else {
		launch_an_app("settings");
		clickOn_Battery();
		validate_DeviceCharging(sa);
		minWait();
		clickBackButton(1);
		clickOnNetwork_Internet();
		clickOn_Wifi_Lnk();
		turnOn_Wifi();
		remove_connectedNtwrk();
		connect_to_WiFi(WIFI_SSID,WIFI_PWD);
		validate_ableToCntWifi(sa);
	}
		 }sa.assertAll();
	
	}	 
	@Test(priority=41,dataProvider="XP8_Wifi", dataProviderClass=DataProviders.class)
	public void XP8_TC_41_Wifi_Check_WifiSignalLevel(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{  
		SoftAssert sa=new SoftAssert();
		//Searched devices should show the signal strength, and check the signal level on notification bar when connected
		APP_LOGS.info("===========XP8_TC_41_Wifi_Check_WifiSignalLevel============");
		launch_an_app("settings");
		clickOnNetwork_Internet();
		clickOn_Wifi_Lnk();
		turnOn_Wifi();
		remove_connectedNtwrk();
		connect_to_WiFi(WIFI_SSID,WIFI_PWD);
		
		for(int i=1;i<=itr;i++) {
	if(operator.contains("-10") || operator.contains("-11") || operator.contains("-15") || operator.contains("-26") || operator.contains("-29")) {
		getNotificationWindow();
		verify_WifiSignalLevel_IsDisplayed(sa);
	}else {
		getNotificationWindow();
		verify_WifiSignalLevel_IsDisplayed(sa);
		}
	
		}sa.assertAll();
	

	}
	@Test(priority=42,dataProvider="XP8_Wifi", dataProviderClass=DataProviders.class)
	public void XP8_TC_42_Wifi_TapOn_WifiSSID_Verify_Behaviour(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{  
		SoftAssert sa=new SoftAssert();
		//Check the behaviour of device while user tap on any of the SSID name from the list
		//password screen along with advanced options should display
		APP_LOGS.info("===========XP8_TC_42_Wifi_TapOn_WifiSSID_Verify_Behaviour============");

		 for(int i=1;i<=itr;i++) {
if(operator.contains("-10") || operator.contains("-11") || operator.contains("-15") || operator.contains("-26") || operator.contains("-29")) {
		launch_an_app("settings");
		clickOnNetwork_Internet();
		clickOn_Wifi_Lnk();
		turnOn_Wifi();
		remove_connectedNtwrk();
		select_SSID(data.get("SSID"));
		verify_PasswordScreen_IsDisplayed(sa);
		verify_AdvancedOption_IsDisplayed(sa);
	}else {
		launch_an_app("settings");
		clickOnNetwork_Internet();
		clickOn_Wifi_Lnk();
		turnOn_Wifi();
		remove_connectedNtwrk();
		select_SSID(data.get("SSID"));
		verify_PasswordScreen_IsDisplayed(sa);
		verify_AdvancedOption_IsDisplayed(sa);
	}
		 }sa.assertAll();	
	}
	@Test(priority=43,dataProvider="XP8_Wifi", dataProviderClass=DataProviders.class)
	public void XP8_TC_43_Wifi_Select_Forget_Verify_ConnectedNotification(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{  
		SoftAssert sa=new SoftAssert();
		//Ensure WIFI SSID connected notification should get cleared after selecting Forget on  WIFI AP.

		APP_LOGS.info("===========XP8_TC_43_Wifi_Select_Forget_Verify_ConnectedNotification============");
		
		 for(int i=1;i<=itr;i++) {
if(operator.contains("-10") || operator.contains("-11") || operator.contains("-15") || operator.contains("-26") || operator.contains("-29")) {
		getNotificationWindow();
		clickOn_QuickSettings();
		clickOnNetwork_Internet();
		clickOn_Wifi_Lnk();
		turnOn_Wifi();
		remove_connectedNtwrk();
		connect_to_WiFi(WIFI_SSID,WIFI_PWD);
		getNotificationWindow();
		verify_Connected_IsDisplayed(sa);
		clickOn_QuickWifiSetting();
		remove_ConnectedWifi_From_QuickSetting();
		getNotificationWindow();
		verify_Wifi_IsNotConnected(sa);
		
	}else {
		getNotificationWindow();
		clickOn_QuickSettings();
		clickOnNetwork_Internet();
		clickOn_Wifi_Lnk();
		turnOn_Wifi();
		remove_connectedNtwrk();
		connect_to_WiFi(WIFI_SSID,WIFI_PWD);
		getNotificationWindow();
		verify_Connected_IsDisplayed(sa);
		clickOn_QuickWifiSetting();
		remove_ConnectedWifi_From_QuickSetting();
		getNotificationWindow();
		verify_Wifi_IsNotConnected(sa);
		}
	}
		sa.assertAll();
	}
	
	 
	@Test(priority=44,dataProvider="XP8_Wifi", dataProviderClass=DataProviders.class)
	public void XP8_TC_44_Wifi_BehaviourOf_Install_Certificate(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{  
		SoftAssert sa=new SoftAssert();
		//Verify the DUT behavior when user is in insatll certificate screen.
		APP_LOGS.info("===========XP8_TC_44_Wifi_BehaviourOf_Install_Certificate============");
		
		 for(int i=1;i<=itr;i++) {
if(operator.contains("-10") || operator.contains("-11") || operator.contains("-15") || operator.contains("-26") || operator.contains("-29")) {
		launch_an_app("settings");
		clickOnNetwork_Internet();
		clickOn_Wifi_Lnk();
		turnOn_Wifi();
		clickOn_WifiPreferences();
		clickOn_Advanced();
		clickOn_InstallCerficate();
		clickBackButton(2);
		Validate_deviceBehaviour(sa);
	}else{
		launch_an_app("settings");
		clickOnNetwork_Internet();
		clickOn_Wifi_Lnk();
		turnOn_Wifi();
		clickOn_WifiPreferences();
		clickOn_Advanced();
		clickOn_InstallCerficate();
		clickBackButton(2);
		Validate_deviceBehaviour(sa);
	}
		 }sa.assertAll();
	}
	@Test(priority=45,dataProvider="XP8_Wifi", dataProviderClass=DataProviders.class)
	public void XP8_TC_45_Wifi_Disable_BTScanning_InSetting_Verify_InLocation(Hashtable<String, String> data) throws Exception
	{  
		SoftAssert sa=new SoftAssert();
		//Verify Bluetooth Scanning setting changes are getting updated properly Location settings.
		APP_LOGS.info("===========XP8_TC_45_Wifi_Disable_BTScanning_InSetting_Verify_InLocation============");
	
	
		 for(int i=1;i<=itr;i++) {
if(operator.contains("-10") || operator.contains("-11") || operator.contains("-15") || operator.contains("-26") || operator.contains("-29")) {
			launch_an_app("settings");
			clickon_ConnectedDevices();
			clickon_Bluetooth();
			turnOff_Bluetooth();
			tapOn_BTScanningSetting();
			String result = disable_BTScanning();
			launch_an_app("settings");
			clickOn_SecurityAndLocation();
			clickOn_Location();
			clickOn_Scanning();
			verify_BTScanning_IsDisabled_InLocation(result,sa);
	}else {
		launch_an_app("settings");
		clickon_ConnectedDevices();
		clickon_Bluetooth();
		turnOff_Bluetooth();
		tapOn_BTScanningSetting();
		String result = disable_BTScanning();
		launch_an_app("settings");
		clickOn_SecurityAndLocation();
		clickOn_Location();
		clickOn_Scanning();
		verify_BTScanning_IsDisabled_InLocation(result,sa);
	}
		 }sa.assertAll();
	}
		 
	@Test(priority=46,dataProvider="XP8_Wifi", dataProviderClass=DataProviders.class)
	public void XP8_TC_46_Wifi_EnterMoreNonNumerics_VerifyErrorMessage(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{  
		SoftAssert sa=new SoftAssert();
		//Check if proper error message is shown when non-numeric or more than 11 digits are keyed in
		APP_LOGS.info("===========XP8_TC_46_Wifi_EnterMoreNonNumerics_VerifyErrorMessage============");
		
		launch_an_app("phone");
		clickOn_dialPad();
		dial_Code(data.get("Code"));
		clickOn_wifiInformation();
		clickOn_WifiAPI();
		 for(int i=1;i<=itr;i++) {
				if(operator.contains("-10") || operator.contains("-11") || operator.contains("-15") || operator.contains("-26") || operator.contains("-29")) {
				 clickOn_enableNetwork();
				 enter_NonNumericNumbersMorethan11(data.get("Input"));
				 takeScreenShot();
				 Read_File.takeScreenShotForOcr("WifiErrormsg");
				 my_main.validate_Using_OCR("WifiErrormsg.jpeg");
				 String path=System.getProperty("user.dir")+"\\src\\test\\resources\\OCR_FILES\\ocr.txt";	
				 String content = readFile(path);
				 System.out.println("Text from screenshot = "+content);
				 verify_ErrorMsgDisplayed_WhenEnteringMoreNonNumerics(content,sa);
			 }else  {																
			 	 clickOn_enableNetwork();
				 enter_NonNumericNumbersMorethan11(data.get("Input"));
				 takeScreenShot();
				 Read_File.takeScreenShotForOcr("WifiErrormsg");
				 my_main.validate_Using_OCR("WifiErrormsg.jpeg");
				 String path=System.getProperty("user.dir")+"\\src\\test\\resources\\OCR_FILES\\ocr.txt";	
				 String content = readFile(path);
				 System.out.println("Text from screenshot = "+content);
				 verify_ErrorMsgDisplayed_WhenEnteringMoreNonNumerics(content,sa);
				 } 
		 }sa.assertAll();
	}
	@Test(priority=47,dataProvider="XP8_Wifi", dataProviderClass=DataProviders.class)
	public void XP8_TC_47_Wifi_EnableWifi_EnableDisableMobileData_Browse(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{  
		SoftAssert sa=new SoftAssert();
		//Enable and Disable Wi-Fi on the phone while downloading/Browsing
		APP_LOGS.info("===========XP8_TC_47_Wifi_EnableWifi_EnableDisableMobileData_Browse============");
		 for(int i=1;i<=itr;i++) {
if(operator.contains("-10") || operator.contains("-11") || operator.contains("-15") || operator.contains("-26") || operator.contains("-29")) {
		launch_an_app("settings");
		clickOnNetwork_Internet();
		clickOn_Wifi_Lnk();
		turnOn_Wifi();
		remove_connectedNtwrk();
		connect_to_WiFi(WIFI_SSID, WIFI_PWD);
		verify_WifiConnected_SignalLevel_AreDisplayed(sa);
		clickOn_BackBtn();	
		clickOn_DataUsage();
		clickOn_WifiDataUsage();customWait(3000);
		String wifiDataUsed=Locators_Wifi.dataUsed.getText().trim();
		String oldWifiData=extract_Numerics(wifiDataUsed);
		clickOn_BackBtn();
		clickOn_BackBtn();
		disable_AirplaneMode();
		clickOn_MobileNtwrk();
		turnOn_MobileNtwrk();clearRecentApps();
		launchYoutube();
		clickOn_Search();
		search_Video("sonim xp8");
		play_Video();
		wait_TenMinutes(wifiDataUsed);
		launchSettings_Activity();
		clickOnNetwork_Internet();
		clickOn_DataUsage();
		clickOn_WifiDataUsage();customWait(3000);
		String newWifiDataUsed=Locators_Wifi.dataUsed.getText().trim();
		String mobileDataEnabled=extract_Numerics(newWifiDataUsed);
		verify_WifiIsUsedToBrowse_MobileDataEnabled(sa,oldWifiData,mobileDataEnabled);
		launchSettings_Activity();
		clickOn_MobileNtwrk();
		turnOff_MobileNtwrk();
		clearRecentApps();
		launchYoutube();
		clickOn_Search();
		search_Video("sonim xp8");
		play_Video();
		wait_TenMinutes(newWifiDataUsed);
		launchSettings_Activity();
		clickOnNetwork_Internet();
		clickOn_DataUsage();
		clickOn_WifiDataUsage();customWait(3000);
		String newWifiData=Locators_Wifi.dataUsed.getText().trim();
		String mobileDataDisabled=extract_Numerics(newWifiData);
		verify_WifiIsUsedToBrowse_MobileDataDisabled(sa,oldWifiData,mobileDataDisabled);
	}else{
		launch_an_app("settings");
		clickOnNetwork_Internet();
		clickOn_Wifi_Lnk();
		turnOn_Wifi();
		remove_connectedNtwrk();
		connect_to_WiFi(WIFI_SSID, WIFI_PWD);
		verify_WifiConnected_SignalLevel_AreDisplayed(sa);
		clickOn_BackBtn();	
		clickOn_DataUsage();
		clickOn_WifiDataUsage();customWait(3000);
		String wifiDataUsed=Locators_Wifi.dataUsed.getText().trim();
		String oldWifiData=extract_Numerics(wifiDataUsed);
		clickOn_BackBtn();
		clickOn_BackBtn();
		disable_AirplaneMode();
		clickOn_MobileNtwrk();
		turnOn_MobileNtwrk();clearRecentApps();
		launchYoutube();
		clickOn_Search();
		search_Video("sonim xp8");
		play_Video();
		wait_TenMinutes(wifiDataUsed);
		launchSettings_Activity();
		clickOnNetwork_Internet();
		clickOn_DataUsage();
		clickOn_WifiDataUsage();customWait(3000);
		String newWifiDataUsed=Locators_Wifi.dataUsed.getText().trim();
		String mobileDataEnabled=extract_Numerics(newWifiDataUsed);
		verify_WifiIsUsedToBrowse_MobileDataEnabled(sa,oldWifiData,mobileDataEnabled);
		launchSettings_Activity();
		clickOn_MobileNtwrk();
		turnOff_MobileNtwrk();
		clearRecentApps();
		launchYoutube();
		clickOn_Search();
		search_Video("sonim xp8");
		play_Video();
		wait_TenMinutes(newWifiDataUsed);
		launchSettings_Activity();
		clickOnNetwork_Internet();
		clickOn_DataUsage();
		clickOn_WifiDataUsage();customWait(3000);
		String newWifiData=Locators_Wifi.dataUsed.getText().trim();
		String mobileDataDisabled=extract_Numerics(newWifiData);
		verify_WifiIsUsedToBrowse_MobileDataDisabled(sa,oldWifiData,mobileDataDisabled);}
	}
		sa.assertAll();
	}
	
	 
	@Test(priority=48,dataProvider="XP8_Wifi", dataProviderClass=DataProviders.class)
	public void XP8_TC_48_Wifi_SwitchOffWifi_CheckNotification(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{  
		SoftAssert sa=new SoftAssert();
		//Verify Even after switching off the wi-fi, the wi-fi is connected WiFi(SSID name) network notification is shown.
		APP_LOGS.info("===========XP8_TC_48_Wifi_SwitchOffWifi_CheckNotification============");
				launch_an_app("settings");
		clickOnNetwork_Internet();
		clickOn_Wifi_Lnk();
		 for(int i=1;i<=itr;i++) {
	if(operator.contains("-10") || operator.contains("-11") || operator.contains("-15") || operator.contains("-26") || operator.contains("-29")) {
				 
		turnOn_Wifi();
		remove_connectedNtwrk();
		connect_to_WiFi(WIFI_SSID,WIFI_PWD);
		customWait(2000);
		verify_Wifi_IsConnected(sa);
		clickOn_BackBtn();
		clickOn_Wifi_Lnk();
		turnOff_Wifi();
		customWait(2000);
		verify_Wifi_IsDisconnected(sa);
	}else{
		turnOn_Wifi();
		remove_connectedNtwrk();
		connect_to_WiFi(WIFI_SSID,WIFI_PWD);
		customWait(2000);
		verify_Wifi_IsConnected(sa);
		clickOn_BackBtn();
		clickOn_Wifi_Lnk();
		turnOff_Wifi();
		customWait(2000);
		verify_Wifi_IsDisconnected(sa);
		}
	}
		sa.assertAll();
	
	}	
	
	 
	@Test(priority=49,dataProvider="XP8_Wifi", dataProviderClass=DataProviders.class)
	public void XP8_TC_49_Wifi_Check_Addnetwork_IsPresentInWifiSetting(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{  
		SoftAssert sa=new SoftAssert();
		//Check Add Network option is present in wi-fi setting.
		APP_LOGS.info("===========XP8_TC_49_Wifi_Check_Addnetwork_IsPresentInWifiSetting============");
		 for(int i=1;i<=itr;i++) {
	if(operator.contains("-10") || operator.contains("-11") || operator.contains("-15") || operator.contains("-26") || operator.contains("-29")) {
		launch_an_app("settings");
		clickOnNetwork_Internet();
		clickOn_Wifi_Lnk();
		turnOn_Wifi();
		verify_Addnetwork_IsPresent_InWifiSetting(sa);
	}else {
		launch_an_app("settings");
		clickOnNetwork_Internet();
		clickOn_Wifi_Lnk();
		turnOn_Wifi();
		verify_Addnetwork_IsPresent_InWifiSetting(sa);
	}
		 } sa.assertAll();
		 
	}
	@Test(priority=50,dataProvider="XP8_Wifi", dataProviderClass=DataProviders.class)
	public void XP8_TC_50_Wifi_EnterWrongPassword_VerifyToastMessage(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{  
		SoftAssert sa=new SoftAssert();
		//Ensure user is getting proper toast message while wrong password is given for wi-fi.
		APP_LOGS.info("=========== XP8_TC_50_Wifi_EnterWrongPassword_VerifyToastMessage============");
		 for(int i=1;i<=itr;i++) {
				if(operator.contains("-10") || operator.contains("-11") || operator.contains("-15") || operator.contains("-26") || operator.contains("-29")) {
		launch_an_app("settings");
		clickOnNetwork_Internet();
		clickOn_Wifi_Lnk();
		turnOn_Wifi();
		remove_connectedNtwrk();
		connect_to_WiFi(data.get("SSID"), data.get("Password"));
		customWait(5000);
		takeScreenShot();
		Read_File.takeScreenShotForOcr("WifiPass");
		my_main.validate_Using_OCR("WifiPass.jpeg");
		String path=System.getProperty("user.dir")+"\\src\\test\\resources\\OCR_FILES\\ocr.txt";	
		String content = readFile(path);
		System.out.println("SCREENSHOT TEXT -> "+content);
		verify_ToastMessgeIsDisplayed_WhileEnteringWrongPassword(content,sa);
	}else {
		launch_an_app("settings");
		clickOnNetwork_Internet();
		clickOn_Wifi_Lnk();
		turnOn_Wifi();
		remove_connectedNtwrk();
		connect_to_WiFi(data.get("SSID"), data.get("Password"));
		customWait(5000);
		takeScreenShot();
		Read_File.takeScreenShotForOcr("WifiPass");
		my_main.validate_Using_OCR("WifiPass.jpeg");
		String path=System.getProperty("user.dir")+"\\src\\test\\resources\\OCR_FILES\\ocr.txt";	
		String content = readFile(path);
		System.out.println("SCREENSHOT TEXT -> "+content);
		verify_ToastMessgeIsDisplayed_WhileEnteringWrongPassword(content,sa);
	}
		 }sa.assertAll();
	}	 
	@Test(priority=51,dataProvider="XP8_Wifi", dataProviderClass=DataProviders.class)
	public void XP8_TC_51_Wifi_RenameWifiDirectMenu(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{  
		SoftAssert sa=new SoftAssert();
		//Check user is able to rename the device name in wifi Direct menu.
		APP_LOGS.info("===========XP8_TC_51_Wifi_RenameWifiDirectMenu============");
		 for(int i=1;i<=itr;i++) {
				if(operator.contains("-10") || operator.contains("-11") || operator.contains("-15") || operator.contains("-26") || operator.contains("-29")) {
		launch_an_app("settings");
		clickOnNetwork_Internet();
		clickOn_Wifi_Lnk();
		turnOn_Wifi();
		clickOn_WifiPreferences();
		clickOn_Advanced();
		clickOn_WifiDirect();
		clickOn_WifiDirect_RenameDevice();	
	    renameDevice(data.get("Rename"));
	    verify_DeviceIsRenamed(sa,data.get("Rename"));	
	    clickOn_WifiDirect_RenameDevice();
		verify_CancelButtonFunctionality(data.get("Rename"),sa);  
	}else {	 
		launch_an_app("settings");
		clickOnNetwork_Internet();
		clickOn_Wifi_Lnk();
		turnOn_Wifi();
		clickOn_WifiPreferences();
		clickOn_Advanced();
		clickOn_WifiDirect();
		clickOn_WifiDirect_RenameDevice();	
	    renameDevice(data.get("Rename"));
	    verify_DeviceIsRenamed(sa,data.get("Rename"));	
	    clickOn_WifiDirect_RenameDevice();
		verify_CancelButtonFunctionality(data.get("Rename"),sa);  
	}
		 }sa.assertAll();
	}
	 	
	 	@Test(priority=52,dataProvider="XP8_Wifi", dataProviderClass=DataProviders.class)
		public void XP8_TC_52_Wifi_ReceiveCallWhileBrowsing(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
		{  
			SoftAssert sa=new SoftAssert();
			//Receive call While brwosing and verify user can able to browse while answering a call
			APP_LOGS.info("===========XP8_TC_52_Wifi_ReceiveCallWhileBrowsing============");
			clear_ChromeHistory();
			 for(int i=1;i<=itr;i++) {
	if(operator.contains("-10") || operator.contains("-11") || operator.contains("-15") || operator.contains("-26") || operator.contains("-29")) {
			launch_an_app("settings");
			clickOnNetwork_Internet();
			clickOn_Wifi_Lnk();
			turnOn_Wifi();
			remove_connectedNtwrk();
			connect_to_WiFi(WIFI_SSID,WIFI_PWD);
			launch_an_app("browser");
			clear_ChromePermission();
			enter_URL(i, URL1);
			start_browsing();
			customWait(2000);
			make_Call_from_RefDev();
			customWait(5000);
			reciveCallInPriDevice();
			customWait(5000);
			moveCall_ToBackground();
			//start_browsing();
			verify_BrowsingIsContinuedAfterReceivingCall(sa);
			minWait();
			endCall_RefDevice();
		 }else {
				launch_an_app("settings");
				clickOnNetwork_Internet();
				clickOn_Wifi_Lnk();
				turnOn_Wifi();
				remove_connectedNtwrk();
				connect_to_WiFi(WIFI_SSID,WIFI_PWD);
				launch_an_app("browser");
				clear_ChromePermission();
				enter_URL(i, URL1);
				start_browsing();
				customWait(2000);
				make_Call_from_RefDev();
				customWait(5000);
				reciveCallInPriDevice();
				customWait(5000);
				moveCall_ToBackground();
			//	start_browsing();
				verify_BrowsingIsContinuedAfterReceivingCall(sa);
				minWait();
				endCall_RefDevice();
		 }
			 }sa.assertAll();
		}
/*	@Ignore
	@Test(priority=53,dataProvider="XP8_Wifi", dataProviderClass=DataProviders.class)
	public void XP8_TC_53_Wifi_UseWifi_ToSetup_Gmail(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{  
		SoftAssert sa=new SoftAssert();
		//Setup Email account using Wi-Fi
		APP_LOGS.info("===========XP8_TC_55_Wifi_UseWifi_ToSetup_Gmail============");
		
		 
		 for(int i=1;i<=itr;i++) {
			 if(operator.contains("-10")) {
		launch_an_app("settings");
		clickOnNetwork_Internet();
		clickOn_Wifi_Lnk();
		turnOn_Wifi();
		remove_connectedNtwrk();
		connect_to_WiFi(WIFI_SSID, WIFI_PWD);
		launch_an_app("settings");
		clickOn_UsersAndAccounts();
		if(check_GmailAlreadyAdded(EMAIL)==false) {
		clickOn_Addaccount();
		clickOn_Google();
		enter_EmailAddress(EMAIL);
		enter_EmailPassword(EMILPWD);
		clickOn_Iagree();
		clickOn_MORE();
		clickOn_ACCEPT();
		}
		verify_GmailAccountAdded(EMAIL,sa);
		postCond_RemoveGmailAccount(EMAIL);
	
			 }	 }
		sa.assertAll();	
	}*/
	/*
	
	@Ignore
	@Test(priority=54,dataProvider="XP8_Wifi", dataProviderClass=DataProviders.class)
	public void XP8_TC_54_Wifi_ChangeLanguageToFrench(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{  
		SoftAssert sa=new SoftAssert();
		//Check the Wi-Fi interface display settings on changing the  phone language to French
		APP_LOGS.info("===========XP8_TC_53_Wifi_ChangeLanguageToFrench============");
		 for(int i=1;i<=itr;i++) {
			 if(operator.contains("-10")) {
		launch_an_app("settings");
		clickOn_System();
		clickOn_LanguagesAndInput();
		clickOn_Languages();
		boolean searchResult=search_FrenchLanguage();
		if(searchResult==false) {
			clickOn_AddALanguage();
			clickOn_French();
		}
		clickOn_MoreOptions();
		clickOn_Remove();
		remove_LanguagesOtherthanFrench();
	//	select_Language();
		 } }
		 sa.assertAll();
	
	}*/
	
	
}
