package com.xp8.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.NoSuchElementException;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
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

	public void reciveCallInRefDevice(String refNum) throws InterruptedException, IOException, NoSuchElementException {
		/* Receive Call in Reference device
		 */
			/*try {
				System.out.println("Inside Make a call");
				Runtime.getRuntime().exec("adb -s "+r_Id+"shell input keyevent 5 "+pryNum);
				customWait(10000);			
				System.out.println("Call is accepted");
				minWait();

			}catch (Exception e) {
				test.log(LogStatus.ERROR, "Exeption in -> reciveCallInRefDevice()");

			}
	}
		*/
		
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
}
	
	public void clearcallhistory()throws InterruptedException, IOException{
	
		/*
		 * clear frequents,all call log  and delete contacts before test
		 */
		 
			try {
				launch_an_app("phone");
				clickBtn(Locators_PhoneDialer.callLogPage1);
				minWait();
				if(isElementExist(Locators_PhoneDialer.dailedFirstNum)){
					clickBtn(Locators_PhoneDialer.settingsIcon);
					minWait();
					clickBtn(Locators_PhoneDialer.callHistoryOpt);
					minWait();
					clickBtn(Locators_PhoneDialer.moreOptionsInCallHistory);
					minWait();
					clickBtn(Locators_PhoneDialer.clearCallHistoryOpt);
					minWait();
					clickBtn(Locators_PhoneDialer.okBtn);
					

				}else{

					clickBackButton_without_trycatch(2);
				}
			} catch (org.openqa.selenium.NoSuchElementException e) {

				test.log(LogStatus.ERROR, "Error in the locators->clearcallhistory()");
				e.printStackTrace();

			}catch (Exception e) {
				test.log(LogStatus.ERROR, "Exception in ->clearcallhistory()");

			}
		}


	public void navigateTocallHistory() throws InterruptedException, IOException
	{
		/*
		 * navigate to call History page
		 */
		
			clickBtn(Locators_PhoneDialer.settingsIcon);
			minWait();
			clickBtn(Locators_PhoneDialer.call_history);
			minWait();
		
	}

	public void setDefaultSavingAccount() throws InterruptedException
	{
		/*
		 * Set default saving account as phone in contacts application
		 */
		try {
			
			clickBtn(Locators_PhoneDialer.ContactsMoreOptions);
			minWait();
			clickBtn(Locators_PhoneDialer.contactsSettingsOPt);
			minWait();
			clickBtn(Locators_PhoneDialer.contactsDefaultAccountSettings);
			minWait();
			clickBtn(Locators_PhoneDialer.contactsDefaultPhone);
			minWait();


		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->setDefaultSavingAccount()");
			e.printStackTrace();

		}
		catch (Exception e) {
			test.log(LogStatus.ERROR, "Exception in setDefaultSavingAccount()");
		}
	}       
			
		


	public void deleteIfContactsPresent(SoftAssert sa) throws InterruptedException
	{

		try {
			launch_an_app("contacts");
			if(isElementExist(Locators_PhoneDialer.deletefirstcontact))
			{
				clickBtn(Locators_PhoneDialer.delete_option);
				minWait();
				clickBtn(Locators_PhoneDialer.zero_selected);
				minWait();
				clickBtn(Locators_PhoneDialer.selectAllOpt);
				minWait();
				clickBtn(Locators_PhoneDialer.Ok_option);
				minWait();
				clickBtn(Locators_PhoneDialer.okBtn);
				customWait(5000);
				APP_LOGS.info("Contacts are deleted successfully");
				sa.assertTrue(true, "Contacts are deleted successfully");
				test.log(LogStatus.PASS, "Contacts are deleted successfully");	


			}else{
				minWait();
			}

		}  catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->deleteIfContactsPresent()");
			e.printStackTrace();

		}
		catch (Exception e) {
			test.log(LogStatus.ERROR, "Exeption in ->deleteIfContactsPresent()");
		}

	}



	public void validatePhoneAppLaunch(SoftAssert sa) throws InterruptedException
	{
		/*
		 * Validates Phone application Launch
		 */
		
		try {
			minWait();
			launch_an_app("phone");
			minWait();
			if(isElementExist(Locators_PhoneDialer.phoneFrame)){
				APP_LOGS.info("Phone Application launched successfully");
				sa.assertTrue(true, "Phone Application launched successfully");	
			    test.log(LogStatus.PASS, "Phone Application launched successfully");
				
			}else {
				APP_LOGS.info("Phone Application not launched");
				sa.fail();
				test.log(LogStatus.FAIL, "Phone Application not launched" );
				
							
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR,"Error in locators->validatePhoneAppLaunch()");
		}catch (Exception e) {
			test.log(LogStatus.ERROR,"Exeption in ->validatePhoneAppLaunch()");
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
	public void clickBackButton_without_trycatch(int number) throws InterruptedException
	{
		/*
		 * clicks on back button with iteration as user input
		 */
		
			for(int i=0;i<number;i++){
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
				minWait();
			}	
		
	}

	public void validatePhoneAppHomePage(SoftAssert sa) throws InterruptedException
	{
		/*
		 * Validates Phone application home page 
		 */
		
		try {
			customWait(2000);
			if(isElementExist(Locators_PhoneDialer.favoriteEmptyPage) || isElementExist(Locators_PhoneDialer.callLogEmptyPage) || isElementExist(Locators_PhoneDialer.contactsEmptyPage))
			{
			    APP_LOGS.info("Phone App Home page is verified");
				sa.assertTrue(true, "Phone App Home page Verfied");
				test.log(LogStatus.PASS, "Phone App Home page is verified");

			}else{
				APP_LOGS.info("Phone App Home page is not verified");
				sa.fail();
				test.log(LogStatus.FAIL, "Phone App Home page is not verified");
			}
			
		}catch (org.openqa.selenium.NoSuchElementException e) {
                 test.log(LogStatus.ERROR,"Error in locators->validatePhoneAppHomePage()");
                 
		}catch (Exception e) {
			test.log(LogStatus.ERROR,"Exeption in ->validatePhoneAppHomePage()");
		}
		
	}

	public void validateMakeACall() throws InterruptedException, IOException
	{
		/*
		 * Validates Make a call option (Clicks Make a call --> Enters number--> Dial --> validate MO call with UI )
		 */
		try{
			clickBtn(Locators_PhoneDialer.callLogPage);
			minWait();
			clickBtn(Locators_PhoneDialer.makeACallOption);
			minWait();
			enterTextToInputField(Locators_PhoneDialer.dialpadEditFld, refNum);
			minWait();
			System.out.println("coming");
			clickBtn(Locators_PhoneDialer.callBtn);
			customWait(5000);
			
			//validateMOCall("from Make a call option", refNum);
		}catch (org.openqa.selenium.NoSuchElementException e) {

				test.log(LogStatus.ERROR,"Error in locators->validateMakeACall()");
			}catch (Exception e) {
				test.log(LogStatus.ERROR,"Exeption in ->validateMakeACall()");
			}
	}
	public void validateMakeACall_sprint() throws InterruptedException, IOException
	{
		/*
		 * Validates Make a call option (Clicks Make a call --> Enters number--> Dial --> validate MO call with UI )
		 */
		try{
			clickBtn(Locators_PhoneDialer.callLogPage);
			minWait();
			clickBtn(Locators_PhoneDialer.makeACallOption);
			minWait();
			enterTextToInputField(Locators_PhoneDialer.dialpadEditFld, refNum);
			minWait();
			System.out.println("coming");
		    clickBtn(Locators_PhoneDialer.sprintcallBtn);
		   customWait(5000);
			
			//validateMOCall("from Make a call option", refNum);
		}catch (org.openqa.selenium.NoSuchElementException e) {

				test.log(LogStatus.ERROR,"Error in locators->validateMakeACall_sprint()");
			}catch (Exception e) {
				test.log(LogStatus.ERROR,"Exeption in ->validateMakeACall_sprint()");
			}
	}

	public void validateMOCall(String call,String phone,SoftAssert sa) throws InterruptedException, IOException
	{
		/*
		 * validate MO call with UI
		 */
		try{
			
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
				
				APP_LOGS.info("MO call placed successfully "+call);
				sa.assertTrue(true, "MO call placed successfully "+call);
				test.log(LogStatus.PASS, "MO call placed successfully "+call);
				
			} else	{
				APP_LOGS.info("MO call not placed "+call);
				sa.fail();
				test.log(LogStatus.FAIL, "MO call not placed "+call);
				
			}	

		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR,"Error in locators->validateMOCall()");
		}catch (Exception e) {
			test.log(LogStatus.ERROR,"Exeption in ->validateMOCall()");
		}
		
	}
	public void validateMOCall_without_trycatch(String call,String phone,SoftAssert sa) throws InterruptedException, IOException
	{
		/*
		 * validate MO call with UI
		 */
		
			
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
				
				APP_LOGS.info("MO call placed successfully "+call);
				sa.assertTrue(true, "MO call placed successfully "+call);
				test.log(LogStatus.PASS, "MO call placed successfully "+call);
				
			} else	{
				APP_LOGS.info("MO call not placed "+call);
				sa.fail();
				test.log(LogStatus.FAIL, "MO call not placed "+call);
				
			}	

		} 

	public void validateCallFromCallLog(SoftAssert sa) throws InterruptedException, IOException
	{
		/*
		 * Validates call from call Log(Call to a number from call log)
		 */
		
		try {
			minWait();
			clickBtn(Locators_PhoneDialer.callLogPage);
			minWait();
			if(isElementExist(Locators_PhoneDialer.recentCallLogNumber)) {
				clickBtn(Locators_PhoneDialer.callBtnInCallLog);
				customWait(5000);
				validateMOCall_without_trycatch("from recent Call Log", refNum,sa);
				clickBackButton_without_trycatch(1);
				clickBtn(Locators_PhoneDialer.recentCallLogNumber);
				APP_LOGS.info("Call from call log is validated successfuly");
				sa.assertTrue(true, "Call from call log is validated successfuly");
				test.log(LogStatus.PASS, "Call from call log is validated successfuly");	
			}else {

				APP_LOGS.info("Call from call log is not validated ");
				sa.fail();
				test.log(LogStatus.FAIL, "Call from call log is not validated ");

			}
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR,"Error in locators-> validateCallFromCallLog()");
		}catch (Exception e) {
			test.log(LogStatus.ERROR,"Exeption in -> validateCallFromCallLog()");
		}
	}

	public void CreateNewContactValidateSavedContact(String name,String location) throws InterruptedException
	{
		/*
		 * Validates Create New contact and validate saved contact
		 */
		try{
			
			if(isElementExist(Locators_PhoneDialer.permissionPopUp)) {
				minWait();
				for(int i=0;i<3;i++){
					minWait();
					clickBtn(Locators_PhoneDialer.allowBtn);
				}
			}
		}catch (org.openqa.selenium.NoSuchElementException e) {

				test.log(LogStatus.ERROR, "Error in the locators->CreateNewContactValidateSavedContact()");
				e.printStackTrace();

			}catch (Exception e) {
				test.log(LogStatus.ERROR, "Exeption in ->CreateNewContactValidateSavedContact()");

			}
		}

	public void CreateNewContactAndSave(String name) throws InterruptedException
	{
		/*
		 * Clicks on Create New contact -->enter name --> save
		 */
		
		
			
			enterTextToInputField(Locators_PhoneDialer.nameEditFld,name);
			customWait(2000);
			clickBtn(Locators_PhoneDialer.saveOpt);
		
	}

	public void validateSavedContact(String name,String location,SoftAssert sa) throws InterruptedException
	{
		/*
		 * validates saved contact
		 */
		
		try {
			customWait(2000);
			String savedContact = Locators_PhoneDialer.savedContact.getText();
			if(savedContact.contains(name)){
				APP_LOGS.info("Contact saved successfully");
				sa.assertTrue(true, "Contact saved "+ location +" successfully");
				test.log(LogStatus.PASS, "Contact save successfully");
			} else	{
				APP_LOGS.info("Contact not saved" + location);
				sa.fail();
				test.log(LogStatus.FAIL, "Contact not saved" + location);
			}		
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR,"Error in locators->validateSavedContact()");
		}catch (Exception e) {
			test.log(LogStatus.ERROR,"Exeption in ->validateSavedContact()");
		}
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
			clickBtn(Locators_PhoneDialer.createNewContactOpt);
			customWait(3000);
			
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->validateCreateNewContactFromCallLog()");
			e.printStackTrace();

		}catch (Exception e) {
			test.log(LogStatus.ERROR, "Exeption in ->validateCreateNewContactFromCallLog()");

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
			clickBtn(Locators_PhoneDialer.createnewconttxt_dialpad);
			minWait();
			enterTextToInputField(Locators_PhoneDialer.nameEditFld,name);
			customWait(2000);
			clickBtn(Locators_PhoneDialer.saveOpt);
			
			
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR,"Error in locators->validateCreateNewContactFromDialpad()");
		}catch (Exception e) {
			test.log(LogStatus.ERROR,"Exeption in ->validateCreateNewContactFromDialpad()");
		}
	}

	public void enterNumberInDialpad(String Phone) throws InterruptedException
	{
		/*
		 * clicks dialpad and enters number
		 */
		
			clickBtn(Locators_PhoneDialer.dialpadBtn);
			customWait(2000);
			enterTextToInputField(Locators_PhoneDialer.dialpadEditFld, Phone);
			customWait(2000);
		} 
	

	public void validate_number_saved_with_plusSymbol(SoftAssert sa){
		
		try {
			String number = Locators_PhoneDialer.savedContactNumber.getText();
			minWait();
			if(number.contains("+")) {
				clickBackButton_without_trycatch(3);
				APP_LOGS.info("Contact saved with '+' successfully");
				sa.assertTrue(true, "Contact saved with '+' successfully");
				test.log(LogStatus.PASS, "Contact saved with '+' successfully");
				
				
			} else	{
				APP_LOGS.info("Failed to save Contact with '+'" );
				sa.fail();
				test.log(LogStatus.FAIL, "Failed to save Contact with '+'");
				}
		} 
		catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR,"Error in locators->validate_number_saved_with_plusSymbol()");
		}catch (Exception e) {
			test.log(LogStatus.ERROR,"Exeption in ->validate_number_saved_with_plusSymbol()");
		}
	}

	public void validate_contacts_added_in_list(SoftAssert sa) {
		
		try {
			launch_an_app("phone");
			clickBtn(Locators_PhoneDialer.contactPage);
			minWait();
			if(isElementExist(Locators_PhoneDialer.contact_one) && isElementExist(Locators_PhoneDialer.contact_two)) {
				clickBackButton_without_trycatch(1);
				APP_LOGS.info("Added list of contacts are available");
				sa.assertTrue(true, "Added list of contacts are available");
				test.log(LogStatus.PASS, "Added list of contacts are available");
				
				
			} else	{
				APP_LOGS.info("Added list of contacts are not available" );
				sa.fail();
				test.log(LogStatus.FAIL, "Added list of contacts are not available");
				
				
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR,"Error in locators->validate_contacts_added_in_list()");
		}catch (Exception e) {
			test.log(LogStatus.ERROR,"Exeption in ->validate_contacts_added_in_list()");
		}
	}

	public void validate_frequent_list() {
		try {
			
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
			clickBackButton_without_trycatch(1);
			minWait();
			clickBtn(Locators_PhoneDialer.FrequentPage);
			minWait();
			
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR,"Error in locators->validate_frequent_list()");
		}catch (Exception e) {
			test.log(LogStatus.ERROR,"Exeption in ->validate_frequent_list()");
		}
	}

	public void validate_Fav_contacts_added_in_list(SoftAssert sa) {
		
		try {
			minWait();
			if(isElementExist(Locators_PhoneDialer.contact_one) && isElementExist(Locators_PhoneDialer.contact_two)) {
				
				APP_LOGS.info("Favourite/Frequent list of contacts are available");
				sa.assertTrue(true, "Favourite/Frequent list of contacts are available");
				test.log(LogStatus.PASS, "Favourite/Frequent list of contacts are available");
				
				
			} else	{
				APP_LOGS.info("Favourite/Frequent list of contacts are not available" );
				sa.fail();
				test.log(LogStatus.FAIL, "Favourite/Frequent list of contacts are not available");
				
				
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR,"Error in locators->validate_Fav_contacts_added_in_list()");
		}catch (Exception e) {
			test.log(LogStatus.ERROR,"Exeption in ->validate_Fav_contacts_added_in_list()");
		}
		
	}

	public void validate_clear_frequents(SoftAssert sa) {
		
		try {
			minWait();
			clickBtn(Locators_PhoneDialer.contactPage);
			minWait();
			clickBtn(Locators_PhoneDialer.contact_two);
			minWait();
			clickBtn(Locators_PhoneDialer.fav_add_star_option);
			clickBackButton_without_trycatch(1);
			clickBtn(Locators_PhoneDialer.FrequentPage);
			minWait();
			clickBtn(Locators_PhoneDialer.settingsIcon);
			minWait();
	       clickBtn(Locators_PhoneDialer.clearFrequentsOpt);
			minWait();
			clickBtn(Locators_PhoneDialer.okBtn);
	
			if(isElementExist(Locators_PhoneDialer.favoriteEmptyPage)) {
				APP_LOGS.info("Favourite/Frequent list cleared successfully");
				sa.assertTrue(true, "Favourite/Frequent list cleared successfully");
				test.log(LogStatus.PASS, "Favourite/Frequent list cleared successfully");
				
				
			} else	{
				APP_LOGS.info("Failed to clear Favourite/Frequent list" );
				sa.fail();
				test.log(LogStatus.ERROR, "Failed to clear Favourite/Frequent list");
				
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR,"Error in locators-> validate_clear_frequents()");
		}catch (Exception e) {
			test.log(LogStatus.ERROR,"Exeption in -> validate_clear_frequents()");
		}
	}

	public void validate_search(String ContactName,SoftAssert sa) {
		
		try {
			clickBtn(Locators_PhoneDialer.callLogPage);
			minWait();
		    clickBtn(Locators_PhoneDialer.searchContactsFld);
			customWait(3000);
			Runtime.getRuntime().exec("adb -s "+ p_Id +" shell input text "+ ContactName);
			customWait(2000);
			if(isElementExist(Locators_PhoneDialer.searched_contact)) {
				String searched_contact = Locators_PhoneDialer.searched_contact.getText();
				minWait();
				if(ContactName.equals(searched_contact)) {
					clickBackButton_without_trycatch(2);
					APP_LOGS.info("Searched contact is available,search option validated successfully");
					sa.assertTrue(true, "Searched contact is available,search option validated successfully");
					test.log(LogStatus.PASS, "Searched contact is available,search option validated successfully");
					
					
				} else	{
					APP_LOGS.info("Failed to validate search option" );
					sa.fail();
					test.log(LogStatus.FAIL, "Failed to validate search option");
					
					
				}
			}else {
				APP_LOGS.info("Contact not available" );
				sa.fail();
				test.log(LogStatus.FAIL, "Contact not available");
				
				
			}
			
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR,"Error in locators->validate_search()");
		}catch (Exception e) {
			test.log(LogStatus.ERROR,"Exeption in ->validate_search()");
		}
	}

	public void validate_recent_contact_in_list(String ContactName,SoftAssert sa) {
		
		try {
			clickBtn(Locators_PhoneDialer.callLogPage);
			minWait();
			String recent_contact = Locators_PhoneDialer.recentCallLogNumber.getText();
			minWait();
			if(recent_contact.equals(ContactName)) {
				
				APP_LOGS.info("Recently used contact details displayed at the top of phone dialer call logs ");
				sa.assertTrue(true, "Recently used contact details displayed at the top of phone dialer call logs");
				test.log(LogStatus.PASS, "Recently used contact details displayed at the top of phone dialer call logs");
				
				
			} else	{
				APP_LOGS.info("Recently used contact details is not displayed at the top of phone dialer call logs" );
				sa.fail();	
				test.log(LogStatus.ERROR, "Recently used contact details is not displayed at the top of phone dialer call logs");
				
				
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR,"Error in locators->validate_recent_contact_in_list()");
		}catch (Exception e) {
			test.log(LogStatus.ERROR,"Exeption in ->validate_recent_contact_in_list()");
		}
	}
	
	public void validate_send_message(String textMessage,SoftAssert sa) {
		
		try {
			clickBtn(Locators_PhoneDialer.callLogPage);
			minWait();
			clickBtn(Locators_PhoneDialer.recentCallLogNumber);
			minWait();
			clickBtn(Locators_PhoneDialer.Send_message);
			minWait();
			clickBtn(Locators_PhoneDialer.messageEditFld);
			minWait();
			enterTextToInputField(Locators_PhoneDialer.messageEditFld, textMessage);
			customWait(8000);
		    clickBtn(Locators_PhoneDialer.sendMessage);
		    WebDriverWait wait = new WebDriverWait(aDriver, 30);
		   wait.until(ExpectedConditions.visibilityOf(Locators_PhoneDialer.messageSentNow));
			if(isElementExist(Locators_PhoneDialer.messageSentNow))
			{
				
				APP_LOGS.info("Message sent successfully ");
				sa.assertTrue(true, "Message sent successfully ");	
				test.log(LogStatus.PASS, "Message sent successfully ");
				
				
			}else {
				APP_LOGS.info("Message not sent");
				sa.fail();
				test.log(LogStatus.FAIL, "Message not sent");
				
			}	
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR,"Error in locators->validate_send_message()");
		}catch (Exception e) {
			test.log(LogStatus.ERROR,"Exeption in ->validate_send_message()");
		}
	}
