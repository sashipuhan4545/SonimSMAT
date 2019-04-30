package com.xp8.util;

import static org.testng.Assert.assertTrue;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.json.simple.parser.ParseException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.testng.asserts.SoftAssert;

import com.graphics.gui.JsonFileReaderAndWriter;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import application.AllQA;
import io.appium.java_client.android.AndroidKeyCode;
import junit.framework.Assert;

public class XP8_SCOUT_SafeGuard_Util extends BaseUtil{

	public static ExtentReports extent;
	public static ExtentTest test;

	public Process p;

	boolean value = false;
	public  String p_Id	 = "";								// Primary Device Serial Number.
	public String r_Id	 = "";								// Reference Device Serial Number.
	public String p_b_No = "";			   					// Primary Device Build Number.
	public String r_b_No = ""; 								// Reference Device Build Number.
	public String pryNum = AllQA.PRIMARYDEVMDN;	// Reference Device MDN. 
	public String refNum = AllQA.REFERENCEDEVMDN;	// Reference Device MDN.


	public boolean check = false;
	public boolean check1 = false;
	public boolean check2 = false;
	public boolean check3 = false;
	public boolean value2 = false;
	public boolean value9 = false;
	
	
	public void fetch_Devices_Details() throws InterruptedException, FileNotFoundException, IOException, ParseException {

		minWait();
		p_Id=JsonFileReaderAndWriter.primaryDevIdReader();
		r_Id=JsonFileReaderAndWriter.ReadRefDeviceId();
		p_b_No=JsonFileReaderAndWriter.primaryDevFirmwareReader();
		r_b_No=JsonFileReaderAndWriter.ReadRefDeviceFirmWare();
	}

