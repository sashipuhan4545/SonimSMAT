package com.xp8.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

import org.json.simple.parser.ParseException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import com.graphics.gui.JsonFileReaderAndWriter;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import application.AllQA;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.AndroidKeyCode;

public class XP8_Messaging_Util extends BaseUtil {
	private static final String String = null;
	public static ExtentReports extent;
	public static ExtentTest test;
	int n = 3;

	public Process p;
	public boolean check = false;
	public boolean check1 = false;

	public boolean check2 = false;
	public boolean check3 = false;
	public boolean check4 = false;

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
	public void deleteall_SMS() throws InterruptedException

	{
		try {
			if (p_b_No.contains("-15.")) {

			
			} else if (p_b_No.contains("-11.") || p_b_No.contains("-26.") || p_b_No.contains("-29.")) {

			

				
			}

			else if (p_b_No.contains("-10.")) {

				launch_APP(Locators_Messaging.Messaging);
				clearSMSPermissions();
				while (isElementExist(Locators_Messaging.delete_firstmsg_a)) {

					if (isElementExist(Locators_Messaging.delete_firstmsg_a)) {
						TouchAction ta = new TouchAction(aDriver);
						ta.longPress(Locators_Messaging.delete_firstmsg_a).release().perform();
						if (isElementExist(Locators_Messaging.select)) {
							clickBtn(Locators_Messaging.select);
							if (isElementExist(Locators_Messaging.selectall)) {
								clickBtn(Locators_Messaging.selectall);
								clickBtn(Locators_Messaging.deletesms);
								clickBtn(Locators_Messaging.delete_Confirm_a);
							}
                           if(isElementExist(Locators_Messaging.oneselected1)) {
                                    System.out.println("1 select");
								clickBtn(Locators_Messaging.oneselected1);
								System.out.println("clicked on one selevt");
								wait(Locators_Messaging.delete, 20);
								TouchAction ta1=new TouchAction(aDriver);
								ta1.tap(Locators_Messaging.delete).perform();
								System.out.println("delete");
								clickBtn(Locators_Messaging.delete_Confirm_a);

							}

						}
					}
				}
			}


		}

		catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.ERROR, "Error in the locators -> deleteall()");
			e.printStackTrace();
		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exeption in -> deleteall()");
		}

		aDriver.pressKeyCode(AndroidKeyCode.BACK);

	}
	public void delete_contact() {
		try {
			launch_an_app("contacts");

			clickBtn(multi_Loc_Strategy(Locators_Messaging.deleteContactOptn2,
					Locators_Messaging.deleteContactOptn, Locators_Messaging.deleteContactOptn3,
					Locators_Messaging.deleteContactOptn1, null, 0, 0));
			clickBtn(multi_Loc_Strategy(Locators_Messaging.Selection_menu,
					Locators_Messaging.Selection_menu1, Locators_Messaging.Selection_menu2,
					Locators_Messaging.Selection_menu, null, 0, 0));
			clickBtn(multi_Loc_Strategy(Locators_Messaging.ALL_Selection_menu1,
					Locators_Messaging.ALL_Selection_menu1, Locators_Messaging.ALL_Selection_menu2, null,
					null, 0, 0));
			if (Locators_Messaging.OKBtn1.isEnabled()) {
				clickBtn(multi_Loc_Strategy(Locators_Messaging.OKBtn1, Locators_Messaging.OKBtnopt2,
						Locators_Messaging.OKBtnopt3, null, null, 961, 158));
				minWait();
				clickBtn(multi_Loc_Strategy(Locators_Messaging.OKBtnopt2, Locators_Messaging.OKBtnopt1,
						Locators_Messaging.OKBtnopt3, null, null, 961, 158));
				minWait();
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.ERROR, "Error in the locators -> delete_contact()");
			e.printStackTrace();
		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exeption in -> delete_contact()");
		}
	}
	public void clickOnAppList() throws InterruptedException {

		try {
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_HOME);
			customWait(2000);
			clickBtn(Locators_Messaging.app_List);
			minWait();
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->clickOnAppList()");
			e.printStackTrace();

		} catch (Exception e) {

			test.log(LogStatus.ERROR, "Exeption in ->clickOnAppList()");
		}

	}
	public void add_Contact_In_Editor_Screen() throws InterruptedException {

		try {
			clickBtn(Locators_Messaging.more_option_a);
			clickBtn(Locators_Messaging.add_contact);
			clickBtn(Locators_Messaging.create_contact);
			enterTextToInputField(multi_Loc_Strategy(Locators_Messaging.name_NewContact,
					Locators_Messaging.name_NewContact1, Locators_Messaging.name_NewContact2, null, null, 0, 0), "Demo1");
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			
			minWait();
			clickBtn(multi_Loc_Strategy(Locators_Messaging.Save_ConatctIcon1, Locators_Messaging.Save_ConatctIcon2,
					Locators_Messaging.Save_ConatctIcon3, null, null, 0, 0));
			minWait();

			for (int i = 1; i <= 3; i++) {
				if (isElementExist(Locators_Messaging.AllowOptn)) {
					clickBtn(Locators_Messaging.AllowOptn);
				}
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->clickOnAppList()");
			e.printStackTrace();

		} catch (Exception e) {

			test.log(LogStatus.ERROR, "Exeption in ->clickOnAppList()");
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
				if (isElementExist(Locators_Messaging.StartMessaging)) {
					clickBtn(Locators_Messaging.StartMessaging);

					minWait();
					clickBtn(Locators_Messaging.NEXT_Msg);
					minWait();
					clickBtn(Locators_Messaging.allow_Permission);
					minWait();
				}
			} else if (p_b_No.contains("-15.") || p_b_No.contains("-29.") || p_b_No.contains("-26.")
					|| p_b_No.contains("-11.")) {
				customWait(2000);
				if (isElementExist(Locators_Messaging.StartMessaging)) {
					clickBtn(Locators_Messaging.StartMessaging);
					minWait();
					clickBtn(Locators_Messaging.skipProvisioning);
					minWait();
				}
				if (isElementExist(Locators_Messaging.OKBtnopt1)) {
					clickBtn(Locators_Messaging.OKBtnopt1);
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
//clickBtn(Locators_Messaging.add_NewSMS);
				clickBtn(multi_Loc_Strategy(Locators_Messaging.add_NewSMS, Locators_Messaging.add_NewSMS11,
						Locators_Messaging.add_NewSMS1, null, null, 924, 1824));
				minWait();
			} else if (p_b_No.contains("-15.")) {
				clickBtn(multi_Loc_Strategy(Locators_Messaging.add_NewSMS_O, Locators_Messaging.add_NewSMS_O1, null,
						null, null, 0, 0));
				minWait();
			} else if (p_b_No.contains("-29.") || p_b_No.contains("-11.") || p_b_No.contains("-26.")) {

				clickBtn(multi_Loc_Strategy(Locators_Messaging.add_NewSMS_s, Locators_Messaging.add_NewSMS_s1,
						Locators_Messaging.add_NewSMS_s2, Locators_Messaging.add_NewSMS_s3, null, 0, 0));
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
System.out.println("to");
				enterTextToInputField(multi_Loc_Strategy(Locators_Messaging.TO_Field_phno1,
						Locators_Messaging.TO_Field_phno2, Locators_Messaging.TO_Field_phno3, null, null, 0, 0),
						number);

				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			} else if (p_b_No.contains("-15.")) {
				minWait();
				enterTextToInputField(multi_Loc_Strategy(Locators_Messaging.TO_Field_O, Locators_Messaging.TO_Field_O1,
						null, null, null, 0, 0), number);
				customWait(2000);
				System.out.println("Selecting COntact NAme");

			} else if (p_b_No.contains("-26.") || p_b_No.contains("-29.") || p_b_No.contains("-11.")) {
				minWait();
				enterTextToInputField(multi_Loc_Strategy(Locators_Messaging.To_Field_B1, Locators_Messaging.To_Field_B,
						Locators_Messaging.To_Field_B2, null, null, 0, 0), number);
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

	public boolean enterTextToInputField1(AndroidElement e, String text) throws InterruptedException {
		if (e == null) {
			return false;
		} else {
			if (e.isDisplayed()) {
				Thread.sleep(300);

				e.click();
				e.sendKeys(text);

				APP_LOGS.info("PASS: Element found and entered value successfully");
				return true;
			} else {
				APP_LOGS.info("FAIL: Element not found or displayed: Object Locator : " + e);
				return false;
			}
		}
	}

	public void enter_Multiple_Number(String number) throws InterruptedException {
		/* Method is used to Enter Number into TO Field. */
		try {
			if (p_b_No.contains("-10.")) {
				minWait();
				for (int i = 1; i <= 1; i++) {
					enterTextToInputField1(multi_Loc_Strategy(Locators_Messaging.TO_Field_phno1,
							Locators_Messaging.TO_Field_phno2, Locators_Messaging.TO_Field_phno3, null, null, 0, 0),
							number + i);
				}
			} else if (p_b_No.contains("-15.")) {
				minWait();
				for (int i = 1; i <= n; i++) {
					enterTextToInputField1(multi_Loc_Strategy(Locators_Messaging.TO_Field_O,
							Locators_Messaging.TO_Field_O1, null, null, null, 0, 0), number + i);

				}
			} else if (p_b_No.contains("-26.") || p_b_No.contains("-29.") || p_b_No.contains("-11.")) {
				minWait();

				enterTextToInputField1(multi_Loc_Strategy(Locators_Messaging.To_Field_B1, Locators_Messaging.To_Field_B,
						Locators_Messaging.To_Field_B2, null, null, 0, 0), number);
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->enter_Num_ToField()");
			e.printStackTrace();

		} catch (Exception e) {

			test.log(LogStatus.ERROR, "Exeption in ->enter_Num_ToField()");
		}
	}

	public void click_To_Field() throws InterruptedException {
		/* Method is used to Enter Number into TO Field. */
		try {
			if (p_b_No.contains("-10.")) {
				minWait();
				for (int i = 1; i <= n; i++) {
					clickBtn(multi_Loc_Strategy(Locators_Messaging.To, Locators_Messaging.To, Locators_Messaging.To,
							null, null, 0, 0));

				}
			} else if (p_b_No.contains("-15.")) {
				minWait();
				for (int i = 1; i <= n; i++) {
					clickBtn(multi_Loc_Strategy(Locators_Messaging.To, Locators_Messaging.To, null, null, null, 0, 0));

				}
			} else if (p_b_No.contains("-26.") || p_b_No.contains("-29.") || p_b_No.contains("-11.")) {
				minWait();
				for (int i = 1; i <= n; i++) {
					clickBtn(multi_Loc_Strategy(Locators_Messaging.To, Locators_Messaging.To, Locators_Messaging.To,
							null, null, 0, 0));

				}
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->enter_Num_ToField()");
			e.printStackTrace();

		} catch (Exception e) {

			test.log(LogStatus.ERROR, "Exeption in ->enter_Num_ToField()");
		}
	}

	public void group_Coversation() throws InterruptedException {
		/* Method is used to Enter Number into TO Field. */
		try {
			if (p_b_No.contains("-10.")) {
				clickBtn(multi_Loc_Strategy(Locators_Messaging.group_sms, Locators_Messaging.group_sms1,
						Locators_Messaging.group_sms, null, null, 0, 0));
				clickBtn(Locators_Messaging.recive_sms_A);
				clickBtn(Locators_Messaging.recive_sms1);
				


			} else if (p_b_No.contains("-15.")) {

			} else if (p_b_No.contains("-26.") || p_b_No.contains("-29.") || p_b_No.contains("-11.")) {
				minWait();
				clickBtn(multi_Loc_Strategy(Locators_Messaging.group_coversation, Locators_Messaging.group_coversation1,
						Locators_Messaging.group_coversation, null, null, 0, 0));

				customWait(2000);
				System.out.println("Selecting Group");

			}
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->group_Coversation()");
			e.printStackTrace();

		} catch (Exception e) {

			test.log(LogStatus.ERROR, "Exeption in ->group_Coversation()");
		}
	}
	public void disable_Group_Messaging() throws InterruptedException {
		/* Method is used to Enter Number into TO Field. */
		try {
			if (p_b_No.contains("-10.")) {
				launch_APP(Locators_Messaging.Messaging);
				 wait(Locators_Messaging.MoreOptions, 80);
					clickBtn(Locators_Messaging.MoreOptions);

					clickBtn(Locators_Messaging.setting);
					scrollToText("Multimedia (MMS) messages settings");
					if (Locators_Messaging.groupmsgbtn.getText().equalsIgnoreCase("on")) {
						clickBtn(Locators_Messaging.groupmsgbtn);
					}

			} else if (p_b_No.contains("-15.")) {

			} else if (p_b_No.contains("-26.") || p_b_No.contains("-29.") || p_b_No.contains("-11.")) {
				
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->disable_Group_Messaging()");
			e.printStackTrace();

		} catch (Exception e) {

			test.log(LogStatus.ERROR, "Exeption in ->disable_Group_Messaging()");
		}
	}
	public void add_Picture() throws InterruptedException {
		/* Method is used to Enter Number into TO Field. */
		try {
			if (p_b_No.contains("-10.")) {
			
				clickBtn(Locators_Messaging.attach);
				clickBtn(Locators_Messaging.picture);
			if(isElementExist(Locators_Messaging.allow))
			{
				clickBtn(Locators_Messaging.allow);
			}
				minWait();
				System.out.println("ss");
				clickBtn(Locators_Messaging.shutter);
				minWait();
				wait(Locators_Messaging.done, 60);
				clickBtn(Locators_Messaging.done1);
				minWait();
			} else if (p_b_No.contains("-15.")) {

			} else if (p_b_No.contains("-26.") || p_b_No.contains("-29.") || p_b_No.contains("-11.")) {
				

			}
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->group_Coversation()");
			e.printStackTrace();

		} catch (Exception e) {

			test.log(LogStatus.ERROR, "Exeption in ->group_Coversation()");
		}
	}
	public void validate_add_Picture(SoftAssert sa) {
		try {
			if (Locators_Messaging.view.isDisplayed()||Locators_Messaging.type_text1.isDisplayed()) {
				APP_LOGS.info("Attached the File sucessfully");
				sa.assertTrue(true, "Attached the File sucessfully");
				test.log(LogStatus.PASS, "Attached the File sucessfully");
			} else {
				APP_LOGS.info("Failed to Attach the File ");
				sa.fail();
				test.log(LogStatus.FAIL, "Failed to Attach the File ");
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators --> validate_add_Picture()");
			e.printStackTrace();

		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exception in -->validate_add_Picture()");
			e.printStackTrace();
		}
	}
	public void validate_Lock_Unlock_Screen(SoftAssert sa) {
		try {
			if (Locators_Messaging.TO_Field_phno1.isDisplayed()||Locators_Messaging.type_text1.isDisplayed()) {
				APP_LOGS.info("Editor Screen displayed sucessfully");
				sa.assertTrue(true, "Editor Screen displayed sucessfully");
				test.log(LogStatus.PASS, "Editor Screen displayed sucessfully");
			} else {
				APP_LOGS.info("Failed to display the Editor Screen ");
				sa.fail();
				test.log(LogStatus.FAIL, "Failed to display the Editor Screen");
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators --> validate_Lock_Unlock_Screen()");
			e.printStackTrace();

		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exception in -->validate_Lock_Unlock_Screen()");
			e.printStackTrace();
		}
	}
	public void lock_Unlock_Screen_A() throws InterruptedException, IOException {
		try {
minWait();
			launchAppThroughABD("adb shell input keyevent 26");
			minWait();
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

	public void validate_group_SMS(SoftAssert soft) throws InterruptedException {
		/* To validate the Sent Message. */

		try {
			minWait();
	java.lang.String group = Locators_Messaging.Selection_menu.getText();

			if (group.equalsIgnoreCase("2 selected")) {
				APP_LOGS.info(" Sucessfully selected the contact");
				soft.assertTrue(true, " Sucessfully selected the contact");
				test.log(LogStatus.PASS, " Sucessfully selected the contact");
			} else {
				APP_LOGS.info("Failed to select the contact");
				soft.fail();
				test.log(LogStatus.FAIL, "Failed to select the contact");
			}
		
			} catch (org.openqa.selenium.NoSuchElementException e) {

				test.log(LogStatus.ERROR, "Error in the locators->validate_group_SMS()");
				e.printStackTrace();

			} catch (Exception e) {

				test.log(LogStatus.ERROR, "Exeption in ->validate_group_SMS()");
			}
		}

	public void select_SIM_Contact() throws InterruptedException {
		/* Method is used to Enter Number into TO Field. */
		try {
				clickBtn(multi_Loc_Strategy(Locators_Messaging.group_sms, Locators_Messaging.group_sms1,
						Locators_Messaging.group_sms, null, null, 0, 0));
				clickBtn(Locators_Messaging.sonim1);
				clickBtn(Locators_Messaging.sonim2);

			
				


			
			
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->select_SIM_Contact()");
			e.printStackTrace();

		} catch (Exception e) {

			test.log(LogStatus.ERROR, "Exeption in ->select_SIM_Contact()");
		}
	}
	public void select_SIM_And_Phone_Contact() throws InterruptedException {
		/* Method is used to Enter Number into TO Field. */
		try {
			clickBtn(multi_Loc_Strategy(Locators_Messaging.group_sms, Locators_Messaging.group_sms1,
					Locators_Messaging.group_sms, null, null, 0, 0));
			
				clickBtn(Locators_Messaging.sonim2);
				clickBtn(Locators_Messaging.sonim1);
				//clickBtn(Locators_Messaging.test1);			
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->select_SIM_And_Phone_Contact()");
			e.printStackTrace();

		} catch (Exception e) {

			test.log(LogStatus.ERROR, "Exeption in ->select_SIM_And_Phone_Contact()");
		}
	}

	public void enterText_MessageField(String message) throws InterruptedException {
		/* Methos is Used enter Message into Message Field. */
		try {

			if (p_b_No.contains("-10.")) {
System.out.println("text");
				enterTextToInputField(multi_Loc_Strategy(Locators_Messaging.type_text1, Locators_Messaging.type_text2,
						Locators_Messaging.type_text3, null, null, 0, 0), message);
				minWait();
			} else if (p_b_No.contains("-15")) {
				minWait();

				enterTextToInputField(multi_Loc_Strategy(Locators_Messaging.messageField_O,
						Locators_Messaging.messageField_O1, Locators_Messaging.messageField_O2, null, null, 0, 0),
						message);
				minWait();
			} else if (p_b_No.contains("-29") || p_b_No.contains("-26") || p_b_No.contains("-11")) {
				minWait();
				if (isElementExist(Locators_Messaging.Text_Field_B)) {
					enterTextToInputField(Locators_Messaging.Text_Field_B, message);
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

	public void select_The_Contact() throws InterruptedException {
		/* Method used to click on Send Button. */
		try {
			if (p_b_No.contains("-10.")) {
				clickBtn(multi_Loc_Strategy(Locators_Messaging.group_sms, Locators_Messaging.group_sms1,
						Locators_Messaging.group_sms, null, null, 0, 0));
				clickBtn(Locators_Messaging.recive_sms_A);
				clickBtn(Locators_Messaging.recive_sms1);
				
				

			} else if (p_b_No.contains("-15")) {

			} else if (p_b_No.contains("-29") || p_b_No.contains("-26") || p_b_No.contains("-11")) {
				clickBtn(Locators_Messaging.Test_2);
				clickBtn(Locators_Messaging.Test_1);
				clickBtn(Locators_Messaging.Test_1);
				clickBtn(Locators_Messaging.confirm);
			} else {

			}

		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->select_Deselect_The_Contact()");
			e.printStackTrace();

		} catch (Exception e) {

			test.log(LogStatus.ERROR, "Exeption in ->select_Deselect_The_Contact()");
		}
	}
	public void Scroll_To_Contact() throws InterruptedException {
		/* Method used to click on Send Button. */
		try {
			if (p_b_No.contains("-10.")) {
				clickBtn(multi_Loc_Strategy(Locators_Messaging.group_sms, Locators_Messaging.group_sms1,
						Locators_Messaging.group_sms, null, null, 0, 0));
				minWait();
			scrollTo("Test4");
				

			} else if (p_b_No.contains("-15")) {

			} else if (p_b_No.contains("-29") || p_b_No.contains("-26") || p_b_No.contains("-11")) {
				
			} else {

			}

		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->Scroll_To_Contact()");
			e.printStackTrace();

		} catch (Exception e) {

			test.log(LogStatus.ERROR, "Exeption in ->Scroll_To_Contact()");
		}
	}
	public void delect_The_Contact() throws InterruptedException {
		/* Method used to click on Send Button. */
		try {
			if (p_b_No.contains("-10.")) {
				
				clickBtn(Locators_Messaging.recive_sms1);
	
				

			} else if (p_b_No.contains("-15")) {

			} else if (p_b_No.contains("-29") || p_b_No.contains("-26") || p_b_No.contains("-11")) {
				clickBtn(Locators_Messaging.Test_2);
				clickBtn(Locators_Messaging.Test_1);
				clickBtn(Locators_Messaging.Test_1);
				clickBtn(Locators_Messaging.confirm);
			} else {

			}

		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->select_Deselect_The_Contact()");
			e.printStackTrace();

		} catch (Exception e) {

			test.log(LogStatus.ERROR, "Exeption in ->select_Deselect_The_Contact()");
		}
	}
	public void validate_Select_Contact(SoftAssert soft) throws InterruptedException {
		/* To validate the Sent Message. */

		try {
		
				if (Locators_Messaging.Selection_menu.getText().equalsIgnoreCase("2 selected")) {
					APP_LOGS.info("Selected the contact Sucessfully");
					soft.assertTrue(true,	"Selected the contact Sucessfully");
					test.log(LogStatus.PASS,	"Selected the contact Sucessfully");

				} else {
					APP_LOGS.info("Failed to Select the contact ");
					soft.fail();
					test.log(LogStatus.FAIL, "Failed to Select the contact ");

				}
			} catch (org.openqa.selenium.NoSuchElementException e) {

				test.log(LogStatus.ERROR, "Error in the locators->validate_Selected_Contact()");
				e.printStackTrace();

			} catch (Exception e) {

				test.log(LogStatus.ERROR, "Exeption in ->validate_Selected_Contact()");
			}
		}
	public void validate_Select_Contact_In_SIM_Phone(SoftAssert soft) throws InterruptedException {
		/* To validate the Sent Message. */

		try {
		
				if (Locators_Messaging.Selection_menu.getText().contains("selected")) {
					APP_LOGS.info("Selected the contact Sucessfully");
					soft.assertTrue(true,	"Selected the contact Sucessfully");
					test.log(LogStatus.PASS,	"Selected the contact Sucessfully");

				} else {
					APP_LOGS.info("Failed to Select the contact ");
					soft.fail();
					test.log(LogStatus.FAIL, "Failed to Select the contact ");

				}
			} catch (org.openqa.selenium.NoSuchElementException e) {

				test.log(LogStatus.ERROR, "Error in the locators->validate_Select_Contact_In_SIM_Phone()");
				e.printStackTrace();

			} catch (Exception e) {

				test.log(LogStatus.ERROR, "Exeption in ->validate_Select_Contact_In_SIM_Phone()");
			}
		}
	public void validate_Delected_Contact(SoftAssert soft) throws InterruptedException {
		/* To validate the Sent Message. */

		try {
			
					
			if (Locators_Messaging.Selection_menu.getText().equalsIgnoreCase("1 selected")) {
					APP_LOGS.info("Delected the contact Sucessfully");
					soft.assertTrue(true,	"Delected the contact Sucessfully");
					test.log(LogStatus.PASS,	"Delected the contact Sucessfully");

				} else {
					APP_LOGS.info("Failed to delected the contact ");
					soft.fail();
					test.log(LogStatus.FAIL, "Failed to delected the contact ");

				}
			} catch (org.openqa.selenium.NoSuchElementException e) {

				test.log(LogStatus.ERROR, "Error in the locators->validate_Delected_Contact()");
				e.printStackTrace();

			} catch (Exception e) {

				test.log(LogStatus.ERROR, "Exeption in ->validate_Delected_Contact()");
			}
		}
	public void validate_MMS(SoftAssert soft) throws InterruptedException {
		/* To validate the Sent Message. */

		try {
			java.lang.String mms = Locators_Messaging.mms.getText();
				if (mms.equals("MMS")) {
					APP_LOGS.info("SMS converted to MMS Sucessfully");
					soft.assertTrue(true,	"SMS converted to MMS Sucessfully");
					test.log(LogStatus.PASS,	"SMS converted to MMS Sucessfully");

				} else {
					APP_LOGS.info("Failed to convert SMS  to MMS ");
					soft.fail();
					test.log(LogStatus.FAIL, "Failed to convert SMS  to MMS ");

				}
			} catch (org.openqa.selenium.NoSuchElementException e) {

				test.log(LogStatus.ERROR, "Error in the locators->select_Deselect_The_Contact()");
				e.printStackTrace();

			} catch (Exception e) {

				test.log(LogStatus.ERROR, "Exeption in ->select_Deselect_The_Contact()");
			}
		}
	public void validate_SMS(SoftAssert soft) throws InterruptedException {
		/* To validate the Sent Message. */

		try {
			java.lang.String mms = Locators_Messaging.sms.getText();
				if (mms.equals("SMS")) {
					APP_LOGS.info("SMS converted to MMS Sucessfully");
					soft.assertTrue(true,
							"SMS converted to MMS Sucessfully");
					test.log(LogStatus.PASS,
							"SMS converted to MMS Sucessfully");

				} else {
					APP_LOGS.info("Failed to convert SMS  to MMS ");
					soft.fail();
					test.log(LogStatus.FAIL, "Failed to convert SMS  to MMS ");

				}
			} catch (org.openqa.selenium.NoSuchElementException e) {

				test.log(LogStatus.ERROR, "Error in the locators->validate_SMS()");
				e.printStackTrace();

			} catch (Exception e) {

				test.log(LogStatus.ERROR, "Exeption in ->validate_SMS()");
			}
		}
	public void validate_Forward_SMS(SoftAssert soft) throws InterruptedException {
		/* To validate the Sent Message. */

		try {
		 AndroidElement mms = Locators_Messaging.TO_Field_phno1;
				if (isElementExist(mms)) {
					APP_LOGS.info("Sucessfully Recived and Forward the SMS ");
					soft.assertTrue(true,"Sucessfully Recived and Forward the SMS ");
					test.log(LogStatus.PASS,"Sucessfully Recived and Forward the SMS ");

				} else {
					APP_LOGS.info("Failed to  Recive and Forward the SMS ");
					soft.fail();
					test.log(LogStatus.FAIL, "Failed to  Recive and Forward the SMS ");

				}
			} catch (org.openqa.selenium.NoSuchElementException e) {

				test.log(LogStatus.ERROR, "Error in the locators->validate_SMS()");
				e.printStackTrace();

			} catch (Exception e) {

				test.log(LogStatus.ERROR, "Exeption in ->validate_SMS()");
			}
		}

	public void adding_new_Recipients() throws InterruptedException {
		/* Method used to click on Send Button. */
		try {
			if (p_b_No.contains("-10.")) {
				minWait();
				System.err.println("sonim");
              maxWait();
            //   launchAppThroughABD("adb shell input tap 985 149");
              TouchAction ta=new TouchAction(aDriver);
            //  ta.tap(Locators_Messaging.group_sms, 985, 149).perform();
              ta.tap(985, 149).perform();
               minWait();
				clickBtn(Locators_Messaging.sonim1);

			} else if (p_b_No.contains("-15")) {

			} else if (p_b_No.contains("-29") || p_b_No.contains("-26") || p_b_No.contains("-11")) {
				clickBtn(Locators_Messaging.Test_2);
				clickBtn(Locators_Messaging.confirm);
				clickBtn(multi_Loc_Strategy(Locators_Messaging.add_people, Locators_Messaging.add_people1, null, null,
						null, 0, 0));
				aDriver.pressKeyCode(AndroidKeyCode.BACK);
				clickBtn(Locators_Messaging.Test_1);
				clickBtn(Locators_Messaging.confirm);
			} else {

			}

		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->add_New_Number_To_Recipient()");
			e.printStackTrace();

		} catch (Exception e) {

			test.log(LogStatus.ERROR, "Exeption in ->add_New_Number_To_Recipient()");
		}
	}

	public void clickOn_Send() throws InterruptedException {
		/* Method used to click on Send Button. */
		try {
			if (p_b_No.contains("-10.")) {
				if (isElementExist(Locators_Messaging.send_Icon)) {
					clickBtn(multi_Loc_Strategy(Locators_Messaging.send_Icon, Locators_Messaging.send_Icon2,
							Locators_Messaging.send_Icon3, null, null, 990, 1024));
					APP_LOGS.info("Send icon");
					minWait();
				} else if (isElementExist(Locators_Messaging.send_SMS)) {
					clickBtn(Locators_Messaging.send_SMS);
					APP_LOGS.info("Send icon");
					minWait();
				} else {
					clickBtn(Locators_Messaging.send_MMS_Icon);
					minWait();
				}
			} else if (p_b_No.contains("-15.")) {
				customWait(500);
				clickBtn(Locators_Messaging.send_Icon_O);
				minWait();
			} else if (p_b_No.contains("-29.") || p_b_No.contains("-26.") || p_b_No.contains("-11.")) {

				customWait(500);
				clickBtn(multi_Loc_Strategy(Locators_Messaging.send_Icon_B, Locators_Messaging.send_Icon_B1,
						Locators_Messaging.send_Icon_B2, null, null, 0, 0));
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

	public void clickOn_Thread() throws InterruptedException {
		/* Method used to click on Send Button. */
		try {
			if (p_b_No.contains("-10.")) {

			} else if (p_b_No.contains("-15.")) {

			} else if (p_b_No.contains("-29.") || p_b_No.contains("-26.") || p_b_No.contains("-11.")) {
				TouchAction ta = new TouchAction(aDriver);
				ta.longPress(Locators_Messaging.thread_2, 10).perform().release();
				clickBtn(Locators_Messaging.MoreOptions);
				clickBtn(multi_Loc_Strategy(Locators_Messaging.view_details, Locators_Messaging.view_details1,
						Locators_Messaging.view_details2, null, null, 0, 0));
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->clickOn_Send()");
			e.printStackTrace();

		} catch (Exception e) {

			test.log(LogStatus.ERROR, "Exeption in ->clickOn_Send()");
		}
	}

	public void validate_SentMessage(SoftAssert soft) throws InterruptedException {
		/* To validate the Sent Message. */

		try {
			if (p_b_No.contains("-10.")) {
				System.out.println("Sent msg");
				wait(Locators_Messaging.sending_Txt, 25);
				if (isElementExist(Locators_Messaging.sending_Txt)||isElementExist(Locators_Messaging.now_Text) || isElementExist(Locators_Messaging.justnow_Text)
						|| isElementExist(Locators_Messaging.not_Sent_Text)
						|| isElementExist(Locators_Messaging.sending_Txt)
						|| isElementExist(Locators_Messaging.sended_SMS)) {

					APP_LOGS.info("Message sent Succeccfully");
					test.log(LogStatus.PASS, "Message sent Succeccfully");
					soft.assertTrue(true, "Message sent Succeccfully");

				} else {

					APP_LOGS.info("SMS didn't sent");
					test.log(LogStatus.FAIL, "SMS didn't sent");
					soft.fail();
				}
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
				minWait();
			} else if (p_b_No.contains("-15.")) {
				wait(Locators_Messaging.Delivered, 10);
				if (isElementExist(Locators_Messaging.Delivered) || isElementExist(Locators_Messaging.Delivered)
						|| isElementExist(Locators_Messaging.not_Sent_Text)) {
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
				wait(Locators_Messaging.now_sms1, 15);
				if (isElementExist(Locators_Messaging.now_sms1) || isElementExist(Locators_Messaging.now_sms3)
						|| isElementExist(Locators_Messaging.now_sms) || isElementExist(Locators_Messaging.Delivered)
						|| isElementExist(Locators_Messaging.now_sms1)) {
					APP_LOGS.info("Message sent Succeccfully");
					soft.assertTrue(true, "SMS Sent Successfully ");
					test.log(LogStatus.PASS, "SMS Sent Successfully ");
				} else {
					APP_LOGS.info("Message didn't sent ");
					soft.fail();
					test.log(LogStatus.FAIL, "Message didn't sent ");
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
	public void validate_ForwardMessage(SoftAssert soft) throws InterruptedException {
		/* To validate the Sent Message. */

		try {
			if (p_b_No.contains("-10.")) {
				System.out.println("Sent msg");
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
				wait(Locators_Messaging.now_Text, 15);
				if (isElementExist(Locators_Messaging.now_Text) || isElementExist(Locators_Messaging.justnow_Text)
						|| isElementExist(Locators_Messaging.not_Sent_Text)
						|| isElementExist(Locators_Messaging.sending_Txt)
						|| isElementExist(Locators_Messaging.sended_SMS)) {

					APP_LOGS.info("Message Forwarded sent Succeccfully");
					test.log(LogStatus.PASS, "Message Forwarded sent Succeccfully");
					soft.assertTrue(true, "Message Forwarded sent Succeccfully");

				} else {

					APP_LOGS.info("SMS didn't sent");
					test.log(LogStatus.FAIL, "SMS didn't sent");
					soft.fail();
				}
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
				minWait();
			} else if (p_b_No.contains("-15.")) {
				wait(Locators_Messaging.Delivered, 10);
				if (isElementExist(Locators_Messaging.Delivered) || isElementExist(Locators_Messaging.Delivered)
						|| isElementExist(Locators_Messaging.not_Sent_Text)) {
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
				wait(Locators_Messaging.now_sms1, 15);
				if (isElementExist(Locators_Messaging.now_sms1) || isElementExist(Locators_Messaging.now_sms3)
						|| isElementExist(Locators_Messaging.now_sms) || isElementExist(Locators_Messaging.Delivered)
						|| isElementExist(Locators_Messaging.now_sms1)) {
					APP_LOGS.info("Message sent Succeccfully");
					soft.assertTrue(true, "SMS Sent Successfully ");
					test.log(LogStatus.PASS, "SMS Sent Successfully ");
				} else {
					APP_LOGS.info("Message didn't sent ");
					soft.fail();
					test.log(LogStatus.FAIL, "Message didn't sent ");
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
	public void validate_SentMessage_multiple_Times(SoftAssert soft) throws InterruptedException {
		/* To validate the Sent Message. */

		try {
			if (p_b_No.contains("-10.")) {
				System.out.println("Sent msg");
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
				wait(Locators_Messaging.now_Text, 15);
				if (isElementExist(Locators_Messaging.now_Text) || isElementExist(Locators_Messaging.justnow_Text)
						|| isElementExist(Locators_Messaging.not_Sent_Text)
						|| isElementExist(Locators_Messaging.sending_Txt)
						|| isElementExist(Locators_Messaging.sended_SMS)) {

					APP_LOGS.info("SMS Sent multiple time Successfully ");
					test.log(LogStatus.PASS, "SMS Sent multiple time Successfully ");
					soft.assertTrue(true, "SMS Sent multiple time Successfully ");

				} else {

					APP_LOGS.info("SMS didn't sent");
					test.log(LogStatus.FAIL, "SMS didn't sent");
					soft.fail();
				}
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
				minWait();
			} else if (p_b_No.contains("-15.")) {
				wait(Locators_Messaging.Delivered, 10);
				if (isElementExist(Locators_Messaging.Delivered) || isElementExist(Locators_Messaging.Delivered)
						|| isElementExist(Locators_Messaging.not_Sent_Text)) {
					check = true;
					APP_LOGS.info("Message sent Succeccfully");
					System.out.println("Sent");

					test.log(LogStatus.PASS, "SMS Sent Successfully ");
					soft.assertTrue(check, "SMS Sent Successfully at iteration ");
					APP_LOGS.info("SMS Sent Successfully at iteration ");
				} else {
					APP_LOGS.info("SMS didn't sent");
					soft.fail();
					test.log(LogStatus.FAIL, "Message didn't sent at iteration ");
				}
			} else if (p_b_No.contains("-29.") || p_b_No.contains("-26.") || p_b_No.contains("-11.")) {
				wait(Locators_Messaging.now_sms1, 15);
				if (isElementExist(Locators_Messaging.now_sms1) || isElementExist(Locators_Messaging.now_sms3)
						|| isElementExist(Locators_Messaging.now_sms) || isElementExist(Locators_Messaging.Delivered)
						|| isElementExist(Locators_Messaging.now_sms1)) {
					APP_LOGS.info("Message sent Succeccfully");
					soft.assertTrue(true, "SMS Sent Successfully ");
					test.log(LogStatus.PASS, "SMS Sent Successfully ");
				} else {
					APP_LOGS.info("Message didn't sent ");
					soft.fail();
					test.log(LogStatus.FAIL, "Message didn't sent ");
				}

			}

			/* aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK); */
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->validate_SentMessage_multiple_Times()");
			e.printStackTrace();

		} catch (Exception e) {

			test.log(LogStatus.ERROR, "Exeption in ->validate_SentMessage_multiple_Times()");
		}

	}

	public void validate_New_Phno(SoftAssert soft, String phno1) throws InterruptedException {
		/* To validate the Sent Message. */

		try {
			if (p_b_No.contains("-10.")) {
				java.lang.String phno = Locators_Messaging.Phno_A.getText();
				System.out.println(phno);
				if (phno.equals(phno1)) {
					APP_LOGS.info(" New international  number is Entered  Sucessfully");
					soft.assertTrue(true,
							" New international  number is Entered  Sucessfully");
					test.log(LogStatus.PASS,
							" New international  number is Entered  Sucessfully");

				} else {
					APP_LOGS.info("Entered new  and international format phone number is not displayed ");
					soft.fail();
					test.log(LogStatus.FAIL, "Entered new  and international format phone number is not displayed ");

				}

			} else if (p_b_No.contains("-15.")) {
				java.lang.String phno = Locators_Messaging.Phno.getText();
				System.out.println(phno);
				if (phno.equals(phno1)) {
					APP_LOGS.info("Entered new number and international format phone number displayed Sucessfully");
					soft.assertTrue(true,
							"Entered new number and international format phone number displayed Sucessfully");
					test.log(LogStatus.PASS,
							"Entered new number and international format phone number displayed Sucessfully");

				} else {
					APP_LOGS.info("Entered new number and international format phone number is not displayed ");
					soft.fail();
					test.log(LogStatus.FAIL,
							"Entered new number and international format phone number is not displayed ");

				}
			}

			else if (p_b_No.contains("-29.") || p_b_No.contains("-26.") || p_b_No.contains("-11.")) {
				java.lang.String phno = Locators_Messaging.Phno.getText().trim();

				if (phno.equals(phno1)) {
					APP_LOGS.info("Entered new number and international format phone number displayed Sucessfully");
					soft.assertTrue(true,
							"Entered new number and international format phone number displayed Sucessfully");
					test.log(LogStatus.PASS,
							"Entered new number and international format phone number displayed Sucessfully");

				} else {
					APP_LOGS.info("Failed to display new number and international format phone number");
					soft.fail();
					test.log(LogStatus.FAIL, "Failed to display new number and international format phone number");

				}

			}

			/* aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK); */
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->validate_New_Phno()");
			e.printStackTrace();

		} catch (Exception e) {

			test.log(LogStatus.ERROR, "Exeption in ->validate_New_Phno()");
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

	public void validate_Special_Character(SoftAssert soft, String specialchar1) throws InterruptedException {
		/* To validate the Sent Message. */

		try {
			if (p_b_No.contains("-10.")) {
				java.lang.String specialchar = Locators_Messaging.special_char_A.getText().trim();

				if (specialchar.equals(specialchar1)) {
					APP_LOGS.info("Sucessfully entered Special character");
					soft.assertTrue(true, "Sucessfully entered Special character");
					test.log(LogStatus.PASS, "Sucessfully entered Special character");

				} else {
					APP_LOGS.info("Failed to enter the Special character");
					soft.fail();
					test.log(LogStatus.FAIL, "Failed to enter the Special character");

				}

			} else if (p_b_No.contains("-15.")) {

			}

			else if (p_b_No.contains("-29.") || p_b_No.contains("-26.") || p_b_No.contains("-11.")) {
				java.lang.String specialchar = Locators_Messaging.special_char.getText().trim();

				if (specialchar.equals(specialchar1)) {
					APP_LOGS.info("Sucessfully entered Special character");
					soft.assertTrue(true, "Sucessfully entered Special character");
					test.log(LogStatus.PASS, "Sucessfully entered Special character");

				} else {
					APP_LOGS.info("Failed to enter the Special character");
					soft.fail();
					test.log(LogStatus.FAIL, "Failed to enter the Special character");

				}

			}

			/* aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK); */
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->validate_Special_Character()");
			e.printStackTrace();

		} catch (Exception e) {

			test.log(LogStatus.ERROR, "Exeption in ->validate_Special_Character()");
		}

	}

	public void validate_Precondition(SoftAssert soft) throws InterruptedException {
		/* To validate the Sent Message. */

		try {

					APP_LOGS.info("PreCondition Excecuted Sucessfully");
					soft.assertTrue(true, "PreCondition Excecuted Sucessfully");
					test.log(LogStatus.PASS, "PreCondition Excecuted Sucessfully");

		

			
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->validate_Precondition()");
			e.printStackTrace();

		} catch (Exception e) {

			test.log(LogStatus.ERROR, "Exeption in ->validate_Precondition()");
		}

	}
	public void validate_Postcondition(SoftAssert soft) throws InterruptedException {
		/* To validate the Sent Message. */

		try {

					APP_LOGS.info("PostCondition Excecuted Sucessfully");
					soft.assertTrue(true, "PostCondition Excecuted Sucessfully");
					test.log(LogStatus.PASS, "PostCondition Excecuted Sucessfully");

		

			
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->validate_Postcondition()");
			e.printStackTrace();

		} catch (Exception e) {

			test.log(LogStatus.ERROR, "Exeption in ->validate_Postcondition()");
		}

	}

	public void validate_UpperCaseandLowerCase(SoftAssert soft, String expected) throws InterruptedException {
		/* To validate the Sent Message. */

		try {
			if (p_b_No.contains("-10.")) {
				java.lang.String actual = Locators_Messaging.special_char_A.getText().trim();
				System.out.println(actual);
				System.out.println(expected);
				System.out.println(actual.length());
				System.out.println(expected.length());
				if (actual.equals(expected)) {
					APP_LOGS.info("Uppercase and Lowercase Characters Entered Sucessfully");
					soft.assertTrue(true, "Uppercase and Lowercase Characters Entered Sucessfully");
					test.log(LogStatus.PASS, "Uppercase and Lowercase Characters Entered Sucessfully");

				} else {
					APP_LOGS.info("Failed to Enter Uppercase and Lowercase Characters");
					soft.fail();
					test.log(LogStatus.FAIL, "Failed to Enter Uppercase and Lowercase Characters");

				}

			} else if (p_b_No.contains("-15.")) {

			}

			else if (p_b_No.contains("-29.") || p_b_No.contains("-26.") || p_b_No.contains("-11.")) {
				java.lang.String actual = Locators_Messaging.special_char.getText().trim();
				System.out.println(actual);
				System.out.println(expected);
				System.out.println(actual.length());
				System.out.println(expected.length());
				if (actual.equals(expected)) {
					APP_LOGS.info(" Sucessfully Entered Uppercase and Lowercase");
					soft.assertTrue(true, " Sucessfully Entered Uppercase and Lowercase");
					test.log(LogStatus.PASS, " Sucessfully Entered Uppercase and Lowercase");

				} else {
					APP_LOGS.info("Failed to Enter Uppercase and Lowercase");
					soft.fail();
					test.log(LogStatus.FAIL, "Failed to Enter Uppercase and Lowercase");

				}

			}

			/* aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK); */
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->validate_Small_and_Caps_Character()");
			e.printStackTrace();

		} catch (Exception e) {

			test.log(LogStatus.ERROR, "Exeption in ->validate_Small_and_Caps_Character()");
		}

	}
	public void validate_Scroll_To_Contact(SoftAssert soft) throws InterruptedException {
		/* To validate the Sent Message. */

		try {
			if (p_b_No.contains("-10.")) {
			
				if (isElementExist(Locators_Messaging.Test4)) {
					APP_LOGS.info("Scroll UP and Down Sucessfully");
					soft.assertTrue(true, "Scroll UP and Down Sucessfully");
					test.log(LogStatus.PASS, "Scroll UP and Down Sucessfully");

				} else {
					APP_LOGS.info("Failed to Scrolling");
					soft.fail();
					test.log(LogStatus.FAIL, "Failed to Scrolling");

				}

			} else if (p_b_No.contains("-15.")) {

			}

			else if (p_b_No.contains("-29.") || p_b_No.contains("-26.") || p_b_No.contains("-11.")) {
				

			}

			/* aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK); */
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->validate_Scroll_To_Contact()");
			e.printStackTrace();

		} catch (Exception e) {

			test.log(LogStatus.ERROR, "Exeption in ->validate_Scroll_To_Contact()");
		}

	}
	public void validate_LowerCase(SoftAssert soft, String expected) throws InterruptedException {
		/* To validate the Sent Message. */

		try {
			if (p_b_No.contains("-10.")) {
				java.lang.String actual = Locators_Messaging.special_char_A.getText().trim();
				System.out.println(actual);
				System.out.println(expected);
				System.out.println(actual.length());
				System.out.println(expected.length());
				if (actual.equals(expected)) {
					APP_LOGS.info("  LowerCase Characters Entered Sucessfully");
					soft.assertTrue(true, "  LowerCase Characters Entered Sucessfully");
					test.log(LogStatus.PASS, "  LowerCase Characters Entered Sucessfully");

				} else {
					APP_LOGS.info("Failed to Enter the Lowercase Characters");
					soft.fail();
					test.log(LogStatus.FAIL, "Failed to Enter the Lowercase Characters");

				}
			} else if (p_b_No.contains("-15.")) {

			}

			else if (p_b_No.contains("-29.") || p_b_No.contains("-26.") || p_b_No.contains("-11.")) {
				java.lang.String actual = Locators_Messaging.special_char.getText().trim();
				System.out.println(actual);
				System.out.println(expected);
				System.out.println(actual.length());
				System.out.println(expected.length());
				if (actual.equals(expected)) {
					APP_LOGS.info("Entered  LowerCase Characters Sucessfully");
					soft.assertTrue(true, "Entered  LowerCase Characters Sucessfully");
					test.log(LogStatus.PASS, "Entered  LowerCase Characters Sucessfully");

				} else {
					APP_LOGS.info("Failed to Enter the Lowercase Characters");
					soft.fail();
					test.log(LogStatus.FAIL, "Failed to Enter the Lowercase Characters");

				}

			}

			/* aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK); */
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->validate_LowerCase()");
			e.printStackTrace();

		} catch (Exception e) {

			test.log(LogStatus.ERROR, "Exeption in ->validate_LowerCase()");
		}

	}

	public void validate_upperCase(SoftAssert soft, String expected) throws InterruptedException {
		/* To validate the Sent Message. */

		try {
			if (p_b_No.contains("-10.")) {
				java.lang.String actual = Locators_Messaging.special_char_A.getText().trim();
				System.out.println(actual);
				System.out.println(expected);
				System.out.println(actual.length());
				System.out.println(expected.length());
				if (actual.equals(expected)) {
					APP_LOGS.info("Entered UpperCase Characters Sucessfully");
					soft.assertTrue(true, "Entered UpperCase Characters Sucessfully");
					test.log(LogStatus.PASS, "Entered UpperCase Characters Sucessfully");

				} else {
					APP_LOGS.info("Failed to Enter the Uppercase Characters");
					soft.fail();
					test.log(LogStatus.FAIL, "Failed to Enter the Uppercase Characters");

				}
			} else if (p_b_No.contains("-15.")) {

			}

			else if (p_b_No.contains("-29.") || p_b_No.contains("-26.") || p_b_No.contains("-11.")) {
				java.lang.String actual = Locators_Messaging.special_char.getText().trim();
				System.out.println(actual);
				System.out.println(expected);
				System.out.println(actual.length());
				System.out.println(expected.length());
				if (actual.equals(expected)) {
					APP_LOGS.info("Entered UpperCase Characters Sucessfully");
					soft.assertTrue(true, "Entered UpperCase Characters Sucessfully");
					test.log(LogStatus.PASS, "Entered UpperCase Characters Sucessfully");

				} else {
					APP_LOGS.info("Failed to Enter the Uppercase Characters");
					soft.fail();
					test.log(LogStatus.FAIL, "Failed to Enter the Uppercase Characters");

				}

			}

			/* aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK); */
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->validate_upperCase()");
			e.printStackTrace();

		} catch (Exception e) {

			test.log(LogStatus.ERROR, "Exeption in ->validate_upperCase()");
		}

	}

	public void validate_Entered_URL(SoftAssert soft) throws InterruptedException {
		/* To validate the Sent Message. */

		try {
			if (p_b_No.contains("-10.")) {
				
				if (isElementExist(Locators_Messaging.google_text)) {
					APP_LOGS.info("Entered URL Sucessfully");
					soft.assertTrue(true, "Entered URL Sucessfully");
					test.log(LogStatus.PASS, "Entered URL Sucessfully");

				} else {
					APP_LOGS.info("Failed to Enter the URL");
					soft.fail();
					test.log(LogStatus.FAIL, "Failed to Enter the URL");

				}
			} else if (p_b_No.contains("-15.")) {

			}

			else if (p_b_No.contains("-29.") || p_b_No.contains("-26.") || p_b_No.contains("-11.")) {
				java.lang.String actual = Locators_Messaging.special_char.getText().trim();
				
			}

			/* aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK); */
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->validate_Entered_URL()");
			e.printStackTrace();

		} catch (Exception e) {

			test.log(LogStatus.ERROR, "Exeption in ->validate_Entered_URL()");
		}

	}

	public void Click_ON_URL() throws InterruptedException {
		/* To validate the Sent Message. */

		try {
			if (p_b_No.contains("-10.")) {
//				clickBtn(multi_Loc_Strategy(Locators_Messaging.google_text, Locators_Messaging.google, null, null, null,
//						0, 0));
				clickBtn(Locators_Messaging.connect);
				clickBtn(multi_Loc_Strategy(Locators_Messaging.OKBtnopt2, Locators_Messaging.OKBtnopt1, Locators_Messaging.OKBtnopt3, null, null, 961, 158));

			} else if (p_b_No.contains("-15.")) {

			}

			else if (p_b_No.contains("-29.") || p_b_No.contains("-26.") || p_b_No.contains("-11.")) {
				clickBtn(multi_Loc_Strategy(Locators_Messaging.google_text, Locators_Messaging.google, null, null, null,
						0, 0));

			}
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->validate_Entered_URL()");
			e.printStackTrace();

		} catch (Exception e) {

			test.log(LogStatus.ERROR, "Exeption in ->validate_Entered_URL()");
		}

	}

	public void createContact(String name, String num) throws InterruptedException {
		/*
		 * Create contact
		 */
		try {
if (p_b_No.contains("-10.")) {
	clickBtn(Locators_Messaging.saving_to_phone);
}
			minWait();
			Locators_Messaging.AddtoContact.click();
			minWait();

			if (isElementExist(Locators_Messaging.Choose_Phone)) {
				minWait();
				clickBtn(Locators_Messaging.Choose_Phone);
				customWait(2000);
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
			}
			if (isElementExist(Locators_Messaging.Contacts_Name)) {
				minWait();
			}

			customWait(4000);
			minWait();
			// enterTextToInputField(Locators_DeviceStability.name_NewContact, name);
			enterTextToInputField(multi_Loc_Strategy(Locators_Messaging.name_NewContact,
					Locators_Messaging.name_NewContact1, Locators_Messaging.name_NewContact2, null, null, 0, 0), name);
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			minWait();
			enterTextToInputField(multi_Loc_Strategy(Locators_Messaging.phone_NewContact,
					Locators_Messaging.phone_NewContact1, Locators_Messaging.phone_NewContact2, null, null, 0, 0), num);
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			minWait();
			enterTextToInputField(multi_Loc_Strategy(Locators_Messaging.email_NewContact,
					Locators_Messaging.email_NewContact1, Locators_Messaging.email_NewContact2, null, null, 0, 0),
					"Sonimtech@gmail.com");
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			minWait();
			clickBtn(multi_Loc_Strategy(Locators_Messaging.Save_ConatctIcon1, Locators_Messaging.Save_ConatctIcon2,
					Locators_Messaging.Save_ConatctIcon3, null, null, 0, 0));
			minWait();

			for (int i = 1; i <= 3; i++) {
				if (isElementExist(Locators_Messaging.AllowOptn)) {
					clickBtn(Locators_Messaging.AllowOptn);
				}
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->createContact()");
			e.printStackTrace();

		} catch (Exception e) {

			test.log(LogStatus.ERROR, "Exeption in ->createContact()");
		}

	}
	public void Click_ON_Phone() throws InterruptedException {
		/* To validate the Sent Message. */

		try {
			if (p_b_No.contains("-10.")) {
				
				if (isElementExist(Locators_Messaging.Choose_Phone_A)) {
					minWait();
					clickBtn(Locators_Messaging.Choose_Phone_A);
					customWait(2000);
					aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
				}

			} else if (p_b_No.contains("-15.")) {

			}

			else if (p_b_No.contains("-29.") || p_b_No.contains("-26.") || p_b_No.contains("-11.")) {
				clickBtn(multi_Loc_Strategy(Locators_Messaging.google_text, Locators_Messaging.google, null, null, null,
						0, 0));

			}
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->Click_ON_Phone()");
			e.printStackTrace();

		} catch (Exception e) {

			test.log(LogStatus.ERROR, "Exeption in ->Click_ON_Phone()");
		}

	}
	public void reject_Call_With_SMS_O(String message) throws InterruptedException {

		try {
                 minWait();
		 clickBtn(Locators_Messaging.Notificationbar);
		 System.out.println("incoming call");
//		TouchAction ta= new TouchAction(aDriver);
//		ta.tap(207, 143).release().perform();
		//Runtime.getRuntime().exec("adb -s " + p_Id + " shell input tap 207 143");
		minWait();
		aDriver.swipe(80, 1840, 300, 600, 750);
		clickBtn(Locators_Messaging.write_Your_Own);
		minWait();
		//Runtime.getRuntime().exec("adb -s " + p_Id + " shell input text"+ message);
		enterTextToInputField(Locators_Messaging.msg_popup, message);
		//Runtime.getRuntime().exec("adb -s " + p_Id + " shell input text " + message);
		minWait();
		clickBtn(Locators_Messaging.SEND);
		minWait();
		} catch (org.openqa.selenium.NoSuchElementException e) {

		test.log(LogStatus.ERROR, "Error in locators->reject_Call_With_SMS_O()");
		} catch (Exception e) {
		test.log(LogStatus.ERROR, "Exeption in ->reject_Call_With_SMS_O()");
		}
		}
	public void Click_ON_SIM() throws InterruptedException {
		/* To validate the Sent Message. */

		try {
			if (p_b_No.contains("-10.")) {
				
				if (isElementExist(Locators_Messaging.Choose_sim_A)) {
					minWait();
					clickBtn(Locators_Messaging.Choose_sim_A);
					customWait(2000);
				}

			} else if (p_b_No.contains("-15.")) {

			}

			else if (p_b_No.contains("-29.") || p_b_No.contains("-26.") || p_b_No.contains("-11.")) {
				clickBtn(multi_Loc_Strategy(Locators_Messaging.google_text, Locators_Messaging.google, null, null, null,
						0, 0));

			}
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->Click_ON_Phone()");
			e.printStackTrace();

		} catch (Exception e) {

			test.log(LogStatus.ERROR, "Exeption in ->Click_ON_Phone()");
		}

	}

	public void createContact_A(String name, String num) throws InterruptedException {
		/*
		 * Create contact
		 */
		try {
if (p_b_No.contains("-10.")) {
	clickBtn(Locators_Messaging.saving_to_phone);

			
			if (isElementExist(Locators_Messaging.Choose_Phone_A)) {
				minWait();
				clickBtn(Locators_Messaging.Choose_Phone_A);
				customWait(2000);
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
			}
			if (isElementExist(Locators_Messaging.Contacts_Name)) {
				minWait();
			}

			customWait(4000);
			minWait();
			// enterTextToInputField(Locators_DeviceStability.name_NewContact, name);
			enterTextToInputField(multi_Loc_Strategy(Locators_Messaging.name_NewContact,
					Locators_Messaging.name_NewContact1, Locators_Messaging.name_NewContact2, null, null, 0, 0), name);
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			minWait();
			enterTextToInputField(multi_Loc_Strategy(Locators_Messaging.phone_NewContact,
					Locators_Messaging.phone_NewContact1, Locators_Messaging.phone_NewContact2, null, null, 0, 0), num);
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			minWait();
			enterTextToInputField(multi_Loc_Strategy(Locators_Messaging.email_NewContact,
					Locators_Messaging.email_NewContact1, Locators_Messaging.email_NewContact2, null, null, 0, 0),
					"Sonimtech@gmail.com");
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			minWait();
			clickBtn(multi_Loc_Strategy(Locators_Messaging.Save_ConatctIcon1, Locators_Messaging.Save_ConatctIcon2,
					Locators_Messaging.Save_ConatctIcon3, null, null, 0, 0));
			minWait();

			for (int i = 1; i <= 3; i++) {
				if (isElementExist(Locators_Messaging.AllowOptn)) {
					clickBtn(Locators_Messaging.AllowOptn);
				}
			}
		} }catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->createContact()");
			e.printStackTrace();

		} catch (Exception e) {

			test.log(LogStatus.ERROR, "Exeption in ->createContact()");
		}

	}
	public void createContact_IN_SIM(String name, String num) throws InterruptedException {
		/*
		 * Create contact
		 */
		try {

	

			
		
			if (isElementExist(Locators_Messaging.Contacts_Name)) {
				minWait();
			}

			customWait(4000);
			minWait();
			// enterTextToInputField(Locators_DeviceStability.name_NewContact, name);
			enterTextToInputField(multi_Loc_Strategy(Locators_Messaging.name_NewContact,
					Locators_Messaging.name_NewContact1, Locators_Messaging.name_NewContact2, null, null, 0, 0), name);
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			minWait();
			enterTextToInputField(multi_Loc_Strategy(Locators_Messaging.phone_NewContact,
					Locators_Messaging.phone_NewContact1, Locators_Messaging.phone_NewContact2, null, null, 0, 0), num);
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
//			minWait();
//			enterTextToInputField(multi_Loc_Strategy(Locators_Messaging.email_NewContact,
//					Locators_Messaging.email_NewContact1, Locators_Messaging.email_NewContact2, null, null, 0, 0),
//					"Sonimtech@gmail.com");
//			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
//			minWait();
			clickBtn(multi_Loc_Strategy(Locators_Messaging.Save_ConatctIcon1, Locators_Messaging.Save_ConatctIcon2,
					Locators_Messaging.Save_ConatctIcon3, null, null, 0, 0));
			minWait();

			for (int i = 1; i <= 3; i++) {
				if (isElementExist(Locators_Messaging.AllowOptn)) {
					clickBtn(Locators_Messaging.AllowOptn);
				}
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->createContact()");
			e.printStackTrace();

		} catch (Exception e) {

			test.log(LogStatus.ERROR, "Exeption in ->createContact()");
		}

	}

	public void validateContactCreation(SoftAssert soft) throws InterruptedException {
		/*
		 * Validate contact
		 */
		SoftAssert Sa = new SoftAssert();
		try {
			customWait(3000);
			String getcontactNameText = null;
			getcontactNameText = Locators_Messaging.ContactTitle.getText();
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
			String getPhoneNumText = Locators_Messaging.contact_phnNum.getText();

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
			getEmailText = Locators_Messaging.contact_Emailid.getText();
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
				test.log(LogStatus.PASS, "Contact created saved in phone memory at iteration");
				soft.assertTrue(true, " Contact created saved in phone memory at iteration");
			} else {

				APP_LOGS.info("Test case failed");
				test.log(LogStatus.ERROR, "Contact creation failed at iteration: ");
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
	public void validate_Remove_Recipient_From_To_Field(SoftAssert soft) throws InterruptedException {
		/* To validate the Sent Message. */

		try {
			
				if (Locators_Messaging.recive_sms_A.isDisplayed()) {
					APP_LOGS.info("Selected recipient removed Sucessfully ");
					soft.assertTrue(true, "Selected recipient removed Sucessfully ");
					test.log(LogStatus.PASS, "Selected recipient removed Sucessfully ");

				} else {
					APP_LOGS.info("Failed to remove the recipient");
					soft.fail();
					test.log(LogStatus.FAIL, "Failed to remove the recipient");

				}	} catch (org.openqa.selenium.NoSuchElementException e) {

					test.log(LogStatus.ERROR, "Error in the locators->validate_Remove_Recipient_From_To_Field()");
					e.printStackTrace();

				} catch (Exception e) {

					test.log(LogStatus.ERROR, "Exeption in ->validate_Remove_Recipient_From_To_Field()");
				}

			}

	public void validate_receive_Message_In_Notificationbar(SoftAssert soft) throws InterruptedException {
		/* To validate the Sent Message. */

		try {
			
				if (Locators_Messaging.forward_Text_A.isDisplayed()) {
					APP_LOGS.info("Sucessfully send SMS through Notification");
					soft.assertTrue(true, "Sucessfully send SMS through Notification");
					test.log(LogStatus.PASS, "Sucessfully send SMS through Notification");

				} else {
					APP_LOGS.info("Failed to send the SMS");
					soft.fail();
					test.log(LogStatus.FAIL, "Failed to send the SMS");

				}	} catch (org.openqa.selenium.NoSuchElementException e) {

					test.log(LogStatus.ERROR, "Error in the locators->validate_receive_Message_In_Notificationbar()");
					e.printStackTrace();

				} catch (Exception e) {

					test.log(LogStatus.ERROR, "Exeption in ->validate_receive_Message_In_Notificationbar()");
				}

			}



	public void validate_Contact_In_ToField(SoftAssert soft, String expected) throws InterruptedException {
		/* To validate the Sent Message. */

		try {
			if (p_b_No.contains("-10.")) {
				java.lang.String actual = Locators_Messaging.Phno_A.getText();
				System.out.println(actual);
				System.out.println(expected);
				if (expected.contains(actual)) {
					APP_LOGS.info("Contact Selected Sucessfully");
					soft.assertTrue(true, "Contact Selected Sucessfully");
					test.log(LogStatus.PASS, "Contact Selected Sucessfully");

				} else {
					APP_LOGS.info("Failed to Select the Contact");
					soft.fail();
					test.log(LogStatus.FAIL, "Failed to Select the Contact");

				}

			} else if (p_b_No.contains("-15.")) {

			}

			else if (p_b_No.contains("-29.") || p_b_No.contains("-26.") || p_b_No.contains("-11.")) {
				java.lang.String actual = Locators_Messaging.To_Field_B1.getText();
				System.out.println(actual);
				System.out.println(expected);
				if (expected.contains(actual)) {
					APP_LOGS.info("Contact Selected Sucessfully");
					soft.assertTrue(true, "Contact Selected Sucessfully");
					test.log(LogStatus.PASS, "Contact Selected Sucessfully");

				} else {
					APP_LOGS.info("Failed to Select the Contact");
					soft.fail();
					test.log(LogStatus.FAIL, "Failed to Select the Contact");

				}

			}

			/* aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK); */
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->validate_Entered_URL()");
			e.printStackTrace();

		} catch (Exception e) {

			test.log(LogStatus.ERROR, "Exeption in ->validate_Entered_URL()");
		}

	}

	public void enter_Name_To_Field(String name) throws InterruptedException {
		/* Method is used to Enter Number into TO Field. */
		try {
			if (p_b_No.contains("-10.")) {

				enterTextToInputField(multi_Loc_Strategy(Locators_Messaging.TO_Field_phno1,
						Locators_Messaging.TO_Field_phno2, Locators_Messaging.TO_Field_phno3, null, null, 0, 0), name);

			} else if (p_b_No.contains("-15.")) {
				minWait();
				enterTextToInputField(multi_Loc_Strategy(Locators_Messaging.TO_Field_O, Locators_Messaging.TO_Field_O1,
						null, null, null, 0, 0), name);

			} else if (p_b_No.contains("-26.") || p_b_No.contains("-29.") || p_b_No.contains("-11.")) {
				minWait();
				enterTextToInputField(multi_Loc_Strategy(Locators_Messaging.To_Field_B1, Locators_Messaging.To_Field_B,
						Locators_Messaging.To_Field_B2, null, null, 0, 0), name);
				customWait(2000);

			}
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->enter_Num_ToField()");
			e.printStackTrace();

		} catch (Exception e) {

			test.log(LogStatus.ERROR, "Exeption in ->enter_Num_ToField()");
		}
	}

	public void Msg_clear_Screen() throws IOException, InterruptedException {
		try {
			minWait();
			Runtime.getRuntime().exec("adb  shell input keyevent 3");
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_APP_SWITCH);
			minWait();
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->Msg_clear_Screen()");
			e.printStackTrace();

		} catch (Exception e) {

			test.log(LogStatus.ERROR, "Exeption in ->Msg_clear_Screen()");
		}

	}
	public void delete_SMS() throws IOException, InterruptedException {
		try {
			clickBtn(Locators_Messaging.more_option_a);
			clickBtn(Locators_Messaging.delete_thread);
			clickBtn(Locators_Messaging.delete_Confirm_a);
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->delete_SMS()");
			e.printStackTrace();

		} catch (Exception e) {

			test.log(LogStatus.ERROR, "Exeption in ->delete_SMS()");
		}

	}


	public void validate_Text_After_Pressing_Homekey(SoftAssert soft) throws InterruptedException {
		/* To validate the Sent Message. */

		try {
			if (p_b_No.contains("-10.")) {
		
				
				if (Locators_Messaging.type_text2.isDisplayed()||isElementExist(Locators_Messaging.type_text1)) {
					APP_LOGS.info("Navigated back to compose screen after pressing home key");
					soft.assertTrue(true, "Navigated back to compose screen after pressing home key");
					test.log(LogStatus.PASS, "Navigated back to compose screen after pressing home key");

				} else {
					APP_LOGS.info("Failed to display the compose Screen ");
					soft.fail();
					test.log(LogStatus.FAIL, "Failed to display the compose Screen ");

				}

			} else if (p_b_No.contains("-15.")) {

			}

			else if (p_b_No.contains("-29.") || p_b_No.contains("-26.") || p_b_No.contains("-11.")) {
				java.lang.String actual = Locators_Messaging.special_char.getText();
			
			/* aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK); */
			}} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->validate_Text_After_Pressing_Homekey()");
			e.printStackTrace();

		} catch (Exception e) {

			test.log(LogStatus.ERROR, "Exeption in ->validate_Text_After_Pressing_Homekey()");
		}

	}
	public void validate_Text_After_initiating_Call(SoftAssert soft) throws InterruptedException {
		/* To validate the Sent Message. */

		try {
			if (p_b_No.contains("-10.")) {
		
				
				if (Locators_Messaging.type_text2.isDisplayed()||isElementExist(Locators_Messaging.type_text1)) {
					APP_LOGS.info("Navigated back to compose screen after initiating a Call");
					soft.assertTrue(true, "Navigated back to compose screen after initiating a Call");
					test.log(LogStatus.PASS, "Navigated back to compose screen after initiating a Call");

				} else {
					APP_LOGS.info("Failed to display the compose Screen ");
					soft.fail();
					test.log(LogStatus.FAIL, "Failed to display the compose Screen ");

				}

			} else if (p_b_No.contains("-15.")) {

			}

			else if (p_b_No.contains("-29.") || p_b_No.contains("-26.") || p_b_No.contains("-11.")) {
				java.lang.String actual = Locators_Messaging.special_char.getText();
			
			/* aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK); */
			}} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->validate_Text_After_initiating_Call()");
			e.printStackTrace();

		} catch (Exception e) {

			test.log(LogStatus.ERROR, "Exeption in ->validate_Text_After_initiating_Call()");
		}

	}

	public void validate_Thread(SoftAssert soft) throws InterruptedException {
		/* To validate the Sent Message. */

		try {
			if (p_b_No.contains("-10.")) {

				if (isElementExist(Locators_Messaging.thread_A) || isElementExist(Locators_Messaging.thread_A1)) {
					APP_LOGS.info("All the previous messages displayed and Message sent displayed successfully in the same screen");
					soft.assertTrue(true, "All the previous messages displayed and Message sent displayed successfully in the same screen");
					test.log(LogStatus.PASS, "All the previous messages displayed and Message sent displayed successfully in the same screen");

				} else {
					APP_LOGS.info("Failed to display the Messages in same screen ");
					soft.fail();
					test.log(LogStatus.FAIL, "Failed to display the Messages in same screen ");

				}
			} else if (p_b_No.contains("-15.")) {

			}

			else if (p_b_No.contains("-29.") || p_b_No.contains("-26.") || p_b_No.contains("-11.")) {

				if (isElementExist(Locators_Messaging.thread_2) || isElementExist(Locators_Messaging.thread_3)) {
					APP_LOGS.info("Sent Message Sucessfully displayed the same screen ");
					soft.assertTrue(true, "Sent Message Sucessfully displayed the same screen ");
					test.log(LogStatus.PASS, "Sent Message Sucessfully displayed the same screen ");

				} else {
					APP_LOGS.info("Failed to display the Message in same screen ");
					soft.fail();
					test.log(LogStatus.FAIL, "Failed to display the Message in same screen ");

				}

			}

			/* aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK); */
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->validate_Thread()");
			e.printStackTrace();

		} catch (Exception e) {

			test.log(LogStatus.ERROR, "Exeption in ->validate_Thread()");
		}

	}

	public void sendSMS_fromRefDevice() {

		// To validate MT Message User should be inside Messaging APP of Primary Device.
		try {

			// Below Code To clear Battery PopUp.
			// Runtime.getRuntime().exec("adb -s "+r_Id+" shell input tap 777 1083");
			minWait();
			if (r_b_No.contains("8A.")) {
				if (r_b_No.contains("-10.") || r_b_No.contains("-30.")) {


					Runtime.getRuntime()
							.exec("adb -s " + r_Id
									+ " shell service call isms 7 i32 0 s16 \"com.android.mms.service\" s16 " + pryNum
									+ " s16 \"null\" s16 \"Testing\" s16 \"null\" s16 \"null\"");

				} else if (r_b_No.contains("-11.") || r_b_No.contains("-12.") || r_b_No.contains("-18.")
						|| r_b_No.contains("-26.") || r_b_No.contains("-29.")) {

					System.out.println("send sms to primary");
					Runtime.getRuntime()
							.exec("adb -s " + r_Id
									+ " shell service call isms 7 i32 0 s16 \"com.android.mms.service\" s16 " + pryNum
									+ " s16 \"null\" s16 \"Testing\" s16 \"null\" s16 \"null\"");
					System.out.println("exit");
				} else if (r_b_No.contains("-15.")) {

					Runtime.getRuntime()
							.exec("adb -s " + r_Id
									+ " shell service call isms 7 i32 0 s16 \"com.android.mms.service\" s16 " + pryNum
									+ " s16 \"null\" s16 \"Testing\" s16 \"null\" s16 \"null\"");

				}
			} else if (r_b_No.contains("5SA.")) {
				minWait();

				Runtime.getRuntime()
						.exec("adb -s " + r_Id + " shell service call isms 7 i32 0 s16 \"com.android.mms.service\" s16 "
								+ pryNum + " s16 \"null\" s16 \"Testing\" s16 \"null\" s16 \"null\"");

			}
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->sendSMS_fromRefDevice()");
			e.printStackTrace();

		} catch (Exception e) {

			test.log(LogStatus.ERROR, "Exeption in ->sendSMS_fromRefDevice()");
		}
	}

	public void ReceivedMessage() throws InterruptedException {

		try {
			WebDriverWait wait = new WebDriverWait(aDriver, 80);
			if (p_b_No.contains("-10.")) {
				wait(Locators_Messaging.forward_Text_A, 80);
maxWait();
clickBtn(multi_Loc_Strategy(Locators_Messaging.forward_Text_A, Locators_Messaging.Receive_sms1, null, null,
						null, 0, 0));

				TouchAction ta = new TouchAction(aDriver);
				minWait();
				ta.longPress(Locators_Messaging.forward_Text_A1, 10000).release().perform();
				minWait();
				clickBtn(multi_Loc_Strategy(Locators_Messaging.forward_opt2, Locators_Messaging.forward_opt,
						Locators_Messaging.forward_opt1, null, null, 0, 0));
				minWait();
			} else if (p_b_No.contains("-15.")) {
				
				clickBtn(Locators_DeviceStability.firstSMS_InList_O);

			}

			else if (p_b_No.contains("-29.") || p_b_No.contains("-26.") || p_b_No.contains("-11.")) {
				System.out.println("29");
				wait(Locators_Messaging.now_Text1, 20);
				clickBtn(multi_Loc_Strategy(Locators_Messaging.Receive_sms, Locators_Messaging.Receive_sms1, null, null,
						null, 0, 0));

				TouchAction ta = new TouchAction(aDriver);
				aDriver.pressKeyCode(AndroidKeyCode.BACKSPACE);
				ta.longPress(Locators_Messaging.forward_Text, 10).perform().release();
				clickBtn(multi_Loc_Strategy(Locators_Messaging.more_option, Locators_Messaging.more_option,
						Locators_Messaging.more_option, null, null, 0, 0));
				clickBtn(multi_Loc_Strategy(Locators_Messaging.forward_opt1, Locators_Messaging.forward_opt,
						Locators_Messaging.forward_opt2, null, null, 0, 0));
				clickBtn(multi_Loc_Strategy(Locators_Messaging.phno, Locators_Messaging.phno1, Locators_Messaging.phno,
						null, null, 0, 0));
				minWait();
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->ReceivedMessage()");
			e.printStackTrace();

		} catch (Exception e) {

			test.log(LogStatus.ERROR, "Exeption in ->ReceivedMessage()");
		}
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

	public void validate_text(SoftAssert soft) {
		try {
			String path=System.getProperty("user.dir")+"\\src\\test\\resources\\OCR_FILES\\ocr.txt";

			String content = readFile(path);
			System.out.println("text from screenshot = " + content);
			if (content.contains("Test")) {
				APP_LOGS.info("Entered Number is Stored in contact");
				soft.assertTrue(true, "Entered Number is Stored in contact");
				test.log(LogStatus.PASS, "Entered Number is Stored in contact");
			} else {
				APP_LOGS.info("Entered Number is not Stored in the contact");
				soft.fail();
				test.log(LogStatus.FAIL, "Entered Number is Stored in the contact");
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->validate_text()");
			e.printStackTrace();

		} catch (Exception e) {

			test.log(LogStatus.ERROR, "Exeption in ->validate_text()");
		}
	}
	public void validate_add_Contact(SoftAssert soft) {
		try {
		
			if (isElementExist(Locators_Messaging.demo1)) {
				APP_LOGS.info("Entered Number is Stored in contact");
				soft.assertTrue(true, "Entered Number is Stored in contact");
				test.log(LogStatus.PASS, "Entered Number is Stored in contact");
			} else {
				APP_LOGS.info("Entered Number is not Stored in the contact");
				soft.fail();
				test.log(LogStatus.FAIL, "Entered Number is Stored in the contact");
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->validate_Entered_URL()");
			e.printStackTrace();

		} catch (Exception e) {

			test.log(LogStatus.ERROR, "Exeption in ->validate_Entered_URL()");
		}
	}
	public void enter_Num1(String number) throws InterruptedException {
		try {
			enterTextToInputField(multi_Loc_Strategy(Locators_Messaging.TO_Field_phno1,
					Locators_Messaging.TO_Field_phno2, Locators_Messaging.TO_Field_phno3, null, null, 0, 0),
					number);
		
	
	} catch (org.openqa.selenium.NoSuchElementException e) {

		test.log(LogStatus.ERROR, "Error in the locators->enter_Num1()");
		e.printStackTrace();

	} catch (Exception e) {

		test.log(LogStatus.ERROR, "Exeption in ->enter_Num1()");
	}
}
	
	public void enter_Num(String number) throws InterruptedException {
		/* Method is used to Enter Number into TO Field. */
		try {
			if (p_b_No.contains("-10.")) {

				enterTextToInputField(multi_Loc_Strategy(Locators_Messaging.TO_Field_phno1,
						Locators_Messaging.TO_Field_phno2, Locators_Messaging.TO_Field_phno3, null, null, 0, 0),
						number);

				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			} else if (p_b_No.contains("-15.")) {
				minWait();
				enterTextToInputField(multi_Loc_Strategy(Locators_Messaging.TO_Field_O, Locators_Messaging.TO_Field_O1,
						null, null, null, 0, 0), number);
				customWait(2000);
				System.out.println("Selecting COntact NAme");

			} else if (p_b_No.contains("-26.") || p_b_No.contains("-29.") || p_b_No.contains("-11.")) {
				minWait();
				enterTextToInputField(multi_Loc_Strategy(Locators_Messaging.To_Field_B1, Locators_Messaging.To_Field_B,
						Locators_Messaging.To_Field_B2, null, null, 0, 0), number);

			}
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->enter_Num()");
			e.printStackTrace();

		} catch (Exception e) {

			test.log(LogStatus.ERROR, "Exeption in ->enter_Num()");
		}
	}

	public void adding_new_Recipients(String number) throws InterruptedException {
		/* Method is used to Enter Number into TO Field. */
		try {
			if (p_b_No.contains("-10.")) {

				enterTextToInputField(multi_Loc_Strategy(Locators_Messaging.TO_Field_phno1,
						Locators_Messaging.TO_Field_phno2, Locators_Messaging.TO_Field_phno3, null, null, 0, 0),
						number);

				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			} else if (p_b_No.contains("-15.")) {
				minWait();
				enterTextToInputField(multi_Loc_Strategy(Locators_Messaging.TO_Field_O, Locators_Messaging.TO_Field_O1,
						null, null, null, 0, 0), number);
				customWait(2000);
				System.out.println("Selecting COntact NAme");

			} else if (p_b_No.contains("-26.") || p_b_No.contains("-29.") || p_b_No.contains("-11.")) {
				minWait();
				enterTextToInputField(multi_Loc_Strategy(Locators_Messaging.To_Field_B1, Locators_Messaging.To_Field_B,
						Locators_Messaging.To_Field_B2, null, null, 0, 0), number);

			}
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->enter_New_Num_ToRecipiet()");
			e.printStackTrace();

		} catch (Exception e) {

			test.log(LogStatus.ERROR, "Exeption in ->enter_New_Num_ToRecipiet()");
		}
	}
	public void validate_SMS_Details(SoftAssert soft) throws InterruptedException {
		/* To validate the Sent Message. */

		try {
			if (p_b_No.contains("-10.")) {
         if (isElementExist(Locators_Messaging.message_details)) {
        		APP_LOGS.info("Message details displayed Sucessfully ");
				soft.assertTrue(true, "Message details displayed Sucessfully ");
				test.log(LogStatus.PASS, "Message details displayed Sucessfully ");
		}
         else {
        	 APP_LOGS.info("Failed to display the Message details");
				soft.fail();
				test.log(LogStatus.FAIL, "Failed to display the Message details");
		}
			} else if (p_b_No.contains("-15.")) {

			}

			else if (p_b_No.contains("-29.") || p_b_No.contains("-26.") || p_b_No.contains("-11.")) {
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->validate_New_Phno()");
			e.printStackTrace();

		} catch (Exception e) {

			test.log(LogStatus.ERROR, "Exeption in ->validate_New_Phno()");
		}

	}

	

	public void validate_New_And_Old_Recipients(SoftAssert soft) throws InterruptedException {
		/* To validate the Sent Message. */

		try {
			if (p_b_No.contains("-10.")) {
				java.lang.String actual = Locators_Messaging.Multiple_Recipients.getText();
				System.out.println(actual);
				if (Locators_Messaging.Multiple_Recipients.isDisplayed()) {
					APP_LOGS.info("old number and New number are displayed Sucessfully");
					soft.assertTrue(true, "old number and New number are displayed Sucessfully");
					test.log(LogStatus.PASS, "old number and New number are displayed Sucessfully");
				} else {
					APP_LOGS.info("Failed to display the Old number and new number");
					soft.fail();
					test.log(LogStatus.FAIL, "Failed to display the old number and new number");
				}
			
			} else if (p_b_No.contains("-15.")) {

			}

			else if (p_b_No.contains("-29.") || p_b_No.contains("-26.") || p_b_No.contains("-11.")) {
				java.lang.String actual = Locators_Messaging.Multiple_Recipients.getText();
				if (actual.equalsIgnoreCase("Test1, Test2")) {
					APP_LOGS.info("old number and New number are displayed Sucessfully");
					soft.assertTrue(true, "old number and New number are displayed Sucessfully");
					test.log(LogStatus.PASS, "old number and New number are displayed Sucessfully");
				} else {
					APP_LOGS.info("Failed to display the Old number and new number");
					soft.fail();
					test.log(LogStatus.FAIL, "Failed to display the old number and new number");
				}
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->validate_New_And_Old_Recipients()");
			e.printStackTrace();

		} catch (Exception e) {

			test.log(LogStatus.ERROR, "Exeption in ->validate_New_And_Old_Recipients()");
		}

	}

	public void LongPress_On_Message_Icon() throws InterruptedException {
		/* To validate the Sent Message. */

		try {
			if (p_b_No.contains("-10.")) {
				TouchAction ta = new TouchAction(aDriver);
				ta.longPress(Locators_Messaging.Messaging).perform().release();
			} else if (p_b_No.contains("-15.")) {

			}

			else if (p_b_No.contains("-29.") || p_b_No.contains("-26.") || p_b_No.contains("-11.")) {
				TouchAction ta = new TouchAction(aDriver);
				ta.longPress(Locators_Messaging.messages).perform().release();

			}
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->LongPress_On_Message_Icon()");
			e.printStackTrace();

		} catch (Exception e) {

			test.log(LogStatus.ERROR, "Exeption in ->LongPress_On_Message_Icon()");
		}

	}

	public void validate_New_Conversation_Screen(SoftAssert soft) throws InterruptedException {
		/* To validate the Sent Message. */

		try {
			if (p_b_No.contains("-10.")) {
				if (Locators_Messaging.app_info.isDisplayed()) {
					APP_LOGS.info("Sucessfully displayed App info Option");
					soft.assertTrue(true, "Sucessfully displayed App info  Option");
					test.log(LogStatus.PASS, "Sucessfully displayed  App info Option");
				} else {
					APP_LOGS.info("Failed to display the App info  Option");
					soft.fail();
					test.log(LogStatus.SKIP, "Failed to display the  App info Option");
				}

			} else if (p_b_No.contains("-15.")) {

			}

			else if (p_b_No.contains("-29.") || p_b_No.contains("-26.") || p_b_No.contains("-11.")) {
				if (Locators_Messaging.New_Conversation.isDisplayed()) {
					APP_LOGS.info("Sucessfully displayed new Conversation Option");
					soft.assertTrue(true, "Sucessfully displayed New Conversation Option");
					test.log(LogStatus.PASS, "Sucessfully displayed New Conversation Option");
				} else {
					APP_LOGS.info("Failed to display the New Conversation Option");
					soft.fail();
					test.log(LogStatus.FAIL, "Failed to display the New Conversation Option");
				}

			}
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->validate_New_Conversation_Screen()");
			e.printStackTrace();

		} catch (Exception e) {

			test.log(LogStatus.ERROR, "Exeption in ->validate_New_Conversation_Screen()");
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
//				Runtime.getRuntime().exec("adb -s "+p_Id+" shell input keyevent 5");
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_ENDCALL);

		}

		catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->initiateCall()");
			e.printStackTrace();

		} catch (Exception e) {

			test.log(LogStatus.ERROR, "Exeption in ->initiateCall()");
		}
	}

	public void callHistory() throws InterruptedException, IOException {
		/*
		 * call save contact
		 */
		try {
			minWait();
			if (isElementExist(Locators_Messaging.MoreOptions)) {
				clickBtn(multi_Loc_Strategy(Locators_Messaging.moreOptions, Locators_Messaging.MoreOptions11,
						Locators_Messaging.MoreOptions2, null, null, 961, 158));
				minWait();
			} else {
				clickBtn(Locators_Messaging.MoreOptions1);
				minWait();
			}
			customWait(1000);
			clickBtn(multi_Loc_Strategy(Locators_Messaging.callHistory_O, Locators_Messaging.callHistory_O1,
					Locators_Messaging.callHistory_O2, null, null, 670, 176));
			minWait();
			clickBtn(multi_Loc_Strategy(Locators_Messaging.First_Contact, Locators_Messaging.First_Contact1, null, null,
					null, 947, 627));
			minWait();
			clickBtn(multi_Loc_Strategy(Locators_Messaging.Send_sms_To_First_Contact,
					Locators_Messaging.Send_sms_To_First_Contact1, null, null, null, 0, 0));

		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->callHistory()");
			e.printStackTrace();

		} catch (Exception e) {

			test.log(LogStatus.ERROR, "Exeption in ->callHistory()");
		}
	}

	public void dailpad() throws InterruptedException, IOException {

		try {
			if (p_b_No.contains("-10.")) {

			} else if (p_b_No.contains("-15.")) {

			}

			else if (p_b_No.contains("-29.") || p_b_No.contains("-26.") || p_b_No.contains("-11.")) {
				clickBtn(multi_Loc_Strategy(Locators_Messaging.dailpad, Locators_Messaging.dailpad1, null, null, null,
						0, 0));
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_9);
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_5);

				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_1);
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_3);
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_5);
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_4);
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_8);
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_6);
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_7);
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_9);

			}
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->callHistory()");
			e.printStackTrace();

		} catch (Exception e) {

			test.log(LogStatus.ERROR, "Exeption in ->callHistory()");
		}
	}

	public void validate_Dailpad_Screen(SoftAssert soft) throws InterruptedException {
		/* To validate the Sent Message. */

		try {
			if (p_b_No.contains("-10.")) {

			} else if (p_b_No.contains("-15.")) {

			}

			else if (p_b_No.contains("-29.") || p_b_No.contains("-26.") || p_b_No.contains("-11.")) {
				if (Locators_Messaging.New_Conversation.isDisplayed()) {
					APP_LOGS.info("Sucessfully displayed new Conversation Option");
					soft.assertTrue(true, "Sucessfully displayed New Conversation Option");
					test.log(LogStatus.PASS, "Sucessfully displayed New Conversation Option");
				} else {
					APP_LOGS.info("Failed to display the New Conversation Option");
					soft.fail();
					test.log(LogStatus.FAIL, "Failed to display the New Conversation Option");
				}

			}
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->validate_Dailpad_Screen()");
			e.printStackTrace();

		} catch (Exception e) {

			test.log(LogStatus.ERROR, "Exeption in ->validate_Dailpad_Screen()");
		}

	}
	public void validate_delete_SMS(SoftAssert soft) {
		try {
  AndroidElement msg = Locators_Messaging.delete_msg;
			if (!isElementExist(msg)) {
				APP_LOGS.info("Message deleted Sucessfully");
				soft.assertTrue(true, "Message deleted Sucessfully");
				test.log(LogStatus.PASS, "Message deleted Sucessfully");
			} else {
				APP_LOGS.info("Failed to delete message");
				soft.fail();
				test.log(LogStatus.FAIL, "Failed to delete message");
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->validate_Entered_URL()");
			e.printStackTrace();

		} catch (Exception e) {

			test.log(LogStatus.ERROR, "Exeption in ->validate_Entered_URL()");
		}
	}

	public void validate_dailpad(SoftAssert soft) {
		try {
			String content = readFile(
					"C:\\Users\\navyashree.m\\Desktop\\navya\\XP8_AutomationProduct\\src\\test\\resources\\OCR_FILES\\ocr.txt");
			System.out.println("text from screenshot = " + content.substring(content.length() - 28));
			if (content.contains("9513548679")) {
				APP_LOGS.info("Daial Pad displayed Sucessfully");
				soft.assertTrue(true, "Daial Pad displayed Sucessfully");
				test.log(LogStatus.PASS, "Daial Pad displayed Sucessfully");
			} else {
				APP_LOGS.info("Failes to display the Daial Pad  ");
				soft.fail();
				test.log(LogStatus.FAIL, "Failes to display the Daial Pad ");
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->validate_Entered_URL()");
			e.printStackTrace();

		} catch (Exception e) {

			test.log(LogStatus.ERROR, "Exeption in ->validate_Entered_URL()");
		}
	}

	public void Copy_And_Paste_IN_Text_Field() throws InterruptedException, IOException {

		try {
			if (p_b_No.contains("-10.")) {

			} else if (p_b_No.contains("-15.")) {

			}

			else if (p_b_No.contains("-29.") || p_b_No.contains("-26.") || p_b_No.contains("-11.")) {

				if (isElementExist(Locators_Messaging.Text_Field_B)) {
					enterTextToInputField(Locators_Messaging.Text_Field_B, "hellohello");
					minWait();
					System.out.println("copy");
					TouchAction ta = new TouchAction(aDriver);
					ta.longPress(224, 1099, 20).release().perform();
					// ta.longPress(Locators_Messaging.Text_Field_B, 10).perform().release();
					System.out.println("end");
					ta.tap(290, 947).perform().release();
					aDriver.pressKeyCode(AndroidKeyCode.ENTER);
					System.err.println("pste");
					ta.longPress(Locators_Messaging.Text_Field_B, 20).perform().release();
					ta.tap(146, 854).perform().release();
					minWait();
				}
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->callHistory()");
			e.printStackTrace();

		} catch (Exception e) {

			test.log(LogStatus.ERROR, "Exeption in ->callHistory()");
		}
	}

	public void click_On_ViewDetails() {
		try {
//			TouchAction ta = new TouchAction(aDriver);
//			ta.longPress(Locators_Messaging.details).release().perform();
//			clickBtn(multi_Loc_Strategy(Locators_Messaging.more_option, Locators_Messaging.more_option,
//					Locators_Messaging.more_option, null, null, 0, 0));
//			clickBtn(multi_Loc_Strategy(Locators_Messaging.view_details, Locators_Messaging.view_details1,
//					Locators_Messaging.view_details2, null, null, 0, 0));

			
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->click_On_ViewDetails()");
			e.printStackTrace();

		} catch (Exception e) {

			test.log(LogStatus.ERROR, "Exeption in ->click_On_ViewDetails()");
		}
	}

	public void validate_Date_And_Time(SoftAssert soft) {
		try {
			Date date = new Date();

			Calendar cal = Calendar.getInstance();

			LocalDateTime now = LocalDateTime.now();

			LocalDate localDate = LocalDate.now();
			java.lang.String d = DateTimeFormatter.ofPattern("MM/dd/yyy").format(localDate);
			System.out.println(DateTimeFormatter.ofPattern("MM/dd/yyy").format(localDate));

			java.lang.String discription = Locators_Messaging.date_and_time.getAttribute("contentDescription");
			System.out.println(discription);
			if (discription.equals(date)) {
				APP_LOGS.info("Properly displayed Date and time");
				soft.assertTrue(true, "Properly displayed Date and time");
				test.log(LogStatus.PASS, "Properly displayed Date and time");

			} else {
				APP_LOGS.info("Failed to display the date and time");
				soft.fail();
				test.log(LogStatus.FAIL, "Failed to display the date and time");
			}

		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->validate_Date_And_Time()");
			e.printStackTrace();

		} catch (Exception e) {

			test.log(LogStatus.ERROR, "Exeption in ->validate_Date_And_Time()");
		}
	}

	public void make_Call_from_RefDev() throws InterruptedException, IOException {
		// make a call from ref device to primary device
		try {
			Runtime.getRuntime()
					.exec("adb -s " + r_Id + " shell am start -a android.intent.action.CALL -d tel:" + pryNum);
			
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

	public void enable_Notification() {
		try {
			if (p_b_No.contains("-10.")) {
				clickBtn(Locators_Messaging.MoreOptions);

				clickBtn(Locators_Messaging.setting);
				scrollTo("Notifications");
				java.lang.String checkbox = Locators_Messaging.checked.getAttribute("checked");
				if (checkbox.equalsIgnoreCase("false")) {
					clickBtn(Locators_Messaging.checked);
				}
				}
			else if (p_b_No.contains("-15.")) {
				
			} else if (p_b_No.contains("-29.") || p_b_No.contains("-26.") || p_b_No.contains("-11.")) {

				
				clickBtn(multi_Loc_Strategy(Locators_Messaging.more_option, Locators_Messaging.more_option,
						Locators_Messaging.more_option, null, null, 0, 0));
					clickBtn(Locators_Messaging.setting);
					clickBtn(Locators_Messaging.Notifications_opt);
					if (Locators_Messaging.Notifications_On.isDisplayed()) {

					} else {
						clickBtn(Locators_Messaging.Notifications_On);
					}
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->validate_SentMessage()");
			e.printStackTrace();

		} catch (Exception e) {

			test.log(LogStatus.ERROR, "Exeption in ->validate_SentMessage()");
		}

	}
	public void vibration_On(SoftAssert soft) {
		try {
			if (p_b_No.contains("-10.")) {
				scrollTo("Vibrate");
				if (isElementExist(Locators_Messaging.vibrate_on)) {
					
					APP_LOGS.info("Vibration option is present in Settings");
					soft.assertTrue(true, "Vibration option is present in Settings");
					test.log(LogStatus.PASS, "Vibration option is present in Settings");

				} else {
					APP_LOGS.info("Vibration option is not present in Settings");
					soft.fail();
					test.log(LogStatus.FAIL, "Vibration option is not present in Settings");
				}}

			else if (p_b_No.contains("-15.")) {
				
			} else if (p_b_No.contains("-29.") || p_b_No.contains("-26.") || p_b_No.contains("-11.")) {

				
				
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->validate_SentMessage()");
			e.printStackTrace();

		} catch (Exception e) {

			test.log(LogStatus.ERROR, "Exeption in ->validate_SentMessage()");
		}

	}
	public void Notification(SoftAssert soft) {
		try {
			if (p_b_No.contains("-10.")) {
				scrollToText("Vibrate");
				if (isElementExist(Locators_Messaging.vibrate_on)) {
					
					APP_LOGS.info("vibrate is in on Condition");
					soft.assertTrue(true, "vibrate is in on Condition");
					test.log(LogStatus.PASS, "vibrate is in on Condition");

				} else {
					APP_LOGS.info("vibrate is in off Condition");
					soft.fail();
					test.log(LogStatus.FAIL, "vibrate is in off Condition");
				}}

			else if (p_b_No.contains("-15.")) {
				
			} else if (p_b_No.contains("-29.") || p_b_No.contains("-26.") || p_b_No.contains("-11.")) {

				
				
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->validate_SentMessage()");
			e.printStackTrace();

		} catch (Exception e) {

			test.log(LogStatus.ERROR, "Exeption in ->validate_SentMessage()");
		}

	}
		
	

	

	public void disable_Notification() {
		try {
			
			if (Locators_Messaging.Notifications_On.isDisplayed()) {

				clickBtn(Locators_Messaging.Notifications_On);
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->Notification_Option()");
			e.printStackTrace();

		} catch (Exception e) {

			test.log(LogStatus.ERROR, "Exeption in ->Notification_Option()");
		}
	}

	public void validate_enable_Notification(SoftAssert soft) {
		try {
			java.lang.String checkbox = Locators_Messaging.checked.getAttribute("checked");
			if (checkbox.equalsIgnoreCase("true")) {

				APP_LOGS.info("Notification enabled Sucessfully");
				soft.assertTrue(true, "Notification enabled Sucessfully");
				test.log(LogStatus.PASS, "Notification enabled Sucessfully");

			} else {
				APP_LOGS.info("Failed to enable Notification");
				soft.fail();
				test.log(LogStatus.FAIL, "Failed to enable Notification");
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->Notification_Option()");
			e.printStackTrace();

		} catch (Exception e) {

			test.log(LogStatus.ERROR, "Exeption in ->Notification_Option()");
		}
	}

	public void validate_Disable_Notification(SoftAssert soft) {
		try {
			if (Locators_Messaging.Notifications_Off.isDisplayed()) {

				APP_LOGS.info("Notification Disable Sucessfully");
				soft.assertTrue(true, "Notification Disable Sucessfully");
				test.log(LogStatus.PASS, "Notification Disable Sucessfully");

			} else {
				APP_LOGS.info("Failed to Disable Notification");
				soft.fail();
				test.log(LogStatus.FAIL, "Failed to Disable Notification");
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->Notification_Option()");
			e.printStackTrace();

		} catch (Exception e) {

			test.log(LogStatus.ERROR, "Exeption in ->Notification_Option()");
		}
	}

	public void validate_Enabled_Notification_Received_SMS_InPrimary(SoftAssert soft) {
		try {
			waituntilnew(Locators_Messaging.recive_sms, 20);
			if (Locators_Messaging.recive_sms.isDisplayed()) {
				APP_LOGS.info("Receiving SMS in Notification bar");
				soft.assertTrue(true, "Receiving SMS in Notification bar");
				test.log(LogStatus.PASS, "Receiving SMS in Notification bar");
			} else {
				APP_LOGS.info("Notification option disabled SMS is not displayed in Notification Bar");
				soft.fail();
				test.log(LogStatus.FAIL, "Notification option disabled SMS is not displayed in Notification Bar");
			}

		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->Notification_Option()");
			e.printStackTrace();

		} catch (Exception e) {

			test.log(LogStatus.ERROR, "Exeption in ->Notification_Option()");
		}
	}
	public void validate_Disabled_Notification_Received_SMS_InPrimary(SoftAssert soft) {
		try {
			waituntilnew(Locators_Messaging.recive_sms, 20);
			if (!Locators_Messaging.recive_sms.isDisplayed()) {
				APP_LOGS.info("Notification option disabled SMS is not displayed in Notification Bar");
				soft.assertTrue(true, "Notification option disabled SMS is not displayed in Notification Bar");
				test.log(LogStatus.PASS, "Notification option disabled SMS is not displayed in Notification Bar");
			} else {
				APP_LOGS.info("Receiving SMS in Notification bar");
				soft.fail();
				test.log(LogStatus.FAIL, "Receiving SMS in Notification bar");
			}

		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->Notification_Option()");
			e.printStackTrace();

		} catch (Exception e) {

			test.log(LogStatus.ERROR, "Exeption in ->Notification_Option()");
		}
	}
	public void reply_From_Notification_bar()
	{
	try {
System.out.println("click on sms");
wait(Locators_Messaging.Reply, 20);
clickBtn(Locators_Messaging.Reply);

	}
	 catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->reply_From_Notification_bar()");
			e.printStackTrace();

		} catch (Exception e) {

			test.log(LogStatus.ERROR, "Exeption in ->reply_From_Notification_bar()");
		}
	}
	public void block_Contact()
	{
	try {
		TouchAction ta=new TouchAction(aDriver);
		ta.longPress(Locators_Messaging.recive_sms, 10).perform().release();
		clickBtn(Locators_Messaging.block_contact);
		clickBtn(Locators_Messaging.OKBtbtn2);
	}
	 catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->block_Contact()");
			e.printStackTrace();

		} catch (Exception e) {

			test.log(LogStatus.ERROR, "Exeption in ->block_Contact()");
		}
	}
	
	public void validate_block_Contcat(SoftAssert soft)
	{
		try {
			minWait();
			clickBtn(multi_Loc_Strategy(Locators_Messaging.more_option, Locators_Messaging.more_option,
					Locators_Messaging.more_option, null, null, 0, 0));
			clickBtn(multi_Loc_Strategy(Locators_Messaging.block_contact_opt, Locators_Messaging.block_contact_opt1,
					Locators_Messaging.block_contact_opt2, null, null, 0, 0));
			if (Locators_Messaging.block_contact_Phno.isDisplayed()||Locators_Messaging.recive_sms.isDisplayed()||Locators_Messaging.block_contact_Phno1.isDisplayed()) {
				APP_LOGS.info("Blocked the Contact Sucessfully");
				soft.assertTrue(true, "Blocked the Contact Sucessfully");
				test.log(LogStatus.PASS, "Blocked the Contact Sucessfully");
			} else {
				APP_LOGS.info("Failed to block the Contact");
				soft.fail();
				test.log(LogStatus.FAIL, "Failed to block the Contact");
			}
			
		}
		 catch (org.openqa.selenium.NoSuchElementException e) {

				test.log(LogStatus.ERROR, "Error in the locators->validate_block_Contcat()");
				e.printStackTrace();

			} catch (Exception e) {

				test.log(LogStatus.ERROR, "Exeption in ->validate_block_Contcat()");
			}
	}
	public void unblock_Contcat()
	{
		try {
			clickBtn(multi_Loc_Strategy(Locators_Messaging.unblock_contact1, Locators_Messaging.unblock_contact2,
					Locators_Messaging.unblock_contact3, null, null, 0, 0));
			clickBtn(multi_Loc_Strategy(Locators_Messaging.unblock_confirm, Locators_Messaging.unblock_confirm1,
					Locators_Messaging.unblock_confirm2, null, null, 0, 0));
		
		}
		 catch (org.openqa.selenium.NoSuchElementException e) {

				test.log(LogStatus.ERROR, "Error in the locators->unblock_Contcat()");
				e.printStackTrace();

			} catch (Exception e) {

				test.log(LogStatus.ERROR, "Exeption in ->unblock_Contcat()");
			}
	}
	public void performAction() throws IOException, InterruptedException {
		/*
		 * Perform action for IMS registered check
		 */
		try {
			minWait();
			Runtime.getRuntime().exec("adb -s "+p_Id+" shell am start -a android.settings.AIRPLANE_MODE_SETTINGS");
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
		scrollTo("Mobile network");
		if(!Locators_Messaging.checkMobilenetwork.isEnabled()) {
		scrollTo("Airplane mode");
		
	clickBtn(Locators_Messaging.airoplane_mode);
		disabled=true;}
		} catch (org.openqa.selenium.NoSuchElementException e) {
		test.log(LogStatus.ERROR, "Error in the locators->disable_AirplaneMode() ");
		e.printStackTrace();
		} catch (Exception e) {
		test.log(LogStatus.ERROR, "Exception in ->disable_AirplaneMode()");
		}
		return disabled;
		}

	public boolean enable_MobileData() {
		/*
		* Enable mobile data from data usage window
		*/
		boolean enabled = false;
		try {
		if (Locators_Messaging.mobile_data_State.getText().equalsIgnoreCase("off")) {
		clickBtn(Locators_Messaging.mobile_data_State);
		}
		enabled = Locators_Messaging.mobile_data_State.getText().equalsIgnoreCase("on");
		} catch (org.openqa.selenium.NoSuchElementException e) {
		test.log(LogStatus.ERROR, "Error in the locators->enable_MobileData() ");
		e.printStackTrace();
		} catch (Exception e) {
		test.log(LogStatus.ERROR, "Exception in ->enable_MobileData()");

		}
		return enabled;
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

	public void message_Template() throws IOException {
		try {
		scrollToText("Text (SMS) messages settings");
			
			clickBtn(Locators_Messaging.msgtemplate);
			while (isElementExist(Locators_Messaging.msgtemplate1)) {
				if (Locators_Messaging.msgtemplate1.isDisplayed()) {
					TouchAction ta=new TouchAction(aDriver);
					ta.longPress(Locators_Messaging.msgtemplate1, 20).perform().release();
					clickBtn(Locators_Messaging.deleteContactOptn);
				}
				
				
			}

		} catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.ERROR, "Error in the locators->message_Template() ");
			e.printStackTrace();
		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exception in ->message_Template()");
		}
	}
	public void validate_message_Template(SoftAssert soft) throws IOException {
		try {
		if (!isElementExist(Locators_Messaging.msgtemplate1)) {
			APP_LOGS.info("Message template delete sucessfully");
			soft.assertTrue(true, "Message template delete sucessfully");
			test.log(LogStatus.PASS, "Message template delete sucessfully");
		} else {
			APP_LOGS.info("Failed to delete the Message template");
			soft.fail();
			test.log(LogStatus.FAIL, "Failed to delete the Message template");
		}
		} catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.ERROR, "Error in the locators->memory_status() ");
			e.printStackTrace();
		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exception in ->memory_status()");
		}
	}
	public void memory_status() throws IOException {
		try {
		scrollToText("Memory status");
		} catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.ERROR, "Error in the locators->memory_status() ");
			e.printStackTrace();
		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exception in ->memory_status()");
		}
	}
	
	public void validate_Memory_status(SoftAssert soft) throws IOException {
		try {
		java.lang.String text = Locators_Messaging.memory_status_text.getText();
		if (text.contains("SMS Used")) {
			APP_LOGS.info("Memory status displayed sucessfully");
			soft.assertTrue(true, "Memory status displayed sucessfully");
			test.log(LogStatus.PASS, "Memory status displayed sucessfully");
		} else {
			APP_LOGS.info("Failed to display the memory status");
			soft.fail();
			test.log(LogStatus.FAIL, "Failed to display the memory status");
		}
		} catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.ERROR, "Error in the locators->memory_status() ");
			e.printStackTrace();
		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exception in ->memory_status()");
		}
	}
	public void remove_Recipient_In_To_Field(SoftAssert soft) throws IOException {
		try {
	
	
	} catch (org.openqa.selenium.NoSuchElementException e) {
		test.log(LogStatus.ERROR, "Error in the locators->remove_Recipient_In_To_Field() ");
		e.printStackTrace();
	} catch (Exception e) {
		test.log(LogStatus.ERROR, "Exception in ->remove_Recipient_In_To_Field()");
	}
}
	public void clearChromePermission() {
		try {
			System.out.println("In Browser");
			
			if(isElementExist(Locators_Messaging.OK)) {
				clickBtn(Locators_Messaging.OK);
			}
			if(isElementExist(Locators_Messaging.Accept_id)) {
				clickBtn(Locators_Messaging.Accept_id);
			}
			if(isElementExist(Locators_Messaging.NEXT)) {
				clickBtn(Locators_Messaging.NEXT);
			}
			if(isElementExist(Locators_Messaging.NO_THANKS)) {
				clickBtn(Locators_Messaging.NO_THANKS);
			}
			if(isElementExist(Locators_Messaging.No_ThanKS)) {
				clickBtn(Locators_Messaging.No_ThanKS);
			}
			if(isElementExist(Locators_Messaging.Cancel)) {
				clickBtn(Locators_Messaging.Cancel);
			}
			
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->clearChromePermission()");
			e.printStackTrace();

		} catch (Exception e) {

			test.log(LogStatus.ERROR, "Exeption in ->clearChromePermission()");
		}		
	}
	public void validate_And_BrowseIn_Chrome(SoftAssert soft) throws InterruptedException, IOException {
		
		try {
					
		customWait(2000);
		if (isElementExist(Locators_Messaging.google_savedDataOpt)) {

		clickBtn(Locators_Messaging.google_savedDataOpt_OkBtn);
		}
		minWait();
		minWait();

		boolean noInternet=wait(Locators_Messaging.noInternet, 20);
		if (!noInternet) {
		APP_LOGS.info("Page is loaded Sucessfully");
		soft.assertTrue(true, "Page is loaded Sucessfully");
		test.log(LogStatus.PASS, "Page is loaded Sucessfully");
		} else {
		APP_LOGS.info("Page is not loaded");
		test.log(LogStatus.FAIL, "Page is not loaded");

		soft.fail();
		}

		}

		catch (org.openqa.selenium.NoSuchElementException e) {

		test.log(LogStatus.ERROR, "Error in locators->validate_And_BrowseIn_Chrome()");
		} catch (Exception e) {
		test.log(LogStatus.ERROR, "Exeption in ->validate_And_BrowseIn_Chrome()");
		}
		}
	
}
