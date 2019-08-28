package com.xp8.util;

import java.awt.AWTException;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.touch.TouchActions;
import org.openqa.selenium.remote.server.handler.interactions.touch.Scroll;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.asserts.IAssert;
import org.testng.asserts.SoftAssert;

import com.graphics.gui.JsonFileReaderAndWriter;
//import com.graphics.gui.JsonFileReaderAndWriter;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import application.AllQA;
import ch.qos.logback.core.util.Duration;
//import application.AllQA;
import freemarker.core.CustomAttribute;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.AndroidKeyCode;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class XP8_Network_Stability_Util_orio extends BaseUtil {

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


	public void deleteContacts() throws InterruptedException {
		try {
			minWait();
			System.out.println("IM in Contacts");
			if (isElementExist(Locators_Network_DeviceStability.no_Contacts)) {
				APP_LOGS.info("No Contact"); 
				System.out.println("No Contact");
				minWait();
			} else {
				clickBtn(Locators_Network_DeviceStability.deleteContactOptn1);
				minWait();
				clickBtn(Locators_Network_DeviceStability.Selection_menu);
				minWait();
				if(isElementExist(Locators_Network_DeviceStability.ALL_Selection_menu)) {
					clickBtn(Locators_Network_DeviceStability.ALL_Selection_menu);
					minWait();
				}
				else {
					minWait();
					clickBtn(Locators_Network_DeviceStability.one_Selection_menu);
				}
				clickBtn(Locators_Network_DeviceStability.OKBtn1);
				minWait();
				clickBtn(Locators_Network_DeviceStability.OKBtn);
				APP_LOGS.info("Contacts Deleted");
				minWait();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			test.log(LogStatus.ERROR, "No Such element Found");
		}
	}

	public void setDefaultAccount_Contact() throws InterruptedException, IOException {

		try {
			clickBtn(Locators_Network_DeviceStability.moreOptions);
			minWait();
			clickBtn(Locators_Network_DeviceStability.settings_Contact);
			minWait();
			clickBtn(Locators_Network_DeviceStability.defaultAccount_NewContacts);
			minWait();
			clickBtn(Locators_Network_DeviceStability.PHONE_RadioBtn);
			customWait(2000);
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			//clickBtn(Locators_Network_DeviceStability_ATT.navigateUp);
			//			Runtime.getRuntime().exec("adb -s "+p_Id+" shell input keyevent 4");
			minWait();
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}


	public void createContact(String name,String num) throws InterruptedException {
		/*
		 * Create contact
		 */
		try {

			minWait();
			Locators_Network_DeviceStability.AddtoContact.click();
			minWait();

			if(isElementExist(Locators_Network_DeviceStability.Choose_Phone)) {
				minWait();
				clickBtn(Locators_Network_DeviceStability.Choose_Phone);
				customWait(2000);
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
			}
			if(isElementExist(Locators_Network_DeviceStability.Contacts_Name)) {
				minWait();
			}

			customWait(4000);
			minWait();
			enterTextToInputField(Locators_Network_DeviceStability.name_NewContact, name);
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			minWait();
			enterTextToInputField(Locators_Network_DeviceStability.phone_NewContact, num);
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			minWait();
			enterTextToInputField(Locators_Network_DeviceStability.email_NewContact, "Sonimtech@gmail.com");
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			minWait();
			clickBtn(Locators_Network_DeviceStability.Save_ConatctIcon1);
			minWait();

			for(int i=1; i<=3; i++) {
				if(isElementExist(Locators_Network_DeviceStability.AllowOptn))
				{
					clickBtn(Locators_Network_DeviceStability.AllowOptn);
				} 
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			test.log(LogStatus.ERROR, "No Such element Found");
		}		
	}


	public void validateContactCreation(int n,SoftAssert soft) throws InterruptedException {
		/*
		 * Validate contact
		 */
		SoftAssert Sa = new SoftAssert();
		try {
			customWait(3000);
			String getcontactNameText = null;
			getcontactNameText=Locators_Network_DeviceStability.ContactTitle.getText();
			System.out.println(getcontactNameText);
			if(getcontactNameText.contains("Test"))
			{	check1 =true;
			APP_LOGS.info("check1: "+check1);
			APP_LOGS.info("Contact Name is: "+getcontactNameText);
			}
			else{
				APP_LOGS.info("check1: "+check1);
				APP_LOGS.info("Test case failed");	
			}

			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
			customWait(1000);
			String getPhoneNumText=Locators_Network_DeviceStability.contact_phnNum.getText();

			String phoneNum = getPhoneNumText.replaceAll("[^a-zA-Z0-9]", "");
			System.out.println(phoneNum);
			minWait();
			String refNumber = refNum.replaceAll("[^a-zA-Z0-9]", "");
			minWait();
			if(phoneNum.contains(refNumber)||phoneNum.equalsIgnoreCase(refNumber))
			{	check2 =true;
			APP_LOGS.info("check2: "+check2);
			APP_LOGS.info("Contact Phone Num is: "+phoneNum);
			}
			else {
				APP_LOGS.info("check2: "+check2);
			}
			

			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
			customWait(1000);
			String getEmailText =null ;
			getEmailText=Locators_Network_DeviceStability.contact_Emailid.getText();
			//			System.out.println(getEmailText);
			if(getEmailText.contains("Sonimtech"))
			{	check3 =true;
			APP_LOGS.info("check3: "+check3);
			APP_LOGS.info("Contact email id is: "+getEmailText);
			}
			else {
				APP_LOGS.info("check3: "+check3);
			}

			if((check1)&&(check2)&&(check3)){
				check=true;				
				APP_LOGS.info("Contact created with Name,phone_Num, Email_ID and Address is saved in phone memory");
				test.log(LogStatus.INFO,"Contact created saved in phone memory at iteration: "+n);
				soft.assertTrue(check, " ");
			}
			else{

				APP_LOGS.info("Test case failed");	
				test.log(LogStatus.ERROR, "Contact creation failed at iteration: "+n);
				soft.fail();
			}
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			//			e.printStackTrace();
			test.log(LogStatus.ERROR, " No Such Element Found");
			soft.fail();	
		}
	}


	public void validateContactCreation_SIM(int n) throws InterruptedException {
		/*
		 * Validate contact
		 */
		SoftAssert Sa = new SoftAssert();
		try {
			customWait(3000);
			String getcontactNameText = null;
			getcontactNameText=Locators_Network_DeviceStability.ContactTitle.getText();
			System.out.println(getcontactNameText);
			if(getcontactNameText.contains("Test"))
			{	check1 =true;
			APP_LOGS.info("check1: "+check1);
			APP_LOGS.info("Contact Name is: "+getcontactNameText);
			}
			else{
				APP_LOGS.info("check1: "+check1);
				APP_LOGS.info("Test case failed");	
			}

			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
			customWait(1000);
			String getPhoneNumText=Locators_Network_DeviceStability.contact_phnNum.getText();

			String phoneNum = getPhoneNumText.replaceAll("[^a-zA-Z0-9]", "");
			System.out.println(phoneNum);
			minWait();
			if(phoneNum.contains(refNum)||phoneNum.equalsIgnoreCase(refNum))
			{	check2 =true;
			APP_LOGS.info("check2: "+check2);
			APP_LOGS.info("Contact Phone Num is: "+phoneNum);
			}
			else {
				APP_LOGS.info("check2: "+check2);
			}

			if((check1)&&(check2)){
				check=true;				
				APP_LOGS.info("Contact created with Name,phone_Num, Email_ID and Address is saved in SIM memory");
				test.log(LogStatus.INFO,"Contact created saved in SIM memory at iteration: "+n);
			}
			else{

				APP_LOGS.info("Test case failed");	
				test.log(LogStatus.ERROR, "Contact creation failed at iteration: "+n);
			}
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			//			e.printStackTrace();
			test.log(LogStatus.ERROR, " No Such Element Found");

		}
	}

	public void create_NewSMS_O(String number, String message) throws InterruptedException {
		/* Method used to create New SMS. */

		try {
			enter_Num_ToField_O(number);
			enterText_MessageField_O(message);
		} catch (Exception e) {			 
			e.printStackTrace();
		}
	}

	public void enterText_MessageField_O(String message) throws InterruptedException {
		/* Methos is Used enter Message into Message Field. */
		try {
			minWait();
			if(isElementExist(Locators_Network_DeviceStability.messageField_O)) {
				enterTextToInputField(Locators_Network_DeviceStability.messageField_O, message);
				minWait();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void navigateTo_NewSMS_O() throws InterruptedException {
		/* Used to Navigate to New Message Page. */
		try {
		
			clickBtn(Locators_Network_DeviceStability.add_NewSMS_O);
			minWait();
		} catch (Exception e) {		 
			e.printStackTrace();
		}
	}

	public void enter_Num_ToField_O(String number) throws InterruptedException {
		/* Method is used to Enter Number into TO Field. */
		try {
			minWait();
			enterTextToInputField(Locators_Network_DeviceStability.TO_Field_O, number);
			customWait(2000);
			System.out.println("Selecting COntact NAme");
			clickBtn(Locators_Network_DeviceStability.contact_Select);
			//			customWait(2000);			
		} catch (NoSuchElementException e) {			 
			e.printStackTrace();
		}
	}


	public void clickOn_Send_O() throws InterruptedException { 
		/* Method used to click on Send Button. */
		try {
			customWait(500);
			clickBtn(Locators_Network_DeviceStability.send_Icon_O);
			minWait();
		} catch (NoSuchElementException e) {
			e.printStackTrace();
		}
	}

	public void validate_SentMessage_O(int n,SoftAssert soft) throws InterruptedException {
		/* To validate the Sent Message. */
		WebDriverWait wait  =new WebDriverWait(aDriver, 60);

		try {
			wait.until(ExpectedConditions.visibilityOf(Locators_Network_DeviceStability.Delivered));
			if(isElementExist(Locators_Network_DeviceStability.Delivered)||isElementExist(Locators_Network_DeviceStability.not_Sent_Text)) {
				check=true;
				APP_LOGS.info("Message sent Succeccfully");
				System.out.println("Sent");
				soft.assertTrue(check, "TestCase Valiation is PASS");
				test.log(LogStatus.PASS, "SMS Sent Successfully at iteration "+n);
			} else {
				APP_LOGS.info("SMS didn't sent");
				soft.fail();
				test.log(LogStatus.FAIL,"Message didn't sent at iteration "+n);
			}
		} catch (Exception e) {			 
			e.printStackTrace();
			soft.fail();
		}
	}

	public void delete_SMS_O() throws InterruptedException {
		/* This Method delete the First Thread in the List. */

		try {
			minWait();
			clickBtn(Locators_Network_DeviceStability.firstSMS_InList_O);
			minWait();
			clickBtn(Locators_Network_DeviceStability.moreOption_O);
			minWait();
			clickBtn(Locators_Network_DeviceStability.delete_Messages);
			minWait();
			clickBtn(Locators_Network_DeviceStability.ALL_Msg);
			minWait();
			clickBtn(Locators_Network_DeviceStability.delete_Btn);
			minWait();
			clickBtn(Locators_Network_DeviceStability.Delete);
			minWait();
			//			test.log(LogStatus.INFO, "SMS or MMS is deleted.");
		} catch (Exception e) {			 
			e.printStackTrace();
		}
	}

	public void performAction_O() throws IOException, InterruptedException {
		/*
		 * Perform action for IMS registered check
		 */
		try {
			customWait(2000);
			Runtime.getRuntime().exec("adb -s "+p_Id+" shell am start -a android.settings.AIRPLANE_MODE_SETTINGS");
			minWait();
			for(int i=1; i<=9; i++) {
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
			}
			enableSwitch(Locators_Network_DeviceStability.enabled_Airplane,Locators_Network_DeviceStability.disabled_Airplane,Locators_Network_DeviceStability.switch_widget);
			customWait(5000);
			clickBtn(Locators_Network_DeviceStability.OKBtn);
			minWait();
			disableSwitch(Locators_Network_DeviceStability.disabled_Airplane,Locators_Network_DeviceStability.enabled_Airplane,Locators_Network_DeviceStability.switch_widget );		
			customWait(15000);
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void sendSMS_fromRefDevice(String AutomationMessagee) {

		// To validate MT Message User should be inside Messaging APP of Primary Device.
		try {
			
			// Below Code To clear Battery PopUp.
			//			Runtime.getRuntime().exec("adb -s "+r_Id+" shell input tap 777 1083"); 
			minWait();
			if (r_b_No.contains("8A.")) {
				if (r_b_No.contains("-10.")||r_b_No.contains("-30.")) {
					
					System.out.println("SSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSS");
					
					Runtime.getRuntime().exec("adb -s "+r_Id+" shell service call isms 7 i32 0 s16 \"com.android.mms.service\" s16 "+pryNum+" s16 \"null\" s16 \"SMAT\" s16 \"null\" s16 \"null\"");
					
					/*minWait();
					Runtime.getRuntime().exec("adb -s "+r_Id+" shell input keyevent 3");
					minWait();
					Runtime.getRuntime().exec("adb -s "+r_Id+" shell am force-stop com.android.mms");
					customWait(2000);
					Runtime.getRuntime().exec("adb -s "+r_Id+" shell am start -a android.intent.action.SENDTO -d sms:"+pryNum+" --es sms_body \""+AutomationMessagee+"\" --ez exit_on_sent true");
					customWait(6000);
					Runtime.getRuntime().exec("adb -s "+r_Id+" shell input tap 540 1776");
					minWait();
					Runtime.getRuntime().exec("adb -s "+r_Id+" shell input tap 713 1098");
					minWait();
					Runtime.getRuntime().exec("adb -s "+r_Id+" shell input tap 918 1699");
					minWait();
					Runtime.getRuntime().exec("adb -s "+r_Id+" shell input tap 918 952");
					minWait();*/
				} else if(r_b_No.contains("-11.")||r_b_No.contains("-12.")||r_b_No.contains("-18.")||r_b_No.contains("-26.")||r_b_No.contains("-29.")){
					/*minWait();
					Runtime.getRuntime().exec("adb -s "+r_Id+" shell input keyevent 3");
					minWait();
					Runtime.getRuntime().exec("adb -s "+r_Id+" shell am force-stop com.google.android.apps.messaging");
					customWait(2000);
					Runtime.getRuntime().exec("adb -s "+r_Id+" shell am start -a android.intent.action.SENDTO -d sms:"+pryNum+" --es sms_body \""+AutomationMessagee+"\" --ez exit_on_sent true");
					customWait(6000);
					Runtime.getRuntime().exec("adb -s "+r_Id+" shell input tap 930 1756");
					minWait();
					Runtime.getRuntime().exec("adb -s "+r_Id+" shell input tap 930 976");
					minWait();*/
					Runtime.getRuntime().exec("adb -s "+r_Id+" shell service call isms 7 i32 0 s16 \"com.android.mms.service\" s16 "+pryNum+" s16 \"null\" s16 \"SMAT\" s16 \"null\" s16 \"null\"");

				}else if(r_b_No.contains("-15.")){
					minWait();
					/*System.out.println("IN Android O");
					minWait();
					Runtime.getRuntime().exec("adb -s "+r_Id+" shell input keyevent 3");
					minWait();
					Runtime.getRuntime().exec("adb -s "+r_Id+" shell am force-stop com.verizon.messaging.vzmsgs");
					customWait(4000);
					Runtime.getRuntime().exec("adb -s "+r_Id+" shell am start -a android.intent.action.SENDTO -d sms:"+pryNum+" --es sms_body \""+AutomationMessagee+"\" --ez exit_on_sent true");
					customWait(6000);
					System.out.println("Sending Message...");
					Runtime.getRuntime().exec("adb -s "+r_Id+" shell input tap 948 1769");
					minWait();
					Runtime.getRuntime().exec("adb -s "+r_Id+" shell input tap 948 1022");
					minWait();*/
					Runtime.getRuntime().exec("adb -s "+r_Id+" shell service call isms 7 i32 0 s16 \"com.android.mms.service\" s16 "+pryNum+" s16 \"null\" s16 \"SMAT\" s16 \"null\" s16 \"null\"");

				}
			} else if (r_b_No.contains("5SA.")){
				minWait();
				/*Runtime.getRuntime().exec("adb -s "+r_Id+" shell input keyevent 3");
				minWait();
				Runtime.getRuntime().exec("adb -s "+r_Id+" shell am force-stop com.android.mms");
				customWait(2000);
				Runtime.getRuntime().exec("adb -s "+r_Id+" shell am start -a android.intent.action.SENDTO -d sms:"+pryNum+" --es sms_body \""+AutomationMessagee+"\" --ez exit_on_sent true");
				customWait(6000);
				Runtime.getRuntime().exec("adb -s "+r_Id+" shell input keyevent 66");
				minWait();*/
				Runtime.getRuntime().exec("adb -s "+r_Id+" shell service call isms 7 i32 0 s16 \"com.android.mms.service\" s16 "+pryNum+" s16 \"null\" s16 \"SMAT\" s16 \"null\" s16 \"null\"");

			}
		} catch (Exception e) {

		}
	}

	public void validate_RecievedMessage(int n,SoftAssert soft) throws InterruptedException {
		/* User should navigate to the Messaging APP home Page in the Primary device. */
		WebDriverWait wait  =new WebDriverWait(aDriver, 80);

		try {
			wait.until(ExpectedConditions.visibilityOf(Locators_Network_DeviceStability.now_Text));	
			customWait(8000);
			if(isElementExist(Locators_Network_DeviceStability.now_Text)||isElementExist(Locators_Network_DeviceStability.not_Sent_Text)) {
				check=true;
				APP_LOGS.info("Message sent Succeccfully");
				soft.assertTrue(check, "TestCase Valiation is PASS");
				test.log(LogStatus.PASS, "Message Recieved Successfully at iteration : "+ n);
			} else {
				APP_LOGS.info("SMS didn't sent");
				test.log(LogStatus.FAIL, "Message didn't Recieved at iteration : "+ n);
				soft.fail();
			}
		} catch (Exception e) {			 
			e.printStackTrace();
			soft.fail();
		}
	}


	public void validate_RecievedMessage_O(int n,SoftAssert soft) throws InterruptedException {
		/* User should navigate to the Messaging APP home Page in the Primary device. */

		WebDriverWait wait  =new WebDriverWait(aDriver, 80);

		try {
			wait.until(ExpectedConditions.visibilityOf(Locators_Network_DeviceStability.Delivered));
			customWait(8000);
			clickBtn(Locators_Network_DeviceStability.firstSMS_InList_O);
			if(isElementExist(Locators_Network_DeviceStability.now_Text)||isElementExist(Locators_Network_DeviceStability.unread_Conv_Messages)||isElementExist(Locators_Network_DeviceStability.Delivered)||isElementExist(Locators_Network_DeviceStability.not_Sent_Text)) {
				check=true;
				APP_LOGS.info("Message sent Succeccfully");
				System.out.println("Received");
				soft.assertTrue(check, "TestCase Valiation is PASS");
				test.log(LogStatus.PASS, "Message Recieved Successfully at iteration "+ n);
			} else {
				APP_LOGS.info("SMS didn't sent");	
				test.log(LogStatus.FAIL,"Message didn't Recieved at iteration "+n);
				soft.fail();
			}
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
		} catch (Exception e) {
			e.printStackTrace();	
			test.log(LogStatus.FAIL,"Expected condition failed: waiting for visibility of element located at iteration "+n);
			soft.fail();
		}
	}

	public void endcall() throws InterruptedException {
		/*
		 * disconnect call
		 */
		minWait();
		aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_ENDCALL);
		minWait();	
	}

	public void searchContact(String name) throws InterruptedException {
		/*
		 * Search contact 
		 */
		try {
			minWait();
			clickBtn(Locators_Network_DeviceStability.Search_ConatctIcon);
			customWait(2000);
			enterTextToInputField(Locators_Network_DeviceStability.findContacts_O, name);
			minWait();
			clickBtn(Locators_Network_DeviceStability.longpress_FirstContact_O);
			minWait();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void initiateCall() throws InterruptedException {
		/*
		 * initiate a call from Saved contacts
		 */
		try {
			/*minWait();
			clickBtn(Locators_Network_DeviceStability.SavedContact);
			minWait();
			clickBtn(Locators_Network_DeviceStability.contact_phnNum);
			minWait();*/
			customWait(4000);
			Runtime.getRuntime().exec("adb -s "+p_Id+" shell am start -a android.intent.action.CALL -d tel:"+refNum);
			Thread.sleep(10000);
		} catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, " No Such Element Found");
		}		
	}

	public void validateCallLog(String str,int n,String callType,SoftAssert soft) throws InterruptedException, IOException {
		/*
		 * Validate via log String that MO-Voice  call initiated 
		 */
		SoftAssert s1 = new SoftAssert();
		String value = null;
		customWait(10000);
		try {

			for(int j=1;j<=100;j++){
				Process child = Runtime.getRuntime().exec("adb -s "+r_Id+" shell service call telecom 27" );
				InputStream lsOut = child.getInputStream();
				InputStreamReader r = new InputStreamReader(lsOut);
				BufferedReader in = new BufferedReader(r);
				value=in.readLine();
				System.out.println(value);
				customWait(2000);
				if(value.contains("00000001")) {
					Runtime.getRuntime().exec("adb -s "+r_Id+" shell input keyevent 5");
					test.log(LogStatus.PASS,"MO-Voice call from "+ callType +" is validated at : iteration " + n);
					soft.assertTrue(check, " ");		
					break;
				}
				/*	else if(value.contains("00000000")){			
				continue;				
			}*/
				else {
					test.log(LogStatus.FAIL, "MO-Voice call Failed at : iteration " + n );
					soft.fail();
				}

			}
			customWait(6000);
			endcall();
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
		} catch (Exception e) {
			Thread.sleep(2000);
			Runtime.getRuntime().exec("adb -s "+r_Id+" shell input keyevent 5");
			Thread.sleep(2000);
		}
	}


	public void validateCallLog_Orio(String str,int n,String callType,SoftAssert soft) throws InterruptedException, IOException {
		/*
		 * Validate via log String that MO-Voice  call initiated 
		 */

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
			customWait(2000);
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			minWait();
		} catch (Exception e) {
			Thread.sleep(2000);
			Runtime.getRuntime().exec("adb -s "+r_Id+" shell input keyevent 5");
			Thread.sleep(2000);
		}
	}

	public void selectPage(WebElement selectpage) throws InterruptedException {
		try {
			minWait();	
			clickBtn(selectpage);
		}
		catch(Exception e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, " No Such Element Found");
		}
	}

	public void callHistory() throws InterruptedException, IOException {
		/*
		 * call save contact
		 */
		try {
			minWait();
			if(isElementExist(Locators_Network_DeviceStability.MoreOptions)) {
				clickBtn(Locators_Network_DeviceStability.MoreOptions);
				minWait();		
			}
			else {
				clickBtn(Locators_Network_DeviceStability.MoreOptions1);
				minWait();		
			}
			customWait(1000);
			clickBtn(Locators_Network_DeviceStability.callHistory_O);
			minWait();			
			clickBtn(Locators_Network_DeviceStability.Call_Contact);
			minWait();
			APP_LOGS.info("initiated a call");
			minWait();
			customWait(2000);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void performAction() throws IOException, InterruptedException {
		/*
		 * Perform action for IMS registered check
		 */
		try {
			customWait(2000);
			Runtime.getRuntime().exec("adb -s "+p_Id+" shell am start -a android.settings.AIRPLANE_MODE_SETTINGS");
			minWait();
			enableSwitch(Locators_Network_DeviceStability.enabled_Airplane,Locators_Network_DeviceStability.disabled_Airplane,Locators_Network_DeviceStability.switch_widget);
			customWait(5000);
			disableSwitch(Locators_Network_DeviceStability.disabled_Airplane,Locators_Network_DeviceStability.enabled_Airplane,Locators_Network_DeviceStability.switch_widget );		
			customWait(15000);
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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

	public boolean validateCSFB(String str,String fileName) throws IOException, InterruptedException {
		/*
		 * Validate via log String that MO-Voice  call initiated 
		 */

		customWait(4000);
		if(searchString(str, fileName)) {
			check = false;
			test.log(LogStatus.SKIP, "IMS Registered SIM is available" );		
		}
		else {		
			check = true;
			test.log(LogStatus.INFO, "Non-IMS registered validated");
			//		    assertTrue(check);
		}
		return check;
	}


	public boolean validateIMSLog(String str,String fileName) throws IOException, InterruptedException {
		/*
		 * Validate via log String that MO-Voice  call initiated 
		 */
		SoftAssert s1 = new SoftAssert();

		if(searchString(str, fileName)) {
			check = true;
			test.log(LogStatus.INFO, "IMS is Enable");
			//			s1.assertTrue(check, " ");
		}
		else {
			check = false;
			test.log(LogStatus.SKIP, "IMS is not enabled" );

		}
		return check;
	}

	public void validateIMSLogSMS(String str,String fileName,String callType) throws IOException, InterruptedException {
		/*
		 * Validate via log String that MO-Voice  call initiated 
		 */
		SoftAssert s1 = new SoftAssert();

		if(searchString(str, fileName)) {
			check = true;
			test.log(LogStatus.INFO, "SMS over IMS registered from "+ callType +" is validated ");
			s1.assertTrue(check, " ");
		}
		else {
			test.log(LogStatus.SKIP, "IMS Registered SIM not available " );
		}
	}



	//This method will take adb log
	public static void startLog(String fileName) throws AWTException, InterruptedException {

		customWait(2000);
		try {
			APP_LOGS.info("Adb log started sucessfully ");
			Runtime.getRuntime().exec("cmd /C \"adb logcat -b all -v threadtime>src/test/resources/adbLogs/"+fileName+".txt \"");
			Thread.sleep(2000);
		}
		catch(Exception e) {
			System.out.println("Something goes Wrong!!!");  
			e.printStackTrace();  
		}
	}


	public void selectWIFIOptn() throws InterruptedException{
		/*
		 * Select Wifi option from settings List
		 */
		SoftAssert Sa = new SoftAssert();
		try {
			customWait(1000);
			//					ScrollToElement(Locators_Network_DeviceStability.SettingsList, "Wi-Fi");
			customWait(2000);
			clickBtn(Locators_Network_DeviceStability.wifi);
			minWait();
			APP_LOGS.info("Selected WIFI option");
		} catch (Exception e) {
			e.printStackTrace();
			APP_LOGS.info("WIFI : No such Element found");
			test.log(LogStatus.ERROR, "Wi-Fi No such Element found");
		}
	}
	public void select_Wifi_SSID(String ssid) {
		/*
		 * Select IDC wifi which is available
		 */
		try{
				boolean ssidAvailable=false;
				ssidAvailable=scrollToText(ssid);
				while(ssidAvailable==false) {
					ssidAvailable=scrollToText(ssid);
			}
			if(ssidAvailable)
			{	
				APP_LOGS.info(" Wifi network available and selected ");
			}
		}catch (NoSuchElementException ne) {
			test.log(LogStatus.ERROR,"Error in the locators => select_Wifi_SSID()");
			ne.printStackTrace();
		}catch (Exception en) {
			test.log(LogStatus.ERROR,"Exception in functionality=> select_Wifi_SSID()");
			en.printStackTrace();
		}
		
			} 
	


	public void disconnectSSIDifConnected(String ssid) throws InterruptedException {
		/*
		 * disconnect wifi[SSID]if IDC wifi connected
		 */
		SoftAssert Sa = new SoftAssert();
		try {
			select_Wifi_SSID(ssid);
			customWait(4000);
			clickBtn(Locators_Network_DeviceStability.wifi_IDC_ForgetBtn);
			APP_LOGS.info("IDC available secured wifi is disconnected");
			System.out.println("Disconnected");
			customWait(4000);
		} catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "Wi-Fi Forget button: No such Element found");		
		}
	}


	public void validate_And_BrowseIn_Chrome1(String url, AndroidElement autoSuggestion, AndroidElement expectedElement, AndroidElement expectedElement1) throws InterruptedException, IOException {
		/* 
		 * Validates MobileData enabality by launching the Chrome & browsing google page.
		 */
		SoftAssert  sf = new SoftAssert();
		try {
			customWait(2000);
			enterTextToInputField(Locators_Network_DeviceStability.urlBar_Chrome, url);
			customWait(4000);
			clickBtn(autoSuggestion);
			customWait(12000);
			try {
				if(expectedElement.isDisplayed()) {
					check=true;
					APP_LOGS.info("Suggeted URL Page Displayed Succeessfully");
					sf.assertTrue(check, "TestCase Valiation is PASS");
					test.log(LogStatus.PASS, "Test case Status is Pass.");
					test.log(LogStatus.INFO, "URL loaded successfully : "+url);
				} else {
					APP_LOGS.info("Suggeted URL page didn't get Loaded");
					sf.fail();
					test.log(LogStatus.FAIL,"Test Case Status is Fail.");
					test.log(LogStatus.INFO, "URL didn't load.");
				}
			} catch (Exception e) {
				if(expectedElement1.isDisplayed()) {
					check=true;
					APP_LOGS.info("Suggeted URL Page Displayed Succeessfully");
					sf.assertTrue(check, "TestCase Valiation is PASS");
					test.log(LogStatus.PASS, "Test case Status is Pass.");
					test.log(LogStatus.INFO, "URL loaded successfully : "+url);
				} else {
					APP_LOGS.info("Suggeted URL page didn't get Loaded");
					sf.fail();
					test.log(LogStatus.FAIL,"Test Case Status is Fail.");
					test.log(LogStatus.INFO, "URL didn't load.");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		//		closeAllTabs();	
	}

	public void selectRefresh() throws InterruptedException {
		/*
		 * Refresh the List SSID 
		 */		
		try {
			minWait();
			//				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_MENU);
			clickBtn(Locators_Network_DeviceStability.MoreOptions);
			minWait();
			clickBtn(Locators_Network_DeviceStability.RefreshOptn);
			minWait();
			//refresh btn
			customWait(5000);
		} catch (Exception e) {
			e.printStackTrace();
			APP_LOGS.info("Wi-Fi Forget button: No such Element found");
			test.log(LogStatus.ERROR, "Wi-Fi : No such Element found");
		}		
	}

	/*public void enterPassword() throws InterruptedException, IOException {
		
		 * enter Password for SSID
		 
		try {
			minWait();
			if(isElementExist(Locators_Network_DeviceStability.SSIDTxt)) {
				String getSSIDTitle = Locators_Network_DeviceStability.SSIDTxt.getText();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
				minWait();
				changeToNumberMode();
				customWait(2000);
				if(getSSIDTitle.equalsIgnoreCase("IDCSONWAP")) {
					minWait();
					clickBtn(Locators_Network_DeviceStability.wifi_IDC_Psswd);
					customWait(4000);
					//					enterTextToInputField(Locators_Network_DeviceStability.wifi_IDC_Psswd,"1dcS0n1md0tc0MbLr");
					Runtime.getRuntime().exec("adb -s "+p_Id+" shell input text 1dcS0n1md0tc0MbLr");		
					customWait(3000);	
				}
				minWait();
				String psswrd = Locators_Network_DeviceStability.wifi_IDC_Psswd.getText();
				System.out.println(psswrd);
				customWait(1000);
				clickBtn(Locators_Network_DeviceStability.wifi_IDC_ConnectBtn);
				customWait(3000);
			}
		} catch (NoSuchElementException ne) {
			test.log(LogStatus.ERROR,"Error in the locators => enterPassword()");
			ne.printStackTrace();
		}catch (Exception en) {
			test.log(LogStatus.ERROR,"Exception in functionality=> enterPassword()");
			en.printStackTrace();
		}
	}*/
	public void enterPassword(String ssid,String pwd) throws InterruptedException, IOException {
		/*
		 * enter Password for SSID
		 */
		try {
			minWait();
			if(isElementExist(Locators_Network_DeviceStability.SSIDTxt)) {
				String getSSIDTitle = Locators_Network_DeviceStability.SSIDTxt.getText();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
				minWait();
				changeToNumberMode();
				customWait(2000);
				if(getSSIDTitle.equalsIgnoreCase(ssid)) {
					minWait();
					clickBtn(Locators_Network_DeviceStability.wifi_IDC_Psswd);
					customWait(4000);
					//					enterTextToInputField(Locators_Network_DeviceStability.wifi_IDC_Psswd,"1dcS0n1md0tc0MbLr");
					Runtime.getRuntime().exec("adb -s "+p_Id+" shell input text "+pwd);		
					customWait(3000);	
				}
				minWait();
				String psswrd = Locators_Network_DeviceStability.wifi_IDC_Psswd.getText();
				System.out.println(psswrd);
				customWait(1000);
				clickBtn(Locators_Network_DeviceStability.wifi_IDC_ConnectBtn);
				customWait(3000);
			}
		} catch (NoSuchElementException ne) {
			test.log(LogStatus.ERROR,"Error in the locators => enterPassword()");
			ne.printStackTrace();
		}catch (Exception en) {
			test.log(LogStatus.ERROR,"Exception in functionality=> enterPassword()");
			en.printStackTrace();
		}
	}

	public void changeToNumberMode() throws InterruptedException {

		try {
			minWait();
			Locators_Network_DeviceStability.wifi_IDC_Psswd.sendKeys("123");
			customWait(1500);
			String text = Locators_Network_DeviceStability.wifi_IDC_Psswd.getText();
			System.out.println(text);
			if(!text.equals("123")) {
				for (int i = 0; i < 3; i++) {
					aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_POUND);
					minWait();
				}
			}
			Locators_Network_DeviceStability.wifi_IDC_Psswd.clear();
		} catch (Exception e) {
			e.printStackTrace();
			APP_LOGS.info("Wi-Fi Forget button: No such Element found");
		}
	}

	public void changeToNumberModeSMS() throws InterruptedException {

		try {
			minWait();
			Locators_Network_DeviceStability.wifi_IDC_Psswd.sendKeys("123");
			customWait(1500);
			String text = Locators_Network_DeviceStability.wifi_IDC_Psswd.getText();
			System.out.println(text);
			if(!text.equals("123")) {
				for (int i = 0; i < 3; i++) {
					aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_POUND);
					minWait();
				}
			}
			Locators_Network_DeviceStability.wifi_IDC_Psswd.clear();
		} catch (Exception e) {
			e.printStackTrace();
			APP_LOGS.info("Wi-Fi Forget button: No such Element found");
			test.log(LogStatus.ERROR, " No such Element found");
		}
	}

	public void validateWifiConnect(int n,SoftAssert soft) throws InterruptedException, IOException {
		/*
		 * Validate wifi connection
		 */
		SoftAssert sf = new SoftAssert();
		try {
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			minWait();
			String getTxt = Locators_Network_DeviceStability.connectedWIFI1.getText();
			System.out.println(getTxt);
			if(getTxt.contains("IDCSONWAP")) {
				check = true;
				APP_LOGS.info("Connected to Secured Wifi succesfully.");
				System.out.println("Connected to Secured Wifi succesfully.");
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
			test.log(LogStatus.FAIL, "Secured Wifi is unsuccesful at iteration: "+n);
			soft.fail();
		}
	}


	public void validate_ON_OFF_WiFiFeature(WebElement feature,int n) throws InterruptedException, IOException{
		/*
		 * validate Turn On and OFF wifi toggle button	
		 */
		SoftAssert S1 = new SoftAssert();
		try {	   
			customWait(2000);
			enableFeature(Locators_Network_DeviceStability.enabled,Locators_Network_DeviceStability.disabled,Locators_Network_DeviceStability.switch_Title);
			minWait();

			if(isElementExist(Locators_Network_DeviceStability.enabled)) {
				check1 = true;
				APP_LOGS.info(" check1");
			}
			customWait(3000);
			disableFeature(Locators_Network_DeviceStability.enabled,Locators_Network_DeviceStability.disabled);
			if(isElementExist(Locators_Network_DeviceStability.disabled)) {
				check2 = true;
				APP_LOGS.info(" ");
			}
			if((check1) && (check2)) {
				check = true;
				APP_LOGS.info(" Turn ON and OFF feature is verified");
				test.log(LogStatus.PASS, "Turn ON and OFF WIFI feature is verified at iteration: "+n);
				S1.assertTrue(check, " ");
			}
			else {
				APP_LOGS.info(" check1"+check1+"check2"+check2);
				test.log(LogStatus.FAIL, "No Such Element Found");
				APP_LOGS.info(" Turn ON and OFF feature is not verified at iteration: "+ n);
				S1.fail();
			}
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
		}catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.FAIL, " No Such Element Found");

		}

	}

	public void acceptDefault() throws InterruptedException {
		/*
		 * Accept default Options
		 */

		try {
			customWait(2000);
			if(isElementExist(Locators_Network_DeviceStability.AccptBtn)){
				Locators_Network_DeviceStability.AccptBtn.click();
			}
			customWait(4000);
			if(isElementExist(Locators_Network_DeviceStability.NextIcon)){
				Locators_Network_DeviceStability.NextIcon.click();
			}
			customWait(4000);
			if(isElementExist(Locators_Network_DeviceStability.NothanksBtn)){
				Locators_Network_DeviceStability.NothanksBtn.click();
			}
			customWait(4000);
		} catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "Url bar: No Such Element Found");
		}

	}

	public void enterUrl(String newurl) throws InterruptedException, IOException {
		/*
		 * Enter the Website in Url bar
		 */
		SoftAssert Sa = new SoftAssert();
		try {
			//			acceptDefault();
			customWait(2000);	 
			/*	clickBtn(Locators_Network_DeviceStability.DefaultUrlTxt);
					enterTextToInputField(Locators_Network_DeviceStability.DefaultUrlTxt,newurl);
			 */
			Runtime.getRuntime().exec("adb -s "+p_Id+" shell am start -a android.intent.action.VIEW -d http:/"+newurl);
			Thread.sleep(2000);
			APP_LOGS.info(" URl is entered is sucessful.");
			customWait(3000);
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_ENTER);
			minWait();
			APP_LOGS.info("Search click is sucessful.");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			test.log(LogStatus.ERROR, "Url bar: No Such Element Found");	
		} 
		Sa.assertAll();
	}


	


	public static void imsLogs(String fileName) throws AWTException, InterruptedException {

		String path = System.getProperty("user.dir");
		customWait(1000);
		try {
			Runtime.getRuntime().exec("cmd /C \"adb logcat -b all -v time>"+path+"\\src\\test\\resources\\adbLogs\\"+fileName+".txt\"");
			Thread.sleep(1000);
		}
		catch(Exception e) {
			Reporter.log("Something goes Wrong!!!");  
			e.printStackTrace(); 
		}
	}


	public void delete_All_Threads() throws InterruptedException {
		
		//  Method is used to delete All the SMS Threads.
		 
		SoftAssert sf = new SoftAssert();
		try {
			minWait();
			while (true) {
				if (isElementExist(Locators_Network_DeviceStability.firstSMS_InList)) {
					minWait();
					TouchAction touchaction = new TouchAction(aDriver);
					touchaction.longPress(Locators_Network_DeviceStability.firstSMS_InList).release().perform();
					minWait();
					clickBtn(Locators_Network_DeviceStability.delete_Icon_SMS);
					minWait();
					if(isElementExist(Locators_Network_DeviceStability.delete_Confirm)) {
						clickBtn(Locators_Network_DeviceStability.delete_Confirm);
						minWait();
					}
					else {
						clickBtn(Locators_Network_DeviceStability.delete_moreOption);
						minWait();
					}
				} 
				else if(isElementExist(Locators_Network_DeviceStability.first_sms_Thread)){
					minWait();
					TouchAction touchaction = new TouchAction(aDriver);
					touchaction.longPress(Locators_Network_DeviceStability.first_sms_Thread).release().perform();
					minWait();
					clickBtn(Locators_Network_DeviceStability.delete_Icon);
					minWait();
					if(isElementExist(Locators_Network_DeviceStability.delete_Confirm)) {
						clickBtn(Locators_Network_DeviceStability.delete_Confirm);
						minWait();
					}
					else {
						clickBtn(Locators_Network_DeviceStability.delete_moreOption);
						minWait();
					}
				}
				else {

					Reporter.log("No Threads Found");
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			sf.fail();
			test.log(LogStatus.ERROR, "Error in Deleting the Messages.");
		}
	}


	//======================================================================================================

	public void navigateTo_NewMessage() throws InterruptedException, IOException {
		SoftAssert sf = new SoftAssert();
		try {
			//				launch_an_app("messaging");
			minWait();
			//				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_MENU);
			minWait();
			if(isElementExist(Locators_Network_DeviceStability.add_NewSMS)) {
				clickBtn(Locators_Network_DeviceStability.add_NewSMS);
				minWait();
			}

			if(isElementExist(Locators_Network_DeviceStability.new_Message_Icon)) {
				clickBtn(Locators_Network_DeviceStability.new_Message_Icon);
				minWait();
			}

		} catch (Exception e) {
			e.printStackTrace();				
			test.log(LogStatus.ERROR, "Didn't navigated to New Message Window");
			sf.fail();
		}
		sf.assertAll();
	}

	public void create_NewSMS(String number, String message) throws InterruptedException, IOException {
		/* Method used to create New SMS. */
		navigateTo_NewSMS();
		try {
			enter_Num_ToField(number);
			System.out.println("Enter Text");
			enterText_MessageField(message);
		} catch (Exception e) {			 
			e.printStackTrace();
		}
	}

	public void validate_CharacterAndPageNumber(WebElement charAndPageNum,String expectedcharAndPageNum, int n) throws InterruptedException {
		/*
		 * This Meathod is to validate Characters and Page Number
		 * Precondition : User should be in Message window with Typed Message
		 */
		SoftAssert sf = new SoftAssert();
		try {
			minWait();
			String charAndPageNum1 = null;
			if(isElementExist(Locators_Network_DeviceStability.zero_Characters_FirstPage)) {
				System.out.println("Im out");
				charAndPageNum1 = charAndPageNum.getText();
			}
			else if(isElementExist(Locators_Network_DeviceStability.zero_Characters_FirstPage1)) {
				System.out.println("Im in");
				charAndPageNum1 = Locators_Network_DeviceStability.zero_Characters_FirstPage1.getText();
			}

			if (check=charAndPageNum1.equals(expectedcharAndPageNum)) {
				APP_LOGS.info("Character and Page Number Displayed is correct");
				sf.assertTrue(check, "Validation is Pass");
			} else {
				APP_LOGS.info("Character and Page Number Displayed is NOT correct");
				test.log(LogStatus.ERROR, "Error in validating the Number of characters and Page Number at iteration: "+ n);


			} 
		} catch (Exception e) {
			e.printStackTrace();
			//				sf.fail();
			//				test.log(LogStatus.ERROR, "Error in validating the Number of characters and Page Number");
		}
	}	

	public void validate_CharacterAndPageNumber_O(WebElement charAndPageNum,String expectedcharAndPageNum,int n) throws InterruptedException {
		/*
		 * This Meathod is to validate Characters and Page Number
		 * Precondition : User should be in Message window with Typed Message
		 */
		SoftAssert sf = new SoftAssert();
		try {
			minWait();
			String charAndPageNum1 = null;
			if(isElementExist(Locators_Network_DeviceStability.zero_Characters_FirstPage_O)) {
				System.out.println("Im out");
				charAndPageNum1 = charAndPageNum.getText();
			}
			System.out.println(charAndPageNum1);
			if (check=charAndPageNum1.equals(expectedcharAndPageNum)) {
				APP_LOGS.info("Character and Page Number Displayed is correct");
				sf.assertTrue(check, "Validation is Pass");
			} else {
				APP_LOGS.info("Character and Page Number Displayed is NOT correct");
				test.log(LogStatus.ERROR, "Error in validating the Number of characters and Page Number at iteration: "+ n);

			} 
		} catch (Exception e) {
			e.printStackTrace();
			//				test.log(LogStatus.ERROR, "Error in validating the Number of characters and Page Number");
		}
		sf.assertAll();
	}	

	public void create_NewSMS1(String number, String message) throws InterruptedException {
		/* Method used to create New SMS. */
		navigateTo_NewSMS1();
		try {
			enter_Num_ToField1(number);
			enterText_MessageField1(message);
		} catch (Exception e) {			 
			e.printStackTrace();
		}
	}

	public void navigateTo_NewSMS() throws InterruptedException {
		/* Used to Navigate to New Message Page. */
		try {
			clickBtn(Locators_Network_DeviceStability.add_NewSMS);
			minWait();
		} catch (Exception e) {		 
			e.printStackTrace();
		}
	}

	public void delete_SMS1() throws InterruptedException {
		/* This Method delete the First Thread in the List. */

		try {
			minWait();
			clickBtn(Locators_Network_DeviceStability.firstSMS_InList1);
			minWait();
			clickBtn(Locators_Network_DeviceStability.moreOptions);
			minWait();
			clickBtn(Locators_Network_DeviceStability.delete_moreOption);
			minWait();
			clickBtn(Locators_Network_DeviceStability.delete_Confirm);
			minWait();
			//				test.log(LogStatus.INFO, "SMS or MMS is deleted.");
		} catch (Exception e) {			 
			e.printStackTrace();
		}
	}

	public void delete_SMS() throws InterruptedException {
		/* This Method delete the First Thread in the List. */

		try {
			System.out.println("IN Delete");
			minWait();
			if (isElementExist(Locators_Network_DeviceStability.firstSMS_InList)) {
				clickBtn(Locators_Network_DeviceStability.firstSMS_InList);
				minWait();
				clickBtn(Locators_Network_DeviceStability.moreOptions);
				minWait();
				clickBtn(Locators_Network_DeviceStability.delete_Thread);
				minWait();
				clickBtn(Locators_Network_DeviceStability.delete_Confirm);
				minWait();
			}
		} catch (Exception e) {			 
			e.printStackTrace();
		}
	}

	public void clickOn_Send1() throws InterruptedException { 
		/* Method used to click on Send Button. */
		try {
			customWait(500);
			clickBtn(Locators_Network_DeviceStability.send_Icon1);
			minWait();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	public void navigateTo_NewSMS1() throws InterruptedException {
		/* Used to Navigate to New Message Page. */
		try {
			clickBtn(Locators_Network_DeviceStability.add_NewSMS1);
			minWait();
		} catch (Exception e) {		 
			e.printStackTrace();
		}
	}
	public void enterText_MessageField1(String message) throws InterruptedException {
		/* Methos is Used enter Message into Message Field. */
		try {
			minWait();
			enterTextToInputField(Locators_Network_DeviceStability.messageField1, message);
			minWait();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void enter_Num_ToField1(String number) throws InterruptedException {
		/* Method is used to Enter Number into TO Field. */
		try {
			minWait();
			enterTextToInputField(Locators_Network_DeviceStability.TO_Field1, number);
			customWait(2000);
			clickBtn(Locators_Network_DeviceStability.contactPicker);
			customWait(2000);			
		} catch (NoSuchElementException e) {			 
			e.printStackTrace();
		}
	}

	public void enter_Num_ToField(String number) throws InterruptedException {
		/* Method is used to Enter Number into TO Field. */
		try {
			minWait();
			enterTextToInputField(Locators_Network_DeviceStability.TO_Field, number);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			
		} catch (Exception e) {			 
			e.printStackTrace();
		}
	}

	public void enterText_MessageField(String message) throws InterruptedException {
		/* Methos is Used enter Message into Message Field. */
		try {
			customWait(2000);
			enterTextToInputField(Locators_Network_DeviceStability.messageField, message);
			minWait();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	public void type_New_Message(String cell_No, String typeMessage) throws InterruptedException, IOException {
		/*
		 * This Method is to type New Meassage.
		 */
		customWait(1000);
		type_Message(typeMessage);	
		enter_ToField(cell_No);

	}

	public void enter_ToField(String cellNo) throws InterruptedException {
		SoftAssert sf = new SoftAssert();
		try {
			customWait(2000);
			if(isElementExist(Locators_Network_DeviceStability.TO_Field)) {
				enterTextToInputField(Locators_Network_DeviceStability.TO_Field, cellNo);
				minWait();
			}

			if(isElementExist(Locators_Network_DeviceStability.TO_Field_enter)) {
				enterTextToInputField(Locators_Network_DeviceStability.TO_Field_enter, cellNo);
				minWait();
			}

			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_ENTER);
		} catch (Exception e) {
			e.printStackTrace();
			//				sf.fail();
			test.log(LogStatus.ERROR, "Error in entering number to ToField");
		}
		sf.assertAll();
	}

	public void type_Message(String typeMessage) throws InterruptedException {
		SoftAssert sf = new SoftAssert();
		try {
			minWait();
			if(isElementExist(Locators_Network_DeviceStability.type_Message)) {
				enterTextToInputField(Locators_Network_DeviceStability.messageField, typeMessage);
			}
			if(isElementExist(Locators_Network_DeviceStability.type_Message_enter)) {
				enterTextToInputField(Locators_Network_DeviceStability.type_Message_enter, typeMessage);
			}

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
			clickBtn(Locators_Network_DeviceStability.send);
			minWait();
		} catch (Exception e) {
			e.printStackTrace();
			//				sf.fail();
			test.log(LogStatus.ERROR, "Error in Sending the Message.");
		}
		sf.assertAll();
	}


	public void clickOn_Send() throws InterruptedException { 
		/* Method used to click on Send Button. */
		try {
			minWait();
			if(isElementExist(Locators_Network_DeviceStability.send_Icon)) {
				clickBtn(Locators_Network_DeviceStability.send_Icon);
				APP_LOGS.info("Send icon");
				minWait();
			}
			else if(isElementExist(Locators_Network_DeviceStability.send_SMS)) {
				clickBtn(Locators_Network_DeviceStability.send_SMS);
				APP_LOGS.info("Send icon");
				minWait();
			}
			else {
				clickBtn(Locators_Network_DeviceStability.send_MMS_Icon);
				minWait();
			}

		} catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "Error in Entering text meassage.");
		}
	}

	public void clearSMSPermissions() throws InterruptedException {
		try {	
			customWait(2000);
			//				clickBtn(Locators_Network_DeviceStability.StartMessaging);
			minWait();
			clickBtn(Locators_Network_DeviceStability.NEXT_Msg);
			minWait();
			clickBtn(Locators_Network_DeviceStability.allow_Permission);
			minWait();
			//			Runtime run = Runtime.getRuntime();
			//			Process pr = run.exec("adb shell input tap 540 1776");
			//			pr.waitFor();
			//			Runtime run1 = Runtime.getRuntime();
			//			Process pr1 = run1.exec("adb shell input tap 713 1098");
			//			pr1.waitFor();
		} catch (Exception e) {	
			e.getMessage();
		}		 
	}

	public void clickOnAppList() throws InterruptedException {

		try {
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_HOME);			
			customWait(2000);			
			clickBtn(Locators_Network_DeviceStability.app_List);
			minWait();
		} catch (Exception e) {
		//	e.printStackTrace();
		}

	}
	

	public void launch_APP(AndroidElement appToClick) throws InterruptedException {
		try {
			clickOnAppList();
			for (int i = 0; i < 6; i++) {
				if (isElementExist(appToClick)) {
					//						test.log(LogStatus.INFO, "\""+appToClick.getText()+"\" app launched.");
					clickBtn(appToClick);
					minWait();
					break;
				} else {
					scroll();
					minWait();
				}
			}
		} catch (NoSuchElementException e) {
			test.log(LogStatus.ERROR,"Error in the locators => launch_APP()");
			e.printStackTrace();
		}catch (Exception e) {
			test.log(LogStatus.ERROR,"Exception in functionality=> launch_APP()");
			e.printStackTrace();
		}}

	public void clearSMSPermissions_O() throws InterruptedException {
		try {	
			customWait(2000);
			clickBtn(Locators_Network_DeviceStability.StartMessaging);
			minWait();
			clickBtn(Locators_Network_DeviceStability.skipProvisioning);
			minWait();
		}  catch (NoSuchElementException e) {
			test.log(LogStatus.ERROR,"Error in the locators => clearSMSPermissions_O()");
			e.printStackTrace();
		}catch (Exception e) {
			test.log(LogStatus.ERROR,"Exception in functionality=> clearSMSPermissions_O()");
			e.printStackTrace();
		}}

	public void validate_SentMessage(int n,SoftAssert soft) throws InterruptedException {
		/* To validate the Sent Message. */

		try {
			//				 launch_an_app("messaging");
			customWait(5000);
			System.out.println("Sent msg");
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			customWait(1000);
			if(isElementExist(Locators_Network_DeviceStability.now_Text)||isElementExist(Locators_Network_DeviceStability.justnow_Text)||isElementExist(Locators_Network_DeviceStability.not_Sent_Text)||isElementExist(Locators_Network_DeviceStability.sending_Txt)) {
				
				APP_LOGS.info("Message sent Succeccfully");
				test.log(LogStatus.PASS, "Test case status is Passed at iteration : "+ n);
				soft.assertTrue(true, " ");

			} else {
				
				APP_LOGS.info("SMS didn't sent");
				test.log(LogStatus.FAIL, "SMS or MMS didn't Sent, Validation Failed at iteration : "+ n);
				soft.fail();
			}
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			minWait();
			/*	   aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);*/
		} catch (Exception e) {			 
			e.printStackTrace();
			
		}		
	}

	public void validate_CharacterAndPageNumber(WebElement charAndPageNum,String expectedcharAndPageNum) throws InterruptedException {
		/*
		 * This Meathod is to validate Characters and Page Number
		 * Precondition : User should be in Message window with Typed Message
		 */
		SoftAssert sf = new SoftAssert();
		try {
			minWait();
			String charAndPageNum1 = null;
			if(isElementExist(Locators_Network_DeviceStability.zero_Characters_FirstPage)) {
				System.out.println("Im out");
				charAndPageNum1 = charAndPageNum.getText();
			}
			else if(isElementExist(Locators_Network_DeviceStability.zero_Characters_FirstPage1)) {
				System.out.println("Im in");
				charAndPageNum1 = Locators_Network_DeviceStability.zero_Characters_FirstPage1.getText();
			}

			if (check=charAndPageNum1.equals(expectedcharAndPageNum)) {
				APP_LOGS.info("Character and Page Number Displayed is correct");
				sf.assertTrue(check, "Validation is Pass");
			} else {
				APP_LOGS.info("Character and Page Number Displayed is NOT correct");
				sf.fail();
			} 
		} catch (Exception e) {
			e.printStackTrace();
			//				sf.fail();
			//				test.log(LogStatus.ERROR, "Error in validating the Number of characters and Page Number");
		}
		sf.assertAll();
	}	

	public void validate_CharacterAndPageNumber_O(WebElement charAndPageNum,String expectedcharAndPageNum) throws InterruptedException {
		/*
		 * This Meathod is to validate Characters and Page Number
		 * Precondition : User should be in Message window with Typed Message
		 */
		SoftAssert sf = new SoftAssert();
		try {
			minWait();
			String charAndPageNum1 = null;
			if(isElementExist(Locators_Network_DeviceStability.zero_Characters_FirstPage_O)) {
				System.out.println("Im out");
				charAndPageNum1 = charAndPageNum.getText();
			}
			System.out.println(charAndPageNum1);
			if (check=charAndPageNum1.equals(expectedcharAndPageNum)) {
				APP_LOGS.info("Character and Page Number Displayed is correct");
				sf.assertTrue(check, "Validation is Pass");
			} else {
				APP_LOGS.info("Character and Page Number Displayed is NOT correct");
				sf.fail();
			} 
		} catch (Exception e) {
			e.printStackTrace();
			//				sf.fail();
			//				test.log(LogStatus.ERROR, "Error in validating the Number of characters and Page Number");
		}
		sf.assertAll();
	}	

	public void navigateAttachOthers() throws InterruptedException {
		/* Navigates to */
		try {
			minWait();
			clickBtn(Locators_Network_DeviceStability.attach_icon_O);
			minWait();
			System.out.println("Clicked Attachment");
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






	/*public void enable_MobileData() throws InterruptedException, IOException {
		 
		 * This Method is to enable MobileData.
		 
		if (p_b_No.contains("-11.")||p_b_No.contains("-12.")||p_b_No.contains("-18.")||p_b_No.contains("-26.")||p_b_No.contains("-29.")||p_b_No.contains("-30.")) {
			try {
				minWait();
				aDriver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().className(\"android.support.v7.widget.RecyclerView\")).scrollIntoView(new UiSelector().text(\"Data usage\"))").click();
				minWait();
			} catch (Exception e) {
			}
			if(!isElementExist(Locators_Network_DeviceStability.cellularData_OnState)) {
				clickBtn(Locators_Network_DeviceStability.cellularData_OffState);
				if(isElementExist(Locators_Network_DeviceStability.OK)) {
					clickBtn(Locators_Network_DeviceStability.OK);
				}
				APP_LOGS.info("MobileData is Disabled");
				minWait();
			} else {
				APP_LOGS.info("MobileData is already Disabled");
			}
		} else if(p_b_No.contains("-10.")){
			try {
				navigateTo_CellularNetworks();
				minWait();
				if(!isElementExist(Locators_Network_DeviceStability.mobileData_OnState)) {
					clickBtn(Locators_Network_DeviceStability.mobileData_OffState);
					if(isElementExist(Locators_Network_DeviceStability.OK)) {
						clickBtn(Locators_Network_DeviceStability.OK);
					}
					APP_LOGS.info("MobileData is Disabled");
					minWait();
				} else {
					APP_LOGS.info("MobileData is already Disabled");
				}
			} catch (Exception e) {			 
				e.printStackTrace();
			}	
		}	
	}
*/
	public void navigateTo_CellularNetworks() throws InterruptedException, IOException {
		/* This Method will Navigate to Cellular Netwotks option. */ 
		minWait();
		try {
			aDriver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().className(\"android.support.v7.widget.RecyclerView\")).scrollIntoView(new UiSelector().text(\"More\"))").click();
			minWait();
			aDriver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().className(\"android.support.v7.widget.RecyclerView\")).scrollIntoView(new UiSelector().text(\"Cellular networks\"))").click();
			minWait();
		} catch (Exception e) {			 
			e.printStackTrace();
		}
	}


	public void clickOn_NetworkAndInternet() {
		try {
		scrollTo("Network & Internet");
		if(isElementExist(Locators_Network_DeviceStability.NetworkAndInternet_tc1)) {
			clickBtn(multi_Loc_Strategy(Locators_Network_DeviceStability.NetworkAndInternet_tc1,Locators_Network_DeviceStability.NetworkAndInternet_tc2, Locators_Network_DeviceStability.NetworkAndInternet_x3, null,null, 414, 1004));
		}else {
			boolean ni=scrollText("Network & Internet");
			while(ni==false) {
				ni=scrollText("Network & Internet");
			}
		}
		} catch (NoSuchElementException e) {
			test.log(LogStatus.ERROR,"Error in the locators => clickOn_NetworkAndInternet()");
			e.printStackTrace();
		}catch (Exception e) {
			test.log(LogStatus.ERROR,"Exception in functionality=> clickOn_NetworkAndInternet()");
			e.printStackTrace();
		}
	}
	public void clickOn_MobileNetwork() {
		try {
			scrollTo("Mobile network");
			AndroidElement element=multi_Loc_Strategy(Locators_Network_DeviceStability.MobileNetwork_tc1, null, null, null, null, 371, 546);
			clickBtn(element);
		} catch (NoSuchElementException e) {
			test.log(LogStatus.ERROR,"Error in the locators => clickOn_MobileNetwork()");
			e.printStackTrace();
		}catch (Exception e) {
			test.log(LogStatus.ERROR,"Exception in functionality=> clickOn_MobileNetwork()");
			e.printStackTrace();
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
	public void makeCall(String contactNo) {
		try {
			if(isElementExist(Locators_Network_DeviceStability.keypad)) {
				clickBtn(Locators_Network_DeviceStability.keypad);
				minWait();
			}if(isElementExist(Locators_Network_DeviceStability.enterNumber)) {
				enterTextToInputField(Locators_Network_DeviceStability.enterNumber,contactNo);
				minWait();
			}if(isElementExist(Locators_Network_DeviceStability.call)) {
				clickBtn(Locators_Network_DeviceStability.call);
			}
		}catch (NoSuchElementException e) {
			test.log(LogStatus.ERROR,"Error in the locators => makeCall()");
			e.printStackTrace();
		}catch (Exception e) {
			test.log(LogStatus.ERROR,"Exception in functionality=> makeCall()");
			e.printStackTrace();
		}
	}
	public void dialCallUsingDialPad(String dailNum) throws InterruptedException, IOException {

		try {
			dailNumber(dailNum);
			if (!isElementExist(Locators_Network_DeviceStability.turnOff_Airplane_PopUp)) {
				try {
					for(int j=1;j<=100;j++){
						Process child = null;
						if (r_b_No.contains("8A.")) {
							child=Runtime.getRuntime().exec("adb -s "+r_Id+" shell service call telecom 27");
						} else if(r_b_No.contains("5SA.")) {
							child=Runtime.getRuntime().exec("adb -s "+r_Id+" shell service call telecom 28");
						}
						customWait(5000);
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
				/*	Thread.sleep(2000);
					Runtime.getRuntime().exec("adb -s "+r_Id+" shell input keyevent 5");
					Thread.sleep(2000);
				*/}
			}
			APP_LOGS.info("Number dailed is: "+dailNum);
		}catch (NoSuchElementException e) {
			test.log(LogStatus.ERROR,"Error in the locators => dialCallUsingDialPad()");
			e.printStackTrace();
		}catch (Exception e) {
			test.log(LogStatus.ERROR,"Exception in functionality=> dialCallUsingDialPad()");
			e.printStackTrace();
		}
	}

	public void dailNumber(String dailNum) throws IOException {
		try {
			minWait();
			clickBtn(Locators_Network_DeviceStability.add_Call);
			minWait();
			clickBtn(Locators_Network_DeviceStability.dailerPad);
			minWait();
			clickBtn(Locators_Network_DeviceStability.enterNumFiled);
			minWait();
			enterTextToInputField(Locators_Network_DeviceStability.enterNumFiled, dailNum);
			minWait();
			//Runtime.getRuntime().exec("adb -s "+p_Id+" shell input text "+dailNum);
			minWait();
			clickBtn(Locators_Network_DeviceStability.dail);
			customWait(4000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}



	public void validate_Airplane_Enable(SoftAssert soft,int n) throws InterruptedException, IOException {
		/* 
		 * Method can be Used Validate Airplane Mode activation via by making the call.
		 */
		try {
			if(wait(Locators_Network_DeviceStability.turnOff_Airplane_PopUp,60)) {
				minWait();
				APP_LOGS.info("Airplane mode is enabled -> Turn off airplane mode popup displayed at iteration : "+n);
				soft.assertTrue(true, "Airplane mode is enabled -> Turn off airplane mode popup displayed at iteration : "+n);
				test.log(LogStatus.PASS, "Airplane mode is enabled -> Turn off airplane mode popup displayed at iteration : "+n);
			} else  {
				minWait();
				APP_LOGS.info("Failed -> Turnoff airplane mode popup is not displayed");			
				soft.fail();
				test.log(LogStatus.FAIL,"Failed -> Turnoff airplane mode popup is not displayed"+n);
			}
		} catch (NoSuchElementException e) {
			test.log(LogStatus.ERROR,"Error in the locators => validate_Airplane_Enable()");
			e.printStackTrace();
		}catch (Exception e) {
			test.log(LogStatus.ERROR,"Exception in functionality=> validate_Airplane_Enable()");
			e.printStackTrace();
		}
	}

	public void validate_Airplane_Disable(SoftAssert soft,int n) throws InterruptedException, IOException {
		/* 
		 * Method can be Used Validate Airplane Mode activation via by making the call.
		 */
		try {		
			if(!isElementExist(Locators_Network_DeviceStability.turnOff_Airplane_PopUp)) {
				minWait();
				check=true;
				APP_LOGS.info("Airplane mode is disabled -> Airplane mode popup is not displayed at iteration : "+n);
				soft.assertTrue(true, "Airplane mode is disabled -> Airplane mode popup is not displayed at iteration : "+n);
				test.log(LogStatus.PASS, "Airplane mode is disabled -> Airplane mode popup is not displayed at iteration : "+n);
			} else  {
				minWait();
				APP_LOGS.info("Failed -> Airplane mode is not disabled");	
				soft.fail();
				test.log(LogStatus.FAIL,"Failed -> Airplane mode is not disabled");
			}
			
		} catch (NoSuchElementException e) {
			test.log(LogStatus.ERROR,"Error in the locators => validate_Airplane_Disable()");
			e.printStackTrace();
		}catch (Exception e) {
			test.log(LogStatus.ERROR,"Exception in functionality=> validate_Airplane_Disable()");
			e.printStackTrace();
		}
	}


	public void endCall() throws InterruptedException {
		/* Method is to end Call.
		 * Precondition : User should initiate the Call to any Number. */
		try {
			minWait();
			if(isElementExist(Locators_Network_DeviceStability.end_Call)) {
				clickBtn(Locators_Network_DeviceStability.end_Call);
				APP_LOGS.info("Call Ended");
			}else {
				APP_LOGS.info("End Call Button Doesn't exist on screen");
			}
		}catch (NoSuchElementException e) {
			test.log(LogStatus.ERROR,"Error in the locators => end_Call()");
			e.printStackTrace();
		}catch (Exception e) {
			test.log(LogStatus.ERROR,"Exception in functionality=> end_Call()");
			e.printStackTrace();
		}}
public void setName_BTRefDevice(String refDeviceName) {
	try {
			clearRecentApps();
			launch_an_app("settings");
			scrollToText("Connected devices");
			scrollToText("Bluetooth");
			if(Locators_Network_DeviceStability.BT_switch.getText().equalsIgnoreCase("off")) {
				clickBtn(Locators_Network_DeviceStability.BT_switch);
			}
		new WebDriverWait(aDriver, 10).until(ExpectedConditions.visibilityOf(Locators_Network_DeviceStability.BT_DeviceName)).click();
			enterTextToInputField(Locators_Network_DeviceStability.setdata, refDeviceName);
			clickBtn(Locators_Network_DeviceStability.BT_RENAME);
			
		}catch (NoSuchElementException e) {
		test.log(LogStatus.ERROR,"Error in the locators => setName_BTRefDevice()");
		e.printStackTrace();
	}catch (Exception e) {
		test.log(LogStatus.ERROR,"Exception in functionality=> setName_BTRefDevice()");
		e.printStackTrace();
	}	
	
}
public void enable_BT() {
	try {
			if(Locators_Network_DeviceStability.BT_switch.getText().equalsIgnoreCase("off")) {
				clickBtn(Locators_Network_DeviceStability.BT_switch);
			}
			
		}catch (NoSuchElementException e) {
		test.log(LogStatus.ERROR,"Error in the locators => enable_BT()");
		e.printStackTrace();
	}catch (Exception e) {
		test.log(LogStatus.ERROR,"Exception in functionality=> enable_BT()");
		e.printStackTrace();
	}	
	
}
public void disable_BT() {
	try {
			if(Locators_Network_DeviceStability.BT_switch.getText().equalsIgnoreCase("on")) {
				clickBtn(Locators_Network_DeviceStability.BT_switch);
			}
			
		}catch (NoSuchElementException e) {
		test.log(LogStatus.ERROR,"Error in the locators => disable_BT()");
		e.printStackTrace();
	}catch (Exception e) {
		test.log(LogStatus.ERROR,"Exception in functionality=> disable_BT()");
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
		Runtime.getRuntime().exec("adb -s "+r_Id+" shell am start -a android.bluetooth.adapter.action.REQUEST_DISABLE");
		minWait();
		Runtime.getRuntime().exec("adb -s "+r_Id+" shell input tap 869 1073");
		customWait(3000);
	} catch (Exception e) {
		e.printStackTrace();
	}
	}
	public void enable_BT_RefDevice() throws InterruptedException, IOException {

		try {
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
	
	public void setName_RefBTDevice(String name) throws InterruptedException, IOException {

		try {
			minWait();
			Runtime.getRuntime().exec("adb  -s "+r_Id+" shell input tap 900 1000 ");
			minWait();
			Runtime.getRuntime().exec("adb  -s "+r_Id+" shell input swipe 143 616 918 610 2000"); // this will select the text 2000 is duration
			minWait();
			Runtime.getRuntime().exec("adb  -s "+r_Id+" shell input keyevent 67");
			minWait();
			Runtime.getRuntime().exec("adb  -s "+r_Id+" shell input keyboard text "+name);
			minWait();
			Runtime.getRuntime().exec("adb -s "+r_Id+" shell input tap 860 803");
		/*	minWait();
			Runtime.getRuntime().exec("adb -s "+r_Id+" shell input keyevent 66");
		*/	customWait(3000);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void ref_TapOnPAIR() throws InterruptedException, IOException {

		try {
		//	wait(Locators_Network_DeviceStability.BT_PAIR, 30);
			Runtime.getRuntime().exec("adb -s "+r_Id+" shell input tap 552 158 ");
			Runtime.getRuntime().exec("adb -s "+r_Id+" shell input tap 866 1240 ");
			
		}catch (NoSuchElementException e) {
			test.log(LogStatus.ERROR,"Error in the locators => ref_TapOnPAIR()");
			e.printStackTrace();
		}catch (Exception e) {
			test.log(LogStatus.ERROR,"Exception in functionality=> ref_TapOnPAIR()");
			e.printStackTrace();
		}
	}

	public void select_BT_RefDevice(String refDevName) {
		try {
			boolean refDevice = scrollTo(refDevName);
			if(refDevice == true) {
				scrollToText(refDevName);	
			}
			else {
				AndroidElement BT_RefDevice_opt3=aDriver.findElementByXPath("//*[@text='"+refDevName+"']");
				boolean ref1=wait(BT_RefDevice_opt3,10);
				if(ref1)
					clickBtn(BT_RefDevice_opt3);
				else
					scrollToElements(BT_RefDevice_opt3);
				clickBtn(BT_RefDevice_opt3);
					
			}
			
		}catch (NoSuchElementException e) {
			test.log(LogStatus.ERROR,"Error in the locators => select_BT_RefDevice()");
			e.printStackTrace();
		}catch (Exception e) {
			test.log(LogStatus.ERROR,"Exception in functionality=> select_BT_RefDevice()");
			e.printStackTrace();
		}}
	public void clickOnPAIR() {
		try {
			new WebDriverWait(aDriver,60).until(ExpectedConditions.elementToBeClickable(Locators_Network_DeviceStability.BT_PAIR)).click();
		}catch (NoSuchElementException e) {
			test.log(LogStatus.ERROR,"Error in the locators => clickOnPAIR()");
			e.printStackTrace();
		}catch (Exception e) {
			test.log(LogStatus.ERROR,"Exception in functionality=> clickOnPAIR()");
			e.printStackTrace();
		}}

	public void validate_BT_Devices(SoftAssert soft, int n,String BT_Device_Name)  {
		try {
			minWait();
			Runtime.getRuntime().exec("adb -s "+r_Id+" shell am start -a android.settings.BLUETOOTH_SETTINGS");
			customWait(6000);
			System.out.println(" Im Searching...BT devices");
			WebDriverWait wait = new WebDriverWait(aDriver, 120);
			//		scrollToElement(Locators_Network_DeviceStability.BT_Devices);
			AndroidElement BT_devices=aDriver.findElementByXPath("//*[@text='"+BT_Device_Name+"']");	//New Statement added
			wait.until(ExpectedConditions.visibilityOf(BT_devices));
			
			minWait();			
			boolean check=BT_devices.isDisplayed();
			minWait();
			if (check) {
				System.out.println("Bluetooth enabled");
				APP_LOGS.info("Enabling Bluetooth is validated successfully at iteration : "+n);
				soft.assertTrue(true, "Enabling Bluetooth is validated successfully at iteration : ");
				test.log(LogStatus.PASS, "Enabling Bluetooth is validated successfully at iteration : "+n);
			}
			else {
				System.out.println("Bluetooth disabled");
				APP_LOGS.info("Enable Bluethooth is UnSuccessful at iteration: "+n);
				soft.fail();
				test.log(LogStatus.FAIL, "Enable Bluethooth is UnSuccessful at iteration: "+n);
			}}	catch (NoSuchElementException ne) {
			test.log(LogStatus.ERROR,"Error in the locators => validate_BT_Devices()");
			ne.printStackTrace();
		}catch (Exception en) {
			test.log(LogStatus.ERROR,"Exception in functionality=> validate_BT_Devices()");
			en.printStackTrace();
		}
	
	}

	public boolean clickOnText(String text) {
		/*
		  Method used to select an element on the page by scrolling the Scroll View/List View
		 */
		boolean check = false;
		try {  
			String scrollable = "new UiSelector()";
			String textElement = ".text(\""+ text +"\")";
			aDriver.findElementByAndroidUIAutomator(scrollable+textElement).click();
			APP_LOGS.info("Searched application is found sucessfully : ");
			check = true;
		}
		catch (NoSuchElementException ne) {
			test.log(LogStatus.ERROR,"Error in the locators => clickOnText()");
			ne.printStackTrace();
		}catch (Exception en) {
			test.log(LogStatus.ERROR,"Exception in functionality=> clickOnText()");
			en.printStackTrace();
		}
		return check;

	}

	public boolean scrollToTextContains(String text) {
		/*
			  Method used to select an element on the page by scrolling the Scroll View/List View
		 */
		boolean check = false;
		try {  
			String scrollable = "new UiScrollable(new UiSelector().scrollable(true))";
			String textElement = ".scrollIntoView(new UiSelector().textContains(\""+ text +"\"))";
			aDriver.findElementByAndroidUIAutomator(scrollable+textElement).click();
			APP_LOGS.info("Searched application is found sucessfully : ");
			check = true;
		}
		catch (NoSuchElementException ne) {
			test.log(LogStatus.ERROR,"Error in the locators => scrollToTextContains()");
			ne.printStackTrace();
		}catch (Exception en) {
			test.log(LogStatus.ERROR,"Exception in functionality=> scrollToTextContains()");
			en.printStackTrace();
		}
		return check;

	}

	public void scrollToElementWithDpadDown(WebElement element) {
		/*
		 * Clicks up button till element is available
		 */
		System.out.println("Clicking Element1");
		if(isElementExist(element)) {
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
			System.out.println("Clicking Element");
			element.click();
		}
		else {
			System.out.println("Clicking First Element");
			clickBtn(Locators_Network_DeviceStability.firstApp_Suggetion);
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
			}catch (NoSuchElementException e) {
				check=false;
			}catch (Exception e) {
				check=false;
			}
		return check;
	}
	public void clickOn_BackBtn()  {
		try {
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			minWait();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void checkWifiConnected(String ssid) throws InterruptedException {
		try {
			customWait(4000);
			if(isElementExist(Locators_Network_DeviceStability.connectedWIFI1)) {
				String getTxt = Locators_Network_DeviceStability.connectedWIFI1.getText();
				System.out.println(getTxt);
				test.log(LogStatus.INFO, "Wi-Fi is Connected : will be disconnecting");
				disconnectSSIDifConnected(ssid);
			}
			else {
				System.out.println("Not Connected");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void validate_PageIsLoaded(String type, int n,SoftAssert soft) throws InterruptedException, IOException {
		/* 
		 * Validates MobileData enabality by launching the Chrome & browsing google page.
		 */

		try {
				boolean noInternet=wait(Locators_Network_DeviceStability.No_Internet,7);
				if(!noInternet) {
					APP_LOGS.info(type+"is connected successfully and given website is loaded at iteration : "+n);
					soft.assertTrue(true,type+"is connected successfully and given website is loaded at iteration : "+n);
					test.log(LogStatus.PASS, type+"is connected successfully and given website is loaded at iteration : "+n);
				} else {
					APP_LOGS.info("Failed -> "+type+" is not connected ");
					soft.fail();
					test.log(LogStatus.FAIL,"Failed -> "+type+" is not connected ");
									}
		} catch (NoSuchElementException e) {
			test.log(LogStatus.ERROR,"Error in the locators => validate_PageIsLoaded()");
			e.printStackTrace();
		}catch (Exception e) {
			test.log(LogStatus.ERROR,"Exception in functionality=> validate_PageIsLoaded()");
			e.printStackTrace();
		}
		//		closeAllTabs();	
	}

		public void ON_Switch(String switch_To_ON) {
		try {
			minWait();
			if(isElementExist(aDriver.findElementByXPath("//*[contains(@text,'"+switch_To_ON+"')]/../..//*[@text='OFF']")))
					{
				aDriver.findElementByXPath("//*[contains(@text,'"+switch_To_ON+"')]/../..//*[@text='OFF']").click();
					}
			minWait();
		} catch (NoSuchElementException e) {
			test.log(LogStatus.ERROR,"Error in the locators => ON_Switch()");
			e.printStackTrace();
		}catch (Exception e) {
			test.log(LogStatus.ERROR,"Exception in functionality=> ON_Switch()");
			e.printStackTrace();
		}
		
	}
	public boolean enable_AirplaneMode() {
		/*
		 * disable data from data usage window
		 */
		boolean enabled = false;
		try {
				scrollTo("Mobile network");
			if(Locators_Network_DeviceStability.checkMobilenetwork.isEnabled()==true) {
				scrollTo("Airplane mode");
				ON_Switch("Airplane mode");
				if(wait(Locators_Network_DeviceStability.OK,5)) {
					clickBtn(Locators_Network_DeviceStability.OK);
				}
				enabled=true;
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.ERROR, "Error in the locators->enable_AirplaneMode() ");
			e.printStackTrace();
		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exception in ->enable_AirplaneMode()");
			e.printStackTrace();
		}
		return enabled;
	}
	public boolean disable_AirplaneMode() {
		/*
		 * disable data from data usage window
		 */
		boolean enabled = false;
		try {
			scrollTo("Mobile network");
			if(Locators_Network_DeviceStability.checkMobilenetwork.isEnabled()==false) {
				scrollTo("Airplane mode");
				OFF_Switch("Airplane mode");
			enabled=true;}
		} catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.ERROR, "Error in the locators->disable_AirplaneMode() ");
			e.printStackTrace();
		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exception in ->disable_AirplaneMode()");
		}
		return enabled;
	}
	public boolean enable_Mobiledata() {
		/*
		 * disable data from data usage window
		 */
		boolean enabled = false;
		try {
			if (Locators_Network_DeviceStability.mobile_data_State.getText().equalsIgnoreCase("off")) {
				clickBtn(Locators_Network_DeviceStability.mobile_data_State);
				enabled=true;
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.ERROR, "Error in the locators->enable_Mobiledata() ");
			e.printStackTrace();
		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exception in ->enable_Mobiledata()");
		}
		return enabled;
	}

	public boolean disable_MobileData() {
		/*
		 * disable data from data usage window
		 */
		boolean disabled = false;
		try {
			if (Locators_Network_DeviceStability.mobile_data_State.getText().equalsIgnoreCase("on")) {
				clickBtn(Locators_Network_DeviceStability.mobile_data_State);
				clickBtn(Locators_Network_DeviceStability.OK);
				disabled=true;
}
		} catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.ERROR, "Error in the locators->disable_MobileData() ");
			e.printStackTrace();
		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exception in ->disable_MobileData()");
		}
		return disabled;
	}

	
	public void OFF_Switch(String switch_To_OFF) {
		try {
			minWait();
			if(isElementExist(aDriver.findElementByXPath("//*[contains(@text,'"+switch_To_OFF+"')]/../..//*[@text='ON']"))){
				aDriver.findElementByXPath("//*[contains(@text,'"+switch_To_OFF+"')]/../..//*[@text='ON']").click();
			}
		//	minWait();
		} catch (NoSuchElementException e) {
			test.log(LogStatus.ERROR,"Error in the locators => OFF_Switch()");
			e.printStackTrace();
		}catch (Exception e) {
			test.log(LogStatus.ERROR,"Exception in functionality=> OFF_Switch()");
			e.printStackTrace();
		}
		}
	public void clickOn_Wifi() throws InterruptedException {
		try {
		if(isElementExist(Locators_Network_DeviceStability.Wi_Fi)) {
			clickBtn(Locators_Network_DeviceStability.Wi_Fi);
		}
		else {
			scrollToElements(Locators_Network_DeviceStability.Wi_Fi);
			clickBtn(multi_Loc_Strategy(Locators_Network_DeviceStability.Wi_Fi,Locators_Network_DeviceStability.wi_Fi_x2,null,null,null,216,288));
		}
		} catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.ERROR, "Error in the locators -> clickOn_Wifi()");
			e.printStackTrace();
		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exeption in -> clickOn_Wifi()");
		}

	}	
	public boolean enable_Wifi() {
		boolean enabled=false;
		try {
			minWait();
			if(isElementExist(Locators_Network_DeviceStability.wifiConnectionSate)) {
		 if(Locators_Network_DeviceStability.wifiConnectionSate.getText().equalsIgnoreCase("off")) {
				clickBtn(Locators_Network_DeviceStability.wifiConnectionSate);
				enabled=(Locators_Network_DeviceStability.wifiConnectionSate.getText().equalsIgnoreCase("on"))?true:false;
			}
			}
		}  catch (NoSuchElementException e) {
			test.log(LogStatus.ERROR,"Error in the locators => enable_Wifi()");
			e.printStackTrace();
		}catch (Exception e) {
			test.log(LogStatus.ERROR,"Exception in functionality=> enable_Wifi()");
			e.printStackTrace();
		}
	return enabled;	
	}

	public boolean disable_Wifi() {
		boolean disabled=false;
		try {
		 if(Locators_Network_DeviceStability.wifiConnectionSate.getText().equalsIgnoreCase("on")) {
				clickBtn(Locators_Network_DeviceStability.wifiConnectionSate);
			}
			
		}  catch (NoSuchElementException e) {
			test.log(LogStatus.ERROR,"Error in the locators => disable_Wifi()");
			e.printStackTrace();
		}catch (Exception e) {
			test.log(LogStatus.ERROR,"Exception in functionality=> disable_Wifi()");
			e.printStackTrace();
		}
	return disabled;	
	}
	public void closeAllTabs() throws InterruptedException {
		/* To close all tha Tabs in Chrome Browser. */
		try {
			minWait();
			clickBtn(Locators_Network_DeviceStability.switcherButton_Chrome);
			minWait();
			clickBtn(Locators_Network_DeviceStability.menuButton_Chrome);
			minWait();
			clickBtn(Locators_Network_DeviceStability.closeAllTabs_Chrome);
			minWait(); 
		} catch (NoSuchElementException e) {			 
			e.printStackTrace();
		}
	}
	public void enterURL(int n,String url) {
		try {	
	
			if(isElementExist(Locators_Network_DeviceStability.alert_OKBtn)) {
				clickBtn(Locators_Network_DeviceStability.alert_OKBtn);
				System.out.println("Im Clicking Ok First ");
			}
			if(isElementExist(Locators_Network_DeviceStability.NO_THANKS)) {
				clickBtn(Locators_Network_DeviceStability.NO_THANKS);
			}
			if(isElementExist(Locators_Network_DeviceStability.No_ThanKS) ){
				clickBtn(Locators_Network_DeviceStability.No_ThanKS);
			}
		
			customWait(2000);
			enterTextToInputField(Locators_Network_DeviceStability.chromeSearch,url);
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_ENTER);
			customWait(2000);
			if(isElementExist(Locators_Network_DeviceStability.OK)) {
				clickBtn(Locators_Network_DeviceStability.OK);
			}
			if(isElementExist(Locators_Network_DeviceStability.NO_THANKS)) {
				clickBtn(Locators_Network_DeviceStability.NO_THANKS);
			}
			if(isElementExist(Locators_Network_DeviceStability.No_ThanKS) ){
				clickBtn(Locators_Network_DeviceStability.No_ThanKS);
			}
		
		} catch (NoSuchElementException e) {
			test.log(LogStatus.ERROR,"Error in the locators => enterURL()");
			e.printStackTrace();
		}catch (Exception e) {
			test.log(LogStatus.ERROR,"Exception in functionality=> enterURL()");
			e.printStackTrace();
		}
		
	}
	public boolean wait(AndroidElement element,int waitTime) {
		boolean availability=false;
		try {
		new WebDriverWait(aDriver, waitTime).until(ExpectedConditions.visibilityOf(element));
		availability=true;
		}
		catch(NoSuchElementException e) {
		availability=false;
		}
		return availability;
	}
	public void clear_ChromeHistory() {
		try {
			Runtime.getRuntime().exec("adb -s "+p_Id+" shell pm clear com.android.chrome");
		}catch (Exception e) {
		
		}
	}
	public void validate_PageIsNotLoaded(int n,String typ,SoftAssert soft) throws InterruptedException, IOException {
		/* 
		 * Validates MobileData disabality by launching the Chrome.
		 */
	
		try {	
			customWait(6000);
			boolean noInternet=wait(Locators_Network_DeviceStability.No_Internet,30);
			boolean Results_will_be_available=wait(Locators_Network_DeviceStability.Results_will_be_available,3);
			if(Results_will_be_available) {
			System.out.println("results text = "+Locators_Network_DeviceStability.Results_will_be_available.getText());
			}
			if(noInternet || Results_will_be_available) {
				APP_LOGS.info(typ+"is disconnected successfully and given website is not loaded at iteration : "+n);
				soft.assertTrue(true, typ+"is disconnected successfully and given website is not loaded at iteration : "+n);
				test.log(LogStatus.PASS, typ+"is disconnected successfully and given website is not loaded at iteration : "+n);	
			
			}else {
				APP_LOGS.info("Failed -> "+typ+"is not disconnected");
				soft.fail();
				test.log(LogStatus.FAIL,"Failed -> "+typ+"is not disconnected");
			
			}
		} catch (NoSuchElementException e) {
			test.log(LogStatus.ERROR,"Error in the locators => validate_PageIsNotLoaded()");
			e.printStackTrace();
		}catch (Exception e) {
			test.log(LogStatus.ERROR,"Exception in functionality=> validate_PageIsNotLoaded()");
			e.printStackTrace();
		}
		
	}	public void clear_ChromePermission() {
		try {
			if(isElementExist(Locators_Network_DeviceStability.OK)) {
				clickBtn(Locators_Network_DeviceStability.OK);
				minWait();
			}
			if(isElementExist(Locators_Network_DeviceStability.No_ThanKS)) {
				clickBtn(Locators_Network_DeviceStability.No_ThanKS);
			}
		
			if(isElementExist(Locators_Network_DeviceStability.Cancel)) {
				clickBtn(Locators_Network_DeviceStability.Cancel);
			}
			if(isElementExist(Locators_Network_DeviceStability.Accept_id)) {
				clickBtn(Locators_Network_DeviceStability.Accept_id);
			}
			if(isElementExist(Locators_Network_DeviceStability.NEXT)) {
				clickBtn(Locators_Network_DeviceStability.NEXT);
			}
			if(isElementExist(Locators_Network_DeviceStability.NEXTBTN)) {
				clickBtn(Locators_Network_DeviceStability.NEXTBTN);
			}
	
			if(isElementExist(Locators_Network_DeviceStability.NO_THANKS)) {
				clickBtn(Locators_Network_DeviceStability.NO_THANKS);
			}
		
			if(isElementExist(Locators_Network_DeviceStability.No_ThanKS)) {
				clickBtn(Locators_Network_DeviceStability.No_ThanKS);
			}
			
	}  catch (NoSuchElementException e) {
		test.log(LogStatus.ERROR,"Error in the locators => clear_ChromePermission()");
		e.printStackTrace();
	}catch (Exception e) {
		test.log(LogStatus.ERROR,"Exception in functionality=> clear_ChromePermission()");
		e.printStackTrace();
	}		
}

	public void clear_ChromePermission_VSW() {
		try {
				if(isElementExist(Locators_Network_DeviceStability.OK)) {
					clickBtn(Locators_Network_DeviceStability.OK);
					minWait();
				}
				if(isElementExist(Locators_Network_DeviceStability.No_ThanKS)) {
					clickBtn(Locators_Network_DeviceStability.No_ThanKS);
				}
				if(isElementExist(Locators_Network_DeviceStability.OK)) {
					clickBtn(Locators_Network_DeviceStability.OK);
					minWait();
				}
				if(isElementExist(Locators_Network_DeviceStability.Cancel)) {
					clickBtn(Locators_Network_DeviceStability.Cancel);
				}
				if(isElementExist(Locators_Network_DeviceStability.OK)) {
					clickBtn(Locators_Network_DeviceStability.OK);
				
				}
				if(isElementExist(Locators_Network_DeviceStability.Accept_id)) {
					clickBtn(Locators_Network_DeviceStability.Accept_id);
				}
				if(isElementExist(Locators_Network_DeviceStability.OK)) {
					clickBtn(Locators_Network_DeviceStability.OK);
				
				}
				if(isElementExist(Locators_Network_DeviceStability.NEXT)) {
					clickBtn(Locators_Network_DeviceStability.NEXT);
				}
				if(isElementExist(Locators_Network_DeviceStability.OK)) {
					clickBtn(Locators_Network_DeviceStability.OK);
				
				}
				if(isElementExist(Locators_Network_DeviceStability.NEXTBTN)) {
					clickBtn(Locators_Network_DeviceStability.NEXTBTN);
				}
				if(isElementExist(Locators_Network_DeviceStability.OK)) {
					clickBtn(Locators_Network_DeviceStability.OK);
				
				}
				if(isElementExist(Locators_Network_DeviceStability.NO_THANKS)) {
					clickBtn(Locators_Network_DeviceStability.NO_THANKS);
				}
				if(isElementExist(Locators_Network_DeviceStability.OK)) {
					clickBtn(Locators_Network_DeviceStability.OK);
				
				}
				if(isElementExist(Locators_Network_DeviceStability.No_ThanKS)) {
					clickBtn(Locators_Network_DeviceStability.No_ThanKS);
				}
				if(isElementExist(Locators_Network_DeviceStability.OK)) {
					clickBtn(Locators_Network_DeviceStability.OK);
				
				}
		}  catch (NoSuchElementException e) {
			test.log(LogStatus.ERROR,"Error in the locators => clear_ChromePermission_VSW()");
			e.printStackTrace();
		}catch (Exception e) {
			test.log(LogStatus.ERROR,"Exception in functionality=> clear_ChromePermission_VSW()");
			e.printStackTrace();
		}		
	}

	public void add_Picture_O() throws InterruptedException {

		try {
			if (p_b_No.contains("-10.")||p_b_No.contains("-30.")) {
				clickAttachment();
				minWait();
				clickBtn(Locators_Network_DeviceStability.capturePicture_MMS);
				minWait();
				clearAllow();
				clickBtn(Locators_Network_DeviceStability.capturePicture);
				customWait(10000);
				clickBtn(Locators_Network_DeviceStability.done_1);
				minWait();
			} else if(p_b_No.contains("-11.")||p_b_No.contains("-12.")||p_b_No.contains("-18.")||p_b_No.contains("-26.")||p_b_No.contains("-29.")){
				clickAttachment();
				minWait();
				clickBtn(Locators_Network_DeviceStability.capture_pictures_or_video);
				minWait();
				clearAllow();
				clickBtn(Locators_Network_DeviceStability.capture_pictures_or_video);
				minWait();
				clickBtn(Locators_Network_DeviceStability.take_picture_MMS);
				customWait(10000);			
			}
		} catch (Exception e) {
		}		
	}


	public void clickAttachment() throws InterruptedException {
		try {
			minWait();
			clickBtn(Locators_Network_DeviceStability.attachment);
			minWait();
		} catch (Exception e) {
		}
	}

	public void capturePic_MMS_O(){
		try {
			minWait();
			clickBtn(Locators_Network_DeviceStability.button_Attach_SMS);
			minWait();
			clickBtn(Locators_Network_DeviceStability.camera_MMS);
			customWait(2000);
			clickBtn(Locators_Network_DeviceStability.OptionPhoto_Icon);
			customWait(2000);
			clickBtn(Locators_Network_DeviceStability.cameraCapture_Icon);
			minWait();
			clickBtn(Locators_Network_DeviceStability.send_Cam_SMS);
			customWait(3000);
		} catch (Exception e) {

		}

	}


	/*public void captureVideo_MMS_O(){
		try {
			minWait();
			clickBtn(Locators_Network_DeviceStability.button_Attach_SMS);
			minWait();
			clickBtn(Locators_Network_DeviceStability.camera_MMS);
			customWait(2000);
			clickBtn(Locators_Network_DeviceStability.OptionVideo_Icon);
			customWait(2000);
			TouchAction action = new TouchAction(aDriver);
			action.longPress(Locators_Network_DeviceStability.cameraCapture_Icon).release().perform();
			//				clickBtn(Locators_Network_DeviceStability.cameraCapture_Icon);
			minWait();
			clickBtn(Locators_Network_DeviceStability.send_Cam_SMS);
			customWait(3000);
		} catch (Exception e) {

		}

	}
*/
	public void remove_GoogleAcccount_Orio() {
		//remove added google Account if any 
		try {
			scrollToText("Users & accounts");
			//				clickOnAccounts();
			minWait();
			if(isElementExist(Locators_Network_DeviceStability.connectedAccount)) {
				System.out.println("Account is present");
				minWait();
				clickBtn(Locators_Network_DeviceStability.connectedAccount);
				minWait();
				clickBtn(Locators_Network_DeviceStability.REMOVE_ACCOUNT);
				minWait();
				clickBtn(Locators_Network_DeviceStability.REMOVE_ACCOUNT);
				customWait(3000);
			}
			else {
				System.out.println("No Google account present");
			}


		} catch (Exception e) {

		}
	}

	public void navigateTo_AddGoogleAccount_Orio() {
		//navigate to settings option Add google Account
		WebDriverWait wt = new WebDriverWait(aDriver, 60);
		try {
			scrollToText("Users & accounts");
			clickBtn(Locators_Network_DeviceStability.add_Account);
			minWait();
			clickBtn(Locators_Network_DeviceStability.google_Account);
			customWait(1600);
			wt.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[contains(@text,'Checking info')]")));
			minWait();
		} catch (Exception e) {

		}
	}
	public void validate_BluetoothIsEnabled(String refDevName,SoftAssert soft,int n) throws InterruptedException {
		try {
			AndroidElement BT_RefDevice=aDriver.findElementByXPath("//*[@text='"+refDevName+"']");
			if(isElementExist(BT_RefDevice)) {
				check=true;
				APP_LOGS.info("Bluetooth is enabled and paired with referece device successfully at iteration : "+n);
				soft.assertTrue(true, "Bluetooth is enabled and paired with referece device successfully at iteration : "+n);
				test.log(LogStatus.PASS, "Bluetooth is enabled and paired with referece device successfully at iteration : "+n);
			} else  {
				minWait();
				APP_LOGS.info("Bluetooth is not enabled at iteraiton : "+n);	
				soft.fail();
				test.log(LogStatus.FAIL,"Bluetooth is not enabled at iteraiton : "+n);
		
			}
			
		}  catch (NoSuchElementException e) {
			test.log(LogStatus.ERROR,"Error in the locators => validate_BluetoothIsEnabled()");
			e.printStackTrace();
		}catch (Exception e) {
			test.log(LogStatus.ERROR,"Exception in functionality=> validate_BluetoothIsEnabled()");
			e.printStackTrace();
		}

	
	}

	
	
	public void validate_BluetoothIsDisabled(SoftAssert soft,int n) throws InterruptedException {
		try {
			if(isElementExist(Locators_Network_DeviceStability.BT_Disable_Text)) {
				check=true;
				APP_LOGS.info("Bluetooth is disabled successfully at iteration : "+n);
				soft.assertTrue(true, "Bluetooth is disabled successfully at iteration : "+n);
				test.log(LogStatus.PASS, "Bluetooth is disabled successfully at iteration : "+n);
			} else  {
				minWait();
				APP_LOGS.info("Bluetooth is not disabled at iteraiton : "+n);	
				soft.fail();
				test.log(LogStatus.FAIL,"Bluetooth is not disabled at iteraiton : "+n);
		
			}
			
		}  catch (NoSuchElementException e) {
			test.log(LogStatus.ERROR,"Error in the locators => validate_BluetoothIsDisabled()");
			e.printStackTrace();
		}catch (Exception e) {
			test.log(LogStatus.ERROR,"Exception in functionality=> validate_BluetoothIsDisabled()");
			e.printStackTrace();
		}

	
	}

	/*public void add_GoogleAccount(String emailId, String password) {
		WebDriverWait wt = new WebDriverWait(aDriver, 60);
		try {
			minWait();
			enterTextToInputField(Locators_Network_DeviceStability.email_googleAcnt, emailId);
			minWait();
			clickBtn(Locators_Network_DeviceStability.next);
			minWait();
			wt.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@text='Welcome' or @resource-id='profileIdentifier']")));
			minWait();
			enterTextToInputField(Locators_Network_DeviceStability.password_googleAcnt, password);
			customWait(5000);
			clickBtn(Locators_Network_DeviceStability.next);
			customWait(3000);
			scroll() ;
			scroll() ;
			minWait();
			clickBtn(Locators_Network_DeviceStability.skip_);
			//			aDriver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().className(\"android.widget.Button\")).scrollIntoView(new UiSelector().textContains(\"Skip\"))").click();
			minWait();
			//			    scrollToText("Skip");
			if (p_b_No.contains("-11.")||p_b_No.contains("-12.")||p_b_No.contains("-18.")||p_b_No.contains("-26.")||p_b_No.contains("-29.")) {
				try {
					aDriver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().className(\"android.webkit.WebView\")).scrollIntoView(new UiSelector().textContains(\"Yes\"))").click();
					minWait();
				} catch (Exception e) {
				}
			}		
			customWait(1000);
			clickBtn(Locators_Network_DeviceStability.i_agree);
			minWait();
			wt.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[contains(@text,'Checking info')]")));
			minWait();
			clickBtn(Locators_Network_DeviceStability.MORE);
			minWait();
			clickBtn(Locators_Network_DeviceStability.ACCEPTorAGREE);
			customWait(3000);
		} catch (Exception e) {
		}
	}
*/
	public void checkWifiConnected1(String ssid,String pwd) throws InterruptedException {
		try {
			customWait(4000);
			if(isElementExist(Locators_Network_DeviceStability.connectedWIFI1)) {
				String getTxt = Locators_Network_DeviceStability.connectedWIFI1.getText();
				System.out.println("Connected "+getTxt);
				//					test.log(LogStatus.INFO, "Wi-Fi is Connected");
			}
			else {
				System.out.println("Not Connected");
				select_Wifi_SSID(ssid);	
				enterPassword(ssid,pwd);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void install_App(String appName,WebElement element) throws InterruptedException, IOException {

		if(isElementExist(Locators_Network_DeviceStability.account_Page)) {
			clearRecentApps();
			launch_APP(Locators_Network_DeviceStability.PlayStore);
		}

		WebDriverWait wait = new WebDriverWait(aDriver, 60);
		try {
			System.out.println("To Be install "+appName);
			customWait(3000);
			clickBtn(Locators_Network_DeviceStability.google_Play);
			customWait(3000);
			enterTextToInputField(Locators_Network_DeviceStability.search_PlayStore,appName);
			customWait(3000);
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_ENTER);
			customWait(8000);
			System.out.println("Scrolling");
			scrollToElementWithDpadDown(element);
			minWait();

			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@text='INSTALL']")));
			customWait(6000);
			if (isElementExist(Locators_Network_DeviceStability.INSTALL)) {
				minWait();
				clickBtn(Locators_Network_DeviceStability.INSTALL);
				minWait();
				clickBtn(Locators_Network_DeviceStability.ACCEPT);
				minWait();
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@text='UNINSTALL']")));
				minWait();
			} else {
				test.log(LogStatus.ERROR, "App already installed.");
			}
			System.out.println("Installed");
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
		} catch (Exception e) {
			//                e.printStackTrace();
		}
	}

	//		public SoftAssert sf42 = new SoftAssert();
	public void validate_Installed_App(String appName,int n,SoftAssert soft) {

		try {
			boolean check = false;
			clickOnAppList();
			enterTextToInputField(Locators_Network_DeviceStability.searchApps, appName);
			minWait();
			if (isElementExist(Locators_Network_DeviceStability.apkExtractor)) {
				check=true;
				soft.assertTrue(check, "TestCase Valiation is PASS");
				test.log(LogStatus.PASS, "APK installed " + "\" "+ appName +"\"" + " Successfully at iteration: "+n);
			} else {		
				test.log(LogStatus.FAIL,"APK didn't Installed "+ "\" "+ appName + "\"" +"at iteration: "+n);
				soft.fail();
			}
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			minWait();

		} catch (Exception e) {
			soft.fail();
		}

	}


	public void CheckInstalled_App(String appName) throws InterruptedException {
		//Search Application is installed and uninstall

		clickOnAppList();

		enterTextToInputField(Locators_Network_DeviceStability.searchApps, appName);
		minWait();
		System.out.println("Checking....");
		if (isElementExist(Locators_Network_DeviceStability.apkExtractor)) {
			System.out.println("Yes App is Present...");
			if(appName.contains("Tech Support")) {
				System.out.println("Im in Staples");
				launch_APP(Locators_Network_DeviceStability.PlayStore);
				unInstall_App("Tech Support Staples");

			}
			else if(appName.contains("Meeting App")) {
				System.out.println("Im in MAcys");
				launch_APP(Locators_Network_DeviceStability.PlayStore);
				unInstall_App("Macys");
			}

			else if(appName.contains("MCDelivery App")) {
				System.out.println("MCDelivery");
				launch_APP(Locators_Network_DeviceStability.PlayStore);
				unInstall_App("McDonald's");
			}		

			else if(appName.contains("Craigslist")) {
				System.out.println("App for Craigslist");
				launch_APP(Locators_Network_DeviceStability.PlayStore);
				unInstall_App("App for Craigslist");
			}			


			else {
				System.out.println("Im in Gen");
				launch_APP(Locators_Network_DeviceStability.PlayStore);
				unInstall_App(appName);		
			}
			System.out.println("Uninstalled");
		}
	}

	public void unInstall_App(String appName) {

		WebDriverWait wait = new WebDriverWait(aDriver, 60);
		try {
			minWait();
			clickBtn(Locators_Network_DeviceStability.google_Play);
			minWait();
			enterTextToInputField(Locators_Network_DeviceStability.search_PlayStore, appName);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_ENTER);
			customWait(5000);
			if(isElementExist(Locators_Network_DeviceStability.installed_Playstore1)) {
				customWait(3000);
				clickBtn(Locators_Network_DeviceStability.installed_Playstore1);
			}
			if (isElementExist(Locators_Network_DeviceStability.UNINSTALL)) {
				System.out.println("Uninstalling");
				minWait();
				clickBtn(Locators_Network_DeviceStability.UNINSTALL);
				minWait();
				clickBtn(Locators_Network_DeviceStability.OK);
				minWait();
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@text='INSTALL']")));
				minWait();					
			} 
			else if(isElementExist(Locators_Network_DeviceStability.UNINSTALL1)){
				System.out.println("Uninstalling....");
				minWait();
				clickBtn(Locators_Network_DeviceStability.UNINSTALL1);
				minWait();
				clickBtn(Locators_Network_DeviceStability.OK);
				minWait();
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@text='INSTALL']")));
				minWait();
			}

			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
		} catch (Exception e) {

		}
	}

	public void validate_Uninstalled_App(String appName,int n,SoftAssert soft) {

		try {
			boolean check = false;
			clickOnAppList();
			enterTextToInputField(Locators_Network_DeviceStability.searchApps, appName);
			minWait();
			if (isElementExist(Locators_Network_DeviceStability.apkExtractor)) {
				test.log(LogStatus.FAIL,"APK didn't Uninstalled "+ "\" "+ appName + "\"" +"at iteration: "+n);
				soft.fail();
			} else {		

				check=true;
				soft.assertTrue(check, "TestCase Valiation is PASS");
				test.log(LogStatus.PASS, "APK Uninstalled " + "\" "+ appName +"\"" + " Successfully at iteration: "+n);
			}
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			minWait();

		} catch (Exception e) {
			soft.fail();
		}

	}

	public void add_NewContact_withAllFields_O(String contactName, String lastName,String phoneticlastName,
			String phoneticmiddleName,String phoneticfirstName, String nickName,String company, String title,
			String phone,String SIP, String email,String address, String IM,String webSite,String relationship, String notes) {

		try {
			clickBtn(Locators_Network_DeviceStability.add_ContactIcon_O);
			minWait();
			clickBtn(Locators_Network_DeviceStability.contact_SavingTo);
			minWait();
			clickBtn(Locators_Network_DeviceStability.savingTo_Phone);
			minWait();
			//				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			minWait();
			clickBtn(Locators_Network_DeviceStability.more_Fields);
			minWait();
			scrollToAndEnterText("First name", contactName);
			minWait();
			scrollToAndEnterText("Last name", lastName);
			minWait();
			scrollToAndEnterText("Phonetic last name", phoneticlastName);
			minWait();
			scrollToAndEnterText("Phonetic middle name", phoneticmiddleName);
			minWait();
			scrollToAndEnterText("Phonetic first name", phoneticfirstName);
			minWait();
			scrollToAndEnterText("Nickname", nickName);
			minWait();
			scrollToAndEnterText("Company", company);
			minWait();
			scrollToAndEnterText("Title", title);
			minWait();
			scrollToAndEnterText("Phone", phone);
			minWait();
			scrollToAndEnterText("SIP", SIP);
			minWait();
			scrollToAndEnterText("Email", email);
			minWait();
			scrollToAndEnterText("Address", address);
			minWait();
			scrollToAndEnterText("IM", IM);
			minWait();
			scrollToAndEnterText("Website", webSite);
			minWait();
			scrollToAndEnterText("Relationship", relationship);
			minWait();
			scrollToAndEnterText("Notes", notes);
			minWait();		
			Runtime.getRuntime().exec("adb -s "+p_Id+" shell input tap 888 84");
			minWait();
			//			clickBtn(Locators_Network_DeviceStability.SAVE);
			//			minWait();
			clearAllow();
			//				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			minWait();		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	public boolean scrollToAndEnterText(String scrollTo,String stringToEnter) {
		/*
			  Method used to select an element on the page by scrolling the Scroll View/List View
		 */
		boolean check = false;
		try {  
			String scrollable = "new UiScrollable(new UiSelector().scrollable(true))";
			String textElement = ".scrollIntoView(new UiSelector().text(\""+ scrollTo +"\"))";
			aDriver.findElementByAndroidUIAutomator(scrollable+textElement).sendKeys(stringToEnter);
			APP_LOGS.info("Searched application is found sucessfully : ");
			check = true;
			return check;
		}
		catch(NoSuchElementException e) {
			return check;
		}
	}


	/*public void scrollUp() {

		try {
			
			   	org.openqa.selenium.Dimension size =aDriver.manage().window().getSize();
			   	System.out.println(size);
			   	int x=size.getWidth()/2;
			   	int starty=(int)(size.getHeight()*0.60);
			   	int endy=(int)(size.getHeight()*0.10);
			 
			aDriver.swipe(600, 1800, 600, 400, 1000);
			   	
		} catch (Exception e) {   
			e.printStackTrace();
		}
	}
*/
	public void add_NewContact(int enter1forPhone_2forSimMemory,String contactName, String contactNum) throws InterruptedException, IOException {
		/* Method is to Add Contact in Contacts.
		 * Precondition:User should give 1 for first argument to save for Phone Memory and 2 fo rsim Memory.
		 */
		try {
			minWait();
			clickBtn(Locators_Network_DeviceStability.add_ContactIcon_O);
			minWait();
			//				clickBtn(Locators_Network_DeviceStability.contact_SavingTo);
			minWait();
			//				clickBtn(Locators_Network_DeviceStability.add_NewContact);
			minWait();
			if(enter1forPhone_2forSimMemory==1) {
				clickBtn(Locators_Network_DeviceStability.contact_SavingTo);
				minWait();
				clickBtn(Locators_Network_DeviceStability.savingTo_Phone);
				minWait();
				enterTextToInputField(Locators_Network_DeviceStability.name_NewContact, contactName);
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
				minWait();
				enterTextToInputField(Locators_Network_DeviceStability.phone_NewContact, contactNum);
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
				minWait();
				//			
			} else if (enter1forPhone_2forSimMemory==2) {
				clickBtn(Locators_Network_DeviceStability.contact_SavingTo);
				minWait();
				clickBtn(Locators_Network_DeviceStability.savingTo_SIM);
				minWait();
				enterTextToInputField(Locators_Network_DeviceStability.name_NewContact, contactName);
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
				minWait();
				enterTextToInputField(Locators_Network_DeviceStability.phone_NewContact, contactNum);
				//					aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
				minWait();
				//enterTextToInputField(Locators_Network_DeviceStability.phone_NewContact, contactNum);
				//aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
				//minWait();			
			}		
			clickBtn(Locators_Network_DeviceStability.save_Icon);
			minWait();
			clearAllow();
			//				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			minWait();
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
	public void validate_Bluetooth_Disable(AndroidElement e1,SoftAssert soft,int n) throws InterruptedException {
		minWait();
		boolean check1 = false;		
		try {
			if(check1 = e1.isDisplayed()) {
			APP_LOGS.info("Bluetooth disabled is validated successfully at iteration : "+n);
			soft.assertTrue(check, "TestCase Valiation is PASS");
			test.log(LogStatus.PASS, "Disable Bluetooth validated successfully at iteraion : "+n);
		} else  {
			minWait();
			APP_LOGS.info("Bluetooth is enabled");				
			test.log(LogStatus.FAIL,"Disable Bluetooth is UnSuccessful at iteration: "+n);
			soft.fail();
			
			
		}}  catch (NoSuchElementException e) {
			test.log(LogStatus.ERROR,"Error in the locators => validate_Locators1()");
			e.printStackTrace();
		}catch (Exception e) {
			test.log(LogStatus.ERROR,"Exception in functionality=> validate_Locators1()");
			e.printStackTrace();
		}
	}
	public void clickOn_BTSetting()
	{
		try {
			if(wait(Locators_Network_DeviceStability.BT_settings,10)){
			clickBtn(Locators_Network_DeviceStability.BT_settings);}
		}  catch (NoSuchElementException e) {
			test.log(LogStatus.ERROR,"Error in the locators => clickOn_BTSetting()");
			e.printStackTrace();
		}catch (Exception e) {
			test.log(LogStatus.ERROR,"Exception in functionality=> clickOn_BTSetting()");
			e.printStackTrace();
		}
	}
	public void clickOn_BTFORGET()
	{
		try {
			if(wait(Locators_Network_DeviceStability.BT_FORGET,10)) {
			clickBtn(Locators_Network_DeviceStability.BT_FORGET);
			}
		}  catch (NoSuchElementException e) {
			test.log(LogStatus.ERROR,"Error in the locators => clickOn_BT_FORGET()");
			e.printStackTrace();
		}catch (Exception e) {
			test.log(LogStatus.ERROR,"Exception in functionality=> clickOn_BT_FORGET()");
			e.printStackTrace();
		}
	}
	public void enter_WifiPwd(String pwd) throws InterruptedException, IOException {
		/*
		 * enter Password for SSID
		 */
		try {
			if(wait(Locators_Network_DeviceStability.wifi_enterPwd_index,10)) {
			enterTextToInputField(Locators_Network_DeviceStability.wifi_enterPwd_index,pwd);
			clickBtn(Locators_Network_DeviceStability.CONNECT_t1);
			}	
		} catch (NoSuchElementException ne) {
			test.log(LogStatus.ERROR,"Error in the locators => enter_WifiPwd()");
			ne.printStackTrace();
		}catch (Exception en) {
			test.log(LogStatus.ERROR,"Exception in functionality=> enter_WifiPwd()");
			en.printStackTrace();
		}
	}
	public void remove_connectedNtwrk()
	{
		try{
			boolean connected = scrollToText("Connected");
			if(connected == true)
			{
					if(isElementExist(Locators_Network_DeviceStability.FORGET)) {
						clickBtn(Locators_Network_DeviceStability.FORGET);
					}else {
						scrollToText("FORGET");
					}
					wait(Locators_Network_DeviceStability.Wi_Fi,10);
			}
			else if(scrollToText("Check password and try again") == true)
			{
					scrollToTextLongPress("Check password and try again");
					scrollToText("Forget network");
					wait(Locators_Network_DeviceStability.Wi_Fi,10);
			}				
		}catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.ERROR,"Error in locators->remove_connectedNtwrk()");
			e.printStackTrace();
		}catch (Exception e) {
			test.log(LogStatus.ERROR,"Exeption in ->remove_connectedNtwrk()");
			e.printStackTrace();
		}
	}
	
	public boolean scrollToTextLongPress(String text) {
		/*
		Method used to select an element on the page by scrolling the Scroll View/List View
		*/
		
		boolean check = false;
		try {		
			TouchAction ta = new TouchAction(aDriver);
			String scrollable = "new UiScrollable(new UiSelector().scrollable(true))";
			String textElement = ".scrollIntoView(new UiSelector().text(\""+ text +"\"))";
			AndroidElement ele = aDriver.findElementByAndroidUIAutomator(scrollable+textElement);
			ta.longPress(ele).perform();
			APP_LOGS.info("Searched application is found sucessfully : ");
			check = true;
			return check;
	}
		catch(Exception e) {
			return check;
			}
		}



}
