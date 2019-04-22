package com.aosp.Utils;

import java.io.IOException;
import java.util.NoSuchElementException;


import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.touch.TouchActions;
import org.testng.asserts.SoftAssert;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.sun.jna.platform.win32.WinNT.LOGICAL_PROCESSOR_RELATIONSHIP;

import com.xp5S.util.BaseUtil;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.AndroidKeyCode;

public class Contacts_Util extends BaseUtil{

	public boolean check = false;
	boolean check0 = false;
	boolean check1 = false;
	boolean check2 = false;
	boolean check3 = false;
	boolean check4 = false;
	boolean check5 = false;
	public static ExtentReports extent;
	public static ExtentTest test;

	public void deleteIfContactsPresent() throws InterruptedException
	{
		/*
		 * Delete contacts if exist before test
		 */
		try {
			if(!isElementExist(Locators_Contacts.addContacts))
			{
				minWait();
				clickMenuAndElement(Locators_Contacts.selectOpt);
				minWait();
				clickMenuAndElement(Locators_Contacts.selectAllOpt);
				minWait();
				clickMenuAndElement(Locators_Contacts.deleteOpt);
				minWait();
				Locators_Contacts.deleteBtn.click();
				minWait();
			}

		} catch (NoSuchElementException e) {
			e.printStackTrace();
			APP_LOGS.info("Element Not Found");
			test.log(LogStatus.ERROR, "Element Not Found");
		}
	}

	public void validateContactsAppLaunch() throws InterruptedException {
		/*
		 * Validates Contacts application launch
		 */
		SoftAssert SA= new SoftAssert();
		customWait(4000);
		try {
			minWait();
			if (isElementExist(Locators_Contacts.contactsIcon)) {
				check = true;
				APP_LOGS.info("Contacts Application validation is successful.");
				test.log(LogStatus.INFO, "Contacts Application validation is successful.");
				SA.assertTrue(check, "Contacts Application Launched Successfully");	
			} else if(isElementExist(Locators_Contacts.findContactsoption)) {
				check = true;
				APP_LOGS.info("Contacts Application validation is successful.");
				test.log(LogStatus.INFO, "Contacts Application validation is successful.");
				SA.assertTrue(check, "Contacts Application Launched Successfully");	
			}
			else {
				APP_LOGS.info("Contacts Application Launched validation is Unsuccessful.");
				test.log(LogStatus.ERROR, "Contacts Application Launched validation is Unsuccessful." );
				SA.fail("Contacts Application not launched");			
			}

		}
		catch (NoSuchElementException e) {
			e.printStackTrace();
			APP_LOGS.info("Element Not Found");
			test.log(LogStatus.ERROR, "No Such Element Found");
			SA.fail();
		}
		SA.assertAll();
	}

	public void validateContactsHomePage() throws InterruptedException
	{
		/*
		 * Validates Contacts application home page without any contacts
		 */
		SoftAssert SA= new SoftAssert();
		customWait(2000);
		try {
			minWait();
			if(isElementExist(Locators_Contacts.addContacts))
			{
				if(isElementExist(Locators_Contacts.importContacts)) 
				{
					check = true ;
					APP_LOGS.info("Contacts App Home page is verified");
					test.log(LogStatus.INFO, "Contacts App Home page is verified");
					SA.assertTrue(check, "Contacts App Home page Verfied");
				}
				else
				{
					APP_LOGS.info("Imports Contacts Option not available");
					test.log(LogStatus.INFO, "Imports Contacts Option not available");
					SA.fail("Contacts App Home page not Verfied- Import Contacts Option NA");
				}
			}
			else
			{
				APP_LOGS.info("Add Contacts Option not available");
				test.log(LogStatus.INFO, "Add Contacts Option not available");
				SA.fail("Contacts App Home page not Verfied- Add Contacts Option NA");
			}     
		}
		catch (NoSuchElementException e) {
			e.printStackTrace();
			APP_LOGS.info("Element Not Found");
			test.log(LogStatus.ERROR, "Element Not Found");
			SA.fail();
		}
		SA.assertAll();
	}	

