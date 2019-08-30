package com.xp8.test;

import java.awt.AWTException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.util.Hashtable;
import java.util.Properties;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidKeyCode;

import org.openqa.selenium.remote.DesiredCapabilities;
//import io.appium.java_client.android.nativekey.AndroidKey;
//import io.appium.java_client.android.nativekey.KeyEvent;

import org.json.simple.parser.ParseException;
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
import com.xp8.util.DataProviders;
import com.xp8.util.ExcelConstants;
import com.xp8.util.ExcelReader;
import com.xp8.util.Locators_BaseUtil;
import com.xp8.util.Locators_Contacts;
import com.xp8.util.XP8_Contacts_Util;
import com.xp8.util.XP8_Phone_Contacts_Util;

import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class XP8_Phone_Contacts_Test extends XP8_Phone_Contacts_Util{

	public ExcelReader excel;
	Properties properties;
	public static ExtentReports extent;
	public static ExtentTestInterruptedException testexception;
	//public AndroidDriver<AndroidElement> aDriver;

	@BeforeSuite
	public void beforeSuite() throws FileNotFoundException, bsh.ParseException, InterruptedException, IOException, ParseException 
	{
		extent = new ExtentReports("src/test/resources/extentreport/XP8_Phone_Contacts.html", true); //Provide Desired Report Directory Location and Name
		extent.loadConfig(new File("src/test/resources/StorageFile/ReportsConfig.xml"));
		extent.addSystemInfo("Build #",JsonFileReaderAndWriter.primaryDevFirmwareReader())
		.addSystemInfo("Product",JsonFileReaderAndWriter.primaryDevModelReader())
		.addSystemInfo("Operator", JsonFileReaderAndWriter.primaryDevOperatorReader());	
		fetch_Devices_Details();
		
		try {
			appiumService.TOTAL_NUM_OF_TESTCASES=GetMethods.TotalTestcase("XP8_Contacts", this.getClass());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@BeforeClass
	public void copyFilesToDevice() throws IOException {

		Runtime.getRuntime().exec("adb -s "+p_Id+" push src/test/resources/contactTransferFile/i475.csv /storage/emulated/0/Download");
		Runtime.getRuntime().exec("adb -s "+p_Id+" push src/test/resources/contactTransferFile/i475.mdb /storage/emulated/0/Download");
		Runtime.getRuntime().exec("adb -s "+p_Id+" push src/test/resources/contactTransferFile/i475.vcf /storage/emulated/0/Download");
		Runtime.getRuntime().exec("adb -s "+p_Id+" push src/test/resources/contactTransferFile/i476.vcf /storage/emulated/0/Download");
	}

	@BeforeMethod()
	public  void beforeMethod(Method method) 
	{
		test = extent.startTest( (this.getClass().getSimpleName() +" :: "+  method.getName()),method.getName()); //Test Case Start Here

	}

	@AfterMethod()
	public void tearDown(ITestResult result,Method method) throws IOException, InterruptedException
	{ 
		if(result.getStatus()==ITestResult.FAILURE)
		{	
			String screenshot_path=captureScreenshot(method.getName());
			String image= test.addScreenCapture(screenshot_path);		
			test.log(LogStatus.FAIL,result.getThrowable());		
			clearRecentApps();			
		}
		extent.endTest(test);
		extent.flush();
	}

	@BeforeTest
	public void setupSys() throws MalformedURLException, InterruptedException, FileNotFoundException, AWTException{
		properties=loadDriverAndProperties();
		//aDriver = (AndroidDriver<AndroidElement>)driver;
		Locators_Contacts loc=new Locators_Contacts(aDriver);
		Locators_BaseUtil loc1 = new Locators_BaseUtil(aDriver);	
		PageFactory.initElements(new AppiumFieldDecorator(aDriver),loc);
		PageFactory.initElements(new AppiumFieldDecorator(aDriver),loc1);
		excel=new ExcelReader(ExcelConstants.XP5S_XL_PATH);	
	}

	@Test(priority=1,dataProvider="XP8_Phone_Contacts",dataProviderClass=DataProviders.class)
	public void XP8_Contacts_001_Importing_VCF_File(Hashtable<String, String> data) throws InterruptedException, 
	AWTException, IOException, ParseException 
	{
		SoftAssert sa = new SoftAssert();
		APP_LOGS.info("===========Contacts_01============");
		startAdbLog("Contacts_01");
		clearRecentApps();
		launch_an_app("contacts");
		deleteContacts();
		navigateToMoreOptions("Settings","Import");
		importContacts(sa);
		make_Call_from_RefDev();
		recieve_Call_PrimaryDev_O();
		endCall_RefDevice();
		validateImportContacts(sa);
		sa.assertAll();
	}


	@Test(priority= 2,dataProvider="XP8_Phone_Contacts",dataProviderClass=DataProviders.class)
	public void XP8_Contacts_002_Exporting_VCF_File(Hashtable<String, String> data) throws InterruptedException,
	AWTException, IOException, ParseException 
	{
		SoftAssert sa = new SoftAssert();
		APP_LOGS.info("===========Contacts_02============");
		startAdbLog("Contacts_02");
		clearRecentApps();
		launch_an_app("contacts");
		navigateToMoreOptions("Settings","Export");
		exportContacts(sa);
		make_Call_from_RefDev();
		recieve_Call_PrimaryDev_O();
		endCall_RefDevice();
		sa.assertAll();
	}

	@Test(priority= 3,dataProvider="XP8_Phone_Contacts",dataProviderClass=DataProviders.class)
	public void XP8_Contacts_003_Importing_110VCF_File(Hashtable<String, String> data) throws InterruptedException,
	AWTException, IOException, ParseException 
	{
		SoftAssert sa = new SoftAssert();
		APP_LOGS.info("===========Contacts_03============");
		startAdbLog("Contacts_03");
		clearRecentApps();
		launch_an_app("contacts");
		deleteContacts();
		navigateToMoreOptions("Settings","Import");
		importContacts(sa);
		validateImportContacts110(sa);  // need a .vcf file which contains 2000 contact
		sa.assertAll();
	}

	@Test(priority= 4,dataProvider="XP8_Phone_Contacts",dataProviderClass=DataProviders.class)
	public void XP8_Contacts_004_Importing_from_SIM_card(Hashtable<String, String> data) throws InterruptedException, 
	AWTException, IOException, ParseException 
	{
		SoftAssert sa = new SoftAssert();
		APP_LOGS.info("===========Contacts_04============");
		startAdbLog("Contacts_04");
		clearRecentApps();
		launch_an_app("contacts");
		deleteContacts();
		addcontactsTO_SIM(data.get("Username1"), data.get("Phone1"), data.get("Username2"), data.get("Phone2"),sa);
		navigateToMoreOptions("Settings","Import");
		importSIMContacts();
		make_Call_from_RefDev();
		recieve_Call_PrimaryDev_O();
		endCall_RefDevice();
		validateImportfromSIMContacts(sa);  //need to check
		sa.assertAll();
	}

	@Test(priority= 5,dataProvider="XP8_Phone_Contacts",dataProviderClass=DataProviders.class)
	public void XP8_Contacts_005_Exporting_to_SIM_card(Hashtable<String, String> data) 
			throws InterruptedException, AWTException, IOException, ParseException 
	{
		SoftAssert sa = new SoftAssert();
		APP_LOGS.info("===========Contacts_05============");
		startAdbLog("Contacts_05");
		clearRecentApps();
		launch_an_app("contacts");
		deleteContacts();
		addcontactsTO_Phone(data.get("Username1"), data.get("Phone1"),data.get("Email1"), data.get("Username2"), 
				data.get("Phone2"),data.get("Email2"));  
		navigateToMoreOptions("Settings","Export");
		exportContactstoSIM(sa); 
		make_Call_from_RefDev();
		recieve_Call_PrimaryDev_O();
		endCall_RefDevice();
		validateExportContactstoSIM(sa);  //need to check
		sa.assertAll();
	}

	@Test(priority= 6,dataProvider="XP8_Phone_Contacts",dataProviderClass=DataProviders.class)
	public void XP8_Contacts_006_Edit_searched_contact(Hashtable<String, String> data) 
			throws InterruptedException, AWTException, IOException, ParseException 
	{
		SoftAssert sa = new SoftAssert();
		APP_LOGS.info("===========Contacts_06============");
		startAdbLog("Contacts_06");
		clearRecentApps();
		launch_an_app("contacts");
		deleteContacts();
		addcontactTO_Phone(data.get("Username1"), data.get("Phone1"),data.get("Email1")); 
		editcontactinfo(data.get("Username2"),data.get("Phone2"),data.get("Email2"));
		editRingtone(sa);
		validateContactEdit(sa);
		sa.assertAll();
	}

	@Test(priority= 15,dataProvider="XP8_Phone_Contacts",dataProviderClass=DataProviders.class)
	public void XP8_Contacts_007_Send_SMS_from_favorite_contact(Hashtable<String, String> data) 
			throws InterruptedException, AWTException, IOException, ParseException 
	{
		SoftAssert sa = new SoftAssert();
		APP_LOGS.info("===========Contacts_07============");
		startAdbLog("Contacts_07");
		clearRecentApps();
		launch_an_app("contacts");
		deleteContacts();
		addcontactTO_Phone(data.get("Username1"), data.get("Phone1"),data.get("Email1")); 
		MakefavoriteContact(sa);
		sendMessageFromContacts(data.get("Message"),sa);	
		sa.assertAll();
	}

	@Test(priority= 8,dataProvider="XP8_Phone_Contacts",dataProviderClass=DataProviders.class)
	public void XP8_Contacts_008_Block_and_Unblock_contact(Hashtable<String, String> data) 
			throws InterruptedException, AWTException, IOException, ParseException 
	{
		SoftAssert sa = new SoftAssert();
		APP_LOGS.info("===========Contacts_08============");
		startAdbLog("Contacts_08");
		clearRecentApps();
		Set_Sleeptime_30min();
//		Set_Sleeptime_30sec();
		launch_an_app("contacts");
		deleteContacts();
		addcontactTO_Phone(data.get("Username1"), data.get("Phone1"),data.get("Email1")); 
		BlockNumber(data.get("Phone1"),sa);
		UnBlockNumber(data.get("Phone1"),sa);
		sa.assertAll();
	}

	//	@Test(priority= 9,dataProvider="XP8_Phone_Contacts",dataProviderClass=DataProviders.class)
	//	public void XP8_Contacts_009_Link_and_UnLink_contact(Hashtable<String, String> data) 
	//			throws InterruptedException, AWTException, IOException, ParseException 
	//	{
	//	    SoftAssert sa = new SoftAssert();
	//		APP_LOGS.info("===========Contacts_09============");
	//		startAdbLog("Contacts_09");
	//		clearRecentApps();
	//		launch_an_app("contacts");
	//    	deleteContacts();
	//    	addcontactsTO_Phone(data.get("Username1"), data.get("Phone1"),data.get("Email1"), data.get("Username2"), 
	//				data.get("Phone2"),data.get("Email2"));
	//    	LinkNumber(data.get("Phone1"));
	//    	UnLinkNumber(data.get("Phone1"));
	//   	sa.assertAll();
	//     }

	@Test(priority= 10,dataProvider="XP8_Phone_Contacts",dataProviderClass=DataProviders.class)
	public void XP8_Contacts_010_Verify_name_Format_of_contact(Hashtable<String, String> data) 
			throws InterruptedException, AWTException, IOException, ParseException 
	{
		SoftAssert sa = new SoftAssert();
		APP_LOGS.info("===========Contacts_10============");
		startAdbLog("Contacts_10");
		clearRecentApps();
		launch_an_app("contacts");
		deleteContacts();
		addcontact_Phone(data.get("Firstname"),data.get("Lastname"), data.get("Phone1"));
		navigateToNameFormatOptions();
		Change_format_to_Lastnamefirst_verify(data.get("name"),sa);
		navigateToNameFormatOptions();
		Change_format_to_Firstnamefirst_verify(data.get("name1"),sa);
		sa.assertAll();
	}

	@Test(priority= 11,dataProvider="XP8_Phone_Contacts",dataProviderClass=DataProviders.class)
	public void XP8_Contacts_011_Verify_Sortby_of_contact(Hashtable<String, String> data) 
			throws InterruptedException, AWTException, IOException, ParseException 
	{
		SoftAssert sa = new SoftAssert();
		APP_LOGS.info("===========Contacts_11============");
		startAdbLog("Contacts_11");
		clearRecentApps();
		
//		System.out.println("app install done");
//    	aDriver.installApp(".\\src\\test\\resources\\documents\\MemoryFill_com_rektgames_memoryfill.apk");
//	    System.out.println("app install done");
//	    minWait();
		
		launch_an_app("contacts");
		deleteContacts();
		addcontact_Phone(data.get("Firstname"),data.get("Lastname"), data.get("Phone1"));
		navigateToSortbyOptions();
		Change_Sortby_Lastname_verify(data.get("name"),sa);
		navigateToSortbyOptions();
		Change_Sortby_Firstname_verify(data.get("name1"),sa);
		sa.assertAll();
	}

	@Test(priority= 12,dataProvider="XP8_Phone_Contacts",dataProviderClass=DataProviders.class)
	public void XP8_Contacts_012_Add_and_Edit_Myinfo(Hashtable<String, String> data) 
			throws InterruptedException, AWTException, IOException, ParseException 
	{
		SoftAssert sa = new SoftAssert();
		APP_LOGS.info("===========Contacts_12============");
		startAdbLog("Contacts_12");
		clearRecentApps();
		launch_an_app("contacts");
		deleteContacts();
		navigateToMyinfoOptions();
		Add_Myinfo_if_not_present(data.get("Firstname1"),data.get("Lastname1"), data.get("Phone1"),
				data.get("Profile"),sa);
		Edit_Myinfo(data.get("Firstname2"),data.get("Lastname2"), data.get("Phone2"),sa);
		sa.assertAll();
	}

	@Test(priority= 13,dataProvider="XP8_Phone_Contacts",dataProviderClass=DataProviders.class)
	public void XP8_Contacts_013_Add_contact_with_Picture(Hashtable<String, String> data) 
			throws InterruptedException, AWTException, IOException, ParseException 
	{
		SoftAssert sa = new SoftAssert();
		APP_LOGS.info("===========Contacts_13============");
		
		startAdbLog("Contacts_13");
		clearRecentApps();
		launch_an_app("contacts");
		deleteContacts();
		addcontactwith_Picture(data.get("Firstname1"),data.get("Lastname1"), data.get("Phone1"),sa);
		sa.assertAll();
	}

	@Test(priority= 14,dataProvider="XP8_Phone_Contacts",dataProviderClass=DataProviders.class)
	public void XP8_Contacts_014_Add_10contacts_with_10fields(Hashtable<String, String> data) 
			throws InterruptedException, AWTException, IOException, ParseException 
	{	
		SoftAssert sa = new SoftAssert();
		APP_LOGS.info("===========Contacts_14============");
		startAdbLog("Contacts_14");
		clearRecentApps();
		launch_an_app("contacts");
		deleteContacts();
		addcontactTO_Phone_with_10fields(data.get("Firstname1"), data.get("Lastname1"),data.get("Company"),data.get("Phone"),data.get("SIP"),data.get("Email"),data.get("Address"),data.get("IM"),data.get("Website"),data.get("Notes"));
		addcontactTO_Phone_with_10fields(data.get("Firstname2"), data.get("Lastname2"),data.get("Company"),data.get("Phone"),data.get("SIP"),data.get("Email"),data.get("Address"),data.get("IM"),data.get("Website"),data.get("Notes"));
		addcontactTO_Phone_with_10fields(data.get("Firstname3"), data.get("Lastname3"),data.get("Company"),data.get("Phone"),data.get("SIP"),data.get("Email"),data.get("Address"),data.get("IM"),data.get("Website"),data.get("Notes"));
		addcontactTO_Phone_with_10fields(data.get("Firstname4"), data.get("Lastname4"),data.get("Company"),data.get("Phone"),data.get("SIP"),data.get("Email"),data.get("Address"),data.get("IM"),data.get("Website"),data.get("Notes"));
		addcontactTO_Phone_with_10fields(data.get("Firstname5"), data.get("Lastname5"),data.get("Company"),data.get("Phone"),data.get("SIP"),data.get("Email"),data.get("Address"),data.get("IM"),data.get("Website"),data.get("Notes"));
		addcontactTO_Phone_with_10fields(data.get("Firstname6"), data.get("Lastname6"),data.get("Company"),data.get("Phone"),data.get("SIP"),data.get("Email"),data.get("Address"),data.get("IM"),data.get("Website"),data.get("Notes"));
		addcontactTO_Phone_with_10fields(data.get("Firstname7"), data.get("Lastname7"),data.get("Company"),data.get("Phone"),data.get("SIP"),data.get("Email"),data.get("Address"),data.get("IM"),data.get("Website"),data.get("Notes"));
		addcontactTO_Phone_with_10fields(data.get("Firstname8"), data.get("Lastname8"),data.get("Company"),data.get("Phone"),data.get("SIP"),data.get("Email"),data.get("Address"),data.get("IM"),data.get("Website"),data.get("Notes"));
		addcontactTO_Phone_with_10fields(data.get("Firstname9"), data.get("Lastname9"),data.get("Company"),data.get("Phone"),data.get("SIP"),data.get("Email"),data.get("Address"),data.get("IM"),data.get("Website"),data.get("Notes"));
		addcontactTO_Phone_with_10fields(data.get("Firstname10"), data.get("Lastname10"),data.get("Company"),data.get("Phone"),data.get("SIP"),data.get("Email"),data.get("Address"),data.get("IM"),data.get("Website"),data.get("Notes"));
		sa.assertAll();
	}
	
	
}
