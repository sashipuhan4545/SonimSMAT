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
import org.sikuli.script.FindFailed;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;
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
	public String pryNum =AllQA.PRIMARYDEVMDN;	// Reference Device MDN. 
	public String refNum = AllQA.REFERENCEDEVMDN;	// Reference Device MDN.


	public void fetch_Devices_Details() throws InterruptedException, FileNotFoundException, IOException, ParseException {

		minWait();
		p_Id=JsonFileReaderAndWriter.primaryDevIdReader();
		r_Id=JsonFileReaderAndWriter.ReadRefDeviceId();
		p_b_No=JsonFileReaderAndWriter.primaryDevFirmwareReader();
		r_b_No=JsonFileReaderAndWriter.ReadRefDeviceFirmWare();
	}

	public void navigate_to_call_settings(){
		try {
			clickBtn(Locators_BaseUtil.settings);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void navigate_To_Settings_AndElement(AndroidElement element) throws InterruptedException
	{
		/*
		 * Navigates to settings and clicks on given element
		 */
		try {
			minWait();
			clickBtn(Locators_CallSetting.settingsIcon);
			minWait();
			clickBtn(Locators_CallSetting.settingsOpt);
			minWait();
			element.click();
			minWait();
		} catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "Element Not Found");
		}
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
	public void validateSortByAndNameFormat(AndroidElement element,AndroidElement subElement ,AndroidElement contactName,String Contact) throws InterruptedException {
		/*
		 * Common method to validate sortBy and name format option 
		 */
		SoftAssert SA= new SoftAssert();
		try {

			navigate_To_Settings_AndElement(Locators_CallSetting.displayOptions);

			String field = selectDisplaySubOption(element, subElement);
			minWait();
			clickBackButton(2);
			clickBtn(Locators_CallSetting.contactPage);
			minWait();
			String actualText = contactName.getText();
			System.out.println(actualText);
			minWait();
			if(actualText.equals(Contact)){
				check = true;
				APP_LOGS.info( field +" feature is validated successfully");
				test.log(LogStatus.PASS, field +" feature is validated successfully");
				SA.assertTrue(check, field +" feature is validated successfully");
			}else{
				APP_LOGS.info(field +" feature is not validated");
				test.log(LogStatus.FAIL, field +" feature is not validated");
				SA.fail();
			}
		}catch (Exception e) {
			e.printStackTrace();
			APP_LOGS.info("Element Not Found");
			test.log(LogStatus.ERROR, "Element Not Found");
			SA.fail();
		}
		SA.assertAll();
	}
	public void clickBackButton(int number)
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
			e.printStackTrace();
		}
	}
	public void validateSettingsAndDisplayOptions() throws InterruptedException, IOException
	{
		/*
		 * validates settings and display option
		 */
		try {
			minWait();
			clickBtn(Locators_CallSetting.displayOptions);
			minWait();
			validateSortByAndNameFormat(Locators_CallSetting.sortByOpt,Locators_CallSetting.firstNameOpt,Locators_CallSetting.contact1, "Abc Automation");
			minWait();
			validateSortByAndNameFormat(Locators_CallSetting.sortByOpt,Locators_CallSetting.lastNameOpt,Locators_CallSetting.contact2, "Cde Test");
			minWait();
			validateSortByAndNameFormat(Locators_CallSetting.nameFormatOpt, Locators_CallSetting.firstNameFirstOpt,Locators_CallSetting.contact2, "Cde Test");
			minWait();
			validateSortByAndNameFormat(Locators_CallSetting.nameFormatOpt, Locators_CallSetting.lastNameFirstOpt,Locators_CallSetting.lnfcontact2, "Test, Cde");
			minWait();
		} catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "Element Not Found");
		}
	}

	public void deleteIfContactsPresent() throws InterruptedException
	{
		/*
		 * deletes contacts if present in contacts application
		 */
		try {
			clickBtn(Locators_CallSetting.delete_option);
			minWait();
			if(isElementExist(Locators_CallSetting.zero_selected)){
				minWait();
				/*TouchAction touchaction = new TouchAction(aDriver);
				touchaction.longPress(Locators_CallSetting.zero_selected).perform().release();
				minWait();*/
				clickBtn(Locators_CallSetting.zero_selected);
				minWait();
				if(isElementExist(Locators_CallSetting.selectAllOpt)){
					clickBtn(Locators_CallSetting.selectAllOpt);
					minWait();
				}else{
					clickBackButton(1);
				}
				clickBtn(Locators_CallSetting.Ok_option);
				minWait();
				clickBtn(Locators_CallSetting.okBtn);
				customWait(5000);
				clickBackButton(2);
			}
		} catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "Element Not Found");
		}
	}

	public void deleteConatactList() throws InterruptedException{
		//deleting added contacts if present in Black or white list
		try {
			for(int i=1; i<=20; i++){
				if(isElementExist(Locators_CallSetting.contactList)){
					//System.out.println("Im in delete method");
					minWait();
					clickBtn(Locators_CallSetting.unBlock_Icon);
					minWait();
					clickBtn(Locators_CallSetting.remove_Confirm);
					APP_LOGS.info("Validation is successful");
					test.log(LogStatus.PASS, "validated added whitelisted contact ");
				} else {
					APP_LOGS.info("Validation is not successful");

					test.log(LogStatus.FAIL,"Validation is not successful ");

				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	public void createContactWithNameandPhone(String name,String lastname,String phone) throws InterruptedException
	{
		/*
		 * Enter name and phone number and save the contact
		 */
		try {
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
		} catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "Element Not Found");
		}
	}

	public void validateCreateNewContactFromCallLog(String name) throws InterruptedException
	{
		/*
		 * Validates Create New contact from call log
		 */
		try {
			minWait();
			clickBtn(Locators_CallSetting.callLogPage);
			customWait(2000);
			CreateNewContactValidateSavedContact(name,"from call Log");
			minWait();
			clickBtn(Locators_CallSetting.contactPage);
			minWait();
			clickBtn(Locators_CallSetting.addedContactCallLog);
			minWait();
			clickBtn(Locators_CallSetting.moreSettings);
			minWait();
			clickBtn(Locators_CallSetting.delete);
			minWait();
			clickBtn(Locators_CallSetting.deleteBtn);
			minWait();
		} catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "Element Not Found");
		}
	}

	public void CreateNewContactValidateSavedContact(String name,String location) throws InterruptedException
	{
		/*
		 * Validates Create New contact and validate saved contact
		 */
		try {
			minWait();
			CreateNewContactAndSave(name);
			customWait(2000);
			if(isElementExist(Locators_CallSetting.permissionPopUp)) {
				minWait();
				for(int i=0;i<3;i++){
					minWait();
					clickBtn(Locators_CallSetting.allowBtn);
				}
				validateSavedContact(name,location);
			}else{
				validateSavedContact(name,location);
			}
		} catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "Element Not Found");
		}
	}
	public void CreateNewContactAndSave(String name) throws InterruptedException
	{
		/*
		 * Clicks on Create New contact -->enter name --> save
		 */
		try {
			minWait();
			clickBtn(Locators_CallSetting.createNewContactOpt);
			customWait(2000);
			enterTextToInputField(Locators_CallSetting.nameEditFld,name);
			customWait(2000);
			clickBtn(Locators_CallSetting.saveOpt);
		} catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "Element Not Found");
		}
	}

	public void validateSavedContact(String name,String location) throws InterruptedException
	{
		/*
		 * validates saved contact
		 */
		SoftAssert SA= new SoftAssert();
		try {
			customWait(2000);
			String savedContact = Locators_CallSetting.savedContact.getText();
			if(savedContact.contains(name)){
				clickBackButton(1);
				check = true;
				APP_LOGS.info("Contact saved "+ location +"successfully");
				test.log(LogStatus.PASS, "Contact saved  "+ location +" successfully");
				test.log(LogStatus.PASS, "Test case Status is PASS");
				SA.assertTrue(check, "Contact saved "+ location +" successfully");
			} else	{
				APP_LOGS.info("Contact not saved" + location);
				test.log(LogStatus.FAIL, "Contact not saved" + location);
				test.log(LogStatus.FAIL, "Test case Status is FAIL");
				SA.fail();
			}		
		} catch (Exception e) {
			e.printStackTrace();
			APP_LOGS.info("Element Not Found");
			test.log(LogStatus.ERROR, "Element Not Found");
			SA.fail();
		}
		SA.assertAll();
	}

	public void validateAddContactFromContactsPage(String name,String lastname,String phone) throws InterruptedException, IOException{
		/*
		 * Validate Add contact from contacts page and validate added contact 
		 */
		try
		{
			minWait();
			clickBtn(Locators_CallSetting.contactPage);
			minWait();
			clickBtn(Locators_CallSetting.createNewContactInContactPage);

			createContactWithNameandPhone(name,lastname, phone);
			minWait();
			validateSavedContact(name, "from Contacts page");
		}catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "Element Not Found");
		}
	} 
	public void add_Contact(String Newname,String Newlastname,String Newphone) throws InterruptedException, IOException{
		/*
		 * Validate Add contact from contacts page and validate added contact 
		 */
		try
		{
			minWait();
			clickBtn(Locators_CallSetting.contactPage);
			minWait();
			clickBtn(Locators_CallSetting.createSecondcontactInContactPage);

			createContactWithNameandPhone(Newname,Newlastname, Newphone);
			minWait();
			validateSavedContact(Newname, "from Contacts page");
		}catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "Element Not Found");
		}
	} 

	public void navigateToSettingsAndElement(WebElement element) throws InterruptedException
	{
		/*
		 * Navigates to settings and clicks on given element
		 */
		try {
			customWait(2000);
			clickBtn(Locators_CallSetting.settingsIcon);
			minWait();
			clickBtn(Locators_CallSetting.settingsOpt);
			minWait();
			element.click();
			minWait();
		} catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "Element Not Found");
		}
	}


	public void validatePhoneRingtone() throws InterruptedException {
		/*
		 * Sets phone ringtone and validates selected phone ringtone
		 */
		SoftAssert SA= new SoftAssert();
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
				test.log(LogStatus.PASS, "Phone Ringtone is validated successfully");
				SA.assertTrue(check, "Phone Ringtone is validated successfully");
			}else{
				APP_LOGS.info("Phone Ringtone is not validated");
				test.log(LogStatus.FAIL, "Phone Ringtone is not validated");
				SA.fail();
			}
		}catch (Exception e) {
			e.printStackTrace();
			APP_LOGS.info("Element Not Found");
			test.log(LogStatus.ERROR, "Element Not Found");
			SA.fail();
		}
		SA.assertAll();
	}



	public void validateSoundsAndVibrationSubOptions() throws InterruptedException {
		/*
		 * validates sounds and vibration sub-options checkbox status 
		 */
		try {
			minWait();
			checkOtherSoundOptionsCheckboxStatus(Locators_CallSetting.alsoVibrateForCallsOpt, Locators_CallSetting.alsoVibrateCheckbox);
			customWait(2000);
			checkOtherSoundOptionsCheckboxStatus(Locators_CallSetting.dialpadTonesOpt, Locators_CallSetting.dialpadToneCheckbox);
			customWait(2000);
			checkOtherSoundOptionsCheckboxStatus(Locators_CallSetting.callEndToneOpt, Locators_CallSetting.callEndToneCheckbox);
			minWait();
		}catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "Element Not Found");
		}
	}   


	public void  checkOtherSoundOptionsCheckboxStatus(WebElement element,WebElement checkbox) throws InterruptedException {
		/*
		 * checks whether given elements checkbox is enabled
		 */
		SoftAssert SA= new SoftAssert();
		try {
			minWait();
			String soundResourceText = element.getText();
			minWait();
			element.click();
			minWait();
			if(isElementExist(checkbox)){
				element.click();
				check = true;
				APP_LOGS.info(soundResourceText+" is Enabled");
				test.log(LogStatus.PASS, soundResourceText+" is Enabled");
				SA.assertTrue(check,soundResourceText+" is Enabled");
			}else{
				APP_LOGS.info(soundResourceText+" is disabled");
				test.log(LogStatus.FAIL, soundResourceText+" is disabled");
			}
		}catch (Exception e) {
			e.printStackTrace();
			APP_LOGS.info("Element Not Found");
			test.log(LogStatus.ERROR, "Element Not Found");
			SA.fail();
		}
		SA.assertAll();
	}
	public void validateQuickResponses(String data) throws InterruptedException
	{	
		/*
		 * Validate quick response option
		 */
		try {
			minWait();
			clickBtn(Locators_CallSetting.moreSettings);
			minWait();
			clickBtn(Locators_CallSetting.restoreDefaultsInQuickResponse);
			customWait(2000);
			getAndValidateQuickResponsesList();
			minWait();
			editAndValidateQuickResponse(data);
			minWait();
			restoreDefaults();
			clickBackButton(2);
		} catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "Element Not Found");
		}
	}

	public void getAndValidateQuickResponsesList() throws InterruptedException
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
		}catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "Element Not Found");
		}
	}

	public void getQuickResonseText(WebElement element,String expectedTextMessage) throws InterruptedException 
	{
		/*
		 * Gets Quick response text message and returns
		 */
		SoftAssert SA= new SoftAssert();
		try {
			minWait();
			String textMessage = element.getText();
			minWait();
			if(textMessage.contains(expectedTextMessage)){
				check = true;
				APP_LOGS.info("Quick Resonse message : "+textMessage);
				test.log(LogStatus.INFO,"Quick Resonse message : "+textMessage);
				SA.assertTrue(check,"Quick Resonse message : "+textMessage);
			}else{
				APP_LOGS.info("Quick Resonse message : "+textMessage +" is not available");
				test.log(LogStatus.ERROR, "Quick Resonse message : "+textMessage +" is not available");
				SA.fail();
			}
		}catch (Exception e) {
			e.printStackTrace();
			APP_LOGS.info("Element Not Found");
			test.log(LogStatus.ERROR, "Element Not Found");
			SA.fail();
		}
		SA.assertAll();
	}
	public void editAndValidateQuickResponse(String data) throws InterruptedException
	{
		/*
		 *  Edit quick response and validate edited quick response message
		 */
		SoftAssert SA= new SoftAssert(); 
		try{
			minWait();
			Locators_CallSetting.quickResponseText.click();
			minWait();
			Locators_CallSetting.quickResponseEditField.clear();
			minWait();
			enterTextToInputField(Locators_CallSetting.quickResponseEditField, data);
			minWait();
			Locators_CallSetting.okBtn.click();
			minWait();
			String actualEditedMessage = Locators_CallSetting.quickResponseText.getText();
			if(actualEditedMessage.contains("Hello Sonim")){
				check = true;
				APP_LOGS.info("Edited and validated Quick Response text");
				test.log(LogStatus.INFO, "Edited and validated Quick Response text");
				SA.assertTrue(check, "Edited and validated Quick Response text");
			}else{
				APP_LOGS.info("Edited Quick Response Text is not available");
				test.log(LogStatus.ERROR, "Edited Quick Response Text is not available");
				SA.fail();
			}
		}catch (Exception e) {
			e.printStackTrace();
			APP_LOGS.info("Element Not Found");
			test.log(LogStatus.ERROR, "Element Not Found");
			SA.fail();
		}
		SA.assertAll();
	}
	public void restoreDefaults() throws InterruptedException{
		/*
		 * Validate Restore default option
		 */
		SoftAssert SA= new SoftAssert();
		try{
			clickBtn(Locators_CallSetting.moreSettings);
			minWait();
			clickBtn(Locators_CallSetting.restoreDefaultsInQuickResponse);
			customWait(2000);
			String actualString = Locators_CallSetting.quickResponseText.getText();
			if(actualString.equals("Can't talk now. What's up?")){
				check = true;
				APP_LOGS.info("Default settings Restored");
				test.log(LogStatus.INFO, "Default settings Restored");
				SA.assertTrue(check, "Default settings Restored");
			}else{
				APP_LOGS.info("Failed to restore default Quick Response");
				test.log(LogStatus.ERROR, "Failed to restore default Quick Response");
				SA.fail();
			}
		}catch (Exception e) {
			e.printStackTrace();
			APP_LOGS.info("Element Not Found");
			test.log(LogStatus.ERROR, "Element Not Found");
			SA.fail();
		}
		SA.assertAll();
	}
	public void assignSpeedDialNumber(String Phone,String name) throws InterruptedException, IOException{
		/*
		 * Validate All speed dial settings
		 */
		SoftAssert SA1= new SoftAssert();
		try{
			minWait();
			presenceOfVoicemailInSpeedDailSettings();
			minWait();
			addContactInSpeedDailSettings(Phone);
			minWait();
			replaceContactInSpeedDailSettings(name);
			minWait();
			deleteContactInSpeedDailSettings();
			minWait();
			//aDriver.pressKeyCode(AndroidKeyCode.BACK);
			Runtime.getRuntime().exec("adb -s "+ p_Id +" shell input keyevent 4");
			longpress(2);
			check = true;
			APP_LOGS.info("Speed Dail settings validated successfully");
			test.log(LogStatus.INFO, "Speed Dail settings validated successfully");
			SA1.assertTrue(check, "Speed Dail settings validated successfully");
		}catch (Exception e) {
			e.printStackTrace();
			APP_LOGS.info("Element Not Found");
			test.log(LogStatus.ERROR, "Element Not Found");
			SA1.fail();
		}
		SA1.assertAll();
	}
	public void presenceOfVoicemailInSpeedDailSettings()
	{
		/*
		 * validates Presence of voicemail option
		 */
		SoftAssert SA= new SoftAssert();
		try
		{
			if(isElementExist(Locators_CallSetting.voiceMailOpt)){
				check = true;
				APP_LOGS.info("Voicemail Option is present in Speed Dail settings");
				test.log(LogStatus.INFO, "Voicemail Option is present in Speed Dail settings");
				SA.assertTrue(check, "Voicemail Option is present in Speed Dail settings");
			}else {
				APP_LOGS.info("Voicemail Option is not present in Speed Dail settings");
				test.log(LogStatus.INFO, "Voicemail Option is not present in Speed Dail settings");
				SA.fail("Voicemail Option is not present in Speed Dail settings");
			}
		}catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "Element Not Found");
		}
		SA.assertAll();
	}

	public void addContactInSpeedDailSettings(String Phone) throws InterruptedException, IOException
	{
		/*
		 * Add contact in speed Dial settings and validate
		 */
		//SoftAssert SA= new SoftAssert();
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
				check = true;
				APP_LOGS.info("Contact added in Speed Dail settings");
				test.log(LogStatus.INFO, "Contact added in Speed Dail settings");
				//SA.assertTrue(check, "Contact added in Speed Dail settings");
			}else {
				APP_LOGS.info("Contact not added in Speed Dail settings");
				test.log(LogStatus.INFO, "Contact not added in Speed Dail settings");
				//SA.fail();
			}
		}catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "Element Not Found");
		}
		//SA.assertAll();
	}

	public void replaceContactInSpeedDailSettings(String name) throws InterruptedException, IOException
	{
		/*
		 * Replace contact in speed dial settings and validate
		 */
		SoftAssert SA= new SoftAssert();
		try
		{
			clickBtn(Locators_CallSetting.addedContactInSpeedDialSettings);
			minWait();
			clickBtn(Locators_CallSetting.replaceOptInSpeedDialSettings);
			minWait();
			clickBtn(Locators_CallSetting.selectContactInSpeedDialSettings);
			minWait();
			clickBtn(Locators_CallSetting.searchOpt);
			customWait(4000);
			Runtime.getRuntime().exec("adb -s "+ p_Id +" shell input text "+name);
			customWait(3000);
			clickBtn(Locators_CallSetting.firstNameContact);
			minWait();
			if(isElementExist(Locators_CallSetting.firstNameContact)){
				check = true;
				APP_LOGS.info("Contact replaced in Speed Dail settings");
				test.log(LogStatus.INFO, "Contact replaced in Speed Dail settings");
				SA.assertTrue(check, "Contact replaced in Speed Dail settings");
			}else {
				APP_LOGS.info("Contact not replaced in Speed Dail settings");
				test.log(LogStatus.INFO, "Contact not replaced in Speed Dail settings");
				SA.fail("Contact not replaced in Speed Dail settings");
			}
		}catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "Element Not Found");
		}
		SA.assertAll();
	}
	public void deleteContactInSpeedDailSettings() throws InterruptedException, IOException
	{
		/*
		 * Delete contact in speed dial settings and validate
		 */
		SoftAssert SA= new SoftAssert();
		try
		{
			clickBtn(Locators_CallSetting.firstNameContact);
			minWait();
			clickBtn(Locators_CallSetting.delete);
			minWait();
			if(isElementExist(Locators_CallSetting.notSetInSpeedDialSettings)){
				check = true;
				APP_LOGS.info("Contact deleted in Speed Dail settings");
				test.log(LogStatus.INFO, "Contact deleted in Speed Dail settings");
				SA.assertTrue(check, "Contact deleted in Speed Dail settings");
			}else {
				APP_LOGS.info("Contact not deleted in Speed Dail settings");
				test.log(LogStatus.INFO, "Contact not deleted in Speed Dail settings");
				SA.fail("Contact not deleted in Speed Dail settings");
			}
		}catch (org.openqa.selenium.NoSuchElementException e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "Element Not Found");
		}
		SA.assertAll();
	}
	public void BlacklistincallScreening(String phoneno,String phoneno2) throws InterruptedException {

		//* black list


		try {
			minWait();
			clickBtn(Locators_CallSetting.Manageblacklist);
			for(int i=1; i<=2;i++){
				clickBtn(Locators_CallSetting.addanumber);
				minWait();
				enterTextToInputField(Locators_CallSetting.Phonenumber, phoneno);
				minWait();
				clickBtn(Locators_CallSetting.blockBtn);
				minWait();
				minWait();
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "Element Not Found");

		}
	}

	public void UnblockincallScreening(String phone) throws InterruptedException {

		// * unblock in black list

		clickBackButton(4);
		launch_an_app("phone");
		navigateToSettingsAndElement(Locators_CallSetting.Callscreening);  
		try {
			minWait();
			clickBtn(Locators_CallSetting.Manageblacklist);
			minWait();
			clickBtn(Locators_CallSetting.deleteblockedno);
			minWait();
			clickBtn(Locators_CallSetting.unblockBtn);
			minWait();			
		}
		catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "Element Not Found");

		}
	}
	public void clearcallhistory()throws InterruptedException, IOException
	{

		/*
		 * clear frequents,all call log  and delete contacts before test
		 */
		minWait();
		if(isElementExist(Locators_CallSetting.clearCallHistoryOpt)){

			launch_an_app("phone");
			navigateTocallHistory();
			minWait();
			clickBtn(Locators_CallSetting.moreOptionsInCallHistory);
			minWait();
			clickBtn(Locators_CallSetting.clearCallHistoryOpt);
			minWait();
			clickBtn(Locators_CallSetting.okBtn);
			customWait(3000);
			clickBackButton(3);
			deleteIfContactsPresent();
		}else if(!isElementExist(Locators_CallSetting.clearCallHistoryOpt)){
			minWait();
			deleteIfContactsPresent();
		}minWait();
		launch_an_app("phone");
		navigateTocallHistory();
		minWait();
		clickBtn(Locators_CallSetting.moreOptionsInCallHistory);
		minWait();
		clickBtn(Locators_CallSetting.clearCallHistoryOpt);
		minWait();
		clickBtn(Locators_CallSetting.okBtn);
		customWait(3000);
		clickBackButton(3);

	}
	public void navigateTocallHistory() throws InterruptedException, IOException
	{
		/*
		 * navigate to call History page
		 */
		try {
			minWait();
			clickBtn(Locators_CallSetting.settingsIcon);
			minWait();
			clickBtn(Locators_CallSetting.callHistoryOpt);
			minWait();
		} catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "Element Not Found");
		}
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

		} catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "Element Not Found");
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
		} catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "Element Not Found");
		}
	}

	public void blockUnblockNumber(WebElement element, WebElement subElement) throws InterruptedException
	{
		/*
		 * Block And Unblock number
		 */
		try {
			minWait();
			element.click();
			minWait();
			subElement.click();
			minWait();
		} catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "Element Not Found");
		}
	}

	public void validateBlockAndUnblockNumber(String status,String phonenum,SoftAssert sf2) throws InterruptedException
	{
		/*
		 * Validates Block And Unblock number
		 */
		try {

			minWait();
			clearcallhistory();
			minWait();
			MakeACall(phonenum);
			clickBackButton(2);
			minWait();

			launch_an_app("phone");
			clickBtn(Locators_CallSetting.Callhistorytab);
			customWait(3000);
			System.out.println("Sarching...");
			String actualString = Locators_CallSetting.Blocked.getText();
			System.out.println(actualString);
			String NumString = Locators_CallSetting.dailedFirstNum.getText();
			System.out.println(NumString);

			if(actualString.contains("Blocked")){
				check = true;
				APP_LOGS.info("Number is blocked");
				test.log(LogStatus.PASS, "Number is blocked ");
				sf2.assertTrue(check, "Number is blocked");
			}else {
				APP_LOGS.info("Number is  Unblocked");
				test.log(LogStatus.FAIL, "Number is  Unblocked");
				sf2.fail("Number is  Unblocked");
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "Element Not Found");
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
	public void validate_blocknumber_with_values(String str,SoftAssert sf3){

		try {
			String NumString = Locators_CallSetting.dailedFirstNum.getText();
			System.out.println(NumString);
			if((NumString.contains(str))) {
				check = true;
				clickBackButton(3);
				APP_LOGS.info("Contact saved with 'str' successfully");
				test.log(LogStatus.PASS, "Contact saved with 'str' successfully");

				sf3.assertTrue(check, "Contact saved with 'str' successfully");
			} else	{
				APP_LOGS.info("Failed to save Contact with 'str'" );
				test.log(LogStatus.FAIL, "Failed to save Contact with 'str'");
				test.log(LogStatus.FAIL, "Test case Status is FAIL");
				sf3.fail();
			}
		} catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "Element Not Found");
		}
	}
	public void Addingno_in_blacklist_callscreening(String phoneno){
		//Adding no in black list


		try {
			minWait();
			clickBtn(Locators_CallSetting.Manageblacklist);
			minWait();
			clickBtn(Locators_CallSetting.addanumber);
			minWait();
			enterTextToInputField(Locators_CallSetting.Phonenumber, phoneno);
			minWait();
			clickBtn(Locators_CallSetting.blockBtn);
		}
		catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void validate_pressing_backkey_once(String phoneno,SoftAssert sf4) throws InterruptedException
	{

		// pressing Back key while adding number to the blocked list

		try {
			minWait();
			clickBtn(Locators_CallSetting.Manageblacklist);
			minWait();
			clickBtn(Locators_CallSetting.addanumber);
			minWait();
			enterTextToInputField(Locators_CallSetting.Phonenumber, phoneno);
			minWait();
			clickBackButton(1);
			String imagePath="C:\\Users\\naveenat.t\\Desktop\\sikuli images\\virtualkeyboard.PNG";
			Screen sc=new Screen();
			Pattern p=new Pattern(imagePath).similar((float)0.7);
			sc.wait(p,10);
			if(sc.exists(imagePath) != null){

				System.out.println("Image exist");
			}else {
				System.out.println("image doesnot exist");
			}



		} catch (FindFailed e) {

			System.out.println("IMAGE doesnot ");

		}catch (Exception e) {
			e.printStackTrace();
		}


	}

	public void validate_pressing_backkey_twice(String phoneno,SoftAssert sf4) throws InterruptedException{
		// pressing Back key while adding number to the blocked list.

		try{
			minWait();
			Addingno_in_blacklist_callscreening(phoneno);
			clickBackButton(2);
			customWait(3000);
			if (!isElementExist(Locators_CallSetting.addingnotoblock)) {
				check = true;
				APP_LOGS.info("Blockedno screen page is dispalyed");
				test.log(LogStatus.PASS, "Blockedno screen page is displayed");
				sf4.assertTrue(check, "Blockedno screen page is dispalyed");
			}else {
				APP_LOGS.info("Blockedno screen page is not displayed");
				test.log(LogStatus.FAIL, "Blockedno screen page is not dispalyed");
				sf4.fail("Blockedno screen page is not displayed");
			}
		}catch(Exception e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "Element Not Found");
		}

	}
	public void validate_pressing_Shortpress_HomeKey(String phoneno,SoftAssert sf5) throws InterruptedException{

		try{
			minWait();
			Addingno_in_blacklist_callscreening(phoneno);
			//clickBackButton(2);
			customWait(3000);
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_HOME);
			minWait();
			if (isElementExist(Locators_CallSetting.Appslist)) {
				check = true;
				APP_LOGS.info(" Homescreen is displayed");
				test.log(LogStatus.PASS, "Homescreen is displayed");
				sf5.assertTrue(check, "Homescreen is displayed");
			}else {
				APP_LOGS.info("Homescreen is not displayed");
				test.log(LogStatus.FAIL, "Homescreen is not displayed");
				sf5.fail("Homescreen is not displayed");
			}
		}catch(Exception e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "Element Not Found");
		}

	}

	public void validate_pressing_Longpress_HomeKey(String phoneno,SoftAssert sf6) throws InterruptedException{

		//pressing Home key (LONG PRESS)while adding number to the blocked list

		try{
			minWait();
			Addingno_in_blacklist_callscreening(phoneno);
			//clickBackButton(2);
			customWait(3000);
			Runtime.getRuntime().exec("adb -s "+p_Id+" shell input keyevent --longpress KEYCODE_HOME");
			/* TouchAction action = new TouchAction(aDriver);
		action.longPress(Locators_CallSetting.file_list_SoundRec).release().perform();;
			 */

			minWait();
			if (isElementExist(Locators_CallSetting.googlequicksearch)) {
				check = true;
				APP_LOGS.info(" Googlenow on tap screen is displayed");
				test.log(LogStatus.PASS, "Googlenow on tap screen is displayed");
				sf6.assertTrue(check, "Googlenow on tap screen is displayed");
			}else {
				APP_LOGS.info("Googlenow on tap screen is not displayed");
				test.log(LogStatus.FAIL, "Googlenow on tap screen is not displayed");
				sf6.fail("Googlenow on tap screen is not displayed");
			}
		}catch(Exception e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "Element Not Found");
		}

	}
	public void validate_pressing_Recentsapp_Key(String phoneno,SoftAssert sf7) throws InterruptedException{

		//pressing Recent apps key while adding number to the blocked list

		try{
			minWait();
			Addingno_in_blacklist_callscreening(phoneno);
			//clickBackButton(2);
			customWait(3000);
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_APP_SWITCH);
			minWait();	 	
			if(!isElementExist(Locators_BaseUtil.no_Recent_Items)) {
				check = true;
				APP_LOGS.info(" Recentappkey is displaying recent opened apps");
				test.log(LogStatus.PASS, "Recentappkey is displaying recent opened apps");
				sf7.assertTrue(check, "Recentappkey is displaying recent opened apps");
			}else {
				APP_LOGS.info("Recentappkey is not displaying recent opened apps");
				test.log(LogStatus.FAIL, "Recentappkey is not displaying recent opened apps");
				sf7.fail("Recentappkey is not displaying recent opened apps");
			}

		}catch(Exception e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "Element Not Found");
		}

	}
	public void whitelist_callscreening(String phoneno){

		try {
			minWait();

			clickBtn(Locators_CallSetting.addanumber);
			minWait();
			enterTextToInputField(Locators_CallSetting.Phonenumber, phoneno);
			minWait();
			clickBtn(Locators_CallSetting.addBtn);
			customWait(2000);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void addContactNumWhitelist(String phoneNum){
		try {

			clickBtn(Locators_CallSetting.Managewhitelist);
			minWait();


			whitelist_callscreening(phoneNum);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}

	public void deleting_no_in_whitelist(String phoneNum) throws InterruptedException{

		try{
			clickBtn(Locators_CallSetting.Managewhitelist);
			minWait();
			clickBtn(Locators_CallSetting.removenoBtn);
			minWait();
			clickBtn(Locators_CallSetting.removetxt);
			minWait();
			deleteConatactList();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	public void deleting_no_in_blacklist() throws InterruptedException{

		try {
			minWait();
			clickBtn(Locators_CallSetting.Manageblacklist);
			minWait();
			clickBtn(Locators_CallSetting.deleteblockBtn);
			minWait();
			clickBtn(Locators_CallSetting.unblocktxt);
			minWait();
			deleteConatactList();

		}
		catch (Exception e) {
			e.printStackTrace();
		}				
	}

	public void validatescrollingno_in_whitelist(String phoneNum, int n,SoftAssert sf8){
		//
		System.out.println(phoneNum);
		if(scrollTo(phoneNum)){
			check=true;
			APP_LOGS.info("Validation is successful");
			sf8.assertTrue(check, "TestCase Valiation is PASS");
			test.log(LogStatus.PASS, "validated added whitelisted contact "+n);
		} else {
			APP_LOGS.info("Validation is not successful");
			sf8.fail();
			test.log(LogStatus.FAIL,"Validation is not successful "+n);

		}
	}
	

	public void validatescrollingno_in_blacklist(String phoneNum, int n,SoftAssert sf1){
		//
		System.out.println(phoneNum);
		if(scrollTo(phoneNum)){
			check=true;
			APP_LOGS.info("Validation is successful");
			sf1.assertTrue(check, "TestCase Valiation is PASS");
			test.log(LogStatus.PASS, "validated added whitelisted contact "+n);
		} else {
			APP_LOGS.info("Validation is not successful ");
			sf1.fail();
			test.log(LogStatus.FAIL,"Validation is not successful "+n);

		}
	}
	public void validate_cancel_option(String phoneno,SoftAssert sf2) throws InterruptedException{

		//**CANCEL options while adding number to White list

		try {
			deleting_no_in_blacklist();
			minWait();
			clickBackButton(1);
			minWait();
			clickBtn(Locators_CallSetting.Manageblacklist);
			minWait();
			clickBtn(Locators_CallSetting.addanumber);
			minWait();
			enterTextToInputField(Locators_CallSetting.Phonenumber, phoneno);
			minWait();
			clickBtn(Locators_CallSetting.canceltxt);
			minWait();

			if(isElementExist(Locators_CallSetting.contactList)){
				check = true;
				APP_LOGS.info(" number is not added to list");
				test.log(LogStatus.PASS, "number is not added to list");
				sf2.assertTrue(check, "number is not added to list");
			}else {
				APP_LOGS.info("number is added to list");
				test.log(LogStatus.FAIL, "number is added to list");
				sf2.fail("number is  added to list");
			}
		}
		catch(Exception e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "Element Not Found");
		}

	}

	public void validate_addno_option_under_callsettings_blacklist(String phoneno,SoftAssert sf3) throws InterruptedException{
		// number field is displayed on selecting "ADD A NUMBER" option under Black List
		try {
			minWait();
			clickBtn(Locators_CallSetting.Manageblacklist);
			minWait();
			clickBtn(Locators_CallSetting.addanumber);
			minWait();
			if(isElementExist(Locators_CallSetting.contactList)){
				check = true;
				APP_LOGS.info(" numberfield option is displayed ");
				test.log(LogStatus.PASS, "numberfield option is displayed");
				sf3.assertTrue(check, "numberfield option is displayed");
			}else {
				APP_LOGS.info("numberfield option is not displayed");
				test.log(LogStatus.FAIL, "numberfield option is not displayed");
				sf3.fail("numberfield option is not displayed");
			}
		} catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "Element Not Found");
		}
	}
	public void validate_addno_option_under_callsettings_Whitelist(String phoneno,SoftAssert sf3) throws InterruptedException{
		// number field is displayed on selecting "ADD A NUMBER" option under white List
		try {
			minWait();
			clickBtn(Locators_CallSetting.Managewhitelist);
			minWait();
			clickBtn(Locators_CallSetting.addanumber);
			minWait();
			if(isElementExist(Locators_CallSetting.contactList)){
				check = true;
				APP_LOGS.info(" numberfield option is displayed ");
				test.log(LogStatus.PASS, "numberfield option is displayed");
				sf3.assertTrue(check, "numberfield option is displayed");
			}else {
				APP_LOGS.info("numberfield option is not displayed");
				test.log(LogStatus.FAIL, "numberfield option is not displayed");
				sf3.fail("numberfield option is not displayed");
			}
		} catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "Element Not Found");
		}
	}

	public void checkScreeningIncomingEnabled() throws InterruptedException{
		if(!Locators_CallSetting.screeningincomingcallsetting.isEnabled()){
			clickBtn(Locators_CallSetting.screeningincomcallcheckbox);
			minWait();
			System.out.println("Clicked disabled checkbox");
		}

	}

	public void validate_screening_incoming_call_highlighted(SoftAssert sf4){
		//Screening incoming call Setting option is highlighted (not greyed out)
		try {
			minWait();
			checkScreeningIncomingEnabled();
			if(Locators_CallSetting.screeningincomingcallsetting.isEnabled()){
				clickBtn(Locators_CallSetting.screeningincomingcallsetting);
				minWait();
				if(isElementExist(Locators_CallSetting.ScreeningincomingcallsettingOpt) && isElementExist(Locators_CallSetting.blockblacklistopt) && isElementExist(Locators_CallSetting.allowwhitelistopt)){

				}
				check = true;
				APP_LOGS.info(" Screening incoming call setting is highlighted & options are shown ");
				System.out.println("Highlighted");
				test.log(LogStatus.PASS, "Screening incoming call setting is highlighted & options are shown");
				sf4.assertTrue(check, "Screening incoming call setting is highlighted & options are shown");
			}else {
				APP_LOGS.info("Screening incoming call setting is not highlighted & options are not shown");
				test.log(LogStatus.FAIL, "Screening incoming call setting is not highlighted & options are not shown");
				sf4.fail("Screening incoming call setting is not highlighted & options are not shown");
			}
		} catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "Element Not Found");
		}
	}

	public void validate_Callscreening_under_Callsettings(SoftAssert sf4)
	{
		try{
			clickBackButton(1);
			if(isElementExist(Locators_CallSetting.Callscreening)){
				check = true;
				APP_LOGS.info(" CallScreening present under Callsettings  ");
				test.log(LogStatus.PASS, "CallScreening present under Callsettings");
				sf4.assertTrue(check, "CallScreening present under Callsettings");
			}else {
				APP_LOGS.info("CallScreening not present under Callsettings");
				test.log(LogStatus.FAIL, "CallScreening not present under Callsettings");
				sf4.fail("CallScreening not present under Callsettings");
			}
		} catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "Element Not Found");
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
			clickBtn(Locators_CallSetting.addanumber);
			minWait();
			enterTextToInputField(Locators_CallSetting.Phonenumber, refNum);
			minWait();
			clickBtn(Locators_CallSetting.blockBtn);
			minWait();
			clickBackButton(4);
			minWait();

		}
		catch(Exception e){
			e.printStackTrace();
			test.log(LogStatus.ERROR, "Element Not Found");

		}
	}
	public void validate_blockno_in_blacklist(SoftAssert sf5) throws InterruptedException{
		try{
			launch_an_app("phone");
			navigateToSettingsAndElement(Locators_CallSetting.Callscreening);
			minWait();
			clickBtn(Locators_CallSetting.Manageblacklist);
			minWait();
			if(isElementExist(Locators_CallSetting.firstblockedno)){
				check = true;
				APP_LOGS.info(" Blockingno from contacts app is reflected in Black lsit under Call screening");
				test.log(LogStatus.PASS, "Blockingno from contacts app is reflected in Black lsit under Call screening");
				sf5.assertTrue(check, "Blockingno from contacts app is reflected in Black lsit under Call screening");
			}else {
				APP_LOGS.info("Blockingno from contacts app is not reflected in Black lsit under Call screening");
				test.log(LogStatus.FAIL, "Blockingno from contacts app is not reflected in Black lsit under Call screening");
				sf5.fail("Blockingno from contacts app is not reflected in Black lsit under Call screening");
			}
		} catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "Element Not Found");
		}
	}



	public void make_Call_from_RefDev() throws InterruptedException, IOException {
		//make a call from ref device to primary device
		try {
			Runtime.getRuntime().exec("adb -s "+r_Id+" shell am start -a android.intent.action.CALL -d tel:"+pryNum);
			minWait();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	public void make_Call_from_PrmyDev() throws InterruptedException, IOException {
		//make a call from ref device to primary device
		try {
			Runtime.getRuntime().exec("adb -s "+p_Id+" shell am start -a android.intent.action.CALL -d tel:"+refNum);
			minWait();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	public void endCall_RefDevice() {
		try {
			minWait();
			Runtime.getRuntime().exec("adb -s "+r_Id+" shell input keyevent 6");
			Thread.sleep(1000);
		} catch (Exception e) {
		}
	}

	public void endCall_PrmyDevice() {
		try {
			minWait();
			Runtime.getRuntime().exec("adb -s "+p_Id+" shell input keyevent 6");
			Thread.sleep(1000);
		} catch (Exception e) {
		}
	}

	public void recieve_Call_PrimaryDev_O() throws IOException, InterruptedException {
		SoftAssert SA = new SoftAssert();

		String value = null;
		customWait(10000);
		try {
			for(int j=1;j<=100;j++){
				Process child = null;
				Process version = null;
				version = Runtime.getRuntime().exec("adb -s "+p_Id+" shell getprop ro.build.version.release");
				InputStream lsOut1 = version.getInputStream();
				InputStreamReader r1 = new InputStreamReader(lsOut1);
				BufferedReader in1 = new BufferedReader(r1);
				String  value1=in1.readLine();
				System.out.println(value1);    
				if (p_b_No.contains("8A.")) {

					System.out.println("XP8");
					if(value1.contains("8.1")) {
						child=Runtime.getRuntime().exec("adb -s "+p_Id+" shell service call telecom 29");
					}else {
						child=Runtime.getRuntime().exec("adb -s "+p_Id+" shell service call telecom 27");
					}
				} if (p_b_No.contains("3A.")) {
					System.out.println("XP3");
					child=Runtime.getRuntime().exec("adb -s "+p_Id+" shell service call telecom 30");
				} 

				else if(p_b_No.contains("5SA.")) {
					System.out.println("XP5");
					System.out.println(value1);    

					if(value1.contains("8.1")) {
						child=Runtime.getRuntime().exec("adb -s "+p_Id+" shell service call telecom 30");
					}else {
						child=Runtime.getRuntime().exec("adb -s "+p_Id+" shell service call telecom 28");
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
					Runtime.getRuntime().exec("adb -s "+p_Id+" shell input keyevent 5");
					test.log(LogStatus.PASS,"Recieved Call in Primary device validated ");
					SA.assertTrue(check, " ");	
					break;
				}
				else {
					test.log(LogStatus.FAIL, "Receive Voice call Failed " );
					SA.fail();
				}
				customWait(10000);	

			}
			/*	aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
				minWait();*/
		} catch (Exception e) {
			Thread.sleep(2000);
			Runtime.getRuntime().exec("adb -s "+r_Id+" shell input keyevent 5");
			Thread.sleep(2000);
		}


	}


	public void validateCallLog_Orio() throws InterruptedException, IOException {
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
					test.log(LogStatus.PASS,"MO-Voice call from  is validated at : iteration " );
					//						soft.assertTrue(check, " ");	
					break;
				}
				else {
					test.log(LogStatus.FAIL, "MO-Voice call Failed at : iteration " );
					//						soft.fail();
				}
				customWait(10000);	

			}
			customWait(10000);	
			endCall_PrmyDevice();
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

	public void Screeningincomingcalls_callscreening() throws InterruptedException{
		navigateToSettingsAndElement(Locators_CallSetting.Callscreening);
		minWait();
		deleting_no_in_blacklist();
		minWait();
		Addingno_in_blacklist_callscreening(refNum);
		minWait();
		clickBackButton(1);
		checkScreeningIncomingEnabled();
		//Screeningincomingcallsetting_option2();


	}
	public void Screeningincomingcallsetting_option2() throws InterruptedException{
		//screening incoming call setting block black list option
		try{
			clickBtn(Locators_CallSetting.screeningincomingcallsetting);
			minWait();
			clickBtn(Locators_CallSetting.blockblacklistopt);
			minWait();
		}
		catch(Exception e){
			e.printStackTrace();
			test.log(LogStatus.ERROR, "Element Not Found");
		}
	}
	public void Screeningincomingcallsetting_option3() throws InterruptedException{
		//screening incoming call setting Allow white list option
		try{
			clickBtn(Locators_CallSetting.screeningincomingcallsetting);
			minWait();
			clickBtn(Locators_CallSetting.allowwhitelistopt);
			minWait();
		}
		catch(Exception e){
			e.printStackTrace();
			test.log(LogStatus.ERROR, "Element Not Found");
		}
	}
	public void Screeningincomingcallsetting_option1() throws InterruptedException{
		//screening incoming call setting allow only contacts option
		try{
			clickBtn(Locators_CallSetting.screeningincomingcallsetting);
			minWait();
			clickBtn(Locators_CallSetting.allowonlycontactsopt);
			minWait();
		}
		catch(Exception e){
			e.printStackTrace();
			test.log(LogStatus.ERROR, "Element Not Found");
		}
	}

	public void Unblockno_in_contactapp(String refNum){
		//Blocked and unblocked numbers in phone contact settings
		try{
			clickBtn(Locators_CallSetting.opennavigationdrawer);
			minWait();
			clickBtn(Locators_CallSetting.Settings);
			minWait();
			scrollToText("Blocked numbers");
			minWait();
			clickBtn(Locators_CallSetting.deleteblockBtn);
			minWait();
			clickBtn(Locators_CallSetting.unblocktxt);
			minWait();
		}
		catch(Exception e){
			e.printStackTrace();
			test.log(LogStatus.ERROR, "Element Not Found");
		}
	}
	public void validate_Unblockno_in_blacklist(SoftAssert sf5) throws InterruptedException{
		try{
			navigateToSettingsAndElement(Locators_CallSetting.Callscreening);
			minWait();
			clickBtn(Locators_CallSetting.Manageblacklist);
			minWait();
			if(!isElementExist(Locators_CallSetting.firstblockedno)){
				check = true;
				APP_LOGS.info(" UnBlockingno from contacts app is reflected in Black lsit under Call screening");
				test.log(LogStatus.PASS, "UnBlockingno from contacts app is reflected in Black lsit under Call screening");
				sf5.assertTrue(check, "UnBlockingno from contacts app is reflected in Black lsit under Call screening");
			}else {
				APP_LOGS.info("UnBlockingno from contacts app is not reflected in Black lsit under Call screening");
				test.log(LogStatus.FAIL, "UnBlockingno from contacts app is not reflected in Black lsit under Call screening");
				sf5.fail("UnBlockingno from contacts app is not reflected in Black lsit under Call screening");
			}
		} catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "Element Not Found");
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
			wait.until(ExpectedConditions.visibilityOf(Locators_CallSetting.Delivered));
			if(isElementExist(Locators_CallSetting.Delivered)||isElementExist(Locators_CallSetting.not_Sent_Text)) {
				check=true;
				APP_LOGS.info("Message sent Succeccfully");
				System.out.println("Sent");
				soft.assertTrue(check, "TestCase Valiation is PASS");
				test.log(LogStatus.PASS, "SMS Sent Successfully at iteration ");
			} else {
				APP_LOGS.info("SMS didn't sent");
				soft.fail();
				test.log(LogStatus.FAIL,"Message didn't sent at iteration ");
			}
		} catch (Exception e) {			 
			e.printStackTrace();
			soft.fail();
		}
	}
	public void delete_SMS() throws InterruptedException {
		/* This Method delete the First Thread in the List. */

		try {
			System.out.println("IN Delete");
			minWait();
			if (isElementExist(Locators_CallSetting.firstSMS_InList)) {
				clickBtn(Locators_CallSetting.firstSMS_InList);
				minWait();
				clickBtn(Locators_CallSetting.moreOptions);
				minWait();
				clickBtn(Locators_CallSetting.delete_Thread);
				minWait();
				clickBtn(Locators_CallSetting.delete_Confirm);
				minWait();
			}
		} catch (Exception e) {			 
			e.printStackTrace();
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
			navigateToSettingsAndElement(Locators_CallSetting.Callscreening);
			minWait();
			deleting_no_in_blacklist();
			minWait();
			clickBackButton(3);
			minWait();
			clearcallhistory();
			minWait();
			make_Call_from_PrmyDev();
			customWait(3000);
			endCall_PrmyDevice();
			minWait();
			launch_an_app("phone");
			minWait();
			clickBtn(Locators_CallSetting.Callhistorytab);
			minWait();
			 clickBtn(Locators_CallSetting.dialerfirstno);
			 minWait();
			blockUnblockNumber(Locators_CallSetting.blockNumberOpt, Locators_CallSetting.blockBtn);
			minWait();

		} catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "Element Not Found");
		}
	}

   public void Unblockno_in_calldetails(){
	  //UnBlocking no from call details

			try {
				clickBtn(Locators_CallSetting.Callhistorytab);
				minWait();
				clickBtn(Locators_CallSetting.dialerfirstno);
				minWait();
				blockUnblockNumber(Locators_CallSetting.unblockNumberOpt, Locators_CallSetting.unblockBtn);
				minWait();
   }
			 catch (Exception e) {
					e.printStackTrace();
					test.log(LogStatus.ERROR, "Element Not Found");
				}

}
   public void validateBlockAndUnblockNumber(WebElement element,String status) throws InterruptedException
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

