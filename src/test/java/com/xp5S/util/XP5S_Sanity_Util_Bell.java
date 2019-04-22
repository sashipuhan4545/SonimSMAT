package com.xp5S.util;

import java.io.IOException;
import java.sql.Time;
import java.util.Calendar;
import java.util.Date;
import java.util.Formatter;

import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import io.appium.java_client.android.AndroidKeyCode;

public class XP5S_Sanity_Util_Bell extends BaseUtil {

	public boolean check = false;
	public boolean check1 = false;
	public boolean check2 = false;
	public boolean check3 = false;
	public boolean check4 = false;
	public boolean check5 = false;
	public boolean check6 = false;
	public boolean check7 = false;
	public boolean check8 = false;
	public boolean check9 = false;
	public boolean check10= false;


	// generation of random numbers
	public static int randonClickOnCalculatorKeyboard() throws InterruptedException  {
		Random rn = new Random();
		int answer = rn.nextInt(9) + 1;
		return answer;
	}


	public static int firstclick() throws InterruptedException{
		int first = randonClickOnCalculatorKeyboard();
		//Log.i(Constants.TAG, "First click" + String.valueOf(first));

		switch (first) {
		case 1:
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_1);
			break;
		case 2:
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_2);
			break;
		case 3:
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_3);
			break;
		case 4:
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_4);
			break;
		case 5:
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_5);
			break;
		case 6:
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_6);
			break;
		case 7:
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_7);
			break;
		case 8:
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_8);
			break;
		case 9:
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_9);
			break;
		case 10:
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_0);
			break;
		}return first;
	}

	public static int secondclick() throws InterruptedException{
		int second = randonClickOnCalculatorKeyboard();
		//Log.i(Constants.TAG, "First click" + String.valueOf(first));

		switch (second) {
		case 1:
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_1);
			break;
		case 2:
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_2);
			break;
		case 3:
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_3);
			break;
		case 4:
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_4);
			break;
		case 5:
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_5);
			break;
		case 6:
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_6);
			break;
		case 7:
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_7);
			break;
		case 8:
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_8);
			break;
		case 9:
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_9);
			break;
		case 10:
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_0);
			break;
		}return second;
	}

	public int gen() {
		Random r = new Random(System.currentTimeMillis());
		return 1000000000 + r.nextInt(2000000000);
	}

	public void launchSettings() throws InterruptedException {
		launchApp("SETTINGS_PACKAGE","SETTINGS_ACTIVITY");
		APP_LOGS.info("Settings Launched succesfully. ");
		minWait();
	}


	public void  navigateTo_DateAndTime() throws InterruptedException{
		/*
		 * Navigate to Date & Time  in native settings.
		 */	
		
		//		customWait(1000);
		aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_HOME);
		//		customWait(4000);
		try {
			clickBtn(Locators_Sanity.settingsIcon);
			minWait();
			APP_LOGS.info("Launched Settings icon sucessfully");
			for (int iter = 1; iter <= 21; iter++) {
				if (isElementExist(Locators_Sanity.date_time)) {
					APP_LOGS.info("Date and Time is displayed sucessfully");
					minWait();	
					break;					
				} else {
					aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
					continue;					
				}
			}clickBtn(Locators_Sanity.date_time);
		} catch (NoSuchElementException e) {
			APP_LOGS.info("Date and Time is not displayed.");
			minWait();	
			Assert.fail();
		}
	}
	
	
	

	public void validate_getDate_Time_TimeZone() throws InterruptedException {
		/*
		 * check the date ,time and timezone.
		 */
		SoftAssert Sv = new SoftAssert();
		try {
			if(Locators_Sanity.Autodate_time.isEnabled()){
				APP_LOGS.info("Automatic date and Time is enabled");
			}
			else {
				clickBtn(Locators_Sanity.Autodate_time);
			}
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
			minWait();	
			if(Locators_Sanity.Autotimezone.isEnabled()){
				APP_LOGS.info("Automatic date and Time is enabled");
			}
			else {
				clickBtn(Locators_Sanity.Autotimezone);
			}

			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
			minWait();	
			clickBtn(Locators_Sanity.setDate);
			minWait();	
			String get_Date = Locators_Sanity.Summary.getText();
			System.out.println(get_Date);
			
			
			if(isElementExist(Locators_Sanity.Summary)){
				check1 = true;
				APP_LOGS.info("Element found Date : "+get_Date);
				APP_LOGS.info("check1: "+check1);
			}else{
				APP_LOGS.info("Date element is not found");
				APP_LOGS.info("check1: "+check1);
			}
			minWait();	
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
			minWait();	
			clickBtn(Locators_Sanity.settime);
			minWait();	
			String get_Time = Locators_Sanity.Summary.getText();
			System.out.println(get_Time);
			minWait();	
			if(isElementExist(Locators_Sanity.Summary)){
				check2 = true;
				APP_LOGS.info("Element found Time : "+get_Time);
				APP_LOGS.info("check2: "+check2);
			}else{
				APP_LOGS.info("Time element is not found");
				APP_LOGS.info("check2: "+check2);
			}
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
			minWait();	
			clickBtn(Locators_Sanity.settimeZone);
			minWait();	
			String get_Timezone = Locators_Sanity.Summary.getText();
			System.out.println(get_Timezone);
			minWait();	
			if(isElementExist(Locators_Sanity.Summary)){
				check3 = true;
				APP_LOGS.info("Element found Time : "+get_Timezone);
				APP_LOGS.info("check3: "+check3);
			}else{
				APP_LOGS.info("Time element is not found");
				APP_LOGS.info("check3: "+check3);
			}
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			minWait();	
			
			if((check1)&&(check2)&&(check3)){
				check=true;
				APP_LOGS.info("All dynamic parameters are displayed");
				APP_LOGS.info("Test case is passed");
				Sv.assertTrue(check, "  ");
			}else{
				APP_LOGS.info("Test case failed");
				Sv.fail();
			}
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			Sv.fail();
		}
     Sv.assertAll();
	}
	
	public void validateRecordedSoundSaved() throws InterruptedException {
		/*
		 * check recording name is saved in recorded list after saving
		 */
		try {
		minWait();
		String SavedRecName=Locators_Sanity.recordingName.getText();
		minWait();
		System.out.println(SavedRecName);
		Locators_Sanity.saveBtn.click(); 
		APP_LOGS.info("Pressed Save button sucessfully");
		minWait();
		String ListSavedRecName=Locators_Sanity.saveBtnUnderRecList.getText();
		System.out.println(ListSavedRecName);
		minWait();
	
		if(ListSavedRecName.contains("New record")){
			check=true;
			APP_LOGS.info("Sound Record is saved succesfully");
			Assert.assertTrue(check);	
		}else{
			APP_LOGS.info("Sound Record is not saved, unsuccesful");
			Assert.fail();
		}	aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
		}
		catch (org.openqa.selenium.NoSuchElementException e) {
			APP_LOGS.info("Element not Found");
			e.printStackTrace();
		}
	}
	
	

	public void selectMoreOptions() throws InterruptedException {
		/*
		 *select more option in settings
		 */	aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_HOME);
		 //		customWait(4000);
		 try {
			 clickBtn(Locators_Sanity.settingsIcon);
			 minWait();
			 APP_LOGS.info("Launched Settings icon sucessfully");
			 for (int iter = 1; iter <= 9; iter++) {
				 if (isElementExist(Locators_Sanity.moreOptn)) {
					 APP_LOGS.info("More Option is displayed sucessfully");
					 minWait();	
					 clickBtn(Locators_Sanity.moreOptn);
					 break;					
				 } else {
					 aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
					 continue;					
				 }
			 }clickBtn(Locators_Sanity.date_time);
		 } catch (NoSuchElementException e) {
			 APP_LOGS.info("More Option is not displayed.");
			 minWait();	
			 Assert.fail();

		 }

	}

	public void validateEnabledisableFlightmode() throws InterruptedException {
		/*
		 * Validates enable and disable flight mode with phone settings
		 */	
		//		customWait(1000);

		SoftAssert SA = new SoftAssert();
		try {
			if (isElementExist(Locators_Sanity.Flightmode)) {
				APP_LOGS.info("flight mode is displayed sucessfully");
				minWait();	
				clickBtn(Locators_Sanity.AiroplaneSwitch);
				minWait();	
			}
			if (Locators_Sanity.Flightmode.isEnabled()) {
				APP_LOGS.info("flight mode is enabled sucessfully");
				check1 = true;
				minWait();	
				clickBtn(Locators_Sanity.AiroplaneSwitch);
				minWait();	
				minWait();	
			}				
			if (Locators_Sanity.Flightmode.isSelected()) {
				minWait();	
				APP_LOGS.info("flight mode is enabled ");
			}
			else {
				minWait();	
				APP_LOGS.info("flight mode is disabled sucessfully");
				check2 = true;
				minWait();	
			}
			if((check1)&&(check2)){
				check= true;
				APP_LOGS.info("Enable ansd disable of flight mode with phone settings.");
				APP_LOGS.info("TC is Passed.");
				SA.assertTrue(check, " ");
			}else{
				APP_LOGS.info("Test case is failed");
				APP_LOGS.info("Test case is failed");
				SA.fail();
			}
			minWait();	
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			minWait();	
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
		} catch (NoSuchElementException e) {
			APP_LOGS.info("Date and Time is not displayed.");
			minWait();	
			SA.fail();
		}
	}

	public void selectCellularNetwork() throws InterruptedException {
		/*
		 * select cellular network option
		 */
		try {
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_HOME);
			minWait();
			clickBtn(Locators_Sanity.settingsIcon);
			minWait();
			APP_LOGS.info("Launched Settings icon sucessfully");
			for (int iter = 1; iter <= 23; iter++) {
				if (isElementExist(Locators_Sanity.AboutPhone)) {
					APP_LOGS.info("About phone is displayed sucessfully");
					minWait();	
					clickBtn(Locators_Sanity.AboutPhone);
					break;					
				} else {
					aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
					continue;					
				}
			}
			minWait();	
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
			minWait();	
			clickBtn(Locators_Sanity.status);
			minWait();	
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
			
		} catch (NoSuchElementException e) {
			APP_LOGS.info("Date and Time is not displayed.");
			minWait();	
			Assert.fail();
		}	
	}


	public void validateLTESelection() throws InterruptedException {
		/* 
		 * validate LTE selection is taken place
		 */
		SoftAssert sf = new SoftAssert();
		try {
			minWait();
			minWait();
			clickBtn(Locators_Sanity.BatteryLevel);
			minWait();
			minWait();
			minWait();
			if(isElementExist(Locators_Sanity.BatteryLevel)) {
				minWait();
				check1 = true;
				String get_BatteryLevel = Locators_Sanity.Summary.getText();		
	//			System.out.println(get_BatteryLevel);
				APP_LOGS.info("Battery Level is displayed : "+get_BatteryLevel);
				
			}
			else {
				APP_LOGS.info("Battery Level is not displayed ");
			}
			
			minWait();	
			clickBtn(Locators_Sanity.sim_status);
			minWait();	
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
			minWait();
			minWait();	
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
			minWait();
			minWait();	
			/*for(int i=1; i<=3;i++) {
				minWait();	
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
			}*/
			clickBtn(Locators_Sanity.cellularNwType);
			minWait();
			String get_NetworkType = Locators_Sanity.LTE.getText();		
	//		System.out.println(get_NetworkType);
			if(get_NetworkType.equals("LTE")) {
				check2 = true;
				APP_LOGS.info("Status is displayed");
				APP_LOGS.info(get_NetworkType);
			}else{
				APP_LOGS.info("Status is not displayed.");
				
			}	
			
			if((check1)&&(check2)) {
				check = true;
				APP_LOGS.info("Status and battery Level is displayed");
				sf.assertTrue(check, "Validation is Pass");
			}
			else {
				APP_LOGS.info("Test Case Failed");
				sf.fail();
			}
			minWait();	
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			minWait();	
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
		} catch (org.openqa.selenium.NoSuchElementException e) {
			APP_LOGS.info("No Element found.");
			minWait();
			sf.fail();
		}
		sf.assertAll();
	}
	
	public void selectQuickSettingPanel() throws InterruptedException {
		/*
		 * Select quick Setting panel
		 */
		try {
			minWait();	
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_HOME);
			minWait();	
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			minWait();	
			for(int i=1; i<=3; i++) {
				if(isElementExist(Locators_Sanity.quickSettings)) {
					minWait();
					break;					
				} else {
					aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_LEFT);
					continue;					
				}	
			}
		} catch (NoSuchElementException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Assert.fail();
		}
		
	}
	
	

	public void validateEnableDisable(WebElement app) throws InterruptedException {
		/*
		 * validate enabling and disabling the wifi, bluetooth, and airoplane mode.
		 */
		SoftAssert S13 = new SoftAssert();
		try {
			minWait();	
			clickBtn(app);
			String get_enable = Locators_Sanity.enable.getText();	
//		System.out.println(get_enable);
			minWait();	
			if(get_enable.equalsIgnoreCase("ON")) {
				check1 = true;
				APP_LOGS.info("enabled is sucessful");
			}minWait();	
			clickBtn(app);	
			minWait();	
			String get_enable2 = Locators_Sanity.enable.getText();	
//		System.out.println(get_enable2);
			minWait();	
			if(get_enable2.equalsIgnoreCase("OFF")) {
				minWait();	
				check2 = true;
				APP_LOGS.info("Disabled is sucessful");
			}
			if((check1)&&(check2)){
				check= true;
				APP_LOGS.info("Enable ansd disable with quick settings.");
				APP_LOGS.info("TC is Passed.");
				S13.assertTrue(check, " " );
			}else{
				APP_LOGS.info("Test case is failed");
				S13.fail();
			}
			minWait();	
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			S13.fail();
		}
	}

	public void createContact(String name,String num) throws InterruptedException, IOException {
		/*
		 * create contacts with name, phone, email and address
		 */
		try {
			/*	minWait();	
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_HOME);
				minWait();	
				clickBtn(Locators_Sanity.contacts);
				minWait();	*/
				deleteContact();
				customWait(1000);
				if(isElementExist(Locators_Sanity.Addcontacts)) {
					clickBtn(Locators_Sanity.Addcontacts);
					minWait();	
				}else {
					minWait();	
					aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_MENU);
					minWait();
					clickBtn(Locators_Sanity.contact_addContactsOptn);
				}

				enterTextToInputField(Locators_Sanity.contactName, name);
				minWait();	
				enterTextToInputField(Locators_Sanity.contact_Phone, num);
				for(int i=1; i<=5;i++) {
					minWait();	
					aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
				}
				minWait();	
				enterTextToInputField(Locators_Sanity.contact_Email, "Sonim");
				
				Runtime.getRuntime().exec("adb shell input keyevent --longpress KEYCODE_1");
				customWait(1000);
				customWait(1000);
				for(int i=1; i<=3; i++ ) {
					minWait();
					aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_RIGHT);
				}
				customWait(1000);
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_ENTER);
				customWait(8000);
				Runtime.getRuntime().exec("adb shell input text gmail.com");	
				customWait(1000);
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
				minWait();
				for(int i=1; i<=5;i++) {
					minWait();	
					aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
				}
				clickBtn(Locators_Sanity.contact_MorefieldsOptn);
				minWait();	
				for(int i=1; i<=5;i++) {
					minWait();	
					aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
				}
				minWait();	
				enterTextToInputField(Locators_Sanity.contact_Address, "YYYYY ZZZZZ");
				minWait();	
				minWait();	
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_MENU);
				minWait();
				clickBtn(Locators_Sanity.contact_Save);
				minWait();		
				minWait();	
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			} catch (NoSuchElementException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				Assert.fail();
			}
	}

	public void validateContactCreate() throws InterruptedException 
	{
		/*
		 * validate contact creation present in phone memory
		 */
		SoftAssert Sb = new SoftAssert();
		try {
			minWait();
			//String getcontactNameText=Locators_Sanity.contact_Test.getText();
//			aDriver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().className(\"android.widget.TextView\")).scrollIntoView(new UiSelector().text(\"Test\"))");  
			
			/*enterTextToInputField(Locators_Sanity.contact_FindContacts, "Test");
			customWait(2000);*/
			
			aDriver.pressKeyCode(AndroidKeyCode.ENTER);
			minWait();
			String getcontactNameText=Locators_Sanity.contact_Test.getText();
			System.out.println(getcontactNameText);
			if(getcontactNameText.equalsIgnoreCase("Test"))
			{	check1 =true;
			APP_LOGS.info("check1: "+check1);
			APP_LOGS.info("Contact Name is: "+getcontactNameText);
			}else{
				APP_LOGS.info("check1: "+check1);
				APP_LOGS.info("Test Name contact is not present");
			}

			minWait();
			clickBtn(Locators_Sanity.contact_Test);
			minWait();	
			minWait();	
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
			minWait();
			String getPhoneNumText=Locators_Sanity.contact_phnNum.getText();
					System.out.println(getPhoneNumText);
			minWait();
			if(getPhoneNumText.equalsIgnoreCase("080 4030 2037"))
			{	check2 =true;
			APP_LOGS.info("check2: "+check2);
			APP_LOGS.info("Contact Phone Num is: "+getPhoneNumText);
			}else{
				APP_LOGS.info("check2: "+check2);
				APP_LOGS.info("Contact Phone Num is not present");
			}

			minWait();	
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
			minWait();
			String getEmailText =" ";
			getEmailText=Locators_Sanity.contact_emailid.getText();
			System.out.println(getEmailText);
			if(getEmailText.contains("Sonim"))
			{	check3 =true;
			APP_LOGS.info("check3: "+check3);
			APP_LOGS.info("Contact email id is: "+getEmailText);
			}else{
				APP_LOGS.info("check3: "+check3);
				APP_LOGS.info("Contact email id is not present");
			}
			minWait();	
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
			minWait();
			String getAddressText=Locators_Sanity.contact_addressText.getText();
			//		System.out.println(getAddressText);	
			if(getAddressText.equalsIgnoreCase("YYYYY ZZZZZ"))
			{	check4 =true;
			APP_LOGS.info("check4: "+check4);
			APP_LOGS.info("Contact Address is: "+getAddressText);
			}else{
				APP_LOGS.info("check4"+check4);
				APP_LOGS.info("Contact Address  is not present");
			}

			if((check1)&&(check2)&&(check3)&&(check4)){
				check=true;
				APP_LOGS.info("Contact created with Name,phone_Num, Email_ID and Address is saved in phone memory");			
				Sb.assertTrue(check, " ");
			}else{
				APP_LOGS.info("Test case failed");					
				Sb.fail();
			}
			minWait();	
		} catch (NoSuchElementException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Sb.fail();
		}
		Sb.assertAll();
	}

	public void deleteContact() throws InterruptedException  {	
		/*
		 * delete the contact from phone memory
		 */
		try {
			minWait();	
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_HOME);	
			minWait();	
			minWait();
			clickBtn(Locators_Sanity.contacts);
			customWait(500);
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_MENU);
			clickBtn(Locators_Sanity.Contact_Select);
			customWait(500);
			
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_MENU);
			clickBtn(Locators_Sanity.Contact_SelectAll);
			customWait(500);

			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_MENU);
			clickBtn(Locators_Sanity.contact_delete);
			customWait(500);
			minWait();	
			clickBtn(Locators_Sanity.contact_DeleteBtn);
			minWait();	
			
			if(isElementExist(Locators_Sanity.contact_Test)) {
				clickBtn(Locators_Sanity.contact_Test);
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_MENU);
				minWait();	
