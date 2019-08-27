package com.xp8.test;

import java.awt.AWTException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
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
import org.testng.asserts.SoftAssert;

import com.graphics.gui.JsonFileReaderAndWriter;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTestInterruptedException;
import com.relevantcodes.extentreports.LogStatus;
import com.xp8.util.DataProviders;
import com.xp8.util.ExcelConstants;
import com.xp8.util.ExcelReader;

import com.xp8.util.Locators_BaseUtil;
import com.xp8.util.Locators_DataUsageSetting;
import com.xp8.util.XP8_Data_Usage_Setting_Util;

import application.AllQA;
import io.appium.java_client.android.AndroidKeyCode;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class XP8_Data_Usage_Setting_Test extends XP8_Data_Usage_Setting_Util{

	public ExcelReader excel;
	Properties properties;
	public static ExtentTestInterruptedException testexception;
	public  String SSID=AllQA.SSID;
	public  String PWD=AllQA.WIFIPASSWORD;
	public  String videoName="sonim xp8";
	public  int durationInSeconds=240;		//to play four minutes
	public int itr=1;

	@BeforeSuite
	public void beforeSuite() throws FileNotFoundException, InterruptedException, IOException, ParseException  
	{
		extent = new ExtentReports("src/test/resources/extentreport/XP8_Data_Usage_Setting_TestReport.html", true); //Provide Desired Report Directory Location and Name
		extent.loadConfig(new File("src/test/resources/StorageFile/ReportsConfig.xml"));
		extent.addSystemInfo("Build #",JsonFileReaderAndWriter.primaryDevFirmwareReader())
		.addSystemInfo("Product",JsonFileReaderAndWriter.primaryDevModelReader())
		.addSystemInfo("Operator", JsonFileReaderAndWriter.primaryDevOperatorReader());			
		fetch_Devices_Details();	
		
		
		/*try {
			appiumService.TOTAL_NUM_OF_TESTCASES=GetMethods.TotalTestcase("XP8_TC", this.getClass());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	}

	
	
	@BeforeMethod()
	public  void beforeMethod(Method method) 
	{
		test = extent.startTest( (this.getClass().getSimpleName() +" :: "+  method.getName()),method.getName()); //Test Case Start Here
	//	test.assignAuthor("Farheen Taj"); //Test Script Author Name

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
		{	String screenshot_path=captureScreenshot(method.getName());
		    String image= test.addScreenCapture(screenshot_path);		
		    test.log(LogStatus.FAIL,result.getThrowable());						
		}
		extent.endTest(test);
		extent.flush();
	}


	@BeforeTest
	public void setupSys() throws InterruptedException, AWTException, IOException{
		properties=loadDriverAndProperties();
		Locators_DataUsageSetting loc=new Locators_DataUsageSetting(aDriver);
		Locators_BaseUtil loc1 = new Locators_BaseUtil(aDriver);	
		PageFactory.initElements(new AppiumFieldDecorator(aDriver),loc);
		PageFactory.initElements(new AppiumFieldDecorator(aDriver),loc1);
		excel=new ExcelReader(ExcelConstants.XP5S_XL_PATH);	
		

	}
	@Test(priority=0,dataProvider="XP8_Data_Usage_Setting", dataProviderClass=DataProviders.class)
	public void XP8_TC_00_Data_Usage_Setting_PreConditions(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException
	{  
		APP_LOGS.info("===========XP8_TC_00_Data_Usage_Setting_PreConditions============");
		System.out.println("Precondition start");
		SoftAssert sa=new SoftAssert();
		preCond_setSleepTime();
		preCond_enableData();
		preCond_browseData(videoName,durationInSeconds);
		preCond_enableWifi(SSID,PWD);
		preCond_browseData(videoName,durationInSeconds);
		verify_PostConditionExcuted(sa);
		sa.assertAll();
		System.out.println("Precondition end");
	}
	//============================================================Test Case=======================================================================================
	@Test(priority=1,dataProvider="XP8_Data_Usage_Setting", dataProviderClass=DataProviders.class)
	public void XP8_TC_01_Data_Usage_Setting_Validate_DataUsageSetting(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException
	{  
		APP_LOGS.info("===========XP8_TC_01_Data_Usage_Setting_Validate_DataUsageSetting============");
		SoftAssert sa=new SoftAssert();
		clearRecentApps();
		if(JsonFileReaderAndWriter.primaryDevFirmwareReader().contains("-10")) {		//ATT operator
	
			for(int i=1;i<=itr;i++) {
			//navigate to data usage setting  and validate data usage setting
			launch_an_app("settings");
			clickOn_NetworkAndInternet();
			clickOn_DataUsage();
			validate_DataUsageSetting(sa);
			
			//navigate to data usage settings when airplane mode is enabled
			launch_an_app("settings");
			clickOn_NetworkAndInternet();
			enable_AirplaneMode();
			
			drag_NotificationBar();
			clickOn_RSSIIcon();
			clickOn_NetworkAndInternet();
			clickOn_DataUsage();
			validate_DataUsageSetting_AirplaneMode(sa);
		}}
		else if(JsonFileReaderAndWriter.primaryDevFirmwareReader().contains("-11")) {	//BELL operator
	
			for(int i=1;i<=itr;i++) {
			//navigate to data usage setting  and validate data usage setting
			launch_an_app("settings");
			clickOn_NetworkAndInternet();
			clickOn_DataUsage();
			validate_DataUsageSetting(sa);
			
			//navigate to data usage settings when airplane mode is enabled
			launch_an_app("settings");
			clickOn_NetworkAndInternet();
			enable_AirplaneMode();
			
			drag_NotificationBar();
			clickOn_RSSIIcon();
			clickOn_NetworkAndInternet();
			clickOn_DataUsage();
			validate_DataUsageSetting_AirplaneMode(sa);
		}}
		else if(JsonFileReaderAndWriter.primaryDevFirmwareReader().contains("-15")) {		//VERISON operator
			
			for(int i=1;i<=itr;i++) {
			//navigate to data usage setting  and validate data usage setting
			launch_an_app("settings");
			clickOn_NetworkAndInternet();
			clickOn_DataUsage();
			validate_DataUsageSetting(sa);
			
			//navigate to data usage settings when airplane mode is enabled
			launch_an_app("settings");
			clickOn_NetworkAndInternet();
			enable_AirplaneMode();
			
			drag_NotificationBar();
			clickOn_RSSIIcon();
			clickOn_NetworkAndInternet();
			clickOn_DataUsage();
			validate_DataUsageSetting_AirplaneMode(sa);
		}}
		else if(JsonFileReaderAndWriter.primaryDevFirmwareReader().contains("-26")) {		//SL operator
			
			for(int i=1;i<=itr;i++) {
			//navigate to data usage setting  and validate data usage setting
			launch_an_app("settings");
			clickOn_NetworkAndInternet();
			clickOn_DataUsage();
			validate_DataUsageSetting(sa);
			
			//navigate to data usage settings when airplane mode is enabled
			launch_an_app("settings");
			clickOn_NetworkAndInternet();
			enable_AirplaneMode();
			
			drag_NotificationBar();
			clickOn_RSSIIcon();
			clickOn_NetworkAndInternet();
			clickOn_DataUsage();
			validate_DataUsageSetting_AirplaneMode(sa);
		}}
		else if(JsonFileReaderAndWriter.primaryDevFirmwareReader().contains("-29")) {		//SPRINT operator
			
			for(int i=1;i<=itr;i++) {
			//navigate to data usage setting  and validate data usage setting
			launch_an_app("settings");
			clickOn_NetworkAndInternet();
			clickOn_DataUsage();
			validate_DataUsageSetting(sa);
			
			//navigate to data usage settings when airplane mode is enabled
			launch_an_app("settings");
			clickOn_NetworkAndInternet();
			enable_AirplaneMode();
			
			drag_NotificationBar();
			clickOn_RSSIIcon();
			clickOn_NetworkAndInternet();
			clickOn_DataUsage();
			validate_DataUsageSetting_AirplaneMode(sa);
		}}
		else {					//ALL other operator
			
			for(int i=1;i<=itr;i++) {
			//navigate to data usage setting  and validate data usage setting
			launch_an_app("settings");
			clickOn_NetworkAndInternet();
			clickOn_DataUsage();
			validate_DataUsageSetting(sa);
			
			//navigate to data usage settings when airplane mode is enabled
			launch_an_app("settings");
			clickOn_NetworkAndInternet();
			enable_AirplaneMode();
			
			drag_NotificationBar();
			clickOn_RSSIIcon();
			clickOn_NetworkAndInternet();
			clickOn_DataUsage();
			validate_DataUsageSetting_AirplaneMode(sa);
		}}

		launch_an_app("settings");
		clickOn_NetworkAndInternet();
		disable_AirplaneMode();
		sa.assertAll();
	}
	@Test(priority=2,dataProvider="XP8_Data_Usage_Setting", dataProviderClass=DataProviders.class)
	public void XP8_TC_02_Data_Usage_Setting_Enable_Disable_CellularData(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException
	{  
		//Enable Disable Mobile data from data usage window
		APP_LOGS.info("===========XP8_TC_02_Data_Usage_Setting============");
		SoftAssert sa=new SoftAssert();
		clearRecentApps();
		if(JsonFileReaderAndWriter.primaryDevFirmwareReader().contains("-10")) {	//ATT operator
			for(int i=1;i<=itr;i++) {
	
			//Enable and disable mobile data from mobile networks
			launch_an_app("settings");
			clickOn_NetworkAndInternet();
			clickOn_DataUsage();
			clickOn_DataUsage_SubModules("mobile networks");
			boolean enabled=enable_MobileData();
			validate_MobileData_IsEnabled(enabled, "Data usage window", sa);
			//validate mobile data usage graph 
			clickOn_BackBtn();
			clickOn_DataUsage_SubModules("mobile data usage");
			verify_DataUsageGraph(sa);
			verify_DataUsageAppListWithAppData(sa);
			clickOn_BackBtn();
			clickOn_DataUsage_SubModules("mobile networks");
			boolean disabled=disable_MobileData();
			validate_MobileData_IsDisabled(disabled, "Data usage window", sa);
			}}
		else if(JsonFileReaderAndWriter.primaryDevFirmwareReader().contains("-11")) {	//BELL operator
			for(int i=1;i<=itr;i++) {
	
			//Enable and disable mobile data from mobile networks
			launch_an_app("settings");
			clickOn_NetworkAndInternet();
			clickOn_DataUsage();
			boolean enabled=enable_MobileData();
			validate_MobileData_IsEnabled(enabled, "Data usage window", sa);
			//validate mobile data usage graph 
			clickOn_DataUsage_SubModules("mobile data usage");
			verify_DataUsageGraph(sa);
			verify_DataUsageAppListWithAppData(sa);
			clickOn_BackBtn();
			boolean disabled=disable_MobileData();
			validate_MobileData_IsDisabled(disabled, "Data usage window", sa);
			}}
		else if(JsonFileReaderAndWriter.primaryDevFirmwareReader().contains("-15")) {	//VERISON operator
			for(int i=1;i<=itr;i++) {
	
			//Enable and disable mobile data from mobile networks
			launch_an_app("settings");
			clickOn_NetworkAndInternet();
			clickOn_DataUsage();
			boolean enabled=enable_MobileData();
			validate_MobileData_IsEnabled(enabled, "Data usage window", sa);
			//validate mobile data usage graph 
			clickOn_DataUsage_SubModules("mobile data usage");
			verify_DataUsageGraph(sa);
			verify_DataUsageAppListWithAppData(sa);
			clickOn_BackBtn();
			boolean disabled=disable_MobileData();
			validate_MobileData_IsDisabled(disabled, "Data usage window", sa);
			}}
		else if(JsonFileReaderAndWriter.primaryDevFirmwareReader().contains("-26")) {	//SL operator
			for(int i=1;i<=itr;i++) {
	
			//Enable and disable mobile data from mobile networks
			launch_an_app("settings");
			clickOn_NetworkAndInternet();
			clickOn_DataUsage();
			boolean enabled=enable_MobileData();
			validate_MobileData_IsEnabled(enabled, "Data usage window", sa);
			//validate mobile data usage graph 
			clickOn_DataUsage_SubModules("mobile data usage");
			verify_DataUsageGraph(sa);
			verify_DataUsageAppListWithAppData(sa);
			clickOn_BackBtn();
			boolean disabled=disable_MobileData();
			validate_MobileData_IsDisabled(disabled, "Data usage window", sa);
			}}
		else if(JsonFileReaderAndWriter.primaryDevFirmwareReader().contains("-29")) {	//SPRINT operator
			for(int i=1;i<=itr;i++) {
	
			//Enable and disable mobile data from mobile networks
			launch_an_app("settings");
			clickOn_NetworkAndInternet();
			clickOn_DataUsage();
			boolean enabled=enable_MobileData();
			validate_MobileData_IsEnabled(enabled, "Data usage window", sa);
			//validate mobile data usage graph 
			clickOn_DataUsage_SubModules("mobile data usage");
			verify_DataUsageGraph(sa);
			verify_DataUsageAppListWithAppData(sa);
			clickOn_BackBtn();
			boolean disabled=disable_MobileData();
			validate_MobileData_IsDisabled(disabled, "Data usage window", sa);
			}}
		else  {
			for(int i=1;i<=itr;i++) {													//other operator
	
			//Enable and disable mobile data from mobile networks
			launch_an_app("settings");
			clickOn_NetworkAndInternet();
			clickOn_DataUsage();
			boolean enabled=enable_MobileData();
			validate_MobileData_IsEnabled(enabled, "Data usage window", sa);
			//validate mobile data usage graph 
			clickOn_DataUsage_SubModules("mobile data usage");
			verify_DataUsageGraph(sa);
			verify_DataUsageAppListWithAppData(sa);
			clickOn_BackBtn();
			boolean disabled=disable_MobileData();
			validate_MobileData_IsDisabled(disabled, "Data usage window", sa);
			}}

		sa.assertAll();

	/*	//Enable and disable mobile data from notification window
		boolean enable,disable;
		dragNotification_Twice();
		boolean isMobileDataPresent=search_MobileDataIcon();	//searching mobile data icon in notification window
		if(isMobileDataPresent) {								//enable and disable mobile data if mobile data icon is present
		enable=enable_MobileData_FromNotificationWindow();
		validate_MobileData_IsEnabled(enable,"Notification window",sa);
		customWait(2000);
		disable=disable_MobileData_FromNotificationWindow();
		validate_MobileData_IsDisabled(disable,"Notification window",sa);
		}else {													//else , swipe right to left to get mobile data icon and enable disable mobile data
			swipe_Left();
			enable=enable_MobileData_FromNotificationWindow();
			validate_MobileData_IsEnabled(enable,"Notification window",sa);customWait(2000);
			disable=disable_MobileData_FromNotificationWindow();
			validate_MobileData_IsDisabled(disable,"Notification window",sa);
			}
	*/	
		}
	@Test(priority=3,dataProvider="XP8_Data_Usage_Setting", dataProviderClass=DataProviders.class)
	public void XP8_TC_03_Data_Usage_Setting_Set_Data_Warning(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException
	{  
		APP_LOGS.info("===========XP8_TC_03_Data_Usage_Setting============");
		SoftAssert sa=new SoftAssert();
		clearRecentApps();
		//Enable set data warning, set data warning in mb , set data warning in gb , disable set data warning
		if(JsonFileReaderAndWriter.primaryDevFirmwareReader().contains("-10")) {
			for(int i=1;i<=itr;i++) {
	
			launch_an_app("settings");
			clickOn_NetworkAndInternet();
			clickOn_DataUsage();
			clickOn_DataUsage_SubModules("billing cycle");
			disable_SetDataLimit();
			boolean enabled = enable_SetDataWarning();
			verify_SetDataWarning_IsEnabled(enabled,sa);
			set_DataWarning(data.get("Data warning"),"gb");
			clickOn_BackBtn();
			validate_SetDataWarning(data.get("Data warning"),sa,"GB");
			clickOn_DataUsage_SubModules("billing cycle");
			set_DataWarning(data.get("Data warning"), "mb");
			clickOn_BackBtn();
			validate_SetDataWarning(data.get("Data warning"),sa,"MB");
			clickOn_DataUsage_SubModules("billing cycle");
			boolean disabled=disable_SetDataWarning();
			verify_SetDataWarning_IsDisabled(disabled,sa);
			verify_DataWarning_IsGreyedOut(sa);
		}}
		else if(JsonFileReaderAndWriter.primaryDevFirmwareReader().contains("-11")) {
			for(int i=1;i<=itr;i++) {
	
			launch_an_app("settings");
			clickOn_NetworkAndInternet();
			clickOn_DataUsage();
			clickOn_DataUsage_SubModules("billing cycle");
			disable_SetDataLimit();
			boolean enabled = enable_SetDataWarning();
			verify_SetDataWarning_IsEnabled(enabled,sa);
			set_DataWarning(data.get("Data warning"),"gb");
			clickOn_BackBtn();
			validate_SetDataWarning(data.get("Data warning"),sa,"GB");
			clickOn_DataUsage_SubModules("billing cycle");
			set_DataWarning(data.get("Data warning"), "mb");
			clickOn_BackBtn();
			validate_SetDataWarning(data.get("Data warning"),sa,"MB");
			clickOn_DataUsage_SubModules("billing cycle");
			boolean disabled=disable_SetDataWarning();
			verify_SetDataWarning_IsDisabled(disabled,sa);
			verify_DataWarning_IsGreyedOut(sa);
		}}
		else if(JsonFileReaderAndWriter.primaryDevFirmwareReader().contains("-15")) {
			for(int i=1;i<=itr;i++) {
	
			launch_an_app("settings");
			clickOn_NetworkAndInternet();
			clickOn_DataUsage();
			clickOn_DataUsage_SubModules("billing cycle");
			disable_SetDataLimit();
			boolean enabled = enable_SetDataWarning();
			verify_SetDataWarning_IsEnabled(enabled,sa);
			set_DataWarning(data.get("Data warning"),"gb");
			clickOn_BackBtn();
			validate_SetDataWarning(data.get("Data warning"),sa,"GB");
			clickOn_DataUsage_SubModules("billing cycle");
			set_DataWarning(data.get("Data warning"), "mb");
			clickOn_BackBtn();
			validate_SetDataWarning(data.get("Data warning"),sa,"MB");
			clickOn_DataUsage_SubModules("billing cycle");
			boolean disabled=disable_SetDataWarning();
			verify_SetDataWarning_IsDisabled(disabled,sa);
			verify_DataWarning_IsGreyedOut(sa);
		}}
		else if(JsonFileReaderAndWriter.primaryDevFirmwareReader().contains("-26")) {
			for(int i=1;i<=itr;i++) {
	
			launch_an_app("settings");
			clickOn_NetworkAndInternet();
			clickOn_DataUsage();
			clickOn_DataUsage_SubModules("billing cycle");
			disable_SetDataLimit();
			boolean enabled = enable_SetDataWarning();
			verify_SetDataWarning_IsEnabled(enabled,sa);
			set_DataWarning(data.get("Data warning"),"gb");
			clickOn_BackBtn();
			validate_SetDataWarning(data.get("Data warning"),sa,"GB");
			clickOn_DataUsage_SubModules("billing cycle");
			set_DataWarning(data.get("Data warning"), "mb");
			clickOn_BackBtn();
			validate_SetDataWarning(data.get("Data warning"),sa,"MB");
			clickOn_DataUsage_SubModules("billing cycle");
			boolean disabled=disable_SetDataWarning();
			verify_SetDataWarning_IsDisabled(disabled,sa);
			verify_DataWarning_IsGreyedOut(sa);
		}}
		else if(JsonFileReaderAndWriter.primaryDevFirmwareReader().contains("-29")) {
			for(int i=1;i<=itr;i++) {
	
			launch_an_app("settings");
			clickOn_NetworkAndInternet();
			clickOn_DataUsage();
			clickOn_DataUsage_SubModules("billing cycle");
			disable_SetDataLimit();
			boolean enabled = enable_SetDataWarning();
			verify_SetDataWarning_IsEnabled(enabled,sa);
			set_DataWarning(data.get("Data warning"),"gb");
			clickOn_BackBtn();
			validate_SetDataWarning(data.get("Data warning"),sa,"GB");
			clickOn_DataUsage_SubModules("billing cycle");
			set_DataWarning(data.get("Data warning"), "mb");
			clickOn_BackBtn();
			validate_SetDataWarning(data.get("Data warning"),sa,"MB");
			clickOn_DataUsage_SubModules("billing cycle");
			boolean disabled=disable_SetDataWarning();
			verify_SetDataWarning_IsDisabled(disabled,sa);
			verify_DataWarning_IsGreyedOut(sa);
		}}
		else {
			for(int i=1;i<=itr;i++) {
	
			launch_an_app("settings");
			clickOn_NetworkAndInternet();
			clickOn_DataUsage();
			clickOn_DataUsage_SubModules("billing cycle");
			disable_SetDataLimit();
			boolean enabled = enable_SetDataWarning();
			verify_SetDataWarning_IsEnabled(enabled,sa);
			set_DataWarning(data.get("Data warning"),"gb");
			clickOn_BackBtn();
			validate_SetDataWarning(data.get("Data warning"),sa,"GB");
			clickOn_DataUsage_SubModules("billing cycle");
			set_DataWarning(data.get("Data warning"), "mb");
			clickOn_BackBtn();
			validate_SetDataWarning(data.get("Data warning"),sa,"MB");
			clickOn_DataUsage_SubModules("billing cycle");
			boolean disabled=disable_SetDataWarning();
			verify_SetDataWarning_IsDisabled(disabled,sa);
			verify_DataWarning_IsGreyedOut(sa);
		}}
	
		sa.assertAll();
		
	}
	
	@Test(priority=4,dataProvider="XP8_Data_Usage_Setting", dataProviderClass=DataProviders.class)
	public void XP8_TC_04_Data_Usage_Setting_Set_Data_Usage_Cycle(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException
	{  
		SoftAssert sa=new SoftAssert();
		clearRecentApps();
		APP_LOGS.info("===========XP8_TC_04_Data_Usage_Setting============");
		//change data usage cycle date from 1-31 from billing cycle menu
		if(JsonFileReaderAndWriter.primaryDevFirmwareReader().contains("-10")) {
			for(int i=1;i<=itr;i++) {
	
			drag_NotificationBar();
			clickOn_QuickSettings();
			clickOn_NetworkAndInternet();
			clickOn_DataUsage();
			clickOn_BillingCycle();
			clickOn_BillingCycle();
			String date=change_DataUsageCycle(data.get("Date"));
			validate_DataUsageCycle(date, sa);
		}}
		else if(JsonFileReaderAndWriter.primaryDevFirmwareReader().contains("-11")) {
			for(int i=1;i<=itr;i++) {
	
			drag_NotificationBar();
			clickOn_QuickSettings();
			clickOn_NetworkAndInternet();
			clickOn_DataUsage();
			clickOn_BillingCycle();
			clickOn_BillingCycle();
			String date=change_DataUsageCycle(data.get("Date"));
			validate_DataUsageCycle(date, sa);
		}}
		else if(JsonFileReaderAndWriter.primaryDevFirmwareReader().contains("-15")) {
			for(int i=1;i<=itr;i++) {
	
			drag_NotificationBar();
			clickOn_QuickSettings();
			clickOn_NetworkAndInternet();
			clickOn_DataUsage();
			clickOn_BillingCycle();
			clickOn_BillingCycle();
			String date=change_DataUsageCycle(data.get("Date"));
			validate_DataUsageCycle(date, sa);
		}}
		else if(JsonFileReaderAndWriter.primaryDevFirmwareReader().contains("-26")) {
			for(int i=1;i<=itr;i++) {
	
			drag_NotificationBar();
			clickOn_QuickSettings();
			clickOn_NetworkAndInternet();
			clickOn_DataUsage();
			clickOn_BillingCycle();
			clickOn_BillingCycle();
			String date=change_DataUsageCycle(data.get("Date"));
			validate_DataUsageCycle(date, sa);
		}}
		else if(JsonFileReaderAndWriter.primaryDevFirmwareReader().contains("-29")) {
			for(int i=1;i<=itr;i++) {
	
			drag_NotificationBar();
			clickOn_QuickSettings();
			clickOn_NetworkAndInternet();
			clickOn_DataUsage();
			clickOn_BillingCycle();
			clickOn_BillingCycle();
			String date=change_DataUsageCycle(data.get("Date"));
			validate_DataUsageCycle(date, sa);
		}}
		else  {
			for(int i=1;i<=itr;i++) {
	
			drag_NotificationBar();
			clickOn_QuickSettings();
			clickOn_NetworkAndInternet();
			clickOn_DataUsage();
			clickOn_BillingCycle();
			clickOn_BillingCycle();
			String date=change_DataUsageCycle(data.get("Date"));
			validate_DataUsageCycle(date, sa);
		}}
	
		sa.assertAll();
	}
	@Test(priority=5,dataProvider="XP8_Data_Usage_Setting", dataProviderClass=DataProviders.class)
	public void XP8_TC_05_Data_Usage_Setting_Set_Data_Limit(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException
	{  
		SoftAssert sa=new SoftAssert();
		APP_LOGS.info("===========XP8_TC_05_Data_Usage_Setting============");
		//Enable set data limit,Set data limit in gb,Set data limit in mb,Disable set data limit
		clearRecentApps();
		if(JsonFileReaderAndWriter.primaryDevFirmwareReader().contains("-10")) {
			for(int i=1;i<=itr;i++) {
	
			launch_an_app("settings");
			clickOn_NetworkAndInternet();
			clickOn_DataUsage();
			clickOn_DataUsage_SubModules("billing cycle");
			boolean enabled=enable_SetDataLimit();
			verify_SetDataLimit_IsEnabled(enabled,sa);
			set_DataLimit(data.get("Data"),"gb");
			clickOn_BackBtn();
			validate_SetDataLimit(data.get("Data"), sa,"GB");
			clickOn_DataUsage_SubModules("billing cycle");
			set_DataLimit(data.get("Data"),"mb");
			clickOn_BackBtn();
			validate_SetDataLimit(data.get("Data"), sa,"MB");
			clickOn_DataUsage_SubModules("billing cycle");
			boolean disabled=disable_SetDataLimit();
			verify_SetDataLimit_IsDisabled(disabled,sa);
			verify_DataLimit_IsGreyedOut(sa);
		}}
		else if(JsonFileReaderAndWriter.primaryDevFirmwareReader().contains("-11")) {
			for(int i=1;i<=itr;i++) {
	
			launch_an_app("settings");
			clickOn_NetworkAndInternet();
			clickOn_DataUsage();
			clickOn_DataUsage_SubModules("billing cycle");
			boolean enabled=enable_SetDataLimit();
			verify_SetDataLimit_IsEnabled(enabled,sa);
			set_DataLimit(data.get("Data"),"gb");
			clickOn_BackBtn();
			validate_SetDataLimit(data.get("Data"), sa,"GB");
			clickOn_DataUsage_SubModules("billing cycle");
			set_DataLimit(data.get("Data"),"mb");
			clickOn_BackBtn();
			validate_SetDataLimit(data.get("Data"), sa,"MB");
			clickOn_DataUsage_SubModules("billing cycle");
			boolean disabled=disable_SetDataLimit();
			verify_SetDataLimit_IsDisabled(disabled,sa);
			verify_DataLimit_IsGreyedOut(sa);
		}}
		else if(JsonFileReaderAndWriter.primaryDevFirmwareReader().contains("-15")) {
			for(int i=1;i<=itr;i++) {
	
			launch_an_app("settings");
			clickOn_NetworkAndInternet();
			clickOn_DataUsage();
			clickOn_DataUsage_SubModules("billing cycle");
			boolean enabled=enable_SetDataLimit();
			verify_SetDataLimit_IsEnabled(enabled,sa);
			set_DataLimit(data.get("Data"),"gb");
			clickOn_BackBtn();
			validate_SetDataLimit(data.get("Data"), sa,"GB");
			clickOn_DataUsage_SubModules("billing cycle");
			set_DataLimit(data.get("Data"),"mb");
			clickOn_BackBtn();
			validate_SetDataLimit(data.get("Data"), sa,"MB");
			clickOn_DataUsage_SubModules("billing cycle");
			boolean disabled=disable_SetDataLimit();
			verify_SetDataLimit_IsDisabled(disabled,sa);
			verify_DataLimit_IsGreyedOut(sa);
		}}
		else if(JsonFileReaderAndWriter.primaryDevFirmwareReader().contains("-26")) {
			for(int i=1;i<=itr;i++) {
	
			launch_an_app("settings");
			clickOn_NetworkAndInternet();
			clickOn_DataUsage();
			clickOn_DataUsage_SubModules("billing cycle");
			boolean enabled=enable_SetDataLimit();
			verify_SetDataLimit_IsEnabled(enabled,sa);
			set_DataLimit(data.get("Data"),"gb");
			clickOn_BackBtn();
			validate_SetDataLimit(data.get("Data"), sa,"GB");
			clickOn_DataUsage_SubModules("billing cycle");
			set_DataLimit(data.get("Data"),"mb");
			clickOn_BackBtn();
			validate_SetDataLimit(data.get("Data"), sa,"MB");
			clickOn_DataUsage_SubModules("billing cycle");
			boolean disabled=disable_SetDataLimit();
			verify_SetDataLimit_IsDisabled(disabled,sa);
			verify_DataLimit_IsGreyedOut(sa);
		}}
		else if(JsonFileReaderAndWriter.primaryDevFirmwareReader().contains("-29")) {
			for(int i=1;i<=itr;i++) {
	
			launch_an_app("settings");
			clickOn_NetworkAndInternet();
			clickOn_DataUsage();
			clickOn_DataUsage_SubModules("billing cycle");
			boolean enabled=enable_SetDataLimit();
			verify_SetDataLimit_IsEnabled(enabled,sa);
			set_DataLimit(data.get("Data"),"gb");
			clickOn_BackBtn();
			validate_SetDataLimit(data.get("Data"), sa,"GB");
			clickOn_DataUsage_SubModules("billing cycle");
			set_DataLimit(data.get("Data"),"mb");
			clickOn_BackBtn();
			validate_SetDataLimit(data.get("Data"), sa,"MB");
			clickOn_DataUsage_SubModules("billing cycle");
			boolean disabled=disable_SetDataLimit();
			verify_SetDataLimit_IsDisabled(disabled,sa);
			verify_DataLimit_IsGreyedOut(sa);
		}}
		else  {
			for(int i=1;i<=itr;i++) {
	
			launch_an_app("settings");
			clickOn_NetworkAndInternet();
			clickOn_DataUsage();
			clickOn_DataUsage_SubModules("billing cycle");
			boolean enabled=enable_SetDataLimit();
			verify_SetDataLimit_IsEnabled(enabled,sa);
			set_DataLimit(data.get("Data"),"gb");
			clickOn_BackBtn();
			validate_SetDataLimit(data.get("Data"), sa,"GB");
			clickOn_DataUsage_SubModules("billing cycle");
			set_DataLimit(data.get("Data"),"mb");
			clickOn_BackBtn();
			validate_SetDataLimit(data.get("Data"), sa,"MB");
			clickOn_DataUsage_SubModules("billing cycle");
			boolean disabled=disable_SetDataLimit();
			verify_SetDataLimit_IsDisabled(disabled,sa);
			verify_DataLimit_IsGreyedOut(sa);
		}}
	
		sa.assertAll();
	}	
	@Test(priority=6,dataProvider="XP8_Data_Usage_Setting", dataProviderClass=DataProviders.class)
	public void XP8_TC_06_Data_Usage_Setting_Enable_DS_DNM_APM_BT(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException
	{  
		SoftAssert sa=new SoftAssert();
		APP_LOGS.info("===========XP8_TC_06_Data_Usage_Setting============");
			//enable data saver and verify user can able to enabele airplane mode, donotdistrub mode and bluetooth sharing,
		dragNotification_Twice();
		clickOn_Edit();
		reset_Icons();
		//move_DataSaverIcon();
		clearRecentApps();
		if(JsonFileReaderAndWriter.primaryDevFirmwareReader().contains("-10")) {
			for(int i=1;i<=itr;i++) {
	
			launch_an_app("settings");
			clickOn_NetworkAndInternet();
			clickOn_DataUsage();
			clickOn_DataSaver();
			boolean ds=enable_DataSaver();
			verify_DataSaver_IsEnabled(ds, sa);
			aDriver.pressKeyCode(AndroidKeyCode.HOME);
			dragNotification_Twice();
			boolean dnm=enable_Donotdistrub();
			verify_Donotdistrub_IsEnabled(dnm,sa);
	
			clearRecentApps();
			launch_an_app("settings");
			clickOn_NetworkAndInternet();
			boolean apm=enable_AirplaneMode();
			verify_Airplane_IsEnabled(apm,sa);
			
			aDriver.pressKeyCode(AndroidKeyCode.HOME);
			drag_NotificationBar();
			clickOn_QuickSettings();
			clickon_ConnectedDevices();
			boolean bt=enable_BT();
			verify_Bluetooth_IsEnabled(bt,sa);
		}}
		else if(JsonFileReaderAndWriter.primaryDevFirmwareReader().contains("-11")) {
		for(int i=1;i<=itr;i++) {

			launch_an_app("settings");
			clickOn_NetworkAndInternet();
			clickOn_DataUsage();
			clickOn_DataSaver();
			boolean ds=enable_DataSaver();
			verify_DataSaver_IsEnabled(ds, sa);
			aDriver.pressKeyCode(AndroidKeyCode.HOME);
			dragNotification_Twice();
			boolean dnm=enable_Donotdistrub();
			verify_Donotdistrub_IsEnabled(dnm,sa);

			clearRecentApps();
			launch_an_app("settings");
			clickOn_NetworkAndInternet();
			boolean apm=enable_AirplaneMode();
			verify_Airplane_IsEnabled(apm,sa);
			
			aDriver.pressKeyCode(AndroidKeyCode.HOME);
			drag_NotificationBar();
			clickOn_QuickSettings();
			clickon_ConnectedDevices();
			boolean bt=enable_BT();
			verify_Bluetooth_IsEnabled(bt,sa);
		}}
		else if(JsonFileReaderAndWriter.primaryDevFirmwareReader().contains("-15")) {
			for(int i=1;i<=itr;i++) {

				launch_an_app("settings");
				clickOn_NetworkAndInternet();
				clickOn_DataUsage();
				clickOn_DataSaver();
				boolean ds=enable_DataSaver();
				verify_DataSaver_IsEnabled(ds, sa);
				aDriver.pressKeyCode(AndroidKeyCode.HOME);
				dragNotification_Twice();
				boolean dnm=enable_Donotdistrub();
				verify_Donotdistrub_IsEnabled(dnm,sa);

				clearRecentApps();
				launch_an_app("settings");
				clickOn_NetworkAndInternet();
				boolean apm=enable_AirplaneMode();
				verify_Airplane_IsEnabled(apm,sa);
				
				aDriver.pressKeyCode(AndroidKeyCode.HOME);
				drag_NotificationBar();
				clickOn_QuickSettings();
				clickon_ConnectedDevices();
				boolean bt=enable_BT();
				verify_Bluetooth_IsEnabled(bt,sa);
			}}
		else if(JsonFileReaderAndWriter.primaryDevFirmwareReader().contains("-26")) {
			for(int i=1;i<=itr;i++) {

				launch_an_app("settings");
				clickOn_NetworkAndInternet();
				clickOn_DataUsage();
				clickOn_DataSaver();
				boolean ds=enable_DataSaver();
				verify_DataSaver_IsEnabled(ds, sa);
				aDriver.pressKeyCode(AndroidKeyCode.HOME);
				dragNotification_Twice();
				boolean dnm=enable_Donotdistrub();
				verify_Donotdistrub_IsEnabled(dnm,sa);

				clearRecentApps();
				launch_an_app("settings");
				clickOn_NetworkAndInternet();
				boolean apm=enable_AirplaneMode();
				verify_Airplane_IsEnabled(apm,sa);
				
				aDriver.pressKeyCode(AndroidKeyCode.HOME);
				drag_NotificationBar();
				clickOn_QuickSettings();
				clickon_ConnectedDevices();
				boolean bt=enable_BT();
				verify_Bluetooth_IsEnabled(bt,sa);
			}}
		else if(JsonFileReaderAndWriter.primaryDevFirmwareReader().contains("-29")) {
			for(int i=1;i<=itr;i++) {

				launch_an_app("settings");
				clickOn_NetworkAndInternet();
				clickOn_DataUsage();
				clickOn_DataSaver();
				boolean ds=enable_DataSaver();
				verify_DataSaver_IsEnabled(ds, sa);
				aDriver.pressKeyCode(AndroidKeyCode.HOME);
				dragNotification_Twice();
				boolean dnm=enable_Donotdistrub();
				verify_Donotdistrub_IsEnabled(dnm,sa);

				clearRecentApps();
				launch_an_app("settings");
				clickOn_NetworkAndInternet();
				boolean apm=enable_AirplaneMode();
				verify_Airplane_IsEnabled(apm,sa);
				
				aDriver.pressKeyCode(AndroidKeyCode.HOME);
				drag_NotificationBar();
				clickOn_QuickSettings();
				clickon_ConnectedDevices();
				boolean bt=enable_BT();
				verify_Bluetooth_IsEnabled(bt,sa);
			}}
		else  {
			for(int i=1;i<=itr;i++) {

				launch_an_app("settings");
				clickOn_NetworkAndInternet();
				clickOn_DataUsage();
				clickOn_DataSaver();
				boolean ds=enable_DataSaver();
				verify_DataSaver_IsEnabled(ds, sa);
				aDriver.pressKeyCode(AndroidKeyCode.HOME);
				dragNotification_Twice();
				boolean dnm=enable_Donotdistrub();
				verify_Donotdistrub_IsEnabled(dnm,sa);

				clearRecentApps();
				launch_an_app("settings");
				clickOn_NetworkAndInternet();
				boolean apm=enable_AirplaneMode();
				verify_Airplane_IsEnabled(apm,sa);
				
				aDriver.pressKeyCode(AndroidKeyCode.HOME);
				drag_NotificationBar();
				clickOn_QuickSettings();
				clickon_ConnectedDevices();
				boolean bt=enable_BT();
				verify_Bluetooth_IsEnabled(bt,sa);
			}}

		//post cond disable dnm,apm,bt ,ds
		launch_an_app("settings");
		clickOn_NetworkAndInternet();
		disable_AirplaneMode();	
		clickOn_DataUsage();
		clickOn_DataSaver();
		disable_DataSaver();
		launch_an_app("settings");
		clickon_ConnectedDevices();
		disable_BT();
		dragNotification_Twice();
		disable_Donotdistrub();

		sa.assertAll();
	}
	@Test(priority=7,dataProvider="XP8_Data_Usage_Setting", dataProviderClass=DataProviders.class)
	public void XP8_TC_07_Data_Usage_Setting_Mobile_Data_Usage_Details(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException
	{  
		SoftAssert sa=new SoftAssert();
		APP_LOGS.info("===========XP8_TC_07_Data_Usage_Setting============");
		//precondition wifi should be enabled and wifi data should be used by some application
	
		//Verify app data usage details is displayed for particular app,Check Foreground ,Background,Total data used is displayed
		clearRecentApps();
		if(JsonFileReaderAndWriter.primaryDevFirmwareReader().contains("-10")) {
			for(int i=1;i<=itr;i++) {
	
			launch_an_app("settings");
			clickOn_NetworkAndInternet();
			clickOn_DataUsage();
			clickOn_DataUsage_SubModules("mobile data usage");
			validate_MobileDataUsage(sa);
			verify_DataUsageGraph(sa);
			clickOn_YouTube();
			verify_AppDataUsageDetails(sa);
			verify_TFB_IsDisplayed(sa);
			open_AppSettings();
			validate_MobileDataUsage_AppSetting(sa);
			clickOn_BackBtn();
			open_AppInfo();
			validate_AppInfo(sa);
		}}
		else if(JsonFileReaderAndWriter.primaryDevFirmwareReader().contains("-11")) {
			for(int i=1;i<=itr;i++) {
	
			launch_an_app("settings");
			clickOn_NetworkAndInternet();
			clickOn_DataUsage();
			clickOn_DataUsage_SubModules("mobile data usage");
			validate_MobileDataUsage(sa);
			verify_DataUsageGraph(sa);
			clickOn_YouTube();
			verify_AppDataUsageDetails(sa);
			verify_TFB_IsDisplayed(sa);
			open_AppSettings();
			validate_MobileDataUsage_AppSetting(sa);
			clickOn_BackBtn();
			open_AppInfo();
			validate_AppInfo(sa);
		}}
		else if(JsonFileReaderAndWriter.primaryDevFirmwareReader().contains("-15")) {
			for(int i=1;i<=itr;i++) {
	
			launch_an_app("settings");
			clickOn_NetworkAndInternet();
			clickOn_DataUsage();
			clickOn_DataUsage_SubModules("mobile data usage");
			validate_MobileDataUsage(sa);
			verify_DataUsageGraph(sa);
			clickOn_YouTube();
			verify_AppDataUsageDetails(sa);
			verify_TFB_IsDisplayed(sa);
			open_AppSettings();
			validate_MobileDataUsage_AppSetting(sa);
			clickOn_BackBtn();
			open_AppInfo();
			validate_AppInfo(sa);
		}}
		else if(JsonFileReaderAndWriter.primaryDevFirmwareReader().contains("-26")) {
			for(int i=1;i<=itr;i++) {
	
			launch_an_app("settings");
			clickOn_NetworkAndInternet();
			clickOn_DataUsage();
			clickOn_DataUsage_SubModules("mobile data usage");
			validate_MobileDataUsage(sa);
			verify_DataUsageGraph(sa);
			clickOn_YouTube();
			verify_AppDataUsageDetails(sa);
			verify_TFB_IsDisplayed(sa);
			open_AppSettings();
			validate_MobileDataUsage_AppSetting(sa);
			clickOn_BackBtn();
			open_AppInfo();
			validate_AppInfo(sa);
		}}
		else if(JsonFileReaderAndWriter.primaryDevFirmwareReader().contains("-29")) {
			for(int i=1;i<=itr;i++) {
	
			launch_an_app("settings");
			clickOn_NetworkAndInternet();
			clickOn_DataUsage();
			clickOn_DataUsage_SubModules("mobile data usage");
			validate_MobileDataUsage(sa);
			verify_DataUsageGraph(sa);
			clickOn_YouTube();
			verify_AppDataUsageDetails(sa);
			verify_TFB_IsDisplayed(sa);
			open_AppSettings();
			validate_MobileDataUsage_AppSetting(sa);
			clickOn_BackBtn();
			open_AppInfo();
			validate_AppInfo(sa);
		}}
		else  {
			for(int i=1;i<=itr;i++) {
	
			launch_an_app("settings");
			clickOn_NetworkAndInternet();
			clickOn_DataUsage();
			clickOn_DataUsage_SubModules("mobile data usage");
			validate_MobileDataUsage(sa);
			verify_DataUsageGraph(sa);
			clickOn_YouTube();
			verify_AppDataUsageDetails(sa);
			verify_TFB_IsDisplayed(sa);
			open_AppSettings();
			validate_MobileDataUsage_AppSetting(sa);
			clickOn_BackBtn();
			open_AppInfo();
			validate_AppInfo(sa);
		}}
	
		sa.assertAll();
	}
	
	@Test(priority=8,dataProvider="XP8_Data_Usage_Setting", dataProviderClass=DataProviders.class)
	public void XP8_TC_08_Data_Usage_Setting_Wifi_Data_Usage_Details(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException
	{   
		APP_LOGS.info("===========XP8_TC_08_Data_Usage_Setting============");
		SoftAssert sa=new SoftAssert();
		//wifi should be enabled and wifi data should be used by some application
	/*	preCond_enableWifi(SSID,PWD);
		preCond_browseData(videoName,duration);
*/		//Verify Wifi data usage is displayed,Wifi data used applist is displayed,Wifi data usage graph is displayed
		preCond_enableWifi(SSID,PWD);
		clearRecentApps();
		if(JsonFileReaderAndWriter.primaryDevFirmwareReader().contains("-10")) {
			for(int i=1;i<=itr;i++) {
	
			launch_an_app("settings");
			clickOn_NetworkAndInternet();
			clickOn_DataUsage();
			clickOn_DataUsage_SubModules("wifi data usage");
			validate_Wifi_Data_Usage(sa);
			verify_WifiDataUsed_AppList_IsDisplayed(sa);
			verify_WifiDataUsage_Graph(sa);
			validate_WifiDataUsage_Graph_AsPerCycle(sa);
			
			clickOn_YouTube();
			verify_TFB_IsDisplayed(sa);
			open_AppSettings();
			validate_AppSettings(sa);
			
			clickOn_BackBtn();
			open_AppInfo();
			validate_AppInfo(sa);
			}}
	
		else if(JsonFileReaderAndWriter.primaryDevFirmwareReader().contains("-11")) {
			for(int i=1;i<=itr;i++) {
	
			launch_an_app("settings");
			clickOn_NetworkAndInternet();
			clickOn_DataUsage();
			clickOn_DataUsage_SubModules("wifi data usage");
			validate_Wifi_Data_Usage(sa);
			verify_WifiDataUsed_AppList_IsDisplayed(sa);
			verify_WifiDataUsage_Graph(sa);
			validate_WifiDataUsage_Graph_AsPerCycle(sa);
			
			clickOn_YouTube();
			verify_TFB_IsDisplayed(sa);
			open_AppSettings();
			validate_AppSettings(sa);
			
			clickOn_BackBtn();
			open_AppInfo();
			validate_AppInfo(sa);
			}}
		else if(JsonFileReaderAndWriter.primaryDevFirmwareReader().contains("-15")) {

			for(int i=1;i<=itr;i++) {
				
			launch_an_app("settings");
			clickOn_NetworkAndInternet();
			clickOn_DataUsage();
			clickOn_DataUsage_SubModules("wifi data usage");
			validate_Wifi_Data_Usage(sa);
			verify_WifiDataUsed_AppList_IsDisplayed(sa);
			verify_WifiDataUsage_Graph(sa);
			validate_WifiDataUsage_Graph_AsPerCycle(sa);
			
			clickOn_YouTube();
			verify_TFB_IsDisplayed(sa);
			open_AppSettings();
			validate_AppSettings(sa);
			
			clickOn_BackBtn();
			open_AppInfo();
			validate_AppInfo(sa);
		}}
			else if(JsonFileReaderAndWriter.primaryDevFirmwareReader().contains("-26")) {

			for(int i=1;i<=itr;i++) {
				
			launch_an_app("settings");
			clickOn_NetworkAndInternet();
			clickOn_DataUsage();
			clickOn_DataUsage_SubModules("wifi data usage");
			validate_Wifi_Data_Usage(sa);
			verify_WifiDataUsed_AppList_IsDisplayed(sa);
			verify_WifiDataUsage_Graph(sa);
			validate_WifiDataUsage_Graph_AsPerCycle(sa);
			
			clickOn_YouTube();
			verify_TFB_IsDisplayed(sa);
			open_AppSettings();
			validate_AppSettings(sa);
			
			clickOn_BackBtn();
			open_AppInfo();
			validate_AppInfo(sa);
		}}
		else if(JsonFileReaderAndWriter.primaryDevFirmwareReader().contains("-29")) {

			for(int i=1;i<=itr;i++) {
				
			launch_an_app("settings");
			clickOn_NetworkAndInternet();
			clickOn_DataUsage();
			clickOn_DataUsage_SubModules("wifi data usage");
			validate_Wifi_Data_Usage(sa);
			verify_WifiDataUsed_AppList_IsDisplayed(sa);
			verify_WifiDataUsage_Graph(sa);
			validate_WifiDataUsage_Graph_AsPerCycle(sa);
			
			clickOn_YouTube();
			verify_TFB_IsDisplayed(sa);
			open_AppSettings();
			validate_AppSettings(sa);
			
			clickOn_BackBtn();
			open_AppInfo();
			validate_AppInfo(sa);
		}}
		else  {

			for(int i=1;i<=itr;i++) {
				
			launch_an_app("settings");
			clickOn_NetworkAndInternet();
			clickOn_DataUsage();
			clickOn_DataUsage_SubModules("wifi data usage");
			validate_Wifi_Data_Usage(sa);
			verify_WifiDataUsed_AppList_IsDisplayed(sa);
			verify_WifiDataUsage_Graph(sa);
			validate_WifiDataUsage_Graph_AsPerCycle(sa);
			
			clickOn_YouTube();
			verify_TFB_IsDisplayed(sa);
			open_AppSettings();
			validate_AppSettings(sa);
			
			clickOn_BackBtn();
			open_AppInfo();
			validate_AppInfo(sa);
		}}

		disable_Wifi_ADBcommand();

		sa.assertAll();
	}
	@Test(priority=9,dataProvider="XP8_Data_Usage_Setting", dataProviderClass=DataProviders.class)
	public void XP8_TC_09_Data_Usage_Setting_Network_Restrictions_Metered_Wifi_Networks(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException
	{  
		SoftAssert sa=new SoftAssert();
		APP_LOGS.info("===========XP9_TC_09_Data_Usage_Setting============");
		//Pre condition enable wifi network
	//	enable_Wifi_ADBcommand();
		preCond_enableWifi(SSID,PWD);

		//verify metered network info is displayed,List of Metered network is displayed,Automatic and metered and not metered options are displayed
		
		clearRecentApps();
		if(JsonFileReaderAndWriter.primaryDevFirmwareReader().contains("-10")) {
	
			for(int i=1;i<=itr;i++) {
	
			launch_an_app("settings");
			clickOn_NetworkAndInternet();
			clickOn_DataUsage();
			clickOn_DataUsage_SubModules("network restrictions");
			verify_MeteredNetworksInfo_IsDisplayed(data.get("Metered networks info"), sa);
			verify_ListOfMeteredNetworks_IsDisplayed(sa);
			validate_NetworksRestrictions_Enable_Disable_Metered_Wifi_Networks("automatic", sa);
			validate_NetworksRestrictions_Enable_Disable_Metered_Wifi_Networks("metered", sa);
			validate_NetworksRestrictions_Enable_Disable_Metered_Wifi_Networks("non metered", sa);
		}}
		else if(JsonFileReaderAndWriter.primaryDevFirmwareReader().contains("-11")) {
	
			for(int i=1;i<=itr;i++) {
	
			launch_an_app("settings");
			clickOn_NetworkAndInternet();
			clickOn_DataUsage();
			clickOn_DataUsage_SubModules("network restrictions");
			verify_MeteredNetworksInfo_IsDisplayed(data.get("Metered networks info"), sa);
			verify_ListOfMeteredNetworks_IsDisplayed(sa);
			validate_NetworksRestrictions_Enable_Disable_Metered_Wifi_Networks("automatic", sa);
			validate_NetworksRestrictions_Enable_Disable_Metered_Wifi_Networks("metered", sa);
			validate_NetworksRestrictions_Enable_Disable_Metered_Wifi_Networks("non metered", sa);
		}}
		else if(JsonFileReaderAndWriter.primaryDevFirmwareReader().contains("-15")) {
			
			for(int i=1;i<=itr;i++) {
	
			launch_an_app("settings");
			clickOn_NetworkAndInternet();
			clickOn_DataUsage();
			clickOn_DataUsage_SubModules("network restrictions");
			verify_MeteredNetworksInfo_IsDisplayed(data.get("Metered networks info"), sa);
			verify_ListOfMeteredNetworks_IsDisplayed(sa);
			validate_NetworksRestrictions_Enable_Disable_Metered_Wifi_Networks("automatic", sa);
			validate_NetworksRestrictions_Enable_Disable_Metered_Wifi_Networks("metered", sa);
			validate_NetworksRestrictions_Enable_Disable_Metered_Wifi_Networks("non metered", sa);
		}}
		else if(JsonFileReaderAndWriter.primaryDevFirmwareReader().contains("-26")) {
			
			for(int i=1;i<=itr;i++) {
	
			launch_an_app("settings");
			clickOn_NetworkAndInternet();
			clickOn_DataUsage();
			clickOn_DataUsage_SubModules("network restrictions");
			verify_MeteredNetworksInfo_IsDisplayed(data.get("Metered networks info"), sa);
			verify_ListOfMeteredNetworks_IsDisplayed(sa);
			validate_NetworksRestrictions_Enable_Disable_Metered_Wifi_Networks("automatic", sa);
			validate_NetworksRestrictions_Enable_Disable_Metered_Wifi_Networks("metered", sa);
			validate_NetworksRestrictions_Enable_Disable_Metered_Wifi_Networks("non metered", sa);
		}}
		else if(JsonFileReaderAndWriter.primaryDevFirmwareReader().contains("-29")) {
			
			for(int i=1;i<=itr;i++) {
	
			launch_an_app("settings");
			clickOn_NetworkAndInternet();
			clickOn_DataUsage();
			clickOn_DataUsage_SubModules("network restrictions");
			verify_MeteredNetworksInfo_IsDisplayed(data.get("Metered networks info"), sa);
			verify_ListOfMeteredNetworks_IsDisplayed(sa);
			validate_NetworksRestrictions_Enable_Disable_Metered_Wifi_Networks("automatic", sa);
			validate_NetworksRestrictions_Enable_Disable_Metered_Wifi_Networks("metered", sa);
			validate_NetworksRestrictions_Enable_Disable_Metered_Wifi_Networks("non metered", sa);
		}}
		else {
			
			for(int i=1;i<=itr;i++) {
	
			launch_an_app("settings");
			clickOn_NetworkAndInternet();
			clickOn_DataUsage();
			clickOn_DataUsage_SubModules("network restrictions");
			verify_MeteredNetworksInfo_IsDisplayed(data.get("Metered networks info"), sa);
			verify_ListOfMeteredNetworks_IsDisplayed(sa);
			validate_NetworksRestrictions_Enable_Disable_Metered_Wifi_Networks("automatic", sa);
			validate_NetworksRestrictions_Enable_Disable_Metered_Wifi_Networks("metered", sa);
			validate_NetworksRestrictions_Enable_Disable_Metered_Wifi_Networks("non metered", sa);
		}}
	
		disable_Wifi_ADBcommand();
		sa.assertAll();
	}
	@Test(priority=10,dataProvider="XP8_Data_Usage_Setting", dataProviderClass=DataProviders.class)
	public void XP8_TC_10_Data_Usage_Setting_MobileNetworks(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException
	{  
		APP_LOGS.info("===========XP8_TC_10_Data_Usage_Setting============");
		SoftAssert sa=new SoftAssert();
		//Verify mobile networks option is present in data usage window,Mobile network setting option is present while tapping on mobile network
		clearRecentApps();
		if(JsonFileReaderAndWriter.primaryDevFirmwareReader().contains("-10")) {
	
			for(int i=1;i<=itr;i++) {
	
			launch_an_app("settings");
			clickOn_NetworkAndInternet();
			clickOn_DataUsage();
			tapOn_MoreOptions();
			validate_MobileNetworks(sa);
			tapOn_MobileNetworks();
			validate_MobileNetworkSetting(sa);
		}}
		else if(JsonFileReaderAndWriter.primaryDevFirmwareReader().contains("-11")) {
	
			for(int i=1;i<=itr;i++) {
	
			launch_an_app("settings");
			clickOn_NetworkAndInternet();
			clickOn_DataUsage();
			tapOn_MoreOptions();
			validate_MobileNetworks(sa);
			tapOn_MobileNetworks();
			validate_MobileNetworkSetting(sa);
		}}
		else if(JsonFileReaderAndWriter.primaryDevFirmwareReader().contains("-15")) {
			
			for(int i=1;i<=itr;i++) {
	
			launch_an_app("settings");
			clickOn_NetworkAndInternet();
			clickOn_DataUsage();
			tapOn_MoreOptions();
			validate_MobileNetworks(sa);
			tapOn_MobileNetworks();
			validate_MobileNetworkSetting(sa);
		}}
		else if(JsonFileReaderAndWriter.primaryDevFirmwareReader().contains("-26")) {
			
			for(int i=1;i<=itr;i++) {
	
			launch_an_app("settings");
			clickOn_NetworkAndInternet();
			clickOn_DataUsage();
			tapOn_MoreOptions();
			validate_MobileNetworks(sa);
			tapOn_MobileNetworks();
			validate_MobileNetworkSetting(sa);
		}}
		else if(JsonFileReaderAndWriter.primaryDevFirmwareReader().contains("-29")) {
			
			for(int i=1;i<=itr;i++) {
	
			launch_an_app("settings");
			clickOn_NetworkAndInternet();
			clickOn_DataUsage();
			tapOn_MoreOptions();
			validate_MobileNetworks(sa);
			tapOn_MobileNetworks();
			validate_MobileNetworkSetting(sa);
		}}
		else {
			
			for(int i=1;i<=itr;i++) {
	
			launch_an_app("settings");
			clickOn_NetworkAndInternet();
			clickOn_DataUsage();
			tapOn_MoreOptions();
			validate_MobileNetworks(sa);
			tapOn_MobileNetworks();
			validate_MobileNetworkSetting(sa);
		}}
	
		sa.assertAll();
	}
	@Test(priority=11,dataProvider="XP8_Data_Usage_Setting", dataProviderClass=DataProviders.class)
	public void XP8_TC_11_Data_Usage_Setting_Billing_Cycle(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException
	{  
		APP_LOGS.info("===========XP8_TC_11_Data_Usage_Setting============");
		SoftAssert sa=new SoftAssert();
		//Verify billing cycle is displayed with 1.Billing cycle 2.Set data warning 3.Data warning 4.Set data limit 5.Data limit
		clearRecentApps();		
		if(JsonFileReaderAndWriter.primaryDevFirmwareReader().contains("-10")) {
			for(int i=1;i<=itr;i++) {
	
			launch_an_app("settings");
			clickOn_NetworkAndInternet();
			clickOn_DataUsage();
			verify_DataUsageMenu(sa);
			clickOn_DataUsage_SubModules("billing cycle");
			validate_BillingCycle(sa);
		}}
		else if(JsonFileReaderAndWriter.primaryDevFirmwareReader().contains("-11")) {
			for(int i=1;i<=itr;i++) {
	
			launch_an_app("settings");
			clickOn_NetworkAndInternet();
			clickOn_DataUsage();
			verify_DataUsageMenu(sa);
			clickOn_DataUsage_SubModules("billing cycle");
			validate_BillingCycle(sa);
		}}
		else if(JsonFileReaderAndWriter.primaryDevFirmwareReader().contains("-15")) {
			for(int i=1;i<=itr;i++) {
	
			launch_an_app("settings");
			clickOn_NetworkAndInternet();
			clickOn_DataUsage();
			verify_DataUsageMenu(sa);
			clickOn_DataUsage_SubModules("billing cycle");
			validate_BillingCycle(sa);
		}}
		else if(JsonFileReaderAndWriter.primaryDevFirmwareReader().contains("-26")) {
			for(int i=1;i<=itr;i++) {
	
			launch_an_app("settings");
			clickOn_NetworkAndInternet();
			clickOn_DataUsage();
			verify_DataUsageMenu(sa);
			clickOn_DataUsage_SubModules("billing cycle");
			validate_BillingCycle(sa);
		}}
		else if(JsonFileReaderAndWriter.primaryDevFirmwareReader().contains("-29")) {
			for(int i=1;i<=itr;i++) {
	
			launch_an_app("settings");
			clickOn_NetworkAndInternet();
			clickOn_DataUsage();
			verify_DataUsageMenu(sa);
			clickOn_DataUsage_SubModules("billing cycle");
			validate_BillingCycle(sa);
		}}
	
		sa.assertAll();
	}
	@Test(priority=12,dataProvider="XP8_Data_Usage_Setting", dataProviderClass=DataProviders.class)
	public void XP8_TC_12_Data_Usage_Setting_Back_To_DataUsage_Without_ModifyingWarning(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException
	{  
		APP_LOGS.info("===========XP8_TC_12_Data_Usage_Setting============");
		SoftAssert sa=new SoftAssert();
		clearRecentApps();
		if(JsonFileReaderAndWriter.primaryDevFirmwareReader().contains("-10")) {	//ATT
			for(int i=1;i<=itr;i++) {
	
			launch_an_app("settings");
			clickOn_NetworkAndInternet();
			clickOn_DataUsage();
			validate_DataUsageSetting(sa);
			clickOn_DataUsageBar();
			clickOn_BackBtn();
			clickOn_BackBtn();
			validate_DataUsage_Without_Modifying_SetDataUsageWarning(sa);
		}}
		else if(JsonFileReaderAndWriter.primaryDevFirmwareReader().contains("-11")) {	//BELL
			for(int i=1;i<=itr;i++) {
	
			launch_an_app("settings");
			clickOn_NetworkAndInternet();
			clickOn_DataUsage();
			validate_DataUsageSetting(sa);
			clickOn_DataUsageBar();
			clickOn_BackBtn();
			clickOn_BackBtn();
			validate_DataUsage_Without_Modifying_SetDataUsageWarning(sa);
		}}
		else if(JsonFileReaderAndWriter.primaryDevFirmwareReader().contains("-15")) {	//VERIZON
			for(int i=1;i<=itr;i++) {
	
			launch_an_app("settings");
			clickOn_NetworkAndInternet();
			clickOn_DataUsage();
			validate_DataUsageSetting(sa);
			clickOn_DataUsageBar();
			clickOn_BackBtn();
			clickOn_BackBtn();
			validate_DataUsage_Without_Modifying_SetDataUsageWarning(sa);
		}}
		else if(JsonFileReaderAndWriter.primaryDevFirmwareReader().contains("-26")) {	//SL
			for(int i=1;i<=itr;i++) {
	
			launch_an_app("settings");
			clickOn_NetworkAndInternet();
			clickOn_DataUsage();
			validate_DataUsageSetting(sa);
			clickOn_DataUsageBar();
			clickOn_BackBtn();
			clickOn_BackBtn();
			validate_DataUsage_Without_Modifying_SetDataUsageWarning(sa);
		}}
		else if(JsonFileReaderAndWriter.primaryDevFirmwareReader().contains("-29")) {	//SPRINT
			for(int i=1;i<=itr;i++) {
	
			launch_an_app("settings");
			clickOn_NetworkAndInternet();
			clickOn_DataUsage();
			validate_DataUsageSetting(sa);
			clickOn_DataUsageBar();
			clickOn_BackBtn();
			clickOn_BackBtn();
			validate_DataUsage_Without_Modifying_SetDataUsageWarning(sa);
		}}
		else  {																			//OTHERs
			for(int i=1;i<=itr;i++) {
	
			launch_an_app("settings");
			clickOn_NetworkAndInternet();
			clickOn_DataUsage();
			validate_DataUsageSetting(sa);
			clickOn_DataUsageBar();
			clickOn_BackBtn();
			clickOn_BackBtn();
			validate_DataUsage_Without_Modifying_SetDataUsageWarning(sa);
		}}
	
		sa.assertAll();
	}
 	
	@Test(priority=13,dataProvider="XP8_Data_Usage_Setting", dataProviderClass=DataProviders.class)
	public void XP8_TC_13_Data_Usage_Setting_Cancel_SetDataLimit(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException
	{  
		APP_LOGS.info("===========XP8_TC_13_Data_Usage_Setting============");
		SoftAssert sa=new SoftAssert();
		//Verify user can able to navigate back to billing cycle window without modifying data limit 
		clearRecentApps();
		if(JsonFileReaderAndWriter.primaryDevFirmwareReader().contains("-10")) {	//ATT
			for(int i=1;i<=itr;i++) {
			launch_an_app("settings");
			clickOn_NetworkAndInternet();
			clickOn_DataUsage();
			validate_DataUsageSetting(sa);
			clickOn_DataUsage_SubModules("billing cycle");
			enable_SetDataLimit();
			clickOn_SetDataLimit();
			clickOn_BackBtn();
			clickOn_BackBtn();
			validate_CancelSetDataLimit(sa);
			}}
		else if(JsonFileReaderAndWriter.primaryDevFirmwareReader().contains("-11")) {	//BELL
			for(int i=1;i<=itr;i++) {
			launch_an_app("settings");
			clickOn_NetworkAndInternet();
			clickOn_DataUsage();
			validate_DataUsageSetting(sa);
			clickOn_DataUsage_SubModules("billing cycle");
			enable_SetDataLimit();
			clickOn_SetDataLimit();
			clickOn_BackBtn();
			clickOn_BackBtn();
			validate_CancelSetDataLimit(sa);
			}}
		else if(JsonFileReaderAndWriter.primaryDevFirmwareReader().contains("15")) {	//VERIZON
			for(int i=1;i<=itr;i++) {
			launch_an_app("settings");
			clickOn_NetworkAndInternet();
			clickOn_DataUsage();
			validate_DataUsageSetting(sa);
			clickOn_DataUsage_SubModules("billing cycle");
			enable_SetDataLimit();
			clickOn_SetDataLimit();
			clickOn_BackBtn();
			clickOn_BackBtn();
			validate_CancelSetDataLimit(sa);
			}}
		else if(JsonFileReaderAndWriter.primaryDevFirmwareReader().contains("-26")) {	//SL
			for(int i=1;i<=itr;i++) {
			launch_an_app("settings");
			clickOn_NetworkAndInternet();
			clickOn_DataUsage();
			validate_DataUsageSetting(sa);
			clickOn_DataUsage_SubModules("billing cycle");
			enable_SetDataLimit();
			clickOn_SetDataLimit();
			clickOn_BackBtn();
			clickOn_BackBtn();
			validate_CancelSetDataLimit(sa);
			}}
		else if(JsonFileReaderAndWriter.primaryDevFirmwareReader().contains("-29")) {	//SPRINT
			for(int i=1;i<=itr;i++) {
			launch_an_app("settings");
			clickOn_NetworkAndInternet();
			clickOn_DataUsage();
			validate_DataUsageSetting(sa);
			clickOn_DataUsage_SubModules("billing cycle");
			enable_SetDataLimit();
			clickOn_SetDataLimit();
			clickOn_BackBtn();
			clickOn_BackBtn();
			validate_CancelSetDataLimit(sa);
			}}
		else {																			//OTHER
			for(int i=1;i<=itr;i++) {
			launch_an_app("settings");
			clickOn_NetworkAndInternet();
			clickOn_DataUsage();
			validate_DataUsageSetting(sa);
			clickOn_DataUsage_SubModules("billing cycle");
			enable_SetDataLimit();
			clickOn_SetDataLimit();
			clickOn_BackBtn();
			clickOn_BackBtn();
			validate_CancelSetDataLimit(sa);
			}}
		sa.assertAll();
	}
 
 
	@Test(priority=14,dataProvider="XP8_Data_Usage_Setting", dataProviderClass=DataProviders.class)
	public void XP8_TC_14_Data_Usage_Setting_BackgroundData_Enable_Disable(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException
	{  
		APP_LOGS.info("===========XP8_TC_14_Data_Usage_Setting============");
		SoftAssert sa=new SoftAssert();
		//pre cond use wifi data and browse data
	/*	preCond_enableWifi(SSID,PWD);
		preCond_browseData(videoName,duration);
	*/	//Verify user can able to 1.Enable 2.Disable background data option
		clearRecentApps();
		 if(JsonFileReaderAndWriter.primaryDevFirmwareReader().contains("-10")) {	//ATT

			for(int i=1;i<=itr;i++) {
			launch_an_app("settings");
			clickOn_NetworkAndInternet();
			clickOn_DataUsage();		
			clickOn_DataUsage_SubModules("wifi data usage");
			clickOn_YouTube();
			boolean enable=enable_BackgroundData();
			verify_BackgroundData_IsEnabled(enable,sa);
			boolean disable=disable_BackgroundData();
			verify_BackgroundData_IsDisabled(disable,sa);
		}}
		else if(JsonFileReaderAndWriter.primaryDevFirmwareReader().contains("-11")) {	//BELL

			for(int i=1;i<=itr;i++) {
			launch_an_app("settings");
			clickOn_NetworkAndInternet();
			clickOn_DataUsage();		
			clickOn_DataUsage_SubModules("wifi data usage");
			clickOn_YouTube();
			boolean enable=enable_BackgroundData();
			verify_BackgroundData_IsEnabled(enable,sa);
			boolean disable=disable_BackgroundData();
			verify_BackgroundData_IsDisabled(disable,sa);
		}}else if(JsonFileReaderAndWriter.primaryDevFirmwareReader().contains("-15")) {	//VERIZON

			for(int i=1;i<=itr;i++) {
			launch_an_app("settings");
			clickOn_NetworkAndInternet();
			clickOn_DataUsage();		
			clickOn_DataUsage_SubModules("wifi data usage");
			clickOn_YouTube();
			boolean enable=enable_BackgroundData();
			verify_BackgroundData_IsEnabled(enable,sa);
			boolean disable=disable_BackgroundData();
			verify_BackgroundData_IsDisabled(disable,sa);
		}}else if(JsonFileReaderAndWriter.primaryDevFirmwareReader().contains("-26")) {	//SL

			for(int i=1;i<=itr;i++) {
			launch_an_app("settings");
			clickOn_NetworkAndInternet();
			clickOn_DataUsage();		
			clickOn_DataUsage_SubModules("wifi data usage");
			clickOn_YouTube();
			boolean enable=enable_BackgroundData();
			verify_BackgroundData_IsEnabled(enable,sa);
			boolean disable=disable_BackgroundData();
			verify_BackgroundData_IsDisabled(disable,sa);
		}}else if(JsonFileReaderAndWriter.primaryDevFirmwareReader().contains("-29")) {	//SPRINT

			for(int i=1;i<=itr;i++) {
			launch_an_app("settings");
			clickOn_NetworkAndInternet();
			clickOn_DataUsage();		
			clickOn_DataUsage_SubModules("wifi data usage");
			clickOn_YouTube();
			boolean enable=enable_RestrictBackgroundData();
			verify_BackgroundData_IsEnabled(enable,sa);
			boolean disable=disable_RestrictBackgroundData();
			verify_BackgroundData_IsDisabled(disable,sa);
		}}else{																			//OTHERS

			for(int i=1;i<=itr;i++) {
			launch_an_app("settings");
			clickOn_NetworkAndInternet();
			clickOn_DataUsage();		
			clickOn_DataUsage_SubModules("wifi data usage");
			clickOn_YouTube();
			boolean enable=enable_BackgroundData();
			verify_BackgroundData_IsEnabled(enable,sa);
			boolean disable=disable_BackgroundData();
			verify_BackgroundData_IsDisabled(disable,sa);
		}}
			sa.assertAll();
	}
	@Test(priority=15,dataProvider="XP8_Data_Usage_Setting", dataProviderClass=DataProviders.class)
	public void XP8_TC_15_Data_Usage_Setting_UnrestrictedData_Enable_Disable(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException
	{  
		APP_LOGS.info("===========XP8_TC_15_Data_Usage_Setting============");
		SoftAssert sa=new SoftAssert();
		//pre cond use wifi data and browse youtube
/*		preCond_enableWifi(SSID,PWD);
		preCond_browseData(videoName,duration);
*/		//Verify user can able to 1.Enable 2.Disable unrestricted data usage for any application from data usage window
		clearRecentApps();
		 if(JsonFileReaderAndWriter.primaryDevFirmwareReader().contains("-10")) {	//ATT
			for(int i=1;i<=itr;i++) {
	
			launch_an_app("settings");
			clickOn_NetworkAndInternet();
			clickOn_DataUsage();
			clickOn_DataUsage_SubModules("wifi data usage");
			clickOn_YouTube();
			boolean enabledBackgroundData=enable_BackgroundData();
			verify_UnrestrictedDataUsage_IsPresent(enabledBackgroundData, sa);
			boolean enabled=enable_UnrestrictedDataUsage();
			verify_UnrestrictedDataUsage_IsEnabled(enabled,sa);
			boolean disabled= disable_UnrestrictedDataUsage();
			verify_UnrestrictedDataUsage_IsDisabled(disabled,sa);
		}}
		else if(JsonFileReaderAndWriter.primaryDevFirmwareReader().contains("-11")) {	//BELL
			for(int i=1;i<=itr;i++) {
	
			launch_an_app("settings");
			clickOn_NetworkAndInternet();
			clickOn_DataUsage();
			clickOn_DataUsage_SubModules("wifi data usage");
			clickOn_YouTube();
			boolean enabledBackgroundData=enable_BackgroundData();
			verify_UnrestrictedDataUsage_IsPresent(enabledBackgroundData, sa);
			boolean enabled=enable_UnrestrictedDataUsage();
			verify_UnrestrictedDataUsage_IsEnabled(enabled,sa);
			boolean disabled= disable_UnrestrictedDataUsage();
			verify_UnrestrictedDataUsage_IsDisabled(disabled,sa);
		}}
		else if(JsonFileReaderAndWriter.primaryDevFirmwareReader().contains("-15")) {	//VERIZON
			for(int i=1;i<=itr;i++) {
	
			launch_an_app("settings");
			clickOn_NetworkAndInternet();
			clickOn_DataUsage();
			clickOn_DataUsage_SubModules("wifi data usage");
			clickOn_YouTube();
			boolean enabledBackgroundData=enable_BackgroundData();
			verify_UnrestrictedDataUsage_IsPresent(enabledBackgroundData, sa);
			boolean enabled=enable_UnrestrictedDataUsage();
			verify_UnrestrictedDataUsage_IsEnabled(enabled,sa);
			boolean disabled= disable_UnrestrictedDataUsage();
			verify_UnrestrictedDataUsage_IsDisabled(disabled,sa);
		}}
		else if(JsonFileReaderAndWriter.primaryDevFirmwareReader().contains("-26")) {	//SL
			for(int i=1;i<=itr;i++) {
	
			launch_an_app("settings");
			clickOn_NetworkAndInternet();
			clickOn_DataUsage();
			clickOn_DataUsage_SubModules("wifi data usage");
			clickOn_YouTube();
			boolean enabledBackgroundData=enable_BackgroundData();
			verify_UnrestrictedDataUsage_IsPresent(enabledBackgroundData, sa);
			boolean enabled=enable_UnrestrictedDataUsage();
			verify_UnrestrictedDataUsage_IsEnabled(enabled,sa);
			boolean disabled= disable_UnrestrictedDataUsage();
			verify_UnrestrictedDataUsage_IsDisabled(disabled,sa);
		}}
		else if(JsonFileReaderAndWriter.primaryDevFirmwareReader().contains("-29")) {	//SPRINT
			for(int i=1;i<=itr;i++) {
	
			launch_an_app("settings");
			clickOn_NetworkAndInternet();
			clickOn_DataUsage();
			clickOn_DataUsage_SubModules("wifi data usage");
			clickOn_YouTube();
			boolean enabled=enable_RestrictBackgroundData();
			verify_UnrestrictedDataUsage_IsEnabled(enabled,sa);
			boolean disabled= disable_RestrictBackgroundData();
			verify_UnrestrictedDataUsage_IsDisabled(disabled,sa);
		}}
		else {																						//OTHERS
			for(int i=1;i<=itr;i++) {
	
			launch_an_app("settings");
			clickOn_NetworkAndInternet();
			clickOn_DataUsage();
			clickOn_DataUsage_SubModules("wifi data usage");
			clickOn_YouTube();
			boolean enabledBackgroundData=enable_BackgroundData();
			verify_UnrestrictedDataUsage_IsPresent(enabledBackgroundData, sa);
			boolean enabled=enable_UnrestrictedDataUsage();
			verify_UnrestrictedDataUsage_IsEnabled(enabled,sa);
			boolean disabled= disable_UnrestrictedDataUsage();
			verify_UnrestrictedDataUsage_IsDisabled(disabled,sa);
		}}
	
		sa.assertAll();
	}
	@Test(priority=16,dataProvider="XP8_Data_Usage_Setting", dataProviderClass=DataProviders.class)
	public void XP8_TC_16_Data_Usage_Setting_NetworkReset_Verify_DataSaverOption(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException
	{  
		APP_LOGS.info("===========XP8_TC_16_Data_Usage_Setting============");
		SoftAssert sa=new SoftAssert();
		//Verify 1.data saver option is present in data usage window,2.data saver is disabled by default,3.User can able to enable data saver
		
		clearRecentApps();
		if(JsonFileReaderAndWriter.primaryDevFirmwareReader().contains("-10")) {	//ATT
			for(int i=1;i<=itr;i++) {
	
			launch_an_app("settings");
			clickOn_NetworkAndInternet();
			clickOn_DataUsage();
			verify_DataSaver(sa);
			clickOn_DataSaver();
			verify_DataSaver_IsDisabled_ByDefault(sa);
			boolean enable=enable_DataSaver();
			verify_DataSaver_IsEnabled(enable,sa);
			//Verify user can able to enable unrestricted data from data saver window
			clickOn_UnrestrictedData();
			boolean unrestrictedDataEnabled=enable_UnrestrictedData_App1();
			verify_UnrestrictedData_IsEnabled(unrestrictedDataEnabled,sa);
			
			launch_an_app("settings");
			clickOn_NetworkAndInternet();
			clickOn_DataUsage();
			tapOn_MoreOptions();
			tapOn_MobileNetworks();
			boolean enableData=enable_MobileData();
			validate_MobileData_IsEnabled_When_DSEnabled(enableData, sa);
			boolean disableData=disable_MobileData();
			validate_MobileData_IsDisabled_When_DSEnabled(disableData,sa);
			//verify after reseting operation data saver option is disabled
			launch_an_app("settings");
			open_ResetOption();
			RESET_SETTINGS("Reset Wi-Fi, mobile & Bluetooth");
			launch_an_app("settings");
			clickOn_NetworkAndInternet();
			clickOn_DataUsage();
			clickOn_DataSaver();
			validate_Perform_Network_RESET_DataSaver_Off(sa);
			}}
		else if(JsonFileReaderAndWriter.primaryDevFirmwareReader().contains("-11")) {	//BELL
			for(int i=1;i<=itr;i++) {
	
			launch_an_app("settings");
			clickOn_NetworkAndInternet();
			clickOn_DataUsage();
			verify_DataSaver(sa);
			clickOn_DataSaver();
			verify_DataSaver_IsDisabled_ByDefault(sa);
			boolean enable=enable_DataSaver();
			verify_DataSaver_IsEnabled(enable,sa);
			//Verify user can able to enable unrestricted data from data saver window
			clickOn_UnrestrictedData();
			boolean unrestrictedDataEnabled=enable_UnrestrictedData_App1();
			verify_UnrestrictedData_IsEnabled(unrestrictedDataEnabled,sa);
			
			launch_an_app("settings");
			clickOn_NetworkAndInternet();
			clickOn_DataUsage();
			boolean enableData=enable_MobileData();
			validate_MobileData_IsEnabled_When_DSEnabled(enableData, sa);
			boolean disableData=disable_MobileData();
			validate_MobileData_IsDisabled_When_DSEnabled(disableData,sa);
			//verify after reseting operation data saver option is disabled
			launch_an_app("settings");
			open_ResetOption();
			RESET_SETTINGS("Reset Wi-Fi, mobile & Bluetooth");
			launch_an_app("settings");
			clickOn_NetworkAndInternet();
			clickOn_DataUsage();
			clickOn_DataSaver();
			validate_Perform_Network_RESET_DataSaver_Off(sa);
			}}
		else if(JsonFileReaderAndWriter.primaryDevFirmwareReader().contains("-15")) {	//VERIZON
			for(int i=1;i<=itr;i++) {
	
			launch_an_app("settings");
			clickOn_NetworkAndInternet();
			clickOn_DataUsage();
			verify_DataSaver(sa);
			clickOn_DataSaver();
			verify_DataSaver_IsDisabled_ByDefault(sa);
			boolean enable=enable_DataSaver();
			verify_DataSaver_IsEnabled(enable,sa);
			//Verify user can able to enable unrestricted data from data saver window
			clickOn_UnrestrictedData();
			boolean unrestrictedDataEnabled=enable_UnrestrictedData_App1();
			verify_UnrestrictedData_IsEnabled(unrestrictedDataEnabled,sa);
			
			launch_an_app("settings");
			clickOn_NetworkAndInternet();
			clickOn_DataUsage();
			boolean enableData=enable_MobileData();
			validate_MobileData_IsEnabled_When_DSEnabled(enableData, sa);
			boolean disableData=disable_MobileData();
			validate_MobileData_IsDisabled_When_DSEnabled(disableData,sa);
			//verify after reseting operation data saver option is disabled

			launch_an_app("settings");
			open_ResetOption();
			RESET_SETTINGS("Reset Wi-Fi, mobile & Bluetooth");
			launch_an_app("settings");
			clickOn_NetworkAndInternet();
			clickOn_DataUsage();
			clickOn_DataSaver();
			validate_Perform_Network_RESET_DataSaver_Off(sa);
			}}
		else if(JsonFileReaderAndWriter.primaryDevFirmwareReader().contains("-26")) {	//SL
			for(int i=1;i<=itr;i++) {
	
			launch_an_app("settings");
			clickOn_NetworkAndInternet();
			clickOn_DataUsage();
			verify_DataSaver(sa);
			clickOn_DataSaver();
			verify_DataSaver_IsDisabled_ByDefault(sa);
			boolean enable=enable_DataSaver();
			verify_DataSaver_IsEnabled(enable,sa);
			//Verify user can able to enable unrestricted data from data saver window
			clickOn_UnrestrictedData();
			boolean unrestrictedDataEnabled=enable_UnrestrictedData_App1();
			verify_UnrestrictedData_IsEnabled(unrestrictedDataEnabled,sa);
			
			launch_an_app("settings");
			clickOn_NetworkAndInternet();
			clickOn_DataUsage();
			boolean enableData=enable_MobileData();
			validate_MobileData_IsEnabled_When_DSEnabled(enableData, sa);
			boolean disableData=disable_MobileData();
			validate_MobileData_IsDisabled_When_DSEnabled(disableData,sa);
			//verify after reseting operation data saver option is disabled
			launch_an_app("settings");
			open_ResetOption();
			RESET_SETTINGS("Reset Wi-Fi, mobile & Bluetooth");
			launch_an_app("settings");
			clickOn_NetworkAndInternet();
			clickOn_DataUsage();
			clickOn_DataSaver();
			validate_Perform_Network_RESET_DataSaver_Off(sa);
			}}
		else if(JsonFileReaderAndWriter.primaryDevFirmwareReader().contains("-29")) {	//SPRINT
			for(int i=1;i<=itr;i++) {
	
			launch_an_app("settings");
			clickOn_NetworkAndInternet();
			clickOn_DataUsage();
			verify_DataSaver(sa);
			clickOn_DataSaver();
			verify_DataSaver_IsDisabled_ByDefault(sa);
			boolean enable=enable_DataSaver();
			verify_DataSaver_IsEnabled(enable,sa);
			//Verify user can able to enable unrestricted data from data saver window
			clickOn_UnrestrictedData();
			boolean unrestrictedDataEnabled=enable_UnrestrictedData_App1();
			verify_UnrestrictedData_IsEnabled(unrestrictedDataEnabled,sa);
			
			launch_an_app("settings");
			clickOn_NetworkAndInternet();
			clickOn_DataUsage();
			boolean enableData=enable_MobileData();
			validate_MobileData_IsEnabled_When_DSEnabled(enableData, sa);
			boolean disableData=disable_MobileData();
			validate_MobileData_IsDisabled_When_DSEnabled(disableData,sa);
			
			skip_NtwrkRst_Verify_DS_Disabled(sa);
			//verify after reseting operation data saver option is disabled
		/*	launch_an_app("settings");
			open_ResetOption();
			RESET_SETTINGS("Reset Wi-Fi, mobile & Bluetooth");
			launch_an_app("settings");
			clickOn_NetworkAndInternet();
			clickOn_DataUsage();
			clickOn_DataSaver();
			validate_Perform_Network_RESET_DataSaver_Off(sa);
		*/		}}
		else {																				//OTHERS
			for(int i=1;i<=itr;i++) {
			clickOn_BackBtn();
			launch_an_app("settings");
			clickOn_NetworkAndInternet();
			clickOn_DataUsage();
			verify_DataSaver(sa);
			clickOn_DataSaver();
			verify_DataSaver_IsDisabled_ByDefault(sa);
			boolean enable=enable_DataSaver();
			verify_DataSaver_IsEnabled(enable,sa);
			//Verify user can able to enable unrestricted data from data saver window
			clickOn_UnrestrictedData();
			boolean unrestrictedDataEnabled=enable_UnrestrictedData_App1();
			verify_UnrestrictedData_IsEnabled(unrestrictedDataEnabled,sa);
			
			launch_an_app("settings");
			clickOn_NetworkAndInternet();
			clickOn_DataUsage();
			tapOn_MoreOptions();
			tapOn_MobileNetworks();
			boolean enableData=enable_MobileData();
			validate_MobileData_IsEnabled_When_DSEnabled(enableData, sa);
			boolean disableData=disable_MobileData();
			validate_MobileData_IsDisabled_When_DSEnabled(disableData,sa);
			//verify after reseting operation data saver option is disabled
			launch_an_app("settings");
			open_ResetOption();
			RESET_SETTINGS("Reset Wi-Fi, mobile & Bluetooth");
			launch_an_app("settings");
			clickOn_NetworkAndInternet();
			clickOn_DataUsage();
			clickOn_DataSaver();
			validate_Perform_Network_RESET_DataSaver_Off(sa);
			}}
	
		
		sa.assertAll();
	}
	@Test(priority=17,dataProvider="XP8_Data_Usage_Setting", dataProviderClass=DataProviders.class)
	public void XP8_TC_17_Data_Usage_Setting_ED_YouTube(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException
	{  
		APP_LOGS.info("===========XP8_TC_17_Data_Usage_Setting============");
		SoftAssert sa=new SoftAssert();
		//precondition mobile data should be enabled and mobile data should be used for some application
	

		//Enable unrestricted data for any app and verify the same app is enabled in data saver window and disable there
		clearRecentApps();
		if(JsonFileReaderAndWriter.primaryDevFirmwareReader().contains("-10")) {	//ATT
			for(int i=1;i<=itr;i++) {
	
			launch_an_app("settings");
			clickOn_NetworkAndInternet();
			clickOn_DataUsage();
			clickOn_DataUsage_SubModules("mobile data usage");
			clickOn_YouTube();
			enable_BackgroundData();
			boolean enabled=enable_UnrestrictedDataUsage();
			verify_UDU_YouTube_IsEnabled(enabled,sa);
	
			launch_an_app("settings");
			clickOn_NetworkAndInternet();
			clickOn_DataUsage();
			clickOn_DataUsage_SubModules("data saver");
			clickOn_UnrestrictedData();
			verify_YouTube_IsEnabled_InDataSaver(sa);
			boolean disable=disable_UDU_YouTube();
			verify_UDU_YouTube_IsDisabled(disable,sa);
			}}
		else if(JsonFileReaderAndWriter.primaryDevFirmwareReader().contains("-11")) {	//BELL
			for(int i=1;i<=itr;i++) {
	
			launch_an_app("settings");
			clickOn_NetworkAndInternet();
			clickOn_DataUsage();
			clickOn_DataUsage_SubModules("mobile data usage");
			clickOn_YouTube();
			enable_BackgroundData();
			boolean enabled=enable_UnrestrictedDataUsage();
			verify_UDU_YouTube_IsEnabled(enabled,sa);
	
			launch_an_app("settings");
			clickOn_NetworkAndInternet();
			clickOn_DataUsage();
			clickOn_DataUsage_SubModules("data saver");
			clickOn_UnrestrictedData();
			verify_YouTube_IsEnabled_InDataSaver(sa);
			boolean disable=disable_UDU_YouTube();
			verify_UDU_YouTube_IsDisabled(disable,sa);
			}}
		else if(JsonFileReaderAndWriter.primaryDevFirmwareReader().contains("-15")) {	//VERIZON
			for(int i=1;i<=itr;i++) {
	
			launch_an_app("settings");
			clickOn_NetworkAndInternet();
			clickOn_DataUsage();
			clickOn_DataUsage_SubModules("mobile data usage");
			clickOn_YouTube();
			enable_BackgroundData();
			boolean enabled=enable_UnrestrictedDataUsage();
			verify_UDU_YouTube_IsEnabled(enabled,sa);
	
			launch_an_app("settings");
			clickOn_NetworkAndInternet();
			clickOn_DataUsage();
			clickOn_DataUsage_SubModules("data saver");
			clickOn_UnrestrictedData();
			verify_YouTube_IsEnabled_InDataSaver(sa);
			boolean disable=disable_UDU_YouTube();
			verify_UDU_YouTube_IsDisabled(disable,sa);
			}}
		else if(JsonFileReaderAndWriter.primaryDevFirmwareReader().contains("-26")) {	//SL
			for(int i=1;i<=itr;i++) {
	
			launch_an_app("settings");
			clickOn_NetworkAndInternet();
			clickOn_DataUsage();
			clickOn_DataUsage_SubModules("mobile data usage");
			clickOn_YouTube();
			enable_BackgroundData();
			boolean enabled=enable_UnrestrictedDataUsage();
			verify_UDU_YouTube_IsEnabled(enabled,sa);
	
			launch_an_app("settings");
			clickOn_NetworkAndInternet();
			clickOn_DataUsage();
			clickOn_DataUsage_SubModules("data saver");
			clickOn_UnrestrictedData();
			verify_YouTube_IsEnabled_InDataSaver(sa);
			boolean disable=disable_UDU_YouTube();
			verify_UDU_YouTube_IsDisabled(disable,sa);
			}}
		else if(JsonFileReaderAndWriter.primaryDevFirmwareReader().contains("-29")) {	//SPRINT
			for(int i=1;i<=itr;i++) {
	
			launch_an_app("settings");
			clickOn_NetworkAndInternet();
			clickOn_DataUsage();
			clickOn_DataUsage_SubModules("mobile data usage");
			clickOn_YouTube();
			boolean enabled=enable_RestrictBackgroundData();
			verify_UDU_YouTube_IsEnabled(enabled,sa);
	
			launch_an_app("settings");
			clickOn_NetworkAndInternet();
			clickOn_DataUsage();
			clickOn_DataUsage_SubModules("data saver");
			clickOn_UnrestrictedData();
			verify_YouTube_IsEnabled_InDataSaver(sa);
			boolean disable=disable_UDU_YouTube();
			verify_UDU_YouTube_IsDisabled(disable,sa);
			}}
		else {																			//OTHERS
			for(int i=1;i<=itr;i++) {
	
			launch_an_app("settings");
			clickOn_NetworkAndInternet();
			clickOn_DataUsage();
			clickOn_DataUsage_SubModules("mobile data usage");
			clickOn_YouTube();
			enable_BackgroundData();
			boolean enabled=enable_UnrestrictedDataUsage();
			verify_UDU_YouTube_IsEnabled(enabled,sa);
	
			launch_an_app("settings");
			clickOn_NetworkAndInternet();
			clickOn_DataUsage();
			clickOn_DataUsage_SubModules("data saver");
			clickOn_UnrestrictedData();
			verify_YouTube_IsEnabled_InDataSaver(sa);
			boolean disable=disable_UDU_YouTube();
			verify_UDU_YouTube_IsDisabled(disable,sa);
			}}
	
		sa.assertAll();
	}
	@Test(priority=18,dataProvider="XP8_Data_Usage_Setting", dataProviderClass=DataProviders.class)
	public void XP8_TC_18_Data_Usage_Setting_SystemApps(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException
	{  
		APP_LOGS.info("===========XP8_TC_18_Data_Usage_Setting============");
		SoftAssert sa=new SoftAssert();
		//Verify user can able to see Show system option in data usage window and verify all apps(Including system apps) displayed while tapping on show system option
		clearRecentApps();
		if(JsonFileReaderAndWriter.primaryDevFirmwareReader().contains("-10")) {	//ATT
			for(int i=1;i<=itr;i++) {
	
			launch_an_app("settings");
			clickOn_NetworkAndInternet();
			clickOn_DataUsage();
			clickOn_DataSaver();
			clickOn_UnrestrictedData();
			tapOn_MoreOptions();
			verify_ShowSystem(sa);
			tapOn_ShowSystem();
			verify_SystemApps(sa);
			//Verify hide system option is present under unrestricted data window
			tapOn_MoreOptions();
			verify_HideSystem(sa);
			//verify by default only downloaded and default apps are displayed under unrestricted data window
			launch_an_app("settings");
			clickOn_NetworkAndInternet();
			clickOn_DataUsage();
			clickOn_DataSaver();
			clickOn_UnrestrictedData();
			verify_DefaultDownloadedApps(sa);
		}}
		else if(JsonFileReaderAndWriter.primaryDevFirmwareReader().contains("-11")) {	//BELL
			for(int i=1;i<=itr;i++) {
	
			launch_an_app("settings");
			clickOn_NetworkAndInternet();
			clickOn_DataUsage();
			clickOn_DataSaver();
			clickOn_UnrestrictedData();
			tapOn_MoreOptions();
			verify_ShowSystem(sa);
			tapOn_ShowSystem();
			verify_SystemApps(sa);
			//Verify hide system option is present under unrestricted data window
			tapOn_MoreOptions();
			verify_HideSystem(sa);
			//verify by default only downloaded and default apps are displayed under unrestricted data window
			launch_an_app("settings");
			clickOn_NetworkAndInternet();
			clickOn_DataUsage();
			clickOn_DataSaver();
			clickOn_UnrestrictedData();
			verify_DefaultDownloadedApps(sa);
		}}
		else if(JsonFileReaderAndWriter.primaryDevFirmwareReader().contains("-15")) {	//VERIZON
			for(int i=1;i<=itr;i++) {
	
			launch_an_app("settings");
			clickOn_NetworkAndInternet();
			clickOn_DataUsage();
			clickOn_DataSaver();
			clickOn_UnrestrictedData();
			tapOn_MoreOptions();
			verify_ShowSystem(sa);
			tapOn_ShowSystem();
			verify_SystemApps(sa);
			//Verify hide system option is present under unrestricted data window
			tapOn_MoreOptions();
			verify_HideSystem(sa);
			//verify by default only downloaded and default apps are displayed under unrestricted data window
			launch_an_app("settings");
			clickOn_NetworkAndInternet();
			clickOn_DataUsage();
			clickOn_DataSaver();
			clickOn_UnrestrictedData();
			verify_DefaultDownloadedApps(sa);
			}}
			else if(JsonFileReaderAndWriter.primaryDevFirmwareReader().contains("-26")) {	//SL
				for(int i=1;i<=itr;i++) {
		
				launch_an_app("settings");
				clickOn_NetworkAndInternet();
				clickOn_DataUsage();
				clickOn_DataSaver();
				clickOn_UnrestrictedData();
				tapOn_MoreOptions();
				verify_ShowSystem(sa);
				tapOn_ShowSystem();
				verify_SystemApps(sa);
				//Verify hide system option is present under unrestricted data window
				tapOn_MoreOptions();
				verify_HideSystem(sa);
				//verify by default only downloaded and default apps are displayed under unrestricted data window
				launch_an_app("settings");
				clickOn_NetworkAndInternet();
				clickOn_DataUsage();
				clickOn_DataSaver();
				clickOn_UnrestrictedData();
				verify_DefaultDownloadedApps(sa);
			}}
			else if(JsonFileReaderAndWriter.primaryDevFirmwareReader().contains("-29")) {	//SPRINT
				for(int i=1;i<=itr;i++) {
		
				launch_an_app("settings");
				clickOn_NetworkAndInternet();
				clickOn_DataUsage();
				clickOn_DataSaver();
				clickOn_UnrestrictedData();
				tapOn_MoreOptions();
				verify_ShowSystem(sa);
				tapOn_ShowSystem();
				verify_SystemApps(sa);
				//Verify hide system option is present under unrestricted data window
				tapOn_MoreOptions();
				verify_HideSystem(sa);
				//verify by default only downloaded and default apps are displayed under unrestricted data window
				launch_an_app("settings");
				clickOn_NetworkAndInternet();
				clickOn_DataUsage();
				clickOn_DataSaver();
				clickOn_UnrestrictedData();
				verify_DefaultDownloadedApps(sa);
			}}
			else  {															//OTHERS
				for(int i=1;i<=itr;i++) {
		
				launch_an_app("settings");
				clickOn_NetworkAndInternet();
				clickOn_DataUsage();
				clickOn_DataSaver();
				clickOn_UnrestrictedData();
				tapOn_MoreOptions();
				verify_ShowSystem(sa);
				tapOn_ShowSystem();
				verify_SystemApps(sa);
				//Verify hide system option is present under unrestricted data window
				tapOn_MoreOptions();
				verify_HideSystem(sa);
				//verify by default only downloaded and default apps are displayed under unrestricted data window
				launch_an_app("settings");
				clickOn_NetworkAndInternet();
				clickOn_DataUsage();
				clickOn_DataSaver();
				clickOn_UnrestrictedData();
				verify_DefaultDownloadedApps(sa);
			}
			}
		sa.assertAll();
	}
	
	@Test(priority=19,dataProvider="XP8_Data_Usage_Setting", dataProviderClass=DataProviders.class)
	public void XP8_TC_19_Data_Usage_Setting_Back_To_BillingCycle_WithoutModifyingDateCycle(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException
	{  
	APP_LOGS.info("===========XP8_TC_19_Data_Usage_Setting============");
	SoftAssert sa=new SoftAssert();
	//Verify user can able to see Data reset field under billing cycle window and can able to change the recycling date that should be displayed in the billing cycle
	clearRecentApps();
	if(JsonFileReaderAndWriter.primaryDevFirmwareReader().contains("-10")) {	//ATT
	
		for(int i=1;i<=itr;i++) {
	
		launch_an_app("settings");
		clickOn_NetworkAndInternet();
		clickOn_DataUsage();
		verify_DataUsageMenu(sa);
		
		clickOn_DataUsage_SubModules("billing cycle");
		String beforeModification=fetch_DateCycleValue();
		clickOn_BillingCycle();
		verify_DateUsageCycleResetField(sa);
		
		String dateValue=removeSpl(data.get("Date"));
		changeDate(dateValue);
		clickOn_BackBtn();
		clickOn_BackBtn();
		String afterModification=fetch_DateCycleValue();
		validate_DateSetting_WithoutModifyingDateCycle(beforeModification,afterModification,sa);
		}}
	else if(JsonFileReaderAndWriter.primaryDevFirmwareReader().contains("-11")) {	//BELL
	
		for(int i=1;i<=itr;i++) {
	
		launch_an_app("settings");
		clickOn_NetworkAndInternet();
		clickOn_DataUsage();
		verify_DataUsageMenu(sa);
		
		clickOn_DataUsage_SubModules("billing cycle");
		String beforeModification=fetch_DateCycleValue();
		clickOn_BillingCycle();
		verify_DateUsageCycleResetField(sa);
		
		String dateValue=removeSpl(data.get("Date"));
		changeDate(dateValue);
		clickOn_BackBtn();
		clickOn_BackBtn();
		String afterModification=fetch_DateCycleValue();
		validate_DateSetting_WithoutModifyingDateCycle(beforeModification,afterModification,sa);
		}}
	else if(JsonFileReaderAndWriter.primaryDevFirmwareReader().contains("-15")) {	//VERIZon
		
		for(int i=1;i<=itr;i++) {
	
		launch_an_app("settings");
		clickOn_NetworkAndInternet();
		clickOn_DataUsage();
		verify_DataUsageMenu(sa);
		
		clickOn_DataUsage_SubModules("billing cycle");
		String beforeModification=fetch_DateCycleValue();
		clickOn_BillingCycle();
		verify_DateUsageCycleResetField(sa);
		
		String dateValue=removeSpl(data.get("Date"));
		changeDate(dateValue);
		clickOn_BackBtn();
		clickOn_BackBtn();
		String afterModification=fetch_DateCycleValue();
		validate_DateSetting_WithoutModifyingDateCycle(beforeModification,afterModification,sa);
		}}
	else if(JsonFileReaderAndWriter.primaryDevFirmwareReader().contains("-26")) {	//SL
		
		for(int i=1;i<=itr;i++) {
	
		launch_an_app("settings");
		clickOn_NetworkAndInternet();
		clickOn_DataUsage();
		verify_DataUsageMenu(sa);
		
		clickOn_DataUsage_SubModules("billing cycle");
		String beforeModification=fetch_DateCycleValue();
		clickOn_BillingCycle();
		verify_DateUsageCycleResetField(sa);
		
		String dateValue=removeSpl(data.get("Date"));
		changeDate(dateValue);
		clickOn_BackBtn();
		clickOn_BackBtn();
		String afterModification=fetch_DateCycleValue();
		validate_DateSetting_WithoutModifyingDateCycle(beforeModification,afterModification,sa);
		}}
	else if(JsonFileReaderAndWriter.primaryDevFirmwareReader().contains("-29")) {	//SPRINT
		
		for(int i=1;i<=itr;i++) {
	
		launch_an_app("settings");
		clickOn_NetworkAndInternet();
		clickOn_DataUsage();
		verify_DataUsageMenu(sa);
		
		clickOn_DataUsage_SubModules("billing cycle");
		String beforeModification=fetch_DateCycleValue();
		clickOn_BillingCycle();
		verify_DateUsageCycleResetField(sa);
		
		String dateValue=removeSpl(data.get("Date"));
		changeDate(dateValue);
		clickOn_BackBtn();
		clickOn_BackBtn();
		String afterModification=fetch_DateCycleValue();
		validate_DateSetting_WithoutModifyingDateCycle(beforeModification,afterModification,sa);
		}}
	else  {																			//OTHERS
		
		for(int i=1;i<=itr;i++) {
	
		launch_an_app("settings");
		clickOn_NetworkAndInternet();
		clickOn_DataUsage();
		verify_DataUsageMenu(sa);
		
		clickOn_DataUsage_SubModules("billing cycle");
		String beforeModification=fetch_DateCycleValue();
		clickOn_BillingCycle();
		verify_DateUsageCycleResetField(sa);
		
		String dateValue=removeSpl(data.get("Date"));
		changeDate(dateValue);
		clickOn_BackBtn();
		clickOn_BackBtn();
		String afterModification=fetch_DateCycleValue();
		validate_DateSetting_WithoutModifyingDateCycle(beforeModification,afterModification,sa);
		}}

	sa.assertAll();
	}
	@Test(priority=20,dataProvider="XP8_Data_Usage_Setting", dataProviderClass=DataProviders.class)
	public void XP8_TC_20_Data_Usage_Setting_SetDataWarning_From_DataUsageMenu(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException
	{  
		APP_LOGS.info("===========XP8_TC_20_Data_Usage_Setting============");
		SoftAssert sa=new SoftAssert();
		// Check warning message is displayed when background data is restricted and app uses 
		//metered wifi network for Larger downloads.

		clearRecentApps();
		if(JsonFileReaderAndWriter.primaryDevFirmwareReader().contains("-10")) {	//ATT
			for(int i=1;i<=itr;i++) {
	
			launch_an_app("settings");
			clickOn_NetworkAndInternet();
			clickOn_DataUsage();
			verify_DataUsageMenu(sa);
			clickOn_DataUsageMenu();
			verify_SetDataWarning_IsDisplayed(sa);
			String dataWarning=removeSpl(data.get("Data warning"));
			String resWarning=setDataWarning_From_DUM(dataWarning, "gb");
			validate_SetDataWarning(resWarning, sa,"GB");
		}}
		else if(JsonFileReaderAndWriter.primaryDevFirmwareReader().contains("-11")) {	//BELL
			for(int i=1;i<=itr;i++) {

				launch_an_app("settings");
				clickOn_NetworkAndInternet();
				clickOn_DataUsage();
				verify_DataUsageMenu(sa);
				clickOn_DataUsageMenu();
				verify_SetDataWarning_IsDisplayed(sa);
				String dataWarning=removeSpl(data.get("Data warning"));
				String resWarning=setDataWarning_From_DUM(dataWarning, "gb");
				validate_SetDataWarning(resWarning, sa,"GB");
				}}
		else if(JsonFileReaderAndWriter.primaryDevFirmwareReader().contains("-15")) {	//VERIZON
			for(int i=1;i<=itr;i++) {

				launch_an_app("settings");
				clickOn_NetworkAndInternet();
				clickOn_DataUsage();
				verify_DataUsageMenu(sa);
				clickOn_DataUsageMenu();
				verify_SetDataWarning_IsDisplayed(sa);
				String dataWarning=removeSpl(data.get("Data warning"));
				String resWarning=setDataWarning_From_DUM(dataWarning, "gb");
				validate_SetDataWarning(resWarning, sa,"GB");
				}}
		else if(JsonFileReaderAndWriter.primaryDevFirmwareReader().contains("-26")) {	//SL
			for(int i=1;i<=itr;i++) {

				launch_an_app("settings");
				clickOn_NetworkAndInternet();
				clickOn_DataUsage();
				verify_DataUsageMenu(sa);
				clickOn_DataUsageMenu();
				verify_SetDataWarning_IsDisplayed(sa);
				String dataWarning=removeSpl(data.get("Data warning"));
				String resWarning=setDataWarning_From_DUM(dataWarning, "gb");
				validate_SetDataWarning(resWarning, sa,"GB");
				}}
		else if(JsonFileReaderAndWriter.primaryDevFirmwareReader().contains("-29")) {	//SPRINT
			for(int i=1;i<=itr;i++) {

				launch_an_app("settings");
				clickOn_NetworkAndInternet();
				clickOn_DataUsage();
				verify_DataUsageMenu(sa);
				clickOn_DataUsageMenu();
				verify_SetDataWarning_IsDisplayed(sa);
				String dataWarning=removeSpl(data.get("Data warning"));
				String resWarning=setDataWarning_From_DUM(dataWarning, "gb");
				validate_SetDataWarning(resWarning, sa,"GB");
				}}
		else {																			//OTHERS
			for(int i=1;i<=itr;i++) {

				launch_an_app("settings");
				clickOn_NetworkAndInternet();
				clickOn_DataUsage();
				verify_DataUsageMenu(sa);
				clickOn_DataUsageMenu();
				verify_SetDataWarning_IsDisplayed(sa);
				String dataWarning=removeSpl(data.get("Data warning"));
				String resWarning=setDataWarning_From_DUM(dataWarning, "gb");
				validate_SetDataWarning(resWarning, sa,"GB");
				}}


		sa.assertAll();
	}
	@Test(priority=21,dataProvider="XP8_Data_Usage_Setting", dataProviderClass=DataProviders.class)
	public void XP8_TC_21_Data_Usage_Setting_Enable_DS_From_DUW_Verify_DS_Icon(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException
	{  
		APP_LOGS.info("===========XP8_TC_21_Data_Usage_Setting============");
		SoftAssert sa=new SoftAssert();
		//Enable data saver from data usage window and verify data saver icon is enabled from notification window also
		dragNotification_Twice();
		clickOn_Edit();
		reset_Icons();
		move_DataSaverIcon();
		clearRecentApps();
		if(JsonFileReaderAndWriter.primaryDevFirmwareReader().contains("-10")) {	//ATT
	
			for(int i=1;i<=itr;i++) {
	
			launch_an_app("settings");
			clickOn_NetworkAndInternet();
			clickOn_DataUsage();
			verify_DataSaver(sa);
			clickOn_DataSaver();
			boolean enable=enable_DataSaver();
			verify_DataSaver_IsEnabled(enable,sa);
	
			clearRecentApps();
			dragNotification_Twice();
			boolean availabilityOfDataSaver=search_DataSaverIcon();
			if(availabilityOfDataSaver)
			{
				verify_IsDataSaverEnabled_NotificationBar(sa);
			}else {
				swipe_Left();
				verify_IsDataSaverEnabled_NotificationBar(sa);
			}
		}}
		else if(JsonFileReaderAndWriter.primaryDevFirmwareReader().contains("-11")) {	//BELL

			for(int i=1;i<=itr;i++) {

			launch_an_app("settings");
			clickOn_NetworkAndInternet();
			clickOn_DataUsage();
			verify_DataSaver(sa);
			clickOn_DataSaver();
			boolean enable=enable_DataSaver();
			verify_DataSaver_IsEnabled(enable,sa);
		
			
			clearRecentApps();
			dragNotification_Twice();
			boolean availabilityOfDataSaver=search_DataSaverIcon();
			if(availabilityOfDataSaver)
			{
				verify_IsDataSaverEnabled_NotificationBar(sa);
			}else {
			swipe_Left();
			verify_IsDataSaverEnabled_NotificationBar(sa);
			}
			}}
		else if(JsonFileReaderAndWriter.primaryDevFirmwareReader().contains("-15")) {	//VERIZON

			for(int i=1;i<=itr;i++) {

			launch_an_app("settings");
			clickOn_NetworkAndInternet();
			clickOn_DataUsage();
			verify_DataSaver(sa);
			clickOn_DataSaver();
			boolean enable=enable_DataSaver();
			verify_DataSaver_IsEnabled(enable,sa);
		
			
			clearRecentApps();
			dragNotification_Twice();
			boolean availabilityOfDataSaver=search_DataSaverIcon();
			if(availabilityOfDataSaver)
			{
				verify_IsDataSaverEnabled_NotificationBar(sa);
			}
			else {
				swipe_Left();
				verify_IsDataSaverEnabled_NotificationBar(sa);
			}
			
			}}
		else if(JsonFileReaderAndWriter.primaryDevFirmwareReader().contains("-26")) {	//SL

			for(int i=1;i<=itr;i++) {

			launch_an_app("settings");
			clickOn_NetworkAndInternet();
			clickOn_DataUsage();
			verify_DataSaver(sa);
			clickOn_DataSaver();
			boolean enable=enable_DataSaver();
			verify_DataSaver_IsEnabled(enable,sa);
		
			
			clearRecentApps();
			dragNotification_Twice();
			boolean availabilityOfDataSaver=search_DataSaverIcon();
			if(availabilityOfDataSaver)
			{
				verify_IsDataSaverEnabled_NotificationBar(sa);
			}
			else {
				swipe_Left();
				verify_IsDataSaverEnabled_NotificationBar(sa);
			}
			}}
		else if(JsonFileReaderAndWriter.primaryDevFirmwareReader().contains("-29")) {	//SPRINT

			for(int i=1;i<=itr;i++) {

			launch_an_app("settings");
			clickOn_NetworkAndInternet();
			clickOn_DataUsage();
			verify_DataSaver(sa);
			clickOn_DataSaver();
			boolean enable=enable_DataSaver();
			verify_DataSaver_IsEnabled(enable,sa);
		
			
			clearRecentApps();
			dragNotification_Twice();
			boolean availabilityOfDataSaver=search_DataSaverIcon();
			if(availabilityOfDataSaver)
			{
				verify_IsDataSaverEnabled_NotificationBar(sa);
			}
			else {
				swipe_Left();
				verify_IsDataSaverEnabled_NotificationBar(sa);
			}
			}}
		else {															//OTHERS

			for(int i=1;i<=itr;i++) {

			launch_an_app("settings");
			clickOn_NetworkAndInternet();
			clickOn_DataUsage();
			verify_DataSaver(sa);
			clickOn_DataSaver();
			boolean enable=enable_DataSaver();
			verify_DataSaver_IsEnabled(enable,sa);
		
			
			clearRecentApps();
			dragNotification_Twice();
			boolean availabilityOfDataSaver=search_DataSaverIcon();
			if(availabilityOfDataSaver)
			{
				verify_IsDataSaverEnabled_NotificationBar(sa);
			}
			else {
				swipe_Left();
				verify_IsDataSaverEnabled_NotificationBar(sa);
			}
			}}
		sa.assertAll();
	}
	@Test(priority=22,dataProvider="XP8_Data_Usage_Setting", dataProviderClass=DataProviders.class)
	public void XP8_TC_22_Data_Usage_Setting_Validate_UpdationOF_DUW(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException
	{  
		APP_LOGS.info("===========XP8_TC_22_Data_Usage_Setting============");
		SoftAssert sa=new SoftAssert();
		//Verify data usagae window is updated properly
		String setDate=removeSpl(data.get("Date"));			//removing . separator
		String setWarning=removeSpl(data.get("Data warning"));
		String setLimit=removeSpl(data.get("Data limit"));
		
		clearRecentApps();
		if(JsonFileReaderAndWriter.primaryDevFirmwareReader().contains("-10")) {	//ATT
	
			for(int i=1;i<=itr;i++) {
	
			launch_an_app("settings");
			clickOn_NetworkAndInternet();
			clickOn_DataUsage();
			clickOn_DataUsage_SubModules("billing cycle");
			clickOn_BillingCycle();
			change_DataUsageCycle(setDate);
			enable_SetDataWarning();
			set_DataWarning(setWarning, "mb");
			enable_SetDataLimit();
			set_DataLimit(setLimit,"mb");
			minWait();
			
			clearRecentApps();
			drag_NotificationBar();
			clickOn_QuickSettings();
			clickOn_NetworkAndInternet();
			clickOn_DataUsage();
			verify_ProgressBar_IsDisplayed(sa);
			verify_DataWarningAndDataLimit_AreDisplayed(setWarning,setLimit,sa);
			verify_DateCycle_IsDisplayed(setDate,sa);
			verify_DataUsageValue_IsDisplayed(sa);
			}
		}else if(JsonFileReaderAndWriter.primaryDevFirmwareReader().contains("-11")) {	//BELL

			for(int i=1;i<=itr;i++) {

			launch_an_app("settings");
			clickOn_NetworkAndInternet();
			clickOn_DataUsage();
			clickOn_DataUsage_SubModules("billing cycle");
			clickOn_BillingCycle();
			change_DataUsageCycle(setDate);
			enable_SetDataWarning();
			set_DataWarning(setWarning, "mb");
			enable_SetDataLimit();
			set_DataLimit(setLimit,"mb");
			minWait();
			
			clearRecentApps();
			drag_NotificationBar();
			clickOn_QuickSettings();
			clickOn_NetworkAndInternet();
			clickOn_DataUsage();
			verify_ProgressBar_IsDisplayed(sa);
			verify_DataWarningAndDataLimit_AreDisplayed(setWarning,setLimit,sa);
			verify_DateCycle_IsDisplayed(setDate,sa);
			verify_DataUsageValue_IsDisplayed(sa);
			}
		}else if(JsonFileReaderAndWriter.primaryDevFirmwareReader().contains("-15")) {	//VERIZON

			for(int i=1;i<=itr;i++) {

			launch_an_app("settings");
			clickOn_NetworkAndInternet();
			clickOn_DataUsage();
			clickOn_DataUsage_SubModules("billing cycle");
			clickOn_BillingCycle();
			change_DataUsageCycle(setDate);
			enable_SetDataWarning();
			set_DataWarning(setWarning, "mb");
			enable_SetDataLimit();
			set_DataLimit(setLimit,"mb");
			minWait();
			
			clearRecentApps();
			drag_NotificationBar();
			clickOn_QuickSettings();
			clickOn_NetworkAndInternet();
			clickOn_DataUsage();
			verify_ProgressBar_IsDisplayed(sa);
			verify_DataWarningAndDataLimit_AreDisplayed(setWarning,setLimit,sa);
			verify_DateCycle_IsDisplayed(setDate,sa);
			verify_DataUsageValue_IsDisplayed(sa);
			}}else if(JsonFileReaderAndWriter.primaryDevFirmwareReader().contains("-26")) {	//SL

				for(int i=1;i<=itr;i++) {

				launch_an_app("settings");
				clickOn_NetworkAndInternet();
				clickOn_DataUsage();
				clickOn_DataUsage_SubModules("billing cycle");
				clickOn_BillingCycle();
				change_DataUsageCycle(setDate);
				enable_SetDataWarning();
				set_DataWarning(setWarning, "mb");
				enable_SetDataLimit();
				set_DataLimit(setLimit,"mb");
				minWait();
				
				clearRecentApps();
				drag_NotificationBar();
				clickOn_QuickSettings();
				clickOn_NetworkAndInternet();
				clickOn_DataUsage();
				verify_ProgressBar_IsDisplayed(sa);
				verify_DataWarningAndDataLimit_AreDisplayed(setWarning,setLimit,sa);
				verify_DateCycle_IsDisplayed(setDate,sa);
				verify_DataUsageValue_IsDisplayed(sa);
				}}else if(JsonFileReaderAndWriter.primaryDevFirmwareReader().contains("-29")) {	//SPRINT

					for(int i=1;i<=itr;i++) {

					launch_an_app("settings");
					clickOn_NetworkAndInternet();
					clickOn_DataUsage();
					clickOn_DataUsage_SubModules("billing cycle");
					clickOn_BillingCycle();
					change_DataUsageCycle(setDate);
					enable_SetDataWarning();
					set_DataWarning(setWarning, "mb");
					enable_SetDataLimit();
					set_DataLimit(setLimit,"mb");
					minWait();
					
					clearRecentApps();
					drag_NotificationBar();
					clickOn_QuickSettings();
					clickOn_NetworkAndInternet();
					clickOn_DataUsage();
					verify_ProgressBar_IsDisplayed(sa);
					verify_DataWarningAndDataLimit_AreDisplayed(setWarning,setLimit,sa);
					verify_DateCycle_IsDisplayed(setDate,sa);
					verify_DataUsageValue_IsDisplayed(sa);
					}}
		else {																		//OTHERS

		for(int i=1;i<=itr;i++) {

		launch_an_app("settings");
		clickOn_NetworkAndInternet();
		clickOn_DataUsage();
		clickOn_DataUsage_SubModules("billing cycle");
		clickOn_BillingCycle();
		change_DataUsageCycle(setDate);
		enable_SetDataWarning();
		set_DataWarning(setWarning, "mb");
		enable_SetDataLimit();
		set_DataLimit(setLimit,"mb");
		minWait();
		
		clearRecentApps();
		drag_NotificationBar();
		clickOn_QuickSettings();
		clickOn_NetworkAndInternet();
		clickOn_DataUsage();
		verify_ProgressBar_IsDisplayed(sa);
		verify_DataWarningAndDataLimit_AreDisplayed(setWarning,setLimit,sa);
		verify_DateCycle_IsDisplayed(setDate,sa);
		verify_DataUsageValue_IsDisplayed(sa);
		}}

		sa.assertAll();
	}
	@Test(priority=23,dataProvider="XP8_Data_Usage_Setting", dataProviderClass=DataProviders.class)
	public void XP8_TC_23_Data_Usage_Setting_Enable_Data_When_LimitIsReached(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException
	{  
		APP_LOGS.info("===========XP8_TC_23_Data_Usage_Setting============");
		SoftAssert sa=new SoftAssert();
		//pre condition mobile data should be enabled
		disable_Wifi_ADBcommand();

		if(JsonFileReaderAndWriter.primaryDevFirmwareReader().contains("-10")) {
		launch_an_app("settings");
		clickOn_NetworkAndInternet();
		disable_AirplaneMode();
		clickOn_MobileNetwork();
		disable_MobileData();
		//
		clearRecentApps();
		for(int i=1;i<=itr;i++) {

		launch_an_app("settings");
		clickOn_NetworkAndInternet();
		clickOn_DataUsage();
		String mobileDataUsed=Locators_DataUsageSetting.mobiledataUsed.getText().trim();
		String oldLimit=extract_Numerics(mobileDataUsed);
		String newLimit=add_MB(oldLimit, 2);
		System.out.println("old limit = "+oldLimit);
		System.out.println("new limit = "+newLimit);
		clickOn_DataUsage_SubModules("billing cycle");
		enable_SetDataLimit();
		set_DataLimit(newLimit,"mb");
		
		launch_an_app("settings");
		clickOn_NetworkAndInternet();
		clickOn_MobileNetwork();
		enable_MobileData();
	
		clearRecentApps();
		launchYoutube();
		clickOn_Search();
		search_Video("sonim xp8");
		play_Video();
		clickOn_RESUME();
		
		launch_an_app("settings");
		clickOn_NetworkAndInternet();
		clickOn_MobileNetwork();
		verify_MobileData_IsEnabled_AftLimitReached(sa);
		}}
		else	if(JsonFileReaderAndWriter.primaryDevFirmwareReader().contains("-11")) {
			launch_an_app("settings");
			clickOn_NetworkAndInternet();
			disable_AirplaneMode();
			clickOn_MobileNetwork();
			disable_MobileData();
			//
			clearRecentApps();
			for(int i=1;i<=itr;i++) {

			launch_an_app("settings");
			clickOn_NetworkAndInternet();
			clickOn_DataUsage();
			String mobileDataUsed=Locators_DataUsageSetting.mobiledataUsed.getText().trim();
			String oldLimit=extract_Numerics(mobileDataUsed);
			String newLimit=add_MB(oldLimit, 2);
			System.out.println("old limit = "+oldLimit);
			System.out.println("new limit = "+newLimit);
			clickOn_DataUsage_SubModules("billing cycle");
			enable_SetDataLimit();
			set_DataLimit(newLimit,"mb");
			
			launch_an_app("settings");
			clickOn_NetworkAndInternet();
			clickOn_MobileNetwork();
			enable_MobileData();
		
			clearRecentApps();
			launchYoutube();
			clickOn_Search();
			search_Video("sonim xp8");
			play_Video();
			clickOn_RESUME();
			
			launch_an_app("settings");
			clickOn_NetworkAndInternet();
			clickOn_MobileNetwork();
			verify_MobileData_IsEnabled_AftLimitReached(sa);
			}}
		else if(JsonFileReaderAndWriter.primaryDevFirmwareReader().contains("-15")) {
				launch_an_app("settings");
				clickOn_NetworkAndInternet();
				disable_AirplaneMode();
				clickOn_MobileNetwork();
				disable_MobileData();
				//
				clearRecentApps();
				for(int i=1;i<=itr;i++) {

				launch_an_app("settings");
				clickOn_NetworkAndInternet();
				clickOn_DataUsage();
				String mobileDataUsed=Locators_DataUsageSetting.mobiledataUsed.getText().trim();
				String oldLimit=extract_Numerics(mobileDataUsed);
				String newLimit=add_MB(oldLimit, 2);
				System.out.println("old limit = "+oldLimit);
				System.out.println("new limit = "+newLimit);
				clickOn_DataUsage_SubModules("billing cycle");
				enable_SetDataLimit();
				set_DataLimit(newLimit,"mb");
				
				launch_an_app("settings");
				clickOn_NetworkAndInternet();
				clickOn_MobileNetwork();
				enable_MobileData();
			
				clearRecentApps();
				launchYoutube();
				clickOn_Search();
				search_Video("sonim xp8");
				play_Video();
				clickOn_RESUME();
				
				launch_an_app("settings");
				clickOn_NetworkAndInternet();
				clickOn_MobileNetwork();
				verify_MobileData_IsEnabled_AftLimitReached(sa);
				}}
		else if(JsonFileReaderAndWriter.primaryDevFirmwareReader().contains("-26")) {	//SL
			launch_an_app("settings");
			clickOn_NetworkAndInternet();
			disable_AirplaneMode();
			clickOn_MobileNetwork();
			disable_MobileData();
			//
			clearRecentApps();
			for(int i=1;i<=itr;i++) {

			launch_an_app("settings");
			clickOn_NetworkAndInternet();
			clickOn_DataUsage();
			String mobileDataUsed=Locators_DataUsageSetting.mobiledataUsed.getText().trim();
			String oldLimit=extract_Numerics(mobileDataUsed);
			String newLimit=add_MB(oldLimit, 2);
			System.out.println("old limit = "+oldLimit);
			System.out.println("new limit = "+newLimit);
			clickOn_DataUsage_SubModules("billing cycle");
			enable_SetDataLimit();
			set_DataLimit(newLimit,"mb");
			
			launch_an_app("settings");
			clickOn_NetworkAndInternet();
			clickOn_MobileNetwork();
			enable_MobileData();
		
			clearRecentApps();
			launchYoutube();
			clickOn_Search();
			search_Video("sonim xp8");
			play_Video();
			clickOn_RESUME();
			
			launch_an_app("settings");
			clickOn_NetworkAndInternet();
			clickOn_MobileNetwork();
			verify_MobileData_IsEnabled_AftLimitReached(sa);
			}}
		else if(JsonFileReaderAndWriter.primaryDevFirmwareReader().contains("-29")) {	//SPRINT
			launch_an_app("settings");
			clickOn_NetworkAndInternet();
			disable_AirplaneMode();
			clickOn_MobileNetwork();
			disable_MobileData();
			//
			clearRecentApps();
			for(int i=1;i<=itr;i++) {

			launch_an_app("settings");
			clickOn_NetworkAndInternet();
			clickOn_DataUsage();
			String mobileDataUsed=Locators_DataUsageSetting.mobiledataUsed.getText().trim();
			String oldLimit=extract_Numerics(mobileDataUsed);
			String newLimit=add_MB(oldLimit, 2);
			System.out.println("old limit = "+oldLimit);
			System.out.println("new limit = "+newLimit);
			clickOn_DataUsage_SubModules("billing cycle");
			enable_SetDataLimit();
			set_DataLimit(newLimit,"mb");
			
			launch_an_app("settings");
			clickOn_NetworkAndInternet();
			clickOn_MobileNetwork();
			enable_MobileData();
		
			clearRecentApps();
			launchYoutube();
			clickOn_Search();
			search_Video("sonim xp8");
			play_Video();
			clickOn_RESUME();
			
			launch_an_app("settings");
			clickOn_NetworkAndInternet();
			clickOn_MobileNetwork();
			verify_MobileData_IsEnabled_AftLimitReached(sa);
			}}
		else  {																				//OTHERS
			launch_an_app("settings");
			clickOn_NetworkAndInternet();
			disable_AirplaneMode();
			clickOn_MobileNetwork();
			disable_MobileData();
			//
			clearRecentApps();
			for(int i=1;i<=itr;i++) {

			launch_an_app("settings");
			clickOn_NetworkAndInternet();
			clickOn_DataUsage();
			String mobileDataUsed=Locators_DataUsageSetting.mobiledataUsed.getText().trim();
			String oldLimit=extract_Numerics(mobileDataUsed);
			String newLimit=add_MB(oldLimit, 2);
			System.out.println("old limit = "+oldLimit);
			System.out.println("new limit = "+newLimit);
			clickOn_DataUsage_SubModules("billing cycle");
			enable_SetDataLimit();
			set_DataLimit(newLimit,"mb");
			
			launch_an_app("settings");
			clickOn_NetworkAndInternet();
			clickOn_MobileNetwork();
			enable_MobileData();
		
			clearRecentApps();
			launchYoutube();
			clickOn_Search();
			search_Video("sonim xp8");
			play_Video();
			clickOn_RESUME();
			
			launch_an_app("settings");
			clickOn_NetworkAndInternet();
			clickOn_MobileNetwork();
			verify_MobileData_IsEnabled_AftLimitReached(sa);
			}}



	/*	//post condition
		launch_an_app("settings");
		clickOn_NetworkAndInternet();
		clickOn_MobileNetwork();
		disable_MobileData();
	*/	
		
		sa.assertAll();
	}

}
