package com.aosp.Utils;

import static org.testng.Assert.assertTrue;


import java.awt.AWTException;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;


import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.asserts.SoftAssert;

import com.graphics.gui.JsonFileReaderAndWriter;
import com.relevantcodes.extentreports.LogStatus;


import com.xp5S.util.BaseUtil;
import com.xp5S.util.Locators_BaseUtil;

import application.AllQA;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.AndroidKeyCode;

public class XP5S_Dev_Sanity_Util extends BaseUtil{

	//	public String b_No="";
	public String p_Id="";
	public String r_Id="";
	public String r_b_No="";
	public String pryNum="";
	public String refNum="";


	public void fetch_Devices_Details() throws InterruptedException, FileNotFoundException, IOException, ParseException {

		minWait();
		p_Id=JsonFileReaderAndWriter.primaryDevIdReader();
		r_Id=JsonFileReaderAndWriter.ReadRefDeviceId();
		//		p_b_No=JsonFileReaderAndWriter.primaryDevFirmwareReader();
		r_b_No=JsonFileReaderAndWriter.ReadRefDeviceFirmWare();
		pryNum= AllQA.PRIMARYDEVMDN;
		refNum=AllQA.REFERENCEDEVMDN;
	}


	public boolean check = false;
	public boolean check1 = false;
	public boolean check2 = false;
	public boolean check3 = false;
	public boolean check4 = false;
	public boolean check5 = false;