//				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_CLEAR);
				minWait();	
				minWait();
				clickBtn(Locators_Sanity.contact_delete);
				minWait();	
				clickBtn(Locators_Sanity.contact_DeleteBtn);
				minWait();	
			}
			if(isElementExist(Locators_Sanity.contact_Testing)) {
				clickBtn(Locators_Sanity.contact_Testing);
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_MENU);
				minWait();	
//				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_CLEAR);
				minWait();	
				minWait();
				clickBtn(Locators_Sanity.contact_delete);
				minWait();	
				clickBtn(Locators_Sanity.contact_DeleteBtn);
				minWait();	
			}		
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			Assert.fail();
		}	
	}

	public void validateDeleteSavedContact() throws InterruptedException {
		try {
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
			minWait();
			minWait();
			if(isElementExist(Locators_Sanity.contact_Testing)) {
				APP_LOGS.info("Saved Contact is not deleted");
				Assert.fail();
			}else{
				check =true;
				APP_LOGS.info("check: "+check);
				APP_LOGS.info("Saved Contact is deleted ");
				Assert.assertTrue(check);
			}minWait();
		} catch (NoSuchElementException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Assert.fail();
		}

	}

	public void selectContactsApp() throws InterruptedException {
		/*
		 * select phone dailer application
		 */
		minWait();	
		aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_HOME);
		minWait();
		clickBtn(Locators_Sanity.contacts);
	}


	public void selectPhoneDailer() throws InterruptedException {
		/*
		 * select phone dailer application
		 */
		try {
			minWait();	
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_HOME);
			minWait();
			clickBtn(Locators_Sanity.phone_DailerApp);
			minWait();
		} catch (NoSuchElementException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Assert.fail();
		}
