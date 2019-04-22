package com.aosp.Utils;

import static org.testng.Assert.assertTrue;

import java.awt.AWTException;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Random;

import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.asserts.SoftAssert;

import com.graphics.gui.JsonFileReaderAndWriter;
import com.relevantcodes.extentreports.LogStatus;
import com.xp5S.util.BaseUtil;

import application.AllQA;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.AndroidKeyCode;

public class XP5S_Dev_Sanity_O_Util extends BaseUtil {

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

	public String p_Id	 = "";								// Primary Device Serial Number.
	public String r_Id	 = "";								// Reference Device Serial Number.
	public String p_b_No = "";			   					// Primary Device Build Number.
	public String r_b_No = ""; 								// Reference Device Build Number.
	public String pryNum = AllQA.PRIMARYDEVMDN;	// Reference Device MDN. 
	public String refNum = AllQA.REFERENCEDEVMDN;	// Reference Device MDN.


	// generation of random numbers
	public static int randonClickOnCalculatorKeyboard() throws InterruptedException  {
		Random rn = new Random();
		int answer = rn.nextInt(9) + 1;
		return answer;
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

	public void fetch_Devices_Details() throws InterruptedException, FileNotFoundException, IOException, org.json.simple.parser.ParseException {
		System.out.println("In Fetching");
		minWait();
		p_Id=JsonFileReaderAndWriter.primaryDevIdReader();
		r_Id=JsonFileReaderAndWriter.ReadRefDeviceId();
		p_b_No=JsonFileReaderAndWriter.primaryDevFirmwareReader();
		System.out.println(p_b_No);
		r_b_No=JsonFileReaderAndWriter.ReadRefDeviceFirmWare();
	}

	public void back() throws InterruptedException {
		/* 
		 * perform back by back key
		 */
		minWait();	
		aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
		minWait();	
		aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
	}

	public void navigateToNetworkAndInternetOptions() throws InterruptedException, IOException {
		/*
		 * Select Screen lock or Simcard Lock
		 */		
		try {
			if(isElementExist(Locators_DevSanity.networkAndInternet)) {
				minWait();
				clickBtn(Locators_DevSanity.networkAndInternet); 
				minWait();
			}else {
				scrollToElementWithDpadUp(Locators_DevSanity.networkAndInternet);
			}
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			test.setDescription("Failure Reason: Element not found");
			Assert.fail();
		}
	}

	public void scrollToElementWithDpadUp(WebElement element) {
		/*
		 * Clicks up button till element is available
		 */
		while(!isElementExist(element)) {
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_UP);
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
			//clickBtn(Locators_DevSanity.wifi);
			//navigateToOptions(Locators_DevSanity.wifi, "Wi-Fi");
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			test.setDescription("Failure Reason: Element not found");
			Assert.fail();
		}
	}

	public void changePreferredNetworkTypeVZW() {
		SoftAssert sf = new SoftAssert();
		try {
			navigateTo_NetworkSettings();
			clickBtn(Locators_DevSanity.preferred_network_option_oreo);
			minWait();
			clickBackButton(2);
		} catch (Exception e) {
			e.printStackTrace();
			APP_LOGS.info("No Element found.");
			test.log(LogStatus.ERROR, "No Such Element FOund");
			sf.fail();
		}
	}


	public void navigateTo_NetworkSettings() throws InterruptedException, IOException {
		/*
		 * This Method will Navigate to the APN's  
		 */
		try {
			launch_an_app("settings");
			minWait();

			navigateToNetworkAndInternetOptions();
			clickBtn(Locators_DevSanity.MobileNetwrks_oreo);
			minWait();
			scrollToElementWithDpadDown(Locators_DevSanity.PreferredNwType_Operators);
			minWait();
			clickBtn(Locators_DevSanity.PreferredNwType_Operators);
		} catch (org.openqa.selenium.NoSuchElementException e) {
			APP_LOGS.info("No Element found.");
			test.log(LogStatus.ERROR, "No Such Element Found");
			Assert.fail();
		}
	}

	public void navigateToSystemSettings(WebElement option) throws InterruptedException, IOException {
		/*
		 * select particular setting features
		 */
		SoftAssert Sa = new SoftAssert();
		try {	
			minWait();
			scrollToElementWithDpadDown(option);
			minWait();
			String getName= option.getText();
			minWait();
			APP_LOGS.info("Settings Feature is displayed sucessfully " + getName);
			minWait();
			clickBtn(option);
			APP_LOGS.info("Settings Feature is selected sucessfully  " +  getName);
			minWait();
		} catch (org.openqa.selenium.NoSuchElementException e) {
			APP_LOGS.info("No Element found.");
			e.printStackTrace();
			test.log(LogStatus.ERROR, "No Such Element Found");
			Sa.fail();
		}
		Sa.assertAll();
	}

	public void scrollToElementWithDpadDown(WebElement element)
	{
		/*
		 * Clicks down button till element is available
		 */
		while(!isElementExist(element)){
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
		}
	}

	public void validateLTESelection(String type) throws InterruptedException {
		/* 
		 * validate LTE selection is taken place
		 */
		SoftAssert sf = new SoftAssert();
		try {
			minWait();
			clickBtn(Locators_DevSanity.status);
			customWait(2000);
			clickBtn(Locators_DevSanity.sim_status);
			minWait();	
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
			customWait(2000);
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
			customWait(2000);
			clickBtn(Locators_DevSanity.mobileNwType);
			minWait();
			String get_NetworkType = Locators_DevSanity.networkType_Oreo.getText();	
			System.out.println(get_NetworkType);
			minWait();
			if(get_NetworkType.contains(type)) {
				check = true;
				APP_LOGS.info("Network Type verified "+get_NetworkType);
				test.log(LogStatus.INFO, "Prefered Network Type verified "+get_NetworkType);
				sf.assertTrue(check, "Validation is Pass");
			}else{
				APP_LOGS.info("test Case Failed");
				test.log(LogStatus.ERROR,"Prefered Network Type unverified "+get_NetworkType);
				sf.fail();
			}			
			clickBackButton(5);
		} catch (org.openqa.selenium.NoSuchElementException e) {
			e.printStackTrace();
			APP_LOGS.info("No Element found.");
			test.log(LogStatus.ERROR, "No Such Element FOund");
			sf.fail();
		}
		sf.assertAll();
	}

	public void changePreferedNetworkType_Mainline() throws InterruptedException, IOException {
		/*
		 * change network type
		 */
		minWait();	
		changeNetwork(Locators_DevSanity.GSM_NetwrkTyp);
		clickBackButton(2);
		navigateToSystemSettings(Locators_DevSanity.systemInsettingsList);
		navigateToSettingsFeature(Locators_DevSanity.AboutPhone,"About phone");
		validateLTESelection("EDGE");
		clickBackButton(5);


		navigateTo_NetworkSettings();
		minWait();	
		changeNetwork(Locators_DevSanity.CDMA_Combo_NetwrkTyp);
		clickBackButton(2);	
		navigateToSystemSettings(Locators_DevSanity.systemInsettingsList);
		navigateToSettingsFeature(Locators_DevSanity.AboutPhone,"About phone");	
		validateLTESelection("HSPA");
		clickBackButton(5);

		navigateTo_NetworkSettings();
		minWait();	
		changeNetwork(Locators_DevSanity.LTE_NetwrkTyp);
		clickBackButton(2);
		navigateToSystemSettings(Locators_DevSanity.systemInsettingsList);
		navigateToSettingsFeature(Locators_DevSanity.AboutPhone,"About phone");	
		validateLTESelection("4G");
		clickBackButton(5);

		navigateTo_NetworkSettings();
		minWait();	
		changeNetwork(Locators_DevSanity.Global_NetwrkTyp);
		clickBackButton(2);
		navigateToSystemSettings(Locators_DevSanity.systemInsettingsList);
		navigateToSettingsFeature(Locators_DevSanity.AboutPhone,"About phone");	
		validateLTESelection("4G");
		clickBackButton(5);
	}
	
	public void changePreferedNetworkType_Sprint() throws InterruptedException, IOException {
		/*
		 * change network type
		 */
		if(isElementExist(Locators_DevSanity.LTE_Recommended_Optn)) {
		clickBackButton(2);
		navigateToSystemSettings(Locators_DevSanity.systemInsettingsList);
		navigateToSettingsFeature(Locators_DevSanity.AboutPhone,"About phone");
		validateLTESelection("LTE");
		clickBackButton(5);
		}
		
		minWait();	
		changeNetwork(Locators_DevSanity.NetwrkType_2G);
		clickBackButton(2);
		navigateToSystemSettings(Locators_DevSanity.systemInsettingsList);
		navigateToSettingsFeature(Locators_DevSanity.AboutPhone,"About phone");
		validateLTESelection("EDGE");
		clickBackButton(5);

		
		navigateTo_NetworkSettings();
		minWait();	
		changeNetwork(Locators_DevSanity.NetwrkType_3G);
		clickBackButton(2);	
		navigateToSystemSettings(Locators_DevSanity.systemInsettingsList);
		navigateToSettingsFeature(Locators_DevSanity.AboutPhone,"About phone");	
		validateLTESelection("HSPA");
		clickBackButton(5);
	}

	public void changeNetwork(WebElement element) throws InterruptedException {
		/*
		 * change network
		 */
		SoftAssert SA=new SoftAssert();
		try {
			if(isElementExist(element)) {
				clickBtn(element);
			}else {
				scrollToElementWithDpadDown(Locators_DevSanity.GSM_WCDMA_Network_type);
				if(isElementExist(element)) {
					clickBtn(element);
				}else {
					scrollToElementWithDpadUp(element);
					clickBtn(element);
				}
			}
			customWait(2000);
		} catch (Exception e) {
			APP_LOGS.info("No Element found.");
			test.log(LogStatus.ERROR, "No Such Element Found");
			SA.fail();
		}
	}

	public void performAction() throws IOException, InterruptedException {
		/*
		 * 
		 * perform action airplane mode on and off
		 */
		navigateToAirplaneMode();
		minWait();
		enableSwitch(Locators_DevSanity.enabled_Airplane,Locators_DevSanity.disabled_Airplane,Locators_DevSanity.switch_widget);
		customWait(5000);
		disableSwitch(Locators_DevSanity.disabled_Airplane,Locators_DevSanity.enabled_Airplane,Locators_DevSanity.switch_widget );		
		customWait(9000);
		clickBackButton(3);
	}

	public void validateIMSLog(String str,String fileName) throws IOException, InterruptedException {
		/*
		 * Validate via log String that MO-Voice  call initiated 
		 */
		SoftAssert s1 = new SoftAssert();


		if(searchString(str, fileName)) {
			check = true;
			test.log(LogStatus.INFO, "IMS is registered and validated " );
			s1.assertTrue(check, " ");
		}
		else {
			test.log(LogStatus.ERROR, "Failure because: IMS is not Registered " );
			s1.fail();
		}
		s1.assertAll();
	}

	public void navigateToAirplaneMode() throws InterruptedException, IOException {
		/*
		 * Select Screen lock or Simcard Lock
		 */		
		try {
			minWait();
			clickBtn(Locators_DevSanity.networkAndInternet); 
			minWait();
			scrollToElementWithDpadDown(Locators_DevSanity.Flightmode);
			minWait();
			clickBtn(Locators_DevSanity.Flightmode);
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			test.setDescription("Failure Reason: Element not found");
			Assert.fail();
		}
	}

