package com.xp3.Utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.asserts.SoftAssert;

import com.graphics.gui.JsonFileReaderAndWriter;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import application.AllQA;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.AndroidKeyCode;

public class XP3_Stability_Util extends BaseUtil {

	public static ExtentReports extent;
	public static ExtentTest test;

	public String p_Id  	= "";    		// Primary Device Serial Number.
	public String r_Id 		= "";    		// Reference Device Serial Number.
	public String p_b_No 	= "";      		// Primary Device Build Number.
	public String r_b_No 	= "";    		// Reference Device Build Number.
	public String pryNum = AllQA.PRIMARYDEVMDN;	// Reference Device MDN. 
	public String refNum = AllQA.REFERENCEDEVMDN;	// Reference Device MDN.

	public void fetch_Devices_Details() throws InterruptedException, FileNotFoundException, IOException, ParseException {

		minWait();
		p_Id=JsonFileReaderAndWriter.primaryDevIdReader();
		r_Id=JsonFileReaderAndWriter.ReadRefDeviceId();
		p_b_No=JsonFileReaderAndWriter.primaryDevFirmwareReader();
		r_b_No=JsonFileReaderAndWriter.ReadRefDeviceFirmWare();
	}

	public boolean check = false;
	public boolean check1 = false;
	public boolean check2 = false;
	public boolean check3 = false;
	public boolean check4 = false;
	public boolean check5 = false;

