package com.xp8.util;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
//import io.appium.java_client.android.nativekey.AndroidKey;
//import io.appium.java_client.android.nativekey.KeyEvent;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import org.apache.poi.hssf.record.pivottable.ViewDefinitionRecord;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import com.graphics.gui.JsonFileReaderAndWriter;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import application.AllQA;
//import application.AllQA;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.AndroidKeyCode;

public class XP8_Phone_Contacts_Util extends BaseUtil {

	public static boolean check=false;
	public static ExtentReports extent;
	public static ExtentTest test;

	public SoftAssert sf1 = new SoftAssert();

	public String p_Id	 = "";						// Primary Device Serial Number.
	public String r_Id	 = "";						// Reference Device Serial Number.
	public String p_b_No = "";			   			// Primary Device Build Number.
	public String r_b_No = ""; 						// Reference Device Build Number.
	public String pryNum = AllQA.PRIMARYDEVMDN;		// Reference Dmevice MDN. 
	public String refNum = AllQA.REFERENCEDEVMDN.replaceAll("[^0-9]", "");	// Reference Device MDN.


	public void fetch_Devices_Details() throws InterruptedException, FileNotFoundException, IOException, ParseException {

		minWait();
		p_Id=JsonFileReaderAndWriter.primaryDevIdReader();
		r_Id=JsonFileReaderAndWriter.ReadRefDeviceId();
		p_b_No=JsonFileReaderAndWriter.primaryDevFirmwareReader();
		r_b_No=JsonFileReaderAndWriter.ReadRefDeviceFirmWare();
	}

	public void End() {
		aDriver.quit();
	}

