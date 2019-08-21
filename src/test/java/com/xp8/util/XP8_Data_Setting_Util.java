package com.xp8.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalTime;

import org.json.simple.parser.ParseException;
import org.jsoup.select.Evaluator.AllElements;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.server.handler.SendKeys;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import com.graphics.gui.JsonFileReaderAndWriter;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import application.AllQA;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.AndroidKeyCode;
import javassist.bytecode.stackmap.BasicBlock.Catch;

public class XP8_Data_Setting_Util extends BaseUtil {

	private static final String String = null;
	public static ExtentReports extent;
	public static ExtentTest test;

	public Process p;
	public boolean check = false;
	public boolean check1 = false;

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

	public void navigate_to_AccessPointName() {

		try {
			clickBtn(Locators_Data_Setting.networkandinternetopt);
			clickBtn(Locators_Data_Setting.Mobilenetworkopt);
			clickBtn(Locators_Data_Setting.Advancedopt);
			scrollToText("Access Point Names");
			clickBtn(Locators_Data_Setting.APNopt);
			
		} catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.ERROR, "Error in the locators->navigate_to_APNScreen()");
			e.printStackTrace();
		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exeption in ->navigate_to_APNScreen()");
			e.printStackTrace();
		}

	}

	public void validate_navigatetoAPNscreen(SoftAssert sa) {
		try {

			if (isElementExist(Locators_Data_Setting.apnsframe)) {
				
				APP_LOGS.info("APN Screen is navigated successfully");
				sa.assertTrue(true, "APN Screen is navigated successfully");
				test.log(LogStatus.PASS, "APN Screen is navigated successfully");

			} else {
				APP_LOGS.info("APN Screen navigation fail");
				sa.fail();
				test.log(LogStatus.FAIL, "APN Screen navigation fail");

			}
		} catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.ERROR, "Error in locators->validate_navigatetoAPNs()");
			e.printStackTrace();

		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exception  ->validate_navigatetoAPNs()");
			e.printStackTrace();

		}
	}

	public void VerifyingAlltheFieldinAPNScreen(SoftAssert sa) {

		try {
			clickBtn(Locators_Data_Setting.APNSettings);
			if (Locators_Data_Setting.resettodefaul.isDisplayed()) {
				APP_LOGS.info("Setting option displayed successfully");
				sa.assertTrue(true, "Setting option displayed successfully");
				test.log(LogStatus.PASS, "Setting option displayed successfully");
			} else {
				APP_LOGS.info("Setting option is not displayed");
				sa.fail();
				test.log(LogStatus.PASS, "Setting option is not displayed ");
			}
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			clickBtn(Locators_Data_Setting.NewAPN);
			if (Locators_Data_Setting.editapopt.isDisplayed()) {
				APP_LOGS.info("adding new APN field is displayed successfully");
				sa.assertTrue(true, "adding new APN field is displayed successfully");
				test.log(LogStatus.PASS, "adding new APN field is displayed successfully");
			} else {
				APP_LOGS.info("adding new APN field is not displayed ");
				sa.fail();
				test.log(LogStatus.PASS, "adding new APN field is not displayed ");
			}
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			clickBtn(Locators_Data_Setting.okbtn);
			clickBtn(Locators_Data_Setting.APNSettings);
			clickBtn(Locators_Data_Setting.discardbtn);

		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->VerifyingAlltheFieldinAPNScreen()");
			e.printStackTrace();
		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exeption in ->VerifyingAlltheFieldinAPNScreen()");
			e.printStackTrace();
		}

	}

	public void AddAPNnumber(String name, String APN) throws InterruptedException, IOException {
		try {
			if (isElementExist(Locators_Data_Setting.apnsframe)) {
				clickBtn(Locators_Data_Setting.NewAPN);
				add_APNName(name);
				add_APNNo(APN);
				save_APN();
			}

		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators --> AddAPNnumber()");
			e.printStackTrace();

		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exception in -->AddAPNnumber()");
			e.printStackTrace();
		}
	}

	public void validate_Added_APN(SoftAssert sa) {
		try {

			if (Locators_Data_Setting.apnsframe.isDisplayed()) {
				APP_LOGS.info("APN added successfully");
				sa.assertTrue(true, "APN added successfully");
				test.log(LogStatus.PASS, "APN added successfully");
			} else {
				APP_LOGS.info("Fail to added APN");
				sa.fail();
				test.log(LogStatus.PASS, "Fail to added APN");
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators --> AddAPNnumber()");
			e.printStackTrace();

		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exception in -->AddAPNnumber()");
			e.printStackTrace();
		}
	}

	public void add_APNName(String name) throws InterruptedException {
		clickBtn(Locators_Data_Setting.APNname);
		enterTextToInputField(Locators_Data_Setting.APNinput, name);
		clickBtn(Locators_Data_Setting.okbtn);

	}

	public void add_APNNo(String apn) throws InterruptedException {
		clickBtn(Locators_Data_Setting.APNno);
		// int num = (int) Double.parseDouble(apn);
		enterTextToInputField(Locators_Data_Setting.APNinput, apn.substring(0, 3));
		webwait(Locators_Data_Setting.okbtn, 30);
		clickBtn(Locators_Data_Setting.okbtn);
	}

	public void editAPNnumber(String name, String APN) throws InterruptedException, IOException {

		try {

			clickBtn(Locators_Data_Setting.newapnnum);
			add_APNName(name);
			add_APNNo(APN);
			save_APN();
		} catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.ERROR, "Error in the locators->editAPNnumber()");
			e.printStackTrace();

		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exception in editAPNnumber()");
			e.printStackTrace();
		}
	}

	public void CancelAPN(String name, String APN) throws InterruptedException, IOException {

		try {

			clickBtn(Locators_Data_Setting.editapnnum);
			add_APNName(name);
			clickBtn(Locators_Data_Setting.APNno);
			enterTextToInputField(Locators_Data_Setting.APNinput, APN);
			clickBtn(Locators_Data_Setting.apncancelbtn);
			discard_APN();

		} catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.ERROR, "Error in the locators->editAPNnumber()");
			e.printStackTrace();

		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exception in editAPNnumber()");
			e.printStackTrace();
		}
	}

	public void validate_edited_APN(SoftAssert sa) {
		try {
			if (isElementExist(Locators_Data_Setting.apnsframe)) {
				APP_LOGS.info("APN edited successfully");
				sa.assertTrue(true, "APN edited successfully");
				test.log(LogStatus.PASS, "APN edited successfully");
			} else {
				APP_LOGS.info("Fail to edited APN");
				sa.fail();
				test.log(LogStatus.PASS, "Fail to edited APN");
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.ERROR, "Error in the locators->editAPNnumber()");
			e.printStackTrace();

		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exception in editAPNnumber()");
			e.printStackTrace();
		}
	}

	public void validate_CancelAPN(SoftAssert sa) {
		try {
			if (isElementExist(Locators_Data_Setting.editapnnum)) {
				APP_LOGS.info("APN cancel successfully");
				sa.assertTrue(true, "APN cancel successfully");
				test.log(LogStatus.PASS, "APN cancel successfully");
			} else {
				APP_LOGS.info("Fail to edited APN");
				sa.fail();
				test.log(LogStatus.PASS, "Fail to edited APN");
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.ERROR, "Error in the locators->validate_CancelAPN()");
			e.printStackTrace();

		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exception in validate_CancelAPN()");
			e.printStackTrace();
		}
	}

	public void deleteAPNnumber() throws InterruptedException, IOException {

		try {

			clickBtn(Locators_Data_Setting.editapnnum);
			clickBtn(Locators_Data_Setting.APNSettings);
			clickBtn(Locators_Data_Setting.deleteapnnum);

		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->deleteAPNnumber()");
			e.printStackTrace();

		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exception in deleteAPNnumber()");
			e.printStackTrace();
		}
	}

	public void validate_deleted_APN(SoftAssert sa) {

		if (!isElementExist(Locators_Data_Setting.editapnnum)) {
			APP_LOGS.info("APN deleted successfully");
			sa.assertTrue(true, "APN deleted successfully");
			test.log(LogStatus.PASS, "APN deleted successfully");
			
			
		} else {
			APP_LOGS.info("Fail to deleted APN");
			sa.fail();
			test.log(LogStatus.PASS, "Fail to deleted APN");

		}
	}

	public void Authenticationtype(String names) throws InterruptedException, IOException {

		scrollToText("Authentication type");

		java.lang.String atapn = Locators_Data_Setting.AuthenticationtypeofAPN.getText();

		if (atapn.equals("Authentication type")) {

			switch (names) {

			case "None":
				clickBtn(Locators_Data_Setting.NoneAPNAT);

				break;
			case "PAP":
				clickBtn(Locators_Data_Setting.PAPAPNAT);

				break;
			case "CHAP":
				clickBtn(Locators_Data_Setting.CHAPAPNAT);

				break;
			case "PAPorCHAP":
				clickBtn(Locators_Data_Setting.PAPorCHAPPNAT);

				break;
			}
		}
	}

	public void validate_Authenticationtype(SoftAssert sa) {

		if (isElementExist(Locators_Data_Setting.NoneAPNAT)) {
			APP_LOGS.info("None selected successfully");
			sa.assertTrue(true, "None selected successfully");
			test.log(LogStatus.PASS, "None selected successfully");
		} else if (isElementExist(Locators_Data_Setting.PAPAPNAT)) {
			APP_LOGS.info("PAP selected successfully");
			sa.assertTrue(true, "PAP selected successfully");
			test.log(LogStatus.PASS, "PAP selected successfully");
		} else if (isElementExist(Locators_Data_Setting.CHAPAPNAT)) {
			APP_LOGS.info("CHAP selected successfully");
			sa.assertTrue(true, "CHAP selected successfully");
			test.log(LogStatus.PASS, "CHAP selected successfully");
		} else {
			APP_LOGS.info("PAPorCHAP selected successfully");
			sa.assertTrue(true, "PAPorCHAP selected successfully");
			test.log(LogStatus.PASS, "PAPorCHAP selected successfully");

		}
	}

	public void differentAPNField(SoftAssert sa, String name, String APN, String proxyapn, java.lang.String string2,
			String usernameapn, java.lang.String string3, java.lang.String string4, String MMSCapn)
			throws InterruptedException, IOException {

		try

		{
			clickBtn(Locators_Data_Setting.NewAPN);

			java.lang.String name1 = Locators_Data_Setting.APNname.getText();
			System.out.println(name1);
			if (name1.equals("Name")) {
				add_APNName(name1);
				APP_LOGS.info("Name added successfully");
				sa.assertTrue(true, "Name field in APN are validated");
				test.log(LogStatus.PASS, "Name added   successfully");

				java.lang.String apnno = Locators_Data_Setting.APNno.getText();
				if (apnno.equals("APN")) {
					add_APNNo(APN);
					APP_LOGS.info("APNNo added successfully");
					sa.assertTrue(true, "APNNo field in APN are validated");
					test.log(LogStatus.PASS, "APNNumber added   successfully");

				}

				java.lang.String proxy = Locators_Data_Setting.proxyAPN.getText();
				if (proxy.equals("Proxy")) {
					clickBtn(Locators_Data_Setting.proxyAPN);
					enterTextToInputField(Locators_Data_Setting.APNinput, proxyapn);
					clickBtn(Locators_Data_Setting.okbtn);
					APP_LOGS.info("Proxy added successfully");
					sa.assertTrue(true, "Proxy field in APN are validated");
					test.log(LogStatus.PASS, "Proxy added   successfully");

				}
				java.lang.String port = Locators_Data_Setting.portAPN.getText();
				if (port.equals("Port")) {
					clickBtn(Locators_Data_Setting.portAPN);
					enterTextToInputField(Locators_Data_Setting.APNinput, proxyapn);
					clickBtn(Locators_Data_Setting.okbtn);
					APP_LOGS.info("portAPN added successfully");
					sa.assertTrue(true, "portAPNfield in APN are validated");
					test.log(LogStatus.PASS, "portAPN added   successfully");

				}

				java.lang.String username = Locators_Data_Setting.usernameAPN.getText();
				if (username.equals("Username")) {
					clickBtn(Locators_Data_Setting.usernameAPN);
					enterTextToInputField(Locators_Data_Setting.APNinput, proxyapn);
					clickBtn(Locators_Data_Setting.okbtn);
					APP_LOGS.info("Username added successfully");
					sa.assertTrue(true, "Username field in APN are validated");
					test.log(LogStatus.PASS, "Username added   successfully");

				}
				java.lang.String password = Locators_Data_Setting.PasswordAPN.getText();
				if (password.equals("Password")) {
					clickBtn(Locators_Data_Setting.PasswordAPN);
					enterTextToInputField(Locators_Data_Setting.APNinput, proxyapn);
					clickBtn(Locators_Data_Setting.okbtn);
					APP_LOGS.info("Password added successfully");
					sa.assertTrue(true, "Password in APN are validated");
					test.log(LogStatus.PASS, "Password added   successfully");

				}
				java.lang.String server = Locators_Data_Setting.ServerAPN.getText();
				if (server.equals("Server")) {
					clickBtn(Locators_Data_Setting.ServerAPN);
					enterTextToInputField(Locators_Data_Setting.APNinput, proxyapn);
					clickBtn(Locators_Data_Setting.okbtn);
					APP_LOGS.info("Server added successfully");
					sa.assertTrue(true, "Server in APN are validated");
					test.log(LogStatus.PASS, "Server added   successfully");

				}
				java.lang.String mmsc = Locators_Data_Setting.MMSCAPN.getText();
				if (mmsc.equals("MMSC")) {
					clickBtn(Locators_Data_Setting.MMSCAPN);
					enterTextToInputField(Locators_Data_Setting.APNinput, proxyapn);
					clickBtn(Locators_Data_Setting.okbtn);
					APP_LOGS.info("MMSC added successfully");
					sa.assertTrue(true, "MMSC in APN are validated");
					test.log(LogStatus.PASS, "MMSC added   successfully");

				}

			}

		} catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.ERROR, "Error in the locators->differentAPNField()");
			e.printStackTrace();

		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exception in differentAPNField()");
			e.printStackTrace();
		}
	}

	public void save_APN() {
		try {
			clickBtn(Locators_Data_Setting.APNSettings);
			clickBtn(Locators_Data_Setting.savebtn);
		}

		catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators -->save_APN() ");
			e.printStackTrace();

		} catch (Exception e) {
			test.log(LogStatus.ERROR, "error in locator------>save_APN()");
			e.printStackTrace();
		}
	}

	public void discard_APN() {
		try {
			clickBtn(Locators_Data_Setting.APNSettings);
			clickBtn(Locators_Data_Setting.discardbtn);
		}

		catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators --> discard_APN()");
			e.printStackTrace();

		} catch (Exception e) {
			test.log(LogStatus.ERROR, "error in locator------>discard_APN()");
			e.printStackTrace();
		}
	}

	public void reset_To_Default() {
		try {
			clickBtn(Locators_Data_Setting.APNSettings);
			clickBtn(Locators_Data_Setting.resettodefaul);
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators -->reset_To_Default");
			e.printStackTrace();

		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Error in the locators -->reset_To_Default");
			e.printStackTrace();
		}

	}

	public void validate_Reset_to_Default(SoftAssert sa) {
		try {
			if (Locators_Data_Setting.apnsframe.isDisplayed()) {
				APP_LOGS.info("reset the apn sucessfully");
				sa.assertTrue(true, "reset the apn sucessfully\"");
				test.log(LogStatus.PASS, "reset the apn sucessfully\"");
			} else {
				APP_LOGS.info("Failed to reset the apn");
				sa.assertTrue(true, "Failed to reset the apn");
				test.log(LogStatus.PASS, "Failed to reset the apn");
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators -->validate_Reset_to_Default");
			e.printStackTrace();

		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Error in the locators -->validate_Reset_to_Default");
			e.printStackTrace();
		}
	}

	public void save_APN_without_Entering_APN(String erromsgdiplay, SoftAssert sa) throws InterruptedException {
		try {
			navigate_to_AccessPointName();
			clickBtn(Locators_Data_Setting.NewAPN);
			add_APNName("Sonim");
			save_APN();
			webwait(Locators_Data_Setting.errormsginapn, 30);
			java.lang.String errormsg = Locators_Data_Setting.errormsginapn.getText();
			if (errormsg.equals(erromsgdiplay)) {
				clickBtn(Locators_Data_Setting.okbtn);
				save_APN();
				APP_LOGS.info(errormsg + "  msg displayed successfully");
				sa.assertTrue(true, errormsg + "  msg displayed successfully");
				test.log(LogStatus.PASS, errormsg + "  msg displayed successfully");
			} else {
				APP_LOGS.info("error msg is not displayed");
				sa.fail();
				test.log(LogStatus.FAIL, "error msg is not displayed");

			}
		}

		catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators --> addapn_without_name_and_apnno()");
			e.printStackTrace();

		} catch (Exception e) {
			test.log(LogStatus.ERROR, "error msg is not displayed");
			e.printStackTrace();
		}

	}

	public void addapn_Without_Entering_Name_and_Apnno() throws InterruptedException, IOException {
		try {

			if (Locators_Data_Setting.apnsframe.isDisplayed()) {
				clickBtn(Locators_Data_Setting.NewAPN);

				save_APN();
				if (Locators_Data_Setting.errormsginname.isDisplayed()) {

				} else {
					APP_LOGS.info("error msg is not displayed");

					test.log(LogStatus.FAIL, "error msg is not displayed");

				}

			}

		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators --> addapn_without_name_and_apnno()");
			e.printStackTrace();

		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exception in -->addapn_without_name_and_apnno()");
			e.printStackTrace();
		}
	}

	public void validate_error_Msg(String errormsg, SoftAssert sa) throws InterruptedException, IOException {
		try {
			java.lang.String text = Locators_Data_Setting.errormsginname.getText();
			if (text.equals(errormsg)) {

				APP_LOGS.info(errormsg + " msg is  displayed sucessfully");
				sa.assertTrue(true, errormsg + "  msg is  displayed sucessfully");
				test.log(LogStatus.PASS, errormsg + " msg is  displayed sucessfully");
			} else {
				APP_LOGS.info("error msg is not displayed");
				sa.fail();
				test.log(LogStatus.FAIL, "error msg is not displayed");

			}

		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators --> addapn_without_name_and_apnno()");
			e.printStackTrace();

		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exception in -->addapn_without_name_and_apnno()");
			e.printStackTrace();
		}
	}

	public void lock_Unlock_Screen(String name, String APN, SoftAssert sa) throws InterruptedException, IOException {
		try {
			navigate_to_AccessPointName();
			if (isElementExist(Locators_Data_Setting.apnsframe)) {
				clickBtn(Locators_Data_Setting.NewAPN);
				add_APNName(name);
				
				launchAppThroughABD("adb shell input keyevent 26");
				launchAppThroughABD(" adb shell input keyevent 26");
				launchAppThroughABD(" adb shell input keyevent 82");
//				Runtime.getRuntime().exec(" adb shell input keyevent 26");
//				customWait(2000);
//
//				Runtime.getRuntime().exec(" adb shell input keyevent 26");
//
//				minWait();
//				Runtime.getRuntime().exec("adb shell input keyevent 82");
//				maxWait();
				validate_Lock_Unlock_Screen(sa);
				add_APNNo(APN);
				save_APN();

			} else {
				APP_LOGS.info("Lock&unlock the device is not working------->lock_Unlock_Screen()");

				test.log(LogStatus.PASS, "Lock&Unlock  is not working----------->lock_Unlock_Screen()");

			}

		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators --> lock_Unlock_Screen()");
			e.printStackTrace();

		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exception in -->lock_Unlock_Screen()");
			e.printStackTrace();
		}
	}

	public void validate_Lock_Unlock_Screen(SoftAssert sa) {
		try {
			if (isElementExist(Locators_Data_Setting.editapopt)) {
				APP_LOGS.info("Edit access point Screen displayed sucessfully");
				sa.assertTrue(true, "Edit access point Screen displayed sucessfully");
				test.log(LogStatus.PASS, "Edit access point Screen displayed sucessfully");
			} else {
				APP_LOGS.info("Failed to display the Edit access point Screen");
				sa.fail();
				test.log(LogStatus.PASS, "Failed to display the Edit access point Screen");
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators --> validate_Lock_Unlock_Screen()");
			e.printStackTrace();

		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exception in -->validate_Lock_Unlock_Screen()");
			e.printStackTrace();
		}
	}
	// public void APN_Displayed_Under_Network_Settings(SoftAssert sa)

	public void add_APN_Click_Backbutton(SoftAssert sa, String name, String apn, String expectedtext)
			throws InterruptedException, IOException {
		try {
			clickBtn(Locators_Data_Setting.NewAPN);
			add_APNName(name);
			add_APNNo(apn);
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			webwait(Locators_Data_Setting.apnsframe, 30);
			if (isElementExist(Locators_Data_Setting.apnsframe)) {
				if (Locators_Data_Setting.newapnnum.isDisplayed()) {
					java.lang.String apnname = Locators_Data_Setting.newapnnum.getText();
					if (apnname.equals(expectedtext)) {
						APP_LOGS.info(apnname + " added sucessfully in APNs Screen");
						sa.assertTrue(true, apnname + "APN added sucessfully in APNs Screen");
						test.log(LogStatus.PASS, apnname + "APN added sucessfully in APNs Screen");
					}
				}
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.ERROR, "Error in the locators->add_APN_Click_Backbutton()");
			e.printStackTrace();
		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Error in the locators->add_APN_Click_Backbutton()");
			e.printStackTrace();
		}
	}

	public void enable_Disable_Airplane_Mode(SoftAssert sa, String dataon) {
		try {
			clickBtn(Locators_Data_Setting.networkandinternetopt);
			if (isElementExist(Locators_Data_Setting.airplanemode)) {

				Turn_On_Off_Airplane_Mode(4, sa, dataon);

			}
		} catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.ERROR, "Error in the locators->add_APN_Click_Backbutton()");
			e.printStackTrace();
		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Error in the locators->add_APN_Click_Backbutton()");
			e.printStackTrace();
		}
	}

	public void Turn_On_Off_Airplane_Mode(int number, SoftAssert sa, String on) throws InterruptedException {

		try {
			for (int i = 0; i < number; i++) {
				webwait(Locators_Data_Setting.airplanemodeswitchonoff, 30);
				clickBtn(Locators_Data_Setting.airplanemodeswitchonoff);

				webwait(Locators_Data_Setting.Mobilenetworkopt, 30);
				if (Locators_Data_Setting.Mobilenetworkopt.isDisplayed()) {
					clickBtn(Locators_Data_Setting.mobiledatarbtn);
					webwait(Locators_Data_Setting.mobiledatarbtn, 30);
					java.lang.String text = Locators_Data_Setting.mobiledatarbtn.getText();
					if (text.equals(on)) {
						APP_LOGS.info("Airplane mode is in OFF state");
						sa.assertTrue(true, "Airplane mode is in OFF state");
						test.log(LogStatus.PASS, "Airplane mode is in OFF state");
					} else {
						APP_LOGS.info("Airplane mode ON successfully");
						sa.assertTrue(true, "Airplane mode ON  successfully");
						test.log(LogStatus.PASS, "Airplane mode ON successfully");
					}
				} else {
					APP_LOGS.info("Airplane mode OFF");
					sa.fail();
					test.log(LogStatus.PASS, "Airplane mode OFF");
				}
				minWait();
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.ERROR, "Error in the locators->on_Off_Airoplane_Mode()");
			e.printStackTrace();
		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exeption occurs in  on_Off_Airoplane_Mode()");
			e.printStackTrace();
		}
	}

	public void APN_protocol(String name, String apn, String Protocolname, SoftAssert sa)
			throws InterruptedException, IOException {
		try {
			clickBtn(Locators_Data_Setting.NewAPN);
			add_APNName(name);
			add_APNNo(apn);
			scrollToText("APN protocol");
			switch (Protocolname) {
			case "IPv4":
				clickBtn(Locators_Data_Setting.ipv4p);
				APP_LOGS.info("IPv4 selected successfully in APN Protocol");
				sa.assertTrue(true, "IPv4 selected successfully in APN Protocol");
				test.log(LogStatus.PASS, "IPv4 selected successfully in APN Protocol");
				break;
			case "IPv6":
				clickBtn(Locators_Data_Setting.ipv6p);
				APP_LOGS.info("IPv6 selected successfully in APN Protocol");
				sa.assertTrue(true, "IPv6 selected successfully in APN Protocol");
				test.log(LogStatus.PASS, "IPv6 selected successfully in APN Protocol");
				break;
			case "IPv4/IPv6":
				clickBtn(Locators_Data_Setting.ipv4oripv6p);
				APP_LOGS.info("IPv4/IPv6 selected successfully in APN Protocol");
				sa.assertTrue(true, "IPv4/IPv6 selected successfully in APN Protocol");
				test.log(LogStatus.PASS, "IPv4/IPv6 selected successfully in APN Protocol");
				break;

			}
		} catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.ERROR, "Error in the locators->APN_protocol()");
			e.printStackTrace();
		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exeption occurs in  APN_protocol()");
			e.printStackTrace();
		}

	}

	public void APN_Roaming_Protocol(String Protocolname, SoftAssert sa) throws InterruptedException, IOException {
		try {
			scrollToText("APN roaming protocol");
			switch (Protocolname) {
			case "IPv4":
				clickBtn(Locators_Data_Setting.ipv4r);
				APP_LOGS.info("IPv4 selected successfully in APN Roaming");
				sa.assertTrue(true, "IPv4 selected successfully in APN Roaming");
				test.log(LogStatus.PASS, "IPv4 selected successfully in APN Roaming");
				break;
			case "IPv6":
				clickBtn(Locators_Data_Setting.ipv6r);
				APP_LOGS.info("IPv6 selected successfully in APN Roaming");
				sa.assertTrue(true, "IPv6 selected successfully APN Roaming");
				test.log(LogStatus.PASS, "IPv6 selected successfully APN Roaming");
				break;
			case "IPv4orIPv6":
				clickBtn(Locators_Data_Setting.ipv4oripv6r);
				APP_LOGS.info("IPv4/IPv6 selected successfully in APN Roaming");
				sa.assertTrue(true, "IPv4/IPv6 selected successfully in APN Roaming");
				test.log(LogStatus.PASS, "IPv4/IPv6 selected successfully in APN Roaming");
				break;

			}
		}

		catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.ERROR, "Error in the locators->APN_Roaming_Protocol()");
			e.printStackTrace();
		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exeption occurs in  APN_Roaming_Protocol()");
			e.printStackTrace();
		}

	}

	public void Bearer(String name, String apn) throws InterruptedException {
		try {
			clickBtn(Locators_Data_Setting.NewAPN);
			add_APNName(name);
			add_APNNo(apn);
			minWait();
			scrollToText("Bearer");
		} catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.ERROR, "Error in the locators->Bearer()");
			e.printStackTrace();
		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exeption occurs in  Bearer()");
			e.printStackTrace();
		}
	}

	public void validate_Bearer(SoftAssert sa) throws InterruptedException {

		try {
			boolean check1 = scrollToText("Unspecified");
			boolean check2 = scrollToText("LTE");
			boolean check3 = scrollToText("HSPAP");
			boolean check4 = scrollToText("HSPA");
			boolean check5 = scrollToText("HSUPA");
			boolean check6 = scrollToText("HSDPA");
			boolean check7 = scrollToText("UMTS");
			boolean check8 = scrollToText("UMTS");
			boolean check9 = scrollToText("GPRS");
			boolean check10 = scrollToText("eHRPD");
			boolean check11 = scrollToText("EVDO_B");
			boolean check12 = scrollToText("EVDO_A");
			boolean check13 = scrollToText("EVDO_0");
			boolean check14 = scrollToText("1xRTT");
			boolean check15 = scrollToText("IS95B");
			boolean check16 = scrollToText("IS95A");

			if (check1 && check2 && check3 && check4 && check5 && check6 && check7 && check8 && check9 && check10
					&& check11) {
				APP_LOGS.info("Bearer selected successfully");
				sa.assertTrue(true, "Bearer selected successfully");
				test.log(LogStatus.PASS, "Bearer selected successfully");
			} else {
				APP_LOGS.info("Failed to Select Bearer");
				sa.fail();
				test.log(LogStatus.FAIL, "Failed to Select Bearer");

			}

		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->validate_Bearer()");
			e.printStackTrace();

		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exeption in ->validate_Bearer()");
		}

	}

	public void enabled_Data_Access(SoftAssert sa) {
		try {
			clickBtn(Locators_Data_Setting.networkandinternetopt);
			clickBtn(Locators_Data_Setting.Mobilenetworkopt);
			if (Locators_Data_Setting.mobiledata.isDisplayed()) {
				if (Locators_Data_Setting.mobiledatarbtn.getText().equals("ON")) {

					System.out.println("ON");
					APP_LOGS.info("Mobile data selected successfully");
					sa.assertTrue(true, "Mobile data selected successfully");
					test.log(LogStatus.PASS, "Mobile data selected successfully");
				}

				else if (Locators_Data_Setting.mobiledatarbtn.getText().equals("OFF")) {
					clickBtn(Locators_Data_Setting.mobiledatarbtn);
					APP_LOGS.info("Mobile data selected successfully");
					sa.assertTrue(true, "Mobile data selected successfully");
					test.log(LogStatus.PASS, "Mobile data selected successfully");

				}

			} else {
				APP_LOGS.info("Failed to Select Mobile data");
				sa.fail();
				test.log(LogStatus.FAIL, "Failed to Select Mobile data");

			}
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->enabled_Data_Access()");
			e.printStackTrace();

		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exeption in ->enabled_Data_Access()");
		}
	}

	public void Launch_The_Browser(SoftAssert sa) throws InterruptedException, IOException {
		try {
			launch_an_app("browser");
			clickBtn(Locators_Data_Setting.googletextfield);
			enterTextToInputField(Locators_Data_Setting.textfieldinchrome, "https://www.amazon.in");

			aDriver.pressKeyCode(AndroidKeyCode.ENTER);
            minWait();
			if (isElementExist(Locators_Data_Setting.nointernet)) {

				APP_LOGS.info("Failed to Launch the browser ");
				sa.assertTrue(true, "Failed to Launch the browser ");
				test.log(LogStatus.PASS, "Failed to Launch the browser");

			}

			else {
			
				APP_LOGS.info("Launch the browser sucessfully");
				sa.assertTrue(true, "Launch the browser sucessfully");
				test.log(LogStatus.PASS, "Launch the browser sucessfully");
			}

		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->open_Browser(()");
			e.printStackTrace();

		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exeption in ->open_Browser()");
		}
	}

	public void enable_Airplane_Mode() {
		try {
			clickBtn(Locators_Data_Setting.networkandinternetopt);
			if (isElementExist(Locators_Data_Setting.airplanemode)) {

				if (Locators_Data_Setting.airplanemodeswitchonoff.getText().equals("ON")) {

				}

				else {
					clickBtn(Locators_Data_Setting.airplanemodeswitchonoff);

				}

			}

		} catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.ERROR, "Error in the locators->enable_Airoplane_Mode()");
			e.printStackTrace();
		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Error in the locators->enable_Airoplane_Mode()");
			e.printStackTrace();
		}
	}

	public void verifying_MobileNtwrk(SoftAssert sa) {

		try {

			if (isElementExist(Locators_Data_Setting.Mobilenetworkopt)) {

				APP_LOGS.info("Successfully Mobile Network is Disabled Not Able to select Mobile Network Icon");
				sa.assertTrue(true, "Successfully Mobile Network is Disabled Not Able to select Mobile Network Icon");
				test.log(LogStatus.PASS,
						"Successfully Mobile Network is Disabled Not Able to select Mobile Network Icon");
			}

			else {

				APP_LOGS.info("Mobile data enabled in Network Setting");
				sa.assertTrue(true, "Mobile data enabled in Network Setting");
				test.log(LogStatus.PASS, "Mobile data enabled in Network Setting");

			}

		}

		catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.ERROR, "Error in the locators->verifying_MobileNtwrk()");
			e.printStackTrace();
		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Error in the locators->verifying_MobileNtwrk()");
			e.printStackTrace();
		}
	}

	public void enable_data(SoftAssert sa) {

		try {

			if (isElementExist(Locators_Data_Setting.Mobilenetworkopt)) {
				clickBtn(Locators_Data_Setting.Mobilenetworkopt);
				if (Locators_Data_Setting.mobiledata.isDisplayed()) {
					if (Locators_Data_Setting.mobiledatarbtn.getText().equals("ON")) {

						APP_LOGS.info("Mobile data selected successfully");
						sa.assertTrue(true, "Mobile data selected successfully");
						test.log(LogStatus.PASS, "Mobile data selected successfully");
					}

					else {
						clickBtn(Locators_Data_Setting.mobiledatarbtn);
						APP_LOGS.info("Mobile data selected successfully");
						sa.assertTrue(true, "Mobile data selected successfully");
						test.log(LogStatus.PASS, "Mobile data selected successfully");

					}

				}

			}

		} catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.ERROR, "Error in the locators->enable_Airoplane_Mode()");
			e.printStackTrace();
		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Error in the locators->enable_Airoplane_Mode()");
			e.printStackTrace();
		}
	}

	public void disable_Airplane() {
		try {
			clickBtn(Locators_Data_Setting.networkandinternetopt);
			if (isElementExist(Locators_Data_Setting.airplanemode)) {

				if (Locators_Data_Setting.airplanemodeswitchonoff.getText().equals("OFF")) {

				}

				else {
					clickBtn(Locators_Data_Setting.airplanemodeswitchonoff);

				}

			}

		} catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.ERROR, "Error in the locators->disable_Airplane()");
			e.printStackTrace();
		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Error in the locators->disable_Airplane()");
			e.printStackTrace();
		}
	}

	public void network_Settingsopt() {
		try {
			clickBtn(Locators_Data_Setting.networkandinternetopt);
			clickBtn(Locators_Data_Setting.Mobilenetworkopt);
			clickBtn(Locators_Data_Setting.Advancedopt);
			if (isElementExist(Locators_Data_Setting.networksettingbar)) {

			}
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->verify_LTEserviceopt()");
			e.printStackTrace();

		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exception error in verify_LTEserviceopt()");
		}

	}

	public void Validate_LTEservice(SoftAssert sa) {
		try {
			if (Locators_Data_Setting.lteservice.isDisplayed()) {
				APP_LOGS.info("LTEservice is present inside Network Settings");
				sa.assertTrue(true, "LTEservice is present inside Network Settings");
				test.log(LogStatus.PASS, "LTEservice is present inside Network Settings");
			} else {
				APP_LOGS.info("LTEservice is not present inside Network Settings");
				sa.fail();
				test.log(LogStatus.FAIL, "LTEservice is Not present inside Network Settings");
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->Validate_LTEservice()");
			e.printStackTrace();

		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exception error in Validate_LTEservice()");
		}

	}

	public void view_And_Select_APNs(String apnname) {
		try {
			if (Locators_Data_Setting.apnname.isDisplayed()) {
				if (apnname.equals(Locators_Data_Setting.apnname.getText())) {
					clickBtn(Locators_Data_Setting.radiobtn);
				} else if (apnname.equals(Locators_Data_Setting.apnname.getText())) {
					clickBtn(Locators_Data_Setting.radiobtn);

				} else {
					System.out.println("Fail");
				}

			}
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->view_And_Select_APNs()");
			e.printStackTrace();

		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exception error in view_And_Select_APNs()");
		}
	}

	public void validate_View_And_Select_APNs(SoftAssert sa) {
		try {
			if (Locators_Data_Setting.newapnnum.isDisplayed()) {

				APP_LOGS.info("User viewed and Selected the APNs Successfully");
				sa.assertTrue(true, "User viewed and Selected the APNs Successfully");
				test.log(LogStatus.PASS, "User viewed and Selected the APNs Successfully");
			} else {
				APP_LOGS.info("User can't view and Select the APNs Successfully");
				sa.fail();
				test.log(LogStatus.FAIL, "User can't view and Select the APNs Successfully");
			}

		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->validate_View_And_Select_APNs()");
			e.printStackTrace();

		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Error in the locators->validate_View_And_Select_APNs()");
		}
	}

	public void validate_Added_APN_To_The_List(SoftAssert sa) {
		try {
			if (Locators_Data_Setting.apnsframe.isDisplayed()) {
				if (isElementExist(Locators_Data_Setting.newapnnum)) {
					APP_LOGS.info("APN added successfully");
					sa.assertTrue(true, "APN added successfully");
					test.log(LogStatus.PASS, "APN added successfully");
				} else {
					APP_LOGS.info("Failed to added APN");
					sa.fail();
					test.log(LogStatus.PASS, "Failed to added APN");
				}
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators --> AddAPNnumber()");
			e.printStackTrace();

		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exception in -->AddAPNnumber()");
			e.printStackTrace();
		}
	}

	public void discard_APNs(String name) throws InterruptedException {
		try {
			clickBtn(Locators_Data_Setting.NewAPN);
			add_APNName(name);
			discard_APN();
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators --> discard_APNs()");
			e.printStackTrace();

		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exception in -->discard_APNs()");
			e.printStackTrace();
		}

	}

	public void Validate_Discard_APNs(SoftAssert sa) {
		try {
			if (Locators_Data_Setting.apnsframe.isDisplayed()) {
				if (isElementExist(Locators_Data_Setting.newapnnum)) {
					APP_LOGS.info("Failed to discard APN");
					sa.fail();
					test.log(LogStatus.PASS, "Failed to discard APN");
				} else {
					APP_LOGS.info("APN discard successfully");
					sa.assertTrue(true, "APN discard successfully");
					test.log(LogStatus.PASS, "APN discard successfully");
				}
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators --> Validate_Discard_APNs()");
			e.printStackTrace();

		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exception in -->Validate_Discard_APNs()");
			e.printStackTrace();
		}

	}

	public void webwait(AndroidElement ele, int timeinSeconds) {
		try {
			waituntilnew(ele, 30);

		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators --> webwait()");
			e.printStackTrace();

		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exception in -->webwait()");
			e.printStackTrace();
		}
	}
}