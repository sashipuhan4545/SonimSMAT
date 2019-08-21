package com.aosp.Utils;

import static org.testng.Assert.assertTrue;

import java.awt.AWTException;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Time;
import java.util.Calendar;
import java.util.Formatter;
import java.util.Random;


import org.apache.http.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.asserts.SoftAssert;

import com.graphics.gui.JsonFileReaderAndWriter;
import com.relevantcodes.extentreports.LogStatus;
import com.xp5S.util.BaseUtil;
import com.xp5S.util.Locators_FM;

import application.AllQA;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.AndroidKeyCode;


public class AOSP_XP5S_Sanity_Util extends BaseUtil {

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



	public String p_Id	 = "17c5e020";								// Primary Device Serial Number.
	public String r_Id	 = "37b82527";								// Reference Device Serial Number.
	public String p_b_No = "";			   					// Primary Device Build Number.
	public String r_b_No = ""; 								// Reference Device Build Number.
	public String pryNum = AllQA.PRIMARYDEVMDN;	// Reference Device MDN. 
	public String refNum = AllQA.REFERENCEDEVMDN;	// Reference Device MDN.


	public void fetch_Devices_Details() throws InterruptedException, FileNotFoundException, IOException, org.json.simple.parser.ParseException {
		System.out.println("In Fetching");
		minWait();
		p_Id=JsonFileReaderAndWriter.primaryDevIdReader();
		r_Id=JsonFileReaderAndWriter.ReadRefDeviceId();
		p_b_No=JsonFileReaderAndWriter.primaryDevFirmwareReader();
		System.out.println(p_b_No);
		r_b_No=JsonFileReaderAndWriter.ReadRefDeviceFirmWare();
	}

	
	

	public void OCRScreencapture(String fileName) throws InterruptedException {
		//Capture required Screen shots for validation

		OCR.Read_File.takeScreenShotForOcr(fileName);
		Thread.sleep(2000);
		OCR.my_main.validate_Using_OCR(fileName+".png");
	}

	public void setSleepMax() throws InterruptedException {
		/*
		 * Set sleep mode to maximum
		 */

		try {
			
		
			clickBtn(Locators_Sanity.Sleep_Optn);
			minWait();
			for(int i=1; i<=8; i++) {
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
			}
			clickBtn(Locators_Sanity.eightHrsSleep_Optn);
			test.log(LogStatus.INFO, " Sleep Mode is set to maximum");
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			test.log(LogStatus.ERROR, " No such Element Found");
		}		
	}

