package com.xp8.util;

import java.awt.AWTException;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import javax.imageio.ImageIO;

import org.json.simple.parser.ParseException;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
/*import org.sikuli.script.FindFailed;
import org.sikuli.script.Screen;*/
/*import org.sikuli.natives.OCR;
import org.sikuli.script.Screen;*/
import org.testng.asserts.SoftAssert;

import com.graphics.gui.JsonFileReaderAndWriter;
import com.relevantcodes.extentreports.LogStatus;


import application.AllQA;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidKeyCode;
import net.sourceforge.tess4j.TesseractException;
import net.sourceforge.tess4j.Tesseract;


public class XP8_Interruption_Util extends BaseUtil {

	public boolean check = false;

	public String p_Id  	= "";    		// Primary Device Serial Number.
	public String r_Id 		= "";    		// Reference Device Serial Number.
	public String p_b_No 	= "";      	// Primary Device Build Number.
	public String r_b_No 	= "";    		// Reference Device Build Number.
	public String pryNum 	= AllQA.PRIMARYDEVMDN; // Primary Device MDN.  
	public String refNum 	= AllQA.REFERENCEDEVMDN; // Reference Device MDN.  

	public void fetch_Devices_Details() throws InterruptedException, FileNotFoundException, IOException,org.json.simple.parser.ParseException {

		minWait();
		p_Id=JsonFileReaderAndWriter.primaryDevIdReader();
		r_Id=JsonFileReaderAndWriter.ReadRefDeviceId();
		p_b_No=JsonFileReaderAndWriter.primaryDevFirmwareReader();
		r_b_No=JsonFileReaderAndWriter.ReadRefDeviceFirmWare();
	}

