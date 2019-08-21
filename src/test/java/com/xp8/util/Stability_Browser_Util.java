package com.xp8.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import com.google.common.collect.ImmutableMap;
import com.graphics.gui.JsonFileReaderAndWriter;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import com.xp8.util.BaseUtil;

import application.AllQA;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.AndroidKeyCode;

public class Stability_Browser_Util extends BaseUtil {

	public static ExtentReports extent;
	public static ExtentTest test;

	public Process p;
	public boolean check = false;
	public boolean check1 = false;
	public boolean check2 = false;
	boolean value = false;
	public String p_Id	 = "";								// Primary Device Serial Number.
	public String r_Id	 = "";								// Reference Device Serial Number.
	public String p_b_No = "";			   					// Primary Device Build Number.
	public String r_b_No = ""; 								// Reference Device Build Number.
	public String pryNum = AllQA.PRIMARYDEVMDN;	// Reference Device MDN. 
	public String refNum = AllQA.REFERENCEDEVMDN;	// Reference Device MDN.
	public String SSID=AllQA.SSID;
	public String WIFIPASSWORD=AllQA.WIFIPASSWORD;

	/*public String pryNum = AllQA.PRIMARYDEVMDN;	// Reference Device MDN. 
	public String refNum = AllQA.REFERENCEDEVMDN;	// Reference Device MDN.
*/

	public void fetch_Devices_Details() throws InterruptedException, FileNotFoundException, IOException, ParseException {

		minWait();
		p_Id=JsonFileReaderAndWriter.primaryDevIdReader();
		r_Id=JsonFileReaderAndWriter.ReadRefDeviceId();
		p_b_No=JsonFileReaderAndWriter.primaryDevFirmwareReader();
		r_b_No=JsonFileReaderAndWriter.ReadRefDeviceFirmWare();
	}


	public void installAPK() throws InterruptedException, IOException {

		System.out.println(" +++");
		customWait(1000);
		Process p1 = Runtime.getRuntime().exec("adb -s "+p_Id+" install src/test/resources/StorageFile/FillMemory.apk");
		customWait(2000);
		//		 aDriver.installApp("src/test/resources/StorageFile/FillMemory.apk");
		Thread.sleep(6000);
		p1.destroy();
		launch_an_app("fillmemory");
		if(isElementExist(Locators_Stability.startFilling)) {
			System.out.println("Verified");
			test.log(LogStatus.INFO, "FillMemory App is not present in the device ");
		}
		else {
			System.out.println("Not Verified");
			test.log(LogStatus.INFO, "FillMemory App is not present in the device ");
		}		  
	}

