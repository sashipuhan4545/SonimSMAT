package com.xp8.util;

import static org.testng.Assert.assertTrue;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.testng.asserts.SoftAssert;

import com.graphics.gui.JsonFileReaderAndWriter;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.xp5S.util.CommonConfig;

import application.AllQA;
import io.appium.java_client.android.AndroidKeyCode;
import junit.framework.Assert;


public class XP8_SCOUT_Contact_Transfer_Util extends BaseUtil{

	public static ExtentReports extent;
	public static ExtentTest test;

	public Process p;

	boolean value = false;
	public  String p_Id	 = "";								// Primary Device Serial Number.
	public String r_Id	 = "";								// Reference Device Serial Number.
	public String p_b_No = "";			   					// Primary Device Build Number.
	public String r_b_No = ""; 								// Reference Device Build Number.
	public String pryNum = AllQA.PRIMARYDEVMDN;	// Reference Device MDN. 
	public String refNum = AllQA.REFERENCEDEVMDN;	// Reference Device MDN.


	public boolean check = false;
	public boolean check1 = false;
	public boolean check2 = false;
	public boolean check3 = false;
	public boolean check4 = false;
	public boolean check5 = false;


	
	public void fetch_Devices_Details() throws InterruptedException, FileNotFoundException, IOException, ParseException {

		minWait();
		p_Id=JsonFileReaderAndWriter.primaryDevIdReader();
		r_Id=JsonFileReaderAndWriter.ReadRefDeviceId();
		p_b_No=JsonFileReaderAndWriter.primaryDevFirmwareReader();
		r_b_No=JsonFileReaderAndWriter.ReadRefDeviceFirmWare();
	}

