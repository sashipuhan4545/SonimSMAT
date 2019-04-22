package com.aosp.Utils;

import static org.testng.Assert.assertTrue;

import java.awt.AWTException;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.print.attribute.SetOfIntegerSyntax;

import org.json.simple.parser.ParseException;
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

import application.AllQA;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.AndroidKeyCode;

public class XP5S_Stability_Util extends BaseUtil{

	public Process p;

	boolean value = false;
	public  String p_Id	 = "";								// Primary Device Serial Number.
	public String r_Id	 = "";								// Reference Device Serial Number.
	public String p_b_No = "";			   					// Primary Device Build Number.
	public String r_b_No = ""; 								// Reference Device Build Number.
	public String pryNum = AllQA.PRIMARYDEVMDN;	// Reference Device MDN. 
	public String refNum = AllQA.REFERENCEDEVMDN;	// Reference Device MDN.


	public void fetch_Devices_Details() throws InterruptedException, FileNotFoundException, IOException, ParseException {

		minWait();
		p_Id=JsonFileReaderAndWriter.primaryDevIdReader();
		r_Id=JsonFileReaderAndWriter.ReadRefDeviceId();
		p_b_No=JsonFileReaderAndWriter.primaryDevFirmwareReader();
		r_b_No=JsonFileReaderAndWriter.ReadRefDeviceFirmWare();
	}


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

	public void createContact(String name,int n,String num) throws InterruptedException, IOException {
		/*
		 * create contacts with name, phone, email and address
		 */
		try {

			if(isElementExist(Locators_Stability.Addcontacts)) {
				clickBtn(Locators_Stability.Addcontacts);
				minWait();	
			}else {
				minWait();	
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_MENU);
				minWait();
				clickBtn(Locators_Stability.contact_addContactsOptn);
			}

			enterTextToInputField(Locators_Stability.contactName, name);
			minWait();	
			for(int i=1; i<=3; i++) {
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_POUND);
				minWait();
			}
			Runtime.getRuntime().exec("adb -s "+p_Id+" shell input text "+n+" ");
			minWait();
			enterTextToInputField(Locators_Stability.contact_Phone, num);
			for(int i=1; i<=5;i++) {
				minWait();	
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
			}
			minWait();	
			enterTextToInputField(Locators_Stability.contact_Email, "Sonim");