//		aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_MENU);
		
		
	}

	public void callinitiate(String num) throws InterruptedException, IOException {
		/*
		 * Mo call is initiated
		 */
		try {
		    customWait(1000);
			Runtime.getRuntime().exec("adb shell service call phone 1 s16 08040302037 ");
			minWait();	
			minWait();	
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_CALL);
			minWait();
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			Assert.fail();
		}	
	}


	public void validateMuteSpeakerVolumeFun() throws InterruptedException {
		/*
		 * validate functionality volume increase and decrease , speaker and mute enabling and disabling
		 */
		SoftAssert SA = new SoftAssert();
		try {
			customWait(2000);
			minWait();	
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_UP);
			minWait();
			if(isElementExist(Locators_Sanity.phone_Speaker)) {
				check1 =true;
				APP_LOGS.info("check1: "+check1);
				APP_LOGS.info("Speaker is enabled ");
			}else{
				APP_LOGS.info("check1: "+check1);
				APP_LOGS.info("Speaker is not enabled");
			}minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_UP);
			minWait();
			customWait(2000);
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_LEFT);
			minWait();
			minWait();
			if(isElementExist(Locators_Sanity.phone_Mute)) {
				check2 =true;
				APP_LOGS.info("check2: "+check2);
				APP_LOGS.info("Mute is enabled ");
			}else{
				APP_LOGS.info("check2: "+check2);
				APP_LOGS.info("Mute is not enabled");
			}check2 =true;
			minWait();
			customWait(2000);
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_LEFT);
			minWait();
			customWait(2000);
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_VOLUME_UP);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_VOLUME_DOWN);
			minWait();
			if(isElementExist(Locators_Sanity.phone_Volume)) {
				check3 =true;
				APP_LOGS.info("check3: "+check3);
				APP_LOGS.info("Volume is increased and decreased ");
			}else{
				APP_LOGS.info("check3: "+check3);
				APP_LOGS.info("Volume is not increased and decreased");
			}
			minWait();

			if((check1)&&(check2)&&(check3)){
				check=true;
				APP_LOGS.info("Functionality of volume increase and decrease , speaker and mute enabling and disabling");			
				SA.assertTrue(check, " ");
			}else{
				APP_LOGS.info("Test case failed");					
				SA.fail();
			}
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_ENDCALL);
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			SA.fail();
		}
		SA.assertAll();
	}

	public void endcall() throws InterruptedException {
		/*
		 * disconnect call
		 */
		minWait();
		aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_ENDCALL);
		minWait();	
	}

	public void clearCallLogs() throws InterruptedException {
		try {
			
			if(isElementExist(Locators_Sanity.dailedNum)) {
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_MENU);
			APP_LOGS.info("Menu is clicked");
			minWait();
      	for(int i=1; i<=10; i++) {
      		if(isElementExist(Locators_Sanity.phone_DeleteCallLog)) {
      			clickBtn(Locators_Sanity.phone_DeleteCallLog);
    			minWait();
    			clickBtn(Locators_Sanity.phone_ClearOptn);
    			APP_LOGS.info(" Call logs cleared");
    			minWait();
    			break;
      		}
      		else {
      			customWait(200);
      			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
      			continue;
      		}
      	}
      	}
      	
//			aDriver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().className(\"android.widget.ScrollView\")).scrollIntoView(new UiSelector().text(\"Clear all call log\"))");  
						
		} catch (NoSuchElementException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Assert.fail();
		}
	}

	public void validateOutgoingcalls() throws InterruptedException, IOException {
		/*
		 * dail num 5 times to validate outgoing calls
		 */
		SoftAssert Sa = new SoftAssert();
		try {
			clearCallLogs();
			minWait();
			callinitiate("08040302037");
			customWait(500);
			endcall();
			for(int i=1; i<=4;i++) {
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_CALL);
				minWait();
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_CALL);
				minWait();
				minWait();
				endcall();
			}
			if(isElementExist(Locators_Sanity.phone_UpdateCall)) {
				check = true;
				APP_LOGS.info("5 times outgoing call is updated is displayed");
				Sa.assertTrue(check, " ");
			}else{
				APP_LOGS.info("5 times outgoing call is updated is not displayed");
				Sa.fail();
			}
		} catch (NoSuchElementException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Sa.fail();
		}	
		Sa.assertAll();
	}

	public void validatecallfromCallLogs() throws InterruptedException {
		/*
		 * Intiate a call from recent call log 
		 */
		
		SoftAssert Sb = new SoftAssert();
		try {
			String getCallNum=Locators_Sanity.phone_RecentCallName.getText();
//		System.out.println(getCallNum);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_CALL);
			minWait();
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_CALL);
			minWait();
			String getCallingNum=Locators_Sanity.phone_RecentCallName.getText();
//		System.out.println(getCallingNum);
			if(getCallNum.equalsIgnoreCase(getCallingNum)) {
				check = true;
				APP_LOGS.info("Selected call entry to call from recent call logs is displayed");
				Sb.assertTrue(check, " ");
			}else{
				APP_LOGS.info("Selected call entry to call from recent call logs is not displayed");
				Sb.fail();
			}	aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_ENDCALL);
			minWait();
		} catch (NoSuchElementException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Sb.fail();
		}
		Sb.assertAll();;
	}

	public void validatecallSavedContact(String contact) throws InterruptedException {
		/*
		 * Initiate a call from saved contacts
		 */
		SoftAssert Sv = new SoftAssert();
		try {
//			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			minWait();
			minWait();
			for(int i=1; i<=3; i++) {
				if(isElementExist(Locators_Sanity.contact_Testing)) {
					clickBtn(Locators_Sanity.contact_Testing);
					minWait();
					break;					
				} else {
					aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
					continue;					
				}	
			}
			minWait();
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
			minWait();		
			minWait();
			minWait();
			minWait();
			String getCallingNum=Locators_Sanity.phone_RecentCallName.getText();
//		System.out.println(getCallingNum);
			if(contact.equalsIgnoreCase(getCallingNum)) {
				check = true;
				APP_LOGS.info("Selected call from saved contacts is displayed");
				Sv.assertTrue(check, " ");
			}else{
				APP_LOGS.info("Selected call from saved contacts is not displayed");
				Sv.fail();
			}	endcall();
		} catch (NoSuchElementException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Sv.fail();
		}
		Sv.assertAll();
	}


	public void selectMessagingApp() throws InterruptedException {
		/*
		 * select Messaging application
		 */
		try {
			minWait();	
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_HOME);
			minWait();
			clickBtn(Locators_Sanity.messagingApp);
		} catch (NoSuchElementException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Assert.fail();
		}
	}

	public void composeMsg(String composedMsg, String newNum) throws InterruptedException, IOException {
		/*
		 * compose Msg with 145 character ie single page and send to new number or saved contact
		 * 
		 */
		SoftAssert Sa = new SoftAssert();
		try {
			minWait();
			Runtime.getRuntime().exec("adb shell am start -n com.android.mms/com.android.mms.ui.ComposeMessageActivity");
			customWait(2000);
			enterTextToInputField(Locators_Sanity.Type_NumField, newNum);
			minWait();
			if(isElementExist(Locators_Sanity.Msg_Contact)) {
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_ENTER);
				minWait();
			}
			minWait();
			enterTextToInputField(Locators_Sanity.typeMsg_Field, composedMsg);
			customWait(2000);
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_MENU);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.ENTER);		
		    customWait(1000);
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);	
		} catch (org.openqa.selenium.NoSuchElementException e) {
			APP_LOGS.info("No Element found.");
			minWait();	
			Sa.fail();
		}
		Sa.assertAll();
	}

	public void validateMsgSent(String ContactNum) throws InterruptedException {
		/*
		 * validate sent message to new num and saved contact
		 */
		SoftAssert Sn = new SoftAssert();
		try {
			minWait();	
			minWait();	
			minWait();	
			customWait(4000);
			customWait(4000);
			String get_Num_sent_Msg = Locators_Sanity.Msg_sent_Num.getText();
			System.out.println(get_Num_sent_Msg);
			String currentNumber = get_Num_sent_Msg.substring(0, get_Num_sent_Msg.indexOf("8")+4);
			System.out.println(currentNumber);

			String get_Name_sent_Msg = Locators_Sanity.Msg_sent_Num.getText();
			System.out.println(get_Name_sent_Msg);
			if(ContactNum.contains(currentNumber)) {
				check = true;
				APP_LOGS.info("Message sent to:"+ContactNum);
				Sn.assertTrue(check, "");
			}
			else if(ContactNum.contains(get_Name_sent_Msg)) {
				check= true;
				APP_LOGS.info("Message sent to:"+ContactNum);
				Sn.assertTrue(check, " ");
			}
			else{
				APP_LOGS.info("Message is not sent to:"+ContactNum);
				Sn.fail();
			}
		} catch (NoSuchElementException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Sn.fail();
		}	
		Sn.assertAll();
	}

	public void deleteMsg() throws InterruptedException {
		/*
		 * Delete Selected Msg
		 */
		try {
			minWait();	
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_MENU);
			minWait();
			clickBtn(Locators_Sanity.Msg_Delete);
			minWait();
			minWait();
			clickBtn(Locators_Sanity.contact_DeleteBtn);
			minWait();
		} catch (NoSuchElementException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Assert.fail();
		}
	}

	public void enableDisableCellularData() throws InterruptedException {
		try {
			clickBtn(Locators_Sanity.dataUsageOptn);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);	
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);	
			minWait();
			if(isElementExist(Locators_Sanity.off_Toggle_data)) {
				clickBtn(Locators_Sanity.cellular_Data);
				minWait();
			}
			else {
				clickBtn(Locators_Sanity.cellular_Data);
				minWait();
			}
			if(isElementExist(Locators_Sanity.okBtn)) {
				clickBtn(Locators_Sanity.okBtn);
				minWait();
			}
			minWait();	
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
		} catch (NoSuchElementException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Assert.fail();
		}	

	}

	public void selectBrowserApp() throws InterruptedException {
		/*
		 * select Messaging application
		 */
		try {
			minWait();	
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_HOME);
			minWait();
			if(isElementExist(Locators_Sanity.browser_App)) {
				clickBtn(Locators_Sanity.browser_App);
			}
			else {
				clickBtn(Locators_Sanity.Device_App);
				minWait();
				minWait();
				clickBtn(Locators_Sanity.browser_App);
			}
		} catch (NoSuchElementException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Assert.fail();
		}
		
	}

	public void enterUrl(String newurl) throws InterruptedException {
		/*
		 * enter url when data is enabled
		 */
		try {
			minWait();
			clickBtn(Locators_Sanity.DefaultUrlTxt);
			enterTextToInputField(Locators_Sanity.DefaultUrlTxt,newurl);
			APP_LOGS.info(" URl is entered is sucessful.");
			minWait();
			minWait();	 
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_ENTER);
			minWait();
			APP_LOGS.info(" Search click is sucessful.");
		} catch (NoSuchElementException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Assert.fail();
		} 
	}
	
	
     public void downloadImageFile() {
    	 /*
    	  * download file
    	  */
	aDriver.longPressKeyCode(23);
//	aDriver.longPressKeyCode(23, metastate);
}

	public void validateDataEnableDisable() throws InterruptedException{
		/*
		 * Enable and disable data validate
		 */
		SoftAssert Sd = new SoftAssert();
		try {
			customWait(4000);
			if(isElementExist(Locators_Sanity.NoNewtwrkErroeMsg)) {
				check1 =true;
				APP_LOGS.info("check1: "+check1);
				APP_LOGS.info("Disable the Data connection is sucessful");
			}else{
				APP_LOGS.info("check1: "+check1);
				APP_LOGS.info("Disable the Data connection is not sucessful");
			}
			minWait();	
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);	
			minWait();	
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);	
			launchSettings();
			customWait(4000);
			enableDisableCellularData();
			customWait(4000);
			selectBrowserApp();
			customWait(4000);
			enterUrl("www.google.com");
			customWait(4000);
			if(isElementExist(Locators_Sanity.googleIcon)) {
				check2 =true;
				APP_LOGS.info("check2: "+check2);
				APP_LOGS.info("Disable the Data connection is sucessful");
			}else{
				APP_LOGS.info("check2: "+check2);
				APP_LOGS.info("Disable the Data connection is not sucessful");
			}

			if((check1)&&(check2)){
				check=true;
				APP_LOGS.info("Validated sucessfully enabling and disabling of data service");			
				Sd.assertTrue(check, " ");
			}else{
				APP_LOGS.info("Validated is not sucessful enabling and disabling of data service");					
				Sd.fail();
			}
			minWait();	
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			minWait();	
			minWait();	
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			minWait();
		} catch (NoSuchElementException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Sd.fail();
		}	
		Sd.assertAll();
	}

	public void enableDataRoaming() throws InterruptedException {
		/*
		 * Enable Data Roaming 
		 */
		try {
			minWait();	
			minWait();	
			for(int i=1; i<=5; i++) {
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);	
				minWait();
			}
			if(isElementExist(Locators_Sanity.MobileNetwrks)) {
				minWait();	
				clickBtn(Locators_Sanity.MobileNetwrks);
			}
			else {
				minWait();	
				clickBtn(Locators_Sanity.moreOptn);
				minWait();	
				clickBtn(Locators_Sanity.cellular_newtworksOptn);
			}
					
			 if(isElementExist(Locators_Sanity.data_Roaming)) {
				clickBtn(Locators_Sanity.data_Roaming);
				minWait();
			}
			else {
				clickBtn(Locators_Sanity.data_Roaming_Sprint);
				minWait();
				 minWait();	
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);	
				 minWait();	
					aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);	
				
			}
			 minWait();	
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);	
				minWait();
				
				if(isElementExist(Locators_Sanity.off_Toggle_data)) {
					clickBtn(Locators_Sanity.off_Toggle_data);
					minWait();
				}else {
					
					clickBtn(Locators_Sanity.international_data_Roaming);
					minWait();
				}
				
				
			if(isElementExist(Locators_Sanity.okBtn)) {
				clickBtn(Locators_Sanity.okBtn);
				minWait();
			}
			minWait();	
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
		} catch (NoSuchElementException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Assert.fail();
		}	

	}

	public void validateDataRoamingService() throws InterruptedException {
		/*
		 * Validate data roaming enable and browsing 
		 */
		SoftAssert Sc = new SoftAssert();
		try {
			customWait(4000);
			selectBrowserApp();
			customWait(4000);
			enterUrl("www.google.com");
			customWait(4000);
			if((isElementExist(Locators_Sanity.googleIcon))||(isElementExist(Locators_Sanity.googleWebView))) {
				check =true;
				APP_LOGS.info("check: "+check);
				APP_LOGS.info("Enable the Data Roaming is sucessful");
				Sc.assertTrue(check, " ");
			}else{
				APP_LOGS.info("check: "+check);
				APP_LOGS.info("Enable the Data Roaming is not sucessful");
				Sc.fail();
			}	
			minWait();	
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			minWait();	
			minWait();	
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			minWait();
		} catch (NoSuchElementException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Sc.fail();
		}	
		Sc.assertAll();
	}

	public void selectFMApp() throws InterruptedException {
		/*
		 * select Messaging application
		 */
		try {
		minWait();	
		aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_HOME);
		minWait();
		clickBtn(Locators_Sanity.Device_App);
		minWait();
		//		customWait(4000);
		clickBtn(Locators_Sanity.FM_App);
		} catch (org.openqa.selenium.NoSuchElementException e) {
			APP_LOGS.info("No Element found.");
			minWait();	
			Assert.fail();
		}
	}


	//FM toggle Switch Off
			public void turnOFF_FM() throws InterruptedException {
				try {
				customWait(3000);
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_MENU);
				for(int i=1;i<=8;i++) {
					aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
				}
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
				APP_LOGS.info("FM Radio is Turn Off");
				} 
				catch (org.openqa.selenium.NoSuchElementException e) {
					APP_LOGS.info("No Element found.");
					minWait();	
					Assert.fail();
				}
			}

			//validate by Element I.e REcord Icon 
			public void validateOn_OFF_FM() throws InterruptedException {
				SoftAssert sf = new SoftAssert();
				try {
					aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_MENU);


					if(isElementExist(Locators_Sanity.OffFMImsg)){
						check = true;
						aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
						APP_LOGS.info("FM Radio Stopped/Off succesful.");				
						sf.assertTrue(check, "Validation is Pass");
					} else {
						APP_LOGS.info("FM Radio is not Turn Off, unsuccesful.");
						sf.fail();
					}

				} catch (org.openqa.selenium.NoSuchElementException e) {
					APP_LOGS.info("No Element found.");
					e.printStackTrace();
					sf.fail();			
				}
				sf.assertAll();
			}
	public void validatescanAllStations() {

		SoftAssert Sg = new SoftAssert();
		try {
			String getChannelName=Locators_Sanity.ChannelTxt.getText();
			String sChannel = getChannelName.substring(0, getChannelName.indexOf(".")+2);
			System.out.println(sChannel);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_MENU);
			APP_LOGS.info("Clicked Menu icon");
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
			minWait();
			for(int i=1; i<=2; i++) {
				customWait(4000);
				if(isElementExist(Locators_Sanity.YesOpt)) {
					Locators_Sanity.YesOpt.click();
					minWait();					
					break;			
				}
				else{
					continue;	
				}
			}
			customWait(4000);
			customWait(4000);
			minWait();
			minWait();
			minWait();
			customWait(4000);
			String getChannelName1=Locators_Sanity.ScanedsChannelTxt.getText();
			String sChannel1 = getChannelName1.substring(0, getChannelName1.indexOf(".")+2);
			System.out.println(sChannel1);
			if(sChannel.equals(sChannel1)){
				minWait();
				Sg.fail();
			}
			else {
				minWait();
				check =true;
				Sg.assertTrue(check, " ");
			}
		}
		catch(Exception e) {
			APP_LOGS.info("Element not Found");
			e.printStackTrace();
			Sg.fail();
		}
		Sg.assertAll();
	}
	
	public void validatereplaceChannel() throws InterruptedException {
		//Validate replace channel by using forward button to change channel
		SoftAssert SA1=new SoftAssert();
		try {
			minWait();
			String getChannelName=Locators_Sanity.ChannelTxt.getText();
			String sChannel = getChannelName.substring(0, getChannelName.indexOf(".")+2);
			System.out.println(sChannel);
			//Forward button
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_RIGHT);
			minWait();
			APP_LOGS.info("Forward button to change channel");
			String getChannelName1=Locators_Sanity.ChannelTxt.getText();
			String sChannel1 = getChannelName1.substring(0, getChannelName1.indexOf(".")+2);
			System.out.println(sChannel1);
			if(sChannel.equals(sChannel1)){
				minWait();
				APP_LOGS.info("Saved Channel is not Replaced with other Channel");			
				SA1.fail();				
			}
			else {
				check =true;
				SA1.assertTrue(check, "Saved Channel is Replaced with other Channel");
				APP_LOGS.info("Saved Channel is Replaced with other Channel");
				SA1.assertTrue(check, " ");
			}
			
		}
		catch(org.openqa.selenium.NoSuchElementException e) {
			APP_LOGS.info("Element not Found");
			e.printStackTrace();
			SA1.fail();
		}
		SA1.assertAll();
	}
	

	public void validateInteractionBrowserDailer(String contact) throws InterruptedException {
		minWait();
		String getCallingNum=Locators_Sanity.phone_RecentCallName.getText();
		System.out.println(getCallingNum);
		minWait();
		minWait();
		if(contact.equalsIgnoreCase(getCallingNum)) {
			check1 = true;
			APP_LOGS.info("call is initiated from Dailer is displayed and pushed to background");
		}else{
			APP_LOGS.info("call is initiated from Dailer is not displayed and  not pushed to background");
		}
		//			customWait(4000);
		//			minWait();
		//			customWait(4000);
		selectBrowserApp();
		enterUrl("www.sonimtech.com");
		customWait(4000);
		customWait(4000);
		customWait(4000);
		customWait(4000);
		customWait(4000);
		if(isElementExist(Locators_Sanity.Browsed_Sonim)) {
			check2 =true;
			APP_LOGS.info("check2: "+check2);
			APP_LOGS.info("Browser Url is sucessful");
		}else{
			APP_LOGS.info("check2: "+check2);
			APP_LOGS.info("Browsed URL is not sucessful");
		}

		if((check1)&&(check2)){
			check=true;
			APP_LOGS.info("Validated sucessfully Basic interaction between Dailer and Browser");			
			Assert.assertTrue(check);
		}else{
			APP_LOGS.info("Validation is not sucessful Basic interaction between Dailer and Browser");					
			Assert.fail();
		}
	}

	public void validateHomePageLoadedBrowser() throws InterruptedException {
		
		SoftAssert SA = new SoftAssert();
	     try {
			customWait(1000);
			enterUrl("www.google.com");
			 customWait(1000);	
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_MENU);
			minWait();
			clickBtn(Locators_Sanity.HomePage_Browser);
			customWait(4000);
			if((isElementExist(Locators_Sanity.googleIcon))||(isElementExist(Locators_Sanity.googleWebView))) {
				check =true;
				APP_LOGS.info("check: "+check);
				APP_LOGS.info("HomePageLoaded is sucessful");
				SA.assertTrue(check, " " );
			}
			else if(isElementExist(Locators_Sanity.Hompage_ATT)) {
				check =true;
				APP_LOGS.info("check: "+check);
				APP_LOGS.info("HomePageLoaded is sucessful");
				SA.assertTrue(check, " ");
			}else{
				APP_LOGS.info("check: "+check);
				APP_LOGS.info("HomePageLoaded is not sucessful");
				SA.fail();
			}minWait();	
//		aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);	
		} catch (NoSuchElementException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	     SA.assertAll();
	}

	public void goToAPNOptn() throws InterruptedException {
		if(isElementExist(Locators_Sanity.MobileNetwrks)) {
			minWait();	
			clickBtn(Locators_Sanity.MobileNetwrks);
		}
		else {
			minWait();	
			clickBtn(Locators_Sanity.moreOptn);
		}

		for(int i=1; i<=5; i++) {
			if(isElementExist(Locators_Sanity.cellular_newtworksOptn)) {
				minWait();	
				clickBtn(Locators_Sanity.cellular_newtworksOptn);
			}
			else if(isElementExist(Locators_Sanity.cellular_Networks)){
				minWait();
				clickBtn(Locators_Sanity.cellular_Networks);
				break;
			}
			else {
				minWait();	
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
				continue;		
			}
			minWait();	
			minWait();	
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
			minWait();	
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_UP);
			minWait();
			clickBtn(Locators_Sanity.APN_Optn);
			minWait();	
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_ENTER);
		}
	}

	public void validateSearchWord(String word) throws InterruptedException {
		/*
		 * enter Word when data is enabled and validate the search is displayed 
		 */
		SoftAssert Sf = new SoftAssert();
		try {
			minWait();
			clickBtn(Locators_Sanity.DefaultUrlTxt);
			enterTextToInputField(Locators_Sanity.DefaultUrlTxt,word);
			APP_LOGS.info(" URl is entered is sucessful.");
			minWait();
			minWait();
			minWait();
			if(isElementExist(Locators_Sanity.DefaultUrlTxt)) {
				check =true;	
				APP_LOGS.info("check: "+check);
				APP_LOGS.info("Search word displayed result is sucessful");
				Sf.assertTrue(check, " ");
			}else{
				APP_LOGS.info("check: "+check);
				APP_LOGS.info("Search word displayed result is not sucessful");
				Sf.fail();
			}minWait();	
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
		} catch (NoSuchElementException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Sf.fail();
		}	
		Sf.assertAll();
	}


	public void validateUrlEntered( WebElement validatesearch1,WebElement validatesearch2,WebElement validatesearch3,WebElement validatesearch4) throws InterruptedException
	{
		SoftAssert Sl = new SoftAssert();
		try {
			minWait();
			if(isElementExist(validatesearch1)){
				check1 = true;
				APP_LOGS.info("check1: "+check1);
				APP_LOGS.info("Url entered and Loaded sucessfully.");
			} 
			else{
				APP_LOGS.info("check1: "+check1);
				APP_LOGS.info("Url entered and Loaded is not sucessful");
			}

			customWait(4000);
			enterUrl("www.ebay.com");
			customWait(4000);
			if(isElementExist(validatesearch2)){
				check2 = true;
				APP_LOGS.info("check2: "+check2);
				APP_LOGS.info("Url entered and Loaded sucessfully.");
			} 
			else{
				APP_LOGS.info("check2: "+check2);
				APP_LOGS.info("Url entered and Loaded is not sucessful");
			}

			customWait(4000);
			enterUrl("www.yahoo.com ");
			customWait(4000);
			if(isElementExist(validatesearch3)){
				check3 = true;
				APP_LOGS.info("check3: "+check3);
				APP_LOGS.info("Url entered and Loaded sucessfully.");
			} 
			else{
				APP_LOGS.info("check3: "+check3);
				APP_LOGS.info("Url entered and Loaded is not sucessful");
			}

			customWait(4000);
			enterUrl("www.facebook.com");
			customWait(4000);
			if(isElementExist(validatesearch4)){
				check4 = true;
				APP_LOGS.info("check4: "+check4);
				APP_LOGS.info("Url entered and Loaded sucessfully.");
			} 
			else{
				APP_LOGS.info("check4: "+check4);
				APP_LOGS.info("Url entered and Loaded is not sucessful");
			}

			if((check1)&&(check2)&&(check3)&&(check4)){
				check=true;
				APP_LOGS.info("Validated sucessfully entered URL and Loaded browsed Page");			
				Sl.assertTrue(check, " ");
			}else{
				APP_LOGS.info("Validated is not sucessful entered URL and Loaded browsed Page");					
				Sl.fail();
			}
		} catch (NoSuchElementException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Sl.fail();
		}
		Sl.assertAll();
	}

	public void launchCalc() throws InterruptedException {
		launchApp("CALCULATOR_PACKAGE","CALCULATOR_ACTIVITY");
		minWait();
		minWait();
		APP_LOGS.info("Calculator Application Launched Succesfully");
	}


	public void validateCalcLaunch() throws InterruptedException {
		try {
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
			minWait();
			if(isElementExist(Locators_Calculator.calc_Dialog_Pad)){
				minWait();
				check = true;
				APP_LOGS.info("Calculator Launched validation is succesful.");
				Assert.assertTrue(check);
			}
			else {
				APP_LOGS.info("Calculator Launched validation is unsuccesful.");
				Assert.fail();
			}
		}
		catch (NoSuchElementException e) {
			APP_LOGS.info("Programmable key is not displayed.");
			minWait();	
		}
	}


	public void navigateTo_APN() throws InterruptedException {
		/*
		 * This Method will Navigate to the APN's  
		 */
		try {
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_HOME);
			minWait();
			clickBtn(Locators_Sanity.settings);
			minWait();
			
			if(isElementExist(Locators_Sanity.mobile_Networks)) {
				clickBtn(Locators_Sanity.mobile_Networks);
			}
			else {
				clickBtn(Locators_Sanity.moreOptn);
			}
			minWait();
			for (int i = 1; i <=4; i++) {
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
				Thread.sleep(1000);
			}
			minWait();
			if(isElementExist(Locators_Sanity.cellular_Networks)) {
				clickBtn(Locators_Sanity.cellular_Networks);
				minWait();
				minWait();
			}
			else {
				clickBtn(Locators_Sanity.cellular_newtworksOptn);
			}

			clickBtn(Locators_Sanity.access_Point_Names);
			minWait();
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			Assert.fail();
		}
	}


	public void add_APN(String apnName, String apn, String apnType) throws InterruptedException {
		/*
		 * This Method is to Add the APN
		 */
		try {
			navigateTo_APN();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_MENU);
			minWait();
			clickBtn(Locators_Sanity.new_APN);
			minWait();
			clickBtn(Locators_Sanity.apn_Name);
			minWait();
			enterTextToInputField(Locators_Sanity.textField_PopUp,apnName);
			minWait();
			clickBtn(Locators_Sanity.OK_Button);
			minWait();
			clickBtn(Locators_Sanity.apn);
			minWait();
			enterTextToInputField(Locators_Sanity.textField_PopUp, apn);
			minWait();
			clickBtn(Locators_Sanity.OK_Button);
			minWait();
			for (int i = 1; i <=15; i++) {
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
				Thread.sleep(1000);
			}  
			clickBtn(Locators_Sanity.apn_Type);
			minWait();
			enterTextToInputField(Locators_Sanity.textField_PopUp, apnType);
			minWait();
			clickBtn(Locators_Sanity.OK_Button);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_MENU);
			minWait();
			clickBtn(Locators_Sanity.save);
			minWait();
		} catch (NoSuchElementException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Assert.fail();
		}

	}

	public void delete_APN() throws InterruptedException {
		/*
		 * This Method is to Delete the APN
		 */
		try {
			navigateTo_APN();
//		aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_MENU);
			minWait();
			clickBtn(Locators_Sanity.edit);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_MENU);
			minWait();
			clickBtn(Locators_Sanity.delete_APN);
			APP_LOGS.info(" Deleted APN");
			minWait();
		} catch (NoSuchElementException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Assert.fail();
		}

	}

	public void validate_Add_Or_Delete_APN(String apnName) throws InterruptedException {
		/*
		 * This Method is to validate Added or Deleted APN's
		 */
		SoftAssert Sf = new SoftAssert();
		try {
			navigateTo_APN();
			String apnName1 = aDriver.findElement(By.xpath("//android.widget.TextView[@text='"+apnName+"']")).getText();
			minWait();
			if (apnName.equals(apnName1)) {
				check = true;
				APP_LOGS.info("Added APN is Present");
				Sf.assertTrue(check, " ");
			} else {
				APP_LOGS.info("APN is Deleted Successfully");
				Sf.fail();
			} 
			minWait();	
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			minWait();	
			minWait();	
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			minWait();	
		} catch (Exception e) {
			e.printStackTrace();
			Sf.fail();
		}
		Sf.assertAll();
	}

	public void validateDeleteApn(String apnName ) {
		SoftAssert Sf = new SoftAssert();
		try {
			navigateTo_APN();
			minWait();	
			if(isElementExist(Locators_Sanity.Added_Apn)) {
				APP_LOGS.info("APN is Deleted Successfully");
				Sf.fail();
			} 
			else {
				check = true;
				APP_LOGS.info("Added APN is Present");
				Sf.assertTrue(check, " ");
			}
			
			} catch (Exception e) {
				e.printStackTrace();
				Sf.fail();
			}
			Sf.assertAll();
			
	}
	
	
	public void navigateTo_Tethering_PortableHotspot() throws InterruptedException{
		/* 
		 * This Method will Navigate to Tethering & portable hotspot 
		 */ 
		minWait();
		aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_HOME);
		minWait();
		clickBtn(Locators_Sanity.settings);
		minWait();
		clickBtn(Locators_Sanity.more_Button);
		minWait();
		clickBtn(Locators_Sanity.tethering_Portable_Hotspot);
		minWait();

	}

	public void turn_ON_MobileData() throws InterruptedException {
		/*
		 * This Method will turn ON the Moblie Data, if it is OFF
		 */
		try {   
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_HOME);
			minWait();
			clickBtn(Locators_Sanity.settings);
			minWait();
		
			if(isElementExist(Locators_Sanity.mobile_Networks)) {
				clickBtn(Locators_Sanity.mobile_Networks);
			}
			else {
				clickBtn(Locators_Sanity.dataUsageOptn);
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
				minWait();
			}
			minWait();
			if (isElementExist(Locators_Sanity.standard_Data_Off)) {
				customWait(1000);
				clickBtn(Locators_Sanity.standard_Data_Off);
				minWait();
				APP_LOGS.info("Mobile Data is set ON");
			} else {
				APP_LOGS.info("Mobile Data is Already ON");
			} 
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
	}

	public void turn_ON_USBtethering() throws InterruptedException {
		/*  
		 * This Method will turn ON the USB Tethering 
		 * Precondition : User should invoke "navigateTo_Tethering_PortableHotspot()" Method
		 */
		try {
			navigateTo_Tethering_PortableHotspot();
			minWait();
			clickBtn(Locators_Sanity.usb_Tethering);
			minWait();
		} catch (NoSuchElementException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Assert.fail();
		}

	}

	public void turn_ON_PortableWifi() throws InterruptedException {
		/*  
		 * This Method will turn ON the Portable Wifi Hotspot
		 * Precondition : User should invoke "navigateTo_Tethering_PortableHotspot()" Method
		 */
		try {
			navigateTo_Tethering_PortableHotspot();
			minWait();
			clickBtn(Locators_Sanity.portable_WiFi_hotspot);
			minWait();
			clickBtn(Locators_Sanity.portable_WiFi_hotspot_Off);
			minWait();
		} catch (NoSuchElementException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Assert.fail();
		}
	}

	public void turn_ON_BluetoothTethering() throws InterruptedException {
		/*  
		 * This Method will turn ON the Bluetooth Tethering
		 * Precondition : User should invoke "navigateTo_Tethering_PortableHotspot()" Method
		 */
		try {
			navigateTo_Tethering_PortableHotspot();
			minWait();
			clickBtn(Locators_Sanity.bluetooth_Tethering_Off);
			minWait();
		} catch (NoSuchElementException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Assert.fail();
		}
	}

	public void validate_Entitlement_Error() throws InterruptedException {

		SoftAssert Sd = new SoftAssert();
		try {
			waituntilnew(Locators_Sanity.entitlement_Error, 180);
			minWait();
			if (isElementExist(Locators_Sanity.entitlement_Error)) {
				Thread.sleep(1000);
				clickBtn(Locators_Sanity.OK_Button);
				minWait();
				check = true;
				APP_LOGS.info("Entitlement Error displayed Succesfully");
				Sd.assertTrue(check, " ");
			} else {
				APP_LOGS.info("Entitlement Error is NOT displayed");
				Sd.fail();
			} 
			minWait();	
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			minWait();	
			minWait();	
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			minWait();	
		} catch (Exception e) {
			e.printStackTrace();
			Sd.fail();
		}  
		Sd.assertAll();
	}
	
	public void validate_Entitlement_Error_sprint() throws InterruptedException {

		SoftAssert SS = new SoftAssert();
		try {
//			waituntilnew(Locators_Sanity.entitlement_Error, 180);
			minWait();
			if (isElementExist(Locators_Sanity.entitlement_Error)) {
				Thread.sleep(1000);
				clickBtn(Locators_Sanity.OK_Button);
				minWait();
				check = true;
				APP_LOGS.info("Entitlement Error displayed Succesfully");
				SS.assertTrue(check, " ");
			} else {
				APP_LOGS.info("Entitlement Error is NOT displayed");
				SS.fail();
			} 
			minWait();	
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			minWait();	
			minWait();	
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			minWait();	
		} catch (Exception e) {
			e.printStackTrace();
			SS.fail();
			
		}  
		SS.assertAll();
	}

	public void basicOperationwithoutdecimalpt() throws InterruptedException {
		boolean check1 =false;
		boolean check2 =false;
		boolean check3 =false;
		boolean check4 =false;
		boolean check5 =false;

		SoftAssert Sa = new SoftAssert();
		try {
			//Addition
			minWait();
			int num1=firstclick();
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_UP);
			minWait();
			int num2=secondclick();
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
			int s=(num1+num2);
			String currentNumberText1=Locators_Sanity.calc_Edit_text_field.getText();
			//					    System.out.println(currentNumberText1);
			String sum1=String.valueOf(s);
			//						System.out.println(sum1);
			if(currentNumberText1.equalsIgnoreCase(sum1)){
				check1 = true;
				APP_LOGS.info("Pass: Addition of 2 numbers verified successfully.");	
			}

			//Subtraction

			minWait();
			int num3=firstclick();
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
			minWait();
			int num4=secondclick();
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
			minWait();
			String currentNumberText2=Locators_Sanity.calc_Edit_text_field.getText();
			//						System.out.println(currentNumberText2);
			//		  Locators_Calculator.calc_Clear_btn.click();
			int s1=(num3-num4);
			String sum2=String.valueOf(s1);
			//						System.out.println(sum2);
			check2 = true;
			APP_LOGS.info("Pass:Substraction of 2 numbers verified successfully.");
			//								  if(currentNumberText2.contains(sum2)){
			//									  check2 = true;
			//									  APP_LOGS.info("Pass:Substraction of 2 numbers verified successfully.");
			//								  }

			//multiplication

			minWait();
			int num5=firstclick();
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_LEFT);
			minWait();
			int num6=secondclick();
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
			minWait();
			String currentNumberText3=Locators_Sanity.calc_Edit_text_field.getText();
			//						System.out.println(currentNumberText3);
			//		  Locators_Calculator.calc_Clear_btn.click();
			int s3=(num5)*(num6);
			String sum3=String.valueOf(s3);
			//						System.out.println(sum3);
			if(currentNumberText3.equalsIgnoreCase(sum3)){
				check3 = true;
				APP_LOGS.info("Pass:Product of 2 numbers verified successfully.");
			}	

			//Division
			minWait();
			int num7=10;
			enterTextToInputField(Locators_Sanity.calc_Edit_text_field,"10/2");
			minWait();
			minWait();
			int num8=2;
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
			minWait();
			String currentNumberText4=Locators_Sanity.calc_Edit_text_field.getText();
			minWait();
			//						System.out.println(currentNumberText4);
			int s4=(num7/num8);
			String sum4=String.valueOf(s4);
			//						System.out.println(sum4);
			if(currentNumberText4.contains(sum4)){
				check4 = true;
				APP_LOGS.info("Pass:Division of 2 numbers verified successfully.");
			}
			if((check1)&&(check2)&&(check3)&&(check4)){
				check= true;
				APP_LOGS.info("Basic Functionality operations verified");
				Sa.assertTrue(check, " ");
			}
			else{
				APP_LOGS.info(currentNumberText1+": "+check1+ "\n"+currentNumberText2+": "+check2+"\n"+currentNumberText3+": "+check3+"\n"+currentNumberText4+": "+check4+
						"\n");
				Sa.fail();
			}	
			customWait(3000);
		} catch (NoSuchElementException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Sa.fail();
		}
		Sa.assertAll();
	}
	

	public void deleteCallLogs() throws InterruptedException {
		for(int i=1; i<=5; i++) {
			if(isElementExist(Locators_Sanity.dailedNum)) {
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_CLEAR);
				minWait();
				clickBtn(Locators_Sanity.contact_DeleteBtn);
			} 
		}aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_HOME);
	}

	public void selectApp(WebElement app) throws InterruptedException {
		/*
		 * select Calculator application
		 */
		try {
			minWait();	
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_HOME);
			minWait();
			clickBtn(Locators_Sanity.ToolsOptn);
			minWait();
			clickBtn(app);
		} catch (NoSuchElementException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Assert.fail();
		}
	}
	
	public void selectCalenderApp() throws InterruptedException {
		/*
		 * select Calculator application
		 */
		try {
			minWait();	
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_HOME);
			minWait();
			if(isElementExist(Locators_Sanity.CalenderApp)) {
				clickBtn(Locators_Sanity.CalenderApp);
			}
			else {
				clickBtn(Locators_Sanity.Device_App);
				minWait();
				clickBtn(Locators_Sanity.CalenderApp);
			}
		} catch (NoSuchElementException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Assert.fail();
		}
		
	}
	
	public void createNewEvent() throws InterruptedException {
		/*
		 * Create a new event
		 */
		try {
			minWait();	
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_MENU);
			minWait();
			clickBtn(Locators_Sanity.Calender_NewEvent);
			minWait();
			enterTextToInputField(Locators_Sanity.Calender_Eventname, "Birthday");
			minWait();
			enterTextToInputField(Locators_Sanity.Calender_Location, "India");
			for(int i=1; i<=5; i++) {
				if(isElementExist(Locators_Sanity.Calender_Description)) {
					minWait();
					minWait();
					enterTextToInputField(Locators_Sanity.Calender_Description,"Happy Birthday");
					break;
				}	
				else {
					minWait();	
					aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
					continue;
					
				}
			}
			enterTextToInputField(Locators_Sanity.Calender_Description,"Happy Birthday");
			minWait();	
			clickBtn(Locators_Sanity.Calender_SpinnerRepitition);
			minWait();	
			clickBtn(Locators_Sanity.Calender_onetime_event);
			minWait();	
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_MENU);
			minWait();
			clickBtn(Locators_Sanity.save);
		} catch (NoSuchElementException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Assert.fail();
		}
	}
	
	public void validateEventReminder() throws InterruptedException {
		SoftAssert Sa = new SoftAssert();
		try {
			minWait();	
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_HOME);
			minWait();
			minWait();	
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_MENU);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_ENTER);
			minWait();
			for(int i=1; i<=5; i++){
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_UP);
				minWait();
			}
			
			if(isElementExist(Locators_Sanity.Notification_Calender)) {
				minWait();
				check = true;
				APP_LOGS.info("Calender Launched and validated Reminder Notification is succesful.");
				Sa.assertTrue(check, " ");
			}
			else {
				APP_LOGS.info("Calender Launched and validated Reminder Notification is unsuccesful.");
				Sa.fail();
			}
		} catch (NoSuchElementException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Sa.fail();
		}
		Sa.assertAll();
	}
	
	
	
	public void deleteEventCreated() throws InterruptedException {
		/*
		 * Delete events which is created
		 */
		SoftAssert Sa = new SoftAssert();
		try {
			customWait(1000);
			selectCalenderApp();
			minWait();	
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_MENU);
			minWait();
			if(isElementExist(Locators_Sanity.Deleteevents_Calender)) {
				clickBtn(Locators_Sanity.Deleteevents_Calender);
				check = true;
				APP_LOGS.info("Calender Launched and validated Reminder Notification is succesful.");
				Sa.assertTrue(check, " ");
			
				minWait();	
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_MENU);
				minWait();
				minWait();	
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
				minWait();
				minWait();	
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_ENTER);
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_MENU);
				minWait();
				minWait();	
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_UP);
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_ENTER);
				minWait();
				minWait();	
				clickBtn(Locators_Sanity.OK_Button);
				minWait();
				APP_LOGS.info(" Events are  present to Delete");
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			}
			else {		
				APP_LOGS.info(" Events are not present to Delete");
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
				APP_LOGS.info("Calender Launched and validated Reminder Notification is unsuccesful.");
				Sa.fail();
			}
			minWait();
		} catch (NoSuchElementException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Sa.fail();
		}
		Sa.assertAll();		
	}
	
	//Start Recorder
		public void recordSound() throws InterruptedException {
			try {
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
				minWait();
				selectRecordBtn();
				APP_LOGS.info("Pressed Record button sucessfully");
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_LEFT);
				APP_LOGS.info("Pressed Stop button sucessfully");
			} catch (NoSuchElementException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				Assert.fail();
			}	
		}
		
		//Selecting Allow for pop ups
		public void selectRecordBtn() throws InterruptedException {
			for(int i=0; i<=2; i++) {
				if(isElementExist(Locators_Sanity.allowBtn)){
					customWait(2000);
					Locators_Sanity.allowBtn.click();
					APP_LOGS.info("Pressed Allow button sucessfully");
					minWait();
					APP_LOGS.info("Allow Button is displayed and Selected, succesful.");

				}else{
					APP_LOGS.info("Allow Button is not displayed.");

				}
			}maxWait();	
		}

		
		//Save Recorded Sound
		public void SaveSoundRecord() throws InterruptedException {
			try {
				minWait();
				Locators_Sanity.recordingName.clear();
				minWait();
				enterTextToInputField(Locators_Sanity.recordingName,"SampleSoundRecord");
				minWait();
				if(isElementExist(Locators_Sanity.NameExist)) {
					Locators_Sanity.DiscardBtn.click();
				}
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
//				Locators_Sanity.saveBtn.click(); 
				APP_LOGS.info("Pressed Save button sucessfully");
				minWait();
			/*	aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_HOME);*/
			} catch (NoSuchElementException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				Assert.fail();
			}
		}
		

		public void selectFileExplApp() throws InterruptedException {
			/*
			 * select File Explorer application
			 */
			try {
				minWait();	
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_HOME);
				minWait();
				clickBtn(Locators_Sanity.Device_App);
				minWait();
				clickBtn(Locators_Sanity.FileExplorer_App);
				minWait();
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_ENTER);
				minWait();
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
				for(int i=1; i<=15; i++) 
				{
					if(isElementExist(Locators_Sanity.SoundRecorderoptn)) {
						minWait();
						aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_ENTER);
//					clickBtn(Locators_Sanity.SoundRecorderoptn);
						minWait();
						break;
					}
					else {
						aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
						minWait();
						continue;
					}		
				}
			} catch (NoSuchElementException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				Assert.fail();
			}
		}

		
		public void validateDetailsSoundRec() throws InterruptedException {
			/*
			 * Validate sound recorder in File explorer
			 */
			try {
				minWait();
				minWait();
				if(isElementExist(Locators_Sanity.SampleSoundRec)){
					check = true;
					String getSoundRecName= Locators_Sanity.SampleSoundRec.getText();
					APP_LOGS.info("Sound Recorder Name is :"+getSoundRecName);
				}
				else{
					APP_LOGS.info("All Details of Sound Record is not Displayed");
					Assert.fail();
				}	  
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
//			customWait(2000);
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_HOME);
			} catch (NoSuchElementException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				Assert.fail();
			}
		}

		
		//Selecting List Option after Launching Application
		public void selectListOptn() throws InterruptedException {
			try {
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_RIGHT);
				minWait();
				APP_LOGS.info("Recording List Pressed succesfully");
				if(isElementExist(Locators_Sanity.ListallowBtn)) {
					Locators_Sanity.ListallowBtn.click(); }
				minWait();
				longpress(4);
			} catch (NoSuchElementException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				Assert.fail();
			}
		}
		
		//Delete Saved Recorded Sound
		public void deleteSavedRecorder() throws InterruptedException {
			try {
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_RIGHT);
				APP_LOGS.info("Pressed List Icon sucessfully");
				minWait();
				minWait();
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_MENU);
				for(int i=1; i<=3; i++) {
					aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
				}
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
				minWait();
				APP_LOGS.info("Pressed Delete Icon sucessfully");
				minWait();
				minWait();
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_RIGHT);
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
				APP_LOGS.info("Pressed Delete Button sucessfully");
				Thread.sleep(2000);
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			} catch (NoSuchElementException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				Assert.fail();
			}
		}
		
		public void ValidatedefaultSettings() throws InterruptedException {

			SoftAssert SS = new SoftAssert();
			try {
				for(int i=1; i<=7; i++) {
					minWait();	
					aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
				}

				if(isElementExist(Locators_Sanity.defaultPhone_ringtone)) {
					check1=true;
					APP_LOGS.info("check1: "+check1);
					APP_LOGS.info("Default Phone Ringtone is displayed succesfully");
				}else{
					APP_LOGS.info("check1: "+check1);
					APP_LOGS.info("Default Phone Ringtone is displayed unsuccesfully");
				}

				if(isElementExist(Locators_Sanity.defaultPhone_ringtone)) {	
					check2=true;
					APP_LOGS.info("check2: "+check2);
					APP_LOGS.info("Default Notification Ringtone is displayed succesfully");
				}else{
					APP_LOGS.info("check2: "+check2);
					APP_LOGS.info("Default Notification Ringtone is displayed unsuccesfully");
				}

				if((check1)&&(check2)){
					check=true;
					APP_LOGS.info("Validated sucessfully Defult Notification Ringtone");			
					SS.assertTrue(check, " ");
				}else{
					APP_LOGS.info("Validated is not sucessful Defult Notification Ringtone");					
					SS.fail();
				}minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			} catch (NoSuchElementException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				SS.fail();
			}
			SS.assertAll();
		}
		
		
		public void selectSettings(WebElement option) throws InterruptedException {
			/*
			 * select particular setting option
			 */
			try {
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_HOME);
				minWait();
				minWait();
				clickBtn(Locators_Sanity.settingsIcon);
				minWait();
				APP_LOGS.info("Launched Settings icon sucessfully");
				for (int iter= 1; iter<= 23; iter++) {
					if (isElementExist(option)) {
						String getName= option.getText();
						APP_LOGS.info("Settings Option is displayed sucessfully" +getName);
						minWait();	
						clickBtn(option);
						break;					
					} else {
						aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
						continue;					
					}
				}
			} catch (NoSuchElementException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				Assert.fail();
			}
		}
		
		public void validateDefaultLanguage() throws InterruptedException {
			/*
			 * check the default language
			 */
			SoftAssert SA = new SoftAssert();
			try {
				if(isElementExist(Locators_Sanity.defaultLaunguage)){
					check = true;
					String getName= Locators_Sanity.defaultLaunguage.getText();
					APP_LOGS.info("Default Language is :"+getName);
					SA.assertTrue(check, " ");
				}
				else{
					APP_LOGS.info("Default Language is not Displayed");
					SA.fail();
				}	minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			} catch (NoSuchElementException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				SA.fail();
			}
			SA.assertAll();
		}
		
		
		public void validatePrefferedNetwork(WebElement type) throws InterruptedException {
			/*
			 * Preffered network should not support 
			 */
			SoftAssert Se = new SoftAssert();
			try {
				minWait();	
				minWait();	
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
				minWait();	
				minWait();	
				if(type.isEnabled()) {
					minWait();	
					check = true;
					APP_LOGS.info("Preffered Network mode is not Supportable");
					Se.assertTrue(check, " ");
				}
				else{
					minWait();	
					APP_LOGS.info("Preffered Network mode is not Supportable");
					Se.fail();				
				}	minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			} catch (NoSuchElementException e) {
				e.printStackTrace();
				Se.fail();
			}
			Se.assertAll();
		}

		public void validateEditionDeletionApn() throws InterruptedException {
			/*
			 * validate default APn should not able to edit and delete
			 */
			SoftAssert SA = new SoftAssert();
			try {
				minWait();	
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_MENU);
				minWait();	
				minWait();	
				if(isElementExist(Locators_Sanity.edit)) {
					check1=true;
					APP_LOGS.info("check1: "+check1);
					APP_LOGS.info("Edition of Apn is displayed unsuccesfully");
				}else{
					check1=true;
					APP_LOGS.info("check1: "+check1);
					APP_LOGS.info("Edition of Apn is displayed succesfully");			
				}
				clickBtn(Locators_Sanity.edit);
				minWait();
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_MENU);
				minWait();
				minWait();
				if(isElementExist(Locators_Sanity.delete_APN)) {	
					check2=true;
					APP_LOGS.info("check2: "+check2);
					APP_LOGS.info("Deletion Apn is displayed unsuccesfully");
				}else{
					check2=true;
					APP_LOGS.info("check2: "+check2);
					APP_LOGS.info("Deletion Apn is displayed succesfully");
				}
				if((check1)&&(check2)){
					check=true;
					APP_LOGS.info("Validated sucessfully Edition and Deletion of Default APn");			
					SA.assertTrue(check, " ");
				}else{
					APP_LOGS.info("Validated is not sucessful Edition and Deletion of Default APn ");					
					SA.fail();
				}minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			} catch (NoSuchElementException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				SA.fail();
			}
			SA.assertAll();
		}
		
		public void validateRestoreAPn() throws InterruptedException {
			/*
			 * Validate APN default /restore After and before retore Option 
			 */
			SoftAssert SS = new SoftAssert();
			try {
				String getApNTxt= null;
				String getApNTxtRestore= null;
				for(int i=1; i<=3; i++) {
					if(isElementExist(Locators_Sanity.SelectedApn)) {
						aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
						getApNTxt = Locators_Sanity.Txt_Apn.getText();
						 System.out.println(getApNTxt);
					}
					else {
						aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_MENU);
						continue;
					}		
				}	
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_MENU);	
				minWait();
				clickBtn(Locators_Sanity.RestoreDefault);
				customWait(4000);
				customWait(4000);
				customWait(4000);
				getApNTxtRestore = Locators_Sanity.Txt_Apn.getText();
				System.out.println(getApNTxtRestore);
				if(getApNTxt.equalsIgnoreCase(getApNTxtRestore)) {
					check = true;
					APP_LOGS.info("Default Restore Apn is display sucessful");
					SS.assertTrue(check, " ");
				}
				else{
					APP_LOGS.info("Default Restore Apn is not Displayed");
					SS.fail();
				}	
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			} catch (NoSuchElementException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				SS.fail();
			}
			SS.assertAll();
		}
		
		public void removeScreenLock() throws InterruptedException {
			/*
			 * Remove Screen Lock PIN 
			 */
			try {
				minWait();
				enterTextToInputField(Locators_Sanity.setPsswd, "1234");
				minWait();
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_ENTER);
				minWait();
				minWait();
				clickBtn(Locators_Sanity.Press_hold_Star);
				minWait();
				minWait();
				clickBtn(Locators_Sanity.yes_RemoveBtn);
				minWait();
			} catch (NoSuchElementException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				Assert.fail();
			}	
		}
		
		public void setPinLock() throws InterruptedException {
			/*
			 * set screen PIN lock 
			 */
			try {
				minWait();
				clickBtn(Locators_Sanity.Screen_Lock);
				minWait();
				minWait();
				if(isElementExist(Locators_Sanity.setPsswd)) {		
					removeScreenLock();
					APP_LOGS.info("Removed ScreenLock");
					minWait();
					clickBtn(Locators_Sanity.Screen_Lock);
					minWait();
				}
				minWait();
				clickBtn(Locators_Sanity.PIN);
				minWait();
				enterTextToInputField(Locators_Sanity.setPsswd, "1234");
				minWait();
				minWait();
				clickBtn(Locators_Sanity.continueBtn);
				minWait();
				minWait();
				enterTextToInputField(Locators_Sanity.setPsswd, "1234");
				minWait();
				minWait();
				clickBtn(Locators_Sanity.OK_Button);
				minWait();
				minWait();
			} catch (NoSuchElementException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				Assert.fail();
			}
		}
		
		public void selectSetSIMLock() throws InterruptedException, IOException {
			try {
				for(int i=1; i<=4; i++) {
					if(isElementExist(Locators_Sanity.Set_SIM_Lock_Optn)) {
						clickBtn(Locators_Sanity.Set_SIM_Lock_Optn);
						break;
					}
					else {
						aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
						minWait();
						continue;
					}
				}
				minWait();
				minWait();
				if(Check_Activation_Off_On().equals("OFF")) {
					APP_LOGS.info("Activation status is OFF");
					clickBtn(Locators_Sanity.toggle_btn);
				}
				else {
					APP_LOGS.info("Activation status is ON");
				}
				minWait();
				enterTextToInputField(Locators_Sanity.setPsswd_SIM, "1234");
				minWait();
				minWait();
				clickBtn(Locators_Sanity.OK_Button);
				minWait();
			} catch (NoSuchElementException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				Assert.fail();
			}
		}
		
		public void validateSIMLock() throws InterruptedException, IOException
		{
			SoftAssert Sa = new SoftAssert();
			try {
				customWait(2000);
				if(Check_Activation_Off_On().equals("OFF")) {
					APP_LOGS.info("Activation status is OFF");
					APP_LOGS.info("check1: "+check1);
					Sa.fail();
				}
				else {
					APP_LOGS.info("Activation status is ON");
					check = true;
					APP_LOGS.info("check1: "+check1);
					APP_LOGS.info("Set Device Screen Lock sucessful");
					Sa.assertTrue(check, " ");
				}
				minWait();
				clickBtn(Locators_Sanity.Set_SIM_Lock_Optn);
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.ENTER);
				minWait();
				enterTextToInputField(Locators_Sanity.setPsswd_SIM, "1234");
				minWait();
				minWait();
				clickBtn(Locators_Sanity.OK_Button);
				minWait();
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			} catch (NoSuchElementException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				Sa.fail();
			}
			Sa.assertAll();
				
	}
		
		
		public void validateSetPIN() throws InterruptedException {
			/*
			 * PIN screen Lock is checked
			 */
			SoftAssert SS = new SoftAssert();
			try {
				minWait();
				String getTxt= null;
				minWait();
				if(isElementExist(Locators_Sanity.Screen_Lock)) {
					getTxt = Locators_Sanity.txt_ScreenLockSet.getText();
					//				System.out.println(getTxt);
					if(getTxt.equalsIgnoreCase("PIN")) {
						check = true;
						APP_LOGS.info("Set Device Screen Lock sucessful");
						SS.assertTrue(check, " ");
					}
					else{
						APP_LOGS.info("Set Device Screen Lock is unsucessful");
						SS.fail();
					}	
				}
				clickBtn(Locators_Sanity.Screen_Lock);
				minWait();
				minWait();
				removeScreenLock();
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			} catch (NoSuchElementException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				SS.fail();
			}
			SS.assertAll();
		}
		
	
		
		public void enableparticularSettings(WebElement switchBtn) throws InterruptedException {
			/*
			 * Select particular settings option
			 */
	
			try {
				if(isElementExist(switchBtn)) {
					minWait();
//				clickBtn(Locators_Sanity.wifi_switch);
					aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_ENTER);
				}minWait();	
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
				minWait();	
				minWait();	
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
				minWait();
			} catch (NoSuchElementException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				Assert.fail();
			}	
		}
		
		public void ConnectSecuredWifi() throws InterruptedException {
			   /*
			    * Connect wifi with entering passkey
			    */
			   try {
			    aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_HOME);
			    minWait();
			    minWait();
			    clickBtn(Locators_Sanity.settingsIcon);
			    minWait();
			    aDriver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().resourceId(\"com.android.settings:id/main_content\")).scrollIntoView(new UiSelector().text(\"Wi-Fi\"))");  
			    clickBtn(Locators_Sanity.wifi);
			    disconnectWifiifConnected();
			    minWait();
			    minWait();
			    aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_MENU);
			    minWait();
			    aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_ENTER);
			    //refresh btn
			    minWait();
			    minWait();
			    customWait(4000);
			    selectIDcwifi();      
			    minWait();
			    clickBtn(Locators_Sanity.wifi_show_Pswd);
			    minWait();
			    aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_UP);
			    customWait(1000);
			    for(int i =1; i<=3; i++) {
			     minWait();
			     aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_POUND);
			     minWait();
			    }
			    minWait();
			    enterTextToInputField(Locators_Sanity.wifi_IDC_Psswd,"1dcS0n1md0tc0MbLr");
			    minWait();
			          customWait(1000);
			    String psswrd = Locators_Sanity.wifi_IDC_Psswd.getText();
