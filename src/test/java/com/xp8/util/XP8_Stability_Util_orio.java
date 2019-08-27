package com.xp8.util;

import java.awt.AWTException;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.asserts.SoftAssert;

import com.google.common.collect.ImmutableMap;
import com.graphics.gui.JsonFileReaderAndWriter;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import application.AllQA;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.AndroidKeyCode;

public class XP8_Stability_Util_orio extends BaseUtil {

	public static ExtentReports extent;
	public static ExtentTest test;

	public Process p;

	boolean value = false;
	public String p_Id = ""; // Primary Device Serial Number.
	public String r_Id = ""; // Reference Device Serial Number.
	public String p_b_No = ""; // Primary Device Build Number.
	public String r_b_No = ""; // Reference Device Build Number.
	public String pryNum = AllQA.PRIMARYDEVMDN; // Reference Device MDN.
	public String refNum = AllQA.REFERENCEDEVMDN; // Reference Device MDN.

	public void fetch_Devices_Details()
			throws InterruptedException, FileNotFoundException, IOException, ParseException {

		minWait();
		p_Id = JsonFileReaderAndWriter.primaryDevIdReader();
		r_Id = JsonFileReaderAndWriter.ReadRefDeviceId();
		p_b_No = JsonFileReaderAndWriter.primaryDevFirmwareReader();
		r_b_No = JsonFileReaderAndWriter.ReadRefDeviceFirmWare();
	}

	public boolean check = false;
	public boolean check1 = false;
	public boolean check2 = false;
	public boolean check3 = false;
	public boolean check4 = false;

	public boolean scrollToTextClick(String text) {
		/*
		 * Method used to select an element on the page by scrolling the Scroll
		 * View/List View
		 */

		boolean check = false;
		try {
			String scrollable = "new UiScrollable(new UiSelector().scrollable(true))";
			String textElement = ".scrollIntoView(new UiSelector().text(\"" + text + "\"))";
			aDriver.findElementByAndroidUIAutomator(scrollable + textElement).click();
			APP_LOGS.info("Searched application is found sucessfully : ");
			check = true;
			return check;
		} catch (Exception e) {
			return check;
		}
	}

	public boolean scrollToTextLongPress(String text) {
		/*
		 * Method used to select an element on the page by scrolling the Scroll
		 * View/List View
		 */

		boolean check = false;
		try {
			TouchAction ta = new TouchAction(aDriver);
			String scrollable = "new UiScrollable(new UiSelector().scrollable(true))";
			String textElement = ".scrollIntoView(new UiSelector().text(\"" + text + "\"))";
			AndroidElement ele = aDriver.findElementByAndroidUIAutomator(scrollable + textElement);
			ta.longPress(ele).perform();
			APP_LOGS.info("Searched application is found sucessfully : ");
			check = true;
			return check;
		} catch (Exception e) {
			return check;
		}
	}

	/*public void clickOn_Wifi_Lnk() {

		try {
			Locators_Wifi.WiFi_Lnk.click();

		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->clickOn_Wifi_Lnk()");
			e.printStackTrace();

		} catch (Exception e) {

			test.log(LogStatus.ERROR, "Exeption in ->clickOn_Wifi_Lnk()");
		}
	}*/

