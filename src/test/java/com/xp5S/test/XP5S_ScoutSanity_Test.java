/*------------------------------------------------PRECONDITION-------------------------------------------------------------------
 * 1. Device shouldn't be warranty registered in sonim cloud.
 * 2. App updater should be restricted to only one apk.(probably create a update specific to device or enterprise.)  need to be automated
 * 3. Link Scout, Sonim Care and App Updater to test.sonimcloud.com
 * 4.Safeguard shouldn't be activated.
 * 
 * 
 * 
 */

package com.xp5S.test;

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
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.graphics.gui.JsonFileReaderAndWriter;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.ExtentTestInterruptedException;
import com.relevantcodes.extentreports.LogStatus;
import com.xp5S.util.BaseUtil;
import com.xp5S.util.DataProviders;
import com.xp5S.util.ExcelConstants;
import com.xp5S.util.ExcelReader;
import com.xp5S.util.Locators_ContactTransfer;
import com.xp5S.util.Locators_ProgrammableKey;
import com.xp5S.util.Locators_Sanity;
import com.xp5S.util.Locators_ScoutSanity;
import com.xp5S.util.Locators_SonimCare;
import com.xp5S.util.XP5S_ScoutSanity_Util;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class XP5S_ScoutSanity_Test extends XP5S_ScoutSanity_Util {
	
	public ExcelReader excel;
	Properties properties;
	public static ExtentReports extent;
	public static ExtentTest test;
	public static ExtentTestInterruptedException testexception;
	public AndroidDriver<AndroidElement> aDriver;
	public static String XP5deviceModelNumber;
	
	@BeforeSuite
	public void beforeSuite() 
	{
		extent = new ExtentReports("src/test/resources/extentreport/XP5S_Scout_Sanity_TestReport.html", true); //Provide Desired Report Directory Location and Name
		
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
	
	@BeforeClass
	public void copyFilesToDevice() throws IOException, ParseException  {
		System.out.println("Here");
		Runtime.getRuntime().exec("adb push src/test/resources/contactTransferFile/CSV.csv /storage/emulated/0/Download");
		Runtime.getRuntime().exec("adb push src/test/resources/contactTransferFile/i475.mdb /storage/emulated/0/Download");
		Runtime.getRuntime().exec("adb push src/test/resources/contactTransferFile/MMS_Contact.vcf /storage/emulated/0/Download");
	}	
	
	@AfterMethod()
	public void tearDown(ITestResult result,Method method) throws IOException, ParseException , InterruptedException
	{
		if(result.getStatus()==ITestResult.FAILURE)
		{						
			String screenshot_path=captureScreenshot(method.getName());
			String image= test.addScreenCapture(screenshot_path);
			test.log(LogStatus.FAIL,result.getThrowable());
			test.log(LogStatus.FAIL, "screenshot for failure: "+test.addScreenCapture(image));
		}
		extent.endTest(test);
		extent.flush();
		
	}
	
	
	
	@BeforeTest
	public void setupSys() throws MalformedURLException, InterruptedException, FileNotFoundException, AWTException{
		properties=loadDriverAndProperties();
		Locators_ScoutSanity loc=new Locators_ScoutSanity(aDriver);
		PageFactory.initElements(new AppiumFieldDecorator(getDriver()),loc);
		excel=new ExcelReader(ExcelConstants.XP5S_XL_PATH);	
		XP5deviceModelNumber = BaseUtil.fetchDeviceProperty("adb shell getprop gsm.version.baseband");
	}
	
		@Test(priority=1,dataProvider="ScoutSanity_Data_Provider", dataProviderClass=DataProviders.class)
	public void XP5S_ScoutFunctional_01_Validate_navigate_ScoutApp_from_Menu(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException  
	{
		APP_LOGS.info("===========ScoutFunctional_01============");
		startAdbLog("ScoutFunctional_01");
		
		launch_application(data.get("AppName"));
		validateScoutAppLaunch();
		customWait(4000);
		test.log(LogStatus.PASS, "Test case status is Passed");
		
	}
	

	@Test(priority=2,dataProvider="ScoutSanity_Data_Provider", dataProviderClass=DataProviders.class)
	public void XP5S_ScoutFunctional_02_Validate_allTabsListExist(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException  
	{
		APP_LOGS.info("===========ScoutFunctional_02============");
		startAdbLog("ScoutFunctional_02");
		//("ScoutFunctional_02");
		launch_application(data.get("AppName"));
		validateAllTabsandList();
		customWait(4000);
		test.log(LogStatus.PASS, "Test case status is Passed");
	}
	

	@Test(priority=3,dataProvider="ScoutSanity_Data_Provider", dataProviderClass=DataProviders.class)
	public void XP5S_ScoutFunctional_03_Validate_App_Update_ViaMobile_or_Wifi(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException  
	{
		APP_LOGS.info("===========ScoutFunctional_03============");
		startAdbLog("ScoutFunctional_03");
		//("ScoutSanity_03");
		if(XP5deviceModelNumber.contains("ATT")) {
			test.log(LogStatus.SKIP, "Test case status is Skipped  because App Updater is not Available in ATT operator");
		}
		else{
			launch_application(data.get("AppName"));
			navigateApps(Locators_ScoutSanity.setUpTab, data.get("Application"));
			click_checkForUpdateBtn();
			installApp();
			appUpdaterSuccessAlert();
			validate_succesfull_appInstallation("Sonim BLE Connect");
			uninstallAppFromSettings("Sonim BLE Connect");	
		}
		customWait(4000);
		test.log(LogStatus.PASS, "Test case status is Passed");
	}
	
	
	@Test(priority=4,dataProvider="ScoutSanity_Data_Provider", dataProviderClass=DataProviders.class)
	public void XP5S_ScoutFunctional_04_Validate_Default_ProgrammableKey_Assign(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException  
	{
		APP_LOGS.info("===========ScoutFunctional_04============");
		startAdbLog("ScoutFunctional_04");
		//("ScoutFunctional_04");
	   if (XP5deviceModelNumber.contains("SL")) {
		   test.log(LogStatus.SKIP, "Test case status is Skipped  because Programmable Key is not Available in ATT operator");
		}
	   else {
		navigateprogrammablekey();
		validate_ProgrammableKeySummaryText();
		test.log(LogStatus.PASS, "Test case status is Passed");
	   }
	}
	
	@Test(priority=5,dataProvider="ScoutSanity_Data_Provider", dataProviderClass=DataProviders.class)
	public void XP5S_ScoutFunctional_05_Validate_ProgrammableKey_SummaryText_Of_AssignparticularApp(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException  
	{
		APP_LOGS.info("===========ScoutFunctional_05============");
		startAdbLog("ScoutFunctional_05");
		//("ScoutFunctional_05");
	   if (XP5deviceModelNumber.contains("SL")) {
		   test.log(LogStatus.SKIP, "Test case status is Skipped  because Programmable Key is not Available in ATT operator");
		}
	   else {
		   	navigateprogrammablekey();
			String applicationSet = Locators_ScoutSanity.summaryText.getText();
			//scrollToNoApplication();
			System.out.println(applicationSet);
			
			if (applicationSet.equalsIgnoreCase(data.get("Application"))) {
				test.log(LogStatus.PASS, "Testcase is passed since Programmable key is assigned to selected app");
				}
			else
				{
				navigateUsingText("Select PTT Key app");
				assign_app_to_ProgrammableKey(data.get("Application"));
				validateAppProgrammableKey(data.get("Application"));
				}	
		test.log(LogStatus.PASS, "Test case status is Passed");
	   		}
	
	}
	
	@Test(priority=6,dataProvider="ScoutSanity_Data_Provider", dataProviderClass=DataProviders.class)
	public void XP5S_ScoutFunctional_06_Validate_ProgrammableKey_successful_LaunchOf_ThirdPartyApp(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException  
	{
		APP_LOGS.info("===========ScoutFunctional_06============");
		startAdbLog("ScoutFunctional_06");
		//("ScoutFunctional_06");
	   if (XP5deviceModelNumber.contains("SL")) {
		   test.log(LogStatus.SKIP, "Test case status is Skipped  because Programmable Key is not Available in SL operator");
		}
	   else {
		   navigateprogrammablekey();
		   String applicationSet = Locators_ScoutSanity.summaryText.getText();
			//scrollToNoApplication();
			System.out.println(applicationSet);
			
			if (applicationSet.equalsIgnoreCase(data.get("Application"))) {
				test.log(LogStatus.PASS, "Programmable key is already assigned to selected app");
				}
			else
				{
				navigateUsingText("Select PTT Key app");
				assign_app_to_ProgrammableKey(data.get("Application"));
				validateAppProgrammableKey(data.get("Application"));
				
				}	
			press_PTT_Key();
			validate_launchedApplication(data.get("summaryText"));
		test.log(LogStatus.PASS, "Test case status is Passed");
	   		}
	}

	@Test(priority=7,dataProvider="ScoutSanity_Data_Provider", dataProviderClass=DataProviders.class)
	public void XP5S_ScoutFunctional_07_Validate_ProgrammableKey_successful_LaunchOf_SonimScout(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException  
	{
		APP_LOGS.info("===========ScoutFunctional_07============");
		startAdbLog("ScoutFunctional_07");
		//("ScoutFunctional_07");
	   if (XP5deviceModelNumber.contains("SL")) {
		   test.log(LogStatus.SKIP, "Test case status is Skipped  because Programmable Key is not Available in SL operator");
		}
	   else {
		   navigateprogrammablekey();
		   String applicationSet = Locators_ScoutSanity.summaryText.getText();
			//scrollToNoApplication();
			System.out.println(applicationSet);
			
			if (applicationSet.equalsIgnoreCase(data.get("Application"))) {
				test.log(LogStatus.PASS, "Testcase is passed since Programmable key is assigned to selected app");
				}
			else
				{
				navigateUsingText("Select PTT Key app");
				assign_app_to_ProgrammableKey(data.get("Application"));
				validateAppProgrammableKey(data.get("Application"));
				
				}	
			press_PTT_Key();
			validateScoutAppLaunch();
		test.log(LogStatus.PASS, "Test case status is Passed");
	   		}
	   }	
	
	@Test(priority=8,dataProvider="ScoutSanity_Data_Provider", dataProviderClass=DataProviders.class)
	public void XP5S_ScoutFunctional_08_Validate_Presence_Of_SWR_In_Scout(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException  
	{
		APP_LOGS.info("===========ScoutFunctional_08============");
		startAdbLog("ScoutFunctional_08");
		//("ScoutFunctional_08");
		   if (XP5deviceModelNumber.contains("SL")) {
			   test.log(LogStatus.SKIP, "Test case status is Skipped  because Programmable Key is not Available in ATT operator");
			}
		   else {
			   	launch_application(data.get("Application"));
				presence_Of_SonimWarrantyReg_In_ScoutApp();	
		   }	
		test.log(LogStatus.PASS, "Test case status is Passed");
	}
	
	@Test(priority=9,dataProvider="ScoutSanity_Data_Provider", dataProviderClass=DataProviders.class)
	public void XP5S_ScoutFunctional_09_Validate_Presence_Of_RegistrationForm_SWR(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException  
	{
		APP_LOGS.info("===========ScoutFunctional_09============");
		startAdbLog("ScoutFunctional_09");
		//("ScoutFunctional_09");
		   if (XP5deviceModelNumber.contains("SL")) {
			   test.log(LogStatus.SKIP, "Test case status is Skipped  because Programmable Key is not Available in SL operator");
			}
		   else {
			   launch_application(data.get("Application"));
			   navigateToWarrantyRegApp();
			   skip_AllFields_Press_SubmitBtn();
		   }
		test.log(LogStatus.PASS, "Test case status is Passed");
	}
	
	@Test(priority=10,dataProvider="ScoutSanity_Data_Provider", dataProviderClass=DataProviders.class)
	public void XP5S_ScoutFunctional_10_Validate_Upload_File_SWR(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException  
	{
		APP_LOGS.info("===========ScoutFunctional_10============");
		startAdbLog("ScoutFunctional_10");
		//("ScoutFunctional_10");
		   if (XP5deviceModelNumber.contains("SL")) {
			   test.log(LogStatus.SKIP, "Test case status is Skipped  because Programmable Key is not Available in SL operator");
			}
		   else {
			   launch_application(data.get("Application"));
			   navigateToWarrantyRegApp();
			   addInvoice();
				validateUploadFile(data.get("summaryText"));
		   }
		test.log(LogStatus.PASS, "Test case status is Passed");
	}
	
	@Test(priority=11,dataProvider="ScoutSanity_Data_Provider", dataProviderClass=DataProviders.class)
	public void XP5S_ScoutFunctional_11_Validate_ResetBtn_SWR(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException  
	{
		APP_LOGS.info("===========ScoutFunctional_11============");
		startAdbLog("ScoutFunctional_11");
		//("ScoutFunctional_11");
		   if (XP5deviceModelNumber.contains("SL")) {
			   test.log(LogStatus.SKIP, "Test case status is Skipped  because Programmable Key is not Available in SL operator");
			}
		   else {
			   launch_application(data.get("Application"));
			   navigateToWarrantyRegApp();
				enter_Feilds_Press_ResetBtn();
				validateResetBtn();
		   }
		test.log(LogStatus.PASS, "Test case status is Passed");
	}
	
	@Test(priority=12,dataProvider="ScoutSanity_Data_Provider", dataProviderClass=DataProviders.class)
	public void XP5S_ScoutFunctional_12_Validate_ErrorMsg_MandatoryFileds_notfilled_SWR(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException  
	{
		APP_LOGS.info("===========ScoutFunctional_12============");
		startAdbLog("ScoutFunctional_12");
		//("ScoutFunctional_12");
		   if (XP5deviceModelNumber.contains("SL")) {
			   test.log(LogStatus.SKIP, "Test case status is Skipped  because Programmable Key is not Available in SL operator");
			}
		   else {
			   launch_application(data.get("Application"));
			   navigateToWarrantyRegApp();
				validateMandatoryfield();
		   }
		test.log(LogStatus.PASS, "Test case status is Passed");
	}
	
	@Test(priority=13,dataProvider="ScoutSanity_Data_Provider", dataProviderClass=DataProviders.class)
	public void XP5S_ScoutFunctional_13_Validate_Access_DeviceInformation(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException  
	{
		APP_LOGS.info("===========ScoutFunctional_13============");
		startAdbLog("ScoutFunctional_13");
		//("ScoutFunctional_13");
		launch_application(data.get("Application"));
		navigateApps(Locators_ScoutSanity.SWR_Support_Tab, data.get("summaryText"));
		validateDeviceInfoAcess();
		test.log(LogStatus.PASS, "Test case status is Passed");
	}
	
	@Test(priority=14,dataProvider="ScoutSanity_Data_Provider", dataProviderClass=DataProviders.class)
	public void XP5S_ScoutFunctional_14_Validate_AccessSWR_DeviceInformation(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException  
	{
		APP_LOGS.info("===========ScoutFunctional_14============");
		startAdbLog("ScoutFunctional_14");
		//("ScoutFunctional_14");
		   if (XP5deviceModelNumber.contains("SL")) {
			   test.log(LogStatus.SKIP, "Test case status is Skipped  because Programmable Key is not Available in ATT operator");
			}
		   else {
			   launch_application(data.get("Application"));
			   navigateApps(Locators_ScoutSanity.SWR_Support_Tab, data.get("summaryText"));
			   validateAcessSWR();	
		   }			
		test.log(LogStatus.PASS, "Test case status is Passed");
	}
	
	
	@Test(priority=15,dataProvider="ScoutSanity_Data_Provider", dataProviderClass=DataProviders.class)
	public void XP5S_ScoutFunctional_15_Validate_AccessRemoteControl_Scout(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException  
	{
		APP_LOGS.info("===========ScoutFunctional_15============");
		startAdbLog("ScoutFunctional_15");
		//("ScoutFunctional_15");
		launch_application(data.get("Application"));
		   if (XP5deviceModelNumber.contains("SL")) {
			   navigateApps(Locators_ScoutSanity.SWR_Support_Tab, data.get("summaryText_SL"));
				validateRemoteControl();	
		   }
		   else {
		navigateApps(Locators_ScoutSanity.SWR_Support_Tab, data.get("summaryText"));
		validateRemoteControl();		
		   }
		test.log(LogStatus.PASS, "Test case status is Passed");
	}
	
	
	@Test(priority=16,dataProvider="ScoutSanity_Data_Provider", dataProviderClass=DataProviders.class)
	public void XP5S_ScoutFunctional_16_Validate_AccessChat_Scout(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException  
	{
		APP_LOGS.info("===========ScoutFunctional_16============");
		startAdbLog("ScoutFunctional_16");
		//("ScoutFunctional_16");
		launch_application(data.get("Application"));
		navigateApps(Locators_ScoutSanity.SWR_Support_Tab, data.get("summaryText"));
		validateChatFields();
		test.log(LogStatus.PASS, "Test case status is Passed");
	}

	@Test(priority=17,dataProvider="ScoutSanity_Data_Provider", dataProviderClass=DataProviders.class)
	public void XP5S_ScoutFunctional_17_Validate_AccessContactTransfer_Scout(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException  
	{
		APP_LOGS.info("===========ScoutFunctional_17============");
		startAdbLog("ScoutFunctional_17");
		//("ScoutFunctional_17");
		launch_application(data.get("Application"));
		navigateApps(Locators_ScoutSanity.utilitiesTab,data.get("summaryText"));
		validate_Current_Screen(Locators_ScoutSanity.ContactTransferTitle);
		test.log(LogStatus.PASS, "Test case status is Passed");
	}
	
	@Test(priority=18,dataProvider="ScoutSanity_Data_Provider", dataProviderClass=DataProviders.class)
	public void XP5S_ScoutFunctional_18_Validate_AccessAllOptions_ContactTransfer(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException  
	{
		APP_LOGS.info("===========ScoutFunctional_18============");
		startAdbLog("ScoutFunctional_18");
		//("ScoutFunctional_18");
		launch_application(data.get("Application"));
		navigateApps(Locators_ScoutSanity.utilitiesTab,data.get("summaryText"));
		validateAllOptionsSelect(data.get("BrowseTitle1"),(data.get("BrowseTitle2")),(data.get("BrowseTitle3")),(data.get("BrowseTitle4")));
		test.log(LogStatus.PASS, "Test case status is Passed");
	}
	
	@Test(priority=19,dataProvider="ScoutSanity_Data_Provider", dataProviderClass=DataProviders.class)
	public void XP5S_ScoutSanity_19_Validate_ImportVCFContacts_ContactTransfer(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException  
	{
		APP_LOGS.info("===========ScoutSanity_19============");
		startAdbLog("ContactTransfer_AdbLog");
		//("ScoutSanity_19");
		launch_application(data.get("Application"));
		navigateApps(Locators_ScoutSanity.utilitiesTab,data.get("summaryText"));		
		select_Transfer_Mode("vcf");
		selectContactfileToImport(1);
		validateAllContactImport("ContactTransfer_AdbLog");
		pressBackBtn();
		customWait(4000);
		test.log(LogStatus.PASS, "Test case status is Passed");
	}
	
	
	@Test(priority=20,dataProvider="ScoutSanity_Data_Provider", dataProviderClass=DataProviders.class)
	public void XP5S_ScoutFunctional_20_Validate_ImportMDBcontacts_ContactTransfer(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException  
	{
		APP_LOGS.info("===========ScoutFunctional_20============");
		startAdbLog("ContactTransfer_AdbLog");
		//("ScoutFunctional_20");
		launch_application(data.get("Application"));
		navigateApps(Locators_ScoutSanity.utilitiesTab,data.get("summaryText"));
		select_Transfer_Mode("mdb");
		selectContactfileToImport(2);
		validateAllContactImport("ContactTransfer_AdbLog");
		pressBackBtn();
		customWait(4000);
		test.log(LogStatus.PASS, "Test case status is Passed");
	}
	
	@Test(priority=21,dataProvider="ScoutSanity_Data_Provider", dataProviderClass=DataProviders.class)
	public void XP5S_ScoutFunctional_21_Validate_ImportCSVContacts_ContactTransfer(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException  
	{
		APP_LOGS.info("===========ScoutFunctional_21============");
		startAdbLog("ContactTransfer_AdbLog");
		//("ScoutFunctional_21");
		launch_application(data.get("Application"));
		navigateApps(Locators_ScoutSanity.utilitiesTab,data.get("summaryText"));
		select_Transfer_Mode("csv");
		selectContactfileToImport(3);
		validateAllContactImport("ContactTransfer_AdbLog");
		pressBackBtn();
		customWait(4000);
		test.log(LogStatus.PASS, "Test case status is Passed");
	}
	
	@Test(priority=22,dataProvider="ScoutSanity_Data_Provider", dataProviderClass=DataProviders.class)
	public void XP5S_ScoutFunctional_22_Validate_SummaryHistory_ImportedContacts_ContactTransfer(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException  
	{
		APP_LOGS.info("===========ScoutFunctional_22============");
		startAdbLog("ScoutFunctional_22");
		//("ScoutFunctional_22");
		launch_application(data.get("Application"));
		navigateApps(Locators_ScoutSanity.utilitiesTab,data.get("summaryText"));
		select_Transfer_Mode("csv");
		selectContactfileToImport(3);
		validateSummaryHistory();
		pressBackBtn();
		customWait(4000);
		test.log(LogStatus.PASS, "Test case status is Passed");
	}	
	
	
	@Test(priority=23,dataProvider="ScoutSanity_Data_Provider", dataProviderClass=DataProviders.class)
	public void XP5S_ScoutFunctional_23_Validate_Menu_Safeguard(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException  
	{
		APP_LOGS.info("===========ScoutFunctional_23============");
		startAdbLog("ScoutFunctional_23");
		//("ScoutFunctional_23");
		launch_application(data.get("Application"));
		navigateApps(Locators_ScoutSanity.setUpTab,data.get("summaryText"));
		navigateToSafeguard();
		verify_Default_MenuStates_Of_All_Options_SG();
		test.log(LogStatus.PASS, "Test case status is Passed");
	}
	
	@Test(priority=24,dataProvider="ScoutSanity_Data_Provider", dataProviderClass=DataProviders.class)
	public void XP5S_ScoutFunctional_24_Validate_Restrict_AllAppLaunch_Safeguard(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException  
	{
		APP_LOGS.info("===========ScoutFunctional_24============");
		startAdbLog("ScoutFunctional_24");
		//("ScoutFunctional_24");
		launch_application(data.get("Application"));
		navigateApps(Locators_ScoutSanity.setUpTab,data.get("summaryText"));
		select_All_Apps_And_Check();
		test.log(LogStatus.PASS, "Test case status is Passed");
	}
	
	@Test(priority=25,dataProvider="ScoutSanity_Data_Provider", dataProviderClass=DataProviders.class)
	public void XP5S_ScoutFunctional_25_Validate_UnRestrict_AllAppLaunch_Safeguard(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException  
	{
		APP_LOGS.info("===========ScoutFunctional_25============");
		startAdbLog("ScoutFunctional_25");
		//("ScoutFunctional_25");
		launch_application(data.get("Application"));
		enterPin();
		navigateApps(Locators_ScoutSanity.setUpTab,data.get("summaryText"));
		Unselect_All_Apps_And_Check();
		test.log(LogStatus.PASS, "Test case status is Passed");
	}
	
	@Test(priority=26,dataProvider="ScoutSanity_Data_Provider", dataProviderClass=DataProviders.class)
	public void XP5S_ScoutFunctional_26_Validate_RestrictFeature_Check_Safeguard(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException  
	{
		APP_LOGS.info("===========ScoutSanity_26============");
		startAdbLog("ScoutSanity_26");
		//("ScoutSanity_26");
		launch_application(data.get("Application"));
		navigateApps(Locators_ScoutSanity.setUpTab,data.get("summaryText"));
		check_Features_Tab();
		launch_application(data.get("Application"));
		Unselect_All_Features_And_Check();
		test.log(LogStatus.PASS, "Test case status is Passed");
	}
	
	@Test(priority=27,dataProvider="ScoutSanity_Data_Provider", dataProviderClass=DataProviders.class)
	public void XP5S_ScoutSanity_27_Validate_PinTimeout_Check_Safeguard(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException  
	{
		APP_LOGS.info("===========ScoutSanity_27============");
		startAdbLog("ScoutSanity_27");
		//("ScoutSanity_27");
		launch_application(data.get("Application"));
		   if (XP5deviceModelNumber.contains("SPR")) {
			    navigateApps(Locators_ScoutSanity.setUpTab,data.get("summaryText"));
			    special_Case_SelectSprint();
				application_Pin_30Sec_TimeOut();
				launch_application(data.get("Application"));
				navigateApps(Locators_ScoutSanity.setUpTab,data.get("summaryText"));
			    pin_Time_Out_Set_To_1_Min();
			    launch_application(data.get("Application"));
			    navigateApps(Locators_ScoutSanity.setUpTab,data.get("summaryText"));
				pass_Locators(Locators_ScoutSanity.SG_Applications_String, Locators_ScoutSanity.SG_Un_Select_All_Btn, Locators_ScoutSanity.SG_Save_Btn);
		   }
		   else {
			    navigateApps(Locators_ScoutSanity.setUpTab,data.get("summaryText"));
				special_Case_Select();
				application_Pin_30Sec_TimeOut();
				launch_application(data.get("Application"));
				navigateApps(Locators_ScoutSanity.setUpTab,data.get("summaryText"));
			    pin_Time_Out_Set_To_1_Min();
			    launch_application(data.get("Application"));
			    navigateApps(Locators_ScoutSanity.setUpTab,data.get("summaryText"));
				pass_Locators(Locators_ScoutSanity.SG_Applications_String, Locators_ScoutSanity.SG_Un_Select_All_Btn, Locators_ScoutSanity.SG_Save_Btn);
		   }
		
		test.log(LogStatus.PASS, "Test case status is Passed");
	}

	
	@Test(priority=28,dataProvider="ScoutSanity_Data_Provider", dataProviderClass=DataProviders.class)
	public void XP5S_ScoutFunctional_28_Validate_Device_info_Self_Diagnosis_Screen(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException  
	{
		APP_LOGS.info("===========ScoutFunctional_28============");
		startAdbLog("ScoutFunctional_28");
		//("ScoutFunctional_28");
		launch_application(data.get("Application"));
		navigateApps(Locators_ScoutSanity.supportTab,data.get("summaryText"));
		sonimCareSelect(data.get("sonimCareTest"));
		pressCenterKey();
		validate_Device_Info_Self_Diagnosis_screen();
		test.log(LogStatus.PASS, "Test case status is Passed");
	}
	  
	@Test(priority=29,dataProvider="ScoutSanity_Data_Provider", dataProviderClass=DataProviders.class)
	public void XP5S_ScoutFunctional_29_SonimCare_Validate_Wifi_Scan_Test(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException  
	{
		APP_LOGS.info("===========ScoutFunctional_29============");
		startAdbLog("ScoutFunctional_29");
		//("ScoutFunctional_29");
		launch_application(data.get("Application"));
		navigateApps(Locators_ScoutSanity.supportTab,data.get("summaryText"));
		sonimCareSelect(data.get("sonimCareTest"));
		navigateTo_TestAll_screen();
		navigateTo_Individual_feature("wi-fi");
		click_On_coninueBtn_featureScreen();
		validate_wifiScan();
		test.log(LogStatus.PASS, "Test case status is Passed");
	}
	
	@Test(priority=30,dataProvider="ScoutSanity_Data_Provider", dataProviderClass=DataProviders.class)
	public void XP5S_ScoutFunctional_30_SonimCare_Validate_BackCamera_Scan_Test(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException  
	{
		APP_LOGS.info("===========ScoutFunctional_30============");
		startAdbLog("ScoutFunctional_30");
		//("ScoutFunctional_30");
		launch_application(data.get("Application"));
		navigateApps(Locators_ScoutSanity.supportTab,data.get("summaryText"));
		sonimCareSelect(data.get("sonimCareTest"));
		navigateTo_TestAll_screen();
		navigateTo_Individual_feature("Back camera");
		click_On_coninueBtn_featureScreen();
		validate_backCamera(data.get("confirmationMessage"));
		test.log(LogStatus.PASS, "Test case status is Passed");
	}
	
	@Test(priority=31,dataProvider="ScoutSanity_Data_Provider", dataProviderClass=DataProviders.class)
	public void XP5S_ScoutFunctional_31_SonimCare_Validate_Battery_Scan_Test(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException  
	{
		APP_LOGS.info("===========ScoutFunctional_31============");
		startAdbLog("ScoutFunctional_31");
		//("ScoutFunctional_31");
		launch_application(data.get("Application"));
		navigateApps(Locators_ScoutSanity.supportTab,data.get("summaryText"));
		sonimCareSelect(data.get("sonimCareTest"));
		navigateTo_TestAll_screen();
		navigateTo_Individual_feature("Battery");
		click_On_coninueBtn_featureScreen();
		validate_BatteryTest(data.get("bat_Status"), data.get("bat_status_value_1"),data.get("bat_status_value_2"), data.get("bat_Electricity"), data.get("bat_Electricity_value"));
		test.log(LogStatus.PASS, "Test case status is Passed");
	}
	
	@Test(priority=32,dataProvider="ScoutSanity_Data_Provider", dataProviderClass=DataProviders.class)
	public void XP5S_ScoutFunctional_32_SonimCare_Validate_Clear_CacheMemory_Test(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException  
	{
		APP_LOGS.info("===========ScoutFunctional_32============");
		startAdbLog("ScoutFunctional_32");
		//("ScoutFunctional_32");
		launch_application(data.get("Application"));
		navigateApps(Locators_ScoutSanity.supportTab,data.get("summaryText"));
		sonimCareSelect(data.get("sonimCareTest"));
		navigateTo_TestAll_screen();
		navigateTo_Individual_feature("CLEAR CACHE MEMORY");
		click_On_coninueBtn_featureScreen();
		validate_ClearCacheMemory();
		test.log(LogStatus.PASS, "Test case status is Passed");
	}
	
	
	@Test(priority=33,dataProvider="ScoutSanity_Data_Provider", dataProviderClass=DataProviders.class)
	public void XP5S_ScoutFunctional_33_SonimCare_Validate_Bluetooth_Scan_Test(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException  
	{
		APP_LOGS.info("===========ScoutFunctional_33============");
		startAdbLog("ScoutFunctional_33");
		//("ScoutFunctional_33");
		launch_application(data.get("Application"));
		navigateApps(Locators_ScoutSanity.supportTab,data.get("summaryText"));
		sonimCareSelect(data.get("sonimCareTest"));
		navigateTo_TestAll_screen();
		navigateTo_Individual_feature("Bluetooth");
		click_On_coninueBtn_featureScreen();
		validate_bluetooth_scan();
		test.log(LogStatus.PASS, "Test case status is Passed");
	}
	
	@Test(priority=34,dataProvider="ScoutSanity_Data_Provider", dataProviderClass=DataProviders.class)
	public void XP5S_ScoutFunctional_34_SonimCare_Validate_HyperLinks_Alerts_wifiFeature(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException  
	{
		APP_LOGS.info("===========ScoutFunctional_34============");
		startAdbLog("ScoutFunctional_34");
		//("ScoutFunctional_34");
		launch_application(data.get("Application"));
		navigateApps(Locators_ScoutSanity.supportTab,data.get("summaryText"));
		sonimCareSelect(data.get("sonimCareTest"));
		navigateTo_TestAll_screen();
		navigateTo_Individual_feature("wi-fi");
		click_On_coninueBtn_featureScreen();
		validate_wifi_links("wi-fi");
		test.log(LogStatus.PASS, "Test case status is Passed");
	}
	
	@Test(priority=35,dataProvider="ScoutSanity_Data_Provider", dataProviderClass=DataProviders.class)
	public void XP5S_ScoutFunctional_35_SonimCare_Validate_HyperLinks_Alerts_battery_Feature(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException  
	{
		APP_LOGS.info("===========ScoutFunctional_35============");
		startAdbLog("ScoutFunctional_35");
		//("ScoutFunctional_35");
		launch_application(data.get("Application"));
		navigateApps(Locators_ScoutSanity.supportTab,data.get("summaryText"));
		sonimCareSelect(data.get("sonimCareTest"));
		navigateTo_TestAll_screen();
		navigateTo_Individual_feature("battery");
		click_On_coninueBtn_featureScreen();
		validate_battery_links("battery");
		test.log(LogStatus.PASS, "Test case status is Passed");
	}
	
	@Test(priority=36,dataProvider="ScoutSanity_Data_Provider", dataProviderClass=DataProviders.class)
	public void XP5S_ScoutFunctional_36_SonimCare_Validate_HyperLinks_Alerts_Self_Diagnosis_Screen(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException  
	{
		APP_LOGS.info("===========ScoutFunctional_36============");
		startAdbLog("ScoutFunctional_36");
		//("ScoutFunctional_36");
		launch_application(data.get("Application"));
		navigateApps(Locators_ScoutSanity.supportTab,data.get("summaryText"));
		sonimCareSelect(data.get("sonimCareTest"));
		pressCenterKey();
		validate_hyperTexts_alert_selfDiagnosisPage();
		test.log(LogStatus.PASS, "Test case status is Passed");
	}
	
	@Test(priority=37,dataProvider="ScoutSanity_Data_Provider", dataProviderClass=DataProviders.class)
	public void XP5S_ScoutFunctional_37_SonimCare_Validate_Wifi_No_btn_Functionality(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException  
	{
		APP_LOGS.info("===========ScoutFunctional_37============");
		startAdbLog("ScoutFunctional_37");
		//("ScoutFunctional_37");
		launch_application(data.get("Application"));
		navigateApps(Locators_ScoutSanity.supportTab,data.get("summaryText"));
		sonimCareSelect(data.get("sonimCareTest"));
		navigateTo_TestAll_screen();
		navigateTo_Individual_feature("wi-fi");
		click_On_coninueBtn_featureScreen();
		clickBtn(Locators_ScoutSanity.scanForMoreNetworksBtn);
		validate_No_btn_feature("wi-fi");
		test.log(LogStatus.PASS, "Test case status is Passed");
	}
	
	@Test(priority=38,dataProvider="ScoutSanity_Data_Provider", dataProviderClass=DataProviders.class)
	public void XP5S_ScoutFunctional_38_SonimCare_Validate_Bluetooth_No_btn_Functionality(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException  
	{
		APP_LOGS.info("===========ScoutFunctional_38============");
		startAdbLog("ScoutFunctional_38");
		//("ScoutFunctional_38");
		launch_application(data.get("Application"));
		navigateApps(Locators_ScoutSanity.supportTab,data.get("summaryText"));
		sonimCareSelect(data.get("sonimCareTest"));
		navigateTo_TestAll_screen();
		navigateTo_Individual_feature("bluetooth");
		click_On_coninueBtn_featureScreen();
		validate_No_btn_feature("bluetooth");
		test.log(LogStatus.PASS, "Test case status is Passed");
	}
	
	@Test(priority=39,dataProvider="ScoutSanity_Data_Provider", dataProviderClass=DataProviders.class)
	public void XP5S_ScoutFunctional_39_SonimCare_Validate_Battery_diagnosis_Screen(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException  
	{
		APP_LOGS.info("===========ScoutFunctional_39============");
		startAdbLog("ScoutFunctional_39");
		//("ScoutFunctional_39");
		launch_application(data.get("Application"));
		navigateApps(Locators_ScoutSanity.supportTab,data.get("summaryText"));
		sonimCareSelect(data.get("sonimCareTest"));
		click_On_coninueBtn_Bat_Scan();
		validate_batteryDiagnosis();
		test.log(LogStatus.PASS, "Test case status is Passed");
	}
	
	
	@Test(priority=40,dataProvider="ScoutSanity_Data_Provider", dataProviderClass=DataProviders.class)
	public void XP5S_ScoutFunctional_40_SonimCare_Validate_Battery_Service_History(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException , ParseException 
	{
		APP_LOGS.info("===========ScoutFunctional_40============");
		startAdbLog("ScoutFunctional_40");
		//("ScoutFunctional_40");
		launch_application(data.get("Application"));
		navigateApps(Locators_ScoutSanity.supportTab,data.get("summaryText"));
		sonimCareSelect(data.get("sonimCareTest"));
		click_On_coninueBtn_Bat_Scan();
		validate_battery_service_history();
		test.log(LogStatus.PASS, "Test case status is Passed");
	}
	
}
