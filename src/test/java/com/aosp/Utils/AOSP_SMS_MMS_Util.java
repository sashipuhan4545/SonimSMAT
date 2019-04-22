package com.aosp.Utils;



import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;
import org.testng.asserts.SoftAssert;

import com.relevantcodes.extentreports.LogStatus;
import com.xp5S.util.BaseUtil;

import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.AndroidKeyCode;

public class AOSP_SMS_MMS_Util extends BaseUtil {

	public boolean check = false;

	public void launch_Messaging() throws InterruptedException, IOException {
		SoftAssert sf = new SoftAssert();
		try {
			minWait();
			Runtime.getRuntime().exec("adb shell am start -n com.android.mms/com.android.mms.ui.ConversationList");
			minWait();
		} catch (Exception e) {
			e.printStackTrace();
			sf.fail();
			test.log(LogStatus.ERROR, "Messaging NOT launched");
		}	
		sf.assertAll();
	}

	public void validateLaunch_SMS() {
		/*
		 * This Method is to validate Launching of Messsaing App.
		 */
		SoftAssert sf = new SoftAssert();
		try {		
			if (isElementExist(AOSP_Locators_SMS_MMS.messaging_Title)) {
				check = true;
				String getTitleText = AOSP_Locators_SMS_MMS.messaging_Title.getText();
				APP_LOGS.info("Home Screen is verified succesfully.");
				APP_LOGS.info("Home Screen title: " + getTitleText);
				sf.assertTrue(check, "Validation is Pass");
			} else {
				APP_LOGS.info("Testcase failed");
				sf.fail();
				test.log(LogStatus.ERROR, "Validation Failed.");
			} 
		} catch (Exception e) {			
			e.printStackTrace();
			sf.fail();
			test.log(LogStatus.ERROR, "Validation Failed.");
		}
		sf.assertAll();
	}	