	//=======================LOCK condition============================//
		public void lockscreen() {
			try{
				System.out.println("To unlock the screen");
				launch_an_app("settings");
				scrollToText("Security & location");

				if(isElementExist(Locators_XP8_CallHistory.locknone)){
					System.out.println("Inside IF");
					aDriver.pressKeyCode(AndroidKeyCode.HOME);
				}
				else if(isElementExist(Locators_XP8_CallHistory.lockswipe)){
					System.out.println("Inside ELSE IF");
					clickBtn(Locators_XP8_CallHistory.lockscreenlock);
					customWait(2000);
					clickBtn(Locators_XP8_CallHistory.locknone2);
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

		//===============SLEEP pre and post conditions===================//

		public void Pre_Condition_Set_Sleeptime_30min() throws InterruptedException, IOException {

			Runtime.getRuntime().exec("adb -s "+p_Id+" shell settings put system screen_off_timeout 1800000");
		}

		public void Post_Condition_Set_Sleeptime_30sec() throws InterruptedException, IOException {

			Runtime.getRuntime().exec("adb -s "+p_Id+" shell settings put system screen_off_timeout 30000");
			System.out.println("Sleep is set to 30 Secs");
		}

		//=====================Airplane Condition==========================//

		public void checkairplanemode(SoftAssert sa){

			try {
				customWait(2000);
				/*Runtime.getRuntime().exec("adb -s "+p_Id+" shell am start -a android.settings.AIRPLANE_MODE_SETTINGS");
					minWait();
					for (int i = 1; i <= 9; i++) {
						aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
					}*/if(isElementExist(Locators_XP8_CallHistory.airplanemodeCheckboxoff)){
						System.out.println("airplane mode off");
						aDriver.pressKeyCode(AndroidKeyCode.HOME);
						APP_LOGS.info("Airplane mode is disabled");
						sa.assertTrue(true, "Airplane mode is disabled");
						test.log(LogStatus.PASS,"Airplane mode is disabled");
					}
					else{
						clickBtn(Locators_XP8_CallHistory.airplanemodeCheckboxon);
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

		//============================ Memory Testing=======================================//

		public void preconditionMemoryStorageCheck(SoftAssert SA) throws InterruptedException{
			/*
				Checks the memory status as >= 85%, else fills the memory as the pre condition checklist
			 */
			try {
				aDriver.pressKeyCode(AndroidKeyCode.HOME);
				minWait();
				WebDriverWait wait = new WebDriverWait(aDriver, 600);
				Locators_XP8_CallHistory.AppListIcon.click();
				minWait();
				scrollToElements(Locators_XP8_CallHistory.memoryfillIcon);
				if(isElementExist(Locators_XP8_CallHistory.memoryfillIcon)){
					clickBtn(Locators_XP8_CallHistory.memoryfillIcon);
					if(isElementExist(Locators_XP8_CallHistory.internalSpaceopt)){
						System.out.println("App is launched Successfully");
						APP_LOGS.info("App is launched Successfully");
						test.log(LogStatus.PASS,"App is launched Successfully");
						aDriver.pressKeyCode(AndroidKeyCode.HOME);
					}
					else{
						System.out.println("App is not launched");
						APP_LOGS.info("App is not launched Successfully");
						test.log(LogStatus.FAIL,"App is not launched Successfully");
						SA.fail();
					}
				}
				else{
					System.out.println("App is not installed");
					APP_LOGS.info("App is not installed");
					test.log(LogStatus.INFO,"App is not installed");
					/*minWait();
						System.out.println("App to be installed!!");
						APP_LOGS.info("App to be installed!!");
						test.log(LogStatus.INFO,"App to be installed!!");
						//aDriver.installApp(".\\src\\test\\resources\\StorageFile\\XP8memoryfill.apk");
						Runtime.getRuntime().exec("adb -s "+p_Id+" install .\\src\\test\\resources\\StorageFile\\XP8memoryfill.apk");
						minWait();
						System.out.println("App installed successfull!!");
						APP_LOGS.info("App installed successfull!!");
						test.log(LogStatus.PASS,"App installed successfull!!");*/
				}	
				minWait();
				launch_an_app("memoryFill");
				minWait();
				System.out.println("App Launched");
				APP_LOGS.info("App Launched");
				test.log(LogStatus.INFO,"App Launched");
				clickBtn(Locators_XP8_CallHistory.internalSpaceopt);
				customWait(3000);
				String filledValue = (Locators_XP8_CallHistory.filledValue).getText();
				System.out.println(filledValue);
				APP_LOGS.info(filledValue);
				test.log(LogStatus.INFO,filledValue);
				String[] splitFilledValue = filledValue.split(" ");
				System.out.println(splitFilledValue[0]);

				int memoryLimit = (int)50.0;
				System.out.println("Value to limit the memory "+memoryLimit);
				APP_LOGS.info("Value to limit the memory "+memoryLimit);
				test.log(LogStatus.INFO,"Value to limit the memory "+memoryLimit);

				double stringToDouble = Double.parseDouble(splitFilledValue[0]);
				System.out.println(stringToDouble);

				int alreadyFilled = (int)stringToDouble;
				System.out.println("Value of already filled memory "+alreadyFilled);
				APP_LOGS.info("Value of already filled memory "+alreadyFilled);
				test.log(LogStatus.INFO,"Value of already filled memory "+alreadyFilled);

				if(alreadyFilled<memoryLimit){
					System.out.println("value of memory filled is"+filledValue);
					APP_LOGS.info("value of memory filled is"+filledValue);
					test.log(LogStatus.INFO,"value of memory filled is"+filledValue);

					clickBtn(Locators_XP8_CallHistory.fillOpt);
					System.out.println("Clicked on Fill Button");
					customWait(10000);
					String Filled1 = (Locators_XP8_CallHistory.filledValue).getText();
					System.out.println(Filled1);
					if(Filled1.contains("0.0 %")){
						clickBtn(Locators_XP8_CallHistory.cancelOpt);
						clickBackButton(1);
					}else{
						System.out.println("Waiting till Filled value is 50");
						APP_LOGS.info("Waiting till Filled value is 50");
						test.log(LogStatus.INFO,"Waiting till Filled value is 50");
						WebDriverWait wait1 = new WebDriverWait(aDriver, 300);
						wait1.until(ExpectedConditions.textToBePresentInElement(Locators_XP8_CallHistory.filledValue, "50"));
						/*	String z = (Locators_XP8_CallHistory.filledValue).getText();
							System.out.println("Value of Z "+z);*/
						System.out.println("Waited till Filled value is 50");
						APP_LOGS.info("Waited till Filled value is 50");
						test.log(LogStatus.INFO,"Waited till Filled value is 50");

						clickBtn(Locators_XP8_CallHistory.cancelOpt);
						String filled = (Locators_XP8_CallHistory.filledValue).getText();
						System.out.println("value of Filled is : "+filled);
						APP_LOGS.info("value of Filled is : "+filled);
						test.log(LogStatus.PASS,"value of Filled is : "+filled);
					}

				}
				else{
					System.out.println("value of memory filled is"+filledValue);
					APP_LOGS.info("value of memory filled is"+filledValue);
					test.log(LogStatus.INFO,"value of memory filled is"+filledValue);
					System.out.println("More than or equal 50.0 %");
					APP_LOGS.info("More than or equal 50.0 %");
					test.log(LogStatus.PASS,"More than or equal 50.0 %");
					aDriver.pressKeyCode(AndroidKeyCode.HOME);
				}
			} catch (org.openqa.selenium.NoSuchElementException e) {
				test.log(LogStatus.ERROR, "Error in the locators->preconditionMemoryStorageCheck()");
				e.printStackTrace();

			}catch (Exception e) {
				test.log(LogStatus.ERROR, "Exception in preconditionMemoryStorageCheck()");
				e.printStackTrace();
			}
		}

		public void postconditionMemoryStorageCheck() throws InterruptedException{
			/*
					Uninstall the memory fill app as the post condition checklist
			 */
			try {
				launch_an_app("memoryFill");
				minWait();
				clickBtn(Locators_XP8_CallHistory.internalSpaceopt);
				minWait();
				String filled = (Locators_XP8_CallHistory.filledValue).getText();
				System.out.println("value of Filled is : "+filled);
				APP_LOGS.info("value of Filled is : "+filled);
				test.log(LogStatus.INFO,"value of Filled is : "+filled);

				clickBtn(Locators_XP8_CallHistory.memoryFreeUpBtn);
				System.out.println("Successfully cleared: "+filled+ " of memory");
				APP_LOGS.info("Successfully cleared: "+filled+ "% of memory");
				test.log(LogStatus.PASS,"Successfully cleared: "+filled+ "% of memory");

				customWait(3000);
				aDriver.pressKeyCode(AndroidKeyCode.BACK);

				Runtime.getRuntime().exec("adb -s"+p_Id+"uninstall fillememory.myapplication");
				launch_an_app("memoryFill");
				if(isElementExist(Locators_XP8_CallHistory.memoryfillIcon)){
					System.out.println("Memory Fill app is not uninstalled");
					aDriver.startActivity("com.android.settings", "com.android.settings.Settings$AppAndNotificationDashboardActivity");
					minWait();
					if(isElementExist(Locators_XP8_CallHistory.memoryfillIcon)){
						System.out.println("Memory Fill App Found");
						APP_LOGS.info("Memory Fill App Found");
						test.log(LogStatus.INFO,"Memory Fill App Found");
						clickBtn(Locators_XP8_CallHistory.memoryfillIcon);
					}else{
						System.out.println("NOT FOUND");
						APP_LOGS.info("Memory Fill App not found");
						test.log(LogStatus.INFO,"Memory Fill App not found");
						clickBtn(Locators_XP8_CallHistory.seeAllOptn);
						customWait(2000);
						scrollToElements(Locators_XP8_CallHistory.memoryfillIcon);
						clickBtn(Locators_XP8_CallHistory.memoryfillIcon);
					}
					//clickBtn(Locators_XP8_CallHistory.memoryfillIcon);
					minWait();
					clickBtn(Locators_XP8_CallHistory.uninstallBtn);
					minWait();
					clickBtn(Locators_XP8_CallHistory.okBtn);
				}else{
					System.out.println("Memory Fill app is uninstalled successfully");
				}

				aDriver.pressKeyCode(AndroidKeyCode.HOME);

			} catch (org.openqa.selenium.NoSuchElementException e) {
				test.log(LogStatus.ERROR, "Error in the locators->postconditionMemoryStorageCheck()");
				e.printStackTrace();

			}catch (Exception e) {
				test.log(LogStatus.ERROR, "Exception in postconditionMemoryStorageCheck()");
				e.printStackTrace();
			}	
		}
		
	public void validateCallInterruption() throws InterruptedException, IOException{
		try{
			SoftAssert SA = new SoftAssert();
			//launch_an_app("phone");
			make_Call_from_RefDev();
			/*Checks the Call status and receives the call*/
			customWait(10000);
			String id=p_Id;
			String cmd="adb -s "+id+" shell \"dumpsys telephony.registry | grep mCallState\"";
			InputStream p = Runtime.getRuntime().exec(cmd).getInputStream();
			InputStreamReader isr = new InputStreamReader(p);
			BufferedReader br = new BufferedReader(isr);
			String value = br.readLine();
			System.out.println(value);
			if((value.contains("mCallState=1"))){
				System.out.println("Phone is ringing");
				minWait();
				recieve_Call_PrimaryDev_Without_Try_Catch(SA);
				/*	customWait(10000);
				endCall_RefDevice_Without_Try_Catch();*/
				minWait();
				System.out.println("Call InProgress");
				test.log(LogStatus.PASS, "Call InProgress");
			}
			else{
				Runtime.getRuntime().exec("adb -s "+r_Id+" shell input keyevent 6"); //ends the call in reference device
				System.out.println("Call is not initialized");
				test.log(LogStatus.FAIL, "Call is not initialized");
			}
		}
		catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.ERROR, "Error in the locators->validateCallInterruption()");
			e.printStackTrace();
		}
		catch (Exception e) {
			test.log(LogStatus.ERROR, "Exception in validateCallInterruption()");
			e.printStackTrace();
		}

	}

	public void deleteSMSInPostCondition() throws InterruptedException{
		try{
			if(p_b_No.contains("-15.")){
				System.out.println("Firmware Version ATT");
				test.log(LogStatus.INFO, "Firmware Version -15.");
				launch_an_app("messagePlus");
				minWait();
				TouchAction action = new TouchAction(aDriver);
				action.longPress(Locators_XP8_CallHistory.contact_two, 10).release().perform();
				minWait();
				clickBtn(Locators_XP8_CallHistory.deleteConversationInVZW);
				minWait();
				clickBtn(Locators_XP8_CallHistory.deleteOptInVZW);
				System.out.println("Message deleted successfully");
				test.log(LogStatus.PASS, "Message deleted successfully");
				minWait();
			}
			else if(p_b_No.contains("-29.")){
				System.out.println("Firmware Version SPRINT");
				test.log(LogStatus.INFO, "Firmware Version -29.");
				launch_an_app("messaging");
				minWait();
				(Locators_XP8_CallHistory.contact_two).click();
				minWait();
				clickBtn(Locators_XP8_CallHistory.moreOptionsInCallHistory);
				minWait();
				clickBtn(Locators_XP8_CallHistory.deleteInUiSelector);
				minWait();
				System.out.println("Message deleted successfully");
				test.log(LogStatus.PASS, "Message deleted successfully");
				minWait();
			}
			else{
				System.out.println("Firmware Version Others");
				test.log(LogStatus.INFO, "Firmware Version Others");
				launch_an_app("messaging");
				minWait();
				TouchAction action = new TouchAction(aDriver);
				action.longPress(Locators_XP8_CallHistory.contact_two, 10).release().perform();
				clickBtn(multi_Loc_Strategy(Locators_XP8_CallHistory.SMSDeleteLogo, null, null, null, null, 0, 0));
				minWait();
				clickBtn(multi_Loc_Strategy(Locators_XP8_CallHistory.delete_BUTTONInUiSelector, Locators_XP8_CallHistory.delete_BUTTON, null, null, null, 0, 0));
				System.out.println("Message deleted successfully");
				test.log(LogStatus.PASS, "Message deleted successfully");
				minWait();
			}
		}
		catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.ERROR, "Error in the locators->deleteSMSInPostCondition()");
			e.printStackTrace();
		}
		catch (Exception e) {
			test.log(LogStatus.ERROR, "Exception in deleteSMSInPostCondition() ");
			e.printStackTrace();
		}
	}

	public void deleteContacts(String Phone, SoftAssert SA){

		try{
			launch_an_app("phone");
			if(isElementExist(multi_Loc_Strategy(Locators_XP8_CallHistory.dialpadBtn, Locators_XP8_CallHistory.dialpadBtnInVZW, null, null, null, 0, 0))){
				validate_Permission_Popup_In_Call_Logs_Without_Try_Catch(SA);
			}
			else{
				System.out.println("Dial Pad isn't available");
				APP_LOGS.info("Dial Pad isn't available");
				test.log(LogStatus.FAIL, "Test case Status is FAIL");
				SA.fail();
			}
			enterTextToInputField(multi_Loc_Strategy(Locators_XP8_CallHistory.dialpadEditFld, Locators_XP8_CallHistory.dialpadEditFldVZW, null, null, null, 0, 0), Phone);
			minWait();
			if(isElementExist(multi_Loc_Strategy(Locators_XP8_CallHistory.dailedContactDetails, Locators_XP8_CallHistory.dailedContactDetailsInVZW, null, null, null, 0, 0))){
				//if(!multi_Loc_Strategy(Locators_XP8_CallHistory.dailedContactDetails, Locators_XP8_CallHistory.dailedContactDetailsInVZW, null, null, null, 0, 0).equals("Primary Number") || !multi_Loc_Strategy(Locators_XP8_CallHistory.dailedContactDetails, Locators_XP8_CallHistory.dailedContactDetailsInVZW, null, null, null, 0, 0).equals("Reference Number")){
				minWait();
				System.out.println("Contact is saved already");
				APP_LOGS.info("Contact is saved already");
				test.log(LogStatus.INFO, "Contact is saved already");
				clickBtn(multi_Loc_Strategy(Locators_XP8_CallHistory.contactIconInVZW, Locators_XP8_CallHistory.contactIconInVZWInst, Locators_XP8_CallHistory.contactIconInVZWInstRef, Locators_XP8_CallHistory.contactLogoIns, Locators_XP8_CallHistory.contactLogo, 48, 231));
				minWait();
				clickBtn(multi_Loc_Strategy(Locators_XP8_CallHistory.moreSettings, Locators_XP8_CallHistory.moreSettingsInUiSelector, null, null, null, 0, 0));
				minWait();
				clickBtn(multi_Loc_Strategy(Locators_XP8_CallHistory.delete, Locators_XP8_CallHistory.deleteInUiSelector, null, null, null, 0, 0));
				minWait();
				clickBtn(multi_Loc_Strategy(Locators_XP8_CallHistory.delete_BUTTON, null, null, null, null, 0, 0));
				clickBackButton(2);
				System.out.println("Contacts deleted");
				test.log(LogStatus.INFO, "Contacts deleted");
			}			
			else {
				System.out.println("Contact is not saved");
				test.log(LogStatus.INFO, "Contact is not saved");
				clickBackButton(2);
			}
			/*launch_an_app("contacts");
			minWait();
			clickBtn(Locators_XP8_CallHistory.delete_option);
			minWait();
			clickBtn(Locators_XP8_CallHistory.zero_selected);
			minWait();
			clickBtn(Locators_XP8_CallHistory.selectAllOpt);
			minWait();
			String selectedContacts = (Locators_XP8_CallHistory.selectOpt).getText();
			System.out.println(selectedContacts);
			if(selectedContacts.equals("0 selected")){
				System.out.println("No Contacts to Delete");
			}
			else{
				System.out.println("Deleting "+selectedContacts+" Contacts");
				clickBtn(Locators_XP8_CallHistory.btn_ok_Contacts);
				minWait();
				clickBtn(Locators_XP8_CallHistory.Ok_option);
				minWait();
			}*/

		}
		catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.ERROR, "Error in the locators->deleteContacts()");
			e.printStackTrace();
		}
		catch (Exception e) {
			test.log(LogStatus.ERROR, "Exception in deleteContacts() ");
			e.printStackTrace();
		}

	}