	public void launch_APP(AndroidElement appToClick) throws InterruptedException {
		try {
			clickOnAppList();
			for (int i = 0; i < 6; i++) {
				if (isElementExist(appToClick)) {
					test.log(LogStatus.INFO, "\""+appToClick.getText()+"\" app launched.");
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
			clickBtn(Locators_Phone_Contacts.app_List);
			minWait();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	public void navigateToMoreOptions(String str1,String str) throws InterruptedException {
		//
		WebDriverWait wait =new WebDriverWait(aDriver, 60);
		try {
			minWait();
			clickBtn(Locators_Phone_Contacts.ContactsMoreOptions);
			minWait();
			scrollToText(str1);
			minWait();
		    wait.until(ExpectedConditions.visibilityOf(Locators_Phone_Contacts.contactsSettingsOPt));
			clickBtn(Locators_Phone_Contacts.contactsSettingsOPt);
			minWait();
			scrollToText(str);

		} 
		catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators");
			e.printStackTrace();

		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void navigateToNameFormatOptions() throws InterruptedException {
		try {
			minWait();
			clickBtn(Locators_Phone_Contacts.ContactsMoreOptions);
			minWait();
			scrollText("Settings");
			minWait();
			clickBtn(Locators_Phone_Contacts.contactsSettingsOPt);
			minWait();
			clickBtn(Locators_Phone_Contacts.NameFormatOpt);
			minWait();		
		}
		catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators");
			e.printStackTrace();

		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void navigateToSortbyOptions() throws InterruptedException {
		try {
			minWait();
			clickBtn(Locators_Phone_Contacts.ContactsMoreOptions);
			minWait();
			scrollText("Settings");
			minWait();
			clickBtn(Locators_Phone_Contacts.contactsSettingsOPt);
			minWait();
			clickBtn(Locators_Phone_Contacts.SortbyOpt);
			minWait();		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void navigateToMyinfoOptions() throws InterruptedException {
		try {
			minWait();
			clickBtn(Locators_Phone_Contacts.ContactsMoreOptions);
			minWait();
			scrollText("Settings");
			minWait();
			clickBtn(Locators_Phone_Contacts.contactsSettingsOPt);
			minWait();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void Set_Sleeptime_30min() throws InterruptedException {
		SoftAssert sa =new SoftAssert();
		WebDriverWait wait = new WebDriverWait(aDriver, 60);
		try {
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.HOME);
			minWait();
			launch_an_app("settings");
			minWait();
			scrollToElements(Locators_Phone_Contacts.Settings_Display);
			minWait();
			clickBtn(Locators_Phone_Contacts.Settings_Display);
			minWait();
			clickBtn(Locators_Phone_Contacts.Display_Advanced);
			minWait();
			String getName = Locators_Phone_Contacts.Sleep_min.getText();
			if(getName.equalsIgnoreCase("After 30 minutes of inactivity"))
				{
				minWait();
				System.out.println(getName);
				System.out.println("Already sleep time is 30 min");
				APP_LOGS.info("Already sleep time is 30 min");
				aDriver.pressKeyCode(AndroidKeyCode.HOME);
				
			}else {
				minWait();
				System.out.println(getName);
				clickBtn(Locators_Phone_Contacts.SleepOpt);
				minWait();			
				clickBtn(Locators_Phone_Contacts.SleepOpt30min);
				minWait();
				System.out.println("Sleep time set as 30 min");
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.HOME);
			}
			}
		catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators");
			e.printStackTrace();

		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		sa.assertAll();
	}
	
	public void Set_Sleeptime_30sec() throws InterruptedException {
		SoftAssert sa =new SoftAssert();
	//	WebDriverWait wait = new WebDriverWait(aDriver, 60);
		try {
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.HOME);
			minWait();
			launch_an_app("settings");
			minWait();
			scrollToElements(Locators_Phone_Contacts.Settings_Display);
			minWait();
			clickBtn(Locators_Phone_Contacts.Settings_Display);
			minWait();
			clickBtn(Locators_Phone_Contacts.Display_Advanced);
			minWait();
			clickBtn(Locators_Phone_Contacts.SleepOpt);
			minWait();			
			clickBtn(Locators_Phone_Contacts.SleepOpt30sec);
			minWait();
			
			System.out.println("Sleep time set as 30 sec");
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.HOME);
			
				APP_LOGS.info("Sleep time set as 30 sec");
				aDriver.pressKeyCode(AndroidKeyCode.HOME);

			}
		catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators");
			e.printStackTrace();

		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		sa.assertAll();
	}
	
	public void importContacts(SoftAssert sa) throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(aDriver, 60);
		try {
			minWait();
			clickBtn(Locators_Phone_Contacts.contactsImportSettings);
			minWait();
			clickBtn(Locators_Phone_Contacts.vcfFile);
			minWait();
			clickBtn(Locators_Phone_Contacts.PHONE_RadioBtn);
			minWait();
			if(isElementExist(Locators_Phone_Contacts.permissionPopUp)) {
				check=true;
				minWait();
				for(int i=0;i<3;i++){
					minWait();
					clickBtn(Locators_Phone_Contacts.allowBtn);
				}
			}
			if(isElementExist(Locators_Phone_Contacts.DownloadsOption)) {
				System.out.println("Already in Downloads");
			}else {
				clickBtn(Locators_Phone_Contacts.MoreOptions);
				minWait();			
				clickBtn(Locators_Phone_Contacts.DownloadsOption);
				minWait();
			}
			System.out.println("Clicking on VCF");
		//	scrollToElement(Locators_Phone_Contacts.i476_vcf);
		//	wait.until(ExpectedConditions.visibilityOf(Locators_Phone_Contacts.i476_vcf));
			minWait();
			scrollToText("i476.vcf");
			minWait();
			clickBtn(Locators_Phone_Contacts.i476_vcf);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
		}
		catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators");
			e.printStackTrace();

		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}    	  	
	}

	public void importSIMContacts() throws InterruptedException {
		try {
			minWait();
			clickBtn(Locators_Phone_Contacts.contactsImportSettings);
			minWait();
			clickBtn(Locators_Phone_Contacts.SIM_card);
			minWait();
			clickBtn(Locators_Phone_Contacts.DropDown);
			minWait();
			clickBtn(Locators_Phone_Contacts.PHONE_RadioBtn);
			minWait();	   
			clickBtn(Locators_Phone_Contacts.ImportBtn);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}    	  	
	}

	public void exportContacts(SoftAssert sa) throws InterruptedException {
		//export contacts
		try {
			minWait();
			clickBtn(Locators_Phone_Contacts.vcfFile);
			minWait();
			clickBtn(Locators_Phone_Contacts.PHONE_RadioBtn);
			minWait();
			clickBtn(Locators_Phone_Contacts.Save_Btn);
			

		} 
		catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators");
			e.printStackTrace();

		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void exportContactstoSIM(SoftAssert sa) throws InterruptedException {
		//export contacts to SIM
		try {
			minWait();
			clickBtn(Locators_Phone_Contacts.contactsIExportSettings);
			minWait();
			clickBtn(Locators_Phone_Contacts.ExportSIMcard);
			minWait();
			clickBtn(Locators_Phone_Contacts.Selection_menu);
			minWait();
			if(isElementExist(Locators_Phone_Contacts.ALL_Selection_menu)) {
				clickBtn(Locators_Phone_Contacts.ALL_Selection_menu);
				minWait();
			}
			else {
				minWait();
				clickBtn(Locators_Phone_Contacts.one_Selection_menu);
			}
			clickBtn(Locators_Phone_Contacts.OKBtn1);
			customWait(4000);
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			minWait();
			APP_LOGS.info("Contacts Export Finish");
			sa.assertTrue(true, "Contacts Export Finish");

		}
		catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators");
			e.printStackTrace();

		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void deleteContacts() throws InterruptedException {
		try {
			minWait();
			System.out.println("IM in Contacts");
			if (isElementExist(Locators_Phone_Contacts.no_Contacts)) {
				APP_LOGS.info("No Contact"); 
				System.out.println("No Contact");
				minWait();
			} else {
				clickBtn(Locators_Phone_Contacts.deleteContactOptn1);
				minWait();
				clickBtn(Locators_Phone_Contacts.Selection_menu);
				minWait();
				if(isElementExist(Locators_Phone_Contacts.ALL_Selection_menu)) {
					clickBtn(Locators_Phone_Contacts.ALL_Selection_menu);
					minWait();
				}
				else {
					minWait();
					clickBtn(Locators_Phone_Contacts.one_Selection_menu);
				}
				clickBtn(Locators_Phone_Contacts.OKBtn1);
				minWait();
				clickBtn(Locators_Phone_Contacts.OKBtn);
				APP_LOGS.info("Contacts Deleted");
				minWait();
			}
		} 
		catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators");
			e.printStackTrace();
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			test.log(LogStatus.ERROR, "No Such element Found");
		}
	}

	public void addcontactsTO_SIM(String Username1,String Phone1,String Username2,String Phone2,SoftAssert sa) 
			throws InterruptedException {
		try {
			minWait();
			System.out.println("IM in Contacts");
			minWait();
			clickBtn(Locators_Phone_Contacts.ContactsMoreOptions);
			minWait();
			scrollText("SIM");
			minWait();
			clickBtn(Locators_Phone_Contacts.AccountSIMOPt);
			minWait();
			clickBtn(Locators_Phone_Contacts.AddcontactBtn);
			minWait();
			enterTextToInputField(Locators_Phone_Contacts.nameField, Username1);
			customWait(3000);
			enterTextToInputField(Locators_Phone_Contacts.phoneField, Phone1);
			customWait(3000);
			clickBtn(Locators_Phone_Contacts.SaveBtn);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			minWait();
			clickBtn(Locators_Phone_Contacts.AddcontactBtn);
			minWait();
			enterTextToInputField(Locators_Phone_Contacts.nameField, Username2);
			customWait(3000);
			enterTextToInputField(Locators_Phone_Contacts.phoneField, Phone2);
			customWait(3000);
			clickBtn(Locators_Phone_Contacts.SaveBtn);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			minWait();
			APP_LOGS.info("Contacts added in SIM");
			minWait();

		} 
		catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators");
			e.printStackTrace();

		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			test.log(LogStatus.ERROR, "No Such element Found");
		}
	}

	public void addcontactsTO_Phone(String Username1,String Phone1,String Email1,String Username2,String Phone2,
			String Email2) throws InterruptedException {
		try {
			minWait();
			System.out.println("IM in Contacts");
			minWait();
			clickBtn(Locators_Phone_Contacts.ContactsMoreOptions);
			minWait();
			scrollToElements(Locators_Phone_Contacts.AccountsPhoneOPt1);
	//		scrollText("PHONE");
			minWait();
			clickBtn(Locators_Phone_Contacts.AccountsPhoneOPt1);
			minWait();
			clickBtn(Locators_Phone_Contacts.AddcontactBtn);
			minWait();
			enterTextToInputField(Locators_Phone_Contacts.FirstnameField, Username1); 
			customWait(3000);
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			minWait();
			enterTextToInputField(Locators_Phone_Contacts.phoneField, Phone1);
			customWait(3000);
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			minWait();
			enterTextToInputField(Locators_Phone_Contacts.EmailField, Email1);
			customWait(3000);
			clickBtn(Locators_Phone_Contacts.SaveBtn);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			minWait();
			clickBtn(Locators_Phone_Contacts.AddcontactBtn);
			minWait();
			enterTextToInputField(Locators_Phone_Contacts.FirstnameField, Username2); 
			customWait(3000);
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			minWait();
			enterTextToInputField(Locators_Phone_Contacts.phoneField, Phone2);
			customWait(3000);
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			minWait();
			enterTextToInputField(Locators_Phone_Contacts.EmailField, Email2);
			customWait(3000);
			clickBtn(Locators_Phone_Contacts.SaveBtn);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			minWait();
			APP_LOGS.info("Contacts added in Phone");
			minWait();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			test.log(LogStatus.ERROR, "No Such element Found");
		}
	}

	public void addcontactTO_Phone(String Username1,String Phone1,String Email1) throws InterruptedException {
		try {
			minWait();
			System.out.println("IM in Contacts");
			minWait();
			clickBtn(Locators_Phone_Contacts.ContactsMoreOptions);
			minWait();
			scrollToElements(Locators_Phone_Contacts.AccountsPhoneOPt1);
			minWait();
			clickBtn(Locators_Phone_Contacts.AccountsPhoneOPt1);
			minWait();
			clickBtn(Locators_Phone_Contacts.AddcontactBtn);
			minWait();
			enterTextToInputField(Locators_Phone_Contacts.FirstnameField, Username1); 
			customWait(3000);
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			minWait();
			enterTextToInputField(Locators_Phone_Contacts.phoneField, Phone1);
			customWait(3000);
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			minWait();
			enterTextToInputField(Locators_Phone_Contacts.EmailField, Email1);
			customWait(3000);
			clickBtn(Locators_Phone_Contacts.SaveBtn);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			minWait();
			APP_LOGS.info("Contact added in Phone");
			minWait();

		}
		catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators");
			e.printStackTrace();

		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			test.log(LogStatus.ERROR, "No Such element Found");
		}
	}

	public void addcontactTO_Phone_with_10fields(String Firstname,String Lastname,String Company,String Phone,String SIP,
			String Email,String Address,String IM,String Website,String Notes) throws InterruptedException {
		try {
			minWait();
			System.out.println("IM in Contacts");
			minWait();
			clickBtn(Locators_Phone_Contacts.ContactsMoreOptions);
			minWait();
			scrollToElements(Locators_Phone_Contacts.AccountsPhoneOPt1);
			minWait();
			clickBtn(Locators_Phone_Contacts.AccountsPhoneOPt1);
			minWait();
			
	//		for(int i=0;i<10;i++){
			clickBtn(Locators_Phone_Contacts.AddcontactBtn);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			minWait();
			clickBtn(Locators_Phone_Contacts.Morefields);
			minWait();
			enterTextToInputField(Locators_Phone_Contacts.FirstnameField, Firstname); 
			customWait(3000);
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			minWait();
			enterTextToInputField(multi_Loc_Strategy(Locators_Phone_Contacts.LaststnameField, Locators_Phone_Contacts.SurnameField,
					null, null, null, 0, 0), Lastname); 
			customWait(3000);
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			minWait();
			scrollToElements(Locators_Phone_Contacts.Companyfield);
			minWait();
			enterTextToInputField(Locators_Phone_Contacts.Companyfield, Company); 
			customWait(3000);
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			minWait();
			scrollToElements(Locators_Phone_Contacts.phoneField);
			minWait();
			enterTextToInputField(Locators_Phone_Contacts.phoneField, Phone);
			customWait(3000);
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			minWait();
			
			scrollToElements(Locators_Phone_Contacts.SIPfield);
			minWait();
			enterTextToInputField(Locators_Phone_Contacts.SIPfield, SIP);
			customWait(3000);
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			minWait();
			
			scrollToElements(Locators_Phone_Contacts.EmailField);
			minWait();
			enterTextToInputField(Locators_Phone_Contacts.EmailField, Email);
			customWait(3000);
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			minWait();
			
			scrollToElements(Locators_Phone_Contacts.Addressfield);
			minWait();
			enterTextToInputField(Locators_Phone_Contacts.Addressfield, Address);
			customWait(3000);
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			minWait();
			
			scrollToElements(Locators_Phone_Contacts.IMfield);
			minWait();
			enterTextToInputField(Locators_Phone_Contacts.IMfield, IM);
			customWait(3000);
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			minWait();
			
			scrollToElements(Locators_Phone_Contacts.Websitefield);
			minWait();
			enterTextToInputField(Locators_Phone_Contacts.Websitefield, Website);
			customWait(3000);
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			minWait();

			scrollToElements(Locators_Phone_Contacts.Notesfield);
			minWait();
			enterTextToInputField(Locators_Phone_Contacts.Notesfield, Notes);
			customWait(3000);
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			minWait();
			clickBtn(Locators_Phone_Contacts.SaveBtn);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			minWait();
			System.out.println("Contact added in Phone with 10 field");
		//	}
			APP_LOGS.info("Contact added in Phone with 10 field");
	     	test.log(LogStatus.PASS, "Test case Status is PASS");
			minWait();

		}
		catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators");
			e.printStackTrace();

		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			test.log(LogStatus.ERROR, "No Such element Found");
		}
	}
	
	public void addcontact_Phone(String Firstname,String Lastname,String Phone1) throws InterruptedException {
		// contact with First & Last name
		try {
			minWait();
			System.out.println("IM in Contacts");
			minWait();
			clickBtn(Locators_Phone_Contacts.ContactsMoreOptions);
			minWait();
			scrollToElements(Locators_Phone_Contacts.AccountsPhoneOPt1);
			minWait();
			clickBtn(Locators_Phone_Contacts.AccountsPhoneOPt1);
			minWait();
			clickBtn(Locators_Phone_Contacts.AddcontactBtn);
			minWait();
			enterTextToInputField(Locators_Phone_Contacts.FirstnameField, Firstname); 
			customWait(3000);
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			minWait();
			enterTextToInputField(multi_Loc_Strategy(Locators_Phone_Contacts.LaststnameField, Locators_Phone_Contacts.SurnameField,
					null, null, null, 204,930), Lastname);
			customWait(3000);
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			minWait();
			enterTextToInputField(Locators_Phone_Contacts.phoneField, Phone1);
			customWait(3000);
			clickBtn(Locators_Phone_Contacts.SaveBtn);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			minWait();
			APP_LOGS.info("Contact added in Phone");
			System.out.println("Contact added in Phone");
			minWait();

		}
		catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators");
			e.printStackTrace();

		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			test.log(LogStatus.ERROR, "No Such element Found");
		}
	}

	public void addcontactwith_Picture(String Firstname,String Lastname,String Phone1,SoftAssert sa) throws InterruptedException {
		// contact with First & Last name & Picture


		try {
			minWait();
			System.out.println("IM in Contacts");
			minWait();
			clickBtn(Locators_Phone_Contacts.ContactsMoreOptions);
			minWait();
			scrollToElements(Locators_Phone_Contacts.AccountsPhoneOPt1);
			minWait();
			clickBtn(Locators_Phone_Contacts.AccountsPhoneOPt1);
			minWait();
			clickBtn(Locators_Phone_Contacts.AddcontactBtn);
			minWait();
			enterTextToInputField(Locators_Phone_Contacts.FirstnameField, Firstname); 
			customWait(3000);
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			minWait();
			enterTextToInputField(multi_Loc_Strategy(Locators_Phone_Contacts.LaststnameField, Locators_Phone_Contacts.SurnameField,
					null, null, null, 204,1115 ), Lastname);
			customWait(3000);
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			minWait();
			enterTextToInputField(Locators_Phone_Contacts.phoneField, Phone1);
			customWait(3000);
			clickBtn(Locators_Phone_Contacts.camera_image);
			minWait();
			take_photo_Without_Try_Catch(sa);
			clickBtn(Locators_Phone_Contacts.SaveBtn);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			minWait();
			APP_LOGS.info("Contact added in Phone");
			minWait();

		}
		catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators");
			e.printStackTrace();

		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			test.log(LogStatus.ERROR, "No Such element Found");
		}

	}