	public void navigateTo_NewMessage() throws InterruptedException, IOException {
		SoftAssert sf = new SoftAssert();
		try {
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_MENU);
			minWait();
			clickBtn(AOSP_Locators_SMS_MMS.new_Message);
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
			minWait();
			enterTextToInputField(AOSP_Locators_SMS_MMS.toField_NewMessage, cellNo);
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
			enterTextToInputField(AOSP_Locators_SMS_MMS.type_Message, typeMessage);
		} catch (Exception e) {
			e.printStackTrace();
			sf.fail();
			test.log(LogStatus.ERROR, "Error in Typing a MEssage.");
		}
		sf.assertAll();
	}

	public void type_New_Message(String cell_No, String typeMessage) throws InterruptedException, IOException {
		/*
		 * This Method is to type New Meassage
		 */
		enter_ToField(cell_No);
		minWait();
		type_Message(typeMessage);
		minWait();		
	}

	public void discard_SMS() throws InterruptedException {
		/*
		 * This Method is to Discard the New Message
		 * Precondition : User Should be in New Message window.
		 */
		SoftAssert sf = new SoftAssert();
		try {
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_MENU);
			minWait();			
			for (int i = 0; i <15; i++) {				
				if (isElementExist(AOSP_Locators_SMS_MMS.discard)) {
					clickBtn(AOSP_Locators_SMS_MMS.discard);
					break;
				} else {
					aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
					Thread.sleep(600);
				} 
			}
			minWait();
		} catch (Exception e) {
			e.printStackTrace();
			sf.fail();
			test.log(LogStatus.ERROR, "Message didn't Discard");			
		}
		sf.assertAll();
	}

	//============================================================================================================

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
			clickBtn(AOSP_Locators_SMS_MMS.send);
			minWait();
		} catch (Exception e) {
			e.printStackTrace();
			sf.fail();
			test.log(LogStatus.ERROR, "Error in Sending the Message.");
		}
		sf.assertAll();
	}

	public void validate_Sent_SMS() throws InterruptedException {
		/*
		 * This Method is to validate SENT SMS 
		 */
		SoftAssert sf = new SoftAssert();
		minWait();
		try {
			if (isElementExist(AOSP_Locators_SMS_MMS.now_Text)) {
				check=true;
				APP_LOGS.info("Message Sent Successfully");
				sf.assertTrue(check, "Validation is Pass");
			} else {
				APP_LOGS.info("Message DIDN'T Sent");
				sf.fail();
				test.log(LogStatus.FAIL, "SMS or MMS didn't Sent, Validation Failed.");
			}
		} catch (Exception e) {
			e.printStackTrace();
			sf.fail();
			test.log(LogStatus.FAIL, "SMS or MMS didn't Sent, Validation Failed.");
		}
		sf.assertAll();
	}

	public void delete_All_Threads() throws InterruptedException {
		/*
		 * Method is used to delete All the SMS Threads
		 */
		SoftAssert sf = new SoftAssert();
		try {
			minWait();
			while (true) {
				if (isElementExist(AOSP_Locators_SMS_MMS.first_sms_Thread)) {
					minWait();
					aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_CLEAR);
					minWait();
					clickBtn(AOSP_Locators_SMS_MMS.delete_Confirm);				
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

	public void click_OnFirstSMSThread() throws InterruptedException {
		/*
		 * Click on First SMS Thread in Inbox.
		 */
		SoftAssert sf = new SoftAssert();
		try {
			minWait();
			clickBtn(AOSP_Locators_SMS_MMS.first_sms_Thread);
			minWait();
		} catch (Exception e) {
			e.printStackTrace();
			sf.fail();
			test.log(LogStatus.ERROR, "Error in clicking on the First SMS");
		}
		sf.assertAll();
	}

	public void forward_SMS(String cellNo) throws InterruptedException {
		/*
		 *  Method is used to forward the SMS.
		 *  Precondition : Atleast one Message should be present in INBOX.
		 */		
		SoftAssert sf = new SoftAssert();
		try {
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_UP);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_MENU);
			minWait();
			clickBtn(AOSP_Locators_SMS_MMS.forward);
			minWait();
			enterTextToInputField(AOSP_Locators_SMS_MMS.toField_NewMessage, cellNo);
			minWait();
			send_SMS();
		} catch (Exception e) {
			e.printStackTrace();
			sf.fail();
			test.log(LogStatus.ERROR, "Error in forwarding the SMS");
		}
		sf.assertAll();
	}

	public void copy_SMS() throws InterruptedException {

		SoftAssert sf = new SoftAssert();
		try {
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_UP);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_MENU);
			minWait();
			clickBtn(AOSP_Locators_SMS_MMS.copy_Text);
			minWait();
		} catch (Exception e) {
			e.printStackTrace();
			sf.fail();
			test.log(LogStatus.ERROR, "Error in Coping the SMS");
		}
		sf.assertAll();
	}

	public void paste_SMS() throws InterruptedException {

		SoftAssert sf = new SoftAssert();
		try {
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_MENU);
			minWait();
			clickBtn(AOSP_Locators_SMS_MMS.paste_Text);
			minWait();
		} catch (Exception e) {
			e.printStackTrace();
			sf.fail();
			test.log(LogStatus.ERROR, "Error in pasting the SMS");
		}
		sf.assertAll();
	}

	public void navigateTo_SMS_Settings() throws InterruptedException {
		/*
		 * Navigate's to SMS Settings. User should be in SMS Home page.
		 */
		SoftAssert sf = new SoftAssert();
		try {
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_MENU);
			minWait();
			clickBtn(AOSP_Locators_SMS_MMS.settings_SMS);
			minWait();
		} catch (Exception e) {
			e.printStackTrace();
			sf.fail();
			test.log(LogStatus.ERROR, "Error in navigating to SMS settings");
		}
		sf.assertAll();
	}

	public void validate_SMS_Settings() throws InterruptedException {

		SoftAssert sf = new SoftAssert();
		minWait();
		if (isElementExist(AOSP_Locators_SMS_MMS.settings_Text)) {
			check=true;
			APP_LOGS.info("Settings page Displayed Successsfully");
			sf.assertTrue(check, "Settings page Validation is PASS");
		} else {
			sf.fail();
			test.log(LogStatus.ERROR, "Error in validating the SMS Settings");
		}
		sf.assertAll();
	}

	public void enableGroup_Notification_Msg() throws InterruptedException {
		/*
		 * This Method is to Enable Group Notification Message. 
		 */
		minWait();
		if (isElementExist(AOSP_Locators_SMS_MMS.disable_Group_Notftn_Msg)) {
			clickBtn(AOSP_Locators_SMS_MMS.disable_Group_Notftn_Msg);
		} else {
			Reporter.log("Group Notification Message is already Enabled");
			APP_LOGS.info("Group Notification Message is already Enabled");
		}
	}

	public void disableGroup_Notification_Msg() throws InterruptedException {
		/*
		 * This Method is to Enable Group Notification Message. 
		 */
		minWait();
		if (isElementExist(AOSP_Locators_SMS_MMS.enable_Group_Notftn_Msg)) {
			clickBtn(AOSP_Locators_SMS_MMS.enable_Group_Notftn_Msg);
		} else {
			Reporter.log("Group Notification Message is already disabled");
			APP_LOGS.info("Group Notification Message is already disabled");
		}
	}

	public void validate_Group_Notification_Msg() throws InterruptedException {
		/*
		 * Method is to validate Group Notification Message enabality. 
		 */
		SoftAssert sf = new SoftAssert();
		minWait();
		if (!isElementExist(AOSP_Locators_SMS_MMS.disable_Group_Notftn_Msg)) {
			check=true;
			APP_LOGS.info("Group Notification msg is Enable");
			sf.assertTrue(check, "Validation is PASS");
		} else {
			sf.fail();
			test.log(LogStatus.ERROR, "Validation of Group Message Notification Failed.");
		}
		sf.assertAll();
	}

	public void launch_Contacts() throws InterruptedException, IOException {
		SoftAssert sf = new SoftAssert();
		try {
			minWait();
			Runtime.getRuntime().exec("adb shell am start -n com.android.contacts/com.android.contacts.activities.PeopleActivity");
			minWait();
		} catch (Exception e) {
			e.printStackTrace();
			sf.fail();
			test.log(LogStatus.ERROR, "Contacts NOT launched");
		}
		sf.assertAll();
	}

	public void navigateTo_Add_New_Contact() throws InterruptedException {
		/* Navigates to Add New Contact Page */

		try {
			minWait();
			if (isElementExist(AOSP_Locators_SMS_MMS.add_Contact_Contacts)) {
				clickBtn(AOSP_Locators_SMS_MMS.add_Contact_Contacts);
				minWait();
			} else if(isElementExist(AOSP_Locators_SMS_MMS.find_Contacts)) {
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_MENU);
				minWait();
				clickBtn(AOSP_Locators_SMS_MMS.add_Contact_Contacts_Menu);
				minWait();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void add_New_Contact(String contactName, String contactNo) throws InterruptedException {

		/* Method is to Add New Contact in the Contacts APP */
		SoftAssert sf = new SoftAssert();
		try {
			minWait();
			enterTextToInputField(AOSP_Locators_SMS_MMS.name_add_Contact, contactName);
			minWait();
			enterTextToInputField(AOSP_Locators_SMS_MMS.phoneNo_add_Contact, contactNo);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_MENU);
			minWait();
			clickBtn(AOSP_Locators_SMS_MMS.save_add_Contact);
			minWait();
		} catch (Exception e) {
			e.printStackTrace();
			sf.fail();
			test.log(LogStatus.ERROR, "Error in adding a New Contact.");
		}		
	}

	public void add_Contact_ToField(String contactName) throws InterruptedException {

		/* Method is to ADD Contact to the TO field of SMS*/
		try {
			minWait();
			AOSP_Locators_SMS_MMS.toField_NewMessage.click();
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_MENU);
			minWait();
			clickBtn(AOSP_Locators_SMS_MMS.add_Contact);
			minWait();
			enterTextToInputField(AOSP_Locators_SMS_MMS.search_Contact, contactName);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_MENU);
			minWait();
			clickBtn(AOSP_Locators_SMS_MMS.done);
			minWait();
		} catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "Error in adding Contact to SMS");
		}
	}

	public void search_SMS(String sms_Nmae) throws InterruptedException {

		try {
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_MENU);
			minWait();
			clickBtn(AOSP_Locators_SMS_MMS.search_SMS);
			minWait();
			enterTextToInputField(AOSP_Locators_SMS_MMS.search_messaging, sms_Nmae);
			minWait();
		} catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "Error in searching a Messsage");
		}
	}

	public void validate_Searched_SMS(String smsName) throws InterruptedException {

		SoftAssert sf = new SoftAssert();
		minWait();
		String smsName1 = AOSP_Locators_SMS_MMS.searched_SMS_Name.getText();
		if (smsName1.equalsIgnoreCase(smsName)) {
			check=true;
			APP_LOGS.info("SMS found in the Search");
			sf.assertTrue(check, "Validation is Pass");
		} else {
			sf.fail();
			test.log(LogStatus.ERROR, "Error in validating the searched SMS");
		}
		sf.assertAll();
	}

	public void validate_SMS_Deletion() throws InterruptedException {

		SoftAssert sf = new SoftAssert();
		minWait();
		if (isElementExist(AOSP_Locators_SMS_MMS.no_Conversations)) {
			check=true;
			APP_LOGS.info("ALL SMS Deleted ");
			sf.assertTrue(check, "Validation is Pass");
		} else {
			sf.fail();
			test.log(LogStatus.ERROR, "Valdation failed for SMS Delete.");
		}
		sf.assertAll();
	}

	public void press_BackKey() throws InterruptedException {
		minWait();
		aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
	}

	public void validate_CharacterAndPageNumber(WebElement charAndPageNum,String expectedcharAndPageNum) throws InterruptedException {
		/*
		 * This Meathod is to validate Characters and Page Number
		 * Precondition : User should be in Message window with Typed Message
		 */
		SoftAssert sf = new SoftAssert();
		try {
			minWait();
			String charAndPageNum1 = charAndPageNum.getText();
			if (check=charAndPageNum1.equals(expectedcharAndPageNum)) {
				APP_LOGS.info("Character and Page Number Displayed is correct");
				sf.assertTrue(check, "Validation is Pass");
			} else {
				APP_LOGS.info("Character and Page Number Displayed is NOT correct");
				sf.fail();
			} 
		} catch (Exception e) {
			e.printStackTrace();
			sf.fail();
			test.log(LogStatus.ERROR, "Error in validating the Number of characters and Page Number");
		}
		sf.assertAll();
	}

	public void enable_Vibrate() throws InterruptedException {

		try {
			minWait();
			clickBtn(AOSP_Locators_SMS_MMS.offState_Vibrate);
		} catch (Exception e) {
			APP_LOGS.info("Vibrate is already enabled");
		}
	}

	public void disable_Vibrate() throws InterruptedException {

		try {
			minWait();
			clickBtn(AOSP_Locators_SMS_MMS.onState_Vibrate);
		} catch (Exception e) {
			APP_LOGS.info("Vibrate is already disabled");
		}
	}

	public void enable_PopupAlert() throws InterruptedException {

		try {
			minWait();
			clickBtn(AOSP_Locators_SMS_MMS.offState_PopUp_Alert);
		} catch (Exception e) {
			APP_LOGS.info("Popup Alert is already enabled");
		}
	}

	public void disable_PopupAlert() throws InterruptedException {

		try {
			minWait();
			clickBtn(AOSP_Locators_SMS_MMS.onState_PopUp_Alert);
		} catch (Exception e) {
			APP_LOGS.info("Popup Alert is already disabled");
		}
	}

	public void restore_Default_Settings() throws InterruptedException {

		try {
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_MENU);
			minWait();
			clickBtn(AOSP_Locators_SMS_MMS.restoreDefaultSettings);
			minWait();
		} catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "Error in Restoring the Default Setting.");
		}		
	}

	public void validate_Default_Settings() throws InterruptedException {
		SoftAssert sf = new SoftAssert();
		minWait();
		if (isElementExist(AOSP_Locators_SMS_MMS.offState_Vibrate)&&isElementExist(AOSP_Locators_SMS_MMS.offState_PopUp_Alert)) {
			check=true;
			APP_LOGS.info("Both Vibrate and Popup alert are in OFF state");
			sf.assertTrue(check, "Validation is PASS");
		} else {
			sf.fail();
			test.log(LogStatus.ERROR, "Error in validating the Default settings.");
		}
		sf.assertAll();
	}

	public void navigateTo_TextMessage_Settings() throws InterruptedException {

		try {
			for (int i = 0; i < 15; i++) {
				if (isElementExist(AOSP_Locators_SMS_MMS.textMessagesSettings)) {
					clickBtn(AOSP_Locators_SMS_MMS.textMessagesSettings);
					break;
				} else {
					aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
					Thread.sleep(400);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "Error in navigating to Text Message Settings.");
		}		
	}

	public void clickOn_MessageTemplate() throws InterruptedException {

		try {
			minWait();
			clickBtn(AOSP_Locators_SMS_MMS.messageTemplate);
		} catch (Exception e) {
			e.printStackTrace();			
		}
	}

	public void add_Template(String templateText) throws IOException, InterruptedException {

		try {
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_MENU);
			minWait();
			clickBtn(AOSP_Locators_SMS_MMS.add_Template);
			minWait();
			Runtime.getRuntime().exec("cmd /C \"adb shell input text "+templateText+"\"");
			customWait(3000);
			clickBtn(AOSP_Locators_SMS_MMS.OK);
			minWait();
		} catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "Error in Adding the Template.");
		}		
	}

	public void edit_Template( AndroidElement templateLocator, String newTemplateText) throws InterruptedException, IOException {

		try {
			minWait();
			for (int i = 0; i < 15; i++) {
				if (isElementExist(templateLocator)) {
					aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
					minWait();
					aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_MENU);
					minWait();
					clickBtn(AOSP_Locators_SMS_MMS.edit);
					customWait(1500);
					Runtime.getRuntime().exec("adb shell input keyevent KEYCODE_MOVE_END");
					minWait();

					for (int j = 0; j <20; j++) {
						Runtime.getRuntime().exec("adb shell input keyevent KEYCODE_CLEAR");
						Thread.sleep(400);
					}

					Runtime.getRuntime().exec("cmd /C \"adb shell input text " + newTemplateText + "\"");
					minWait();
					clickBtn(AOSP_Locators_SMS_MMS.OK);
					minWait();
					break;
				} else {
					aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
					Thread.sleep(500);
				} 
			}
		} catch (WebDriverException e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "Error in Editing the Template.");
		}		
	}

	public void validate_Template(AndroidElement templateToValidate) throws InterruptedException {

		SoftAssert sf = new SoftAssert();
		try {			
			minWait();		
			if (isElementExist(templateToValidate)) {
				check=true;
				APP_LOGS.info("Created Template present in Template List");
				sf.assertTrue(check, "Validated Created Template");
			} else {
				sf.fail();
			}
		} catch (Exception e) {
			e.printStackTrace();
			sf.fail();
			test.log(LogStatus.ERROR, "Error in validating the Template.");
		}
		sf.assertAll();
	}

	public void delete_Template(AndroidElement templateLocator) throws InterruptedException {
		minWait();
		try {
			minWait();
			for (int i = 0; i < 15; i++) {
				if (isElementExist(templateLocator)) {
					aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
					minWait();
					aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_CLEAR);
					minWait();
					clickBtn(AOSP_Locators_SMS_MMS.OK);
					break;
				} else {
					aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
					Thread.sleep(400);
				} 
			}
		} catch (WebDriverException e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "Error in Deleting the Template.");
		}
	}

	public void cancel_Template(String templateText) throws IOException, InterruptedException {

		try {
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_MENU);
			minWait();
			clickBtn(AOSP_Locators_SMS_MMS.add_Template);
			minWait();
			Runtime.getRuntime().exec("cmd /C \"adb shell input text "+templateText+"\"");
			customWait(3000);
			clickBtn(AOSP_Locators_SMS_MMS.CANCEL);
			minWait();
		} catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "Error in canceling the Template.");
		}		
	}

	public void validate_CancelTemplate() throws InterruptedException {

		minWait();
		SoftAssert sf = new SoftAssert();
		if (!isElementExist(AOSP_Locators_SMS_MMS.CANCEL)) {
			check=true;
			APP_LOGS.info("Add Template Cancelled");
			sf.assertTrue(check, "Validation is Pass");
		} else {
			sf.fail();
			test.log(LogStatus.ERROR, "Validation failed for Cancel Template.");
		}
		sf.assertAll();
	}

	public void navigate_Add_To_Contacts() throws InterruptedException {
		/*Navigates to Add t Contacts.*/
		try {
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_MENU);
			minWait();
			clickBtn(AOSP_Locators_SMS_MMS.addToContacts);
			minWait();
		} catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "Error in navigating to 'Add to Contacts'.");
		}
	}

	public void createNewContact(String contactName) throws InterruptedException {

		try {
			minWait();
			clickBtn(AOSP_Locators_SMS_MMS.createNewContact);
			minWait();
			enterTextToInputField(AOSP_Locators_SMS_MMS.name_add_Contact, contactName);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_MENU);
			minWait();
			clickBtn(AOSP_Locators_SMS_MMS.save_add_Contact);
			minWait();
		} catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "Error in Creating the New Contact.");
		}
	}

	public void validate_Created_Contact(String contactName) throws InterruptedException {

		minWait();
		SoftAssert sf = new SoftAssert();
		String contactName1 = AOSP_Locators_SMS_MMS.searched_SMS_Name.getText();		

		if(contactName1.equals(contactName)) {
			check=true;
			APP_LOGS.info("Contact created Successfully");
			sf.assertTrue(check, "Valiation Created Contact ia PASS");
		}else {
			sf.fail();
			test.log(LogStatus.ERROR, "Validation Failed for Created Contact.");
		}
		sf.assertAll();
	}

	public void delete_Contact(String contactName) throws InterruptedException, IOException {
		try {
			minWait();	
			if (!isElementExist(AOSP_Locators_SMS_MMS.add_Contact_Contacts)) {
				minWait();
				Runtime.getRuntime().exec("adb shell input text "+contactName);
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_MENU);
				minWait();
				clickBtn(AOSP_Locators_SMS_MMS.delete_InMenu);
				minWait();
				clickBtn(AOSP_Locators_SMS_MMS.delete_Confirm);
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
				minWait();
			}
		} catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "Error in Deleting the Contact.");
		}
	}

	public void insert_Contact(String contactName) throws InterruptedException, IOException {
		/* To Insert Contact to SMS*/
		try {
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_MENU);
			minWait();
			clickBtn(AOSP_Locators_SMS_MMS.insertContact);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_UP);
			minWait();
			enterTextToInputField(AOSP_Locators_SMS_MMS.find_Contacts, contactName);
			minWait();
			clickBtn(AOSP_Locators_SMS_MMS.searchedContact);
			minWait();
		} catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "Error in Inserting the Contact.");
		}
	}

	public void insert_From_Template(AndroidElement templateToClick) throws InterruptedException {
		/* Method is to insert the Template */
		try {
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_MENU);
			minWait();
			clickBtn(AOSP_Locators_SMS_MMS.insertFromTemplate);
			minWait();		
			templateToClick.click();
			minWait();
		} catch (Exception e) {
			for (int i = 0; i < 15; i++) {
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
				Thread.sleep(500);
			}
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
			minWait();
		}		
	}

	public void importContacts_from_VCF() throws InterruptedException {

		try {
			minWait();
			clickBtn(AOSP_Locators_SMS_MMS.importContacts);
			minWait();
			clickBtn(AOSP_Locators_SMS_MMS.importFrom_VCF_File);
			customWait(4000);
		} catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "Error in importing .vcf File or .vcf File NOT found.");
		}
	}

	public void add_Contacts(int no_Of_Contacts) throws InterruptedException {
		/*
		 * This Method is to Add First 'n' displayed Contacts to Messaging "To" field.
		 * Precondition : 1. 'n' Contacts should be Present in the Contacts list.
		 * 				  2. User should be in new Message Window.
		 * 				  3. Specify "ErrorMsg" Text and Adb "filename" of which the current Method is invoked.
		 */
		try {
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_MENU);
			minWait();
			clickBtn(AOSP_Locators_SMS_MMS.add_Contact);
			minWait();
			for (int i=1; i<=no_Of_Contacts;i++) {
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
				customWait(500);
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
				customWait(500);
			}
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_MENU);
			minWait();
			clickBtn(AOSP_Locators_SMS_MMS.done);
			minWait();
		} catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "Error in Adding the Contacts.");
		}
	}

	public void clear_ToField(int no_Of_Contacts) throws InterruptedException {
		/*
		 * This Method is to Clear the Added Contacts in the "To" Field.
		 * Precondition : Contacts should be Added in the "To" Field.
		 */
		try {
			minWait();
			aDriver.findElement(By.xpath("//android.widget.MultiAutoCompleteTextView[@index='0']")).click();
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_MOVE_END);
			minWait();
			for (int i = 0; i <no_Of_Contacts ; i++) {
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_CLEAR);
				minWait();			
			}
		} catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "Error in Clearing the 'To' field.");
		}
	}

	public void clear_TypeMesssage() throws InterruptedException {
		/*
		 * This Method is to Clear the typed Message.
		 * Precondition :  User should be in New Message window.
		 */
		try {
			minWait();
			aDriver.findElement(By.xpath("//android.widget.EditText[@index='0']")).click();
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_MENU);
			minWait();
			clickBtn(AOSP_Locators_SMS_MMS.select_All);
			minWait();
			aDriver.longPressKeyCode(AndroidKeyCode.KEYCODE_CLEAR);
			minWait();
		} catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "Error in clearing the 'Type Message' field.");
		}
	}

	public void paste_Message() throws InterruptedException {
		/*
		 * This Method is to paste the copied or cut Message.
		 * Precondition :  User should be copied or cut with Message.
		 */
		try {
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_MENU);
			minWait();
			clickBtn(AOSP_Locators_SMS_MMS.paste);
			minWait();
		} catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "Error in pasting the Text.");
		}
	}

	public void copy_Full_TextMessage() throws InterruptedException {
		/*
		 * This Method is to COPY the typed Message through "Select All" or "Select" option.
		 * "no_Of_Char" to copy through Select Option.
		 * Precondition :  User should be typed with Message.
		 */
		try {
			minWait();
			aDriver.findElement(By.xpath("//android.widget.EditText[@index='0']")).click();
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_MENU);
			minWait();
			clickBtn(AOSP_Locators_SMS_MMS.select_All);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_MENU);
			minWait();
			clickBtn(AOSP_Locators_SMS_MMS.copy);
			minWait();
		} catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "Error in copying the Message.");
		}
	}

	public void copy_Message(int no_Of_Char) throws InterruptedException {
		try {
			minWait();
			aDriver.findElement(By.xpath("//android.widget.EditText[@index='0']")).click();
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_MENU);
			minWait();
		} catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "Error in Copying the Message.");
		}
	}

	public void cut_Message() throws InterruptedException {
		/*
		 * This Method is to CUT the typed Message through "Select All" option.
		 * Precondition :  User should be typed with Message.
		 */
		try {
			minWait();
			aDriver.findElement(By.xpath("//android.widget.EditText[@index='0']")).click();
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_MENU);
			minWait();
			clickBtn(AOSP_Locators_SMS_MMS.select_All);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_MENU);
			minWait();
			clickBtn(AOSP_Locators_SMS_MMS.cut);
			minWait();
		} catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "Error in 'Cut' Message.");
		}
	}

	public void validate_Copy_or_Cut_Paste(String copy) throws InterruptedException {

		SoftAssert sf = new SoftAssert();

		try {
			minWait();
			String copy1 = aDriver.findElement(By.xpath("//android.widget.EditText[@index='0']")).getText();
			if(check=copy.equals(copy1)) {
				APP_LOGS.info("String Copied Successfully");
				sf.assertTrue(check, "Validation is Pass");
			}else {
				APP_LOGS.info("String NOT copied");
				sf.fail();
				test.log(LogStatus.ERROR, "Error in validating Copy/Cut and paste Functionality. ");
			}
		} catch (Exception e) {
			e.printStackTrace();
			sf.fail();
			test.log(LogStatus.ERROR, "Error in validating Copy/Cut and paste Functionality. ");
		}
		sf.assertAll();
	}

	public void deleteAllContacts() throws InterruptedException {

		try {
			minWait();
			if(!isElementExist(AOSP_Locators_SMS_MMS.importContacts)) {
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_MENU);
				minWait();
				clickBtn(AOSP_Locators_SMS_MMS.select);
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_MENU);
				minWait();
				clickBtn(AOSP_Locators_SMS_MMS.select_All);
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_MENU);
				minWait();
				clickBtn(AOSP_Locators_SMS_MMS.delete_InMenu);
				minWait();
				clickBtn(AOSP_Locators_SMS_MMS.delete_Confirm);
				minWait();
			}else {
				APP_LOGS.info("No contacts Found");
				Reporter.log("No contacts Found");
			}
		} catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "Error in Deleting the Contacts.");
		}
	}

	public void launchDialer() {
		SoftAssert sf = new SoftAssert();
		try {
			minWait();
			Runtime.getRuntime().exec("adb shell am start -n com.android.dialer/com.android.dialer.DialerMainActivity");
			minWait();
		} catch (Exception e) {
			e.printStackTrace();
			sf.fail();
			test.log(LogStatus.ERROR, "Dialer NOT launched.");
		}
		sf.assertAll();
	}

	public void enterNumber_Dialer(String number) throws InterruptedException, IOException {

		try {
			minWait();
			Runtime.getRuntime().exec("adb shell input text "+number);
			minWait();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void dialCall() throws InterruptedException {
		try {
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_CALL);
			minWait();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void endCall() throws InterruptedException {
		try {
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_ENDCALL);
			minWait();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void sendSMS_FromDialer(String number) throws InterruptedException, IOException {
		try {
			minWait();
			Runtime.getRuntime().exec("adb shell input text "+number);
			customWait(3000);
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_MENU);
			minWait();
			clickBtn(AOSP_Locators_SMS_MMS.sendMessage_Dialer);
			minWait();
		} catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "Error in Sending SMS through Dialer.");
		}
	}

	public void validate_MessageInDraft() throws InterruptedException {

		SoftAssert sf = new SoftAssert();
		minWait();
		if (isElementExist(AOSP_Locators_SMS_MMS.draft_Text)) {
			check=true;
			APP_LOGS.info("Message found in Draft");
			sf.assertTrue(check, "Message found in Draft, valildation pass");
		} else {
			sf.fail();
			test.log(LogStatus.ERROR, "Message not found in Draft, validation Fail");
		}
		sf.assertAll();	
		minWait();
	}

	public void press_CenterKey() throws InterruptedException {
		try {
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void validate_DiscardPopUp() throws InterruptedException {
		SoftAssert sf = new SoftAssert();
		minWait();
		if (isElementExist(AOSP_Locators_SMS_MMS.discard_Popup)) {
			check=true;
			APP_LOGS.info("Discard Popup displayed Succefully");
			sf.assertTrue(check, "Discard Popup displayed, Validation Pass");
		} else {
			sf.fail();
			test.log(LogStatus.ERROR, "Discard Popup NOT displayed, Validation Fail");
		}
		sf.assertAll();
	}

	public void navigateAttachPhotosVideos() throws InterruptedException {
		/* To Attach Photos videos from Gallery for MMS. */
		try {
			minWait();
			aDriver.findElement(By.xpath("//android.widget.EditText[@index='0']")).click();
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_MENU);
			minWait();
			clickBtn(AOSP_Locators_SMS_MMS.attachPhotosVideos);
			minWait();
		} catch (Exception e) {
			e.printStackTrace();
		}
			
	}

	public void navigateAttachOthers() throws InterruptedException {
		/* Navigates to */
		try {
			minWait();
			aDriver.findElement(By.xpath("//android.widget.EditText[@index='0']")).click();
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_MENU);
			minWait();
			clickBtn(AOSP_Locators_SMS_MMS.attachOthers);
			minWait();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void clickOnAttach_Options(AndroidElement optionToClick) throws InterruptedException {
		/* Clicks on Capture Picture in the Attach Others option.*/
		try {
			minWait();		
			for (int i = 0; i < 8; i++) {
				if (isElementExist(optionToClick)) {
					clickBtn(optionToClick);
					minWait();
					break;
				} else {
					aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
					Thread.sleep(300);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "Error in click on 'Capture Picturre'");
		}
	}

	public void capturePic_ADD() throws InterruptedException, IOException {
		/* Captures the image and adds to the Message (MMS)
		 * Precondition : User should navigate to Camera via "Attach others" Option
		 */
		try {
			customWait(3000);
			clickBtn(AOSP_Locators_SMS_MMS.photoCaptureIcon);
			customWait(2000);
			clickBtn(AOSP_Locators_SMS_MMS.OK);
			minWait();
		} catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "Error in adding Picturre to MMS");
		}
	}

	public void captureVideo_ADD() throws InterruptedException {
		/* Captures the Video and adds to the Message (MMS)
		 * Precondition : User should navigate to Camera via "Attach others" Option
		 */
		
		customWait(2000);
		
		
	}










}
