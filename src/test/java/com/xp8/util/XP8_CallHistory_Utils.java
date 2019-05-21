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
import io.appium.java_client.android.AndroidElement;
//import application.AllQA;
import io.appium.java_client.android.AndroidKeyCode;
import net.sourceforge.tess4j.TesseractException;

public class XP8_CallHistory_Utils extends BaseUtil{

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

	SoftAssert SA = new SoftAssert();
	public void fetch_Devices_Details() throws InterruptedException, FileNotFoundException, IOException,org.json.simple.parser.ParseException {

		minWait();
		p_Id=JsonFileReaderAndWriter.primaryDevIdReader();
		r_Id=JsonFileReaderAndWriter.ReadRefDeviceId();
		p_b_No=JsonFileReaderAndWriter.primaryDevFirmwareReader();
		r_b_No=JsonFileReaderAndWriter.ReadRefDeviceFirmWare();
	}
	public void validatePhoneAppLaunch() throws InterruptedException
	{
		/*
		 * Validates Phone application Launch
		 */
		SoftAssert SA= new SoftAssert();
		try {
			minWait();
			//launch_an_app("phone");
			clickBtn(Locators_XP8_CallHistory.allowBtn);
			minWait();
			if(isElementExist(Locators_XP8_CallHistory.phoneFrame)){
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

	public void navigateTocallHistory() throws InterruptedException, IOException
	{
		/*
		 * navigate to call History page
		 */		 
		try {
			minWait();
			clickBtn(Locators_XP8_CallHistory.settingsIcon);
			minWait();
			clickBtn(Locators_XP8_CallHistory.callHistoryOpt);
			minWait();
		} catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "Element Not Found");
		}
	}

	public void validateCallLogList() throws InterruptedException{
		SoftAssert SA= new SoftAssert();
		try {
			customWait(2000);
			if(isElementExist(Locators_XP8_CallHistory.callLogWithSymbols))
			{
				check = true ;
				APP_LOGS.info("All Call Log List with Symbol is verified");
				test.log(LogStatus.INFO, "All Call Log List with Symbol is verified");
				test.log(LogStatus.PASS, "Test case Status is PASS");
				SA.assertTrue(check, "All Call Log List with Symbol is verified");
			}else{
				System.out.println("No Contacts");
				//test.log(LogStatus.ERROR, "Element Not Found");
				APP_LOGS.info("All Call Log List with Symbol is not verified");
				test.log(LogStatus.ERROR, "All Call Log List with Symbol is not verified");
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

	public void validateContactFromCallLog() throws InterruptedException{
		SoftAssert SA= new SoftAssert();
		try {
			if (isElementExist(Locators_XP8_CallHistory.contactDetails)) {
				minWait();
				clickBtn(Locators_XP8_CallHistory.contactDetails);
				minWait();
				clickBtn(Locators_XP8_CallHistory.callDetailsOpt);
				minWait();
				clickBtn(Locators_XP8_CallHistory.contactLogo);
			}
		} 
		/*			else{
				System.out.println("No Contacts Available");
				APP_LOGS.info("Saved Contact is not available");
				test.log(LogStatus.ERROR, "Saved Contact is not available");
				test.log(LogStatus.FAIL, "Test case Status is FAIL");
				SA.fail();

			}*/
		catch (Exception e) {
			e.printStackTrace();
			APP_LOGS.info("Element Not Found");
			test.log(LogStatus.ERROR, "Element Not Found");
			SA.fail();
		}		
	}
	public void validateCallFromCallLog() throws InterruptedException, IOException
	{
		/*
		 * Validates call from call Log(Call to a number from call log)
		 */
		SoftAssert SA = new SoftAssert();
		try {
			minWait();
			clickBtn(Locators_XP8_CallHistory.callLogPage);
			minWait();
			if(isElementExist(Locators_XP8_CallHistory.recentCallLogNumber)) {
				clickBtn(Locators_XP8_CallHistory.callBtnInCallLog);
				customWait(5000);
				validateMOCall("from recent Call Log", refNum);
				clickBackButton(1);
				clickBtn(Locators_XP8_CallHistory.recentCallLogNumber);
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
	public void validateMOCall(String call,String phone) throws InterruptedException, IOException
	{

		/* validate MO call with UI*/

		SoftAssert SA= new SoftAssert();
		try {
			//reciveCallInRefDevice(refNum);
			customWait(2000);
			clickBtn(Locators_XP8_CallHistory.endCallBtn);
			minWait();
			clickBtn(Locators_XP8_CallHistory.firstDialedNumber);
			minWait();
			clickBtn(Locators_XP8_CallHistory.callDetailsOpt);
			minWait();
			String[] callDuration = Locators_XP8_CallHistory.durationInSec.getText().split(" ");
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
	public void clickBackButton(int number)
	{
		/*
		 * clicks on back button with iteration as user input
		 */
		try {
			for(int i=0;i<number;i++){
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
				//aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_R);
				minWait();
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	/*public void reciveCallInRefDevice(String refNum) throws InterruptedException, IOException {

	 * Receive Call in Reference device

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
	}*/
	public void validateCreateNewContactFromCallLog(String name) throws InterruptedException
	{
		/*
		 * Validates Create New contact from call log
		 */
		try {
			minWait();
			clickBtn(Locators_XP8_CallHistory.callLogPage);
			customWait(2000);
			clickBtn(Locators_XP8_CallHistory.recentCallLogNumber);
			minWait();
			CreateNewContactValidateSavedContact(name,"from call Log");
			minWait();
			clickBackButton(1);
		} catch (NoSuchElementException e) {
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
			if(isElementExist(Locators_XP8_CallHistory.permissionPopUp)) {
				minWait();
				for(int i=0;i<3;i++){
					minWait();
					clickBtn(Locators_XP8_CallHistory.allowBtn);
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
			clickBtn(Locators_XP8_CallHistory.createNewContactOpt);
			customWait(3000);
			enterTextToInputField(Locators_XP8_CallHistory.nameEditFld,name);
			customWait(2000);
			clickBtn(Locators_XP8_CallHistory.saveContactOpt);
			validate_frequent_list();
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
			String savedContact = Locators_XP8_CallHistory.savedContact.getText();
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

	public void validate_frequent_list() {
		try {
			minWait();
			/*clickBtn(Locators_XP8_CallHistory.callLogPage);
			minWait();
			clickBtn(Locators_XP8_CallHistory.callBtnInCallLog);
			customWait(3000);
			clickBtn(Locators_XP8_CallHistory.endCallBtn);
			minWait();
			clickBtn(Locators_XP8_CallHistory.contactPage);
			minWait();
			clickBtn(Locators_XP8_CallHistory.contact_two);
			minWait();*/
			clickBtn(Locators_XP8_CallHistory.fav_add_star_option);
			clickBackButton(2);
			minWait();
			clickBtn(Locators_XP8_CallHistory.FrequentPage);
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
			if(isElementExist(Locators_XP8_CallHistory.contact_one)){// && isElementExist(Locators_XP8_CallHistory.contact_two)) {
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

	public void validate_Missed_Call_Log() throws InterruptedException, IOException{
		try {			
			minWait();
			clickBtn(Locators_XP8_CallHistory.missedCallOpt);				
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "Element Not Found");
		}
	}


	public void validate_Call_From_Missed_Call_Log() throws InterruptedException{
		try {
			minWait();
			clickBtn(Locators_XP8_CallHistory.callBtnInCallLog);
		} catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "Element Not Found");
		}		
	}

	public void validate_send_message(String textMessage) {
		SoftAssert SA = new SoftAssert();
		try {
			clickBtn(Locators_XP8_CallHistory.callLogPage);
			minWait();
			clickBtn(Locators_XP8_CallHistory.recentCallLogNumber);
			minWait();
			clickBtn(Locators_XP8_CallHistory.Send_message);
			minWait();
			if(isElementExist(Locators_XP8_CallHistory.permissionPopUp)) {
				minWait();
				for(int i=0;i<3;i++){
					minWait();
					clickBtn(Locators_XP8_CallHistory.allowBtn);
				}
			}
			enterTextToInputField(Locators_XP8_CallHistory.messageEditFld, textMessage);
			customWait(3000);
			/*	clickBtn(Locators_XP8_CallHistory.messageEditFld);
			customWait(2000);
			Runtime.getRuntime().exec("adb -s "+ p_Id +" shell input text "+ textMessage);*/
			clickBtn(Locators_XP8_CallHistory.sendMessage);
			customWait(7000);
			if(isElementExist(Locators_XP8_CallHistory.messageSentNow))
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
	public void enterNumberInDialpad(String Phone) throws InterruptedException
	{
		/*
		 * clicks dialpad and enters number
		 */
		SoftAssert SA = new SoftAssert();
		try {
			clickBtn(Locators_XP8_CallHistory.dialpadBtn);
			customWait(2000);
			enterTextToInputField(Locators_XP8_CallHistory.dialpadEditFld, Phone);
			customWait(2000);
			if(isElementExist(Locators_XP8_CallHistory.dailedContactDetails)){
				customWait(2000);
				check = true;
				APP_LOGS.info("Contact is already saved");
				test.log(LogStatus.INFO, "Contact is already saved ");
				test.log(LogStatus.PASS, "Test case Status is PASS");
				SA.assertTrue(check, "Contact is already saved");	
			}else {
				clickBtn(Locators_XP8_CallHistory.dailedContactDetails);
				APP_LOGS.info("Contact is not saved");
				test.log(LogStatus.ERROR, "Contact is not saved");
				test.log(LogStatus.FAIL, "Test case Status is FAIL");
				SA.fail();
			}	
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "Element Not Found");
		}
	}

	public void validateClearCallHistory() throws InterruptedException, IOException{
		SoftAssert SA = new SoftAssert();
		try {
			navigateTocallHistory();
			minWait();
			clickBtn(Locators_XP8_CallHistory.moreOptionsInCallHistory);
			minWait();
			clickBtn(Locators_XP8_CallHistory.clearCallHistoryOpt);
			minWait();
			clickBtn(Locators_XP8_CallHistory.selectOpt);
			minWait();
			clickBtn(Locators_XP8_CallHistory.selectAllOpt);
			minWait();
			if(isElementExist(Locators_XP8_CallHistory.clearOptInCallHistory)){
				clickBtn(Locators_XP8_CallHistory.selectOpt);
				minWait();
				clickBtn(Locators_XP8_CallHistory.UnselectAllOpt);
			}
			clickBackButton(1);
			minWait();
			clickBtn(Locators_XP8_CallHistory.moreOptionsInCallHistory);
			minWait();
			clickBtn(Locators_XP8_CallHistory.clearCallHistoryOpt);
			minWait();
			clickBtn(Locators_XP8_CallHistory.selectOpt);
			minWait();
			clickBtn(Locators_XP8_CallHistory.selectAllOpt);
			minWait();
			clickBtn(Locators_XP8_CallHistory.selectAllOpt);
			minWait();
			clickBtn(Locators_XP8_CallHistory.clearOptInCallHistory);
			minWait();
			clickBtn(Locators_XP8_CallHistory.Ok_option);
			minWait();
			clickBtn(Locators_XP8_CallHistory.moreOptionsInCallHistory);
			if(!isElementExist(Locators_XP8_CallHistory.clearCallHistoryOpt)){
				customWait(2000);
				check = true;
				APP_LOGS.info("Clear Call History option is not available");
				test.log(LogStatus.INFO, "Clear Call History option is not available");
				test.log(LogStatus.PASS, "Test case Status is PASS");
				SA.assertTrue(check, "Clear Call History option is not available");	
			}else {
				APP_LOGS.info("Clear Call History option is available");
				test.log(LogStatus.ERROR, "Clear Call History option is available");
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

	public void validate_Permission_Popup_In_Call_Logs() throws InterruptedException{
		/*
		Validates if Permission Pop Up appears and validates if ALLOW button is greyed out when "Dont Ask me again" check box is checked
		 */
		SoftAssert SA = new SoftAssert();
		try {
			if(isElementExist(Locators_XP8_CallHistory.permissionPopUp)){
				clickBtn(Locators_XP8_CallHistory.denyBtn);
			}
			clickBackButton(1);
			customWait(5000);
			launch_an_app("phone");
			minWait();
			selectCheckbox(Locators_XP8_CallHistory.dontAskAgain);
			customWait(5000);
			if(isElementExist(Locators_XP8_CallHistory.allowBtn)){
				//customWait(2000);
				clickBtn(Locators_XP8_CallHistory.denyBtn);
				//System.out.println("Am I getting into DENY?");
				check = true;
				APP_LOGS.info("Allow button is Disabled");
				test.log(LogStatus.PASS, "Allow button is enabled");
				SA.assertTrue(check, "Allow button is enabled");
			}
			else{
				customWait(5000);
				clickBtn(Locators_XP8_CallHistory.allowBtn);
				//System.out.println("Am I getting into ALLOW?");
			}
		} catch (Exception e) {
			e.printStackTrace();
			APP_LOGS.info("Element Not Found");
			test.log(LogStatus.ERROR, "Element Not Found");
			SA.fail();
		}
		SA.assertAll();
	}
	public void validateCallBlock(){
		/*
		Validates the Call Block Option, if present then Block the number and check the same in Manage Block List
		 */
		SoftAssert SA = new SoftAssert();
		try {
			minWait();
			clickBtn(Locators_XP8_CallHistory.contactDetails);
			minWait();
			clickBtn(Locators_XP8_CallHistory.callDetailsOpt);
			if(isElementExist(Locators_XP8_CallHistory.blockNumberopt)){
				check = true;
				APP_LOGS.info("Block Number option is available");
				test.log(LogStatus.PASS, "Block Number option is available");
				blockNumber();
				minWait();
				clickBackButton(2);
				customWait(5000);
				manageBlacklist();
			}
			else {
				APP_LOGS.info("Number is already Blocked");
				test.log(LogStatus.FAIL, "Number is already Blocked");
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

	public void blockNumber(){
		/*
		Block the number
		 */
		SoftAssert SA = new SoftAssert();
		try {
			clickBtn(Locators_XP8_CallHistory.blockNumberopt);
			clickBtn(Locators_XP8_CallHistory.blockNumberBtn);
			APP_LOGS.info("Number is Blocked");
			test.log(LogStatus.PASS, "Number is Blocked");
		} catch (Exception e) {
			e.printStackTrace();
			APP_LOGS.info("Element Not Found");
			test.log(LogStatus.ERROR, "Element Not Found");
			SA.fail();
		}
		SA.assertAll();
	}

	public void unblockNumber(){
		/*
		Unblocks the Number
		 */
		SoftAssert SA = new SoftAssert();
		try {
			clickBtn(Locators_XP8_CallHistory.deleteBlockedNumber);
			clickBtn(Locators_XP8_CallHistory.unblockNumber);
			APP_LOGS.info("Number is unBlocked");
			test.log(LogStatus.PASS, "Number is unBlocked");
		} catch (Exception e) {
			e.printStackTrace();
			APP_LOGS.info("Element Not Found");
			test.log(LogStatus.FAIL, "Element Not Found");
			SA.fail();
		}
		SA.assertAll();
	}


	public void manageBlacklist(){

		/*
		Manages to check the Block numbers and unblocks if any
		 */
		SoftAssert SA = new SoftAssert();
		try {
			clickBtn(Locators_XP8_CallHistory.settingsIcon);
			clickBtn(Locators_XP8_CallHistory.phoneSettingsOpt);
			clickBtn(Locators_XP8_CallHistory.callScreeningOpt);
			clickBtn(Locators_XP8_CallHistory.manageBlackListOpt);
			if(isElementExist(Locators_XP8_CallHistory.blockedNumbers))    //Validates the Block number Option is available
			{
				customWait(5000);
				APP_LOGS.info("Number is Blocked");
				test.log(LogStatus.PASS, "Number is Blocked");
				unblockNumber();
				clickBackButton(5);
			}
			else {
				APP_LOGS.info("No Numbers are Blocked");
				test.log(LogStatus.FAIL, "No Numbers are Blocked");
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

	public void callDetailsAvailableOptions(){
		/*
		Checks the Options available under Call Details
		 */
		SoftAssert SA = new SoftAssert();
		try {
			if((isElementExist(Locators_XP8_CallHistory.copyNumberInCallDetails)) && (isElementExist(Locators_XP8_CallHistory.editNumberBeforeCallOpt))){
				check = true;
				APP_LOGS.info("Copy number and Edit Number before call options are available");
				test.log(LogStatus.PASS, "Copy number and Edit Number before call options are available");
				SA.assertTrue(check, "Copy number and Edit Number before call options are available");	
			}else {
				APP_LOGS.info("Copy number and Edit Number before call options are not available");
				test.log(LogStatus.FAIL, "Copy number and Edit Number before call options are not available" );
				SA.fail();				
			}
			minWait();
			copyNumberFromCallHistoryOpt();
			minWait();

		} catch (Exception e) {
			e.printStackTrace();
			APP_LOGS.info("Element Not Found");
			test.log(LogStatus.ERROR, "Element Not Found");
			SA.fail();
		}
		SA.assertAll();
	}

	public void copyNumberFromCallHistoryOpt() throws TesseractException, InterruptedException{
		/*
		Copies the Phone Number
		 */
		SoftAssert SA = new SoftAssert();
		try {
			launch_an_app("phone");
			navigateTocallHistory();
			clickBtn(Locators_XP8_CallHistory.contactDetails);
			minWait();
			clickBtn(Locators_XP8_CallHistory.callDetailsOpt);
			minWait();
			clickBtn(Locators_XP8_CallHistory.copyNumberInCallDetails);
			customWait(7000);
			clickBackButton(3);
			APP_LOGS.info("Number Copied from Call Details");
			test.log(LogStatus.PASS, "Number Copied from Call Details");
			SA.assertTrue(check, "Number Copied from Call Details");
		} catch (Exception e) {
			e.printStackTrace();
			APP_LOGS.info("Element Not Found");
			test.log(LogStatus.ERROR, "Element Not Found");
			SA.fail();
		}
		SA.assertAll();
		//readToastMessage();
	}

	public void validateEditNumberBeforeCall(){
		/*
		Validates the Edit Number Before Call Option under Call Details
		 */
		SoftAssert SA = new SoftAssert();
		try {
			launch_an_app("phone");
			navigateTocallHistory();
			clickBtn(Locators_XP8_CallHistory.contactDetails);
			minWait();
			clickBtn(Locators_XP8_CallHistory.callDetailsOpt);
			minWait();
			clickBtn(Locators_XP8_CallHistory.editNumberBeforeCallOpt);
			if(isElementExist(Locators_XP8_CallHistory.keypadDailedNumber)){
				check= true;
				APP_LOGS.info("Number Copied from Call Details");
				test.log(LogStatus.PASS, "Number Copied from Call Details");
				SA.assertTrue(check, "Number Copied from Call Details");
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			APP_LOGS.info("Element Not Found");
			test.log(LogStatus.ERROR, "Element Not Found");
			SA.fail();
		}
		SA.assertAll();
	}

}

