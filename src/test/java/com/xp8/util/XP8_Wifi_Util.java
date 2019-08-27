package com.xp8.util;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import org.json.simple.parser.ParseException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import com.graphics.gui.JsonFileReaderAndWriter;
import com.relevantcodes.extentreports.LogStatus;

import application.AllQA;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.AndroidKeyCode;

public class XP8_Wifi_Util extends BaseUtil {
	
	boolean value = false;
	public  String p_Id	 = "";								// Primary Device Serial Number.
	public String r_Id	 = "";								// Reference Device Serial Number.
	public String p_b_No = "";			   					// Primary Device Build Number.
	public String r_b_No = ""; 								// Reference Device Build Number.
	public String pryNum =AllQA.PRIMARYDEVMDN;	// Reference Device MDN. 
	public String refNum = AllQA.REFERENCEDEVMDN;	// Reference Device MDN.
	
	public void fetch_Devices_Details() throws InterruptedException, FileNotFoundException, IOException, ParseException {

		minWait();
		p_Id=JsonFileReaderAndWriter.primaryDevIdReader();
		r_Id=JsonFileReaderAndWriter.ReadRefDeviceId();
		p_b_No=JsonFileReaderAndWriter.primaryDevFirmwareReader();
		r_b_No=JsonFileReaderAndWriter.ReadRefDeviceFirmWare();
	}
	
	public void waituntilnew(AndroidElement ele, int timeinSeconds) {
		try {
			WebDriverWait wait = new WebDriverWait(getDriver(), timeinSeconds);
			wait.until(ExpectedConditions.visibilityOf(ele));
			
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->waituntilnew()");
			e.printStackTrace();

		}catch (Exception e) {

			test.log(LogStatus.ERROR, "Exeption in ->waituntilnew()");
		}
	
	}
	
	
	
