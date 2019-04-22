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
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTestInterruptedException;
import com.relevantcodes.extentreports.LogStatus;
import com.xp3.Utils.Locators_BaseUtil;
import com.xp3.Utils.Locators_DeviceStability;
import com.xp3.Utils.XP3_Stability_Util;
import com.xp5S.util.DataProviders;
import com.xp5S.util.ExcelConstants;
import com.xp5S.util.ExcelReader;

import application.AllQA;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.AndroidKeyCode;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class XP3_NetworkConnectivityTest extends XP3_Stability_Util{


	public ExcelReader excel;
	Properties properties;
	public static ExtentTestInterruptedException testexception;
	public AndroidDriver<AndroidElement> aDriver;

	
	public int itr =AllQA.NUMOFCALLS;
	boolean value = false;
	
	@BeforeSuite
	public void beforeSuite() throws FileNotFoundException, InterruptedException, IOException, ParseException {

		extent = new ExtentReports("src/test/resources/extentreport/XP3_NetworkConnectivity_Stability_TestReport.html", true); //Provide Desired Report Directory Location and Name
		fetch_Devices_Details();
	}

	@BeforeMethod()
	public  void beforeMethod(Method method) throws InterruptedException 	{

		test = extent.startTest( (this.getClass().getSimpleName() +" :: "+  method.getName()),method.getName()); //Test Case Start Here
		test.assignAuthor(""); //Test Script Author Name
		extent.loadConfig(new File(System.getProperty("user.dir")+"//ReportsConfig.xml"));
		extent.addSystemInfo("Appium Version", "1.2.7").addSystemInfo("Environment", "TEST");		
	}

	@AfterMethod()
	public void tearDown(ITestResult result,Method method) throws IOException, InterruptedException	{

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
	public void XP3_TC_001_Stability_Enable_Disable_MobileData_DeviceSettings(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException 
	{
		APP_LOGS.info("===========XP3_Device_Stability============");
		startAdbLog("XP3_Device_Stability_01");
		SoftAssert sa1 = new SoftAssert();
		clearRecentApps();
				for (int i = 1; i <= itr; i++) {	
					disableData();
					launch_an_app("browser");
					validateBrowseNoInternet(sa1,i,"Data");
					launch_an_app("settings");
					enableData();
					launch_an_app("browser");		
					enterUrl(data.get("url"));
					validateBrowseInternet(sa1,"Data",i);				
				} 
				sa1.assertAll();
	}	
	
	
	@Test(priority=2,dataProvider="DeviceStability", dataProviderClass=DataProviders.class)
	public void XP3_TC_002_Stability_Enable_Disable_Connection_Secured_Wifi_DeviceSettings(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException 
	{
		APP_LOGS.info("===========XP3_Device_Stability============");
		startAdbLog("XP3_Device_Stability_02");	
		SoftAssert sa2 = new SoftAssert();
		clearRecentApps();
		disableData();
		navigateToApplication("Settings");
		selectOptn_Orio("Network & Internet",Locators_DeviceStability.NetworkInternet) ;
		selectWIFIOptn();
		ON_Switch("Wi");
		selectSSIDwifi();		   
		enterPassword();
		for (int i = 1; i <=itr; i++) {
			clearRecentApps();
			navigateToApplication("Settings");
			selectOptn_Orio("Network & Internet",Locators_DeviceStability.NetworkInternet) ;
			selectWIFIOptn();
			wiFi_OFF();
			launch_an_app("browser");		
			validateBrowseNoInternet(sa2,i,"WiFi");
			clearRecentApps();
			navigateToApplication("Settings");
			selectOptn_Orio("Network & Internet",Locators_DeviceStability.NetworkInternet) ;
			selectWIFIOptn();
			wiFi_ON();
			launch_an_app("browser");
			enterUrl(data.get("url"));
			validateBrowseInternet(sa2,"WiFi",i);
		}
		sa2.assertAll();
	}	
	
	
	@Test(priority=3,dataProvider="DeviceStability", dataProviderClass=DataProviders.class)
	public void XP3_TC_003_Stability_Enable_Disable_Airplane_Devicesettings(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException 
	{
		APP_LOGS.info("===========XP3_Device_Stability============");
		startAdbLog("XP3_Device_Stability_03");	
		SoftAssert sa3 = new SoftAssert();
		for (int i = 1; i <=itr; i++) {		
			
			navigateToApplication("Settings");
			customWait(1000);
			ScrollToElement(Locators_DeviceStability.SettingsList, "Network & Internet");
			customWait(2000);
			clickBtn(Locators_DeviceStability.NetworkInternet);
//			selectOptn_Orio("Network & Internet",Locators_DeviceStability.NetworkInternet) ;
			scrollTo("Airplane mode");
			ON_Switch("Airplane mode");
			clickBtn(Locators_DeviceStability.OK);
			navigateToApplication("Phone");
			dailCallUsingDailPad(refNum);
			validate_Airplane_Enable(refNum,sa3,i);
			navigateToApplication("Settings");
			ScrollToElement(Locators_DeviceStability.SettingsList, "Network & Internet");
			customWait(2000);
			clickBtn(Locators_DeviceStability.NetworkInternet);
//			selectOptn_Orio("Network & Internet",Locators_DeviceStability.NetworkInternet) ;
			scrollTo("Airplane mode");
			OFF_Switch("Airplane mode");
			navigateToApplication("Phone");
			dailCallUsingDailPad(refNum);
			validate_Airplane_Disable(refNum,sa3,i);
			endcall();
		
		}
		sa3.assertAll();
	}	
	
	@Test(priority=4,dataProvider="DeviceStability", dataProviderClass=DataProviders.class)
	public void XP3_TC_004_Stability_Enable_Disable_BT_And_Scan_BT_Devices(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException 
	{
		APP_LOGS.info("===========XP3_Device_Stability============");
		startAdbLog("XP3_Device_Stability_04");	
		SoftAssert sa4 = new SoftAssert();
		for (int i = 1; i <=itr; i++) {		
			Runtime.getRuntime().exec("adb -s "+r_Id+" shell input tap 777 1083"); // To Clear Battery full Popup if present.
			enable_BT_RefDevice();
			navigateToApplication("Settings");
			scrollToText("Connected devices");
			scrollToText("Bluetooth");
			OFF_Switch("On");
			ON_Switch("Off");
			customWait(4000);
			clickOnText("Pair new device");
			validate_BT_Devices(sa4,i);
			customWait(2000);
			clickOn_BackBtn();
			OFF_Switch("On");
			customWait(2000);
			customWait(2000);
			validate_Locators1(Locators_DeviceStability.BT_Disable_Text,sa4,i);
//			enable_BT_RefDevice(); // With the same Method we can Disable the BT.
		}
		sa4.assertAll();
	}	

}