//			    System.out.println(psswrd);
			    clickBtn(Locators_Sanity.wifi_IDC_ConnectBtn);
			    APP_LOGS.info("IDC available secured wifi is connected");
			    customWait(4000);
			   } catch (NoSuchElementException e) {
			    // TODO Auto-generated catch block
			    e.printStackTrace();
			    Assert.fail();
			   }
			  }
			  
		
		
		public void selectIDcwifi() throws InterruptedException {
			/*
			 * Select IDC wifi which is available
			 */
			try {
				customWait(4000);
				for(int i=1; i<=50; i++) {
					if(isElementExist(Locators_Sanity.wifi_IDC)) {
						minWait();
						clickBtn(Locators_Sanity.wifi_IDC);
						APP_LOGS.info("IDC available secured wifi is Selected");
						break;
					}
					else {
						minWait();
						aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
						continue;
					}
				}
			} catch (NoSuchElementException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				Assert.fail();
			}
		}
		
		public void validateWifiConnect() throws InterruptedException {
			/*
			 * Validate wifi connection
			 */
			SoftAssert Sb = new SoftAssert();
			try {
				minWait();
				selectSettings(Locators_Sanity.wifi);
				customWait(4000);
				selectIDcwifi();
				minWait();
				if(isElementExist(Locators_Sanity.wifi_IDC_ForgetBtn)) {
					 check = true;
					 APP_LOGS.info("Connected to Secured Wifi succesfully.");
					 Sb.assertTrue(check, " ");						
				 }else{
					 APP_LOGS.info("Connected to Secured Wifi is unsuccesful.");	
					 Sb.fail();				
				 }
				minWait();	
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
				minWait();	
				minWait();	
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
				minWait();
			} catch (NoSuchElementException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				 Sb.fail();				
			}	
			Sb.assertAll();
		}
		
		public void disconnectWifiifConnected() throws InterruptedException {
			/*
			 * disconnect wifi if IDC wifi connected
			 */
			try {
				selectIDcwifi();
				customWait(4000);
				clickBtn(Locators_Sanity.wifi_IDC_ForgetBtn);
				APP_LOGS.info("IDC available secured wifi is disconnected");
				customWait(4000);
			} catch (NoSuchElementException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				Assert.fail();
			}
		}
		
		public void validateSearchDevices() throws InterruptedException {
			/*
			 * validate with available devices are shown
			 */
			customWait(4000);
			customWait(4000);
			customWait(4000);
			if(isElementExist(Locators_Sanity.bluetooth_Availabledevices)) {
				 check = true;
				 APP_LOGS.info("Bluetooth Search or scan devices is succesful.");
				 Assert.assertTrue(check);						
			 }else{
				 APP_LOGS.info("Bluetooth Search or scan devices is unsuccesful.");	
				 Assert.fail();				
			 }
		}
		
		public void validateselectHardKeys() throws InterruptedException, IOException {
			/*
			 * validate Select Hard Keys , Home button, volume Up /down
			 */
			SoftAssert Sb = new SoftAssert();
			try {
				customWait(5000);
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_HOME);
				if(isElementExist(Locators_Sanity.homePge)) {
					check1=true;
					APP_LOGS.info("check1: "+check1);
					APP_LOGS.info("Home Key is pressed succesfully.");				
				}else{
					 APP_LOGS.info("check1: "+check1);
					 APP_LOGS.info("Home Key is not pressed sucessfully.");				
				 }
				Runtime.getRuntime().exec("cmd /C \"adb shell input keyevent --longpress KEYCODE_VOLUME_UP");
				minWait();
				customWait(5000);
				Runtime.getRuntime().exec("cmd /C \"adb shell input keyevent --longpress KEYCODE_VOLUME_UP");
//			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_VOLUME_UP);
				if(isElementExist(Locators_Sanity.volume_Up_Icon)) {
					check2=true;
					APP_LOGS.info("check2: "+check2);
					APP_LOGS.info("Volume Up Key is pressed is succesful.");			
				}else{
					check2=true;
					APP_LOGS.info("check2: "+check2);
					 APP_LOGS.info("Volume Up Key is not pressed sucessfully");				
				 }
				
				customWait(5000);
				for(int i=1; i<=7; i++) {
					minWait();
					Runtime.getRuntime().exec("cmd /C \"adb shell input keyevent --longpress KEYCODE_VOLUME_DOWN");
				}
				
//			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_VOLUME_DOWN);
				if(isElementExist(Locators_Sanity.volume_Down_Icon)) {
					check3=true;
					APP_LOGS.info("check3: "+check3);
					APP_LOGS.info("Volume down Key is pressed is succesful.");			
				}else{
					check3=true;
					 APP_LOGS.info("check3: "+check3);
					 APP_LOGS.info("Volume down Key is not pressed sucessfully");				
				 }
				
				customWait(5000);
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_MENU);
				minWait();
				if(isElementExist(Locators_Sanity.menu_Notification)) {
					check4=true;
					APP_LOGS.info("check4: "+check4);
					APP_LOGS.info("Home Key is pressed succesfully.");				
				}else{
					 APP_LOGS.info("check4: "+check4);
					 APP_LOGS.info("Home Key is not pressed sucessfully.");				
				 }
				
				if((check1)&&(check2)&&(check3)&&(check4)){
					check=true;
					APP_LOGS.info("Validated Pressed Hard Key sucessfully ");			
					Sb.assertTrue(check, " ");
				}else{
					APP_LOGS.info("Not Validated Pressed Hard Key sucessfully ");					
					Sb.fail();
				}
			} catch (NoSuchElementException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				Sb.fail();
			}Sb.assertAll();
		}
		
		public void capturePic() throws InterruptedException, IOException {
			/*
			 * Capture picture via camera
			 */
			try {
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_HOME);
				minWait();
				clickBtn(Locators_Sanity.cameraApp);
				minWait();
				customWait(4000);	
				customWait(4000);	
				customWait(4000);	
				clickBtn(Locators_Sanity.camera_Icon);
//			Runtime.getRuntime().exec("cmd /C \"adb shell input keyevent --longpress KEYCODE_DPAD_CENTER");
//			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
				minWait();
			} catch (NoSuchElementException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				Assert.fail();
			}
		}
		
		public void validateFileExplorer() throws InterruptedException {
			/*
			 * validate Picture captured is present in DCIM
			 */
			SoftAssert SS = new SoftAssert();
			try {
				minWait();	
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_HOME);
				minWait();
				clickBtn(Locators_Sanity.Device_App);
				minWait();
				clickBtn(Locators_Sanity.FileExplorer_App);
				minWait();
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_ENTER);
				minWait();
				minWait();
				for(int i=1; i<=15; i++) 
				{
					if(isElementExist(Locators_Sanity.DCIM_optn)) {
						minWait();
						aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_ENTER);
						minWait();
						aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_ENTER);
						minWait();//Select Camera folder
						break;
					}
					else {
						aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
						minWait();
						continue;
					}
				}
				minWait();
				minWait();

				if(isElementExist(Locators_Sanity.pic_capture)) {
					check=true;
					APP_LOGS.info("Validated File Explorer Browsed captured picture sucessfully ");			
					SS.assertTrue(check, " ");
				}else{
					APP_LOGS.info("Not Validated File Explorer Browsed captured picture sucessfully ");					
					SS.fail();
				}

				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_CLEAR);
				minWait();
				clickBtn(Locators_Sanity.contact_DeleteBtn);
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
				minWait();
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			} catch (NoSuchElementException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				SS.fail();
			}
			SS.assertAll();
		}
		
		
		
		
		public void validateClockApp() throws InterruptedException {
			/*
			 * getCurrent date time
			 */
			SoftAssert Sd = new SoftAssert();
			try {
				String time = Locators_Sanity.Current_Device_Time.getText();
//			System.out.println(time);
				String date = Locators_Sanity.Current_Device_Date.getText();
//			System.out.println(date);
				
				/*Formatter fmt = new Formatter();
				Calendar cal = Calendar.getInstance();
				//actual arguments for belo method("%tB %td %tY", cal, cal, cal) "," is concatenated.
				fmt.format("%ta"+","+" %tB %td", cal, cal, cal);
//		    System.out.println(fmt);
				String date1 = fmt.toString();
				check1=date1.equalsIgnoreCase(date);
				APP_LOGS.info("check1: "+check1);
				APP_LOGS.info("Clock Date displayed succesfully.");	
				 
				java.util.Date today = new java.util.Date();
				Time fmt1 = new java.sql.Time(today.getTime());
//		    System.out.println(fmt1); 
				String time1 = fmt1.toString();
				
				check2=time1.contains(time);
				APP_LOGS.info("check2: "+check2);
				APP_LOGS.info("Clock Time displayed succesfully.");	*/
				
				String getClockTime = Locators_Sanity.Current_Device_Time.getText();
				System.out.println(getClockTime);
				if(isElementExist(Locators_Sanity.Current_Device_Time)){
					check1 = true;
					APP_LOGS.info("Element found: "+getClockTime);
					APP_LOGS.info("check1: "+check1);
				}else{
					APP_LOGS.info("Clock Time element is not found");
					APP_LOGS.info("check1: "+check1);
				}
				
				
				String getClockDate = Locators_Sanity.Current_Device_Date.getText();
				System.out.println(getClockDate);
				if(isElementExist(Locators_Sanity.Current_Device_Date)){
					check2 = true;
					APP_LOGS.info("Element found: "+getClockDate);
					APP_LOGS.info("check2: "+check2);
				}else{
					APP_LOGS.info("Clock Date element is not found");
					APP_LOGS.info("check2: "+check2);
				}
  
				clickBtn(Locators_Sanity.Alarm_Icon);
				minWait();
				minWait();
				if((Locators_Sanity.Alarm_Icon).isSelected()) {
					check3 = true;
					APP_LOGS.info("check3: "+check3);
					APP_LOGS.info("Alarm pressed succesfully.");	
				}
				else {
					APP_LOGS.info("check3: "+check3);
					APP_LOGS.info("Alarm pressed unsuccesfully.");	
				}
				
				clickBtn(Locators_Sanity.Timer_Icon);
				minWait();
				minWait();
				if(isElementExist(Locators_Sanity.Timer_Timing)) {
					check4 = true;
					APP_LOGS.info("check4: "+check4);
					APP_LOGS.info("Timer pressed succesfully.");	
				}
				else {
					APP_LOGS.info("check4: "+check4);
					APP_LOGS.info("Timer pressed unsuccesfully.");	
				}
				

				clickBtn(Locators_Sanity.Stopwatch_Icon);
				minWait();
				minWait();
				if(isElementExist(Locators_Sanity.STopwatch_Timing)) {
					check5 = true;
					APP_LOGS.info("check5: "+check5);
					APP_LOGS.info("Stopwatch pressed succesfully.");	
				}
				else {
					APP_LOGS.info("check5: "+check5);
					APP_LOGS.info("Stopwatch pressed unsuccesfully.");	
				}
				
				if((check1)&&(check2)&&(check3)&&(check4)){
					check= true;
					APP_LOGS.info("Clock date and time, Alarm, Stopwatch and Timer validated");
					APP_LOGS.info("TC is Passed.");
					Sd.assertTrue(check, " ");
				}else{
					APP_LOGS.info("Test Case Failed");
					Sd.fail();
				}
			} catch (NoSuchElementException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				Sd.fail();
			}
			Sd.assertAll();
		}
		
		public void enable24Format() throws InterruptedException, IOException {
			try {
				for(int i=1; i<=6; i++) {
					if(isElementExist(Locators_Sanity.Format_optn)) {
						if(Check_Activation_Off_On().equals("OFF")) {
							clickBtn(Locators_Sanity.toggle_btn);
							APP_LOGS.info("Activation status is OFF");		
						}
						else {
							APP_LOGS.info("Activation status is ON");
						}	break;
					}
					else {
						aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
						continue;
					}
				}minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			} catch (NoSuchElementException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				Assert.fail();
			}
		}
		
		
		public void validateInternalStorage() throws InterruptedException {
			/*
			 * Validate internal storage
			 */
			SoftAssert Sv = new SoftAssert();
		
				try {
					minWait();
					if(isElementExist(Locators_Sanity.internalStorageTxt))
					{
						check1 = true;
						APP_LOGS.info("check1: " + check1);
						APP_LOGS.info("Internal_Storage_text Element is present");
						String getAlertText = Locators_Sanity.internalStorage_Value_Txt.getText();
						APP_LOGS.info("internal_Storage_value_text: " + getAlertText);
					}else
					{
						APP_LOGS.info("check1: "+check1);
						APP_LOGS.info("Internal_Storage_text Element is not present");			
					}
					
					
					if(isElementExist(Locators_Sanity.Available_Progress_Bar))
					{
						check2 = true;
						APP_LOGS.info("check2: " + check2);
						APP_LOGS.info("Internal_Storage_Available_bar Element is present");
						String getAlertText = Locators_Sanity.Available_Value_Txt.getText();
						APP_LOGS.info("internal_Storage_Available_value_text: " + getAlertText);
					}else
					{
						APP_LOGS.info("check2: "+check2);
						APP_LOGS.info("Internal_Storage_Available_bar Element is not present");			
					}
					
					if((check1)&&(check2)){
						check=true;
						APP_LOGS.info("Internal Storage and available storage of the device is displayed successfully.");							
						customWait(5000);
						Sv.assertTrue(check, " ");
					}else{
						APP_LOGS.info("Test case failed");			
						minWait();
						customWait(5000);
						Sv.fail();
					}
					minWait();
					aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
					minWait();
					aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
				} catch (NoSuchElementException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					Sv.fail();
				}
				Sv.assertAll();
		}
		
		
		public void validateThundersoftUpdate() throws InterruptedException {
			/*
			 * check for the update 
			 */
			if(isElementExist(Locators_Sanity.AboutPhone)) {
				clickBtn(Locators_Sanity.AboutPhone);
			}
			
			if(isElementExist(Locators_Sanity.Software_Update_ATT)) {
				clickBtn(Locators_Sanity.Software_Update_ATT);
				
				clickBtn(Locators_Sanity.check_Updates);
			}
			else if (isElementExist(Locators_Sanity.Software_Update)){
				clickBtn(Locators_Sanity.Software_Update);
				minWait();
				clickBtn(Locators_Sanity.check_for_Updates);
			}		
			customWait(5000);
			customWait(5000);
			customWait(5000);
			
			if(isElementExist(Locators_Sanity.Up_To_Software_OKBtn)) {
				check = true;
				APP_LOGS.info("Thundersoft Software update_text Element is present");
//				String getAlertText = Locators_Sanity.Up_To_Software.getText();
//				APP_LOGS.info("ThunderSoft Summary for Updation_text is: " + getAlertText);
				Assert.assertTrue(check);
			}else
			{
				APP_LOGS.info("Thundersoft Software update_text Element is not present");			
//				Assert.fail();
			}
			clickBtn(Locators_Sanity.OK_Button);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
		}
		
		
		public String Check_Activation_Off_On() throws InterruptedException, IOException {
			//This method is used to check wheather activation is ON or OFF .
			minWait();
			String status = null;

//			navigateToSafeGuard();

			if(Locators_Sanity.toggle_btn.getText().equals("OFF")) {
				status=Locators_Sanity.toggle_btn.getText();
				APP_LOGS.info("Checking Activation status for OFF" +status);
			}

			else if(Locators_Sanity.toggle_btn.getText().equals("ON")){
				status=Locators_Sanity.toggle_btn.getText();
				APP_LOGS.info("Checking Activation status for ON" +status);
			}

			return status;
		}
	
		
		
}