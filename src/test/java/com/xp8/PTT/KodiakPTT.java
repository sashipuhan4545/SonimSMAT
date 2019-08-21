package com.xp8.PTT;

import java.awt.AWTException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.util.Properties;
import java.util.Timer;

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
import com.xp8.util.BaseUtil;
import com.xp8.util.ExcelConstants;
import com.xp8.util.ExcelReader;
import com.xp8.util.Locators_BaseUtil;
import com.xp8.util.Locators_CallSetting;

import application.AllQA;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidKeyCode;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class KodiakPTT extends BaseUtil{

	public ExcelReader excel;
	Properties properties;
	public static ExtentTestInterruptedException testexception;
	public Timer timer;


	@BeforeSuite
	public void beforeSuite() throws FileNotFoundException, InterruptedException, IOException, ParseException  
	{

		extent = new ExtentReports("src/test/resources/extentreport/XP8_KodiakPTT_Call.html", true); //Provide Desired Report Directory Location and Name
		extent.loadConfig(new File("src/test/resources/StorageFile/ReportsConfig.xml"));
		extent.addSystemInfo("Build #",JsonFileReaderAndWriter.primaryDevFirmwareReader())
		.addSystemInfo("Product",JsonFileReaderAndWriter.primaryDevModelReader())
		.addSystemInfo("Operator", JsonFileReaderAndWriter.primaryDevOperatorReader());			


	}



	@BeforeMethod()
	public  void beforeMethod(Method method) 
	{
		test = extent.startTest( (this.getClass().getSimpleName() +" :: "+  method.getName()),method.getName()); //Test Case Start Here

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

		Locators_BaseUtil loc1 = new Locators_BaseUtil(aDriver);	
		PageFactory.initElements(new AppiumFieldDecorator(aDriver),loc1);
		excel=new ExcelReader(ExcelConstants.XP5S_XL_PATH);	

	}



	@Test	
	public void privatePTTCall() throws InterruptedException {

		try {

			SoftAssert sa=new SoftAssert();

			clearRecentApps();

			if(JsonFileReaderAndWriter.primaryDevFirmwareReader().contains("-11")) {

				scrollToText("Push-to-Talk"); 

			}
			
			customWait(2000);

			for(int i=0;i<=10;i++) {
				
				System.out.println("1111111111111111111111");

				customWait(3000);
				if(isElementExist(Locators_BaseUtil.PTT_BackBtn) || isElementExist(Locators_BaseUtil.PTT_Back)) {
					clickBtn(multi_Loc_Strategy(Locators_BaseUtil.PTT_Back, Locators_BaseUtil.PTT_BackBtn, null, null, null, 0, 72));
				}


				customWait(3000);
				clickBtn(Locators_BaseUtil.PTT_contactlist);
				/*TouchAction tap=new TouchAction(aDriver);
				tap.tap(530, 361).perform();*/

				customWait(4000);
				//clickBtn(Locators_BaseUtil.PTT_onlineContact);
				clickBtn(multi_Loc_Strategy(Locators_BaseUtil.PTT_onlineContact, Locators_BaseUtil.PTT_onlineContact1, null, null, null, 42, 561));
				minWait();
				makeCall();
				minWait();

				if(!isElementExist(Locators_BaseUtil.PTT_callendcon) || isElementExist(Locators_BaseUtil.PTT_OK)) {

					System.out.println("<--------------FAIL----------------------->");

					clickBtn(Locators_BaseUtil.PTT_OK);
					customWait(3000);
					clickBtn(Locators_BaseUtil.PTT_BackBtn);
					AllQA.NUM_OF_CALL_ITER= "\n"+"PTT CALL FAILED:"+i;
					AllQA.CALL_COUNT=i;
					
					test.log(LogStatus.INFO,"PTT CALL FAILED: "+i);

					test.log(LogStatus.INFO, "Error due to contact you are calling is busy/Unavailable. Please try again later :"+i);
					test.log(LogStatus.ERROR, "");
					sa.fail();

				}else {
					System.out.println("<------------------------PASS------------------>");
					

					sa.assertTrue(true, "");
					AllQA.NUM_OF_CALL_ITER= "\n"+"PTT CALL :"+i;
					AllQA.CALL_COUNT=i;
					
					test.log(LogStatus.INFO,"PTT CALL : "+i);

					

				}

				Runtime.getRuntime().exec("adb -s "+JsonFileReaderAndWriter.ReadRefDeviceId()+" shell input swipe 535 1098 560 1080 15000");	

				customWait(15000);
				clickBtn(Locators_BaseUtil.PTT_callendcon);



			}




			test.log(LogStatus.PASS, "TestCase is Pass");
			sa.assertAll();

		} catch (Exception e) {
			e.printStackTrace();

			privatePTTCall();
		}


	}


	public void makeCall() {



		TouchAction t=new TouchAction(aDriver);
		t.longPress(Locators_BaseUtil.PTT_flooricon, 15000).release().perform();


	}



















}
