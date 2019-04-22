package com.aosp.Utils;

import static org.testng.Assert.assertTrue;

import java.awt.AWTException;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Time;
import java.util.Calendar;
import java.util.Formatter;
import java.util.Random;

import org.apache.http.ParseException;
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

public class XP5S_Sanity_Util_Oreo extends BaseUtil {

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



	public void validateAppMenuLaunch() throws InterruptedException {
		/*
		 * validate App Launch Menu from Launcher
		 */
		validate_HomeScreen();	
		minWait();
		aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
		minWait();
		aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
		minWait();
		aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_HOME);
		minWait();
		test.log(LogStatus.INFO, "App Menu is clicked");
		minWait();
		if(validate_presenceOfElement(Locators_Sanity.settings, "Settings")) {
			check = true;
			assertTrue(check);
			test.log(LogStatus.INFO, "Validated Launch App Menu From Launcher");
		}
		else {
			Assert.fail();
			test.log(LogStatus.ERROR, "Launch App Menu From Launcher is not Validated");
		}		
	}

	public void validate_HomeScreen() throws InterruptedException{
		SoftAssert Ss = new SoftAssert();
		try {
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_HOME);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			minWait();

			for(int i=1; i<=8; i++) {
				if(isElementExist(Locators_Sanity.HomeScreen)) {
					APP_LOGS.info("In Quick Settings");
					minWait();
					break;					
				} else {
					aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_LEFT);
					continue;					
				}	
			}


			if(isElementExist(Locators_Sanity.HomeScreen)){
				check = true;
				APP_LOGS.info("HomeSCreen Ring is found in home screen");
				APP_LOGS.info("You are in homescreen");
				//					test.log(LogStatus.INFO, "Home Screen is Validated");
				Ss.assertTrue(check, " ");
			}else{
				APP_LOGS.info("Settings icon is not found in home screen");
				APP_LOGS.info("You are not in homescreen");
				test.log(LogStatus.ERROR, " Not in Homescreen");
				Ss.fail();
			}
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "Settings icon is not found in home screen : No Such Element Found ");
			Ss.fail();
		}
		Ss.assertAll();
	}

	public void enableData() throws InterruptedException {
		/*
		 * enable data
		 */
		try {
			launch_an_app("settings");
			minWait();
			clickBtn(Locators_Sanity.networkAndInternet);
			minWait();
			if(isElementExist(Locators_Sanity.MobileNetwrks_oreo)) {
				minWait();
				clickBtn(Locators_Sanity.MobileNetwrks_oreo);
				APP_LOGS.info("Selected Mobile Networks");
				minWait();				
				enableSwitch(Locators_Sanity.enabled_Data_Oreo,Locators_Sanity.disabled_Data_Oreo,Locators_Sanity.switch_widget);
				minWait();
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {
			APP_LOGS.info("No Element found.");
			Assert.fail();
			minWait();	
		}
	}

	public void validateLaunch_All_Applications() throws InterruptedException, IOException {
		//this method launch all applications one by one and validate

		try {
			launch_an_app("browser");
			customWait(3000);
			validate_launchofApp(Locators_Sanity.DefaultUrlTxt, "Browser");

			launch_an_app("messaging");
			validate_launchofApp(Locators_Sanity.messaging_Title, "Messaging");

			launch_an_app("contacts");
			if(isElementExist(Locators_Sanity.contact_FindContacts)) {
				validate_launchofApp(Locators_Sanity.contact_FindContacts, "Contacts");
			}
			else if(isElementExist(Locators_Sanity.contacts)) {
				validate_launchofApp(Locators_Sanity.contacts, "Contacts");
			}

			launch_an_app("phone");
			validate_launchofApp(Locators_Sanity.phone_find, "Phone");

			launch_an_app("scout");
			validate_launchofApp(Locators_Sanity.SetUp_Scout, "Sonim Scout");

			launch_an_app("camera");
			switchCamera();


			launch_an_app("gallery");
			validate_launchofApp(Locators_Sanity.GalleryAlbums, "Gallery");

			launch_an_app("calendar");
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_MENU);
			validate_launchofApp(Locators_Sanity.Calender_NewEvent, "Calendar");

			launch_an_app("settings");
			validate_launchofApp(Locators_Sanity.settingsIcon, "Settings");

			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_HOME);
			minWait();
			clickBtn(Locators_Sanity.applications_icon);
			validate_launchofApp(Locators_Sanity.music_icon, "Applications");

			launch_an_app("music");
			validate_launchofApp(Locators_Sanity.artists_music_player, "Music");

			launch_an_app("soundRecorder");
			validate_launchofApp(Locators_Sanity.sound_Recorder, "Sound Recorder");

			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);

		} catch (NoSuchElementException e) {
			e.printStackTrace();
			APP_LOGS.info("File failed to open");
			test.log(LogStatus.ERROR,"No Such Element Found");
			Assert.fail();
		}						
	}

	// Validates the presence of element
	public void validate_launchofApp(WebElement element, String elementName) throws InterruptedException {
		minWait();
		SoftAssert Sa = new SoftAssert();
		try {
			customWait(6000);
			if (isElementExist(element)) {
				check = true;
				APP_LOGS.info(elementName + " is found succesfully");
				test.log(LogStatus.INFO, elementName + " Application sucessfully Launched ");
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
			if(isElementExist(Locators_Sanity.camera_Icon)) {
				minWait();
				APP_LOGS.info("Camera icon is selected");
			}
			else {
				//			     clickBtn(Locators_Sanity.switchCamerIcon);
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_UP);
				minWait();
			}
		} catch (NoSuchElementException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Assert.fail();
		}

	}

	public void navigateToOptions(WebElement optntype) throws InterruptedException, IOException {
		/*
		 * Select Screen lock or Simcard Lock
		 */		
		try {
			scrollToElementWithDpadDown(optntype);
			minWait();
			String getOptionType = optntype.getText();
			minWait();
			clickBtn(optntype);
			APP_LOGS.info("Selected option is " + getOptionType);
			customWait(1000);			
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			test.setDescription("Failure Reason: Element not found");
			Assert.fail();
		}
	}

	public void validateAboutPhone() throws InterruptedException, FileNotFoundException, IOException, ParseException, org.json.simple.parser.ParseException {
		/*
		 * validate About phone by Basebandversion and Build number
		 */
		SoftAssert Sa = new SoftAssert();
		try {
			String Uid=JsonFileReaderAndWriter.primaryDevIdReader();

			String BasebandVersionTxt = Locators_Sanity.BasebandVersionText.getText();
			System.out.println(BasebandVersionTxt);
			minWait();
			String deviceBaseband = fetchDeviceProperty("adb -s "+Uid+" shell getprop gsm.version.baseband");
			minWait();
			System.out.println(deviceBaseband);
			if(BasebandVersionTxt.equals(deviceBaseband)) {
				check1 = true;
				APP_LOGS.info("About Phone Baseband is verified "+ deviceBaseband);
				test.log(LogStatus.INFO, " About Phone Baseband is verified "+ deviceBaseband);
			}

			String BuildNumberTxt = Locators_Sanity.BuildNumberText.getText();
			minWait();
			System.out.println(BuildNumberTxt);
			String deviceBuildNum = fetchDeviceProperty("adb -s "+Uid+" shell getprop ro.build.id");
			minWait();
			System.out.println(deviceBuildNum);
			if(BuildNumberTxt.contains(deviceBuildNum)) {
				check2 = true;
				APP_LOGS.info("About phone BuildNumber is verified "+ deviceBuildNum);
				test.log(LogStatus.INFO, " About phone BuildNumber is verified "+ deviceBuildNum);
			}

			if((check1)&&(check2)) {
				check = true;
				APP_LOGS.info("About Phone verified with Baseband and Build Number");
				Sa.assertTrue(check, " ");
			}
			else {
				test.log(LogStatus.ERROR, "Baseband and Build Number are mismatch");
				Sa.fail();
			}        
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "No Such Element Found");
			Sa.fail();
		}
		Sa.assertAll();
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

	public void navigateToNetworkAndInternetOptions() throws InterruptedException, IOException {
		/*
		 * Select Screen lock or Simcard Lock
		 */	
		try {
			if(isElementExist(Locators_Sanity.networkAndInternet)) {
				minWait();
				clickBtn(Locators_Sanity.networkAndInternet); 
				minWait();
			}else {
				scrollToElementWithDpadUp(Locators_Sanity.networkAndInternet);
			}
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			test.setDescription("Failure Reason: Element not found");
			Assert.fail();
		}
	}

	public void navigateToAirplaneMode() throws InterruptedException, IOException {
		/*
		 * Select Screen lock or Simcard Lock
		 */		
		try {
			minWait();
			clickBtn(Locators_Sanity.networkAndInternet); 
			minWait();
			scrollToElementWithDpadDown(Locators_Sanity.Flightmode);
			minWait();
			clickBtn(Locators_Sanity.Flightmode);
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			test.setDescription("Failure Reason: Element not found");
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
			//clickBtn(Locators_Sanity.wifi);
			//navigateToOptions(Locators_Sanity.wifi, "Wi-Fi");
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
			clickBtn(Locators_Sanity.preferred_network_option_oreo);
			minWait();
			clickBackButton(2);
		} catch (Exception e) {
			e.printStackTrace();
			APP_LOGS.info("No Element found.");
			test.log(LogStatus.ERROR, "No Such Element FOund");
			sf.fail();
		}
	}

	public void validateLTESelection(String type) throws InterruptedException {
		/* 
		 * validate LTE selection is taken place
		 */
		SoftAssert sf = new SoftAssert();
		try {
			minWait();
			clickBtn(Locators_Sanity.status);
			customWait(2000);
			clickBtn(Locators_Sanity.sim_status);
			minWait();	
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
			customWait(2000);
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
			customWait(2000);
			clickBtn(Locators_Sanity.mobileNwType);
			minWait();
			String get_NetworkType = Locators_Sanity.networkType_Oreo.getText();	
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

	public void displayOperatorName() throws InterruptedException{
		/*
		 * Fetches and displays operator name
		 */
		SoftAssert SA= new SoftAssert();
		try {
			minWait();
			String operatorName = Locators_Sanity.operatorName.getText();
			if(operatorName.contains("Emergency calls only")){
				test.log(LogStatus.FAIL, "SIM not Inserted/Not Latched : Not able to fetch Operator Name from Homescreen");
				test.log(LogStatus.FAIL, "Test case status is Failed");
			}else{
				test.log(LogStatus.INFO, "Operator Name is "+operatorName);
				test.log(LogStatus.PASS, "Test case status is Passed");
			}
		} catch (NoSuchElementException e) {
			APP_LOGS.info("No Element found.");
			e.printStackTrace();
			test.log(LogStatus.FAIL, "SIM not Inserted/Not Latched : Not able to fetch Operator Name from Homescreen");
			test.log(LogStatus.FAIL, "Test case status is Failed");
			SA.fail();
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

	public void dailNumber(String dailNum) throws IOException, InterruptedException {
		minWait();
		launch_an_app("phone");
		customWait(2000);
		Runtime.getRuntime().exec("adb -s "+p_Id+" shell am start -a android.intent.action.CALL -d tel:"+refNum);
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
			String callLogNum = Locators_Sanity.first_No_In_CallLog.getText().replaceAll(" ", "");
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
			clickBtn(Locators_Sanity.New_msgOptn);
			minWait();
			//Runtime.getRuntime().exec("adb shell am start -n com.android.mms/com.android.mms.ui.ComposeMessageActivity");
			customWait(1000);
			changeToNumberMode();
			minWait();
			enterTextToInputField(Locators_Sanity.Type_NumField, newNum);
			minWait();
			if(isElementExist(Locators_Sanity.Msg_Contact)) {
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_ENTER);
				minWait();
			}
			minWait();
			enterTextToInputField(Locators_Sanity.typeMsg_Field, composedMsg);
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
		Locators_Sanity.toField_NewMessage.sendKeys("123");
		customWait(1500);
		String text = Locators_Sanity.toField_NewMessage.getText();
		System.out.println(text);
		if(!text.equals("123")) {
			for (int i = 0; i < 3; i++) {
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_POUND);
				minWait();
			}
		}
		Locators_Sanity.toField_NewMessage.clear();
	}

	public void send() throws InterruptedException {
		/*
		 * send sms 
		 */		
		customWait(2000);
		/*aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_MENU);
		minWait();
		aDriver.pressKeyCode(AndroidKeyCode.ENTER);		
		customWait(1000);*/
		clickMenuAndElement(Locators_Sanity.msg_send);
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
			wait.until(ExpectedConditions.visibilityOf(Locators_Sanity.now_Text));
			minWait();
			if (isElementExist(Locators_Sanity.now_Text)) {
				check=true;
				APP_LOGS.info("Message "+str+" sucessfully");
				sf.assertTrue(check, "Validation is Pass");
				test.log(LogStatus.PASS, "Message "+str+" sucessfully");
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

	public void endCall_RefDevice() {
		try {
			minWait();
			Runtime.getRuntime().exec("adb -s "+r_Id+" shell input keyevent 6");
			Thread.sleep(1000);
		} catch (Exception e) {
		}
	}

	public void holdFunctionStatus() throws InterruptedException, IOException
	{
		/*
		 * Validates hold status
		 */
		try {
			minWait();
			Runtime.getRuntime().exec("adb -s "+p_Id+" shell input keyevent 22");
			customWait(5000);
			validateInCallFunctionality("Hold","GsmCdmaConnection: [GsmCdmaConn] update: parent=HOLDING, hasNewParent=false, wasConnectingInOrOut=false, wasHolding=false, isConnectingInOrOut=false, changed=true","XP5S_Sanity_007");
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_RIGHT);
			customWait(5000);
			validateInCallFunctionality("Unhold","GsmCdmaConnection: [GsmCdmaConn] update: parent=ACTIVE, hasNewParent=false, wasConnectingInOrOut=false, wasHolding=true, isConnectingInOrOut=false, changed=true","XP5S_Sanity_007");
		}catch (NoSuchElementException e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "Element Not Found");
		}
	}

	public void validateInCallFunctionality(String function,String searchstring,String filename) throws InterruptedException, IOException
	{
		/*
		 * Validate call history
		 */
		SoftAssert SA = new SoftAssert();
		try {
			if(searchString(searchstring, filename))
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

	public void inCallFunctionality() throws InterruptedException, IOException, AWTException, org.json.simple.parser.ParseException
	{
		/*
		 * Validates in call functionality
		 */
		try {			
			customWait(3000);
			dailCallUsingDailPad(refNum);
			minWait();
			speakerStatus();
			minWait();
			//			muteFunctionStatus();
			minWait();
			endcall();		
		}catch (NoSuchElementException e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "Element Not Found");
		}
	}

	public void speakerStatus() throws InterruptedException, IOException, AWTException, org.json.simple.parser.ParseException
	{
		/*
		 * Validates speaker status
		 */
		try {
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_UP);
			customWait(2000);
			//	validateInCallFunctionality("Speaker On","Message received: USER_SWITCH_SPEAKER=1104, arg1=0: ICA.sAR->CARSM.pM_USER_SWITCH_SPEAKER","XP5S_Sanity_008");
			validateSpeaker("Speaker On", "CallAudioRouteStateMachine: setSystemAudioState: changing from [AudioState isMuted: false, route: EARPIECE, supportedRouteMask: EARPIECE, SPEAKER] to [AudioState isMuted: false, route: SPEAKER, supportedRouteMask: EARPIECE, SPEAKER]: ICA.sAR->CARSM.pM_USER_SWITCH_SPEAKER",
					"CallAudioRouteStateMachine: setSystemAudioState: changing from [AudioState isMuted: false, route: WIRED_HEADSET, supportedRouteMask: WIRED_HEADSET, SPEAKER] to [AudioState isMuted: false, route: SPEAKER, supportedRouteMask: WIRED_HEADSET, SPEAKER]: ICA.sAR->CARSM.pM_USER_SWITCH_SPEAKER", "XP5S_Sanity_008");
			startLog("XP5S_Sanity_008_1");
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_UP);
			customWait(2000);
			//validateInCallFunctionality("Speaker Off","Message received: USER_SWITCH_EARPIECE=1101, arg1=0: CSW.hCCC->CARSM.pM_USER_SWITCH_EARPIECE","XP5S_Sanity_008");
			validateSpeaker("Speaker Off", "CallAudioRouteStateMachine: setSystemAudioState: changing from [AudioState isMuted: false, route: SPEAKER, supportedRouteMask: EARPIECE, SPEAKER] to [AudioState isMuted: false, route: EARPIECE, supportedRouteMask: EARPIECE, SPEAKER]: ICA.sAR->CARSM.pM_USER_SWITCH_EARPIECE",
					"CallAudioRouteStateMachine: setSystemAudioState: changing from [AudioState isMuted: false, route: SPEAKER, supportedRouteMask: WIRED_HEADSET, SPEAKER] to [AudioState isMuted: false, route: WIRED_HEADSET, supportedRouteMask: WIRED_HEADSET, SPEAKER]: ICA.sAR->CARSM.pM_USER_SWITCH_HEADSET", "XP5S_Sanity_008_1");
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

	public void make_Call_from_RefDev() throws InterruptedException, IOException {

		try {
			minWait();
			Runtime.getRuntime().exec("adb -s "+r_Id+" shell am start -a android.intent.action.CALL -d tel:"+pryNum);
			minWait();
		} catch (Exception e) {
			e.printStackTrace();
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

	public void rejectWithSMS() throws InterruptedException, IOException
	{
		/*
		 * reject with SMS
		 */
		SoftAssert SA = new SoftAssert();
		try {
			//Receive call from reference device
			//	waituntilnew(Locators_Sanity.rejectWithSMSTextCallPage, 10000);
			customWait(8000);
			if(isElementExist(Locators_Sanity.rejectWithSMSTextCallPage)) {
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
			clickMenuAndElement(Locators_Sanity.searchOpt);
			changeToNumberMode_SMS(Locators_Sanity.searchFld_InMessages);
			inputData(refNum);
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
			clickCenterButton(1);
			if(isElementExist(Locators_Sanity.quickResponseSent))
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

	public void callFromHistory() throws IOException, InterruptedException {
		//initiate call from call log
		clickBtn(Locators_Sanity.first_No_In_CallLog);
		customWait(4000);
		Runtime.getRuntime().exec("adb -s "+p_Id+" shell input keyevent 5");		
		customWait(1000);
		endcall();
		minWait();
		aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
	}

	public void clearCallLogs() throws InterruptedException {
		try {
			if(isElementExist(Locators_Sanity.dailedNum)) {
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_MENU);
				APP_LOGS.info("Menu is clicked");
				minWait();
				for(int i=1; i<=10; i++) {
					if(isElementExist(Locators_Sanity.phone_DeleteCallLog)) {
						clickBtn(Locators_Sanity.phone_DeleteCallLog);
						minWait();
						clickBtn(Locators_Sanity.phone_ClearOptn);
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
			waituntilnew(Locators_Sanity.rejectWithSMSTextCallPage, 5000);
			if(isElementExist(Locators_Sanity.rejectWithSMSTextCallPage)) {
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
			if(isElementExist(Locators_Sanity.missedCall) && isElementExist(Locators_Sanity.declinedCall))
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
			if(isElementExist(Locators_Sanity.outgoingCall)&& isElementExist(Locators_Sanity.incomingCall))
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

	public void delete_All_Threads() throws InterruptedException {
		/*
		 * Method is used to delete All the SMS Threads.
		 */
		SoftAssert sf = new SoftAssert();
		try {
			minWait();
			while (true) {
				if (isElementExist(Locators_Sanity.first_sms_Thread)||isElementExist(Locators_Sanity.first_sms_Thread1)) {
					minWait();
					aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
					minWait();
					aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_CLEAR);
					minWait();
					clickBtn(Locators_Sanity.DELETE);
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

	public void createContact(String name,String num) throws InterruptedException, IOException, org.json.simple.parser.ParseException {
		/*
		 * create contacts with name, phone, email and address
		 */
		try {
			String Uid=JsonFileReaderAndWriter.primaryDevIdReader();
			deleteIfContactsPresent();
			customWait(1000);
			if(isElementExist(Locators_Sanity.Addcontacts)) {
				clickBtn(Locators_Sanity.Addcontacts);
				minWait();	
			}else {
				minWait();	
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_MENU);
				minWait();
				clickBtn(Locators_Sanity.contact_addContactsOptn);
			}

			enterTextToInputField(Locators_Sanity.contactName, name);
			minWait();	
			enterTextToInputField(Locators_Sanity.contact_Phone, num);
			for(int i=1; i<=5;i++) {
				minWait();	
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
			}
			minWait();	

			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_MENU);
			minWait();
			clickBtn(Locators_Sanity.contact_Save);
			minWait();		
			minWait();	
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
		} catch (NoSuchElementException e) {
			APP_LOGS.info("No Element found.");
			e.printStackTrace();
			test.log(LogStatus.ERROR, "No Such Element Found");
			Assert.fail();
		}
	}

	public void deleteIfContactsPresent() throws InterruptedException
	{
		/*
		 * Delete contacts if exist before test
		 */
		try {
			if(!isElementExist(Locators_Sanity.addContacts))
			{
				minWait();
				clickMenuAndElement(Locators_Sanity.Contact_Select);
				minWait();
				clickMenuAndElement(Locators_Sanity.Contact_SelectAll);
				minWait();
				clickMenuAndElement(Locators_Sanity.contact_delete);
				minWait();
				Locators_Sanity.contact_DeleteBtn.click();
				minWait();
			}
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			clickBackButton(2);
			APP_LOGS.info("Element Not Found");
			test.log(LogStatus.ERROR, "Element Not Found");

		}
	}


	public void deleteContact() throws InterruptedException  {	
		/*
		 * delete the contact from phone memory
		 */
		try {

			launch_an_app("contacts");
			clickMenuAndElement(Locators_Sanity.Contact_Select);
			customWait(500);

			clickMenuAndElement(Locators_Sanity.Contact_SelectAll);
			customWait(500);

			clickMenuAndElement(Locators_Sanity.contact_delete);
			customWait(500);
			clickBtn(Locators_Sanity.contact_DeleteBtn);
			minWait();	

			if(isElementExist(Locators_Sanity.contact_Test)) {
				clickBtn(Locators_Sanity.contact_Test);
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_MENU);
				minWait();	
				//				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_CLEAR);
				minWait();	
				minWait();
				clickBtn(Locators_Sanity.contact_delete);
				minWait();	
				clickBtn(Locators_Sanity.contact_DeleteBtn);
				minWait();	
			}
			if(isElementExist(Locators_Sanity.contact_Testing)) {
				clickBtn(Locators_Sanity.contact_Testing);
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_MENU);
				minWait();	
				//				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_CLEAR);
				minWait();	
				minWait();
				clickBtn(Locators_Sanity.contact_delete);
				minWait();	
				clickBtn(Locators_Sanity.contact_DeleteBtn);
				minWait();	
			}		
			APP_LOGS.info("Deleted all contacts");
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "No Such Element Found");
			Assert.fail();
		}	
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
			e.printStackTrace();
			test.log(LogStatus.ERROR, "No Such Element Found");
			Assert.fail();
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
			wait.until(ExpectedConditions.visibilityOf(Locators_Sanity.draft_Text));
			minWait();
			if (isElementExist(Locators_Sanity.draft_Text)) {
				check=true;
				APP_LOGS.info(str+" saved as Draft is sucessfully");
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
		 * Delete Selected Msg
		 */
		try {
			minWait();	
			clickMenuAndElement(Locators_Sanity.selectOptInMsg);
			minWait();
			clickMenuAndElement(Locators_Sanity.deleteAllThreadsInMsg);
			minWait();
			clickBtn(Locators_Sanity.contact_DeleteBtn);
			customWait(2000);
		} catch (org.openqa.selenium.NoSuchElementException e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "No Such Element Found");
			Assert.fail();
		}
	}

	public void validateDeleteSMS(String str) throws InterruptedException {
		//validate after deletion

		if (validate_presenceOfElement(Locators_Sanity.noConversations,"NoConversations")) {
			check = true;
			test.log(LogStatus.INFO, "Able to delete " +str +" verified");
			Assert.assertTrue(check);
		}
		else {
			test.log(LogStatus.ERROR, str+" is not able to delete");
			Assert.fail();
		}
	}

	public void navigateTo_NewMessage() throws InterruptedException, IOException {
		SoftAssert sf = new SoftAssert();
		try {
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_MENU);
			minWait();
			clickBtn(Locators_Sanity.New_msgOptn);
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
			clickBtn(Locators_Sanity.attachOthers);
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
			clickBtn(Locators_Sanity.photoCaptureIcon);
			customWait(2000);
			clickBtn(Locators_Sanity.OK);
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
			APP_LOGS.info("Attachment added sucessfully.");
			sf.assertTrue(check, "Attachment added sucessfully, validation PASS.");
		} else {
			sf.fail();
			test.log(LogStatus.ERROR, "Error in adding the Attachment to SMS.");
		}
		sf.assertAll();		
	}

	public void enter_ToField() throws InterruptedException {
		SoftAssert sf = new SoftAssert();
		try {
			changeToNumberMode();
			minWait();
			clickBtn(Locators_Sanity.toField_NewMessage);
			customWait(2000);
			Runtime.getRuntime().exec("adb -s "+p_Id+" shell input text "+refNum);
			//senterTextToInputField(Locators_Sanity.toField_NewMessage, cellNo);
			customWait(2000);
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
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
			customWait(2000);
			enterTextToInputField(Locators_Sanity.typeMsg_Field, typeMessage);
		} catch (Exception e) {
			e.printStackTrace();
			sf.fail();
			test.log(LogStatus.ERROR, "Error in Typing a Message.");
		}
		sf.assertAll();
	}

	public void addContactWithAllFields(String namePrefix,String firstName,String middleName,String lastName,String nameSuffix,String phoneticLastName,String phoneticMiddleName,String phoneticFirstName,String nickName,String company,String title,String SIP,String Email ,String address,String IM,String website,String Notes) throws InterruptedException, IOException, org.json.simple.parser.ParseException
	{
		/*
		 * add contact with all fields and validate
		 */
		String Uid=JsonFileReaderAndWriter.primaryDevIdReader();
		try {
			checkAddContactsOption();
			minWait();
			ScrollToElement(Locators_Sanity.scrollpage, "More fields");
			minWait();
			Locators_Sanity.contact_MorefieldsOptn.click();
			minWait();
			aDriver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().className(\"android.widget.ScrollView\")).scrollIntoView(new UiSelector().text(\"Name\"))");
			minWait();
			boolean check1 = addImageInContact();
			Locators_Sanity.expandFld.click();
			minWait();
			boolean check2 = addNameFields(namePrefix, firstName, middleName, lastName);
			minWait();
			ScrollToElement(Locators_Sanity.scrollpage, "Title");
			minWait();
			boolean check3 = addPhoneticNameFields(nameSuffix, phoneticLastName, phoneticMiddleName, phoneticFirstName, nickName);
			minWait();
			ScrollToElement(Locators_Sanity.scrollpage, "Phone");
			minWait();
			boolean check4 = addComapnyAndPhoneNumber(company, title);
			minWait();
			ScrollToElement(Locators_Sanity.scrollpage, "Email");
			minWait();
			boolean check5 = addSIPEmailAndAddress(SIP, Email, address);
			minWait();
			ScrollToElement(Locators_Sanity.scrollpage, "Website");
			minWait();
			boolean check6 = addOtherFieldsInContact(IM, website, Notes);
			minWait();
			if((check1)&&(check2)&&(check3)&&(check4)&&(check5)&&(check6))
			{
				clickMenuAndElement(Locators_Sanity.saveButton);
			}else {
				clickMenuAndElement(Locators_Sanity.saveButton);
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
			if(isElementExist(Locators_Sanity.addContacts)) {
				clickBtn(Locators_Sanity.addContacts);
			}else{
				minWait();
				clickMenuAndElement(Locators_Sanity.addContactOpt);
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
			Locators_Sanity.changePhotoOpt.click();
			minWait();
			Locators_Sanity.takePhotoOpt.click();
			minWait();
			Locators_Sanity.captureOpt.click();
			minWait();
			Locators_Sanity.imageOkOpt.click();
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
			enterTextToInputField(Locators_Sanity.namePrefixFld,namePrefix);
			minWait();
			enterTextToInputField(Locators_Sanity.firstNameFld,firstName);
			minWait();
			enterTextToInputField(Locators_Sanity.middleNameFld,middleName);
			minWait();
			enterTextToInputField(Locators_Sanity.lastNameFld,lastName);
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
			enterTextToInputField(Locators_Sanity.nameSuffixFld,nameSuffix);
			minWait();
			Locators_Sanity.expandFld.click();
			enterTextToInputField(Locators_Sanity.phoneticLastName,phoneticLastName);
			minWait();
			enterTextToInputField(Locators_Sanity.phoneticMiddleName,phoneticMiddleName);
			minWait();
			enterTextToInputField(Locators_Sanity.phoneticFirstName,phoneticFirstName);
			minWait();
			enterTextToInputField(Locators_Sanity.nickNameFld,nickName);
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
			enterTextToInputField(Locators_Sanity.companyFld,company);
			minWait();
			enterTextToInputField(Locators_Sanity.titleFld,title);
			minWait();
			enterTextToInputField(Locators_Sanity.contact_Phone,refNum);
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
		//	enterTextToInputField(Locators_Sanity.sipFld,SIP);
			minWait();
			enterTextToInputField(Locators_Sanity.emailFld,Email);
			minWait();
			ScrollToElement(Locators_Sanity.scrollpage, "Address");
			minWait();
			enterTextToInputField(Locators_Sanity.addressFld,address);
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
			enterTextToInputField(Locators_Sanity.IMFld,IM);
			minWait();
			enterTextToInputField(Locators_Sanity.websiteFld,website);
			minWait();
			ScrollToElement(Locators_Sanity.scrollpage, "Notes");
			minWait();
			enterTextToInputField(Locators_Sanity.notesFld,Notes);
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
			if(!isElementExist(Locators_Sanity.noContacts)){
				String actualString = Locators_Sanity.addedContact.getText();
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
			clickMenuAndElement(Locators_Sanity.saveButton);
			clickBackButton(1);
			customWait(2000);
			Runtime.getRuntime().exec("adb -s "+p_Id+" shell input text "+ Name);
			customWait(3000);
			validateAddedContact(Name);
			minWait();
			clickMenuButton();
			if(isElementExist(Locators_Sanity.copyToSIMOpt)) {
				clickBtn(Locators_Sanity.copyToSIMOpt);
				clickBackButton(1);
				validatecopiedContactToSIM();
			}else if(isElementExist(Locators_Sanity.copyToSIM1Opt)) {
				clickBtn(Locators_Sanity.copyToSIM1Opt);
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
			enterTextToInputField(Locators_Sanity.contactName, Name);
			customWait(3000);
			enterTextToInputField(Locators_Sanity.contact_Phone, refNum);
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
			if(Locators_Sanity.matchedContactsFld.getText().contains("2 Contacts matched")){
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

	public void deleteContact(String name1,String name2) throws InterruptedException, IOException, org.json.simple.parser.ParseException
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
			Locators_Sanity.contact_DeleteBtn.click();
			customWait(2000);
			clickBackButton(1);
			customWait(2000);
			Runtime.getRuntime().exec("adb -s "+Uid+" shell input text "+ name2);
			customWait(3000);
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
			minWait();
			clickMenuAndElement(Locators_Sanity.contact_delete);
			minWait();
			Locators_Sanity.contact_DeleteBtn.click();
			minWait();
			validatedeletedcontact(name2);
			clickBackButton(1);
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
			if(isElementExist(Locators_Sanity.noContacts)){
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


	public void validateBrowseInternet() throws InterruptedException {
		/*
		 * Validate while browsing google.com
		 */
		SoftAssert sf= new SoftAssert();
		try {
			customWait(2000);
			enterUrl("www.Google.com");
			customWait(10000);
			if((isElementExist(Locators_Sanity.SearchIcon_Google_Oreo))||(isElementExist(Locators_Sanity.Searchenter_Google_Oreo))) {
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

	public void enterUrl(String newurl) throws InterruptedException {
		/*
		 * enter url when data is enabled
		 */
		try {
			minWait();
			clickBtn(Locators_Sanity.DefaultUrlTxt);
			enterTextToInputField(Locators_Sanity.DefaultUrlTxt,newurl);
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

	public void disableCellularData() throws InterruptedException {
		try {
			clickBtn(Locators_Sanity.networkAndInternet);
			minWait();
			if(isElementExist(Locators_Sanity.MobileNetwrks_oreo)) {
				minWait();
				clickBtn(Locators_Sanity.MobileNetwrks_oreo);
				APP_LOGS.info("Selected Mobile Networks");
				minWait();
				disableFeature(Locators_Sanity.enabled_Data_Oreo,Locators_Sanity.disabled_Data_Oreo);
				minWait();
				clickBtn(Locators_Sanity.okBtn);
				minWait();
			}
			else {
				clickBtn(Locators_Sanity.dataUsageOptn);
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
				minWait();
				disableFeature(Locators_Sanity.cellular_DataON,Locators_Sanity.cellular_DataOFF);
				minWait();
				clickBtn(Locators_Sanity.okBtn);
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
			clickBtn(Locators_Sanity.networkAndInternet);
			customWait(2000);
			navigateToWifiOption();
			minWait();
			if(isElementExist(Locators_Sanity.disabled)) {
				enableFeature(Locators_Sanity.enabled,Locators_Sanity.disabled,Locators_Sanity.switch_Title);
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);		
				customWait(3000);
			}		
			else {
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);		    	
			}
			customWait(3000);
			String getSummary = Locators_Sanity.summaryWIFI_oreo.getText();
			System.out.println(getSummary);
			minWait();
			if(getSummary.contains("Not connected")) {
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
				minWait();
				clickCenterButton(2);
				customWait(4000);
				scrollToText("IDCSONWAP");
			//	selectIDcwifi();      
				minWait();
				if(isElementExist(Locators_Sanity.wifi_IDC_ForgetBtn)) {
					clickBtn(Locators_Sanity.wifi_IDC_ForgetBtn);
					minWait();
					scrollToText("IDCSONWAP");
					//selectIDcwifi();    
				}
				clickBtn(Locators_Sanity.wifi_show_Pswd);
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_UP);
				customWait(1000);

				changeToNumberMode_SMS(Locators_Sanity.wifi_IDC_Psswd);
				customWait(9000);
				if(isElementExist(Locators_Sanity.wifi_IDC)) {
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

				else if(isElementExist(Locators_Sanity.wifi_Cannada)) {
					Runtime.getRuntime().exec("adb -s "+p_Id+" shell input text rrxfo68837");
				}

				else if(isElementExist(Locators_Sanity.wifi_Dellas)) {
					Runtime.getRuntime().exec("adb -s "+p_Id+" shell input text yellowbook143");
				}

				minWait();
				customWait(1000);
				String psswrd = Locators_Sanity.wifi_IDC_Psswd.getText();
				System.out.println(psswrd);
				clickBtn(Locators_Sanity.wifi_IDC_ConnectBtn);
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

	public void validateDisableEnableWiFi() throws InterruptedException, IOException {
		/*
		 * disable enable wiFi
		 */
		SoftAssert sf = new SoftAssert();
		try {
			launch_an_app("settings");
			minWait();
			clickBtn(Locators_Sanity.networkAndInternet);
			minWait();
			navigateToWifiOption();
			minWait();
			customWait(2000);
			disableFeature(Locators_Sanity.enabled,Locators_Sanity.disabled);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			launch_an_app("browser");
			customWait(4000);
			if(isElementExist(Locators_Sanity.NoNewtwrkErroeMsg)||isElementExist(Locators_Sanity.webpageNotavailable)) {
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
			clickBtn(Locators_Sanity.networkAndInternet);
			minWait();
			navigateToWifiOption();
			minWait();

			enableFeature(Locators_Sanity.enabled,Locators_Sanity.disabled,Locators_Sanity.switch_Title);
			minWait();
			customWait(4000);
			clearRecentApps();
			launch_an_app("browser");
			customWait(4000);
			enterUrl("www.google.com");
			customWait(4000);
			if((isElementExist(Locators_Sanity.SearchIcon_Google_Oreo))||(isElementExist(Locators_Sanity.Searchenter_Google_Oreo))) 
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
	
	public void selectIDcwifi() throws InterruptedException {
		/*
		 * Select IDC wifi which is available
		 */
		try {
			/*customWait(4000);
			do {
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
			}while(!(isElementExist(Locators_Sanity.wifi_IDC) || isElementExist(Locators_Sanity.wifi_Cannada) || isElementExist(Locators_Sanity.wifi_Dellas)));
			
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
			minWait();
			String wifi_name = Locators_Sanity.wifi_title_name.getText();
			
			switch (wifi_name) {
			
			case "IDCSONWAP":
				clickBtn(Locators_Sanity.wifi_IDC);
				break;
				
			case "dlink-F092-media" :
				clickBtn(Locators_Sanity.wifi_Cannada);
				break;
				
			case "1234567890sonim":
				clickBtn(Locators_Sanity.wifi_Dellas);
				break;

			default:
				break;
				
			}
			*/
			for(int i=1; i<=50; i++) {
				if(isElementExist(Locators_Sanity.wifi_IDC)) {
					minWait();
					clickBtn(Locators_Sanity.wifi_IDC);
					APP_LOGS.info("IDC available secured wifi is Selected");
					break;
				}

				else if(isElementExist(Locators_Sanity.wifi_Cannada)) {
					minWait();
					clickBtn(Locators_Sanity.wifi_Cannada);
					APP_LOGS.info("Cannada available secured wifi is Selected");
					break;
				}

				else if(isElementExist(Locators_Sanity.wifi_Dellas)) {
					minWait();
					clickBtn(Locators_Sanity.wifi_Dellas);
					APP_LOGS.info("Cannada available secured wifi is Selected");
					break;
				}
				else {
					minWait();
					aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
					continue;
				}
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {
			APP_LOGS.info("No Element found.");
			minWait();	
			Assert.fail();
		}
	}

	public void selectMoreOptions() throws InterruptedException, IOException, org.json.simple.parser.ParseException {
		/*
		 *select more option in settings
		 */	aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_HOME);
		 String Uid=JsonFileReaderAndWriter.primaryDevIdReader();
		 //		customWait(4000);
		 try {
			 Runtime.getRuntime().exec("adb -s "+Uid+" shell am start -n com.android.settings/com.android.settings.Settings");
			 minWait();
			 APP_LOGS.info("Launched Settings icon sucessfully");
			 for (int iter = 1; iter <= 9; iter++) {
				 if (isElementExist(Locators_Sanity.moreOptn)) {
					 APP_LOGS.info("More Option is displayed sucessfully");
					 minWait();	
					 clickBtn(Locators_Sanity.moreOptn);
					 break;					
				 } else {
					 aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
					 continue;					
				 }
			 }clickBtn(Locators_Sanity.date_time);
		 } catch (NoSuchElementException e) {
			 APP_LOGS.info("More Option is not displayed.");
			 test.log(LogStatus.ERROR, "No Such Element Found");
			 minWait();	
			 Assert.fail();
		 }

	}

	public void checkFlightmodeStatusAndValidate() {
		try {
			navigateToAirplaneMode();
			minWait();
			validateEnabledisableFlightmode(Locators_Sanity.enabled_FlightMode,"Enable");
			minWait();
			clickBtn(Locators_Sanity.Flightmode);
			validateEnabledisableFlightmode(Locators_Sanity.disabled_Flightmode,"Disable");
			if(isElementExist(Locators_Sanity.enabled_FlightMode)) {
				clickBtn(Locators_Sanity.Flightmode);
				clickBackButton(2);
			}else {
				clickBackButton(2);
			}
		}catch (Exception e) {
			APP_LOGS.info("No Element found.");
			e.printStackTrace();
			test.log(LogStatus.ERROR, "No Such Element Found");
		}
	}

	public void validateEnabledisableFlightmode(WebElement element,String mode) throws InterruptedException {
		/*
		 * Validates enable and disable flight mode with phone settings
		 */	
		//		customWait(1000);
		SoftAssert sf = new SoftAssert();
		try {
			if(isElementExist(element)) {
				minWait();	
				check = true;
				APP_LOGS.info("Airplane mode is enabled sucessfully");
				test.log(LogStatus.INFO, mode+" Airplane mode with phone settings validated sucessfully");
				sf.assertTrue(check, "Validation is Pass");
			}else{
				APP_LOGS.info("test case is failed");
				test.log(LogStatus.ERROR, "Failed to validated "+mode+" Airplane mode with phone settings");
				sf.fail();
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {
			APP_LOGS.info("No Element found.");
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
			clickBtn(Locators_Sanity.connectedDevices_oreo);
			minWait();
			clickBtn(Locators_Sanity.Bluetooth);
			minWait();
			enable(Locators_Sanity.enabled,Locators_Sanity.disabled,Locators_Sanity.switch_Title);
			customWait(2000);
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
			minWait();
			if(isElementExist(Locators_Sanity.enabled)) {
				check1 = true;
				test.log(LogStatus.INFO, "Enable BT feature is verified");
				APP_LOGS.info(" check1");
			}
			customWait(2000);
			disableFeature(Locators_Sanity.enabled,Locators_Sanity.disabled);
			if(isElementExist(Locators_Sanity.disabled)) {
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

		clickBtn(Locators_Sanity.connectedDevices_oreo);
		minWait();
		clickBtn(Locators_Sanity.Bluetooth);
		minWait();
		enable(Locators_Sanity.enabled,Locators_Sanity.disabled,Locators_Sanity.switch_Title);
		WebDriverWait wait = new WebDriverWait(getDriver(), 12000);
		minWait();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@text='CANCEL']")));

		System.out.println("BT Cancel");
		customWait(4000);
		minWait();
		System.out.println("BT*********");
		if(isElementExist(Locators_Sanity.BT_Devices)) {
			check1= true; 
			APP_LOGS.info( "BT ON "+check1);
		}
		customWait(3000);
		disableFeature(Locators_Sanity.enabled,Locators_Sanity.disabled);
		if(isElementExist(Locators_Sanity.BluetoothOff)) {
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

	public void validateDisable(WebElement element) throws InterruptedException {
		/*
		 * Validate data roaming is enable 
		 */
		SoftAssert S1 = new SoftAssert();
		try {
			clickBtn(Locators_Sanity.OK_Btn);
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
				if(isElementExist(Locators_Sanity.quickSettings)|| isElementExist(Locators_Sanity.quick_Toggle)) {
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
				System.out.println("ckeck0");
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
				System.out.println("check1");
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

	public void navigateToAirplaneModeOptionQuickSettings() throws InterruptedException, IOException {
		/*
		 * Select Screen lock or Simcard Lock
		 */		
		try {
			minWait();
			scrollToElementWithDpadDown(Locators_Sanity.Flightmode);
			minWait();
			clickBtn(Locators_Sanity.Flightmode);
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			test.setDescription("Failure Reason: Element not found");
			Assert.fail();
		}
	}

	public void validate_phone_Charging() throws InterruptedException{
		//			navigate_to_NotificationScreen();
		//			ScrollToElement(Locators_Sanity.settings_frame, "Battery");
		try {
			minWait();
			clickBtn(Locators_Sanity.status);
			customWait(1000);
			if(isElementExist(Locators_Sanity.Charging_OverUSB_oreo)||isElementExist(Locators_Sanity.Full_OverUSB)){
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

	public void navigateTo_DateAndTime() throws InterruptedException, IOException{
		/*
		 * Navigate to Date & Time  in native settings.
		 */	
		try {
			navigateToSystemSettings(Locators_Sanity.systemInsettingsList);
			minWait();
			aDriver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().resourceId(\"com.android.settings:id/main_content\")).scrollIntoView(new UiSelector().text(\"Date & time\"))");
			minWait();
			clickBtn(Locators_Sanity.date_time);
			APP_LOGS.info("Date and Time is displayed sucessfully");
		} catch (NoSuchElementException e) {
			APP_LOGS.info("Date and Time is not displayed.");
			e.printStackTrace();
			test.log(LogStatus.ERROR, "No Such Element Found");
			Assert.fail();
		}
	}

	public void validate_getDate_Time_TimeZone() throws InterruptedException {
		/*
		 * check the date ,time and timezone.
		 */
		SoftAssert Sv = new SoftAssert();
		try {
			if(Locators_Sanity.Autodate_time.isEnabled()){
				APP_LOGS.info("Automatic date and Time is enabled");
			}
			else {
				clickBtn(Locators_Sanity.Autodate_time);
			}
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
			minWait();	
			if(Locators_Sanity.Autotimezone.isEnabled()){
				APP_LOGS.info("Automatic date and Time is enabled");
			}
			else {
				clickBtn(Locators_Sanity.Autotimezone);
			}

			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
			minWait();	
			clickBtn(Locators_Sanity.setDate);
			minWait();	

			Formatter fmt = new Formatter();
			Calendar cal = Calendar.getInstance();
			//actual arguments for below method("%tB %td %tY", cal, cal, cal) and "," is concatenated.
			fmt.format("%tB %te"+","+" %tY", cal, cal, cal);
			String date1 = fmt.toString();
			String get_Date = Locators_Sanity.setDateSummary.getText();
			if(get_Date.equals(date1)){
				check1 = true;
				APP_LOGS.info("Element found Date : "+get_Date);
				APP_LOGS.info("check1: "+check1);
			}else{
				APP_LOGS.info("Date element is not found");
				APP_LOGS.info("check1: "+check1);
			}
			minWait();	

			scrollToElementWithDpadDown(Locators_Sanity.use24HourFormatOpt);
			minWait();
			if(isElementExist(Locators_Sanity.timeFormatDisabled_oreo)){
				clickBtn(Locators_Sanity.use24HourFormatOpt);
				minWait();
				clickBackButton(1);
				minWait();
				clickBtn(Locators_Sanity.date_time);
			}else{
				clickBackButton(1);
				minWait();
				clickBtn(Locators_Sanity.date_time);
			}
			java.util.Date today = new java.util.Date();
			Time fmt1 = new java.sql.Time(today.getTime());
			String time1 = fmt1.toString();

			clickBtn(Locators_Sanity.settime);
			minWait();	
			String get_Time = Locators_Sanity.setTimeSummary.getText();
			minWait();	
			if(time1.contains(get_Time)){
				check2 = true;
				APP_LOGS.info("Element found Time : "+get_Time);
				APP_LOGS.info("check2: "+check2);
			}else{
				APP_LOGS.info("Time element is not found");
				APP_LOGS.info("check2: "+check2);
			}

			scrollToElementWithDpadDown(Locators_Sanity.use24HourFormatOpt);

			clickBtn(Locators_Sanity.settimeZone);
			minWait();	
			String get_Timezone = Locators_Sanity.setTimeZoneSummary_oreo.getText();
			minWait();	
			if(get_Timezone.contains("GMT+05:30")){
				check3 = true;
				APP_LOGS.info("Element found Time : "+get_Timezone);
				APP_LOGS.info("check3: "+check3);
			}else{
				APP_LOGS.info("Time element is not found");
				APP_LOGS.info("check3: "+check3);
			}
			minWait();
			scrollToElementWithDpadDown(Locators_Sanity.use24HourFormatOpt);
			minWait();
			clickBtn(Locators_Sanity.use24HourFormatOpt);
			clickBackButton(1);
			minWait();	

			if((check1)&&(check2)&&(check3)){
				check = true ;
				APP_LOGS.info("Date,Time and Time Zone validated sucessfully");
				test.log(LogStatus.INFO, "Date,Time and Time Zone validated sucessfully");
				test.log(LogStatus.PASS, "Test case status is Passed");	
				Sv.assertTrue(check, "Date,Time and Time Zone validated sucessfully");
			}else{
				APP_LOGS.info("Failed to validate Date,Time and Time Zone");
				test.log(LogStatus.INFO, "Failed to validate Date,Time and Time Zone");
				Sv.fail("Failed to validate Date,Time and Time Zone");
			}
			clickBackButton(2);
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "No Such Element Found");
			Sv.fail();
		}
		Sv.assertAll();
	}

	public void add_APN(String apnName, String apn, String apnType) throws InterruptedException, IOException {
		/*
		 * This Method is to Add the APN
		 */
		try {
			navigateTo_APN();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_MENU);
			minWait();
			clickBtn(Locators_Sanity.new_APN);
			minWait();
			clickBtn(Locators_Sanity.apn_Name);
			minWait();
			enterTextToInputField(Locators_Sanity.textField_PopUp,apnName);
			minWait();
			clickBtn(Locators_Sanity.OK_Button);
			minWait();
			clickBtn(Locators_Sanity.apn);
			minWait();
			enterTextToInputField(Locators_Sanity.textField_PopUp, apn);
			minWait();
			clickBtn(Locators_Sanity.OK_Button);
			minWait();
			scrollToElementWithDpadDown(Locators_Sanity.apn_Type); 
			minWait();
			clickBtn(Locators_Sanity.apn_Type);
			minWait();
			enterTextToInputField(Locators_Sanity.textField_PopUp, apnType);
			minWait();
			clickBtn(Locators_Sanity.OK_Button);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_MENU);
			minWait();
			clickBtn(Locators_Sanity.save);
			minWait();
		} catch (org.openqa.selenium.NoSuchElementException e) {
			APP_LOGS.info("No Element found.");
			test.log(LogStatus.ERROR, "No Such Element Found");	
			Assert.fail();
		}

	}

	public void validate_Add(String apnName) throws InterruptedException, IOException {
		/*
		 * This Method is to validate Added or Deleted APN's
		 */
		SoftAssert sf =new SoftAssert();
		try {
			navigateTo_APN();
			String apnName1 = aDriver.findElement(By.xpath("//android.widget.TextView[@text='"+apnName+"']")).getText();
			minWait();
			if (apnName.equals(apnName1)) {
				APP_LOGS.info("Added APN is Present");
				check=true;
				test.log(LogStatus.INFO, " APN is Added");
				sf.assertTrue(check, "Validation is Pass");
			} else {
				APP_LOGS.info("APN is not added,Failed");
				test.log(LogStatus.ERROR, "APN is not added,Failed");
				sf.fail();
			} 
			minWait();	
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			minWait();	
			minWait();	
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
		} catch (org.openqa.selenium.NoSuchElementException e) {
			APP_LOGS.info("No Element found.");
			test.log(LogStatus.ERROR, "No Such Element Found");
			sf.fail();
		}
		sf.assertAll();
	}

	public void validateEditionDeletionApn() throws InterruptedException {
		/*
		 * validate default APn should not able to edit and delete
		 */
		SoftAssert sf = new SoftAssert();
		try {
			minWait();	
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_MENU);
			minWait();	
			minWait();	
			if(isElementExist(Locators_Sanity.edit)) {
				check1=true;
				APP_LOGS.info("check1: "+check1);
				APP_LOGS.info("Edition of Apn is displayed unsuccesfully");
			}else{
				check1=true;
				APP_LOGS.info("check1: "+check1);
				APP_LOGS.info("Edition of Apn is displayed succesfully");			
			}
			clickBtn(Locators_Sanity.edit);
			minWait();
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_MENU);
			minWait();
			minWait();
			if(isElementExist(Locators_Sanity.delete_APN)) {	
				check2=true;
				APP_LOGS.info("check2: "+check2);
				APP_LOGS.info("Deletion Apn is displayed unsuccesfully");
			}else{
				check2=true;
				APP_LOGS.info("check2: "+check2);
				APP_LOGS.info("Deletion Apn is displayed succesfully");
			}
			if((check1)&&(check2)){
				check=true;
				APP_LOGS.info("Validated sucessfully Edition and Deletion of Default APn");		
				test.log(LogStatus.INFO, "Validated sucessfully Edition and Deletion of Default APn");
				sf.assertTrue(check, "Validation is Pass");
			}else{
				APP_LOGS.info("Validated is not sucessful Edition and Deletion of Default APn ");	
				test.log(LogStatus.INFO, " Edition and Deletion of Default APn is Unsucessfully");
				sf.fail();
			}
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_MENU);	
			minWait();
			clickBtn(Locators_Sanity.RestoreDefault);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
		} catch (org.openqa.selenium.NoSuchElementException e) {
			APP_LOGS.info("No Element found.");
			sf.fail();
		}
		sf.assertAll();
	}

	public void navigateTo_APN() throws InterruptedException, IOException {
		/*
		 * This Method will Navigate to the APN's  
		 */
		try {
			launch_an_app("settings");
			navigateToNetworkAndInternetOptions();
			minWait();
			if(isElementExist(Locators_Sanity.MobileNetwrks_oreo)) {
				clickBtn(Locators_Sanity.MobileNetwrks_oreo);
			}
			if(isElementExist(Locators_Sanity.advanced_optn_oreo)) {
				clickBtn(Locators_Sanity.advanced_optn_oreo);
			}
			scrollToElementWithDpadDown(Locators_Sanity.access_Point_Names_oreo);
			minWait();
			clickBtn(Locators_Sanity.access_Point_Names_oreo);
			minWait();
		} catch (org.openqa.selenium.NoSuchElementException e) {
			APP_LOGS.info("No Element found.");
			test.log(LogStatus.ERROR, "No Such Element Found");
			Assert.fail();
		}
	}

	public void selectSettings(WebElement option) throws InterruptedException, IOException, org.json.simple.parser.ParseException {
		/*
		 * select particular setting option
		 */
		SoftAssert Sa = new SoftAssert();
		String Uid=JsonFileReaderAndWriter.primaryDevIdReader();
		try {
			launch_an_app("settings");
			minWait();
			APP_LOGS.info("Launched Settings icon sucessfully");
			for (int iter= 1; iter<= 23; iter++) {
				if (isElementExist(option)) {
					String getName= option.getText();
					APP_LOGS.info("Settings Option is displayed sucessfully" +getName);
					minWait();	
					clickBtn(option);
					break;					
				} else {
					aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
					continue;					
				}
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {
			APP_LOGS.info("No Element found.");
			e.printStackTrace();
			test.log(LogStatus.ERROR, "No Such Element Found");
			Sa.fail();
		}
		Sa.assertAll();
	}

	public void validateInternalStorage() throws InterruptedException {
		/*
		 * Validate internal storage
		 */
		SoftAssert sf = new SoftAssert();
		try {
			minWait();
			if(isElementExist(Locators_Sanity.internalStorageTxt_Oreo))
			{
				check1 = true;
				APP_LOGS.info("check1: " + check1);
				APP_LOGS.info("Internal_Storage_text Element is present");
				String getAlertText = Locators_Sanity.internalStorage_Value_Txt.getText();
				APP_LOGS.info("internal_Storage_value_text: " + getAlertText);
			}else
			{
				APP_LOGS.info("check1: "+check1);
				APP_LOGS.info("Internal_Storage_text Element is not present");			
			}								
			if(isElementExist(Locators_Sanity.Available_Progress_Bar))
			{
				check2 = true;
				APP_LOGS.info("check2: " + check2);
				APP_LOGS.info("Internal_Storage_Available_bar Element is present");
				String getAlertText = Locators_Sanity.Available_Value_Txt.getText();
				APP_LOGS.info("internal_Storage_Available_value_text: " + getAlertText);
			}else
			{
				APP_LOGS.info("check2: "+check2);
				APP_LOGS.info("Internal_Storage_Available_bar Element is not present");			
			}				
			if((check1)&&(check2)){
				check=true;
				APP_LOGS.info("Internal Storage and available storage of the device is verified sucessfully.");							
				customWait(5000);
				test.log(LogStatus.INFO, "Internal Storage and available storage of the device is verified sucessfully.");
				sf.assertTrue(check, "Validation is Pass");
			}else{
				APP_LOGS.info("test case failed");			
				minWait();
				test.log(LogStatus.ERROR, " Internal Storage and available storage of the device is not verified.");
				sf.fail();
			}
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
		} catch (org.openqa.selenium.NoSuchElementException e) {
			APP_LOGS.info("No Element found.");
			e.printStackTrace();
			sf.fail();
		}
		sf.assertAll();
	}	

	public void volumeUpKey() throws InterruptedException{
		/*
		 * Clicks volume button
		 */
		try {
			minWait();
			for(int i=0;i<16;i++){
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_VOLUME_UP);
			}
			validateVolumeKeys("Up", "writeEvent level_changed STREAM_SYSTEM 15");
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "Element Not Found");
		}
	}

	public void validateVolumeKeys(String function,String searchstring) throws InterruptedException
	{
		/*
		 * validate volume keys up/down
		 */
		SoftAssert SA = new SoftAssert();
		try {
			boolean value = searchString(searchstring, "XP5S_Sanity_038");
			minWait();
			if(value)
			{
				check = true;
				APP_LOGS.info("Volume Key "+ function +" verified sucessfully");
				test.log(LogStatus.INFO, "Volume Key "+ function +" verified sucessfully");
				SA.assertTrue(check, "Volume Key "+ function +" verified sucessfully");	
			}else {
				APP_LOGS.info("Volume Key "+ function +" not verified");
				test.log(LogStatus.ERROR, "Volume Key "+ function +" not verified");
				SA.fail("Volume Key "+ function +" not verified");
			}
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			APP_LOGS.info("Element Not Found");
			test.log(LogStatus.ERROR, "Element Not Found");
			SA.fail();
		}
	}

	public void volumeDownKey() throws InterruptedException{
		/*
		 * Clicks volume button
		 */
		try {
			minWait();
			for(int i=0;i<16;i++){
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_VOLUME_DOWN);
			}
			validateVolumeKeys("Down", "writeEvent level_changed STREAM_SYSTEM 1");
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "Element Not Found");
		}
	}

	public void navigateToPTTKey() throws InterruptedException, IOException
	{
		/*
		 * Navigate to programmable keys,clicks on Select PTT key App option
		 */
		try {
			navigateToSystemSettings(Locators_Sanity.systemInsettingsList);
			minWait();
			clickBtn(Locators_Sanity.programmableKeyOpt);
			minWait();
			clickBtn(Locators_Sanity.selectPTTKeyOpt);
			minWait();
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR,"No Such Element Found");
		}						
	}

	public void selectApplicationAndLaunch() throws InterruptedException, IOException{
		/*
		 * select application for PTT key
		 */
		try {
			minWait();
			if(isElementExist(Locators_Sanity.contacts)){
				clickBtn(Locators_Sanity.contacts);
			}else if(isElementExist(Locators_Sanity.noApplicationInPtt)) {
				scrollToElementWithDpadDown(Locators_Sanity.contacts);
			}else{
				scrollToElementWithDpadUp(Locators_Sanity.noApplicationInPtt);
				minWait();
				scrollToElementWithDpadDown(Locators_Sanity.contacts);
			}
			clickBtn(Locators_Sanity.contacts);
			customWait(2000);
			if(isElementExist(Locators_Sanity.PTTPopUp)){
				clickBtn(Locators_Sanity.okBtn);
				customWait(2000);
			}
			Runtime.getRuntime().exec("adb -s "+p_Id+" shell input keyevent --longpress KEYCODE_PTT");
			minWait();
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR,"No Such Element Found");
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

	public void validateApplicationlaunch() throws InterruptedException
	{
		/*
		 * validate launched application 
		 */
		SoftAssert SA = new SoftAssert();
		try {
			customWait(2000);
			if (isElementExist(Locators_Sanity.contacts) || isElementExist(Locators_Sanity.contact_FindContacts)) {
				check = true;
				APP_LOGS.info("Assigned contacts application launched succesfully");
				test.log(LogStatus.INFO,"Assigned contacts application launched succesfully");
				test.log(LogStatus.PASS, "Test case status is Passed");
				SA.assertTrue(check, "Assigned contacts application launched succesfully");

			} else {
				APP_LOGS.info("Application is not Launched");
				test.log(LogStatus.ERROR,"Application is not Launched");
				test.log(LogStatus.FAIL, "Test case status is Failed");
				SA.fail();
			}
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			APP_LOGS.info("Element Not Found");
			test.log(LogStatus.ERROR, "No Such Element Found");
			SA.fail();
		}
	}

	public void validateHomeKey() throws InterruptedException, IOException
	{
		/*
		 * validate menu key by launching menu tray
		 */
		try {
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_HOME);
			minWait();
			validateKey(Locators_Sanity.appsMenuScreen, "Home");
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "No Such Element Found");
		}
	}

	public void validateKey(AndroidElement element,String key) throws InterruptedException, IOException
	{
		/*
		 * validate menu key by launching menu tray
		 */
		SoftAssert SA = new SoftAssert();
		try {
			if(isElementExist(element)){
				check = true;
				APP_LOGS.info(key+" key validated succesfully");
				test.log(LogStatus.INFO,key+" key validated succesfully");
				SA.assertTrue(check, key+" key validated succesfully");
			}else if(element.getText().contains("Missed Events")){
				check = true;
				APP_LOGS.info(key+" key validated succesfully");
				test.log(LogStatus.INFO,key+" key validated succesfully");
				SA.assertTrue(check, key+" key validated succesfully");
			}else{
				APP_LOGS.info("Failed to validate "+key+" key");
				test.log(LogStatus.ERROR,"Failed to validate "+key+" key");
				SA.fail();
			}
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			APP_LOGS.info("Element Not Found");
			test.log(LogStatus.ERROR, "No Such Element Found");
			SA.fail();
		}
	}

	public void validateGreen_Red_Key() throws InterruptedException, IOException
	{
		/*
		 * validate Green key and Red by Initiating call
		 */
		try {
			minWait();
			launch_an_app("phone");
			minWait();
			searchField(refNum);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_CALL);
			customWait(4000);
			validateKey(Locators_Sanity.dialingOpt, "Green");
			customWait(2000);
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_ENDCALL);
			minWait();
			validateKey(Locators_Sanity.recentCallsScreen, "Red");
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "No Such Element Found");
		}
	}

	public void searchField(String data) throws InterruptedException, IOException
	{
		/*
		 * Enters given data in search field
		 */
		customWait(4000);
		Runtime.getRuntime().exec("adb -s "+p_Id+" shell input text "+data);
		customWait(3000);
	}

	public void validate_Menu_Back_Clear_Key() throws InterruptedException, IOException
	{
		/*
		 * validate Menu key,Back key and clear key in phone dialer application
		 */
		try {
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_MENU);
			minWait();
			validateKey(Locators_Sanity.sendMessageInDialer, "Menu");
			minWait();
			clickBtn(Locators_Sanity.sendMessageInDialer);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			minWait();
			validateKey(Locators_Sanity.recentCallsScreen, "Back");
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_CLEAR);
			minWait();
			validateKey(Locators_Sanity.deleteMsgPopUp,"Clear");
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "No Such Element Found");
		}
	}

	public void validateRecentKey() throws InterruptedException, IOException
	{
		/*
		 * validate Recent key 
		 */
		try {
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_APP_SWITCH);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_MENU);
			minWait();
			validateKey(Locators_Sanity.removeAllOpt, "Recent");
			minWait();
			clickBtn(Locators_Sanity.removeAllOpt);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "No Such Element Found");
		}
	}

	public void validateNavigationKeys() throws InterruptedException, IOException
	{
		/*
		 * validate Recent key 
		 */
		try {
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_ENDCALL);
			minWait();

			do {
				minWait();
				Runtime.getRuntime().exec("adb -s "+p_Id+" shell input keyevent 21");
				minWait();
			} while (!(isElementExist(Locators_Sanity.quickSettings) || isElementExist(Locators_Sanity.quick_Toggle)));
			minWait();
			validateLeftRightKey(Locators_Sanity.quickSettings,Locators_Sanity.quick_Toggle, "Left-Navigation");
			minWait();

			do {
				minWait();
				Runtime.getRuntime().exec("adb -s "+p_Id+" shell input keyevent 22");
				minWait();
			} while (!isElementExist(Locators_Sanity.missedEventsOpt));
			minWait();
			validateKey(Locators_Sanity.missedEventsOpt, "Right-Navigation");
			minWait();
			launch_an_app("settings");
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
			minWait();
			validateKey(Locators_Sanity.connectedDevices_oreo, "Down-Navigation");
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
			minWait();
			validateKey(Locators_Sanity.bluetooth, "Center-Navigation");
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_UP);
			minWait();
			validateKey(Locators_Sanity.networkAndInternet, "Up-Navigation");
			minWait();
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "No Such Element Found");
		}
	}

	public void validateLeftRightKey(AndroidElement element,AndroidElement element1,String key) throws InterruptedException, IOException
	{
		/*
		 * validate menu key by launching menu tray
		 */
		SoftAssert SA = new SoftAssert();
		try {
			if(isElementExist(element)||isElementExist(element1)){
				check = true;
				APP_LOGS.info(key+" key validated succesfully");
				test.log(LogStatus.INFO,key+" key validated succesfully");
				SA.assertTrue(check, key+" key validated succesfully");
			}else{
				APP_LOGS.info("Failed to validate "+key+" key");
				test.log(LogStatus.ERROR,"Failed to validate "+key+" key");
				SA.fail();
			}
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			APP_LOGS.info("Element Not Found");
			test.log(LogStatus.ERROR, "No Such Element Found");
			SA.fail();
		}
	}

	public void selectFMApp() throws InterruptedException {
		/*
		 * select Messaging application
		 */
		try {
			minWait();	
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_HOME);
			minWait();
			clickBtn(Locators_Sanity.Device_App);
			minWait();
			//		customWait(4000);
			clickBtn(Locators_Sanity.FM_App);
		} catch (org.openqa.selenium.NoSuchElementException e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "No Such Element Found");
			Assert.fail();
		}
	}

	public void turnOFF_FM() throws InterruptedException {
		try {
			customWait(3000);
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_MENU);
			for(int i=1;i<=8;i++) {
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
			}
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
			APP_LOGS.info("FM Radio is Turn Off");
		} 
		catch (org.openqa.selenium.NoSuchElementException e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "No Such Element Found");
			Assert.fail();
		}
	}

	public void validateOn_OFF_FM() throws InterruptedException {
		SoftAssert sf = new SoftAssert();
		try {
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_MENU);

			if(isElementExist(Locators_Sanity.OffFMImsg)){
				check = true;
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
				APP_LOGS.info("FM Radio Stopped/Off succesful.");
				test.log(LogStatus.INFO, "FM Radio Turned On/OFF succesfully.");
				sf.assertTrue(check, "Validation is Pass");
			} else {
				APP_LOGS.info("FM Radio is not Turn Off, unsuccesful.");
				test.log(LogStatus.ERROR, "FM Radio is not Turn Off/On, unsuccesful.");
				sf.fail();
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {
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
			clickBtn(Locators_Sanity.networkAndInternet);
			minWait();
			navigateToWifiOption();
			customWait(2000);
			disableFeature(Locators_Sanity.enabled,Locators_Sanity.disabled);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "Element Not Found");
		}
	}

	public void validateHomePageLoadedBrowser() throws InterruptedException {
		SoftAssert sf = new SoftAssert();
		try {
			launch_an_app("browser");
			customWait(2000);
			enterUrl("www.youtube.com");
			customWait(5000);
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_MENU);
			minWait();
			clickBtn(Locators_Sanity.HomePage_Browser);
			customWait(4000);
			if((isElementExist(Locators_Sanity.SearchIcon_Google_Oreo))||(isElementExist(Locators_Sanity.Searchenter_Google_Oreo))) {
				check =true;
				APP_LOGS.info("check: "+check);
				APP_LOGS.info("HomePageLoaded sucessful");
				test.log(LogStatus.INFO, "HomePage Loaded is sucessful");
				sf.assertTrue(check, "Validation is Pass");
			}
			else if(isElementExist(Locators_Sanity.Hompage_ATT)) {
				check =true;
				APP_LOGS.info("check: "+check);
				APP_LOGS.info("HomePageLoaded is sucessful");
				test.log(LogStatus.INFO, "HomePage Loaded is sucessful");
				sf.assertTrue(check, "Validation is Pass");
			}else{
				APP_LOGS.info("check: "+check);
				APP_LOGS.info("HomePageLoaded is not sucessful");
				test.log(LogStatus.INFO, "HomePage Loaded is not sucessful");
				sf.fail();
			}minWait();	
		} catch (org.openqa.selenium.NoSuchElementException e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "No Such Element Found");
			sf.fail();
		}
		sf.assertAll();
	}

	public void validateBrowseWithWiFi() throws InterruptedException, IOException {
		/*
		 * disable enable wiFi
		 */
		SoftAssert sf = new SoftAssert();
		try {
			launch_an_app("settings");
			minWait();
			clickBtn(Locators_Sanity.networkAndInternet);
			minWait();
			navigateToWifiOption();
			minWait();
			enableFeature(Locators_Sanity.enabled,Locators_Sanity.disabled,Locators_Sanity.switch_Title);
			minWait();
			customWait(4000);
			launch_an_app("browser");
			customWait(4000);
			enterUrl("www.google.com");
			customWait(4000);
			if((isElementExist(Locators_Sanity.SearchIcon_Google_Oreo))||(isElementExist(Locators_Sanity.Searchenter_Google_Oreo))) 
			{
				check =true;
				APP_LOGS.info("check: "+check);
				APP_LOGS.info("Enable the WiFi connection is sucessful");
				test.log(LogStatus.INFO, "Enable the WiFi connection is sucessful");
			}else{
				APP_LOGS.info("check: "+check);
				APP_LOGS.info("Enable WiFi connection is not sucessful");
				test.log(LogStatus.INFO, "Enable WiFi connection is not sucessful");
			}
			minWait();	
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			minWait();	
			minWait();	
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			minWait();	
		} catch (org.openqa.selenium.NoSuchElementException e) {
			APP_LOGS.info("No Element found.");
			sf.fail();
		}
		sf.assertAll();
	}

	public void deleteSavedRecorder() throws InterruptedException {
		try {
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_RIGHT);
			if(!isElementExist(Locators_Sanity.noRecordingsOpt)){
				APP_LOGS.info("Pressed List Icon sucessfully");
				minWait();
				minWait();
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_MENU);
				for(int i=1; i<=3; i++) {
					aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
				}
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
				minWait();
				APP_LOGS.info("Pressed Delete Icon sucessfully");
				minWait();
				minWait();
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_RIGHT);
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
				APP_LOGS.info("Pressed Delete Button sucessfully");
				Thread.sleep(2000);
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			}else{
				clickBackButton(1);
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {
			APP_LOGS.info("No Element found.");
			minWait();	
		}
	}

	public void recordSound() throws InterruptedException {
		try {
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
			minWait();
			selectRecordBtn();
			APP_LOGS.info("Pressed Record button sucessfully");
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_LEFT);
			APP_LOGS.info("Pressed Stop button sucessfully");
			customWait(2000);
		} catch (org.openqa.selenium.NoSuchElementException e) {
			APP_LOGS.info("No Element found.");
			minWait();	
			//				Assert.fail();
		}
	}

	public void selectRecordBtn() throws InterruptedException {

		for(int i=0; i<=2; i++) {
			if(isElementExist(Locators_Sanity.allowBtn)){
				customWait(2000);
				Locators_Sanity.allowBtn.click();
				APP_LOGS.info("Pressed Allow button sucessfully");
				minWait();
				APP_LOGS.info("Allow Button is displayed and Selected, succesful.");

			}else{
				APP_LOGS.info("Allow Button is not displayed.");

			}
		}

	}

	public void validateRecordedSoundSaved() throws InterruptedException {
		/*
		 * check recording name is saved in recorded list after saving
		 */
		SoftAssert SA= new SoftAssert();
		try {
			minWait();
			String SavedRecName=Locators_Sanity.recordingName.getText();
			minWait();
			System.out.println(SavedRecName);
			Locators_Sanity.saveBtn.click(); 
			APP_LOGS.info("Pressed Save button sucessfully");
			minWait();
			String ListSavedRecName=Locators_Sanity.saveBtnUnderRecList.getText();
			System.out.println(ListSavedRecName);
			minWait();

			if(ListSavedRecName.contains("New record")){
				check= true;
				APP_LOGS.info("Recorded sound using Sound recorder and validated successfully");
				test.log(LogStatus.INFO, "Recorded sound using Sound recorder and validated successfully");
				SA.assertTrue(check, "Recorded sound using Sound recorder and validated successfully");	
			}else{
				APP_LOGS.info("test Case Failed");
				test.log(LogStatus.ERROR, "Failed to validate Sound recorder");
				SA.fail();
			}	aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
		}
		catch (org.openqa.selenium.NoSuchElementException e) {
			APP_LOGS.info("Element not Found");
			e.printStackTrace();
		}
	}

	public void cameraPic() throws IOException, InterruptedException, org.json.simple.parser.ParseException {
		/*
		 * Take camera picture via camera application
		 */
		String Uid=JsonFileReaderAndWriter.primaryDevIdReader();

		try {
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_HOME);
			customWait(2000);
			Runtime.getRuntime().exec("adb -s "+Uid+" shell am start -n com.borqs.camera/com.borqs.camera.CameraActivity");
			customWait(2000);
			//			clickBtn(Locators_Sanity.CamerIcon);
			customWait(1000);
			if(isElementExist(Locators_Sanity.photocameraIcon)) {
				clickBtn(Locators_Sanity.photocameraIcon);
				//			APP_LOGS.info("clic);
			}
			else {
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_UP);
				//switch to camera
				minWait();
				clickBtn(Locators_Sanity.photocameraIcon);
			}
			minWait();
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			Assert.fail();
		}
	}

	public void validateBackCamera(String str,String str2, String TitleName,String fileName) throws InterruptedException, AWTException, IOException {
		/*
		 * Validate Gallery Folders from TitleName
		 */

		try {
			customWait(3000);
			if(searchString(str,fileName) || searchString(str2, fileName)) {				
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
			if(isElementExist(Locators_Sanity.camera_captured_data)) {	
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
				customWait(2000);
				if(isElementExist(Locators_Sanity.camera_captured_image)) {
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

	public void playMusic() throws InterruptedException, IOException{
		try {
			minWait();
			launch_an_app("music");
			minWait();
			if(isElementExist(Locators_Sanity.audiorecordings)) {
				minWait();
				clickBtn(Locators_Sanity.audiorecordings);
				minWait();
				clickBtn(Locators_Sanity.yourrecordings);
				minWait();
			}else {
				minWait();
				clickBtn(Locators_Sanity.yourrecordings);
				minWait();
				clickBtn(Locators_Sanity.audiorecordings);
				minWait();
				clickBtn(Locators_Sanity.yourrecordings);
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
			if(isElementExist(Locators_Sanity.musicIndicator)){
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
			String getSleepSummary = Locators_Sanity.Sleep_Summary.getText();
			System.out.println(getSleepSummary);
			customWait(2000);
			clickBtn(Locators_Sanity.Sleep_Optn);
			minWait();
			for(int i=1; i<=2; i++) {
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_UP);
			}
			clickBtn(Locators_Sanity.thirtyMinSleep_Optn);
			customWait(5000);
			String getSleepchange = Locators_Sanity.Sleep_Summary.getText();
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
			clickBtn(Locators_Sanity.Sleep_Optn);
			minWait();

			clickBtn(Locators_Sanity.eightHrsSleep_Optn);
			test.log(LogStatus.INFO, " Sleep Mode is set to maximum");
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			test.log(LogStatus.ERROR, " No such Element Found");
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
			clickBtn(Locators_Sanity.SwitchToCameraOptn);
			customWait(2000);
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_LEFT);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_UP);
			//	Runtime.getRuntime().exec("adb -s "+r_Id+" shell input keyevent 19");
			//	clickBtn(Locators_Sanity.swith_to_video);
			customWait(2000);
			clickBtn(Locators_Sanity.videocameraIcon);
			customWait(2000);
			clickBtn(Locators_Sanity.videocameraIcon);
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

	public void checkOperatorAndlaunchCalendar() throws InterruptedException, IOException, ParseException, org.json.simple.parser.ParseException
	{
		/*
		 * check operator and launch calendar
		 */
		String XP5deviceModelNumber=JsonFileReaderAndWriter.primaryDevOperatorReader();
		try {
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_HOME);
			minWait();
			if(XP5deviceModelNumber.contains("5SA.0.1-04-7.1.2-26.")) {
				clickBtn(Locators_Sanity.applications);
				minWait();
				clickBtn(Locators_Sanity.calendar);
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
			if(isElementExist(Locators_Sanity.agendaOpt)) {
				clickBtn(Locators_Sanity.agendaOpt);
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

	public void deleteEvents() throws InterruptedException, IOException
	{
		/*
		 * delete events
		 */
		try {
			clickMenuButton();
			if(isElementExist(Locators_Sanity.monthOpt)){
				clickBtn(Locators_Sanity.monthOpt);
				minWait();
				clickMenuButton();
			} else {
				clickBackButton(1);
				minWait();
				clickMenuButton();
			}
			if (Locators_Sanity.deleteEventsEnabled.isEnabled()) {
				clickBtn(Locators_Sanity.deleteEventsOpt);
				minWait();
				clickMenuAndElement(Locators_Sanity.selectAllOpt);
				minWait();
				clickMenuAndElement(Locators_Sanity.doneOpt);
				minWait();
				clickBtn(Locators_Sanity.OkBtn);
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
			clickMenuAndElement(Locators_Sanity.newEvent);
			minWait();
			enterTextToInputField(Locators_Sanity.eventName, eventName);
			minWait();
			enterTextToInputField(Locators_Sanity.locationFld, location);
			minWait();
			clickMenuAndElement(Locators_Sanity.saveOpt);
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
			String event = Locators_Sanity.createdEvent.getText();
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

	public void launchClock() throws InterruptedException, IOException
	{
		/*
		 * Launch clock application
		 */
		try {
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_HOME);
			minWait();
			clickBtn(Locators_Sanity.tools);
			minWait();
			clickBtn(Locators_Sanity.clock);
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
			clickMenuAndElement(Locators_Sanity.addOpt_oreo);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_RIGHT);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_RIGHT);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
			minWait();
			clickBtn(Locators_Sanity.OkBtn);
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
			if(isElementExist(Locators_Sanity.alarmSetPage)){
				check = true;
				clickBackButton(1);
				minWait();
				if(isElementExist(Locators_Sanity.alarmTodayOpt) && isElementExist(Locators_Sanity.alarmSwitchOn))
				{
					clickBtn(Locators_Sanity.alarmSwitchOn);
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
			waituntilnew(Locators_Sanity.alarmPage, 60000);
			minWait();
			if(isElementExist(Locators_Sanity.alarmTimeFld)) {
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_LEFT);
			}
			validateSnoozeAndDismiss(Locators_Sanity.alarmSwitchOn,Locators_Sanity.alarmTodayOpt,"Snoozed");
			minWait();
			clickBtn(Locators_Sanity.alarmSwitchOn);
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
			waituntilnew(Locators_Sanity.alarmPage, 60000);
			minWait();
			if(isElementExist(Locators_Sanity.alarmTimeFld)) {
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_RIGHT);
			}
			customWait(2000);
			validateSnoozeAndDismiss(Locators_Sanity.alarmSwitchOff,Locators_Sanity.alarmTomorrowOpt,"Dismissed");
			minWait();
		}catch (NoSuchElementException e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "Element Not Found");
		}
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

	public void navigateTo_NetworkSettings() throws InterruptedException, IOException {
		/*
		 * This Method will Navigate to the APN's  
		 */
		try {
			launch_an_app("settings");
			minWait();

			navigateToNetworkAndInternetOptions();
			clickBtn(Locators_Sanity.MobileNetwrks_oreo);
			minWait();
			System.out.println("check1");
//			scrollToElementWithDpadUp(Locators_Sanity.PreferredNwType_Operators);
			scrollText("Preferred network type");
			clickBtn(Locators_Sanity.PreferredNwType_Operators);
			minWait();
			System.out.println("check2");
		} catch (org.openqa.selenium.NoSuchElementException e) {
			APP_LOGS.info("No Element found.");
			test.log(LogStatus.ERROR, "No Such Element Found");
			Assert.fail();
		}
	}

	public void changePreferedNetworkType_Mainline() throws InterruptedException, IOException {
		/*
		 * change network type
		 */
		minWait();	
		changeNetwork(Locators_Sanity.GSM_NetwrkTyp);
		clickBackButton(2);
		navigateToSystemSettings(Locators_Sanity.systemInsettingsList);
		navigateToSettingsFeature(Locators_Sanity.AboutPhone,"About phone");
		validateLTESelection("EDGE");
		clickBackButton(5);


		navigateTo_NetworkSettings();
		minWait();	
		changeNetwork(Locators_Sanity.CDMA_Combo_NetwrkTyp);
		clickBackButton(2);	
		navigateToSystemSettings(Locators_Sanity.systemInsettingsList);
		navigateToSettingsFeature(Locators_Sanity.AboutPhone,"About phone");	
		validateLTESelection("HSPA");
		clickBackButton(5);

		navigateTo_NetworkSettings();
		minWait();	
		changeNetwork(Locators_Sanity.LTE_NetwrkTyp);
		clickBackButton(2);
		navigateToSystemSettings(Locators_Sanity.systemInsettingsList);
		navigateToSettingsFeature(Locators_Sanity.AboutPhone,"About phone");	
		validateLTESelection("4G");
		clickBackButton(5);

		navigateTo_NetworkSettings();
		minWait();	
		changeNetwork(Locators_Sanity.Global_NetwrkTyp);
		clickBackButton(2);
		navigateToSystemSettings(Locators_Sanity.systemInsettingsList);
		navigateToSettingsFeature(Locators_Sanity.AboutPhone,"About phone");	
		validateLTESelection("4G");
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
				scrollToElementWithDpadDown(Locators_Sanity.GSM_WCDMA_Network_type);
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
	
	public void changeNetwork_call(WebElement element) throws InterruptedException {
		/*
		 * change network
		 */
		SoftAssert SA=new SoftAssert();
		try {
			if(isElementExist(element)) {
				clickBtn(element);
			}else if(isElementExist(Locators_Sanity.CDMA_NetwrkTyp)) {
				clickBtn(Locators_Sanity.CDMA_NetwrkTyp);
			}
			else {
				scrollToElementWithDpadDown(Locators_Sanity.GSM_WCDMA_Network_type);
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

	public void changePreferedNetworkType_Sprint() throws InterruptedException, IOException {
		/*
		 * change network type
		 */
		if(isElementExist(Locators_Sanity.LTE_Recommended_Optn)) {
		clickBackButton(2);
		navigateToSystemSettings(Locators_Sanity.systemInsettingsList);
		navigateToSettingsFeature(Locators_Sanity.AboutPhone,"About phone");
		validateLTESelection("LTE");
		clickBackButton(5);
		}
		
		minWait();	
		changeNetwork(Locators_Sanity.NetwrkType_2G);
		clickBackButton(2);
		navigateToSystemSettings(Locators_Sanity.systemInsettingsList);
		navigateToSettingsFeature(Locators_Sanity.AboutPhone,"About phone");
		validateLTESelection("EDGE");
		clickBackButton(5);

		
		navigateTo_NetworkSettings();
		minWait();	
		changeNetwork(Locators_Sanity.NetwrkType_3G);
		clickBackButton(2);	
		navigateToSystemSettings(Locators_Sanity.systemInsettingsList);
		navigateToSettingsFeature(Locators_Sanity.AboutPhone,"About phone");	
		validateLTESelection("HSPA");
		clickBackButton(5);
	}
	

	
	



}
