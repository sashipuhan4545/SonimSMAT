package com.xp8.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.NoSuchElementException;

import org.openqa.selenium.WebElement;
import org.testng.asserts.SoftAssert;

import com.graphics.gui.JsonFileReaderAndWriter;
import com.relevantcodes.extentreports.LogStatus;

import io.appium.java_client.android.AndroidKeyCode;
import SikuliHelper.*;
import application.AllQA;

public class XP8_PhoneDialer_util extends BaseUtil {

	public boolean check = false;
	boolean check0 = false;
	boolean check1 = false;

	public String p_Id  	= "";    		// Primary Device Serial Number.
	public String r_Id 		= "";    		// Reference Device Serial Number.
	public String p_b_No 	= "";      	// Primary Device Build Number.
	public String r_b_No 	= "";    		// Reference Device Build Number.
	public String pryNum 	= AllQA.PRIMARYDEVMDN; // Reference Device MDN.  
	public String refNum 	= AllQA.REFERENCEDEVMDN; // Reference Device MDN.  

	public void fetch_Devices_Details() throws InterruptedException, FileNotFoundException, IOException,org.json.simple.parser.ParseException {

		minWait();
		p_Id=JsonFileReaderAndWriter.primaryDevIdReader();
		r_Id=JsonFileReaderAndWriter.ReadRefDeviceId();
		p_b_No=JsonFileReaderAndWriter.primaryDevFirmwareReader();
		r_b_No=JsonFileReaderAndWriter.ReadRefDeviceFirmWare();
	}

	public void reciveCallInRefDevice(String refNum) throws InterruptedException, IOException {
		/*
		 * Receive Call in Reference device
		 */
		try {
			if (!isElementExist(Locators_XP8_Sanity.turnOff_Airplane_PopUp)) {
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
						customWait(2000);
						if(value.contains("00000001")||value.contains("ffffffff")) {
							System.out.println("Phone is ringing so accepting call.");
							Thread.sleep(10000);
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
			APP_LOGS.info("Number dailed is: "+refNum);
		}catch (NoSuchElementException e) {
			APP_LOGS.info("Selected Page is not displayed.");
			e.printStackTrace();
		}
	}

	public void beforeExecution() throws InterruptedException, IOException
	{
		/*
		 * clear frequents,all call log  and delete contacts before test
		 */
		try {
			minWait();
			navigateTocallHistory();
			minWait();
			clickBtn(Locators_PhoneDialer.moreOptionsInCallHistory);
			minWait();
			if(isElementExist(Locators_PhoneDialer.clearCallHistoryOpt)){
				clickBtn(Locators_PhoneDialer.clearCallHistoryOpt);
				minWait();
				clickBtn(Locators_PhoneDialer.okBtn);
				customWait(3000);
				launch_an_app("contacts");
				setDefaultSavingAccount();
				minWait();
				deleteIfContactsPresent();
			}else if(!isElementExist(Locators_PhoneDialer.clearCallHistoryOpt)){
				launch_an_app("contacts");
				setDefaultSavingAccount();
				minWait();
				deleteIfContactsPresent();
			}
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "Element Not Found");
		}
	}

	public void navigateTocallHistory() throws InterruptedException, IOException
	{
		/*
		 * navigate to call History page
		 */
		try {
			minWait();
			clickBtn(Locators_PhoneDialer.settingsIcon);
			minWait();
			clickBtn(Locators_PhoneDialer.call_history);
			minWait();
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "Element Not Found");
		}
	}