	public void validateClearCallHistory(SoftAssert SA) throws InterruptedException, IOException{
		try {
			navigateTocallHistory_Without_Try_Catch(SA);
			minWait();
			if(!isElementExist(Locators_XP8_CallHistory.callLogEmptyPage)){
				clickBtn(multi_Loc_Strategy(Locators_XP8_CallHistory.moreOptionsInCallHistory, Locators_XP8_CallHistory.moreSettingsInUiSelector, Locators_XP8_CallHistory.moreSettings, null, null, 912, 96));
				minWait();
				clickBtn(Locators_XP8_CallHistory.clearCallHistoryOpt);
				minWait();
				clickBtn(Locators_XP8_CallHistory.Ok_option);
				minWait();
				if(isElementExist(Locators_XP8_CallHistory.callLogEmptyPage)){
					System.out.println("Call History is cleared");
					APP_LOGS.info("Call History is cleared");
					test.log(LogStatus.PASS, "Call History is cleared");
				}
				else{
					System.out.println("Call History is not cleared");
					APP_LOGS.info("Call History is not cleared");
					test.log(LogStatus.FAIL, "Call History is not cleared");
					SA.fail();
				}				
			}
			else{
				String callHistory = (Locators_XP8_CallHistory.callLogEmptyPage).getText();
				System.out.println(callHistory);
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
			test.log(LogStatus.ERROR, "Exception in validateClearCallHistory() ");
			e.printStackTrace();
			SA.fail();
		}
	}

	public void navigateTocallHistory_Without_Try_Catch(SoftAssert SA) throws InterruptedException, IOException
	{
		/*
		 * navigate to call History page
		 */		 
		minWait();
		clickBtn(multi_Loc_Strategy(Locators_XP8_CallHistory.moreOptionsInCallHistory, Locators_XP8_CallHistory.moreSettingsInUiSelector, Locators_XP8_CallHistory.moreSettings, null, null, 912, 96));
		minWait();
		clickBtn(Locators_XP8_CallHistory.callHistoryOpt);
		minWait();
		System.out.println("Call History Option");
		APP_LOGS.info("Call History Option");
		test.log(LogStatus.PASS, "Call History Option" );
	}

	public void validate_Permission_Popup_In_Call_Logs_Without_Try_Catch(SoftAssert SA) throws InterruptedException{
		/*
		Validates if Permission Pop Up appears Dial pad is clicked
		 */
		clickBtn(multi_Loc_Strategy(Locators_XP8_CallHistory.dialpadBtn, Locators_XP8_CallHistory.dialpadBtnInVZW, null, null, null, 0, 0));
		if(isElementExist(Locators_XP8_CallHistory.dialpad_PermissionPopUp)){
			minWait();
			clickBtn(Locators_XP8_CallHistory.Ok_option);
			check = true;
			System.out.println("Permission Pop-Up is actioned");
			APP_LOGS.info("Permission Pop-Up is actioned");
			test.log(LogStatus.PASS, "Permission Pop-Up is actioned");
		}
	}

	public void enterNumberInDialpad(String Phone,SoftAssert SA) throws InterruptedException
	{
		/*
		 * clicks dialpad and validate the number entered
		 */
		try {
			if(isElementExist(multi_Loc_Strategy(Locators_XP8_CallHistory.dialpadBtn, Locators_XP8_CallHistory.dialpadBtnInVZW, null, null, null, 0, 0))){
				validate_Permission_Popup_In_Call_Logs_Without_Try_Catch(SA);
			}
			else{
				System.out.println("Dial Pad isn't available");
				APP_LOGS.info("Dial Pad isn't available");
				test.log(LogStatus.FAIL, "Test case Status is FAIL");
				SA.fail();
			}
			enterTextToInputField(multi_Loc_Strategy(Locators_XP8_CallHistory.dialpadEditFld, Locators_XP8_CallHistory.dialpadEditFldVZW, null, null, null, 0, 0), Phone);
			minWait();
			if(isElementExist(multi_Loc_Strategy(Locators_XP8_CallHistory.dailedContactDetails, Locators_XP8_CallHistory.dailedContactDetailsInVZW, null, null, null, 0, 0))){
				minWait();
				//clickBtn(multi_Loc_Strategy(Locators_XP8_CallHistory.dailedContactDetails, Locators_XP8_CallHistory.dailedContactDetailsInVZW, null, null, null, 0, 0));
				System.out.println("Contact is saved already");
				APP_LOGS.info("Contact is saved already");
				test.log(LogStatus.PASS, "Contact is saved already");
			}			
			else {
				System.out.println("Contact is not saved!! Trying to Save");
				APP_LOGS.info("Contact is not saved!! Trying to Save");
				test.log(LogStatus.INFO, "Contact is not saved!! Trying to Save");
				if(Phone == pryNum){
					CreateNewContactAndSave_Without_Try_Catch("Primary Number",SA);
					clickBackButton(3);
					System.out.println("Re-run");
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
			}
			APP_LOGS.info("Contact is saved and rechecked");
		} 
		catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->enterNumberInDialpad()");
			e.printStackTrace();
			SA.fail();
		}catch (Exception e) {
			test.log(LogStatus.ERROR, "Exception in enterNumberInDialpad()");
			e.printStackTrace();
			SA.fail();
		}
	}

	public void make_Call_from_RefDev() throws InterruptedException, IOException {

		try {
			minWait();
			Runtime.getRuntime().exec("adb -s "+r_Id+" shell am start -a android.intent.action.CALL -d tel:"+pryNum);
			System.out.println("Call is initialized from reference");
			minWait();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void make_Call_from_RefDev_Without_Try_Catch() throws InterruptedException, IOException {

		minWait();
		Runtime.getRuntime().exec("adb -s "+r_Id+" shell am start -a android.intent.action.CALL -d tel:"+pryNum);
		System.out.println("Call is initialized from reference");
		minWait();
	}

	public void recieve_Call_PrimaryDev(SoftAssert SA) throws IOException, InterruptedException, AWTException, org.json.simple.parser.ParseException {
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


			while(true){


				String id=p_Id;
				String cmd="adb -s "+id+" shell \"dumpsys telephony.registry | grep mCallState\"";
				InputStream p = Runtime.getRuntime().exec(cmd).getInputStream();
				InputStreamReader isr = new InputStreamReader(p);
				BufferedReader br = new BufferedReader(isr);
				String value = br.readLine();
				System.out.println(value);



				if(value.contains("mCallState=0")){
					System.out.println("Call isn't recieved in DUT");
					Runtime.getRuntime().exec("adb -s "+r_Id+" shell input keyevent 6");
					System.out.println("Call is disconnected from reference");
					break;
				}
				else if((value.contains("mCallState=1"))){
					System.out.println("Phone is ringing");
					minWait();
					Runtime.getRuntime().exec("adb -s "+r_Id+" shell input keyevent 6");
					System.out.println("Call is disconnected from reference");
					Thread.sleep(1000);
					break;
				}
				else if((value.contains("mCallState=2"))){
					System.out.println("Call is Active");
					minWait();
					Runtime.getRuntime().exec("adb -s "+r_Id+" shell input keyevent 6");
					System.out.println("Call is disconnected from reference");
					Thread.sleep(1000);
					break;
				}
				else{
					System.out.println("Call is not connecting");
				}
			}
		} catch (Exception e) {
			e.getMessage();
		}
	}

	public void endCall_RefDevice_Without_Try_Catch() throws InterruptedException, IOException {
		while(true){
			String id=p_Id;
			String cmd="adb -s "+id+" shell \"dumpsys telephony.registry | grep mCallState\"";
			InputStream p = Runtime.getRuntime().exec(cmd).getInputStream();
			InputStreamReader isr = new InputStreamReader(p);
			BufferedReader br = new BufferedReader(isr);
			String value = br.readLine();
			System.out.println(value);

			if(value.contains("mCallState=0")){
				System.out.println("Call isn't recieved in DUT");
				Runtime.getRuntime().exec("adb -s "+r_Id+" shell input keyevent 6");
				System.out.println("Call is disconnected from reference");
				break;
			}
			else if((value.contains("mCallState=1"))){
				System.out.println("Phone is ringing");
				minWait();
				Runtime.getRuntime().exec("adb -s "+r_Id+" shell input keyevent 6");
				System.out.println("Call is disconnected from reference");
				Thread.sleep(1000);
				break;
			}
			else if((value.contains("mCallState=2"))){
				System.out.println("Call is Active");
				minWait();
				Runtime.getRuntime().exec("adb -s "+r_Id+" shell input keyevent 6");
				System.out.println("Call is disconnected from reference");
				Thread.sleep(1000);
				break;
			}
			else{
				System.out.println("Call is not connecting");
			}
		}
	}

	public void CreateNewContactAndSave_Without_Try_Catch(String name,SoftAssert SA) throws InterruptedException
	{
		/*
		 * Clicks on Create New contact -->enter name --> save
		 */		
		minWait();
		clickBtn(multi_Loc_Strategy(Locators_XP8_CallHistory.createNewContactOptUiSelector, Locators_XP8_CallHistory.createNewContactOpt, Locators_XP8_CallHistory.createNewContactOptUiInstance, null, null, 240, 111));
		customWait(1000);
		if(isElementExist(Locators_XP8_CallHistory.contactsDefaultAccountSettings)){
			System.out.println("Found Default Account Setting options");
			clickBtn(Locators_XP8_CallHistory.contactsDefaultPhone);
		}
		else{
			System.out.println("Didnt get into Default ACT Option");
		}
		enterTextToInputField(Locators_XP8_CallHistory.nameEditFld,name);
		customWait(1000);
		clickBtn(multi_Loc_Strategy(Locators_XP8_CallHistory.saveContactOpt, Locators_XP8_CallHistory.saveContactOptVZW, null, null, null, 0, 0));
	}

	public void addAlarm(){
		/*
		Create Alarm as per the device time with 2mintues gap
		 */
		SoftAssert SA = new SoftAssert();
		try{
			launch_an_app("clock");
			System.out.println("Clock launched");
			minWait();
			clickBtn(multi_Loc_Strategy(Locators_XP8_CallHistory.alarm, Locators_XP8_CallHistory.alarmInstance, null, null, null, 61, 103));
			System.out.println("Clicked on Alarm");
			minWait();
			clickBtn(multi_Loc_Strategy(Locators_XP8_CallHistory.createalarm, Locators_XP8_CallHistory.createalarmInstance, Locators_XP8_CallHistory.createalarmInstance, null, null, 456, 1704));
			System.out.println("Clicked on create Alarm");
			minWait();
			clickBtn(multi_Loc_Strategy(Locators_XP8_CallHistory.alarmSwitchToText, Locators_XP8_CallHistory.alarmSwitchToTextInstance, Locators_XP8_CallHistory.alarmSwitchToTextXpath, null, null, 144, 1428));
			System.out.println("Clicked on Alarm Switch to Text");
			minWait();

			String device_Time=getCurrentDeviceTime("adb -s  "+p_Id+" shell date +'%r'");
			System.out.println(device_Time);
			test.log(LogStatus.INFO, device_Time);
			String[] split_Device_Time=device_Time.split(" ");
			String time_WithOutAMPM=split_Device_Time[0];    //+":"+split_time[1];
			String[] spiltTimeByHHMM = time_WithOutAMPM.split(":");
			String spiltFirstElementHH = spiltTimeByHHMM[0];
			System.out.println("Current Time HH is "+spiltFirstElementHH);
			test.log(LogStatus.INFO,"Current Time HH is "+spiltFirstElementHH);

			String spiltFirstElementMM = spiltTimeByHHMM[1];
			System.out.println("Current Time MM is "+spiltFirstElementMM);
			test.log(LogStatus.INFO,"Current Time MM is "+spiltFirstElementMM);
			/*if((spiltFirstElementMM).contains("58") || (spiltFirstElementMM).contains("59")){
				min = 04;
				System.out.println("Min "+min);

			}
			else{
				min = 2;
				System.out.println("Min "+min);
			}*/
			int min=2;
			int minToInt = Integer.parseInt(spiltFirstElementMM);
			System.out.println("MM is "+minToInt);
			int extraMintue=minToInt+min;
			String finalAlarmMinute = Integer.toString(extraMintue);
			if((finalAlarmMinute).contains("60")){
				System.out.println("Final Alarm exceeds 60 ");
				int HHtoInt1 = Integer.parseInt(spiltFirstElementHH);
				int HH=1;
				int spiltFirstElementHH1 = HHtoInt1+HH;
				System.out.println("MM Exceeds 60 so HH is "+spiltFirstElementHH1);
				String finalAlarmMintue1 = "00";
				System.out.println("Final Alarm Minute"+finalAlarmMintue1);

				/*clickBtn(multi_Loc_Strategy(Locators_XP8_CallHistory.alarmInputHour, null, null, null, null, 180, 1007));*/
				//customWait(2000);
				clickBtn((multi_Loc_Strategy(Locators_XP8_CallHistory.alarmInputHour, null, null, null, null, 180, 1007)));
				customWait(2000);
				/*TouchAction action = new TouchAction(aDriver);
				action.tap(285, 1069).perform();
				minWait();*/
				/*Runtime.getRuntime().exec("adb -s "+p_Id+" shell input keyevent 67");
				minWait();
				Runtime.getRuntime().exec("adb -s "+p_Id+" shell input keyevent 67");*/
				/*TouchAction action = new TouchAction(aDriver);
				action.tap(282, 784).perform();
				customWait(2000);*/
				(multi_Loc_Strategy(Locators_XP8_CallHistory.alarmInputHour, null, null, null, null, 180, 1007)).clear();
				minWait();
				Runtime.getRuntime().exec("adb -s "+p_Id+" shell input text "+spiltFirstElementHH1);
				minWait();
				clickBtn(multi_Loc_Strategy(Locators_XP8_CallHistory.alarmInputMinute, null, null, null, null, 330, 1007));
				minWait();
				/*TouchAction action1 = new TouchAction(aDriver);
				action1.tap(446, 778).perform();*/
				(multi_Loc_Strategy(Locators_XP8_CallHistory.alarmInputMinute, null, null, null, null, 330, 1007)).clear();
				minWait();
				/*Runtime.getRuntime().exec("adb -s "+p_Id+" shell input keyevent 67");
				minWait();
				Runtime.getRuntime().exec("adb -s "+p_Id+" shell input keyevent 67");
				minWait();*/
				Runtime.getRuntime().exec("adb -s "+p_Id+" shell input text "+finalAlarmMintue1);
				minWait();
				clickBtn(Locators_XP8_CallHistory.Ok_option);
				/*TouchAction action3 = new TouchAction(aDriver);
				action3.tap(841, 1000).perform();*/
				customWait(2000);
				/*if(isElementExist(multi_Loc_Strategy(Locators_XP8_CallHistory.deleteOptInVZW, null, null, null, null, 72, 1488))){*/
				System.out.println("Alarm is set to "+spiltFirstElementHH1+":"+finalAlarmMintue1+" "+split_Device_Time[1]);
				test.log(LogStatus.INFO,"Alarm is set to "+spiltFirstElementHH1+":"+finalAlarmMintue1+" "+split_Device_Time[1]);
				/*}
				else{
					System.out.println("Alarm is not set");
					test.log(LogStatus.INFO,"Alarm is not set");
				}*/
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_APP_SWITCH);
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_APP_SWITCH);
				minWait();
				aDriver.openNotifications();
				minWait();
				System.out.println("Waiting for Alarm to Trigger");
				test.log(LogStatus.INFO,"Waiting for Alarm to Trigger");
				/*customWait(120000);     //waits for 2mins
				String device_Time1=getCurrentDeviceTime("adb -s  "+p_Id+" shell date +'%r'");
				System.out.println(device_Time1);
				if(device_Time1.contains(finalAlarmMintue1)){
					takeScreenShot();
					Read_File.takeScreenShotForOcr("alarm1");
					my_main.validate_Using_OCR("alarm1.png");
					validate_Alarm_OCR(SA);
					Runtime.getRuntime().exec("adb -s "+p_Id+" shell input tap 807 357");
					System.out.println("Alarm is dismissed");
					test.log(LogStatus.PASS,"Alarm is dismissed");
				}
				else{
					System.out.println("Time Exceeds");
					test.log(LogStatus.FAIL,"Time Exceeds");
					Runtime.getRuntime().exec("adb -s "+p_Id+" shell input tap 807 357");
					System.out.println("Alarm is dismissed");
					test.log(LogStatus.INFO,"Alarm is dismissed");
				}*/
				
				WebDriverWait wait1 = new WebDriverWait(aDriver, 180);
				wait1.until(ExpectedConditions.visibilityOf(Locators_XP8_CallHistory.alarmSnoozeBtn));
				
				if(isElementExist(Locators_XP8_CallHistory.alarmSnoozeBtn)){
					clickBtn(Locators_XP8_CallHistory.alarmDismissBtn);
					System.out.println("Alarm is dismissed");
					test.log(LogStatus.PASS,"Alarm is dismissed");
				}
				else{
					Runtime.getRuntime().exec("adb -s "+p_Id+" shell input tap 807 357");
					System.out.println("Alarm is dismissed");
					test.log(LogStatus.INFO,"Alarm is dismissed");
				}
			}
			else if((finalAlarmMinute).contains("61")){

				System.out.println("Final Alarm exceeds 61");
				int HHtoInt2 = Integer.parseInt(spiltFirstElementHH);
				int HH=1;
				int spiltFirstElementHH2 = HHtoInt2+HH;
				System.out.println("MM Exceeds 61 so HH is "+spiltFirstElementHH2);
				String finalAlarmMintue2 = "01";
				System.out.println("Final Alarm Minute"+finalAlarmMintue2);

				/*clickBtn(multi_Loc_Strategy(Locators_XP8_CallHistory.alarmInputHour, null, null, null, null, 180, 1007));*/
				//customWait(2000);
				clickBtn((multi_Loc_Strategy(Locators_XP8_CallHistory.alarmInputHour, null, null, null, null, 180, 1007)));
				customWait(2000);
				/*TouchAction action = new TouchAction(aDriver);
				action.tap(285, 1069).perform();
				minWait();
				Runtime.getRuntime().exec("adb -s "+p_Id+" shell input keyevent 67"); //Clears the text
				minWait();
				Runtime.getRuntime().exec("adb -s "+p_Id+" shell input keyevent 67");*/   //Clears the text
				/*TouchAction action = new TouchAction(aDriver);
				action.tap(282, 784).perform();
				customWait(2000);*/
				(multi_Loc_Strategy(Locators_XP8_CallHistory.alarmInputHour, null, null, null, null, 180, 1007)).clear();
				minWait();
				Runtime.getRuntime().exec("adb -s "+p_Id+" shell input text "+spiltFirstElementHH2);
				minWait();
				clickBtn(multi_Loc_Strategy(Locators_XP8_CallHistory.alarmInputMinute, null, null, null, null, 330, 1007));
				minWait();
				/*TouchAction action1 = new TouchAction(aDriver);
				action1.tap(446, 778).perform();*/
				(multi_Loc_Strategy(Locators_XP8_CallHistory.alarmInputMinute, null, null, null, null, 330, 1007)).clear();
				minWait();
				/*minWait();
				Runtime.getRuntime().exec("adb -s "+p_Id+" shell input keyevent 67");         //Clears the text
				minWait();
				Runtime.getRuntime().exec("adb -s "+p_Id+" shell input keyevent 67");         //Clears the text
				minWait();*/
				Runtime.getRuntime().exec("adb -s "+p_Id+" shell input text "+finalAlarmMintue2);
				customWait(2000);
				clickBtn(Locators_XP8_CallHistory.Ok_option);
				/*TouchAction action3 = new TouchAction(aDriver);
				action3.tap(841, 1000).perform();*/
				minWait();
				/*if(isElementExist(multi_Loc_Strategy(Locators_XP8_CallHistory.deleteOptInVZW, null, null, null, null, 72, 1488))){*/
				System.out.println("Alarm is set to "+spiltFirstElementHH2+":"+finalAlarmMintue2+" "+split_Device_Time[1]);
				test.log(LogStatus.PASS,"Alarm is set to "+spiltFirstElementHH2+":"+finalAlarmMintue2+" "+split_Device_Time[1]);
				/*}
				else{
					System.out.println("Alarm is not set");
					test.log(LogStatus.INFO,"Alarm is not set");
				}*/
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_APP_SWITCH);
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_APP_SWITCH);
				minWait();
				aDriver.openNotifications();
				minWait();
				System.out.println("Waiting for Alarm to Trigger");
				test.log(LogStatus.INFO,"Waiting for Alarm to Trigger");
				/*customWait(120000);    //waits for 2mins
				String device_Time1=getCurrentDeviceTime("adb -s  "+p_Id+" shell date +'%r'");
				System.out.println(device_Time1);
				if(device_Time1.contains(finalAlarmMintue2)){
					takeScreenShot();
					Read_File.takeScreenShotForOcr("alarm1");
					my_main.validate_Using_OCR("alarm1.png");
					validate_Alarm_OCR(SA);
					Runtime.getRuntime().exec("adb -s "+p_Id+" shell input tap 807 357");
					System.out.println("Alarm is dismissed");
					test.log(LogStatus.PASS,"Alarm is dismissed");
				}
				else{
					System.out.println("Time Exceeds");
					test.log(LogStatus.FAIL,"Time Exceeds");
					Runtime.getRuntime().exec("adb -s "+p_Id+" shell input tap 807 357");
					System.out.println("Alarm is dismissed");
					test.log(LogStatus.INFO,"Alarm is dismissed");
				}*/
				
				WebDriverWait wait2 = new WebDriverWait(aDriver, 180);
				wait2.until(ExpectedConditions.visibilityOf(Locators_XP8_CallHistory.alarmSnoozeBtn));
				
				if(isElementExist(Locators_XP8_CallHistory.alarmSnoozeBtn)){
					clickBtn(Locators_XP8_CallHistory.alarmDismissBtn);
					System.out.println("Alarm is dismissed");
					test.log(LogStatus.PASS,"Alarm is dismissed");
				}
				else{
					Runtime.getRuntime().exec("adb -s "+p_Id+" shell input tap 807 357");
					System.out.println("Alarm is dismissed");
					test.log(LogStatus.INFO,"Alarm is dismissed");
				}
			}
			else{
				System.out.println("Final Alarm Minute"+finalAlarmMinute);



				/*clickBtn(multi_Loc_Strategy(Locators_XP8_CallHistory.alarmInputHour, null, null, null, null, 180, 1007));*/
				//customWait(2000);
				clickBtn((multi_Loc_Strategy(Locators_XP8_CallHistory.alarmInputHour, null, null, null, null, 180, 1007)));
				customWait(2000);
				/*TouchAction action = new TouchAction(aDriver);
				action.tap(285, 1069).perform();
				customWait(2000);
				Runtime.getRuntime().exec("adb -s "+p_Id+" shell input keyevent 67");
				minWait();
				Runtime.getRuntime().exec("adb -s "+p_Id+" shell input keyevent 67");*/
				/*TouchAction action = new TouchAction(aDriver);
			action.tap(282, 784).perform();
			customWait(2000);*/
				(multi_Loc_Strategy(Locators_XP8_CallHistory.alarmInputHour, null, null, null, null, 180, 1007)).clear();
				minWait();
				Runtime.getRuntime().exec("adb -s "+p_Id+" shell input text "+spiltFirstElementHH);
				minWait();
				clickBtn(multi_Loc_Strategy(Locators_XP8_CallHistory.alarmInputMinute, null, null, null, null, 330, 1007));
				minWait();
				/*TouchAction action1 = new TouchAction(aDriver);
				action1.tap(446, 778).perform();*/
				(multi_Loc_Strategy(Locators_XP8_CallHistory.alarmInputMinute, null, null, null, null, 330, 1007)).clear();
				minWait();
				/*customWait(2000);
				Runtime.getRuntime().exec("adb -s "+p_Id+" shell input keyevent 67");
				minWait();
				Runtime.getRuntime().exec("adb -s "+p_Id+" shell input keyevent 67");
				minWait();*/
				Runtime.getRuntime().exec("adb -s "+p_Id+" shell input text "+finalAlarmMinute);
				customWait(2000);
				clickBtn(Locators_XP8_CallHistory.Ok_option);
				/*TouchAction action3 = new TouchAction(aDriver);
				action3.tap(841, 1000).perform();*/
				customWait(2000);
				/*if(isElementExist(multi_Loc_Strategy(Locators_XP8_CallHistory.deleteOptInVZW, null, null, null, null, 72, 1488))){*/
				System.out.println("Alarm is set to "+spiltFirstElementHH+":"+finalAlarmMinute+" "+split_Device_Time[1]);
				test.log(LogStatus.PASS,"Alarm is set to "+spiltFirstElementHH+":"+finalAlarmMinute+" "+split_Device_Time[1]);
				/*}
			else{
				System.out.println("Alarm is not set");
				test.log(LogStatus.INFO,"Alarm is not set");
			}*/
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_APP_SWITCH);
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_APP_SWITCH);
				minWait();
				aDriver.openNotifications();
				minWait();
				System.out.println("Waiting for Alarm to Trigger");
				test.log(LogStatus.INFO,"Waiting for Alarm to Trigger");
				/*customWait(90000); //waits for 2mins
				String device_Time1=getCurrentDeviceTime("adb -s  "+p_Id+" shell date +'%r'");
				System.out.println(device_Time1);
				if(device_Time1.contains(finalAlarmMinute)){
					takeScreenShot();
					Read_File.takeScreenShotForOcr("alarm1");
					my_main.validate_Using_OCR("alarm1.png");
					validate_Alarm_OCR(SA);
					Runtime.getRuntime().exec("adb -s "+p_Id+" shell input tap 807 357");
					System.out.println("Alarm is dismissed");
					test.log(LogStatus.PASS,"Alarm is dismissed");
				}
				else{
					System.out.println("Time Exceeds");
					test.log(LogStatus.FAIL,"Time Exceeds");
					Runtime.getRuntime().exec("adb -s "+p_Id+" shell input tap 807 357");
					System.out.println("Alarm is dismissed");
					test.log(LogStatus.INFO,"Alarm is dismissed");
				}*/
				
				WebDriverWait wait3 = new WebDriverWait(aDriver, 180);
				wait3.until(ExpectedConditions.visibilityOf(Locators_XP8_CallHistory.alarmSnoozeBtn));
				
				if(isElementExist(Locators_XP8_CallHistory.alarmSnoozeBtn)){
					clickBtn(Locators_XP8_CallHistory.alarmDismissBtn);
					System.out.println("Alarm is dismissed");
					test.log(LogStatus.PASS,"Alarm is dismissed");
				}
				else{
					Runtime.getRuntime().exec("adb -s "+p_Id+" shell input tap 807 357");
					System.out.println("Alarm is dismissed");
					test.log(LogStatus.INFO,"Alarm is dismissed");
				}
			}
		}
		catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.ERROR, "Error in the locators->addAlarm()");
			e.printStackTrace();
			SA.fail();
		}catch (Exception e) {
			test.log(LogStatus.ERROR, "Exception in addAlarm()");
			e.printStackTrace();
			SA.fail();
		}
	}

	/*public void validate_Alarm_OCR(SoftAssert SA) {
		try {
			String content = readFile(".\\src\\test\\resources\\OCR_FILES\\ocr.txt");
			System.out.println("text from screenshot = " + content.substring(content.length() - 20));
			if (content.contains("Alarm")) {
				System.out.println("Alarm displayed Sucessfully");
				APP_LOGS.info("Alarm displayed Sucessfully");
				SA.assertTrue(true, "Alarm displayed Sucessfully");
				test.log(LogStatus.PASS, "Alarm displayed Sucessfully");
			} else {
				System.out.println("Alarm is not displayed");
				APP_LOGS.info("Fails to display the Alarm");
				test.log(LogStatus.FAIL, "Fails to display the Alarm");
				SA.fail();
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.ERROR, "Error in the locators->validate_Alarm_OCR()");
			e.printStackTrace();

		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exception in ->validate_Alarm_OCR()");
		}
	}*/



	public String getCurrentDeviceTime(String adbCommands) throws IOException, ParseException {

		Process child = Runtime.getRuntime().exec(adbCommands);
		InputStream lsOut = child.getInputStream();
		InputStreamReader r = new InputStreamReader(lsOut);
		BufferedReader in = new BufferedReader(r);
		String value=in.readLine();
		return value;

	}

	public void compareImages() throws IOException, InterruptedException{

		//my_main.validate_Using_OCR("homepage.png");
		// Java Program to compare two images 
		BufferedImage imgA = null; 
		BufferedImage imgB = null; 

		try
		{ 
			File fileA = new File(".\\src\\test\\resources\\OCR_FILES\\Alarm.png"); 
			File fileB = new File(".\\src\\test\\resources\\OCR_FILES\\alarm1.png"); 

			imgA = ImageIO.read(fileA); 
			imgB = ImageIO.read(fileB); 
		} 
		catch (IOException e) 
		{ 
			System.out.println(e); 
		} 
		int width1 = imgA.getWidth(); 
		int width2 = imgB.getWidth(); 
		int height1 = imgA.getHeight(); 
		int height2 = imgB.getHeight(); 

		if ((width1 != width2) || (height1 != height2)) 
			System.out.println("Error: Images dimensions mismatch"); 
		else
		{ 
			long difference = 0; 
			for (int y = 0; y < height1; y++) 
			{ 
				for (int x = 0; x < width1; x++) 
				{ 
					int rgbA = imgA.getRGB(x, y); 
					int rgbB = imgB.getRGB(x, y); 
					int redA = (rgbA >> 16) & 0xff; 
					int greenA = (rgbA >> 8) & 0xff; 
					int blueA = (rgbA) & 0xff; 
					int redB = (rgbB >> 16) & 0xff; 
					int greenB = (rgbB >> 8) & 0xff; 
					int blueB = (rgbB) & 0xff; 
					difference += Math.abs(redA - redB); 
					difference += Math.abs(greenA - greenB); 
					difference += Math.abs(blueA - blueB); 
				} 
			} 

			// Total number of red pixels = width * height 
			// Total number of blue pixels = width * height 
			// Total number of green pixels = width * height 
			// So total number of pixels = width * height * 3 
			double total_pixels = width1 * height1 * 3; 

			// Normalizing the value of different pixels 
			// for accuracy(average pixels per color 
			// component) 
			double avg_different_pixels = difference / 
					total_pixels; 

			// There are 255 values of pixels in total 
			double percentage = (avg_different_pixels / 
					255) * 100; 

			String s= Double.toString(percentage);

			if(s.contains("0.0")){
				System.out.println("Image Matched");
			}
			else{
				System.out.println("Difference Percentage-->" + percentage); 
				OCRScreencapture("Alarm");
			}
		} 
	} 

	public void timerExpire(){
		/*
		Sets the timer for 10Sec and validates the timer expire
		 */
		SoftAssert SA = new SoftAssert();
		try{
			launch_an_app("clock");
			System.out.println("Clock launched");
			minWait();
			clickBtn(multi_Loc_Strategy(Locators_XP8_CallHistory.timerOpt, null, null, null, null, 543, 103));
			minWait();
			clickBtn(multi_Loc_Strategy(Locators_XP8_CallHistory.timerValue1, Locators_XP8_CallHistory.timerValue1Ins, null, null, null, 64, 801));
			minWait();
			clickBtn(multi_Loc_Strategy(Locators_XP8_CallHistory.timerValue0, Locators_XP8_CallHistory.timerValue0Ins, null, null, null, 381, 1442));
			minWait();
			clickBtn(multi_Loc_Strategy(Locators_XP8_CallHistory.timerStartOpt, Locators_XP8_CallHistory.timerStartOptXpath, null, null, null, 456, 1704));
			/*	customWait(1000);
			//minWait();
			takeScreenShot();
			Read_File.takeScreenShotForOcr("timer");
			minWait();
			my_main.validate_Using_OCR("timer.png");
			validate_timer_OCR(SA);*/
			//minWait();
			aDriver.openNotifications();
			WebDriverWait wait4 = new WebDriverWait(aDriver, 30);
			wait4.until(ExpectedConditions.visibilityOf(Locators_XP8_CallHistory.clockStopBtn));
			
			if(isElementExist(Locators_XP8_CallHistory.clockStopBtn)){
				clickBtn(Locators_XP8_CallHistory.clockStopBtn);
				System.out.println("Timer expired Sucessfully");
				APP_LOGS.info("Timer expired Sucessfully");
				SA.assertTrue(true, "Timer expired Sucessfully");
			}
			else{
				System.out.println("Timer not expired Sucessfully");
				APP_LOGS.info("Timer not expired Sucessfully");
				SA.assertTrue(true, "Timer not expired Sucessfully");
			}
			
			
			/*String timer = (Locators_XP8_CallHistory.timerTimeText).getText();
			if(timer.contains("0")){
				System.out.println("Success");
				Runtime.getRuntime().exec("adb -s "+p_Id+" shell input tap 539 1790");
			}
			else{
				System.out.println("FAIL");
				Runtime.getRuntime().exec("adb -s "+p_Id+" shell input tap 539 1790");
			}*/
		}
		catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->timerExpire()");
			e.printStackTrace();
			SA.fail();
		}catch (Exception e) {
			test.log(LogStatus.ERROR, "Exception in timerExpire()");
			e.printStackTrace();
			SA.fail();
		}
	}

	/*public void validate_timer_OCR(SoftAssert SA) {
		try {
			String content = readFile(".\\src\\test\\resources\\OCR_FILES\\ocr.txt");
			System.out.println("text from screenshot = " + content.substring(content.length() - 28));
			if ((content.contains("-00:01")) || (content.contains("-00:02") || (content.contains("-00:03")))){
				System.out.println("Timer expired Sucessfully");
				APP_LOGS.info("Timer expired Sucessfully");
				SA.assertTrue(true, "Timer expired Sucessfully");
				test.log(LogStatus.PASS, "Timer expired Sucessfully");
				Runtime.getRuntime().exec("adb -s "+p_Id+" shell input tap 539 1790");
			} else {
				System.out.println("Timer not expired");
				APP_LOGS.info("Timer not expired");
				test.log(LogStatus.INFO, "Timer not expired");
				Runtime.getRuntime().exec("adb -s "+p_Id+" shell input tap 539 1790");
				SA.fail();
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.ERROR, "Error in the locators->validate_timer_OCR()");
			e.printStackTrace();

		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exception in ->validate_timer_OCR()");
		}
	}*/

	public void recieveSMSFromReferenceDevice() throws IOException, InterruptedException{
		
		launch_an_app("messaging");
		minWait();
		clickBtn(multi_Loc_Strategy(Locators_XP8_CallHistory.contact_two, null, null, null, null, 308, 366));
		
		/*WebDriverWait wait = new WebDriverWait(aDriver, 60);
		wait.until(ExpectedConditions.visibilityOf(Locators_XP8_CallHistory.messageSentNow));*/
		customWait(15000);
		if(isElementExist(Locators_XP8_CallHistory.messageSentNow)){
			System.out.println("Found NOW");
			APP_LOGS.info("Found NOW");
			test.log(LogStatus.PASS, "Found NOW");
		}
		else{
			System.out.println("NOW not Found");
			APP_LOGS.info("NOW not Found");
			test.log(LogStatus.FAIL, "NOW not Found");
		}
		//clearTheFile();
		/*takeScreenShot();
		Read_File.takeScreenShotForOcr("Message");
		my_main.validate_Using_OCR("Message.png");
		validate_Message_OCR(SA);*/
		/*
		if(isElementExist(multi_Loc_Strategy(Locators_XP8_CallHistory.messageSentNow, null, null, null, null, 0, 0))){
			System.out.println("Message Recieved successfully");
			APP_LOGS.info("Message Recieved successfully");
			test.log(LogStatus.PASS, "Message Recieved successfully");
		}else{
			System.out.println("Message Not Recieved");
			APP_LOGS.info("Message Not Recieved");
			test.log(LogStatus.INFO, "Message Not Recieved");
		}*/
	}

	public void recieveSMSFromReferenceDeviceForSMSInterruption() throws IOException, InterruptedException{

		String cmd1 = "adb -s "+r_Id+" shell service call isms 7 i32 0 s16 \"com.android.mms.service\" s16 "+pryNum+" s16 \"null\" s16 \"Hi\" SMAT\" !\" s16 \"null\" s16 \"null\"";
		Runtime.getRuntime().exec(cmd1);
		minWait();
		/*launch_an_app("messaging");
		minWait();
		clickBtn(multi_Loc_Strategy(Locators_XP8_CallHistory.contact_two, null, null, null, null, 308, 366));*/
		customWait(5000);
		if(isElementExist(multi_Loc_Strategy(Locators_XP8_CallHistory.messageSentNow, null, null, null, null, 0, 0))){
			System.out.println("Message Recieved successfully");
			APP_LOGS.info("Message Recieved successfully");
			test.log(LogStatus.PASS, "Message Recieved successfully");
		}else{
			System.out.println("Message Not Recieved");
			APP_LOGS.info("Message Not Recieved");
			test.log(LogStatus.INFO, "Message Not Recieved");
		}
	}

	public void callDetailsOpt(SoftAssert SA) throws InterruptedException{
		/*
		Clicks on contact title and opens the Call Details
		 */

		try {
			if(isElementExist(multi_Loc_Strategy(Locators_XP8_CallHistory.contactDetails, Locators_XP8_CallHistory.contactDetailsInVZW, null, null, null, 0, 0))){
				System.out.println("Contact Clicked");
				clickBtn(multi_Loc_Strategy(Locators_XP8_CallHistory.contactDetails, Locators_XP8_CallHistory.contactDetailsInVZW, null, null, null, 0, 0));
				minWait();
			}
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
			test.log(LogStatus.ERROR, "Exception in callDetailsOpt() ");
			e.printStackTrace();
			SA.fail();
		}
	}

	public void validateCallDetails(SoftAssert SA) throws InterruptedException{
		launch_an_app("phone");
		minWait();
		clickBtn(multi_Loc_Strategy(Locators_XP8_CallHistory.callLogPage, null, null, null, null, 543, 334));
		minWait();
		/*clickBtn(multi_Loc_Strategy(Locators_XP8_CallHistory.contact_two, null, null, null, null, 379, 618));*/
		callDetailsOpt(SA);
		System.out.println(multi_Loc_Strategy(Locators_XP8_CallHistory.call_Time, Locators_XP8_CallHistory.call_TimeVZW, null, null, null, 0, 0).getText());
		System.out.println("Last Call Duration"+(Locators_XP8_CallHistory.callDurationData).getText());	
	}

	public static void clearTheFile() throws IOException {
		/*FileWriter fwOb = new FileWriter(".\\src\\test\\resources\\OCR_FILES\\ocr.txt", false); 
		PrintWriter pwOb = new PrintWriter(fwOb, false);
		pwOb.flush();
		pwOb.close();
		fwOb.close();*/
		PrintWriter writer = new PrintWriter(".\\src\\test\\resources\\OCR_FILES\\ocr.txt");
		writer.print("");
		writer.close();
		System.out.println("COMPLETED");

	}

	public void clearChromePermission() {
		try {
			WebDriverWait wait = new WebDriverWait(aDriver, 60);
			if (isElementExist(multi_Loc_Strategy(Locators_Stability.ACCEPTCONTINUE, Locators_Stability.ACCEPTCONTINUEByID, Locators_Stability.ACCEPTCONTINUEByResourceId, Locators_Stability.ACCEPTCONTINUEByText, Locators_Stability.ACCEPTCONTINUEByXpath, 332, 1764))) {
				wait.until(ExpectedConditions.visibilityOf(multi_Loc_Strategy(Locators_Stability.ACCEPTCONTINUE, Locators_Stability.ACCEPTCONTINUEByID, Locators_Stability.ACCEPTCONTINUEByResourceId, Locators_Stability.ACCEPTCONTINUEByText, Locators_Stability.ACCEPTCONTINUEByXpath, 332, 1764)));
				clickBtn(multi_Loc_Strategy(Locators_Stability.ACCEPTCONTINUE, Locators_Stability.ACCEPTCONTINUEByID, Locators_Stability.ACCEPTCONTINUEByResourceId, Locators_Stability.ACCEPTCONTINUEByText, Locators_Stability.ACCEPTCONTINUEByXpath, 332, 1764));
				wait.until(ExpectedConditions.visibilityOf(multi_Loc_Strategy(Locators_Stability.NEXT, Locators_Stability.NEXTById, Locators_Stability.NEXTByContains, Locators_Stability.NEXTByText, Locators_Stability.NEXTByXpath, 48, 1764)));
				clickBtn(multi_Loc_Strategy(Locators_Stability.NEXT, Locators_Stability.NEXTById, Locators_Stability.NEXTByContains, Locators_Stability.NEXTByText, Locators_Stability.NEXTByXpath, 48, 1764));
				wait.until(ExpectedConditions.visibilityOf(multi_Loc_Strategy(Locators_Stability.NO_THANKS, Locators_Stability.NO_THANKS_By_Id, Locators_Stability.NO_THANKS_By_Contains, Locators_Stability.NO_THANKS_By_Text, Locators_Stability.NO_THANKS_By_Xpath, 48, 1764)));
				minWait();
				clickBtn(multi_Loc_Strategy(Locators_Stability.NO_THANKS, Locators_Stability.NO_THANKS_By_Id, Locators_Stability.NO_THANKS_By_Contains, Locators_Stability.NO_THANKS_By_Text, Locators_Stability.NO_THANKS_By_Xpath, 48, 1764));
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.ERROR, "Error in the locators->clearChromePermission()");
			e.printStackTrace();
		}
		catch (Exception e) {
			test.log(LogStatus.ERROR, "Exception in clearChromePermission() ");
			e.printStackTrace();
		}			
	}

	public void validateFMRadio() {

		try{
			launch_an_app("fm");
			minWait();
			if(!isElementExist(Locators_XP8_CallHistory.FMHeadSetError)){
				System.out.println("Headset connected");
				APP_LOGS.info("Headset connected");
				test.log(LogStatus.INFO, "Headset connected");
				if(!isElementExist(Locators_XP8_CallHistory.fmFrequency)){
					System.out.println("FM isn't ON");
					APP_LOGS.info("FM isn't ON");
					test.log(LogStatus.INFO, "FM isn't ON");
					minWait();
					clickBtn(Locators_XP8_CallHistory.fmPowerONOFF);
				}
				else{
					System.out.println("FM is ON");
					APP_LOGS.info("FM is ON");
					test.log(LogStatus.INFO, "FM is ON");
					minWait();
					clickBtn(Locators_XP8_CallHistory.fmPowerONOFF);
				}
			}
			else{
				System.out.println((Locators_XP8_CallHistory.FMHeadSetError).getText());
				APP_LOGS.info("Headset isnt connected");
				test.log(LogStatus.FAIL, (Locators_XP8_CallHistory.FMHeadSetError).getText());
			}
		}
		catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.ERROR, "Error in the locators->validateFMRadio()");
			e.printStackTrace();
		}
		catch (Exception e) {
			test.log(LogStatus.ERROR, "Exception in validateFMRadio() ");
			e.printStackTrace();
		}		
	}

	/*public void validate_Message_OCR(SoftAssert SA){
		try {
			String content = readFile(".\\src\\test\\resources\\OCR_FILES\\ocr.txt");
			System.out.println("text from screenshot = " + content.substring(content.length() - 28));
			if (content.contains("Now")) {
				System.out.println("Message displayed Sucessfully");
				APP_LOGS.info("Message displayed Sucessfully");
				test.log(LogStatus.PASS, "Message displayed Sucessfully");
			} else {
				System.out.println("Message is not displayed");
				APP_LOGS.info("Fails to display the Message");
				test.log(LogStatus.FAIL, "Fails to display the Message");
				SA.fail();
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.ERROR, "Error in the locators->validate_Message_OCR()");
			e.printStackTrace();

		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exception in ->validate_Message_OCR()");
		}
	}*/

	public void gmailAccess() throws InterruptedException{
		try{
			String gmailUN = "sonimcdma.369";
			String gmailPWD = "sonimcdma";
			launch_an_app("gmail");
			minWait();
			if((isElementExist(Locators_XP8_CallHistory.skipBtn)) || (isElementExist(Locators_XP8_CallHistory.gotIT))){
				System.out.println("Clicked on SKIP / GOT IT option");
				test.log(LogStatus.INFO, "Clicked on SKIP / GOT IT option");
				clickBtn(multi_Loc_Strategy(Locators_XP8_CallHistory.skipBtn, Locators_XP8_CallHistory.gotIT, null, null, null, 0, 0));

				if(isElementExist(Locators_XP8_CallHistory.gmailAccountName)){
					System.out.println("Sonim Gmail Account is already saved");
					test.log(LogStatus.PASS, "Gmail Account is already saved");
					clickBtn(Locators_XP8_CallHistory.takeMeToGmailOpt);
					minWait();
					if(isElementExist(Locators_XP8_CallHistory.nextBtn)){
						clickBtn(Locators_XP8_CallHistory.nextBtn);
						minWait();
						clickBtn(Locators_XP8_CallHistory.okBtnUiSelector);
						customWait(2000);
						if(isElementExist(Locators_XP8_CallHistory.gmailLoggedIn)){
							System.out.println("Gmail Account launched successfully");
							test.log(LogStatus.PASS, "Gmail Account launched successfully");
						}
						else{
							System.out.println("Gmail Account is not launched");
							test.log(LogStatus.FAIL, "Gmail Account launched");
						}
					}
					else{
						System.out.println("Gmail Account launched successfully");
						test.log(LogStatus.PASS, "Gmail Account launched successfully");
					}
				}
				else{
					System.out.println("Gmail Account is not saved");
					test.log(LogStatus.INFO, "Gmail Account is not saved");
					clickBtn(Locators_XP8_CallHistory.addAnotherEmailAddressopt);
					minWait();
					System.out.println("Click on Google Account");
					clickBtn(Locators_XP8_CallHistory.googleAccountOpt);
					WebDriverWait wait = new WebDriverWait(aDriver, 60);
					wait.until(ExpectedConditions.visibilityOf(Locators_XP8_CallHistory.nextBtn));
					clickBtn(Locators_XP8_CallHistory.gmailUserName);
					Runtime.getRuntime().exec("adb -s "+p_Id+" shell input text "+gmailUN);
					minWait();
					clickBtn(Locators_XP8_CallHistory.nextBtn);
					minWait();
					clickBtn(Locators_XP8_CallHistory.gmailPwd);
					minWait();
					Runtime.getRuntime().exec("adb -s "+p_Id+" shell input text "+gmailPWD);
					minWait();
					clickBtn(Locators_XP8_CallHistory.nextBtn);
					minWait();
					clickBtn(Locators_XP8_CallHistory.gmailAgreeBtn);
					customWait(3000);
					clickBtn(Locators_XP8_CallHistory.gmailMOREBtn);
					minWait();
					clickBtn(Locators_XP8_CallHistory.gmailAcceptBtn);
					customWait(5000);
					clickBtn(Locators_XP8_CallHistory.takeMeToGmailOpt);
					if(isElementExist(Locators_XP8_CallHistory.nextBtn)){
						clickBtn(Locators_XP8_CallHistory.nextBtn);
						minWait();
						clickBtn(Locators_XP8_CallHistory.okBtnUiSelector);
						customWait(2000);
						if(isElementExist(Locators_XP8_CallHistory.gmailLoggedIn)){
							System.out.println("Gmail Account launched successfully");
							test.log(LogStatus.PASS, "Gmail Account launched successfully");
						}
						else{
							System.out.println("Gmail Account is not launched");
							test.log(LogStatus.FAIL, "Gmail Account launched");
						}
					}
					else{
						System.out.println("Gmail Account launched successfully");
						test.log(LogStatus.PASS, "Gmail Account launched successfully");
					}
				}
			}
			else if(isElementExist(Locators_XP8_CallHistory.gmailLoggedIn)){
				System.out.println("Gmail Account launched successfully");
				test.log(LogStatus.PASS, "Gmail Account launched successfully");
			}
		}
		catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.ERROR, "Error in the locators->gmailAccess()");
			e.printStackTrace();

		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exception in ->gmailAccess()");
		}
	}


