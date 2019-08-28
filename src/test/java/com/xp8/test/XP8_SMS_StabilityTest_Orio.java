package com.xp8.test;

import java.awt.AWTException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.json.simple.parser.ParseException;
import org.openqa.selenium.support.PageFactory;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.android.sdklib.internal.repository.updater.SettingsController.Settings;
import com.google.common.collect.ImmutableMap;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTestInterruptedException;
import com.relevantcodes.extentreports.LogStatus;
import com.xp5S.util.BaseUtil;
import com.xp5S.util.GetMethods;
import com.xp5S.util.appiumService;
import com.xp8.util.DataProviders;
import com.xp8.util.ExcelConstants;
import com.xp8.util.ExcelReader;
import com.xp8.util.Locators_BaseUtil;
import com.xp8.util.Locators_SMS_DeviceStability;
import com.xp8.util.XP8_SMS_Stability_Util_orio;

import application.AllQA;
import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidKeyCode;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class XP8_SMS_StabilityTest_Orio extends XP8_SMS_Stability_Util_orio{
	
	public ExcelReader excel;
	Properties properties;
	public static ExtentTestInterruptedException testexception;
	public int  itr =AllQA.NUMOFCALLS;
	boolean value = false;

	@BeforeSuite
	public void beforeSuite() throws FileNotFoundException, InterruptedException, IOException, ParseException  
	{
		extent = new ExtentReports("src/test/resources/extentreport/XP8_SMS_Stability_Orio_TestReport.html", true); //Provide Desired Report Directory Location and Name
		extent.loadConfig(new File(System.getProperty("user.dir")+"//ReportsConfig.xml"));
		fetch_Devices_Details();	
	}

	@BeforeMethod()
	public  void beforeMethod(Method method) 
	{
		test = extent.startTest( (this.getClass().getSimpleName() +" :: "+  method.getName()),method.getName()); //Test Case Start Here
		test.assignAuthor(""); //Test Script Author Name
		String BuildNumber=BaseUtil.fetchDeviceProperty("adb shell getprop ro.build.id");
		test.assignCategory("Build #:"+BuildNumber);
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
		{	
		String screenshot_path=captureScreenshot(method.getName());
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
	
	


	@BeforeTest
	public void setupSys() throws MalformedURLException, InterruptedException, FileNotFoundException, AWTException{
		properties=loadDriverAndProperties();
		Locators_SMS_DeviceStability loc=new Locators_SMS_DeviceStability(aDriver);
		Locators_BaseUtil loc1 = new Locators_BaseUtil(aDriver);	
		PageFactory.initElements(new AppiumFieldDecorator(aDriver),loc);
		PageFactory.initElements(new AppiumFieldDecorator(aDriver),loc1);
		excel=new ExcelReader(ExcelConstants.XP5S_XL_PATH);	
	}
	
	
	@Test(priority=1,dataProvider="XP8_Stability", dataProviderClass=DataProviders.class)
	public void XP8_TC_001_Stability_Validate_Send_MO_SMS(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException
	{
		
		
		
		APP_LOGS.info("===========XP8_Stability_001============");
		startAdbLog("XP8_Stability_001");
		SoftAssert sa1 = new SoftAssert();
		
			for(int i=1; i<=itr;i++) {
				clearRecentApps();
				
				if (p_b_No.contains("-10.")||p_b_No.contains("-00.")) {
					
					launch_APP(Locators_SMS_DeviceStability.messaging);
					//clearSMSPermissions();
					create_NewSMS(refNum, data.get("typeMessage"));
					clickOn_Send();
					validate_SentMessage(i,sa1) ;
					delete_SMS();
					
				} else if(p_b_No.contains("-15.")) {
					System.out.println("Executing...");
					launch_APP(Locators_SMS_DeviceStability.MessagePlus);
					launch_APP(Locators_SMS_DeviceStability.vzwSettings);
					precondition_start_msg_vzw();
					clearRecentApps();
					launch_APP(Locators_SMS_DeviceStability.MessagePlus);
					//clearSMSPermissions_O();
					create_NewSMS_O(refNum,data.get("typeMessage"),pryNum);
					clickOn_Send_O();
					validate_SentMessage_O(i,sa1) ;
					aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
					minWait();
					aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
					minWait();
					delete_SMS_O();
				}
				else if(p_b_No.contains("-26.")||p_b_No.contains("-29.")||p_b_No.contains("-11.")||p_b_No.contains("-31.")||p_b_No.contains("-18.")||p_b_No.contains("-12.")){
					System.out.println("Executing SL part");
					launch_APP(Locators_SMS_DeviceStability.messages);
					 create_NewSMS_SL(refNum, data.get("typeMessage"));
					 clickOn_Send_SL();
					 validate_SentMessage_SL(i,sa1);
					 delete_SMS_SL();
						
					 
				}
				else {
					System.out.println("Executing Else part");
					launch_APP(Locators_SMS_DeviceStability.messaging);
					clearSMSPermissions();
					create_NewSMS(refNum, data.get("typeMessage"));
					clickOn_Send();
					validate_SentMessage(i,sa1) ;
				}
			}
			sa1.assertAll();
	}

	
	@Test(priority=2,dataProvider="XP8_Stability", dataProviderClass=DataProviders.class)
	public void XP8_TC_002_Stability_Validate_MO_SMS_Maximum_NumberOfCharacters(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException
	{
		APP_LOGS.info("===========XP8_Stability_002============");
		startAdbLog("XP8_Stability_002");
		SoftAssert sa2 = new SoftAssert();
		
		WebDriverWait wait=new WebDriverWait(aDriver, 30);
		
		for(int i=1; i<=itr;i++) {
			
			if (p_b_No.contains("-10.")||p_b_No.contains("-00.")) {
				launch_APP(Locators_SMS_DeviceStability.messaging);
			//	wait.until(ExpectedConditions.visibilityOf(Locators_SMS_DeviceStability.createSMS)).click();
			//	navigateTo_NewSMS_O();
				create_NewSMS(refNum, data.get("typeMessage3"));
				validate_CharacterAndPageNumber(Locators_SMS_DeviceStability.zero_Characters_FirstPage,data.get("expectedChar&PageNum3"),sa2);
				clickOn_Send();
				validate_SentMessage(i,sa2) ;
				delete_SMS();
			} else if(p_b_No.contains("-15.")) {
				launch_APP(Locators_SMS_DeviceStability.MessagePlus);	
				navigateTo_NewSMS_O();				
				create_NewSMS_O(refNum,data.get("typeMessage3"),pryNum);
				validate_CharacterAndPageNumber_O(Locators_SMS_DeviceStability.zero_Characters_FirstPage_O,data.get("expectedChar&PageNum3"),sa2);
				clickOn_Send_O();
				validate_SentMessage_O(i,sa2) ;
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
				minWait();
				delete_SMS_O();
			}
			else if(p_b_No.contains("-26.")||p_b_No.contains("-29.")||p_b_No.contains("-11.")||p_b_No.contains("-31.")||p_b_No.contains("-18.")||p_b_No.contains("-12.")){
				System.out.println("Executing SL part");
				launch_APP(Locators_SMS_DeviceStability.messages);
				 create_NewSMS_SL(refNum, data.get("typeMessage3"));
				 validate_CharacterAndPageNumber_SL((multi_Loc_Strategy(Locators_SMS_DeviceStability.validatingchrs_id_sl, Locators_SMS_DeviceStability.validatingchrs_sl, Locators_SMS_DeviceStability.validatingchrs_sl_indx, Locators_SMS_DeviceStability.validatingchrscls_sl, Locators_SMS_DeviceStability.validatingchrsid_sl, 1003, 1744)),data.get("expectedChar&PageNum3"),sa2);
				 clickOn_Send_SL();
				 validate_SentMessage_SL(i,sa2);
				 delete_SMS_SL();
					
				 
			}
			else {
				System.out.println("Executing Else part");
				launch_APP(Locators_SMS_DeviceStability.messaging);
				navigateTo_NewSMS_O();
				create_NewSMS(refNum, data.get("typeMessage3"));
				validate_CharacterAndPageNumber(Locators_SMS_DeviceStability.zero_Characters_FirstPage,data.get("expectedChar&PageNum3"),i,sa2);
				clickOn_Send();
				validate_SentMessage(i,sa2) ;
				delete_SMS();
			}
		}
		sa2.assertAll();	
	}


	@Test(priority=3,dataProvider="XP8_Stability", dataProviderClass=DataProviders.class)
	public void XP8_TC_003_Stability_Validate_MT_SMS(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException
	{
		APP_LOGS.info("===========XP8_Stability_003============");
		startAdbLog("XP8_Stability_003");
		SoftAssert sa3 = new SoftAssert();
		clearRecentApps();
		for(int i=1; i<=itr;i++) {

			if (p_b_No.contains("-10.")||p_b_No.contains("-00.")) {
				launch_APP(Locators_SMS_DeviceStability.messaging);
				delete_SMS();
				sendSMS_fromRefDevice(data.get("typeMessage1"));
				validate_RecievedMessage(i,sa3);
				
			} else if(p_b_No.contains("-15.")) {
				
				launch_APP(Locators_SMS_DeviceStability.MessagePlus);
				sendSMS_fromRefDevice(data.get("typeMessage1"));
				/*navigateTo_NewSMS_O();				
				create_NewSMS_O(pryNum,data.get("typeMessage1"),refNum);
				clickOn_Send_O();*/
				validate_RecievedMessage_O(i,sa3);		
			}
			else if(p_b_No.contains("-26.")||p_b_No.contains("-29.")||p_b_No.contains("-11.")||p_b_No.contains("-31.")||p_b_No.contains("-18.")||p_b_No.contains("-12.")){
				System.out.println("Executing SL part");
				launch_APP(Locators_SMS_DeviceStability.messages);
				sendSMS_fromRefDevice(data.get("typeMessage1"));
				 validate_RecievedMessage_SL(i,sa3);
				 
					
				 
			}
			else {
				System.out.println("Executing Else part");
				launch_APP(Locators_SMS_DeviceStability.messaging);			
				navigateTo_NewSMS_O();
				create_NewSMS(pryNum, data.get("typeMessage1"));
				clickOn_Send();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
				validate_RecievedMessage_O(i,sa3);		
			}
		}
	}




}
