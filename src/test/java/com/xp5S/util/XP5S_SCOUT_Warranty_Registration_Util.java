package com.xp5S.util;

import static org.testng.Assert.assertTrue;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.testng.asserts.SoftAssert;

import com.graphics.gui.JsonFileReaderAndWriter;
import com.relevantcodes.extentreports.LogStatus;
import com.xp3.Utils.Locators_SonimCare;

import application.AllQA;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.AndroidKeyCode;
import junit.framework.Assert;


public class XP5S_SCOUT_Warranty_Registration_Util extends BaseUtil{

	public boolean check = false;
	public boolean check1 = false;
	public boolean check2 = false;
	public boolean check3 = false;
	public boolean check4 = false;
	public boolean check5 = false;

	public String p_Id	 = "";								// Primary Device Serial Number.
	public String r_Id	 = "";								// Reference Device Serial Number.
	public String p_b_No = "";			   					// Primary Device Build Number.
	public String r_b_No = ""; 								// Reference Device Build Number.
	public String pryNum = AllQA.PRIMARYDEVMDN;	// Reference Device MDN. 
	public String refNum = AllQA.REFERENCEDEVMDN;	// Reference Device MDN.
	//Added comment to test

	public void fetch_Devices_Details() throws InterruptedException, FileNotFoundException, IOException, org.json.simple.parser.ParseException {
		System.out.println("In Fetching");
		minWait();
		p_Id=JsonFileReaderAndWriter.primaryDevIdReader();
		r_Id=JsonFileReaderAndWriter.ReadRefDeviceId();
		p_b_No=JsonFileReaderAndWriter.primaryDevFirmwareReader();
		System.out.println(p_b_No);
		r_b_No=JsonFileReaderAndWriter.ReadRefDeviceFirmWare();
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


	public void preconditionSetUp() {
		/*
		 * Preconditions disable cellular data,disable wifi,clear app storage,change to test mode and enable wifi
		 */
		try {
			deleteExistingFiles();
			minWait();
			pushFiles();
			minWait();
			disableCellularData();
			minWait();
			disablewifi_changeMode_enablewifi();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void pushFiles() {
		/*
		 * delete pushed files in device
		 */
		try {
			Runtime.getRuntime().exec("adb -s "+ p_Id +" push src/test/resources/StorageFile/picture.jpg /storage/emulated/0/Download");
			Runtime.getRuntime().exec("adb -s "+ p_Id +" push src/test/resources/StorageFile/picture.jpeg /storage/emulated/0/Download");
			Runtime.getRuntime().exec("adb -s "+ p_Id +" push src/test/resources/StorageFile/picture.png /storage/emulated/0/Download");
			Runtime.getRuntime().exec("adb -s "+ p_Id +" push src/test/resources/StorageFile/sample.pdf /storage/emulated/0/Download");
			Runtime.getRuntime().exec("adb -s "+ p_Id +" push src/test/resources/StorageFile/sample.doc /storage/emulated/0/Download");
			Runtime.getRuntime().exec("adb -s "+ p_Id +" push src/test/resources/StorageFile/test.xlsx /storage/emulated/0/Download");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void deleteExistingFiles() {
		/*
		 * check and delete's existing files in download folder in File explorer
		 */
		try {
			launch_an_app("fileExplorer");
			customWait(2000);
			//clickDownButton(2);
			clickDownButtonruntime(3);
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
			clickDownButton(3);
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
			customWait(2000);
			takeScreenShotForOcrPngFormat("WarrantyReg");
			Thread.sleep(2000);
			OCR.my_main.validate_Using_OCR("WarrantyReg.png");
			customWait(2000);
			Boolean value1 = searchStringOCR("N0 items", "ocr");
			Boolean value2 = searchStringOCR("No items", "ocr");
			if(!(value1 || value2 )) {
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_MENU);
				minWait();
				scrollText("Select");
				clickBtn(Locators_WarrantyReg.select_option);
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_MENU);
				minWait();
				scrollText("Select all");
				clickBtn(Locators_WarrantyReg.Select_all_option);
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_MENU);
				minWait();
				scrollText("Delete");
				clickBtn(Locators_WarrantyReg.delete_option);
				minWait();
				clickBtn(Locators_WarrantyReg.SWR_OK_Btn);
			}else {
				clickBackButton(4);
			}
		} catch (Exception e) {
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
		catch(NoSuchElementException e) {
			return check;
			}
		}
	
	public void OFF_Switch(String switch_To_OFF) {
		try {
			minWait();
			aDriver.findElementByXPath("//*[contains(@text,'"+switch_To_OFF+"')]/../..//*[@text='ON']").click();
			minWait();
		} catch (Exception e) {
		}
	}
	
	public void disableCellularData() throws InterruptedException {
		/*
		 * disable cellular data
		 */
		try {
			launch_an_app("settings");
			clickBtn(Locators_WarrantyReg.networkAndInternet);
			minWait();
			if(isElementExist(Locators_WarrantyReg.MobileNetwrks_oreo)) {
				minWait();
				clickBtn(Locators_WarrantyReg.MobileNetwrks_oreo);
				APP_LOGS.info("Selected Mobile Networks");
				minWait();
				if(isElementExist(Locators_SonimCare.enabled_Data_Oreo)) {
					OFF_Switch("Mobile data");
				}
				//disableFeature(Locators_WarrantyReg.enabled_Data_Oreo,Locators_WarrantyReg.disabled_Data_Oreo);
				minWait();
				clickBtn(Locators_WarrantyReg.SWR_OK_Btn);
				minWait();
			}
			else {
				clickBtn(Locators_WarrantyReg.dataUsageOptn);
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
				minWait();
				if(isElementExist(Locators_SonimCare.enabled_Data_Oreo)) {
					OFF_Switch("Mobile data");
				}
			//	disableFeature(Locators_WarrantyReg.cellular_DataON,Locators_WarrantyReg.cellular_DataOFF);
				minWait();
				clickBtn(Locators_WarrantyReg.SWR_OK_Btn);
				minWait();
			}
			clickBackButton(3);
		} catch (org.openqa.selenium.NoSuchElementException e) {
			APP_LOGS.info("No Element found.");
			Assert.fail();
			minWait();	
		}

	}

	public void disablewifi_changeMode_enablewifi() throws InterruptedException, IOException {
		/*
		 * disable wifi,clear storage,change to test mode,enable wiFi
		 */
		SoftAssert sf = new SoftAssert();
		try {
			launch_an_app("settings");
			minWait();
			clickBtn(Locators_WarrantyReg.networkAndInternet);
			minWait();
			navigateToWifiOption();
			minWait();
			customWait(2000);
			disableFeature(Locators_WarrantyReg.enabled,Locators_WarrantyReg.disabled);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			clickBackButton(2);
			clearStorage();
			changeToTestMode();
			minWait();
			launch_an_app("settings");
			ConnectSecuredWifi();
			clickBackButton(2);	
		} catch (org.openqa.selenium.NoSuchElementException e) {
			APP_LOGS.info("No Element found.");
			sf.fail();
		}
		sf.assertAll();
	}

	public void clearStorage() {
		/*
		 * clear warranty app storage
		 */
		try {
			launch_an_app("settings");
			clickBtn(Locators_WarrantyReg.Apps_Notification);
			minWait();
			clickBtn(Locators_WarrantyReg.Apps_info);
			minWait();
			Runtime.getRuntime().exec("adb -s "+ p_Id +" shell input text warranty");
			customWait(2000);
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
			minWait();
			clickBtn(Locators_WarrantyReg.storage);
			minWait();
			clickBtn(Locators_WarrantyReg.cleardata);
			customWait(3000);
			clickBackButton(5);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void ConnectSecuredWifi() throws InterruptedException, IOException {
		/*
		 * Connect wifi with entering passkey
		 */
		try {
			clickBtn(Locators_WarrantyReg.networkAndInternet);
			customWait(2000);
			navigateToWifiOption();
			minWait();
			if(isElementExist(Locators_WarrantyReg.disabled)) {
				enableFeature(Locators_WarrantyReg.enabled,Locators_WarrantyReg.disabled,Locators_WarrantyReg.switch_Title);
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);		
				customWait(3000);
			}		
			else {
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);		    	
			}
			customWait(3000);
			String getSummary = Locators_WarrantyReg.summaryWIFI_oreo.getText();
			System.out.println(getSummary);
			minWait();
			if(getSummary.contains("Not connected")) {
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
				customWait(2000);
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
				customWait(2000);
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
				customWait(4000);
				scrollToText("IDCSONWAP");
				//	selectIDcwifi();      
				minWait();
				if(isElementExist(Locators_WarrantyReg.wifi_IDC_ForgetBtn)) {
					clickBtn(Locators_WarrantyReg.wifi_IDC_ForgetBtn);
					minWait();
					scrollToText("IDCSONWAP");
					//selectIDcwifi();    
				}
				clickBtn(Locators_WarrantyReg.wifi_show_Pswd);
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_UP);
				customWait(1000);

				changeToNumberMode_SMS(Locators_WarrantyReg.wifi_IDC_Psswd);
				customWait(9000);
				if(isElementExist(Locators_WarrantyReg.wifi_IDC)) {
					Runtime.getRuntime().exec("adb -s "+ p_Id +" shell input text 1");
					minWait();
					Runtime.getRuntime().exec("adb -s "+ p_Id +" shell input text dcS");
					minWait();
					Runtime.getRuntime().exec("adb -s "+ p_Id +" shell input text 0");
					minWait();
					Runtime.getRuntime().exec("adb -s "+ p_Id +" shell input text n");
					minWait();
					Runtime.getRuntime().exec("adb -s "+ p_Id +" shell input text 1");
					minWait();
					Runtime.getRuntime().exec("adb -s "+ p_Id +" shell input text md");
					minWait();
					Runtime.getRuntime().exec("adb -s "+ p_Id+" shell input text 0");
					minWait();
					Runtime.getRuntime().exec("adb -s "+ p_Id +" shell input text tc");
					minWait();
					Runtime.getRuntime().exec("adb -s "+ p_Id +" shell input text 0");
					minWait();
					Runtime.getRuntime().exec("adb -s "+ p_Id +" shell input text MbLr");
					minWait();
				}

				minWait();
				customWait(1000);
				String psswrd = Locators_WarrantyReg.wifi_IDC_Psswd.getText();
				System.out.println(psswrd);
				clickBtn(Locators_WarrantyReg.wifi_IDC_ConnectBtn);
				APP_LOGS.info("IDC available secured wifi is connected");
				customWait(4000);		    	
			}		    
		} 
		catch (NoSuchElementException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			test.log(LogStatus.ERROR, "NO Such Element Found");
			Assert.fail();
		}
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

	public void navigateToWifiOption() throws InterruptedException, IOException {
		/*
		 * navigate to wifi option
		 */	
		try {
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			test.setDescription("Failure Reason: Element not found");
			Assert.fail();
		}
	}
	
	public void scrollToElementWithDpadDown(AndroidElement element)
	{
		/*
		 * Clicks down button till element is available
		 */
		while(!isElementExist(element)){
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
		}
	}

	public void changeToTestMode() {
		/*
		 * change to test mode warranty server
		 */
		try {
			launch_an_app("phone");
			customWait(3000);
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_STAR);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_POUND);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_STAR);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_POUND);
			minWait();
			Runtime.getRuntime().exec("adb -s "+ p_Id +" shell input text 726888");
			customWait(2000);
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_POUND);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_STAR);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_POUND);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_STAR);
			clickDownButton(1);
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
			Locators_WarrantyReg.url_Field.clear();
			customWait(2000);
			enterTextToInputField(Locators_WarrantyReg.url_Field, "https://test.sonimcloud.com");
			customWait(2000);
			clickBtn(Locators_WarrantyReg.SWR_OK_Btn);
			clickBackButton(2);
			customWait(5000);
		} catch (Exception e) {
			e.printStackTrace();
		}

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
					clickBtn(Locators_WarrantyReg.HomeBtn_Wizard);
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

	public void clickDownButtonruntime(int number)
	{
		/*
		 * clicks on down button with runtime command with iteration as user input
		 */
		try {
			for(int i=0;i<number;i++){
				minWait();
				Runtime.getRuntime().exec("adb -s "+ p_Id +" shell input keyevent 20");
				minWait();
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void navigateApps(WebElement tab,WebElement app) throws InterruptedException, IOException {
		minWait();
		//Navigates to given element in SCOUT app
		try {
			customWait(2000);
			clickBtn(tab);
			customWait(2000);
			for(int i=1; i<=6; i++) {
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_UP);
			}
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
			for(int i=1; i<=6; i++) {
				if(isElementExist(app)) {
					clickBtn(app);
					break;
				}
				else {
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



	public void Launch_Warranty_Registration() throws InterruptedException, IOException{
		/*
		 * Launch's warranty registration application
		 */
		SoftAssert sf = new SoftAssert();
		try {
			launch_an_app("scout");
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_LEFT);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_LEFT);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_RIGHT);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_RIGHT);
			customWait(3000);
			navigateApps(Locators_WarrantyReg.supportTab,Locators_WarrantyReg.WarrantyReg);
			if(isElementExist(Locators_WarrantyReg.permission_pop_up)) {
				for(int i=0;i<3;i++) {
					minWait();
					clickBtn(Locators_WarrantyReg.allow_btn);
					minWait();
				}
			}
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
		} catch (org.openqa.selenium.NoSuchElementException e) {
			e.printStackTrace();
			sf.fail();
		}	
		sf.assertAll();
	}	

	public void validateNowOption() throws InterruptedException {
		/*
		 * validate now option 
		 */

		try {
			if(isElementExist(Locators_WarrantyReg.Now_Button)) {
				minWait();
				clickBtn(Locators_WarrantyReg.Now_Button);
				if(isElementExist(Locators_WarrantyReg.manual_reg_String)) {
					check = true;
					customWait(1000);
					test.log(LogStatus.INFO,"Now option is present and verified ");
					assertTrue(check);
				}else {
					test.log(LogStatus.ERROR, "Now option not verified");
					Assert.fail();
				}
			}
			else {
				test.log(LogStatus.ERROR, "Now option not available");
				Assert.fail();
			}
			clickBackButton(2);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void validateLaterOption() throws InterruptedException {
		/*
		 * validate later option
		 */

		try {
			System.out.println(Locators_WarrantyReg.Later_Button.getText());
			minWait();
			if(isElementExist(Locators_WarrantyReg.Later_Button)) {
				minWait();
				clickBtn(Locators_WarrantyReg.Later_Button);
				customWait(2000);
				takeScreenShotForOcrPngFormat("WarrantyReg");
				Thread.sleep(2000);
				OCR.my_main.validate_Using_OCR("WarrantyReg.png");
				customWait(2000);
				Boolean value = searchStringOCR("Support", "ocr");
				if(value) {
					check = true;
					customWait(1000);
					test.log(LogStatus.INFO,"Later option is present and verified ");
					assertTrue(check);
				}else {
					test.log(LogStatus.ERROR, "Later option not verified");
					Assert.fail();
				}
			}
			else {
				test.log(LogStatus.ERROR, "Later option not available");
				Assert.fail();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void validateNeverOption() throws InterruptedException {
		/*
		 * validate never option
		 */

		try {
			boolean check1 = validateNeverOptionwithYesorNoOption(Locators_WarrantyReg.no_Btn, "No");
			minWait();
			boolean check2 = validateNeverOptionwithYesorNoOption(Locators_WarrantyReg.yes_Btn, "Yes");
			if(check1 && check2) {
				check = true;
				customWait(1000);
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_ENDCALL);
				test.log(LogStatus.INFO,"Never option is present and verified with Yes or No Option");
				test.log(LogStatus.PASS, "Test case status is Passed");
				assertTrue(check);
			}else {
				test.log(LogStatus.ERROR, "Failed to validate Never option with Yes or No Option ");
				test.log(LogStatus.FAIL, "Test case status is Failed");
				Assert.fail();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	public boolean validateNeverOptionwithYesorNoOption(WebElement element,String option) throws InterruptedException {
		/*
		 * validate never with yes or no options
		 */
		try {
			if(isElementExist(Locators_WarrantyReg.Never_Button)) {
				minWait();
				clickBtn(Locators_WarrantyReg.Never_Button);
				if(isElementExist(Locators_WarrantyReg.never_Optn_Confirmation_Popup)) {
					clickBtn(element);
					if(option == "Yes") {
						takeScreenShotForOcrPngFormat("WarrantyReg");
						Thread.sleep(2000);
						OCR.my_main.validate_Using_OCR("WarrantyReg.png");
						customWait(2000);
						clickBackButton(2);
					}
					Boolean value = searchStringOCR("Support", "ocr");
					if(isElementExist(Locators_WarrantyReg.RegisterYourDeviceOptn)|| value ) {
						check = true;
					}else {
						check = false;
						test.log(LogStatus.ERROR, "Failed to validate "+option+" Option in confirmation pop_up of Never Option");
						Assert.fail();
					}
				}else {
					check = false;
					test.log(LogStatus.ERROR, "Confirmation Pop_up not available");
					Assert.fail();
				}
			}
			else {
				check = false;
				test.log(LogStatus.ERROR, "Never option not available");
				Assert.fail();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return check;
	}

	public void validatemandatoryPopUp(String Field) throws InterruptedException {
		/*
		 * validate mandatory error pop up
		 */
		try {
			if(isElementExist(Locators_WarrantyReg.mandatoryFieldPopUp)) {
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_UP);
				minWait();
				clickBtn(Locators_WarrantyReg.SWR_OK_Btn);
				check = true;
				customWait(2000);
				test.log(LogStatus.INFO,"Mandatory Pop_Up for "+Field+" Validated Successfully");
				assertTrue(check);
			}else {
				test.log(LogStatus.ERROR, "Failed to validate mandatory Pop_Up for "+Field);
				Assert.fail();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void validateEachMandatoryField(String firstname,String lastname,String country,String company,String state,String city,String zipCode,String address,String phoneNumber,String email_id) throws InterruptedException {
		/*
		 * validate all mandatory fields
		 */
		try {
			clickBtn(Locators_WarrantyReg.Now_Button);
			minWait();

			//scrollToText("SUBMIT");
			scrollToElementWithDpadDown(Locators_WarrantyReg.submitBtn);
			clickBtn(Locators_WarrantyReg.submitBtn);
			validatemandatoryPopUp("First_Name Field");
			checkAndFillField(Locators_WarrantyReg.firstNameTextField, firstname,"First_Name Field");

			scrollToElementWithDpadDown(Locators_WarrantyReg.submitBtn);
			clickBtn(Locators_WarrantyReg.submitBtn);
			validatemandatoryPopUp("Last_Name Field");
			checkAndFillField(Locators_WarrantyReg.lastNameTextField, lastname,"Last_Name Field");
			minWait();

			scrollToElementWithDpadDown(Locators_WarrantyReg.submitBtn);
			clickBtn(Locators_WarrantyReg.submitBtn);
			validatemandatoryPopUp("Country Field");
			checkAndFillField(Locators_WarrantyReg.countryTextField, country,"Country Field");

			scrollToElementWithDpadDown(Locators_WarrantyReg.submitBtn);
			clickBtn(Locators_WarrantyReg.submitBtn);
			validatemandatoryPopUp("Company_Name Field");
			checkAndFillField(Locators_WarrantyReg.companyTextField, company , "Company_Name Field");

			scrollToElementWithDpadDown(Locators_WarrantyReg.submitBtn);
			clickBtn(Locators_WarrantyReg.submitBtn);
			validatemandatoryPopUp("State Field");
			checkAndFillField(Locators_WarrantyReg.stateTextField, state,"State Field");

			scrollToElementWithDpadDown(Locators_WarrantyReg.submitBtn);
			clickBtn(Locators_WarrantyReg.submitBtn);
			validatemandatoryPopUp("City Field");
			checkAndFillField(Locators_WarrantyReg.cityTextField, city ,"City Field");

			scrollToElementWithDpadDown(Locators_WarrantyReg.submitBtn);
			clickBtn(Locators_WarrantyReg.submitBtn);
			validatemandatoryPopUp("ZipCode Field");
			checkAndFillField(Locators_WarrantyReg.zipcodeTextField, zipCode ,"ZipCode Field");

			scrollToElementWithDpadDown(Locators_WarrantyReg.submitBtn);
			clickBtn(Locators_WarrantyReg.submitBtn);
			validatemandatoryPopUp("address1 Field");
			checkAndFillField(Locators_WarrantyReg.address1TextField, address ,"address1 Field");

			scrollToElementWithDpadDown(Locators_WarrantyReg.submitBtn);
			clickBtn(Locators_WarrantyReg.submitBtn);
			validatemandatoryPopUp("Phone_Number Field");
			checkAndFillField(Locators_WarrantyReg.phoneNumberTextField, phoneNumber ,"Phone_Number Field");

			scrollToElementWithDpadDown(Locators_WarrantyReg.submitBtn);
			clickBtn(Locators_WarrantyReg.submitBtn);
			validatemandatoryPopUp("email_id Field");
			checkAndFillField(Locators_WarrantyReg.email_idTextField, email_id ,"email_id Field");

			scrollToElementWithDpadDown(Locators_WarrantyReg.submitBtn);
			clickBtn(Locators_WarrantyReg.submitBtn);
			validatemandatoryPopUp("purchased_on Field");
			if(isElementExist(Locators_WarrantyReg.selectPurchasedDateField)) {
				minWait();
				clickBtn(Locators_WarrantyReg.selectPurchasedDateField);
				minWait();
				clickBtn(Locators_WarrantyReg.choose_Btn);
			}else {
				test.log(LogStatus.ERROR, "Purchased on Field not available to fill the data");
				Assert.fail();
			}
			clickBackButton(2);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void checkAndFillField(WebElement element, String field,String str ) throws InterruptedException {
		/*
		 * check if field is available and fill data 
		 */
		try {
			if(isElementExist(element)) {
				enterTextToInputField(element, field);
				minWait();
			}else {
				test.log(LogStatus.ERROR, str+" not available to fill the data");
				Assert.fail();
			}
		} catch (Exception e) {
			e.printStackTrace();
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

	public void validateResetButton() throws InterruptedException {
		/*
		 * validate Reset option
		 */
		try {
			clickBtn(Locators_WarrantyReg.Now_Button);
			minWait();
			Boolean check1 = validateResetButtonNoOption();
			minWait();
			clickBtn(Locators_WarrantyReg.Now_Button);
			Boolean check2 = validateResetButtonYesOption();
			if(check1 && check2) {
				check = true;
				customWait(1000);
				test.log(LogStatus.INFO,"Reset Button Validated Successfully");
				test.log(LogStatus.PASS, "Test case status is Passed");
				assertTrue(check);
			}else {
				test.log(LogStatus.ERROR, "Failed to validate Reset Button");
				test.log(LogStatus.FAIL, "Test case status is Failed");
				Assert.fail();
			}
			clickBackButton(2);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean validateResetButtonNoOption() throws InterruptedException {
		/*
		 * validate reset with no option
		 */
		try {
			enterTextToInputField(Locators_WarrantyReg.firstNameTextField, "Automation");
			scrollToText("RESET");
			clickBtn(Locators_WarrantyReg.resetBtn);
			if(isElementExist(Locators_WarrantyReg.ResetConfirmationPopUp)) {
				clickBtn(Locators_WarrantyReg.no_Btn);
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_LEFT);
				minWait();
				for(int i=0;i<19;i++) {
					aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_UP);
				}
				//	scrollToElementWithDpadUp(Locators_WarrantyReg.firstnamefilled_Fld);
				clickDownButton(1);
				takeScreenShotForOcrPngFormat("WarrantyReg");
				Thread.sleep(2000);
				OCR.my_main.validate_Using_OCR("WarrantyReg.png");
				customWait(2000);
				Boolean value = searchStringOCR("Automation", "ocr");
				if(value) {
					check = true;
					customWait(1000);
					test.log(LogStatus.INFO,"Reset Button with No option Validated Successfully");
					assertTrue(check);
				}else {
					test.log(LogStatus.ERROR, "Failed to validate Reset Button with No Option");
					Assert.fail();
				}
			}else {
				test.log(LogStatus.ERROR, "Reset Confirmation pop up not available");
				Assert.fail();
			}
			clickBackButton(1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return check;
	}

	public void scrollToElementWithDpadUp(WebElement element) {
		/*
		 * Clicks up button till element is available
		 */
		System.out.println("method");
		while(!isElementExist(element)) {
			System.out.println("inside");
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_UP);
		}
	}


	public boolean validateResetButtonYesOption() throws InterruptedException {
		/*
		 * validate reset with yes option
		 */
		try {
			enterTextToInputField(Locators_WarrantyReg.firstNameTextField, "Automation");
			scrollToText("RESET");
			clickBtn(Locators_WarrantyReg.resetBtn);
			if(isElementExist(Locators_WarrantyReg.ResetConfirmationPopUp)) {
				clickBtn(Locators_WarrantyReg.yes_Btn);
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_LEFT);
				minWait();
				for(int i=0;i<19;i++) {
					aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_UP);
				}
				//	scrollToElementWithDpadUp(Locators_WarrantyReg.PersonalDetailsString);
				clickDownButton(1);
				if(isElementExist(Locators_WarrantyReg.firstNameTextField)) {
					check = true;
					customWait(1000);
					test.log(LogStatus.INFO,"Reset Button with Yes option Validated Successfully");
					assertTrue(check);
				}else {
					test.log(LogStatus.ERROR, "Failed to validate Reset Button with Yes Option");
					Assert.fail();
				}
			}else {
				test.log(LogStatus.ERROR, "Reset Confirmation pop up not available");
				Assert.fail();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return check;
	}

	public void validatePressenceOfUploadFiles() throws InterruptedException {
		/*
		 * validate presence of upload file option
		 */
		try {
			clickBtn(Locators_WarrantyReg.Now_Button);
			scrollToText("Proof of Purchase");
			minWait();
			if(isElementExist(Locators_WarrantyReg.uploadFilesString) && isElementExist(Locators_WarrantyReg.uploadFilesOption)) {
				check = true;
				customWait(1000);
				test.log(LogStatus.INFO,"Upload files option is present");
				assertTrue(check);
			}else {
				test.log(LogStatus.ERROR, "Upload files option not available");
				Assert.fail();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void validateTakeAPicture() throws InterruptedException {
		/*
		 * validate take picture option
		 */
		try {
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_UP);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_UP);
			minWait();
			clickBtn(Locators_WarrantyReg.uploadFilesOption);
			minWait();
			if(isElementExist(Locators_WarrantyReg.take_a_picture_option)) {
				clickBtn(Locators_WarrantyReg.take_a_picture_option);
				minWait();
				/*	aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_UP);
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);*/
				clickBtn(Locators_WarrantyReg.captureImageOption);
				customWait(5000);
				if(isElementExist(Locators_WarrantyReg.addImagePopUp)) {
					minWait();
					System.out.println(Locators_WarrantyReg.SWR_OK_Btn.getText());
					clickBtn(Locators_WarrantyReg.SWR_OK_Btn);
					minWait();
					clickDownButton(1);
					if(isElementExist(Locators_WarrantyReg.imageAddedOption)) {
						check = true;
						customWait(1000);
						test.log(LogStatus.INFO,"Take a picture option is validated successfully and captued image is available");
						test.log(LogStatus.PASS, "Test case status is Passed");
						assertTrue(check);
					}else {
						test.log(LogStatus.ERROR, "Failed to validate take a picture Option,captured image is not available");
						test.log(LogStatus.FAIL, "Test case status is Failed");
						Assert.fail();
					}
				}else {
					test.log(LogStatus.ERROR, "Add Image Pop_Up not available");
					test.log(LogStatus.FAIL, "Test case status is Failed");
					Assert.fail();
				}

			}else {
				test.log(LogStatus.ERROR, "Take a picture option not available");
				test.log(LogStatus.FAIL, "Test case status is Failed");
				Assert.fail();
			}
			clickBackButton(3);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void validateFileType() throws InterruptedException {
		/*
		 * validate all supporting file types
		 */
		try {
			clickBtn(Locators_WarrantyReg.Now_Button);
			//	scrollToText("Proof of Purchase");
			minWait();
			scrollToElementWithDpadDownRuntime(Locators_WarrantyReg.submitBtn);
			customWait(2000);
			boolean check1 = selectandValidatefile(1,Locators_WarrantyReg.jpegFile, "jpeg");
			customWait(2000);
			boolean check2 = selectandValidatefile(2, Locators_WarrantyReg.jpgFile, "jpg");
			customWait(2000);
			boolean check3 = selectandValidatefile(3,Locators_WarrantyReg.pngFile, "png");
			customWait(2000);
			boolean check4 = selectandValidatefile(4, Locators_WarrantyReg.docFile, "doc");
			customWait(2000);
			boolean check5 = selectandValidatefile(5, Locators_WarrantyReg.pdfFile, "pdf");
			customWait(2000);
			if(check1 && check2 && check3 && check4 && check5) {
				check = true;
				customWait(1000);
				test.log(LogStatus.INFO,"Upload files option is tested with all supporting file types successfully");
				assertTrue(check);
			}else {
				test.log(LogStatus.ERROR, "Failed to test upload files option with all supporting file types");
				Assert.fail();
			}
			clickBackButton(1);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean selectandValidatefile(int number,WebElement element,String filetype) {
		/*
		 * common method to select supporting file type and validates all file types
		 */
		try {
			NavigateToselectFile();
			if(number==1) {
				clickDownButtonruntime(1);
				customWait(2000);
				Runtime.getRuntime().exec("adb -s "+ p_Id +" shell input keyevent 19");
				customWait(2000);
			}else if (number==2) {
				clickDownButtonruntime(2);
				customWait(2000);
				Runtime.getRuntime().exec("adb -s "+ p_Id +" shell input keyevent 19");
				customWait(2000);
			}else if (number==3) {
				clickDownButtonruntime(3);
				customWait(2000);
				Runtime.getRuntime().exec("adb -s "+ p_Id +" shell input keyevent 19");
				customWait(2000);
			}else if (number==4) {
				clickDownButtonruntime(4);
				customWait(2000);
				Runtime.getRuntime().exec("adb -s "+ p_Id +" shell input keyevent 19");
				customWait(2000);
			}else if (number==5) {
				clickDownButtonruntime(5);
				customWait(2000);
				Runtime.getRuntime().exec("adb -s "+ p_Id +" shell input keyevent 19");
				customWait(2000);
			}else if(number == 6) {
				clickDownButtonruntime(6);
				minWait();
			}
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
			clickDownButton(1);
			customWait(3000);
			/*OCR.Read_File.takeScreenShotForOcrPngFormat("WarrantyReg");
			Thread.sleep(2000);
			OCR.my_main.validate_Using_OCR("WarrantyReg.jpeg");
			customWait(2000);
			Boolean value = searchStringOCR(str, "ocr");*/
			System.out.println(element.getText());
			if(isElementExist(element)) {
				check = true;
				customWait(1000);
				test.log(LogStatus.INFO,"Able to add "+filetype+" File successfully");
			}else {
				check = false;
				test.log(LogStatus.ERROR, "Failed to add "+filetype);
				Assert.fail();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return check;
	}

	public void validateInvalidFileType() throws InterruptedException {
		/*
		 * upload and check invalid file type
		 */
		try {
			clickBtn(Locators_WarrantyReg.Now_Button);
			//		scrollToText("Proof of Purchase");
			minWait();
			scrollToElementWithDpadDownRuntime(Locators_WarrantyReg.submitBtn);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_UP);
			minWait();
			clickBtn(Locators_WarrantyReg.uploadFilesOption);
			minWait();
			clickBtn(Locators_WarrantyReg.uploadFilesOption);
			minWait();
			clickDownButton(2);
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
			clickDownButton(2);
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
			clickDownButton(3);
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
			minWait();
			clickDownButton(6);
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
			minWait();
			if(isElementExist(Locators_WarrantyReg.invalidFileTypeErrorMsg)) {
				clickBtn(Locators_WarrantyReg.SWR_OK_Btn);
				minWait();
				check = true;
				test.log(LogStatus.INFO,"Invalid file type error message vaidated successfully,when upload unsupported format file");
				test.log(LogStatus.PASS, "Test case status is Passed");
				assertTrue(check);
			}else {
				test.log(LogStatus.ERROR, "Failed to validate invalid file type error message");
				test.log(LogStatus.FAIL, "Test case status is Failed");
				Assert.fail();
			}
			clickBackButton(1);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void fillAllTheFields(String firstname,String lastname,String country,String company,String state,String city,String zipCode,String address1,String address2,String phoneNumber,String email_id,String dealerName) throws InterruptedException {
		/*
		 * Fill all the fields with given data
		 */
		try {
			clickBtn(Locators_WarrantyReg.Now_Button);
			minWait();
			checkAndFillField(Locators_WarrantyReg.firstNameTextField, firstname,"First_Name Field");
			minWait();
			checkAndFillField(Locators_WarrantyReg.lastNameTextField, lastname,"Last_Name Field");
			minWait();
			checkAndFillField(Locators_WarrantyReg.countryTextField, country,"Country Field");
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_UP);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
			minWait();
			scrollToElementWithDpadDown(Locators_WarrantyReg.address1TextField);
			minWait();
			checkAndFillField(Locators_WarrantyReg.companyTextField, company , "Company_Name Field");
			minWait();
			checkAndFillField(Locators_WarrantyReg.stateTextField, state,"State Field");
			minWait();
			checkAndFillField(Locators_WarrantyReg.cityTextField, city ,"City Field");
			minWait();
			checkAndFillField(Locators_WarrantyReg.zipcodeTextField, zipCode ,"ZipCode Field");
			minWait();
			checkAndFillField(Locators_WarrantyReg.address1TextField, address1 ,"address1 Field");
			minWait();
			scrollToElementWithDpadDown(Locators_WarrantyReg.email_idTextField);
			checkAndFillField(Locators_WarrantyReg.address2TextField, address2 ,"address2 Field");
			minWait();
			checkAndFillField(Locators_WarrantyReg.phoneNumberTextField, phoneNumber ,"Phone_Number Field");
			minWait();
			checkAndFillField(Locators_WarrantyReg.email_idTextField, email_id ,"email_id Field");
			minWait();

			//to fill mail id
			Runtime.getRuntime().exec("adb -s "+ p_Id +" shell input keyevent --longpress KEYCODE_1");
			customWait(1000);
			customWait(1000);
			for(int i=1; i<=3; i++ ) {
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_RIGHT);
			}
			customWait(1000);
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_ENTER);
			customWait(8000);
			Runtime.getRuntime().exec("adb -s "+ p_Id +" shell input text gmail.com"); 
			customWait(1000);

			scrollToElementWithDpadDown(Locators_WarrantyReg.selectPurchasedDateField);
			checkAndFillField(Locators_WarrantyReg.dealer_Name, dealerName ,"dealer_Name Field");
			minWait();
			clickDownButton(1);
			System.out.println(Locators_WarrantyReg.selectPurchasedDateField.getText());
			if(isElementExist(Locators_WarrantyReg.selectPurchasedDateField)) {
				minWait();
				clickBtn(Locators_WarrantyReg.selectPurchasedDateField);
				minWait();
				clickBtn(Locators_WarrantyReg.choose_Btn);
			}else {
				test.log(LogStatus.ERROR, "Purchased on Field not available to fill the data");
				Assert.fail();
			}
			clickDownButtonruntime(1);
			scrollToElementWithDpadDownRuntime(Locators_WarrantyReg.submitBtn);
			NavigateToselectFile();
			clickDownButtonruntime(1);
			Runtime.getRuntime().exec("adb -s "+ p_Id +" shell input keyevent 19");
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void NavigateToselectFile() {
		/*
		 * navigates to file location
		 */
		try {
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_UP);
			minWait();
			clickBtn(Locators_WarrantyReg.uploadFilesOption);
			minWait();
			clickBtn(Locators_WarrantyReg.uploadFilesOption);
			minWait();
			//clickBtn(Locators_WarrantyReg.chooseAFileOption);
			minWait();
			clickDownButton(2);
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
			clickDownButton(2);
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
			clickDownButton(3);
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
			minWait();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void validateSubmitOptionWithNoOption() {
		/*
		 * validate submit button with no option
		 */
		try {
			scrollToElementWithDpadDown(Locators_WarrantyReg.submitBtn);
			clickDownButton(2);
			clickBtn(Locators_WarrantyReg.submitBtn);
			minWait();
			if(isElementExist(Locators_WarrantyReg.submit_pop_up)) {
				minWait();
				clickBtn(Locators_WarrantyReg.no_Btn);
				minWait();
				takeScreenShotForOcrPngFormat("WarrantyReg");
				Thread.sleep(2000);
				OCR.my_main.validate_Using_OCR("WarrantyReg.png");
				customWait(2000);
				Boolean value = searchStringOCR("SUBMIT", "ocr");
				if(value) {
					check = true;
					customWait(1000);
					test.log(LogStatus.INFO,"Filled all fields and validated Submit option with No option Successfully");
					assertTrue(check);
				} else {
					test.log(LogStatus.ERROR, "Failed to validated submit option with no option");
					Assert.fail();
				}
			}else {
				test.log(LogStatus.ERROR, "Submit Pop_Up is not available");
				Assert.fail();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void scrollToElementWithDpadDownRuntime(AndroidElement element) throws IOException, InterruptedException
	{
		/*
		 * Clicks down button till element is available
		 */
		while(!isElementExist(element)){
			//aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
			customWait(1000);
			Runtime.getRuntime().exec("adb -s "+ p_Id +" shell input keyevent 20");
			customWait(1000);
		}
	}

	public void validateSubmitOptionWithYesOption() {
		/*
		 * validate submit button with yes option
		 */
		try {
			for(int i=0;i<19;i++) {
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_UP);
			}
			clickDownButton(20);
			//	scrollToElementWithDpadDownRuntime(Locators_WarrantyReg.submitBtn);
			minWait();
			clickDownButton(1);
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
			//	clickBtn(Locators_WarrantyReg.submitBtn);
			minWait();
			if(isElementExist(Locators_WarrantyReg.submit_pop_up)) {
				minWait();
				clickBtn(Locators_WarrantyReg.yes_Btn);
				customWait(30000);
				clickBackButton(1);
				clickBtn(Locators_WarrantyReg.WarrantyReg);
				minWait();
				takeScreenShotForOcrPngFormat("WarrantyReg");
				Thread.sleep(2000);
				OCR.my_main.validate_Using_OCR("WarrantyReg.png");
				customWait(2000);
				Boolean value = searchStringOCR("User Information", "ocr");
				if(value) {
					check = true;
					customWait(1000);
					test.log(LogStatus.INFO,"Filled all fields and validated Submit option with Yes option Successfully,User Registered Successfully");
					test.log(LogStatus.PASS, "Test case status is Passed");
					assertTrue(check);
				} else {
					test.log(LogStatus.ERROR, "Failed to validated submit option with yes option");
					test.log(LogStatus.FAIL, "Test case status is Failed");
					Assert.fail();
				}
			}else {
				test.log(LogStatus.ERROR, "Submit Pop_Up is not available");
				test.log(LogStatus.FAIL, "Test case status is Failed");
				Assert.fail();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void getVersion() {
		/*
		 * navigate to about and gets version
		 */
		try {
			//	clickDownButtonruntime(1);
			customWait(3000);
			Runtime.getRuntime().exec("adb -s "+ p_Id +" shell input keyevent 19");
			//	aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_UP);
			customWait(2000);
			//	aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
			Runtime.getRuntime().exec("adb -s "+ p_Id +" shell input keyevent 23");
			//	clickBtn(Locators_WarrantyReg.More_option);
			customWait(2000);
			clickBtn(Locators_WarrantyReg.about_option);
			customWait(3000);
			if(isElementExist(Locators_WarrantyReg.app_version)) {
				String version = Locators_WarrantyReg.app_version.getText();
				check = true;
				test.log(LogStatus.INFO, "Sonim Warranty Registration Application version is : " +version);
				clickBtn(Locators_WarrantyReg.SWR_OK_Btn);
				test.log(LogStatus.PASS, "Test case status is Passed");
				assertTrue(check);
			}else {
				test.log(LogStatus.ERROR, "Failed to fetch Sonim Warranty Registration Application version");
				test.log(LogStatus.FAIL, "Test case status is Failed");
				Assert.fail();
			}
			clickBackButton(3);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void validateAbout() {
		/*
		 * navigate to about and validates about option
		 */
		try {
			customWait(3000);
			Runtime.getRuntime().exec("adb -s "+ p_Id + " shell input keyevent 19");
			//	aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_UP);
			customWait(2000);
			//	aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
			Runtime.getRuntime().exec("adb -s "+ p_Id +" shell input keyevent 23");
			//	clickBtn(Locators_WarrantyReg.More_option);
			customWait(2000);
			clickBtn(Locators_WarrantyReg.about_option);
			boolean value1 = getTextAndValidate(Locators_WarrantyReg.copyright_option, "Copyright ©2019");
			boolean value2 = getTextAndValidate(Locators_WarrantyReg.copyright_option, "Sonim Technologies Inc.");
			boolean value3 = getTextAndValidate(Locators_WarrantyReg.AllRightsReservedString, "All rights Reserved");
			boolean value4 = getTextAndValidate(Locators_WarrantyReg.sonimPrivacyPolicyString, "Sonim Privacy Policy");
			boolean value5 = getTextAndValidate(Locators_WarrantyReg.PrivacyPolicylink, "https://www.sonimtech.com/index.php/policy");
			if(value1 && value2 && value3 && value4 && value5) {
				clickBtn(Locators_WarrantyReg.SWR_OK_Btn);
				check = true;
				test.log(LogStatus.INFO, "Validated Copyright and sonim privacy policy link successfully");
				test.log(LogStatus.PASS, "Test case status is Passed");
				assertTrue(check);
			}else {
				test.log(LogStatus.ERROR, "Failed to validate Copyright and sonim privacy policy link");
				test.log(LogStatus.FAIL, "Test case status is Failed");
				Assert.fail();
			}
			clickBackButton(4);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	public boolean getTextAndValidate(WebElement element,String str) {
		/*
		 *common method to validate with given string 
		 */
		try {
			String text = element.getText();
			System.out.println(text);
			if(text.contains(str)) {
				check = true;
				assertTrue(check);
			}else {
				check = false;
				Assert.fail();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return check;
	}

	public void validateRegisteredInfo() {
		/*
		 * validates Registered information of user
		 */
		try {
			customWait(2000);
			takeScreenShotForOcrPngFormat("WarrantyReg");
			Thread.sleep(2000);
			OCR.my_main.validate_Using_OCR("WarrantyReg.png");
			customWait(2000);
			Boolean value = searchStringOCR("User Information", "ocr");
			if(value) {
				Boolean value1 = compare("Automation","Name","Automation Test");
				customWait(2000);
				Boolean value2 = compare("Sonim","Company Name","Sonim");
				customWait(2000);
				Boolean value3 = compare("Sonim","Company Name","Sonim");
				customWait(2000);
				clickDownButton(3);
				Boolean value4 = compare("9876543210","Phone Number","9876543210");
				customWait(2000);
				Boolean value5 = compare("@gmai|","Email ID","automationtest@gmail.com");
				customWait(2000);
				clickDownButton(3);
				minWait();
				Boolean value6 = compare("XP5800","Model No.","XP5800");
				customWait(2000);
				Boolean value7 = compare("Operator","Dealer Name","Operator");
				customWait(2000);
				if(value1 && value2 && value3 && value4 && value5 && value6 && value7) {
					check = true;
					test.log(LogStatus.INFO, "Registered data validated successfully");
					test.log(LogStatus.PASS, "Test case status is Passed");
					assertTrue(check);
				}else {
					test.log(LogStatus.ERROR, "Failed to validate Registered data");
					test.log(LogStatus.FAIL, "Test case status is Failed");
					Assert.fail();
				}
			}
			clickBackButton(3);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean compare(String str,String str1,String str2) {
		/*
		 *common method to validate registered information with presence of string 
		 */
		try {
			takeScreenShotForOcrPngFormat("WarrantyReg");
			Thread.sleep(2000);
			OCR.my_main.validate_Using_OCR("WarrantyReg.png");
			customWait(2000);
			Boolean value = searchStringOCR(str, "ocr");
			if(value) {
				check = true;
				test.log(LogStatus.INFO,"Registered "+ str1+ " is :"+ str2);
				System.out.println("Registered "+ str1+ " is :"+ str2);
			}else {
				check = false;
				test.log(LogStatus.ERROR,"Registered "+ str1+ " is Not available");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return check;
	}

	public void deleteFiles() {
		/*
		 * delete pushed files in device
		 */
		try {
			Runtime.getRuntime().exec("adb -s "+p_Id+" shell rm -r /storage/emulated/0/Download/picture.jpg");
			Runtime.getRuntime().exec("adb -s "+p_Id+" shell rm -r /storage/emulated/0/Download/picture.jpeg");
			Runtime.getRuntime().exec("adb -s "+p_Id+" shell rm -r /storage/emulated/0/Download/picture.png");
			Runtime.getRuntime().exec("adb -s "+p_Id+" shell rm -r /storage/emulated/0/Download/sample.pdf");
			Runtime.getRuntime().exec("adb -s "+p_Id+" shell rm -r /storage/emulated/0/Download/sample.doc");
			Runtime.getRuntime().exec("adb -s "+p_Id+" shell rm -r /storage/emulated/0/Download/test.xlsx");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}




}
