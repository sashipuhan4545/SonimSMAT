package com.xp3.Utils;

import static org.testng.Assert.assertTrue;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.testng.asserts.SoftAssert;

import com.graphics.gui.JsonFileReaderAndWriter;
import com.relevantcodes.extentreports.LogStatus;
import com.xp5S.util.CommonConfig;

import application.AllQA;
import io.appium.java_client.android.AndroidKeyCode;
import junit.framework.Assert;

public class XP3_SCOUT_Safeguard_Util extends BaseUtil {

	public boolean check = false;
	public boolean check1 = false;
	public boolean check2 = false;
	public boolean check3 = false;
	public boolean check4 = false;
	public boolean value3;
	public boolean value4;
	public boolean value7;
	public boolean value18;
	public boolean value19;


	public String p_Id	 = "";								// Primary Device Serial Number.
	public String r_Id	 = "";								// Reference Device Serial Number.
	public String p_b_No = "";			   					// Primary Device Build Number.
	public String r_b_No = ""; 								// Reference Device Build Number.
	public String pryNum = AllQA.PRIMARYDEVMDN;	// Reference Device MDN. 
	public String refNum = AllQA.REFERENCEDEVMDN;	// Reference Device MDN.

	//Added comment to test
	public void fetch_Devices_Details() throws InterruptedException, FileNotFoundException, IOException, org.json.simple.parser.ParseException {
		/*
		 * fetching device details
		 */
		System.out.println("In Fetching");
		minWait();
		p_Id=JsonFileReaderAndWriter.primaryDevIdReader();
		r_Id=JsonFileReaderAndWriter.ReadRefDeviceId();
		p_b_No=JsonFileReaderAndWriter.primaryDevFirmwareReader();
		System.out.println(p_b_No);
		r_b_No=JsonFileReaderAndWriter.ReadRefDeviceFirmWare();
	}

	public static void takeScreenShotForOcrPngFormat(String imageName) {
		/*
		 * Take screenshot in png format 
		 */

		File scrFile = ((TakesScreenshot) CommonConfig.getDriver()).getScreenshotAs(OutputType.FILE);
		try {

			String ocrImageFilePath = System.getProperty("user.dir") + "\\src\\test\\resources\\OCR_FILES\\" +imageName+".png";
			FileUtils.copyFile(scrFile, new File(ocrImageFilePath));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("<-----------Exeption occurs in takeScreenShotsForOcr method----------->");
		}

	}

	public static void enlargeImage(String inputImagePath,String outputImagePath) throws IOException {
		/*
		 *   Enlarge Image 
		 */
		File input = new File(inputImagePath);
		BufferedImage image = ImageIO.read(input);

		BufferedImage resized = resize(image, 800, 600);

		File output = new File(outputImagePath);
		ImageIO.write(resized, "png", output);

	}

	private static BufferedImage resize(BufferedImage img, int height, int width) {
		/*
		 * Buffered Image Resize
		 */
		Image tmp = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
		BufferedImage resized = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2d = resized.createGraphics();
		g2d.drawImage(tmp, 0, 0, null);
		g2d.dispose();
		return resized;
	}

	public void Launch_SafeGuard() throws InterruptedException, IOException{
		/*
		 * Launch's SafeGuard application
		 */
		SoftAssert sf = new SoftAssert();
		try {
			launch_an_app("scout");
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_LEFT);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_LEFT);
			customWait(3000);
			navigateApps(Locators_SafeGuard.setUpTab,Locators_SafeGuard.Safeguard);
			if(isElementExist(Locators_SafeGuard.permission_pop_up)) {
				for(int i=0;i<3;i++) {
					minWait();
					clickBtn(Locators_SafeGuard.allow_btn);
					minWait();
				}
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {
			e.printStackTrace();
			sf.fail();
		}	
		sf.assertAll();
	}	

