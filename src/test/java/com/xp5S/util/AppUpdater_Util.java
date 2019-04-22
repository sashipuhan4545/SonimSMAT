package com.xp5S.util;

import java.io.IOException;


import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.AndroidKeyCode;

public class AppUpdater_Util extends BaseUtil{

	public boolean check  = false;
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


	public void launch_application(WebElement app) throws InterruptedException{
		/*
		 * Launches sonim scout and navigates to supports tab..
		 */
		try {
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_HOME);
			minWait();
			clickBtn(app);
			APP_LOGS.info("Application launched succesfully");
			minWait();
		} catch (NoSuchElementException e) {
			APP_LOGS.info("Error: Application not found.");
			e.printStackTrace();
		}
	}

	public void validate_appUpdater_Presence() throws InterruptedException {
		SoftAssert sf = new SoftAssert();
		try {
			minWait();
			if(isElementExist(Locators_AppUpdater.appUpdater_app)) {
				APP_LOGS.info("App updater application is present");
				check=true;
				sf.assertTrue(check, "Validatio is Pass");
			} else {
				APP_LOGS.info("App updater application is not present");
				sf.fail();
			}
		} catch (NoSuchElementException e) {
			APP_LOGS.info("No element found");
			e.printStackTrace();
			sf.fail();
		}
		sf.assertAll();
	}

	public void launchAppUpdater() throws InterruptedException {

		/*
		 * Launches scout application : Warranty Registration
		 */
		try {
			clickBtn(Locators_AppUpdater.SetUp_Scout);
			minWait();
			aDriver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().className(\"android.widget.ScrollView\")).scrollIntoView(text(\"App Updater\"))").click();
			minWait();
			APP_LOGS.info("Launched App Updater application");
		} catch (NoSuchElementException e) {
			APP_LOGS.info("No element found");
			e.printStackTrace();
		}
	}

	public void validate_AppUpdaterHomeScreen() throws InterruptedException{
		SoftAssert sf = new SoftAssert();
		try {
			minWait();
			if(isElementExist(Locators_AppUpdater.appUpdaterTitle)){
				APP_LOGS.info("App updater home screen is displayed succesfully.");
				check=true;
				sf.assertTrue(check,"Validation is Pass");
			}else{
				APP_LOGS.info("App updater home screen is displayed succesfully.");
				sf.fail();
			}
		} catch (NoSuchElementException e) {
			APP_LOGS.info("No element found");
			e.printStackTrace();
			sf.fail();
		}
		sf.assertAll();		
	}
	
	public void clear_AllAppsUptoDate() {
		if (isElementExist(Locators_AppUpdater.allAppsUptoDateText)) {
			clickBtn(Locators_AppUpdater.uptoDateScreen_OkBtn);
		}
	}

	public void click_checkForUpdateBtn() throws InterruptedException{
		try {
			minWait();
			clickBtn(Locators_AppUpdater.checkForUpdateBtn);		
			customWait(2000);
		} catch (org.openqa.selenium.NoSuchElementException e) {
			APP_LOGS.info("No element found");
			e.printStackTrace();
		}
	}

	public void pressCenter() throws InterruptedException{
		minWait();
		aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
		customWait(3000);
	}

	public void click_HomeSoftKey() throws InterruptedException{
		// clicks on home soft key present in app updater homescreen after.
		try {
			customWait(2000);
			while(true) {
				if(isElementExist(Locators_AppUpdater.appUpdaterAlert)){
					APP_LOGS.info("Confirmation alert is displayed succesfully.");
					aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_RIGHT);
					minWait();
					aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
					minWait();
					break;
				}else{
					waitUntilElementclickable(Locators_AppUpdater.appUpdaterAlert);
					minWait();
				}
			}	
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
			minWait();
			APP_LOGS.info("Clicked on HOmekey succesfully.");
			customWait(15000);	
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
			minWait();
		} catch (org.openqa.selenium.NoSuchElementException e) {
			APP_LOGS.info("No element found");
			e.printStackTrace();
		}
	}

	public void validate_checkForUpdates_btn() throws InterruptedException{
		/*
		 * Validates presence of check for updates button
		 */
		SoftAssert sf = new SoftAssert();
		try {
			minWait();

			if(isElementExist(Locators_AppUpdater.checkForUpdateBtn)){
				APP_LOGS.info("Check for update button is displayed in App updater homescreen.");
				check=true;
				sf.assertTrue(check, "Validation is Pass");
			}else{
				APP_LOGS.info("Check for update button is not displayed in App updater homescreen.");
				sf.fail();
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {
			APP_LOGS.info("No element found");
			e.printStackTrace();
			sf.fail();
		}
		sf.assertAll();
	}

	public void validate_UpdateConfirmationDialog() throws InterruptedException{

		/*
		 * Validates display of update alert.
		 */
		SoftAssert sf = new SoftAssert();
		WebDriverWait wait = new WebDriverWait(aDriver, 30);
		try {
			waitUntilElementclickable(Locators_AppUpdater.appUpdaterAlert);
			minWait();
			if(isElementExist(Locators_AppUpdater.appUpdaterAlert)){
				APP_LOGS.info("App updater alert is displayed succesfully.");
				check=true;
				sf.assertTrue(check, "Validation is Pass");
			}else{
				APP_LOGS.info("App updater alert is not displayed.");
				sf.fail();
			}
			wait.until(ExpectedConditions.visibilityOf(Locators_AppUpdater.cancel_PopUp));
			minWait();
			clickBtn(Locators_AppUpdater.cancel_PopUp);
		} catch (org.openqa.selenium.NoSuchElementException e) {
			APP_LOGS.info("No element found");
			e.printStackTrace();
			sf.fail();
		}
		sf.assertAll();
	}

	public void validate_downloadProgress() throws InterruptedException{
		/*
		 * Validates the presence of downloading in progress screen.
		 */
		try {
			minWait();
			if(isElementExist(Locators_AppUpdater.downloadingProgressBar)){
				APP_LOGS.info("Downloading is in progress bar is displayed.");
				check = true;
				Assert.assertTrue(check);
			}else{
				APP_LOGS.info("Downloading is in progress bar is not displayed.");
				Assert.fail();
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {
			APP_LOGS.info("No element found");
			e.printStackTrace();
		}
	}

	public void validate_checkingForUpdateProgress() throws InterruptedException{
		/*
		 * Validates checking for checking for update progress bar.
		 */
		SoftAssert sf = new SoftAssert();
		WebDriverWait wait = new WebDriverWait(aDriver, 30);
		try {
			minWait();
			clickBtn(Locators_AppUpdater.checkForUpdateBtn);
			minWait();
			if(isElementExist(Locators_AppUpdater.checkingForUpdatesBar)){
				APP_LOGS.info("Checking for updates progress bar is displayed.");
				check = true;
				sf.assertTrue(check, "Validation is Pass");
			}else{
				APP_LOGS.info("Checking for updates progress bar is not displayed.");
				sf.fail();
			}
			wait.until(ExpectedConditions.visibilityOf(Locators_AppUpdater.cancel_PopUp));
			minWait();
			clickBtn(Locators_AppUpdater.cancel_PopUp);
		} catch (org.openqa.selenium.NoSuchElementException e) {
			APP_LOGS.info("No element found");
			e.printStackTrace();
			sf.fail();
		}
		sf.assertAll();
	}

	public void validate_installingUpdatesProgress() throws InterruptedException{
		/*
		 * Validates checking for update screen presence
		 */
		try {
			minWait();
			if(isElementExist(Locators_AppUpdater.installingUpdatesBar)){
				APP_LOGS.info("Installing updates progress bar is displayed.");
				check = true;
				Assert.assertTrue(check);
			}else{
				APP_LOGS.info("Installing updates progress bar is not displayed.");
				Assert.fail();
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {
			APP_LOGS.info("No element found");
			e.printStackTrace();
		}
	}

	public void validate_silentUpgrade() throws InterruptedException{
		/*
		 * Validates silent upgrade.
		 * Pre condition, create profile in sonimcloud with silent upgrade option.
		 */
		try {
			minWait();
			if(isElementExist(Locators_AppUpdater.downloadingProgressBar)){
				APP_LOGS.info("Downloading update with silent update is successful.");
				check = true;
				Assert.assertTrue(check);
			}else{
				APP_LOGS.info("Downloading update with silent update is not successful.");
				Assert.fail();
			} 
		}catch (org.openqa.selenium.NoSuchElementException e) {
			APP_LOGS.info("No element found");
			e.printStackTrace();
		}
	}

	public void checkForUpdates(String button) throws InterruptedException{
		/*
		 * Clicks on check for updates button and waits for the alert.
		 */
		try {
			minWait();
			clickBtn(Locators_AppUpdater.checkForUpdateBtn);
			waitUntilElementclickable(Locators_AppUpdater.appUpdaterAlert);
			minWait();
			if(button.equalsIgnoreCase("cancel")){
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
				minWait();
				APP_LOGS.info("Clicked on cancel button.");				
			}else{
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_RIGHT);
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
				minWait();
				APP_LOGS.info("Clicked on Ok or yes button succesfully.");		
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {
			APP_LOGS.info("No element found");
			e.printStackTrace();
		}

	}

	public void clearRecentApps_util() throws InterruptedException {
		/*
		 * Clears apps from recent apps screen.
		 */
		customWait(1000);
		aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_HOME);
		customWait(4000);
		try {
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_APP_SWITCH);
			customWait(2000);
			APP_LOGS.info("Pressed recent button sucessfully");

			for (int iter = 0; iter <= 24; iter++) {

				// if (isElementExist(Locators_SonimCare.recentApp)) {
				if (isElementExist(Locators_AppUpdater.recentApp)) {
					APP_LOGS.info("Recent Apps displayed sucessfully");
					aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_CLEAR);
					customWait(1000);
					APP_LOGS.info("Clearing recent APPS: " + iter);
					continue;
				} else {
					APP_LOGS.info("No recent apps are displayed");
					break;
				}
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {
			APP_LOGS.info("No element found");
			e.printStackTrace();
		}
	}

	public void uninstallUpdates(AndroidElement app) throws InterruptedException, IOException{
		/*
		 * Navigates to settings >> Apps >> Enters the string >> Uninstalls
		 */
		customWait(1000);
		Runtime.getRuntime().exec("adb shell am start -n com.android.settings/com.android.settings.Settings");
		customWait(2000);
		try {
			aDriver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().resourceId(\"com.android.settings:id/main_content\")).scrollIntoView(new UiSelector().text(\"Apps\"))").click();
			customWait(2000);

			while (true) {
				if (isElementExist(app)) {
					clickBtn(app);
					minWait();
					break;					
				} else {
					aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
					if (isElementExist(Locators_AppUpdater.updater_AllApps)||isElementExist(Locators_AppUpdater.unlock_AllApps)) {
						break;
					}
				} 
			}
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_MENU);
			minWait();
			clickBtn(Locators_AppUpdater.uninstall_Updates);
			minWait();
			clickBtn(Locators_AppUpdater.uptoDateScreen_OkBtn);
			minWait();
			
		} catch (org.openqa.selenium.NoSuchElementException e) {
			APP_LOGS.info("No element found");
			e.printStackTrace();
		}
	}

	public void uninstallAppFromSettings(String app) throws InterruptedException{
		/*
		 * Navigates to settings >> Apps >> Enters the string >> Uninstalls
		 */
		customWait(1000);
		aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_HOME);
		customWait(2000);
		try {
			clickBtn(Locators_AppUpdater.settingsIcon);
			minWait();
			APP_LOGS.info("Launched Settings icon sucessfully");          
			for (int iter = 1; iter <= 25; iter++) {
				if (isElementExist(Locators_AppUpdater.nativeSettings_apps)){
					APP_LOGS.info("Apps displayed sucessfully");
					clickBtn(Locators_AppUpdater.nativeSettings_apps);
					minWait();	
					enterTextToInputField(Locators_AppUpdater.apps_homescreen, app);
					minWait();
					aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
					minWait();
					aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
					minWait();
					aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_RIGHT);
					minWait();
					aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
					customWait(8000);
					aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
					customWait(4000);
					APP_LOGS.info("Application is uninstalled succesfully.");
					break;					
				} else {
					//APP_LOGS.info("Searching for programmable key: " + iter);
					aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
					continue;					
				}
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {
			APP_LOGS.info("No element found");
			e.printStackTrace();
		}
	}

	public void installApp() throws InterruptedException{
		// Acknowledges the confirmation and installs.
		// Later and Ok button should be displayed in confirmation dialog.
		try {
			WebDriverWait wait = new WebDriverWait(aDriver, 30);
			customWait(5000);
			for(int i=0;i<20;i++){
				if(isElementExist(Locators_AppUpdater.cancel_PopUp)){
					APP_LOGS.info("Confirmation alert is displayed succesfully.");
					aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_RIGHT);
					minWait();
					aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
					minWait();
					break;
				}else{
					wait.until(ExpectedConditions.visibilityOf(Locators_AppUpdater.cancel_PopUp));
					minWait();
					i++;
				}
			}	
		} catch (org.openqa.selenium.NoSuchElementException e) {
			APP_LOGS.info("No element found");
			e.printStackTrace();
		}		
	}

	public void appUpdaterSuccessAlert() throws InterruptedException{
		// Wait until it success alert is displayed.
		try {
			WebDriverWait wait = new WebDriverWait(aDriver, 30);
	
			wait.until(ExpectedConditions.visibilityOf(Locators_AppUpdater.OkBtn));
			minWait();
				if(isElementExist(Locators_AppUpdater.OkBtn)){
					APP_LOGS.info("Success alert is displayed succesfully.");
					minWait();
					aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
				}
		} catch (org.openqa.selenium.NoSuchElementException e) {
			APP_LOGS.info("No element found");
			e.printStackTrace();
		}
	}

	public void validate_succesfull_appInstallation(String app) throws InterruptedException{
		SoftAssert sf = new SoftAssert();
		customWait(1000);
		aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_HOME);
		customWait(2000);
		try {
			clickBtn(Locators_AppUpdater.settingsIcon);
			minWait();
			APP_LOGS.info("Launched Settings icon sucessfully");          
			for (int iter = 1; iter <= 25; iter++) {
				if (isElementExist(Locators_AppUpdater.nativeSettings_apps)){
					APP_LOGS.info("Apps displayed sucessfully");
					clickBtn(Locators_AppUpdater.nativeSettings_apps);
					minWait();	
					enterTextToInputField(Locators_AppUpdater.apps_homescreen, app);
					minWait();
					aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
					minWait();
					if(isElementExist(Locators_AppUpdater.appInfo_appTitle))
					{
						check=true;
						APP_LOGS.info("App info page of selected app is displayed.");
						sf.assertTrue(check, "Validation is Pass");
						break;
					}else{
						APP_LOGS.info("App info page of selected app is not displayed.");
						sf.fail();
						break;
					}
				} else {
					//APP_LOGS.info("Searching for programmable key: " + iter);
					aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
					continue;					
				}					
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {
			APP_LOGS.info("No element found");
			e.printStackTrace();
			sf.fail();
		}
		sf.assertAll();
	}

	public void validate_aboutScreen() throws InterruptedException{
		// Validates about screen of app updater.
		SoftAssert sf = new SoftAssert();
		try {
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_MENU);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
			minWait();
			//			clickBtn(Locators_AppUpdater.aboutBtn);
			minWait();
			if(isElementExist(Locators_AppUpdater.appUpdaterVersion)){
				String version = Locators_AppUpdater.appUpdaterVersion.getText();
				APP_LOGS.info("App updater version dialog is displayed");
				APP_LOGS.info("App updater version is: "+version);
				check=true;
				sf.assertTrue(check, "Validation is Pass");
			}else{
				APP_LOGS.info("App updater version dialog is not displayed");
				sf.fail();
			}

		} catch (org.openqa.selenium.NoSuchElementException e) {
			APP_LOGS.info("No element found");
			e.printStackTrace();
			sf.fail();
		}
		sf.assertAll();
	}	

	public void validate_lastChecked_screen() throws InterruptedException{
		// Validates last checked screen of app updater.

		try {
			minWait();
			if(isElementExist(Locators_AppUpdater.lastCheckedText)){
				String dateText = Locators_AppUpdater.lastCheckedText.getText();
				APP_LOGS.info("Last checked date is displayed.");
				APP_LOGS.info(dateText);
				check = true;
				Assert.assertTrue(check);
			}else{
				APP_LOGS.info("Last checked date is not displayed.");
				Assert.fail();
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {
			APP_LOGS.info("No element found");
			e.printStackTrace();
		}
	}

	public void validate_uptoDate_Screen() throws InterruptedException{
		// Validates upto date screen of app updater.
		SoftAssert sf = new SoftAssert();
		try {
			minWait();
			if(isElementExist(Locators_AppUpdater.allAppsUptoDateText)){
				APP_LOGS.info("Upto date element is displayed.");
				check = true;
				sf.assertTrue(check, "Validation is Pass");
				clickBtn(Locators_AppUpdater.uptoDateScreen_OkBtn);
			}else{
				APP_LOGS.info("Upto date element is not displayed.");
				sf.fail();
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {
			APP_LOGS.info("No element found");
			e.printStackTrace();
			sf.fail();
		}
		sf.assertAll();		
	}

	public void waitForLong() throws InterruptedException{
		customWait(5000);
	}


	public void enable_disable_Wifi() throws InterruptedException {
		// Disables wifi from native settings.
		// Precondition: Wifi should be enabled.
		customWait(1000);
		aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_HOME);
		customWait(2000);
		try {
			minWait();
			clickBtn(Locators_AppUpdater.settingsIcon);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
			minWait();
			clickBtn(Locators_AppUpdater.wifiBtn);
		} catch (org.openqa.selenium.NoSuchElementException e) {
			APP_LOGS.info("No element found");
			e.printStackTrace();
		}
	}
}
