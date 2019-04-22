/*package com.xp8.test;

import java.awt.AWTException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.util.Hashtable;
import java.util.Properties;

import javax.swing.JOptionPane;

import org.openqa.selenium.support.PageFactory;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.graphics.gui.CountIncrementFile;
import com.graphics.gui.ToolFrame;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.ExtentTestInterruptedException;
import com.relevantcodes.extentreports.LogStatus;
import com.xp8.util.DataProviders;
import com.xp8.util.ExcelConstants;
import com.xp8.util.ExcelReader;
import com.xp8.util.Locators_BaseUtil;
import com.xp8.util.Locators_Browser;
import com.xp8.util.Locators_SoundRec;
import com.xp8.util.XP8_Browser_Util;


import io.appium.java_client.TouchAction;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class XP8_Browser_Test extends XP8_Browser_Util {

	
	public ExcelReader excel;
	Properties properties;
	public static ExtentReports extent;
	public static ExtentTest test; 
	public static ExtentTestInterruptedException testexception;
	//public AndroidDriver<AndroidElement> aDriver;
	
	@BeforeSuite
	public void beforeSuite() 
	{
		extent = new ExtentReports("src/test/resources/extentreport/XP8_BrowserTest.html", true); //Provide Desired Report Directory Location and Name
		extent.loadConfig(new File(System.getProperty("user.dir")+"//ReportsConfig.xml"));
		extent.addSystemInfo("Appium", "1.2.7").addSystemInfo("Environment", "TEST");	
	}
	
	@BeforeMethod()
	public  void beforeMethod(Method method) 
	{
		test = extent.startTest( (this.getClass().getSimpleName() +" :: "+  method.getName()),method.getName()); //Test Case Start Here
		test.assignAuthor("Farheen Taj"); //Test Script Author Name
	}
	
	@AfterMethod()
	public void tearDown(ITestResult result,Method method) throws IOException, InterruptedException
	{
		if(result.getStatus()==ITestResult.FAILURE)
		{	String screenshot_path=captureScreenshot(method.getName());
		   String image= test.addScreenCapture(screenshot_path);		
			test.log(LogStatus.FAIL,result.getThrowable());	
           clearRecentApps();			
		}
		extent.endTest(test);
		extent.flush();
	}
	
	
	@AfterMethod
	public void setProgressBar_TestResult() {
		
		  
		  int count =CountIncrementFile.getCount(5);  
		  if(count==95) {
			  count=count+5;
			  CountIncrementFile.putCount(count);
			  
		  }
		  else {
			 
				ToolFrame.progressBar.setValue(count);
			    count=count+5;
				CountIncrementFile.putCount(count);
		  }
		 
		
	}
	
	
	@BeforeTest
	public void setupSys() throws MalformedURLException, InterruptedException, FileNotFoundException, AWTException{
		properties=loadDriverAndProperties();
		//aDriver = (AndroidDriver<AndroidElement>)driver;
		Locators_Browser loc=new Locators_Browser(aDriver);
		Locators_BaseUtil loc1 = new Locators_BaseUtil(aDriver);	
		PageFactory.initElements(new AppiumFieldDecorator(aDriver),loc);
		PageFactory.initElements(new AppiumFieldDecorator(aDriver),loc1);
		excel=new ExcelReader(ExcelConstants.XP5S_XL_PATH);	
	}
	
	@Test(priority=1,dataProvider="XP8_Browser", dataProviderClass=DataProviders.class)
	public void XP8_BRWSR_001_Launch_and_Exit_Browser(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========XP8_BRWSR_001============");
		clearRecentApps();
		startAdbLog("XP8_BRWSR_001");
		recordVideo("XP8_BRWSR_001");
		launchBrowser();
		validateChromeBrowserLaunch();
		test.log(LogStatus.PASS, "Test case status is Passed");	
	}

	@Test(priority=2,dataProvider="XP8_Browser", dataProviderClass=DataProviders.class)
	public void XP8_BRWSR_002_Enter_URL_and_Refresh(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========XP8_BRWSR_002============");
		clearRecentApps();
		startAdbLog("XP8_BRWSR_002");
		recordVideo("XP8_BRWSR_002");
		launchBrowser();
		enterUrl(data.get("newUrl"));
		validateUrlEntered();
		refreshUrl();
		validaterefresh();
		deleteAllTabs();
		test.log(LogStatus.PASS, "Test case status is Passed");	
	}
	
	@Test(priority=3,dataProvider="XP8_Browser", dataProviderClass=DataProviders.class)
	public void XP8_BRWSR_003_Select_NewTab_with_DiffSites_and_Delete(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========XP8_BRWSR_003============");
		clearRecentApps();
		startAdbLog("XP8_BRWSR_003");
		recordVideo("XP8_BRWSR_003");
		launchBrowser();
		enterUrl(data.get("newUrl"));
		selectNewTab();
		enterUrlNewTab();
		ValidateNewTabsOpen();
		deleteAllTabs();
		test.log(LogStatus.PASS, "Test case status is Passed");	
	}
	

	@Test(priority=4,dataProvider="XP8_Browser", dataProviderClass=DataProviders.class)
	public void XP8_BRWSR_004_Validate_History_pages(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========XP8_BRWSR_004============");
		clearRecentApps();
		startAdbLog("XP8_BRWSR_004");
		recordVideo("XP8_BRWSR_004");
		launchBrowser();
		enterUrl(data.get("newUrl"));
		selectNewTab();
		enterUrlNewTab();
		selectHistoryPg();
		validateHistoryPage();
		deleteAllTabs();
		test.log(LogStatus.PASS, "Test case status is Passed");	
	}

	@Test(priority=5,dataProvider="XP8_Browser", dataProviderClass=DataProviders.class)
	public void XP8_BRWSR_005_Launchpage_From_History_pages(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========XP8_BRWSR_005============");
		clearRecentApps();
		startAdbLog("XP8_BRWSR_005");
		recordVideo("XP8_BRWSR_005");
		launchBrowser();
		enterUrl(data.get("newUrl"));
		selectHistoryPg();
		validateselectPagesite();
		deleteAllTabs();
		test.log(LogStatus.PASS, "Test case status is Passed");	
	}

	@Test(priority=6,dataProvider="XP8_Browser", dataProviderClass=DataProviders.class)
	public void XP8_BRWSR_006_CopyLink_from_History(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========XP8_BRWSR_006============");
		clearRecentApps();
		startAdbLog("XP8_BRWSR_006");
		recordVideo("XP8_BRWSR_006");
		launchBrowser();
		enterUrl(data.get("newUrl"));
		selectHistoryPg();
		copyTxtLink();
		selectIncognitoNewTab();
		enterUrlIncgnbar();
		test.log(LogStatus.PASS, "Test case status is Passed");	
	}
	
	@Test(priority=7,dataProvider="XP8_Browser", dataProviderClass=DataProviders.class)
	public void XP8_BRWSR_007_Remove_History(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========XP8_BRWSR_007============");
		clearRecentApps();
		startAdbLog("XP8_BRWSR_007");
		recordVideo("XP8_BRWSR_007");
		launchBrowser();
		enterUrl(data.get("newUrl"));
		selectHistoryPg();
		clearHistry();
		validateHistryClear();
		deleteAllTabs();
		test.log(LogStatus.PASS, "Test case status is Passed");	
	} 
	
	
	@Test(priority=8,dataProvider="XP8_Browser", dataProviderClass=DataProviders.class)
	public void XP8_BRWSR_008_Select_NewIncognito_with_DiffSites_and_Delete(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========XP8_BRWSR_008============");
		clearRecentApps();
		startAdbLog("XP8_BRWSR_008");
		recordVideo("XP8_BRWSR_008");
		launchBrowser();
		selectIncognitotab();
		enterUrl(data.get("newUrl"));
		selectIncognitotab();
		enterUrlNewTab();
		ValidateNewTabsOpen();
		deleteAllTabs();
		deleteAllIncognitoTabs();
		test.log(LogStatus.PASS, "Test case status is Passed");	
	} 
	
	@Test(priority=9,dataProvider="XP8_Browser", dataProviderClass=DataProviders.class)
	public void XP8_BRWSR_009_validateBrowse_In_Landscapemode(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========XP8_BRWSR_009============");
		clearRecentApps();
		startAdbLog("XP8_BRWSR_009");
		recordVideo("XP8_BRWSR_009");
		launchBrowser();
		BrowseLandscapemode();
		enterUrl(data.get("newUrl"));
		validateUrlEntered();
		BackPotraitmode();
		deleteAllTabs();
		test.log(LogStatus.PASS, "Test case status is Passed");		
	}
	
	@Test(priority=10,dataProvider="XP8_Browser", dataProviderClass=DataProviders.class)
	public void XP8_BRWSR_010_validate_Added_Bookmarks(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========XP8_BRWSR_010============");
		clearRecentApps();
		startAdbLog("XP8_BRWSR_010");
		recordVideo("XP8_BRWSR_010");
		launchBrowser();
		enterUrl(data.get("newUrl"));
		selectBookmarks();
		validateBookmarks();
		deleteAllTabs();
		test.log(LogStatus.PASS, "Test case status is Passed");		
	}
	
	@Test(priority=11,dataProvider="XP8_Browser", dataProviderClass=DataProviders.class)
	public void XP8_BRWSR_011_validate_Delete_Bookmarks(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========XP8_BRWSR_011============");
		clearRecentApps();
		startAdbLog("XP8_BRWSR_011");
		recordVideo("XP8_BRWSR_011");
		launchBrowser();
		enterUrl(data.get("newUrl"));
		deselectBookmarks();
		deleteAllTabs();
		test.log(LogStatus.PASS, "Test case status is Passed");		
	}
	
	@Test(priority=12,dataProvider="XP8_Browser", dataProviderClass=DataProviders.class)
	public void XP8_BRWSR_012_validate_RecentTabs(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========XP8_BRWSR_012============");
		clearRecentApps();
		startAdbLog("XP8_BRWSR_012");
		recordVideo("XP8_BRWSR_012");
		launchBrowser();
		enterUrl(data.get("newUrl"));
		selectRecentTabs();
		validateRecentTab();
		deleteAllTabs();
		test.log(LogStatus.PASS, "Test case status is Passed");		
	}
	

	@Test(priority=13,dataProvider="XP8_Browser", dataProviderClass=DataProviders.class)
	public void XP8_BRWSR_013_validate_Search_History(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========XP8_BRWSR_013============");
		clearRecentApps();
		startAdbLog("XP8_BRWSR_013");
		recordVideo("XP8_BRWSR_013");
		launchBrowser();
		enterUrl(data.get("newUrl"));
		selectHistoryPg();
		validateHistorySearch();
		deleteAllTabs();
		test.log(LogStatus.PASS, "Test case status is Passed");		
	}
	
	@Test(priority=14,dataProvider="XP8_Browser", dataProviderClass=DataProviders.class)
	public void XP8_BRWSR_014_validate_RequestDesktopsite(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========XP8_BRWSR_014============");
		clearRecentApps();
		startAdbLog("XP8_BRWSR_014");
		recordVideo("XP8_BRWSR_014");
		launchBrowser();
		selectReqDesktpSite();
		enterUrl(data.get("newUrl"));
		validateReqDesktopSiteEnabled();
		test.log(LogStatus.PASS, "Test case status is Passed");		
	}
	

	@Test(priority=15,dataProvider="XP8_Browser", dataProviderClass=DataProviders.class)
	public void XP8_BRWSR_015_validate_Add_to_HomeScreen(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========XP8_BRWSR_015============");
		clearRecentApps();
		startAdbLog("XP8_BRWSR_015");
		recordVideo("XP8_BRWSR_015");
		launchBrowser();
		enterUrl(data.get("newUrl"));
		selectAddtoHomeScreen();
		validateAddtoHomeScreen();
		test.log(LogStatus.PASS, "Test case status is Passed");		
	}
	
	@Test(priority=16,dataProvider="XP8_Browser", dataProviderClass=DataProviders.class)
	public void XP8_BRWSR_016_validate_Find_In_Page(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========XP8_BRWSR_016============");
		clearRecentApps();
		startAdbLog("XP8_BRWSR_016");
		recordVideo("XP8_BRWSR_016");
		launchBrowser();
		enterUrl(data.get("newUrl"));
		selectFindinPage();
		validateFindinPage();
		test.log(LogStatus.PASS, "Test case status is Passed");		
	}
	
	
	@Test(priority=17,dataProvider="XP8_Browser", dataProviderClass=DataProviders.class)
	public void XP8_BRWSR_017_validate_Disable_Cookies(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========XP8_BRWSR_017============");
		clearRecentApps();
		startAdbLog("XP8_BRWSR_017");
		recordVideo("XP8_BRWSR_017");
		launchBrowser();
		enterUrl(data.get("newUrl"));
		selectSettings();
		validateCookiesDisabled();
		test.log(LogStatus.PASS, "Test case status is Passed");		
	}
	
	@Test(priority=18,dataProvider="XP8_Browser", dataProviderClass=DataProviders.class)
	public void XP8_BRWSR_018_validate_Change_SearchEngine(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========XP8_BRWSR_018============");
		clearRecentApps();
		startAdbLog("XP8_BRWSR_018");
		recordVideo("XP8_BRWSR_018");
		launchBrowser();
		enterUrl(data.get("newUrl"));
		changeSearchEngine();
		validateSearchEngChanged(data.get("summaryText"));
		deleteAllTabs();
		test.log(LogStatus.PASS, "Test case status is Passed");		
	}

	@Test(priority=19,dataProvider="XP8_Browser", dataProviderClass=DataProviders.class)
	public void XP8_BRWSR_019_validate_Disable_Homepage(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========XP8_BRWSR_019============");
		clearRecentApps();
		startAdbLog("XP8_BRWSR_019");
		recordVideo("XP8_BRWSR_019");
		launchBrowser();
		enterUrl(data.get("newUrl"));
		disableHomepage();
		deleteAllTabs();
		validatediabledHomepage();
		test.log(LogStatus.PASS, "Test case status is Passed");		
	}
	
	@Test(priority=20,dataProvider="XP8_Browser", dataProviderClass=DataProviders.class)
	public void XP8_BRWSR_020_validate_Edit_Homepage_Open(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========XP8_BRWSR_020============");
		clearRecentApps();
		startAdbLog("XP8_BRWSR_020");
		recordVideo("XP8_BRWSR_020");
		launchBrowser();
		enterUrl(data.get("newUrl"));
		editHomepageOpen(data.get("newUrl2"));
		deleteAllTabs();
		launchBrowser();
		validateEditHomePageOpn() ;
		editHomepageOpen(data.get("newUrl3"));
		test.log(LogStatus.PASS, "Test case status is Passed");		
	}
}
*/