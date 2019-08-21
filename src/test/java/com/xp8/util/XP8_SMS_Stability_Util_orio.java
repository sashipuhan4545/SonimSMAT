package com.xp8.util;

import java.awt.AWTException;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.hibernate.engine.transaction.jta.platform.internal.SynchronizationRegistryBasedSynchronizationStrategy;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.asserts.IAssert;
import org.testng.asserts.SoftAssert;

import com.graphics.gui.JsonFileReaderAndWriter;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.xp5S.util.Locators;

import application.AllQA;
import freemarker.core.CustomAttribute;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.AndroidKeyCode;

public class XP8_SMS_Stability_Util_orio extends BaseUtil {

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
			if (isElementExist(Locators_SMS_DeviceStability.no_Contacts)) {
				APP_LOGS.info("No Contact"); 
				System.out.println("No Contact");
				minWait();
			} else {
				clickBtn(Locators_SMS_DeviceStability.deleteContactOptn1);
				minWait();
				clickBtn(Locators_SMS_DeviceStability.Selection_menu);
				minWait();
				if(isElementExist(Locators_SMS_DeviceStability.ALL_Selection_menu)) {
					clickBtn(Locators_SMS_DeviceStability.ALL_Selection_menu);
					minWait();
				}
				else {
					minWait();
					clickBtn(Locators_SMS_DeviceStability.one_Selection_menu);
				}
				clickBtn(Locators_SMS_DeviceStability.OKBtn1);
				minWait();
				clickBtn(Locators_SMS_DeviceStability.OKBtn);
				APP_LOGS.info("Contacts Deleted");
				minWait();
			}
		}  catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR,"Error in locators->deleteContacts()");
		}catch (Exception e) {
			test.log(LogStatus.ERROR,"Exeption in ->deleteContactS()");
		}
	}

	public void setDefaultAccount_Contact() throws InterruptedException, IOException {

		try {
			clickBtn(Locators_SMS_DeviceStability.moreOptions);
			minWait();
			clickBtn(Locators_SMS_DeviceStability.settings_Contact);
			minWait();
			clickBtn(Locators_SMS_DeviceStability.defaultAccount_NewContacts);
			minWait();
			clickBtn(Locators_SMS_DeviceStability.PHONE_RadioBtn);
			customWait(2000);
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			//clickBtn(Locators_SMS_DeviceStability_ATT.navigateUp);
			//			Runtime.getRuntime().exec("adb -s "+p_Id+" shell input keyevent 4");
			minWait();
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR,"Error in locators->setDefaultAccount_Contact()");
		}catch (Exception e) {
			test.log(LogStatus.ERROR,"Exeption in ->setDefaultAccount_Contact()");
		}		
	}


	public void createContact(String name,String num) throws InterruptedException {
		/*
		 * Create contact
		 */
		try {

			minWait();
			Locators_SMS_DeviceStability.AddtoContact.click();
			minWait();

			if(isElementExist(Locators_SMS_DeviceStability.Choose_Phone)) {
				minWait();
				clickBtn(Locators_SMS_DeviceStability.Choose_Phone);
				customWait(2000);
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
			}
			if(isElementExist(Locators_SMS_DeviceStability.Contacts_Name)) {
				minWait();
			}

			customWait(4000);
			minWait();
			enterTextToInputField(Locators_SMS_DeviceStability.name_NewContact, name);
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			minWait();
			enterTextToInputField(Locators_SMS_DeviceStability.phone_NewContact, num);
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			minWait();
			enterTextToInputField(Locators_SMS_DeviceStability.email_NewContact, "Sonimtech@gmail.com");
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			minWait();
			clickBtn(Locators_SMS_DeviceStability.Save_ConatctIcon1);
			minWait();

			for(int i=1; i<=3; i++) {
				if(isElementExist(Locators_SMS_DeviceStability.AllowOptn))
				{
					clickBtn(Locators_SMS_DeviceStability.AllowOptn);
				} 
			}
		}  catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR,"Error in locators->createContact()");
		}catch (Exception e) {
			test.log(LogStatus.ERROR,"Exeption in ->createContact()");
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
			getcontactNameText=Locators_SMS_DeviceStability.ContactTitle.getText();
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
			String getPhoneNumText=Locators_SMS_DeviceStability.contact_phnNum.getText();

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
			getEmailText=Locators_SMS_DeviceStability.contact_Emailid.getText();
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

		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR,"Error in locators->validateContactCreation()");
		}catch (Exception e) {
			test.log(LogStatus.ERROR,"Exeption in ->validateContactCreation()");
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
			getcontactNameText=Locators_SMS_DeviceStability.ContactTitle.getText();
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
			String getPhoneNumText=Locators_SMS_DeviceStability.contact_phnNum.getText();

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

		}  catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR,"Error in locators->validate_navigatetocallsettings()");
		}catch (Exception e) {
			test.log(LogStatus.ERROR,"Exeption in ->validate_navigatetocallsettings()");
		}
	}
	
	public void precondition_start_msg_vzw() throws InterruptedException {
		try {
			scrollToText("Apps & notifications");
			clickBtn(Locators_SMS_DeviceStability.MessagePlus);
			scrollToText("Message+");
			clickBtn(Locators_SMS_DeviceStability.vzwStorage);
			clickBtn(Locators_SMS_DeviceStability.vzwclrdata);
			clickBtn(Locators_SMS_DeviceStability.vzwok);
			
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR,"Error in locators->precondition_start_msg_vzw()");
		}catch (Exception e) {
			test.log(LogStatus.ERROR,"Exeption in ->precondition_start_msg_vzw()");
		}
	}
	public void create_NewSMS_O(String number, String message,String prynumber) throws InterruptedException {
		/* Method used to create New SMS. */
		//WebDriverWait wait  =new WebDriverWait(aDriver, 60);

	try {
			if(isElementExist(Locators_SMS_DeviceStability.vzw_Start_msg))
			{
				clickBtn(Locators_SMS_DeviceStability.vzw_Start_msg);
				enterTextToInputField(Locators_SMS_DeviceStability.vzw_msg_enterno, prynumber);
				clickBtn(Locators_SMS_DeviceStability.vzw_msg_next);
				customWait(10000);
				//wait.until(ExpectedConditions.visibilityOf(Locators_SMS_DeviceStability.Conversations));
		        System.out.println("coming to create new sms");
			   Runtime.getRuntime().exec("adb -s "+p_Id+" shell input tap 959 1767");
				System.out.println("create new sms page reached");
			if(isElementExist(Locators_SMS_DeviceStability.vzw_msg_popup_txt1))
				{	
				clickBtn(Locators_SMS_DeviceStability.vzw_msg_popup_txt1);
				clickBtn(Locators_SMS_DeviceStability.vzw_msg_allow_txt1);
				}
				enter_Num_ToField_O(number);
				enterText_MessageField_O(message);	
				
			}else{
				Runtime.getRuntime().exec("adb -s "+p_Id+" shell input tap 959 1767");
				enter_Num_ToField_O(number);
				enterText_MessageField_O(message);
			
			
			
			}
		}  catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR,"Error in locators->create_NewSMS_O()");
		}catch (Exception e) {
			test.log(LogStatus.ERROR,"Exeption in ->create_NewSMS_O()");
		}
	}

	public void enterText_MessageField_O(String message) throws InterruptedException {
		/* Methos is Used enter Message into Message Field. */
		try {
			minWait();
			if(isElementExist(Locators_SMS_DeviceStability.messageField_O)) {
				enterTextToInputField(Locators_SMS_DeviceStability.messageField_O, message);
				minWait();
			}
		}  catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR,"Error in locators->enterText_MessageField_O()");
		}catch (Exception e) {
			test.log(LogStatus.ERROR,"Exeption in ->enterText_MessageField_O()");
		}
	}

	public void navigateTo_NewSMS_O() throws InterruptedException {
		/* Used to Navigate to New Message Page. */
		
		
			
			if(isElementExist(Locators_SMS_DeviceStability.vzw_msg_popup_txt1))
			{	
			clickBtn(Locators_SMS_DeviceStability.vzw_msg_popup_txt1);
			clickBtn(Locators_SMS_DeviceStability.vzw_msg_allow_txt1);
			}
	}

	public void enter_Num_ToField_O(String number) throws InterruptedException {
		/* Method is used to Enter Number into TO Field. */
		try {
			minWait();
			enterTextToInputField(Locators_SMS_DeviceStability.TO_Field_O, number);
			customWait(2000);
			System.out.println("Selecting COntact NAme");
			clickBtn(Locators_SMS_DeviceStability.contact_Select);
			//			customWait(2000);			
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR,"Error in locators->enter_Num_ToField_O()");
		}catch (Exception e) {
			test.log(LogStatus.ERROR,"Exeption in ->enter_Num_ToField_O()");
		}
	}


	public void clickOn_Send_O() throws InterruptedException { 
		/* Method used to click on Send Button. */
		try {
			customWait(500);
			clickBtn(Locators_SMS_DeviceStability.send_Icon_O);
			minWait();
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR,"Error in locators->clickOn_Send_O()");
		}catch (Exception e) {
			test.log(LogStatus.ERROR,"Exeption in ->clickOn_Send_O()");
		}
	}

	public void validate_SentMessage_O(int n,SoftAssert soft) throws InterruptedException {
		/* To validate the Sent Message. */
		WebDriverWait wait  =new WebDriverWait(aDriver, 60);

		try {
			wait.until(ExpectedConditions.visibilityOf(Locators_SMS_DeviceStability.Delivered));
			if(isElementExist(Locators_SMS_DeviceStability.Delivered)||isElementExist(Locators_SMS_DeviceStability.not_Sent_Text)) {
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
		}  catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR,"Error in locators->validate_SentMessage_O()");
		}catch (Exception e) {
			test.log(LogStatus.ERROR,"Exeption in ->validate_SentMessage_O()");
		}
	}

	public void delete_SMS_O() throws InterruptedException {
		/* This Method delete the First Thread in the List. */

		try {
			minWait();
			clickBtn(Locators_SMS_DeviceStability.firstSMS_InList_O);
			minWait();
			clickBtn(Locators_SMS_DeviceStability.moreOption_O);
			minWait();
			clickBtn(Locators_SMS_DeviceStability.delete_Messages);
			minWait();
			clickBtn(Locators_SMS_DeviceStability.ALL_Msg);
			minWait();
			clickBtn(Locators_SMS_DeviceStability.delete_Btn);
			minWait();
			clickBtn(Locators_SMS_DeviceStability.Delete);
			minWait();
			//			test.log(LogStatus.INFO, "SMS or MMS is deleted.");
		}  catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR,"Error in locators->delete_SMS_O()");
		}catch (Exception e) {
			test.log(LogStatus.ERROR,"Exeption in ->delete_SMS_O()");
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
			enableSwitch(Locators_SMS_DeviceStability.enabled_Airplane,Locators_SMS_DeviceStability.disabled_Airplane,Locators_SMS_DeviceStability.switch_widget);
			customWait(5000);
			clickBtn(Locators_SMS_DeviceStability.OKBtn);
			minWait();
			disableSwitch(Locators_SMS_DeviceStability.disabled_Airplane,Locators_SMS_DeviceStability.enabled_Airplane,Locators_SMS_DeviceStability.switch_widget );		
			customWait(15000);
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
		}  catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR,"Error in locators->validate_navigatetocallsettings()");
		}catch (Exception e) {
			test.log(LogStatus.ERROR,"Exeption in ->validate_navigatetocallsettings()");
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
		}  catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR,"Error in locators->sendSMS_fromRefDevice()");
		}catch (Exception e) {
			test.log(LogStatus.ERROR,"Exeption in ->sendSMS_fromRefDevice()");
		}
	}

	public void validate_RecievedMessage(int n,SoftAssert soft) throws InterruptedException {
		/* User should navigate to the Messaging APP home Page in the Primary device. */
		WebDriverWait wait  =new WebDriverWait(aDriver, 80);

		try {
			wait.until(ExpectedConditions.visibilityOf(Locators_SMS_DeviceStability.now_Text));	
			customWait(8000);
			if(isElementExist(Locators_SMS_DeviceStability.now_Text)||isElementExist(Locators_SMS_DeviceStability.not_Sent_Text)) {
				
				APP_LOGS.info("Message sent Succeccfully");
				soft.assertTrue(true, "Message sent Succeccfully");
				test.log(LogStatus.PASS, "Message Recieved Successfully at iteration : "+ n);
			} else {
				APP_LOGS.info("SMS didn't sent");
				soft.fail("SMS didn't sent");
				test.log(LogStatus.FAIL, "Message didn't Recieved at iteration : "+ n);
				
			}
		}  catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR,"Error in locators->validate_RecievedMessage()");
		}catch (Exception e) {
			test.log(LogStatus.ERROR,"Exeption in ->validate_RecievedMessage()");
		}
	}


	public void validate_RecievedMessage_O(int n,SoftAssert soft) throws InterruptedException {
		/* User should navigate to the Messaging APP home Page in the Primary device. */

		WebDriverWait wait  =new WebDriverWait(aDriver, 80);

		try {
			wait.until(ExpectedConditions.visibilityOf(Locators_SMS_DeviceStability.firstsmsvzw));
			customWait(8000);
			
			clickBtn(Locators_SMS_DeviceStability.firstSMS_InList_O);
			if(isElementExist(Locators_SMS_DeviceStability.now_Text)||isElementExist(Locators_SMS_DeviceStability.unread_Conv_Messages)||isElementExist(Locators_SMS_DeviceStability.Delivered)||isElementExist(Locators_SMS_DeviceStability.not_Sent_Text)) {
				
				APP_LOGS.info("Message sent Succeccfully");
				System.out.println("Received");
				soft.assertTrue(true, "Message sent Succeccfully");
				test.log(LogStatus.PASS, "Message Recieved Successfully at iteration "+ n);
			} else {
				APP_LOGS.info("SMS didn't sent");
				soft.fail("sms didn't sent");
				test.log(LogStatus.FAIL,"Message didn't Recieved at iteration "+n);
				
			}
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
		}  catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR,"Error in locators->validate_RecievedMessage_O()");
		}catch (Exception e) {
			test.log(LogStatus.ERROR,"Exeption in ->validate_RecievedMessage_O()");
		}
	}
	public void validate_RecievedMessage_SL(int n,SoftAssert soft) throws InterruptedException {
		/* User should navigate to the Messaging APP home Page in the Primary device. */

		WebDriverWait wait  =new WebDriverWait(aDriver, 80);

		try {
			wait.until(ExpectedConditions.visibilityOf(multi_Loc_Strategy(Locators_SMS_DeviceStability.validatingreceived_id_sl, Locators_SMS_DeviceStability.validatingreceived_sl, Locators_SMS_DeviceStability.validatingreceived_sl_indx, Locators_SMS_DeviceStability.validatingreceivedcls_sl, Locators_SMS_DeviceStability.validatingreceivedid_sl, 1643, 129)));
			customWait(8000);
			/*aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			minWait();*/
			clickBtn(multi_Loc_Strategy(Locators_SMS_DeviceStability.firstsmsinlist_sl, Locators_SMS_DeviceStability.firstsmsinlist_sl_indx, Locators_SMS_DeviceStability.firstsmsinlist_text_sl, Locators_SMS_DeviceStability.firstsmsinlistcls_sl, Locators_SMS_DeviceStability.firstsmsinlistdes_sl, 605, 752));
			minWait();
			if(isElementExist(multi_Loc_Strategy(Locators_SMS_DeviceStability.valsendmsg_sl, Locators_SMS_DeviceStability.valsendmsg_sl_indx, Locators_SMS_DeviceStability.valsendmsg_text_sl, Locators_SMS_DeviceStability.valsendmsgcls_sl, Locators_SMS_DeviceStability.valsendmsgdes_sl, 1660, 876))||isElementExist(multi_Loc_Strategy(Locators_SMS_DeviceStability.valnotsendmsg_sl, Locators_SMS_DeviceStability.valnotsendmsg_sl_indx, Locators_SMS_DeviceStability.valnotsendmsg_text_sl, Locators_SMS_DeviceStability.valnotsendmsgcls_sl, Locators_SMS_DeviceStability.valnotsendmsgdes_sl, 1735, 1104)))
			{
				
				
				APP_LOGS.info("Message sent Succeccfully");
				System.out.println("Received");
				soft.assertTrue(true, "Message sent Succeccfully");
				test.log(LogStatus.PASS, "Message Recieved Successfully at iteration "+ n);
			} else {
				APP_LOGS.info("SMS didn't sent");
				soft.fail("SMS didn't sent");
				test.log(LogStatus.FAIL,"Message didn't Recieved at iteration "+n);
				
			}
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
		}  catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR,"Error in locators->validate_RecievedMessage_SL()");
		}catch (Exception e) {
			test.log(LogStatus.ERROR,"Exeption in ->validate_RecievedMessage_SL()");
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
			clickBtn(Locators_SMS_DeviceStability.Search_ConatctIcon);
			customWait(2000);
			enterTextToInputField(Locators_SMS_DeviceStability.findContacts_O, name);
			minWait();
			clickBtn(Locators_SMS_DeviceStability.longpress_FirstContact_O);
			minWait();
		}  catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR,"Error in locators->validate_navigatetocallsettings()");
		}catch (Exception e) {
			test.log(LogStatus.ERROR,"Exeption in ->validate_navigatetocallsettings()");
		}
	}
	public void initiateCall() throws InterruptedException {
		/*
		 * initiate a call from Saved contacts
		 */
		try {
			/*minWait();
			clickBtn(Locators_SMS_DeviceStability.SavedContact);
			minWait();
			clickBtn(Locators_SMS_DeviceStability.contact_phnNum);
			minWait();*/
			customWait(4000);
			Runtime.getRuntime().exec("adb -s "+p_Id+" shell am start -a android.intent.action.CALL -d tel:"+refNum);
			Thread.sleep(10000);
		}  catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR,"Error in locators->validate_navigatetocallsettings()");
		}catch (Exception e) {
			test.log(LogStatus.ERROR,"Exeption in ->validate_navigatetocallsettings()");
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
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR,"Error in locators->validate_navigatetocallsettings()");
		}catch (Exception e) {
			test.log(LogStatus.ERROR,"Exeption in ->validate_navigatetocallsettings()");
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
		}  catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR,"Error in locators->validate_navigatetocallsettings()");
		}catch (Exception e) {
			test.log(LogStatus.ERROR,"Exeption in ->validate_navigatetocallsettings()");
		}
	}

	public void selectPage(WebElement selectpage) throws InterruptedException {
		try {
			minWait();	
			clickBtn(selectpage);
		}
		 catch (org.openqa.selenium.NoSuchElementException e) {

				test.log(LogStatus.ERROR,"Error in locators->validate_navigatetocallsettings()");
			}catch (Exception e) {
				test.log(LogStatus.ERROR,"Exeption in ->validate_navigatetocallsettings()");
			}
	}

	public void callHistory() throws InterruptedException, IOException {
		/*
		 * call save contact
		 */
		try {
			minWait();
			if(isElementExist(Locators_SMS_DeviceStability.MoreOptions)) {
				clickBtn(Locators_SMS_DeviceStability.MoreOptions);
				minWait();		
			}
			else {
				clickBtn(Locators_SMS_DeviceStability.MoreOptions1);
				minWait();		
			}
			customWait(1000);
			clickBtn(Locators_SMS_DeviceStability.callHistory_O);
			minWait();			
			clickBtn(Locators_SMS_DeviceStability.Call_Contact);
			minWait();
			APP_LOGS.info("initiated a call");
			minWait();
			customWait(2000);
		}  catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR,"Error in locators->validate_navigatetocallsettings()");
		}catch (Exception e) {
			test.log(LogStatus.ERROR,"Exeption in ->validate_navigatetocallsettings()");
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
			enableSwitch(Locators_SMS_DeviceStability.enabled_Airplane,Locators_SMS_DeviceStability.disabled_Airplane,Locators_SMS_DeviceStability.switch_widget);
			customWait(5000);
			disableSwitch(Locators_SMS_DeviceStability.disabled_Airplane,Locators_SMS_DeviceStability.enabled_Airplane,Locators_SMS_DeviceStability.switch_widget );		
			customWait(15000);
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
		}  catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR,"Error in locators->validate_navigatetocallsettings()");
		}catch (Exception e) {
			test.log(LogStatus.ERROR,"Exeption in ->validate_navigatetocallsettings()");
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
		 catch (org.openqa.selenium.NoSuchElementException e) {

				test.log(LogStatus.ERROR,"Error in locators->validate_navigatetocallsettings()");
			}catch (Exception e) {
				test.log(LogStatus.ERROR,"Exeption in ->validate_navigatetocallsettings()");
			}
	}


	public void selectWIFIOptn() throws InterruptedException{
		/*
		 * Select Wifi option from settings List
		 */
		SoftAssert Sa = new SoftAssert();
		try {
			customWait(1000);
			//					ScrollToElement(Locators_SMS_DeviceStability.SettingsList, "Wi-Fi");
			customWait(2000);
			clickBtn(Locators_SMS_DeviceStability.wifi);
			minWait();
			APP_LOGS.info("Selected WIFI option");
		}  catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR,"Error in locators->validate_navigatetocallsettings()");
		}catch (Exception e) {
			test.log(LogStatus.ERROR,"Exeption in ->validate_navigatetocallsettings()");
		}
	}


	public void selectSSIDwifi() throws InterruptedException {
		/*
		 * Select IDC wifi which is available
		 */
		SoftAssert Sa = new SoftAssert();
		try {
			customWait(4000);
			for(int i=1; i<=50; i++) {
				if(isElementExist(Locators_SMS_DeviceStability.wifi_IDC)) {
					customWait(2000);
					clickBtn(Locators_SMS_DeviceStability.wifi_IDC);
					APP_LOGS.info("IDC available secured wifi is Selected");
					System.out.println("IDC available secured wifi is Selected");
					break;
				}
				else if(isElementExist(Locators_SMS_DeviceStability.wifi_Dellas)) {
					minWait();
					clickBtn(Locators_SMS_DeviceStability.wifi_Dellas);
					APP_LOGS.info("Dellas available secured wifi is Selected");
					break;
				}
				else if(isElementExist(Locators_SMS_DeviceStability.wifi_Cannada)) {
					minWait();
					clickBtn(Locators_SMS_DeviceStability.wifi_Cannada);
					APP_LOGS.info("Cannada available secured wifi is Selected");
					break;
				}
				else {
					minWait();
					aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
					continue;
				}
			}
		}  catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR,"Error in locators->validate_navigatetocallsettings()");
		}catch (Exception e) {
			test.log(LogStatus.ERROR,"Exeption in ->validate_navigatetocallsettings()");
		}
	}


	public void disconnectSSIDifConnected() throws InterruptedException {
		/*
		 * disconnect wifi[SSID]if IDC wifi connected
		 */
		SoftAssert Sa = new SoftAssert();
		try {
			selectSSIDwifi();
			customWait(4000);
			clickBtn(Locators_SMS_DeviceStability.wifi_IDC_ForgetBtn);
			APP_LOGS.info("IDC available secured wifi is disconnected");
			System.out.println("Disconnected");
			customWait(4000);
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR,"Error in locators->validate_navigatetocallsettings()");
		}catch (Exception e) {
			test.log(LogStatus.ERROR,"Exeption in ->validate_navigatetocallsettings()");
		}
	}


	public void validate_And_BrowseIn_Chrome1(String url, AndroidElement autoSuggestion, AndroidElement expectedElement, AndroidElement expectedElement1) throws InterruptedException, IOException {
		/* 
		 * Validates MobileData enabality by launching the Chrome & browsing google page.
		 */
		SoftAssert  sf = new SoftAssert();
		try {
			customWait(2000);
			enterTextToInputField(Locators_SMS_DeviceStability.urlBar_Chrome, url);
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
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR,"Error in locators->validate_navigatetocallsettings()");
		}catch (Exception e) {
			test.log(LogStatus.ERROR,"Exeption in ->validate_navigatetocallsettings()");
		}
	}

	public void selectRefresh() throws InterruptedException {
		/*
		 * Refresh the List SSID 
		 */		
		try {
			minWait();
			//				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_MENU);
			clickBtn(Locators_SMS_DeviceStability.MoreOptions);
			minWait();
			clickBtn(Locators_SMS_DeviceStability.RefreshOptn);
			minWait();
			//refresh btn
			customWait(5000);
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR,"Error in locators->validate_navigatetocallsettings()");
		}catch (Exception e) {
			test.log(LogStatus.ERROR,"Exeption in ->validate_navigatetocallsettings()");
		}	
	}

	public void enterPassword() throws InterruptedException, IOException {
		/*
		 * enter Password for SSID
		 */
		try {
			minWait();
			if(isElementExist(Locators_SMS_DeviceStability.SSIDTxt)) {
				String getSSIDTitle = Locators_SMS_DeviceStability.SSIDTxt.getText();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
				minWait();
				changeToNumberMode();
				customWait(2000);
				if(getSSIDTitle.equalsIgnoreCase("IDCSONWAP")) {
					minWait();
					clickBtn(Locators_SMS_DeviceStability.wifi_IDC_Psswd);
					customWait(4000);
					//					enterTextToInputField(Locators_SMS_DeviceStability.wifi_IDC_Psswd,"1dcS0n1md0tc0MbLr");
					Runtime.getRuntime().exec("adb -s "+p_Id+" shell input text 1dcS0n1md0tc0MbLr");		
					customWait(3000);	
				}
				minWait();
				String psswrd = Locators_SMS_DeviceStability.wifi_IDC_Psswd.getText();
				System.out.println(psswrd);
				customWait(1000);
				clickBtn(Locators_SMS_DeviceStability.wifi_IDC_ConnectBtn);
				customWait(3000);
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR,"Error in locators->validate_navigatetocallsettings()");
		}catch (Exception e) {
			test.log(LogStatus.ERROR,"Exeption in ->validate_navigatetocallsettings()");
		}
	}

	public void changeToNumberMode() throws InterruptedException {

		try {
			minWait();
			Locators_SMS_DeviceStability.wifi_IDC_Psswd.sendKeys("123");
			customWait(1500);
			String text = Locators_SMS_DeviceStability.wifi_IDC_Psswd.getText();
			System.out.println(text);
			if(!text.equals("123")) {
				for (int i = 0; i < 3; i++) {
					aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_POUND);
					minWait();
				}
			}
			Locators_SMS_DeviceStability.wifi_IDC_Psswd.clear();
		}catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR,"Error in locators->validate_navigatetocallsettings()");
		}catch (Exception e) {
			test.log(LogStatus.ERROR,"Exeption in ->validate_navigatetocallsettings()");
		}
	}

	public void changeToNumberModeSMS() throws InterruptedException {

		try {
			minWait();
			Locators_SMS_DeviceStability.wifi_IDC_Psswd.sendKeys("123");
			customWait(1500);
			String text = Locators_SMS_DeviceStability.wifi_IDC_Psswd.getText();
			System.out.println(text);
			if(!text.equals("123")) {
				for (int i = 0; i < 3; i++) {
					aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_POUND);
					minWait();
				}
			}
			Locators_SMS_DeviceStability.wifi_IDC_Psswd.clear();
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR,"Error in locators->validate_navigatetocallsettings()");
		}catch (Exception e) {
			test.log(LogStatus.ERROR,"Exeption in ->validate_navigatetocallsettings()");
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
			String getTxt = Locators_SMS_DeviceStability.connectedWIFI1.getText();
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
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR,"Error in locators->validate_navigatetocallsettings()");
		}catch (Exception e) {
			test.log(LogStatus.ERROR,"Exeption in ->validate_navigatetocallsettings()");
		}
	}


	public void validate_ON_OFF_WiFiFeature(WebElement feature,int n) throws InterruptedException, IOException{
		/*
		 * validate Turn On and OFF wifi toggle button	
		 */
		SoftAssert S1 = new SoftAssert();
		try {	   
			customWait(2000);
			enableFeature(Locators_SMS_DeviceStability.enabled,Locators_SMS_DeviceStability.disabled,Locators_SMS_DeviceStability.switch_Title);
			minWait();

			if(isElementExist(Locators_SMS_DeviceStability.enabled)) {
				check1 = true;
				APP_LOGS.info(" check1");
			}
			customWait(3000);
			disableFeature(Locators_SMS_DeviceStability.enabled,Locators_SMS_DeviceStability.disabled);
			if(isElementExist(Locators_SMS_DeviceStability.disabled)) {
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
		}catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR,"Error in locators->validate_navigatetocallsettings()");
		}catch (Exception e) {
			test.log(LogStatus.ERROR,"Exeption in ->validate_navigatetocallsettings()");
		}

	}

	public void acceptDefault() throws InterruptedException {
		/*
		 * Accept default Options
		 */

		try {
			customWait(2000);
			if(isElementExist(Locators_SMS_DeviceStability.AccptBtn)){
				Locators_SMS_DeviceStability.AccptBtn.click();
			}
			customWait(4000);
			if(isElementExist(Locators_SMS_DeviceStability.NextIcon)){
				Locators_SMS_DeviceStability.NextIcon.click();
			}
			customWait(4000);
			if(isElementExist(Locators_SMS_DeviceStability.NothanksBtn)){
				Locators_SMS_DeviceStability.NothanksBtn.click();
			}
			customWait(4000);
		}catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR,"Error in locators->validate_navigatetocallsettings()");
		}catch (Exception e) {
			test.log(LogStatus.ERROR,"Exeption in ->validate_navigatetocallsettings()");
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
			/*	clickBtn(Locators_SMS_DeviceStability.DefaultUrlTxt);
					enterTextToInputField(Locators_SMS_DeviceStability.DefaultUrlTxt,newurl);
			 */
			Runtime.getRuntime().exec("adb -s "+p_Id+" shell am start -a android.intent.action.VIEW -d http:/"+newurl);
			Thread.sleep(2000);
			APP_LOGS.info(" URl is entered is sucessful.");
			customWait(3000);
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_ENTER);
			minWait();
			APP_LOGS.info("Search click is sucessful.");
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR,"Error in locators->validate_navigatetocallsettings()");
		}catch (Exception e) {
			test.log(LogStatus.ERROR,"Exeption in ->validate_navigatetocallsettings()");
		}
		Sa.assertAll();
	}


	public void validateUrlEntered(int n,SoftAssert soft)
	{
		/*
		 * validate the website page is loaded via adb logs
		 */
		SoftAssert S2= new SoftAssert();
		try { 
			customWait(20000);
			if(isElementExist(Locators_SMS_DeviceStability.networkNotAvailable)|isElementExist(Locators_Stability.WebPageBlocked)){
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
		catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR,"Error in locators->validate_navigatetocallsettings()");
		}catch (Exception e) {
			test.log(LogStatus.ERROR,"Exeption in ->validate_navigatetocallsettings()");
		}	}


	public static void imsLogs(String fileName) throws AWTException, InterruptedException {

		String path = System.getProperty("user.dir");
		customWait(1000);
		try {
			Runtime.getRuntime().exec("cmd /C \"adb logcat -b all -v time>"+path+"\\src\\test\\resources\\adbLogs\\"+fileName+".txt\"");
			Thread.sleep(1000);
		}
		catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR,"Error in locators->validate_navigatetocallsettings()");
		}catch (Exception e) {
			test.log(LogStatus.ERROR,"Exeption in ->validate_navigatetocallsettings()");
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
				if (isElementExist(Locators_SMS_DeviceStability.firstSMS_InList)) {
					minWait();
					TouchAction touchaction = new TouchAction(aDriver);
					touchaction.longPress(Locators_SMS_DeviceStability.firstSMS_InList).perform().release();
					minWait();
					clickBtn(Locators_SMS_DeviceStability.delete_Icon_SMS);
					minWait();
					if(isElementExist(Locators_SMS_DeviceStability.delete_Confirm)) {
						clickBtn(Locators_SMS_DeviceStability.delete_Confirm);
						minWait();
					}
					else {
						clickBtn(Locators_SMS_DeviceStability.delete_moreOption);
						minWait();
					}
				} 
				else if(isElementExist(Locators_SMS_DeviceStability.first_sms_Thread)){
					minWait();
					TouchAction touchaction = new TouchAction(aDriver);
					touchaction.longPress(Locators_SMS_DeviceStability.first_sms_Thread).perform().release();
					minWait();
					clickBtn(Locators_SMS_DeviceStability.delete_Icon);
					minWait();
					if(isElementExist(Locators_SMS_DeviceStability.delete_Confirm)) {
						clickBtn(Locators_SMS_DeviceStability.delete_Confirm);
						minWait();
					}
					else {
						clickBtn(Locators_SMS_DeviceStability.delete_moreOption);
						minWait();
					}
				}
				else {

					Reporter.log("No Threads Found");
					break;
				}
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR,"Error in locators->validate_navigatetocallsettings()");
		}catch (Exception e) {
			test.log(LogStatus.ERROR,"Exeption in ->validate_navigatetocallsettings()");
		}
	}


	//======================================================================================================

	public void navigateTo_NewMessage() throws InterruptedException, IOException {
		
		try {
			//				launch_an_app("messaging");
			minWait();
			//				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_MENU);
			minWait();
			if(isElementExist(Locators_SMS_DeviceStability.add_NewSMS)) {
				clickBtn(Locators_SMS_DeviceStability.add_NewSMS);
				minWait();
			}

			if(isElementExist(Locators_SMS_DeviceStability.new_Message_Icon)) {
				clickBtn(Locators_SMS_DeviceStability.new_Message_Icon);
				minWait();
			}

		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR,"Error in locators->validate_navigatetocallsettings()");
		}catch (Exception e) {
			test.log(LogStatus.ERROR,"Exeption in ->validate_navigatetocallsettings()");
		}
		}
		
	

	public void create_NewSMS(String number, String message) throws InterruptedException, IOException {
		/* Method used to create New SMS. */
		navigateTo_NewSMS_ATT();
		try {
			enter_Num_ToField(number);
			System.out.println("Enter Text");
			enterText_MessageField(message);
		}catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR,"Error in locators->create_NewSMS()");
		}catch (Exception e) {
			test.log(LogStatus.ERROR,"Exeption in ->create_NewSMS()");
		}
	}
	public void create_NewSMS_SL(String number, String message) throws InterruptedException, IOException {
		/* Method used to create New SMS. */
		navigateTo_NewSMS_SL();
		try {
			enter_Num_ToField_SL(number);
			System.out.println("Enter Text");
			enterText_MessageField_SL(message);
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR,"Error in locators-> create_NewSMS_SL()");
		}catch (Exception e) {
			test.log(LogStatus.ERROR,"Exeption in -> create_NewSMS_SL()");
		}
	}

	public void validate_CharacterAndPageNumber(WebElement charAndPageNum,String expectedcharAndPageNum, int n,SoftAssert sa) throws InterruptedException {
		/*
		 * This Meathod is to validate Characters and Page Number
		 * Precondition : User should be in Message window with Typed Message
		 */
		
		try {
			minWait();
			String charAndPageNum1 = null;
			if(isElementExist(Locators_SMS_DeviceStability.zero_Characters_FirstPage)) {
				System.out.println("Im out");
				charAndPageNum1 = charAndPageNum.getText();
			}
			else if(isElementExist(Locators_SMS_DeviceStability.zero_Characters_FirstPage1)) {
				System.out.println("Im in");
				charAndPageNum1 = Locators_SMS_DeviceStability.zero_Characters_FirstPage1.getText();
			}

			if (check=charAndPageNum1.equals(expectedcharAndPageNum)) {
				APP_LOGS.info("Character and Page Number Displayed is correct");
				sa.assertTrue(check, "Validation is Pass");
			} else {
				APP_LOGS.info("Character and Page Number Displayed is NOT correct");
				test.log(LogStatus.ERROR, "Error in validating the Number of characters and Page Number at iteration: "+ n);


			} 
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR,"Error in locators->validate_navigatetocallsettings()");
		}catch (Exception e) {
			test.log(LogStatus.ERROR,"Exeption in ->validate_navigatetocallsettings()");
		}
	}	

	public void validate_CharacterAndPageNumber_O(WebElement charAndPageNum,String expectedcharAndPageNum,int n,SoftAssert sa) throws InterruptedException {
		/*
		 * This Meathod is to validate Characters and Page Number
		 * Precondition : User should be in Message window with Typed Message
		 */
		
		try {
			minWait();
			String charAndPageNum1 = null;
			if(isElementExist(Locators_SMS_DeviceStability.zero_Characters_FirstPage_O)) {
				System.out.println("Im out");
				charAndPageNum1 = charAndPageNum.getText();
			}
			System.out.println(charAndPageNum1);
			if (check=charAndPageNum1.equals(expectedcharAndPageNum)) {
				APP_LOGS.info("Character and Page Number Displayed is correct");
				sa.assertTrue(check, "Validation is Pass");
			} else {
				APP_LOGS.info("Character and Page Number Displayed is NOT correct");
				test.log(LogStatus.ERROR, "Error in validating the Number of characters and Page Number at iteration: "+ n);

			} 
		}catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR,"Error in locators->validate_navigatetocallsettings()");
		}catch (Exception e) {
			test.log(LogStatus.ERROR,"Exeption in ->validate_navigatetocallsettings()");
		}
		
	}	

	public void create_NewSMS1(String number, String message) throws InterruptedException {
		/* Method used to create New SMS. */
		navigateTo_NewSMS1();
		try {
			enter_Num_ToField1(number);
			enterText_MessageField1(message);
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR,"Error in locators->validate_navigatetocallsettings()");
		}catch (Exception e) {
			test.log(LogStatus.ERROR,"Exeption in ->validate_navigatetocallsettings()");
		}
	}

	public void navigateTo_NewSMS_ATT() throws InterruptedException {
		/* Used to Navigate to New Message Page. */
		try {
			clickBtn(multi_Loc_Strategy(Locators_SMS_DeviceStability.add_NewSMS_ATT, Locators_SMS_DeviceStability.add_NewSMS_ATT_cls, Locators_SMS_DeviceStability.add_NewSMS_ATT_id, Locators_SMS_DeviceStability.add_NewSMS_ATT_indx, Locators_SMS_DeviceStability.add_NewSMS_ATT_xpath, 1735, 959));
			minWait();
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR,"Error in locators->navigateTo_NewSMS_ATT()");
		}catch (Exception e) {
			test.log(LogStatus.ERROR,"Exeption in ->navigateTo_NewSMS_ATT()");
		}
	}
	public void navigateTo_NewSMS_SL() throws InterruptedException {
		/* Used to Navigate to New Message Page. */
		try {
			clickBtn(multi_Loc_Strategy(Locators_SMS_DeviceStability.add_NewSMS_sl, Locators_SMS_DeviceStability.add_NewSMS_sl_description, Locators_SMS_DeviceStability.add_NewSMS_sl_index, Locators_SMS_DeviceStability.add_NewSMS_sl_text, Locators_SMS_DeviceStability.add_NewSMS_sl_uides, 775, 1810));
			minWait();
		}catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR,"Error in locators->navigateTo_NewSMS_SL()");
		}catch (Exception e) {
			test.log(LogStatus.ERROR,"Exeption in ->navigateTo_NewSMS_SL()");
		}
	}
	public void delete_SMS1() throws InterruptedException {
		/* This Method delete the First Thread in the List. */

		try {
			minWait();
			clickBtn(Locators_SMS_DeviceStability.firstSMS_InList1);
			minWait();
			clickBtn(Locators_SMS_DeviceStability.moreOptions);
			minWait();
			clickBtn(Locators_SMS_DeviceStability.delete_moreOption);
			minWait();
			clickBtn(Locators_SMS_DeviceStability.delete_Confirm);
			minWait();
			//				test.log(LogStatus.INFO, "SMS or MMS is deleted.");
		}catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR,"Error in locators->validate_navigatetocallsettings()");
		}catch (Exception e) {
			test.log(LogStatus.ERROR,"Exeption in ->validate_navigatetocallsettings()");
		}
	}

	public void delete_SMS() throws InterruptedException {
		/* This Method delete the First Thread in the List. */

		try {
			System.out.println("IN Delete");
			minWait();
			if (isElementExist(Locators_SMS_DeviceStability.firstSMS_InList)) {
				clickBtn(Locators_SMS_DeviceStability.firstSMS_InList);
				minWait();
				clickBtn(Locators_SMS_DeviceStability.moreOptions);
				minWait();
				clickBtn(Locators_SMS_DeviceStability.delete_Thread);
				minWait();
				clickBtn(Locators_SMS_DeviceStability.delete_Confirm);
				minWait();
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR,"Error in locators->delete_SMS()");
		}catch (Exception e) {
			test.log(LogStatus.ERROR,"Exeption in ->delete_SMS()");
		}
	}
	public void delete_SMS_SL() throws InterruptedException {
		/* This Method delete the First Thread in the List. */

		try {
			System.out.println("IN Delete");
			minWait();
			if (isElementExist(multi_Loc_Strategy(Locators_SMS_DeviceStability.firstsmsinlist_sl, Locators_SMS_DeviceStability.firstsmsinlist_sl_indx, Locators_SMS_DeviceStability.firstsmsinlist_text_sl, Locators_SMS_DeviceStability.firstsmsinlistcls_sl, Locators_SMS_DeviceStability.firstsmsinlistdes_sl, 1712, 1896))) {
				clickBtn(multi_Loc_Strategy(Locators_SMS_DeviceStability.firstsmsinlist_sl, Locators_SMS_DeviceStability.firstsmsinlist_sl_indx, Locators_SMS_DeviceStability.firstsmsinlist_text_sl, Locators_SMS_DeviceStability.firstsmsinlistcls_sl, Locators_SMS_DeviceStability.firstsmsinlistdes_sl, 605, 752));
				minWait();
				clickBtn(Locators_SMS_DeviceStability.moreoptions_des_sl);
				minWait();
				clickBtn(multi_Loc_Strategy(Locators_SMS_DeviceStability.delete_sl, Locators_SMS_DeviceStability.delete_sl_indx, Locators_SMS_DeviceStability.delete_id_sl, Locators_SMS_DeviceStability.deletecls_sl, Locators_SMS_DeviceStability.deletetxt_sl, 1735, 412));
				minWait();
				clickBtn(multi_Loc_Strategy(Locators_SMS_DeviceStability.cnfrmdelete_id_sl, Locators_SMS_DeviceStability.cnfrmdelete_sl,Locators_SMS_DeviceStability.cnfrmdelete_sl_indx, Locators_SMS_DeviceStability.cnfrmdeletecls_sl, Locators_SMS_DeviceStability.cnfrmdeletetxt_sl, 1712, 1896));
				minWait();
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR,"Error in locators->delete_SMS_SL()");
		}catch (Exception e) {
			test.log(LogStatus.ERROR,"Exeption in ->delete_SMS_SL()");
		}
	}

	public void clickOn_Send1() throws InterruptedException { 
		/* Method used to click on Send Button. */
		try {
			customWait(500);
			clickBtn(Locators_SMS_DeviceStability.send_Icon1);
			minWait();
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR,"Error in locators->clickOn_Send1()");
		}catch (Exception e) {
			test.log(LogStatus.ERROR,"Exeption in ->clickOn_Send1()");
		}
	}


	public void navigateTo_NewSMS1() throws InterruptedException {
		/* Used to Navigate to New Message Page. */
		try {
			clickBtn(Locators_SMS_DeviceStability.add_NewSMS1);
			minWait();
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR,"Error in locators->navigateTo_NewSMS1()");
		}catch (Exception e) {
			test.log(LogStatus.ERROR,"Exeption in ->navigateTo_NewSMS1()");
		}
	}
	public void enterText_MessageField1(String message) throws InterruptedException {
		/* Methos is Used enter Message into Message Field. */
		try {
			minWait();
			enterTextToInputField(Locators_SMS_DeviceStability.messageField1, message);
			minWait();
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR,"Error in locators->enterText_MessageField1()");
		}catch (Exception e) {
			test.log(LogStatus.ERROR,"Exeption in ->enterText_MessageField1()");
		}
	}

	public void enter_Num_ToField1(String number) throws InterruptedException {
		/* Method is used to Enter Number into TO Field. */
		try {
			minWait();
			enterTextToInputField(Locators_SMS_DeviceStability.TO_Field1, number);
			customWait(2000);
			clickBtn(Locators_SMS_DeviceStability.contactPicker);
			customWait(2000);			
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR,"Error in locators->enter_Num_ToField1()");
		}catch (Exception e) {
			test.log(LogStatus.ERROR,"Exeption in ->enter_Num_ToField1()");
		}
	}

	public void enter_Num_ToField_SL(String number) throws InterruptedException {
		/* Method is used to Enter Number into TO Field. */
		try {
			minWait();
			enterTextToInputField(multi_Loc_Strategy(Locators_SMS_DeviceStability.TO_Field_enter_sl, Locators_SMS_DeviceStability.toField_NewMessage_sl, Locators_SMS_DeviceStability.typeno_sl, Locators_SMS_DeviceStability.typeno_sl_indx, Locators_SMS_DeviceStability.tofield_text_sl, 1695, 1196), number);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.ENTER);
			minWait();
			
			
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR,"Error in locators->enter_Num_ToField_SL()");
		}catch (Exception e) {
			test.log(LogStatus.ERROR,"Exeption in ->enter_Num_ToField_SL()");
		}
	}
	public void enter_Num_ToField(String number) throws InterruptedException {
		/* Method is used to Enter Number into TO Field. */
		try {
			minWait();
			enterTextToInputField(multi_Loc_Strategy(Locators_SMS_DeviceStability.TO_Field_att, Locators_SMS_DeviceStability.TO_Field_indx_att, Locators_SMS_DeviceStability.TO_Field_Text1_att, Locators_SMS_DeviceStability.TO_Field_Txt1_att, Locators_SMS_DeviceStability.TO_Fieldid_att, 162, 197), number);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			minWait();
			//aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR,"Error in locators->enter_Num_ToField()");
		}catch (Exception e) {
			test.log(LogStatus.ERROR,"Exeption in ->enter_Num_ToField()");
		}
	}

	public void enterText_MessageField(String message) throws InterruptedException {
		/* Methos is Used enter Message into Message Field. */
		try {
			customWait(2000);
			enterTextToInputField(multi_Loc_Strategy(Locators_SMS_DeviceStability.type_Messageid_att, Locators_SMS_DeviceStability.type_Message_att, Locators_SMS_DeviceStability.type_Message_indx_att, Locators_SMS_DeviceStability.type_Messagetxt_att, Locators_SMS_DeviceStability.typemessageField_att, 234, 1120), message);
			minWait();
		}catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR,"Error in locators->enterText_MessageField()");
		}catch (Exception e) {
			test.log(LogStatus.ERROR,"Exeption in ->enterText_MessageField()");
		}
	}
	public void enterText_MessageField_SL(String message) throws InterruptedException {
		/* Methos is Used enter Message into Message Field. */
		try {
			customWait(2000);
			enterTextToInputField(multi_Loc_Strategy(Locators_SMS_DeviceStability.typemsg_sl, Locators_SMS_DeviceStability.typemsg_sl_indx, Locators_SMS_DeviceStability.typemsg_text_sl, Locators_SMS_DeviceStability.typemsg_xpath_sl,Locators_SMS_DeviceStability.typemsgcls_sl, 1268, 343), message);
			minWait();
		
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR,"Error in locators->enterText_MessageField_SL()");
		}catch (Exception e) {
			test.log(LogStatus.ERROR,"Exeption in ->enterText_MessageField_SL()");
		}
	}


	public void type_New_Message(String cell_No, String typeMessage,SoftAssert sa) throws InterruptedException, IOException {
		/*
		 * This Method is to type New Meassage.
		 */
		customWait(1000);
		type_Message(typeMessage,sa);	
		enter_ToField(cell_No);

	}

	public void enter_ToField(String cellNo) throws InterruptedException {
		
		try {
			customWait(2000);
			if(isElementExist(multi_Loc_Strategy(Locators_SMS_DeviceStability.TO_Field_att, Locators_SMS_DeviceStability.TO_Field_indx_att, Locators_SMS_DeviceStability.TO_Field_Text1_att, Locators_SMS_DeviceStability.TO_Field_Txt1_att, Locators_SMS_DeviceStability.TO_Fieldid_att, 162, 197))) {
				enterTextToInputField((multi_Loc_Strategy(Locators_SMS_DeviceStability.TO_Field_att, Locators_SMS_DeviceStability.TO_Field_indx_att, Locators_SMS_DeviceStability.TO_Field_Text1_att, Locators_SMS_DeviceStability.TO_Field_Txt1_att, Locators_SMS_DeviceStability.TO_Fieldid_att, 162, 197)), cellNo);
				minWait();
			}

			if(isElementExist(Locators_SMS_DeviceStability.TO_Field_enter)) {
				enterTextToInputField(Locators_SMS_DeviceStability.TO_Field_enter, cellNo);
				minWait();
			}

			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_ENTER);
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR,"Error in locators->validate_navigatetocallsettings()");
		}catch (Exception e) {
			test.log(LogStatus.ERROR,"Exeption in ->validate_navigatetocallsettings()");
		}
		
	}

	public void type_Message(String typeMessage,SoftAssert sa) throws InterruptedException {
		
		try {
			minWait();
			if(isElementExist(Locators_SMS_DeviceStability.type_Message)) {
				enterTextToInputField(multi_Loc_Strategy(Locators_SMS_DeviceStability.type_Messageid_att, Locators_SMS_DeviceStability.type_Message_att, Locators_SMS_DeviceStability.type_Message_indx_att, Locators_SMS_DeviceStability.type_Messagetxt_att, Locators_SMS_DeviceStability.typemessageField_att, 234, 1120), typeMessage);
			}
			if(isElementExist(Locators_SMS_DeviceStability.type_Message_enter)) {
				enterTextToInputField(Locators_SMS_DeviceStability.type_Message_enter, typeMessage);
			}

		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR,"Error in locators->validate_navigatetocallsettings()");
		}catch (Exception e) {
			test.log(LogStatus.ERROR,"Exeption in ->validate_navigatetocallsettings()");
		}
		
	}

	public void send_SMS() throws InterruptedException {
		/*	
		 * 	This method is used to SEND SMS
		 * Precondition : User should create new SMS 
		 */
	
		try {
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_MENU);
			minWait();
			clickBtn(Locators_SMS_DeviceStability.send);
			minWait();
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR,"Error in locators->validate_navigatetocallsettings()");
		}catch (Exception e) {
			test.log(LogStatus.ERROR,"Exeption in ->validate_navigatetocallsettings()");
		}
		
	}


	public void clickOn_Send() throws InterruptedException { 
		/* Method used to click on Send Button. */
		try {
			minWait();
			if(isElementExist(Locators_SMS_DeviceStability.send_Icon)) {
				clickBtn(Locators_SMS_DeviceStability.send_Icon);
				APP_LOGS.info("Send icon");
				minWait();
			}
			else if(isElementExist(Locators_SMS_DeviceStability.send_SMS)) {
				clickBtn(Locators_SMS_DeviceStability.send_SMS);
				APP_LOGS.info("Send icon");
				minWait();
			}
			else {
				clickBtn(Locators_SMS_DeviceStability.send_MMS_Icon);
				minWait();
			}

		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR,"Error in locators->clickOn_Send()");
		}catch (Exception e) {
			test.log(LogStatus.ERROR,"Exeption in ->clickOn_Send()");
		}
	}
	public void clickOn_Send_SL() throws InterruptedException { 
		/* Method used to click on Send Button. */
		try {
			clickBtn(multi_Loc_Strategy(Locators_SMS_DeviceStability.sendmsg_sl, Locators_SMS_DeviceStability.sendmsg_sl_indx, Locators_SMS_DeviceStability.sendmsg_text_sl, Locators_SMS_DeviceStability.sendmsgcls_sl, Locators_SMS_DeviceStability.sendmsgtxt_sl, 1011, 1127));

		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR,"Error in locators->clickOn_Send_SL()");
		}catch (Exception e) {
			test.log(LogStatus.ERROR,"Exeption in ->clickOn_Send_SL()");
		}
	}

	public void clearSMSPermissions() throws InterruptedException {
		try {	
			customWait(2000);
			//				clickBtn(Locators_SMS_DeviceStability.StartMessaging);
			minWait();
			clickBtn(Locators_SMS_DeviceStability.NEXT_Msg);
			minWait();
			clickBtn(Locators_SMS_DeviceStability.allow_Permission);
			minWait();
			//			Runtime run = Runtime.getRuntime();
			//			Process pr = run.exec("adb shell input tap 540 1776");
			//			pr.waitFor();
			//			Runtime run1 = Runtime.getRuntime();
			//			Process pr1 = run1.exec("adb shell input tap 713 1098");
			//			pr1.waitFor();
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR,"Error in locators->clearSMSPermissions()");
		}catch (Exception e) {
			test.log(LogStatus.ERROR,"Exeption in ->clearSMSPermissions()");
		}
	}

	public void clickOnAppList() throws InterruptedException {

		//try {
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_HOME);			
			customWait(2000);			
			clickBtn(multi_Loc_Strategy(Locators_SMS_DeviceStability.app_List_desc, Locators_SMS_DeviceStability.app_List_id, Locators_SMS_DeviceStability.app_List_index, Locators_SMS_DeviceStability.app_List_resourceid, Locators_SMS_DeviceStability.app_List_contdesc, 468, 1650));
			minWait();
	//	} catch (Exception e) {
		//	e.printStackTrace();
	//	}

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
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR,"Error in locators->launch_APP()");
		}catch (Exception e) {
			test.log(LogStatus.ERROR,"Exeption in ->launch_APP()");
		}
	}

	public void clearSMSPermissions_O() throws InterruptedException {
		try {	
			customWait(2000);
			clickBtn(Locators_SMS_DeviceStability.StartMessaging);
			minWait();
			clickBtn(Locators_SMS_DeviceStability.skipProvisioning);
			minWait();
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR,"Error in locators->clearSMSPermissions_O()");
		}catch (Exception e) {
			test.log(LogStatus.ERROR,"Exeption in ->clearSMSPermissions_O()");
		}
	}

	public void validate_SentMessage(int n,SoftAssert soft) throws InterruptedException {
		/* To validate the Sent Message. */

		try {
			//				 launch_an_app("messaging");
			customWait(8000);
			System.out.println("Sent msg");
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			customWait(1000);
			if(isElementExist(Locators_SMS_DeviceStability.now_Text)||isElementExist(Locators_SMS_DeviceStability.justnow_Text)||isElementExist(Locators_SMS_DeviceStability.not_Sent_Text)||isElementExist(Locators_SMS_DeviceStability.sending_Txt)) {
				
				APP_LOGS.info("Message sent Succeccfully");
				soft.assertTrue(true, "Message sent Succeccfully");
				test.log(LogStatus.PASS, "Test case status is Passed at iteration : "+ n);
				

			} else {
				
				APP_LOGS.info("SMS didn't sent");
				soft.fail("SMS didn't sent");
				test.log(LogStatus.FAIL, "SMS or MMS didn't Sent, Validation Failed at iteration : "+ n);
				
			}
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			minWait();
			
		}catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR,"Error in locators->validate_SentMessage()");
		}catch (Exception e) {
			test.log(LogStatus.ERROR,"Exeption in ->validate_SentMessage()");
		}	
	}
	public void validate_SentMessage_SL(int n,SoftAssert soft) throws InterruptedException {
		/* To validate the Sent Message. */

		try {
			//				 launch_an_app("messaging");
			customWait(8000);
			System.out.println("Sent msg");
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
	        customWait(1000);
			if(isElementExist(multi_Loc_Strategy(Locators_SMS_DeviceStability.valsendmsg_sl, Locators_SMS_DeviceStability.valsendmsg_sl_indx, Locators_SMS_DeviceStability.valsendmsg_text_sl, Locators_SMS_DeviceStability.valsendmsgcls_sl, Locators_SMS_DeviceStability.valsendmsgdes_sl, 1660, 876))||isElementExist(multi_Loc_Strategy(Locators_SMS_DeviceStability.valnotsendmsg_sl, Locators_SMS_DeviceStability.valnotsendmsg_sl_indx, Locators_SMS_DeviceStability.valnotsendmsg_text_sl, Locators_SMS_DeviceStability.valnotsendmsgcls_sl, Locators_SMS_DeviceStability.valnotsendmsgdes_sl, 1735, 1104)))
					{
				
				APP_LOGS.info("Message sent Succeccfully");
				soft.assertTrue(true, "Message sent Succeccfully");
				test.log(LogStatus.PASS, "Test case status is Passed at iteration : "+ n);
			

			} else {
				
				APP_LOGS.info("SMS didn't sent");
				soft.fail("SMS didn't sent");
				test.log(LogStatus.FAIL, "SMS or MMS didn't Sent, Validation Failed at iteration : "+ n);
				
			}
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			minWait();
			/*	   aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);*/
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR,"Error in locators->validate_SentMessage_SL()");
		}catch (Exception e) {
			test.log(LogStatus.ERROR,"Exeption in ->validate_SentMessage_SL()");
		}	
	}

	public void validate_CharacterAndPageNumber(WebElement charAndPageNum,String expectedcharAndPageNum,SoftAssert sa) throws InterruptedException {
		/*
		 * This Meathod is to validate Characters and Page Number
		 * Precondition : User should be in Message window with Typed Message
		 */
		
		try {
			minWait();
			String charAndPageNum1 = null;
			if(isElementExist(Locators_SMS_DeviceStability.zero_Characters_FirstPage)) {
				System.out.println("Im out");
				charAndPageNum1 = charAndPageNum.getText();
			}
			else if(isElementExist(Locators_SMS_DeviceStability.zero_Characters_FirstPage1)) {
				System.out.println("Im in");
				charAndPageNum1 = Locators_SMS_DeviceStability.zero_Characters_FirstPage1.getText();
			}

			if (check=charAndPageNum1.equals(expectedcharAndPageNum)) {
				APP_LOGS.info("Character and Page Number Displayed is correct");
				sa.assertTrue(true, "Character and Page Number Displayed is correct");
				test.log(LogStatus.PASS, "Character and Page Number Displayed is correct ");
			} else {
				APP_LOGS.info("Character and Page Number Displayed is NOT correct");
				sa.fail("Character and Page Number Displayed is NOT correct ");
				test.log(LogStatus.FAIL, "Character and Page Number Displayed is NOT correct ");
			} 
		}			catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR,"Error in locators->validate_CharacterAndPageNumber()");
		}catch (Exception e) {
			test.log(LogStatus.ERROR,"Exeption in ->validate_CharacterAndPageNumber()");
		}
	}
	public void validate_CharacterAndPageNumber_SL(WebElement charAndPageNum,String expectedcharAndPageNum,SoftAssert sa) throws InterruptedException {
		/*
		 * This Meathod is to validate Characters and Page Number
		 * Precondition : User should be in Message window with Typed Message
		 */
		
		try {
			minWait();
			String charAndPageNum1 = null;
			if(multi_Loc_Strategy(Locators_SMS_DeviceStability.validatingchrs_id_sl, Locators_SMS_DeviceStability.validatingchrs_sl, Locators_SMS_DeviceStability.validatingchrs_sl_indx, Locators_SMS_DeviceStability.validatingchrscls_sl, Locators_SMS_DeviceStability.validatingchrsid_sl, 1003, 1744) != null) {
				System.out.println("Im out");
				charAndPageNum1 = charAndPageNum.getText();
			}
			

			if (check=charAndPageNum1.equals(expectedcharAndPageNum)) {
				APP_LOGS.info("Character and Page Number Displayed is correct");
				sa.assertTrue(true, "Character and Page Number Displayed is correct");
				test.log(LogStatus.PASS, "Character and Page Number Displayed is correct ");
			} else {
				APP_LOGS.info("Character and Page Number Displayed is NOT correct");
				sa.fail("Character and Page Number Displayed is correct");
				test.log(LogStatus.FAIL, "Character and Page Number Displayed is NOT correct ");
			} 
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR,"Error in locators-> validate_CharacterAndPageNumber_SL()");
		}catch (Exception e) {
			test.log(LogStatus.ERROR,"Exeption in -> validate_CharacterAndPageNumber_SL()");
		}
	}	

	public void validate_CharacterAndPageNumber_O(WebElement charAndPageNum,String expectedcharAndPageNum,SoftAssert sa) throws InterruptedException {
		/*
		 * This Meathod is to validate Characters and Page Number
		 * Precondition : User should be in Message window with Typed Message
		 */
		
		try {
			minWait();
			String charAndPageNum1 = null;
			if(isElementExist(Locators_SMS_DeviceStability.zero_Characters_FirstPage_O)) {
				System.out.println("Im out");
				charAndPageNum1 = charAndPageNum.getText();
			}
			System.out.println(charAndPageNum1);
			if (check=charAndPageNum1.equals(expectedcharAndPageNum)) {
				APP_LOGS.info("Character and Page Number Displayed is correct");
				sa.assertTrue(true, "Character and Page Number Displayed is correct");
				test.log(LogStatus.PASS, "Character and Page Number Displayed is correct ");
			} else {
				APP_LOGS.info("Character and Page Number Displayed is NOT correct");
				sa.fail("Character and Page Number Displayed is correct");
				test.log(LogStatus.FAIL, "Character and Page Number Displayed is NOT correct ");
			} 
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR,"Error in locators->validate_CharacterAndPageNumber_O()");
		}catch (Exception e) {
			test.log(LogStatus.ERROR,"Exeption in ->validate_CharacterAndPageNumber_O()");
		}
	}	

	public void navigateAttachOthers() throws InterruptedException {
		/* Navigates to */
		try {
			minWait();
			clickBtn(Locators_SMS_DeviceStability.attach_icon_O);
			minWait();
			System.out.println("Clicked Attachment");
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR,"Error in locators->validate_navigatetocallsettings()");
		}catch (Exception e) {
			test.log(LogStatus.ERROR,"Exeption in ->validate_navigatetocallsettings()");
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
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR,"Error in locators->validate_navigatetocallsettings()");
		}catch (Exception e) {
			test.log(LogStatus.ERROR,"Exeption in ->validate_navigatetocallsettings()");
		}
	}






	public void enable_MobileData() throws InterruptedException, IOException {
		/* 
		 * This Method is to enable MobileData.
		 */
		if (p_b_No.contains("-11.")||p_b_No.contains("-12.")||p_b_No.contains("-18.")||p_b_No.contains("-26.")||p_b_No.contains("-29.")||p_b_No.contains("-30.")) {
			try {
				minWait();
				aDriver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().className(\"android.support.v7.widget.RecyclerView\")).scrollIntoView(new UiSelector().text(\"Data usage\"))").click();
				minWait();
			} catch (Exception e) {
			}
			if(!isElementExist(Locators_SMS_DeviceStability.cellularData_OnState)) {
				clickBtn(Locators_SMS_DeviceStability.cellularData_OffState);
				if(isElementExist(Locators_SMS_DeviceStability.OK)) {
					clickBtn(Locators_SMS_DeviceStability.OK);
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
				if(!isElementExist(Locators_SMS_DeviceStability.mobileData_OnState)) {
					clickBtn(Locators_SMS_DeviceStability.mobileData_OffState);
					if(isElementExist(Locators_SMS_DeviceStability.OK)) {
						clickBtn(Locators_SMS_DeviceStability.OK);
					}
					APP_LOGS.info("MobileData is Disabled");
					minWait();
				} else {
					APP_LOGS.info("MobileData is already Disabled");
				}
			} catch (org.openqa.selenium.NoSuchElementException e) {

				test.log(LogStatus.ERROR,"Error in locators->validate_navigatetocallsettings()");
			}catch (Exception e) {
				test.log(LogStatus.ERROR,"Exeption in ->validate_navigatetocallsettings()");
			}
		}	
	}

	public void navigateTo_CellularNetworks() throws InterruptedException, IOException {
		/* This Method will Navigate to Cellular Netwotks option. */ 
		minWait();
		try {
			aDriver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().className(\"android.support.v7.widget.RecyclerView\")).scrollIntoView(new UiSelector().text(\"More\"))").click();
			minWait();
			aDriver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().className(\"android.support.v7.widget.RecyclerView\")).scrollIntoView(new UiSelector().text(\"Cellular networks\"))").click();
			minWait();
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR,"Error in locators->validate_navigatetocallsettings()");
		}catch (Exception e) {
			test.log(LogStatus.ERROR,"Exeption in ->validate_navigatetocallsettings()");
		}
	}


	public void clickOn_Networks_and_Internet() {
		try {
			minWait();
			scrollToText("Network & Internet");
			minWait();
		} catch (Exception e) {
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

	public void dailCallUsingDailPad(String dailNum) throws InterruptedException, IOException {

		try {
			dailNumber(dailNum);
			if (!isElementExist(Locators_SMS_DeviceStability.turnOff_Airplane_PopUp)) {
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
			}
			APP_LOGS.info("Number dailed is: "+dailNum);
		}catch (Exception e) {
			APP_LOGS.info("Selected Page is not displayed.");
			e.printStackTrace();
		}
	}

	public void dailNumber(String dailNum) throws IOException {
		try {
			minWait();
			clickBtn(Locators_SMS_DeviceStability.add_Call);
			minWait();
			clickBtn(Locators_SMS_DeviceStability.dailerPad);
			minWait();
			clickBtn(Locators_SMS_DeviceStability.enterNumFiled);
			minWait();
			enterTextToInputField(Locators_SMS_DeviceStability.enterNumFiled, dailNum);
			minWait();
			//Runtime.getRuntime().exec("adb -s "+p_Id+" shell input text "+dailNum);
			minWait();
			clickBtn(Locators_SMS_DeviceStability.dail);
			customWait(4000);
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR,"Error in locators->validate_navigatetocallsettings()");
		}catch (Exception e) {
			test.log(LogStatus.ERROR,"Exeption in ->validate_navigatetocallsettings()");
		}
	}



	public void validate_Airplane_Enable(String dailNum,SoftAssert soft,int n) throws InterruptedException, IOException {
		/* 
		 * Method can be Used Validate Airplane Mode activation via by making the call.
		 */
		try {
			customWait(2000);
			if(isElementExist(Locators_SMS_DeviceStability.turnOff_Airplane_PopUp)) {
				minWait();
				check=true;
				APP_LOGS.info("Validated TurnOff Airplane Mode PopUp Displayed Successfully"+ check);
				soft.assertTrue(check, "TestCase Valiation is PASS");
				System.out.println("Validated TurnOff Airplane Mode PopUp Displayed Successfully"+ check);
				test.log(LogStatus.PASS, "Enable Airplane Mode is Validated at iteration: "+n);
			} else  {
				minWait();
				APP_LOGS.info("TurnOff Airplane Mode PopUp NOT Displayed");			
				System.out.println("Enable Airplane Mode is Unsuccessful at iteration: "+n);
				test.log(LogStatus.FAIL,"Enable Airplane Mode is Unsuccessful at iteration: "+n);
				soft.fail();
			}
			minWait();
			clickBtn(Locators_SMS_DeviceStability.OK);
			minWait();
			clickBtn(Locators_SMS_DeviceStability.CANCEL);
			minWait();
		}catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR,"Error in locators->validate_navigatetocallsettings()");
		}catch (Exception e) {
			test.log(LogStatus.ERROR,"Exeption in ->validate_navigatetocallsettings()");
		}
	}

	public void validate_Airplane_Disable(String dailNum,SoftAssert soft,int n) throws InterruptedException, IOException {
		/* 
		 * Method can be Used Validate Airplane Mode activation via by making the call.
		 */
		try {		
			minWait();
			if(!isElementExist(Locators_SMS_DeviceStability.turnOff_Airplane_PopUp)) {
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
			//			clickBtn(Locators_SMS_DeviceStability.OK);
			minWait();
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR,"Error in locators->validate_navigatetocallsettings()");
		}catch (Exception e) {
			test.log(LogStatus.ERROR,"Exeption in ->validate_navigatetocallsettings()");
		}
	}


	public void end_Call() throws InterruptedException {
		/* Method is to end Call.
		 * Precondition : User should initiate the Call to any Number. */
		try {
			minWait();
			if(isElementExist(Locators_SMS_DeviceStability.end_Call)) {
				clickBtn(Locators_SMS_DeviceStability.end_Call);
				APP_LOGS.info("Call Ended");
			}else {
				APP_LOGS.info("End Call Button Doesn't exist on screen");
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR,"Error in locators->validate_navigatetocallsettings()");
		}catch (Exception e) {
			test.log(LogStatus.ERROR,"Exeption in ->validate_navigatetocallsettings()");
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
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR,"Error in locators->validate_navigatetocallsettings()");
		}catch (Exception e) {
			test.log(LogStatus.ERROR,"Exeption in ->validate_navigatetocallsettings()");
		}
	}

	public void validate_BT_Devices(SoftAssert soft, int n) throws InterruptedException, IOException {
		minWait();
		Runtime.getRuntime().exec("adb -s "+r_Id+" shell am start -a android.settings.BLUETOOTH_SETTINGS");
		customWait(6000);
		System.out.println(" Im Searching...BT devices");
		WebDriverWait wait = new WebDriverWait(aDriver, 30);
		//		scrollToElement(Locators_SMS_DeviceStability.BT_Devices);
		wait.until(ExpectedConditions.visibilityOf(Locators_SMS_DeviceStability.BT_Devices));
		minWait();			
		boolean check=Locators_SMS_DeviceStability.BT_Devices.isDisplayed();
		minWait();
		if (check) {
			check=true;
			soft.assertTrue(check, "TestCase Valiation is PASS");
			test.log(LogStatus.PASS, "Enable Bluethooth is Validated at iteration: "+n);
		} else {
			soft.fail();
			test.log(LogStatus.FAIL, "Enable Bluethooth is UnSuccessful at iteration: "+n);
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
			return check;
		}
		catch(Exception e) {
			return check;
		}
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
			return check;
		}
		catch(NoSuchElementException e) {
			return check;
		}
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
			clickBtn(Locators_SMS_DeviceStability.firstApp_Suggetion);
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


	public void clickOn_BackBtn() throws InterruptedException {
		try {
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			minWait();
		} catch (NoSuchElementException e) {
			e.printStackTrace();
		}
	}

	public void checkWifiConnected() throws InterruptedException {
		try {
			customWait(4000);
			if(isElementExist(Locators_SMS_DeviceStability.connectedWIFI1)) {
				String getTxt = Locators_SMS_DeviceStability.connectedWIFI1.getText();
				System.out.println(getTxt);
				test.log(LogStatus.INFO, "Wi-Fi is Connected : will be disconnecting");
				disconnectSSIDifConnected();
			}
			else {
				System.out.println("Not Connected");
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR,"Error in locators->validate_navigatetocallsettings()");
		}catch (Exception e) {
			test.log(LogStatus.ERROR,"Exeption in ->validate_navigatetocallsettings()");
		}
	}

	public void validate_And_BrowseIn_Chrome(String url, AndroidElement autoSuggestion, AndroidElement expectedElement, AndroidElement expectedElement1, int n,SoftAssert soft) throws InterruptedException, IOException {
		/* 
		 * Validates MobileData enabality by launching the Chrome & browsing google page.
		 */

		try {
			customWait(3000);
			Runtime.getRuntime().exec("adb -s "+p_Id+" shell am start -a android.intent.action.VIEW -d http:/"+url);
			customWait(3000);
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_ENTER);
			minWait();
			customWait(12000);

			try {
				if(expectedElement.isDisplayed()) {
					check=true;
					APP_LOGS.info("Suggeted URL Page Displayed Succeessfully");
					soft.assertTrue(check, "TestCase Valiation is PASS");
					test.log(LogStatus.PASS, "WiFi Enabled and URL loaded successfully at iteration "+n);
				} else {
					APP_LOGS.info("Suggeted URL page didn't get Loaded");
					test.log(LogStatus.FAIL,"Test Case Status is Fail at iteration "+ n);
					test.log(LogStatus.INFO, "URL didn't load.");
					soft.fail();
				}
			} catch (Exception e) {
				if(expectedElement1.isDisplayed()) {
					check=true;
					APP_LOGS.info("Suggeted URL Page Displayed Succeessfully");	
					test.log(LogStatus.PASS, "URL loaded successfully at iteration "+n);

				} else {
					APP_LOGS.info("Suggeted URL page didn't get Loaded");
					test.log(LogStatus.FAIL,"Test Case Status is Fail at iteration "+n);
					soft.fail();
					test.log(LogStatus.INFO, "URL didn't load.");
				}
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR,"Error in locators->validate_navigatetocallsettings()");
		}catch (Exception e) {
			test.log(LogStatus.ERROR,"Exeption in ->validate_navigatetocallsettings()");
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


	public void closeAllTabs() throws InterruptedException {
		/* To close all tha Tabs in Chrome Browser. */
		try {
			minWait();
			clickBtn(Locators_SMS_DeviceStability.switcherButton_Chrome);
			minWait();
			clickBtn(Locators_SMS_DeviceStability.menuButton_Chrome);
			minWait();
			clickBtn(Locators_SMS_DeviceStability.closeAllTabs_Chrome);
			minWait(); 
		} catch (NoSuchElementException e) {			 
			e.printStackTrace();
		}
	}

	public void validate_MobileData_Disable(int n,String typ,SoftAssert soft) throws InterruptedException, IOException {
		/* 
		 * Validates MobileData disabality by launching the Chrome.
		 */
		customWait(3000);
		if(isElementExist(Locators_SMS_DeviceStability.alert_OKBtn)) {
			clickBtn(Locators_SMS_DeviceStability.alert_OKBtn);
			System.out.println("Im Clicking Ok First ");
		}
		String url = "www.google.co.in";
		customWait(2000);
		Runtime.getRuntime().exec("adb -s "+p_Id+" shell am start -a android.intent.action.VIEW -d http:/"+url);
		customWait(3000);
		aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_ENTER);
		minWait();
		customWait(12000);
		customWait(9000);
		System.out.println("Im in validation");
		try {			
			customWait(3000);
			if(!isElementExist(Locators_SMS_DeviceStability.google_Logo)||!isElementExist(Locators_SMS_DeviceStability.google_Logo)) {
				check=true;
				APP_LOGS.info("MobbileData Disabled Successfully");
				soft.assertTrue(check, "TestCase Valiation is PASS");
				test.log(LogStatus.PASS, typ+" Disable validation is PASS at iteration "+n);	
			} else {
				APP_LOGS.info("Mobiledata is NOT Disabled");
				test.log(LogStatus.FAIL,typ+" Disable validation is FAIL at iteration "+n);
				soft.fail();
			}
			customWait(3000);
			if(isElementExist(Locators_SMS_DeviceStability.alert_OKBtn)) {
				clickBtn(Locators_SMS_DeviceStability.alert_OKBtn);
				System.out.println("Im Clicking Ok 2nd ");
			}
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			minWait();
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR,"Error in locators->validate_navigatetocallsettings()");
		}catch (Exception e) {
			test.log(LogStatus.ERROR,"Exeption in ->validate_navigatetocallsettings()");
		}
		//		closeAllTabs();
	}

	public void clearChromePermission() {
		try {
			System.out.println("In Browser");
			if (isElementExist(Locators_SMS_DeviceStability.ACCEPTCONTINUE)) {
				customWait(3000);
				clickBtn(Locators_SMS_DeviceStability.ACCEPTCONTINUE);
				customWait(5000);
				clickBtn(Locators_SMS_DeviceStability.NEXT);
				customWait(3000);
				clickBtn(Locators_SMS_DeviceStability.NO_THANKS);
				customWait(2000);
			}
			else {
				clickBtn(Locators_SMS_DeviceStability.ACCEPTandCONTINUE);
				customWait(5000);
				clickBtn(Locators_SMS_DeviceStability.NEXT);
				customWait(3000);
				clickBtn(Locators_SMS_DeviceStability.No_ThanKS);
				customWait(2000);
			}
		} catch (Exception e) {	 	
			e.printStackTrace();
		}			
	}

	public void add_Picture_O() throws InterruptedException {

		try {
			if (p_b_No.contains("-10.")||p_b_No.contains("-30.")) {
				clickAttachment();
				minWait();
				clickBtn(Locators_SMS_DeviceStability.capturePicture_MMS);
				minWait();
				clearAllow();
				clickBtn(Locators_SMS_DeviceStability.capturePicture);
				customWait(10000);
				clickBtn(Locators_SMS_DeviceStability.done_1);
				minWait();
			} else if(p_b_No.contains("-11.")||p_b_No.contains("-12.")||p_b_No.contains("-18.")||p_b_No.contains("-26.")||p_b_No.contains("-29.")){
				clickAttachment();
				minWait();
				clickBtn(Locators_SMS_DeviceStability.capture_pictures_or_video);
				minWait();
				clearAllow();
				clickBtn(Locators_SMS_DeviceStability.capture_pictures_or_video);
				minWait();
				clickBtn(Locators_SMS_DeviceStability.take_picture_MMS);
				customWait(10000);			
			}
		} catch (Exception e) {
		}		
	}


	public void clickAttachment() throws InterruptedException {
		try {
			minWait();
			clickBtn(Locators_SMS_DeviceStability.attachment);
			minWait();
		} catch (Exception e) {
		}
	}

	public void capturePic_MMS_O(){
		try {
			minWait();
			clickBtn(Locators_SMS_DeviceStability.button_Attach_SMS);
			minWait();
			clickBtn(Locators_SMS_DeviceStability.camera_MMS);
			customWait(2000);
			clickBtn(Locators_SMS_DeviceStability.OptionPhoto_Icon);
			customWait(2000);
			clickBtn(Locators_SMS_DeviceStability.cameraCapture_Icon);
			minWait();
			clickBtn(Locators_SMS_DeviceStability.send_Cam_SMS);
			customWait(3000);
		} catch (Exception e) {

		}

	}


	public void captureVideo_MMS_O(){
		try {
			minWait();
			clickBtn(Locators_SMS_DeviceStability.button_Attach_SMS);
			minWait();
			clickBtn(Locators_SMS_DeviceStability.camera_MMS);
			customWait(2000);
			clickBtn(Locators_SMS_DeviceStability.OptionVideo_Icon);
			customWait(2000);
			TouchAction action = new TouchAction(aDriver);
			action.longPress(Locators_SMS_DeviceStability.cameraCapture_Icon).release().perform();
			//				clickBtn(Locators_SMS_DeviceStability.cameraCapture_Icon);
			minWait();
			clickBtn(Locators_SMS_DeviceStability.send_Cam_SMS);
			customWait(3000);
		} catch (Exception e) {

		}

	}

	public void remove_GoogleAcccount_Orio() {
		//remove added google Account if any 
		try {
			scrollToText("Users & accounts");
			//				clickOnAccounts();
			minWait();
			if(isElementExist(Locators_SMS_DeviceStability.connectedAccount)) {
				System.out.println("Account is present");
				minWait();
				clickBtn(Locators_SMS_DeviceStability.connectedAccount);
				minWait();
				clickBtn(Locators_SMS_DeviceStability.REMOVE_ACCOUNT);
				minWait();
				clickBtn(Locators_SMS_DeviceStability.REMOVE_ACCOUNT);
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
			clickBtn(Locators_SMS_DeviceStability.add_Account);
			minWait();
			clickBtn(Locators_SMS_DeviceStability.google_Account);
			customWait(1600);
			wt.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[contains(@text,'Checking info')]")));
			minWait();
		} catch (Exception e) {

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

	public void add_GoogleAccount(String emailId, String password) {
		WebDriverWait wt = new WebDriverWait(aDriver, 60);
		try {
			minWait();
			enterTextToInputField(Locators_SMS_DeviceStability.email_googleAcnt, emailId);
			minWait();
			clickBtn(Locators_SMS_DeviceStability.next);
			minWait();
			wt.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@text='Welcome' or @resource-id='profileIdentifier']")));
			minWait();
			enterTextToInputField(Locators_SMS_DeviceStability.password_googleAcnt, password);
			customWait(5000);
			clickBtn(Locators_SMS_DeviceStability.next);
			customWait(3000);
			scroll() ;
			scroll() ;
			minWait();
			clickBtn(Locators_SMS_DeviceStability.skip_);
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
			clickBtn(Locators_SMS_DeviceStability.i_agree);
			minWait();
			wt.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[contains(@text,'Checking info')]")));
			minWait();
			clickBtn(Locators_SMS_DeviceStability.MORE);
			minWait();
			clickBtn(Locators_SMS_DeviceStability.ACCEPTorAGREE);
			customWait(3000);
		} catch (Exception e) {
		}
	}

	public void checkWifiConnected1() throws InterruptedException {
		try {
			customWait(4000);
			if(isElementExist(Locators_SMS_DeviceStability.connectedWIFI1)) {
				String getTxt = Locators_SMS_DeviceStability.connectedWIFI1.getText();
				System.out.println("Connected "+getTxt);
				//					test.log(LogStatus.INFO, "Wi-Fi is Connected");
			}
			else {
				System.out.println("Not Connected");
				selectSSIDwifi();	
				enterPassword();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void install_App(String appName,WebElement element) throws InterruptedException, IOException {

		if(isElementExist(Locators_SMS_DeviceStability.account_Page)) {
			clearRecentApps();
			launch_APP(Locators_SMS_DeviceStability.PlayStore);
		}

		WebDriverWait wait = new WebDriverWait(aDriver, 60);
		try {
			System.out.println("To Be install "+appName);
			customWait(3000);
			clickBtn(Locators_SMS_DeviceStability.google_Play);
			customWait(3000);
			enterTextToInputField(Locators_SMS_DeviceStability.search_PlayStore,appName);
			customWait(3000);
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_ENTER);
			customWait(8000);
			System.out.println("Scrolling");
			scrollToElementWithDpadDown(element);
			minWait();

			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@text='INSTALL']")));
			customWait(6000);
			if (isElementExist(Locators_SMS_DeviceStability.INSTALL)) {
				minWait();
				clickBtn(Locators_SMS_DeviceStability.INSTALL);
				minWait();
				clickBtn(Locators_SMS_DeviceStability.ACCEPT);
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
			enterTextToInputField(Locators_SMS_DeviceStability.searchApps, appName);
			minWait();
			if (isElementExist(Locators_SMS_DeviceStability.apkExtractor)) {
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

		enterTextToInputField(Locators_SMS_DeviceStability.searchApps, appName);
		minWait();
		System.out.println("Checking....");
		if (isElementExist(Locators_SMS_DeviceStability.apkExtractor)) {
			System.out.println("Yes App is Present...");
			if(appName.contains("Tech Support")) {
				System.out.println("Im in Staples");
				launch_APP(Locators_SMS_DeviceStability.PlayStore);
				unInstall_App("Tech Support Staples");

			}
			else if(appName.contains("Meeting App")) {
				System.out.println("Im in MAcys");
				launch_APP(Locators_SMS_DeviceStability.PlayStore);
				unInstall_App("Macys");
			}

			else if(appName.contains("MCDelivery App")) {
				System.out.println("MCDelivery");
				launch_APP(Locators_SMS_DeviceStability.PlayStore);
				unInstall_App("McDonald's");
			}		

			else if(appName.contains("Craigslist")) {
				System.out.println("App for Craigslist");
				launch_APP(Locators_SMS_DeviceStability.PlayStore);
				unInstall_App("App for Craigslist");
			}			


			else {
				System.out.println("Im in Gen");
				launch_APP(Locators_SMS_DeviceStability.PlayStore);
				unInstall_App(appName);		
			}
			System.out.println("Uninstalled");
		}
	}

	public void unInstall_App(String appName) {

		WebDriverWait wait = new WebDriverWait(aDriver, 60);
		try {
			minWait();
			clickBtn(Locators_SMS_DeviceStability.google_Play);
			minWait();
			enterTextToInputField(Locators_SMS_DeviceStability.search_PlayStore, appName);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_ENTER);
			customWait(5000);
			if(isElementExist(Locators_SMS_DeviceStability.installed_Playstore1)) {
				customWait(3000);
				clickBtn(Locators_SMS_DeviceStability.installed_Playstore1);
			}
			if (isElementExist(Locators_SMS_DeviceStability.UNINSTALL)) {
				System.out.println("Uninstalling");
				minWait();
				clickBtn(Locators_SMS_DeviceStability.UNINSTALL);
				minWait();
				clickBtn(Locators_SMS_DeviceStability.OK);
				minWait();
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@text='INSTALL']")));
				minWait();					
			} 
			else if(isElementExist(Locators_SMS_DeviceStability.UNINSTALL1)){
				System.out.println("Uninstalling....");
				minWait();
				clickBtn(Locators_SMS_DeviceStability.UNINSTALL1);
				minWait();
				clickBtn(Locators_SMS_DeviceStability.OK);
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
			enterTextToInputField(Locators_SMS_DeviceStability.searchApps, appName);
			minWait();
			if (isElementExist(Locators_SMS_DeviceStability.apkExtractor)) {
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
			clickBtn(Locators_SMS_DeviceStability.add_ContactIcon_O);
			minWait();
			clickBtn(Locators_SMS_DeviceStability.contact_SavingTo);
			minWait();
			clickBtn(Locators_SMS_DeviceStability.savingTo_Phone);
			minWait();
			//				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			minWait();
			clickBtn(Locators_SMS_DeviceStability.more_Fields);
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
			//			clickBtn(Locators_SMS_DeviceStability.SAVE);
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


	public void scrollUp() {

		try {
			/*
			   	org.openqa.selenium.Dimension size =aDriver.manage().window().getSize();
			   	System.out.println(size);
			   	int x=size.getWidth()/2;
			   	int starty=(int)(size.getHeight()*0.60);
			   	int endy=(int)(size.getHeight()*0.10);
			 */
			aDriver.swipe(600, 1800, 600, 400, 1000);
		} catch (Exception e) {   
			e.printStackTrace();
		}
	}

	public void add_NewContact(int enter1forPhone_2forSimMemory,String contactName, String contactNum) throws InterruptedException, IOException {
		/* Method is to Add Contact in Contacts.
		 * Precondition:User should give 1 for first argument to save for Phone Memory and 2 fo rsim Memory.
		 */
		try {
			minWait();
			clickBtn(Locators_SMS_DeviceStability.add_ContactIcon_O);
			minWait();
			//				clickBtn(Locators_SMS_DeviceStability.contact_SavingTo);
			minWait();
			//				clickBtn(Locators_SMS_DeviceStability.add_NewContact);
			minWait();
			if(enter1forPhone_2forSimMemory==1) {
				clickBtn(Locators_SMS_DeviceStability.contact_SavingTo);
				minWait();
				clickBtn(Locators_SMS_DeviceStability.savingTo_Phone);
				minWait();
				enterTextToInputField(Locators_SMS_DeviceStability.name_NewContact, contactName);
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
				minWait();
				enterTextToInputField(Locators_SMS_DeviceStability.phone_NewContact, contactNum);
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
				minWait();
				//			
			} else if (enter1forPhone_2forSimMemory==2) {
				clickBtn(Locators_SMS_DeviceStability.contact_SavingTo);
				minWait();
				clickBtn(Locators_SMS_DeviceStability.savingTo_SIM);
				minWait();
				enterTextToInputField(Locators_SMS_DeviceStability.name_NewContact, contactName);
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
				minWait();
				enterTextToInputField(Locators_SMS_DeviceStability.phone_NewContact, contactNum);
				//					aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
				minWait();
				//enterTextToInputField(Locators_SMS_DeviceStability.phone_NewContact, contactNum);
				//aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
				//minWait();			
			}		
			clickBtn(Locators_SMS_DeviceStability.save_Icon);
			minWait();
			clearAllow();
			//				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			minWait();
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}




}