	public void preconditionSetUp() {
		/*
		 * Preconditions disable cellular data,disable wifi,clear app storage,change to test mode and enable wifi
		 */
		try {
			deleteDirectory();
			customWait(3000);
			pushFiles();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void pushFiles() {
		/*
		 * delete pushed files in device
		 */
		try {
			customWait(2000);
			Runtime.getRuntime().exec("adb -s "+ p_Id +" shell mkdir /storage/emulated/0/Download");
			customWait(2000);
			System.out.println("created folder");
			Runtime.getRuntime().exec("adb -s "+ p_Id +" push src/test/resources/StorageFile/i475.csv /storage/emulated/0/Download");
			Runtime.getRuntime().exec("adb -s "+ p_Id +" push src/test/resources/StorageFile/i475.mdb /storage/emulated/0/Download");
			Runtime.getRuntime().exec("adb -s "+ p_Id +" push src/test/resources/StorageFile/i475.vcf /storage/emulated/0/Download");
			Runtime.getRuntime().exec("adb -s "+ p_Id +" push src/test/resources/StorageFile/i476.vcf /storage/emulated/0/Download");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void Launch_Contact_Transfer(WebElement app) throws InterruptedException, IOException{
		/*
		 * Launch's contact transfer application
		 */
		SoftAssert sf = new SoftAssert();
		try {
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_HOME);
			minWait();
			XP8_Locators_ContactTransfer.AppListIcon.click();
			minWait();
			scroll();
			minWait();
			scroll();
			minWait();
			clickBtn(app);
			APP_LOGS.info("Application launched succesfully");
			minWait();
			clickBtn(XP8_Locators_ContactTransfer.utilitiesTab);
			minWait();
			clickBtn(XP8_Locators_ContactTransfer.ContactTransferApp);
			minWait();
		} catch (org.openqa.selenium.NoSuchElementException e) {
			e.printStackTrace();
			sf.fail();
		}	
		sf.assertAll();
	}	

	public void validateLaunchScoutApp(WebElement appTitle,String appName) throws InterruptedException {
		/*
		 * validate Scout APps Launch
		 */

		try {
			if(isElementExist(appTitle)) {
				check = true;
				customWait(1000);
				clickBackButton(1);
				test.log(LogStatus.INFO, appName+" is launched and verified ");
				assertTrue(check);
				if(appName.equals("SetUpWizard")) {
					clickBtn(XP8_Locators_ContactTransfer.HomeBtn_Wizard);
				}
				test.log(LogStatus.PASS, "Test case status is Passed");
			}
			else {
				test.log(LogStatus.ERROR, appName+" launch is unverified");
				test.log(LogStatus.FAIL, "Test case status is Failed");
				Assert.fail();
			}
			clickBackButton(2);
		} catch (Exception e) {
			// TODO Auto-generated catch block
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
				minWait();
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void validateTransferModesPresent() throws InterruptedException {
		/*
		 * Validating by elements for all transfer modes present 
		 */
		SoftAssert sf = new SoftAssert();
		try {	
			if(isElementExist(XP8_Locators_ContactTransfer.BluetoothOptn)){
				check1=true;
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
				minWait();
			} if(isElementExist(XP8_Locators_ContactTransfer.VCFOptn)){
				check2=true;
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
				minWait();
			} if(isElementExist(XP8_Locators_ContactTransfer.MDBOptn)){
				check3=true;
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
				minWait();
			} if(isElementExist(XP8_Locators_ContactTransfer.CSVOptn)){
				check4=true;
			}
			if((check1)&&(check2)&&(check3)&&(check4)) {
				check= true;
				APP_LOGS.info("All Transfer Mode Present");
				sf.assertTrue(check, "Validation is Pass");
				test.log(LogStatus.INFO,"All Transfer Modes are Present");
				test.log(LogStatus.PASS, "Test case status is Passed");
			}
			else{
				APP_LOGS.info(check1+"\n"+": "+check2+"\n"+": "+check3+"\n"+": "+check4+ "\n");
				test.log(LogStatus.FAIL, "Test case status is Failed");
				sf.fail();
			}	
			clickBackButton(2);
		}catch (NoSuchElementException e) {
			APP_LOGS.info("Error: Element not found.");
			e.printStackTrace();
			sf.fail();
		}
		sf.assertAll();
	}

	public void selectContactfileToImport() throws InterruptedException, IOException {
		/*
		 * Select file and choose all contact to import
		 */
		try {
			minWait();
			for(int i=0; i<4;i++) {
				minWait();
				clickDownButton(1);
				APP_LOGS.info(" Clicked Download folder");
			}
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_ENTER);
			minWait();
			
			clickDownButton(1);
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_ENTER);//Open Contacts file to be imported
			minWait();
			clickDownButton(1);
			minWait();
			for(int i=0; i<=2;i++) {
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_UP);
				minWait();
			}minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_ENTER);
			minWait();
			clickDownButton(1);
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_ENTER);
			
		}
		catch (NoSuchElementException e) {
			APP_LOGS.info("Error: Element not found.");
			e.printStackTrace();
			Assert.fail();
		}	
	}

