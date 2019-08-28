package com.xp8.test;

import java.awt.AWTException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.util.Hashtable;
import java.util.Properties;
import java.util.Timer;
import java.util.TimerTask;


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
import com.xp5S.util.GetMethods;
import com.xp5S.util.appiumService;
import com.xp8.util.DataProviders;
import com.xp8.util.ExcelConstants;
import com.xp8.util.ExcelReader;
import com.xp8.util.Locators_BaseUtil;
import com.xp8.util.Locators_Network_DeviceStability;
import com.xp8.util.XP8_Network_Stability_Util_orio;

import application.AllQA;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class XP8_NetworkConnectivityTest extends XP8_Network_Stability_Util_orio{

	public ExcelReader excel;
	Properties properties;
	public static ExtentTestInterruptedException testexception;
	public int  itr =AllQA.NUMOFCALLS;
	boolean value = false;
	public Timer timer1;
	public String operator;
	public  String SSID=AllQA.SSID;
	public  String PWD=AllQA.WIFIPASSWORD;
	public String PRIMARY_BT_DEVICE_NAME="Sashi";
	public String REF_BT_DEVICE_NAME="AUTOMATION";
	public String url1 = "www.google.com";
	public String URL2="www.amazon.com";

	@BeforeSuite
	public void beforeSuite() throws FileNotFoundException, InterruptedException, IOException, ParseException  
	{
		extent = new ExtentReports("src/test/resources/extentreport/XP8_NetworkConnectivity_Stability_Orio_TestReport.html", true); //Provide Desired Report Directory Location and Name
		extent.loadConfig(new File(System.getProperty("user.dir")+"//ReportsConfig.xml"));
		extent.addSystemInfo("Appium", "1.2.7").addSystemInfo("Environment", "TEST");
		fetch_Devices_Details();	
		
		try {
			appiumService.TOTAL_NUM_OF_TESTCASES=GetMethods.TotalTestcase("XP8_TC", this.getClass());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@BeforeMethod()
	public  void beforeMethod(Method method) 
	{
		test = extent.startTest( (this.getClass().getSimpleName() +" :: "+  method.getName()),method.getName()); //Test Case Start Here
		clear_ChromeHistory();
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


	@BeforeTest()
	public void setupSys() throws InterruptedException, AWTException, IOException, ParseException{
		properties=loadDriverAndProperties();
		Locators_Network_DeviceStability loc=new Locators_Network_DeviceStability(aDriver);
		Locators_BaseUtil loc1 = new Locators_BaseUtil(aDriver);	
		PageFactory.initElements(new AppiumFieldDecorator(aDriver),loc);
		PageFactory.initElements(new AppiumFieldDecorator(aDriver),loc1);
		excel=new ExcelReader(ExcelConstants.XP5S_XL_PATH);	
		operator=JsonFileReaderAndWriter.primaryDevFirmwareReader();
		

	}
	
	@BeforeTest
	public void timer() {
		try {
			timer1 = new Timer();
			timer1.schedule(new TimerTask() {
				@Override
				public void run() {
//					System.out.println("Im in Timer");
				/*	if(isElementExist(Locators_DeviceStability.alert_OKBtn)) {
						clickBtn(Locators_DeviceStability.OK);
					}
					if(isElementExist(Locators_DeviceStability.OK)) {
						Locators_DeviceStability.OK.click();
						System.out.println("Clicked Timer Element");

					}
*/				}
			}, 0, 10*(100*1));
			aDriver.rotate(ScreenOrientation.PORTRAIT);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test(priority=1,dataProvider="XP8_Stability", dataProviderClass=DataProviders.class)
	public void XP8_TC_001_Stability_Enable_Disable_MobileData_DeviceSettings(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException
	{
		APP_LOGS.info("===========XP8_TC_001_Stability_Enable_Disable_MobileData_DeviceSettings============");
		SoftAssert sa = new SoftAssert();

		clearRecentApps();
		launch_an_app("settings");
		clickOn_NetworkAndInternet();
		disable_AirplaneMode();	
		clickOn_Wifi();		
		disable_Wifi();
		for(int i =1; i<=itr; i++) {			

		if(operator.contains("-10") || operator.contains("-11") || operator.contains("-15") ||
				operator.contains("-26") || operator.contains("-29")) {
		//For ATT,BELL,VERIZON,SL,SPRINT
		
				launch_an_app("settings");
				clickOn_NetworkAndInternet();
				clickOn_MobileNetwork();
				disable_MobileData();
				launch_an_app("chrome");
				clear_ChromePermission();	
				enterURL(i, url1);
				validate_PageIsNotLoaded(i,"Mobile data ", sa);
				launch_an_app("settings");
				clickOn_NetworkAndInternet();
				clickOn_MobileNetwork();
				enable_Mobiledata();
				launch_an_app("chrome");		
				clear_ChromePermission();
				enterURL(i, URL2);
				validate_PageIsLoaded("Mobile data ",i,sa);
		}else {
				
				launch_an_app("settings");
				clickOn_NetworkAndInternet();
				clickOn_MobileNetwork();
				disable_MobileData();
				launch_an_app("chrome");
				clear_ChromePermission();	
				enterURL(i, url1);
				validate_PageIsNotLoaded(i,"Mobile data ", sa);
				launch_an_app("settings");
				clickOn_NetworkAndInternet();
				clickOn_MobileNetwork();
				enable_Mobiledata();
				launch_an_app("chrome");		
				clear_ChromePermission();
				enterURL(i, URL2);
				validate_PageIsLoaded("Mobile data ",i,sa);
			}
		
		}
		sa.assertAll();
	}
	@Test(priority=2,dataProvider="XP8_Stability", dataProviderClass=DataProviders.class)
	public void XP8_TC_002_Stability_Enable_Disable_Connection_Secured_Wifi_DeviceSettings(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException
	{
		APP_LOGS.info("======================XP8_TC_002_Stability_Enable_Disable_Connection_Secured_Wifi_DeviceSettings=======================");
		SoftAssert sa = new SoftAssert();
		clearRecentApps();
		launch_an_app("settings");
		clickOn_NetworkAndInternet();
		disable_AirplaneMode();
		clickOn_MobileNetwork();
		disable_MobileData();
		clickOn_BackBtn();
		clickOn_Wifi();
		enable_Wifi();
		remove_connectedNtwrk();
		select_Wifi_SSID(SSID);	
		enter_WifiPwd(PWD);
		for(int i=1; i<=itr; i++) {	

		if(operator.contains("-10") || operator.contains("-11") || operator.contains("-15") ||
				operator.contains("-26") || operator.contains("-29")) {
		//For ATT,BELL,VERIZON,SL,SPRINT
			launch_an_app("settings");
			clickOn_NetworkAndInternet();
			clickOn_Wifi();
			disable_Wifi();
			launch_an_app("chrome");
			clear_ChromePermission();	
			enterURL(i, url1);
			validate_PageIsNotLoaded(i,"Wifi ", sa);
			launch_an_app("settings");
			clickOn_NetworkAndInternet();
			clickOn_Wifi();
			enable_Wifi();	
			launch_an_app("chrome");
			clear_ChromePermission();	
			enterURL(i, URL2);
			validate_PageIsLoaded("Wifi ",i,sa);
		}else {
			launch_an_app("settings");
			clickOn_NetworkAndInternet();
			clickOn_Wifi();
			disable_Wifi();
			launch_an_app("chrome");
			clear_ChromePermission();	
			enterURL(i, url1);
			validate_PageIsNotLoaded(i,"Wifi ", sa);
			launch_an_app("settings");
			clickOn_NetworkAndInternet();
			clickOn_Wifi();
			enable_Wifi();	
			launch_an_app("chrome");
			clear_ChromePermission();	
			enterURL(i, URL2);
			validate_PageIsLoaded("Wifi ",i,sa);
					
				}
			}
		sa.assertAll();
	}
	
	@Test(priority=3,dataProvider="XP8_Stability", dataProviderClass=DataProviders.class)
	public void XP8_TC_003_Stability_Enable_Disable_Airplane_Devicesettings(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException
	{
		APP_LOGS.info("======================XP8_TC_003_Stability_Enable_Disable_Airplane_Devicesettings=======================");
		SoftAssert sa = new SoftAssert();
		clearRecentApps();
		for(int i=1; i<=itr; i++) {

		if(operator.contains("-10") || operator.contains("-11") || operator.contains("-15") ||
				operator.contains("-26") || operator.contains("-29")) {
		//For ATT,BELL,VERIZON,SL,SPRINT
					launch_an_app("settings");
					clickOn_NetworkAndInternet();
					enable_AirplaneMode();
					launch_an_app("phone");
					makeCall(refNum);
					validate_Airplane_Enable(sa,i);
					launch_an_app("settings");
					clickOn_NetworkAndInternet();
					disable_AirplaneMode();
					launch_an_app("phone");
					makeCall(refNum);
					validate_Airplane_Disable(sa,i);
					endCall();
		}else {					//other devices
					launch_an_app("settings");
					clickOn_NetworkAndInternet();
					enable_AirplaneMode();
					launch_an_app("phone");
					makeCall(refNum);
					validate_Airplane_Enable(sa,i);
					launch_an_app("settings");
					clickOn_NetworkAndInternet();
					disable_AirplaneMode();
					launch_an_app("phone");
					makeCall(refNum);
					validate_Airplane_Disable(sa,i);
					endCall();
				}
			
			}
		
		sa.assertAll();
	}
	/*@Test(priority=4,dataProvider="XP8_Stability", dataProviderClass=DataProviders.class)
	public void XP8_TC_004_Stability_Enable_Disable_BT_And_Scan_BT_Devices(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException
	{
		APP_LOGS.info("======================XP8_DeviceStability_004=======================");
		SoftAssert sa = new SoftAssert();
		for(int i=1; i<=itr; i++) {

			if(operator.contains("-10") || operator.contains("-11") || operator.contains("-15") ||
					operator.contains("-26") || operator.contains("-29")) {
			//For ATT,BELL,VERIZON,SL,SPRINT
						
					disable_BT_RefDevice();
					enable_BT_RefDevice();
					setName_RefBTDevice(REF_BT_DEVICE_NAME);
					launch_an_app("settings");
					scrollToText("Connected devices");
					scrollToText("Bluetooth");
					enable_BT();
					clickOn_BTSetting();
					clickOn_BTFORGET();
					clickOnText("Pair new device");
					select_BT_RefDevice(REF_BT_DEVICE_NAME);
					ref_TapOnPAIR();
					clickOnPAIR();
					validate_BluetoothIsEnabled(REF_BT_DEVICE_NAME, sa, itr);
					clickOn_BackBtn();
					disable_BT();
					customWait(2000);
					validate_BluetoothIsDisabled(sa,i);
					enable_BT_RefDevice(); // With the same Method we can Disable the BT.
		}else {
			//For other operator
					
					disable_BT_RefDevice();
					enable_BT_RefDevice();
					setName_RefBTDevice(REF_BT_DEVICE_NAME);
					launch_an_app("settings");
					scrollToText("Connected devices");
					scrollToText("Bluetooth");
					enable_BT();
					clickOn_BTSetting();
					clickOn_BTFORGET();
					clickOnText("Pair new device");
					select_BT_RefDevice(REF_BT_DEVICE_NAME);
					ref_TapOnPAIR();
					clickOnPAIR();
					validate_BluetoothIsEnabled(REF_BT_DEVICE_NAME, sa, itr);
					clickOn_BackBtn();
					disable_BT();
					customWait(2000);
					validate_BluetoothIsDisabled(sa,i);
					enable_BT_RefDevice(); // With the same Method we can Disable the BT.
				}
		}
		sa.assertAll();
	}
*/
}