public void validate_send_message_sprint(String textMessage,SoftAssert sa) {
		
		try {
			launch_an_app("Message+");
		
			if(isElementExist(Locators_SMS_DeviceStability.vzw_Start_msg))
			{
				Runtime.getRuntime().exec("adb -s "+p_Id+" shell pm clear com.verizon.messaging.vzmsgs");
				launch_an_app("Message+");
				clickBtn(Locators_SMS_DeviceStability.vzw_Start_msg);
				clickBtn(Locators_SMS_DeviceStability.vzw_msg_skipprovisioing);
			}else{
				
			launch_an_app("phone");
			clickBtn(Locators_PhoneDialer.callLogPage);
			minWait();
			clickBtn(Locators_PhoneDialer.recentCallLogNumber);
			minWait();
			clickBtn(Locators_PhoneDialer.Send_message);
			minWait();
			/*clickBtn(Locators_PhoneDialer.messageEditFld);
			minWait();*/
			enterTextToInputField(Locators_PhoneDialer.sprintmessageEditFld, textMessage);
			customWait(8000);
		    clickBtn(Locators_PhoneDialer.sprintsendMessage);
		    WebDriverWait wait = new WebDriverWait(aDriver, 30);
		   wait.until(ExpectedConditions.visibilityOf(Locators_PhoneDialer.sprintmessageSentNow));
			}
			if(isElementExist(Locators_PhoneDialer.sprintmessageSentNow))
			{
				
				APP_LOGS.info("Message sent successfully ");
				sa.assertTrue(true, "Message sent successfully ");	
				test.log(LogStatus.PASS, "Message sent successfully ");
				
				
			}else {
				APP_LOGS.info("Message not sent");
				sa.fail();
				test.log(LogStatus.FAIL, "Message not sent");
				
			}	
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR,"Error in locators->validate_send_message_sprint()");
		}catch (Exception e) {
			test.log(LogStatus.ERROR,"Exeption in ->validate_send_message_sprint()");
		}
	}