	public void clickDownButton(int number)
	{
		/*
		 * clicks on down button with iteration as user input
		 */
		try {
			for(int i=0;i<number;i++){
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
				minWait();
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void Launch_SafeGuard(WebElement app) throws InterruptedException, IOException{
		/*
		 * Launch's SafeGuard transfer application
		 */
		SoftAssert sf = new SoftAssert();
		try {
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_HOME);
			minWait();
			XP8_Locators_SafeGuard.AppListIcon.click();
			minWait();
			scroll();
			minWait();
			scroll();
			minWait();
			clickBtn(app);
			APP_LOGS.info("Application launched succesfully");
			minWait();
			clickBtn(XP8_Locators_SafeGuard.setUpTab);
			minWait();
			clickBtn(XP8_Locators_SafeGuard.Safeguard);
			minWait();
		} catch (org.openqa.selenium.NoSuchElementException e) {
			e.printStackTrace();
			sf.fail();
		}	
		sf.assertAll();
	}	

	public void validateLaunchScoutApp(WebElement appTitle,String appName) throws InterruptedException {
		/*
		 * validate Scout APps Launch
		 */

		try {
			if(isElementExist(appTitle)) {
				check = true;
				customWait(1000);
				clickBackButton(1);
				test.log(LogStatus.INFO, appName+" is launched and verified ");
				assertTrue(check);
				if(appName.equals("SetUpWizard")) {
					clickBtn(XP8_Locators_SafeGuard.HomeBtn_Wizard);
				}
				test.log(LogStatus.PASS, "Test case status is Passed");
			}
			else {
				test.log(LogStatus.ERROR, appName+" launch is unverified");
				test.log(LogStatus.FAIL, "Test case status is Failed");
				Assert.fail();
			}
			clickBackButton(2);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void clickBackButton(int number)
	{
		/*
		 * clicks on back button with iteration as user input
		 */
		try {
			for(int i=0;i<number;i++){
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
				minWait();
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void checkStatus() {
		/*
		 * Check status of SafeGuard Activation
		 */
		SoftAssert SA = new SoftAssert();
		try {
			String status = XP8_Locators_SafeGuard.Activation_switch.getText();
			minWait();
			if(status.equals("OFF")) {
				check = true;
				APP_LOGS.info("Activation Status is "+status);
				test.log(LogStatus.INFO, "Activation Status is "+status);
				SA.assertTrue(check, "Activation Status is "+status);
				test.log(LogStatus.PASS, "Test case status is Passed");
			} else if(status.equals("ON")){
				APP_LOGS.info("Activation Status is "+status);
				test.log(LogStatus.FAIL, "Activation Status is "+status);
				SA.fail();
				test.log(LogStatus.FAIL, "Test case status is Failed");
			}	
			clickBackButton(2);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void checkStatusOfAllOptions() {
		/*
		 * Check default status of All options when Activation is OFF
		 */
		SoftAssert SA= new SoftAssert();
		try {
			minWait();
			String status = XP8_Locators_SafeGuard.Activation_switch.getText();
			if(status.equals("OFF")) {
				boolean application = statusOfOption(XP8_Locators_SafeGuard.Applications, "Applications");
				boolean features = statusOfOption(XP8_Locators_SafeGuard.Features, "Features");
				boolean changePIN = statusOfOption(XP8_Locators_SafeGuard.ChangePIN, "Change PIN");
				boolean AppPINTimeout = statusOfOption(XP8_Locators_SafeGuard.ApplicationPINTimeout, "Application PIN Timeout");
				scrollText("Help");
				boolean version = statusOfOption(XP8_Locators_SafeGuard.SG_Version, "Version");
				boolean Help = statusOfOption(XP8_Locators_SafeGuard.SG_Help, "Help");

				if(application && features && changePIN && AppPINTimeout && (!version) && (!Help)) {
					check = true;
					test.log(LogStatus.INFO, "Verified All options status successfully");
					test.log(LogStatus.PASS, "Test case status is Passed");
					SA.assertTrue(check, "All options status verified");
				}else {
					test.log(LogStatus.FAIL, "Failed to verify all options status");
					test.log(LogStatus.FAIL, "Tesct case status is Failed");
					SA.fail();
				}
			}else {
				test.log(LogStatus.ERROR, "Turn SafeGuard Activation OFF");
			}
			clickBackButton(3);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean statusOfOption(WebElement element,String option) {
		/*
		 * Common method to check status
		 */
		try {
			minWait();
			if(!(element.isEnabled())) {
				check = true;
				test.log(LogStatus.INFO, option+" option is Disabled");
			}else {
				check=false;
				test.log(LogStatus.INFO, option+" option is Enabled");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return check;

	}

	public void fetchDetails() {
		/*
		 * Fetch details (Version and help description) 
		 */
		SoftAssert SA = new SoftAssert();
		try {
			check=true;
			if(isElementExist(XP8_Locators_SafeGuard.Version)) {
				check1 = true;
				String version = XP8_Locators_SafeGuard.Version.getText();
				test.log(LogStatus.INFO,"SafeGuard Version is : "+ version);
			}else {
				test.log(LogStatus.ERROR, "Failed to fetch SafeGuard Version");
			}
			clickBtn(XP8_Locators_SafeGuard.SG_Help);
			minWait();
			if(isElementExist(XP8_Locators_SafeGuard.Help_description)) {
				String Help_Text = XP8_Locators_SafeGuard.Help_description.getText();
				if(Help_Text.contains("Default PIN is 1234")) {
					check2 = true;
					test.log(LogStatus.INFO,"SafeGuard Help Description : "+ Help_Text);
				}else {
					test.log(LogStatus.ERROR,"Default PIN not available");
				}
			}else {
				test.log(LogStatus.ERROR,"Help Description not available");
			}

			if(check1 && check2) {
				check = true;
				test.log(LogStatus.INFO,"Version and Default PIN displayed successfully");
				SA.assertTrue(check, "Version and Default PIN displayed successfully");
				test.log(LogStatus.PASS, "Test case status is Passed");
			}else {
				test.log(LogStatus.FAIL, "Test case status is Failed");
				SA.fail();
			}
			clickBackButton(3);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void turn_On_SG() throws InterruptedException {
		/*
		 * Turn ON SafeGuard Activation if OFF
		 */
		try {
			if(XP8_Locators_SafeGuard.Activation_switch.getText().equals("OFF")) {
				minWait();
				clickBtn(XP8_Locators_SafeGuard.Activation_switch);
				customWait(2000);
				enter_Pin();
			}else {
				test.log(LogStatus.FAIL, "Please Turn OFF SafeGuard Activation");
				Assert.fail();
			}
		}
		catch (Exception e) {
			APP_LOGS.info("Element not Found");
			e.printStackTrace();
		}
	}

	public void validateActivationONStatus() {
		/*
		 * Validate Activation ON status of SafeGuard
		 */
		SoftAssert SA = new SoftAssert();
		try {
			customWait(2000);
			if(XP8_Locators_SafeGuard.Activation_switch.getText().equals("ON")) {
				check = true;
				test.log(LogStatus.INFO, "Turned ON SafeGuard Activation Successfully");
				test.log(LogStatus.PASS, "Test case status is passed");
				SA.assertTrue(check, "Turned ON SafeGuard Activation Successfully");
			}else {
				test.log(LogStatus.INFO, "Failed to Turn ON SafeGuard Activation");
				test.log(LogStatus.FAIL, "Test case status is Failed");
				SA.fail();
			}
		} catch (Exception e) {
			APP_LOGS.info("Element not Found");
			e.printStackTrace();
		}
	}

	public void enter_Pin() throws InterruptedException {
		/*
		 * Enter SafeGuard PIN and click ok
		 */
		try {
			minWait();
			enterTextToInputField(XP8_Locators_SafeGuard.enterPINHereOption , "1234");
			customWait(2000);
			if(XP8_Locators_SafeGuard.Ok_Button.isEnabled()) {
				customWait(1000);
				clickBtn(XP8_Locators_SafeGuard.Ok_Button);
				APP_LOGS.info(" Entered pin and clicked ok");
			} else {
				APP_LOGS.info("Input PIN Ok button is not enabled");
				test.log(LogStatus.ERROR,"Input PIN Ok button is not enabled ");
			}
		}
		catch (Exception e) {
			APP_LOGS.info("Element not Found");
			e.printStackTrace();
		}
	}

	public void selectAllApplications() {
		/*
		 * Navigate and select all applications and save 
		 */
		try {
			minWait();
			clickBtn(XP8_Locators_SafeGuard.Applications);
			minWait();
			enter_Pin();
			clickMenuAndElement(XP8_Locators_SafeGuard.select_All);
			minWait();
			clickBtn(XP8_Locators_SafeGuard.save);
		} catch (Exception e) {
			APP_LOGS.info("Element not Found");
			e.printStackTrace();
		}
	}

	public void clickMenuAndElement(WebElement element) throws InterruptedException
	{
		/*
		 * Click on menu button and clicks on element passed
		 */
		try
		{
			customWait(2000);
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_MENU);
			customWait(2000);
			element.click();
			minWait();
		}catch (Exception e) {
			APP_LOGS.info("Element not Found");
			e.printStackTrace();
		}
	}


	public void launchSafeGuardedApps(String AppName) {
		/*
		 * Launch SafeGuard application
		 */
		try {
			minWait();
			launch_an_app(AppName);
			minWait();
			enter_pin_for_apps_features();
		}catch (Exception e) {
			APP_LOGS.info("Element not Found");
			e.printStackTrace();
		}
	}

	public boolean validate_launchofApp(WebElement element, String elementName) throws InterruptedException {
		/*
		 * Common method to validate launch of an App passing an element to validate
		 */
		SoftAssert Sa = new SoftAssert();
		try {
			customWait(6000);
			if (isElementExist(element)) {
				check = true;
				APP_LOGS.info(elementName + " is found succesfully");
				test.log(LogStatus.INFO, "SafeGuarded "+ elementName + " Application Successfully Launched ");
				Sa.assertTrue(check, "Tc Passed");

			} else {
				check = false;
				APP_LOGS.info(elementName + " is not found");
				test.log(LogStatus.ERROR,"SafeGuarded " + elementName + " Application is not Launched");
				Sa.fail();
			}
		} catch (Exception e) {
			e.printStackTrace();
			APP_LOGS.info("No Such Element Found");
		}
		return check;
	}

	public void enter_pin_for_apps_features() {
		/*
		 * Common Method to Enter SafeGuard PIN for Apps and features
		 */
		try {
			if(isElementExist(XP8_Locators_SafeGuard.Enter_PIN_string) && isElementExist(XP8_Locators_SafeGuard.Safeguard)) {
				minWait();
				enterTextToInputField(XP8_Locators_SafeGuard.enterPIN_TextFld, "1234");
				customWait(2000);
				aDriver.pressKeyCode(AndroidKeyCode.ENTER);
			}
		} catch (Exception e) {
			e.printStackTrace();
			APP_LOGS.info("No Such Element Found");
		}
	}

	public void launch_and_validate_safeguarded_apps() {
		/*
		 *  Launch SafeGuarded App and validate
		 */
		SoftAssert SA = new SoftAssert();
		try {

			launchSafeGuardedApps("calculator");
			boolean value1= validate_launchofApp(XP8_Locators_SafeGuard.calculator_pannel, "Calculator");

			launchSafeGuardedApps("calendar");
			customWait(4000);
			if(isElementExist(XP8_Locators_SafeGuard.Google_SignIn_page)) {
				 value2=validate_launchofApp(XP8_Locators_SafeGuard.Google_SignIn_page, "Calendar");
			}else {
				 value2=validate_launchofApp(XP8_Locators_SafeGuard.calender_view, "Calendar");
			}
			launchSafeGuardedApps("camera");
			boolean value3=validate_launchofApp(XP8_Locators_SafeGuard.Camera_view, "Camera");

			launchApplication(XP8_Locators_SafeGuard.chrome, "Chrome");
			boolean value4=validate_launchofApp(XP8_Locators_SafeGuard.Google_Search, "Chrome");

			launchApplication(XP8_Locators_SafeGuard.Clock, "Clock");
			boolean value5=validate_launchofApp(XP8_Locators_SafeGuard.clock_view, "Clock");
			
			launchSafeGuardedApps("contacts");
			boolean value6	= validate_launchofApp(XP8_Locators_SafeGuard.add_ContactIcon_O, "Contacts");
			
			launchApplication(XP8_Locators_SafeGuard.Downloads, "Downloads");
			boolean value7	= validate_launchofApp(XP8_Locators_SafeGuard.Downloads, "Downloads");

			launchApplication(XP8_Locators_SafeGuard.Drive, "Drive");
			boolean value8 = validate_launchofApp(XP8_Locators_SafeGuard.drive_add_option, "Drive");

			launchSafeGuardedApps("messaging");
			if(isElementExist(XP8_Locators_SafeGuard.permission_pop_up)) {
				for(int i=0;i<2;i++) {
					minWait();
					clickBtn(XP8_Locators_SafeGuard.allow_btn);
					minWait();
				}
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
			}
			if(isElementExist(XP8_Locators_SafeGuard.messaging_Title_Msg)) {
				 value9 = 	validate_launchofApp(XP8_Locators_SafeGuard.messaging_Title_Msg, "Messaging");
			}else {
				 value9 = 	validate_launchofApp(XP8_Locators_SafeGuard.messaging_Title_CallScreening, "Messaging");
			}
			
			launchSafeGuardedApps("phone");
			boolean value10 =	validate_launchofApp(XP8_Locators_SafeGuard.phone_dail, "Phone");

			launchSafeGuardedApps("scout");
			boolean value11 = validate_launchofApp(XP8_Locators_SafeGuard.SetUp_Scout, "Sonim Scout");

			launchSafeGuardedApps("settings");
			boolean value12  = validate_launchofApp(XP8_Locators_SafeGuard.networkAndInternet, "Settings");

			launchApplication(XP8_Locators_SafeGuard.Photos, "Photos");
			boolean value13 = validate_launchofApp(XP8_Locators_SafeGuard.PhotosAlbums, "Gallery");

			launchSafeGuardedApps("soundRecorder");
			boolean value14 = validate_launchofApp(XP8_Locators_SafeGuard.recordButton, "Sound Recorder");

			launchSafeGuardedApps("fm");
			boolean value15 =validate_launchofApp(XP8_Locators_SafeGuard.fm_on_off, "FM Radio");

			launchApplication(XP8_Locators_SafeGuard.File_Manager, "File Manager");
			if(isElementExist(XP8_Locators_SafeGuard.permission_pop_up)) {
				for(int i=0;i<2;i++) {
					minWait();
					clickBtn(XP8_Locators_SafeGuard.allow_btn);
					minWait();
				}
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
			}
			boolean value16 =	validate_launchofApp(XP8_Locators_SafeGuard.open_Navigation_Drawer, "File Explorer");
			clickBackButton(3);
			
			if(value1 && value2 && value3 && value4 && value5 && value6 && value7 && value8 && value9 && value10 && value11 && value12 && value13 && value14 && value15 && value16 ) {
				check = true;
				APP_LOGS.info("All SafeGuarded applications validated succesfully");
				test.log(LogStatus.INFO, "All SafeGuarded applications validated succesfully");
				test.log(LogStatus.PASS, "Test case Status is Passed");
				SA.assertTrue(check, "Tc Passed");
			} else {
				test.log(LogStatus.ERROR,"Failed to validate all SafeGuarded Applications");
				test.log(LogStatus.FAIL, "Test case Status is Failed");
				SA.fail();
			}
		} catch (Exception e) {
			e.printStackTrace();
			APP_LOGS.info("No Such Element Found");
		}
	}
	
	public void launchApplication(WebElement app,String text) {
		try {
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_HOME);
			minWait();
			Locators_BaseUtil.AppListIcon.click();
			minWait();
			scrollText(text);
			minWait();
			clickBtn(app);
			minWait();
			enter_pin_for_apps_features();
		} catch (Exception e) {
			e.printStackTrace();
			APP_LOGS.info("No Such Element Found");
		}
	}

	public boolean scrollText(String text) {
		/*
		Method used to scroll to an element in the scrollable view
		 */

		boolean check = false;
		try {		
			String scrollable = "new UiScrollable(new UiSelector().scrollable(true))";
			String textElement = ".scrollIntoView(new UiSelector().text(\""+ text +"\"))";
			aDriver.findElementByAndroidUIAutomator(scrollable+textElement);
			APP_LOGS.info("Searched application is found sucessfully : ");
			check = true;
			return check;
		}
		catch(NoSuchElementException e) {
			return check;
		}
	}

	public void selectAllFeatures() {
		/*
		 * Navigate and select all features and save
		 */
		try {
			minWait();
			clickBtn(XP8_Locators_SafeGuard.Features);
			minWait();
			enter_Pin();
			clickMenuAndElement(XP8_Locators_SafeGuard.select_All);
			minWait();
			clickBtn(XP8_Locators_SafeGuard.save);
		} catch (Exception e) {
			APP_LOGS.info("Element not Found");
			e.printStackTrace();
		}
	}

	public void launch_and_validate_safeguarded_features() {
		/*
		 * common method to launch and validate safeguarded features
		 */
		SoftAssert SA = new SoftAssert();
		try {
			minWait();
			launch_an_app("settings");
			minWait();

			launch_safeguarded_feature(XP8_Locators_SafeGuard.networkAndInternet, "Network & Internet", XP8_Locators_SafeGuard.Wifi);
			boolean feature1 = validate_launchof_All_Feature(XP8_Locators_SafeGuard.Wifi, "Wi-Fi");
			clickBtn(XP8_Locators_SafeGuard.toggle_switch);
			minWait();
			enter_pin_for_apps_features();
			clickBackButton(2);

			launch_safeguarded_feature(XP8_Locators_SafeGuard.networkAndInternet, "Network & Internet", XP8_Locators_SafeGuard.hotspotAndTethering);
			boolean feature2 = validate_launchof_All_Feature(XP8_Locators_SafeGuard.hotspotAndTethering, "Hotspot & tethering");
			clickBackButton(2);

			launch_safeguarded_feature(XP8_Locators_SafeGuard.connectedDevices, "Connected devices", XP8_Locators_SafeGuard.Bluetooth);
			boolean feature3 =validate_launchof_All_Feature(XP8_Locators_SafeGuard.Bluetooth, "Bluetooth");
			clickBtn(XP8_Locators_SafeGuard.toggle_switch);
			minWait();
			enter_pin_for_apps_features();
			clickBackButton(2);

			launch_safeguarded_feature(XP8_Locators_SafeGuard.connectedDevices, "Connected devices", XP8_Locators_SafeGuard.usb);
			boolean feature4 = validate_launchof_All_Feature(XP8_Locators_SafeGuard.Use_USB_to_string, "USB Restriction");
			clickBackButton(2);

			scrollText("Security & location");
			clickBtn(XP8_Locators_SafeGuard.security_Location);
			enter_pin_for_apps_features();
			boolean feature5 =validate_launchof_All_Feature(XP8_Locators_SafeGuard.security_Location, "Security & location");

			scrollText("Location");
			clickBtn(XP8_Locators_SafeGuard.Location);
			clickBtn(XP8_Locators_SafeGuard.toggle_switch);
			if(isElementExist(XP8_Locators_SafeGuard.Agree_Btn)) {
				clickBtn(XP8_Locators_SafeGuard.Agree_Btn);
			}
			minWait();
			enter_pin_for_apps_features();
			boolean feature6 = validate_launchof_All_Feature(XP8_Locators_SafeGuard.Location, "Location");
			clickBtn(XP8_Locators_SafeGuard.toggle_switch);
			if(isElementExist(XP8_Locators_SafeGuard.Agree_Btn)) {
				clickBtn(XP8_Locators_SafeGuard.Agree_Btn);
			}
			minWait();
			enter_pin_for_apps_features();
			clickBackButton(2);

			scrollText("System");
			clickBtn(XP8_Locators_SafeGuard.systemInsettingsList);
			scrollText("Reset options");
			clickBtn(XP8_Locators_SafeGuard.Reset_options);
			clickBtn(XP8_Locators_SafeGuard.erase_all_data_option);
			enter_pin_for_apps_features();
			boolean feature7 =validate_launchof_All_Feature(XP8_Locators_SafeGuard.erase_all_data_option, "Restore Factory settings");
			clickBackButton(3);

			launch_an_app("phone");
			minWait();
			clickBtn(XP8_Locators_SafeGuard.more_option);
			minWait();
			clickBtn(XP8_Locators_SafeGuard.call_settings);
			minWait();
			clickBtn(XP8_Locators_SafeGuard.Blocked_numbers);
			enter_pin_for_apps_features();
			boolean feature8 =validate_launchof_All_Feature(XP8_Locators_SafeGuard.Blocked_numbers, "Call blocking");
			clickBackButton(3);

			launch_an_app("contacts");
			addContact();
			boolean feature9 =validate_launchof_All_Feature(XP8_Locators_SafeGuard.added_contact, "Modify Contacts:add contact");
			clickBtn(XP8_Locators_SafeGuard.Contact_edit_button);
			enter_pin_for_apps_features();
			boolean feature10 =	validate_launchof_All_Feature(XP8_Locators_SafeGuard.edit_contact_string, "Modify Contacts:edit contact");
			clickBackButton(1);
			clickBtn(XP8_Locators_SafeGuard.more_option);
			minWait();
			clickBtn(XP8_Locators_SafeGuard.Contact_delete_option);
			enter_pin_for_apps_features();
			boolean feature11 =validate_launchof_All_Feature(XP8_Locators_SafeGuard.delete_button, "Modify Contacts:delete contact");
			clickBtn(XP8_Locators_SafeGuard.delete_button);
			clickBackButton(2);

			if(feature1 && feature2 && feature3 && feature4 && feature5 && feature6 && feature7 && feature8 && feature9 && feature10 && feature11) {
				check = true;
				APP_LOGS.info("All SafeGuarded features validated succesfully");
				test.log(LogStatus.INFO, "All SafeGuarded features validated succesfully");
				test.log(LogStatus.PASS, "Test case status is Passed");
				SA.assertTrue(check, "Tc Passed");
			} else {
				test.log(LogStatus.ERROR,"Failed to validate all SafeGuarded features");
				test.log(LogStatus.FAIL, "Test case status is Failed");
				SA.fail();
			}
		} catch (Exception e) {
			APP_LOGS.info("Element not Found");
			e.printStackTrace();
		}
		SA.assertAll();
	}

	public void launch_safeguarded_feature(WebElement element,String text1,WebElement subelement) {
		/*
		 * Navigate and launch SafeGuarded feature
		 */
		try {
			if(isElementExist(element)) {
				clickBtn(element);
			}else {
				scrollText(text1);
				clickBtn(element);
			}
			clickBtn(subelement);
			if(isElementExist(XP8_Locators_SafeGuard.toggle_switch)) {
				clickBtn(XP8_Locators_SafeGuard.toggle_switch);
				minWait();
				enter_pin_for_apps_features();
			}else {
				enter_pin_for_apps_features();
			}
		} catch (Exception e) {
			APP_LOGS.info("Element not Found");
			e.printStackTrace();
		}
	}

	public boolean validate_launchof_All_Feature(WebElement element, String elementName) throws InterruptedException {
		/*
		 * Common method to validate SafeGuarded Feature passing element to validate
		 */
		SoftAssert Sa = new SoftAssert();
		try {
			customWait(6000);
			if (isElementExist(element)) {
				check = true;
				APP_LOGS.info(elementName + " is found succesfully");
				test.log(LogStatus.INFO, "SafeGuarded "+ elementName + " Feature validated Successfully");
				Sa.assertTrue(check, "Tc Passed");

			} else {
				check = false;
				APP_LOGS.info(elementName + " is not found");
				test.log(LogStatus.ERROR,"SafeGuarded " + elementName + " feature is not validated");
			}
		} catch (Exception e) {
			e.printStackTrace();
			APP_LOGS.info("No Such Element Found");
		}
		return check;
	}

	public void addContact() throws InterruptedException, IOException
	{
		/*
		 * Add's Contact with Name and Phone Number
		 */
		SoftAssert SA = new SoftAssert();
		try {
			checkAddContactsOption();
			minWait();
			enter_pin_for_apps_features();
			customWait(2000);
			enterTextToInputField(XP8_Locators_SafeGuard.contactName, "Automation");
			customWait(2000);
			enterTextToInputField(XP8_Locators_SafeGuard.contact_Phone, refNum);
			customWait(2000);
			minWait();
			clickBtn(XP8_Locators_SafeGuard.Contact_Save_button);
		}catch (NoSuchElementException e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "Element Not Found");
		}
		SA.assertAll();
	}

	public void checkAddContactsOption() throws InterruptedException
	{
		/*
		 * Check Add contact option and click
		 */
		try
		{
			if(isElementExist(XP8_Locators_SafeGuard.addContact)) {
				clickBtn(XP8_Locators_SafeGuard.addContact);
			}else{
				minWait();
				clickBtn(XP8_Locators_SafeGuard.add_contact_floting_option);
			}
		}catch (NoSuchElementException e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "Element Not Found");
		}
	}

	public void unselectAllfeatures() {
		/*
		 * Navigate and unselect all features and save
		 */
		try {
			minWait();
			clickBtn(XP8_Locators_SafeGuard.Features);
			minWait();
			enter_Pin();
			clickMenuAndElement(XP8_Locators_SafeGuard.unselect_All);
			minWait();
			clickBtn(XP8_Locators_SafeGuard.save);
		} catch (Exception e) {
			APP_LOGS.info("Element not Found");
			e.printStackTrace();
		}
	}

	public void select_a_feature_and_validate() {
		/*
		 * navigate select a feature and validate
		 */
		try {
			minWait();
			clickBtn(XP8_Locators_SafeGuard.Features);
			enter_Pin();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_ENTER);
			minWait();
			clickBtn(XP8_Locators_SafeGuard.save);
			minWait();
			launch_an_app("settings");
			minWait();
			launch_safeguarded_feature(XP8_Locators_SafeGuard.connectedDevices, "Connected devices", XP8_Locators_SafeGuard.Bluetooth);
			validate_launchof_Feature(XP8_Locators_SafeGuard.Bluetooth, "Bluetooth");
			clickBtn(XP8_Locators_SafeGuard.toggle_switch);
			minWait();
			enter_pin_for_apps_features();
			clickBackButton(2);
		} catch (Exception e) {
			APP_LOGS.info("Element not Found");
			e.printStackTrace();
		}
	}

	public void validate_launchof_Feature(WebElement element, String elementName) throws InterruptedException {
		/*
		 * Common method to validate SafeGuarded Feature passing element to validate
		 */
		SoftAssert Sa = new SoftAssert();
		try {
			customWait(6000);
			if (isElementExist(element)) {
				check = true;
				APP_LOGS.info(elementName + " is found succesfully");
				test.log(LogStatus.INFO, "SafeGuarded "+ elementName + " Feature validated Successfully");
				test.log(LogStatus.PASS, "Test case status is Passed");
				Sa.assertTrue(check, "Tc Passed");

			} else {
				check = false;
				APP_LOGS.info(elementName + " is not found");
				test.log(LogStatus.ERROR,"SafeGuarded " + elementName + " feature is not validated");
				test.log(LogStatus.FAIL, "Test case status is Failed");
				Sa.fail();
			}
		} catch (Exception e) {
			e.printStackTrace();
			APP_LOGS.info("No Such Element Found");
		}
		Sa.assertAll();
	}

	public void changePIN(String OldPIN,String NewPIN) { 
		/*
		 * Change PIN with old and new as arguments
		 */
		try {
			minWait();
			clickBtn(XP8_Locators_SafeGuard.ChangePIN);
			minWait();
			enterTextToInputField(XP8_Locators_SafeGuard.enterPINHereOption, OldPIN);
			minWait();
			clickBtn(XP8_Locators_SafeGuard.Ok_Button);
			minWait();
			enterTextToInputField(XP8_Locators_SafeGuard.old_PIN, OldPIN);
			minWait();
			enterTextToInputField(XP8_Locators_SafeGuard.new_PIN, NewPIN);
			minWait();
			enterTextToInputField(XP8_Locators_SafeGuard.reEnter_PIN, NewPIN);
			minWait();
			if(XP8_Locators_SafeGuard.Ok_Button.isEnabled()) {
				customWait(1000);
				clickBtn(XP8_Locators_SafeGuard.Ok_Button);
				APP_LOGS.info(" Entered pin and clicked ok");
			} else {
				APP_LOGS.info("Input PIN Ok button is not enabled");
				test.log(LogStatus.ERROR,"Input PIN Ok button is not enabled ");
			}
		} catch (Exception e) {
			APP_LOGS.info("Element not Found");
			e.printStackTrace();
		}
	}

	public void validateChangePIN() {
		/*
		 * Validate change PIN option
		 */
		SoftAssert SA = new SoftAssert();
		try {
			minWait();
			clickBtn(XP8_Locators_SafeGuard.Activation_switch);
			minWait();
			enterTextToInputField(XP8_Locators_SafeGuard.enterPINHereOption , "4321");
			customWait(2000);
			if(XP8_Locators_SafeGuard.Ok_Button.isEnabled()) {
				customWait(1000);
				clickBtn(XP8_Locators_SafeGuard.Ok_Button);
				APP_LOGS.info(" Entered pin and clicked ok");
			} else {
				APP_LOGS.info("Input PIN Ok button is not enabled");
				test.log(LogStatus.ERROR,"Input PIN Ok button is not enabled ");
			}
			if(XP8_Locators_SafeGuard.Activation_switch.getText().equals("OFF")) {
				clickBtn(XP8_Locators_SafeGuard.Activation_switch);
				enterTextToInputField(XP8_Locators_SafeGuard.enterPINHereOption , "4321");
				clickBtn(XP8_Locators_SafeGuard.Ok_Button);
				check = true;
				test.log(LogStatus.INFO, "PIN changed Successfully and able to access with new PIN");
				SA.assertTrue(check, "PIN Changed successfully");
				test.log(LogStatus.PASS, "Test case status is Passed");
			}else {
				test.log(LogStatus.ERROR, "Failed to change PIN");
				test.log(LogStatus.FAIL, "Test case status is Failed");
				SA.fail();
			}
		} catch (Exception e) {
			APP_LOGS.info("Element not Found");
			e.printStackTrace();
		}
	}

	public void selectAnAppAndvalidate() {
		/*
		 * Navigate and select an application and validate
		 */
		try {
			minWait();
			clickBtn(XP8_Locators_SafeGuard.Applications);
			minWait();
			enter_Pin();
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_UP);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_UP);
			customWait(2000);
			Runtime.getRuntime().exec("adb -s "+ p_Id +" shell input text calculator");
			minWait();
			customWait(2000);
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_RIGHT);
			minWait();
			Runtime.getRuntime().exec("adb -s "+ p_Id +" shell input tap 1065 505");
			customWait(3000);
			clickBackButton(2);
			minWait();
			clickBtn(XP8_Locators_SafeGuard.cancel_option);
			minWait();
			clickBtn(XP8_Locators_SafeGuard.save);


			launchSafeGuardedApps("calculator");
			validate_launchofApp(XP8_Locators_SafeGuard.Calculator_number_board, "Calculator");
		} catch (Exception e) {
			APP_LOGS.info("Element not Found");
			e.printStackTrace();
		}
	}

	public void validate_application_PIN_timeout_Immediate() {
		try {
			launch_and_select_application_PIN_timeout(XP8_Locators_SafeGuard.Immediate_option);
			clickBackButton(3);
			launch_an_app("messaging");
			boolean value = enter_pin_for_app();
			if(value) {
				clickBackButton(1);
				launch_an_app("messaging");
				enter_pin_for_apps_features();
				validate_application_PIN_timeout("Immediate");
				clickBackButton(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
			APP_LOGS.info("No Such Element Found");
		}
	}

	public void validate_application_PIN_timeout_30_Seconds() {
		SoftAssert SA = new SoftAssert();
		try {
			launch_and_select_application_PIN_timeout(XP8_Locators_SafeGuard.thirty_Seconds_option);
			clickBackButton(3);
			launch_an_app("messaging");
			boolean value = enter_pin_for_app();
			if(value) {
				clickBackButton(1);
				customWait(15000);
				launch_an_app("messaging");
				if(!(isElementExist(XP8_Locators_SafeGuard.Enter_PIN_string) && isElementExist(XP8_Locators_SafeGuard.Safeguard)))
				{
					clickBackButton(1);
					customWait(10000);
					launch_an_app("messaging");
					enter_pin_for_apps_features();
					validate_application_PIN_timeout("30 Seconds");
					clickBackButton(1);
				}else {
					APP_LOGS.info("SafeGuard PIN screen popped before set Timeout");
					test.log(LogStatus.FAIL,"SafeGuard PIN screen popped before set Timeout");
					SA.fail();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			APP_LOGS.info("No Such Element Found");
		}
		SA.assertAll();
	}

	public void validate_application_PIN_timeout_1_minute() {
		SoftAssert SA = new SoftAssert();
		try {
			launch_and_select_application_PIN_timeout(XP8_Locators_SafeGuard.one_Minute_option);
			clickBackButton(3);
			launch_an_app("messaging");
			boolean value = enter_pin_for_app();
			if(value) {
				clickBackButton(1);
				customWait(30000);
				launch_an_app("messaging");
				if(!(isElementExist(XP8_Locators_SafeGuard.Enter_PIN_string) && isElementExist(XP8_Locators_SafeGuard.Safeguard)))
				{
					clickBackButton(1);
					customWait(30000);
					launch_an_app("messaging");
					enter_pin_for_apps_features();
					validate_application_PIN_timeout("1 Minutesss");
					clickBackButton(1);
				}else {
					check = false;
					APP_LOGS.info("SafeGuard PIN screen popped before set Timeout");
					test.log(LogStatus.ERROR,"SafeGuard PIN screen popped before set Timeout");
					SA.fail();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			APP_LOGS.info("No Such Element Found");
		}
		SA.assertAll();
	}

	public void unselectAllApplications() {
		/*
		 * navigate and unselect all applications and save
		 */
		try {
			minWait();
			clickBtn(XP8_Locators_SafeGuard.Applications);
			minWait();
			enter_Pin();
			clickMenuAndElement(XP8_Locators_SafeGuard.unselect_All);
			minWait();
			clickBtn(XP8_Locators_SafeGuard.save);
		} catch (Exception e) {
			APP_LOGS.info("Element not Found");
			e.printStackTrace();
		}
	}

	public void launch_and_select_application_PIN_timeout(WebElement element) {
		/*
		 * Common method to Navigate and select application PIN timeout option
		 */
		try {
			Launch_SafeGuard(XP8_Locators_SafeGuard.sonim_scout_AppLauncher);
			clickBtn(XP8_Locators_SafeGuard.ApplicationPINTimeout);
			minWait();
			clickBtn(element);
		} catch (Exception e) {
			APP_LOGS.info("Element not Found");
			e.printStackTrace();
		}
	}

	public boolean validate_application_PIN_timeout(String elementName) throws InterruptedException {
		/*
		 * Common method to validate application PIN timeout
		 */
		SoftAssert Sa = new SoftAssert();
		try {
			if (isElementExist(XP8_Locators_SafeGuard.messaging_Title_Msg)) {
				check = true;
				APP_LOGS.info("Application PIN Timeout "+ elementName + " option is validated successfully");
				test.log(LogStatus.INFO, "Application PIN Timeout "+ elementName + " option is validated successfully");
				Sa.assertTrue(check, "Tc Passed");

			} else {
				check = false;
				APP_LOGS.info(elementName + " is not found");
				test.log(LogStatus.ERROR," Failed to validate Application PIN Timeout "+ elementName + " option");
				Sa.fail();
			}
		} catch (Exception e) {
			e.printStackTrace();
			APP_LOGS.info("No Such Element Found");
		}
		Sa.assertAll();
		return check;
	}

	public boolean enter_pin_for_app() {
		/*
		 * Enter PIN 
		 */
		try {
			if(isElementExist(XP8_Locators_SafeGuard.Enter_PIN_string) && isElementExist(XP8_Locators_SafeGuard.Safeguard)) {
				minWait();
				enterTextToInputField(XP8_Locators_SafeGuard.enterPIN_TextFld, "1234");
				customWait(2000);
				aDriver.pressKeyCode(AndroidKeyCode.ENTER);
				check = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			APP_LOGS.info("No Such Element Found");
		}
		return check;
	}



}
