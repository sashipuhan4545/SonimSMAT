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
import org.openqa.selenium.support.PageFactory;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.LogStatus;

import com.xp8.util.DataProviders;
import com.xp8.util.ExcelConstants;
import com.xp8.util.ExcelReader;
import com.xp8.util.Locators_BaseUtil;
import com.xp8.util.Locators_PhoneDialer;
import com.xp8.util.XP8_PhoneDialer_Util;

import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class XP8_PhoneDialer_Test extends XP8_PhoneDialer_Util{


	public ExcelReader excel;
	
	
	@BeforeSuite
	public void beforeSuite() throws FileNotFoundException, InterruptedException, IOException, ParseException 
	{
		extent = new ExtentReports("src/test/resources/extentreport/XP8_PhoneDialer_Test.html", true); //Provide Desired Report Directory Location and Name
		fetch_Devices_Details();
	} 

	@BeforeMethod()
	public  void beforeMethod(Method method) 
	{
		test = extent.startTest( (this.getClass().getSimpleName() +" :: "+  method.getName()),method.getName()); //Test Case Start Here
		test.assignAuthor("Tejavathi D"); //Test Script Author Name
		extent.loadConfig(new File(System.getProperty("user.dir")+"//ReportsConfig.xml"));
		extent.addSystemInfo("Appium", "1.1").addSystemInfo("Environment", "TEST");
	}

	@AfterMethod()
	public void tearDown(ITestResult result,Method method) throws IOException, InterruptedException
	{
		if(result.getStatus()==ITestResult.FAILURE)
		{	String screenshot_path=captureScreenshot(method.getName());
		String image= test.addScreenCapture(screenshot_path);		
		test.log(LogStatus.FAIL,result.getThrowable());			
		test.log(LogStatus.FAIL, "screenshot for failure: "+test.addScreenCapture(image));
		// clear screen
		clearRecentApps();
		}
		extent.endTest(test);
		extent.flush();
	}


	@BeforeTest
	public void setupSys() throws MalformedURLException, InterruptedException, FileNotFoundException, AWTException{
		properties=loadDriverAndProperties();
		Locators_PhoneDialer loc=new Locators_PhoneDialer(aDriver);
		Locators_BaseUtil loc1 = new Locators_BaseUtil(aDriver);	
		PageFactory.initElements(new AppiumFieldDecorator(aDriver),loc);
		PageFactory.initElements(new AppiumFieldDecorator(aDriver),loc1);
		excel=new ExcelReader(ExcelConstants.XP5S_XL_PATH);	
	}
	//=========================================================Test Scripts================================================================
	@Test(priority=1,dataProvider="XP8_PhoneDialer", dataProviderClass=DataProviders.class)
	public void XP8_PhoneDialer_01_Validate_Phone_Application_Launch(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException
	{
		APP_LOGS.info("===========XP8_PhoneDialer_01============");
		startAdbLog("XP8_PhoneDialer_01");
		//("XP8_PhoneDialer_01");
		launch_an_app("phone");
		beforeExecution();
		validatePhoneAppLaunch();
	}

	@Test(priority=2,dataProvider="XP8_PhoneDialer", dataProviderClass=DataProviders.class)
	public void XP8_PhoneDialer_02_Validate_Phone_App_Home_Page_Without_call_Log(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException
	{
		APP_LOGS.info("===========XP8_PhoneDialer_02============");
		startAdbLog("XP8_PhoneDialer_02");
		//("XP8_PhoneDialer_02");
		launch_an_app("phone");
		validatePhoneAppHomePage();
	}

	@Test(priority=3,dataProvider="XP8_PhoneDialer", dataProviderClass=DataProviders.class)
	public void XP8_PhoneDialer_03_Validate_Make_A_Call(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException
	{
		APP_LOGS.info("===========XP8_PhoneDialer_02============");
		startAdbLog("XP8_PhoneDialer_03");
		
		launch_an_app("phone");
		validateMakeACall();
	}

	@Test(priority=4,dataProvider="XP8_PhoneDialer", dataProviderClass=DataProviders.class)
	public void XP8_PhoneDialer_04_Validate_Call_From_CallLog(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException
	{
		APP_LOGS.info("===========XP8_PhoneDialer_04============");
		startAdbLog("XP8_PhoneDialer_04");
		//("XP8_PhoneDialer_04");
		launch_an_app("phone");
		validateCallFromCallLog();
	}

	@Test(priority=5,dataProvider="XP8_PhoneDialer", dataProviderClass=DataProviders.class)
	public void XP8_PhoneDialer_05_Validate_Add_A_Contact_In_Contact_Page(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException
	{
		APP_LOGS.info("===========XP8_PhoneDialer_05============");
		startAdbLog("XP8_PhoneDialer_05");
		//("XP8_PhoneDialer_05");
		launch_an_app("phone");
		validateAddAContactFromContactPage(data.get("name"), data.get("phone"));
	}
	
	@Test(priority=6,dataProvider="XP8_PhoneDialer", dataProviderClass=DataProviders.class)
	public void XP8_PhoneDialer_06_Validate_Add_Contacts_From_Contacts_Page(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException
	{
		APP_LOGS.info("===========XP8_PhoneDialer_06============");
		startAdbLog("XP8_PhoneDialer_06");
		//("XP8_PhoneDialer_06");
		launch_an_app("phone");
		minWait();
		validateAddContactFromContactsPage(data.get("name"), data.get("phone"));
	}

	@Test(priority=7,dataProvider="XP8_PhoneDialer", dataProviderClass=DataProviders.class)
	public void XP8_PhoneDialer_07_Validate_Create_New_Contact_From_Call_Log(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException
	{
		APP_LOGS.info("===========XP8_PhoneDialer_07============");
		startAdbLog("XP8_PhoneDialer_07");
		//("XP8_PhoneDialer_07");
		launch_an_app("phone");
		validateCreateNewContactFromCallLog(data.get("name"));
	}

	@Test(priority=8,dataProvider="XP8_PhoneDialer", dataProviderClass=DataProviders.class)
	public void XP8_PhoneDialer_08_Validate_Create_New_Contact_From_Dialpad(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException
	{
		APP_LOGS.info("===========XP8_PhoneDialer_08============");
		startAdbLog("XP8_PhoneDialer_08");
		//("XP8_PhoneDialer_08");
		launch_an_app("phone");
		validateCreateNewContactFromDialpad(data.get("phone"),data.get("name"));
	}

	@Test(priority=9,dataProvider="XP8_PhoneDialer", dataProviderClass=DataProviders.class)
	public void XP8_PhoneDialer_09_Validate_Add_To_A_Contact_From_Call_Log(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException
	{
		APP_LOGS.info("===========XP8_PhoneDialer_09============");
		startAdbLog("XP8_PhoneDialer_09");
		//("XP8_PhoneDialer_09");
		launch_an_app("phone");
		validateAddToContactFromCallLog(data.get("name"));
	}

	@Test(priority=10,dataProvider="XP8_PhoneDialer", dataProviderClass=DataProviders.class)
	public void XP8_PhoneDialer_10_Validate_Add_To_A_Contact_From_Dialpad(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException
	{
		APP_LOGS.info("===========XP8_PhoneDialer_10============");
		startAdbLog("XP8_PhoneDialer_10");
		//("XP8_PhoneDialer_10");
		launch_an_app("phone");
		validateAddToContactFromDialpad(data.get("phone"), data.get("name"));
	}

	@Test(priority=11,dataProvider="XP8_PhoneDialer", dataProviderClass=DataProviders.class)
	public void XP8_PhoneDialer_11_Validate_Send_Message_From_call_Log(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException
	{
		APP_LOGS.info("===========XP8_PhoneDialer_11============");
		startAdbLog("XP8_PhoneDialer_11");
		//("XP8_PhoneDialer_11");
		launch_an_app("phone");
		validateSendMessageFromCallLog(data.get("textMessage"));
	}

	@Test(priority=12,dataProvider="XP8_PhoneDialer", dataProviderClass=DataProviders.class)
	public void XP8_PhoneDialer_12_Validate_Send_Message_From_Dialpad(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException
	{
		APP_LOGS.info("===========XP8_PhoneDialer_12============");
		startAdbLog("XP8_PhoneDialer_12");
		//("XP8_PhoneDialer_12");
		launch_an_app("phone");
		validateSendMessageFromDialpad(data.get("phone"), data.get("textMessage"));
	}
	
	@Test(priority=13,dataProvider="XP8_PhoneDialer", dataProviderClass=DataProviders.class)
	public void XP8_PhoneDialer_13_Validate_Call_Details_With_outgoing_Call_Log(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException
	{
		APP_LOGS.info("===========XP8_PhoneDialer_13============");
		startAdbLog("XP8_PhoneDialer_13");
		//("XP8_PhoneDialer_13");
		launch_an_app("phone");
		navigateToCallDetails(Locators_PhoneDialer.addedContactCallLog);
		minWait();
		validateCallDetailsWithOutgoingCallLog();
	}

	@Test(priority=14,dataProvider="XP8_PhoneDialer", dataProviderClass=DataProviders.class)
	public void XP8_PhoneDialer_14_Validate_Block_And_unblock_Number(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException
	{
		APP_LOGS.info("===========XP8_PhoneDialer_14============");
		startAdbLog("XP8_PhoneDialer_14");
		//("XP8_PhoneDialer_14");
		launch_an_app("phone");
		navigateToCallDetails(Locators_PhoneDialer.addedContactCallLog);
		minWait();
		blockAndUnblockNumber();
		test.log(LogStatus.PASS, "Test case Status is PASS");
	}

	@Test(priority=15,dataProvider="XP8_PhoneDialer", dataProviderClass=DataProviders.class)
	public void XP8_PhoneDialer_15_Validate_Copy_number(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException
	{
		APP_LOGS.info("===========XP8_PhoneDialer_15============");
		startAdbLog("XP8_PhoneDialer_15");
		//("XP8_PhoneDialer_15");
		launch_an_app("phone");
		navigateToCallDetails(Locators_PhoneDialer.addedContactCallLog);
		minWait();
		validateCopyNumber();
	}

	@Test(priority=16,dataProvider="XP8_PhoneDialer", dataProviderClass=DataProviders.class)
	public void XP8_PhoneDialer_16_Validate_Edit_Number_Before_Call(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException
	{
		APP_LOGS.info("===========XP8_PhoneDialer_16============");
		startAdbLog("XP8_PhoneDialer_16");
		//("XP8_PhoneDialer_16");
		launch_an_app("phone");
		navigateToCallDetails(Locators_PhoneDialer.addedContactCallLog);
		minWait();
		validateEditBeforeCall(data.get("phone"));
	}

	@Test(priority=17,dataProvider="XP8_PhoneDialer", dataProviderClass=DataProviders.class)
	public void XP8_PhoneDialer_17_Validate_Delete(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException
	{
		APP_LOGS.info("===========XP8_PhoneDialer_17============");
		startAdbLog("XP8_PhoneDialer_17");
		//("XP8_PhoneDialer_17");
		launch_an_app("phone");
		navigateToCallDetails(Locators_PhoneDialer.editedNumber);
		minWait();
		validateDelete();
	}

	@Test(priority=18,dataProvider="XP8_PhoneDialer", dataProviderClass=DataProviders.class)
	public void XP8_PhoneDialer_18_Validate_Add_A_Favorite(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException
	{
		APP_LOGS.info("===========XP8_PhoneDialer_18============");
		startAdbLog("XP8_PhoneDialer_18");
		//("XP8_PhoneDialer_18");
		launch_an_app("phone");
		validateAddFavorite();
	}

	@Test(priority=19,dataProvider="XP8_PhoneDialer", dataProviderClass=DataProviders.class)
	public void XP8_PhoneDialer_19_Validate_Call_From_Favorites(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException
	{
		APP_LOGS.info("===========XP8_PhoneDialer_19============");
		startAdbLog("XP8_PhoneDialer_19");
		//("XP8_PhoneDialer_19");
		launch_an_app("phone");
		validateCallFromFavorites();
	}

	@Test(priority=20,dataProvider="XP8_PhoneDialer", dataProviderClass=DataProviders.class)
	public void XP8_PhoneDialer_20_Validate_Remove_From_Favorites(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException
	{
		APP_LOGS.info("===========XP8_PhoneDialer_20============");
		startAdbLog("XP8_PhoneDialer_20");
		//("XP8_PhoneDialer_20");
		launch_an_app("phone");
		validateRemoveFromFavorite();
	}

	@Test(priority=21,dataProvider="XP8_PhoneDialer", dataProviderClass=DataProviders.class)
	public void XP8_PhoneDialer_21_Validate_Clear_Frequents(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException
	{
		APP_LOGS.info("===========XP8_PhoneDialer_21============");
		startAdbLog("XP8_PhoneDialer_21");
		//("XP8_PhoneDialer_21");
		launch_an_app("phone");
		validateClearFrequents();
	}

	@Test(priority=22,dataProvider="XP8_PhoneDialer", dataProviderClass=DataProviders.class)
	public void XP8_PhoneDialer_22_Validate_Import_Export_Contacts(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException
	{
		APP_LOGS.info("===========XP8_PhoneDialer_22============");
		startAdbLog("XP8_PhoneDialer_22");
		//("XP8_PhoneDialer_22");
		launch_an_app("phone");
		validateImportExportContacts(data.get("contactName"));
	}

	@Test(priority=23,dataProvider="XP8_PhoneDialer", dataProviderClass=DataProviders.class)
	public void XP8_PhoneDialer_23_Validate_Settings_And_Display_Options(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException
	{
		APP_LOGS.info("===========XP8_PhoneDialer_23============");
		startAdbLog("XP8_PhoneDialer_23");
		//("XP8_PhoneDialer_23");
		launch_an_app("phone");
		minWait();
		validateSettingsAndDisplayOptions();
		test.log(LogStatus.PASS, "Test case Status is PASS");
	}

	@Test(priority=24,dataProvider="XP8_PhoneDialer", dataProviderClass=DataProviders.class)
	public void XP8_PhoneDialer_24_Validate_Sounds_And_Vibration(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException
	{
		APP_LOGS.info("===========XP8_PhoneDialer_24============");
		startAdbLog("XP8_PhoneDialer_24");
		//("XP8_PhoneDialer_24");
		launch_an_app("phone");
		navigateToSettingsAndElement(Locators_PhoneDialer.soundsAndVibration);
		minWait();
		validatePhoneRingtone();
		minWait();
		validateSoundsAndVibrationSubOptions();
		minWait();
		validatePresenceOfDialpadToneLength();
		test.log(LogStatus.PASS, "Test case Status is PASS");
	}

	@Test(priority=25,dataProvider="XP8_PhoneDialer", dataProviderClass=DataProviders.class)
	public void XP8_PhoneDialer_25_Validate_Quick_Responses(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException
	{
		APP_LOGS.info("===========XP8_PhoneDialer_25============");
		startAdbLog("XP8_PhoneDialer_25");
		//("XP8_PhoneDialer_25");
		launch_an_app("phone");
		navigateToSettingsAndElement(Locators_PhoneDialer.quickResponseOpt);
		minWait();
		validateQuickResponses(data.get("textMessage"));
		test.log(LogStatus.PASS, "Test case Status is PASS");
	}

	@Test(priority=26,dataProvider="XP8_PhoneDialer", dataProviderClass=DataProviders.class)
	public void XP8_PhoneDialer_26_Validate_Speed_Dial_Settings(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException
	{
		APP_LOGS.info("===========XP8_PhoneDialer_26============");
		startAdbLog("XP8_PhoneDialer_26");
		//("XP8_PhoneDialer_26");
		launch_an_app("phone");
		navigateToSettingsAndElement(Locators_PhoneDialer.speedDialSettingsOpt);
		minWait();
		validateSpeedDialSettings(data.get("phone"), data.get("name"));
		test.log(LogStatus.PASS, "Test case Status is PASS");
	}


	@Test(priority=27,dataProvider="XP8_PhoneDialer", dataProviderClass=DataProviders.class)
	public void XP8_PhoneDialer_27_Validate_keepEditing_and_Discard(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException
	{
		APP_LOGS.info("===========XP8_PhoneDialer_27============");
		startAdbLog("XP8_PhoneDialer_27");
		//("XP8_PhoneDialer_27");
		launch_an_app("phone");
		minWait();
		validateKeepEditingAndDiscard(data.get("phone"));
		test.log(LogStatus.PASS, "Test case Status is PASS");
	}

	@Test(priority=28,dataProvider="XP8_PhoneDialer", dataProviderClass=DataProviders.class)
	public void XP8_PhoneDialer_28_Validate_Cancel(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException
	{
		APP_LOGS.info("===========XP8_PhoneDialer_28============");
		startAdbLog("XP8_PhoneDialer_28");
		//("XP8_PhoneDialer_28");
		launch_an_app("phone");
		minWait();
		validateCancel();
	}
	
	@Test(priority=29,dataProvider="XP8_PhoneDialer", dataProviderClass=DataProviders.class)
	public void XP8_PhoneDialer_29_Validate_Call_History(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException
	{
		APP_LOGS.info("===========XP8_PhoneDialer_29============");
		startAdbLog("XP8_PhoneDialer_29");
		//("XP8_PhoneDialer_29");
		launch_an_app("phone");
		navigateTocallHistory();
		minWait();
		validateCallHistory();
		test.log(LogStatus.PASS, "Test case Status is PASS");
	}
	
	@Test(priority=30,dataProvider="XP8_PhoneDialer", dataProviderClass=DataProviders.class)
	public void XP8_PhoneDialer_30_Validate_Call_History_Settings(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException
	{
		APP_LOGS.info("===========XP8_PhoneDialer_29============");
		startAdbLog("XP8_PhoneDialer_29");
		//("XP8_PhoneDialer_29");
		launch_an_app("phone");
		navigateTocallHistory();
		minWait();
		validateSearchCallLog(data.get("name"));
		minWait();
		validateClearCallHistory();
		test.log(LogStatus.PASS, "Test case Status is PASS");
	}

}