			Runtime.getRuntime().exec("adb -s "+p_Id+" shell input keyevent --longpress KEYCODE_1");
			customWait(1000);
			customWait(1000);
			for(int i=1; i<=3; i++ ) {
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_RIGHT);
			}
			customWait(1000);
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_ENTER);
			customWait(8000);
			Runtime.getRuntime().exec("adb -s "+p_Id+" shell input text gmail.com");	
			customWait(1000);
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
			minWait();
			for(int i=1; i<=5;i++) {
				minWait();	
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
			}
			clickBtn(Locators_Stability.contact_MorefieldsOptn);
			minWait();	
			for(int i=1; i<=20;i++) {
				minWait();	
				if(isElementExist(Locators_Stability.contact_Address)){
					break;
				}
				else {
					aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
					continue;
				}
			}
			minWait();	
			enterTextToInputField(Locators_Stability.contact_Address, "IDC Sonim");
			minWait();	
			minWait();	
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_MENU);
			minWait();
			clickBtn(Locators_Stability.contact_Save);
			minWait();		
			minWait();	
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void back(int n) throws InterruptedException {
		for(int i=1; i<=n; i++) {
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			minWait();
		}
	}

	public void validateContactCreate(int n,SoftAssert soft) throws InterruptedException 
	{
		/*
		 * validate contact creation present in phone memory
		 */
	
		try {

			minWait();
			for(int i=0; i<n; i++) {
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
			}
			minWait();			
			aDriver.pressKeyCode(AndroidKeyCode.ENTER);
			minWait();
			String getcontactNameText=Locators_Stability.contact_Test.getText();
			System.out.println(getcontactNameText);
			if(getcontactNameText.contains("Test"))
			{	check1 =true;
			APP_LOGS.info("check1: "+check1);
			APP_LOGS.info("Contact Name is: "+getcontactNameText);
			}else{
				APP_LOGS.info("check1: "+check1);
				APP_LOGS.info("Test Name contact is not present");
			}

			minWait();	
			minWait();	
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
			minWait();
			String getPhoneNumText=Locators_Stability.contact_phnNum.getText();


			String phoneNum = getPhoneNumText.replaceAll("[^a-zA-Z0-9]", "");
			System.out.println(phoneNum);
			minWait();
			if(phoneNum.equalsIgnoreCase(refNum))
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
			getEmailText=Locators_Stability.contact_emailid.getText();
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
			String getAddressText=Locators_Stability.contact_addressText.getText();
			//		System.out.println(getAddressText);	
			if(getAddressText.equalsIgnoreCase("IDC Sonim"))
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
				test.log(LogStatus.PASS,"Contact created with Name: " +getcontactNameText);
				soft.assertTrue(check, " ");
			}else{
				APP_LOGS.info("Test case failed");	
				test.log(LogStatus.ERROR, "Contact creation failed at iteration: "+n);
				soft.fail();
			}
			minWait();	
			back(3);
		} catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "No Such Element Found");
			soft.fail();
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
			clickBtn(Locators_Stability.contacts);
			customWait(500);
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_MENU);
			clickBtn(Locators_Stability.Contact_Select);
			customWait(500);

			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_MENU);
			clickBtn(Locators_Stability.Contact_SelectAll);
			customWait(1000);

			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_MENU);
			customWait(500);
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
			clickBtn(Locators_Stability.contact_delete);
			customWait(500);
			minWait();	
			clickBtn(Locators_Stability.contact_DeleteBtn);
			minWait();	

			if(isElementExist(Locators_Stability.contact_Test)) {
				clickBtn(Locators_Stability.contact_Test);
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_MENU);
				minWait();	
				//				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_CLEAR);
				minWait();	
				minWait();
				clickBtn(Locators_Stability.contact_delete);
				minWait();	
				clickBtn(Locators_Stability.contact_DeleteBtn);
				minWait();	
			}
			if(isElementExist(Locators_Stability.contact_Testing)) {
				clickBtn(Locators_Stability.contact_Testing);
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_MENU);
				minWait();	
				//				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_CLEAR);
				minWait();	
				minWait();
				clickBtn(Locators_Stability.contact_delete);
				minWait();	
				clickBtn(Locators_Stability.contact_DeleteBtn);
				minWait();	
			}		
			APP_LOGS.info("Deleted all contacts");
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}

	public void validatecallSavedContact(String contact) throws InterruptedException, IOException {
		/*
		 * Intiate a call from saved contacts
		 */
		SoftAssert sf = new SoftAssert();
		try {
			launch_an_app("contacts");
			minWait();
			minWait();
			for(int i=1; i<=3; i++) {
				if(isElementExist(Locators_Stability.contactSelect_Testing)) {
					minWait();
					clickBtn(Locators_Stability.contactSelect_Testing);
					APP_LOGS.info("Selected Testing Contacts");
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
			String getCallingNum=Locators_Stability.phone_RecentCallName.getText();
			//		System.out.println(getCallingNum);
			if(contact.equalsIgnoreCase(getCallingNum)) {
				check = true;
				APP_LOGS.info("Selected call from saved contacts is displayed");
				sf.assertTrue(check, "Validation is Pass");
			}else{
				APP_LOGS.info("Selected call from saved contacts is not displayed");
			}	
			endcall();
		} catch (Exception e) {
			APP_LOGS.info("No Element found.");
			test.log(LogStatus.ERROR, "No Such Element Found");
		}
		sf.assertAll();
	}


	public void endcall() throws InterruptedException {
		/*
		 * disconnect call
		 */
		minWait();
		aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_ENDCALL);
		minWait();	
	}

	public void performCall() throws InterruptedException {
		/*
		 * call save contact
		 */
		customWait(3000);
		minWait();
		aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
		minWait();
		aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
		APP_LOGS.info("Selected COntact");
		minWait();
		aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
		minWait();
		aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
		APP_LOGS.info("initiated a call");
		minWait();
		customWait(2000);
	}

	public void validateLog(String str,String fileName,int n) {
		/*
		 * Validate via log String that MO-Voice  call initiated 
		 */
		SoftAssert s1 = new SoftAssert();
		if(searchString(str, fileName)) {
			check = true;
			s1.assertTrue(check, " ");
		}
		else {
			test.log(LogStatus.ERROR, "MO-Voice call Failed at : iteration " + n );
		}
		s1.assertAll();
	}

	/*	//This method will take adb log
	public static void startLog(String fileName) throws AWTException, InterruptedException {

		customWait(2000);

		try {
			APP_LOGS.info("Adb log started sucessfully ");
			Runtime.getRuntime().exec("cmd /C \"adb -s 191a9d2f logcat -b all -v threadtime>src/test/resources/adbLogs/"+fileName+".txt \"");
			Thread.sleep(2000);
		}
		catch(Exception e) {
			System.out.println("Something goes Wrong!!!");   e.printStackTrace();  
		}
	}
	 */

	public void callHistory() throws InterruptedException {
		/*
		 * call save contact
		 */
		customWait(2000);
		aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_CALL);
		APP_LOGS.info("initiated a call");
		minWait();
		customWait(2000);
	}

	public void callHistory_Orio() throws InterruptedException, IOException {
		/*
		 * call save contact
		 */
		minWait();
		clickBtn(Locators_Stability.first_No_In_CallLog);
		customWait(4000);
		WebDriverWait wait = new WebDriverWait(aDriver, 40);
		wait.until(ExpectedConditions.visibilityOf(Locators_Stability.outgoingCall));
		minWait();
		Runtime.getRuntime().exec("adb -s "+p_Id+" shell input keyevent 5");		
		customWait(1000);
//		Runtime.getRuntime().exec("adb -s "+p_Id+" shell input keyevent 5");		
		customWait(1000);
		
	}


	public void validateCall(String str,String fileName,int n,String callType,SoftAssert soft) throws IOException, InterruptedException {
		/*
		 * Validate via log String that MO-Voice  call initiated 
		 */
		SoftAssert s1 = new SoftAssert();
		String value = null;
		customWait(10000);
		try {
			for(int j=1;j<=100;j++){
				Process child = Runtime.getRuntime().exec("adb -s "+p_Id+" shell service call telecom 28" );
				InputStream lsOut = child.getInputStream();
				InputStreamReader r = new InputStreamReader(lsOut);
				BufferedReader in = new BufferedReader(r);
				value=in.readLine();
				customWait(2000);
				System.out.println(value);
				if(value.contains("00000002")) {
					check = true;
					Runtime.getRuntime().exec("adb -s "+r_Id+" shell input keyevent 5");
					test.log(LogStatus.INFO,"MO-Voice call from "+ callType +" is validated at : iteration " + n);
					soft.assertTrue(check, " ");		
				}
				else if(value.contains("00000000")){			
					continue;				
				}
				else {
					test.log(LogStatus.ERROR, "MO-Voice call Failed at : iteration " + n );
					soft.fail();
				}
				customWait(10000);
				endcall();
			}
		} catch (Exception e) {
			Thread.sleep(2000);
			Runtime.getRuntime().exec("adb -s "+r_Id+" shell input keyevent 5");
			Thread.sleep(2000);
		}
	}


	public void validateCall_Orio(String str,String fileName,int n,String callType) throws IOException, InterruptedException {
		/*
		 * Validate via log String that MO-Voice  call initiated 
		 */
		SoftAssert s1 = new SoftAssert();
		String value = null;
		customWait(10000);
		try {
			for(int j=1;j<=100;j++){
				Process child = Runtime.getRuntime().exec("adb -s "+p_Id+" shell service call telecom 30" );
				InputStream lsOut = child.getInputStream();
				InputStreamReader r = new InputStreamReader(lsOut);
				BufferedReader in = new BufferedReader(r);
				value=in.readLine();
				customWait(2000);
				System.out.println(value);
				if(value.contains("00000002")) {
					check = true;
					Runtime.getRuntime().exec("adb -s "+r_Id+" shell input keyevent 5");
					test.log(LogStatus.INFO,"MO-Voice call from "+ callType +" is validated at : iteration " + n);
					s1.assertTrue(check, " ");	
					break;
				}

				else {
					test.log(LogStatus.FAIL, "MO-Voice call Failed at : iteration " + n );
				}
				customWait(10000);			
			}customWait(10000);	
			endcall();
		} catch (Exception e) {
			Thread.sleep(2000);
			Runtime.getRuntime().exec("adb -s "+r_Id+" shell input keyevent 5");
			Thread.sleep(2000);
		}

		s1.assertAll();
	}

	public void performAction() throws IOException, InterruptedException {
		/*
		 * 
		 * perform action airplane mode on and off
		 */

		Runtime.getRuntime().exec("adb -s "+p_Id+" shell am start -a android.settings.AIRPLANE_MODE_SETTINGS");
		minWait();
		enableSwitch(Locators_Stability.enabled_Airplane,Locators_Stability.disabled_Airplane,Locators_Stability.switch_widget);
		customWait(5000);
		disableSwitch(Locators_Stability.disabled_Airplane,Locators_Stability.enabled_Airplane,Locators_Stability.switch_widget );		
		customWait(9000);


	}


	public void performActionOrio() throws IOException, InterruptedException {
		/*
		 * 
		 * perform action airplane mode on and off
		 */

		Runtime.getRuntime().exec("adb -s "+p_Id+" shell am start -a android.settings.AIRPLANE_MODE_SETTINGS");
		minWait();
		for(int i=1; i<=6;i++) {
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
			minWait();
		}
		enableSwitch(Locators_Stability.enabled_Airplane,Locators_Stability.disabled_Airplane,Locators_Stability.switch_widget);
		customWait(5000);
		disableSwitch(Locators_Stability.disabled_Airplane,Locators_Stability.enabled_Airplane,Locators_Stability.switch_widget );		
		customWait(9000);


	}

	public boolean validateIMSLog(String str,String fileName) throws IOException, InterruptedException {
		/*
		 * Validate via log String that MO-Voice  call initiated 
		 */

		if(searchString(str, fileName)) {
			check = true;
			test.log(LogStatus.INFO, "IMS is Enabled" );
		}
		else {
			check = false;
			test.log(LogStatus.INFO, "IMS is not Enabled"  );
		}
		return check;
	}

	public void enableSwitch(WebElement enablebtn, WebElement disablebtn, WebElement switchwidget) throws InterruptedException {
		/*
		 * Enable Switch widget or toggle button
		 */
		try {
			minWait();
			if(isElementExist(switchwidget)) {
				if (!isElementExist(enablebtn)) {
					minWait();
					clickBtn(disablebtn);
					APP_LOGS.info("Switch is Enabled");                     
					minWait();
				}
			}
			else {
				
			}
		} catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "No Such Element Found");
		}
	}

	public void disableSwitch(WebElement disablebtn, WebElement enablebtn, WebElement switchwidget) throws InterruptedException {
		/*
		 * Enable Switch widget or toggle button
		 */
		try {
			minWait();
			if(isElementExist(switchwidget)) {
				if (!isElementExist(disablebtn)) {
					minWait();
					clickBtn(enablebtn);
					APP_LOGS.info("Switch is Disabled");                     
					minWait();
				}
			}
			else {
				test.log(LogStatus.ERROR, "Toggle button is enabled");
			}
		} catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "No Such Element Found");
		}
	}


	public void disconnectSSIDifConnected() throws InterruptedException {
		/*
		 * disconnect wifi[SSID]if IDC wifi connected
		 */
		SoftAssert Sa = new SoftAssert();
		try {
			selectSSIDwifi();
			customWait(4000);
			clickBtn(Locators_Stability.wifi_IDC_ForgetBtn);
			System.out.println("Disconnected");
			APP_LOGS.info("IDC available secured wifi is disconnected");
			customWait(4000);
		} catch (Exception e) {
			e.printStackTrace();
			APP_LOGS.info("Wi-Fi Forget button: No such Element found");
			test.log(LogStatus.ERROR, "Wi-Fi Forget button: No such Element found");
		}
		
	}
	
	public void disconnectSSIDifConnected1() throws InterruptedException {
		/*
		 * disconnect wifi[SSID]if IDC wifi connected
		 */
		SoftAssert Sa = new SoftAssert();
		try {
			selectSSIDwifi();
			customWait(4000);
			clickBtn(Locators_Stability.wifi_IDC_ForgetBtn1);
			System.out.println("Disconnected");
			APP_LOGS.info("IDC available secured wifi is disconnected");
			customWait(4000);
		} catch (Exception e) {
			e.printStackTrace();
			APP_LOGS.info("Wi-Fi Forget button: No such Element found");
		}
		
	}
	
	public void checkWifiConnected() throws InterruptedException {
		try {
			customWait(4000);
			if(isElementExist(Locators_Stability.connectedWIFI1)) {
				String getTxt = Locators_Stability.connectedWIFI1.getText();
				System.out.println(getTxt);
				test.log(LogStatus.INFO, "Wi-Fi is Connected : will be disconnecting");
				disconnectSSIDifConnected1();
			}
			else {
				System.out.println("Not Connected");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}


	public void selectSSIDwifi() throws InterruptedException, IOException {
		/*
		 * Select IDC wifi which is available
		 */
		SoftAssert Sa = new SoftAssert();
		try {
		
			for(int i=1; i<=50; i++) {
//				ScrollToElement(Locators_Stability.wifiList,"IDCSONWAP");
				if(isElementExist(Locators_Stability.wifi_IDC)) {
					minWait();
					clickBtn(Locators_Stability.wifi_IDC);
					APP_LOGS.info("IDC available secured wifi is Selected");
					break;
				}
//				ScrollToElement(Locators_Stability.wifiList,"1234567890sonim");
				 if(isElementExist(Locators_Stability.wifi_Dellas)) {
					minWait();
					clickBtn(Locators_Stability.wifi_Dellas);
					APP_LOGS.info("Dellas available secured wifi is Selected");
					break;
				}
//				 ScrollToElement(Locators_Stability.wifiList,"dlink-F092-media");
				if(isElementExist(Locators_Stability.wifi_Cannada)) {
					minWait();
					clickBtn(Locators_Stability.wifi_Cannada);
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
			APP_LOGS.info("WIFI : No such Element found");
		}
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

	public void selectWIFIOptn() throws InterruptedException{
		/*
		 * Select Wifi option from settings List
		 */
		SoftAssert Sa = new SoftAssert();
		try {
			customWait(1000);
//			ScrollToElement(Locators_Stability.SettingsList, "Wi-Fi");
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
			customWait(2000);
//			clickBtn(Locators_Stability.wifi);
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
			minWait();
			APP_LOGS.info("Selected WIFI option");
		} catch (Exception e) {
			APP_LOGS.info("WIFI : No such Element found");
		}
	}
	
	public void selectOptn_Orio(String str, WebElement option) throws InterruptedException{
		/*
		 * Select Wifi option from settings List
		 */
		SoftAssert Sa = new SoftAssert();
		try {
			customWait(1000);
			ScrollToElement(Locators_Stability.SettingsList, str);
			customWait(2000);
			clickBtn(option);
			minWait();
			APP_LOGS.info("Selected Network & Internet option");
		} catch (Exception e) {
			APP_LOGS.info("WIFI : No such Element found");
		}
		
	}


	public void enterPassword() throws InterruptedException, IOException {
		/*
		 * enter Password for SSID
		 */
		try {
			String getSSIDTitle = Locators_Stability.SSIDTxt.getText();
			//		System.out.println(getSSIDTitle);

			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
			minWait();
			changeToNumberModeWifi();
			customWait(10000);
			if(getSSIDTitle.equalsIgnoreCase("IDCSONWAP")) {
				minWait();
				clickBtn(Locators_Stability.wifi_IDC_Psswd);
				customWait(6000);
				passwordEnter();
//				Runtime.getRuntime().exec("adb -s "+p_Id+" shell input text 1dcS0n1md0tc0MbLr");		
				customWait(3000);	
			}
			
			if(getSSIDTitle.equalsIgnoreCase("dlink-F092-media")) {
				minWait();
				clickBtn(Locators_Stability.wifi_IDC_Psswd);
				customWait(6000);
				//			enterTextToInputField(Locators_Stability.wifi_IDC_Psswd,"1dcS0n1md0tc0MbLr");
				Runtime.getRuntime().exec("adb -s "+p_Id+" shell input text rrxfo68837");		
				customWait(3000);	
			}
			if(getSSIDTitle.equalsIgnoreCase("1234567890sonim")) {
				minWait();
				clickBtn(Locators_Stability.wifi_IDC_Psswd);
				customWait(6000);
				//			enterTextToInputField(Locators_Stability.wifi_IDC_Psswd,"1dcS0n1md0tc0MbLr");
				Runtime.getRuntime().exec("adb -s "+p_Id+" shell input text yellowbook143");		
				customWait(3000);	
			}
			customWait(3000);
			String psswrd = Locators_Stability.wifi_IDC_Psswd.getText();
			System.out.println(psswrd);
			customWait(3000);
			clickBtn(Locators_Stability.wifi_IDC_ConnectBtn);
			customWait(3000);
		} catch (Exception e) {
			// TODO Auto-generated catch block
		
		}
	}
	
	public void passwordEnter() throws IOException, InterruptedException {
		minWait();
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


	public void validateWifiConnect(int n,SoftAssert soft) throws InterruptedException, IOException {
		/*
		 * Validate wifi connection
		 */
		try {
			minWait();
//			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			minWait();
			String getTxt = Locators_Stability.connectedWIFI.getText();
			System.out.println(getTxt);
			if(getTxt.contains("IDCSONWAP")||getTxt.contains("Disconnected")) {
				check = true;
				APP_LOGS.info("Connected to Secured Wifi succesfully.");
				test.log(LogStatus.INFO, "Connected and Disconnected to Secured Wifi succesfully at iteration: "+n);
				soft.assertTrue(check, "Validation is Pass");
			}
			else if (getTxt.contains("1234567890sonim")) {
				check = true;
				APP_LOGS.info("Connected to Secured Wifi succesfully.");
				test.log(LogStatus.INFO, "Connected and Disconnected to Secured Wifi--Dellas succesfully at iteration: "+n);
				soft.assertTrue(check, "Validation is Pass");
			}
			else if (getTxt.contains("dlink-F092-media")) {
				check = true;
				APP_LOGS.info("Connected to Secured Wifi succesfully.");
				test.log(LogStatus.INFO, "Connected and Disconnected to Secured Wifi--Cannada succesfully at iteration: "+n);
				soft.assertTrue(check, "Validation is Pass");
			}
			else{
				APP_LOGS.info("Connected to Secured Wifi is unsuccesful.");	
				test.log(LogStatus.ERROR, "Connected and Disconnected to Secured Wifi is unsuccesful at iteration: "+n);
				soft.fail();
			}
		} catch (Exception e) {
			APP_LOGS.info("No Element found.");		
		}
	}
	
	public void validateWifiConnect_Orio(int n) throws InterruptedException, IOException {
		/*
		 * Validate wifi connection
		 */
		SoftAssert sf = new SoftAssert();
		try {
			minWait();
//			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			minWait();
			String getTxt = Locators_Stability.connectedWIFI1.getText();
			System.out.println(getTxt);
			if(getTxt.contains("IDCSONWAP")||getTxt.contains("Connected")) {
				check = true;
				APP_LOGS.info("Connected to Secured Wifi succesfully.");
				test.log(LogStatus.PASS, "Connected and Disconnected to Secured Wifi succesfully at iteration: "+n);
				sf.assertTrue(check, "Validation is Pass");
			}
			else if (getTxt.contains("1234567890sonim")) {
				check = true;
				APP_LOGS.info("Connected to Secured Wifi succesfully.");
				test.log(LogStatus.PASS, "Connected and Disconnected to Secured Wifi--Dellas succesfully at iteration: "+n);
				sf.assertTrue(check, "Validation is Pass");
			}
			else if (getTxt.contains("dlink-F092-media")) {
				check = true;
				APP_LOGS.info("Connected to Secured Wifi succesfully.");
				test.log(LogStatus.PASS, "Connected and Disconnected to Secured Wifi--Cannada succesfully at iteration: "+n);
				sf.assertTrue(check, "Validation is Pass");
			}
			else{
				APP_LOGS.info("Connected to Secured Wifi is unsuccesful.");	
				test.log(LogStatus.ERROR, "Connected and Disconnected to Secured Wifi is unsuccesful at iteration: "+n);
			}
		} catch (Exception e) {
			APP_LOGS.info("No Element found.");
		}
		sf.assertAll();
	}

	public void changeToNumberModeWifi() throws InterruptedException {

		minWait();
		Locators_Stability.wifi_IDC_Psswd.sendKeys("123");
		customWait(1500);
		String text = Locators_Stability.wifi_IDC_Psswd.getText();
		System.out.println(text);
		if(!text.equals("123")) {
			for (int i = 0; i < 3; i++) {
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_POUND);
				minWait();
			}
		}
		Locators_Stability.wifi_IDC_Psswd.clear();
	}

	public void changeToNumberMode() throws InterruptedException {

		minWait();
		Locators_Stability.toField_NewMessage.sendKeys("123");
		customWait(1500);
		String text = Locators_Stability.toField_NewMessage.getText();
		System.out.println(text);
		if(!text.equals("123")) {
			for (int i = 0; i < 3; i++) {
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_POUND);
				minWait();
			}
		}
		minWait();
		Locators_Stability.toField_NewMessage.clear();
	}

	public void enableFeature(WebElement enablebtn, WebElement disablebtn, WebElement switchtitle) throws InterruptedException {
		/*
		 * This Method is to Enable Settings Feature"
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
				test.log(LogStatus.ERROR, "Toggle button is disabled");
			}
		} catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "No Such Element Found");
		}
	}

	public void disableFeature(WebElement enablebtn, WebElement disablebtn) throws InterruptedException {
		/*
		 * This Method is to Disable Settings Feature"
		 */
		try {
			minWait();
			if (!isElementExist(disablebtn)) {
				minWait();
				clickBtn(enablebtn);
				APP_LOGS.info("Featureis disabled");
				minWait();
			}
		} catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "Disable toggle : No Such Element Found");
		}
	}

	public boolean validateCSFB(String str,String fileName) throws IOException, InterruptedException {
		/*
		 * Validate via log String that MO-Voice  call initiated 
		 */


		if(searchString(str, fileName)) {
			check = false;
			test.log(LogStatus.INFO, "IMS is Enable " );		
		}
		else {		
			check = true;
			test.log(LogStatus.INFO, "IMS is not Enabled");
			assertTrue(check);
		}
		return check;
	}




	public void validate_ON_OFF_WiFiFeature(WebElement feature,int n,SoftAssert soft) throws InterruptedException, IOException{
		/*
		 * validate Turn On and OFF wifi toggle button	
		 */
		try {	   
			customWait(2000);
			enableFeature(Locators_Stability.enabled,Locators_Stability.disabled,Locators_Stability.switch_Title);
			minWait();

			if(isElementExist(Locators_Stability.enabled)) {
				check1 = true;
				APP_LOGS.info(" check1");
			}
			customWait(3000);
			disableFeature(Locators_Stability.enabled,Locators_Stability.disabled);
			if(isElementExist(Locators_Stability.disabled)) {
				check2 = true;
				APP_LOGS.info(" ");
			}
			if((check1) && (check2)) {
				check = true;
				APP_LOGS.info(" Turn ON and OFF feature is verified");
				test.log(LogStatus.INFO, "Turn ON and OFF WIFI feature is verified at iteration: "+n);
			soft.assertTrue(check, " ");
			}
			else {
				APP_LOGS.info(" check1"+check1+"check2"+check2);
				test.log(LogStatus.ERROR, "No Such Element Found");
				APP_LOGS.info(" Turn ON and OFF feature is not verified at iteration: "+ n);
				soft.fail();
			}
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
		}catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, " No Such Element Found");			
		}
	}

	public void enterUrl(String newurl) throws InterruptedException, IOException {
		/*
		 * Enter the Website in Url bar
		 */
		SoftAssert Sa = new SoftAssert();
		try {
			if(isElementExist(Locators_Stability.networkNotSecured)) {
				minWait();
				clickBtn(Locators_Stability.ViewCertificate);
				minWait();
				clickBtn(Locators_Stability.OKBtn);
			}
			minWait();			 
			Runtime.getRuntime().exec("cmd /C \"adb -s "+p_Id+" shell am start -a android.intent.action.VIEW -d http:/"+newurl);
			Thread.sleep(2000);
			APP_LOGS.info(" URl is entered is sucessful.");
			customWait(3000);
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_ENTER);
			minWait();
			APP_LOGS.info("Search click is sucessful.");
		} catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "Url bar: No Such Element Found");	
		} 
		Sa.assertAll();
	}



	public void validateUrlEntered(int n,SoftAssert soft)
	{
		/*
		 * validate the website page is loaded via adb logs
		 */
		try { 
			customWait(20000);
			if(isElementExist(Locators_Stability.networkNotAvailable)){
				test.log(LogStatus.INFO, "Entered Website page not Loaded successfully at iteration: "+n);
				soft.fail();
			} 
			else {
				check = true;
				APP_LOGS.info("Url entered sucessfully.");
				test.log(LogStatus.INFO, "Entered Website page Loaded Successfully at iteration: "+n);
				soft.assertTrue(check, " ");
			}
		}
		catch (Exception e) {
			APP_LOGS.info("Element not Found");
			e.printStackTrace();
			soft.fail();
		} 
	}


	public static void imsLogs(String fileName) throws AWTException, InterruptedException {

		String path = System.getProperty("user.dir");
		customWait(1000);
		try {
			Runtime.getRuntime().exec("adb  logcat -b all -v time>"+path+"\\src\\test\\resources\\adbLogs\\"+fileName+".txt\"");
			Thread.sleep(1000);
		}
		catch(Exception e) {
			Reporter.log("Something goes Wrong!!!");  
			e.printStackTrace(); 
		}
	}


	public void cameraPic() throws IOException, InterruptedException {
		/*
		 * Take camera picture via camera application
		 */
		SoftAssert S1 = new SoftAssert();
		try {

			customWait(2000);
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_HOME);
			customWait(2000);
			Runtime.getRuntime().exec("adb -s "+p_Id+" shell am start -n com.borqs.camera/com.borqs.camera.CameraActivity");
			customWait(2000);
			customWait(1000);
			if(isElementExist(Locators_Stability.photocameraIcon)) {
				clickBtn(Locators_Stability.photocameraIcon);
				APP_LOGS.info("clicked Camera Icon to capture Image");
			}
			else {
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_UP);
				minWait();
				clickBtn(Locators_Stability.photocameraIcon);
			}
			minWait();
		} catch (Exception e) {
			e.printStackTrace();
		}
		S1.assertAll();
	}

	public void navigateToCameraFolder() throws InterruptedException {

		//navigate To CAmera Folder			
		customWait(1000);
		minWait();
		aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_ENTER);
		customWait(1000);
	}


	public void clearAllAlbums() throws InterruptedException {
		/*
		 * clearing all albums if present
		 */
		SoftAssert Ss = new SoftAssert();			
		try {
			customWait(500);
			aDriver.pressKeyCode(AndroidKeyCode.MENU);
			minWait();
			if(isElementExist(Locators_Stability.SelectAlbumOptn)) {
				APP_LOGS.info("Selected Albums");
				clickBtn(Locators_Stability.SelectAlbumOptn);
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_MENU);
				minWait();
				clickBtn(Locators_Stability.SelectallOptn);
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_MENU);
				minWait();
				clickBtn(Locators_Stability.Delete);
				minWait();
				clickBtn(Locators_Stability.OKBtn);	
				APP_LOGS.info("Clicked Ok button");
				customWait(2000);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}			
		Ss.assertAll();			
	}

	public void validateGalleryFolder(WebElement folderTitle, String TitleName,int n,SoftAssert soft) throws InterruptedException, AWTException, IOException {
		/*
		 * Validate Gallery Folders from TitleName
		 */			
		SoftAssert Sa = new SoftAssert();
		try {
			customWait(1000);
			minWait();
			if(isElementExist(folderTitle) ){
				APP_LOGS.info(TitleName + " Capture and deleted is unVerified in Gallery");
				test.log(LogStatus.ERROR, TitleName+ " Capture and deleted is unVerfied in Gallery at iteration "+ n);	
				soft.fail();
			}
			else {					
				check = true;
				APP_LOGS.info(TitleName + " Capture and deleted is Validated in Gallery");
				test.log(LogStatus.INFO, TitleName+ " Capture and deleted is Validated  in Gallery at iteration "+ n);
				soft.assertTrue(check, " ");
			}
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			minWait();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();	
			test.log(LogStatus.ERROR, " No Such Element Found");
			soft.fail();
		}
	}

	public void deleteImage() throws InterruptedException {
		/*
		 * delete video
		 */
		SoftAssert Se = new SoftAssert();
		try {
			customWait(1000);
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_ENTER);
			customWait(1000);
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_MENU);
			minWait();
			clickBtn(Locators_Stability.video_DetailsOption);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_MENU);
			minWait();
			clickBtn(Locators_Stability.videoDeleteOption);
			minWait();
			APP_LOGS.info("Select Delete Imageoption");
			minWait();
			clickBtn(Locators_Stability.OKBtn);
			minWait();
			APP_LOGS.info("Deleted");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			test.log(LogStatus.ERROR, "Delete option: No Such Element FOund");
			e.printStackTrace();
		}
		Se.assertAll();
	}

	public void validateDeleteVideo(String TitleName,int n,SoftAssert soft) throws InterruptedException {
		/*
		 * Validate video deletion
		 */
		SoftAssert Sa = new SoftAssert();
		customWait(1000);
		//			customWait(1000);
		aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_ENTER);
		customWait(1000);
		aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_MENU);
		minWait();
		clickBtn(Locators_Stability.video_DetailsOption);
		minWait();
		aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
		minWait();
		aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_MENU);
		minWait();
		if(isElementExist(Locators_Stability.videoDeleteOption) ){
			minWait();
			clickBtn(Locators_Stability.videoDeleteOption);
			minWait();
			APP_LOGS.info("Select Delete Imageoption");
			minWait();
			clickBtn(Locators_Stability.OKBtn);
			minWait();
			check = true;
			APP_LOGS.info(TitleName + " Capture and deleted is Validated in Gallery");
			test.log(LogStatus.INFO, TitleName+ " Capture and deleted is Validated  in Gallery at iteration "+ n);
			soft.assertTrue(check, " ");
		}
		else {								
			APP_LOGS.info("Deleted");
			APP_LOGS.info(TitleName + " Capture and deleted is unVerified in Gallery");
			test.log(LogStatus.ERROR, TitleName+ " Capture and deleted is unVerfied in Gallery at iteration "+ n);	
			soft.fail();
		}
	}


	public void takeCameraVideo(int n) throws InterruptedException, IOException {
		/*
		 * Capture photo from camera and store in SDcard
		 */
		SoftAssert S1 = new SoftAssert();
		try {
			customWait(500);
			aDriver.pressKeyCode(AndroidKeyCode.MENU);
		customWait(1000);
			clickBtn(Locators_Stability.SwitchToCameraOptn);
			customWait(2000);
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_UP);
			minWait();
			clickBtn(Locators_Stability.videocameraIcon);
			for(int i=1; i<=n;i++) {
				customWait(2000);
			}
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
			customWait(1000);
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			minWait();
		} catch (Exception e) {
			e.printStackTrace();
		}
		S1.assertAll();
	}

	public void navigateToVideoFolder() throws InterruptedException {
		/*
		 * navigate To Video Folder
		 */
		customWait(2000);
		aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_ENTER);
		minWait();		
		//			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_ENTER);
	}

	public void cameraLaunch() throws IOException, InterruptedException {
		/*
		 * Launch camera from adb log
		 */
		minWait();
		Runtime.getRuntime().exec("adb -s "+p_Id+" shell am start -n com.borqs.camera/com.borqs.camera.CameraActivity");
		APP_LOGS.info("Camera launched successfully");
		customWait(2000);
	}

	//======================================================================================================

	public void navigateTo_NewMessage() throws InterruptedException, IOException {
		SoftAssert sf = new SoftAssert();
		try {
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_MENU);
			minWait();
			clickBtn(Locators_Stability.new_Message);
			minWait();
		} catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "Didn't navigated to New Message Window");
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


	public void validateMsgSent(String str,SoftAssert soft) throws InterruptedException {
		/*
		 * validate sent message to new num and saved contact
		 */
		SoftAssert sf = new SoftAssert();
		try {
			minWait();		
			customWait(8000);
			WebDriverWait wait = new WebDriverWait(aDriver, 40);
			wait.until(ExpectedConditions.visibilityOf(Locators_Stability.now_Text));
			minWait();
			if (isElementExist(Locators_Stability.now_Text)) {
				check=true;
				APP_LOGS.info("Message "+str+" Successfully");
				soft.assertTrue(check, "Validation is Pass");
				test.log(LogStatus.PASS,  str+": Test case status is Passed");
			} else {
				APP_LOGS.info("Message DIDN'T "+ str);
				soft.fail();
				test.log(LogStatus.ERROR, "didn't "+str +", Validation Failed ");
			}
		} catch (Exception e) {
			APP_LOGS.info("No Element found.");
			e.printStackTrace();
			test.log(LogStatus.ERROR, " No Such Element Found");
			soft.fail();
		}
	}

	public void enter_ToField(String cellNo) throws InterruptedException {
		SoftAssert sf = new SoftAssert();
		try {
			minWait();
			enterTextToInputField(Locators_Stability.toField_NewMessage, cellNo);
			minWait();
		} catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "Error in entering number to ToField");
		}
		sf.assertAll();
	}

	public void type_Message(String typeMessage) throws InterruptedException {
		SoftAssert sf = new SoftAssert();
		try {
			minWait();
			enterTextToInputField(Locators_Stability.type_Message, typeMessage);
		} catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "Error in Typing a Message.");
		}
		sf.assertAll();
	}

	public void type_New_Message(String cell_No, String typeMessage) throws InterruptedException, IOException {
		/*
		 * This Method is to type New Meassage.
		 */


		type_Message(typeMessage);
		minWait();
		changeToNumberMode();
		enter_ToField(cell_No);
		minWait();
	}

	public void send_SMS() throws InterruptedException {
		/*	
		 * 	This method is used to SEND SMS
		 * Precondition : User should create new SMS 
		 */
		SoftAssert sf = new SoftAssert();
		try {
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_MENU);
			minWait();
			clickBtn(Locators_Stability.send);
			minWait();
		} catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "Error in Sending the Message.");
		}
		sf.assertAll();
	}

	public void validate_Sent_SMS(int n,SoftAssert soft) throws InterruptedException {
		/*
		 * This Method is to validate SENT SMS.
		 */
		SoftAssert sf = new SoftAssert();
		
		try {
			WebDriverWait wait = new WebDriverWait(aDriver, 40);
			wait.until(ExpectedConditions.visibilityOf(Locators_Stability.now_Text));
			minWait();
			if (isElementExist(Locators_Stability.now_Text)) {
				check=true;
				APP_LOGS.info("Message Sent Successfully");
				soft.assertTrue(check, "Validation is Pass");
				test.log(LogStatus.PASS, "Test case status is Passed at iteration : "+ n);
			} else {
				APP_LOGS.info("Message DIDN'T Sent");
				test.log(LogStatus.ERROR, "SMS or MMS didn't Sent, Validation Failed at iteration : "+ n);
				soft.fail();
			}
		} catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "SMS or MMS didn't Sent, Validation Failed at iteration : "+ n);
			soft.fail();
		}
	}

	public void delete_All_Threads() throws InterruptedException {
		/*
		 * Method is used to delete All the SMS Threads.
		 */
		SoftAssert sf = new SoftAssert();
		try {
			minWait();
			while (true) {

				if (isElementExist(Locators_Stability.first_sms_Thread)||isElementExist(Locators_Stability.first_sms_Thread1)) {
					System.out.println("Is present");
					minWait();
					aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
					aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_CLEAR);
					minWait();
					clickBtn(Locators_Stability.delete_Confirm);
				} else {
					Reporter.log("No Threads Found");
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "Error in Deleting the Messages.");
		}
		sf.assertAll();
	}

	public void validate_CharacterAndPageNumber(WebElement charAndPageNum,String expectedcharAndPageNum) throws InterruptedException {
		/*
		 * This Meathod is to validate Characters and Page Number
		 * Precondition : User should be in Message window with Typed Message
		 */
		SoftAssert sf = new SoftAssert();
		try {
			minWait();
			String charAndPageNum1 = charAndPageNum.getText();
			if (check=charAndPageNum1.equals(expectedcharAndPageNum)) {
				APP_LOGS.info("Character and Page Number Displayed is correct");
				sf.assertTrue(check, "Validation is Pass");
			} else {
				APP_LOGS.info("Character and Page Number Displayed is NOT correct");
			} 
		} catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "Error in validating the Number of characters and Page Number");
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
			clickBtn(Locators_Stability.attachOthers);
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
			clickBtn(Locators_Stability.photoCaptureIcon);
			customWait(2000);
			clickBtn(Locators_Stability.OK);
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
			APP_LOGS.info("Attachment added Successfully.");
			sf.assertTrue(check, "Attachment added Successfully, validation PASS.");
		} else {
			test.log(LogStatus.ERROR, "Error in adding the Attachment to SMS.");
		}
		sf.assertAll();		
	}

	public void press_BackKey() throws InterruptedException {
		minWait();
		aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
		minWait();
	}

	public void captureVideo_ADD() throws InterruptedException {

		/* Captures the Video and adds to the Message (MMS)
		 * Precondition : User should navigate to "Attach others" Option
		 */

		try {
			customWait(2000);
			clickBtn(Locators_Stability.videoCaptureIcon);
			customWait(10000);
			clickBtn(Locators_Stability.videoCaptureIcon);
			minWait();
			clickBtn(Locators_Stability.OK);
			minWait();
		} catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "Error in adding Video to MMS");
		}		
	}


	public boolean validate_RIL_Logs(String filename,String validationString,String infoForFailure) throws InterruptedException {

		customWait(2000);
		boolean check1 = searchString(validationString,filename);
		SoftAssert sf = new SoftAssert();
		if (check1) {
			check=true;
			test.log(LogStatus.INFO, "Validated from RIL Logs : IMS is Enable");
		} else {			
			test.log(LogStatus.INFO, "Validation failed from ADB Logs. ==>> "+infoForFailure);
		}
		sf.assertAll();
		return check1;
	}

	public void enableData() throws InterruptedException {
		/*
		 * enable data
		 */
		try {
			launch_an_app("settings");
			minWait();
			clickBtn(Locators_Stability.networkAndInternet);
			minWait();
			if(isElementExist(Locators_Stability.MobileNetwrks_oreo)) {
				minWait();
				clickBtn(Locators_Stability.MobileNetwrks_oreo);
				APP_LOGS.info("Selected Mobile Networks");
				minWait();				
				enableSwitch(Locators_Stability.enabled_Data_Oreo,Locators_Stability.disabled_Data_Oreo,Locators_Stability.switch_widget);
				minWait();
			}
		} catch (Exception e) {
			APP_LOGS.info("No Element found.");
			minWait();	
		}
	}

	public void validateUrlEntered_O(String web,int n,int numitr)
	{
		/*
		 * validate the website page is loaded via adb logs
		 */
		SoftAssert S2= new SoftAssert();
		try { 
			customWait(20000);
			customWait(25000);
			if(isElementExist(Locators_Stability.BlockBtn)) {
				clickBtn(Locators_Stability.BlockBtn);
				minWait();
			}
			if(isElementExist(Locators_Stability.networkNotAvailable)||isElementExist(Locators_Stability.WebPageBlocked)){
				test.log(LogStatus.FAIL, "Entered Website page " + "\""+ web +"\"" + " not Loaded successfully at iteration: "+n);
			} 
			else {
				check = true;
				APP_LOGS.info("Url entered sucessfully.");
				test.log(LogStatus.INFO, "Entered Website page " + "\""+ web +"\"" + " Loaded Successfully at iteration: "+n);
				S2.assertTrue(check, " ");
			}
		}
		catch (Exception e) {
			APP_LOGS.info("Element not Found");
			e.printStackTrace();
		} 
		S2.assertAll();
	}






}
