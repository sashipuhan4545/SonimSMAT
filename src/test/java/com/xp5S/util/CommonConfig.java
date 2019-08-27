package com.xp5S.util;

import java.awt.AWTException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.RoundingMode;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.text.DecimalFormat;
import java.util.Properties;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.text.WordUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.android.xml.AndroidManifest;
import com.graphics.gui.JsonFileReaderAndWriter;

import application.AllQA;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.remote.MobileCapabilityType;


public class CommonConfig extends appiumService {
	public static AndroidDriver driver;
	public FileInputStream inputStream;
	public Properties properties;
	public static AndroidDriver<AndroidElement> aDriver;
	public static int COMMAND_TIMEOUT=100;
	public static String CALL_MODULE="";
	private static final String LOG_PATH = "src/test/resources/properties/log4j.properties";
	public static Logger APP_LOGS = null;
	private static String deviceId;
	public static DesiredCapabilities capabilities;



	@Parameters({ "deviceName_","platformVersion_", "URL_","App_Package_Name_","App_Activity_Name_"})
	@BeforeTest(alwaysRun=true)
	public static void setUp(String deviceName_,String platformVersion_,String URL_,String App_Package_Name_,String App_Activity_Name_) throws InterruptedException, FileNotFoundException, IOException, ParseException{


		try {


			if(CALL_MODULE.equals("MT-Call")) {

				System.out.println("REFERENCE");
				deviceId=JsonFileReaderAndWriter.ReadRefDeviceId(); 
			}else {

				System.out.println("PRIMARY");

				deviceId=JsonFileReaderAndWriter.primaryDevIdReader(); 
			}

			capabilities = new DesiredCapabilities();
			capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, deviceName_);
			capabilities.setCapability(MobileCapabilityType.UDID, deviceId);
			//	capabilities.setCapability("automationName", "UiAutomator2");
			//capabilities.setCapability("orientation","PORTRAIT");
			capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, platformVersion_);
			capabilities.setCapability("autoGrantPermissions", true);
			capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, COMMAND_TIMEOUT);
			//	capabilities.setCapability("autoGrantPermissions", true);
			capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
			capabilities.setCapability("appPackage", App_Package_Name_);
			capabilities.setCapability("appActivity", App_Activity_Name_);






			//aDriver = new AndroidDriver<AndroidElement>(new URL("http://"+URL_), capabilities);
			aDriver = new AndroidDriver<AndroidElement>(appiumServerURL, capabilities);

			aDriver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);




		} catch (Exception e) {

			e.printStackTrace();
		}

	}	

	public void loggerProperties() {
		APP_LOGS=Logger.getLogger(this.getClass().getName());
		PropertyConfigurator.configure(LOG_PATH);
	}


	public Properties loadDriverAndProperties() throws FileNotFoundException, MalformedURLException, InterruptedException, AWTException {
		loggerProperties();
		return properties;
	}

	public static void cleanUp() {

		try {

			if (getDriver() != null) {

				getDriver().quit();

			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static AndroidDriver getDriver() {
		return aDriver;
	}




}