	public void validateEnabledisableFlightmode() throws InterruptedException, IOException, ParseException {
		/*
		 * Validates enable and disable flight mode with phone settings
		 */	
		//		customWait(1000);
		SoftAssert sf = new SoftAssert();

		String Uid=JsonFileReaderAndWriter.primaryDevIdReader();

		try {
			enableSwitch(Locators_DevSanity.enabled_Airplane,Locators_DevSanity.disabled_Airplane,Locators_DevSanity.switch_widget);
			customWait(5000);
			initiateCall();
			customWait(1000);

			if (isElementExist(Locators_DevSanity.airplaneMode_Call_Oreo)|| isElementExist(Locators_DevSanity.airplaneMode_Call)) {
				check1 = true;
				APP_LOGS.info("flight mode is enabled sucessfully"+ check1);
				minWait();	
				clickBtn(Locators_DevSanity.OK_Btn);
				minWait();	
			}
			customWait(1000);
			launch_an_app("settings");	
			navigateToAirplaneMode();
			disableSwitch(Locators_DevSanity.disabled_Airplane,Locators_DevSanity.enabled_Airplane,Locators_DevSanity.switch_widget );		
			customWait(9000);
			clickBackButton(1);
			initiateCall();
			customWait(3000);
			if (!isElementExist(Locators_DevSanity.airplaneMode_Call_Oreo)) {
				APP_LOGS.info("flight mode is disabled sucessfully");
				check2 = true;
				minWait();	
			}				
			customWait(1000);
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_ENDCALL);
			if((check1)&&(check2)){
				check= true;
				APP_LOGS.info("Enable and disable of Airplane mode with phone settings.");
				test.log(LogStatus.INFO, "Enable and disable of Airplane mode with phone settings validated Successfully");
				APP_LOGS.info("TC is Passed.");
				sf.assertTrue(check, "Validation is Pass");
			}else{
				APP_LOGS.info("test case is failed");
				test.log(LogStatus.ERROR, "Enable ansd disable of Airplane mode with phone settings, Unsuccessful");
				sf.fail();
			}
			minWait();	
			clickBackButton(2);
		} catch (org.openqa.selenium.NoSuchElementException e) {
			APP_LOGS.info("No Element found.");
			e.printStackTrace();
			test.log(LogStatus.ERROR, "No Such Element Found");
			sf.fail();
		}
		sf.assertAll();
	}

	public void initiateCall() throws IOException, ParseException {
		/*
		 * Intiate MO call
		 */
		String Uid=JsonFileReaderAndWriter.primaryDevIdReader();
		Runtime.getRuntime().exec("adb -s "+Uid+" shell am start -a android.intent.action.CALL -d tel:"+refNum+"");
	}

	public void validateAppMenuLaunch() throws InterruptedException {
		/*
		 * validate App Launch Menu from Launcher
		 */
		minWait();
		clickBackButton(2);
		aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_HOME);
		minWait();
		test.log(LogStatus.INFO, "App Menu is clicked");
		minWait();
		if(validate_presenceOfElement(Locators_DevSanity.settings, "Settings")) {
			check = true;
			assertTrue(check);
			test.log(LogStatus.INFO, "Validated Launch App Menu From Launcher");
		}
		else {
			Assert.fail();
			test.log(LogStatus.ERROR, "Launch App Menu From Launcher is not Validated");
		}		
	}

	public void validateLaunch_All_Applications() throws InterruptedException, IOException {
		//this method launch all applications one by one and validate

		try {
			launch_an_app("browser");
			customWait(3000);
			validate_launchofApp(Locators_DevSanity.DefaultUrlTxt, "Browser");

			launch_an_app("messaging");
			validate_launchofApp(Locators_DevSanity.messaging_Title, "Messaging");

			launch_an_app("contacts");
			if(isElementExist(Locators_DevSanity.contact_FindContacts)) {
				validate_launchofApp(Locators_DevSanity.contact_FindContacts, "Contacts");
			}
			else if(isElementExist(Locators_DevSanity.contacts)) {
				validate_launchofApp(Locators_DevSanity.contacts, "Contacts");
			}

			launch_an_app("phone");
			validate_launchofApp(Locators_DevSanity.phone_find, "Phone");

			launch_an_app("scout");
			validate_launchofApp(Locators_DevSanity.SetUp_Scout, "Sonim Scout");

			launch_an_app("camera");
			switchCamera();


			launch_an_app("gallery");
			validate_launchofApp(Locators_DevSanity.GalleryAlbums, "Gallery");

			launch_an_app("calendar");
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_MENU);
			validate_launchofApp(Locators_DevSanity.Calender_NewEvent, "Calendar");

			launch_an_app("settings");
			validate_launchofApp(Locators_DevSanity.settingsIcon, "Settings");

			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_HOME);
			minWait();
			clickBtn(Locators_BaseUtil.applications_icon);
			validate_launchofApp(Locators_DevSanity.music_icon, "Applications");

			launch_an_app("music");
			validate_launchofApp(Locators_DevSanity.artists_music_player, "Music");

			launch_an_app("soundRecorder");
			validate_launchofApp(Locators_DevSanity.sound_Recorder, "Sound Recorder");

			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);

		} catch (NoSuchElementException e) {
			e.printStackTrace();
			APP_LOGS.info("File failed to open");
			test.log(LogStatus.ERROR,"No Such Element Found");
			Assert.fail();
		}						
	}

	public void validate_launchofApp(WebElement element, String elementName) throws InterruptedException {
		minWait();
		SoftAssert Sa = new SoftAssert();
		try {
			customWait(6000);
			if (isElementExist(element)) {
				check = true;
				APP_LOGS.info(elementName + " is found succesfully");
				test.log(LogStatus.INFO, elementName + " Application Successfully Launched ");
				Sa.assertTrue(check, "Tc Passed");

			} else {
				check = false;
				APP_LOGS.info(elementName + " is not found");
				test.log(LogStatus.ERROR, elementName + " Application is not Launched");
				Sa.fail();
			}
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			APP_LOGS.info("No Such Element Found");
			test.log(LogStatus.ERROR,"No Such Element Found");
			Sa.fail();
		}
		Sa.assertAll();
	}

	public void switchCamera() throws InterruptedException {
		try {
			if(isElementExist(Locators_DevSanity.camera_Icon)) {
				validate_launchofApp(Locators_DevSanity.camera_Icon, "Camera");
			}
			else if (isElementExist(Locators_DevSanity.videocameraIcon)) {
				validate_launchofApp(Locators_DevSanity.videocameraIcon, "Camera");
			}
		} catch (NoSuchElementException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Assert.fail();
		}					
	}

	public void enableData() throws InterruptedException {
		/*
		 * enable data
		 */
		try {
			launch_an_app("settings");
			minWait();
			clickBtn(Locators_DevSanity.networkAndInternet);
			minWait();
			if(isElementExist(Locators_DevSanity.MobileNetwrks_oreo)) {
				minWait();
				clickBtn(Locators_DevSanity.MobileNetwrks_oreo);
				APP_LOGS.info("Selected Mobile Networks");
				minWait();				
				enableSwitch(Locators_DevSanity.enabled_Data_Oreo,Locators_DevSanity.disabled_Data_Oreo,Locators_DevSanity.switch_widget);
				minWait();
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {
			APP_LOGS.info("No Element found.");
			Assert.fail();
			minWait();	
		}
	}

	public void dailCallUsingDailPad(String dailNum) throws InterruptedException, IOException {

		try {
			dailNumber(dailNum);

			try {
				for(int j=1;j<=100;j++){
					Process child = null;
					if (r_b_No.contains("8A.")) {
						child=Runtime.getRuntime().exec("adb -s "+r_Id+" shell service call telecom 27");
					} else if(r_b_No.contains("5SA.")) {
						child=Runtime.getRuntime().exec("adb -s "+r_Id+" shell service call telecom 30");
					}					
					InputStream lsOut = child.getInputStream();
					InputStreamReader r = new InputStreamReader(lsOut);
					BufferedReader in = new BufferedReader(r);
					String  value=in.readLine();
					System.out.println(value);
					if(value.contains("00000001")) {
						System.out.println("Phone is ringing so accepting call.");
						Thread.sleep(3000);
						Runtime.getRuntime().exec("adb -s "+r_Id+" shell input keyevent 5");
						break;
					}else {
						continue;
					}
				}
			}catch(Exception e) {
				Thread.sleep(2000);
				Runtime.getRuntime().exec("adb -s "+r_Id+" shell input keyevent 5");
				Thread.sleep(2000);
			}

			APP_LOGS.info("Number dailed is: "+dailNum);
		}catch (NoSuchElementException e) {
			APP_LOGS.info("Selected Page is not displayed.");
			e.printStackTrace();
		}
	}

	public void endcall() throws InterruptedException {
		/*
		 * disconnect call
		 */
		Thread.sleep(5000);
		aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_ENDCALL);
		Thread.sleep(3000); 
	}

	public void validate_Num_In_CallLog(String expectedNum,String callType) throws InterruptedException {
		/* 
		 * This Method will Validate the Dailled Number in Call Log history.
		 * Precondition : User should be in Dialler Home Page.
		 */
		SoftAssert sf = new SoftAssert();
		try {
			minWait();
			String callLogNum = Locators_DevSanity.first_No_In_CallLog.getText().replaceAll(" ", "");
			System.out.println("callLogNum "+callLogNum);
			System.out.println("expectedNum "+expectedNum);
			if (callLogNum.contains(expectedNum)) {
				check=true;
				APP_LOGS.info("Dailed Number sucessfully Recorded in Call log");
				sf.assertTrue(check, "TestCase Valiation is PASS");
				test.log(LogStatus.PASS, callType+" Number Validated in the Call Logs.");
			}else {
				APP_LOGS.info("Dailed Number NOT Recorded in Call log");
				sf.fail();
				test.log(LogStatus.FAIL,callType+" Number NOT found in Call Logs.");
			}
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			sf.fail();
		}		
		sf.assertAll();
	}

	public void dailNumber(String dailNum) throws IOException, InterruptedException {
		minWait();
		launch_an_app("phone");
		customWait(2000);
		Runtime.getRuntime().exec("adb -s "+p_Id+" shell am start -a android.intent.action.CALL -d tel:"+refNum);
	}

	public void endCall_RefDevice() {
		try {
			minWait();
			Runtime.getRuntime().exec("adb -s "+r_Id+" shell input keyevent 6");
			Thread.sleep(1000);
		} catch (Exception e) {
		}
	}

	public void make_Call_from_RefDev() throws InterruptedException, IOException {

		try {
			minWait();
			Runtime.getRuntime().exec("adb -s "+r_Id+" shell am start -a android.intent.action.CALL -d tel:"+pryNum);
			minWait();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void recieve_Call_PrimaryDev() throws IOException, InterruptedException {

		try {
			for(int j=1;j<=100;j++){
				Process child = null;
				Process version = null;
				if (p_b_No.contains("8A.")) {
					System.out.println("XP8");
					child=Runtime.getRuntime().exec("adb -s "+p_Id+" shell service call telecom 27");

				} else if(p_b_No.contains("5SA.")) {
					System.out.println("XP5");

					version = Runtime.getRuntime().exec("adb -s "+p_Id+" shell getprop ro.build.version.release");
					InputStream lsOut1 = version.getInputStream();
					InputStreamReader r1 = new InputStreamReader(lsOut1);
					BufferedReader in1 = new BufferedReader(r1);
					String  value1=in1.readLine();
					System.out.println(value1);
					if(value1.contains("8.1")) {
						child=Runtime.getRuntime().exec("adb -s "+p_Id+" shell service call telecom 30");
					}else {
						child=Runtime.getRuntime().exec("adb -s "+p_Id+" shell service call telecom 28");
					}
				}					
				InputStream lsOut = child.getInputStream();
				InputStreamReader r = new InputStreamReader(lsOut);
				BufferedReader in = new BufferedReader(r);
				String  value=in.readLine();
				System.out.println(value);
				if(value.contains("00000001")) {
					System.out.println("Phone is ringing so accepting call.");

					Runtime.getRuntime().exec("adb -s "+p_Id+" shell input keyevent 5");
					break;
				}else {
					continue;
				}
			}
		}catch(Exception e) {
			Thread.sleep(2000);
			Runtime.getRuntime().exec("adb -s "+p_Id+" shell input keyevent 5");
			Thread.sleep(2000);
		}
	}

	public void endCall_PIDDevice() {
		try {
			minWait();
			Runtime.getRuntime().exec("adb -s "+p_Id+" shell input keyevent 6");
			Thread.sleep(1000);
		} catch (Exception e) {
		}
	}

	public void inCallFunctionality() throws InterruptedException, IOException, AWTException, ParseException
	{
		/*
		 * Validates in call functionality
		 */
		try {
			minWait();
			initiateCall();
			customWait(3000);
			reciveCallInRefDevice(refNum);
			minWait();
			speakerStatus();
			minWait();
			muteFunctionStatus();
			minWait();
			holdFunctionStatus();
			minWait();
			clickEndCallButton();
		}catch (NoSuchElementException e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "Element Not Found");
		}
	}

	public void reciveCallInRefDevice(String refNum) throws InterruptedException, IOException {
		/*
		 * Receive Call in Reference device
		 */
		try {
			if (!isElementExist(Locators_DevSanity.turnOff_Airplane_PopUp)) {
				try {
					for(int j=1;j<=100;j++){
						Process child = null;
						if (r_b_No.contains("8A.")) {
							child=Runtime.getRuntime().exec("adb -s "+r_Id+" shell service call telecom 27");
						} else if(r_b_No.contains("5SA.")) {
							child=Runtime.getRuntime().exec("adb -s "+r_Id+" shell service call telecom 28");
						}     
						InputStream lsOut = child.getInputStream();
						InputStreamReader r = new InputStreamReader(lsOut);
						BufferedReader in = new BufferedReader(r);
						String  value=in.readLine();
						System.out.println(value);
						if(value.contains("00000001")) {
							System.out.println("Phone is ringing so accepting call.");
							Thread.sleep(3000);
							Runtime.getRuntime().exec("adb -s "+r_Id+" shell input keyevent 5");
							break;
						}else {
							continue;
						}
					}
				}catch(Exception e) {
					Thread.sleep(2000);
					Runtime.getRuntime().exec("adb -s "+r_Id+" shell input keyevent 5");
					Thread.sleep(2000);
				}
			}
			APP_LOGS.info("Number dailed is: "+refNum);
		}catch (NoSuchElementException e) {
			APP_LOGS.info("Selected Page is not displayed.");
			e.printStackTrace();
		}
	}

	public void speakerStatus() throws InterruptedException, IOException, AWTException, ParseException
	{
		/*
		 * Validates speaker status
		 */
		try {
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_UP);
			customWait(2000);

			validateSpeaker("Speaker On", "CallAudioRouteStateMachine: setSystemAudioState: changing from [AudioState isMuted: false, route: EARPIECE, supportedRouteMask: EARPIECE, SPEAKER] to [AudioState isMuted: false, route: SPEAKER, supportedRouteMask: EARPIECE, SPEAKER]: ICA.sAR->CARSM.pM_USER_SWITCH_SPEAKER",
					"CallAudioRouteStateMachine: setSystemAudioState: changing from [AudioState isMuted: false, route: WIRED_HEADSET, supportedRouteMask: WIRED_HEADSET, SPEAKER] to [AudioState isMuted: false, route: SPEAKER, supportedRouteMask: WIRED_HEADSET, SPEAKER]: ICA.sAR->CARSM.pM_USER_SWITCH_SPEAKER", "XP5S_Dev_Sanity_08");

			startLog("XP5S_Dev_Sanity_08_1");
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_UP);
			customWait(2000);
			validateSpeaker("Speaker Off", "CallAudioRouteStateMachine: setSystemAudioState: changing from [AudioState isMuted: false, route: SPEAKER, supportedRouteMask: EARPIECE, SPEAKER] to [AudioState isMuted: false, route: EARPIECE, supportedRouteMask: EARPIECE, SPEAKER]: ICA.sAR->CARSM.pM_USER_SWITCH_EARPIECE",
					"CallAudioRouteStateMachine: setSystemAudioState: changing from [AudioState isMuted: false, route: SPEAKER, supportedRouteMask: WIRED_HEADSET, SPEAKER] to [AudioState isMuted: false, route: WIRED_HEADSET, supportedRouteMask: WIRED_HEADSET, SPEAKER]: ICA.sAR->CARSM.pM_USER_SWITCH_HEADSET", "XP5S_Dev_Sanity_08_1");
		}catch (NoSuchElementException e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "Element Not Found");
		}
	}

	public void validateSpeaker(String function,String searchstring,String searchstring1,String filename) throws InterruptedException, IOException
	{
		/*
		 * Validate call history
		 */
		SoftAssert SA = new SoftAssert();
		try {
			if(searchString(searchstring, filename) || searchString(searchstring1, filename))
			{
				check = true;
				APP_LOGS.info("In call functionality "+ function +" verified successfully");
				test.log(LogStatus.INFO, "In call functionality "+ function +" verified successfully");
				SA.assertTrue(check, "In call functionality "+ function +" verified successfully");	
			}else {
				APP_LOGS.info("In call functionality "+ function +" not verified");
				test.log(LogStatus.ERROR, "In call functionality "+ function +" not verified");
				SA.fail("In call functionality "+ function +" not verified");
			}	
		}catch (NoSuchElementException e) {
			e.printStackTrace();
			APP_LOGS.info("Element Not Found");
			test.log(LogStatus.ERROR, "Element Not Found");
			SA.fail();
		}
		SA.assertAll();
	}

	public void muteFunctionStatus() throws InterruptedException, IOException
	{
		/*
		 * Validates mute status
		 */
		try {
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_LEFT);
			customWait(2000);
			validateInCallFunctionality("Mute","Message received: MUTE_ON=3001, arg1=0: ICA.m->CARSM.pM_MUTE_ON");
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_LEFT);
			customWait(2000);
			validateInCallFunctionality("Unmute","Message received: MUTE_OFF=3002, arg1=0: ICA.m->CARSM.pM_MUTE_OFF");
		}catch (NoSuchElementException e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "Element Not Found");
		}
	}

	public void holdFunctionStatus() throws InterruptedException, IOException
	{
		/*
		 * Validates hold status
		 */
		try {
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_RIGHT);
			customWait(2000);
			validateInCallFunctionality("Hold","GsmCdmaConnection: [GsmCdmaConn] update: parent=HOLDING, hasNewParent=false, wasConnectingInOrOut=false, wasHolding=false, isConnectingInOrOut=false, changed=true");
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_RIGHT);
			customWait(2000);
			validateInCallFunctionality("Unhold","GsmCdmaConnection: [GsmCdmaConn] update: parent=ACTIVE, hasNewParent=false, wasConnectingInOrOut=false, wasHolding=true, isConnectingInOrOut=false, changed=true");
		}catch (NoSuchElementException e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "Element Not Found");
		}
	}

	public void clickEndCallButton()
	{
		/*
		 * clicks on End call button 
		 */
		try {
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_ENDCALL);
			minWait();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void validateInCallFunctionality(String function,String searchstring) throws InterruptedException, IOException
	{
		/*
		 * Validate call history
		 */
		SoftAssert SA = new SoftAssert();
		try {
			boolean value = searchString(searchstring, "XP5S_Dev_Sanity_08_1");
			if(value)
			{
				check = true;
				APP_LOGS.info("In call functionality "+ function +" verified successfully");
				test.log(LogStatus.INFO, "In call functionality "+ function +" verified successfully");
				SA.assertTrue(check, "In call functionality "+ function +" verified successfully");	
			}else {
				APP_LOGS.info("In call functionality "+ function +" not verified");
				test.log(LogStatus.ERROR, "In call functionality "+ function +" not verified");
				SA.fail("In call functionality "+ function +" not verified");
			}	
		}catch (NoSuchElementException e) {
			e.printStackTrace();
			APP_LOGS.info("Element Not Found");
			test.log(LogStatus.ERROR, "Element Not Found");
			SA.fail();
		}
		SA.assertAll();
	}

	public void rejectWithSMS() throws InterruptedException, IOException
	{
		/*
		 * reject with SMS
		 */
		SoftAssert SA = new SoftAssert();
		try {
			//Receive call from reference device
			//	waituntilnew(Locators_DevSanity.rejectWithSMSTextCallPage, 10000);
			customWait(10000);
			if(isElementExist(Locators_DevSanity.rejectWithSMSTextCallPage)) {
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_MENU);
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
				minWait();
				clickCenterButton(1);
				validateSentQuickResponse();
			}else {
				APP_LOGS.info("Call not received");
				test.log(LogStatus.INFO, "Call not received");
				SA.fail("Call not received");
			}
		}catch (NoSuchElementException e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "Element Not Found");
		}
		SA.assertAll();
	}

	public void clickCenterButton(int number)
	{
		/*
		 * clicks on back button with iteration as user input
		 */
		try {
			for(int i=0;i<number;i++){
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
				customWait(2000);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void validateSentQuickResponse() throws InterruptedException, IOException
	{
		/*
		 * Validates sent quick response in messaging 
		 */
		SoftAssert SA = new SoftAssert();
		try {
			launch_an_app("messaging");
			clickMenuAndElement(Locators_DevSanity.searchOpt);
			changeToNumberMode_SMS(Locators_DevSanity.searchFld_InMessages);
			inputData(refNum);
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
			clickCenterButton(1);
			if(isElementExist(Locators_DevSanity.quickResponseSent))
			{
				check= true;
				APP_LOGS.info("Rejected call with Quick response succesfully");
				test.log(LogStatus.INFO, "Rejected call with Quick response succesfully");
				SA.assertTrue(check, "Rejected call with Quick response succesfully");	
			}else {
				APP_LOGS.info("Failed to Reject call with Quick response");
				test.log(LogStatus.INFO, "Failed to Reject call with Quick response");
				SA.fail("Failed to Reject call with Quick response");
			}
			clickBackButton(1);
		}catch (NoSuchElementException e) {
			e.printStackTrace();
			APP_LOGS.info("Element Not Found");
			test.log(LogStatus.ERROR, "Element Not Found");
			SA.fail();
		}
		SA.assertAll();
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
		}catch (NoSuchElementException e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "Element Not Found");
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

	public void inputData(String data) throws InterruptedException, IOException
	{
		/*
		 * Search contact with name
		 */
		try
		{
			customWait(2000);
			Runtime.getRuntime().exec("adb -s "+p_Id+" shell input text "+ data);
			customWait(3000);
		}
		catch (NoSuchElementException e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "Element Not Found");
		}
	}

	public void composeMsg(String composedMsg, String newNum) throws InterruptedException, IOException {
		/*
		 * compose Msg with 145 character ie single page and send to new number or saved contact
		 * 
		 */
		SoftAssert Sa = new SoftAssert();
		try {
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_MENU);
			minWait();
			clickBtn(Locators_DevSanity.New_msgOptn);
			minWait();
			customWait(1000);
			changeToNumberMode();
			minWait();
			enterTextToInputField(Locators_DevSanity.Type_NumField, newNum);
			minWait();
			if(isElementExist(Locators_DevSanity.Msg_Contact)) {
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_ENTER);
				minWait();
			}
			customWait(2000);
			enterTextToInputField(Locators_DevSanity.typeMessageOpt, composedMsg);
			customWait(1000);
		} catch (org.openqa.selenium.NoSuchElementException e) {
			APP_LOGS.info("No Element found.");
			e.printStackTrace();
			test.log(LogStatus.ERROR, "No Such Element Found");
			Sa.fail();
		}
		Sa.assertAll();
	}

	public void changeToNumberMode() throws InterruptedException {

		minWait();
		Locators_DevSanity.toField_NewMessage.sendKeys("123");
		customWait(1500);
		String text = Locators_DevSanity.toField_NewMessage.getText();
		System.out.println(text);
		if(!text.equals("123")) {
			for (int i = 0; i < 3; i++) {
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_POUND);
				minWait();
			}
		}
		Locators_DevSanity.toField_NewMessage.clear();
	}

	public void send() throws InterruptedException {
		/*
		 * send sms 
		 */		
		customWait(2000);
		clickMenuAndElement(Locators_DevSanity.msg_send);
		aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);	
	}

	public void validateMsgSent(String str) throws InterruptedException {
		/*
		 * validate sent message to new num and saved contact
		 */
		SoftAssert sf = new SoftAssert();
		try {
			minWait();		
			customWait(8000);
			WebDriverWait wait = new WebDriverWait(aDriver, 40);
			wait.until(ExpectedConditions.visibilityOf(Locators_DevSanity.now_Text));
			minWait();
			if (isElementExist(Locators_DevSanity.now_Text)) {
				check=true;
				APP_LOGS.info("Message "+str+" sucessfully");
				sf.assertTrue(check, "Validation is Pass");
				test.log(LogStatus.PASS, "Message "+str+" successfully");
			} else {
				APP_LOGS.info("Message DIDN'T "+ str);
				sf.fail();
				test.log(LogStatus.ERROR, "Failed to send SMS,Validation Failed ");
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {
			APP_LOGS.info("No Element found.");
			e.printStackTrace();
			test.log(LogStatus.ERROR, " No Such Element Found");
			sf.fail();
		}
		sf.assertAll();
	}

	public void callHistoryDetails() throws InterruptedException, IOException
	{
		/*
		 * validates call history details
		 */
		try {

			//==========outgoing call==========
			dailCallUsingDailPad(refNum);
			customWait(3000);
			endcall();		

			//==========Incoming call==========
			make_Call_from_RefDev();
			customWait(5000);
			recieve_Call_PrimaryDev();
			customWait(5000);
			endCall_RefDevice();

			//==========Missed call===========
			make_Call_from_RefDev();
			customWait(5000);
			endCall_RefDevice();

			//==========declined call=========
			make_Call_from_RefDev();
			customWait(3000);
			waituntilnew(Locators_DevSanity.rejectWithSMSTextCallPage, 5000);
			if(isElementExist(Locators_DevSanity.rejectWithSMSTextCallPage)) {
				//endcall();
				minWait();
				Runtime.getRuntime().exec("adb -s "+p_Id+" shell input keyevent 6");
				customWait(2000);
			}else {
				test.log(LogStatus.ERROR, "Call not recieved to decline");
			}
			customWait(1000);
			endCall_RefDevice();

			customWait(2000);
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_ENTER);
			minWait();
			validateCallHistoryDeclinedAndMissed();
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
			minWait();
			validateCallHistoryIncomingAndOutgoing();
		}catch (NoSuchElementException e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "Element Not Found");
		}
	}

	public void validateCallHistoryDeclinedAndMissed() throws InterruptedException, IOException
	{
		/*
		 * Validate call history
		 */
		SoftAssert SA = new SoftAssert();
		try {
			if(isElementExist(Locators_DevSanity.missedCall) && isElementExist(Locators_DevSanity.declinedCall))
			{
				check = true;
				APP_LOGS.info("Call History with declined call and missed call verified sucessfully");
				test.log(LogStatus.INFO, "Call History with declined call and missed call verified sucessfully");
				SA.assertTrue(check, "Call History with declined call and missed call verified sucessfully");	
			}else {
				APP_LOGS.info("Call History with declined call and missed call not verified");
				test.log(LogStatus.ERROR, "Call History with declined call and missed call not verified");
				SA.fail("Call History with declined call and missed call not verified");
			}	
		}catch (NoSuchElementException e) {
			e.printStackTrace();
			APP_LOGS.info("Element Not Found");
			test.log(LogStatus.ERROR, "Element Not Found");
			SA.fail();
		}
		SA.assertAll();
	}

	public void validateCallHistoryIncomingAndOutgoing() throws InterruptedException, IOException
	{
		/*
		 * Validate call history
		 */
		SoftAssert SA = new SoftAssert();
		try {
			if(isElementExist(Locators_DevSanity.outgoingCall)&& isElementExist(Locators_DevSanity.incomingCall))
			{
				check = true;
				APP_LOGS.info("Call History with incoming call and outgoing call verified sucessfully");
				test.log(LogStatus.INFO, "Call History with incoming call and outgoing call verified sucessfully");
				SA.assertTrue(check, "Call History with incoming call and outgoing call verified sucessfully");	
			}else {
				APP_LOGS.info("Call History with incoming call and outgoing call not verified");
				test.log(LogStatus.ERROR, "Call History with incoming call and outgoing call not verified");
				SA.fail("Call History with incoming call and outgoing call not verified");
			}	
		}catch (NoSuchElementException e) {
			e.printStackTrace();
			APP_LOGS.info("Element Not Found");
			test.log(LogStatus.ERROR, "Element Not Found");
			SA.fail();
		}
		SA.assertAll();
	}

	public void clearCallLogs() throws InterruptedException {
		try {
			if(isElementExist(Locators_DevSanity.dailedNum)) {
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_MENU);
				APP_LOGS.info("Menu is clicked");
				minWait();
				for(int i=1; i<=10; i++) {
					if(isElementExist(Locators_DevSanity.phone_DeleteCallLog)) {
						clickBtn(Locators_DevSanity.phone_DeleteCallLog);
						minWait();
						clickBtn(Locators_DevSanity.phone_ClearOptn);
						APP_LOGS.info(" Call logs cleared");
						minWait();
						break;
					}
					else {
						customWait(200);
						aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
						continue;
					}
				}
			}
			//			aDriver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().className(\"android.widget.ScrollView\")).scrollIntoView(new UiSelector().text(\"Clear all call log\"))");  
		} catch (NoSuchElementException e) {
			APP_LOGS.info("No Element found.");
			e.printStackTrace();
			test.log(LogStatus.ERROR, "No Such Element Found");
			Assert.fail();
		}
	}

	public void delete_All_Threads() throws InterruptedException {
		/*
		 * Method is used to delete All the SMS Threads.
		 */
		SoftAssert sf = new SoftAssert();
		try {
			minWait();
			while (true) {
				if (isElementExist(Locators_DevSanity.first_sms_Thread)||isElementExist(Locators_DevSanity.first_sms_Thread1)) {
					minWait();
					aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
					minWait();
					aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_CLEAR);
					minWait();
					clickBtn(Locators_DevSanity.DELETE);
				} else {
					Reporter.log("No Threads Found");
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			sf.fail();
			test.log(LogStatus.ERROR, "Error in Deleting the Messages.");
		}
		sf.assertAll();
	}

	public void sendSMS_fromRefDevice() {
		try {
			minWait();
			if (r_b_No.contains("8A.")) {
				if (r_b_No.contains("-10.")||r_b_No.contains("-30.")) {
					minWait();
					Runtime.getRuntime().exec("adb -s "+r_Id+" shell input keyevent 3");
					minWait();
					Runtime.getRuntime().exec("adb shell am force-stop com.android.mms");
					minWait();
					Runtime.getRuntime().exec("adb -s "+r_Id+" shell am start -a android.intent.action.SENDTO -d sms:"+pryNum+" --es sms_body \"AutomationMessagee\" --ez exit_on_sent true");
					customWait(2500);
					Runtime.getRuntime().exec("adb -s "+r_Id+" shell input tap 918 1699");
					minWait();
					Runtime.getRuntime().exec("adb -s "+r_Id+" shell input tap 918 952");
					minWait();
				} else if(r_b_No.contains("-11.")||r_b_No.contains("-12.")||r_b_No.contains("-18.")||r_b_No.contains("-26.")||r_b_No.contains("-29.")){
					minWait();
					Runtime.getRuntime().exec("adb -s "+r_Id+" shell input keyevent 3");
					minWait();
					Runtime.getRuntime().exec("adb -s "+r_Id+" shell am force-stop com.google.android.apps.messaging");
					minWait();
					Runtime.getRuntime().exec("adb -s "+r_Id+" shell am start -a android.intent.action.SENDTO -d sms:"+pryNum+" --es sms_body \"AutomationMessagee\" --ez exit_on_sent true");
					customWait(2500);
					Runtime.getRuntime().exec("adb -s "+r_Id+" shell input tap 930 1756");
					minWait();
					Runtime.getRuntime().exec("adb -s "+r_Id+" shell input tap 930 976");
					minWait();
				}
			} else if (r_b_No.contains("5SA.")){
				minWait();
				Runtime.getRuntime().exec("adb -s "+r_Id+" shell input keyevent 3");
				minWait();
				Runtime.getRuntime().exec("adb -s "+r_Id+" shell am force-stop com.android.mms");
				minWait();
				Runtime.getRuntime().exec("adb -s "+r_Id+" shell am start -a android.intent.action.SENDTO -d sms:"+pryNum+" --es sms_body \"AutomationMessage.\" --ez exit_on_sent true");
				customWait(2500);
				Runtime.getRuntime().exec("adb -s "+r_Id+" shell input keyevent 66");
				minWait();
			}

		} catch (Exception e) {

		}
	}

	public void validateMsgDraft(String ContactNum,String str) throws InterruptedException {
		/*
		 * validate saved as draft message to new num and saved contact
		 */
		SoftAssert sf = new SoftAssert();
		try {
			minWait();		
			customWait(8000);
			WebDriverWait wait = new WebDriverWait(aDriver, 40);
			wait.until(ExpectedConditions.visibilityOf(Locators_DevSanity.draft_Text));
			minWait();
			if (isElementExist(Locators_DevSanity.draft_Text)) {
				check=true;
				APP_LOGS.info(str+" saved as Draft is Successfully");
				sf.assertTrue(check, "Validation is Pass");
				test.log(LogStatus.PASS, "Message saved as Draft: Test case status is Passed");
			} else {
				APP_LOGS.info("Message DIDN'T ");
				sf.fail();
				test.log(LogStatus.ERROR, str+" didn't saved as Draft , Validation Failed ");
			}

		} catch (org.openqa.selenium.NoSuchElementException e) {
			APP_LOGS.info("No Element found.");
			e.printStackTrace();
			test.log(LogStatus.ERROR, " No Such Element Found");
			sf.fail();
		}
		sf.assertAll();
	}

	public void deleteMsg() throws InterruptedException {
		/*
		 * select sms if present and delete
		 */
		for(int i=1; i<=20; i++) {					
			if (isElementExist(Locators_DevSanity.MsgList)) {
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_CLEAR);
				minWait();
				clickBtn(Locators_DevSanity.DELETE);
				minWait();
				continue;
			}
			else {
				break;
			}
		}
	}

	public void validateDeleteSMS(String str) throws InterruptedException {
		//validate after deletion

		if (validate_presenceOfElement(Locators_DevSanity.noConversations,"NoConversations")) {
			check = true;
			test.log(LogStatus.INFO, "Able to delete " +str +" verified");
			Assert.assertTrue(check);
		}
		else {
			test.log(LogStatus.ERROR, str+" is not able to delete");
			Assert.fail();
		}
		clickBackButton(1);
	}

	public void navigateTo_NewMessage() throws InterruptedException, IOException {
		SoftAssert sf = new SoftAssert();
		try {
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_MENU);
			minWait();
			clickBtn(Locators_DevSanity.new_Message);
			minWait();
		} catch (Exception e) {
			e.printStackTrace();
			sf.fail();
			test.log(LogStatus.ERROR, "Didn't navigated to New Message Window");
		}
		sf.assertAll();
	}

	public void navigateAttachOthers() throws InterruptedException {
		/* Navigates to */
		try {
			minWait();
			aDriver.findElement(By.xpath("//android.widget.EditText[@index='0']")).click();
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_MENU);
			minWait();
			clickBtn(Locators_DevSanity.attachOthers);
			minWait();
		} catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "Error in navigating to 'Attach others' Option");
		}
	}

	public void clickOnAttach_Options(AndroidElement optionToClick) throws InterruptedException {
		/* Clicks on Capture Picture in the Attach Others option.*/
		try {
			minWait();		
			for (int i = 0; i < 10; i++) {
				if (isElementExist(optionToClick)) {
					minWait();
					clickBtn(optionToClick);
					minWait();
					break;
				} else {
					aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
					Thread.sleep(500);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "Error in clicking Attach Option.");
		}
	}

	public void capturePic_ADD() throws InterruptedException, IOException {
		/* Captures the image and adds to the Message (MMS)
		 * Precondition : User should navigate to "Attach others" Option
		 */
		try {
			customWait(3000);
			clickBtn(Locators_DevSanity.photoCaptureIcon);
			customWait(2000);
			clickBtn(Locators_DevSanity.OK);
			minWait();
		} catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "Error in adding Picture to MMS");
		}
	}

	public void validate_AnyAdded_Attachment(AndroidElement attachmentToValidate) throws InterruptedException {

		SoftAssert sf = new SoftAssert();
		minWait();
		if (isElementExist(attachmentToValidate)) {
			check=true;
			APP_LOGS.info("Attachment added Successfully.");
			sf.assertTrue(check, "Attachment added Successfully, validation PASS.");
		} else {
			sf.fail();
			test.log(LogStatus.ERROR, "Error in adding the Attachment to SMS.");
		}
		sf.assertAll();		
	}

	public void enter_ToField(String cellNo) throws InterruptedException {
		SoftAssert sf = new SoftAssert();
		try {
			changeToNumberMode();
			minWait();
			enterTextToInputField(Locators_DevSanity.toField_NewMessage, cellNo);
			minWait();
		} catch (Exception e) {
			e.printStackTrace();
			sf.fail();
			test.log(LogStatus.ERROR, "Error in entering number to ToField");
		}
		sf.assertAll();
	}

	public void type_Message(String typeMessage) throws InterruptedException {
		SoftAssert sf = new SoftAssert();
		try {
			minWait();
			enterTextToInputField(Locators_DevSanity.type_Message, typeMessage);
		} catch (Exception e) {
			e.printStackTrace();
			sf.fail();
			test.log(LogStatus.ERROR, "Error in Typing a Message.");
		}
		sf.assertAll();
	}

	public void send_SMS() throws InterruptedException {
		/*	
		 * 	This method is used to SEND SMS
		 * Precondition : User should create new SMS 
		 */
		SoftAssert sf = new SoftAssert();
		try {
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_MENU);
			minWait();
			clickBtn(Locators_DevSanity.send);
			minWait();
		} catch (Exception e) {
			e.printStackTrace();
			sf.fail();
			test.log(LogStatus.ERROR, "Error in Sending the Message.");
		}
		sf.assertAll();
	}

	public void disableCellularData() throws InterruptedException {
		try {
			clickBtn(Locators_DevSanity.networkAndInternet);
			minWait();
			if(isElementExist(Locators_DevSanity.MobileNetwrks_oreo)) {
				minWait();
				clickBtn(Locators_DevSanity.MobileNetwrks_oreo);
				APP_LOGS.info("Selected Mobile Networks");
				minWait();
				disableFeature(Locators_DevSanity.enabled_Data_Oreo,Locators_DevSanity.disabled_Data_Oreo);
				minWait();
				clickBtn(Locators_DevSanity.okBtn);
				minWait();
			}
			else {
				clickBtn(Locators_DevSanity.dataUsageOptn);
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
				minWait();
				disableFeature(Locators_DevSanity.cellular_DataON,Locators_DevSanity.cellular_DataOFF);
				minWait();
				clickBtn(Locators_DevSanity.okBtn);
				minWait();
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {
			APP_LOGS.info("No Element found.");
			Assert.fail();
			minWait();	
		}

	}

	public void ConnectSecuredWifi() throws InterruptedException, IOException {
		/*
		 * Connect wifi with entering passkey
		 */
		try {
			clickBtn(Locators_DevSanity.networkAndInternet);
			customWait(2000);
			navigateToWifiOption();
			minWait();
			if(isElementExist(Locators_DevSanity.disabled)) {
				enableFeature(Locators_DevSanity.enabled,Locators_DevSanity.disabled,Locators_DevSanity.switch_Title);
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);  
				customWait(3000);
			}  
			else {
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);       
			}
			customWait(3000);
			String getSummary = Locators_DevSanity.summaryWIFI_oreo.getText();
			System.out.println(getSummary);
			minWait();
			if(getSummary.contains("Not connected")) {

				System.out.println("check");
				minWait();
				// aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
				minWait();
				clickCenterButton(2);
				customWait(4000);
				scrollToText("IDCSONWAP");
				// selectIDcwifi();      
				minWait();
				if(isElementExist(Locators_DevSanity.wifi_IDC_ForgetBtn)) {
					System.out.println("check0");
					clickBtn(Locators_DevSanity.wifi_IDC_ForgetBtn);
					minWait();
					scrollToText("IDCSONWAP");
					//selectIDcwifi();    
				}
				clickBtn(Locators_DevSanity.wifi_show_Pswd);
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_UP);
				customWait(1000);

				changeToNumberMode_SMS(Locators_DevSanity.wifi_IDC_Psswd);
				customWait(9000);
				if(isElementExist(Locators_DevSanity.wifi_IDC)) {
					Runtime.getRuntime().exec("adb -s "+p_Id+" shell input text 1");
					minWait();
					Runtime.getRuntime().exec("adb -s "+p_Id+" shell input text dcS");
					minWait();
					Runtime.getRuntime().exec("adb -s "+p_Id+" shell input text 0");
					minWait();
					Runtime.getRuntime().exec("adb -s "+p_Id+" shell input text n");
					minWait();
					Runtime.getRuntime().exec("adb -s "+p_Id+" shell input text 1");
					minWait();
					Runtime.getRuntime().exec("adb -s "+p_Id+" shell input text md");
					minWait();
					Runtime.getRuntime().exec("adb -s "+p_Id+" shell input text 0");
					minWait();
					Runtime.getRuntime().exec("adb -s "+p_Id+" shell input text tc");
					minWait();
					Runtime.getRuntime().exec("adb -s "+p_Id+" shell input text 0");
					minWait();
					Runtime.getRuntime().exec("adb -s "+p_Id+" shell input text MbLr");
					minWait();
				}

				else if(isElementExist(Locators_DevSanity.wifi_Cannada)) {
					Runtime.getRuntime().exec("adb -s "+p_Id+" shell input text rrxfo68837");
				}

				else if(isElementExist(Locators_DevSanity.wifi_Dellas)) {
					Runtime.getRuntime().exec("adb -s "+p_Id+" shell input text yellowbook143");
				}

				minWait();
				customWait(1000);
				String psswrd = Locators_DevSanity.wifi_IDC_Psswd.getText();
				System.out.println(psswrd);
				clickBtn(Locators_DevSanity.wifi_IDC_ConnectBtn);
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

	public boolean scrollToText(String text) {
		/*
		  Method used to select an element on the page by scrolling the Scroll View/List View
		 */

		boolean check = false;
		try {  
			String scrollable = "new UiScrollable(new UiSelector().scrollable(true))";
			String textElement = ".scrollIntoView(new UiSelector().text(\""+ text +"\"))";
			aDriver.findElementByAndroidUIAutomator(scrollable+textElement).click();
			APP_LOGS.info("Searched application is found sucessfully : ");
			check = true;
			return check;
		}
		catch(Exception e) {
			return check;
		}
	}


	public void validateDisableEnableWiFi() throws InterruptedException, IOException {
		/*
		 * disable enable wiFi
		 */
		SoftAssert sf = new SoftAssert();
		try {
			launch_an_app("settings");
			minWait();
			clickBtn(Locators_DevSanity.networkAndInternet);
			minWait();
			navigateToWifiOption();
			minWait();
			customWait(2000);
			disableFeature(Locators_DevSanity.enabled,Locators_DevSanity.disabled);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			launch_an_app("browser");
			customWait(4000);
			if(isElementExist(Locators_DevSanity.NoNewtwrkErroeMsg)||isElementExist(Locators_DevSanity.webpageNotavailable)) {
				check1 =true;
				APP_LOGS.info("check1: "+check1);
				APP_LOGS.info("Disable the WiFi is sucessful");
				test.log(LogStatus.INFO, "Disable the WiFi is sucessful");
			}else{
				APP_LOGS.info("check1: "+check1);
				APP_LOGS.info("Disable the WiFi is not sucessful");
				test.log(LogStatus.FAIL," Disable the WiFi is not sucessful");
			}
			minWait();	
			clickBackButton(2);

			minWait();
			launch_an_app("settings");
			minWait();
			clickBtn(Locators_DevSanity.networkAndInternet);
			minWait();
			navigateToWifiOption();
			minWait();

			enableFeature(Locators_DevSanity.enabled,Locators_DevSanity.disabled,Locators_DevSanity.switch_Title);
			minWait();
			customWait(4000);
			clearRecentApps();
			launch_an_app("browser");
			customWait(4000);
			enterUrl("www.google.com");
			customWait(4000);
			if((isElementExist(Locators_DevSanity.SearchIcon_Google_Oreo))||(isElementExist(Locators_DevSanity.Searchenter_Google_Oreo))) 
			{
				check2 =true;
				APP_LOGS.info("check2: "+check2);
				APP_LOGS.info("Enable the WiFi connection is sucessful");
				test.log(LogStatus.INFO, "Enable the WiFi connection is sucessful");
			}else{
				APP_LOGS.info("check2: "+check2);
				APP_LOGS.info("Enable WiFi connection is not sucessful");
				test.log(LogStatus.INFO, "Enable WiFi connection is not sucessful");
			}

			if((check1) && (check2)) {
				check = true;
				APP_LOGS.info(" Turn ON and OFF feature is verified");
				test.log(LogStatus.INFO, " Browsing with WiFi feature is verified");
				sf.assertTrue(check, " ");
			}
			else {
				APP_LOGS.info(" check1"+check1+"check2"+check2);
				test.log(LogStatus.ERROR, "No Such Element Found");
				APP_LOGS.info(" Browsing with WiFi  is not verified");
				sf.fail();
			}
			minWait();	
			clickBackButton(2);	
		} catch (org.openqa.selenium.NoSuchElementException e) {
			APP_LOGS.info("No Element found.");
			sf.fail();
		}
		sf.assertAll();
	}


	public void enterUrl(String newurl) throws InterruptedException {
		/*
		 * enter url when data is enabled
		 */
		try {
			minWait();
			clickBtn(Locators_DevSanity.DefaultUrlTxt);
			enterTextToInputField(Locators_DevSanity.DefaultUrlTxt,newurl);
			APP_LOGS.info(" URl entered sucessfully.");
			minWait();
			minWait();	 
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_ENTER);
			minWait();
			APP_LOGS.info(" Search click is sucessful."); 
		} catch (org.openqa.selenium.NoSuchElementException e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "No Such Element Found");
			Assert.fail();
		}
	}

	public void selectQuickSettingPanel() throws InterruptedException {
		/*
		 * Select quick Setting panel
		 */
		try {
			minWait();	
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_HOME);
			minWait();	
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			minWait();	
			for(int i=1; i<=8; i++) {
				if(isElementExist(Locators_DevSanity.quickSettings)|| isElementExist(Locators_DevSanity.quick_Toggle)) {
					APP_LOGS.info("In Quick Settings");
					minWait();
					break;					
				} else {
					aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_LEFT);
					continue;					
				}	
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {
			APP_LOGS.info("More Option is not displayed.");
			test.log(LogStatus.ERROR, "No Such Element Found");
			minWait();	
			Assert.fail();
		}

	}

	public void validateEnableDisable(WebElement app,String str,WebElement enabled,WebElement disabled,WebElement element) throws InterruptedException {
		/*
		 * validate enabling and disabling the wifi, bluetooth, and airoplane mode.
		 */
		SoftAssert sf = new SoftAssert();
		try {
			minWait();	
			clickBtn(app);
			customWait(3000);
			if (!isElementExist(enabled)) {
				minWait();
				clickBtn(disabled);
				APP_LOGS.info("Switch is Enabled");                     
				minWait();
			}
			/*aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_UP);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);*/
			customWait(2000);
			String get_enable = element.getText();	
			System.out.println(get_enable);
			minWait();	
			if(get_enable.equalsIgnoreCase("ON")) {
				check1 = true;
				APP_LOGS.info("enabled is sucessful");
			}minWait();	
			clickBtn(app);	
			if (!isElementExist(disabled)) {
				minWait();
				clickBtn(enabled);
				APP_LOGS.info("Switch is Disabled");                     
				minWait();
			}
			minWait();	
			String get_enable2 = element.getText();	
			System.out.println(get_enable2);
			minWait();	
			if(get_enable2.equalsIgnoreCase("OFF")) {
				minWait();	
				check2 = true;
				System.out.println("check2");
				APP_LOGS.info("Disabled is sucessful");
			}
			if((check1)&&(check2)){
				check= true;
				APP_LOGS.info(" Enabled and disabled with quick settings.");
				test.log(LogStatus.INFO, str + " Enabled and disabled with quick settings.");
				sf.assertTrue(check, "Validation is pass");
			}else{
				APP_LOGS.info("test case is failed");
				test.log(LogStatus.ERROR, " Validation Failed, Enable and disable with quick settings.");
				sf.fail();
			}
			minWait();	
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
		} catch (org.openqa.selenium.NoSuchElementException e) {
			APP_LOGS.info("No Element found.");
			e.printStackTrace();
			test.log(LogStatus.ERROR, "No Such Element Found");
			sf.fail();			
		}
		sf.assertAll();
	}

	public void disableWifi() throws InterruptedException, IOException
	{
		try {
			launch_an_app("settings");
			minWait();
			clickBtn(Locators_DevSanity.networkAndInternet);
			minWait();
			navigateToWifiOption();
			customWait(2000);
			disableFeature(Locators_DevSanity.enabled,Locators_DevSanity.disabled);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "Element Not Found");
		}
	}

	public void validateBrowseInternet() throws InterruptedException {
		/*
		 * Validate while browsing google.com
		 */
		SoftAssert sf= new SoftAssert();
		try {
			customWait(2000);
			enterUrl("www.Google.com");
			customWait(10000);
			if((isElementExist(Locators_DevSanity.SearchIcon_Google_Oreo))||(isElementExist(Locators_DevSanity.Searchenter_Google_Oreo))) {
				check =true;
				APP_LOGS.info("check: "+check);
				APP_LOGS.info("Browser is Launched sucessfully");
				test.log(LogStatus.INFO, "Browsing is Launched and Surf sucessfully");
				sf.assertTrue(check, "Validation is Pass");
			}
			else{
				APP_LOGS.info("check: "+check);
				APP_LOGS.info("Browse internet is not sucessful");
				test.log(LogStatus.INFO, "Failed to launch browser and surf");
				sf.fail();
			}minWait();	
		} catch (org.openqa.selenium.NoSuchElementException e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "No Such Element Found");
			sf.fail();
		}
		sf.assertAll();
	}

	public void validateDisableEnableBT() throws InterruptedException {
		/*
		 * disable enable wiFi
		 */
		SoftAssert S1 = new SoftAssert();
		try {
			clickBtn(Locators_DevSanity.connectedDevices_oreo);
			minWait();
			clickBtn(Locators_DevSanity.Bluetooth);
			minWait();
			enable(Locators_DevSanity.enabled,Locators_DevSanity.disabled,Locators_DevSanity.switch_Title);
			customWait(2000);
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
			minWait();
			if(isElementExist(Locators_DevSanity.enabled)) {
				check1 = true;
				test.log(LogStatus.INFO, "Enable BT feature is verified");
				APP_LOGS.info(" check1");
			}
			customWait(2000);
			disableFeature(Locators_DevSanity.enabled,Locators_DevSanity.disabled);
			if(isElementExist(Locators_DevSanity.disabled)) {
				check2 = true;
				test.log(LogStatus.INFO, "Disable BT feature is verified");
				APP_LOGS.info(" ");
			}
			customWait(2000);
			if((check1) && (check2)) {
				check = true;
				APP_LOGS.info(" Turn ON and OFF feature is verified");
				test.log(LogStatus.INFO, "Enable and disable BT feature is verified");
				S1.assertTrue(check, " ");
			}
			else {
				APP_LOGS.info(" check1"+check1+"check2"+check2);
				test.log(LogStatus.ERROR, "Enable and Disable BT feature is not verified");
				APP_LOGS.info("Enable and Disable BT feature is not verified");
				S1.fail();
			}
		} catch (NoSuchElementException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			S1.fail();
		}
		S1.assertAll();
	}

	public void enable(WebElement enablebtn, WebElement disablebtn, WebElement switchtitle) throws InterruptedException {
		/*
		 * enable BT
		 */
		try {
			minWait();
			if(isElementExist(switchtitle)) {
				if (!isElementExist(enablebtn)) {
					minWait();
					clickBtn(disablebtn);
					APP_LOGS.info("Feature is Enabled");                     
					minWait();
				}
			}
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "No Such Element Found");
			Assert.fail();
		}
	}

	public void enable_BT_RefDevice() throws InterruptedException, IOException {

		try {
			minWait();
			Runtime.getRuntime().exec("adb -s "+r_Id+" shell am start -a android.settings.BLUETOOTH_SETTINGS");
			minWait();
			Runtime.getRuntime().exec("adb -s "+r_Id+" shell am start -a android.bluetooth.adapter.action.REQUEST_ENABLE");
			minWait();  
			Runtime.getRuntime().exec("adb -s "+r_Id+" shell input keyevent 22");
			minWait();
			Runtime.getRuntime().exec("adb -s "+r_Id+" shell input keyevent 22");
			minWait();
			Runtime.getRuntime().exec("adb -s "+r_Id+" shell input keyevent 66");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void validateScanBluetooth() throws InterruptedException {
		/*
		 * enable disable BT
		 */
		SoftAssert S1 = new SoftAssert();
		minWait();

		clickBtn(Locators_DevSanity.connectedDevices_oreo);
		minWait();
		clickBtn(Locators_DevSanity.Bluetooth);
		minWait();
		enable(Locators_DevSanity.enabled,Locators_DevSanity.disabled,Locators_DevSanity.switch_Title);
		WebDriverWait wait = new WebDriverWait(getDriver(), 12000);
		minWait();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@text='CANCEL']")));

		System.out.println("BT Cancel");
		customWait(4000);
		minWait();
		System.out.println("BT*********");
		clickMenuAndElement(Locators_DevSanity.pairNewDeviceOptn);
		customWait(5000);
		if(isElementExist(Locators_DevSanity.BT_Devices)) {
			check1= true; 
			APP_LOGS.info( "BT ON "+check1);
			clickBackButton(1);
		}
		customWait(3000);
		disableFeature(Locators_DevSanity.enabled,Locators_DevSanity.disabled);
		if(isElementExist(Locators_DevSanity.BluetoothOff)) {
			check2 = true;
			APP_LOGS.info(" ");
		}
		customWait(2000);

		if((check1) && (check2)) {
			check = true;
			APP_LOGS.info("Scan near by BT devices verified sucessfully");
			test.log(LogStatus.INFO, "Scan near by BT devices verified sucessfully");
			S1.assertTrue(check, "Scan near by BT devices verified sucessfully ");
		}
		else {
			APP_LOGS.info(" check1"+check1+"check2"+check2);
			test.log(LogStatus.ERROR, "Failed to scan near by BT devices");
			APP_LOGS.info("Failed to scan near by BT devices");
			S1.fail();
		}		
	}

	public void validate_phone_Charging() throws InterruptedException{
		//			navigate_to_NotificationScreen();
		//			ScrollToElement(Locators_DevSanity.settings_frame, "Battery");
		try {
			minWait();
			clickBtn(Locators_DevSanity.status);
			customWait(1000);
			if(isElementExist(Locators_DevSanity.Charging_OverUSB_oreo)||isElementExist(Locators_DevSanity.Full_OverUSB)){
				APP_LOGS.info("Charging over USB in Battery Status option is displayed");
				test.log(LogStatus.INFO, "Charging over USB in Battery Status option is displayed");
				check=true;
				Assert.assertTrue(check);
			}else{
				APP_LOGS.info("Charging over USB in Battery Status option NOT is displayed");
				test.log(LogStatus.ERROR, "Charging over USB in Battery Status option NOT is displayed");
				Assert.fail();
			}	
			clickBackButton(2);
			minWait();
			clickBackButton(2);

		} catch (NoSuchElementException e) {
			e.printStackTrace();
			APP_LOGS.info("No Element found.");
			Assert.fail();
		}
	}

	public void navigateToSDCard() throws InterruptedException {
		/*
		 * this method will navigate to any folder 
		 */
		try {
			minWait();
			clickBtn(Locators_DevSanity.SDcard);
			APP_LOGS.info("Selected SD card");
			minWait();
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "No Such Element Found");
			Assert.fail();
		}
	}

	public void validateSDCardDetection() throws InterruptedException{
		/*
		 * Validate Sd card is detected
		 */
		SoftAssert Sa = new SoftAssert();
		try {
			String getSDcard = Locators_DevSanity.SDcard.getText();
			if(validate_presenceOfElement(Locators_DevSanity.SDcard, "SD card")) {
				check = true;
				APP_LOGS.info("SD card is Launched successfully");
				test.log(LogStatus.INFO, "Detected SD card: "+getSDcard);
				Sa.assertTrue(check, " ");
			}
			else {
				test.log(LogStatus.ERROR, "SD card is not Detected");
				Sa.fail();
			}
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
		} catch (NoSuchElementException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			test.log(LogStatus.ERROR, " No Such Element Found");
			Sa.fail();
		}
		Sa.assertAll();
	}

	public void selectProperties() throws InterruptedException {
		/*
		 * Select properties 
		 */
		try {
			if(isElementExist(Locators_DevSanity.SDcard)) {
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_MENU);
				minWait();
				clickBtn(Locators_DevSanity.SD_PropertiesSettingsOptn);
				minWait();
			}
			else {
				test.log(LogStatus.ERROR, "SD card is not Detected");
				Assert.fail();
			}

		} catch (NoSuchElementException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			test.log(LogStatus.ERROR, "Properties options is not present");
			Assert.fail();
		}	
	}

	public void fetchSDcardSize() {
		/*
		 * Fetch Total size ,NAme Available Size
		 */
		SoftAssert Sa = new SoftAssert();
		try {
			String getSize = Locators_DevSanity.SDcardSize.getText();
			System.out.println(getSize);

			if(getSize.contains("GB")) {
				check = true;
				APP_LOGS.info("SD card is Launched successfully");
				test.log(LogStatus.INFO, "Detected SD card: "+ getSize);
				Sa.assertTrue(check, " ");
			}
			else {
				test.log(LogStatus.ERROR, "SD card Size is not displayed");
				Sa.fail();
			}
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_ENTER);
		} catch (NoSuchElementException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			test.log(LogStatus.ERROR, "SD card Size element is not displayed");
			Sa.fail();
		}
		Sa.assertAll();
	}

	public void deviceIMEI() throws IOException, InterruptedException {
		/*
		 * Naviagte to IMEI mode to fetch Device IMEI
		 */
		minWait();
		aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_HOME);
		minWait();
		launch_an_app("phone");
		for(int i=1; i<=1; i++) {
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_STAR);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_POUND);
			minWait();
		}

		aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_0);
		minWait();
		aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_6);
		minWait();

		for(int i=1; i<=1; i++) {
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_POUND);
			minWait();
		}   
	}

	public void validateIMEIdisplayed() throws InterruptedException, IOException, ParseException {
		/*
		 * Validate IMEI is displayed using *#06# dail
		 */
		String Uid=JsonFileReaderAndWriter.primaryDevIdReader();

		SoftAssert Sa = new SoftAssert();
		String IMEITxt = null;
		try {
			if(isElementExist(Locators_DevSanity.IMEI_Num)) {
				IMEITxt = Locators_DevSanity.IMEI_Num.getText();
				minWait();
			}
			else if(isElementExist(Locators_DevSanity.IMEI_Numdisplay)) {
				IMEITxt = Locators_DevSanity.IMEI_Numdisplay.getText();
				minWait();
			}

			System.out.println(IMEITxt);
			String deviceIMEI1 = fetchDeviceProperty("adb -s "+Uid+" shell \"service call iphonesubinfo 1 | toybox cut -d \\\"'\\\"\r\n" + 
					" -f2 | toybox grep -Eo '[0-9]' | toybox xargs | toybox sed 's/\\ //g'");
			minWait();

			String deviceIMEI = deviceIMEI1.substring(0, deviceIMEI1.indexOf("8")+4);
			System.out.println(deviceIMEI);

			if(IMEITxt.contains(deviceIMEI)) {
				check = true;
				APP_LOGS.info("IMEI displayed and verified dailing *#06#");
				test.log(LogStatus.INFO, "IMEI displayed and verified dailing *#06#");
				Sa.assertTrue(check, " ");
			}
			else {
				test.log(LogStatus.ERROR, "IMEI is not fetched");
				Sa.fail();
			}
		} catch (NoSuchElementException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			test.log(LogStatus.ERROR, "Element not found");
			Sa.fail();
		}		
		Sa.assertAll();
	}

	public void deleteIfContactsPresent() throws InterruptedException
	{
		/*
		 * Delete contacts if exist before test
		 */
		try {
			if(!isElementExist(Locators_DevSanity.addContacts))
			{
				minWait();
				clickMenuAndElement(Locators_DevSanity.Contact_Select);
				minWait();
				clickMenuAndElement(Locators_DevSanity.Contact_SelectAll);
				minWait();
				clickMenuAndElement(Locators_DevSanity.contact_delete);
				minWait();
				Locators_DevSanity.contact_DeleteBtn.click();
				minWait();
			}
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			clickBackButton(2);
			APP_LOGS.info("Element Not Found");
			test.log(LogStatus.ERROR, "Element Not Found");

		}
	}

	public void addContactWithAllFields(String namePrefix,String firstName,String middleName,String lastName,String nameSuffix,String phoneticLastName,String phoneticMiddleName,String phoneticFirstName,String nickName,String company,String title,String SIP,String Email ,String address,String IM,String website,String Notes) throws InterruptedException, IOException, ParseException
	{
		/*
		 * add contact with all fields and validate
		 */
		String Uid=JsonFileReaderAndWriter.primaryDevIdReader();
		try {
			checkAddContactsOption();
			minWait();
			ScrollToElement(Locators_DevSanity.scrollpage, "More fields");
			minWait();
			Locators_DevSanity.contact_MorefieldsOptn.click();
			minWait();
			aDriver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().className(\"android.widget.ScrollView\")).scrollIntoView(new UiSelector().text(\"Name\"))");
			minWait();
			boolean check1 = addImageInContact();
			Locators_DevSanity.expandFld.click();
			minWait();
			boolean check2 = addNameFields(namePrefix, firstName, middleName, lastName);
			minWait();
			ScrollToElement(Locators_DevSanity.scrollpage, "Title");
			minWait();
			boolean check3 = addPhoneticNameFields(nameSuffix, phoneticLastName, phoneticMiddleName, phoneticFirstName, nickName);
			minWait();
			ScrollToElement(Locators_DevSanity.scrollpage, "Phone");
			minWait();
			boolean check4 = addComapnyAndPhoneNumber(company, title);
			minWait();
			ScrollToElement(Locators_DevSanity.scrollpage, "Email");
			minWait();
			boolean check5 = addSIPEmailAndAddress(SIP, Email, address);
			minWait();
			ScrollToElement(Locators_DevSanity.scrollpage, "Website");
			minWait();
			boolean check6 = addOtherFieldsInContact(IM, website, Notes);
			minWait();
			if((check1)&&(check2)&&(check3)&&(check4)&&(check5)&&(check6))
			{
				clickMenuAndElement(Locators_DevSanity.saveButton);
			}else {
				clickMenuAndElement(Locators_DevSanity.saveButton);
				test.log(LogStatus.INFO, "Contact saved:Unable fill some fields");
			}
			clickBackButton(1);
			customWait(3000);
			Runtime.getRuntime().exec("adb -s "+p_Id+" shell input text Sonim");
			customWait(2000);
			validateAddedContact(namePrefix);
		}catch (NoSuchElementException e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "Element Not Found");
		}
	}

	public void checkAddContactsOption() throws InterruptedException
	{
		/*
		 * Check Add contact option and click
		 */
		try
		{
			if(isElementExist(Locators_DevSanity.addContacts)) {
				clickBtn(Locators_DevSanity.addContacts);
			}else{
				minWait();
				clickMenuAndElement(Locators_DevSanity.addContactOpt);
			}
		}catch (NoSuchElementException e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "Element Not Found");
		}
	}

	public boolean addImageInContact() throws InterruptedException
	{
		/*
		 * Captures image in contact
		 */
		try
		{
			Locators_DevSanity.changePhotoOpt.click();
			minWait();
			Locators_DevSanity.takePhotoOpt.click();
			minWait();
			Locators_DevSanity.captureOpt.click();
			minWait();
			Locators_DevSanity.imageOkOpt.click();
			minWait();
			return check1=true;
		}catch (NoSuchElementException e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "Element Not Found");
			return check1 = false;
		}
	}


	public boolean addNameFields(String namePrefix,String firstName,String middleName,String lastName) throws InterruptedException
	{
		/*
		 * enters name to different name fields
		 */
		try
		{
			enterTextToInputField(Locators_DevSanity.namePrefixFld,namePrefix);
			minWait();
			enterTextToInputField(Locators_DevSanity.firstNameFld,firstName);
			minWait();
			enterTextToInputField(Locators_DevSanity.middleNameFld,middleName);
			minWait();
			enterTextToInputField(Locators_DevSanity.lastNameFld,lastName);
			return check2=true;
		}catch (NoSuchElementException e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "Element Not Found");
			return check2=true;
		}
	}

	public boolean addPhoneticNameFields(String nameSuffix,String phoneticLastName,String phoneticMiddleName,String phoneticFirstName,String nickName) throws InterruptedException
	{
		/*
		 * enters phonetic names to different name fields
		 */
		try
		{
			enterTextToInputField(Locators_DevSanity.nameSuffixFld,nameSuffix);
			minWait();
			Locators_DevSanity.expandFld.click();
			enterTextToInputField(Locators_DevSanity.phoneticLastName,phoneticLastName);
			minWait();
			enterTextToInputField(Locators_DevSanity.phoneticMiddleName,phoneticMiddleName);
			minWait();
			enterTextToInputField(Locators_DevSanity.phoneticFirstName,phoneticFirstName);
			minWait();
			enterTextToInputField(Locators_DevSanity.nickNameFld,nickName);
			return check3=true;
		}catch (NoSuchElementException e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "Element Not Found");
			return check3=false;
		}
	}

	public boolean addComapnyAndPhoneNumber(String company,String title) throws InterruptedException
	{
		/*
		 * enters Company,Title and phone Number
		 */
		try
		{
			enterTextToInputField(Locators_DevSanity.companyFld,company);
			minWait();
			enterTextToInputField(Locators_DevSanity.titleFld,title);
			minWait();
			enterTextToInputField(Locators_DevSanity.contact_Phone,refNum);
			return check4=true;
		}catch (NoSuchElementException e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "Element Not Found");
			return check4=false;
		}
	}

	public boolean addSIPEmailAndAddress(String SIP,String Email,String address) throws InterruptedException
	{
		/*
		 * enters SIP,Email and Address
		 */
		try
		{
			//	enterTextToInputField(Locators_DevSanity.sipFld,SIP);
			minWait();
			enterTextToInputField(Locators_DevSanity.emailFld,Email);
			minWait();
			ScrollToElement(Locators_DevSanity.scrollpage, "Address");
			minWait();
			enterTextToInputField(Locators_DevSanity.addressFld,address);
			minWait();
			return check5=true;
		}catch (NoSuchElementException e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "Element Not Found");
			return check5=false;
		}
	}

	public boolean addOtherFieldsInContact(String IM,String website,String Notes) throws InterruptedException
	{
		/*
		 * enters IM,Website and Notes
		 */
		try
		{
			enterTextToInputField(Locators_DevSanity.IMFld,IM);
			minWait();
			enterTextToInputField(Locators_DevSanity.websiteFld,website);
			minWait();
			ScrollToElement(Locators_DevSanity.scrollpage, "Notes");
			minWait();
			enterTextToInputField(Locators_DevSanity.notesFld,Notes);
			return check6=true;
		}catch (NoSuchElementException e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "Element Not Found");
			return check6=false;
		}
	}

	public void validateAddedContact(String Name) throws InterruptedException, IOException
	{
		/*
		 * validates added contact
		 */
		SoftAssert SA= new SoftAssert();
		try {
			customWait(2000);
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
			customWait(2000);
			if(!isElementExist(Locators_DevSanity.noContacts)){
				String actualString = Locators_DevSanity.addedContact.getText();
				System.out.println(actualString);
				if(actualString.contains(Name)){
					check = true;
					APP_LOGS.info("Contact Added sucessfully");
					test.log(LogStatus.INFO, "Contact Added sucessfully");
					SA.assertTrue(check, "Contact Added sucessfully");
				}else {
					APP_LOGS.info("Contact not added");
					test.log(LogStatus.INFO, "Contact not added");
					SA.fail("Contact not added");
				}
			}else {
				APP_LOGS.info("No Contacts Available");
				test.log(LogStatus.INFO, "No Contacts Available");
				SA.fail("No Contacts Available");
			}
		}catch (NoSuchElementException e) {
			e.printStackTrace();
			APP_LOGS.info("Element Not Found");
			test.log(LogStatus.ERROR, "Element Not Found");
			SA.fail();
		}
		SA.assertAll();
	}

	public void addContactAndCopyToSIM(String Name) throws InterruptedException, IOException
	{
		/*
		 * Add's Contact with Name and Phone Number and validates
		 */
		SoftAssert SA = new SoftAssert();
		try {
			checkAddContactsOption();
			minWait();
			enterNameAndPhone(Name);
			minWait();
			clickMenuAndElement(Locators_DevSanity.saveButton);
			clickBackButton(1);
			customWait(2000);
			Runtime.getRuntime().exec("adb -s "+p_Id+" shell input text "+ Name);
			customWait(3000);
			validateAddedContact(Name);
			minWait();
			clickMenuButton();
			if(isElementExist(Locators_DevSanity.copyToSIMOpt)) {
				clickBtn(Locators_DevSanity.copyToSIMOpt);
				clickBackButton(1);
				validatecopiedContactToSIM();
			}else if(isElementExist(Locators_DevSanity.copyToSIM1Opt)) {
				clickBtn(Locators_DevSanity.copyToSIM1Opt);
				clickBackButton(1);
				validatecopiedContactToSIM();
			}else {
				clickBackButton(1);
				APP_LOGS.info("Failed to copy Contact to SIM:Option not available");
				test.log(LogStatus.INFO, "Copy to SIM option not available");
				SA.fail("Failed to copy Contact to SIM:Option not available");
			}
			clickBackButton(1);
		}catch (NoSuchElementException e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "Element Not Found");
		}
		SA.assertAll();
	}

	public void enterNameAndPhone(String Name) throws InterruptedException
	{
		/*
		 * Enter name and phone number 
		 */
		try{
			customWait(3000);
			enterTextToInputField(Locators_DevSanity.contactName, Name);
			customWait(3000);
			enterTextToInputField(Locators_DevSanity.contact_Phone, refNum);
			customWait(3000);
		}
		catch (NoSuchElementException e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "Element Not Found");
		}
	}

	public void clickMenuButton()
	{
		/*
		 * clicks on back button with iteration as user input
		 */
		try {
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_MENU);
			minWait();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void validatecopiedContactToSIM()  throws InterruptedException, IOException
	{
		/*
		 * validates copied contact to SIM
		 */
		SoftAssert SA= new SoftAssert();
		try {
			if(Locators_DevSanity.matchedContactsFld.getText().contains("2 Contacts matched")){
				check = true ;
				APP_LOGS.info("Contact copied to SIM sucessfully");
				test.log(LogStatus.INFO, "Contact copied to SIM sucessfully");
				SA.assertTrue(check, "Contact copied to SIM sucessfully");
			}else{
				APP_LOGS.info("Failed to copy Contact to SIM");
				test.log(LogStatus.INFO, "Failed to copy Contact to SIM");
				SA.fail("Failed to copy Contact to SIM");
			} 
		}catch (NoSuchElementException e) {
			e.printStackTrace();
			APP_LOGS.info("Element Not Found");
			test.log(LogStatus.ERROR, "Element Not Found");
			SA.fail();
		}
		SA.assertAll();
	}

	public void deleteContact(String name1,String name2) throws InterruptedException, IOException, ParseException
	{
		/*
		 * Delete contact 
		 */
		String Uid=JsonFileReaderAndWriter.primaryDevIdReader();
		try {
			customWait(2000);
			Runtime.getRuntime().exec("adb -s "+Uid+" shell input text "+ name1);
			customWait(3000);
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_CLEAR);
			minWait();
			Locators_DevSanity.contact_DeleteBtn.click();
			customWait(2000);
			clickBackButton(1);
			customWait(2000);
			Runtime.getRuntime().exec("adb -s "+Uid+" shell input text "+ name2);
			customWait(3000);
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
			minWait();
			clickMenuAndElement(Locators_DevSanity.contact_delete);
			minWait();
			Locators_DevSanity.contact_DeleteBtn.click();
			minWait();
			validatedeletedcontact(name2);
			//	clickBackButton(1);
			deleteIfContactsPresent();
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "Element Not Found");
		}
	}

	public void validatedeletedcontact(String name) throws InterruptedException, IOException
	{
		/*
		 * Validates deleted Contact
		 */
		SoftAssert SA = new SoftAssert();
		try {
			if(isElementExist(Locators_DevSanity.noContacts)){
				check = true;
				APP_LOGS.info("Contacts deleted sucessfully");
				test.log(LogStatus.INFO, "Contacts deleted sucessfully");
				SA.assertTrue(check, "Contacts deleted sucessfully");	
			}else{
				APP_LOGS.info("Contacts not deleted");
				test.log(LogStatus.INFO, "Contacts not deleted");
				SA.fail("Contacts not deleted");
			}
		}catch (NoSuchElementException e) {
			e.printStackTrace();
			APP_LOGS.info("Element Not Found");
			test.log(LogStatus.ERROR, "Element Not Found");
			SA.fail();
		}
		SA.assertAll();
	}

	public void cameraPic() throws IOException, InterruptedException, ParseException {
		/*
		 * Take camera picture via camera application
		 */
		String Uid=JsonFileReaderAndWriter.primaryDevIdReader();

		try {
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_HOME);
			customWait(2000);
			Runtime.getRuntime().exec("adb -s "+Uid+" shell am start -n com.borqs.camera/com.borqs.camera.CameraActivity");
			customWait(2000);
			//			clickBtn(Locators_DevSanity.CamerIcon);
			customWait(1000);
			if(isElementExist(Locators_DevSanity.photocameraIcon)) {
				clickBtn(Locators_DevSanity.photocameraIcon);
				//			APP_LOGS.info("clic);
			}
			else {
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_UP);
				//switch to camera
				minWait();
				clickBtn(Locators_DevSanity.photocameraIcon);
			}

			minWait();
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			Assert.fail();
		}
	}

	public void validateBackCamera(String str, String TitleName,String fileName) throws InterruptedException, AWTException, IOException {
		/*
		 * Validate Gallery Folders from TitleName
		 */

		try {
			customWait(3000);
			if(searchString(str,fileName)) {				
				check = true;
				APP_LOGS.info(TitleName + "Back Camera is Validated in Gallery");
				test.log(LogStatus.INFO, TitleName + ":Back Camera is Validated ");
				Assert.assertTrue(check);
			}
			else {
				APP_LOGS.info(TitleName + "Back Camera is unVerified in Gallery");
				test.log(LogStatus.ERROR, TitleName + ":Back Camera is unVerfied ");
				Assert.fail();
			}
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
		} catch (NoSuchElementException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Assert.fail();
		}
	}

	public void validateCamera(String TitleName) throws InterruptedException, AWTException, IOException {
		/*
		 * Validate Gallery Folders from TitleName
		 */
		SoftAssert SA = new SoftAssert();
		try {
			customWait(3000);
			if(isElementExist(Locators_DevSanity.camera_captured_data)) {	
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
				customWait(2000);
				if(isElementExist(Locators_DevSanity.camera_captured_image)) {
					check = true;
					APP_LOGS.info(TitleName + " is Validated successfully");
					test.log(LogStatus.INFO, TitleName + " is Validated successfully");
					SA.assertTrue(check, "Back camera validated");	
				}else {
					APP_LOGS.info("Failed to validate "+TitleName);
					test.log(LogStatus.ERROR, "Failed to validate "+TitleName);
					SA.fail();
				}
			}
			else {
				APP_LOGS.info("Captured data not available");
				test.log(LogStatus.ERROR,"Captured data not available");
				Assert.fail();
			}
			clickBackButton(2);
		} catch (NoSuchElementException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Assert.fail();
		}
	}

	public void takeCameraVideo(int n) throws InterruptedException, IOException {
		/*
		 * Capture photo from camera and store in SDcard
		 */
		SoftAssert S1 = new SoftAssert();
		try {
			customWait(1000);
			aDriver.pressKeyCode(AndroidKeyCode.MENU);
			customWait(2000);
			clickBtn(Locators_DevSanity.SwitchToCameraOptn);
			customWait(2000);
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_LEFT);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_UP);
			//	Runtime.getRuntime().exec("adb -s "+r_Id+" shell input keyevent 19");
			//	clickBtn(Locators_DevSanity.swith_to_video);
			customWait(2000);
			clickBtn(Locators_DevSanity.videocameraIcon);
			customWait(2000);
			clickBtn(Locators_DevSanity.videocameraIcon);
			/*for(int i=1; i<=n;i++) {
				customWait(2000);
			}
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
			customWait(1000);
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			minWait();*/
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			S1.fail();
		}
		S1.assertAll();
	}

	public void press_Enter() throws InterruptedException {
		/*
		 * enter key is pressed
		 */
		minWait();
		aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_ENTER);
		minWait();
		aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_ENTER);
		minWait();
	}

	public void deleteSavedRecorder() throws InterruptedException {
		/*
		 * delete the saved recorded sound
		 */
		try {
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_RIGHT);
			APP_LOGS.info("Pressed List Icon sucessfully");
			customWait(2000);
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_MENU);
			/*for(int i=1; i<=3; i++) {
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
			}*/
			clickBtn(Locators_DevSanity.selectSRList);
			customWait(2000);
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_MENU);
			minWait();
			if(isElementExist(Locators_DevSanity.SelectallOptn)) {
				clickBtn(Locators_DevSanity.SelectallOptn);
				customWait(1000);
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_MENU);
				minWait();
			}

			clickBtn(Locators_DevSanity.Delete);
			APP_LOGS.info("Pressed Delete Icon sucessfully");
			customWait(2000);
			clickBtn(Locators_DevSanity.DELETE);
			APP_LOGS.info("Pressed Delete Button sucessfully");
			Thread.sleep(2000);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
		} catch (NoSuchElementException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Assert.fail();
		}
	}

	public void recordSound() throws InterruptedException {
		/*
		 * Record the sound 
		 */
		try {
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
			minWait();
			selectRecordBtn();
			APP_LOGS.info("Pressed Record button sucessfully");
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_LEFT);
			APP_LOGS.info("Pressed Stop button sucessfully");
		} catch (NoSuchElementException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Assert.fail();
		}
	}

	public void selectRecordBtn() throws InterruptedException {
		/*
		 * Select the record Button
		 */
		try {
			for(int i=0; i<=2; i++) {
				if(isElementExist(Locators_DevSanity.allowBtn)){
					customWait(2000);
					Locators_DevSanity.allowBtn.click();
					APP_LOGS.info("Pressed Allow button sucessfully");
					minWait();
					APP_LOGS.info("Allow Button is displayed and Selected, succesful.");

				}else{
					APP_LOGS.info("Allow Button is not displayed.");
				}
			}maxWait();
		} catch (NoSuchElementException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Assert.fail();			
		}	
	}

	public void validateRecordedSoundSaved() throws InterruptedException {
		/*
		 * check recording name is saved in recorded list after saving
		 */
		try {
			minWait();
			String SavedRecName=Locators_DevSanity.recordingName.getText();
			minWait();
			System.out.println(SavedRecName);
			Locators_DevSanity.SaveButton.click(); 
			APP_LOGS.info("Pressed Save button sucessfully");
			minWait();
			String ListSavedRecName1=Locators_DevSanity.saveBtnUnderRecList.getText();
			String ListSavedRecName = ListSavedRecName1.substring(0, ListSavedRecName1.indexOf(".")+3);

			System.out.println(ListSavedRecName);
			minWait();

			if(ListSavedRecName.contains("New record")){
				check=true;
				APP_LOGS.info("Sound Record is Verified succesfully");
				test.log(LogStatus.INFO, "Sound Record is Verified succesfully");
				Assert.assertTrue(check);	
			}else{
				APP_LOGS.info("Sound Record is not saved, unsuccesful");
				test.log(LogStatus.ERROR, "Sound Record is not Verified succesfully");
				Assert.fail();
			}	aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			Assert.fail();
		}
	}

	public void playMusic() throws InterruptedException, IOException{
		try {
			minWait();
			launch_an_app("music");
			minWait();
			if(isElementExist(Locators_DevSanity.audiorecordings)) {
				minWait();
				clickBtn(Locators_DevSanity.audiorecordings);
				minWait();
				clickBtn(Locators_DevSanity.yourrecordings);
				minWait();
			}else {
				minWait();
				clickBtn(Locators_DevSanity.yourrecordings);
				minWait();
				clickBtn(Locators_DevSanity.audiorecordings);
				minWait();
				clickBtn(Locators_DevSanity.yourrecordings);
			}
			clickBackButton(2);
		}catch (Exception e) {
			e.printStackTrace();
			APP_LOGS.info("No Element found.");
			test.log(LogStatus.ERROR, "No Such Element Found.");
		}
	}

	public void validate_music_player() throws InterruptedException{
		SoftAssert SA = new SoftAssert();
		try {
			if(isElementExist(Locators_DevSanity.musicIndicator)){
				check = true;
				APP_LOGS.info("Music played sucessfully.");
				test.log(LogStatus.INFO, "Music played sucessfully.");
				SA.assertTrue(check, "Music played sucessfully.");	
			}else {
				APP_LOGS.info("Music is not played.");
				test.log(LogStatus.ERROR, "Music is not played.");
				SA.fail("Music is not played.");
			}	
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			APP_LOGS.info("No Element found.");
			test.log(LogStatus.ERROR, " Battery option: No Such Element Found.");
			Assert.fail();
		}
	}

	public void validateChaneSleepTime() throws InterruptedException {
		/*
		 * Set sleep mode to maximum
		 */
		try {
			minWait();
			String getSleepSummary = Locators_DevSanity.Sleep_Summary.getText();
			System.out.println(getSleepSummary);
			customWait(2000);
			clickBtn(Locators_DevSanity.Sleep_Optn);
			minWait();
			for(int i=1; i<=2; i++) {
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_UP);
			}
			clickBtn(Locators_DevSanity.thirtyMinSleep_Optn);
			customWait(5000);
			String getSleepchange = Locators_DevSanity.Sleep_Summary.getText();
			System.out.println(getSleepchange);

			if(!getSleepchange.equals(getSleepSummary)) {
				check = true;
				test.log(LogStatus.INFO, "Sleep Time is changed sucessfully");
				Assert.assertTrue(check);
			}
			else {
				test.log(LogStatus.ERROR, "Sleep Time is not changed ");
				Assert.fail();
			}
			clickBtn(Locators_DevSanity.Sleep_Optn);
			minWait();

			clickBtn(Locators_DevSanity.eightHrsSleep_Optn);
			test.log(LogStatus.INFO, " Sleep Mode is set to maximum");
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			test.log(LogStatus.ERROR, " No such Element Found");
		}		
	}

	public void ValidatechangeRingtone() throws InterruptedException {
		/*
		 * change Ringtone
		 */

		ScrollToElement(Locators_BaseUtil.SettingsList, "Phone ringtone");
		customWait(2000);
		String getSummary = Locators_DevSanity.Ringtone_Summary.getText();
		System.out.println(getSummary);
		customWait(2000);
		clickBtn(Locators_DevSanity.Ringtone_feature);
		customWait(2000);

		aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
		minWait();
		aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_UP);
		minWait();
		aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
		customWait(2000);

		String getSleepchange = Locators_DevSanity.Ringtone_Summary.getText();
		System.out.println(getSleepchange);

		if(!getSleepchange.equals(getSummary)) {
			check = true;
			test.log(LogStatus.INFO, "Ringtone is changed successfully");
			Assert.assertTrue(check);
		}
		else {
			test.log(LogStatus.ERROR, "Ringtone is not changed ");
			Assert.fail();
		}
		clickBtn(Locators_DevSanity.Ringtone_feature);
		minWait();


		aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_UP);

		aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
		minWait();
		aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
	}

	public void validateDisable(WebElement element) throws InterruptedException {
		/*
		 * Validate data roaming is enable 
		 */
		SoftAssert S1 = new SoftAssert();
		try {
			clickBtn(Locators_DevSanity.OK_Btn);
			minWait();
			if(element.isEnabled()) {
				check = true;
				test.log(LogStatus.INFO, " Location is disabled");
				S1.assertTrue(check, " ");
			}
			else {
				test.log(LogStatus.ERROR, " Location is not disabled");
				S1.fail();
			}
		} catch (NoSuchElementException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			test.log(LogStatus.ERROR, "No Such Element Found");
			S1.fail();

		}
		S1.assertAll();
	}

	public void validateEnable(WebElement element) {
		/*
		 * Validate data roaming is enable 
		 */
		SoftAssert S1 = new SoftAssert();
		try {
			if(element.isEnabled()) {
				check = true;
				test.log(LogStatus.INFO, "Location is enabled");
				S1.assertTrue(check, " ");
			}
			else {
				test.log(LogStatus.ERROR, " Location is not enabled");
				S1.fail();
			}
		} catch (NoSuchElementException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			test.log(LogStatus.ERROR, "No Such Element Found");
			S1.fail();

		}
		S1.assertAll();
	}

	public void checkOperatorAndlaunchCalendar() throws InterruptedException, IOException, ParseException
	{
		/*
		 * check operator and launch calendar
		 */
		String XP5deviceModelNumber=JsonFileReaderAndWriter.primaryDevOperatorReader();
		try {
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_HOME);
			minWait();
			if(XP5deviceModelNumber.contains("5SA.0.1-04-7.1.2-26.")) {
				clickBtn(Locators_DevSanity.applications);
				minWait();
				clickBtn(Locators_DevSanity.calendar);
			}else {
				launch_an_app("calendar");
			}
		}catch (NoSuchElementException e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "Element Not Found");
		}
	}

	public void addCalendarEvent(String eventName,String location) throws InterruptedException, IOException
	{
		/*
		 * Add calendar Event
		 */
		try {
			minWait();
			deleteEvents();
			minWait();
			addEvent(eventName, location);
			minWait();
			clickMenuButton();
			if(isElementExist(Locators_DevSanity.agendaOpt)) {
				clickBtn(Locators_DevSanity.agendaOpt);
				minWait();
			}else {
				clickBackButton(1);
			}
			validateAddedEvent(eventName);
			minWait();
			deleteEvents();
		}catch (NoSuchElementException e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "Element Not Found");
		}
	}

	public void validateAddedEvent(String eventName) throws InterruptedException, IOException
	{
		/*
		 * Validates Added event
		 */
		SoftAssert SA = new SoftAssert();
		try {
			String event = Locators_DevSanity.createdEvent.getText();
			minWait();
			if(event.equals(eventName)){
				check = true;
				APP_LOGS.info("Event added in Calendar succesfully");
				test.log(LogStatus.INFO, "Event added in Calendar succesfully");
				SA.assertTrue(check, "Event added in Calendar succesfully");	
			}else{
				APP_LOGS.info("Failed to add event");
				test.log(LogStatus.INFO, "Failed to add event");
				SA.fail("Failed to add event");
			}
		}catch (NoSuchElementException e) {
			e.printStackTrace();
			APP_LOGS.info("Element Not Found");
			test.log(LogStatus.ERROR, "Element Not Found");
			SA.fail();
		}
		SA.assertAll();
	}

	public void deleteEvents() throws InterruptedException, IOException
	{
		/*
		 * delete events
		 */
		try {
			clickMenuButton();
			if(isElementExist(Locators_DevSanity.monthOpt)){
				clickBtn(Locators_DevSanity.monthOpt);
				minWait();
				clickMenuButton();
			} else {
				clickBackButton(1);
				minWait();
				clickMenuButton();
			}
			if (Locators_DevSanity.deleteEventsEnabled.isEnabled()) {
				clickBtn(Locators_DevSanity.deleteEventsOpt);
				minWait();
				clickMenuAndElement(Locators_DevSanity.selectAllOpt);
				minWait();
				clickMenuAndElement(Locators_DevSanity.doneOpt);
				minWait();
				clickBtn(Locators_DevSanity.OkBtn);
				customWait(2000);
			}else {
				clickBackButton(1);
			}
		}catch (NoSuchElementException e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "Element Not Found");
		}
	}

	public void addEvent(String eventName,String location ) throws InterruptedException, IOException
	{
		/*
		 * Add event and save
		 */
		try {
			clickMenuAndElement(Locators_DevSanity.newEvent);
			minWait();
			enterTextToInputField(Locators_DevSanity.eventName, eventName);
			minWait();
			enterTextToInputField(Locators_DevSanity.locationFld, location);
			minWait();
			clickMenuAndElement(Locators_DevSanity.saveBtn);
		}catch (NoSuchElementException e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "Element Not Found");
		}
	}

	public void launchClock() throws InterruptedException, IOException
	{
		/*
		 * Launch clock application
		 */
		try {
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_HOME);
			minWait();
			clickBtn(Locators_DevSanity.tools);
			minWait();
			clickBtn(Locators_DevSanity.clock);
		}catch (NoSuchElementException e) {
			e.printStackTrace();
		}
	}

	public void addAlarm() throws InterruptedException, IOException
	{
		/*
		 * Adds Alarm
		 */
		try {
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_LEFT);
			minWait();
			clickMenuAndElement(Locators_DevSanity.addOpt_oreo);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_RIGHT);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_RIGHT);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
			minWait();
			clickBtn(Locators_DevSanity.OkBtn);
			minWait();
		}catch (NoSuchElementException e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "Element Not Found");
		}
	}

	public void validateAddedAlarm() throws InterruptedException, IOException
	{
		/*
		 * Validates Added Alarm
		 */
		SoftAssert SA = new SoftAssert();
		try {
			if(isElementExist(Locators_DevSanity.alarmSetPage)){
				check = true;
				clickBackButton(1);
				minWait();
				if(isElementExist(Locators_DevSanity.alarmTodayOpt) && isElementExist(Locators_DevSanity.alarmSwitchOn))
				{
					clickBtn(Locators_DevSanity.alarmSwitchOn);
					APP_LOGS.info("Alarm added succesfully");
					test.log(LogStatus.INFO, "Alarm added succesfully");
					SA.assertTrue(check, "Alarm added succesfully");	
				}
			}else{
				APP_LOGS.info("Failed to add alarm");
				test.log(LogStatus.INFO, "Failed to add alarm");
				SA.fail("Failed to add alarm");
			}
		}catch (NoSuchElementException e) {
			e.printStackTrace();
			APP_LOGS.info("Element Not Found");
			test.log(LogStatus.ERROR, "Element Not Found");
			SA.fail();
		}
		SA.assertAll();
	}

	public void snoozeTheAlarm() throws InterruptedException, IOException
	{
		/*
		 * Add alarm,Snooze and validate
		 */
		try {
			addAlarm();
			clickBackButton(1);
			minWait();
			waituntilnew(Locators_DevSanity.alarmPage, 60000);
			minWait();
			if(isElementExist(Locators_DevSanity.alarmTimeFld)) {
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_LEFT);
			}
			validateSnoozeAndDismiss(Locators_DevSanity.alarmSwitchOn,Locators_DevSanity.alarmTodayOpt,"Snoozed");
			minWait();
			clickBtn(Locators_DevSanity.alarmSwitchOn);
		}catch (NoSuchElementException e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "Element Not Found");
		}
	}

	public void validateSnoozeAndDismiss(WebElement element,WebElement elementday,String state) throws InterruptedException, IOException
	{
		/*
		 * Validates snooze and dismiss alarm
		 */
		SoftAssert SA = new SoftAssert();
		try {
			customWait(4000);
			if(isElementExist(elementday) && isElementExist(element))
			{
				check= true;
				APP_LOGS.info("Alarm "+state+" succesfully");
				test.log(LogStatus.INFO, "Alarm "+state+" succesfully");
				SA.assertTrue(check, "Alarm "+state+" succesfully");	
			}else {
				APP_LOGS.info("Alarm not "+state);
				test.log(LogStatus.INFO, "Alarm not "+state);
				SA.fail("Alarm not "+state);
			}
		}catch (NoSuchElementException e) {
			e.printStackTrace();
			APP_LOGS.info("Element Not Found");
			test.log(LogStatus.ERROR, "Element Not Found");
			SA.fail();
		}
		SA.assertAll();
	}

	public void dismissTheAlarm() throws InterruptedException, IOException
	{
		/*
		 * Add Alarm,Dismiss and validate
		 */
		try {
			addAlarm();
			clickBackButton(1);
			minWait();
			waituntilnew(Locators_DevSanity.alarmPage, 60000);
			minWait();
			if(isElementExist(Locators_DevSanity.alarmTimeFld)) {
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_RIGHT);
			}
			customWait(2000);
			validateSnoozeAndDismiss(Locators_DevSanity.alarmSwitchOff,Locators_DevSanity.alarmTomorrowOpt,"Dismissed");
			minWait();
		}catch (NoSuchElementException e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "Element Not Found");
		}
	}

	public void validateScoutAppLaunch() {
		/*
		 * Validate Scout app launch  with tabs present
		 */
		SoftAssert SA = new SoftAssert();

		try {
			if(isElementExist(Locators_DevSanity.utilitiesTab)) {
				check1 =true;
				APP_LOGS.info("Utility Tab is present");
			}
			if(isElementExist(Locators_DevSanity.setUpTab)) {
				check2 =true;
				APP_LOGS.info("SetUp Tab is present");
			}
			if(isElementExist(Locators_DevSanity.supportTab)) {
				check3 =true;
				APP_LOGS.info("Support Tab is present");
			}

			if((check1)&&(check2)&&(check3)) {
				check= true;
				APP_LOGS.info("Scout App Launched sucessfully and all Tabs verified");
				test.log(LogStatus.INFO, "Scout App Launched sucessfully and all Tabs verified");
				SA.assertTrue(check, " ");
			}
			else{
				APP_LOGS.info(check1+"\n"+": "+check2+"\n"+": "+check3+ "\n");
				test.log(LogStatus.ERROR, "Scout App Launch unverified");
				SA.fail();
			}
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			SA.fail();
		}	
		SA.assertAll();
	}

	public void validatelaunchAllSCoutApps() throws InterruptedException, IOException, ParseException {
		/*
		 * Launch scout Application tray and all apps
		 */

		//Launch and validate SonimSetupWizard
		navigateApps(Locators_DevSanity.setUpTab,Locators_DevSanity.SonimSetUpWizard);
		validateLaunchScoutApp(Locators_DevSanity.SonimSetUpWizard, "SetUpWizard");

		//Launch and validate Safeguard
		navigateApps(Locators_DevSanity.setUpTab,Locators_DevSanity.Safeguard);
		validateLaunchScoutApp(Locators_DevSanity.Safeguard, "SafeGuard");

		//Launch and validate AppUpdater
		navigateApps(Locators_DevSanity.setUpTab,Locators_DevSanity.AppUpdater);
		aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
		customWait(2000);

		if(isElementExist(Locators_DevSanity.appUpdater_CheckForUpdates)) {
			validateLaunchScoutApp(Locators_DevSanity.appUpdater_CheckForUpdates, "App Updater");
		}else {
			OCR.Read_File.takeScreenShotForOcr("AppUpdater");
			Thread.sleep(2000);
			OCR.my_main.validate_Using_OCR("AppUpdater.png");
			customWait(2000);
			Boolean value = searchStringOCR("App Updater", "ocr");
			if(value) {
				check = true;
				customWait(1000);
				clickBackButton(1);
				test.log(LogStatus.INFO, "App Updater is launched and verified ");
				assertTrue(check);

			}else {
				test.log(LogStatus.ERROR, "App Updater launch is unverified");
				Assert.fail();
			}
		}

		//Verify each apps in Utilities tab
		aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
		minWait();
		aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_RIGHT);
		customWait(3000);
		
		//Launch and validate Contact Transfer
		navigateApps(Locators_DevSanity.utilitiesTab,Locators_DevSanity.ContactTransferTitle);
		validateLaunchScoutApp(Locators_DevSanity.ContactTransferTitle, "Contact Transfer");

		//Launch and validate SonimBLEConnect
		navigateApps(Locators_DevSanity.utilitiesTab,Locators_DevSanity.BLEconnect);
		if(isElementExist(Locators_DevSanity.BLEconnectError)) {
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
			if(isElementExist(Locators_DevSanity.switchBTOnBtn)) {
				clickBtn(Locators_DevSanity.switchBTOnBtn);
			}
		}
		
		if(isElementExist(Locators_DevSanity.BLEconnect)) {
			validateLaunchScoutApp(Locators_DevSanity.BLEconnect, "Sonim BLE connect");
		}else {
			OCR.Read_File.takeScreenShotForOcr("BLEconnect");
			Thread.sleep(2000);
			OCR.my_main.validate_Using_OCR("BLEconnect.png");
			Boolean value = searchStringOCR("Sonim BLE Connect", "ocr");
			if(value) {
				check = true;
				customWait(1000);
				clickBackButton(1);
				test.log(LogStatus.INFO, "Sonim BLE Connect is launched and verified ");
				assertTrue(check);

			}else {
				test.log(LogStatus.ERROR, "Sonim BLE Connect launch is unverified");
				Assert.fail();
			}
		}
		
		//verify each apps in Support tab
		aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
		minWait();
		aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_RIGHT);

		//Launch and validate Sonim chat
		navigateApps(Locators_DevSanity.supportTab,Locators_DevSanity.Chat);
		validateLaunchScoutApp(Locators_DevSanity.Chat_Title, "Sonim Chat");

		//Launch and validate Remote Support
		navigateApps(Locators_DevSanity.supportTab,Locators_DevSanity.RemoteSupport);
		customWait(3000);
		validateLaunchScoutApp(Locators_DevSanity.RemoteControl_pge, "Remote Control");

		//Launch and validate Sonimcare
		navigateApps(Locators_DevSanity.supportTab,Locators_DevSanity.SonimCare);
		validateLaunchScoutApp(Locators_DevSanity.SonimCare, "SonimCare");

		//Launch and validate Warranty Registration
		navigateApps(Locators_DevSanity.supportTab,Locators_DevSanity.WarrantyReg);
		validateLaunchScoutApp(Locators_DevSanity.WarrantyReg, "Warranty Registration");
	}

	public void navigateApps(WebElement tab,WebElement app) throws InterruptedException, IOException {
		minWait();
		//launch_an_app("scout");
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
					clickBtn(Locators_DevSanity.HomeBtn_Wizard);
				}
			}
			else {
				test.log(LogStatus.ERROR, appName+" launch is unverified");
				Assert.fail();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


}