	public void deleteContacts() throws InterruptedException {
		try {
			minWait();
			System.out.println("IM in Contacts");
			if (isElementExist(Locators_WifiStability.no_Contacts)) {
				APP_LOGS.info("No Contact");
				System.out.println("No Contact");
				minWait();
			} else {
				clickBtn(Locators_WifiStability.deleteContactOptn1);
				minWait();
				clickBtn(Locators_WifiStability.Selection_menu);
				minWait();
				if (isElementExist(Locators_WifiStability.ALL_Selection_menu)) {
					clickBtn(Locators_WifiStability.ALL_Selection_menu);
					minWait();
				} else {
					minWait();
					clickBtn(Locators_WifiStability.one_Selection_menu);
				}
				clickBtn(Locators_WifiStability.OKBtn1);
				minWait();
				clickBtn(Locators_WifiStability.OKBtn);
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
			clickBtn(Locators_WifiStability.moreOptions);
			minWait();
			clickBtn(Locators_WifiStability.settings_Contact);
			minWait();
			clickBtn(Locators_WifiStability.defaultAccount_NewContacts);
			minWait();
			clickBtn(Locators_WifiStability.PHONE_RadioBtn);
			customWait(2000);
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			// clickBtn(Locators_WifiStability_ATT.navigateUp);
			// Runtime.getRuntime().exec("adb -s "+p_Id+" shell input keyevent 4");
			minWait();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void createContact(String name, String num) throws InterruptedException {
		/*
		 * Create contact
		 */
		try {

			minWait();
			Locators_WifiStability.AddtoContact.click();
			minWait();

			if (isElementExist(Locators_WifiStability.Choose_Phone)) {
				minWait();
				clickBtn(Locators_WifiStability.Choose_Phone);
				customWait(2000);
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
			}
			if (isElementExist(Locators_WifiStability.Contacts_Name)) {
				minWait();
			}

			customWait(4000);
			minWait();
			enterTextToInputField(Locators_WifiStability.name_NewContact, name);
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			minWait();
			enterTextToInputField(Locators_WifiStability.phone_NewContact, num);
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			minWait();
			enterTextToInputField(Locators_WifiStability.email_NewContact, "Sonimtech@gmail.com");
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			minWait();
			clickBtn(Locators_WifiStability.Save_ConatctIcon1);
			minWait();

			for (int i = 1; i <= 3; i++) {
				if (isElementExist(Locators_WifiStability.AllowOptn)) {
					clickBtn(Locators_WifiStability.AllowOptn);
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			test.log(LogStatus.ERROR, "No Such element Found");
		}
	}

	public void validateContactCreation(int n, SoftAssert soft) throws InterruptedException {
		/*
		 * Validate contact
		 */
		SoftAssert Sa = new SoftAssert();
		try {
			customWait(3000);
			String getcontactNameText = null;
			getcontactNameText = Locators_WifiStability.ContactTitle.getText();
			System.out.println(getcontactNameText);
			if (getcontactNameText.contains("Test")) {
				check1 = true;
				APP_LOGS.info("check1: " + check1);
				APP_LOGS.info("Contact Name is: " + getcontactNameText);
			} else {
				APP_LOGS.info("check1: " + check1);
				APP_LOGS.info("Test case failed");
			}

			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
			customWait(1000);
			String getPhoneNumText = Locators_WifiStability.contact_phnNum.getText();

			String phoneNum = getPhoneNumText.replaceAll("[^a-zA-Z0-9]", "");
			System.out.println(phoneNum);
			minWait();
			String refNumber = refNum.replaceAll("[^a-zA-Z0-9]", "");
			minWait();
			if (phoneNum.contains(refNumber) || phoneNum.equalsIgnoreCase(refNumber)) {
				check2 = true;
				APP_LOGS.info("check2: " + check2);
				APP_LOGS.info("Contact Phone Num is: " + phoneNum);
			} else {
				APP_LOGS.info("check2: " + check2);
			}

			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
			customWait(1000);
			String getEmailText = null;
			getEmailText = Locators_WifiStability.contact_Emailid.getText();
			// System.out.println(getEmailText);
			if (getEmailText.contains("Sonimtech")) {
				check3 = true;
				APP_LOGS.info("check3: " + check3);
				APP_LOGS.info("Contact email id is: " + getEmailText);
			} else {
				APP_LOGS.info("check3: " + check3);
			}

			if ((check1) && (check2) && (check3)) {
				check = true;
				APP_LOGS.info("Contact created with Name,phone_Num, Email_ID and Address is saved in phone memory");
				test.log(LogStatus.INFO, "Contact created saved in phone memory at iteration: " + n);
				soft.assertTrue(check, " ");
			} else {

				APP_LOGS.info("Test case failed");
				test.log(LogStatus.ERROR, "Contact creation failed at iteration: " + n);
				soft.fail();
			}
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
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
			getcontactNameText = Locators_WifiStability.ContactTitle.getText();
			System.out.println(getcontactNameText);
			if (getcontactNameText.contains("Test")) {
				check1 = true;
				APP_LOGS.info("check1: " + check1);
				APP_LOGS.info("Contact Name is: " + getcontactNameText);
			} else {
				APP_LOGS.info("check1: " + check1);
				APP_LOGS.info("Test case failed");
			}

			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
			customWait(1000);
			String getPhoneNumText = Locators_WifiStability.contact_phnNum.getText();

			String phoneNum = getPhoneNumText.replaceAll("[^a-zA-Z0-9]", "");
			System.out.println(phoneNum);
			minWait();
			if (phoneNum.contains(refNum) || phoneNum.equalsIgnoreCase(refNum)) {
				check2 = true;
				APP_LOGS.info("check2: " + check2);
				APP_LOGS.info("Contact Phone Num is: " + phoneNum);
			} else {
				APP_LOGS.info("check2: " + check2);
			}

			if ((check1) && (check2)) {
				check = true;
				APP_LOGS.info("Contact created with Name,phone_Num, Email_ID and Address is saved in SIM memory");
				test.log(LogStatus.INFO, "Contact created saved in SIM memory at iteration: " + n);
			} else {

				APP_LOGS.info("Test case failed");
				test.log(LogStatus.ERROR, "Contact creation failed at iteration: " + n);
			}
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
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
			if (isElementExist(Locators_WifiStability.messageField_O)) {
				enterTextToInputField(Locators_WifiStability.messageField_O, message);
				minWait();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void navigateTo_NewSMS_O() throws InterruptedException {
		/* Used to Navigate to New Message Page. */
		try {
			clickBtn(Locators_WifiStability.add_NewSMS_O);
			minWait();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void enter_Num_ToField_O(String number) throws InterruptedException {
		/* Method is used to Enter Number into TO Field. */
		try {
			minWait();
			enterTextToInputField(Locators_WifiStability.TO_Field_O, number);
			customWait(2000);
			System.out.println("Selecting COntact NAme");
			clickBtn(Locators_WifiStability.contact_Select);
			// customWait(2000);
		} catch (NoSuchElementException e) {
			e.printStackTrace();
		}
	}

	public void clickOn_Send_O() throws InterruptedException {
		/* Method used to click on Send Button. */
		try {
			customWait(500);
			clickBtn(Locators_WifiStability.send_Icon_O);
			minWait();
		} catch (NoSuchElementException e) {
			e.printStackTrace();
		}
	}

	public void validate_SentMessage_O(int n, SoftAssert soft) throws InterruptedException {
		/* To validate the Sent Message. */
		WebDriverWait wait = new WebDriverWait(aDriver, 60);

		try {
			wait.until(ExpectedConditions.visibilityOf(Locators_WifiStability.Delivered));
			if (isElementExist(Locators_WifiStability.Delivered)
					|| isElementExist(Locators_WifiStability.not_Sent_Text)) {
				check = true;
				APP_LOGS.info("Message sent Succeccfully");
				System.out.println("Sent");
				soft.assertTrue(check, "TestCase Valiation is PASS");
				test.log(LogStatus.PASS, "SMS Sent Successfully at iteration " + n);
			} else {
				APP_LOGS.info("SMS didn't sent");
				soft.fail();
				test.log(LogStatus.FAIL, "Message didn't sent at iteration " + n);
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
			clickBtn(Locators_WifiStability.firstSMS_InList_O);
			minWait();
			clickBtn(Locators_WifiStability.moreOption_O);
			minWait();
			clickBtn(Locators_WifiStability.delete_Messages);
			minWait();
			clickBtn(Locators_WifiStability.ALL_Msg);
			minWait();
			clickBtn(Locators_WifiStability.delete_Btn);
			minWait();
			clickBtn(Locators_WifiStability.Delete);
			minWait();
			// test.log(LogStatus.INFO, "SMS or MMS is deleted.");
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
			Runtime.getRuntime().exec("adb -s " + p_Id + " shell am start -a android.settings.AIRPLANE_MODE_SETTINGS");
			minWait();
			for (int i = 1; i <= 9; i++) {
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
			}
			enableSwitch(Locators_WifiStability.enabled_Airplane, Locators_WifiStability.disabled_Airplane,
					Locators_WifiStability.switch_widget);
			customWait(5000);
			clickBtn(Locators_WifiStability.OKBtn);
			minWait();
			disableSwitch(Locators_WifiStability.disabled_Airplane, Locators_WifiStability.enabled_Airplane,
					Locators_WifiStability.switch_widget);
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
			minWait();
			// Below Code To clear Battery PopUp.
			// Runtime.getRuntime().exec("adb -s "+r_Id+" shell input tap 777 1083");
			minWait();
			if (r_b_No.contains("8A.")) {
				if (r_b_No.contains("-10.") || r_b_No.contains("-30.")) {
					minWait();
					Runtime.getRuntime().exec("adb -s " + r_Id + " shell input keyevent 3");
					minWait();
					Runtime.getRuntime().exec("adb -s " + r_Id + " shell am force-stop com.android.mms");
					customWait(2000);
					Runtime.getRuntime()
							.exec("adb -s " + r_Id + " shell am start -a android.intent.action.SENDTO -d sms:" + pryNum
									+ " --es sms_body \"" + AutomationMessagee + "\" --ez exit_on_sent true");
					customWait(6000);
					Runtime.getRuntime().exec("adb -s " + r_Id + " shell input tap 540 1776");
					minWait();
					Runtime.getRuntime().exec("adb -s " + r_Id + " shell input tap 713 1098");
					minWait();
					Runtime.getRuntime().exec("adb -s " + r_Id + " shell input tap 918 1699");
					minWait();
					Runtime.getRuntime().exec("adb -s " + r_Id + " shell input tap 918 952");
					minWait();
				} else if (r_b_No.contains("-11.") || r_b_No.contains("-12.") || r_b_No.contains("-18.")
						|| r_b_No.contains("-26.") || r_b_No.contains("-29.")) {
					minWait();
					Runtime.getRuntime().exec("adb -s " + r_Id + " shell input keyevent 3");
					minWait();
					Runtime.getRuntime()
							.exec("adb -s " + r_Id + " shell am force-stop com.google.android.apps.messaging");
					customWait(2000);
					Runtime.getRuntime()
							.exec("adb -s " + r_Id + " shell am start -a android.intent.action.SENDTO -d sms:" + pryNum
									+ " --es sms_body \"" + AutomationMessagee + "\" --ez exit_on_sent true");
					customWait(6000);
					Runtime.getRuntime().exec("adb -s " + r_Id + " shell input tap 930 1756");
					minWait();
					Runtime.getRuntime().exec("adb -s " + r_Id + " shell input tap 930 976");
					minWait();
				} else if (r_b_No.contains("-15.")) {
					minWait();
					System.out.println("IN Android O");
					minWait();
					Runtime.getRuntime().exec("adb -s " + r_Id + " shell input keyevent 3");
					minWait();
					Runtime.getRuntime().exec("adb -s " + r_Id + " shell am force-stop com.verizon.messaging.vzmsgs");
					customWait(4000);
					Runtime.getRuntime()
							.exec("adb -s " + r_Id + " shell am start -a android.intent.action.SENDTO -d sms:" + pryNum
									+ " --es sms_body \"" + AutomationMessagee + "\" --ez exit_on_sent true");
					customWait(6000);
					System.out.println("Sending Message...");
					Runtime.getRuntime().exec("adb -s " + r_Id + " shell input tap 948 1769");
					minWait();
					Runtime.getRuntime().exec("adb -s " + r_Id + " shell input tap 948 1022");
					minWait();
				}
			} else if (r_b_No.contains("5SA.")) {
				minWait();
				Runtime.getRuntime().exec("adb -s " + r_Id + " shell input keyevent 3");
				minWait();
				Runtime.getRuntime().exec("adb -s " + r_Id + " shell am force-stop com.android.mms");
				customWait(2000);
				Runtime.getRuntime().exec("adb -s " + r_Id + " shell am start -a android.intent.action.SENDTO -d sms:"
						+ pryNum + " --es sms_body \"" + AutomationMessagee + "\" --ez exit_on_sent true");
				customWait(6000);
				Runtime.getRuntime().exec("adb -s " + r_Id + " shell input keyevent 66");
				minWait();
			}
		} catch (Exception e) {

		}
	}

	public void validate_RecievedMessage(int n, SoftAssert soft) throws InterruptedException {
		/* User should navigate to the Messaging APP home Page in the Primary device. */
		WebDriverWait wait = new WebDriverWait(aDriver, 80);

		try {
			wait.until(ExpectedConditions.visibilityOf(Locators_WifiStability.now_Text));
			customWait(8000);
			if (isElementExist(Locators_WifiStability.now_Text)
					|| isElementExist(Locators_WifiStability.not_Sent_Text)) {
				check = true;
				APP_LOGS.info("Message sent Succeccfully");
				soft.assertTrue(check, "TestCase Valiation is PASS");
				test.log(LogStatus.PASS, "Message Recieved Successfully at iteration : " + n);
			} else {
				APP_LOGS.info("SMS didn't sent");
				test.log(LogStatus.PASS, "Message didn't Recieved at iteration : " + n);
				soft.fail();
			}
		} catch (Exception e) {
			e.printStackTrace();
			soft.fail();
		}
	}

	public void validate_RecievedMessage_O(int n, SoftAssert soft) throws InterruptedException {
		/* User should navigate to the Messaging APP home Page in the Primary device. */

		WebDriverWait wait = new WebDriverWait(aDriver, 80);

		try {
			wait.until(ExpectedConditions.visibilityOf(Locators_WifiStability.Delivered));
			customWait(8000);
			clickBtn(Locators_WifiStability.firstSMS_InList_O);
			if (isElementExist(Locators_WifiStability.now_Text)
					|| isElementExist(Locators_WifiStability.unread_Conv_Messages)
					|| isElementExist(Locators_WifiStability.Delivered)
					|| isElementExist(Locators_WifiStability.not_Sent_Text)) {
				check = true;
				APP_LOGS.info("Message sent Succeccfully");
				System.out.println("Received");
				soft.assertTrue(check, "TestCase Valiation is PASS");
				test.log(LogStatus.PASS, "Message Recieved Successfully at iteration " + n);
			} else {
				APP_LOGS.info("SMS didn't sent");
				test.log(LogStatus.FAIL, "Message didn't Recieved at iteration " + n);
				soft.fail();
			}
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
		} catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.FAIL,
					"Expected condition failed: waiting for visibility of element located at iteration " + n);
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
			clickBtn(Locators_WifiStability.Search_ConatctIcon);
			customWait(2000);
			enterTextToInputField(Locators_WifiStability.findContacts_O, name);
			minWait();
			clickBtn(Locators_WifiStability.longpress_FirstContact_O);
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
			/*
			 * minWait(); clickBtn(Locators_WifiStability.SavedContact); minWait();
			 * clickBtn(Locators_WifiStability.contact_phnNum); minWait();
			 */
			customWait(4000);
			Runtime.getRuntime()
					.exec("adb -s " + p_Id + " shell am start -a android.intent.action.CALL -d tel:" + refNum);
			Thread.sleep(10000);
		} catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, " No Such Element Found");
		}
	}

	public void validateCallLog(String str, int n, String callType, SoftAssert soft)
			throws InterruptedException, IOException {
		/*
		 * Validate via log String that MO-Voice call initiated
		 */
		SoftAssert s1 = new SoftAssert();
		String value = null;
		customWait(10000);
		try {

			for (int j = 1; j <= 100; j++) {
				Process child = Runtime.getRuntime().exec("adb -s " + r_Id + " shell service call telecom 27");
				InputStream lsOut = child.getInputStream();
				InputStreamReader r = new InputStreamReader(lsOut);
				BufferedReader in = new BufferedReader(r);
				value = in.readLine();
				System.out.println(value);
				customWait(2000);
				if (value.contains("00000001")) {
					Runtime.getRuntime().exec("adb -s " + r_Id + " shell input keyevent 5");
					test.log(LogStatus.PASS, "MO-Voice call from " + callType + " is validated at : iteration " + n);
					soft.assertTrue(check, " ");
					break;
				}
				/*
				 * else if(value.contains("00000000")){ continue; }
				 */
				else {
					test.log(LogStatus.FAIL, "MO-Voice call Failed at : iteration " + n);
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
			Runtime.getRuntime().exec("adb -s " + r_Id + " shell input keyevent 5");
			Thread.sleep(2000);
		}
	}

	public void validateCallLog_Orio(String str, int n, String callType, SoftAssert soft)
			throws InterruptedException, IOException {
		/*
		 * Validate via log String that MO-Voice call initiated
		 */

		String value = null;
		customWait(10000);
		try {

			for (int j = 1; j <= 100; j++) {
				Process child = null;
				Process version = null;
				version = Runtime.getRuntime().exec("adb -s " + r_Id + " shell getprop ro.build.version.release");
				InputStream lsOut1 = version.getInputStream();
				InputStreamReader r1 = new InputStreamReader(lsOut1);
				BufferedReader in1 = new BufferedReader(r1);
				String value1 = in1.readLine();
				System.out.println(value1);
				if (r_b_No.contains("8A.")) {

					System.out.println("XP8");
					if (value1.contains("8.1")) {
						child = Runtime.getRuntime().exec("adb -s " + r_Id + " shell service call telecom 29");
					} else {
						child = Runtime.getRuntime().exec("adb -s " + r_Id + " shell service call telecom 27");
					}
				}
				if (r_b_No.contains("3A.")) {
					System.out.println("XP3");
					child = Runtime.getRuntime().exec("adb -s " + r_Id + " shell service call telecom 30");
				}

				else if (r_b_No.contains("5SA.")) {
					System.out.println("XP5");
					System.out.println(value1);

					if (value1.contains("8.1")) {
						child = Runtime.getRuntime().exec("adb -s " + r_Id + " shell service call telecom 30");
					} else {
						child = Runtime.getRuntime().exec("adb -s " + r_Id + " shell service call telecom 28");
					}
				}
				InputStream lsOut = child.getInputStream();
				InputStreamReader r = new InputStreamReader(lsOut);
				BufferedReader in = new BufferedReader(r);
				value = in.readLine();
				customWait(2000);
				System.out.println(value);
				if (value.contains("00000001")) {
					check = true;
					Runtime.getRuntime().exec("adb -s " + r_Id + " shell input keyevent 5");
					test.log(LogStatus.PASS, "MO-Voice call from " + callType + " is validated at : iteration " + n);
					soft.assertTrue(check, " ");
					break;
				} else {
					test.log(LogStatus.FAIL, "MO-Voice call Failed at : iteration " + n);
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
			Runtime.getRuntime().exec("adb -s " + r_Id + " shell input keyevent 5");
			Thread.sleep(2000);
		}
	}

	public void selectPage(WebElement selectpage) throws InterruptedException {
		try {
			minWait();
			clickBtn(selectpage);
		} catch (Exception e) {
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
			if (isElementExist(Locators_WifiStability.MoreOptions)) {
				clickBtn(Locators_WifiStability.MoreOptions);
				minWait();
			} else {
				clickBtn(Locators_WifiStability.MoreOptions1);
				minWait();
			}
			customWait(1000);
			clickBtn(Locators_WifiStability.callHistory_O);
			minWait();
			clickBtn(Locators_WifiStability.Call_Contact);
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
			Runtime.getRuntime().exec("adb -s " + p_Id + " shell am start -a android.settings.AIRPLANE_MODE_SETTINGS");
			minWait();
			enableSwitch(Locators_WifiStability.enabled_Airplane, Locators_WifiStability.disabled_Airplane,
					Locators_WifiStability.switch_widget);
			customWait(5000);
			disableSwitch(Locators_WifiStability.disabled_Airplane, Locators_WifiStability.enabled_Airplane,
					Locators_WifiStability.switch_widget);
			customWait(15000);
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public boolean validate_RIL_Logs(String filename, String validationString, String infoForFailure)
			throws InterruptedException {

		customWait(2000);
		boolean check1 = searchString(validationString, filename);
		SoftAssert sf = new SoftAssert();
		if (check1) {
			check = true;
			test.log(LogStatus.INFO, "Validated from RIL Logs : IMS is Enable");
		} else {
			test.log(LogStatus.INFO, "Validation failed from ADB Logs. ==>> " + infoForFailure);
		}
		return check1;
	}

	public boolean validateCSFB(String str, String fileName) throws IOException, InterruptedException {
		/*
		 * Validate via log String that MO-Voice call initiated
		 */

		customWait(4000);
		if (searchString(str, fileName)) {
			check = false;
			test.log(LogStatus.SKIP, "IMS Registered SIM is available");
		} else {
			check = true;
			test.log(LogStatus.INFO, "Non-IMS registered validated");
			// assertTrue(check);
		}
		return check;
	}

	public boolean validateIMSLog(String str, String fileName) throws IOException, InterruptedException {
		/*
		 * Validate via log String that MO-Voice call initiated
		 */
		SoftAssert s1 = new SoftAssert();

		if (searchString(str, fileName)) {
			check = true;
			test.log(LogStatus.INFO, "IMS is Enable");
			// s1.assertTrue(check, " ");
		} else {
			check = false;
			test.log(LogStatus.SKIP, "IMS is not enabled");

		}
		return check;
	}

	public void validateIMSLogSMS(String str, String fileName, String callType)
			throws IOException, InterruptedException {
		/*
		 * Validate via log String that MO-Voice call initiated
		 */
		SoftAssert s1 = new SoftAssert();

		if (searchString(str, fileName)) {
			check = true;
			test.log(LogStatus.INFO, "SMS over IMS registered from " + callType + " is validated ");
			s1.assertTrue(check, " ");
		} else {
			test.log(LogStatus.SKIP, "IMS Registered SIM not available ");
		}
	}

	// This method will take adb log
	public static void startLog(String fileName) throws AWTException, InterruptedException {

		customWait(2000);
		try {
			APP_LOGS.info("Adb log started sucessfully ");
			Runtime.getRuntime().exec(
					"cmd /C \"adb logcat -b all -v threadtime>src/test/resources/adbLogs/" + fileName + ".txt \"");
			Thread.sleep(2000);
		} catch (Exception e) {
			System.out.println("Something goes Wrong!!!");
			e.printStackTrace();
		}
	}

	public void selectWIFIOptn() throws InterruptedException {
		/*
		 * Select Wifi option from settings List
		 */
		SoftAssert Sa = new SoftAssert();
		try {
			customWait(1000);
			// ScrollToElement(Locators_WifiStability.SettingsList, "Wi-Fi");
			customWait(2000);
			clickBtn(Locators_WifiStability.wifi);
			minWait();
			APP_LOGS.info("Selected WIFI option");
		} catch (Exception e) {
			e.printStackTrace();
			APP_LOGS.info("WIFI : No such Element found");
			test.log(LogStatus.ERROR, "Wi-Fi No such Element found");
		}
	}

	public void selectSSIDwifi() throws InterruptedException {
		/*
		 * Select IDC wifi which is available
		 */
		SoftAssert Sa = new SoftAssert();
		try {
			customWait(4000);
			for (int i = 1; i <= 50; i++) {
				if (isElementExist(Locators_WifiStability.wifi_IDC)) {
					customWait(2000);
					clickBtn(Locators_WifiStability.wifi_IDC);
					APP_LOGS.info("IDC available secured wifi is Selected");
					System.out.println("IDC available secured wifi is Selected");
					break;
				} else if (isElementExist(Locators_WifiStability.wifi_Dellas)) {
					minWait();
					clickBtn(Locators_WifiStability.wifi_Dellas);
					APP_LOGS.info("Dellas available secured wifi is Selected");
					break;
				} else if (isElementExist(Locators_WifiStability.wifi_Cannada)) {
					minWait();
					clickBtn(Locators_WifiStability.wifi_Cannada);
					APP_LOGS.info("Cannada available secured wifi is Selected");
					break;
				} else {
					minWait();
					aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
					continue;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			APP_LOGS.info("WIFI : No such Element found");
			test.log(LogStatus.ERROR, "Wi-Fi is not available : No such Element found");
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
			clickBtn(Locators_WifiStability.wifi_IDC_ForgetBtn);
			APP_LOGS.info("IDC available secured wifi is disconnected");
			System.out.println("Disconnected");
			customWait(4000);
		} catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "Wi-Fi Forget button: No such Element found");
		}
	}

	public void remove_connectedNtwrk() {
		try {
			
			boolean check1 = scrollToTextClick("Connected");
			boolean check2 = scrollToText("Check password and try again");
			if (check1 == true) {
				customWait(3000);
				scrollToTextClick("FORGET");
			} else if (check2 == true) {
				scrollToTextLongPress("Check password and try again");
				scrollToTextClick("Forget network");
			}

		}

		catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.ERROR, "Error in locators->remove_connectedNtwrk()");
		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exeption in ->remove_connectedNtwrk()");
		}
	}

	public void validate_And_BrowseIn_Chrome1(String url, AndroidElement autoSuggestion, AndroidElement expectedElement,
			AndroidElement expectedElement1) throws InterruptedException, IOException {
		/*
		 * Validates MobileData enabality by launching the Chrome & browsing google
		 * page.
		 */
		SoftAssert sf = new SoftAssert();
		try {
			customWait(2000);
			enterTextToInputField(Locators_WifiStability.urlBar_Chrome, url);
			customWait(4000);
			clickBtn(autoSuggestion);
			customWait(12000);
			try {
				if (expectedElement.isDisplayed()) {
					check = true;
					APP_LOGS.info("Suggeted URL Page Displayed Succeessfully");
					sf.assertTrue(check, "TestCase Valiation is PASS");
					test.log(LogStatus.PASS, "Test case Status is Pass.");
					test.log(LogStatus.INFO, "URL loaded successfully : " + url);
				} else {
					APP_LOGS.info("Suggeted URL page didn't get Loaded");
					sf.fail();
					test.log(LogStatus.FAIL, "Test Case Status is Fail.");
					test.log(LogStatus.INFO, "URL didn't load.");
				}
			} catch (Exception e) {
				if (expectedElement1.isDisplayed()) {
					check = true;
					APP_LOGS.info("Suggeted URL Page Displayed Succeessfully");
					sf.assertTrue(check, "TestCase Valiation is PASS");
					test.log(LogStatus.PASS, "Test case Status is Pass.");
					test.log(LogStatus.INFO, "URL loaded successfully : " + url);
				} else {
					APP_LOGS.info("Suggeted URL page didn't get Loaded");
					sf.fail();
					test.log(LogStatus.FAIL, "Test Case Status is Fail.");
					test.log(LogStatus.INFO, "URL didn't load.");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		// closeAllTabs();
	}

	public void selectRefresh() throws InterruptedException {
		/*
		 * Refresh the List SSID
		 */
		try {
			minWait();
			// aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_MENU);
			clickBtn(Locators_WifiStability.MoreOptions);
			minWait();
			clickBtn(Locators_WifiStability.RefreshOptn);
			minWait();
			// refresh btn
			customWait(5000);
		} catch (Exception e) {
			e.printStackTrace();
			APP_LOGS.info("Wi-Fi Forget button: No such Element found");
			test.log(LogStatus.ERROR, "Wi-Fi : No such Element found");
		}
	}

	public void enterPassword() throws InterruptedException, IOException {
		/*
		 * enter Password for SSID
		 */
		try {
			minWait();
			if (isElementExist(Locators_WifiStability.SSIDTxt)) {
				String getSSIDTitle = Locators_WifiStability.SSIDTxt.getText();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
				minWait();
				changeToNumberMode();
				customWait(2000);
				if (getSSIDTitle.equalsIgnoreCase("IDCSONWAP")) {
					minWait();
					clickBtn(Locators_WifiStability.wifi_IDC_Psswd);
					customWait(4000);
					// enterTextToInputField(Locators_WifiStability.wifi_IDC_Psswd,"1dcS0n1md0tc0MbLr");
					Runtime.getRuntime().exec("adb -s " + p_Id + " shell input text 1dcS0n1md0tc0MbLr");
					customWait(3000);
				}
				minWait();
				String psswrd = Locators_WifiStability.wifi_IDC_Psswd.getText();
				System.out.println(psswrd);
				customWait(1000);
				clickBtn(Locators_WifiStability.wifi_IDC_ConnectBtn);
				customWait(3000);
			}
		} catch (Exception e) {
			e.printStackTrace();
			APP_LOGS.info("Wi-Fi Forget button: No such Element found");
		}
	}

	public void changeToNumberMode() throws InterruptedException {

		try {
			minWait();
			Locators_WifiStability.wifi_IDC_Psswd.sendKeys("123");
			customWait(1500);
			String text = Locators_WifiStability.wifi_IDC_Psswd.getText();
			System.out.println(text);
			if (!text.equals("123")) {
				for (int i = 0; i < 3; i++) {
					aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_POUND);
					minWait();
				}
			}
			Locators_WifiStability.wifi_IDC_Psswd.clear();
		} catch (Exception e) {
			e.printStackTrace();
			APP_LOGS.info("Wi-Fi Forget button: No such Element found");
		}
	}

	public void changeToNumberModeSMS() throws InterruptedException {

		try {
			minWait();
			Locators_WifiStability.wifi_IDC_Psswd.sendKeys("123");
			customWait(1500);
			String text = Locators_WifiStability.wifi_IDC_Psswd.getText();
			System.out.println(text);
			if (!text.equals("123")) {
				for (int i = 0; i < 3; i++) {
					aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_POUND);
					minWait();
				}
			}
			Locators_WifiStability.wifi_IDC_Psswd.clear();
		} catch (Exception e) {
			e.printStackTrace();
			APP_LOGS.info("Wi-Fi Forget button: No such Element found");
			test.log(LogStatus.ERROR, " No such Element found");
		}
	}

	public void validateWifiConnect(int n, SoftAssert soft) throws InterruptedException, IOException {
		/*
		 * Validate wifi connection
		 */
		SoftAssert sf = new SoftAssert();
		try {
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			minWait();
			String getTxt = Locators_WifiStability.connectedWIFI1.getText();
			System.out.println(getTxt);
			if (getTxt.contains("IDCSONWAP")) {
				check = true;
				APP_LOGS.info("Connected to Secured Wifi succesfully.");
				System.out.println("Connected to Secured Wifi succesfully.");
				test.log(LogStatus.PASS, "Connected and Disconnected to Secured Wifi succesfully at iteration: " + n);
				soft.assertTrue(check, "Validation is Pass");
			} else if (getTxt.contains("1234567890sonim")) {
				check = true;
				APP_LOGS.info("Connected to Secured Wifi succesfully.");
				test.log(LogStatus.PASS,
						"Connected and Disconnected to Secured Wifi--Dellas succesfully at iteration: " + n);
				soft.assertTrue(check, "Validation is Pass");
			} else if (getTxt.contains("dlink-F092-media")) {
				check = true;
				APP_LOGS.info("Connected to Secured Wifi succesfully.");
				test.log(LogStatus.PASS,
						"Connected and Disconnected to Secured Wifi--Cannada succesfully at iteration: " + n);
				soft.assertTrue(check, "Validation is Pass");
			} else {
				APP_LOGS.info("Connected to Secured Wifi is unsuccesful.");
				test.log(LogStatus.FAIL,
						"Connected and Disconnected to Secured Wifi is unsuccesful at iteration: " + n);
				soft.fail();
			}
		} catch (Exception e) {
			APP_LOGS.info("No Element found.");
			test.log(LogStatus.FAIL, "Secured Wifi is unsuccesful at iteration: " + n);
			soft.fail();
		}
	}

	public void validate_ON_OFF_WiFiFeature(WebElement feature, int n) throws InterruptedException, IOException {
		/*
		 * validate Turn On and OFF wifi toggle button
		 */
		SoftAssert S1 = new SoftAssert();
		try {
			customWait(2000);
			enableFeature(Locators_WifiStability.enabled, Locators_WifiStability.disabled,
					Locators_WifiStability.switch_Title);
			minWait();

			if (isElementExist(Locators_WifiStability.enabled)) {
				check1 = true;
				APP_LOGS.info(" check1");
			}
			customWait(3000);
			disableFeature(Locators_WifiStability.enabled, Locators_WifiStability.disabled);
			if (isElementExist(Locators_WifiStability.disabled)) {
				check2 = true;
				APP_LOGS.info(" ");
			}
			if ((check1) && (check2)) {
				check = true;
				APP_LOGS.info(" Turn ON and OFF feature is verified");
				test.log(LogStatus.PASS, "Turn ON and OFF WIFI feature is verified at iteration: " + n);
				S1.assertTrue(check, " ");
			} else {
				APP_LOGS.info(" check1" + check1 + "check2" + check2);
				test.log(LogStatus.FAIL, "No Such Element Found");
				APP_LOGS.info(" Turn ON and OFF feature is not verified at iteration: " + n);
				S1.fail();
			}
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
		} catch (Exception e) {
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
			if (isElementExist(Locators_WifiStability.AccptBtn)) {
				Locators_WifiStability.AccptBtn.click();
			}
			customWait(4000);
			if (isElementExist(Locators_WifiStability.NextIcon)) {
				Locators_WifiStability.NextIcon.click();
			}
			customWait(4000);
			if (isElementExist(Locators_WifiStability.NothanksBtn)) {
				Locators_WifiStability.NothanksBtn.click();
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
			// acceptDefault();
			customWait(2000);
			/*
			 * clickBtn(Locators_WifiStability.DefaultUrlTxt);
			 * enterTextToInputField(Locators_WifiStability.DefaultUrlTxt,newurl);
			 */
			Runtime.getRuntime()
					.exec("adb -s " + p_Id + " shell am start -a android.intent.action.VIEW -d http:/" + newurl);
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

	public void validateUrlEntered(int n, SoftAssert soft) {
		/*
		 * validate the website page is loaded via adb logs
		 */
		SoftAssert S2 = new SoftAssert();
		try {
			customWait(20000);
			if (isElementExist(Locators_WifiStability.networkNotAvailable)
					| isElementExist(Locators_Stability.WebPageBlocked)) {
				test.log(LogStatus.INFO, "Entered Website page not Loaded successfully at iteration: " + n);
				soft.fail();
			} else {
				check = true;
				APP_LOGS.info("Url entered sucessfully.");
				test.log(LogStatus.INFO, "Entered Website page Loaded Successfully at iteration: " + n);
				soft.assertTrue(check, " ");
			}
		} catch (Exception e) {
			APP_LOGS.info("Element not Found");
			e.printStackTrace();
			soft.fail();
		}
	}

	public static void imsLogs(String fileName) throws AWTException, InterruptedException {

		String path = System.getProperty("user.dir");
		customWait(1000);
		try {
			Runtime.getRuntime().exec("cmd /C \"adb logcat -b all -v time>" + path + "\\src\\test\\resources\\adbLogs\\"
					+ fileName + ".txt\"");
			Thread.sleep(1000);
		} catch (Exception e) {
			Reporter.log("Something goes Wrong!!!");
			e.printStackTrace();
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
				if (isElementExist(Locators_WifiStability.firstSMS_InList)) {
					minWait();
					TouchAction touchaction = new TouchAction(aDriver);
					touchaction.longPress(Locators_WifiStability.firstSMS_InList).perform().release();
					minWait();
					clickBtn(Locators_WifiStability.delete_Icon_SMS);
					minWait();
					if (isElementExist(Locators_WifiStability.delete_Confirm)) {
						clickBtn(Locators_WifiStability.delete_Confirm);
						minWait();
					} else {
						clickBtn(Locators_WifiStability.delete_moreOption);
						minWait();
					}
				} else if (isElementExist(Locators_WifiStability.first_sms_Thread)) {
					minWait();
					TouchAction touchaction = new TouchAction(aDriver);
					touchaction.longPress(Locators_WifiStability.first_sms_Thread).perform().release();
					minWait();
					clickBtn(Locators_WifiStability.delete_Icon);
					minWait();
					if (isElementExist(Locators_WifiStability.delete_Confirm)) {
						clickBtn(Locators_WifiStability.delete_Confirm);
						minWait();
					} else {
						clickBtn(Locators_WifiStability.delete_moreOption);
						minWait();
					}
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
	}

	// ======================================================================================================

	public void navigateTo_NewMessage() throws InterruptedException, IOException {
		SoftAssert sf = new SoftAssert();
		try {
			// launch_an_app("messaging");
			minWait();
			// aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_MENU);
			minWait();
			if (isElementExist(Locators_WifiStability.add_NewSMS)) {
				clickBtn(Locators_WifiStability.add_NewSMS);
				minWait();
			}

			if (isElementExist(Locators_WifiStability.new_Message_Icon)) {
				clickBtn(Locators_WifiStability.new_Message_Icon);
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

	public void validate_CharacterAndPageNumber(WebElement charAndPageNum, String expectedcharAndPageNum, int n)
			throws InterruptedException {
		/*
		 * This Meathod is to validate Characters and Page Number Precondition : User
		 * should be in Message window with Typed Message
		 */
		SoftAssert sf = new SoftAssert();
		try {
			minWait();
			String charAndPageNum1 = null;
			if (isElementExist(Locators_WifiStability.zero_Characters_FirstPage)) {
				System.out.println("Im out");
				charAndPageNum1 = charAndPageNum.getText();
			} else if (isElementExist(Locators_WifiStability.zero_Characters_FirstPage1)) {
				System.out.println("Im in");
				charAndPageNum1 = Locators_WifiStability.zero_Characters_FirstPage1.getText();
			}

			if (check = charAndPageNum1.equals(expectedcharAndPageNum)) {
				APP_LOGS.info("Character and Page Number Displayed is correct");
				sf.assertTrue(check, "Validation is Pass");
			} else {
				APP_LOGS.info("Character and Page Number Displayed is NOT correct");
				test.log(LogStatus.ERROR,
						"Error in validating the Number of characters and Page Number at iteration: " + n);

			}
		} catch (Exception e) {
			e.printStackTrace();
			// sf.fail();
			// test.log(LogStatus.ERROR, "Error in validating the Number of characters and
			// Page Number");
		}
	}

	public void validate_CharacterAndPageNumber_O(WebElement charAndPageNum, String expectedcharAndPageNum, int n)
			throws InterruptedException {
		/*
		 * This Meathod is to validate Characters and Page Number Precondition : User
		 * should be in Message window with Typed Message
		 */
		SoftAssert sf = new SoftAssert();
		try {
			minWait();
			String charAndPageNum1 = null;
			if (isElementExist(Locators_WifiStability.zero_Characters_FirstPage_O)) {
				System.out.println("Im out");
				charAndPageNum1 = charAndPageNum.getText();
			}
			System.out.println(charAndPageNum1);
			if (check = charAndPageNum1.equals(expectedcharAndPageNum)) {
				APP_LOGS.info("Character and Page Number Displayed is correct");
				sf.assertTrue(check, "Validation is Pass");
			} else {
				APP_LOGS.info("Character and Page Number Displayed is NOT correct");
				test.log(LogStatus.ERROR,
						"Error in validating the Number of characters and Page Number at iteration: " + n);

			}
		} catch (Exception e) {
			e.printStackTrace();
			// test.log(LogStatus.ERROR, "Error in validating the Number of characters and
			// Page Number");
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
			clickBtn(Locators_WifiStability.add_NewSMS);
			minWait();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void delete_SMS1() throws InterruptedException {
		/* This Method delete the First Thread in the List. */

		try {
			minWait();
			clickBtn(Locators_WifiStability.firstSMS_InList1);
			minWait();
			clickBtn(Locators_WifiStability.moreOptions);
			minWait();
			clickBtn(Locators_WifiStability.delete_moreOption);
			minWait();
			clickBtn(Locators_WifiStability.delete_Confirm);
			minWait();
			// test.log(LogStatus.INFO, "SMS or MMS is deleted.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void delete_SMS() throws InterruptedException {
		/* This Method delete the First Thread in the List. */

		try {
			System.out.println("IN Delete");
			minWait();
			if (isElementExist(Locators_WifiStability.firstSMS_InList)) {
				clickBtn(Locators_WifiStability.firstSMS_InList);
				minWait();
				clickBtn(Locators_WifiStability.moreOptions);
				minWait();
				clickBtn(Locators_WifiStability.delete_Thread);
				minWait();
				clickBtn(Locators_WifiStability.delete_Confirm);
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
			clickBtn(Locators_WifiStability.send_Icon1);
			minWait();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void navigateTo_NewSMS1() throws InterruptedException {
		/* Used to Navigate to New Message Page. */
		try {
			clickBtn(Locators_WifiStability.add_NewSMS1);
			minWait();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void enterText_MessageField1(String message) throws InterruptedException {
		/* Methos is Used enter Message into Message Field. */
		try {
			minWait();
			enterTextToInputField(Locators_WifiStability.messageField1, message);
			minWait();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void enter_Num_ToField1(String number) throws InterruptedException {
		/* Method is used to Enter Number into TO Field. */
		try {
			minWait();
			enterTextToInputField(Locators_WifiStability.TO_Field1, number);
			customWait(2000);
			clickBtn(Locators_WifiStability.contactPicker);
			customWait(2000);
		} catch (NoSuchElementException e) {
			e.printStackTrace();
		}
	}

	public void enter_Num_ToField(String number) throws InterruptedException {
		/* Method is used to Enter Number into TO Field. */
		try {
			customWait(2000);
			enterTextToInputField(Locators_WifiStability.TO_Field, number);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			minWait();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void enterText_MessageField(String message) throws InterruptedException {
		/* Methos is Used enter Message into Message Field. */
		try {
			customWait(1000);
			enterTextToInputField(Locators_WifiStability.messageField, message);
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
			if (isElementExist(Locators_WifiStability.TO_Field)) {
				enterTextToInputField(Locators_WifiStability.TO_Field, cellNo);
				minWait();
			}

			if (isElementExist(Locators_WifiStability.TO_Field_enter)) {
				enterTextToInputField(Locators_WifiStability.TO_Field_enter, cellNo);
				minWait();
			}

			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_ENTER);
		} catch (Exception e) {
			e.printStackTrace();
			// sf.fail();
			test.log(LogStatus.ERROR, "Error in entering number to ToField");
		}
		sf.assertAll();
	}

	public void type_Message(String typeMessage) throws InterruptedException {
		SoftAssert sf = new SoftAssert();
		try {
			minWait();
			if (isElementExist(Locators_WifiStability.type_Message)) {
				enterTextToInputField(Locators_WifiStability.messageField, typeMessage);
			}
			if (isElementExist(Locators_WifiStability.type_Message_enter)) {
				enterTextToInputField(Locators_WifiStability.type_Message_enter, typeMessage);
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
		 * This method is used to SEND SMS Precondition : User should create new SMS
		 */
		SoftAssert sf = new SoftAssert();
		try {
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_MENU);
			minWait();
			clickBtn(Locators_WifiStability.send);
			minWait();
		} catch (Exception e) {
			e.printStackTrace();
			// sf.fail();
			test.log(LogStatus.ERROR, "Error in Sending the Message.");
		}
		sf.assertAll();
	}

	public void clickOn_Send() throws InterruptedException {
		/* Method used to click on Send Button. */
		try {
			minWait();
			if (isElementExist(Locators_WifiStability.send_Icon)) {
				clickBtn(Locators_WifiStability.send_Icon);
				APP_LOGS.info("Send icon");
				minWait();
			} else if (isElementExist(Locators_WifiStability.send_SMS)) {
				clickBtn(Locators_WifiStability.send_SMS);
				APP_LOGS.info("Send icon");
				minWait();
			} else {
				clickBtn(Locators_WifiStability.send_MMS_Icon);
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
			// clickBtn(Locators_WifiStability.StartMessaging);
			minWait();
			clickBtn(Locators_WifiStability.NEXT_Msg);
			minWait();
			clickBtn(Locators_WifiStability.allow_Permission);
			minWait();
			// Runtime run = Runtime.getRuntime();
			// Process pr = run.exec("adb shell input tap 540 1776");
			// pr.waitFor();
			// Runtime run1 = Runtime.getRuntime();
			// Process pr1 = run1.exec("adb shell input tap 713 1098");
			// pr1.waitFor();
		} catch (Exception e) {
			e.getMessage();
		}
	}

	public void clickOnAppList() throws InterruptedException {

		try {
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_HOME);
			customWait(2000);
			clickBtn(Locators_WifiStability.app_List);
			minWait();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void launch_APP(AndroidElement appToClick) throws InterruptedException {
		try {
			clickOnAppList();
			for (int i = 0; i < 6; i++) {
				if (isElementExist(appToClick)) {
					// test.log(LogStatus.INFO, "\""+appToClick.getText()+"\" app launched.");
					clickBtn(appToClick);
					minWait();
					break;
				} else {
					scroll();
					minWait();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void clearSMSPermissions_O() throws InterruptedException {
		try {
			customWait(2000);
			clickBtn(Locators_WifiStability.StartMessaging);
			minWait();
			clickBtn(Locators_WifiStability.skipProvisioning);
			minWait();
		} catch (Exception e) {
			e.getMessage();
		}
	}

	public void validate_SentMessage(int n, SoftAssert soft) throws InterruptedException {
		/* To validate the Sent Message. */

		try {
			// launch_an_app("messaging");
			customWait(2000);
			System.out.println("Sent msg");
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			customWait(1000);
			if (isElementExist(Locators_WifiStability.now_Text)
					|| isElementExist(Locators_WifiStability.justnow_Text)
					|| isElementExist(Locators_WifiStability.not_Sent_Text)
					|| isElementExist(Locators_WifiStability.sending_Txt)) {
				check = true;
				APP_LOGS.info("Message sent Succeccfully");
				test.log(LogStatus.PASS, "Test case status is Passed at iteration : " + n);
				soft.assertTrue(check, " ");

			} else {
				APP_LOGS.info("SMS didn't sent");
				test.log(LogStatus.ERROR, "SMS or MMS didn't Sent, Validation Failed at iteration : " + n);
				soft.fail();
			}
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			minWait();
			/* aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK); */
		} catch (Exception e) {
			e.printStackTrace();
			soft.fail();
		}
	}

	public void validate_CharacterAndPageNumber(WebElement charAndPageNum, String expectedcharAndPageNum)
			throws InterruptedException {
		/*
		 * This Meathod is to validate Characters and Page Number Precondition : User
		 * should be in Message window with Typed Message
		 */
		SoftAssert sf = new SoftAssert();
		try {
			minWait();
			String charAndPageNum1 = null;
			if (isElementExist(Locators_WifiStability.zero_Characters_FirstPage)) {
				System.out.println("Im out");
				charAndPageNum1 = charAndPageNum.getText();
			} else if (isElementExist(Locators_WifiStability.zero_Characters_FirstPage1)) {
				System.out.println("Im in");
				charAndPageNum1 = Locators_WifiStability.zero_Characters_FirstPage1.getText();
			}

			if (check = charAndPageNum1.equals(expectedcharAndPageNum)) {
				APP_LOGS.info("Character and Page Number Displayed is correct");
				sf.assertTrue(check, "Validation is Pass");
			} else {
				APP_LOGS.info("Character and Page Number Displayed is NOT correct");
				sf.fail();
			}
		} catch (Exception e) {
			e.printStackTrace();
			// sf.fail();
			// test.log(LogStatus.ERROR, "Error in validating the Number of characters and
			// Page Number");
		}
		sf.assertAll();
	}

	public void validate_CharacterAndPageNumber_O(WebElement charAndPageNum, String expectedcharAndPageNum)
			throws InterruptedException {
		/*
		 * This Meathod is to validate Characters and Page Number Precondition : User
		 * should be in Message window with Typed Message
		 */
		SoftAssert sf = new SoftAssert();
		try {
			minWait();
			String charAndPageNum1 = null;
			if (isElementExist(Locators_WifiStability.zero_Characters_FirstPage_O)) {
				System.out.println("Im out");
				charAndPageNum1 = charAndPageNum.getText();
			}
			System.out.println(charAndPageNum1);
			if (check = charAndPageNum1.equals(expectedcharAndPageNum)) {
				APP_LOGS.info("Character and Page Number Displayed is correct");
				sf.assertTrue(check, "Validation is Pass");
			} else {
				APP_LOGS.info("Character and Page Number Displayed is NOT correct");
				sf.fail();
			}
		} catch (Exception e) {
			e.printStackTrace();
			// sf.fail();
			// test.log(LogStatus.ERROR, "Error in validating the Number of characters and
			// Page Number");
		}
		sf.assertAll();
	}

	public void navigateAttachOthers() throws InterruptedException {
		/* Navigates to */
		try {
			minWait();
			clickBtn(Locators_WifiStability.attach_icon_O);
			minWait();
			System.out.println("Clicked Attachment");
		} catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "Error in navigating to 'Attach others' Option");
		}
	}

	public void clickOnAttach_Options(AndroidElement optionToClick) throws InterruptedException {
		/* Clicks on Capture Picture in the Attach Others option. */
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

	public void enable_MobileData() throws InterruptedException, IOException {
		/*
		 * This Method is to enable MobileData.
		 */
		if (p_b_No.contains("-11.") || p_b_No.contains("-12.") || p_b_No.contains("-18.") || p_b_No.contains("-26.")
				|| p_b_No.contains("-29.") || p_b_No.contains("-30.")) {
			try {
				minWait();
				aDriver.findElementByAndroidUIAutomator(
						"new UiScrollable(new UiSelector().className(\"android.support.v7.widget.RecyclerView\")).scrollIntoView(new UiSelector().text(\"Data usage\"))")
						.click();
				minWait();
			} catch (Exception e) {
			}
			if (!isElementExist(Locators_WifiStability.cellularData_OnState)) {
				clickBtn(Locators_WifiStability.cellularData_OffState);
				if (isElementExist(Locators_WifiStability.OK)) {
					clickBtn(Locators_WifiStability.OK);
				}
				APP_LOGS.info("MobileData is Disabled");
				minWait();
			} else {
				APP_LOGS.info("MobileData is already Disabled");
			}
		} else if (p_b_No.contains("-10.")) {
			try {
				navigateTo_CellularNetworks();
				minWait();
				if (!isElementExist(Locators_WifiStability.mobileData_OnState)) {
					clickBtn(Locators_WifiStability.mobileData_OffState);
					if (isElementExist(Locators_WifiStability.OK)) {
						clickBtn(Locators_WifiStability.OK);
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

	public void navigateTo_CellularNetworks() throws InterruptedException, IOException {
		/* This Method will Navigate to Cellular Netwotks option. */
		minWait();
		try {
			aDriver.findElementByAndroidUIAutomator(
					"new UiScrollable(new UiSelector().className(\"android.support.v7.widget.RecyclerView\")).scrollIntoView(new UiSelector().text(\"More\"))")
					.click();
			minWait();
			aDriver.findElementByAndroidUIAutomator(
					"new UiScrollable(new UiSelector().className(\"android.support.v7.widget.RecyclerView\")).scrollIntoView(new UiSelector().text(\"Cellular networks\"))")
					.click();
			minWait();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void clickOn_Networks_and_Internet() {
		/*
		 * This method Clickes on "Network And Internet" which is Present Under Setting
		 * Menu
		 */
		try {
			scrollToElements(multi_Loc_Strategy(Locators_WifiStability.setting_Network_InternetLnk_txt1,
					Locators_WifiStability.setting_Network_InternetLnk_ind2,
					Locators_WifiStability.setting_Network_InternetLnk_SubTxt3, null, null, 419, 934));
			clickBtn(multi_Loc_Strategy(Locators_WifiStability.setting_Network_InternetLnk_txt1,
					Locators_WifiStability.setting_Network_InternetLnk_ind2,
					Locators_WifiStability.setting_Network_InternetLnk_SubTxt3, null, null, 419, 934));
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->clickOn_Networks_and_Internet()");
			e.printStackTrace();

		} catch (Exception e) {

			test.log(LogStatus.ERROR, "Exeption in ->clickOn_Networks_and_Internet()");
		}

	}

	public void clickOn_MobileNetwork() {
		/*
		 * this method clickes on "Mobile Network" Which is Present Under Network & Internet Menu
		 */
		try {
			scrollToElements(multi_Loc_Strategy(Locators_WifiStability.network_Internet_MobileNtwrk_Lnk_Txt1,
					Locators_WifiStability.network_Internet_MobileNtwrk_Lnk_Instance2, null, null, null, 312, 698));
			clickBtn(multi_Loc_Strategy(Locators_WifiStability.network_Internet_MobileNtwrk_Lnk_Txt1,
					Locators_WifiStability.network_Internet_MobileNtwrk_Lnk_Instance2, null, null, null, 312, 698));
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->clickOn_MobileNetwork()");
			e.printStackTrace();

		} catch (Exception e) {

			test.log(LogStatus.ERROR, "Exeption in ->clickOn_MobileNetwork()");
		}

	}

	public boolean scrollTo(String text) {
		/*
		 * Method used to select an element on the page by scrolling the Scroll
		 * View/List View
		 */

		boolean check = false;
		try {
			String scrollable = "new UiScrollable(new UiSelector().scrollable(true))";
			String textElement = ".scrollIntoView(new UiSelector().text(\"" + text + "\"))";
			aDriver.findElementByAndroidUIAutomator(scrollable + textElement);
			APP_LOGS.info("Searched application is found sucessfully : ");
			check = true;
			return check;
		} catch (NoSuchElementException e) {
			return check;
		}
	}

	public void dailCallUsingDailPad(String dailNum) throws InterruptedException, IOException {

		try {
			dailNumber(dailNum);
			if (!isElementExist(Locators_WifiStability.turnOff_Airplane_PopUp)) {
				try {
					for (int j = 1; j <= 100; j++) {
						Process child = null;
						if (r_b_No.contains("8A.")) {
							child = Runtime.getRuntime().exec("adb -s " + r_Id + " shell service call telecom 27");
						} else if (r_b_No.contains("5SA.")) {
							child = Runtime.getRuntime().exec("adb -s " + r_Id + " shell service call telecom 28");
						}
						InputStream inputStream = child.getInputStream();
						InputStreamReader isr = new InputStreamReader(inputStream);
						BufferedReader bf = new BufferedReader(isr);
						String value = bf.readLine();

						System.out.println(value);
						if (value.contains("00000001") || value.contains("ffffffff")) {
							System.out.println("Phone is ringing so accepting call.");
							Thread.sleep(3000);
							Runtime.getRuntime().exec("adb -s " + r_Id + " shell input keyevent 5");
							break;
						} else {
							continue;
						}
					}
				} catch (Exception e) {
					Thread.sleep(2000);
					Runtime.getRuntime().exec("adb -s " + r_Id + " shell input keyevent 5");
					Thread.sleep(2000);
				}
			}
			APP_LOGS.info("Number dailed is: " + dailNum);
		} catch (Exception e) {
			APP_LOGS.info("Selected Page is not displayed.");
			e.printStackTrace();
		}
	}

	public void dailNumber(String dailNum) throws IOException {
		try {
			minWait();
			clickBtn(Locators_WifiStability.add_Call);
			minWait();
			clickBtn(Locators_WifiStability.dailerPad);
			minWait();
			clickBtn(Locators_WifiStability.enterNumFiled);
			minWait();
			enterTextToInputField(Locators_WifiStability.enterNumFiled, dailNum);
			minWait();
			// Runtime.getRuntime().exec("adb -s "+p_Id+" shell input text "+dailNum);
			minWait();
			clickBtn(Locators_WifiStability.dail);
			customWait(4000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void validate_Airplane_Enable(String dailNum, SoftAssert soft, int n)
			throws InterruptedException, IOException {
		/*
		 * Method can be Used Validate Airplane Mode activation via by making the call.
		 */
		try {
			customWait(2000);
			if (isElementExist(Locators_WifiStability.turnOff_Airplane_PopUp)) {
				minWait();
				check = true;
				APP_LOGS.info("Validated TurnOff Airplane Mode PopUp Displayed Successfully" + check);
				soft.assertTrue(check, "TestCase Valiation is PASS");
				System.out.println("Validated TurnOff Airplane Mode PopUp Displayed Successfully" + check);
				test.log(LogStatus.PASS, "Enable Airplane Mode is Validated at iteration: " + n);
			} else {
				minWait();
				APP_LOGS.info("TurnOff Airplane Mode PopUp NOT Displayed");
				System.out.println("Enable Airplane Mode is Unsuccessful at iteration: " + n);
				test.log(LogStatus.FAIL, "Enable Airplane Mode is Unsuccessful at iteration: " + n);
				soft.fail();
			}
			minWait();
			clickBtn(Locators_WifiStability.OK);
			minWait();
			clickBtn(Locators_WifiStability.CANCEL);
			minWait();
		} catch (Exception e) {
			e.printStackTrace();
			// soft.fail();
		}
	}

	public void validate_Airplane_Disable(String dailNum, SoftAssert soft, int n)
			throws InterruptedException, IOException {
		/*
		 * Method can be Used Validate Airplane Mode activation via by making the call.
		 */
		try {
			minWait();
			if (!isElementExist(Locators_WifiStability.turnOff_Airplane_PopUp)) {
				minWait();
				check = true;
				APP_LOGS.info("TurnOff Airplane Mode PopUp NOT Displayed.");
				soft.assertTrue(check, "TestCase Valiation is PASS");
				test.log(LogStatus.PASS, "Disable Airplane Mode is VAlidated at iteration: " + n);
			} else {
				minWait();
				APP_LOGS.info("TurnOff Airplane Mode PopUp is Displayed");
				test.log(LogStatus.FAIL, "Disable Airplane Mode is UnSuccessful at iteration: " + n);
				soft.fail();
			}
			minWait();
			// clickBtn(Locators_WifiStability.OK);
			minWait();
		} catch (Exception e) {
			e.printStackTrace();
			soft.fail();
		}
	}

	public void end_Call() throws InterruptedException {
		/*
		 * Method is to end Call. Precondition : User should initiate the Call to any
		 * Number.
		 */
		try {
			minWait();
			if (isElementExist(Locators_WifiStability.end_Call)) {
				clickBtn(Locators_WifiStability.end_Call);
				APP_LOGS.info("Call Ended");
			} else {
				APP_LOGS.info("End Call Button Doesn't exist on screen");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void enable_BT_RefDevice() throws InterruptedException, IOException {

		try {
			minWait();
			Runtime.getRuntime().exec("adb -s " + r_Id + " shell input keyevent 3");
			minWait();
			Runtime.getRuntime().exec("adb -s " + r_Id + " shell am start -a android.settings.BLUETOOTH_SETTINGS");
			minWait();
			Runtime.getRuntime()
					.exec("adb -s " + r_Id + " shell am start -a android.bluetooth.adapter.action.REQUEST_ENABLE");
			minWait();
			Runtime.getRuntime().exec("adb -s " + r_Id + " shell input keyevent 22");
			minWait();
			Runtime.getRuntime().exec("adb -s " + r_Id + " shell input keyevent 22");
			minWait();
			Runtime.getRuntime().exec("adb -s " + r_Id + " shell input keyevent 66");
			customWait(3000);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void validate_BT_Devices(SoftAssert soft, int n) throws InterruptedException, IOException {
		minWait();
		Runtime.getRuntime().exec("adb -s " + r_Id + " shell am start -a android.settings.BLUETOOTH_SETTINGS");
		customWait(6000);
		System.out.println(" Im Searching...BT devices");
		WebDriverWait wait = new WebDriverWait(aDriver, 30);
		// scrollToElement(Locators_WifiStability.BT_Devices);
		wait.until(ExpectedConditions.visibilityOf(Locators_WifiStability.BT_Devices));
		minWait();
		boolean check = Locators_WifiStability.BT_Devices.isDisplayed();
		minWait();
		if (check) {
			check = true;
			soft.assertTrue(check, "TestCase Valiation is PASS");
			test.log(LogStatus.PASS, "Enable Bluethooth is Validated at iteration: " + n);
		} else {
			soft.fail();
			test.log(LogStatus.FAIL, "Enable Bluethooth is UnSuccessful at iteration: " + n);
		}
	}

	public boolean clickOnText(String text) {
		/*
		 * Method used to select an element on the page by scrolling the Scroll
		 * View/List View
		 */
		boolean check = false;
		try {
			String scrollable = "new UiSelector()";
			String textElement = ".text(\"" + text + "\")";
			aDriver.findElementByAndroidUIAutomator(scrollable + textElement).click();
			APP_LOGS.info("Searched application is found sucessfully : ");
			check = true;
			return check;
		} catch (Exception e) {
			return check;
		}
	}

	public boolean scrollToTextContains(String text) {
		/*
		 * Method used to select an element on the page by scrolling the Scroll
		 * View/List View
		 */
		boolean check = false;
		try {
			String scrollable = "new UiScrollable(new UiSelector().scrollable(true))";
			String textElement = ".scrollIntoView(new UiSelector().textContains(\"" + text + "\"))";
			aDriver.findElementByAndroidUIAutomator(scrollable + textElement).click();
			APP_LOGS.info("Searched application is found sucessfully : ");
			check = true;
			return check;
		} catch (NoSuchElementException e) {
			return check;
		}
	}

	public void scrollToElementWithDpadDown(WebElement element) {
		/*
		 * Clicks up button till element is available
		 */
		System.out.println("Clicking Element1");
		if (isElementExist(element)) {
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
			System.out.println("Clicking Element");
			element.click();
		} else {
			System.out.println("Clicking First Element");
			clickBtn(Locators_WifiStability.firstApp_Suggetion);
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
	
	public void clickBackButton(int number) throws InterruptedException
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
			test.log(LogStatus.ERROR, "Exeption occurs in clickBackButton() method");
			e.printStackTrace();
		}
	}

	public void checkWifiConnected() throws InterruptedException {
		try {
			customWait(4000);
			if (isElementExist(Locators_WifiStability.connectedWIFI1)) {
				String getTxt = Locators_WifiStability.connectedWIFI1.getText();
				System.out.println(getTxt);
				test.log(LogStatus.INFO, "Wi-Fi is Connected : will be disconnecting");
				disconnectSSIDifConnected();
			} else {
				System.out.println("Not Connected");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void validate_And_BrowseIn_Chrome(int n, SoftAssert soft) throws InterruptedException, IOException {
		/*
		 * Validates MobileData enabality by launching the Chrome & browsing google
		 * page.
		 */

		try {
			customWait(5000);
			if (isElementExist(Locators_WifiStability.google_savedDataOpt)) {

				clickBtn(Locators_WifiStability.google_savedDataOpt_OkBtn);
			}
			minWait();
			String url = "https://www.google.com";
			Locators_WifiStability.google_urlBar.clear();
			enterTextToInputField(Locators_WifiStability.google_urlBar, url);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_ENTER);
			minWait();

			boolean check1 = Locators_WifiStability.google_appsBtn.isDisplayed();
			boolean check2 = Locators_WifiStability.google_offeredInTxt.isDisplayed();
			boolean check3 = Locators_WifiStability.google_signinBtn.isDisplayed();

			if (check1 && check2 && check3) {
				APP_LOGS.info("Suggeted URL Page Displayed Succeessfully");
				soft.assertTrue(true, "TestCase Valiation is PASS");
				test.log(LogStatus.PASS, "WiFi Enabled and URL loaded successfully at iteration " + n);
			} else {
				APP_LOGS.info("Suggeted URL page didn't get Loaded");
				test.log(LogStatus.FAIL, "Test Case Status is Fail at iteration " + n);
				test.log(LogStatus.INFO, "URL didn't load.");
				soft.fail();
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in locators->validate_And_BrowseIn_Chrome()");
		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exeption in ->validate_And_BrowseIn_Chrome()");
		}
	}

	public void validate_And_BrowseIn_Chrome_Disconnect(int n, SoftAssert soft)
			throws InterruptedException, IOException {
		/*
		 * Validates MobileData enabality by launching the Chrome & browsing google
		 * page.
		 */

		try {
			customWait(5000);
			if (isElementExist(Locators_WifiStability.google_Alert_NoInternetOpt)) {

				clickBtn(Locators_WifiStability.google_Alert_okBtn);
			} else if (isElementExist(Locators_WifiStability.google_savedDataOpt)) {

				clickBtn(Locators_WifiStability.google_savedDataOpt_OkBtn);
			}

			customWait(2000);

			String url = "https://www.appium.com";
			Locators_WifiStability.google_urlBar.clear();
			enterTextToInputField(Locators_WifiStability.google_urlBar, url);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_ENTER);
			minWait();

			/*
			 * boolean check1 = Locators_WifiStability.google_appsBtn.isDisplayed();
			 * boolean check2 = Locators_WifiStability.google_offeredInTxt.isDisplayed();
			 * boolean check3 = Locators_WifiStability.google_signinBtn.isDisplayed();
			 */
			/*
			 * if(isElementExist(multi_Loc_Strategy(Locators_WifiStability.
			 * google_noInternetAlertID, Locators_WifiStability.google_noInternetAlertBtn,
			 * null, null, null, 889, 1093))) {
			 * 
			 * clickBtn(multi_Loc_Strategy(Locators_WifiStability.
			 * google_noInternetAlertID, Locators_WifiStability.google_noInternetAlertBtn,
			 * null, null, null, 889, 1093)); }
			 */

			if (Locators_WifiStability.google_NoInternetOpt.isDisplayed()) {
				APP_LOGS.info("Page Not Displayed do to Wifi Disconnection");
				soft.assertTrue(true, "Page Not Displayed due to Wifi Disconnection");
				test.log(LogStatus.PASS, "Page Not Displayed due to Wifi Disconnection in Iteration " + n);

			} else {
				APP_LOGS.info("Failed -> Page shown Successfully after Wifi Disconnection Also");
				test.log(LogStatus.FAIL,
						"Failed -> Page shown Successfully after Wifi Disconnection Also in iteration " + n);
				test.log(LogStatus.INFO, "Failed -> Page shown Successfully after Wifi Disconnection Also");
				soft.fail();
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in locators->validate_And_BrowseIn_Chrome_Disconnect()");
		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exeption in ->validate_And_BrowseIn_Chrome_Disconnect()");
		}
	}

	public void ON_Switch(String switch_To_ON) {
		try {
			minWait();
			aDriver.findElementByXPath("//*[contains(@text,'" + switch_To_ON + "')]/../..//*[@text='OFF']").click();
			minWait();
		} catch (Exception e) {
		}
	}

	public void OFF_Switch() {
		try {
			minWait();
			if(Locators_WifiStability.MobileDataSwitch.getText().equalsIgnoreCase("On")) {
				Locators_WifiStability.MobileDataSwitch.click();
				minWait();
				Locators_WifiStability.OkBtn_ID.click();
			}
				
			minWait();
		} catch (Exception e) {
		}
	}
	
	public void OFF_Switch_SPRN(String switch_To_OFF) {
		try {
			minWait();
			aDriver.findElementByXPath("//*[contains(@text,'" + switch_To_OFF + "')]/../..//*[@text='ON']").click();
			minWait();
			Locators_WifiStability.OkBtn_ID.click();
		} catch (Exception e) {
		}
	}

	public void turnOn_Wifi() {
		/*
		 * Turning on the Wifi
		 */
		try {
			if (Locators_WifiStability.Wifi_Switch_Btn.getText().equalsIgnoreCase("ON")) {

			} else {
				Locators_WifiStability.Wifi_Switch_Btn.click();
				customWait(2000);
			}

		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->turnOn_Wifi()");
			e.printStackTrace();

		} catch (Exception e) {

			test.log(LogStatus.ERROR, "Exeption in ->turnOn_Wifi()");
		}
	}

	public void clearBrowser_History() {
		/*
		 * Clearing Google chrome History
		 */
		try {
			clickBtn(Locators_WifiStability.google_chromeMenuBtn);
			clickBtn(Locators_WifiStability.google_ChromeHistoryLnk);
			if (isElementExist(Locators_WifiStability.google_chromeEmptyHistory_Txt_Id)) {

			} else {

				clickBtn(Locators_WifiStability.google_chromeClearBrowserBtn);
				clickBtn(Locators_WifiStability.google_ChromeAdvancedLnk);
				clickBtn(Locators_WifiStability.google_chromeTimeRangeMenuDrpDwn);
				clickBtn(Locators_WifiStability.google_chromeTimeRange_AllTimeCheckBx);

				List<AndroidElement> checkBxStatus = Locators_WifiStability.google_chromeCheckBoxStatus;

				for (AndroidElement androidElement : checkBxStatus) {

					String result = androidElement.getAttribute("checked");
					System.out.println(result);
					Boolean T_F = Boolean.parseBoolean(result);
					if (T_F == false) {

						clickBtn(androidElement);
					} else {

					}

				}

				clickBtn(Locators_WifiStability.google_chromeClearBrowser_Clear_Btn_Id);

			}
			clickOn_BackBtn();

		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->clearBrowser_History()");
			e.printStackTrace();

		} catch (Exception e) {

			test.log(LogStatus.ERROR, "Exeption in ->clearBrowser_History()");
		}
	}

	public void enter_Password(String Pswd) {

		try {
			minWait();
			enterTextToInputField(multi_Loc_Strategy(Locators_WifiStability.wifi_PsswdBx,Locators_WifiStability.wifi_PsswdBx_index, Locators_WifiStability.wifi_PsswdBx_instance, null, null, 250, 500), Pswd);

		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->enter_Password()");
			e.printStackTrace();

		} catch (Exception e) {

			test.log(LogStatus.ERROR, "Exeption in ->enter_Password()");
		}
	}

	public void clickOn_Connect() {

		try {
			clickBtn(Locators_WifiStability.Wifi_connectBtn);
			customWait(3000);

		}

		catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators-> clickOn_Connect()");
			e.printStackTrace();

		} catch (Exception e) {

			test.log(LogStatus.ERROR, "Exeption in -> clickOn_Connect()");
		}
	}

	public void select_SSIDwifi(String ssid) {
		/*
		 * Selectin SSid And Clicking By scrolling
		 */
		try {

			scrollToText(ssid);

		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->select_SSIDwifi()");
			e.printStackTrace();

		} catch (Exception e) {

			test.log(LogStatus.ERROR, "Exeption in ->select_SSIDwifi()");
		}
	}

	public void turnOff_Wifi() {

		try {
			if (Locators_WifiStability.Wifi_Switch_Btn.getText().equalsIgnoreCase("OFF")) {

			} else {
				Locators_WifiStability.Wifi_Switch_Btn.click();
				customWait(3000);
			}

		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->turnOff_Wifi()");
			e.printStackTrace();

		} catch (Exception e) {

			test.log(LogStatus.ERROR, "Exeption in ->turnOff_Wifi()");
		}
	}

	public void closeAllTabs() throws InterruptedException {
		/* To close all tha Tabs in Chrome Browser. */
		try {
			minWait();
			clickBtn(Locators_WifiStability.switcherButton_Chrome);
			minWait();
			clickBtn(Locators_WifiStability.menuButton_Chrome);
			minWait();
			clickBtn(Locators_WifiStability.closeAllTabs_Chrome);
			minWait();
		} catch (NoSuchElementException e) {
			e.printStackTrace();
		}
	}

	public void validate_MobileData_Disable(int n, String typ, SoftAssert soft)
			throws InterruptedException, IOException {
		/*
		 * Validates MobileData disabality by launching the Chrome.
		 */
		try {
			String url = "https://www.google.com";

			minWait();
			aDriver.get(url);
			customWait(3000);
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_ENTER);
			minWait();

			boolean check1 = Locators_WifiStability.google_appsBtn.isDisplayed();
			boolean check2 = Locators_WifiStability.google_offeredInTxt.isDisplayed();
			boolean check3 = Locators_WifiStability.google_signinBtn.isDisplayed();

			if (check1 && check2 && check3) {

				APP_LOGS.info("Mobbile Data Disabled Successfully");
				soft.assertTrue(check, "TestCase Valiation is PASS");
				test.log(LogStatus.PASS, typ + " Disable validation is PASS at iteration " + n);
			} else {
				APP_LOGS.info("Mobiledata is NOT Disabled");
				test.log(LogStatus.FAIL, typ + " Disable validation is FAIL at iteration " + n);
				soft.fail();
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in locators->validate_MobileData_Disable()");
		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exeption in ->validate_MobileData_Disable()");
		}

	}

	public void clearChromePermission() {
		try {
			System.out.println("In Browser");
			if (isElementExist(Locators_WifiStability.ACCEPTCONTINUE)) {
				customWait(3000);
				clickBtn(Locators_WifiStability.ACCEPTCONTINUE);
				customWait(5000);
				clickBtn(Locators_WifiStability.NEXT);
				customWait(3000);
				clickBtn(Locators_WifiStability.NO_THANKS);
				customWait(2000);
			} else {

			}
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in locators->clearChromePermission()");
		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exeption in ->clearChromePermission()");
		}
	}
	
	public void clearChromePermission_SPRN() {
		try {			
			System.out.println("In Browser");
			if (isElementExist(Locators_WifiStability.ACCEPTCONTINUE)) {
				clickBtn(Locators_WifiStability.ACCEPTCONTINUE);
				customWait(3000);
			}
			if(isElementExist(Locators_WifiStability.NEXT));{
				clickBtn(Locators_WifiStability.NEXT);
				customWait(3000);}
			if(Locators_WifiStability.nothanksBtn.isDisplayed()) {
					clickBtn(Locators_WifiStability.nothanksBtn);
					customWait(2000);
				}
				
	
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in locators->clearChromePermission()");
		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exeption in ->clearChromePermission()");
		}
	}
	
	public void clearChromeCache() {
		try {
			Runtime.getRuntime().exec("adb -s "+p_Id+" shell pm clear com.android.chrome");
			/*launch_an_app("settings");
			minWait();
			clickBtn(Locators_WifiStability.setting_app_notificationLnk_Txt1);
			minWait();
			clickBtn(Locators_WifiStability.setting_seeAllApps_Txt1);
			minWait();
			scrollToText("Chrome");
			minWait();
			scrollText("Storage");
			minWait();
			clickBtn(Locators_WifiStability.setting_manageSpaceBtn_Txt1);
			minWait();
			clickBtn(Locators_WifiStability.setting_ClearAllBtn_Txt1);
			minWait();
			Locators_WifiStability.OkBtn_ID.click();
			minWait();*/
			
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in locators->clearChromeCache()");
		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exeption in ->clearChromeCache()");
		}
	}
	
	public void clearChromeCache_SPRN() {
		try {
			minWait();
			launch_an_app("settings");
			minWait();
			clickBtn(Locators_WifiStability.setting_app_notificationLnk_Txt1);
			minWait();
			clickBtn(Locators_WifiStability.setting_seeAllApps_Txt1);
			minWait();
			scrollToElements(Locators_WifiStability.setting_chromeEle);
			minWait();
			clickBtn(Locators_WifiStability.setting_chromeEle);
			minWait();
			scrollText("Storage");
			minWait();
			clickBtn(Locators_WifiStability.setting_manageSpaceBtn_Txt1);
			minWait();
			clickBtn(Locators_WifiStability.setting_ClearAllBtn_Txt1);
			minWait();
			Locators_WifiStability.OkBtn_ID.click();
			minWait();
			
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in locators->clearChromeCache_VZN()");
		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exeption in ->clearChromeCache_VZN()");
		}
	}
	
	public void clearChromeCache_VZN() {
		try {
			TouchAction ta = new TouchAction(aDriver);
			launch_an_app("settings");
			minWait();
			clickBtn(Locators_WifiStability.setting_app_notificationLnk_Txt1);
			minWait();
			clickBtn(Locators_WifiStability.setting_seeAllApps_Txt1);
			minWait();
			scrollToText("Chrome");
			minWait();
			scrollText("Storage");
			minWait();
			clickBtn(Locators_WifiStability.setting_manageSpaceBtn_Txt1);
			customWait(5000);
			if(isElementExist(Locators_WifiStability.alertTextDisplay)) {
				
				ta.tap(142, 1716).perform();
			}
			minWait();
			//clickBtn(Locators_WifiStability.clearAllDataBtn);
			ta.tap(758, 1462);
			minWait();
			Locators_WifiStability.OkBtn_ID.click();
			minWait();
			
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in locators->clearChromeCache()");
		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exeption in ->clearChromeCache()");
		}
	}

	public void add_Picture_O() throws InterruptedException {

		try {
			if (p_b_No.contains("-10.") || p_b_No.contains("-30.")) {
				clickAttachment();
				minWait();
				clickBtn(Locators_WifiStability.capturePicture_MMS);
				minWait();
				clearAllow();
				clickBtn(Locators_WifiStability.capturePicture);
				customWait(10000);
				clickBtn(Locators_WifiStability.done_1);
				minWait();
			} else if (p_b_No.contains("-11.") || p_b_No.contains("-12.") || p_b_No.contains("-18.")
					|| p_b_No.contains("-26.") || p_b_No.contains("-29.")) {
				clickAttachment();
				minWait();
				clickBtn(Locators_WifiStability.capture_pictures_or_video);
				minWait();
				clearAllow();
				clickBtn(Locators_WifiStability.capture_pictures_or_video);
				minWait();
				clickBtn(Locators_WifiStability.take_picture_MMS);
				customWait(10000);
			}
		} catch (Exception e) {
		}
	}

	public void clickAttachment() throws InterruptedException {
		try {
			minWait();
			clickBtn(Locators_WifiStability.attachment);
			minWait();
		} catch (Exception e) {
		}
	}

	public void capturePic_MMS_O() {
		try {
			minWait();
			clickBtn(Locators_WifiStability.button_Attach_SMS);
			minWait();
			clickBtn(Locators_WifiStability.camera_MMS);
			customWait(2000);
			clickBtn(Locators_WifiStability.OptionPhoto_Icon);
			customWait(2000);
			clickBtn(Locators_WifiStability.cameraCapture_Icon);
			minWait();
			clickBtn(Locators_WifiStability.send_Cam_SMS);
			customWait(3000);
		} catch (Exception e) {

		}

	}

	public void captureVideo_MMS_O() {
		try {
			minWait();
			clickBtn(Locators_WifiStability.button_Attach_SMS);
			minWait();
			clickBtn(Locators_WifiStability.camera_MMS);
			customWait(2000);
			clickBtn(Locators_WifiStability.OptionVideo_Icon);
			customWait(2000);
			TouchAction action = new TouchAction(aDriver);
			action.longPress(Locators_WifiStability.cameraCapture_Icon).release().perform();
			// clickBtn(Locators_WifiStability.cameraCapture_Icon);
			minWait();
			clickBtn(Locators_WifiStability.send_Cam_SMS);
			customWait(3000);
		} catch (Exception e) {

		}

	}

	public void remove_GoogleAcccount_Orio() {
		// remove added google Account if any
		try {
			scrollToText("Users & accounts");
			// clickOnAccounts();
			minWait();
			if (isElementExist(Locators_WifiStability.connectedAccount)) {
				System.out.println("Account is present");
				minWait();
				clickBtn(Locators_WifiStability.connectedAccount);
				minWait();
				clickBtn(Locators_WifiStability.REMOVE_ACCOUNT);
				minWait();
				clickBtn(Locators_WifiStability.REMOVE_ACCOUNT);
				customWait(3000);
			} else {
				System.out.println("No Google account present");
			}

		} catch (Exception e) {

		}
	}

	public void navigateTo_AddGoogleAccount_Orio() {
		// navigate to settings option Add google Account
		WebDriverWait wt = new WebDriverWait(aDriver, 60);
		try {
			scrollToText("Users & accounts");
			clickBtn(Locators_WifiStability.add_Account);
			minWait();
			clickBtn(Locators_WifiStability.google_Account);
			customWait(1600);
			wt.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[contains(@text,'Checking info')]")));
			minWait();
		} catch (Exception e) {

		}
	}

	public void validate_Locators1(AndroidElement e1, SoftAssert soft, int n) throws InterruptedException {
		minWait();
		boolean check1 = false;
		try {
			check1 = e1.isDisplayed();
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (check1) {
			check = true;
			test.log(LogStatus.PASS, "Disable BT is Validated at iteration: " + n);
			soft.assertTrue(check, "TestCase Valiation is PASS");
		} else {
			test.log(LogStatus.FAIL, "Disable BT is UnSuccessful at iteration: " + n);
			soft.fail();
		}
	}

	public void add_GoogleAccount(String emailId, String password) {
		WebDriverWait wt = new WebDriverWait(aDriver, 60);
		try {
			minWait();
			enterTextToInputField(Locators_WifiStability.email_googleAcnt, emailId);
			minWait();
			clickBtn(Locators_WifiStability.next);
			minWait();
			wt.until(ExpectedConditions
					.visibilityOfElementLocated(By.xpath("//*[@text='Welcome' or @resource-id='profileIdentifier']")));
			minWait();
			enterTextToInputField(Locators_WifiStability.password_googleAcnt, password);
			customWait(5000);
			clickBtn(Locators_WifiStability.next);
			customWait(3000);
			scroll();
			scroll();
			minWait();
			clickBtn(Locators_WifiStability.skip_);
			// aDriver.findElementByAndroidUIAutomator("new UiScrollable(new
			// UiSelector().className(\"android.widget.Button\")).scrollIntoView(new
			// UiSelector().textContains(\"Skip\"))").click();
			minWait();
			// scrollToText("Skip");
			if (p_b_No.contains("-11.") || p_b_No.contains("-12.") || p_b_No.contains("-18.") || p_b_No.contains("-26.")
					|| p_b_No.contains("-29.")) {
				try {
					aDriver.findElementByAndroidUIAutomator(
							"new UiScrollable(new UiSelector().className(\"android.webkit.WebView\")).scrollIntoView(new UiSelector().textContains(\"Yes\"))")
							.click();
					minWait();
				} catch (Exception e) {
				}
			}
			customWait(1000);
			clickBtn(Locators_WifiStability.i_agree);
			minWait();
			wt.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[contains(@text,'Checking info')]")));
			minWait();
			clickBtn(Locators_WifiStability.MORE);
			minWait();
			clickBtn(Locators_WifiStability.ACCEPTorAGREE);
			customWait(3000);
		} catch (Exception e) {
		}
	}

	public void checkWifiConnected1() throws InterruptedException {
		try {
			customWait(4000);
			if (isElementExist(Locators_WifiStability.connectedWIFI1)) {
				String getTxt = Locators_WifiStability.connectedWIFI1.getText();
				System.out.println("Connected " + getTxt);
				// test.log(LogStatus.INFO, "Wi-Fi is Connected");
			} else {
				System.out.println("Not Connected");
				selectSSIDwifi();
				enterPassword();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void install_App(String appName, WebElement element) throws InterruptedException, IOException {

		if (isElementExist(Locators_WifiStability.account_Page)) {
			clearRecentApps();
			launch_APP(Locators_WifiStability.PlayStore);
		}

		WebDriverWait wait = new WebDriverWait(aDriver, 60);
		try {
			System.out.println("To Be install " + appName);
			customWait(3000);
			clickBtn(Locators_WifiStability.google_Play);
			customWait(3000);
			enterTextToInputField(Locators_WifiStability.search_PlayStore, appName);
			customWait(3000);
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_ENTER);
			customWait(8000);
			System.out.println("Scrolling");
			scrollToElementWithDpadDown(element);
			minWait();

			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@text='INSTALL']")));
			customWait(6000);
			if (isElementExist(Locators_WifiStability.INSTALL)) {
				minWait();
				clickBtn(Locators_WifiStability.INSTALL);
				minWait();
				clickBtn(Locators_WifiStability.ACCEPT);
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
			// e.printStackTrace();
		}
	}

	// public SoftAssert sf42 = new SoftAssert();
	public void validate_Installed_App(String appName, int n, SoftAssert soft) {

		try {
			boolean check = false;
			clickOnAppList();
			enterTextToInputField(Locators_WifiStability.searchApps, appName);
			minWait();
			if (isElementExist(Locators_WifiStability.apkExtractor)) {
				check = true;
				soft.assertTrue(check, "TestCase Valiation is PASS");
				test.log(LogStatus.PASS,
						"APK installed " + "\" " + appName + "\"" + " Successfully at iteration: " + n);
			} else {
				test.log(LogStatus.FAIL, "APK didn't Installed " + "\" " + appName + "\"" + "at iteration: " + n);
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
		// Search Application is installed and uninstall

		clickOnAppList();

		enterTextToInputField(Locators_WifiStability.searchApps, appName);
		minWait();
		System.out.println("Checking....");
		if (isElementExist(Locators_WifiStability.apkExtractor)) {
			System.out.println("Yes App is Present...");
			if (appName.contains("Tech Support")) {
				System.out.println("Im in Staples");
				launch_APP(Locators_WifiStability.PlayStore);
				unInstall_App("Tech Support Staples");

			} else if (appName.contains("Meeting App")) {
				System.out.println("Im in MAcys");
				launch_APP(Locators_WifiStability.PlayStore);
				unInstall_App("Macys");
			}

			else if (appName.contains("MCDelivery App")) {
				System.out.println("MCDelivery");
				launch_APP(Locators_WifiStability.PlayStore);
				unInstall_App("McDonald's");
			}

			else if (appName.contains("Craigslist")) {
				System.out.println("App for Craigslist");
				launch_APP(Locators_WifiStability.PlayStore);
				unInstall_App("App for Craigslist");
			}

			else {
				System.out.println("Im in Gen");
				launch_APP(Locators_WifiStability.PlayStore);
				unInstall_App(appName);
			}
			System.out.println("Uninstalled");
		}
	}

	public void unInstall_App(String appName) {

		WebDriverWait wait = new WebDriverWait(aDriver, 60);
		try {
			minWait();
			clickBtn(Locators_WifiStability.google_Play);
			minWait();
			enterTextToInputField(Locators_WifiStability.search_PlayStore, appName);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_ENTER);
			customWait(5000);
			if (isElementExist(Locators_WifiStability.installed_Playstore1)) {
				customWait(3000);
				clickBtn(Locators_WifiStability.installed_Playstore1);
			}
			if (isElementExist(Locators_WifiStability.UNINSTALL)) {
				System.out.println("Uninstalling");
				minWait();
				clickBtn(Locators_WifiStability.UNINSTALL);
				minWait();
				clickBtn(Locators_WifiStability.OK);
				minWait();
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@text='INSTALL']")));
				minWait();
			} else if (isElementExist(Locators_WifiStability.UNINSTALL1)) {
				System.out.println("Uninstalling....");
				minWait();
				clickBtn(Locators_WifiStability.UNINSTALL1);
				minWait();
				clickBtn(Locators_WifiStability.OK);
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

	public void validate_Uninstalled_App(String appName, int n, SoftAssert soft) {

		try {
			boolean check = false;
			clickOnAppList();
			enterTextToInputField(Locators_WifiStability.searchApps, appName);
			minWait();
			if (isElementExist(Locators_WifiStability.apkExtractor)) {
				test.log(LogStatus.FAIL, "APK didn't Uninstalled " + "\" " + appName + "\"" + "at iteration: " + n);
				soft.fail();
			} else {

				check = true;
				soft.assertTrue(check, "TestCase Valiation is PASS");
				test.log(LogStatus.PASS,
						"APK Uninstalled " + "\" " + appName + "\"" + " Successfully at iteration: " + n);
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

	public void add_NewContact_withAllFields_O(String contactName, String lastName, String phoneticlastName,
			String phoneticmiddleName, String phoneticfirstName, String nickName, String company, String title,
			String phone, String SIP, String email, String address, String IM, String webSite, String relationship,
			String notes) {

		try {
			clickBtn(Locators_WifiStability.add_ContactIcon_O);
			minWait();
			clickBtn(Locators_WifiStability.contact_SavingTo);
			minWait();
			clickBtn(Locators_WifiStability.savingTo_Phone);
			minWait();
			// aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			minWait();
			clickBtn(Locators_WifiStability.more_Fields);
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
			Runtime.getRuntime().exec("adb -s " + p_Id + " shell input tap 888 84");
			minWait();
			// clickBtn(Locators_WifiStability.SAVE);
			// minWait();
			clearAllow();
			// aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			minWait();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean scrollToAndEnterText(String scrollTo, String stringToEnter) {
		/*
		 * Method used to select an element on the page by scrolling the Scroll
		 * View/List View
		 */
		boolean check = false;
		try {
			String scrollable = "new UiScrollable(new UiSelector().scrollable(true))";
			String textElement = ".scrollIntoView(new UiSelector().text(\"" + scrollTo + "\"))";
			aDriver.findElementByAndroidUIAutomator(scrollable + textElement).sendKeys(stringToEnter);
			APP_LOGS.info("Searched application is found sucessfully : ");
			check = true;
			return check;
		} catch (NoSuchElementException e) {
			return check;
		}
	}

	public void scrollUp() {

		try {
			/*
			 * org.openqa.selenium.Dimension size =aDriver.manage().window().getSize();
			 * System.out.println(size); int x=size.getWidth()/2; int
			 * starty=(int)(size.getHeight()*0.60); int endy=(int)(size.getHeight()*0.10);
			 */
			aDriver.swipe(600, 1800, 600, 400, 1000);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void add_NewContact(int enter1forPhone_2forSimMemory, String contactName, String contactNum)
			throws InterruptedException, IOException {
		/*
		 * Method is to Add Contact in Contacts. Precondition:User should give 1 for
		 * first argument to save for Phone Memory and 2 fo rsim Memory.
		 */
		try {
			minWait();
			clickBtn(Locators_WifiStability.add_ContactIcon_O);
			minWait();
			// clickBtn(Locators_WifiStability.contact_SavingTo);
			minWait();
			// clickBtn(Locators_WifiStability.add_NewContact);
			minWait();
			if (enter1forPhone_2forSimMemory == 1) {
				clickBtn(Locators_WifiStability.contact_SavingTo);
				minWait();
				clickBtn(Locators_WifiStability.savingTo_Phone);
				minWait();
				enterTextToInputField(Locators_WifiStability.name_NewContact, contactName);
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
				minWait();
				enterTextToInputField(Locators_WifiStability.phone_NewContact, contactNum);
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
				minWait();
				//
			} else if (enter1forPhone_2forSimMemory == 2) {
				clickBtn(Locators_WifiStability.contact_SavingTo);
				minWait();
				clickBtn(Locators_WifiStability.savingTo_SIM);
				minWait();
				enterTextToInputField(Locators_WifiStability.name_NewContact, contactName);
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
				minWait();
				enterTextToInputField(Locators_WifiStability.phone_NewContact, contactNum);
				// aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
				minWait();
				// enterTextToInputField(Locators_WifiStability.phone_NewContact, contactNum);
				// aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
				// minWait();
			}
			clickBtn(Locators_WifiStability.save_Icon);
			minWait();
			clearAllow();
			// aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			minWait();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	

}
