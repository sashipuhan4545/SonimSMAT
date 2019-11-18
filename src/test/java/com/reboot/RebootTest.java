package com.reboot;

import java.awt.AWTException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.util.Properties;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.NoSuchElementException;
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
import com.relevantcodes.extentreports.LogStatus;
import com.xp5S.util.CommonConfig;
import com.xp5S.util.GetMethods;
import com.xp5S.util.appiumService;
import com.xp8.util.BaseUtil;
import com.xp8.util.ExcelConstants;
import com.xp8.util.ExcelReader;
import com.xp8.util.Locators_BaseUtil;
import com.xp8.util.New_SanityLocators;

import application.AllQA;
import io.appium.java_client.MobileBy;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.AndroidKeyCode;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import javafx.scene.input.KeyCode;

public class RebootTest extends BaseUtil {


	boolean val2=false;
	boolean val3=false;




	@BeforeSuite
	public void beforeSuite() throws FileNotFoundException, InterruptedException,  IOException, ParseException, ParseException, ParseException, ParseException, ParseException, ParseException, org.json.simple.parser.ParseException 
	{
		extent = new ExtentReports("src/test/resources/extentreport/XP8_RebootScenario.html", true); //Provide Desired Report Directory Location and Name
		extent.loadConfig(new File("src/test/resources/StorageFile/ReportsConfig.xml"));
		extent.addSystemInfo("Build #",JsonFileReaderAndWriter.primaryDevFirmwareReader())
		.addSystemInfo("Product",JsonFileReaderAndWriter.primaryDevModelReader())
		.addSystemInfo("Operator", JsonFileReaderAndWriter.primaryDevOperatorReader());		

	}

	@BeforeSuite
	public void numofTestCases() throws ClassNotFoundException, IOException, InterruptedException {


		//int numberOfTestCases = GetMethods.TotalTestcase("XP8_TC", this.getClass());

		appiumService.TOTAL_NUM_OF_TESTCASES=GetMethods.TotalTestcase("XP8_reboot", this.getClass());
		actBatteryPopUp();


	}
	
	
	
	


	@BeforeMethod()
	public  void beforeMethod(Method method) 
	{
		test = extent.startTest( (this.getClass().getSimpleName() +" :: "+  method.getName()),method.getName()); //Test Case Start Here
		//test.assignAuthor("JK"); //Test Script Author Name
		System.out.println("Before Method");
		
	}


	/*	@BeforeSuite
	public void setUpSys() throws FileNotFoundException, MalformedURLException, InterruptedException, AWTException {
		Locators_BaseUtil loc2 = new Locators_BaseUtil(aDriver);
		PageFactory.initElements(new AppiumFieldDecorator(aDriver), loc2);
	}
	 */


	@AfterMethod()
	public void tearDown(ITestResult result,Method method) throws  IOException, ParseException, ParseException, ParseException, ParseException, ParseException, ParseException , InterruptedException
	{
		if(result.getStatus()==ITestResult.FAILURE)
		{

			String screenshot_path=captureScreenshot(method.getName());
			String image= test.addScreenCapture(screenshot_path);		
			test.log(LogStatus.FAIL,result.getThrowable());	
			//clearRecentApps();
		}
		extent.endTest(test);
		extent.flush();
	}





	//=========================================================Test Scripts================================================================

	@Test()
	public void XP8_reboot_device() throws FileNotFoundException, IOException, org.json.simple.parser.ParseException, InterruptedException {


		try {


			for(int j=1;j<=AllQA.NUMOFSTEPS;j++) {

				//clearRecentApps();

				TimeUnit.SECONDS.sleep(5);

				SoftAssert sa=new SoftAssert();

				for(int i=1;i<=AllQA.NUMOFCALLS;i++) {

					Runtime.getRuntime().exec("adb -s "+JsonFileReaderAndWriter.primaryDevIdReader()+" reboot");
					test.log(LogStatus.INFO, "Device Reboots #:"+i);
					TimeUnit.SECONDS.sleep(50);
					aDriver=new AndroidDriver<AndroidElement>(new URL("http://127.0.0.1:4723/wd/hub"),capabilities);  //

				}


				boolean one=makeCall_And_End_Call();
				TimeUnit.SECONDS.sleep(5);
				boolean two=browse();
				TimeUnit.SECONDS.sleep(5);

				boolean three=youtubeStream();

				System.out.println(one);
				System.out.println(two);
				System.out.println(three);

				System.out.println("---------------=======================-------");

				if(one && two && three) {
					test.log(LogStatus.INFO, "Call,Browse and Youtube Stream is Sucessfull");

					test.log(LogStatus.PASS, "Test cases is passed");

					sa.assertTrue(true, "");

				}else{

				  sa.fail();

				}

				sa.assertAll();	

			}

		} catch (Exception e) {
			e.printStackTrace();
		}



	}