	public void navigateToApplication(String application) throws InterruptedException {

		try {
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_HOME);
			minWait();
			navigateUsingText(application);
			minWait();
		} catch (Exception e) {
			System.out.println("Handling Exception");
			e.printStackTrace();
		}
	}


	public void press_BackKey() throws InterruptedException {
		minWait();
		aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
		minWait();
	}
	public void deleteContact() throws InterruptedException  {	
		/*
		 * delete the contact from phone memory
		 */
		try {

			if(isElementExist(Locators_DeviceStability.zero_Characters_Firstcontact)) {
				customWait(500);
				selectFromList("Select");
				customWait(500);
				selectFromList("Select All");
				customWait(1000);
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_MENU);
				customWait(500);
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
				clickBtn(Locators_DeviceStability.contact_delete);

				customWait(500);
				minWait();	
				clickBtn(Locators_DeviceStability.contact_DeleteBtn);
				minWait();	

				if(isElementExist(Locators_DeviceStability.contact_Test)) {
					clickBtn(Locators_DeviceStability.contact_Test);
					customWait(500);
					selectFromList("Delete");

					minWait();	
					clickBtn(Locators_DeviceStability.contact_DeleteBtn);
					minWait();	
				}
				if(isElementExist(Locators_DeviceStability.contact_Testing)) {
					clickBtn(Locators_DeviceStability.contact_Testing);
					customWait(500);
					aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_MENU);
					customWait(500);
					aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
					minWait();
					aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
					clickBtn(Locators_DeviceStability.contact_delete);
					customWait(500);
					customWait(500);
					clickBtn(Locators_DeviceStability.contact_DeleteBtn);
					minWait();	
				}		
			}
			APP_LOGS.info("Deleted all contacts");
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}


	public void createContact(String name,int n,String num) throws InterruptedException, IOException {
		/*
		 * create contacts with name, phone, email and address
		 */
		try {

			if(isElementExist(Locators_DeviceStability.Addcontacts)) {
				clickBtn(Locators_DeviceStability.Addcontacts);
				minWait();	
			}else {
				minWait();	
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_MENU);
				minWait();
				clickBtn(Locators_DeviceStability.contact_addContactsOptn);
			}

			enterTextToInputField(Locators_DeviceStability.contactName, name);
			minWait();	
			for(int i=1; i<=3; i++) {
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_POUND);
				minWait();
			}
			customWait(1000);
			Runtime.getRuntime().exec("adb -s "+p_Id+" shell input text "+n);
			minWait();
			enterTextToInputField(Locators_DeviceStability.contact_Phone, num);
			minWait();
			scrollText("Email");
			minWait();	
			enterTextToInputField(Locators_DeviceStability.contact_Email, "Sonim");
			customWait(2000);
			Runtime.getRuntime().exec("adb -s "+p_Id+" shell input keyevent --longpress KEYCODE_1");
			customWait(1000);
			customWait(1000);
			for(int i=1; i<=3; i++ ) {
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_RIGHT);
			}
			customWait(1000);
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_ENTER);
			customWait(2000);
			Runtime.getRuntime().exec("adb -s "+p_Id+" shell input text gmail.com");	
			System.out.println("Entering Email Id");
			customWait(4000);
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
			minWait();
			scrollText("More fields");
			clickBtn(Locators_DeviceStability.contact_MorefieldsOptn);
			minWait();				
			scrollText("Address");
			minWait();	
			enterTextToInputField(Locators_DeviceStability.contact_Address, "IDC Sonim");
			minWait();	
			minWait();	
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_MENU);
			minWait();
			clickBtn(Locators_DeviceStability.contact_Save);
			minWait();		
			minWait();	
			//			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
		} catch (Exception e) {
			e.printStackTrace();
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
			APP_LOGS.info("Successfully scrolled to "+ text);
			check = true;
			return check;
		}
		catch(Exception e) {
			APP_LOGS.info("Didn't find  "+ text);
			
			if(text.equals("IDCSONWAP")) {
				test.log(LogStatus.FAIL, "Unable to find Secured Wifi or Wifi is Not Found");	
			}
			return check;
			
		}
	}
	public void validateContactCreate(int n,SoftAssert soft,String name) throws InterruptedException 
	{
		/*
		 * validate contact creation present in phone memory
		 */
		try {
			System.out.println(n);
			/*	minWait();
			for(int i=0; i<n; i++) {
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
			}
			minWait();			
			aDriver.pressKeyCode(AndroidKeyCode.ENTER);
			minWait();*/
			//			clickBtn(Locators_DeviceStability.dontAsk_Again);
			minWait();
			/*	enterTextToInputField(Locators_DeviceStability.find_Contacts, name);
			minWait();
			for(int i=1; i<=3; i++) {
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_POUND);
				minWait();
			}
			customWait(1000);
			Runtime.getRuntime().exec("adb -s "+p_Id+" shell input text "+n);
			minWait();*/
			System.out.println("Clicking CheckBox");
			for(int i=1; i<=5; i++) {
				System.out.println("In For Loop");
				if(isElementExist(Locators_DeviceStability.allow_Permission)) {
					minWait();
					System.out.println("allow");
					clickBtn(Locators_DeviceStability.allow_Permission);
					minWait();
				}

				if(isElementExist(Locators_DeviceStability.allow_Permission1)) {
					minWait();
					System.out.println("Permission");
					clickBtn(Locators_DeviceStability.allow_Permission1);
					minWait();
				}
			}
			String getcontactNameText= Locators_DeviceStability.contact_Test.getText();
			System.out.println(getcontactNameText);
			if(getcontactNameText.contains("Test"))
			{	check1 =true;
			APP_LOGS.info("check1: "+check1);
			APP_LOGS.info("Contact Name is: "+getcontactNameText);
			}else{
				APP_LOGS.info("check1: "+check1);
				APP_LOGS.info("Test Name contact is not present");
			}
			minWait();	
			minWait();	
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
			minWait();
			String getPhoneNumText=Locators_DeviceStability.contact_phnNum.getText();


			String phoneNum = getPhoneNumText.replaceAll("[^a-zA-Z0-9]", "");
			System.out.println(phoneNum);
			minWait();
			String refNumber = refNum.replaceAll("[^a-zA-Z0-9]", "");

			if(phoneNum.equalsIgnoreCase(refNumber))
			{	check2 =true;
			APP_LOGS.info("check2: "+check2);
			APP_LOGS.info("Contact Phone Num is: "+getPhoneNumText);
			}else{
				APP_LOGS.info("check2: "+check2);
				APP_LOGS.info("Contact Phone Num is not present");

			}

			minWait();	
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
			minWait();
			String getEmailText =" ";
			getEmailText=Locators_DeviceStability.contact_emailid.getText();
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
			String getAddressText=Locators_DeviceStability.contact_addressText.getText();
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
				test.log(LogStatus.PASS,"Contact created with Name: " +getcontactNameText);
				soft.assertTrue(check, " ");
			}else{
				APP_LOGS.info("Test case failed");					
				soft.fail();
			}
			minWait();	
			back(3);
		} catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "No Such Element Found");
			soft.fail();
		}
	}


	public void performAction() throws IOException, InterruptedException {
		/*
		 * 
		 * perform action airplane mode on and off
		 */
		navigateToApplication("Settings");
		scrollToText("Network & Internet");
		//		Runtime.getRuntime().exec("adb -s "+p_Id+" shell am start -a android.settings.AIRPLANE_MODE_SETTINGS");
		for(int i=1; i<=6;i++) {
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
			minWait();
		}
		minWait();

		enableSwitch(Locators_DeviceStability.enabled_Airplane,Locators_DeviceStability.disabled_Airplane,Locators_DeviceStability.switch_widget);
		customWait(5000);
		disableSwitch(Locators_DeviceStability.disabled_Airplane,Locators_DeviceStability.enabled_Airplane,Locators_DeviceStability.switch_widget );		
		customWait(9000);
	}

	public boolean validate_RIL_Logs(String filename,String validationString,String infoForFailure) throws InterruptedException {

		customWait(2000);
		boolean check1 = searchString(validationString,filename);
		SoftAssert sf = new SoftAssert();
		if (check1) {
			check=true;
			test.log(LogStatus.INFO, "Validated from RIL Logs : IMS is Enable");
		} else {			
			test.log(LogStatus.INFO, "Validation failed from ADB Logs. ==>> "+infoForFailure);
		}
		return check1;
	}


	public void back(int n) throws InterruptedException {
		for(int i=1; i<=n; i++) {
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			minWait();
		}
	}

	public void delete_All_Threads() throws InterruptedException {
		/*
		 * Method is used to delete All the SMS Threads.
		 */
		SoftAssert sf = new SoftAssert();
		try {

			if(isElementExist(Locators_DeviceStability.discard_msg)) {
				clickBtn(Locators_DeviceStability.OK);
			}
			minWait();

			while (true) {

				if (isElementExist(Locators_DeviceStability.first_sms_Thread)||isElementExist(Locators_DeviceStability.first_sms_Thread1)) {
					System.out.println("Is present");
					minWait();
					aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
					aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_CLEAR);
					minWait();
					clickBtn(Locators_DeviceStability.delete_Confirm);
				} else {
					Reporter.log("No Threads Found");
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			//			test.log(LogStatus.ERROR, "Error in Deleting the Messages.");
		}
	}

	public void navigateTo_NewMessage() throws InterruptedException, IOException {
		SoftAssert sf = new SoftAssert();
		try {
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_MENU);
			minWait();
			clickBtn(Locators_DeviceStability.new_Message);
			minWait();
		} catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "Didn't navigated to New Message Window");
		}
	}


	public void validate_CharacterAndPageNumber(WebElement charAndPageNum,String expectedcharAndPageNum,int n,SoftAssert soft) throws InterruptedException {
		/*
		 * This Meathod is to validate Characters and Page Number
		 * Precondition : User should be in Message window with Typed Message
		 */
		try {
			minWait();
			String charAndPageNum1 = charAndPageNum.getText();
			System.out.println(charAndPageNum1);
			if (check=charAndPageNum1.equals(expectedcharAndPageNum)) {
				APP_LOGS.info("Character and Page Number Displayed is correct");
				soft.assertTrue(check, "Validation is Pass");
			} else {
				APP_LOGS.info("Character and Page Number Displayed is NOT correct");
				soft.fail();
			} 
		} catch (Exception e) {
			e.printStackTrace();
			soft.fail();
			test.log(LogStatus.ERROR, "Error in validating the Number of characters and Page Number");
		}
	}	



	public void changeToNumberMode() throws InterruptedException {

		minWait();
		Locators_DeviceStability.toField_NewMessage.sendKeys("123");
		customWait(1500);
		String text = Locators_DeviceStability.toField_NewMessage.getText();
		System.out.println(text);
		if(!text.equals("123")) {
			for (int i = 0; i < 3; i++) {
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_POUND);
				minWait();
			}
		}
		minWait();
		Locators_DeviceStability.toField_NewMessage.clear();
	}

	public void type_New_Message(String cell_No, String typeMessage) throws InterruptedException, IOException {
		/*
		 * This Method is to type New Meassage.
		 */
		try {
			type_Message(typeMessage);
			minWait();
			changeToNumberMode();
			enter_ToField(cell_No);
			minWait();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void enter_ToField(String cellNo) throws InterruptedException {
		SoftAssert sf = new SoftAssert();
		try {
			minWait();
			enterTextToInputField(Locators_DeviceStability.toField_NewMessage, cellNo);
			minWait();
		} catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "Error in entering number to ToField");
		}
	}

	public void type_Message(String typeMessage) throws InterruptedException {
		SoftAssert sf = new SoftAssert();
		try {
			minWait();
			enterTextToInputField(Locators_DeviceStability.type_Message, typeMessage);
		} catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "Error in Typing a Message.");
		}
	}

	public void send_SMS(int n) throws InterruptedException {
		/*	
		 * 	This method is used to SEND SMS
		 * Precondition : User should create new SMS 
		 */
		SoftAssert sf = new SoftAssert();
		try {
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_MENU);
			minWait();
			clickBtn(Locators_DeviceStability.send);
			minWait();

		} catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "Error in Sending the Message. at iteration "+ n);
		}

	}

	public void validate_Sent_SMS(int n,SoftAssert soft) throws InterruptedException {
		/*
		 * This Method is to validate SENT SMS.
		 */

		WebDriverWait wait = new WebDriverWait(aDriver, 40);

		try {
			wait.until(ExpectedConditions.visibilityOf(Locators_DeviceStability.now_Text));
			minWait();
			System.out.println("Validating....");
			if (isElementExist(Locators_DeviceStability.now_Text)) {
				check=true;
				APP_LOGS.info("Message Sent Successfully");
				System.out.println("Message Sent Successfully");
				soft.assertTrue(check, "Validation is Pass");
				test.log(LogStatus.PASS, "Test case status is Passed at iteration : "+ n);
			} else {
				APP_LOGS.info("Message DIDN'T Sent");
				System.out.println("Message DIDN'T Sent");
				test.log(LogStatus.FAIL, "SMS or MMS didn't Sent, Validation Failed at iteration : "+ n);
				soft.fail();
			}
		} catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "SMS or MMS didn't Sent, Validation Failed at iteration : "+ n);
			soft.fail();
		}
	}


	public void sendSMS_fromRefDevice() {
		try {
			System.out.println(r_b_No+" Ref");
			minWait();
			// Below Code To clear Battery PopUp.
			Runtime.getRuntime().exec("adb -s "+r_Id+" shell input tap 777 1083"); 
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

				else if(r_b_No.contains("-15.")){
					minWait();
					System.out.println("IN Android O");
					minWait();
					Runtime.getRuntime().exec("adb -s "+r_Id+" shell input keyevent 3");
					minWait();
					Runtime.getRuntime().exec("adb -s "+r_Id+" shell am force-stop com.verizon.messaging.vzmsgs");
					customWait(4000);
					Runtime.getRuntime().exec("adb -s "+r_Id+" shell am start -a android.intent.action.SENDTO -d sms:"+pryNum+" --es sms_body \"AutomationMessagee\" --ez exit_on_sent true");
					customWait(6000);
					Runtime.getRuntime().exec("adb -s "+r_Id+" shell input tap 948 1769");
					minWait();
					Runtime.getRuntime().exec("adb -s "+r_Id+" shell input tap 948 1022");
					minWait();
				}
			} else if (r_b_No.contains("5SA.")){
				minWait();
				Runtime.getRuntime().exec("adb -s "+r_Id+" shell input keyevent 3");
				minWait();
				Runtime.getRuntime().exec("adb -s "+r_Id+" shell am force-stop com.android.mms");
				minWait();
				Runtime.getRuntime().exec("adb -s "+r_Id+" shell am start -a android.intent.action.SENDTO -d sms:"+pryNum+" --es sms_body \"AutomationMessagee\" --ez exit_on_sent true");
				customWait(2500);
				Runtime.getRuntime().exec("adb -s "+r_Id+" shell input keyevent 66");
				minWait();
			}

			else if(r_b_No.contains("3A.")) {
				System.out.println("Im IN XP3 ");
				minWait();
				System.out.println(r_Id);
				Runtime.getRuntime().exec("adb -s "+r_Id+" shell input keyevent 3");
				minWait();
				Runtime.getRuntime().exec("adb -s "+r_Id+" shell am force-stop com.android.mms");
				customWait(2500);
				Runtime.getRuntime().exec("adb -s "+r_Id+" shell am start -a android.intent.action.SENDTO -d sms:"+pryNum+" --es sms_body \"AutomationMessagee\" --ez exit_on_sent true");
				customWait(2500);
				Runtime.getRuntime().exec("adb -s "+r_Id+" shell input keyevent 66");
				minWait();
			}

		} catch (Exception e) {

		}
	}


	public void validateMsgSent(String str,SoftAssert soft) throws InterruptedException {
		/*
		 * validate sent message to new num and saved contact
		 */

		try {
			minWait();		
			customWait(8000);
			WebDriverWait wait = new WebDriverWait(aDriver, 40);
			wait.until(ExpectedConditions.visibilityOf(Locators_DeviceStability.now_Text));
			minWait();
			if (isElementExist(Locators_DeviceStability.now_Text)) {
				check=true;
				APP_LOGS.info("Message "+str+" Successfully");
				soft.assertTrue(check, "Validation is Pass");
				test.log(LogStatus.PASS,  str+": Test case status is Passed");
			} else {
				APP_LOGS.info("Message DIDN'T "+ str);
				soft.fail();
				test.log(LogStatus.FAIL, "didn't "+str +", Validation Failed ");
			}
		} catch (Exception e) {
			APP_LOGS.info("No Element found.");
			e.printStackTrace();
			test.log(LogStatus.FAIL, "didn't "+str +", Validation Failed ");
			soft.fail();
		}
	}

	public void performCall() throws InterruptedException {
		/*
		 * call save contact
		 */
		try {
			for(int i=1; i<=3;i++) {
				if(isElementExist(Locators_DeviceStability.allow_Permission)) {
					clickBtn(Locators_DeviceStability.allow_Permission);
				}}
			customWait(3000);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
			minWait();
			clickBtn(Locators_DeviceStability.callNum);
			System.out.println("Selected Contact");
			/*minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);*/
			System.out.println("initiated a call");
			minWait();
			customWait(2000);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	public void Call_Orio(String str,int n,String callType,SoftAssert soft) throws IOException, InterruptedException {
		/*
		 * Validate via log String that MO-Voice  call initiated 
		 */
		SoftAssert s1 = new SoftAssert();
		String value = null;
		customWait(10000);
		try {
			for(int j=1;j<=100;j++){
				Process child = null;
				Process version = null;
				version = Runtime.getRuntime().exec("adb -s "+r_Id+" shell getprop ro.build.version.release");
				InputStream lsOut1 = version.getInputStream();
				InputStreamReader r1 = new InputStreamReader(lsOut1);
				BufferedReader in1 = new BufferedReader(r1);
				String  value1=in1.readLine();
				System.out.println(value1);    
				if (r_b_No.contains("8A.")) {

					System.out.println("XP8");
					if(value1.contains("8.1")) {
						child=Runtime.getRuntime().exec("adb -s "+r_Id+" shell service call telecom 29");
					}else {
						child=Runtime.getRuntime().exec("adb -s "+r_Id+" shell service call telecom 27");
					}
				} if (r_b_No.contains("3A.")) {
					System.out.println("XP3");
					child=Runtime.getRuntime().exec("adb -s "+r_Id+" shell service call telecom 30");
				} 

				else if(r_b_No.contains("5SA.")) {
					System.out.println("XP5");


					System.out.println(value1);    

					if(value1.contains("8.1")) {
						child=Runtime.getRuntime().exec("adb -s "+r_Id+" shell service call telecom 30");
					}else {
						child=Runtime.getRuntime().exec("adb -s "+r_Id+" shell service call telecom 28");
					}
				}     
				customWait(10000);			
			}customWait(10000);	
		} catch (Exception e) {
			Thread.sleep(2000);
			Runtime.getRuntime().exec("adb -s "+r_Id+" shell input keyevent 5");
			Thread.sleep(2000);
		}
	}


	public void validateCall_Orio(String str,int n,String callType,SoftAssert soft) throws IOException, InterruptedException {
		/*
		 * Validate via log String that MO-Voice  call initiated 
		 */
		SoftAssert s1 = new SoftAssert();
		String value = null;
		customWait(10000);
		try {
			for(int j=1;j<=100;j++){
				Process child = null;
				Process version = null;
				version = Runtime.getRuntime().exec("adb -s "+r_Id+" shell getprop ro.build.version.release");
				InputStream lsOut1 = version.getInputStream();
				InputStreamReader r1 = new InputStreamReader(lsOut1);
				BufferedReader in1 = new BufferedReader(r1);
				String  value1=in1.readLine();
				System.out.println(value1);    
				if (r_b_No.contains("8A.")) {

					System.out.println("XP8");
					if(value1.contains("8.1")) {
						child=Runtime.getRuntime().exec("adb -s "+r_Id+" shell service call telecom 29");
					}else {
						child=Runtime.getRuntime().exec("adb -s "+r_Id+" shell service call telecom 27");
					}
				} if (r_b_No.contains("3A.")) {
					System.out.println("XP3");
					child=Runtime.getRuntime().exec("adb -s "+r_Id+" shell service call telecom 30");
				} 

				else if(r_b_No.contains("5SA.")) {
					System.out.println("XP5");


					System.out.println(value1);    

					if(value1.contains("8.1")) {
						child=Runtime.getRuntime().exec("adb -s "+r_Id+" shell service call telecom 30");
					}else {
						child=Runtime.getRuntime().exec("adb -s "+r_Id+" shell service call telecom 28");
					}
				}     
				InputStream lsOut = child.getInputStream();
				InputStreamReader r = new InputStreamReader(lsOut);
				BufferedReader in = new BufferedReader(r);
				value=in.readLine();
				customWait(2000);
				System.out.println(value);
				if(value.contains("00000001")) {
					check = true;
					Runtime.getRuntime().exec("adb -s "+r_Id+" shell input keyevent 5");
					test.log(LogStatus.PASS,"MO-Voice call from "+ callType +" is validated at : iteration " + n);
					soft.assertTrue(check, " ");	

					break;
				}

				else {
					test.log(LogStatus.FAIL, "MO-Voice call Failed at : iteration " + n );
					soft.fail();
				}
				customWait(10000);	

			}
			customWait(10000);	
			endcall();

		} catch (Exception e) {
			Thread.sleep(2000);
			Runtime.getRuntime().exec("adb -s "+r_Id+" shell input keyevent 5");
			Thread.sleep(2000);
		}
	}



	public void endcall() throws InterruptedException, IOException {
		/*
		 * disconnect call
		 */
		minWait();
		Runtime.getRuntime().exec("adb -s "+p_Id+" shell input keyevent 6");
		//		aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_ENDCALL);
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
			clickBtn(Locators_DeviceStability.attachOthers);
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
			for(int i=1; i<=3;i++) {
				if(isElementExist(Locators_DeviceStability.allow_Permission)) {
					clickBtn(Locators_DeviceStability.allow_Permission);
				}}
			customWait(4000);
			clickBtn(Locators_DeviceStability.photoCaptureIcon);
			customWait(2000);
			clickBtn(Locators_DeviceStability.OK);
			minWait();
		} catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "Error in adding Picture to MMS");
		}
	}

	public void callHistory_Orio() throws InterruptedException, IOException {
		/*
		 * call save contact
		 */
		WebDriverWait wait = new WebDriverWait(aDriver, 40);

		try {
			wait.until(ExpectedConditions.visibilityOf(Locators_DeviceStability.first_No_In_CallLog));		
			clickBtn(Locators_DeviceStability.first_No_In_CallLog);		
			wait.until(ExpectedConditions.visibilityOf(Locators_DeviceStability.first_No_Info));
			System.out.println("Call is initiated");
			Runtime.getRuntime().exec("adb -s "+p_Id+" shell input keyevent 5");	
			customWait(1000);	
		} catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "Call Details: No such Element found");
		}

	}

	public void selectOptn_Orio(String str, WebElement option) throws InterruptedException{
		/*
		 * Select Wifi option from settings List
		 */
		SoftAssert Sa = new SoftAssert();
		try {
			System.out.println("Printing String Element");
			System.out.println(str);
			customWait(1000);
			ScrollToElement(Locators_DeviceStability.SettingsList, str);
			customWait(2000);
			clickBtn(option);
			minWait();
			minWait();
			if(isElementExist(Locators_DeviceStability.MobileNetwrks)) {
				minWait();
				clickBtn(Locators_DeviceStability.MobileNetwrks);
				APP_LOGS.info("Selected Mobile Networks");
				minWait();				
				enableSwitch(Locators_DeviceStability.enabled_Data,Locators_DeviceStability.disabled_Data,Locators_DeviceStability.switch_widget);
				minWait();
				System.out.println("Data is enabled1");
			}
			else {
				clickBtn(Locators_DeviceStability.dataUsageOptn);
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
				minWait();
				if (!isElementExist(Locators_DeviceStability.cellular_DataON)) {
					minWait();
					clickBtn(Locators_DeviceStability.cellular_DataOFF);
					APP_LOGS.info("Switch is Enabled");                     
					minWait();
				}
				minWait();
				System.out.println("Data is enabled2");
			}
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			APP_LOGS.info("Selected Network & Internet option");
		} catch (Exception e) {
			e.printStackTrace();
			APP_LOGS.info("WIFI : No such Element found");
		}

	}

	public void selectWIFIOptn() throws InterruptedException{
		/*
		 * Select Wifi option from settings List
		 */
		try {
			customWait(1000);
			//			ScrollToElement(Locators_DeviceStability.SettingsList, "Wi-Fi");
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
			customWait(2000);
			//			clickBtn(Locators_DeviceStability.wifi);
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
			minWait();
			System.out.println("Selected WIFI option");
		} catch (Exception e) {
			e.printStackTrace();
			APP_LOGS.info("WIFI : No such Element found");
			test.log(LogStatus.ERROR, "WIFI : No such Element found");
		}
	}

	public void disconnectSSIDifConnected1() throws InterruptedException {
		/*
		 * disconnect wifi[SSID]if IDC wifi connected
		 */
		SoftAssert Sa = new SoftAssert();
		try {
			selectSSIDwifi();
			scrollText("FORGET");
			customWait(4000);
			clickBtn(Locators_DeviceStability.wifi_IDC_ForgetBtn1);
			System.out.println("Disconnected");
			APP_LOGS.info("IDC available secured wifi is disconnected");
			customWait(4000);
		} catch (Exception e) {
			e.printStackTrace();
			APP_LOGS.info("Wi-Fi Forget button: No such Element found");
			test.log(LogStatus.ERROR, "Wi-Fi Forget button: No such Element found");
		}
	}
	
	public void validateDisconnectedWifi(SoftAssert soft,int n) {
		/*
		 * validating Wifi Disconnection
		 */
		try {
			minWait();
			//			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			if(!isElementExist(Locators_DeviceStability.wifi_Connected)) {
				check = true;
				APP_LOGS.info("Connected to Secured Wifi succesfully.");
				test.log(LogStatus.PASS, "Disconnected to Secured Wifi succesfully at iteration: "+n);
				soft.assertTrue(check, "Validation is Pass");
			}
			else {
				APP_LOGS.info("Connected to Secured Wifi is unsuccesful.");	
				test.log(LogStatus.ERROR, "Disconnected to Secured Wifi is unsuccesful at iteration: "+n);
				soft.fail();
			}
		} catch (Exception e) {
			e.printStackTrace();
			APP_LOGS.info("Connected to Secured Wifi is unsuccesful.");	
			test.log(LogStatus.ERROR, "Not Connected Element Not Found at iteration: "+n);
			soft.fail();
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
		catch(NoSuchElementException e) {
			return check;
		}
	}

	public void selectSSIDwifi() throws InterruptedException, IOException {
		/*
		 * Select IDC wifi which is available
		 */
		SoftAssert Sa = new SoftAssert();
		try {

			for(int i=1; i<=5; i++) {
				scrollText("IDCSONWAP");
				if(isElementExist(Locators_DeviceStability.wifi_IDC)) {
					minWait();
					clickBtn(Locators_DeviceStability.wifi_IDC);
					APP_LOGS.info("IDC available secured wifi is Selected");
					System.out.println("IDC available secured wifi is Selected");
					break;
				}
				scrollText("1234567890sonim");
				if(isElementExist(Locators_DeviceStability.wifi_Dellas)) {
					minWait();
					clickBtn(Locators_DeviceStability.wifi_Dellas);
					APP_LOGS.info("Dellas available secured wifi is Selected");
					break;
				}
				scrollText("dlink-F092-media");
				if(isElementExist(Locators_DeviceStability.wifi_Cannada)) {
					minWait();
					clickBtn(Locators_DeviceStability.wifi_Cannada);
					APP_LOGS.info("Cannada available secured wifi is Selected");
					break;
				}
				else {
					for(int i1=1; i1<=5;i1++) {
						minWait();
						aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
					}

//					System.out.println("Finding ....");
//					continue;
				}			
			}			
		} catch (Exception e) {
			e.printStackTrace();
			APP_LOGS.info("WIFI : No such Element found");
			test.log(LogStatus.ERROR, "Unable to find Secured Wifi or Wifi is Not Found");
		}
	}


	public void enterPassword() throws InterruptedException, IOException {
		/*
		 * enter Password for SSID
		 */
		try {
			String getSSIDTitle = Locators_DeviceStability.SSIDTxt.getText();
			System.out.println("Selected Title "+getSSIDTitle);
			minWait();
			clickBtn(Locators_DeviceStability.wifi_show_Pswd);
			minWait();
			changeToNumberModeWifi();
			customWait(10000);
			if(getSSIDTitle.equalsIgnoreCase("IDCSONWAP")) {
				minWait();
				System.out.println("Selected Wifi IDC");
				clickBtn(Locators_DeviceStability.wifi_IDC_Psswd);
				customWait(6000);
				passwordEnter();
				//				Runtime.getRuntime().exec("adb -s "+p_Id+" shell input text 1dcS0n1md0tc0MbLr");		
				customWait(3000);	
			}

			if(getSSIDTitle.equalsIgnoreCase("dlink-F092-media")) {
				minWait();
				clickBtn(Locators_DeviceStability.wifi_IDC_Psswd);
				customWait(6000);
				//			enterTextToInputField(Locators_DeviceStability.wifi_IDC_Psswd,"1dcS0n1md0tc0MbLr");
				Runtime.getRuntime().exec("adb -s "+p_Id+" shell input text rrxfo68837");		
				customWait(3000);	
			}
			if(getSSIDTitle.equalsIgnoreCase("1234567890sonim")) {
				minWait();
				clickBtn(Locators_DeviceStability.wifi_IDC_Psswd);
				customWait(6000);
				//			enterTextToInputField(Locators_DeviceStability.wifi_IDC_Psswd,"1dcS0n1md0tc0MbLr");
				Runtime.getRuntime().exec("adb -s "+p_Id+" shell input text yellowbook143");		
				customWait(3000);	
			}
			customWait(3000);
			String psswrd = Locators_DeviceStability.wifi_IDC_Psswd.getText();
			System.out.println(psswrd);
			customWait(3000);
			clickBtn(Locators_DeviceStability.wifi_IDC_ConnectBtn);
			customWait(3000);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
//			test.log(LogStatus.ERROR, "Unable to find Secured Wifi or Wifi is Not Found");
		}
	}


	public void passwordEnter() throws IOException, InterruptedException {
		System.out.println("Entering Wifi Password");
		minWait();
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

	public void validateWifiConnect_Orio(int n,SoftAssert soft) throws InterruptedException, IOException {
		/*
		 * Validate wifi connection
		 */
		try {
			minWait();
			//			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			customWait(2000);
			String getTxt = Locators_DeviceStability.connectedWIFI1.getText();
			System.out.println(getTxt);
			if(getTxt.contains("IDCSONWAP")||getTxt.contains("Connected")) {
				check = true;
				APP_LOGS.info("Connected to Secured Wifi succesfully.");
				test.log(LogStatus.PASS, "Connected and Disconnected to Secured Wifi succesfully at iteration: "+n);
				soft.assertTrue(check, "Validation is Pass");
			}
			else if (getTxt.contains("1234567890sonim")) {
				check = true;
				APP_LOGS.info("Connected to Secured Wifi succesfully.");
				test.log(LogStatus.PASS, "Connected and Disconnected to Secured Wifi--Dellas succesfully at iteration: "+n);
				soft.assertTrue(check, "Validation is Pass");
			}
			else if (getTxt.contains("dlink-F092-media")) {
				check = true;
				APP_LOGS.info("Connected to Secured Wifi succesfully.");
				test.log(LogStatus.PASS, "Connected and Disconnected to Secured Wifi--Cannada succesfully at iteration: "+n);
				soft.assertTrue(check, "Validation is Pass");
			}
			else{
				APP_LOGS.info("Connected to Secured Wifi is unsuccesful.");	
				test.log(LogStatus.FAIL, "Connected and Disconnected to Secured Wifi is unsuccesful at iteration: "+n);
				soft.fail();
			}
		} catch (Exception e) {
			APP_LOGS.info("No Element found.");
			e.printStackTrace();
			test.log(LogStatus.FAIL, "No Element Found at iteration: "+n);
			//			soft.fail();
		}
	}


	public void changeToNumberModeWifi() throws InterruptedException {

		minWait();
		Locators_DeviceStability.wifi_IDC_Psswd.sendKeys("123");
		customWait(1500);
		String text = Locators_DeviceStability.wifi_IDC_Psswd.getText();
		System.out.println(text);
		if(!text.equals("123")) {
			for (int i = 0; i < 3; i++) {
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_POUND);
				minWait();
			}
		}
		Locators_DeviceStability.wifi_IDC_Psswd.clear();
	}

	public void checkWifiConnected() throws InterruptedException {
		try {
			customWait(4000);
			if(isElementExist(Locators_DeviceStability.connectedWIFI1)) {
				String getTxt = Locators_DeviceStability.connectedWIFI1.getText();
				System.out.println("Wifi"+getTxt);
				test.log(LogStatus.INFO, "Wi-Fi is Connected : will be disconnecting");
				minWait();
//				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
				disconnectSSIDifConnected1();
			}
			else {
				System.out.println("Not Connected");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}




	public void validate_ON_OFF_WiFiFeature(WebElement feature,int n,SoftAssert soft) throws InterruptedException, IOException{
		/*
		 * validate Turn On and OFF wifi toggle button	
		 */
		try {	   


			if(isElementExist(Locators_DeviceStability.enabled)) {
				check1 = true;
				APP_LOGS.info("Enabled");
			}
			customWait(3000);
			disableFeature(Locators_DeviceStability.enabled,Locators_DeviceStability.disabled);
			if(isElementExist(Locators_DeviceStability.disabled)) {
				check2 = true;
				APP_LOGS.info("Disabled ");
			}
			if((check1) && (check2)) {
				check = true;
				APP_LOGS.info(" Turn ON and OFF feature is verified");
				test.log(LogStatus.INFO, "Turn ON and OFF WIFI feature is verified at iteration: "+n);
				soft.assertTrue(check, " ");
			}
			else {
				APP_LOGS.info(" check1"+check1+"check2"+check2);
				test.log(LogStatus.ERROR, "No Such Element Found");
				APP_LOGS.info(" Turn ON and OFF feature is not verified at iteration: "+ n);
				soft.fail();
			}
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
		}catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, " No Such Element Found");
			soft.fail();

		}
	}

	public void enable(WebElement enablebtn, WebElement disablebtn) throws InterruptedException {
		try {
			if (!isElementExist(enablebtn)) {
				minWait();
				clickBtn(disablebtn);
				System.out.println("Enabled feature");
				APP_LOGS.info("Feature is Enabled");                     
				minWait();
			}
			else {
				System.out.println("Unable to enable");
				//				test.log(LogStatus.ERROR, "Toggle button is disabled");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void enterUrl(String newurl) throws InterruptedException, IOException {
		/*
		 * Enter the Website in Url bar
		 */
		SoftAssert Sa = new SoftAssert();
		try {
			minWait();			 
			/*clickBtn(Locators_Stability.DefaultUrlTxt);
			enterTextToInputField(Locators_Stability.DefaultUrlTxt,newurl);*/

			for(int i=1; i<=5;i++) {
				if(isElementExist(Locators_DeviceStability.allow_Permission)) {
					clickBtn(Locators_DeviceStability.allow_Permission);
				}}
			Runtime.getRuntime().exec("cmd /C \"adb -s "+p_Id+" shell am start -a android.intent.action.VIEW -d http:/"+newurl);
			Thread.sleep(2000);
			APP_LOGS.info(" URl is entered is sucessful.");
			customWait(3000);
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_ENTER);
			minWait();
			APP_LOGS.info("Search click is sucessful.");
		} catch (NoSuchElementException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			test.log(LogStatus.ERROR, "Url bar: No Such Element Found");
			//			Sa.fail();			
		} 
	}



	public void validateUrlEntered(int n,SoftAssert soft)
	{
		/*
		 * validate the website page is loaded via adb logs
		 */
		try { 
			customWait(20000);
			if(isElementExist(Locators_DeviceStability.networkNotAvailable)||isElementExist(Locators_DeviceStability.WebPageBlocked)){

				test.log(LogStatus.INFO, "Entered Website page not Loaded successfully at iteration: "+n);
				soft.fail();
			} 

			else {
				check = true;
				APP_LOGS.info("Url entered sucessfully.");
				test.log(LogStatus.INFO, "Entered Website page Loaded Successfully at iteration: "+n);
				soft.assertTrue(check, " ");
			}
		}
		catch (Exception e) {
			APP_LOGS.info("Element not Found");
			e.printStackTrace();
			soft.fail();
		} 
	}

	public void validateUrlEntered_O(String web,int n,int numitr)
	{
		/*
		 * validate the website page is loaded via adb logs
		 */
		SoftAssert S2= new SoftAssert();
		try { 
			customWait(20000);
			customWait(25000);
			if(isElementExist(Locators_DeviceStability.BlockBtn)) {
				clickBtn(Locators_DeviceStability.BlockBtn);
				minWait();
			}
			if(isElementExist(Locators_DeviceStability.networkNotAvailable)||isElementExist(Locators_DeviceStability.WebPageBlocked)){
				test.log(LogStatus.FAIL, "Entered Website page " +  "\""+  web +"\"" + " not Loaded successfully at iteration: "+n);
			} 
			else {
				check = true;
				APP_LOGS.info("Url entered sucessfully.");
				test.log(LogStatus.PASS, "Entered Website page " + "\""+ web +"\"" + " Loaded Successfully at iteration: "+n);
				S2.assertTrue(check, " ");
			}
		}
		catch (Exception e) {
			APP_LOGS.info("Element not Found");
			e.printStackTrace();
		} 
	}

	public void captureVideo_ADD() throws InterruptedException {

		/* Captures the Video and adds to the Message (MMS)
		 * Precondition : User should navigate to "Attach others" Option
		 */

		try {
			customWait(2000);
			clickBtn(Locators_DeviceStability.videoCaptureIcon);
			customWait(10000);
			clickBtn(Locators_DeviceStability.videoCaptureIcon);
			minWait();
			clickBtn(Locators_DeviceStability.OK);
			minWait();
		} catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "Error in adding Video to MMS");
		}		
	}


	public void enableData() throws InterruptedException {
		/*
		 * enable data
		 */
		try {
			launch_an_app("settings");
			minWait();
			clickBtn(Locators_DeviceStability.networkAndInternet);
			minWait();
			if(isElementExist(Locators_DeviceStability.MobileNetwrks_oreo)) {
				minWait();
				clickBtn(Locators_DeviceStability.MobileNetwrks_oreo);
				APP_LOGS.info("Selected Mobile Networks");
				minWait();				
				enableSwitch(Locators_DeviceStability.enabled_Data_Oreo,Locators_DeviceStability.disabled_Data_Oreo,Locators_DeviceStability.switch_widget);
				minWait();
			}
		} catch (Exception e) {
			APP_LOGS.info("No Element found.");
			minWait();	
		}
	}

	public void ON_Switch(String switch_To_ON) {
		try {
			minWait();
			aDriver.findElementByXPath("//*[contains(@text,'"+switch_To_ON+"')]/../..//*[@text='OFF']").click();
			minWait();
		} catch (Exception e) {
		}
	}

	public void OFF_Switch(String switch_To_OFF) {
		try {
			minWait();
			aDriver.findElementByXPath("//*[contains(@text,'"+switch_To_OFF+"')]/../..//*[@text='ON']").click();
			minWait();
		} catch (Exception e) {
		}
	}

	public void wiFi_OFF() {
		try {
			minWait();
			aDriver.findElementByXPath("//*[contains(@text,'Wi')]/../../..//*[@text='ON']").click();
			minWait();
		} catch (Exception e) {
		}
	}

	public void wiFi_ON() {
		try {
			minWait();
			aDriver.findElementByXPath("//*[contains(@text,'Wi')]/../../..//*[@text='OFF']").click();
			minWait();
		} catch (Exception e) {
		}
	}

	public void disableData() throws InterruptedException {
		/*
		 * enable data
		 */
		try {
			//this perform disable WiFi
			launch_an_app("settings");
			minWait();
			selectOptn_Orio("Network & Internet",Locators_DeviceStability.NetworkInternet) ;
			customWait(2000);
			selectWIFIOptn();
			minWait();
			OFF_Switch("Wi");
			customWait(3000);
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			minWait();
			if(isElementExist(Locators_DeviceStability.MobileNetwrks)) {
				minWait();
				clickBtn(Locators_DeviceStability.MobileNetwrks);
				System.out.println("Selected Mobile Networks");
				APP_LOGS.info("Selected Mobile Networks");
				minWait();	
				OFF_Switch("Mobile data");
				clickBtn(Locators_DeviceStability.OK);
				minWait();
			}
			else {
				clickBtn(Locators_DeviceStability.dataUsageOptn);
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
				minWait();
				if (!isElementExist(Locators_DeviceStability.cellular_DataON)) {
					minWait();
					clickBtn(Locators_DeviceStability.cellular_DataOFF);
					APP_LOGS.info("Switch is Enabled");                     
					minWait();
				}
				minWait();
			}
		} catch (Exception e) {
			APP_LOGS.info("No Element found.");
		}
	}


	public void validateBrowseNoInternet(SoftAssert soft, int n,String typ)  {
		/*
		 * Validate while browsing google.com
		 */
		try {		
			if (isElementExist(Locators_DeviceStability.noNetworkTitle)){
				clickBtn(Locators_DeviceStability.cancelButton);
				minWait();
			}
			customWait(3000);
			minWait();	
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_MENU);
			minWait();
			clickBtn(Locators_DeviceStability.URLOption);
			minWait();
			Runtime.getRuntime().exec("adb -s "+p_Id+" shell am start -a android.intent.action.VIEW -d http://www.google.com");
			//			enterTextToInputField(Locators_DeviceStability.UrlTxt, "www.google.com");
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_ENTER);
			customWait(6000);

			if((isElementExist(Locators_DeviceStability.webpageNotavailable))||isElementExist(Locators_DeviceStability.noNetworkTitle)) {
				if (isElementExist(Locators_DeviceStability.noNetworkTitle)){
					clickBtn(Locators_DeviceStability.cancelButton);
					minWait();
				}
				check =true;
				APP_LOGS.info("check: "+check);
				APP_LOGS.info("Disable validation is PASS at iteration: "+n);
				test.log(LogStatus.PASS, typ+" Disable validation is PASS at iteration "+n);
				soft.assertTrue(check, "Validation is Pass");
			}
			else{
				APP_LOGS.info("check: "+check);
				APP_LOGS.info("Disable validation is FAIL at iteration: "+n);
				test.log(LogStatus.FAIL,typ+" Disable validation is FAIL at iteration "+n);
				soft.fail();
			}minWait();	
		} catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "No Such Element Found at iteration "+n);
			soft.fail();
		}
	}


	public void validateBrowseInternet(SoftAssert soft,String typ,int n) throws InterruptedException, IOException {
		/*
		 * Validate while browsing google.com
		 */
		boolean b = false, check = false;
		try {
			customWait(3000);
			if((isElementExist(Locators_DeviceStability.SearchIcon_Google))||(isElementExist(Locators_DeviceStability.Searchenter_Google))) {
				check =true;
				APP_LOGS.info("check: "+check);
				APP_LOGS.info("Enable and URL loaded successfully at iteration : "+n);
				test.log(LogStatus.PASS, typ+" Enable and URL loaded successfully at iteration "+n);
				soft.assertTrue(check, "Validation is Pass");
			}
			else{
				APP_LOGS.info("check: "+check);
				APP_LOGS.info("Enable and URL loaded Unsuccessful at iteration: "+n);
				test.log(LogStatus.FAIL, typ+" Enable and URL loaded Unsuccessful at iteration "+n);
				soft.fail();
			}minWait();	
		} catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "No Such Element Found"+n);
			soft.fail();
		}
	}

	public boolean scrollTo(String text) {
		/*
		  Method used to select an element on the page by scrolling the Scroll View/List View
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

	public void validate_Airplane_Enable(String dailNum,SoftAssert soft,int n) throws InterruptedException, IOException {
		/* 
		 * Method can be Used Validate Airplane Mode activation via by making the call.
		 */
		try {
			minWait();
			if(isElementExist(Locators_DeviceStability.turnOff_Airplane_PopUp)) {
				minWait();
				check=true;
				APP_LOGS.info("VAlidated TurnOff Airplane Mode PopUp Displayed Successfully");
				soft.assertTrue(check, "TestCase Valiation is PASS");
				test.log(LogStatus.PASS, "Enable Airplane Mode is Validated at iteration: "+n);
			} else  {
				minWait();
				APP_LOGS.info("TurnOff Airplane Mode PopUp NOT Displayed");				
				test.log(LogStatus.FAIL,"Enable Airplane Mode is Unsuccessful at iteration: "+n);
				soft.fail();
			}
			minWait();
			clickBtn(Locators_DeviceStability.OK);
			minWait();
			clickBtn(Locators_DeviceStability.CANCEL);
			minWait();
		} catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR,"No Such Element Found at iteration: "+n);
			soft.fail();
		}	
	}

	public void validate_Airplane_Disable(String dailNum,SoftAssert soft,int n) throws InterruptedException, IOException {
		/* 
		 * Method can be Used Validate Airplane Mode activation via by making the call.
		 */
		try {		
			minWait();
			if(!isElementExist(Locators_DeviceStability.turnOff_Airplane_PopUp)) {
				minWait();
				check=true;
				APP_LOGS.info("TurnOff Airplane Mode PopUp NOT Displayed.");
				soft.assertTrue(check, "TestCase Valiation is PASS");
				test.log(LogStatus.PASS, "Disable Airplane Mode is VAlidated at iteration: "+n);
			} else  {
				minWait();
				APP_LOGS.info("TurnOff Airplane Mode PopUp is Displayed");				
				test.log(LogStatus.FAIL,"Disable Airplane Mode is UnSuccessful at iteration: "+n);
				soft.fail();
			}
			minWait();
		} catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR,"No Such Element Found at iteration: "+n);
			soft.fail();
		}	
	}


	public void end_Call() throws InterruptedException {
		/* Method is to end Call.
		 * Precondition : User should initiate the Call to any Number. */
		try {
			minWait();
			if(isElementExist(Locators_DeviceStability.end_Call)) {
				clickBtn(Locators_DeviceStability.end_Call);
				APP_LOGS.info("Call Ended");
			}else {
				APP_LOGS.info("End Call Button Doesn't exist on screen");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void dailCallUsingDailPad(String dailNum) throws InterruptedException, IOException {

		try {
			customWait(4000);
			Runtime.getRuntime().exec("adb -s "+p_Id+" shell am start -a android.intent.action.CALL -d tel:"+refNum);
			Thread.sleep(10000);
			/*if (!isElementExist(Locators_DeviceStability.turnOff_Airplane_PopUp)) {
				try {
					for(int j=1;j<=100;j++){
						Process child = null;
						if (r_b_No.contains("8A.")) {
							child=Runtime.getRuntime().exec("adb -s "+r_Id+" shell service call telecom 27");
						} else if(r_b_No.contains("5SA.")) {
							child=Runtime.getRuntime().exec("adb -s "+r_Id+" shell service call telecom 28");
						}
						InputStream inputStream = child.getInputStream();
						InputStreamReader isr = new InputStreamReader(inputStream);
						BufferedReader bf = new BufferedReader(isr);
						String  value=bf.readLine();

						System.out.println(value);
						if(value.contains("00000001")||value.contains("ffffffff")) {
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
			}*/
			APP_LOGS.info("Number dailed is: "+dailNum);
		}catch (Exception e) {
			APP_LOGS.info("Selected Page is not displayed.");
			e.printStackTrace();
		}
	}

	public void scrollToElement(AndroidElement e) throws InterruptedException{
		minWait();
		WebElement element = e;
		((JavascriptExecutor) aDriver).executeScript("arguments[0].scrollIntoView(true);", element);
		minWait();
	}

	public void validate_BT_Devices(SoftAssert soft, int n) throws InterruptedException {

		try {
			WebDriverWait wait = new WebDriverWait(aDriver, 30);
			wait.until(ExpectedConditions.visibilityOf(Locators_DeviceStability.BT_Pair_new));
			minWait();
			Runtime.getRuntime().exec("adb -s "+r_Id+" shell am start -a android.settings.BLUETOOTH_SETTINGS");
			customWait(6000);
			System.out.println(" Im Searching...BT devices");
			//			scrollToElement(Locators_DeviceStability.BT_Devices);
			boolean check=Locators_DeviceStability.BT_Devices.isDisplayed();
			minWait();
			if (check) {
				check=true;
				soft.assertTrue(check, "TestCase Valiation is PASS");
				test.log(LogStatus.PASS, "Enable Bluethooth is Validated at iteration: "+n);
			} else {
				soft.fail();
				test.log(LogStatus.FAIL, "Enable Bluethooth is UnSuccessful at iteration: "+n);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			test.log(LogStatus.FAIL, "Enable Bluethooth is UnSuccessful at iteration: "+n);
			soft.fail();
		}
	}

	public boolean clickOnText(String text) {
		/*
		  Method used to select an element on the page by scrolling the Scroll View/List View
		 */
		boolean check = false;
		try {  
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_MENU);
			minWait();
			String scrollable = "new UiSelector()";
			String textElement = ".text(\""+ text +"\")";
			aDriver.findElementByAndroidUIAutomator(scrollable+textElement).click();
			APP_LOGS.info("Searched application is found sucessfully : ");
			check = true;
			return check;
		}
		catch(Exception e) {
			return check;
		}
	}

	public void validate_Locators1(AndroidElement e1,SoftAssert soft,int n) throws InterruptedException {
		minWait();
		boolean check1 = false;		
		try {
			check1 = e1.isDisplayed();
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (check1) {
			check=true;
			test.log(LogStatus.PASS, "Disable BT is Validated at iteration: "+n);
			soft.assertTrue(check, "TestCase Valiation is PASS");
		} else {
			test.log(LogStatus.FAIL, "Disable BT is UnSuccessful at iteration: "+n);
			soft.fail();
		}
	}

	public void clickOn_BackBtn() throws InterruptedException {
		try {
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			minWait();
		} catch (NoSuchElementException e) {
			e.printStackTrace();
		}
	}

	public void enable_BT_RefDevice() throws InterruptedException, IOException {

		try {
			minWait();
			Runtime.getRuntime().exec("adb -s "+r_Id+" shell input keyevent 3");
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
			customWait(3000);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void disable_BT_RefDevice() throws InterruptedException, IOException {

		try {
			minWait();
			Runtime.getRuntime().exec("adb -s "+r_Id+" shell input keyevent 3");
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
			customWait(3000);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public void Call_Orio(SoftAssert soft) throws IOException, InterruptedException {
		/*
		 * Validate via log String that MO-Voice  call initiated 
		 */
		String value = null;
		customWait(10000);
		try {
			customWait(4000);
			Runtime.getRuntime().exec("adb -s "+p_Id+" shell am start -a android.intent.action.CALL -d tel:"+refNum);
			Thread.sleep(10000);
			for(int j=1;j<=100;j++){
				Process child = null;
				Process version = null;
				version = Runtime.getRuntime().exec("adb -s "+r_Id+" shell getprop ro.build.version.release");
				InputStream lsOut1 = version.getInputStream();
				InputStreamReader r1 = new InputStreamReader(lsOut1);
				BufferedReader in1 = new BufferedReader(r1);
				String  value1=in1.readLine();
				System.out.println(value1);    
				if (r_b_No.contains("8A.")) {

					System.out.println("XP8");
					if(value1.contains("8.1")) {
						child=Runtime.getRuntime().exec("adb -s "+r_Id+" shell service call telecom 29");
					}else {
						child=Runtime.getRuntime().exec("adb -s "+r_Id+" shell service call telecom 27");
					}
				} if (r_b_No.contains("3A.")) {
					System.out.println("XP3");
					child=Runtime.getRuntime().exec("adb -s "+r_Id+" shell service call telecom 30");
				} 

				else if(r_b_No.contains("5SA.")) {
					System.out.println("XP5");


					System.out.println(value1);    

					if(value1.contains("8.1")) {
						child=Runtime.getRuntime().exec("adb -s "+r_Id+" shell service call telecom 30");
					}else {
						child=Runtime.getRuntime().exec("adb -s "+r_Id+" shell service call telecom 28");
					}
				}     
				InputStream lsOut = child.getInputStream();
				InputStreamReader r = new InputStreamReader(lsOut);
				BufferedReader in = new BufferedReader(r);
				value=in.readLine();
				customWait(2000);
				System.out.println(value);
				if(value.contains("00000001")) {
					check = true;
					Runtime.getRuntime().exec("adb -s "+r_Id+" shell input keyevent 5");
					test.log(LogStatus.PASS,"MO-Voice call is initiated");
					soft.assertTrue(check, " ");	
					break;
				}
			}     
			customWait(10000);	
		} catch (Exception e) {
			Thread.sleep(2000);
			Runtime.getRuntime().exec("adb -s "+r_Id+" shell input keyevent 5");
			Thread.sleep(2000);
		}
	}
	
	public void validateLaunch_All_Applications(SoftAssert soft,int n,String str) throws InterruptedException, IOException {
		//this method launch all applications one by one and validate

		try {
			launch_an_app("phone");
			if(str.equals("phone")) {
				System.out.println("Phone Dailer");
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_ENTER);
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_ENTER);
				validate_launchofApp(Locators_DeviceStability.dialerText, "Phone",soft,n);
			}
			else {
				System.out.println("Browser");
				validate_launchofApp(Locators_DeviceStability.first_No_In_CallLog, "Phone",soft,n);
			}
			launch_an_app("contacts");
			if(isElementExist(Locators_DeviceStability.contact_FindContacts)) {
				customWait(30000);
				validate_launchofApp(Locators_DeviceStability.contact_FindContacts, "Contacts",soft,n);
			}
			else if(isElementExist(Locators_DeviceStability.contacts)) {
				customWait(30000);
				validate_launchofApp(Locators_DeviceStability.contacts, "Contacts",soft,n);
			}

			launch_an_app("messaging");
			customWait(30000);
			validate_launchofApp(Locators_DeviceStability.messaging_Title, "Messaging",soft,n);			

			launch_an_app("camera");
			customWait(30000);
			switchCamera(soft,n);

			launch_an_app("settings");
			customWait(30000);
			validate_launchofApp(Locators_DeviceStability.settingsIcon, "Settings",soft,n);

			/*	launch_an_app("music");
			validate_launchofApp(Locators_DeviceStability.artists_music_player, "Music",soft,n);
			 */
			launch_an_app("soundRecorder");
			customWait(30000);
			validate_launchofApp(Locators_DeviceStability.sound_Recorder, "Sound Recorder",soft,n);

			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);

		} catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR,"No Such Element Found");
		}						
	}
	
	public void validate_launchofApp(WebElement element, String elementName,SoftAssert soft,int n) throws InterruptedException {
		minWait();
		System.out.println("inside validation Method");
		try {
			System.out.println(elementName);
			customWait(6000);
			if (isElementExist(element)) {
				System.out.println(" inside ");
				check = true;
				APP_LOGS.info(elementName + " is found succesfully");
				test.log(LogStatus.PASS, elementName + " Application Successfully Launched at iteration : "+ n);			
				soft.assertTrue(check, "Tc Passed");
			} else {
				check = false;
				APP_LOGS.info(elementName + " is not found");
				test.log(LogStatus.ERROR, elementName + " Application is not Launched at iteration : "+ n);
				soft.fail();
			}
		} catch (Exception e) {
			e.printStackTrace();
			APP_LOGS.info("No Such Element Found");
			test.log(LogStatus.ERROR,"No Such Element Found");
			soft.fail();
		}
	}

	public void switchCamera(SoftAssert soft,int n) throws InterruptedException {
		try {
			if(isElementExist(Locators_DeviceStability.camera_Icon)) {
				validate_launchofApp(Locators_DeviceStability.camera_Icon, "Camera",soft,n);
			}
			else if (isElementExist(Locators_DeviceStability.videocameraIcon)) {
				validate_launchofApp(Locators_DeviceStability.videocameraIcon, "Camera",soft, n);
			}
		} catch (NoSuchElementException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			test.log(LogStatus.ERROR,"No Such Element Found");
		}					
	}

	public void endcall_() throws InterruptedException, IOException {
		/*
		 * disconnect call
		 */
		/*	minWait();
		aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_ENDCALL);
		minWait();	*/

		String value = null;
		customWait(10000);
		try {
			customWait(4000);
			Thread.sleep(10000);
			for(int j=1;j<=100;j++){
				Process child = null;
				Process version = null;
				version = Runtime.getRuntime().exec("adb -s "+r_Id+" shell getprop ro.build.version.release");
				InputStream lsOut1 = version.getInputStream();
				InputStreamReader r1 = new InputStreamReader(lsOut1);
				BufferedReader in1 = new BufferedReader(r1);
				String  value1=in1.readLine();
				System.out.println(value1);    
				if (r_b_No.contains("8A.")) {

					System.out.println("XP8");
					if(value1.contains("8.1")) {
						child=Runtime.getRuntime().exec("adb -s "+r_Id+" shell service call telecom 29");
					}else {
						child=Runtime.getRuntime().exec("adb -s "+r_Id+" shell service call telecom 27");
					}
				} if (r_b_No.contains("3A.")) {
					System.out.println("XP3");
					child=Runtime.getRuntime().exec("adb -s "+r_Id+" shell service call telecom 30");
				} 

				else if(r_b_No.contains("5SA.")) {
					System.out.println("XP5");


					System.out.println(value1);    

					if(value1.contains("8.1")) {
						child=Runtime.getRuntime().exec("adb -s "+r_Id+" shell service call telecom 30");
					}else {
						child=Runtime.getRuntime().exec("adb -s "+r_Id+" shell service call telecom 28");
					}
				}     
				InputStream lsOut = child.getInputStream();
				InputStreamReader r = new InputStreamReader(lsOut);
				BufferedReader in = new BufferedReader(r);
				value=in.readLine();
				customWait(2000);
				System.out.println(value);
				if(value.contains("00000002")) {
					check = true;
					Runtime.getRuntime().exec("adb -s "+r_Id+" shell input keyevent 6");
					test.log(LogStatus.PASS,"MO-Voice call is Terminated");

					break;
				}
			}     
			customWait(10000);	
		} catch (Exception e) {
			Thread.sleep(2000);
			Runtime.getRuntime().exec("adb -s "+r_Id+" shell input keyevent 5");
			Thread.sleep(2000);
		}
	}

	public void loadHomePage() throws InterruptedException {
		try {
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_ENTER);
			minWait();
			minWait();	
			aDriver.pressKeyCode(AndroidKeyCode.MENU);
			minWait();
			minWait();
			clickBtn(Locators_DeviceStability.HomepageOption);
			customWait(6000);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void pressEnter() throws InterruptedException {
		minWait();
		aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_ENTER);
		customWait(5000);
	}

	public void validateloadHomePage(SoftAssert soft) throws InterruptedException {
		try {

			if(isElementExist(Locators_DeviceStability.Homepage)) {
				System.out.println("USC HomePage Validated");
				check = true;
				APP_LOGS.info("HomePage is found succesfully");
				test.log(LogStatus.PASS, "USC HomePage Loaded Successfully");			
				soft.assertTrue(check, "Tc Passed");
			}
			else if((isElementExist(Locators_DeviceStability.SearchIcon_Google))||(isElementExist(Locators_DeviceStability.Searchenter_Google))) {
				check =true;
				APP_LOGS.info("check: "+check);
				APP_LOGS.info("Browser is Launched sucessfully");
				test.log(LogStatus.PASS, " HomePage Loaded Successfully");
				soft.assertTrue(check, "Validation is Pass");
			}
			else {
				check = false;
				APP_LOGS.info(  " is not found");
				test.log(LogStatus.FAIL,  "HomePage is not Loaded ");
				soft.fail();
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void captureVideo() throws InterruptedException {
		//this method record a video

		try {
			for(int i=1; i<=5;i++) {
				if(isElementExist(Locators_DeviceStability.allow_Permission)) {
					clickBtn(Locators_DeviceStability.allow_Permission);
				}}
			for (int i = 1; i <=2; i++) {
				if (isElementExist(Locators_DeviceStability.videoCaptureIcon)) {
					minWait();
					clickBtn(Locators_DeviceStability.videoCaptureIcon);
					break;
				} else {
					aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_UP);
				} 
			}
			customWait(31000);		
			clickBtn(Locators_DeviceStability.videoCaptureIcon);
			minWait();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void capturePicture() throws InterruptedException {
		//this method record a video

		try {	for(int i=1; i<=5;i++) {
			if(isElementExist(Locators_DeviceStability.allow_Permission)) {
				clickBtn(Locators_DeviceStability.allow_Permission);
			}}
			
			for (int i = 1; i <=2; i++) {
				if (isElementExist(Locators_DeviceStability.photoCaptureIcon)) {
					minWait();
					clickBtn(Locators_DeviceStability.photoCaptureIcon);
					break;
				} else {
					aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_UP);
				} 
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



}

