package com.xp8.util;

import java.awt.AWTException;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;

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

import OCR.Read_File;
import OCR.my_main;
import application.AllQA;
import freemarker.core.CustomAttribute;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.AndroidKeyCode;

public class XP8_DeviceStability_Util_orio extends BaseUtil {

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

	public void deleteContacts() throws InterruptedException {
		try {
			minWait();
			System.out.println("IM in Contacts");
			if (isElementExist(Locators_DeviceStability.no_Contacts)) {
				APP_LOGS.info("No Contact");
				System.out.println("No Contact");
				minWait();
			} else {
				clickBtn(multi_Loc_Strategy(Locators_DeviceStability.deleteContactOptn1,
						Locators_DeviceStability.deleteContactOptn2, Locators_DeviceStability.deleteContactOptn3, null,
						null, 0, 0));
				minWait();
				clickBtn(multi_Loc_Strategy(Locators_DeviceStability.Selection_menu,
						Locators_DeviceStability.Selection_menu1, Locators_DeviceStability.Selection_menu2, null, null,
						0, 0));
				minWait();
				if (isElementExist(Locators_DeviceStability.ALL_Selection_menu)) {
					clickBtn(Locators_DeviceStability.ALL_Selection_menu);
					minWait();
				} else {
					minWait();
					clickBtn(Locators_DeviceStability.one_Selection_menu);
				}
				clickBtn(multi_Loc_Strategy(Locators_DeviceStability.OKBtn1, Locators_DeviceStability.OKBtn2,
						Locators_DeviceStability.OKBtn3, null, null, 0, 0));
				minWait();
				clickBtn(multi_Loc_Strategy(Locators_DeviceStability.OKBtnopt1, Locators_DeviceStability.OKBtnopt2,
						Locators_DeviceStability.OKBtnopt3, null, null, 0, 0));
				APP_LOGS.info("Contacts Deleted");
				minWait();
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->deleteContacts()");
			e.printStackTrace();

		} catch (Exception e) {

			test.log(LogStatus.ERROR, "Exeption in ->deleteContacts()");
		}
	}

	public void setDefaultAccount_Contact() throws InterruptedException, IOException {

		try {
			clickBtn(Locators_DeviceStability.moreOptions);
			minWait();
			clickBtn(Locators_DeviceStability.settings_Contact);
			minWait();
			clickBtn(Locators_DeviceStability.defaultAccount_NewContacts);
			minWait();
			clickBtn(Locators_DeviceStability.PHONE_RadioBtn);
			customWait(2000);
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			// clickBtn(Locators_DeviceStability_ATT.navigateUp);
			// Runtime.getRuntime().exec("adb -s "+p_Id+" shell input keyevent 4");
			minWait();
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->deleteContacts()");
			e.printStackTrace();

		} catch (Exception e) {

			test.log(LogStatus.ERROR, "Exeption in ->deleteContacts()");
		}
	}

	public void createContact(String name, String num) throws InterruptedException {
		/*
		 * Create contact
		 */
		try {

			minWait();
			Locators_DeviceStability.AddtoContact.click();
			minWait();

			if (isElementExist(Locators_DeviceStability.Choose_Phone)) {
				minWait();
				clickBtn(Locators_DeviceStability.Choose_Phone);
				customWait(2000);
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
			}
			if (isElementExist(Locators_DeviceStability.Contacts_Name)) {
				minWait();
			}

			customWait(4000);
			minWait();
			// enterTextToInputField(Locators_DeviceStability.name_NewContact, name);
			enterTextToInputField(multi_Loc_Strategy(Locators_DeviceStability.name_NewContact,
					Locators_DeviceStability.name_NewContact1, Locators_DeviceStability.name_NewContact2, null, null, 0,
					0), name);
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			minWait();
			enterTextToInputField(multi_Loc_Strategy(Locators_DeviceStability.phone_NewContact,
					Locators_DeviceStability.phone_NewContact1, Locators_DeviceStability.phone_NewContact2, null, null,
					0, 0), num);
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			minWait();
			enterTextToInputField(multi_Loc_Strategy(Locators_DeviceStability.email_NewContact,
					Locators_DeviceStability.email_NewContact1, Locators_DeviceStability.email_NewContact2, null, null,
					0, 0), "Sonimtech@gmail.com");
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			minWait();
			clickBtn(multi_Loc_Strategy(Locators_DeviceStability.Save_ConatctIcon1,
					Locators_DeviceStability.Save_ConatctIcon2, Locators_DeviceStability.Save_ConatctIcon3, null, null,
					0, 0));
			minWait();

			for (int i = 1; i <= 3; i++) {
				if (isElementExist(Locators_DeviceStability.AllowOptn)) {
					clickBtn(Locators_DeviceStability.AllowOptn);
				}
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->createContact()");
			e.printStackTrace();

		} catch (Exception e) {

			test.log(LogStatus.ERROR, "Exeption in ->createContact()");
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
			getcontactNameText = Locators_DeviceStability.ContactTitle.getText();
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
			String getPhoneNumText = Locators_DeviceStability.contact_phnNum.getText();

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
			getEmailText = Locators_DeviceStability.contact_Emailid.getText();
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
				test.log(LogStatus.PASS, "Contact created saved in phone memory at iteration: " + n);
				soft.assertTrue(true, " Contact created saved in phone memory at iteration: " + n);
			} else {

				APP_LOGS.info("Test case failed");
				test.log(LogStatus.ERROR, "Contact creation failed at iteration: " + n);
				soft.fail();
			}
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);

		}

		catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->validateContactCreation()");
			e.printStackTrace();

		} catch (Exception e) {

			test.log(LogStatus.ERROR, "Exeption in ->validateContactCreation()");
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
			getcontactNameText = Locators_DeviceStability.ContactTitle.getText();
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
			String getPhoneNumText = Locators_DeviceStability.contact_phnNum.getText();

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

		}

		catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->validateContactCreation_SIM()");
			e.printStackTrace();

		} catch (Exception e) {

			test.log(LogStatus.ERROR, "Exeption in ->validateContactCreation_SIM()");
		}
	}

	public void create_NewSMS_O(String number, String message) throws InterruptedException {
		/* Method used to create New SMS. */

		try {
			enter_Num_ToField_O(number);
			enterText_MessageField_O(message);
		}

		catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->create_NewSMS_O()");
			e.printStackTrace();

		} catch (Exception e) {

			test.log(LogStatus.ERROR, "Exeption in ->create_NewSMS_O()");
		}
	}

	public void create_NewSMS_B(String number, String message) throws InterruptedException {
		/* Method used to create New SMS. */

		try {
			enter_Num_ToField_B(number);
			enterText_MessageField_B(message);
		}

		catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->create_NewSMS_O()");
			e.printStackTrace();

		} catch (Exception e) {

			test.log(LogStatus.ERROR, "Exeption in ->create_NewSMS_O()");
		}
	}

	public void enterText_MessageField_O(String message) throws InterruptedException {
		/* Methos is Used enter Message into Message Field. */
		try {
			minWait();

			enterTextToInputField(multi_Loc_Strategy(Locators_DeviceStability.messageField_O,
					Locators_DeviceStability.messageField_O1, Locators_DeviceStability.messageField_O2, null, null, 0,
					0), message);
			minWait();

		}

		catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->enterText_MessageField_O()");
			e.printStackTrace();

		} catch (Exception e) {

			test.log(LogStatus.ERROR, "Exeption in ->enterText_MessageField_O()");
		}
	}

	public void enterText_MessageField_B(String message) throws InterruptedException {
		/* Methos is Used enter Message into Message Field. */
		try {
			minWait();
			if (isElementExist(Locators_DeviceStability.Text_Field_B)) {
				enterTextToInputField(Locators_DeviceStability.Text_Field_B, message);
				minWait();
			}
		}

		catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->enterText_MessageField_O()");
			e.printStackTrace();

		} catch (Exception e) {

			test.log(LogStatus.ERROR, "Exeption in ->enterText_MessageField_O()");
		}
	}

	public void navigateTo_NewSMS_O() throws InterruptedException {
		/* Used to Navigate to New Message Page. */
		try {

			clickBtn(multi_Loc_Strategy(Locators_DeviceStability.add_NewSMS_O, Locators_DeviceStability.add_NewSMS_O1,
					null, null, null, 0, 0));
			minWait();
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->navigateTo_NewSMS_O()");
			e.printStackTrace();

		} catch (Exception e) {

			test.log(LogStatus.ERROR, "Exeption in ->navigateTo_NewSMS_O()");
		}
	}

	public void navigateTo_NewSMS_S() throws InterruptedException {
		/* Used to Navigate to New Message Page. */
		try {

			clickBtn(Locators_DeviceStability.add_NewSMS_s);
			minWait();
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->navigateTo_NewSMS_O()");
			e.printStackTrace();

		} catch (Exception e) {

			test.log(LogStatus.ERROR, "Exeption in ->navigateTo_NewSMS_O()");
		}
	}

	public void enter_Num_ToField_O(String number) throws InterruptedException {
		/* Method is used to Enter Number into TO Field. */
		try {
			minWait();
			enterTextToInputField(multi_Loc_Strategy(Locators_DeviceStability.TO_Field_O,
					Locators_DeviceStability.TO_Field_O1, null, null, null, 0, 0), number);
			customWait(2000);
			System.out.println("Selecting COntact NAme");

			// customWait(2000);
		}

		catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->enter_Num_ToField_O()");
			e.printStackTrace();

		} catch (Exception e) {

			test.log(LogStatus.ERROR, "Exeption in ->enter_Num_ToField_O()");
		}
	}

	public void enter_Num_ToField_B(String number) throws InterruptedException {
		/* Method is used to Enter Number into TO Field. */
		try {
			minWait();
			enterTextToInputField(multi_Loc_Strategy(Locators_DeviceStability.To_Field_B1,
					Locators_DeviceStability.To_Field_B, Locators_DeviceStability.To_Field_B2, null, null, 0, 0),
					number);
			customWait(2000);
			System.out.println("Selecting COntact NAme");
			aDriver.pressKeyCode(AndroidKeyCode.ENTER);
			aDriver.pressKeyCode(AndroidKeyCode.BACK);
			// customWait(2000);
		}

		catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->enter_Num_ToField_O()");
			e.printStackTrace();

		} catch (Exception e) {

			test.log(LogStatus.ERROR, "Exeption in ->enter_Num_ToField_O()");
		}
	}

	public void clickOn_Send_O() throws InterruptedException {
		/* Method used to click on Send Button. */
		try {
			customWait(500);
			clickBtn(Locators_DeviceStability.send_Icon_O);
			minWait();
		}

		catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->clickOn_Send_O()");
			e.printStackTrace();

		} catch (Exception e) {

			test.log(LogStatus.ERROR, "Exeption in ->clickOn_Send_O()");
		}
	}

	public void clickOn_Send_B() throws InterruptedException {
		/* Method used to click on Send Button. */
		try {
			customWait(500);
			clickBtn(multi_Loc_Strategy(Locators_DeviceStability.send_Icon_B, Locators_DeviceStability.send_Icon_B1,
					Locators_DeviceStability.send_Icon_B2, null, null, 0, 0));
			System.out.println("sending");
			minWait();
		}

		catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->clickOn_Send_O()");
			e.printStackTrace();

		} catch (Exception e) {

			test.log(LogStatus.ERROR, "Exeption in ->clickOn_Send_O()");
		}
	}

	public void validate_SentMessage_O(int n, SoftAssert soft) throws InterruptedException {
		/* To validate the Sent Message. */
		WebDriverWait wait = new WebDriverWait(aDriver, 60);

		try {
			wait.until(ExpectedConditions.visibilityOf(Locators_DeviceStability.Delivered));
			if (isElementExist(Locators_DeviceStability.Delivered) || isElementExist(Locators_DeviceStability.Delivered)
					|| isElementExist(Locators_DeviceStability.not_Sent_Text)) {
				check = true;
				APP_LOGS.info("Message sent Succeccfully");
				System.out.println("Sent");

				test.log(LogStatus.PASS, "SMS Sent Successfully at iteration " + n);
				soft.assertTrue(check, "SMS Sent Successfully at iteration ");
				APP_LOGS.info("SMS Sent Successfully at iteration ");
			} else {
				APP_LOGS.info("SMS didn't sent");
				soft.fail();
				test.log(LogStatus.FAIL, "Message didn't sent at iteration " + n);
			}

		}

		catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->validate_SentMessage_O()");
			e.printStackTrace();

		} catch (Exception e) {

			test.log(LogStatus.ERROR, "Exeption in ->validate_SentMessage_O()");
		}

		aDriver.pressKeyCode(AndroidKeyCode.BACK);
		minWait();
		aDriver.pressKeyCode(AndroidKeyCode.BACK);
	}

	public void validate_SentMessage_B(int n, SoftAssert soft) throws InterruptedException {
		/* To validate the Sent Message. */
		WebDriverWait wait = new WebDriverWait(aDriver, 60);

		try {
			wait(Locators_DeviceStability.now_sms1, 15);
			if (isElementExist(Locators_DeviceStability.now_sms1) || isElementExist(Locators_DeviceStability.now_sms3)
					|| isElementExist(Locators_DeviceStability.now_sms)
					|| isElementExist(Locators_DeviceStability.Delivered)
					|| isElementExist(Locators_DeviceStability.now_sms1)) {
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
		}

		catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->validate_SentMessage_O()");
			e.printStackTrace();

		} catch (Exception e) {

			test.log(LogStatus.ERROR, "Exeption in ->validate_SentMessage_O()");
		}
	}

	public void delete_SMS_O() throws InterruptedException {
		/* This Method delete the First Thread in the List. */

		try {
			minWait();
			clickBtn(Locators_DeviceStability.firstSMS_InList_O);
			minWait();
			clickBtn(Locators_DeviceStability.moreOption_O);
			minWait();
			clickBtn(Locators_DeviceStability.delete_Messages);
			minWait();
			clickBtn(Locators_DeviceStability.ALL_Msg);
			minWait();
			clickBtn(Locators_DeviceStability.delete_Btn);
			minWait();
			clickBtn(Locators_DeviceStability.Delete);
			minWait();
			// test.log(LogStatus.INFO, "SMS or MMS is deleted.");
		}

		catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->delete_SMS_O()");
			e.printStackTrace();

		} catch (Exception e) {

			test.log(LogStatus.ERROR, "Exeption in ->delete_SMS_O()");
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
			enableSwitch(Locators_DeviceStability.enabled_Airplane, Locators_DeviceStability.disabled_Airplane,
					Locators_DeviceStability.switch_widget);
			customWait(5000);
			clickBtn(multi_Loc_Strategy(Locators_DeviceStability.OKBtn1, Locators_DeviceStability.OKBtn2,
					Locators_DeviceStability.OKBtn3, null, null, 0, 0));
			minWait();
			disableSwitch(Locators_DeviceStability.disabled_Airplane, Locators_DeviceStability.enabled_Airplane,
					Locators_DeviceStability.switch_widget);
			customWait(15000);
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
		}

		catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->performAction_O()");
			e.printStackTrace();

		} catch (Exception e) {

			test.log(LogStatus.ERROR, "Exeption in ->performAction_O()");
		}
	}

	public void sendSMS_fromRefDevice(String AutomationMessagee) {

		try {

			minWait();
			if (r_b_No.contains("8A.")) {
				if (r_b_No.contains("-10.") || r_b_No.contains("-30.")) {

					System.out.println("SSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSS");

					Runtime.getRuntime()
							.exec("adb -s " + r_Id
									+ " shell service call isms 7 i32 0 s16 \"com.android.mms.service\" s16 " + pryNum
									+ " s16 \"null\" s16 \"SMAT\" s16 \"null\" s16 \"null\"");

				} else if (r_b_No.contains("-11.") || r_b_No.contains("-12.") || r_b_No.contains("-18.")
						|| r_b_No.contains("-26.") || r_b_No.contains("-29.")) {

					Runtime.getRuntime()
							.exec("adb -s " + r_Id
									+ " shell service call isms 7 i32 0 s16 \"com.android.mms.service\" s16 " + pryNum
									+ " s16 \"null\" s16 \"SMAT\" s16 \"null\" s16 \"null\"");

				} else if (r_b_No.contains("-15.")) {
					minWait();

					Runtime.getRuntime()
							.exec("adb -s " + r_Id
									+ " shell service call isms 7 i32 0 s16 \"com.android.mms.service\" s16 " + pryNum
									+ " s16 \"null\" s16 \"SMAT\" s16 \"null\" s16 \"null\"");

				}
			} else if (r_b_No.contains("5SA.")) {
				minWait();

				Runtime.getRuntime()
						.exec("adb -s " + r_Id + " shell service call isms 7 i32 0 s16 \"com.android.mms.service\" s16 "
								+ pryNum + " s16 \"null\" s16 \"SMAT\" s16 \"null\" s16 \"null\"");

			}
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->sendSMS_fromRefDevice()");
			e.printStackTrace();

		} catch (Exception e) {

			test.log(LogStatus.ERROR, "Exeption in ->sendSMS_fromRefDevice()");
		}
	}

	public void validate_ReceivedMessage(int n, SoftAssert soft) throws InterruptedException {
		/* User should navigate to the Messaging APP home Page in the Primary device. */
		WebDriverWait wait = new WebDriverWait(aDriver, 80);

		try {
			wait.until(ExpectedConditions.visibilityOf(Locators_DeviceStability.test1));
			if (isElementExist(Locators_DeviceStability.test1) || isElementExist(Locators_DeviceStability.now_Text11)
					|| isElementExist(Locators_DeviceStability.now_Text)
					|| isElementExist(Locators_DeviceStability.not_Sent_Text)) {
				check = true;
				APP_LOGS.info("Message Recieved Successfully at iteration : " + n);
				soft.assertTrue(true, "Message Recieved Successfully at iteration : " + n);
				test.log(LogStatus.PASS, "Message Recieved Successfully at iteration : " + n);
			} else {
				APP_LOGS.info("Message is not recievied for perticular duration: " + n);
				test.log(LogStatus.ERROR, "Message is not recievied for perticular duration: " + n);

			}
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, p_Id + "is not receiving MSG within 80 Second  ");
			e.printStackTrace();

		} catch (Exception e) {

			test.log(LogStatus.ERROR, p_Id + "is not receiving MSG within 80 Second  ");
		}
	}

	public void validate_ReceivedMessage_b(int n, SoftAssert soft) throws InterruptedException {
		/* User should navigate to the Messaging APP home Page in the Primary device. */
		WebDriverWait wait = new WebDriverWait(aDriver, 80);

		try {
			wait.until(ExpectedConditions.elementToBeClickable(Locators_DeviceStability.now_Text11));
			// wait(Locators_DeviceStability.now_Text1, 20);
			if (isElementExist(Locators_DeviceStability.now_Text11) || isElementExist(Locators_DeviceStability.now_Text)
					|| isElementExist(Locators_DeviceStability.not_Sent_Text)) {
				check = true;
				APP_LOGS.info("Message Recieved Successfully at iteration : ");
				soft.assertTrue(true, "Message Recieved Successfully at iteration : ");
				test.log(LogStatus.PASS, "Message Recieved Successfully at iteration : " + n);
			} else {
				APP_LOGS.info("Message is not recievied for perticular duration: " + n);
				test.log(LogStatus.ERROR, "Message is not recievied for perticular duration: " + n);

			}
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, p_Id + "is not receiving MSG within 80 Second  ");
			e.printStackTrace();

		} catch (Exception e) {

			test.log(LogStatus.ERROR, p_Id + "is not receiving MSG within 80 Second  ");
		}
	}

	public void validate_RecievedMessage_O(int n, SoftAssert soft) throws InterruptedException {
		/* User should navigate to the Messaging APP home Page in the Primary device. */

		WebDriverWait wait = new WebDriverWait(aDriver, 80);

		try {
			wait.until(ExpectedConditions.visibilityOf(Locators_DeviceStability.Delivered));
			// customWait(8000);
			clickBtn(Locators_DeviceStability.firstSMS_InList_O);
			if (isElementExist(Locators_DeviceStability.now_Text)
					|| isElementExist(Locators_DeviceStability.unread_Conv_Messages)
					|| isElementExist(Locators_DeviceStability.Delivered)
					|| isElementExist(Locators_DeviceStability.not_Sent_Text)
					|| isElementExist(Locators_DeviceStability.unread_Conv_Messages1)) {
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
		}
		// test.log(LogStatus.FAIL,"Expected condition failed: waiting for visibility of
		// element located at iteration "+n);
		catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->validate_RecievedMessage_O()");
			e.printStackTrace();

		} catch (Exception e) {

			test.log(LogStatus.ERROR, "Exeption in ->validate_RecievedMessage_O()");
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
			clickBtn(multi_Loc_Strategy(Locators_DeviceStability.Search_ConatctIcon,
					Locators_DeviceStability.Search_ConatctIcon1, Locators_DeviceStability.Search_ConatctIcon2, null,
					null, 820, 146));
			wait(Locators_DeviceStability.findContacts_O, 80);
			enterTextToInputField(Locators_DeviceStability.findContacts_O, name);
			minWait();
			clickBtn(Locators_DeviceStability.longpress_FirstContact_O);
			minWait();
		}

		catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->searchContact()");
			e.printStackTrace();

		} catch (Exception e) {

			test.log(LogStatus.ERROR, "Exeption in ->searchContact()");
		}
	}

	public void initiateCall() throws InterruptedException {
		/*
		 * initiate a call from Saved contacts
		 */
		try {
			/*
			 * minWait(); clickBtn(Locators_DeviceStability.SavedContact); minWait();
			 * clickBtn(Locators_DeviceStability.contact_phnNum); minWait();
			 */
			customWait(4000);
			Runtime.getRuntime()
					.exec("adb -s " + p_Id + " shell am start -a android.intent.action.CALL -d tel:" + refNum);
			Thread.sleep(10000);
		}

		catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->initiateCall()");
			e.printStackTrace();

		} catch (Exception e) {

			test.log(LogStatus.ERROR, "Exeption in ->initiateCall()");
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
				Process child = Runtime.getRuntime().exec("adb -s " + r_Id + " shell service call telecom 29");
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
		customWait(3000);
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
					soft.assertTrue(check, "MO-Voice call from " + callType + " is validated at : iteration " + n);
					APP_LOGS.info("MO-Voice call from " + callType + " is validated at : iteration" + n);
					break;
				} else {

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
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->selectPage()");
			e.printStackTrace();

		} catch (Exception e) {

			test.log(LogStatus.ERROR, "Exeption in ->selectPage()");
		}
	}

	public void callHistory() throws InterruptedException, IOException {
		/*
		 * call save contact
		 */
		try {
			minWait();
			if (isElementExist(Locators_DeviceStability.MoreOptions)) {
				clickBtn(
						multi_Loc_Strategy(Locators_DeviceStability.moreOptions, Locators_DeviceStability.MoreOptions11,
								Locators_DeviceStability.MoreOptions2, null, null, 961, 158));
				minWait();
			} else {
				clickBtn(Locators_DeviceStability.MoreOptions1);
				minWait();
			}
			customWait(1000);
			clickBtn(multi_Loc_Strategy(Locators_DeviceStability.callHistory_O, Locators_DeviceStability.callHistory_O1,
					Locators_DeviceStability.callHistory_O2, null, null, 670, 176));
			minWait();
			clickBtn(multi_Loc_Strategy(Locators_DeviceStability.Call_Contact, Locators_DeviceStability.Call_Contact1,
					Locators_DeviceStability.Call_Contact2, Locators_DeviceStability.Call_Contact3,
					Locators_DeviceStability.Call_Contact4, 947, 627));
			minWait();
			APP_LOGS.info("initiated a call");
			minWait();
			customWait(2000);
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->callHistory()");
			e.printStackTrace();

		} catch (Exception e) {

			test.log(LogStatus.ERROR, "Exeption in ->callHistory()");
		}
	}

	public void performAction() throws IOException, InterruptedException {
		/*
		 * Perform action for IMS registered check
		 */
		try {
			minWait();
			Runtime.getRuntime().exec("adb -s " + p_Id + " shell am start -a android.settings.AIRPLANE_MODE_SETTINGS");
			minWait();
			disable_AirplaneMode();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->performAction()");
			e.printStackTrace();

		} catch (Exception e) {

			test.log(LogStatus.ERROR, "Exeption in ->performAction()");
		}
	}

	public void performAction_Ref() throws IOException, InterruptedException {
		/*
		 * Perform action for IMS registered check
		 */
		try {
			minWait();
			Process child = Runtime.getRuntime().exec("adb -s " + r_Id + " settings get global airplane_mode_on");
			InputStream lsOut = child.getInputStream();
			InputStreamReader r = new InputStreamReader(lsOut);
			BufferedReader in = new BufferedReader(r);
			String line = in.readLine();
			System.out.println(line);

			minWait();
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->performAction()");
			e.printStackTrace();

		} catch (Exception e) {

			test.log(LogStatus.ERROR, "Exeption in ->performAction()");
		}
	}

	public void performAction_s() throws IOException, InterruptedException {
		/*
		 * Perform action for IMS registered check
		 */
		try {
			// customWait(2000);
			// Runtime.getRuntime().exec("adb -s "+p_Id+" shell am start -a
			// android.settings.AIRPLANE_MODE_SETTINGS");
			disable_AirplaneMode();

		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->performAction()");
			e.printStackTrace();

		} catch (Exception e) {

			test.log(LogStatus.ERROR, "Exeption in ->performAction()");
		}
	}

	public boolean validate_RIL_Logs(String filename, String validationString, String infoForFailure)
			throws InterruptedException {

		customWait(2000);
		boolean check1 = searchString(validationString, filename);
		SoftAssert sf = new SoftAssert();
		if (check1) {
			check = true;
			System.out.println(check);
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
			// ScrollToElement(Locators_DeviceStability.SettingsList, "Wi-Fi");
			customWait(2000);
			clickBtn(Locators_DeviceStability.wifi);
			minWait();
			APP_LOGS.info("Selected WIFI option");
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->selectWIFIOptn()");
			e.printStackTrace();

		} catch (Exception e) {

			test.log(LogStatus.ERROR, "Exeption in ->selectWIFIOptn()");
		}
	}

	public void selectSSIDwifi() throws InterruptedException {
		/*
		 * Select IDC wifi which is available
		 */

		try {
			scrollToElements(Locators_DeviceStability.wifi_IDC);
			customWait(4000);
			for (int i = 1; i <= 50; i++) {

				if (isElementExist(Locators_DeviceStability.wifi_IDC)) {
					customWait(2000);
					clickBtn(Locators_DeviceStability.wifi_IDC);
					APP_LOGS.info("IDC available secured wifi is Selected");
					System.out.println("IDC available secured wifi is Selected");
					break;
				} else if (isElementExist(Locators_DeviceStability.wifi_Dellas)) {
					minWait();
					clickBtn(Locators_DeviceStability.wifi_Dellas);
					APP_LOGS.info("Dellas available secured wifi is Selected");
					break;
				} else if (isElementExist(Locators_DeviceStability.wifi_Cannada)) {
					minWait();
					clickBtn(Locators_DeviceStability.wifi_Cannada);
					APP_LOGS.info("Cannada available secured wifi is Selected");
					break;
				} else {
					minWait();
					aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
					continue;
				}
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->selectSSIDwifi()");
			e.printStackTrace();

		} catch (Exception e) {

			test.log(LogStatus.ERROR, "Exeption in ->selectSSIDwifi()");
		}
	}

	public void disconnectSSIDifConnected() throws InterruptedException {
		/*
		 * disconnect wifi[SSID]if IDC wifi connected
		 */
		SoftAssert Sa = new SoftAssert();
		try {
			String getTxt = Locators_DeviceStability.wificonnected.getText();
if (wait(Locators_DeviceStability.wificonnected, 20))
 {
	clickBtn(Locators_DeviceStability.wificonnected);
	if (wait(Locators_DeviceStability.wifi_IDC_ForgetBtn, 20)) {
		clickBtn(multi_Loc_Strategy(Locators_DeviceStability.wifi_IDC_ForgetBtn,
				Locators_DeviceStability.wifi_IDC_ForgetBtn1, Locators_DeviceStability.wifi_IDC_ForgetBtn2, null,
				null, 0, 0));

	}
}
			APP_LOGS.info("IDC available secured wifi is disconnected");
			System.out.println("Disconnected");
			customWait(5000);
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->disconnectSSIDifConnected()");
			e.printStackTrace();

		} catch (Exception e) {

			test.log(LogStatus.ERROR, "Exeption in ->disconnectSSIDifConnected()");
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
			enterTextToInputField(Locators_DeviceStability.urlBar_Chrome, url);
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
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->validate_And_BrowseIn_Chrome1()");
			e.printStackTrace();

		} catch (Exception e) {

			test.log(LogStatus.ERROR, "Exeption in ->validate_And_BrowseIn_Chrome1()");
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
			clickBtn(Locators_DeviceStability.MoreOptions);
			minWait();
			clickBtn(Locators_DeviceStability.RefreshOptn);
			minWait();
			// refresh btn
			customWait(5000);
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->selectRefresh()");
			e.printStackTrace();

		} catch (Exception e) {

			test.log(LogStatus.ERROR, "Exeption in ->selectRefresh()");
		}
	}

	public void enterPassword(String password) throws InterruptedException, IOException {
		/*
		 * enter Password for SSID
		 */
		try {
			minWait();
			if (isElementExist(Locators_DeviceStability.SSIDTxt)) {
				String getSSIDTitle = Locators_DeviceStability.SSIDTxt.getText();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
				minWait();
				changeToNumberMode();
				customWait(2000);
				if (getSSIDTitle.equalsIgnoreCase("IDCSONWAP")) {
					minWait();
					clickBtn(Locators_DeviceStability.wifi_IDC_Psswd);
					customWait(4000);
					Runtime.getRuntime().exec("adb -s " + p_Id + " shell input text " + password);
					customWait(3000);
				}
				minWait();
				String psswrd = Locators_DeviceStability.wifi_IDC_Psswd.getText();
				System.out.println(psswrd);
				customWait(1000);
				clickBtn(Locators_DeviceStability.wifi_IDC_ConnectBtn);
				customWait(3000);
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->enterPassword()");
			e.printStackTrace();

		} catch (Exception e) {

			test.log(LogStatus.ERROR, "Exeption in ->enterPassword()");
		}
	}

	public void changeToNumberMode() throws InterruptedException {

		try {
			minWait();
			Locators_DeviceStability.wifi_IDC_Psswd.sendKeys("123");
			customWait(1500);
			String text = Locators_DeviceStability.wifi_IDC_Psswd.getText();
			System.out.println(text);
			if (!text.equals("123")) {
				for (int i = 0; i < 3; i++) {
					aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_POUND);
					minWait();
				}
			}
			Locators_DeviceStability.wifi_IDC_Psswd.clear();
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->changeToNumberMode()");
			e.printStackTrace();

		} catch (Exception e) {

			test.log(LogStatus.ERROR, "Exeption in ->changeToNumberMode()");
		}
	}

	public void changeToNumberModeSMS() throws InterruptedException {

		try {
			minWait();
			Locators_DeviceStability.wifi_IDC_Psswd.sendKeys("123");
			customWait(1500);
			String text = Locators_DeviceStability.wifi_IDC_Psswd.getText();
			System.out.println(text);
			if (!text.equals("123")) {
				for (int i = 0; i < 3; i++) {
					aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_POUND);
					minWait();
				}
			}
			Locators_DeviceStability.wifi_IDC_Psswd.clear();
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->changeToNumberModeSMS()");
			e.printStackTrace();

		} catch (Exception e) {

			test.log(LogStatus.ERROR, "Exeption in ->changeToNumberModeSMS()");
		}
	}

	public void validateWifiConnect(int n, SoftAssert soft) throws InterruptedException, IOException {
		/*
		 * Validate wifi connection
		 */

		try {
			WebDriverWait wait = new WebDriverWait(aDriver, 30);
			wait.until(ExpectedConditions.visibilityOf(Locators_DeviceStability.wificonnected));
			String getTxt = Locators_DeviceStability.wificonnected.getText();
			System.out.println(getTxt);
			if (getTxt.equalsIgnoreCase("Connected")) {
				check = true;
				APP_LOGS.info("Connected to the wifi network successfully at iteration : " + n);
				test.log(LogStatus.PASS, "Connected to the wifi network successfully at iteration : " + n);
				soft.assertTrue(true, "Connected to the wifi network successfully at iteration : " + n);
			}

			else {
				APP_LOGS.info("Not connected to the wifi network at iteration : " + n);
				test.log(LogStatus.FAIL, "Not connected to the wifi network at iteration : " + n);
				soft.fail();
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->validateWifiConnect()");
			e.printStackTrace();

		} catch (Exception e) {

			test.log(LogStatus.ERROR, "Exeption in ->validateWifiConnect()");
		}
	}

	public void validate_ON_OFF_WiFiFeature(WebElement feature, int n) throws InterruptedException, IOException {
		/*
		 * validate Turn On and OFF wifi toggle button
		 */
		SoftAssert S1 = new SoftAssert();
		try {
			customWait(2000);
			enableFeature(Locators_DeviceStability.enabled, Locators_DeviceStability.disabled,
					Locators_DeviceStability.switch_Title);
			minWait();

			if (isElementExist(Locators_DeviceStability.enabled)) {
				check1 = true;
				APP_LOGS.info(" check1");
			}
			customWait(3000);
			disableFeature(Locators_DeviceStability.enabled, Locators_DeviceStability.disabled);
			if (isElementExist(Locators_DeviceStability.disabled)) {
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
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->validate_ON_OFF_WiFiFeature()");
			e.printStackTrace();

		} catch (Exception e) {

			test.log(LogStatus.ERROR, "Exeption in ->validate_ON_OFF_WiFiFeature()");
		}

	}

	public void acceptDefault() throws InterruptedException {
		/*
		 * Accept default Options
		 */

		try {
			customWait(2000);
			if (isElementExist(Locators_DeviceStability.AccptBtn)) {
				Locators_DeviceStability.AccptBtn.click();
			}
			customWait(4000);
			if (isElementExist(Locators_DeviceStability.NextIcon)) {
				Locators_DeviceStability.NextIcon.click();
			}
			customWait(4000);
			if (isElementExist(Locators_DeviceStability.NothanksBtn)) {
				Locators_DeviceStability.NothanksBtn.click();
			}
			customWait(4000);
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->acceptDefault()");
			e.printStackTrace();

		} catch (Exception e) {

			test.log(LogStatus.ERROR, "Exeption in ->acceptDefault()");
		}
	}

	public void enterUrl() throws InterruptedException, IOException {
		/*
		 * Enter the Website in Url bar
		 */
		try {

			String url = "www.google.com";
//			Locators_DeviceStability.chromeSearch.clear();
//			enterTextToInputField(Locators_DeviceStability.chromeSearch, url);
//			minWait();
//			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_ENTER);
			Runtime.getRuntime()
					.exec("adb -s " + p_Id + " shell am start -a android.intent.action.VIEW -d http:/" + url);
			Thread.sleep(2000);
			APP_LOGS.info(" URl is entered is sucessful.");

		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->enterUrl()");
			e.printStackTrace();

		} catch (Exception e) {

			test.log(LogStatus.ERROR, "Exeption in ->enterUrl()");
		}

	}

	public void validateUrlEntered(int n, SoftAssert soft) {
		/*
		 * validate the website page is loaded via adb logs
		 */

		try {
			customWait(20000);
			if (isElementExist(Locators_DeviceStability.networkNotAvailable)
					|| isElementExist(Locators_DeviceStability.YouAre_offline_lnk3)) {
				test.log(LogStatus.FAIL, "Entered Website page not Loaded successfully at iteration: " + n);
				soft.fail();
			} else {
				check = true;
				APP_LOGS.info("Url entered sucessfully.");
				test.log(LogStatus.PASS, "Entered Website page Loaded Successfully at iteration: " + n);
				soft.assertTrue(true, "Entered Website page Loaded Successfully at iteration: " + n);
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->validateUrlEntered()");
			e.printStackTrace();

		} catch (Exception e) {

			test.log(LogStatus.ERROR, "Exeption in ->validateUrlEntered()");
		}
	}

	public static void imsLogs(String fileName) throws AWTException, InterruptedException {

		String path = System.getProperty("user.dir");
		customWait(1000);
		try {
			Runtime.getRuntime().exec("cmd /C \"adb logcat -b all -v time>" + path + "\\src\\test\\resources\\adbLogs\\"
					+ fileName + ".txt\"");
			Thread.sleep(1000);
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->imsLogs()");
			e.printStackTrace();

		} catch (Exception e) {

			test.log(LogStatus.ERROR, "Exeption in ->imsLogs()");
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
				if (isElementExist(Locators_DeviceStability.firstSMS_InList)) {
					minWait();
					TouchAction touchaction = new TouchAction(aDriver);
					touchaction.longPress(Locators_DeviceStability.firstSMS_InList).perform().release();
					minWait();
					clickBtn(Locators_DeviceStability.delete_Icon_SMS);
					minWait();
					if (isElementExist(Locators_DeviceStability.delete_Confirm)) {
						clickBtn(Locators_DeviceStability.delete_Confirm);
						minWait();
					} else {
						clickBtn(Locators_DeviceStability.delete_moreOption);
						minWait();
					}
				} else if (isElementExist(Locators_DeviceStability.first_sms_Thread)) {
					minWait();
					TouchAction touchaction = new TouchAction(aDriver);
					touchaction.longPress(Locators_DeviceStability.first_sms_Thread).perform().release();
					minWait();
					clickBtn(Locators_DeviceStability.delete_Icon);
					minWait();
					if (isElementExist(Locators_DeviceStability.delete_Confirm)) {
						clickBtn(Locators_DeviceStability.delete_Confirm);
						minWait();
					} else {
						clickBtn(Locators_DeviceStability.delete_moreOption);
						minWait();
					}
				} else {

					Reporter.log("No Threads Found");
					break;
				}
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->delete_All_Threads()");
			e.printStackTrace();

		} catch (Exception e) {

			test.log(LogStatus.ERROR, "Exeption in ->delete_All_Threads()");
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
			if (isElementExist(Locators_DeviceStability.add_NewSMS)) {
				clickBtn(Locators_DeviceStability.add_NewSMS);
				minWait();
			}

			if (isElementExist(Locators_DeviceStability.new_Message_Icon)) {
				clickBtn(Locators_DeviceStability.new_Message_Icon);
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
			// launchAppThroughABD("");
			// clickBtn(Locators_DeviceStability.msg_Screen);
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
			if (isElementExist(Locators_DeviceStability.zero_Characters_FirstPage)) {
				System.out.println("Im out");
				charAndPageNum1 = charAndPageNum.getText();
			} else if (isElementExist(Locators_DeviceStability.zero_Characters_FirstPage1)) {
				System.out.println("Im in");
				charAndPageNum1 = Locators_DeviceStability.zero_Characters_FirstPage1.getText();
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
			if (isElementExist(Locators_DeviceStability.zero_Characters_FirstPage_O)) {
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

	public void validate_CharacterAndPageNumber_O(String expectedcharAndPageNum, int n) throws InterruptedException {
		/*
		 * This Meathod is to validate Characters and Page Number Precondition : User
		 * should be in Message window with Typed Message
		 */
		SoftAssert sf = new SoftAssert();
		try {
			minWait();
			takeScreenShot();
			Read_File.takeScreenShotForOcr("TestingMsg");
			my_main.validate_Using_OCR("TestingMsg.png");

			String msgfile = readFile(
					"C:\\Users\\navyashree.m\\Desktop\\XP8_AutomationProduct\\src\\test\\resources\\OCR_FILES\\ocr.txt");
			System.out.println(msgfile);
			System.out.println(expectedcharAndPageNum);

			if (expectedcharAndPageNum.contains(msgfile)) {
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

	public void delete_SMS1() throws InterruptedException {
		/* This Method delete the First Thread in the List. */

		try {
			minWait();
			clickBtn(Locators_DeviceStability.firstSMS_InList1);
			minWait();
			clickBtn(Locators_DeviceStability.moreOptions);
			minWait();
			clickBtn(Locators_DeviceStability.delete_moreOption);
			minWait();
			clickBtn(Locators_DeviceStability.delete_Confirm);
			minWait();
			// test.log(LogStatus.INFO, "SMS or MMS is deleted.");
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->delete_SMS1()");
			e.printStackTrace();

		} catch (Exception e) {

			test.log(LogStatus.ERROR, "Exeption in ->delete_SMS1()");
		}
	}

	public void delete_SMS() throws InterruptedException {
		/* This Method delete the First Thread in the List. */

		try {
			System.out.println("IN Delete");
			minWait();
			if (isElementExist(Locators_DeviceStability.firstSMS_InList)) {
				clickBtn(Locators_DeviceStability.firstSMS_InList);
				minWait();
				clickBtn(multi_Loc_Strategy(Locators_DeviceStability.moreOptions, Locators_DeviceStability.moreOptions1,
						null, null, null, 1064, 164));
				minWait();
				clickBtn(multi_Loc_Strategy(Locators_DeviceStability.delete_Thread,
						Locators_DeviceStability.delete_Thread1, Locators_DeviceStability.delete_Thread2, null, null,
						720, 146));
				minWait();
				clickBtn(multi_Loc_Strategy(Locators_DeviceStability.delete_Confirm,
						Locators_DeviceStability.delete_Confirm1, Locators_DeviceStability.delete_Confirm2, null, null,
						856, 1157));
				minWait();
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->delete_SMS()");
			e.printStackTrace();

		} catch (Exception e) {

			test.log(LogStatus.ERROR, "Exeption in ->delete_SMS()");
		}
	}

	public void delete_SMS_s() throws InterruptedException {
		/* This Method delete the First Thread in the List. */

		try {
			System.out.println("IN Delete");
			minWait();
			if (isElementExist(Locators_DeviceStability.moreOptions)) {
//				clickBtn(Locators_DeviceStability.firstSMS_InList11);
//				minWait();
				clickBtn(multi_Loc_Strategy(Locators_DeviceStability.moreOptions, Locators_DeviceStability.moreOptions1,
						null, null, null, 1064, 164));
				minWait();
				clickBtn(multi_Loc_Strategy(Locators_DeviceStability.delete_moreOption1,
						Locators_DeviceStability.delete_moreOption, Locators_DeviceStability.delete_Thread2, null, null,
						720, 146));
				minWait();
				clickBtn(multi_Loc_Strategy(Locators_DeviceStability.delete_Confirm_s,
						Locators_DeviceStability.delete_Confirm_s1, Locators_DeviceStability.delete_Confirm2, null,
						null, 856, 1157));
				minWait();
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->delete_SMS()");
			e.printStackTrace();

		} catch (Exception e) {

			test.log(LogStatus.ERROR, "Exeption in ->delete_SMS()");
		}
	}

	public void clickOn_Send1() throws InterruptedException {
		/* Method used to click on Send Button. */
		try {
			customWait(500);
			clickBtn(Locators_DeviceStability.send_Icon1);
			minWait();
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->clickOn_Send1()");
			e.printStackTrace();

		} catch (Exception e) {

			test.log(LogStatus.ERROR, "Exeption in ->clickOn_Send1()");
		}
	}

	public void navigateTo_NewSMS1() throws InterruptedException {
		/* Used to Navigate to New Message Page. */
		try {
			clickBtn(Locators_DeviceStability.add_NewSMS1);
			minWait();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void enterText_MessageField1(String message) throws InterruptedException {
		/* Methos is Used enter Message into Message Field. */
		try {
			minWait();
			enterTextToInputField(Locators_DeviceStability.messageField1, message);
			minWait();
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->clickOn_Send1()");
			e.printStackTrace();

		} catch (Exception e) {

			test.log(LogStatus.ERROR, "Exeption in ->clickOn_Send1()");
		}
	}

	public void enter_Num_ToField1(String number) throws InterruptedException {
		/* Method is used to Enter Number into TO Field. */
		try {
			minWait();
			enterTextToInputField(Locators_DeviceStability.TO_Field1, number);
			customWait(2000);
			clickBtn(Locators_DeviceStability.contactPicker);
			customWait(2000);
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->clickOn_Send1()");
			e.printStackTrace();

		} catch (Exception e) {

			test.log(LogStatus.ERROR, "Exeption in ->clickOn_Send1()");
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
			if (isElementExist(Locators_DeviceStability.TO_Field1)) {
				enterTextToInputField(Locators_DeviceStability.TO_Field1, cellNo);
				minWait();
			}

			if (isElementExist(Locators_DeviceStability.TO_Field_enter)) {
				enterTextToInputField(Locators_DeviceStability.TO_Field_enter, cellNo);
				minWait();
			}

			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_ENTER);
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->clickOn_Send1()");
			e.printStackTrace();

		} catch (Exception e) {

			test.log(LogStatus.ERROR, "Exeption in ->clickOn_Send1()");
		}
		sf.assertAll();
	}

	public void type_Message(String typeMessage) throws InterruptedException {
		SoftAssert sf = new SoftAssert();
		try {
			minWait();
			if (isElementExist(Locators_DeviceStability.type_Message)) {
				enterTextToInputField(Locators_DeviceStability.type_text2, typeMessage);
			}
			if (isElementExist(Locators_DeviceStability.type_Message_enter)) {
				enterTextToInputField(Locators_DeviceStability.type_Message_enter, typeMessage);
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
			clickBtn(Locators_DeviceStability.send);
			minWait();
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->clickOn_Send1()");
			e.printStackTrace();

		} catch (Exception e) {

			test.log(LogStatus.ERROR, "Exeption in ->clickOn_Send1()");
		}
		sf.assertAll();
	}

	public void clickOnAppList() throws InterruptedException {

		try {
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_HOME);
			customWait(2000);
			clickBtn(Locators_DeviceStability.app_List);
			minWait();
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->clickOnAppList()");
			e.printStackTrace();

		} catch (Exception e) {

			test.log(LogStatus.ERROR, "Exeption in ->clickOnAppList()");
		}

	}

	public boolean disable_AirplaneMode() {
		/*
		 * disable data from data usage window
		 */
		boolean disabled = false;
		try {
			scrollTo("Mobile network");
			if (!Locators_DeviceStability.checkMobilenetwork.isEnabled()) {
				scrollToText("Airplane mode");

				disabled = true;
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.ERROR, "Error in the locators->disable_AirplaneMode() ");
			e.printStackTrace();
		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exception in ->disable_AirplaneMode()");
		}
		return disabled;
	}

	public void clearSMSPermissions_O() throws InterruptedException {
		try {
			customWait(2000);
			if (isElementExist(Locators_DeviceStability.StartMessaging)) {
				clickBtn(Locators_DeviceStability.StartMessaging);
				minWait();
				clickBtn(Locators_DeviceStability.skipProvisioning);
				minWait();
			}
			if (isElementExist(Locators_DeviceStability.OKBtnopt1)) {
				clickBtn(Locators_DeviceStability.OKBtnopt1);
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->clearSMSPermissions_O()");
			e.printStackTrace();

		} catch (Exception e) {

			test.log(LogStatus.ERROR, "Exeption in ->clearSMSPermissions_O()");
		}

	}

	public void validate_SentMessage(int n, SoftAssert soft) throws InterruptedException {
		/* To validate the Sent Message. */
		try {
			if (p_b_No.contains("-10.")) {
				System.out.println("Sent msg");
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
				wait(Locators_DeviceStability.now_Text, 15);
				if (isElementExist(Locators_DeviceStability.now_Text)
						|| isElementExist(Locators_DeviceStability.justnow_Text)
						|| isElementExist(Locators_DeviceStability.not_Sent_Text)
						|| isElementExist(Locators_DeviceStability.sending_Txt)
						|| isElementExist(Locators_DeviceStability.sended_SMS)) {

					APP_LOGS.info("Message sent Succeccfully");
					test.log(LogStatus.PASS, "Test case status is Passed at iteration : " + n);
					soft.assertTrue(true, "Test case status is Passed at iteration :  " + n);

				} else {

					APP_LOGS.info("SMS didn't sent");
					test.log(LogStatus.FAIL, "SMS or MMS didn't Sent, Validation Failed at iteration : " + n);
					soft.fail();
				}
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
				minWait();
			} else if (p_b_No.contains("-15.")) {
				wait(Locators_DeviceStability.Delivered, 10);
				if (isElementExist(Locators_DeviceStability.Delivered)
						|| isElementExist(Locators_DeviceStability.Delivered)
						|| isElementExist(Locators_DeviceStability.not_Sent_Text)) {
					check = true;
					APP_LOGS.info("Message sent Succeccfully");
					System.out.println("Sent");

					test.log(LogStatus.PASS, "SMS Sent Successfully at iteration ");
					soft.assertTrue(check, "SMS Sent Successfully at iteration ");
					APP_LOGS.info("SMS Sent Successfully at iteration ");
				} else {
					APP_LOGS.info("SMS didn't sent");
					soft.fail();
					test.log(LogStatus.FAIL, "Message didn't sent at iteration ");
				}
			} else if (p_b_No.contains("-29.") || p_b_No.contains("-26.") || p_b_No.contains("-11.")) {

				wait(Locators_DeviceStability.now_sms1, 15);
				if (isElementExist(Locators_DeviceStability.now_sms1)
						|| isElementExist(Locators_DeviceStability.now_sms3)
						|| isElementExist(Locators_DeviceStability.now_sms)
						|| isElementExist(Locators_DeviceStability.Delivered)
						|| isElementExist(Locators_DeviceStability.now_sms1)) {
					APP_LOGS.info("Message sent Succeccfully");
					System.out.println("Sent");
					soft.assertTrue(true, "TestCase Valiation is PASS");
					test.log(LogStatus.PASS, "SMS Sent Successfully at iteration ");
				} else {
					APP_LOGS.info("SMS didn't sent");
					soft.fail();
					test.log(LogStatus.FAIL, "Message didn't sent at iteration ");
				}

			}

		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->validate_SentMessage()");
			e.printStackTrace();

		} catch (Exception e) {

			test.log(LogStatus.ERROR, "Exeption in ->validate_SentMessage()");
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
			if (isElementExist(Locators_DeviceStability.zero_Characters_FirstPage)) {
				System.out.println("Im out");
				charAndPageNum1 = charAndPageNum.getText();
			} else if (isElementExist(Locators_DeviceStability.zero_Characters_FirstPage1)) {
				System.out.println("Im in");
				charAndPageNum1 = Locators_DeviceStability.zero_Characters_FirstPage1.getText();
			}

			if (check = charAndPageNum1.equals(expectedcharAndPageNum)) {
				APP_LOGS.info("Character and Page Number Displayed is correct");
				sf.assertTrue(check, "Validation is Pass");
			} else {
				APP_LOGS.info("Character and Page Number Displayed is NOT correct");
				sf.fail();
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->validate_CharacterAndPageNumber()");
			e.printStackTrace();

		} catch (Exception e) {

			test.log(LogStatus.ERROR, "Exeption in ->validate_CharacterAndPageNumber()");
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
			if (isElementExist(Locators_DeviceStability.zero_Characters_FirstPage_O)) {
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
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->validate_CharacterAndPageNumber_O()");
			e.printStackTrace();

		} catch (Exception e) {

			test.log(LogStatus.ERROR, "Exeption in ->validate_CharacterAndPageNumber_O()");
		}
		sf.assertAll();
	}

	public void navigateAttachOthers() throws InterruptedException {
		/* Navigates to */
		try {
			minWait();
			clickBtn(Locators_DeviceStability.attach_icon_O);
			minWait();
			System.out.println("Clicked Attachment");
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->navigateAttachOthers()");
			e.printStackTrace();

		} catch (Exception e) {

			test.log(LogStatus.ERROR, "Exeption in ->navigateAttachOthers()");
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
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->clickOnAttach_Options()");
			e.printStackTrace();

		} catch (Exception e) {

			test.log(LogStatus.ERROR, "Exeption in ->clickOnAttach_Options()");
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
		try {
			minWait();
			scrollToText("Network & Internet");
			minWait();
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->clickOn_Networks_and_Internet()");
			e.printStackTrace();

		} catch (Exception e) {

			test.log(LogStatus.ERROR, "Exeption in ->clickOn_Networks_and_Internet()");
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
			if (!isElementExist(Locators_DeviceStability.turnOff_Airplane_PopUp)) {
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
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->dailCallUsingDailPad()");
			e.printStackTrace();

		} catch (Exception e) {

			test.log(LogStatus.ERROR, "Exeption in ->dailCallUsingDailPad()");
		}
	}

	public void dailNumber(String dailNum) throws IOException {
		try {
			minWait();
			clickBtn(Locators_DeviceStability.add_Call);
			minWait();
			clickBtn(Locators_DeviceStability.dailerPad);
			minWait();
			clickBtn(Locators_DeviceStability.enterNumFiled);
			minWait();
			enterTextToInputField(Locators_DeviceStability.enterNumFiled, dailNum);
			minWait();
			// Runtime.getRuntime().exec("adb -s "+p_Id+" shell input text "+dailNum);
			minWait();
			clickBtn(Locators_DeviceStability.dail);
			customWait(4000);
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->dailNumber()");
			e.printStackTrace();

		} catch (Exception e) {

			test.log(LogStatus.ERROR, "Exeption in ->dailNumber()");
		}
	}

	public void validate_Airplane_Enable(String dailNum, SoftAssert soft, int n)
			throws InterruptedException, IOException {
		/*
		 * Method can be Used Validate Airplane Mode activation via by making the call.
		 */
		try {
			customWait(2000);
			if (isElementExist(Locators_DeviceStability.turnOff_Airplane_PopUp)) {
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
			clickBtn(Locators_DeviceStability.OK);
			minWait();
			clickBtn(Locators_DeviceStability.CANCEL);
			minWait();
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->validate_Airplane_Enable()");
			e.printStackTrace();

		} catch (Exception e) {

			test.log(LogStatus.ERROR, "Exeption in ->validate_Airplane_Enable()");
		}
	}

	public void validate_Airplane_Disable(String dailNum, SoftAssert soft, int n)
			throws InterruptedException, IOException {
		/*
		 * Method can be Used Validate Airplane Mode activation via by making the call.
		 */
		try {
			minWait();
			if (!isElementExist(Locators_DeviceStability.turnOff_Airplane_PopUp)) {
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
			// clickBtn(Locators_DeviceStability.OK);
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
			if (isElementExist(Locators_DeviceStability.end_Call)) {
				clickBtn(Locators_DeviceStability.end_Call);
				APP_LOGS.info("Call Ended");
			} else {
				APP_LOGS.info("End Call Button Doesn't exist on screen");
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->end_Call()");
			e.printStackTrace();

		} catch (Exception e) {

			test.log(LogStatus.ERROR, "Exeption in ->end_Call()");
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
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->enable_BT_RefDevice()");
			e.printStackTrace();

		} catch (Exception e) {

			test.log(LogStatus.ERROR, "Exeption in ->enable_BT_RefDevice()");
		}
	}

	public void validate_BT_Devices(SoftAssert soft, int n) throws InterruptedException, IOException {
		try {
			minWait();
			Runtime.getRuntime().exec("adb -s " + r_Id + " shell am start -a android.settings.BLUETOOTH_SETTINGS");
			customWait(6000);
			System.out.println(" Im Searching...BT devices");
			WebDriverWait wait = new WebDriverWait(aDriver, 30);
			// scrollToElement(Locators_DeviceStability.BT_Devices);
			wait.until(ExpectedConditions.visibilityOf(Locators_DeviceStability.BT_Devices));
			minWait();
			boolean check = Locators_DeviceStability.BT_Devices.isDisplayed();
			minWait();
			if (check) {
				check = true;
				soft.assertTrue(check, "TestCase Valiation is PASS");
				test.log(LogStatus.PASS, "Enable Bluethooth is Validated at iteration: " + n);
			} else {
				soft.fail();
				test.log(LogStatus.FAIL, "Enable Bluethooth is UnSuccessful at iteration: " + n);
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->validate_BT_Devices()");
			e.printStackTrace();

		} catch (Exception e) {

			test.log(LogStatus.ERROR, "Exeption in ->validate_BT_Devices()");
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
			System.out.println(check);
			return check;
		} catch (NoSuchElementException e) {
			return check;
		}
	}

	public void scrollToElementWithDpadDown(WebElement element) {
		/*
		 * Clicks up button till element is available
		 */
		try {
			System.out.println("Clicking Element1");
			if (isElementExist(element)) {
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
				System.out.println("Clicking Element");
				element.click();
			} else {
				System.out.println("Clicking First Element");
				clickBtn(Locators_DeviceStability.firstApp_Suggetion);
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->scrollToElementWithDpadDown()");
			e.printStackTrace();

		} catch (Exception e) {

			test.log(LogStatus.ERROR, "Exeption in ->scrollToElementWithDpadDown()");
		}
	}

	public boolean scrollToText(String text) {
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
		} catch (NoSuchElementException e) {
			return check;
		}
	}

	public void clickOn_BackBtn() throws InterruptedException {
		try {
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			minWait();
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->clickOn_BackBtn()");
			e.printStackTrace();

		} catch (Exception e) {

			test.log(LogStatus.ERROR, "Exeption in ->clickOn_BackBtn()");
		}
	}

	public void checkWifiConnected() throws InterruptedException {
		try {
			customWait(4000);
			if (isElementExist(Locators_DeviceStability.connectedwifi)
					|| isElementExist(Locators_DeviceStability.connectedWIFI1)) {
				String getTxt = Locators_DeviceStability.connectedWIFI1.getText();
				System.out.println(getTxt);

				test.log(LogStatus.INFO, "Wi-Fi is Connected : will be disconnecting");
				disconnectSSIDifConnected();
			} else {
				System.out.println("Not Connected");
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->checkWifiConnected()");
			e.printStackTrace();

		} catch (Exception e) {

			test.log(LogStatus.ERROR, "Exeption in ->checkWifiConnected()");
		}
	}

	public void validate_And_BrowseIn_Chrome(String url, AndroidElement autoSuggestion, AndroidElement expectedElement,
			AndroidElement expectedElement1, int n, SoftAssert soft) throws InterruptedException, IOException {
		/*
		 * Validates MobileData enabality by launching the Chrome & browsing google
		 * page.
		 */

		try {
			customWait(3000);
			Runtime.getRuntime()
					.exec("adb -s " + p_Id + " shell am start -a android.intent.action.VIEW -d http:/" + url);
			customWait(3000);
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_ENTER);
			minWait();
			customWait(1200);

			try {
				if (expectedElement.isDisplayed()) {
					check = true;
					APP_LOGS.info("Suggeted URL Page Displayed Succeessfully");
					soft.assertTrue(check, "TestCase Valiation is PASS");
					test.log(LogStatus.PASS, "WiFi Enabled and URL loaded successfully at iteration " + n);
				} else {
					APP_LOGS.info("Suggeted URL page didn't get Loaded");
					test.log(LogStatus.FAIL, "Test Case Status is Fail at iteration " + n);
					test.log(LogStatus.INFO, "URL didn't load.");
					soft.fail();
				}
			} catch (Exception e) {
				if (expectedElement1.isDisplayed()) {
					check = true;
					APP_LOGS.info("Suggeted URL Page Displayed Succeessfully");
					test.log(LogStatus.PASS, "URL loaded successfully at iteration " + n);

				} else {
					APP_LOGS.info("Suggeted URL page didn't get Loaded");
					test.log(LogStatus.FAIL, "Test Case Status is Fail at iteration " + n);
					soft.fail();
					test.log(LogStatus.INFO, "URL didn't load.");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			soft.fail();
		}
		// closeAllTabs();
	}

	public void ON_Switch(String switch_To_ON) {
		try {
			minWait();
			aDriver.findElementByXPath("//*[contains(@text,'" + switch_To_ON + "')]/../..//*[@text='OFF']").click();
			minWait();
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->ON_Switch()");
			e.printStackTrace();

		} catch (Exception e) {

			test.log(LogStatus.ERROR, "Exeption in ->ON_Switch()");
		}
	}

	public void OFF_Switch(String switch_To_OFF) {
		try {
			minWait();
			aDriver.findElementByXPath("//*[contains(@text,'" + switch_To_OFF + "')]/../..//*[@text='ON']").click();
			minWait();
			Runtime.getRuntime().exec("adb shell input tap 880 1269");
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->OFF_Switch()");
			e.printStackTrace();

		} catch (Exception e) {

			test.log(LogStatus.ERROR, "Exeption in ->OFF_Switch()");
		}
	}

	public void OFF_Switch_S(String switch_To_OFF) {
		try {
			minWait();
			AndroidElement dataon = aDriver
					.findElementByXPath("//*[contains(@text,'" + switch_To_OFF + "')]/../..//*[@text='ON']");
			if (isElementExist(dataon)) {
				dataon.click();
			}
			minWait();
//			clickBtn(Locators_DeviceStability.OK);
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->OFF_Switch()");
			e.printStackTrace();

		} catch (Exception e) {

			test.log(LogStatus.ERROR, "Exeption in ->OFF_Switch()");
		}
	}

	public void wiFi_OFF() {
		try {
			minWait();
			aDriver.findElementByXPath("//*[contains(@text,'Wi')]/../../..//*[@text='ON']").click();
			minWait();
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->wiFi_OFF()");
			e.printStackTrace();

		} catch (Exception e) {

			test.log(LogStatus.ERROR, "Exeption in ->wiFi_OFF()");
		}
	}

	public void wiFi_ON() {
		try {
			minWait();
			aDriver.findElementByXPath("//*[contains(@text,'Wi')]/../../..//*[@text='OFF']").click();
			minWait();
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->wiFi_ON()");
			e.printStackTrace();

		} catch (Exception e) {

			test.log(LogStatus.ERROR, "Exeption in ->wiFi_ON()");
		}
	}

	public void closeAllTabs() throws InterruptedException {
		/* To close all tha Tabs in Chrome Browser. */
		try {
			minWait();
			clickBtn(Locators_DeviceStability.switcherButton_Chrome);
			minWait();
			clickBtn(Locators_DeviceStability.menuButton_Chrome);
			minWait();
			clickBtn(Locators_DeviceStability.closeAllTabs_Chrome);
			minWait();
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->closeAllTabs()");
			e.printStackTrace();

		} catch (Exception e) {

			test.log(LogStatus.ERROR, "Exeption in ->closeAllTabs()");
		}
	}

	public void validate_MobileData_Disable(int n, String typ, SoftAssert soft)
			throws InterruptedException, IOException {
		/*
		 * Validates MobileData disabality by launching the Chrome.
		 */
		customWait(3000);
		if (isElementExist(Locators_DeviceStability.alert_OKBtn)) {
			clickBtn(Locators_DeviceStability.alert_OKBtn);
			System.out.println("Im Clicking Ok First ");
		}
		String url = "www.google.co.in";
		customWait(2000);
		Runtime.getRuntime().exec("adb -s " + p_Id + " shell am start -a android.intent.action.VIEW -d http:/" + url);
		customWait(3000);
		aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_ENTER);
		minWait();
		customWait(12000);
		customWait(9000);
		System.out.println("Im in validation");
		try {
			customWait(3000);
			if (!isElementExist(Locators_DeviceStability.google_Logo)
					|| !isElementExist(Locators_DeviceStability.google_Logo)) {
				check = true;
				APP_LOGS.info("MobbileData Disabled Successfully");
				soft.assertTrue(check, "TestCase Valiation is PASS");
				test.log(LogStatus.PASS, typ + " Disable validation is PASS at iteration " + n);
			} else {
				APP_LOGS.info("Mobiledata is NOT Disabled");
				test.log(LogStatus.FAIL, typ + " Disable validation is FAIL at iteration " + n);
				soft.fail();
			}
			customWait(3000);
			if (isElementExist(Locators_DeviceStability.alert_OKBtn)) {
				clickBtn(Locators_DeviceStability.alert_OKBtn);
				System.out.println("Im Clicking Ok 2nd ");
			}
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			minWait();
		} catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.FAIL, "No Element found");
			soft.fail();
		}
		// closeAllTabs();
	}

	public void clearChromePermission() {
		try {
			System.out.println("In Browser");

			if (isElementExist(Locators_DeviceStability.OK)) {
				clickBtn(Locators_DeviceStability.OK);
			}
			if (isElementExist(Locators_DeviceStability.Accept_id)) {
				clickBtn(Locators_DeviceStability.Accept_id);
			}
			if (isElementExist(Locators_DeviceStability.NEXT)) {
				clickBtn(Locators_DeviceStability.NEXT);
			}
			if (isElementExist(Locators_DeviceStability.NO_THANKS)) {
				clickBtn(Locators_DeviceStability.NO_THANKS);
			}
			if (isElementExist(Locators_DeviceStability.No_ThanKS)) {
				clickBtn(Locators_DeviceStability.No_ThanKS);
			}
			if (isElementExist(Locators_DeviceStability.No_thanKS)) {
				clickBtn(Locators_DeviceStability.No_thanKS);
			}
			if (isElementExist(Locators_DeviceStability.Cancel)) {
				clickBtn(Locators_DeviceStability.Cancel);
			}

		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->clearChromePermission()");
			e.printStackTrace();

		} catch (Exception e) {

			test.log(LogStatus.ERROR, "Exeption in ->clearChromePermission()");
		}
	}

	public void clearChromePermission_vzw() {
		try {
			System.out.println("In Browser");

			if (isElementExist(Locators_DeviceStability.OK)) {
				clickBtn(Locators_DeviceStability.OK);
				minWait();
			}
			if (isElementExist(Locators_DeviceStability.No_ThanKS)) {
				clickBtn(Locators_DeviceStability.No_ThanKS);
			}
			if (isElementExist(Locators_DeviceStability.OK)) {
				clickBtn(Locators_DeviceStability.OK);
				minWait();
			}
			if (isElementExist(Locators_DeviceStability.Cancel)) {
				clickBtn(Locators_DeviceStability.Cancel);
			}
			if (isElementExist(Locators_DeviceStability.OK)) {
				clickBtn(Locators_DeviceStability.OK);

			}
			if (isElementExist(Locators_DeviceStability.Accept_id)) {
				clickBtn(Locators_DeviceStability.Accept_id);
			}
			if (isElementExist(Locators_DeviceStability.OK)) {
				clickBtn(Locators_DeviceStability.OK);

			}
			if (isElementExist(Locators_DeviceStability.NEXT)) {
				clickBtn(Locators_DeviceStability.NEXT);
			}
			if (isElementExist(Locators_DeviceStability.OK)) {
				clickBtn(Locators_DeviceStability.OK);

			}
			if (isElementExist(Locators_DeviceStability.NEXTBTN)) {
				clickBtn(Locators_DeviceStability.NEXTBTN);
			}
			if (isElementExist(Locators_DeviceStability.OK)) {
				clickBtn(Locators_DeviceStability.OK);

			}
			if (isElementExist(Locators_DeviceStability.NO_THANKS)) {
				clickBtn(Locators_DeviceStability.NO_THANKS);
			}
			if (isElementExist(Locators_DeviceStability.OK)) {
				clickBtn(Locators_DeviceStability.OK);

			}
			if (isElementExist(Locators_DeviceStability.No_ThanKS)) {
				clickBtn(Locators_DeviceStability.No_ThanKS);
			}
			if (isElementExist(Locators_DeviceStability.OK)) {
				clickBtn(Locators_DeviceStability.OK);
			}

		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->clearChromePermission()");
			e.printStackTrace();

		} catch (Exception e) {

			test.log(LogStatus.ERROR, "Exeption in ->clearChromePermission()");
		}
	}

	public void add_Picture_O() throws InterruptedException {

		try {
			if (p_b_No.contains("-10.") || p_b_No.contains("-30.")) {
				clickAttachment();
				minWait();
				clickBtn(Locators_DeviceStability.capturePicture_MMS);
				minWait();
				clearAllow();
				clickBtn(multi_Loc_Strategy(Locators_DeviceStability.capturePicture,
						Locators_DeviceStability.capturePicture2, Locators_DeviceStability.capturePicture3, null, null,
						0, 0));
				customWait(5000);
				clickBtn(Locators_DeviceStability.done_1);
				minWait();
			} else if (p_b_No.contains("-11.") || p_b_No.contains("-12.") || p_b_No.contains("-18.")
					|| p_b_No.contains("-26.") || p_b_No.contains("-29.")) {
				clickAttachment();
				minWait();
				clickBtn(Locators_DeviceStability.capture_pictures_or_video);
				minWait();
				clearAllow();
				clickBtn(Locators_DeviceStability.capture_pictures_or_video);
				minWait();
				clickBtn(Locators_DeviceStability.take_picture_MMS);
				customWait(10000);
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->add_Picture_O()");
			e.printStackTrace();

		} catch (Exception e) {

			test.log(LogStatus.ERROR, "Exeption in ->add_Picture_O()");
		}
	}

	public void clickAttachment() throws InterruptedException {
		try {
			minWait();
			clickBtn(Locators_DeviceStability.attachment);
			minWait();
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->clickAttachment()");
			e.printStackTrace();

		} catch (Exception e) {

			test.log(LogStatus.ERROR, "Exeption in ->clickAttachment()");
		}
	}

	public void click_On_Camera() {
		try {
			System.out.println("camrera");
			clickBtn(Locators_DeviceStability.camera_sl);
			clickBtn(Locators_DeviceStability.camera_shutter_sl);
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->click_On_Camera()");
			e.printStackTrace();

		} catch (Exception e) {

			test.log(LogStatus.ERROR, "Exeption in ->click_On_Camera()");
		}
	}

	public void capturePic_MMS_O() {
		try {
			minWait();
			clickBtn(Locators_DeviceStability.button_Attach_SMS);
			minWait();
			clickBtn(Locators_DeviceStability.camera_MMS);
			customWait(2000);
			clickBtn(Locators_DeviceStability.OptionPhoto_Icon);
			customWait(2000);
			clickBtn(Locators_DeviceStability.cameraCapture_Icon);
			minWait();
			clickBtn(Locators_DeviceStability.send_Cam_SMS);
			customWait(3000);
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->capturePic_MMS_O()");
			e.printStackTrace();

		} catch (Exception e) {

			test.log(LogStatus.ERROR, "Exeption in ->capturePic_MMS_O()");
		}

	}

	public void captureVideo_MMS_O() {
		try {
			minWait();
			clickBtn(Locators_DeviceStability.button_Attach_SMS);
			minWait();
			clickBtn(Locators_DeviceStability.camera_MMS);
			customWait(2000);
			clickBtn(Locators_DeviceStability.OptionVideo_Icon);
			customWait(2000);
			TouchAction action = new TouchAction(aDriver);
			action.longPress(Locators_DeviceStability.cameraCapture_Icon).release().perform();
			// clickBtn(Locators_DeviceStability.cameraCapture_Icon);
			minWait();
			clickBtn(Locators_DeviceStability.send_Cam_SMS);
			customWait(3000);
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->captureVideo_MMS_O()");
			e.printStackTrace();

		} catch (Exception e) {

			test.log(LogStatus.ERROR, "Exeption in ->captureVideo_MMS_O()");
		}
	}

	public void remove_GoogleAcccount_Orio() {
		// remove added google Account if any
		try {
			scrollToText("Users & accounts");
			// clickOnAccounts();
			minWait();
			if (isElementExist(Locators_DeviceStability.connectedAccount)) {
				System.out.println("Account is present");
				minWait();
				clickBtn(Locators_DeviceStability.connectedAccount);
				minWait();
				clickBtn(Locators_DeviceStability.REMOVE_ACCOUNT);
				minWait();
				clickBtn(Locators_DeviceStability.REMOVE_ACCOUNT);
				customWait(3000);
			} else {
				System.out.println("No Google account present");
			}

		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->remove_GoogleAcccount_Orio()");
			e.printStackTrace();

		} catch (Exception e) {

			test.log(LogStatus.ERROR, "Exeption in ->remove_GoogleAcccount_Orio()");
		}
	}

	public void navigateTo_AddGoogleAccount_Orio() {
		// navigate to settings option Add google Account
		WebDriverWait wt = new WebDriverWait(aDriver, 60);
		try {
			scrollToText("Users & accounts");
			clickBtn(Locators_DeviceStability.add_Account);
			minWait();
			clickBtn(Locators_DeviceStability.google_Account);
			customWait(1600);
			wt.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[contains(@text,'Checking info')]")));
			minWait();
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->navigateTo_AddGoogleAccount_Orio()");
			e.printStackTrace();

		} catch (Exception e) {

			test.log(LogStatus.ERROR, "Exeption in ->navigateTo_AddGoogleAccount_Orio()");
		}
	}

	public void validate_Locators1(AndroidElement e1, SoftAssert soft, int n) throws InterruptedException {
		minWait();
		boolean check1 = false;
		try {
			check1 = e1.isDisplayed();
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->validate_Locators1()");
			e.printStackTrace();

		} catch (Exception e) {

			test.log(LogStatus.ERROR, "Exeption in ->validate_Locators1()");
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
			enterTextToInputField(Locators_DeviceStability.email_googleAcnt, emailId);
			minWait();
			clickBtn(Locators_DeviceStability.next);
			minWait();
			wt.until(ExpectedConditions
					.visibilityOfElementLocated(By.xpath("//*[@text='Welcome' or @resource-id='profileIdentifier']")));
			minWait();
			enterTextToInputField(Locators_DeviceStability.password_googleAcnt, password);
			customWait(5000);
			clickBtn(Locators_DeviceStability.next);
			customWait(3000);
			scroll();
			scroll();
			minWait();
			clickBtn(Locators_DeviceStability.skip_);
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
				} catch (org.openqa.selenium.NoSuchElementException e) {

					test.log(LogStatus.ERROR, "Error in the locators->add_GoogleAccount()");
					e.printStackTrace();

				} catch (Exception e) {

					test.log(LogStatus.ERROR, "Exeption in ->add_GoogleAccount()");
				}
			}
			customWait(1000);
			clickBtn(Locators_DeviceStability.i_agree);
			minWait();
			wt.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[contains(@text,'Checking info')]")));
			minWait();
			clickBtn(Locators_DeviceStability.MORE);
			minWait();
			clickBtn(Locators_DeviceStability.ACCEPTorAGREE);
			customWait(3000);
		} catch (Exception e) {
		}
	}

	public void checkWifiConnected1() throws InterruptedException {
		try {
			customWait(4000);
			if (isElementExist(Locators_DeviceStability.connectedWIFI1)) {
				String getTxt = Locators_DeviceStability.connectedWIFI1.getText();
				System.out.println("Connected " + getTxt);
				// test.log(LogStatus.INFO, "Wi-Fi is Connected");
			} else {
				System.out.println("Not Connected");
				selectSSIDwifi();
				// enterPassword();
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->checkWifiConnected1()");
			e.printStackTrace();

		} catch (Exception e) {

			test.log(LogStatus.ERROR, "Exeption in ->checkWifiConnected1()");
		}
	}

	public void install_App(String appName, WebElement element) throws InterruptedException, IOException {

		if (isElementExist(Locators_DeviceStability.account_Page)) {
			clearRecentApps();
			launch_APP(Locators_DeviceStability.PlayStore);
		}

		WebDriverWait wait = new WebDriverWait(aDriver, 60);
		try {
			System.out.println("To Be install " + appName);
			customWait(3000);
			clickBtn(Locators_DeviceStability.google_Play);
			customWait(3000);
			enterTextToInputField(Locators_DeviceStability.search_PlayStore, appName);
			customWait(3000);
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_ENTER);
			customWait(8000);
			System.out.println("Scrolling");
			scrollToElementWithDpadDown(element);
			minWait();

			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@text='INSTALL']")));
			customWait(6000);
			if (isElementExist(Locators_DeviceStability.INSTALL)) {
				minWait();
				clickBtn(Locators_DeviceStability.INSTALL);
				minWait();
				clickBtn(Locators_DeviceStability.ACCEPT);
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
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->install_App()");
			e.printStackTrace();

		} catch (Exception e) {

			test.log(LogStatus.ERROR, "Exeption in ->install_App()");
		}
	}

	// public SoftAssert sf42 = new SoftAssert();
	public void validate_Installed_App(String appName, int n, SoftAssert soft) {

		try {
			boolean check = false;
			clickOnAppList();
			enterTextToInputField(Locators_DeviceStability.searchApps, appName);
			minWait();
			if (isElementExist(Locators_DeviceStability.apkExtractor)) {
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

		enterTextToInputField(Locators_DeviceStability.searchApps, appName);
		minWait();
		System.out.println("Checking....");
		if (isElementExist(Locators_DeviceStability.apkExtractor)) {
			System.out.println("Yes App is Present...");
			if (appName.contains("Tech Support")) {
				System.out.println("Im in Staples");
				launch_APP(Locators_DeviceStability.PlayStore);
				unInstall_App("Tech Support Staples");

			} else if (appName.contains("Meeting App")) {
				System.out.println("Im in MAcys");
				launch_APP(Locators_DeviceStability.PlayStore);
				unInstall_App("Macys");
			}

			else if (appName.contains("MCDelivery App")) {
				System.out.println("MCDelivery");
				launch_APP(Locators_DeviceStability.PlayStore);
				unInstall_App("McDonald's");
			}

			else if (appName.contains("Craigslist")) {
				System.out.println("App for Craigslist");
				launch_APP(Locators_DeviceStability.PlayStore);
				unInstall_App("App for Craigslist");
			}

			else {
				System.out.println("Im in Gen");
				launch_APP(Locators_DeviceStability.PlayStore);
				unInstall_App(appName);
			}
			System.out.println("Uninstalled");
		}
	}

	public void unInstall_App(String appName) {

		WebDriverWait wait = new WebDriverWait(aDriver, 60);
		try {
			minWait();
			clickBtn(Locators_DeviceStability.google_Play);
			minWait();
			enterTextToInputField(Locators_DeviceStability.search_PlayStore, appName);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_ENTER);
			customWait(5000);
			if (isElementExist(Locators_DeviceStability.installed_Playstore1)) {
				customWait(3000);
				clickBtn(Locators_DeviceStability.installed_Playstore1);
			}
			if (isElementExist(Locators_DeviceStability.UNINSTALL)) {
				System.out.println("Uninstalling");
				minWait();
				clickBtn(Locators_DeviceStability.UNINSTALL);
				minWait();
				clickBtn(Locators_DeviceStability.OK);
				minWait();
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@text='INSTALL']")));
				minWait();
			} else if (isElementExist(Locators_DeviceStability.UNINSTALL1)) {
				System.out.println("Uninstalling....");
				minWait();
				clickBtn(Locators_DeviceStability.UNINSTALL1);
				minWait();
				clickBtn(Locators_DeviceStability.OK);
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
			enterTextToInputField(Locators_DeviceStability.searchApps, appName);
			minWait();
			if (isElementExist(Locators_DeviceStability.apkExtractor)) {
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
			clickBtn(Locators_DeviceStability.add_ContactIcon_O);
			minWait();
			clickBtn(Locators_DeviceStability.contact_SavingTo);
			minWait();
			clickBtn(Locators_DeviceStability.savingTo_Phone);
			minWait();
			// aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			minWait();
			clickBtn(Locators_DeviceStability.more_Fields);
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
			// clickBtn(Locators_DeviceStability.SAVE);
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
			clickBtn(Locators_DeviceStability.add_ContactIcon_O);
			minWait();
			// clickBtn(Locators_DeviceStability.contact_SavingTo);
			minWait();
			// clickBtn(Locators_DeviceStability.add_NewContact);
			minWait();
			if (enter1forPhone_2forSimMemory == 1) {
				clickBtn(Locators_DeviceStability.contact_SavingTo);
				minWait();
				clickBtn(Locators_DeviceStability.savingTo_Phone);
				minWait();
				enterTextToInputField(Locators_DeviceStability.name_NewContact, contactName);
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
				minWait();
				enterTextToInputField(Locators_DeviceStability.phone_NewContact, contactNum);
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
				minWait();
				//
			} else if (enter1forPhone_2forSimMemory == 2) {
				clickBtn(Locators_DeviceStability.contact_SavingTo);
				minWait();
				clickBtn(Locators_DeviceStability.savingTo_SIM);
				minWait();
				enterTextToInputField(Locators_DeviceStability.name_NewContact, contactName);
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
				minWait();
				enterTextToInputField(Locators_DeviceStability.phone_NewContact, contactNum);
				// aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
				minWait();
				// enterTextToInputField(Locators_DeviceStability.phone_NewContact, contactNum);
				// aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
				// minWait();
			}
			clickBtn(Locators_DeviceStability.save_Icon);
			minWait();
			clearAllow();
			// aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			minWait();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

//---------------------edit
	public boolean enterTextToInputField1(AndroidElement e, String text) throws InterruptedException {

		if (e.isDisplayed()) {

			e.click();
			e.clear();
			e.sendKeys(text);
			APP_LOGS.info("PASS: Element found and entered value successfully");
			return true;
		} else {
			APP_LOGS.info("FAIL: Element not found or displayed: Object Locator : " + e);
			return false;

		}
	}

	public void validate_PageIsLoaded(String type, int n, SoftAssert soft) throws InterruptedException, IOException {
		/*
		 * Validates MobileData enabality by launching the Chrome & browsing google
		 * page.
		 */

		try {
			if (isElementExist(Locators_DeviceStability.googlesign)) {
				check = true;
				APP_LOGS.info(type + "is enabled and Suggeted URL Page Displayed Succeessfully at iteration : " + n);
				soft.assertTrue(true, "is enabled and Suggeted URL Page Displayed Succeessfully at iteration : ");
				test.log(LogStatus.PASS,
						type + "is enabled and Suggeted URL Page Displayed Succeessfully at iteration : " + n);
			} else {
				APP_LOGS.info("Suggeted URL page didn't get Loaded");
				soft.fail();
				test.log(LogStatus.FAIL, "Suggeted URL page didn't get Loaded" + n);
			}
		} catch (NoSuchElementException e) {
			test.log(LogStatus.ERROR, "Error in the locators => validate_PageIsLoaded()");
			e.printStackTrace();
		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exception in functionality=> validate_PageIsLoaded()");
			e.printStackTrace();
		}
		// closeAllTabs();
	}

	public void swipeDown() {
		try {
			customWait(2000);
			Runtime.getRuntime().exec("adb -s " + p_Id + "shell input swipe 500 500 500 1600");
			System.out.println("Swiped down");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void validate_PageIsNotLoaded(int n, String typ, SoftAssert soft) throws InterruptedException, IOException {
		/*
		 * Validates MobileData disabality by launching the Chrome.
		 */
		boolean res = check;
		try {
			customWait(6000);
			if (Locators_DeviceStability.noInternet.isDisplayed()) {
				res = true;
				System.out.println("First");
			} else if (Locators_DeviceStability.YouAre_offline_lnk2.isDisplayed()) {
				res = true;
				System.out.println("Second");

			} else if (Locators_DeviceStability.YouAre_offline_lnk3.isDisplayed()) {
				res = true;
				System.out.println("Third");

			} else {
				res = false;
			}

		} catch (NoSuchElementException e) {
			test.log(LogStatus.ERROR, "Error in the locators => validate_PageIsNotLoaded()");
			e.printStackTrace();
		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exception in functionality=> validate_PageIsNotLoaded()");
			e.printStackTrace();
		}
		if (res) {
			APP_LOGS.info(typ + "is disabled and Suggeted URL Page not displayed  at iteration : " + n);
			soft.assertTrue(true, "is disabled and Suggeted URL Page not displayed  at iteration : " + n);
			test.log(LogStatus.PASS, typ + "is disabled and Suggeted URL Page not displayed  at iteration : " + n);
		} else {
			APP_LOGS.info(typ + "is enabled at iteration : " + n);
			soft.fail();
			test.log(LogStatus.FAIL, typ + "is enabled at iteration : " + n);

		}
	}

	public void validate_And_BrowseIn_Chrome1(String url) {
		// * Validates MobileData enabality by launching the Chrome & browsing google
		// page.

		try {
			customWait(3000);
			Runtime.getRuntime()
					.exec("adb -s " + p_Id + " shell am start -a android.intent.action.VIEW -d http:/" + url);
			customWait(3000);
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_ENTER);
			minWait();
			customWait(1200);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		// closeAllTabs();
	}

	public void enterURL(int n, String url) {
		try {
			customWait(3000);
			if (isElementExist(Locators_DeviceStability.alert_OKBtn)) {
				clickBtn(Locators_DeviceStability.alert_OKBtn);
				System.out.println("Im Clicking Ok First ");
			}
			if (isElementExist(Locators_DeviceStability.NO_THANKS)) {
				clickBtn(Locators_DeviceStability.NO_THANKS);
			}
			customWait(2000);
			Runtime.getRuntime()
					.exec("adb -s " + p_Id + " shell am start -a android.intent.action.VIEW -d http:/" + url);
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_ENTER);
			customWait(3000);
		} catch (NoSuchElementException e) {
			test.log(LogStatus.ERROR, "Error in the locators => enterURL()");
			e.printStackTrace();
		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exception in functionality=> enterURL()");
			e.printStackTrace();
		}

	}

	public boolean disable_MobileData() {
		/*
		 * disable data from data usage window
		 */
		boolean disabled = false;
		try {
			if (Locators_DeviceStability.mobile_data_State1.getText().equalsIgnoreCase("ON")) {
				clickBtn(multi_Loc_Strategy(Locators_DeviceStability.mobile_data_State1,
						Locators_DeviceStability.mobile_data_State, null, null, null, 0, 0));
				clickBtn(Locators_DeviceStability.OK);
				disabled = true;
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.ERROR, "Error in the locators->disable_MobileData() ");
			e.printStackTrace();
		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exception in ->disable_MobileData()");
		}
		return disabled;
	}

	public String readFile(String filename) throws IOException {
		String content = null;
		try {
			content = new String(Files.readAllBytes(Paths.get(filename)));

		} catch (Exception e) {
			e.printStackTrace();
		}
		return content;
	}

	public void validate_And_BrowseIn_Chrome(int n, SoftAssert soft) throws InterruptedException, IOException {

		try {

			customWait(2000);
			if (isElementExist(Locators_DeviceStability.google_savedDataOpt)) {

				clickBtn(Locators_DeviceStability.google_savedDataOpt_OkBtn);
			}
			minWait();
			String url = "https://www.google.com";
wait(Locators_DeviceStability.chromeSearch, 20);			
			enterTextToInputField(Locators_DeviceStability.chromeSearch, url);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_ENTER);

			boolean noInternet = wait(Locators_DeviceStability.noInternet, 20);
			if (!noInternet) {
				APP_LOGS.info("WiFi Enabled and URL loaded successfully at iteration " + n);
				soft.assertTrue(true, "WiFi Enabled and URL loaded successfully at iteration " + n);
				test.log(LogStatus.PASS, "WiFi Enabled and URL loaded successfully at iteration " + n);
			} else {
				APP_LOGS.info("Suggeted URL page didn't get Loaded");
				test.log(LogStatus.FAIL, "Test Case Status is Fail at iteration " + n);

				soft.fail();
			}

		}

		catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.ERROR, "Error in locators->validate_And_BrowseIn_Chrome()");
			e.printStackTrace();

		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exeption in ->validate_And_BrowseIn_Chrome()");
			e.printStackTrace();

		}
	}

	public void validate_Page_IsNotLoaded_InChrome(int n, SoftAssert soft) throws InterruptedException, IOException {

		try {

			customWait(2000);
			if (isElementExist(Locators_DeviceStability.google_savedDataOpt)) {

				clickBtn(Locators_DeviceStability.google_savedDataOpt_OkBtn);
			}
			minWait();
			String url = "https://www.facebook.com";
			wait(Locators_DeviceStability.chromeSearch, 20);
			enterTextToInputField(Locators_DeviceStability.chromeSearch, url);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_ENTER);
//clickBtn(multi_Loc_Strategy(Locators_DeviceStability.OK, Locators_DeviceStability.OKBtn1, Locators_DeviceStability.OKBtn2, Locators_DeviceStability.OKBtn3, Locators_DeviceStability.OKBtnopt1, 889, 1104));
			if (isElementExist(Locators_DeviceStability.google_savedDataOpt)) {

				clickBtn(Locators_DeviceStability.google_savedDataOpt_OkBtn);
			}
			boolean noInternet = wait(Locators_DeviceStability.noInternet,20);
			if (noInternet) {
				APP_LOGS.info("Wifi is disabled successfully and page is not loaded");
				test.log(LogStatus.PASS, "Wifi is disabled successfully and page is not loaded" + n);
				soft.assertTrue(true, "Wifi is disabled successfully and page is not loaded" + n);

			} else {
				APP_LOGS.info("WiFi is not disabled " + n);
				test.log(LogStatus.FAIL, "WiFi is not disabled " + n);
				soft.fail();
			}

		}

		catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in locators->validate_Page_IsNotLoaded_InChrome()");
		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exeption in ->validate_Page_IsNotLoaded_InChrome()");
		}
	}
	public void validate_Page_IsNotLoaded_InChrome_A(int n, SoftAssert soft) throws InterruptedException, IOException {

		try {
			
			if (Locators_DeviceStability.noInternet.isDisplayed()) {
				APP_LOGS.info("Wifi is disabled successfully and page is not loaded");
				test.log(LogStatus.PASS, "Wifi is disabled successfully and page is not loaded" + n);
				soft.assertTrue(true, "Wifi is disabled successfully and page is not loaded" + n);

			} else {
				APP_LOGS.info("WiFi is not disabled " + n);
				test.log(LogStatus.FAIL, "WiFi is not disabled " + n);
				soft.fail();
			}

		}

		catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in locators->validate_Page_IsNotLoaded_InChrome()");
		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exeption in ->validate_Page_IsNotLoaded_InChrome()");
		}
	}
	public void enter_URL() throws InterruptedException, IOException {

		try {

			customWait(2000);
			if (isElementExist(Locators_DeviceStability.google_savedDataOpt)) {

				clickBtn(Locators_DeviceStability.google_savedDataOpt_OkBtn);
			}
			aDriver.pressKeyCode(AndroidKeyCode.BACK);

			minWait();
			
			String url = "https://www.facebook.com";
			wait(Locators_DeviceStability.chromeSearch, 20);
			enterTextToInputField(Locators_DeviceStability.chromeSearch, url);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_ENTER);
//clickBtn(multi_Loc_Strategy(Locators_DeviceStability.OK, Locators_DeviceStability.OKBtn1, Locators_DeviceStability.OKBtn2, Locators_DeviceStability.OKBtn3, Locators_DeviceStability.OKBtnopt1, 889, 1104));
			if (isElementExist(Locators_DeviceStability.google_savedDataOpt)) {

				clickBtn(Locators_DeviceStability.google_savedDataOpt_OkBtn);
			}
			boolean noInternet = wait(Locators_DeviceStability.noInternet,20);
		
		}

		catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in locators->validate_Page_IsNotLoaded_InChrome()");
		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exeption in ->validate_Page_IsNotLoaded_InChrome()");
		}
	}
	public boolean enable_Wifi() {
		boolean enabled = false;
		try {
			if (Locators_DeviceStability.wifiConnectionSate.getText().equalsIgnoreCase("off")) {
				clickBtn(Locators_DeviceStability.wifiConnectionSate);
			}
			enabled = (Locators_DeviceStability.wifiConnectionSate.getText().equalsIgnoreCase("on")) ? true : false;

			minWait();

		} catch (NoSuchElementException e) {
			test.log(LogStatus.ERROR, "Error in the locators => enable_Wifi()");
			e.printStackTrace();
		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exception in functionality=> enable_Wifi()");
			e.printStackTrace();
		}
		return enabled;
	}

	public boolean disable_Wifi() {
		boolean disable = false;
		try {
			if (Locators_DeviceStability.wifiConnectionSate.getText().equalsIgnoreCase("on")) {
				clickBtn(Locators_DeviceStability.wifiConnectionSate);
				disable = (Locators_DeviceStability.wifiConnectionSate.getText().equalsIgnoreCase("off")) ? true
						: false;
			}

		} catch (NoSuchElementException e) {
			test.log(LogStatus.ERROR, "Error in the locators => enable_Wifi()");
			e.printStackTrace();
		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exception in functionality=> enable_Wifi()");
			e.printStackTrace();
		}
		return disable;
	}

	public boolean enable_Mobiledata() {

		boolean enabled = false;
		try {
			if (Locators_DeviceStability.mobile_data_State1.getText().equalsIgnoreCase("off")) {
				clickBtn(Locators_DeviceStability.mobile_data_State1);
				enabled = true;
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.ERROR, "Error in the locators->enable_Mobiledata() ");
			e.printStackTrace();
		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exception in ->enable_Mobiledata()");
		}
		return enabled;
	}

	public void chrome_Clear() {
		try {
			Runtime.getRuntime().exec("adb -s " + p_Id + " shell pm clear com.android.chrome");
		} catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.ERROR, "Error in the locators -> chrome_Clear()");
			e.printStackTrace();
		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exeption in -> chrome_Clear()");
		}

	}

	public void clickOn_Wifi() throws InterruptedException {
		try {
			if (isElementExist(Locators_DeviceStability.Wi_Fi)) {
				clickBtn(Locators_DeviceStability.Wi_Fi);
			} else {
				scrollToElements(Locators_DeviceStability.Wi_Fi);
				clickBtn(multi_Loc_Strategy(Locators_DeviceStability.Wi_Fi, Locators_DeviceStability.wi_Fi_x2, null,
						null, null, 216, 288));
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.ERROR, "Error in the locators -> clickOn_Wifi()");
			e.printStackTrace();
		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exeption in -> clickOn_Wifi()");
		}

	}

	public void deleteall_SMS() throws InterruptedException

	{
		try {
			if (p_b_No.contains("-15.")) {

				launch_APP(Locators_DeviceStability.MessagePlus);
				while (isElementExist(Locators_DeviceStability.deleteall)) {
					if (isElementExist(Locators_DeviceStability.deleteall)) {
						TouchAction ta = new TouchAction(aDriver);
						ta.longPress(Locators_DeviceStability.deleteall).release().perform();
						clickBtn(Locators_DeviceStability.delete_conversation);
						minWait();
						clickBtn(Locators_DeviceStability.delete_Confirm_s);

					}

				}
			} else if (p_b_No.contains("-11.") || p_b_No.contains("-26.") || p_b_No.contains("-29.")) {

				launch_APP(Locators_DeviceStability.messages);
				clearSMSPermissions_O();
				while (isElementExist(Locators_DeviceStability.delete_firstmsg_b)) {
					if (isElementExist(Locators_DeviceStability.delete_firstmsg_b)) {
						TouchAction ta = new TouchAction(aDriver);
						ta.longPress(Locators_DeviceStability.delete_firstmsg_b).release().perform();
						clickBtn(Locators_DeviceStability.deletebtn_b);
						minWait();
						clickBtn(Locators_DeviceStability.delete_Confirm_s);

						minWait();
					}

				}
			}

			else if (p_b_No.contains("-10.")) {

				launch_APP(Locators_DeviceStability.Messageing);
				while (isElementExist(Locators_DeviceStability.delete_firstmsg_a)) {

					if (isElementExist(Locators_DeviceStability.delete_firstmsg_a)) {
						TouchAction ta = new TouchAction(aDriver);
						ta.longPress(Locators_DeviceStability.delete_firstmsg_a).release().perform();
						if (isElementExist(Locators_DeviceStability.select)) {
							clickBtn(Locators_DeviceStability.select);
							if (isElementExist(Locators_DeviceStability.selectall)) {
								clickBtn(Locators_DeviceStability.selectall);
								clickBtn(Locators_DeviceStability.deletesms);
								clickBtn(Locators_DeviceStability.delete_Confirm_a);
							}

							else {

								clickBtn(Locators_DeviceStability.oneselected1);
								clickBtn(Locators_DeviceStability.deletesms);
								clickBtn(Locators_DeviceStability.delete_Confirm_s);

								// longpress(Locators_DeviceStability.msg1);
							}

						}
					}
				}
			}

//	clickBtn(multi_Loc_Strategy(Locators_DeviceStability.search_btn, Locators_DeviceStability.search_btn1, Locators_DeviceStability.search_btn2, null, null, 0, 0));
//	enterTextToInputField(multi_Loc_Strategy(Locators_DeviceStability.search_all_btn, Locators_DeviceStability.search_all_btn1, Locators_DeviceStability.search_all_btn2, null, null, 0, 0), num);
//	clickBtn(multi_Loc_Strategy(Locators_DeviceStability.First_no, Locators_DeviceStability.First_no1, null, null, null, 0, 0));
//	minWait();
//	clickBtn(multi_Loc_Strategy(Locators_DeviceStability.more_opt, Locators_DeviceStability.more_opt1, Locators_DeviceStability.more_opt2, null, null, 0, 0));
//	clickBtn(multi_Loc_Strategy(Locators_DeviceStability.delete_o1, Locators_DeviceStability.delete_o1, null, null, null, 0, 0));
//	clickBtn(multi_Loc_Strategy(Locators_DeviceStability.delete_all_o, Locators_DeviceStability.delete_all_o1, null, null, null, 0, 0));
//	clickBtn(multi_Loc_Strategy(Locators_DeviceStability.delete_conf_o, Locators_DeviceStability.delete_conf_o1, Locators_DeviceStability.delete_conf_o2, null, null, 0, 0));
//	clickBtn(multi_Loc_Strategy(Locators_DeviceStability.delete_Confirm_s, Locators_DeviceStability.delete_Confirm_o1, Locators_DeviceStability.delete_Confirm_o2, null, null, 856, 1157));
//    aDriver.pressKeyCode(AndroidKeyCode.BACK);

		}

		catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.ERROR, "Error in the locators -> deleteall()");
			e.printStackTrace();
		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exeption in -> deleteall()");
		}

		aDriver.pressKeyCode(AndroidKeyCode.BACK);

	}

	public void delete_Call_From_Call_History() {
		try {
			launch_an_app("phone");
			minWait();
			if (isElementExist(Locators_DeviceStability.MoreOptions)) {
				clickBtn(
						multi_Loc_Strategy(Locators_DeviceStability.MoreOptions, Locators_DeviceStability.MoreOptions11,
								Locators_DeviceStability.MoreOptions2, null, null, 961, 158));
				minWait();
			} else {
				clickBtn(Locators_DeviceStability.MoreOptions1);
				minWait();
			}
			customWait(1000);
			clickBtn(multi_Loc_Strategy(Locators_DeviceStability.callHistory_O, Locators_DeviceStability.callHistory_O1,
					Locators_DeviceStability.callHistory_O2, null, null, 670, 176));
			minWait();
			if (isElementExist(Locators_DeviceStability.morecal)) {
				clickBtn(multi_Loc_Strategy(Locators_DeviceStability.morecal, Locators_DeviceStability.MoreOptions11,
						Locators_DeviceStability.MoreOptions2, null, null, 961, 158));
				clickBtn(multi_Loc_Strategy(Locators_DeviceStability.clear_call_history,
						Locators_DeviceStability.clear_call_history, Locators_DeviceStability.clear_call_history, null,
						null, 961, 158));

				clickBtn(multi_Loc_Strategy(Locators_DeviceStability.OKBtnopt1, Locators_DeviceStability.OKBtnopt2,
						Locators_DeviceStability.OKBtnopt3, null, null, 961, 158));

			}

		} catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.ERROR, "Error in the locators -> delete_Call_From_Call_History()");
			e.printStackTrace();
		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exeption in -> delete_Call_From_Call_History()");
		}
	}

	public void delete_contact() {
		try {
			launch_an_app("contacts");

			clickBtn(multi_Loc_Strategy(Locators_DeviceStability.deleteContactOptn2,
					Locators_DeviceStability.deleteContactOptn, Locators_DeviceStability.deleteContactOptn3,
					Locators_DeviceStability.deleteContactOptn1, null, 0, 0));
			clickBtn(multi_Loc_Strategy(Locators_DeviceStability.Selection_menu,
					Locators_DeviceStability.Selection_menu1, Locators_DeviceStability.Selection_menu2,
					Locators_DeviceStability.Selection_menu, null, 0, 0));
			clickBtn(multi_Loc_Strategy(Locators_DeviceStability.ALL_Selection_menu1,
					Locators_DeviceStability.ALL_Selection_menu1, Locators_DeviceStability.ALL_Selection_menu2, null,
					null, 0, 0));
			if (Locators_DeviceStability.OKBtn1.isEnabled()) {
				clickBtn(multi_Loc_Strategy(Locators_DeviceStability.OKBtn1, Locators_DeviceStability.OKBtnopt2,
						Locators_DeviceStability.OKBtnopt3, null, null, 961, 158));
				clickBtn(multi_Loc_Strategy(Locators_DeviceStability.OKBtnopt2, Locators_DeviceStability.OKBtnopt1,
						Locators_DeviceStability.OKBtnopt3, null, null, 961, 158));
				minWait();
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.ERROR, "Error in the locators -> delete_contact()");
			e.printStackTrace();
		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exeption in -> delete_contact()");
		}
	}

	// --------------------modify code---------------------

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
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->launch_APP()");
			e.printStackTrace();

		} catch (Exception e) {

			test.log(LogStatus.ERROR, "Exeption in ->launch_APP()");
		}

	}

	public void clearSMSPermissions() throws InterruptedException {
		try {

			if (p_b_No.contains("-10.")) {

				customWait(2000);
				if (isElementExist(Locators_DeviceStability.StartMessaging)) {
					clickBtn(Locators_DeviceStability.StartMessaging);

					minWait();
					clickBtn(Locators_DeviceStability.NEXT_Msg);
					minWait();
					clickBtn(Locators_DeviceStability.allow_Permission);
					minWait();
				}
			} else if (p_b_No.contains("-15.") || p_b_No.contains("-29.") || p_b_No.contains("-26.")
					|| p_b_No.contains("-11.")) {
				customWait(2000);
				if (isElementExist(Locators_DeviceStability.StartMessaging)) {
					clickBtn(Locators_DeviceStability.StartMessaging);
					minWait();
					clickBtn(Locators_DeviceStability.skipProvisioning);
					minWait();
				}
				if (isElementExist(Locators_DeviceStability.OKBtnopt1)) {
					clickBtn(Locators_DeviceStability.OKBtnopt1);
				}

			}

		} catch (Exception e) {
			e.getMessage();
		}
	}

	public void navigateTo_NewSMS() throws InterruptedException {
		/* Used to Navigate to New Message Page. */
		try {
			if (p_b_No.contains("-10.")) {

				clickBtn(multi_Loc_Strategy(Locators_DeviceStability.add_NewSMS, Locators_DeviceStability.add_NewSMS11,
						Locators_DeviceStability.add_NewSMS1, null, null, 924, 1824));
				minWait();
			} else if (p_b_No.contains("-15.")) {
				clickBtn(multi_Loc_Strategy(Locators_DeviceStability.add_NewSMS_O,
						Locators_DeviceStability.add_NewSMS_O1, null, null, null, 0, 0));
				minWait();
			} else if (p_b_No.contains("-29.") || p_b_No.contains("-11.") || p_b_No.contains("-26.")) {
				System.out.println("Start chat");
				clickBtn(multi_Loc_Strategy(Locators_DeviceStability.add_NewSMS_s1,
						Locators_DeviceStability.add_NewSMS_s, Locators_DeviceStability.add_NewSMS_s2,
						Locators_DeviceStability.add_NewSMS_s3, null, 0, 0));
				System.out.println("end");
				minWait();
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->navigateTo_NewSMS()");
			e.printStackTrace();

		} catch (Exception e) {

			test.log(LogStatus.ERROR, "Exeption in ->navigateTo_NewSMS()");
		}
	}

	public void enter_Num_ToField(String number) throws InterruptedException {
		/* Method is used to Enter Number into TO Field. */
		try {
			if (p_b_No.contains("-10.")) {

				enterTextToInputField(multi_Loc_Strategy(Locators_DeviceStability.TO_Field_phno1,
						Locators_DeviceStability.TO_Field_phno2, Locators_DeviceStability.TO_Field_phno3, null, null, 0,
						0), number);

				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			} else if (p_b_No.contains("-15.")) {
				minWait();
				enterTextToInputField(multi_Loc_Strategy(Locators_DeviceStability.TO_Field_O,
						Locators_DeviceStability.TO_Field_O1, null, null, null, 0, 0), number);
				customWait(2000);
				System.out.println("Selecting COntact NAme");

			} else if (p_b_No.contains("-26.") || p_b_No.contains("-29.") || p_b_No.contains("-11.")) {
				minWait();
				enterTextToInputField(multi_Loc_Strategy(Locators_DeviceStability.To_Field_B1,
						Locators_DeviceStability.To_Field_B, Locators_DeviceStability.To_Field_B2, null, null, 0, 0),
						number);
				customWait(2000);
				System.out.println("Selecting COntact NAme");
				aDriver.pressKeyCode(AndroidKeyCode.ENTER);
				aDriver.pressKeyCode(AndroidKeyCode.BACK);
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->enter_Num_ToField()");
			e.printStackTrace();

		} catch (Exception e) {

			test.log(LogStatus.ERROR, "Exeption in ->enter_Num_ToField()");
		}
	}

	public void enterText_MessageField(String message) throws InterruptedException {
		/* Methos is Used enter Message into Message Field. */
		try {

			if (p_b_No.contains("-10.")) {

				customWait(2000);
				enterTextToInputField(multi_Loc_Strategy(Locators_DeviceStability.type_text1,
						Locators_DeviceStability.type_text2, Locators_DeviceStability.type_text3, null, null, 0, 0),
						message);
				minWait();
			} else if (p_b_No.contains("-15")) {
				minWait();

				enterTextToInputField(multi_Loc_Strategy(Locators_DeviceStability.messageField_O,
						Locators_DeviceStability.messageField_O1, Locators_DeviceStability.messageField_O2, null, null,
						0, 0), message);
				minWait();
			} else if (p_b_No.contains("-29") || p_b_No.contains("-26") || p_b_No.contains("-11")) {
				minWait();
				if (isElementExist(Locators_DeviceStability.Text_Field_B)) {
					enterTextToInputField(Locators_DeviceStability.Text_Field_B, message);
					minWait();
				}
			} else {

			}

		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->enterText_MessageField()");
			e.printStackTrace();

		} catch (Exception e) {

			test.log(LogStatus.ERROR, "Exeption in ->enterText_MessageField()");
		}
	}

	public void clickOn_Send() throws InterruptedException {
		/* Method used to click on Send Button. */
		try {
			if (p_b_No.contains("-10.")) {
				if (isElementExist(Locators_DeviceStability.send_Icon)) {
					clickBtn(multi_Loc_Strategy(Locators_DeviceStability.send_Icon, Locators_DeviceStability.send_Icon2,
							Locators_DeviceStability.send_Icon3, null, null, 990, 1024));
					APP_LOGS.info("Send icon");
					minWait();
				} else if (isElementExist(Locators_DeviceStability.send_SMS)) {
					clickBtn(Locators_DeviceStability.send_SMS);
					APP_LOGS.info("Send icon");
					minWait();
				} else {
					clickBtn(Locators_DeviceStability.send_MMS_Icon);
					minWait();
				}
			} else if (p_b_No.contains("-15.")) {
				customWait(500);
				clickBtn(Locators_DeviceStability.send_Icon_O);
				minWait();
			} else if (p_b_No.contains("-29.") || p_b_No.contains("-26.") || p_b_No.contains("-11.")) {

				customWait(500);
				clickBtn(multi_Loc_Strategy(Locators_DeviceStability.send_Icon_B, Locators_DeviceStability.send_Icon_B1,
						Locators_DeviceStability.send_Icon_B2, null, null, 0, 0));
				System.out.println("sending");
				minWait();
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->clickOn_Send()");
			e.printStackTrace();

		} catch (Exception e) {

			test.log(LogStatus.ERROR, "Exeption in ->clickOn_Send()");
		}
	}

	public void validate_Sprint(SoftAssert soft) {
		try {
			wait(Locators_DeviceStability.now_sms1, 15);

			if (isElementExist(Locators_DeviceStability.now_sms1)) {
				APP_LOGS.info("Message sent Succeccfully");
				System.out.println("Sent");
				soft.assertTrue(true, "TestCase Valiation is PASS");
				test.log(LogStatus.PASS, "SMS Sent Successfully at iteration ");
			} else {
				APP_LOGS.info("SMS didn't sent");
				// soft.fail();
				test.log(LogStatus.FAIL, "Message didn't sent at iteration ");
			}
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		soft.assertAll();
	}

	public void validate_SentMessage(SoftAssert soft) throws InterruptedException {
		/* To validate the Sent Message. */

		try {
			if (p_b_No.contains("-10.")) {
				System.out.println("Sent msg");
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
				customWait(1000);
				wait(Locators_DeviceStability.now_Text, 15);
				if (isElementExist(Locators_DeviceStability.now_Text)
						|| isElementExist(Locators_DeviceStability.justnow_Text)
						|| isElementExist(Locators_DeviceStability.not_Sent_Text)
						|| isElementExist(Locators_DeviceStability.sending_Txt)
						|| isElementExist(Locators_DeviceStability.sended_SMS)) {

					APP_LOGS.info("Message sent Succeccfully");
					test.log(LogStatus.PASS, "Test case status is Passed at iteration : ");
					soft.assertTrue(true, "Test case status is Passed at iteration :  ");

				} else {

					APP_LOGS.info("SMS didn't sent");
					test.log(LogStatus.FAIL, "SMS or MMS didn't Sent, Validation Failed at iteration : ");
					soft.fail();
				}
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
				minWait();
			} else if (p_b_No.contains("-15.")) {
				wait(Locators_DeviceStability.Delivered, 10);
				if (isElementExist(Locators_DeviceStability.Delivered)
						|| isElementExist(Locators_DeviceStability.Delivered)
						|| isElementExist(Locators_DeviceStability.not_Sent_Text)) {
					check = true;
					APP_LOGS.info("Message sent Succeccfully");
					System.out.println("Sent");

					test.log(LogStatus.PASS, "SMS Sent Successfully at iteration ");
					soft.assertTrue(check, "SMS Sent Successfully at iteration ");
					APP_LOGS.info("SMS Sent Successfully at iteration ");
				} else {
					APP_LOGS.info("SMS didn't sent");
					soft.fail();
					test.log(LogStatus.FAIL, "Message didn't sent at iteration ");
				}
			} else if (p_b_No.contains("-29.") || p_b_No.contains("-26.") || p_b_No.contains("-11.")) {
				wait(Locators_DeviceStability.now_sms1, 15);
				if (isElementExist(Locators_DeviceStability.now_sms1)
						|| isElementExist(Locators_DeviceStability.now_sms3)
						|| isElementExist(Locators_DeviceStability.now_sms)
						|| isElementExist(Locators_DeviceStability.Delivered)
						|| isElementExist(Locators_DeviceStability.now_sms1)) {
					APP_LOGS.info("Message sent Succeccfully");
					System.out.println("Sent");
					soft.assertTrue(true, "TestCase Valiation is PASS");
					test.log(LogStatus.PASS, "SMS Sent Successfully at iteration ");
				} else {
					APP_LOGS.info("SMS didn't sent");
					soft.fail();
					test.log(LogStatus.FAIL, "Message didn't sent at iteration ");
				}

			}

			/* aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK); */
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->validate_SentMessage()");
			e.printStackTrace();

		} catch (Exception e) {

			test.log(LogStatus.ERROR, "Exeption in ->validate_SentMessage()");
		}

	}

	public boolean wait(AndroidElement element, int waitTime) {
		boolean availability = false;
		try {
			new WebDriverWait(aDriver, waitTime).until(ExpectedConditions.visibilityOf(element));
			availability = true;
		} catch (NoSuchElementException e) {
			availability = false;
		}
		return availability;
	}

}