	public void clickOnNetwork_Internet(){

		try {
			scrollToElements(Locators_Wifi.Network_Internet_Lnk);
			if(isElementExist(Locators_Wifi.Network_Internet_Lnk)) {
				clickBtn(Locators_Wifi.Network_Internet_Lnk);
			}else {
				boolean ni=scrollToText("Network & Internet");
				while(ni==false) {
					ni=scrollToText("Network & Internet");
				}
			}
			
		} catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.ERROR, "Error in the locators->clickOnNetwork_Internet()");
			e.printStackTrace();
		}catch (Exception e) {
			test.log(LogStatus.ERROR, "Exeption in ->clickOnNetwork_Internet()");
			e.printStackTrace();
		}
	}
	
	public boolean scrollText(String text) {
		/*
		Method used to scroll to an element in the scrollable view
		*/
		
		boolean check = false;
		try {		
			String scrollable = "new UiScrollable(new UiSelector().scrollable(true))";
			String textElement = ".scrollIntoView(new UiSelector().text(\""+ text +"\"))";
			aDriver.findElementByAndroidUIAutomator(scrollable+textElement);
			APP_LOGS.info("Searched application is found sucessfully : ");
			check = true;
			return check;
	}
		catch(Exception e) {
			return check;
			}
		}
	
	public boolean scrollToTextClick(String text) {
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
	public void launchWifiActivity() {
		try {
			aDriver.startActivity("com.android.settings","com.android.settings.SubSettings");
		}catch (Exception e) {
			test.log(LogStatus.ERROR, "Exeption in -> launchWifiActivity()");
			e.printStackTrace();
		}
	}
	public void clickOn_Wifi_Lnk(){

		try {
			if(wait(Locators_Wifi.WiFi_Lnk, 10)) {
				   clickBtn(Locators_Wifi.WiFi_Lnk);
			}else {
				
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.ERROR, "Error in the locators->clickOn_Wifi_Lnk()");
			e.printStackTrace();
		}catch (Exception e) {
			test.log(LogStatus.ERROR, "Exeption in ->clickOn_Wifi_Lnk()");
			e.printStackTrace();
		}
	}
	
	public void turnOn_Wifi(){

		try {
			if(Locators_Wifi.Wifi_Switch_Btn.getText().equalsIgnoreCase("off")) {
				clickBtn(Locators_Wifi.Wifi_Switch_Btn);
			}
			} catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.ERROR, "Error in the locators-> turnOn_Wifi()");
			e.printStackTrace();
		}catch (Exception e) {
			test.log(LogStatus.ERROR, "Exeption in -> turnOn_Wifi()");
			e.printStackTrace();
		}
	}
	public boolean verify_SavedNetwork_IsPresent(){
		boolean savedNetwork=false;
		try {
				scrollToElements(Locators_Wifi.Saved_network);
				if(isElementExist(Locators_Wifi.Saved_network)) {
					savedNetwork=true;
					clickBtn(Locators_Wifi.Saved_network);
				}else {
					savedNetwork=scrollToTextClick("Saved networks");
				}
			} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->clickOn_Savednetwork()");
			e.printStackTrace();
		}catch (Exception e) {
			test.log(LogStatus.ERROR, "Exeption in ->clickOn_Savednetwork()");
			e.printStackTrace();
		}
		System.out.println("Saved network available = ? "+savedNetwork);
		return savedNetwork;
	}
	public boolean clickOn_Savednetwork(){
		boolean savedNetwork=false;
		try {
				scrollToElements(Locators_Wifi.Saved_network);
				if(isElementExist(Locators_Wifi.Saved_network)) {
					savedNetwork=true;
					clickBtn(Locators_Wifi.Saved_network);
				}else {
					savedNetwork=scrollToTextClick("Saved networks");
					while(savedNetwork==false) {
						savedNetwork=scrollToTextClick("Saved networks");
					}
				}
			} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->clickOn_Savednetwork()");
			e.printStackTrace();
		}catch (Exception e) {
			test.log(LogStatus.ERROR, "Exeption in ->clickOn_Savednetwork()");
			e.printStackTrace();
		}
		System.out.println("Saved network available = ? "+savedNetwork);
		return savedNetwork;
	}
	public void clickOn_Addnetwork(){

		try {
				if(wait(Locators_Wifi.Add_network,10)) {
					clickBtn(Locators_Wifi.Add_network);
				}else {
					boolean an=scrollToText("Add network");
					while(an==false) {
						an=scrollToText("Add network");
					}
				}
			} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->clickOn_Addnetwork()");
			e.printStackTrace();
		}catch (Exception e) {
			test.log(LogStatus.ERROR, "Exeption in ->clickOn_Addnetwork()");
			e.printStackTrace();
		}
	}
	public void SearchFor_WiFi_Name(){

		try {
			clickBtn(Locators_Wifi.Wifi_ListLnk);
			minWait();
				
			}

		 catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators-> SearchFor_WiFi_Name()");
			e.printStackTrace();

		}catch (Exception e) {

			test.log(LogStatus.ERROR, "Exeption in -> SearchFor_WiFi_Name()");
		}
	}
	
	public void enter_Password(){

		try {
			enterTextToInputField(Locators_Wifi.Wifi_PasswordTxt, "password");
			minWait();
				
			}

		 catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators-> enter_Password()");
			e.printStackTrace();

		}catch (Exception e) {

			test.log(LogStatus.ERROR, "Exeption in -> enter_Password()");
		}
	}
	
	public void clickOn_Connect(){

		try {
			clickBtn(Locators_Wifi.Wifi_connectBtn);
			minWait();
				
			}

		 catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators-> clickOn_Connect()");
			e.printStackTrace();

		}catch (Exception e) {

			test.log(LogStatus.ERROR, "Exeption in -> clickOn_Connect()");
		}
	}
	
	public void validate_Password(SoftAssert sa)
	{
		try{
			boolean checkPassword = scrollTo("Check password and try again");
			
			if(checkPassword == true){
				APP_LOGS.info("Wifi does not allow wrong password to connect ");
				sa.assertTrue(true, "Wifi does not allow wrong password to connect ");
				test.log(LogStatus.PASS, "Wifi does not allow wrong password to connect ");	
			}else {
				APP_LOGS.info("Failed -> WiFi is allowing Wrong Password to connect");
				sa.fail();
				test.log(LogStatus.FAIL, "Failed -> WiFi is allowing Wrong Password to connect");
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.ERROR,"Error in locators->validate_Password()");
			e.printStackTrace();
		}catch (Exception e) {
			test.log(LogStatus.ERROR,"Exeption in functionality->validate_Password()");
			e.printStackTrace();
		}
	}
	
	public void remove_OldSSID(String ssid) {
		boolean checkSSID=false;
		try {
			System.out.println("ssid = "+ssid);
			checkSSID=scrollText(ssid);
			while(checkSSID==true) {
				if(isElementExist(Locators_Wifi.FORGET)) {
					clickBtn(Locators_Wifi.FORGET);
				}
				checkSSID=scrollToText(ssid);
			}	
			System.out.println("removed old ssid");
		} catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.ERROR,"Error in locators-> remove_OldSSID()");
			e.printStackTrace();
		}catch (Exception e) {
			test.log(LogStatus.ERROR,"Exeption in -> remove_OldSSID()");
			e.printStackTrace();
		}
	}
	public void Add_Network_security_None(String name)
	{
		try{
			wait(Locators_Wifi.Wifi_ssidTxtBx, 90);
			enterTextToInputField(Locators_Wifi.Wifi_ssidTxtBx, name);
			clickBtn(Locators_Wifi.Wifi_securityLst);
			clickBtn(Locators_Wifi.WiFi_securityLst_Open);
			clickBtn(Locators_Wifi.WiFi_saveBtn);
		} catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.ERROR,"Error in locators->Add_Network_security_None()");
			e.printStackTrace();
		}catch (Exception e) {
			test.log(LogStatus.ERROR,"Exeption in ->Add_Network_security_None()");
			e.printStackTrace();
		}
	}
	
	public void Add_Network_security_WEP(String name,String pswd)
	{
		try{
			wait(Locators_Wifi.Wifi_ssidTxtBx, 90);
			enterTextToInputField(Locators_Wifi.Wifi_ssidTxtBx, name);
			clickBtn(Locators_Wifi.Wifi_securityLst);
			clickBtn(Locators_Wifi.WiFi_securityLst_WEP);
			enterTextToInputField(Locators_Wifi.Wifi_PasswordTxt,pswd);
			clickBtn(Locators_Wifi.WiFi_saveBtn);

		}catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.ERROR,"Error in locators->Add_Network_security_WEP()");
			e.printStackTrace();
		} catch (Exception e) {
			test.log(LogStatus.ERROR,"Exeption in ->Add_Network_security_WEP()");
			e.printStackTrace();
		}
	}
	
	public void Add_Network_security_WPA_PSK(String name,String pswd)
	{
		try{
			wait(Locators_Wifi.Wifi_ssidTxtBx, 90);
			enterTextToInputField(Locators_Wifi.Wifi_ssidTxtBx, name);
			clickBtn(Locators_Wifi.Wifi_securityLst);
			clickBtn(Locators_Wifi.WiFi_securityLst_WPA_WPA2PSK);
			enterTextToInputField(Locators_Wifi.Wifi_PasswordTxt, pswd);
			clickBtn(Locators_Wifi.WiFi_saveBtn);

		} catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.ERROR,"Error in locators->Add_Network_security_WPA_PSK()");
			e.printStackTrace();
		}catch (Exception e) {
			test.log(LogStatus.ERROR,"Exeption in ->Add_Network_security_WPA_PSK()");
			e.printStackTrace();
		}
	}
	
	public void Add_Network_security_802_1x_EAP(String name,String pswd)
	{
		try{
			
			wait(Locators_Wifi.Wifi_ssidTxtBx, 90);
			enterTextToInputField(Locators_Wifi.Wifi_ssidTxtBx, name);System.out.println("txt bx");
			clickBtn(Locators_Wifi.Wifi_securityLst);System.out.println("security");
			clickBtn(Locators_Wifi.WiFi_securityLst_802_1x_EAP);System.out.println("eap");
			clickBackButton(1);System.out.println("back btn");
			scrollToTextClick("Please select");System.out.println("pls select");
			clickBtn(Locators_Wifi.WiFi_802_dontvalidateLst);System.out.println("do not validate");
			scrollToText("Password");System.out.println("clicked on pwd");
			enterTextToInputField(Locators_Wifi.WiFi_802_pswdTxtBx, pswd);System.out.println("pwd entered");
			clickBtn(Locators_Wifi.WiFi_saveBtn);System.out.println("saved");
			
		} catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.ERROR,"Error in locators->Add_Network_security_802_1x_EAP()");
			e.printStackTrace();
		}catch (Exception e) {
			test.log(LogStatus.ERROR,"Exeption in ->Add_Network_security_802_1x_EAP()");
			e.printStackTrace();
		}
	}
	
	public void clickBackButton(int number) throws InterruptedException
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
			test.log(LogStatus.ERROR, "Exeption occurs in clickBackButton() method");
			e.printStackTrace();
		}
	}
	
	public void validate_AddNetwork(SoftAssert sa,String ssid_None,String ssid_wep,String ssid_wpa,String ssid_802)
	{
		try{
			
			scrollToTextClick("Saved networks");
			boolean none = scrollTo(ssid_None);
			boolean wep123 = scrollTo(ssid_wep);
			boolean wpa123 = scrollTo(ssid_wpa);
			boolean epa123 = scrollTo(ssid_802);
			
			if(none && wep123 &&  wpa123 && epa123){
				APP_LOGS.info("All 4 types of WiFi Networks added successfully");
				sa.assertTrue(true, "All 4 types of WiFi Networks added successfully");
				test.log(LogStatus.PASS, "All 4 types of WiFi Networks added successfully");	
			}else {
				APP_LOGS.info("Failed in Adding Networks to WiFi");
				sa.fail();
				test.log(LogStatus.FAIL, "Failed in Adding Networks to WiFi");
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.ERROR,"Error in locators->validate_AddNetwork()");
			e.printStackTrace();
		}catch (Exception e) {
			test.log(LogStatus.ERROR,"Exeption in ->validate_AddNetwork()");
			e.printStackTrace();
		}
	}
	
	public void validate_HidePassword(SoftAssert sa,String ssid,String pswd)
	{
		try{
			boolean wifi_SSID=scrollToText(ssid);
			while(wifi_SSID==false) {
				wifi_SSID=scrollToText(ssid);
			}
			enterTextToInputField(Locators_Wifi.wifi_enterPwd_index, pswd);wait(Locators_Wifi.sonimvideo,10);
		 	boolean password = scrollToText(pswd);
			if(password == false){
				APP_LOGS.info("Password hidden successfully");
				sa.assertTrue(true, "Password hidden successfully");
				test.log(LogStatus.PASS, "Password hidden successfully");	
			}else {
				APP_LOGS.info("Password is not hidden");
				sa.fail();
				test.log(LogStatus.FAIL, "Password is not hidden");
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.ERROR,"Error in locators->validate_HidePassword()");
			e.printStackTrace();
		}catch (Exception e) {
			test.log(LogStatus.ERROR,"Exeption in ->validate_HidePassword()");
			e.printStackTrace();
		}
	}
	
	
	public void validate_ShowPassword(SoftAssert sa,String pswd)
	{
		try{
			wait(Locators_Wifi.WiFi_showpswdBtn,20);
			clickBtn(Locators_Wifi.WiFi_showpswdBtn);
		 	boolean password = scrollToText(pswd);
			
			if(password == true){
				
				APP_LOGS.info("Password is displayed successfully");
				sa.assertTrue(true, "Password is displayed successfully");
				test.log(LogStatus.PASS, "Password is displayed successfully");	

			}else {
				APP_LOGS.info("Password is not displayed");
				sa.fail();
				test.log(LogStatus.FAIL, "Password is not displayed");
			}

		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR,"Error in locators->validate_ShowPassword()");
		}catch (Exception e) {
			test.log(LogStatus.ERROR,"Exeption in ->validate_ShowPassword()");
		}
	}
	
	public void turnOn_AirplaneMode()
	{
		try{
			//This will Scroll and Click the Airplane mode
			scrollTo("Airplane mode");
			scrollToElements(Locators_Wifi.ArplnMode);
			if(Locators_Wifi.ArplnMode.getText().equalsIgnoreCase("off")) {			
				clickBtn(Locators_Wifi.ArplnMode);
				if(wait(Locators_Wifi.OK,5)) {
					clickBtn(Locators_Wifi.OK);
				}
		}} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR,"Error in locators-> turnOn_AirplaneMode()");
		}catch (Exception e) {
			test.log(LogStatus.ERROR,"Exeption in -> turnOn_AirplaneMode()");
		}
	}
	
	public void connect_to_WiFi(String name,String pwsd)
	{
		try{
			boolean wifiName=scrollToText(name);
			while(wifiName==false) {
				wifiName=scrollToText(name);
			}
			minWait();
			System.out.println("SSID selected ? = "+wifiName);
			if(isElementExist(Locators_Wifi.wifi_enterPwd_index)) {
				enterTextToInputField(Locators_Wifi.wifi_enterPwd_index, pwsd);
				clickBtn(Locators_Wifi.Wifi_connectBtn);
			}
			wait(Locators_Wifi.Wifi_PageLogo, 20);
			
		} catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.ERROR,"Error in locators->connect_to_WiFi()");
			e.printStackTrace();
		}catch (Exception e) {
			test.log(LogStatus.ERROR,"Exeption in ->connect_to_WiFi()");
			e.printStackTrace();
		}
	}
	
	
	public void remove_connectedNtwrk()
	{
		try{
			if( wait(Locators_Wifi.Connected,50)==true)
			{
					if(wait(Locators_Wifi.FORGET,10)) {
						clickBtn(Locators_Wifi.FORGET);
					}else {
						scrollToText("FORGET");
					}
					wait(Locators_Wifi.Wifi_PageLogo,10);
			}
			else if(scrollToText("Check password and try again") == true)
			{
					scrollToTextLongPress("Check password and try again");
					scrollToText("Forget network");
					wait(Locators_Wifi.Wifi_PageLogo,10);
			}				
		}catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.ERROR,"Error in locators->remove_connectedNtwrk()");
			e.printStackTrace();
		}catch (Exception e) {
			test.log(LogStatus.ERROR,"Exeption in ->remove_connectedNtwrk()");
			e.printStackTrace();
		}
	}

	public void verify_WifiConnected_Arplnmode_ON(SoftAssert sa)
	{
		boolean connected =false;
		try{
			if(wait(Locators_Wifi.Connected,20)) {
				connected=isElementExist(Locators_Wifi.Connected);
			}else {
				scrollToElements(Locators_Wifi.Connected);
				connected=isElementExist(Locators_Wifi.Connected);
			}
			if(connected == true){			
				APP_LOGS.info("Wifi Connected Successfully When Flight Mode is Enabled");
				sa.assertTrue(true, "Wifi Connected Successfully When Flight Mode is Enabled");
				test.log(LogStatus.PASS, "Wifi Connected Successfully When Flight Mode is Enabled");	
			}else {
				APP_LOGS.info("Wifi Failed to Connect When Flight Mode is Enabled");
				sa.fail();
				test.log(LogStatus.FAIL, "Wifi Failed to Connect When Flight Mode is Enabled");
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.ERROR,"Error in locators->verify_WifiConnected_Arplnmode_ON()");
			e.printStackTrace();
		}catch (Exception e) {
			test.log(LogStatus.ERROR,"Exeption in ->verify_WifiConnected_Arplnmode_ON()");
			e.printStackTrace();
		}
	}
	
	public void turnOff_AirplaneMode()
	{
		try{
			scrollTo("Airplane mode");
			scrollToElements(Locators_Wifi.ArplnMode);
			if(Locators_Wifi.ArplnMode.getText().equalsIgnoreCase("on")) {
				clickBtn(Locators_Wifi.ArplnMode);
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.ERROR,"Error in locators-> turnOff_AirplaneMode()");
			e.printStackTrace();
		}catch (Exception e) {
			test.log(LogStatus.ERROR,"Exeption in -> turnOff_AirplaneMode()");
			e.printStackTrace();
		}
	}
	
	public void verify_WifiConnected_Arplnmode_Off(SoftAssert sa)
	{
			boolean connected =false;
			try{
				if(wait(Locators_Wifi.Connected,20)) {
					connected=isElementExist(Locators_Wifi.Connected);
				}else {
					scrollToElements(Locators_Wifi.Connected);
					connected=isElementExist(Locators_Wifi.Connected);
				}			
				if(connected == true){
				APP_LOGS.info("Wifi Connected Successfully when Flight Mode Disabled");
				sa.assertTrue(true, "Wifi Connected Successfully when Flight Mode Disabled");
				test.log(LogStatus.PASS, "Wifi Connected Successfully when Flight Mode Disabled");	
				}else {
				APP_LOGS.info("Wifi Failed to Connect When Flight Mode is Disabled");
				sa.fail();
				test.log(LogStatus.FAIL, "Wifi Failed to Connect When Flight Mode is Disabled");
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.ERROR,"Error in locators->verify_WifiConnected_Arplnmode_Off()");
			e.printStackTrace();
		}catch (Exception e) {
			test.log(LogStatus.ERROR,"Exeption in ->verify_WifiConnected_Arplnmode_Off()");
			e.printStackTrace();
		}
	}
	public void verify_WifiConnected_SignalLevel_AreDisplayed(SoftAssert sa)
	{
			boolean connected =false;
			try{
				if(wait(Locators_Wifi.Connected_SignalLevel,300)) {
					connected=isElementExist(Locators_Wifi.Connected_SignalLevel);
				}else {
					scrollToElements(Locators_Wifi.Connected_SignalLevel);
					connected=isElementExist(Locators_Wifi.Connected_SignalLevel);
				}		
				System.out.println("Wifi connection status => "+Locators_Wifi.Connected_SignalLevel.getAttribute("contentDescription"));
				
				if(connected == true){
				APP_LOGS.info("Wifi is connected and signal level is displayed successfully");
				sa.assertTrue(true, "Wifi is connected and signal level is displayed successfully");
				test.log(LogStatus.PASS, "Wifi is connected and signal level is displayed successfully");	
				}else {
				APP_LOGS.info("Failed -> Wifi is not connected");
				sa.fail();
				test.log(LogStatus.FAIL, "Failed -> Wifi is not connected");
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.ERROR,"Error in locators-> verify_WifiConnected_SignalLevel_AreDisplayed()");
			e.printStackTrace();
		}catch (Exception e) {
			test.log(LogStatus.ERROR,"Exeption in -> verify_WifiConnected_SignalLevel_AreDisplayed()");
			e.printStackTrace();
		}
	}
	public void clickOn_WifiDataUsage() {
		try {
			scrollToElements(Locators_Wifi.Wi_fi_data_usage);
			clickBtn(Locators_Wifi.Wi_fi_data_usage);
			APP_LOGS.info("clicked on Wifi data usage successfully.");
		} catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.ERROR,"Error in locators-> clickOn_WifiDataUsage()");
			e.printStackTrace();
		}catch (Exception e) {
			test.log(LogStatus.ERROR,"Exeption in -> clickOn_WifiDataUsage()");
			e.printStackTrace();
		}
	}
	public void validate_NetworkIsSaved(SoftAssert sa,String ssid,String type)
	{
		try{
			
			boolean sn=scrollToText("Saved networks");
			while(sn==false)
			{
				sn=scrollToText("Saved networks");
			}
			boolean checkSSID = scrollTo(ssid);
			if(checkSSID == true){	
				APP_LOGS.info(type+" Network Added Succesfully ");
				sa.assertTrue(true, type+" Network Added Succesfully");
				test.log(LogStatus.PASS,type+" Network Added Succesfully");
			}
			else {
				APP_LOGS.info("Failed in Adding Networks to WiFi");
				sa.fail();
				test.log(LogStatus.FAIL, "Failed in Adding Networks to WiFi");
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.ERROR,"Error in locators->validate_NetworkIsSaved()");
			e.printStackTrace();
		}catch (Exception e) {
			test.log(LogStatus.ERROR,"Exeption in ->validate_NetworkIsSaved()");
			e.printStackTrace();
		}
	}
	
	public void validate_sameNetworkIsSaved(SoftAssert sa,String ssid)
	{
		try{
			
			boolean sn=scrollToText("Saved networks");
			while(sn==false)
			{
				sn=scrollToText("Saved networks");
			}			
			boolean checkSSID = scrollToText(ssid);
			clickBackButton(1);	
			if(checkSSID == true){
				APP_LOGS.info("Network Added Succesfully With Same Name");
				sa.assertTrue(true, "Network Added Succesfully With Same Name");
				test.log(LogStatus.PASS,"Network Added Succesfully With Same Name");	
			}
			else {
				APP_LOGS.info("Network Failed to Add With Same Name");
				sa.fail();
				test.log(LogStatus.FAIL, "Network Failed to Add With Same Name");
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.ERROR,"Error in locators->validate_sameNetworkIsSaved()");
			e.printStackTrace();
		}catch (Exception e) {
			test.log(LogStatus.ERROR,"Exeption in ->validate_sameNetworkIsSaved()");
			e.printStackTrace();
		}
	}
	
	public void getNotificationWindow() {
		try {
			aDriver.pressKeyCode(AndroidKeyCode.HOME);
			minWait();
			Runtime.getRuntime().exec("adb -s "+p_Id+" shell input swipe 10 10 10 1000");
			minWait();
			Runtime.getRuntime().exec("adb -s "+p_Id+" shell input swipe 10 10 10 1000");
			minWait();
		}catch (Exception e) {
			test.log(LogStatus.ERROR, "Cannot interract with command Prompt");
			e.printStackTrace();
		}
		
	}
	
	public void swipeRight() {
		try {
			Runtime r = Runtime.getRuntime();
			r.exec("adb -s "+p_Id+" shell input swipe 800 400 400 400");
		}catch (Exception e) {
			test.log(LogStatus.ERROR, "Cannot interract with command Prompt");
			e.printStackTrace();
		}
		
	}
	
	public void clickOn_QuickWifiSetting()
	{
		try{
			clickBtn(Locators_Wifi.Wifi_QuickTurnSetting);
		} catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.ERROR,"Error in locators->  clickOn_QuickWifiSetting()");
			e.printStackTrace();
		}catch (Exception e) {
			test.log(LogStatus.ERROR,"Exeption in ->  clickOn_QuickWifiSetting()");
			e.printStackTrace();
		}
	}
	public void clickOn_WifiMoreSettings()
	{
		try{
			clickBtn(Locators_Wifi.WifiQuick_MoresettingBtn);
		} catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.ERROR,"Error in locators->  clickOn_WifiMoreSettings()");
			e.printStackTrace();
		}catch (Exception e) {
			test.log(LogStatus.ERROR,"Exeption in ->  clickOn_WifiMoreSettings()");
			e.printStackTrace();
		}
	}
	public void remove_ConnectedWifi_From_QuickSetting()
	{
		try{
			if(isElementExist(Locators_Wifi.WifiQuick_MoresettingBtn)) {
				clickBtn(Locators_Wifi.WifiQuick_MoresettingBtn);
			}
			boolean connected = scrollToTextClick("Connected");
			if(connected==true)
			{
				scrollToElements(Locators_Wifi.WiFi_FrgtBtn);
				clickBtn(Locators_Wifi.WiFi_FrgtBtn);
				customWait(3000);
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.ERROR,"Error in locators->  remove_ConnectedWifi_From_QuickSetting()");
			e.printStackTrace();
		}catch (Exception e) {
			test.log(LogStatus.ERROR,"Exeption in ->  remove_ConnectedWifi_From_QuickSetting()");
			e.printStackTrace();
		}
	}
	public void verify_WifiScanningDevice(SoftAssert sa)
	{
		try{
			 if(wait(Locators_Wifi.ScanningProgress,30)) {
				APP_LOGS.info("Scanning near by available wifi networks");
				sa.assertTrue(true, "Scanning near by available wifi networks");
				test.log(LogStatus.PASS, "Scanning near by available wifi networks");	
			}else {
				APP_LOGS.info("Failed to scan the wifi networks");
				sa.fail();
				test.log(LogStatus.FAIL, "Failed to scan the wifi networks");
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.ERROR,"Error in locators-> verify_WifiScanningDevice()");
			e.printStackTrace();
		}catch (Exception e) {
			test.log(LogStatus.ERROR,"Exeption in functionality-> verify_WifiScanningDevice()");
			e.printStackTrace();
		}
	}
	public void verify_wifi_IsTurnedOn(SoftAssert sa)
	{
		try{
			wait(Locators_Wifi.Switch,30);
			 
			 if(Locators_Wifi.Switch.getText().equalsIgnoreCase("on")) {
				APP_LOGS.info("Wifi is turned on in notification bar");
				sa.assertTrue(true, "Wifi is turned on in notification bar");
				test.log(LogStatus.PASS, "Wifi is turned on in notification bar");	
			}else {
				APP_LOGS.info("Failed -> Wifi is not turned on");
				sa.fail();
				test.log(LogStatus.FAIL, "Failed -> Wifi is not turned on");
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.ERROR,"Error in locators-> verify_wifi_IsTurnedOn()");
			e.printStackTrace();
		}catch (Exception e) {
			test.log(LogStatus.ERROR,"Exeption in -> verify_wifi_IsTurnedOn()");
			e.printStackTrace();
		}
	}
	public void verify_quickWifiConnected(SoftAssert sa)
	{
		try{
			
			
			 aDriver.pressKeyCode(AndroidKeyCode.HOME);
			 aDriver.startActivity("com.google.android.youtube", "com.google.android.apps.youtube.app.WatchWhileActivity");
			 
			 if(!isElementExist(Locators_Wifi.Wifi_youtubeErrorMsg)) {
				APP_LOGS.info("Wifi Connected and given site is opened successfully");
				sa.assertTrue(true, "Wifi Connected  and given site is opened successfully");
				test.log(LogStatus.PASS, "Wifi Connected  and given site is opened successfully");	
			}else {
				APP_LOGS.info("Failed to connect with wifi network");
				sa.fail();
				test.log(LogStatus.FAIL, "Failed to connect with wifi network");
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.ERROR,"Error in locators->verify_quickWifiConnected()");
			e.printStackTrace();
		}catch (Exception e) {
			test.log(LogStatus.ERROR,"Exeption in ->verify_quickWifiConnected()");
			e.printStackTrace();
		}
	}
	
	public void swipeUp() {
		try {
			Runtime r = Runtime.getRuntime();
			r.exec("adb shell input swipe 800 400 400 400");
		}catch (Exception e) {
			test.log(LogStatus.ERROR, "Cannot interract with command Prompt");
		}
		
	}
	
	public void clickOn_SecurityAndLocation(){

		try {
			scrollToElements(Locators_Wifi.SecurityAndLocation);
			if(isElementExist(Locators_Wifi.SecurityAndLocation)) {
				clickBtn(Locators_Wifi.SecurityAndLocation);
			}else {
				scrollToTextClick("Security & location");
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.ERROR, "Error in the locators-> clickOn_SecurityAndLocation()");
			e.printStackTrace();
		}catch (Exception e) {
			test.log(LogStatus.ERROR, "Exeption in -> clickOn_SecurityAndLocation()");
			e.printStackTrace();
		}
	}
	
	public void clickOn_Location(){
		try {
			scrollToElements(Locators_Wifi.Location);
			if(isElementExist(Locators_Wifi.Location)) {
				clickBtn(Locators_Wifi.Location);
			}else {
				scrollToTextClick("Location");
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.ERROR, "Error in the locators-> clickOn_Location()");
			e.printStackTrace();
		}catch (Exception e) {
			test.log(LogStatus.ERROR, "Exeption in -> clickOn_Location()");
			e.printStackTrace();
		}
	}
	
	public void validate_ScanningOpt(SoftAssert sa)
	{
		try{
			
			boolean scanning = scrollToText("Scanning");
			
			if(scanning == true){
				APP_LOGS.info("Scanning option is present in location setting");
				sa.assertTrue(true, "Scanning option is present in location setting");
				test.log(LogStatus.PASS,"Scanning option is present in location setting");	
			}
			else {
				APP_LOGS.info("Failed -> Scanning option is not displayed in location setting");
				sa.fail();
				test.log(LogStatus.FAIL, "Failed -> Scanning option is not displayed in location setting");
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.ERROR,"Error in locators->validate_ScanningOpt()");
			e.printStackTrace();
		}catch (Exception e) {
			test.log(LogStatus.ERROR,"Exeption in ->validate_ScanningOpt()");
			e.printStackTrace();
		}
	}
	
	public void turnOff_Wifi(){

		try {
			wait(Locators_Wifi.Wifi_Switch_Btn,60) ;
			if(Locators_Wifi.Wifi_Switch_Btn.getText().equalsIgnoreCase("on")) {
				clickBtn(Locators_Wifi.Wifi_Switch_Btn);
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.ERROR, "Error in the locators-> turnOff_Wifi()");
			e.printStackTrace();

		}catch (Exception e) {
			test.log(LogStatus.ERROR, "Exeption in -> turnOff_Wifi()");
			e.printStackTrace();
		}
	}
	
	public void tapOn_WifiScanningSetting(){

		try {
			customWait(1000);
			TouchAction ta = new TouchAction(aDriver);
		    ta.tap(750,787).perform();
		    ta.tap(500, 900).perform();
			}
		catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.ERROR, "Error in the locators-> tapOn_WifiScanningSetting()");
			e.printStackTrace();
		}catch (Exception e) {
			test.log(LogStatus.ERROR, "Exeption in -> tapOn_WifiScanningSetting()");
			e.printStackTrace();
		}
	}
	
	public void validate_ScanningSetting(SoftAssert sa)
	{
		try{
			boolean scanning = scrollToText("Scanning");	
			if(scanning ==true){
				
				APP_LOGS.info("Scanning setting option displayed in wifi setting");
				sa.assertTrue(true, "Scanning setting option displayed in wifi setting");
				test.log(LogStatus.PASS,"Scanning setting option displayed in wifi setting");	
			}
			else {
				
				APP_LOGS.info("Scanning setting is not displayed");
				sa.fail();
				test.log(LogStatus.FAIL, "Scanning setting is not displayed");
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.ERROR,"Error in locators->validate_ScanningSetting()");
			e.printStackTrace();
		}catch (Exception e) {
			test.log(LogStatus.ERROR,"Exeption in ->validate_ScanningSetting()");
			e.printStackTrace();
		}
	}
	public void select_SSID(String ssid) {
		try {
			boolean wifiSSID=scrollToTextClick(ssid);
			while(wifiSSID==false) {
				wifiSSID=scrollToTextClick(ssid);
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.ERROR,"Error in locators-> select_SSID()");
			e.printStackTrace();
		}catch (Exception e) {
			test.log(LogStatus.ERROR,"Exeption in -> select_SSID()");
			e.printStackTrace();
		}
	}
	public void verify_FORGET_CANCEL_Displayed(SoftAssert sa)
	{
		try{				
			boolean forget = isElementExist(Locators_Wifi.FORGET);
			boolean cancel = isElementExist(Locators_Wifi.CANCEL);	
			if(forget && cancel){
				APP_LOGS.info("Forget And Cancel Button are Present in saved networks");
				sa.assertTrue(true, "Forget And Cancel Button are Present in saved networks");
				test.log(LogStatus.PASS,"Forget And Cancel Button are Present in saved networks");	
			}
			else {
				APP_LOGS.info("Forget And Cancel Button are Not Present");
				sa.fail();
				test.log(LogStatus.FAIL, "Forget And Cancel Button are Not Present");
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.ERROR,"Error in locators-> verify_FORGET_CANCEL_Displayed()");
			e.printStackTrace();
		}catch (Exception e) {
			test.log(LogStatus.ERROR,"Exeption in -> verify_FORGET_CANCEL_Displayed()");
			e.printStackTrace();
		}
	}
	
	public void validate_CnclBtn(SoftAssert sa,String ssid)
	{
		boolean verifySSID=false;
		try{
			if(isElementExist(Locators_Wifi.CANCEL)) {
				clickBtn(Locators_Wifi.CANCEL);
				verifySSID=scrollTo(ssid);
			}
			if(verifySSID){
				APP_LOGS.info("Verified the functionality of Cancel button");
				sa.assertTrue(true, "Verified the functionality of Cancel button");
				test.log(LogStatus.PASS,"Verified the functionality of Cancel button");
			}
			else {
				APP_LOGS.info("Failed -> Cancel button is not functioning properly");
				sa.fail();
				test.log(LogStatus.FAIL, "Failed -> Cancel button is not functioning properly");
				}
		} catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.ERROR,"Error in locators->validate_CnclBtn()");
			e.printStackTrace();
		}catch (Exception e) {
			test.log(LogStatus.ERROR,"Exeption in ->validate_CnclBtn()");
			e.printStackTrace();
		}
	}
	
	public void validate_FrgtBtn(SoftAssert sa,String ssid)
	{
		try{
			if(isElementExist(Locators_Wifi.FORGET)) {
				clickBtn(Locators_Wifi.FORGET);
			}
			boolean verifySSID = scrollTo(ssid);	
			if(verifySSID==false){	
				APP_LOGS.info("Verified the functionality of Forget button");
				sa.assertTrue(true, "Verified the functionality of Forget button");
				test.log(LogStatus.PASS,"Verified the functionality of Forget button");	
			}
			else {		
				APP_LOGS.info("Failed -> Forget button is not functioning properly");
				sa.fail();
				test.log(LogStatus.FAIL, "Failed -> Forget button is not functioning properly");
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.ERROR,"Error in locators->validate_FrgtBtn()");
			e.printStackTrace();
		}catch (Exception e) {
			test.log(LogStatus.ERROR,"Exeption in ->validate_FrgtBtn()");
			e.printStackTrace();
		}
	}
	
	public boolean scrollToTextContains(String text) {
		/*
		  Method used to select an element on the page by scrolling the Scroll View/List View
		 */
		boolean check = false;
		try {  
			String scrollable = "new UiScrollable(new UiSelector().scrollable(true))";
			String textElement = ".scrollIntoView(new UiSelector().textContains(\""+ text +"\"))";
			aDriver.findElementByAndroidUIAutomator(scrollable+textElement).click();
			APP_LOGS.info("Searched application is found sucessfully : ");
			check = true;
			return check;
		}
		catch(NoSuchElementException e) {
			return check;
		}
	}
	public void clickOn_WifiPreferences(){

		try {
			scrollToElements(Locators_Wifi.WiFi_preferences);
			if(isElementExist(Locators_Wifi.WiFi_preferences)) {
				clickBtn(Locators_Wifi.WiFi_preferences);
			}else {
				scrollToTextContains("preferences");
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.ERROR, "Error in the locators->clickOn_WifiPreferences()");
			e.printStackTrace();
		}catch (Exception e) {
			test.log(LogStatus.ERROR, "Exeption in ->clickOn_WifiPreferences()");
			e.printStackTrace();
		}
	}
	
	public void clickOn_Advanced(){

		try {
			scrollToElements(Locators_Wifi.Advanced);
			if(isElementExist(Locators_Wifi.Advanced)) {
				clickBtn(Locators_Wifi.Advanced);
			}else {
				scrollToTextClick("Advanced");
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.ERROR, "Error in the locators->clickOn_Advanced()");
			e.printStackTrace();
		}catch (Exception e) {
			test.log(LogStatus.ERROR, "Exeption in ->clickOn_Advanced()");
			e.printStackTrace();
		}
	}
	public void clickOn_System(){

		try {
			scrollToElements(Locators_Wifi.System);
			if(isElementExist(Locators_Wifi.System)) {
				clickBtn(Locators_Wifi.System);
			}else {
				scrollToTextClick("System");
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.ERROR, "Error in the locators->clickOn_System()");
			e.printStackTrace();
		}catch (Exception e) {
			test.log(LogStatus.ERROR, "Exeption in ->clickOn_System()");
			e.printStackTrace();
		}
	}
	public void clickOn_Aboutphone(){

		try {
			scrollToElements(Locators_Wifi.Aboutphone);
			if(isElementExist(Locators_Wifi.Aboutphone)) {
				clickBtn(Locators_Wifi.Aboutphone);
			}else {
				scrollToTextClick("About phone");
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.ERROR, "Error in the locators->clickOn_Aboutphone()");
			e.printStackTrace();
		}catch (Exception e) {
			test.log(LogStatus.ERROR, "Exeption in ->clickOn_Aboutphone()");
			e.printStackTrace();
		}
	}
	public void clickOn_Status(){

		try {
			scrollToElements(Locators_Wifi.Status);
			if(isElementExist(Locators_Wifi.Status)) {
				clickBtn(Locators_Wifi.Status);
			}else {
				scrollToTextClick("Status");
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.ERROR, "Error in the locators->clickOn_Status()");
			e.printStackTrace();
		}catch (Exception e) {
			test.log(LogStatus.ERROR, "Exeption in ->clickOn_Status()");
			e.printStackTrace();
		}
	}
	public void clickOn_OK(){

		try {
			Locators_Wifi.WifiDirect_OKBtn.click();
			
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->clickOn_OK()");
			e.printStackTrace();

		}catch (Exception e) {

			test.log(LogStatus.ERROR, "Exeption in ->clickOn_OK()");
		}
	}
	
	public void clickOn_WifiDirect(){

		try {
			scrollToElements(Locators_Wifi.WifiDirectLnk);
			if(isElementExist(Locators_Wifi.WifiDirectLnk)) {
				clickBtn(Locators_Wifi.WifiDirectLnk);
			}else {
				scrollToTextContains("Direct");
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.ERROR, "Error in the locators-> clickOn_WifiDirect()");
			e.printStackTrace();
		}catch (Exception e) {
			test.log(LogStatus.ERROR, "Exeption in -> clickOn_WifiDirect()");
			e.printStackTrace();
		}
	}
	
	public void clickOn_WifiDirect_RenameDevice(){

		try {
			clickBtn(Locators_Wifi.WifiDirect_RenameDevices);
		} catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.ERROR, "Error in the locators->clickOn_WifiDirect_RenameDevice()");
			e.printStackTrace();
		}catch (Exception e) {
			test.log(LogStatus.ERROR, "Exeption in ->clickOn_WifiDirect_RenameDevice()");
			e.printStackTrace();
		}
	}
	
	public void renameDevice(String rename){

		try {
			enterTextToInputField(Locators_Wifi.setdata, rename);
			clickBtn(Locators_Wifi.OK);
		} catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.ERROR, "Error in the locators-> renameDevice()");
			e.printStackTrace();
		}catch (Exception e) {
			test.log(LogStatus.ERROR, "Exeption in -> renameDevice()");
			e.printStackTrace();
		}
	}
	
	
	
	public void verify_MACAddress_IsPresent(SoftAssert sa)
	{
		try{
			scrollToTextContains("MAC");
			String macID = Locators_Wifi.Wifi_MacAddress.getText();
			System.out.println("Mac id = "+macID);
			if(macID!=null){
				APP_LOGS.info("MAC address is displayed under wifi settings");
				sa.assertTrue(true, "MAC address is displayed under wifi settings");
				test.log(LogStatus.PASS,"MAC address is displayed under wifi settings");	
			}
			else {
				APP_LOGS.info("Failed -> MAC address is not displayed under wifi setting");
				sa.fail();
				test.log(LogStatus.FAIL, "Failed -> MAC address is not displayed under wifi setting");
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.ERROR,"Error in locators->verify_MACAddress_IsPresent()");
			e.printStackTrace();
		}catch (Exception e) {
			test.log(LogStatus.ERROR,"Exeption in ->verify_MACAddress_IsPresent()");
			e.printStackTrace();
		}
	}
	
	public void validate_IP_Address(SoftAssert sa)
	{
		try{
			boolean check1 = scrollTo("IP address");
			String Ip_ID = Locators_Wifi.Wifi_IpDetails.getText();
			boolean check2 = scrollTo(Ip_ID);
			
			if(Ip_ID.equalsIgnoreCase("Unavailable"))
			{
				APP_LOGS.info("Wifi disconneced and IP address is unavailable under wifi settings");
				sa.assertTrue(true, "Wifi disconneced and IP address is unavailable under wifi settings");
				test.log(LogStatus.PASS,"Wifi disconneced and IP address is unavailable under wifi settings");
			}
			else if(check1 && check2){	
				APP_LOGS.info("IP address is displayed under wifi setting");
				sa.assertTrue(true, "IP address is displayed under wifi setting");
				test.log(LogStatus.PASS,"IP address is displayed under wifi setting");	
			}
			else {
				APP_LOGS.info("Failed -> IP address is not displayed under wifi setting");
				sa.fail();
				test.log(LogStatus.FAIL, "Failed -> IP address is not displayed under wifi setting");
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.ERROR,"Error in locators->validate_IP_Address()");
			e.printStackTrace();
		}catch (Exception e) {
			test.log(LogStatus.ERROR,"Exeption in ->validate_IP_Address()");
			e.printStackTrace();
		}
	}
	
	public void validate_NetworkIsSvdPassWith20Char(SoftAssert sa,String ssid)
	{
		try{
			
			scrollToText("Saved networks");
			clickBtn(Locators_Wifi.WiFi_savedNtw);
			boolean check1 = scrollToText(ssid);
			clickBackButton(1);
			
			if(check1 == true){
				
				APP_LOGS.info("WPA Network Added Succesfully by Giving 20 Character as Password");
				sa.assertTrue(true, "WPA Network Added Succesfully by Giving 20 Character as Password");
				test.log(LogStatus.PASS,"WPA Network Added Succesfully by Giving 20 Character as Password");	


			}
			else {
				
				APP_LOGS.info("WPA Network Adding Failed by Giving 20 Character as Password");
				sa.fail();
				test.log(LogStatus.FAIL, "WPA Network Adding Failed by Giving 20 Character as Password");

			}

		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR,"Error in locators->validate_NetworkIsSvdPassWith20Char()");
			
		}catch (Exception e) {
			test.log(LogStatus.ERROR,"Exeption in ->validate_NetworkIsSvdPassWith20Char()");
		}
	}
	
	public void clickOn_InstallCerficate(){

		try {
			scrollToElements(Locators_Wifi.Install_certificates);
			if(isElementExist(Locators_Wifi.Install_certificates)) {
				clickBtn(Locators_Wifi.Install_certificates);
			}else {
				scrollToTextClick("Install certificates");		
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.ERROR, "Error in the locators->clickOn_InstallCerficate()");
			e.printStackTrace();
		}catch (Exception e) {
			test.log(LogStatus.ERROR, "Exeption in ->clickOn_InstallCerficate()");
			e.printStackTrace();
		}
	}
	public void Validate_navigatedToStorageLocation(SoftAssert sa){

		try {
			boolean Recent = isElementExist(Locators_Wifi.Recent);	
				if(Recent == true){
					APP_LOGS.info("Navigated to storage location");
					sa.assertTrue(true, "Navigated to storage location");
					test.log(LogStatus.PASS,"Navigated to storage location");
				}
				else {
					APP_LOGS.info("Failed to Navigate to Storage Location");
					sa.fail();
					test.log(LogStatus.FAIL, "Failed to Navigate to Storage Location");
				}
			} catch (org.openqa.selenium.NoSuchElementException e) {
				test.log(LogStatus.ERROR,"Error in locators-> Validate_navigatedToStorageLocation()");
				e.printStackTrace();
			}catch (Exception e) {
				test.log(LogStatus.ERROR,"Exeption in -> Validate_navigatedToStorageLocation()");
				e.printStackTrace();
			}
		}	
	public void verify_InstallCertificate(SoftAssert sa){

		try {
			boolean Recent = isElementExist(Locators_Wifi.Recent);	
				if(Recent == true){
					APP_LOGS.info("Install certificate is working fine in wifi advaced menu");
					sa.assertTrue(true, "Install certificate is working fine in wifi advaced menu");
					test.log(LogStatus.PASS,"Install certificate is working fine in wifi advaced menu");
				}
				else {
					APP_LOGS.info("Failed to Navigate to Storage Location");
					sa.fail();
					test.log(LogStatus.FAIL, "Failed to Navigate to Storage Location");
				}
			} catch (org.openqa.selenium.NoSuchElementException e) {
				test.log(LogStatus.ERROR,"Error in locators-> verify_InstallCertificate()");
				e.printStackTrace();
			}catch (Exception e) {
				test.log(LogStatus.ERROR,"Exeption in -> Validate_navigatedToStorageLocation()");
				e.printStackTrace();
			}
		}	
	public void validate_AdvOpt(SoftAssert sa){

		try {
			boolean advancedOptions = isElementExist(Locators_Wifi.Advanced_options);
				if(advancedOptions == true){
					APP_LOGS.info("Advanced Options is displayed in wifi password screen");
					sa.assertTrue(true, "Advanced Options is displayed in wifi password screen");
					test.log(LogStatus.PASS,"Advanced Options is displayed in wifi password screen");	
				}
				else {				
					APP_LOGS.info("Advanced options is not displayed");
					sa.fail();
					test.log(LogStatus.FAIL, "Advanced options is not displayed");
					}
			} catch (org.openqa.selenium.NoSuchElementException e) {
				test.log(LogStatus.ERROR,"Error in locators->validate_AdvOpt()");
				e.printStackTrace();
			}catch (Exception e) {
				test.log(LogStatus.ERROR,"Exeption in ->validate_AdvOpt()");
				e.printStackTrace();
			}
		}
	
	public void validate_userAbleToClickAdvOpt(SoftAssert sa){

		try {
			boolean clickAdvOpt = scrollToTextClick("Advanced options");				
				if(clickAdvOpt == true){	
					APP_LOGS.info("Successfully clicked on Advanced options");
					sa.assertTrue(true, "Successfully clicked on Advanced options");
					test.log(LogStatus.PASS,"Successfully clicked on Advanced options");	
				}
				else {
					APP_LOGS.info("Failed to click on Advanced options");
					sa.fail();
					test.log(LogStatus.FAIL, "Failed to click on Advanced options");
				}
			} catch (org.openqa.selenium.NoSuchElementException e) {
				test.log(LogStatus.ERROR,"Error in locators->validate_userAbleToClickAdvOpt()");
				e.printStackTrace();
			}catch (Exception e) {
				test.log(LogStatus.ERROR,"Exeption in ->validate_userAbleToClickAdvOpt()");
				e.printStackTrace();
			}
		}
	

	public void validate_ProxyOpt(SoftAssert sa){

		try {
			 clickBtn(Locators_Wifi.Spinner);
			 boolean none = isElementExist(Locators_Wifi.None);
			 boolean  manual = isElementExist(Locators_Wifi.Manual);
			 boolean proxyAutoConfig = isElementExist(Locators_Wifi.Proxy_Auto_Config);		
				if(none && manual && proxyAutoConfig){		
					APP_LOGS.info("None,Manual,Proxy Auto-Config options are displayed in proxy setting");
					sa.assertTrue(true, "None,Manual,Proxy Auto-Config options are displayed in proxy setting");
					test.log(LogStatus.PASS,"None,Manual,Proxy Auto-Config options are displayed in proxy setting");	
				}
				else {		
					APP_LOGS.info("Failed to Show None,Manual,Proxy Auto-Config Options in Proxy Setting");
					sa.fail();
					test.log(LogStatus.FAIL, "Failed to Show None,Manual,Proxy Auto-Config Options in Proxy Setting");
				}
			} catch (org.openqa.selenium.NoSuchElementException e) {
				test.log(LogStatus.ERROR,"Error in locators->validate_ProxyOpt()");
				e.printStackTrace();
			}catch (Exception e) {
				test.log(LogStatus.ERROR,"Exeption in ->validate_ProxyOpt()");
				e.printStackTrace();
			}
		}
	
	public void validate_AlloptSelectableInProxy(SoftAssert sa){

		try {
			
				clickBtn(Locators_Wifi.Spinner);
				clickBtn(Locators_Wifi.None);		
				boolean none = isElementExist(Locators_Wifi.None);
			
				clickBtn(Locators_Wifi.Spinner);
				clickBtn(Locators_Wifi.Manual);		
				boolean manual = isElementExist(Locators_Wifi.Manual);
				
				clickBtn(Locators_Wifi.Spinner);
				clickBtn(Locators_Wifi.Proxy_Auto_Config);		
				boolean proxyAutoConfig = isElementExist(Locators_Wifi.Proxy_Auto_Config);
			
				if(none && manual  && proxyAutoConfig){
					APP_LOGS.info("None,Manual,Proxy Auto-Config options are selectable in proxy setting");
					sa.assertTrue(true, "None,Manual,Proxy Auto-Config options are selectable in proxy setting");
					test.log(LogStatus.PASS,"None,Manual,Proxy Auto-Config options are selectable in proxy setting");	
				}
				else {
					APP_LOGS.info("Failed to Select None,Manual,Proxy Auto-Config Options in Proxy Setting");
					sa.fail();
					test.log(LogStatus.FAIL, "Failed to Select None,Manual,Proxy Auto-Config Options in Proxy Setting");
				}
			} catch (org.openqa.selenium.NoSuchElementException e) {
				test.log(LogStatus.ERROR,"Error in locators-> validate_AlloptSelectableInProxy()");
				e.printStackTrace();
			}catch (Exception e) {
				test.log(LogStatus.ERROR,"Exeption in -> validate_AlloptSelectableInProxy()");
				e.printStackTrace();
			}
		}
	
	public void validate_IpOpt(SoftAssert sa){

		try {
			scrollToText("IP settings");
			clickBtn(Locators_Wifi.IpsettingSpinner);
			 boolean DHCP = isElementExist(Locators_Wifi.DHCP);
			 boolean Static = isElementExist(Locators_Wifi.Static);
				
				if(DHCP && DHCP){
					APP_LOGS.info("DHCP and Static options are displayed under IP settings");
					sa.assertTrue(true, "DHCP and Static options are displayed under IP settings");
					test.log(LogStatus.PASS,"DHCP and Static options are displayed under IP settings");	
				}
				else {			
					APP_LOGS.info("Failed to Show DHCP and Static Options under IP Setting");
					sa.fail();
					test.log(LogStatus.FAIL, "Failed to Show DHCP and Static Options under IP Setting");
				}

			} catch (org.openqa.selenium.NoSuchElementException e) {
				test.log(LogStatus.ERROR,"Error in locators->validate_IpOpt()");
				e.printStackTrace();
			}catch (Exception e) {
				test.log(LogStatus.ERROR,"Exeption in ->validate_IpOpt()");
				e.printStackTrace();
			}
		}
	
	public void validate_AlloptSelectableInIP(SoftAssert sa){

		try {
		
			scrollText("IP settings");
			clickBtn(Locators_Wifi.IpsettingSpinner);
			clickBtn(Locators_Wifi.DHCP);
			boolean DHCP=isElementExist(Locators_Wifi.DHCP);
			clickBtn(Locators_Wifi.IpsettingSpinner);
			clickBtn(Locators_Wifi.Static);
			boolean Static=isElementExist(Locators_Wifi.Static);
				if(DHCP &&  Static){
					APP_LOGS.info("DHCP and Static options are selectable in IP settings");
					sa.assertTrue(true, "DHCP and Static options are selectable in IP settings");
					test.log(LogStatus.PASS,"DHCP and Static options are selectable in IP settings");	
				}
				else {
					APP_LOGS.info("Failed to Select DHCP and Static options in IP Setting");
					sa.fail();
					test.log(LogStatus.FAIL, "Failed to Select DHCP and Static options in IP Setting");
				}
			} catch (org.openqa.selenium.NoSuchElementException e) {
				test.log(LogStatus.ERROR,"Error in locators->validate_AlloptSelectableInIP()");
				e.printStackTrace();
			}catch (Exception e) {
				test.log(LogStatus.ERROR,"Exeption in ->validate_AlloptSelectableInIP()");
				e.printStackTrace();
			}
		}
	public void clickOn_QuickSettings() {
		/*
		 * Get settings option from notification window
		 */
		try {
			minWait();
			clickBtn(Locators_Wifi.openSettings);
		} catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.ERROR, "Error in the locators -> clickOn_QuickSettings()");
			e.printStackTrace();
		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exeption in -> clickOn_QuickSettings()");
			e.printStackTrace();
		}
	}
	public int[] get_WifiLocation() {
		int wificoordinates[]=new int[2];
		try {
			 org.openqa.selenium.Point size = Locators_Wifi.Wifi_icon.getLocation();
			 int x=size.getX();
			 int y=size.getY();
			 wificoordinates[0]=x;
			 wificoordinates[1]=y;
		}catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.ERROR,"Error in locators-> get_WifiLocation()");
			e.printStackTrace();
		}catch (Exception e) {
			test.log(LogStatus.ERROR,"Exeption in -> get_WifiLocation()");
			e.printStackTrace();
		}
		return wificoordinates;
	}
	public void move_WifiIcon( )
	{
		try{
			
			scrollToElements(Locators_Wifi.Wifi_icon);
			drag_Drop(Locators_Wifi.Wifi_icon,Locators_Wifi.Cast_icon);
		} catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.ERROR,"Error in locators-> move_WifiIcon()");
			e.printStackTrace();
		}catch (Exception e) {
			test.log(LogStatus.ERROR,"Exeption in -> move_WifiIcon()");
			e.printStackTrace();
		}
	}
	public void validate_WifiIcon_Moved(int []beforeSuffle,int []afterSuffle,SoftAssert sa){

		try {
			System.out.println("before suffle x,y values = "+Arrays.toString(beforeSuffle));
			System.out.println("after suffle x,y values = "+Arrays.toString(afterSuffle));

			boolean xValue=(beforeSuffle[0]!=afterSuffle[0])?true:false;
			boolean yValue=(beforeSuffle[1]!=afterSuffle[1])?true:false;

			if(xValue==true || yValue==true){
					APP_LOGS.info("Wifi icon is moved successfully");
					sa.assertTrue(true, "Wifi icon is moved successfully");
					test.log(LogStatus.PASS,"Wifi icon is moved successfully");	
				}
				else {			
					APP_LOGS.info("Failed to move wifi icon");
					sa.fail();
					test.log(LogStatus.FAIL, "Failed to move wifi icon");
				}
			} catch (org.openqa.selenium.NoSuchElementException e) {
				test.log(LogStatus.ERROR,"Error in locators-> validate_WifiIcon_Moved()");
				e.printStackTrace();
			}catch (Exception e) {
				test.log(LogStatus.ERROR,"Exeption in -> validate_WifiIcon_Moved()");
				e.printStackTrace();
			}
		}
	public void clickOn_Edit() {
		try {
			clickBtn(Locators_Wifi.pencil);
			System.out.println("Pencil");
		} catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.ERROR, "Error in the locators -> clickOn_Edit()");
			e.printStackTrace();
		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exeption in -> clickOn_Edit()");
			e.printStackTrace();
		}
	}
	public void reset_Icons() {
		try {
			clickBtn(Locators_Wifi.MoreOptions);
			minWait();
			System.out.println("More options");
			clickBtn(Locators_Wifi.Reset);
			System.out.println("Reset");

		} catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.ERROR, "Error in the locators -> reset_Icons()");
			e.printStackTrace();
		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exeption in -> reset_Icons()");
			e.printStackTrace();
		}
	}
	public void clickOn_AdvancedOptions() {
		try {
			scrollToElements(Locators_Wifi.Advanced_options);
			clickBtn(Locators_Wifi.Advanced_options);
		}catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.ERROR, "Error in the locators -> clickOn_AdvancedOptions()");
			e.printStackTrace();
		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exeption in -> clickOn_AdvancedOptions()");
			e.printStackTrace();
		}
	}
	public void validate_AvlblWifi(SoftAssert sa){

		try {
			
			
			clickBtn(Locators_Wifi.IpsettingSpinner);
			boolean DHCP=isElementExist(Locators_Wifi.DHCP);
			boolean Static=isElementExist(Locators_Wifi.Static); 
				if(DHCP && Static){		
					APP_LOGS.info("Successfully Two options are Selectable in IP Setting");
					sa.assertTrue(true, "Successfully Two options are Selectable in IP Setting");
					test.log(LogStatus.PASS,"Successfully Two options are Selectable in IP Setting");	


				}
				else {
					
					APP_LOGS.info("Failed to Select Two Option in IP Setting");
					sa.fail();
					test.log(LogStatus.FAIL, "Failed to Select Two Option in IP Setting");

				}

			} catch (org.openqa.selenium.NoSuchElementException e) {

				test.log(LogStatus.ERROR,"Error in locators->validate_AvlblWifi()");
				
			}catch (Exception e) {
				test.log(LogStatus.ERROR,"Exeption in ->validate_AvlblWifi()");
			}
		}
	
	public void connectToNetwork(String name )
	{
		try{
			
			scrollToTextClick(name);
			

		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR,"Error in locators->connectToNetwork()");
		}catch (Exception e) {
			test.log(LogStatus.ERROR,"Exeption in ->connectToNetwork()");
		}
	}
	
	public void validate_PassIsShown(SoftAssert sa){

		try {	
				if(isElementExist(Locators_Wifi.Wifi_PasswordTxt)){	
					APP_LOGS.info("Deleted the connected network and password text box is displayed when trying to reconnect same network");
					sa.assertTrue(true, "Deleted the connected network and password text box is displayed when trying to reconnect same network");
					test.log(LogStatus.PASS,"Deleted the connected network and password text box is displayed when trying to reconnect same network");	
				}
				else {		
					APP_LOGS.info("Failed to show Password text box");
					sa.fail();
					test.log(LogStatus.FAIL, "Failed to show Password text box");
				}
			} catch (org.openqa.selenium.NoSuchElementException e) {
				test.log(LogStatus.ERROR,"Error in locators->validate_PassIsShown()");
				e.printStackTrace();
			}catch (Exception e) {
				test.log(LogStatus.ERROR,"Exeption in ->validate_PassIsShown()");
				e.printStackTrace();
			}
		}
	
	
	public void validate_WifiConnected(SoftAssert sa){

		try {
			
			boolean check1 = scrollToText("Connected");			
				if(check1 == true){
					APP_LOGS.info("Wifi Connected Successfully in LandScape Mode");
					sa.assertTrue(true, "Wifi Connected Successfully in LandScape Mode");
					test.log(LogStatus.PASS,"Wifi Connected Successfully in LandScape Mode");	
				}
				else {		
					APP_LOGS.info("Failed to Connect Wifi In LandScape Mode");
					sa.fail();
					test.log(LogStatus.FAIL, "Failed to Connect Wifi In LandScape Mode");
				}
			} catch (org.openqa.selenium.NoSuchElementException e) {
				test.log(LogStatus.ERROR,"Error in locators->validate_WifiConnected()");
				e.printStackTrace();
			}catch (Exception e) {
				test.log(LogStatus.ERROR,"Exeption in ->validate_WifiConnected()");
				e.printStackTrace();
			}
		}
	
	public void navigateTo_Accessibility()
	{
		try{
			
			scrollToElements(Locators_Wifi.setting_AccesibilityLnk);
			Locators_Wifi.setting_AccesibilityLnk.click();
			scrollToText("Auto-rotate screen");
			

		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR,"Error in locators->navigateTo_Accessibility()");
		}catch (Exception e) {
			test.log(LogStatus.ERROR,"Exeption in ->navigateTo_Accessibility()");
		}
	}
	public void clear_ChromeHistory() {
		try {
			Runtime.getRuntime().exec("adb -s "+p_Id+" shell pm clear com.android.chrome");
		}catch (Exception e) {
		
		}
	}
	public void check_AirplaneModeState_InRefDev()    {
		/*
		* Perform action for IMS registered check
		*/
		try {
		minWait();
		Process child = Runtime.getRuntime().exec("adb -s " + r_Id + " settings get global airplane_mode_on ");
		InputStream lsOut = child.getInputStream();
		InputStreamReader r = new InputStreamReader(lsOut);
		BufferedReader in = new BufferedReader(r);
		String line = in.readLine();
		System.out.println("Airplanemode state -> 0 = off ,1 = on "+line);

		minWait();
		} catch (IOException io) {
			test.log(LogStatus.ERROR, "Caught IOException in -> check_AirplaneModeState_InRefDev");
			io.printStackTrace();
		} catch (InterruptedException ie) {
			test.log(LogStatus.ERROR, "Caught InterruptedException  in -> check_AirplaneModeState_InRefDev");
			ie.printStackTrace();
		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exeption in functionality -> check_AirplaneModeState_InRefDev()");
			e.printStackTrace();
		}
		}
	public void longPressOn_ConnectedSSID(String ssid,String pwsd) {
		try {
			if(wait(Locators_Wifi.Connected,50)) {
				TouchAction ta=new TouchAction(aDriver);
				ta.longPress(Locators_Wifi.Connected, 10000).perform();
			}else {
				connect_to_WiFi(ssid, pwsd);
				if(wait(Locators_Wifi.Connected,50)) {
				TouchAction ta=new TouchAction(aDriver);
				ta.longPress(Locators_Wifi.Connected, 10000).perform();
				}
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.ERROR,"Error in locators-> longPressOn_ConnectedSSID()");
			e.printStackTrace();
		}catch (Exception e) {
			test.log(LogStatus.ERROR,"Exeption in -> longPressOn_ConnectedSSID()");
			e.printStackTrace();
		}
	}
	public boolean wait(AndroidElement element, int waitTime) {
		boolean availability = false;
		try {
			new WebDriverWait(aDriver, waitTime).until(ExpectedConditions.visibilityOf(element));
			availability = true;
		} catch (NoSuchElementException e) {
			availability = false;
		}
		return availability;
	}
	public void longPress_Ssid(String name)
	{
		try{
			boolean checkSSID=scrollToText(name);
			while(checkSSID==false) {
				checkSSID=scrollToText(name);
			}
			if(isElementExist(Locators_Wifi.FORGET)) {
				clickBtn(Locators_Wifi.FORGET);
				wait(Locators_Wifi.Wifi_PageLogo,30);
				checkSSID=scrollToText(name);
				while(checkSSID==false) {
					checkSSID=scrollToText(name);
				}
				if(isElementExist(Locators_Wifi.CANCEL)){
					clickBtn(Locators_Wifi.CANCEL);
				}
			}else if(isElementExist(Locators_Wifi.CANCEL)){
				clickBtn(Locators_Wifi.CANCEL);
			}
							
			AndroidElement ssid=aDriver.findElementByXPath("//android.widget.TextView[@text='"+name+"']");
			boolean wifiSSID=wait(ssid, 60);
			if(wifiSSID==true) {
				TouchAction ta=new TouchAction(aDriver);
				ta.longPress(ssid, 10000).perform();
			}else {
				scrollToElements(ssid);
				wifiSSID=isElementExist(ssid);
				while(wifiSSID==false) {
					scrollToElements(ssid);
					wifiSSID=isElementExist(ssid);
				}
				TouchAction ta=new TouchAction(aDriver);
				ta.longPress(ssid, 10000).perform();
			}
			/*scrollToText(name);
			List<AndroidElement> ele = Locators_Wifi.Wifi_WifiDetails;		
			for (AndroidElement androidElement : ele) {
				if(androidElement.getText().equalsIgnoreCase(name))
				{
					TouchAction ta = new TouchAction(aDriver);
					ta.longPress(androidElement).perform();
					break;
				}			
			}*/
		} catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.ERROR,"Error in locators->longPress_Ssid()");
			e.printStackTrace();
		}catch (Exception e) {
			test.log(LogStatus.ERROR,"Exeption in ->longPress_Ssid()");
			e.printStackTrace();
		}
	}
	
	public void validate_ConnectToNetwork(SoftAssert sa){

		try {
			
			boolean connectToNetwork = wait(Locators_Wifi.Connect_to_network,120);
				if(connectToNetwork == true){			
					APP_LOGS.info("Long pressed on ssid and connect to network is displayed");
					sa.assertTrue(true, "Long pressed on ssid and connect to network is displayed");
					test.log(LogStatus.PASS,"Long pressed on ssid and connect to network is displayed");	
				}
				else {			
					APP_LOGS.info("Failed -> Connect to network is not displayed ");
					test.log(LogStatus.FAIL, "Failed -> Connect to network is not displayed ");
					sa.fail();
				}
			} catch (org.openqa.selenium.NoSuchElementException e) {
				test.log(LogStatus.ERROR,"Error in locators-> validate_ConnectToNetwork()");
				e.printStackTrace();
			}catch (Exception e) {
				test.log(LogStatus.ERROR,"Exeption in -> validate_ConnectToNetwork()");
				e.printStackTrace();
			}
		}
	public void clickOn_ConnectToNetwork() {
		try {
			if(wait(Locators_Wifi.Connect_to_network,240)) {
				clickBtn(Locators_Wifi.Connect_to_network);
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.ERROR,"Error in locators-> clickOn_ConnectToNetwork()");
			e.printStackTrace();
		}catch (Exception e) {
			test.log(LogStatus.ERROR,"Exeption in -> clickOn_ConnectToNetwork()");
			e.printStackTrace();
		}
	}
	public String get_WifiIpAddress() {
		String wifi_IPAddress =null;
		try {
			scrollToTextContains("IP");
			wifi_IPAddress= Locators_Wifi.Wifi_IpDetails.getText();
			System.out.println("ip detail "+wifi_IPAddress);
		} catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.ERROR,"Error in locators-> get_WifiIpAddress()");
			e.printStackTrace();
		}catch (Exception e) {
			test.log(LogStatus.ERROR,"Exeption in -> get_WifiIpAddress()");
			e.printStackTrace();
		}
		return wifi_IPAddress;
	}
	public void validate_IPAddress_AbtPhn(String wifiIpAddress,SoftAssert sa){

		try {
					String IP_ID_Abtphn = Locators_Wifi.Abtphone_IpDetails.getText();
					System.out.println("Wifi ip address = "+wifiIpAddress);
					System.out.println("System ip address = "+IP_ID_Abtphn);
				if(wifiIpAddress.equalsIgnoreCase(IP_ID_Abtphn)){	
					APP_LOGS.info("IP address is same in Wi-Fi Advance setting and in About Phone");
					sa.assertTrue(true, "IP address is same in Wi-Fi Advance setting and in About Phone");
					test.log(LogStatus.PASS,"IP address is same in Wi-Fi Advance setting and in About Phone");	
					}
				else {		
					APP_LOGS.info("IP address is different in Wi-Fi Advance setting and in About Phone");
					sa.fail();
					test.log(LogStatus.FAIL, "IP address is different in Wi-Fi Advance setting and in About Phone");
				}
			} catch (org.openqa.selenium.NoSuchElementException e) {
				test.log(LogStatus.ERROR,"Error in locators->validate_IPAddress_AbtPhn()");
				e.printStackTrace();
			}catch (Exception e) {
				test.log(LogStatus.ERROR,"Exeption in ->validate_IPAddress_AbtPhn()");
				e.printStackTrace();
			}
		}
	
	public void verify_TwoOptions_InScanningSettings(SoftAssert sa)
	{
		try{
			
			boolean wifiScanning = isElementExist(Locators_Wifi.Wi_Fi_scanning);
			boolean bluetoothScanning = isElementExist(Locators_Wifi.Bluetooth_scanning);
			
			if(wifiScanning && bluetoothScanning){		
				APP_LOGS.info("Two Options in Scanning Setting is displayed Successfully");
				sa.assertTrue(true, "Two Options in Scanning Setting is displayed Successfully");
				test.log(LogStatus.PASS,"Two Options in Scanning Setting is displayed Successfully");	
			}
			else {		
				APP_LOGS.info("Failed to show Two Options in Scanning Setting");
				sa.fail();
				test.log(LogStatus.FAIL, "Failed to show Two Options in Scanning Setting");
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.ERROR,"Error in locators->verify_TwoOptions_InScanningSettings()");
			e.printStackTrace();
		}catch (Exception e) {
			test.log(LogStatus.ERROR,"Exeption in ->verify_TwoOptions_InScanningSettings()");
			e.printStackTrace();
		}
	}
	
	public void verify_ScanningAlwaysAvailable_IsNotPresent(SoftAssert sa)
	{
		try{
			boolean scanning = scrollToText("Scanning Always Available");		
			if(scanning == false){	
				APP_LOGS.info("Scanning Always Available is not present under Advanced wi-Fi preferences");
				sa.assertTrue(true, "Scanning Always Available is not present under Advanced wi-Fi preferences");
				test.log(LogStatus.PASS,"Scanning Always Available is not present under Advanced wi-Fi preferences");	
			}
			else {		
				APP_LOGS.info("Scanning Always Available is present ");
				sa.fail();
				test.log(LogStatus.FAIL, "Scanning Always Available is present ");
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.ERROR,"Error in locators-> verify_ScanningAlwaysAvailable_IsNotPresent()");
			e.printStackTrace();
		}catch (Exception e) {
			test.log(LogStatus.ERROR,"Exeption in -> verify_ScanningAlwaysAvailable_IsNotPresent()");
			e.printStackTrace();
		}
	}
	
	public void validate_Wifi_BT_Enabled_ByDefault(SoftAssert sa)
	{
		try{
			String str1 = Locators_Wifi.WifiScanningBtn.getText();
			String str2 = Locators_Wifi.BluetoothScanningBtn.getText();
			if(str1.equalsIgnoreCase("ON") && str2.equalsIgnoreCase("ON")){	
				APP_LOGS.info("By default Wifi and Bluetooth options are enabled");
				sa.assertTrue(true, "By default Wifi and Bluetooth options are enabled");
				test.log(LogStatus.PASS,"By default Wifi and Bluetooth options are enabled");
			}
			else {
				APP_LOGS.info("By default Wifi and Bluetooth options are not enabled");
				test.log(LogStatus.FAIL, "By default Wifi and Bluetooth options are not enabled");
				sa.fail();
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.ERROR,"Error in locators-> validate_Wifi_BT_Enabled_ByDefault()");
			e.printStackTrace();
		}catch (Exception e) {
			test.log(LogStatus.ERROR,"Exeption in -> validate_Wifi_BT_Enabled_ByDefault()");
			e.printStackTrace();
		}
	}
	
	public void verify_IP_MAC_NotEditable(SoftAssert sa)
	{
		try{
			scrollToElements(Locators_Wifi.MAC_address);
		 boolean mac = isElementExist(Locators_Wifi.MAC_address);
		 scrollToElements(Locators_Wifi.IP_address);
		 boolean ip = isElementExist(Locators_Wifi.IP_address);
			
			if(mac && ip){		
				APP_LOGS.info("IP And MAC Address are not Editable verified successfully");
				sa.assertTrue(true, "IP And MAC Address are not Editable verified successfully");
				test.log(LogStatus.PASS,"IP And MAC Address are not Editable verified successfully");	
			}
			else {	
				APP_LOGS.info("IP And MAC Address are  Editable");
				sa.fail();
				test.log(LogStatus.FAIL, "IP And MAC Address are Editable");
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.ERROR,"Error in locators->verify_IP_MAC_NotEditable()");
			e.printStackTrace();
		}catch (Exception e) {
			test.log(LogStatus.ERROR,"Exeption in ->verify_IP_MAC_NotEditable()");
			e.printStackTrace();
		}
	
	}
	
	public void validate_ToggleScanningBtn(SoftAssert sa)
	{
		try{
			
			String wifi = Locators_Wifi.WifiScanningBtn.getText();
			String BT = Locators_Wifi.BluetoothScanningBtn.getText();
			
			if(wifi.equalsIgnoreCase("ON")){
				
				clickBtn(Locators_Wifi.WifiScanningBtn);
				APP_LOGS.info("Wifi scanning button is disabled successfully");
				sa.assertTrue(true, "Wifi scanning button is disabled successfully");
				test.log(LogStatus.PASS,"Wifi scanning button is disabled successfully");	
			}
			else if(wifi.equalsIgnoreCase("OFF")) {
				clickBtn(Locators_Wifi.WifiScanningBtn);
				clickBtn(Locators_Wifi.Wifi_scngOKBtn);
				
				APP_LOGS.info("Wifi scanning button is enabled successfully");
				sa.assertTrue(true, "Wifi scanning button is enabled successfully");
				test.log(LogStatus.PASS,"Wifi scanning button is enabled successfully");	
			}
			else {	
				APP_LOGS.info("Failed to enable/disable wifi scanning button");
				sa.fail();
				test.log(LogStatus.FAIL, "Failed to enable/disable wifi scanning button");
			}
			if(BT.equalsIgnoreCase("ON")){
				clickBtn(Locators_Wifi.BluetoothScanningBtn);
				APP_LOGS.info("Bluetooth scanning button is disabled successfully");
				sa.assertTrue(true, "Bluetooth scanning button is disabled successfully");
				test.log(LogStatus.PASS,"Bluetooth scanning button is disabled successfully");	
				}
			else if(BT.equalsIgnoreCase("OFF")) {
				
				clickBtn(Locators_Wifi.BluetoothScanningBtn);				
				APP_LOGS.info("Bluetooth scanning button is enabled successfully");
				sa.assertTrue(true, "Bluetooth scanning button is enabled successfully");
				test.log(LogStatus.PASS,"Bluetooth scanning button is enabled successfully");	
			}
			else {	
				APP_LOGS.info("Failed to enable/disable Bluetooth scanning button");
				sa.fail();
				test.log(LogStatus.FAIL, "Failed to enable/disable Bluetooth scanning button");
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.ERROR,"Error in locators->validate_ToggleScanningBtn()");
			e.printStackTrace();
		}catch (Exception e) {
			test.log(LogStatus.ERROR,"Exeption in ->validate_ToggleScanningBtn()");
			e.printStackTrace();
		}
	}
	
	public void clickOn_Scanning(){
		try {
			if(isElementExist(Locators_Wifi.Scanning)) {
				clickBtn(Locators_Wifi.Scanning);
			}else {
				scrollToTextClick("Scanning");
			}	
		} catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.ERROR, "Error in the locators-> clickOn_Scanning()");
			e.printStackTrace();
		}catch (Exception e) {
			test.log(LogStatus.ERROR, "Exeption in -> clickOn_Scanning()");
			e.printStackTrace();
		}
	}
	
	public String enable_WiFiScanning() {
		String wifiScanningSwitchState =null;

		try {
			wifiScanningSwitchState = Locators_Wifi.WifiScanningBtn.getText();
		if(wifiScanningSwitchState.equalsIgnoreCase("Off")){		
			clickBtn(Locators_Wifi.WifiScanningBtn);
			if(isElementExist(Locators_Wifi.OK)){
				clickBtn(Locators_Wifi.OK);
			}
		}
		wifiScanningSwitchState = Locators_Wifi.WifiScanningBtn.getText();
	} catch (org.openqa.selenium.NoSuchElementException e) {
		test.log(LogStatus.ERROR, "Error in the locators-> enable_WiFiScanning()");
		e.printStackTrace();
	}catch (Exception e) {
		test.log(LogStatus.ERROR, "Exeption in -> enable_WiFiScanning()");
		e.printStackTrace();
	}
		return wifiScanningSwitchState;
	}
	public String disable_WiFiScanning() {
		String wifiScanningSwitchState =null;

		try {
			wifiScanningSwitchState = Locators_Wifi.WifiScanningBtn.getText();
		if(wifiScanningSwitchState.equalsIgnoreCase("On")){		
			clickBtn(Locators_Wifi.WifiScanningBtn);	
		}
		wifiScanningSwitchState = Locators_Wifi.WifiScanningBtn.getText();
	} catch (org.openqa.selenium.NoSuchElementException e) {
		test.log(LogStatus.ERROR, "Error in the locators-> disable_WiFiScanning()");
		e.printStackTrace();
	}catch (Exception e) {
		test.log(LogStatus.ERROR, "Exeption in -> disable_WiFiScanning()");
		e.printStackTrace();
	}
		return wifiScanningSwitchState;
	}
	public String enable_BTScanning() {
		String wifiScanningSwitchState =null;

		try {
			wifiScanningSwitchState = Locators_Wifi.BT_ScngBtn.getText();
		if(wifiScanningSwitchState.equalsIgnoreCase("Off")){		
			clickBtn(Locators_Wifi.BT_ScngBtn);	
		}
		wifiScanningSwitchState = Locators_Wifi.BT_ScngBtn.getText();
	} catch (org.openqa.selenium.NoSuchElementException e) {
		test.log(LogStatus.ERROR, "Error in the locators-> enable_BTScanning()");
		e.printStackTrace();
	}catch (Exception e) {
		test.log(LogStatus.ERROR, "Exeption in -> enable_BTScanning()");
		e.printStackTrace();
	}
		return wifiScanningSwitchState;
	}
	public String disable_BTScanning() {
		String wifiScanningSwitchState =null;

		try {
			wifiScanningSwitchState = Locators_Wifi.BT_ScngBtn.getText();
		if(wifiScanningSwitchState.equalsIgnoreCase("On")){		
			clickBtn(Locators_Wifi.BT_ScngBtn);	
		}
		wifiScanningSwitchState = Locators_Wifi.BT_ScngBtn.getText();
	} catch (org.openqa.selenium.NoSuchElementException e) {
		test.log(LogStatus.ERROR, "Error in the locators-> disable_BTScanning()");
		e.printStackTrace();
	}catch (Exception e) {
		test.log(LogStatus.ERROR, "Exeption in -> disable_BTScanning()");
		e.printStackTrace();
	}
		return wifiScanningSwitchState;
	}
	public String toggle_WifiScanning(){
		String wifiScanningSwitchState =null;
		try {
				wifiScanningSwitchState = Locators_Wifi.WifiScanningBtn.getText();
			if(wifiScanningSwitchState.equalsIgnoreCase("ON")){		
				clickBtn(Locators_Wifi.WifiScanningBtn);	
			}else{		
				clickBtn(Locators_Wifi.WifiScanningBtn);
				clickBtn(Locators_Wifi.Wifi_scngOKBtn);
			}	
			wifiScanningSwitchState = Locators_Wifi.WifiScanningBtn.getText();
		} catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.ERROR, "Error in the locators-> toggle_WifiScanning()");
			e.printStackTrace();
		}catch (Exception e) {
			test.log(LogStatus.ERROR, "Exeption in -> toggle_WifiScanning()");
			e.printStackTrace();
		}
		return wifiScanningSwitchState;
	}
	public void verify_WifiScanning_IsDisabled_InLocation(SoftAssert sa,String LocStngState)
	{
		try{
			
			String str1 = Locators_Wifi.WifiScanningBtn.getText();
			System.out.println("expected = "+LocStngState);
			System.out.println("actual = "+str1);
			
			if(str1.equalsIgnoreCase(LocStngState)){
				APP_LOGS.info("Wifi scanning is disabled in wifi scanning and its updated in location setting");
				sa.assertTrue(true, "Wifi scanning is disabled in wifi scanning and its updated in location setting");
				test.log(LogStatus.PASS,"Wifi scanning is disabled in wifi scanning and its updated in location setting");	
			}
			else {
				APP_LOGS.info("Failed -> Changes made in Wifi settings is not updated in Location setting");
				sa.fail();
				test.log(LogStatus.FAIL, "Failed -> Changes made in Wifi settings is not updated in Location setting");
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.ERROR,"Error in locators-> verify_WifiScanning_IsDisabled_InLocation()");
			e.printStackTrace();
		}catch (Exception e) {
			test.log(LogStatus.ERROR,"Exeption in -> verify_WifiScanning_IsDisabled_InLocation()");
			e.printStackTrace();
		}
	}
	public void verify_BTScanning_IsDisabled_InLocation(String LocStngState,SoftAssert sa)
	{
		try{
			String str1 = Locators_Wifi.BluetoothScanningBtn.getText();
			System.out.println("expected = "+LocStngState);
			System.out.println("actual = "+str1);
			
			if(str1.equalsIgnoreCase(LocStngState)){
				APP_LOGS.info("BT scanning disabled in BT setting and its updated in location setting");
				sa.assertTrue(true, "BT scanning disabled in BT setting and its updated in location setting");
				test.log(LogStatus.PASS,"BT scanning disabled in BT setting and its updated in location setting");	
			}
			else {
				APP_LOGS.info("Failed -> Changes made in BT setting is not updated in location setting");
				sa.fail();
				test.log(LogStatus.FAIL, "Failed -> Changes made in BT setting is not updated in location setting");
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.ERROR,"Error in locators-> verify_BTScanning_IsDisabled_InLocation()");
			e.printStackTrace();
		}catch (Exception e) {
			test.log(LogStatus.ERROR,"Exeption in -> verify_BTScanning_IsDisabled_InLocation()");
			e.printStackTrace();
		}
	}
	public void verify_BTScanning_IsEnabled_InSetting(String LocStngState,SoftAssert sa)
	{
		try{
			String str1 = Locators_Wifi.BluetoothScanningBtn.getText();
			System.out.println("expected = "+LocStngState);
			System.out.println("actual = "+str1);
			
			if(str1.equalsIgnoreCase(LocStngState)){
				APP_LOGS.info("BT scanning enabled in location setting and its updated in BT setting");
				sa.assertTrue(true, "BT scanning enabled in location setting and its updated in BT setting");
				test.log(LogStatus.PASS,"BT scanning enabled in location setting and its updated in BT setting");	
			}else {	
				APP_LOGS.info("Failed -> Changes made in location setting is not updated in BT setting");
				sa.fail();
				test.log(LogStatus.FAIL, "Failed -> Changes made in location setting is not updated in BT setting");
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.ERROR,"Error in locators-> verify_BTScanning_IsEnabled_InSetting()");
			e.printStackTrace();
		}catch (Exception e) {
			test.log(LogStatus.ERROR,"Exeption in -> verify_BTScanning_IsEnabled_InSetting()");
			e.printStackTrace();
		}
	}
	public void verify_WifiScanning_IsEnabled_Wifi(SoftAssert sa,String LocStngState)
	{
		try{
			
			String str1 = Locators_Wifi.WifiScanningBtn.getText();
			System.out.println("expected = "+LocStngState);
			System.out.println("actual = "+str1);
			
			if(str1.equalsIgnoreCase(LocStngState)){
				APP_LOGS.info("Wifi scanning is enabled in location setting and its updated in wifi setting");
				sa.assertTrue(true, "Wifi scanning is enabled in location setting and its updated in wifi setting");
				test.log(LogStatus.PASS,"Wifi scanning is enabled in location setting and its updated in wifi setting");	
			}
			else {
				APP_LOGS.info("Failed -> Changes made in Location setting is not updated in Wifi setting  ");
				sa.fail();
				test.log(LogStatus.FAIL, "Failed -> Changes made in Location setting is not updated in Wifi setting  ");
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.ERROR,"Error in locators-> verify_WifiScanning_IsEnabled_Wifi()");
			e.printStackTrace();
		}catch (Exception e) {
			test.log(LogStatus.ERROR,"Exeption in -> verify_WifiScanning_IsEnabled_Wifi()");
			e.printStackTrace();
		}
	}

	
	public boolean scrollToTextLongPress(String text) {
		/*
		Method used to select an element on the page by scrolling the Scroll View/List View
		*/
		
		boolean check = false;
		try {		
			TouchAction ta = new TouchAction(aDriver);
			String scrollable = "new UiScrollable(new UiSelector().scrollable(true))";
			String textElement = ".scrollIntoView(new UiSelector().text(\""+ text +"\"))";
			AndroidElement ele = aDriver.findElementByAndroidUIAutomator(scrollable+textElement);
			ta.longPress(ele).perform();
			APP_LOGS.info("Searched application is found sucessfully : ");
			check = true;
			return check;
	}
		catch(Exception e) {
			return check;
			}
		}
	
	public void tapOn_firstLink(){

		try {
			TouchAction ta = new TouchAction(aDriver);
		    ta.tap(207,1295).perform();
			
		}
		catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.ERROR, "Error in the locators->tapOn_ScanningSetting()");
			e.printStackTrace();

		}catch (Exception e) {

			test.log(LogStatus.ERROR, "Exeption in ->tapOn_ScanningSetting()");
		}
	}
	
	public void clickOn_HotspotLnk(){

		try {
				scrollToElements(Locators_Wifi.HotspotLnk);
			   if(isElementExist(Locators_Wifi.HotspotLnk)) {
				   clickBtn(Locators_Wifi.HotspotLnk);
			   }else {
				   scrollToTextContains("Hotspot");
			   }
		} catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.ERROR, "Error in the locators->clickOn_HotspotLnk()");
			e.printStackTrace();
		}catch (Exception e) {
			test.log(LogStatus.ERROR, "Exeption in ->clickOn_HotspotLnk()");
			e.printStackTrace();
		}
	}
	
	public void clickOn_setUpWiFiHotspot(){

		try {
			   scrollToElements(Locators_Wifi.Hotspot_setUpLnk);
			   if(isElementExist(Locators_Wifi.Hotspot_setUpLnk)) {
				   clickBtn(Locators_Wifi.Hotspot_setUpLnk);
			   }else {
				   scrollToTextContains("Set up");
			   }
		} catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.ERROR, "Error in the locators-> clickOn_setUpWiFiHotspot()");
			e.printStackTrace();
		}catch (Exception e) {
			test.log(LogStatus.ERROR, "Exeption in -> clickOn_setUpWiFiHotspot()");
			e.printStackTrace();
		}
	}
	
	public void setup_WiFiHotspot_Open(String name){
		try {
			System.out.println("wifi hotspot open = "+name);
			   wait(Locators_Wifi.Hotspot_ssidTxtBx,60);
			   enterTextToInputField(Locators_Wifi.Hotspot_ssidTxtBx, name);System.out.println("txt");
			   clickBtn(Locators_Wifi.Hotspot_securityDrpDwn);System.out.println("dropdown");
			   clickBtn(Locators_Wifi.Hotspot_security_Open);System.out.println("open or none");
			   if(isElementExist(Locators_Wifi.OK)) {
				   clickBtn(Locators_Wifi.OK);
			   }
			   clickBtn(Locators_Wifi.Hotspot_saveBtn);System.out.println("save");
			} catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.ERROR, "Error in the locators-> setup_WiFiHotspot_Open()");
			e.printStackTrace();
		}catch (Exception e) {
			test.log(LogStatus.ERROR, "Exeption in -> setup_WiFiHotspot_Open()");
			e.printStackTrace();
		}
	}
	
	public void validate_WiFiHotspot_setupOpen(String name,SoftAssert sa)
	{
		try{
			String str1 = Locators_Wifi.Hotspot_svdNtwrk.getText();			
			if(str1.equalsIgnoreCase(name+" Open hotspot") || str1.equalsIgnoreCase(name+" None hotspot")){
				APP_LOGS.info("Open network Wifi hotspot configured successfully");
				sa.assertTrue(true, "Open network Wifi hotspot configured successfully");
				test.log(LogStatus.PASS,"Open network Wifi hotspot configured successfully");	
			}
			else {
				APP_LOGS.info("Failed -> Open network wifi hotspots is not configured");
				sa.fail();
				test.log(LogStatus.FAIL, "Failed -> Open network wifi hotspots is not configured");
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.ERROR,"Error in locators-> validate_WiFiHotspot_setupOpen()");
			e.printStackTrace();
		}catch (Exception e) {
			test.log(LogStatus.ERROR,"Exeption in -> validate_WiFiHotspot_setupOpen()");
			e.printStackTrace();
		}
	}
	
	public void setup_WiFiHotspot_Secure(String name,String pass){

		try {
			wait(Locators_Wifi.Hotspot_ssidTxtBx,60);
			   enterTextToInputField(Locators_Wifi.Hotspot_ssidTxtBx, name);
			   clickBtn(Locators_Wifi.Hotspot_securityDrpDwn);
			   clickBtn(Locators_Wifi.Hotspot_security_WPA2);
			   enterTextToInputField(Locators_Wifi.Hotspot_PassTxtBx,pass);
			   clickBtn(Locators_Wifi.Hotspot_saveBtn);
		} catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.ERROR, "Error in the locators-> setup_WiFiHotspot_Secure()");
			e.printStackTrace();
		}catch (Exception e) {
			test.log(LogStatus.ERROR, "Exeption in -> setup_WiFiHotspot_Secure()");
			e.printStackTrace();
		}
	}
	
	public void validate_WiFiHotspot_setupSecure(String name,SoftAssert sa)
	{
		try{
			
			String str1 = Locators_Wifi.Hotspot_svdNtwrk.getText();			
			if(str1.equalsIgnoreCase(name+" WPA2 PSK hotspot")){
				APP_LOGS.info("Secured network WiFi hotspot configured successfully");
				sa.assertTrue(true, "Secured network WiFi hotspot configured successfully");
				test.log(LogStatus.PASS,"Secured network WiFi hotspot configured successfully");	
			}
			else {
				APP_LOGS.info("Failed -> Secured network wifi hotspot is not configured");
				sa.fail();
				test.log(LogStatus.FAIL, "Failed -> Secured network wifi hotspot is not configured");
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.ERROR,"Error in locators-> validate_WiFiHotspot_setupSecure()");
			e.printStackTrace();
		}catch (Exception e) {
			test.log(LogStatus.ERROR,"Exeption in -> validate_WiFiHotspot_setupSecure()");
			e.printStackTrace();
		}
	}
	
	public void clickOn_MobileNtwrk(){

		try {
			 scrollToElements(Locators_Wifi.MobileNtwrk_Lnk);
			 if(isElementExist(Locators_Wifi.MobileNtwrk_Lnk)) {
				 clickBtn(Locators_Wifi.MobileNtwrk_Lnk);
			 }
			 else {
				 scrollText("Mobile network");
			 }
		} catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.ERROR, "Error in the locators->clickOn_MobileNtwrk()");
			e.printStackTrace();
		}catch (Exception e) {
			test.log(LogStatus.ERROR, "Exeption in ->clickOn_MobileNtwrk()");
		}
	}
	public void turnOff_MobileNtwrk(){

		try {
			if(Locators_Wifi.MobileData_Lnk.getText().equalsIgnoreCase("On")) {
				 clickBtn(Locators_Wifi.MobileData_Lnk);
				 if(isElementExist(Locators_Wifi.OK)) {
					 clickBtn(Locators_Wifi.OK);
				 }
			}
			
		} catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.ERROR, "Error in the locators-> turnOff_MobileNtwrk()");
			e.printStackTrace();
		}catch (Exception e) {
			test.log(LogStatus.ERROR, "Exeption in -> turnOff_MobileNtwrk()");
			e.printStackTrace();
		}
	}
	public void turnOn_MobileNtwrk(){

		try {
			if(Locators_Wifi.MobileData_Lnk.getText().equalsIgnoreCase("Off")) {
				 clickBtn(Locators_Wifi.MobileData_Lnk);
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.ERROR, "Error in the locators->turnOn_MobileNtwrk()");
			e.printStackTrace();
		}catch (Exception e) {
			test.log(LogStatus.ERROR, "Exeption in ->turnOn_MobileNtwrk()");
			e.printStackTrace();
		}
	}
	public void verify_HideInternalStorage_IsNotPresent(SoftAssert sa){
		try {
			boolean hideInternalStorage = scrollToText("Hide internal storage");				
				if(hideInternalStorage == false){	
					APP_LOGS.info("Hide Internal storage is not present verified successfully");
					sa.assertTrue(true, "Hide Internal storage is not present verified successfully");
					test.log(LogStatus.PASS,"Hide Internal storage is not present verified successfully");
				}
				else {	
					APP_LOGS.info("Hide Internal storage is present");
					sa.fail();
					test.log(LogStatus.FAIL, "Hide Internal storage is present");
				}
			} catch (org.openqa.selenium.NoSuchElementException e) {
				test.log(LogStatus.ERROR,"Error in locators-> validate_HideInternalStorage_IsNotPresent()");
				e.printStackTrace();
			}catch (Exception e) {
				test.log(LogStatus.ERROR,"Exeption in -> validate_HideInternalStorage_IsNotPresent()");
				e.printStackTrace();
			}
		}
	
	public void clickon_ConnectedDevices(){

		try {
				scrollToElements(Locators_Wifi.Connected_devices);
				if(isElementExist(Locators_Wifi.Connected_devices)) {
					clickBtn(Locators_Wifi.Connected_devices);
				}else {
					scrollToTextClick("Connected devices");
				}
		} catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.ERROR, "Error in the locators->clickon_ConnectedDevices()");
			e.printStackTrace();
		}catch (Exception e) {
			test.log(LogStatus.ERROR, "Exeption in ->clickon_ConnectedDevices()");
			e.printStackTrace();
		}
	}
	
	public void clickon_Bluetooth(){

		try {
				scrollToElements(Locators_Wifi.Bluetooth);
				if(isElementExist(Locators_Wifi.Bluetooth))
				{
					clickBtn(Locators_Wifi.Bluetooth);
				}else {
					scrollToTextClick("Bluetooth");
				}
		} catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.ERROR, "Error in the locators->clickon_Bluetooth()");
			e.printStackTrace();
		}catch (Exception e) {
			test.log(LogStatus.ERROR, "Exeption in ->clickon_Bluetooth()");
			e.printStackTrace();
		}
	}
	public void verify_Peer_Displayed_InWifiDirect(SoftAssert sa)
	{
		try{	
			boolean Peerdevices=wait(Locators_Wifi.Peer_devices,60);
			if(Peerdevices==true){
				APP_LOGS.info("Peer devices option is displayed in wifi direct window");
				sa.assertTrue(true, "Peer devices option is displayed in wifi direct window");
				test.log(LogStatus.PASS,"Peer devices option is displayed in wifi direct window");	
			}
			else {
				APP_LOGS.info("Failed -> Peer devices option is not displayed in wifi direct window");
				sa.fail();
				test.log(LogStatus.FAIL, "Failed -> Peer devices option is not displayed in wifi direct window");
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.ERROR,"Error in locators-> verify_Peer_Displayed_InWifiDirect()");
			e.printStackTrace();
		}catch (Exception e) {
			test.log(LogStatus.ERROR,"Exeption in -> verify_Peer_Displayed_InWifiDirect()");
			e.printStackTrace();
		}
	}
	public void validate_BTScanning(SoftAssert sa)
	{
		try{	
			String BTScreen=Locators_Wifi.BT_ScngOpt.getText();
			if(BTScreen.contains("scanning settings")==false){
				APP_LOGS.info("Bluetooth scanning turned off Successfully");
				sa.assertTrue(true, "Bluetooth scanning turned off Successfully");
				test.log(LogStatus.PASS,"Bluetooth scanning turned off Successfully");	
			}
			else {
				APP_LOGS.info("Failed to turn off bluetooth scanning ");
				sa.fail();
				test.log(LogStatus.FAIL, "Failed to turn off bluetooth scanning ");
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.ERROR,"Error in locators->validate_BTScanning()");
			e.printStackTrace();
		}catch (Exception e) {
			test.log(LogStatus.ERROR,"Exeption in ->validate_BTScanning()");
			e.printStackTrace();
		}
	}
	
	public void turnOff_BTScanning(){

			try {
				String str1 = Locators_Wifi.BluetoothScanningBtn.getText();
					if(str1.equalsIgnoreCase("ON")){
					isElementExist(Locators_Wifi.BluetoothScanningBtn);	
					}
			}catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.ERROR, "Error in the locators->  turnOff_BTScanning()");
			e.printStackTrace();
		}catch (Exception e) {
			test.log(LogStatus.ERROR, "Exeption in ->  turnOff_BTScanning()");
			e.printStackTrace();
		}
	}
	
	public void verify_AutoConnect_IsPresent(String operator,SoftAssert sa)
	{
		try{
			if(operator.contains("-10")) {
			boolean  autoConnect= scrollToTextContains("Auto Connect");		
			if(autoConnect==true){
				APP_LOGS.info("Auto connect option is Present in Wifi Setting");
				sa.assertTrue(true, "Auto connect option is Present in Wifi Setting");
				test.log(LogStatus.PASS,"Auto connect option is Present in Wifi Setting");	
			}
			else {	
				APP_LOGS.info("Failed -> Auto connect option is not Present in Wifi Setting");
				sa.fail();
				test.log(LogStatus.FAIL, "Failed -> Auto connect option is not Present in Wifi Setting");
			}}
			else {
				APP_LOGS.info("Auto connect option only applicable for ATT");
				test.log(LogStatus.INFO,"Auto connect option only applicable for ATT");	
				test.log(LogStatus.SKIP,"Only applicable for ATT");	
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.ERROR,"Error in locators-> verify_AutoConnect_IsPresent()");
			e.printStackTrace();
		}catch (Exception e) {
			test.log(LogStatus.ERROR,"Exeption in -> verify_AutoConnect_IsPresent()");
			e.printStackTrace();
		}
	}
	
	public void validate_LongPressOn_ConnectedSSID(SoftAssert sa){

		try {
			boolean forgetNetwork = wait(Locators_Wifi.Forgetnetwork,30);
				if(forgetNetwork == true){
					APP_LOGS.info("Long pressed on connected ssid and forget network option is displayed");
					sa.assertTrue(true, "Long pressed on connected ssid and forget network option is displayed");
					test.log(LogStatus.PASS,"Long pressed on connected ssid and forget network option is displayed");					}
				else {	
					APP_LOGS.info("Failed to do long press on connected wifi network");
					sa.fail();
					test.log(LogStatus.FAIL, "Failed to do long press on connected wifi network");
				}
			} catch (org.openqa.selenium.NoSuchElementException e) {
				test.log(LogStatus.ERROR,"Error in locators-> validate_LongPressOn_ConnectedSSID()");
				e.printStackTrace();
			}catch (Exception e) {
				test.log(LogStatus.ERROR,"Exeption in -> validate_LongPressOn_ConnectedSSID()");
				e.printStackTrace();
			}
		}
	
	public void turnOn_AutoRotate(){

		try {
		    
			if(Locators_Wifi.autoRotateScreen.getText().equals("Auto-rotate screen")) {
				if(Locators_Wifi.aRSwitch.getText().equalsIgnoreCase("off"))
				clickBtn(Locators_Wifi.aRSwitch);
			}
			
		}
			 catch (org.openqa.selenium.NoSuchElementException e) {

		test.log(LogStatus.ERROR, "Error in the locators->turnOn_AutoRotate()");
		e.printStackTrace();

	}catch (Exception e) {

		test.log(LogStatus.ERROR, "Exeption in ->turnOn_AutoRotate()");
	}
}
	
	public void turnOff_AutoRotate(){

		try {
		    
			if(Locators_Wifi.autoRotateScreen.getText().equals("Auto-rotate screen")) {
				if(Locators_Wifi.aRSwitch.getText().equalsIgnoreCase("ON"))
				clickBtn(Locators_Wifi.aRSwitch);
			}
			
		}
			 catch (org.openqa.selenium.NoSuchElementException e) {

		test.log(LogStatus.ERROR, "Error in the locators->turnOn_AutoRotate()");
		e.printStackTrace();

	}catch (Exception e) {

		test.log(LogStatus.ERROR, "Exeption in ->turnOn_AutoRotate()");
	}
}
	
	public void clickOn_Battery(){

		try {
			scrollToElements(Locators_Wifi.Battery);
			if(isElementExist(Locators_Wifi.Battery)) {
				clickBtn(Locators_Wifi.Battery);
			}else {
				scrollToTextClick("Battery");
			}
		}catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.ERROR, "Error in the locators->clickOn_Battery()");
			e.printStackTrace();
		}catch (Exception e) {
			test.log(LogStatus.ERROR, "Exeption in ->clickOn_Battery()");
			e.printStackTrace();
	}
}
	
	public void validate_DeviceCharging(SoftAssert sa){

		try {
			customWait(3000);
			scrollToElements(Locators_Wifi.Charging);
			boolean charging = isElementExist(Locators_Wifi.Charging);
			boolean full = isElementExist(Locators_Wifi.Full) || scrollText("Full");
			if(charging | full){
					APP_LOGS.info("DUT is charging");
					sa.assertTrue(true, "DUT is charging");
					test.log(LogStatus.PASS,"DUT is charging");	
				}
				else {
					APP_LOGS.info("Failed -> Dut is not charging");
					sa.fail();
					test.log(LogStatus.FAIL, "Failed -> Dut is not charging");
				}
			} catch (org.openqa.selenium.NoSuchElementException e) {
				test.log(LogStatus.ERROR,"Error in locators-> validate_DeviceCharging()");
				e.printStackTrace();
			}catch (Exception e) {
				test.log(LogStatus.ERROR,"Exeption in -> validate_DeviceCharging()");
				e.printStackTrace();
			}
		}
	
	public void validate_ableToCntWifi(SoftAssert sa){
		try {
			boolean connected = wait(Locators_Wifi.Connected, 10000);
				if(connected == true){
					APP_LOGS.info("DUT is charging and wifi connected successfully");
					sa.assertTrue(true, "DUT is charging and wifi connected successfully");
					test.log(LogStatus.PASS,"DUT is charging and wifi connected successfully");
				}
				else {
					APP_LOGS.info("Failed -> Wifi is not connected while DUT is charging");
					sa.fail();
					test.log(LogStatus.FAIL, "Failed -> Wifi is not connected while DUT is charging");
				}
			} catch (org.openqa.selenium.NoSuchElementException e) {
				test.log(LogStatus.ERROR,"Error in locators->validate_ableToCntWifi()");
				e.printStackTrace();
			}catch (Exception e) {
				test.log(LogStatus.ERROR,"Exeption in ->validate_ableToCntWifi()");
				e.printStackTrace();
			}
		}
	public void verify_SecurityType_IsDisplayed(SoftAssert sa){

		try {
			if(wait(Locators_Wifi.Connected_SignalLevel,300)) {
				String wifiInfo=Locators_Wifi.Connected_SignalLevel.getAttribute("contentDescription");
				System.out.println("Wifi info => "+wifiInfo);
				if(wifiInfo.contains("Open") || wifiInfo.contains("Secure") ) {
					APP_LOGS.info("Wifi security type is displayed successfully");
					sa.assertTrue(true, "Wifi security type is displayed successfully");
					test.log(LogStatus.PASS,"Wifi security type is displayed successfully");	
				}
				else {
					APP_LOGS.info("Failed -> Wifi security type is not displayed");
					sa.fail();
					test.log(LogStatus.FAIL, "Failed -> Wifi security type is not displayed");
				}
			}
			} catch (org.openqa.selenium.NoSuchElementException e) {
				test.log(LogStatus.ERROR,"Error in locators-> verify_SecurityType_IsDisplayed()");
				e.printStackTrace();
			}catch (Exception e) {
				test.log(LogStatus.ERROR,"Exeption in -> verify_SecurityType_IsDisplayed()");
				e.printStackTrace();
			}
		}
	public void verify_SignalLevel_IsDisplayed(SoftAssert sa){

		try {
			if(wait(Locators_Wifi.Connected_SignalLevel,300)) {
				String wifiInfo=Locators_Wifi.Connected_SignalLevel.getAttribute("contentDescription");
				System.out.println("Wifi info => "+wifiInfo);
				if(wifiInfo.contains("bar") || wifiInfo.contains("full")) {
					APP_LOGS.info("Wifi Signal level displayed successfully ");
					sa.assertTrue(true, "Wifi Signal level displayed successfully ");
					test.log(LogStatus.PASS,"Wifi Signal level displayed successfully ");	
				}
				else {
					APP_LOGS.info("Failed -> Wifi signal level is not displayed");
					sa.fail();
					test.log(LogStatus.FAIL, "Failed -> Wifi signal level is not displayed");
				}
			}
			} catch (org.openqa.selenium.NoSuchElementException e) {
				test.log(LogStatus.ERROR,"Error in locators-> verify_WifiSignalLevel_IsDisplayed()");
				e.printStackTrace();
			}catch (Exception e) {
				test.log(LogStatus.ERROR,"Exeption in -> verify_WifiSignalLevel_IsDisplayed()");
				e.printStackTrace();
			}
		}

	public void verify_WifiSSID_IsDisplayed(String ssid,SoftAssert sa){

		try {
			if(wait(Locators_Wifi.Connected_SignalLevel,300)) {
				String wifiInfo=Locators_Wifi.Connected_SignalLevel.getAttribute("contentDescription");
				System.out.println("Wifi info => "+wifiInfo);
				if(wifiInfo.contains(ssid) ) {
					APP_LOGS.info("Wifi SSID is displayed successfully");
					sa.assertTrue(true, "Wifi SSID is displayed successfully");
					test.log(LogStatus.PASS,"Wifi SSID is displayed successfully");	
				}
				else {
					APP_LOGS.info("Failed -> Wifi SSID is not displayed");
					sa.fail();
					test.log(LogStatus.FAIL, "Failed -> Wifi SSIDis not displayed");
				}
			}
			} catch (org.openqa.selenium.NoSuchElementException e) {
				test.log(LogStatus.ERROR,"Error in locators-> verify_WifiSSID_IsDisplayed()");
				e.printStackTrace();
			}catch (Exception e) {
				test.log(LogStatus.ERROR,"Exeption in -> verify_WifiSSID_IsDisplayed()");
				e.printStackTrace();
			}
		}
	public void verify_WifiSignalLevel_IsDisplayed(SoftAssert sa){

		try {
			if(isElementExist(Locators_Wifi.Wifi_img)) {
				String wifiInfo=Locators_Wifi.Wifi_img.getAttribute("contentDescription");
				System.out.println("Wifi info => "+wifiInfo);
				if(wifiInfo.contains("bar") || wifiInfo.contains("full")) {
					APP_LOGS.info("Wifi Signal level displayed successfully ");
					sa.assertTrue(true, "Wifi Signal level displayed successfully ");
					test.log(LogStatus.PASS,"Wifi Signal level displayed successfully ");	
				}
				else {
					APP_LOGS.info("Failed -> Wifi signal level is not displayed");
					sa.fail();
					test.log(LogStatus.FAIL, "Failed -> Wifi signal level is not displayed");
				}
			}
			} catch (org.openqa.selenium.NoSuchElementException e) {
				test.log(LogStatus.ERROR,"Error in locators-> verify_WifiSignalLevel_IsDisplayed()");
				e.printStackTrace();
			}catch (Exception e) {
				test.log(LogStatus.ERROR,"Exeption in -> verify_WifiSignalLevel_IsDisplayed()");
				e.printStackTrace();
			}
		}

	public void verify_PasswordScreen_IsDisplayed(SoftAssert sa){

		try {
			boolean pwdScreen = wait(Locators_Wifi.setdata,60);
				if(pwdScreen){
					APP_LOGS.info("Tapped on Wifi SSID and Password screen displayed successfully ");
					sa.assertTrue(true, "Tapped on Wifi SSID and Password screen displayed successfully" );
					test.log(LogStatus.PASS,"Tapped on Wifi SSID and Password screen displayed successfully ");	
				}
				else {
					APP_LOGS.info("Failed -> Password screen is not displayed");
					sa.fail();
					test.log(LogStatus.FAIL, "Failed -> Password screen is not displayed");
				}
			} catch (org.openqa.selenium.NoSuchElementException e) {
				test.log(LogStatus.ERROR,"Error in locators-> verify_PasswordScreen_IsDisplayed()");
				e.printStackTrace();
			}catch (Exception e) {
				test.log(LogStatus.ERROR,"Exeption in -> verify_PasswordScreen_IsDisplayed()");
				e.printStackTrace();
			}
		}
	public void verify_AdvancedOption_IsDisplayed(SoftAssert sa){

		try {
			boolean advancedOption = isElementExist(Locators_Wifi.Wifi_AdvOpt);
				if(advancedOption){
					APP_LOGS.info("Advanced option is displayed in password screen");
					sa.assertTrue(true, "Advanced option is displayed in password screen");
					test.log(LogStatus.PASS,"Advanced option is displayed in password screen ");	
				}
				else {
					APP_LOGS.info("Failed -> Advanced option is not displayed in password screen");
					sa.fail();
					test.log(LogStatus.FAIL, "Failed -> Advanced option is not displayed in password screen");
				}
			} catch (org.openqa.selenium.NoSuchElementException e) {
				test.log(LogStatus.ERROR,"Error in locators-> verify_AdvancedOption_IsDisplayed()");
				e.printStackTrace();
			}catch (Exception e) {
				test.log(LogStatus.ERROR,"Exeption in -> verify_AdvancedOption_IsDisplayed()");
				e.printStackTrace();
			}
		}
	public void clickOn_ConnectToNtwrk() {

		try {
			scrollToTextClick("Connect to network");
		} catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.ERROR, "Error in the locators-> clickOn_ConnectToNtwrk()");
			e.printStackTrace();
		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exeption in -> clickOn_ConnectToNtwrk()");
			e.printStackTrace();
		}
	}
	
	public void verify_Connected_IsDisplayed(SoftAssert sa){

		try {
				if(wait(Locators_Wifi.Wifi_img,120)){
				String wifiInfo=Locators_Wifi.Wifi_img.getAttribute("contentDescription");
				if(wifiInfo.contains("bar") || wifiInfo.contains("full")){
					APP_LOGS.info("Connected notification is displayed successfully");
					sa.assertTrue(true, "Connected notification is displayed successfully ");
					test.log(LogStatus.PASS,"Connected notification is displayed successfully ");	
				}}
				else {
					APP_LOGS.info("Failed -> Connected notification is not displayed");
					sa.fail();
					test.log(LogStatus.FAIL, "Failed -> Connected notification is not displayed");
				}
			} catch (org.openqa.selenium.NoSuchElementException e) {
				test.log(LogStatus.ERROR,"Error in locators-> verify_Connected_IsDisplayed()");
				e.printStackTrace();
			}catch (Exception e) {
				test.log(LogStatus.ERROR,"Exeption in -> verify_Connected_IsDisplayed()");
				e.printStackTrace();
			}
		}
	
	
	public void verify_Wifi_IsNotConnected(SoftAssert sa){

		try {
			if(wait(Locators_Wifi.Wifi_img, 120)) {
			String wifiInfo=Locators_Wifi.Wifi_img.getAttribute("contentDescription");
			if(wifiInfo.contains("bar")==false || wifiInfo.contains("full")==false){
					APP_LOGS.info("Clicked on Forget button and Connected notification is not displayed ");
					sa.assertTrue(true, "Clicked on Forget button and Connected notification is not displayed ");
					test.log(LogStatus.PASS,"Clicked on Forget button and Connected notification is not displayed");	
				}}
				else {
					APP_LOGS.info("Failed -> Connected notification is displayed");
					sa.fail();
					test.log(LogStatus.FAIL, "Failed -> Connected notification is displayed");
				}
			} catch (org.openqa.selenium.NoSuchElementException e) {
				test.log(LogStatus.ERROR,"Error in locators-> verify_Wifi_IsNotConnected()");
				e.printStackTrace();
			}catch (Exception e) {
				test.log(LogStatus.ERROR,"Exeption in -> verify_Wifi_IsNotConnected()");
				e.printStackTrace();
			}
		}
	
	public void clickOn_DataUsage() {

		try {
			scrollToElements(Locators_Wifi.Data_usage);
			if(isElementExist(Locators_Wifi.Data_usage)) {
				clickBtn(Locators_Wifi.Data_usage);
			}else {
				scrollToTextClick("Data usage");
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.ERROR, "Error in the locators->clickOn_DataUsage()");
			e.printStackTrace();
		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exeption in ->clickOn_DataUsage()");
			e.printStackTrace();
		}
	}
	public boolean disable_AirplaneMode() {
		/*
		 * disable airplane mode
		 */
		boolean disabled = false;
		try {
			scrollTo("Mobile network");
			if (Locators_Wifi.MobileNtwrk_Lnk.isEnabled()==false) {
				scrollTo("Airplane mode");
				clickBtn(Locators_Wifi.Airplanemode);
			}
			disabled = true;
		} catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.ERROR, "Error in the locators-> disable_AirplaneMode() ");
			e.printStackTrace();
		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exception in -> disable_AirplaneMode()");
		}
		return disabled;
	}

	public void drag_Drop(AndroidElement source, AndroidElement target) {
		TouchAction action = new TouchAction(aDriver);
		action.longPress(source, 9000).moveTo(target).release().perform();	
	}
	public void clickOn_BackBtn() {
		try {
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			minWait();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public String extract_Numerics(String stringValue) {
		String numericValue = "";
		String arr[]=stringValue.split("used");
		stringValue=arr[0];
		System.out.println("Input string = "+stringValue);
		/*if(stringValue.contains(".")) {
		    System.out.println(stringValue.indexOf("."));
		    stringValue=stringValue.substring(0,stringValue.indexOf("."));
		System.out.println("New string = "+stringValue);
		}*/
		for (int i = 0; i < stringValue.length(); i++) {
			if (stringValue.charAt(i) >= '0' && stringValue.charAt(i) <= '9' || stringValue.charAt(i)=='.')
				numericValue = numericValue + stringValue.charAt(i);
		}
		System.out.println("Output string = "+numericValue);
		return numericValue;
	}

	public int get_MobileDataUsage() {
		 int dataUsage =0;
		try {
		String str = Locators_Wifi.dataUsageDetails.getText();
		System.out.println("Mobile data used = "+str);
		String num = "";
		char[] arr = str.toCharArray();
		for (int i = 0; i < arr.length; i++) {
			if(arr[i]>='0' && arr[i]<='9')
			{
				num = num+arr[i];
			}
		}
		 dataUsage = Integer.parseInt(num);
		} catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.ERROR, "Error in the locators-> get_MobileDataUsage()");
			e.printStackTrace();
		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exeption in -> get_MobileDataUsage()");
			e.printStackTrace();
		}
		System.out.println("Mobile data usage details = "+dataUsage);
		 return dataUsage;
	}
	
	
	public void Validate_deviceBehaviour(SoftAssert sa){

		try {
			if(Locators_Wifi.Wifi_SwitchBar.isDisplayed()){	
					APP_LOGS.info("Setting is not crashed , Device is working normally");
					sa.assertTrue(true, "Setting is not crashed , Device is working normally ");
					test.log(LogStatus.PASS,"Setting is not crashed , Device is working normally");	
				}else {
					APP_LOGS.info("Failed -> Device is not working normally");
					sa.fail();
					test.log(LogStatus.FAIL, "Failed -> Device is not working normally");
				}
			} catch (org.openqa.selenium.NoSuchElementException e) {
				test.log(LogStatus.ERROR,"Error in locators->Validate_deviceBehaviour()");
				e.printStackTrace();
			}catch (Exception e) {
				test.log(LogStatus.ERROR,"Exeption in ->Validate_deviceBehaviour()");
				e.printStackTrace();
			}
		}
	
	public void ResetWifi_Mob_BT_stng() {

		try {
			minWait();
			TouchAction ta = new TouchAction(aDriver);
		    ta.tap(1013,152).perform();
		    minWait();
			Locators_Wifi.Wifi_resetOpt.click();
			Locators_Wifi.Wifi_resetStngBtn.click();
			Locators_Wifi.Wifi_resetExecuteBtn.click();
			
			waituntilnew(Locators_Wifi.Wifi_ResetLogoTxt, 5);

		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->ResetWifi_Mob_BT_stng()");
			e.printStackTrace();

		} catch (Exception e) {

			test.log(LogStatus.ERROR, "Exeption in ->ResetWifi_Mob_BT_stng()");
		}
	}
	
	public void turnOff_Bluetooth() {

		try {
			
			if(Locators_Wifi.Wifi_Switch_Btn.getText().equalsIgnoreCase("on"))
			{
			  clickBtn(Locators_Wifi.Wifi_Switch_Btn);
			   customWait(3000);
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.ERROR, "Error in the locators->ResetWifi_Mob_BT_stng()");
			e.printStackTrace();
		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exeption in ->ResetWifi_Mob_BT_stng()");
			e.printStackTrace();
		}
	}
	
	public void check_ScanningSetting(SoftAssert sa) throws Exception {

		
			if(isElementExist(Locators_Wifi.scanningsetting)==false) {
				test.log(LogStatus.FAIL, "After performing Resettings,Scanning setting is not present in Bluetooth window");
				sa.fail();
				APP_LOGS.info("After performing Resettings,Scanning setting is not present in Bluetooth window");
				throw new Exception("Bug with network resettings");
				}
	
	}
	public void tapOn_BTScanningSetting() {

		try {
			minWait();
			TouchAction ta = new TouchAction(aDriver);
		    ta.tap(467,1329).perform();
		} catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.ERROR, "Error in the locators->  tapOn_BTScanningSetting()");
			e.printStackTrace();
		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exeption in -> tapOn_BTScanningSetting()");
			e.printStackTrace();
		}
	}
	

	public void make_Call_from_RefDev() throws InterruptedException, IOException {
		//make a call from ref device to primary device
		try {
			Runtime.getRuntime()
			.exec("adb -s " + r_Id + " shell am start -a android.intent.action.CALL -d tel:" + pryNum);
			minWait();
			} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exeption in ->make_Call_from_RefDev()");

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
		
		public void reciveCallInPriDevice() throws InterruptedException, IOException {
			/* Receive Call in Reference device
			*/
		/*
			String cmd="adb -s "+id+" shell \"dumpsys telephony.registry | grep mCallState\"";
			InputStream p = Runtime.getRuntime().exec(cmd).getInputStream();
			InputStreamReader isr = new InputStreamReader(p);
			BufferedReader br = new BufferedReader(isr);
			String value = br.readLine();
			System.out.println(value);
			*/
			if (!isElementExist(Locators_XP8_Sanity.turnOff_Airplane_PopUp)) {
			try {
				while(true){
					Process child = null;
					if (p_b_No.contains("8A.")) {
						child=Runtime.getRuntime().exec("adb -s "+p_Id+" shell service call telecom 29");
					} else if(r_b_No.contains("5SA.")) {
						child=Runtime.getRuntime().exec("adb -s "+p_Id+" shell service call telecom 28");
					}
					InputStream inputStream = child.getInputStream();
					InputStreamReader isr = new InputStreamReader(inputStream);
					BufferedReader bf = new BufferedReader(isr);
					String value=bf.readLine();
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

			}
			}
			}
			
		public void verify_CallReceived(SoftAssert sa) {
			try {
				if(wait(Locators_Wifi.Endcall,100000)) {		
					System.out.println("Call received");
						APP_LOGS.info("Call received successfully");
						sa.assertTrue(true, "Call received successfully");
						test.log(LogStatus.PASS,"Call received successfully");
					}
					else {		
						APP_LOGS.info("Failed -> Could not receive call");
						sa.fail();
						test.log(LogStatus.FAIL, "Failed -> Could not receive call");
					}
				
	//		System.out.println("Call status active 2 = ");
	//		System.out.print(Runtime.getRuntime().exec("adb shell -s "+p_Id+" dumpsys telephony.registry | grep mCallState"));
			} catch (org.openqa.selenium.NoSuchElementException e) {
				test.log(LogStatus.ERROR,"Error in locators-> verify_CallReceived()");
				e.printStackTrace();
			}catch (Exception e) {
				test.log(LogStatus.ERROR,"Exeption in -> verify_CallReceived()");
				e.printStackTrace();
			}
		}
		
		public void connect_to_WiFi_Landscape(String name,String pwsd)
		{
			try{
				scrollToTextClick(name);
				minWait();
				enterTextToInputField(Locators_Wifi.Wifi_PasswordTxt, pwsd);
				aDriver.pressKeyCode(AndroidKeyCode.ENTER);
				waituntilnew(Locators_Wifi.Wifi_PageLogo, 20);
			} catch (org.openqa.selenium.NoSuchElementException e) {
				test.log(LogStatus.ERROR,"Error in locators-> connect_to_WiFi_Landscape()");
				e.printStackTrace();
			}catch (Exception e) {
				test.log(LogStatus.ERROR,"Exeption in -> connect_to_WiFi_Landscape()");
				e.printStackTrace();
			}
		}
		
	
		public void Swipe_the_Call() {
			try {
				Runtime r = Runtime.getRuntime();
				r.exec("adb shell input swipe 40 420 40 0");
				minWait();
			}catch (Exception e) {
				test.log(LogStatus.ERROR, "Swipe_the_Call");
			}
			
		}
		
		public void moveCall_ToBackground() {
			try {
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			}catch (Exception e) {
				test.log(LogStatus.ERROR, "moveCall_ToBackground");
			}
			
		}
		
		public void start_browsing() {
			try {
				aDriver.pressKeyCode(4);
				/*TouchAction ta = new TouchAction(aDriver);
				if(wait(Locators_Wifi.CLEAR,10)) {
					ta.tap(Locators_Wifi.CLEAR).perform();
					if(wait(Locators_Wifi.CLEAR,10)) 
						ta.tap(Locators_Wifi.CLEAR).perform();
					System.out.println("CLEAR");
				}
				if(wait(Locators_Wifi.clearreset, 10)) {
					ta.tap(Locators_Wifi.clearreset).perform();
					if(wait(Locators_Wifi.clearreset, 10)) 
						ta.tap(Locators_Wifi.clearreset).perform();
					System.out.println("Clear");
				}*/
				if(wait((AndroidElement) Locators_Wifi.googleSearchBx,120)) {
					Locators_Wifi.googleSearchBx.sendKeys("Appium");
					aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_ENTER);
					System.out.println("searched appium");
				}	
			/*	if(wait(Locators_Wifi.CLEAR,10)) {
					ta.tap(Locators_Wifi.CLEAR).perform();
					if(wait(Locators_Wifi.CLEAR,10)) 
						ta.tap(Locators_Wifi.CLEAR).perform();
					System.out.println("CLEAR last");
				}
				if(wait(Locators_Wifi.clearreset, 10)) {
					ta.tap(Locators_Wifi.clearreset).perform();
					if(wait(Locators_Wifi.clearreset, 10)) 
						ta.tap(Locators_Wifi.clearreset).perform();
					System.out.println("Clear last");
				}*/
			} catch (org.openqa.selenium.NoSuchElementException e) {
				test.log(LogStatus.ERROR,"Error in locators->start_browsing()");
				e.printStackTrace();
			}catch (Exception e) {
				test.log(LogStatus.ERROR, "Exception in functionality->start_browsing()");
				e.printStackTrace();
			}
			
		}
		
		
		public void verify_BrowsingIsContinuedAfterReceivingCall(SoftAssert sa)
		{
			try{
				boolean check1 = wait(Locators_Wifi.google_verifyLnk,10);
				boolean check2 =  wait(Locators_Wifi.google_navigateIdLnk,10);
				boolean appium =  wait(Locators_Wifi.Appium,10);
				if(check1==true || check2==true || appium==true){		
					APP_LOGS.info("Browsing is continued using wifi when call is in active");
					sa.assertTrue(true, "Browsing is continued using wifi when call is in active");
					test.log(LogStatus.PASS,"Browsing is continued using wifi when call is in active");
				}
				else {		
					APP_LOGS.info("Failed -> Browsing is not continued using wifi when call is in Active");
					sa.fail();
					test.log(LogStatus.FAIL, "Failed -> Browsing is not continued using wifi when call is in Active");
				}
			} catch (org.openqa.selenium.NoSuchElementException e) {
				test.log(LogStatus.ERROR,"Error in locators-> verify_BrowsingIsContinuedAfterReceivingCall()");
				e.printStackTrace();
			}catch (Exception e) {
				test.log(LogStatus.ERROR,"Exeption in -> verify_BrowsingIsContinuedAfterReceivingCall()");
				e.printStackTrace();
			}
		}
		
			public void validate_browsingInMobileData(SoftAssert sa,double MdataBefore,double MdataAfter )
		{
			try{
				 	double dataBefore = MdataBefore;
				 	double dataAfter = MdataAfter;
				if(dataAfter > dataBefore){
					APP_LOGS.info("Successfully we are able to Browse using Mobile Data when Wifi is Disabled");
					sa.assertTrue(true, "Successfully we are able to Browse using Mobile Data when Wifi is Disabled");
					test.log(LogStatus.PASS,"Successfully we are able to Browse using Mobile Data when Wifi is Disabled");	
				}
				else {	
					APP_LOGS.info("Failed to Browse using Mobile Data");
					sa.fail();
					test.log(LogStatus.FAIL, "Failed to Browse using Mobile Data");
				}
			} catch (org.openqa.selenium.NoSuchElementException e) {
				test.log(LogStatus.ERROR,"Error in locators->validate_browsingInMobileData()");
				e.printStackTrace();
			}catch (Exception e) {
				test.log(LogStatus.ERROR,"Exeption in ->validate_browsingInMobileData()");
				e.printStackTrace();
			}
		}
		
			public void verify_WifiIsUsedToBrowse_MobileDataEnabled(SoftAssert sa,String MdataBefore,String MdataAfter )
			{
				try{
					System.out.println("Old wifi data = "+MdataBefore);
					System.out.println("New wifi data = "+MdataAfter);
					double oldV=Double.parseDouble(MdataBefore);
					double newV=Double.parseDouble(MdataAfter);
					if(newV>oldV==true){
						APP_LOGS.info("Wifi data is used for browsing when Mobile data is in enable mode");
						sa.assertTrue(true, "Wifi data is used for browsing when Mobile data is in enable mode");
						test.log(LogStatus.PASS,"Wifi data is used for browsing when Mobile data is in enable mode");	
					}
					else {	
						APP_LOGS.info("Failed -> Wifi data is not used for browsing when mobile data is in enable mode");
						sa.fail();
						test.log(LogStatus.FAIL, "Failed -> Wifi data is not used for browsing when mobile data is in enable mode");
					}
				} catch (org.openqa.selenium.NoSuchElementException e) {
					test.log(LogStatus.ERROR,"Error in locators-> verify_WifiIsUsedToBrowse_MobileDataEnabled()");
					e.printStackTrace();
				}catch (Exception e) {
					test.log(LogStatus.ERROR,"Exeption in -> verify_WifiIsUsedToBrowse_MobileDataEnabled()");
					e.printStackTrace();
				}
			}
			public void verify_WifiIsUsedToBrowse_MobileDataDisabled(SoftAssert sa,String MdataBefore,String MdataAfter )
			{
				try{
					System.out.println("Old wifi data = "+MdataBefore);
					System.out.println("New wifi data = "+MdataAfter);
					double oldV=Double.parseDouble(MdataBefore);
					double newV=Double.parseDouble(MdataAfter);
					if(newV>oldV==true){
						APP_LOGS.info("Wifi data is used for browsing when Mobile data is in disable mode");
						sa.assertTrue(true, "Wifi data is used for browsing when Mobile data is in disable mode");
						test.log(LogStatus.PASS,"Wifi data is used for browsing when Mobile data is in disable mode");	
					}
					else {	
						APP_LOGS.info("Failed -> Wifi data is not used for browsing");
						sa.fail();
						test.log(LogStatus.FAIL, "Failed -> Wifi data is not used for browsing");
					}
				} catch (org.openqa.selenium.NoSuchElementException e) {
					test.log(LogStatus.ERROR,"Error in locators-> verify_WifiIsUsedToBrowse_MobileDataDisabled()");
					e.printStackTrace();
				}catch (Exception e) {
					test.log(LogStatus.ERROR,"Exeption in -> verify_WifiIsUsedToBrowse_MobileDataDisabled()");
					e.printStackTrace();
				}
			}
			public void makeCall(String contactNo) {
				try {
					if(isElementExist(Locators_Wifi.keypad)) {
						clickBtn(Locators_Wifi.keypad);
						minWait();
					}if(isElementExist(Locators_Wifi.enterNumber)) {
						enterTextToInputField(Locators_Wifi.enterNumber,contactNo);
						minWait();
					}if(isElementExist(Locators_Wifi.call)) {
						clickBtn(Locators_Wifi.call);
					}
				}catch (NoSuchElementException e) {
					test.log(LogStatus.ERROR,"Error in the locators => makeCall()");
					e.printStackTrace();
				}catch (Exception e) {
					test.log(LogStatus.ERROR,"Exception in functionality=> makeCall()");
					e.printStackTrace();
				}
			}
		public void validate_browingInWifiData(SoftAssert sa,double MdataBefore,double MdataAfter)
		{
			try{
				double dataBefore = MdataBefore;
			 	double dataAfter = MdataAfter;
			if(dataAfter == dataBefore){
				
				APP_LOGS.info("After Enabling Wifi Successfully we are able to Browse using Wifi");
				sa.assertTrue(true, "After Enabling Wifi Successfully we are able to Browse using Wifi");
				test.log(LogStatus.PASS,"After Enabling Wifi Successfully we are able to Browse using Wifi");	
			}
			else {
				
				APP_LOGS.info("Failed to Browse using Wifi");
				sa.fail();
				test.log(LogStatus.FAIL, "Failed to Browse using Wifi");

			}

		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR,"Error in locators->validate_browingInWifiData()");
			
		}catch (Exception e) {
			test.log(LogStatus.ERROR,"Exeption in ->validate_browingInWifiData()");
		}
	}
		
		public double UsedMobileData() {
			try {
			Locators_Wifi.dataUsageDetails.click();
			customWait(2000);
			String str = Locators_Wifi.dataUsageSummary.getText();
			String num = "";
			char[] arr = str.toCharArray();
			for (int i = 0; i < arr.length; i++) {	
				if(arr[i]>='0' && arr[i]<='9' || arr[i]=='.')
				{
					num = num+arr[i];
				}
			}
			double dataUsage = Double.parseDouble(num);
			return dataUsage;	
			} catch (org.openqa.selenium.NoSuchElementException e) {
				test.log(LogStatus.ERROR,"Error in locators-> UsedMobileData()");
				e.printStackTrace();
			}catch (Exception e) {
				test.log(LogStatus.ERROR, "Exception in functionality -> UsedMobileData()");
				e.printStackTrace();
			}
			return 0;
			
		}
		
		public void launchSettings_Activity() {
			try {
				aDriver.startActivity("com.android.settings", "com.android.settings.Settings$NetworkDashboardActivity");
					
			}catch (Exception e) {
				test.log(LogStatus.ERROR, "Exception in functionality -> Open_dataUsage()");
				e.printStackTrace();
			}
			
		}
		public void verify_WifiIsNotDisabled_WhileDiallingEmergency(SoftAssert sa)
		{
			try{
				scrollToElements(Locators_Wifi.Connected);
				 boolean Connected=isElementExist(Locators_Wifi.Connected);
			if(Connected == true){
				APP_LOGS.info("Emergency number dialled and wifi is not disconnected");
				sa.assertTrue(true, "Emergency number dialled and wifi is not disconnected");
				test.log(LogStatus.PASS,"Emergency number dialled and wifi is not disconnected");	
			}
			else {	
				APP_LOGS.info("Failed -> Wifi is not connected when emergency no is dialled");
				sa.fail();
				test.log(LogStatus.FAIL, "Failed -> Wifi is not connected when emergency no is dialled");
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.ERROR,"Error in locators-> verify_WifiIsNotDisabled_WhileDiallingEmergency()");
			e.printStackTrace();
		}catch (Exception e) {
			test.log(LogStatus.ERROR,"Exeption in -> verify_WifiIsNotDisabled_WhileDiallingEmergency()");
			e.printStackTrace();
		}
	}
		public void verify_Wifi_IsConnected(SoftAssert sa)
		{
			try{
				scrollToElements(Locators_Wifi.Connected);
				 boolean Connected=isElementExist(Locators_Wifi.Connected);
			if(Connected == true){
				APP_LOGS.info("Wifi is connected and Connected notification is displayed");
				sa.assertTrue(true, "Wifi is connected and Connected notification is displayed");
				test.log(LogStatus.PASS,"Wifi is connected and Connected notification is displayed");	
			}
			else {	
				APP_LOGS.info("Failed -> Connected notification is not displayed");
				sa.fail();
				test.log(LogStatus.FAIL, "Failed -> Connected notification is not displayed");
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.ERROR,"Error in locators-> verify_Wifi_IsConnected()");
			e.printStackTrace();
		}catch (Exception e) {
			test.log(LogStatus.ERROR,"Exeption in -> verify_Wifi_IsConnected()");
			e.printStackTrace();
		}
	}
		
		public void verify_Wifi_IsDisconnected(SoftAssert sa)
		{
			try{
				 boolean Connected = isElementExist(Locators_Wifi.Connected);
			if(Connected== false){
				APP_LOGS.info("Wifi is disconnected and Connected notification is not displayed");
				sa.assertTrue(true, "Wifi is disconnected and Connected notification is not displayed");
				test.log(LogStatus.PASS,"Wifi is disconnected and Connected notification is not displayed");
			}
			else {
				APP_LOGS.info("Failed-> Connected notification is displayed");
				sa.fail();
				test.log(LogStatus.FAIL, "Failed-> Connected notification is displayed");
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.ERROR,"Error in locators-> verify_Wifi_IsDisconnected()");
			e.printStackTrace();
		}catch (Exception e) {
			test.log(LogStatus.ERROR,"Exeption in -> verify_Wifi_IsDisconnected()");
			e.printStackTrace();
		}
	}
		
		public void verify_Addnetwork_IsPresent_InWifiSetting(SoftAssert sa)
		{
			try{
				
				 boolean Addnetwork= false;
				 scrollToElements(Locators_Wifi.Add_network);
				 Addnetwork=isElementExist(Locators_Wifi.Add_network);
			if(Addnetwork == true){
				APP_LOGS.info("Add network option is present in wifi setting");
				sa.assertTrue(true, "Add network option is present in wifi setting");
				test.log(LogStatus.PASS,"Add network option is present in wifi setting");	
			}
			else {
				
				APP_LOGS.info("Failed-> Add Network option is not present in wifi setting");
				sa.fail();
				test.log(LogStatus.FAIL, "Failed-> Add Network option is not present in wifi setting");

			}

		} catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.ERROR,"Error in locators-> verify_Addnetwork_IsPresent_InWifiSetting()");
			e.printStackTrace();
		}catch (Exception e) {
			test.log(LogStatus.ERROR,"Exeption in -> verify_Addnetwork_IsPresent_InWifiSetting()");
			e.printStackTrace();
		}
	}
		
		
		public void verify_DeviceIsRenamed(SoftAssert sa,String Rename)
		{
			try{
				 boolean check = scrollToText(Rename);
			if(check == true){
				APP_LOGS.info("Device is renamed successfully in wifi direct");
				sa.assertTrue(true, "Device is renamed successfully in wifi direct");
				test.log(LogStatus.PASS,"Device is renamed successfully in wifi direct");	
			}
			else {
				APP_LOGS.info("Failed -> Device is not renamed in wifi direct");
				sa.fail();
				test.log(LogStatus.FAIL, "Failed -> Device is not renamed in wifi direct");
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.ERROR,"Error in locators-> verify_DeviceIsRenamed()");
			e.printStackTrace();
		}catch (Exception e) {
			test.log(LogStatus.ERROR,"Exeption in -> verify_DeviceIsRenamed()");
			e.printStackTrace();
		}
	}
		
		public void verify_CancelButtonFunctionality(String deviceName,SoftAssert sa)
		{
			try{
				  if(isElementExist(Locators_Wifi.CANCEL)) {
					  clickBtn(Locators_Wifi.CANCEL);
				  }
			if(scrollText(deviceName)){
				APP_LOGS.info("Cancel button functionality is verified in wifi direct Rename device");
				sa.assertTrue(true, "Cancel button functionality is verified in wifi direct Rename device");
				test.log(LogStatus.PASS,"Cancel button functionality is verified in wifi direct Rename device");	
			}
			else {
				APP_LOGS.info("Failed -> Cancel button is not functioning in wifi direct Rename device");
				sa.fail();
				test.log(LogStatus.FAIL, "Failed -> Cancel button is not functioning in wifi direct Rename device");
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.ERROR,"Error in locators-> verify_CancelButtonFunctionality()");
			e.printStackTrace();
		}catch (Exception e) {
			test.log(LogStatus.ERROR,"Exeption in -> verify_CancelButtonFunctionality()");
			e.printStackTrace();
		}
	}
	public void clickOn_LanguagesAndInput() {
		try {
			scrollToElements(Locators_Wifi.Languages_inputLnk);
			if(isElementExist(Locators_Wifi.Languages_inputLnk)) {
				clickBtn(Locators_Wifi.Languages_inputLnk);
			}else {
				scrollText("Languages�& input");
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.ERROR,"Error in locators-> clickOn_LanguagesAndInput()");
			e.printStackTrace();
		}catch (Exception e) {
			test.log(LogStatus.ERROR,"Exeption in -> clickOn_LanguagesAndInput()");
			e.printStackTrace();
		}
	}
	public void clickOn_Languages() {
		try {
			scrollToElements(Locators_Wifi.LanguagesLnk);
			if(isElementExist(Locators_Wifi.LanguagesLnk)) {
				clickBtn(Locators_Wifi.LanguagesLnk);
			}else {
				scrollText("Languages");
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.ERROR,"Error in locators-> clickOn_Languages()");
			e.printStackTrace();
		}catch (Exception e) {
			test.log(LogStatus.ERROR,"Exeption in -> clickOn_Languages()");
			e.printStackTrace();
		}
	}
	public boolean search_FrenchLanguage() {
		boolean french=false;
		try {
			scrollToElements(Locators_Wifi.French);
			if(isElementExist(Locators_Wifi.French)){
				french=true;
			}else {
				scrollToTextContains("Fran");
				french=isElementExist(Locators_Wifi.French);
			}
		}catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.ERROR,"Error in locators-> search_FrenchLanguage()");
			e.printStackTrace();
		}catch (Exception e) {
			test.log(LogStatus.ERROR,"Exeption in -> search_FrenchLanguage()");
			e.printStackTrace();
		}
		return french;
	}
	public void clickOn_AddALanguage() {
		try {
			scrollToElements(Locators_Wifi.AddlanguagesLnk);
			if(isElementExist(Locators_Wifi.AddlanguagesLnk)) {
				clickBtn(Locators_Wifi.AddlanguagesLnk);
			}else {
				scrollText("Add a language");
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.ERROR,"Error in locators-> clickOn_AddALanguage()");
			e.printStackTrace();
		}catch (Exception e) {
			test.log(LogStatus.ERROR,"Exeption in -> clickOn_AddALanguage()");
			e.printStackTrace();
		}
	}
	public void clickOn_French() {
		try {
			scrollToElements(Locators_Wifi.French);
			if(isElementExist(Locators_Wifi.French)) {
				clickBtn(Locators_Wifi.French);
			}else {
				scrollToTextContains("Fran");
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.ERROR,"Error in locators-> clickOn_French()");
			e.printStackTrace();
		}catch (Exception e) {
			test.log(LogStatus.ERROR,"Exeption in -> clickOn_French()");
			e.printStackTrace();
		}
	}
	public void clickOn_MoreOptions() {
		try {
			if(wait(Locators_Wifi.MoreOptions,10)) {
				clickBtn(Locators_Wifi.MoreOptions);
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.ERROR,"Error in locators-> clickOn_MoreOptions()");
			e.printStackTrace();
		}catch (Exception e) {
			test.log(LogStatus.ERROR,"Exeption in -> clickOn_MoreOptions()");
			e.printStackTrace();
		}
	}
	public void clickOn_Remove() {
		try {
			if(wait(Locators_Wifi.Remove_clsname,10)) {
				clickBtn(Locators_Wifi.Remove_clsname);
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.ERROR,"Error in locators-> clickOn_Remove()");
			e.printStackTrace();
		}catch (Exception e) {
			test.log(LogStatus.ERROR,"Exeption in -> clickOn_Remove()");
			e.printStackTrace();
		}
	}
	public void remove_LanguagesOtherthanFrench() {
		try {
			boolean frenchLang=Locators_Wifi.checkbox.getAttribute("contentDescription").contains("Frech");
			while(frenchLang==false) {
				clickBtn(Locators_Wifi.checkbox);
				clickBtn(Locators_Wifi.Remove);
				clickBtn(Locators_Wifi.OK);
				frenchLang=Locators_Wifi.checkbox.getAttribute("contentDescription").contains("Frech");

			}
			clickBtn(Locators_Wifi.Remove);	//to remove all other selected language 
			clickBtn(Locators_Wifi.OK);
		} catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.ERROR,"Error in locators-> remove_LanguagesOtherthanFrench()");
			e.printStackTrace();
		}catch (Exception e) {
			test.log(LogStatus.ERROR,"Exeption in -> remove_LanguagesOtherthanFrench()");
			e.printStackTrace();
		}
	}
		public void select_Language() throws InterruptedException
		{
		try
		{
		
		boolean result = scrollToText("Français (France)");
		if(result == false) {
			clickBtn(Locators_Wifi.AddlanguagesLnk);
			minWait();
			scrollToText("Français (France)");
			clickBtn(Locators_Wifi.France);
			
			int count = 0;
			
		}
		List<AndroidElement> lang1 = Locators_Wifi.addedLanguageOpt;
		System.out.println(lang1.size());
		for (AndroidElement androidElement : lang1) {
			if(androidElement.getText().equalsIgnoreCase("Français (France)")) {
				
			}
			else {
				
			}
		

	
	}
		
		
		clickBtn(Locators_Wifi.LanguagesettingBtn);
		clickBtn(Locators_Wifi.removeBtn);
		
		List<AndroidElement> lang = Locators_Wifi.LanguageSelChkBoxes;
		System.out.println(lang.size());
		for (AndroidElement androidElement : lang) {
			System.out.println(androidElement.getAttribute("content-desc"));
			if(androidElement.getText().equalsIgnoreCase("Français (France)")) {
				
			}
			else {
				androidElement.click();
			}
		

	
	}
		clickBtn(Locators_Wifi.deleteBtn);
		clickBtn(Locators_Wifi.OkBtn);
		clickBackButton(3);
		}
		catch (org.openqa.selenium.NoSuchElementException e) {

		test.log(LogStatus.ERROR, "Error in the locators->select_Language()");

		e.printStackTrace();

		} catch (Exception e) {

		test.log(LogStatus.ERROR, "Exeption in ->select_Language()");
		}

		}
		
		public void clickOn_UsersAndAccounts() {
			try {
				scrollToElements(Locators_Wifi.UsersAndAccounts);
				if(isElementExist(Locators_Wifi.UsersAndAccounts)) {
					clickBtn(Locators_Wifi.UsersAndAccounts);
				}else {
					scrollText("Users & accounts");
				}
			} catch (org.openqa.selenium.NoSuchElementException e) {
				test.log(LogStatus.ERROR,"Error in locators-> clickOn_UsersAndAccounts()");
				e.printStackTrace();
			}catch (Exception e) {
				test.log(LogStatus.ERROR,"Exeption in -> clickOn_UsersAndAccounts()");
				e.printStackTrace();
			}
		}
		public void clickOn_Addaccount() {
			try {
				scrollToElements(Locators_Wifi.Addaccount);
				if(isElementExist(Locators_Wifi.Addaccount)) {
					clickBtn(Locators_Wifi.Addaccount);
				}else {
					scrollText("Add account");
				}
			} catch (org.openqa.selenium.NoSuchElementException e) {
				test.log(LogStatus.ERROR,"Error in locators-> clickOn_Addaccount()");
				e.printStackTrace();
			}catch (Exception e) {
				test.log(LogStatus.ERROR,"Exeption in -> clickOn_Addaccount()");
				e.printStackTrace();
			}
		}
		public void clickOn_Google() {
			try {
				scrollToElements(Locators_Wifi.gmail_googleLnk);
				if(isElementExist(Locators_Wifi.gmail_googleLnk)) {
					clickBtn(Locators_Wifi.gmail_googleLnk);
				}else {
					scrollText("Google");
				}
			} catch (org.openqa.selenium.NoSuchElementException e) {
				test.log(LogStatus.ERROR,"Error in locators-> clickOn_Google()");
				e.printStackTrace();
			}catch (Exception e) {
				test.log(LogStatus.ERROR,"Exeption in -> clickOn_Google()");
				e.printStackTrace();
			}
		}
		public void enter_EmailAddress(String emailId) {
			try {
				if(wait(Locators_Wifi.Email_or_phone,24000)) {
					enterTextToInputField(Locators_Wifi.Email_or_phone,emailId );
				}
				if(isElementExist(Locators_Wifi.Next_gmailsetup)) {
					clickBtn(Locators_Wifi.Next_gmailsetup);
				}
			}catch (org.openqa.selenium.NoSuchElementException e) {
				test.log(LogStatus.ERROR,"Error in locators-> enter_EmailAddress()");
				e.printStackTrace();
			}catch (Exception e) {
				test.log(LogStatus.ERROR,"Exeption in -> enter_EmailAddress()");
				e.printStackTrace();
			}

		}
		public void enter_EmailPassword(String emailId) {
			try {
				if(wait(Locators_Wifi.setdata,24000)) {
					enterTextToInputField(Locators_Wifi.setdata,emailId );
				}
				if(isElementExist(Locators_Wifi.Next_gmailsetup)) {
					clickBtn(Locators_Wifi.Next_gmailsetup);
				}
			}catch (org.openqa.selenium.NoSuchElementException e) {
				test.log(LogStatus.ERROR,"Error in locators-> enter_EmailPassword()");
				e.printStackTrace();
			}catch (Exception e) {
				test.log(LogStatus.ERROR,"Exeption in -> enter_EmailPassword()");
				e.printStackTrace();
			}

		}
		public void clickOn_Iagree() {
			try {
				if(wait(Locators_Wifi.I_agree,60)) {
					clickBtn(Locators_Wifi.I_agree);
				}
			}catch (org.openqa.selenium.NoSuchElementException e) {
				test.log(LogStatus.ERROR,"Error in locators-> clickOn_Iagree()");
				e.printStackTrace();
			}catch (Exception e) {
				test.log(LogStatus.ERROR,"Exeption in -> clickOn_Iagree()");
				e.printStackTrace();
			}
		}
		public void clickOn_MORE() {
			try {
				if(wait(Locators_Wifi.MORE,60)) {
					clickBtn(Locators_Wifi.MORE);
				}
			}catch (org.openqa.selenium.NoSuchElementException e) {
				test.log(LogStatus.ERROR,"Error in locators-> clickOn_MORE()");
				e.printStackTrace();
			}catch (Exception e) {
				test.log(LogStatus.ERROR,"Exeption in -> clickOn_MORE()");
				e.printStackTrace();
			}
		}
		public void clickOn_Yes_i_am_in() {
			try {
				if(wait(Locators_Wifi.MORE,60)) {
					clickBtn(Locators_Wifi.MORE);
				}
			}catch (org.openqa.selenium.NoSuchElementException e) {
				test.log(LogStatus.ERROR,"Error in locators-> clickOn_Yes_i_am_in()");
				e.printStackTrace();
			}catch (Exception e) {
				test.log(LogStatus.ERROR,"Exeption in -> clickOn_Yes_i_am_in()");
				e.printStackTrace();
			}
		}
		public void clickOn_ACCEPT() {
			try {
				if(wait(Locators_Wifi.ACCEPT,60)) {
					clickBtn(Locators_Wifi.ACCEPT);
				}
			}catch (org.openqa.selenium.NoSuchElementException e) {
				test.log(LogStatus.ERROR,"Error in locators-> clickOn_ACCEPT()");
				e.printStackTrace();
			}catch (Exception e) {
				test.log(LogStatus.ERROR,"Exeption in -> clickOn_ACCEPT()");
				e.printStackTrace();
			}
		}
		
		public String readFile(String filename) throws IOException
		{
			String content =null;	
			try {
				 content = new String(Files.readAllBytes(Paths.get(filename)));
			}catch (Exception e) {
				e.printStackTrace();
			}
			return content;
		}
		
		public void verify_ToastMessgeIsDisplayed_WhileEnteringWrongPassword(String content,SoftAssert sa)
		{
			try{
				
			//	System.out.println("Text from screenshot = "+content);
				if(content.contains("Could not connect")         || 
				   content.contains("Check password and try again")||
				   content.contains("Password may be incorrect")) {
					APP_LOGS.info("Toast message is displayed while entering wrong password");
					sa.assertTrue(true, "Toast message is displayed while entering wrong password");
					test.log(LogStatus.PASS,"Toast message is displayed while entering wrong password");	
				}else {
					APP_LOGS.info("Failed -> Toast message is not displayed while entering wrong password");
					sa.fail();
					test.log(LogStatus.FAIL, "Failed -> Toast message is not displayed while entering wrong password");
				}
		} catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.ERROR,"Error in locators->  verify_ToastMessgeIsDisplayed_WhileEnteringWrongPassword()");
			e.printStackTrace();
		}catch (Exception e) {
			test.log(LogStatus.ERROR,"Exeption in ->  verify_ToastMessgeIsDisplayed_WhileEnteringWrongPassword()");
			e.printStackTrace();
		}
	}
		public void dial_Code(String contactNo) {
			try {
				if(isElementExist(Locators_Wifi.keypad)) {
					clickBtn(Locators_Wifi.keypad);
					minWait();
				}if(isElementExist(Locators_Wifi.enterNumber)) {
					enterTextToInputField(Locators_Wifi.enterNumber,contactNo);
				}		
			}catch (NoSuchElementException e) {
				test.log(LogStatus.ERROR,"Error in the locators => dial_Code()");
				e.printStackTrace();
			}catch (Exception e) {
				test.log(LogStatus.ERROR,"Exception in functionality=> dial_Code()");
				e.printStackTrace();
			}
		}

		public void clickOn_dialPad() {
			try {
				if(wait(Locators_Wifi.dialPad,10)) {
				clickBtn(Locators_Wifi.dialPad);
				}
			} catch (org.openqa.selenium.NoSuchElementException e) {
				test.log(LogStatus.ERROR,"Error in locators->clickOn_dialPad()");
				e.printStackTrace();
			}catch (Exception e) {
				test.log(LogStatus.ERROR, "Exception in functionality->clickOn_dialPad()");
				e.printStackTrace();
			}
			
		}
		
	
		public void clickOn_wifiInformation() {
			try {
				if(wait(Locators_Wifi.wifi_information,60))
				{	
					clickBtn(Locators_Wifi.wifi_information);
				}
				
			} catch (org.openqa.selenium.NoSuchElementException e) {

				test.log(LogStatus.ERROR,"Error in locators->clickOn_wifiInformation()");
				
			}catch (Exception e) {
				test.log(LogStatus.ERROR, "clickOn_wifiInformation");
			}
			
		}
		
		public void clickOn_WifiAPI() {
			try {
				if(wait(Locators_Wifi.wifi_API,60))
				{
					clickBtn(Locators_Wifi.wifi_API);
				}
			} catch (org.openqa.selenium.NoSuchElementException e) {
				test.log(LogStatus.ERROR,"Error in locators-> clickOn_WifiAPI()");
				e.printStackTrace();
			}catch (Exception e) {
				test.log(LogStatus.ERROR, "Exception in funcionality -> clickOn_WifiAPI()");
				e.printStackTrace();
			}
			
		}
		
		public void clickOn_enableNetwork() {
			try {
				if(wait(Locators_Wifi.enableNetwork,60))
					{
					clickBtn(Locators_Wifi.enableNetwork);
					}
			} catch (org.openqa.selenium.NoSuchElementException e) {
				test.log(LogStatus.ERROR,"Error in locators->clickOn_enableNetwork()");
				e.printStackTrace();
			}catch (Exception e) {
				test.log(LogStatus.ERROR, "Exception in funcionality -> clickOn_enableNetwork()");
				e.printStackTrace();
			}
			
		}
		
		public void enter_numberInInputField(String num) {
			try {
				enterTextToInputField(Locators_Wifi.setdata, num);
				clickBtn(Locators_Wifi.OkBtn);
			} catch (org.openqa.selenium.NoSuchElementException e) {
				test.log(LogStatus.ERROR,"Error in locators->enter_numberInInputField()");
				e.printStackTrace();
			}catch (Exception e) {
				test.log(LogStatus.ERROR, "Exception in functionality->enter_numberInInputField()");
				e.printStackTrace();
			}
			
		}
		public void enter_NonNumericNumbersMorethan11(String num) {
			try {
				if(wait(Locators_Wifi.setdata,10)) {
				enterTextToInputField(Locators_Wifi.setdata, num);
				clickBtn(Locators_Wifi.OkBtn);
				}
			} catch (org.openqa.selenium.NoSuchElementException e) {
				test.log(LogStatus.ERROR,"Error in locators-> enter_NonNumericNumbersMorethan11()");
				e.printStackTrace();
			}catch (Exception e) {
				test.log(LogStatus.ERROR, "Exception in functionality-> enter_NonNumericNumbersMorethan11()");
				e.printStackTrace();
			}
			
		}

		public void verify_ErrorMsgDisplayed_WhenEnteringMoreNonNumerics(String content,SoftAssert sa)
		{
			try{
				if(content.contains("Please enter Integer value")) {
					APP_LOGS.info("'Please enter Integer value' error message displayed successfully");
					sa.assertTrue(true, "'Please enter Integer value' error message displayed successfully");
					test.log(LogStatus.PASS,"'Please enter Integer value' error message displayed successfully");
				}else {
					APP_LOGS.info("Failed -> 'Please enter Integer value' error message is not displayed");
					sa.fail();
					test.log(LogStatus.FAIL, "Failed -> 'Please enter Integer value' error message is not displayed");
				}
		} catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.ERROR,"Error in locators-> verify_ErrorMsgDisplayed_WhenEnteringMoreNonNumerics()");
			e.printStackTrace();
		}catch (Exception e) {
			test.log(LogStatus.ERROR,"Exeption in -> verify_ErrorMsgDisplayed_WhenEnteringMoreNonNumerics()");
			e.printStackTrace();
		}
	}
		
		public void verify_AvailableWifiNetworksDisplayed(SoftAssert sa)
		{
			try{
				
				List<AndroidElement> wifis = Locators_Wifi.wifiListWindow;
				int size = wifis.size();
				if(size > 0) {
					System.out.println(size+" Wifi Networks are shown");
					APP_LOGS.info("Available wifi networks are displayed successfully");
					sa.assertTrue(true, "Available wifi networks are displayed successfully");
					test.log(LogStatus.PASS,"Available wifi networks are displayed successfully");	
				}else if(size == 0) {
					APP_LOGS.info("There is no Wifi Network Available to show");
					sa.assertTrue(true, "There is no Wifi Network Available to show");
					test.log(LogStatus.PASS,"There is no Wifi Network Available to show");	
				}
				else {
					APP_LOGS.info("Failed -> Available wifi networks are not displayed");
					sa.fail();
					test.log(LogStatus.FAIL, "Failed -> Available wifi networks are not displayed");
				}
		} catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.ERROR,"Error in locators-> verify_AvailableWifiNetworksDisplayed()");
			e.printStackTrace();
		}catch (Exception e) {
			test.log(LogStatus.ERROR,"Exeption in -> verify_AvailableWifiNetworksDisplayed()");
			e.printStackTrace();
		}
	}
		
		
		
		public void skip_welcomeGmail()
		{
			try{
				
				if(Locators_Wifi.gmailSkipBtn.isEnabled()) {
					
					Locators_Wifi.gmailSkipBtn.click();
				}
				

			} catch (org.openqa.selenium.NoSuchElementException e) {

				test.log(LogStatus.ERROR,"Error in locators->skip_welcomeGmail()");
			}catch (Exception e) {
				test.log(LogStatus.ERROR,"Exeption in ->skip_welcomeGmail()");
			}
		}	
		public void gmail_setup() {
			try {
				if(wait(Locators_Wifi.ACCEPT,60)) {
					clickBtn(Locators_Wifi.ACCEPT);
				}
			}catch (org.openqa.selenium.NoSuchElementException e) {
				test.log(LogStatus.ERROR,"Error in locators-> clickkOn_ACCEPT()");
				e.printStackTrace();
			}catch (Exception e) {
				test.log(LogStatus.ERROR,"Exeption in -> clickkOn_ACCEPT()");
				e.printStackTrace();
			}
		}
		public void check_gmailAlreadyPresent()
		{
			try{
				
				if(Locators_Wifi.add_EmailAddress.isDisplayed()) {
					Locators_Wifi.add_EmailAddress.click();
				}
				else {
					Locators_Wifi.gmailMenuBtn.click();
					Locators_Wifi.gmail_addAccountLnk.click();
					
				}

			} catch (org.openqa.selenium.NoSuchElementException e) {

				test.log(LogStatus.ERROR,"Error in locators->check_gmailAlreadyPresent()");
			}catch (Exception e) {
				test.log(LogStatus.ERROR,"Exeption in ->check_gmailAlreadyPresent()");
			}
		}	
		
		public void clickOn_google()
		{
			try{
				
				Locators_Wifi.gmail_googleLnk.click();
				

			} catch (org.openqa.selenium.NoSuchElementException e) {

				test.log(LogStatus.ERROR,"Error in locators->clickOn_google()");
			}catch (Exception e) {
				test.log(LogStatus.ERROR,"Exeption in ->clickOn_google()");
			}
		}	
		
		public void clickNextBtn()
		{
			try{
				
				Locators_Wifi.gmailNxtBtn.click();
				

			} catch (org.openqa.selenium.NoSuchElementException e) {

				test.log(LogStatus.ERROR,"Error in locators->clickNextBtn()");
			}catch (Exception e) {
				test.log(LogStatus.ERROR,"Exeption in ->clickNextBtn()");
			}
		}	
		public boolean check_GmailAlreadyAdded(String email)
		{
			boolean emailIdAdded=false;
			try{
				if(wait(Locators_Wifi.Addaccount,120)) {
					emailIdAdded=scrollText(email);
				}
			
		} catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.ERROR,"Error in locators-> check_GmailAlreadyAdded()");
			e.printStackTrace();
		}catch (Exception e) {
			test.log(LogStatus.ERROR,"Exeption in -> check_GmailAlreadyAdded()");
			e.printStackTrace();
		}
			return emailIdAdded;
	}
	
		public boolean verify_GmailAccountAdded(String email,SoftAssert sa)
		{
			boolean emailIdAdded=false;
			try{
				if(wait(Locators_Wifi.Addaccount,120)) {
					emailIdAdded=scrollText(email);
				}
				if(emailIdAdded == true) {
					APP_LOGS.info("Gmail account is configured successfully using wifi network");
					sa.assertTrue(true, "Gmail account is configured successfully using wifi network");
					test.log(LogStatus.PASS,"Gmail account is configured successfully using wifi network");	
				}else  {
					APP_LOGS.info("Failed -> Gmail account is not configured using wifi network");
					sa.fail();
					test.log(LogStatus.FAIL,"Failed -> Gmail account is not configured using wifi network");	
				}

		} catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.ERROR,"Error in locators-> verify_GmailAccountAdded()");
			e.printStackTrace();
		}catch (Exception e) {
			test.log(LogStatus.ERROR,"Exeption in -> verify_GmailAccountAdded()");
			e.printStackTrace();
		}
			return emailIdAdded;
	}
		public void postCond_RemoveGmailAccount(String emailId) {
			try {
			//	AndroidElement emailAddress=aDriver.findElementByXPath("//android.widget.TextView[@text='"emailId"']");
				boolean emailAddress=scrollText(emailId);
				while(emailAddress==false) {
					emailAddress=scrollText(emailId);
				}
				if(wait(Locators_Wifi.REMOVE_ACCOUNT,70)) {
					clickBtn(Locators_Wifi.REMOVE_ACCOUNT);
				}
				if(isElementExist(Locators_Wifi.REMOVE_ACCOUNT)) {
					clickBtn(Locators_Wifi.REMOVE_ACCOUNT);
				}
			}catch (org.openqa.selenium.NoSuchElementException e) {
				test.log(LogStatus.ERROR,"Error in locators-> postCond_RemoveGmailAccount()");
				e.printStackTrace();
			}catch (Exception e) {
				test.log(LogStatus.ERROR,"Exeption in -> postCond_RemoveGmailAccount()");
				e.printStackTrace();
			}
		}
		
		public void preCond_setSleepTime(){
			try {
				Runtime.getRuntime().exec("adb -s "+p_Id+" shell settings put system screen_off_timeout 1800000");
			}catch (Exception e) {
				test.log(LogStatus.ERROR, "Exception in -> preCond_setSleepTime()");
				e.printStackTrace();
			}
		}	
		
		public void clear_ChromePermission() {
			try {
				if(isElementExist(Locators_Wifi.OK)) {
					clickBtn(Locators_Wifi.OK);
					minWait();
				}
				if(isElementExist(Locators_Wifi.No_ThanKS)) {
					clickBtn(Locators_Wifi.No_ThanKS);
				}
			
				if(isElementExist(Locators_Wifi.Cancel)) {
					clickBtn(Locators_Wifi.Cancel);
				}
				if(isElementExist(Locators_Wifi.Accept_id)) {
					clickBtn(Locators_Wifi.Accept_id);
				}
				if(isElementExist(Locators_Wifi.NEXT)) {
					clickBtn(Locators_Wifi.NEXT);
				}
				if(isElementExist(Locators_Wifi.NEXTBTN)) {
					clickBtn(Locators_Wifi.NEXTBTN);
				}
		
				if(isElementExist(Locators_Wifi.NO_THANKS)) {
					clickBtn(Locators_Wifi.NO_THANKS);
				}
			
				if(isElementExist(Locators_Wifi.No_ThanKS)) {
					clickBtn(Locators_Wifi.No_ThanKS);
				}
				
		}  catch (NoSuchElementException e) {
			test.log(LogStatus.ERROR,"Error in the locators => clear_ChromePermission()");
			e.printStackTrace();
		}catch (Exception e) {
			test.log(LogStatus.ERROR,"Exception in functionality=> clear_ChromePermission()");
			e.printStackTrace();
		}		
	}
		
		public void enter_URL(int n,String url) {
			try {	
		
				if(isElementExist(Locators_Wifi.alert_OKBtn)) {
					clickBtn(Locators_Wifi.alert_OKBtn);
					System.out.println("Im Clicking Ok First ");
				}
				if(isElementExist(Locators_Wifi.NO_THANKS)) {
					clickBtn(Locators_Wifi.NO_THANKS);
				}
				if(isElementExist(Locators_Wifi.No_ThanKS) ){
					clickBtn(Locators_Wifi.No_ThanKS);
				}
			
				customWait(2000);
				enterTextToInputField(Locators_Wifi.chromeSearch,url);
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_ENTER);
				customWait(2000);
				if(isElementExist(Locators_Wifi.OK)) {
					clickBtn(Locators_Wifi.OK);
				}
				if(isElementExist(Locators_Wifi.NO_THANKS)) {
					clickBtn(Locators_Wifi.NO_THANKS);
				}
				if(isElementExist(Locators_Wifi.No_ThanKS) ){
					clickBtn(Locators_Wifi.No_ThanKS);
				}
			
			} catch (NoSuchElementException e) {
				test.log(LogStatus.ERROR,"Error in the locators => enter_URL()");
				e.printStackTrace();
			}catch (Exception e) {
				test.log(LogStatus.ERROR,"Exception in functionality=> enter_URL()");
				e.printStackTrace();
			}
			
		}	
		public void clickOn_Search() {
			try {
				if (isElementExist(Locators_Wifi.NOT_NOW_txt)) {
					clickBtn(Locators_Wifi.NOT_NOW_txt);
				}
				if (isElementExist(Locators_Wifi.NOT_NOW_id)) {
					clickBtn(Locators_Wifi.NOT_NOW_id);
				}

				clickBtn(Locators_Wifi.Search);

			} catch (org.openqa.selenium.NoSuchElementException e) {
				test.log(LogStatus.ERROR, "Error in the locators->clickOn_Search()");
				e.printStackTrace();
			} catch (Exception e) {
				test.log(LogStatus.ERROR, "Exception in ->clickOn_Search()");
				e.printStackTrace();
			}

		}
		public void search_Video(String videoName) {
			try {
				wait(Locators_Wifi.setdata,10);
				enterTextToInputField(Locators_Wifi.setdata, videoName);
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.ENTER);
				minWait();
			} catch (org.openqa.selenium.NoSuchElementException e) {
				test.log(LogStatus.ERROR, "Error in the locators->search_Video()");
				e.printStackTrace();
			} catch (Exception e) {
				test.log(LogStatus.ERROR, "Exception in ->search_Video()");
				e.printStackTrace();
			}

		}
		public void launchYoutube() {
			try {
				customWait(1000);
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_HOME);
				APP_LOGS.info("Home PAge");
				customWait(2000);
				Locators_BaseUtil.AppListIcon.click();
				customWait(1000);		
				scrollToElements(Locators_Wifi.You_Tube);
				clickBtn(Locators_Wifi.You_Tube);
				APP_LOGS.info("Clicked on Youtube successfully.");
			} catch (org.openqa.selenium.NoSuchElementException e) {
				test.log(LogStatus.ERROR, "Error in the locators->launchYoutube()");
				e.printStackTrace();
			} catch (Exception e) {
				test.log(LogStatus.ERROR, "Exception in ->launchYoutube()");
				e.printStackTrace();
			}	
		}
		public void play_Video() {
			try {
				if (wait(Locators_Wifi.sonimvideo, 120)==true) {
					if(isElementExist(Locators_Wifi.sonimvideo)) {
						clickBtn(Locators_Wifi.sonimvideo);
					}else {
						scrollToElements(Locators_Wifi.sonimvideo);
						if(isElementExist(Locators_Wifi.sonimvideo)) {
							clickBtn(Locators_Wifi.sonimvideo);
						}else {
							boolean sonimVideo=scrollToTextContains("Sonim XP8. First look.");
							while(sonimVideo==false) {
								sonimVideo=scrollToTextContains("Sonim XP8. First look.");
							}
						}
					}
					System.out.println("locator");
				} else {
					Runtime.getRuntime().exec("adb -s " + p_Id + " shell input tap 348 1635");
					System.out.println("adb command");
				}
			} catch (org.openqa.selenium.NoSuchElementException e) {
				test.log(LogStatus.ERROR, "Error in the locators-> play_Video()");
				e.printStackTrace();
			} catch (Exception e) {
				test.log(LogStatus.ERROR, "Exception in -> play_Video()");
				e.printStackTrace();
			}

		}
		public void wait_TenMinutes(String dataUsed) {
			try {
				if(dataUsed.contains("GB") || dataUsed.contains("gb") || dataUsed.contains("Gb")) {
					wait(Locators_Wifi.Hotspot_saveBtn,600);
					System.out.println("Video played for ten minutes ");
				}
				else if(dataUsed.contains("MB") || dataUsed.contains("mb") || dataUsed.contains("Mb")) {
					wait(Locators_Wifi.Hotspot_saveBtn,240);
					System.out.println("Video played for three minutes ");
					}
				else  {
					wait(Locators_Wifi.Hotspot_saveBtn,240);
					System.out.println("Video played for three minutes ");
					}
			} catch (Exception e) {
				test.log(LogStatus.ERROR, "Exception in -> wait_TenMinutes()");
				e.printStackTrace();
			}

		}

	public void verify_MobileData_NotUsed(int beforeWifi,int afterWifi,SoftAssert sa) {
	try {
				System.out.println("before wifi mobile data used = "+beforeWifi);
				System.out.println("after wifi mobile data used = "+afterWifi);
				if(beforeWifi == afterWifi) {	
				APP_LOGS.info("Wifi connected successfully and mobile data is not used");
				sa.assertTrue(true, "Wifi connected successfully and mobile data is not used");
				test.log(LogStatus.PASS,"Wifi connected successfully and mobile data is not used");	
			}
			else {
				APP_LOGS.info("Mobile data is used for browsing");
				sa.fail();
				test.log(LogStatus.FAIL, "Mobile data is used for browsing");
			}
	} catch (org.openqa.selenium.NoSuchElementException e) {
		test.log(LogStatus.ERROR,"Error in locators-> verify_MobileData_NotUsed()");
		e.printStackTrace();
	}catch (Exception e) {
		test.log(LogStatus.ERROR,"Exeption in -> verify_MobileData_NotUsed()");
		e.printStackTrace();
	}
		}
	
	
}
	
	
	
	
	
	
	

