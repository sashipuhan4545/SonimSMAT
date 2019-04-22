package com.xp8.util;

import static org.testng.Assert.fail;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import com.graphics.gui.JsonFileReaderAndWriter;
import com.relevantcodes.extentreports.LogStatus;
import com.xp5S.util.Locators_ScoutSanity;
import com.xp8.util.Locators_ScoutFT;

import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.AndroidKeyCode;
import junit.framework.Assert;

public class XP8_ScoutFT_Util  extends BaseUtil {
	
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
	public boolean check11= false;

	boolean ch1=false;
	boolean ch2=false;
	boolean ch3=false;
	boolean ch4=false;
	boolean ch5=false;
	boolean ch6=false;
	boolean ch7=false;
	boolean ch8=false;
	boolean ch9=false;
	boolean ch10=false;
	boolean ch11=false;
	boolean ch12=false;
	boolean ch13=false;
	boolean ch14=false;
	boolean ch15=false;
	boolean ch16=false;
	boolean ch17=false;

	boolean check1_a = false;
	boolean check2_a = false;
	boolean check3_a = false;
	boolean check4_a = false;
	boolean check5_a = false;
	boolean check6_a = false;
	boolean check7_a = false;
	boolean check8_a = false;
	boolean check9_a = false;
	boolean check10_a= false;


	public void scroll() {
		try {
			org.openqa.selenium.Dimension size =aDriver.manage().window().getSize();
			System.out.println(size);
			int x=size.getWidth()/2;
			int starty=(int)(size.getHeight()*0.60);
			int endy=(int)(size.getHeight()*0.10);
			aDriver.swipe(x, starty, x, endy, 2000);
		} catch (org.openqa.selenium.NoSuchElementException e) {
			e.printStackTrace();
		}
	}
	