	public void selectUFMIfileToImport() throws InterruptedException, IOException {
		/*
		 * Select file and choose all contact to import
		 */
		try {
			minWait();
			for(int i=0; i<4;i++) {
				minWait();
				clickDownButton(1);
				APP_LOGS.info(" Clicked Download folder");
			}
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_ENTER);
			minWait();
			
			clickDownButton(2);
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_ENTER);//Open Contacts file to be imported
			minWait();
			clickDownButton(1);
			minWait();
			for(int i=0; i<=2;i++) {
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_UP);
				minWait();
			}minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_ENTER);
			minWait();
			clickDownButton(1);
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_ENTER);
			
		}
		catch (NoSuchElementException e) {
			APP_LOGS.info("Error: Element not found.");
			e.printStackTrace();
			Assert.fail();
		}	
	}

	
	public void select_Transfer_Mode(String mode) throws InterruptedException{
		/*
		 * Selects the mode of transfer in contact transfer screen.
		 * Pass mode as parameter.
		 */
		try {
			if(mode.equalsIgnoreCase("Bluetooth")){
				minWait();
				clickDownButton(1);
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
				minWait();
				APP_LOGS.info("Selected BT option");
			}else if(mode.equalsIgnoreCase("mdb")){
				minWait();
				clickDownButton(2);
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
				minWait();
				APP_LOGS.info("Selected MBD option");
			}else if(mode.equalsIgnoreCase("vcf")){
				minWait();
				clickDownButton(3);
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
				minWait();
				APP_LOGS.info("Selected VCF option");
			}else if(mode.equalsIgnoreCase("csv")){
				minWait();
				clickDownButton(4);
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
				minWait();
				APP_LOGS.info("Selected CSV option");
			}

			if(isElementExist(XP8_Locators_ContactTransfer.permission_pop_up)) {
				for(int i=0;i<2;i++) {
					minWait();
					clickBtn(XP8_Locators_ContactTransfer.allow_btn);
					minWait();
				}
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
			}



		} catch (org.openqa.selenium.NoSuchElementException e) {
			APP_LOGS.info("No element found");
			e.printStackTrace();
			Assert.fail();
		}
	}

	public static void deleteDirectory() throws IOException, InterruptedException, ParseException
	{
		String Uid=JsonFileReaderAndWriter.primaryDevIdReader();
		System.out.println(Uid);
		customWait(2000);	
		Runtime.getRuntime().exec("adb -s "+Uid+" shell rm -r /storage/emulated/0/Download");  
		System.out.println("Deleted Memory");
		customWait(6000);

	/*	Process p1 = Runtime.getRuntime().exec("adb -s "+Uid+" uninstall fillememory.myapplication");
		customWait(2000);	*/
	}


	public void clickDownButton(int number)
	{
		/*
		 * clicks on down button with iteration as user input
		 */
		try {
			for(int i=0;i<number;i++){
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
				minWait();
			}
		}catch (Exception e) {
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
		if(check1) {
			check = true;
			S.assertTrue(check, "Validation is Pass");
			APP_LOGS.info("Contacts are imported via "+mode);
			test.log(LogStatus.INFO,"Contacts are imported via "+mode);
			test.log(LogStatus.PASS, "Test case status is Passed");
		}else {
			APP_LOGS.info("Failed to add "+mode+" contacts");
			test.log(LogStatus.ERROR,"Failed to add "+mode+" contacts");
			test.log(LogStatus.FAIL, "Test case status is Failed");
		}
		S.assertAll();
	}

	public void validateUFMIContactImport(String AdbTxt, String mode) throws InterruptedException, IOException {
		/*
		 * String validate Imported summary
		 */
		SoftAssert S = new SoftAssert();
		customWait(7000);
		customWait(5000);
		check1 = searchString("Checkbox Imported Successfully",AdbTxt);
		minWait();
		System.out.println(check1);
		if(check1) {
			check = true;
			S.assertTrue(check, "Validation is Pass");
			APP_LOGS.info("UFMI Contacts imported successfully");
			test.log(LogStatus.INFO,"UFMI Contacts imported successfully");
			test.log(LogStatus.PASS, "Test case status is Passed");
		} else {
			APP_LOGS.info("Failed to add UFMI contacts");
			test.log(LogStatus.ERROR,"Failed to add UFMI contacts");
			test.log(LogStatus.FAIL, "Test case status is Failed");
		}
		S.assertAll();
	}
	
	public void push_to_background_and_verify() throws InterruptedException, IOException{
		/*
		 * Push Contact Transfer to background,launch messaging and validate
		 */
		SoftAssert sf = new SoftAssert();
		try {
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_HOME);
			minWait();
			launch_an_app("messaging");
			if(isElementExist(XP8_Locators_ContactTransfer.permission_pop_up)) {
				for(int i=0;i<2;i++) {
					minWait();
					clickBtn(XP8_Locators_ContactTransfer.allow_btn);
					minWait();
				}
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
			}
			customWait(3000);
			validate_launchofApp("Messaging");
		} catch (org.openqa.selenium.NoSuchElementException e) {
			e.printStackTrace();
			sf.fail();
		}	
		sf.assertAll();
	}	
	
	
	public void validate_launchofApp(String elementName) throws InterruptedException {
		/*
		 * Launch's and validates launched app
		 */
		SoftAssert Sa = new SoftAssert();
		try {
			customWait(6000);
			takeScreenShotForOcrPngFormat("Messaging");
			Thread.sleep(2000);
			OCR.my_main.validate_Using_OCR("Messaging.png");
			customWait(2000);
			Boolean value = searchStringOCR("Messages", "ocr");
			if(value) {
				check = true;
				APP_LOGS.info("Pushed Contact Transfer application to background and "+elementName + " is found succesfully");
				test.log(LogStatus.INFO, "Pushed Contact Transfer application to background and "+elementName + " Application sucessfully Launched ");
				Sa.assertTrue(check, "Tc Passed");
			} else {
				check = false;
				APP_LOGS.info("Failed to push Contact Transfer application to background");
				test.log(LogStatus.ERROR,"Failed to push Contact Transfer application to background");
				Sa.fail();
			}
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			APP_LOGS.info("No Such Element Found");
			test.log(LogStatus.ERROR,"No Such Element Found");
			Sa.fail();
		}
		Sa.assertAll();
	}
	
	public static void takeScreenShotForOcrPngFormat(String imageName) {

		File scrFile = ((TakesScreenshot) CommonConfig.getDriver()).getScreenshotAs(OutputType.FILE);
		try {
			
			String ocrImageFilePath = System.getProperty("user.dir") + "\\src\\test\\resources\\OCR_FILES\\" +imageName+".png";
			FileUtils.copyFile(scrFile, new File(ocrImageFilePath));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("<-----------Exeption occurs in takeScreenShotsForOcr method----------->");
		}
	}
	

	public void launch_from_background_and_verify() throws InterruptedException, IOException{
		/*
		 * Launch contact transfer from recent apps and validate
		 */
		SoftAssert sf = new SoftAssert();
		try {
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_HOME);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_APP_SWITCH);
			customWait(1500);
			if(!isElementExist(XP8_Locators_ContactTransfer.no_Recent_Items)) {
				if(isElementExist(XP8_Locators_ContactTransfer.ContactTransferTitle)) {
				//	aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
					clickBtn(XP8_Locators_ContactTransfer.ContactTransferTitle);
					customWait(2000);
					validateLaunchScoutApp(XP8_Locators_ContactTransfer.ContactTransferTitle, "Contact Transfer");
				}else {
					scrollToElementWithDpadUp(XP8_Locators_ContactTransfer.ContactTransferTitle);
				//	aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
					clickBtn(XP8_Locators_ContactTransfer.ContactTransferTitle);
					customWait(2000);
					validateLaunchScoutApp(XP8_Locators_ContactTransfer.ContactTransferTitle, "Contact Transfer");
				}
			}else {
				test.log(LogStatus.ERROR,"No Recent apps to launch");
				test.log(LogStatus.FAIL, "Test case status is Failed");
				sf.fail();
			}
			clickBackButton(2);
		} catch (org.openqa.selenium.NoSuchElementException e) {
			e.printStackTrace();
			sf.fail();
		}	
		sf.assertAll();
	}	
	
	public void scrollToElementWithDpadUp(WebElement element) {
		/*
		 * Clicks up button till element is available
		 */
		while(!isElementExist(element)) {
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_UP);
		}
	}
	
	public void validate_key_press() throws InterruptedException {
		/*
		 * validate press any key
		 */
		SoftAssert sf = new SoftAssert();
		try {
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_HOME);
			minWait();
			if(isElementExist(XP8_Locators_ContactTransfer.google_search_bar)) {
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_APP_SWITCH);
				customWait(1500);
				clickBtn(XP8_Locators_ContactTransfer.ContactTransferTitle);
				customWait(2000);
				takeScreenShotForOcrPngFormat("Contact Transfer");
				Thread.sleep(2000);
				OCR.my_main.validate_Using_OCR("Contact Transfer.png");
				customWait(2000);
				Boolean value = searchStringOCR("Importing contacts", "ocr");
				if(value) {
					check = true;
					APP_LOGS.info("Press any key while contacts transfer in progress validated successfully and contacts transfered succesfully");
					test.log(LogStatus.INFO, "Press any key while contacts transfer in progress validated successfully and contacts transfered succesfully");
					test.log(LogStatus.PASS, "Test case status is Passed");
					sf.assertTrue(check, "Tc Passed");
				}else {
					check = false;
					APP_LOGS.info("Failed to verify press any key when contacts in progress");
					test.log(LogStatus.ERROR, "Failed to verify press any key when contacts in progress");
					test.log(LogStatus.FAIL, "Test case status is Failed");
					sf.fail();
				}
			}
			clickBackButton(4);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void disableBluetooth() throws InterruptedException {
		/*
		 * Navigates to BT settings and disables Bluetooth
		 */
		try {
			clickBtn(XP8_Locators_ContactTransfer.connectedDevices_oreo);
			minWait();
			clickBtn(XP8_Locators_ContactTransfer.Bluetooth);
			minWait();
			if (isElementExist(XP8_Locators_ContactTransfer.enabled)) {
				customWait(2000);
				clickBtn(XP8_Locators_ContactTransfer.enabled);
				APP_LOGS.info("Feature is disabled");
				minWait();
				clickBackButton(3);
			}else {
				clickBackButton(3);
			}
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "Disable toggle : No Such Element Found");
			Assert.fail();
		}
	}
	
	public void enable_BT_and_verify_from_CT() throws InterruptedException {
		/*
		 * Enables BT from Contact transfer and verify
		 */
		SoftAssert sf = new SoftAssert();
		try {
			customWait(4000);
			//clickBtn(Locators_ContactTransfer.Allow_BT_Btn);
			clickDownButton(1);
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_RIGHT);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
			minWait();
			if(isElementExist(XP8_Locators_ContactTransfer.searchingBT)) {
				check = true;
				test.log(LogStatus.INFO, "BT enabled from Contact Transfer succesfully");
				sf.assertTrue(check, "Tc Passed");
			}else{
				test.log(LogStatus.ERROR, "Failed to enable Bluetooth from contact transfer");
				sf.fail();
			}

		}
		catch (NoSuchElementException e) {
			e.printStackTrace();
			sf.fail();
		}
	}
	
	public void scan_for_BT_devices_verify() throws InterruptedException {
		/*
		 * Clicks and validates scan button
		 */
		SoftAssert sf = new SoftAssert();
		try {
			customWait(10000);
			if(isElementExist(XP8_Locators_ContactTransfer.scan_Button)) {
				clickBtn(XP8_Locators_ContactTransfer.scan_Button);
				customWait(3000);
				if(isElementExist(XP8_Locators_ContactTransfer.searchingBT)) {
					check = true;
					test.log(LogStatus.INFO, "Scan option verified successfully");
					test.log(LogStatus.PASS, "Test case status is Passed");
					sf.assertTrue(check, "Tc Passed");
				}else{
					test.log(LogStatus.ERROR, "Failed to verify scan option");
					test.log(LogStatus.FAIL, "Test case status is Failed");
					sf.fail();
				}
			}else {
				test.log(LogStatus.ERROR, "Scan button not enabled");
				test.log(LogStatus.FAIL, "Test case status is Failed");
				sf.fail();
			}
			clickBackButton(2);
		}
		catch (NoSuchElementException e) {
			e.printStackTrace();
			Assert.fail();
		}
	}
	
	public void deny_and_verify() throws InterruptedException {
		/*
		 * validates deny option 
		 */
		SoftAssert sf = new SoftAssert();
		try {
			takeScreenShotForOcrPngFormat("Contact Transfer");
			Thread.sleep(2000);
			OCR.my_main.validate_Using_OCR("Contact Transfer.png");
			customWait(2000);
			Boolean value1 = searchStringOCR("Contact Transfer wants to turn on", "ocr");
			Boolean value2 = searchStringOCR("Bluetooth", "ocr");
			if(value1 && value2) {
				clickDownButton(1);
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
				customWait(5000);
				takeScreenShotForOcrPngFormat("Contact Transfer");
				Thread.sleep(2000);
				OCR.my_main.validate_Using_OCR("Contact Transfer.png");
				customWait(2000);
				Boolean value3 = searchStringOCR("Bluetooth will need to be enabled to", "ocr");
				Boolean value4 = searchStringOCR("use the BT Transfer feature.", "ocr");
				minWait();
				if(value3 && value4) {
					clickDownButton(1);
					aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
					check = true;
					test.log(LogStatus.INFO, "Deny and ok option verified successfully");
					test.log(LogStatus.PASS, "Test case status is Passed");
					sf.assertTrue(check, "Tc Passed");
				}else{
					test.log(LogStatus.ERROR, "Failed to verify Deny option");
					test.log(LogStatus.FAIL, "Test case status is Failed");
					sf.fail();
				}
			}else {
				test.log(LogStatus.ERROR, "Enable BT pop up not available");
				test.log(LogStatus.FAIL, "Test case status is Failed");
				sf.fail();
			}
			clickBackButton(3);
		}
		catch (NoSuchElementException e) {
			e.printStackTrace();
			Assert.fail();
		}
	}
	
	public void validateSearchfield(String contactName) throws InterruptedException, IOException {
		/*
		 * validates search field
		 */
		SoftAssert sf = new SoftAssert();
		try {
			minWait();
			for(int i=0; i<4;i++) {
				minWait();
				clickDownButton(1);
				APP_LOGS.info(" Clicked Download folder");
			}
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_ENTER);
			clickDownButton(2);
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_ENTER);//Open Contacts file to be imported
			customWait(2000);
			//	enterTextToInputField(Locators_ContactTransfer.search_field, contactName);
			Runtime.getRuntime().exec("adb -s "+p_Id+" shell input text "+ contactName);
			customWait(2000);
			takeScreenShotForOcrPngFormat("Contact Transfer");
			Thread.sleep(2000);
			OCR.my_main.validate_Using_OCR("Contact Transfer.png");
			customWait(2000);
			Boolean value1 = searchStringOCR("SouthernL|NC", "ocr");
			Boolean value2 = searchStringOCR("Work1: 18004060151", "ocr");
			if(value1 || value2) {
				check = true;
				test.log(LogStatus.INFO, "Search field validated successfully");
				sf.assertTrue(check, "Tc Passed");
			}else{
				test.log(LogStatus.FAIL, "Failed to validate search field");
				sf.fail();
			}
			clickBackButton(2);
		}
		catch (NoSuchElementException e) {
			e.printStackTrace();
			Assert.fail();
		}
	}
	
	public void validateSelectAll() throws InterruptedException, IOException {
		/*
		 * validates select all option
		 */
		SoftAssert sf = new SoftAssert();
		try {
			for(int i=0; i<4;i++) {
				minWait();
				clickDownButton(1);
				APP_LOGS.info(" Clicked Download folder");
			}
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_ENTER);
			clickDownButton(1);
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_ENTER);//Open Contacts file to be imported
			minWait();
			clickDownButton(1);
			minWait();
			for(int i=0; i<=2;i++) {
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_UP);
				minWait();
			}minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_ENTER);
			minWait();
			takeScreenShotForOcrPngFormat("Contact Transfer");
			Thread.sleep(2000);
			OCR.my_main.validate_Using_OCR("Contact Transfer.png");
			customWait(2000);
			Boolean value = searchStringOCR("OK(5)", "ocr");
			if(value) {
				check = true;
				test.log(LogStatus.INFO, "Select all option validated successfully");
				test.log(LogStatus.PASS, "Test case status is Passed");
				sf.assertTrue(check, "Tc Passed");
			}else{
				test.log(LogStatus.FAIL, "Failed to validate select all option");
				test.log(LogStatus.FAIL, "Test case status is Failed");
				sf.fail();
			}
			clickBackButton(4);
		}
		catch (NoSuchElementException e) {
			e.printStackTrace();
			Assert.fail();
		}
	}
	
	public void verify_Notification_bar_when_import_in_progress() throws InterruptedException, IOException {
		/*
		 * verifies notification bar when contact transfer in progress
		 */
		SoftAssert sf = new SoftAssert();
		try {
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_HOME);
			minWait();
			aDriver.openNotifications();
			minWait();
			takeScreenShotForOcrPngFormat("Contact Transfer");
			Thread.sleep(2000);
			OCR.my_main.validate_Using_OCR("Contact Transfer.png");
			customWait(2000);
			Boolean value1 = searchStringOCR("Importing contact", "ocr");
			Boolean value2 = searchStringOCR("Status", "ocr");
			if(value1 && value2) {
				check = true;
				test.log(LogStatus.INFO, "Veirified when contact transfer in progress from notification bar successfully");
				sf.assertTrue(check, "Tc Passed");
			}else{
				test.log(LogStatus.ERROR, "Failed to Veirify when contact transfer in progress from notification bar");
				sf.fail();
			}
		}
		catch (NoSuchElementException e) {
			e.printStackTrace();
			Assert.fail();
		}
	}
	
	public void verify_succes_icon_in_Notification_bar() throws InterruptedException, IOException {
		/*
		 * verifies contact transfer success icon from notification bar
		 */
		SoftAssert sf = new SoftAssert();
		try {
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_HOME);
			minWait();
			aDriver.openNotifications();
			minWait();
			customWait(40000);
			takeScreenShotForOcrPngFormat("Contact Transfer");
			Thread.sleep(2000);
			OCR.my_main.validate_Using_OCR("Contact Transfer.png");
			customWait(2000);
			Boolean value1 = searchStringOCR("Completed contact import", "ocr");
			if(value1) {
				check = true;
				test.log(LogStatus.INFO, "Veirified contact import success from notification bar successfully");
				test.log(LogStatus.PASS, "Test case status is Passed");
				sf.assertTrue(check, "Tc Passed");
			}else{
				test.log(LogStatus.ERROR, "Failed to Veirify contact import success from notification bar");
				test.log(LogStatus.FAIL, "Test case status is Failed");
				sf.fail();
			}
		}
		catch (NoSuchElementException e) {
			e.printStackTrace();
			Assert.fail();
		}
	}
	
	public void deleteFiles() {
		/*
		 * Delete's pushed files
		 */
		try {
			customWait(2000);
			Runtime.getRuntime().exec("adb -s "+ p_Id +" shell rm -r /storage/emulated/0/Download/i475.csv");
			customWait(2000);
			Runtime.getRuntime().exec("adb -s "+ p_Id +" shell rm -r /storage/emulated/0/Download/i475.mdb");
			customWait(2000);
			Runtime.getRuntime().exec("adb -s "+ p_Id +" shell rm -r /storage/emulated/0/Download/i475.vcf");
			customWait(2000);
			Runtime.getRuntime().exec("adb -s "+ p_Id +" shell rm -r /storage/emulated/0/Download/i476.vcf");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	
	
	
	
	
}
