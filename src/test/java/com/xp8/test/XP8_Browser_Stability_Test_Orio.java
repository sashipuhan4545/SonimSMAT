package com.xp8.test;

import java.awt.AWTException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.Properties;

import org.json.simple.parser.ParseException;
import org.openqa.selenium.support.PageFactory;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import org.testng.collections.Lists;

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
import com.xp8.util.Locators_DeviceStability;
import com.xp8.util.Locators_Stability;
import com.xp8.util.Stability_Browser_Util;

import application.AllQA;
//import application.AllQA;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class XP8_Browser_Stability_Test_Orio extends Stability_Browser_Util{

	public ExcelReader excel;
	Properties properties;
	public static ExtentTestInterruptedException testexception;
	public int  itr =AllQA.NUMOFCALLS;
	boolean value = false;

	@BeforeSuite
	public void beforeSuite() throws FileNotFoundException, InterruptedException, IOException, ParseException  
	{

		fetch_Devices_Details();	

		extent = new ExtentReports("src/test/resources/extentreport/XP8_Stability_Orio_TestReport.html",true); //Provide Desired Report Directory Location and Name
		extent.loadConfig(new File("src/test/resources/StorageFile/ReportsConfig.xml"));
		extent.addSystemInfo("Build #",JsonFileReaderAndWriter.primaryDevFirmwareReader())
		.addSystemInfo("Product",JsonFileReaderAndWriter.primaryDevModelReader())
		.addSystemInfo("Operator", JsonFileReaderAndWriter.primaryDevOperatorReader());		
		
		
		
		try {
			appiumService.TOTAL_NUM_OF_TESTCASES=GetMethods.TotalTestcase("XP8_TC", this.getClass());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@BeforeMethod()
	public  void beforeMethod(Method method) 
	{
		test = extent.startTest( (this.getClass().getSimpleName() +" :: "+  method.getName()),method.getName()); //Test Case Start Here
		//test.assignAuthor("Farheen Taj"); //Test Script Author Name
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
	public void setupSys() throws MalformedURLException, InterruptedException, FileNotFoundException, AWTException{
		properties=loadDriverAndProperties();
		Locators_Stability loc=new Locators_Stability(aDriver);
		Locators_BaseUtil loc1 = new Locators_BaseUtil(aDriver);	
		PageFactory.initElements(new AppiumFieldDecorator(aDriver),loc);
		PageFactory.initElements(new AppiumFieldDecorator(aDriver),loc1);
		excel=new ExcelReader(ExcelConstants.XP5S_XL_PATH);	
	}
	
	@AfterTest
	public void closeTabs() throws InterruptedException{
		launch_an_app("browser");
		minWait();
		if(isElementExist(multi_Loc_Strategy(Locators_Stability.tabSwitcherButton, Locators_Stability.tabSwitcherButtonById, Locators_Stability.tabSwitcherButtonByContectDesc, Locators_Stability.tabSwitcherButtonByXpath, null, 936, 240))){
			clickBtn((multi_Loc_Strategy(Locators_Stability.tabSwitcherButton, Locators_Stability.tabSwitcherButtonById, Locators_Stability.tabSwitcherButtonByContectDesc, Locators_Stability.tabSwitcherButtonByXpath, null, 936, 240)));
			minWait();
			clickBtn((multi_Loc_Strategy(Locators_Stability.MoreOptnsIcn, Locators_Stability.MoreOptnsIcnById, Locators_Stability.MoreOptnsIcnByContectDesc, Locators_Stability.MoreOptnsIcnByXpath, null, 936,72)));
			minWait();
			clickBtn(multi_Loc_Strategy(Locators_Stability.closeAllBrowserTabs, Locators_Stability.closeAllBrowserTabsById, Locators_Stability.closeAllBrowserTabsByUiSelectorId, Locators_Stability.closeAllBrowserTabsByContectDesc, Locators_Stability.closeAllBrowserTabsByXpath, 337, 377));
		}
	}

	@Test(priority=1,dataProvider="XP8_Stability", dataProviderClass=DataProviders.class)
	public void XP8_TC_001_Stability_Load_Website_Google(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========XP8_Stability_01_Browser============");
		SoftAssert sa1 = new SoftAssert();
		clearRecentApps();
		launch_an_app("settings");
		clickOn_Networks_and_Internet();
		scrollToText("Mobile network");
		enable_Mobile_Data();
		minWait();
		clickBackButton(1);
		scrollToTextContains("Wi");
		setUp_And_Enable_WiFi();
		launch_an_app("browser");
		if(isElementExist(multi_Loc_Strategy(Locators_Stability.ACCEPTCONTINUE, Locators_Stability.ACCEPTCONTINUEByID, Locators_Stability.ACCEPTCONTINUEByResourceId, Locators_Stability.ACCEPTCONTINUEByText, Locators_Stability.ACCEPTCONTINUEByXpath, 332, 1764))){
			clearChromePermission();		
		}
		for(int i=1; i<=itr;i++) {	  

			for(int j=1; j<=1; j++) {
				launch_an_app("browser");
				navigateBrowserMenu(data.get("Web"+j));	
				validateUrlEntered(data.get("Web"+j),i,j,sa1);
			}
			minWait();		
		}
		sa1.assertAll();
	}

	@Test(priority=2,dataProvider="XP8_Stability", dataProviderClass=DataProviders.class)
	public void XP8_TC_002_Stability_Load_Website_Yahoo(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========XP8_Stability_02_Browser============");
		SoftAssert sa2 = new SoftAssert();
		launch_an_app("browser");
		for(int i=1; i<=itr;i++) {	  

			for(int j=2; j<=2; j++) {
				navigateBrowserMenu(data.get("Web"+j));	
				validateUrlEntered(data.get("Web"+j),i,j,sa2);
			}
			minWait();		
		}
		sa2.assertAll();
	}

	@Test(priority=3,dataProvider="XP8_Stability", dataProviderClass=DataProviders.class)
	public void XP8_TC_003_Stability_Load_Website_Bing(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========XP8_Stability_03_Browser============");
		SoftAssert sa3 = new SoftAssert();
		launch_an_app("browser");
		for(int i=1; i<=itr;i++) {	  

			for(int j=3; j<=3; j++) {
				navigateBrowserMenu(data.get("Web"+j));	
				System.out.println(data.get("Web3"));
				validateUrlEntered(data.get("Web"+j),i,j,sa3);
			}
			minWait();		
		}
		sa3.assertAll();
	}

	@Test(priority=4,dataProvider="XP8_Stability", dataProviderClass=DataProviders.class)
	public void XP8_TC_004_Stability_Load_Website_BankOfAmerica(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========XP8_Stability_03_Browser============");
		SoftAssert sa4 = new SoftAssert();
		launch_an_app("browser");
		for(int i=1; i<=itr;i++) {	  

			for(int j=4; j<=4; j++) {
				navigateBrowserMenu(data.get("Web"+j));	
				validateUrlEntered(data.get("Web"+j),i,j,sa4);
			}
			minWait();		
		}
		sa4.assertAll();
	}

	@Test(priority=5,dataProvider="XP8_Stability", dataProviderClass=DataProviders.class)
	public void XP8_TC_005_Stability_Load_Website_Chase(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("==========XP8_Stability_03_Browser============");
		SoftAssert sa5 = new SoftAssert();
		launch_an_app("browser");
		for(int i=1; i<=itr;i++) {	  
			for(int j=5; j<=5; j++) {
				navigateBrowserMenu(data.get("Web"+j));	
				validateUrlEntered(data.get("Web"+j),i,j,sa5);
			}
			minWait();		
		}
		sa5.assertAll();
	}

	@Test(priority=6,dataProvider="XP8_Stability", dataProviderClass=DataProviders.class)
	public void XP8_TC_006_Stability_Load_Website_AxisBank(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========XP8_Stability_06_Browser============");
		SoftAssert sa6 = new SoftAssert();
		launch_an_app("browser");
		for(int i=1; i<=itr;i++) {	  
			for(int j=6; j<=6; j++) {
				navigateBrowserMenu(data.get("Web"+j));	
				validateUrlEntered(data.get("Web"+j),i,j,sa6);
			}
			minWait();		
		}
		sa6.assertAll();
	}

	@Test(priority=7,dataProvider="XP8_Stability", dataProviderClass=DataProviders.class)
	public void XP8_TC_007_Stability_Load_Website_Wellsfargo(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========XP8_Stability_07_Browser============");
		SoftAssert sa7 = new SoftAssert();
		launch_an_app("browser");
		for(int i=1; i<=itr;i++) {	  
			for(int j=7; j<=7; j++) {
				navigateBrowserMenu(data.get("Web"+j));	
				validateUrlEntered(data.get("Web"+j),i,j,sa7);
			}
			minWait();		
		}
		sa7.assertAll();
	}


	@Test(priority=8,dataProvider="XP8_Stability", dataProviderClass=DataProviders.class)
	public void XP8_TC_008_Stability_Load_Website_Paypal(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========XP8_Stability_08_Browser============");
		SoftAssert sa8 = new SoftAssert();
		launch_an_app("browser");
		for(int i=1; i<=itr;i++) {	  
			for(int j=8; j<=8; j++) {
				navigateBrowserMenu(data.get("Web"+j));	
				validateUrlEntered(data.get("Web"+j),i,j,sa8);
			}
			minWait();		
		}
		sa8.assertAll();
	}


	@Test(priority=9,dataProvider="XP8_Stability", dataProviderClass=DataProviders.class)
	public void XP8_TC_009_Stability_Load_Website_Amazon(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========XP8_Stability_09_Browser============");

		SoftAssert sa9 = new SoftAssert();
		launch_an_app("browser");
		for(int i=1; i<=itr;i++) {	  
			for(int j=9; j<=9; j++) {
				navigateBrowserMenu(data.get("Web"+j));	
				validateUrlEntered(data.get("Web"+j),i,j,sa9);
			}
			minWait();		
		}
		sa9.assertAll();
	}

	@Test(priority=10,dataProvider="XP8_Stability", dataProviderClass=DataProviders.class)
	public void XP8_TC_010_Stability_Load_Website_Flipkart(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========XP8_Stability_10_Browser============");
		SoftAssert sa10 = new SoftAssert();
		launch_an_app("browser");
		for(int i=1; i<=itr;i++) {	  
			for(int j=10; j<=10; j++) {
				navigateBrowserMenu(data.get("Web"+j));	
				validateUrlEntered(data.get("Web"+j),i,j,sa10);
			}
			minWait();		
		}
		sa10.assertAll();
	}

	@Test(priority=11,dataProvider="XP8_Stability", dataProviderClass=DataProviders.class)
	public void XP8_TC_011_Stability_Load_Website_Walmart(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========XP8_Stability_10_Browser============");
		SoftAssert sa11 = new SoftAssert();
		launch_an_app("browser");
		for(int i=1; i<=itr;i++) {	  
			for(int j=11; j<=11; j++) {
				navigateBrowserMenu(data.get("Web"+j));	
				validateUrlEntered(data.get("Web"+j),i,j,sa11);
			}
			minWait();		
		}
		sa11.assertAll();

	}

	@Test(priority=12,dataProvider="XP8_Stability", dataProviderClass=DataProviders.class)
	public void XP8_TC_012_Stability_Load_Website_Craigslist(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========XP8_Stability_10_Browser============");
		SoftAssert sa12 = new SoftAssert();
		launch_an_app("browser");
		for(int i=1; i<=itr;i++) {	  
			for(int j=12; j<=12; j++) {
				navigateBrowserMenu(data.get("Web"+j));	
				validateUrlEntered(data.get("Web"+j),i,j,sa12);
			}
			minWait();		
		}
		sa12.assertAll();
	}

	@Test(priority=13,dataProvider="XP8_Stability", dataProviderClass=DataProviders.class)
	public void XP8_TC_013_Stability_Load_Website_Kohls(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========XP8_Stability_10_Browser============");
		SoftAssert sa13 = new SoftAssert();
		launch_an_app("browser");
		for(int i=1; i<=itr;i++) {	  
			for(int j=13; j<=13; j++) {
				navigateBrowserMenu(data.get("Web"+j));	
				validateUrlEntered(data.get("Web"+j),i,j,sa13);
			}
			minWait();		
		}
		sa13.assertAll();
	}

	@Test(priority=14,dataProvider="XP8_Stability", dataProviderClass=DataProviders.class)
	public void XP8_TC_014_Stability_Load_Website_Macys(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========XP8_Stability_14_Browser============");
		SoftAssert sa14 = new SoftAssert();
		launch_an_app("browser");
		for(int i=1; i<=itr;i++) {	  
			for(int j=14; j<=14; j++) {
				navigateBrowserMenu(data.get("Web"+j));	
				validateUrlEntered(data.get("Web"+j),i,j,sa14);
			}
			minWait();		
		}
		sa14.assertAll();
	}

	@Test(priority=15,dataProvider="XP8_Stability", dataProviderClass=DataProviders.class)
	public void XP8_TC_015_Stability_Load_Website_Staples(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========XP8_Stability_15_Browser============");
		SoftAssert sa15 = new SoftAssert();
		launch_an_app("browser");
		for(int i=1; i<=itr;i++) {	  
			for(int j=15; j<=15; j++) {
				navigateBrowserMenu(data.get("Web"+j));	
				validateUrlEntered(data.get("Web"+j),i,j,sa15);
			}
			minWait();		
		}
		sa15.assertAll();
	}


	@Test(priority=16,dataProvider="XP8_Stability", dataProviderClass=DataProviders.class)
	public void XP8_TC_016_Stability_Load_Website_Lenskart(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========XP8_Stability_16_Browser============");
		SoftAssert sa16 = new SoftAssert();
		launch_an_app("browser");
		for(int i=1; i<=itr;i++) {	  
			for(int j=16; j<=16; j++) {
				navigateBrowserMenu(data.get("Web"+j));	
				validateUrlEntered(data.get("Web"+j),i,j,sa16);
			}
			minWait();		
		}
		sa16.assertAll();
	}

	@Test(priority=17,dataProvider="XP8_Stability", dataProviderClass=DataProviders.class)
	public void XP8_TC_017_Stability_Load_Website_KFC(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========XP8_Stability_17_Browser============");
		SoftAssert sa17 = new SoftAssert();
		launch_an_app("browser");
		for(int i=1; i<=itr;i++) {	  
			for(int j=17; j<=17; j++) {
				navigateBrowserMenu(data.get("Web"+j));	
				validateUrlEntered(data.get("Web"+j),i,j,sa17);
			}
			minWait();		
		}
		sa17.assertAll();
	}

	@Test(priority=18,dataProvider="XP8_Stability", dataProviderClass=DataProviders.class)
	public void XP8_TC_018_Stability_Load_Website_McDonalds(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========XP8_Stability_18_Browser============");
		SoftAssert sa18 = new SoftAssert();
		launch_an_app("browser");
		for(int i=1; i<=itr;i++) {	  
			for(int j=18; j<=18; j++) {
				navigateBrowserMenu(data.get("Web"+j));	
				validateUrlEntered(data.get("Web"+j),i,j,sa18);
			}
			minWait();		
		}
		sa18.assertAll();
	}


	@Test(priority=19,dataProvider="XP8_Stability", dataProviderClass=DataProviders.class)
	public void XP8_TC_019_Stability_Load_Website_BurgerKing(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========XP8_Stability_19_Browser============");
		SoftAssert sa19 = new SoftAssert();
		launch_an_app("browser");
		for(int i=1; i<=itr;i++) {	  
			for(int j=19; j<=19; j++) {
				navigateBrowserMenu(data.get("Web"+j));	
				validateUrlEntered(data.get("Web"+j),i,j,sa19);
			}
			minWait();		
		}
		sa19.assertAll();
	}


	@Test(priority=20,dataProvider="XP8_Stability", dataProviderClass=DataProviders.class)
	public void XP8_TC_020_Stability_Load_Website_BestBuy(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========XP8_Stability_20_Browser============");
		SoftAssert sa20 = new SoftAssert();
		launch_an_app("browser");
		for(int i=1; i<=itr;i++) {	  
			for(int j=20; j<=20; j++) {
				navigateBrowserMenu(data.get("Web"+j));	
				validateUrlEntered(data.get("Web"+j),i,j,sa20);
			}
			minWait();		
		}
		sa20.assertAll();
	}

	@Test(priority=21,dataProvider="XP8_Stability", dataProviderClass=DataProviders.class)
	public void XP8_TC_021_Stability_Load_Website_Telenor(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========XP8_Stability_21_Browser============");
		SoftAssert sa21 = new SoftAssert();
		launch_an_app("browser");
		for(int i=1; i<=itr;i++) {	  
			for(int j=21; j<=21; j++) {
				navigateBrowserMenu(data.get("Web"+j));	
				validateUrlEntered(data.get("Web"+j),i,j,sa21);
			}
			minWait();		
		}
		sa21.assertAll();
	}

	@Test(priority=22,dataProvider="XP8_Stability", dataProviderClass=DataProviders.class)
	public void XP8_TC_022_Stability_Load_Website_DealsBuy(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========XP8_Stability_22_Browser============");
		SoftAssert sa22 = new SoftAssert();
		launch_an_app("browser");
		for(int i=1; i<=itr;i++) {	  
			for(int j=22; j<=22; j++) {
				navigateBrowserMenu(data.get("Web"+j));	
				validateUrlEntered(data.get("Web"+j),i,j,sa22);
			}
			minWait();		
		}
		sa22.assertAll();
	}

	@Test(priority=23,dataProvider="XP8_Stability", dataProviderClass=DataProviders.class)
	public void XP8_TC_023_Stability_Load_Website_PizzaHut(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========XP8_Stability_23_Browser============");
		SoftAssert sa23 = new SoftAssert();
		launch_an_app("browser");
		for(int i=1; i<=itr;i++) {	  
			for(int j=23; j<=23; j++) {
				navigateBrowserMenu(data.get("Web"+j));	
				validateUrlEntered(data.get("Web"+j),i,j,sa23);
			}
			minWait();		
		}
		sa23.assertAll();
	}

	@Test(priority=24,dataProvider="XP8_Stability", dataProviderClass=DataProviders.class)
	public void XP8_TC_024_Stability_Load_Website_AirportShuttles(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========XP8_Stability_24_Browser============");
		SoftAssert sa24 = new SoftAssert();
		launch_an_app("browser");
		for(int i=1; i<=itr;i++) {	  
			for(int j=24; j<=24; j++) {
				navigateBrowserMenu(data.get("Web"+j));	
				validateUrlEntered(data.get("Web"+j),i,j,sa24);
			}
			minWait();		
		}
		sa24.assertAll();
	}

	@Test(priority=25,dataProvider="XP8_Stability", dataProviderClass=DataProviders.class)
	public void XP8_TC_025_Stability_Load_Website_Supershuttle(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========XP8_Stability_25_Browser============");
		SoftAssert sa25 = new SoftAssert();
		launch_an_app("browser");
		for(int i=1; i<=itr;i++) {	  
			for(int j=25; j<=25; j++) {
				navigateBrowserMenu(data.get("Web"+j));	
				validateUrlEntered(data.get("Web"+j),i,j,sa25);
			}
			minWait();		
		}
		sa25.assertAll();
	}


	@Test(priority=26,dataProvider="XP8_Stability", dataProviderClass=DataProviders.class)
	public void XP8_TC_026_Stability_Load_Website_Uber(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========XP8_Stability_26_Browser============");
		SoftAssert sa26 = new SoftAssert();
		launch_an_app("browser");
		for(int i=1; i<=itr;i++) {	  
			for(int j=26; j<=26; j++) {
				navigateBrowserMenu(data.get("Web"+j));	
				validateUrlEntered(data.get("Web"+j),i,j,sa26);
			}
			minWait();		
		}
		sa26.assertAll();
	}


	@Test(priority=27,dataProvider="XP8_Stability", dataProviderClass=DataProviders.class)
	public void XP8_TC_027_Stability_Load_Website_CNN(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========XP8_Stability_27_Browser============");
		SoftAssert sa27 = new SoftAssert();
		launch_an_app("browser");
		for(int i=1; i<=itr;i++) {	  
			for(int j=27; j<=27; j++) {
				navigateBrowserMenu(data.get("Web"+j));	
				validateUrlEntered(data.get("Web"+j),i,j,sa27);
			}
			minWait();		
		}
		sa27.assertAll();
	}


	@Test(priority=28,dataProvider="XP8_Stability", dataProviderClass=DataProviders.class)
	public void XP8_TC_028_Stability_Load_Website_Reddit(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========XP8_Stability_28_Browser============");
		SoftAssert sa28 = new SoftAssert();
		launch_an_app("browser");
		for(int i=1; i<=itr;i++) {	  
			for(int j=28; j<=28; j++) {
				navigateBrowserMenu(data.get("Web"+j));	
				validateUrlEntered(data.get("Web"+j),i,j,sa28);
			}
			minWait();		
		}
		sa28.assertAll();
	}

	@Test(priority=29,dataProvider="XP8_Stability", dataProviderClass=DataProviders.class)
	public void XP8_TC_029_Stability_Load_Website_Fox(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========XP8_Stability_29_Browser============");
		SoftAssert sa29 = new SoftAssert();
		launch_an_app("browser");
		for(int i=1; i<=itr;i++) {	  
			for(int j=29; j<=29; j++) {
				navigateBrowserMenu(data.get("Web"+j));	
				validateUrlEntered(data.get("Web"+j),i,j,sa29);
			}
			minWait();		
		}
		sa29.assertAll();
	}

	@Test(priority=30,dataProvider="XP8_Stability", dataProviderClass=DataProviders.class)
	public void XP8_TC_030_Stability_Load_Website_BBC(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========XP8_Stability_30_Browser============");
		SoftAssert sa30 = new SoftAssert();
		launch_an_app("browser");
		for(int i=1; i<=itr;i++) {	  
			for(int j=30; j<=30; j++) {
				navigateBrowserMenu(data.get("Web"+j));	
				validateUrlEntered(data.get("Web"+j),i,j,sa30);
			}
			minWait();		
		}
		sa30.assertAll();
	}

	@Test(priority=31,dataProvider="XP8_Stability", dataProviderClass=DataProviders.class)
	public void XP8_TC_031_Stability_Load_Website_ESPN(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========XP8_Stability_31_Browser============");
		SoftAssert sa31 = new SoftAssert();
		launch_an_app("browser");
		for(int i=1; i<=itr;i++) {	  
			for(int j=31; j<=31; j++) {
				navigateBrowserMenu(data.get("Web"+j));	
				validateUrlEntered(data.get("Web"+j),i,j,sa31);
			}
			minWait();		
		}
		sa31.assertAll();
	}

	@Test(priority=32,dataProvider="XP8_Stability", dataProviderClass=DataProviders.class)
	public void XP8_TC_032_Stability_Load_Website_NFL(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========XP8_Stability_32_Browser============");
		SoftAssert sa32 = new SoftAssert();
		launch_an_app("browser");
		for(int i=1; i<=itr;i++) {	  
			for(int j=32; j<=32; j++) {
				navigateBrowserMenu(data.get("Web"+j));	
				validateUrlEntered(data.get("Web"+j),i,j,sa32);
			}
			minWait();		
		}
		sa32.assertAll();
	}


	@Test(priority=33,dataProvider="XP8_Stability", dataProviderClass=DataProviders.class)
	public void XP8_TC_033_Stability_Load_Website_Hertz(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========XP8_Stability_33_Browser============");
		SoftAssert sa33 = new SoftAssert();
		launch_an_app("browser");
		for(int i=1; i<=itr;i++) {	  
			for(int j=33; j<=33; j++) {
				navigateBrowserMenu(data.get("Web"+j));	
				validateUrlEntered(data.get("Web"+j),i,j,sa33);
			}
			minWait();		
		}
		sa33.assertAll();
	}

	@Test(priority=34,dataProvider="XP8_Stability", dataProviderClass=DataProviders.class)
	public void XP8_TC_034_Stability_Load_Website_Enterprise(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========XP8_Stability_34_Browser============");
		SoftAssert sa34 = new SoftAssert();
		launch_an_app("browser");
		for(int i=1; i<=itr;i++) {	  
			for(int j=34; j<=34; j++) {
				navigateBrowserMenu(data.get("Web"+j));	
				validateUrlEntered(data.get("Web"+j),i,j,sa34);
			}
			minWait();		
		}
		sa34.assertAll();
	}

	@Test(priority=35,dataProvider="XP8_Stability", dataProviderClass=DataProviders.class)
	public void XP8_TC_035_Stability_Load_Website_Avis(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========XP8_Stability_35_Browser============");
		SoftAssert sa35 = new SoftAssert();
		launch_an_app("browser");
		for(int i=1; i<=itr;i++) {	  
			for(int j=35; j<=35; j++) {
				navigateBrowserMenu(data.get("Web"+j));	
				validateUrlEntered(data.get("Web"+j),i,j,sa35);
			}
			minWait();		
		}
		sa35.assertAll();
	}

	@Test(priority=36,dataProvider="XP8_Stability", dataProviderClass=DataProviders.class)
	public void XP8_TC_036_Stability_Load_Website_Youtube(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========XP8_Stability_36_Browser============");
		SoftAssert sa36 = new SoftAssert();
		launch_an_app("browser");
		for(int i=1; i<=itr;i++) {	  
			for(int j=36; j<=36; j++) {
				navigateBrowserMenu(data.get("Web"+j));	
				validateUrlEntered(data.get("Web"+j),i,j,sa36);
			}
			minWait();		
		}
		sa36.assertAll();
	}

	@Test(priority=37,dataProvider="XP8_Stability", dataProviderClass=DataProviders.class)
	public void XP8_TC_037_Stability_Load_Website_Hulu(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========XP8_Stability_37_Browser============");
		SoftAssert sa37 = new SoftAssert();
		launch_an_app("browser");
		for(int i=1; i<=itr;i++) {	  
			for(int j=37; j<=37; j++) {
				navigateBrowserMenu(data.get("Web"+j));	
				validateUrlEntered(data.get("Web"+j),i,j,sa37);
			}
			minWait();		
		}
		sa37.assertAll();
	}

	@Test(priority=38,dataProvider="XP8_Stability", dataProviderClass=DataProviders.class)
	public void XP8_TC_038_Stability_Load_Website_Vimeo(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========XP8_Stability_38_Browser============");
		SoftAssert sa38 = new SoftAssert();
		launch_an_app("browser");
		for(int i=1; i<=itr;i++) {	  
			for(int j=38; j<=38; j++) {
				navigateBrowserMenu(data.get("Web"+j));	
				validateUrlEntered(data.get("Web"+j),i,j,sa38);
			}
			minWait();		
		}
		sa38.assertAll();
	}

	@Test(priority=39,dataProvider="XP8_Stability", dataProviderClass=DataProviders.class)
	public void XP8_TC_039_Stability_Load_Website_Netflix(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========XP8_Stability_39_Browser============");
		SoftAssert sa39 = new SoftAssert();
		launch_an_app("browser");
		for(int i=1; i<=itr;i++) {	  
			for(int j=39; j<=39; j++) {
				navigateBrowserMenu(data.get("Web"+j));	
				validateUrlEntered(data.get("Web"+j),i,j,sa39);
			}
			minWait();		
		}
		sa39.assertAll();
	}

	@Test(priority=40,dataProvider="XP8_Stability", dataProviderClass=DataProviders.class)
	public void XP8_TC_040_Stability_Load_Website_Spotify(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========XP8_Stability_40_Browser============");
		SoftAssert sa40 = new SoftAssert();
		launch_an_app("browser");
		for(int i=1; i<=itr;i++) {	  
			for(int j=40; j<=40; j++) {
				navigateBrowserMenu(data.get("Web"+j));	
				validateUrlEntered(data.get("Web"+j),i,j,sa40);
			}
			minWait();		
		}
		sa40.assertAll();
	}

	@Test(priority=41,dataProvider="XP8_Stability", dataProviderClass=DataProviders.class)
	public void XP8_TC_041_Stability_Load_Website_Dice(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========XP8_Stability_41_Browser============");
		SoftAssert sa41 = new SoftAssert();
		launch_an_app("browser");
		for(int i=1; i<=itr;i++) {	  
			for(int j=41; j<=41; j++) {
				navigateBrowserMenu(data.get("Web"+j));	
				validateUrlEntered(data.get("Web"+j),i,j,sa41);
			}
			minWait();		
		}
		sa41.assertAll();
	}

	@Test(priority=42,dataProvider="XP8_Stability", dataProviderClass=DataProviders.class)
	public void XP8_TC_042_Stability_Load_Website_Monster(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========XP8_Stability_42_Browser============");
		SoftAssert sa42 = new SoftAssert();
		launch_an_app("browser");
		for(int i=1; i<=itr;i++) {	  
			for(int j=42; j<=42; j++) {
				navigateBrowserMenu(data.get("Web"+j));	
				validateUrlEntered(data.get("Web"+j),i,j,sa42);
			}
			minWait();		
		}
		sa42.assertAll();
	}

	@Test(priority=43,dataProvider="XP8_Stability", dataProviderClass=DataProviders.class)
	public void XP8_TC_043_Stability_Load_Website_Indeed(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========XP8_Stability_43_Browser============");
		SoftAssert sa43 = new SoftAssert();
		launch_an_app("browser");
		for(int i=1; i<=itr;i++) {	  
			for(int j=43; j<=43; j++) {
				navigateBrowserMenu(data.get("Web"+j));	
				validateUrlEntered(data.get("Web"+j),i,j,sa43);
			}
			minWait();		
		}
		sa43.assertAll();
	}

	@Test(priority=44,dataProvider="XP8_Stability", dataProviderClass=DataProviders.class)
	public void XP8_TC_044_Stability_Load_Website_Facebook(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========XP8_Stability_44_Browser============");
		SoftAssert sa44 = new SoftAssert();
		launch_an_app("browser");
		for(int i=1; i<=itr;i++) {	  
			for(int j=44; j<=44; j++) {
				navigateBrowserMenu(data.get("Web"+j));	
				validateUrlEntered(data.get("Web"+j),i,j,sa44);
			}
			minWait();		
		}
		sa44.assertAll();
	}

	@Test(priority=45,dataProvider="XP8_Stability", dataProviderClass=DataProviders.class)
	public void XP8_TC_045_Stability_Load_Website_Twitter(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========XP8_Stability_45_Browser============");
		SoftAssert sa45 = new SoftAssert();
		launch_an_app("browser");
		for(int i=1; i<=itr;i++) {	  
			for(int j=45; j<=45; j++) {
				navigateBrowserMenu(data.get("Web"+j));	
				validateUrlEntered(data.get("Web"+j),i,j,sa45);
			}
			minWait();		
		}
		sa45.assertAll();
	}

	@Test(priority=46,dataProvider="XP8_Stability", dataProviderClass=DataProviders.class)
	public void XP8_TC_046_Stability_Load_Website_Instagram(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========XP8_Stability_46_Browser============");
		SoftAssert sa46 = new SoftAssert();
		launch_an_app("browser");
		for(int i=1; i<=itr;i++) {	  
			for(int j=46; j<=46; j++) {
				navigateBrowserMenu(data.get("Web"+j));	
				validateUrlEntered(data.get("Web"+j),i,j,sa46);
			}
			minWait();		
		}
		sa46.assertAll();
	}

	@Test(priority=47,dataProvider="XP8_Stability", dataProviderClass=DataProviders.class)
	public void XP8_TC_047_Stability_Load_Website_MeetUp(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========XP8_Stability_47_Browser============");
		SoftAssert sa47 = new SoftAssert();
		launch_an_app("browser");
		for(int i=1; i<=itr;i++) {	  
			for(int j=47; j<=47; j++) {
				navigateBrowserMenu(data.get("Web"+j));	
				validateUrlEntered(data.get("Web"+j),i,j,sa47);
			}
			minWait();		
		}
		sa47.assertAll();
	}

	@Test(priority=48,dataProvider="XP8_Stability", dataProviderClass=DataProviders.class)
	public void XP8_TC_048_Stability_Load_Website_Fedex(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========XP8_Stability_48_Browser============");
		SoftAssert sa48 = new SoftAssert();
		launch_an_app("browser");
		for(int i=1; i<=itr;i++) {	  
			for(int j=48; j<=48; j++) {
				navigateBrowserMenu(data.get("Web"+j));	
				validateUrlEntered(data.get("Web"+j),i,j,sa48);
			}
			minWait();		
		}
		sa48.assertAll();
	}

	@Test(priority=49,dataProvider="XP8_Stability", dataProviderClass=DataProviders.class)
	public void XP8_TC_049_Stability_Load_Website_DHL(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========XP8_Stability_49_Browser============");
		SoftAssert sa49 = new SoftAssert();
		launch_an_app("browser");
		for(int i=1; i<=itr;i++) {	  
			for(int j=49; j<=49; j++) {
				navigateBrowserMenu(data.get("Web"+j));	
				validateUrlEntered(data.get("Web"+j),i,j,sa49);
			}
			minWait();		
		}
		sa49.assertAll();
	}

	@Test(priority=50,dataProvider="XP8_Stability", dataProviderClass=DataProviders.class)
	public void XP8_TC_050_Stability_Load_Website_DTDC(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========XP8_Stability_50_Browser============");
		SoftAssert sa50 = new SoftAssert();
		launch_an_app("browser");
		for(int i=1; i<=itr;i++) {	  
			for(int j=50; j<=50; j++) {
				navigateBrowserMenu(data.get("Web"+j));	
				validateUrlEntered(data.get("Web"+j),i,j,sa50);
			}
			minWait();		
		}
		sa50.assertAll();
	}
}
