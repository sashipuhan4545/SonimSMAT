package com.xp8.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebElement;
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

public class XP8_Call_Settings_Util extends BaseUtil{

	private static final String String = null;
	public static ExtentReports extent;
	public static ExtentTest test;

	public Process p;
	public boolean check = false;
	public boolean check1 = false;


	boolean value = false;
	public  String p_Id	 = "";								// Primary Device Serial Number.
	public String r_Id	 = "";								// Reference Device Serial Number.
	public String p_b_No = "";			   					// Primary Device Build Number.
	public String r_b_No = ""; 								// Reference Device Build Number.
	public String pryNum =AllQA.PRIMARYDEVMDN;	            // Reference Device MDN. 
	public String refNum = AllQA.REFERENCEDEVMDN;	        // Reference Device MDN.


	public void fetch_Devices_Details() throws InterruptedException, FileNotFoundException, IOException, ParseException {

		minWait();
		p_Id=JsonFileReaderAndWriter.primaryDevIdReader();
		r_Id=JsonFileReaderAndWriter.ReadRefDeviceId();
		p_b_No=JsonFileReaderAndWriter.primaryDevFirmwareReader();
		r_b_No=JsonFileReaderAndWriter.ReadRefDeviceFirmWare();
	}
	public void sleeptime_30min() {
		try{
			Runtime.getRuntime().exec("adb -s " + p_Id + " shell settings put system screen_off_timeout 1800000");
			minWait();

		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR,"Error in locators->sleeptime_30min()");
		}catch (Exception e) {
			test.log(LogStatus.ERROR,"Exeption in ->sleeptime_30min()");
		}
	}
	public void sleeptime_30sec() {
		try{
			Runtime.getRuntime().exec("adb -s " + p_Id + " shell settings put system screen_off_timeout 180000");
			minWait();

		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR,"Error in locators->sleeptime_30sec()");
		}catch (Exception e) {
			test.log(LogStatus.ERROR,"Exeption in ->sleeptime_30sec()");
		}
	}
	public void lockscreen() {
		try{
			System.out.println("To unlock the screen");
			launch_an_app("settings");
			scrollToText("Security & location");

			if(isElementExist(Locators_CallSetting.locknone)){
				System.out.println("Inside IF");
				aDriver.pressKeyCode(AndroidKeyCode.HOME);
			}
			else if(isElementExist(Locators_CallSetting.lockswipe)){
				System.out.println("Inside ELSE IF");
				clickBtn(Locators_CallSetting.lockscreenlock);
				customWait(2000);
				clickBtn(Locators_CallSetting.locknone2);
				clickBackButton_without_try_catch(2);
			}else{
				System.out.println("Phone is locked");

			}

		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR,"Error in locators->lockscreen()");
		}catch (Exception e) {
			test.log(LogStatus.ERROR,"Exeption in ->lockscreen()");
		}
	}
	public void checkairplanemode(SoftAssert sa){

		try {
			customWait(2000);
			Runtime.getRuntime().exec("adb -s " + p_Id + " shell am start -a android.settings.AIRPLANE_MODE_SETTINGS");
			minWait();
			for (int i = 1; i <= 9; i++) {
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
			}if(isElementExist(Locators_CallSetting.airplanemodeCheckboxoff)){
				System.out.println("airplane mode off");
				aDriver.pressKeyCode(AndroidKeyCode.HOME);
				APP_LOGS.info("Airplane mode is disabled");
				sa.assertTrue(true, "Airplane mode is disabled");
				test.log(LogStatus.PASS,"Airplane mode is disabled");
			}
			else{
				clickBtn(Locators_CallSetting.airplanemodeCheckboxon);
				System.out.println("airplane mode on");
				APP_LOGS.info("Airplane mode is enabled and now it is disabled");
				sa.assertTrue(true, "Airplane mode is enabled and now it is disabled");
				test.log(LogStatus.PASS,"Airplane mode is disabled");
			}

		} catch (org.openqa.selenium.NoSuchElementException e) {
			APP_LOGS.info("Airplane mode is disabled");
			sa.assertTrue(true, "Airplane mode is disabled");
			test.log(LogStatus.ERROR,"Error in locators->checkairplanemode()");
		}catch (Exception e) {
			APP_LOGS.info("Airplane mode is disabled");
			sa.assertTrue(true, "Airplane mode is disabled");
			test.log(LogStatus.ERROR,"Exeption in ->checkairplanemode()");
		}
	}

	public void navigate_to_call_settings(){

		try {

			if(isElementExist(Locators_CallSetting.settingsIcon)){
				clickBtn(Locators_CallSetting.settingsIcon);
				minWait();
				clickBtn(Locators_CallSetting.settingsOpt);
			}else{
				clickBtn(Locators_CallSetting.vzwsettingsIcon);
				minWait();
				clickBtn(Locators_CallSetting.settingsOpt);
			}

		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->navigate_to_call_settings()");
			e.printStackTrace();

		}catch (Exception e) {

			test.log(LogStatus.ERROR, "Exeption in ->navigate_to_call_settings()");
		}
	}

	public void navigate_to_call_settingswithout_try_catch() throws InterruptedException {
		if(isElementExist(Locators_CallSetting.settingsIcon)){
			clickBtn(Locators_CallSetting.settingsIcon);
			minWait();
			clickBtn(Locators_CallSetting.settingsOpt);
		}else{
			clickBtn(Locators_CallSetting.vzwsettingsIcon);
			minWait();
			clickBtn(Locators_CallSetting.settingsOpt);
		}
	}


	public void validate_navigatetocallsettings(SoftAssert sa)
	{
		try{

			launch_an_app("phone");
			minWait();
			navigate_to_call_settings();
			minWait();
			if(isElementExist(Locators_CallSetting.callsettingsframe)){
				APP_LOGS.info("Call Setting is navigated successfully");
				sa.assertTrue(true, "Call Setting is navigated successfully");
				test.log(LogStatus.PASS, "Call Setting is navigated successfully");	


			}else {


				APP_LOGS.info("Call setting navigation fail");
				sa.fail();
				test.log(LogStatus.FAIL, "Call setting navigation fail");

			}

		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR,"Error in locators->validate_navigatetocallsettings()");
		}catch (Exception e) {
			test.log(LogStatus.ERROR,"Exeption in ->validate_navigatetocallsettings()");
		}
	}




	public void navigate_To_Settings_AndElement(AndroidElement element) throws InterruptedException
	{
		/*
		 * Navigates to settings and clicks on given element
		 */
		try {
			if(isElementExist(Locators_CallSetting.settingsIcon)){
				clickBtn(Locators_CallSetting.settingsIcon);
				minWait();
				clickBtn(Locators_CallSetting.settingsOpt);
			}else{
				clickBtn(Locators_CallSetting.vzwsettingsIcon);
				minWait();
				clickBtn(Locators_CallSetting.settingsOpt);
			}
			element.click();
			minWait();
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->navigate_To_Settings_AndElement()");
			e.printStackTrace();

		}catch (Exception e) {
			test.log(LogStatus.ERROR, "Exeption in ->navigate_To_Settings_AndElement()");

		}
	}
	public void navigate_To_Settings_AndElement_without_try_catch(AndroidElement element) throws InterruptedException
	{
		/*
		 * Navigates to settings and clicks on given element
		 */
		if(isElementExist(Locators_CallSetting.settingsIcon)){
			clickBtn(Locators_CallSetting.settingsIcon);
			minWait();
			clickBtn(Locators_CallSetting.settingsOpt);
		}else{
			clickBtn(Locators_CallSetting.vzwsettingsIcon);
			minWait();
			clickBtn(Locators_CallSetting.settingsOpt);
		}
		element.click();
		minWait();

	}
	public String selectDisplaySubOption(AndroidElement element,AndroidElement subElement) throws InterruptedException
	{
		/*
		 * Navigates to display options and clicks on given sub element
		 */
		//	navigate_To_Settings_AndElement(Locators_CallSetting.displayOptions);
		minWait();
		element.click();
		minWait();
		String field = subElement.getText();
		subElement.click();
		minWait();
		return field;
	}
	public void validateSortByAndNameFormat(AndroidElement element,AndroidElement subElement ,AndroidElement contactName,String Contact,SoftAssert sa) throws InterruptedException {
		/*
		 * Common method to validate sortBy and name format option 
		 */
		//navigate_To_Settings_AndElement(Locators_CallSetting.displayOptions);
		String field = selectDisplaySubOption(element, subElement);
		minWait();
		clickBackButton_without_try_catch(2);
		if(isElementExist(Locators_CallSetting.contactPage)){
			clickBtn(Locators_CallSetting.contactPage);
			minWait();
		}
		else{
			clickBtn(Locators_CallSetting.vzwcontactpage);
			minWait();
		}
		String actualText = contactName.getText();
		System.out.println(actualText);
		minWait();
		if(actualText.equals(Contact)){
			APP_LOGS.info( field +" feature is validated successfully");
			sa.assertTrue(true, "Sort by feature is validated successfully");
			test.log(LogStatus.PASS, field +" feature is validated successfully");

		}else{
			APP_LOGS.info(field +" feature is not validated");
			sa.fail();
			test.log(LogStatus.FAIL, field +" feature is not validated");

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
	public void clickBackButton_without_try_catch(int number) throws InterruptedException
	{
		/*
		 * clicks on back button with iteration as user input
		 */

		for(int i=0;i<number;i++){
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.BACK);
		}
	}
	public void validateSettingsAndDisplayOptions(SoftAssert sa) throws InterruptedException, IOException
	{
		/*
		 * validates settings and display option
		 */
		try {

			launch_an_app("phone");
			minWait();
			navigate_to_call_settingswithout_try_catch();
			minWait();
			clickBtn(Locators_CallSetting.displayOptions);
			minWait();
			validateSortByAndNameFormat(Locators_CallSetting.sortByOpt,Locators_CallSetting.lastNameOpt,Locators_CallSetting.contact2, "Cde Test",sa);
			minWait();
			navigate_To_Settings_AndElement_without_try_catch(Locators_CallSetting.displayOptions);
			validateSortByAndNameFormat(Locators_CallSetting.sortByOpt,Locators_CallSetting.firstNameOpt,Locators_CallSetting.contact1, "Abc Automation",sa);
			minWait();
			navigate_To_Settings_AndElement_without_try_catch(Locators_CallSetting.displayOptions);
			validateSortByAndNameFormat(Locators_CallSetting.nameFormatOpt, Locators_CallSetting.lastNameFirstOpt,Locators_CallSetting.lnfcontact2, "Test, Cde",sa);
			minWait();
			navigate_To_Settings_AndElement_without_try_catch(Locators_CallSetting.displayOptions);
			validateSortByAndNameFormat(Locators_CallSetting.nameFormatOpt, Locators_CallSetting.firstNameFirstOpt,Locators_CallSetting.contact2, "Cde Test",sa);
			minWait();
			APP_LOGS.info("Settings and display option is validated");
			sa.assertTrue(true, "Settings and display option is validated");
			test.log(LogStatus.PASS, "Settings and display option is validated");

		}catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators-> validateSettingsAndDisplayOptions()");
			e.printStackTrace();

		} 

		catch (Exception e) {
			sa.fail();
			test.log(LogStatus.ERROR, "Exception in validateSettingsAndDisplayOptions()");
		}
	}

	public void deleteIfContactsPresent(SoftAssert sa) throws InterruptedException
	{

		try {

			if(isElementExist(Locators_CallSetting.deletefirstcontact))
			{
				clickBtn(Locators_CallSetting.delete_option);
				minWait();
				//  if(isElementExist(Locators_CallSetting.selectAllOpt)){

				clickBtn(Locators_CallSetting.zero_selected);
				minWait();
				clickBtn(Locators_CallSetting.selectAllOpt);
				minWait();
				clickBtn(Locators_CallSetting.Ok_option);
				minWait();
				clickBtn(Locators_CallSetting.okBtn);
				customWait(5000);
				APP_LOGS.info("Contacts are deleted successfully");
				sa.assertTrue(true, "Contacts are deleted successfully");
				test.log(LogStatus.PASS, "Contacts are deleted successfully");	


			}else{
				clickBackButton_without_try_catch(4);
			}

		}  catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->deleteIfContactsPresent()");
			e.printStackTrace();

		}
		catch (Exception e) {
			test.log(LogStatus.ERROR, "Exeption in ->deleteIfContactsPresent()");
		}

	}


	public void deleteConatactList() throws InterruptedException{
		//deleting added contacts if present in Black  list

		try {
			launch_an_app("phone");
			minWait();
			navigateToSettingsAndElement(Locators_CallSetting.Callscreening);
			minWait();
			clickBtn(Locators_CallSetting.Manageblacklist);
			minWait();
			for(int i=1; i<=20; i++){
				if(isElementExist(Locators_CallSetting.blackcontactList)){
					clickBtn(Locators_CallSetting.deleteblockBtninContact);
					minWait();
					clickBtn(Locators_CallSetting.unblocktxt);
					minWait();
				} else {
					clickBackButton(4);
				}
			}
		}catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->deleteConatactList() ");
			e.printStackTrace();

		}catch (Exception e) {
			test.log(LogStatus.ERROR, "Exeption in ->deleteConatactList() ");

		}

	}
	public void createContactWithNameandPhone(String name,String lastname,String phone) throws InterruptedException
	{
		/*
		 * Enter name and phone number and save the contact
		 */
		//	try {
		customWait(2000);
		enterTextToInputField(Locators_CallSetting.nameEditFld, name);
		minWait();
		clickBackButton(1);
		enterTextToInputField(Locators_CallSetting.lastnameEditFld, lastname);
		minWait();
		clickBackButton(1);
		enterTextToInputField(Locators_CallSetting.phoneNumberEditFld, phone);
		minWait();
		clickBtn(Locators_CallSetting.saveOpt);
		minWait();
		//	} catch (Exception e) {
		//	e.printStackTrace();
		//	test.log(LogStatus.ERROR, "Create contact with name and phone is failed");
		//}
	}




	public void validateSavedContact(String name,String location,SoftAssert sa) throws InterruptedException
	{
		/*
		 * validates saved contact
		 */

		try {
			customWait(2000);
			String savedContact = Locators_CallSetting.savedContact.getText();
			if(savedContact.contains(name)){
				clickBackButton_without_try_catch(1);
				APP_LOGS.info("Contact saved "+ location +"successfully");
				sa.assertTrue(true, "Saved contacts are validated");
				test.log(LogStatus.PASS, "Contact saved  "+ location +" successfully");


			} else	{
				APP_LOGS.info("Contact not saved" + location);
				sa.fail();
				test.log(LogStatus.FAIL, "Contact not saved");

			}		
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->validateSavedContact()");
			e.printStackTrace();

		}
		catch (Exception e) {
			test.log(LogStatus.ERROR, "Exception in validateSavedContact()");

		}

	}


	public void validateSavedContact2(String Thirdname,String location,SoftAssert sa) throws InterruptedException
	{
		/*
		 * validates saved contact
		 */

		try {
			customWait(2000);
			String savedContact = Locators_CallSetting.savedContact.getText();
			if(savedContact.contains(Thirdname)){
				clickBackButton_without_try_catch(1);
				APP_LOGS.info("Contact saved "+ location +"successfully");
				sa.assertTrue(true, "Saved contact2 are validated");
				test.log(LogStatus.PASS, "Contact saved  "+ location +" successfully");


			} else	{
				APP_LOGS.info("Contact not saved" + location);
				sa.fail();
				test.log(LogStatus.FAIL, "Contact not saved");

			}		
		}catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->validateSavedContact2()");
			e.printStackTrace();

		} 
		catch (Exception e) {
			test.log(LogStatus.ERROR, "Exception in validateSavedContact2()");

		}

	}

	public void AddContactFromContactsPage(String name,String lastname,String phone) throws InterruptedException, IOException{
		/*
		 *  Add contact from contacts page 
		 */
		try
		{
			if(isElementExist(Locators_CallSetting.contactPage)){
				clickBtn(Locators_CallSetting.contactPage);
				minWait();
				clickBtn(Locators_CallSetting.createNewContactInContactPage);
				createContactWithNameandPhone(name,lastname, phone);
				minWait();
			}
			else{
				clickBtn(Locators_CallSetting.vzwcontactpage);
				minWait();
				clickBtn(Locators_CallSetting.createNewContactInContactPage);
				createContactWithNameandPhone(name,lastname, phone);
				minWait();
			}
		}catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->AddContactFromContactsPage()");
			e.printStackTrace();

		}
		catch (Exception e) {
			test.log(LogStatus.ERROR, "Exception in AddContactFromContactsPage()");
		}
	} 

	public void add_Contact(String Newname,String Newlastname,String Newphone) throws InterruptedException, IOException{
		/*
		 * Validate Add contact from contacts page and validate added contact 
		 */
		try
		{
			if(isElementExist(Locators_CallSetting.contactPage)){
				clickBtn(Locators_CallSetting.contactPage);
				minWait();
				clickBtn(Locators_CallSetting.createSecondcontactInContactPage);
				createContactWithNameandPhone(Newname,Newlastname, Newphone);
				minWait();

			}
			else{
				clickBtn(Locators_CallSetting.vzwcontactpage);
				clickBtn(Locators_CallSetting.createSecondcontactInContactPage);
				createContactWithNameandPhone(Newname,Newlastname, Newphone);
				minWait();
			}

		}
		catch (org.openqa.selenium.NoSuchElementException e) {


			test.log(LogStatus.ERROR, "Error in the locators->add_Contact()");
			e.printStackTrace();

		}
		catch (Exception e) {
			test.log(LogStatus.ERROR, "Exception in add_Contact()");
		}
	} 

	public void navigateToSettingsAndElement(WebElement element) throws InterruptedException
	{
		/*
		 * Navigates to settings and clicks on given element
		 */
		try {
			if(isElementExist(Locators_CallSetting.settingsIcon)){
				clickBtn(Locators_CallSetting.settingsIcon);
				minWait();
				clickBtn(Locators_CallSetting.settingsOpt);
			}else{
				clickBtn(Locators_CallSetting.vzwsettingsIcon);
				minWait();
				clickBtn(Locators_CallSetting.settingsOpt);
			}
			element.click();
			minWait();
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->navigateToSettingsAndElement()");
			e.printStackTrace();

		}catch (Exception e) {
			test.log(LogStatus.ERROR, "Exception error in navigateToSettingsAndElement");
		}
	}
	public void navigateToSettingsAndElement_without_try_catch(AndroidElement element) throws InterruptedException
	{
		/*
		 * Navigates to settings and clicks on given element
		 */

		if(isElementExist(Locators_CallSetting.settingsIcon)){
			clickBtn(Locators_CallSetting.settingsIcon);
			minWait();
			clickBtn(Locators_CallSetting.settingsOpt);
		}else{
			clickBtn(Locators_CallSetting.vzwsettingsIcon);
			minWait();
			clickBtn(Locators_CallSetting.settingsOpt);
		}
		element.click();
		minWait();
	}

	public void validatePhoneRingtone(SoftAssert sa) throws InterruptedException {
		/*
		 * Sets phone ringtone and validates selected phone ringtone
		 */

		try {
			minWait();
			clickBtn(Locators_CallSetting.phoneRingtoneOpt);
			minWait();
			clickBtn(Locators_CallSetting.ringtoneOpt);
			minWait();
			String expectedRingtone = Locators_CallSetting.ringtoneOpt.getText();
			minWait();
			clickBtn(Locators_CallSetting.okBtn);
			customWait(2000);
			String selectedRingtone = Locators_CallSetting.ringtoneSelected.getText();
			minWait();
			if(selectedRingtone.equals(expectedRingtone)){
				check = true;
				APP_LOGS.info("Phone Ringtone is validated successfully");
				sa.assertTrue(true, "Phone Ringtone is validated successfully");
				test.log(LogStatus.PASS, "Phone Ringtone is validated successfully");

			}else{
				APP_LOGS.info("Phone Ringtone is not validated");
				sa.fail();
				test.log(LogStatus.FAIL, "Phone Ringtone is not validated");

			}
		}catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->validatePhoneRingtone()");
			e.printStackTrace();

		}catch (Exception e) {
			test.log(LogStatus.INFO, "Exception in validatePhoneRingtone() ");

		}

	}
	public void validatePhoneRingtone_vzw(SoftAssert sa) throws InterruptedException {
		/*
		 * Sets phone ringtone and validates selected phone ringtone
		 */

		try {
			minWait();
			/*clickBtn(Locators_CallSetting.phoneRingtoneOpt);
			minWait();*/
			scrollToText("Phone ringtone");
			clickBtn(Locators_CallSetting.ringtoneOpt);
			clickBtn(Locators_CallSetting.okBtn);
		    clickBtn(Locators_CallSetting.vzw_advanced);
			scrollToText("Default notification sound");
			clickBtn(Locators_CallSetting.ringtoneOpt);
			clickBtn(Locators_CallSetting.okBtn);
			scrollToText("Default alarm sound");
			clickBtn(Locators_CallSetting.ringtoneOpt);
			clickBtn(Locators_CallSetting.okBtn);
			minWait();
			if(isElementExist(Locators_CallSetting.ringtoneSelected)){
				APP_LOGS.info("Phone Ringtone is validated successfully");
				sa.assertTrue(true, "Phone Ringtone is validated successfully");
				test.log(LogStatus.PASS, "Phone Ringtone is validated successfully");

			}else{
				APP_LOGS.info("Phone Ringtone is not validated");
				sa.fail();
				test.log(LogStatus.FAIL, "Phone Ringtone is not validated");

			}
			
          }catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->validatePhoneRingtone()");
			e.printStackTrace();

		}catch (Exception e) {
			test.log(LogStatus.INFO, "Exception in validatePhoneRingtone() ");

		}

	}
	public void validateSoundsAndVibrationSubOptions(SoftAssert sa) throws InterruptedException {
		/*
		 * validates sounds and vibration sub-options checkbox status 
		 */
		try {
			minWait();
			checkOtherSoundOptionsCheckboxStatus(Locators_CallSetting.alsoVibrateForCallsOpt, Locators_CallSetting.alsoVibrateCheckbox,sa);
			customWait(2000);
			checkOtherSoundOptionsCheckboxStatus(Locators_CallSetting.dialpadTonesOpt, Locators_CallSetting.dialpadToneCheckbox,sa);
			customWait(2000);
			checkOtherSoundOptionsCheckboxStatus(Locators_CallSetting.callEndToneOpt, Locators_CallSetting.callEndToneCheckbox,sa);
			minWait();
			APP_LOGS.info("Sounds and vibration are validated");
			sa.assertTrue(true, "Sounds and vibration are validated");
			test.log(LogStatus.PASS, "Sounds and vibration are validated");	
		}catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->validateSoundsAndVibrationSubOptions()");
			e.printStackTrace();

		}catch (Exception e) {
			test.log(LogStatus.ERROR, "Exception error in validateSoundsAndVibrationSubOptions()");
		}
	}  
	public void validateSoundsAndVibrationSubOptions_vzw(SoftAssert sa) throws InterruptedException {
		/*
		 * validates sounds and vibration sub-options checkbox status 
		 */
		try {
			//clickBtn(Locators_CallSetting.vzw_advanced);
			scrollToText("Other sounds and vibrations");
			minWait();
			checkOtherSoundOptionsCheckboxStatus(Locators_CallSetting.keypadsounds, Locators_CallSetting.keypadsoundsCheckbox,sa);
			customWait(2000);
			checkOtherSoundOptionsCheckboxStatus(Locators_CallSetting.dialpadtones, Locators_CallSetting.dialpadtonesCheckbox,sa);
			customWait(2000);
			checkOtherSoundOptionsCheckboxStatus(Locators_CallSetting.screenlockingsounds, Locators_CallSetting.screenlockingsoundsCheckbox,sa);
			minWait();
			checkOtherSoundOptionsCheckboxStatus(Locators_CallSetting.touchsounds, Locators_CallSetting.touchsoundsCheckbox,sa);
			customWait(2000);
			checkOtherSoundOptionsCheckboxStatus(Locators_CallSetting.vibrateontap, Locators_CallSetting.vibrateontapCheckbox,sa);
			customWait(2000);
			checkOtherSoundOptionsCheckboxStatus(Locators_CallSetting.poweronsounds, Locators_CallSetting.poweronsoundsCheckbox,sa);
			customWait(2000);
			APP_LOGS.info("Sounds and vibration are validated");
			sa.assertTrue(true, "Sounds and vibration are validated");
			test.log(LogStatus.PASS, "Sounds and vibration are validated");	
		}catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->validateSoundsAndVibrationSubOptions_vzw()");
			e.printStackTrace();

		}catch (Exception e) {
			test.log(LogStatus.ERROR, "Exception error in validateSoundsAndVibrationSubOptions_vzw()");
		}
	}  
	public void  checkOtherSoundOptionsCheckboxStatus(WebElement element,AndroidElement checkbox,SoftAssert sa) throws InterruptedException {
		/*
		 * checks whether given elements checkbox is enabled
		 */
		String soundResourceText = element.getText();
		minWait();
		element.click();
		minWait();
		if(isElementExist(checkbox)){
			element.click();
			check = true;
			APP_LOGS.info(soundResourceText+" is Enabled");
			sa.assertTrue(true, "Sound option checkbox is enabled");
			test.log(LogStatus.PASS, soundResourceText+" is Enabled");

		}else{
			APP_LOGS.info(soundResourceText+" is disabled");
			test.log(LogStatus.INFO, soundResourceText+" is disabled");
			sa.fail();
			test.log(LogStatus.FAIL, "Test cases failed");
		}
	}





	public void Restore_default_QuickResponses() throws InterruptedException
	{	
		/*
		 * Restore default in quick response
		 */
		try {
			minWait();
			clickBtn(Locators_CallSetting.moreSettings);
			minWait();
			clickBtn(Locators_CallSetting.restoreDefaultsInQuickResponse);
			customWait(2000);

		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->Restore_default_QuickResponses()");
			e.printStackTrace();

		}catch (Exception e) {
			test.log(LogStatus.ERROR, "Exception in Restore_default_QuickResponses()");
		}
	}

	public void getAndValidateQuickResponsesList(SoftAssert sa) throws InterruptedException
	{
		/*
		 * validates presence of quick response list messages
		 */
		try {
			minWait();
			getQuickResonseText(Locators_CallSetting.quickResponseTextOne,"Can't talk now. What's up?");
			minWait();
			getQuickResonseText(Locators_CallSetting.quickResponseTextTwo,"I'll call you right back.");
			minWait();
			getQuickResonseText(Locators_CallSetting.quickResponseTextThree,"I'll call you later.");
			minWait();
			getQuickResonseText(Locators_CallSetting.quickResponseTextFour,"Can't talk now. Call me later?");
			APP_LOGS.info("Quick response list is verified");
			sa.assertTrue(true, "Quick response list is verified");
			test.log(LogStatus.PASS, "Quick response list is verified");
		}catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->getAndValidateQuickResponsesList()");
			e.printStackTrace();

		}catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.INFO, "Exception in getAndValidateQuickResponsesList()");
		}
	}

	public void getQuickResonseText(WebElement element,String expectedTextMessage) throws InterruptedException 
	{
		/*
		 * Gets Quick response text message and returns
		 */
		String textMessage = element.getText();
		minWait();
		if(textMessage.contains(expectedTextMessage)){
			check = true;
			APP_LOGS.info("Quick Resonse message : "+textMessage);
			test.log(LogStatus.INFO,"Quick Resonse message : "+textMessage);

		}else{
			APP_LOGS.info("Quick Resonse message : "+textMessage +" is not available");
			test.log(LogStatus.ERROR, "Quick Resonse message : "+textMessage +" is not available");

		}
	}
	public void editAndValidateQuickResponse(String data,SoftAssert sa) throws InterruptedException
	{
		/*
		 *  Edit quick response and validate edited quick response message
		 */

		try{
			clickBtn(Locators_CallSetting.quickResponseText);

			minWait();
			clickBtn(Locators_CallSetting.quickResponseEditField);
			minWait();
			enterTextToInputField(Locators_CallSetting.quickResponseEditField, data);
			minWait();
			clickBtn(Locators_CallSetting.okBtn);
			minWait();
			String actualEditedMessage = Locators_CallSetting.quickResponseText.getText();
			if(actualEditedMessage.contains("Hello Sonim")){
				check = true;
				APP_LOGS.info("Edited and validated Quick Response text");
				sa.assertTrue(true, "Edited and validated Quick Response text");
				test.log(LogStatus.PASS, "Edited and validated Quick Response text");

			}else{
				APP_LOGS.info("Edited Quick Response Text is not available");
				sa.fail();
				test.log(LogStatus.FAIL, "Edited Quick Response Text is not available");

			}
		}catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->editAndValidateQuickResponse()");
			e.printStackTrace();

		}catch (Exception e) {
			test.log(LogStatus.ERROR, "Exception in editAndValidateQuickResponse()");

		}

	}


	public void presenceOfVoicemailInSpeedDailSettings(SoftAssert sa)
	{
		/*
		 * validates Presence of voicemail option
		 */
		try
		{
			if(isElementExist(Locators_CallSetting.voiceMailOpt)){
				System.out.println("Voice mail present");
				APP_LOGS.info("Voicemail Option is present in Speed Dail settings");
				sa.assertTrue(true, "Voicemail Option is present in Speed Dail settings");
				test.log(LogStatus.PASS, "Voicemail Option is present in Speed Dail settings");				
			}else {

				APP_LOGS.info("Voicemail Option is not present in Speed Dail settings");
				test.log(LogStatus.PASS, "Voicemail Option is not present in Speed Dail settings");
				sa.fail();
				test.log(LogStatus.FAIL, "Test cases failed");
			}
		}catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators-> presenceOfVoicemailInSpeedDailSettings()");
			e.printStackTrace();

		}catch (Exception e) {
			test.log(LogStatus.INFO, "Exception in  presenceOfVoicemailInSpeedDailSettings()");
		}

	}
	public void presenceOfCallingAccounts(SoftAssert sa)
	{
		/*
		 * validates Presence of voicemail option
		 */
		try
		{
			if(isElementExist(Locators_CallSetting.callingaccount)){
				System.out.println("Calling Account present");
				APP_LOGS.info("Calling Account Option is present in Speed Dail settings");
				sa.assertTrue(true, "Calling Account Option is present in Speed Dail settings");
				test.log(LogStatus.PASS, "Calling Account Option is present in Speed Dail settings");				
			}else {

				APP_LOGS.info("Calling Account Option is not present in Speed Dail settings");
				test.log(LogStatus.PASS, "Calling Account Option is not present in Speed Dail settings");
				sa.fail();
				test.log(LogStatus.FAIL, "Test cases failed");
			}
		}catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators-> presenceOfCallingAccounts()");
			e.printStackTrace();

		}catch (Exception e) {
			test.log(LogStatus.INFO, "Exception in presenceOfCallingAccounts()");
		}

	}
	public void presenceOfFixeddialingnumbers(SoftAssert sa)
	{
		/*
		 * validates Presence of voicemail option
		 */
		try
		{
			if(isElementExist(Locators_CallSetting.fdn)){
				System.out.println("FDN present");
				APP_LOGS.info("FDN Option is present in Calls");
				sa.assertTrue(true, "FDN Option is present in Calls");
			}else {

				APP_LOGS.info("FDN Option is not present in Calls");
				test.log(LogStatus.PASS, "FDN Option is not present in calls");
				sa.fail();
				test.log(LogStatus.FAIL, "FDN Option is not present in calls");
			}
		}catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->  presenceOfFixeddialingnumbers()");
			e.printStackTrace();

		}catch (Exception e) {
			test.log(LogStatus.INFO, "Exception in  presenceOfFixeddialingnumbers()");
		}

	}
	public void presenceOfCallForwarding(SoftAssert sa)
	{
		/*
		 * validates Presence of voicemail option
		 */
		try
		{
			if(isElementExist(Locators_CallSetting.callforwarding)){
				System.out.println("callforwarding present");
				APP_LOGS.info("callforwarding Option is present in calls");
				sa.assertTrue(true, "callforwarding Option is present in calls");
				test.log(LogStatus.PASS, "callforwarding Option is present in calls");				
			}else {

				APP_LOGS.info("callforwarding Option is not present in calls");
				test.log(LogStatus.PASS, "callforwarding Option is not present in calls");
				sa.fail();
				test.log(LogStatus.FAIL, "callforwarding Option is not present in calls");
			}
		}catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators-> presenceOfCallForwarding()");
			e.printStackTrace();

		}catch (Exception e) {
			test.log(LogStatus.INFO, "Exception in presenceOfCallForwarding()");
		}

	}
	public void presenceOfAdditionalSettings(SoftAssert sa)
	{
		/*
		 * validates Presence of voicemail option
		 */
		try
		{
			if(isElementExist(Locators_CallSetting.additionalsettings)){
				System.out.println("additionalsettings present");
				APP_LOGS.info("Additional Setting Option is present in calls");
				sa.assertTrue(true, "Additional Setting Option is present in calls");
				test.log(LogStatus.PASS, "Additional Setting Option is present in calls");				
			}else {

				APP_LOGS.info("Additional Setting Option is not present in calls");
				test.log(LogStatus.PASS, "Additional Setting Option is not present in calls");
				sa.fail();
				test.log(LogStatus.FAIL, "Additional Setting Option is not present in calls");
			}
		}catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators-> presenceOfCallingAccounts()");
			e.printStackTrace();

		}catch (Exception e) {
			test.log(LogStatus.INFO, "Exception in presenceOfCallingAccounts()");
		}

	}
	public void presenceOfCallbarring(SoftAssert sa)
	{
		/*
		 * validates Presence of voicemail option
		 */
		try
		{
			if(isElementExist(Locators_CallSetting.callbarring)){
				System.out.println("Call barring present");
				APP_LOGS.info("CallBarring Option is present in calls");
				sa.assertTrue(true, "CallBarring Option is present in calls");
				test.log(LogStatus.PASS, "CallBarring Account Option is present in calls");				
			}else {

				APP_LOGS.info("CallBarring Option is not present in calls");
				test.log(LogStatus.PASS, "CallBarring Option is not present in calls");
				sa.fail();
				test.log(LogStatus.FAIL, "CallBarring Option is not present in calls");
			}
		}catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators-> presenceOfCallbarring()");
			e.printStackTrace();

		}catch (Exception e) {
			test.log(LogStatus.INFO, "Exception in presenceOfCallbarring()");
		}

	}

	public void addContactInSpeedDailSettings(String Phone,SoftAssert sa) throws InterruptedException, IOException
	{
		/*
		 * Add contact in speed Dial settings and validate
		 */


		try
		{
			clickBtn(Locators_CallSetting.notSetInSpeedDialSettings);
			minWait();
			enterTextToInputField(Locators_CallSetting.editFldInSpeedDialSettings, Phone);
			minWait();
			clickBtn(Locators_CallSetting.okBtn);
			customWait(3000);
			if(isElementExist(Locators_CallSetting.addedContactInSpeedDialSettings))
			{

				APP_LOGS.info("Contact added in Speed Dail settings");
				sa.assertTrue(true, "Contact added in Speed Dail settings");
				test.log(LogStatus.PASS, "Contact added in Speed Dail settings");


			}else {
				APP_LOGS.info("Contact not added in Speed Dail settings");
				test.log(LogStatus.INFO, "Contact not added in Speed Dail settings");
				sa.fail();
				test.log(LogStatus.FAIL, "");
			}
		}catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->addContactInSpeedDailSettings()");
			e.printStackTrace();

		}catch (Exception e) {
			test.log(LogStatus.ERROR, "Exception in addContactInSpeedDailSettings()");
		}


	}

	public void replaceContactInSpeedDailSettings(String name,SoftAssert sa) throws InterruptedException, IOException
	{
		/*
		 * Replace contact in speed dial settings and validate
		 */


		try
		{
			clickBtn(Locators_CallSetting.addedContactInSpeedDialSettings);
			minWait();
			clickBtn(Locators_CallSetting.replaceOptInSpeedDialSettings);
			minWait();
			clickBtn(Locators_CallSetting.selectContactInSpeedDialSettings);
			minWait();
			clickBtn(Locators_CallSetting.firstNameContact);
			minWait();
			if(isElementExist(Locators_CallSetting.firstNameContact)){

				System.out.println("0000000000000000000000000000");

				APP_LOGS.info("Contact replaced in Speed Dail settings");
				sa.assertTrue(true, "Contact replaced in Speed Dail settings");
				test.log(LogStatus.INFO, "Contact replaced in Speed Dail settings");

				test.log(LogStatus.PASS, "Contact replaced in Speed Dail settings");

			}else {

				System.out.println("FAILLLLLLLLLLLLLLL");


				APP_LOGS.info("Contact not replaced in Speed Dail settings");
				sa.fail();
				test.log(LogStatus.FAIL, "Contact not replaced in Speed Dail settings");


			}
		}catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->replaceContactInSpeedDailSettings()");
			e.printStackTrace();

		}catch (Exception e) {
			test.log(LogStatus.ERROR, "Exception in replaceContactInSpeedDailSettings()");
		}


	}
	public void deleteContactInSpeedDailSettings(SoftAssert sa) throws InterruptedException, IOException
	{
		/*
		 * Delete contact in speed dial settings and validate
		 */


		try
		{
			System.out.println("delete");
			clickBtn(Locators_CallSetting.speeddialsettingfirstcontact);
			minWait();
			clickBtn(Locators_CallSetting.delete);
			minWait();
			if(isElementExist(Locators_CallSetting.notSetInSpeedDialSettings)){
				APP_LOGS.info("Contact deleted in Speed Dail settings");
				sa.assertTrue(true, "Contact deleted in Speed Dail settings");
				test.log(LogStatus.PASS, "Contact deleted in Speed Dail settings");


			}else {
				APP_LOGS.info("Contact not deleted in Speed Dail settings");
				sa.fail();
				test.log(LogStatus.FAIL, "Contact not deleted in Speed Dail settings");



			}
		}catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->deleteContactInSpeedDailSettings()");
			e.printStackTrace();

		}catch (Exception e) {
			test.log(LogStatus.INFO, "Exception in deleteContactInSpeedDailSettings()");
		}


	}
	public void deleteContactInSpeedDailSettings_post(SoftAssert sa) throws InterruptedException, IOException
	{
		/*
		 * Delete contact in speed dial settings and validate
		 */


		try
		{
			System.out.println("delete");
			if(isElementExist(Locators_CallSetting.speeddialsettingfirstcontactpost)){
				
				clickBtn(Locators_CallSetting.speeddialsettingfirstcontactpost);
				minWait();
				clickBtn(Locators_CallSetting.delete);
			}
			else{
				
					clickBackButton_without_try_catch(4);
			
			minWait();
			}
			/*if(isElementExist(Locators_CallSetting.notSetInSpeedDialSettings)){
				APP_LOGS.info("Contact deleted in Speed Dail settings");
				sa.assertTrue(true, "Contact deleted in Speed Dail settings");
				test.log(LogStatus.PASS, "Contact deleted in Speed Dail settings");


			}else {
				APP_LOGS.info("Contact not deleted in Speed Dail settings");
				sa.fail();
				test.log(LogStatus.FAIL, "Contact not deleted in Speed Dail settings");



			}*/
		}catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->deleteContactInSpeedDailSettings()");
			e.printStackTrace();

		}catch (Exception e) {
			test.log(LogStatus.INFO, "Exception in deleteContactInSpeedDailSettings()");
		}


	}
	public void BlacklistincallScreening(String phoneno) throws InterruptedException {

		//* black list


		try {
			minWait();
			clickBtn(Locators_CallSetting.Manageblacklist);

			clickBtn(Locators_CallSetting.addanumberblack);
			minWait();
			enterTextToInputField(Locators_CallSetting.Phonenumber, phoneno);
			minWait();
			clickBtn(Locators_CallSetting.blockBtn);
			minWait();


		}catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->navigate_to_call_settings()");
			e.printStackTrace();

		}
		catch (Exception e) {
			test.log(LogStatus.ERROR, "Exception in BlacklistincallScreening()");

		}
	}
	public void BlacklistincallScreening_vzw(String phoneno) throws InterruptedException {

		//* black list


		try {
			minWait();
			
			clickBtn(Locators_CallSetting.addanumberblack);
			minWait();
			enterTextToInputField(Locators_CallSetting.Phonenumber, phoneno);
			minWait();
			clickBtn(Locators_CallSetting.blockBtn);
			minWait();


		}catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->navigate_to_call_settings()");
			e.printStackTrace();

		}
		catch (Exception e) {
			test.log(LogStatus.ERROR, "Exception in BlacklistincallScreening()");

		}
	}

	public void UnblockincallScreening() throws InterruptedException {

		// * unblock in black list


		try {
			clickBtn(Locators_CallSetting.deleteblockedno);
			minWait();
			clickBtn(Locators_CallSetting.unblockBtn);
			minWait();			
		}catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->UnblockincallScreening() ");
			e.printStackTrace();

		}
		catch (Exception e) {
			test.log(LogStatus.ERROR, "Exception in UnblockincallScreening() ");

		}
	}
	public void clearcallhistory()throws InterruptedException, IOException
	{

		/*
		 * clear frequents,all call log  and delete contacts before test
		 */
		try {
			launch_an_app("phone");
			clickBtn(Locators_CallSetting.Callhistorytab);
			minWait();
			if(isElementExist(Locators_CallSetting.dailedFirstNum)){
				navigateTocallHistory();
				minWait();
				clickBtn(Locators_CallSetting.moreOptionsInCallHistory);
				minWait();
				clickBtn(Locators_CallSetting.clearCallHistoryOpt);
				minWait();
				clickBtn(Locators_CallSetting.okBtn);
				customWait(3000);

			}else{

				clickBackButton_without_try_catch(4);
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->clearcallhistory()");
			e.printStackTrace();

		}catch (Exception e) {
			test.log(LogStatus.ERROR, "Exeption in ->clearcallhistory()");

		}
	}
	public void clearcallhistory_vzw()throws InterruptedException, IOException
	{

		/*
		 * clear frequents,all call log  and delete contacts before test
		 */
		try {
			launch_an_app("phone");
			clickBtn(Locators_CallSetting.vzw_recent);
			minWait();
			if(isElementExist(Locators_CallSetting.vzwdailedFirstNum)){
				clickBtn(Locators_CallSetting.vzwsettingsIcon);
				minWait();
				//clickBtn(Locators_CallSetting.moreOptionsInCallHistory);
				//minWait();
				clickBtn(Locators_CallSetting.vzw_callhistory);
				clickBtn(Locators_CallSetting.moreOptionsInCallHistory);
				minWait();
				clickBtn(Locators_CallSetting.clearCallHistoryOpt);
				minWait();
				clickBtn(Locators_CallSetting.okBtn);
				customWait(3000);

			}else{

				clickBackButton_without_try_catch(4);
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->clearcallhistory()");
			e.printStackTrace();

		}catch (Exception e) {
			test.log(LogStatus.ERROR, "Exeption in ->clearcallhistory()");

		}
	}


	public void navigateTocallHistory() throws InterruptedException 
	{
		/*
		 * navigate to call History page
		 */
		//	try {
		minWait();
		clickBtn(Locators_CallSetting.settingsIcon);
		minWait();
		clickBtn(Locators_CallSetting.callHistoryOpt);
		minWait();
		//} catch (Exception e) {
		//	e.printStackTrace();
		//test.log(LogStatus.ERROR, "Navigate to call history is failed");
		//}
	}
	public void MakeACall(String phone) throws InterruptedException, IOException
	{
		/*
		 * Validates Make a call option (Clicks Make a call --> Enters number--> Dial --> validate MO call with UI )
		 */
		try {

			launch_an_app("phone");
			minWait();
			clickBtn(Locators_CallSetting.callLogPage);
			minWait();
			clickBtn(Locators_CallSetting.makeACallOption);
			minWait();
			enterTextToInputField(Locators_CallSetting.dialpadEditFld, phone);
			minWait();
			clickBtn(Locators_CallSetting.callBtn);
			customWait(5000);
			clickBtn(Locators_CallSetting.Endcall);
			customWait(3000);

		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->MakeACall()");
			e.printStackTrace();

		}catch (Exception e) {
			test.log(LogStatus.ERROR, "Exeption in ->MakeACall()");

		}
	}
	public void blockAndUnblockNumber(SoftAssert sf2) throws InterruptedException
	{

		//* Block And Unblock number and validate

		try {

			minWait();
			blockUnblockNumber(Locators_CallSetting.blockNumberOpt, Locators_CallSetting.blockBtn);
			minWait();
			clickBtn(Locators_CallSetting.unblockNumberOpt);
			validateBlockAndUnblockNumber( "blocked","phoneno",sf2);
			minWait();

			blockUnblockNumber(Locators_CallSetting.unblockNumberOpt, Locators_CallSetting.unblockBtn);
			minWait();
			clickBtn(Locators_CallSetting.blockNumberOpt);
			validateBlockAndUnblockNumber("Unblocked","phoneno",sf2);
			minWait();
			clickBackButton(1);
			minWait();
			clickBtn(Locators_CallSetting.addedContactCallLog);
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->blockAndUnblockNumber()");
			e.printStackTrace();

		}catch (Exception e) {
			test.log(LogStatus.ERROR, "Exeption in ->blockAndUnblockNumber()");

		}
	}

	public void blockUnblockNumber(WebElement element, WebElement subElement) throws InterruptedException
	{
		/*
		 * Block And Unblock number
		 */

		minWait();
		element.click();
		minWait();
		subElement.click();
		minWait();
	} 
	public void validateBlockAndUnblockNumber(String status,String phonenum,SoftAssert sa) throws InterruptedException
	{
		/*
		 * Validates Block And Unblock number
		 */
		try {
			clickBtn(Locators_CallSetting.Callhistorytab);
			customWait(2000);
			System.out.println("Sarching...");
			if(isElementExist(Locators_CallSetting.Blocked)){
			String actualString = Locators_CallSetting.Blocked.getText();
			System.out.println(actualString);
			String NumString = Locators_CallSetting.dailedFirstNum.getText();
			System.out.println(NumString);
			if(actualString.contains("Blocked")){
				System.out.println("validateBlockAndUnblockNumber if condition is working");
				APP_LOGS.info("Number is blocked");
				sa.assertTrue(true, "Number is blocked");
				test.log(LogStatus.PASS, "Number is blocked ");

			}
			else{
				clickBtn(Locators_CallSetting.dailedFirstNum);
				minWait();
				if(!isElementExist(Locators_CallSetting.blockNumberOpt)){
					System.out.println("validateBlockAndUnblockNumber else condition is working");
					APP_LOGS.info("Number is  Unblocked");
					sa.assertTrue(true, "Number is  Unblocked");
					test.log(LogStatus.PASS, "Number is  Unblocked");

				}}
			}
			else if(!isElementExist(Locators_CallSetting.Blocked)) {
				System.out.println("sprint");
				clickBtn(Locators_CallSetting.dailedFirstNum);
				if(isElementExist(Locators_CallSetting.blockNumberOpt)){
					System.out.println("validateBlockAndUnblockNumber else condition is working");
					APP_LOGS.info("Number is  Unblocked");
					sa.assertTrue(true, "Number is  Unblocked");
					test.log(LogStatus.PASS, "Number is  Unblocked");
				}else{
				clickBtn(Locators_CallSetting.dailedFirstNum);
				if(!isElementExist(Locators_CallSetting.blockNumberOpt)){
					APP_LOGS.info("Number is blocked");
					sa.assertTrue(true, "Number is blocked");
					test.log(LogStatus.PASS, "Number is blocked ");
				}
			}
			
			
				}
			else{
					APP_LOGS.info("validateBlockAndUnblockNumber is failed");
					sa.fail();
					test.log(LogStatus.PASS, "validateBlockAndUnblockNumber is failed");

				}
			}
	
		
		catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->validateBlockAndUnblockNumber()");
			e.printStackTrace();

		}catch (Exception e) {
			test.log(LogStatus.ERROR, "Exeption in ->validateBlockAndUnblockNumber()");

		}
	}
	public void validateBlockAndUnblockNumber_vzw(String status,String phonenum,SoftAssert sa) throws InterruptedException
	{
		/*
		 * Validates Block And Unblock number
		 */
		try {
			clickBtn(Locators_CallSetting.vzw_recent);
			customWait(2000);
			System.out.println("Sarching...");
			String actualString = Locators_CallSetting.vzwBlocked.getText();
			System.out.println(actualString);
			String NumString = Locators_CallSetting.vzwdailedFirstNum.getText();
			System.out.println(NumString);

			if(actualString.contains("Blocked")){
				System.out.println("validateBlockAndUnblockNumber if condition is working");
				APP_LOGS.info("Number is blocked");
				sa.assertTrue(true, "Number is blocked");
				test.log(LogStatus.PASS, "Number is blocked ");

			}else if(isElementExist(Locators_CallSetting.vzwdailedFirstNum)) {
				clickBtn(Locators_CallSetting.vzwdailedFirstNum);
				if(isElementExist(Locators_CallSetting.vzwblockNumberOpt)){
					System.out.println("validateBlockAndUnblockNumber else condition is working");
					APP_LOGS.info("Number is  Unblocked");
					sa.assertTrue(true, "Number is  Unblocked");
					test.log(LogStatus.PASS, "Number is  Unblocked");

				}
				else{
					APP_LOGS.info("validateBlockAndUnblockNumber is failed");
					sa.fail();
					test.log(LogStatus.PASS, "validateBlockAndUnblockNumber is failed");

				}
			}
		}
		catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->validateBlockAndUnblockNumber_vzw()");
			e.printStackTrace();

		}catch (Exception e) {
			test.log(LogStatus.ERROR, "Exeption in ->validateBlockAndUnblockNumber_vzw()");

		}
	}

	public void navigateToCallDetails(WebElement element) throws InterruptedException
	{
		/*
		 * Navigates to call details with given element
		 */
		try {
			minWait();
			clickBtn(Locators_CallSetting.callLogPage);
			customWait(2000);
			//element.click();
			minWait();
			clickBtn(Locators_CallSetting.callDetailsOpt);
			minWait();

		} catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "Element Not Found");
		}
	}	
	public void validate_blocknumber_with_values(String phoneno,SoftAssert sa){

		try {

			clickBtn(Locators_CallSetting.addanumberblack);
			minWait();
			enterTextToInputField(Locators_CallSetting.Phonenumber, phoneno);
			minWait();
			clickBtn(Locators_CallSetting.blockBtn);
			minWait();
			if(isElementExist(Locators_CallSetting.blocklistfirstno)){
				System.out.println("No is blocked");

				APP_LOGS.info("blocked no is validated successfully");
				sa.assertTrue(true, "blocked no is validated successfully");
				test.log(LogStatus.PASS, "blocked no is validated successfully");				
			}else {

				APP_LOGS.info("blocked no is not validated ");
				sa.fail();
				test.log(LogStatus.FAIL, "blocked no is not validated ");
			}
		}catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->validate_blocknumber_with_values()");
			e.printStackTrace();

		}catch (Exception e) {

			test.log(LogStatus.INFO, "Exception in validate_blocknumber_with_values()");
		}
	}
	public void addingno_in_blacklist_callscreening(String phoneno1) throws InterruptedException{
		//Adding no in black list


		try {
			clickBtn(Locators_CallSetting.addanumberblack);
			minWait();
			enterTextToInputField(Locators_CallSetting.Phonenumber, phoneno1);
			minWait();
			clickBtn(Locators_CallSetting.blockBtn);
		}catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->addingno_in_blacklist_callscreening()");
			e.printStackTrace();

		}
		catch (Exception e) {
			test.log(LogStatus.ERROR, "Exception in addingno_in_blacklist_callscreening()");
		}

	}

	public void validate_pressing_backkey_once(String phoneno1,SoftAssert sa) throws InterruptedException
	{

		// pressing Back key while adding number to the blocked list

		try {
			minWait();
			clickBtn(Locators_CallSetting.Manageblacklist);
			minWait();
			clickBtn(Locators_CallSetting.addanumberblack);
			minWait();
			enterTextToInputField(Locators_CallSetting.Phonenumber, phoneno1);
			minWait();
			clickBackButton_without_try_catch(1);
			if(isElementExist(Locators_CallSetting.blockcallandtextscreen)){
				System.out.println("Image exist");
				APP_LOGS.info("Pressing backkey while adding no to blacklist is verified successfully");
				sa.assertTrue(true, "Pressing backkey while adding no to blacklist is verified successfully");
				test.log(LogStatus.PASS, "Pressing backkey while adding no to blacklist is verified successfully");	
			}else {
				System.out.println("image doesnot exist");
				APP_LOGS.info("Pressing backkey while adding no to blacklist is failure");
				test.log(LogStatus.PASS, "Pressing backkey while adding no to blacklist is failure");
				sa.fail();
				test.log(LogStatus.FAIL, "Pressing backkey while adding no to blacklist is failure");
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->validate_pressing_backkey_once()");
			e.printStackTrace();

		}
		catch (Exception e) {
			test.log(LogStatus.ERROR, "Exception in the locators->validate_pressing_backkey_once()");
		}


	}
	public void validate_pressing_backkey_once_vzw(String phoneno1,SoftAssert sa) throws InterruptedException
	{

		// pressing Back key while adding number to the blocked list

		try {
			launch_an_app("phone");
			minWait();
			navigate_To_Settings_AndElement_without_try_catch(Locators_CallSetting.vzwblockedno);
			minWait();
			clickBtn(Locators_CallSetting.addanumberblack);
			minWait();
			enterTextToInputField(Locators_CallSetting.Phonenumber, phoneno1);
			minWait();
			clickBackButton_without_try_catch(1);
			if(isElementExist(Locators_CallSetting.blockcallandtextscreen)){
				System.out.println("Image exist");
				APP_LOGS.info("Pressing backkey while adding no to blacklist is verified successfully");
				sa.assertTrue(true, "Pressing backkey while adding no to blacklist is verified successfully");
				test.log(LogStatus.PASS, "Pressing backkey while adding no to blacklist is verified successfully");	
			}else {
				System.out.println("image doesnot exist");
				APP_LOGS.info("Pressing backkey while adding no to blacklist is failure");
				test.log(LogStatus.PASS, "Pressing backkey while adding no to blacklist is failure");
				sa.fail();
				test.log(LogStatus.FAIL, "Pressing backkey while adding no to blacklist is failure");
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->validate_pressing_backkey_once()");
			e.printStackTrace();

		}
		catch (Exception e) {
			test.log(LogStatus.ERROR, "Exception in the locators->validate_pressing_backkey_once()");
		}


	}

	public void validate_pressing_backkey_twice(String phoneno1,SoftAssert sa) throws InterruptedException{
		// pressing Back key while adding number to the blocked list.

		try{
			launch_an_app("phone");
			navigateToSettingsAndElement_without_try_catch(Locators_CallSetting.Callscreening);
			minWait();
			clickBtn(Locators_CallSetting.Manageblacklist);
			minWait();
			clickBtn(Locators_CallSetting.addanumberblack);
			minWait();
			enterTextToInputField(Locators_CallSetting.Phonenumber, phoneno1);
			minWait();
			clickBackButton_without_try_catch(2);

			customWait(2000);
			Runtime.getRuntime().exec("adb -s "+p_Id+" shell input tap 540 1222");
			minWait();

			if(isElementExist(Locators_CallSetting.addanumberblack)) {

				System.out.println("Adding no to block is displayed");
				APP_LOGS.info("Blockedno screen page is dispalyed");
				sa.assertTrue(true, "Blockedno screen page is dispalyed");
				test.log(LogStatus.PASS, "Blockedno screen page is dispalyed");
			}else {
				System.out.println("not displayed");
				APP_LOGS.info("Blockedno screen page is not displayed");
				test.log(LogStatus.FAIL, "Blockedno screen page is not dispalyed");
				sa.fail();
				test.log(LogStatus.FAIL, "Blockedno screen page is not dispalyed");
			}
		}catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->validate_pressing_backkey_twice()");
			e.printStackTrace();

		}catch(Exception e) {
			test.log(LogStatus.ERROR, " Exception in validate_pressing_backkey_twice()");
		}

	}
	public void validate_pressing_backkey_twice_vzw(String phoneno1,SoftAssert sa) throws InterruptedException{
		// pressing Back key while adding number to the blocked list.

		try{
			launch_an_app("phone");
			minWait();
			navigateToSettingsAndElement(Locators_CallSetting.vzwblockedno);
			minWait();
			clickBtn(Locators_CallSetting.addanumberblack);
			minWait();
			enterTextToInputField(Locators_CallSetting.Phonenumber, phoneno1);
			minWait();
			clickBackButton_without_try_catch(2);

			customWait(2000);
			Runtime.getRuntime().exec("adb -s "+p_Id+" shell input tap 540 1222");
			minWait();

			if(isElementExist(Locators_CallSetting.addanumberblack)) {

				System.out.println("Adding no to block is displayed");
				APP_LOGS.info("Blockedno screen page is dispalyed");
				sa.assertTrue(true, "Blockedno screen page is dispalyed");
				test.log(LogStatus.PASS, "Blockedno screen page is dispalyed");
			}else {
				System.out.println("not displayed");
				APP_LOGS.info("Blockedno screen page is not displayed");
				test.log(LogStatus.FAIL, "Blockedno screen page is not dispalyed");
				sa.fail();
				test.log(LogStatus.FAIL, "Blockedno screen page is not dispalyed");
			}
		}catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->validate_pressing_backkey_twice()");
			e.printStackTrace();

		}catch(Exception e) {
			test.log(LogStatus.ERROR, " Exception in validate_pressing_backkey_twice()");
		}

	}
	public void validate_pressing_Shortpress_HomeKey(String phoneno1,SoftAssert sa) throws InterruptedException{

		try{
			launch_an_app("phone");
			navigateToSettingsAndElement_without_try_catch(Locators_CallSetting.Callscreening);
			minWait();
			clickBtn(Locators_CallSetting.Manageblacklist);
			minWait();
			clickBtn(Locators_CallSetting.addanumberblack);
			minWait();
			enterTextToInputField(Locators_CallSetting.Phonenumber, phoneno1);
			minWait();
			//clickBackButton(2);
			customWait(3000);
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_HOME);
			minWait();
			if (isElementExist(Locators_CallSetting.Appslist)) {
				APP_LOGS.info(" Homescreen is displayed");
				sa.assertTrue(true, "Homescreen is displayed");
				test.log(LogStatus.PASS, "Homescreen is displayed");

			}else {
				APP_LOGS.info("Homescreen is not displayed");
				test.log(LogStatus.FAIL, "Homescreen is not displayed");
				sa.fail();
				test.log(LogStatus.FAIL, "Homescreen is not displayed");
			}
		}catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->validate_pressing_Shortpress_HomeKey()");
			e.printStackTrace();

		}catch(Exception e) {

			test.log(LogStatus.ERROR, "Exception in validate_pressing_Shortpress_HomeKey()");
		}

	}
	public void validate_pressing_Shortpress_HomeKey_vzw(String phoneno1,SoftAssert sa) throws InterruptedException{

		try{
			launch_an_app("phone");
			minWait();
			navigateToSettingsAndElement(Locators_CallSetting.vzwblockedno);
			minWait();
			clickBtn(Locators_CallSetting.addanumberblack);
			minWait();
			enterTextToInputField(Locators_CallSetting.Phonenumber, phoneno1);
			minWait();
			//clickBackButton(2);
			customWait(3000);
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_HOME);
			minWait();
			if (isElementExist(Locators_CallSetting.Appslist)) {
				APP_LOGS.info(" Homescreen is displayed");
				sa.assertTrue(true, "Homescreen is displayed");
				test.log(LogStatus.PASS, "Homescreen is displayed");

			}else {
				APP_LOGS.info("Homescreen is not displayed");
				test.log(LogStatus.FAIL, "Homescreen is not displayed");
				sa.fail();
				test.log(LogStatus.FAIL, "Homescreen is not displayed");
			}
		}catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->validate_pressing_Shortpress_HomeKey()");
			e.printStackTrace();

		}catch(Exception e) {

			test.log(LogStatus.ERROR, "Exception in validate_pressing_Shortpress_HomeKey()");
		}

	}

	public void validate_pressing_Longpress_HomeKey(String phoneno1,SoftAssert sa) throws InterruptedException{

		//pressing Home key (LONG PRESS)while adding number to the blocked list

		try{


			launch_an_app("phone");
			navigateToSettingsAndElement_without_try_catch(Locators_CallSetting.Callscreening);
			minWait();
			clickBtn(Locators_CallSetting.Manageblacklist);
			minWait();
			clickBtn(Locators_CallSetting.addanumberblack);
			minWait();
			enterTextToInputField(Locators_CallSetting.Phonenumber, phoneno1);
			minWait();
			//clickBackButton(2);
			customWait(3000);
			Runtime.getRuntime().exec("adb -s "+p_Id+" shell input keyevent --longpress KEYCODE_HOME");
			/* TouchAction action = new TouchAction(aDriver);
		action.longPress(Locators_CallSetting.file_list_SoundRec).release().perform();;
			 */

			minWait();
			if (isElementExist(Locators_CallSetting.googlequicksearch)) {
				APP_LOGS.info(" Googlenow on tap screen is displayed");
				sa.assertTrue(true, "Googlenow on tap screen is displayed");
				test.log(LogStatus.PASS, "Googlenow on tap screen is displayed");

			}else {
				APP_LOGS.info("Googlenow on tap screen is not displayed");
				test.log(LogStatus.FAIL, "Googlenow on tap screen is not displayed");
				sa.fail();
				test.log(LogStatus.FAIL, "Googlenow on tap screen is not displayed");
			}
		}catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->validate_pressing_Longpress_HomeKey()");
			e.printStackTrace();

		}catch(Exception e) {
			test.log(LogStatus.ERROR, "Exception in validate_pressing_Longpress_HomeKey()");
		}

	}
	public void validate_pressing_Recentsapp_Key(String phoneno1,SoftAssert sa) throws InterruptedException{

		//pressing Recent apps key while adding number to the blocked list

		try{
			minWait();
			clickBackButton_without_try_catch(4);
			launch_an_app("phone");
			navigateToSettingsAndElement_without_try_catch(Locators_CallSetting.Callscreening);
			minWait();
			clickBtn(Locators_CallSetting.Manageblacklist);
			minWait();
			clickBtn(Locators_CallSetting.addanumberblack);
			minWait();
			enterTextToInputField(Locators_CallSetting.Phonenumber, phoneno1);
			minWait();
			//clickBackButton(2);
			customWait(3000);
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_APP_SWITCH);
			minWait();	 	
			if(!isElementExist(Locators_BaseUtil.no_Recent_Items)) {
				APP_LOGS.info(" Recentappkey is displaying recent opened apps");
				sa.assertTrue(true, "Recentappkey is displaying recent opened apps");
				test.log(LogStatus.PASS, "Recentappkey is displaying recent opened apps");

			}else {
				APP_LOGS.info("Recentappkey is not displaying recent opened apps");
				test.log(LogStatus.FAIL, "Recentappkey is not displaying recent opened apps");
				sa.fail();
				test.log(LogStatus.FAIL, "Recentappkey is not displaying recent opened apps");

			}

		}catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->validate_pressing_Recentsapp_Key()");
			e.printStackTrace();

		}
		catch(Exception e) {
			test.log(LogStatus.ERROR, "Exception in validate_pressing_Recentsapp_Key() ");
		}

	}
	public void validate_pressing_Recentsapp_Key_vzw(String phoneno1,SoftAssert sa) throws InterruptedException{

		//pressing Recent apps key while adding number to the blocked list

		try{
			minWait();
			clickBackButton_without_try_catch(4);
			launch_an_app("phone");
			minWait();
			navigateToSettingsAndElement(Locators_CallSetting.vzwblockedno);
			minWait();
			clickBtn(Locators_CallSetting.addanumberblack);
			minWait();
			enterTextToInputField(Locators_CallSetting.Phonenumber, phoneno1);
			minWait();
			//clickBackButton(2);
			customWait(3000);
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_APP_SWITCH);
			minWait();	 	
			if(!isElementExist(Locators_BaseUtil.no_Recent_Items)) {
				APP_LOGS.info(" Recentappkey is displaying recent opened apps");
				sa.assertTrue(true, "Recentappkey is displaying recent opened apps");
				test.log(LogStatus.PASS, "Recentappkey is displaying recent opened apps");

			}else {
				APP_LOGS.info("Recentappkey is not displaying recent opened apps");
				test.log(LogStatus.FAIL, "Recentappkey is not displaying recent opened apps");
				sa.fail();
				test.log(LogStatus.FAIL, "Recentappkey is not displaying recent opened apps");

			}

		}catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->validate_pressing_Recentsapp_Key()");
			e.printStackTrace();

		}
		catch(Exception e) {
			test.log(LogStatus.ERROR, "Exception in validate_pressing_Recentsapp_Key() ");
		}

	}
	public void whitelist_callscreening(String phoneno){

		try {
			minWait();

			clickBtn(Locators_CallSetting.addanumberwhite);
			minWait();
			enterTextToInputField(Locators_CallSetting.Phonenumber, phoneno);
			minWait();
			clickBtn(Locators_CallSetting.addBtn);
			customWait(2000);
		}
		catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->validate_Editedquickresponse_displayproperly_duringcallscreen()");
			e.printStackTrace();

		}catch (Exception e) {
			test.log(LogStatus.ERROR, "Exeption in ->validate_Editedquickresponse_displayproperly_duringcallscreen()");

		}
	}

	public void addContactNumWhitelist(String phoneno) throws InterruptedException{
		try {

			clickBtn(Locators_CallSetting.addanumberwhite);
			minWait();
			enterTextToInputField(Locators_CallSetting.Phonenumber, phoneno);
			minWait();
			clickBtn(Locators_CallSetting.addBtn);
			customWait(2000);


			//whitelist_callscreening(phoneNum);

		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->addContactNumWhitelist()");
			e.printStackTrace();

		}catch (Exception e) {
			test.log(LogStatus.ERROR, "Exception in addContactNumWhitelist()");
		}	
	}
	public void deleting_no_in_blacklist() throws InterruptedException{

		try{

			while(true){

				minWait();

				if(isElementExist(Locators_CallSetting.deleteblockedno)){

					System.out.println("--------------p-------------");


					clickBtn(Locators_CallSetting.deleteblockedno);

					clickBtn(Locators_CallSetting.unblockBtn);



				}else {

					System.out.println("-+++++++++++++f+++++++++++");


					break;
				}
			}

		}catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->deleting_no_in_whitelist()");
			e.printStackTrace();

		}
		catch(Exception e){
			test.log(LogStatus.ERROR, "Exception in->deleting_no_in_whitelist()");
		}
	}
	public void deleting_no_in_whitelist() throws InterruptedException{

		try{

			while(true){

				minWait();

				if(isElementExist(Locators_CallSetting.removeBtn)){

					System.out.println("--------------p-------------");


					clickBtn(Locators_CallSetting.removeBtn);

					clickBtn(Locators_CallSetting.removetxt);



				}else {

					System.out.println("-+++++++++++++f+++++++++++");


					break;
				}
			}

		}catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->deleting_no_in_whitelist()");
			e.printStackTrace();

		}
		catch(Exception e){
			test.log(LogStatus.ERROR, "Exception in->deleting_no_in_whitelist()");
		}
	}
	public void deleting_no_whitelist() throws InterruptedException{

		try{
			clickBtn(Locators_CallSetting.Managewhitelist);
			minWait();
			clickBtn(Locators_CallSetting.removenoBtn);
			minWait();
			clickBtn(Locators_CallSetting.removetxt);
			minWait();

		}
		catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->deleting_no_whitelist()");
			e.printStackTrace();

		}catch (Exception e) {
			test.log(LogStatus.ERROR, "Exeption in ->deleting_no_whitelist()");

		}
	}
	public void deleting_no_whitelist_without_try_catch() throws InterruptedException{

		//	try{
		launch_an_app("phone");
		minWait();
		navigateToSettingsAndElement_without_try_catch(Locators_CallSetting.Callscreening);
		minWait();
		clickBtn(Locators_CallSetting.Managewhitelist);
		minWait();
		clickBtn(Locators_CallSetting.removenoBtn);
		minWait();
		clickBtn(Locators_CallSetting.removetxt);
		minWait();

		//	}
		//	catch(Exception e){
		//	test.log(LogStatus.ERROR, "Error indeleting_no_whitelist()");
		//	e.printStackTrace();
		//}
	}
	public void deleting_no_in_blacklist(String phoneno) throws InterruptedException{

		try {

			clickBtn(Locators_CallSetting.deleteblockBtninContact);
			minWait();
			clickBtn(Locators_CallSetting.unblocktxt);
			minWait();
		}catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->deleting_no_in_blacklist()");
			e.printStackTrace();

		}
		catch (Exception e) {
			test.log(LogStatus.ERROR, "Exception in->deleting_no_in_blacklist()");
		}				
	}
	public void deleting_blacklist() throws InterruptedException{

		try{

			while(true){

				customWait(2000);

				if(isElementExist(Locators_CallSetting.deleteblockBtninContact)){
					System.out.println("--------------p-------------");


					clickBtn(Locators_CallSetting.deleteblockBtninContact);

					clickBtn(Locators_CallSetting.unblocktxt);



				}else {

					System.out.println("-+++++++++++++f+++++++++++");


					break;
				}
			}

		}catch (org.openqa.selenium.NoSuchElementException e) {


			clickBackButton_without_try_catch(1);
			clickBtn(Locators_CallSetting.Manageblacklist);
			minWait();
			deleting_blacklist();
			test.log(LogStatus.ERROR, "Error in the locators->deleting_blacklist()");
			e.printStackTrace();

		}
		catch(Exception e){
			test.log(LogStatus.ERROR, "Exception in->deleting_blacklist()");
		}
	}
	/*public void delete_no_blacklist() throws InterruptedException{
		//blocking  numbers from Contact app
		try{


			if(isElementExist(Locators_CallSetting.deleteblockBtninContact)){
				minWait();
				clickBtn(Locators_CallSetting.deleteblockBtninContact);
				minWait();
				clickBtn(Locators_CallSetting.unblocktxt);
				minWait();
			}else{
				clickBackButton_without_try_catch(4);
			}
		}catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->delete_no_blacklist()");
			e.printStackTrace();

		}catch (Exception e) {
			test.log(LogStatus.ERROR, "Exeption in ->delete_no_blacklist()");

		}
	}*/
	/*public void delete_all_No_in_blacklist() throws InterruptedException{
		try{
			launch_an_app("phone");

			minWait();
			navigateToSettingsAndElement(Locators_CallSetting.Callscreening);
			minWait();
			clickBtn(Locators_CallSetting.Manageblacklist);
			minWait();
			if(isElementExist(Locators_CallSetting.deleteblockBtninContact)){
				minWait();
				clickBtn(Locators_CallSetting.deleteblockBtninContact);
				minWait();
				clickBtn(Locators_CallSetting.unblocktxt);
				minWait();

			}
			else{
				clickBackButton(4);
			}
		}catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->delete_all_No_in_blacklist()");
			e.printStackTrace();

		}catch (Exception e) {
			test.log(LogStatus.ERROR, "Exeption in ->delete_all_No_in_blacklist()");

		}	
	}*/
	public void validatescrollingno_in_whitelist(String phoneNum,SoftAssert sa){
		try{
			System.out.println(phoneNum);
			if(scrollTo(phoneNum)){
				check=true;
				APP_LOGS.info("Validated Scrolling no in whitelist contact");
				sa.assertTrue(true, "Validated Scrolling no in whitelist contact");
				test.log(LogStatus.PASS, "Validated Scrolling no in whitelist contact");
			} else {
				APP_LOGS.info("Validation Scrolling no in whitelist contactis is failed");
				sa.fail();
				test.log(LogStatus.FAIL,"Validation Scrolling no in whitelist contact is failed");

			}
		}catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->validatescrollingno_in_whitelist()");
			e.printStackTrace();

		}catch(Exception e) {
			test.log(LogStatus.ERROR, "Exception in the locators->validatescrollingno_in_whitelist()");
		}
	}
	public void validatescrollingno_in_blacklist(String phoneNum,SoftAssert sa){
		//
		try {
			System.out.println(phoneNum);

			if(scrollTo(phoneNum)){
				System.out.println("PASSSSSSSSSSSSSSSSSSSss");
				APP_LOGS.info("validated Scrolling no in blacklist contact");
				sa.assertTrue(true, "validated Scrolling no in blacklist contact");
				test.log(LogStatus.PASS, "validated Scrolling no in blacklist contact ");
			} else {
				System.out.println("FAIL---------------");

				APP_LOGS.info("Validation Scrolling no in blacklist contact is failed ");
				sa.fail();
				test.log(LogStatus.FAIL,"Validation Scrolling no in blacklist contact is failed");

			}
		}catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->validatescrollingno_in_blacklist()");
			e.printStackTrace();

		}catch(Exception e) {
			test.log(LogStatus.ERROR, "Exception  in->validatescrollingno_in_blacklist()");
		}

	}


	public void validate_cancel_option_blacklist(String phoneno1,SoftAssert sa) throws InterruptedException{

		//**CANCEL options while adding number to black list

		try {

			launch_an_app("phone");
			navigateToSettingsAndElement_without_try_catch(Locators_CallSetting.Callscreening);
			minWait();
			clickBtn(Locators_CallSetting.Manageblacklist);
			minWait();
			clickBtn(Locators_CallSetting.addanumberblack);
			minWait();
			enterTextToInputField(Locators_CallSetting.Phonenumber, phoneno1);
			minWait();
			clickBtn(Locators_CallSetting.canceltxt);
			minWait();
			if(!isElementExist(Locators_CallSetting.blocklistfirstno)){
				APP_LOGS.info("BlacklistNumber is not added to list");
				sa.assertTrue(true, "BlacklistNumber is not added to list");
				test.log(LogStatus.PASS, "BlacklistNumber is not added to list");

			}else {
				APP_LOGS.info("BlacklistNumber is added to list");
				sa.fail();
				test.log(LogStatus.FAIL, "BlacklistNumber is added to list");

			}
		}catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->validate_cancel_option_blacklist()");
			e.printStackTrace();

		}
		catch(Exception e) {
			test.log(LogStatus.ERROR, "Exception in validate_cancel_option_blacklist()");
		}

	}
	public void validate_cancel_option_blacklist_vzw(String phoneno1,SoftAssert sa) throws InterruptedException{

		//**CANCEL options while adding number to black list

		try {

			launch_an_app("phone");
			navigateToSettingsAndElement_without_try_catch(Locators_CallSetting.vzwblockedno);
			minWait();
			clickBtn(Locators_CallSetting.addanumberblack);
			minWait();
			enterTextToInputField(Locators_CallSetting.Phonenumber, phoneno1);
			minWait();
			clickBtn(Locators_CallSetting.canceltxt);
			minWait();
			if(!isElementExist(Locators_CallSetting.blocklistfirstno)){
				APP_LOGS.info("BlacklistNumber is not added to list");
				sa.assertTrue(true, "BlacklistNumber is not added to list");
				test.log(LogStatus.PASS, "BlacklistNumber is not added to list");

			}else {
				APP_LOGS.info("BlacklistNumber is added to list");
				sa.fail();
				test.log(LogStatus.FAIL, "BlacklistNumber is added to list");

			}
		}catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->validate_cancel_option_blacklist()");
			e.printStackTrace();

		}
		catch(Exception e) {
			test.log(LogStatus.ERROR, "Exception in validate_cancel_option_blacklist()");
		}

	}
	public void validate_cancel_option_whitelist(String phoneno1,SoftAssert sa) throws InterruptedException{

		//**CANCEL options while adding number to White list

		try {
			/*launch_an_app("phone");
			navigateToSettingsAndElement_without_try_catch(Locators_CallSetting.Callscreening);
			minWait();
			clickBtn(Locators_CallSetting.Managewhitelist);
			minWait();*/
			clickBtn(Locators_CallSetting.addanumberwhite);
			minWait();
			enterTextToInputField(Locators_CallSetting.Phonenumber, phoneno1);
			minWait();
			clickBtn(Locators_CallSetting.canceltxt);
			minWait();

			if(!isElementExist(Locators_CallSetting.blocklistfirstno)){
				APP_LOGS.info(" WhitelistNumber is not added to list");
				sa.assertTrue(true, "WhitelistNumber is not added to list");
				test.log(LogStatus.PASS, "WhitelistNumber is not added to list");

			}else {
				APP_LOGS.info("WhitelistNumber is added to list");
				sa.fail();
				test.log(LogStatus.FAIL, "WhitelistNumber is added to list");

			}
		}catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->validate_cancel_option_whitelist()");
			e.printStackTrace();

		}
		catch(Exception e) {
			test.log(LogStatus.ERROR, "Exception in validate_cancel_option_whitelist()");
		}

	}


	public void validate_addno_option_under_callsettings_blacklist(SoftAssert sa) throws InterruptedException{
		// number field is displayed on selecting "ADD A NUMBER" option under Black List
		try {
			/*launch_an_app("phone");
			minWait();
			navigateToSettingsAndElement_without_try_catch(Locators_CallSetting.Callscreening);
			minWait();
			clickBtn(Locators_CallSetting.Manageblacklist);
			minWait();*/
			clickBtn(Locators_CallSetting.addanumberblack);
			minWait();
			if(isElementExist(Locators_CallSetting.blackcontactList)){
				APP_LOGS.info(" numberfield option is displayed in blacklist");
				sa.assertTrue(true, "numberfield option is displayed in blacklist");
				test.log(LogStatus.PASS, "numberfield option is displayed in blacklist");

			}else {
				APP_LOGS.info("numberfield option is not displayed in blacklist");
				sa.fail();
				test.log(LogStatus.FAIL, "numberfield option is not displayed in blacklist");
			}
		}catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->validate_addno_option_under_callsettings_blacklist()");
			e.printStackTrace();

		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exception in validate_addno_option_under_callsettings_blacklist()");
		}
	}
	public void validate_addno_option_under_callsettings_Whitelist(SoftAssert sa) throws InterruptedException{
		// number field is displayed on selecting "ADD A NUMBER" option under white List
		try {
			launch_an_app("phone");
			minWait();
			navigateToSettingsAndElement_without_try_catch(Locators_CallSetting.Callscreening);
			minWait();
			clickBtn(Locators_CallSetting.Managewhitelist);
			minWait();
			clickBtn(Locators_CallSetting.addanumberwhite);
			minWait();
			if(isElementExist(Locators_CallSetting.whitecontactList)){
				APP_LOGS.info(" numberfield option is displayed in whitelist ");
				sa.assertTrue(true, "numberfield option is displayed in whitelist");
				test.log(LogStatus.PASS, "numberfield option is displayed in whitelist");

			}else {
				APP_LOGS.info("numberfield option is not displayed in whitelist");
				sa.fail();
				test.log(LogStatus.FAIL, "numberfield option is not displayed in whitelist");

			}
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->validate_addno_option_under_callsettings_Whitelist()");
			e.printStackTrace();

		}catch (Exception e) {
			test.log(LogStatus.ERROR, "Exception in validate_addno_option_under_callsettings_Whitelist()");
		}
	}

	public void checkScreeningIncomingEnabled() throws InterruptedException{

		if(!Locators_CallSetting.screeningincomingcallsetting.isEnabled()){
			clickBtn(Locators_CallSetting.screeningincomcallcheckbox);
			minWait();
			System.out.println("Clicked enabled checkbox");
		}
	}
	public void checkScreeningOutgoingEnabled() throws InterruptedException{
		if(!Locators_CallSetting.screeningoutgoingcallcheckbox.isEnabled()){
			clickBtn(Locators_CallSetting.screeningincomcallcheckbox);
			minWait();
			System.out.println("Clicked enabled checkbox");
		}

	}
	public void checkScreeningOutgoingDisabled() throws InterruptedException{
		if(Locators_CallSetting.screeningoutgoingcall.isEnabled()){
			clickBtn(Locators_CallSetting.screeningoutgoingcallcheckbox);
			minWait();
			System.out.println("Clicked disabled checkbox");
		}

	}
	public void checkScreeningIncomingDisabled() throws InterruptedException{
		if(Locators_CallSetting.screeningincomingcallsetting.isEnabled()){
			clickBtn(Locators_CallSetting.screeningincomcallcheckbox);
			minWait();
			System.out.println("Clicked disabled checkbox");
		}

	}

	public void validate_screening_incoming_call_highlighted(SoftAssert sa){
		//Screening incoming call Setting option is highlighted (not greyed out)
		try {
			launch_an_app("phone");
			minWait();
			navigateToSettingsAndElement_without_try_catch(Locators_CallSetting.Callscreening);
			minWait();
			checkScreeningIncomingEnabled();
			if(Locators_CallSetting.screeningincomingcallsetting.isEnabled()){
				clickBtn(Locators_CallSetting.screeningincomingcallsetting);
				minWait();
				if(isElementExist(Locators_CallSetting.ScreeningincomingcallsettingOpt) && isElementExist(Locators_CallSetting.blockblacklistopt) && isElementExist(Locators_CallSetting.allowwhitelistopt)){
					System.out.println("Highlighted");

					APP_LOGS.info(" Screening incoming call setting is highlighted & options are shown ");
					sa.assertTrue(true, " Screening incoming call setting is highlighted & options are shown ");
					test.log(LogStatus.PASS, "Screening incoming call setting is highlighted & options are shown");

				}else {
					APP_LOGS.info("Screening incoming call setting is not highlighted & options are not shown");
					sa.fail();
					test.log(LogStatus.FAIL, "Screening incoming call setting is not highlighted & options are not shown");
				}
			}else{
				APP_LOGS.info("Screening incoming call setting is not highlighted & options are not shown");
				sa.fail();
				test.log(LogStatus.FAIL, "Screening incoming call setting is not highlighted & options are not shown");

			}
		}catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->validate_screening_incoming_call_highlighted()");
			e.printStackTrace();

		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exception in validate_screening_incoming_call_highlighted()");
		}
	}

	public void validate_Callscreening_under_Callsettings(SoftAssert sa)
	{
		try{

			launch_an_app("phone");
			minWait();
			clickBtn(Locators_CallSetting.settingsIcon);
			minWait();
			clickBtn(Locators_CallSetting.settingsOpt);
			minWait();
			clickBtn(Locators_CallSetting.Callscreening);
			minWait();
			if(isElementExist(Locators_CallSetting.callscreeningpage)){
				APP_LOGS.info(" CallScreening present under Callsettings  ");
				sa.assertTrue(true, "CallScreening present under Callsettings");
				test.log(LogStatus.PASS, "CallScreening present under Callsettings");

			}else {
				APP_LOGS.info("CallScreening not present under Callsettings");
				sa.fail();
				test.log(LogStatus.FAIL, "CallScreening not present under Callsettings");

			}
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->validate_Callscreening_under_Callsettings()");
			e.printStackTrace();

		}catch (Exception e) {
			test.log(LogStatus.ERROR, "Exception in validate_Callscreening_under_Callsettings()");
		}
	}

	public void blockno_in_contactapp(String refNum){
		//Blocked and unblocked numbers in phone contact settings
		try{

			clickBtn(Locators_CallSetting.opennavigationdrawer);
			minWait();
			clickBtn(Locators_CallSetting.Settings);
			minWait();
			scrollToText("Blocked numbers");
			minWait();
			clickBtn(Locators_CallSetting.addanumberblack);
			minWait();
			enterTextToInputField(Locators_CallSetting.Phonenumber, refNum);
			minWait();
			clickBtn(Locators_CallSetting.blockBtn);
			minWait();
			clickBackButton_without_try_catch(4);
			minWait();

		}
		catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->blockno_in_contactapp()");
			e.printStackTrace();

		}catch (Exception e) {
			test.log(LogStatus.ERROR, "Exeption in ->blockno_in_contactapp()");

		}
	}
	public void validate_blockno_in_blacklist(SoftAssert sa) throws InterruptedException{
		try{
			launch_an_app("phone");
			navigateToSettingsAndElement_without_try_catch(Locators_CallSetting.Callscreening);
			minWait();
			clickBtn(Locators_CallSetting.Manageblacklist);
			minWait();
			if(isElementExist(Locators_CallSetting.firstblockedno)){
				System.out.println("validate blockno in blacklist if condition is working");
				APP_LOGS.info(" Blockingno from contacts app is reflected in Black lsit under Call screening");
				sa.assertTrue(true, "Blockingno from contacts app is reflected in Black lsit under Call screening");
				test.log(LogStatus.PASS, "Blockingno from contacts app is reflected in Black lsit under Call screening");

			}else {

				System.out.println("validate blockno in blacklist else condition is working");
				APP_LOGS.info("Blockingno from contacts app is not reflected in Black lsit under Call screening");
				sa.fail();
				test.log(LogStatus.FAIL, "Blockingno from contacts app is not reflected in Black lsit under Call screening");
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->validate_blockno_in_blacklist()");
			e.printStackTrace();

		}catch (Exception e) {
			test.log(LogStatus.ERROR, "Exeption in ->validate_blockno_in_blacklist()");

		}
	}
	public void validate_blockno_in_blacklist_vzw(SoftAssert sa) throws InterruptedException{
		try{
			launch_an_app("phone");
			navigateToSettingsAndElement(Locators_CallSetting.vzwblockedno);
			minWait();
			minWait();
			if(isElementExist(Locators_CallSetting.firstblockedno)){
				System.out.println("validate blockno in blacklist if condition is working");
				APP_LOGS.info(" Blockingno from contacts app is reflected in Black lsit under Call screening");
				sa.assertTrue(true, "Blockingno from contacts app is reflected in Black lsit under Call screening");
				test.log(LogStatus.PASS, "Blockingno from contacts app is reflected in Black lsit under Call screening");

			}else {

				System.out.println("validate blockno in blacklist else condition is working");
				APP_LOGS.info("Blockingno from contacts app is not reflected in Black lsit under Call screening");
				sa.fail();
				test.log(LogStatus.FAIL, "Blockingno from contacts app is not reflected in Black lsit under Call screening");
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->validate_blockno_in_blacklist()");
			e.printStackTrace();

		}catch (Exception e) {
			test.log(LogStatus.ERROR, "Exeption in ->validate_blockno_in_blacklist()");

		}
	}



	public void make_Call_from_RefDev() throws InterruptedException, IOException {
		//make a call from ref device to primary device
		try {
			Runtime.getRuntime().exec("adb -s "+r_Id+" shell am start -a android.intent.action.CALL -d tel:"+pryNum);
			minWait();
		}catch (Exception e) {
			test.log(LogStatus.ERROR, "Exeption in ->make_Call_from_RefDev()");

		}
	}



	public void make_Call_from_PrmyDev() throws InterruptedException, IOException {
		//make a call from ref device to primary device
		try {
			System.out.println("Inside Make a call");
			Runtime.getRuntime().exec("adb -s "+p_Id+" shell am start -a android.intent.action.CALL -d tel:"+refNum);
			customWait(10000);			
			System.out.println("Call is activitated");
			minWait();

		}catch (Exception e) {
			test.log(LogStatus.ERROR, "Exeption in -> make_Call_from_PrmyDev()");

		}
	}


	public void endCall_RefDevice() {
		try {
			minWait();
			Runtime.getRuntime().exec("adb -s "+r_Id+" shell input keyevent 6");
			Thread.sleep(1000);
		}catch (Exception e) {
			test.log(LogStatus.ERROR, "Exeption in ->endCall_RefDevice()");

		}
	}

	public void endCall_PrmyDevice() {
		try {
			minWait();
			Runtime.getRuntime().exec("adb -s "+p_Id+" shell input keyevent 6");
			Thread.sleep(1000);
		}catch (Exception e) {
			test.log(LogStatus.ERROR, "Exeption in ->endCall_PrmyDevice() ");

		}
	}
	public void recieve_Call_PrimaryDev_O() throws IOException, InterruptedException {

		try {

			while(true){

				System.out.println("Running-------------------");

				Process child = null;

				if (p_b_No.contains("8A.")) {
					System.out.println("XP8");					
					child = Runtime.getRuntime().exec("adb -s "+ p_Id+" shell service call telecom 29");
				} else if(p_b_No.contains("5SA.")) {
					System.out.println("XP5");
					child=Runtime.getRuntime().exec("adb -s "+p_Id+" shell service call telecom 28");
				}
				InputStream lsOut = child.getInputStream();
				InputStreamReader r = new InputStreamReader(lsOut);
				BufferedReader in = new BufferedReader(r);
				String  value=in.readLine();
				System.out.println(value);
				if(value.contains("00000001")) {
					System.out.println("Phone is ringing so accepting call.");
					Runtime.getRuntime().exec("adb -s "+p_Id+" shell input keyevent 5");
					break;

				} else {
					System.out.println("Not accepting call.");

				}		
			}

			System.out.println("coming Out of loop");

		}catch(Exception e) {
			Thread.sleep(2000);
			Runtime.getRuntime().exec("adb -s "+p_Id+" shell input keyevent 5");
			Thread.sleep(2000);
			test.log(LogStatus.ERROR, "Exeption in ->recieve_Call_PrimaryDev_O()");
		}
	}




	public void validate_calllog_callreceived(SoftAssert sa){
		try{
			launch_an_app("phone");
			minWait();
			clickBtn(Locators_CallSetting.Callhistorytab);
			minWait();
			String dialedno =Locators_CallSetting.dailedFirstNum.getText();
			String new_Dial_Num=dialedno.replaceAll("\\s+","");
			String new_Ref_Num=refNum.replaceAll("\\s+", "");

			System.out.println(new_Dial_Num +","+new_Ref_Num);

			if(new_Dial_Num.contains(new_Ref_Num)){
				APP_LOGS.info("Call received successfully");
				sa.assertTrue(true, "Call received successfully");
				test.log(LogStatus.PASS, "Call received successfully");


			} else	{
				APP_LOGS.info("Call not received");
				test.log(LogStatus.FAIL, "Call not received");
				sa.fail();
				test.log(LogStatus.FAIL, "Call not received");

			}		
		}catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->validate_calllog_callreceived()");
			e.printStackTrace();

		}catch (Exception e) {
			test.log(LogStatus.ERROR, "Exeption in ->validate_calllog_callreceived()");

		}
	}
	public void validate_calllog_contactcallreceived(SoftAssert sa){

		try{

			launch_an_app("phone");
			minWait();
			clickBtn(Locators_CallSetting.Callhistorytab);
			minWait();
			String dialedno =Locators_CallSetting.dailedFirsttext.getText();
			System.out.println( "dialedno" );
			if(dialedno.contains("Test Automation")){
				APP_LOGS.info("Call received successfully");
				sa.assertTrue(true, "Call received successfully");
				test.log(LogStatus.PASS, "Call received successfully");


			} else	{
				APP_LOGS.info("Call not received");
				sa.fail();
				test.log(LogStatus.FAIL, "Call not received");

			}		
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->validate_calllog_contactcallreceived()");
			e.printStackTrace();

		}catch (Exception e) {
			test.log(LogStatus.ERROR, "Exeption in ->validate_calllog_contactcallreceived()");

		}
	}
	public void validate_calllog_callnotreceived(SoftAssert sa){
		try{
			launch_an_app("phone");
			minWait();
			clickBtn(Locators_CallSetting.Callhistorytab);
			minWait();
			if(!isElementExist(Locators_CallSetting.dailedFirstNum))
			{
				APP_LOGS.info("Call not received ");
				sa.assertTrue(true, "Call not received");
				test.log(LogStatus.PASS, "Call not received");


			} else	{
				APP_LOGS.info("Call received ");
				sa.fail();
				test.log(LogStatus.FAIL, "Call received ");


			}		
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->validate_calllog_callnotreceived()");
			e.printStackTrace();

		}catch (Exception e) {
			test.log(LogStatus.ERROR, "Exeption in ->validate_calllog_callnotreceived()");

		}
	}








	public void disabling_Screeningincomingcalls_callscreening() throws InterruptedException, IOException{
		try{
			navigateToSettingsAndElement_without_try_catch(Locators_CallSetting.Callscreening);
			minWait();
			checkScreeningIncomingDisabled();
			minWait();
		}
		catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->disabling_Screeningincomingcalls_callscreening()");
			e.printStackTrace();

		}catch (Exception e) {
			test.log(LogStatus.ERROR, "Exeption in ->disabling_Screeningincomingcalls_callscreening()");

		}
	}

	//Screeningincomingcallsetting_option2();

	public void enabling_Screeningincomingcalls_callscreening(String phoneno) throws InterruptedException, IOException{
		try{
			navigateToSettingsAndElement_without_try_catch(Locators_CallSetting.Callscreening);
			minWait();
			checkScreeningIncomingEnabled();
			minWait();
			screeningincomingcallsetting_option2();
			minWait();

		}
		catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->enabling_Screeningincomingcalls_callscreening()");
			e.printStackTrace();

		}catch (Exception e) {
			test.log(LogStatus.ERROR, "Exeption in ->enabling_Screeningincomingcalls_callscreening()");

		}
	}
	public void enabling_Screeningincomingcalls_allowonlycontacts(String phoneno) throws InterruptedException, IOException{
		try{
			navigateToSettingsAndElement_without_try_catch(Locators_CallSetting.Callscreening);
			minWait();
			checkScreeningIncomingEnabled();
			minWait();
			screeningincomingcallsetting_option1();
			minWait();
		}
		catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->enabling_Screeningincomingcalls_allowonlycontacts()");
			e.printStackTrace();

		}catch (Exception e) {
			test.log(LogStatus.ERROR, "Exeption in ->enabling_Screeningincomingcalls_allowonlycontacts()");

		}

	}
	public void enabling_Screeningincomingcalls_allowwhitelist() throws InterruptedException, IOException{
		try{
			navigateToSettingsAndElement_without_try_catch(Locators_CallSetting.Callscreening);
			minWait();
			checkScreeningIncomingEnabled();
			minWait();
			screeningincomingcallsetting_option3();
			minWait();

		}
		catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->enabling_Screeningincomingcalls_allowwhitelist()");
			e.printStackTrace();

		}catch (Exception e) {
			test.log(LogStatus.ERROR, "Exeption in ->enabling_Screeningincomingcalls_allowwhitelist()");

		}

	}
	public void screeningincomingcallsetting_option2() throws InterruptedException{
		//screening incoming call setting block black list option

		clickBtn(Locators_CallSetting.screeningincomingcallsetting);
		minWait();
		clickBtn(Locators_CallSetting.blockblacklistopt);
		minWait();


	}
	public void screeningincomingcallsetting_option3() throws InterruptedException{
		//screening incoming call setting Allow white list option

		clickBtn(Locators_CallSetting.screeningincomingcallsetting);
		minWait();
		clickBtn(Locators_CallSetting.allowwhitelistopt);
		minWait();

	}
	public void screeningincomingcallsetting_option1() throws InterruptedException{
		//screening incoming call setting allow only contacts option

		clickBtn(Locators_CallSetting.screeningincomingcallsetting);
		minWait();
		clickBtn(Locators_CallSetting.allowonlycontactsopt);
		minWait();

	}

	public void unblockno_in_contactapp(String refNum) throws InterruptedException{
		//Blocked and unblocked numbers in phone contact settings
		try{
			clickBtn(Locators_CallSetting.opennavigationdrawer);
			minWait();
			clickBtn(Locators_CallSetting.Settings);
			minWait();
			scrollToText("Blocked numbers");
			minWait();
			if(isElementExist(Locators_CallSetting.deleteblockBtninContact)){
				minWait();
				clickBtn(Locators_CallSetting.deleteblockBtninContact);
				minWait();
				clickBtn(Locators_CallSetting.unblocktxt);
				minWait();
			}
			else{
				clickBackButton_without_try_catch(4);
			}
		}catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->unblockno_in_contactapp()");
			e.printStackTrace();

		}catch (Exception e) {
			test.log(LogStatus.ERROR, "Exeption in ->unblockno_in_contactapp()");

		}

	}
	public void validate_Unblockno_in_blacklist(SoftAssert sa) throws InterruptedException{
		try{
			launch_an_app("phone");
			minWait();
			navigateToSettingsAndElement_without_try_catch(Locators_CallSetting.Callscreening);
			minWait();
			clickBtn(Locators_CallSetting.Manageblacklist);
			minWait();

			if(!isElementExist(Locators_CallSetting.firstblockedno)){
				System.out.println("validate Unblockno in blacklist if condition is working");
				APP_LOGS.info(" UnBlockingno from contacts app is reflected in Black lsit under Call screening");
				sa.assertTrue(true, "UnBlockingno from contacts app is reflected in Black lsit under Call screening");
				test.log(LogStatus.PASS, "UnBlockingno from contacts app is reflected in Black lsit under Call screening");

			}else {
				System.out.println("validate Unblockno in blacklist else condition is working");
				APP_LOGS.info("UnBlockingno from contacts app is not reflected in Black lsit under Call screening");
				sa.fail();
				test.log(LogStatus.FAIL, "UnBlockingno from contacts app is not reflected in Black lsit under Call screening");


			}
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->validate_Unblockno_in_blacklist()");
			e.printStackTrace();

		}catch (Exception e) {
			test.log(LogStatus.ERROR, "Exeption in ->validate_Unblockno_in_blacklist()");

		}
	}
	public void validate_Unblockno_in_blacklist_vzw(SoftAssert sa) throws InterruptedException{
		try{
			launch_an_app("phone");
			minWait();
			navigateToSettingsAndElement(Locators_CallSetting.vzwblockedno);

			if(!isElementExist(Locators_CallSetting.firstblockedno)){
				System.out.println("validate Unblockno in blacklist if condition is working");
				APP_LOGS.info(" UnBlockingno from contacts app is reflected in Black lsit under Call screening");
				sa.assertTrue(true, "UnBlockingno from contacts app is reflected in Black lsit under Call screening");
				test.log(LogStatus.PASS, "UnBlockingno from contacts app is reflected in Black lsit under Call screening");

			}else {
				System.out.println("validate Unblockno in blacklist else condition is working");
				APP_LOGS.info("UnBlockingno from contacts app is not reflected in Black lsit under Call screening");
				sa.fail();
				test.log(LogStatus.FAIL, "UnBlockingno from contacts app is not reflected in Black lsit under Call screening");


			}
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->validate_Unblockno_in_blacklist()");
			e.printStackTrace();

		}catch (Exception e) {
			test.log(LogStatus.ERROR, "Exeption in ->validate_Unblockno_in_blacklist()");

		}
	}
	public void clearSMSPermissions() throws InterruptedException {
		try {	
			customWait(2000);
			//				clickBtn(Locators_CallSetting.StartMessaging);
			minWait();
			clickBtn(Locators_CallSetting.NEXT_Msg);
			minWait();
			clickBtn(Locators_CallSetting.allow_Permission);
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


	public void navigateTo_NewSMS_O() throws InterruptedException {
		/* Used to Navigate to New Message Page. */
		try {
			clickBtn(Locators_CallSetting.add_NewSMS_O);
			minWait();
		} catch (Exception e) {		 
			e.printStackTrace();
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
			if(isElementExist(Locators_CallSetting.messageField_O)) {
				enterTextToInputField(Locators_CallSetting.messageField_O, message);
				minWait();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}public void enter_Num_ToField_O(String number) throws InterruptedException {
		/* Method is used to Enter Number into TO Field. */
		try {
			minWait();
			enterTextToInputField(Locators_CallSetting.TO_Field_O, number);
			customWait(2000);
			System.out.println("Selecting COntact NAme");
			clickBtn(Locators_CallSetting.contact_Select);
			//			customWait(2000);			
		} catch (Exception e) {			 
			e.printStackTrace();
		}
	}


	public void clickOn_Send_O() throws InterruptedException { 
		/* Method used to click on Send Button. */
		try {
			customWait(500);
			clickBtn(Locators_CallSetting.send_Icon_O);
			minWait();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void validate_SentMessage_O(SoftAssert soft) throws InterruptedException {
		/* To validate the Sent Message. */
		WebDriverWait wait  =new WebDriverWait(aDriver, 60);

		try {
			if (p_b_No.contains("-10.")||p_b_No.contains("-00.")) {
				wait.until(ExpectedConditions.visibilityOf(Locators_CallSetting.Delivered));
				if(isElementExist(Locators_CallSetting.Delivered)||isElementExist(Locators_CallSetting.not_Sent_Text)) {
					APP_LOGS.info("Message sent Succeccfully");
					System.out.println("Sent");
					soft.assertTrue(check, "TestCase Valiation is PASS");
					test.log(LogStatus.PASS, "SMS Sent Successfully at iteration ");
				} else {
					APP_LOGS.info("SMS didn't sent");
					soft.fail();
					test.log(LogStatus.FAIL,"Message didn't sent at iteration ");
				}
			}
			else if (p_b_No.contains("-11.")) {
				if(isElementExist(Locators_CallSetting.bell_first_sms)) {
					clickBtn(Locators_CallSetting.bell_first_sms);
					APP_LOGS.info("Message sent Succeccfully");
					System.out.println("Sent");
					soft.assertTrue(check, "TestCase Valiation is PASS");
					test.log(LogStatus.PASS, "SMS Sent Successfully at iteration ");
				} else {
					APP_LOGS.info("SMS didn't sent");
					soft.fail();
					test.log(LogStatus.FAIL,"Message didn't sent at iteration ");
				}
			}
		}	catch (Exception e) {			 
			e.printStackTrace();
			soft.fail();
		}
	}
	public void validate_NotSentMessage(SoftAssert sa) throws InterruptedException {
		////Verify  messages is not received from black list on selecting 'Block black list"
		try{
			if (p_b_No.contains("-10.")||p_b_No.contains("-00.")) {
				launch_an_app("messaging");
				if(!isElementExist(Locators_CallSetting.smsfirstmsg)) {
					APP_LOGS.info("Message not sent");
					System.out.println("Not Sent");
					sa.assertTrue(true, "Message not sent");
					test.log(LogStatus.PASS, "Message not sent ");
				} else {
					APP_LOGS.info("Message sent");
					sa.fail();
					test.log(LogStatus.FAIL,"Message  sent ");
				}
			}
			else if (p_b_No.contains("-11.")) {
				launch_APP(Locators_CallSetting.Bell_Messages);
				if(!isElementExist(Locators_CallSetting.bell_first_sms)) {
					APP_LOGS.info("Message not sent");
					System.out.println("Not Sent");
					sa.assertTrue(true, "Message not sent");
					test.log(LogStatus.PASS, "Message not sent ");
				} else {
					APP_LOGS.info("Message sent");
					sa.fail();
					test.log(LogStatus.FAIL,"Message  sent ");
				}

			}
		}catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.ERROR, "Error in the locators-> validate_NotSentMessage()");
			e.printStackTrace();

		}catch (Exception e) {
			test.log(LogStatus.ERROR, "Exeption in -> validate_NotSentMessage()");

		}
	}
	public void validate_SentMessage(SoftAssert sa) throws InterruptedException {
		////Verify  messages is not received from black list on selecting 'Block black list"
		try{
			if(isElementExist(Locators_CallSetting.smsfirstmsg)) {
				APP_LOGS.info("Message  sent");
				System.out.println("Sent");
				sa.assertTrue(true, "");
				test.log(LogStatus.PASS, "Message  sent ");
			} else {
				APP_LOGS.info("Message not sent");
				sa.fail();
				test.log(LogStatus.FAIL,"Message not sent ");
			}
		} catch (Exception e) {			 
			e.printStackTrace();
			test.log(LogStatus.INFO, "Validate sentmessage is failed");
		}
	}


	public void delete_SMS(SoftAssert sa) throws InterruptedException {
		/* This Method delete the First Thread in the List. */

		try {
			System.out.println("IN Delete");
			minWait();
			if (p_b_No.contains("-10.")||p_b_No.contains("-00.")) {
				launch_an_app("messaging");
				minWait();
				if (isElementExist(Locators_CallSetting.firstSMS_InList)) {

					TouchAction action = new TouchAction(aDriver);
					action.longPress(Locators_CallSetting.firstSMS_InList).release().perform();
					minWait();
					clickBtn(Locators_CallSetting.smsmoreOptions);
					minWait();
					if(isElementExist(Locators_CallSetting.smsselectall)){
						minWait();
						clickBtn(Locators_CallSetting.smsselectall);
						minWait();
						clickBtn(Locators_CallSetting.smsdeleteall);
						minWait();
						clickBtn(Locators_CallSetting.smsconfirmdelete);
						minWait();
					}
					else{
						clickBackButton_without_try_catch(2);
						minWait();
						launch_an_app("messaging");
						minWait();
						action.longPress(Locators_CallSetting.firstSMS_InList).release().perform();
						minWait();
						clickBtn(Locators_CallSetting.smsdeleteall);
						minWait();
						clickBtn(Locators_CallSetting.smsconfirmdelete);
						minWait();
						sa.assertTrue(true, "SMS is deleted");
						test.log(LogStatus.PASS, "SMS is deleted");
					}
				} else{

					clickBackButton_without_try_catch(4);
					APP_LOGS.info("SMS is deleted");
					sa.assertTrue(true, "");
					test.log(LogStatus.PASS, "SMS is deleted");
				}
			}
			else if(p_b_No.contains("-11.")||p_b_No.contains("-29.")) {
				launch_APP(Locators_CallSetting.Bell_Messages);
				while(true){
					customWait(2000);

					if (isElementExist(Locators_CallSetting.bell_first_sms)) {
						TouchAction action = new TouchAction(aDriver);
						action.longPress(Locators_CallSetting.bell_first_sms).release().perform();
						minWait();
						clickBtn(Locators_CallSetting.deletebell_first_sms);
						clickBtn(Locators_CallSetting.cnfrmdeletebell_first_sms);
					}else{
						break;
					}
				}




			}


		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators-> delete_SMS()");
			e.printStackTrace();

		}catch (Exception e) {
			test.log(LogStatus.ERROR, "Exeption in -> delete_SMS()");

		}
	}
	public void create_NewSMS_SL(String number, String message) throws InterruptedException, IOException {
		/* Method used to create New SMS. */

		try {
			clickBtn(Locators_CallSetting.Bell_newconversation);
			minWait();
			enterTextToInputField( Locators_CallSetting.tofield_text_sl,number);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.ENTER);
			minWait();
			System.out.println("Enter Text");
			customWait(2000);
			enterTextToInputField(multi_Loc_Strategy(Locators_CallSetting.typemsg_sl, Locators_CallSetting.typemsg_sl_indx, Locators_CallSetting.typemsg_text_sl, Locators_CallSetting.typemsg_xpath_sl,Locators_CallSetting.typemsgcls_sl, 1268, 343), message);
			minWait();
			clickBtn(multi_Loc_Strategy(Locators_CallSetting.sendmsg_sl, Locators_CallSetting.sendmsg_sl_indx, Locators_CallSetting.sendmsg_text_sl, Locators_CallSetting.sendmsgcls_sl, Locators_CallSetting.sendmsgtxt_sl, 1011, 1127));


		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR,"Error in locators-> create_NewSMS_SL()");
		}catch (Exception e) {
			test.log(LogStatus.ERROR,"Exeption in -> create_NewSMS_SL()");
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
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void clickOnAppList() throws InterruptedException {

		try {
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_HOME);			
			customWait(2000);			
			clickBtn(Locators_CallSetting.app_List);
			minWait();
		} catch (Exception e) {
			e.printStackTrace();
		}

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
	public void navigateTo_NewSMS() throws InterruptedException {
		/* Used to Navigate to New Message Page. */
		try {
			clickBtn(Locators_CallSetting.add_NewSMS);
			minWait();
		} catch (Exception e) {		 
			e.printStackTrace();
		}
	}
	public void enter_Num_ToField(String number) throws InterruptedException {
		/* Method is used to Enter Number into TO Field. */
		try {
			customWait(2000);
			enterTextToInputField(Locators_CallSetting.TO_Field, number);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			minWait();
		} catch (Exception e) {			 
			e.printStackTrace();
		}
	}
	public void enterText_MessageField(String message) throws InterruptedException {
		/* Methods is Used enter Message into Message Field. */
		try {
			customWait(1000);
			enterTextToInputField(Locators_CallSetting.messageField, message);
			minWait();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void clickOn_Send() throws InterruptedException { 
		/* Method used to click on Send Button. */
		try {
			minWait();
			if(isElementExist(Locators_CallSetting.send_Icon)) {
				clickBtn(Locators_CallSetting.send_Icon);
				APP_LOGS.info("Send icon");
				minWait();
			}
			else if(isElementExist(Locators_CallSetting.send_SMS)) {
				clickBtn(Locators_CallSetting.send_SMS);
				APP_LOGS.info("Send icon");
				minWait();
			}
			else {
				clickBtn(Locators_CallSetting.send_MMS_Icon);
				minWait();
			}

		} catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "Error in Entering text meassage.");
		}
	}
	public void sendSMS_fromRefDevice(String AutomationMessagee) {

		// To validate MT Message User should be inside Messaging APP of Primary Device.
		try {
			minWait();
			// Below Code To clear Battery PopUp.
			//			Runtime.getRuntime().exec("adb -s "+r_Id+" shell input tap 777 1083"); 
			minWait();
			if (r_b_No.contains("8A.")) {
				if (r_b_No.contains("-10.")||r_b_No.contains("-30.")) {

					minWait();
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

					minWait();
				} else if(r_b_No.contains("-11.")||r_b_No.contains("-12.")||r_b_No.contains("-18.")||r_b_No.contains("-26.")||r_b_No.contains("-29.")){
					minWait();
					Runtime.getRuntime().exec("adb -s "+r_Id+" shell input keyevent 3");
					minWait();
					Runtime.getRuntime().exec("adb -s "+r_Id+" shell am force-stop com.google.android.apps.messaging");
					customWait(2000);
					Runtime.getRuntime().exec("adb -s "+r_Id+" shell am start -a android.intent.action.SENDTO -d sms:"+pryNum+" --es sms_body \""+AutomationMessagee+"\" --ez exit_on_sent true");
					customWait(6000);
					Runtime.getRuntime().exec("adb -s "+r_Id+" shell input tap 930 1756");
					minWait();
					Runtime.getRuntime().exec("adb -s "+r_Id+" shell input tap 930 976");
					minWait();

				}else if(r_b_No.contains("-15.")){
					minWait();
					System.out.println("IN Android O");
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
					minWait();
				}
			} else if (r_b_No.contains("5SA.")){
				minWait();
				Runtime.getRuntime().exec("adb -s "+r_Id+" shell input keyevent 3");
				minWait();
				Runtime.getRuntime().exec("adb -s "+r_Id+" shell am force-stop com.android.mms");
				customWait(2000);
				Runtime.getRuntime().exec("adb -s "+r_Id+" shell am start -a android.intent.action.SENDTO -d sms:"+pryNum+" --es sms_body \""+AutomationMessagee+"\" --ez exit_on_sent true");
				customWait(6000);
				Runtime.getRuntime().exec("adb -s "+r_Id+" shell input keyevent 66");
				minWait();
			}
		} catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "exeption in sendSMS_fromRefDevice() method");


		}
	}

	public void validate_RecievedMessage() throws InterruptedException {
		/* User should navigate to the Messaging APP home Page in the Primary device. */
		WebDriverWait wait  =new WebDriverWait(aDriver, 80);

		try {
			wait.until(ExpectedConditions.visibilityOf(Locators_CallSetting.now_Text));	
			customWait(8000);
			if(isElementExist(Locators_CallSetting.now_Text)||isElementExist(Locators_CallSetting.not_Sent_Text)) {
				check=true;
				APP_LOGS.info("Message sent Succeccfully");
				//soft.assertTrue(check, "TestCase Valiation is PASS");
				test.log(LogStatus.PASS, "Message Recieved Successfully at iteration : ");
			} else {
				APP_LOGS.info("SMS didn't sent");
				test.log(LogStatus.PASS, "Message didn't Recieved at iteration : ");
				//soft.fail();
			}
		} catch (Exception e) {			 
			e.printStackTrace();
			//soft.fail();
		}
	}

	public void blockno_in_Calldetails() throws InterruptedException
	{
		//Blocking no from call details

		try {
			launch_an_app("phone");
			minWait();
			clickBtn(Locators_CallSetting.Callhistorytab);
			minWait();
			clickBtn(Locators_CallSetting.dialerfirstno);
			minWait();

			if(isElementExist(Locators_CallSetting.blockNumberOpt)){
				clickBtn(Locators_CallSetting.blockNumberOpt);
				clickBtn(Locators_CallSetting.blockBtn);
				minWait();

			}else{
				clickBackButton_without_try_catch(4);
			}

		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->blockno_in_Calldetails()");
			e.printStackTrace();

		}catch (Exception e) {
			test.log(LogStatus.ERROR, "Exeption in ->blockno_in_Calldetails()");

		}
	}
	public void blockno_in_Calldetails_vzw() throws InterruptedException
	{
		//Blocking no from call details

		try {
			launch_an_app("phone");
			minWait();
			clickBtn(Locators_CallSetting.vzw_recent);
			minWait();
			clickBtn(Locators_CallSetting.vzwdailedFirstNum);
			minWait();

			if(isElementExist(Locators_CallSetting.vzwblockNumberOpt)){
				clickBtn(Locators_CallSetting.vzwblockNumberOpt);
				clickBtn(Locators_CallSetting.blockBtn);
				minWait();

			}else{
				clickBackButton_without_try_catch(4);
			}

		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->blockno_in_Calldetails_vzw()");
			e.printStackTrace();

		}catch (Exception e) {
			test.log(LogStatus.ERROR, "Exeption in ->blockno_in_Calldetails_vzw()");

		}
	}

	public void Unblockno_in_calldetails(){
		//UnBlocking no from call details

		try {

			launch_an_app("phone");
			minWait();
			clickBtn(Locators_CallSetting.Callhistorytab);
			minWait();
			clickBtn(Locators_CallSetting.dialerfirstno);
			minWait();
			if(isElementExist(Locators_CallSetting.unblockNumberOpt)){
				clickBtn(Locators_CallSetting.unblockNumberOpt);
				clickBtn(Locators_CallSetting.unblockBtn);
				minWait();

			}else{
				clickBackButton_without_try_catch(4);
			}
		}catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->Unblockno_in_calldetails()");
			e.printStackTrace();

		}catch (Exception e) {
			test.log(LogStatus.ERROR, "Exeption in ->Unblockno_in_calldetails()");

		}

	}
	public void Verizon_Unblockno_in_calldetails(){
		//UnBlocking no from call details

		try {

			launch_an_app("phone");
			minWait();
			clickBtn(Locators_CallSetting.vzw_recent);
			minWait();
			System.out.println("dialed first no");
			clickBtn(Locators_CallSetting.vzwdailedFirstNum);
			minWait();
			System.out.println("block no option");
			if(isElementExist(Locators_CallSetting.vzwunblockNumberOpt)){
				customWait(5000);
				System.out.println("inside");
				clickBtn(Locators_CallSetting.vzwunblockNumberOpt);
				minWait();
				System.out.println("out");
				clickBtn(Locators_CallSetting.unblockBtn);
				minWait();

			}else{
				clickBackButton_without_try_catch(4);
			}
		}catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->Unblockno_in_calldetails_vzw()");
			e.printStackTrace();

		}catch (Exception e) {
			test.log(LogStatus.ERROR, "Exeption in ->Unblockno_in_calldetails_vzw()");

		}

	}
	public void validateBlockAndUnblockNumber(AndroidElement element,String status) throws InterruptedException
	{
		/*
		 * Validates Block And Unblock number
		 */
		try {
			clickBackButton(1);
			minWait();
			clickBtn(Locators_CallSetting.callDetailsOpt);
			minWait();
			if(isElementExist(element)){
				check = true;
				APP_LOGS.info("Number "+ status +" Successfully");
				test.log(LogStatus.INFO, "Number "+ status +" Successfully");

			}else{
				APP_LOGS.info("Number not " +status);
				test.log(LogStatus.ERROR,"Number not " +status);

			}
		} catch (Exception e) {
			e.printStackTrace();
			APP_LOGS.info("Element Not Found");
			test.log(LogStatus.ERROR, "Element Not Found");

		}

	}

	public void validate_canceloptionin_calldetailsblockno(SoftAssert sa) throws InterruptedException{
		//cancel option in call details block no

		try{
			System.out.println("cancel block no" );
			if(isElementExist(Locators_CallSetting.blockNumberOpt)){
				clickBtn(Locators_CallSetting.blockNumberOpt);
				minWait();
				clickBtn(Locators_CallSetting.cancelBtn);
				minWait();
				navigateToSettingsAndElement_without_try_catch(Locators_CallSetting.Callscreening);
				minWait();
				clickBtn(Locators_CallSetting.Manageblacklist);
				minWait();
				if(!isElementExist(Locators_CallSetting.firstblockedno)){
					System.out.println("validate_canceloptionin_calldetailsblockno if condition is working");
					APP_LOGS.info(" Cancel option from contacts app is reflected in Black lsit under Call screening");
					sa.assertTrue(true, "Cancel option from contacts app is reflected in Black lsit under Call screening");
					test.log(LogStatus.PASS, "Cancel option from contacts app is reflected in Black lsit under Call screening");

				}
			}
			else if(isElementExist(Locators_CallSetting.unblockNumberOpt)) {
				clickBtn(Locators_CallSetting.unblockNumberOpt);
				minWait();
				clickBtn(Locators_CallSetting.unblockBtn);
				minWait();
				clickBtn(Locators_CallSetting.blockNumberOpt);
				minWait();
				clickBtn(Locators_CallSetting.cancelBtn);
				minWait();
				navigateToSettingsAndElement_without_try_catch(Locators_CallSetting.Callscreening);
				minWait();
				clickBtn(Locators_CallSetting.Manageblacklist);
				minWait();
				if(!isElementExist(Locators_CallSetting.firstblockedno)){
					System.out.println("validate_canceloptionin_calldetailsblockno else if condition is working");
					APP_LOGS.info(" Cancel option from contacts app is reflected in Black lsit under Call screening");
					sa.assertTrue(true, "Cancel option from contacts app is reflected in Black lsit under Call screening");
					test.log(LogStatus.PASS, "Cancel option from contacts app is reflected in Black lsit under Call screening");
				}
			}

			else{
				System.out.println("validate_canceloptionin_calldetailsblockno else condition is working");
				APP_LOGS.info("Cancel option is not verified in call details block no");
				sa.fail();
				test.log(LogStatus.FAIL, "Cancel option is not verified in call details block no");
				clickBackButton_without_try_catch(4);
			}
		}
		catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->validate_canceloptionin_calldetailsblockno()");
			e.printStackTrace();

		}catch (Exception e) {
			test.log(LogStatus.ERROR, "Exeption in ->validate_canceloptionin_calldetailsblockno()");

		}
	}
	public void validate_canceloptionin_calldetailsblockno_vzw(SoftAssert sa) throws InterruptedException{
		//cancel option in call details block no

		try{
			System.out.println("cancel block no" );
			clickBtn(Locators_CallSetting.vzwdailedFirstNum);
			customWait(8000);
			if(isElementExist(Locators_CallSetting.vzwblockNumberOpt)){
				clickBtn(Locators_CallSetting.vzwblockNumberOpt);
				minWait();
				clickBtn(Locators_CallSetting.cancelBtn);
				minWait();
				navigateToSettingsAndElement_without_try_catch(Locators_CallSetting.vzwblockedno);
				minWait();
				if(!isElementExist(Locators_CallSetting.firstblockedno)){
					System.out.println("validate_canceloptionin_calldetailsblockno if condition is working");
					APP_LOGS.info(" Cancel option from contacts app is reflected in Black lsit under Call screening");
					sa.assertTrue(true, "Cancel option from contacts app is reflected in Black lsit under Call screening");
					test.log(LogStatus.PASS, "Cancel option from contacts app is reflected in Black lsit under Call screening");

				}
			}
			else if(isElementExist(Locators_CallSetting.unblockNumberOpt)) {
				clickBtn(Locators_CallSetting.unblockNumberOpt);
				minWait();
				clickBtn(Locators_CallSetting.unblockBtn);
				minWait();
				clickBtn(Locators_CallSetting.vzwblockNumberOpt);
				minWait();
				clickBtn(Locators_CallSetting.cancelBtn);
				minWait();
				navigateToSettingsAndElement_without_try_catch(Locators_CallSetting.vzwblockedno);
				minWait();
				if(!isElementExist(Locators_CallSetting.firstblockedno)){
					System.out.println("validate_canceloptionin_calldetailsblockno else if condition is working");
					APP_LOGS.info(" Cancel option from contacts app is reflected in Black lsit under Call screening");
					sa.assertTrue(true, "Cancel option from contacts app is reflected in Black lsit under Call screening");
					test.log(LogStatus.PASS, "Cancel option from contacts app is reflected in Black lsit under Call screening");
				}
			}

			else{
				System.out.println("validate_canceloptionin_calldetailsblockno else condition is working");
				APP_LOGS.info("Cancel option is not verified in call details block no");
				sa.fail();
				test.log(LogStatus.FAIL, "Cancel option is not verified in call details block no");
				clickBackButton_without_try_catch(4);
			}
		}
		catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->validate_canceloptionin_calldetailsblockno_vzw()");
			e.printStackTrace();

		}catch (Exception e) {
			test.log(LogStatus.ERROR, "Exeption in ->validate_canceloptionin_calldetailsblockno_vzw()");

		}
	}



	public void validate_canceloptionin_calldetailsUnblockno(SoftAssert sa) throws InterruptedException{
		//cancel option in call details Unblock no
		try{
			System.out.println("coming inside");

			if(isElementExist(Locators_CallSetting.unblockNumberOpt)){
				clickBtn(Locators_CallSetting.unblockNumberOpt);
				minWait();
				clickBtn(Locators_CallSetting.cancelBtn);
				minWait();
				navigateToSettingsAndElement_without_try_catch(Locators_CallSetting.Callscreening);
				minWait();
				clickBtn(Locators_CallSetting.Manageblacklist);
				minWait();
				if(isElementExist(Locators_CallSetting.firstblockedno)){
					System.out.println("validate_canceloptionin_calldetailsUnblockno if condition is working");
					APP_LOGS.info(" UnblockCancel option from contacts app is reflected in Black lsit under Call screening");
					sa.assertTrue(true, "UnblockCancel option from contacts app is reflected in Black lsit under Call screening");
					test.log(LogStatus.PASS, "UnblockCancel option from contacts app is reflected in Black lsit under Call screening");
					System.out.println("going out");
				}
			}else if(isElementExist(Locators_CallSetting.blockNumberOpt)){
				System.out.println("else if block no");
				clickBtn(Locators_CallSetting.blockNumberOpt);
				minWait();
				clickBtn(Locators_CallSetting.blockBtn);
				minWait();
				clickBtn(Locators_CallSetting.unblockNumberOpt);
				minWait();
				clickBtn(Locators_CallSetting.cancelBtn);
				minWait();
				navigateToSettingsAndElement_without_try_catch(Locators_CallSetting.Callscreening);
				minWait();
				clickBtn(Locators_CallSetting.Manageblacklist);
				minWait();
				if(isElementExist(Locators_CallSetting.firstblockedno)){
					System.out.println("validate_canceloptionin_calldetailsUnblockno elseif condition is working");
					APP_LOGS.info(" Unblock Cancel option from contacts app is reflected in Black lsit under Call screening");
					sa.assertTrue(true, "Unblock Cancel option from contacts app is reflected in Black lsit under Call screening");
					test.log(LogStatus.PASS, " Unblock Cancel option from contacts app is reflected in Black lsit under Call screening");
					System.out.println("going out");
				}
			}else{
				System.out.println("validate_canceloptionin_calldetailsUnblockno else cancel unblock no");
				APP_LOGS.info("Cancel option is not verified in call details unblock no");
				sa.fail();
				test.log(LogStatus.FAIL, "Cancel option is  not verified in call details Unblock no");
				clickBackButton_without_try_catch(4);


			}}

		catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->validate_canceloptionin_calldetailsUnblockno()");
			e.printStackTrace();

		}catch (Exception e) {
			test.log(LogStatus.ERROR, "Exeption in ->validate_canceloptionin_calldetailsUnblockno()");

		}

	}
	public void validate_canceloptionin_calldetailsUnblockno_vzw(SoftAssert sa) throws InterruptedException{
		//cancel option in call details Unblock no
		try{
			System.out.println("coming inside");
			clickBtn(Locators_CallSetting.vzwdailedFirstNum);
			customWait(2000);
     if(isElementExist(Locators_CallSetting.vzwunblockNumberOpt2)){
				clickBtn(Locators_CallSetting.vzwunblockNumberOpt);
				minWait();
				clickBtn(Locators_CallSetting.cancelBtn);
				minWait();
				navigateToSettingsAndElement_without_try_catch(Locators_CallSetting.vzwblockedno);
				minWait();
				if(isElementExist(Locators_CallSetting.firstblockedno)){
					System.out.println("validate_canceloptionin_calldetailsUnblockno if condition is working");
					APP_LOGS.info(" UnblockCancel option from contacts app is reflected in Black lsit under Call screening");
					sa.assertTrue(true, "UnblockCancel option from contacts app is reflected in Black lsit under Call screening");
					test.log(LogStatus.PASS, "UnblockCancel option from contacts app is reflected in Black lsit under Call screening");
					System.out.println("going out");
				}
			}else if(isElementExist(Locators_CallSetting.vzwblockNumberOpt)){
				System.out.println("else if block no");
				clickBtn(Locators_CallSetting.vzwblockNumberOpt);
				minWait();
				clickBtn(Locators_CallSetting.blockBtn);
				minWait();
				clickBtn(Locators_CallSetting.unblockNumberOpt);
				minWait();
				clickBtn(Locators_CallSetting.cancelBtn);
				minWait();
				navigateToSettingsAndElement_without_try_catch(Locators_CallSetting.vzwblockedno);
				minWait();
				if(isElementExist(Locators_CallSetting.firstblockedno)){
					System.out.println("validate_canceloptionin_calldetailsUnblockno elseif condition is working");
					APP_LOGS.info(" Unblock Cancel option from contacts app is reflected in Black lsit under Call screening");
					sa.assertTrue(true, "Unblock Cancel option from contacts app is reflected in Black lsit under Call screening");
					test.log(LogStatus.PASS, " Unblock Cancel option from contacts app is reflected in Black lsit under Call screening");
					System.out.println("going out");
				}
			}else{
				System.out.println("validate_canceloptionin_calldetailsUnblockno else cancel unblock no");
				APP_LOGS.info("Cancel option is not verified in call details unblock no");
				sa.fail();
				test.log(LogStatus.FAIL, "Cancel option is  not verified in call details Unblock no");
				clickBackButton_without_try_catch(4);


			}}

		catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->validate_canceloptionin_calldetailsUnblockno_vzw()");
			e.printStackTrace();

		}catch (Exception e) {
			test.log(LogStatus.ERROR, "Exeption in ->validate_canceloptionin_calldetailsUnblockno_vzw()");

		}

	}
	public void validation_Screeningoutgoingcall_enabled(SoftAssert sa) throws InterruptedException{
		//Verify enabling Screening outgoing calls

		try {
			navigateToSettingsAndElement(Locators_CallSetting.Callscreening);
			minWait();
			checkScreeningOutgoingEnabled();
			minWait();
			if(!Locators_CallSetting.screeningoutgoingcall.isEnabled()){
				clickBtn(Locators_CallSetting.screeningoutgoingcall);
				minWait();
				System.out.println("Enabled");
				APP_LOGS.info( "Screening outgoing call enabled ");
				sa.assertTrue(true, "Screening outgoing call enabled ");
				test.log(LogStatus.PASS, "Screening outgoing call enabled ");
			}
			else{
				System.out.println("Disabled");
				APP_LOGS.info("Validating Screening outgoing call disabled");
				sa.fail();
				test.log(LogStatus.FAIL, "Validating Screening outgoing call disabled");
			}
		}
		catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->validation_Screeningoutgoingcall_enabled()");
			e.printStackTrace();

		}catch (Exception e) {
			test.log(LogStatus.ERROR, "Exeption in ->validation_Screeningoutgoingcall_enabled()");

		}

	}
	public void validation_Screeningoutgoingcall_disabled(SoftAssert sa) throws InterruptedException{
		//Verify disabling Screening outgoing calls

		try {
			launch_an_app("phone");
			minWait();
			navigateToSettingsAndElement_without_try_catch(Locators_CallSetting.Callscreening);
			minWait();
			checkScreeningOutgoingDisabled();
			minWait();
			if(!Locators_CallSetting.screeningoutgoingcall.isEnabled()){
				clickBtn(Locators_CallSetting.screeningoutgoingcall);
				minWait();
				System.out.println("Disabled");
				APP_LOGS.info( "Screening outgoing call disabled ");
				sa.assertTrue(true, "Screening outgoing call disabled");
				test.log(LogStatus.PASS, "Screening outgoing call disabled ");
			}
			else{
				System.out.println("Enabled");
				APP_LOGS.info("Validating Screening outgoing call enabled");
				sa.fail();
				test.log(LogStatus.FAIL, "Validating Screening outgoing call  enabled");
			}
		}
		catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->validation_Screeningoutgoingcall_disabled()");
			e.printStackTrace();

		}catch (Exception e) {
			test.log(LogStatus.ERROR, "Exeption in ->validation_Screeningoutgoingcall_disabled()");

		}


	}
	public void setDefaultSavingAccount() throws InterruptedException
	{
		/*
		 * Set default saving account as phone in contacts application
		 */
		try {
			minWait();
			clickBtn(Locators_CallSetting.ContactsMoreOptions);
			minWait();
			clickBtn(Locators_CallSetting.contactsSettingsOPt);
			minWait();
			clickBtn(Locators_CallSetting.contactsDefaultAccountSettings);
			minWait();
			clickBtn(Locators_CallSetting.contactsDefaultPhone);
			minWait();
			clickBackButton_without_try_catch(1);

		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->setDefaultSavingAccount()");
			e.printStackTrace();

		}
		catch (Exception e) {
			test.log(LogStatus.ERROR, "Exception in setDefaultSavingAccount()");
		}
	}
	public void validation_initiatingcall_Screeningoutgoingcall_enabled(String name,String lastname,String refNum,SoftAssert sa) throws InterruptedException{
		//Verify initiating calls to saved contacts on enabling Screening outgoing calls


		try {
			launch_an_app("phone");
			navigateToSettingsAndElement_without_try_catch(Locators_CallSetting.Callscreening);
			minWait();
			checkScreeningOutgoingEnabled();
			minWait();
			APP_LOGS.info("Initiating calls to saved contacts on enabling Screening outgoing calls is verified");
			sa.assertTrue(true, "Initiating calls to saved contacts on enabling Screening outgoing calls is verified");
			test.log(LogStatus.PASS, "Initiating calls to saved contacts on enabling Screening outgoing calls is verified");		

		}

		catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->validate_Editedquickresponse_displayproperly_duringcallscreen()");
			e.printStackTrace();

		}catch (Exception e) {
			test.log(LogStatus.ERROR, "Exeption in ->validate_Editedquickresponse_displayproperly_duringcallscreen()");

		}


	}

	public void add_contact_for_callscreening(String name,String lastname,String refNum) throws InterruptedException{
		{
			try{

				launch_an_app("phone");
				AddContactFromContactsPage(name,lastname,refNum);
				clickBackButton(4);
				minWait();
			}catch (org.openqa.selenium.NoSuchElementException e) {

				test.log(LogStatus.ERROR, "Error in the locators->add_contact_for_callscreening()");
				e.printStackTrace();

			}catch (Exception e) {
				test.log(LogStatus.ERROR, "Exeption in ->add_contact_for_callscreening()");

			}
		}
	}
	public void Editedquickresponse_displayproperly_duringcallscreen() throws InterruptedException{
		//To verify user edited quick response should display properly on the screen while sending during a call screen
		clickBackButton(4);
		minWait();
		try {
			make_Call_from_RefDev();
			customWait(11000);
			Runtime.getRuntime().exec("adb -s "+p_Id+" shell input tap 547 182");
			customWait(5000);
			aDriver.swipe(80, 1840, 300, 600, 750);
			minWait();
			clickBtn(Locators_CallSetting.quickresponseedittxt);
			minWait();
			endCall_RefDevice();
			minWait();
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->Editedquickresponse_displayproperly_duringcallscreen()");
			e.printStackTrace();

		}catch (Exception e) {
			test.log(LogStatus.ERROR, "Exeption in ->Editedquickresponse_displayproperly_duringcallscreen()");

		}

	}

	public void validate_Editedquickresponse_displayproperly_duringcallscreen(SoftAssert sa) throws InterruptedException{
		try{
			if (p_b_No.contains("-10.")||p_b_No.contains("-00.")) {
				customWait(10000);
				launch_an_app("messaging");
				customWait(10000);

				if(isElementExist(Locators_CallSetting.quickresponsemsg)){
					clickBtn(Locators_CallSetting.quickresponsemsg);
					minWait();
					System.out.println("message sent");
					APP_LOGS.info( "Quick response message sent ");
					sa.assertTrue(true, "Quick response message sent");
					test.log(LogStatus.PASS, "Quick response message sent ");
				}
				else{
					System.out.println("message not sent");
					APP_LOGS.info("Quick response message not sent");
					sa.fail();
					test.log(LogStatus.FAIL, "Quick response message not sent");
				}

			}else if (p_b_No.contains("-11.")||p_b_No.contains("-29.")||p_b_No.contains("-26.")) {
				customWait(10000);
				launch_APP(Locators_CallSetting.Bell_Messages);
				customWait(10000);
				if(isElementExist(Locators_CallSetting.bell_first_sms)){
					clickBtn(Locators_CallSetting.bell_first_sms);
					minWait();
					System.out.println("message sent");
					APP_LOGS.info( "Quick response message sent ");
					sa.assertTrue(true, "Quick response message sent");
					test.log(LogStatus.PASS, "Quick response message sent ");
				}else{
					System.out.println("message not sent");
					APP_LOGS.info("Quick response message not sent");
					sa.fail();
					test.log(LogStatus.FAIL, "Quick response message not sent");
				}
			}
				else if (p_b_No.contains("-15.")) {
					customWait(10000);
					launch_APP(Locators_CallSetting.MessagePlus);
					if(isElementExist(Locators_CallSetting.vzw_Start_msg))
					{
						Runtime.getRuntime().exec("adb -s "+p_Id+" shell pm clear com.verizon.messaging.vzmsgs");
						launch_APP(Locators_CallSetting.MessagePlus);
						clickBtn(Locators_CallSetting.vzw_Start_msg);
						clickBtn(Locators_CallSetting.vzw_msg_skipprovisioing);
						if(isElementExist(Locators_CallSetting.VZW_first_sms)){
							clickBtn(Locators_CallSetting.VZW_first_sms);
							minWait();
							System.out.println("message sent");
							APP_LOGS.info( "Quick response message sent ");
							sa.assertTrue(true, "Quick response message sent");
							test.log(LogStatus.PASS, "Quick response message sent ");
						}else{
							System.out.println("message not sent");
							APP_LOGS.info("Quick response message not sent");
							sa.fail();
							test.log(LogStatus.FAIL, "Quick response message not sent");
						}
					}else{
						launch_APP(Locators_CallSetting.MessagePlus);
						if(isElementExist(Locators_CallSetting.VZW_first_sms)){
							clickBtn(Locators_CallSetting.VZW_first_sms);
							minWait();
							System.out.println("message sent");
							APP_LOGS.info( "Quick response message sent ");
							sa.assertTrue(true, "Quick response message sent");
							test.log(LogStatus.PASS, "Quick response message sent ");
						}else{
							System.out.println("message not sent");
							APP_LOGS.info("Quick response message not sent");
							sa.fail();
							test.log(LogStatus.FAIL, "Quick response message not sent");
						}
						}
					}
				
				
			
				
			
		}catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "validate_Editedquickresponse_displayproperly_duringcallscreen()");
			e.printStackTrace();

		}catch (Exception e) {
			test.log(LogStatus.ERROR, "Exeption in ->validate_Editedquickresponse_displayproperly_duringcallscreen()");

		}

	}
	public void delete_SMS_O(SoftAssert sa) throws InterruptedException {
		/* This Method delete the First Thread in the List. */

		try {
			if(isElementExist(Locators_CallSetting.firstSMS_InList_O)){
			minWait();
			clickBtn(Locators_CallSetting.firstSMS_InList_O);
			minWait();
			clickBtn(Locators_CallSetting.moreOption_O);
			minWait();
			clickBtn(Locators_CallSetting.delete_Messages);
			minWait();
			clickBtn(Locators_CallSetting.ALL_Msg);
			minWait();
			clickBtn(Locators_CallSetting.delete_Btn);
			minWait();
			clickBtn(Locators_CallSetting.Delete);
			minWait();
			APP_LOGS.info(" SMS deleted Successfully");
			sa.assertTrue(true, "SMS deleted Successfully");
			test.log(LogStatus.PASS, "SMS deleted Successfully");
		
	}

else{
	System.out.println("Deleting SMS failed");
	APP_LOGS.info("Deleting SMS failed");
	sa.fail();
	test.log(LogStatus.FAIL, "Deleting SMS failed");
	clickBackButton_without_try_catch(4);
		}
		}  catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR,"Error in locators->delete_SMS_O()");
		}catch (Exception e) {
			test.log(LogStatus.ERROR,"Exeption in ->delete_SMS_O()");
		}
	}
	public void delete_SMS_SL(SoftAssert sa) throws InterruptedException {
		/* This Method delete the First Thread in the List. */

		try {
			System.out.println("IN Delete");
			minWait();
			if (isElementExist(multi_Loc_Strategy(Locators_CallSetting.firstsmsinlist_sl, Locators_CallSetting.firstsmsinlist_sl_indx, Locators_CallSetting.firstsmsinlist_text_sl, Locators_CallSetting.firstsmsinlistcls_sl, Locators_CallSetting.firstsmsinlistdes_sl, 1712, 1896))) {
				clickBtn(multi_Loc_Strategy(Locators_CallSetting.firstsmsinlist_sl, Locators_CallSetting.firstsmsinlist_sl_indx, Locators_CallSetting.firstsmsinlist_text_sl, Locators_CallSetting.firstsmsinlistcls_sl, Locators_CallSetting.firstsmsinlistdes_sl, 605, 752));
				minWait();
				clickBtn(Locators_CallSetting.moreoptions_des_sl);
				minWait();
				clickBtn(multi_Loc_Strategy(Locators_CallSetting.delete_sl, Locators_CallSetting.delete_sl_indx, Locators_CallSetting.delete_id_sl, Locators_CallSetting.deletecls_sl, Locators_CallSetting.deletetxt_sl, 1735, 412));
				minWait();
				clickBtn(multi_Loc_Strategy(Locators_CallSetting.cnfrmdelete_id_sl, Locators_CallSetting.cnfrmdelete_sl,Locators_CallSetting.cnfrmdelete_sl_indx, Locators_CallSetting.cnfrmdeletecls_sl, Locators_CallSetting.cnfrmdeletetxt_sl, 1712, 1896));
				minWait();
				APP_LOGS.info(" SMS deleted Successfully");
				sa.assertTrue(true, "SMS deleted Successfully");
				test.log(LogStatus.PASS, "SMS deleted Successfully");
			
		}
	
	else{
		System.out.println("Deleting SMS failed");
		APP_LOGS.info("Deleting SMS failed");
		sa.fail();
		test.log(LogStatus.FAIL, "Deleting SMS failed");
		clickBackButton_without_try_catch(4);
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR,"Error in locators->delete_SMS_SL()");
		}catch (Exception e) {
			test.log(LogStatus.ERROR,"Exeption in ->delete_SMS_SL()");
		}
	}
}
































































































































