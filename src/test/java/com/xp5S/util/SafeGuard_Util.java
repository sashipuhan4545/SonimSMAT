package com.xp5S.util;

import java.io.IOException;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.testng.asserts.SoftAssert;

import com.relevantcodes.extentreports.LogStatus;

import io.appium.java_client.android.AndroidKeyCode;


public class SafeGuard_Util extends BaseUtil {
	
	public boolean check = false;
	boolean ch1=false;
	boolean ch2=false;
	boolean ch3=false;
	boolean ch4=false;
	boolean ch5=false;
	boolean ch6=false;
	boolean ch7=false;
	boolean ch8=false;
	boolean ch9=false;
	boolean ch10=false;
	boolean ch11=false;

	public void app_Presence() throws InterruptedException {

		//This method is used to validate the presence of scout app in Xp5 device and validate the result
	}

	public void sf_app_Presence_Validation() throws InterruptedException, IOException {

		SoftAssert SA1=new SoftAssert();
		try {
			minWait();
			Runtime.getRuntime().exec("adb shell am start -n com.sonim.scout/com.sonim.scout.activities.MainActivity");
			minWait();
			APP_LOGS.info("Inside Safeguard Settings screen");
			minWait();
			clickBtn(Locators_Safeguard.SG_SetUp_String);
			APP_LOGS.info("Clicked on SetUp Tab successfully");
			minWait();	
			minWait();	
			aDriver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().className(\"android.widget.LinearLayout\")).scrollIntoView(new UiSelector().text(\"SafeGuard\"))");
			String fetch_sf_text=Locators_Safeguard.SG_String.getText();
			APP_LOGS.info("Fetching safeguard string text is " +fetch_sf_text);
			SA1.assertEquals(fetch_sf_text, "SafeGuard");
		}
		catch(org.openqa.selenium.NoSuchElementException e) {
			APP_LOGS.info("Element not Found");
			e.printStackTrace();
			SA1.fail();
		}
		SA1.assertAll();
	}

	public void sf_app_Verify_Defualt_state() throws InterruptedException, IOException {
		//This method is used to check the default state of SF 

		SoftAssert SA2=new SoftAssert();
		minWait();
		try {
			if(sf_app_Check_Activation_Off_On().equals("OFF")) {
				check=true;
				APP_LOGS.info("Activation status is OFF");
				SA2.assertTrue(check, "Deafault Activation status is OFF after flasing the device");
			} else {
				APP_LOGS.info("Activation status is ON");
				SA2.fail();
			}			
		} catch(org.openqa.selenium.NoSuchElementException e) {
			APP_LOGS.info("Element not Found");
			e.printStackTrace();
			SA2.fail();
		}
		SA2.assertAll();
	}


	//This Method will open the safeguard App by navigating.
	public void navigateToSafeGuard() throws InterruptedException, IOException {
		try {
			minWait();
			//aDriver.launchApp();
			Runtime.getRuntime().exec("adb shell am start -n com.sonim.scout/com.sonim.scout.activities.MainActivity");
			minWait();
			APP_LOGS.info("Inside Safeguard Settings screen");
			clickBtn(Locators_Safeguard.SG_SetUp_String);
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
			minWait();	
			//aDriver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().className(\"android.widget.LinearLayout\")).scrollIntoView(new UiSelector().text(\"SafeGuard\"))");
			clickBtn(Locators_Safeguard.SG_Safe_Guard_String);
			
		} catch(org.openqa.selenium.NoSuchElementException e) {
			APP_LOGS.info("Element not Found");
			e.printStackTrace();
		}
	}

	public String sf_app_Check_Activation_Off_On() throws InterruptedException, IOException {
		//This method is used to check wheather activation is ON or OFF .
		minWait();
		String status = null;

		navigateToSafeGuard();

		if(Locators_Safeguard.SG_toggle_btn.getText().equals("OFF")) {
			status=Locators_Safeguard.SG_toggle_btn.getText();
			APP_LOGS.info("Checking Activation status for OFF" +status);
		}

		else if(Locators_Safeguard.SG_toggle_btn.getText().equals("ON")){
			status=Locators_Safeguard.SG_toggle_btn.getText();
			APP_LOGS.info("Checking Activation status for ON" +status);
		}

		return status;
	}

	//
	public String special_case() throws InterruptedException, IOException {
		//This method is used to check wheather activation is ON or OFF .
		minWait();
		String status = null;
		
		try {
			minWait();
			Runtime.getRuntime().exec("adb shell am start -n com.sonim.scout/com.sonim.scout.activities.MainActivity");
			minWait();
			try {
				aDriver.findElementByAndroidUIAutomator("new UiSelector().resourceId(\"com.sonim.safeguard:id/editText\")").sendKeys("1234");
				minWait();		
				aDriver.pressKeyCode(AndroidKeyCode.ENTER);
				minWait();
			} catch (Exception e) {
				e.printStackTrace();
			}
			APP_LOGS.info("Inside Safeguard Settings screen");
			clickBtn(Locators_Safeguard.SG_SetUp_String);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
			clickBtn(Locators_Safeguard.SG_Safe_Guard_String);

				if(Locators_Safeguard.SG_toggle_btn.getText().equals("OFF")) {
					status=Locators_Safeguard.SG_toggle_btn.getText();
					APP_LOGS.info("Checking Activation status for OFF " +status);
				}

				else if(Locators_Safeguard.SG_toggle_btn.getText().equals("ON")){
					status=Locators_Safeguard.SG_toggle_btn.getText();
					APP_LOGS.info("Checking Activation status for ON " +status);
				}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return status;
	}

	//This method will enter Pin the activation screen
	public void enter_Pin() throws InterruptedException {
		try {
			minWait();
			aDriver.findElementByAndroidUIAutomator("UiSelector().resourceId(\"com.sonim.safeguard:id/dialog_edit_text\")").sendKeys("1234");
			APP_LOGS.info("PIN has been entered successfully");
			minWait();
	
				if(Locators_Safeguard.SG_OK_Btn_for_Pin.isEnabled()) {
					customWait(1000);
					clickBtn(Locators_Safeguard.SG_OK_Btn_for_Pin);
					APP_LOGS.info(" Enter pin and clicked ok");
				} else {
					APP_LOGS.info("Pin Ok button is not enabled");
					softAssert_false();
				}
		}
		catch(org.openqa.selenium.NoSuchElementException e) {
			APP_LOGS.info("Element not Found");
			e.printStackTrace();
		}
	}
	
	//This Method will turn OFF/turn On the SF
	public void turn_Off_On_SG() throws InterruptedException {
		try {
			minWait();
			clickBtn(Locators_Safeguard.SG_toggle_btn);
			customWait(2000);
			enter_Pin();
		}
		catch(org.openqa.selenium.NoSuchElementException e) {
			APP_LOGS.info("Element not Found");
			e.printStackTrace();
		}
	}




	//This methos is used to verify wheather Version and Help options are enabled or not .
	public void verify_Version_Help_In_Default_State() throws InterruptedException, IOException {

		SoftAssert SA3=new SoftAssert();

		try {
			//navigateToSafeGuard();
			minWait();
			if(sf_app_Check_Activation_Off_On().equals("OFF")) {
				
				for(int i=0;i<=4;i++) {
					aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
					customWait(1000);
				}
				APP_LOGS.info("Scrolled Till Help Text and Version");
				minWait();
	
				if(Locators_Safeguard.SG_Version.isEnabled() && Locators_Safeguard.SG_Help.isEnabled()){
					check=true;
					APP_LOGS.info("Version and Help text are enabled when safe guard is in OFF mode");
					SA3.assertTrue(check, "Version and Help is Present");
				}		
				else {
					APP_LOGS.info("Version and Help text are not enabled when safe guard is in OFF mode");
					SA3.fail();
				}
			}else {
				APP_LOGS.info("Executing Else Part for Verifying version and Help TC");
	
				turn_Off_On_SG();
	
					for(int i=0;i<=4;i++) {
						aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
						customWait(1000);
					}			
	
				APP_LOGS.info("Execting in Else Part :Scrolled Till Help Text and Version");
				minWait();
	
				if(Locators_Safeguard.SG_Version.isEnabled() && Locators_Safeguard.SG_Help.isEnabled()) {
					check=true;
					APP_LOGS.info("Else Part : Version and Help text are enabled when safe guard is in OFF mode");
					SA3.assertTrue(check, "Version and Help is Present");	
				}else {
					APP_LOGS.info("Version and Help text are not enabled when safe guard is in OFF mode");
					SA3.fail();
				}
			}				
		}
		catch(org.openqa.selenium.NoSuchElementException e) {
			APP_LOGS.info("Element not Found");
			e.printStackTrace();
			SA3.fail();
		}
		SA3.assertAll();
	}

	//This method will navigate to Help option and check the default Pin

	public void navigate_To_Help_Fetch_Pin_Check_Version() throws InterruptedException, IOException {

		SoftAssert SA4=new SoftAssert();

		boolean ch1=true;
		boolean ch2=true;
		try {
			minWait();
			navigateToSafeGuard();
			minWait();
			for(int i=0;i<=4;i++) {
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
				customWait(1000);
			}
			//aDriver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().className(\"android.widget.LinearLayout\")).scrollIntoView(new UiSelector().text(\"Help\"))");
	
			clickBtn(Locators_Safeguard.SG_Help);
			minWait();
			if(Locators_Safeguard.SG_Help_text.getText().contains("Default PIN is 1234")){
				ch1=true;
			}
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.BACK);
			minWait();
			if(Locators_Safeguard.SG_Version_summary_text.getText().equals("")) {
				ch2=true;
			}

			if((ch1) && (ch2)) {
				check=true;
				APP_LOGS.info("TC_Fetching Help Text");
				SA4.assertTrue(check, "");
			}
			else {
				SA4.fail();
			}	
		}
		catch(org.openqa.selenium.NoSuchElementException e) {
			APP_LOGS.info("Element not Found");
			e.printStackTrace();
			SA4.fail();
		}
		SA4.assertAll();
	}

