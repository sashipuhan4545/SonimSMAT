package com.xp8.test;

import java.awt.AWTException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.util.Hashtable;
import java.util.Properties;
import java.util.Timer;
import java.util.TimerTask;

import org.json.simple.parser.ParseException;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.graphics.gui.JsonFileReaderAndWriter;
//import com.graphics.gui.JsonFileReaderAndWriter;
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

import application.AllQA;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import javafx.application.Platform;

public class XP8_Contacts_Test extends XP8_Contacts_Util{

	public ExcelReader excel;
	Properties properties;
	public static ExtentTestInterruptedException testexception;
	public int itr =AllQA.NUMOFCALLS;

	@BeforeSuite
	public void beforeSuite() throws FileNotFoundException, bsh.ParseException, InterruptedException, IOException, ParseException 
	{

		try {
			extent = new ExtentReports("src/test/resources/extentreport/XP8_Contact_Stability.html", true); //Provide Desired Report Directory Location and Name
			extent.loadConfig(new File("src/test/resources/StorageFile/ReportsConfig.xml"));
			extent.addSystemInfo("Build #",JsonFileReaderAndWriter.primaryDevFirmwareReader())
			.addSystemInfo("Product",JsonFileReaderAndWriter.primaryDevModelReader())
			.addSystemInfo("Operator", JsonFileReaderAndWriter.primaryDevOperatorReader());	
			fetch_Devices_Details();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}


	@BeforeSuite
	public void numofTestCases() throws ClassNotFoundException {

		appiumService.TOTAL_NUM_OF_TESTCASES=GetMethods.TotalTestcase("XP8_", this.getClass());
	}
	/*@BeforeClass
	public void copyFilesToDevice() throws IOException {

		Runtime.getRuntime().exec("adb -s "+p_Id+" push src/test/resources/contactTransferFile/i475.csv /storage/emulated/0/Download");
		Runtime.getRuntime().exec("adb -s "+p_Id+" push src/test/resources/contactTransferFile/i475.mdb /storage/emulated/0/Download");
		Runtime.getRuntime().exec("adb -s "+p_Id+" push src/test/resources/contactTransferFile/i475.vcf /storage/emulated/0/Download");
		Runtime.getRuntime().exec("adb -s "+p_Id+" push src/test/resources/contactTransferFile/i476.vcf /storage/emulated/0/Download");
	}*/

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
		
	}
	
	
	@BeforeTest
	public void appendresult() {
		
		Timer t=new Timer();
		t.schedule(new TimerTask() {

			public void run() {

				
			extent.endTest(test);
			extent.flush();
					
			}
		},  0, 10*(100*1)); 
		
	}

	@BeforeTest
	public void setupSys() throws MalformedURLException, InterruptedException, FileNotFoundException, AWTException{
		properties=loadDriverAndProperties();

		Locators_Contacts loc=new Locators_Contacts(aDriver);
		Locators_BaseUtil loc1 = new Locators_BaseUtil(aDriver);	
		PageFactory.initElements(new AppiumFieldDecorator(aDriver),loc);
		PageFactory.initElements(new AppiumFieldDecorator(aDriver),loc1);
		excel=new ExcelReader(ExcelConstants.XP5S_XL_PATH);	
	}

	@BeforeTest
	public void t_Select_Phone_Sim_To_Insert_Contact() {

		try {

		//	clearRecentApps();
			//clearDataContact();
			WebDriverWait wait=new WebDriverWait(aDriver, 10);
			if(JsonFileReaderAndWriter.primaryDevFirmwareReader().contains("-10")) {

				launch_an_app("contacts");
				wait.until(ExpectedConditions.visibilityOf(Locators_Contacts.addContact1));
				clickBtn(multi_Loc_Strategy(Locators_Contacts.addContact1,Locators_Contacts.addContact2,
						null,null,null, 864,1704));

				if(isElementExist(Locators_Contacts.newcontactwillsavetophonbook)) {
					clickBtn(Locators_Contacts.OK);
				}



				if(isElementExist(Locators_Contacts.PHONE_RadioBtn)) {

					clickBtn(Locators_Contacts.PHONE_RadioBtn);
				}else if (isElementExist(Locators_Contacts.sim)) {

					clickBtn(Locators_Contacts.downarray);
					clickBtn(Locators_Contacts.PHONE_RadioBtn);
				}else if (isElementExist(Locators_Contacts.simcard)) {

					clickBtn(Locators_Contacts.PHONE_RadioBtn);
				}


			}else {

				launch_an_app("contacts");
				wait.until(ExpectedConditions.visibilityOf(Locators_Contacts.addContact1));
				clickBtn(multi_Loc_Strategy(Locators_Contacts.addContact1,Locators_Contacts.addContact2,
						null,null,null, 864,1704));
				minWait();
				if(isElementExist(Locators_Contacts.newcontactwillsavetophonbook)) {
					clickBtn(Locators_Contacts.OK);
				}
				minWait();

				/*	if(!JsonFileReaderAndWriter.PrimaryDevicesimAvailability().contains("")) {

					System.out.println("does not contain sim->");

				}else {*/

				System.out.println("Else part");

				if(isElementExist(Locators_Contacts.PHONE_RadioBtn)) {

					clickBtn(Locators_Contacts.PHONE_RadioBtn);
				}else if (isElementExist(Locators_Contacts.sim)) {

					clickBtn(Locators_Contacts.downarray);
					clickBtn(Locators_Contacts.PHONE_RadioBtn);
				}else if (isElementExist(Locators_Contacts.simcard)) {

					clickBtn(Locators_Contacts.PHONE_RadioBtn);
				}







			}



		}catch (Exception e) {
			System.out.println("Exeption in t_Select_Phone_Sim_To_Insert_Contact() ");
		}

	}



	@Test(priority=1,dataProvider="XP8_Contacts",dataProviderClass=DataProviders.class)
	public void XP8_Add_Contact_To_PhoneBook(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException 
	{
		APP_LOGS.info("===========Contacts_01============");
		startAdbLog("Contacts_01");
		SoftAssert sa=new SoftAssert();

		clearRecentApps();

		for(int i=1;i<=itr;i++) {

			createContactsFromAdbCommands(sa,i);


		}


		sa.assertAll();










	}







}
