package com.xp5S.util;

import java.io.IOException;
import java.util.NoSuchElementException;

import org.openqa.grid.common.exception.RemoteException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.AndroidKeyCode;

public class ProgrammableKey_Util extends BaseUtil {
	
	public boolean check = false;
	public boolean check1 = false;
	public boolean check2 = false;
	public boolean check3 = false;
	public boolean check4 = false;
	public boolean check5 = false;
	public boolean check6 = false;
	public boolean check7 = false;
	public boolean check8 = false;
	public boolean check9 = false;
	public boolean check10= false;
	//public static AndroidDriver<AndroidElement> aDriver;
	public static AndroidDriver<MobileElement> mdriver;
	
	public void validate_ProgrammableKey_in_NativeSettings() throws InterruptedException{
		/*
		 * Validates presence of programmable key in native settings.
		*/	
		customWait(1000);
		aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_HOME);
		customWait(4000);
        try {
        	clickBtn(Locators_ProgrammableKey.settingsIcon);
    		minWait();
        	 APP_LOGS.info("Launched Settings icon sucessfully");
        	
          
			for (int iter = 1; iter <= 19; iter++) {

				if (isElementExist(Locators_ProgrammableKey.programmableKey)) {
					APP_LOGS.info("Programmable key displayed sucessfully");
					minWait();	
					Assert.assertTrue(true);
					break;					
				} else {
					//APP_LOGS.info("Searching for programmable key: " + iter);
					aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
					continue;					
				}
			}

        } catch (NoSuchElementException e) {
        	APP_LOGS.info("Programmable key is not displayed.");
			minWait();	
			Assert.fail();
        }
	}
	
	public void scroll_to_ProgrammableKey() throws InterruptedException{
		/*
		 * Scrolls and selects programmable key.
		*/		
		customWait(1000);
		aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_HOME);
		customWait(2500);
        try {
        	clickBtn(Locators_ProgrammableKey.settingsIcon);
    		minWait();
        	 APP_LOGS.info("Launched Settings icon sucessfully");    
        	 
			for (int iter = 1; iter <= 25; iter++) {
				if (isElementExist(Locators_ProgrammableKey.programmableKey)){
					APP_LOGS.info("Programmable key displayed sucessfully");
					clickBtn(Locators_ProgrammableKey.programmableKey);
					minWait();					
					break;					
				} else {
					//APP_LOGS.info("Searching for programmable key: " + iter);
					aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
					continue;					
				}
			}
        } catch (NoSuchElementException e) {
            e.printStackTrace();
        }
	}
	
	public void validate_ProgrammableKeySummaryText() throws InterruptedException{
		/*
		 * Retrieves programmable key summary text.
		*/
		minWait();
		try {
			if(isElementExist(Locators_ProgrammableKey.summaryText)){
				APP_LOGS.info("Programmable key summary is displayed successfully");	
				String getSummaryText = Locators_ProgrammableKey.summaryText.getText();
				APP_LOGS.info("App assigned to programmable key is: "+getSummaryText);
				minWait();
				check=true;
				softAssert_true(check, "Pass");	
			}else{
				APP_LOGS.info("Programmable key summary is not displayed");
				softAssert_false();
			}								
			
		} catch (NoSuchElementException e) {			
			e.printStackTrace();
		}
	}
	
	public void validate_ProgrammableKeySummaryText_of_ParticularApp(String summaryText) throws InterruptedException{
		/*
		 * Retrieves programmable key summary text of a particular application.
		*/
		minWait();
		try {
			String getSummaryText = aDriver.findElement(By.xpath("//android.widget.TextView[@text='"+summaryText+"']")).getText();
			if(getSummaryText.equalsIgnoreCase(getSummaryText)){
				APP_LOGS.info("App assigned to programmable key is: "+getSummaryText);
				minWait();
				check=true;
				softAssert_true(check, "Pass");	
			}else{
				APP_LOGS.info("Incorrect programmable key is displayed:"+getSummaryText);
				softAssert_false();
			}								
			
		} catch (NoSuchElementException e) {			
			e.printStackTrace();
		}
	}
	
	
	
	public void press_PTT_Key() throws InterruptedException, IOException{
		/*
		 * Long press event of PTT key is fired.
		*/
		Runtime.getRuntime().exec("cmd /C \"adb shell input keyevent --longpress KEYCODE_PTT");
		customWait(5000);
	}
	
	public void scrollToNoApplication() throws InterruptedException{
		/*
		 * scrolls to top of the PTT key app list
		*/
		minWait();
		aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
		customWait(2500);
		APP_LOGS.info("Navigated to select PTT KEY app screen sucessfully");
		for (int i = 1; i <= 34; i++) {
			if (isElementExist(Locators_ProgrammableKey.no_Application_app)) {
				String getAppName = Locators_ProgrammableKey.no_Application_app.getText();
				APP_LOGS.info("No Application app is found.");
				minWait();					
				break;					
			} else {
				//APP_LOGS.info("Searching for No application app: " + i);
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_UP);
				continue;					
			}
		}
	}
	
	public void assign_app_to_ProgrammableKey(WebElement app) throws InterruptedException{
		/*
		 * Assign app to programmable key.
		 * Send webelement to be selected as parameter
		 */
		try {         	
			for (int iter = 1; iter <= 34; iter++) {
				if (isElementExist(app)) {
					String getAppName = app.getText();
					APP_LOGS.info("Searched application is found sucessfully :: "+getAppName);
					minWait();
					clickBtn(app);
					APP_LOGS.info(getAppName+" : Application is assigned to programmableKey");
					minWait();					
					break;					
				} else {
					//APP_LOGS.info("Searching for application to assign: " + iter);
					aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
					continue;					
				}
			}
        } catch (NoSuchElementException e) {
            e.printStackTrace();
        }		
	}
	
	public void validate_launchedApplication(WebElement app, String summaryText) throws InterruptedException, IOException {
		/*
		 * Validates application launched with respect to the application
		 * assigned to PTT KEY.
		 */
		try {
			minWait();
			String getSummaryText = aDriver.
			findElement(By.xpath("//android.widget.TextView[@text='"+summaryText+"']")).getText();
			if (getSummaryText.equalsIgnoreCase(getSummaryText)) {
				APP_LOGS.info("App assigned to programmable key is: "+getSummaryText);
				check1 = true;
				minWait();
			} else {
				APP_LOGS.info("Incorrect app summary text is displayed: "+getSummaryText);
			}

			press_PTT_Key();
			
			if (isElementExist(app)) {
				check2 = true;
				APP_LOGS.info("Assinged application launched succesfully upon pressing programmable key.");
			} else {
				APP_LOGS.info("Assinged application is not launched");
			}
			if(check1 && check2){
				check=true;
				APP_LOGS.info("Test case passed");
				softAssert_true(check, "Pass");
			}else{
				APP_LOGS.info("Test case failed");
				Assert.fail();
			}
		} catch (NoSuchElementException e) {			
			e.printStackTrace();
		}
	}
	
	
	
	
	

}