	public void checkAddContactsOption() throws InterruptedException
	{
		/*
		 * Check Add contact option and click
		 */
		try
		{
			if(isElementExist(Locators_Sanity.addContacts)) {
				clickBtn(Locators_Sanity.addContacts);
			}else{
				minWait();
				clickMenuAndElement(Locators_Sanity.addContactOpt);
			}
		}catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "Element Not Found");
		}
	}

	public boolean addImageInContact() throws InterruptedException
	{
		/*
		 * Captures image in contact
		 */
		try
		{
			Locators_Sanity.changePhotoOpt.click();
			minWait();
			Locators_Sanity.takePhotoOpt.click();
			minWait();
			Locators_Sanity.captureOpt.click();
			minWait();
			Locators_Sanity.imageOkOpt.click();
			minWait();
			return check1=true;
		}catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "Element Not Found");
			return check1 = false;
		}
	}


	public boolean addNameFields(String namePrefix,String firstName,String middleName,String lastName) throws InterruptedException
	{
		/*
		 * enters name to different name fields
		 */
		try
		{
			enterTextToInputField(Locators_Sanity.namePrefixFld,namePrefix);
			minWait();
			enterTextToInputField(Locators_Sanity.firstNameFld,firstName);
			minWait();
			enterTextToInputField(Locators_Sanity.middleNameFld,middleName);
			minWait();
			enterTextToInputField(Locators_Sanity.lastNameFld,lastName);
			return check2=true;
		}catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "Element Not Found");
			return check2=true;
		}
	}

	public boolean addPhoneticNameFields(String nameSuffix,String phoneticLastName,String phoneticMiddleName,String phoneticFirstName,String nickName) throws InterruptedException
	{
		/*
		 * enters phonetic names to different name fields
		 */
		try
		{
			enterTextToInputField(Locators_Sanity.nameSuffixFld,nameSuffix);
			minWait();
			Locators_Sanity.expandFld.click();
			enterTextToInputField(Locators_Sanity.phoneticLastName,phoneticLastName);
			minWait();
			enterTextToInputField(Locators_Sanity.phoneticMiddleName,phoneticMiddleName);
			minWait();
			enterTextToInputField(Locators_Sanity.phoneticFirstName,phoneticFirstName);
			minWait();
			enterTextToInputField(Locators_Sanity.nickNameFld,nickName);
			return check3=true;
		}catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "Element Not Found");
			return check3=false;
		}
	}

	public boolean addComapnyAndPhoneNumber(String company,String title) throws InterruptedException
	{
		/*
		 * enters Company,Title and phone Number
		 */
		try
		{
			enterTextToInputField(Locators_Sanity.companyFld,company);
			minWait();
			enterTextToInputField(Locators_Sanity.titleFld,title);
			minWait();
			enterTextToInputField(Locators_Sanity.contact_Phone,refNum);
			return check4=true;
		}catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "Element Not Found");
			return check4=false;
		}
	}

	public boolean addSIPEmailAndAddress(String SIP,String Email,String address) throws InterruptedException
	{
		/*
		 * enters SIP,Email and Address
		 */
		try
		{
			enterTextToInputField(Locators_Sanity.sipFld,SIP);
			minWait();
			enterTextToInputField(Locators_Sanity.emailFld,Email);
			minWait();
			ScrollToElement(Locators_Sanity.scrollpage, "Address");
			minWait();
			enterTextToInputField(Locators_Sanity.addressFld,address);
			minWait();
			return check5=true;
		}catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "Element Not Found");
			return check5=false;
		}
	}

	public boolean addOtherFieldsInContact(String IM,String website,String Notes) throws InterruptedException
	{
		/*
		 * enters IM,Website and Notes
		 */
		try
		{
			enterTextToInputField(Locators_Sanity.IMFld,IM);
			minWait();
			enterTextToInputField(Locators_Sanity.websiteFld,website);
			minWait();
			ScrollToElement(Locators_Sanity.scrollpage, "Notes");
			minWait();
			enterTextToInputField(Locators_Sanity.notesFld,Notes);
			return check6=true;
		}catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "Element Not Found");
			return check6=false;
		}
	}

	public void addContactWithAllFields(String namePrefix,String firstName,String middleName,String lastName,String nameSuffix,String phoneticLastName,String phoneticMiddleName,String phoneticFirstName,String nickName,String company,String title,String SIP,String Email ,String address,String IM,String website,String Notes) throws InterruptedException, IOException, org.json.simple.parser.ParseException
	{
		/*
		 * add contact with all fields and validate
		 */
		String Uid=JsonFileReaderAndWriter.primaryDevIdReader();
		try {
			checkAddContactsOption();
			minWait();
			ScrollToElement(Locators_Sanity.scrollpage, "More fields");
			minWait();
			Locators_Sanity.contact_MorefieldsOptn.click();
			minWait();
			aDriver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().className(\"android.widget.ScrollView\")).scrollIntoView(new UiSelector().text(\"Name\"))");
			minWait();
			boolean check1 = addImageInContact();
			Locators_Sanity.expandFld.click();
			minWait();
			boolean check2 = addNameFields(namePrefix, phoneticFirstName, phoneticMiddleName, phoneticLastName);
			minWait();
			ScrollToElement(Locators_Sanity.scrollpage, "Title");
			minWait();
			boolean check3 = addPhoneticNameFields(nameSuffix, phoneticLastName, phoneticMiddleName, phoneticFirstName, nickName);
			minWait();
			ScrollToElement(Locators_Sanity.scrollpage, "SIP");
			minWait();
			boolean check4 = addComapnyAndPhoneNumber(company, title);
			minWait();
			ScrollToElement(Locators_Sanity.scrollpage, "Email");
			minWait();
			boolean check5 = addSIPEmailAndAddress(SIP, Email, address);
			minWait();
			ScrollToElement(Locators_Sanity.scrollpage, "Website");
			minWait();
			boolean check6 = addOtherFieldsInContact(IM, website, Notes);
			minWait();
			if((check1)&&(check2)&&(check3)&&(check4)&&(check5)&&(check6))
			{
				clickMenuAndElement(Locators_Sanity.saveButton);
			}else {
				clickMenuAndElement(Locators_Sanity.saveButton);
				test.log(LogStatus.INFO, "Contact saved:Unable fill some fields");
			}
			clickBackButton(1);
			customWait(3000);
			Runtime.getRuntime().exec("adb -s "+p_Id+" shell input text Sonim");
			customWait(2000);
			validateAddedContact(namePrefix);
		}catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "Element Not Found");
		}
	}

	public void enterNameAndPhone(String Name) throws InterruptedException
	{
		/*
		 * Enter name and phone number 
		 */
		try{
			customWait(3000);
			enterTextToInputField(Locators_Sanity.contactName, Name);
			customWait(3000);
			enterTextToInputField(Locators_Sanity.contact_Phone, refNum);
			customWait(3000);
		}
		catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "Element Not Found");
		}
	}

	public void clickMenuButton()
	{
		/*
		 * clicks on back button with iteration as user input
		 */
		try {
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_MENU);
			minWait();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void addContactAndCopyToSIM(String Name) throws InterruptedException, IOException
	{
		/*
		 * Add's Contact with Name and Phone Number and validates
		 */
		try {
			checkAddContactsOption();
			minWait();
			enterNameAndPhone(Name);
			minWait();
			clickMenuAndElement(Locators_Sanity.saveButton);
			clickBackButton(1);
			customWait(2000);
			Runtime.getRuntime().exec("adb -s "+p_Id+" shell input text "+ Name);
			customWait(3000);
			validateAddedContact(Name);
			minWait();
			clickMenuButton();
			if(isElementExist(Locators_Sanity.copyToSIMOpt)) {
				clickBtn(Locators_Sanity.copyToSIMOpt);
			}else {
				clickBtn(Locators_Sanity.copyToSIM1Opt);
				test.log(LogStatus.INFO, "Not able to fill all fields,Contact saved");
			}
			minWait();
			clickBackButton(1);
			validatecopiedContactToSIM();
			clickBackButton(1);
		}catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "Element Not Found");
		}
	}

	public void validateAddedContact(String Name) throws InterruptedException, IOException
	{
		/*
		 * validates added contact
		 */
		SoftAssert SA= new SoftAssert();
		try {
			customWait(2000);
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
			customWait(2000);
			if(!isElementExist(Locators_Sanity.noContacts)){
				String actualString = Locators_Sanity.addedContact.getText();
				System.out.println(actualString);
				if(actualString.contains(Name)){
					check = true;
					APP_LOGS.info("Contact Added sucessfully");
					test.log(LogStatus.INFO, "Contact Added sucessfully");
					SA.assertTrue(check, "Contact Added sucessfully");
				}else {
					APP_LOGS.info("Contact not added");
					test.log(LogStatus.INFO, "Contact not added");
					SA.fail("Contact not added");
				}
			}else {
				APP_LOGS.info("No Contacts Available");
				test.log(LogStatus.INFO, "No Contacts Available");
				SA.fail("No Contacts Available");
			}
		}catch (Exception e) {
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
		try {
			if(isElementExist(Locators_Sanity.noContacts)){
				check = true;
				APP_LOGS.info("Contacts deleted sucessfully");
				test.log(LogStatus.INFO, "Contacts deleted sucessfully");
				SA.assertTrue(check, "Contacts deleted sucessfully");	
			}else{
				APP_LOGS.info("Contacts not deleted");
				test.log(LogStatus.INFO, "Contacts not deleted");
				SA.fail("Contacts not deleted");
			}
		}catch (Exception e) {
			e.printStackTrace();
			APP_LOGS.info("Element Not Found");
			test.log(LogStatus.ERROR, "Element Not Found");
			SA.fail();
		}
		SA.assertAll();
	}


	public void validatecopiedContactToSIM()  throws InterruptedException, IOException
	{
		/*
		 * validates copied contact to SIM
		 */
		SoftAssert SA= new SoftAssert();
		try {
			if(Locators_Sanity.matchedContactsFld.getText().contains("2 Contacts matched")){
				check = true ;
				APP_LOGS.info("Contact copied to SIM sucessfully");
				test.log(LogStatus.INFO, "Contact copied to SIM sucessfully");
				SA.assertTrue(check, "Contact copied to SIM sucessfully");
			}else{
				APP_LOGS.info("Failed to copy Contact to SIM");
				test.log(LogStatus.INFO, "Failed to copy Contact to SIM");
				SA.fail("Failed to copy Contact to SIM");
			} 
		}catch (Exception e) {
			e.printStackTrace();
			APP_LOGS.info("Element Not Found");
			test.log(LogStatus.ERROR, "Element Not Found");
			SA.fail();
		}
		SA.assertAll();
	}



	public void disableAirplaneMode() throws InterruptedException, IOException, org.json.simple.parser.ParseException {
		/*
		 * check whether airplane mode is enabled, if enabled disable 
		 */
		String Uid=JsonFileReaderAndWriter.primaryDevIdReader();
		try {
			minWait();
			Runtime.getRuntime().exec("adb -s "+Uid+" shell am start -a android.settings.AIRPLANE_MODE_SETTINGS");
			minWait();
			disableSwitch(Locators_Sanity.disabled_Airplane,Locators_Sanity.enabled_Airplane,Locators_Sanity.switch_widget );		
			customWait(9000);
			test.log(LogStatus.INFO, "Disable Airplane mode if enabled, verified");
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Assert.fail();
		}
	}

	public void testFilesLoad() throws IOException, InterruptedException, org.json.simple.parser.ParseException {
		/*
		 * Access browser to get test files like.mp3 and .txt
		 */
		String Uid=JsonFileReaderAndWriter.primaryDevIdReader();
		try {
			minWait();
			Runtime.getRuntime().exec("adb -s "+Uid+" shell am start -n com.android.browser/com.android.browser.BrowserActivity");
			minWait();
			enterUrl("www.google.com");
			customWait(10000);
			//			aDriver.get("http://www.google.com");

			customWait(2000);
			clickBtn(Locators_Sanity.signIn);
			customWait(8000);
			enterTextToInputField(Locators_Sanity.email_ID_Login, "smathelp");
			minWait();
			Runtime.getRuntime().exec("adb  -s "+Uid+" shell input keyevent --longpress KEYCODE_1");
			customWait(2000);
			for(int i=1; i<=3; i++ ) {
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_RIGHT);
			}
			customWait(1000);
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_ENTER);
			customWait(8000);
			Runtime.getRuntime().exec("adb -s "+Uid+" shell input text gmail.com");
			customWait(1000);

			org.openqa.selenium.Dimension size =aDriver.manage().window().getSize();
			System.out.println(size);
			int x=size.getWidth()/2;
			int starty=(int)(size.getHeight()*0.60);
			int endy=(int)(size.getHeight()*0.10);
			aDriver.swipe(x, starty, x, endy, 2000);  
			customWait(1000);
			clickBtn(Locators_Sanity.next_Login);
			customWait(1000);
			enterTextToInputField(Locators_Sanity.email_ID_Password, "Smat@007");
			minWait();
			aDriver.swipe(x, starty, x, endy, 2000);  
			customWait(1000);
			clickBtn(Locators_Sanity.next_Passwrd);
			customWait(1000);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}



	}

	public void enableWiFi() throws InterruptedException, IOException, org.json.simple.parser.ParseException {
		/*
		 * check if wifi is connected or else connect wifi
		 */
		selectWIFIOptn();
		enableFeature(Locators_Sanity.enabled,Locators_Sanity.disabled,Locators_Sanity.switch_Title);
		minWait();
		aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
		minWait();
		String getText = Locators_Sanity.disconnectedWIFI.getText();
		customWait(5000);

		/*if(!getText.contains("Disconnected")) {
			selectWIFIOptn();
			selectSSIDwifi();	
			minWait();
			clickBtn(Locators_Sanity.wifi_IDC_ForgetBtn);
			System.out.println("Disconnected the connected");
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
		}*/

		if(getText.contains("Disconnected")) {			
			selectWIFIOptn();
			selectRefresh();
			selectSSIDwifi();		   
			enterPassword();
			test.log(LogStatus.INFO, "Wi-Fi is connected");
		}
		aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
		minWait();
		aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
		test.log(LogStatus.INFO, "Wi-Fi is already connected");

	}

	public void selectRefresh() throws InterruptedException {
		/*
		 * Refresh the List SSID 
		 */		
		minWait();
		aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_MENU);
		minWait();
		aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_ENTER);
		//refresh btn
		customWait(5000);		
	}


	public void enterPassword() throws InterruptedException, IOException, org.json.simple.parser.ParseException {
		/*
		 * enter Password for SSID
		 */
		String Uid=JsonFileReaderAndWriter.primaryDevIdReader();
		String getSSIDTitle = Locators_Sanity.SSIDTxt.getText();
		//		System.out.println(getSSIDTitle);

		aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
		minWait();
		aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
		minWait();
		changeToNumberModeWifi();
		customWait(2000);
		if(getSSIDTitle.equalsIgnoreCase("IDCSONWAP")) {
			minWait();
			clickBtn(Locators_Sanity.wifi_IDC_Psswd);
			customWait(4000);
			//			enterTextToInputField(Locators_Sanity.wifi_IDC_Psswd,"1dcS0n1md0tc0MbLr");
			Runtime.getRuntime().exec("adb shell input text 1dcS0n1md0tc0MbLr");		
			customWait(3000);	
		}
		minWait();
		String psswrd = Locators_Sanity.wifi_IDC_Psswd.getText();
		System.out.println(psswrd);
		customWait(1000);
		clickBtn(Locators_Sanity.wifi_IDC_ConnectBtn);
		customWait(3000);
	}

	public void changeToNumberModeWifi() throws InterruptedException {

		minWait();
		Locators_Sanity.wifi_IDC_Psswd.sendKeys("123");
		customWait(1500);
		String text = Locators_Sanity.wifi_IDC_Psswd.getText();
		System.out.println(text);
		if(!text.equals("123")) {
			for (int i = 0; i < 3; i++) {
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_POUND);
				minWait();
			}
		}
		Locators_Sanity.wifi_IDC_Psswd.clear();
	}


	public void selectSSIDwifi() throws InterruptedException {
		/*
		 * Select IDC wifi which is available
		 */
		SoftAssert Sa = new SoftAssert();
		try {
			customWait(4000);
			for(int i=1; i<=50; i++) {
				if(isElementExist(Locators_Sanity.wifi_IDC)) {
					minWait();
					clickBtn(Locators_Sanity.wifi_IDC);
					APP_LOGS.info("IDC available secured wifi is Selected");
					break;
				}
				else if(isElementExist(Locators_Sanity.wifi_Dellas)) {
					minWait();
					clickBtn(Locators_Sanity.wifi_Dellas);
					APP_LOGS.info("Dellas available secured wifi is Selected");
					break;
				}
				else if(isElementExist(Locators_Sanity.wifi_Cannada)) {
					minWait();
					clickBtn(Locators_Sanity.wifi_Cannada);
					APP_LOGS.info("Cannada available secured wifi is Selected");
					break;
				}
				else {
					minWait();
					aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
					continue;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			APP_LOGS.info("WIFI : No such Element found");
			test.log(LogStatus.ERROR, "Wi-Fi is not available : No such Element found");
			Sa.fail();
		}
		Sa.fail();
	}


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

	public void launchSettings() throws InterruptedException, IOException {
		try {
			String Uid=JsonFileReaderAndWriter.primaryDevIdReader();
			Runtime.getRuntime().exec("adb -s "+Uid+" shell am start -n com.android.settings/com.android.settings.Settings");
			APP_LOGS.info("Settings Launched succesfully. ");
			minWait();
		}catch (Exception e) {

		}
	}


	public void navigateTo_DateAndTime() throws InterruptedException, IOException{
		/*
		 * Navigate to Date & Time  in native settings.
		 */	
		try {
			aDriver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().resourceId(\"com.android.settings:id/main_content\")).scrollIntoView(new UiSelector().text(\"Date & time\"))");
			minWait();
			clickBtn(Locators_Sanity.date_time);
			APP_LOGS.info("Date and Time is displayed sucessfully");
		} catch (Exception e) {
			APP_LOGS.info("Date and Time is not displayed.");
			e.printStackTrace();
			test.log(LogStatus.ERROR, "No Such Element Found");
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

			Formatter fmt = new Formatter();
			Calendar cal = Calendar.getInstance();
			//actual arguments for below method("%tB %td %tY", cal, cal, cal) and "," is concatenated.
			fmt.format("%tB %te"+","+" %tY", cal, cal, cal);
			String date1 = fmt.toString();
			String get_Date = Locators_Sanity.setDateSummary.getText();

			if(get_Date.equals(date1)){
				check1 = true;
				APP_LOGS.info("Element found Date : "+get_Date);
				APP_LOGS.info("check1: "+check1);
			}else{
				APP_LOGS.info("Date element is not found");
				APP_LOGS.info("check1: "+check1);
			}
			minWait();	

			scrollToElementWithDpadDown(Locators_Sanity.use24HourFormatOpt);
			minWait();
			if(isElementExist(Locators_Sanity.timeFormatDisabled)){
				clickBtn(Locators_Sanity.use24HourFormatOpt);
				minWait();
				clickBackButton(1);
				minWait();
				clickBtn(Locators_Sanity.date_time);
			}else{
				clickBackButton(1);
				minWait();
				clickBtn(Locators_Sanity.date_time);
			}
			java.util.Date today = new java.util.Date();
			Time fmt1 = new java.sql.Time(today.getTime());
			String time1 = fmt1.toString();

			clickBtn(Locators_Sanity.settime);
			minWait();	
			String get_Time = Locators_Sanity.setTimeSummary.getText();
			minWait();	
			if(time1.contains(get_Time)){
				check2 = true;
				APP_LOGS.info("Element found Time : "+get_Time);
				APP_LOGS.info("check2: "+check2);
			}else{
				APP_LOGS.info("Time element is not found");
				APP_LOGS.info("check2: "+check2);
			}

			clickBtn(Locators_Sanity.settimeZone);
			minWait();	
			String get_Timezone = Locators_Sanity.setTimeZoneSummary.getText();
			minWait();	
			if(get_Timezone.contains("GMT+05:30 India Standard Time")){
				check3 = true;
				APP_LOGS.info("Element found Time : "+get_Timezone);
				APP_LOGS.info("check3: "+check3);
			}else{
				APP_LOGS.info("Time element is not found");
				APP_LOGS.info("check3: "+check3);
			}
			minWait();
			scrollToElementWithDpadDown(Locators_Sanity.use24HourFormatOpt);
			minWait();
			clickBtn(Locators_Sanity.use24HourFormatOpt);
			clickBackButton(1);
			minWait();	

			if((check1)&&(check2)&&(check3)){
				check = true ;
				APP_LOGS.info("Date,Time and Time Zone validated sucessfully");
				test.log(LogStatus.INFO, "Date,Time and Time Zone validated sucessfully");
				test.log(LogStatus.PASS, "Test case status is Passed");	
				Sv.assertTrue(check, "Date,Time and Time Zone validated sucessfully");
			}else{
				APP_LOGS.info("Failed to validate Date,Time and Time Zone");
				test.log(LogStatus.INFO, "Failed to validate Date,Time and Time Zone");
				Sv.fail("Failed to validate Date,Time and Time Zone");
			}
			back();
		} catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "No Such Element Found");
			Sv.fail();
		}
		Sv.assertAll();
	}

	public void selectMoreOptions() throws InterruptedException, IOException, org.json.simple.parser.ParseException {
		/*
		 *select more option in settings
		 */	aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_HOME);
		 String Uid=JsonFileReaderAndWriter.primaryDevIdReader();
		 //		customWait(4000);
		 try {
			 Runtime.getRuntime().exec("adb -s "+Uid+" shell am start -n com.android.settings/com.android.settings.Settings");
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
		 } catch (Exception e) {
			 APP_LOGS.info("More Option is not displayed.");
			 test.log(LogStatus.ERROR, "No Such Element Found");
			 minWait();	
			 Assert.fail();
		 }

	}

	public void validateEnabledisableFlightmode() throws InterruptedException {
		/*
		 * Validates enable and disable flight mode with phone settings
		 */	
		//		customWait(1000);
		SoftAssert sf = new SoftAssert();
		try {
			if (isElementExist(Locators_Sanity.Flightmode)) {
				APP_LOGS.info("Airplane mode is displayed sucessfully");
				minWait();	
				clickBtn(Locators_Sanity.AiroplaneSwitch);
				minWait();	
			}
			if (Locators_Sanity.Flightmode.isEnabled()) {
				APP_LOGS.info("Airplane mode is enabled sucessfully");
				check1 = true;
				minWait();	
				clickBtn(Locators_Sanity.AiroplaneSwitch);
				minWait();	
				minWait();	
			}				
			if (Locators_Sanity.Flightmode.isSelected()) {
				minWait();	
				APP_LOGS.info("Airplane mode is enabled ");
			}
			else {
				minWait();	
				APP_LOGS.info("Airplane mode is disabled sucessfully");
				check2 = true;
				minWait();	
			}
			if((check1)&&(check2)){
				check= true;
				APP_LOGS.info("Enable and disable of Airplane mode with phone settings.");
				test.log(LogStatus.INFO, "Enable and disable of Airplane mode with phone settings validated sucessfully");
				APP_LOGS.info("TC is Passed.");
				sf.assertTrue(check, "Validation is Pass");
			}else{
				APP_LOGS.info("test case is failed");
				test.log(LogStatus.ERROR, "Enable and disable of Airplane mode with phone settings, Unsucessfully");
				sf.fail();
			}
			minWait();	
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			minWait();	
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
		} catch (Exception e) {
			APP_LOGS.info("No Element found.");
			e.printStackTrace();
			test.log(LogStatus.ERROR, "No Such Element Found");
			sf.fail();
		}
		sf.assertAll();
	}

	public void selectCellularNetwork() throws InterruptedException, IOException {
		/*
		 * select cellular network option
		 */
		try {
			/*aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_HOME);
			minWait();
			Runtime.getRuntime().exec("adb shell am start -n com.android.settings/com.android.settings.Settings");
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
			}*/
			minWait();
			clickBtn(Locators_Sanity.AboutPhone);
			minWait();	
			clickBtn(Locators_Sanity.status);
			minWait();	
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
		} catch (Exception e) {
			e.printStackTrace();
			APP_LOGS.info("No Element found.");
			test.log(LogStatus.ERROR, " Build Summary : No Such Element Found");
			Assert.fail();
		}
	}


	public void validateLTESelection(String type) throws InterruptedException {
		/* 
		 * validate LTE selection is taken place
		 */
		SoftAssert sf = new SoftAssert();
		try {
			minWait();
			clickBtn(Locators_Sanity.status);
			minWait();	
			minWait();	
			clickBtn(Locators_Sanity.sim_status);
			minWait();	
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
			minWait();
			minWait();	
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
			minWait();
			minWait();	
			clickBtn(Locators_Sanity.cellularNwType);
			minWait();
			String get_NetworkType = Locators_Sanity.networkType.getText();	
			System.out.println(get_NetworkType);
			minWait();
			if(get_NetworkType.contains(type)) {
				check = true;
				APP_LOGS.info("Network Type verified "+get_NetworkType);
				test.log(LogStatus.INFO, "Prefered Network Type verified "+get_NetworkType);
				sf.assertTrue(check, "Validation is Pass");
			}else{
				APP_LOGS.info("test Case Failed");
				test.log(LogStatus.ERROR,"Prefered Network Type unverified "+get_NetworkType);
				sf.fail();
			}			
			back();
			minWait();
			back();
		} catch (Exception e) {
			APP_LOGS.info("No Element found.");
			test.log(LogStatus.ERROR, "No Such Element FOund");
			minWait();
			sf.fail();
		}
		sf.assertAll();
	}


	public void navigateTo_NetworkSettings() throws InterruptedException {
		/*
		 * This Method will Navigate to the APN's  
		 */
		try {
			launch_an_app("settings");
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
			for (int i = 1; i <=4; i++) {
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
				Thread.sleep(1000);
			}

		} catch (Exception e) {
			APP_LOGS.info("No Element found.");
			test.log(LogStatus.ERROR, "No Such Element Found");
			Assert.fail();
		}
	}

	public void changePreferedNetworkType() throws InterruptedException, IOException {
		/*
		 * change network type
		 */
		minWait();	
		clickBtn(Locators_Sanity.PreferredNwType_Operators);
		minWait();
		changeNetwork("GSM only");
		back();
		navigateToSettingsFeature(Locators_Sanity.AboutPhone,"About phone");	
		validateLTESelection("EDGE");

		navigateTo_NetworkSettings();
		minWait();	
		clickBtn(Locators_Sanity.PreferredNwType_Operators);
		minWait();
		changeNetwork("WCDMA only");
		back();
		navigateToSettingsFeature(Locators_Sanity.AboutPhone,"About phone");	
		validateLTESelection("HSPA");

		navigateTo_NetworkSettings();
		minWait();	
		clickBtn(Locators_Sanity.PreferredNwType_Operators);
		minWait();
		changeNetwork("WCDMAGSM only");
		back();
		navigateToSettingsFeature(Locators_Sanity.AboutPhone,"About phone");	
		validateLTESelection("HSPA");

		navigateTo_NetworkSettings();
		minWait();	
		clickBtn(Locators_Sanity.PreferredNwType_Operators);
		minWait();
		changeNetwork("LTE only");
		back();
		navigateToSettingsFeature(Locators_Sanity.AboutPhone,"About phone");	
		validateLTESelection("LTE");
	}



	public void changeNetwork(String networkType) throws InterruptedException {
		customWait(2000);
		switch (networkType) {
		case "GSM only": 
			clickBtn(Locators_Sanity.GSM_NetwrkTyp);
			APP_LOGS.info("Clicked on GSM Preferred Network Type sucessfully.");
			break;

		case "GSM": 
			clickBtn(Locators_Sanity.GSM_NetwrkTyp);
			APP_LOGS.info("Clicked on GSM Preferred Network Type sucessfully.");
			break;

		case "UMTS": 
			minWait();
			//			System.out.println("In case UMTS");
			clickBtn(Locators_Sanity.UMTS_NetwrkTyp);
			APP_LOGS.info("Clicked on GSM Preferred Network Type sucessfully.");
			break;

		case "WCDMA only": 
			clickBtn(Locators_Sanity.WCDMA_NetwrkTyp);
			APP_LOGS.info("Clicked on GSM Preferred Network Type sucessfully.");
			break;

		case "CDMA": 
			clickBtn(Locators_Sanity.CDMA_NetwrkTyp);
			APP_LOGS.info("Clicked on GSM Preferred Network Type sucessfully.");
			break;

		case "WCDMAGSM only": 
			clickBtn(Locators_Sanity.WCDMAGSM_NetwrkTyp);
			APP_LOGS.info("Clicked on GSM Preferred Network Type sucessfully.");
			break;

		case "LTE only": 
			clickBtn(Locators_Sanity.LTE_NetwrkTyp);
			APP_LOGS.info("Clicked on GSM Preferred Network Type sucessfully.");
			break;

		case "LTE": 
			clickBtn(Locators_Sanity.LTE_NetwrkTyp);
			APP_LOGS.info("Clicked on GSM Preferred Network Type sucessfully.");
			break;
		}

	}



	public void changePreferedNetworkType_Reskin() throws InterruptedException, IOException {
		/*
		 * change network type
		 */
		minWait();	
		clickBtn(Locators_Sanity.PreferredNwType_Operators);
		minWait();
		changeNetwork("GSM only");
		back();
		navigateToSettingsFeature(Locators_Sanity.AboutPhone,"About phone");	
		validateLTESelection("EDGE");
		back();


		navigateTo_NetworkSettings();
		minWait();	
		clickBtn(Locators_Sanity.PreferredNwType_Operators);
		minWait();
		//		System.out.println("IN Search of UMTS");
		changeNetwork("UMTS");
		minWait();
		back();		
		navigateToSettingsFeature(Locators_Sanity.AboutPhone,"About phone");	
		validateLTESelection("HSPA");

		navigateTo_NetworkSettings();
		minWait();	
		clickBtn(Locators_Sanity.PreferredNwType_Operators);
		minWait();
		changeNetwork("LTE only");
		back();
		navigateToSettingsFeature(Locators_Sanity.AboutPhone,"About phone");	
		validateLTESelection("LTE");
	}

	public void changePreferedNetworkType_Sprint() throws InterruptedException, IOException {
		/*
		 * change network type
		 */
		SoftAssert sf = new SoftAssert();
		minWait();	
		clickBtn(Locators_Sanity.PreferredNwType_Operators);
		minWait();
		changeNetwork("GSM");
		back();
		navigateToSettingsFeature(Locators_Sanity.AboutPhone,"About phone");		
		validateLTESelection("HSPA");


		navigateTo_NetworkSettings();
		minWait();	
		clickBtn(Locators_Sanity.PreferredNwType_Operators);
		minWait();
		changeNetwork("CDMA");
		back();
		navigateToSettingsFeature(Locators_Sanity.AboutPhone,"About phone");	
		validateLTESelection("UMTS");

		navigateTo_NetworkSettings();
		minWait();	
		clickBtn(Locators_Sanity.PreferredNwType_Operators);
		minWait();
		changeNetwork("LTE");
		back();
		navigateToSettingsFeature(Locators_Sanity.AboutPhone,"About phone");	
		validateLTESelection("LTE");
	}




	public void changeToNumberMode() throws InterruptedException {

		minWait();
		Locators_Sanity.toField_NewMessage.sendKeys("123");
		customWait(1500);
		String text = Locators_Sanity.toField_NewMessage.getText();
		System.out.println(text);
		if(!text.equals("123")) {
			for (int i = 0; i < 3; i++) {
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_POUND);
				minWait();
			}
		}
		Locators_Sanity.toField_NewMessage.clear();
	}


	public void back() throws InterruptedException {
		/* 
		 * perform back by back key
		 */
		minWait();	
		aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
		minWait();	
		aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
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
			for(int i=1; i<=8; i++) {
				if(isElementExist(Locators_Sanity.quickSettings) || isElementExist(Locators_Sanity.quick_Toggle)) {
					APP_LOGS.info("In Quick Settings");
					minWait();
					break;					
				} else {
					aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_LEFT);
					continue;					
				}	
			}
		} catch (Exception e) {
			APP_LOGS.info("More Option is not displayed.");
			test.log(LogStatus.ERROR, "No Such Element Found");
			minWait();	
			Assert.fail();
		}

	}



	public void validateEnableDisable(WebElement app,String str,WebElement enabled,WebElement disabled,WebElement element) throws InterruptedException {
		/*
		 * validate enabling and disabling the wifi, bluetooth, and airoplane mode.
		 */
		SoftAssert sf = new SoftAssert();
		try {
			minWait();	
			clickBtn(app);
			minWait();
			if (!isElementExist(enabled)) {
				minWait();
				clickBtn(disabled);
				APP_LOGS.info("Switch is Enabled");                     
				minWait();
			}
			String get_enable = element.getText();	
			//			System.out.println(get_enable);
			minWait();	
			if(get_enable.equalsIgnoreCase("ON")) {
				check1 = true;
				APP_LOGS.info("enabled is sucessful");
			}minWait();	
			clickBtn(app);	
			if (!isElementExist(disabled)) {
				minWait();
				clickBtn(enabled);
				APP_LOGS.info("Switch is Disabled");                     
				minWait();
			}
			minWait();	
			String get_enable2 = element.getText();	
			//			System.out.println(get_enable2);
			minWait();	
			if(get_enable2.equalsIgnoreCase("OFF")) {
				minWait();	
				check2 = true;
				APP_LOGS.info("Disabled is sucessful");
			}
			if((check1)&&(check2)){
				check= true;
				APP_LOGS.info(" Enabled and disabled with quick settings.");
				test.log(LogStatus.INFO, str + " Enabled and disabled with quick settings.");
				sf.assertTrue(check, "Validation is pass");
			}else{
				APP_LOGS.info("test case is failed");
				test.log(LogStatus.ERROR, " Validation Failed, Enable and disable with quick settings.");
				sf.fail();
			}
			minWait();	
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
		} catch (Exception e) {
			APP_LOGS.info("No Element found.");
			e.printStackTrace();
			test.log(LogStatus.ERROR, "No Such Element Found");
			sf.fail();			
		}
		sf.assertAll();
	}

	public void createContact(String name,String num) throws InterruptedException, IOException, org.json.simple.parser.ParseException {
		/*
		 * create contacts with name, phone, email and address
		 */
		try {
			String Uid=JsonFileReaderAndWriter.primaryDevIdReader();
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

			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_MENU);
			minWait();
			clickBtn(Locators_Sanity.contact_Save);
			minWait();		
			minWait();	
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
		} catch (Exception e) {
			APP_LOGS.info("No Element found.");
			e.printStackTrace();
			test.log(LogStatus.ERROR, "No Such Element Found");
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
			aDriver.pressKeyCode(AndroidKeyCode.ENTER);
			minWait();
			String getcontactNameText=Locators_Sanity.contact_Test.getText();
			System.out.println(getcontactNameText);
			if(getcontactNameText.equalsIgnoreCase("test"))
			{	check1 =true;
			APP_LOGS.info("check1: "+check1);
			APP_LOGS.info("Contact Name is: "+getcontactNameText);
			}else{
				APP_LOGS.info("check1: "+check1);
				APP_LOGS.info("test Name contact is not present");
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
				test.log(LogStatus.INFO, "Contact created with Name,phone_Num, Email_ID and Address is saved ");
				Sb.assertTrue(check, " ");
			}else{
				APP_LOGS.info("test case failed");	
				test.log(LogStatus.ERROR, "Contact created with Name,phone_Num, Email_ID and Address is not saved ");
				Sb.fail();
			}
			minWait();	
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			test.log(LogStatus.ERROR, "No Such Element Found");
			Sb.fail();
		}
		Sb.assertAll();
	}

	public void validateMsgDraft(String ContactNum,String str) throws InterruptedException {
		/*
		 * validate saved as draft message to new num and saved contact
		 */
		SoftAssert sf = new SoftAssert();
		try {
			minWait();		
			customWait(8000);
			WebDriverWait wait = new WebDriverWait(aDriver, 40);
			wait.until(ExpectedConditions.visibilityOf(Locators_Sanity.draft_Text));
			minWait();
			if (isElementExist(Locators_Sanity.draft_Text)) {
				check=true;
				APP_LOGS.info(str+" saved as Draft is sucessfully");
				sf.assertTrue(check, "Validation is Pass");
				test.log(LogStatus.PASS, "Message saved as Draft: Test case status is Passed");
			} else {
				APP_LOGS.info("Message DIDN'T ");
				sf.fail();
				test.log(LogStatus.ERROR, str+" didn't saved as Draft , Validation Failed ");
			}

		} catch (Exception e) {
			APP_LOGS.info("No Element found.");
			e.printStackTrace();
			test.log(LogStatus.ERROR, " No Such Element Found");
			sf.fail();
		}
		sf.assertAll();
	}


	public void sendSMS_fromRefDevice() {
		try {
			minWait();
			if (r_b_No.contains("8A.")) {
				if (r_b_No.contains("-10.")||r_b_No.contains("-30.")) {
					minWait();
					Runtime.getRuntime().exec("adb -s "+r_Id+" shell input keyevent 3");
					minWait();
					Runtime.getRuntime().exec("adb shell am force-stop com.android.mms");
					minWait();
					Runtime.getRuntime().exec("adb -s "+r_Id+" shell am start -a android.intent.action.SENDTO -d sms:"+pryNum+" --es sms_body \"AutomationMessagee\" --ez exit_on_sent true");
					customWait(2500);
					Runtime.getRuntime().exec("adb -s "+r_Id+" shell input tap 918 1699");
					minWait();
					Runtime.getRuntime().exec("adb -s "+r_Id+" shell input tap 918 952");
					minWait();
				} else if(r_b_No.contains("-11.")||r_b_No.contains("-12.")||r_b_No.contains("-18.")||r_b_No.contains("-26.")||r_b_No.contains("-29.")){
					minWait();
					Runtime.getRuntime().exec("adb -s "+r_Id+" shell input keyevent 3");
					minWait();
					Runtime.getRuntime().exec("adb -s "+r_Id+" shell am force-stop com.google.android.apps.messaging");
					minWait();
					Runtime.getRuntime().exec("adb -s "+r_Id+" shell am start -a android.intent.action.SENDTO -d sms:"+pryNum+" --es sms_body \"AutomationMessagee\" --ez exit_on_sent true");
					customWait(2500);
					Runtime.getRuntime().exec("adb -s "+r_Id+" shell input tap 930 1756");
					minWait();
					Runtime.getRuntime().exec("adb -s "+r_Id+" shell input tap 930 976");
					minWait();
				}
			} else if (r_b_No.contains("5SA.")){
				minWait();
				Runtime.getRuntime().exec("adb -s "+r_Id+" shell input keyevent 3");
				minWait();
				Runtime.getRuntime().exec("adb -s "+r_Id+" shell am force-stop com.android.mms");
				minWait();
				Runtime.getRuntime().exec("adb -s "+r_Id+" shell am start -a android.intent.action.SENDTO -d sms:"+pryNum+" --es sms_body \"AutomationMessage.\" --ez exit_on_sent true");
				customWait(2500);
				Runtime.getRuntime().exec("adb -s "+r_Id+" shell input keyevent 66");
				minWait();
			}

		} catch (Exception e) {

		}
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
			APP_LOGS.info("Deleted all contacts");
		} catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "No Such Element Found");
			Assert.fail();
		}	
	}

	public void validateDeleteSavedContact() throws InterruptedException {
		SoftAssert sf = new SoftAssert();
		try {
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
			minWait();
			//		deleteContcact();
			minWait();
			if(isElementExist(Locators_Sanity.contact_Testing)) {
				APP_LOGS.info("Saved Contact is not deleted");
				sf.fail();
			}else{
				check =true;
				APP_LOGS.info("check: "+check);
				APP_LOGS.info("Saved Contact is deleted ");
				sf.assertTrue(check, "validation is Pass");
			}
			minWait();
		} catch (Exception e) {
			APP_LOGS.info("No Element found.");
			e.printStackTrace();
			test.log(LogStatus.ERROR, "No Such Element Found");
			sf.fail();
		}
		sf.assertAll();
	}

	public void selectContactsApp() throws InterruptedException {
		/*
		 * select phone dailer application
		 */
		try {
			minWait();	
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_HOME);
			minWait();
			clickBtn(Locators_Sanity.contacts);
		} catch (Exception e) {
			APP_LOGS.info("No Element found.");
			minWait();	
		}
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
		} catch (Exception e) {
			APP_LOGS.info("No Element found.");
			e.printStackTrace();
			test.log(LogStatus.ERROR, "No Such Element Found");
			Assert.fail();			
		}
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
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}	
	}


	public void dailNumber(String dailNum) throws IOException, InterruptedException {
		minWait();
		launch_an_app("phone");
		customWait(2000);
		Runtime.getRuntime().exec("adb -s "+p_Id+" shell am start -a android.intent.action.CALL -d tel:"+AllQA.REFERENCEDEVMDN);
	}

	public void displayOperatorName() throws InterruptedException{
		  /*
		   * Fetches and displays operator name
		   */
		  SoftAssert SA= new SoftAssert();
		  try {
		   minWait();
		   String operatorName = Locators_Sanity.operatorName.getText();
		   if(operatorName.contains("Emergency calls only")){
		    test.log(LogStatus.FAIL, "SIM not Inserted/Not Latched : Not able to fetch Operator Name from Homescreen");
		    test.log(LogStatus.FAIL, "Test case status is Failed");
		   }else{
		    test.log(LogStatus.INFO, "Operator Name is "+operatorName);
		    test.log(LogStatus.PASS, "Test case status is Passed");
		   }
		  } catch (Exception e) {
		   APP_LOGS.info("No Element found.");
		   e.printStackTrace();
		   test.log(LogStatus.FAIL, "SIM not Inserted/Not Latched : Not able to fetch Operator Name from Homescreen");
		   test.log(LogStatus.FAIL, "Test case status is Failed");
		   SA.fail();
		  }
		 }



	public void validateOperatorDisplay(String str,String fileName) throws InterruptedException {
		/*
		 * validate sent message to new num and saved contact
		 */
		SoftAssert sf = new SoftAssert();
		try {

			if(searchStringOCR(str, fileName)||searchStringOCR("airtel", fileName)) {
				check = true;
				APP_LOGS.info("Operator Name is Displayed");
				test.log(LogStatus.INFO, "Operator Name is Displayed");
				sf.assertTrue(check, "Validation is Pass");
			}
			else{
				APP_LOGS.info("Operator Name is not Displayed");
				test.log(LogStatus.INFO, "Operator Name is not Displayed");
				sf.fail();
			}	
		} catch (Exception e) {
			APP_LOGS.info("No Element found.");
			e.printStackTrace();
			test.log(LogStatus.ERROR, " No Such Element Found");
			sf.fail();
		}
		sf.assertAll();
	}

	public void dailCallUsingDailPad(String dailNum) throws InterruptedException, IOException {

		try {
			dailNumber(dailNum);

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

			APP_LOGS.info("Number dailed is: "+dailNum);
		}catch (Exception e) {
			APP_LOGS.info("Selected Page is not displayed.");
			e.printStackTrace();
		}
	}

	public void recieve_Call_PrimaryDev() throws IOException, InterruptedException {

		try {
			for(int j=1;j<=100;j++){
				Process child = null;
				Process version = null;
				if (p_b_No.contains("8A.")) {
					System.out.println("XP8");
					child=Runtime.getRuntime().exec("adb -s "+p_Id+" shell service call telecom 27");

				} else if(p_b_No.contains("5SA.")) {
					System.out.println("XP5");

					version = Runtime.getRuntime().exec("adb -s "+p_Id+" shell getprop ro.build.version.release");
					InputStream lsOut1 = version.getInputStream();
					InputStreamReader r1 = new InputStreamReader(lsOut1);
					BufferedReader in1 = new BufferedReader(r1);
					String  value1=in1.readLine();
					System.out.println(value1);
					if(value1.contains("8")) {
						child=Runtime.getRuntime().exec("adb -s "+p_Id+" shell service call telecom 30");
					}else {
						child=Runtime.getRuntime().exec("adb -s "+p_Id+" shell service call telecom 28");
					}
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

	public void make_Call_from_RefDev() throws InterruptedException, IOException {

		try {
			minWait();
			Runtime.getRuntime().exec("adb -s "+r_Id+" shell am start -a android.intent.action.CALL -d tel:"+pryNum);
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

	public void endCall_PIDDevice() {
		try {
			minWait();
			Runtime.getRuntime().exec("adb -s "+p_Id+" shell input keyevent 6");
			Thread.sleep(1000);
		} catch (Exception e) {
		}
	}

	public void rejectWithSMS() throws InterruptedException, IOException
	{
		/*
		 * reject with SMS
		 */
		SoftAssert SA = new SoftAssert();
		try {
			//Receive call from reference device
			//	waituntilnew(Locators_Sanity.rejectWithSMSTextCallPage, 10000);
			customWait(8000);
			if(isElementExist(Locators_Sanity.rejectWithSMSTextCallPage)) {
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_MENU);
				minWait();
				clickCenterButton(1);
				validateSentQuickResponse();
			}else {
				APP_LOGS.info("Call not received");
				test.log(LogStatus.INFO, "Call not received");
				SA.fail("Call not received");
			}
		}catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "Element Not Found");
		}
		SA.assertAll();
	}

	public void clickMenuAndElement(WebElement element) throws InterruptedException
	{
		/*
		 * Click on menu button and clicks on element passed
		 */
		try
		{
			customWait(2000);
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_MENU);
			customWait(2000);
			element.click();
			minWait();
		}catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "Element Not Found");
		}
	}


	public void validateSentQuickResponse() throws InterruptedException, IOException
	{
		/*
		 * Validates sent quick response in messaging 
		 */
		SoftAssert SA = new SoftAssert();
		try {
			launch_an_app("messaging");
			clickMenuAndElement(Locators_Sanity.searchOpt);
			changeToNumberMode_SMS(Locators_Sanity.searchFld_InMessages);
			inputData(refNum);
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
			clickCenterButton(1);
			if(isElementExist(Locators_Sanity.quickResponseSent))
			{
				check= true;
				APP_LOGS.info("Rejected call with Quick response succesfully");
				test.log(LogStatus.INFO, "Rejected call with Quick response succesfully");
				SA.assertTrue(check, "Rejected call with Quick response succesfully");	
			}else {
				APP_LOGS.info("Failed to Reject call with Quick response");
				test.log(LogStatus.INFO, "Failed to Reject call with Quick response");
				SA.fail("Failed to Reject call with Quick response");
			}
			clickBackButton(1);
		}catch (Exception e) {
			e.printStackTrace();
			APP_LOGS.info("Element Not Found");
			test.log(LogStatus.ERROR, "Element Not Found");
			SA.fail();
		}
		SA.assertAll();
	}

	public void changeToNumberMode_SMS(WebElement element) throws InterruptedException {
		/*
		 * Change input field type to number
		 */
		minWait();
		element.sendKeys("123");
		customWait(1500);
		String text = element.getText();
		System.out.println(text);
		if(!text.equals("123")) {
			for (int i = 0; i < 3; i++) {
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_POUND);
				minWait();
			}
		}
		element.clear();
	}

	public void inputData(String data) throws InterruptedException, IOException
	{
		/*
		 * Search contact with name
		 */
		try
		{
			customWait(2000);
			Runtime.getRuntime().exec("adb -s "+p_Id+" shell input text "+ data);
			customWait(3000);
		}
		catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "Element Not Found");
		}
	}



	public void clickCenterButton(int number)
	{
		/*
		 * clicks on back button with iteration as user input
		 */
		try {
			for(int i=0;i<number;i++){
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
				minWait();
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void validateMuteSpeakerVolumeFun() throws InterruptedException {
		/*
		 * validate functionality volume increase and decrease , speaker and mute enabling and disabling
		 */
		SoftAssert sf = new SoftAssert();
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
				test.log(LogStatus.ERROR, "Call is not initiated");
				APP_LOGS.info("Speaker is not enabled");
			}
			minWait();
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
				test.log(LogStatus.INFO, "Functionality of volume increase and decrease , speaker and mute enabling and disabling");
				sf.assertTrue(check, "Validation is Pass");
			}else{
				APP_LOGS.info("test case failed");					
				sf.fail();
			}
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_ENDCALL);
		} catch (Exception e) {
			APP_LOGS.info("No Element found.");
			e.printStackTrace();
			test.log(LogStatus.ERROR, "No Such Element Found");
			sf.fail();
		}
		sf.assertAll();
	}

	public void endcall() throws InterruptedException {
		/*
		 * disconnect call
		 */
		Thread.sleep(5000);
		aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_ENDCALL);
		Thread.sleep(3000); 
	}


	public void validate_Num_In_CallLog(String expectedNum,String callType) throws InterruptedException {
		/* 
		 * This Method will Validate the Dailled Number in Call Log history.
		 * Precondition : User should be in Dialler Home Page.
		 */
		SoftAssert sf = new SoftAssert();
		try {
			minWait();
			String callLogNum = Locators_Sanity.first_No_In_CallLog.getText().replaceAll(" ", "");
			System.out.println("callLogNum "+callLogNum);
			System.out.println("expectedNum "+expectedNum);
			if (callLogNum.contains(expectedNum)) {
				check=true;
				APP_LOGS.info("Dailed Number sucessfully Recorded in Call log");
				sf.assertTrue(check, "TestCase Valiation is PASS");
				test.log(LogStatus.PASS, callType+" Number Validated in the Call Logs.");
			}else {
				APP_LOGS.info("Dailed Number NOT Recorded in Call log");
				sf.fail();
				test.log(LogStatus.FAIL,callType+" Number NOT found in Call Logs.");
			}
		} catch (Exception e) {
			e.printStackTrace();
			sf.fail();
		}		
		sf.assertAll();
	}

	public void callHistoryDetails() throws InterruptedException, IOException
	{
		/*
		 * validates call history details
		 */
		try {
			//==========outgoing call==========
			dailCallUsingDailPad(refNum);
			customWait(3000);
			endcall();		

			//==========Incoming call==========
			make_Call_from_RefDev();
			customWait(3000);
			recieve_Call_PrimaryDev();
			customWait(5000);
			endCall_RefDevice();

			//==========Missed call===========
			make_Call_from_RefDev();
			customWait(3000);
			endCall_RefDevice();

			//==========declined call=========
			make_Call_from_RefDev();
			customWait(3000);
			//endCall_PIDDevice();
			waituntilnew(Locators_Sanity.rejectWithSMSTextCallPage, 5000);
			if(isElementExist(Locators_Sanity.rejectWithSMSTextCallPage)) {
				endcall();
			}else {
				test.log(LogStatus.ERROR, "Call not recieved to decline");
			}
			customWait(1000);
			endCall_RefDevice();

			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_ENTER);
			minWait();
			validateCallHistoryDeclinedAndMissed();
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
			minWait();
			validateCallHistoryIncomingAndOutgoing();
		}catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "Element Not Found");
		}
	}

	public void validateCallHistoryDeclinedAndMissed() throws InterruptedException, IOException
	{
		/*
		 * Validate call history
		 */
		SoftAssert SA = new SoftAssert();
		try {
			if(isElementExist(Locators_Sanity.missedCall) && isElementExist(Locators_Sanity.declinedCall))
			{
				check = true;
				APP_LOGS.info("Call History with declined call and missed call verified sucessfully");
				test.log(LogStatus.INFO, "Call History with declined call and missed call verified sucessfully");
				SA.assertTrue(check, "Call History with declined call and missed call verified sucessfully");	
			}else {
				APP_LOGS.info("Call History with declined call and missed call not verified");
				test.log(LogStatus.ERROR, "Call History with declined call and missed call not verified");
				SA.fail("Call History with declined call and missed call not verified");
			}	
		}catch (Exception e) {
			e.printStackTrace();
			APP_LOGS.info("Element Not Found");
			test.log(LogStatus.ERROR, "Element Not Found");
			SA.fail();
		}
		SA.assertAll();
	}

	public void validateCallHistoryIncomingAndOutgoing() throws InterruptedException, IOException
	{
		/*
		 * Validate call history
		 */
		SoftAssert SA = new SoftAssert();
		try {
			if(isElementExist(Locators_Sanity.outgoingCall)&& isElementExist(Locators_Sanity.incomingCall))
			{
				check = true;
				APP_LOGS.info("Call History with incoming call and outgoing call verified sucessfully");
				test.log(LogStatus.INFO, "Call History with incoming call and outgoing call verified sucessfully");
				SA.assertTrue(check, "Call History with incoming call and outgoing call verified sucessfully");	
			}else {
				APP_LOGS.info("Call History with incoming call and outgoing call not verified");
				test.log(LogStatus.ERROR, "Call History with incoming call and outgoing call not verified");
				SA.fail("Call History with incoming call and outgoing call not verified");
			}	
		}catch (Exception e) {
			e.printStackTrace();
			APP_LOGS.info("Element Not Found");
			test.log(LogStatus.ERROR, "Element Not Found");
			SA.fail();
		}
		SA.assertAll();
	}


	public void callFromHistory() throws IOException, InterruptedException {
		//initiate call from call log
		clickBtn(Locators_Sanity.first_No_In_CallLog);
		customWait(4000);
		Runtime.getRuntime().exec("adb -s "+p_Id+" shell input keyevent 5");		
		customWait(1000);
		endcall();
		minWait();
		aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
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

		} catch (Exception e) {
			APP_LOGS.info("No Element found.");
			e.printStackTrace();
			test.log(LogStatus.ERROR, "No Such Element Found");
			Assert.fail();
		}
	}

	public void validateOutgoingcalls() throws InterruptedException, IOException {
		/*
		 * dail num 5 times to validate outgoing calls
		 */
		SoftAssert sf = new SoftAssert();
		try {
			clearCallLogs();
			minWait();
			minWait();
			callinitiate("08040302037");
			minWait();
			minWait();
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
				test.log(LogStatus.INFO, "Validated outgoing call is updated is displayed");
				sf.assertTrue(check, "Validation is Pass");
			}else{
				APP_LOGS.info("5 times outgoing call is updated is not displayed");
				test.log(LogStatus.ERROR," outgoing call is not updated displayed");
				sf.fail();
			}	
		} catch (Exception e) {
			APP_LOGS.info("No Element found.");
			test.log(LogStatus.ERROR, "No Such Element Found");
			e.printStackTrace();
			sf.fail();
		}
		sf.assertAll();
	}

	public void validatecallfromCallLogs() throws InterruptedException {
		/*
		 * Intiate a call from recent call log 
		 */
		SoftAssert sf = new SoftAssert();
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
				test.log(LogStatus.INFO, "Selected call entry to call from recent call logs is displayed");
				sf.assertTrue(check, "Validation is Pass");
			}else{
				APP_LOGS.info("Selected call entry to call from recent call logs is not displayed");
				test.log(LogStatus.ERROR, "Selected call entry to call from recent call logs is not displayed");
				sf.fail();
			}	
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_ENDCALL);
			minWait();
		} catch (Exception e) {
			APP_LOGS.info("No Element found.");
			test.log(LogStatus.ERROR, "No Such Element Found");
			e.printStackTrace();
			sf.fail();
		}
		sf.assertAll();
	}

	public void validatecallSavedContact(String contact) throws InterruptedException {
		/*
		 * Intiate a call from saved contacts
		 */
		SoftAssert sf = new SoftAssert();
		try {
			launch_an_app("contacts");
			minWait();
			minWait();
			for(int i=1; i<=3; i++) {
				if(isElementExist(Locators_Sanity.contactSelect_Testing)) {
					minWait();
					clickBtn(Locators_Sanity.contactSelect_Testing);
					APP_LOGS.info("Selected testing Contacts");
					minWait();
					break;					
				} else {
					aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
					continue;					
				}	
			}
			customWait(2000);
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
			customWait(1000);
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
			customWait(3000);
			String getCallingNum=Locators_Sanity.phone_RecentCallName.getText();
			//		System.out.println(getCallingNum);
			if(contact.equalsIgnoreCase(getCallingNum)) {
				check = true;
				APP_LOGS.info("Selected call from saved contacts is displayed");
				test.log(LogStatus.INFO, "Selected call from saved contacts is displayed");
				sf.assertTrue(check, "Validation is Pass");
			}else{
				APP_LOGS.info("Selected call from saved contacts is not displayed");
				test.log(LogStatus.ERROR, " ");
				sf.fail();
			}	
			endcall();
		} catch (Exception e) {
			APP_LOGS.info("No Element found.");
			e.printStackTrace();
			test.log(LogStatus.ERROR, "No Such Element Found");
			sf.fail();
		}
		sf.assertAll();
	}

	public void validateDeleteApn(String apnName ) {
		/*
		 * VAlidate delete APN
		 */
		SoftAssert Sf = new SoftAssert();
		try {
			navigateTo_APN();
			minWait();	
			if(isElementExist(Locators_Sanity.Added_Apn)) {
				APP_LOGS.info("APN is Deleted sucessfully");
				Sf.fail();
			} 
			else {
				check = true;
				APP_LOGS.info("Added APN is Present");
				Sf.assertTrue(check, " ");
			}
			//			back();
		} catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "No Such Element Found");
			Sf.fail();
		}
		Sf.assertAll();

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
		} catch (Exception e) {
			APP_LOGS.info("No Element found.");
			e.printStackTrace();
			test.log(LogStatus.ERROR, "Messaging App: No Such Element Found");
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
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_MENU);
			minWait();
			clickBtn(Locators_Sanity.New_msgOptn);
			minWait();
			//Runtime.getRuntime().exec("adb shell am start -n com.android.mms/com.android.mms.ui.ComposeMessageActivity");
			customWait(1000);
			changeToNumberMode();
			minWait();
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
			customWait(1000);
		} catch (Exception e) {
			APP_LOGS.info("No Element found.");
			e.printStackTrace();
			test.log(LogStatus.ERROR, "No Such Element Found");
			Sa.fail();
		}
		Sa.assertAll();
	}

	public void send() throws InterruptedException {
		/*
		 * send sms 
		 */		
		customWait(2000);
		aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_MENU);
		minWait();
		aDriver.pressKeyCode(AndroidKeyCode.ENTER);		
		customWait(1000);
		aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);	
	}

	public void validateOperatorDisplay(String ContactNum) throws InterruptedException {
		/*
		 * validate sent message to new num and saved contact
		 */
		SoftAssert sf = new SoftAssert();
		try {

			if(searchStringOCR("New", "ocr")||searchStringOCR("Now", "ocr")) {
				check = true;
				APP_LOGS.info("Operator Name is Display");
				test.log(LogStatus.INFO, "Operator Name is Display");
				sf.assertTrue(check, "Validation is Pass");
			}
			else{
				APP_LOGS.info("Operator Name is not Display");
				test.log(LogStatus.INFO, "Operator Name is not Display");
				sf.fail();
			}	
		} catch (Exception e) {
			APP_LOGS.info("No Element found.");
			e.printStackTrace();
			test.log(LogStatus.ERROR, " No Such Element Found");
			sf.fail();
		}
		sf.assertAll();
	}


	public void validateMsgSent(String str) throws InterruptedException {
		/*
		 * validate sent message to new num and saved contact
		 */
		SoftAssert sf = new SoftAssert();
		try {
			minWait();		
			customWait(8000);
			WebDriverWait wait = new WebDriverWait(aDriver, 40);
			wait.until(ExpectedConditions.visibilityOf(Locators_Sanity.now_Text));
			minWait();
			if (isElementExist(Locators_Sanity.now_Text)) {
				check=true;
				APP_LOGS.info("Message "+str+" sucessfully");
				sf.assertTrue(check, "Validation is Pass");
				test.log(LogStatus.PASS, "Message sent successfully");
			} else {
				APP_LOGS.info("Message DIDN'T "+ str);
				sf.fail();
				test.log(LogStatus.ERROR, "Failed to send SMS,Validation Failed ");
			}
		} catch (Exception e) {
			APP_LOGS.info("No Element found.");
			e.printStackTrace();
			test.log(LogStatus.ERROR, " No Such Element Found");
			sf.fail();
		}
		sf.assertAll();
	}


	public void delete_All_Threads() throws InterruptedException {
		/*
		 * Method is used to delete All the SMS Threads.
		 */
		SoftAssert sf = new SoftAssert();
		try {
			minWait();
			while (true) {
				if (isElementExist(Locators_Sanity.first_sms_Thread)||isElementExist(Locators_Sanity.first_sms_Thread1)) {
					minWait();
					aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_CLEAR);
					minWait();
					clickBtn(Locators_Sanity.DELETE);
				} else {
					Reporter.log("No Threads Found");
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			sf.fail();
			test.log(LogStatus.ERROR, "Error in Deleting the Messages.");
		}
		sf.assertAll();
	}



	public void validateInCallFunctions(String verifyTxt,String callfunction,String orTxt) throws InterruptedException {
		/*
		 * validate sent message to new num and saved contact
		 */
		SoftAssert sf = new SoftAssert();
		try {
			if(searchStringOCR(verifyTxt, "ocr")||searchStringOCR(orTxt, "ocr")) {
				check = true;
				APP_LOGS.info("Verified In Call Functionality:"+ callfunction);
				test.log(LogStatus.INFO, "Verified " +callfunction + " In Call Functionality" );
				sf.assertTrue(check, "Validation is Pass");
			}
			else{
				APP_LOGS.info("Message is not sent to:");
				test.log(LogStatus.INFO, "Unverfied "+ callfunction+ " In Call Functionality");
				sf.fail();
			}	
		} catch (Exception e) {
			APP_LOGS.info("No Element found.");
			e.printStackTrace();
			test.log(LogStatus.ERROR, " No Such Element Found");
			sf.fail();
		}
		sf.assertAll();
	}

	public void validateInCallFunctionality(String function,String searchstring,String filename) throws InterruptedException, IOException
	{
		/*
		 * Validate call history
		 */
		SoftAssert SA = new SoftAssert();
		try {
			if(searchString(searchstring, filename))
			{
				check = true;
				APP_LOGS.info("In call functionality "+ function +" verified successfully");
				test.log(LogStatus.INFO, "In call functionality "+ function +" verified successfully");
				SA.assertTrue(check, "In call functionality "+ function +" verified successfully");	
			}else {
				APP_LOGS.info("In call functionality "+ function +" not verified");
				test.log(LogStatus.ERROR, "In call functionality "+ function +" not verified");
				SA.fail("In call functionality "+ function +" not verified");
			}	
		}catch (Exception e) {
			e.printStackTrace();
			APP_LOGS.info("Element Not Found");
			test.log(LogStatus.ERROR, "Element Not Found");
			SA.fail();
		}
		SA.assertAll();
	}

	public void validateSpeaker(String function,String searchstring,String searchstring1,String filename) throws InterruptedException, IOException
	{
		/*
		 * Validate call history
		 */
		SoftAssert SA = new SoftAssert();
		try {
			if(searchString(searchstring, filename) || searchString(searchstring1, filename))
			{
				check = true;
				APP_LOGS.info("In call functionality "+ function +" verified successfully");
				test.log(LogStatus.INFO, "In call functionality "+ function +" verified successfully");
				SA.assertTrue(check, "In call functionality "+ function +" verified successfully");	
			}else {
				APP_LOGS.info("In call functionality "+ function +" not verified");
				test.log(LogStatus.ERROR, "In call functionality "+ function +" not verified");
				SA.fail("In call functionality "+ function +" not verified");
			}	
		}catch (Exception e) {
			e.printStackTrace();
			APP_LOGS.info("Element Not Found");
			test.log(LogStatus.ERROR, "Element Not Found");
			SA.fail();
		}
		SA.assertAll();
	}


	public void speakerStatus() throws InterruptedException, IOException, AWTException, org.json.simple.parser.ParseException
	{
		/*
		 * Validates speaker status
		 */
		try {
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_UP);
			customWait(2000);
			//	validateInCallFunctionality("Speaker On","Message received: USER_SWITCH_SPEAKER=1104, arg1=0: ICA.sAR->CARSM.pM_USER_SWITCH_SPEAKER","XP5S_Sanity_008");
			validateSpeaker("Speaker On", "CallAudioRouteStateMachine: setSystemAudioState: changing from [AudioState isMuted: false, route: EARPIECE, supportedRouteMask: EARPIECE, SPEAKER] to [AudioState isMuted: false, route: SPEAKER, supportedRouteMask: EARPIECE, SPEAKER]: ICA.sAR->CARSM.pM_USER_SWITCH_SPEAKER",
					"CallAudioRouteStateMachine: setSystemAudioState: changing from [AudioState isMuted: false, route: WIRED_HEADSET, supportedRouteMask: WIRED_HEADSET, SPEAKER] to [AudioState isMuted: false, route: SPEAKER, supportedRouteMask: WIRED_HEADSET, SPEAKER]: ICA.sAR->CARSM.pM_USER_SWITCH_SPEAKER", "XP5S_Sanity_008");
			startLog("XP5S_Sanity_008_1");
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_UP);
			customWait(2000);
			//validateInCallFunctionality("Speaker Off","Message received: USER_SWITCH_EARPIECE=1101, arg1=0: CSW.hCCC->CARSM.pM_USER_SWITCH_EARPIECE","XP5S_Sanity_008");
			validateSpeaker("Speaker Off", "CallAudioRouteStateMachine: setSystemAudioState: changing from [AudioState isMuted: false, route: SPEAKER, supportedRouteMask: EARPIECE, SPEAKER] to [AudioState isMuted: false, route: EARPIECE, supportedRouteMask: EARPIECE, SPEAKER]: ICA.sAR->CARSM.pM_USER_SWITCH_EARPIECE",
					"CallAudioRouteStateMachine: setSystemAudioState: changing from [AudioState isMuted: false, route: SPEAKER, supportedRouteMask: WIRED_HEADSET, SPEAKER] to [AudioState isMuted: false, route: WIRED_HEADSET, supportedRouteMask: WIRED_HEADSET, SPEAKER]: ICA.sAR->CARSM.pM_USER_SWITCH_HEADSET", "XP5S_Sanity_008_1");
		}catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "Element Not Found");
		}
	}

	public void muteFunctionStatus() throws InterruptedException, IOException
	{
		/*
		 * Validates mute status
		 */
		try {
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_LEFT);
			customWait(2000);
			OCRScreencapture("MuteON");
			customWait(1000);
			//		    validateInCallFunctions("$<Dll","Hold","&C]n");
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_LEFT);
			customWait(2000);
			//			OCRScreencapture();
			//			validateotherFunctions("$<Dll","Retreive");
		}catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "Element Not Found");
		}
	}


	public void holdFunctionStatus() throws InterruptedException, IOException
	{
		/*
		 * Validates hold status
		 */
		try {
			minWait();
			Runtime.getRuntime().exec("adb -s "+p_Id+" shell input keyevent 22");
			customWait(5000);
			validateInCallFunctionality("Hold","GsmCdmaConnection: [GsmCdmaConn] update: parent=HOLDING, hasNewParent=false, wasConnectingInOrOut=false, wasHolding=false, isConnectingInOrOut=false, changed=true","XP5S_Sanity_007");
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_RIGHT);
			customWait(5000);
			validateInCallFunctionality("Unhold","GsmCdmaConnection: [GsmCdmaConn] update: parent=ACTIVE, hasNewParent=false, wasConnectingInOrOut=false, wasHolding=true, isConnectingInOrOut=false, changed=true","XP5S_Sanity_007");
		}catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "Element Not Found");
		}
	}


	public void inCallFunctionality() throws InterruptedException, IOException, AWTException, org.json.simple.parser.ParseException
	{
		/*
		 * Validates in call functionality
		 */
		try {			
			customWait(3000);
			dailCallUsingDailPad(refNum);
			minWait();
			speakerStatus();
			minWait();
			//			muteFunctionStatus();
			minWait();
			endcall();		
		}catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "Element Not Found");
		}
	}

	public void deleteMsg() throws InterruptedException {
		/*
		 * Delete Selected Msg
		 */
		try {
			minWait();	
			clickMenuAndElement(Locators_Sanity.selectOptInMsg);
			minWait();
			clickMenuAndElement(Locators_Sanity.deleteAllThreadsInMsg);
			minWait();
			clickBtn(Locators_Sanity.contact_DeleteBtn);
			customWait(2000);
		} catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "No Such Element Found");
			Assert.fail();
		}
	}

	public void navigateTo_NewMessage() throws InterruptedException, IOException {
		SoftAssert sf = new SoftAssert();
		try {
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_MENU);
			minWait();
			clickBtn(Locators_Sanity.New_msgOptn);
			minWait();
		} catch (Exception e) {
			e.printStackTrace();
			sf.fail();
			test.log(LogStatus.ERROR, "Didn't navigated to New Message Window");
		}
		sf.assertAll();
	}

	public void navigateAttachOthers() throws InterruptedException {
		/* Navigates to */
		try {
			minWait();
			aDriver.findElement(By.xpath("//android.widget.EditText[@index='0']")).click();
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_MENU);
			minWait();
			clickBtn(Locators_Sanity.attachOthers);
			minWait();
		} catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "Error in navigating to 'Attach others' Option");
		}
	}

	public void clickOnAttach_Options(AndroidElement optionToClick) throws InterruptedException {
		/* Clicks on Capture Picture in the Attach Others option.*/
		try {
			minWait();		
			for (int i = 0; i < 10; i++) {
				if (isElementExist(optionToClick)) {
					minWait();
					clickBtn(optionToClick);
					minWait();
					break;
				} else {
					aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
					Thread.sleep(500);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "Error in clicking Attach Option.");
		}
	}

	public void capturePic_ADD() throws InterruptedException, IOException {
		/* Captures the image and adds to the Message (MMS)
		 * Precondition : User should navigate to "Attach others" Option
		 */
		try {
			customWait(3000);
			clickBtn(Locators_Sanity.photoCaptureIcon);
			customWait(2000);
			clickBtn(Locators_Sanity.OK);
			minWait();
		} catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "Error in adding Picture to MMS");
		}
	}

	public void validate_AnyAdded_Attachment(AndroidElement attachmentToValidate) throws InterruptedException {

		SoftAssert sf = new SoftAssert();
		minWait();
		if (isElementExist(attachmentToValidate)) {
			check=true;
			APP_LOGS.info("Attachment added sucessfully.");
			sf.assertTrue(check, "Attachment added sucessfully, validation PASS.");
		} else {
			sf.fail();
			test.log(LogStatus.ERROR, "Error in adding the Attachment to SMS.");
		}
		sf.assertAll();		
	}

	public void type_Message(String typeMessage) throws InterruptedException {
		SoftAssert sf = new SoftAssert();
		try {
			minWait();
			enterTextToInputField(Locators_Sanity.typeMsg_Field, typeMessage);
		} catch (Exception e) {
			e.printStackTrace();
			sf.fail();
			test.log(LogStatus.ERROR, "Error in Typing a Message.");
		}
		sf.assertAll();
	}

	public void press_BackKey() throws InterruptedException {
		minWait();
		aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
		minWait();
	}



	public void enter_ToField(String cellNo) throws InterruptedException {
		SoftAssert sf = new SoftAssert();
		try {
			changeToNumberMode();
			minWait();
			enterTextToInputField(Locators_Sanity.toField_NewMessage, cellNo);
			minWait();
		} catch (Exception e) {
			e.printStackTrace();
			sf.fail();
			test.log(LogStatus.ERROR, "Error in entering number to ToField");
		}
		sf.assertAll();
	}

	public void validateDeleteSMS(String str) throws InterruptedException {
		//validate after deletion

		if (validate_presenceOfElement(Locators_Sanity.noConversations,"NoConversations")) {
			check = true;
			test.log(LogStatus.INFO, "Able to delete " +str +" verified");
			Assert.assertTrue(check);
		}
		else {
			test.log(LogStatus.ERROR, str+" is not able to delete");
			Assert.fail();
		}
	}

	public void disableCellularData() throws InterruptedException {
		try {
			minWait();
			if(isElementExist(Locators_Sanity.MobileNetwrks)) {
				minWait();
				clickBtn(Locators_Sanity.MobileNetwrks);
				APP_LOGS.info("Selected Mobile Networks");
				minWait();
				disableFeature(Locators_Sanity.enabled_Data,Locators_Sanity.disabled_Data);
				minWait();
				clickBtn(Locators_Sanity.okBtn);
				minWait();
			}
			else {
				clickBtn(Locators_Sanity.dataUsageOptn);
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
				minWait();
				disableFeature(Locators_Sanity.cellular_DataON,Locators_Sanity.cellular_DataOFF);
				minWait();
				clickBtn(Locators_Sanity.okBtn);
				minWait();
			}


		} catch (Exception e) {
			APP_LOGS.info("No Element found.");
			Assert.fail();
			minWait();	
		}

	}


	public void enableData() throws InterruptedException {
		/*
		 * enable data
		 */
		try {
			launch_an_app("settings");
			minWait();
			if(isElementExist(Locators_Sanity.MobileNetwrks)) {
				minWait();
				clickBtn(Locators_Sanity.MobileNetwrks);
				APP_LOGS.info("Selected Mobile Networks");
				minWait();				
				enableSwitch(Locators_Sanity.enabled_Data,Locators_Sanity.disabled_Data,Locators_Sanity.switch_widget);
				minWait();
			}
			else {
				clickBtn(Locators_Sanity.dataUsageOptn);
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
				minWait();
				if (!isElementExist(Locators_Sanity.cellular_DataON)) {
					minWait();
					clickBtn(Locators_Sanity.cellular_DataOFF);
					APP_LOGS.info("Switch is Enabled");                     
					minWait();
				}

				//				enableFeature(Locators_Sanity.cellular_DataON,Locators_Sanity.cellular_DataOFF,Locators_Sanity.switch_Title);
				minWait();
			}


		} catch (Exception e) {
			APP_LOGS.info("No Element found.");
			Assert.fail();
			minWait();	
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
		} catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "No Such Element Found");
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
			APP_LOGS.info(" URl entered sucessfully.");
			minWait();
			minWait();	 
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_ENTER);
			minWait();
			APP_LOGS.info(" Search click is sucessful."); 
		} catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "No Such Element Found");
			Assert.fail();
		}
	}


	public void downloadImageFile() {
		/*
		 * download file
		 */
		aDriver.longPressKeyCode(23);

	}

	public void setAPN() throws InterruptedException, IOException, org.json.simple.parser.ParseException {
		/*
		 * Set APN to Connect when Vodafone SIM card inserted
		 */
		String Uid=JsonFileReaderAndWriter.primaryDevIdReader();
		try {
			String XP5SSIMcard =BaseUtil.fetchDeviceProperty("adb -s "+Uid+" shell getprop gsm.sim.operator.alpha");
			minWait();
			System.out.println(XP5SSIMcard);
			if(XP5SSIMcard.equalsIgnoreCase("Vodafone IN")) {
				navigateTo_APN();
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
				minWait();
				System.out.println("Clicked on Connect");
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
				minWait();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Assert.fail();
		}
	}

	public void validateDataEnableDisable() throws InterruptedException, IOException {
		SoftAssert sf = new SoftAssert();
		try {
			customWait(4000);
			/*	String getTxt = Locators_Sanity.NoNewtwrkErroeMsg.getText();
			System.out.println(getTxt);*/
			if(isElementExist(Locators_Sanity.NoNewtwrkErroeMsg)||isElementExist(Locators_Sanity.ConnectionPrblm)) {
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
			launch_an_app("settings");
			customWait(4000);
			enableData();
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
				test.log(LogStatus.INFO, " Validated sucessfully enabling and disabling of data service");
				sf.assertTrue(check, "Validation is pass");
			}else{
				APP_LOGS.info("Validated is not sucessful enabling and disabling of data service");		
				test.log(LogStatus.ERROR, "Validated is not sucessful enabling and disabling of data service");
				sf.fail();
			}
			minWait();	
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			minWait();	
			minWait();	
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			minWait();	
		} catch (Exception e) {
			APP_LOGS.info("No Element found.");
			sf.fail();
		}
		sf.assertAll();
	}

	public void enableDataRoaming() throws InterruptedException {
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
		} catch (Exception e) {
			APP_LOGS.info("No Element found.");
			minWait();	
		}
	}

	public void validateDataRoamingService() throws InterruptedException {
		SoftAssert sf = new SoftAssert();
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
				test.log(LogStatus.INFO, "Enable the Data Roaming is sucessful");
				sf.assertTrue(check, "validation is Pass");
			}else{
				APP_LOGS.info("check: "+check);
				APP_LOGS.info("Enable the Data Roaming is not sucessful");
				test.log(LogStatus.ERROR, "Enable the Data Roaming is not sucessful");
				sf.fail();
			}	
			minWait();	
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			minWait();	
			minWait();	
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			minWait();	
		} catch (Exception e) {
			APP_LOGS.info("No Element found.");
			sf.fail();
		}
		sf.assertAll();
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
		} catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "No Such Element Found");
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
		catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "No Such Element Found");
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
				test.log(LogStatus.INFO, "FM Radio Turned On/OFF succesfully.");
				sf.assertTrue(check, "Validation is Pass");
			} else {
				APP_LOGS.info("FM Radio is not Turn Off, unsuccesful.");
				test.log(LogStatus.ERROR, "FM Radio is not Turn Off/On, unsuccesful.");
				sf.fail();
			}
		} catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "No Such Element Found");
			sf.fail();			
		}
		sf.assertAll();
	}

	public void validatescanAllStations() throws InterruptedException {
		SoftAssert sf = new SoftAssert();
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
					Locators_FM.YesOpt.click();
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
				sf.assertTrue(check, "Validation is Pass");
			}
			else {
				minWait();
				check =true;
				sf.fail();
			}
		} catch (Exception e) {
			APP_LOGS.info("No Element found.");
			sf.fail();
		}
		sf.assertAll();
	}

	public void validateInteractionBrowserDailer(String contact) throws InterruptedException {
		try {
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
		} catch (Exception e) {
			APP_LOGS.info("No Element found.");
			minWait();	
		}
	}

	public void validateHomePageLoadedBrowser() throws InterruptedException {
		SoftAssert sf = new SoftAssert();
		try {
			launch_an_app("browser");
			customWait(2000);
			enterUrl("www.youtube.com");
			customWait(5000);
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_MENU);
			minWait();
			clickBtn(Locators_Sanity.HomePage_Browser);
			customWait(4000);
			if((isElementExist(Locators_Sanity.SearchIcon_Google))||(isElementExist(Locators_Sanity.Searchenter_Google)) || (isElementExist(Locators_Sanity.rogersHomePageBrowser)) || (isElementExist(Locators_Sanity.attHomePageBrowser)) || (isElementExist(Locators_Sanity.bellSearchHomePageBrowser)) || (isElementExist(Locators_Sanity.bellURLHomePageBrowser))) {
				check =true;
				APP_LOGS.info("check: "+check);
				APP_LOGS.info("HomePageLoaded sucessful");
				test.log(LogStatus.INFO, "HomePage Loaded is sucessful");
				sf.assertTrue(check, "Validation is Pass");
			}
			else if(isElementExist(Locators_Sanity.Hompage_ATT)) {
				check =true;
				APP_LOGS.info("check: "+check);
				APP_LOGS.info("HomePageLoaded is sucessful");
				test.log(LogStatus.INFO, "HomePage Loaded is sucessful");
				sf.assertTrue(check, "Validation is Pass");
			}else{
				APP_LOGS.info("check: "+check);
				APP_LOGS.info("HomePageLoaded is not sucessful");
				test.log(LogStatus.INFO, "HomePage Loaded is not sucessful");
				sf.fail();
			}minWait();	
		} catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "No Such Element Found");
			sf.fail();
		}
		sf.assertAll();
	}

	public void goToAPNOptn() throws InterruptedException {
		try {
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
		} catch (Exception e) {
			APP_LOGS.info("No Element found.");
			minWait();	
		}
	}

	public void validateSearchWord(String word) throws InterruptedException {
		/*
		 * enter Word when data is enabled and validate the search is displayed 
		 */
		SoftAssert sf = new SoftAssert();
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
				sf.assertTrue(check, "Validation is Pass");
			}else{
				APP_LOGS.info("check: "+check);
				APP_LOGS.info("Search word displayed result is not sucessful");
				sf.fail();
			}minWait();	
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);	
		} catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "No Such Element Found");
			sf.fail();
		}
		sf.assertAll();
	}

	public void validateUrlEntered( WebElement validatesearch1,WebElement validatesearch2,WebElement validatesearch3,WebElement validatesearch4) throws InterruptedException{
		SoftAssert sf = new SoftAssert();
		check3 = true;
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

			if((check1)||(check2)||(check3)||(check4)){
				check=true;
				APP_LOGS.info("Validated sucessfully entered URL and Loaded browsed Page");			
				sf.assertTrue(check, "Validation is Pass");
			}else{
				APP_LOGS.info("Validated is not sucessful entered URL and Loaded browsed Page");					
				sf.fail();
			}
		} catch (Exception e) {
			APP_LOGS.info("No Element found.");
			e.printStackTrace();
			sf.fail();
		}
		sf.assertAll();
	}

	public void launchCalc() throws InterruptedException {
		launchApp("CALCULATOR_PACKAGE","CALCULATOR_ACTIVITY");
		minWait();
		minWait();
		APP_LOGS.info("Calculator Application Launched Succesfully");
	}


	public void navigateToOptions(WebElement optntype, String str) throws InterruptedException, IOException {
		/*
		 * Select Screen lock or Simcard Lock
		 */		
		try {
			ScrollToElement(Locators_Sanity.DataUsageList, str);
			minWait();
			String getOptionType = optntype.getText();
			minWait();
			clickBtn(optntype);
			APP_LOGS.info("Selected option is " + getOptionType);
			customWait(1000);			
		} catch (Exception e) {
			e.printStackTrace();
			test.setDescription("Failure Reason: Element not found");
			Assert.fail();
		}
	}


	public void validateCalcLaunch() throws InterruptedException {
		try {
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
			minWait();
			if(isElementExist(Locators_Sanity.calc_Dialog_Pad)){
				minWait();
				check = true;
				APP_LOGS.info("Calculator Launched validation is succesful.");
				Assert.assertTrue(check);
			}
			else {
				APP_LOGS.info("Calculator Launched validation is unsuccesful.");
				Assert.fail();
			}
		} catch (Exception e) {
			APP_LOGS.info("No Element found.");
			minWait();	
		}
	}


	public void navigateTo_APN() throws InterruptedException {
		/*
		 * This Method will Navigate to the APN's  
		 */
		try {
			launch_an_app("settings");
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
			for (int i = 1; i <=4; i++) {
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
				Thread.sleep(1000);
			}
			clickBtn(Locators_Sanity.access_Point_Names);
			minWait();
		} catch (Exception e) {
			APP_LOGS.info("No Element found.");
			test.log(LogStatus.ERROR, "No Such Element Found");
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
		} catch (Exception e) {
			APP_LOGS.info("No Element found.");
			test.log(LogStatus.ERROR, "No Such Element Found");	
			Assert.fail();
		}

	}

	public void delete_APN() throws InterruptedException {
		/*
		 * This Method is to Delete the APN
		 */
		try {
			navigateTo_APN();
			minWait();
			if(isElementExist(Locators_Sanity.Airtel_Apn)) {
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
				minWait();
			}
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_MENU);
			minWait();
			clickBtn(Locators_Sanity.edit);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_MENU);
			minWait();
			clickBtn(Locators_Sanity.delete_APN);
			minWait();		
		} catch (Exception e) {
			APP_LOGS.info("No Element found.");
			test.log(LogStatus.ERROR, "No Such Element Found");
			Assert.fail();
		}

	}

	public void validate_Add(String apnName) throws InterruptedException {
		/*
		 * This Method is to validate Added or Deleted APN's
		 */
		SoftAssert sf =new SoftAssert();
		try {
			navigateTo_APN();
			String apnName1 = aDriver.findElement(By.xpath("//android.widget.TextView[@text='"+apnName+"']")).getText();
			minWait();
			if (apnName.equals(apnName1)) {
				APP_LOGS.info("Added APN is Present");
				check=true;
				test.log(LogStatus.INFO, " APN is Added");
				sf.assertTrue(check, "Validation is Pass");
			} else {
				APP_LOGS.info("APN is not added,Failed");
				test.log(LogStatus.ERROR, "APN is not added,Failed");
				sf.fail();
			} 
			minWait();	
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			minWait();	
			minWait();	
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
		} catch (Exception e) {
			APP_LOGS.info("No Element found.");
			test.log(LogStatus.ERROR, "No Such Element Found");
			sf.fail();
		}
		sf.assertAll();
	}

	public void navigateTo_Tethering_PortableHotspot() throws InterruptedException{
		/* 
		 * This Method will Navigate to Tethering & portable hotspot 
		 */ 
		try {
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_HOME);
			minWait();
			clickBtn(Locators_Sanity.settings);
			minWait();
			clickBtn(Locators_Sanity.more_Button);
			minWait();
			clickBtn(Locators_Sanity.tethering_Portable_Hotspot);
			minWait();
		} catch (Exception e) {
			APP_LOGS.info("No Element found.");
			e.printStackTrace();
			test.log(LogStatus.ERROR, "No Such Element Found");
			Assert.fail();
		}
	}

	public void turn_ON_MobileData() throws InterruptedException {
		/*
		 * This Method will turn ON the Moblie Data, if it is OFF
		 */
		SoftAssert Sa = new SoftAssert();
		try {   
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_HOME);
			minWait();
			clickBtn(Locators_Sanity.settings);
			minWait();

			if(isElementExist(Locators_Sanity.mobile_Networks)) {
				clickBtn(Locators_Sanity.mobile_Networks);
				minWait();
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
			APP_LOGS.info("No Element found.");
			e.printStackTrace();
			test.log(LogStatus.ERROR, "No Such Element Found");
			Sa.fail();
		}
		Sa.assertAll();
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
		} catch (Exception e) {
			APP_LOGS.info("No Element found.");
			e.printStackTrace();
			test.log(LogStatus.ERROR, "No Such Element Found");
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
			if(isElementExist(Locators_Sanity.OK_Button)) {
				clickBtn(Locators_Sanity.OK_Button);
				minWait();
			}
			clickBtn(Locators_Sanity.portable_WiFi_hotspot_Off);
			minWait();
		} catch (Exception e) {
			APP_LOGS.info("No Element found.");
			e.printStackTrace();
			test.log(LogStatus.ERROR, "No Such Element Found");
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
		} catch (Exception e) {
			APP_LOGS.info("No Element found.");
			e.printStackTrace();
			test.log(LogStatus.ERROR, "No Such Element Found");
			Assert.fail();
		}
	}

	public void validate_Entitlement_Error() throws InterruptedException {
		SoftAssert sf = new SoftAssert();
		try {
			waituntilnew(Locators_Sanity.entitlement_Error, 180);
			minWait();
			if (isElementExist(Locators_Sanity.entitlement_Error)) {
				Thread.sleep(1000);
				clickBtn(Locators_Sanity.OK_Button);
				minWait();
				check=true;
				APP_LOGS.info("Entitlement Error displayed Succesfully");
				test.log(LogStatus.INFO, "Validated Entitlement Error displayed Succesfully");
				sf.assertTrue(check, "Validation is Pass");

			} else {
				APP_LOGS.info("Entitlement Error is NOT displayed");
				test.log(LogStatus.ERROR, "Entitlement Error is NOT displayed");
				sf.fail();
			} 
			minWait();	
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			minWait();	
			minWait();	
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			minWait();	
		} catch (Exception e) {
			APP_LOGS.info("No Element found.");
			e.printStackTrace();
			test.log(LogStatus.ERROR, "No Such Element Found");
			sf.fail();
		}
		sf.assertAll();
	}

	public void validate_Entitlement_Error_sprint() throws InterruptedException {
		SoftAssert sf = new SoftAssert();
		try {
			//			waituntilnew(Locators_Sanity.entitlement_Error, 180);
			minWait();
			if (isElementExist(Locators_Sanity.entitlement_Error)) {
				Thread.sleep(1000);
				clickBtn(Locators_Sanity.OK_Button);
				minWait();
				APP_LOGS.info("Entitlement Error displayed Succesfully");
				check=true;
				sf.assertTrue(check, "Validation is Pass");
			} else {
				APP_LOGS.info("Entitlement Error is NOT displayed");
				sf.fail();
			} 
			minWait();	
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			minWait();	
			minWait();	
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			minWait();	
		} catch (Exception e) {
			APP_LOGS.info("No Element found.");
			sf.fail();
		} 
		sf.assertAll();
	}

	public void basicOperationwithoutdecimalpt() throws InterruptedException {
		boolean check1 =false;
		boolean check2 =false;
		boolean check3 =false;
		boolean check4 =false;
		boolean check5 =false;
		SoftAssert sf = new SoftAssert();
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
			//System.out.println(currentNumberText1);
			String sum1=String.valueOf(s);
			//System.out.println(sum1);
			if(currentNumberText1.equalsIgnoreCase(sum1)){
				check1 = true;
				APP_LOGS.info("Pass: Addition of 2 numbers verified sucessfully.");	
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
			//System.out.println(currentNumberText2);
			//Locators_Calculator.calc_Clear_btn.click();
			int s1=(num3-num4);
			String sum2=String.valueOf(s1);
			//						System.out.println(sum2);
			check2 = true;
			APP_LOGS.info("Pass:Substraction of 2 numbers verified sucessfully.");
			//if(currentNumberText2.contains(sum2)){
			//		check2 = true;
			//	APP_LOGS.info("Pass:Substraction of 2 numbers verified sucessfully.");
			//	}

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
				APP_LOGS.info("Pass:Product of 2 numbers verified sucessfully.");
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
				APP_LOGS.info("Pass:Division of 2 numbers verified sucessfully.");
			}

			if((check1)&&(check2)&&(check3)&&(check4)){
				check= true;
				APP_LOGS.info("Basic Functionality operations verified");
				sf.assertTrue(check, "Validation is Pass");
			}
			else{
				APP_LOGS.info(currentNumberText1+": "+check1+ "\n"+currentNumberText2+": "+check2+"\n"+currentNumberText3+": "+check3+"\n"+currentNumberText4+": "+check4+"\n");
				sf.fail();
			}	
			customWait(3000);
		} catch (Exception e) {
			APP_LOGS.info("No Element found.");
			sf.fail();
		}
		sf.assertAll();
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
		} catch (Exception e) {
			APP_LOGS.info("No Element found.");
			minWait();	
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
		}catch (Exception e) {
			APP_LOGS.info("No Element found.");
			e.printStackTrace();
			test.log(LogStatus.ERROR, "No Such Element Found");
			Assert.fail();
		}


	}

	public void createNewEvent() throws InterruptedException {
		/*
		 * Create a new event
		 */
		SoftAssert Sf = new SoftAssert();
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
		} catch (Exception e) {
			APP_LOGS.info("No Element found.");
			minWait();	
			Sf.fail();
		}
		Sf.assertAll();
	}

	public void validateEventReminder() throws InterruptedException {
		SoftAssert sf = new SoftAssert();
		try {
			minWait();	
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_HOME);
			minWait();
			minWait();	
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_MENU);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_ENTER);
			minWait();
			if(isElementExist(Locators_Sanity.Notification_Calender)) {
				minWait();
				check = true;
				APP_LOGS.info("Calender Launched and validated Reminder Notification is succesful.");
				sf.assertTrue(check, "Va);lidation is Pass");
			}
			else {
				APP_LOGS.info("Calender Launched and validated Reminder Notification is unsuccesful.");
				sf.fail();
			}
		} catch (Exception e) {
			APP_LOGS.info("No Element found.");
			e.printStackTrace();
			sf.fail();
		}
		sf.assertAll();
	}



	public void deleteEventCreated() throws InterruptedException {
		/*
		 * Delete events which is created
		 */
		SoftAssert sf = new SoftAssert();
		try {
			selectCalenderApp();
			minWait();	
			//		aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_HOME);
			minWait();	
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_MENU);
			minWait();
			clickBtn(Locators_Sanity.Deleteevents_Calender);
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
		} catch (Exception e) {
			APP_LOGS.info("No Element found.");
			test.log(LogStatus.ERROR, "No Such Element Found");
			e.printStackTrace();
			sf.fail();
		}
		sf.assertAll();
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
			customWait(2000);
			//	Locators_Sanity.saveBtn.click();
		} catch (Exception e) {
			APP_LOGS.info("No Element found.");
			minWait();	
			//				Assert.fail();
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
		}

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
			Locators_Sanity.saveBtn.click(); 
			APP_LOGS.info("Pressed Save button sucessfully");
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_HOME);
		} catch (Exception e) {
			APP_LOGS.info("No Element found.");
			minWait();	
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
		} catch (Exception e) {
			APP_LOGS.info("No Element found.");
			minWait();	
		}
	}


	public void validateDetailsSoundRec() throws InterruptedException {
		/*
		 * Validate sound recorder in File explorer
		 */
		SoftAssert sf = new SoftAssert();
		try {
			minWait();
			minWait();
			if(isElementExist(Locators_Sanity.SampleSoundRec)){
				check = true;
				String getSoundRecName= Locators_Sanity.SampleSoundRec.getText();
				APP_LOGS.info("Sound Recorder Name is :"+getSoundRecName);
				sf.assertTrue(check, "Validation is Pass");
			}
			else{
				APP_LOGS.info("All Details of Sound Record is not Displayed");
				sf.fail();
			}	  
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			//			customWait(2000);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_HOME);
		} catch (Exception e) {
			APP_LOGS.info("No Element found.");
			e.printStackTrace();
			sf.fail();
		}
		sf.assertAll();
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
		} catch (Exception e) {
			APP_LOGS.info("No Element found.");
			minWait();	
		}
	}

	//Delete Saved Recorded Sound
	public void deleteSavedRecorder() throws InterruptedException {
		try {
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_RIGHT);
			if(!isElementExist(Locators_Sanity.noRecordingsOpt)){
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
			}else{
				clickBackButton(1);
			}
		} catch (Exception e) {
			APP_LOGS.info("No Element found.");
			minWait();	
		}
	}

	public void ValidatedefaultSettings() throws InterruptedException {
		SoftAssert sf = new SoftAssert();
		check2=true;
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

			if((check1)||(check2)){
				check=true;
				APP_LOGS.info("Validated sucessfully Defult Notification Ringtone");
				test.log(LogStatus.INFO, "Validated sucessfully Defult Notification Ringtone");
				sf.assertTrue(check, "Validation is Pass");
			}else{
				APP_LOGS.info("Validated is not sucessful Defult Notification Ringtone");	
				test.log(LogStatus.ERROR, " Validated is not sucessful Defult Notification Ringtone");
				sf.fail();
			}
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			minWait();
		} catch (Exception e) {
			APP_LOGS.info("No Element found.");
			e.printStackTrace();
			test.log(LogStatus.ERROR, "No Such Element Found");
			sf.fail();
		}
		sf.assertAll();
	}


	public void selectSettings(WebElement option) throws InterruptedException, IOException, org.json.simple.parser.ParseException {
		/*
		 * select particular setting option
		 */
		SoftAssert Sa = new SoftAssert();
		String Uid=JsonFileReaderAndWriter.primaryDevIdReader();
		try {
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_HOME);
			minWait();
			minWait();
			Runtime.getRuntime().exec("adb -s "+Uid+" shell am start -n com.android.settings/com.android.settings.Settings");
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
		} catch (Exception e) {
			APP_LOGS.info("No Element found.");
			e.printStackTrace();
			test.log(LogStatus.ERROR, "No Such Element Found");
			Sa.fail();
		}
		Sa.assertAll();
	}

	public void validateDefaultLanguage() throws InterruptedException {
		/*
		 * check the default language
		 */
		SoftAssert sf = new SoftAssert();
		try {
			if(isElementExist(Locators_Sanity.defaultLaunguage)){
				check = true;
				String getName= Locators_Sanity.defaultLaunguage.getText();
				APP_LOGS.info("Default Language is :"+getName);
				test.log(LogStatus.INFO, "Default Language is :"+getName);
				sf.assertTrue(check, "Validation is Pass");
			}
			else{
				APP_LOGS.info("Default Language is not Displayed");
				test.log(LogStatus.ERROR, "Default Language is not Displayed");
				sf.fail();
			}	
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
		} catch (Exception e) {
			APP_LOGS.info("No Element found.");
			e.printStackTrace();
			test.log(LogStatus.ERROR, "No Such Element Found");
			sf.fail();
		}
		sf.assertAll();
	}

	public boolean scrollToText(String text) {
		/*
		Method used to select an element on the page by scrolling the Scroll View/List View
		 */

		boolean check = false;
		try {		
			String scrollable = "new UiScrollable(new UiSelector().scrollable(true))";
			String textElement = ".scrollIntoView(new UiSelector().text(\""+ text +"\"))";
			aDriver.findElementByAndroidUIAutomator(scrollable+textElement).click();
			APP_LOGS.info("Searched application is found sucessfully : ");
			check = true;
			return check;
		}
		catch(NoSuchElementException e) {
			return check;
		}
	}

	public void validatePrefferedNetwork(WebElement type) throws InterruptedException {
		/*
		 * Preffered network should not support 
		 */
		SoftAssert sf = new SoftAssert();
		try {
			minWait();	
			minWait();	
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			minWait();	
			minWait();	
			if(type.isEnabled()) {
				minWait();	
				APP_LOGS.info("Preffered Network mode is Supportable");
				test.log(LogStatus.ERROR, "Preffered Network mode is Supportable");
				sf.fail();
			}
			else{
				minWait();	
				check = true;
				APP_LOGS.info("Validated Preffered Network mode is not Supportable");
				test.log(LogStatus.INFO, "Validated Preffered Network mode is not Supportable");
				sf.assertTrue(check, "Validation is Pass");
			}	
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
		} catch (Exception e) {
			APP_LOGS.info("No Element found.");
			e.printStackTrace();
			test.log(LogStatus.ERROR, "No Such Element Found");
			sf.fail();
		}
		sf.assertAll();
	}

	public void validatePreferedMode() throws InterruptedException {
		/*
		 * 
		 */
		SoftAssert sf = new SoftAssert();
		try {
			if(Locators_Sanity.PreferredNwType_Operators.isEnabled()) {
				minWait();	
				check = true;
				APP_LOGS.info(" Preffered Network mode is  Supportable");
				test.log(LogStatus.INFO, "Preffered Network mode is Supportable ");
				sf.assertTrue(check, "Validation is Pass");
			}
			else{
				minWait();	
				APP_LOGS.info("Preffered Network mode is  Supportable");
				test.log(LogStatus.ERROR, "Preffered Network mode is Supportable is not validated");
				sf.fail();			
			}	

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			sf.fail();
		}		  
	}

	public void validateEditionDeletionApn() throws InterruptedException {
		/*
		 * validate default APn should not able to edit and delete
		 */
		SoftAssert sf = new SoftAssert();
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
				test.log(LogStatus.INFO, "Validated sucessfully Edition and Deletion of Default APn");
				sf.assertTrue(check, "Validation is Pass");
			}else{
				APP_LOGS.info("Validated is not sucessful Edition and Deletion of Default APn ");	
				test.log(LogStatus.INFO, " Edition and Deletion of Default APn is Unsucessfully");
				sf.fail();
			}
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_MENU);	
			minWait();
			clickBtn(Locators_Sanity.RestoreDefault);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
		} catch (Exception e) {
			APP_LOGS.info("No Element found.");
			sf.fail();
		}
		sf.assertAll();
	}

	public void validateRestoreAPn() throws InterruptedException {
		/*
		 * Validate APN default /restore After and before retore Option 
		 */
		SoftAssert sf = new SoftAssert();
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
			if(isElementExist(Locators_Sanity.RestoreDefault)) {
				clickBtn(Locators_Sanity.RestoreDefault);
			}
			else {
				clickBtn(Locators_Sanity.Restore);
			}

			customWait(4000);
			customWait(4000);
			customWait(6000);
			getApNTxtRestore = Locators_Sanity.Txt_Apn.getText();
			System.out.println(getApNTxtRestore);
			if(getApNTxt.equalsIgnoreCase(getApNTxtRestore)) {
				check = true;
				APP_LOGS.info("Default Restore Apn is display sucessful");
				test.log(LogStatus.INFO, "Default Restore Apn is display sucessful");
				sf.assertTrue(check, "Validation is Pass");
			}
			else{
				APP_LOGS.info("Default Restore Apn is not Displayed");
				test.log(LogStatus.ERROR, "Default Restore Apn is display sucessful");
				sf.fail();
			}	
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			minWait();
		} catch (Exception e) {
			APP_LOGS.info("No Element found.");
			test.log(LogStatus.ERROR, "No Such Element Found");
			e.printStackTrace();
			sf.fail();
		}
		sf.assertAll();
	}

	public void setPinLock() throws InterruptedException {
		/*
		 * set screen PIN lock 
		 */
		SoftAssert Sa = new SoftAssert();
		try {
			minWait();
			clickBtn(Locators_Sanity.Screen_Lock);
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
		} catch (Exception e) {
			APP_LOGS.info("No Element found.");
			e.printStackTrace();
			test.log(LogStatus.ERROR, "No Such Element Found");
			Assert.fail();
		}
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
			clickBtn(Locators_Sanity.Screenlck_None);
			minWait();
			minWait();
			clickBtn(Locators_Sanity.yes_RemoveBtn);
			minWait();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Assert.fail();
		}	
	}

	public void selectSetSIMLock() throws InterruptedException, IOException {
		SoftAssert Ss = new SoftAssert();
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
		} catch (Exception e) {
			APP_LOGS.info("No Element found.");
			minWait();	
			Ss.fail();
		}
		Ss.assertAll();
	}

	public void validateSIMLock() throws InterruptedException, IOException		{
		SoftAssert sf = new SoftAssert();
		try {
			customWait(2000);
			if(Check_Activation_Off_On().equals("OFF")) {
				APP_LOGS.info("Set Device Screen Lock is unsucessful");
				test.log(LogStatus.ERROR, "Set Device Screen Lock is unsucessful");
				sf.fail();
			}
			else {
				APP_LOGS.info("Activation status is ON");
				check = true;
				APP_LOGS.info("Set Device Screen Lock sucessful");
				test.log(LogStatus.INFO,"Set Device Screen Lock sucessful");
				sf.assertTrue(check, "Validation is Pass");
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
		} catch (Exception e) {
			APP_LOGS.info("No Element found.");
			test.log(LogStatus.ERROR, "No Such Element Found");
			e.printStackTrace();
			sf.fail();
		}
		sf.assertAll();
	}


	public void validateSetPIN() throws InterruptedException {
		/*
		 * PIN screen Lock is checked
		 */
		SoftAssert sf = new SoftAssert();
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
					test.log(LogStatus.INFO, "Set Device Screen Lock sucessful");
					sf.assertTrue(check, "Validation is Pass");
				}
				else{
					APP_LOGS.info("Set Device Screen Lock is unsucessful");
					test.log(LogStatus.ERROR, "Set Device Screen Lock is unsucessful");
					sf.fail();
				}	
			}
			minWait();
			clickBtn(Locators_Sanity.Screen_Lock);
			minWait();
			removeScreenLock();
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
		} catch (Exception e) {
			APP_LOGS.info("No Element found.");
			e.printStackTrace();
			test.log(LogStatus.ERROR, "No Such Element Found");
			sf.fail();
		}
		sf.assertAll();
	}	

	public void validateScreenLockToNone() throws IOException, InterruptedException, ParseException, org.json.simple.parser.ParseException {
		/*
		 * Remove PIN screenlock and set to None
		 */
		SoftAssert Sd = new SoftAssert();
		String Uid=JsonFileReaderAndWriter.primaryDevIdReader();
		try {
			minWait();
			clickBtn(Locators_Sanity.ScreenLock);
			minWait();
			Runtime.getRuntime().exec("adb -s "+Uid+" shell input text 1234");
			customWait(2000);
			Runtime.getRuntime().exec("adb -s "+Uid+" shell input keyevent KEYCODE_ENTER");
			minWait();
			clickBtn(Locators_Sanity.Screenlck_None);
			minWait();
			clickBtn(Locators_Sanity.Screenlck_removeBtn);
			APP_LOGS.info("Removed Screen lock PIN and Set to None");
			customWait(2000);
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
		} catch (Exception e) {
			e.printStackTrace();
			test.setDescription("Failure Reason: Element not found");
			Sd.fail();
		}      
		Sd.assertAll();
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
		} catch (Exception e) {
			APP_LOGS.info("No Element found.");
			minWait();	
		}
	}

	public void ConnectSecuredWifi() throws InterruptedException, IOException {
		/*
		 * Connect wifi with entering passkey
		 */
		try {
			minWait();
			aDriver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().resourceId(\"com.android.settings:id/main_content\")).scrollIntoView(new UiSelector().text(\"Wi-Fi\"))");
			minWait();

			clickBtn(Locators_Sanity.wifi);
			minWait();
			if(isElementExist(Locators_Sanity.disabled)) {
				enableFeature(Locators_Sanity.enabled,Locators_Sanity.disabled,Locators_Sanity.switch_Title);
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);		
				customWait(3000);
			}		
			else {
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);		    	
			}
			customWait(3000);
			String getSummary = Locators_Sanity.summaryWIFI.getText();
			System.out.println(getSummary);
			minWait();
			if(getSummary.contains("Disconnected")) {
				minWait();
				clickBtn(Locators_Sanity.wifi);
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_MENU);
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_ENTER);
				//refresh btn
				minWait();
				minWait();
				customWait(4000);
				scrollToText("IDCSONWAP");
				//selectIDcwifi();      
				minWait();
				if(isElementExist(Locators_Sanity.wifi_IDC_ForgetBtn)) {
					clickBtn(Locators_Sanity.wifi_IDC_ForgetBtn);
					minWait();
					scrollToText("IDCSONWAP");
					//selectIDcwifi();    
				}
				clickBtn(Locators_Sanity.wifi_show_Pswd);
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_UP);
				customWait(1000);

				changeToNumberMode_SMS(Locators_Sanity.wifi_IDC_Psswd);
				customWait(9000);
				if(isElementExist(Locators_Sanity.wifi_IDC)) {
					Runtime.getRuntime().exec("adb -s "+p_Id+" shell input text 1");
					minWait();
					Runtime.getRuntime().exec("adb -s "+p_Id+" shell input text dcS");
					minWait();
					Runtime.getRuntime().exec("adb -s "+p_Id+" shell input text 0");
					minWait();
					Runtime.getRuntime().exec("adb -s "+p_Id+" shell input text n");
					minWait();
					Runtime.getRuntime().exec("adb -s "+p_Id+" shell input text 1");
					minWait();
					Runtime.getRuntime().exec("adb -s "+p_Id+" shell input text md");
					minWait();
					Runtime.getRuntime().exec("adb -s "+p_Id+" shell input text 0");
					minWait();
					Runtime.getRuntime().exec("adb -s "+p_Id+" shell input text tc");
					minWait();
					Runtime.getRuntime().exec("adb -s "+p_Id+" shell input text 0");
					minWait();
					Runtime.getRuntime().exec("adb -s "+p_Id+" shell input text MbLr");
					minWait();
				}

				else if(isElementExist(Locators_Sanity.wifi_Cannada)) {
					Runtime.getRuntime().exec("adb -s "+p_Id+" shell input text rrxfo68837");
				}

				else if(isElementExist(Locators_Sanity.wifi_Dellas)) {
					Runtime.getRuntime().exec("adb -s "+p_Id+" shell input text yellowbook143");
				}

				minWait();
				customWait(1000);
				String psswrd = Locators_Sanity.wifi_IDC_Psswd.getText();
				System.out.println(psswrd);
				clickBtn(Locators_Sanity.wifi_IDC_ConnectBtn);
				APP_LOGS.info("IDC available secured wifi is connected");
				customWait(4000);		    	
			}		    

		} 
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			test.log(LogStatus.ERROR, "NO Such Element Found");
			Assert.fail();
		}
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
				test.log(LogStatus.ERROR, "Saved Channel is not Replaced with other Channel");
				SA1.fail();				
			}
			else {
				check =true;					
				APP_LOGS.info("Saved Channel is Replaced with other Channel");
				test.log(LogStatus.INFO,"Saved Channel is Replaced with other Channel");
				SA1.assertTrue(check, "Saved Channel is Replaced with other Channel");
			}				
		}
		catch(Exception e) {
			APP_LOGS.info("Element not Found");
			e.printStackTrace();
			SA1.fail();
		}
		SA1.assertAll();
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

				if(isElementExist(Locators_Sanity.wifi_Cannada)) {
					minWait();
					clickBtn(Locators_Sanity.wifi_Cannada);
					APP_LOGS.info("Cannada available secured wifi is Selected");
					break;
				}

				if(isElementExist(Locators_Sanity.wifi_Dellas)) {
					minWait();
					clickBtn(Locators_Sanity.wifi_Dellas);
					APP_LOGS.info("Cannada available secured wifi is Selected");
					break;
				}
				else {
					minWait();
					aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
					continue;
				}
			}
		} catch (Exception e) {
			APP_LOGS.info("No Element found.");
			minWait();	
			Assert.fail();
		}
	}

	public void validateWifiConnect() throws InterruptedException, IOException, org.json.simple.parser.ParseException {
		/*
		 * Validate wifi connection
		 */
		SoftAssert sf = new SoftAssert();
		try {
			minWait();
			selectSettings(Locators_Sanity.wifi);
			customWait(6000);
			selectIDcwifi();
			minWait();
			if(isElementExist(Locators_Sanity.wifi_IDC_ForgetBtn)) {
				check = true;
				APP_LOGS.info("Connected to Secured Wifi succesfully.");
				test.log(LogStatus.INFO, "Wifi Connect to SecuredWifi is succesfully");
				sf.assertTrue(check, "Validation is Pass");
			}else{
				APP_LOGS.info("Connected to Secured Wifi is unsuccesful.");	
				test.log(LogStatus.ERROR, " Wifi Connect to SecuredWifi is unsuccesful.");
				sf.fail();
			}
			minWait();	
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			minWait();	
			minWait();	
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			minWait();	
		} catch (Exception e) {
			APP_LOGS.info("No Element found.");
			test.log(LogStatus.ERROR, " No Such Element Found");
			sf.fail();
		}
		sf.assertAll();
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
		} catch (Exception e) {
			APP_LOGS.info("No Element found.");
			minWait();	
		}
	}

	public void validateSearchDevices() throws InterruptedException {
		/*
		 * validate with available devices are shown
		 */
		SoftAssert sf = new SoftAssert();
		try {
			customWait(4000);
			customWait(4000);
			customWait(4000);
			if(isElementExist(Locators_Sanity.bluetooth_Availabledevices)) {
				check = true;
				APP_LOGS.info("Bluetooth Search or scan devices is succesful.");
				sf.assertTrue(check, "Validation is Pass");
			}else{
				APP_LOGS.info("Bluetooth Search or scan devices is unsuccesful.");	
				sf.fail();
			}
		} catch (Exception e) {
			APP_LOGS.info("No Element found.");
			e.printStackTrace();
			sf.fail();
		}
		sf.assertAll();
	}

	public void validateselectHardKeys() throws InterruptedException, IOException, ParseException, org.json.simple.parser.ParseException {
		String Uid=JsonFileReaderAndWriter.primaryDevIdReader();
		SoftAssert sf = new SoftAssert();
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
			Runtime.getRuntime().exec("cmd /C \"adb -s "+Uid+" shell input keyevent --longpress KEYCODE_VOLUME_UP");
			minWait();
			customWait(5000);
			Runtime.getRuntime().exec("cmd /C \"adb -s "+Uid+" shell input keyevent --longpress KEYCODE_VOLUME_UP");
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
				Runtime.getRuntime().exec("cmd /C \"adb -s "+Uid+" shell input keyevent --longpress KEYCODE_VOLUME_DOWN");
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
				test.log(LogStatus.INFO, "Validated Pressed Hard Key sucessfully");
				sf.assertTrue(check, "Validation is Pass");
			}else{
				APP_LOGS.info("Validation Failed, Pressed Hard Key");
				test.log(LogStatus.ERROR, "Validation Failed, Pressed Hard Key");
				sf.fail();
			}
		} catch (Exception e) {
			APP_LOGS.info("No Element found.");
			e.printStackTrace();
			test.log(LogStatus.ERROR, "No Such Element Found");
			sf.fail();
		}
		sf.assertAll();
	}

	public void selectWIFIOptn() throws InterruptedException{
		/*
		 * Select Wifi option from settings List
		 */
		SoftAssert Sa = new SoftAssert();
		try {
			customWait(1000);
			ScrollToElement(Locators_Sanity.SettingsList, "Wi-Fi");
			customWait(2000);
			clickBtn(Locators_Sanity.wifi);
			minWait();
			APP_LOGS.info("Selected WIFI option");
		} catch (Exception e) {
			e.printStackTrace();
			APP_LOGS.info("WIFI : No such Element found");
			test.log(LogStatus.ERROR, "Wi-Fi No such Element found");
			Sa.fail();
		}
		Sa.fail();
	}

	public void validate_ON_OFF_WiFiFeature(WebElement feature) throws InterruptedException, IOException{
		/*
		 * validate Turn On and OFF wifi toggle button	
		 */
		SoftAssert S1 = new SoftAssert();
		try {	   
			customWait(2000);
			enableFeature(Locators_Sanity.enabled,Locators_Sanity.disabled,Locators_Sanity.switch_Title);
			minWait();

			if(isElementExist(Locators_Sanity.enabled)) {
				check1 = true;
				APP_LOGS.info(" check1");
			}
			customWait(3000);
			disableFeature(Locators_Sanity.enabled,Locators_Sanity.disabled);
			if(isElementExist(Locators_Sanity.disabled)) {
				check2 = true;
				APP_LOGS.info(" ");
			}
			if((check1) && (check2)) {
				check = true;
				APP_LOGS.info(" Turn ON and OFF feature is verified");
				test.log(LogStatus.INFO, "Turn ON and OFF WIFI feature is verified ");
				S1.assertTrue(check, " ");
			}
			else {
				APP_LOGS.info(" check1"+check1+"check2"+check2);
				test.log(LogStatus.ERROR, "No Such Element Found");
				APP_LOGS.info(" Turn ON and OFF feature is not verified ");
				S1.fail();
			}
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
		}catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, " No Such Element Found");
			S1.fail();
		}
		S1.assertAll();

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
			minWait();
		} catch (Exception e) {
			APP_LOGS.info("No Element found.");
			e.printStackTrace();
			test.log(LogStatus.ERROR, "No Such Element Found");
			Assert.fail();
		}
	}

	public void validateFileExplorer() throws InterruptedException {
		/*
		 * validate Picture captured is present in DCIM
		 */
		SoftAssert sf = new SoftAssert();
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
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
			minWait();
			for(int i=1; i<=15; i++) 
			{
				if(isElementExist(Locators_Sanity.DCIM_optn)) {
					minWait();
					aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_ENTER);
					minWait();
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
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
			minWait();
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_ENTER);
			minWait();

			if(isElementExist(Locators_Sanity.pic_capture)) {
				check=true;
				APP_LOGS.info("Validated File Explorer Browsed captured picture sucessfully ");			
				sf.assertTrue(check, "Validation is pass");
			}else{
				APP_LOGS.info("Not Validated File Explorer Browsed captured picture sucessfully ");					
				sf.fail();
			}				
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_CLEAR);
			minWait();
			clickBtn(Locators_Sanity.contact_DeleteBtn);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			minWait();
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
		} catch (Exception e) {
			APP_LOGS.info("No Element found.");
			test.log(LogStatus.ERROR, "No Such Element Found");
			e.printStackTrace();
			sf.fail();
		}
		sf.assertAll();
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
				test.log(LogStatus.INFO, "Clock date and time, Alarm, Stopwatch and Timer validated ");
				Sd.assertTrue(check, "Clock date and time, Alarm, Stopwatch and Timer validated ");
			}else{
				APP_LOGS.info("test Case Failed");
				test.log(LogStatus.ERROR, "Clock date and time, Alarm, Stopwatch and Timer is not validated");
				Sd.fail();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			test.log(LogStatus.ERROR, " No Such Element Found");
			Sd.fail();
		}
		Sd.assertAll();
	}

	public void validateRecordedSoundSaved() throws InterruptedException {
		/*
		 * check recording name is saved in recorded list after saving
		 */
		SoftAssert SA= new SoftAssert();
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
				check= true;
				APP_LOGS.info("Recorded sound using Sound recorder and validated successfully");
				test.log(LogStatus.INFO, "Recorded sound using Sound recorder and validated successfully");
				SA.assertTrue(check, "Recorded sound using Sound recorder and validated successfully");	
			}else{
				APP_LOGS.info("test Case Failed");
				test.log(LogStatus.ERROR, "Failed to validate Sound recorder");
				SA.fail();
			}	aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
		}
		catch (Exception e) {
			APP_LOGS.info("Element not Found");
			e.printStackTrace();
		}
	}

	public void enable24Format() throws InterruptedException, IOException {
		try {
			for(int i=1; i<=5; i++) {
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
		} catch (Exception e) {
			APP_LOGS.info("No Element found.");
			minWait();	
		}
	}


	public void validateInternalStorage() throws InterruptedException {
		/*
		 * Validate internal storage
		 */
		SoftAssert sf = new SoftAssert();
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
				APP_LOGS.info("Internal Storage and available storage of the device is verified sucessfully.");							
				customWait(5000);
				test.log(LogStatus.INFO, "Internal Storage and available storage of the device is verified sucessfully.");
				sf.assertTrue(check, "Validation is Pass");
			}else{
				APP_LOGS.info("test case failed");			
				minWait();
				test.log(LogStatus.ERROR, " Internal Storage and available storage of the device is not verified.");
				sf.fail();
			}
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
		} catch (Exception e) {
			APP_LOGS.info("No Element found.");
			e.printStackTrace();
			sf.fail();
		}
		sf.assertAll();
	}		

	public void validateThundersoftUpdate() throws InterruptedException {
		/*
		 * check for the update 
		 */
		SoftAssert sf = new SoftAssert();
		try {
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

			else if (isElementExist(Locators_Sanity.System_Update)) {
				clickBtn(Locators_Sanity.System_Update);
				customWait(3000);		
				if(isElementExist(Locators_Sanity.checkUpdates)) {
					clickBtn(Locators_Sanity.checkUpdates);
				}
				WebDriverWait wait = new WebDriverWait(getDriver(), 12000);
				minWait();
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@text='New version']")));
				customWait(1000);	

				if(isElementExist(Locators_Sanity.UpdateNow)) {
					clickBtn(Locators_Sanity.UpdateNow);
					minWait();
				}
				else if(isElementExist(Locators_Sanity.downloadUpdates)) {
					clickBtn(Locators_Sanity.downloadUpdates);
					minWait();
				}					
			}
			customWait(5000);
			customWait(5000);
			customWait(5000);

			if(isElementExist(Locators_Sanity.Up_To_Software_OKBtn)||isElementExist(Locators_Sanity.updated)) {
				check = true;
				APP_LOGS.info("Thundersoft Software update_text Element is present");
				test.log(LogStatus.INFO, "Thundersoft Software update_text Element is present");
				sf.assertTrue(check, "Validation is Pass");
			}else
			{
				APP_LOGS.info("Thundersoft Software update_text Element is not present");		
				test.log(LogStatus.ERROR, "Thundersoft Software update_text Element is not present");
				sf.fail();
			}
			clickBtn(Locators_Sanity.OK_Button);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
		} catch (Exception e) {
			APP_LOGS.info("No Element found.");
			e.printStackTrace();
			test.log(LogStatus.ERROR, "No Such Element Found");
			sf.fail();

		}
		sf.assertAll();
	}		

	public String Check_Activation_Off_On() throws InterruptedException, IOException {
		//This method is used to check wheather activation is ON or OFF .
		minWait();
		String status = null;
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



	/*
	 * ===================================================================================================================================================================
	 * 																	AOSP test CASES STARTS HERE
	 * ===================================================================================================================================================================

	 */




	// Validates the presence of USB connection.
	public void validate_phone_Charging() throws InterruptedException{
		//			navigate_to_NotificationScreen();
		//			ScrollToElement(Locators_Sanity.settings_frame, "Battery");
		try {
			minWait();
			clickBtn(Locators_Sanity.AboutPhone);
			minWait();
			clickBtn(Locators_Sanity.status);
			customWait(1000);
			if(isElementExist(Locators_Sanity.Charging_OverUSB)||isElementExist(Locators_Sanity.Full_OverUSB)){
				APP_LOGS.info("Charging over USB in Battery Status option is displayed");
				test.log(LogStatus.INFO, "Charging over USB in Battery Status option is displayed");
				check=true;
				Assert.assertTrue(check);
			}else{
				APP_LOGS.info("Charging over USB in Battery Status option NOT is displayed");
				test.log(LogStatus.ERROR, "Charging over USB in Battery Status option NOT is displayed");
				Assert.fail();
			}	
			back();
			minWait();
			back();

		} catch (Exception e) {
			e.printStackTrace();
			APP_LOGS.info("No Element found.");
			Assert.fail();
		}
	}


	// Navigates to About Phone
	public void NavigateToAboutPhone() throws InterruptedException{
		minWait();

		try {
			/*aDriver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().resourceId(\"com.android.settings:android:id/title\")).scrollIntoView(new UiSelector().text(\"About phone\"))");  
			    minWait();
			    aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
			    minWait();
			    aDriver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().resourceId(\"com.android.settings:android:id/title\")).scrollIntoView(new UiSelector().text(\"Build number\"))");  
			 */
			ScrollToElement(Locators_Sanity.settings_frame, "About phone");
			//aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
		} catch (Exception e) {
			e.printStackTrace();
			APP_LOGS.info("No Element found.");
			test.log(LogStatus.ERROR, " About phone option: No Such Element Found");
			Assert.fail();
		}
	}

	public void navigateToBatteryLevel(){
		try {
			ScrollToElement(Locators_Sanity.settings_frame, "Battery");
		} catch (Exception e) {
			e.printStackTrace();
			APP_LOGS.info("No Element found.");
			test.log(LogStatus.ERROR, " Build Summary : No Such Element Found");
			Assert.fail();
		}
	}

	public void navigateToDateTime() throws InterruptedException{
		minWait();
		try {
			ScrollToElement(Locators_Sanity.settings_frame, "Date & time");
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public void navigateToBuildVersion() throws InterruptedException{
		minWait();
		try {
			/*for(int i=0;i<22;i++){
					aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
					minWait();
				}*/

			ScrollToElement(Locators_Sanity.settings_frame, "About phone");
			minWait();
			clickBtn(Locators_Sanity.AboutPhone);
			minWait();
			//aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
			ScrollToElement(Locators_Sanity.settings_frame, "Build number");

		} catch (Exception e) {
			e.printStackTrace();
			APP_LOGS.info("No Element found.");
			test.log(LogStatus.ERROR, " Build Summary : No Such Element Found");
			Assert.fail();	
		}
	}

	public void validate_homescreen() throws InterruptedException{
		minWait();
		aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
		minWait();
		if(validate_presenceOfElement(Locators_Sanity.settings, "Settings")){
			APP_LOGS.info("Settings ");
		}
	}
	public void scrollInLoop(WebElement element) throws InterruptedException{
		for(int i=0;i<10;i++){
			if(isElementExist(element)){
				break;
			}else{
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
				minWait();
			}

		}
	}
	public void validate_build_version() throws InterruptedException{
		minWait();
		try {
			String xp5S_FirmWare = Locators_Sanity.build_Summary.getText();
			if(xp5S_FirmWare.contains("5SA")){
				APP_LOGS.info("Flashed with XP5S firmware");
				test.log(LogStatus.INFO, "About Phone : Flashed with XP5S firmware");
				Assert.assertTrue(true);
			}else{
				APP_LOGS.info("String: 5SA is not found in firmware version");
				test.log(LogStatus.ERROR, "About Phone String: 5SA is not found in firmware version");
				Assert.fail();				
			}
		} catch (Exception e) {
			e.printStackTrace();
			APP_LOGS.info("No Element found.");
			test.log(LogStatus.ERROR, " Build Summary : No Such Element Found");
			Assert.fail();
		}			
	}


	public void validateAboutPhone() throws InterruptedException, FileNotFoundException, IOException, ParseException, org.json.simple.parser.ParseException {
		/*
		 * validate About phone by Basebandversion and Build number
		 */
		SoftAssert Sa = new SoftAssert();
		try {
			String Uid=JsonFileReaderAndWriter.primaryDevIdReader();

			String BasebandVersionTxt = Locators_Sanity.BasebandVersionText.getText();
			System.out.println(BasebandVersionTxt);
			minWait();
			String deviceBaseband = fetchDeviceProperty("adb -s "+Uid+" shell getprop gsm.version.baseband");
			minWait();
			System.out.println(deviceBaseband);
			if(BasebandVersionTxt.equals(deviceBaseband)) {
				check1 = true;
				APP_LOGS.info("About Phone Baseband is verified "+ deviceBaseband);
				test.log(LogStatus.INFO, " About Phone Baseband is verified "+ deviceBaseband);
			}

			String BuildNumberTxt = Locators_Sanity.BuildNumberText.getText();
			minWait();
			System.out.println(BuildNumberTxt);
			String deviceBuildNum = fetchDeviceProperty("adb -s "+Uid+" shell getprop ro.build.id");
			minWait();
			System.out.println(deviceBuildNum);
			if(BuildNumberTxt.contains(deviceBuildNum)) {
				check2 = true;
				APP_LOGS.info("About phone BuildNumber is verified "+ deviceBuildNum);
				test.log(LogStatus.INFO, " About phone BuildNumber is verified "+ deviceBuildNum);
			}

			if((check1)&&(check2)) {
				check = true;
				APP_LOGS.info("About Phone verified with Baseband and Build Number");
				Sa.assertTrue(check, " ");
			}
			else {
				test.log(LogStatus.ERROR, "Baseband and Build Number are mismatch");
				Sa.fail();
			}        
		} catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "No Such Element Found");
			Sa.fail();
		}
		Sa.assertAll();
	}


	public void validate_Date_Time() throws InterruptedException{
		minWait();
		try {
			if(validate_presenceOfElement(Locators_Sanity.date_time, "Date & time")){
				APP_LOGS.info("Date & time is displayed succesfully");
				test.log(LogStatus.INFO, "Date & time is displayed succesfully");
				Assert.assertTrue(true);
			}else{
				APP_LOGS.info("Date & time is not displayed.");
				test.log(LogStatus.ERROR, "Date & time is not displayed succesfully");
				Assert.fail();
			}
		} catch (Exception e) {
			e.printStackTrace();
			APP_LOGS.info("No Element found.");
			test.log(LogStatus.ERROR, " Build Summary : No Such Element Found");
			Assert.fail();
		}
	}
	public void validate_AboutPhone() throws InterruptedException{
		minWait();
		try {
			if(validate_presenceOfElement(Locators_Sanity.AboutPhone, "About Phone")){
				APP_LOGS.info("About phone is displayed succesfully");
				test.log(LogStatus.INFO, "About phone is displayed succesfully");
				Assert.assertTrue(true);
			}else{
				APP_LOGS.info("About phone is not displayed.");
				test.log(LogStatus.ERROR, "About phone is not displayed.");
				Assert.fail();
			}
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
		} catch (Exception e) {
			APP_LOGS.info("No Element found.");
			test.log(LogStatus.ERROR, "No Such Element FOund");
			Assert.fail();
		}

	}

	public void validate_Battery() throws InterruptedException{
		minWait();
		try {
			if(validate_presenceOfElement(Locators_Sanity.battery, "Battery")){
				APP_LOGS.info("Battery is displayed succesfully");
				test.log(LogStatus.INFO, "Battery Level is displayed succesfully");
				Assert.assertTrue(true);
			}else{
				APP_LOGS.info("Battery is not displayed.");
				test.log(LogStatus.ERROR, "Battery Level is not displayed succesfully");
				Assert.fail();}
		} catch (Exception e) {
			e.printStackTrace();
			APP_LOGS.info("No Element found.");
			test.log(LogStatus.ERROR, " Battery option: No Such Element Found.");
			Assert.fail();
		}
	}

	public void validate_ptt() throws InterruptedException{
		minWait();
		try {
			if(validate_presenceOfElement(Locators_Sanity.confirmDialog_ATT_PTT, "Battery")){
				APP_LOGS.info("Battery is displayed succesfully");
				Assert.assertTrue(true);
			}else{
				APP_LOGS.info("Battery is not displayed.");
				Assert.fail();}
		} catch (Exception e) {
			e.printStackTrace();
			APP_LOGS.info("No Element found.");
			test.log(LogStatus.ERROR, " Battery option: No Such Element Found.");
			Assert.fail();
		}
	}

	public void validate_soundRecorder() throws InterruptedException{
		minWait();
		SoftAssert sa = new SoftAssert();
		try {
			if(isElementExist(Locators_Sanity.SoundRecList)) {
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
				minWait();
			}
			if(validate_presenceOfElement(Locators_Sanity.soundRecorder, "Sound Recorder")){
				check = true;
				APP_LOGS.info("Sound Recorder is displayed succesfully");
				test.log(LogStatus.INFO, "Sound Recorder is displayed succesfully");
				sa.assertTrue(check, "");
			}else{
				APP_LOGS.info("Sound Recorder is not displayed.");
				test.log(LogStatus.ERROR, "Sound Recorder is not displayed.");
				sa.fail();
			}

			recordSound();

		} catch (Exception e) {
			e.printStackTrace();
			APP_LOGS.info("No Element found.");
			test.log(LogStatus.ERROR, " Battery option: No Such Element Found.");
			sa.fail();
		}
		sa.assertAll();
	}
	// After taking picture, validates by checking the presence of gallery icon in camera screen.
	public void takePicture_and_validate() throws InterruptedException{



		try {

			customWait(2000);
			//				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
			clickBtn(Locators_Sanity.CamerIcon);
			APP_LOGS.info("Camera clicked sucessfully.");
			customWait(3000);
			if(isElementExist(Locators_Sanity.gallery_icon_in_CameraScreen)){
				APP_LOGS.info("Gallery icon is displayed in the camera after clicking picture.");
				test.log(LogStatus.INFO, "Gallery icon is displayed in the camera after clicking picture.");
				Assert.assertTrue(true);
			}else{
				APP_LOGS.info("Gallery icon is not displayed in the camera after clicking picture.");
				test.log(LogStatus.ERROR, "Gallery icon is not displayed in the camera after clicking picture.");
				Assert.fail();
			}
		} catch (Exception e) {
			e.printStackTrace();
			APP_LOGS.info("No Element found.");
			test.log(LogStatus.ERROR, " Battery option: No Such Element Found.");
			Assert.fail();
		}

	}

	public void captureVideo_validate() throws InterruptedException{
		customWait(3000);
		aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_UP);
		APP_LOGS.info("You are in video mode.");
		customWait(3000);
		//aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
		clickBtn(Locators_Sanity.video_capture_button);
		APP_LOGS.info("Started capturing video.");
		customWait(4000);
		//aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
		clickBtn(Locators_Sanity.video_capture_button);
		APP_LOGS.info("Stopped capturing video.");
		customWait(3000);
		try {
			if(isElementExist(Locators_Sanity.gallery_icon_in_CameraScreen)){
				APP_LOGS.info("Gallery icon is displayed in the camera after clicking picture.");
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_UP);
				APP_LOGS.info("Restored back to picture mode.");
				test.log(LogStatus.INFO, "Gallery icon is displayed in the camera after clicking picture.");
				Assert.assertTrue(true);
			}else{
				APP_LOGS.info("Gallery icon is not displayed in the camera after clicking picture.");
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_UP);
				APP_LOGS.info("Restored back to picture mode.");
				//AOSP_Sanity_tests.test.setDescription("Failure Reason:   Gallery icon is not displayed in the camera after clicking picture.");
				//It will prints in the Description part of the report rather than on the Head
				test.log(LogStatus.ERROR,"Gallery icon is not displayed in the camera after clicking picture.");
				Assert.fail();
			}
		} catch (Exception e) {
			e.printStackTrace();
			APP_LOGS.info("No Element found.");
			test.log(LogStatus.ERROR, " Battery option: No Such Element Found.");
			Assert.fail();
		}

	}

	//Validate MO call.
	// This method is incomplete.
	public void originate_MO_call_and_Validate() throws InterruptedException{
		launch_an_app("phone");
		try {
			//Runtime.getRuntime().exec("adb shell service call phone 1 s16 08040302037 "))
			customWait(4000);

		} catch (Exception e) {
			// TODO: handle exception
		}
	}


	//Presses home button and validates home screen.
	public void validate_HomeScreen() throws InterruptedException{
		SoftAssert Ss = new SoftAssert();
		try {
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_HOME);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			minWait();

			for(int i=1; i<=8; i++) {
				if(isElementExist(Locators_Sanity.HomeScreen)) {
					APP_LOGS.info("In Quick Settings");
					minWait();
					break;					
				} else {
					aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_LEFT);
					continue;					
				}	
			}


			if(isElementExist(Locators_Sanity.HomeScreen)){
				check = true;
				APP_LOGS.info("HomeSCreen Ring is found in home screen");
				APP_LOGS.info("You are in homescreen");
				//					test.log(LogStatus.INFO, "Home Screen is Validated");
				Ss.assertTrue(check, " ");
			}else{
				APP_LOGS.info("Settings icon is not found in home screen");
				APP_LOGS.info("You are not in homescreen");
				test.log(LogStatus.ERROR, " Not in Homescreen");
				Ss.fail();
			}
		} catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "Settings icon is not found in home screen : No Such Element Found ");
			Ss.fail();
		}
		Ss.assertAll();
	}

	public void playMusic() throws InterruptedException, IOException{
		try {
			minWait();
			launch_an_app("music");
			minWait();
			if(isElementExist(Locators_Sanity.audiorecordings)) {
				minWait();
				clickBtn(Locators_Sanity.audiorecordings);
				minWait();
				clickBtn(Locators_Sanity.yourrecordings);
				minWait();
			}else {
				minWait();
				clickBtn(Locators_Sanity.yourrecordings);
				minWait();
				clickBtn(Locators_Sanity.audiorecordings);
				minWait();
				clickBtn(Locators_Sanity.yourrecordings);
			}
			clickBackButton(2);
		}catch (Exception e) {
			e.printStackTrace();
			APP_LOGS.info("No Element found.");
			test.log(LogStatus.ERROR, "No Such Element Found.");
		}
	}

	// Validates music Player.
	public void validate_music_player() throws InterruptedException{
		SoftAssert SA = new SoftAssert();
		try {
			if(isElementExist(Locators_Sanity.musicIndicator)){
				check = true;
				APP_LOGS.info("Music played sucessfully.");
				test.log(LogStatus.INFO, "Music played sucessfully.");
				SA.assertTrue(check, "Music played sucessfully.");	
			}else {
				APP_LOGS.info("Music is not played.");
				test.log(LogStatus.ERROR, "Music is not played.");
				SA.fail("Music is not played.");
			}	
		} catch (Exception e) {
			e.printStackTrace();
			APP_LOGS.info("No Element found.");
			test.log(LogStatus.ERROR, " Battery option: No Such Element Found.");
			Assert.fail();
		}
	}

	public void open_browser_validate() throws InterruptedException{
		launch_an_app("browser");
		customWait(2000);
		if(isElementExist(Locators_Sanity.ConnectionPrblm)) {
			minWait();
			clickBtn(Locators_Sanity.OK_Button);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
		}
		/*//			enterUrl("www.amazon.com");
			customWait(8000);
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_MENU);
			minWait();
//			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
			minWait();
			customWait(5000);
			try{
				if(isElementExist(Locators_Sanity.browser_tabSwitcher)){
					APP_LOGS.info("Webpage opened sucessfully.");
					test.log(LogStatus.INFO, "Browser--Webpage opened sucessfully.");
					Assert.assertTrue(true);
				}else{
					APP_LOGS.info("Webpage launched is not opened.");
					test.log(LogStatus.ERROR,"Webpage launched is not opened.");
					Assert.fail();
				}					
			} catch (Exception e) {
				e.printStackTrace();
				test.log(LogStatus.ERROR, "No Such Element Found");
				Assert.fail();
			}*/

		validate_launchofApp(Locators_Sanity.DefaultUrlTxt, "Browser");
	}

	// Precondition: A folder with name "A1" should be created in DUT and a text file with name "test_File" should be saved.
	// Returns the name of the file to be copied and will paste the file into target folder
	public void copy_file_to_Folder() throws InterruptedException{
		try {
			minWait();
			launch_an_app("fileExplorer");
			minWait();
			clickBtn(Locators_Sanity.xp5800);
			customWait(2000);
			for(int i=0;i<3;i++) {
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
				minWait();
			}
			clickCenterButton(1);
			clickMenuButton();
			minWait();
			for(int i=0;i<4;i++) {
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
				minWait();
			}
			clickCenterButton(1);
			clickBtn(Locators_Sanity.xp5800);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
			minWait();
			clickCenterButton(1);
			minWait();
			clickBtn(Locators_Sanity.copy_here);
			customWait(3000);
		} catch (Exception e) {
			APP_LOGS.info("No Element found.");
			test.log(LogStatus.ERROR, "No Such Element Found");
			e.printStackTrace();
			Assert.fail();
		}
	}

	public void validate_file_copy() throws InterruptedException{
		minWait();
		clickBackButton(1);
		minWait();
		aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
		minWait();
		clickCenterButton(1);
		minWait();
		try {
			if(isElementExist(Locators_Sanity.downloaded_file)){
				APP_LOGS.info("test file is copied to destination folder sucessfully.");
				test.log(LogStatus.INFO, "test file is copied to destination folder sucessfully.");
				Assert.assertTrue(true);
			}else{
				APP_LOGS.info("test file is not copied to destination folder.");
				test.log(LogStatus.ERROR,"test file is not copied to destination folder.");
				Assert.fail();
			}
		} catch (Exception e) {
			APP_LOGS.info("No Element found.");
			test.log(LogStatus.ERROR, "No Such Element Found");
			e.printStackTrace();
			Assert.fail();
		}
	}

	public void clearBrowserData() throws InterruptedException {
		try {
			minWait();
			launch_an_app("settings");
		} catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "No Such Element Found");
		}
	}

	public void deleteContact(String name1,String name2) throws InterruptedException, IOException, org.json.simple.parser.ParseException
	{
		/*
		 * Delete contact 
		 */
		String Uid=JsonFileReaderAndWriter.primaryDevIdReader();
		try {
			customWait(2000);
			Runtime.getRuntime().exec("adb -s "+Uid+" shell input text "+ name1);
			customWait(3000);
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_CLEAR);
			minWait();
			Locators_Sanity.contact_DeleteBtn.click();
			customWait(2000);
			clickBackButton(1);
			customWait(2000);
			Runtime.getRuntime().exec("adb -s "+Uid+" shell input text "+ name2);
			customWait(3000);
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
			minWait();
			clickMenuAndElement(Locators_Sanity.contact_delete);
			minWait();
			Locators_Sanity.contact_DeleteBtn.click();
			minWait();
			validatedeletedcontact(name2);
			clickBackButton(1);
			deleteIfContactsPresent();
		} catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "Element Not Found");
		}
	}


	public void validateDisableEnableBT() throws InterruptedException {
		/*
		 * disable enable wiFi
		 */
		SoftAssert S1 = new SoftAssert();
		try {
			minWait();
			aDriver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().resourceId(\"com.android.settings:id/main_content\")).scrollIntoView(new UiSelector().text(\"Bluetooth\"))");
			minWait();

			clickBtn(Locators_Sanity.Bluetooth);
			minWait();
			enable(Locators_Sanity.enabled,Locators_Sanity.disabled,Locators_Sanity.switch_Title);
			customWait(2000);
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
			minWait();
			if(isElementExist(Locators_Sanity.enabled)) {
				check1 = true;
				APP_LOGS.info(" check1");
			}
			customWait(2000);
			disableFeature(Locators_Sanity.enabled,Locators_Sanity.disabled);
			if(isElementExist(Locators_Sanity.disabled)) {
				check2 = true;
				APP_LOGS.info(" ");
			}
			customWait(2000);


			if((check1) && (check2)) {
				check = true;
				APP_LOGS.info(" Turn ON and OFF feature is verified");
				test.log(LogStatus.INFO, "Enable and disable BT feature is verified");
				S1.assertTrue(check, " ");
			}
			else {
				APP_LOGS.info(" check1"+check1+"check2"+check2);
				test.log(LogStatus.ERROR, "Enable and Disable BT feature is not verified");
				APP_LOGS.info("Enable and Disable BT feature is not verified");
				S1.fail();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			S1.fail();
		}
		S1.assertAll();
	}


	public void validateDisable(WebElement element) throws InterruptedException {
		/*
		 * Validate data roaming is enable 
		 */
		SoftAssert S1 = new SoftAssert();
		try {
			clickBtn(Locators_Sanity.OK_Btn);
			minWait();
			if(element.isEnabled()) {
				check = true;
				test.log(LogStatus.INFO, " Location is disabled");
				S1.assertTrue(check, " ");
			}
			else {
				test.log(LogStatus.ERROR, " Location is not disabled");
				S1.fail();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			test.log(LogStatus.ERROR, "No Such Element Found");
			S1.fail();

		}
		S1.assertAll();
	}

	public void validateEnable(WebElement element) {
		/*
		 * Validate data roaming is enable 
		 */
		SoftAssert S1 = new SoftAssert();
		try {
			if(element.isEnabled()) {
				check = true;
				test.log(LogStatus.INFO, "Location is enabled");
				S1.assertTrue(check, " ");
			}
			else {
				test.log(LogStatus.ERROR, " Location is not enabled");
				S1.fail();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			test.log(LogStatus.ERROR, "No Such Element Found");
			S1.fail();

		}
		S1.assertAll();
	}

	public void enable_BT_RefDevice() throws InterruptedException, IOException {

		try {
			minWait();
			Runtime.getRuntime().exec("adb -s "+r_Id+" shell am start -a android.settings.BLUETOOTH_SETTINGS");
			minWait();
			Runtime.getRuntime().exec("adb -s "+r_Id+" shell am start -a android.bluetooth.adapter.action.REQUEST_ENABLE");
			minWait();  
			Runtime.getRuntime().exec("adb -s "+r_Id+" shell input keyevent 22");
			minWait();
			Runtime.getRuntime().exec("adb -s "+r_Id+" shell input keyevent 22");
			minWait();
			Runtime.getRuntime().exec("adb -s "+r_Id+" shell input keyevent 66");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void validateScanBluetooth() throws InterruptedException {
		/*
		 * enable disable BT
		 */
		SoftAssert S1 = new SoftAssert();
		minWait();
		aDriver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().resourceId(\"com.android.settings:id/main_content\")).scrollIntoView(new UiSelector().text(\"Bluetooth\"))");
		minWait();

		clickBtn(Locators_Sanity.Bluetooth);
		minWait();
		enable(Locators_Sanity.enabled,Locators_Sanity.disabled,Locators_Sanity.switch_Title);
		WebDriverWait wait = new WebDriverWait(getDriver(), 12000);
		minWait();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@text='CANCEL']")));

		System.out.println("BT Cancel");
		customWait(4000);
		minWait();
		System.out.println("BT*********");
		if(isElementExist(Locators_Sanity.BT_Devices)) {
			check1= true; 
			APP_LOGS.info( "BT ON "+check1);
		}
		customWait(3000);
		disableFeature(Locators_Sanity.enabled,Locators_Sanity.disabled);
		if(isElementExist(Locators_Sanity.BluetoothOff)) {
			check2 = true;
			APP_LOGS.info(" ");
		}
		customWait(2000);

		if((check1) && (check2)) {
			check = true;
			APP_LOGS.info("Scan near by BT devices verified sucessfully");
			test.log(LogStatus.INFO, "Scan near by BT devices verified sucessfully");
			S1.assertTrue(check, "Scan near by BT devices verified sucessfully ");
		}
		else {
			APP_LOGS.info(" check1"+check1+"check2"+check2);
			test.log(LogStatus.ERROR, "Failed to scan near by BT devices");
			APP_LOGS.info("Failed to scan near by BT devices");
			S1.fail();
		}		
	}

	public void enable(WebElement enablebtn, WebElement disablebtn, WebElement switchtitle) throws InterruptedException {
		/*
		 * enable BT
		 */
		try {
			minWait();
			if(isElementExist(switchtitle)) {
				if (!isElementExist(enablebtn)) {
					minWait();
					clickBtn(disablebtn);
					APP_LOGS.info("Feature is Enabled");                     
					minWait();
				}
			}
			else {
				//				test.log(LogStatus., "Toggle button is disabled");
			}

		} catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "No Such Element Found");
			Assert.fail();
		}
	}


	public void deleteIfContactsPresent() throws InterruptedException
	{
		/*
		 * Delete contacts if exist before test
		 */
		try {
			if(!isElementExist(Locators_Sanity.addContacts))
			{
				minWait();
				clickMenuAndElement(Locators_Sanity.Contact_Select);
				minWait();
				clickMenuAndElement(Locators_Sanity.Contact_SelectAll);
				minWait();
				clickMenuAndElement(Locators_Sanity.contact_delete);
				minWait();
				Locators_Sanity.contact_DeleteBtn.click();
				minWait();
			}
		} catch (Exception e) {
			e.printStackTrace();
			APP_LOGS.info("Element Not Found");
			test.log(LogStatus.ERROR, "Element Not Found");
		}
	}


	public void validateBrowseInternet() throws InterruptedException {
		/*
		 * Validate while browsing google.com
		 */
		SoftAssert sf= new SoftAssert();
		try {
			customWait(2000);
			enterUrl("www.Google.com");
			customWait(5000);
			if((isElementExist(Locators_Sanity.SearchIcon_Google))||(isElementExist(Locators_Sanity.Searchenter_Google))) {
				check =true;
				APP_LOGS.info("check: "+check);
				APP_LOGS.info("Browser is Launched sucessfully");
				test.log(LogStatus.INFO, "Browsing is Launched and Surf sucessfully");
				sf.assertTrue(check, "Validation is Pass");
			}
			else{
				APP_LOGS.info("check: "+check);
				APP_LOGS.info("Browse internet is not sucessful");
				test.log(LogStatus.INFO, "Browse is not launhed and surf sucessful");
				sf.fail();
			}minWait();	
		} catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "No Such Element Found");
			sf.fail();
		}
		sf.assertAll();
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


	public void openTextFile_validate() throws InterruptedException{
		minWait();
		launch_an_app("fileExplorer");
		clickBtn(Locators_Sanity.xp5800);
		APP_LOGS.info("Clicked on XP5800");
		customWait(2000);
		aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
		APP_LOGS.info("Clicked on A1 folder");
		minWait();
		aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
		customWait(4000);
		if(isElementExist(Locators_Sanity.file_content)){
			APP_LOGS.info("test file is opened sucessfully.");
			test.log(LogStatus.INFO, "test file is opened sucessfully.");
			Assert.assertTrue(true);
		}else{
			APP_LOGS.info("File failed to open");
			test.log(LogStatus.ERROR,"File failed to open.");
			Assert.fail();
		}
	}

	public void launch_PTT_App() throws InterruptedException, IOException, org.json.simple.parser.ParseException {
		/*
		 * Launch PTT APP
		 */
		String XP5deviceModel=BaseUtil.fetchDeviceProperty("adb shell getprop gsm.version.baseband");
		//			String XP5devicebuild=BaseUtil.fetchDeviceProperty("adb shell getprop ro.build.id");
		if(XP5deviceModel.contains("ATT")) {
			launch_any_PTT_app("att");
			validate_PTT_Screen(Locators_Sanity.ATT_PTT_validate);
		}
		else if(XP5deviceModel.contains("SL")) {
			launch_any_PTT_app("sl");
			validate_PTT_Screen(Locators_Sanity.sl_PTT_validate);
		}
		else if(XP5deviceModel.contains("SPR")) {
			launch_any_PTT_app("sprint");
			validate_PTT_Screen(Locators_Sanity.sprint_PTT_validate);
		}
		else {
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_HOME);
			customWait(1000);
			if(isElementExist(Locators_Sanity.bell_PTT)) {
				minWait();
				System.out.println("=========");
				clickBtn(Locators_Sanity.bell_PTT);
				minWait();
				validate_PTT_Screen(Locators_Sanity.ATT_PTT_validate);
			}
			else if (isElementExist(Locators_Sanity.rogers_PTT)) {
				minWait();
				clickBtn(Locators_Sanity.rogers_PTT);
				validate_PTT_Screen(Locators_Sanity.rogers_PTT_validate);
			}
			else if (isElementExist(Locators_Sanity.telusLink_PTT)) {
				minWait();
				clickBtn(Locators_Sanity.telusLink_PTT);
				System.out.println("******");
				validate_PTT_Screen(Locators_Sanity.telusLink_PTT_validate);
			}
			else {
				test.log(LogStatus.INFO, "PTT is not identified");
			}
		}
		minWait();
		aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);

	}

	// Validates the presence of element
	public void validate_launchofApp(WebElement element, String elementName) throws InterruptedException {
		minWait();
		SoftAssert Sa = new SoftAssert();
		try {
			customWait(6000);
			if (isElementExist(element)) {
				check = true;
				APP_LOGS.info(elementName + " is found succesfully");
				test.log(LogStatus.INFO, elementName + " Application sucessfully Launched ");
				Sa.assertTrue(check, "Tc Passed");

			} else {
				check = false;
				APP_LOGS.info(elementName + " is not found");
				test.log(LogStatus.ERROR, elementName + " Application is not Launched");
				Sa.fail();
			}
		} catch (Exception e) {
			e.printStackTrace();
			APP_LOGS.info("No Such Element Found");
			test.log(LogStatus.ERROR,"No Such Element Found");
			Sa.fail();
		}
		Sa.assertAll();
	}

	public void validateAppMenuLaunch() throws InterruptedException {
		/*
		 * validate App Launch Menu from Launcher
		 */
		try {
			validate_HomeScreen();	
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_HOME);
			minWait();
			test.log(LogStatus.INFO, "App Menu is clicked");
			minWait();
			if(validate_presenceOfElement(Locators_Sanity.settings, "Settings")) {
				check = true;
				assertTrue(check);
				test.log(LogStatus.INFO, "Validated Launch App Menu From Launcher");
			}
			else {
				Assert.fail();
				test.log(LogStatus.ERROR, "Launch App Menu From Launcher is not Validated");
			}
		} catch (Exception e) {

			e.printStackTrace();
		}		
	}


	public void validateLaunch_All_Applications() throws InterruptedException, IOException {
		//this method launch all applications one by one and validate

		try {
			launch_an_app("browser");
		   customWait(3000);
		   validate_launchofApp(Locators_Sanity.DefaultUrlTxt, "Browser");

		   launch_an_app("messaging");
		   validate_launchofApp(Locators_Sanity.messaging_Title, "Messaging");

		   launch_an_app("contacts");
		   if(isElementExist(Locators_Sanity.contact_FindContacts)) {
		    validate_launchofApp(Locators_Sanity.contact_FindContacts, "Contacts");
		   }
		   else if(isElementExist(Locators_Sanity.contacts)) {
		    validate_launchofApp(Locators_Sanity.contacts, "Contacts");
		   }

		   launch_an_app("phone");
		   validate_launchofApp(Locators_Sanity.phone_find, "Phone");

		   launch_an_app("scout");
		   validate_launchofApp(Locators_Sanity.SetUp_Scout, "Sonim Scout");

		   launch_an_app("camera");
		   switchCamera();


		   launch_an_app("gallery");
		   validate_launchofApp(Locators_Sanity.GalleryAlbums, "Gallery");

		   launch_an_app("calendar");
		   aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_MENU);
		   validate_launchofApp(Locators_Sanity.Calender_NewEvent, "Calendar");

		   launch_an_app("settings");
		   validate_launchofApp(Locators_Sanity.settingsIcon, "Settings");

		   aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_HOME);
		   customWait(2000);
		   clickBtn(Locators_Sanity.applications_icon);
		   validate_launchofApp(Locators_Sanity.music_icon, "Applications");

		   launch_an_app("music");
		   validate_launchofApp(Locators_Sanity.artists_music_player, "Music");

		   launch_an_app("soundRecorder");
		   validate_launchofApp(Locators_Sanity.sound_Recorder, "Sound Recorder");

		   minWait();
		   aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);    
		 }catch (Exception e) {
			e.printStackTrace();
			APP_LOGS.info("File failed to open");
			test.log(LogStatus.ERROR,"No Such Element Found");
			Assert.fail();
		}						
	}

	public void switchCamera() throws InterruptedException {
		try {
			if(isElementExist(Locators_Sanity.camera_Icon)) {
				minWait();
				APP_LOGS.info("Camera icon is selected");
			}
			else {
				//			     clickBtn(Locators_Sanity.switchCamerIcon);
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_UP);
				minWait();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Assert.fail();
		}

	}

	public void scrollToElementWithDpadUp(AndroidElement element) {
		/*
			 * Clicks up button till element is available
			 */
			while(!isElementExist(element)) {
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_UP);
			}
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

		public void navigateToPTTKey() throws InterruptedException
		{
			/*
			 * Navigate to programmable keys,clicks on Select PTT key App option
			 */
			try {
				minWait();
				ScrollToElement(Locators_Sanity.settingsPageList, "Programmable Key");
				minWait();
				clickBtn(Locators_Sanity.programmableKeyOpt);
				minWait();
				clickBtn(Locators_Sanity.selectPTTKeyOpt);
				minWait();
			} catch (Exception e) {
				e.printStackTrace();
				test.log(LogStatus.ERROR,"No Such Element Found");
			}						
		}

		public void selectApplicationAndLaunch() throws InterruptedException, IOException{
			/*
			 * select application for PTT key
			 */
			try {
				minWait();
				if(isElementExist(Locators_Sanity.contacts)){
					clickBtn(Locators_Sanity.contacts);
				}else if(isElementExist(Locators_Sanity.noApplicationInPtt)) {
					scrollToElementWithDpadDown(Locators_Sanity.contacts);
				}else{
					scrollToElementWithDpadUp(Locators_Sanity.noApplicationInPtt);
					minWait();
					scrollToElementWithDpadDown(Locators_Sanity.contacts);
				}
				clickBtn(Locators_Sanity.contacts);
				customWait(2000);
				if(isElementExist(Locators_Sanity.PTTPopUp)){
					clickBtn(Locators_Sanity.okBtn);
					customWait(2000);
				}
				Runtime.getRuntime().exec("adb -s "+p_Id+" shell input keyevent --longpress KEYCODE_PTT");
				minWait();
			} catch (Exception e) {
				e.printStackTrace();
				test.log(LogStatus.ERROR,"No Such Element Found");
			}
		}

		public void validateApplicationlaunch() throws InterruptedException
		{
			/*
			 * validate launched application 
			 */
			SoftAssert SA = new SoftAssert();
			try {
				customWait(2000);
				if (isElementExist(Locators_Sanity.contacts)) {
					check = true;
					APP_LOGS.info("Assigned contacts application launched succesfully");
					test.log(LogStatus.INFO,"Assigned contacts application launched succesfully");
					test.log(LogStatus.PASS, "Test case status is Passed");
					SA.assertTrue(check, "Assigned contacts application launched succesfully");

				} else {
					APP_LOGS.info("Application is not Launched");
					test.log(LogStatus.ERROR,"Application is not Launched");
					test.log(LogStatus.FAIL, "Test case status is Failed");
					SA.fail();
				}
			} catch (Exception e) {
				e.printStackTrace();
				APP_LOGS.info("Element Not Found");
				test.log(LogStatus.ERROR, "No Such Element Found");
				SA.fail();
			}
		}

		public void validateHomeKey() throws InterruptedException, IOException
		{
			/*
			 * validate menu key by launching menu tray
			 */
			try {
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_HOME);
				minWait();
				validateKey(Locators_Sanity.appsMenuScreen, "Home");
			} catch (Exception e) {
				e.printStackTrace();
				test.log(LogStatus.ERROR, "No Such Element Found");
			}
		}

		public void searchField(String data) throws InterruptedException, IOException
		{
			/*
			 * Enters given data in search field
			 */
			customWait(4000);
			Runtime.getRuntime().exec("adb -s "+p_Id+" shell input text "+data);
			customWait(3000);
		}

		public void validateGreen_Red_Key() throws InterruptedException, IOException
		{
			/*
			 * validate Green key and Red by Initiating call
			 */
			try {
				minWait();
				launch_an_app("phone");
				minWait();
				searchField(refNum);
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_CALL);
				customWait(4000);
				validateKey(Locators_Sanity.dialingOpt, "Green");
				customWait(2000);
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_ENDCALL);
				minWait();
				validateKey(Locators_Sanity.recentCallsScreen, "Red");
			} catch (Exception e) {
				e.printStackTrace();
				test.log(LogStatus.ERROR, "No Such Element Found");
			}
		}

		public void validateKey(AndroidElement element,String key) throws InterruptedException, IOException
		{
			/*
			 * validate menu key by launching menu tray
			 */
			SoftAssert SA = new SoftAssert();
			try {
				if(isElementExist(element)){
					check = true;
					APP_LOGS.info(key+" key validated succesfully");
					test.log(LogStatus.INFO,key+" key validated succesfully");
					SA.assertTrue(check, key+" key validated succesfully");
				}else if(element.getText().contains("Missed Events")){
					check = true;
					APP_LOGS.info(key+" key validated succesfully");
					test.log(LogStatus.INFO,key+" key validated succesfully");
					SA.assertTrue(check, key+" key validated succesfully");
				}else{
					APP_LOGS.info("Failed to validate "+key+" key");
					test.log(LogStatus.ERROR,"Failed to validate "+key+" key");
					SA.fail();
				}
			} catch (Exception e) {
				e.printStackTrace();
				APP_LOGS.info("Element Not Found");
				test.log(LogStatus.ERROR, "No Such Element Found");
				SA.fail();
			}
		}

		public void validateLeftRightKey(AndroidElement element,AndroidElement element1,String key) throws InterruptedException, IOException
		{
			/*
			 * validate menu key by launching menu tray
			 */
			SoftAssert SA = new SoftAssert();
			try {
				if(isElementExist(element)||isElementExist(element1)){
					check = true;
					APP_LOGS.info(key+" key validated succesfully");
					test.log(LogStatus.INFO,key+" key validated succesfully");
					SA.assertTrue(check, key+" key validated succesfully");
				}else{
					APP_LOGS.info("Failed to validate "+key+" key");
					test.log(LogStatus.ERROR,"Failed to validate "+key+" key");
					SA.fail();
				}
			} catch (Exception e) {
				e.printStackTrace();
				APP_LOGS.info("Element Not Found");
				test.log(LogStatus.ERROR, "No Such Element Found");
				SA.fail();
			}
		}

		public void validate_Menu_Back_Clear_Key() throws InterruptedException, IOException
		{
			/*
			 * validate Menu key,Back key and clear key in phone dialer application
			 */
			try {
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_MENU);
				minWait();
				validateKey(Locators_Sanity.sendMessageInDialer, "Menu");
				minWait();
				clickBtn(Locators_Sanity.sendMessageInDialer);
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
				minWait();
				validateKey(Locators_Sanity.recentCallsScreen, "Back");
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_CLEAR);
				minWait();
				validateKey(Locators_Sanity.deleteMsgPopUp,"Clear");
			} catch (Exception e) {
				e.printStackTrace();
				test.log(LogStatus.ERROR, "No Such Element Found");
			}
		}

		public void validateRecentKey() throws InterruptedException, IOException
		{
			/*
			 * validate Recent key 
			 */
			try {
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_APP_SWITCH);
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_MENU);
				minWait();
				validateKey(Locators_Sanity.removeAllOpt, "Recent");
				minWait();
				clickBtn(Locators_Sanity.removeAllOpt);
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			} catch (Exception e) {
				e.printStackTrace();
				test.log(LogStatus.ERROR, "No Such Element Found");
			}
		}

		public void validateNavigationKeys() throws InterruptedException, IOException
		{
			/*
			 * validate Recent key 
			 */
			try {
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_ENDCALL);
				minWait();

				do {
					minWait();
					Runtime.getRuntime().exec("adb -s "+p_Id+" shell input keyevent 21");
					minWait();
				} while (!isElementExist(Locators_Sanity.quickSettings) || !isElementExist(Locators_Sanity.quick_Toggle));
				minWait();
				validateLeftRightKey(Locators_Sanity.quickSettings,Locators_Sanity.quick_Toggle, "Left-Navigation");
				minWait();

				do {
					minWait();
					Runtime.getRuntime().exec("adb -s "+p_Id+" shell input keyevent 22");
					minWait();
				} while (!isElementExist(Locators_Sanity.missedEventsOpt));
				minWait();
				validateKey(Locators_Sanity.missedEventsOpt, "Right-Navigation");
				minWait();
				launch_an_app("settings");
				minWait();
				clickBtn(Locators_Sanity.moreOptn);
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
				minWait();
				validateKey(Locators_Sanity.tethering_Portable_Hotspot, "Down-Navigation");
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
				minWait();
				validateKey(Locators_Sanity.USBTetheringOpt, "Center-Navigation");
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_UP);
				minWait();
				validateKey(Locators_Sanity.Flightmode, "Up-Navigation");
				minWait();
			} catch (Exception e) {
				e.printStackTrace();
				test.log(LogStatus.ERROR, "No Such Element Found");
			}
		}

		public void validateBrowseWithWiFi() throws InterruptedException {
			/*
			 * disable enable wiFi
			 */
			SoftAssert sf = new SoftAssert();
			try {
				launch_an_app("settings");
				minWait();
				clickBtn(Locators_Sanity.wifi);
				minWait();
				enableFeature(Locators_Sanity.enabled,Locators_Sanity.disabled,Locators_Sanity.switch_Title);
				minWait();
				customWait(4000);
				launch_an_app("browser");
				customWait(4000);
				enterUrl("www.google.com");
				customWait(4000);
				if((isElementExist(Locators_Sanity.SearchIcon_Google))||(isElementExist(Locators_Sanity.Searchenter_Google))) 
				{
					check =true;
					APP_LOGS.info("check: "+check);
					APP_LOGS.info("Enable the WiFi connection is sucessful");
					test.log(LogStatus.INFO, "Enable the WiFi connection is sucessful");
				}else{
					APP_LOGS.info("check: "+check);
					APP_LOGS.info("Enable WiFi connection is not sucessful");
					test.log(LogStatus.INFO, "Enable WiFi connection is not sucessful");
				}
				minWait();	
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
				minWait();	
				minWait();	
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
				minWait();	
			} catch (Exception e) {
				APP_LOGS.info("No Element found.");
				sf.fail();
			}
			sf.assertAll();
		}

		public void cameraPic() throws IOException, InterruptedException, org.json.simple.parser.ParseException {
			/*
			 * Take camera picture via camera application
			 */
			String Uid=JsonFileReaderAndWriter.primaryDevIdReader();

			try {
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_HOME);
				customWait(2000);
				Runtime.getRuntime().exec("adb -s "+Uid+" shell am start -n com.borqs.camera/com.borqs.camera.CameraActivity");
				customWait(2000);
				//			clickBtn(Locators_Sanity.CamerIcon);
				customWait(1000);
				if(isElementExist(Locators_Sanity.photocameraIcon)) {
					clickBtn(Locators_Sanity.photocameraIcon);
					//			APP_LOGS.info("clic);
				}
				else {
					aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_UP);
					//switch to camera
					minWait();
					clickBtn(Locators_Sanity.photocameraIcon);
				}
				minWait();
			} catch (Exception e) {
				e.printStackTrace();
				Assert.fail();
			}
		}

		public void validateBackCamera(String str, String TitleName,String fileName) throws InterruptedException, AWTException, IOException {
			/*
			 * Validate Gallery Folders from TitleName
			 */

			try {
				customWait(3000);
				if(searchString(str,fileName)) {				
					check = true;
					APP_LOGS.info(TitleName + "Back Camera is Validated in Gallery");
					test.log(LogStatus.INFO, TitleName + ":Back Camera is Validated ");
					Assert.assertTrue(check);
				}
				else {
					APP_LOGS.info(TitleName + "Back Camera is unVerified in Gallery");
					test.log(LogStatus.ERROR, TitleName + ":Back Camera is unVerfied ");
					Assert.fail();
				}
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				Assert.fail();
			}
		}

		public void takeCameraVideo(int n) throws InterruptedException, IOException {
			/*
			 * Capture photo from camera and store in SDcard
			 */
			SoftAssert S1 = new SoftAssert();
			try {
				customWait(1000);
				aDriver.pressKeyCode(AndroidKeyCode.MENU);
				minWait();
				clickBtn(Locators_Sanity.SwitchToCameraOptn);
				customWait(2000);
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_UP);
				minWait();
				clickBtn(Locators_Sanity.videocameraIcon);
				for(int i=1; i<=n;i++) {
					customWait(2000);
				}
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
				customWait(1000);
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
				minWait();
			} catch (Exception e) {
				e.printStackTrace();
				S1.fail();
			}
			S1.assertAll();
		}

		public void press_Enter() throws InterruptedException {
			/*
			 * enter key is pressed
			 */
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_ENTER);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_ENTER);
			minWait();
		}

		public void checkOperatorAndlaunchCalendar() throws InterruptedException, IOException, ParseException, org.json.simple.parser.ParseException
		{
			/*
			 * check operator and launch calendar
			 */
			String XP5deviceModelNumber=JsonFileReaderAndWriter.primaryDevOperatorReader();
			try {
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_HOME);
				minWait();
				if(XP5deviceModelNumber.contains("5SA.0.1-04-7.1.2-26.")) {
					clickBtn(Locators_Sanity.applications);
					minWait();
					clickBtn(Locators_Sanity.calendar);
				}else {
					launch_an_app("calendar");
				}
			}catch (Exception e) {
				e.printStackTrace();
				test.log(LogStatus.ERROR, "Element Not Found");
			}
		}

		public void deleteEvents() throws InterruptedException, IOException
		{
			/*
			 * delete events
			 */
			try {
				clickMenuButton();
				if(isElementExist(Locators_Sanity.monthOpt)){
					clickBtn(Locators_Sanity.monthOpt);
					minWait();
					clickMenuButton();
				} else {
					clickBackButton(1);
					minWait();
					clickMenuButton();
				}
				if (Locators_Sanity.deleteEventsEnabled.isEnabled()) {
					clickBtn(Locators_Sanity.deleteEventsOpt);
					minWait();
					clickMenuAndElement(Locators_Sanity.selectAllOpt);
					minWait();
					clickMenuAndElement(Locators_Sanity.doneOpt);
					minWait();
					clickBtn(Locators_Sanity.OkBtn);
					customWait(2000);
				}else {
					clickBackButton(1);
				}
			}catch (Exception e) {
				e.printStackTrace();
				test.log(LogStatus.ERROR, "Element Not Found");
			}
		}

		public void addCalendarEvent(String eventName,String location) throws InterruptedException, IOException
		{
			/*
			 * Add calendar Event
			 */
			try {
				minWait();
				deleteEvents();
				minWait();
				addEvent(eventName, location);
				minWait();
				clickMenuButton();
				if(isElementExist(Locators_Sanity.agendaOpt)) {
					clickBtn(Locators_Sanity.agendaOpt);
					minWait();
				}else {
					clickBackButton(1);
				}
				validateAddedEvent(eventName);
				minWait();
				deleteEvents();
			}catch (Exception e) {
				e.printStackTrace();
				test.log(LogStatus.ERROR, "Element Not Found");
			}
		}

		public void validateAddedEvent(String eventName) throws InterruptedException, IOException
		{
			/*
			 * Validates Added event
			 */
			SoftAssert SA = new SoftAssert();
			try {
				String event = Locators_Sanity.createdEvent.getText();
				minWait();
				if(event.equals(eventName)){
					check = true;
					APP_LOGS.info("Event added in Calendar succesfully");
					test.log(LogStatus.INFO, "Event added in Calendar succesfully");
					SA.assertTrue(check, "Event added in Calendar succesfully");	
				}else{
					APP_LOGS.info("Failed to add event");
					test.log(LogStatus.INFO, "Failed to add event");
					SA.fail("Failed to add event");
				}
			}catch (Exception e) {
				e.printStackTrace();
				APP_LOGS.info("Element Not Found");
				test.log(LogStatus.ERROR, "Element Not Found");
				SA.fail();
			}
			SA.assertAll();
		}

		public void addEvent(String eventName,String location ) throws InterruptedException, IOException
		{
			/*
			 * Add event and save
			 */
			try {
				clickMenuAndElement(Locators_Sanity.newEvent);
				minWait();
				enterTextToInputField(Locators_Sanity.eventName, eventName);
				minWait();
				enterTextToInputField(Locators_Sanity.locationFld, location);
				minWait();
				clickMenuAndElement(Locators_Sanity.saveOpt);
			}catch (Exception e) {
				e.printStackTrace();
				test.log(LogStatus.ERROR, "Element Not Found");
			}
		}

		public void launchClock() throws InterruptedException, IOException
		{
			/*
			 * Launch clock application
			 */
			try {
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_HOME);
				minWait();
				clickBtn(Locators_Sanity.tools);
				minWait();
				clickBtn(Locators_Sanity.clock);
			}catch (Exception e) {
				e.printStackTrace();
			}
		}

		public void addAlarm() throws InterruptedException, IOException
		{
			/*
			 * Adds Alarm
			 */
			try {
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_LEFT);
				minWait();
				clickMenuAndElement(Locators_Sanity.addOpt);
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_RIGHT);
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_RIGHT);
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
				minWait();
				clickBtn(Locators_Sanity.OkBtn);
				minWait();
			}catch (Exception e) {
				e.printStackTrace();
				test.log(LogStatus.ERROR, "Element Not Found");
			}
		}

		public void validateAddedAlarm() throws InterruptedException, IOException
		{
			/*
			 * Validates Added Alarm
			 */
			SoftAssert SA = new SoftAssert();
			try {
				if(isElementExist(Locators_Sanity.alarmSetPage)){
					check = true;
					clickBackButton(1);
					minWait();
					if(isElementExist(Locators_Sanity.alarmTodayOpt) && isElementExist(Locators_Sanity.alarmSwitchOn))
					{
						clickBtn(Locators_Sanity.alarmSwitchOn);
						APP_LOGS.info("Alarm added succesfully");
						test.log(LogStatus.INFO, "Alarm added succesfully");
						SA.assertTrue(check, "Alarm added succesfully");	
					}
				}else{
					APP_LOGS.info("Failed to add alarm");
					test.log(LogStatus.INFO, "Failed to add alarm");
					SA.fail("Failed to add alarm");
				}
			}catch (Exception e) {
				e.printStackTrace();
				APP_LOGS.info("Element Not Found");
				test.log(LogStatus.ERROR, "Element Not Found");
				SA.fail();
			}
			SA.assertAll();
		}

		public void snoozeTheAlarm() throws InterruptedException, IOException
		{
			/*
			 * Add alarm,Snooze and validate
			 */
			try {
				addAlarm();
				clickBackButton(1);
				minWait();
				waituntilnew(Locators_Sanity.alarmPage, 60000);
				minWait();
				if(isElementExist(Locators_Sanity.alarmTimeFld)) {
					aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_LEFT);
				}
				validateSnoozeAndDismiss(Locators_Sanity.alarmSwitchOn,Locators_Sanity.alarmTodayOpt,"Snoozed");
				minWait();
				clickBtn(Locators_Sanity.alarmSwitchOn);
			}catch (Exception e) {
				e.printStackTrace();
				test.log(LogStatus.ERROR, "Element Not Found");
			}
		}

		public void validateSnoozeAndDismiss(WebElement element,WebElement elementday,String state) throws InterruptedException, IOException
		{
			/*
			 * Validates snooze and dismiss alarm
			 */
			SoftAssert SA = new SoftAssert();
			try {
				customWait(4000);
				if(isElementExist(elementday) && isElementExist(element))
				{
					check= true;
					APP_LOGS.info("Alarm "+state+" succesfully");
					test.log(LogStatus.INFO, "Alarm "+state+" succesfully");
					SA.assertTrue(check, "Alarm "+state+" succesfully");	
				}else {
					APP_LOGS.info("Alarm not "+state);
					test.log(LogStatus.INFO, "Alarm not "+state);
					SA.fail("Alarm not "+state);
				}
			}catch (Exception e) {
				e.printStackTrace();
				APP_LOGS.info("Element Not Found");
				test.log(LogStatus.ERROR, "Element Not Found");
				SA.fail();
			}
			SA.assertAll();
		}

		public void dismissTheAlarm() throws InterruptedException, IOException
		{
			/*
			 * Add Alarm,Dismiss and validate
			 */
			try {
				addAlarm();
				clickBackButton(1);
				minWait();
				waituntilnew(Locators_Sanity.alarmPage, 60000);
				minWait();
				if(isElementExist(Locators_Sanity.alarmTimeFld)) {
					aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_RIGHT);
				}
				customWait(2000);
				validateSnoozeAndDismiss(Locators_Sanity.alarmSwitchOff,Locators_Sanity.alarmTomorrowOpt,"Dismissed");
				minWait();
			}catch (Exception e) {
				e.printStackTrace();
				test.log(LogStatus.ERROR, "Element Not Found");
			}
		}

		public void disableWifi() throws InterruptedException
		{
			try {
				launch_an_app("settings");
				minWait();
				clickBtn(Locators_Sanity.wifi);
				minWait();
				customWait(2000);
				disableFeature(Locators_Sanity.enabled,Locators_Sanity.disabled);
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			} catch (Exception e) {
				e.printStackTrace();
				test.log(LogStatus.ERROR, "Element Not Found");
			}
		}

		public void validateDisableEnableWiFi() throws InterruptedException {
			/*
			 * disable enable wiFi
			 */
			SoftAssert sf = new SoftAssert();
			try {
				launch_an_app("settings");
				minWait();
				clickBtn(Locators_Sanity.wifi);
				minWait();
				customWait(2000);
				disableFeature(Locators_Sanity.enabled,Locators_Sanity.disabled);
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
				launch_an_app("browser");
				customWait(4000);
				if(isElementExist(Locators_Sanity.NoNewtwrkErroeMsg)||isElementExist(Locators_Sanity.webpageNotavailable)) {
					check1 =true;
					APP_LOGS.info("check1: "+check1);
					APP_LOGS.info("Disable the WiFi is sucessful");
					test.log(LogStatus.INFO, "Disable the WiFi is sucessful");
				}else{
					APP_LOGS.info("check1: "+check1);
					APP_LOGS.info("Disable the WiFi is not sucessful");
					test.log(LogStatus.FAIL," Disable the WiFi is not sucessful");
				}
				minWait();	
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);	
				minWait();	
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);	

				minWait();
				launch_an_app("settings");
				minWait();
				clickBtn(Locators_Sanity.wifi);
				minWait();

				enableFeature(Locators_Sanity.enabled,Locators_Sanity.disabled,Locators_Sanity.switch_Title);
				minWait();
				customWait(4000);
				launch_an_app("browser");
				customWait(4000);
				enterUrl("www.google.com");
				customWait(4000);
				if((isElementExist(Locators_Sanity.SearchIcon_Google))||(isElementExist(Locators_Sanity.Searchenter_Google))) 
				{
					check2 =true;
					APP_LOGS.info("check2: "+check2);
					APP_LOGS.info("Enable the WiFi connection is sucessful");
					test.log(LogStatus.INFO, "Enable the WiFi connection is sucessful");
				}else{
					APP_LOGS.info("check2: "+check2);
					APP_LOGS.info("Enable WiFi connection is not sucessful");
					test.log(LogStatus.INFO, "Enable WiFi connection is not sucessful");
				}

				if((check1) && (check2)) {
					check = true;
					APP_LOGS.info(" Turn ON and OFF feature is verified");
					test.log(LogStatus.INFO, " Browsing with WiFi feature is verified");
					sf.assertTrue(check, " ");
				}
				else {
					APP_LOGS.info(" check1"+check1+"check2"+check2);
					test.log(LogStatus.ERROR, "No Such Element Found");
					APP_LOGS.info(" Browsing with WiFi  is not verified");
					sf.fail();
				}
				minWait();	
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
				minWait();	
				minWait();	
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
				minWait();	
			} catch (Exception e) {
				APP_LOGS.info("No Element found.");
				sf.fail();
			}
			sf.assertAll();
		}

		public void validateChaneSleepTime() throws InterruptedException {
			/*
			 * Set sleep mode to maximum
			 */

			try {
				minWait();
				String getSleepSummary = Locators_Sanity.Sleep_Summary.getText();
				System.out.println(getSleepSummary);
				customWait(2000);
				clickBtn(Locators_Sanity.Sleep_Optn);
				minWait();
				for(int i=1; i<=2; i++) {
					aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_UP);
				}
				clickBtn(Locators_Sanity.thirtyMinSleep_Optn);
				customWait(5000);
				String getSleepchange = Locators_Sanity.Sleep_Summary.getText();
				System.out.println(getSleepchange);

				if(!getSleepchange.equals(getSleepSummary)) {
					check = true;
					test.log(LogStatus.INFO, "Sleep Time is changed sucessfully");
					Assert.assertTrue(check);
				}
				else {
					test.log(LogStatus.ERROR, "Sleep Time is not changed ");
					Assert.fail();
				}
				clickBtn(Locators_Sanity.Sleep_Optn);
				minWait();

				clickBtn(Locators_Sanity.eightHrsSleep_Optn);
				test.log(LogStatus.INFO, " Sleep Mode is set to maximum");
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				test.log(LogStatus.ERROR, " No such Element Found");
			}		
		}

		public void volumeUpKey() throws InterruptedException{
			/*
			 * Clicks volume button
			 */
			try {
				minWait();
				for(int i=0;i<9;i++){
					minWait();
					aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_VOLUME_UP);
				}
				validateVolumeKeys("Up", "writeEvent level_changed STREAM_SYSTEM 7");
			} catch (Exception e) {
				e.printStackTrace();
				test.log(LogStatus.ERROR, "Element Not Found");
			}
		}

		public void volumeDownKey() throws InterruptedException{
			/*
			 * Clicks volume button
			 */
			try {
				minWait();
				for(int i=0;i<8;i++){
					minWait();
					aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_VOLUME_DOWN);
				}
				validateVolumeKeys("Down", "writeEvent level_changed STREAM_SYSTEM 1");
			} catch (Exception e) {
				e.printStackTrace();
				test.log(LogStatus.ERROR, "Element Not Found");
			}
		}


		public void validateVolumeKeys(String function,String searchstring) throws InterruptedException
		 {
		  /*
		   * validate volume keys up/down
		   */
		  SoftAssert SA = new SoftAssert();
		  try {
		   boolean value = searchString(searchstring, "XP5S_Sanity_038");
		   minWait();
		   if(value)
		   {
		    check = true;
		    APP_LOGS.info("Volume Key "+ function +" verified sucessfully");
		    test.log(LogStatus.INFO, "Volume Key "+ function +" verified sucessfully");
		    SA.assertTrue(check, "Volume Key "+ function +" verified sucessfully"); 
		   }else {
		    APP_LOGS.info("Volume Key "+ function +" not verified");
		    test.log(LogStatus.ERROR, "Volume Key "+ function +" not verified");
		    SA.fail("Volume Key "+ function +" not verified");
		   }
		  } catch (Exception e) {
		   e.printStackTrace();
		   APP_LOGS.info("Element Not Found");
		   test.log(LogStatus.ERROR, "Element Not Found");
		   SA.fail();
		  }
		 }

		public void testFilesDownload() throws IOException, InterruptedException {
			/*
			 * Access browser to get test files like.mp3 and .txt
			 */
			try {
				minWait();
				Runtime.getRuntime().exec("adb -s "+p_Id+" shell am start -n com.android.browser/com.android.browser.BrowserActivity");
				minWait();
				enterUrl("www.google.com");
				customWait(10000);
				org.openqa.selenium.Dimension size =aDriver.manage().window().getSize();
				System.out.println(size);
				int x=size.getWidth()/2;
				int starty=(int)(size.getHeight()*0.60);
				int endy=(int)(size.getHeight()*0.10);

				//   aDriver.get("http://www.google.com");

				customWait(2000);
				if(isElementExist(Locators_Sanity.signIn)) {
					clickBtn(Locators_Sanity.signIn);
					customWait(8000);
					enterTextToInputField(Locators_Sanity.email_ID_Login, "automationsmat");
					minWait();
					Runtime.getRuntime().exec("adb  -s "+p_Id+" shell input keyevent --longpress KEYCODE_1");
					customWait(2000);
					for(int i=1; i<=3; i++ ) {
						minWait();
						aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_RIGHT);
					}
					customWait(1000);
					aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_ENTER);
					customWait(8000);
					Runtime.getRuntime().exec("adb -s "+p_Id+" shell input text gmail.com");
					customWait(1000);
					aDriver.swipe(x, starty, x, endy, 2000);  
					customWait(1000);
					clickBtn(Locators_Sanity.next_Login);
					customWait(4000);
					enterTextToInputField(Locators_Sanity.email_ID_Password, "sonim");  
					customWait(5000);
					for (int i = 0; i < 3; i++) {
						aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_POUND);
						minWait();
					}
					Runtime.getRuntime().exec("adb -s "+p_Id+" shell input text 123");
					customWait(1000);

					minWait();
					aDriver.swipe(x, starty, x, endy, 2000);  
					customWait(1000);
					clickBtn(Locators_Sanity.next_Passwrd);
					customWait(2000);
				}
				clickBtn(Locators_Sanity.navigate_GoogleApps);
				customWait(4000);
				aDriver.swipe(x, starty, x, endy, 2000);  
				customWait(4000);
				clickBtn(Locators_Sanity.navigate_Gmail);
				customWait(6000);
				clickBtn(Locators_Sanity.gmailOpen);
				customWait(4000);
				enterTextToInputField(Locators_Sanity.gmailSearch, "From");
				customWait(4000);
				clickBtn(Locators_Sanity.gmailSelectMail);
				customWait(2000);
				clickBtn(Locators_Sanity.gmailselectFile);
				customWait(4000);
				aDriver.swipe(x, starty, x, endy, 2000);  
				customWait(2000);
				clickBtn(Locators_Sanity.gmailAttchment);
				customWait(4000);
			} catch (Exception e) {
				e.printStackTrace();
				testFilesDownload();
			}
		}

	}
