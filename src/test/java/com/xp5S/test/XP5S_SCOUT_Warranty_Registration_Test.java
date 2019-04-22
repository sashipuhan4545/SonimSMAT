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

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTestInterruptedException;
import com.relevantcodes.extentreports.LogStatus;
import com.xp3.Utils.Locators_Warranty_Registration;
import com.xp5S.util.DataProviders;
import com.xp5S.util.ExcelConstants;
import com.xp5S.util.ExcelReader;
import com.xp5S.util.GetMethods;
import com.xp5S.util.Locators_BaseUtil;
import com.xp5S.util.Locators_WarrantyReg;
import com.xp5S.util.Locators_WarrantyReg;
import com.xp5S.util.XP5S_SCOUT_Warranty_Registration_Util;
import com.xp5S.util.appiumService;

import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class XP5S_SCOUT_Warranty_Registration_Test extends XP5S_SCOUT_Warranty_Registration_Util {
	public ExcelReader excel;
	Properties properties;
	public static ExtentTestInterruptedException testexception;


	@BeforeClass
	public void copyFilesToDevice() throws IOException {
		System.out.println("Executing clear log screen");
		File dir = new File("src/test/resources/adbLogs");
		if(dir.isDirectory() == false) {
			System.out.println("Not a directory. Do nothing");
			return;
		}
		//Added comment to test
		File[] listFiles = dir.listFiles();
		for(File file : listFiles){
			System.out.println("Deleting "+file.getName());
			file.delete();
		}
	}

	@BeforeSuite
	public void beforeSuite() throws IOException, InterruptedException, ParseException 
	{
		extent = new ExtentReports("src/test/resources/extentreport/XP5S_SCOUT_Warranty_Registration_TestReport.html", true); 
		fetch_Devices_Details();
		try {
			appiumService.TOTAL_NUM_OF_TESTCASES=GetMethods.TotalTestcase("XP5S_TC_", this.getClass());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@BeforeMethod()
	public  void beforeMethod(Method method) throws InterruptedException 
	{

		test = extent.startTest( (this.getClass().getSimpleName() +" :: "+  method.getName()),method.getName()); //Test Case Start Here
		test.assignAuthor(""); //Test Script Author Name
		extent.loadConfig(new File("src/test/resources/StorageFile/ReportsConfig.xml"));
		extent.addSystemInfo("Build", p_b_No).addSystemInfo("Operator", "Mainline").addSystemInfo("Appium Version", "1.2.7").addSystemInfo("Environment", "TEST");
	}


	@AfterMethod()
	public void tearDown(ITestResult result,Method method) throws IOException, InterruptedException
	{
		if(result.getStatus()==ITestResult.FAILURE)
		{			

			String screenshot_path=captureScreenshot(method.getName());
			String image= test.addScreenCapture(screenshot_path);
			test.log(LogStatus.FAIL,result.getThrowable());
			test.log(LogStatus.FAIL, "screenshot for failure:" +test.addScreenCapture(image));
			clearRecentApps();

		}
		extent.endTest(test);
		extent.flush();
	}


	@BeforeTest
	public void setupSys() throws MalformedURLException, InterruptedException, FileNotFoundException, AWTException{
		properties=loadDriverAndProperties();
		Locators_WarrantyReg loc=new Locators_WarrantyReg(aDriver);
		Locators_BaseUtil loc1 =new Locators_BaseUtil(aDriver);
		PageFactory.initElements(new AppiumFieldDecorator(getDriver()),loc1);
		PageFactory.initElements(new AppiumFieldDecorator(getDriver()),loc);
		excel=new ExcelReader(ExcelConstants.XP5S_XL_PATH);
		preconditionSetUp();
	}


	//================================================Test cases==============================================
	@Test(priority=1,dataProvider="XP5S_WarrantyRegistration", dataProviderClass=DataProviders.class)
	public void XP5S_TC_001_SWR_Launch_Warranty_Registration_Application_from_SCOUTAPP(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException {

		APP_LOGS.info("=========== XP5S_TC_001_SWR_Launch_Warranty_Registration_Application_from_SCOUTAPP============");
		startAdbLog("XP5S_WarrantyRegistration_001");
		Launch_Warranty_Registration();
		waitUntilElementclickable(Locators_WarrantyReg.WarrantyReg);
		validateLaunchScoutApp(Locators_WarrantyReg.WarrantyReg, "Warranty Registration");
	}
	
	@Test(priority=2,dataProvider="XP5S_WarrantyRegistration", dataProviderClass=DataProviders.class)
	public void XP5S_TC_002_SWR_Validate_Now_Later_Never_Options(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException {

		APP_LOGS.info("=========== XP5S_TC_002_SWR_Validate_Now_Later_Never_Options============");
		startAdbLog("XP5S_WarrantyRegistration_002");
		Launch_Warranty_Registration();
		waitUntilElementclickable(Locators_WarrantyReg.WarrantyReg);
		validateNowOption();
		minWait();
		Launch_Warranty_Registration();
		waitUntilElementclickable(Locators_WarrantyReg.WarrantyReg);
		validateLaterOption();
		customWait(10000);
		Launch_Warranty_Registration();
		waitUntilElementclickable(Locators_WarrantyReg.WarrantyReg);
		validateNeverOption();
	}

	@Test(priority=3,dataProvider="XP5S_WarrantyRegistration", dataProviderClass=DataProviders.class)
	public void XP5S_TC_003_SWR_Validate_Reset_button_with_yes_or_no_functionality(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException {

		APP_LOGS.info("=========== XP5S_TC_003_SWR_Validate_Reset_button_with_yes_or_no_functionality============");
		startAdbLog("XP5S_WarrantyRegistration_003");
		Launch_Warranty_Registration();
		waitUntilElementclickable(Locators_WarrantyReg.WarrantyReg);
		validateResetButton();
	}

	@Test(priority=4,dataProvider="XP5S_WarrantyRegistration", dataProviderClass=DataProviders.class)
	public void XP5S_TC_004_SWR_Validate_Upload_Files_option_with_take_a_picture(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException {

		APP_LOGS.info("=========== XP5S_TC_004_SWR_Validate_Upload_Files_option_with_take_a_picture============");
		startAdbLog("XP5S_WarrantyRegistration_004");
		Launch_Warranty_Registration();
		waitUntilElementclickable(Locators_WarrantyReg.WarrantyReg);
		validatePressenceOfUploadFiles();
		validateTakeAPicture();
	}
	
	@Test(priority=5,dataProvider="XP5S_WarrantyRegistration", dataProviderClass=DataProviders.class)
	public void XP5S_TC_005_SWR_Validate_Upload_Files_option_with_different_file_types(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException {

		APP_LOGS.info("=========== XP5S_TC_005_SWR_Validate_Upload_Files_option_with_different_file_types============");
		startAdbLog("XP5S_WarrantyRegistration_005");
		Launch_Warranty_Registration();
		waitUntilElementclickable(Locators_WarrantyReg.WarrantyReg);
		validateFileType();
		validateInvalidFileType();
	}

	@Test(priority=6,dataProvider="XP5S_WarrantyRegistration", dataProviderClass=DataProviders.class)
	public void XP5S_TC_006_SWR_Validate_all_mandatory_fields(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException {

		APP_LOGS.info("=========== XP5S_TC_006_SWR_Validate_all_mandatory_fields============");
		startAdbLog("XP5S_WarrantyRegistration_006");
		Launch_Warranty_Registration();
		waitUntilElementclickable(Locators_WarrantyReg.WarrantyReg);
		validateEachMandatoryField(data.get("firstname"), data.get("lastname"), data.get("country"), data.get("company") , data.get("state"),data.get("city"), data.get("zipCode"),data.get("address"),data.get("phoneNumber"),data.get("email_id"));
	}

	@Test(priority=7,dataProvider="XP5S_WarrantyRegistration", dataProviderClass=DataProviders.class)
	public void XP5S_TC_007_SWR_Display_Warranty_Registration_Version(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException {

		APP_LOGS.info("=========== XP5S_TC_007_SWR_Display_Warranty_Registration_Version============");
		startAdbLog("XP5S_WarrantyRegistration_007");
		Launch_Warranty_Registration();
		waitUntilElementclickable(Locators_WarrantyReg.WarrantyReg);
		getVersion();
	}
	
	@Test(priority=8,dataProvider="XP5S_WarrantyRegistration", dataProviderClass=DataProviders.class)
	public void XP5S_TC_008_SWR_Validate_About(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException {

		APP_LOGS.info("=========== XP5S_TC_008_SWR_Validate_About============");
		startAdbLog("XP5S_WarrantyRegistration_008");
		Launch_Warranty_Registration();
		waitUntilElementclickable(Locators_WarrantyReg.WarrantyReg);
		validateAbout();
	}
	
	@Test(priority=9,dataProvider="XP5S_WarrantyRegistration", dataProviderClass=DataProviders.class)
	public void XP5S_TC_009_SWR_Fill_all_the_fields_and_validate_submit_Button(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException {

		APP_LOGS.info("=========== XP5S_TC_009_SWR_Fill_all_the_fields_and_validate_submit_Button============");
		startAdbLog("XP5S_WarrantyRegistration_009");
		Launch_Warranty_Registration();
		waitUntilElementclickable(Locators_WarrantyReg.WarrantyReg);
		fillAllTheFields(data.get("firstname"), data.get("lastname"), data.get("country"), data.get("company") , data.get("state"),data.get("city"), data.get("zipCode"),data.get("address1"),data.get("address2"),data.get("phoneNumber"),data.get("email_id"),data.get("dealerName"));
		validateSubmitOptionWithNoOption();
		minWait();
		validateSubmitOptionWithYesOption();
	}

	@Test(priority=10,dataProvider="XP5S_WarrantyRegistration", dataProviderClass=DataProviders.class)
	public void XP5S_TC_010_SWR_Validate_User_Registered_Information(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException {

		APP_LOGS.info("=========== XP5S_TC_010_SWR_Validate_User_Registered_Information============");
		startAdbLog("XP5S_WarrantyRegistration_010");
		Launch_Warranty_Registration();
		waitUntilElementclickable(Locators_WarrantyReg.WarrantyReg);
		validateRegisteredInfo();
		deleteFiles();
	}
	
}