	public void navigateApps(WebElement tab,WebElement app) throws InterruptedException, IOException {
		/*
		 * Navigates to given element in SCOUT app
		 */
		try {
			customWait(2000);
			clickBtn(tab);
			customWait(2000);
			for(int i=1; i<=6; i++) {
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_UP);
			}
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
			for(int i=1; i<=6; i++) {
				if(isElementExist(app)) {
					clickBtn(app);
					break;
				}
				else {
					aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
					continue;
				}
			}

		} catch (NoSuchElementException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Assert.fail();
		}		 
	}

	public void validateLaunchScoutApp(WebElement appTitle,String appName) throws InterruptedException {
		/*
		 * validate Scout APP's Launch
		 */
		try {
			if(isElementExist(appTitle)) {
				check = true;
				customWait(1000);
				clickBackButton(1);
				test.log(LogStatus.INFO, appName+" is launched and verified ");
				assertTrue(check);
				if(appName.equals("SetUpWizard")) {
					clickBtn(Locators_SafeGuard.HomeBtn_Wizard);
				}
				test.log(LogStatus.PASS, "Test case status is Passed");
			}
			else {
				test.log(LogStatus.ERROR, appName+" launch is unverified");
				Assert.fail();
				test.log(LogStatus.FAIL, "Test case status is Failed");
			}
			clickBackButton(2);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void clickBackButton(int number){
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
			String status = Locators_SafeGuard.Activation_switch.getText();
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

	public void checkStatusOfVersionAndHelp() {
		/*
		 *  Check version and Help default status
		 */
		SoftAssert SA = new SoftAssert();
		try {
			String status = Locators_SafeGuard.Activation_switch.getText();
			minWait();
			if(status.equals("OFF")) {
				scrollText("Help");
				if(Locators_SafeGuard.SG_Version.isEnabled() && Locators_SafeGuard.SG_Help.isEnabled()){
					check=true;
					APP_LOGS.info("Version and Help options are enabled when SafeGuard Activation is OFF");
					test.log(LogStatus.INFO," Version and Help options are enabled when SafeGuard Activation is OFF");
					SA.assertTrue(check, "Version and Help is Enabled");
					test.log(LogStatus.PASS, "Test case status is Passed");
				}		
				else {
					APP_LOGS.info("Version and Help options are not enabled when safeGuard Activation is OFF");
					test.log(LogStatus.FAIL," Version and Help options are not enabled when SafeGuard Activation is OFF");
					SA.fail();
					test.log(LogStatus.FAIL, "Test case status is Failed");
				}
			} else if(status.equals("ON")){
				APP_LOGS.info("Activation Status is "+status);
				test.log(LogStatus.ERROR, "Activation Status is "+status);
				test.log(LogStatus.ERROR, "Turn SafeGuard Activation OFF");
			}	
			clickBackButton(2);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void fetchDetails() {
		/*
		 * Fetch details (Version and help description) 
		 */
		SoftAssert SA = new SoftAssert();
		try {
			scrollText("Help");
			check=true;
			if(isElementExist(Locators_SafeGuard.Version)) {
				check1 = true;
				String version = Locators_SafeGuard.Version.getText();
				test.log(LogStatus.INFO,"SafeGuard Version is : "+ version);
			}else {
				test.log(LogStatus.ERROR, "Failed to fetch SafeGuard Version");
			}

			clickBtn(Locators_SafeGuard.SG_Help);
			minWait();
			if(isElementExist(Locators_SafeGuard.Help_description)) {
				String Help_Text = Locators_SafeGuard.Help_description.getText();
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

	public void checkStatusOfAllOptions() {
		/*
		 * Check default status of All options when Activation is OFF
		 */
		SoftAssert SA= new SoftAssert();
		try {
			minWait();
			String status = Locators_SafeGuard.Activation_switch.getText();
			if(status.equals("OFF")) {
				boolean application = statusOfOption(Locators_SafeGuard.Applications, "Applications");
				boolean features = statusOfOption(Locators_SafeGuard.Features, "Features");
				boolean changePIN = statusOfOption(Locators_SafeGuard.ChangePIN, "Change PIN");
				boolean AppPINTimeout = statusOfOption(Locators_SafeGuard.ApplicationPINTimeout, "Application PIN Timeout");
				scrollText("Help");
				boolean version = statusOfOption(Locators_SafeGuard.SG_Version, "Version");
				boolean Help = statusOfOption(Locators_SafeGuard.SG_Help, "Help");

				if(application && features && changePIN && AppPINTimeout && (!version) && (!Help)) {
					check = true;
					test.log(LogStatus.INFO, "Verified All options status successfully");
					test.log(LogStatus.PASS, "Test case status is Passed");
					SA.assertTrue(check, "All options status verified");
				}else {
					test.log(LogStatus.FAIL, "Failed to verify all options status");
					test.log(LogStatus.FAIL, "Test case status is Failed");
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

	public void turn_On_SG() throws InterruptedException {
		/*
		 * Turn ON SafeGuard Activation if OFF
		 */
		try {
			if(Locators_SafeGuard.Activation_switch.getText().equals("OFF")) {
				minWait();
				clickBtn(Locators_SafeGuard.Activation_switch);
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

	public void enter_Pin() throws InterruptedException {
		/*
		 * Enter SafeGuard PIN and click ok
		 */
		try {
			minWait();
			enterTextToInputField(Locators_SafeGuard.enterPINHereOption , "1234");
			customWait(2000);
			if(Locators_SafeGuard.Ok_Button.isEnabled()) {
				customWait(1000);
				clickBtn(Locators_SafeGuard.Ok_Button);
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

	public void turn_OFF_SG() throws InterruptedException {
		/*
		 * Turn OFF SafeGuard Activation if ON
		 */
		try {
			if(Locators_SafeGuard.Activation_switch.getText().equals("ON")) {
				minWait();
				clickBtn(Locators_SafeGuard.Activation_switch);
				customWait(2000);
				enter_Pin();
			}else {
				test.log(LogStatus.FAIL, "SafeGuard Activation is OFF");
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
			if(Locators_SafeGuard.Activation_switch.getText().equals("ON")) {
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

	public void selectAllApplications() {
		/*
		 * Navigate and select all applications and save 
		 */
		try {
			minWait();
			clickBtn(Locators_SafeGuard.Applications);
			minWait();
			enter_Pin();
			clickMenuAndElement(Locators_SafeGuard.select_All);
			minWait();
			clickMenuAndElement(Locators_SafeGuard.save);
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

	public void validate_launchofApplication(WebElement element, String elementName) throws InterruptedException {
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
				test.log(LogStatus.PASS, "Test case status is Passed");
				Sa.assertTrue(check, "Tc Passed");

			} else {
				check = false;
				APP_LOGS.info(elementName + " is not found");
				test.log(LogStatus.ERROR,"SafeGuarded " + elementName + " Application is not Launched");
				test.log(LogStatus.FAIL, "Test case status is Failed");
				Sa.fail();
			}
		} catch (Exception e) {
			e.printStackTrace();
			APP_LOGS.info("No Such Element Found");
		}
		Sa.assertAll();
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
			}
		} catch (Exception e) {
			e.printStackTrace();
			APP_LOGS.info("No Such Element Found");
		}
		Sa.assertAll();
		return check;
	}

	
	public void launch_and_validate_safeguarded_apps() {
		/*
		 * Launch SafeGuarded App and validate
		 */
		SoftAssert SA= new SoftAssert();
		try {
			minWait();
			launchSafeGuardedApps("browser");
			 boolean value1	= validate_launchofApp(Locators_SafeGuard.DefaultUrlTxt, "Browser");

			launchSafeGuardedApps("messaging");
			boolean value2 = validate_launchofApp(Locators_SafeGuard.messaging_Title, "Messaging");

			launchSafeGuardedApps("contacts");
			if(isElementExist(Locators_SafeGuard.contact_FindContacts)) {
				boolean value3 = validate_launchofApp(Locators_SafeGuard.contact_FindContacts, "Contacts");
			}else {
				boolean value4	= validate_launchofApp(Locators_SafeGuard.addContact, "Contacts");
			}

			launchSafeGuardedApps("phone");
			boolean value5	= validate_launchofApp(Locators_SafeGuard.phone_find, "Phone");

			launchSafeGuardedApps("scout");
			boolean value6	= validate_launchofApp(Locators_SafeGuard.SetUp_Scout, "Sonim Scout");

			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_HOME);
			if(isElementExist(Locators_SonimCare.Camera)){
			launchSafeGuardedApps("camera");
			 value7	= validate_launchofApp(Locators_SafeGuard.Camera_view, "Camera");
			}else {
				value7 = true;
			}
			
			launchSafeGuardedApps("settings");
			boolean value8	= validate_launchofApp(Locators_SafeGuard.settings, "Settings");

			launchSafeGuardedApps("calendar");
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_MENU);
			customWait(2000);
			boolean value9	= validate_launchofApp(Locators_SafeGuard.Calender_NewEvent, "Calendar");

			launchSafeGuardedApps("gallery");
			boolean value10	= validate_launchofApp(Locators_SafeGuard.GalleryAlbums, "Gallery");

			launchSafeGuardedApps("clock");
			boolean value11	= validate_launchofApp(Locators_SafeGuard.clock_image, "Clock");

			launchSafeGuardedApps("calculator");
			boolean value12	= validate_launchofApp(Locators_SafeGuard.calculator_image, "Calculator");

			launchSafeGuardedApps("soundRecorder");
			boolean value13	= validate_launchofApp(Locators_SafeGuard.sound_Recorder, "Sound Recorder");

			launchSafeGuardedApps("music");
			boolean value14	= validate_launchofApp(Locators_SafeGuard.artists_music_player, "Music");

			launchSafeGuardedApps("fm");
			boolean value15	= validate_launchofApp(Locators_SafeGuard.fm_image, "FM Radio");

			launchSafeGuardedApps("fileExplorer");
			boolean value16	= validate_launchofApp(Locators_SafeGuard.fileExplorer, "File Explorer");

			launchSafeGuardedApps("downloads");
			boolean value17	= validate_launchofApp(Locators_SafeGuard.Downloads, "Downloads");

			launchSafeGuardedApps("backUpReset");
			aDriver.pressKeyCode(AndroidKeyCode.ENTER);
			if(isElementExist(Locators_SafeGuard.backup_error_text)) {
				 value18	=	validate_launchofApp(Locators_SafeGuard.backup_error_text, "BackUp and Restore");
				clickBtn(Locators_SafeGuard.Ok_Button);
			}else {
				 value19	=	validate_launchofApp(Locators_SafeGuard.backUP_reset, "BackUp and Restore");
			}
			clickBackButton(3);
			
			if(value1 && value2 && (value3||value4) && value5 && value6 && value7 && value8 && value9 && value10 && value11 && value12 && value13 && value14 && value15 && value16 && value17 && (value18||value19)) {
				check = true;
				APP_LOGS.info("All SafeGuard applications validated succesfully");
				test.log(LogStatus.INFO, "All SafeGuard applications validated succesfully");
				test.log(LogStatus.PASS, "Test case status is Passed");
				SA.assertTrue(check, "Tc Passed");
			} else {
				test.log(LogStatus.ERROR,"Failed to validate all SafeGuarded Applications");
				test.log(LogStatus.FAIL, "Test case status is Failed");
				SA.fail();
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
			clickBtn(Locators_SafeGuard.Applications);
			minWait();
			enter_Pin();
			clickMenuAndElement(Locators_SafeGuard.unselect_All);
			minWait();
			clickMenuAndElement(Locators_SafeGuard.save);
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
			clickBtn(Locators_SafeGuard.Applications);
			minWait();
			enter_Pin();
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_UP);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_UP);
			customWait(2000);
			//	enterTextToInputField(Locators_SafeGuard.search_field, "messaging");
			Runtime.getRuntime().exec("adb -s "+ p_Id +" shell input text messaging");
			minWait();
			customWait(2000);
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_ENTER);
			//	clickBtn(Locators_SafeGuard.checkbox);
			minWait();
			clickMenuAndElement(Locators_SafeGuard.save);

			launchSafeGuardedApps("messaging");
			validate_launchofApplication(Locators_SafeGuard.messaging_Title, "Messaging");
		} catch (Exception e) {
			APP_LOGS.info("Element not Found");
			e.printStackTrace();
		}
	}

	public void selectAllFeatures() {
		/*
		 * Navigate and select all features and save
		 */
		try {
			minWait();
			clickBtn(Locators_SafeGuard.Features);
			minWait();
			enter_Pin();
			clickMenuAndElement(Locators_SafeGuard.select_All);
			minWait();
			clickMenuAndElement(Locators_SafeGuard.save);
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

			launch_safeguarded_feature(Locators_SafeGuard.networkAndInternet, "Network & Internet", Locators_SafeGuard.Wifi);
			boolean feature1 = validate_launchof_All_Feature(Locators_SafeGuard.Wifi, "Wi-Fi");
			clickBtn(Locators_SafeGuard.toggle_switch);
			minWait();
			enter_pin_for_apps_features();
			clickBackButton(2);
			
			launch_safeguarded_feature(Locators_SafeGuard.networkAndInternet, "Network & Internet", Locators_SafeGuard.hotspotAndTethering);
			boolean feature2 = validate_launchof_All_Feature(Locators_SafeGuard.hotspotAndTethering, "Hotspot & tethering");
			clickBackButton(2);

			launch_safeguarded_feature(Locators_SafeGuard.connectedDevices, "Connected devices", Locators_SafeGuard.Bluetooth);
			boolean feature3 =validate_launchof_All_Feature(Locators_SafeGuard.Bluetooth, "Bluetooth");
			clickBtn(Locators_SafeGuard.toggle_switch);
			minWait();
			enter_pin_for_apps_features();
			clickBackButton(2);

			launch_safeguarded_feature(Locators_SafeGuard.connectedDevices, "Connected devices", Locators_SafeGuard.usb);
			boolean feature4 = validate_launchof_All_Feature(Locators_SafeGuard.Use_USB_to_string, "USB Restriction");
			clickBackButton(2);

			scrollText("Security & location");
			clickBtn(Locators_SafeGuard.security_Location);
			enter_pin_for_apps_features();
			boolean feature5 =validate_launchof_All_Feature(Locators_SafeGuard.security_Location, "Security & location");

			scrollText("Location");
			clickBtn(Locators_SafeGuard.Location);
			clickBtn(Locators_SafeGuard.toggle_switch);
			if(isElementExist(Locators_SafeGuard.Agree_Btn)) {
				clickBtn(Locators_SafeGuard.Agree_Btn);
			}
			minWait();
			enter_pin_for_apps_features();
			boolean feature6 = validate_launchof_All_Feature(Locators_SafeGuard.Location, "Location");
			clickBtn(Locators_SafeGuard.toggle_switch);
			if(isElementExist(Locators_SafeGuard.Agree_Btn)) {
				clickBtn(Locators_SafeGuard.Agree_Btn);
			}
			minWait();
			enter_pin_for_apps_features();
			clickBackButton(2);

			scrollText("System");
			clickBtn(Locators_SafeGuard.systemInsettingsList);
			scrollText("Reset options");
			clickBtn(Locators_SafeGuard.Reset_options);
			clickBtn(Locators_SafeGuard.erase_all_data_option);
			enter_pin_for_apps_features();
			boolean feature7 =validate_launchof_All_Feature(Locators_SafeGuard.erase_all_data_option, "Restore Factory settings");
			clickBackButton(3);

			launch_an_app("phone");
			clickMenuAndElement(Locators_SafeGuard.call_settings);
			clickBtn(Locators_SafeGuard.Call_blocking);
			enter_pin_for_apps_features();
			boolean feature8 =validate_launchof_All_Feature(Locators_SafeGuard.Blocked_numbers_String, "Call blocking");
			clickBackButton(3);

			launch_an_app("contacts");
			addContact();
			boolean feature9 =validate_launchof_All_Feature(Locators_SafeGuard.added_contact, "Modify Contacts:add contact");
			clickMenuAndElement(Locators_SafeGuard.edit);
			enter_pin_for_apps_features();
			boolean feature10 =	validate_launchof_All_Feature(Locators_SafeGuard.edit_contact_string, "Modify Contacts:edit contact");
			clickBackButton(1);
			clickMenuAndElement(Locators_SafeGuard.delete);
			enter_pin_for_apps_features();
			boolean feature11 =validate_launchof_All_Feature(Locators_SafeGuard.delete_button, "Modify Contacts:delete contact");
			clickBtn(Locators_SafeGuard.delete_button);
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
			if(isElementExist(Locators_SafeGuard.toggle_switch)) {
				clickBtn(Locators_SafeGuard.toggle_switch);
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
				Sa.assertTrue(check, "Tc Passed");

			} else {
				check = false;
				APP_LOGS.info(elementName + " is not found");
				test.log(LogStatus.ERROR,"SafeGuarded " + elementName + " feature is not validated");
				Sa.fail();
			}
		} catch (Exception e) {
			e.printStackTrace();
			APP_LOGS.info("No Such Element Found");
		}
		Sa.assertAll();
	}

	public void enter_pin_for_apps_features() {
		/*
		 * Common Method to Enter SafeGuard PIN for Apps and features
		 */
		try {
			if(isElementExist(Locators_SafeGuard.Enter_PIN_string) && isElementExist(Locators_SafeGuard.Safeguard)) {
				minWait();
				enterTextToInputField(Locators_SafeGuard.enterPIN_TextFld, "1234");
				customWait(2000);
				aDriver.pressKeyCode(AndroidKeyCode.ENTER);
			}
		} catch (Exception e) {
			e.printStackTrace();
			APP_LOGS.info("No Such Element Found");
		}
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
			enterTextToInputField(Locators_SafeGuard.contactName, "Automation");
			customWait(2000);
			enterTextToInputField(Locators_SafeGuard.contact_Phone, refNum);
			customWait(2000);
			minWait();
			clickMenuAndElement(Locators_SafeGuard.save);
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
			if(isElementExist(Locators_SafeGuard.addContact)) {
				clickBtn(Locators_SafeGuard.addContact);
			}else{
				minWait();
				clickMenuAndElement(Locators_SafeGuard.addContactOpt);
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
			clickBtn(Locators_SafeGuard.Features);
			minWait();
			enter_Pin();
			clickMenuAndElement(Locators_SafeGuard.unselect_All);
			minWait();
			clickMenuAndElement(Locators_SafeGuard.save);
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
			clickBtn(Locators_SafeGuard.Features);
			enter_Pin();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_ENTER);
			minWait();
			clickMenuAndElement(Locators_SafeGuard.save);
			launch_an_app("settings");
			minWait();
			launch_safeguarded_feature(Locators_SafeGuard.connectedDevices, "Connected devices", Locators_SafeGuard.Bluetooth);
			validate_launchof_Feature(Locators_SafeGuard.Bluetooth, "Bluetooth");
			clickBtn(Locators_SafeGuard.toggle_switch);
			minWait();
			enter_pin_for_apps_features();
			clickBackButton(2);
		} catch (Exception e) {
			APP_LOGS.info("Element not Found");
			e.printStackTrace();
		}
	}

	public void changePIN(String OldPIN,String NewPIN) { 
		/*
		 * Change PIN with old and new as arguments
		 */
		try {
			minWait();
			clickBtn(Locators_SafeGuard.ChangePIN);
			minWait();
			enterTextToInputField(Locators_SafeGuard.enterPINHereOption, OldPIN);
			minWait();
			clickBtn(Locators_SafeGuard.Ok_Button);
			minWait();
			enterTextToInputField(Locators_SafeGuard.old_PIN, OldPIN);
			minWait();
			enterTextToInputField(Locators_SafeGuard.new_PIN, NewPIN);
			minWait();
			enterTextToInputField(Locators_SafeGuard.reEnter_PIN, NewPIN);
			minWait();
			if(Locators_SafeGuard.Ok_Button.isEnabled()) {
				customWait(1000);
				clickBtn(Locators_SafeGuard.Ok_Button);
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
			clickBtn(Locators_SafeGuard.Activation_switch);
			minWait();
			enterTextToInputField(Locators_SafeGuard.enterPINHereOption , "4321");
			customWait(2000);
			if(Locators_SafeGuard.Ok_Button.isEnabled()) {
				customWait(1000);
				clickBtn(Locators_SafeGuard.Ok_Button);
				APP_LOGS.info(" Entered pin and clicked ok");
			} else {
				APP_LOGS.info("Input PIN Ok button is not enabled");
				test.log(LogStatus.ERROR,"Input PIN Ok button is not enabled ");
			}
			if(Locators_SafeGuard.Activation_switch.getText().equals("OFF")) {
				clickBtn(Locators_SafeGuard.Activation_switch);
				enterTextToInputField(Locators_SafeGuard.enterPINHereOption , "4321");
				clickBtn(Locators_SafeGuard.Ok_Button);
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

	public void validateWrongPIN() {
		/*
		 * validate wrong PIN error message
		 */
		SoftAssert SA = new SoftAssert();
		try {
			clickBtn(Locators_SafeGuard.Activation_switch);
			enter_Pin();
			takeScreenShotForOcrPngFormat("SafeGuard");
			Thread.sleep(2000);
			//	enlargeImage("src/test/resources/OCR_FILES/SafeGuard.png", "src/test/resources/OCR_FILES/SafeGuard.png");
			minWait();
			OCR.my_main.validate_Using_OCR("SafeGuard.png");
			customWait(2000);
			Boolean value = searchStringOCR("wrong PIN", "ocr");
			if(value) {
				check = true;
				test.log(LogStatus.INFO, "Wrong PIN error verified successfully");
				SA.assertTrue(check, "Wrong PIN error verified successfully");
			}else{
				test.log(LogStatus.ERROR, "Failed to change PIN");
				SA.fail();
			}
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
			Launch_SafeGuard();
			clickBtn(Locators_SafeGuard.ApplicationPINTimeout);
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
			if (isElementExist(Locators_SafeGuard.messaging_Title)) {
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
			if(isElementExist(Locators_SafeGuard.Enter_PIN_string) && isElementExist(Locators_SafeGuard.Safeguard)) {
				minWait();
				enterTextToInputField(Locators_SafeGuard.enterPIN_TextFld, "1234");
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

	public void validate_application_PIN_timeout_Immediate() {
		try {
			launch_and_select_application_PIN_timeout(Locators_SafeGuard.Immediate_option);
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
			launch_and_select_application_PIN_timeout(Locators_SafeGuard.thirty_Seconds_option);
			clickBackButton(3);
			launch_an_app("messaging");
			boolean value = enter_pin_for_app();
			if(value) {
				clickBackButton(1);
				customWait(15000);
				launch_an_app("messaging");
				if(!(isElementExist(Locators_SafeGuard.Enter_PIN_string) && isElementExist(Locators_SafeGuard.Safeguard)))
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
			launch_and_select_application_PIN_timeout(Locators_SafeGuard.one_Minute_option);
			clickBackButton(3);
			launch_an_app("messaging");
			boolean value = enter_pin_for_app();
			if(value) {
				clickBackButton(1);
				customWait(30000);
				launch_an_app("messaging");
				if(!(isElementExist(Locators_SafeGuard.Enter_PIN_string) && isElementExist(Locators_SafeGuard.Safeguard)))
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

	public void ConnectSecuredWifi() throws InterruptedException, IOException {
		/*
		 * Connect wifi with entering passkey
		 */
		try {
			launch_an_app("settings");
			clickBtn(Locators_SafeGuard.networkAndInternet);
			customWait(2000);
			navigateToWifiOption();
			minWait();
			if(isElementExist(Locators_SafeGuard.disabled)) {
				enableFeature(Locators_SafeGuard.enabled,Locators_SafeGuard.disabled,Locators_SafeGuard.switch_Title);
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);		
				customWait(3000);
			}		
			else {
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);		    	
			}
			customWait(3000);
			String getSummary = Locators_SafeGuard.summaryWIFI_oreo.getText();
			System.out.println(getSummary);
			minWait();
			if(getSummary.contains("Not connected")) {
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
				customWait(2000);
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
				customWait(2000);
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
				customWait(4000);
				scrollToText("IDCSONWAP");
				//	selectIDcwifi();      
				minWait();
				if(isElementExist(Locators_SafeGuard.wifi_IDC_ForgetBtn)) {
					clickBtn(Locators_SafeGuard.wifi_IDC_ForgetBtn);
					minWait();
					scrollToText("IDCSONWAP");
					//selectIDcwifi();    
				}
				clickBtn(Locators_SafeGuard.wifi_show_Pswd);
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_UP);
				customWait(1000);

				changeToNumberMode_SMS(Locators_SafeGuard.wifi_IDC_Psswd);
				customWait(9000);
				if(isElementExist(Locators_SafeGuard.wifi_IDC)) {
					Runtime.getRuntime().exec("adb -s "+ p_Id +" shell input text 1");
					minWait();
					Runtime.getRuntime().exec("adb -s "+ p_Id +" shell input text dcS");
					minWait();
					Runtime.getRuntime().exec("adb -s "+ p_Id +" shell input text 0");
					minWait();
					Runtime.getRuntime().exec("adb -s "+ p_Id +" shell input text n");
					minWait();
					Runtime.getRuntime().exec("adb -s "+ p_Id +" shell input text 1");
					minWait();
					Runtime.getRuntime().exec("adb -s "+ p_Id +" shell input text md");
					minWait();
					Runtime.getRuntime().exec("adb -s "+ p_Id+" shell input text 0");
					minWait();
					Runtime.getRuntime().exec("adb -s "+ p_Id +" shell input text tc");
					minWait();
					Runtime.getRuntime().exec("adb -s "+ p_Id +" shell input text 0");
					minWait();
					Runtime.getRuntime().exec("adb -s "+ p_Id +" shell input text MbLr");
					minWait();
				}

				minWait();
				customWait(1000);
				String psswrd = Locators_SafeGuard.wifi_IDC_Psswd.getText();
				System.out.println(psswrd);
				clickBtn(Locators_SafeGuard.wifi_IDC_ConnectBtn);
				APP_LOGS.info("IDC available secured wifi is connected");
				customWait(4000);		    	
			}		    
		} 
		catch (NoSuchElementException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			test.log(LogStatus.ERROR, "NO Such Element Found");
			Assert.fail();
		}
	}
	
	
	public void navigateToWifiOption() throws InterruptedException, IOException {
		/*
		 * navigate to wifi option
		 */	
		try {
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			test.setDescription("Failure Reason: Element not found");
			Assert.fail();
		}
	}

	public void changeToNumberMode_SMS(WebElement element) throws InterruptedException {
		/*
		 * Change input field type to number
		 */
		minWait();
		element.sendKeys("123");
		customWait(1500);
		String text = element.getText();
		System.out.println(text);
		if(!text.equals("123")) {
			for (int i = 0; i < 3; i++) {
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_POUND);
				minWait();
			}
		}
		element.clear();
	}
	
	public void enableData() throws InterruptedException {
		/*
		 * enable data
		 */
		try {
			launch_an_app("settings");
			minWait();
			clickBtn(Locators_SafeGuard.networkAndInternet);
			minWait();
			if(isElementExist(Locators_SafeGuard.MobileNetwrks_oreo)) {
				minWait();
				clickBtn(Locators_SafeGuard.MobileNetwrks_oreo);
				APP_LOGS.info("Selected Mobile Networks");
				minWait();				
				enableSwitch(Locators_SafeGuard.enabled_Data_Oreo,Locators_SafeGuard.disabled_Data_Oreo,Locators_SafeGuard.switch_widget);
				minWait();
			}
		} catch (Exception e) {
			APP_LOGS.info("No Element found.");
			minWait();	
		}
	}
	
	
}
