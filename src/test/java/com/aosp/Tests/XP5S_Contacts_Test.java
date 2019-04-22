package com.aosp.Tests;

import java.awt.AWTException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.util.Hashtable;
import java.util.Properties;

import org.json.simple.parser.ParseException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.PageFactory;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aosp.Utils.DataProviders;
import com.aosp.Utils.Locators_Contacts;
import com.graphics.gui.JsonFileReaderAndWriter;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.ExtentTestInterruptedException;
import com.relevantcodes.extentreports.LogStatus;


import com.xp5S.util.ExcelConstants;
import com.xp5S.util.ExcelReader;
import com.xp5S.util.GetMethods;
import com.xp5S.util.Locators_BaseUtil;
import com.xp5S.util.appiumService;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class XP5S_Contacts_Test extends com.aosp.Utils.Contacts_Util{

	public ExcelReader excel;
	Properties properties;
	

	public static ExtentTestInterruptedException testexception;
	public AndroidDriver<AndroidElement> aDriver;

	@BeforeSuite
	public void beforeSuite() 
	{
		extent = new ExtentReports("src/test/resources/extentreport/XP5S_Contacts_TestReport.html", true); 
		//Provide Desired Report Directory Location and Name
		try {
			appiumService.TOTAL_NUM_OF_TESTCASES=GetMethods.TotalTestcase("XP5S", this.getClass());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	


	@BeforeMethod()
	public  void beforeMethod(Method method) throws InterruptedException, FileNotFoundException, IOException, ParseException 	
	{

		
		test = extent.startTest( (this.getClass().getSimpleName() +" :: "+  method.getName()),method.getName()); //Test Case Start Here
		test.assignAuthor(""); //Test Script Author Name
		extent.loadConfig(new File("src/test/resources/StorageFile/ReportsConfig.xml"));
		extent.addSystemInfo("Build #",JsonFileReaderAndWriter.primaryDevFirmwareReader())
		.addSystemInfo("Product",JsonFileReaderAndWriter.primaryDevModelReader())
		.addSystemInfo("Operator", JsonFileReaderAndWriter.primaryDevOperatorReader());
	}

	@AfterMethod()
	public void tearDown(ITestResult result,Method method) throws IOException , ParseException , InterruptedException
	{
		if(result.getStatus()==ITestResult.FAILURE)
		{			

			
			//aDriver.getContextHandles();
			String screenshot_path=captureScreenshot(method.getName());
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
		Locators_Contacts loc=new Locators_Contacts(aDriver);
	    Locators_BaseUtil loc1=new Locators_BaseUtil(aDriver);
		PageFactory.initElements(new AppiumFieldDecorator(getDriver()),loc);
	    PageFactory.initElements(new AppiumFieldDecorator(getDriver()),loc1);
		excel=new ExcelReader(ExcelConstants.XP5S_XL_PATH);
		//deleteIfContactsPresent();
	}
	//*****************************************************Test Scripts*******************************************************************
		@Test(priority=1,dataProvider="XP5S_Contacts", dataProviderClass=DataProviders.class)
		public void XP5S_Contacts_01_Validate_Contacts_Application_Launch(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException {

			APP_LOGS.info("===========XP5S_Contacts_01============");
			startAdbLog("XP5S_Contacts_01");
			recordVideo("XP5S_Contacts_01");
			launch_an_app("contacts");
			deleteIfContactsPresent();
			customWait(5000);
			validateContactsAppLaunch();
			test.log(LogStatus.PASS, "Test case status is Passed");		
		}

		@Test(priority=2,dataProvider="XP5S_Contacts", dataProviderClass=DataProviders.class)
		public void XP5S_Contacts_02_Validate_Contacts_HomePage_Without_Contacts(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException   {

			APP_LOGS.info("===========XP5S_Contacts_02============");
			startAdbLog("XP5S_Contacts_02");
			recordVideo("XP5S_Contacts_02");
			launch_an_app("contacts");
			validateContactsHomePage();
			test.log(LogStatus.PASS, "Test case status is Passed");		
		}

		@Test(priority=3,dataProvider="XP5S_Contacts", dataProviderClass=DataProviders.class)
		public void XP5S_Contacts_03_Add_Contacts_with_Min_Fields(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException{

			APP_LOGS.info("===========XP5S_Contacts_03============");
			
			startAdbLog("XP5S_Contacts_03");
			recordVideo("XP5S_Contacts_03");
			launch_an_app("contacts");
			minWait();
			addContactWithMinFields(Locators_Contacts.addContacts, data.get("Name"), data.get("Phone  Number"));
			customWait(2000);
			editContactWithMinFields(data.get("Name"),data.get("EditedName"));
			test.log(LogStatus.PASS, "Test case status is Passed");		
		}

		@Test(priority=4,dataProvider="XP5S_Contacts", dataProviderClass=DataProviders.class)
		public void XP5S_Contacts_04_Add_Contacts_with_All_Fields(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException , ParseException  {

			APP_LOGS.info("===========XP5S_Contacts_04============");
			startAdbLog("XP5S_Contacts_04");
			recordVideo("XP5S_Contacts_04");
			launch_an_app("contacts");
			minWait();
			addContactWithAllFields(data.get("Name prefix"), data.get("First name"), data.get("Middle name"), data.get("Last name"), data.get("Name suffix"), data.get("Phonetic last name"), data.get("Phonetic middle name"), data.get("Phonetic first name"), data.get("Nickname"), data.get("Company"), data.get("Title"), data.get("Phone "), data.get("SIP"), data.get("Email"), data.get("Address"), data.get("IM"), data.get("Website"), data.get("Notes"));
			customWait(2000);
			editContactWithAllFields(data.get("Name prefix"), data.get("editedNamePrefix"));
			test.log(LogStatus.PASS, "Test case status is Passed");		
		}

		@Test(priority=5,dataProvider="XP5S_Contacts", dataProviderClass=DataProviders.class)
		public void XP5S_Contacts_05_Validate_Call_From_Contacts(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException , ParseException  {

			APP_LOGS.info("===========XP5S_Contacts_05============");
			startAdbLog("XP5S_Contacts_05");
			recordVideo("XP5S_Contacts_05");
			launch_an_app("contacts");
			minWait();
			callFromContactsAndValidate(data.get("Name"));
			clearRecentApps();
			test.log(LogStatus.PASS, "Test case status is Passed");
		}

		@Test(priority=6,dataProvider="XP5S_Contacts", dataProviderClass=DataProviders.class)
		public void XP5S_Contacts_06_Validate_Send_Message(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException  {

			APP_LOGS.info("===========XP5S_Contacts_06============");
			startAdbLog("XP5S_Contacts_06");
			recordVideo("XP5S_Contacts_06");
			launch_an_app("contacts");
			minWait();
			sendMessageFromContacts(data.get("Name"), data.get("textMessage"));
			test.log(LogStatus.PASS, "Test case status is Passed");
		}

		@Test(priority=7,dataProvider="XP5S_Contacts", dataProviderClass=DataProviders.class)
		public void XP5S_Contacts_07_Validate_Add_And_Remove_Contacts_To_favorites(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException   {

			APP_LOGS.info("===========XP5S_Contacts_07============");
			startAdbLog("XP5S_Contacts_07");
			recordVideo("XP5S_Contacts_07");
			launch_an_app("contacts");
			minWait();
			addContactToFavorites(data.get("Name"));
			customWait(2000);
			removeContactFromFavorites(data.get("Name"));
			test.log(LogStatus.PASS, "Test case status is Passed");
		}

		@Test(priority=8,dataProvider="XP5S_Contacts", dataProviderClass=DataProviders.class)
		public void XP5S_Contacts_08_Validate_Share_Contact(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException , ParseException  {

			APP_LOGS.info("===========XP5S_Contacts_08============");
			startAdbLog("XP5S_Contacts_08");
			recordVideo("XP5S_Contacts_08");
			launch_an_app("contacts");
			minWait();
			validateShareOption(data.get("Name"));
			clearRecentApps();
			test.log(LogStatus.PASS, "Test case status is Passed");
		}
	 
		@Test(priority=9,dataProvider="XP5S_Contacts", dataProviderClass=DataProviders.class)
		public void XP5S_Contacts_09_Validate_Send_Contact(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException   {

			APP_LOGS.info("===========XP5S_Contacts_09============");
			startAdbLog("XP5S_Contacts_09");
			recordVideo("XP5S_Contacts_09");
			launch_an_app("contacts");
			minWait();
			validatesendContact(data.get("Name"),data.get("Phone"));
			test.log(LogStatus.PASS, "Test case status is Passed");
		}
		
		@Test(priority=10,dataProvider="XP5S_Contacts", dataProviderClass=DataProviders.class)
		public void XP5S_Contacts_10_Validate_CopyToSIM_And_CopyToPhone(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException , ParseException  {

			APP_LOGS.info("===========XP5S_Contacts_10============");
			startAdbLog("XP5S_Contacts_10");
			recordVideo("XP5S_Contacts_10");
			launch_an_app("contacts");
			minWait();
			validateCopyToSIMAndCopyToPhone(data.get("Name"));
			test.log(LogStatus.PASS, "Test case status is Passed");
		}

		@Test(priority=11,dataProvider="XP5S_Contacts", dataProviderClass=DataProviders.class)
		public void XP5S_Contacts_11_Validate_Presence_Of_Set_Ringtone(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException   {

			APP_LOGS.info("===========XP5S_Contacts_11============");
			startAdbLog("XP5S_Contacts_11");
			recordVideo("XP5S_Contacts_11");
			launch_an_app("contacts");
			minWait();
			validatePresenceOfSetRingtone(data.get("Name"));
			test.log(LogStatus.PASS, "Test case status is Passed");
		}

		@Test(priority=12,dataProvider="XP5S_Contacts", dataProviderClass=DataProviders.class)
		public void XP5S_Contacts_12_Validate_Presence_Of_All_Calls_to_voicemail_with_CheckBox_Status(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException   {

			APP_LOGS.info("===========XP5S_Contacts_12============");
			startAdbLog("XP5S_Contacts_12");
			recordVideo("XP5S_Contacts_12");
			launch_an_app("contacts");
			minWait();
			validateAllCallsToVoicemail(data.get("Name"));
			test.log(LogStatus.PASS, "Test case status is Passed");
		}

		@Test(priority=13,dataProvider="XP5S_Contacts", dataProviderClass=DataProviders.class)
		public void XP5S_Contacts_13_Validate_Link_And_Unlink(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException   {

			APP_LOGS.info("===========XP5S_Contacts_13============");
			startAdbLog("XP5S_Contacts_13");
			recordVideo("XP5S_Contacts_13");
			launch_an_app("contacts");
			minWait();
		    validateLinkAndUnlink(data.get("Name1"), data.get("Phone1"), data.get("Name2"), data.get("Phone2"));
			clearRecentApps();
		    test.log(LogStatus.PASS, "Test case status is Passed");
		}

		@Test(priority=14,dataProvider="XP5S_Contacts", dataProviderClass=DataProviders.class)
		public void XP5S_Contacts_14_Validate_Blocked_Numbers(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException   {

			APP_LOGS.info("===========XP5S_Contacts_14============");
			startAdbLog("XP5S_Contacts_14");
			recordVideo("XP5S_Contacts_14");
			launch_an_app("contacts");
			minWait();
			validateBlockedNumbersFeature(data.get("Phone"));
			test.log(LogStatus.PASS, "Test case status is Passed");
		}

			@Test(priority=15,dataProvider="XP5S_Contacts", dataProviderClass=DataProviders.class)
		public void XP5S_Contacts_15_Validate_Select_SelectAll_DeselectAll(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException   {

			APP_LOGS.info("===========XP5S_Contacts_15============");
			startAdbLog("XP5S_Contacts_15");
			recordVideo("XP5S_Contacts_15");
			launch_an_app("contacts");
			minWait();
			validateSelectAllAndDeselectAllfeature();
			test.log(LogStatus.PASS, "Test case status is Passed");
		}

		@Test(priority=16,dataProvider="XP5S_Contacts", dataProviderClass=DataProviders.class)
		public void XP5S_Contacts_16_Validate_Settings(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException   {

			APP_LOGS.info("===========XP5S_Contacts_16============");
			startAdbLog("XP5S_Contacts_16");
			recordVideo("XP5S_Contacts_16");
			launch_an_app("contacts");
			minWait();
			validateSettings();
			test.log(LogStatus.PASS, "Test case status is Passed");
		}

		@Test(priority=17,dataProvider="XP5S_Contacts", dataProviderClass=DataProviders.class)
		public void XP5S_Contacts_17_Validate_Add_Contact_From_Dialer(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException   {

			APP_LOGS.info("===========XP5S_Contacts_17============");
			startAdbLog("XP5S_Contacts_17");
			recordVideo("XP5S_Contacts_17");
			minWait();
			validateAddContactFromDialer(data.get("Name"), data.get("Phone"));
			test.log(LogStatus.PASS, "Test case status is Passed");
		}

		@Test(priority=18,dataProvider="XP5S_Contacts", dataProviderClass=DataProviders.class)
		public void XP5S_Contacts_18_Validate_Cancel(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException   {

			APP_LOGS.info("===========XP5S_Contacts_19============");
			startAdbLog("XP5S_Contacts_18");
			recordVideo("XP5S_Contacts_18");
			launch_an_app("contacts");
			minWait();
			validateCancel(data.get("Name"));
			test.log(LogStatus.PASS, "Test case status is Passed");
			clearRecentApps();
		}

		@Test(priority=19,dataProvider="XP5S_Contacts", dataProviderClass=DataProviders.class)
		public void XP5S_Contacts_19_Validate_Keep_Editing_And_Discard(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException   {

			APP_LOGS.info("===========XP5S_Contacts_19============");
			startAdbLog("XP5S_Contacts_19");
			recordVideo("XP5S_Contacts_19");
			launch_an_app("contacts");
			minWait();
	        validateKeepEditingAndDiscardOption();
			test.log(LogStatus.PASS, "Test case status is Passed");
		}

		@Test(priority=20,dataProvider="XP5S_Contacts", dataProviderClass=DataProviders.class)
		public void XP5S_Contacts_20_Validate_Import_Export(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException  {

			APP_LOGS.info("===========XP5S_Contacts_20============");
			startAdbLog("XP5S_Contacts_20");
			recordVideo("XP5S_Contacts_20");
			launch_an_app("contacts");
			minWait();
	        validateImportExport(data.get("Name"));
			test.log(LogStatus.PASS, "Test case status is Passed");
		}

		@Test(priority=21,dataProvider="XP5S_Contacts", dataProviderClass=DataProviders.class)
		public void XP5S_Contacts_21_Validate_Delete(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException   {

				APP_LOGS.info("===========XP5S_Contacts_21============");
				startAdbLog("XP5S_Contacts_21");
				recordVideo("XP5S_Contacts_21");
				launch_an_app("contacts");
				minWait();
		        validateDeleteFeature(data.get("Name1"), data.get("Name2"), data.get("Name3"));
				test.log(LogStatus.PASS, "Test case status is Passed");
			    clearRecentApps();
			}
}