	public void performAction() throws IOException, InterruptedException {
		/*
		 * Perform action for IMS registered check
		 */
		try {
			customWait(1000);
			Runtime.getRuntime().exec("adb -s "+p_Id+" shell am start -a android.settings.AIRPLANE_MODE_SETTINGS");
			minWait();
			enableSwitch(Locators_Stability.enabled_Airplane,Locators_Stability.disabled_Airplane,Locators_Stability.switch_widget);
			customWait(5000);
			disableSwitch(Locators_Stability.disabled_Airplane,Locators_Stability.enabled_Airplane,Locators_Stability.switch_widget );		
			customWait(15000);
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			minWait();
		} 
		catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.ERROR, "Error in the locators->performAction()");
			e.printStackTrace();
		}
		catch (Exception e) {
			test.log(LogStatus.ERROR, "Exception in performAction() ");
			e.printStackTrace();
		}
	}

	public boolean validateIMSLog(String str,String fileName) throws IOException, InterruptedException {
		/*
		 * Validate via log String that MO-Voice  call initiated 
		 */
		SoftAssert s1 = new SoftAssert();

		if(searchString(str, fileName)) {
			check = true;
			test.log(LogStatus.INFO, "IMS is Enable");
			s1.assertTrue(check, " ");
		}
		else {
			check = false;
			test.log(LogStatus.SKIP, "IMS is not enabled" );

		}
		s1.assertAll();
		return check;
	}


	public void memoryFill() throws InterruptedException {
		/*
		 * Fillinternal memory to 92%
		 */

		try {
			launch_an_app("fillmemory");
			customWait(1000);
			enterTextToInputField(Locators_Stability.enterFill_Memory,"92");
			customWait(1000);
			Locators_Stability.startFilling.click();
			minWait();
			OCRScreencapture("File");
			int i=0;
			while(searchStringOCR("Filling Internal Memory", "OCR")) {				

				System.out.println("Screenshot  "+ i);
				i++;
				customWait(10000);
				OCRScreencapture("File");

				if(aDriver.isLocked()){
					minWait();
					Runtime.getRuntime().exec("adb -s "+p_Id+" shell input keyevent 3");
					minWait();
					Runtime.getRuntime().exec("adb -s "+p_Id+" shell input keyevent 82");
					customWait(500);
					System.out.println("InSide if");
					continue;
				}
				continue;
				//				System.out.println("Continue; ");
			}   
			System.out.println("Out ");
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
		} catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.ERROR, "Error in the locators->memoryFill()");
			e.printStackTrace();
		}
		catch (Exception e) {
			test.log(LogStatus.ERROR, "Exception in memoryFill() ");
			e.printStackTrace();
		}			
	}


	public void clickOnWifi() {
		try {
			minWait();
			aDriver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().className(\"android.support.v7.widget.RecyclerView\")).scrollIntoView(new UiSelector().text(\"Wi-Fi\"))").click();
			minWait();
		}  catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.ERROR, "Error in the locators->clickOnWifi()");
			e.printStackTrace();
		}
		catch (Exception e) {
			test.log(LogStatus.ERROR, "Exception in clickOnWifi() ");
			e.printStackTrace();
		}	
	}

	public void enable_Mobile_Data() {
		try {
			minWait();
			if (!isElementExist(Locators_Stability.mobileDataOption)) {
				System.out.println("Mobile data is OFF");
				WebDriverWait wait = new WebDriverWait(aDriver, 60);
				minWait();
				clickBtn(multi_Loc_Strategy(Locators_Stability.switch_OffState, Locators_Stability.switch_OffStateByXpath, null, null, null, 892, 1032));
				System.out.println("Mobile data is turned ON");
				wait.until(ExpectedConditions.visibilityOf(Locators_Stability.mobileDataOption));
			}		
			else{
				System.out.println("Mobile data is turned ON");
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.ERROR, "Error in the locators->setUp_And_Enable_Mobile_Data()");
			e.printStackTrace();
		}
		catch (Exception e) {
			test.log(LogStatus.ERROR, "Exception in setUp_And_Enable_Mobile_Data() ");
			e.printStackTrace();
		}
	}
	
	/*public void setUp_And_Enable_WiFi() {
		try {
			WebDriverWait wait = new WebDriverWait(aDriver, 60);
			minWait();
			if (!isElementExist(multi_Loc_Strategy(Locators_Stability.wifi_ConnectedState, Locators_Stability.wifi_ConnectedStateByUiAutomator, null, null, null, 0, 0))) {
				System.out.println("Wifi is not connected");
				customWait(2000);
				if(isElementExist(Locators_Stability.switch_OffState)) {
					clickBtn(Locators_Stability.switch_OffState);
					System.out.println("Wifi is turned ON");
					wait.until(ExpectedConditions.visibilityOf(Locators_Stability.wifi_IDC));
				}
				clickBtn(multi_Loc_Strategy(Locators_Stability.switch_OffState, Locators_Stability.switch_OffStateByXpath, null, null, null, 892, 1032));
				//customWait(5000);
				if (!isElementExist(multi_Loc_Strategy(Locators_Stability.wifi_ConnectedStateByUiAutomator, Locators_Stability.wifi_ConnectedState, null, null, null, 0, 0))) {
					customWait(2000);
					scrollToText(SSID);
				//	aDriver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().className(\"android.support.v7.widget.RecyclerView\")).scrollIntoView(new UiSelector().text(\""+SSID+"\")").click();
					
					minWait();
					enterTextToInputField(multi_Loc_Strategy(Locators_Stability.wifi_PsswdBx, Locators_Stability.wifi_PsswdBx_index, Locators_Stability.wifi_PsswdBx_instance, null, null, 135, 424),WIFIPASSWORD);
					minWait();
					clickBtn(multi_Loc_Strategy(Locators_Stability.connect, Locators_Stability.connectById, Locators_Stability.connectByContains, Locators_Stability.connectByXpath, null, 705, 881));
					customWait(7000);
					wait.until(ExpectedConditions.visibilityOf(multi_Loc_Strategy(Locators_Stability.wifi_ConnectedStateByUiAutomator, Locators_Stability.wifi_ConnectedState, null, null, null, 0, 0)));
				}
			}
			
			else{
				System.out.println("Wifi is ON");
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.ERROR, "Error in the locators->setUp_And_Enable_WiFi()");
			e.printStackTrace();
		}
		catch (Exception e) {
			test.log(LogStatus.ERROR, "Exception in setUp_And_Enable_WiFi() ");
			e.printStackTrace();
		}
	}*/
	
	public void setUp_And_Enable_WiFi() {
		try {
			WebDriverWait wait = new WebDriverWait(aDriver, 60);
			minWait();
			if (!isElementExist(multi_Loc_Strategy(Locators_Stability.wifi_ConnectedState, Locators_Stability.wifi_ConnectedStateByUiAutomator, null, null, null, 0, 0))) {
				System.out.println("Wifi is not connected");
				customWait(2000);
				if(isElementExist(Locators_Stability.switch_OffState)){
					clickBtn(Locators_Stability.switch_OffState);
					System.out.println("Wifi is turned ON");
				}
				else{
					System.out.println("Wifi is ON but not connected");
				}
				
				//wait.until(ExpectedConditions.visibilityOf(Locators_Stability.wifi_IDC));
				if (!isElementExist(multi_Loc_Strategy(Locators_Stability.wifi_ConnectedStateByUiAutomator, Locators_Stability.wifi_ConnectedState, null, null, null, 0, 0))) {
					customWait(5000);
					if(isElementExist(Locators_Stability.Wifi_IDC_UiSelector)){
						//scrollToElements(Locators_Stability.Wifi_IDC_UiSelector);
						scrollToText(SSID);
						clickBtn(Locators_Stability.Wifi_IDC_UiSelector);
						//aDriver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().className(\"android.support.v7.widget.RecyclerView\")).scrollIntoView(new UiSelector().text(\"IDCSONWAP\"))").click();
						System.out.println("Clicked on IDCSONWAP");	
					}
					else{
						clickBackButton(2);
						//scrollToElements(Locators_Stability.Wifi_IDC_UiSelector);
						scrollToText(SSID);
						clickBtn(Locators_Stability.Wifi_IDC_UiSelector);
						//aDriver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().className(\"android.support.v7.widget.RecyclerView\")).scrollIntoView(new UiSelector().text(\"IDCSONWAP\"))").click();
						System.out.println("Clicked on IDCSONWAP");
					}
					minWait();
					enterTextToInputField(multi_Loc_Strategy(Locators_Stability.wifiPasswordTextBox, Locators_Stability.wifiPasswordTextBoxById, Locators_Stability.wifiPasswordTextBoxByIndex, Locators_Stability.wifiPasswordTextBoxByXpath, null, 135, 424),"1dcS0n1md0tc0MbLr");
					System.out.println("Entered PWD");
					customWait(3000);
					clickBtn(multi_Loc_Strategy(Locators_Stability.connect, Locators_Stability.connectById, Locators_Stability.connectByContains, Locators_Stability.connectByXpath, null, 705, 881));
					System.out.println("Clicked on CONNECT");
					
					wait.until(ExpectedConditions.visibilityOf(multi_Loc_Strategy(Locators_Stability.wifi_ConnectedStateByUiAutomator, Locators_Stability.wifi_ConnectedState, null, null, null, 0, 0)));
					System.out.println("Waited for Visibility of Connect");
				}
			}
			
			else{
				System.out.println("Wifi is turned ON");
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.ERROR, "Error in the locators->setUp_And_Enable_WiFi()");
			e.printStackTrace();
		}
		catch (Exception e) {
			test.log(LogStatus.ERROR, "Exception in setUp_And_Enable_WiFi() ");
			e.printStackTrace();
		}
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

	public void moreOptions() throws InterruptedException {
		try {
			customWait(2000);
			if(isElementExist(Locators_Stability.moreOptions)) {
				clickBtn(Locators_Stability.moreOptions);
			}
			else if(isElementExist(Locators_Stability.moreUpdateOptions)) {
				clickBtn(Locators_Stability.moreUpdateOptions);
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.ERROR, "Error in the locators->moreOptions()");
			e.printStackTrace();
		}
		catch (Exception e) {
			test.log(LogStatus.ERROR, "Exception in moreOptions() ");
			e.printStackTrace();
		}
	}


	public void setDataSaver_On() throws InterruptedException {
		try {
			minWait();
			moreOptions();
			minWait();
			scrollToText("Settings");
			minWait();
			scrollToText("Data Saver");
			minWait();
			clickBtn(Locators_Stability.switch_Off_State);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			minWait();
		} catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.ERROR, "Error in the locators->setDataSaver_On()");
			e.printStackTrace();
		}
		catch (Exception e) {
			test.log(LogStatus.ERROR, "Exception in setDataSaver_On() ");
			e.printStackTrace();
		}	
	}

	public void editHomepageOpen(String newUrl) throws InterruptedException 
	//Navigate to homepage and set AT&T home page
	{ 
		try {
			minWait();
			moreOptions();
			minWait();
			scrollToText("Settings");
			minWait();
			scrollToText("Home page");
			minWait();
			scrollToText("Open this page");
			minWait();
			//			 Locators_Stability.OpnPage.click();
			minWait();
			enterTextToInputField(Locators_Stability.OpnPgeField,newUrl);
			customWait(2000);
			Locators_Stability.SaveOptns.click();
			navigateback();
		} 
		catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.ERROR, "Error in the locators->editHomepageOpen()");
			e.printStackTrace();
		}
		catch (Exception e) {
			test.log(LogStatus.ERROR, "Exception in editHomepageOpen() ");
			e.printStackTrace();
			navigateback();
		}

	}

	public void navigateback() throws InterruptedException {
		//click on navigate Icon

		try {
			minWait();
			for(int i=1; i<=4; i++) {
				if(isElementExist(Locators_Stability.NavigateUpSrchEng)) {
					minWait();
					Locators_Stability.NavigateUpSrchEng.click();
					minWait(); 
				}
				else if(isElementExist(Locators_Stability.NavigateBack)) {
					minWait();
					Locators_Stability.NavigateBack.click();
					minWait(); 
				}
				else if(isElementExist(Locators_Stability.closeIcon)) {
					minWait();
					Locators_Stability.closeIcon.click();
					minWait(); 
				}
				
				else if(isElementExist(Locators_Stability.close)) {
					minWait();
					System.out.println("In Closing");
					Locators_Stability.close.click();
					minWait(); 
				}
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.ERROR, "Error in the locators->navigateback()");
			e.printStackTrace();
		}
		catch (Exception e) {
			test.log(LogStatus.ERROR, "Exception in navigateback() ");
			e.printStackTrace();
		}
	}

	public void checkBookmarkPage() throws InterruptedException {
		//Check page is Bookmarked or not
		try {
			minWait();
			minWait();
			moreOptions();
			minWait();
			if(isElementExist(Locators_Stability.editBookmark)) {
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			}
			else if(isElementExist(Locators_Stability.BookmarkIcon)) {
				minWait();
				clickBtn(Locators_Stability.BookmarkIcon);
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.ERROR, "Error in the locators->checkBookmarkPage()");
			e.printStackTrace();
		}
		catch (Exception e) {
			test.log(LogStatus.ERROR, "Exception in checkBookmarkPage() ");
			e.printStackTrace();
		}


	}

	public void navigateBrowserMenu(String url) throws InterruptedException {
		//Navigate to Browser menu to any option
		try {
			System.out.println("Entering URL:-"+url);
			customWait(2000);
			
			/*List<String> removePicsArgs = Arrays.asList("-a android.intent.action.VIEW -d https://"+url);
			Map<String, Object> removePicsCmd = ImmutableMap.of("command", "am start", "args", removePicsArgs);
			aDriver.executeScript("mobile: shell", removePicsCmd);*/
			
			Runtime.getRuntime().exec("adb -s "+p_Id+" shell am start -a android.intent.action.VIEW -d https://"+url);
			customWait(3000);
//			enterTextToInputField(Locators_Stability.urlBar_Chrome, url);
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_ENTER);
			minWait();
		} catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.ERROR, "Error in the locators->navigateBrowserMenu()");
			e.printStackTrace();
		}
		catch (Exception e) {
			test.log(LogStatus.ERROR, "Exception in navigateBrowserMenu() ");
			e.printStackTrace();
		}

	}
	
	public void validateUrlEntered(String web,int n, int numitr,SoftAssert soft)
	{
		/*
		 * validate the website page is loaded via adb logs
		 */
		SoftAssert S2= new SoftAssert();
		try { 
			customWait(5000);
			/*if(isElementExist(Locators_Stability.BlockBtn)) {
				clickBtn(Locators_Stability.BlockBtn);
				minWait();
			}*/
			//if(isElementExist(Locators_Stability.networkNotAvailable)||isElementExist(Locators_Stability.site404Error)|| isElementExist(Locators_Stability.siteNotloaded)|| isElementExist(Locators_Stability.WebPageBlocked)){
			/*if(isElementExist(multi_Loc_Strategy(Locators_Stability.networkNotAvailable, Locators_Stability.networkNotAvailableByContains, Locators_Stability.networkNotAvailableByXpath, null, null, 0, 0))||isElementExist(multi_Loc_Strategy(Locators_Stability.WebPageBlocked, Locators_Stability.WebPageBlockedByContains, Locators_Stability.WebPageBlockedByXpath, null, null, 0, 0)) || isElementExist(multi_Loc_Strategy(Locators_Stability.siteNotloaded, Locators_Stability.siteNotloadedByContains, Locators_Stability.siteNotloadedByXpath, null, null, 0, 0)) || isElementExist(multi_Loc_Strategy(Locators_Stability.site404Error, Locators_Stability.site404ErrorByContains, Locators_Stability.site404ErrorByXpath, null, null, 0, 0))){*/

			if(isElementExist(multi_Loc_Strategy(Locators_Stability.networkNotAvailable, null, null, null, null, 0, 0))||isElementExist(multi_Loc_Strategy(Locators_Stability.WebPageBlocked,null, null, null, null, 0, 0)) || isElementExist(multi_Loc_Strategy(Locators_Stability.siteNotloaded,null,null, null, null, 0, 0)) || isElementExist(multi_Loc_Strategy(Locators_Stability.site404Error,null,null, null, null, 0, 0))|| isElementExist(multi_Loc_Strategy(Locators_Stability.connectionNotPrivate, null, null, null, null, 0, 0))){
				test.log(LogStatus.FAIL, "Entered Website page " + "\""+ web +"\"" + " not Loaded successfully at iteration: "+n);
			     soft.fail();
			} 
			else {
				check = true;
				APP_LOGS.info("Url entered sucessfully.");
				System.out.println("Url entered sucessfully.");
				test.log(LogStatus.PASS, "Entered Website page " + "\""+ web +"\"" + " Loaded Successfully at iteration: "+n);
				soft.assertTrue(check, " ");
			}
		}
		catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.ERROR, "Error in the locators->validateUrlEntered()");
			e.printStackTrace();
		}
		catch (Exception e) {
			test.log(LogStatus.ERROR, "Exception in validateUrlEntered() ");
			e.printStackTrace();
		} 
	}

	public void navigateBookmark() throws InterruptedException {
		//Navigate to Browser menu to any option
		try {
			System.out.println("Bookmark");
			customWait(2000);

			moreOptions();
			minWait();
			if(isElementExist(Locators_Stability.editBookmark)) {
				minWait();
				scrollToText("Bookmarks");
				minWait();
				clickBtn(Locators_Stability.MobileBookMark);
				minWait();
				for(int i=1; i<=10;i++) {
					if (isElementExist(Locators_Stability.BookmarkmoreOptions)) {
						clickBtn(Locators_Stability.BookmarkmoreOptions);
						minWait();
						clickBtn(Locators_Stability.deleteContactOptn);
						minWait();			
					}
					navigateback();
				}
			}
			else {
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
				System.out.println("No Bookmarks Page");
			}

		}
		catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.ERROR, "Error in the locators->navigateBookmark()");
			e.printStackTrace();
		}
		catch (Exception e) {
			test.log(LogStatus.ERROR, "Exception in navigateBookmark() ");
			e.printStackTrace();
		}

	}


	public boolean validate_RIL_Logs(String filename,String validationString,String infoForFailure) throws InterruptedException {

		customWait(2000);
		boolean check1 = searchString(validationString,filename);
		SoftAssert sf = new SoftAssert();
		if (check1) {
			//			check=true;
			test.log(LogStatus.INFO, "Validated from RIL Logs : "+validationString);
		} else {			
			test.log(LogStatus.INFO, "Validation failed from ADB Logs. ==>> "+infoForFailure);
		}
		return check1;
	}


	public void bookmarkOpen(WebElement page,String openPage) throws InterruptedException {
		//open bookmarked website

		try {
			System.out.println("In BookMArk ");
			minWait();
			moreOptions();
			minWait();
			scrollToText("Bookmarks");
			minWait();
			clickBtn(Locators_Stability.MobileBookMark);
			minWait();
			scrollToText(openPage);
			minWait();
			clickBtn(page);
			customWait(3000);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		/*scrollToText("Amazon.com");
        minWait();
        clickBtn(Locators_Stability.GooglePage);*/
	}

	
	public void linkToLink(int n) throws InterruptedException {
		//click on Sign Up link
		
		try {
			scrollToText("SIGN UP");
			minWait();
			clickBtn(Locators_Stability.SignUpLink);
			customWait(2000);
			
			if(isElementExist(Locators_Stability.SignUpPage)) {
				test.log(LogStatus.INFO, "Verified Navigation Link to Link at iteration : "+n);
				
			}
			else {
				test.log(LogStatus.ERROR, "Unverified Navigation Link to Link at iteration : "+n);
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.ERROR, "Error in the locators->linkToLink()");
			e.printStackTrace();
		}
		catch (Exception e) {
			test.log(LogStatus.ERROR, "Exception in linkToLink() ");
			e.printStackTrace();
		}
	}
	
	public void validateHomePageLoad(int n) {
		//Validate ATT Home PAge is loaded
		
		try {
			customWait(2000);
			if(isElementExist(Locators_Stability.attnet)) {
				System.out.println("AT&T Homepage");
				test.log(LogStatus.INFO, "Navigated to AT&T Homepage at iteration : "+n);
			}
			else {
				System.out.println("AT&T Homepage");
				test.log(LogStatus.ERROR, "AT&T Homepage not loaded at iteration : "+n);
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.ERROR, "Error in the locators->validateHomePageLoad()");
			e.printStackTrace();
		}
		catch (Exception e) {
			test.log(LogStatus.ERROR, "Exception in validateHomePageLoad() ");
			e.printStackTrace();
		}
	}
	
	
	public void validateLoadWebsites(int n,String webPage) throws InterruptedException {
		//validate till load website completely
		
		try {
			WebDriverWait wait = new WebDriverWait(aDriver, 30);
			wait.until(ExpectedConditions.visibilityOf(Locators_Stability.toolbarSlider));
			minWait();
			
			if(isElementExist(Locators_Stability.toolbarSlider)) {
				test.log(LogStatus.INFO, "Website "+webPage+" loaded successfully at iteration : "+n);
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.ERROR, "Error in the locators->validateLoadWebsites()");
			e.printStackTrace();
		}
		catch (Exception e) {
			test.log(LogStatus.ERROR, "Exception in validateLoadWebsites() ");
			e.printStackTrace();
		}
	}
	
	public void selectHistoryPg() throws InterruptedException {
		try {
			customWait(5000);
			moreOptions();
			APP_LOGS.info("Chrome Menu is Selected sucessfully");
			minWait();
			scrollToText("History");
			APP_LOGS.info("History Option is Selected sucessfully");
		}
		catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.ERROR, "Error in the locators->selectHistoryPg()");
			e.printStackTrace();
		}
		catch (Exception e) {
			test.log(LogStatus.ERROR, "Exception in selectHistoryPg() ");
			e.printStackTrace();
		}
	}

	public void clearHistry() throws InterruptedException {
		try {
			selectHistoryPg();
			minWait();
			Locators_Stability.ClearBrowsngDataOptn.click();
			APP_LOGS.info("Clear Browsing Data.. is Clicked sucessfully");
			minWait();
			Locators_Stability.ClrDataDropdown.click();
			APP_LOGS.info("Clear Data option is Clicked sucessfully");
			minWait();
			Locators_Stability.begningTimeOptn.click();
			APP_LOGS.info("From Begning of time Option is Selected sucessfully");
			minWait();
			Locators_Stability.ClrDataBtn.click();
			APP_LOGS.info("History Clear Data Button is Selected sucessfully");
			minWait();
			if(isElementExist(Locators_Stability.HistryClear)) {
				Locators_Stability.HistryClear.click();
				minWait();
			}	
			customWait(2000);
			navigateback();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_HOME);
		}
		catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.ERROR, "Error in the locators->clearHistry()");
			e.printStackTrace();
		}
		catch (Exception e) {
			test.log(LogStatus.ERROR, "Exception in clearHistry() ");
			e.printStackTrace();
		}
	}
	
	
	public void clickOn_Networks_and_Internet() {
		try {
			minWait();
			scrollToText("Network & Internet");
			minWait();
		} catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.ERROR, "Error in the locators->clickOn_Networks_and_Internet()");
			e.printStackTrace();
		}
		catch (Exception e) {
			test.log(LogStatus.ERROR, "Exception in clickOn_Networks_and_Internet() ");
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
		catch(Exception e) {
			return check;
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

	/*public void ON_Switch(String switch_To_ON) {
		try {
			minWait();
			aDriver.findElementByXPath("//*[contains(@text,'"+switch_To_ON+"')]/../..//*[@text='OFF']").click();
			minWait();
		} catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.ERROR, "Error in the locators->ON_Switch()");
			e.printStackTrace();
		}
		catch (Exception e) {
			test.log(LogStatus.ERROR, "Exception in ON_Switch() ");
			e.printStackTrace();
		}
	}*/
	
	public void ON_Switch(String switch_To_ON) {
		try {
			minWait();
			if(isElementExist(aDriver.findElementByXPath("//*[contains(@text,'"+switch_To_ON+"')]/../..//*[@text='OFF']"))){
				String s = (aDriver.findElementByXPath("//*[contains(@text,'"+switch_To_ON+"')]/../..//*[@text='OFF']")).getText();
				System.out.println(s);
				if(s.equals("OFF")){
					clickBtn(aDriver.findElementByXPath("//*[contains(@text,'"+switch_To_ON+"')]/../..//*[@text='OFF']"));
				}
				else{
					System.out.println("Mobile Data is ON");
				}
			}
			else{
				System.out.println("ELSE");
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.ERROR, "Error in the locators->ON_Switch()");
			e.printStackTrace();
		}
		catch (Exception e) {
			test.log(LogStatus.ERROR, "Exception in ON_Switch()");
			e.printStackTrace();
		}
	}

	public void OFF_Switch(String switch_To_OFF) {
		try {
			minWait();
			aDriver.findElementByXPath("//*[contains(@text,'"+switch_To_OFF+"')]/../..//*[@text='ON']").click();
			minWait();
		} catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.ERROR, "Error in the locators->OFF_Switch()");
			e.printStackTrace();
		}
		catch (Exception e) {
			test.log(LogStatus.ERROR, "Exception in OFF_Switch() ");
			e.printStackTrace();
		}
	}
	
	public void selectSSIDwifi() throws InterruptedException {
		/*
		 * Select IDC wifi which is available
		 */
		SoftAssert Sa = new SoftAssert();
		try {
			customWait(4000);
			for(int i=1; i<=50; i++) {
				if(isElementExist(Locators_Stability.wifi_IDC)) {
					customWait(2000);
					clickBtn(Locators_Stability.wifi_IDC);
					APP_LOGS.info("IDC available secured wifi is Selected");
					break;
				}
				else if(isElementExist(Locators_Stability.wifi_Dellas)) {
					minWait();
					clickBtn(Locators_Stability.wifi_Dellas);
					APP_LOGS.info("Dellas available secured wifi is Selected");
					break;
				}
				else if(isElementExist(Locators_Stability.wifi_Cannada)) {
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
		} catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.ERROR, "Error in the locators->selectSSIDwifi()");
			e.printStackTrace();
		}
		catch (Exception e) {
			test.log(LogStatus.ERROR, "Exception in selectSSIDwifi() ");
			e.printStackTrace();
		}
	}
	
	public void enterPassword() throws InterruptedException, IOException {
		/*
		 * enter Password for SSID
		 */
		try {
			minWait();
			if(isElementExist(Locators_Stability.SSIDTxt)) {
			String getSSIDTitle = Locators_Stability.SSIDTxt.getText();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
			minWait();
			changeToNumberMode();
			customWait(2000);
			if(getSSIDTitle.equalsIgnoreCase("IDCSONWAP")) {
				minWait();
				clickBtn(Locators_Stability.wifi_IDC_Psswd);
				customWait(4000);
				//					enterTextToInputField(Locators_Stability.wifi_IDC_Psswd,"1dcS0n1md0tc0MbLr");
//				Runtime.getRuntime().exec("adb -s "+p_Id+" shell input text 1dcS0n1md0tc0MbLr");		
				passwordEnter();
				customWait(3000);	
			}
			minWait();
			String psswrd = Locators_Stability.wifi_IDC_Psswd.getText();
			System.out.println(psswrd);
			customWait(1000);
			clickBtn(Locators_Stability.wifi_IDC_ConnectBtn);
			customWait(3000);
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.ERROR, "Error in the locators->enterPassword()");
			e.printStackTrace();
		}
		catch (Exception e) {
			test.log(LogStatus.ERROR, "Exception in enterPassword() ");
			e.printStackTrace();
		}
	}
	
	public void changeToNumberMode() throws InterruptedException {

		try {
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
		} catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.ERROR, "Error in the locators->changeToNumberMode()");
			e.printStackTrace();
		}
		catch (Exception e) {
			test.log(LogStatus.ERROR, "Exception in changeToNumberMode() ");
			e.printStackTrace();
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
	
	//==================Stabilization for SMAT========================================//
	
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
		}catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.ERROR, "Error in the locators->clickBackButton()");
			e.printStackTrace();
		}
		catch (Exception e) {
			test.log(LogStatus.ERROR, "Exception in clickBackButton() ");
			e.printStackTrace();
		}
	}

}
