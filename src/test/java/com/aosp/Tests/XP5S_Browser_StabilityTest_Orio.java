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
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aosp.Utils.DataProviders;
import com.aosp.Utils.Locators_Stability;
import com.aosp.Utils.XP5S_Stability_Util;
import com.graphics.gui.JsonFileReaderAndWriter;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTestInterruptedException;
import com.relevantcodes.extentreports.LogStatus;
import com.xp5S.util.BaseUtil;
import com.xp5S.util.ExcelConstants;
import com.xp5S.util.ExcelReader;
import com.xp5S.util.GetMethods;
import com.xp5S.util.Locators_BaseUtil;
import com.xp5S.util.appiumService;

import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class XP5S_Browser_StabilityTest_Orio  extends XP5S_Stability_Util  {
	
	Properties properties;
	public ExcelReader excel;
	public static ExtentTestInterruptedException testexception;

	public int  itr =10;
	boolean value = false;

	@BeforeSuite
	public void beforeSuite() throws IOException, InterruptedException, ParseException
	{
		extent = new ExtentReports("src/test/resources/extentreport/XP5S_Browser_Stability_Orio_TestReport.html", true);
		fetch_Devices_Details();
		
		try {
			appiumService.TOTAL_NUM_OF_TESTCASES=GetMethods.TotalTestcase("XP3_TC", this.getClass());
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
	public void tearDown(ITestResult result,Method method) throws IOException, InterruptedException
	{
		if(result.getStatus()==ITestResult.FAILURE)
		{
			String screenshot_path=captureScreenshot(method.getName());		
			test.log(LogStatus.FAIL,result.getThrowable());
			test.log(LogStatus.FAIL, "screenshot for failure:" +test.addScreenCapture(screenshot_path));
			clearRecentApps();
		}
		extent.endTest(test);
		extent.flush();
	}


	@BeforeTest
	public void setupSys() throws MalformedURLException, InterruptedException, FileNotFoundException, AWTException{
		properties=loadDriverAndProperties();
		Locators_Stability loc=new Locators_Stability(aDriver);
		Locators_BaseUtil loc1 =new Locators_BaseUtil(aDriver);
		PageFactory.initElements(new AppiumFieldDecorator(getDriver()),loc1);
		PageFactory.initElements(new AppiumFieldDecorator(getDriver()),loc);
		excel=new ExcelReader(ExcelConstants.XP5S_XL_PATH);		
	}
	
	@Test(priority=1,dataProvider="XP5S_Stability", dataProviderClass=DataProviders.class)
	public void XP5S_TC_001_Stability_Load_Website_Google(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException 
	{
		clearRecentApps();
		launch_an_app("settings");
		enableData();
		launch_an_app("settings");
		selectOptn_Orio("Network & Internet",Locators_Stability.NetworkInternet) ;		
		selectWIFIOptn();
		enableFeature(Locators_Stability.enabled,Locators_Stability.disabled,Locators_Stability.switch_Title);
		minWait();
		selectSSIDwifi();
		enterPassword();		
		launch_an_app("browser");	  
		for(int i=1; i<=itr;i++) {	  

			for(int j=1; j<=1; j++) {
				enterUrl(data.get("Web"+j));
				validateUrlEntered_O(data.get("Web"+j),i,j);
			}
			minWait();	
		}
	}


	@Test(priority=2,dataProvider="XP5S_Stability", dataProviderClass=DataProviders.class)
	public void XP5S_TC_002_Stability_Load_Website_Yahoo(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========XP5S_Stability_02_Browser============");

		launch_an_app("browser");
		for(int i=1; i<=itr;i++) {	  

			for(int j=2; j<=2; j++) {
				enterUrl(data.get("Web"+j));
				validateUrlEntered_O(data.get("Web"+j),i,j);
			}
			minWait();		
		}
	}

	@Test(priority=3,dataProvider="XP5S_Stability", dataProviderClass=DataProviders.class)
	public void XP5S_TC_003_Stability_Load_Website_Bingo(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========XP5S_Stability_03_Browser============");

		launch_an_app("browser");
		for(int i=1; i<=itr;i++) {	  

			for(int j=3; j<=3; j++) {
				enterUrl(data.get("Web"+j));
				validateUrlEntered_O(data.get("Web"+j),i,j);
			}
			minWait();		
		}
	}


	@Test(priority=4,dataProvider="XP5S_Stability", dataProviderClass=DataProviders.class)
	public void XP5S_TC_004_Stability_Load_Website_BankOfAmerica(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========XP5S_Stability_04_Browser============");

		launch_an_app("browser");
		for(int i=1; i<=itr;i++) {	  

			for(int j=4; j<=4; j++) {
				enterUrl(data.get("Web"+j));
				validateUrlEntered_O(data.get("Web"+j),i,j);
			}
			minWait();		
		}
	}


	@Test(priority=5,dataProvider="XP5S_Stability", dataProviderClass=DataProviders.class)
	public void XP5S_TC_005_Stability_Load_Website_Chase(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("==========XP5S_Stability_05_Browser============");

		launch_an_app("browser");
		for(int i=1; i<=itr;i++) {	

			for(int j=5; j<=5; j++) {
				enterUrl(data.get("Web"+j));
				validateUrlEntered_O(data.get("Web"+j),i,j);
			}
			minWait();		
		}
	}


	@Test(priority=6,dataProvider="XP5S_Stability", dataProviderClass=DataProviders.class)
	public void XP5S_TC_006_Stability_Load_Website_CitiBank(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========XP5S_Stability_06_Browser============");

		launch_an_app("browser");
		for(int i=1; i<=itr;i++) {	  

			for(int j=6; j<=6; j++) {
				enterUrl(data.get("Web"+j));
				validateUrlEntered_O(data.get("Web"+j),i,j);
			}
			minWait();		
		}
	}

	@Test(priority=7,dataProvider="XP5S_Stability", dataProviderClass=DataProviders.class)
	public void XP5S_TC_007_Stability_Load_Website_Wellsfargo(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========XP5S_Stability_07_Browser============");

		launch_an_app("browser");
		for(int i=1; i<=itr;i++) {	

			for(int j=7; j<=7; j++) {
				enterUrl(data.get("Web"+j));
				validateUrlEntered_O(data.get("Web"+j),i,j);
			}
			minWait();		
		}
	}


	@Test(priority=8,dataProvider="XP5S_Stability", dataProviderClass=DataProviders.class)
	public void XP5S_TC_008_Stability_Load_Website_Paypal(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========XP5S_Stability_08_Browser============");

		launch_an_app("browser");
		for(int i=1; i<=itr;i++) {	 

			for(int j=8; j<=8; j++) {
				enterUrl(data.get("Web"+j));
				validateUrlEntered_O(data.get("Web"+j),i,j);
			}
			minWait();		
		}
	}


	@Test(priority=9,dataProvider="XP5S_Stability", dataProviderClass=DataProviders.class)
	public void XP5S_TC_009_Stability_Load_Website_Amazon(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========XP5S_Stability_09_Browser============");


		launch_an_app("browser");
		for(int i=1; i<=itr;i++) {	 

			for(int j=9; j<=9; j++) {
				enterUrl(data.get("Web"+j));
				validateUrlEntered_O(data.get("Web"+j),i,j);
			}
			minWait();		
		}
	}

	@Test(priority=10,dataProvider="XP5S_Stability", dataProviderClass=DataProviders.class)
	public void XP5S_TC_010_Stability_Load_Website_Ebay(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========XP5S_Stability_10_Browser============");

		launch_an_app("browser");
		for(int i=1; i<=itr;i++) {	  

			for(int j=10; j<=10; j++) {
				enterUrl(data.get("Web"+j));
				validateUrlEntered_O(data.get("Web"+j),i,j);
			}
			minWait();		
		}
	}

	@Test(priority=11,dataProvider="XP5S_Stability", dataProviderClass=DataProviders.class)
	public void XP5S_TC_011_Stability_Load_Website_Walmart(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========XP5S_Stability_11_Browser============");

		launch_an_app("browser");
		for(int i=1; i<=itr;i++) {	  

			for(int j=11; j<=11; j++) {
				enterUrl(data.get("Web"+j));
				validateUrlEntered_O(data.get("Web"+j),i,j);
			}
			minWait();		
		}
	}

	@Test(priority=12,dataProvider="XP5S_Stability", dataProviderClass=DataProviders.class)
	public void XP5S_TC_012_Stability_Load_Website_Craigslist(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========XP5S_Stability_12_Browser============");

		launch_an_app("browser");
		for(int i=1; i<=itr;i++) {	 

			for(int j=12; j<=12; j++) {
				enterUrl(data.get("Web"+j));
				validateUrlEntered_O(data.get("Web"+j),i,j);
			}
			minWait();		
		}
	}

	@Test(priority=13,dataProvider="XP5S_Stability", dataProviderClass=DataProviders.class)
	public void XP5S_TC_013_Stability_Load_Website_Kohls(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========XP5S_Stability_13_Browser============");

		launch_an_app("browser");
		for(int i=1; i<=itr;i++) {	  

			for(int j=13; j<=13; j++) {
				enterUrl(data.get("Web"+j));
				validateUrlEntered_O(data.get("Web"+j),i,j);
			}
			minWait();		
		}
	}

	@Test(priority=14,dataProvider="XP5S_Stability", dataProviderClass=DataProviders.class)
	public void XP5S_TC_014_Stability_Load_Website_Macys(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========XP5S_Stability_14_Browser============");

		launch_an_app("browser");
		for(int i=1; i<=itr;i++) {	  
			for(int j=14; j<=14; j++) {
				enterUrl(data.get("Web"+j));
				validateUrlEntered_O(data.get("Web"+j),i,j);
			}
			minWait();		
		}
	}

	@Test(priority=15,dataProvider="XP5S_Stability", dataProviderClass=DataProviders.class)
	public void XP5S_TC_015_Stability_Load_Website_Staples(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========XP5S_Stability_15_Browser============");

		launch_an_app("browser");
		for(int i=1; i<=itr;i++) {	  
			for(int j=15; j<=15; j++) {
				enterUrl(data.get("Web"+j));
				validateUrlEntered_O(data.get("Web"+j),i,j);
			}
			minWait();		
		}
	}


	@Test(priority=16,dataProvider="XP5S_Stability", dataProviderClass=DataProviders.class)
	public void XP5S_TC_016_Stability_Load_Website_TigerdirectBuisness(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========XP5S_Stability_16_Browser============");

		launch_an_app("browser");
		for(int i=1; i<=itr;i++) {	  
			for(int j=16; j<=16; j++) {
				enterUrl(data.get("Web"+j));
				validateUrlEntered_O(data.get("Web"+j),i,j);
			}
			minWait();		
		}
	}

	@Test(priority=17,dataProvider="XP5S_Stability", dataProviderClass=DataProviders.class)
	public void XP5S_TC_017_Stability_Load_Website_KFC(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========XP5S_Stability_17_Browser============");

		launch_an_app("browser");
		for(int i=1; i<=itr;i++) {	  
			for(int j=17; j<=17; j++) {
				enterUrl(data.get("Web"+j));
				validateUrlEntered_O(data.get("Web"+j),i,j);
			}
			minWait();		
		}
	}

	@Test(priority=18,dataProvider="XP5S_Stability", dataProviderClass=DataProviders.class)
	public void XP5S_TC_018_Stability_Load_Website_McDonalds(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========XP5S_Stability_18_Browser============");

		launch_an_app("browser");
		for(int i=1; i<=itr;i++) {	  
			for(int j=18; j<=18; j++) {
				enterUrl(data.get("Web"+j));
				validateUrlEntered_O(data.get("Web"+j),i,j);
			}
			minWait();		
		}
	}


	@Test(priority=19,dataProvider="XP5S_Stability", dataProviderClass=DataProviders.class)
	public void XP5S_TC_019_Stability_Load_Website_BurgerKing(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========XP5S_Stability_19_Browser============");

		launch_an_app("browser");
		for(int i=1; i<=itr;i++) {	  
			for(int j=19; j<=19; j++) {
				enterUrl(data.get("Web"+j));
				validateUrlEntered_O(data.get("Web"+j),i,j);
			}
			minWait();		
		}
	}


	@Test(priority=20,dataProvider="XP5S_Stability", dataProviderClass=DataProviders.class)
	public void XP5S_TC_020_Stability_Load_Website_BestBuy(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========XP5S_Stability_20_Browser============");

		launch_an_app("browser");
		for(int i=1; i<=itr;i++) {	  
			for(int j=20; j<=20; j++) {
				enterUrl(data.get("Web"+j));
				validateUrlEntered_O(data.get("Web"+j),i,j);
			}
			minWait();		
		}
	}

	@Test(priority=21,dataProvider="XP5S_Stability", dataProviderClass=DataProviders.class)
	public void XP5S_TC_021_Stability_Load_Website_Grocery(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========XP5S_Stability_21_Browser============");

		launch_an_app("browser");
		for(int i=1; i<=itr;i++) {	  
			for(int j=21; j<=21; j++) {
				enterUrl(data.get("Web"+j));
				validateUrlEntered_O(data.get("Web"+j),i,j);
			}
			minWait();		
		}
	}

	@Test(priority=22,dataProvider="XP5S_Stability", dataProviderClass=DataProviders.class)
	public void XP5S_TC_022_Stability_Load_Website_DealsBuy(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========XP5S_Stability_22_Browser============");

		launch_an_app("browser");
		for(int i=1; i<=itr;i++) {	  
			for(int j=22; j<=22; j++) {
				enterUrl(data.get("Web"+j));
				validateUrlEntered_O(data.get("Web"+j),i,j);
			}
			minWait();		
		}
	}

	@Test(priority=23,dataProvider="XP5S_Stability", dataProviderClass=DataProviders.class)
	public void XP5S_TC_023_Stability_Load_Website_PizzaHut(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========XP5S_Stability_23_Browser============");

		launch_an_app("browser");
		for(int i=1; i<=itr;i++) {	  
			for(int j=23; j<=23; j++) {
				enterUrl(data.get("Web"+j));
				validateUrlEntered_O(data.get("Web"+j),i,j);
			}
			minWait();		
		}
	}

	@Test(priority=24,dataProvider="XP5S_Stability", dataProviderClass=DataProviders.class)
	public void XP5S_TC_024_Stability_Load_Website_AirportShuttles(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========XP5S_Stability_24_Browser============");

		launch_an_app("browser");
		for(int i=1; i<=itr;i++) {	  
			for(int j=24; j<=24; j++) {
				enterUrl(data.get("Web"+j));
				validateUrlEntered_O(data.get("Web"+j),i,j);
			}
			minWait();		
		}
	}

	@Test(priority=25,dataProvider="XP5S_Stability", dataProviderClass=DataProviders.class)
	public void XP5S_TC_025_Stability_Load_Website_Supershuttle(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========XP5S_Stability_25_Browser============");

		launch_an_app("browser");
		for(int i=1; i<=itr;i++) {	  
			for(int j=25; j<=25; j++) {
				enterUrl(data.get("Web"+j));
				validateUrlEntered_O(data.get("Web"+j),i,j);
			}
			minWait();		
		}
	}


	@Test(priority=26,dataProvider="XP5S_Stability", dataProviderClass=DataProviders.class)
	public void XP5S_TC_026_Stability_Load_Website_Uber(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========XP5S_Stability_26_Browser============");

		launch_an_app("browser");
		for(int i=1; i<=itr;i++) {	  
			for(int j=26; j<=26; j++) {
				enterUrl(data.get("Web"+j));
				validateUrlEntered_O(data.get("Web"+j),i,j);
			}
			minWait();		
		}
	}


	@Test(priority=27,dataProvider="XP5S_Stability", dataProviderClass=DataProviders.class)
	public void XP5S_TC_027_Stability_Load_Website_CNN(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========XP5S_Stability_27_Browser============");

		launch_an_app("browser");
		for(int i=1; i<=itr;i++) {	  
			for(int j=27; j<=27; j++) {
				enterUrl(data.get("Web"+j));
				validateUrlEntered_O(data.get("Web"+j),i,j);
			}
			minWait();		
		}
	}


	@Test(priority=28,dataProvider="XP5S_Stability", dataProviderClass=DataProviders.class)
	public void XP5S_TC_028_Stability_Load_Website_Reddit(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========XP5S_Stability_28_Browser============");

		launch_an_app("browser");
		for(int i=1; i<=itr;i++) {	  
			for(int j=28; j<=28; j++) {
				enterUrl(data.get("Web"+j));
				validateUrlEntered_O(data.get("Web"+j),i,j);
			}
			minWait();		
		}
	}

	@Test(priority=29,dataProvider="XP5S_Stability", dataProviderClass=DataProviders.class)
	public void XP5S_TC_029_Stability_Load_Website_Fox(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========XP5S_Stability_29_Browser============");

		launch_an_app("browser");
		for(int i=1; i<=itr;i++) {	  
			for(int j=29; j<=29; j++) {
				enterUrl(data.get("Web"+j));
				validateUrlEntered_O(data.get("Web"+j),i,j);
			}
			minWait();		
		}
	}

	@Test(priority=30,dataProvider="XP5S_Stability", dataProviderClass=DataProviders.class)
	public void XP5S_TC_030_Stability_Load_Website_BBC(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========XP5S_Stability_30_Browser============");

		launch_an_app("browser");
		for(int i=1; i<=itr;i++) {	  
			for(int j=30; j<=30; j++) {
				enterUrl(data.get("Web"+j));
				validateUrlEntered_O(data.get("Web"+j),i,j);
			}
			minWait();		
		}
	}

	@Test(priority=31,dataProvider="XP5S_Stability", dataProviderClass=DataProviders.class)
	public void XP5S_TC_031_Stability_Load_Website_ESPN(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========XP5S_Stability_31_Browser============");

		launch_an_app("browser");
		for(int i=1; i<=itr;i++) {	  
			for(int j=31; j<=31; j++) {
				enterUrl(data.get("Web"+j));
				validateUrlEntered_O(data.get("Web"+j),i,j);
			}
			minWait();		
		}
	}

	@Test(priority=32,dataProvider="XP5S_Stability", dataProviderClass=DataProviders.class)
	public void XP5S_TC_032_Stability_Load_Website_NFL(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========XP5S_Stability_32_Browser============");

		launch_an_app("browser");
		for(int i=1; i<=itr;i++) {	  
			for(int j=32; j<=32; j++) {
				enterUrl(data.get("Web"+j));
				validateUrlEntered_O(data.get("Web"+j),i,j);
			}
			minWait();		
		}
	}


	@Test(priority=33,dataProvider="XP5S_Stability", dataProviderClass=DataProviders.class)
	public void XP5S_TC_033_Stability_Load_Website_Hertz(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========XP5S_Stability_33_Browser============");

		launch_an_app("browser");
		for(int i=1; i<=itr;i++) {	  
			for(int j=33; j<=33; j++) {
				enterUrl(data.get("Web"+j));
				validateUrlEntered_O(data.get("Web"+j),i,j);
			}
			minWait();		
		}
	}

	@Test(priority=34,dataProvider="XP5S_Stability", dataProviderClass=DataProviders.class)
	public void XP5S_TC_034_Stability_Load_Website_Enterprise(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========XP5S_Stability_34_Browser============");

		launch_an_app("browser");
		for(int i=1; i<=itr;i++) {	  
			for(int j=34; j<=34; j++) {
				enterUrl(data.get("Web"+j));
				validateUrlEntered_O(data.get("Web"+j),i,j);
			}
			minWait();		
		}
	}

	@Test(priority=35,dataProvider="XP5S_Stability", dataProviderClass=DataProviders.class)
	public void XP5S_TC_035_Stability_Load_Website_Avis(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========XP5S_Stability_35_Browser============");

		launch_an_app("browser");
		for(int i=1; i<=itr;i++) {	  
			for(int j=35; j<=35; j++) {
				enterUrl(data.get("Web"+j));
				validateUrlEntered_O(data.get("Web"+j),i,j);
			}
			minWait();		
		}
	}

	@Test(priority=36,dataProvider="XP5S_Stability", dataProviderClass=DataProviders.class)
	public void XP5S_TC_036_Stability_Load_Website_Youtube(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========XP5S_Stability_36_Browser============");

		launch_an_app("browser");
		for(int i=1; i<=itr;i++) {	  
			for(int j=36; j<=36; j++) {
				enterUrl(data.get("Web"+j));
				validateUrlEntered_O(data.get("Web"+j),i,j);
			}
			minWait();		
		}
	}

	@Test(priority=37,dataProvider="XP5S_Stability", dataProviderClass=DataProviders.class)
	public void XP5S_TC_037_Stability_Load_Website_Hulu(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========XP5S_Stability_37_Browser============");

		launch_an_app("browser");
		for(int i=1; i<=itr;i++) {	  
			for(int j=37; j<=37; j++) {
				enterUrl(data.get("Web"+j));
				validateUrlEntered_O(data.get("Web"+j),i,j);
			}
			minWait();		
		}
	}

	@Test(priority=38,dataProvider="XP5S_Stability", dataProviderClass=DataProviders.class)
	public void XP5S_TC_038_Stability_Load_Website_Vimeo(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========XP5S_Stability_38_Browser============");

		launch_an_app("browser");
		for(int i=1; i<=itr;i++) {	  
			for(int j=38; j<=38; j++) {
				enterUrl(data.get("Web"+j));
				validateUrlEntered_O(data.get("Web"+j),i,j);
			}
			minWait();		
		}
	}

	@Test(priority=39,dataProvider="XP5S_Stability", dataProviderClass=DataProviders.class)
	public void XP5S_TC_039_Stability_Load_Website_Netflix(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========XP5S_Stability_39_Browser============");

		launch_an_app("browser");
		for(int i=1; i<=itr;i++) {	  
			for(int j=39; j<=39; j++) {
				enterUrl(data.get("Web"+j));
				validateUrlEntered_O(data.get("Web"+j),i,j);
			}
			minWait();		
		}
	}

	@Test(priority=40,dataProvider="XP5S_Stability", dataProviderClass=DataProviders.class)
	public void XP5S_TC_040_Stability_Load_Website_Spotify(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========XP5S_Stability_40_Browser============");

		launch_an_app("browser");
		for(int i=1; i<=itr;i++) {	  
			for(int j=40; j<=40; j++) {
				enterUrl(data.get("Web"+j));
				validateUrlEntered_O(data.get("Web"+j),i,j);
			}
			minWait();		
		}
	}

	@Test(priority=41,dataProvider="XP5S_Stability", dataProviderClass=DataProviders.class)
	public void XP5S_TC_041_Stability_Load_Website_Dice(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========XP5S_Stability_41_Browser============");

		launch_an_app("browser");
		for(int i=1; i<=itr;i++) {	  
			for(int j=41; j<=41; j++) {
				enterUrl(data.get("Web"+j));
				validateUrlEntered_O(data.get("Web"+j),i,j);
			}
			minWait();		
		}
	}

	@Test(priority=42,dataProvider="XP5S_Stability", dataProviderClass=DataProviders.class)
	public void XP5S_TC_042_Stability_Load_Website_Monster(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========XP5S_Stability_42_Browser============");

		launch_an_app("browser");
		for(int i=1; i<=itr;i++) {	  
			for(int j=42; j<=42; j++) {
				enterUrl(data.get("Web"+j));
				validateUrlEntered_O(data.get("Web"+j),i,j);
			}
			minWait();		
		}
	}

	@Test(priority=43,dataProvider="XP5S_Stability", dataProviderClass=DataProviders.class)
	public void XP5S_TC_043_Stability_Load_Website_Indeed(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========XP5S_Stability_43_Browser============");

		launch_an_app("browser");
		for(int i=1; i<=itr;i++) {	  
			for(int j=43; j<=43; j++) {
				enterUrl(data.get("Web"+j));
				validateUrlEntered_O(data.get("Web"+j),i,j);
			}
			minWait();		
		}
	}

	@Test(priority=44,dataProvider="XP5S_Stability", dataProviderClass=DataProviders.class)
	public void XP5S_TC_044_Stability_Load_Website_Facebook(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========XP5S_Stability_44_Browser============");

		launch_an_app("browser");
		for(int i=1; i<=itr;i++) {	  
			for(int j=44; j<=44; j++) {
				enterUrl(data.get("Web"+j));
				validateUrlEntered_O(data.get("Web"+j),i,j);
			}
			minWait();		
		}
	}

	@Test(priority=45,dataProvider="XP5S_Stability", dataProviderClass=DataProviders.class)
	public void XP5S_TC_045_Stability_Load_Website_Twitter(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========XP5S_Stability_45_Browser============");

		launch_an_app("browser");
		for(int i=1; i<=itr;i++) {	  
			for(int j=45; j<=45; j++) {
				enterUrl(data.get("Web"+j));
				validateUrlEntered_O(data.get("Web"+j),i,j);
			}
			minWait();		
		}
	}

	@Test(priority=46,dataProvider="XP5S_Stability", dataProviderClass=DataProviders.class)
	public void XP5S_TC_046_Stability_Load_Website_Instagram(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========XP5S_Stability_46_Browser============");

		launch_an_app("browser");
		for(int i=1; i<=itr;i++) {	  
			for(int j=46; j<=46; j++) {
				enterUrl(data.get("Web"+j));
				validateUrlEntered_O(data.get("Web"+j),i,j);
			}
			minWait();		
		}
	}

	@Test(priority=47,dataProvider="XP5S_Stability", dataProviderClass=DataProviders.class)
	public void XP5S_TC_047_Stability_Load_Website_MeetUp(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========XP5S_Stability_47_Browser============");

		launch_an_app("browser");
		for(int i=1; i<=itr;i++) {	  
			for(int j=47; j<=47; j++) {
				enterUrl(data.get("Web"+j));
				validateUrlEntered_O(data.get("Web"+j),i,j);
			}
			minWait();		
		}
	}

	@Test(priority=48,dataProvider="XP5S_Stability", dataProviderClass=DataProviders.class)
	public void XP5S_TC_048_Stability_Load_Website_Fedex(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========XP5S_Stability_48_Browser============");

		launch_an_app("browser");
		for(int i=1; i<=itr;i++) {	  
			for(int j=48; j<=48; j++) {
				enterUrl(data.get("Web"+j));
				validateUrlEntered_O(data.get("Web"+j),i,j);
			}
			minWait();		
		}
	}

	@Test(priority=49,dataProvider="XP5S_Stability", dataProviderClass=DataProviders.class)
	public void XP5S_TC_049_Stability_Load_Website_DHL(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========XP5S_Stability_49_Browser============");

		launch_an_app("browser");
		for(int i=1; i<=itr;i++) {	  
			for(int j=49; j<=49; j++) {
				enterUrl(data.get("Web"+j));
				validateUrlEntered_O(data.get("Web"+j),i,j);
			}
			minWait();		
		}
	}

	@Test(priority=50,dataProvider="XP5S_Stability", dataProviderClass=DataProviders.class)
	public void XP5S_TC_050_Stability_Load_Website_DTDC(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========XP5S_Stability_50_Browser============");

		launch_an_app("browser");
		for(int i=1; i<=itr;i++) {	  
			for(int j=50; j<=50; j++) {
				enterUrl(data.get("Web"+j));
				validateUrlEntered_O(data.get("Web"+j),i,j);
			}
			minWait();		
		}
	}


}
