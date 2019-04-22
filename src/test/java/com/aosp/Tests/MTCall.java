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
import org.openqa.selenium.support.PageFactory;
import org.testng.ITestResult;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aosp.Utils.AOSP_Locators_SMS_MMS;
import com.aosp.Utils.Locators_Sanity;
import com.aosp.Utils.XP5S_Data_Locator;
import com.aosp.Utils.XP5S_Data_Utils;
import com.aosp.Utils.XP5S_Sanity_Util_Oreo;
import com.graphics.gui.JsonFileReaderAndWriter;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTestInterruptedException;
import com.relevantcodes.extentreports.LogStatus;
import com.xp3.Utils.XP3_Device_Sanity_Util;
import com.xp5S.util.ExcelConstants;
import com.xp5S.util.ExcelReader;
import com.xp5S.util.GetMethods;
import com.xp5S.util.Locators_BaseUtil;
import com.xp5S.util.appiumService;
import com.xp8.util.Locators_XP8_Sanity;
import com.xp8.util.XP8_Sanity_Util;

import application.AllQA;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class MTCall extends XP5S_Data_Utils {


	XP5S_Data_Locator locators;
	public ExcelReader excel;
	Properties properties;

	public static ExtentTestInterruptedException testexception;
	
	XP8_Sanity_Util xp8=new XP8_Sanity_Util();
	XP5S_Sanity_Util_Oreo xp5=new XP5S_Sanity_Util_Oreo();
	com.xp3.Utils.BaseUtil xp3=new com.xp3.Utils.BaseUtil();


	@BeforeSuite
	public void beforeSuite()
	{
		extent = new ExtentReports("src/test/resources/extentreport/MT_CallPerformance_Report.html", true);

	}

	@BeforeMethod()
	public  void beforeMethod(Method method) throws InterruptedException, FileNotFoundException, IOException, ParseException 	
	{

		test = extent.startTest((method.getName()),method.getName()); //Test Case Start Here
		extent.loadConfig(new File("src/test/resources/StorageFile/ReportsConfig.xml"));

		extent.addSystemInfo("Build #",JsonFileReaderAndWriter.primaryDevFirmwareReader())
		.addSystemInfo("Product",JsonFileReaderAndWriter.primaryDevModelReader())
		.addSystemInfo("Operator", JsonFileReaderAndWriter.primaryDevOperatorReader());


	}


	@BeforeSuite
	public void numofTestCases() throws ClassNotFoundException {

		appiumService.TOTAL_NUM_OF_TESTCASES=GetMethods.TotalTestcase("Validate_MT_Call", this.getClass());


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
						// clear screen
			clearRecentApps();
		}
		extent.endTest(test);
		extent.flush();
	}

	@BeforeTest
	public void a_setupSys() throws MalformedURLException, InterruptedException, FileNotFoundException, AWTException{

		properties=loadDriverAndProperties();
		XP5S_Data_Locator loc=new XP5S_Data_Locator(aDriver);
		Locators_BaseUtil loc1 =new Locators_BaseUtil(aDriver);
		AOSP_Locators_SMS_MMS loc2=new AOSP_Locators_SMS_MMS(aDriver);
		PageFactory.initElements(new AppiumFieldDecorator(getDriver()),loc);
		PageFactory.initElements(new AppiumFieldDecorator(getDriver()),loc1);
		PageFactory.initElements(new AppiumFieldDecorator(getDriver()),loc2);
		
		XP5S_Data_Locator loc0=new XP5S_Data_Locator(aDriver);
		Locators_BaseUtil loc13 =new Locators_BaseUtil(aDriver);
		AOSP_Locators_SMS_MMS loc23=new AOSP_Locators_SMS_MMS(aDriver);

		PageFactory.initElements(new AppiumFieldDecorator(getDriver()),loc0);
		PageFactory.initElements(new AppiumFieldDecorator(getDriver()),loc13);
		PageFactory.initElements(new AppiumFieldDecorator(getDriver()),loc23);


		Locators_XP8_Sanity loc12 = new Locators_XP8_Sanity(aDriver);
		com.xp8.util.Locators_BaseUtil loc14 =new com.xp8.util.Locators_BaseUtil(aDriver);
		PageFactory.initElements(new AppiumFieldDecorator(aDriver), loc12);
		PageFactory.initElements(new AppiumFieldDecorator(aDriver), loc14);

		com.aosp.Utils.Locators_Sanity xp58=new com.aosp.Utils.Locators_Sanity(aDriver);
		PageFactory.initElements(new AppiumFieldDecorator(aDriver), xp58);

		com.xp3.Utils.Locators_Sanity xp3=new com.xp3.Utils.Locators_Sanity(aDriver);
		PageFactory.initElements(new AppiumFieldDecorator(aDriver), xp3);
		com.xp3.Utils.Locators_BaseUtil xp31 =new com.xp3.Utils.Locators_BaseUtil(aDriver);
		PageFactory.initElements(new AppiumFieldDecorator(aDriver), xp31);
		com.xp3.Utils.Locators_DevSanity xp32 = new com.xp3.Utils.Locators_DevSanity(aDriver);
		PageFactory.initElements(new AppiumFieldDecorator(aDriver), xp32);
		excel=new ExcelReader(ExcelConstants.XP5S_XL_PATH);		
	}
	
	
	public void enableData_Pref_Network_Type_To_LTE() throws Exception {

		try {
			xp8.clearRecentApps_O();
			xp8.launch_APP_Only_For_CallPerformance(Locators_XP8_Sanity.settings);
			xp8.clickOn_Networks_and_Internet();
			xp8.scrollToText("Mobile network");
			xp8.minWait();
			xp8.ON_Switch("Mobile data");
			xp8.clickBtn(Locators_XP8_Sanity.down_Triangle);
			xp8.minWait();
			xp8.scrollToText("Preferred network type");
			xp8.minWait();
			xp8.scrollToTextContains("LTE");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	

	@BeforeTest
	public void b_set_NetworkType_To_3G_OR_VOLTE() throws Exception{

		//Here when user selects 3G call-performance from the combobox 
		if(AllQA.comboBoxItems.equalsIgnoreCase("3G-CallPerformance")) 
		{


			if(JsonFileReaderAndWriter.RefDeviceModelNum().contains("xp8800") || JsonFileReaderAndWriter.RefDeviceModelNum().contains("80k"))
			{

				if(!JsonFileReaderAndWriter.ReadRefDeviceFirmWare().contains("-10"))
				{     

					xp8.acceptPlayprotect();
					xp8.unlock_Phone();

					xp8.launch_APP_Only_For_CallPerformance(Locators_XP8_Sanity.settings);
					xp8.clickOn_Networks_and_Internet();
					xp8.scrollToText("Mobile network");
					xp8.minWait();
					xp8.clickBtn(Locators_XP8_Sanity.down_Triangle);
					xp8.minWait();
					xp8.scrollToText("Preferred network type");
					xp8.minWait();
					xp8.scrollToTextContains("3G");
					customWait(10000);


					if(!Locators_XP8_Sanity.PreferrednetworkType_3G.getText().contains("3G"))
					{
						throw new RuntimeException();
					}

				}

			}else if (JsonFileReaderAndWriter.RefDeviceModelNum().contains("xp5800") || JsonFileReaderAndWriter.RefDeviceModelNum().contains("50k"))
			{

				XP5S_Sanity_Util_Oreo xp5=new XP5S_Sanity_Util_Oreo();

				launch_an_app("settings");

				if(JsonFileReaderAndWriter.ReadRefDeviceFirmWare().contains("-15.")) {

					try {
						xp5.changePreferredNetworkTypeVZW();
						xp5.navigateToSystemSettings(Locators_Sanity.systemInsettingsList);
						navigateToSettingsFeature(Locators_Sanity.AboutPhone,"About phone");
						xp5.validateLTESelection("3G");
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}else if(JsonFileReaderAndWriter.ReadRefDeviceFirmWare().contains("-29.")) {

					try {
						xp5.navigateTo_NetworkSettings();
						minWait();	
						xp5.changeNetwork(Locators_Sanity.NetwrkType_3G);
						xp5.clickBackButton(2);	
						xp5.navigateToSystemSettings(Locators_Sanity.systemInsettingsList);
						xp5.navigateToSettingsFeature(Locators_Sanity.AboutPhone,"About phone");	
						xp5.validateLTESelection("HSPA");
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}


				} else {

					try {
						System.out.println("----------------------------------------------------");
						xp5.navigateTo_NetworkSettings();
						minWait();	
						xp5.changeNetwork(Locators_Sanity.CDMA_Combo_NetwrkTyp);
						xp5.clickBackButton(2);	
						xp5.navigateToSystemSettings(Locators_Sanity.systemInsettingsList);
						xp5.navigateToSettingsFeature(Locators_Sanity.AboutPhone,"About phone");	
						xp5.validateLTESelection("HSPA");
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}



				}

			}else if (JsonFileReaderAndWriter.RefDeviceModelNum().contains("xp3800") || JsonFileReaderAndWriter.RefDeviceModelNum().contains("3A.")) {

				try {

					XP3_Device_Sanity_Util xp3=new XP3_Device_Sanity_Util();
					if(JsonFileReaderAndWriter.ReadRefDeviceFirmWare().contains("-10.")) {

						xp3.launch_an_app("settings");
						xp3.scrollToText("Network & Internet");
						minWait();
						xp3.scrollToText("Mobile network");
						minWait();
						xp3.scrollText("Access Point Names");
						minWait();
						xp3.scrollToText("Preferred network type");
						minWait();
						com.xp3.Utils.Locators_Sanity.threeGoption.click();
						customWait(10000);
					}
					else {

						xp3.launch_an_app("settings");
						APP_LOGS.info("Inside changeNetworkTypeSP");
						xp3.scrollToText("Network & Internet");
						minWait();
						xp3.scrollToText("Mobile network");
						minWait();
						//scrollText("Roaming");
						minWait();
						xp3.scrollToText("Preferred network type");
						minWait();

						com.xp3.Utils.Locators_Sanity.twoGoption.click();
						customWait(10000);

					}

				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}



			}

			//Here when user selects Volte call-performance from the combobox 
		}else if (AllQA.comboBoxItems.equalsIgnoreCase("VOLTE-CallPerformance")) {




			if(JsonFileReaderAndWriter.RefDeviceModelNum().contains("xp8800") || JsonFileReaderAndWriter.RefDeviceModelNum().contains("80k"))
			{


				try {

					if(!JsonFileReaderAndWriter.ReadRefDeviceFirmWare().contains("-10.")) {

						enableData_Pref_Network_Type_To_LTE();//This is as a precondition
						customWait(2000);

					}

					String fN = startRIL_Log_Call_Perfomrance_MT();
					xp8.launch_APP_Only_For_CallPerformance(Locators_XP8_Sanity.settings);
					xp8.clickOn_Networks_and_Internet();
					xp8.ON_Switch("Airplane mode");
					xp8.clickBtn(Locators_XP8_Sanity.OK);
					xp8.OFF_Switch("Airplane mode");
					customWait(7000);

					if(searchString("RIL_REQUEST_IMS_REGISTRATION_STATE {1, 1} [SUB0]",fN) || searchString("RIL_REQUEST_IMS_REGISTRATION_STATE {1, 2} [SUB0]",fN))
					{

						System.out.println("ITS IMS ");
						if(!JsonFileReaderAndWriter.ReadRefDeviceFirmWare().contains("-10"))
						{     

							System.out.println("ATT");
							xp8.acceptPlayprotect();
							xp8.scrollToText("Mobile network");
							xp8.minWait();
							xp8.clickBtn(Locators_XP8_Sanity.down_Triangle);
							xp8.minWait();
							xp8.scrollToText("Preferred network type");
							xp8.minWait();
							xp8.scrollToTextContains("LTE");
							customWait(10000);
						}
						
					}else {


						System.out.println("ITS not");
						throw new SkipException("Sim is not IMS registred");

					}


				} catch (Exception e) {
					
					throw new SkipException("Sim is not IMS registred");
				}



			}else if (JsonFileReaderAndWriter.RefDeviceModelNum().contains("xp5800") || JsonFileReaderAndWriter.RefDeviceModelNum().contains("50k"))
			{



				try {

					String fN1 = startRIL_Log_Call_Perfomrance_MT();
					launch_an_app("settings");
					xp5.navigateToNetworkAndInternetOptions();
					xp5.navigateToAirplaneMode();
					xp8.ON_Switch("Airplane mode");
					xp8.OFF_Switch("Airplane mode");
					customWait(7000);
					

					if(searchString("RIL_REQUEST_IMS_REGISTRATION_STATE {1, 1} [SUB0]",fN1) || searchString("RIL_REQUEST_IMS_REGISTRATION_STATE {1, 2} [SUB0]",fN1)) {


						System.out.println("SIM is IMS registred");


					}else {


						System.out.println("SIM is not IMS registred");
						throw new SkipException("Sim is not IMS registred");
					}
				} catch (Exception e) {
					e.printStackTrace();
					throw new SkipException("Sim is not IMS registred");

				}



			}else if (JsonFileReaderAndWriter.RefDeviceModelNum().contains("xp3800") || JsonFileReaderAndWriter.RefDeviceModelNum().contains("3A."))
			{

				try {



					xp3.launch_an_app("settings");
					xp3.scrollToText("Network & Internet");
					String fN1 = startRIL_Log_Call_Perfomrance_MT();
					xp3.scrollToAirplaneModeOption("Airplane mode");
					xp8.ON_Switch("Airplane mode");
					xp8.OFF_Switch("Airplane mode");
					customWait(7000);
					
					if(searchString("RIL_REQUEST_IMS_REGISTRATION_STATE {1, 1} [SUB0]",fN1) || searchString("RIL_REQUEST_IMS_REGISTRATION_STATE {1, 2} [SUB0]",fN1)) {

						System.out.println("IMS REGISTER");


					}else {

						System.out.println("SIM is not IMS registred");
						throw new SkipException("Sim is not IMS registred");

					}

				} catch (Exception e) {
					e.printStackTrace();
					throw new SkipException("Sim is not IMS registred");
				}



			}

		}

	}




	
	
	




	@Test(dataProvider="XP5S_aosp_Data", dataProviderClass=com.aosp.Utils.DataProviders.class)
	public void Validate_MT_Call(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException {

		APP_LOGS.info("===========Aosp_XP5S_TestCases===========");

		if(JsonFileReaderAndWriter.primaryDevModelReader().contains("xp8800") || JsonFileReaderAndWriter.primaryDevModelReader().contains("80k")) {

			MT();
			test.log(LogStatus.PASS, "Test case status is Passed");
		}
		else if (JsonFileReaderAndWriter.primaryDevModelReader().contains("xp5800") || JsonFileReaderAndWriter.primaryDevModelReader().contains("50k")) {

			if(JsonFileReaderAndWriter.ReadRefDeviceFirmWare().contains("-7.")) {

				MT_XP5(28);

				test.log(LogStatus.PASS, "Test case status is Passed");
			}else if (JsonFileReaderAndWriter.ReadRefDeviceFirmWare().contains("-8.")) {

				MT_XP5(30);
				test.log(LogStatus.PASS, "Test case status is Passed");

			}




		} else if (JsonFileReaderAndWriter.primaryDevModelReader().contains("xp3800") || JsonFileReaderAndWriter.primaryDevFirmwareReader().contains("3A.")) {

			//The below method is used to originate MO call from DUT to Ref and pass '30' as argument(30 is for android go)

			MT_XP5(30);
			test.log(LogStatus.PASS, "Test case status is Passed");

		}



	}

}