	public void addContactWithMinFields(WebElement element,String Name,String Phone) throws InterruptedException, IOException
	{
		/*
		 * Add's Contact with Image,Name,Phone Number and validates
		 */
		SoftAssert SA= new SoftAssert();
		try {
			if(isElementExist(element)) {
				minWait();
				element.click();
				Locators_Contacts.changePhotoOpt.click();
				minWait();
				Locators_Contacts.takePhotoOpt.click();
				minWait();
				Locators_Contacts.captureOpt.click();
				minWait();
				Locators_Contacts.imageOkOpt.click();
				minWait();
				enterTextToInputField(Locators_Contacts.nameField, Name);
				customWait(3000);
				enterTextToInputField(Locators_Contacts.phoneField, Phone);
				customWait(3000);
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_MENU);
				minWait();
				Locators_Contacts.saveBtn.click();
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
				customWait(3000);
				Runtime.getRuntime().exec("adb shell input text  "+ Name);
				customWait(3000);
				validateAddedContact(Name);
				minWait();
				check = true;
				APP_LOGS.info("Contact Added Successfully");
				test.log(LogStatus.INFO, "Contact Added Successfully");
				SA.assertTrue(check, "Contact Added Successfully");
			}
			else {
				APP_LOGS.info("Element Not Available");
				test.log(LogStatus.INFO, "Element Not Available");
				SA.fail("Add Contacts option not available");
			}
		}catch (NoSuchElementException e) {
			e.printStackTrace();
			APP_LOGS.info("Element Not Found");
			test.log(LogStatus.ERROR, "Element Not Found");
			SA.fail();
		}
		SA.assertAll();
	}

	public void editContactWithMinFields(String Name,String editedName) throws InterruptedException, IOException
	{
		/*
		 * edit Contact --> Image and name  (Min Fields contact scenario)
		 */
		SoftAssert SA= new SoftAssert();
		try {
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			minWait();
			searchContact(Name);
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_MENU);
			customWait(2000);
			Locators_Contacts.editOpt.click();
			minWait();
			Locators_Contacts.changePhotoOpt.click();
			minWait();
			Locators_Contacts.removePhotoOpt.click();
			minWait();
			Locators_Contacts.editNameFldMinContacts.clear();
			enterTextToInputField(Locators_Contacts.nameField, editedName);
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_MENU);
			minWait();
			Locators_Contacts.saveBtn.click();
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			customWait(5000);
			Runtime.getRuntime().exec("adb shell input text Automation");
			minWait();
			validateAddedContact(editedName);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			check = true;
			APP_LOGS.info("Contact Edited and saved Successfully");
			test.log(LogStatus.INFO, "Contact Edited and saved Successfully");
			SA.assertTrue(check, "Contact Edited and saved Successfully");
		}catch (NoSuchElementException e) {
			e.printStackTrace();
			APP_LOGS.info("Element Not Found");
			test.log(LogStatus.ERROR, "Element Not Found");
			SA.fail();
		}
		SA.assertAll();
	}

	public void validateAddedContact(String Name) throws InterruptedException, IOException
	{
		/*
		 * validates added contact
		 */
		SoftAssert SA= new SoftAssert();
		try {
			customWait(4000);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
			customWait(2000);
			if(!isElementExist(Locators_Contacts.noContacts))
			{
				String actualString = Locators_Contacts.addedContact.getText();
				String expectedString = Name;
				System.out.println(actualString);
				System.out.println(expectedString);
				if(actualString.contains(expectedString))
				{
					minWait();
					aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
					check = true;
					/*APP_LOGS.info("Added Contact is avialable");
					test.log(LogStatus.INFO, "Added Contact is avialable");
					SA.assertTrue(check, "Added Contact is avialable");*/
				}
			}
		}catch (NoSuchElementException e) {
			e.printStackTrace();
			APP_LOGS.info("Element Not Found");
			test.log(LogStatus.ERROR, "Element Not Found");
			SA.fail();
		}
		SA.assertAll();
	}

	public void addContactWithAllFields(String namePrefix,String firstName,String middleName,String lastName,String nameSuffix,String phoneticLastName,String phoneticMiddleName,String phoneticFirstName,String nickName,String company,String title,String phone,String SIP,String Email ,String address,String IM,String website,String Notes) throws InterruptedException, IOException
	{
		/*
		 * add contact with all fields and validate
		 */
		SoftAssert SA= new SoftAssert();
		aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_MENU);
		try {
			if(isElementExist(Locators_Contacts.addContactOpt)) {
				minWait();
				Locators_Contacts.addContactOpt.click();
				minWait();
				ScrollToElement(Locators_Contacts.scrollpage, "More fields");
				Locators_Contacts.moreFldOpt.click();
				minWait();
				aDriver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().className(\"android.widget.ScrollView\")).scrollIntoView(new UiSelector().text(\"Name\"))");
				minWait();
				Locators_Contacts.changePhotoOpt.click();
				minWait();
				Locators_Contacts.takePhotoOpt.click();
				minWait();
				Locators_Contacts.captureOpt.click();
				minWait();
				Locators_Contacts.imageOkOpt.click();
				minWait();
				Locators_Contacts.expandFld.click();
				minWait();
				enterTextToInputField(Locators_Contacts.namePrefixFld,namePrefix);
				minWait();
				enterTextToInputField(Locators_Contacts.firstNameFld,firstName);
				minWait();
				enterTextToInputField(Locators_Contacts.middleNameFld,middleName);
				minWait();
				enterTextToInputField(Locators_Contacts.lastNameFld,lastName);
				minWait();
				ScrollToElement(Locators_Contacts.scrollpage, "Title");
				enterTextToInputField(Locators_Contacts.nameSuffixFld,nameSuffix);
				minWait();
				Locators_Contacts.expandFld.click();
				enterTextToInputField(Locators_Contacts.phoneticLastName,phoneticLastName);
				minWait();
				enterTextToInputField(Locators_Contacts.phoneticMiddleName,phoneticMiddleName);
				minWait();
				enterTextToInputField(Locators_Contacts.phoneticFirstName,phoneticFirstName);
				minWait();
				enterTextToInputField(Locators_Contacts.nickNameFld,nickName);
				minWait();
				ScrollToElement(Locators_Contacts.scrollpage, "SIP");
				enterTextToInputField(Locators_Contacts.companyFld,company);
				minWait();
				enterTextToInputField(Locators_Contacts.titleFld,title);
				minWait();
				enterTextToInputField(Locators_Contacts.phoneField,phone);
				minWait();
				ScrollToElement(Locators_Contacts.scrollpage, "Email");
				enterTextToInputField(Locators_Contacts.sipFld,SIP);
				minWait();
				enterTextToInputField(Locators_Contacts.emailFld,Email);
				minWait();
				ScrollToElement(Locators_Contacts.scrollpage, "Address");
				enterTextToInputField(Locators_Contacts.addressFld,address);
				minWait();
				ScrollToElement(Locators_Contacts.scrollpage, "Website");
				enterTextToInputField(Locators_Contacts.IMFld,IM);
				minWait();
				enterTextToInputField(Locators_Contacts.websiteFld,website);
				minWait();
				ScrollToElement(Locators_Contacts.scrollpage, "Notes");
				enterTextToInputField(Locators_Contacts.notesFld,Notes);
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_MENU);
				minWait();
				Locators_Contacts.saveBtn.click();
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
				customWait(5000);
				Runtime.getRuntime().exec("adb shell input text Sonim");
				customWait(2000);
				validateAddedContact(namePrefix);
				check = true;
				APP_LOGS.info("Contact Added with all fields Successfully");
				test.log(LogStatus.INFO, "Contact Added with all fields Successfully");
				SA.assertTrue(check, "Contact Added with all fields Successfully");
			}
			else {
				APP_LOGS.info("Element Not Available");
				test.log(LogStatus.INFO, "Element Not Available");
				SA.fail("Add Contacts option not available");
			}
		}catch (NoSuchElementException e) {
			e.printStackTrace();
			APP_LOGS.info("Element Not Found");
			test.log(LogStatus.ERROR, "Element Not Found");
			SA.fail();
		}
		SA.assertAll();
	}

	public void editContactWithAllFields(String Name,String editednamePrefix) throws InterruptedException, IOException 
	{
		SoftAssert SA= new SoftAssert();
		try {
			/*
			 * Edit Contact --> Image and Nameprefix (All fields contact scenario)		 
			 */
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			minWait();
			searchContact(Name);
			minWait();
			clickMenuAndElement(Locators_Contacts.editOpt);
			minWait();
			Locators_Contacts.changePhotoOpt.click();
			minWait();
			Locators_Contacts.takeNewPhotoOpt.click();
			minWait();
			Locators_Contacts.captureOpt.click();
			minWait();
			Locators_Contacts.imageOkOpt.click();
			minWait();
			Locators_Contacts.expandFld.click();
			Locators_Contacts.editNameAllFldContacs.clear();
			enterTextToInputField(Locators_Contacts.namePrefixFld,editednamePrefix);
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_MENU);
			Locators_Contacts.saveBtn.click();
			Runtime.getRuntime().exec("adb shell input text Sonim");
			validateAddedContact(editednamePrefix);
			check = true;
			APP_LOGS.info("Contact Edited and saved Successfully");
			test.log(LogStatus.INFO, "Contact Edited and saved Successfully");
			SA.assertTrue(check, "Contact Edited and saved Successfully");

		}catch (NoSuchElementException e) {
			e.printStackTrace();
			APP_LOGS.info("Element Not Found");
			test.log(LogStatus.ERROR, "Element Not Found");
			SA.fail();
		}
		SA.assertAll();
	}

	public void callFromContactsAndValidate(String Name) throws InterruptedException, IOException
	{
		/*
		 * Call to the saved contact from Contacts and Validate the MO Call
		 */
		SoftAssert SA= new SoftAssert();
		try {
			searchContact(Name);
			minWait();
			if(!isElementExist(Locators_Contacts.noContacts))
			{
				customWait(3000);
				Locators_Contacts.callOpt.click();
				customWait(15000);
				if(isElementExist(Locators_Contacts.dailingOpt)) 
				{
					customWait(5000);
					aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_ENDCALL);
					check = true;
					APP_LOGS.info("MO call placed successfully");
					test.log(LogStatus.INFO, "MO call placed successfully");
					SA.assertTrue(check, "MO call placed successfully");
				}
				else {
					APP_LOGS.info("MO call not placed");
					test.log(LogStatus.ERROR, "MO call not placed");
					SA.fail("MO call not placed");
				}			
			}
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			APP_LOGS.info("Element Not Found");
			test.log(LogStatus.ERROR, "Element Not Found");
			SA.fail();
		}
		SA.assertAll();
	}

	public void sendMessageFromContacts(String Name,String textMessage) throws InterruptedException, IOException
	{
		/*
		 * Send message to the saved contact and validate sent message
		 */
		SoftAssert SA= new SoftAssert();
		try {
			searchContact(Name);
			if(!isElementExist(Locators_Contacts.noContacts))
			{
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_MENU);
				minWait();
				Locators_Contacts.sendMessageOpt.click();
				minWait();
				enterTextToInputField(Locators_Contacts.typeMessageOpt, textMessage);
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_MENU);
				minWait();
				Locators_Contacts.sendOpt.click();
				customWait(5000);
				if(isElementExist(Locators_Contacts.messageNowOpt))
				{
					minWait();
					aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
					check = true;
					APP_LOGS.info("Message sent successfully");
					test.log(LogStatus.INFO, "Message sent successfully");
					SA.assertTrue(check, "Message sent successfully");	
				}
				else {
					APP_LOGS.info("Message not sent");
					test.log(LogStatus.ERROR, "Message not sent");
					SA.fail("Message not sent");
				}	

			}
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			APP_LOGS.info("Element Not Found");
			test.log(LogStatus.ERROR, "Element Not Found");
			SA.fail();
		}
		SA.assertAll();
	}

	public void addContactToFavorites(String Name) throws InterruptedException, IOException
	{
		/*
		 * Add contact to favorites and validate
		 */
		SoftAssert SA= new SoftAssert();
		try {
			searchContact(Name);
			if(!isElementExist(Locators_Contacts.noContacts))
			{
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_MENU);
				Locators_Contacts.addToFavorites.click();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
				customWait(2000);
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
				customWait(2000);
				if(isElementExist(Locators_Contacts.favoriteContact)) 
				{
					check = true;
					APP_LOGS.info("Contact added to Favorites successfully");
					test.log(LogStatus.INFO, "Contact added to Favorites successfully");
					SA.assertTrue(check, "Contact added to Favorites successfully");
				}
				else
				{
					APP_LOGS.info("Contact not added to Favorites");
					test.log(LogStatus.ERROR, "Contact not added to Favorites");
					SA.fail("Contact not added to Favorites");
				}
			}
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			APP_LOGS.info("Element Not Found");
			test.log(LogStatus.ERROR, "Element Not Found");
			SA.fail();
		}
		SA.assertAll();
	}

	public void removeContactFromFavorites(String Name) throws InterruptedException, IOException
	{
		/*
		 * Remove Contact from favorites and validate
		 */
		SoftAssert SA= new SoftAssert();
		try {
			searchContact(Name);
			if(!isElementExist(Locators_Contacts.noContacts))
			{
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_MENU);
				customWait(2000);
				Locators_Contacts.removeFavorites.click();
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
				customWait(3000);
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
				minWait();
				if(!isElementExist(Locators_Contacts.favoriteContact)) 
				{
					check = true;
					APP_LOGS.info("Contact removed from Favorites successfully");
					test.log(LogStatus.INFO, "Contact removed from Favorites successfully");
					SA.assertTrue(check, "Contact removed from Favorites successfully");
				}
				else
				{
					APP_LOGS.info("Contact not removed from Favorites");
					test.log(LogStatus.ERROR, "Contact not removed from Favorites");
					SA.fail("Contact not removed from Favorites");
				}
			}

		} catch (NoSuchElementException e) {
			e.printStackTrace();
			APP_LOGS.info("Element Not Found");
			test.log(LogStatus.ERROR, "Element Not Found");
			SA.fail();
		}
		SA.assertAll();
	}

	public void  validateShareOption(String Name) throws InterruptedException, IOException
	{
		/*
		 * Validate Share Contact option via Message and Presence of share contact via Bluetooth
		 */
		SoftAssert SA= new SoftAssert();
		try {
			searchContact(Name);
			if(!isElementExist(Locators_Contacts.noContacts))
			{
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_MENU);
				Locators_Contacts.shareOpt.click();
				minWait();
				if(isElementExist(Locators_Contacts.shareViaBluetooth))
				{
					check = true;
					APP_LOGS.info("Share Contact via Bluetooth Option is available");
					test.log(LogStatus.INFO, "Share Contact via Bluetooth Option is available");
					SA.assertTrue(check, "Share Contact via Bluetooth Option is available");
				}
				else {
					APP_LOGS.info("Share Contact via Bluetooth option is not available");
					test.log(LogStatus.ERROR, "Share Contact via Bluetooth option is not available");
					SA.fail("Share Contact via Bluetooth option is not available");
				}
				Locators_Contacts.shareViaMessage.click();
				minWait();
				if(isElementExist(Locators_Contacts.shareMessagePage))
				{
					check = true;
					APP_LOGS.info("Able to share contact via Message");
					test.log(LogStatus.INFO, "Able to share contact via Message");
					SA.assertTrue(check, "Able to share contact via Message");
				}
				else {
					APP_LOGS.info("Unable to share contact via message");
					test.log(LogStatus.ERROR, "Unable to share contact via message");
					SA.fail("Unable to share contact via message");
				}
			}	
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			APP_LOGS.info("Element Not Found");
			test.log(LogStatus.ERROR, "Element Not Found");
			SA.fail();
		}
		SA.assertAll();

	}

	public void validatesendContact(String Name,String Phone) throws InterruptedException, IOException
	{
		/*
		 * Validate Send Contact Option 
		 */
		SoftAssert SA= new SoftAssert();
		try {
			searchContact(Name);
			if(!isElementExist(Locators_Contacts.noContacts))
			{
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_MENU);
				minWait();
				scrollToElementWithDpadDown(Locators_Contacts.sendContactOpt);
				minWait();
				Locators_Contacts.sendContactOpt.click();
			    customWait(2000);
			    changeToNumberMode();
			    minWait();
				Runtime.getRuntime().exec("adb shell input text "+Phone);
				customWait(2000);
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_MENU);
				minWait();
				Locators_Contacts.sendOpt.click();
				minWait();
				if(isElementExist(Locators_Contacts.messageNowOpt))
				{
					minWait();
					aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
					check = true;
					APP_LOGS.info("Contact sent successfully");
					test.log(LogStatus.INFO, "Contact sent successfully");
					SA.assertTrue(check, "Contact sent successfully");	
				}
				else {
					APP_LOGS.info("Contact not sent");
					test.log(LogStatus.ERROR, "Contact not sent");
					SA.fail("Contact not sent");
				}	
			}
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			APP_LOGS.info("Element Not Found");
			test.log(LogStatus.ERROR, "Element Not Found");
			SA.fail();
		}
		SA.assertAll();
	}

	public void scrollToElementWithDpadDown(AndroidElement element)
	{
		/*
		 * Clicks down button till element is available
		 */
		do
		{
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
		}while(!isElementExist(element));
	}

	public void validateCopyToSIMAndCopyToPhone(String Name) throws InterruptedException, IOException
	{
		/*
		 * Validates Copy to SIM and Copy to Phone options
		 */
		SoftAssert SA= new SoftAssert();
		try {
			searchContact(Name);
			if(!isElementExist(Locators_Contacts.noContacts))
			{
				minWait();
				String actualCount1= clickToCopy(Locators_Contacts.copyToSIMOpt);
				minWait();
				SA.assertEquals(actualCount1, "2 Contacts matched");
				check = true;
				APP_LOGS.info("Contact Copied to SIM successfully");
				test.log(LogStatus.INFO, "Contact Copied to SIM successfully");
				SA.assertTrue(check, "Contact Copied to SIM successfully");	
				check = false;
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
				minWait();
				String actualCount2 = clickToCopy(Locators_Contacts.copyToPhoneOpt);
				minWait();
				SA.assertEquals(actualCount2, "3 Contacts matched");
				check = true;
				APP_LOGS.info("Contact Copied to Phone successfully");
				test.log(LogStatus.INFO, "Contact Copied to Phone successfully");
				SA.assertTrue(check, "Contact Copied to Phone successfully");
			}
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			APP_LOGS.info("Element Not Found");
			test.log(LogStatus.ERROR, "Element Not Found");
			SA.fail();
		}
		SA.assertAll();
	}

	public String clickToCopy(WebElement element) throws InterruptedException
	{
		/*
		 * Clicks on element(Ex:Copy to SIM) and captures matching contacts
		 */
		aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_MENU);
		minWait();
		element.click();
		customWait(3000);
		aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
		String actualCount = Locators_Contacts.matchedContactsFld.getText();
		return actualCount;

	}

	public void validatePresenceOfSetRingtone(String Name) throws InterruptedException, IOException
	{
		/*
		 * Validates Presence of Set Ringtone Option
		 */
		SoftAssert SA= new SoftAssert();
		try {
			searchContact(Name);
			if(!isElementExist(Locators_Contacts.noContacts))
			{
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_MENU);
				if(Locators_Contacts.setRingtoneOpt.isDisplayed())
				{
					minWait();
					aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
					check = true;
					APP_LOGS.info("Set Ringtone option is available");
					test.log(LogStatus.INFO, "Set Ringtone option is available");
					SA.assertTrue(check, "Set Ringtone option is available");
				}
				else
				{
					APP_LOGS.info("Set Ringtone option is not available");
					test.log(LogStatus.ERROR, "Set Ringtone option is not available");
					SA.fail("Set Ringtone option is not available");
				}
			}

		} catch (NoSuchElementException e) {
			e.printStackTrace();
			APP_LOGS.info("Element Not Found");
			test.log(LogStatus.ERROR, "Element Not Found");
			SA.fail();
		}
		SA.assertAll();
	}

	public void validateAllCallsToVoicemail(String Name) throws InterruptedException, IOException
	{
		/*
		 * Validates presence of All calls to voicemail option with check box status
		 */
		SoftAssert SA= new SoftAssert();
		try {
			searchContact(Name);
			if(!isElementExist(Locators_Contacts.noContacts))
			{
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_MENU);
				if(Locators_Contacts.allCallsToVoicemailOpt.isDisplayed())
				{
					minWait();
					if(!Locators_Contacts.allcallsToVoicemailCheckbox.isSelected())
					{
						aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
						check = true;
						APP_LOGS.info("All calls option is available and Checkbox is not enabled");
						test.log(LogStatus.INFO, "All calls option is available and Checkbox is not enabled");
						SA.assertTrue(check, "All calls option is available and Checkbox is not enabled");
					}
					else
					{
						APP_LOGS.info("Checkbox is enabled");
						test.log(LogStatus.ERROR, "Checkbox is enabled");
						SA.fail("Checkbox is enabled");
					}
				}
				else
				{
					APP_LOGS.info("All calls to voicemail option is not available");
					test.log(LogStatus.ERROR, "All calls to voicemail option is not available");
					SA.fail("All calls to voicemail option is not available");
				}
			}
		}catch (NoSuchElementException e) {
			e.printStackTrace();
			APP_LOGS.info("Element Not Found");
			test.log(LogStatus.ERROR, "Element Not Found");
			SA.fail();
		}
		SA.assertAll();
	}

	public void searchContact(String Name) throws InterruptedException, IOException
	{
		/*
		 * Search for the contact and selects
		 */
		customWait(5000);
		Runtime.getRuntime().exec("adb shell input text  "+Name);
		customWait(3000);
		aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
		minWait();
	}

	public void validateLinkAndUnlink(String Name1,String Phone1,String Name2,String Phone2) throws InterruptedException, IOException
	{
		/*
		 * Perform Link and Unlink Contact and validates
		 */
		SoftAssert SA= new SoftAssert();
		try {
			addContact(Locators_Contacts.addContactOpt, Name1, Phone1);
			customWait(3000);
			addContact(Locators_Contacts.addContactOpt, Name2, Phone2);
			minWait();
			searchContact(Name1);
			customWait(3000);
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_MENU);
			minWait();
			scrollToElementWithDpadDown(Locators_Contacts.linkOpt);
			minWait();
			Locators_Contacts.linkOpt.click();
			minWait();
			searchContact(Name2);
			customWait(2000);
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
			customWait(2000);
			validateLinkedAndUnlikedContact();
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_MENU);
			minWait();
			scrollToElementWithDpadDown(Locators_Contacts.unlinkOpt);
			minWait();
			Locators_Contacts.unlinkOpt.click();
			minWait();
			Locators_Contacts.unlinkBtn.click();
			customWait(2000);
			validateLinkedAndUnlikedContact();

		} catch (NoSuchElementException e) {
			e.printStackTrace();
			APP_LOGS.info("Element Not Found");
			test.log(LogStatus.ERROR, "Element Not Found");
			SA.fail();
		}

	}

	public void validateLinkedAndUnlikedContact() throws InterruptedException {
		/*
		 * Validates Linked and unlinked contact
		 */
		SoftAssert SA= new SoftAssert();
		customWait(3000);
		if(isElementExist(Locators_Contacts.linkedContact))
		{
			customWait(2000);
			//aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			check = true;
			APP_LOGS.info("Contact Linked successfully and linked Contact is available");
			test.log(LogStatus.INFO, "Contact Linked successfully and linked Contact is available");
			SA.assertTrue(check, "Contact Linked successfully and linked Contact is available");
		}
		else
		{
			customWait(2000);
			//aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			APP_LOGS.info("Contact unlinked successfully");
			test.log(LogStatus.INFO, "Contact unlinked successfully");
			SA.fail("Contact unlinked successfully");
		}
	}

	public void addContact(WebElement element,String Name,String Phone) throws InterruptedException, IOException
	{
		/*
		 * Adds contact through settings--> add contact 
		 */
		customWait(2000);
		aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_MENU);
		customWait(2000);
		Locators_Contacts.addContactOpt.click();
		minWait();
		enterTextToInputField(Locators_Contacts.nameField, Name);
		customWait(3000);
		enterTextToInputField(Locators_Contacts.phoneField, Phone);
		customWait(3000);
		aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_MENU);
		minWait();
		Locators_Contacts.saveBtn.click();
		minWait();
		aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
		customWait(3000);
		minWait();
	}

	public void validateBlockedNumbersFeature(String Phone) throws InterruptedException  
	{
		/*
		 * Performs Block and unblock numbers and validates
		 */
		SoftAssert SA= new SoftAssert();
		try {
			clickMenuAndElement(Locators_Contacts.blockedNumbersOpt);
			minWait();
			clickMenuAndElement(Locators_Contacts.addNumberToBlock);
			minWait();
			enterTextToInputField(Locators_Contacts.phoneNumberToBlock, Phone);
			customWait(2000);
			Locators_Contacts.blockBtn.click();
			minWait();
			validateblockedAndUnblockedNumber();
			customWait(2000);
			clickMenuAndElement(Locators_Contacts.blockedNumbersOpt);
			minWait();
			clickMenuAndElement(Locators_Contacts.unblockOpt);
			minWait();
			Locators_Contacts.unblockBtn.click();
			minWait();
			validateblockedAndUnblockedNumber();
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			APP_LOGS.info("Element Not Found");
			test.log(LogStatus.ERROR, "Element Not Found");
			SA.fail();

		}
		SA.assertAll();
	}

	public void validateblockedAndUnblockedNumber() throws InterruptedException {
		/*
		 * Validate blocked and unblocked number
		 */
		SoftAssert SA= new SoftAssert();
		if(isElementExist(Locators_Contacts.blockedNumber))
		{
			customWait(2000);
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			check = true;
			APP_LOGS.info("Contact Blocked successfully and Blocked Contact is available");
			test.log(LogStatus.INFO, "Contact Blocked successfully and Blocked Contact is available");
			SA.assertTrue(check, "Contact Blocked successfully and Blocked Contact is available");
		}
		else
		{
			customWait(2000);
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			APP_LOGS.info("Contact unblocked successfully");
			test.log(LogStatus.INFO, "Contact unblocked successfully");
			SA.fail("Contact unblocked successfully");
		}
	}

	public void clickMenuAndElement(WebElement element) throws InterruptedException
	{
		/*
		 * Click on menu button and clicks on element passed
		 */
		customWait(2000);
		aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_MENU);
		customWait(3000);
		element.click();
		minWait();
	}

	public void validateSelectAllAndDeselectAllfeature() throws InterruptedException {
		/*
		 * validates select,select all and deselect all feature
		 */
		SoftAssert SA= new SoftAssert();
		try {
			customWait(2000);
			clickMenuAndElement(Locators_Contacts.selectOpt);
			minWait();
			clickMenuAndElement(Locators_Contacts.selectAllOpt);
			minWait();
			getSelectedCountAndValidate();
			customWait(2000);
			clickMenuAndElement(Locators_Contacts.deselectAllOpt);
			customWait(5000);
			getSelectedCountAndValidate();	
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			APP_LOGS.info("Element Not Found");
			test.log(LogStatus.ERROR, "Element Not Found");
			SA.fail();

		}
		SA.assertAll();
	}

	public void getSelectedCountAndValidate() throws InterruptedException
	{
		/*
		 * gets the count of selected contacts and validates
		 */
		SoftAssert SA= new SoftAssert();
		customWait(2000);
		String actualCount=Locators_Contacts.countField.getText();
		int count = Integer.parseInt(actualCount);
		System.out.println(count);
		if(count==0)
		{
			customWait(2000);
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			check = true;
			APP_LOGS.info("Deselected all contacts");
			test.log(LogStatus.INFO, "Deselected all contacts");
			SA.assertTrue(check, "Deselected all contacts");	 
		}
		else
		{
			APP_LOGS.info("Selected all Contacts");
			test.log(LogStatus.INFO, "Selected all Contacts");
			SA.fail("Selected all Contacts");	 
		}
	}

	public void validateSettings() throws InterruptedException, IOException
	{
		/*
		 * validates SortBy options and Name Format Options in settings
		 */
		SoftAssert SA= new SoftAssert();
		try {
			minWait();
			validateSortBy(Locators_Contacts.sortFirstName, Locators_Contacts.firstNamefirstContact, "Abc Auto");
			customWait(2000);
			validateSortBy(Locators_Contacts.sortLastName, Locators_Contacts.sortLastNameContact, "Test A");
			minWait();
			validateNameFormat(Locators_Contacts.firstNameFirst, Locators_Contacts.firstNamefirstContact, "Abc Auto");
			customWait(2000);
			validateNameFormat(Locators_Contacts.lastNameFirst, Locators_Contacts.lastNameFirstContact, "Auto, Abc");
			minWait();
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			APP_LOGS.info("Element Not Found");
			test.log(LogStatus.ERROR, "Element Not Found");
			SA.fail();
		}
		SA.assertAll();
	}

	public String selectSettings(WebElement setElement, WebElement element) throws InterruptedException {
		/*
		 * Clicks Settings and select options based on Input element
		 */
		minWait();
		clickMenuAndElement(Locators_Contacts.settingsOpt);
		minWait();
		setElement.click();
		String field = element.getText();
		element.click();
		aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
		minWait();
		return field;
	}

	public void validateSortBy(WebElement element,WebElement contactElement,String Contact) throws InterruptedException {
		/*
		 * Common method to validate sortBy option 
		 */
		SoftAssert SA= new SoftAssert();
		String field = selectSettings(Locators_Contacts.sortByOpt,element);
		minWait();
		launch_an_app("contacts");
		String actualText = contactElement.getText();
		minWait();
		SA.assertEquals(actualText, Contact);
		minWait();
		check = true;
		APP_LOGS.info( field +" feature is validated successfully");
		test.log(LogStatus.INFO, field +" feature is validated successfully");
		SA.assertTrue(check, field +" feature is validated successfully");
	}

	public void validateNameFormat(WebElement element,WebElement contactElement,String Contact) throws InterruptedException, IOException
	{
		/*
		 * Common method to validate Name format option 
		 */
		SoftAssert SA= new SoftAssert();
		minWait();
		String field = selectSettings(Locators_Contacts.nameFormat,element );
		minWait();
		launch_an_app("contacts");
		customWait(3000);
		Runtime.getRuntime().exec("adb shell input text abc");
		customWait(3000);
		String actaulName = contactElement.getText();
		SA.assertEquals(actaulName, Contact);
		minWait();
		aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
		check = true;
		APP_LOGS.info( field +" feature is validated successfully");
		test.log(LogStatus.INFO, field +" feature is validated successfully");
		SA.assertTrue(check, field +" feature is validated successfully");
	}

	
	public void createNewContactFromDailer(String Name) throws InterruptedException
	{
		/*
		 * Creates new contact 
		 */
		minWait();
		Locators_Contacts.createNewContact.click();
		customWait(2000);
		enterTextToInputField(Locators_Contacts.nameField, Name);
		minWait();
		aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_MENU);
		minWait();
		Locators_Contacts.saveBtn.click();	
		minWait();
	}

	public void validateAddContactFromDialer(String Name,String Phone) throws InterruptedException, IOException {
		/*
		 * Validates add contact from dialer
		 */
		SoftAssert SA = new SoftAssert();
		try {
			customWait(2000);
			launch_an_app("phone");
			customWait(2000);
			Runtime.getRuntime().exec("adb shell input text "+Phone);
			customWait(2000);
			if(!isElementExist(Locators_Contacts.existingContactInDailer))
			{
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
				customWait(2000);
				Locators_Contacts.addToContactOptDailer.click();
				customWait(2000);
				createNewContactFromDailer(Name);
				customWait(2000);
				launch_an_app("contacts");
				customWait(3000);
				Runtime.getRuntime().exec("adb shell input text  "+Name);
				customWait(3000);
				validateAddedContact(Name);
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			}
			else
			{
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
				APP_LOGS.info("Contact Already Exist");
				test.log(LogStatus.INFO, "Contact Already Exist");
				SA.fail("Contact Already Exist");
			}


		} catch (NoSuchElementException e) {
			e.printStackTrace();
			APP_LOGS.info("Element Not Found");
			test.log(LogStatus.ERROR, "Element Not Found");
			SA.fail();
		}
		SA.assertAll();

	}

	public void validateCancel(String Name) throws InterruptedException, IOException
	{
		/*
		 * Validates Cancel Option
		 */
		SoftAssert SA = new SoftAssert();
		try {
			/*=================Scenario-1(Delete Scenario)============*/
			customWait(2000);
			searchContact(Name);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_CLEAR);
			minWait();
			if(isElementExist(Locators_Contacts.cancelOpt))
			{
				minWait();
				Locators_Contacts.cancelOpt.click();
				minWait();
				check0 = true;
				APP_LOGS.info("Cancel Option is verified in delete scenario");
				test.log(LogStatus.INFO, "Cancel Option is verified in delete scenario");
			}
			/*=================Scenario-2(Send Contact Scenario)============*/
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_MENU);
			minWait();
			scrollToElementWithDpadDown(Locators_Contacts.sendContactOpt);
			minWait();
			Locators_Contacts.sendContactOpt.click();
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			customWait(2000);
			if(isElementExist(Locators_Contacts.cancelOpt))
			{
				minWait();
				Locators_Contacts.cancelOpt.click();
				minWait();
				check1 = true;
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
				minWait();
				Locators_Contacts.okOpt.click();
				minWait();
				APP_LOGS.info("Cancel Option is verified in Send contact scenario");
				test.log(LogStatus.INFO, "Cancel Option is verified in Send contact scenario");
			}
			/*=================Scenario-3(Unlink Scenario)============*/
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_MENU);
			minWait();
			scrollToElementWithDpadDown(Locators_Contacts.linkOpt);
			minWait();
			Locators_Contacts.linkOpt.click();
			customWait(2000);
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
			customWait(2000);
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_MENU);
			minWait();
			scrollToElementWithDpadDown(Locators_Contacts.unlinkOpt);
			minWait();
			Locators_Contacts.unlinkOpt.click();
			minWait();
			if(isElementExist(Locators_Contacts.cancelOpt))
			{
				minWait();
				Locators_Contacts.cancelOpt.click();
				minWait();
				check2 = true;
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
				APP_LOGS.info("Cancel Option is verified in Unlink scenario");
				test.log(LogStatus.INFO, "Cancel Option is verified in Unlink scenario");
			}
			/*=================Scenario-4(Block Scenario)============*/
			customWait(2000);
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			customWait(3000);
			clickMenuAndElement(Locators_Contacts.blockedNumbersOpt);
			customWait(2000);
			clickMenuAndElement(Locators_Contacts.addNumberToBlock);
			minWait();
			if(isElementExist(Locators_Contacts.cancelOpt))
			{
				minWait();
				Locators_Contacts.cancelOpt.click();
				minWait();
				check3 = true;
				APP_LOGS.info("Cancel Option is verified in Block scenario");
				test.log(LogStatus.INFO, "Cancel Option is verified in Block scenario");
			}
			/*=================Scenario-5(Unblock Scenario)============*/
			minWait();
			clickMenuAndElement(Locators_Contacts.addNumberToBlock);
			customWait(2000);
			Runtime.getRuntime().exec("adb shell input text 9966666666");
			customWait(2000);
			Locators_Contacts.blockBtn.click();
			customWait(2000);
			clickMenuAndElement(Locators_Contacts.unblockOpt);
			minWait();
			if(isElementExist(Locators_Contacts.cancelOpt))
			{
				minWait();
				Locators_Contacts.cancelOpt.click();
				minWait();
				check4 = true;
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
				APP_LOGS.info("Cancel Option is verified in Unblock scenario");
				test.log(LogStatus.INFO, "Cancel Option is verified in Unblock scenario");
			}
			/*=================Scenario-6(Add Contact Scenario)============*/
			minWait();
			clickMenuAndElement(Locators_Contacts.addContactOpt);
			customWait(2000);
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_MENU);
			minWait();
			if(isElementExist(Locators_Contacts.cancelOptInAddContact))
			{
				minWait();
				Locators_Contacts.cancelOptInAddContact.click();
				minWait();
				check5 = true;
				minWait();
				APP_LOGS.info("Cancel Option is verified in Add Contact scenario");
				test.log(LogStatus.INFO, "Cancel Option is verified in Add Contact scenario");
			}

			if((check0)&&(check1)&&(check2)&&(check3)&&(check4)){
				check= true;
				APP_LOGS.info("Cancel Option is verified successfully");
				test.log(LogStatus.INFO, "Cancel Option is verified successfully");
				SA.assertTrue(check,"Cancel Option is verified successfully");
			}
			else{
				APP_LOGS.info("Cancel Option is not verified");
				test.log(LogStatus.INFO, "Cancel Option is not verified");
				SA.fail("Cancel Option is not verified");
			}			
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			APP_LOGS.info("Element Not Found");
			test.log(LogStatus.ERROR, "Element Not Found");
			SA.fail();
		}
		SA.assertAll();
	}

	public void validateKeepEditingAndDiscardOption() throws InterruptedException
	{
		/*
		 * Validates keep editing and Discard option
		 */
		SoftAssert SA = new SoftAssert();
		try {
			customWait(2000);
			clickMenuAndElement(Locators_Contacts.addContactOpt);
			customWait(2000);
			enterTextToInputField(Locators_Contacts.nameField, "Abc");
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.BACK);
			minWait();
			if(isElementExist(Locators_Contacts.discardPromptMessage))
			{
				minWait();
				if(isElementExist(Locators_Contacts.keepEditingOpt))
				{
					minWait();
					Locators_Contacts.keepEditingOpt.click();
					minWait();
					check = true;
					APP_LOGS.info("Keep Editing Option verified successfully");
					test.log(LogStatus.INFO, "Keep Editing Option is verified successfully");
					SA.assertTrue(check,"Keep Editing Option is verified successfully");
				}
				else
				{
					APP_LOGS.info("Keep Editing Option is not available");
					test.log(LogStatus.INFO, "Keep Editing Option is not available");
					SA.fail("Keep Editing Option is not available");
				}
				customWait(2000);
				aDriver.pressKeyCode(AndroidKeyCode.BACK);
				minWait();
				if(isElementExist(Locators_Contacts.discardOpt))
				{
					minWait();
					Locators_Contacts.discardOpt.click();
					minWait();
					check = true;
					APP_LOGS.info("Discard Option verified successfully");
					test.log(LogStatus.INFO, "Discard Option is verified successfully");
					SA.assertTrue(check,"Discard Option is verified successfully");
				}
				else
				{
					APP_LOGS.info("Discard Option is not available");
					test.log(LogStatus.INFO, "Discard Option is not available");
					SA.fail("Discard Option is not available");
				}
			}
			
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			APP_LOGS.info("Element Not Found");
			test.log(LogStatus.ERROR, "Element Not Found");
			SA.fail();
		}
		SA.assertAll();
	}

	public void validateImportExport(String Name) throws InterruptedException, IOException {
		SoftAssert SA = new SoftAssert();
		try {
			/*
			 * Validates all Import/Export Options
			 */
			//=============Import from .vcf file================
			selectImportOption(Locators_Contacts.importFromVcfOpt);
			if(isElementExist(Locators_Contacts.noVCFPopUpOpt))
			{
				Locators_Contacts.okOpt.click();
				minWait();
				check = true;
				APP_LOGS.info("VCF Contacts Not available");
				test.log(LogStatus.INFO, "VCF Contacts Not available");
				SA.assertTrue(check, "VCF Contacts Not available");
			}
			else
			{
				customWait(1000);
				clickMenuAndElement(Locators_Contacts.selectOpt);
				customWait(2000);
				clickMenuAndElement(Locators_Contacts.selectAllOpt);
				customWait(2000);
				String actualCount=Locators_Contacts.countField.getText();
				int count = Integer.parseInt(actualCount);
				System.out.println(count);
				if(count>250)
				{
					aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
					minWait();
					check = true ;
					APP_LOGS.info("Contacts Imported form .vcf file successfully");
					test.log(LogStatus.INFO, "Contacts Imported form .vcf file successfully");
					SA.assertTrue(check, "Contacts Imported form .vcf file successfully");
				}
				else
				{
					aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
					minWait();
					APP_LOGS.info("Failed to Import Contacts from .vcf file");
					test.log(LogStatus.INFO, "Failed to Import Contacts from .vcf file");
					SA.fail("Failed to Import Contacts from .vcf file");
				}				
			}	
			//=============Import from SIM Card================
			selectImportOption(Locators_Contacts.importFromSIMOpt);
			if(isElementExist(Locators_Contacts.SIMContactToImport))
			{
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
				minWait();
				clickMenuAndElement(Locators_Contacts.doneOpt);
				minWait();
				Locators_Contacts.okOpt.click();
				customWait(3000);
				Runtime.getRuntime().exec("adb shell input text  "+ Name);
				customWait(3000);
				String actualCount = Locators_Contacts.matchedContactsFld.getText();
				SA.assertEquals(actualCount, "4 Contacts matched");
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
				minWait();
				check = true;
				APP_LOGS.info("Contact imported from SIM successfully");
				test.log(LogStatus.INFO, "Contact imported from SIM successfully");
				SA.assertTrue(check, "Contact imported from SIM successfully");
			}else
			{
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
				minWait();
				APP_LOGS.info("No Contacts Found to import from SIM");
				test.log(LogStatus.INFO, "No Contacts Found to import from SIM");
				SA.fail("No Contacts Found to import from SIM");
			}
			//=============Export to SIM Card================
			selectImportOption(Locators_Contacts.exportToSIMopt);
			if(isElementExist(Locators_Contacts.SIMContactToImport))
			{
			searchContact(Name);
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
			minWait();
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
			minWait();
			clickMenuAndElement(Locators_Contacts.doneOpt);
			customWait(3000);
			Runtime.getRuntime().exec("adb shell input text  "+ Name);
			customWait(3000);
			String actualCount = Locators_Contacts.matchedContactsFld.getText();
			SA.assertEquals(actualCount, "5 Contacts matched");
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			minWait();
			check = true;
			APP_LOGS.info("Contact exported to SIM successfully");
			test.log(LogStatus.INFO, "Contact exported to SIM successfully");
			SA.assertTrue(check, "Contact exported to SIM successfully");
			}else
			{
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
				minWait();
				APP_LOGS.info("No Contacts Found to export to SIM");
				test.log(LogStatus.INFO, "No Contacts Found to export to SIM");
				SA.fail("No Contacts Found to export to SIM");
			}	
			//=============Export to .vcf file================
			selectImportOption(Locators_Contacts.exportToVcfOpt);
			if(isElementExist(Locators_Contacts.SIMContactToImport))
			{
			searchContact(Name);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
			minWait();
			clickMenuAndElement(Locators_Contacts.doneOpt);
			minWait();
			Locators_Contacts.okOpt.click();
			customWait(3000);
			Runtime.getRuntime().exec("adb shell input text  "+ Name);
			customWait(3000);
			String actualCount = Locators_Contacts.matchedContactsFld.getText();
			SA.assertEquals(actualCount, "6 Contacts matched");
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			minWait();
			check = true;
			APP_LOGS.info("Contact exported to .vcf file successfully");
			test.log(LogStatus.INFO, "Contact exported to .vcf file successfully");
			SA.assertTrue(check, "Contact exported to .vcf file successfully");
			}else
			{
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
				minWait();
				APP_LOGS.info("No Contacts Found to export to .vcf file");
				test.log(LogStatus.INFO, "No Contacts Found to export to .vcf file");
				SA.fail("No Contacts Found to export to .vcf file");
			}
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			APP_LOGS.info("Element Not Found");
			test.log(LogStatus.ERROR, "Element Not Found");
			SA.fail();
		}
		SA.assertAll();
	}
	
	public void selectImportOption(WebElement element) throws InterruptedException
	{
		/*
		 * Select Import/Export option
		 */
		customWait(2000);
		clickMenuAndElement(Locators_Contacts.importExportOpt);
		minWait();
		element.click();
		minWait();
	}

	public void changeToNumberMode() throws InterruptedException {
  /*
   * Change input field type to number
   */
		  minWait();
		  Locators_Contacts.toField_NewMessage.sendKeys("123");
		  customWait(1500);
		  String text = Locators_Contacts.toField_NewMessage.getText();
		  System.out.println(text);
		  if(!text.equals("123")) {
		   for (int i = 0; i < 3; i++) {
		    aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_POUND);
		    minWait();
		   }
		  }
		  Locators_Contacts.toField_NewMessage.clear();
		 }
	
	public void validateDeleteFeature(String name1,String name2,String name) throws InterruptedException, IOException
	{
		/*
		 * Delete contact 
		 */
		SoftAssert SA = new SoftAssert();
		try {
			customWait(2000);
			searchContact(name1);
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_CLEAR);
			minWait();
			Locators_Contacts.deleteBtn.click();
			customWait(2000);
			validatedeletedcontact(name1);
			customWait(2000);
			searchContact(name2);
			minWait();
			clickMenuAndElement(Locators_Contacts.deleteOpt);
			minWait();
			Locators_Contacts.deleteBtn.click();
			minWait();
			validatedeletedcontact(name2);
			minWait();
			clickMenuAndElement(Locators_Contacts.selectOpt);
			minWait();
			clickMenuAndElement(Locators_Contacts.selectAllOpt);
			minWait();
			clickMenuAndElement(Locators_Contacts.deleteOpt);
			minWait();
			Locators_Contacts.deleteBtn.click();
			minWait();
			if(isElementExist(Locators_Contacts.addContacts))
			{
					check = true ;
					APP_LOGS.info("All Contacts deleted successfully");
					test.log(LogStatus.INFO, "All Contacts deleted successfully");
					SA.assertTrue(check, "All Contacts deleted successfully");
			}
			else
			{
				customWait(2000);
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
				APP_LOGS.info("Contact not deleted");
				test.log(LogStatus.INFO, "Contact not deleted");
				SA.fail("Contact not deleted");
			}     
			minWait();

		} catch (NoSuchElementException e) {
			e.printStackTrace();
			APP_LOGS.info("Element Not Found");
			test.log(LogStatus.ERROR, "Element Not Found");
			SA.fail();
		}
		SA.assertAll();
	}

	public void validatedeletedcontact(String name) throws InterruptedException, IOException
	{
		/*
		 * Validates deleted Contact
		 */
		SoftAssert SA = new SoftAssert();
		minWait();
		aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
		minWait();
		searchContact(name);
		customWait(3000);
		if(isElementExist(Locators_Contacts.noContacts))
		{
			customWait(2000);
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			check = true;
			APP_LOGS.info("Contact deleted successfully");
			test.log(LogStatus.INFO, "Contact deleted successfully");
			SA.assertTrue(check, "Contact deleted successfully");	
		}
		else
		{
			customWait(2000);
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			APP_LOGS.info("Contact not deleted");
			test.log(LogStatus.INFO, "Contact not deleted");
			SA.fail("Contact not deleted");
		}
	}

}