	//This method is used to check the deafult state of APPS,Features,Pintime out should be accessible 
	public void verify_Default_States_Of_All_Options_SG() throws InterruptedException, IOException {

		SoftAssert SA5=new SoftAssert();
		try {
				if(sf_app_Check_Activation_Off_On().equals("OFF")) {
		
					turn_Off_On_SG();
					SA5.assertTrue(Locators_Safeguard.SG_Applications_String.isEnabled(),"Application is enabed");
					APP_LOGS.info("Checking the Applications is enabled or not :" +Locators_Safeguard.SG_Applications_String.isEnabled());
					SA5.assertTrue(Locators_Safeguard.SG_Features_String.isEnabled(),"Feature is enabled");
					APP_LOGS.info("Checking the Feature is enabled or not :" +Locators_Safeguard.SG_Features_String.isEnabled());
					SA5.assertTrue(Locators_Safeguard.SG_Change_Pin_String.isEnabled(),"Pin is enabled");
					APP_LOGS.info("Checking the Pin is enabled or not :" +Locators_Safeguard.SG_Change_Pin_String.isEnabled());		
					minWait();	
				for(int i=0;i<=5;i++) {
						aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
						customWait(1000);
					}		
					minWait();
					SA5.assertTrue(Locators_Safeguard.SG_Version_String.isEnabled(), "Version is enabled");
					APP_LOGS.info("Checking the Version is enabled or not :" +Locators_Safeguard.SG_Version_String.isEnabled());
					SA5.assertTrue(Locators_Safeguard.SG_Help_String.isEnabled(), "Help is enabled");
					APP_LOGS.info("Checking the Help is enabled or not :" +Locators_Safeguard.SG_Help_String.isEnabled());
		
				}
				else {
		
					SA5.assertTrue(Locators_Safeguard.SG_Applications_String.isEnabled(),"Application is enabed");
					APP_LOGS.info("Checking the Applications is enabled or not :" +Locators_Safeguard.SG_Applications_String.isEnabled());
					SA5.assertTrue(Locators_Safeguard.SG_Features_String.isEnabled(),"Feature is enabled");
					APP_LOGS.info("Checking the Feature is enabled or not :" +Locators_Safeguard.SG_Features_String.isEnabled());
					SA5.assertTrue(Locators_Safeguard.SG_Change_Pin_String.isEnabled(),"Pin is enabled");
					APP_LOGS.info("Checking the Pin is enabled or not :" +Locators_Safeguard.SG_Change_Pin_String.isEnabled());
		
					minWait();	
					for(int i=0;i<=5;i++) {
						aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
						customWait(1000);
					}
		
					minWait();		
					SA5.assertTrue(Locators_Safeguard.SG_Version_String.isEnabled(), "Version is enabled");
					APP_LOGS.info("Checking the Version is enabled or not :" +Locators_Safeguard.SG_Version_String.isEnabled());
					SA5.assertTrue(Locators_Safeguard.SG_Help_String.isEnabled(), "Help is enabled");
					APP_LOGS.info("Checking the Help is enabled or not :" +Locators_Safeguard.SG_Help_String.isEnabled());
				}				
		}
		catch(org.openqa.selenium.NoSuchElementException e) {
			APP_LOGS.info("Element not Found");
			e.printStackTrace();
			SA5.fail();
		}
		SA5.assertAll();
	}


	public void verify_Default_State_Of_All_Apps_In_App_Option() throws InterruptedException, IOException{
		try {

			if(sf_app_Check_Activation_Off_On().equals("OFF")) {	
				turn_Off_On_SG();
				customWait(1000);
				clickBtn(Locators_Safeguard.SG_Features_String);
				customWait(1000);
				enter_Pin();
				customWait(1000);	
			}
		}
		catch(org.openqa.selenium.NoSuchElementException e) {
			APP_LOGS.info("Element not Found");
			e.printStackTrace();
		}
	}

	//This method is used to lauch any app to foreground

	public void launchApp(String pkgName,String ActivityName) {
		int num=0;
		aDriver.startActivity(pkgName, ActivityName);
		APP_LOGS.info("Application launched successfully" +num);
		num++;
	}

	//This is a common method used for 
	public void pass_Locators(WebElement loc1,WebElement loc2,WebElement loc3) throws InterruptedException {
		try {
		minWait();
		clickBtn(loc1);
		minWait();
		enter_Pin();
		customWait(2000);
		aDriver.pressKeyCode(AndroidKeyCode.MENU);
		customWait(2000);
		clickBtn(loc2);
		minWait();
		aDriver.pressKeyCode(AndroidKeyCode.MENU);
		customWait(3000);
		clickBtn(loc3);
		minWait();
		}
		catch(org.openqa.selenium.NoSuchElementException e) {
			APP_LOGS.info("Element not Found");
			e.printStackTrace();
		}
	}



	//THis case will be use to select All apps and check wheather sf screen is showing or not

	public void select_All_Apps_And_Check() throws InterruptedException, IOException {


		try {
			 
			customWait(1000);
	
			if(sf_app_Check_Activation_Off_On().equals("OFF")) {
	
				turn_Off_On_SG();
	
				pass_Locators(Locators_Safeguard.SG_Applications_String, Locators_Safeguard.SG_Select_All_Btn, Locators_Safeguard.SG_Save_Btn);
				APP_LOGS.info("Launching All the Apps one by one and checking wheather SG locked screen is displayed or not");
				launching_All_Apps_One_By_One();
			}
			else {
	
	
				APP_LOGS.info("Else Part : Activation is already ON and checking all the Apps one by one");
				pass_Locators(Locators_Safeguard.SG_Applications_String, Locators_Safeguard.SG_Select_All_Btn, Locators_Safeguard.SG_Save_Btn);
				APP_LOGS.info("Launching All the Apps one by one and checking wheather SG locked screen is displayed or not");
				launching_All_Apps_One_By_One();
	
			}
		}
		catch(org.openqa.selenium.NoSuchElementException e) {
			APP_LOGS.info("Element not Found");
			e.printStackTrace();
		}

	}


	//This method is used to validate the SG Locked screen



	//This methos is used to validate whaether an App is launched sucessfully or not 


	//This method will launch All the Apps present in the device one by one and validate it 
	public void launching_All_Apps_One_By_One() throws InterruptedException, IOException {

		boolean ch1=false;
		boolean ch2=false;
		boolean ch3=false;
		boolean ch4=false;
		boolean ch5=false;
		boolean ch6=false;
		boolean ch7=false;
		boolean ch8=false;
		boolean ch9=false;
		boolean ch10=false;
		boolean ch11=false;
		boolean ch12=false;
		boolean ch13=false;
		boolean ch14=false;
		boolean ch15=false;
		boolean ch16=false;
		boolean ch17=false;

		SoftAssert SA7=new SoftAssert();

		customWait(1000);
		Runtime.getRuntime().exec("adb shell am start -n  com.caf.fmradio/com.caf.fmradio.FMRadio");
		APP_LOGS.info("FM launched successfully");
		customWait(2000);
		try {
			if(isElementExist(Locators_Safeguard.SG_Locked_Screen_Validation) || isElementExist(Locators_Safeguard.SG_Safe_Guard_Locked_Screen_Edit_Box)) {
				ch1=true;
				APP_LOGS.info("Element FM is selected");
			}
		}
		catch(NoSuchElementException e) {
			e.printStackTrace();
			APP_LOGS.info("Element not Found Execption:FM Radio");
		}

		minWait();
		Runtime.getRuntime().exec("adb shell am start -n com.android.mms/com.android.mms.ui.ConversationList");
		APP_LOGS.info("mms launched successfully");
		customWait(2000);
		try {
			if(isElementExist(Locators_Safeguard.SG_Locked_Screen_Validation) || isElementExist(Locators_Safeguard.SG_Safe_Guard_Locked_Screen_Edit_Box)) {
				ch2=true;
				APP_LOGS.info("Element mms is selected");
			}
		}
		catch(NoSuchElementException e) {
			e.printStackTrace();
			APP_LOGS.info("Element not Found Execption:mms");
		}

		minWait();
		Runtime.getRuntime().exec("adb shell am start -n com.borqs.camera/com.borqs.camera.CameraActivity");
		APP_LOGS.info("Camera launched successfully");
		customWait(2000);
		try {
			if(isElementExist(Locators_Safeguard.SG_Locked_Screen_Validation)) {
				ch3=true;	
				APP_LOGS.info("Element camera is selected");
			}
		}
		catch (NoSuchElementException e) {
			e.printStackTrace();
			APP_LOGS.info("Element not Found Execption:Camera");
		}

		minWait();		
		Runtime.getRuntime().exec("adb shell am start -n com.android.soundrecorder/com.android.soundrecorder.SoundRecorder");
		APP_LOGS.info("sound recorder launched successfully");
		customWait(3000);
		try {
			if(isElementExist(Locators_Safeguard.SG_Locked_Screen_Validation) || isElementExist(Locators_Safeguard.SG_Safe_Guard_Locked_Screen_Edit_Box)) {
				ch4=true;
				APP_LOGS.info("Element sound recorder is selected");
			}
		}
		catch (NoSuchElementException e) {
			e.printStackTrace();
			APP_LOGS.info("Element not Found Execption:Sound Recorder");
		}

		minWait();
		Runtime.getRuntime().exec("adb shell am start -n com.android.deskclock/com.android.deskclock.DeskClock");
		APP_LOGS.info("Setup Wizard launched successfully");
		customWait(3000);
		try {
			if(isElementExist(Locators_Safeguard.SG_Locked_Screen_Validation)) {
				ch5=true;
				APP_LOGS.info("Element SEtup Wizard is selected");
			}
		}
		catch (NoSuchElementException e) {
			e.printStackTrace();
			APP_LOGS.info("Element not Found Execption:clock");
		}

		minWait();
		Runtime.getRuntime().exec("adb shell am start -n com.android.browser/com.android.browser.BrowserActivity");
		APP_LOGS.info("Browser launched successfully");
		customWait(3000);
		try {
			if(isElementExist(Locators_Safeguard.SG_Locked_Screen_Validation)) {
				ch6=true;	
				APP_LOGS.info("Element Browser is selected");
			}
		}
		catch(NoSuchElementException e) {
			e.printStackTrace();
			APP_LOGS.info("Element not Found Execption:browser");
		}

		minWait();
		Runtime.getRuntime().exec("adb shell am start -n com.android.contacts/com.android.contacts.activities.PeopleActivity");
		APP_LOGS.info("Contact launched successfully");
		customWait(3000);
		try {
			if(isElementExist(Locators_Safeguard.SG_Locked_Screen_Validation) || isElementExist(Locators_Safeguard.SG_Safe_Guard_Locked_Screen_Edit_Box)) {
				ch7=true;
				APP_LOGS.info("Element contacts is selected");
			}
		}
		catch (NoSuchElementException e) {
			e.printStackTrace();
			APP_LOGS.info("Element not Found Execption:Contact App");
		}
		
		minWait();
		Runtime.getRuntime().exec("adb shell am start -n com.android.dialer/com.android.dialer.DialerMainActivity");
		APP_LOGS.info("Dialer launched successfully");
		customWait(3000);
		try {
			if(isElementExist(Locators_Safeguard.SG_Locked_Screen_Validation) || isElementExist(Locators_Safeguard.SG_Safe_Guard_Locked_Screen_Edit_Box)) {
				ch9=true;
				APP_LOGS.info("Element Dailer is selected");
			}
		}
		catch (NoSuchElementException e) {
			e.printStackTrace();
			APP_LOGS.info("Element not Found Execption:Dialer");
		}
		
		minWait();
		Runtime.getRuntime().exec("adb shell am start -n com.borqs.backup/com.borqs.backup.activity.BackupRestoreActivity");
		APP_LOGS.info("Back up and restore launched successfully");
		customWait(3000);
		try {
			if(isElementExist(Locators_Safeguard.SG_Locked_Screen_Validation) || isElementExist(Locators_Safeguard.SG_Safe_Guard_Locked_Screen_Edit_Box)) {
				ch10=true;	
				APP_LOGS.info("Element Backup and restore is selected");
			}
		}
		catch (NoSuchElementException e) {
			e.printStackTrace();
			APP_LOGS.info("Element not Found Execption:Backup & restore");
		}

		minWait();
		Runtime.getRuntime().exec("adb shell am start -n com.android.gallery3d/com.android.gallery3d.app.Gallery");
		APP_LOGS.info("Waranty launched successfully");
		customWait(3000);
		try {
			if(isElementExist(Locators_Safeguard.SG_Locked_Screen_Validation) || isElementExist(Locators_Safeguard.SG_Safe_Guard_Locked_Screen_Edit_Box)) {

				ch13=true;
				APP_LOGS.info("Element Warranty is selected");
			}
		}
		catch (NoSuchElementException e) {
			e.printStackTrace();
			APP_LOGS.info("Element not Found Execption:Gallery");
		}
		
		minWait();
		Runtime.getRuntime().exec("adb shell am start -n com.android.settings/com.android.settings.Settings");
		APP_LOGS.info("Settings launched successfully");
		customWait(3000);
		try {
			if(isElementExist(Locators_Safeguard.SG_Locked_Screen_Validation) || isElementExist(Locators_Safeguard.SG_Safe_Guard_Locked_Screen_Edit_Box)) {
				ch14=true;
				APP_LOGS.info("Element Settings is selected");
			}
		}
		catch (NoSuchElementException e) {
			e.printStackTrace();
			APP_LOGS.info("Element not Found Execption:Settings");
		}
		
		minWait();
		Runtime.getRuntime().exec("adb shell am start -n com.android.calculator2/com.android.calculator2.Calculator");
		APP_LOGS.info("Calculator launched successfully");
		customWait(3000);
		try {
			if(isElementExist(Locators_Safeguard.SG_Locked_Screen_Validation) || isElementExist(Locators_Safeguard.SG_Safe_Guard_Locked_Screen_Edit_Box)) {
				ch15=true;
				APP_LOGS.info("Element calculator is selected");
			}
		}
		catch (NoSuchElementException e) {
			e.printStackTrace();
			APP_LOGS.info("Element not Found Execption:Calculator");
		}
		
		minWait();
		Runtime.getRuntime().exec("adb shell am start -n com.android.calendar/com.android.calendar.AllInOneActivity");
		APP_LOGS.info("Calender launched successfully");
		customWait(3000);
		try {
			if(isElementExist(Locators_Safeguard.SG_Locked_Screen_Validation) || isElementExist(Locators_Safeguard.SG_Safe_Guard_Locked_Screen_Edit_Box)) {
				ch16=true;
				APP_LOGS.info("Element Calender is selected");
			}
		}
		catch (NoSuchElementException e) {
			e.printStackTrace();
			APP_LOGS.info("Element not Found Execption:Calemder");
		}
		
		minWait();
		Runtime.getRuntime().exec("adb shell am start -n com.android.providers.downloads.ui/com.android.providers.downloads.ui.DownloadList");
		APP_LOGS.info("Download launched successfully");
		customWait(3000);
		try {
			if(isElementExist(Locators_Safeguard.SG_Locked_Screen_Validation) || isElementExist(Locators_Safeguard.SG_Safe_Guard_Locked_Screen_Edit_Box)) {
				ch17=true;
				APP_LOGS.info("Element Download is selected");
			}
		}
		catch (NoSuchElementException e) {
			e.printStackTrace();
			APP_LOGS.info("Element not Found Execption:Download");
		}

		if((ch1) && (ch2) && (ch3) && (ch4) && (ch5) && (ch6) && (ch7) && (ch9) && (ch10) && (ch13) && (ch14) && (ch15) && (ch16) && (ch17)) {

			check=true;
			APP_LOGS.info("All APPS laucned successfully");
			SA7.assertTrue(check, "All PASS");
		}
		else {
			APP_LOGS.info("Values of All Apps are :" +ch1+ "," +ch2+ "," +ch3+ "," +ch4+ "," +ch5+ "," +ch6+ "," +ch7+ "," +ch9+ "," +ch10+ "," +ch13+ "," +ch14+ "," +ch15+ "," +ch16+ "," +ch17);
			SA7.fail();
		}
		SA7.assertAll();
		minWait();
		 
	}