	public void setDefaultSavingAccount() throws InterruptedException
	{
		/*
		 * Set default saving account as phone in contacts application
		 */
		try {
			minWait();
			clickBtn(Locators_PhoneDialer.ContactsMoreOptions);
			minWait();
			clickBtn(Locators_PhoneDialer.contactsSettingsOPt);
			minWait();
			clickBtn(Locators_PhoneDialer.contactsDefaultAccountSettings);
			minWait();
			clickBtn(Locators_PhoneDialer.contactsDefaultPhone);
			minWait();
			clickBackButton(1);
		} catch (NoSuchElementException e) {
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
			clickBtn(Locators_PhoneDialer.delete_option);
			minWait();
			if(isElementExist(Locators_PhoneDialer.zero_selected)){
				minWait();
				/*TouchAction touchaction = new TouchAction(aDriver);
				touchaction.longPress(Locators_PhoneDialer.zero_selected).perform().release();
				minWait();*/
				clickBtn(Locators_PhoneDialer.zero_selected);
				minWait();
				if(isElementExist(Locators_PhoneDialer.selectAllOpt)){
					clickBtn(Locators_PhoneDialer.selectAllOpt);
					minWait();
				}else{
					clickBackButton(1);
				}
				clickBtn(Locators_PhoneDialer.Ok_option);
				minWait();
				clickBtn(Locators_PhoneDialer.okBtn);
				customWait(5000);
				clickBackButton(2);
			}
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "Element Not Found");
		}
	}

	public void validatePhoneAppLaunch() throws InterruptedException
	{
		/*
		 * Validates Phone application Launch
		 */
		SoftAssert SA= new SoftAssert();
		try {
			minWait();
			launch_an_app("phone");
			minWait();
			if(isElementExist(Locators_PhoneDialer.phoneFrame)){
				check = true;
				APP_LOGS.info("Phone Application launched successfully");
				test.log(LogStatus.INFO, "Phone Application launched successfully");
				test.log(LogStatus.PASS, "Test case Status is PASS");
				SA.assertTrue(check, "Phone Application launched successfully");	
			}else {
				APP_LOGS.info("Phone Application not launched");
				test.log(LogStatus.ERROR, "Phone Application not launched" );
				test.log(LogStatus.FAIL, "Test case Status is FAIL");
				SA.fail();			
			}
		} catch (NoSuchElementException e) {
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

	public void validatePhoneAppHomePage() throws InterruptedException
	{
		/*
		 * Validates Phone application home page 
		 */
		SoftAssert SA= new SoftAssert();
		try {
			customWait(2000);
			if(isElementExist(Locators_PhoneDialer.favoriteEmptyPage) || isElementExist(Locators_PhoneDialer.callLogEmptyPage) || isElementExist(Locators_PhoneDialer.contactsEmptyPage))
			{
				check = true ;
				APP_LOGS.info("Phone App Home page is verified");
				test.log(LogStatus.INFO, "Phone App Home page is verified");
				test.log(LogStatus.PASS, "Test case Status is PASS");
				SA.assertTrue(check, "Phone App Home page Verfied");
			}else{
				APP_LOGS.info("Phone App Home page is not verified");
				test.log(LogStatus.ERROR, "Phone App Home page is not verified");
				test.log(LogStatus.FAIL, "Test case Status is FAIL");
				SA.fail();
			}
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			APP_LOGS.info("Element Not Found");
			test.log(LogStatus.ERROR, "Element Not Found");
			SA.fail();
		}
		SA.assertAll();
	}

	public void validateMakeACall() throws InterruptedException, IOException
	{
		/*
		 * Validates Make a call option (Clicks Make a call --> Enters number--> Dial --> validate MO call with UI )
		 */
		try {
			minWait();
			clickBtn(Locators_PhoneDialer.callLogPage);
			minWait();
			clickBtn(Locators_PhoneDialer.makeACallOption);
			minWait();
			enterTextToInputField(Locators_PhoneDialer.dialpadEditFld, refNum);
			minWait();
			clickBtn(Locators_PhoneDialer.callBtn);
			customWait(5000);
			validateMOCall("from Make a call option", refNum);
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "Element Not Found");
		}
	}

	public void validateMOCall(String call,String phone) throws InterruptedException, IOException
	{
		/*
		 * validate MO call with UI
		 */
		SoftAssert SA= new SoftAssert();
		try {
			reciveCallInRefDevice(refNum);
			customWait(2000);
			clickBtn(Locators_PhoneDialer.endCallBtn);
			minWait();
			clickBtn(Locators_PhoneDialer.firstDialedNumber);
			minWait();
			clickBtn(Locators_PhoneDialer.callDetailsOpt);
			minWait();
			String[] callDuration = Locators_PhoneDialer.durationInSec.getText().split(" ");
			System.out.println(callDuration);
			int callDur = Integer.parseInt(callDuration[0]);
			System.out.println(callDur);
			if(callDur>=0)	{
				check = true;
				APP_LOGS.info("MO call placed successfully "+call);
				test.log(LogStatus.INFO, "MO call placed successfully "+call);
				test.log(LogStatus.PASS, "Test case Status is PASS");
				SA.assertTrue(check, "MO call placed successfully "+call);
			} else	{
				APP_LOGS.info("MO call not placed "+call);
				test.log(LogStatus.ERROR, "MO call not placed "+call);
				test.log(LogStatus.FAIL, "Test case Status is FAIL");
				SA.fail();
			}	

		} catch (NoSuchElementException e) {
			e.printStackTrace();
			APP_LOGS.info("Element Not Found");
			test.log(LogStatus.ERROR, "Element Not Found");
			SA.fail();
		}
		SA.assertAll();
	}

	public void validateCallFromCallLog() throws InterruptedException, IOException
	{
		/*
		 * Validates call from call Log(Call to a number from call log)
		 */
		SoftAssert SA = new SoftAssert();
		try {
			minWait();
			clickBtn(Locators_PhoneDialer.callLogPage);
			minWait();
			if(isElementExist(Locators_PhoneDialer.recentCallLogNumber)) {
				clickBtn(Locators_PhoneDialer.callBtnInCallLog);
				customWait(5000);
				validateMOCall("from recent Call Log", refNum);
				clickBackButton(1);
				clickBtn(Locators_PhoneDialer.recentCallLogNumber);
			}else {
				test.log(LogStatus.ERROR, "Recent Call Log not available");
				SA.fail();
			}
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "Element Not Found");
		}
		SA.assertAll();
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
			if(isElementExist(Locators_PhoneDialer.permissionPopUp)) {
				minWait();
				for(int i=0;i<3;i++){
					minWait();
					clickBtn(Locators_PhoneDialer.allowBtn);
				}
			}
			validateSavedContact(name,location);
		} catch (NoSuchElementException e) {
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
			clickBtn(Locators_PhoneDialer.createNewContactOpt);
			customWait(3000);
			enterTextToInputField(Locators_PhoneDialer.nameEditFld,name);
			customWait(2000);
			clickBtn(Locators_PhoneDialer.saveOpt);
		} catch (NoSuchElementException e) {
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
			String savedContact = Locators_PhoneDialer.savedContact.getText();
			if(savedContact.contains(name)){
				check = true;
				APP_LOGS.info("Contact saved "+ location +"successfully");
				test.log(LogStatus.INFO, "Contact saved  "+ location +" successfully");
				test.log(LogStatus.PASS, "Test case Status is PASS");
				SA.assertTrue(check, "Contact saved "+ location +" successfully");
			} else	{
				APP_LOGS.info("Contact not saved" + location);
				test.log(LogStatus.ERROR, "Contact not saved" + location);
				test.log(LogStatus.FAIL, "Test case Status is FAIL");
				SA.fail();
			}		
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			APP_LOGS.info("Element Not Found");
			test.log(LogStatus.ERROR, "Element Not Found");
			SA.fail();
		}
		SA.assertAll();
	}

	public void validateCreateNewContactFromCallLog(String name) throws InterruptedException
	{
		/*
		 * Validates Create New contact from call log
		 */
		try {
			minWait();
			clickBtn(Locators_PhoneDialer.callLogPage);
			customWait(2000);
			clickBtn(Locators_PhoneDialer.recentCallLogNumber);
			minWait();
			CreateNewContactValidateSavedContact(name,"from call Log");
			minWait();
			clickBackButton(1);
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "Element Not Found");
		}
	}

	public void validateCreateNewContactFromDialpad(String Phone,String name) throws InterruptedException
	{
		/*
		 * Validates Create New contact from dialpad
		 */
		try {
			clickBtn(Locators_PhoneDialer.callLogPage);
			minWait();
			enterNumberInDialpad(Phone);
			minWait();
			CreateNewContactValidateSavedContact(name,"from Dialpad");
			minWait();
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "Element Not Found");
		}
	}

	public void enterNumberInDialpad(String Phone) throws InterruptedException
	{
		/*
		 * clicks dialpad and enters number
		 */
		try {
			clickBtn(Locators_PhoneDialer.dialpadBtn);
			customWait(2000);
			enterTextToInputField(Locators_PhoneDialer.dialpadEditFld, Phone);
			customWait(2000);
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "Element Not Found");
		}
	}

	public void validate_number_saved_with_plusSymbol(){
		SoftAssert SA = new SoftAssert();
		try {
			String number = Locators_PhoneDialer.savedContactNumber.getText();
			minWait();
			if(number.contains("+")) {
				check = true;
				clickBackButton(3);
				APP_LOGS.info("Contact saved with '+' successfully");
				test.log(LogStatus.INFO, "Contact saved with '+' successfully");
				test.log(LogStatus.PASS, "Test case Status is PASS");
				SA.assertTrue(check, "Contact saved with '+' successfully");
			} else	{
				APP_LOGS.info("Failed to save Contact with '+'" );
				test.log(LogStatus.ERROR, "Failed to save Contact with '+'");
				test.log(LogStatus.FAIL, "Test case Status is FAIL");
				SA.fail();
			}
		} catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "Element Not Found");
		}
		SA.assertAll();
	}

	public void validate_contacts_added_in_list() {
		SoftAssert SA = new SoftAssert();
		try {
			clickBtn(Locators_PhoneDialer.contactPage);
			minWait();
			if(isElementExist(Locators_PhoneDialer.contact_one) && isElementExist(Locators_PhoneDialer.contact_two)) {
				check = true;
				clickBackButton(1);
				APP_LOGS.info("Added list of contacts are available");
				test.log(LogStatus.INFO, "Added list of contacts are available");
				test.log(LogStatus.PASS, "Test case Status is PASS");
				SA.assertTrue(check, "Added list of contacts are available");
			} else	{
				APP_LOGS.info("Added list of contacts are not available" );
				test.log(LogStatus.ERROR, "Added list of contacts are not available");
				test.log(LogStatus.FAIL, "Test case Status is FAIL");
				SA.fail();
			}
		} catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "Element Not Found");
		}
		SA.assertAll();
	}

	public void validate_frequent_list() {
		try {
			minWait();
			clickBtn(Locators_PhoneDialer.callLogPage);
			minWait();
			clickBtn(Locators_PhoneDialer.callBtnInCallLog);
			customWait(3000);
			clickBtn(Locators_PhoneDialer.endCallBtn);
			minWait();
			clickBtn(Locators_PhoneDialer.contactPage);
			minWait();
			clickBtn(Locators_PhoneDialer.contact_two);
			minWait();
			clickBtn(Locators_PhoneDialer.fav_add_star_option);
			clickBackButton(1);
			minWait();
			clickBtn(Locators_PhoneDialer.FrequentPage);
			minWait();
			validate_Fav_contacts_added_in_list();
		} catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "Element Not Found");
		}
	}

	public void validate_Fav_contacts_added_in_list() {
		SoftAssert SA = new SoftAssert();
		try {
			minWait();
			if(isElementExist(Locators_PhoneDialer.contact_one) && isElementExist(Locators_PhoneDialer.contact_two)) {
				check = true;
				APP_LOGS.info("Favourite/Frequent list of contacts are available");
				test.log(LogStatus.INFO, "Favourite/Frequent list of contacts are available");
				test.log(LogStatus.PASS, "Test case Status is PASS");
				SA.assertTrue(check, "Favourite/Frequent list of contacts are available");
			} else	{
				APP_LOGS.info("Favourite/Frequent list of contacts are not available" );
				test.log(LogStatus.ERROR, "Favourite/Frequent list of contacts are not available");
				test.log(LogStatus.FAIL, "Test case Status is FAIL");
				SA.fail();
			}
		} catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "Element Not Found");
		}
		SA.assertAll();
	}

	public void validate_clear_frequents() {
		SoftAssert SA = new SoftAssert();
		try {
			minWait();
			clickBtn(Locators_PhoneDialer.contactPage);
			minWait();
			clickBtn(Locators_PhoneDialer.contact_two);
			minWait();
			clickBtn(Locators_PhoneDialer.fav_add_star_option);
			clickBackButton(1);
			clickBtn(Locators_PhoneDialer.FrequentPage);
			minWait();
			clickBtn(Locators_PhoneDialer.settingsIcon);
			minWait();
		//	SikuliHelper.clickByImage(SikuliConstants.ClearFrequents);
			clickBtn(Locators_PhoneDialer.clearFrequentsOpt);
			minWait();
			clickBtn(Locators_PhoneDialer.okBtn);
	//		SikuliHelper.clickByImage(SikuliConstants.OK_Btn);
			if(isElementExist(Locators_PhoneDialer.favoriteEmptyPage)) {
				check = true;
				APP_LOGS.info("Favourite/Frequent list cleared successfully");
				test.log(LogStatus.INFO, "Favourite/Frequent list cleared successfully");
				test.log(LogStatus.PASS, "Test case Status is PASS");
				SA.assertTrue(check, "Favourite/Frequent list cleared successfully");
			} else	{
				APP_LOGS.info("Failed to clear Favourite/Frequent list" );
				test.log(LogStatus.ERROR, "Failed to clear Favourite/Frequent list");
				test.log(LogStatus.FAIL, "Test case Status is FAIL");
				SA.fail();
			}
		} catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "Element Not Found");
		}
		SA.assertAll();
	}

	public void validate_search(String ContactName) {
		SoftAssert SA = new SoftAssert();
		try {
			clickBtn(Locators_PhoneDialer.callLogPage);
			minWait();
		//	enterTextToInputField(Locators_PhoneDialer.search_bar, ContactName);
		//	SikuliHelper.typeTextIntoTextBox(SikuliConstants.Search_contacts, ContactName);
			clickBtn(Locators_PhoneDialer.searchContactsFld);
			customWait(3000);
			Runtime.getRuntime().exec("adb -s "+ p_Id +" shell input text "+ ContactName);
			customWait(2000);
			if(isElementExist(Locators_PhoneDialer.searched_contact)) {
				String searched_contact = Locators_PhoneDialer.searched_contact.getText();
				minWait();
				if(ContactName.equals(searched_contact)) {
					check = true;
					clickBackButton(2);
					APP_LOGS.info("Searched contact is available,search option validated successfully");
					test.log(LogStatus.INFO, "Searched contact is available,search option validated successfully");
					test.log(LogStatus.PASS, "Test case Status is PASS");
					SA.assertTrue(check, "Searched contact is available,search option validated successfully");
				} else	{
					APP_LOGS.info("Failed to validate search option" );
					test.log(LogStatus.ERROR, "Failed to validate search option");
					test.log(LogStatus.FAIL, "Test case Status is FAIL");
					SA.fail();
				}
			}else {
				APP_LOGS.info("Contact not available" );
				test.log(LogStatus.ERROR, "Contact not available");
				test.log(LogStatus.FAIL, "Test case Status is FAIL");
				SA.fail();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "Element Not Found");
		}
		SA.assertAll();
	}

	public void validate_recent_contact_in_list(String ContactName) {
		SoftAssert SA = new SoftAssert();
		try {
			clickBtn(Locators_PhoneDialer.callLogPage);
			minWait();
			String recent_contact = Locators_PhoneDialer.recentCallLogNumber.getText();
			minWait();
			if(recent_contact.equals(ContactName)) {
				check = true;
				APP_LOGS.info("Recently used contact details displayed at the top of phone dialer call logs ");
				test.log(LogStatus.INFO, "Recently used contact details displayed at the top of phone dialer call logs");
				test.log(LogStatus.PASS, "Test case Status is PASS");
				SA.assertTrue(check, "Recently used contact details displayed at the top of phone dialer call logs");
			} else	{
				APP_LOGS.info("Recently used contact details is not displayed at the top of phone dialer call logs" );
				test.log(LogStatus.ERROR, "Recently used contact details is not displayed at the top of phone dialer call logs");
				test.log(LogStatus.FAIL, "Test case Status is FAIL");
				SA.fail();	
			}
		} catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "Element Not Found");
		}
		SA.assertAll();
	}
	
	public void validate_send_message(String textMessage) {
		SoftAssert SA = new SoftAssert();
		try {
			clickBtn(Locators_PhoneDialer.callLogPage);
			minWait();
			clickBtn(Locators_PhoneDialer.recentCallLogNumber);
			minWait();
			clickBtn(Locators_PhoneDialer.Send_message);
			minWait();
			if(isElementExist(Locators_PhoneDialer.permissionPopUp)) {
				minWait();
				for(int i=0;i<3;i++){
					minWait();
					clickBtn(Locators_PhoneDialer.allowBtn);
				}
			}
			enterTextToInputField(Locators_PhoneDialer.messageEditFld, textMessage);
			customWait(3000);
		/*	clickBtn(Locators_PhoneDialer.messageEditFld);
			customWait(2000);
			Runtime.getRuntime().exec("adb -s "+ p_Id +" shell input text "+ textMessage);*/
			clickBtn(Locators_PhoneDialer.sendMessage);
			customWait(2000);
			if(isElementExist(Locators_PhoneDialer.messageSentNow))
			{
				check = true;
				APP_LOGS.info("Message sent successfully ");
				test.log(LogStatus.INFO, "Message sent successfully ");
				test.log(LogStatus.PASS, "Test case Status is PASS");
				SA.assertTrue(check, "Message sent successfully ");	
			}else {
				APP_LOGS.info("Message not sent");
				test.log(LogStatus.ERROR, "Message not sent");
				test.log(LogStatus.FAIL, "Test case Status is FAIL");
				SA.fail();
			}	
		} catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "Element Not Found");
		}
		SA.assertAll();
	}
	
	public void validate_add_new_contact(String Phone,String name) {
		try {
			clickBtn(Locators_PhoneDialer.callLogPage);
			minWait();
			enterNumberInDialpad(Phone);
			minWait();
			clickBtn(Locators_PhoneDialer.Add_to_contact);
			minWait();
			CreateNewContactAndSave(name);
			customWait(2000);
			if(isElementExist(Locators_PhoneDialer.permissionPopUp)) {
				minWait();
				for(int i=0;i<3;i++){
					minWait();
					clickBtn(Locators_PhoneDialer.allowBtn);
				}
			}
			clickBackButton(2);
			minWait();
			clickBtn(Locators_PhoneDialer.contactPage);
			minWait();
			clickBtn(Locators_PhoneDialer.Contact_three);
			minWait();
			validateSavedContact(name,"");
			clickBackButton(1);
		} catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "Element Not Found");
		}
	}
	
	public void remove_GoogleAcccount() {
		//remove added google Account if any 
		try {
			scrollToText("Users & accounts");
//			clickOnAccounts();
			minWait();
			if(isElementExist(Locators_DeviceStability.connectedAccount)) {
				System.out.println("Account is present");
				minWait();
				clickBtn(Locators_DeviceStability.connectedAccount);
				minWait();
				clickBtn(Locators_DeviceStability.REMOVE_ACCOUNT);
				minWait();
				clickBtn(Locators_DeviceStability.REMOVE_ACCOUNT);
				customWait(3000);
			}
			else {
				System.out.println("No Google account present");
			}


		} catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "Element Not Found");
		}
	}
	
	public void validate_link_contact() {
		SoftAssert SA = new SoftAssert();
		try {
			clickBtn(Locators_PhoneDialer.contactPage);
			minWait();
			clickBtn(Locators_PhoneDialer.Contact_three);
			minWait();
			clickBtn(Locators_PhoneDialer.contact_more_option);
			minWait();
			clickBtn(Locators_PhoneDialer.Link);
			minWait();
			clickBtn(Locators_PhoneDialer.contact_two);
			minWait();
			if(isElementExist(Locators_PhoneDialer.contact_linked_1) && isElementExist(Locators_PhoneDialer.contact_linked_2)) {
				check = true;
				APP_LOGS.info("Contact linked successfully");
				test.log(LogStatus.INFO, "Contact linked successfully");
				test.log(LogStatus.PASS, "Test case Status is PASS");
				SA.assertTrue(check, "Contact linked successfully");	
			}else {
				APP_LOGS.info("Failed to link Contact");
				test.log(LogStatus.ERROR, "Failed to link Contact");
				test.log(LogStatus.FAIL, "Test case Status is FAIL");
				SA.fail();
			}
			clickBackButton(1);
		} catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "Element Not Found");
		}
		SA.assertAll();
	}
	
	public void add_picture_to_contact() {
		try {
			clickBtn(Locators_PhoneDialer.contactPage);
			minWait();
			clickBtn(Locators_PhoneDialer.contact_one);
			minWait();
			clickBtn(Locators_PhoneDialer.edit_contact);
			minWait();
			clickBtn(Locators_PhoneDialer.camera_image);
			minWait();
			validate_take_photo();
			minWait();
			validate_remove_photo();
			minWait();
			validate_Choose_photo();
			clickBackButton(1);
		} catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "Element Not Found");
		}
	}
	
	
	public void validate_take_photo() {
		SoftAssert SA = new SoftAssert();
		try {
			clickBtn(Locators_PhoneDialer.Take_photo);
			customWait(2000);
			if(isElementExist(Locators_PhoneDialer.permissionPopUp)) {
				minWait();
				for(int i=0;i<3;i++){
					minWait();
					clickBtn(Locators_PhoneDialer.allowBtn);
				}
			}
			
			clickBtn(Locators_PhoneDialer.photo_capture_Btn);
			customWait(8000);
			clickBtn(Locators_PhoneDialer.photo_add_Btn);
			customWait(5000);
			System.out.println("check2");
			clickBtn(Locators_PhoneDialer.done_btn);
			customWait(5000);
			
			if(isElementExist(Locators_PhoneDialer.added_image)) {
				check = true;
				APP_LOGS.info("Image added to saved contact Successfully,Take photo option verified");
				test.log(LogStatus.INFO, "Image added to saved contact Successfully,Take photo option verified");
				test.log(LogStatus.PASS, "Test case Status is PASS");
				SA.assertTrue(check, "Image added to saved contact Successfully,Take photo option verified");	
			}else {
				APP_LOGS.info("Failed to add image to saved Contact");
				test.log(LogStatus.ERROR, "Failed to add image to saved Contact");
				test.log(LogStatus.FAIL, "Test case Status is FAIL");
				SA.fail();
			}
		} catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "Element Not Found");
		}
		SA.assertAll();
	}
	
	public void validate_remove_photo() {
		SoftAssert SA = new SoftAssert();
		try {
			clickBtn(Locators_PhoneDialer.added_image);
			customWait(2000);
			clickBtn(Locators_PhoneDialer.Remove_photo);
			minWait();
			if(isElementExist(Locators_PhoneDialer.contact_image_view_without_image)) {
				check = true;
				APP_LOGS.info("Image removed from saved contact Successfully,Remove photo option verified");
				test.log(LogStatus.INFO, "Image removed from saved contact Successfully,Remove photo option verified");
				test.log(LogStatus.PASS, "Test case Status is PASS");
				SA.assertTrue(check, "Image removed from saved contact Successfully,Remove photo option verified");	
			}else {
				APP_LOGS.info("Failed to remove image from saved Contact");
				test.log(LogStatus.ERROR, "Failed to remove image from saved Contact");
				test.log(LogStatus.FAIL, "Test case Status is FAIL");
				SA.fail();
			}
		} catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "Element Not Found");
		}
		SA.assertAll();
	}

	public void validate_Choose_photo() {
		SoftAssert SA = new SoftAssert();
		try {
			launch_an_app("camera");
			minWait();
			clickBtn(Locators_PhoneDialer.photo_capture_Btn);
			customWait(5000);
			if(isElementExist(Locators_PhoneDialer.captured_image_view_in_camera)) {
				launch_an_app("phone");
				minWait();
				clickBtn(Locators_PhoneDialer.contactPage);
				minWait();
				clickBtn(Locators_PhoneDialer.contact_one);
				minWait();
				clickBtn(Locators_PhoneDialer.edit_contact);
				minWait();
				clickBtn(Locators_PhoneDialer.contact_image_view_without_image);
				minWait();
				clickBtn(Locators_PhoneDialer.Choose_photo);
				minWait();
				clickBtn(Locators_PhoneDialer.Camera);
				minWait();
				clickBtn(Locators_PhoneDialer.captured_image);
				customWait(2000);
				clickBtn(Locators_PhoneDialer.done_btn);
				customWait(2000);
				if(isElementExist(Locators_PhoneDialer.added_image)) {
					check = true;
					clickBtn(Locators_PhoneDialer.saveOpt);
					APP_LOGS.info("Image added to saved contact Successfully,Choose photo option verified");
					test.log(LogStatus.INFO, "Image added to saved contact Successfully,Choose photo option verified");
					test.log(LogStatus.PASS, "Test case Status is PASS");
					SA.assertTrue(check, "Image added to saved contact Successfully,Choose photo option verified");	
				}else {
					APP_LOGS.info("Failed to add image to saved Contact");
					test.log(LogStatus.ERROR, "Failed to add image to saved Contact");
					test.log(LogStatus.FAIL, "Test case Status is FAIL");
					SA.fail();
				}
				
			}else {
				test.log(LogStatus.ERROR, "Failed to capture image");
				test.log(LogStatus.FAIL, "Test case Status is FAIL");
				SA.fail();
			}
		} catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "Element Not Found");
		}
		SA.assertAll();
	}

	public void validateKeepEditingAndDiscard(String Phone) throws InterruptedException, IOException{
		/*
		 * Enters number in dialpad and validates keep editing and discard option
		 */
		try
		{
			clickBtn(Locators_PhoneDialer.callLogPage);
			minWait();
			enterNumberInDialpad(Phone);
			minWait();
			clickBtn(Locators_PhoneDialer.createNewContactOpt);
			minWait();
			clickBackButton(2);
			minWait();
			KeepEditingAndDiscard(Locators_PhoneDialer.CancelOpt, Locators_PhoneDialer.nameEditFld);
			minWait();
			clickBackButton(1);
			minWait();
			KeepEditingAndDiscard(Locators_PhoneDialer.discardBtn, Locators_PhoneDialer.dialpadEditFld);
			minWait();
			clickBackButton(2);
		}catch (NoSuchElementException e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "Element Not Found");
		}
	}

	public void KeepEditingAndDiscard(WebElement element,WebElement subElement) throws InterruptedException, IOException
	{
		/*
		 * clicks on keep editing/Discard element and validates
		 */
		SoftAssert SA= new SoftAssert();
		try
		{
			String option = element.getText();
			minWait();
			element.click();
			customWait(2000);
			if(isElementExist(subElement)){
				check = true;
				APP_LOGS.info(option + " Option is verified successfully");
				test.log(LogStatus.INFO, option + " Option is verified successfully");
				test.log(LogStatus.PASS, "Test case Status is PASS");
				SA.assertTrue(check,option + " Option is verified successfully");
			}
			else{
				APP_LOGS.info(option + " Option is not available");
				test.log(LogStatus.INFO,option+ " Option is not available");
				test.log(LogStatus.FAIL, "Test case Status is FAIL");
				SA.fail(option +" Option is not available");
			}
		}catch (NoSuchElementException e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "Element Not Found");
		}
		SA.assertAll();
	}
	
	public void validate_block_option_in_call_log() {
		SoftAssert SA = new SoftAssert();
		try {
			clickBtn(Locators_PhoneDialer.callLogPage);
			minWait();
			clickBtn(Locators_PhoneDialer.contact_one);
			minWait();
			if(isElementExist(Locators_PhoneDialer.blockNumberOpt)) {
				check = true;
				APP_LOGS.info("Block number option is present in call log page");
				test.log(LogStatus.INFO, "Block number option is present in call log page");
				test.log(LogStatus.PASS, "Test case Status is PASS");
				SA.assertTrue(check, "Block number option is present in call log page");	
			}else {
				APP_LOGS.info("Block number option is not present in call log page");
				test.log(LogStatus.ERROR, "Block number option is not present in call log page");
				test.log(LogStatus.FAIL, "Test case Status is FAIL");
				SA.fail();
			}
		} catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "Element Not Found");
		}
		SA.assertAll();
	}
	
	public void navigate_to_block_number_in_call_settings() {
		try {
			clickBtn(Locators_PhoneDialer.settingsIcon);
			minWait();
			clickBtn(Locators_PhoneDialer.Settings);
			minWait();
			clickBtn(Locators_PhoneDialer.Call_screening);
			minWait();
			clickBtn(Locators_PhoneDialer.Manage_black_list);
		} catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "Element Not Found");
		}
	}
	
	public void blockNumberAndValidate() {
		SoftAssert SA = new SoftAssert();
		try {
			clickBtn(Locators_PhoneDialer.add_a_number_to_block);
			minWait();
			System.out.println(Locators_PhoneDialer.blockBtn.isEnabled());
			if(Locators_PhoneDialer.blockBtn.isEnabled()) {
				APP_LOGS.info("Block option is highlighted before entering Number to block");
				test.log(LogStatus.ERROR, "Block option is highlighted before entering Number to block");
				test.log(LogStatus.FAIL, "Test case Status is FAIL");
				SA.fail();
			}else {
				check = true;
				APP_LOGS.info("Block option is not highlighted before entering Number to block");
				test.log(LogStatus.INFO, "Block option is not highlighted before entering Number to block");
				test.log(LogStatus.PASS, "Test case Status is PASS");
				SA.assertTrue(check, "Block option is not highlighted before entering Number to block");	
			}
			
			enterTextToInputField(Locators_PhoneDialer.phone_number_fld_to_block, refNum);
			customWait(2000);
			clickBtn(Locators_PhoneDialer.blockBtn);
			if(isElementExist(Locators_PhoneDialer.blocked_number)) {
				check = true;
				APP_LOGS.info("Number blocked successfully from call settings,blocked number is available in list");
				test.log(LogStatus.INFO, "Number blocked successfully from call settings,blocked number is available in list");
				test.log(LogStatus.PASS, "Test case Status is PASS");
				SA.assertTrue(check, "Number blocked successfully from call settings,blocked number is available in list");	
			}else {
				APP_LOGS.info("Failed to block number from call settings");
				test.log(LogStatus.ERROR, "Failed to block number from call settings");
				test.log(LogStatus.FAIL, "Test case Status is FAIL");
				SA.fail();
			}
		} catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "Element Not Found");
		}
		SA.assertAll();
	}
	
	public void unblockNumberAndValidate() {
		SoftAssert SA = new SoftAssert();
		try {
			if(isElementExist(Locators_PhoneDialer.blocked_number)) {
				clickBtn(Locators_PhoneDialer.delete_unblock_number_option);
				minWait();
				clickBtn(Locators_PhoneDialer.CancelOpt);
				if(isElementExist(Locators_PhoneDialer.blocked_number)) {
					check = true;
					APP_LOGS.info("Cancel option in Unblock number pop up is verified successfully");
					test.log(LogStatus.INFO, "Cancel option in Unblock number pop up is verified successfully");
					test.log(LogStatus.PASS, "Test case Status is PASS");
					SA.assertTrue(check, "Cancel option in Unblock number pop up is verified successfully");	
				}else {
					APP_LOGS.info("Failed to validate Cancel option in Unblock number pop up");
					test.log(LogStatus.ERROR, "Failed to validate Cancel option in Unblock number pop up");
					test.log(LogStatus.FAIL, "Test case Status is FAIL");
					SA.fail();
				}
				
				clickBtn(Locators_PhoneDialer.delete_unblock_number_option);
				minWait();
				if(!isElementExist(Locators_PhoneDialer.blocked_number)) {
					check = true;
					APP_LOGS.info("Number Unblocked successfully from call settings");
					test.log(LogStatus.INFO, "Number Unblocked successfully from call settings");
					test.log(LogStatus.PASS, "Test case Status is PASS");
					SA.assertTrue(check, "Number Unblocked successfully from call settings");	
				}else {
					APP_LOGS.info("Failed to Unblock number from call settings");
					test.log(LogStatus.ERROR, "Failed to Unblock number from call settings");
					test.log(LogStatus.FAIL, "Test case Status is FAIL");
					SA.fail();
				}
			}else {
				APP_LOGS.info("Blocked number is not available in list");
				test.log(LogStatus.ERROR, "Blocked number is not available in list");
				test.log(LogStatus.FAIL, "Test case Status is FAIL");
				SA.fail();
			}
		} catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "Element Not Found");
		}
		SA.assertAll();
	}
	
	
}
