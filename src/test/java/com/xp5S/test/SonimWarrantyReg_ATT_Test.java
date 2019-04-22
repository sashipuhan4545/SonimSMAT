package com.xp5S.test;

import java.awt.AWTException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.util.Hashtable;
import java.util.Properties;

import javax.swing.JOptionPane;

import org.json.simple.parser.ParseException;
import org.openqa.selenium.support.PageFactory;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.graphics.gui.CountIncrementFile;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.ExtentTestInterruptedException;
import com.relevantcodes.extentreports.LogStatus;
import com.xp5S.util.BaseUtil;
import com.xp5S.util.DataProviders;
import com.xp5S.util.ExcelConstants;
import com.xp5S.util.ExcelReader;
import com.xp5S.util.Locators;
import com.xp5S.util.Locators_BaseUtil;
import com.xp5S.util.Locators_WarrantyReg;
import com.xp5S.util.SonimWarrantyReg_Util;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class SonimWarrantyReg_ATT_Test  extends SonimWarrantyReg_Util {

	Locators locators;
	public static ExcelReader excel;
	Properties properties;

	public static ExtentReports extent;
	public static ExtentTest test;

	public static ExtentTestInterruptedException testexception;
	//public AndroidDriver<AndroidElement> aDriver;


	@BeforeSuite
	public void beforeSuite() 
	{	//Provide Desired Report Directory Location and Name
		extent = new ExtentReports("src/test/resources/extentreport/XP5S_Scout_SonimWarrantyReg.html", true);

	}	

	@BeforeMethod()
	public  void beforeMethod(Method method) throws InterruptedException 
	{		
		test = extent.startTest( (this.getClass().getSimpleName() +" :: "+  method.getName()),method.getName()); //Test Case Start Here
		test.assignAuthor("Sashi P"); //Test Script Author Name
		extent.loadConfig(new File("src/test/resources/drivers/"+"//ReportsConfig.xml"));
		extent.addSystemInfo("Appium Version", "1.2.7").addSystemInfo("Environment", "TEST");	
		String BuildNumber=BaseUtil.fetchDeviceProperty("adb shell getprop ro.build.id");
		test.assignCategory("Build #:"+BuildNumber);

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
			//Here In case of TC failure ,This will clear the reccent APP and 	
			

			customWait(2000);
			clearRecentApps();	   
		}	  
		extent.endTest(test);
		extent.flush();
	}	


	/*@AfterMethod
	public void setProgressBar_TestResult() {

		int count =CountIncrementFile.getCount(7);  
		if(count==91) {
			count=count+9;
			CountIncrementFile.putCount(count);

		}
		else {

			ToolFrame.progressBar.setValue(count);
			count=count+7;
			CountIncrementFile.putCount(count);
		}


	}

*/
	@BeforeTest
	public void setupSys() throws MalformedURLException, InterruptedException, FileNotFoundException, AWTException{

		properties=loadDriverAndProperties();
		Locators_WarrantyReg loc=new Locators_WarrantyReg(aDriver);
		Locators_BaseUtil loc1 =new Locators_BaseUtil(aDriver);
		PageFactory.initElements(new AppiumFieldDecorator(getDriver()),loc1);
		PageFactory.initElements(new AppiumFieldDecorator(getDriver()),loc);
		excel=new ExcelReader(ExcelConstants.XP5S_XL_PATH);
	}	



	@Test(priority=1,dataProvider="Sonim_Warranty_Data_Provider", dataProviderClass=DataProviders.class)
	public void XP5s_Validate_Presence_Of_SWR_In_Scout_01(Hashtable<String, String> data) throws Exception
	{
		APP_LOGS.info("===========Sonim_Warranty_01============");		
		startAdbLog("Sonim_Warranty_01");
		recordVideo("Sonim_Warranty_01");
		clearRecentApps();		
		clickOnReset();
		presence_Of_SonimWarrantyReg_In_ScoutApp();	
		test.log(LogStatus.PASS, "TestCase Passed");		
	}	

	@Test(priority=2,dataProvider="Sonim_Warranty_Data_Provider", dataProviderClass=DataProviders.class)
	public void XP5s_Validate_Presence_Of_Register_Option_In_Scout_02(Hashtable<String, String> data) throws Exception
	{
		APP_LOGS.info("===========Sonim_Warranty_02============");		
		startAdbLog("Sonim_Warranty_02");
		recordVideo("Sonim_Warranty_02");
		clearRecentApps();				
		presence_Of_Register_Option();	
		test.log(LogStatus.PASS, "TestCase Passed");		
	}

	@Test(priority=3,dataProvider="Sonim_Warranty_Data_Provider", dataProviderClass=DataProviders.class)
	public void XP5s_Validate_Skip_AllFields_and_Validate_03(Hashtable<String, String> data) throws Exception
	{
		APP_LOGS.info("===========Sonim_Warranty_03============");		
		startAdbLog("Sonim_Warranty_03");
		recordVideo("Sonim_Warranty_03");
		clearRecentApps();			
		skip_AllFields_Press_SubmitBtn();
		test.log(LogStatus.PASS, "TestCase Passed");		
	}

	@Test(priority=4,dataProvider="Sonim_Warranty_Data_Provider", dataProviderClass=DataProviders.class)
	public void XP5s_Validate_CountryName_In_Scout_04(Hashtable<String, String> data) throws Exception {
		APP_LOGS.info("===========Sonim_Warranty_04============");		
		startAdbLog("Sonim_Warranty_04");
		recordVideo("Sonim_Warranty_04");
		clearRecentApps();
		enter_Country_Name_Press_SubmitBtn();	
		test.log(LogStatus.PASS, "TestCase Passed");
	}

	@Test(priority=5,dataProvider="Sonim_Warranty_Data_Provider", dataProviderClass=DataProviders.class)
	public void XP5s_Validate_FirstName_Field_05(Hashtable<String, String> data) throws Exception {

		APP_LOGS.info("===========Sonim_Warranty_05============");		
		startAdbLog("Sonim_Warranty_05");
		recordVideo("Sonim_Warranty_05");
		clearRecentApps();			
		enter_Fname_Press_SubmitBtn();
		test.log(LogStatus.PASS, "TestCase Passed");
	}		

	@Test(priority=6,dataProvider="Sonim_Warranty_Data_Provider", dataProviderClass=DataProviders.class)
	public void XP5s_Validate_SecondtName_Field_06(Hashtable<String, String> data) throws Exception 
	{	
		APP_LOGS.info("===========Sonim_Warranty_06============");		
		startAdbLog("Sonim_Warranty_06");
		recordVideo("Sonim_Warranty_06");
		clearRecentApps();			
		enter_Sname_Press_SubmitBtn();		
		test.log(LogStatus.PASS, "TestCase Passed");
	}

	@Test(priority=7,dataProvider="Sonim_Warranty_Data_Provider", dataProviderClass=DataProviders.class)
	public void XP5s_Validate_CompanyName_Field_07(Hashtable<String, String> data) throws Exception 
	{
		APP_LOGS.info("===========Sonim_Warranty_07============");		
		startAdbLog("Sonim_Warranty_07");
		recordVideo("Sonim_Warranty_07");
		clearRecentApps();		
		enter_CompanyName_Press_SubmitBtn();	
		test.log(LogStatus.PASS, "TestCase Passed");
	}		

	@Test(priority=8,dataProvider="Sonim_Warranty_Data_Provider", dataProviderClass=DataProviders.class)
	public void XP5s_Validate_AddressField_1_Field_08(Hashtable<String, String> data) throws Exception
	{
		APP_LOGS.info("===========Sonim_Warranty_08============");		
		startAdbLog("Sonim_Warranty_08");
		recordVideo("Sonim_Warranty_08");
		clearRecentApps();			
		enter_Address1_Press_SubmitBtn();	
		test.log(LogStatus.PASS, "TestCase Passed");
	}	

	@Test(priority=9,dataProvider="Sonim_Warranty_Data_Provider", dataProviderClass=DataProviders.class)
	public void XP5s_Validate_City_Field_09(Hashtable<String, String> data) throws Exception 
	{
		APP_LOGS.info("===========Sonim_Warranty_09============");		
		startAdbLog("Sonim_Warranty_09");
		recordVideo("Sonim_Warranty_09");
		clearRecentApps();			
		enter_City_Press_SubmitBtn();		
		test.log(LogStatus.PASS, "TestCase Passed");
	}	

	@Test(priority=10,dataProvider="Sonim_Warranty_Data_Provider", dataProviderClass=DataProviders.class)
	public void XP5s_Validate_StateName_Field_10(Hashtable<String, String> data) throws Exception
	{
		APP_LOGS.info("===========Sonim_Warranty_10============");		
		startAdbLog("Sonim_Warranty_10");
		recordVideo("Sonim_Warranty_10");
		clearRecentApps();			
		enter_StateName_PressSubmitBtn();
		test.log(LogStatus.PASS, "TestCase Passed");
	}	

	@Test(priority=11,dataProvider="Sonim_Warranty_Data_Provider", dataProviderClass=DataProviders.class)
	public void XP5s_Validate_ZipCode_Field_11(Hashtable<String, String> data) throws Exception 
	{
		APP_LOGS.info("===========Sonim_Warranty_11============");
		startAdbLog("Sonim_Warranty_11");
		recordVideo("Sonim_Warranty_11");
		clearRecentApps();			
		enter_ZipCode_PressSubmitbtn();		
		test.log(LogStatus.PASS, "TestCase Passed");
	}	

	@Test(priority=12,dataProvider="Sonim_Warranty_Data_Provider", dataProviderClass=DataProviders.class)
	public void XP5s_Validate_Email_Field_12(Hashtable<String, String> data) throws Exception 
	{
		APP_LOGS.info("===========Sonim_Warranty_12============");		
		startAdbLog("Sonim_Warranty_12");
		recordVideo("Sonim_Warranty_12");
		clearRecentApps();			
		enter_EmailId_Press_SubmitBtn();		
		test.log(LogStatus.PASS, "TestCase Passed");
	}	

	@Test(priority=13,dataProvider="Sonim_Warranty_Data_Provider", dataProviderClass=DataProviders.class)
	public void XP5s_Validate_PhoneNumber_Field_13(Hashtable<String, String> data) throws Exception 
	{
		APP_LOGS.info("===========Sonim_Warranty_13============");		
		startAdbLog("Sonim_Warranty_13");
		recordVideo("Sonim_Warranty_13");
		clearRecentApps();
		enter_PhoneNum_Press_SubmitBtn();
		test.log(LogStatus.PASS, "TestCase Passed");
	}	

	@Test(priority=14,dataProvider="Sonim_Warranty_Data_Provider", dataProviderClass=DataProviders.class)
	public void XP5s_Validate_All_Field_14(Hashtable<String, String> data) throws Exception
	{
		APP_LOGS.info("===========Sonim_Warranty_14============");
		startAdbLog("Sonim_Warranty_14");
		recordVideo("Sonim_Warranty_14");
		clearRecentApps();
		enter_PurchaseDate_Press_SubmitBtn();
		clearRecentApps();
		enter_DealerName_Press_SubmitBtn();
		clearRecentApps();
		addInvoice();
		clickOnReset();
		test.log(LogStatus.PASS, "TestCase Passed");
	}


}