public void validate_send_message_verizon(String textMessage,SoftAssert sa) {
	
	try {
		
		clickBtn(Locators_PhoneDialer.callLogPage);
		minWait();
		clickBtn(Locators_PhoneDialer.recentCallLogNumber);
		minWait();
		clickBtn(Locators_PhoneDialer.Send_message);
		minWait();
		if(isElementExist(Locators_PhoneDialer.vzwstartmsg))
		{
			clickBtn(Locators_PhoneDialer.vzwstartmsg);
			clickBtn(Locators_PhoneDialer.vzwskip);
		}
		enterTextToInputField(Locators_PhoneDialer.sprintmessageEditFld, textMessage);
		customWait(8000);
	    clickBtn(Locators_PhoneDialer.sprintsendMessage);
	    WebDriverWait wait = new WebDriverWait(aDriver, 30);
	   wait.until(ExpectedConditions.visibilityOf(Locators_PhoneDialer.sprintmessageSentNow));
		if(isElementExist(Locators_PhoneDialer.sprintmessageSentNow))
		{
			
			APP_LOGS.info("Message sent successfully ");
			sa.assertTrue(true, "Message sent successfully ");	
			test.log(LogStatus.PASS, "Message sent successfully ");
			
			
		}else {
			APP_LOGS.info("Message not sent");
			sa.fail();
			test.log(LogStatus.FAIL, "Message not sent");
			
		}	
	} catch (org.openqa.selenium.NoSuchElementException e) {

		test.log(LogStatus.ERROR,"Error in locators->validate_send_message_sprint()");
	}catch (Exception e) {
		test.log(LogStatus.ERROR,"Exeption in ->validate_send_message_sprint()");
	}
}
	public void validate_add_new_contact(String Phone,String name) {
		try {
			clickBtn(Locators_PhoneDialer.callLogPage);
			minWait();
			clickBtn(Locators_PhoneDialer.dialpadBtn);
			customWait(2000);
			enterTextToInputField(Locators_PhoneDialer.dialpadEditFld, Phone);
			customWait(2000);
			clickBtn(Locators_PhoneDialer.Add_to_contact);
			minWait();
			clickBtn(Locators_PhoneDialer.createNewContactOpt);
			customWait(3000);
			enterTextToInputField(Locators_PhoneDialer.nameEditFld,name);
			customWait(2000);
			clickBtn(Locators_PhoneDialer.saveOpt);
			customWait(2000);
		  /* clickBackButton_without_trycatch(2);
			minWait();
			clickBtn(Locators_PhoneDialer.contactPage);
			minWait();
			clickBtn(Locators_PhoneDialer.Contact_three);
			minWait();
		*/
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR,"Error in locators->validate_add_new_contact()");
		}catch (Exception e) {
			test.log(LogStatus.ERROR,"Exeption in ->validate_add_new_contact()");
		}
	}
	public void remove_GoogleAcccount() {
		//remove added google Account if any 
		try {
			scrollToText("Users & accounts");
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


		}catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR,"Error in locators->remove_GoogleAcccount()");
		}catch (Exception e) {
			test.log(LogStatus.ERROR,"Exeption in ->remove_GoogleAcccount()");
		}
	}
	
	public void validate_link_contact(SoftAssert sa) {
		
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
				APP_LOGS.info("Contact linked successfully");
				sa.assertTrue(true, "Contact linked successfully");
				test.log(LogStatus.PASS, "Contact linked successfully");
			}else {
				APP_LOGS.info("Failed to link Contact");
				sa.fail();
				test.log(LogStatus.FAIL, "Failed to link Contact");
			}
			clickBackButton_without_trycatch(1);
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR,"Error in locators->validate_link_contact()");
		}catch (Exception e) {
			test.log(LogStatus.ERROR,"Exeption in ->validate_link_contact()");
		}
	}
