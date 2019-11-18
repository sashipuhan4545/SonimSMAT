package com.xp8.PTT;

import java.awt.AWTException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.util.Properties;
import java.util.Timer;
import java.util.concurrent.TimeUnit;

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
import com.xp8.util.BaseUtil;
import com.xp8.util.ExcelConstants;
import com.xp8.util.ExcelReader;
import com.xp8.util.Locators_BaseUtil;

import application.AllQA;
import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidKeyCode;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class NetworkPerformance extends BaseUtil{
	
	
	
	

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
	public void validate_different_networkmode_latching() throws InterruptedException, FileNotFoundException, IOException, ParseException {
		
		clearRecentApps();
		SoftAssert sa=new SoftAssert();
		
		String firmWareNum=JsonFileReaderAndWriter.primaryDevFirmwareReader();
		
		if(firmWareNum.contains("8A")) {
			

            aDriver.findElement(MobileBy.AccessibilityId("Apps list")).click();
			aDriver.findElement(MobileBy.id("com.android.launcher3:id/search_box_input")).sendKeys("Settings");
			aDriver.findElement(MobileBy.AccessibilityId("Settings")).click();
			
			
		}
		
		
		
		
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	



}