	//This Method is used to check wheather safeguardlock screen is presen or not ,if preset enter the Pin,else dont do anything


	public void unlockSG_Screen() throws InterruptedException {


		//enterTextToInputField(Locators.SG_Locked_Screen_Entered_Pin, "1234");
		aDriver.findElementByAndroidUIAutomator("UiSelector().resourceId(\"com.sonim.safeguard:id/editText\")").sendKeys("1234");
		minWait();
		aDriver.pressKeyCode(AndroidKeyCode.ENTER);
	}


	//This method will be used to unselect all apps and validate
	public void Unselect_All_Apps_And_Check() throws InterruptedException, IOException {
		
		if(special_case().equals("OFF")) {
			
			turn_Off_On_SG();
			
			pass_Locators(Locators_Safeguard.SG_Applications_String, Locators_Safeguard.SG_Un_Select_All_Btn, Locators_Safeguard.SG_Save_Btn);
			APP_LOGS.info("Launching All the Apps one by one and checking wheather SG locked screen is displayed or not");
			launching_All_Apps_One_By_One_With_Out_Locked_Screen();
		}	else {
			
			APP_LOGS.info("Else Part : Activation is already ON and checking all the Apps one by one");
			pass_Locators(Locators_Safeguard.SG_Applications_String, Locators_Safeguard.SG_Un_Select_All_Btn, Locators_Safeguard.SG_Save_Btn);
			
			APP_LOGS.info("Launching All the Apps one by one and checking wheather SG locked screen is displayed or not");
			launching_All_Apps_One_By_One_With_Out_Locked_Screen();
		}
	}

	public void launching_All_Apps_One_By_One_With_Out_Locked_Screen() throws IOException, InterruptedException {

		boolean ch1=false;
		boolean ch2=false;
		boolean ch3=false;
		boolean ch4=false;
		boolean ch5=false;
		boolean ch6=false;
		boolean ch7=false;
		boolean ch8=false;
		boolean ch9=false;
		boolean ch10=false;
		boolean ch11=false;
		boolean ch12=false;
		boolean ch13=false;
		boolean ch14=false;
		boolean ch15=false;
		boolean ch16=false;
		boolean ch17=false;

		SoftAssert SA8=new SoftAssert();
		try {
			Runtime.getRuntime().exec("adb shell am start -n  com.caf.fmradio/com.caf.fmradio.FMRadio");
			APP_LOGS.info("FM launched successfully");
			customWait(2000);
			if(isElementExist(Locators_Safeguard.SG_FM)) {
				ch1=true;
	
			}
	
			minWait();
			Runtime.getRuntime().exec("adb shell am start -n com.android.mms/com.android.mms.ui.ConversationList");
			APP_LOGS.info("mms launched successfully");
			customWait(2000);
			if(isElementExist(Locators_Safeguard.SG_Message)) {
				ch2=true;
			}
	
			minWait();
			Runtime.getRuntime().exec("adb shell am start -n com.borqs.camera/com.borqs.camera.CameraActivity");
			APP_LOGS.info("Camera launched successfully");
			customWait(2000);
			if(isElementExist(Locators_Safeguard.SG_Camera)) {
				ch3=true;
			}
			
			minWait();	
			Runtime.getRuntime().exec("adb shell am start -n com.android.soundrecorder/com.android.soundrecorder.SoundRecorder");
			APP_LOGS.info("sound recorder launched successfully");
			customWait(2000);
			if(isElementExist(Locators_Safeguard.SG_Sound_Recoder)){
				ch4=true;
			}
	
			minWait();	
			Runtime.getRuntime().exec("adb shell am start -n com.sonimtech.setupwizard/com.sonimtech.setupwizard.SetupLauncherActivity");
			APP_LOGS.info("Setup Wizard launched successfully");
			customWait(2000);
			if(isElementExist(Locators_Safeguard.SG_SetUpwizard)) {
				ch5=true;
			}
	
			minWait();	
			Runtime.getRuntime().exec("adb shell am start -n com.android.contacts/com.android.contacts.activities.PeopleActivity");
			APP_LOGS.info("Contact launched successfully");
			customWait(2000);
			if((isElementExist(Locators_Safeguard.SG_ADD_CONTACT_String) || isElementExist(Locators_Safeguard.SG_Find_Contacts))) {
	
				ch6=true;
			}
			minWait();	
			Runtime.getRuntime().exec("adb shell am start -n com.android.dialer/com.android.dialer.DialerMainActivity");
			APP_LOGS.info("Dialer launched successfully");
			customWait(2000);
			if(isElementExist(Locators_Safeguard.SG_RecentCalls)) {
				ch7=true;
			}
			
			minWait();	
			Runtime.getRuntime().exec("adb shell am start -n com.borqs.backup/com.borqs.backup.activity.BackupRestoreActivity");
			APP_LOGS.info("Back up and restore launched successfully");
			customWait(2000);
			if(isElementExist(Locators_Safeguard.SG_BackUp_restore)) {
				ch8=true;
			}
			minWait();	
			//Runtime.getRuntime().exec("adb shell am start -n com.sonim.ble.connect/com.sonim.ble.connect.BLEDiscoverActivity");
			aDriver.startActivity("com.sonim.ble.connect", "com.sonim.ble.connect.BLEDiscoverActivity");
			APP_LOGS.info("BLE launched successfully");
			customWait(2000);
			if(isElementExist(Locators_Safeguard.SG_BLE_Connect) || isElementExist(Locators_Safeguard.SG_Bluetooth_Error_Msg)) {
				ch9=true;
				customWait(1000);
	
				try {
					aDriver.findElementByAndroidUIAutomator("UiSelector().resourceId(\"android:id/button2\")").click();
				}
				catch (NoSuchElementException exception) {
					 
				}	
			}
	
			minWait();
			Runtime.getRuntime().exec("adb shell am start -n com.android.gallery3d/com.android.gallery3d.app.Gallery");
			APP_LOGS.info("Waranty launched successfully");
			customWait(2000);
			if(isElementExist(Locators_Safeguard.SG_Album)) {
				ch10=true;
			}
			
			minWait();	
			Runtime.getRuntime().exec("adb shell am start -n com.android.settings/com.android.settings.Settings");
			APP_LOGS.info("Settings launched successfully");
			customWait(3000);
			if(isElementExist(Locators_Safeguard.SG_Native_Settings)) {
				ch11=true;
			}
			
			minWait();	
			Runtime.getRuntime().exec("adb shell am start -n com.android.calculator2/com.android.calculator2.Calculator");
			APP_LOGS.info("Calculator launched successfully");
			customWait(2000);
			if(isElementExist(Locators_Safeguard.SG_Calc)){
				ch12=true;
			}
			
			minWait();	
			Runtime.getRuntime().exec("adb shell am start -n com.android.providers.downloads.ui/com.android.providers.downloads.ui.DownloadList");
			APP_LOGS.info("Download launched successfully");
			customWait(2000);
			if(isElementExist(Locators_Safeguard.SG_Downlaod)) {
				ch14=true;
			}
	
			if((ch1) && (ch2) && (ch3) && (ch4) && (ch5) && (ch6) && (ch7) && (ch8) && (ch9) && (ch10) && (ch11) && (ch12) && (ch14)) {
	
				check=true;
				APP_LOGS.info("All APPS laucned successfully");
				SA8.assertTrue(check, "All PASS");
			}
			else {
				APP_LOGS.info(" The values are :"+ch1+","+ch2+","+ch3+ ","+ch3+","+ch4+","+ch5+","+ch6+","+ch7+","+ch8+","+ch9+","+ch10+","+ch11+","+ch12+","+ch14 );
				SA8.fail();
			}
	
		
		}
		catch(org.openqa.selenium.NoSuchElementException e) {
			APP_LOGS.info("Element not Found");
			e.printStackTrace();
			SA8.fail();
		}
		SA8.assertAll();
		minWait();
		 	
	}

