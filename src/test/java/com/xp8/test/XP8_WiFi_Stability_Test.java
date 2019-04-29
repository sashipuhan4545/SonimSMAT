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
import com.relevantcodes.extentreports.ExtentTestInterruptedException;
import com.relevantcodes.extentreports.LogStatus;
import com.xp5S.util.GetMethods;
import com.xp5S.util.appiumService;
import com.xp8.util.DataProviders;
import com.xp8.util.ExcelConstants;
import com.xp8.util.ExcelReader;
import com.xp8.util.Locators_BaseUtil;
import com.xp8.util.Locators_DeviceStability;
import com.xp8.util.XP8_Stability_Util_orio;

import application.AllQA;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class XP8_WiFi_Stability_Test extends XP8_Stability_Util_orio{
	
	public ExcelReader excel;
	Properties properties;
	public static ExtentTestInterruptedException testexception;
	public int  itr =AllQA.NUMOFCALLS;
	public Timer timer1;


	@BeforeSuite
	public void beforeSuite() throws FileNotFoundException, InterruptedException, IOException, ParseException  
	{
		extent = new ExtentReports("src/test/resources/extentreport/XP8_WiFi_Stability_Orio_TestReport.html", true); //Provide Desired Report Directory Location and Name
		extent.loadConfig(new File(System.getProperty("user.dir")+"//ReportsConfig.xml"));
		extent.addSystemInfo("Appium", "1.2.7").addSystemInfo("Environment", "TEST");
		fetch_Devices_Details();	
	}

	@BeforeMethod()
	public  void beforeMethod(Method method) 
	{
		test = extent.startTest( (this.getClass().getSimpleName() +" :: "+  method.getName()),method.getName()); //Test Case Start Here
		test.assignAuthor("Farheen Taj"); //Test Script Author Name
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
	
	@BeforeSuite
	public void numofTestCases() throws ClassNotFoundException {
		
		appiumService.TOTAL_NUM_OF_TESTCASES=GetMethods.TotalTestcase("XP8_TC", this.getClass());
	}



	@BeforeTest()
	public void setupSys() throws MalformedURLException, InterruptedException, FileNotFoundException, AWTException{
		properties=loadDriverAndProperties();
		Locators_DeviceStability loc=new Locators_DeviceStability(aDriver);
		Locators_BaseUtil loc1 = new Locators_BaseUtil(aDriver);	
		PageFactory.initElements(new AppiumFieldDecorator(aDriver),loc);
		PageFactory.initElements(new AppiumFieldDecorator(aDriver),loc1);
		excel=new ExcelReader(ExcelConstants.XP5S_XL_PATH);	
	}
	
	@BeforeTest
	public void timer() {
		try {
			timer1 = new Timer();
			timer1.schedule(new TimerTask() {
				@Override
				public void run() {
					if(isElementExist(Locators_DeviceStability.alert_OKBtn)) {
						Locators_DeviceStability.OK.click();
					}
					if(isElementExist(Locators_DeviceStability.OK)) {
						System.out.println("Clicked Timer Element");
						Locators_DeviceStability.OK.click();
					}
				}
			}, 0, 10*(100*1));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	@Test(priority=1,dataProvider="XP8_Stability", dataProviderClass=DataProviders.class)
	public void XP8_TC_001_Stability_Connection_and_Disconnection_Secured_Wifi_DeviceSettings(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========XP8_Stability_001============");
		SoftAssert sa1 = new SoftAssert();
		clearRecentApps();
				launch_APP(Locators_DeviceStability.settings);
				clickOn_Networks_and_Internet();
				scrollToText("Mobile network");
				OFF_Switch("Mobile data");
				clickBtn(Locators_DeviceStability.OK);
				clickOn_BackBtn();
				scrollToTextContains("Wi");
				enableFeature(Locators_DeviceStability.enabled,Locators_DeviceStability.disabled,Locators_DeviceStability.switch_Title);
				checkWifiConnected();			
				for (int i = 1; i <=itr; i++) {
					launch_APP(Locators_DeviceStability.settings);
					clickOn_Networks_and_Internet();
					scrollToTextContains("Wi");
					wiFi_ON();	
					selectSSIDwifi();		   
					enterPassword();
					launch_an_app("browser");
					clearChromePermission();
					validate_And_BrowseIn_Chrome("www.google.com", Locators_DeviceStability.googleCoIn_Text, Locators_DeviceStability.google_Logo, Locators_DeviceStability.google_Logo,i,sa1);
					launch_APP(Locators_DeviceStability.settings);
					clickOn_Networks_and_Internet();
					scrollToTextContains("Wi");
					disconnectSSIDifConnected();
					launch_an_app("browser");
					validate_MobileData_Disable(i,"WiFi",sa1);
					customWait(3000);
				}					
			sa1.assertAll();
	}
	

	@Test(priority=2,dataProvider="XP8_Stability", dataProviderClass=DataProviders.class)
	public void XP8_TC_002_Stability_Enable_Disable_Connection_Secured_Wifi_DeviceSettings(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========XP8_Stability_002============");
			SoftAssert sa2 = new SoftAssert();
		
				launch_APP(Locators_DeviceStability.settings);
				clickOn_Networks_and_Internet();
				scrollToText("Mobile network");
				OFF_Switch("Mobile data");
				clickBtn(Locators_DeviceStability.OK);
				clickOn_BackBtn();
				scrollToTextContains("Wi");
				enableFeature(Locators_DeviceStability.enabled,Locators_DeviceStability.disabled,Locators_DeviceStability.switch_Title);
				selectSSIDwifi();		   
				enterPassword();
				for (int i = 1; i <=itr; i++) {
					launch_APP(Locators_DeviceStability.settings);
					clickOn_Networks_and_Internet();
					scrollToTextContains("Wi");
					wiFi_ON();	
					launch_an_app("browser");
					clearChromePermission();
					validate_And_BrowseIn_Chrome("www.google.com",Locators_DeviceStability.googleCoIn_Text,Locators_DeviceStability.google_Logo,Locators_DeviceStability.google_Logo,i,sa2);
					launch_an_app("settings");
					clickOn_Networks_and_Internet();
					wiFi_OFF();
					launch_an_app("browser");
					validate_MobileData_Disable(i,"WiFi",sa2);
					customWait(3000);
				}					
			sa2.assertAll();
	}

}