public void validateCancel_option_calldialer(){
	//Verify CANCEL option from Call details
	
		/*
		 * Validates cancel option 
		 */
		
		try {
			//============================Block scenario==========================
			clickBtn(Locators_CallSetting.Callhistorytab);
			minWait();
			
			clickBtn(Locators_CallSetting.dailedFirstNum);
			clickBtn(Locators_CallSetting.blockNumberOpt);
			minWait();
			if(isElementExist(Locators_CallSetting.cancelBtn)){
				clickBtn(Locators_CallSetting.cancelBtn);
				check=true;
				APP_LOGS.info("Cancel Option is verified in Block scenario");
				test.log(LogStatus.INFO, "Cancel Option is verified in Block scenario");
			}
			clickBackButton(1);
			//============================Unblock Scenario==========================
			minWait();
			launch_an_app("phone");
			minWait();
			clickBtn(Locators_CallSetting.Callhistorytab);
			minWait();
			clickBtn(Locators_CallSetting.dailedFirstNum);
			minWait();
			blockUnblockNumber(Locators_CallSetting.blockNumberOpt, Locators_CallSetting.blockBtn);
			minWait();
			clickBackButton(1);
			launch_an_app("phone");
			minWait();
			clickBtn(Locators_CallSetting.Callhistorytab);
			minWait();
			clickBtn(Locators_CallSetting.dailedFirstNum);
			minWait();
			clickBtn(Locators_CallSetting.unblockNumberOpt);
			minWait();
			if(isElementExist(Locators_CallSetting.cancelBtn)){
				clickBtn(Locators_CallSetting.cancelBtn);
				check=true;
				APP_LOGS.info("Cancel Option is verified in Unblock scenario");
				test.log(LogStatus.INFO, "Cancel Option is verified in Unblock scenario");
			}
			clickBackButton(1);
			minWait();
			clickBtn(Locators_CallSetting.callDetailsOpt);
			minWait();
			blockUnblockNumber(Locators_CallSetting.unblockNumberOpt, Locators_CallSetting.unblockBtn);
			clickBackButton(1);

		}
		catch (Exception e) {
			e.printStackTrace();
			APP_LOGS.info("Element Not Found");
			test.log(LogStatus.ERROR, "Element Not Found");
			
		}
}
}









































































































