/*	public static void addAlarmTesting(BufferedImage ipimage, float scaleFactor,float offset) throws InterruptedException, IOException, ParseException, TesseractException{

		// Making an empty image buffer 
		// to store image later 
		// ipimage is an image buffer 
		// of input image 
		BufferedImage opimage= new BufferedImage(1050,1024,ipimage.getType()); 
		System.out.println("opimage"+opimage);
		// creating a 2D platform 
		// on the buffer image 
		// for drawing the new image 
		Graphics2D graphic = opimage.createGraphics(); 

		// drawing new image starting from 0 0 
		// of size 1050 x 1024 (zoomed images) 
		// null is the ImageObserver class object 
		graphic.drawImage(ipimage, 0, 0, 1050, 1024, null); 
		graphic.dispose(); 

		// rescale OP object 
		// for gray scaling images 
		RescaleOp rescale = new RescaleOp(scaleFactor, offset, null); 

		// performing scaling 
		// and writing on a .png file 
		BufferedImage fopimage = rescale.filter(opimage, null); 
		ImageIO.write(fopimage,"png",new File("C:\\Users\\jeevankumar.j\\Desktop\\JK GIT Demo\\TimerZ.png")); 
		System.out.println("Created a new file");

		// Instantiating the Tesseract class 
		// which is used to perform OCR 
		 Tesseract it = new Tesseract();
		System.out.println("Created new Tesseract class");

		it.setDatapath("C:/Users/jeevankumar.j/Desktop/JK GIT Demo/Tessdata"); 
		System.out.println("Read the TessData file");

		// doing OCR on the image 
		// and storing result in string str 
		String str = it.doOCR(fopimage);
		//String str = ((ITesseract) it).doOCR(fopimage); 
		System.out.println(str); 

	}

	public void hi() throws InterruptedException, TesseractException, IOException, ParseException
	{ 
		SoftAssert SA =new SoftAssert();
		File f 
					= new File( 
						"C:\\Users\\jeevankumar.j\\Desktop\\JK GIT Demo\\Timer.png"); 

				BufferedImage ipimage = ImageIO.read(f); 

				// getting RGB content of the whole image file 
				double d = ipimage.getRGB(ipimage.getTileWidth() / 2, ipimage.getTileHeight() / 2); 
				System.out.println("DDDDDDDDDD"+d);
				// comparing the values 
				// and setting new scaling values 
				// that are later on used by RescaleOP 
				if (d >= -1.4211511E7 && d < -7254228) { 
					System.out.println("1");
					addAlarmTesting(ipimage, 3f, -10f); 
				} 
				else if (d >= -7254228 && d < -2171170) { 
					System.out.println("2");
					addAlarmTesting(ipimage, 1.455f, -47f); 
				} 
				else if (d >= -2171170 && d < -1907998) { 
					System.out.println("3");
					addAlarmTesting(ipimage, 1.35f, -10f); 
				} 
				else if (d >= -1907998 && d < -257) { 
					System.out.println("4");
					addAlarmTesting(ipimage, 1.19f, 0.5f); 
				} 
				else if (d >= -257 && d < -1) { 
					System.out.println("5");
					addAlarmTesting(ipimage, 1f, 0.5f); 
				} 
				else if (d >= -1 && d < 2) { 
					System.out.println("6");
					addAlarmTesting(ipimage, 1f, 0.35f); 
				} 
				
		try{

			BufferedImage originalImage = 
					ImageIO.read(new File("C:\\Users\\jeevankumar.j\\Desktop\\JK GIT Demo\\Timer.png"));

			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ImageIO.write( originalImage, "png", baos );
			baos.flush();
			byte[] imageInByte = baos.toByteArray();
			System.out.println("Image In Byte"+imageInByte.toString());
			String ocr = new String(imageInByte);
			System.out.println("OCR"+ocr);
			baos.close();

		}catch(IOException e){
			System.out.println(e.getMessage());
		}		
		
	}
	
	static String SRC_Path = "C:/Users/jeevankumar.j/Desktop/JK GIT Demo/";
	static Tesseract tesseract = new Tesseract();
	static{
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		tesseract.setDatapath("C:/Users/jeevankumar.j/Desktop/JK GIT Demo/Tessdata");
	}
	
	String extractString(Mat inputMat){
		
		String result = "";
		Mat gray = new Mat();
		cvtColor(inputMat, gray, COLOR_RGB2GRAY);
		imwrite(SRC_Path + "NowGray.PNG", gray);
		Mat element = getStructuringElement(MORPH_RECT, new Size(2, 2), new Point(1, 1));
		dilate(gray, gray, element);
		erode(gray, gray, element);
		
		imwrite(SRC_Path + "closeopen.PNG", gray);
		
		try{
			result = tesseract.doOCR(new File(SRC_Path+"NowGray.png"));
		}
		catch (TesseractException e) {
			// TODO: handle exception
			System.out.println("Tesseract Exception");
			e.printStackTrace();
		}
		return result;
		
	}
	*/					
	public static void OCR() throws TesseractException, IOException{
		System.out.println("main");
		/*long start = System.currentTimeMillis();
		System.out.println(start);
		Mat origin = imread(SRC_Path + "Now.png");
		System.out.println(origin);
		String result = new XP8_Interruption_Util().extractString(origin);
		System.out.println(result);
		System.out.println("Time");
		System.out.println(System.currentTimeMillis() - start);
		System.out.println("Done");*/
		
		Tesseract ocr = new Tesseract();
		ocr.setDatapath("C:\\Users\\jeevankumar.j\\Desktop\\JK GIT Demo\\Tessdata\\");
		//String aasds = ocr.doOCR(new File("C:/Users/jeevankumar.j/Desktop/JK GIT Demo/XP5gray.jpg"));
		String aasds = ocr.doOCR(new File("/1_XP5s/src/test/resources/OCR_FILES/Message.png"));
		System.out.println(aasds);
	}
	
}