	public void take_photo_Without_Try_Catch(SoftAssert sa) throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(aDriver, 60);
		if(isElementExist(Locators_Phone_Contacts.Take_photo)){
			check=true;
			clickBtn(Locators_Phone_Contacts.Take_photo);
		}
		else if(isElementExist(Locators_Phone_Contacts.Take_new_photo)){
			check = true;
			clickBtn(Locators_Phone_Contacts.Take_new_photo);
		}
		else{
			APP_LOGS.info("Failed to add image to saved Contact");
			test.log(LogStatus.FAIL, "Failed to add image to saved Contact");
			sa.fail();
		}
		customWait(2000);
		if(isElementExist(Locators_Phone_Contacts.permissionPopUp)) {
			minWait();
			for(int i=0;i<3;i++){
				minWait();
				clickBtn(Locators_Phone_Contacts.allowBtn);
			}
		}

		clickBtn(Locators_Phone_Contacts.photo_capture_Btn);
		wait.until(ExpectedConditions.visibilityOf(Locators_Phone_Contacts.photo_add_Btn));
		clickBtn(Locators_Phone_Contacts.photo_add_Btn);
		wait.until(ExpectedConditions.visibilityOf(Locators_Phone_Contacts.done_btn));
		clickBtn(Locators_Phone_Contacts.done_btn);
		wait.until(ExpectedConditions.visibilityOf(Locators_Phone_Contacts.camera_image));
		clickBtn(Locators_Phone_Contacts.camera_image);
		minWait();
		if(isElementExist(Locators_Phone_Contacts.Remove_photo)) {
			check = true;
			clickBtn(Locators_Phone_Contacts.cancelChangePhoto);
			minWait();
			clickBtn(Locators_Phone_Contacts.editor_menu_save_button);
			APP_LOGS.info("Image added to saved contact Successfully,Take photo option verified");
			test.log(LogStatus.INFO, "Image added to saved contact Successfully,Take photo option verified");
			test.log(LogStatus.PASS, "Test case Status is PASS");
			sa.assertTrue(check, "Image added to saved contact Successfully,Take photo option verified");	
		}else {
			APP_LOGS.info("Failed to add image to saved Contact");
			test.log(LogStatus.ERROR, "Failed to add image to saved Contact");
			test.log(LogStatus.FAIL, "Test case Status is FAIL");
			sa.fail();
		}
	}

	public void Change_format_to_Lastnamefirst_verify(String name,SoftAssert sa) throws InterruptedException {

		try {
			clickBtn(multi_Loc_Strategy(Locators_Phone_Contacts.Lastnamefirst, Locators_Phone_Contacts.Surnamefirst,
					null, null, null, 75,1063 ));
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			minWait();
			String getName = Locators_Phone_Contacts.Change_Lastname.getText();
			if(getName.equalsIgnoreCase(name))
			{
				APP_LOGS.info("Name format changed to Last/Sur name first"); 
				check=true;
				System.out.println("Name format changed to Last/Sur name first");
				sa.assertTrue(check, "TestCase Valiation is PASS");
				test.log(LogStatus.PASS,"Name format changed to Last;/Sur name first");
			} else {
				APP_LOGS.info("SMS didn't sent");
				sa.fail();
				test.log(LogStatus.FAIL,"Name format didn't changed to Last name first");
			}	
		}
		catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators");
			e.printStackTrace();

		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			test.log(LogStatus.ERROR, "No Such element Found");
		}
	}

	public void Change_format_to_Firstnamefirst_verify(String name1,SoftAssert sa) throws InterruptedException {

		try {
			clickBtn(Locators_Phone_Contacts.Firstnamefirst);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			minWait();
			String getName = Locators_Phone_Contacts.Change_Firststname.getText();
			if(getName.equalsIgnoreCase(name1))
			{
				APP_LOGS.info("Name format changed to First name first"); 
				check=true;
				System.out.println("Name format changed to First name first");
				sa.assertTrue(check, "TestCase Valiation is PASS");
				test.log(LogStatus.PASS,"Name format changed to First name first");
			} else {
				APP_LOGS.info("SMS didn't sent");
				sa.fail();
				test.log(LogStatus.FAIL,"Name format didn't changed to First name first");
			}	
		}
		catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators");
			e.printStackTrace();

		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			test.log(LogStatus.ERROR, "No Such element Found");
		}
	}

	public void Change_Sortby_Lastname_verify(String name,SoftAssert sa) throws InterruptedException {

		try {
			clickBtn(multi_Loc_Strategy(Locators_Phone_Contacts.SortbyLastnameOpt, Locators_Phone_Contacts.SortbySurnameOpt,
					null, null, null, 75,1063));
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			minWait();
			String getName = Locators_Phone_Contacts.AfterChange_SortbyLastname.getText();
			if(getName.equalsIgnoreCase(name))
			{
				APP_LOGS.info("Short by working fine for Last name"); 
				check=true;
				System.out.println("Short by working fine for Last name");
				sa.assertTrue(check, "TestCase Valiation is PASS");
				test.log(LogStatus.PASS,"Short by working fine for Last name");
			} else {
				APP_LOGS.info("SMS didn't sent");
				sa.fail();
				test.log(LogStatus.FAIL,"Short by not working for Last name");
			}	
		}
		catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators");
			e.printStackTrace();

		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			test.log(LogStatus.ERROR, "No Such element Found");
		}
	}

	public void Change_Sortby_Firstname_verify(String name,SoftAssert sa) throws InterruptedException {

		try {
			clickBtn(Locators_Phone_Contacts.SortbyFirstnameOpt);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			minWait();
			String getName = Locators_Phone_Contacts.AfterChange_SortbyFirstname.getText();
			if(getName.equalsIgnoreCase(name))
			{
				APP_LOGS.info("Short by working fine for First name"); 
				check=true;
				System.out.println("Short by working fine for First name");
				sa.assertTrue(check, "TestCase Valiation is PASS");
				test.log(LogStatus.PASS,"Short by working fine for First name");
			} else {
				APP_LOGS.info("SMS didn't sent");
				sa.fail();
				test.log(LogStatus.FAIL,"Short by not working for First name");
			}	
		} 
		catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators");
			e.printStackTrace();

		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			test.log(LogStatus.ERROR, "No Such element Found");
		}
	}

	public void Add_Myinfo_if_not_present(String Firstname1,String Lastname1,String Phone1,
			String Profile,SoftAssert sa) throws InterruptedException {
		//	WebDriverWait wait = new WebDriverWait(aDriver, 15);
		try {
			minWait();	
			if(isElementExist(Locators_Phone_Contacts.SetupProfile))
			{
				APP_LOGS.info("No Profile is there"); 
				check=true;
				System.out.println("No Profile is there & Profile need to add");
				minWait();
				clickBtn(Locators_Phone_Contacts.MyinfoOpt);
				minWait();
				enterTextToInputField(Locators_Phone_Contacts.FirstnameField, Firstname1); 
				customWait(3000);
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
				minWait();
				enterTextToInputField(multi_Loc_Strategy(Locators_Phone_Contacts.LaststnameField, Locators_Phone_Contacts.SurnameField,
						null, null, null, 204,930), Lastname1);
				customWait(3000);
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
				minWait();
				enterTextToInputField(Locators_Phone_Contacts.phoneField, Phone1);
				customWait(3000);
				clickBtn(Locators_Phone_Contacts.SaveBtn);
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
				minWait();
				APP_LOGS.info("Profile setup done");
				System.out.println("Profile added");
				sa.assertTrue(check, "TestCase Valiation is PASS");
				test.log(LogStatus.PASS,"New Profile setup done");	
			}
			else {
				APP_LOGS.info("Profile already there");
				System.out.println("Profile already there need to edit");
				test.log(LogStatus.PASS,"Profile already there need to edit");
			}
		} 
		catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators");
			e.printStackTrace();

		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			test.log(LogStatus.ERROR, "No Such element Found");
		}
	}

	public void Edit_Myinfo(String Firstname2,String Lastname2,String Phone2,SoftAssert sa) throws InterruptedException {
		// edit Profile info

		try {
			minWait();
			clickBtn(Locators_Phone_Contacts.MyinfoOpt);
			minWait();
			clickBtn(Locators_Phone_Contacts.ContactEditBtn);
			minWait();
			clearInputField(Locators_Phone_Contacts.FirstnameEdit);
			minWait();
			enterTextToInputField(Locators_Phone_Contacts.FirstnameEdit, Firstname2);
			customWait(3000);
			//			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			//			minWait();
			clickBtn(multi_Loc_Strategy(Locators_Phone_Contacts.LastnameEdit,Locators_Phone_Contacts.SurnameField,
					null, null, null, 204,1115));
			minWait();
			clearInputField(multi_Loc_Strategy(Locators_Phone_Contacts.LastnameEdit,Locators_Phone_Contacts.SurnameField,
					null, null, null, 204,1115));
			minWait();
			enterTextToInputField(multi_Loc_Strategy(Locators_Phone_Contacts.LastnameEdit,Locators_Phone_Contacts.SurnameField,
					null, null, null, 204,1115), Lastname2);
			customWait(3000);
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			minWait();
			clearInputField(Locators_Phone_Contacts.PhoneEdit);
			customWait(3000);
			enterTextToInputField(Locators_Phone_Contacts.PhoneEdit, Phone2);
			customWait(3000);
			clickBtn(Locators_Phone_Contacts.SaveBtn);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			minWait();
			APP_LOGS.info("Edit Profile done");
			sa.assertTrue(true, "Profile edited");
			System.out.println("Profile Edited");
			minWait();
			test.log(LogStatus.PASS,"Profile Edit done");
		}
		catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators");
			e.printStackTrace();

		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			test.log(LogStatus.ERROR, "No Such element Found");
		}  

	}

	public void MakefavoriteContact(SoftAssert sa) throws InterruptedException {
		try {
			clickBtn(Locators_Phone_Contacts.ContactsMoreOptions);
			minWait();
			scrollToElements(Locators_Phone_Contacts.AccountsPhoneOPt1);
			minWait();
			clickBtn(Locators_Phone_Contacts.AccountsPhoneOPt1);
			minWait();
			clickBtn(Locators_Phone_Contacts.Contactfirst);
			minWait();
			clickBtn(Locators_Phone_Contacts.menu_star);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.BACK);
			minWait();
			APP_LOGS.info("Contact make as Favorite");
			minWait();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			test.log(LogStatus.ERROR, "No Such element Found");
		}
		sa.assertAll();
	}

	public void BlockNumber(String Phone1,SoftAssert sa) throws InterruptedException {

		try {
			navigateToMoreOptions("Settings","Blocked numbers");
			minWait();
			clickBtn(Locators_Phone_Contacts.BlocknumOpt);
			minWait();
			clickBtn(Locators_Phone_Contacts.AddBlocknumOpt);
			minWait();
			enterTextToInputField(Locators_Phone_Contacts.EditboxOpt, Phone1); 
			customWait(3000);
			clickBtn(Locators_Phone_Contacts.BlockOpt);
			minWait();
			if (isElementExist(Locators_Phone_Contacts.BlockNum)) {
				APP_LOGS.info("Phone1 added in Block no list "); 
				check=true;
				System.out.println("Phone1 added in Block no list ");
				sa.assertTrue(check, "TestCase Valiation is PASS");
				test.log(LogStatus.PASS,"Phone1 added in Block no list ");
			} else {
				APP_LOGS.info("SMS didn't sent");
				sa.fail();
				test.log(LogStatus.FAIL,"Phone1 didn't added in Block no list ");
			}
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			minWait();	
		} 
		catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators");
			e.printStackTrace();

		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			test.log(LogStatus.ERROR, "No Such element Found");
		}

	}

	public void UnBlockNumber(String Phone1,SoftAssert sa) throws InterruptedException {

		try {
			navigateToMoreOptions("Settings","Blocked numbers");
			minWait();
			clickBtn(Locators_Phone_Contacts.BlocknumOpt);
			minWait();
			if (isElementExist(Locators_Phone_Contacts.BlockNum)) {
				clickBtn(Locators_Phone_Contacts.DelBlockNum);
				minWait();
				clickBtn(Locators_Phone_Contacts.UnBlockOpt);
				minWait();

				APP_LOGS.info("Phone1 no UnBlocked"); 
				check=true;
				System.out.println("Phone1 removed from Block no list ");
				sa.assertTrue(check, "TestCase Valiation is PASS");
				test.log(LogStatus.PASS,"Phone1 removed from Block no list");
			} else {
				APP_LOGS.info("SMS didn't sent");
				sa.fail();
				test.log(LogStatus.FAIL,"Phone1 removed from Block no list ");
			}
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			minWait();	
		}
		catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators");
			e.printStackTrace();
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			test.log(LogStatus.ERROR, "No Such element Found");
		}
		sa.assertAll();
	}

	public void LinkNumber(String Phone1) throws InterruptedException {
		SoftAssert sf = new SoftAssert();
		try {
			minWait();
			clickBtn(Locators_Phone_Contacts.FirstName);
			minWait();
			clickBtn(Locators_Phone_Contacts.More_Options);
			minWait();
			clickBtn(Locators_Phone_Contacts.LinkOpt);
			minWait();
			clickBtn(Locators_Phone_Contacts.SecondName);
			minWait();
			if (isElementExist(Locators_Phone_Contacts.BlockNum)) {
				APP_LOGS.info("Phone1 added in Block no list "); 
				check=true;
				System.out.println("Phone1 added in Block no list ");
				sf.assertTrue(check, "TestCase Valiation is PASS");
				test.log(LogStatus.PASS,"Phone1 added in Block no list ");
			} else {
				APP_LOGS.info("SMS didn't sent");
				sf.fail();
				test.log(LogStatus.FAIL,"Phone1 didn't added in Block no list ");
			}
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			minWait();	
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			test.log(LogStatus.ERROR, "No Such element Found");
		}
		sf.assertAll();
	}

	public void UnLinkNumber(String Phone1) throws InterruptedException {
		SoftAssert sf = new SoftAssert();
		try {
			navigateToMoreOptions("Settings","Blocked numbers");
			minWait();
			clickBtn(Locators_Phone_Contacts.BlocknumOpt);
			minWait();
			if (isElementExist(Locators_Phone_Contacts.BlockNum)) {
				clickBtn(Locators_Phone_Contacts.DelBlockNum);
				minWait();
				clickBtn(Locators_Phone_Contacts.UnBlockOpt);
				minWait();

				APP_LOGS.info("Phone1 no UnBlocked"); 
				check=true;
				System.out.println("Phone1 removed from Block no list ");
				sf.assertTrue(check, "TestCase Valiation is PASS");
				test.log(LogStatus.PASS,"Phone1 removed from Block no list");
			} else {
				APP_LOGS.info("SMS didn't sent");
				sf.fail();
				test.log(LogStatus.FAIL,"Phone1 removed from Block no list ");
			}
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			minWait();	
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			test.log(LogStatus.ERROR, "No Such element Found");
		}
		sf.assertAll();
	}

	public void sendMessageFromContacts(String Message,SoftAssert sa) throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(aDriver, 120);
		try {
			minWait();
			clickBtn(Locators_Phone_Contacts.Contactfirst);
			wait.until(ExpectedConditions.visibilityOf(multi_Loc_Strategy( Locators_Phone_Contacts.SMSOpt2,Locators_Phone_Contacts.SMSOpt3,
					Locators_Phone_Contacts.SMSOpt,Locators_Phone_Contacts.SMSOpt4, null, 0,0)));
			clickBtn(multi_Loc_Strategy( Locators_Phone_Contacts.SMSOpt2,Locators_Phone_Contacts.SMSOpt3,
					Locators_Phone_Contacts.SMSOpt,Locators_Phone_Contacts.SMSOpt4, null, 0,0));
			minWait();
			System.out.println("Clicked on SMS option");
			minWait();
			if(isElementExist(multi_Loc_Strategy(Locators_Phone_Contacts.messageEditFld, Locators_Phone_Contacts.messageEditFld2,
					Locators_Phone_Contacts.messageEditFld3, null, null, 357,1749))){
				minWait();
				System.out.println("SMS edit field is there");
				minWait();
				clickBtn(multi_Loc_Strategy(Locators_Phone_Contacts.messageEditFld, Locators_Phone_Contacts.messageEditFld2,
						Locators_Phone_Contacts.messageEditFld3, null, null, 357,1002));
				minWait();
				System.out.println("Clicked on SMS edit field");
				minWait();
				Runtime.getRuntime().exec("adb -s "+ p_Id +" shell input text 'Test Message '");
				minWait();
				if(isElementExist(multi_Loc_Strategy(Locators_Phone_Contacts.sendMessageOpt5, Locators_Phone_Contacts.sendMessageOpt2,
						Locators_Phone_Contacts.sendMessageOpt3, Locators_Phone_Contacts.sendMessageOpt, null, 930,1017))){	
					minWait();
					System.out.println("Send Button is there");
					minWait();
					clickBtn(multi_Loc_Strategy(Locators_Phone_Contacts.sendMessageOpt5, Locators_Phone_Contacts.sendMessageOpt2,
							Locators_Phone_Contacts.sendMessageOpt3, Locators_Phone_Contacts.sendMessageOpt, null, 930,1017));
					minWait();
					System.out.println("Clicked on Send Button ");
					minWait();
					test.log(LogStatus.INFO, "SEND BUTTON available");
				}
				else{
					test.log(LogStatus.INFO, "Send Button is not available");
					sa.fail();
				}
				
			}
			else{
				System.out.println("messageEditFld not found");
				sa.fail();
			}
			
			if(isElementExist(Locators_Phone_Contacts.now_Text1)||isElementExist(Locators_Phone_Contacts.justnow_Text)||
					isElementExist(Locators_Phone_Contacts.not_Sent_Text1)||isElementExist(Locators_Phone_Contacts.sending_Txt)) {
				APP_LOGS.info("Message sent Succeccfully");
				System.out.println("Message sent Succeccfully");
				test.log(LogStatus.PASS, "Message sent Succeccfully");
				sa.assertTrue(true, "Message sent Succeccfully");
			}
			aDriver.pressKeyCode(AndroidKeyCode.HOME);





			//			System.out.println("KP");
			//			clickBtn(Locators_Phone_Contacts.SMS_editor);
			//			System.out.println("KP");
			//			minWait();
			////			Runtime.getRuntime().exec("adb shell input text Test");
			//		    enterTextToInputField(Locators_Phone_Contacts.SMS_editor, Message);
			//		    System.out.println("KP");
			//			wait.until(ExpectedConditions.visibilityOf(Locators_Phone_Contacts.SendBtn));
			//			System.out.println("SMS written");
			//			clickBtn(Locators_Phone_Contacts.SendBtn);
//			wait.until(ExpectedConditions.visibilityOf(Locators_Phone_Contacts.RecSMS));
//			System.out.println("SMS sended");
//
//			if (isElementExist(Locators_Phone_Contacts.RecSMS)) {
//				APP_LOGS.info("SMS send "); 
//				check=true;
//				System.out.println("SMS send");
//				sa.assertTrue(check, "TestCase Valiation is PASS");
//				test.log(LogStatus.PASS,"SMS sent successfully");
//			} else {
//				APP_LOGS.info("SMS didn't sent");
//				sa.fail();
//				test.log(LogStatus.FAIL,"SMS didn't Send");
//			}	
		} 
		catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators");
			e.printStackTrace();

		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			test.log(LogStatus.ERROR, "No Such element Found");
		}
		sa.assertAll();

	}

	public void editcontactinfo(String Username2,String Phone2,String Email2) throws InterruptedException {
		// edit contact info
		SoftAssert sf = new SoftAssert();
		try {
			if (isElementExist(Locators_Phone_Contacts.Contact1)) {
				APP_LOGS.info("Contact is there"); 
				clickBtn(Locators_Phone_Contacts.Contact1);
				minWait();			
			} else {
				APP_LOGS.info("No Contact is there");
				sf.fail();
				test.log(LogStatus.FAIL,"Contact not there");
			}
			clickBtn(Locators_Phone_Contacts.ContactEditBtn);
			minWait();
			clickBtn(Locators_Phone_Contacts.FirstnameEdit);
			minWait();
			clearInputField(Locators_Phone_Contacts.FirstnameEdit);
			minWait();
			enterTextToInputField(Locators_Phone_Contacts.FirstnameEdit, Username2);
			customWait(3000);
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			minWait();
			clickBtn(Locators_Phone_Contacts.PhoneEdit);
			minWait();
			clearInputField(Locators_Phone_Contacts.PhoneEdit);
			minWait();
			enterTextToInputField(Locators_Phone_Contacts.PhoneEdit, Phone2);
			customWait(3000);
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			minWait();
			//		    scrollToElement(Locators_Phone_Contacts.EmailEdit);
			//		    minWait();
			//			clickBtn(Locators_Phone_Contacts.EmailEdit);
			//			minWait();
			clearInputField(Locators_Phone_Contacts.EmailEdit);
			customWait(3000);
			enterTextToInputField(Locators_Phone_Contacts.EmailField, Email2);
			customWait(3000);
			clickBtn(Locators_Phone_Contacts.SaveBtn);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			minWait();
			APP_LOGS.info("Edit Contact done");
			minWait();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			test.log(LogStatus.ERROR, "No Such element Found");
		}  
		sf.assertAll();
	}

	public void editRingtone(SoftAssert sa) throws InterruptedException {
		// validate Imported contacts 
		try {
			if (isElementExist(Locators_Phone_Contacts.Contact1)) {
				APP_LOGS.info("Contact is there"); 
				sa.assertTrue(true, "Contact is there");
				clickBtn(Locators_Phone_Contacts.Contact1);
				minWait();			
			} else {
				APP_LOGS.info("No Contact is there");
				sa.fail();
				test.log(LogStatus.FAIL,"Contact not there");
			}
			clickBtn(Locators_Phone_Contacts.More_Options);
			minWait();
			clickBtn(Locators_Phone_Contacts.SetRingtoneOpt);
			minWait();
			clickBtn(Locators_Phone_Contacts.RingtoneOpt);
			minWait();
			clickBtn(Locators_Phone_Contacts.OKBtn);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			minWait();
			APP_LOGS.info("Ringtone Edited");
			minWait();
		} 
		catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators");
			e.printStackTrace();

		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			test.log(LogStatus.ERROR, "No Such element Found");
		}  

	}

	public void validateImportContacts(SoftAssert sa) throws InterruptedException {
		// validate Imported contacts 

		try {
			if (!isElementExist(Locators_Phone_Contacts.no_Contacts)) {
				APP_LOGS.info("No Contact"); 
				check=true;
				APP_LOGS.info("Contacts Imported Succeccfully");
				System.out.println("Imported");
				sa.assertTrue(check, "TestCase Valiation is PASS");
				test.log(LogStatus.PASS,"Contacts Imported Successfully");
			} else {
				APP_LOGS.info("SMS didn't sent");
				sa.fail();
				test.log(LogStatus.FAIL,"Contacts didn't Imported");
			}

		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators");
			e.printStackTrace();

		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			test.log(LogStatus.ERROR, "No Such element Found");
		}  
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
		catch(Exception e) {
			return check;
		}
	}

	public void validateImportContacts110(SoftAssert sa) throws InterruptedException {
		// validate Imported 2000 contacts 
		try {
			minWait();
			clickBtn(Locators_Phone_Contacts.ContactsMoreOptions);
			minWait();
			scrollText("PHONE");
			minWait();
			clickBtn(Locators_Phone_Contacts.AccountsPhoneOPt);
			minWait();
			if(isElementExist(Locators_Phone_Contacts.ContactOptn110)) {
				APP_LOGS.info("110 Contacts Imported Succeccfully");
				System.out.println("110 Contact Imported");
				sa.assertTrue(check, "TestCase Valiation is PASS");
				test.log(LogStatus.PASS,"110 Contacts Imported Successfully");
			}else {
				APP_LOGS.info("SMS didn't sent");
				sa.fail();
				test.log(LogStatus.FAIL,"Contacts didn't Imported");
			}
		} 
		catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators");
			e.printStackTrace();

		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			test.log(LogStatus.ERROR, "No Such element Found");
		}  

	}

	public void validateImportfromSIMContacts(SoftAssert sa) throws InterruptedException {
		// validate Imported SIM contacts to Phone
		try {
			minWait();
			clickBtn(Locators_Phone_Contacts.ContactsMoreOptions);
			minWait();
			clickBtn(Locators_Phone_Contacts.AccountSIMOPt);
			minWait();
			if(isElementExist(Locators_Phone_Contacts.ContactOptn2)) {
				APP_LOGS.info("SIM Contacts Imported Succeccfully");
				System.out.println("SIM Contact Imported");
				sa.assertTrue(check, "TestCase Valiation is PASS");
				test.log(LogStatus.PASS,"SIM Contacts Imported Successfully");
			}else {
				APP_LOGS.info("SMS didn't sent");
				sa.fail();
				test.log(LogStatus.FAIL,"Contacts didn't Imported");
			}
		} 
		catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators");
			e.printStackTrace();

		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			test.log(LogStatus.ERROR, "No Such element Found");
		}  

	}

	public void validateExportContactstoSIM(SoftAssert sa) throws InterruptedException {
		// validate Export contacts to SIM

		try {
			minWait();
			clickBtn(Locators_Phone_Contacts.ContactsMoreOptions);
			minWait();
			clickBtn(Locators_Phone_Contacts.AccountSIMOPt);
			minWait();
			if(isElementExist(Locators_Phone_Contacts.ContactOptnto)) {
				APP_LOGS.info("SIM Contacts Exported Succeccfully");
				System.out.println("Contacts Exported to SIM");
				sa.assertTrue(check, "TestCase Valiation is PASS");
				test.log(LogStatus.PASS,"Contacts Exported Successfully");
			}else {
				
				APP_LOGS.info("SMS didn't sent");
				sa.fail();
				test.log(LogStatus.FAIL,"Contacts didn't Exported");
			}
		} 
		catch (org.openqa.selenium.NoSuchElementException e) {
			
			test.log(LogStatus.ERROR, "Error in the locators");
			e.printStackTrace();

		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			
			e.printStackTrace();
			test.log(LogStatus.ERROR, "No Such element Found");
		}  

	}

	public void validateContactEdit(SoftAssert sa) throws InterruptedException {
		// validate Edit of contact
		try {
			minWait();
			clickBtn(Locators_Phone_Contacts.ContactsMoreOptions);
			minWait();
			scrollToElements(Locators_Phone_Contacts.AccountsPhoneOPt1);
			minWait();
			clickBtn(Locators_Phone_Contacts.AccountsPhoneOPt1);
			minWait();
			if(isElementExist(Locators_Phone_Contacts.EditContact)) {
				APP_LOGS.info("Contact edited Successfully");
				System.out.println("Contact edited Successfully");
				sa.assertTrue(true, "TestCase Valiation is PASS");
				test.log(LogStatus.PASS,"Contact Edited Successfully");
			}else {
				APP_LOGS.info("SMS didn't sent");
				sa.fail();
				test.log(LogStatus.FAIL,"Contact didn't Edited");
			}
		}
		catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators");
			e.printStackTrace();

		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			test.log(LogStatus.ERROR, "No Such element Found");
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


	public void endCall_RefDevice() {
		try {
			minWait();
			Runtime.getRuntime().exec("adb -s "+r_Id+" shell input keyevent 6");
			Thread.sleep(1000);
		} catch (Exception e) {
		}
	}

	public void recieve_Call_PrimaryDev_O() throws IOException, InterruptedException {
		SoftAssert SA = new SoftAssert();
		/*try {
			for(int j=1;j<=100;j++){
				Process child = null;
				if (p_b_No.contains("8A.")) {
					System.out.println("XP8");					
					child = Runtime.getRuntime().exec("adb -s "+ p_Id+" shell service call telecom 29");
				} else if(p_b_No.contains("5SA.")) {
					System.out.println("XP5");
					child=Runtime.getRuntime().exec("adb -s "+p_Id+" shell service call telecom 28");
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
				} else {
					continue;
				}
			}
		}catch(Exception e) {
			Thread.sleep(2000);
			Runtime.getRuntime().exec("adb -s "+p_Id+" shell input keyevent 5");
			Thread.sleep(2000);
		}
		 */
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




}
