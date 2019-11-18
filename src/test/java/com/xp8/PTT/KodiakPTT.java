package com.xp8.PTT;

import java.awt.AWTException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Timer;
import java.util.concurrent.TimeUnit;

import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.DeviceRotation; 
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

import com.beust.jcommander.Strings;
import com.google.common.collect.ImmutableMap;
import com.graphics.gui.JsonFileReaderAndWriter;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTestInterruptedException;
import com.relevantcodes.extentreports.LogStatus;
import com.xp5S.util.GetMethods;
import com.xp5S.util.appiumService;
import com.xp8.util.BaseUtil;
import com.xp8.util.ExcelConstants;
import com.xp8.util.ExcelReader;
import com.xp8.util.Locators_BaseUtil;
import com.xp8.util.Locators_CallSetting;

import TNGListner.RetryAnalyzer;
import application.AllQA;
import io.appium.java_client.MobileBy;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidKeyCode;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.remote.MobileCapabilityType;

public class KodiakPTT extends BaseUtil{

	public ExcelReader excel;
	Properties properties;
	public static ExtentTestInterruptedException testexception;
	public Timer timer;


	boolean value = false;
	public String p_Id = ""; // Primary Device Serial Number.
	public String r_Id = ""; // Reference Device Serial Number.
	public String p_b_No = ""; // Primary Device Build Number.
	public String r_b_No = ""; // Reference Device Build Number.
	public String pryNum = AllQA.PRIMARYDEVMDN; // Reference Device MDN.
	public String refNum = AllQA.REFERENCEDEVMDN;
	@BeforeSuite
	public void beforeSuite() throws FileNotFoundException, InterruptedException, IOException, ParseException  
	{

		extent = new ExtentReports("src/test/resources/extentreport/CSFB_VoLTE.html", true); //Provide Desired Report Directory Location and Name
		extent.loadConfig(new File("src/test/resources/StorageFile/ReportsConfig.xml"));
		extent.addSystemInfo("Build #",JsonFileReaderAndWriter.primaryDevFirmwareReader())
		.addSystemInfo("Product",JsonFileReaderAndWriter.primaryDevModelReader())
		.addSystemInfo("Operator", JsonFileReaderAndWriter.primaryDevOperatorReader());			


	}



	@BeforeMethod()
	public  void beforeMethod(Method method) 
	{
		test = extent.startTest( (this.getClass().getSimpleName() +" :: "+  method.getName()),method.getName()); //Test Case Start Here

	}

