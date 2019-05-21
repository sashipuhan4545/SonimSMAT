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

import com.graphics.gui.JsonFileReaderAndWriter;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.ExtentTestInterruptedException;
import com.relevantcodes.extentreports.LogStatus;
import com.xp8.util.DataProviders;
import com.xp8.util.ExcelConstants;
import com.xp8.util.ExcelReader;
import com.xp8.util.Locators_BaseUtil;
import com.xp8.util.Locators_Contacts;
import com.xp8.util.XP8_Contacts_Util;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class XP8_Contacts_Test extends XP8_Contacts_Util{
	
	public ExcelReader excel;
	Properties properties;
	public static ExtentReports extent;
	public static ExtentTestInterruptedException testexception;
	//public AndroidDriver<AndroidElement> aDriver;

	@BeforeSuite
	public void beforeSuite() throws FileNotFoundException, bsh.ParseException, InterruptedException, IOException, ParseException 
	{
		extent = new ExtentReports("src/test/resources/extentreport/XP8_CallModule_Contacts_TestReport.html", true); //Provide Desired Report Directory Location and Name
		extent.loadConfig(new File("src/test/resources/StorageFile/ReportsConfig.xml"));
		extent.addSystemInfo("Build #",JsonFileReaderAndWriter.primaryDevFirmwareReader())
		.addSystemInfo("Product",JsonFileReaderAndWriter.primaryDevModelReader())
		.addSystemInfo("Operator", JsonFileReaderAndWriter.primaryDevOperatorReader());	
		fetch_Devices_Details();

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
	
	@Test(priority=1,dataProvider="XP8_Contacts",dataProviderClass=DataProviders.class)
	public void XP8_Contacts_001_Importing_VCF_File(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException 
	{
		APP_LOGS.info("===========Contacts_01============");
		startAdbLog("Contacts_01");
		clearRecentApps();
		launch_an_app("contacts");
		deleteContacts();
		navigateToMoreOptions("Import");
		importContacts();
		make_Call_from_RefDev();
		recieve_Call_PrimaryDev_O();
		endCall_RefDevice();
		validateImportContacts();
	}
	

	@Test(priority=2,dataProvider="XP8_Contacts",dataProviderClass=DataProviders.class)
	public void XP8_Contacts_002_Exporting_VCF_File(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException 
	{
		APP_LOGS.info("===========Contacts_02============");
		startAdbLog("Contacts_02");
		clearRecentApps();
		launch_an_app("contacts");
		navigateToMoreOptions("Export");
		exportContacts();
		make_Call_from_RefDev();
		recieve_Call_PrimaryDev_O();
		endCall_RefDevice();
	}
	
	
	

}
