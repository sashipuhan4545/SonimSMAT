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
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.ExtentTestInterruptedException;
import com.relevantcodes.extentreports.LogStatus;
import com.xp5S.util.Locators_ScoutSanity;
import com.xp8.util.DataProviders;
import com.xp8.util.ExcelConstants;
import com.xp8.util.ExcelReader;
import com.xp8.util.Locators_BaseUtil;
import com.xp8.util.Locators_ScoutFT;
import com.xp8.util.XP8_ScoutFT_Util;

import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class XP8_ScoutFT_Test extends XP8_ScoutFT_Util {

	public ExcelReader excel;
	Properties properties;
	public static ExtentReports extent;
	public static ExtentTest test;
	public static ExtentTestInterruptedException testexception;

	@BeforeSuite
	public void beforeSuite() 
	{
		extent = new ExtentReports("src/test/resources/extentreport/XP8_ScoutFT_Test.html", true); //Provide Desired Report Directory Location and Name
		extent.loadConfig(new File(System.getProperty("user.dir")+"//ReportsConfig.xml"));
		extent.addSystemInfo("Appium", "1.1").addSystemInfo("Environment", "TEST");
	}
	
	@BeforeClass
	public void copyFilesToDevice() throws IOException, ParseException {
		
		Runtime.getRuntime().exec("adb push src/test/resources/contactTransferFile/CSV.csv /storage/emulated/0/Download");
		Runtime.getRuntime().exec("adb push src/test/resources/contactTransferFile/i475.mdb /storage/emulated/0/Download");
		Runtime.getRuntime().exec("adb push src/test/resources/contactTransferFile/MMS_Contact.vcf /storage/emulated/0/Download");
		
	}


	@BeforeMethod()
	public  void beforeMethod(Method method) 
	{
		test = extent.startTest( (this.getClass().getSimpleName() +" :: "+  method.getName()),method.getName()); //Test Case Start Here
		test.assignAuthor("Farheen Taj"); //Test Script Author Name
	}

	@AfterMethod()
	public void tearDown(ITestResult result,Method method) throws IOException, ParseException, InterruptedException
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
		properties=loadDriverAndProperties();
		Locators_ScoutFT loc=new Locators_ScoutFT(aDriver);
		Locators_BaseUtil loc1 = new Locators_BaseUtil(aDriver);	
		PageFactory.initElements(new AppiumFieldDecorator(aDriver),loc);
		PageFactory.initElements(new AppiumFieldDecorator(aDriver),loc1);
		excel=new ExcelReader(ExcelConstants.XP5S_XL_PATH);	

	}


	@Test(priority=1,dataProvider="XP8_ScoutFTTest", dataProviderClass=DataProviders.class)
	public void XP8_ScoutFT_001_Validate_navigate_ScoutApp_from_Menu(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException
	{
		APP_LOGS.info("===========XP8_ScoutFT_001============");
		clearRecentApps();
		startAdbLog("XP8_ScoutFT_001");
		//("XP8_ScoutFT_001");
		launch_application(data.get("Application"));
		validateScoutAppLaunch();
		test.log(LogStatus.PASS, "Test case status is Passed");	
	}
	
	@Test(priority=2,dataProvider="XP8_ScoutFTTest", dataProviderClass=DataProviders.class)
	public void XP8_ScoutFT_002_Validate_allTabsListExist(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException
	{
		APP_LOGS.info("===========XP8_ScoutFT_002============");
		clearRecentApps();
		startAdbLog("XP8_ScoutFT_002");
		//("XP8_ScoutFT_002");
		launch_application(data.get("Application"));
		validateAllTabsandList();
		test.log(LogStatus.PASS, "Test case status is Passed");	
	}
	
	@Test(priority=3,dataProvider="XP8_ScoutFTTest", dataProviderClass=DataProviders.class)
	public void XP8_ScoutFT_003_Validate_Default_ProgrammableKey_Assign(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException
	{
		APP_LOGS.info("===========XP8_ScoutFT_003============");
		clearRecentApps();
		startAdbLog("XP8_ScoutFT_003");
		//("XP8_ScoutFT_003");
		launch_application(data.get("Application"));
		navigateprogrammablekey();
		validate_ProgrammableKeySummaryText();
		test.log(LogStatus.PASS, "Test case status is Passed");	
	}
	
	@Test(priority=4,dataProvider="XP8_ScoutFTTest", dataProviderClass=DataProviders.class)
	public void XP8_ScoutFT_004_Validate_SummaryText_Of_AssignparticularApp(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException
	{
		APP_LOGS.info("===========XP8_ScoutFT_004============");
		clearRecentApps();
		startAdbLog("XP8_ScoutFT_004");
		//("XP8_ScoutFT_004");
		launch_application(data.get("Application"));
		navigateprogrammablekey();
		navigateUsingText(data.get("SelectKey"));
	//	scrollToNoApplication(Locators_ScoutFT.SelectPTTKey);
		assign_app_to_ProgrammableKey(data.get("summaryText"));
		validateAssignApp(data.get("summaryText"));
		test.log(LogStatus.PASS, "Test case status is Passed");	
	}
	
	@Test(priority=5,dataProvider="XP8_ScoutFTTest", dataProviderClass=DataProviders.class)
	public void XP8_ScoutFT_005_Validate_successful_LaunchOf_ThirdPartyApp(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException
	{
		APP_LOGS.info("===========XP8_ScoutFT_005============");
		clearRecentApps();
		startAdbLog("XP8_ScoutFT_005");
		//("XP8_ScoutFT_005");
		launch_application(data.get("Application"));
		navigateprogrammablekey();
		navigateUsingText(data.get("SelectKey"));
		assign_app_to_ProgrammableKey(data.get("summaryText"));
		validate_launchedApplication(data.get("summaryText"));
		test.log(LogStatus.PASS, "Test case status is Passed");	
	}
	
	@Test(priority=6,dataProvider="XP8_ScoutFTTest", dataProviderClass=DataProviders.class)
	public void XP8_ScoutFT_006_Validate_successful_LaunchOf_SonimScout(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException, ParseException
	{
		APP_LOGS.info("===========XP8_ScoutFT_006============");
		clearRecentApps();
		startAdbLog("XP8_ScoutFT_006");
		//("XP8_ScoutFT_006");
		launch_application(data.get("Application"));
		navigateprogrammablekey();
		navigateUsingText(data.get("SelectKey"));
		assign_app_to_ProgrammableKey(data.get("summaryText"));
		press_PTT_Key();
		validateScoutAppLaunch();
		test.log(LogStatus.PASS, "Test case status is Passed");	
	}
	
	@Test(priority=7,dataProvider="XP8_ScoutFTTest", dataProviderClass=DataProviders.class)
	public void XP8_ScoutFT_007_Validate_Presence_Of_SWR_In_Scout(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException
	{
		APP_LOGS.info("===========XP8_ScoutFT_007============");
		clearRecentApps();
		startAdbLog("XP8_ScoutFT_007");
		//("XP8_ScoutFT_007");
		presence_Of_SonimWarrantyReg_In_ScoutApp();	
		test.log(LogStatus.PASS, "Test case status is Passed");	
	}
	
	@Test(priority=8,dataProvider="XP8_ScoutFTTest", dataProviderClass=DataProviders.class)
	public void XP8_ScoutFT_008_Validate_Presence_Of_RegistrationForm_SWR(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException
	{
		APP_LOGS.info("===========XP8_ScoutFT_008============");
		clearRecentApps();
		startAdbLog("XP8_ScoutFT_008");
		//("XP8_ScoutFT_008");
		skip_AllFields_Press_SubmitBtn();
		test.log(LogStatus.PASS, "Test case status is Passed");	
	}
	
	@Test(priority=9,dataProvider="XP8_ScoutFTTest", dataProviderClass=DataProviders.class)
	public void XP8_ScoutFT_009_Validate_Upload_File_SWR(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException
	{
		APP_LOGS.info("===========XP8_ScoutFT_009============");
		clearRecentApps();
		startAdbLog("XP8_ScoutFT_009");
		//("XP8_ScoutFT_009");
		navigateToWarrantyRegApp();
		addInvoice();
		validateUploadFile();
		test.log(LogStatus.PASS, "Test case status is Passed");	
	}
	
	
	@Test(priority=10,dataProvider="XP8_ScoutFTTest", dataProviderClass=DataProviders.class)
	public void XP8_ScoutFT_010_Validate_ResetBtn_SWR(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException
	{
		APP_LOGS.info("===========XP8_ScoutFT_010============");
		clearRecentApps();
		startAdbLog("XP8_ScoutFT_010");
		//("XP8_ScoutFT_010");
		navigateToWarrantyRegApp();
		enter_Feilds_Press_ResetBtn();
		validateResetBtn();
		test.log(LogStatus.PASS, "Test case status is Passed");	
	}
	
	@Test(priority=11,dataProvider="XP8_ScoutFTTest", dataProviderClass=DataProviders.class)
	public void XP8_ScoutFT_011_Validate_ErrorMsg_MandatoryFileds_notfilled_SWR(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException
	{
		APP_LOGS.info("===========XP8_ScoutFT_011============");
		clearRecentApps();
		startAdbLog("XP8_ScoutFT_011");
		//("XP8_ScoutFT_011");
		navigateToWarrantyRegApp();
		validateMandatoryfield();
		test.log(LogStatus.PASS, "Test case status is Passed");	
	}
	
	@Test(priority=12,dataProvider="XP8_ScoutFTTest", dataProviderClass=DataProviders.class)
	public void XP8_ScoutFT_012_Validate_Access_DeviceInformation(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException
	{
		APP_LOGS.info("===========XP8_ScoutFT_012============");
		clearRecentApps();
		startAdbLog("XP8_ScoutFT_012");
		//("XP8_ScoutFT_012");
		launch_application(data.get("Application"));
		navigateToApps("Support", data.get("summaryText"));
		//navigateApps(Locators_ScoutFT.SWR_Support_Tab,Locators_ScoutFT.DeviceInfo_App);
		validateDeviceInfoAcess();
		test.log(LogStatus.PASS, "Test case status is Passed");	
	}
	
	@Test(priority=13,dataProvider="XP8_ScoutFTTest", dataProviderClass=DataProviders.class)
	public void XP8_ScoutFT_013_Validate_AccessSWR_DeviceInformation(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException
	{
		APP_LOGS.info("===========XP8_ScoutFT_013============");
		clearRecentApps();
		startAdbLog("XP8_ScoutFT_013");
		//("XP8_ScoutFT_013");
		launch_application(data.get("Application"));
		navigateToApps("Support", data.get("summaryText"));
		//navigateApps(Locators_ScoutFT.SWR_Support_Tab,Locators_ScoutFT.DeviceInfo_App);
		validateAcessSWR();	
		test.log(LogStatus.PASS, "Test case status is Passed");	
	}
	
	
	@Test(priority=14,dataProvider="XP8_ScoutFTTest", dataProviderClass=DataProviders.class)
	public void XP8_ScoutFT_014_Validate_AccessRemoteControl_Scout(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException
	{
		APP_LOGS.info("===========XP8_ScoutFT_014============");
		clearRecentApps();
		startAdbLog("XP8_ScoutFT_014");
		//("XP8_ScoutFT_014");
		launch_application(data.get("Application"));
		navigateToApps("Support", data.get("summaryText"));
		//navigateApps(Locators_ScoutFT.SWR_Support_Tab,Locators_ScoutFT.RemoteControl);
		validateRemoteControl();	
		test.log(LogStatus.PASS, "Test case status is Passed");	
	}
	
	@Test(priority=15,dataProvider="XP8_ScoutFTTest", dataProviderClass=DataProviders.class)
	public void XP8_ScoutFT_015_Validate_AccessChat_Scout(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException
	{
		APP_LOGS.info("===========XP8_ScoutFT_015============");
		clearRecentApps();
		startAdbLog("XP8_ScoutFT_015");
		//("XP8_ScoutFT_015");
		launch_application(data.get("Application"));
		navigateToApps("Support", data.get("summaryText"));
		//navigateApps(Locators_ScoutFT.SWR_Support_Tab,Locators_ScoutFT.Chat);
		validateChatFields();
		test.log(LogStatus.PASS, "Test case status is Passed");	
	}
	
	@Test(priority=16,dataProvider="XP8_ScoutFTTest", dataProviderClass=DataProviders.class)
	public void XP8_ScoutFT_016_Validate_AccessContactTransfer_Scout(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException
	{
		APP_LOGS.info("===========XP8_ScoutFT_016============");
		clearRecentApps();
		startAdbLog("XP8_ScoutFT_016");
		//("XP8_ScoutFT_016");
		launch_application(data.get("Application"));
		navigateToApps("Utilities", data.get("summaryText"));
		//navigateApps(Locators_ScoutFT.utilitiesTab,Locators_ScoutFT.ContactTransferTitle);
		validate_Current_Screen(Locators_ScoutFT.ContactTransferTitle);
		test.log(LogStatus.PASS, "Test case status is Passed");	
	}
	
	@Test(priority=17,dataProvider="XP8_ScoutFTTest", dataProviderClass=DataProviders.class)
	public void XP8_ScoutFT_017_Validate_AccessAllOptions_ContactTransfer(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException
	{
		APP_LOGS.info("===========XP8_ScoutFT_017============");
		clearRecentApps();
		startAdbLog("XP8_ScoutFT_017");
		//("XP8_ScoutFT_017");
		launch_application(data.get("Application"));
		navigateToApps("Utilities", data.get("summaryText"));
		//navigateApps(Locators_ScoutFT.utilitiesTab,Locators_ScoutFT.ContactTransferTitle);
		validateAllOptionsSelect(data.get("BrowseTitle1"),(data.get("BrowseTitle2")),(data.get("BrowseTitle3")),(data.get("BrowseTitle4")));
		test.log(LogStatus.PASS, "Test case status is Passed");	
	}
	
	@Test(priority=18,dataProvider="XP8_ScoutFTTest", dataProviderClass=DataProviders.class)
	public void XP8_ScoutFT_018_Validate_ImportVCFCOntacts_ContactTransfer(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException
	{
		APP_LOGS.info("===========XP8_ScoutFT_018============");
		clearRecentApps();
		startAdbLog("XP8_ScoutFT_018");
		//("XP8_ScoutFT_018");
		launch_application(data.get("Application"));
		navigateToApps("Utilities", data.get("summaryText"));
		//navigateApps(Locators_ScoutFT.utilitiesTab,Locators_ScoutFT.ContactTransferTitle);
		select_Transfer_Mode("vcf");
		selectContactfileToImport(7);
		validateAllContactImport();
		test.log(LogStatus.PASS, "Test case status is Passed");	
	}
	
	
	@Test(priority=19,dataProvider="XP8_ScoutFTTest", dataProviderClass=DataProviders.class)
	public void XP8_ScoutFT_019_Validate_ImportMDBcontacts_ContactTransfer(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException
	{
		APP_LOGS.info("===========XP8_ScoutFT_019============");
		clearRecentApps();
		startAdbLog("XP8_ScoutFT_019");
		//("XP8_ScoutFT_019");
		launch_application(data.get("Application"));
		navigateToApps("Utilities", data.get("summaryText"));
		//navigateApps(Locators_ScoutFT.utilitiesTab,Locators_ScoutFT.ContactTransferTitle);
		select_Transfer_Mode("mdb");
		selectContactfileToImport(7);
		validateAllContactImport();
		test.log(LogStatus.PASS, "Test case status is Passed");	
	}
	
	@Test(priority=20,dataProvider="XP8_ScoutFTTest", dataProviderClass=DataProviders.class)
	public void XP8_ScoutFT_020_Validate_ImportCSVContacts_ContactTransfer(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException
	{
		APP_LOGS.info("===========XP8_ScoutFT_020============");
		clearRecentApps();
		startAdbLog("XP8_ScoutFT_020");
		//("XP8_ScoutFT_020");
		launch_application(data.get("Application"));
		navigateToApps("Utilities", data.get("summaryText"));
		//navigateApps(Locators_ScoutFT.utilitiesTab,Locators_ScoutFT.ContactTransferTitle);
		select_Transfer_Mode("csv");
		selectContactfileToImport(45);
		validateAllContactImport();
		test.log(LogStatus.PASS, "Test case status is Passed");	
	}
	
	@Test(priority=21,dataProvider="XP8_ScoutFTTest", dataProviderClass=DataProviders.class)
	public void XP8_ScoutFT_021_Validate_SummaryHistory_ImportedContacts_ContactTransfer(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException
	{
		APP_LOGS.info("===========XP8_ScoutFT_021============");
		clearRecentApps();
		startAdbLog("XP8_ScoutFT_021");
		//("XP8_ScoutFT_021");
		launch_application(data.get("Application"));
		navigateToApps("Utilities", data.get("summaryText"));
		//navigateApps(Locators_ScoutFT.utilitiesTab,Locators_ScoutFT.ContactTransferTitle);
		select_Transfer_Mode("csv");
		selectContactfileToImport(7);
		validateSummaryHistory();
		pressBackBtn();
		test.log(LogStatus.PASS, "Test case status is Passed");	
	}
	
	@Test(priority=22,dataProvider="XP8_ScoutFTTest", dataProviderClass=DataProviders.class)
	public void XP8_ScoutFT_022_Validate_Menu_Safeguard(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException
	{
		APP_LOGS.info("===========XP8_ScoutFT_022============");
		clearRecentApps();
		startAdbLog("XP8_ScoutFT_022");
		//("XP8_ScoutFT_022");
		launch_application(data.get("Application"));
		navigateToApps("Setup", data.get("summaryText"));
		//navigateApps(Locators_ScoutFT.setUpTab,Locators_ScoutFT.Safeguard);
		verify_Default_States_Of_All_Options_SG();
		test.log(LogStatus.PASS, "Test case status is Passed");	
	}
	
	@Test(priority=23,dataProvider="XP8_ScoutFTTest", dataProviderClass=DataProviders.class)
	public void XP8_ScoutFT_023_Validate_Restrict_AllAppLaunch_Safeguard(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException
	{
		APP_LOGS.info("===========XP8_ScoutFT_023============");
		clearRecentApps();
		startAdbLog("XP8_ScoutFT_023");
		//("XP8_ScoutFT_023");
		launch_application(data.get("Application"));
		navigateToApps("Setup", data.get("summaryText"));
		//navigateApps(Locators_ScoutFT.setUpTab,Locators_ScoutFT.Safeguard);
		select_All_Apps_And_Check();
		test.log(LogStatus.PASS, "Test case status is Passed");	
	}
	
	@Test(priority=24,dataProvider="XP8_ScoutFTTest", dataProviderClass=DataProviders.class)
	public void XP8_ScoutFT_024_Validate_UnRestrict_AllAppLaunch_Safeguard(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException
	{
		APP_LOGS.info("===========XP8_ScoutFT_024============");
		clearRecentApps();
		startAdbLog("XP8_ScoutFT_024");
		//("XP8_ScoutFT_024");
		launch_application(data.get("Application"));
		navigateToApps("Setup", data.get("summaryText"));
		//navigateApps(Locators_ScoutFT.setUpTab,Locators_ScoutFT.Safeguard);		
		Unselect_All_Apps_And_Check();
		test.log(LogStatus.PASS, "Test case status is Passed");	
	}
	
	@Test(priority=25,dataProvider="XP8_ScoutFTTest", dataProviderClass=DataProviders.class)
	public void XP8_ScoutFT_025_Validate_RestrictFeature_Check_Safeguard(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException
	{
		APP_LOGS.info("===========XP8_ScoutFT_025============");
		clearRecentApps();
		startAdbLog("XP8_ScoutFT_025");
		//("XP8_ScoutFT_025");
		launch_application(data.get("Application"));
		navigateToApps("Setup", data.get("summaryText"));
		//navigateApps(Locators_ScoutFT.setUpTab,Locators_ScoutFT.Safeguard);
		special_Case_Unselect();
		check_Features_Tab();
		test.log(LogStatus.PASS, "Test case status is Passed");	
	}
	
/*	@Test(priority=26,dataProvider="XP8_ScoutFTTest", dataProviderClass=DataProviders.class)
	public void XP8_ScoutFT_26_Validate_PinTimeout_Check_Safeguard(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException 
	{
		APP_LOGS.info("===========ScoutSanity_27============");
		clearRecentApps();
		startAdbLog("XP8_ScoutFT_026");
		launch_application(data.get("Application"));
		
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
	} */
	

	@Test(priority=27,dataProvider="XP8_ScoutFTTest", dataProviderClass=DataProviders.class)
	public void XP8_ScoutFT_27_Validate_Device_info_Self_Diagnosis_Screen(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException 
	{
		APP_LOGS.info("===========XP8_ScoutFT_027============");
		clearRecentApps();
		startAdbLog("XP8_ScoutFT_027");
		//("XP8_ScoutFT_027");
		launch_application(data.get("Application"));
		navigateToApps("Support", data.get("summaryText"));
		sonimCareSelect(data.get("sonimCareTest"));
		clickBtnWithText("Continue");
		validate_Device_Info_Self_Diagnosis_screen();
		test.log(LogStatus.PASS, "Test case status is Passed");
	}

	@Test(priority=28,dataProvider="XP8_ScoutFTTest", dataProviderClass=DataProviders.class)
	public void XP8_ScoutFT_28_SonimCare_Validate_Wifi_Scan_Test(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException 
	{
		APP_LOGS.info("===========XP8_ScoutFT_028============");
		clearRecentApps();
		startAdbLog("XP8_ScoutFT_028");
		launch_application(data.get("Application"));
		navigateToApps("Support", data.get("summaryText"));
		sonimCareSelect(data.get("sonimCareTest"));
		navigateToDiagnosisScreen();
		navigateToIndividualFeature(data.get("Feature"));
		click_On_coninueBtn_featureScreen();
		validate_wifiScan();
		
		test.log(LogStatus.PASS, "Test case status is Passed");
	}
	
	@Test(priority=29,dataProvider="XP8_ScoutFTTest", dataProviderClass=DataProviders.class)
	public void XP8_ScoutFT_29_SonimCare_Validate_BackCamera_Scan_Test(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException 
	{
		APP_LOGS.info("===========XP8_ScoutFT_029============");
		clearRecentApps();
		startAdbLog("XP8_ScoutFT_029");
		launch_application(data.get("Application"));
		navigateToApps("Support", data.get("summaryText"));
		sonimCareSelect(data.get("sonimCareTest"));
		navigateToDiagnosisScreen();
		navigateToIndividualFeature(data.get("Feature"));
		click_On_coninueBtn_featureScreen();
		validate_backCamera();
		test.log(LogStatus.PASS, "Test case status is Passed");
	}
	
	@Test(priority=30,dataProvider="XP8_ScoutFTTest", dataProviderClass=DataProviders.class)
	public void XP8_ScoutFT_30_SonimCare_Validate_Battery_Scan_Test(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException 
	{
		APP_LOGS.info("===========XP8_ScoutFT_030============");
		clearRecentApps();
		startAdbLog("XP8_ScoutFT_030");
		launch_application(data.get("Application"));
		navigateToApps("Support", data.get("summaryText"));
		sonimCareSelect(data.get("sonimCareTest"));
		navigateToDiagnosisScreen();
		navigateToIndividualFeature(data.get("Feature"));
		click_On_coninueBtn_featureScreen();
		validate_BatteryTest(data.get("bat_Status"), data.get("bat_status_value_1"),data.get("bat_status_value_2"), data.get("bat_Electricity"), data.get("bat_Electricity_value"));
		test.log(LogStatus.PASS, "Test case status is Passed");
	}
	
	@Test(priority=31,dataProvider="XP8_ScoutFTTest", dataProviderClass=DataProviders.class)
	public void XP8_ScoutFT_31_SonimCare_Validate_Clear_CacheMemory_Test(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException 
	{
		APP_LOGS.info("===========XP8_ScoutFT_031============");
		clearRecentApps();
		startAdbLog("XP8_ScoutFT_031");
		launch_application(data.get("Application"));
		navigateToApps("Support", data.get("summaryText"));
		sonimCareSelect(data.get("sonimCareTest"));
		navigateToDiagnosisScreen();
		navigateToIndividualFeature(data.get("Feature"));
		click_On_coninueBtn_featureScreen();
		validate_ClearCacheMemory();
		test.log(LogStatus.PASS, "Test case status is Passed");
	}
	
	@Test(priority=32,dataProvider="XP8_ScoutFTTest", dataProviderClass=DataProviders.class)
	public void XP8_ScoutFT_32_SonimCare_Validate_Bluetooth_Scan_Test(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException 
	{
		APP_LOGS.info("===========XP8_ScoutFT_032============");
		clearRecentApps();
		startAdbLog("XP8_ScoutFT_032");
		launch_application(data.get("Application"));
		navigateToApps("Support", data.get("summaryText"));
		sonimCareSelect(data.get("sonimCareTest"));
		navigateToDiagnosisScreen();
		navigateToIndividualFeature(data.get("Feature"));
		click_On_coninueBtn_featureScreen();
		validate_bluetooth_scan();
		test.log(LogStatus.PASS, "Test case status is Passed");
	}
	
	@Test(priority=33,dataProvider="XP8_ScoutFTTest", dataProviderClass=DataProviders.class)
	public void XP8_ScoutFT_33_SonimCare_Validate_HyperLinks_Alerts_wifiFeature(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException 
	{
		APP_LOGS.info("===========XP8_ScoutFT_033============");
		clearRecentApps();
		startAdbLog("XP8_ScoutFT_033");
		launch_application(data.get("Application"));
		navigateToApps("Support", data.get("summaryText"));
		sonimCareSelect(data.get("sonimCareTest"));
		navigateToDiagnosisScreen();
		navigateToIndividualFeature(data.get("Feature"));
		click_On_coninueBtn_featureScreen();
		validate_wifi_links("wi-fi");
		test.log(LogStatus.PASS, "Test case status is Passed");
	}
	
	@Test(priority=34,dataProvider="XP8_ScoutFTTest", dataProviderClass=DataProviders.class)
	public void XP8_ScoutFT_34_SonimCare_Validate_HyperLinks_Alerts_battery_Feature(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException 
	{
		APP_LOGS.info("===========XP8_ScoutFT_034============");
		clearRecentApps();
		startAdbLog("XP8_ScoutFT_034");
		launch_application(data.get("Application"));
		navigateToApps("Support", data.get("summaryText"));
		sonimCareSelect(data.get("sonimCareTest"));
		navigateToDiagnosisScreen();
		navigateToIndividualFeature(data.get("Feature"));
		click_On_coninueBtn_featureScreen();
		validate_battery_links(data.get("Feature"));
		test.log(LogStatus.PASS, "Test case status is Passed");
	}
	
	@Test(priority=35,dataProvider="XP8_ScoutFTTest", dataProviderClass=DataProviders.class)
	public void XP8_ScoutFT_35_SonimCare_Validate_HyperLinks_Alerts_Self_Diagnosis_Screen(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException 
	{
		APP_LOGS.info("===========XP8_ScoutFT_035============");
		clearRecentApps();
		startAdbLog("XP8_ScoutFT_035");
		launch_application(data.get("Application"));
		navigateToApps("Support", data.get("summaryText"));
		sonimCareSelect(data.get("sonimCareTest"));
		navigateUsingText("Continue");
		validate_hyperTexts_alert_selfDiagnosisPage();
		test.log(LogStatus.PASS, "Test case status is Passed");
	}

	
	@Test(priority=36,dataProvider="XP8_ScoutFTTest", dataProviderClass=DataProviders.class)
	public void XP8_ScoutFT_36_SonimCare_Validate_Wifi_No_btn_Functionality(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException 
	{
		APP_LOGS.info("===========XP8_ScoutFT_036============");
		clearRecentApps();
		startAdbLog("XP8_ScoutFT_036");
		launch_application(data.get("Application"));
		navigateToApps("Support", data.get("summaryText"));
		sonimCareSelect(data.get("sonimCareTest"));
		navigateToDiagnosisScreen();
		navigateToIndividualFeature(data.get("Feature"));
		click_On_coninueBtn_featureScreen();
		clickBtnWithText("SCAN FOR MORE NETWORKS");
		
	
		validate_No_btn_feature("wi-fi");
		test.log(LogStatus.PASS, "Test case status is Passed");
	}
	
	@Test(priority=37,dataProvider="XP8_ScoutFTTest", dataProviderClass=DataProviders.class)
	public void XP8_ScoutFT_37_SonimCare_Validate_Bluetooth_No_btn_Functionality(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException 
	{
		APP_LOGS.info("===========XP8_ScoutFT_037============");
		clearRecentApps();
		startAdbLog("XP8_ScoutFT_037");
		launch_application(data.get("Application"));
		navigateToApps("Support", data.get("summaryText"));
		sonimCareSelect(data.get("sonimCareTest"));
		navigateToDiagnosisScreen();
		navigateToIndividualFeature(data.get("Feature"));
		click_On_coninueBtn_featureScreen();
		validate_No_btn_feature("bluetooth");
		test.log(LogStatus.PASS, "Test case status is Passed");
	}
	
	@Test(priority=38,dataProvider="XP8_ScoutFTTest", dataProviderClass=DataProviders.class)
	public void XP8_ScoutFT_38_SonimCare_Validate_Battery_diagnosis_Screen(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException 
	{
		APP_LOGS.info("===========XP8_ScoutFT_038============");
		clearRecentApps();
		startAdbLog("XP8_ScoutFT_038");
		launch_application(data.get("Application"));
		navigateToApps("Support", data.get("summaryText"));
		sonimCareSelect(data.get("sonimCareTest"));
		click_On_coninueBtn_Bat_Scan();
		validate_batteryDiagnosis();
		test.log(LogStatus.PASS, "Test case status is Passed");
	}
	
	@Test(priority=39,dataProvider="XP8_ScoutFTTest", dataProviderClass=DataProviders.class)
	public void XP8_ScoutFT_39_SonimCare_Validate_Battery_Service_History(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException 
	{
		APP_LOGS.info("===========XP8_ScoutFT_038============");
		clearRecentApps();
		startAdbLog("ScoutFunctional_40");
		//("ScoutFunctional_40");
		launch_application(data.get("Application"));
		navigateToApps("Support", data.get("summaryText"));
		sonimCareSelect(data.get("sonimCareTest"));
		click_On_coninueBtn_Bat_Scan();
		validate_battery_service_history();
		test.log(LogStatus.PASS, "Test case status is Passed");
	}

}