	@BeforeSuite
	public void numofTestCases() throws ClassNotFoundException, IOException, InterruptedException {


		//int numberOfTestCases = GetMethods.TotalTestcase("XP8_TC", this.getClass());
		try {
			appiumService.TOTAL_NUM_OF_TESTCASES=GetMethods.TotalTestcase("PerformanceScript", this.getClass());
		}catch (Exception e) {
			// TODO: handle exception
		}

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

		try {

			properties=loadDriverAndProperties();

			Locators_BaseUtil loc1 = new Locators_BaseUtil(aDriver);	
			PageFactory.initElements(new AppiumFieldDecorator(aDriver),loc1);
			excel=new ExcelReader(ExcelConstants.XP5S_XL_PATH);	
			System.out.println("Setting device orientation");

			aDriver.rotate(ScreenOrientation.PORTRAIT);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}



	@Test	
	public void PerformanceScript() throws InterruptedException, FileNotFoundException, IOException, ParseException {

     System.out.println(AllQA.NUMOFCALLS);
		
		try {
			clearRecentApps();
			SoftAssert sa=new SoftAssert();


			String firmWareNum=JsonFileReaderAndWriter.primaryDevFirmwareReader();
			if(firmWareNum.contains("8A")) {


				for(int i=1;i<=AllQA.NUMOFCALLS;i++) {

                   aDriver.pressKeyCode(AndroidKeyCode.HOME);
                   
                   TimeUnit.SECONDS.sleep(2);
				
				    System.out.println("+++++---------------------");

					System.out.println("ITERATION NUMBER  "+i);
					
				}
			}
					//disable_Enhanced_4G();
				//	TimeUnit.SECONDS.sleep(5);
					/*//boolean fst=makeCall_And_End_Call();
					//enable_Enhanced_4G();
					TimeUnit.SECONDS.sleep(5);
					//boolean snd=makeCall_And_End_Call();
					aDriver.pressKeyCode(AndroidKeyCode.HOME);

					if(fst && snd) {
						
						System.out.println("Test cases is passed");

						sa.assertTrue(true, "");
						test.log(LogStatus.INFO, "Iteration #"+i);
						test.log(LogStatus.PASS, "Test cases is passed");
					}else {
						System.out.println("Test cases is failed");

						test.log(LogStatus.INFO, "Iteration #"+i);
						sa.fail();
					}



				}

*/


			//}
					else if (firmWareNum.contains("3A")) {

				TimeUnit.SECONDS.sleep(2);

			}else if (firmWareNum.contains("5A")) {


				TimeUnit.SECONDS.sleep(2);
			}

			TimeUnit.SECONDS.sleep(5);
		    System.out.println("lassssssssssssssssssss");
			//test.log(LogStatus.PASS, "TestCase is Pass");
			sa.assertAll();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}




	}


	public boolean makeCall_And_End_Call() throws FileNotFoundException, IOException, org.json.simple.parser.ParseException, InterruptedException {

		boolean val1=false;

		Runtime.getRuntime().exec("adb -s "+JsonFileReaderAndWriter.primaryDevIdReader()+" shell am start -a android.intent.action.CALL -d tel:"+AllQA.REFERENCEDEVMDN);
		TimeUnit.SECONDS.sleep(10);


		while(true) {
			String cmd="adb -s "+JsonFileReaderAndWriter.ReadRefDeviceId()+" shell \"dumpsys telephony.registry | grep mCallState\"";


			InputStream p = Runtime.getRuntime().exec(cmd).getInputStream();
			InputStreamReader isr = new InputStreamReader(p);
			BufferedReader br = new BufferedReader(isr);
			String value = br.readLine();
			System.out.println(value);


			if(JsonFileReaderAndWriter.PrimaryDevicesimAvailability().contains("Jio")) {
				
				System.out.println("Jio----------------------------------------------");

				if(value.contains("mCallState=1") || value.contains("mCallState=2")) {


					Runtime.getRuntime().exec("adb -s "+JsonFileReaderAndWriter.ReadRefDeviceId()+" shell input keyevent 5");
					val1=true;
					TimeUnit.SECONDS.sleep(10);
					Runtime.getRuntime().exec("adb -s "+JsonFileReaderAndWriter.primaryDevIdReader()+" shell input keyevent 6");
					break;
				}else if (value.contains("mCallState=0")) {
					System.out.println("Jio----------------------------------------------call state 0");


					System.out.println("This is jio sim ,so lte is disbaled");
					break;
				}
			}else if (JsonFileReaderAndWriter.PrimaryDevicesimAvailability().contains("airtel")) {
				System.out.println("Airtel---------------------------------------------------");


				if(value.contains("mCallState=1") || value.contains("mCallState=2")) {


					Runtime.getRuntime().exec("adb -s "+JsonFileReaderAndWriter.ReadRefDeviceId()+" shell input keyevent 5");
					val1=true;
					TimeUnit.SECONDS.sleep(10);
					Runtime.getRuntime().exec("adb -s "+JsonFileReaderAndWriter.primaryDevIdReader()+" shell input keyevent 6");
					break;
				}else if (value.contains("mCallState=0")) {

					System.out.println("Airtel------------------------------------------------callstate=0");

					System.out.println("This is jio sim ,so lte is disbaled");
					break;
				}



			}


		}
		return val1;

	}	


	public boolean makeCall_And_End_Call_2(String x) throws FileNotFoundException, IOException, org.json.simple.parser.ParseException, InterruptedException {

		boolean val1=false;

		Runtime.getRuntime().exec("adb -s "+JsonFileReaderAndWriter.primaryDevIdReader()+" shell am start -a android.intent.action.CALL -d tel:"+AllQA.REFERENCEDEVMDN);
		TimeUnit.SECONDS.sleep(7);


		while(true) {
		String cmd="adb -s "+JsonFileReaderAndWriter.ReadRefDeviceId()+" shell \"dumpsys telephony.registry | grep mCallState\"";


			InputStream p = Runtime.getRuntime().exec(cmd).getInputStream();
			InputStreamReader isr = new InputStreamReader(p);
			BufferedReader br = new BufferedReader(isr);
			String value = br.readLine();
			System.out.println(value);


			if(JsonFileReaderAndWriter.PrimaryDevicesimAvailability().contains("Jio")) {

				if(value.contains("mCallState=1") || value.contains("mCallState=2") || value.contains("mCallState=0")) {


					Runtime.getRuntime().exec("adb -s "+JsonFileReaderAndWriter.ReadRefDeviceId()+" shell input keyevent 5");
					val1=true;
					TimeUnit.SECONDS.sleep(10);
					Runtime.getRuntime().exec("adb -s "+JsonFileReaderAndWriter.primaryDevIdReader()+" shell input keyevent 6");
					break;

				}else if (JsonFileReaderAndWriter.PrimaryDevicesimAvailability().contains("airtel")) {

					if(value.contains("mCallState=1") || value.contains("mCallState=2")) {


						Runtime.getRuntime().exec("adb -s "+JsonFileReaderAndWriter.ReadRefDeviceId()+" shell input keyevent 5");
						val1=true;
						TimeUnit.SECONDS.sleep(10);
						Runtime.getRuntime().exec("adb -s "+JsonFileReaderAndWriter.primaryDevIdReader()+" shell input keyevent 6");
						break;

					}else if (value.contains("mCallState=0")) {
						

						
						break;

					}

				}

			}


		}
		return val1;

	}	
	
	


	public void disable_Enhanced_4G() throws InterruptedException {

		
		aDriver.findElement(MobileBy.AccessibilityId("Apps list")).click();
		aDriver.findElement(MobileBy.id("com.android.launcher3:id/search_box_input")).sendKeys("Settings");
		aDriver.findElement(MobileBy.AccessibilityId("Settings")).click();
		//aDriver.findElement(MobileBy.AccessibilityId("Network & Internet")).click();
		//aDriver.findElement(MobileBy.xpath("//android.widget.TextView[@text='Network & Internet']")).click();
		System.out.println("network and internet ");
		TimeUnit.SECONDS.sleep(2);
		aDriver.findElement(MobileBy.AndroidUIAutomator("new UiSelector().className(\"android.widget.TextView\").text(\"Network & Internet\")")).click();
		TimeUnit.SECONDS.sleep(4);
		System.out.println("network and internet is clicked");
		try {
		
			if(aDriver.findElement(MobileBy.xpath("//android.widget.TextView[@text='Airplane mode']/../..//android.widget.Switch[@text='ON']")).isDisplayed()) {
				
				System.out.println("ON is displayed so switching to off state");

				aDriver.findElement(MobileBy.xpath("//android.widget.TextView[@text='Airplane mode']")).click();
			}

		}catch (org.openqa.selenium.NoSuchElementException e) {
			System.out.println("Exeption is thrown ");

			aDriver.findElement(MobileBy.xpath("//android.widget.TextView[@text='Airplane mode']")).click();
			TimeUnit.SECONDS.sleep(2);
			aDriver.findElement(MobileBy.xpath("//android.widget.TextView[@text='Airplane mode']")).click();
			

			
		}


		aDriver.findElement(MobileBy.xpath("//android.widget.TextView[@text='Mobile network']")).click();
		//aDriver.findElement(MobileBy.xpath("//android.widget.TextView[@text='Advanced']")).click();
		TimeUnit.SECONDS.sleep(2);
		aDriver.findElement(MobileBy.AndroidUIAutomator("new UiSelector().className(\"android.widget.TextView\").text(\"Advanced\")")).click();
		TimeUnit.SECONDS.sleep(4);
		
		try {

			TimeUnit.SECONDS.sleep(2);
			

			if(aDriver.findElement(MobileBy.xpath("//android.widget.TextView[@text='Enhanced 4G LTE Mode']/../..//android.widget.Switch[@text='ON']")).isDisplayed()) {
				System.out.println("INSIDE IF");
				aDriver.findElement(MobileBy.AndroidUIAutomator("new UiSelector().className(\"android.widget.TextView\").textContains(\"Enhanced 4G LTE \")")).click();
				
				
				TimeUnit.SECONDS.sleep(4);

			}
			else if(aDriver.findElement(By.xpath("//android.widget.TextView[@text='Enhanced 4G LTE services']/../..//android.widget.Switch[@text='ON']")).isDisplayed()) {
				
				System.out.println("ELSE IF");
	aDriver.findElement(MobileBy.AndroidUIAutomator("new UiSelector().className(\"android.widget.TextView\").textContains(\"Enhanced 4G LTE \")")).click();
				
				
				TimeUnit.SECONDS.sleep(4);
			}
			else{
				 System.out.println("ELSE");
			}
			
			
		}catch (org.openqa.selenium.NoSuchElementException e) {

			System.out.println("OFF SO not doing anythink");
			TimeUnit.SECONDS.sleep(2);
			
			}
		

	}

				

	public void enable_Enhanced_4G() throws InterruptedException {


		aDriver.pressKeyCode(AndroidKeyCode.HOME);
		aDriver.findElement(MobileBy.AccessibilityId("Apps list")).click();
		aDriver.findElement(MobileBy.id("com.android.launcher3:id/search_box_input")).sendKeys("Settings");
		aDriver.findElement(MobileBy.AccessibilityId("Settings")).click();
		TimeUnit.SECONDS.sleep(2);
		//aDriver.findElement(MobileBy.xpath("//android.widget.TextView[@text='Network & Internet']")).click();
		aDriver.findElement(MobileBy.AndroidUIAutomator("new UiSelector().className(\"android.widget.TextView\").text(\"Network & Internet\")")).click();
		TimeUnit.SECONDS.sleep(4);
		try {

             if(aDriver.findElement(MobileBy.xpath("//android.widget.TextView[@text='Airplane mode']/../..//android.widget.Switch[@text='ON']")).isDisplayed()) {
				
				System.out.println("ON is displayed so switching to off state");

				aDriver.findElement(MobileBy.xpath("//android.widget.TextView[@text='Airplane mode']")).click();
			}

		}catch (org.openqa.selenium.NoSuchElementException e) {

			aDriver.findElement(MobileBy.xpath("//android.widget.TextView[@text='Airplane mode']")).click();
			TimeUnit.SECONDS.sleep(2);
			aDriver.findElement(MobileBy.xpath("//android.widget.TextView[@text='Airplane mode']")).click();

		}


		aDriver.findElement(MobileBy.xpath("//android.widget.TextView[@text='Mobile network']")).click();
		TimeUnit.SECONDS.sleep(2);
		aDriver.findElement(MobileBy.xpath("//android.widget.TextView[@text='Advanced']")).click();

		try {
			

				if(aDriver.findElement(MobileBy.xpath("//android.widget.TextView[@text='Enhanced 4G LTE Mode']/../..//android.widget.Switch[@text='OFF']")).isDisplayed() || aDriver.findElement(MobileBy.xpath("//android.widget.TextView[@text='Enhanced 4G LTE services']/../..//android.widget.Switch[@text='OFF']")).isDisplayed()) {
	                 System.out.println("inside if");
				aDriver.findElement(MobileBy.AndroidUIAutomator("new UiSelector().className(\"android.widget.TextView\").textContains(\"Enhanced 4G LTE \")")).click();
				
				System.out.println("Enhanced 4g lte is enabled");
				TimeUnit.SECONDS.sleep(4);
			}
		

		}catch (org.openqa.selenium.NoSuchElementException e) {

			
			System.out.println("Catch Enhanced 4g lte is enabled");
		}


	}
}