	//This will select few apps and check
	public void select_few_And_Check() throws InterruptedException, IOException {
		
		try {
			if(sf_app_Check_Activation_Off_On().equals("OFF")) {
				turn_Off_On_SG();
				minWait();
				clickBtn(Locators_Safeguard.SG_Applications_String);
				minWait();
				enter_Pin();
				customWait(1000);					
					for(int i=1;i<=2;i++) {
						minWait();				
						aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
						customWait(1000);
					}
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.MENU);
				customWait(2000);
				clickBtn(Locators_Safeguard.SG_Save_Btn);
				minWait();
				APP_LOGS.info("Launching few apps and checking");
				launch_Few_Apps();
			}
			else {
				APP_LOGS.info("Else Part : Activation is already ON and checking all the Apps one by one");
				minWait();
				clickBtn(Locators_Safeguard.SG_Applications_String);
				minWait();
				enter_Pin();
				customWait(1000);
				for(int i=1;i<=2;i++) {
					minWait();				
					aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
					customWait(1000);
				}
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
				minWait();
				
				aDriver.pressKeyCode(AndroidKeyCode.MENU);
				customWait(2000);
				clickBtn(Locators_Safeguard.SG_Save_Btn);
				minWait();
				APP_LOGS.info("Launching Few Apps");
				launch_Few_Apps();
			}
		}
		catch(org.openqa.selenium.NoSuchElementException e) {
			APP_LOGS.info("Element not Found");
			e.printStackTrace();
		}
	}

	public void launch_Few_Apps() throws IOException, InterruptedException {


		boolean ch1=false;
		boolean ch2=false;

		SoftAssert SA9=new SoftAssert();
		try {
			 
			Runtime.getRuntime().exec("adb shell am start -n com.android.calendar/com.android.calendar.AllInOneActivity");
			APP_LOGS.info("Calender launched successfully");
			minWait();
			if(isElementExist(Locators_Safeguard.SG_Locked_Screen_Validation) || isElementExist(Locators_Safeguard.SG_Safe_Guard_Locked_Screen_Edit_Box)) {
				ch1=true;
			}
			if(ch1) {
				check=true;
				SA9.assertTrue(check, "True");
			}
			else {
				SA9.fail();
			}
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			SA9.fail();
		}
		SA9.assertAll();
	}

	//This method is used to check functionality of cancel,save,select All,UnselectAll options
	public void cancel_save_select_unselect() throws InterruptedException, IOException {
		Runtime.getRuntime().exec("adb shell am start -n com.sonim.safeguard/com.sonim.safeguard.SGSettingsPreferenceActivity");
		customWait(2000);
		clickBtn(Locators_Safeguard.SG_Applications_String);
		minWait();
		enter_Pin();
		customWait(2000);
		aDriver.pressKeyCode(AndroidKeyCode.MENU);
		customWait(2000);
		clickBtn(Locators_Safeguard.SG_Select_All_Btn);
	}
	


	//This method is used to check the feature Tab present in the SG
	public void check_Features_Tab() throws InterruptedException, IOException {
		try {
				 
				minWait();		
				if(sf_app_Check_Activation_Off_On().equals("OFF")) {
		
					turn_Off_On_SG();
		
					minWait();
					clickBtn(Locators_Safeguard.SG_Features_String);
					minWait();
					enter_Pin();
					customWait(2000);
					aDriver.pressKeyCode(AndroidKeyCode.MENU);
					customWait(2000);
					clickBtn(Locators_Safeguard.SG_Select_All_Btn);
					minWait();
					customWait(2000);
					aDriver.pressKeyCode(AndroidKeyCode.MENU);
					customWait(2000);
					clickBtn(Locators_Safeguard.SG_Save_Btn);
					minWait();
					APP_LOGS.info("Launching All features one by one");
					launchFeatures();
				}
		
				else {
					APP_LOGS.info("Checking the features When Activastion is ON ");
					minWait();
					clickBtn(Locators_Safeguard.SG_Features_String);
					minWait();
					enter_Pin();
					customWait(2000);
					aDriver.pressKeyCode(AndroidKeyCode.MENU);
					customWait(2000);
					clickBtn(Locators_Safeguard.SG_Select_All_Btn);
					customWait(2000);
					aDriver.pressKeyCode(AndroidKeyCode.MENU);
					customWait(2000);
					clickBtn(Locators_Safeguard.SG_Save_Btn);
					minWait();
					APP_LOGS.info("Launching All the Features");
					launchFeatures();
				}
		}
		catch(org.openqa.selenium.NoSuchElementException e) {
			APP_LOGS.info("Element not Found");
			e.printStackTrace();
		}
	}
	
	public void launchFeatures() throws IOException, InterruptedException {		
		SoftAssert SA=new SoftAssert();
		try {
			 
			customWait(2000);
			Runtime.getRuntime().exec("adb shell am start -n com.android.settings/com.android.settings.Settings");
			minWait();
			aDriver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().resourceId(\"com.android.settings:id/main_content\")).scrollIntoView(new UiSelector().text(\"Bluetooth\"))");  
			customWait(2000);		
			clickBtn(Locators_Safeguard.SG_Bluetooth);
			minWait();
			for(int i=1; i<=9; i++) {
				if(isElementExist(Locators_Safeguard.SG_Location_Off_On_Btn)) {
					break;
				}
				else {
					customWait(2000);
					customWait(2000);
					customWait(2000);
					customWait(2000);
					continue;
				}
			}
			clickBtn(Locators_Safeguard.SG_Location_Off_On_Btn);
			customWait(2000);
	
			if(isElementExist(Locators_Safeguard.SG_Locked_Screen_Validation) || isElementExist(Locators_Safeguard.SG_Safe_Guard_Locked_Screen_Edit_Box)){
				ch1=true;
				APP_LOGS.info("Bluetooth ch1 =" +ch1);
			}
			 
			customWait(2000);
			Runtime.getRuntime().exec("adb shell am start -n com.android.dialer/com.android.dialer.DialerMainActivity");
			customWait(3000);
			aDriver.pressKeyCode(AndroidKeyCode.MENU);
			customWait(2000);
			//Do horizontal scrolling
	
			for(int i=1;i<=8;i++) {
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
			}
			customWait(2000);
			clickBtn(Locators_Safeguard.SG_Call_Settings);
			customWait(1000);
			clickBtn(Locators_Safeguard.SG_Call_Blocking);
			customWait(1000);
			if(isElementExist(Locators_Safeguard.SG_Locked_Screen_Validation) || isElementExist(Locators_Safeguard.SG_Safe_Guard_Locked_Screen_Edit_Box)){
				ch2=true;
				APP_LOGS.info("Dialer ch2 ="+ ch2);
			}
			 
			customWait(2000);
			Runtime.getRuntime().exec("adb shell am start -n com.android.settings/com.android.settings.Settings");
			customWait(2000);
			aDriver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().resourceId(\"com.android.settings:id/main_content\")).scrollIntoView(new UiSelector().text(\"Location\"))");
			customWait(2000);
			clickBtn(Locators_Safeguard.SG_Location);
			minWait();
			clickBtn(Locators_Safeguard.SG_Location_Off_On_Btn);
			customWait(2000);
			if(isElementExist(Locators_Safeguard.SG_Locked_Screen_Validation) || isElementExist(Locators_Safeguard.SG_Safe_Guard_Locked_Screen_Edit_Box)){
				ch3=true;
				APP_LOGS.info("Location ch3 =" +ch3);
			}
			 
			customWait(1000);
			Runtime.getRuntime().exec("adb shell am start -n com.android.contacts/com.android.contacts.activities.PeopleActivity");
			customWait(2000);
//			customWait(2000);
			if(isElementExist(Locators_Safeguard.SG_ADD_CONTACT_String)) {
				customWait(1000);
				clickBtn(Locators_Safeguard.SG_ADD_CONTACT_String);
				customWait(2000);
				if(isElementExist(Locators_Safeguard.SG_Locked_Screen_Validation) || isElementExist(Locators_Safeguard.SG_Safe_Guard_Locked_Screen_Edit_Box)){
					ch4=true;
					APP_LOGS.info("Add Contact ch4 =" +ch4);
				}
				if((ch1) && (ch2) && (ch3) && (ch4)) {
					check=true;
					//softAssert_true(check,"Message");
					SA.assertTrue(check, "Passed");
				}
				else {
					APP_LOGS.info("The values for Features are :" +ch1+ "," +ch2+ "," +ch3+ "," +ch4);
					//softAssert_false();
					SA.fail();
				}
			}
			else if(isElementExist(Locators_Safeguard.SG_Find_Contact_In_Search_Box)) {
				customWait(2000);
				aDriver.pressKeyCode(AndroidKeyCode.MENU);
				customWait(2000);
				clickBtn(Locators_Safeguard.SG_Add_Contact_String);
				customWait(2000);
				if(isElementExist(Locators_Safeguard.SG_Locked_Screen_Validation) || isElementExist(Locators_Safeguard.SG_Safe_Guard_Locked_Screen_Edit_Box)){
					ch5=true;
					APP_LOGS.info("Add Contact when already a contact is added ch5 "+ch5);
				}
				customWait(2000);
				clearRecentApps();
				customWait(2000);
				Runtime.getRuntime().exec("adb shell am start -n com.android.contacts/com.android.contacts.activities.PeopleActivity");
				customWait(2000);
				aDriver.pressKeyCode(AndroidKeyCode.MENU);
				customWait(2000);
				if(isElementExist(Locators_Safeguard.SG_Blocked_Number)) {
					clickBtn(Locators_Safeguard.SG_Blocked_Number);
					customWait(2000);
				}
				else if(isElementExist(Locators_Safeguard.SG_Block_Number)){
					clickBtn(Locators_Safeguard.SG_Block_Number);
					customWait(2000);
				}
				
				if(isElementExist(Locators_Safeguard.SG_Locked_Screen_Validation) || isElementExist(Locators_Safeguard.SG_Safe_Guard_Locked_Screen_Edit_Box)){
					ch6=true;
					APP_LOGS.info("Add Contact when already a contact is added ch6"+ch6);
				}
				customWait(2000);
				clearRecentApps();
				customWait(2000);
				Runtime.getRuntime().exec("adb shell am start -n com.android.contacts/com.android.contacts.activities.PeopleActivity");
				customWait(2000);
				aDriver.pressKeyCode(AndroidKeyCode.MENU);
				customWait(2000);
				clickBtn(Locators_Safeguard.SG_ME);
				customWait(2000);
				aDriver.pressKeyCode(AndroidKeyCode.MENU);
				customWait(2000);
				clickBtn(Locators_Safeguard.SG_Contact_EDit);
				customWait(2000);
	
				if(isElementExist(Locators_Safeguard.SG_Locked_Screen_Validation) || isElementExist(Locators_Safeguard.SG_Safe_Guard_Locked_Screen_Edit_Box)){
					ch7=true;
					APP_LOGS.info("ch7"+ch7);
				}
				if((ch1) && (ch2) && (ch3) && (ch5) && (ch6) && (ch7)) {
	
					APP_LOGS.info("TC Passed");
					check=true;
					//softAssert_true(check, "All are clicked");
					SA.assertTrue(check, "");
				}
				else {
					APP_LOGS.info("The values for Features are :" +ch1+ "," +ch2+ "," +ch3+  "," +ch5+ "," +ch6+ "," +ch7 );
					APP_LOGS.info("TC Failed");
					//softAssert_false();		
					SA.fail();
				}
				
			}
	
			//This part is used for scrolling tof
			minWait();
			 
			Runtime.getRuntime().exec("adb shell am start -n com.android.settings/com.android.settings.Settings");
			customWait(2000);
			aDriver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().resourceId(\"com.android.settings:id/main_content\")).scrollIntoView(new UiSelector().text(\"Factory reset\"))");
			customWait(2000);
			clickBtn(Locators_Safeguard.SG_Factory_reset);
			customWait(2000);
			if(isElementExist(Locators_Safeguard.SG_Locked_Screen_Validation) || isElementExist(Locators_Safeguard.SG_Safe_Guard_Locked_Screen_Edit_Box)) {
				check=true;
				APP_LOGS.info("TC Passed factory Reset " +check);
				//  softAssert_true(check, "Messages");
				SA.assertTrue(check, "");  
			}
			else {
				APP_LOGS.info("Test is for factory Reset");
				//  softAssert_false();
				SA.fail();
			}
			//This part is used for scrolling tof
			   
			Runtime.getRuntime().exec("adb shell am start -n com.android.settings/com.android.settings.Settings");
			customWait(2000);
			aDriver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().resourceId(\"com.android.settings:id/main_content\")).scrollIntoView(new UiSelector().text(\"Security\"))");
			clickBtn(Locators_Safeguard.SG_Security);
			customWait(2000);
			if(isElementExist(Locators_Safeguard.SG_Locked_Screen_Validation) || isElementExist(Locators_Safeguard.SG_Safe_Guard_Locked_Screen_Edit_Box)) {
	
				check=true;
				APP_LOGS.info("TC Passed Security " +check);
				//  softAssert_true(check, "Messages");
				SA.assertTrue(check, "");
			}
			else {
				APP_LOGS.info("Security");
				// softAssert_false();
				SA.fail();
			}
			String XP5deviceModelNumber=BaseUtil.fetchDeviceProperty("adb shell getprop gsm.version.baseband");
			if(XP5deviceModelNumber.contains("SL")) {
				check=true;
				APP_LOGS.info("TC Passed Teathering is not available in SL operator " +check);
			}
			else {
				Runtime.getRuntime().exec("adb shell am start -n com.android.settings/com.android.settings.Settings");
				customWait(2000);
				aDriver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().resourceId(\"com.android.settings:id/main_content\")).scrollIntoView(new UiSelector().text(\"More\"))");
				customWait(2000);
				clickBtn(Locators_Safeguard.SG_More);
				customWait(2000);
				try {
					clickBtn(Locators_Safeguard.SG_Teathering);
				}
				catch(NoSuchElementException exception) {
					 
				}
			
				customWait(2000);
				if(isElementExist(Locators_Safeguard.SG_Locked_Screen_Validation) || isElementExist(Locators_Safeguard.SG_Safe_Guard_Locked_Screen_Edit_Box)) {
		
					check=true;
					APP_LOGS.info("TC Passed Teathering " +check);
					//  softAssert_true(check, "Messages");
					SA.assertTrue(check, "");
				}
				else{
					APP_LOGS.info("Teathering");
					// softAssert_false();
					SA.fail();
				}
		
			}
			customWait(2000);
			
			//checking USb restricction
			 
			customWait(2000);
			Runtime.getRuntime().exec("adb shell am start -c android.intent.category.HOME -a android.intent.action.MAIN");
			customWait(2000);
			aDriver.pressKeyCode(AndroidKeyCode.MENU);
			customWait(2000);
			clickBtn(Locators_Safeguard.SG_MyNotification);
			customWait(2000);
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
			customWait(2000);
			aDriver.pressKeyCode(AndroidKeyCode.ENTER);
			customWait(2000);
		
			//This is for WIFI 
			 
			customWait(2000);
			Runtime.getRuntime().exec("adb shell am start -a android.intent.action.MAIN -n com.android.settings/.wifi.WifiSettings");
			//aDriver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().resourceId(\"com.android.settings:id/main_content\")).scrollIntoView(new UiSelector().text(\"WLAN\"))");
			customWait(2000);
			clickBtn(Locators_Safeguard.SG_Location_Off_On_Btn);
			customWait(2000);
			
			if(isElementExist(Locators_Safeguard.SG_Locked_Screen_Validation) || isElementExist(Locators_Safeguard.SG_Safe_Guard_Locked_Screen_Edit_Box)) {
				check=true;
				APP_LOGS.info("TC Passed wifi " +check);
				//  softAssert_true(check, "Messages");
				SA.assertTrue(check, "");
			}	else {
				APP_LOGS.info("Wifi");
				// softAssert_false();
				SA.fail();
			}			
			clickBtn(Locators_Safeguard.SG_Location_Off_On_Btn);
		}
		catch(org.openqa.selenium.NoSuchElementException e) {
			APP_LOGS.info("Element not Found");
			e.printStackTrace();
			SA.fail();
		}
		SA.assertAll();
	}


	//This Method will unselect all features and check
	public void  Unselect_All_Features_And_Check() throws IOException, InterruptedException {

		if(sf_app_Check_Activation_Off_On().equals("OFF")) {
			turn_Off_On_SG();
			minWait();
			clickBtn(Locators_Safeguard.SG_Features_String);
			minWait();
			enter_Pin();
			customWait(2000);
			aDriver.pressKeyCode(AndroidKeyCode.MENU);
			customWait(2000);
			clickBtn(Locators_Safeguard.SG_Un_Select_All_Btn);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.MENU);
			customWait(2000);
			clickBtn(Locators_Safeguard.SG_Save_Btn);
			minWait();
			APP_LOGS.info("Launching All features one by one and checking that locked screen should not be present");
			Unselect_All_Features_Options_check();
			
		}	else {
			APP_LOGS.info("Checking the features When Activastion is ON ");
			minWait();
			clickBtn(Locators_Safeguard.SG_Features_String);
			minWait();
			enter_Pin();
			customWait(2000);
			aDriver.pressKeyCode(AndroidKeyCode.MENU);
			customWait(2000);

			clickBtn(Locators_Safeguard.SG_Un_Select_All_Btn);
			customWait(2000);
			aDriver.pressKeyCode(AndroidKeyCode.MENU);
			customWait(2000);
			clickBtn(Locators_Safeguard.SG_Save_Btn);
			minWait();
			APP_LOGS.info("Launching All the Features");
			Unselect_All_Features_Options_check();
		}
	}

	//This method will unselect all features and save
	public void Unselect_All_Features_Options_check() throws IOException, InterruptedException {

		boolean ch1=false;
		boolean ch2=false;
		boolean ch3=false;
		boolean ch4=false;
		boolean ch5=false;
		boolean ch6=false;
		boolean ch7=false;
		boolean ch8=false;

		SoftAssert SA11=new SoftAssert();

		try {
			customWait(2000);
			Runtime.getRuntime().exec("adb shell am start -n com.android.settings/com.android.settings.Settings");
			minWait();
			aDriver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().resourceId(\"com.android.settings:id/main_content\")).scrollIntoView(new UiSelector().text(\"Bluetooth\"))");
			customWait(2000);
			clickBtn(Locators_Safeguard.SG_Bluetooth);
			customWait(10000);
			customWait(8000);
			clickBtn(Locators_Safeguard.SG_Location_Off_On_Btn);
			customWait(2000);
			if(isElementExist(Locators_Safeguard.SG_Bluetooth)){
				ch1=true;
				APP_LOGS.info("Bluetooth ch1"+ch1);
			}
	
			customWait(2000);
			Runtime.getRuntime().exec("adb shell am start -n com.android.dialer/com.android.dialer.DialerMainActivity");
			customWait(2000);
			aDriver.pressKeyCode(AndroidKeyCode.MENU);
			customWait(2000);
			//Do horizontal scrolling
	
			for(int i=1;i<=8;i++) {
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
			}
	
			customWait(2000);
	
			clickBtn(Locators_Safeguard.SG_Call_Settings);
			customWait(2000);
			clickBtn(Locators_Safeguard.SG_Call_Blocking);
			customWait(2000);
	
			if(isElementExist(Locators_Safeguard.SG_Blocked_NumberDailer)){
				ch2=true;
				APP_LOGS.info("Dialer ch2 =" +ch2);
			}
	
			 
			customWait(2000);
	
			Runtime.getRuntime().exec("adb shell am start -n com.android.settings/com.android.settings.Settings");
			customWait(2000);
			aDriver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().resourceId(\"com.android.settings:id/main_content\")).scrollIntoView(new UiSelector().text(\"Location\"))");
	
			customWait(2000);
			clickBtn(Locators_Safeguard.SG_Location);
			minWait();
			customWait(2000);
			clickBtn(Locators_Safeguard.SG_Location_Off_On_Btn);
			minWait();
			if(isElementExist(Locators_Safeguard.SG_Location_consent)) {
				minWait();
				clickBtn(Locators_Safeguard.SG_Location_Agree);		
			}
			minWait();
			if(isElementExist(Locators_Safeguard.SG_Location)){
				ch3=true;
				APP_LOGS.info("Location ch3 =" +ch3);
			}
	
			minWait();
		 
			Runtime.getRuntime().exec("adb shell am start -n com.android.contacts/com.android.contacts.activities.PeopleActivity");
			customWait(3000);
	
			if(isElementExist(Locators_Safeguard.SG_ADD_CONTACT_String)) {
				customWait(2000);
				customWait(2000);
				clickBtn(Locators_Safeguard.SG_ADD_CONTACT_String);
				customWait(2000);
	
				if(isElementExist(Locators_Safeguard.SG_Add_new_Contact)){
					ch4=true;
					APP_LOGS.info("Add Contact ch4 =" +ch4);
				}
	
				boolean returnResult=subApps();
				System.out.println(returnResult);
	
				if((ch1) && (ch2) && (ch3) && (ch4) && (returnResult)) {
					check=true;
					SA11.assertTrue(true, "All true");
				}
				else {
					APP_LOGS.info("The Values with out checking the Apps are :" +ch1+ "," +ch2+ "," +ch3+ "," +returnResult);
					SA11.fail();
				}
			}
			//Now Executing else part
			else if(isElementExist(Locators_Safeguard.SG_Find_Contact_In_Search_Box)) {
	
	
				customWait(3000);
				aDriver.pressKeyCode(AndroidKeyCode.MENU);
				customWait(2000);
				clickBtn(Locators_Safeguard.SG_Add_Contact_String);
				customWait(2000);
				if(isElementExist(Locators_Safeguard.SG_Add_new_Contact)){
					ch5=true;
					APP_LOGS.info("Add new contact ch5 "+ch5);
				}
	
				customWait(2000);	
				clearRecentApps();
				customWait(2000);
				Runtime.getRuntime().exec("adb shell am start -n com.android.contacts/com.android.contacts.activities.PeopleActivity");
				customWait(3000);
				aDriver.pressKeyCode(AndroidKeyCode.MENU);
				customWait(2000);
//				clickBtn(Locators_Safeguard.SG_Blocked_Number);
				if((isElementExist(Locators_Safeguard.SG_Blocked_Number))||(isElementExist(Locators_Safeguard.SG_Block_Number))){
					ch6=true;
					APP_LOGS.info("Blocked Number ch6"+ch6);
				}	
				customWait(2000);	
				clearRecentApps();
				customWait(1000);
				Runtime.getRuntime().exec("adb shell am start -n com.android.contacts/com.android.contacts.activities.PeopleActivity");
				customWait(3000);
				aDriver.pressKeyCode(AndroidKeyCode.MENU);
				customWait(2000);
				clickBtn(Locators_Safeguard.SG_ME);
				customWait(2000);
	
				if(isElementExist(Locators_Safeguard.SG_Add_new_Contact)){
					ch7=true;
					APP_LOGS.info(""+ch7);
				}
	
				boolean returnresult=subApps();
				
				if((ch1) && (ch2) && (ch3) && (ch5) && (ch6) && (ch7) && (returnresult)) {
	
					check=true;
					SA11.assertTrue(check, "");
	
				}
				else {
					APP_LOGS.info("else part for unchecking all apps ");
					APP_LOGS.info("The Values with out checking the Apps are :" +ch1+ "," +ch2+ "," +ch3+ "," +ch5+ "," +ch6+ "," +ch7+ "," +returnresult);
					SA11.fail();
				}				 
			}				
		}
		catch(org.openqa.selenium.NoSuchElementException e) {
			APP_LOGS.info("Element not Found");
			e.printStackTrace();
			SA11.fail();
		}
		SA11.assertAll();	
	}


	//This part is used for scrolling tof
	public boolean subApps() throws IOException, InterruptedException {	

		boolean chk1=false;
		boolean chk2=false;
		boolean chk3=false;
		boolean chk4=false;
		boolean chk5=false;
		boolean result=false;

		 
		customWait(1000);
		Runtime.getRuntime().exec("adb shell am start -n com.android.settings/com.android.settings.Settings");
		customWait(2000);
		aDriver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().resourceId(\"com.android.settings:id/main_content\")).scrollIntoView(new UiSelector().text(\"Factory reset\"))");
		customWait(2000);
		clickBtn(Locators_Safeguard.SG_Factory_reset);
		minWait();
			if(isElementExist(Locators_Safeguard.SG_Factory_reset)) {
				chk1=true;
				APP_LOGS.info("Factory Reset " +chk1);
			}
		//This part is used for scrolling tof
		minWait();
		   
		Runtime.getRuntime().exec("adb shell am start -n com.android.settings/com.android.settings.Settings");
		customWait(2000);
		aDriver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().resourceId(\"com.android.settings:id/main_content\")).scrollIntoView(new UiSelector().text(\"Security\"))");
		clickBtn(Locators_Safeguard.SG_Security);
		customWait(2000);
			if(isElementExist(Locators_Safeguard.SG_Security)) {
				chk2=true;
				APP_LOGS.info("Security " +chk2);
			}

			String XP5deviceModelNumber=BaseUtil.fetchDeviceProperty("adb shell getprop gsm.version.baseband");
			if(XP5deviceModelNumber.contains("SL")) {
				chk3=true;
				APP_LOGS.info("TC Passed Teathering is not available in SL operator " +check);
			}
			else {
				 
				customWait(2000);
				Runtime.getRuntime().exec("adb shell am start -n com.android.settings/com.android.settings.Settings");
				customWait(2000);
				aDriver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().resourceId(\"com.android.settings:id/main_content\")).scrollIntoView(new UiSelector().text(\"More\"))");
				customWait(2000);
				clickBtn(Locators_Safeguard.SG_More);
				customWait(2000);
				clickBtn(Locators_Safeguard.SG_Teathering);
				customWait(2000);
					if(isElementExist(Locators_Safeguard.SG_Teathering)) {
						chk3=true;
						APP_LOGS.info("SG_Teathering " +chk3);	
					}
			}
		

		//This is for WIFI 
		 
		customWait(2000);
		Runtime.getRuntime().exec("adb shell am start -n com.android.settings/com.android.settings.Settings");
		customWait(2000);
		aDriver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().resourceId(\"com.android.settings:id/main_content\")).scrollIntoView(new UiSelector().text(\"Wi-Fi\"))");
		customWait(1000);
		clickBtn(Locators_Safeguard.SG_WIFI);
		customWait(8000);
		clickBtn(Locators_Safeguard.SG_Location_Off_On_Btn);
		customWait(2000);
			if(isElementExist(Locators_Safeguard.SG_WIFI)) {
				chk5=true;
				APP_LOGS.info("WIFI " +ch5);
			}
			customWait(1000);
			clickBtn(Locators_Safeguard.SG_Location_Off_On_Btn);
			if((chk1) && (chk2) && (chk3)  && (chk5)) {
	
				result=true;
			}
	
			else {
				APP_LOGS.info("SubApp values are :"+chk1+ "," +chk2+ "," +chk3+ "," +chk5 );
				result=false;
			}
			return result;
	}


	//Here it ends the Method
	//This method is used for changing the Pin
	public void change_Pin(String oldpin, String newPin, String reEnterPin) throws InterruptedException, IOException {

		APP_LOGS.info("Chaning the PIN TC ");
		SoftAssert SA19=new SoftAssert();
		try {

			if(sf_app_Check_Activation_Off_On().equals("OFF")) {

				minWait();
				clickBtn(Locators_Safeguard.SG_toggle_btn);
				customWait(2000);
				minWait();
				aDriver.findElementByAndroidUIAutomator("UiSelector().resourceId(\"com.sonim.safeguard:id/dialog_edit_text\")").sendKeys(oldpin);
				APP_LOGS.info("PIN has been entered successfully");
				minWait();
				clickBtn(Locators_Safeguard.SG_OK_Btn_for_Pin);
				minWait();
				clickBtn(Locators_Safeguard.SG_Change_Pin_String);
				minWait();
				enterTextToInputField(Locators_Safeguard.SG_Old_Pin, oldpin);
				minWait();
				enterTextToInputField(Locators_Safeguard.SG_New_Pin, newPin);
				minWait();
				enterTextToInputField(Locators_Safeguard.SG_Reenter_Pin, reEnterPin);
				minWait();
				clickBtn(Locators_Safeguard.SG_OK_Btn_for_Pin);
				minWait();	
				clickBtn(Locators_Safeguard.SG_toggle_btn);
				minWait();
				enterTextToInputField(Locators_Safeguard.SG_Enter_Pin, reEnterPin);
				APP_LOGS.info(" Changed Pin entered successfully");
				clickBtn(Locators_Safeguard.SG_OK_Btn_for_Pin);
				minWait();
					if(isElementExist(Locators_Safeguard.SG_Activation_Text)) {
						check=true;
						SA19.assertTrue(check, "");
					}
					else {
						SA19.fail();
					}
			}	else {	
				APP_LOGS.info("Checking for the Pin change when activation is in On state ");
				minWait();
				clickBtn(Locators_Safeguard.SG_Change_Pin_String);
				minWait();
				enterTextToInputField(Locators_Safeguard.SG_Old_Pin, oldpin);
				minWait();
				enterTextToInputField(Locators_Safeguard.SG_New_Pin, newPin);
				minWait();
				enterTextToInputField(Locators_Safeguard.SG_Reenter_Pin, reEnterPin);
				minWait();
				clickBtn(Locators_Safeguard.SG_OK_Btn_for_Pin);
				minWait();
	
				clickBtn(Locators_Safeguard.SG_toggle_btn);
				minWait();
				enterTextToInputField(Locators_Safeguard.SG_Enter_Pin, reEnterPin);
				APP_LOGS.info(" Changed Pin entered successfully");
				clickBtn(Locators_Safeguard.SG_OK_Btn_for_Pin);
				minWait();
	
				if(isElementExist(Locators_Safeguard.SG_Activation_Text)) {
					check=true;
					SA19.assertTrue(check, "");
				}
				else {
					SA19.fail();
				}
			}
		}
		catch(org.openqa.selenium.NoSuchElementException e) {
			APP_LOGS.info("Element not Found");
			e.printStackTrace();
			SA19.fail();
		}
		SA19.assertAll();
	}
	//End of the Method

	//This Method is used to check for Invalid Pin
	public void Check_Invalid_Pin() throws InterruptedException, IOException {
		 
		APP_LOGS.info("--------------------------------------------------------------");
		APP_LOGS.info("Invalid Pincheck TC");
		SoftAssert SA20=new SoftAssert();
		try {
			if(sf_app_Check_Activation_Off_On().equals("OFF")) {
	
				turn_Off_On_SG();
				minWait();
	
				clickBtn(Locators_Safeguard.SG_Applications_String);
				minWait();
				//Here trying to enter wrong Pin
				enterTextToInputField(Locators_Safeguard.SG_Enter_Pin, "2222");
				minWait();
				clickBtn(Locators_Safeguard.SG_OK_Btn_for_Pin);
				minWait();
				if(isElementExist(Locators_Safeguard.SG_Input_PIN)) {
					check=true;
					APP_LOGS.info("Invalid Pin verification test Case Passed");
					SA20.assertTrue(check, "");
				} else {
				SA20.fail();
				}
			} else {

			clickBtn(Locators_Safeguard.SG_Applications_String);
			minWait();
			//Here trying to enter wrong Pin
			enterTextToInputField(Locators_Safeguard.SG_Enter_Pin, "2222");
			minWait();
			clickBtn(Locators_Safeguard.SG_OK_Btn_for_Pin);
			minWait();
				if(isElementExist(Locators_Safeguard.SG_Input_PIN)) {
					check=true;
					APP_LOGS.info("Invalid Pin verification test Case Passed");
					SA20.assertTrue(check, "");
				}	else {
					SA20.fail();
				}
			}		
		}
		catch(org.openqa.selenium.NoSuchElementException e) {
			APP_LOGS.info("Element not Found");
			e.printStackTrace();
			SA20.fail();
		}
		SA20.assertAll();
	}

	//End of Method

	//This method is used to check the wheather a Pin edit box accepts only number or not
	public void Pin_Acccepts_Only_Num() throws InterruptedException, IOException {

		APP_LOGS.info("-----------------------------------------------------------");
		APP_LOGS.info("TC for entering alphabets iin Pin edit box");
		SoftAssert SA21=new SoftAssert();
		try {
			if(sf_app_Check_Activation_Off_On().equals("OFF")) {
	
				turn_Off_On_SG();
				minWait();
	
				clickBtn(Locators_Safeguard.SG_Applications_String);
				minWait();
				//Here trying to enter alphnumeric number Pin
				enterTextToInputField(Locators_Safeguard.SG_Enter_Pin, "abcd");
				minWait();
	
				if(!Locators_Safeguard.SG_OK_Btn_for_Pin.isEnabled()) {
					clickBtn(Locators_Safeguard.SG_OK_Btn_for_Pin);
					minWait();
					if(isElementExist(Locators_Safeguard.SG_Input_PIN)) {
						check=true;
						APP_LOGS.info("Invalid Pin verification test Case Passed");
						SA21.assertTrue(check, "");
					}
					else {
						SA21.fail();
					}
				}
					else {
					APP_LOGS.info("Pin Ok button is not enabled");
					SA21.fail();
				}	
			}	
			else {
	
				clickBtn(Locators_Safeguard.SG_Applications_String);
				minWait();
				//Here trying to enter wrong Pin
				enterTextToInputField(Locators_Safeguard.SG_Enter_Pin, "abcd");
				minWait();
	
				if(!Locators_Safeguard.SG_OK_Btn_for_Pin.isEnabled()) {
					clickBtn(Locators_Safeguard.SG_OK_Btn_for_Pin);
					minWait();
	
					if(isElementExist(Locators_Safeguard.SG_Input_PIN)) {
						check=true;
						APP_LOGS.info("Invalid Pin verification test Case Passed");
						SA21.assertTrue(check, "");
					}
					else {
						SA21.fail();
					}	
				}	
				else {
					APP_LOGS.info("Pin Ok button is not enabled");
					SA21.fail();
				}
			}
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
		
		}
		catch(org.openqa.selenium.NoSuchElementException e) {
			APP_LOGS.info("Element not Found");
			e.printStackTrace();
			SA21.fail();
		}
		SA21.assertAll();

	}

	//End of Method
	//This Method is used for Application Pin timeout
	public void application_Pin_30Sec_TimeOut() throws InterruptedException, IOException{


		APP_LOGS.info("-----------------------------------------");
		APP_LOGS.info("Application Pin Time Out");
		SoftAssert SA13=new SoftAssert();
		try {
			if(sf_app_Check_Activation_Off_On().equals("OFF")) {
	
				turn_Off_On_SG();
				clickBtn(Locators_Safeguard.SG_App_Pin_TimeOut_String);
				customWait(1000);
				APP_LOGS.info("Clicked on 30 Seconds in Applciaiton Time out");
	
				clickBtn(Locators_Safeguard.SG_App_Pin_TimeOut_30_Sec);
	
				customWait(30000);
				Runtime.getRuntime().exec("adb shell am start -n com.android.browser/com.android.browser.BrowserActivity");
				APP_LOGS.info("Browser launched successfully");
				customWait(2000);
				if(isElementExist(Locators_Safeguard.SG_Locked_Screen_Validation)) {
					check=true;
					SA13.assertTrue(check, "locked screen");
				}
				else {
					SA13.fail();
				}
	
	
			}
			else {
	
				clickBtn(Locators_Safeguard.SG_App_Pin_TimeOut_String);
				minWait();
				APP_LOGS.info("Clicked on 30 Seconds in Applciaiton Time out");
	
				clickBtn(Locators_Safeguard.SG_App_Pin_TimeOut_30_Sec);
	
				customWait(30000);
	
				System.out.println("Waiting for 30 seconds...");
	
	
				Runtime.getRuntime().exec("adb shell am start -n com.android.browser/com.android.browser.BrowserActivity");
	
	
				APP_LOGS.info("Browser launched successfully");
				customWait(2000);
				if(isElementExist(Locators_Safeguard.SG_Locked_Screen_Validation)) {
					check=true;
					SA13.assertTrue(check, "locked screen");
				}
				else {
					SA13.fail();
				}	
			}			
		}
		catch(org.openqa.selenium.NoSuchElementException e) {
				APP_LOGS.info("Element not Found");
				e.printStackTrace();
				SA13.fail();
		}
		SA13.assertAll();
	}
	
	//End of the Method
	//This Method is used to  check the application time out functionality whcih is set to 1 minute .

	public void pin_Time_Out_Set_To_1_Min() throws InterruptedException, IOException {

		APP_LOGS.info("-------TC for PinTime out Set to 1 minutes--------------------------");

		SoftAssert SA15=new SoftAssert();
		try {
			 
			customWait(1000);
			if(sf_app_Check_Activation_Off_On().equals("OFF")) {
	
				turn_Off_On_SG();
				customWait(1000);
				clickBtn(Locators_Safeguard.SG_App_Pin_TimeOut_String);
				customWait(1000);
				clickBtn(Locators_Safeguard.SG_App_Pin_TimeOut_1_Min);
	
				customWait(50000);
				Runtime.getRuntime().exec("adb shell am start -n com.android.browser/com.android.browser.BrowserActivity");
				System.out.println("1 Mintime out is working fine");
				System.out.println("Now launching browser.......");
	
				Runtime.getRuntime().exec("adb shell am start -n com.android.browser/com.android.browser.BrowserActivity");
				customWait(2000);
				if(isElementExist(Locators_Safeguard.SG_Locked_Screen_Validation)) {
	
					check=true;
					APP_LOGS.info("------------Locked screen exist---------");
					SA15.assertTrue(check, "");
				}
				else {
					APP_LOGS.info("----------Locked screen does not exist----"+check);
					SA15.fail();
				}
			}
			else {
				APP_LOGS.info("--------Activation is ON and executing else part ----------------");
				clickBtn(Locators_Safeguard.SG_App_Pin_TimeOut_String);
				customWait(1000);
				clickBtn(Locators_Safeguard.SG_App_Pin_TimeOut_1_Min);
	
				customWait(50000);
				// Runtime.getRuntime().exec("adb shell am start -n com.android.browser/com.android.browser.BrowserActivity");
				System.out.println("1 Mintime out is working fine");
				//  customWait(30000);
				System.out.println("launching browser App ....");
	
	
				Runtime.getRuntime().exec("adb shell am start -n com.android.browser/com.android.browser.BrowserActivity");
				customWait(2000);
				if(isElementExist(Locators_Safeguard.SG_Locked_Screen_Validation)) {
	
					check=true;
					APP_LOGS.info("------------Locked screen exist---------");
					SA15.assertTrue(check, "");
				}
				else {
					APP_LOGS.info("----------Locked screen does not exist----"+check);
					SA15.fail();
				}
			}		
		}
		catch(org.openqa.selenium.NoSuchElementException e) {
			APP_LOGS.info("Element not Found");
			e.printStackTrace();
			SA15.fail();
		}
		SA15.assertAll();
	}

	//This Ends the Method 

	//This method is used for oroginating gsm call...

	public void originateGsmCall() throws IOException, InterruptedException {

		SoftAssert SA16=new SoftAssert();
		 
		customWait(1000);
		navigateToSafeGuard();
		customWait(1000);
		Runtime.getRuntime().exec("adb shell am start -n com.android.dialer/com.android.dialer.DialerMainActivity");
		customWait(2000);

		if(isElementExist(Locators_Safeguard.SG_dialer_History_List)) {

			customWait(1000);
			aDriver.pressKeyCode(AndroidKeyCode.MENU);
			minWait();

			for(int i=1;i<=8;i++) {
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
			}

			clickBtn(Locators_Safeguard.SG_Clear_Call_Log);
			customWait(1000);
			clickBtn(Locators_Safeguard.SG_clear_button);
			customWait(1000);

			aDriver.findElementByAndroidUIAutomator("UiSelector().resourceId(\"com.android.dialer:id/action_bar_container\")").sendKeys("9738644810");
			customWait(2000);
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_CALL);
			customWait(5000);

			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_ENDCALL);


		}
		else {
			APP_LOGS.info("--------------No History Exist,So executing else part-----------");
			aDriver.findElementByAndroidUIAutomator("UiSelector().resourceId(\"com.android.dialer:id/action_bar_container\")").sendKeys("9738644810");
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_CALL);
			customWait(5000);

			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_ENDCALL);
		}
	}

	//Test of OCR


	//
	public void scroll() throws InterruptedException, IOException {

		navigateToSafeGuard();
		clickBtn(Locators_Safeguard.SG_Applications_String);
		enter_Pin();
		minWait();

		aDriver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().className(\"android.widget.FrameLayout\")).scrollIntoView(new UiSelector().text(\"Browser\"))");
		customWait(1000);

		//System.out.println(value);
	}


	//THis method is used for min pin code verification
	public void min_Code_Verification() throws InterruptedException, IOException {		 
		minWait();
		boolean ch1=false;
		boolean ch2=false;
		boolean ch3=false;
		boolean ch4=false;
		SoftAssert SA16=new SoftAssert();
		try {

			APP_LOGS.info("--------------Min Pin Code Verification-----------");
			navigateToSafeGuard();
			customWait(1000);
			clickBtn(Locators_Safeguard.SG_toggle_btn);
	
			minWait();
			enterTextToInputField(Locators_Safeguard.SG_Enter_Pin, "1");
			APP_LOGS.info("Entering a single digit number in pin field");
			customWait(1000);
	
			if(!(Locators_Safeguard.SG_OK_Btn_for_Pin.isEnabled())) {
				ch1=true;
				APP_LOGS.info("Ok button is disabled");
			}
	
			aDriver.pressKeyCode(AndroidKeyCode.BACK);
			customWait(1000);
			clickBtn(Locators_Safeguard.SG_toggle_btn);
			customWait(1000);
	
			enterTextToInputField(Locators_Safeguard.SG_Enter_Pin, "12");
			APP_LOGS.info("Entering a single digit number in pin field");
			customWait(1000);
	
			if(!(Locators_Safeguard.SG_OK_Btn_for_Pin.isEnabled())) {
				ch2=true;
				APP_LOGS.info("Ok button is disabled");
	
			}
			aDriver.pressKeyCode(AndroidKeyCode.BACK);
			customWait(1000);
			clickBtn(Locators_Safeguard.SG_toggle_btn);
			customWait(1000);
	
			enterTextToInputField(Locators_Safeguard.SG_Enter_Pin, "123");
			APP_LOGS.info("Entering a single digit number in pin field");
			customWait(1000);
	
			if(!(Locators_Safeguard.SG_OK_Btn_for_Pin.isEnabled())) {
				ch3=true;
				APP_LOGS.info("Ok button is disabled");
	
			}
	
			aDriver.pressKeyCode(AndroidKeyCode.BACK);
			customWait(1000);
			clickBtn(Locators_Safeguard.SG_toggle_btn);
			customWait(1000);
	
			enterTextToInputField(Locators_Safeguard.SG_Enter_Pin, "1234");
	
			customWait(1000);
	
	
			if(Locators_Safeguard.SG_OK_Btn_for_Pin.isEnabled()) {
				ch4=true;
				APP_LOGS.info("Ok button is Enabled");
	
			}
	
			if((ch1) && (ch2) && (ch3) && (ch4)) {
				check=true;
				APP_LOGS.info("TC passed");
	
				SA16.assertTrue(check, "");
			}
			else {
	
				APP_LOGS.info("Checking for values:" +ch1+ "," +ch2+ "," +ch3+ "," +ch4);
				SA16.fail();
			}
		}
		catch(org.openqa.selenium.NoSuchElementException e) {
			APP_LOGS.info("Element not Found");
			e.printStackTrace();
			SA16.fail();
		}
	}

	//End of the Method

	//This method is used to fetch max entered pin
	public void max_Limit() throws InterruptedException, IOException {

		SoftAssert SA17=new SoftAssert();
		try {
			minWait();
			navigateToSafeGuard();
			customWait(1000);
			clickBtn(Locators_Safeguard.SG_toggle_btn);
			customWait(1000);

			aDriver.findElementByAndroidUIAutomator("UiSelector().resourceId(\"com.sonim.safeguard:id/dialog_edit_text\")").sendKeys("123456789");
			customWait(1000);
			String fetch_pin=aDriver.findElementByAndroidUIAutomator("UiSelector().resourceId(\"com.sonim.safeguard:id/dialog_edit_text\")").getText();
			if(fetch_pin.length()==8) {
				check=true;
				APP_LOGS.info("Pin Length is :"+fetch_pin.length());
				SA17.assertTrue(check, "");
			}
			else {
				APP_LOGS.info("Pin Length is :"+fetch_pin.length());
				SA17.fail();
			}
		} catch (Exception e) {
			e.printStackTrace();
			SA17.fail();
		}
		SA17.assertAll();	

	}
	//End of the Method 

	//This Method is used to originate emergency call on top of the locked screen
	public void emergency_Call() throws InterruptedException, IOException {

		SoftAssert SA10=new SoftAssert();		 
		minWait();
		APP_LOGS.info("=====Emergency call==========");
		Runtime.getRuntime().exec("adb shell am start -n com.android.dialer/com.android.dialer.DialerMainActivity");
		minWait();
		aDriver.findElementByAndroidUIAutomator("UiSelector().resourceId(\"com.sonim.safeguard:id/emergencybutton\")").click();
		customWait(1000);
		aDriver.findElementByAndroidUIAutomator("UiSelector().className(\"android.widget.EditText\")").sendKeys("101");
		customWait(1000);
		aDriver.findElementByAndroidUIAutomator("UiSelector().resourceId(\"android:id/button1\")").click();

		minWait();
		if(isElementExist(Locators_Safeguard.SG_Emergency_No)) {
			check=true;

			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_ENDCALL);
			SA10.assertTrue(check, "");
		}
		else {
			APP_LOGS.info("TC failed");
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_ENDCALL);
			SA10.fail();
		}

		SA10.assertAll();
	}

	//
	public void special_Case_UnSelect() throws InterruptedException, IOException {

		 
		minWait();
		try {
			if(sf_app_Check_Activation_Off_On().equals("OFF")) {
				turn_Off_On_SG();
				pass_Locators(Locators_Safeguard.SG_Applications_String, Locators_Safeguard.SG_Un_Select_All_Btn, Locators_Safeguard.SG_Save_Btn);
			}	else {
				APP_LOGS.info("Checking the features When Activastion is ON ");
				pass_Locators(Locators_Safeguard.SG_Applications_String, Locators_Safeguard.SG_Un_Select_All_Btn, Locators_Safeguard.SG_Save_Btn);
			}
			minWait();
		}
		catch(org.openqa.selenium.NoSuchElementException e) {
			APP_LOGS.info("Element not Found S1");
			e.printStackTrace();
		}
	}

	//End of the Method

	public void special_Case_Select() throws InterruptedException, IOException {
		minWait();
		try {
			if(sf_app_Check_Activation_Off_On().equals("OFF")) {
				turn_Off_On_SG();
				minWait();
				clickBtn(Locators_Safeguard.SG_Applications_String);
				minWait();
				enter_Pin();
				customWait(2000);
				//Here select two apps
				String XP5deviceModelNumber=BaseUtil.fetchDeviceProperty("adb shell getprop gsm.version.baseband");
				if(XP5deviceModelNumber.contains("SPR")) {
					for(int i=1;i<=6;i++) {
						aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
						customWait(1000);
					}	
				}		
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.MENU);
				customWait(2000);
				clickBtn(Locators_Safeguard.SG_Save_Btn);
				minWait();	
				APP_LOGS.info("Selected App");
			} else {	
				APP_LOGS.info("Else Part : Activation is already ON and checking all the Apps one by one");
				minWait();
				clickBtn(Locators_Safeguard.SG_Applications_String);
				minWait();
				enter_Pin();
				String XP5deviceModelNumber=BaseUtil.fetchDeviceProperty("adb shell getprop gsm.version.baseband");
				if(XP5deviceModelNumber.contains("SPR")) {
					for(int i=1;i<=6;i++) {
						aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
						customWait(1000);
					}	
				}		
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
				minWait();
				APP_LOGS.info("Selected Broswer App");
				aDriver.pressKeyCode(AndroidKeyCode.MENU);
				customWait(2000);
				clickBtn(Locators_Safeguard.SG_Save_Btn);
				minWait();	
				APP_LOGS.info("Selected App");
			}
		}
		catch(org.openqa.selenium.NoSuchElementException e) {
			APP_LOGS.info("Element not Found");
			e.printStackTrace();
		}

	}

	//This Method is ised for trail
	public void trial() throws IOException, InterruptedException {
		navigateToSafeGuard();
		clickBtn(Locators_Safeguard.SG_Applications_String);
		enter_Pin();
		aDriver.scrollTo("Search");
	}
	public void trial123() throws InterruptedException, IOException {
		customWait(2000);

		for(int i=0; i<=5;i++) {
			customWait(2000);
			String Usb=Locators_Safeguard.SG_USB.getText();
			System.out.println(Usb);
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
			customWait(2000);
			
		}
		String Usb=Locators_Safeguard.SG_USB.getText();
		System.out.println(Usb);
    clickBtn(Locators_Safeguard.SG_USB);
	
}
	
	//This method is used for changing the Pin
	public void change_Pin() throws InterruptedException, IOException {

		APP_LOGS.info("Chaning the PIN TC ");
		SoftAssert SA19=new SoftAssert();
		try {

		if(sf_app_Check_Activation_Off_On().equals("OFF")) {

			turn_Off_On_SG();
			minWait();
			clickBtn(Locators_Safeguard.SG_Change_Pin_String);
			minWait();
			enterTextToInputField(Locators_Safeguard.SG_Old_Pin, "1234");
			minWait();
			enterTextToInputField(Locators_Safeguard.SG_New_Pin, "4545");
			minWait();
			enterTextToInputField(Locators_Safeguard.SG_Reenter_Pin, "4545");
			minWait();
			clickBtn(Locators_Safeguard.SG_OK_Btn_for_Pin);
			minWait();

			clickBtn(Locators_Safeguard.SG_toggle_btn);
			minWait();
			enterTextToInputField(Locators_Safeguard.SG_Enter_Pin, "4545");
			APP_LOGS.info(" Changed Pin entered successfully");

			clickBtn(Locators_Safeguard.SG_OK_Btn_for_Pin);
			minWait();
			if(isElementExist(Locators_Safeguard.SG_Activation_Text)) {
				check=true;
				SA19.assertTrue(check, "");
			}
			else {
				SA19.fail();
			}
		}

		else{

			APP_LOGS.info("Checking for the Pin change when activation is in On state ");
			minWait();
			clickBtn(Locators_Safeguard.SG_Change_Pin_String);
			minWait();
			enterTextToInputField(Locators_Safeguard.SG_Old_Pin, "1234");
			minWait();
			enterTextToInputField(Locators_Safeguard.SG_New_Pin, "4545");
			minWait();
			enterTextToInputField(Locators_Safeguard.SG_Reenter_Pin, "4545");
			minWait();
			clickBtn(Locators_Safeguard.SG_OK_Btn_for_Pin);
			minWait();

			clickBtn(Locators_Safeguard.SG_toggle_btn);
			minWait();
			enterTextToInputField(Locators_Safeguard.SG_Enter_Pin, "4545");
			APP_LOGS.info(" Changed Pin entered successfully");

			clickBtn(Locators_Safeguard.SG_OK_Btn_for_Pin);
			minWait();

			if(isElementExist(Locators_Safeguard.SG_Activation_Text)) {
				check=true;
				SA19.assertTrue(check, "");
			}
			else {
				SA19.fail();
			}
		}
		SA19.assertAll();
		}
		catch(org.openqa.selenium.NoSuchElementException e) {
			APP_LOGS.info("Element not Found");
			e.printStackTrace();
			SA19.fail();
		}

	}

}

