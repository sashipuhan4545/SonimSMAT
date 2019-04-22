package com.xp5S.util;

import java.io.IOException;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.testng.asserts.SoftAssert;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDeviceActionShortcuts;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidKeyCode;
import junit.framework.Assert;

public class ContactTransfer_Util extends BaseUtil {
	
	public SoftAssert softAssert;

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
	//public static AndroidDriver<AndroidElement> aDriver;
	public static AndroidDriver<MobileElement> mdriver;

	
	public void launch_application(WebElement app) throws InterruptedException{
		/*
		 * Launches sonim scout and navigates to supports tab..
		 */
		try {
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_HOME);
			minWait();
			clickBtn(app);
			APP_LOGS.info("Application launched succesfully");
			minWait();
			clickBtn(Locators_ContactTransfer.utilitiesTab);
			APP_LOGS.info("Clicked on Support tab succesfully");
			clickBtn(Locators_ContactTransfer.ContactTransferTitle);
			APP_LOGS.info("Clicked on Support tab succesfully");
//			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
		} catch (org.openqa.selenium.NoSuchElementException e) {
			APP_LOGS.info("No element found");
			e.printStackTrace();
			Assert.fail();
		}		
	}
	
	public void launchAppUpdater() throws InterruptedException {

		/*
		 * Launches scout application : Warranty Registration
		 */
		minWait();
		aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_RIGHT);
		minWait();
		aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
		minWait();		
		APP_LOGS.info("Launched App Updater application");
	}
	public void appPermissions() throws InterruptedException, IOException {
		try {
//			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
			for(int i=1; i<=3;i++) {
				if(isElementExist(Locators_ContactTransfer.AllowBtn));
				{
					clickBtn(Locators_ContactTransfer.AllowBtn);
					APP_LOGS.info("clicked Allow btn ");
					minWait();
				}
			}
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			minWait();
			Runtime.getRuntime().exec("adb shell input keyevent 4");
			APP_LOGS.info(" Got device permission");
			
		} catch (NoSuchElementException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Assert.fail();
		}
//		aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
	}
	
	
	public void select_Transfer_Mode(String mode) throws InterruptedException{
		/*
		 * Selects the mode of transfer in contact transfer screen.
		 * Pass mode as parameter.
		 */
		try {
			if(mode.equalsIgnoreCase("Bluetooth")){
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
				minWait();
				if(isElementExist(Locators_ContactTransfer.Allow_BT_Btn)) {
					aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_RIGHT);
					minWait();
					aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
					APP_LOGS.info("Selected BT option");
				}
			}else if(mode.equalsIgnoreCase("mdb")){
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
				minWait();
				APP_LOGS.info("Selected MBD option");
			}else if(mode.equalsIgnoreCase("vcf")){
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
				minWait();
				APP_LOGS.info("Selected VCF option");
			}else if(mode.equalsIgnoreCase("csv")){
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
				minWait();
				APP_LOGS.info("Selected CSV option");
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {
			APP_LOGS.info("No element found");
			e.printStackTrace();
			Assert.fail();
		}
	}
	
	public void select_folder(WebElement folder ) throws InterruptedException{
		/*
		 * Scrolls to and selectes the folder to be selected
		 * Pass webelement to be selected as parameter
		 * This is a reusable method and it can be reused for each mode of transfer.
		 * 		 
		 * Need to create locators of the folder if required file is kept in different locations(folder), it depends on the 
		 * user requirement or test case. ---- Delete this line of comment once completed.
		*/
		try {         	
			for (int iter = 1; iter <= 14; iter++) {
				if (isElementExist(folder)) {
					String getFolderName = folder.getText();
					APP_LOGS.info("Searched folder is found sucessfully");
					APP_LOGS.info("Searched folder name: "+getFolderName);
					minWait();
					clickBtn(folder);
					APP_LOGS.info(getFolderName+" : Folder is selected succesfully.");
					minWait();					
					break;					
				} else {					
					aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
					continue;					
				}
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {
			APP_LOGS.info("No element found");
			e.printStackTrace();
		}					
	}
	
	public void validate_Current_Screen(WebElement contactTransferTitle, String titleText) throws InterruptedException{
		/*
		 * Validates the current screen.
		 * Pass the element to be validated as parameter.
		 */
		SoftAssert sf = new SoftAssert();
		try {
			if(isElementExist(Locators_ContactTransfer.ContactTransferTitle)){
				check = true;
				String getTitleText = contactTransferTitle.getText();
				APP_LOGS.info("Current Screen is verified succesfully.");
				APP_LOGS.info("Current Screen title: "+getTitleText);
				sf.assertTrue(check, "Validation is Pass");
			}else{
				APP_LOGS.info("Testcase failed");
				sf.fail();
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {
			APP_LOGS.info("No element found");
			e.printStackTrace();
			sf.fail();
		}	
		sf.assertAll();
	}	
	
	public void validate_Default_TransferMode() throws InterruptedException {
		/*
		 * Check the mode of transfer in contact transfer screen.
		 * validate default mode
		 */
		SoftAssert sf = new SoftAssert();
		try {		
//			minWait();
//			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
			if(!(Locators_ContactTransfer.BtRadioBtn).isSelected()){
				check1=true;
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);	
			}
			if(!(Locators_ContactTransfer.MDBRadioBtn).isSelected()){
				check2=true;
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);	
			}
			if(!(Locators_ContactTransfer.VCFRadioBtn).isSelected()){
				check3=true;
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);	
			}
			if(!(Locators_ContactTransfer.CSVRadioBtn).isSelected()){
				check4=true;
				minWait();
			}
			
			if((check1)&&(check2)&&(check3)&&(check4)) {
				check= true;
				APP_LOGS.info("Default No selected Transfer Mode verified");
				sf.assertTrue(check, "Validation is Pass");
			} else {
				APP_LOGS.info(check1+"\n"+": "+check2+"\n"+": "+check3+"\n"+": "+check4+ "\n");
				sf.fail();
			}	
		} catch (org.openqa.selenium.NoSuchElementException e) {
			APP_LOGS.info("No element found");
			e.printStackTrace();
			sf.fail();
		}
		sf.assertAll();
	}
	

	public void selectMoreOptions() throws InterruptedException {
		/*
		 * Selects more options
		 */
		try {
			minWait();
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_MENU);
			minWait();
			clickBtn(Locators_ContactTransfer.AbtContactTransfer);
		} catch (org.openqa.selenium.NoSuchElementException e) {
			APP_LOGS.info("No element found");
			e.printStackTrace();
		}
	}

	public void validateAboutText()	{
		/*
		 * check the String present in About Option
		 */
		SoftAssert sf = new SoftAssert();
		try {
			String AboutInfo=Locators_ContactTransfer.About_CT_Text.getText();
			//System.out.println(AboutInfo);
				if(AboutInfo.contains("All rights reserved")) {
					check= true;
					APP_LOGS.info("Default No selected Transfer Mode verified");
					sf.assertTrue(check, "Validation is pass");
				} else{
					//APP_LOGS.info(check1+"\n"+": "+check2+"\n"+": "+check3+"\n"+": "+check4+ "\n");
					sf.fail();
				}	
				clickBtn(Locators_ContactTransfer.OKBtn_Abt);
		} catch (org.openqa.selenium.NoSuchElementException e) {
			APP_LOGS.info("No element found");
			e.printStackTrace();
			sf.fail();
		}
		sf.assertAll();
	}

	public void validateTransferModesPresent() throws InterruptedException {
		/*
		 * Validating by elements for all transfer modes present 
		 */
		SoftAssert sf = new SoftAssert();
		try {	
//			minWait();
//		    aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
//			minWait();
			if(isElementExist(Locators_ContactTransfer.BluetoothOptn)){
				check1=true;
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
				minWait();
			} if(isElementExist(Locators_ContactTransfer.VCFOptn)){
				check2=true;
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
				minWait();
			} if(isElementExist(Locators_ContactTransfer.MDBOptn)){
				check3=true;
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
				minWait();
			} if(isElementExist(Locators_ContactTransfer.CSVOptn)){
				check4=true;
			}
				if((check1)&&(check2)&&(check3)&&(check4)) {
					check= true;
					APP_LOGS.info("All Transfer Mode Present");
					sf.assertTrue(check, "Validation is Pass");
				}
				else{
					APP_LOGS.info(check1+"\n"+": "+check2+"\n"+": "+check3+"\n"+": "+check4+ "\n");
					sf.fail();
				}		
		}catch (NoSuchElementException e) {
			APP_LOGS.info("Error: Element not found.");
			e.printStackTrace();
			sf.fail();
		}
		sf.assertAll();
	}
	
	
	public void selectContactfileToImport(int num) throws InterruptedException, IOException {
		/*
		 * Select file and choose all contact to import
		 */
		try {
			/*minWait();
			for(int i=1; i<=2;i++) {
				if(isElementExist(Locators_ContactTransfer.AllowBtn));
				{
					clickBtn(Locators_ContactTransfer.AllowBtn);
				}
			}*/
			minWait();
			for(int i=0; i<=1;i++) {
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
				//				Runtime.getRuntime().exec(" adb shell input keyevent 20");
				APP_LOGS.info(" Clicked Alarm folder");
			}
			///Select Alarms folder

			//			aDriver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().className(\"android.widget.ScrollView\")).scrollIntoView(new UiSelector().text(\"Download\"))");		
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_ENTER);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
			minWait();
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_ENTER);//Open Contacts file to be imported
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
			minWait();

			for(int i=0; i<=2;i++) {
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_UP);
				minWait();
			}minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_ENTER);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_LEFT);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_ENTER);//selects All option
		}
		catch (NoSuchElementException e) {
			APP_LOGS.info("Error: Element not found.");
			e.printStackTrace();
			Assert.fail();
		}	
	}
	
	public void selectContactfileToImport_SL(int num) throws InterruptedException {
		/*
		 * Select file and choose all contact to import
		 */
		try {
			minWait();
			for(int i=1; i<=2;i++) {
			if(isElementExist(Locators_ContactTransfer.AllowBtn));
			{
				clickBtn(Locators_ContactTransfer.AllowBtn);
			}
			}
			for(int i=0; i<=5;i++) {
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
			}//Select Download folder
			for(int i=0; i<=1;i++) {
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_ENTER);
			minWait();
			}
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
			minWait();
			for(int i=0; i<=2;i++) {
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_UP);
				minWait();
			}minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_ENTER);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_LEFT);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_ENTER);//selects All option
		} catch (org.openqa.selenium.NoSuchElementException e) {
			APP_LOGS.info("No element found");
			e.printStackTrace();
		}
	}

	public void validateAllContactImport(String AdbTxt, String mode) throws InterruptedException, IOException {
		/*
		 * String validate Imported summary
		 */
		SoftAssert S = new SoftAssert();
		customWait(7000);
		customWait(5000);
		check1 = searchString("Checkbox Imported Successfully",AdbTxt);
		  	minWait();
		  	if(check1==false) {
		  		launch_application(Locators_ContactTransfer.sonim_scout_AppLauncher);
				select_Transfer_Mode(mode);
				selectContactfileToImport(7);
		  	}
		if(check1) {
		  		check = true;
		  		APP_LOGS.info(" Contacts are imported");
		  		S.assertTrue(check, "Validation is Pass");
		  	} else {
		  		APP_LOGS.info("Contacts are not imported");
		  		S.fail();
		  	}
		 S.assertAll();
	}
	
	public void pressBackBtn() throws InterruptedException {
		for(int i=0;i<=3;i++) {
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			minWait();
		}
	}	

	public void validateNotificationBar() throws InterruptedException {
		/*
		 * Verify Notification present when the Import Contact is completed
		 */
		SoftAssert sf = new SoftAssert();
		try {
			minWait();
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_HOME);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_MENU);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
			minWait();
			minWait();
				if(isElementExist(Locators_ContactTransfer.NotifctnTitle)) {
					check= true;
					APP_LOGS.info("Contact Imported at Notification is verified");
					sf.assertTrue(check, "Validation is Pass");
				}else{
					APP_LOGS.info("Contact Imported at Notification is unverified");
					sf.fail();
				}
		} catch (org.openqa.selenium.NoSuchElementException e) {
			APP_LOGS.info("No element found");
			e.printStackTrace();
			sf.fail();
		}
		sf.assertAll();
	}
	
	public void validateCancelImport(String adblog) throws InterruptedException {
		/*
		 * Verify the cancel functionality while importing contacts
		 */
		SoftAssert Sa = new SoftAssert();
		try {
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_ENTER);
			minWait();
			check1 = searchString(" Stop the Service",adblog);
		  	if(check1) {
		  		check = true;
		  		APP_LOGS.info("Contacts importing cancel is verified");
		  		Sa.assertTrue(check, "Validation is Pass");
		  	} else {
		  		APP_LOGS.info("Contacts importing cancel is unverified");
		  		Sa.fail();
		  	}

		}catch (NoSuchElementException e) {
			APP_LOGS.info("Error: Element not found.");
			e.printStackTrace();
			Sa.fail();
		}	
		Sa.assertAll();
	}
	
	public void DisableFile(String file, WebElement mode, int num) throws InterruptedException {
		/*
		 * Validate Disable option when same mode of transfer file is choosen
		 */
		SoftAssert sf = new SoftAssert();
		try {
			select_Transfer_Mode(file);
			minWait();
			minWait();
				for(int i=1; i<=2;i++) {
					if(isElementExist(Locators_ContactTransfer.AllowBtn)) {
						clickBtn(Locators_ContactTransfer.AllowBtn);
					}
				}
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_MENU);
			for(int i=0; i<=num; i++) {
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
			}
				if((mode).isSelected()){
					check=true;
					APP_LOGS.info("Verified Import option is disabled "+ file);
					sf.assertTrue(check, "Validation is Pass");
				}
				else {
					APP_LOGS.info("Import option is not disabled "+ file);
					sf.fail();
				}
		} catch (org.openqa.selenium.NoSuchElementException e) {
			APP_LOGS.info("No element found");
			e.printStackTrace();
			sf.fail();
		}
		sf.assertAll();
	}
	
	
	public void enterSearchField() throws InterruptedException, IOException {
		/*
		 *  Scrolls to and selectes the Download folder to be selected
		 */
		try {
			minWait();
		for(int i=0; i<=4;i++) {
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
		}//Select Download folder
		for(int i=0; i<=1;i++) {
		aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_ENTER);
		minWait();
		}
		aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
			
//			clickBtn(Locators_ContactTransfer.DownloadOptn);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_ENTER);//Selects File
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
			minWait();
			((AndroidDeviceActionShortcuts) aDriver).pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_LEFT);
			minWait();
		}catch (org.openqa.selenium.NoSuchElementException e) {
			APP_LOGS.info("Error: Element not found.");
			e.printStackTrace();
		}	
	}

	public void validateFewImportContact() throws InterruptedException {
		  /*
		   * validate selected contact imported 
		   */
		  SoftAssert sf = new SoftAssert();
		  try {
		   minWait();
		   for(int i=0; i<=1;i++) {
		    minWait();
		    aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
		    //    Runtime.getRuntime().exec(" adb shell input keyevent 20");
		    APP_LOGS.info(" Clicked Alarm folder");
		   }
		   aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_ENTER);
		   minWait();
		   aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
		   minWait();
		   minWait();
		   aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_ENTER);//Open Contacts file to be imported
		   minWait();
		   for(int i=0; i<=1;i++) {
		    aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
		    minWait();
		    aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
		   }
		   aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_LEFT);
		   minWait();
		   aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_ENTER);
		   minWait();
		   check = searchString("Checkbox Imported Successfully","ContactTransfer_AdbLog");
		   if(check) {   
		    check=true;
		    APP_LOGS.info("Verified Import few contacts");
		    sf.assertTrue(check, "Validation is Pass");
		   }
		   else {
		    APP_LOGS.info("Import few contacts is unsuccessful");
		    sf.fail();
		   }
		  }catch (org.openqa.selenium.NoSuchElementException e) {
		   APP_LOGS.info("Error: Element not found.");
		   e.printStackTrace();
		   sf.fail();
		  } 
		 }
		 
	
	public void validateUFMIFormat() throws InterruptedException {
		/*
		 * Validate UFMI format contact can be imported
		 */
		SoftAssert sf = new SoftAssert();
		try {
			for(int i=0; i<=1;i++) {
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
			}
			//Select Alarams folder
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_ENTER);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_ENTER);
			minWait();
			//Select few contacts 
			for(int i=0; i<=4;i++) {
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
			}
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_LEFT);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_ENTER);
			minWait();
	
			String UFMI_Num=Locators_ContactTransfer.UFMI_Search.getText();
			System.out.println(UFMI_Num);
			if(UFMI_Num.contains("*")) {
				check= true;
				APP_LOGS.info("UFMI Search and Contact Imported verified");
				sf.assertTrue(check, "Validation is Pass");
			}else{
				APP_LOGS.info("\"UFMI Search and Contact Importedis not completed");
				sf.fail();
			}
			clickBtn(Locators_ContactTransfer.DoneOptn);
		}catch (org.openqa.selenium.NoSuchElementException e) {
			APP_LOGS.info("Error: Element not found.");
			e.printStackTrace();
			sf.fail();
		}	
		sf.assertAll();
	}
	
	
	public void validateUFMIFormat_SL() throws InterruptedException {
		/*
		 * Validate UFMI format contact can be imported
		 */
//		aDriver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().className(\"android.widget.TextView\")).scrollIntoView(new UiSelector().text(\"Download\"))");
		SoftAssert sf = new SoftAssert();
		try {
			for(int i=0; i<=5;i++) {
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
			}//Select Download folder
			
			for(int i=0; i<=1;i++) {
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
			minWait();
			}//Select few contacts 
			for(int i=0; i<=4;i++) {
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
			}
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_LEFT);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_ENTER);
			minWait();
	
			String UFMI_Num=Locators_ContactTransfer.UFMI_Search.getText();
			System.out.println(UFMI_Num);
			if(UFMI_Num.contains("*")) {
				check= true;
				APP_LOGS.info("UFMI Search and Contact Imported verified");
				sf.assertTrue(check, "Validation ia pass");
			}else{
				APP_LOGS.info("\"UFMI Search and Contact Importedis not completed");
				sf.fail();
			}
			clickBtn(Locators_ContactTransfer.DoneOptn);
		}catch (org.openqa.selenium.NoSuchElementException e) {
			APP_LOGS.info("Error: Element not found.");
			e.printStackTrace();
			sf.fail();
		}	
		sf.assertAll();
	}
	
	
	public void validateBrowse_Title(WebElement title, String BrowseTitle,String mode) throws InterruptedException {
		/*
		 * After selecting mode of transfer validate directed Browse file Title
		 */
		SoftAssert sf = new SoftAssert();
		try {
			if(mode.equalsIgnoreCase("Bluetooth")){
				customWait(8000);
				customWait(4000);		
				customWait(8000);
			}
			customWait(4000);	
			String s=title.getText();
			System.out.println(s);
				if(s.equalsIgnoreCase(BrowseTitle)) {
					check= true;
					APP_LOGS.info("verified Browse Title Contact Imported "+ title);
					sf.assertTrue(check, "Validation is Pass");
				}else{
					APP_LOGS.info("Unverified Browse Title Contact Imported "+ title);
					sf.fail();
				}	
				clickBtn(Locators_ContactTransfer.DoneOptn);
		}catch (org.openqa.selenium.NoSuchElementException e) {
			APP_LOGS.info("Error: Element not found.");
			e.printStackTrace();
			sf.fail();
		}	
		sf.assertAll();
	}
	
	public void validateImportSummaryPageAfterReopen() throws InterruptedException {
		/*
		 * Verify Import Complete Summary Screen launching the App from Notification Bar 
		 */
		SoftAssert sf = new SoftAssert();
		try {
			minWait();
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_HOME);
			minWait();
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_MENU);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
			minWait();
			minWait();
			customWait(4000);
			aDriver.pressKeyCode(AndroidKeyCode.ENTER);
			minWait();
			if(isElementExist(Locators_ContactTransfer.Done_Btn)) {
				check= true;
				APP_LOGS.info("Summary Imported Contact screen is Opened");
				sf.assertTrue(check, "Validation is Pass");
			}else{
				APP_LOGS.info("Summary Imported Contact screen is not opened");
				sf.fail();
			}
		}catch (org.openqa.selenium.NoSuchElementException e) {
			APP_LOGS.info("Error: Element not found.");
			e.printStackTrace();
			sf.fail();
		}	
		sf.assertAll();
	}
	
	public void validateDeafaultModeMoreOptions() throws InterruptedException {
		/*
		 * check the default state of More options
		 */
		SoftAssert sf = new SoftAssert();
		try {
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_MENU);		
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
			minWait();
			if((Locators_ContactTransfer.ImportBTOptn).isEnabled()) {
				check1 = true;
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
			}
			if((Locators_ContactTransfer.ImportMBDOptn).isEnabled()) {
				check2 = true;
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
			}
			if((Locators_ContactTransfer.ImportVCFOptn).isEnabled()) {
				check3 = true;
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
			}
			if((Locators_ContactTransfer.ImportCSVOptn).isEnabled()) {
				check4 = true;
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
			}

				if((check1)&&(check2)&&(check3)&&(check4)){
					check=true;
					APP_LOGS.info("Verified Default Mode of MoreOptions");
					sf.assertTrue(check, "Validation is Pass");
				}
				else {
					APP_LOGS.info("Verified Default Mode of MoreOptions");
					sf.fail();
				}
		}catch (org.openqa.selenium.NoSuchElementException e) {
			APP_LOGS.info("Error: Element not found.");
			e.printStackTrace();
			sf.fail();
		}	
		sf.assertAll();
	}
		
}