public void validate_unlink_contact(SoftAssert sa) {
		
		try {
			
			clickBtn(Locators_PhoneDialer.contactPage);
			minWait();
			clickBtn(Locators_PhoneDialer.Contact_three);
			minWait();
			clickBtn(Locators_PhoneDialer.contact_more_option);
			minWait();
			clickBtn(Locators_PhoneDialer.view_linked_cnts);
			minWait();
			clickBtn(Locators_PhoneDialer.unlink_txt);
			minWait();
			clickBtn(Locators_PhoneDialer.unlink_txt);
			minWait();
			if(isElementExist(Locators_PhoneDialer.after_unlink)){
				APP_LOGS.info("Contact unlinked successfully");
				sa.assertTrue(true, "Contact unlinked successfully");
				test.log(LogStatus.PASS, "Contact unlinked successfully");
			}else {
				APP_LOGS.info("Failed to unlink Contact");
				sa.fail();
				test.log(LogStatus.FAIL, "Failed to unlink Contact");
			}
			clickBackButton_without_trycatch(1);
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR,"Error in locators->validate_unlink_contact()");
		}catch (Exception e) {
			test.log(LogStatus.ERROR,"Exeption in ->validate_unlink_contact()");
		}
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
			
		}catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR,"Error in locators->add_picture_to_contact()");
		}catch (Exception e) {
			test.log(LogStatus.ERROR,"Exeption in ->add_picture_to_contact()");
		}
	}
	
	
	public void validate_take_photo(SoftAssert sa) {
		
		try {
			
			clickBtn(Locators_PhoneDialer.contactstab);
			minWait();
			clickBtn(Locators_PhoneDialer.contact_one);
			minWait();
			clickBtn(Locators_PhoneDialer.edit_contact);
			minWait();
			clickBtn(Locators_PhoneDialer.camera_image);
			minWait();
			clickBtn(Locators_PhoneDialer.Take_photo);
			customWait(2000);
			System.out.println("photo is taken");
		/*	if(isElementExist(Locators_PhoneDialer.permissionPopUp)) {
				minWait();
				for(int i=0;i<3;i++){
					minWait();
					clickBtn(Locators_PhoneDialer.allowBtn);
				}
			}
			*/
			System.out.println("take photo");
			clickBtn(Locators_PhoneDialer.photo_capture_Btn);
			customWait(8000);
			clickBtn(Locators_PhoneDialer.photo_reviewdone);
			/*clickBtn(Locators_PhoneDialer.photo_add_Btn);
			customWait(5000);*/
			System.out.println("check2");
			clickBtn(Locators_PhoneDialer.done_btn);
			customWait(5000);
		  if(isElementExist(Locators_PhoneDialer.added_image)) {
				
				APP_LOGS.info("Image added to saved contact Successfully,Take photo option verified");
				sa.assertTrue(true, "Image added to saved contact Successfully,Take photo option verified");
				test.log(LogStatus.PASS, "Image added to saved contact Successfully,Take photo option verified");
				
				
			}else {
				APP_LOGS.info("Failed to add image to saved Contact");
				sa.fail();
				test.log(LogStatus.FAIL, "Failed to add image to saved Contact");
				
				
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR,"Error in locators->validate_take_photo()");
		}catch (Exception e) {
			test.log(LogStatus.ERROR,"Exeption in ->validate_take_photo()");
		}
	}
	
	public void validate_remove_photo(SoftAssert sa) {
		
		try {
			clickBtn(Locators_PhoneDialer.added_image);
			customWait(2000);
			clickBtn(Locators_PhoneDialer.Remove_photo);
			minWait();
			if(isElementExist(Locators_PhoneDialer.contact_image_view_without_image)) {
				APP_LOGS.info("Image removed from saved contact Successfully,Remove photo option verified");
				sa.assertTrue(true, "Image removed from saved contact Successfully,Remove photo option verified");	
				test.log(LogStatus.PASS, "Image removed from saved contact Successfully,Remove photo option verified");
				

			}else {
				APP_LOGS.info("Failed to remove image from saved Contact");
				sa.fail();
				test.log(LogStatus.ERROR, "Failed to remove image from saved Contact");
				
				
			}
		}catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR,"Error in locators->validate_remove_photo()");
		}catch (Exception e) {
			test.log(LogStatus.ERROR,"Exeption in ->validate_remove_photo()");
		}
	}

	public void validate_Choose_photo(SoftAssert sa) {
		
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
					clickBtn(Locators_PhoneDialer.saveOpt);
					APP_LOGS.info("Image added to saved contact Successfully,Choose photo option verified");
					sa.assertTrue(true, "Image added to saved contact Successfully,Choose photo option verified");	
					test.log(LogStatus.PASS, "Image added to saved contact Successfully,Choose photo option verified");
					
					
				}else {
					APP_LOGS.info("Failed to add image to saved Contact");
					sa.fail();
					test.log(LogStatus.FAIL, "Failed to add image to saved Contact");
					
					
				}
				
			}else {
				APP_LOGS.info("Failed to capture image");
				sa.fail();
				test.log(LogStatus.FAIL, "Failed to capture image");
				
			}
		}catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR,"Error in locators->validate_Choose_photo()");
		}catch (Exception e) {
			test.log(LogStatus.ERROR,"Exeption in ->validate_Choose_photo()");
		}
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
			clickBackButton_without_trycatch(2);
			minWait();
		}catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR,"Error in locators->validateKeepEditingAndDiscard()");
		}catch (Exception e) {
			test.log(LogStatus.ERROR,"Exeption in ->validateKeepEditingAndDiscard()");
		}
		}

	public void KeepEditingAndDiscard(WebElement element,WebElement subElement,SoftAssert sa) throws InterruptedException, IOException
	{
		/*
		 * clicks on keep editing/Discard element and validates
		 */
		
		try
		{
			String option = element.getText();
			minWait();
			element.click();
			customWait(2000);
			if(isElementExist(subElement)){
				APP_LOGS.info(option + " Option is verified successfully");
				sa.assertTrue(true,option + " Option is verified successfully");
				test.log(LogStatus.PASS, option + " Option is verified successfully");
				
				
			}
			else{
				APP_LOGS.info(option + " Option is not available");
				sa.fail(option +" Option is not available");
				test.log(LogStatus.FAIL,option+ " Option is not available");
				
				
			}
		}catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR,"Error in locators->KeepEditingAndDiscard()");
		}catch (Exception e) {
			test.log(LogStatus.ERROR,"Exeption in ->KeepEditingAndDiscard()");
		}
	}
	
	public void validate_block_option_in_call_log(SoftAssert sa) {
		
		try {
			clickBtn(Locators_PhoneDialer.callLogPage);
			minWait();
			clickBtn(Locators_PhoneDialer.contact_one);
			minWait();
			if(isElementExist(Locators_PhoneDialer.blockNumberOpt)) {
				APP_LOGS.info("Block number option is present in call log page");
				sa.assertTrue(true, "Block number option is present in call log page");
				test.log(LogStatus.PASS, "Block number option is present in call log page");
			}else {
				APP_LOGS.info("Block number option is not present in call log page");
				sa.fail();
				test.log(LogStatus.FAIL, "Block number option is not present in call log page");
				
				
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR,"Error in locators->validate_block_option_in_call_log()");
		}catch (Exception e) {
			test.log(LogStatus.ERROR,"Exeption in ->validate_block_option_in_call_log()");
		}
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
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR,"Error in locators->navigate_to_block_number_in_call_settings()");
		}catch (Exception e) {
			test.log(LogStatus.ERROR,"Exeption in ->navigate_to_block_number_in_call_settings()");
		}
	}
	
	public void blockNumberAndValidate(SoftAssert sa) {
		
		try {
			clickBtn(Locators_PhoneDialer.add_a_number_to_block);
			minWait();
			System.out.println("blocked");
			enterTextToInputField(Locators_PhoneDialer.phone_number_fld_to_block, refNum);
			customWait(2000);
			clickBtn(Locators_PhoneDialer.blockBtn);
			//System.out.println(Locators_PhoneDialer.blockBtn.isEnabled());
			/*if(Locators_PhoneDialer.blockBtn.isEnabled()) {
				APP_LOGS.info("Block option is highlighted before entering Number to block");
				test.log(LogStatus.PASS, "Block option is highlighted before entering Number to block");
				
				
			}else {
				
				APP_LOGS.info("Block option is not highlighted before entering Number to block");
				sa.assertTrue(true, "Block option is not highlighted before entering Number to block");
				test.log(LogStatus.PASS, "Block option is not highlighted before entering Number to block");
				
					
			}
			*/
			if(isElementExist(Locators_PhoneDialer.blocked_number)) {
				
				APP_LOGS.info("Number blocked successfully from call settings,blocked number is available in list");
				sa.assertTrue(true, "Number blocked successfully from call settings,blocked number is available in list");	
				test.log(LogStatus.PASS, "Number blocked successfully from call settings,blocked number is available in list");
				
				
			}else {
				APP_LOGS.info("Failed to block number from call settings");
				sa.fail();
				test.log(LogStatus.FAIL, "Failed to block number from call settings");
				
				
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR,"Error in locators-> blockNumberAndValidate()");
		}catch (Exception e) {
			test.log(LogStatus.ERROR,"Exeption in -> blockNumberAndValidate()");
		}
		}
		
	
	public void unblockNumberAndValidate(SoftAssert sa) {
		
		try {
			clickBtn(Locators_PhoneDialer.settingsIcon);
			minWait();
			clickBtn(Locators_PhoneDialer.Settings);
			minWait();
			clickBtn(Locators_PhoneDialer.Call_screening);
			minWait();
			clickBtn(Locators_PhoneDialer.Manage_black_list);
			clickBtn(Locators_PhoneDialer.delete_unblock_number_option);
				minWait();
			clickBtn(Locators_PhoneDialer.unblocktxt);
				minWait();
				if(isElementExist(Locators_PhoneDialer.blocked_no_list)) {
					
					APP_LOGS.info("Number Unblocked successfully from call settings");
					sa.assertTrue(true, "Number Unblocked successfully from call settings");	
					test.log(LogStatus.PASS, "Number Unblocked successfully from call settings");
					
					
				}else {
					APP_LOGS.info("Failed to Unblock number from call settings");
					sa.fail();
					test.log(LogStatus.FAIL, "Failed to Unblock number from call settings");
					
					
				}
			
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR,"Error in locators->unblockNumberAndValidate()");
		}catch (Exception e) {
			test.log(LogStatus.ERROR,"Exeption in ->unblockNumberAndValidate()");
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
	
}