	public boolean makeCall_And_End_Call() throws FileNotFoundException, IOException, org.json.simple.parser.ParseException, InterruptedException {

		boolean val1=false;

		Runtime.getRuntime().exec("adb -s "+JsonFileReaderAndWriter.primaryDevIdReader()+" shell am start -a android.intent.action.CALL -d tel:"+AllQA.REFERENCEDEVMDN);
		TimeUnit.SECONDS.sleep(7);
		String cmd="adb -s "+JsonFileReaderAndWriter.primaryDevIdReader()+" shell \"dumpsys telephony.registry | grep mCallState\"";


		while(true) {

			InputStream p = Runtime.getRuntime().exec(cmd).getInputStream();
			InputStreamReader isr = new InputStreamReader(p);
			BufferedReader br = new BufferedReader(isr);
			String value = br.readLine();
			System.out.println(value);

			if(value.contains("mCallState=1") || value.contains("mCallState=2")) {


				Runtime.getRuntime().exec("adb -s "+JsonFileReaderAndWriter.ReadRefDeviceId()+" shell input keyevent 5");
				val1=true;
				TimeUnit.SECONDS.sleep(10);
				Runtime.getRuntime().exec("adb -s "+JsonFileReaderAndWriter.primaryDevIdReader()+" shell input keyevent 6");
				break;
			}
			System.out.println(val1);

		}
		return val1;

	}

	public boolean browse() throws FileNotFoundException, IOException, org.json.simple.parser.ParseException, InterruptedException {

		try {


			Runtime.getRuntime().exec("adb -s "+JsonFileReaderAndWriter.primaryDevIdReader()+" shell pm clear com.android.chrome");

			TimeUnit.SECONDS.sleep(5);

			aDriver.findElement(MobileBy.AccessibilityId("Apps list")).click();
			TimeUnit.SECONDS.sleep(2);

			aDriver.findElement(MobileBy.AndroidUIAutomator("new UiSelector().className(\"android.widget.TextView\").text(\"Chrome\")")).click();


			TimeUnit.SECONDS.sleep(4);

			aDriver.findElement(MobileBy.id("com.android.chrome:id/terms_accept")).click();
			TimeUnit.SECONDS.sleep(2);
			aDriver.findElement(MobileBy.id("com.android.chrome:id/next_button")).click();
			TimeUnit.SECONDS.sleep(2);
			aDriver.findElement(MobileBy.id("com.android.chrome:id/negative_button")).click();
			TimeUnit.SECONDS.sleep(5);

            aDriver.findElement(MobileBy.id("com.android.chrome:id/url_bar")).clear();
        	TimeUnit.SECONDS.sleep(2);
            aDriver.findElement(MobileBy.id("com.android.chrome:id/url_bar")).sendKeys("www.google.com");;
            aDriver.pressKeyCode(AndroidKeyCode.ENTER);
            

//			Runtime.getRuntime().exec("adb -s "+JsonFileReaderAndWriter.primaryDevIdReader()+" shell am start -a android.intent.action.VIEW -d http://www.stackoverflow.com");
			TimeUnit.SECONDS.sleep(8);
			if(aDriver.findElement(MobileBy.id("main-frame-error")).isDisplayed() ||aDriver.findElement(MobileBy.id("main-frame-error")).isDisplayed() ) {

				val2=false;

			}
			System.out.println("browser"+val2);

		} catch (Exception e) {

			val2=true;
			System.out.println("browser NuSuch"+val2);
			aDriver.pressKeyCode(AndroidKeyCode.HOME);

		}
		return val2;



	}


	public boolean youtubeStream() throws InterruptedException {

		try {
			aDriver.pressKeyCode(AndroidKeyCode.HOME);
			TimeUnit.SECONDS.sleep(2);

			aDriver.findElement(MobileBy.AccessibilityId("Apps list")).click();
			TimeUnit.SECONDS.sleep(3);
			aDriver.findElement(MobileBy.id("com.android.launcher3:id/search_box_input")).sendKeys("Youtube");
			aDriver.findElement(MobileBy.AccessibilityId("YouTube")).click();
			TimeUnit.SECONDS.sleep(2);
			clickOnNotNow();

			TimeUnit.SECONDS.sleep(8);
			TouchAction action=new TouchAction(aDriver);
			action.tap(0, 216).perform();
			TimeUnit.SECONDS.sleep(8);

			if(aDriver.findElement(MobileBy.xpath("//android.widget.TextView[@text='No connection']")).isDisplayed()) {

				val3=false;
			}

			System.out.println("youtube"+val3);


		} catch (NoSuchElementException e) {
			val3=true;
			System.out.println("youtube No Such"+val3);

		}catch (Exception e) {
		}
		return val3;


	}

	public void clickOnNotNow() {

		try {

			if(aDriver.findElement(MobileBy.id("com.google.android.youtube:id/later_button")).isDisplayed()) {

				aDriver.findElement(MobileBy.id("com.google.android.youtube:id/later_button")).click();
			}
		


	}catch (Exception e) {
		
		System.out.println("Doesnot display");
		
	}

}

	public void actBatteryPopUp() {
		
		
		Thread t =new Thread(new Runnable() {
			
			@Override
			public void run() {
			
				Timer t=new Timer();
				t.schedule(new TimerTask() {

					public void run() {

				 try {
					 
					 if(aDriver.findElement(MobileBy.xpath("//android.widget.TextView[@text='Battery is full']")).isDisplayed()) {
						 
						 TimeUnit.SECONDS.sleep(2);
						 
						 aDriver.findElement(MobileBy.xpath("//android.widget.Button[@text='OK']")).click();
					 }
					 
					 
				 }catch(Exception e) {
					 
					 System.out.println("Battery pop up is not present ");
					 
				 }
						
				
							
					}
				},  0, 10*(100*1)); 
				
				
			}
		});
		
		t.start();
	}
	
	


}