	public void launch_application(String app) throws InterruptedException{
		/*
		 * Launches sonim scout and navigates to supports tab..
		 */
		try {
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_HOME);
			minWait();
			Locators_ScoutFT.AppListIcon.click();
			minWait();
			enterTextToInputField(Locators_ScoutFT.AppSearch, app);
			minWait();
			navigateUsingText(app);
			minWait();
			APP_LOGS.info("Application launched succesfully");
			minWait_SonimCare();
		} catch (NoSuchElementException e) {
			APP_LOGS.info("Error: Application not found.");
			e.printStackTrace();
		}
	}

	public void validateScoutAppLaunch() {
		if(isElementExist(Locators_ScoutFT.utilitiesTab)) {
			check1 =true;
			APP_LOGS.info("Utility Tab is present");
		}
		if(isElementExist(Locators_ScoutFT.setUpTab)) {
			check2 =true;
			APP_LOGS.info("SetUp Tab is present");
		}
		if(isElementExist(Locators_ScoutFT.supportTab)) {
			check3 =true;
			APP_LOGS.info("Support Tab is present");
		}

		if((check1)&&(check2)&&(check3)) {
			check= true;
			APP_LOGS.info("Scout App Launched sucessfully and all Tabs verified");
			Assert.assertTrue(check);
		}
		else{
			APP_LOGS.info(check1+"\n"+": "+check2+"\n"+": "+check3+ "\n");
			Assert.fail();
		}		
	}
	
	public void validateAllTabsandList() throws InterruptedException, IOException
	{
		clickBtn(Locators_ScoutFT.setUpTab);
		APP_LOGS.info("SetUp Tab is clicked");
		if(isElementExist(Locators_ScoutFT.SonimSetUpWizard)){
			check1 =true;
			APP_LOGS.info("SonimSetUpWazard is present ch1 "+ check1);        
		}
		if(isElementExist(Locators_ScoutFT.Safeguard)) {
			check2 =true;
			APP_LOGS.info("Safeguard is present ch2 "+ check2);	         
		}
        customWait(1000);
		clickBtn(Locators_ScoutFT.utilitiesTab);
		if(isElementExist(Locators_ScoutFT.ContactTransferTitle)) {
			check4 =true;
			APP_LOGS.info("ContactTransferTitle is present ch4 "+ check4);        
		}

		if(isElementExist(Locators_ScoutFT.BLEconnect)) {
			check5 =true;
			APP_LOGS.info("BLEconnect is present ch5 "+ check5);	         
		}
		 customWait(1000);
		clickBtn(Locators_ScoutFT.supportTab);
		if(isElementExist(Locators_ScoutFT.Chat)) {
			check6 =true;
			APP_LOGS.info("Chat is present ch6 "+ check6);        
		}

		if(isElementExist(Locators_ScoutFT.RemoteControl)) {
			check7 =true;
			APP_LOGS.info("RemoteControl is present ch7 "+ check7);	         
		}

		if(isElementExist(Locators_ScoutFT.SonimCare)) {
			check8 =true;
			APP_LOGS.info("SonimCare is present ch8 "+ check8);	         
		}

		if(isElementExist(Locators_ScoutFT.WarrantyReg)) {
			check9 =true;
			APP_LOGS.info("WarrantyReg is present ch9 "+ check9);	         
		}
		aDriver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().className(\"android.widget.ScrollView\")).scrollIntoView(new UiSelector().text(\"Contact Sonim Support\"))");  
		customWait(1000);
		if(isElementExist(Locators_ScoutFT.DeviceInfo_App)) {
			check10 =true;
			APP_LOGS.info("DeviceInfo is present ch10 "+ check10);	         
		}

		if(isElementExist(Locators_ScoutFT.ContactSonimSupport)) {
			check11 =true;
			APP_LOGS.info("ContactSonimSupport is present ch11 "+ check11);	         
		}

		if((check1)&&(check2)&&(check4)&&(check5)&&(check6)&&(check7)&&(check8)&&(check9)&&(check10)&&(check11)) {
			check= true;
			APP_LOGS.info("Scout App Launched and Tabs verifiedwith All Lists Present");
			Assert.assertTrue(check);
		}
		else{
			APP_LOGS.info(check1+"\n"+": "+check2+"\n");
			APP_LOGS.info(check4+"\n"+": "+check5+"\n"+": "+check6+ "\n");
			APP_LOGS.info(check7+"\n"+": "+check8+"\n"+": "+check9+ "\n");
			APP_LOGS.info(check10+"\n"+": "+check11+"\n");
			Assert.fail();
		}	
	}
	
	
	public void navigateprogrammablekey() throws InterruptedException, IOException {
		try {

		scrollToText("Programmable Keys");
		minWait();
		}catch (NoSuchElementException e) {
			APP_LOGS.info("Error: Application not found.");
			e.printStackTrace();
		}
	}
	
	public void validate_ProgrammableKeySummaryText() throws InterruptedException{
		/*
		 * Retrieves programmable key summary text.
		 */
		minWait();
		try {
			if(isElementExist(Locators_ScoutFT.summaryText)){
				APP_LOGS.info("Programmable key summary is displayed successfully");	
				String getSummaryText = Locators_ScoutFT.summaryText.getText();
				APP_LOGS.info("App assigned to programmable key is: "+getSummaryText);
				minWait();
				check=true;
				softAssert_true(check, "Pass");	
			}else{
				APP_LOGS.info("Programmable key summary is not displayed");
				softAssert_false();
			}								
		} catch (NoSuchElementException e) {			
			e.printStackTrace();
		}
	}
	
	public void validateAssignApp(String app) {
		boolean check = false;
		String summaryDisplayed;
		try {
			summaryDisplayed = Locators_ScoutFT.summaryText.getText();
			if(summaryDisplayed.equalsIgnoreCase(app)){
				APP_LOGS.info("Programmable key summary is displayed successfully");	
				check = true;
				APP_LOGS.info("App assigned to programmable key is: "+summaryDisplayed);
				check=true;
				softAssert_true(check, "Pass");	
		}else{
			APP_LOGS.info("Programmable key is not assigned properly");
			softAssert_false();
		}								
	} catch (NoSuchElementException e) {			
		e.printStackTrace();
		APP_LOGS.info("Programmable key summary is not displayed");
	}
	}
	
	
	public void scrollToNoApplication(WebElement key) throws InterruptedException{
		 /*
		  * scrolls to top of the PTT key app list
		  */
		 try {
			minWait();
			 clickBtn(key);
			 customWait(4000);
			 APP_LOGS.info("Navigated to select PTT KEY app screen sucessfully");
			 for (int i = 1; i <= 44; i++) {
				 if (isElementExist(Locators_ScoutFT.no_Application_app)) {
					 String getAppName = Locators_ScoutFT.no_Application_app.getText();
						APP_LOGS.info("No Application app is found.");
						minWait();					
						break;					
					} else {
						//APP_LOGS.info("Searching for No application app: " + i);
						aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_UP);
						continue;					
					}
			 }
		} catch (NoSuchElementException e) {
			APP_LOGS.info("Error: Application not found.");
			e.printStackTrace();
		}	
    }
	
	public void assign_app_to_ProgrammableKey(String app) throws InterruptedException{
		/*
		 * Assign app to programmable key.
		 * Send webelement to be selected as parameter
		 */
		try {         	
			/* for (int iter = 1; iter <= 34; iter++) {
				if (isElementExist(app)) {
					String getAppName = app.getText();
					APP_LOGS.info("Searched application is found sucessfully :: "+getAppName);
					minWait();
					clickBtn(app);
					APP_LOGS.info(getAppName+" : Application is assigned to programmableKey");
					minWait();	
					if(isElementExist(Locators_ScoutFT.Ok_Btn)) {
						APP_LOGS.info("Validated assigning to other Apps sucessfully ");
						aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_RIGHT);
						minWait();
						aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
						minWait();					
						minWait();
					}
					break;					
				} else {
					aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
					continue;			*/		
				scrollToText(app);
				}
			
		 	catch (NoSuchElementException e) {
			APP_LOGS.info("Error: Application not found.");
			e.printStackTrace();
		}		
	}
	
	public void validate_launchedApplication(String summaryText) throws InterruptedException, IOException {
		/*
		 * Validates application launched with respect to the application
		 * assigned to PTT KEY.
		 */
		try {
			minWait();
			String getSummaryText = aDriver.
					findElement(By.xpath("//android.widget.TextView[@text='"+summaryText+"']")).getText();
			if (getSummaryText.equalsIgnoreCase(getSummaryText)) {
				APP_LOGS.info("App assigned to programmable key is: "+getSummaryText);
				check1 = true;
				minWait();
			} else {
				APP_LOGS.info("Incorrect app summary text is displayed: "+getSummaryText);
			}

			press_PTT_Key();
			
			if (isTextPresent(summaryText)) {
				check2 = true;
				APP_LOGS.info("Assinged application launched succesfully upon pressing programmable key.");
			}else {
				APP_LOGS.info("Assinged application is not launched");
			}
		
			/*if (isElementExist(app)) {
				check2 = true;
				APP_LOGS.info("Assinged application launched succesfully upon pressing programmable key.");
			} else {
				APP_LOGS.info("Assinged application is not launched");
			}*/
			if(check1 && check2){
				check=true;
				APP_LOGS.info("Test case passed");
				softAssert_true(check, "Pass");
			}else{
				APP_LOGS.info("Test case failed");
				Assert.fail();
			}
		} catch (NoSuchElementException e) {			
			APP_LOGS.info("Error: Application not found.");
			e.printStackTrace();
		}
	}

	
	public void press_PTT_Key() throws InterruptedException, IOException{
		/*
		 * Long press event of PTT key is fired.
		 */
		Runtime.getRuntime().exec("cmd /c  \"adb shell input keyevent --longpress KEYCODE_PTT");
		customWait(5000);
	}
	
	public void presence_Of_SonimWarrantyReg_In_ScoutApp() throws InterruptedException, IOException {

		APP_LOGS.info("**************************Starts******************************"); 
		//			clearApp();
		SoftAssert SA1=new SoftAssert();
		navigateToWarrantyRegApp();
		customWait(1000);
		try {
			clickBtn(Locators_ScoutFT.SWR_Support_Tab);
			APP_LOGS.info("Tapped on Support tab successfully");
			customWait(1000);	
			String fetch_sf_text=Locators_ScoutFT.SWR_String_Name.getText();
			APP_LOGS.info("Fetching safeguard string text is" +fetch_sf_text);
			SA1.assertEquals(fetch_sf_text, "Warranty Registration");
			SA1.assertAll();
		}
		catch(NoSuchElementException ex){
			ex.printStackTrace();
			clearRecentApps();
			APP_LOGS.info("Element is not present in the page");
		}
		APP_LOGS.info("***************************** Ends******************************"); 	
	}
	
	public void navigateToWarrantyRegApp() throws InterruptedException, IOException {
		APP_LOGS.info("*****************************Common Method to launch Sonim Warranty Reg******************************");  
		aDriver.startActivity("com.sonim.scout", "com.sonim.scout.activities.MainActivity");
		customWait(1000);
		try {
			clickBtn(Locators_ScoutFT.SWR_Support_Tab);
			customWait(1000);
			clickBtn(Locators_ScoutFT.SWR_String_Name);
			customWait(1000);
			if(isElementExist(Locators_ScoutFT.SWR_Contacting_server_progress_dialog)) {
				APP_LOGS.info(" launchWarrantyRegApp: Progress dialog displayed on FG,So Waiting for 5 seconds");
				customWait(6000);
				if(isElementExist(Locators_ScoutFT.SWR_UnableTo_Fetch_RegistrationInforamtionFromServer)) {
					APP_LOGS.info("Unable to fetch registration information from server present on FG");
					clickBtn(Locators_ScoutFT.SWR_OK_Btn);
				}		
			}
			else if (isElementExist(Locators_ScoutFT.SWR_UnableTo_Fetch_RegistrationInforamtionFromServer)) {
				APP_LOGS.info("Unable to fetch registration information from server present on FG");
				clickBtn(Locators_ScoutFT.SWR_OK_Btn);
				System.out.println("Execution has stopped as,Device is unable to fetch registration in information from server");
				//aDriver.quit();
				test.log(LogStatus.SKIP, "Test case status is Skipped");	
				Assert.fail();
			}
			else if (isElementExist(Locators_ScoutFT.SWR_Please_contact_Sonim_At)) {
				customWait(1000);
				Runtime.getRuntime().exec("adb shell pm clear com.sonimtech.warrantyregapp");
				customWait(1000);
				//			      clearApp();
				aDriver.startActivity("com.sonim.scout", "com.sonim.scout.activities.MainActivity");
			}
			else if(isElementExist(Locators_ScoutFT.SWR_No_internet_Connection)) {
				clickBtn(Locators_ScoutFT.SWR_OK_Btn);
				APP_LOGS.info("************No internet connection window display ***************");
				System.out.println("Execution has stopped as ,there is No internet connection available in the device!! ");
				//aDriver.quit();
				test.log(LogStatus.SKIP, "Test case status is Skipped");	
				Assert.fail();
			}
		}
		catch (NoSuchElementException ex) {
			clearRecentApps();
			APP_LOGS.info("launchWarrantyRegApp:Element not found");
		}
	}

	// Validating submit button without entering any values in all the fields
		public void skip_AllFields_Press_SubmitBtn() throws InterruptedException, IOException {
			APP_LOGS.info("*******************Start*******************************");
			customWait(1000);	 
			SoftAssert SA3=new SoftAssert();		   
			if(isDeviceRegistered().equals("Register your device")) {
				try {
					customWait(1000);
					clickBtn(Locators_ScoutFT.SWR_Now);
					customWait(1000);
					clickBtn(Locators_ScoutFT.SWR_Reset);
					customWait(1000);				
					clickBtn(Locators_ScoutFT.SWR_Submit);
					customWait(2000);
					clickBtn(Locators_ScoutFT.SWR_OK_Btn);
					customWait(2000);
					//Dont enter any value in the test field and press on "submit" button and check
					if(isElementExist(Locators_ScoutFT.SWR_Submit)) {
						APP_LOGS.info("Did not entered anything and press the Submit button");
						check=true;
						APP_LOGS.info("PASSED");
						SA3.assertTrue(check, "D");					 
					}
					else {
						SA3.fail();
						APP_LOGS.info("FAILED");
					}	 
				}
				catch (NoSuchElementException e) {
					e.printStackTrace();
					APP_LOGS.info("Without entering Any value press submit button:No such Element found execption");
				}
				catch (NullPointerException e) {
					e.printStackTrace();
					APP_LOGS.info("Without entering Any value press submit button:\"Null pointer exception\"");
				}
				SA3.assertAll();
			}
			else {
				APP_LOGS.info("********************Device is Already Registered********************");
				System.out.println("*******Device is Already Registered,Need to Delete the device from server to run the Script*******");
				test.log(LogStatus.SKIP, "Test case status is Passed");	
				Assert.fail();
			//	aDriver.quit(); 
			}
		}

		//This method check whether device is registered or not
		public String isDeviceRegistered() throws InterruptedException, IOException {
			APP_LOGS.info("******************************start******************************"); 
			String status = null; 
			navigateToWarrantyRegApp();
			customWait(1000);
			try {
				if(isElementExist(Locators_ScoutFT.SWR_String_Register_Your_Device)) {
					APP_LOGS.info("isDeviceRegistered:Need to Registered the device");
					status=Locators_ScoutFT.SWR_String_Register_Your_Device.getText();	
					APP_LOGS.info("isDeviceRegistered :" +status);
				}
				else {
					APP_LOGS.info("isDeviceRegistered:Device is Already registered");
					status="Device is Already Registered";	
					APP_LOGS.info("isDeviceRegistered :"+status);
				}
			}
			catch(NoSuchElementException e) {
				e.printStackTrace();
				APP_LOGS.info("isDeviceRegistered:No Such Element found exeception");
			}
			APP_LOGS.info("TC isDevice Registered end******************************");
			return status;
		}


		public void validateUploadFile() throws InterruptedException {
			aDriver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().className(\"android.widget.ScrollView\")).scrollIntoView(new UiSelector().text(\"Invoice\"))").click();;
			customWait(1000);
			if(isElementExist(Locators_ScoutFT.SWR_invoice)) {
				check=true;
				APP_LOGS.info("Test case passed Validated Upload File");
				softAssert_true(check, "Pass");
			}else{
				APP_LOGS.info("Test case failed Upload File");
				Assert.fail();
			}
		}
		
		public void addInvoice() throws InterruptedException, WebDriverException, IOException {

			if(isDeviceRegistered().equals("Register your device")) {
				clickBtn(Locators_ScoutFT.SWR_Now);
				customWait(500);
				aDriver.hideKeyboard();
				customWait(1000);
				aDriver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().className(\"android.widget.ScrollView\")).scrollIntoView(new UiSelector().text(\"Choose a File\"))").click();;
				customWait(1000);
				clickBtn(Locators_ScoutFT.SWR_Take_a_Pic);
				customWait(2000);
				clickBtn(Locators_ScoutFT.SWR_camerabtn);
				customWait(4000);
				customWait(4000);
				customWait(6000);
				clickBtn(Locators_ScoutFT.btn_resultConfirmation_dialog);        
			}
			else {
				APP_LOGS.info("Device is already Registered");
				test.log(LogStatus.SKIP, "Test case status is Skipped");	
				Assert.fail();
			}
		}
		
		
		public void enter_Feilds_Press_ResetBtn() throws InterruptedException, IOException {
			try {

				if(isDeviceRegistered().equals("Register your device")) {						
					customWait(1000);
					clickBtn(Locators_ScoutFT.SWR_Now);					
					customWait(1000);
					aDriver.hideKeyboard();
					customWait(1000);
					enterTextToInputField(Locators_ScoutFT.SWR_Enter_First_Name, "sonim");
					customWait(1000);
					aDriver.hideKeyboard();
					customWait(1000);
					enterTextToInputField(Locators_ScoutFT.SWR_Enter_Last_Name, "sonim");
					customWait(1000);
					enterTextToInputField(Locators_ScoutFT.SWR_Enter_Company_Name, "Sonim Technology");
					customWait(1000);
					aDriver.hideKeyboard();
					customWait(1000);
					enterTextToInputField(Locators_ScoutFT.SWR_Enter_Address_1, "J P Nagar,3rd phase");
					customWait(1000);
					aDriver.hideKeyboard();
					customWait(1000);
					aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
					enterTextToInputField(Locators_ScoutFT.SWR_enter_City, "Banglore");
					customWait(1000);
					aDriver.hideKeyboard();
					customWait(1000);
					aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
					enterTextToInputField(Locators_ScoutFT.SWR_Enter_State_Name, "Karnataka");
					customWait(1000);
					aDriver.hideKeyboard();
					customWait(1000);
					//				aDriver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().className(\"android.widget.ScrollView\")).scrollIntoView(new UiSelector().text(\"Enter Country\"))");  
					customWait(1000);
					customWait(1000);
				}		 
				else {
					APP_LOGS.info("********************Device is Already Registered********************");
					System.out.println("*******Device is Already Registered,Need to Delete the device from server to run the Script*******");
					test.log(LogStatus.SKIP, "Test case status is Skipped");
					Assert.fail();
				}		 
			}catch (org.openqa.selenium.NoSuchElementException e) {
				// TODO: handle exception
			}
		}

		public void validateResetBtn() throws InterruptedException {
			customWait(500);
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			minWait();
			clickBtn(Locators_ScoutFT.SWR_Now);
			customWait(1000);	
			customWait(1000);
			clickBtn(Locators_ScoutFT.SWR_Reset);
			customWait(1000);
			if(isElementExist(Locators_ScoutFT.SWR_Enter_First_Name)) {
				check=true;
				APP_LOGS.info("Test case passed Validated Reset Button");
				softAssert_true(check, "Pass");
			}else{
				APP_LOGS.info("Test case failed Reset Button");
				Assert.fail();
			}
		}
		
		public void validateMandatoryfield() throws InterruptedException {
			clickBtn(Locators_ScoutFT.SWR_Now);
			minWait();
			clickBtn(Locators_ScoutFT.SWR_Submit);
			if(isElementExist(Locators_ScoutFT.SWR_OK_Btn)) {
				clickBtn(Locators_ScoutFT.SWR_OK_Btn);
				check=true;
				APP_LOGS.info("Test case passed Validated mandatory fields");
				softAssert_true(check, "Pass");
			}else{
				APP_LOGS.info("Test case failed mandatory fields");
				Assert.fail();
			}	   
		}
		
		public void navigateApps(WebElement tab,WebElement app) throws InterruptedException, IOException {
			//launchScoutApp();
			aDriver.startActivity("com.sonim.scout", "com.sonim.scout.activities.MainActivity");
			clickBtn(tab);
			customWait(1000);
			for(int i=1; i<=6; i++) {
				if(isElementExist(app)) {
					if(isElementExist(Locators_ScoutFT.SG_Locked_Screen_Validation)) {
						enter_Pin_App();
						minWait();
					}
					clickBtn(app);
					break;
				}
				else {
					aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
					continue;
				}
			}		 
		}
		
		public void navigateToApps(String tab, String app) throws InterruptedException, IOException {
			//launchScoutApp();
			//clickBtn(tab);
			if(isElementExist(Locators_ScoutFT.SG_Locked_Screen_Validation)) {
				enter_Pin_App();
				minWait();
			}
			navigateUsingText(tab);
			customWait(1000);
			
			navigateUsingText(app);
		}	 
		
		public void launchScoutApp() throws InterruptedException, IOException {
			launchAppThroughABD("cmd /c \"adb shell am start -n com.sonim.scout/com.sonim.scout.activities.MainActivity");
			customWait(5000);
		}
		
		public void validateDeviceInfoAcess() throws InterruptedException, FileNotFoundException, IOException, ParseException {
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
			minWait();
			if(isElementExist(Locators_ScoutFT.DeviceInfo_App)) {
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
				check1=true;
				APP_LOGS.info("ch1 Presence of Device Info"+ check1);	
			}

			if(isElementExist(Locators_ScoutFT.DeviceInfo)) {
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
				check2=true;
				APP_LOGS.info("ch2 Presence of Device Info"+check2);			
			}
			if(isElementExist(Locators_ScoutFT.DeviceInfo)) {
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
				check3=true;
				APP_LOGS.info("ch3 Presence of Device Info"+check3);			
			}

			if(isElementExist(Locators_ScoutFT.DeviceInfo_IMEI1)) {
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
				check4=true;
				APP_LOGS.info("ch4 Presence of Device Info"+ check4);			
			}

			if(isElementExist(Locators_ScoutFT.DeviceInfo_Model)) {
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
				check6=true;
				APP_LOGS.info("ch6 Presence of Device Info"+ check6);			
			}

			if(isElementExist(Locators_ScoutFT.DeviceInfo_Carrier)) {
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
				check7=true;
				APP_LOGS.info("ch7 Presence of Device Info"+ check7);			
			}
			if(isElementExist(Locators_ScoutFT.DeviceInfo_Baseband)) {
				
				String XP8deviceModelNumber=JsonFileReaderAndWriter.primaryDevModelReader();
				APP_LOGS.info("Device Baseband is: "+XP8deviceModelNumber);
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
				check8=true;
				APP_LOGS.info(" ch8 Presence of Device Info"+ check8);			
			}
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);

			if(isElementExist(Locators_ScoutFT.DeviceInfo_Build)) {
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
				check9=true;
				APP_LOGS.info("ch9 Presence of Device Info"+ check9);			
			}
			if((check1)&&(check2)&&(check3)&&(check4)&&(check6)&&(check7)&&(check8)&&(check9)) {
				check= true;
				APP_LOGS.info("Scout App Launched and Device information is Present");
				Assert.assertTrue(check);
			}
			else{
				APP_LOGS.info(check1+"\n"+": "+check2+"\n"+": "+check3+ "\n");
				APP_LOGS.info(check4+"\n"+": "+check6+ "\n"+check7+ "\n");		
				APP_LOGS.info(check8+"\n"+": "+check9+"\n");
				Assert.fail();
			}			  
		}


		public void validateAcessSWR() throws InterruptedException, IOException {
			/*
			 * Validating Acess of SWR from Device information
			 */
			if(isElementExist(Locators_ScoutFT.DevInf_Register)) {
				check1=true;
				clickBtn(Locators_ScoutFT.DevInf_Register);
				customWait(1000);
			}

			clickBtn(Locators_ScoutFT.SWR_Now);					
			customWait(1000);

			if(isElementExist(Locators_ScoutFT.SWR_Reset)) {
				check2=true;
				APP_LOGS.info("Test case passed Validated ");			
			}	

			if(isElementExist(Locators_ScoutFT.SWR_Submit)) {
				check3=true;
				APP_LOGS.info("Test case passed Validated ");			
			}		 

			enterTextToInputField(Locators_ScoutFT.SWR_Enter_First_Name, "sonim");
			customWait(1000);
			enterTextToInputField(Locators_ScoutFT.SWR_Enter_Last_Name, "sonim");
			customWait(1000);
			enterTextToInputField(Locators_ScoutFT.SWR_Enter_Company_Name, "Sonim Technology");
			customWait(1000);
			enterTextToInputField(Locators_ScoutFT.SWR_Enter_Address_1, "J P Nagar,3rd phase");
			customWait(1000);
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
			enterTextToInputField(Locators_ScoutFT.SWR_enter_City, "Banglore");
			customWait(1000);
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
			enterTextToInputField(Locators_ScoutFT.SWR_Enter_State_Name, "Karnataka");

			customWait(1000);	

			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_UP);
			customWait(1000);
			clickBtn(Locators_ScoutFT.SWR_Reset);
			/*
			 * Validating back from warranty when acess from Device information
			 */
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			customWait(1000);
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			minWait();

			if(isElementExist(Locators_ScoutFT.DevInf_Register)) {
				check4=true;
				APP_LOGS.info("Test case passed Validated");			
			}	

			if((check1)&&(check2)&&(check3)&&(check4)) {
				check= true;
				APP_LOGS.info("Scout App Launched and Device information is able Acess SWR");
				Assert.assertTrue(check);
			}
			else{
				APP_LOGS.info(check1+"\n"+": "+check2+"\n"+": "+check3+ "\n"+check4+ "\n");		
				Assert.fail();
			}	  
		}

		public void validateRemoteControl() throws InterruptedException {
			/*
			 * Validate Remote control Device ID
			 */
			try {
				if(isElementExist(Locators_ScoutFT.RemoteControl_pge)) {
					check1= true;
					APP_LOGS.info("Remote control is Acessed from Scout");	
				}
				customWait(1000);
				if(isElementExist(Locators_ScoutFT.RemoteControl_DeviceID)) {
					String currentDeviceIdName = Locators_ScoutFT.RemoteControl_DeviceID.getText();
					APP_LOGS.info("isDeviceRegistered :"+currentDeviceIdName);
					check2 = true;
					APP_LOGS.info("Device Id is displayed in Remote Control");	
				}

			}catch (NoSuchElementException e) {
				APP_LOGS.info("Error: Application not found.");
				e.printStackTrace();
			}
		}
		
		public void validateChatFields() throws InterruptedException, IOException {
			try {
				if(isElementExist(Locators_ScoutFT.Chat_Title)) {
					check =true;
					APP_LOGS.info("Scout App Launched and Device information is able Acess SWR");
					Assert.assertTrue(check);
				}
				else{
					APP_LOGS.info("Scout App Launched and Device information is  not able  to Access SWR");
					Assert.fail();
				}
			}
			catch (NoSuchElementException e) {
				APP_LOGS.info("Error: Application not found.");
				e.printStackTrace();
			}
		}
		
		public void validate_Current_Screen(WebElement contactTransferTitle) throws InterruptedException{

			/*
			 * Validates the current screen.
			 * Pass the element to be validated as parameter.
			 * 
			 */
			try {
				if(isElementExist(Locators_ScoutFT.ContactTransferTitle)){
					check = true;
					String getTitleText = contactTransferTitle.getText();
					APP_LOGS.info("Current Screen is verified succesfully.");
					APP_LOGS.info("Current Screen title: "+getTitleText);
					softAssert_true(check, "Test case is pass");
				}else{
					APP_LOGS.info("Testcase failed");
					softAssert_false();
				}
			} catch (NoSuchElementException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
		}

		
		public void validateAllOptionsSelect(String title1, String title2, String title3, String title4) {
			/*
			 * After selecting mode of transfer validate directed Browse file Title
			 */
			try {
				while (isTextPresent("ALLOW")) {
					clickBtnWithText("ALLOW");
					minWait();
				}
				select_Transfer_Mode("Bluetooth");
				minWait();
				minWait();
				for(int i=1; i<=4;i++) {
					if(isElementExist(Locators_ScoutFT.AllowBtn));
					{
						clickBtn(Locators_ScoutFT.AllowBtn);
					}
				}
				minWait();
				validateBrowse_Title(Locators_ScoutFT.Title_BTOptn,title1);
				customWait(2000);
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
				customWait(1000);
				select_Transfer_Mode("mdb");
				validateBrowse_Title(Locators_ScoutFT.Title_MBDOptn,title2);
				customWait(1000);
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
				customWait(1000);
				select_Transfer_Mode("vcf");
				validateBrowse_Title(Locators_ScoutFT.Title_VCFOptn,title3);
				customWait(1000);
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
				customWait(1000);
				select_Transfer_Mode("csv");
				validateBrowse_Title(Locators_ScoutFT.Title_CSVOptn,title4);				
			}
			catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}

		}

		public void validateBrowse_Title(WebElement title, String BrowseTitle) throws InterruptedException {
			/*
			 * After selecting mode of transfer validate directed Browse file Title
			 */
			customWait(8000);
			customWait(4000);
			customWait(4000);
			customWait(8000);
			String s=title.getText();
			System.out.println(s);
			if(s.equalsIgnoreCase(BrowseTitle)) {
				check= true;
				APP_LOGS.info("verified Browse Title Contact Imported "+ title);
				Assert.assertTrue(check);
			}else{
				APP_LOGS.info("Unverified Browse Title Contact Imported "+ title);
				Assert.fail();
			}	
		}
		
		public void select_Transfer_Mode(String mode) throws InterruptedException{
			/*
			 * Selects the mode of transfer in contact transfer screen.
			 * Pass mode as parameter.
			 */
			//			minWait();

			try {
				if(mode.equalsIgnoreCase("Bluetooth")){
					clickBtn(Locators_ScoutFT.BluetoothOptn);
					minWait();
					customWait(4000);
					for(int i=1; i<=3; i++) {
						minWait();
						if(isElementExist(Locators_ScoutFT.AllowBtn)) {
							clickBtn(Locators_ScoutFT.AllowBtn);
							minWait();
						}
						if(isElementExist(Locators_ScoutFT.Allow_BT_Btn)) {
							clickBtn(Locators_ScoutFT.Allow_BT_Btn);
							minWait();
						}
					}
				}else if(mode.equalsIgnoreCase("mdb")){
					clickBtn(Locators_ScoutFT.MDBOptn);
					minWait();
					for(int i=1; i<=3; i++) {
						minWait();
						if(isElementExist(Locators_ScoutFT.AllowBtn)) {
							clickBtn(Locators_ScoutFT.AllowBtn);
							minWait();
						}
					}
				}else if(mode.equalsIgnoreCase("vcf")){
					clickBtn(Locators_ScoutFT.VCFOptn);
					minWait();
					for(int i=1;i<=2; i++) {
						if(isElementExist(Locators_ScoutFT.AllowBtn)) {
							clickBtn(Locators_ScoutFT.AllowBtn);
						}
					}
				}else if(mode.equalsIgnoreCase("csv")){
					clickBtn(Locators_ScoutFT.CSVOptn);
					minWait();
					for(int i=1;i<=2; i++) {
						if(isElementExist(Locators_ScoutFT.AllowBtn)) {
							clickBtn(Locators_ScoutFT.AllowBtn);
						}
					}
				}
			} catch (NoSuchElementException e) {
				e.printStackTrace();
			}
		}

		public void selectContactfileToImport(int num) throws InterruptedException {
			/*
			 * Select file and choose all contact to import
			 */
			try {
				if(isElementExist(Locators_ScoutFT.MDBOptn)) {
					clickBtn(Locators_ScoutFT.MDBOptn);
				}
				//aDriver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().className(\"android.widget.ScrollView\")).scrollIntoView(new UiSelector().text(\"Download\"))");
				//scrollText("Download");
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
				//clickBtn(Locators_ScoutFT.DownloadOptn);
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_ENTER);//selects All option
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_ENTER);
				minWait();
				for(int i=0;i<=num;i++) {
					minWait();
					aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
				}
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_ENTER);
			}catch (NoSuchElementException e) {
				//aDriver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().className(\"android.widget.ScrollView\")).scrollIntoView(new UiSelector().text(\"Download\"))");
				//scrollText("Download");
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
				//clickBtn(Locators_ScoutFT.DownloadOptn);
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
				minWait();
				//minWait();
				//clickBtn(Locators_ScoutFT.DownloadOptn);
				//minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_ENTER);//selects All option
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_ENTER);
				minWait();
				for(int i=0;i<=num;i++) {
					minWait();
					aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
				}
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_ENTER);
				APP_LOGS.info("Error: Element not found.");
				e.printStackTrace();
			}	
		}

		public void validateAllContactImport() {
			/*
			 * String validate Imported summary
			 */
			try {
				
				String AboutInfo=Locators_ScoutFT.ImprtSummry_Text.getText();
				//		System.out.println(AboutInfo);
				if(AboutInfo.contains("IMPORTED CONTACTS SUMMARY")) {
					check= true;
					APP_LOGS.info("Contact Imported Summary verified");
					Assert.assertTrue(check);
				}else{
					APP_LOGS.info("Imported Contact is not completed");
					Assert.fail();
				}	
				clickBtn(Locators_ScoutFT.DoneOptn);
			} catch (NoSuchElementException e) {
				e.printStackTrace();
			}
		}
		
		public void validateSummaryHistory() {
			/*
			 * Validate Summary History after importing contacts from any options or modes 
			 */
			try {
				if(isElementExist(Locators_ScoutFT.Title_ImportSummary)) {
					check= true;
					APP_LOGS.info("Verified Summary History for Imported Contacts");				
					Assert.assertTrue(check);
				}else{
					APP_LOGS.info("Unverified Summary History Title Contacts Imported ");
					Assert.fail();
				}	
			}
			catch (Exception e) {
				APP_LOGS.info("Error: Element not found.");
				e.printStackTrace();
			}
		}

		public void pressBackBtn() throws InterruptedException {
			for(int i=0;i<=3;i++) {
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
				minWait();
			}
		}
		
		
		//This method is used to check the deafult state of APPS,Features,Pintime out should be accessible 
		public void verify_Default_States_Of_All_Options_SG() throws InterruptedException, IOException {

			SoftAssert SA5=new SoftAssert();

			try {
				if(sf_app_Check_Activation_Off_On().equals("OFF")) {

					turn_Off_On_SG();
					SA5.assertTrue(Locators_ScoutFT.SG_Applications_String.isEnabled(),"Application is enabed");
					APP_LOGS.info("Checking the Applications is enabled or not :" +Locators_ScoutFT.SG_Applications_String.isEnabled());
					SA5.assertTrue(Locators_ScoutFT.SG_Features_String.isEnabled(),"Feature is enabled");
					APP_LOGS.info("Checking the Feature is enabled or not :" +Locators_ScoutFT.SG_Features_String.isEnabled());
					SA5.assertTrue(Locators_ScoutFT.SG_Change_Pin_String.isEnabled(),"Pin is enabled");
					APP_LOGS.info("Checking the Pin is enabled or not :" +Locators_ScoutFT.SG_Change_Pin_String.isEnabled());

					minWait();
					for(int i=0;i<=5;i++) {
						aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
						customWait(1000);
					}
					minWait();
					SA5.assertTrue(Locators_ScoutFT.SG_Version_String.isEnabled(), "Version is enabled");
					APP_LOGS.info("Checking the Version is enabled or not :" +Locators_ScoutFT.SG_Version_String.isEnabled());
					SA5.assertTrue(Locators_ScoutFT.SG_Help_String.isEnabled(), "Help is enabled");
					APP_LOGS.info("Checking the Help is enabled or not :" +Locators_ScoutFT.SG_Help_String.isEnabled());
				}
				else {
					SA5.assertTrue(Locators_ScoutFT.SG_Applications_String.isEnabled(),"Application is enabed");
					APP_LOGS.info("Checking the Applications is enabled or not :" +Locators_ScoutFT.SG_Applications_String.isEnabled());
					SA5.assertTrue(Locators_ScoutFT.SG_Features_String.isEnabled(),"Feature is enabled");
					APP_LOGS.info("Checking the Feature is enabled or not :" +Locators_ScoutFT.SG_Features_String.isEnabled());
					SA5.assertTrue(Locators_ScoutFT.SG_Change_Pin_String.isEnabled(),"Pin is enabled");
					APP_LOGS.info("Checking the Pin is enabled or not :" +Locators_ScoutFT.SG_Change_Pin_String.isEnabled());

					minWait();
					for(int i=0;i<=5;i++) {
						aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
						customWait(1000);
					}
					minWait();

					SA5.assertTrue(Locators_ScoutFT.SG_Version_String.isEnabled(), "Version is enabled");
					APP_LOGS.info("Checking the Version is enabled or not :" +Locators_ScoutFT.SG_Version_String.isEnabled());
					SA5.assertTrue(Locators_ScoutFT.SG_Help_String.isEnabled(), "Help is enabled");
					APP_LOGS.info("Checking the Help is enabled or not :" +Locators_ScoutFT.SG_Help_String.isEnabled());

				}
				SA5.assertAll();
			} catch (NoSuchElementException e) {
				e.printStackTrace();
			}
		}
		
		public String sf_app_Check_Activation_Off_On() throws InterruptedException, IOException {
			//This method is used to check wheather activation is ON or OFF.
			String status = null;
			try {
				minWait();
				status = null;
				//		navigateToSafeGuard();
				if(Locators_ScoutFT.SG_toggle_btn.getText().equals("OFF")) {
					status=Locators_ScoutFT.SG_toggle_btn.getText();
					APP_LOGS.info("Checking Activation status for OFF" +status);
				}
				else if(Locators_ScoutFT.SG_toggle_btn.getText().equals("ON")){
					status=Locators_ScoutFT.SG_toggle_btn.getText();
					APP_LOGS.info("Checking Activation status for ON" +status);
				}
			} catch (NoSuchElementException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return status;
		}
		
		//This Method will turn OFF/turn On the SF
		public void turn_Off_On_SG() throws InterruptedException {
			try {
				minWait();
				clickBtn(Locators_ScoutFT.SG_toggle_btn);
				customWait(2000);
				enter_Pin();
			} catch (NoSuchElementException e) {
				e.printStackTrace();
			}
		}
		
		public void enter_Pin() throws InterruptedException {
			try {
				minWait();
				minWait();
				aDriver.findElementByAndroidUIAutomator("UiSelector().resourceId(\"com.sonim.safeguard:id/dialog_edit_text\")").sendKeys("1234");
				APP_LOGS.info("PIN has been entered successfully");
				minWait();
				if(Locators_ScoutFT.SG_OK_Btn_for_Pin.isEnabled()) {
					customWait(1000);
					clickBtn(Locators_ScoutFT.SG_OK_Btn_for_Pin);
				}
				else {
					APP_LOGS.info("Pin Ok button is not enabled");
					softAssert_false();
				}
			} catch (NoSuchElementException e) {
				e.printStackTrace();
			}
		}

		
		public void select_All_Apps_And_Check() throws InterruptedException, IOException {
			//clearRecentApps();
			try {
				customWait(1000);
				if(sf_app_Check_Activation_Off_On().equals("OFF")) {
					turn_Off_On_SG();
					pass_Locators(Locators_ScoutFT.SG_Applications_String, Locators_ScoutFT.SG_Select_All_Btn, Locators_ScoutFT.SG_Save_Btn);
					APP_LOGS.info("Launching All the Apps one by one and checking wheather SG locked screen is displayed or not");
					launching_All_Apps_One_By_One();
				}
				else {
					APP_LOGS.info("Else Part : Activation is already ON and checking all the Apps one by one");
					pass_Locators(Locators_ScoutFT.SG_Applications_String, Locators_ScoutFT.SG_Select_All_Btn, Locators_ScoutFT.SG_Save_Btn);
					APP_LOGS.info("Launching All the Apps one by one and checking wheather SG locked screen is displayed or not");
					launching_All_Apps_One_By_One();
				}
			} catch (NoSuchElementException e) {
				e.printStackTrace();
			}
		}
		
		//This is a common method used for 
		public void pass_Locators(WebElement loc1,WebElement loc2,WebElement loc3) throws InterruptedException {
			try {
				minWait();
				clickBtn(loc1);
				minWait();
				enter_Pin();
				customWait(2000);
				aDriver.pressKeyCode(AndroidKeyCode.MENU);
				customWait(3000);
				clickBtn(loc2);
				minWait();
				customWait(3000);
				clickBtn(loc3);
				minWait();
			} catch (NoSuchElementException e) {
				e.printStackTrace();
			}
		}

		//This method is used to validate the SG Locked screen
		//This methos is used to validate whaether an App is launched sucessfully or not
		//This method will launch All the Apps present in the device one by one and validate it 
		public void launching_All_Apps_One_By_One() throws InterruptedException, IOException {

			boolean ch1=false;
			boolean ch2=false;
			boolean ch3=false;
			boolean ch4=false;
			boolean ch5=false;
			boolean ch6=false;
			boolean ch7=false;
			boolean ch8=false;
			boolean ch9=false;
			boolean ch10=false;
			boolean ch11=false;
			boolean ch12=false;
			boolean ch13=false;
		
			SoftAssert SA7=new SoftAssert();
			//clearRecentApps();
			customWait(1000);
			Runtime.getRuntime().exec("adb shell am start -n  com.caf.fmradio/com.caf.fmradio.FMRadio");
			APP_LOGS.info("FM launched successfully");
			customWait(2000);
			try {
				if(isElementExist(Locators_ScoutFT.SG_Locked_Screen_Validation) || isElementExist(Locators_ScoutFT.SG_Safe_Guard_Locked_Screen_Edit_Box)) {
					ch1=true;
					APP_LOGS.info("ch1: "+ch1);
				}
			}
			catch(NoSuchElementException e) {
				e.printStackTrace();
				APP_LOGS.info("Element not Found Execption:FM Radio");
			}
			minWait();
			Runtime.getRuntime().exec("adb shell am start -n com.android.mms/com.android.mms.ui.ConversationList");
			APP_LOGS.info("mms launched successfully");
			customWait(2000);
			try {
				if(isElementExist(Locators_ScoutFT.SG_Locked_Screen_Validation) || isElementExist(Locators_ScoutFT.SG_Safe_Guard_Locked_Screen_Edit_Box)) {
					ch2=true;
					APP_LOGS.info("ch2: "+ch2);
				}
			}
			catch(NoSuchElementException e) {
				e.printStackTrace();
				APP_LOGS.info("Element not Found Execption:mms");
			}
			minWait();
			//clearRecentApps();
			Runtime.getRuntime().exec("adb shell am start -n org.codeaurora.snapcam/com.android.camera.CameraActivity");
			APP_LOGS.info("Camera launched successfully");
			customWait(2000);
			try {
				if(isElementExist(Locators_ScoutFT.SG_Locked_Screen_Validation)) {
					ch3=true;	
					APP_LOGS.info("ch3: "+ch3);
				}
			}
			catch (NoSuchElementException e) {
				e.printStackTrace();
				APP_LOGS.info("Element not Found Execption:Camera");
			}
			minWait();
			//clearRecentApps();
			Runtime.getRuntime().exec("adb shell am start -n com.android.soundrecorder/com.android.soundrecorder.SoundRecorder");
			APP_LOGS.info("sound recorder launched successfully");
			customWait(3000);
			try {
				if(isElementExist(Locators_ScoutFT.SG_Locked_Screen_Validation) || isElementExist(Locators_ScoutFT.SG_Safe_Guard_Locked_Screen_Edit_Box)) {
					ch4=true;
					APP_LOGS.info("ch4: "+ch4);
				}
			}
			catch (NoSuchElementException e) {
				e.printStackTrace();
				APP_LOGS.info("Element not Found Execption:Sound Recorder");
			}
			minWait();
			//clearRecentApps();
			Runtime.getRuntime().exec("adb shell am start -n com.google.android.deskclock/com.android.deskclock.DeskClock");
			APP_LOGS.info("Clock launched successfully");
			customWait(3000);
			try {
				if(isElementExist(Locators_ScoutFT.SG_Locked_Screen_Validation)) {
					ch5=true;
					APP_LOGS.info("ch5: "+ch5);
				}
			}
			catch (NoSuchElementException e) {
				e.printStackTrace();
				APP_LOGS.info("Element not Found Execption:clock");
			}
			minWait();
			//clearRecentApps();
			Runtime.getRuntime().exec("adb shell am start -n com.android.chrome/org.chromium.chrome.browser.firstrun.FirstRunActivity");
			APP_LOGS.info("Browser launched successfully");
			customWait(3000);
			try {
				if(isElementExist(Locators_ScoutFT.SG_Locked_Screen_Validation)) {
					ch6=true;
					APP_LOGS.info("ch6: "+ch6);
				}
			}
			catch(NoSuchElementException e) {
				e.printStackTrace();
				APP_LOGS.info("Element not Found Execption:browser");
			}
			minWait();
			//clearRecentApps();
			Runtime.getRuntime().exec("adb shell am start -n com.android.contacts/com.android.contacts.activities.PeopleActivity");
			APP_LOGS.info("Contact launched successfully");
			customWait(3000);
			try {
				if(isElementExist(Locators_ScoutFT.SG_Locked_Screen_Validation) || isElementExist(Locators_ScoutFT.SG_Safe_Guard_Locked_Screen_Edit_Box)) {
					ch7=true;
					APP_LOGS.info("ch7: "+ch7);
				}
			}
			catch (NoSuchElementException e) {
				e.printStackTrace();
				APP_LOGS.info("Element not Found Execption:Contact App");
			}
			minWait();
			Runtime.getRuntime().exec("adb shell am start -n com.android.dialer/com.android.dialer.DialerMainActivity");
			APP_LOGS.info("Dialer launched successfully");
			customWait(3000);
			try {
				if(isElementExist(Locators_ScoutFT.SG_Locked_Screen_Validation) || isElementExist(Locators_ScoutFT.SG_Safe_Guard_Locked_Screen_Edit_Box)) {
					ch8=true;
					APP_LOGS.info("ch8: "+ch8);
				}
			}
			catch (NoSuchElementException e) {
				e.printStackTrace();
				APP_LOGS.info("Element not Found Execption:Dialer");
			}

			minWait();
			//clearRecentApps();
			Runtime.getRuntime().exec("adb shell am start -n com.android.gallery3d/com.android.gallery3d.app.Gallery");
			APP_LOGS.info("Gallery launched successfully");
			customWait(3000);
			try {
				if(isElementExist(Locators_ScoutFT.SG_Locked_Screen_Validation) || isElementExist(Locators_ScoutFT.SG_Safe_Guard_Locked_Screen_Edit_Box)) {
					ch9=true;
					APP_LOGS.info("ch9: "+ch9);
				}
			}
			catch (NoSuchElementException e) {
				e.printStackTrace();
				APP_LOGS.info("Element not Found Execption:Gallery");
			}
			minWait();
			Runtime.getRuntime().exec("adb shell am start -n com.android.settings/com.android.settings.Settings");
			APP_LOGS.info("Settings launched successfully");
			customWait(3000);
			try {
				if(isElementExist(Locators_ScoutFT.SG_Locked_Screen_Validation) || isElementExist(Locators_ScoutFT.SG_Safe_Guard_Locked_Screen_Edit_Box)) {
					ch10=true;
					APP_LOGS.info("ch10: "+ch10);
				}
			}
			catch (NoSuchElementException e) {
				e.printStackTrace();
				APP_LOGS.info("Element not Found Execption:Settings");
			}
			minWait();
			//clearRecentApps();
			Runtime.getRuntime().exec("adb shell am start -n com.google.android.calculator/com.android.calculator2.Calculator");
			APP_LOGS.info("Calculator launched successfully");
			customWait(3000);
			try {
				if(isElementExist(Locators_ScoutFT.SG_Locked_Screen_Validation) || isElementExist(Locators_ScoutFT.SG_Safe_Guard_Locked_Screen_Edit_Box)) {
					ch11=true;
					APP_LOGS.info("ch11: "+ch11);
				}
			}
			catch (NoSuchElementException e) {
				e.printStackTrace();
				APP_LOGS.info("Element not Found Execption:Calculator");
			}
			minWait();
			//clearRecentApps();
			Runtime.getRuntime().exec("adb shell am start -n com.android.calendar/com.android.calendar.AllInOneActivity");
			APP_LOGS.info("Calender launched successfully");
			customWait(3000);
			try {
				if(isElementExist(Locators_ScoutFT.SG_Locked_Screen_Validation) || isElementExist(Locators_ScoutFT.SG_Safe_Guard_Locked_Screen_Edit_Box)) {
					ch12=true;
					APP_LOGS.info("ch12: "+ch12);
				}
			}
			catch (NoSuchElementException e) {
				e.printStackTrace();
				APP_LOGS.info("Element not Found Execption:Calemder");
			}
			minWait();
			//clearRecentApps();
			Runtime.getRuntime().exec("adb shell am start -n com.android.documentsui/com.android.documentsui.FilesActivity");
			APP_LOGS.info("Download launched successfully");
			customWait(3000);
			try {
				if(isElementExist(Locators_ScoutFT.SG_Locked_Screen_Validation) || isElementExist(Locators_ScoutFT.SG_Safe_Guard_Locked_Screen_Edit_Box)) {
					ch13=true;
					APP_LOGS.info("ch13: "+ch13);
				}
			}
			catch (NoSuchElementException e) {
				e.printStackTrace();
				APP_LOGS.info("Element not Found Execption:Download");
			}
			
			if((ch1) && (ch2) && (ch3) && (ch4) && (ch5) && (ch6) && (ch7) && (ch8) && (ch10) && (ch9) && (ch11) && (ch12) && (ch13)) {
				check=true;
				APP_LOGS.info("All APPS launched successfully");
				SA7.assertTrue(check, "All PASS");
			}
			else {
				APP_LOGS.info("Values of All Apps are :" +ch1+ "," +ch2+ "," +ch3+ "," +ch4+ "," +ch5+ "," +ch6+ "," +ch7+ "," +ch9+ "," +ch10+ "," +ch8+ "," +ch11+ "," +ch12+ "," +ch13);
				SA7.fail();
			}
			SA7.assertAll();
			minWait();
			//clearRecentApps();
		}
		

		public void Unselect_All_Apps_And_Check() throws InterruptedException, IOException {
//			clearRecentApps();
			try {
				customWait(2000);	
				if(special_case().equals("OFF")) {
					customWait(2000);
					turn_Off_On_SG();
					pass_Locators(Locators_ScoutFT.SG_Applications_String, Locators_ScoutFT.SG_Un_Select_All_Btn, Locators_ScoutFT.SG_Save_Btn);
					APP_LOGS.info("Launching All the Apps one by one and checking wheather SG locked screen is displayed or not");
					launching_All_Apps_One_By_One_With_Out_Locked_Screen();
				}
				else {
					APP_LOGS.info("Else Part : Activation is already ON and checking all the Apps one by one");
					pass_Locators(Locators_ScoutFT.SG_Applications_String, Locators_ScoutFT.SG_Un_Select_All_Btn, Locators_ScoutFT.SG_Save_Btn);
					APP_LOGS.info("Launching All the Apps one by one and checking wheather SG locked screen is displayed or not");
					launching_All_Apps_One_By_One_With_Out_Locked_Screen();
				}
			} catch (NoSuchElementException e) {
				e.printStackTrace();
			}
		}

		
		public String special_case() throws InterruptedException, IOException {
			//This method is used to check wheather activation is ON or OFF.
			
			minWait();
			String status = null;
			minWait();

			try {
				if(Locators_ScoutFT.SG_toggle_btn.getText().equals("OFF")) {
					status=Locators_ScoutFT.SG_toggle_btn.getText();
					APP_LOGS.info("Checking Activation status for OFF" +status);
				}
				else if(Locators_ScoutFT.SG_toggle_btn.getText().equals("ON")){
					status=Locators_ScoutFT.SG_toggle_btn.getText();
					APP_LOGS.info("Checking Activation status for ON" +status);
				}
			} catch (NoSuchElementException e) {
				e.printStackTrace();
			}
			return status;
		}
		
		public void launching_All_Apps_One_By_One_With_Out_Locked_Screen() throws IOException, InterruptedException {

			boolean ch1=false;
			boolean ch2=false;
			boolean ch3=false;
			boolean ch4=false;
			boolean ch5=false;
			boolean ch6=false;
			boolean ch7=false;
			boolean ch8=false;
			boolean ch9=false;
			boolean ch10=false;
			boolean ch11=false;
			boolean ch12=false;
			boolean ch13=false;
			boolean ch14=false;
			boolean ch15=false;
			boolean ch16=false;
			boolean ch17=false;

			SoftAssert SA8=new SoftAssert();
			Runtime.getRuntime().exec("adb shell am start -n  com.caf.fmradio/com.caf.fmradio.FMRadio");
			APP_LOGS.info("FM launched successfully");
			customWait(2000);
			if(isElementExist(Locators_ScoutFT.SG_FM)) {
				ch1=true;
				APP_LOGS.info("ch1: "+ch1);
			}
			minWait();
			Runtime.getRuntime().exec("adb shell am start -n com.android.mms/com.android.mms.ui.ConversationList");
			APP_LOGS.info("mms launched successfully");
			customWait(2000);
			customWait(2000);
			if(isElementExist(Locators_ScoutFT.SG_Message)){
				ch2=true;
				APP_LOGS.info("ch2: "+ch2);
			}
			minWait();
			Runtime.getRuntime().exec("adb shell am start -n org.codeaurora.snapcam/com.android.camera.CameraActivity");
			APP_LOGS.info("Camera launched successfully");
			customWait(2000);
			if(isElementExist(Locators_ScoutFT.SG_Camera)) {
				ch3=true;
				APP_LOGS.info("ch3: "+ch3);
			}
			minWait();
			Runtime.getRuntime().exec("adb shell am start -n com.android.soundrecorder/com.android.soundrecorder.SoundRecorder");
			APP_LOGS.info("sound recorder launched successfully");
			customWait(2000);
			if (isTextPresent("CANCEL")) {
				clickBtnWithText("CANCEL");
			}
			if(isElementExist(Locators_ScoutFT.SG_Sound_Recoder)){
				ch4=true;
				APP_LOGS.info("ch4: "+ch4);
			}
			minWait();
			Runtime.getRuntime().exec("adb shell am start -n com.sonimtech.setupwizard/com.sonimtech.setupwizard.SetupLauncherActivity");
			APP_LOGS.info("Setup Wizard launched successfully");
			customWait(2000);
			if(isElementExist(Locators_ScoutFT.SG_SetUpwizard)) {
				ch5=true;
				APP_LOGS.info("ch5: "+ch5);
			}
			minWait();
			Runtime.getRuntime().exec("adb shell am start -n com.android.contacts/com.android.contacts.activities.PeopleActivity");
			APP_LOGS.info("Contact launched successfully");
			customWait(2000);
			if((isElementExist(Locators_ScoutFT.SG_ADD_CONTACT_String) || isElementExist(Locators_ScoutFT.SG_Find_Contacts))) {
				ch6=true;
				APP_LOGS.info("ch6: "+ch6);
			}
			minWait();
			Runtime.getRuntime().exec("adb shell am start -n com.android.dialer/com.android.dialer.DialtactsActivity");
			APP_LOGS.info("Dialer launched successfully"); 
			customWait(2000);
			if(isElementExist(Locators_ScoutFT.SG_RecentCalls)) {
				ch7=true;
				APP_LOGS.info("ch7: "+ch7);
			}
		
			if((ch1)&& (ch2)  && (ch3) && (ch4) && (ch5) && (ch6) && (ch7) ) {
				check=true;
				APP_LOGS.info("All APPS launched successfully");
				SA8.assertTrue(check, "All PASS");
			}
			else {
				APP_LOGS.info("Values of All Apps are :" +ch1+ "," +ch2+ "," +ch3+ "," +ch4+ "," +ch5+ "," +ch6+ "," +ch7);
				SA8.fail();
			}
			SA8.assertAll();
			minWait();
		
		}
		
		public void special_Case_Unselect() throws InterruptedException {
				try {
					clickBtn(Locators_ScoutFT.SG_Applications_String);
					minWait();
					enter_Pin();
					customWait(1000);
					clickBtn(Locators_ScoutFT.SG_More_Optns);
					customWait(2000);
					customWait(2000);
					if(isElementExist(Locators_ScoutFT.SG_Un_Select_All_Btn)) {
						clickBtn(Locators_ScoutFT.SG_Un_Select_All_Btn);
						minWait();
						minWait();
						clickBtn(Locators_ScoutFT.SG_Save_Btn);
						minWait();
					}
					else{
						aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
					}
				
				} catch (NoSuchElementException e) {
					e.printStackTrace();
				}	
		}
		
		//This method is used to check the feature Tab present in the SG
		public void check_Features_Tab() throws InterruptedException, IOException {

				//clearRecentApps();
				try {
					minWait();
					if(sf_app_Check_Activation_Off_On().equals("OFF")) {
						turn_Off_On_SG();
						minWait();
						clickBtn(Locators_ScoutFT.SG_Features_String);
						minWait();
						enter_Pin();
						customWait(2000);
//						aDriver.pressKeyCode(AndroidKeyCode.MENU);
						clickBtn(Locators_ScoutFT.SG_More_Optns);
						customWait(2000);
						clickBtn(Locators_ScoutFT.SG_Select_All_Btn);
						minWait();
						//aDriver.pressKeyCode(AndroidKeyCode.MENU);
						customWait(2000);
						clickBtn(Locators_ScoutFT.SG_Save_Btn);
						minWait();
						APP_LOGS.info("Launching All features one by one");
						launchFeatures();
					}
					else {
						APP_LOGS.info("Checking the features When Activastion is ON ");
						minWait();
						clickBtn(Locators_ScoutFT.SG_Features_String);
						minWait();
						enter_Pin();
						customWait(2000);
						clickBtn(Locators_ScoutFT.SG_More_Optns);
						customWait(2000);
						clickBtn(Locators_ScoutFT.SG_Select_All_Btn);
						customWait(2000);
//					aDriver.pressKeyCode(AndroidKeyCode.MENU);
						customWait(2000);
						clickBtn(Locators_ScoutFT.SG_Save_Btn);
						minWait();
						APP_LOGS.info("Launching All the Features");
						launchFeatures();
					}
				} catch (NoSuchElementException e) {
					e.printStackTrace();
				}
			}
			
			
		
		public void launchFeatures() throws IOException, InterruptedException {

			boolean ch1=false;
			boolean ch2=false;
			boolean ch3=false;
			boolean ch4=false;
			boolean ch5=false;
			boolean ch6=false;
			boolean ch7=false;
			boolean ch8=false;

			SoftAssert SA=new SoftAssert();


			try {
		
				//This part is used for scrolling tof
				minWait();
//				clearRecentApps();
				Runtime.getRuntime().exec("adb shell am start -n com.android.settings/com.android.settings.Settings");
				customWait(2000);
				aDriver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().resourceId(\"com.android.settings:id/main_content\")).scrollIntoView(new UiSelector().text(\"Backup & reset\"))");
				customWait(2000);
				clickBtn(Locators_ScoutFT.SG_Factory_reset);

				customWait(2000);
				if(isElementExist(Locators_ScoutFT.SG_Locked_Screen_Validation) || isElementExist(Locators_ScoutFT.SG_Safe_Guard_Locked_Screen_Edit_Box)) {

					check5=true;
					APP_LOGS.info("TC Passed FActory Reset" +check5);
					//  softAssert_true(check, "Messages");
					SA.assertTrue(check, "");  
				}
				else {
					APP_LOGS.info("Test is for factory Reset");
					//  softAssert_false();
					SA.fail();
				}

				//This part is used for scrolling tof
//				clearRecentApps();  
				Runtime.getRuntime().exec("adb shell am start -n com.android.settings/com.android.settings.Settings");
				customWait(2000);
				aDriver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().resourceId(\"com.android.settings:id/main_content\")).scrollIntoView(new UiSelector().text(\"Security\"))");
				clickBtn(Locators_ScoutFT.SG_Security);
				customWait(2000);
				if(isElementExist(Locators_ScoutFT.SG_Locked_Screen_Validation) || isElementExist(Locators_ScoutFT.SG_Safe_Guard_Locked_Screen_Edit_Box)) {
					check6=true;
					APP_LOGS.info("TC Passed Security" +check6);
					SA.assertTrue(check, "");
				}
				else {
					APP_LOGS.info("Test is for Security");
					SA.fail();
				}
				//This is for WIFI 
				//clearRecentApps();
				customWait(2000);
				Runtime.getRuntime().exec("adb shell am start -a android.intent.action.MAIN -n com.android.settings/.wifi.WifiSettings");
				//aDriver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().resourceId(\"com.android.settings:id/main_content\")).scrollIntoView(new UiSelector().text(\"WLAN\"))");
				customWait(2000);
				clickBtn(Locators_ScoutFT.SG_Location_Off_On_Btn);
				customWait(2000);
				if(isElementExist(Locators_ScoutFT.SG_Locked_Screen_Validation) || isElementExist(Locators_ScoutFT.SG_Safe_Guard_Locked_Screen_Edit_Box)) {
					check=true;
					APP_LOGS.info("TC Passed" +check);
					//  softAssert_true(check, "Messages");
					SA.assertTrue(check, "");
				}
				else {
					APP_LOGS.info("Test is for factory Reset");
					// softAssert_false();
					SA.fail();
				}
				SA.assertAll();
			} catch (NoSuchElementException e) {
				e.printStackTrace();
			}
		}
		
	

		public void enter_Pin_App() throws InterruptedException {
			try {
				minWait();
				minWait();
				aDriver.findElementByAndroidUIAutomator("UiSelector().resourceId(\"com.sonim.safeguard:id/editText\")").sendKeys("1234");
				APP_LOGS.info("PIN has been entered successfully");
				minWait();
				if(Locators_ScoutFT.SG_OK_Btn_for_PinApp.isEnabled()) {
					customWait(1000);
					clickBtn(Locators_ScoutFT.SG_OK_Btn_for_PinApp);
				}
				else {
					APP_LOGS.info("Pin Ok button is not enabled");
					softAssert_false();
				}
			} catch (NoSuchElementException e) {
				e.printStackTrace();
			}
		}
		
		
		public void selectApplicationPinTimeOut(String option) throws InterruptedException {
			try {
				minWait();
				if(Locators_ScoutFT.safeguardStatus.getText().equalsIgnoreCase("OFF")) {
					turn_Off_On_SG();
					APP_LOGS.info("SafeGuard has been turned ON");
				}
				else {
					APP_LOGS.info("SafeGuard is already ON");
				}
				navigateUsingText("Application PIN Timeout");
				String template = "new UiSelector().className(\"android.widget.CheckedTextView\")";
				String textElement = ".text(\""+ option + "\")";
				aDriver.findElementByAndroidUIAutomator(template + textElement).click();
				minWait();
				APP_LOGS.info("Application PIN Timeout set to "+ option);
			}
			catch (NoSuchElementException e) {
				e.printStackTrace();
			}
		}
		
		public void selectApplicationForSafeGuard(String app) throws InterruptedException {
			try {
				minWait();
				navigateUsingText("Applications");
				minWait();
				enter_Pin();
				customWait(2000);
				//navigateUsingText(app);
				/*System.out.println("//android.widget.TextView[@text=\'"+app+"\']");
				aDriver.findElementByXPath("//android.widget.TextView[@text=\'"+app+"\']").click();*/
				//aDriver.findElementByXPath("//android.widget.ListView/android.widget.RelativeLayout//*[text()=\'"+ app + "\']");
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_RIGHT);
				minWait();
				//aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_UP);
				minWait_SonimCare();
				clickBtn(Locators_ScoutFT.saveText);
				//clickText("SAVE");
				//android.widget.RelativeLayout[android.widget.LinearLayout/android.widget.TextView/@text='Chrome']
				APP_LOGS.info("Application selected successfully ");
			}
			catch (NoSuchElementException e) {
				APP_LOGS.error("Application not found-- "+app);
			}
		}
		
		public void appLaunchCheckTimeout(String application, String option) throws InterruptedException {
			boolean check1 = false;
			boolean check2 = false;
			boolean check3 = false;
			
			SoftAssert SA=new SoftAssert();
			
			try {
				aDriver.pressKeyCode(AndroidKeyCode.HOME);
				if (option.equalsIgnoreCase("Immediate")) {
					launch_application(application);
					minWait();
					aDriver.pressKeyCode(AndroidKeyCode.HOME);
					minWait();
					aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_APP_SWITCH);
					navigateUsingText(application);
					APP_LOGS.info("Application launching");
					if (isTextPresent("Enter PIN")) {
						enter_Pin_App();
						check1 = true;
						SA.assertTrue(check1, "Application is asking for PIN");
						APP_LOGS.info("Application is asking for PIN");
					}
					else {
						SA.fail("Application didn't ask for PIN");
						}
				}
				else if (option.equalsIgnoreCase("30 Seconds")) {
						launch_application(application);
						minWait();
						aDriver.pressKeyCode(AndroidKeyCode.HOME);
						minWait();
						aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_APP_SWITCH);
						navigateUsingText(application);
					}
				}
			catch (NoSuchElementException e) {
				APP_LOGS.error("Application not found-- "+ application);
			}
		}
		
		public void sonimCareSelect(String feature) throws InterruptedException{			
			/*
			 * Launches feature of sonimcare application
			 * Pass the webelement of feature as parameter
			 */
			minWait();
			navigateUsingText(feature);
			minWait();		
		}
		
		public void click_On_coninueBtn_featureScreen() throws InterruptedException{
			/*
			 * Clicks on continue button displayed in each feature screen.
			 */
			try {
				minWait_SonimCare();
				clickBtn(Locators_ScoutFT.continueBtnFeatureScreen);
			} catch (NoSuchElementException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				Assert.fail();
			}					
		}
		
		public void validate_Device_Info_Self_Diagnosis_screen() throws InterruptedException{
			/*
			 * Validates all the valuese(texts) of device information in self diagnosis screen.
			 */
			SoftAssert SA1 = new SoftAssert();
			try {
				minWait_SonimCare();
				if(isElementExist(Locators_ScoutFT.model_value_text))
				{
					check1 = true;
					APP_LOGS.info("check1: " + check1);
					APP_LOGS.info("model_value_text Element is present");
					String getAlertText = Locators_ScoutFT.model_value_text.getText();
					APP_LOGS.info("model_value_text: " + getAlertText);
				}else
				{
					APP_LOGS.info("check1: "+check1);
					APP_LOGS.info("model_value_text Element is not present");			
				}
				pressDownKey();
				minWait_SonimCare();
				if(isElementExist(Locators_ScoutFT.imei_value_text))
				{
					check2 = true;
					APP_LOGS.info("check2: " + check2);
					APP_LOGS.info("imei_value_text Element is present");
					String getAlertText = Locators_ScoutFT.imei_value_text.getText();
					APP_LOGS.info("imei_value_text: " + getAlertText);
				}else
				{
					APP_LOGS.info("check2: "+check2);
					APP_LOGS.info("model_value_text Element is not present");			
				}
				pressDownKey();
				minWait_SonimCare();
				if(isElementExist(Locators_ScoutFT.android_version_value_text))
				{
					check3 = true;
					APP_LOGS.info("check3: " + check3);
					APP_LOGS.info("android_version_value_text Element is present");
					String getAlertText = Locators_ScoutFT.android_version_value_text.getText();
					APP_LOGS.info("android_version_value_text: " + getAlertText);
				}else
				{
					APP_LOGS.info("check3: "+check3);
					APP_LOGS.info("android_version_value_text Element is not present");			
				}
				pressDownKey();
				minWait_SonimCare();
				if(isElementExist(Locators_ScoutFT.build_value_text))
				{
					check4 = true;
					APP_LOGS.info("check4: " + check4);
					APP_LOGS.info("build_value_text Element is present");
					String getAlertText = Locators_ScoutFT.build_value_text.getText();
					APP_LOGS.info("build_value_text: " + getAlertText);
				}else
				{
					APP_LOGS.info("check4: "+check4);
					APP_LOGS.info("build_value_text Element is not present");			
				}
				pressDownKey();
				minWait_SonimCare();
				if(isElementExist(Locators_ScoutFT.phone_number_value_text))
				{
					check5 = true;
					APP_LOGS.info("check5: " + check5);
					APP_LOGS.info("phone_number_value_text Element is present");
					String getAlertText = Locators_ScoutFT.phone_number_value_text.getText();
					APP_LOGS.info("phone_number_value_text: " + getAlertText);
				}else
				{
					APP_LOGS.info("check5: "+check5);
					APP_LOGS.info("phone_number_value_text Element is not present");			
				}
				pressDownKey();
				pressDownKey();
				minWait_SonimCare();
				if(isElementExist(Locators_ScoutFT.network_type_value_text))
				{
					check6 = true;
					APP_LOGS.info("check6: " + check6);
					APP_LOGS.info("network_type_value_text Element is present");
					String getAlertText = Locators_ScoutFT.network_type_value_text.getText();
					APP_LOGS.info("network_type_value_text: " + getAlertText);
				}else
				{
					APP_LOGS.info("check6: "+check6);
					APP_LOGS.info("network_type_value_text Element is not present");			
				}
				pressDownKey();
				minWait_SonimCare();
				if(isElementExist(Locators_ScoutFT.signal_strength_value_text))
				{
					check7 = true;
					APP_LOGS.info("check7: " + check7);
					APP_LOGS.info("signal_strength_value_text Element is present");
					String getAlertText = Locators_ScoutFT.signal_strength_value_text.getText();
					APP_LOGS.info("signal_strength_value_text: " + getAlertText);
				}else
				{
					APP_LOGS.info("check7: "+check7);
					APP_LOGS.info("signal_strength_value_text Element is not present");			
				}
				pressDownKey();
				minWait_SonimCare();
				if(isElementExist(Locators_ScoutFT.on_roaming_value_text))
				{
					check8 = true;
					APP_LOGS.info("check8: " + check8);
					APP_LOGS.info("on_roaming_value_text Element is present");
					String getAlertText = Locators_ScoutFT.on_roaming_value_text.getText();
					APP_LOGS.info("on_roaming_value_text: " + getAlertText);
				}else
				{
					APP_LOGS.info("check8: "+check8);
					APP_LOGS.info("on_roaming_value_text Element is not present");			
				}
				pressDownKey();
				minWait_SonimCare();
				if(isElementExist(Locators_ScoutFT.data_value_text))
				{
					check9 = true;
					APP_LOGS.info("check9: " + check9);
					APP_LOGS.info("data_value_text Element is present");
					String getAlertText = Locators_ScoutFT.data_value_text.getText();
					APP_LOGS.info("data_value_text: " + getAlertText);
				}else
				{
					APP_LOGS.info("check9: "+check9);
					APP_LOGS.info("data_value_text Element is not present");			
				}
				pressDownKey();
				minWait_SonimCare();
				if(isElementExist(Locators_ScoutFT.network_value_text))
				{
					check10 = true;
					APP_LOGS.info("check10: " + check10);
					APP_LOGS.info("network_value_text Element is present");
					String getAlertText = Locators_ScoutFT.network_value_text.getText();
					APP_LOGS.info("network_value_text: " + getAlertText);
				}else
				{
					APP_LOGS.info("check10: "+check10);
					APP_LOGS.info("network_value_text Element is not present");			
				}

				if((check1)&&(check2)&&(check3)&&(check4)&&(check5)&&(check6)&&(check7)&&(check8)&&(check9)&&(check10)){
					check=true;
					APP_LOGS.info("All static device info of self diagnosis screen is displayed successfully.");			
					pressBackKey();
					minWait_SonimCare();
					SA1.assertTrue(check, "  ");
				}else{
					APP_LOGS.info("Test case failed");			
					pressBackKey();
					minWait_SonimCare();
					SA1.fail();
				}
			} catch (NoSuchElementException e) {

				e.printStackTrace();
				SA1.fail();
			
			}			
			SA1.assertAll();
		}
		
		public void pressDownKey(){
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
		}
		
		public void pressBackKey(){
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
		}
	
		public void navigateToDiagnosisScreen() {
			while (isElementExistWithText("Continue")) {
				navigateUsingText("Continue");
				APP_LOGS.info("Clicked on continue successfully");
			}
		}
		
		public void navigateToIndividualFeature(String feature) {
			if (feature.equalsIgnoreCase("TEST ALL"))
				clickBtnWithText(feature);
			else
				scrollText(feature);
				clickBtnWithText(feature);
		}
		
		public void validate_wifiScan() throws InterruptedException{
			/*
			 * 
			 * Scans available wifi and performs wifi test.
			 * validates wifi scan
			 */
		SoftAssert	SA2 = new SoftAssert();
			try {
				minWait_SonimCare();
				clickBtn(Locators_ScoutFT.scanForMoreNetworksBtn);
				customWait(6000);
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
				minWait_SonimCare();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_LEFT);
				minWait_SonimCare();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
				minWait_SonimCare();		
				if(isElementExist(Locators_ScoutFT.selfDiagnosisTitle)){
					check=true;
					APP_LOGS.info("Navigated back to Test All Screen succesfully");
					SA2.assertTrue(check, " ");
				}else{
					APP_LOGS.info("Not navigated to test all screen");
					SA2.fail();
				} 
				back();
			} catch (NoSuchElementException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				SA2.fail();
			}		
			SA2.assertAll();
		}
		
		
		public void back() throws InterruptedException {
			/*
			 * This method will make application to front page
			 */
			try {
				for(int i=1; i<=4; i++) {
				if(isElementExist(Locators_ScoutFT.No_btn)) {
					clickBtn(Locators_ScoutFT.No_btn);
					minWait();
					break;
				}
				else {
					aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
					minWait();
				continue;
				}
				}
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
				minWait();
			} catch (NoSuchElementException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				Assert.fail();
			}
		}
		
		public void validate_backCamera() throws InterruptedException{
			/*
			 * takes a picture and saves.
			 * Validates picture taken.
			 */
			
			WebDriverWait wait = new WebDriverWait(aDriver, 30);
			
			
			
			SoftAssert SA3 = new SoftAssert();
			try {
				customWait(4000);
				clickBtn(Locators_ScoutFT.cameraIcon);
				minWait_SonimCare();
				wait.until(ExpectedConditions.presenceOfElementLocated(By
			            .xpath("//android.widget.ImageView[@resource-id='org.codeaurora.snapcam:id/preview_btn_done']")));
				clickBtn(Locators_ScoutFT.acceptCameraImagePreview);
				customWait(4000);
				if(isElementExist(Locators_ScoutFT.cameraCOnfirmation_text)){
					check=true;
					APP_LOGS.info("Test result confirmation message is displayed succesfully.");
					SA3.assertTrue(check, " ");
				}else{
					APP_LOGS.info("Test result confirmation message is not displayed.");
					SA3.fail();
				}
				minWait();
				clickBtn(Locators_ScoutFT.cameraPass_Confirmation_dialog);
				back();
			} catch (NoSuchElementException e) {
				e.printStackTrace();
				SA3.fail();
			}	
			SA3.assertAll();
		}
		
		public void validate_BatteryTest(String bat_Status, String bat_status_value_1, String bat_status_value_2, String bat_Electricity, String bat_Electricity_value ) throws InterruptedException{
			/*
			 * Validates battery test.
			 * Pass battery status and battery electricity as parameters.
			 */		
			SoftAssert SS1 = new SoftAssert();
			try {
				minWait_SonimCare();
				String get_bat_status = Locators_ScoutFT.batteryStatusLabel.getText();
				if(get_bat_status.equalsIgnoreCase(bat_Status)){
					check1=true;
					APP_LOGS.info("Label:"+get_bat_status);
				}
				String get_bat_value = Locators_ScoutFT.batteryStatusValue.getText();
				if(get_bat_value.equalsIgnoreCase(bat_status_value_1)||get_bat_value.equalsIgnoreCase(bat_status_value_2)){
					check2=true;
					APP_LOGS.info("Value:"+get_bat_value);
				}		
				String get_bat_Electricity = Locators_ScoutFT.batteryElectricityLabel.getText();
				if(get_bat_Electricity.equalsIgnoreCase(bat_Electricity)){
					check3=true;
					APP_LOGS.info("Label:"+get_bat_Electricity);
				}
				String get_bat_Electricity_value = Locators_ScoutFT.batteryElectricityValue.getText();
				if(get_bat_Electricity_value.equalsIgnoreCase(bat_Electricity_value)){
					check4=true;
					APP_LOGS.info("Value:"+get_bat_Electricity_value);
				}
				if((check1)&&(check2)&&(check3)&&(check4)){
					check= true;
					APP_LOGS.info("Static parameters are displayed properly.");
					APP_LOGS.info("TC is Passed.");
					clickBtn(Locators_ScoutFT.YES_btn_wifiScanResult);
					//selectYESButton();
					 SS1.assertTrue(check, " ");
				}else{
					APP_LOGS.info(get_bat_status+": "+check1+"\n"+get_bat_value+": "+check2+"\n"
							+get_bat_Electricity+": "+check3+"\n"+get_bat_Electricity_value+": "+check4);
					clickBtn(Locators_ScoutFT.YES_btn_wifiScanResult);
					//selectYESButton();
					 SS1.fail();
				}  back();
			} catch (NoSuchElementException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				 SS1.fail();
			}		
			SS1.assertAll();
		}
		
		public void validate_ClearCacheMemory() throws InterruptedException{
			/*
			 * validates cache memory feature.
			 */
			SoftAssert SS2 = new SoftAssert();
			try {
				customWait(6000);
				scrollToText("Cached data");
				
				/*for(int i=0;i<7;i++){
					aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
					minWait_SonimCare();
				}*/
				//Locators_ScoutSanity.scrollToCacheMemory();
				//aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
				minWait_SonimCare();
				clickBtn(Locators_ScoutFT.OK_btn_resultConfirmation_dialog);
				minWait_SonimCare();
				customWait(4000);
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
				minWait_SonimCare();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
				minWait_SonimCare();		
				String cachedDataValue = Locators_ScoutFT.cachedMemory_0.getText();
				if(cachedDataValue.equalsIgnoreCase("0")){
					check=true;
					APP_LOGS.info("Cached memory is cleared succesfully.");
					SS2.assertTrue(check, " ");
				}else{
					APP_LOGS.info("Cached memory is not cleared succesfully.");
					SS2.fail();
				}
				back();
			} catch (NoSuchElementException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				SS2.fail();
			}		
			SS2.assertAll();
		}
		
		public void validate_bluetooth_scan() throws InterruptedException{
			/*
			 * Validates Bluetooth scan feature.
			 * Pass the confirmation message as parameter.
			 */
			SoftAssert SB1 = new SoftAssert();
			customWait(7000);	
			try {
				customWait(7000);
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
				minWait_SonimCare();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_LEFT);
				minWait_SonimCare();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
				minWait_SonimCare();		
				if(isElementExist(Locators_ScoutFT.selfDiagnosisTitle)){
					check=true;
					APP_LOGS.info("Navigated back to Test All Screen succesfully");
					SB1.assertTrue(check, " ");
				}else{
					APP_LOGS.info("Not navigated to test all screen");
					SB1.fail();
				}
				back();
			} catch (NoSuchElementException e) {
				e.printStackTrace();
				SB1.fail();
			}
			SB1.assertAll();
		}
		
		public void validate_wifi_links(String featureName ) throws InterruptedException{
			/*
			 * Validates all the hyper links and alerts of wifi feature page.
			 */
			SoftAssert SS3 = new SoftAssert();
			try {
				
				pressDownKey();
				minWait_SonimCare();
				if(isElementExist(Locators_ScoutFT.wifi_State_link))
				{
					check1 = true;
					APP_LOGS.info("check1: "+check1);
					APP_LOGS.info("wifi_State_link Element is present");
					aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
					minWait_SonimCare();			
					if(isElementExist(Locators_ScoutFT.alert))
					{
						check1_a =true;
						APP_LOGS.info("check1_a: "+check1_a);
						String getAlertText=Locators_ScoutFT.alert.getText();
						APP_LOGS.info("Alert heading is: "+getAlertText);
						pressBackKey();
						minWait_SonimCare();
					}else{
						APP_LOGS.info("check1_a: "+check1_a);
						APP_LOGS.info("Wifi_state_link_Alert is not present");
					}
				}else
				{
					APP_LOGS.info("check1: "+check1);
					APP_LOGS.info("wifi_State_link Element is not present");			
				}	
				pressDownKey();
				minWait_SonimCare();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
				//clickBtnWithText("Connected to");
				minWait_SonimCare();			
				if(isElementExist(Locators_ScoutFT.alert))
				{
					check2_a =true;
					APP_LOGS.info("check2_a: "+check2_a);
					String getAlertText=Locators_ScoutFT.alert.getText();
					APP_LOGS.info("Alert heading is: "+getAlertText);
					pressBackKey();
					minWait_SonimCare();
				}else{
					APP_LOGS.info("check2_a: "+check2_a);
					APP_LOGS.info("Connected to link Alert is not present");
				}
				pressDownKey();
				minWait_SonimCare();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
				//clickBtnWithText("Status");
				minWait_SonimCare();			
				if(isElementExist(Locators_ScoutFT.alert))
				{
					check3_a =true;
					APP_LOGS.info("check3_a: "+check3_a);
					String getAlertText=Locators_ScoutFT.alert.getText();
					APP_LOGS.info("Alert heading is: "+getAlertText);
					pressBackKey();
					minWait_SonimCare();
				}else{
					APP_LOGS.info("check3_a: "+check3_a);
					APP_LOGS.info("status_link Alert is not present");
				}
				pressDownKey();
				minWait_SonimCare();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
				//clickBtnWithText("RSSI");
				minWait_SonimCare();			

				if(isElementExist(Locators_ScoutFT.alert))
				{
					check4_a =true;
					APP_LOGS.info("check4_a: "+check4_a);
					String getAlertText=Locators_ScoutFT.alert.getText();
					APP_LOGS.info("Alert heading is: "+getAlertText);
					pressBackKey();
					minWait_SonimCare();
				}else{
					APP_LOGS.info("check4_a: "+check4_a);
					APP_LOGS.info("RSSI_link Alert is not present");
				}
				pressDownKey();
				minWait_SonimCare();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
				//clickBtnWithText("BSSID");
				minWait_SonimCare();			

				if(isElementExist(Locators_ScoutFT.alert))
				{
					check5_a =true;
					APP_LOGS.info("check5_a: "+check5_a);
					String getAlertText=Locators_ScoutFT.alert.getText();
					APP_LOGS.info("Alert heading is: "+getAlertText);
					pressBackKey();
					minWait_SonimCare();
				}else{
					APP_LOGS.info("check5_a: "+check5_a);
					APP_LOGS.info("bssID_link Alert is not present");
				}
				pressDownKey();
				minWait_SonimCare();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
				//clickBtnWithText("SSID");
				minWait_SonimCare();			
				if(isElementExist(Locators_ScoutFT.alert))
				{
					check6_a =true;
					APP_LOGS.info("check6_a: "+check6_a);
					String getAlertText=Locators_ScoutFT.alert.getText();
					APP_LOGS.info("Alert heading is: "+getAlertText);
					pressBackKey();
					minWait_SonimCare();
				}else{
					APP_LOGS.info("check6_a: "+check6_a);
					APP_LOGS.info("ssID_link Alert is not present");
				}
				pressDownKey();
				minWait_SonimCare();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
				//clickBtnWithText("IP Address");
				minWait_SonimCare();			
				if(isElementExist(Locators_ScoutFT.alert))
				{
					check7_a =true;
					APP_LOGS.info("check7_a: "+check7_a);
					String getAlertText=Locators_ScoutFT.alert.getText();
					APP_LOGS.info("Alert heading is: "+getAlertText);
					pressBackKey();
					minWait_SonimCare();
				}else{
					APP_LOGS.info("check7_a: "+check7_a);
					APP_LOGS.info("ip_address_link Alert is not present");
				}		
				pressDownKey();		
				minWait_SonimCare();				
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
				//clickBtnWithText("Link Speed");
				minWait_SonimCare();			
				if(isElementExist(Locators_ScoutFT.alert))
				{
					check9_a =true;
					APP_LOGS.info("check9_a: "+check9_a);
					String getAlertText=Locators_ScoutFT.alert.getText();
					APP_LOGS.info("Alert heading is: "+getAlertText);
					pressBackKey();
					minWait_SonimCare();
				}else{
					APP_LOGS.info("check9_a: "+check9_a);
					APP_LOGS.info("link_speed_link Alert is not present");
				}
				if((check1_a)&&(check2_a)&&(check3_a)&&(check4_a)&&(check5_a)&&(check6_a)&&(check7_a)&&(check9_a)){
					check=true;
					APP_LOGS.info("All hyperlinks and alerts of wifi feature screen is displayed");			
					pressDownKey();
					minWait_SonimCare();
					SS3.assertTrue(check, " ");
				}else{
					APP_LOGS.info("Test case failed");			
					pressDownKey();
					minWait();
					SS3.fail();
				}
				minWait_SonimCare();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
				//clickBtn(Locators_ScoutFT.scanForMoreNetworksBtn);
				customWait(6000);
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
				minWait_SonimCare();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_RIGHT);
				minWait_SonimCare();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
				minWait_SonimCare();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
				minWait();
				if(isElementExist(Locators_ScoutFT.alert))
				{
					clickBtnWithText("OK");
				}
				back();
			
			} catch (NoSuchElementException e) {
				e.printStackTrace();
				SS3.fail();
			}		
			
			minWait();
			
			
			SS3.assertAll();
		}
		
		public void validate_battery_links(String featureName) throws InterruptedException{
			/*
			 * Validates hyperlinks and alerts of battery feature screen.
			 */
			SoftAssert SB3 = new SoftAssert();
			try {
				pressDownKey();
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
				customWait(4000);			
				if(isElementExist(Locators_ScoutFT.alert))
				{
					check1_a =true;
					APP_LOGS.info("check1_a: "+check1_a);
					String getAlertText=Locators_ScoutFT.alert.getText();
					APP_LOGS.info("Alert heading is: "+getAlertText);
					pressBackKey();
					minWait_SonimCare();
				}else{
					APP_LOGS.info("check1_a: "+check1_a);
					APP_LOGS.info("battery_level_link is not present");
				}
				pressDownKey();
				minWait_SonimCare();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
				minWait_SonimCare();			
				if(isElementExist(Locators_ScoutFT.alert))
				{
					check2_a =true;
					APP_LOGS.info("check2_a: "+check2_a);
					String getAlertText=Locators_ScoutFT.alert.getText();
					APP_LOGS.info("Alert heading is: "+getAlertText);
					pressBackKey();
					minWait_SonimCare();
				}else{
					APP_LOGS.info("check2_a: "+check2_a);
					APP_LOGS.info("battery_health_link Alert is not present");
				}
				pressDownKey();
				minWait_SonimCare();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
				minWait_SonimCare();			
				if(isElementExist(Locators_ScoutFT.alert))
				{
					check3_a =true;
					APP_LOGS.info("check3_a: "+check3_a);
					String getAlertText=Locators_ScoutFT.alert.getText();
					APP_LOGS.info("Alert heading is: "+getAlertText);
					pressBackKey();
					minWait_SonimCare();
				}else{
					APP_LOGS.info("check3_a: "+check3_a);
					APP_LOGS.info("battery_status_link Alert is not present");
				}
				pressDownKey();
				minWait_SonimCare();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
				minWait_SonimCare();			
				if(isElementExist(Locators_ScoutFT.alert))
				{
					check5_a =true;
					APP_LOGS.info("check5_a: "+check5_a);
					String getAlertText=Locators_ScoutFT.alert.getText();
					APP_LOGS.info("Alert heading is: "+getAlertText);
					pressBackKey();
					minWait_SonimCare();
				}else{
					APP_LOGS.info("check5_a: "+check5_a);
					APP_LOGS.info("battery_temperature_link Alert is not present");
				}
				pressDownKey();
				minWait_SonimCare();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
				minWait_SonimCare();			
				if(isElementExist(Locators_ScoutFT.alert))
				{
					check6_a =true;
					APP_LOGS.info("check6_a: "+check6_a);
					String getAlertText=Locators_ScoutFT.alert.getText();
					APP_LOGS.info("Alert heading is: "+getAlertText);
					pressBackKey();
					minWait_SonimCare();
				}else{
					APP_LOGS.info("check6_a: "+check6_a);
					APP_LOGS.info("battery_voltage_link Alert is not present");
				}
				pressDownKey();
				minWait_SonimCare();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
				minWait_SonimCare();	
				if(isElementExist(Locators_ScoutFT.alert))
				{
					check7_a =true;
					APP_LOGS.info("check7_a: "+check7_a);
					String getAlertText=Locators_ScoutFT.alert.getText();
					APP_LOGS.info("Alert heading is: "+getAlertText);
					pressBackKey();
					minWait_SonimCare();
				}else{
					APP_LOGS.info("check7_a: "+check7_a);
					APP_LOGS.info("battery_scale_link Alert is not present");
				}
				if((check1_a)&&(check2_a)&&(check3_a)&&(check5_a)&&(check6_a)&&(check7_a)){
					check=true;
					APP_LOGS.info("All hyperlinks and alerts of wifi feature screen is displayed");			
					pressDownKey();
					minWait();
					aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_RIGHT);
					minWait();
					aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
					SB3.assertTrue(check, " ");
				}else{
					APP_LOGS.info("Test case failed");			
					//selectNOButton(featureName);
					pressDownKey();
					minWait();
					aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_RIGHT);
					minWait();
					aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
					SB3.fail();
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				SB3.fail();
			}	
			SB3.assertAll();
			if(isElementExist(Locators_ScoutFT.alert))
			{
				clickBtnWithText("OK");
			}
			minWait();
			back();
		}
		
		public void selectNOButton(String featurename) throws InterruptedException{
			/*
			 * clicks on NO button to exit from the scan.
			 * pass feature name as parameter.
			 */
			minWait_SonimCare();
			if(featurename.equalsIgnoreCase("wi-fi")){
				
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
				minWait_SonimCare();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
				minWait_SonimCare();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
				minWait_SonimCare();
			}else if(featurename.equalsIgnoreCase("bluetooth")){
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
				minWait_SonimCare();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_RIGHT);
				minWait_SonimCare();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
			}else if(featurename.equalsIgnoreCase("back camera")){
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_RIGHT);
				minWait_SonimCare();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
			}else if(featurename.equalsIgnoreCase("flash light")){
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
				minWait_SonimCare();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
			}else if(featurename.equalsIgnoreCase("display")){
				for(int i=1;i<7;i++){
					aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
					minWait_SonimCare();
				}
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_RIGHT);
				minWait_SonimCare();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
			}else if(featurename.equalsIgnoreCase("battery")){
				for(int i=1;i<8;i++){
					aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
					minWait_SonimCare();
				}
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_RIGHT);
				minWait_SonimCare();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
				minWait();
				if(isElementExist(Locators_ScoutFT.alert)) {
					aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
					minWait();
					aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
					minWait();
					aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
				}	
			}
		}
		
		public void validate_No_btn_feature(String featureName) throws InterruptedException{
			SoftAssert Sv = new SoftAssert();
			try {
				customWait(5000);
				selectNOButton(featureName);

				if(isElementExist(Locators_ScoutFT.alert)){
					check=true;
					APP_LOGS.info("Confirmation alert displayed succesfully");
					Sv.assertTrue(check, " ");
				}else{
					APP_LOGS.info("Confirmation alert displayed unsucessful");
					Sv.fail();

				}
			} catch (NoSuchElementException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				Sv.fail();
			}
			Sv.assertAll();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			minWait();
			back();
		}
		
		public void click_On_coninueBtn_Bat_Scan() throws InterruptedException{
			/*
			 * Clicks on continue button displayed in each feature screen.
			 */
			try {
				minWait_SonimCare();
				clickBtn(Locators_ScoutFT.continueBtn_Bat_scan);
			} catch (NoSuchElementException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				Assert.fail();
			}			
		}
		
		public void validate_batteryDiagnosis() throws InterruptedException{
			/*
			 * Validates battery diagnosis sub application screen.
			 * Validates against all static headings of the screen.
			 */
			SoftAssert Sv = new SoftAssert();
			try {
				customWait(4000);
				String getBatInfo = Locators_ScoutFT.battery_Info.getText();
				if(isElementExist(Locators_ScoutFT.battery_Info)){
					check1 = true;
					APP_LOGS.info("Element found: "+getBatInfo);
				}else{
					APP_LOGS.info("Bat info Element not found");
				}
				String getBatHealth = Locators_ScoutFT.battery_health.getText();
				if(isElementExist(Locators_ScoutFT.battery_health)){
					check2 = true;
					APP_LOGS.info("Element found: "+getBatHealth);
				}else{
					APP_LOGS.info("Bat health element is not found");
				}
				String getBatVoltage= Locators_ScoutFT.battery_voltage.getText();
				if(isElementExist(Locators_ScoutFT.battery_voltage)){
					check3 = true;
					APP_LOGS.info("Element found: "+getBatVoltage);
				}else{
					APP_LOGS.info("Bat voltage element is not found");
				}
				String getBatTemp = Locators_ScoutFT.battery_temperature.getText();
				if(isElementExist(Locators_ScoutFT.battery_temperature)){
					check4= true;
					APP_LOGS.info("Element found: "+getBatTemp);
				}else{
					APP_LOGS.info("Bat temperature element is not found");
				}
				if(isElementExist(Locators_ScoutFT.battery_history_Layout)){
					check5 = true;
					APP_LOGS.info("Battery history layout element is found");
				}else{
					APP_LOGS.info("Battery history layout element is not found");
				}
				String getBatHistory = Locators_ScoutFT.battery_service_History.getText();
				if(isElementExist(Locators_ScoutFT.battery_service_History)){
					check6 = true;
					APP_LOGS.info("Battery service history element is found: "+ getBatHistory);
				}else{
					APP_LOGS.info("Battery service hostory element is not found");
				}
				String getBatReport = Locators_ScoutFT.battery_view_detailedReport.getText();
				if(isElementExist(Locators_ScoutFT.battery_view_detailedReport)){
					check7=true;
					APP_LOGS.info("Battery detailed report element is found: "+getBatReport);
				}else{
					APP_LOGS.info("Battery detailed report element is not found");
				}
				String getBatDiagnosisTitle = Locators_ScoutFT.battery_diagnosis_title.getText();
				if(isElementExist(Locators_ScoutFT.battery_diagnosis_title)){
					check8=true;
					APP_LOGS.info("Battery detailed report element is found: "+getBatDiagnosisTitle);
				}else{
					APP_LOGS.info("Battery detailed report element is not found");
				}

				if((check1)&&(check2)&&(check3)&&(check4)&&(check5)&&(check6)&&(check7)&&(check8)){
					check= true;
					APP_LOGS.info("All static elements are located succesfully.");
					APP_LOGS.info("Test case is passed");
					Sv.assertTrue(check, " ");
				}else{
					APP_LOGS.info("Test case is failed");
					Sv.fail();
				}
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
		
		public void validate_battery_service_history() throws InterruptedException{
			/*
			 * validates battery service history.
			 * Records static and dynamic values.
			 */
			SoftAssert Sv = new SoftAssert();
			try {
				customWait(4000);
				clickBtn(Locators_ScoutFT.battery_service_History);
				minWait_SonimCare();		
				String getBat_Year_month_Info = Locators_ScoutFT.battery_Month_Year_view.getText();
				if(isElementExist(Locators_ScoutFT.battery_Month_Year_view)){
					check1 = true;
					APP_LOGS.info("Element found: "+getBat_Year_month_Info);
				}else{
					APP_LOGS.info("Bat year month info Element not found");
				}
				String get_total_no_charges = Locators_ScoutFT.battery_Total_No_Charges.getText();
				if(isElementExist(Locators_ScoutFT.battery_Total_No_Charges)){
					check2 = true;
					APP_LOGS.info("Element found: "+get_total_no_charges);
				}else{
					APP_LOGS.info("Bat total number of charges element is not found");
				}
				String get_total_no_charges_value= Locators_ScoutFT.battery_Total_No_Charges_value.getText();
				if(isElementExist(Locators_ScoutFT.battery_Total_No_Charges_value)){
					check3 = true;
					APP_LOGS.info("Element found: "+get_total_no_charges_value);			
				}else{
					APP_LOGS.info("Bat total no of charges value element is not found");
				}
				String get_max_temp_recorded = Locators_ScoutFT.battery_Max_temp_recorded.getText();
				if(isElementExist(Locators_ScoutFT.battery_Max_temp_recorded)){
					check4= true;
					APP_LOGS.info("Element found: "+get_max_temp_recorded);
				}else{
					APP_LOGS.info("Max battery temperature element is not found");
				}
				String get_max_temp_recorded_value = Locators_ScoutFT.battery_Max_temp_recorded_value.getText();
				if(isElementExist(Locators_ScoutFT.battery_Max_temp_recorded_value)){
					check5 = true;
					APP_LOGS.info("element is found"+get_max_temp_recorded_value);
				}else{
					APP_LOGS.info("bat_max_temp element is not found");
				}
				String getTotalChargeDuration = Locators_ScoutFT.battery_total_charge_duration.getText();
				if(isElementExist(Locators_ScoutFT.battery_total_charge_duration)){
					check6 = true;
					APP_LOGS.info("Element is found: "+ getTotalChargeDuration);
				}else{
					APP_LOGS.info("TotalCharge Duration element is not found");
				}
				String getTotalChargeDuration_Value = Locators_ScoutFT.battery_total_charge_duration_value.getText();
				if(isElementExist(Locators_ScoutFT.battery_total_charge_duration_value)){
					check7=true;
					APP_LOGS.info("Element is found: "+getTotalChargeDuration_Value);
				}else{
					APP_LOGS.info("TotalCharge duration value element is not found");
				}
				if((check1)&&(check2)&&(check3)&&(check4)&&(check5)&&(check6)&&(check7)){
					check=true;
					APP_LOGS.info("All dynamic parameters are displayed");
					APP_LOGS.info("Test case is passed");
					Sv.assertTrue(check, "  ");
				}else{
					APP_LOGS.info("Test case failed");
					Sv.fail();
				}
			} catch (NoSuchElementException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				Sv.fail();
			}
			Sv.assertAll();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
		}
		
		public void validate_hyperTexts_alert_selfDiagnosisPage() throws InterruptedException{
			/*
			 * Validates hyperlinks and alerts of self Diagnosis screen.
			 */
	         SoftAssert Sv1 = new SoftAssert();
			try {
				pressDownKey();
				minWait_SonimCare();
				if(isElementExist(Locators_ScoutFT.model_link))
				{
					check1 = true;
					APP_LOGS.info("check1: "+check1);
					APP_LOGS.info("model_link Element is present");
					pressCenterKey();
					minWait_SonimCare();			
					if(isElementExist(Locators_ScoutFT.alert))
					{
						check1_a =true;
						APP_LOGS.info("check1_a: "+check1_a);
						String getAlertText=Locators_ScoutFT.alert.getText();
						APP_LOGS.info("Alert heading is: "+getAlertText);
						pressBackKey();
						minWait_SonimCare();
					}else{
						APP_LOGS.info("check1_a: "+check1_a);
						APP_LOGS.info("model_link is not present");
					}
				}else
				{
					APP_LOGS.info("check1: "+check1);
					APP_LOGS.info("model_link Element is not present");			
				}	
				pressDownKey();
				customWait(2000);
				pressCenterKey();
				minWait_SonimCare();

				if(isElementExist(Locators_ScoutFT.alert))
				{
					check2 = true;
					check2_a =true;
					APP_LOGS.info("check2_a: "+check2_a);
					String getAlertText=Locators_ScoutFT.alert.getText();
					APP_LOGS.info("Alert heading is: "+getAlertText);
					pressBackKey();
					minWait_SonimCare();
				}else{
					APP_LOGS.info("check2_a: "+check2_a);
					APP_LOGS.info("imei_link to link Alert is not present");
				}
				pressDownKey();
				minWait_SonimCare();
				pressCenterKey();
				minWait_SonimCare();
				if(isElementExist(Locators_ScoutFT.alert))
				{
					check3 = true;
					check3_a =true;
					APP_LOGS.info("check3_a: "+check3_a);
					String getAlertText=Locators_ScoutFT.alert.getText();
					APP_LOGS.info("Alert heading is: "+getAlertText);
					pressBackKey();
					minWait_SonimCare();
				}else{
					APP_LOGS.info("check3_a: "+check3_a);
					APP_LOGS.info("androidVersion_link Alert is not present");
				}
				pressDownKey();
				minWait_SonimCare();
				pressCenterKey();
				minWait_SonimCare();
				if(isElementExist(Locators_ScoutFT.alert))
				{
					check4 = true;
					check4_a =true;
					APP_LOGS.info("check4_a: "+check4_a);
					String getAlertText=Locators_ScoutFT.alert.getText();
					APP_LOGS.info("Alert heading is: "+getAlertText);
					pressBackKey();
					minWait_SonimCare();
				}else{
					APP_LOGS.info("check4_a: "+check4_a);
					APP_LOGS.info("build_link Alert is not present");
				}
				pressDownKey();
				minWait_SonimCare();
				pressCenterKey();
				minWait_SonimCare();
				if(isElementExist(Locators_ScoutFT.alert))
				{
					check5 = true;
					check5_a =true;
					APP_LOGS.info("check5_a: "+check5_a);
					String getAlertText=Locators_ScoutFT.alert.getText();
					APP_LOGS.info("Alert heading is: "+getAlertText);
					pressBackKey();
					minWait_SonimCare();
				}else{
					APP_LOGS.info("check5_a: "+check5_a);
					APP_LOGS.info("phoneNumber_link Alert is not present");
				}
				pressDownKey();
				minWait_SonimCare();
				pressCenterKey();
				minWait_SonimCare();
				if(isElementExist(Locators_ScoutFT.alert))
				{
					check6 = true;
					check6_a =true;
					APP_LOGS.info("check6_a: "+check6_a);
					String getAlertText=Locators_ScoutFT.alert.getText();
					APP_LOGS.info("Alert heading is: "+getAlertText);
					pressBackKey();
					minWait_SonimCare();
				}else{
					APP_LOGS.info("check6_a: "+check6_a);
					APP_LOGS.info("network_link Alert is not present");
				}
				pressDownKey();
				minWait_SonimCare();
				pressCenterKey();
				minWait_SonimCare();
				if(isElementExist(Locators_ScoutFT.alert))
				{
					check7 = true;
					check7_a =true;
					APP_LOGS.info("check7_a: "+check7_a);
					String getAlertText=Locators_ScoutFT.alert.getText();
					APP_LOGS.info("Alert heading is: "+getAlertText);
					pressBackKey();
					minWait_SonimCare();
				}else{
					APP_LOGS.info("check7_a: "+check7_a);
					APP_LOGS.info("network_type_link Alert is not present");
				}
				pressDownKey();
				minWait_SonimCare();
				pressCenterKey();
				minWait_SonimCare();
				if(isElementExist(Locators_ScoutFT.alert))
				{
					check8 = true;
					check8_a =true;
					APP_LOGS.info("check8_a: "+check8_a);
					String getAlertText=Locators_ScoutFT.alert.getText();
					APP_LOGS.info("Alert heading is: "+getAlertText);
					pressBackKey();
					minWait_SonimCare();
				}else{
					APP_LOGS.info("check8_a: "+check8_a);
					APP_LOGS.info("signal_strength_link Alert is not present");
				}
				pressDownKey();
				minWait_SonimCare();
				pressCenterKey();
				minWait_SonimCare();
				if(isElementExist(Locators_ScoutFT.alert))
				{
					check9 = true;
					check9_a =true;
					APP_LOGS.info("check9_a: "+check9_a);
					String getAlertText=Locators_ScoutFT.alert.getText();
					APP_LOGS.info("Alert heading is: "+getAlertText);
					pressBackKey();
					minWait_SonimCare();
				}else{
					APP_LOGS.info("check9_a: "+check9_a);
					APP_LOGS.info("on_roaming_link Alert is not present");
				}
				pressDownKey();
				minWait_SonimCare();
				pressCenterKey();
				minWait_SonimCare();
				if(isElementExist(Locators_ScoutFT.alert))
				{
					check10 = true;
					check10_a =true;
					APP_LOGS.info("check10_a: "+check10_a);
					String getAlertText=Locators_ScoutFT.alert.getText();
					APP_LOGS.info("Alert heading is: "+getAlertText);
					pressBackKey();
					minWait_SonimCare();
				}else{
					APP_LOGS.info("check10_a: "+check10_a);
					APP_LOGS.info("data_link Alert is not present");
				}
				if((check1)&&(check1_a)&&(check2)&&(check2_a)&&(check3)&&(check3_a)&&(check4)&&(check4_a)&&(check5)&&(check5_a)&&(check6)&&(check6_a)&&(check7)&&(check7_a)
						&&(check8)&(check8_a)&&(check9)&(check9_a)&&(check10)&&(check10_a)){
					check=true;
					APP_LOGS.info("All hyperlinks and alerts of self diagnosis screen is displayed successfully.");			
					pressBackKey();
					minWait_SonimCare();
					Sv1.assertTrue(check, " ");
				}else{
					APP_LOGS.info("Test case failed");			
					pressBackKey();
					minWait_SonimCare();
					Sv1.fail();
				}
			} catch (NoSuchElementException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				Sv1.fail();
			}		
			Sv1.assertAll();
			pressBackKey();
		}
		
		public void pressCenterKey(){
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
		}
		
		public void special_Case_SelectSprint() throws InterruptedException, IOException {
			try {
				minWait();
				if(sf_app_Check_Activation_Off_On().equals("OFF")) {
					turn_Off_On_SG();
					minWait();
					clickBtn(Locators_ScoutFT.SG_Applications_String);
					minWait();
					enter_Pin();
					customWait(2000);
					for(int i=1; i<=6; i++) {
						minWait();
						aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
						minWait();
					}
					minWait();
					aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
					minWait();
					aDriver.pressKeyCode(AndroidKeyCode.MENU);
					customWait(2000);
					clickBtn(Locators_ScoutFT.SG_Save_Btn);
					minWait();
				}
				else {
					APP_LOGS.info("Else Part : Activation is already ON and checking all the Apps one by one");
					minWait();
					clickBtn(Locators_ScoutFT.SG_Applications_String);
					minWait();
					enter_Pin();
					minWait();
					for(int i=1; i<=6; i++) {
						minWait();
						aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
						minWait();
					}
					aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
					minWait();
					aDriver.pressKeyCode(AndroidKeyCode.MENU);
					customWait(2000);
					clickBtn(Locators_ScoutFT.SG_Save_Btn);
					minWait();
				}
			} catch (NoSuchElementException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				Assert.fail();
			}
		}
}
