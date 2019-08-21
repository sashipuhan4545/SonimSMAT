package com.xp8.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.util.NoSuchElementException;




import org.testng.asserts.SoftAssert;

import com.graphics.gui.JsonFileReaderAndWriter;

import com.relevantcodes.extentreports.LogStatus;


import application.AllQA;
//import application.AllQA;
import io.appium.java_client.android.AndroidKeyCode;

public class XP8_CallHistory_Utils extends BaseUtil{

	public boolean check = false;
	protected int i,j,k,l,m,n,o,p,q,r,s,t,u,v=0;


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
	public void validatePhoneAppLaunch(SoftAssert SA) throws InterruptedException
	{
		/*
		 * Validates Phone application Launch
		 */
		try {
			minWait();
			if(isElementExist(Locators_XP8_CallHistory.phoneFrame)){
				check = true;
				APP_LOGS.info("Phone Application launched successfully");
				SA.assertTrue(check, "Phone Application launched successfully");	
				test.log(LogStatus.PASS, "Phone Application launched successfully");

			}else {
				APP_LOGS.info("Phone Application not launched");
				SA.fail();		
				test.log(LogStatus.FAIL, "Phone Application not launched" );

			}
		} catch (org.openqa.selenium.NoSuchElementException e) {
			e.printStackTrace();
			APP_LOGS.info("Element Not Found");
			SA.fail();
			test.log(LogStatus.ERROR, "Error in the locators->validatePhoneAppLaunch()");

		}
		catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.INFO, "Exception in validatePhoneAppLaunch()");

		}
	}

	public void navigateTocallHistory(SoftAssert SA) throws InterruptedException, IOException
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
			APP_LOGS.info("Call History Option");
			test.log(LogStatus.PASS, "Call History Option" );
		}
		catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->navigateTocallHistory()");
			e.printStackTrace();

		}catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.INFO, "Exception in navigateTocallHistory() ");
		}
	}

	public void navigateTocallHistory_Without_Try_Catch(SoftAssert SA) throws InterruptedException, IOException
	{
		/*
		 * navigate to call History page
		 */		 
		minWait();
		clickBtn(Locators_XP8_CallHistory.settingsIcon);
		minWait();
		clickBtn(Locators_XP8_CallHistory.callHistoryOpt);
		minWait();
		APP_LOGS.info("Call History Option");
		test.log(LogStatus.PASS, "Call History Option" );
	}

	public void validateCallLogList(SoftAssert SA) throws InterruptedException, IOException{
		/*
		Validate the call log if any calls are listed
		 */




		try {

			customWait(2000);
			if(isElementExist(Locators_XP8_CallHistory.callLogWithSymbols))
			{

				APP_LOGS.info("All Call Log List with Symbol is verified");
				test.log(LogStatus.PASS, "All Call Log List with Symbol is verified");
				SA.assertTrue(check, "All Call Log List with Symbol is verified");

			}
			else{


				if(i==0 || i==1) {


					make_Call_from_RefDev_Without_Try_Catch();
					customWait(10000);
					//endCall_RefDevice();
					endCall_RefDevice_Without_Try_Catch();
					test.log(LogStatus.INFO, "Rerunning the program, after adding the call log details");
					customWait(2000);
					i+=1;
					validateCallLogList(SA);
					System.out.println("I value is"+i);

				}




			}







		} 
		catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.ERROR, "Error in the locators->validateCallLogList()");
			e.printStackTrace();
			SA.fail();
		}
		catch (Exception e) {
			test.log(LogStatus.INFO, "Exception in validateCallLogList() ");
			e.printStackTrace();
			SA.fail();
		}
	}

	public void validateContactFromCallLog(SoftAssert SA) throws InterruptedException{
		try {
			if (isElementExist(Locators_XP8_CallHistory.contactDetails)) {
				minWait();
				clickBtn(Locators_XP8_CallHistory.contactDetails);
				minWait();
				clickBtn(Locators_XP8_CallHistory.callDetailsOpt);
				minWait();
				clickBtn(Locators_XP8_CallHistory.contactLogo);
				customWait(2000);
				APP_LOGS.info("Logo is clicked");
				if((isElementExist(Locators_XP8_CallHistory.callMobile_ContactDetails)) || (isElementExist(Locators_XP8_CallHistory.emailHome_ContactDetails)) || (isElementExist(Locators_XP8_CallHistory.viewAddress_ContactDetails))){
					APP_LOGS.info("Contact Details contains number or email or address");
					test.log(LogStatus.PASS, "Contact details contains number or email or address");
					SA.assertTrue(check, "Contact details contains values successfully ");
				} else	{
					APP_LOGS.info("Contact details doesn't contains number or email or address");
					test.log(LogStatus.FAIL, "Contact details doesn't contains number or email or address");
					SA.fail();
				}
			}
			else{
				APP_LOGS.info("Saved Contact is not available");
				test.log(LogStatus.FAIL, "Saved Contact is not available");
				SA.fail();
			}
		}

		catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.ERROR, "Error in the locators->validateContactFromCallLog()");
			e.printStackTrace();
			SA.fail();
		}
		catch (Exception e) {
			test.log(LogStatus.INFO, "Exception in validateContactFromCallLog() ");
			SA.fail();
		}
		SA.assertAll();
	}

	public void validateCallFromCallLog(SoftAssert SA) throws InterruptedException, IOException
	{
		/*
		 * Validates call from call Log(Call to a number from call log)
		 */
		try {
			minWait();
			clickBtn(Locators_XP8_CallHistory.callLogPage);
			minWait();
			if(isElementExist(Locators_XP8_CallHistory.recentCallLogNumber)) {
				clickBtn(Locators_XP8_CallHistory.callBtnInCallLog);
				Runtime.getRuntime().exec("adb -s "+r_Id+" shell input keyevent 5");
				customWait(7000);
				clickBtn(Locators_XP8_CallHistory.endCallBtn);
				APP_LOGS.info("Call was initated Successfully");
				test.log(LogStatus.INFO, "Call was initated Successfully");
			}else {				

				if(j==0 || j==1) {
					customWait(3000);
					make_Call_from_RefDev_Without_Try_Catch();
					customWait(5000);
					endCall_RefDevice_Without_Try_Catch();
					customWait(2000);
					j+=1;
					test.log(LogStatus.INFO, "Rerunning the program, after adding the call log details");
					validateCallFromCallLog(SA);
				}
			}
		}
		catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.ERROR, "Error in the locators->validateCallFromCallLog()");
			e.printStackTrace();

		}catch (Exception e) {
			test.log(LogStatus.INFO, "Exception in validateCallFromCallLog()");
			e.printStackTrace();
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
				//aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_R);
				minWait();
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void reciveCallInRefDevice(String refNum, SoftAssert SA) throws InterruptedException, IOException {
		/* Receive Call in Reference device
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

	public void validateCreateNewContactFromCallLog(String name, SoftAssert SA) throws InterruptedException, IOException
	{
		/*
		 * Validates Create New contact from call log
		 */
		try {
			minWait();
			clickBtn(Locators_XP8_CallHistory.callLogPage);
			customWait(2000);
			if(isElementExist(Locators_XP8_CallHistory.callLogWithSymbols)){

				clickBtn(Locators_XP8_CallHistory.contactDetails);

			}
			else{

				if(k==0 || k==1) {
					System.out.println("Calling");
					make_Call_from_RefDev_Without_Try_Catch();;
					//customWait(5000);
					recieve_Call_PrimaryDev_Without_Try_Catch(SA);
					customWait(3000);
					endCall_RefDevice_Without_Try_Catch();
					customWait(10000);
					k+=1;
					System.out.println("Rerunning");
					clickBtn(Locators_XP8_CallHistory.contactDetails);
				}
			}
			minWait();
			if(isElementExist(Locators_XP8_CallHistory.Add_to_contact)){
				minWait();
				CreateNewContactValidateSavedContact_Without_Try_Catch(name,"from call Log", SA);
				minWait();
				APP_LOGS.info("New Contact is added");
				test.log(LogStatus.PASS, "New Contact is added");
			}
			else{
				APP_LOGS.info("Contact is already saved");
				test.log(LogStatus.PASS, "Contact is already saved");
				//SA.fail();
			}

		} 
		catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.ERROR, "Error in the locators->validateCreateNewContactFromCallLog()");
			e.printStackTrace();
		}
		catch (Exception e) {
			test.log(LogStatus.INFO, "Exception in validateCreateNewContactFromCallLog() ");
			e.printStackTrace();
		}
	}
	public void CreateNewContactValidateSavedContact(String name,String location, SoftAssert SA) throws InterruptedException
	{
		/*
		 * Validates Create New contact and validate saved contact
		 */
		try {
			minWait();
			//CreateNewContactAndSave(name,SA);
			CreateNewContactAndSave_Without_Try_Catch(name,SA);
			customWait(2000);
			if(isElementExist(Locators_XP8_CallHistory.permissionPopUp)) {
				check=true;
				minWait();
				for(int i=0;i<3;i++){
					minWait();
					clickBtn(Locators_XP8_CallHistory.allowBtn);
				}
			}
			clickBackButton(1);
			//validateSavedContact(name,location);
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "Element Not Found");
		}
	}

	public void CreateNewContactValidateSavedContact_Without_Try_Catch(String name,String location, SoftAssert SA) throws InterruptedException
	{
		/*
		 * Validates Create New contact and validate saved contact
		 */
		minWait();
		//CreateNewContactAndSave(name,SA);
		CreateNewContactAndSave_Without_Try_Catch(name,SA);
		customWait(2000);
		if(isElementExist(Locators_XP8_CallHistory.permissionPopUp)) {
			check=true;
			minWait();
			for(int i=0;i<3;i++){
				minWait();
				clickBtn(Locators_XP8_CallHistory.allowBtn);
			}
		}
		clickBackButton(1);
		//validateSavedContact(name,location);
	}

	public void callDetailsOpt(SoftAssert SA) throws InterruptedException{
		/*
		Clicks on contact title and opens the Call Details
		 */

		try {
			clickBtn(Locators_XP8_CallHistory.contactDetails);
			minWait();
			clickBtn(Locators_XP8_CallHistory.callDetailsOpt);
			minWait();
			APP_LOGS.info("Call Details is available");
			test.log(LogStatus.PASS, "Call Details is available");
		} 
		catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.ERROR, "Error in the locators->callDetailsOpt()");
			e.printStackTrace();
			SA.fail();
		}
		catch (Exception e) {
			test.log(LogStatus.INFO, "Exception in callDetailsOpt() ");
			e.printStackTrace();
			SA.fail();
		}
	}

	public void callDetailsOpt_Without_Try_Catch(SoftAssert SA) throws InterruptedException{
		/*
		Clicks on contact title and opens the Call Details
		 */
		clickBtn(Locators_XP8_CallHistory.contactDetails);
		minWait();
		clickBtn(Locators_XP8_CallHistory.callDetailsOpt);
		minWait();
		APP_LOGS.info("Call Details is available");
		test.log(LogStatus.PASS, "Call Details is available");
	}

	public void CreateNewContactAndSave(String name,SoftAssert SA) throws InterruptedException
	{
		/*
		 * Clicks on Create New contact -->enter name --> save
		 */
		try {
			minWait();
			clickBtn(Locators_XP8_CallHistory.createNewContactOpt);
			customWait(1000);
			enterTextToInputField(Locators_XP8_CallHistory.nameEditFld,name);
			customWait(1000);
			clickBtn(Locators_XP8_CallHistory.saveContactOpt);
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "Element Not Found");
		}
	}

	public void CreateNewContactAndSave_Without_Try_Catch(String name,SoftAssert SA) throws InterruptedException
	{
		/*
		 * Clicks on Create New contact -->enter name --> save
		 */		
		minWait();
		clickBtn(Locators_XP8_CallHistory.createNewContactOpt);
		customWait(1000);
		/*if(isElementExist(Locators_XP8_CallHistory.default_Account_For_New_Contact)) {
			clickBtn(Locators_XP8_CallHistory.default_Account_For_New_Contact);
			enterTextToInputField(Locators_XP8_CallHistory.nameEditFld,name);
			customWait(1000);
			clickBtn(Locators_XP8_CallHistory.saveContactOpt);
		}
		else {*/
			enterTextToInputField(Locators_XP8_CallHistory.nameEditFld,name);
			customWait(1000);
			clickBtn(Locators_XP8_CallHistory.saveContactOpt);
		//}
		
	}

	public void validateSavedContact(String name,String location,SoftAssert SA) throws InterruptedException
	{
		/*
		 * validates saved contact
		 */
		try {
			customWait(2000);
			if(isElementExist(Locators_XP8_CallHistory.savedContact)){
				String savedContact = Locators_XP8_CallHistory.savedContact.getText();
				if(savedContact.contains(name)){
					check = true;
					System.out.println("Contact saved already");
					APP_LOGS.info("Contact saved "+ location +"successfully");
					test.log(LogStatus.PASS, "Contact saved  "+ location +" successfully");
					SA.assertTrue(check, "Contact saved "+ location +" successfully");
				} 
				else	{
					APP_LOGS.info("Contact not saved" + location);
					test.log(LogStatus.INFO, "Contact not saved" + location);
					//test.log(LogStatus.FAIL, "Test case Status is FAIL");
					//SA.fail();
				}	
			}
			/*else
			{
				System.out.println("No Contacted Under Missed Call");
			}*/
		} catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.ERROR, "Error in the locators->validateSavedContact()");
			e.printStackTrace();
			SA.fail();
		}
		catch (Exception e) {
			test.log(LogStatus.INFO, "Exception in validateSavedContact() ");
			e.printStackTrace();
			SA.fail();
		}
	}

	public void validate_frequent_list(SoftAssert SA) {
		try {
			minWait();
			/*clickBtn(Locators_XP8_CallHistory.callLogPage);
			minWait();
			clickBtn(Locators_XP8_CallHistory.callBtnInCallLog);
			customWait(3000);
			clickBtn(Locators_XP8_CallHistory.endCallBtn);
			minWait();*/
			clickBtn(Locators_XP8_CallHistory.contactPage);
			minWait();
			clickBtn(Locators_XP8_CallHistory.contact_two);
			minWait();
			if(!isElementExist(Locators_XP8_CallHistory.validate_fav_add_star_enabled)){
				customWait(4000);
				clickBtn(Locators_XP8_CallHistory.fav_add_star_option);
			}
			else{
				test.log(LogStatus.INFO, "Fav add star is enabled");
			}
			clickBackButton(1);
			minWait();
			clickBtn(Locators_XP8_CallHistory.FrequentPage);
			minWait();
			validate_Fav_contacts_added_in_list_Without_Try_Catch(SA);
			APP_LOGS.info("Favourite/Frequent list of contacts are available");
			test.log(LogStatus.INFO, "Favourite/Frequent list of contacts are available");
			System.out.println("Test case Status is PASS");
		} 
		catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.ERROR, "Error in the locators->validate_frequent_list()");
			e.printStackTrace();
		}
		catch (Exception e) {
			test.log(LogStatus.INFO, "Exception in validate_frequent_list() ");
			e.printStackTrace();
		}
	}

	public void validate_Fav_contacts_added_in_list(SoftAssert SA) {
		try {
			minWait();
			if((isElementExist(Locators_XP8_CallHistory.contact_one)) || (isElementExist(Locators_XP8_CallHistory.contact_two))) {
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
		//SA.assertAll();
	}

	public void validate_Fav_contacts_added_in_list_Without_Try_Catch(SoftAssert SA) throws InterruptedException {
		minWait();
		if((isElementExist(Locators_XP8_CallHistory.contact_one)) || (isElementExist(Locators_XP8_CallHistory.contact_two))) {
			check = true;
			APP_LOGS.info("Favourite/Frequent list of contacts are available");
			test.log(LogStatus.PASS, "Favourite/Frequent list of contacts are available");
			SA.assertTrue(check, "Favourite/Frequent list of contacts are available");
		} else	{
			APP_LOGS.info("Favourite/Frequent list of contacts are not available" );
			test.log(LogStatus.FAIL, "Favourite/Frequent list of contacts are not available");
			SA.fail();
		}
	}

	public void click_Missed_Call_Log(SoftAssert SA) throws InterruptedException, IOException{

		try {			
			minWait();
			clickBtn(Locators_XP8_CallHistory.missedCallOpt);				
		} 
		catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.ERROR, "Error in the locators->click_Missed_Call_Log()");
			e.printStackTrace();
			SA.fail();
		}
		catch (Exception e) {
			test.log(LogStatus.INFO, "Exception in click_Missed_Call_Log() ");
			e.printStackTrace();
			SA.fail();
		}
	}


	public void validate_Call_From_Missed_Call_Log(SoftAssert SA) throws InterruptedException{
		try {
			minWait();
			clickBtn(Locators_XP8_CallHistory.callBtnInCallLog);
			customWait(7000);
			clickBtn(Locators_XP8_CallHistory.endCallBtn);
		} 
		catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.ERROR, "Error in the locators->validate_Call_From_Missed_Call_Log()");
			e.printStackTrace();
			SA.fail();
		}
		catch (Exception e) {
			test.log(LogStatus.INFO, "Exception in validate_Call_From_Missed_Call_Log() ");
			e.printStackTrace();
			SA.fail();
		}	
	}

	public void validate_send_message(SoftAssert SA) {
		try {
			clickBtn(Locators_XP8_CallHistory.callLogPage);
			minWait();
			if(isElementExist(Locators_XP8_CallHistory.recentCallLogNumber)){
				clickBtn(Locators_XP8_CallHistory.recentCallLogNumber);
				minWait();
			}
			else{
				if(l==0 || l==1) {
					minWait();
					make_Call_from_RefDev_Without_Try_Catch();
					minWait();
					recieve_Call_PrimaryDev_Without_Try_Catch(SA);
					minWait();
					l+=1;
					clickBtn(Locators_XP8_CallHistory.endCallBtn);
					minWait();
					clickBtn(Locators_XP8_CallHistory.recentCallLogNumber);
				}
			}
			clickBtn(Locators_XP8_CallHistory.Send_message);
			minWait();
			if(isElementExist(Locators_XP8_CallHistory.permissionPopUp)) {
				minWait();
				for(int i=0;i<3;i++){
					minWait();
					clickBtn(Locators_XP8_CallHistory.allowBtn);
				}
			}
			customWait(2000);
			if(isElementExist(Locators_XP8_CallHistory.messageEditFld)){
				clickBtn(Locators_XP8_CallHistory.messageEditFld);
				Runtime.getRuntime().exec("adb -s "+ p_Id +" shell input text 'Test Message '");
				minWait();
				if(isElementExist(Locators_XP8_CallHistory.sendMessageOpt)){
					test.log(LogStatus.INFO, "SEND BUTTON available");
					clickBtn(Locators_XP8_CallHistory.sendMessageOpt);
				}
				else{
					test.log(LogStatus.INFO, "Send Button is not available");
					SA.fail();
				}
				customWait(7000);
				if(isElementExist(Locators_XP8_CallHistory.messageSentNow))
				{
					APP_LOGS.info("Message sent successfully ");
					test.log(LogStatus.PASS, "Message sent successfully ");
					SA.assertTrue(check, "Message sent successfully ");	
				}else {
					APP_LOGS.info("Message not sent");
					test.log(LogStatus.FAIL, "Message not sent");
					SA.fail();
				}
			}
			else{
				System.out.println("element not found");
				SA.fail();
			}

		}
		catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.ERROR, "Error in the locators->validate_send_message()");
			e.printStackTrace();
			SA.fail();
		}
		catch (Exception e) {
			test.log(LogStatus.INFO, "Exception in validate_send_message() ");
			e.printStackTrace();
			SA.fail();
		}

	}
	public void enterNumberInDialpad(String Phone,SoftAssert SA) throws InterruptedException
	{
		/*
		 * clicks dialpad and validate the number entered
		 */
		try {

			if(isElementExist(Locators_XP8_CallHistory.dialpadBtn)){
				validate_Permission_Popup_In_Call_Logs_Without_Try_Catch(SA);
			}
			else{

				APP_LOGS.info("Dial Pad isn't available");
				SA.fail();
				test.log(LogStatus.FAIL, "Test case Status is FAIL");

			}
			//if(Phone=="") {
				enterTextToInputField(Locators_XP8_CallHistory.dialpadEditFld, Phone);

				if(isElementExist(Locators_XP8_CallHistory.dailedContactDetails)){
					minWait();
					clickBtn(Locators_XP8_CallHistory.dialpadContactDetials);
					APP_LOGS.info("Contact is saved already");
					test.log(LogStatus.PASS, "Contact is saved already");
				}			
				else {
					APP_LOGS.info("Contact is not saved!! Trying to Save");
					test.log(LogStatus.INFO, "Contact is not saved!! Trying to Save");
					if(Phone == pryNum){
						CreateNewContactAndSave_Without_Try_Catch("Primary Number",SA);
						clickBackButton(3);
						test.log(LogStatus.INFO, "Re-run");
						enterNumberInDialpad(pryNum, SA);
					}
					else if(Phone == refNum){
						CreateNewContactAndSave_Without_Try_Catch("Reference Number",SA);
						clickBackButton(3);
						test.log(LogStatus.INFO, "Re-run");
						enterNumberInDialpad(refNum, SA);
					}
					else{
						CreateNewContactAndSave_Without_Try_Catch("DemoAuto",SA);
						clickBackButton(3);
						test.log(LogStatus.INFO, "Re-run");
						enterNumberInDialpad(Phone, SA);
					}

					APP_LOGS.info("Contact is saved and rechecked");
				}
			//}
			/*else {
				SA.fail();
				APP_LOGS.info("MDN is empty");
				test.log(LogStatus.FAIL, "MDN is empty");
			}*/
		} 
		catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->enterNumberInDialpad()");
			e.printStackTrace();

		}catch (Exception e) {
			test.log(LogStatus.INFO, "Exception in enterNumberInDialpad()");
			e.printStackTrace();
		}
	}

	public void validateClearCallHistory(SoftAssert SA) throws InterruptedException, IOException{
		try {
			navigateTocallHistory_Without_Try_Catch(SA);
			minWait();
			if(!isElementExist(Locators_XP8_CallHistory.callLogEmptyPage)){
				clickBtn(Locators_XP8_CallHistory.moreOptionsInCallHistory);
				minWait();
				clickBtn(Locators_XP8_CallHistory.clearCallHistoryOpt);
				minWait();
				clickBtn(Locators_XP8_CallHistory.Ok_option);
				minWait();
				if(isElementExist(Locators_XP8_CallHistory.callLogEmptyPage)){
					APP_LOGS.info("Call History is cleared");
					test.log(LogStatus.PASS, "Call History is cleared");
				}
				else{
					APP_LOGS.info("Call History is not cleared");
					test.log(LogStatus.FAIL, "Call History is not cleared");
					SA.fail();
				}				
			}
			else{				
				APP_LOGS.info("Call History is Empty");
				test.log(LogStatus.PASS, "Call History is Empty");
			}
		}
		catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.ERROR, "Error in the locators->validateClearCallHistory()");
			e.printStackTrace();
			SA.fail();
		}
		catch (Exception e) {
			test.log(LogStatus.INFO, "Exception in validateClearCallHistory() ");
			e.printStackTrace();
			SA.fail();
		}
	}

	public void validate_Permission_Popup_In_Call_Logs(SoftAssert SA) throws InterruptedException{
		/*
		Validates if Permission Pop Up appears Dial pad is clicked
		 */
		try {
			clickBtn(Locators_XP8_CallHistory.dialpadBtn);
			if(isElementExist(Locators_XP8_CallHistory.dialpad_PermissionPopUp)){
				minWait();
				clickBtn(Locators_XP8_CallHistory.Ok_option);
				check = true;
				APP_LOGS.info("Permission Pop-Up is actioned");
				test.log(LogStatus.PASS, "Permission Pop-Up is actioned");
			}
		} catch (Exception e) {
			e.printStackTrace();
			APP_LOGS.info("Element Not Found");
			test.log(LogStatus.ERROR, "Element Not Found");
			SA.fail();
		}
	}


	public void validate_Permission_Popup_In_Call_Logs_Without_Try_Catch(SoftAssert SA) throws InterruptedException{
		/*
		Validates if Permission Pop Up appears Dial pad is clicked
		 */
		clickBtn(Locators_XP8_CallHistory.dialpadBtn);
		if(isElementExist(Locators_XP8_CallHistory.dialpad_PermissionPopUp)){
			minWait();
			clickBtn(Locators_XP8_CallHistory.Ok_option);
			check = true;
			APP_LOGS.info("Permission Pop-Up is actioned");
			test.log(LogStatus.PASS, "Permission Pop-Up is actioned");
		}
	}
	public void validateCallBlock(SoftAssert SA){
		/*
		Validates the Call Block Option, if present then Block the number and check the same in Manage Block List
		 */
		try {
			minWait();
			if(isElementExist(Locators_XP8_CallHistory.contactDetails)){
				clickBtn(Locators_XP8_CallHistory.contactDetails);
				minWait();
			}
			else{
				if(m==0 || m==1) {
					make_Call_from_RefDev_Without_Try_Catch();
					customWait(5000);
					endCall_RefDevice_Without_Try_Catch();
					test.log(LogStatus.INFO, "New Call Log is added!! Re-running the steps");
					minWait();
					m+=1;
					if(isElementExist(Locators_XP8_CallHistory.contactDetails)){
						validateCallBlock(SA);
					}
					else{
						SA.fail();
					}
				}
			}
			//clickBtn(Locators_XP8_CallHistory.callDetailsOpt);
			if(isElementExist(Locators_XP8_CallHistory.blockNumberopt)){

				APP_LOGS.info("Block Number option is available");
				test.log(LogStatus.INFO, "Block Number option is available");
				blockNumber_Without_Try_Catch(SA);
				minWait();
				/*clickBackButton(1);
				customWait(5000);
				manageBlacklist(SA);*/
				if(isElementExist(Locators_XP8_CallHistory.unblockNumberOpt)){
					clickBtn(Locators_XP8_CallHistory.unblockNumberOpt);
					minWait();
					clickBtn(Locators_XP8_CallHistory.unblockNumber);
				}
				else{					
					manageBlacklist_Without_Try_Catch(SA);
				}
			}			
			else{				
				APP_LOGS.info("Number is already Blocked");
				test.log(LogStatus.FAIL, "Number is already Blocked");
				SA.fail();
			}
			test.log(LogStatus.PASS, "Number Blocked and unblocked successfully");
		} 
		catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.ERROR, "Error in the locators->validateCallBlock()");
			e.printStackTrace();
			SA.fail();
		}
		catch (Exception e) {
			test.log(LogStatus.INFO, "Exception in validateCallBlock() ");
			e.printStackTrace();
			SA.fail();
		}
	}

	public void blockNumber(SoftAssert SA){
		/*
		Block the number
		 */
		try {
			clickBtn(Locators_XP8_CallHistory.blockNumberopt);
			clickBtn(Locators_XP8_CallHistory.blockNumberBtn);
			APP_LOGS.info("Number is Blocked");
			test.log(LogStatus.INFO, "Number is Blocked");
		} 
		catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.ERROR, "Error in the locators->blockNumber()");
			e.printStackTrace();
			SA.fail();
		}
		catch (Exception e) {
			test.log(LogStatus.INFO, "Exception in blockNumber() ");
			e.printStackTrace();
			SA.fail();
		}
	}

	public void blockNumber_Without_Try_Catch(SoftAssert SA){
		/*
		Block the number
		 */
		clickBtn(Locators_XP8_CallHistory.blockNumberopt);
		clickBtn(Locators_XP8_CallHistory.blockNumberBtn);
		APP_LOGS.info("Number is Blocked");
		test.log(LogStatus.INFO, "Number is Blocked");
	}

	public void unblockNumber(SoftAssert SA){
		/*
		Unblocks the Number
		 */
		try {
			clickBtn(Locators_XP8_CallHistory.deleteBlockedNumber);
			clickBtn(Locators_XP8_CallHistory.unblockNumber);
			APP_LOGS.info("Number is unBlocked");
			test.log(LogStatus.INFO, "Number is unBlocked");
		} catch (Exception e) {
			e.printStackTrace();
			APP_LOGS.info("Element Not Found");
			test.log(LogStatus.FAIL, "Element Not Found");
			SA.fail();
		}
	}


	public void manageBlacklist(SoftAssert SA){

		/*
		Manages to check the Block numbers and unblocks if any
		 */
		try {
			clickBtn(Locators_XP8_CallHistory.settingsIcon);
			clickBtn(Locators_XP8_CallHistory.phoneSettingsOpt);
			clickBtn(Locators_XP8_CallHistory.callScreeningOpt);
			clickBtn(Locators_XP8_CallHistory.manageBlackListOpt);
			if(isElementExist(Locators_XP8_CallHistory.blockedNumbers))    //Validates the Block number Option is available
			{
				customWait(3000);
				APP_LOGS.info("Block contacts are available");
				test.log(LogStatus.INFO, "Block contacts are available");
				unblockNumber(SA);
				//clickBackButton(5);
			}
			else {
				APP_LOGS.info("No Numbers are Blocked");
				test.log(LogStatus.INFO, "No Numbers are Blocked");
				//SA.fail();
			}
		} catch (Exception e) {
			e.printStackTrace();
			APP_LOGS.info("Element Not Found");
			test.log(LogStatus.ERROR, "Element Not Found");
			SA.fail();
		}
	}

	public void manageBlacklist_Without_Try_Catch(SoftAssert SA) throws InterruptedException{

		/*
		Manages to check the Block numbers and unblocks if any
		 */
		clickBtn(Locators_XP8_CallHistory.settingsIcon);
		clickBtn(Locators_XP8_CallHistory.phoneSettingsOpt);
		clickBtn(Locators_XP8_CallHistory.callScreeningOpt);
		clickBtn(Locators_XP8_CallHistory.manageBlackListOpt);
		if(isElementExist(Locators_XP8_CallHistory.blockedNumbers))    //Validates the Block number Option is available
		{
			customWait(3000);
			APP_LOGS.info("Block contacts are available");
			test.log(LogStatus.INFO, "Block contacts are available");
			unblockNumber(SA);
			//clickBackButton(5);
		}
		else {
			APP_LOGS.info("No Numbers are Blocked");
			test.log(LogStatus.INFO, "No Numbers are Blocked");
			//SA.fail();
		}
	}


	public void callDetailsAvailableOptions(SoftAssert SA){
		/*
		Checks the Options available under Call Details
		 */
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
			copyNumberFromCallHistoryOpt(SA);
			minWait();

		} catch (Exception e) {
			e.printStackTrace();
			APP_LOGS.info("Element Not Found");
			test.log(LogStatus.ERROR, "Element Not Found");
			SA.fail();
		}
	}

	public void copyNumberFromCallHistoryOpt(SoftAssert SA) throws InterruptedException{
		/*
		Copies the Phone Number
		 */
		try {

			clickBtn(Locators_XP8_CallHistory.contactDetails);
			minWait();
			clickBtn(Locators_XP8_CallHistory.callDetailsOpt);
			customWait(2000);
			scrollToElements(Locators_XP8_CallHistory.copyNumberInCallDetails);
			//clickBtn(Locators_BaseUtil.browser_App);
			minWait();
			if(isElementExist(Locators_XP8_CallHistory.copyNumberInCallDetails)){
				clickBtn(Locators_XP8_CallHistory.copyNumberInCallDetails);
				APP_LOGS.info("Number Copied from Call Details");
				test.log(LogStatus.INFO, "Number Copied from Call Details");
			}
			else{
				APP_LOGS.info("Number is not copied from Call Details");
				test.log(LogStatus.INFO, "Number is not copied from Call Details");
			}
		} 
		catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.ERROR, "Error in the locators->copyNumberFromCallHistoryOpt()");
			e.printStackTrace();
			SA.fail();
		}
		catch (Exception e) {
			test.log(LogStatus.INFO, "Exception in copyNumberFromCallHistoryOpt() ");
			e.printStackTrace();
			SA.fail();
		}
	}

	public void validateEditNumberBeforeCall(SoftAssert SA){
		/*
		Validates the Edit Number Before Call Option under Call Details
		 */
		try {
			/*//launch_an_app("phone");
			navigateTocallHistory(SA);
			clickBtn(Locators_XP8_CallHistory.contactDetails);
			minWait();
			clickBtn(Locators_XP8_CallHistory.callDetailsOpt);
			minWait();*/
			scrollToElements(Locators_XP8_CallHistory.editNumberBeforeCallOpt);
			minWait();
			clickBtn(Locators_XP8_CallHistory.editNumberBeforeCallOpt);
			if(isElementExist(Locators_XP8_CallHistory.keypadDailedNumber)){
				APP_LOGS.info("Number is present on Keypad for editing");
				test.log(LogStatus.PASS, "Number is present on Keypad for editing");
				clickBackButton(3);
			}
			else{
				APP_LOGS.info("Number is not present on Keypad for editing");
				test.log(LogStatus.PASS, "Number is not present on Keypad for editing");
				SA.fail();
			}
		}
		catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.ERROR, "Error in the locators->validateEditNumberBeforeCall()");
			e.printStackTrace();
			SA.fail();
		}
		catch (Exception e) {
			test.log(LogStatus.INFO, "Exception in validateEditNumberBeforeCall() ");
			e.printStackTrace();
			SA.fail();
		}
	}

	public void validateNumberOfMissedCalls(SoftAssert SA) throws InterruptedException, IOException
	{
		//navigateTocallHistory(SA);
		try{
			if(isElementExist(Locators_XP8_CallHistory.callLogWithSymbols)){		
				minWait();
				clickBtn(Locators_XP8_CallHistory.missedCallOpt);
				callDetailsOpt_Without_Try_Catch(SA);
				if(isElementExist(Locators_XP8_CallHistory.missedCall)){
					check = true;
					APP_LOGS.info("MissedCall log is available");
					test.log(LogStatus.PASS, "MissedCall log is available");					
				}
				else{
					if(n==0 || n==1) {
						make_Call_from_RefDev_Without_Try_Catch();
						customWait(5000);
						endCall_RefDevice_Without_Try_Catch();
						n+=1;
						test.log(LogStatus.INFO, "New Call Log is added!! Re-running the steps");
						minWait();
						validateNumberOfMissedCalls(SA);
					}
				}
			}
			else{
				if(o==0 || o==1) {
					make_Call_from_RefDev_Without_Try_Catch();
					recieve_Call_PrimaryDev_Without_Try_Catch(SA);
					endCall_RefDevice_Without_Try_Catch();
					o+=1;
					test.log(LogStatus.INFO, "New Call Log is added!! Re-running the steps");
					minWait();
					validateNumberOfMissedCalls(SA);
				}
			}
		}
		catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.ERROR, "Error in the locators->validateNumberOfMissedCalls()");
			e.printStackTrace();
			SA.fail();
		}
		catch (Exception e) {
			test.log(LogStatus.INFO, "Exception in validateNumberOfMissedCalls() ");
			e.printStackTrace();
			SA.fail();
		}


	}

	public void add_picture_to_contact(SoftAssert SA) {
		try {
			if(isElementExist(Locators_XP8_CallHistory.contactPage)){
				clickBtn(Locators_XP8_CallHistory.contactPage);
				minWait();
				clickBtn(Locators_XP8_CallHistory.contact_one);
				minWait();
				clickBtn(Locators_XP8_CallHistory.edit_contact);
				minWait();
				clickBtn(Locators_XP8_CallHistory.camera_image);
				minWait();
				validate_take_photo_Without_Try_Catch(SA);
			}
			else{
				test.log(LogStatus.FAIL, "Element Not Found");
				SA.fail();
			}

			/*minWait();
			validate_remove_photo();
			minWait();*/

		}
		catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.ERROR, "Error in the locators->add_picture_to_contact()");
			e.printStackTrace();
		}
		catch (Exception e) {
			test.log(LogStatus.INFO, "Exception in add_picture_to_contact() ");
			e.printStackTrace();
		}
	}

	public void validate_take_photo(SoftAssert SA) {
		try {
			if(isElementExist(Locators_XP8_CallHistory.Take_photo)){
				check=true;
				clickBtn(Locators_XP8_CallHistory.Take_photo);
			}
			else if(isElementExist(Locators_XP8_CallHistory.Take_new_photo)){
				check = true;
				clickBtn(Locators_XP8_CallHistory.Take_new_photo);
			}
			else{
				APP_LOGS.info("Failed to add image to saved Contact");
				test.log(LogStatus.FAIL, "Failed to add image to saved Contact");
				SA.fail();
			}
			customWait(2000);
			if(isElementExist(Locators_XP8_CallHistory.permissionPopUp)) {
				minWait();
				for(int i=0;i<3;i++){
					minWait();
					clickBtn(Locators_XP8_CallHistory.allowBtn);
				}
			}

			clickBtn(Locators_XP8_CallHistory.photo_capture_Btn);
			customWait(5000);
			clickBtn(Locators_XP8_CallHistory.photo_add_Btn);
			customWait(7000);
			clickBtn(Locators_XP8_CallHistory.done_btn);
			minWait();
			clickBtn(Locators_XP8_CallHistory.camera_image);
			minWait();
			if(isElementExist(Locators_XP8_CallHistory.Remove_photo)) {
				check = true;
				clickBtn(Locators_XP8_CallHistory.cancelChangePhoto);
				minWait();
				clickBtn(Locators_XP8_CallHistory.editor_menu_save_button);
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
		} 
		catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.ERROR, "Error in the locators->validate_take_photo()");
			e.printStackTrace();
		}
		catch (Exception e) {
			test.log(LogStatus.INFO, "Exception in validate_take_photo() ");
			e.printStackTrace();
		}
	}

	public void validate_take_photo_Without_Try_Catch(SoftAssert SA) throws InterruptedException {
		if(isElementExist(Locators_XP8_CallHistory.Take_photo)){
			check=true;
			clickBtn(Locators_XP8_CallHistory.Take_photo);
		}
		else if(isElementExist(Locators_XP8_CallHistory.Take_new_photo)){
			check = true;
			clickBtn(Locators_XP8_CallHistory.Take_new_photo);
		}
		else{
			APP_LOGS.info("Failed to add image to saved Contact");
			test.log(LogStatus.FAIL, "Failed to add image to saved Contact");
			SA.fail();
		}
		customWait(2000);
		if(isElementExist(Locators_XP8_CallHistory.permissionPopUp)) {
			minWait();
			for(int i=0;i<3;i++){
				minWait();
				clickBtn(Locators_XP8_CallHistory.allowBtn);
			}
		}

		clickBtn(Locators_XP8_CallHistory.photo_capture_Btn);
		customWait(5000);
		clickBtn(Locators_XP8_CallHistory.photo_add_Btn);
		customWait(7000);
		clickBtn(Locators_XP8_CallHistory.done_btn);
		minWait();
		clickBtn(Locators_XP8_CallHistory.camera_image);
		minWait();
		if(isElementExist(Locators_XP8_CallHistory.Remove_photo)) {
			check = true;
			clickBtn(Locators_XP8_CallHistory.cancelChangePhoto);
			minWait();
			clickBtn(Locators_XP8_CallHistory.editor_menu_save_button);
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
	}

	public void validate_remove_photo(SoftAssert SA) {
		try {
			clickBtn(Locators_XP8_CallHistory.added_image);
			customWait(2000);
			clickBtn(Locators_XP8_CallHistory.Remove_photo);
			minWait();
			if(isElementExist(Locators_XP8_CallHistory.contact_image_view_without_image)) {
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
	}

	public void validate_specific_call_details(SoftAssert SA) throws InterruptedException{
		callDetailsOpt_Without_Try_Catch(SA);
		try {
			if(isElementExist(Locators_XP8_CallHistory.outgoingCall)){
				APP_LOGS.info("Outgoing Call log is available");
				test.log(LogStatus.INFO, "Outgoing Call log is available");
			}
			else if(isElementExist(Locators_XP8_CallHistory.incomingCall)){
				APP_LOGS.info("Incoming Call log is available");
				test.log(LogStatus.INFO, "Incoming Call log is available");
			}
			else{
				APP_LOGS.info("Call History is empty");
				test.log(LogStatus.FAIL, "Call History is empty");
				SA.fail();				
			}
		} 
		catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.ERROR, "Error in the locators->validate_specific_call_details()");
			e.printStackTrace();
			SA.fail();
		}
		catch (Exception e) {
			test.log(LogStatus.INFO, "Exception in validate_specific_call_details() ");
			e.printStackTrace();
			SA.fail();
		}
	}

	public void clickRecentAppKey(int number){
		/*
		 * clicks on recent app button with iterations as user input
		 */
		try {
			for(int i=0;i<number;i++){
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_APP_SWITCH);
				minWait();
				if((isElementExist(Locators_XP8_CallHistory.recent_task_view_bar))||(isElementExist(Locators_XP8_CallHistory.no_Recent_Items))) {
				test.log(LogStatus.PASS, "Recent app key pressed successfully");
				APP_LOGS.info("Recent app key pressed successfully");
				}
				else {
					test.log(LogStatus.FAIL, "Recent Key is not pressed");
					APP_LOGS.info("Recent Key is not pressed");
				}
			}
		}
		catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.ERROR, "Error in the locators->clickRecentAppKey()");
			e.printStackTrace();
		}
		catch (Exception e) {
			test.log(LogStatus.INFO, "Exception in clickRecentAppKey() ");
			e.printStackTrace();
		}
	}

	public String getCurrentDeviceTime(String adbCommands) throws IOException, ParseException {

		Process child = Runtime.getRuntime().exec(adbCommands);
		InputStream lsOut = child.getInputStream();
		InputStreamReader r = new InputStreamReader(lsOut);
		BufferedReader in = new BufferedReader(r);
		String value=in.readLine();

		return value;



	}

	public void validateSavedContactInCallingScreen(SoftAssert SA) throws InterruptedException{



	}

	public void validate_Saved_Contact_In_Call_Recieving_Screen(SoftAssert SA) throws InterruptedException, IOException{

		//validateSavedContact("Automation","");
	}

	public void searchContact(String name, SoftAssert SA) throws InterruptedException {
		/*
		 * Search contact details under contact app 
		 */
		try {
			//launch_an_app("contacts");
			minWait();
			clickBtn(Locators_XP8_CallHistory.Search_ConatctIcon);
			customWait(3000);
			enterTextToInputField(Locators_XP8_CallHistory.findContacts, name);
			minWait();
			if(isElementExist(Locators_XP8_CallHistory.longpress_FirstContact)){
				check= true;
				System.out.println("Element located");
				clickBtn(Locators_XP8_CallHistory.longpress_FirstContact);
			}else{
				check = false;
				System.out.println("Element not located");
				SA.fail();
			}	
			//minWait();
		} catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, " No Such Element Found");
		}
	}

	public void make_Call_from_RefDev() throws InterruptedException, IOException {

		try {
			minWait();
			Runtime.getRuntime().exec("adb -s "+r_Id+" shell am start -a android.intent.action.CALL -d tel:"+pryNum);
			minWait();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void make_Call_from_RefDev_Without_Try_Catch() throws InterruptedException, IOException {
		minWait();
		Runtime.getRuntime().exec("adb -s "+r_Id+" shell am start -a android.intent.action.CALL -d tel:"+pryNum);
		minWait();
	}

	public void recieve_Call_PrimaryDev(SoftAssert SA) throws IOException, InterruptedException {

		try {
			for(int j=1;j<=100;j++){
				Process child = null;
				if (p_b_No.contains("8A.")) {
					System.out.println("XP8");					
					child = Runtime.getRuntime().exec("adb -s " + p_Id + " shell service call phone 5");
				} else if(p_b_No.contains("5SA.")) {
					System.out.println("XP5");
					child=Runtime.getRuntime().exec("adb -s " + p_Id + " shell service call phone 5");
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
				}else {
					continue;
				}
			}
		}catch(Exception e) {
			Thread.sleep(2000);
			Runtime.getRuntime().exec("adb -s "+p_Id+" shell input keyevent 5");
			Thread.sleep(2000);
		}
	}


	public void recieve_Call_PrimaryDev_Without_Try_Catch(SoftAssert SA) throws IOException, InterruptedException {

		for(int j=1;j<=100;j++){
			Process child = null;
			if (p_b_No.contains("8A.")) {
				System.out.println("XP8");					
				child = Runtime.getRuntime().exec("adb -s " + p_Id + " shell service call phone 5");
			} else if(p_b_No.contains("5SA.")) {
				System.out.println("XP5");
				child=Runtime.getRuntime().exec("adb -s " + p_Id + " shell service call phone 5");
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
			}else {
				continue;
			}
		}
	}

	public void endCall_RefDevice() {
		try {
			minWait();
			Runtime.getRuntime().exec("adb -s "+r_Id+" shell input keyevent 6");
			Thread.sleep(1000);
		} catch (Exception e) {
			e.getMessage();
		}
	}

	public void endCall_RefDevice_Without_Try_Catch() throws InterruptedException, IOException {
		minWait();
		Runtime.getRuntime().exec("adb -s "+r_Id+" shell input keyevent 6");
		Thread.sleep(1000);
	}

	public void validate_Update_Recently_ALL_Call(SoftAssert SA) throws InterruptedException{

		try {
			String[] callDateTime = (Locators_XP8_CallHistory.call_Time).getText().split(",");
			String callTime=callDateTime[1];
			String[] spiltTime = callTime.split(":");
			String spiltFirstTimeDigit = spiltTime[0];
			StringBuilder afterSpiltFirstTimeDigit = new StringBuilder(spiltFirstTimeDigit);
			StringBuilder deleteFirstTimeLetter = (afterSpiltFirstTimeDigit.deleteCharAt(0));
			String finalCallLogTime = (deleteFirstTimeLetter+":"+spiltTime[1]);
			test.log(LogStatus.INFO, finalCallLogTime);
			String device_Time=getCurrentDeviceTime("adb -s  "+p_Id+" shell date +'%r'"); 			
			String[] split_Device_Time=device_Time.split(" ");
			String time_WithOutAMPM=split_Device_Time[0];    //+":"+split_time[1];
			String[] spiltTimeByHHMM = time_WithOutAMPM.split(":");
			String[] spiltFirstElementHH = spiltTimeByHHMM[0].split("");
			if(spiltFirstElementHH[0].contains("0")){

				String finalDeviceTime= (spiltFirstElementHH[1]+":"+spiltTimeByHHMM[1]+" "+split_Device_Time[1]);
				/*String afterSpiltTimeByHHMM = spiltFirstElementHH[0];
				StringBuilder device_Time_Spilt = new StringBuilder(afterSpiltTimeByHHMM);
				System.out.println("device_Time_Split::"+device_Time_Spilt);
				StringBuilder deleteFirstDeviceLetter = (device_Time_Spilt.deleteCharAt(0));
				System.out.println("deleteFirstDeviceLetter:-"+deleteFirstDeviceLetter);
				String finalDeviceTime= (deleteFirstDeviceLetter+":"+spiltTimeByHHMM[1]+" "+split_Device_Time[1]);*/
				System.out.println(finalCallLogTime);
				System.out.println(finalDeviceTime);
				test.log(LogStatus.INFO,finalDeviceTime);
				if(finalCallLogTime.equals(finalDeviceTime)){

					test.log(LogStatus.PASS,"Call History is updated with recent call detials");
				}
				else{

					test.log(LogStatus.INFO,"Time didnt match");
					minWait();
					clickBackButton(1);
					minWait();
					if(p==0 || p==1) {
						make_Call_from_RefDev_Without_Try_Catch();
						customWait(5000);
						endCall_RefDevice_Without_Try_Catch();
						minWait();
						p+=1;
						callDetailsOpt_Without_Try_Catch(SA);
						validate_Update_Recently_ALL_Call(SA);				
						/*test.log(LogStatus.FAIL,"Call history is not updated with recent call details11111");
					SA.fail();*/
					}
				}
			}
			else{
				String finalDeviceTime= (spiltTimeByHHMM[0]+":"+spiltTimeByHHMM[1]+" "+split_Device_Time[1]);
				System.out.println(finalDeviceTime);
				test.log(LogStatus.INFO, finalDeviceTime);
				if(finalCallLogTime.equals(finalDeviceTime)){

					test.log(LogStatus.PASS,"Call History is updated with recent call detials");
				}
				else{
					if(q==0 || q==1) {
						System.out.println("Time didn match");
						make_Call_from_RefDev_Without_Try_Catch();
						customWait(5000);
						endCall_RefDevice_Without_Try_Catch();
						minWait();
						q+=1;
						callDetailsOpt_Without_Try_Catch(SA);
						validate_Update_Recently_ALL_Call(SA);	
						/*test.log(LogStatus.FAIL,"Call history is not updated with recent call details22222");
					SA.fail();*/
					}
				}				
			}			

		} catch (IOException e) {
			test.log(LogStatus.ERROR, "Error in the IOException->validate_Update_Recently_ALL_Call()");
			e.printStackTrace();
		} catch (ParseException e) {
			test.log(LogStatus.ERROR, "Error in the ParseException->validate_Update_Recently_ALL_Call()");
			e.printStackTrace();
		}
		catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.ERROR, "Error in the locators->validate_Update_Recently_ALL_Call()");
			e.printStackTrace();
			SA.fail();
		}
		catch (Exception e) {
			test.log(LogStatus.INFO, "Exception in validate_Update_Recently_ALL_Call() ");
			e.printStackTrace();
			SA.fail();
		}	
	}

	public void validate_Update_Recently_MISSED_Call(SoftAssert SA) throws InterruptedException{

		try {
			String[] callDateTime = (Locators_XP8_CallHistory.call_Time).getText().split(",");
			String callTime=callDateTime[1];
			String[] spiltTime = callTime.split(":");
			String spiltFirstTimeDigit = spiltTime[0];
			StringBuilder afterSpiltFirstTimeDigit = new StringBuilder(spiltFirstTimeDigit);
			StringBuilder deleteFirstTimeLetter = (afterSpiltFirstTimeDigit.deleteCharAt(0));
			String finalCallLogTime = (deleteFirstTimeLetter+":"+spiltTime[1]);
			System.out.println(finalCallLogTime);
			test.log(LogStatus.INFO, finalCallLogTime);
			String device_Time=getCurrentDeviceTime("adb -s  "+p_Id+" shell date +'%r'"); 			
			String[] split_Device_Time=device_Time.split(" ");
			String time_WithOutAMPM=split_Device_Time[0];    //+":"+split_time[1];
			String[] spiltTimeByHHMM = time_WithOutAMPM.split(":");
			String[] spiltFirstElementHH = spiltTimeByHHMM[0].split("");
			if(spiltFirstElementHH[0].contains("0")){

				String finalDeviceTime= (spiltFirstElementHH[1]+":"+spiltTimeByHHMM[1]+" "+split_Device_Time[1]);
				/*String afterSpiltTimeByHHMM = spiltFirstElementHH[0];
				StringBuilder device_Time_Split = new StringBuilder(afterSpiltTimeByHHMM);
				StringBuilder deleteFirstDeviceLetter = (device_Time_Split.deleteCharAt(0));
				String finalDeviceTime= (deleteFirstDeviceLetter+":"+spiltTimeByHHMM[1]+" "+split_Device_Time[1]);
				System.out.println(finalDeviceTime);*/
				System.out.println(finalCallLogTime);
				System.out.println(finalDeviceTime);
				test.log(LogStatus.INFO,finalDeviceTime);
				if(finalCallLogTime.equals(finalDeviceTime)){

					test.log(LogStatus.PASS,"Call History is updated with recent call detials11111");
				}
				else{
					if(r==0 || r==1) {
						System.out.println("Time didnt match");
						clickBackButton(1);
						make_Call_from_RefDev_Without_Try_Catch();
						customWait(5000);
						endCall_RefDevice_Without_Try_Catch();
						minWait();
						r+=1;
						callDetailsOpt_Without_Try_Catch(SA);
						validate_Update_Recently_ALL_Call(SA);				
						/*test.log(LogStatus.FAIL,"Call history is not updated with recent call details11111");
					SA.fail();*/
					}
				}
			}
			else{
				String finalDeviceTime= (spiltTimeByHHMM[0]+":"+spiltTimeByHHMM[1]+" "+split_Device_Time[1]);
				System.out.println(finalDeviceTime);
				test.log(LogStatus.INFO, finalDeviceTime);
				if(finalCallLogTime.equals(finalDeviceTime)){

					test.log(LogStatus.PASS,"Call History is updated with recent call detials222222");
				}
				else{

					System.out.println("Time didn match");
					minWait();
					clickBackButton(1);
					if(s==0 || s==1) {
						make_Call_from_RefDev_Without_Try_Catch();
						customWait(5000);
						endCall_RefDevice_Without_Try_Catch();
						minWait();
						s+=1;
						callDetailsOpt_Without_Try_Catch(SA);
						validate_Update_Recently_ALL_Call(SA);	
						/*test.log(LogStatus.FAIL,"Call history is not updated with recent call details22222");
					SA.fail();*/
					}
				}
			}			
		} 
		catch (IOException e) {

			test.log(LogStatus.ERROR, "Error in the IOException->validate_Update_Recently_MISSED_Call()");
			e.printStackTrace();
		} 
		catch (ParseException e) {
			test.log(LogStatus.ERROR, "Error in the ParseException->validate_Update_Recently_MISSED_Call()");
			e.printStackTrace();
		}

		catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.ERROR, "Error in the locators->validate_Update_Recently_MISSED_Call()");
			e.printStackTrace();
			SA.fail();
		}
		catch (Exception e) {
			test.log(LogStatus.INFO, "Exception in validate_Update_Recently_MISSED_Call() ");
			e.printStackTrace();
			SA.fail();
		}
	}

	public void validate_Device_Date_Time_Format(SoftAssert SA) throws InterruptedException{

		try {
			launch_an_app("settings");
			scrollToElements(Locators_XP8_CallHistory.SystemOpt);
			clickBtn(Locators_XP8_CallHistory.SystemOpt);
			clickBtn(Locators_XP8_CallHistory.date_time_Opt);
			if(isElementExist(Locators_XP8_CallHistory.timeFormatSetting12Hrs)){
				test.log(LogStatus.INFO,"TIME is in 12 HRS");
			}
			else{
				test.log(LogStatus.INFO,"Time is in 24hrs and changing to 12hrs");
				minWait();
				clickBtn(Locators_XP8_CallHistory.timeFormatInSetting24hrs);
				customWait(5000);
			}
		} 
		catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.ERROR, "Error in the locators->click_Missed_Call_Log()");
			e.printStackTrace();
			SA.fail();
		}
		catch (Exception e) {
			test.log(LogStatus.INFO, "Exception in click_Missed_Call_Log() ");
			e.printStackTrace();
			SA.fail();
		}
	}

	public void currentDeviceAndCallLogDateTime(SoftAssert SA) throws IOException, ParseException, InterruptedException{
		try {
			if(isElementExist(Locators_XP8_CallHistory.call_Time)){
				String callDateTime = (Locators_XP8_CallHistory.call_Time).getText();
				test.log(LogStatus.INFO, callDateTime);
				minWait();
				String device_Date_Time=getCurrentDeviceTime("adb -s  "+p_Id+" shell date +'%a, %b %d, %Y  %r'");
				test.log(LogStatus.INFO, device_Date_Time);
				test.log(LogStatus.PASS, "Call Log Date Time is: "+" "+callDateTime+"and Device Date Time is: "+" "+device_Date_Time);
				clickBackButton(2);
			}
			else{

				test.log(LogStatus.FAIL, "Call log detail is not available");
				SA.fail();
			}
		} 
		catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.ERROR, "Error in the locators->click_Missed_Call_Log()");
			e.printStackTrace();
			SA.fail();
		}
		catch (Exception e) {
			test.log(LogStatus.INFO, "Exception in click_Missed_Call_Log() ");
			e.printStackTrace();
			SA.fail();
		}
	}

	public void get_A_Missed_Call(SoftAssert SA) throws InterruptedException, IOException {
		try {
			if(u==0 || u==1) {	
				make_Call_from_RefDev();
				customWait(5000);
				endCall_RefDevice();
				customWait(2000);
				u+=1;
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			SA.fail();
		}
	}
	
	public void get_A_Missed_Call_WithOut_Try_catch(SoftAssert SA) throws InterruptedException, IOException {
			if(v==0 || v==1) {	
				make_Call_from_RefDev();
				customWait(5000);
				endCall_RefDevice();
				customWait(2000);
				v+=1;
			}
	}
}