	public void validateAppMenuLaunch() throws InterruptedException {
		/*
		 * validate App Launch Menu from Launcher
		 */
		minWait();
		aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
		minWait();
		aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
		minWait();
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

	public void performAction() throws IOException, InterruptedException, ParseException {
		/*
		 * 
		 * perform action airplane mode on and off
		 */
		String Uid=JsonFileReaderAndWriter.primaryDevIdReader();

		Runtime.getRuntime().exec("adb -s "+Uid+" shell am start -a android.settings.AIRPLANE_MODE_SETTINGS");
		minWait();
		enableSwitch(Locators_DevSanity.enabled_Airplane,Locators_DevSanity.disabled_Airplane,Locators_DevSanity.switch_widget);
		customWait(5000);
		disableSwitch(Locators_DevSanity.disabled_Airplane,Locators_DevSanity.enabled_Airplane,Locators_DevSanity.switch_widget );		
		customWait(9000);

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

	public void ConnectSecuredWifi() throws InterruptedException, IOException {
		/*
		 * Connect wifi with entering passkey
		 */
		try {
			minWait();
			aDriver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().resourceId(\"com.android.settings:id/main_content\")).scrollIntoView(new UiSelector().text(\"Wi-Fi\"))");
			minWait();

			clickBtn(Locators_DevSanity.wifi);
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
			String getSummary = Locators_DevSanity.summaryWIFI.getText();
			System.out.println(getSummary);
			minWait();
			if(getSummary.contains("Disconnected")) {
				minWait();
				clickBtn(Locators_DevSanity.wifi);
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_MENU);
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_ENTER);
				//refresh btn
				minWait();
				minWait();
				customWait(4000);
				selectIDcwifi();      
				minWait();
				if(isElementExist(Locators_DevSanity.wifi_IDC_ForgetBtn)) {
					clickBtn(Locators_DevSanity.wifi_IDC_ForgetBtn);
					minWait();
					selectIDcwifi();    
				}
				clickBtn(Locators_DevSanity.wifi_show_Pswd);
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_UP);
				customWait(1000);

				changeToNumberMode_SMS(Locators_DevSanity.wifi_IDC_Psswd);
				customWait(2000);
				minWait();
				if(isElementExist(Locators_DevSanity.wifi_IDC_ForgetBtn)) {
					clickBtn(Locators_DevSanity.wifi_IDC_ForgetBtn);
					minWait();
					selectIDcwifi();    
				}
				clickBtn(Locators_DevSanity.wifi_show_Pswd);
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_UP);
				customWait(1000);

				changeToNumberMode_SMS(Locators_DevSanity.wifi_IDC_Psswd);
				customWait(9000);
				if(isElementExist(Locators_DevSanity.wifi_IDC)) {
					Runtime.getRuntime().exec("adb -s "+p_Id+" shell input text 1dcS0n1md0tc0MbLr");
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
				//				    System.out.println(psswrd);
				clickBtn(Locators_DevSanity.wifi_IDC_ConnectBtn);
				APP_LOGS.info("IDC available secured wifi is connected");
				customWait(4000);		    	
			}		    

		} 
		catch (Exception e) {

			e.printStackTrace();
			test.log(LogStatus.ERROR, "NO Such Element Found");
			Assert.fail();
		}
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
			else {
				//				test.log(LogStatus., "Toggle button is disabled");
			}

		} catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "No Such Element Found");
			Assert.fail();
		}
	}

	public void validateScanBluetooth() throws InterruptedException {
		/*
		 * enable disable BT
		 */
		SoftAssert S1 = new SoftAssert();
		minWait();
		aDriver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().resourceId(\"com.android.settings:id/main_content\")).scrollIntoView(new UiSelector().text(\"Bluetooth\"))");
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
		if(isElementExist(Locators_DevSanity.BT_Devices)) {
			check1= true; 
			APP_LOGS.info( "BT ON "+check1);
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
			APP_LOGS.info("Enable and Disable Bluetooth feature is verified");
			test.log(LogStatus.INFO, "Enable and Disable Bluetooth  feature is verified");
			S1.assertTrue(check, " ");
		}
		else {
			APP_LOGS.info(" check1"+check1+"check2"+check2);
			test.log(LogStatus.ERROR, "Enable and Disable Bluetooth faeture is not verified");
			APP_LOGS.info("Enable and Disable Bluetooth  feature is not verified");
			S1.fail();
		}		
	}

	public void selectIDcwifi() throws InterruptedException {
		/*
		 * Select IDC wifi which is available
		 */
		try {
			customWait(4000);
			for(int i=1; i<=50; i++) {
				if(isElementExist(Locators_DevSanity.wifi_IDC)) {
					minWait();
					clickBtn(Locators_DevSanity.wifi_IDC);
					APP_LOGS.info("IDC available secured wifi is Selected");
					break;
				}

				if(isElementExist(Locators_DevSanity.wifi_Cannada)) {
					minWait();
					clickBtn(Locators_DevSanity.wifi_Cannada);
					APP_LOGS.info("Cannada available secured wifi is Selected");
					break;
				}

				if(isElementExist(Locators_DevSanity.wifi_Dellas)) {
					minWait();
					clickBtn(Locators_DevSanity.wifi_Dellas);
					APP_LOGS.info("Cannada available secured wifi is Selected");
					break;
				}
				else {
					minWait();
					aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
					continue;
				}
			}
		} catch (Exception e) {
			APP_LOGS.info("No Element found.");
			minWait();	
			Assert.fail();
		}
	}

	public void validateDisableEnableWiFi() throws InterruptedException {
		/*
		 * disable enable wiFi
		 */
		SoftAssert sf = new SoftAssert();
		try {
			launch_an_app("settings");
			minWait();
			clickBtn(Locators_DevSanity.wifi);
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
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);	
			minWait();	
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);	

			minWait();
			launch_an_app("settings");
			minWait();
			clickBtn(Locators_DevSanity.wifi);
			minWait();

			enableFeature(Locators_DevSanity.enabled,Locators_DevSanity.disabled,Locators_DevSanity.switch_Title);
			minWait();
			customWait(4000);
			launch_an_app("browser");
			customWait(4000);
			enterUrl("www.google.com");

			waituntilnew(Locators_DevSanity.SearchIcon_Google, 60);
			if((isElementExist(Locators_DevSanity.SearchIcon_Google))||(isElementExist(Locators_DevSanity.Searchenter_Google))) 
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
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			minWait();	
			minWait();	
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			minWait();	
		} catch (Exception e) {
			APP_LOGS.info("No Element found.");
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
			minWait();
			aDriver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().resourceId(\"com.android.settings:id/main_content\")).scrollIntoView(new UiSelector().text(\"Bluetooth\"))");
			minWait();

			clickBtn(Locators_DevSanity.Bluetooth);
			minWait();
			enable(Locators_DevSanity.enabled,Locators_DevSanity.disabled,Locators_DevSanity.switch_Title);
			customWait(2000);
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
			minWait();
			if(isElementExist(Locators_DevSanity.enabled)) {
				check1 = true;
				APP_LOGS.info(" check1");
			}
			customWait(2000);
			disableFeature(Locators_DevSanity.enabled,Locators_DevSanity.disabled);
			if(isElementExist(Locators_DevSanity.disabled)) {
				check2 = true;
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
		} catch (Exception e) {

			e.printStackTrace();
			S1.fail();
		}
		S1.assertAll();
	}


	// Validates the presence of USB connection.
	public void validate_phone_Charging() throws InterruptedException{
		try {
			minWait();
			clickBtn(Locators_DevSanity.AboutPhone);
			minWait();
			clickBtn(Locators_DevSanity.status);
			customWait(1000);
			if(isElementExist(Locators_DevSanity.Charging_OverUSB)||isElementExist(Locators_DevSanity.Full_OverUSB)){
				APP_LOGS.info("Charging over USB in Battery Status option is displayed");
				test.log(LogStatus.INFO, "Charging over USB in Battery Status option is displayed");
				check=true;
				Assert.assertTrue(check);
			}else{
				APP_LOGS.info("Charging over USB in Battery Status option NOT is displayed");
				test.log(LogStatus.ERROR, "Charging over USB in Battery Status option NOT is displayed");
				Assert.fail();
			}	
			back();
			minWait();
			back();

		} catch (Exception e) {
			e.printStackTrace();
			APP_LOGS.info("No Element found.");
			Assert.fail();
		}
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

	public void navigateToSDCard() throws InterruptedException {
		/*
		 * this method will navigate to any folder 
		 */
		try {
			minWait();
			clickBtn(Locators_DevSanity.SDcard);
			APP_LOGS.info("Selected SD card");
			minWait();
		} catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "Precondition : SD Card is not inserted");
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
		} catch (Exception e) {

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

		} catch (Exception e) {

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
		} catch (Exception e) {

			e.printStackTrace();
			test.log(LogStatus.ERROR, "SD card Size element is not displayed");
			Sa.fail();
		}
		Sa.assertAll();
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
			//Runtime.getRuntime().exec("adb shell am start -n com.android.mms/com.android.mms.ui.ComposeMessageActivity");
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
			minWait();
			enterTextToInputField(Locators_DevSanity.typeMsg_Field, composedMsg);

		} catch (Exception e) {
			APP_LOGS.info("No Element found.");
			e.printStackTrace();
			test.log(LogStatus.ERROR, "No Such Element Found");
			Sa.fail();
		}
		Sa.assertAll();
	}

	public void send() throws InterruptedException {
		/*
		 * send sms 
		 */		
		customWait(2000);
		aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_MENU);
		minWait();
		aDriver.pressKeyCode(AndroidKeyCode.ENTER);		
		customWait(1000);
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
				APP_LOGS.info("Message "+str+" Successfully");
				sf.assertTrue(check, "Validation is Pass");
				test.log(LogStatus.PASS,  str+": Test case status is Passed");
			} else {
				APP_LOGS.info("Message DIDN'T "+ str);
				sf.fail();
				test.log(LogStatus.ERROR, "didn't "+str +", Validation Failed ");
			}

		} catch (Exception e) {
			APP_LOGS.info("No Element found.");
			e.printStackTrace();
			test.log(LogStatus.ERROR, " No Such Element Found");
			sf.fail();
		}
		sf.assertAll();
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

	public void type_New_Message(String cell_No, String typeMessage) throws InterruptedException, IOException {
		/*
		 * This Method is to type New Meassage.
		 */
		enter_ToField(cell_No);
		minWait();
		type_Message(typeMessage);
		minWait();
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

	public void press_BackKey() throws InterruptedException {
		minWait();
		aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
		minWait();
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

		} catch (Exception e) {
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
	}

	public void changeToNumberMode() throws InterruptedException {

		try {
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
		} catch (Exception e) {

			e.printStackTrace();
			test.log(LogStatus.ERROR, "To Enter Field is not found");
			Assert.fail();
		}
	}



	public static void MO_CALL_Sanity() throws IOException, InterruptedException, ParseException {

		APP_LOGS.info("S@shi:This Method is used to Originate MO call");
		SoftAssert sa=new SoftAssert();


		String Refdevid=JsonFileReaderAndWriter.ReadRefDeviceId();
		String mobnumber=AllQA.REFERENCEDEVMDN;
		JsonFileReaderAndWriter.RefMobileNumber(mobnumber);
		String Uid=JsonFileReaderAndWriter.primaryDevIdReader();

		for(int i=1;i<=1;i++)
		{
			Runtime.getRuntime().exec("adb -s "+Uid+" shell am start -a android.intent.action.CALL -d tel:"+AllQA.REFERENCEDEVMDN);
			Thread.sleep(10000);

			try {

				for(int j=1;j<=1;j++){
					String value=null;
					Process child=Runtime.getRuntime().exec("adb -s "+Refdevid+" shell service call telecom 28");
					InputStream lsOut = child.getInputStream();
					InputStreamReader r = new InputStreamReader(lsOut);
					BufferedReader in = new BufferedReader(r);
					value=in.readLine();
					System.out.println(value);
					System.out.println(Refdevid);
					if(value.contains("00000001")) {

						System.out.println("Phone is rining");
						Runtime.getRuntime().exec("adb -s "+Refdevid+" shell input keyevent 5");

						test.log(LogStatus.INFO, "MO Call initiated ");	
						sa.assertTrue(true, "PASS");
						break;

					}else {

						test.log(LogStatus.INFO, "Reference phone is not ringing");
						System.out.println("Reference phone is not rining");

						sa.fail();
					}


				}
			}catch (Exception e) {

				System.out.println("Execption in call recevie ");

				Runtime.getRuntime().exec("adb -s "+Refdevid+" shell input keyevent 5");
				Thread.sleep(2000);

			}
		}
		sa.assertAll();
	}

	public void disconnectVoiceCall() throws InterruptedException {
		//Disconnect call

		System.out.println("Disconceting ");
		Thread.sleep(5000);
		aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_ENDCALL);
		Thread.sleep(3000); 
	}



	public void reject_XP5Call() throws FileNotFoundException, IOException, ParseException, InterruptedException {
		SoftAssert sf = new SoftAssert();
		String Refdevid=JsonFileReaderAndWriter.ReadRefDeviceId();
		String mobnumber=AllQA.REFERENCEDEVMDN;
		JsonFileReaderAndWriter.RefMobileNumber(mobnumber);
		String Uid=JsonFileReaderAndWriter.primaryDevIdReader();


		SoftAssert sa = new SoftAssert();
		for(int i=1;i<=1;i++)
		{

			Runtime.getRuntime().exec("adb -s "+Refdevid+" shell am start -a android.intent.action.CALL -d tel:"+AllQA.PRIMARYDEVMDN);
			Thread.sleep(10000);

			try {

				for(int j=1;j<=100;j++){
					String value=null;
					Process child=Runtime.getRuntime().exec("adb -s "+Uid+" shell service call telecom 28");
					InputStream lsOut = child.getInputStream();
					InputStreamReader r = new InputStreamReader(lsOut);
					BufferedReader in = new BufferedReader(r);
					value=in.readLine();
					if(value.contains("00000001")) {

						System.out.println("Phone is rining");

						test.log(LogStatus.INFO, "MO CALL rejected  #"+i  +"     :     "+"  "+AllQA.REFERENCEDEVMDN +"     ===>>>     "+AllQA.PRIMARYDEVMDN);

						Runtime.getRuntime().exec("adb -s "+Uid+" shell input keyevent 6");

					}else {
						test.log(LogStatus.INFO, "MO CALL is not rejected #"+i +"     :     Primary Phone is not Ringing because of network issue/Call has be landed . ");
						System.out.println("Phone is not rining so TC fail");
						sa.fail();
					}

					break;	
				}


			}catch (Exception e) {

				System.out.println("saldsldjsad");
				Runtime.getRuntime().exec("adb -s "+Refdevid+" shell input keyevent 5");
				Thread.sleep(2000);
				continue;			
			}

			Thread.sleep(5000);
			System.out.println("Now dosconnecting the call in ref device");
			Runtime.getRuntime().exec("adb -s "+Refdevid+" shell input keyevent 6");

		}
		sa.assertAll();
	}


	public void MT_XP5Call() throws FileNotFoundException, IOException, ParseException, InterruptedException {

		String Refdevid=JsonFileReaderAndWriter.ReadRefDeviceId();
		String mobnumber=AllQA.REFERENCEDEVMDN;
		JsonFileReaderAndWriter.RefMobileNumber(mobnumber);
		String Uid=JsonFileReaderAndWriter.primaryDevIdReader();


		SoftAssert sa = new SoftAssert();
		for(int i=1;i<=1;i++)
		{

			Runtime.getRuntime().exec("adb -s "+Refdevid+" shell am start -a android.intent.action.CALL -d tel:"+AllQA.PRIMARYDEVMDN);
			Thread.sleep(10000);

			try {

				for(int j=1;j<=100;j++){
					String value=null;
					Process child=Runtime.getRuntime().exec("adb -s "+Uid+" shell service call telecom 28");
					InputStream lsOut = child.getInputStream();
					InputStreamReader r = new InputStreamReader(lsOut);
					BufferedReader in = new BufferedReader(r);
					value=in.readLine();
					if(value.contains("00000001")) {

						System.out.println("Phone is rining");


						Runtime.getRuntime().exec("adb -s "+Uid+" shell input keyevent 5");
						test.log(LogStatus.INFO, "MT CALL #"+i  +"     :     "+"  "+AllQA.REFERENCEDEVMDN +"     ===>>>     "+AllQA.PRIMARYDEVMDN);
						break;	


					}else {



						test.log(LogStatus.INFO, "MO CALL #"+i +"     :     Primary Phone is not Ringing because of network issue/Call has be landed . ");
						System.out.println("Phone is not rining so TC fail");
						sa.fail();
					}



				}


			}catch (Exception e) {

				System.out.println("saldsldjsad");
				Runtime.getRuntime().exec("adb -s "+Refdevid+" shell input keyevent 5");
				Thread.sleep(2000);
				continue;			
			}


		}
		sa.assertAll();



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
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_STAR);
			minWait();
		}   
	}

	public void navigateToOptions(WebElement optntype, String str) throws InterruptedException, IOException {
		/*
		 * Select Screen lock or Simcard Lock
		 */		
		try {
			ScrollToElement(Locators_DevSanity.DataUsageList, str);
			minWait();
			String getOptionType = optntype.getText();
			minWait();
			clickBtn(optntype);
			APP_LOGS.info("Selected option is " + getOptionType);
			customWait(1000);			
		} catch (Exception e) {
			e.printStackTrace();
			test.setDescription("Failure Reason: Element not found");
			Assert.fail();
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
		} catch (Exception e) {

			e.printStackTrace();
			test.log(LogStatus.ERROR, "Element not found");
			Sa.fail();
		}		
		Sa.assertAll();
	}

	public void enterUrl(String newurl) throws InterruptedException {
		/*
		 * enter url when data is enabled
		 */
		try {
			minWait();
			clickBtn(Locators_DevSanity.DefaultUrlTxt);
			enterTextToInputField(Locators_DevSanity.DefaultUrlTxt,newurl);
			APP_LOGS.info(" URl is entered is sucessful.");
			minWait();
			minWait();	 
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_ENTER);
			minWait();
			APP_LOGS.info(" Search click is sucessful."); 
		} catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "No Such Element Found");
			Assert.fail();
		}
	}


	public void validateBrowseInternet() throws InterruptedException {
		/*
		 * Validate while browsing google.com
		 */
		SoftAssert sf= new SoftAssert();
		try {
			customWait(2000);
			enterUrl("www.google.com");
			customWait(2000);
			if((isElementExist(Locators_DevSanity.SearchIcon_Google))||(isElementExist(Locators_DevSanity.Searchenter_Google))) {
				check =true;
				APP_LOGS.info("check: "+check);
				APP_LOGS.info("Browser is Launched sucessfully");
				test.log(LogStatus.INFO, "Browsing is Launched and Surf sucessfully");
				sf.assertTrue(check, "Validation is Pass");
			}
			else{
				APP_LOGS.info("check: "+check);
				APP_LOGS.info("Browse internet is not sucessful");
				test.log(LogStatus.INFO, "Browse is not launhed and surf sucessful");
				sf.fail();
			}minWait();	
		} catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "No Such Element Found");
			sf.fail();
		}
		sf.assertAll();
	}

	public void enableData() throws InterruptedException {
		/*
		 * enable data
		 */
		try {
			//this perform disable WiFi
			launch_an_app("settings");
			minWait();
			clickBtn(Locators_DevSanity.wifi);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			customWait(2000);
			disableFeature(Locators_DevSanity.enabled,Locators_DevSanity.disabled);
			minWait();

			minWait();
			if(isElementExist(Locators_DevSanity.MobileNetwrks)) {
				minWait();
				clickBtn(Locators_DevSanity.MobileNetwrks);
				APP_LOGS.info("Selected Mobile Networks");
				minWait();				
				enableSwitch(Locators_DevSanity.enabled_Data,Locators_DevSanity.disabled_Data,Locators_DevSanity.switch_widget);
				minWait();
			}
			else {
				clickBtn(Locators_DevSanity.dataUsageOptn);
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
				minWait();
				if (!isElementExist(Locators_DevSanity.cellular_DataON)) {
					minWait();
					clickBtn(Locators_DevSanity.cellular_DataOFF);
					APP_LOGS.info("Switch is Enabled");                     
					minWait();
				}
				minWait();
			}
		} catch (Exception e) {
			APP_LOGS.info("No Element found.");
			test.log(LogStatus.ERROR, "No Such Element Found");
			Assert.fail();
			minWait();	
		}
	}

	public void disableCellularData() throws InterruptedException {
		try {
			minWait();
			if(isElementExist(Locators_DevSanity.MobileNetwrks)) {
				minWait();
				clickBtn(Locators_DevSanity.MobileNetwrks);
				APP_LOGS.info("Selected Mobile Networks");
				minWait();
				disableFeature(Locators_DevSanity.enabled_Data,Locators_DevSanity.disabled_Data);
				minWait();
				clickBtn(Locators_DevSanity.OkBtn);
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
				clickBtn(Locators_DevSanity.OkBtn);
				minWait();
			}


		} catch (Exception e) {
			APP_LOGS.info("No Element found.");
			Assert.fail();
			minWait();	
		}

	}

	public void createContact(String name,String num) throws InterruptedException, IOException, ParseException {
		/*
		 * create contacts with name, phone, email and address
		 */
		try {
			String Uid=JsonFileReaderAndWriter.primaryDevIdReader();
			deleteContact();
			customWait(1000);
			if(isElementExist(Locators_DevSanity.Addcontacts)) {
				clickBtn(Locators_DevSanity.Addcontacts);
				minWait();	
			}else {
				minWait();	
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_MENU);
				minWait();
				clickBtn(Locators_DevSanity.contact_addContactsOptn);
			}

			enterTextToInputField(Locators_DevSanity.contactName, name);
			minWait();	
			enterTextToInputField(Locators_DevSanity.contact_Phone, num);
			for(int i=1; i<=5;i++) {
				minWait();	
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
			}
			minWait();	
			enterTextToInputField(Locators_DevSanity.contact_Email, "Sonim");
			minWait();
			Runtime.getRuntime().exec("adb -s "+Uid+" shell input text gmail.com");	
			customWait(1000);
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
			minWait();
			for(int i=1; i<=5;i++) {
				minWait();	
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
			}
			clickBtn(Locators_DevSanity.contact_MorefieldsOptn);
			minWait();	
			for(int i=1; i<=5;i++) {
				minWait();	
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
			}
			minWait();	
			enterTextToInputField(Locators_DevSanity.contact_Address, "IDC Sonim");
			minWait();	
			minWait();	
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_MENU);
			minWait();
			clickBtn(Locators_DevSanity.contact_Save);
			minWait();		
			minWait();	
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
		} catch (Exception e) {
			APP_LOGS.info("No Element found.");
			e.printStackTrace();
			test.log(LogStatus.ERROR, "No Such Element Found");
			Assert.fail();
		}
	}


	public void deleteContact() throws InterruptedException  {	
		/*
		 * delete the contact from phone memory
		 */
		try {
			minWait();	
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_HOME);	
			minWait();	
			minWait();
			clickBtn(Locators_DevSanity.contacts);
			customWait(500);
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_MENU);
			clickBtn(Locators_DevSanity.Contact_Select);
			customWait(500);

			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_MENU);
			clickBtn(Locators_DevSanity.Contact_SelectAll);
			customWait(500);

			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_MENU);
			clickBtn(Locators_DevSanity.contact_delete);
			customWait(500);
			minWait();	
			clickBtn(Locators_DevSanity.contact_DeleteBtn);
			minWait();	
			APP_LOGS.info("Deleted all contacts");
		} catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "No Such Element Found");
			Assert.fail();
		}	
	}

	public void validateContactCreate() throws InterruptedException 
	{
		/*
		 * validate contact creation present in phone memory
		 */
		SoftAssert Sb = new SoftAssert();
		try {
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.ENTER);
			minWait();
			String getcontactNameText=Locators_DevSanity.contact_Test.getText();
			System.out.println(getcontactNameText);
			if(getcontactNameText.equalsIgnoreCase("Test"))
			{	check1 =true;
			APP_LOGS.info("check1: "+check1);
			APP_LOGS.info("Contact Name is: "+getcontactNameText);
			}else{
				APP_LOGS.info("check1: "+check1);
				APP_LOGS.info("Test Name contact is not present");
			}

			minWait();
			clickBtn(Locators_DevSanity.contact_Test);
			minWait();	
			minWait();	
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
			minWait();
			String getPhoneNumText=Locators_DevSanity.contact_phnNum.getText();
			System.out.println(getPhoneNumText);
			minWait();
			check2 =true;
			/*if(getPhoneNumText.equalsIgnoreCase(AllQA.REFERENCEDEVMDN))
			{	check2 =true;
			APP_LOGS.info("check2: "+check2);
			APP_LOGS.info("Contact Phone Num is: "+getPhoneNumText);
			}else{
				APP_LOGS.info("check2: "+check2);
				APP_LOGS.info("Contact Phone Num is not present");
			}
			 */
			minWait();	
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
			minWait();
			String getEmailText =" ";
			getEmailText=Locators_DevSanity.contact_emailid.getText();
			System.out.println(getEmailText);
			if(getEmailText.contains("Sonim"))
			{	check3 =true;
			APP_LOGS.info("check3: "+check3);
			APP_LOGS.info("Contact email id is: "+getEmailText);
			}else{
				APP_LOGS.info("check3: "+check3);
				APP_LOGS.info("Contact email id is not present");
			}
			minWait();	
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
			minWait();
			String getAddressText=Locators_DevSanity.contact_addressText.getText();
			//		System.out.println(getAddressText);	
			if(getAddressText.equalsIgnoreCase("IDC Sonim"))
			{	check4 =true;
			APP_LOGS.info("check4: "+check4);
			APP_LOGS.info("Contact Address is: "+getAddressText);
			}else{
				APP_LOGS.info("check4"+check4);
				APP_LOGS.info("Contact Address  is not present");
			}

			if((check1)&&(check2)&&(check3)&&(check4)){
				check=true;
				APP_LOGS.info("Contact created with Name,phone_Num, Email_ID and Address is saved in phone memory");
				test.log(LogStatus.INFO, "Contact created with Name,phone_Num, Email_ID and Address is saved ");
				Sb.assertTrue(check, " ");
			}else{
				APP_LOGS.info("Test case failed");	
				test.log(LogStatus.ERROR, "Contact created with Name,phone_Num, Email_ID and Address is not saved ");
				Sb.fail();
			}
			minWait();	
		} catch (Exception e) {

			e.printStackTrace();
			test.log(LogStatus.ERROR, "No Such Element Found");
			Sb.fail();
		}
		Sb.assertAll();
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
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
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

	public void takeCameraVideo(int n) throws InterruptedException, IOException {
		/*
		 * Capture photo from camera and store in SDcard
		 */
		SoftAssert S1 = new SoftAssert();
		try {
			customWait(1000);
			aDriver.pressKeyCode(AndroidKeyCode.MENU);
			minWait();
			clickBtn(Locators_DevSanity.SwitchToCameraOptn);
			customWait(2000);
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_UP);
			minWait();
			clickBtn(Locators_DevSanity.videocameraIcon);
			for(int i=1; i<=n;i++) {
				customWait(2000);
			}
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
			customWait(1000);
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			minWait();
		} catch (Exception e) {
			e.printStackTrace();
			S1.fail();
		}
		S1.assertAll();
	}


	public void navigateToCameraFolder() throws InterruptedException {

		//navigate To CAmera Folder

		customWait(1000);
		for(int i=1; i<=3;i++) {
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_LEFT);
			minWait();
		}
		minWait();
		aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_ENTER);
		minWait();
		//		aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
		minWait();
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
		} catch (Exception e) {

			e.printStackTrace();
			Assert.fail();
		}
	}

	public void clearAllAlbums() throws InterruptedException {
		/*
		 * clearing all albums if present
		 */		
		try {
			aDriver.pressKeyCode(AndroidKeyCode.MENU);
			minWait();
			if(isElementExist(Locators_DevSanity.SelectAlbumOptn)) {
				clickBtn(Locators_DevSanity.SelectAlbumOptn);
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_MENU);
				minWait();
				clickBtn(Locators_DevSanity.SelectallOptn);
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_MENU);
				minWait();
				clickBtn(Locators_DevSanity.Delete);
				minWait();
				clickBtn(Locators_DevSanity.OKBtn);	
				customWait(2000);
			}
		} catch (Exception e) {

			e.printStackTrace();
			test.log(LogStatus.ERROR, " No Such Element Found");
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
		} catch (Exception e) {

			e.printStackTrace();
			Assert.fail();
		}
	}

	//Selecting Allow for pop ups
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
		} catch (Exception e) {

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
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
	}


	public void validatereplaceChannel() throws InterruptedException {
		//Validate replace channel by using forward button to change channel
		SoftAssert SA1=new SoftAssert();
		try {
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_MENU);
			minWait();
			clickBtn(Locators_DevSanity.AutoScan);
			customWait(1000);
			if(isElementExist(Locators_DevSanity.YesOpt)) {
				clickBtn(Locators_DevSanity.YesOpt);
			}

			WebDriverWait wait = new WebDriverWait(getDriver(), 12000);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//android.widget.TextView[@text='Scanning for All Stations']")));


			if(isElementExist(Locators_DevSanity.AllChannelsList)){
				minWait();
				check =true;			
				APP_LOGS.info("Channel Scan");	

				SA1.assertTrue(check, "Saved Channel is Replaced with other Channel");

			}
			else {

				APP_LOGS.info("Saved Channel is Replaced with other Channel");
				test.log(LogStatus.INFO,"Saved Channel is Replaced with other Channel");
				SA1.fail();			

			}				
		}
		catch(Exception e) {
			APP_LOGS.info("Element not Found");
			e.printStackTrace();
			SA1.fail();
		}
		SA1.assertAll();
	}

	public void changeScreenLock(String change,WebElement select) throws InterruptedException {
		/*
		 * 	 * Change screen Lock from different types
		 */	
		try {
			String getScreenLockType = Locators_DevSanity.typelock.getText();
			System.out.println(getScreenLockType);
			if(getScreenLockType.equals(change)) {
				minWait();
				APP_LOGS.info("Changed Screen Lock to " + getScreenLockType);
			}
			else {
				clickBtn(Locators_DevSanity.ScreenLock);
				minWait();
				String changedLock = select.getText();
				clickBtn(select);
				minWait();
				APP_LOGS.info("Changed Screen Lock to " + changedLock);
			}
		} catch (Exception e) {
			e.printStackTrace();
			test.setDescription("Failure Reason: Element not found");
			Assert.fail();
		}
	}

	public void enterPIN(WebElement option,String pin) throws InterruptedException {
		/*
		 * enter the PIN number to set Screen lock of device
		 */		
		try {
			enterTextToInputField(Locators_DevSanity.enter_PIN, pin);
			minWait();
			clickBtn(option);
		} catch (Exception e) {
			e.printStackTrace();
			test.setDescription("Failure Reason: Element not found");
			Assert.fail();
		}
	}

	public void screenLockDevice() throws IOException, InterruptedException, ParseException {
		/*
		 * Screen lock the device 
		 */
		String Uid=JsonFileReaderAndWriter.primaryDevIdReader();
		aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_HOME);
		minWait();
		minWait();
		aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
		minWait();	
		customWait(1000);
		Runtime.getRuntime().exec("adb -s "+Uid+" shell input keyevent --longpress KEYCODE_STAR");
		APP_LOGS.info("Device is Screen locked");
		customWait(3000);

	}


	public void validatePINScreenLock(String pin) throws InterruptedException {
		/*
		 * Validate PIN Screen lock 
		 */
		SoftAssert S3 = new SoftAssert();
		try {
			if(validate_presenceOfElement(Locators_DevSanity.enter_PINLock,"")) {
				check = true;
				enterTextToInputField(Locators_DevSanity.enter_PINLock, pin);
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
				test.log(LogStatus.INFO, "Set and Validated Screen Lock PIN");
				assertTrue(check, " ");
			}
			else {
				test.log(LogStatus.ERROR, "Not Validated Screen Lock PIN");
			}

		} catch (Exception e) {
			e.printStackTrace();
			test.setDescription("Failure Reason: Element not found");
			S3.fail();
		}
		S3.assertAll();
	}

	public void checkAllLock_Features() throws InterruptedException, IOException, ParseException {
		/*
		 * Set All lock features PIN, Star and None and Validate
		 */
		String Uid=JsonFileReaderAndWriter.primaryDevIdReader();
		/*  changeScreenLock("PIN", Locators_DevSanity.Screenlck_PIN);	
		        enterPIN(Locators_DevSanity.PIN_continue,"1234");
		        enterPIN(Locators_DevSanity.OK_Btn,"1234");
		    	screenLockDevice();   
		    	validatePINScreenLock("1234");    	
		    	// Set and Validated PIN Lock

		    	launch_an_app("settings");
				navigateToSettingsFeature(Locators_DevSanity.Security_feature,"Security");*/
		//			minWait();
		/*	Runtime.getRuntime().exec("adb shell input text 1234");
				customWait(1000);
				Runtime.getRuntime().exec("adb shell input keyevent 66");
				minWait();*/
		changeScreenLock("Press and hold ￼ key", Locators_DevSanity.Screenlck_PressHoldStar);	
		screenLockDevice();
		validatePressStarScreenLock();
		Runtime.getRuntime().exec("adb -s "+Uid+" shell input keyevent --longpress KEYCODE_STAR");

		launch_an_app("settings");
		navigateToSettingsFeature(Locators_DevSanity.Security_feature,"Security");
		seleckScreenLock("Press and hold ? key",Locators_DevSanity.Screenlck_PressHoldStar);
		changeScreenLock("None", Locators_DevSanity.Screenlck_None);		
		validate_Displayed_text(Locators_DevSanity.typelock, "None");
		back();
	}

	public void seleckScreenLock(String present, WebElement select) throws InterruptedException, IOException {
		/*
		 * selecting different screen locks 
		 */

		try {
			ScrollToElement(Locators_DevSanity.DataUsageList, "Screen lock");
			minWait();
			String getScreenLockType = Locators_DevSanity.typelock.getText();
			System.out.println(getScreenLockType);
			if(getScreenLockType.equals(present)) {
				minWait();
				APP_LOGS.info("Present Screen Lock is " + getScreenLockType);
			}
			else {
				clickBtn(Locators_DevSanity.ScreenLock);
				minWait();
				String selectedLock = select.getText();
				clickBtn(select);
				minWait();
				APP_LOGS.info("Present Screen Lock is " + selectedLock );
			}
		} catch (Exception e) {
			e.printStackTrace();
			test.setDescription("Failure Reason: Element not found");
			Assert.fail();
		}
	}


	public void validatePressStarScreenLock() throws InterruptedException {
		/*
		 * After Setting Screen lock as Press and Hold Star
		 */
		SoftAssert Ss = new SoftAssert();
		try {
			if(isElementExist(Locators_DevSanity.Screenlck_Star_Device)) {
				APP_LOGS.info("Device is set to Screen Lock" );
				check = true;
			}
			else if(isElementExist(Locators_DevSanity.Screenlck_Star_Device_emergencyTXt)) {
				APP_LOGS.info("Device is set to Screen Lock" );
				check = true;
			}
			else if(validate_presenceOfElement(Locators_DevSanity.Screenlck_Star_Device,"")) {
				APP_LOGS.info("Device is set to Screen Lock" );
				check = true;						
			}
			if(check) {
				APP_LOGS.info("Device is set to Screen Lock and verified" );
				Ss.assertTrue(check, " ");
			}
			else {
				APP_LOGS.info("Device is not set to Screen Lock and unverified" );
				Ss.fail();
			}
		} catch (Exception e) {
			e.printStackTrace();
			test.setDescription("Failure Reason: Element not found");
			Ss.fail();
		}
		Ss.assertAll();
	}

	public void selectMoreOptions() throws InterruptedException, IOException, ParseException {
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
				 if (isElementExist(Locators_DevSanity.moreOptn)) {
					 APP_LOGS.info("More Option is displayed sucessfully");
					 minWait();	
					 clickBtn(Locators_DevSanity.moreOptn);
					 break;					
				 } else {
					 aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
					 continue;					
				 }
			 }
		 } catch (Exception e) {
			 APP_LOGS.info("More Option is not displayed.");
			 test.log(LogStatus.ERROR, "No Such Element Found");
			 minWait();	
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

	public void initiateCall() throws IOException, ParseException {
		/*
		 * Intiate MO call
		 */
		String Uid=JsonFileReaderAndWriter.primaryDevIdReader();
		Runtime.getRuntime().exec("adb -s "+Uid+" shell am start -a android.intent.action.CALL -d tel:"+AllQA.REFERENCEDEVMDN+"");
	}


	public void initiateCallRef() throws IOException, ParseException {
		/*
		 * Intiate MO call
		 */
		String Uid=JsonFileReaderAndWriter.primaryDevIdReader();
		Runtime.getRuntime().exec("adb -s "+r_Id+" shell am start -a android.intent.action.CALL -d tel:"+AllQA.PRIMARYDEVMDN+"");
	}


	public void rejectcallRef() throws IOException {
		//Disconnect call in Reference device

		SoftAssert sa = new SoftAssert();
		for(int j=1;j<=1;j++){
			String value=null;
			Process child=Runtime.getRuntime().exec("adb -s "+p_Id+" shell service call telecom 28");
			InputStream lsOut = child.getInputStream();
			InputStreamReader r = new InputStreamReader(lsOut);
			BufferedReader in = new BufferedReader(r);
			value=in.readLine();
			System.out.println(value);
			System.out.println(r_Id);
			if(value.contains("00000001")) {

				System.out.println("Phone is rining");
				Runtime.getRuntime().exec("adb -s "+r_Id+" shell input keyevent 6");
				sa.assertTrue(true, "PASS");
				test.log(LogStatus.INFO, "Reference phone is disconnected");
				break;


			}else {

				test.log(LogStatus.INFO, "Reference phone is not ringing");
				System.out.println("Reference phone is not rining");

				sa.fail();
			}


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

			if (isElementExist(Locators_DevSanity.airplaneMode_Call)) {
				check1 = true;
				APP_LOGS.info("flight mode is enabled sucessfully"+ check1);
				minWait();	
				clickBtn(Locators_DevSanity.OK_Btn);
				minWait();	
			}
			customWait(1000);
			Runtime.getRuntime().exec("adb -s "+Uid+" shell am start -a android.settings.AIRPLANE_MODE_SETTINGS");
			minWait();
			disableSwitch(Locators_DevSanity.disabled_Airplane,Locators_DevSanity.enabled_Airplane,Locators_DevSanity.switch_widget );		
			customWait(9000);
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			minWait();
			initiateCall();
			customWait(3000);
			if (!isElementExist(Locators_DevSanity.airplaneMode_Call)) {
				APP_LOGS.info("flight mode is disabled sucessfully");
				check2 = true;
				minWait();	
			}				
			customWait(1000);
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_ENDCALL);
			if((check1)&&(check2)){
				check= true;
				APP_LOGS.info("Enable ansd disable of Airplane mode with phone settings.");
				test.log(LogStatus.INFO, "Enable ansd disable of Airplane mode with phone settings validated Successful");
				APP_LOGS.info("TC is Passed.");
				sf.assertTrue(check, "Validation is Pass");
			}else{
				APP_LOGS.info("test case is failed");
				test.log(LogStatus.ERROR, "Enable ansd disable of Airplane mode with phone settings, Unsuccessful");
				sf.fail();
			}
			minWait();	
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			minWait();	
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
		} catch (Exception e) {
			APP_LOGS.info("No Element found.");
			e.printStackTrace();
			test.log(LogStatus.ERROR, "No Such Element Found");
			sf.fail();
		}
		sf.assertAll();
	}

	public void validateAirplaneSB() throws InterruptedException, IOException, ParseException {
		/*
		 * Airplane enable and disable
		 */
		SoftAssert sf = new SoftAssert();
		String Uid=JsonFileReaderAndWriter.primaryDevIdReader();

		try {
			if (isElementExist(Locators_DevSanity.webpageNotavailable)) {
				check1 = true;
				APP_LOGS.info("flight mode is enabled sucessfully"+ check1);
				minWait();	
			}
			customWait(1000);
			Runtime.getRuntime().exec("adb -s "+Uid+" shell am start -a android.settings.AIRPLANE_MODE_SETTINGS");
			minWait();
			disableSwitch(Locators_DevSanity.disabled_Airplane,Locators_DevSanity.enabled_Airplane,Locators_DevSanity.switch_widget );		
			customWait(9000);
			launch_an_app("browser");	
			customWait(2000);
			enterUrl("www.google.com");
			customWait(3000);
			if ((isElementExist(Locators_DevSanity.SearchIcon_Google))||(isElementExist(Locators_DevSanity.Searchenter_Google))) {
				APP_LOGS.info("flight mode is disabled sucessfully");
				check2 = true;
				minWait();	
			}			

			if((check1)&&(check2)){
				check= true;
				APP_LOGS.info("Enable ansd disable of Airplane mode with phone settings.");
				test.log(LogStatus.INFO, "Enable ansd disable of Airplane mode with phone settings validated Successful");
				APP_LOGS.info("TC is Passed.");
				sf.assertTrue(check, "Validation is Pass");
			}else{
				APP_LOGS.info("test case is failed");
				test.log(LogStatus.ERROR, "Enable ansd disable of Airplane mode with phone settings, Unsuccessful");
				sf.fail();
			}
			minWait();	
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			minWait();	
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
		} catch (Exception e) {
			APP_LOGS.info("No Element found.");
			e.printStackTrace();
			test.log(LogStatus.ERROR, "No Such Element Found");
			sf.fail();
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
			//			validate_launchofApp(Locators_DevSanity.camera_Icon, "Camera");

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

		} catch (Exception e) {
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
				test.log(LogStatus.INFO, elementName + " Application Successfully Launched ");
				Sa.assertTrue(check, "Tc Passed");

			} else {
				check = false;
				APP_LOGS.info(elementName + " is not found");
				test.log(LogStatus.ERROR, elementName + " Application is not Launched");
				Sa.fail();
			}
		} catch (Exception e) {
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
		} catch (Exception e) {

			e.printStackTrace();
			Assert.fail();
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
			minWait();	
			minWait();	
			clickBtn(Locators_DevSanity.sim_status);
			minWait();	
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
			minWait();
			minWait();	
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
			minWait();
			minWait();	
			clickBtn(Locators_DevSanity.cellularNwType);
			minWait();
			String get_NetworkType = Locators_DevSanity.networkType.getText();	
			System.out.println(get_NetworkType);
			if(get_NetworkType.contains(type)) {
				check = true;
				APP_LOGS.info("Network Type changed and verified "+get_NetworkType);
				test.log(LogStatus.INFO, "Prefered Network Type changed and verified "+get_NetworkType);
				sf.assertTrue(check, "Validation is Pass");
			}else{
				APP_LOGS.info("test Case Failed");
				test.log(LogStatus.ERROR,"Prefered Network Type changed and unverified "+get_NetworkType);
				sf.fail();
			}			
			back();
			minWait();
			back();
		} catch (Exception e) {
			APP_LOGS.info("No Element found.");
			test.log(LogStatus.ERROR, "No Such Element FOund");
			minWait();
			sf.fail();
		}
		sf.assertAll();
	}

	public void changePreferedNetworkType() throws InterruptedException, IOException {
		/*
		 * change network type
		 */
		minWait();	
		clickBtn(Locators_DevSanity.PreferredNwType_Operators);
		minWait();
		changeNetwork("GSM only");
		back();
		navigateToSettingsFeature(Locators_DevSanity.AboutPhone,"About phone");	
		validateLTESelection("EDGE");

		navigateTo_NetworkSettings();
		minWait();	
		clickBtn(Locators_DevSanity.PreferredNwType_Operators);
		minWait();
		changeNetwork("WCDMA only");
		back();
		navigateToSettingsFeature(Locators_DevSanity.AboutPhone,"About phone");	
		validateLTESelection("HSPA");

		navigateTo_NetworkSettings();
		minWait();	
		clickBtn(Locators_DevSanity.PreferredNwType_Operators);
		minWait();
		changeNetwork("WCDMAGSM only");
		back();
		navigateToSettingsFeature(Locators_DevSanity.AboutPhone,"About phone");	
		validateLTESelection("HSPA");

		navigateTo_NetworkSettings();
		minWait();	
		clickBtn(Locators_DevSanity.PreferredNwType_Operators);
		minWait();
		changeNetwork("LTE only");
		back();
		navigateToSettingsFeature(Locators_DevSanity.AboutPhone,"About phone");	
		validateLTESelection("LTE");
	}


	public void changePreferedNetworkType_Reskin() throws InterruptedException, IOException {
		/*
		 * change network type
		 */
		minWait();	
		clickBtn(Locators_DevSanity.PreferredNwType_Operators);
		minWait();
		changeNetwork("GSM only");
		back();
		navigateToSettingsFeature(Locators_DevSanity.AboutPhone,"About phone");	
		validateLTESelection("EDGE");
		back();


		navigateTo_NetworkSettings();
		minWait();	
		clickBtn(Locators_DevSanity.PreferredNwType_Operators);
		minWait();
		//		System.out.println("IN Search of UMTS");
		changeNetwork("UMTS");
		minWait();
		back();		
		navigateToSettingsFeature(Locators_DevSanity.AboutPhone,"About phone");	
		validateLTESelection("HSPA");

		navigateTo_NetworkSettings();
		minWait();	
		clickBtn(Locators_DevSanity.PreferredNwType_Operators);
		minWait();
		changeNetwork("LTE only");
		back();
		navigateToSettingsFeature(Locators_DevSanity.AboutPhone,"About phone");	
		validateLTESelection("LTE");
	}

	public void changePreferedNetworkType_Sprint() throws InterruptedException, IOException {
		/*
		 * change network type
		 */
		SoftAssert sf = new SoftAssert();
		minWait();	
		clickBtn(Locators_DevSanity.PreferredNwType_Operators);
		minWait();
		changeNetwork("GSM");
		back();
		navigateToSettingsFeature(Locators_DevSanity.AboutPhone,"About phone");		
		validateLTESelection("HSPA");


		navigateTo_NetworkSettings();
		minWait();	
		clickBtn(Locators_DevSanity.PreferredNwType_Operators);
		minWait();
		changeNetwork("CDMA");
		back();
		navigateToSettingsFeature(Locators_DevSanity.AboutPhone,"About phone");	
		validateLTESelection("UMTS");

		navigateTo_NetworkSettings();
		minWait();	
		clickBtn(Locators_DevSanity.PreferredNwType_Operators);
		minWait();
		changeNetwork("LTE");
		back();
		navigateToSettingsFeature(Locators_DevSanity.AboutPhone,"About phone");	
		validateLTESelection("LTE");
	}




	public void changeNetwork(String networkType) throws InterruptedException {
		customWait(2000);
		switch (networkType) {
		case "GSM only": 
			clickBtn(Locators_DevSanity.GSM_NetwrkTyp);
			APP_LOGS.info("Clicked on GSM Preferred Network Type successfully.");
			break;

		case "GSM": 
			clickBtn(Locators_DevSanity.GSM_NetwrkTyp);
			APP_LOGS.info("Clicked on GSM Preferred Network Type successfully.");
			break;

		case "UMTS": 
			minWait();
			//			System.out.println("In case UMTS");
			clickBtn(Locators_DevSanity.UMTS_NetwrkTyp);
			APP_LOGS.info("Clicked on GSM Preferred Network Type successfully.");
			break;

		case "WCDMA only": 
			clickBtn(Locators_DevSanity.WCDMA_NetwrkTyp);
			APP_LOGS.info("Clicked on GSM Preferred Network Type successfully.");
			break;

		case "CDMA": 
			clickBtn(Locators_DevSanity.CDMA_NetwrkTyp);
			APP_LOGS.info("Clicked on GSM Preferred Network Type successfully.");
			break;

		case "WCDMAGSM only": 
			clickBtn(Locators_DevSanity.WCDMAGSM_NetwrkTyp);
			APP_LOGS.info("Clicked on GSM Preferred Network Type successfully.");
			break;

		case "LTE only": 
			clickBtn(Locators_DevSanity.LTE_NetwrkTyp);
			APP_LOGS.info("Clicked on GSM Preferred Network Type successfully.");
			break;

		case "LTE": 
			clickBtn(Locators_DevSanity.LTE_NetwrkTyp);
			APP_LOGS.info("Clicked on GSM Preferred Network Type successfully.");
			break;
		}

	}


	public void navigateTo_NetworkSettings() throws InterruptedException {
		/*
		 * This Method will Navigate to the APN's  
		 */
		try {
			launch_an_app("settings");
			minWait();

			if(isElementExist(Locators_DevSanity.mobile_Networks)) {
				clickBtn(Locators_DevSanity.mobile_Networks);
			}
			else {
				clickBtn(Locators_DevSanity.moreOptn);
			}
			minWait();
			for (int i = 1; i <=4; i++) {
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
				Thread.sleep(1000);
			}
			minWait();
			if(isElementExist(Locators_DevSanity.cellular_Networks)) {
				clickBtn(Locators_DevSanity.cellular_Networks);
				minWait();
				minWait();
			}
			else {
				clickBtn(Locators_DevSanity.cellular_newtworksOptn);
			}	
			for (int i = 1; i <=4; i++) {
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
				Thread.sleep(1000);
			}

		} catch (Exception e) {
			APP_LOGS.info("No Element found.");
			test.log(LogStatus.ERROR, "No Such Element Found");
			Assert.fail();
		}
	}



	public void navigateToDataRoaming() throws InterruptedException {
		try {
			minWait();	
			minWait();	
			for(int i=1; i<=5; i++) {
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);	
				minWait();
			}
			if(isElementExist(Locators_DevSanity.MobileNetwrks)) {
				minWait();	
				clickBtn(Locators_DevSanity.MobileNetwrks);
			}
			else {
				minWait();	
				clickBtn(Locators_DevSanity.moreOptn);
				minWait();	
				clickBtn(Locators_DevSanity.cellular_newtworksOptn);
			}

			if(isElementExist(Locators_DevSanity.data_Roaming)) {
				clickBtn(Locators_DevSanity.data_Roaming);
				minWait();
			}
			else {
				clickBtn(Locators_DevSanity.data_Roaming_Sprint);
				minWait();
				minWait();	
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);	
				minWait();	
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);	

			}
			minWait();	
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);	
			minWait();


		} catch (Exception e) {
			APP_LOGS.info("No Element found.");
			minWait();	
			Assert.fail();
		}
	}

	public void enabledataRoaming(WebElement enablebtn, WebElement disablebtn, WebElement switchtitle) throws InterruptedException {
		//enable data roaming

		minWait();
		if(isElementExist(switchtitle)) {
			if (!isElementExist(enablebtn)) {
				minWait();
				clickBtn(disablebtn);
				APP_LOGS.info("Feature is Enabled");                     
				minWait();
			}
		}
	}

	public void validateDataRoamingService(WebElement enable,String str) throws InterruptedException {
		SoftAssert sf = new SoftAssert();
		try {

			if(isElementExist(enable)) {
				check = true;
				APP_LOGS.info("check: "+check);
				APP_LOGS.info(str+" the Data Roaming is sucessful");
				test.log(LogStatus.INFO, str+" the Data Roaming is sucessful");
				sf.assertTrue(check, "");
			}else{
				APP_LOGS.info("check: "+check);
				APP_LOGS.info(str+" the Data Roaming is not sucessful");
				test.log(LogStatus.ERROR, str+" the Data Roaming is not sucessful");
				sf.fail();
			}	
		} catch (Exception e) {
			APP_LOGS.info("No Element found.");
			sf.fail();
		}
		sf.assertAll();
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
				if(isElementExist(Locators_DevSanity.quickSettings)) {
					APP_LOGS.info("In Quick Settings");
					minWait();
					break;					
				} else {
					aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_LEFT);
					continue;					
				}	
			}
		} catch (Exception e) {
			APP_LOGS.info("More Option is not displayed.");
			test.log(LogStatus.ERROR, "No Such Element Found");
			minWait();	
			Assert.fail();
		}

	}



	public void validateEnableDisable(WebElement app,String str,WebElement enabled,WebElement disabled) throws InterruptedException {
		/*
		 * validate enabling and disabling the wifi, bluetooth, and airoplane mode.
		 */
		SoftAssert sf = new SoftAssert();
		try {
			minWait();	
			clickBtn(app);
			minWait();
			if (!isElementExist(enabled)) {
				minWait();
				clickBtn(disabled);
				APP_LOGS.info("Switch is Enabled");                     
				minWait();
			}
			String get_enable = Locators_DevSanity.enable.getText();	
			//			System.out.println(get_enable);
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
			String get_enable2 = Locators_DevSanity.enable.getText();	
			//			System.out.println(get_enable2);
			minWait();	
			if(get_enable2.equalsIgnoreCase("OFF")) {
				minWait();	
				check2 = true;
				APP_LOGS.info("Disabled is sucessful");
			}
			if((check1)&&(check2)){
				check= true;
				APP_LOGS.info("Enable ansd disable with quick settings.");
				test.log(LogStatus.INFO, str + "Enable ansd disable with quick settings.");
				sf.assertTrue(check, "Validation is pass");
			}else{
				APP_LOGS.info("Test case is failed");
				test.log(LogStatus.ERROR, " Validation Failed, Enable ansd disable with quick settings.");
				sf.fail();
			}
			minWait();	
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
		} catch (Exception e) {
			APP_LOGS.info("No Element found.");
			e.printStackTrace();
			test.log(LogStatus.ERROR, "No Such Element Found");
			sf.fail();			
		}
		sf.assertAll();
	}

	public void soundRecorderLaunch() throws InterruptedException {
		/*
		 * Launch and check SOund Recorder home page
		 */
		try {
			launch_an_app("soundRecorder");
			if(isElementExist(Locators_DevSanity.recording_List)) {
				back();
				launch_an_app("soundRecorder");
			}
		} catch (Exception e) {

			e.printStackTrace();
			Assert.fail();
		}
	}

	//Delete Saved Recorded Sound
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
		} catch (Exception e) {

			e.printStackTrace();
			Assert.fail();
		}
	}


	public void playMusic() throws InterruptedException{
		try {
			minWait();
			launch_an_app("music");
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
			minWait();
			clickBtn(Locators_DevSanity.music);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
			customWait(4000);
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
		} catch (Exception e) {

			e.printStackTrace();
			Assert.fail();
		}


	}
	// Validates music Player.
	public void validate_music_player() throws InterruptedException{
		launch_an_app("music");
		try {
			if(isElementExist(Locators_DevSanity.musicIndicator)){
				APP_LOGS.info("Music played successfully.");
				test.log(LogStatus.INFO, "Music played successfully.");
				Assert.assertTrue(true);
			}else{
				APP_LOGS.info("Music is not played.");
				test.log(LogStatus.ERROR, "Music is not played.");
				Assert.fail();
			}
		} catch (Exception e) {
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
				test.log(LogStatus.INFO, "Sleep Time is changed successfully");
				Assert.assertTrue(check);
			}
			else {
				test.log(LogStatus.ERROR, "Sleep Time is not changed ");
				Assert.fail();
			}
			clickBtn(Locators_DevSanity.Sleep_Optn);
			minWait();

			for(int i=1; i<=8; i++) {
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
			}
			clickBtn(Locators_DevSanity.Ringtone_SilkyWay);
			test.log(LogStatus.INFO, " Sleep Mode is set to maximum");
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
		} catch (Exception e) {

			e.printStackTrace();
			test.log(LogStatus.ERROR, " No such Element Found");
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

	public void reciveCallInRefDevice() throws InterruptedException, IOException {
		/*
		 * Receive Call in Reference device
		 */
		try {
			if (!isElementExist(Locators_DevSanity.turnOff_Airplane_PopUp)) {
				System.out.println("Inside receive call");
				try {
					for(int j=1;j<=100;j++){
						System.out.println("Inside loop");
						System.out.println(r_b_No);
						Process child = null;
						if (r_b_No.contains("8A.")) {
							child=Runtime.getRuntime().exec("adb -s "+r_Id+" shell service call telecom 27");
						} else if(r_b_No.contains("5SA.")) {
							System.out.println("XP5S receiving call in REF device");
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
			APP_LOGS.info("Number dailed is: ");
		}catch (Exception e) {
			APP_LOGS.info("Selected Page is not displayed.");
			e.printStackTrace();
		}
	}

	public void speakerOnOff() throws InterruptedException, IOException
	{
		/*
		 * Validates in call functionality
		 */
		try {
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_UP);
			customWait(2000);
			//	if()
		}catch (Exception e) {
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
		}catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "Element Not Found");
		}
	}

	public void addContactAndCopyToSIM(String Name) throws InterruptedException, IOException
	{
		/*
		 * Add's Contact with Name and Phone Number and validates
		 */
		try {
			checkAddContactsOption();
			minWait();
			enterNameAndPhone(Name);
			minWait();
			clickMenuAndElement(Locators_DevSanity.saveBtn);
			clickBackButton(1);
			customWait(2000);
			Runtime.getRuntime().exec("adb -s "+p_Id+" shell input text "+ Name);
			customWait(3000);
			validateAddedContact(Name);
			minWait();
			clickMenuButton();
			minWait();
			if(isElementExist(Locators_DevSanity.copyToSIMOpt)) {
				clickBtn(Locators_DevSanity.copyToSIMOpt);
			}else {
				clickBtn(Locators_DevSanity.copyToSIM1Opt);
			}
			minWait();
			clickBackButton(1);
			validatecopiedContactToSIM();
			clickBackButton(1);
		}catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "Element Not Found");
		}
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
				clickMenuAndElement(Locators_DevSanity.selectOpt);
				minWait();
				clickMenuAndElement(Locators_DevSanity.contactsSelectAllOpt);
				minWait();
				clickMenuAndElement(Locators_DevSanity.deleteOpt);
				minWait();
				Locators_DevSanity.deleteBtn.click();
				minWait();
			}
		} catch (Exception e) {
			e.printStackTrace();
			APP_LOGS.info("Element Not Found");
			test.log(LogStatus.ERROR, "Element Not Found");
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
				APP_LOGS.info("Contact copied to SIM successfully");
				test.log(LogStatus.INFO, "Contact copied to SIM successfully");
				SA.assertTrue(check, "Contact copied to SIM successfully");
			}else{
				APP_LOGS.info("Failed to copy Contact to SIM");
				test.log(LogStatus.INFO, "Failed to copy Contact to SIM");
				SA.fail("Failed to copy Contact to SIM");
			} 
		}catch (Exception e) {
			e.printStackTrace();
			APP_LOGS.info("Element Not Found");
			test.log(LogStatus.ERROR, "Element Not Found");
			SA.fail();
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
			enterTextToInputField(Locators_DevSanity.nameField, Name);
			customWait(3000);
			enterTextToInputField(Locators_DevSanity.phoneField, refNum);
			customWait(3000);
		}
		catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "Element Not Found");
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
					APP_LOGS.info("Contact Added Successfully");
					test.log(LogStatus.INFO, "Contact Added Successfully");
					SA.assertTrue(check, "Contact Added Successfully");
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
		}catch (Exception e) {
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
		}catch (Exception e) {
			e.printStackTrace();
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
			Locators_DevSanity.moreFldOpt.click();
			minWait();
			aDriver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().className(\"android.widget.ScrollView\")).scrollIntoView(new UiSelector().text(\"Name\"))");
			minWait();
			addImageInContact();
			Locators_DevSanity.expandFld.click();
			minWait();
			addNameFields(namePrefix, phoneticFirstName, phoneticMiddleName, phoneticLastName);
			minWait();
			ScrollToElement(Locators_DevSanity.scrollpage, "Title");
			minWait();
			addPhoneticNameFields(nameSuffix, phoneticLastName, phoneticMiddleName, phoneticFirstName, nickName);
			minWait();
			ScrollToElement(Locators_DevSanity.scrollpage, "SIP");
			minWait();
			addComapnyAndPhoneNumber(company, title);
			minWait();
			ScrollToElement(Locators_DevSanity.scrollpage, "Email");
			minWait();
			addSIPEmailAndAddress(SIP, Email, address);
			minWait();
			ScrollToElement(Locators_DevSanity.scrollpage, "Website");
			minWait();
			addOtherFieldsInContact(IM, website, Notes);
			minWait();
			clickMenuAndElement(Locators_DevSanity.saveBtn);
			clickBackButton(1);
			customWait(3000);
			Runtime.getRuntime().exec("adb -s "+Uid+" shell input text Sonim");
			customWait(2000);
			validateAddedContact(namePrefix);
		}catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "Element Not Found");
		}
	}

	public void addImageInContact() throws InterruptedException
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
		}catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "Element Not Found");
		}
	}

	public void addNameFields(String namePrefix,String firstName,String middleName,String lastName) throws InterruptedException
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
		}catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "Element Not Found");
		}
	}

	public void addPhoneticNameFields(String nameSuffix,String phoneticLastName,String phoneticMiddleName,String phoneticFirstName,String nickName) throws InterruptedException
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
		}catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "Element Not Found");
		}
	}

	public void addComapnyAndPhoneNumber(String company,String title) throws InterruptedException
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
			enterTextToInputField(Locators_DevSanity.phoneField,refNum);
		}catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "Element Not Found");
		}
	}

	public void addSIPEmailAndAddress(String SIP,String Email,String address) throws InterruptedException
	{
		/*
		 * enters SIP,Email and Address
		 */
		try
		{
			enterTextToInputField(Locators_DevSanity.sipFld,SIP);
			minWait();
			enterTextToInputField(Locators_DevSanity.emailFld,Email);
			minWait();
			ScrollToElement(Locators_DevSanity.scrollpage, "Address");
			minWait();
			enterTextToInputField(Locators_DevSanity.addressFld,address);
			minWait();
		}catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "Element Not Found");
		}
	}

	public void addOtherFieldsInContact(String IM,String website,String Notes) throws InterruptedException
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
		}catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "Element Not Found");
		}
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
			Locators_DevSanity.deleteBtn.click();
			customWait(2000);
			clickBackButton(1);
			customWait(2000);
			Runtime.getRuntime().exec("adb -s "+Uid+" shell input text "+ name2);
			customWait(3000);
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
			minWait();
			clickMenuAndElement(Locators_DevSanity.deleteOpt);
			minWait();
			Locators_DevSanity.deleteBtn.click();
			minWait();
			validatedeletedcontact();
			clickBackButton(1);
			deleteIfContactsPresent();
		} catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "Element Not Found");
		}
	}



	public void validatedeletedcontact() throws InterruptedException, IOException
	{
		/*
		 * Validates deleted Contact
		 */
		SoftAssert SA = new SoftAssert();
		try {
			if(isElementExist(Locators_DevSanity.noContacts)){
				check = true;
				APP_LOGS.info("Contact deleted successfully");
				test.log(LogStatus.INFO, "Contact deleted successfully");
				SA.assertTrue(check, "Contact deleted successfully");	
			}else{
				APP_LOGS.info("Contact not deleted");
				test.log(LogStatus.INFO, "Contact not deleted");
				SA.fail("Contact not deleted");
			}
		}catch (Exception e) {
			e.printStackTrace();
			APP_LOGS.info("Element Not Found");
			test.log(LogStatus.ERROR, "Element Not Found");
			SA.fail();
		}
		SA.assertAll();
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
		}catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "Element Not Found");
		}
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
		}catch (Exception e) {
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
		}catch (Exception e) {
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
		}catch (Exception e) {
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
		}catch (Exception e) {
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
			clickBtn(Locators_DevSanity.tools);
			minWait();
			clickBtn(Locators_DevSanity.clock);
		}catch (Exception e) {
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
			clickMenuAndElement(Locators_DevSanity.addOpt);
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
		}catch (Exception e) {
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
		}catch (Exception e) {
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
		}catch (Exception e) {
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
		}catch (Exception e) {
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
		}catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "Element Not Found");
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
		aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
		//		clickBtn(Locators_DevSanity.Ringtone_StarVersus);
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

		//		clickBtn(Locators_DevSanity.Ringtone_SilkyWay);
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
		} catch (Exception e) {

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
		} catch (Exception e) {

			e.printStackTrace();
			test.log(LogStatus.ERROR, "No Such Element Found");
			S1.fail();

		}
		S1.assertAll();
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
		} catch (Exception e) {
			e.printStackTrace();
			SA.fail();
		}	
		SA.assertAll();
	}

	public void navigateApps(WebElement tab,WebElement app) throws InterruptedException, IOException {
		minWait();
		launch_an_app("scout");
		try {
			customWait(2000);
			clickBtn(tab);
			customWait(1000);
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

		} catch (Exception e) {

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
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
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
			back();
		} catch (Exception e) {

			e.printStackTrace();
		}
	}


	public void validatelaunchAllSCoutApps() throws InterruptedException, IOException, ParseException {
		/*
		 * Launch scout Application tray and all apps
		 */
		String XP5deviceModelNumber=JsonFileReaderAndWriter.primaryDevOperatorReader();
		System.out.println(XP5deviceModelNumber);

		navigateApps(Locators_DevSanity.setUpTab,Locators_DevSanity.SonimSetUpWizard);
		validateLaunchScoutApp(Locators_DevSanity.SonimSetUpWizard, "SetUpWizard");

		navigateApps(Locators_DevSanity.setUpTab,Locators_DevSanity.Safeguard);
		validateLaunchScoutApp(Locators_DevSanity.Safeguard, "SafeGuard");

		if(XP5deviceModelNumber.contains("ATT")) {

		}
		else {
			navigateApps(Locators_DevSanity.setUpTab,Locators_DevSanity.AppUpdater);
			validateLaunchScoutApp(Locators_DevSanity.AppUpdater, "AppUpdater");

		}
		//Verify each apps in Utilities tab
		navigateApps(Locators_DevSanity.utilitiesTab,Locators_DevSanity.ContactTransferTitle);
		validateLaunchScoutApp(Locators_DevSanity.ContactTransferTitle, "Contact Transfer");

		navigateApps(Locators_DevSanity.utilitiesTab,Locators_DevSanity.BLEconnect);
		if(isElementExist(Locators_DevSanity.BLEconnectError)) {
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
		}
		validateLaunchScoutApp(Locators_DevSanity.BLEconnect, "Sonim BLE connect");

		//verify each apps in Support tab
		navigateApps(Locators_DevSanity.supportTab,Locators_DevSanity.Chat);
		validateLaunchScoutApp(Locators_DevSanity.Chat_Title, "Sonim Chat");

		/*navigateApps(Locators_DevSanity.supportTab,Locators_DevSanity.RemoteSupport);
		customWait(6000);
		validateLaunchScoutApp(Locators_DevSanity.RemoteControl_pge, "Remote Control");*/


		navigateApps(Locators_DevSanity.supportTab,Locators_DevSanity.SonimCare);
		validateLaunchScoutApp(Locators_DevSanity.SonimCare, "SonimCare");


		if(XP5deviceModelNumber.contains("SL")) {

		}
		else {
			navigateApps(Locators_DevSanity.supportTab,Locators_DevSanity.WarrantyReg);
			validateLaunchScoutApp(Locators_DevSanity.WarrantyReg, "Warranty Registration");
		}

	}


	public void rejectWithSMS() throws InterruptedException, IOException
	{
		/*
		 * reject with SMS
		 */
		SoftAssert SA = new SoftAssert();
		try {

			customWait(8000);
			if(isElementExist(Locators_DevSanity.rejectWithSMSTextCallPage)) {
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_MENU);
				minWait();
				clickCenterButton(1);
				validateSentQuickResponse();
			}else {
				APP_LOGS.info("Call not received");
				test.log(LogStatus.INFO, "Call not received");
				SA.fail("Call not received");
			}
			clickBackButton(2);
		}catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "Element Not Found");
		}
		SA.assertAll();
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
		}catch (Exception e) {
			e.printStackTrace();
			APP_LOGS.info("Element Not Found");
			test.log(LogStatus.ERROR, "Element Not Found");
			SA.fail();
		}
		SA.assertAll();
	}


	public void sendSMSWhenInCall(String text) throws InterruptedException, IOException
	{
		/*
		 * Send SMS with ongoing call
		 */
		try {
			inputData(refNum);
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_CALL);
			reciveCallInRefDevice();
			minWait();
			launch_an_app("messaging");
			minWait();
			clickMenuAndElement(Locators_DevSanity.newMessageOpt);
			minWait();
			changeToNumberMode_SMS(Locators_DevSanity.toField_NewMessage);
			enterTextToInputField(Locators_DevSanity.toField_NewMessage, refNum);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
			minWait();
			enterTextToInputField(Locators_DevSanity.typeMessageOpt, text);
			minWait();
			clickMenuAndElement(Locators_DevSanity.sendOpt);
			customWait(3000);
			clickBackButton(1);
			validateSendMessage();
		}catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "Element Not Found");
		}
	}

	public void validateSendMessage() throws InterruptedException, IOException
	{
		/*
		 * Validates sent message
		 */
		SoftAssert SA = new SoftAssert();
		try {
			if(isElementExist(Locators_DevSanity.messageNowOpt)){
				minWait();
				clickEndCallButton();
				minWait();
				clickEndCallButton();
				check = true;
				APP_LOGS.info("Message sent successfully");
				test.log(LogStatus.INFO, "Message sent successfully");
				SA.assertTrue(check, "Message sent successfully");	
			}
			else {
				APP_LOGS.info("Message not sent");
				test.log(LogStatus.ERROR, "Message not sent");
				SA.fail("Message not sent");
			}	
		}catch (Exception e) {
			e.printStackTrace();
			APP_LOGS.info("Element Not Found");
			test.log(LogStatus.ERROR, "Element Not Found");
			SA.fail();
		}
		SA.assertAll();
	}

	public void callHistoryDetails() throws InterruptedException, IOException, ParseException
	{
		/*
		 * validates call history details
		 */
		try {
			//==========outgoing call==========
			MO_CALL_Sanity();
			customWait(2000);
			clickEndCallButton();


			//incoming call
			customWait(5000);
			MT_XP5Call();
			customWait(2000);
			clickEndCallButton();

			//Missed call in primary device
			customWait(5000);
			initiateCallRef();
			customWait(9000);
			rejectcallRef();

			//			 Runtime.getRuntime().exec("adb -s "+r_Id+" shell input keyevent 6");
			customWait(3000);



			//==========declined call=========
			initiateCallRef();
			customWait(9000);
			Runtime.getRuntime().exec("adb -s "+p_Id+" shell input keyevent 6");
			test.log(LogStatus.INFO, "Missed Call");
			customWait(3000);


			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
			customWait(2000);
			validateCallHistoryDeclinedAndMissed();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
			validateCallHistoryIncomingAndOutgoing();
		}catch (Exception e) {
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
				APP_LOGS.info("Call History with declined call and missed call verified successfully");
				test.log(LogStatus.INFO, "Call History with declined call and missed call verified successfully");
				SA.assertTrue(check, "Call History with declined call and missed call verified successfully");	
			}else {
				APP_LOGS.info("Call History with declined call and missed call not verified");
				test.log(LogStatus.ERROR, "Call History with declined call and missed call not verified");
				SA.fail("Call History with declined call and missed call not verified");
			}	
		}catch (Exception e) {
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
				APP_LOGS.info("Call History with incoming call and outgoing call verified successfully");
				test.log(LogStatus.INFO, "Call History with incoming call and outgoing call verified successfully");
				SA.assertTrue(check, "Call History with incoming call and outgoing call verified successfully");	
			}else {
				APP_LOGS.info("Call History with incoming call and outgoing call not verified");
				test.log(LogStatus.ERROR, "Call History with incoming call and outgoing call not verified");
				SA.fail("Call History with incoming call and outgoing call not verified");
			}	
		}catch (Exception e) {
			e.printStackTrace();
			APP_LOGS.info("Element Not Found");
			test.log(LogStatus.ERROR, "Element Not Found");
			SA.fail();
		}
		SA.assertAll();
	}


	public void inCallFunctionality() throws InterruptedException, IOException, ParseException, AWTException
	{
		/*
		 * Validates in call functionality
		 */
		try {
			minWait();
			initiateCall();
			customWait(7000);
			reciveCallInRefDevice();
			customWait(3000);
			speakerStatus();
			customWait(3000);
			muteFunctionStatus();
			customWait(6000);
			holdFunctionStatus();
			customWait(3000);
			clickEndCallButton();
		}catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "Element Not Found");
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
			//validateInCallFunctionality("Speaker Off","Message received: USER_SWITCH_EARPIECE=1101, arg1=0: CSW.hCCC->CARSM.pM_USER_SWITCH_EARPIECE","XP5S_Sanity_008");
			validateSpeaker("Speaker Off", "CallAudioRouteStateMachine: setSystemAudioState: changing from [AudioState isMuted: false, route: SPEAKER, supportedRouteMask: EARPIECE, SPEAKER] to [AudioState isMuted: false, route: EARPIECE, supportedRouteMask: EARPIECE, SPEAKER]: ICA.sAR->CARSM.pM_USER_SWITCH_EARPIECE",
					"CallAudioRouteStateMachine: setSystemAudioState: changing from [AudioState isMuted: false, route: SPEAKER, supportedRouteMask: WIRED_HEADSET, SPEAKER] to [AudioState isMuted: false, route: WIRED_HEADSET, supportedRouteMask: WIRED_HEADSET, SPEAKER]: ICA.sAR->CARSM.pM_USER_SWITCH_HEADSET", "XP5S_Dev_Sanity_08_1");
		}catch (Exception e) {
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
		}catch (Exception e) {
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
			customWait(3000);
			validateInCallFunctionality("Mute","Message received: MUTE_ON=3001, arg1=0: ICA.m->CARSM.pM_MUTE_ON");
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_LEFT);
			customWait(2000);
			validateInCallFunctionality("Unmute","Message received: MUTE_OFF=3002, arg1=0: ICA.m->CARSM.pM_MUTE_OFF");
		}catch (Exception e) {
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
			minWait();
			Runtime.getRuntime().exec("adb -s "+p_Id+" shell input keyevent 22");
			customWait(5000);
			validateInCallFunctionality("Hold","GsmCdmaConnection: [GsmCdmaConn] parent= ACTIVE, newParent= IDLE");
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_RIGHT);
			customWait(2000);
			validateInCallFunctionality("Unhold","GsmCdmaConnection: [GsmCdmaConn] parent= HOLDING, newParent= IDLE");
		}catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "Element Not Found");
		}
	}
	public void validateInCallFunctionality(String function,String searchstring) throws InterruptedException, IOException
	{
		/*
		 * Validate call history
		 */
		SoftAssert SA = new SoftAssert();
		try {
			boolean value = searchString(searchstring, "XP5_Dev_Sanity_08");
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
		}catch (Exception e) {
			e.printStackTrace();
			APP_LOGS.info("Element Not Found");
			test.log(LogStatus.ERROR, "Element Not Found");
			SA.fail();
		}
		SA.assertAll();
	}

	public void changeToNumberMode_SMS(WebElement element) throws InterruptedException {
		/*
		 * Change input field type to number
		 */
		minWait();
		element.sendKeys("123");
		customWait(2000);
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
		catch (Exception e) {
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

	public void clickCenterButton(int number)
	{
		/*
		 * clicks on back button with iteration as user input
		 */
		try {
			for(int i=0;i<number;i++){
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
				minWait();
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}
