package com.xp8.util;

import static org.testng.Assert.fail;

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

			clickBtn(Locators_Data_Setting.Advancedopt);
			scrollToText("Access Point Names");
		} catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.ERROR, "Error in the locators->navigate_to_APNScreen()");
			e.printStackTrace();
		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exeption in ->navigate_to_APNScreen()");
			e.printStackTrace();
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

	public boolean disable_AirplaneMode() {
		/*
		 * disable data from data usage window
		 */
		boolean disabled = false;
		try {
			scrollTo("Airplane mode");
			scrollToElements(Locators_Data_Setting.airplanemodeswitchonoff);
			if(Locators_Data_Setting.airplanemodeswitchonoff.getText().equalsIgnoreCase("on")) {
			clickBtn(Locators_Data_Setting.airplanemodeswitchonoff);
			}
			disabled = true;
		} catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.ERROR, "Error in the locators-> disable_AirplaneMode() ");
			e.printStackTrace();
		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exception in -> disable_AirplaneMode()");
		}
		return disabled;
	}

	public void validate_navigatetoAPNscreen(SoftAssert sa) {
		try {

			if (isElementExist(Locators_Data_Setting.apnsframe) || isElementExist(Locators_Data_Setting.APNSettings)) {
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

	public void navigate_to_APN() {

		try {

			clickBtn(Locators_Data_Setting.Advancedopt);
			scrollToText("Access Point Names");
			clickBtn(Locators_Data_Setting.NewAPN);

		} catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.ERROR, "Error in the locators->navigate_to_APNScreen()");
			e.printStackTrace();
		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exeption in ->navigate_to_APNScreen()");
			e.printStackTrace();
		}

	}

	public void navigate_to_APN_A() {

		try {

			clickBtn(Locators_Data_Setting.Advancedopt);
			scrollToText("Access Point Names");
			if (isElementExist(Locators_Data_Setting.NewAPN)) {
				clickBtn(Locators_Data_Setting.NewAPN);
			} else {
				aDriver.pressKeyCode(AndroidKeyCode.BACK);
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.ERROR, "Error in the locators->navigate_to_APNScreen()");
			e.printStackTrace();
		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exeption in ->navigate_to_APNScreen()");
			e.printStackTrace();
		}

	}

	public void click_ON_APNSetting() {
		try {
			clickBtn(Locators_Data_Setting.APNSettings);
			webwait(Locators_Data_Setting.resettodefaul, 10);
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->click_ON_APNSetting()");
			e.printStackTrace();
		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exeption in ->click_ON_APNSetting()");
			e.printStackTrace();
		}

	}

	public void validate_APNSettingopt(SoftAssert sa) {
		try {
			if (Locators_Data_Setting.resettodefaul.isDisplayed()) {
				APP_LOGS.info("Setting option displayed successfully");
				sa.assertTrue(true, "Setting option displayed successfully");
				test.log(LogStatus.PASS, "Setting option displayed successfully");
			} else {
				APP_LOGS.info("Setting option is not displayed");
				sa.fail();
				test.log(LogStatus.FAIL, "Setting option is not displayed ");
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->validate_APNSettingopt()");
			e.printStackTrace();
		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exeption in ->validate_APNSettingopt()");
			e.printStackTrace();
		}
	}

	public void add_APN_Field() {
		try {
			minWait();
			clickBtn(multi_Loc_Strategy(Locators_Data_Setting.NewAPN, Locators_Data_Setting.NewAPN1, null, null, null,
					880, 152));

		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->add_APN_Field()");
			e.printStackTrace();
		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exeption in ->add_APN_Field()");
			e.printStackTrace();
		}
	}

	public void click_on_Back() {
		aDriver.pressKeyCode(AndroidKeyCode.BACK);
	}

	public void click_On_Ok() {
		try {

			clickBtn(Locators_Data_Setting.okbtn);
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->click_On_Ok()");
			e.printStackTrace();
		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exeption in ->click_On_Ok()");
			e.printStackTrace();
		}

	}

	public void Validate_Add_APNfield(SoftAssert sa) {
		try {
			if (Locators_Data_Setting.editapopt.isDisplayed() || Locators_Data_Setting.APNname.isDisplayed()
					|| Locators_Data_Setting.APNno.isDisplayed()) {
				APP_LOGS.info(" new APN field is displayed successfully");
				sa.assertTrue(true, " new APN field is displayed successfully");
				test.log(LogStatus.PASS, " new APN field is displayed successfully");
			} else {
				APP_LOGS.info("adding new APN field is not displayed ");
				sa.fail();
				test.log(LogStatus.FAIL, "adding new APN field is not displayed ");
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->Validate_Add_APNfield()");
			e.printStackTrace();
		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exeption in ->Validate_Add_APNfield()");
			e.printStackTrace();
		}

	}

	public void Validate_Add_APNfield_A(SoftAssert sa) {
		try {

			if (Locators_Data_Setting.networksettingbar.isDisplayed()) {
				APP_LOGS.info("adding new APN field is not displayed ");
				sa.assertTrue(true, "adding new APN field is not displayed ");
				test.log(LogStatus.PASS, "adding new APN field is not displayed ");

			} else {
				APP_LOGS.info(" new APN field is displayed successfully");
				sa.fail();
				test.log(LogStatus.FAIL, " new APN field is displayed successfully");
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->Validate_Add_APNfield()");
			e.printStackTrace();
		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exeption in ->Validate_Add_APNfield()");
			e.printStackTrace();
		}

	}

	public void apn_Clk_Backbtn() {
		clickBtn(Locators_Data_Setting.Apnclickbck);
	}

	public void validate_Click_Cackbtn(SoftAssert sa) {
		try {
			if (Locators_Data_Setting.networksettingbar.isDisplayed()) {
				APP_LOGS.info("Navigate to Network Setting Screen Sucessfully");
				sa.assertTrue(true, " Navigate to Network Setting Screen Sucessfully");
				test.log(LogStatus.PASS, "Navigate to Network Setting Screen Sucessfully");
			} else {
				APP_LOGS.info("Failed to Navigate the Network Setting Screen ");
				sa.fail();
				test.log(LogStatus.PASS, " Failed to Navigate the Network Setting Screen");
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->validate_Click_Cackbtn()");
			e.printStackTrace();
		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exeption in ->validate_Click_Cackbtn()");
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

			if (Locators_Data_Setting.newapnnum.isDisplayed() && Locators_Data_Setting.apnsframe.isDisplayed()) {
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

	public void validate_Added_Max_APN(SoftAssert sa)

	{
		try {

			if (Locators_Data_Setting.sonim1.isDisplayed() && Locators_Data_Setting.sonim2.isDisplayed()
					&& Locators_Data_Setting.sonim3.isDisplayed() && Locators_Data_Setting.sonim4.isDisplayed()) {
				APP_LOGS.info("Maximum APN added successfully");
				sa.assertTrue(true, " Maximum APN added successfully");
				test.log(LogStatus.PASS, "Maximum APN added successfully");
			} else {
				APP_LOGS.info("Fail to added APN");
				sa.fail();
				test.log(LogStatus.PASS, "Fail to added APN");
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators --> validate_Added_Max_APN()");
			e.printStackTrace();

		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exception in -->validate_Added_Max_APN()");
			e.printStackTrace();
		}
	}

	public void add_APNName(String name) throws InterruptedException {
		try {
			clickBtn(Locators_Data_Setting.APNname);

			enterTextToInputField(Locators_Data_Setting.APNinput, name);
			clickBtn(Locators_Data_Setting.okbtn);
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators --> AddAPNName()");
			e.printStackTrace();

		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exception in -->AddAPNName()");
			e.printStackTrace();
		}

	}

	public void add_APNNo(String apn) throws InterruptedException {
		try {
			clickBtn(Locators_Data_Setting.APNno);

			enterTextToInputField(Locators_Data_Setting.APNinput, apn.substring(0, 3));
			webwait(Locators_Data_Setting.okbtn, 30);
			clickBtn(Locators_Data_Setting.okbtn);
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators --> AddAPNNo()");
			e.printStackTrace();

		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exception in -->AddAPNNo()");
			e.printStackTrace();
		}

	}

	public void editAPNnumber() throws InterruptedException, IOException {

		try {

			clickBtn(Locators_Data_Setting.newapnnum);

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
			if (isElementExist(Locators_Data_Setting.apnsframe) || Locators_Data_Setting.editapnnum.isDisplayed()) {
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
		try {
			if (!isElementExist(Locators_Data_Setting.editapnnum)) {
				APP_LOGS.info("APN deleted successfully");
				sa.assertTrue(true, "APN deleted successfully");
				test.log(LogStatus.PASS, "APN deleted successfully");

			} else {
				APP_LOGS.info("Fail to deleted APN");
				sa.fail();
				test.log(LogStatus.FAIL, "Fail to deleted APN");

			}
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->validate_deleted_APN()");
			e.printStackTrace();

		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exception in validate_deleted_APN()");
			e.printStackTrace();
		}
	}

	public void select_Authenticationtype(String names, SoftAssert sa) throws InterruptedException, IOException {
		try {
			scrollToText("Authentication type");

			java.lang.String atapn = Locators_Data_Setting.AuthenticationtypeofAPN.getText();

			System.out.println("name");

			switch (names) {

			case "None":
				clickBtn(Locators_Data_Setting.NoneAPNAT);
				APP_LOGS.info("None selected successfully");
				sa.assertTrue(true, "None selected successfully");
				test.log(LogStatus.PASS, "None selected successfully");
				break;
			case "PAP":
				clickBtn(Locators_Data_Setting.PAPAPNAT);
				APP_LOGS.info("PAP selected successfully");
				sa.assertTrue(true, "PAP selected successfully");
				test.log(LogStatus.PASS, "PAP selected successfully");
				break;

			case "CHAP":
				System.out.println("chap");
				clickBtn(Locators_Data_Setting.CHAPAPNAT);
				APP_LOGS.info("CHAP selected successfully");
				sa.assertTrue(true, "CHAP selected successfully");
				test.log(LogStatus.PASS, "CHAP selected successfully");
				break;
			case "PAPorCHAP":
				clickBtn(Locators_Data_Setting.PAPorCHAPPNAT);
				APP_LOGS.info("PAPorCHAP selected successfully");
				sa.assertTrue(true, "PAPorCHAP selected successfully");
				test.log(LogStatus.PASS, "PAPorCHAP selected successfully");
				break;

			}
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->select_Authenticationtype()");
			e.printStackTrace();

		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exception in select_Authenticationtype()");
			e.printStackTrace();
		}
	}

	public void validate_Authenticationtype(SoftAssert sa) {
		try {
//        	  minWait();
//		if (Locators_Data_Setting.NoneAPNAT1.isDisplayed()||isElementExist(Locators_Data_Setting.NoneAPNAT1)) {
//			APP_LOGS.info("None selected successfully");
//			sa.assertTrue(true, "None selected successfully");
//			test.log(LogStatus.PASS, "None selected successfully");
//		} else if (Locators_Data_Setting.PAPAPNAT1.isDisplayed()||isElementExist(Locators_Data_Setting.PAPAPNAT1)) {
//			APP_LOGS.info("PAP selected successfully");
//			sa.assertTrue(true, "PAP selected successfully");
//			test.log(LogStatus.PASS, "PAP selected successfully");
//		} else if (Locators_Data_Setting.CHAPAPNAT1.isDisplayed()||isElementExist(Locators_Data_Setting.CHAPAPNAT1)) {
//			APP_LOGS.info("CHAP selected successfully");
//			sa.assertTrue(true, "CHAP selected successfully");
//			test.log(LogStatus.PASS, "CHAP selected successfully");
//		}
//		else if (Locators_Data_Setting.PAPorCHAPPNAT1.isDisplayed()||isElementExist(Locators_Data_Setting.PAPorCHAPPNAT1)) {
//			APP_LOGS.info("PAPorCHAP selected successfully");
//			sa.assertTrue(true, "PAPorCHAP selected successfully");
//			test.log(LogStatus.PASS, "PAPorCHAP selected successfully");
//		}
			java.lang.String at = Locators_Data_Setting.Authentication_type.getText();
			System.out.println(at);
			if (at.equalsIgnoreCase("pap") || at.equalsIgnoreCase("none") || at.equalsIgnoreCase("chap")) {
				APP_LOGS.info("PAPorCHAP selected successfully");
				sa.assertTrue(true, "PAPorCHAP selected successfully");
				test.log(LogStatus.PASS, "PAPorCHAP selected successfully");
			} else {
				APP_LOGS.info("Failed to select the Authenticatication type");
				sa.fail();
				test.log(LogStatus.FAIL, "Failed to select the Authenticatication type");
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->validate_Authenticationtype()");
			e.printStackTrace();

		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exception in validate_Authenticationtype()");
			e.printStackTrace();
		}

	}

	public void select_APNFields(String name, String APN, String proxyapn, java.lang.String portAPN, String username,
			java.lang.String PasswordAPN, java.lang.String ServerAPN, String MMSCAPN)
			throws InterruptedException, IOException {

		try {
			java.lang.String name1 = Locators_Data_Setting.APNname.getText();
			System.out.println(name1);
			if (name1.equals("Name")) {
				add_APNName(name1);

				java.lang.String apnno = Locators_Data_Setting.APNno.getText();
				if (apnno.equals("APN")) {
					add_APNNo(APN);

				}

				java.lang.String proxy = Locators_Data_Setting.proxyAPN.getText();
				if (proxy.equals("Proxy")) {
					clickBtn(Locators_Data_Setting.proxyAPN);
					enterTextToInputField(Locators_Data_Setting.APNinput, proxyapn);
					clickBtn(Locators_Data_Setting.okbtn);

				}
				java.lang.String port = Locators_Data_Setting.portAPN.getText();
				if (port.equals("Port")) {
					clickBtn(Locators_Data_Setting.portAPN);
					enterTextToInputField(Locators_Data_Setting.APNinput, portAPN);
					clickBtn(Locators_Data_Setting.okbtn);

				}

				java.lang.String username1 = Locators_Data_Setting.usernameAPN.getText();
				if (username1.equals("Username")) {
					clickBtn(Locators_Data_Setting.usernameAPN);
					enterTextToInputField(Locators_Data_Setting.APNinput, username);
					clickBtn(Locators_Data_Setting.okbtn);

				}
				java.lang.String password = Locators_Data_Setting.PasswordAPN.getText();
				if (password.equals("Password")) {
					clickBtn(Locators_Data_Setting.PasswordAPN);
					enterTextToInputField(Locators_Data_Setting.APNinput, PasswordAPN);
					clickBtn(Locators_Data_Setting.okbtn);

				}
				java.lang.String server = Locators_Data_Setting.ServerAPN.getText();
				if (server.equals("Server")) {
					clickBtn(Locators_Data_Setting.ServerAPN);
					enterTextToInputField(Locators_Data_Setting.APNinput, ServerAPN);
					clickBtn(Locators_Data_Setting.okbtn);

				}
				java.lang.String mmsc = Locators_Data_Setting.MMSCAPN.getText();
				if (mmsc.equals("MMSC")) {
					clickBtn(Locators_Data_Setting.MMSCAPN);
					enterTextToInputField(Locators_Data_Setting.APNinput, MMSCAPN);
					clickBtn(Locators_Data_Setting.okbtn);

				}

			}

		} catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.ERROR, "Error in the locators->differentAPNField()");
			e.printStackTrace();

		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exception in --->differentAPNField()");
			e.printStackTrace();
		}
	}

	public void validate_differentField_In_APN(SoftAssert sa) {
		try {
			boolean name = Locators_Data_Setting.APNname.isDisplayed();
			boolean apn = Locators_Data_Setting.APNno.isDisplayed();
			boolean proxy = Locators_Data_Setting.proxyAPN.isDisplayed();
			boolean portapn = Locators_Data_Setting.portAPN.isDisplayed();
			boolean UN = Locators_Data_Setting.usernameAPN.isDisplayed();
			boolean PW = Locators_Data_Setting.PasswordAPN.isDisplayed();
			boolean server = Locators_Data_Setting.ServerAPN.isDisplayed();
			boolean MMSC = Locators_Data_Setting.MMSCAPN.isDisplayed();

			java.lang.String name1 = Locators_Data_Setting.APNname.getText();
			java.lang.String apn1 = Locators_Data_Setting.APNno.getText();
			java.lang.String proxy1 = Locators_Data_Setting.proxyAPN.getText();
			java.lang.String portapn1 = Locators_Data_Setting.portAPN.getText();
			java.lang.String UN1 = Locators_Data_Setting.usernameAPN.getText();
			java.lang.String PW1 = Locators_Data_Setting.PasswordAPN.getText();
			java.lang.String server1 = Locators_Data_Setting.ServerAPN.getText();
			java.lang.String MMSC1 = Locators_Data_Setting.MMSCAPN.getText();

			if (name && apn && proxy && portapn && UN && PW && server && MMSC) {
				APP_LOGS.info(name1 + "," + apn1 + "," + proxy1 + "," + portapn1 + "," + UN1 + "," + PW1 + "," + server1
						+ "and" + MMSC1 + " fields displayed sucessfully");
				sa.assertTrue(true, name1 + "," + apn1 + "," + proxy1 + "," + portapn1 + "," + UN1 + "," + PW1 + ","
						+ server1 + "," + MMSC1 + " fields displayed sucessfully");
				test.log(LogStatus.PASS, name1 + "," + apn1 + "," + proxy1 + "," + portapn1 + "," + UN1 + "," + PW1
						+ "," + server1 + "," + MMSC1 + " fields displayed sucessfully");
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.ERROR, "Error in the locators->validate_differentField_In_APN()");
			e.printStackTrace();

		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exception in --->validate_differentField_In_APN()");
			e.printStackTrace();
		}
	}

	public void save_APN() {
		try {
			clickBtn(Locators_Data_Setting.APNSettings);
			waituntilnew(Locators_Data_Setting.savebtn, 10);
			clickBtn(multi_Loc_Strategy(Locators_Data_Setting.savebtn, Locators_Data_Setting.savebtn1,
					Locators_Data_Setting.savebtn2, null, null, 0, 0));
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
			clickBtn(multi_Loc_Strategy(Locators_Data_Setting.APNSettings, Locators_Data_Setting.APNSettings1, null,
					null, null, 1013, 155));
			clickBtn(multi_Loc_Strategy(Locators_Data_Setting.discardbtn, Locators_Data_Setting.discardbtn1, null, null,
					null, 610, 302));
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
			waituntilnew(Locators_Data_Setting.apnsframe, 10);
			if (Locators_Data_Setting.apnsframe.isDisplayed() || isElementExist(Locators_Data_Setting.newapnnum)) {
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

	public void save_APN_without_Entering_APN_b(String erromsgdiplay, SoftAssert sa) throws InterruptedException {
		try {

			java.lang.String errormsg = Locators_Data_Setting.errormsginapn_b.getText();
			if (errormsg.equalsIgnoreCase(erromsgdiplay)) {

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

	public void Validate_APN_without_Entering_APN(String erromsgdiplay, SoftAssert sa) throws InterruptedException {
		try {

			java.lang.String errormsg = Locators_Data_Setting.errormsginapn.getText();
			if (errormsg.equalsIgnoreCase(erromsgdiplay)) {

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

			test.log(LogStatus.ERROR, "Error in the locators --> Validate_APN_without_Entering_APN()");
			e.printStackTrace();

		} catch (Exception e) {
			test.log(LogStatus.ERROR, "error msg is not displayed");
			e.printStackTrace();
		}

	}

	public void addapn_Without_Entering_Name_and_Apnno() throws InterruptedException, IOException {
		try {

			if (Locators_Data_Setting.errormsginname.isDisplayed()) {

			} else {
				APP_LOGS.info("error msg is not displayed");

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

	public void validate_error_Msg(SoftAssert sa) throws InterruptedException, IOException {
		try {
//			java.lang.String text = Locators_Data_Setting.errormsginname.getText();
//			if (text.equalsIgnoreCase(errormsg)) {	
			java.lang.String errormsg = Locators_Data_Setting.errormsginname1.getText();
			if (Locators_Data_Setting.errormsginname1.isDisplayed()) {

				System.out.println("error msg");
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

	public void lock_Unlock_Screen() throws InterruptedException, IOException {
		try {
			maxWait();
			launchAppThroughABD("adb shell input keyevent 26");
			minWait();
			launchAppThroughABD(" adb shell input keyevent 26");
			// launchAppThroughABD(" adb shell input keyevent 82");

		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators --> lock_Unlock_Screen()");
			e.printStackTrace();

		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exception in -->lock_Unlock_Screen()");
			e.printStackTrace();
		}
	}

	public void lock_Unlock_Screen_A() throws InterruptedException, IOException {
		try {

			launchAppThroughABD("adb shell input keyevent 26");
			launchAppThroughABD(" adb shell input keyevent 26");
			launchAppThroughABD(" adb shell input keyevent 82");

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
			if (Locators_Data_Setting.editapopt.isDisplayed()) {
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

	public void add_APN_Click_Backbutton() throws InterruptedException, IOException {
		try {

			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);

		} catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.ERROR, "Error in the locators->add_APN_Click_Backbutton()");
			e.printStackTrace();
		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Error in the locators->add_APN_Click_Backbutton()");
			e.printStackTrace();
		}
	}

	public void Validate_add_APN_Then_Click_Back(SoftAssert sa, String expectedtext) {
		try {
			if (isElementExist(Locators_Data_Setting.apnsframe) && Locators_Data_Setting.newapnnum1.isDisplayed()) {

				java.lang.String apnname = Locators_Data_Setting.newapnnum1.getText();
				if (apnname.equalsIgnoreCase(expectedtext)) {
					APP_LOGS.info(apnname + " added sucessfully in APNs Screen");
					sa.assertTrue(true, apnname + "APN added sucessfully in APNs Screen");
					test.log(LogStatus.PASS, apnname + "APN added sucessfully in APNs Screen");
				} else {
					APP_LOGS.info(apnname + " is failed to added  in the APNs Screen");
					sa.fail();
					test.log(LogStatus.FAIL, apnname + "Failed to APN added  in the APNs Screen");
				}
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.ERROR, "Error in the locators->Validate_add_APN_Then_Click_Back()");
			e.printStackTrace();
		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Error in the locators->Validate_add_APN_Then_Click_Back()");
			e.printStackTrace();
		}
	}

	public void enable_Disable_Airplane_Mode() {
		try {
			clickBtn(Locators_Data_Setting.networkandinternetopt);
			if (isElementExist(Locators_Data_Setting.airplanemode)) {

			}
		} catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.ERROR, "Error in the locators->add_APN_Click_Backbutton()");
			e.printStackTrace();
		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Error in the locators->add_APN_Click_Backbutton()");
			e.printStackTrace();
		}
	}

	public void Turn_On_Off_Airplane_Mode(int number, SoftAssert sa) throws InterruptedException {

		try {
			for (int i = 0; i < number; i++) {
				String text=Locators_Data_Setting.airplanemodeswitchonoff.getText();
					if (text.equalsIgnoreCase("ON")) {
						APP_LOGS.info("Airplane mode ON successfully");
						sa.fail();
						test.log(LogStatus.FAIL, "Airplane mode ON successfully");
						clickBtn(Locators_Data_Setting.airplanemodeswitchonoff);
						if(wait(Locators_Data_Setting.OK, 5));{
							clickBtn(Locators_Data_Setting.OK);
						}
					} else {
						APP_LOGS.info("Airplane mode is in OFF state");
						sa.assertTrue(true, "Airplane mode is in OFF state");
						test.log(LogStatus.PASS, "Airplane mode is in OFF state");
					}
				

			}
		} catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.ERROR, "Error in the locators-> Turn_On_Off_Airplane_Mode()");
			e.printStackTrace();
		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exeption occurs in functionality -> Turn_On_Off_Airplane_Mode()");
			e.printStackTrace();
		}
	}

	public void Turn_On_Off_Airplane_Mode_v(int number, SoftAssert sa) throws InterruptedException {

		try {
			for (int i = 0; i < number; i++) {

				clickBtn(Locators_Data_Setting.airplanemodeswitchonoff);
				if (Locators_Data_Setting.okbtn.isDisplayed()) {
					clickBtn(Locators_Data_Setting.okbtn);
				}
				webwait(Locators_Data_Setting.Mobilenetworkopt, 30);
				if (Locators_Data_Setting.Mobilenetworkopt.isDisplayed()) {
					clickBtn(Locators_Data_Setting.mobiledatarbtn);
					webwait(Locators_Data_Setting.mobiledatarbtn, 30);
					java.lang.String text = Locators_Data_Setting.mobiledatarbtn.getText();
					if (text.equalsIgnoreCase("ON")) {
						APP_LOGS.info("Airplane mode is in OFF state");
						sa.fail();
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

			}
		} catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.ERROR, "Error in the locators->on_Off_Airoplane_Mode()");
			e.printStackTrace();
		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exeption occurs in  on_Off_Airoplane_Mode()");
			e.printStackTrace();
		}
	}

	public void select_APN_protocol(String Protocolname, SoftAssert sa) throws InterruptedException, IOException {
		try {

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

	public void select_Bearer() throws InterruptedException {
		try {

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
				APP_LOGS.info("Bearer options selected successfully");
				sa.assertTrue(true, "Bearer options selected successfully");
				test.log(LogStatus.PASS, "Bearer options selected successfully");
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

	public boolean enabled_Data_Access(SoftAssert sa) {
		boolean enabled = false;
		try {
			clickBtn(Locators_Data_Setting.networkandinternetopt);
			clickBtn(Locators_Data_Setting.Mobilenetworkopt);
			if (Locators_Data_Setting.mobiledata.isDisplayed()) {
				if (Locators_Data_Setting.mobiledatarbtn.getText().equalsIgnoreCase("ON")) {
					enabled = true;

					System.out.println("ON");
					APP_LOGS.info("Mobile data Turn ON successfully");
					sa.assertTrue(true, "Mobile data Turn ON successfully");
					test.log(LogStatus.PASS, "Mobile data Turn ONsuccessfully");
				}

				else if (Locators_Data_Setting.mobiledatarbtn.getText().equals("OFF")) {
					clickBtn(Locators_Data_Setting.mobiledatarbtn);
					APP_LOGS.info("Mobile data Turn ON successfully");
					sa.assertTrue(true, "Mobile data Turn ON successfully");
					test.log(LogStatus.PASS, "Mobile data Turn ON successfully");
					enabled = false;
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
		return enabled;
	}

	public void disable_airplane_Mode(SoftAssert sa) {
		try {
			clickBtn(Locators_Data_Setting.networkandinternetopt);
			clickBtn(Locators_Data_Setting.Mobilenetworkopt);
			if (Locators_Data_Setting.mobiledata.isDisplayed()) {
				if (Locators_Data_Setting.mobiledatarbtn.getText().equals("ON")) {

					System.out.println("ON");
					APP_LOGS.info("Mobile data Turn ON successfully");
					sa.assertTrue(true, "Mobile data Turn ON successfully");
					test.log(LogStatus.PASS, "Mobile data Turn ONsuccessfully");
				}

				else if (Locators_Data_Setting.mobiledatarbtn.getText().equals("OFF")) {
					clickBtn(Locators_Data_Setting.mobiledatarbtn);
					APP_LOGS.info("Mobile data Turn ON successfully");
					sa.assertTrue(true, "Mobile data Turn ON successfully");
					test.log(LogStatus.PASS, "Mobile data Turn ON successfully");

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
			String url = "https://www.google.com";
			clickBtn(Locators_Data_Setting.googletextfield);
			Runtime.getRuntime()
					.exec("adb -s " + p_Id + " shell am start -a android.intent.action.VIEW -d http:/" + url);

			aDriver.pressKeyCode(AndroidKeyCode.ENTER);
			minWait();
			if (isElementExist(Locators_Data_Setting.nointernet)) {

				APP_LOGS.info("Failed to Launch the browser ");
				sa.assertTrue(true, "Failed to Launch the browser ");
				test.log(LogStatus.PASS, "Failed to Launch the browser");

			}

			else {

				APP_LOGS.info("Launch the browser sucessfully");
				sa.fail();
				test.log(LogStatus.FAIL, "Launch the browser sucessfully");
			}

		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->open_Browser(()");
			e.printStackTrace();

		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exeption in ->open_Browser()");
		}
	}

	public void enterurl() throws InterruptedException, IOException {

		try {

			customWait(2000);
			if (isElementExist(Locators_Data_Setting.google_savedDataOpt)) {

				clickBtn(Locators_Data_Setting.google_savedDataOpt_OkBtn);
			}

			minWait();
			
			String url = "https://www.facebook.com";
			wait(Locators_Data_Setting.chromeSearch, 20);
			enterTextToInputField(Locators_Data_Setting.chromeSearch, url);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_ENTER);
//clickBtn(multi_Loc_Strategy(Locators_DeviceStability.OK, Locators_DeviceStability.OKBtn1, Locators_DeviceStability.OKBtn2, Locators_DeviceStability.OKBtn3, Locators_DeviceStability.OKBtnopt1, 889, 1104));
			if (isElementExist(Locators_Data_Setting.google_savedDataOpt)) {

				clickBtn(Locators_Data_Setting.google_savedDataOpt_OkBtn);
			}
			boolean noInternet = wait(Locators_Data_Setting.noInternet,20);
		
		}catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->open_Browser(()");
			e.printStackTrace();

		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exeption in ->open_Browser()");
		}
	}

	public void enable_Airplane_Mode() {
		try {
			scrollTo("Airplane mode");
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

	public void enable_Airplane_Mode_O() {
		try {
			scrollTo("Airplane mode");
			if (isElementExist(Locators_Data_Setting.airplanemode)) {

				if (Locators_Data_Setting.airplanemodeswitchonoff.getText().equals("ON")) {
					if (Locators_Data_Setting.okbtn.isDisplayed()) {
						clickBtn(Locators_Data_Setting.okbtn);
					}
				}

				else {
					clickBtn(Locators_Data_Setting.airplanemodeswitchonoff);
					if (Locators_Data_Setting.okbtn.isDisplayed()) {
						clickBtn(Locators_Data_Setting.okbtn);
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

	public void validate_Mobile_network(SoftAssert sa) {
		try {

			scrollToText("Mobile network");
			if (isElementExist(Locators_Data_Setting.networksettingbar)) {
				APP_LOGS.info("Mobile network option displayed sucessfully");
				sa.fail();
				test.log(LogStatus.PASS, "Mobile network option displayed sucessfully");
			} else {
				APP_LOGS.info("Mobile network disabled sucessfully");
				sa.assertTrue(true, "Mobile network disabled sucessfully");
				test.log(LogStatus.PASS, "Mobile network disabled sucessfully");

			}

		} catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.ERROR, "Error in the locators->validate_Mobile_network()");
			e.printStackTrace();
		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Error in the locators->validate_Mobile_network()");
			e.printStackTrace();
		}
	}

	public void disable_Airplane_Mode() {
		try {
			Runtime.getRuntime().exec("adb -s " + p_Id + " shell am start -a android.settings.AIRPLANE_MODE_SETTINGS");
			scrollTo("Airplane mode");

			if (Locators_Data_Setting.airplanemodeswitchonoff.getText().equalsIgnoreCase("ON")) {
				clickBtn(Locators_Data_Setting.airplanemodeswitchonoff);

			}

		} catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.ERROR, "Error in the locators->disable_Airplane_Mode()");
			e.printStackTrace();
		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Error in the locators->disable_Airplane_Mode()");
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

	public boolean enable_data() {
		boolean enable = false;
		try {
			scrollToText("Mobile network");

			if (Locators_Data_Setting.mobiledata.isDisplayed()) {
				if (Locators_Data_Setting.mobiledatarbtn.getText().equalsIgnoreCase("ON")) {
					enable = true;
				}

				else {
					clickBtn(Locators_Data_Setting.mobiledatarbtn);
					enable = false;
				}
			}

		} catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.ERROR, "Error in the locators->enable_data()");
			e.printStackTrace();
		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Error in the locators->enable_data()");
			e.printStackTrace();
		}
		return enable;
	}

	public void enable_data1() {

		try {

			if (Locators_Data_Setting.mobiledata.isDisplayed()) {
				if (Locators_Data_Setting.mobiledatarbtn.getText().equalsIgnoreCase("ON")) {
				}

				else {
					clickBtn(Locators_Data_Setting.mobiledatarbtn);

				}

			}

		} catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.ERROR, "Error in the locators->enable_data1()");
			e.printStackTrace();
		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Error in the locators->enable_data1()");
			e.printStackTrace();
		}

	}

	public void validate_enablemobile_data(SoftAssert sa) {
		try {
			if (Locators_Data_Setting.mobiledata.isDisplayed()) {
				java.lang.String dataon = Locators_Data_Setting.mobiledatarbtn.getText();
				if (dataon.equalsIgnoreCase("on")) {

					APP_LOGS.info("Mobile data Turn ON successfully");
					sa.assertTrue(true, "Mobile data Turn ON successfully");
					test.log(LogStatus.PASS, "Mobile data Turn ON successfully");
				}

				else {
					// clickBtn(Locators_Data_Setting.mobiledatarbtn);
					APP_LOGS.info("Mobile data Turn ON successfully");
					sa.fail();
					test.log(LogStatus.PASS, "Mobile data Turn ON successfully");

				}
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.ERROR, "Error in the locators->validate_enablemobile_data()");
			e.printStackTrace();
		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Error in the locators->validate_enablemobile_data()");
			e.printStackTrace();
		}
	}

	public void validate_enablemobile_data_And_Receive_Call(SoftAssert sa) {
		try {
			if (Locators_Data_Setting.mobiledata.isDisplayed()) {
				java.lang.String dataon = Locators_Data_Setting.mobiledatarbtn.getText();
				if (dataon.equalsIgnoreCase("on")) {

					APP_LOGS.info("Mobile data Turn ON successfully while Receiving a call");
					sa.assertTrue(true, "Mobile data Turn ON successfully while Receiving a call");
					test.log(LogStatus.PASS, "Mobile data Turn ON successfully while Receiving a call");
				}

				else {
					// clickBtn(Locators_Data_Setting.mobiledatarbtn);
					APP_LOGS.info("Mobile data Turn ON successfully");
					sa.fail();
					test.log(LogStatus.PASS, "Mobile data Turn ON successfully");

				}
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.ERROR, "Error in the locators->validate_enablemobile_data()");
			e.printStackTrace();
		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Error in the locators->validate_enablemobile_data()");
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

			clickBtn(Locators_Data_Setting.Advancedopt);
			scrollTo("Enhanced 4G LTE services");
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->verify_LTEserviceopt()");
			e.printStackTrace();

		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exception error in verify_LTEserviceopt()");
		}

	}

	public void enable_Disable_LTEservices() {
		try {
			if (isElementExist(Locators_Data_Setting.lteservice) && Locators_Data_Setting.lte_radiobtn1.isDisplayed()) {
				for (int i = 1; i < 2; i++) {
					clickBtn(Locators_Data_Setting.lteservice);
					java.lang.String ltetext = Locators_Data_Setting.lte_radiobtn1.getText();
					System.out.println(ltetext);
					if (ltetext.equalsIgnoreCase("ON")) {
						System.out.println("on");
					}
				}
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->enable_Disable_LTEservices()");
			e.printStackTrace();

		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exception error in enable_Disable_LTEservices()");
		}

	}

	public void Disable_LTEservices() {
		try {
			clickBtn(Locators_Data_Setting.Advancedopt);
			scrollTo("Enhanced 4G LTE services");

			if (isElementExist(Locators_Data_Setting.lteservice) && Locators_Data_Setting.lte_radiobtn.isDisplayed()) {

				java.lang.String ltetext = Locators_Data_Setting.lte_radiobtn.getText();
				System.out.println(ltetext);
				if (ltetext.equalsIgnoreCase("ON")) {
					clickBtn(Locators_Data_Setting.lteservice);
				}
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->enable_Disable_LTEservices()");
			e.printStackTrace();

		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exception error in enable_Disable_LTEservices()");
		}

	}

	public void validate_Enable_Disable_LTEservices(SoftAssert sa) {
		try {

			java.lang.String ltetext = Locators_Data_Setting.lte_radiobtn1.getText();
			System.out.println(ltetext);
			if (ltetext.equalsIgnoreCase("ON")) {
				APP_LOGS.info("Enabled LTE Mode sucessfully");
				sa.assertTrue(true, "Enabled LTE Mode sucessfully");
				test.log(LogStatus.PASS, "Enabled LTE Mode sucessfully");
			} else {
				APP_LOGS.info("Disabled LTE Mode Sucessfully");
				sa.fail();
				test.log(LogStatus.FAIL, "Disabled LTE Mode Sucessfully");

			}

		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->enable_Disable_LTEservices()");
			e.printStackTrace();

		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exception error in enable_Disable_LTEservices()");
		}
	}

	public void Validate_LTEservice(SoftAssert sa) {
		try {
			if (p_b_No.contains("-10.") || p_b_No.contains("-00.")) {
				if (Locators_Data_Setting.lteservice.isDisplayed()) {
					APP_LOGS.info("LTEservice is present inside Network Settings");
					sa.assertTrue(true, "LTEservice is present inside Network Settings");
					test.log(LogStatus.PASS, "LTEservice is present inside Network Settings");
				} else {
					APP_LOGS.info("LTEservice is not present inside Network Settings");
					sa.fail();
					test.log(LogStatus.FAIL, "LTEservice is Not present inside Network Settings");
				}
			} else if (p_b_No.contains("-11.") || p_b_No.contains("-29.")) {
				if (Locators_Data_Setting.lteservice.isDisplayed()) {
					APP_LOGS.info("LTEservice is present inside Network Settings");
					sa.assertTrue(true, "LTEservice is present inside Network Settings");
					test.log(LogStatus.PASS, "LTEservice is present inside Network Settings");
				} else {
					APP_LOGS.info("LTEservice is not present inside Network Settings");
					sa.assertTrue(false, "LTEservice is not present inside Network Settings");
					test.log(LogStatus.FAIL, "LTEservice is Not present inside Network Settings");
				}
			}

		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->Validate_LTEservice()");
			e.printStackTrace();

		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exception error in Validate_LTEservice()");
		}

	}

	public void Validate_LTEservice_s(SoftAssert sa) {
		try {
			if (!isElementExist(Locators_Data_Setting.lteservice)) {
				APP_LOGS.info("LTEservice is not present inside Network Settings");
				sa.assertTrue(true, "LTEservice is not present inside Network Settings");
				test.log(LogStatus.PASS, "LTEservice is not present inside Network Settings");
			} else {
				APP_LOGS.info("LTEservice is  present inside Network Settings");
				sa.fail();
				test.log(LogStatus.FAIL, "LTEservice is  present inside Network Settings");
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
				if (Locators_Data_Setting.apnname.getText().equals(apnname)) {
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

				APP_LOGS.info(" APN displayed and selected Successfully");
				sa.assertTrue(true, "  APN displayed and selected Successfully");
				test.log(LogStatus.PASS, "  APN displayed and selected Successfully");
			} else {
				APP_LOGS.info("APN is not Displayed");
				sa.fail();
				test.log(LogStatus.FAIL, " APN is not Displayed");
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
					APP_LOGS.info("Successfully APN added to the APNs List");
					sa.assertTrue(true, "Successfully APN added to the APNs List");
					test.log(LogStatus.PASS, "Successfully APN added to the APNs List");
				} else {
					APP_LOGS.info("Failed to added APN");
					sa.fail();
					test.log(LogStatus.FAIL, "Failed to added APN");
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
				if (isElementExist(Locators_Data_Setting.newapnnum1)) {
					APP_LOGS.info("Failed to discard APN");
					sa.fail();
					test.log(LogStatus.FAIL, "Failed to discard APN");
				} else {
					APP_LOGS.info("APN discarded successfully");
					sa.assertTrue(true, "APN discarded successfully");
					test.log(LogStatus.PASS, "APN discarded successfully");
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

	public void make_Call_from_RefDev() throws InterruptedException, IOException {
		// make a call from ref device to primary device
		try {
			Runtime.getRuntime()
					.exec("adb -s " + r_Id + " shell am start -a android.intent.action.CALL -d tel:" + pryNum);
			minWait();
		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exeption in ->make_Call_from_RefDev()");

		}
	}

	public void endCall_RefDevice() {
		try {
			minWait();
			Runtime.getRuntime().exec("adb -s " + r_Id + " shell input keyevent 6");
			Thread.sleep(1000);
		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exeption in ->endCall_RefDevice()");

		}
	}

	public void reciveCallInRefDevice(String refNum) throws InterruptedException, IOException {
		/*
		 * Receive Call in Reference device
		 */

		if (!isElementExist(Locators_XP8_Sanity.turnOff_Airplane_PopUp)) {
			try {

				while (true) {

					Process child = null;
					if (r_b_No.contains("8A.")) {
						child = Runtime.getRuntime().exec("adb -s " + p_Id + " shell service call telecom 27");
					} else if (r_b_No.contains("5SA.")) {
						child = Runtime.getRuntime().exec("adb -s " + p_Id + " shell service call telecom 28");
					}
					InputStream inputStream = child.getInputStream();
					InputStreamReader isr = new InputStreamReader(inputStream);
					BufferedReader bf = new BufferedReader(isr);
					String value = bf.readLine();

					if (value.contains("00000001")) {
						System.out.println("Phone is ringing so accepting call.");

						Runtime.getRuntime().exec("adb -s " + r_Id + " shell input keyevent 5");
						break;
					} else {
						continue;
					}
				}

			} catch (Exception e) {
				Thread.sleep(2000);
				Runtime.getRuntime().exec("adb -s " + r_Id + " shell input keyevent 5");

			}
		}
	}

	public void receiveCallInpriDevice() throws InterruptedException, IOException {
		/*
		 * Receive Call in Reference device
		 */

		if (!isElementExist(Locators_XP8_Sanity.turnOff_Airplane_PopUp)) {
			try {

				while (true) {

					Process child = null;
					if (p_b_No.contains("8A.")) {
						child = Runtime.getRuntime().exec("adb -s " + p_Id + " shell service call telecom 29");
					} else if (p_b_No.contains("5SA.")) {
						child = Runtime.getRuntime().exec("adb -s " + p_Id + " shell service call telecom 28");
					}
					InputStream inputStream = child.getInputStream();
					InputStreamReader isr = new InputStreamReader(inputStream);
					BufferedReader bf = new BufferedReader(isr);
					String value = bf.readLine();

					if (value.contains("00000001")) {

						Runtime.getRuntime().exec("adb -s " + p_Id + " shell input keyevent 5");
						break;
					} else {
						continue;
					}
				}

			}

			catch (Exception e) {
				Thread.sleep(2000);
				Runtime.getRuntime().exec("adb -s " + p_Id + " shell input keyevent 5");

			}
		}
	}

	public void validate_Receive_Call_In_PriDev(SoftAssert sa) throws IOException, InterruptedException {
		try {
			if (Locators_Data_Setting.APNno.isDisplayed()) {
				APP_LOGS.info(" Successfully received a call while adding APN");
				sa.assertTrue(true, " Successfully received a call while adding APN");
				test.log(LogStatus.PASS, " Successfully received a call while adding APN");
			} else {
				APP_LOGS.info(" Failed to receive a call while adding APN");
				sa.fail();
				test.log(LogStatus.FAIL, " Failed to receive a call while adding APN");
			}
		} catch (Exception e) {
			Thread.sleep(2000);
			Runtime.getRuntime().exec("adb -s " + p_Id + " shell input keyevent 5");

		}
	}

	public void getNotificationWindow() {
		try {
			aDriver.pressKeyCode(AndroidKeyCode.HOME);
			minWait();
			System.out.println("hi");
			Runtime r = Runtime.getRuntime();
			r.exec("adb shell input swipe 10 10 10 1000");
			minWait();
			r.exec("adb shell input swipe 10 10 10 1000");
			minWait();
		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Cannot interract with command Prompt");
		}

	}

	public void enable_Wifi() {
		try {
			clickBtn(Locators_Data_Setting.wifi);
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators --> enable_Wifi()");
			e.printStackTrace();

		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exception in -->enable_Wifi()");
			e.printStackTrace();
		}

	}
//	
//	public void enable_Wifi(String password) throws InterruptedException
//	{
//		clickBtn(Locators_Data_Setting.networkandinternetopt);
//		clickBtn(Locators_Data_Setting.wifi);
//		
//		if(!Locators_Data_Setting.wifioff.getText().equals("OFF"))
//		{
//			minWait();
//			clickBtn(Locators_Data_Setting.wifi1);
//			enterTextToInputField(Locators_Data_Setting.wifitextfield, password);
//			clickBtn(Locators_Data_Setting.wificonnect);
//		}	
//		else
//		{
//			clickBtn(Locators_Data_Setting.wifioff);
//			clickBtn(Locators_Data_Setting.wifi1);
//			enterTextToInputField(Locators_Data_Setting.wifitextfield, password);
//			clickBtn(Locators_Data_Setting.wificonnect);
//		}
//		
//	}

//	public void validate_Wifi_Connection(SoftAssert sa)
//	{
//		try {
//			if (Locators_Data_Setting.wififrame.isDisplayed()) {
//				
//					java.lang.String text = Locators_Data_Setting.wifitext.getText();
//					APP_LOGS.info(text+"Received a call successfully while Connecting wifi");
//					sa.assertTrue(true, test+"Received a call successfully while Connecting wifi");
//					test.log(LogStatus.PASS, text+"Received a call successfully while Connecting wifi");
//			}	
//				else {
//					APP_LOGS.info("Failed to connect a call");
//					sa.fail();
//					test.log(LogStatus.PASS, "failed to connect a call");
//				}
//			
//		} catch (org.openqa.selenium.NoSuchElementException e) {
//
//			test.log(LogStatus.ERROR, "Error in the locators --> validate_Wifi_Connection()");
//			e.printStackTrace();
//
//		} catch (Exception e) {
//			test.log(LogStatus.ERROR, "Exception in -->validate_Wifi_Connection()");
//			e.printStackTrace();
//		}
//	}
//
//	public void enable_Wifi_And_ReceiveCall() throws InterruptedException
//	{
//		clickBtn(Locators_Data_Setting.networkandinternetopt);
//		
//		clickBtn(Locators_Data_Setting.wifi);
//
//		if(!Locators_Data_Setting.wifioff.getText().equals("OFF"))
//		{
//
//			
//			
//
//		}	
//		else
//		{
//			clickBtn(Locators_Data_Setting.wifioff);
//
//			
//		}
//		
//	}
//	
//	
	public void clickOn_clock(String appName) {

		try {
			customWait(1000);
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_HOME);
			APP_LOGS.info("HOme PAge");
			customWait(2000);
			// Locators_BaseUtil.AppListIcon.click();
			customWait(1000);
			switch (appName) {
			case "clock":
				scrollToElements(Locators_Data_Setting.clock);
				// clickBtn(Locators_Data_Setting.maps);

				APP_LOGS.info("Clicked on Chrome successfully.");

				break;
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->clickOn_clock()");
			e.printStackTrace();

		} catch (Exception e) {

			test.log(LogStatus.ERROR, "Exeption in ->clickOn_clock()");
		}
	}

	public void set_Alram() throws InterruptedException {
		aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_HOME);
		customWait(3000);
		Locators_BaseUtil.AppListIcon.click();
		clickBtn(Locators_Data_Setting.google);
		clickBtn(Locators_Data_Setting.clicktextfield);
		minWait();
		enterTextToInputField(Locators_Data_Setting.search, "set alaram for 1 minute");
		aDriver.longPressKeyCode(AndroidKeyCode.ENTER);
		clickBtn(Locators_Data_Setting.googlealarm);

	}

	public void clickOn_App1(String appName) {

		try {
			customWait(1000);
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_HOME);
			APP_LOGS.info("HOme PAge");
			customWait(2000);
			Locators_BaseUtil.AppListIcon.click();
			customWait(1000);
			switch (appName) {
			case "clock":
				scrollToElements(Locators_Data_Setting.clock);
				clickBtn(Locators_Data_Setting.clock);
				APP_LOGS.info("Clicked on Chrome successfully.");

				break;
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->clickOn_App()");
			e.printStackTrace();

		} catch (Exception e) {

			test.log(LogStatus.ERROR, "Exeption in ->clickOn_App()");
		}
	}

	public void add_account_To_Gmail() throws InterruptedException {
//		clickBtn(Locators_Data_Setting.menu);
//		clickBtn(Locators_Data_Setting.social);
		clickBtn(Locators_Data_Setting.gmenu);
		clickBtn(Locators_Data_Setting.addaccount);
		clickBtn(Locators_Data_Setting.google);
		webwait(Locators_Data_Setting.email, 30);
		enterTextToInputField(Locators_Data_Setting.email, "janardhansonim123@gmail.com");
		clickBtn(Locators_Data_Setting.next);
		enterTextToInputField(Locators_Data_Setting.password, "jana!9738447563");
		clickBtn(Locators_Data_Setting.next);
		webwait(Locators_Data_Setting.iagree, 30);
		clickBtn(Locators_Data_Setting.iagree);
		webwait(Locators_Data_Setting.gmenu, 30);
		clickBtn(Locators_Data_Setting.gmenu);
	}

	public void validate_add_Gmailname(SoftAssert sa) {
		try {
			if (Locators_Data_Setting.jana.isDisplayed()) {
				APP_LOGS.info(" Gmail account added Successfully ");
				sa.assertTrue(true, " Gmail account added Successfully");
				test.log(LogStatus.PASS, " Gmail account added Successfully");
			} else {
				APP_LOGS.info(" Failed to add the account");
				sa.fail();
				test.log(LogStatus.PASS, "Failed to add the account");
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->validate_add_Gmailname()");
			e.printStackTrace();

		} catch (Exception e) {

			test.log(LogStatus.ERROR, "Exeption in ->validate_add_Gmailname()");
		}
	}

	////// wifi

	public void wifi_enable() {
		try {
			clickBtn(Locators_Data_Setting.networkandinternetopt);
			clickBtn(Locators_Data_Setting.wifiradiobtn);
			java.lang.String wifitext = Locators_Data_Setting.turnoff_on_wifi.getText();
			if (wifitext.equalsIgnoreCase("ON")) {

			} else {
				clickBtn(Locators_Data_Setting.turnoff_on_wifi);
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.ERROR, "Error in the locators->wifi_enable()");
			e.printStackTrace();
		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exeption in ->wifi_enable()");
			e.printStackTrace();
		}

	}

	public void validate_FBApplication(SoftAssert sa) throws InterruptedException, IOException {
		try {

			clickBtn(Locators_Data_Setting.googletextfield);
			enterTextToInputField(Locators_Data_Setting.textfieldinchrome, "https://www.facebook.com");

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

	public void music() {
		try {
			enterTextToInputField(Locators_Data_Setting.textfieldinchrome, "https://wynk.in");
			aDriver.pressKeyCode(AndroidKeyCode.ENTER);
			minWait();
			clickBtn(Locators_Data_Setting.musicxpath);
			minWait();

		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->open_Browser(()");
			e.printStackTrace();

		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exeption in ->open_Browser()");
		}
	}

	public void clickOn_Map(String appName) {

		try {
			customWait(1000);
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_HOME);
			APP_LOGS.info("HOme PAge");
			customWait(2000);
			Locators_BaseUtil.AppListIcon.click();
			customWait(1000);
			switch (appName) {
			case "map":
				scrollToElements(Locators_Data_Setting.maps);
				clickBtn(Locators_Data_Setting.maps);

				APP_LOGS.info("Clicked on Chrome successfully.");

				break;
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->clickOn_App()");
			e.printStackTrace();

		} catch (Exception e) {

			test.log(LogStatus.ERROR, "Exeption in ->clickOn_App()");
		}
	}

	public void clickOn_Youtube(String appName) {

		try {
			customWait(1000);
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_HOME);
			APP_LOGS.info("HOme PAge");
			customWait(2000);
			// Locators_BaseUtil.AppListIcon.click();
			customWait(1000);
			switch (appName) {
			case "youtube":
				scrollToElements(Locators_Data_Setting.youtube);
				// clickBtn(Locators_Data_Setting.youtubesearchbtn);

				APP_LOGS.info("Clicked on Chrome successfully.");

				break;
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->clickOn_App()");
			e.printStackTrace();

		} catch (Exception e) {

			test.log(LogStatus.ERROR, "Exeption in ->clickOn_App()");
		}
	}

	public void validate_Youtube(SoftAssert sa) {
		try {
			clickBtn(Locators_Data_Setting.youtubesearchbtn);
			enterTextToInputField(Locators_Data_Setting.searchyoutubeenter, "sonim ");
			if (Locators_Data_Setting.xp8.isDisplayed()) {
				APP_LOGS.info(" launch the vedio sucessfully  ");
				sa.assertTrue(true, " launch the vedio sucessfully ");
				test.log(LogStatus.PASS, "launch the vedio sucessfully ");
			} else {
				APP_LOGS.info(" failed to launch the vedio  ");
				sa.fail();
				test.log(LogStatus.PASS, "Failed to launch the vedio ");
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->validate_Youtube()");
			e.printStackTrace();

		} catch (Exception e) {

			test.log(LogStatus.ERROR, "Exeption in ->validate_Youtube()");
		}
	}

	public void validate_google_Map(SoftAssert sa) {
		try {
			if (isElementExist(Locators_Data_Setting.netwkerr)) {
				APP_LOGS.info(" Failed to navigate the direction");
				sa.assertTrue(true, "Failed to navigate the direction");
				test.log(LogStatus.PASS, "Failed to navigate the direction");
			}

			else {
				APP_LOGS.info(" Successfully Navigate the direction");
				sa.assertTrue(true, " Successfully Navigate the direction");
				test.log(LogStatus.PASS, " Successfully Navigate the direction");

			}
		} catch (Exception e) {
			// TODO: handle exception

			e.printStackTrace();
		}
	}

	public void Gmail() {
		try {
			clickBtn(Locators_Data_Setting.gmailmenu);
			clickBtn(Locators_Data_Setting.starred);
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->clickOn_App()");
			e.printStackTrace();

		} catch (Exception e) {

			test.log(LogStatus.ERROR, "Exeption in ->clickOn_App()");
		}
	}

	public void validate_Gmail(SoftAssert sa) {
		try {
			if (Locators_Data_Setting.starmsg.isDisplayed()) {
				APP_LOGS.info("Failed to launch the gmail application");
				sa.fail();
				test.log(LogStatus.PASS, "Failed to launch the gmail application");
			} else {
				APP_LOGS.info("Successfully launch the gmail application");
				sa.assertTrue(true, "Successfully launch the gmail application");
				test.log(LogStatus.PASS, "Successfully launch the gmail application");
			}
		} catch (Exception e) {
			// TODO: handle exception

			e.printStackTrace();
		}
	}

	public void wifi_Frame() {
		clickBtn(Locators_Data_Setting.wififrameN1);
	}

	public void validate_Wifi_Frame(SoftAssert sa) {
		if (Locators_Data_Setting.wifi.isDisplayed() && Locators_Data_Setting.framever.isDisplayed()) {
			APP_LOGS.info("wifi text displayed Sucessfully");
			sa.assertTrue(true, "wifi text displayed Sucessfully");
			test.log(LogStatus.PASS, "wifi text displayed Sucessfully");
		} else {
			APP_LOGS.info("Failed to display");
			sa.fail();
			test.log(LogStatus.PASS, "Failed to display");
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
			enableSwitch(Locators_Data_Setting.enabled_Airplane, Locators_Data_Setting.disabled_Airplane,
					Locators_Data_Setting.switch_widget);
			customWait(5000);
			// clickBtn(Locators_DeviceStability.OKBtn);
			minWait();
			disableSwitch(Locators_Data_Setting.disabled_Airplane, Locators_Data_Setting.enabled_Airplane,
					Locators_Data_Setting.switch_widget);
			customWait(15000);
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void navigate_To_NetworkandInternet() {
		try {
			scrollToText("Network & Internet");
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->navigate_To_NetworkandInternet()");
			e.printStackTrace();

		} catch (Exception e) {

			test.log(LogStatus.ERROR, "Exeption in ->navigate_To_NetworkandInternet()");
		}
	}

	public void navigate_To_MobileNetwork() {
		try {
			scrollToText("Mobile network");
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->navigate_To_MobileNetwork()");
			e.printStackTrace();

		} catch (Exception e) {

			test.log(LogStatus.ERROR, "Exeption in ->navigate_To_MobileNetwork()");
		}
	}

	public void validate_ADB_Logs(String filename, String string1, String string2, String string3)
			throws InterruptedException {

		customWait(2000);
		boolean check1 = searchString(string1, filename);
		boolean check2 = searchString(string2, filename);
		boolean check3 = searchString(string3, filename);
		SoftAssert sf = new SoftAssert();
		if (check1 && check2 && check3) {
			check = true;
			sf.assertTrue(check, "TestCase Valiation is PASS");
			test.log(LogStatus.PASS, "Test case Status is Pass.");
			test.log(LogStatus.INFO, "Validated from ADB Logs : " + string1 + ", " + string2 + ", " + string3);
		} else {
			sf.fail();
			test.log(LogStatus.FAIL, "Test Case Status is Fail.");
			test.log(LogStatus.INFO, "Validation failed from ADB Logs.");
		}
		sf.assertAll();
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

	public void clearChromePermission() {
		try {
			System.out.println("In Browser");

			if (isElementExist(Locators_Data_Setting.OK)) {
				clickBtn(Locators_Data_Setting.OK);
			}
			if (isElementExist(Locators_Data_Setting.Accept_id)) {
				clickBtn(Locators_Data_Setting.Accept_id);
			}
			if (isElementExist(Locators_Data_Setting.NEXT)) {
				clickBtn(Locators_Data_Setting.NEXT);
			}
			if (isElementExist(Locators_Data_Setting.NO_THANKS)) {
				clickBtn(Locators_Data_Setting.NO_THANKS);
			}
			if (isElementExist(Locators_Data_Setting.No_ThanKS)) {
				clickBtn(Locators_Data_Setting.No_ThanKS);
			}
			if (isElementExist(Locators_Data_Setting.No_thanKS)) {
				clickBtn(Locators_Data_Setting.No_thanKS);
			}
			if (isElementExist(Locators_Data_Setting.Cancel)) {
				clickBtn(Locators_Data_Setting.Cancel);
			}

		}catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->clearChromePermission()");
			e.printStackTrace();

		} catch (Exception e) {

			test.log(LogStatus.ERROR, "Exeption in ->clearChromePermission()");
		}
	}

	public void validate_Page_IsNotLoaded_InChrome(SoftAssert soft) throws InterruptedException, IOException {

		try {

			
			if (Locators_Data_Setting.noInternet_s.isDisplayed()) {
				APP_LOGS.info("Airplane Mode is enabled successfully and page is not loaded");
				test.log(LogStatus.PASS, "Airplane Mode is enabled successfully and page is not loaded");
				soft.assertTrue(true, "Airplane Mode is enabled successfully and page is not loaded");

			} else {
				APP_LOGS.info("Airplane Mode is not disabled ");
				test.log(LogStatus.FAIL, "Airplane Mode is not disabled ");
				soft.fail();
			}

		}

		catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in locators->validate_And_BrowseIn_Chrome()");
		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exeption in ->validate_And_BrowseIn_Chrome()");
		}
	}

	public void validate_Page_IsLoaded_InChrome_s(SoftAssert soft) throws InterruptedException, IOException {

		try {

			boolean noInternet = wait(Locators_Data_Setting.noInternet, 20);
			if (!noInternet) {
				APP_LOGS.info("WiFi Enabled and URL loaded successfully at iteration ");
				soft.assertTrue(true, "WiFi Enabled and URL loaded successfully at iteration ");
				test.log(LogStatus.PASS, "WiFi Enabled and URL loaded successfully at iteration ");
			} else {
				APP_LOGS.info("Suggeted URL page didn't get Loaded");
				test.log(LogStatus.FAIL, "Test Case Status is Fail at iteration ");

				soft.fail();
			}

		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in locators->validate_Page_IsLoaded_InChrome()");
		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exeption in ->validate_Page_IsLoaded_InChrome()");
		}
	}

	public void validate_Page_IsLoaded_InChrome(SoftAssert soft) throws InterruptedException, IOException {

		try {
			boolean value = wait(Locators_Data_Setting.noInternet_s, 10);
			System.out.println(value);
			minWait();
			if (!isElementExist(Locators_Data_Setting.noInternet_s)) {
				APP_LOGS.info("Airplane Mode is disabled and page is  loaded sucessfully");
				soft.assertTrue(true, "Airplane Mode is disabled and page is  loaded sucessfully");
				test.log(LogStatus.PASS, "Airplane Mode is disabled and page is  loaded sucessfully");

			} else {
				APP_LOGS.info("Airplane Mode is enabled and page is not loaded");
				test.log(LogStatus.FAIL, "Airplane Mode is enabled and page is not loaded");
				soft.fail();
			}

		}

		catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in locators->validate_Page_IsLoaded_InChrome()");
		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exeption in ->validate_Page_IsLoaded_InChrome()");
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

	public void clearChromePermission_vzw() {
		try {
			System.out.println("In Browser");

			if (isElementExist(Locators_Data_Setting.OK1)) {
				clickBtn(Locators_Data_Setting.OK1);
				minWait();
			}
			if (isElementExist(Locators_Data_Setting.No_ThanKS)) {
				clickBtn(Locators_Data_Setting.No_ThanKS);
			}
			if (isElementExist(Locators_Data_Setting.OK1)) {
				clickBtn(Locators_Data_Setting.OK1);
				minWait();
			}
			if (isElementExist(Locators_Data_Setting.Cancel)) {
				clickBtn(Locators_Data_Setting.Cancel);
			}
			if (isElementExist(Locators_Data_Setting.OK1)) {
				clickBtn(Locators_Data_Setting.OK1);

			}
			System.err.println("accept");
			if (isElementExist(Locators_Data_Setting.Accept_id)) {
				clickBtn(Locators_Data_Setting.Accept_id);
			}
			if (isElementExist(Locators_Data_Setting.OK1)) {
				clickBtn(Locators_Data_Setting.OK1);

			}
			if (isElementExist(Locators_Data_Setting.NEXT)) {
				clickBtn(Locators_Data_Setting.NEXT);
			}
			if (isElementExist(Locators_Data_Setting.OK1)) {
				clickBtn(Locators_Data_Setting.OK1);

			}
			if (isElementExist(Locators_Data_Setting.NEXTBTN)) {
				clickBtn(Locators_Data_Setting.NEXTBTN);
			}
			if (isElementExist(Locators_Data_Setting.OK1)) {
				clickBtn(Locators_Data_Setting.OK1);

			}
			if (isElementExist(Locators_Data_Setting.NO_THANKS)) {
				clickBtn(Locators_Data_Setting.NO_THANKS);
			}
			if (isElementExist(Locators_Data_Setting.OK1)) {
				clickBtn(Locators_Data_Setting.OK1);

			}
			if (isElementExist(Locators_Data_Setting.No_ThanKS)) {
				clickBtn(Locators_Data_Setting.No_ThanKS);
			}
			if (isElementExist(Locators_Data_Setting.OK1)) {
				clickBtn(Locators_Data_Setting.OK1);
			}

		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->clearChromePermission()");
			e.printStackTrace();

		} catch (Exception e) {

			test.log(LogStatus.ERROR, "Exeption in ->clearChromePermission()");
		}
	}

	public void validate_And_BrowseIn_Chrome(SoftAssert soft) throws InterruptedException, IOException {
		/*
		 * Validates MobileData enabality by launching the Chrome & browsing google
		 * page.
		 */

		try {
			customWait(3000);

			boolean check1 = Locators_Data_Setting.google_appsBtn.isDisplayed();
			boolean check2 = Locators_Data_Setting.google_offeredInTxt.isDisplayed();
			boolean check3 = Locators_Data_Setting.google_signinBtn.isDisplayed();

			if (check1 && check2 && check3) {
				APP_LOGS.info("Suggeted URL Page Displayed Succeessfully");
				soft.assertTrue(true, "TestCase Valiation is PASS");
				test.log(LogStatus.PASS, "WiFi Enabled and URL loaded successfully at iteration ");
			} else {
				APP_LOGS.info("Suggeted URL page didn't get Loaded");
				test.log(LogStatus.FAIL, "Test Case Status is Fail at iteration ");
				test.log(LogStatus.INFO, "URL didn't load.");
				soft.fail();
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in locators->validate_And_BrowseIn_Chrome()");
		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exeption in ->validate_And_BrowseIn_Chrome()");
		}
	}
}
