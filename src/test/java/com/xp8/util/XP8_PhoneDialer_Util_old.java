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

import application.AllQA;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidKeyCode;

public class XP8_PhoneDialer_Util_old extends BaseUtil{

	public boolean check = false;
	boolean check0 = false;
	boolean check1 = false;
	boolean check2 = false;
	boolean check3 = false;
	boolean check4 = false;
	boolean check5 = false;
	boolean check6 = false;
	boolean check7 = false;
	boolean check8 = false;
	boolean check9 = false;
	boolean check10 = false;
	boolean check11 = false;
	boolean check12 = false;
	boolean check13 = false;
	boolean check14 = false;

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
			if (!isElementExist(Locators_PhoneDialer_old.turnOff_Airplane_PopUp)) {
				try {
					for(int j=1;j<=100;j++){
						Process child = null;
						if (r_b_No.contains("8A.")) {
							child=Runtime.getRuntime().exec("adb -s "+r_Id+" shell service call telecom 27");
						} else if(r_b_No.contains("5SA.")) {
							child=Runtime.getRuntime().exec("adb -s "+r_Id+" shell service call telecom 28");
						}     
						InputStream lsOut = child.getInputStream();
						InputStreamReader r = new InputStreamReader(lsOut);
						BufferedReader in = new BufferedReader(r);
						String  value=in.readLine();
						System.out.println(value);
						if(value.contains("00000001")) {
							System.out.println("Phone is ringing so accepting call.");
							Thread.sleep(3000);
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
			clickBtn(Locators_PhoneDialer_old.settingsIcon);
			minWait();
			if(isElementExist(Locators_PhoneDialer_old.clearFrequentsOpt)){
				clickBtn(Locators_PhoneDialer_old.clearFrequentsOpt);
				minWait();
				clickBtn(Locators_PhoneDialer_old.okBtn);
				customWait(2000);
			}else{
				clickBackButton(1);
				minWait();
			}
			minWait();
			launch_an_app("phone");
			navigateTocallHistory();
			minWait();
			clickBtn(Locators_PhoneDialer_old.moreOptionsInCallHistory);
			minWait();
			if(isElementExist(Locators_PhoneDialer_old.clearCallHistoryOpt)){
				clickBtn(Locators_PhoneDialer_old.clearCallHistoryOpt);
				minWait();
				clickBtn(Locators_PhoneDialer_old.selectOpt);
				minWait();
				clickBtn(Locators_PhoneDialer_old.selectAllOpt);
				minWait();
				clickBtn(Locators_PhoneDialer_old.clearOptInCallHistory);
				minWait();
				clickBtn(Locators_PhoneDialer_old.okBtn);
				customWait(3000);
				launch_an_app("contacts");
				setDefaultSavingAccount();
				minWait();
				deleteIfContactsPresent();
			}else if(!isElementExist(Locators_PhoneDialer_old.clearCallHistoryOpt)){
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

	public void setDefaultSavingAccount() throws InterruptedException
	{
		/*
		 * Set default saving account as phone in contacts application
		 */
		try {
			minWait();
			clickBtn(Locators_PhoneDialer_old.ContactsMoreOptions);
			minWait();
			clickBtn(Locators_PhoneDialer_old.contactsSettingsOPt);
			minWait();
			clickBtn(Locators_PhoneDialer_old.contactsDefaultAccountSettings);
			minWait();
			clickBtn(Locators_PhoneDialer_old.contactsDefaultPhone);
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
			clickBtn(Locators_PhoneDialer_old.contactsPageAll);
			 minWait();
			if(isElementExist(Locators_PhoneDialer_old.contact)){
				minWait();
				TouchAction touchaction = new TouchAction(aDriver);
				touchaction.longPress(Locators_PhoneDialer_old.contact).perform().release();
				minWait();
				clickBtn(Locators_PhoneDialer_old.selectContact);
				minWait();
				if(isElementExist(Locators_PhoneDialer_old.selectAllOpt)){
					clickBtn(Locators_PhoneDialer_old.selectAllOpt);
					minWait();
				}else{
					clickBackButton(1);
				}
				clickBtn(Locators_PhoneDialer_old.moreSettings);
				minWait();
				clickBtn(Locators_PhoneDialer_old.delete);
				minWait();
				clickBtn(Locators_PhoneDialer_old.okBtn);
				customWait(5000);
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
			if(isElementExist(Locators_PhoneDialer_old.phoneFrame)){
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

	public void validatePhoneAppHomePage() throws InterruptedException
	{
		/*
		 * Validates Phone application home page 
		 */
		SoftAssert SA= new SoftAssert();
		try {
			customWait(2000);
			if(isElementExist(Locators_PhoneDialer_old.favoriteEmptyPage) || isElementExist(Locators_PhoneDialer_old.callLogEmptyPage) || isElementExist(Locators_PhoneDialer_old.contactsEmptyPage))
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
			clickBtn(Locators_PhoneDialer_old.callLogPage);
			minWait();
			clickBtn(Locators_PhoneDialer_old.makeACallOption);
			minWait();
			enterTextToInputField(Locators_PhoneDialer_old.dialpadEditFld, refNum);
			minWait();
			clickBtn(Locators_PhoneDialer_old.callBtn);
			customWait(5000);
			validateMOCall("from Make a call option", refNum);
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "Element Not Found");
		}
	}

	public void validateCallFromCallLog() throws InterruptedException, IOException
	{
		/*
		 * Validates call from call Log(Call to a number from call log)
		 */
		try {
			minWait();
			clickBtn(Locators_PhoneDialer_old.callLogPage);
			minWait();
			clickBtn(Locators_PhoneDialer_old.callBtnInCallLog);
			customWait(5000);
			validateMOCall("from Call Log", refNum);
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
			clickBtn(Locators_PhoneDialer_old.endCallBtn);
			minWait();
			clickBtn(Locators_PhoneDialer_old.firstDialedNumber);
			minWait();
			clickBtn(Locators_PhoneDialer_old.callDetailsOpt);
			minWait();
			String[] callDuration = Locators_PhoneDialer_old.durationInSec.getText().split(" ");
			int callDur = Integer.parseInt(callDuration[0]);
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

	public void enterNumberInDialpad(String Phone) throws InterruptedException
	{
		/*
		 * clicks dialpad and enters number
		 */
		try {
			clickBtn(Locators_PhoneDialer_old.dialpadBtn);
			customWait(2000);
			enterTextToInputField(Locators_PhoneDialer_old.dialpadEditFld, Phone);
			customWait(2000);
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "Element Not Found");
		}
	}

	public void validateAddAContactFromContactPage(String name,String phone) throws InterruptedException
	{
		try {
			minWait();
			clickBtn(Locators_PhoneDialer_old.contactPage);
			minWait();
			clickBtn(Locators_PhoneDialer_old.addAContactOpt);
			minWait();
			createContactWithNameandPhone(name, phone);
			minWait();
			validateSavedContact(name, "from Contact page");
		} catch (NoSuchElementException e) {
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
			clickBtn(Locators_PhoneDialer_old.callLogPage);
			customWait(2000);
			CreateNewContactValidateSavedContact(name,"from call Log");
			minWait();
			clickBtn(Locators_PhoneDialer_old.contactPage);
			minWait();
			clickBtn(Locators_PhoneDialer_old.addedContactCallLog);
			minWait();
			clickBtn(Locators_PhoneDialer_old.moreSettings);
			minWait();
			clickBtn(Locators_PhoneDialer_old.delete);
			minWait();
			clickBtn(Locators_PhoneDialer_old.deleteBtn);
			minWait();
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
			String savedContact = Locators_PhoneDialer_old.savedContact.getText();
			if(savedContact.contains(name)){
				clickBackButton(1);
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

	public void CreateNewContactValidateSavedContact(String name,String location) throws InterruptedException
	{
		/*
		 * Validates Create New contact and validate saved contact
		 */
		try {
			minWait();
			CreateNewContactAndSave(name);
			customWait(2000);
			if(isElementExist(Locators_PhoneDialer_old.permissionPopUp)) {
				minWait();
				for(int i=0;i<3;i++){
					minWait();
					clickBtn(Locators_PhoneDialer_old.allowBtn);
				}
				validateSavedContact(name,location);
			}else{
				validateSavedContact(name,location);
			}
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
			clickBtn(Locators_PhoneDialer_old.createNewContactOpt);
			customWait(2000);
			enterTextToInputField(Locators_PhoneDialer_old.nameEditFld,name);
			customWait(2000);
			clickBtn(Locators_PhoneDialer_old.saveOpt);
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
			clickBtn(Locators_PhoneDialer_old.callLogPage);
			minWait();
			enterNumberInDialpad(Phone);
			minWait();
			CreateNewContactValidateSavedContact(name,"from Dialpad");
			minWait();
			clickBackButton(3);
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "Element Not Found");
		}
	}

	public void validateAddToContactFromCallLog(String name) throws InterruptedException
	{
		/*
		 * Validates Add to contact from call log
		 */
		try {
			minWait();
			clickBtn(Locators_PhoneDialer_old.callLogPage);
			customWait(2000);
			clickBtn(Locators_PhoneDialer_old.firstDialedNumber);
			minWait();
			clickBtn(Locators_PhoneDialer_old.addToContactOpt);
			minWait();
			CreateNewContactAndSave(name);
			customWait(2000);
			clickBtn(Locators_PhoneDialer_old.addedContactCallLog);
			minWait();
			clickBtn(Locators_PhoneDialer_old.contactPage);
			minWait();
			clickBtn(Locators_PhoneDialer_old.addedContactCallLog);
			minWait();
			validateSavedContact(name, "from Call Log");
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "Element Not Found");
		}
	}

	public void validateAddToContactFromDialpad(String Phone,String name) throws InterruptedException
	{
		/*
		 * Validates Add to contact from Dialpad
		 */
		try {
			minWait();
			clickBtn(Locators_PhoneDialer_old.callLogPage);
			customWait(2000);
			enterNumberInDialpad(Phone);
			minWait();
			clickBtn(Locators_PhoneDialer_old.addToContactOpt);
			minWait();
			CreateNewContactAndSave(name);
			customWait(2000);
			clickBackButton(2);
			clickBtn(Locators_PhoneDialer_old.contactPage);
			minWait();
			clickBtn(Locators_PhoneDialer_old.addedContactDialpad);
			minWait();
			validateSavedContact(name, "from Dialpad");
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "Element Not Found");
		}
	}

	public void validateSendMessageFromCallLog(String textMessage) throws InterruptedException
	{
		/*
		 * Validates send message from call Log
		 */
		try {
			minWait();
			clickBtn(Locators_PhoneDialer_old.callLogPage);
			minWait();
			clickBtn(Locators_PhoneDialer_old.addedContactCallLog);
			minWait();
			clickBtn(Locators_PhoneDialer_old.sendMessageInCallLog);
			minWait();
			sendMessageAndValidate(textMessage, "from call Log");
			clickBackButton(2);
			minWait();
			clickBtn(Locators_PhoneDialer_old.addedContactCallLog);
		} catch (NoSuchElementException e) {
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
			clickBtn(Locators_PhoneDialer_old.callLogPage);
			customWait(2000);
			element.click();
			minWait();
			clickBtn(Locators_PhoneDialer_old.callDetailsOpt);
			minWait();
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "Element Not Found");
		}
	}

	public void sendMessageAndValidate(String textMessage,String location) throws InterruptedException
	{
		/*
		 * Enters text message,send and validate
		 */
		SoftAssert SA= new SoftAssert();
		try {
			String deviceBaseband = p_b_No;
			minWait();
			if(deviceBaseband.contains("8A.0.0-01-7.1.1-30.") || deviceBaseband.contains("8A.0.0-03-7.1.1-10.")){
				sendMessageAndValidateATTAndReskin(textMessage);
			}else{
				customWait(2000);
				enterTextToInputField(Locators_PhoneDialer_old.messageEditFld, textMessage);
				customWait(2000);
				clickBtn(Locators_PhoneDialer_old.sendMessageBtn);
				customWait(2000);
			}
			if(isElementExist(Locators_PhoneDialer_old.messageSentNowOpt) || isElementExist(Locators_PhoneDialer_old.messageSentNowReskin))
			{
				check = true;
				APP_LOGS.info("Message sent successfully "+location);
				test.log(LogStatus.INFO, "Message sent successfully "+location);
				test.log(LogStatus.PASS, "Test case Status is PASS");
				SA.assertTrue(check, "Message sent successfully "+location);	
			}else {
				APP_LOGS.info("Message not sent");
				test.log(LogStatus.ERROR, "Message not sent");
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

	public void sendMessageAndValidateATTAndReskin(String textMessage) throws InterruptedException
	{
		/*
		 * Enters text message,send and validate in ATT and Reskin device
		 */
		try {
			if(isElementExist(Locators_PhoneDialer_old.nextOptInMessaging)) {
				minWait();
				clickBtn(Locators_PhoneDialer_old.nextOptInMessaging);
				minWait();
				if(isElementExist(Locators_PhoneDialer_old.permissionPopUp)) {
					minWait();
					clickBtn(Locators_PhoneDialer_old.allowBtn);
					minWait();
					sendMessageReskin(textMessage);
				}else{
					sendMessageReskin(textMessage);
				}
			}else{
				sendMessageReskin(textMessage);
			}
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "Element Not Found");
		}
	}

	public void sendMessageReskin(String textMessage) throws InterruptedException
	{
		/*
		 * Enters text in message edit field and clicks on send
		 */
		try {
			minWait();
			customWait(2000);
			enterTextToInputField(Locators_PhoneDialer_old.messageEditFldReskin, textMessage);
			customWait(2000);
			clickBtn(Locators_PhoneDialer_old.sendMessageReskin);
			customWait(2000);
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "Element Not Found");
		}
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

	public void validateSendMessageFromDialpad(String phone,String textMessage) throws InterruptedException
	{
		/*
		 * Validates send message from dialpad
		 */
		try {
			minWait();
			enterNumberInDialpad(phone);
			minWait();
			clickBtn(Locators_PhoneDialer_old.sendMessageInDialpad);
			minWait();
			sendMessageAndValidate(textMessage, "from Dialpad");
			minWait();
			clickBackButton(4);
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "Element Not Found");
		}
	}

	public void validateCallDetailsWithOutgoingCallLog() throws InterruptedException
	{
		/*
		 * Validates call details with outgoing text , arrow and contact name
		 */
		SoftAssert SA= new SoftAssert();
		try {
			minWait();
			if(isElementExist(Locators_PhoneDialer_old.addedContactCallLog) && isElementExist(Locators_PhoneDialer_old.outgoingCallText) && isElementExist(Locators_PhoneDialer_old.callTypeArrowIcon))
			{
				check = true;
				APP_LOGS.info("Verified call details with outgoing  call option");
				test.log(LogStatus.INFO, "Verified call details with outgoing  call option");
				test.log(LogStatus.PASS, "Test case Status is PASS");
				SA.assertTrue(check, "Verified call details with outgoing  call option");
			}else{
				APP_LOGS.info("Call detials page is not available");
				test.log(LogStatus.ERROR, "Call detials page is not available");
				test.log(LogStatus.FAIL, "Test case Status is FAIL");
				SA.fail();
			}
			clickBackButton(1);
			minWait();
			clickBtn(Locators_PhoneDialer_old.addedContactCallLog);
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			APP_LOGS.info("Element Not Found");
			test.log(LogStatus.ERROR, "Element Not Found");
			SA.fail();
		}
		SA.assertAll();
	}

	public void blockAndUnblockNumber() throws InterruptedException
	{
		/*
		 * Block And Unblock number and validate
		 */
		try {
			minWait();
			blockUnblockNumber(Locators_PhoneDialer_old.blockNumberOpt, Locators_PhoneDialer_old.blockBtn);
			minWait();
			validateBlockAndUnblockNumber(Locators_PhoneDialer_old.unblockNumberOpt, "blocked");
			minWait();
			blockUnblockNumber(Locators_PhoneDialer_old.unblockNumberOpt, Locators_PhoneDialer_old.unblockBtn);
			minWait();
			validateBlockAndUnblockNumber(Locators_PhoneDialer_old.blockNumberOpt, "Unblocked");
			minWait();
			clickBackButton(1);
			minWait();
			clickBtn(Locators_PhoneDialer_old.addedContactCallLog);
		} catch (NoSuchElementException e) {
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
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "Element Not Found");
		}
	}

	public void validateBlockAndUnblockNumber(WebElement element,String status) throws InterruptedException
	{
		/*
		 * Validates Block And Unblock number
		 */
		SoftAssert SA= new SoftAssert();
		try {
			clickBackButton(1);
			minWait();
			clickBtn(Locators_PhoneDialer_old.callDetailsOpt);
			minWait();
			if(isElementExist(element)){
				check = true;
				APP_LOGS.info("Number "+ status +" Successfully");
				test.log(LogStatus.INFO, "Number "+ status +" Successfully");
				SA.assertTrue(check, "Number "+ status +" Successfully");
			}else{
				APP_LOGS.info("Number not " +status);
				test.log(LogStatus.ERROR,"Number not " +status);
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

	public void validateCopyNumber() throws InterruptedException, IOException
	{
		/*
		 * validate copy number (Copy number --> paste in dialpad --> get pasted number and validate)
		 */
		SoftAssert SA= new SoftAssert();
		try {
			minWait();
			clickBtn(Locators_PhoneDialer_old.copyNumberOpt);
			minWait();
			clickBackButton(1);
			clickBtn(Locators_PhoneDialer_old.dialpadBtn);
			minWait();
			clickBtn(Locators_PhoneDialer_old.dialpadEditFld);
			minWait();
			aDriver.longPressKeyCode(50,28672);
			minWait();
			String copiedNumber = Locators_PhoneDialer_old.dialpadEditFld.getText().replaceAll(" ", "");
			if(copiedNumber.contains(refNum)){
				check = true;
				APP_LOGS.info("Copy Number option is validated successfully");
				test.log(LogStatus.INFO, "Copy Number option is validated successfully");
				test.log(LogStatus.PASS, "Test case Status is PASS");
				SA.assertTrue(check, "Copy Number option is validated successfully");
			}else{
				APP_LOGS.info("Copy Number option is not validated");
				test.log(LogStatus.ERROR, "Copy Number option is not validated");
				test.log(LogStatus.FAIL, "Test case Status is FAIL");
				SA.fail();
			}
			clickBackButton(2);
			minWait();
			clickBtn(Locators_PhoneDialer_old.addedContactCallLog);
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			APP_LOGS.info("Element Not Found");
			test.log(LogStatus.ERROR, "Element Not Found");
			SA.fail();
		}
		SA.assertAll();
	}

	public void validateEditBeforeCall(String phone) throws InterruptedException, IOException
	{
		/*
		 * Validate Edit number before call 
		 */
		SoftAssert SA= new SoftAssert();
		try {
			minWait();
			clickBtn(Locators_PhoneDialer_old.editBeforeCallOpt);
			minWait();
			Locators_PhoneDialer_old.dialpadEditFld.clear();
			minWait();
			enterTextToInputField(Locators_PhoneDialer_old.dialpadEditFld,pryNum);
			minWait();
			clickBtn(Locators_PhoneDialer_old.callBtn);
			customWait(5000);
			clickBtn(Locators_PhoneDialer_old.endCallBtn);
			customWait(2000);
			String editedNumber = Locators_PhoneDialer_old.editedNumber.getText().replaceAll(" ", "");
			if(editedNumber.contains(pryNum)) {
				check = true;
				APP_LOGS.info("Validated edit number before call option successfully");
				test.log(LogStatus.INFO, "Validated edit before call option successfully");
				test.log(LogStatus.PASS, "Test case Status is PASS");
				SA.assertTrue(check, "Validated edit before call option successfully");
			}else{
				APP_LOGS.info("Edit before call option is not verified");
				test.log(LogStatus.ERROR, "Edit before call option is not verified");
				test.log(LogStatus.FAIL, "Test case Status is FAIL");
				SA.fail();
			}
			customWait(2000);
			clickBtn(Locators_PhoneDialer_old.addedContactCallLog);
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			APP_LOGS.info("Element Not Found");
			test.log(LogStatus.ERROR, "Element Not Found");
			SA.fail();
		}
		SA.assertAll();
	}

	public void validateDelete() throws InterruptedException, IOException
	{
		/*
		 * validate delete option
		 */
		SoftAssert SA= new SoftAssert();
		try {
			minWait();
			clickBtn(Locators_PhoneDialer_old.deleteOpt);
			minWait();
			String contact = Locators_PhoneDialer_old.editedNumber.getText().replace(" ", "");
			if(!contact.contains(pryNum)){
				check = true;
				APP_LOGS.info("Contact deleted successfully");
				test.log(LogStatus.INFO, "Contact deleted successfully");
				test.log(LogStatus.PASS, "Test case Status is PASS");
				SA.assertTrue(check, "Contact deleted successfully");
			}else{
				APP_LOGS.info("Contact not deleted");
				test.log(LogStatus.ERROR, "Contact not deleted");
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

	public void validateAddFavorite() throws InterruptedException, IOException
	{
		/*
		 * validate Add a favorite option
		 */
		SoftAssert SA= new SoftAssert();
		try {
			minWait();
			clickBtn(Locators_PhoneDialer_old.favoritePage);
			minWait();
			clickBtn(Locators_PhoneDialer_old.addAFavorite);
			minWait();
			clickBtn(Locators_PhoneDialer_old.addedContactCallLog);
			minWait();
			clickBtn(Locators_PhoneDialer_old.favoriteIcon);
			minWait();
			clickBackButton(1);
			clickBtn(Locators_PhoneDialer_old.favoritePage);
			minWait();
			if(isElementExist(Locators_PhoneDialer_old.addedContactCallLog)){
				check = true;
				APP_LOGS.info("Contact added to favorite successfully");
				test.log(LogStatus.INFO, "Contact added to favorite successfully");
				test.log(LogStatus.PASS, "Test case Status is PASS");
				SA.assertTrue(check, "Contact added to favorite successfully");
			}else{
				APP_LOGS.info("Contact not added to favorite");
				test.log(LogStatus.ERROR, "Contact not added to favorite");
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

	public void validateCallFromFavorites() throws InterruptedException, IOException
	{
		/*
		 * validate call from favorites
		 */
		SoftAssert SA= new SoftAssert();
		try {
			minWait();
			clickBtn(Locators_PhoneDialer_old.favoritePage);
			minWait();
			clickBtn(Locators_PhoneDialer_old.addedContactCallLog);
			minWait();
			customWait(5000);
			if(isElementExist(Locators_PhoneDialer_old.callingViaOpt))
			{
				customWait(5000);
				clickBtn(Locators_PhoneDialer_old.endCallBtn);
				check = true;
				APP_LOGS.info("MO call placed successfully from Favorites");
				test.log(LogStatus.INFO, "MO call placed successfully from Favorites ");
				test.log(LogStatus.PASS, "Test case Status is PASS");
				SA.assertTrue(check, "MO call placed successfully from Favorites");
			} else	{
				APP_LOGS.info("MO call not placed from Favorites");
				test.log(LogStatus.ERROR, "MO call not placed from Favorites");
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

	public void validateRemoveFromFavorite() throws InterruptedException, IOException
	{
		/*
		 * validate remove from favorite 
		 */
		SoftAssert SA= new SoftAssert();
		try {
			minWait();
			clickBtn(Locators_PhoneDialer_old.favoritePage);
			minWait();
			if(isElementExist(Locators_PhoneDialer_old.addedContactCallLog)){
				clickBtn(Locators_PhoneDialer_old.settingInFavorite);
				minWait();
				clickBtn(Locators_PhoneDialer_old.favoriteIcon);
				minWait();
				clickBackButton(1);
				if(!isElementExist(Locators_PhoneDialer_old.favoriteIconInFavoritePage)){
					check = true;
					APP_LOGS.info("Contact removed from favorite successfully");
					test.log(LogStatus.INFO, "Contact removed from favorite successfully");
					test.log(LogStatus.PASS, "Test case Status is PASS");
					SA.assertTrue(check, "Contact removed from favorite successfully");
				}else{
					APP_LOGS.info("Contact not removed from favorite");
					test.log(LogStatus.ERROR, "Contact not removed from favorite");
					test.log(LogStatus.FAIL, "Test case Status is FAIL");
					SA.fail();
				}
			}else{
				APP_LOGS.info("Contact not available");
				test.log(LogStatus.ERROR, "Contact not available");
				test.log(LogStatus.SKIP, "Test case Status is FAIL");
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

	public void validateClearFrequents() throws InterruptedException, IOException
	{
		/*
		 * validate clear frequents 
		 */
		SoftAssert SA= new SoftAssert();
		try {
			minWait();
			if(!isElementExist(Locators_PhoneDialer_old.favoriteEmptyPage)){
				clickBtn(Locators_PhoneDialer_old.settingsIcon);
				minWait();
				clickBtn(Locators_PhoneDialer_old.clearFrequentsOpt);
				minWait();
				clickBtn(Locators_PhoneDialer_old.okBtn);
				customWait(2000);
				if(isElementExist(Locators_PhoneDialer_old.favoriteEmptyPage)){
					check = true;
					APP_LOGS.info("Clear frequents is validated successfully");
					test.log(LogStatus.INFO, "Clear frequents is validated successfully");
					test.log(LogStatus.PASS, "Test case Status is PASS");
					SA.assertTrue(check, "Clear frequents is validated successfully");
				}else{
					APP_LOGS.info("Clear frequents is not validated");
					test.log(LogStatus.ERROR, "Clear frequents is not validated");
					test.log(LogStatus.FAIL, "Test case Status is FAIL");
					SA.fail();
				}
			}else{
				APP_LOGS.info("Contacts not available in favorites page");
				test.log(LogStatus.ERROR, "Contacts not available in favorites page");
				test.log(LogStatus.SKIP, "Test case Status is FAIL");
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

	public void validateImportExportContacts(String contactName) throws InterruptedException, IOException
	{
		/*
		 * validate Import/Export contacts
		 */
		SoftAssert SA= new SoftAssert();
		try {
			minWait();
			//=============Export to SIM card================
			launch_an_app("phone");
			navigateToImportExport(Locators_PhoneDialer_old.exportToSIMCard);
			minWait();
			selectContactToImportExport();
			minWait();
			clickBtn(Locators_PhoneDialer_old.contactPage);
			searchContact(contactName);
			if(isElementExist(Locators_PhoneDialer_old.matchedContactOne) && isElementExist(Locators_PhoneDialer_old.matchedContactTwo)){
				check1 = true ;
				APP_LOGS.info("Contacts Exported to SIM successfully");
				test.log(LogStatus.INFO, "Contacts Exported to SIM successfully");
			}else{
				APP_LOGS.info("Failed to Export Contacts to SIM");
				test.log(LogStatus.INFO, "Failed to Export Contacts to SIM");
			}
			clickBackButton(2);
			//=============Import from SIM card================
			minWait();
			navigateToImportExport(Locators_PhoneDialer_old.importFromSIMCard);
			minWait();
			if(isElementExist(Locators_PhoneDialer_old.phoneOptInImport)){
				clickBtn(Locators_PhoneDialer_old.phoneOptInImport);
				minWait();
				importFromSIMCard(contactName);
			}else{
				importFromSIMCard(contactName);
			}
			clickBackButton(2);
			//=============Share all contacts================
			minWait();
			clickBtn(Locators_PhoneDialer_old.callLogPage);
			minWait();
			navigateToImportExport(Locators_PhoneDialer_old.shareAllContacts);
			minWait();
			clickBtn(Locators_PhoneDialer_old.selectOpt);
			minWait();
			clickBtn(Locators_PhoneDialer_old.selectAllOpt);
			minWait();
			clickBtn(Locators_PhoneDialer_old.okOptInImportExport);
			minWait();
			if(isElementExist(Locators_PhoneDialer_old.messagesOpt)) {
				clickBtn(Locators_PhoneDialer_old.messagesOpt);
				minWait();
				clickBtn(Locators_PhoneDialer_old.justOnceOpt);
			}else if(isElementExist(Locators_PhoneDialer_old.messagingOptReskin)){
				clickBtn(Locators_PhoneDialer_old.messagingOptReskin);
				minWait();
				clickBtn(Locators_PhoneDialer_old.justOnceOpt);
			}else{
				clickBtn(Locators_PhoneDialer_old.justOnceOpt);
			}
			minWait();
			if(isElementExist(Locators_PhoneDialer_old.addedContactCallLog))
			{
				clickBtn(Locators_PhoneDialer_old.addedContactCallLog);
				minWait();
			}else
			{
				clickBtn(Locators_PhoneDialer_old.toFldReskin);
				customWait(3000);
				Runtime.getRuntime().exec("adb -s "+ p_Id +" shell input text "+ contactName);
				customWait(3000);
				clickBtn(Locators_PhoneDialer_old.toFldContactSelect);
				customWait(2000);
			}
			if(isElementExist(Locators_PhoneDialer_old.mmsOpt)){
				minWait();
				clickBtn(Locators_PhoneDialer_old.mmsOpt);
			}else{
				minWait();
				clickBtn(Locators_PhoneDialer_old.mmsOptReskin);
			}
			customWait(2000);
			if(isElementExist(Locators_PhoneDialer_old.messageSentNowOpt) || isElementExist(Locators_PhoneDialer_old.sendingTextOpt) || isElementExist(Locators_PhoneDialer_old.sendingTextReskin) || isElementExist(Locators_PhoneDialer_old.messageSentNowReskin))
			{
				check4 = true ;
				APP_LOGS.info("Share all contacts is validated successfully");
				test.log(LogStatus.INFO, "Share all contacts is validated successfully");
			}else{
				APP_LOGS.info("Share all contacts is not validated");
				test.log(LogStatus.INFO, "Share all contacts is not validated");
			}	
			clickBackButton(2);
			if((check1) && (check2) && (check4)){
				check = true ;
				APP_LOGS.info("Import/Export to SIM Card and share all contacts option is validated successfully");
				test.log(LogStatus.INFO, "Import/Export to SIM Card and share all contacts option is validated successfully");
				test.log(LogStatus.PASS, "Test case Status is PASS");
				SA.assertTrue(check, "Import/Export to SIM Card and share all contacts option is validated successfully");
			}else{
				APP_LOGS.info("Import/Export option is not validated");
				test.log(LogStatus.INFO, "Import/Export option is not validated");
				test.log(LogStatus.FAIL, "Test case Status is FAIL");
				SA.fail("Import/Export option is not validated");
			}
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			APP_LOGS.info("Element Not Found");
			test.log(LogStatus.ERROR, "Element Not Found");
			SA.fail();
		}
		SA.assertAll();
	}

	public void importFromSIMCard(String contactName) throws InterruptedException, IOException
	{
		/*
		 * validates import from SIM card
		 */
		try {
			selectContactToImportExport();
			clickBtn(Locators_PhoneDialer_old.okBtn);
			minWait();
			clickBtn(Locators_PhoneDialer_old.contactPage);
			searchContact(contactName);
			if(isElementExist(Locators_PhoneDialer_old.matchedContactOne) && isElementExist(Locators_PhoneDialer_old.matchedContactTwo) && isElementExist(Locators_PhoneDialer_old.matchedContactThree))
			{
				check2 = true ;
				APP_LOGS.info("Contacts Imported from SIM successfully");
				test.log(LogStatus.INFO, "Contacts Imported from SIM successfully");
			}else{
				APP_LOGS.info("Failed to Import Contacts from SIM");
				test.log(LogStatus.INFO, "Failed to Import Contacts from SIM");
			}
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "Element Not Found");
		}
	}

	public void importFromVCFFile(String contactName) throws InterruptedException, IOException
	{
		/*
		 * validates import from VCF file
		 */
		try {
			clickBtn(Locators_PhoneDialer_old.exportedContact);
			minWait();
			searchContact(contactName);
			if(isElementExist(Locators_PhoneDialer_old.matchedContactOne) && isElementExist(Locators_PhoneDialer_old.matchedContactTwo) && isElementExist(Locators_PhoneDialer_old.matchedContactThree)  && isElementExist(Locators_PhoneDialer_old.matchedContactFour))
			{
				check3 = true ;
				APP_LOGS.info("Contacts Imported from .vcf file successfully");
				test.log(LogStatus.INFO, "Contacts Imported from .vcf file successfully");
			}else{
				APP_LOGS.info("Failed to Import Contacts from .vcf file");
				test.log(LogStatus.INFO, "Failed to Import Contacts from .vcf file");
			}
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "Element Not Found");
		}
	}

	public void navigateToImportExport(WebElement element) throws InterruptedException, IOException
	{
		/*
		 * navigate to import/export option with given input
		 */
		try {
			minWait();
			clickBtn(Locators_PhoneDialer_old.settingsIcon);
			minWait();
			clickBtn(Locators_PhoneDialer_old.importOrExportOpt);
			minWait();
			element.click();
			minWait();
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "Element Not Found");
		}
	}

	public void selectContactToImportExport() throws InterruptedException, IOException
	{
		/*
		 * selects contact to import/export
		 */
		try {
			minWait();
			clickBtn(Locators_PhoneDialer_old.addedContactCallLog);
			minWait();
			clickBtn(Locators_PhoneDialer_old.okOptInImportExport);
			minWait();
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "Element Not Found");
		}
	}

	public void searchContact(String contactName) throws InterruptedException, IOException
	{
		/*
		 * search contact 
		 */
		try {
			clickBtn(Locators_PhoneDialer_old.searchContactsFld);
			customWait(3000);
			Runtime.getRuntime().exec("adb -s "+ p_Id +" shell input text "+ contactName);
			customWait(2000);
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "Element Not Found");
		}
	}

	public void validateSettingsAndDisplayOptions() throws InterruptedException, IOException
	{
		/*
		 * validates settings and display option
		 */
		try {
			minWait();
			clickBtn(Locators_PhoneDialer_old.contactPage);
			minWait();
			validateSortByAndNameFormat(Locators_PhoneDialer_old.sortByOpt,Locators_PhoneDialer_old.firstNameOpt,Locators_PhoneDialer_old.firstNameContact, "Abc Auto");
			minWait();
			validateSortByAndNameFormat(Locators_PhoneDialer_old.sortByOpt,Locators_PhoneDialer_old.lastNameOpt,Locators_PhoneDialer_old.lastNameAndFirstNameFirstContact, "Test A");
			minWait();
			validateSortByAndNameFormat(Locators_PhoneDialer_old.nameFormatOpt, Locators_PhoneDialer_old.firstNameFirstOpt,Locators_PhoneDialer_old.lastNameAndFirstNameFirstContact, "Test A");
			minWait();
			validateSortByAndNameFormat(Locators_PhoneDialer_old.nameFormatOpt, Locators_PhoneDialer_old.lastNameFirstOpt,Locators_PhoneDialer_old.lastNameFirstContact, "A, Test");
			minWait();
		} catch (NoSuchElementException e) {
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
			minWait();
			clickBtn(Locators_PhoneDialer_old.settingsIcon);
			minWait();
			clickBtn(Locators_PhoneDialer_old.settingsOpt);
			minWait();
			element.click();
			minWait();
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "Element Not Found");
		}
	}

	public String selectDisplaySubOption(WebElement element,WebElement subElement) throws InterruptedException
	{
		/*
		 * Navigates to display options and clicks on given sub element
		 */
		navigateToSettingsAndElement(Locators_PhoneDialer_old.displayOptions);
		minWait();
		element.click();
		minWait();
		String field = subElement.getText();
		subElement.click();
		minWait();
		return field;
	}

	public void validateSortByAndNameFormat(WebElement element,WebElement subElement ,WebElement contactName,String Contact) throws InterruptedException {
		/*
		 * Common method to validate sortBy and name format option 
		 */
		SoftAssert SA= new SoftAssert();
		try {
			String field = selectDisplaySubOption(element, subElement);
			minWait();
			clickBackButton(2);
			String actualText = contactName.getText();
			System.out.println(actualText);
			minWait();
			if(actualText.equals(Contact)){
				check = true;
				APP_LOGS.info( field +" feature is validated successfully");
				test.log(LogStatus.INFO, field +" feature is validated successfully");
				SA.assertTrue(check, field +" feature is validated successfully");
			}else{
				APP_LOGS.info(field +" feature is not validated");
				test.log(LogStatus.ERROR, field +" feature is not validated");
				SA.fail();
			}
		}catch (NoSuchElementException e) {
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
			checkOtherSoundOptionsCheckboxStatus(Locators_PhoneDialer_old.alsoVibrateForCallsOpt, Locators_PhoneDialer_old.alsoVibrateCheckbox);
			customWait(2000);
			checkOtherSoundOptionsCheckboxStatus(Locators_PhoneDialer_old.dialpadTonesOpt, Locators_PhoneDialer_old.dialpadToneCheckbox);
			customWait(2000);
			checkOtherSoundOptionsCheckboxStatus(Locators_PhoneDialer_old.callEndToneOpt, Locators_PhoneDialer_old.callEndToneCheckbox);
			minWait();
		}catch (NoSuchElementException e) {
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
			clickBtn(Locators_PhoneDialer_old.phoneRingtoneOpt);
			minWait();
			clickBtn(Locators_PhoneDialer_old.ringtoneOpt);
			minWait();
			String expectedRingtone = Locators_PhoneDialer_old.ringtoneOpt.getText();
			minWait();
			clickBtn(Locators_PhoneDialer_old.okBtn);
			customWait(2000);
			String selectedRingtone = Locators_PhoneDialer_old.ringtoneSelected.getText();
			minWait();
			if(selectedRingtone.equals(expectedRingtone)){
				check = true;
				APP_LOGS.info("Phone Ringtone is validated successfully");
				test.log(LogStatus.INFO, "Phone Ringtone is validated successfully");
				SA.assertTrue(check, "Phone Ringtone is validated successfully");
			}else{
				APP_LOGS.info("Phone Ringtone is not validated");
				test.log(LogStatus.ERROR, "Phone Ringtone is not validated");
				SA.fail();
			}
		}catch (NoSuchElementException e) {
			e.printStackTrace();
			APP_LOGS.info("Element Not Found");
			test.log(LogStatus.ERROR, "Element Not Found");
			SA.fail();
		}
		SA.assertAll();
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
				test.log(LogStatus.INFO, soundResourceText+" is Enabled");
				SA.assertTrue(check,soundResourceText+" is Enabled");
			}else{
				APP_LOGS.info(soundResourceText+" is disabled");
				test.log(LogStatus.INFO, soundResourceText+" is disabled");
			}
		}catch (NoSuchElementException e) {
			e.printStackTrace();
			APP_LOGS.info("Element Not Found");
			test.log(LogStatus.ERROR, "Element Not Found");
			SA.fail();
		}
		SA.assertAll();
	}

	public void  validatePresenceOfDialpadToneLength() throws InterruptedException {
		/*
		 * Checks the presence of Dialpad tone length and sub options
		 */
		SoftAssert SA= new SoftAssert();
		try {
			minWait();
			clickBtn(Locators_PhoneDialer_old.dialPadTonelengthOpt);
			if(isElementExist(Locators_PhoneDialer_old.dialpadToneLengthNormal) && isElementExist(Locators_PhoneDialer_old.dialpadToneLengthLong))			
			{
				check = true;
				APP_LOGS.info("Dialpad tone length with Normal and Long options is present");
				test.log(LogStatus.INFO,"Dialpad tone length with Normal and Long options is present");
				SA.assertTrue(check,"Dialpad tone length with Normal and Long options is present");
			}else{
				APP_LOGS.info("Dialpad tone length with Normal and Long options is not present");
				test.log(LogStatus.ERROR, "Dialpad tone length with Normal and Long options is not present");
				SA.fail();
			}
			clickBackButton(3);
		}catch (NoSuchElementException e) {
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
			clickBtn(Locators_PhoneDialer_old.moreSettings);
			minWait();
			clickBtn(Locators_PhoneDialer_old.restoreDefaultsInQuickResponse);
			customWait(2000);
			getAndValidateQuickResponsesList();
			minWait();
			editAndValidateQuickResponse(data);
			minWait();
			restoreDefaults();
			clickBackButton(2);
		} catch (NoSuchElementException e) {
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
			getQuickResonseText(Locators_PhoneDialer_old.quickResponseTextOne,"Can't talk now. What's up?");
			minWait();
			getQuickResonseText(Locators_PhoneDialer_old.quickResponseTextTwo,"I'll call you right back.");
			minWait();
			getQuickResonseText(Locators_PhoneDialer_old.quickResponseTextThree,"I'll call you later.");
			minWait();
			getQuickResonseText(Locators_PhoneDialer_old.quickResponseTextFour,"Can't talk now. Call me later?");
		}catch (NoSuchElementException e) {
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
		}catch (NoSuchElementException e) {
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
			Locators_PhoneDialer_old.quickResponseText.click();
			minWait();
			Locators_PhoneDialer_old.quickResponseEditField.clear();
			minWait();
			enterTextToInputField(Locators_PhoneDialer_old.quickResponseEditField, data);
			minWait();
			Locators_PhoneDialer_old.okBtn.click();
			minWait();
			String actualEditedMessage = Locators_PhoneDialer_old.quickResponseText.getText();
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
		}catch (NoSuchElementException e) {
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
			clickBtn(Locators_PhoneDialer_old.moreSettings);
			minWait();
			clickBtn(Locators_PhoneDialer_old.restoreDefaultsInQuickResponse);
			customWait(2000);
			String actualString = Locators_PhoneDialer_old.quickResponseText.getText();
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
		}catch (NoSuchElementException e) {
			e.printStackTrace();
			APP_LOGS.info("Element Not Found");
			test.log(LogStatus.ERROR, "Element Not Found");
			SA.fail();
		}
		SA.assertAll();
	}

	public void validateSpeedDialSettings(String Phone,String name) throws InterruptedException, IOException{
		/*
		 * Validate All speed dial settings
		 */
		SoftAssert SA= new SoftAssert();
		try{
			minWait();
			presenceOfVoicemailInSpeedDailSettings();
			minWait();
			addContactInSpeedDailSettings(Phone);
			minWait();
			replaceContactInSpeedDailSettings(name);
			minWait();
			deleteContactInSpeedDailSettings();
			clickBackButton(2);
			check = true;
			APP_LOGS.info("Speed Dail settings validated successfully");
			test.log(LogStatus.INFO, "Speed Dail settings validated successfully");
			SA.assertTrue(check, "Speed Dail settings validated successfully");
		}catch (NoSuchElementException e) {
			e.printStackTrace();
			APP_LOGS.info("Element Not Found");
			test.log(LogStatus.ERROR, "Element Not Found");
			SA.fail();
		}
		SA.assertAll();
	}

	public void presenceOfVoicemailInSpeedDailSettings()
	{
		/*
		 * validates Presence of voicemail option
		 */
		SoftAssert SA= new SoftAssert();
		try
		{
			if(isElementExist(Locators_PhoneDialer_old.voiceMailOpt)){
				check = true;
				APP_LOGS.info("Voicemail Option is present in Speed Dail settings");
				test.log(LogStatus.INFO, "Voicemail Option is present in Speed Dail settings");
				SA.assertTrue(check, "Voicemail Option is present in Speed Dail settings");
			}else {
				APP_LOGS.info("Voicemail Option is not present in Speed Dail settings");
				test.log(LogStatus.INFO, "Voicemail Option is not present in Speed Dail settings");
				SA.fail("Voicemail Option is not present in Speed Dail settings");
			}
		}catch (NoSuchElementException e) {
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
		SoftAssert SA= new SoftAssert();
		try
		{
			clickBtn(Locators_PhoneDialer_old.notSetInSpeedDialSettings);
			minWait();
			enterTextToInputField(Locators_PhoneDialer_old.editFldInSpeedDialSettings, Phone);
			minWait();
			clickBtn(Locators_PhoneDialer_old.okBtn);
			minWait();
			if(isElementExist(Locators_PhoneDialer_old.addedContactInSpeedDialSettings))
			{
				check = true;
				APP_LOGS.info("Contact added in Speed Dail settings");
				test.log(LogStatus.INFO, "Contact added in Speed Dail settings");
				SA.assertTrue(check, "Contact added in Speed Dail settings");
			}else {
				APP_LOGS.info("Contact not added in Speed Dail settings");
				test.log(LogStatus.INFO, "Contact not added in Speed Dail settings");
				SA.fail("Contact not added in Speed Dail settings");
			}
		}catch (NoSuchElementException e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "Element Not Found");
		}
		SA.assertAll();
	}

	public void replaceContactInSpeedDailSettings(String name) throws InterruptedException, IOException
	{
		/*
		 * Replace contact in speed dial settings and validate
		 */
		SoftAssert SA= new SoftAssert();
		try
		{
			clickBtn(Locators_PhoneDialer_old.addedContactInSpeedDialSettings);
			minWait();
			clickBtn(Locators_PhoneDialer_old.replaceOptInSpeedDialSettings);
			minWait();
			clickBtn(Locators_PhoneDialer_old.selectContactInSpeedDialSettings);
			minWait();
			clickBtn(Locators_PhoneDialer_old.searchOpt);
			customWait(4000);
			Runtime.getRuntime().exec("adb -s "+ p_Id +" shell input text "+name);
			customWait(3000);
			clickBtn(Locators_PhoneDialer_old.firstNameContact);
			minWait();
			if(isElementExist(Locators_PhoneDialer_old.firstNameContact)){
				check = true;
				APP_LOGS.info("Contact replaced in Speed Dail settings");
				test.log(LogStatus.INFO, "Contact replaced in Speed Dail settings");
				SA.assertTrue(check, "Contact replaced in Speed Dail settings");
			}else {
				APP_LOGS.info("Contact not replaced in Speed Dail settings");
				test.log(LogStatus.INFO, "Contact not replaced in Speed Dail settings");
				SA.fail("Contact not replaced in Speed Dail settings");
			}
		}catch (NoSuchElementException e) {
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
			clickBtn(Locators_PhoneDialer_old.firstNameContact);
			minWait();
			clickBtn(Locators_PhoneDialer_old.delete);
			minWait();
			if(isElementExist(Locators_PhoneDialer_old.notSetInSpeedDialSettings)){
				check = true;
				APP_LOGS.info("Contact deleted in Speed Dail settings");
				test.log(LogStatus.INFO, "Contact deleted in Speed Dail settings");
				SA.assertTrue(check, "Contact deleted in Speed Dail settings");
			}else {
				APP_LOGS.info("Contact not deleted in Speed Dail settings");
				test.log(LogStatus.INFO, "Contact not deleted in Speed Dail settings");
				SA.fail("Contact not deleted in Speed Dail settings");
			}
		}catch (NoSuchElementException e) {
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
			clickBtn(Locators_PhoneDialer_old.callLogPage);
			minWait();
			enterNumberInDialpad(Phone);
			minWait();
			clickBtn(Locators_PhoneDialer_old.createNewContactOpt);
			minWait();
			clickBackButton(1);
			minWait();
			KeepEditingAndDiscard(Locators_PhoneDialer_old.keepEditingOpt, Locators_PhoneDialer_old.nameEditFld);
			minWait();
			clickBackButton(1);
			minWait();
			KeepEditingAndDiscard(Locators_PhoneDialer_old.discardBtn, Locators_PhoneDialer_old.dialpadEditFld);
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
				SA.assertTrue(check,option + " Option is verified successfully");
			}
			else{
				APP_LOGS.info(option + " Option is not available");
				test.log(LogStatus.INFO,option+ " Option is not available");
				SA.fail(option +" Option is not available");
			}
		}catch (NoSuchElementException e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "Element Not Found");
		}
		SA.assertAll();
	}

	public void validateAddContactFromContactsPage(String name,String phone) throws InterruptedException, IOException{
		/*
		 * Validate Add contact from contacts page and validate added contact 
		 */
		try
		{
			minWait();
			clickBtn(Locators_PhoneDialer_old.contactPage);
			minWait();
			clickBtn(Locators_PhoneDialer_old.createNewContactInContactPage);
			createContactWithNameandPhone(name, phone);
			minWait();
			validateSavedContact(name, "from Contacts page");
		}catch (NoSuchElementException e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "Element Not Found");
		}
	}

	public void createContactWithNameandPhone(String name,String phone) throws InterruptedException
	{
		/*
		 * Enter name and phone number and save the contact
		 */
		try {
			customWait(2000);
			enterTextToInputField(Locators_PhoneDialer_old.nameEditFld, name);
			minWait();
			clickBackButton(1);
			enterTextToInputField(Locators_PhoneDialer_old.phoneNumberEditFld, phone);
			minWait();
			clickBtn(Locators_PhoneDialer_old.saveOpt);
			minWait();
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "Element Not Found");
		}
	}

	public void validateCancel() throws InterruptedException, IOException
	{
		/*
		 * Validates cancel option 
		 */
		SoftAssert SA= new SoftAssert();
		try {
			//============================Block scenario==========================
			minWait();
			navigateToCallDetails(Locators_PhoneDialer_old.addedContactCallLog);
			minWait();
			clickBtn(Locators_PhoneDialer_old.blockNumberOpt);
			minWait();
			if(isElementExist(Locators_PhoneDialer_old.cancelBtn)){
				clickBtn(Locators_PhoneDialer_old.cancelBtn);
				check5 = true;
				APP_LOGS.info("Cancel Option is verified in Block scenario");
				test.log(LogStatus.INFO, "Cancel Option is verified in Block scenario");
			}
			clickBackButton(1);
			//============================Unblock Scenario==========================
			minWait();
			clickBtn(Locators_PhoneDialer_old.callDetailsOpt);
			minWait();
			blockUnblockNumber(Locators_PhoneDialer_old.blockNumberOpt, Locators_PhoneDialer_old.blockBtn);
			minWait();
			clickBackButton(1);
			clickBtn(Locators_PhoneDialer_old.callDetailsOpt);
			minWait();
			clickBtn(Locators_PhoneDialer_old.unblockNumberOpt);
			minWait();
			if(isElementExist(Locators_PhoneDialer_old.cancelBtn)){
				clickBtn(Locators_PhoneDialer_old.cancelBtn);
				check6 = true;
				APP_LOGS.info("Cancel Option is verified in Unblock scenario");
				test.log(LogStatus.INFO, "Cancel Option is verified in Unblock scenario");
			}
			clickBackButton(1);
			minWait();
			clickBtn(Locators_PhoneDialer_old.callDetailsOpt);
			minWait();
			blockUnblockNumber(Locators_PhoneDialer_old.unblockNumberOpt, Locators_PhoneDialer_old.unblockBtn);
			clickBackButton(1);
			//=============================Clear call history scenario===============================
			minWait();
			navigateTocallHistory();
			minWait();
			clickBtn(Locators_PhoneDialer_old.moreOptionsInCallHistory);
			minWait();
			clickBtn(Locators_PhoneDialer_old.clearCallHistoryOpt);
			minWait();
			clickBtn(Locators_PhoneDialer_old.addedContactCallLog);
			minWait();
			clickBtn(Locators_PhoneDialer_old.clearOptInCallHistory);
			minWait();
			if(isElementExist(Locators_PhoneDialer_old.cancelBtn)){
				clickBtn(Locators_PhoneDialer_old.cancelBtn);
				check7 = true;
				APP_LOGS.info("Cancel Option is verified in clear call log scenario");
				test.log(LogStatus.INFO, "Cancel Option is verified in clear call log scenario");
			}
			clickBackButton(2);
			//==========================Phone Ringtone scenario===================================
			minWait();
			navigateToSettingsAndElement(Locators_PhoneDialer_old.soundsAndVibration);
			minWait();
			clickBtn(Locators_PhoneDialer_old.phoneRingtoneOpt);
			minWait();
			if(isElementExist(Locators_PhoneDialer_old.cancelBtn)){
				clickBtn(Locators_PhoneDialer_old.cancelBtn);
				check8 = true;
				APP_LOGS.info("Cancel Option is verified in Phone ringtone scenario");
				test.log(LogStatus.INFO, "Cancel Option is verified in Phone ringtone scenario");
			}
			//========================Dialpad  tone length========================================
			minWait();
			clickBtn(Locators_PhoneDialer_old.dialPadTonelengthOpt);
			minWait();
			if(isElementExist(Locators_PhoneDialer_old.cancelBtn)){
				clickBtn(Locators_PhoneDialer_old.cancelBtn);
				check9 = true;
				APP_LOGS.info("Cancel Option is verified in Dialpad tone length scenario");
				test.log(LogStatus.INFO, "Cancel Option is verified in Dialpad tone length scenario");
			}
			clickBackButton(1);
			//==========================Quick responses===========================================
			minWait();
			clickBtn(Locators_PhoneDialer_old.quickResponseOpt);
			minWait();
			clickBtn(Locators_PhoneDialer_old.quickResponseText);
			minWait();
			if(isElementExist(Locators_PhoneDialer_old.cancelBtn)){
				clickBtn(Locators_PhoneDialer_old.cancelBtn);
				check10 = true;
				APP_LOGS.info("Cancel Option is verified in Quick responses scenario");
				test.log(LogStatus.INFO, "Cancel Option is verified in Quick responses scenario");
			}
			clickBackButton(1);
			//=========================Speed dial settings=========================================
			minWait();
			clickBtn(Locators_PhoneDialer_old.speedDialSettingsOpt);
			minWait();
			clickBtn(Locators_PhoneDialer_old.notSetInSpeedDialSettings);
			minWait();
			if(isElementExist(Locators_PhoneDialer_old.cancelBtn)){
				clickBtn(Locators_PhoneDialer_old.cancelBtn);
				check11 = true;
				APP_LOGS.info("Cancel Option is verified in Speed dial settings scenario");
				test.log(LogStatus.INFO, "Cancel Option is verified in Speed dial settings scenario");
			}
			clickBackButton(2);
			//=======================Import from SIM card scenario================================
			minWait();
			navigateToImportExport(Locators_PhoneDialer_old.importFromSIMCard);
			minWait();
			if(isElementExist(Locators_PhoneDialer_old.phoneOptInImport)){
				clickBtn(Locators_PhoneDialer_old.phoneOptInImport);
				selectContactToImportExport();
				minWait();
			}else{
				selectContactToImportExport();
			}
			if(isElementExist(Locators_PhoneDialer_old.cancelBtn)){
				clickBtn(Locators_PhoneDialer_old.cancelBtn);
				check12 = true;
				APP_LOGS.info("Cancel Option is verified in Import from SIM card scenario");
				test.log(LogStatus.INFO, "Cancel Option is verified in Import from SIM Card scenario");
			}
			clickBackButton(1);
			//=======================Export to SIm Card===========================================
			minWait();
			navigateToImportExport(Locators_PhoneDialer_old.exportToSIMCard);
			minWait();
			clickBtn(Locators_PhoneDialer_old.selectOpt);
			minWait();
			clickBtn(Locators_PhoneDialer_old.selectAllOpt);
			minWait();
			clickBtn(Locators_PhoneDialer_old.okOptInImportExport);
			minWait();
			if(isElementExist(Locators_PhoneDialer_old.cancelBtn)){
				clickBtn(Locators_PhoneDialer_old.cancelBtn);
				check13 = true;
				APP_LOGS.info("Cancel Option is verified in Export to SIM card scenario");
				test.log(LogStatus.INFO, "Cancel Option is verified in EXport to SIM Card scenario");
			}
			//=========================Share all contacts=============================================
			String deviceBaseband = p_b_No;
			minWait();
			if(deviceBaseband.contains("8A.0.0-01-7.1.1-30.") || deviceBaseband.contains("8A.0.0-03-7.1.1-10."))
			{
				check14 = true;
			}else {
				minWait();
				navigateToImportExport(Locators_PhoneDialer_old.shareAllContacts);
				minWait();
				clickBtn(Locators_PhoneDialer_old.addedContactCallLog);
				minWait();
				clickBtn(Locators_PhoneDialer_old.okOptInImportExport);
				minWait();
				if(isElementExist(Locators_PhoneDialer_old.messagesOpt)){
					clickBtn(Locators_PhoneDialer_old.messagesOpt);
					minWait();
					clickBtn(Locators_PhoneDialer_old.justOnceOpt);
				}else if(isElementExist(Locators_PhoneDialer_old.messagingOptReskin)){
					clickBtn(Locators_PhoneDialer_old.messagingOptReskin);
					minWait();
					clickBtn(Locators_PhoneDialer_old.justOnceOpt);
				}else{
					clickBtn(Locators_PhoneDialer_old.justOnceOpt);
				}
				minWait();
				if(isElementExist(Locators_PhoneDialer_old.cancelBtn)){
					clickBtn(Locators_PhoneDialer_old.cancelBtn);
					check14 = true;
					APP_LOGS.info("Cancel Option is verified in Share all contacts scenario");
					test.log(LogStatus.INFO, "Cancel Option is verified in Share all contacts scenario");
				}
			}
			if((check5) && (check6) && (check7) && (check8) && (check9) && (check10) && (check11) && (check12) && (check13) && (check14))
			{
				check = true;
				APP_LOGS.info("Cancel option is verified");
				test.log(LogStatus.INFO, "Cancel option is verified");
				SA.assertTrue(check, "Cancel option is verified");	
			}else{
				APP_LOGS.info("Cancel option is not verified");
				test.log(LogStatus.ERROR,"Cancel option is not verified");
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

	public void navigateTocallHistory() throws InterruptedException, IOException
	{
		/*
		 * navigate to call History page
		 */
		try {
			minWait();
			clickBtn(Locators_PhoneDialer_old.settingsIcon);
			minWait();
			clickBtn(Locators_PhoneDialer_old.callHistoryOpt);
			minWait();
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "Element Not Found");
		}
	}

	public void validateCallHistory() throws InterruptedException, IOException {
		/*
		 * Validate call history based on build
		 */
		try {
			String deviceBaseband = p_b_No;
			minWait();
			if(deviceBaseband.contains("8A.0.0-01-7.1.1-30.")){
				validateCallHistoryReskin(Locators_PhoneDialer_old.allCallsReskin);
				minWait();
				validateCallHistoryReskin(Locators_PhoneDialer_old.incomingCallsReskin);
				minWait();
				validateCallHistoryReskin(Locators_PhoneDialer_old.outgoingCallsReskin);
				minWait();
				validateCallHistoryReskin(Locators_PhoneDialer_old.missedCallsReskin);
			}else{
				validateCallHistoryAllOperator(Locators_PhoneDialer_old.allOptInCallHistory);
				minWait();
				validateCallHistoryAllOperator(Locators_PhoneDialer_old.missedOptInCallHistory);
			}
			clickBackButton(1);
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "Element Not Found");
		}
	}

	public void validateCallHistoryAllOperator(WebElement element) throws InterruptedException, IOException{
		/*
		 * Validate call history (All log and missed call Logs) All operators except reskin
		 */
		SoftAssert SA= new SoftAssert();
		try
		{
			minWait();
			element.click();
			minWait();
			String callHistoryPage = element.getText();
			minWait();
			if(isElementExist(Locators_PhoneDialer_old.todayOptInCallHistory) || isElementExist(Locators_PhoneDialer_old.olderOptInCallHistory) || isElementExist(Locators_PhoneDialer_old.callLogEmptyPage) || isElementExist(Locators_PhoneDialer_old.noMissedCallsInCallHistory))
			{
				check = true;
				APP_LOGS.info(callHistoryPage + " calls Option in Call History is verified successfully");
				test.log(LogStatus.INFO, callHistoryPage + " calls Option in Call History is verified successfully");
				SA.assertTrue(check,callHistoryPage + " calls Option in Call History is verified successfully");
			}else{
				APP_LOGS.info(callHistoryPage + " calls Option in Call History is not verified");
				test.log(LogStatus.INFO,callHistoryPage + " calls Option in Call History is not verified");
				SA.fail(callHistoryPage + " calls Option in Call History is not verified");
			}
		}catch (NoSuchElementException e) {
			e.printStackTrace();
			APP_LOGS.info("Element Not Found");
			test.log(LogStatus.ERROR, "Element Not Found");
			SA.fail();
		}
		SA.assertAll();
	}

	public void validateCallHistoryReskin(WebElement element) throws InterruptedException, IOException{
		/*
		 * Validate call history in Reskin
		 */
		SoftAssert SA= new SoftAssert();
		try
		{
			minWait();
			clickBtn(Locators_PhoneDialer_old.logTypeSelectReskin);
			minWait();
			String callHistoryPage = element.getText();
			minWait();
			element.click();
			minWait();
			if(callHistoryPage.equals("All calls")){
				clickBackButton(1);
				navigateTocallHistory();
			}
			if(isElementExist(Locators_PhoneDialer_old.todayOptInCallHistory) || isElementExist(Locators_PhoneDialer_old.olderOptInCallHistory) || isElementExist(Locators_PhoneDialer_old.noCallsInCallHistory) || isElementExist(Locators_PhoneDialer_old.noIncomingCallsInCallHistory) || isElementExist(Locators_PhoneDialer_old.noOutgoingCallsInCallHistory) || isElementExist(Locators_PhoneDialer_old.noMissedCallsInCallHistory))
			{
				check = true;
				APP_LOGS.info(callHistoryPage + " calls Option in Call History is verified successfully");
				test.log(LogStatus.INFO, callHistoryPage + " calls Option in Call History is verified successfully");
				SA.assertTrue(check,callHistoryPage + " calls Option in Call History is verified successfully");
			}else{
				APP_LOGS.info(callHistoryPage + " calls Option in Call History is not verified");
				test.log(LogStatus.INFO,callHistoryPage + " calls Option in Call History is not verified");
				SA.fail(callHistoryPage + " calls Option in Call History is not verified");
			}
		}catch (NoSuchElementException e) {
			e.printStackTrace();
			APP_LOGS.info("Element Not Found");
			test.log(LogStatus.ERROR, "Element Not Found");
			SA.fail();
		}
		SA.assertAll();
	}

	public void validateSearchCallLog(String name) throws InterruptedException, IOException{
		/*
		 * Search for a call log with given name and validates
		 */
		SoftAssert SA= new SoftAssert();
		try
		{
			minWait();
			clickBtn(Locators_PhoneDialer_old.moreOptionsInCallHistory);
			minWait();
			clickBtn(Locators_PhoneDialer_old.searchCallLogOpt);
			minWait();
			if(!isElementExist(Locators_PhoneDialer_old.callLogEmptyPage)){
				minWait();
				Runtime.getRuntime().exec("adb -s "+ p_Id +" shell input text "+ name);
				minWait();
				if(isElementExist(Locators_PhoneDialer_old.addedContactCallLog)){
					clickBackButton(2);
					check = true;
					APP_LOGS.info( "Search call log Option in Call History is verified successfully");
					test.log(LogStatus.INFO,  "Search call Log Option in Call History is verified successfully");
					SA.assertTrue(check, "Search Call Log Option in Call History is verified successfully");
				}else{
					clickBackButton(2);
					APP_LOGS.info("Search call log Option in Call History is not verified");
					test.log(LogStatus.INFO,"Search call log Option in Call History is not verified");
					SA.fail("Search call log Option in Call History is not verified");
				}
			}else{
				clickBackButton(1);
				APP_LOGS.info("Call logs are not available");
				test.log(LogStatus.SKIP,"Call logs are not available");
			}
		}catch (NoSuchElementException e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "Element Not Found");
		}
		SA.assertAll();
	}

	public void validateClearCallHistory()  throws InterruptedException, IOException{
		/*
		 * clear call history if present and validate
		 */
		SoftAssert SA= new SoftAssert();
		try
		{
			minWait();
			clickBtn(Locators_PhoneDialer_old.moreOptionsInCallHistory);
			minWait();
			if(isElementExist(Locators_PhoneDialer_old.clearCallHistoryOpt)){
				clickBtn(Locators_PhoneDialer_old.clearCallHistoryOpt);
				minWait();
				clickBtn(Locators_PhoneDialer_old.selectOpt);
				minWait();
				clickBtn(Locators_PhoneDialer_old.selectAllOpt);
				minWait();
				clickBtn(Locators_PhoneDialer_old.clearOptInCallHistory);
				minWait();
				clickBtn(Locators_PhoneDialer_old.okBtn);
				customWait(3000);
				if(isElementExist(Locators_PhoneDialer_old.noCallLogInCallHistory) || isElementExist(Locators_PhoneDialer_old.callLogEmptyPage))
				{
					check = true;
					APP_LOGS.info("Call logs are cleared successfully");
					test.log(LogStatus.INFO,"Call logs are cleared successfully");
					SA.assertTrue(check,"Call logs are cleared successfully");
				}else{
					APP_LOGS.info("Call logs are not cleared");
					test.log(LogStatus.INFO,"Call logs are not cleared");
					SA.fail("Call logs are not cleared");
				}
			}else{
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_HOME);
				APP_LOGS.info("Call logs are not available");
				test.log(LogStatus.SKIP,"Call logs are not available");
			}
		}catch (NoSuchElementException e) {
			e.printStackTrace();
			APP_LOGS.info("Element Not Found");
			test.log(LogStatus.ERROR, "Element Not Found");
			SA.fail();
		}
		SA.assertAll();
	}


}