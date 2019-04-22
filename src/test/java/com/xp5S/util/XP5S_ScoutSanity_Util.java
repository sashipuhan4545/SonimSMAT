package com.xp5S.util;

import java.io.IOException;


import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import com.relevantcodes.extentreports.LogStatus;


import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidKeyCode;

public class XP5S_ScoutSanity_Util extends BaseUtil{


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

	public void launch_application(String app) throws InterruptedException{
		/*
		 * Launches sonim scout and navigates to supports tab..
		 */
		try {
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_HOME);
			minWait();
			navigateUsingText(app);
			APP_LOGS.info("Application launched succesfully");
		} catch (NoSuchElementException e) {
			APP_LOGS.info("Error: Application not found.");
			e.printStackTrace();
			Assert.fail();
		}		
	}

	public void validateScoutAppLaunch() throws InterruptedException {
		/*
		 * Validate Scout app launch  with tabs present
		 */
		boolean check1 = false;
		boolean check2 = false;
		boolean check3 = false;
		boolean check = false;
		SoftAssert SA = new SoftAssert();
		
		try {
			if(isElementExist(Locators_ScoutSanity.utilitiesTab)) {
				check1 =true;
				APP_LOGS.info("Utility Tab is present");
			}
			if(isElementExist(Locators_ScoutSanity.setUpTab)) {
				check2 =true;
				APP_LOGS.info("SetUp Tab is present");
			}
			if(isElementExist(Locators_ScoutSanity.supportTab)) {
				check3 =true;
				APP_LOGS.info("Support Tab is present");
			}

			if((check1)&&(check2)&&(check3)) {
				check= true;
				APP_LOGS.info("Scout App Launched sucessfully and all Tabs verified");
				SA.assertTrue(check, " ");
			}
			else{
				APP_LOGS.info(check1+"\n"+": "+check2+"\n"+": "+check3+ "\n");
				SA.fail();
				
			}
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			SA.fail();
		}	
		SA.assertAll();
		minWait();
		aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
		minWait();
	}


	public void launchScoutApp() throws InterruptedException, IOException {
		launchAppThroughABD("cmd /c start /min cmd.exe /K \"adb shell am start -n com.sonim.scout/com.sonim.scout.activities.MainActivity");
		customWait(5000);
	}
	public void validateAllTabsandList() throws InterruptedException, IOException{
    /*
    * Valdate all tabs present, All apps present
    */
		
		boolean check1 = false;
		boolean check2 = false;
		boolean check3 = false;
		boolean check4 = false;
		boolean check5 = false;
		boolean check6 = false;
		boolean check7 = false;
		boolean check8 = false;
		boolean check9 = false;
		boolean check10 = false;
		boolean check = false;
		
		SoftAssert SA1 = new SoftAssert();
		try {
			minWait();
			clickBtn(Locators_ScoutSanity.setUpTab);
			APP_LOGS.info("SetUp Tab is clicked");
			
			
			if(isElementExist(Locators_ScoutSanity.SonimSetUpWizard)) {
				check1 =true;
				APP_LOGS.info("SonimSetUpWazard is present");        
			}
			if(isElementExist(Locators_ScoutSanity.Safeguard)) {
				check2 =true;
				APP_LOGS.info("Safeguard is present");	         
			}

			clickBtn(Locators_ScoutSanity.utilitiesTab);
			if(isElementExist(Locators_ScoutSanity.ContactTransferTitle)) {
				check3 =true;
				APP_LOGS.info("ContactTransferTitle is present");        
			}

			if(isElementExist(Locators_ScoutSanity.BLEconnect)) {
				check4 =true;
				APP_LOGS.info("BLEconnect is present");	         
			}

			clickBtn(Locators_ScoutSanity.supportTab);
			if(isElementExist(Locators_ScoutSanity.Chat)) {
				check5 =true;
				APP_LOGS.info("Chat is present");        
			}

			if(isElementExist(Locators_ScoutSanity.Remote)) {
				check6 =true;
				APP_LOGS.info("RemoteSupport is present");	         
			}

			if(isElementExist(Locators_ScoutSanity.SonimCare)) {
				check7 =true;
				APP_LOGS.info("SonimCare is present");	         
			}

			if(isElementExist(Locators_ScoutSanity.WarrantyReg)) {
				check8 =true;
				APP_LOGS.info("WarrantyReg is present");	         
			}
			aDriver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().className(\"android.widget.ScrollView\")).scrollIntoView(new UiSelector().text(\"Contact Sonim Support\"))");  
			customWait(1000);
			if(isElementExist(Locators_ScoutSanity.DeviceInfo_App)) {
				check9 =true;
				APP_LOGS.info("DeviceInfo is present");	         
			}

			if(isElementExist(Locators_ScoutSanity.ContactSonimSupport)) {
				check10 =true;
				APP_LOGS.info("ContactSonimSupport is present");	         
			}

			if((check1)&&(check2)&&(check3)&&(check4)&&(check5)&&(check6)&&(check7)&&(check8)&&(check9)&&(check10)) {
				check= true;
				APP_LOGS.info("Scout App Launched and Tabs verifiedwith All Lists Present");
				SA1.assertTrue(check, " ");
			}
			else{
				APP_LOGS.info(check1+"\n"+": "+check2+"\n");
				APP_LOGS.info(check3+"\n"+": "+check4+"\n"+": "+check5+ "\n");
				APP_LOGS.info(check6+"\n"+": "+check7+"\n"+": "+check8+ "\n");
				APP_LOGS.info(check9+"\n"+": "+check10+"\n");
				SA1.fail();
			}
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			SA1.fail();
		}
		SA1.assertAll();
	}

	public void navigateprogrammablekey() throws InterruptedException, IOException {
		
		try {
			aDriver.pressKeyCode(AndroidKeyCode.HOME);
			//launchAppThroughABD("cmd /c start /min cmd.exe /K \"adb shell am start -n com.android.settings/com.android.settings.Settings");
			navigateUsingText("Settings");
			customWait(3000);
			//aDriver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().resourceId(\"com.android.settings:id/main_content\")).scrollIntoView(new UiSelector().text(\"Programmable Key\"))");
			//("Programmable Key");
			customWait(1000);
		} catch (NoSuchElementException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Assert.fail();
		}
	}

	public void press_PTT_Key() throws InterruptedException, IOException{
		/*
		 * Long press event of PTT key is fired.
		 */
		Runtime.getRuntime().exec("cmd /c start /min cmd.exe /K \"adb shell input keyevent --longpress KEYCODE_PTT");
		customWait(5000);
	}


	public void validate_ProgrammableKeySummaryText() throws InterruptedException{
		/*
		 * Retrieves programmable key summary text.
		 */
		minWait();
		SoftAssert Sa = new SoftAssert();
		try {
			if(isElementExist(Locators_ScoutSanity.summaryText)){
				APP_LOGS.info("Programmable key summary is displayed successfully");	
				String getSummaryText = Locators_ScoutSanity.summaryText.getText();
				APP_LOGS.info("App assigned to programmable key is: "+getSummaryText);
				minWait();
				check=true;
				Sa.assertTrue(check, " ");	
			}else{
				APP_LOGS.info("Programmable key summary is not displayed");
				Sa.fail();
			}								

		} catch (NoSuchElementException e) {			
			e.printStackTrace();
			Sa.fail();
		}
		Sa.assertAll();
	}

	public void scrollToNoApplication() throws InterruptedException{
		/*
		 * scrolls to top of the PTT key app list
		 */
		try {
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
			customWait(2500);
			APP_LOGS.info("Navigated to select PTT KEY app screen sucessfully");
			for (int i = 1; i <= 34; i++) {
				if (isElementExist(Locators_ScoutSanity.no_Application_app)) {
					String getAppName = Locators_ScoutSanity.no_Application_app.getText();
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
			// TODO Auto-generated catch block
			e.printStackTrace();
			Assert.fail();
		}
	}

	public void assign_app_to_ProgrammableKey(String app) throws InterruptedException{
		/*
		 * Assign app to programmable key.
		 * Send webelement to be selected as parameter
		 */
		try {         	
			//for (int iter = 1; iter <= 34; iter++) {
			//	if (isElementExist(app)) {
			//		String getAppName = app.getText();
					
					for (int i =0; i<=34; i++) {
						if (!isElementExist(Locators_ScoutSanity.no_Application_app)) {
						aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_UP);
						}
						else
							break;
					}
					
					for (int i=0; i<=34; i++) {
						if(!isElementExistWithText(app)) {
							aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
							System.out.println("ELement not found--------=----");
							minWait();
						}
						else {
							aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
							break;
								}
						}
					
					APP_LOGS.info(app+" : Application is assigned to programmableKey");	
					if(isElementExist(Locators_ScoutSanity.Ok_Btn)) {
						APP_LOGS.info("Validated assigning to other Apps sucessfully ");
						aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_RIGHT);
						minWait();
						aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
						minWait();					
						minWait();
					}				 				
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			Assert.fail();
		}		
	}
	
	public void validateAppProgrammableKey(String app) {
		SoftAssert SA = new SoftAssert();
		boolean check = false;
		try {
			String applicationSet = Locators_ScoutSanity.summaryText.getText();
			//scrollToNoApplication();
			System.out.println(applicationSet);
			
			if (applicationSet.equalsIgnoreCase(app)) {
					check = true;
					APP_LOGS.info("App assigned to programmable key is: "+applicationSet);
			}
			else {
				check = false;
				APP_LOGS.info("Incorrect app summary text is displayed: "+applicationSet);
			}
			SA.assertEquals(check, true, "");
			
			}
		catch (NoSuchElementException e) {			
			e.printStackTrace();
			SA.fail();
			}
		SA.assertAll();
	}
	


	public void validate_launchedApplication(String summaryText) throws InterruptedException, IOException {
		/*
		 * Validates application launched with respect to the application
		 * assigned to PTT KEY.
		 */
		
		boolean check1 = false;
		boolean check2 = false;
		
		SoftAssert SA12 = new SoftAssert();
		try {
			/*minWait();
			String getSummaryText = aDriver.
					findElement(By.xpath("//android.widget.TextView[@text='"+summaryText+"']")).getText();
			if (getSummaryText.equalsIgnoreCase(getSummaryText)) {
				APP_LOGS.info("App assigned to programmable key is: "+getSummaryText);
				check1 = true;
				minWait();
			} else {
				APP_LOGS.info("Incorrect app summary text is displayed: "+getSummaryText);
			}*/
				/*String applicationSet = Locators_ScoutSanity.summaryText.getText();
				//scrollToNoApplication();
				System.out.println(applicationSet);
				
				if (applicationSet.equalsIgnoreCase(summaryText)) {
						check1 = true;
						APP_LOGS.info("App assigned to programmable key is: "+applicationSet);
				}
				else
					check1 = false;
				APP_LOGS.info("Incorrect app summary text is displayed: "+applicationSet);*/

			

			if (isElementExistWithText(summaryText)) {
				check2 = true;
				APP_LOGS.info("Assigned application launched succesfully upon pressing programmable key.");
			} else {
				APP_LOGS.info("Assigned application is not launched");
			}
			
				APP_LOGS.info("Test case passed");
				SA12.assertTrue(check2, " ");

		} catch (NoSuchElementException e) {			
			e.printStackTrace();
			SA12.fail();
		}
		SA12.assertAll();
	}


	public void navigateToWarrantyRegApp() throws InterruptedException, IOException {

		try {
			clickBtn(Locators_ScoutSanity.SWR_Support_Tab);
			customWait(1000);
			clickBtn(Locators_ScoutSanity.SWR_String_Name);
			customWait(1000);

			if(isElementExist(Locators_ScoutSanity.SWR_Contacting_server_progress_dialog)) {
				APP_LOGS.info("LaunchWarrantyRegApp: Progress dialog displayed on FG, So Waiting for 5 seconds");
				customWait(6000);

				if(isElementExist(Locators_ScoutSanity.SWR_UnableTo_Fetch_RegistrationInforamtionFromServer)) {
					APP_LOGS.info("Unable to fetch registration information from server present on FG");
					clickBtn(Locators_ScoutSanity.SWR_OK_Btn);					  
				}				 
				else if(isElementExist(Locators_ScoutSanity.SWR_UnableTo_Fetch_RegistrationInforamtionFromServer)) {
					APP_LOGS.info("Unable to fetch registration information from server present on FG");
					clickBtn(Locators_ScoutSanity.SWR_OK_Btn);
					System.out.println("Execution has stopped as,Device is unable to fetch registration in information from server");
					test.log(LogStatus.SKIP, "Test case status is Skipped");	
					Assert.fail();
					//aDriver.quit();
				}
				else if(isElementExist(Locators_ScoutSanity.SWR_Please_contact_Sonim_At)) {
					customWait(1000);
					Runtime.getRuntime().exec("adb shell pm clear com.sonimtech.warrantyregapp");
					customWait(1000);
					clearApp();
					aDriver.startActivity("com.sonim.scout", "com.sonim.scout.activities.MainActivity");	 
				}
				else if(isElementExist(Locators_ScoutSanity.SWR_No_internet_Connection)) {
					clickBtn(Locators_ScoutSanity.SWR_OK_Btn);
					APP_LOGS.info("************No internet connection window display ***************");
					System.out.println("Execution has stopped as ,there is No internet connection available in the device!! ");
					test.log(LogStatus.SKIP, "Test case status is Skipped");	
					Assert.fail();
					//aDriver.quit();			  
				}
			}
			else {
				APP_LOGS.info("Device is already Registered.");
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Assert.fail();
		}
	}


	public void presence_Of_SonimWarrantyReg_In_ScoutApp() throws InterruptedException, IOException {

		APP_LOGS.info("**************************Starts******************************"); 

		SoftAssert SA1=new SoftAssert();
		navigateToWarrantyRegApp();
		customWait(1000);
		try {
			clickBtn(Locators_ScoutSanity.SWR_Support_Tab);

			APP_LOGS.info("Tapped on Support tab successfully");
			customWait(1000);	
			String fetch_sf_text=Locators_ScoutSanity.SWR_String_Name.getText();
			APP_LOGS.info("Fetching safeguard string text is" +fetch_sf_text);
			SA1.assertEquals(fetch_sf_text, "Warranty Registration");
			SA1.assertAll();			
		}
		catch(NoSuchElementException ex){
			ex.printStackTrace();
			APP_LOGS.info("Element is not present in the page");
		}
		APP_LOGS.info("***************************** Ends******************************"); 	
	}


	public void skip_AllFields_Press_SubmitBtn() throws InterruptedException, IOException {

		APP_LOGS.info("*******************Start*******************************");
		//			 clickBtn(Locators_Warranty_Reg.SWR_RstBtn);
		customWait(1000);
		SoftAssert SA3=new SoftAssert();	
		if(isDeviceRegistered().equals("Register your device")) {
			try {
				customWait(1000);
				clickBtn(Locators_ScoutSanity.SWR_Now);
				customWait(1000);
				//						 clickBtn(Locators_Warranty_Reg.SWR_Submit);
				customWait(2000);
				//Dont enter any value in the test field and press on "submit" button and check
				if(isElementExist(Locators_ScoutSanity.SWR_Submit)) {
					APP_LOGS.info("Verified presence of Submit button");
					check1=true;
					APP_LOGS.info("PASSED");
				}
				if(isElementExist(Locators_ScoutSanity.SWR_Reset)) {
					APP_LOGS.info("Verifiedpresence of Reset button");
					check2=true;
					APP_LOGS.info("PASSED");
				}	
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
				if(check1 && check2){
					check=true;
					APP_LOGS.info("Test case passed");
					softAssert_true(check, "Pass");
				}else{
					APP_LOGS.info("Test case failed");
					Assert.fail();
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
			test.log(LogStatus.SKIP, "Test case status is Skipped");
			Assert.fail();
			//aDriver.quit();		 
		}
	}

	public String isDeviceRegistered() throws InterruptedException, IOException {

		APP_LOGS.info("******************************start******************************"); 
		String status = null;
		navigateToWarrantyRegApp();
		APP_LOGS.info("******************************navigate******************************"); 
		try {
			if(isElementExist(Locators_ScoutSanity.SWR_String_Register_Your_Device)) {
				APP_LOGS.info("isDeviceRegistered:Need to Registered the device");
				status=Locators_ScoutSanity.SWR_String_Register_Your_Device.getText();	
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
		System.out.println(status);
		return status;	 
	}


	public void addInvoice() throws InterruptedException, WebDriverException, IOException {

		try {
			if(isDeviceRegistered().equals("Register your device")) {
				clickBtn(Locators_ScoutSanity.SWR_Now);
				aDriver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().className(\"android.widget.ScrollView\")).scrollIntoView(new UiSelector().text(\"Choose a File\"))").click();;
				customWait(1000);
				clickBtn(Locators_ScoutSanity.SWR_Take_a_Pic);
				customWait(2000);
				clickBtn(Locators_ScoutSanity.SWR_PhotoCapture);
				customWait(1000);
				clickBtn(Locators_ScoutSanity.SWR_OK_Btn);	
				customWait(2000);
			}
			else {
				System.out.println(isDeviceRegistered());
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {
			APP_LOGS.info("No Element found");
			e.printStackTrace();
			Assert.fail();
		}
	}


	public void validateUploadFile(String verifyText) throws InterruptedException {
		try {
			String invoiceText = aDriver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().className(\"android.widget.ScrollView\")).scrollIntoView(new UiSelector().resourceId(\"com.sonimtech.warrantyregapp:id/choose_file_textview\"))").getText();
			customWait(1000);
			System.out.println("invoice text ="+ invoiceText+ "verifyText="+verifyText);
			//if(isElementExist(Locators_ScoutSanity.SWR_invoice)) {
			if((invoiceText).equalsIgnoreCase(verifyText))	{
				check=true;
				APP_LOGS.info("Test case passed Validated Upload File");
				softAssert_true(check, "Pass");
			}else{
				APP_LOGS.info("Test case failed Upload File");
				Assert.fail();
			}
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
		} catch (org.openqa.selenium.NoSuchElementException e) {			
			e.printStackTrace();
			Assert.fail();
		}
	}

	public void enter_Feilds_Press_ResetBtn() throws InterruptedException, IOException {
/*
 * Enter Form fields SWR
 */
		try {
			if(isDeviceRegistered().equals("Register your device")) {						
				customWait(1000);
				clickBtn(Locators_ScoutSanity.SWR_Now);					
				customWait(1000);
				if(isElementExist(Locators_ScoutSanity.SWR_Reset)) {
					clickBtn(Locators_ScoutSanity.SWR_Reset);	
				}
				enterTextToInputField(Locators_ScoutSanity.SWR_Enter_First_Name, "sonim");
				customWait(1000);
				enterTextToInputField(Locators_ScoutSanity.SWR_Enter_Last_Name, "sonim");
				customWait(1000);
				enterTextToInputField(Locators_ScoutSanity.SWR_Enter_Company_Name, "Sonim Technology");
				customWait(1000);
				enterTextToInputField(Locators_ScoutSanity.SWR_Enter_Address_1, "J P Nagar,3rd phase");
				customWait(1000);
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
				enterTextToInputField(Locators_ScoutSanity.SWR_enter_City, "Bangalore");
				customWait(1000);
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
				enterTextToInputField(Locators_ScoutSanity.SWR_Enter_State_Name, "Karnataka");
				customWait(1000);
			}		 
			else {
				APP_LOGS.info("********************Device is Already Registered********************");
				System.out.println("*******Device is Already Registered,Need to Delete the device from server to run the Script*******");
				test.log(LogStatus.SKIP, "Test case status is Skipped");
				Assert.fail();
				//aDriver.quit();		 
			}
		} catch (NoSuchElementException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Assert.fail();
		}		 
	}

	public void validateResetBtn() throws InterruptedException {
		try {
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			minWait();
			clickBtn(Locators_ScoutSanity.SWR_Now);
			customWait(1000);	
			clickBtn(Locators_ScoutSanity.SWR_Reset);
			if(isElementExist(Locators_ScoutSanity.SWR_Enter_First_Name)) {
				check=true;
				APP_LOGS.info("Test case passed Validated Reset Button");
				softAssert_true(check, "Pass");
			}else{
				APP_LOGS.info("Test case failed Reset Button");
				Assert.fail();
			}
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
		} catch (org.openqa.selenium.NoSuchElementException e) {
			e.printStackTrace();
			Assert.fail();
		}

	}


	public void validateMandatoryfield() throws InterruptedException {
		try {
			clickBtn(Locators_ScoutSanity.SWR_Now);
			minWait();
			aDriver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().className(\"android.widget.ScrollView\")).scrollIntoView(new UiSelector().text(\"Enter Country\"))").click();;
			customWait(1000);
			enterTextToInputField(Locators_ScoutSanity.SWR_Country, "India");
			customWait(1000);
			aDriver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().className(\"android.widget.ScrollView\")).scrollIntoView(new UiSelector().text(\"Enter first name\"))").click();;
			customWait(1000);
			clickBtn(Locators_ScoutSanity.SWR_Submit);
			minWait();
			if(isElementExist(Locators_ScoutSanity.SWR_OK_Btn)) {
				clickBtn(Locators_ScoutSanity.SWR_OK_Btn);
				check=true;
				APP_LOGS.info("Test case passed Validated mandatory fields");
				softAssert_true(check, "Pass");
			}else{
				APP_LOGS.info("Test case failed mandatory fields");
				Assert.fail();
			}minWait();
			clickBtn(Locators_ScoutSanity.SWR_Reset);
			minWait();
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
		} catch (NoSuchElementException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Assert.fail();
		}	   
	}

	public void navigateApps(WebElement tab, String app) throws InterruptedException, IOException {
		
		try {
			minWait();
			clickBtn(tab);
			customWait(200);
			//System.out.println();
			//(app);
			
			//scrollWithUIAutomatorElement(Locators_ScoutSanity.device_Information);
			//scrollWithText("Device Information");
			
			
//			aDriver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"\"))");
			
//			String appName = app.getText();
//			System.out.println(appName);
			//System.out.println("Entering the scrollWithText");
			//boolean status = scrollWithText("Remote Support");
			//System.out.println(status);*/
			
			/*for(int i=1; i<=6; i++) {
					aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_UP);
			}
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);*/
			
			//aDriver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().className(\"android.widget.ScrollView\")).scrollIntoView(new UiSelector().text(\"Remote Support\"))").click();;
			
			/*for(int i=1; i<=6; i++) {
				if(isElementExist(app)) {
					
					//System.out.println("Inside for loop");
				clickBtn(app);
					//aDriver.findElementByAndroidUIAutomator("new UiSelector().className(\"android.widget.TextView\").text(\"Remote Support\")").click();
				break;
				}
				else {
					aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
					continue;
				}*/
			
			
		} catch (NoSuchElementException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Assert.fail();
		}		 
	}

	public void validateDeviceInfoAcess() throws InterruptedException {
		/*
		 * Validate Device information from Scout App
		 */
		SoftAssert SE = new SoftAssert();
		check6=true;
		try {
			
			aDriver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().className(\"android.widget.ScrollView\")).scrollIntoView(new UiSelector().text(\"Device Info\"))");
			customWait(1000);
			
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
			minWait();
			if(isElementExist(Locators_ScoutSanity.DeviceInfo_App)) {
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
				customWait(1000);
				check1=true;
				APP_LOGS.info("ch1 Presence of Device Info "+ check1);	
			}

			if(isElementExist(Locators_ScoutSanity.DeviceInfo)) {
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
				customWait(1000);
				check2=true;
				APP_LOGS.info("ch2 Presence of Device Info"+check2);			
			}
			if(isElementExist(Locators_ScoutSanity.DeviceInfo)) {
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
				customWait(1000);
				check3=true;
				APP_LOGS.info("ch3 Presence of Device Info"+check3);			
			}

			if(isElementExist(Locators_ScoutSanity.DeviceInfo_IMEI1)) {
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
				customWait(1000);
				check4=true;
				APP_LOGS.info("ch4 Presence of Device Info"+ check4);			
			}

			if(isElementExist(Locators_ScoutSanity.DeviceInfo_Model)) {
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
				customWait(1000);
				check6=true;
				APP_LOGS.info("ch6 Presence of Device Info"+ check6);			
			}

			if(isElementExist(Locators_ScoutSanity.DeviceInfo_Carrier)) {
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
				customWait(1000);
				check7=true;
				APP_LOGS.info("ch7 Presence of Device Info"+ check7);			
			}
			if(isElementExist(Locators_ScoutSanity.DeviceInfo_Baseband)) {
				String XP5deviceModelNumber=BaseUtil.fetchDeviceProperty("adb shell getprop gsm.version.baseband");
				APP_LOGS.info("Device Baseband is: "+XP5deviceModelNumber);
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
				customWait(1000);
				check8=true;
				APP_LOGS.info(" ch8 Presence of Device Info"+ check8);			
			}
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);

			if(isElementExist(Locators_ScoutSanity.DeviceInfo_Build)) {
				customWait(1000);
				check9=true;
				APP_LOGS.info("ch9 Presence of Device Info"+ check9);			
			}
			if((check1)&&(check2)&&(check3)||(check4)&&(check6)&&(check7)&&(check8)&&(check9)) {
				check= true;
				APP_LOGS.info("Scout App Launched and Device information is Present");
				SE.assertTrue(check, " ");
			}
			else{
				APP_LOGS.info(check1+"\n"+": "+check2+"\n"+": "+check3+ "\n");
				APP_LOGS.info(check4+"\n"+": "+check6+ "\n"+check7+ "\n");		
				APP_LOGS.info(check8+"\n"+": "+check9+"\n");
				SE.fail();
			}aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
		} catch (org.openqa.selenium.NoSuchElementException e) {
			e.printStackTrace();
			SE.fail();
		}	
		SE.assertAll();
	}


	public void validateAcessSWR() throws InterruptedException, IOException {
		/*
		 * Validating Acess of SWR from Device information
		 */
		SoftAssert S11 = new SoftAssert();
		try {
			aDriver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().className(\"android.widget.ScrollView\")).scrollIntoView(new UiSelector().text(\"Register\"))");
			customWait(1000);			
			if(isElementExist(Locators_ScoutSanity.DevInf_Register)) {
				check1=true;
				clickBtn(Locators_ScoutSanity.DevInf_Register);
				APP_LOGS.info(" Device Registere Information is displayed");
				customWait(1000);
			}

			clickBtn(Locators_ScoutSanity.SWR_Now);					
			customWait(1000);

			if(isElementExist(Locators_ScoutSanity.SWR_Reset)) {
				check2=true;
				APP_LOGS.info("Test case passed Validated ");			
			}	

			if(isElementExist(Locators_ScoutSanity.SWR_Submit)) {
				check3=true;
				APP_LOGS.info("Test case passed Validated ");			
			}		 

			enterTextToInputField(Locators_ScoutSanity.SWR_Enter_First_Name, "sonim");
			customWait(1000);
			enterTextToInputField(Locators_ScoutSanity.SWR_Enter_Last_Name, "sonim");
			customWait(1000);
			enterTextToInputField(Locators_ScoutSanity.SWR_Enter_Company_Name, "Sonim Technology");
			customWait(1000);
			enterTextToInputField(Locators_ScoutSanity.SWR_Enter_Address_1, "J P Nagar,3rd phase");
			customWait(1000);
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
			enterTextToInputField(Locators_ScoutSanity.SWR_enter_City, "Banglore");
			customWait(1000);
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
			enterTextToInputField(Locators_ScoutSanity.SWR_Enter_State_Name, "Karnataka");
			customWait(1000);
			aDriver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().className(\"android.widget.ScrollView\")).scrollIntoView(new UiSelector().text(\"Enter Country\"))");  
			customWait(1000);
			enterTextToInputField(Locators_ScoutSanity.SWR_Country, "India");
			customWait(1000);
			customWait(1000);
			enterTextToInputField(Locators_ScoutSanity.SWR_Enter_Email_Id, "sonim");
			Runtime.getRuntime().exec("adb shell input keyevent --longpress KEYCODE_1");
			customWait(1000);
			clickBtn(Locators_ScoutSanity.SWR_At_the_Rate_Symbol);
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_ENTER);
			Runtime.getRuntime().exec("adb shell input text gmail.com");			       
			customWait(1000);
			aDriver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().className(\"android.widget.ScrollView\")).scrollIntoView(new UiSelector().text(\"Enter phone number\"))");
			customWait(1000);
			enterTextToInputField(Locators_ScoutSanity.SWR_Enter_Phone_Number, "9989898989");
			customWait(1000);
			aDriver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().className(\"android.widget.ScrollView\")).scrollIntoView(new UiSelector().text(\"Select Date\"))").click();
			customWait(1000);
			clickBtn(Locators_ScoutSanity.SWR_ChooseBtn);
			customWait(1000);	
			for(int i=1; i<=8; i++) {
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_UP);
			}
			clickBtn(Locators_ScoutSanity.SWR_Reset);
			/*
			 * Validating back from warranty when acess from Device information
			 */
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			minWait();

			if(isElementExist(Locators_ScoutSanity.DevInf_Register)) {
				check4=true;
				APP_LOGS.info("Test case passed Validated");			
			}	

			if((check1)&&(check2)&&(check3)&&(check4)) {
				check= true;
				APP_LOGS.info("Scout App Launched and Device information is able Acess SWR");
				S11.assertTrue(check, " ");
			}
			else{
				APP_LOGS.info(check1+"\n"+": "+check2+"\n"+": "+check3+ "\n"+check4+ "\n");		
				S11.fail();
			}aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
		} catch (org.openqa.selenium.NoSuchElementException e) {
			e.printStackTrace();
			System.out.println(" HEllo   ");
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			S11.fail();
		}	  
		S11.assertAll();
	}

	public void validateRemoteControl() throws InterruptedException {
		/*
		 * Validate Remote control Device ID
		 */
      SoftAssert S13 = new SoftAssert();
		try {
			minWait();
			if(isElementExist(Locators_ScoutSanity.RemoteControl_pge)) {
				check1= true;
				APP_LOGS.info("Remote control is Acessed from Scout");	
			}
			customWait(1000);
			if(isElementExist(Locators_ScoutSanity.RemoteControl_DeviceID)) {
				String currentDeviceIdName = Locators_ScoutSanity.RemoteControl_DeviceID.getText();
				APP_LOGS.info("isDeviceRegistered :"+currentDeviceIdName);
				check2 = true;
				APP_LOGS.info("Device Id is displayed in Remote Control");	
			}
			
			if(check1 && check2) {
				check = true;
				APP_LOGS.info("Device Id and Remote control is displayed in Remote Control");	
				S13.assertTrue(check, " ");
			}
			else {
				APP_LOGS.info("Device Id and Remote control is not displayed in Remote Control");	
				S13.fail();
				
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {
			e.printStackTrace();
			S13.fail();
		}
		S13.assertAll();
	}


	public void validateChatFields() throws InterruptedException, IOException {
		/*
		 * Validate Chat application is launched
		 */
		SoftAssert SA = new SoftAssert();
		try {
			if(isElementExist(Locators_ScoutSanity.Chat_Title)) {
				check =true;
				APP_LOGS.info("Scout App Launched and Sonim Chat is present");
				SA.assertTrue(check, " ");
			}
			else{
				APP_LOGS.info("Scout App Launched and Sonim Chat is not validated");
				SA.fail();
			}
			
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			SA.fail();
		}
		SA.assertAll();
	}


	public void validate_Current_Screen(WebElement contactTransferTitle) throws InterruptedException{

		/*
		 * Validates the current screen.
		 * Pass the element to be validated as parameter.
		 * 
		 */
		try {
			// System.out.println(aDriver.getTitle()); Check to implement
			if(isElementExist(Locators_ScoutSanity.ContactTransferTitle)){
				check = true;
				String getTitleText = contactTransferTitle.getText();
				APP_LOGS.info("Current Screen is verified succesfully.");
				APP_LOGS.info("Current Screen title: "+getTitleText);
				softAssert_true(check, "Test case is pass");
			}else{
				APP_LOGS.info("Testcase failed");
				softAssert_false();
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {
			e.printStackTrace();
			Assert.fail();
		}		
		minWait();
		aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
		minWait();
	}

	public void select_Transfer_Mode(String mode) throws InterruptedException, IOException{
		/*
		 * Selects the mode of transfer in contact transfer screen.
		 * Pass mode as parameter.
		 */
		//			minWait();

		if(mode.equalsIgnoreCase("Bluetooth")){
			//("Via Bluetooth");
			minWait();
			if(isElementExist(Locators_ScoutSanity.AllowBtn));
			{
				clickBtn(Locators_ScoutSanity.AllowBtn);
				APP_LOGS.info("clicked Allow btn ");
				minWait();
			}
			APP_LOGS.info(" Good===== ");						
			if(isElementExist(Locators_ScoutSanity.BluetoothOptn)) {
//				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
				Runtime.getRuntime().exec("adb shell input keyevent 23");
				minWait();
			}	
			if(isElementExist(Locators_ScoutSanity.Allow_BT_Btn)) {
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_RIGHT);
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
				APP_LOGS.info("Selected BT Import ");
			}
			
		}else if(mode.equalsIgnoreCase("mdb")){
		//	//("Via MDB");
			APP_LOGS.info("Selected mdb Import ");
			minWait();
		}else if(mode.equalsIgnoreCase("vcf")){
		//	//("Via VCF");
			APP_LOGS.info("Selected Vcf Import ");
			minWait();
		}else if(mode.equalsIgnoreCase("csv")){
		//	//("Via CSV");	
			APP_LOGS.info("Selected csv Import ");
			minWait();
		}
	}
	
	public void appPermissions() throws InterruptedException, IOException {
		try {
//			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
			
			for(int i=1; i<=3;i++) {
				if(isElementExist(Locators_ScoutSanity.AllowBtn));
				{
					clickBtn(Locators_ScoutSanity.AllowBtn);
					APP_LOGS.info("clicked Allow btn ");
					minWait();
				}
			}
//			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			minWait();
//			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
//			minWait();
			//Runtime.getRuntime().exec("adb shell input keyevent 4");
			APP_LOGS.info(" Got device permission");
			
		} catch (NoSuchElementException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Assert.fail();
		}
//		aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
	}

	public void validateAllOptionsSelect(String title1, String title2, String title3, String title4) throws InterruptedException, IOException {
		/*
		 * After selecting mode of transfer validate directed Browse file Title
		 */
		WebDriverWait wait = new WebDriverWait(aDriver, 30); 
		try {
			while (isTextPresent("ALLOW")) {
				clickBtnWithText("ALLOW");
				minWait();
			}
			//("Via Bluetooth");
			appPermissions();
		    //clearRecentApps();
			//navigateApps(Locators_ScoutSanity.utilitiesTab,"Contact Transfer");
//			select_Transfer_Mode("Bluetooth");
//			minWait();
//			minWait();			
			minWait();
			APP_LOGS.info(" ALl is well");
			wait.until(ExpectedConditions.visibilityOf(Locators_ScoutSanity.Title_BTOptn));
			validateBrowse_Title(Locators_ScoutSanity.Title_BTOptn,title1);
			maxWait();
//			waituntilnew(Locators_ScoutSanity.Title_BTOptn, 10);
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			customWait(1000);
//			navigateApps(Locators_ScoutSanity.utilitiesTab,"Contact Transfer");
//			select_Transfer_Mode("mdb");
			//("Via MDB");
			minWait();
			validateBrowse_Title(Locators_ScoutSanity.Title_MBDOptn,title2);
			customWait(500);
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			customWait(1000);
//			select_Transfer_Mode("vcf");
		//	//("Via VCF");
			minWait();
			validateBrowse_Title(Locators_ScoutSanity.Title_VCFOptn,title3);
			customWait(500);
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			customWait(500);
//			select_Transfer_Mode("csv");
		//	//("Via CSV");
			minWait();
			validateBrowse_Title(Locators_ScoutSanity.Title_CSVOptn,title4);	
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
		}
		catch (org.openqa.selenium.NoSuchElementException e) {		
			e.printStackTrace();
			Assert.fail();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
		}

	}

	public void validateBrowse_Title(WebElement title, String BrowseTitle) throws InterruptedException {
		/*
		 * After selecting mode of transfer validate directed Browse file Title
		 */
		SoftAssert Sa = new SoftAssert();
		try {
			customWait(12000);
			String s=title.getText();
			System.out.println(s);
			APP_LOGS.info(" Entered.....");
			if(s.equalsIgnoreCase(BrowseTitle)) {
				check= true;
				APP_LOGS.info("verified Browse Title Contact Imported "+ title);
				Sa.assertTrue(check, " ");
			}else{
				APP_LOGS.info("Unverified Browse Title Contact Imported "+ title);
				Sa.fail();
			}
		} catch (NoSuchElementException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Sa.fail();
		}	Sa.assertAll();
	}

	public void selectContactfileToImport(int num) throws InterruptedException {
		/*
		 * Select file and choose all contact to import
		 */
		try {
			minWait();
			for(int i=1; i<=2;i++) {
				if(isElementExist(Locators_ScoutSanity.AllowBtn));
				{
					clickBtn(Locators_ScoutSanity.AllowBtn);
				}
			}
			/*for(int i=0; i<=1;i++) {
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
			}*/
			///Select Download folder
				scrollListWithText("Download");
			//			aDriver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().className(\"android.widget.ScrollView\")).scrollIntoView(new UiSelector().text(\"Download\"))");		
				/*aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_ENTER);
				minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_ENTER);//Open Contacts file to be imported
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
			minWait();*/
			
			/*for(int i=0; i<=2;i++) {
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_UP);
				minWait();
			}minWait();*/
			minWait();	
			switch(num) {
				case 1:
					navigateUsingText("MMS_Contact.vcf");
					break;
				case 2:
					navigateUsingText("i475.mdb");
					break;
				case 3:
					navigateUsingText("CSV.csv");
					break;
			}
			
			
			//clickBtn(Locators_ScoutSanity.vcf_file_contact_transfer);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_ENTER);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_LEFT);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_ENTER);//selects All option
			minWait();
			
			
			
		}
		catch (NoSuchElementException e) {
			APP_LOGS.info("Error: Element not found.");
			e.printStackTrace();
			Assert.fail();
		}	
	}

	public void validateAllContactImport(String AdbTxt) throws InterruptedException {
		/*
		 * String validate Imported summary
		 */
		SoftAssert S = new SoftAssert();
		customWait(7000);
		customWait(5000);
		check1 = searchString("Checkbox Imported Successfully",AdbTxt);
		if(check1)
		{
			check = true;
			APP_LOGS.info(" Contacts are imported");
			S.assertTrue(check, " ");
		}
		else {
			APP_LOGS.info("Contacts are not imported");
			S.fail();
		}
		S.assertAll();
	}


	public void pressBackBtn() throws InterruptedException {
		for(int i=0;i<=4;i++) {
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			minWait();
		}
	}


	public void selectContactfileToImport_SL(int num) throws InterruptedException {
		/*
		 * Select file and choose all contact to import
		 */
		try {
			minWait();
			for(int i=1; i<=2;i++) {
				if(isElementExist(Locators_ScoutSanity.AllowBtn));
				{
					clickBtn(Locators_ScoutSanity.AllowBtn);
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
		}catch (NoSuchElementException e) {
			APP_LOGS.info("Error: Element not found.");
			e.printStackTrace();
			Assert.fail();
		}	
	}

	public void validateSummaryHistory() {
		/*
		 * Validate Summary History after importing contacts from any options or modes 
		 */
		try {
			if(isElementExist(Locators_ScoutSanity.Title_ImportSummary)) {
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
			Assert.fail();
		}
	}


	public String sf_app_Check_Activation_Off_On() throws InterruptedException, IOException {
		//This method is used to check wheather activation is ON or OFF .
		minWait();
		String status = null;
		try {
			if(Locators_ScoutSanity.SG_toggle_btn.getText().equals("OFF")) {
				status=Locators_ScoutSanity.SG_toggle_btn.getText();
				APP_LOGS.info("Checking Activation status for OFF" +status);
			}
			else if(Locators_ScoutSanity.SG_toggle_btn.getText().equals("ON")){
				status=Locators_ScoutSanity.SG_toggle_btn.getText();
				APP_LOGS.info("Checking Activation status for ON" +status);
			}
		} catch (NoSuchElementException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Assert.fail();
		}
		return status;
	}

	//This method is used to check the deafult state of APPS,Features,Pintime out should be accessible 
	public void verify_Default_MenuStates_Of_All_Options_SG() throws InterruptedException, IOException {

		SoftAssert SA5=new SoftAssert();

		try {
			if(sf_app_Check_Activation_Off_On().equals("OFF")) {

				turn_Off_On_SG();
				SA5.assertTrue(Locators_ScoutSanity.SG_Applications_String.isEnabled(),"Application is enabed");
				APP_LOGS.info("Checking the Applications is enabled or not :" +Locators_ScoutSanity.SG_Applications_String.isEnabled());
				SA5.assertTrue(Locators_ScoutSanity.SG_Features_String.isEnabled(),"Feature is enabled");
				APP_LOGS.info("Checking the Feature is enabled or not :" +Locators_ScoutSanity.SG_Features_String.isEnabled());
				SA5.assertTrue(Locators_ScoutSanity.SG_Change_Pin_String.isEnabled(),"Pin is enabled");
				APP_LOGS.info("Checking the Pin is enabled or not :" +Locators_ScoutSanity.SG_Change_Pin_String.isEnabled());
				minWait();
				for(int i=0;i<=5;i++) {
					aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
					customWait(1000);
				}
				minWait();
				SA5.assertTrue(Locators_ScoutSanity.SG_Version_String.isEnabled(), "Version is enabled");
				APP_LOGS.info("Checking the Version is enabled or not :" +Locators_ScoutSanity.SG_Version_String.isEnabled());
				SA5.assertTrue(Locators_ScoutSanity.SG_Help_String.isEnabled(), "Help is enabled");
				APP_LOGS.info("Checking the Help is enabled or not :" +Locators_ScoutSanity.SG_Help_String.isEnabled());

			}
			else {

				SA5.assertTrue(Locators_ScoutSanity.SG_Applications_String.isEnabled(),"Application is enabed");
				APP_LOGS.info("Checking the Applications is enabled or not :" +Locators_ScoutSanity.SG_Applications_String.isEnabled());
				SA5.assertTrue(Locators_ScoutSanity.SG_Features_String.isEnabled(),"Feature is enabled");
				APP_LOGS.info("Checking the Feature is enabled or not :" +Locators_ScoutSanity.SG_Features_String.isEnabled());
				SA5.assertTrue(Locators_ScoutSanity.SG_Change_Pin_String.isEnabled(),"Pin is enabled");
				APP_LOGS.info("Checking the Pin is enabled or not :" +Locators_ScoutSanity.SG_Change_Pin_String.isEnabled());
				minWait();
				for(int i=0;i<=5;i++) {
					aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
					customWait(1000);
				}
				minWait();
				SA5.assertTrue(Locators_ScoutSanity.SG_Version_String.isEnabled(), "Version is enabled");
				APP_LOGS.info("Checking the Version is enabled or not :" +Locators_ScoutSanity.SG_Version_String.isEnabled());
				SA5.assertTrue(Locators_ScoutSanity.SG_Help_String.isEnabled(), "Help is enabled");
				APP_LOGS.info("Checking the Help is enabled or not :" +Locators_ScoutSanity.SG_Help_String.isEnabled());
			}
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			SA5.fail();
		}

		SA5.assertAll();
		aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
	}

	//This Method will turn OFF/turn On the SF
	public void turn_Off_On_SG() throws InterruptedException {

		minWait();
		clickBtn(Locators_ScoutSanity.SG_toggle_btn);
		customWait(2000);
		enter_Pin();
	}

	//This method will enter Pin the activation screen
	public void enter_Pin() throws InterruptedException {

		minWait();
		aDriver.findElementByAndroidUIAutomator("UiSelector().resourceId(\"com.sonim.safeguard:id/dialog_edit_text\")").sendKeys("1234");
		APP_LOGS.info("PIN has been entered successfully");
		minWait();

		if(Locators_ScoutSanity.SG_OK_Btn_for_Pin.isEnabled()) {
			customWait(1000);
			clickBtn(Locators_ScoutSanity.SG_OK_Btn_for_Pin);
		}
		else {
			APP_LOGS.info("Pin Ok button is not enabled");
			softAssert_false();
		}
	}
	
	
	public void enterPin() throws InterruptedException {

		minWait();
		aDriver.findElementByAndroidUIAutomator("UiSelector().packageName(\"com.sonim.safeguard\").className(\"android.widget.EditText\")").sendKeys("1234");
		APP_LOGS.info("PIN has been entered successfully");
		minWait();
		aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_ENTER);
		
	}

	//This method will be used to unselect all apps and validate
	public void Unselect_All_Apps_And_Check() throws InterruptedException, IOException {

		try {
			if(sf_app_Check_Activation_Off_On().equals("OFF")) {
				turn_Off_On_SG();
				pass_Locators(Locators_ScoutSanity.SG_Applications_String, Locators_ScoutSanity.SG_Un_Select_All_Btn, Locators_ScoutSanity.SG_Save_Btn);
				APP_LOGS.info("Launching All the Apps one by one and checking wheather SG locked screen is displayed or not");
				launching_All_Apps_One_By_One_With_Out_Locked_Screen();
			}
			else {
				APP_LOGS.info("Else Part : Activation is already ON and checking all the Apps one by one");
				pass_Locators(Locators_ScoutSanity.SG_Applications_String, Locators_ScoutSanity.SG_Un_Select_All_Btn, Locators_ScoutSanity.SG_Save_Btn);
				APP_LOGS.info("Launching All the Apps one by one and checking wheather SG locked screen is displayed or not");
				launching_All_Apps_One_By_One_With_Out_Locked_Screen();
			}
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			Assert.fail();
		}
	}

	public String special_case() throws InterruptedException, IOException {
		//This method is used to check wheather activation is ON or OFF .
		String status;
		status = null;
		try {
			minWait();		
			minWait();
			//aDriver.launchApp();
			//minWait();
			//aDriver.findElementByAndroidUIAutomator("UiSelector().resourceId(\"com.sonim.safeguard:id/editText\")").sendKeys("1234");
			//minWait();
			//aDriver.pressKeyCode(AndroidKeyCode.ENTER);
			//minWait();
			APP_LOGS.info("Inside Safeguard Settings screen");
			clickBtn(Locators_ScoutSanity.SG_SetUp_String);
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
			clickBtn(Locators_ScoutSanity.SG_Safe_Guard_String);

			if(Locators_ScoutSanity.SG_toggle_btn.getText().equals("OFF")) {
				status=Locators_ScoutSanity.SG_toggle_btn.getText();
				APP_LOGS.info("Checking Activation status for OFF" +status);
			}
			else if(Locators_ScoutSanity.SG_toggle_btn.getText().equals("ON")){
				status=Locators_ScoutSanity.SG_toggle_btn.getText();
				APP_LOGS.info("Checking Activation status for ON" +status);
			}
		} catch (NoSuchElementException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Assert.fail();
		}
		return status;
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
			customWait(2000);
			clickBtn(loc2);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.MENU);
			customWait(3000);
			clickBtn(loc3);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.BACK);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.BACK);
		} catch (NoSuchElementException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Assert.fail();
		}
	}



	//This method will launch All the Apps present in the device one by one and validate it 
	public void launching_All_Apps_One_By_One() throws InterruptedException, IOException {

		SoftAssert SA7=new SoftAssert();
		customWait(1000);
		Runtime.getRuntime().exec("adb shell am start -n  com.caf.fmradio/com.caf.fmradio.FMRadio");
		APP_LOGS.info("FM launched successfully");
		customWait(2000);
		try {
			if(isElementExist(Locators_ScoutSanity.SG_Locked_Screen_Validation) || isElementExist(Locators_ScoutSanity.SG_Safe_Guard_Locked_Screen_Edit_Box)) {

				ch1=true;
				APP_LOGS.info("Element FM is selected");
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
			if(isElementExist(Locators_ScoutSanity.SG_Locked_Screen_Validation) || isElementExist(Locators_ScoutSanity.SG_Safe_Guard_Locked_Screen_Edit_Box)) {
				ch2=true;
				APP_LOGS.info("Element mms is selected");

			}
		}
		catch(NoSuchElementException e) {
			e.printStackTrace();
			APP_LOGS.info("Element not Found Execption:mms");
		}
		minWait();
		Runtime.getRuntime().exec("adb shell am start -n com.borqs.camera/com.borqs.camera.CameraActivity");
		APP_LOGS.info("Camera launched successfully");
		customWait(2000);
		try {
			if(isElementExist(Locators_ScoutSanity.SG_Locked_Screen_Validation)) {
				ch3=true;	
				APP_LOGS.info("Element camera is selected");
			}
		}
		catch (NoSuchElementException e) {
			e.printStackTrace();
			APP_LOGS.info("Element not Found Execption:Camera");
		}
		minWait();
		Runtime.getRuntime().exec("adb shell am start -n com.android.soundrecorder/com.android.soundrecorder.SoundRecorder");
		APP_LOGS.info("sound recorder launched successfully");
		customWait(3000);
		try {
			if(isElementExist(Locators_ScoutSanity.SG_Locked_Screen_Validation) || isElementExist(Locators_ScoutSanity.SG_Safe_Guard_Locked_Screen_Edit_Box)) {
				ch4=true;
				APP_LOGS.info("Element sound recorder is selected");
			}
		}
		catch (NoSuchElementException e) {
			e.printStackTrace();
			APP_LOGS.info("Element not Found Execption:Sound Recorder");
		}
		minWait();
		Runtime.getRuntime().exec("adb shell am start -n com.android.deskclock/com.android.deskclock.DeskClock");
		APP_LOGS.info("Setup Wizard launched successfully");
		customWait(3000);
		try {
			if(isElementExist(Locators_ScoutSanity.SG_Locked_Screen_Validation)) {
				ch5=true;
				APP_LOGS.info("Element SEtup Wizard is selected");
			}
		}
		catch (NoSuchElementException e) {
			e.printStackTrace();
			APP_LOGS.info("Element not Found Execption:clock");
		}
		minWait();
		Runtime.getRuntime().exec("adb shell am start -n com.android.browser/com.android.browser.BrowserActivity");
		APP_LOGS.info("Browser launched successfully");
		customWait(3000);
		try {
			if(isElementExist(Locators_ScoutSanity.SG_Locked_Screen_Validation)) {
				ch6=true;	
				APP_LOGS.info("Element Browser is selected");
			}
		}
		catch(NoSuchElementException e) {
			e.printStackTrace();
			APP_LOGS.info("Element not Found Execption:browser");
		}
		minWait();
		Runtime.getRuntime().exec("adb shell am start -n com.android.contacts/com.android.contacts.activities.PeopleActivity");
		APP_LOGS.info("Contact launched successfully");
		customWait(3000);
		try {
			if(isElementExist(Locators_ScoutSanity.SG_Locked_Screen_Validation) || isElementExist(Locators_ScoutSanity.SG_Safe_Guard_Locked_Screen_Edit_Box)) {
				ch7=true;
				APP_LOGS.info("Element contacts is selected");
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
			if(isElementExist(Locators_ScoutSanity.SG_Locked_Screen_Validation) || isElementExist(Locators_ScoutSanity.SG_Safe_Guard_Locked_Screen_Edit_Box)) {

				ch9=true;
				APP_LOGS.info("Element Dailer is selected");
			}
		}
		catch (NoSuchElementException e) {
			e.printStackTrace();
			APP_LOGS.info("Element not Found Execption:Dialer");
		}
		minWait();
		Runtime.getRuntime().exec("adb shell am start -n com.borqs.backup/com.borqs.backup.activity.BackupRestoreActivity");
		APP_LOGS.info("Back up and restore launched successfully");
		customWait(3000);
		try {
			if(isElementExist(Locators_ScoutSanity.SG_Locked_Screen_Validation) || isElementExist(Locators_ScoutSanity.SG_Safe_Guard_Locked_Screen_Edit_Box)) {

				ch10=true;	
				APP_LOGS.info("Element Backup and restore is selected");
			}
		}
		catch (NoSuchElementException e) {
			e.printStackTrace();
			APP_LOGS.info("Element not Found Execption:Backup & restore");
		}
		minWait();
		Runtime.getRuntime().exec("adb shell am start -n com.android.gallery3d/com.android.gallery3d.app.Gallery");
		APP_LOGS.info("Waranty launched successfully");
		customWait(3000);
		try {
			if(isElementExist(Locators_ScoutSanity.SG_Locked_Screen_Validation) || isElementExist(Locators_ScoutSanity.SG_Safe_Guard_Locked_Screen_Edit_Box)) {

				ch13=true;
				APP_LOGS.info("Element Warranty is selected");
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
			if(isElementExist(Locators_ScoutSanity.SG_Locked_Screen_Validation) || isElementExist(Locators_ScoutSanity.SG_Safe_Guard_Locked_Screen_Edit_Box)) {

				ch14=true;
				APP_LOGS.info("Element Settings is selected");
			}
		}
		catch (NoSuchElementException e) {
			e.printStackTrace();
			APP_LOGS.info("Element not Found Execption:Settings");
		}
		minWait();
		Runtime.getRuntime().exec("adb shell am start -n com.android.calculator2/com.android.calculator2.Calculator");
		APP_LOGS.info("Calculator launched successfully");
		customWait(3000);
		try {
			if(isElementExist(Locators_ScoutSanity.SG_Locked_Screen_Validation) || isElementExist(Locators_ScoutSanity.SG_Safe_Guard_Locked_Screen_Edit_Box)) {
				ch15=true;
				APP_LOGS.info("Element calculator is selected");
			}
		}
		catch (NoSuchElementException e) {
			e.printStackTrace();
			APP_LOGS.info("Element not Found Execption:Calculator");
		}
		minWait();
		Runtime.getRuntime().exec("adb shell am start -n com.android.calendar/com.android.calendar.AllInOneActivity");
		APP_LOGS.info("Calender launched successfully");
		customWait(3000);
		try {
			if(isElementExist(Locators_ScoutSanity.SG_Locked_Screen_Validation) || isElementExist(Locators_ScoutSanity.SG_Safe_Guard_Locked_Screen_Edit_Box)) {

				ch16=true;
				APP_LOGS.info("Element Calender is selected");
			}
		}
		catch (NoSuchElementException e) {
			e.printStackTrace();
			APP_LOGS.info("Element not Found Execption:Calemder");
		}
		minWait();
		Runtime.getRuntime().exec("adb shell am start -n com.android.providers.downloads.ui/com.android.providers.downloads.ui.DownloadList");
		APP_LOGS.info("Download launched successfully");
		customWait(3000);
		try {
			if(isElementExist(Locators_ScoutSanity.SG_Locked_Screen_Validation) || isElementExist(Locators_ScoutSanity.SG_Safe_Guard_Locked_Screen_Edit_Box)) {

				ch17=true;
				APP_LOGS.info("Element Download is selected");
			}
		}
		catch (NoSuchElementException e) {
			e.printStackTrace();
			APP_LOGS.info("Element not Found Execption:Download");
		}

		if((ch1) && (ch2) && (ch3) && (ch4) && (ch5) && (ch6) && (ch7) && (ch9) && (ch10) && (ch13) && (ch14) && (ch15) && (ch16) && (ch17)) {

			check=true;
			APP_LOGS.info("All APPS laucned successfully");
			SA7.assertTrue(check, "All PASS");
		}
		else {
			APP_LOGS.info("Values of All Apps are :" +ch1+ "," +ch2+ "," +ch3+ "," +ch4+ "," +ch5+ "," +ch6+ "," +ch7+ "," +ch9+ "," +ch10+ "," +ch13+ "," +ch14+ "," +ch15+ "," +ch16+ "," +ch17);
			SA7.fail();
		}
		SA7.assertAll();
		minWait();
	}


	public void launching_All_Apps_One_By_One_With_Out_Locked_Screen() throws IOException, InterruptedException {


		SoftAssert SA8=new SoftAssert();

		Runtime.getRuntime().exec("adb shell am start -n  com.caf.fmradio/com.caf.fmradio.FMRadio");
		APP_LOGS.info("FM launched successfully");
		customWait(2000);
		if(isElementExist(Locators_ScoutSanity.SG_FM)) {
			ch1=true;
			APP_LOGS.info("ch1 launched successfully"+ch1);

		}

		minWait();
		Runtime.getRuntime().exec("adb shell am start -n com.android.mms/com.android.mms.ui.ConversationList");
		APP_LOGS.info("mms launched successfully");
		customWait(2000);
		if(isElementExist(Locators_ScoutSanity.SG_Message)) {
			ch2=true;
			APP_LOGS.info("ch2 launched successfully"+ch2);
		}

		minWait();
		Runtime.getRuntime().exec("adb shell am start -n com.borqs.camera/com.borqs.camera.CameraActivity");
		APP_LOGS.info("Camera launched successfully");
		customWait(2000);
		if(isElementExist(Locators_ScoutSanity.SG_Camera)) {
			ch3=true;
			APP_LOGS.info("ch3 launched successfully"+ch3);
		}
		minWait();
		
		Runtime.getRuntime().exec("adb shell am start -n com.android.soundrecorder/com.android.soundrecorder.SoundRecorder");
		APP_LOGS.info("sound recorder launched successfully");
		customWait(2000);
		if(isElementExist(Locators_ScoutSanity.SG_Sound_Recoder)){
			ch4=true;
			APP_LOGS.info("ch4 launched successfully"+ch4);
		}

		minWait();
		Runtime.getRuntime().exec("adb shell am start -n com.sonimtech.setupwizard/com.sonimtech.setupwizard.SetupLauncherActivity");
		APP_LOGS.info("Setup Wizard launched successfully");
		customWait(2000);
		if(isElementExist(Locators_ScoutSanity.SG_SetUpwizard)) {
			ch5=true;
			APP_LOGS.info("ch5 launched successfully"+ch5);
		}

		minWait();
		Runtime.getRuntime().exec("adb shell am start -n com.android.contacts/com.android.contacts.activities.PeopleActivity");
		APP_LOGS.info("Contact launched successfully");
		customWait(2000);
		if((isElementExist(Locators_ScoutSanity.SG_ADD_CONTACT_String) || isElementExist(Locators_ScoutSanity.SG_Find_Contacts))) {
			ch6=true;
			APP_LOGS.info("ch6 launched successfully"+ch6);
		}
		minWait();
		Runtime.getRuntime().exec("adb shell am start -n com.android.dialer/com.android.dialer.DialerMainActivity");
		APP_LOGS.info("Dialer launched successfully");
		customWait(2000);
		if(isElementExist(Locators_ScoutSanity.SG_RecentCalls)) {
			ch7=true;
			APP_LOGS.info("ch7 launched successfully"+ch7);
		}
		minWait();
		Runtime.getRuntime().exec("adb shell am start -n com.borqs.backup/com.borqs.backup.activity.BackupRestoreActivity");
		APP_LOGS.info("Back up and restore launched successfully");
		customWait(2000);
		if(isElementExist(Locators_ScoutSanity.SG_BackUp_restore)) {
			ch8=true;
			APP_LOGS.info("ch8 launched successfully"+ch8);
		}
		minWait();
		aDriver.startActivity("com.sonim.ble.connect", "com.sonim.ble.connect.BLEDiscoverActivity");
		APP_LOGS.info("BLE launched successfully");
		customWait(2000);
		if(isElementExist(Locators_ScoutSanity.SG_BLE_Connect) || isElementExist(Locators_ScoutSanity.SG_Bluetooth_Error_Msg)) {
			ch9=true;
			customWait(1000);
			APP_LOGS.info("ch9 launched successfully"+ch9);
		}
		Runtime.getRuntime().exec("adb shell am start -n com.android.gallery3d/com.android.gallery3d.app.Gallery");
		APP_LOGS.info("Gallery launched successfully");
		customWait(2000);
		if(isElementExist(Locators_ScoutSanity.SG_Album)) {
			ch10=true;
			APP_LOGS.info("ch10 launched successfully"+ch10);
		}
		minWait();
		Runtime.getRuntime().exec("adb shell am start -n com.android.settings/com.android.settings.Settings");
		APP_LOGS.info("Settings launched successfully");
		customWait(3000);
		if(isElementExist(Locators_ScoutSanity.SG_Native_Settings)) {
			ch11=true;
			APP_LOGS.info("ch11 launched successfully"+ch11);
		}
		minWait();
		Runtime.getRuntime().exec("adb shell am start -n com.android.calculator2/com.android.calculator2.Calculator");
		APP_LOGS.info("Calculator launched successfully");
		customWait(2000);
		if(isElementExist(Locators_ScoutSanity.SG_Calc)){
			ch12=true;
			APP_LOGS.info("ch12 launched successfully"+ch12);
		}
		minWait();
		Runtime.getRuntime().exec("adb shell am start -n com.android.providers.downloads.ui/com.android.providers.downloads.ui.DownloadList");
		APP_LOGS.info("Download launched successfully");
		customWait(2000);
		if(isElementExist(Locators_ScoutSanity.SG_Downlaod)) {
			ch14=true;
			APP_LOGS.info("ch14 launched successfully"+ch14);
		}

		if((ch1) && (ch2) && (ch3) && (ch4) && (ch5) && (ch6) && (ch7) && (ch8) && (ch9) && (ch10) && (ch11) && (ch12) && (ch14)) {
			check=true;
			APP_LOGS.info("All APPS laucned successfully");
			SA8.assertTrue(check, "All PASS");
		}
		else {
			APP_LOGS.info(" The values are :"+ch1+","+ch2+","+ch3+ ","+ch3+","+ch4+","+ch5+","+ch6+","+ch7+","+ch8+","+ch9+","+ch10+","+ch11+","+ch12+","+ch14 );
			SA8.fail();
		}
		SA8.assertAll();
		minWait();
	}


	//THis case will be use to select All apps and check wheather sf screen is showing or not

	public void select_All_Apps_And_Check() throws InterruptedException, IOException {

		try {
			customWait(1000);
			for(int i=1; i<=5; i++) {
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_UP);
			}//Scroll up 

			if(sf_app_Check_Activation_Off_On().equals("OFF")) {
				turn_Off_On_SG();
				pass_Locators(Locators_ScoutSanity.SG_Applications_String, Locators_ScoutSanity.SG_Select_All_Btn, Locators_ScoutSanity.SG_Save_Btn);
				APP_LOGS.info("Launching All the Apps one by one and checking wheather SG locked screen is displayed or not");
				launching_All_Apps_One_By_One();
			}
			else {
				APP_LOGS.info("Else Part : Activation is already ON and checking all the Apps one by one");
				pass_Locators(Locators_ScoutSanity.SG_Applications_String, Locators_ScoutSanity.SG_Select_All_Btn, Locators_ScoutSanity.SG_Save_Btn);
				APP_LOGS.info("Launching All the Apps one by one and checking wheather SG locked screen is displayed or not");
				launching_All_Apps_One_By_One();
			}
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			Assert.fail();
		}
	}

	//This method is used to check the feature Tab present in the SG
	public void check_Features_Tab() throws InterruptedException, IOException {

		try {
			minWait();
			if(sf_app_Check_Activation_Off_On().equals("OFF")) {
				turn_Off_On_SG();
				minWait();
				clickBtn(Locators_ScoutSanity.SG_Features_String);
				minWait();
				enter_Pin();
				customWait(2000);
				aDriver.pressKeyCode(AndroidKeyCode.MENU);
				customWait(2000);
				clickBtn(Locators_ScoutSanity.SG_Select_All_Btn);
				minWait();
				customWait(2000);
				aDriver.pressKeyCode(AndroidKeyCode.MENU);
				customWait(2000);
				clickBtn(Locators_ScoutSanity.SG_Save_Btn);
				minWait();
				APP_LOGS.info("Launching All features one by one");
				launchFeatures();
				customWait(2000);
				aDriver.pressKeyCode(AndroidKeyCode.HOME);
			}

			else {
				APP_LOGS.info("Checking the features When Activastion is ON ");
				minWait();
				clickBtn(Locators_ScoutSanity.SG_Features_String);
				minWait();
				enter_Pin();
				customWait(2000);
				aDriver.pressKeyCode(AndroidKeyCode.MENU);
				customWait(2000);
				clickBtn(Locators_ScoutSanity.SG_Select_All_Btn);
				customWait(2000);
				aDriver.pressKeyCode(AndroidKeyCode.MENU);
				customWait(2000);
				clickBtn(Locators_ScoutSanity.SG_Save_Btn);
				minWait();
				APP_LOGS.info("Launching All the Features");
				launchFeatures();
				customWait(2000);
			}
		} catch (NoSuchElementException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Assert.fail();
		}
	}




	public void launchFeatures() throws IOException, InterruptedException {		
		SoftAssert SA=new SoftAssert();
		customWait(2000);
		Runtime.getRuntime().exec("adb shell am start -n com.android.settings/com.android.settings.Settings");
		minWait();
		aDriver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().resourceId(\"com.android.settings:id/main_content\")).scrollIntoView(new UiSelector().text(\"Bluetooth\"))");  
		customWait(2000);		
		clickBtn(Locators_ScoutSanity.SG_Bluetooth);
		minWait();
		for(int i=1; i<=9; i++) {
			if(isElementExist(Locators_ScoutSanity.SG_Location_Off_On_Btn)) {
				break;
			}
			else {
				customWait(8000);
				continue;
			}
		}
		clickBtn(Locators_ScoutSanity.SG_Location_Off_On_Btn);
		customWait(2000);

		if(isElementExist(Locators_ScoutSanity.SG_Locked_Screen_Validation) || isElementExist(Locators_ScoutSanity.SG_Safe_Guard_Locked_Screen_Edit_Box)){
			ch1=true;
			APP_LOGS.info("Bluetooth ch1 =" +ch1);
		}		
		customWait(2000);

		//This part is used for scrolling tof
		minWait();
		Runtime.getRuntime().exec("adb shell am start -n com.android.settings/com.android.settings.Settings");
		customWait(2000);
		aDriver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().resourceId(\"com.android.settings:id/main_content\")).scrollIntoView(new UiSelector().text(\"Factory reset\"))");
		customWait(2000);
		clickBtn(Locators_ScoutSanity.SG_Factory_reset);
		customWait(2000);
		if(isElementExist(Locators_ScoutSanity.SG_Locked_Screen_Validation) || isElementExist(Locators_ScoutSanity.SG_Safe_Guard_Locked_Screen_Edit_Box)) {
			check=true;
			APP_LOGS.info("TC Passed factory Reset " +check);
			//  softAssert_true(check, "Messages");
			SA.assertTrue(check, "");  
		}
		else {
			APP_LOGS.info("Test is for factory Reset");
			//  softAssert_false();
			SA.fail();
		}

		//This is for WIFI 
		customWait(2000);
		Runtime.getRuntime().exec("adb shell am start -n com.android.settings/com.android.settings.Settings");
		customWait(2000);
		aDriver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().resourceId(\"com.android.settings:id/main_content\")).scrollIntoView(new UiSelector().text(\"Wi-Fi\"))").click();
		customWait(2000);
		clickBtn(Locators_ScoutSanity.SG_Location_Off_On_Btn);
		customWait(2000);
		if(isElementExist(Locators_ScoutSanity.SG_Locked_Screen_Validation) || isElementExist(Locators_ScoutSanity.SG_Safe_Guard_Locked_Screen_Edit_Box)) {
			check=true;
			APP_LOGS.info("TC Passed wifi " +check);
			//  softAssert_true(check, "Messages");
			SA.assertTrue(check, "");
		}
		else {
			APP_LOGS.info("Test is for factory Reset");
			// softAssert_false();
			SA.fail();
		}
		SA.assertAll();
	}

	//This Method will unselect all features and check
	public void  Unselect_All_Features_And_Check() throws IOException, InterruptedException {

		try {
			minWait();
			clickBtn(Locators_ScoutSanity.SG_Features_String);
			minWait();
			enter_Pin();
			customWait(2000);
			aDriver.pressKeyCode(AndroidKeyCode.MENU);
			customWait(2000);
			clickBtn(Locators_ScoutSanity.SG_Un_Select_All_Btn);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.MENU);
			customWait(2000);
			clickBtn(Locators_ScoutSanity.SG_Save_Btn);
			minWait();
			APP_LOGS.info("Launching All features one by one and checking that locked screen should not be present");
			turn_Off_On_SG();
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			Assert.fail();
		}		
	}


	public void application_Pin_30Sec_TimeOut() throws InterruptedException, IOException{

		APP_LOGS.info("-----------------------------------------");
		APP_LOGS.info("Application Pin Time Out");
		SoftAssert SA13=new SoftAssert();
		try {
			if(sf_app_Check_Activation_Off_On().equals("OFF")) {
				turn_Off_On_SG();
				customWait(1000);				
				clickBtn(Locators_ScoutSanity.SG_App_Pin_TimeOut_String);
				customWait(1000);
				APP_LOGS.info("Clicked on 30 Seconds in Applciaiton Time out");
				clickBtn(Locators_ScoutSanity.SG_App_Pin_TimeOut_30_Sec);
				customWait(10000);
				Runtime.getRuntime().exec("adb shell am start -n com.android.browser/com.android.browser.BrowserActivity");
				APP_LOGS.info("Browser launched successfully");
				customWait(1000);
				if(isElementExist(Locators_ScoutSanity.SG_Locked_Screen_Validation)) {
					check=true;
					SA13.assertTrue(check, "locked screen");
				}
				else {
					SA13.fail();
				}
			}
			else {
				clickBtn(Locators_ScoutSanity.SG_App_Pin_TimeOut_String);
				minWait();
				APP_LOGS.info("Clicked on 30 Seconds in Applciaiton Time out");
				clickBtn(Locators_ScoutSanity.SG_App_Pin_TimeOut_30_Sec);
				customWait(10000);
				System.out.println("Waiting for 30 seconds...");
				Runtime.getRuntime().exec("adb shell am start -n com.android.browser/com.android.browser.BrowserActivity");
				APP_LOGS.info("Browser launched successfully");
				customWait(1000);
				if(isElementExist(Locators_ScoutSanity.SG_Locked_Screen_Validation)) {
					check=true;
					SA13.assertTrue(check, "locked screen");
				}
				else {
					SA13.fail();
				}
			}
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			SA13.fail();
		}

		SA13.assertAll();

	}
	//End of the Method

	public void pin_Time_Out_Set_To_1_Min() throws InterruptedException, IOException {

		APP_LOGS.info("-------TC for PinTime out Set to 1 minutes--------------------------");
		SoftAssert SA15=new SoftAssert();
		customWait(1000);
		try {
			if(sf_app_Check_Activation_Off_On().equals("OFF")) {
				turn_Off_On_SG();
				customWait(1000);
				clickBtn(Locators_ScoutSanity.SG_App_Pin_TimeOut_String);
				customWait(1000);
				clickBtn(Locators_ScoutSanity.SG_App_Pin_TimeOut_1_Min);
				System.out.println("1 Mintime out is working fine");
				System.out.println("Now launching browser.......");
				Runtime.getRuntime().exec("adb shell am start -n com.android.browser/com.android.browser.BrowserActivity");
				customWait(2000);
				if(isElementExist(Locators_ScoutSanity.SG_Locked_Screen_Validation)) {
					check=true;
					APP_LOGS.info("------------Locked screen exist---------");
					SA15.assertTrue(check, "");
				}
				else {
					APP_LOGS.info("----------Locked screen does not exist----"+check);
					SA15.fail();
				}
			}
			else {
				APP_LOGS.info("--------Activation is ON and executing else part ----------------");
				clickBtn(Locators_ScoutSanity.SG_App_Pin_TimeOut_String);
				customWait(1000);
				clickBtn(Locators_ScoutSanity.SG_App_Pin_TimeOut_1_Min);
				customWait(50000);
				System.out.println("1 Mintime out is working fine");
				System.out.println("launching browser App ....");
				Runtime.getRuntime().exec("adb shell am start -n com.android.browser/com.android.browser.BrowserActivity");
				customWait(2000);
				if(isElementExist(Locators_ScoutSanity.SG_Locked_Screen_Validation)) {
					check=true;
					APP_LOGS.info("------------Locked screen exist---------");
					SA15.assertTrue(check, "");
				}
				else {
					APP_LOGS.info("----------Locked screen does not exist----"+check);
					SA15.fail();
				}
			}
		} catch (NoSuchElementException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			SA15.fail();
		}
		SA15.assertAll();
	}
	//This Ends the Method 



	public void special_Case_Select() throws InterruptedException, IOException {
		try {
			minWait();
			if(sf_app_Check_Activation_Off_On().equals("OFF")) {
				turn_Off_On_SG();
				minWait();
				clickBtn(Locators_ScoutSanity.SG_Applications_String);
				minWait();
				enter_Pin();
				customWait(2000);
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.MENU);
				customWait(2000);
				clickBtn(Locators_ScoutSanity.SG_Save_Btn);
				minWait();
			}
			else {
				APP_LOGS.info("Else Part : Activation is already ON and checking all the Apps one by one");
				minWait();
				clickBtn(Locators_ScoutSanity.SG_Applications_String);
				minWait();
				enter_Pin();
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.MENU);
				customWait(2000);
				clickBtn(Locators_ScoutSanity.SG_Save_Btn);
				minWait();
			}
		} catch (NoSuchElementException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Assert.fail();
		}
	}

	
	public void special_Case_SelectSprint() throws InterruptedException, IOException {
		try {
			minWait();
			if(sf_app_Check_Activation_Off_On().equals("OFF")) {
				turn_Off_On_SG();
				minWait();
				clickBtn(Locators_ScoutSanity.SG_Applications_String);
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
				clickBtn(Locators_ScoutSanity.SG_Save_Btn);
				minWait();
			}
			else {
				APP_LOGS.info("Else Part : Activation is already ON and checking all the Apps one by one");
				minWait();
				clickBtn(Locators_ScoutSanity.SG_Applications_String);
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
				clickBtn(Locators_ScoutSanity.SG_Save_Btn);
				minWait();
			}
		} catch (NoSuchElementException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Assert.fail();
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

	public void pressCenterKey(){
		aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
	}

	public void validate_Device_Info_Self_Diagnosis_screen() throws InterruptedException{
		/*
		 * Validates all the valuese(texts) of device information in self diagnosis screen.
		 */
		SoftAssert SA1 = new SoftAssert();
		try {
			minWait_SonimCare();
			if(isElementExist(Locators_ScoutSanity.model_value_text))
			{
				check1 = true;
				APP_LOGS.info("check1: " + check1);
				APP_LOGS.info("model_value_text Element is present");
				String getAlertText = Locators_ScoutSanity.model_value_text.getText();
				APP_LOGS.info("model_value_text: " + getAlertText);
			}else
			{
				APP_LOGS.info("check1: "+check1);
				APP_LOGS.info("model_value_text Element is not present");			
			}
			pressDownKey();
			minWait_SonimCare();
			if(isElementExist(Locators_ScoutSanity.imei_value_text))
			{
				check2 = true;
				APP_LOGS.info("check2: " + check2);
				APP_LOGS.info("imei_value_text Element is present");
				String getAlertText = Locators_ScoutSanity.imei_value_text.getText();
				APP_LOGS.info("imei_value_text: " + getAlertText);
			}else
			{
				APP_LOGS.info("check2: "+check2);
				APP_LOGS.info("model_value_text Element is not present");			
			}
			pressDownKey();
			minWait_SonimCare();
			if(isElementExist(Locators_ScoutSanity.android_version_value_text))
			{
				check3 = true;
				APP_LOGS.info("check3: " + check3);
				APP_LOGS.info("android_version_value_text Element is present");
				String getAlertText = Locators_ScoutSanity.android_version_value_text.getText();
				APP_LOGS.info("android_version_value_text: " + getAlertText);
			}else
			{
				APP_LOGS.info("check3: "+check3);
				APP_LOGS.info("android_version_value_text Element is not present");			
			}
			pressDownKey();
			minWait_SonimCare();
			if(isElementExist(Locators_ScoutSanity.build_value_text))
			{
				check4 = true;
				APP_LOGS.info("check4: " + check4);
				APP_LOGS.info("build_value_text Element is present");
				String getAlertText = Locators_ScoutSanity.build_value_text.getText();
				APP_LOGS.info("build_value_text: " + getAlertText);
			}else
			{
				APP_LOGS.info("check4: "+check4);
				APP_LOGS.info("build_value_text Element is not present");			
			}
			pressDownKey();
			minWait_SonimCare();
			if(isElementExist(Locators_ScoutSanity.phone_number_value_text))
			{
				check5 = true;
				APP_LOGS.info("check5: " + check5);
				APP_LOGS.info("phone_number_value_text Element is present");
				String getAlertText = Locators_ScoutSanity.phone_number_value_text.getText();
				APP_LOGS.info("phone_number_value_text: " + getAlertText);
			}else
			{
				APP_LOGS.info("check5: "+check5);
				APP_LOGS.info("phone_number_value_text Element is not present");			
			}
			pressDownKey();
			pressDownKey();
			minWait_SonimCare();
			if(isElementExist(Locators_ScoutSanity.network_type_value_text))
			{
				check6 = true;
				APP_LOGS.info("check6: " + check6);
				APP_LOGS.info("network_type_value_text Element is present");
				String getAlertText = Locators_ScoutSanity.network_type_value_text.getText();
				APP_LOGS.info("network_type_value_text: " + getAlertText);
			}else
			{
				APP_LOGS.info("check6: "+check6);
				APP_LOGS.info("network_type_value_text Element is not present");			
			}
			pressDownKey();
			minWait_SonimCare();
			if(isElementExist(Locators_ScoutSanity.signal_strength_value_text))
			{
				check7 = true;
				APP_LOGS.info("check7: " + check7);
				APP_LOGS.info("signal_strength_value_text Element is present");
				String getAlertText = Locators_ScoutSanity.signal_strength_value_text.getText();
				APP_LOGS.info("signal_strength_value_text: " + getAlertText);
			}else
			{
				APP_LOGS.info("check7: "+check7);
				APP_LOGS.info("signal_strength_value_text Element is not present");			
			}
			pressDownKey();
			minWait_SonimCare();
			if(isElementExist(Locators_ScoutSanity.on_roaming_value_text))
			{
				check8 = true;
				APP_LOGS.info("check8: " + check8);
				APP_LOGS.info("on_roaming_value_text Element is present");
				String getAlertText = Locators_ScoutSanity.on_roaming_value_text.getText();
				APP_LOGS.info("on_roaming_value_text: " + getAlertText);
			}else
			{
				APP_LOGS.info("check8: "+check8);
				APP_LOGS.info("on_roaming_value_text Element is not present");			
			}
			pressDownKey();
			minWait_SonimCare();
			if(isElementExist(Locators_ScoutSanity.data_value_text))
			{
				check9 = true;
				APP_LOGS.info("check9: " + check9);
				APP_LOGS.info("data_value_text Element is present");
				String getAlertText = Locators_ScoutSanity.data_value_text.getText();
				APP_LOGS.info("data_value_text: " + getAlertText);
			}else
			{
				APP_LOGS.info("check9: "+check9);
				APP_LOGS.info("data_value_text Element is not present");			
			}
			pressDownKey();
			minWait_SonimCare();
			if(isElementExist(Locators_ScoutSanity.network_value_text))
			{
				check10 = true;
				APP_LOGS.info("check10: " + check10);
				APP_LOGS.info("network_value_text Element is present");
				String getAlertText = Locators_ScoutSanity.network_value_text.getText();
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

	public void pressBackKey(){
		aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
	}

	public void minWait_SonimCare() throws InterruptedException{
		Thread.sleep(2500);
	}

	public void pressDownKey(){
		aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
	}

	public void navigateTo_TestAll_screen() throws InterruptedException{
		/*
		 * Navigates to Test all screen of Diagnosis sub application.
		 */		
		try {
			minWait();
			clickBtn(Locators_ScoutSanity.continueBtn_welcomeScreen_subApp);
			customWait(3000);
			clickBtn(Locators_ScoutSanity.continueBtn_selfDiagnosis);
			minWait_SonimCare();
		} catch (NoSuchElementException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Assert.fail();
		}
	}


	public void navigateTo_Individual_feature(String featureName) throws InterruptedException{
		/*
		 * Navigates to any feature in Test all screen.
		 * Pass name of the feature as parameter
		 */

		minWait_SonimCare();		
		if(featureName.equalsIgnoreCase("WI-FI"))
		{
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
			minWait_SonimCare();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
			minWait_SonimCare();
		}
		else if(featureName.equalsIgnoreCase("Test all"))
		{
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
		}
		else if(featureName.equalsIgnoreCase("BLUETOOTH"))
		{
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
			minWait_SonimCare();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_RIGHT);
			minWait_SonimCare();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
			minWait_SonimCare();
		}
		else if(featureName.equalsIgnoreCase("BACK CAMERA"))
		{
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
			minWait_SonimCare();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
			minWait_SonimCare();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
			minWait_SonimCare();	
		}
		else if(featureName.equalsIgnoreCase("FLASHLIGHT"))
		{
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
			minWait_SonimCare();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
			minWait_SonimCare();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_RIGHT);
			minWait_SonimCare();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
			minWait_SonimCare();

		}
		else if(featureName.equalsIgnoreCase("DISPLAY"))
		{
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
			minWait_SonimCare();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
			minWait_SonimCare();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
			minWait_SonimCare();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
			minWait_SonimCare();	
		}
		else if(featureName.equalsIgnoreCase("Keypad"))
		{
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
			minWait_SonimCare();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
			minWait_SonimCare();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
			minWait_SonimCare();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_RIGHT);
			minWait_SonimCare();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
			minWait_SonimCare();
		}
		else if(featureName.equalsIgnoreCase("BATTERY"))
		{
			for(int i=0;i<4;i++){
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
				minWait_SonimCare();
			}
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
			minWait_SonimCare();

		}
		else if(featureName.equalsIgnoreCase("VIBRATION"))
		{
			for(int i=0;i<4;i++){
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
				minWait_SonimCare();
			}
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_RIGHT);
			minWait_SonimCare();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
			minWait_SonimCare();
		}
		else if(featureName.equalsIgnoreCase("EARPIECE"))
		{
			for(int i=0;i<5;i++){
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
				minWait_SonimCare();
			}
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
			minWait_SonimCare();

		}
		else if(featureName.equalsIgnoreCase("HEADSET"))
		{
			for(int i=0;i<5;i++){
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
				minWait_SonimCare();
			}
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_RIGHT);
			minWait_SonimCare();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
			minWait_SonimCare();
		}
		else if(featureName.equalsIgnoreCase("SPEAKER"))
		{
			for(int i=0;i<6;i++){
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
				minWait_SonimCare();
			}
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
			minWait_SonimCare();
		}
		else if(featureName.equalsIgnoreCase("ACCELERATOR SENSOR"))
		{
			for(int i=0;i<6;i++){
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
				minWait_SonimCare();
			}
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_RIGHT);
			minWait_SonimCare();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
			minWait_SonimCare();

		}
		else if(featureName.equalsIgnoreCase("GPS"))
		{
			for(int i=0;i<7;i++){
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
				minWait_SonimCare();
			}
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
			minWait_SonimCare();
		}
		else if(featureName.equalsIgnoreCase("NOTIFICATION LED"))
		{
			for(int i=0;i<7;i++){
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
				minWait_SonimCare();
			}
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_RIGHT);
			minWait_SonimCare();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
			minWait_SonimCare();

		}
		else if(featureName.equalsIgnoreCase("CLEAR CACHE MEMORY"))
		{
			for(int i=0;i<8;i++){
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
				minWait_SonimCare();
			}
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_RIGHT);
			minWait_SonimCare();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
			minWait_SonimCare();
		}
		else if(featureName.equalsIgnoreCase("STORAGE INFO"))
		{
			for(int i=0;i<8;i++){
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
				minWait_SonimCare();
			}
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
			minWait_SonimCare();

		}		
	}
	
	public void navigateToIndividualFeature(String feature) {
		if (feature.equalsIgnoreCase("TEST ALL"))
			clickBtnWithText(feature);
		else
			scrollText(feature);
			clickBtnWithText(feature);
	}
	
	public void click_On_coninueBtn_featureScreen() throws InterruptedException{
		/*
		 * Clicks on continue button displayed in each feature screen.
		 */
		try {
			minWait_SonimCare();
			clickBtn(Locators_ScoutSanity.continueBtnFeatureScreen);
		} catch (NoSuchElementException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Assert.fail();
		}					
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
			clickBtn(Locators_ScoutSanity.scanForMoreNetworksBtn);
			customWait(6000);
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
			minWait_SonimCare();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_LEFT);
			minWait_SonimCare();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
			minWait_SonimCare();		
			if(isElementExist(Locators_ScoutSanity.selfDiagnosisTitle)){
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
			if(isElementExist(Locators_ScoutSanity.No_btn)) {
				clickBtn(Locators_ScoutSanity.No_btn);
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
	


	public void validate_backCamera(String textToVerify) throws InterruptedException{
		/*
		 * takes a picture and saves.
		 * Validates picture taken.
		 */
		SoftAssert SA3 = new SoftAssert();
		try {
			customWait(4000);
			clickBtn(Locators_ScoutSanity.cameraIcon);
			minWait_SonimCare();
			clickBtn(Locators_ScoutSanity.OK_btn_resultConfirmation_dialog);
			customWait(4000);
			if(isElementExist(Locators_ScoutSanity.cameraCOnfirmation_text)){
				check=true;
				APP_LOGS.info("Test result confirmation message is displayed succesfully.");
				SA3.assertTrue(check, " ");
			}else{
				APP_LOGS.info("Test result confirmation message is not displayed.");
				SA3.fail();
			}
			minWait();
			clickBtn(Locators_ScoutSanity.cameraPass_Confirmation_dialog);
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
			String get_bat_status = Locators_ScoutSanity.batteryStatusLabel.getText();
			if(get_bat_status.equalsIgnoreCase(bat_Status)){
				check1=true;
				APP_LOGS.info("Label:"+get_bat_status);
			}
			String get_bat_value = Locators_ScoutSanity.batteryStatusValue.getText();
			if(get_bat_value.equalsIgnoreCase(bat_status_value_1)||get_bat_value.equalsIgnoreCase(bat_status_value_2)){
				check2=true;
				APP_LOGS.info("Value:"+get_bat_value);
			}		
			String get_bat_Electricity = Locators_ScoutSanity.batteryElectricityLabel.getText();
			if(get_bat_Electricity.equalsIgnoreCase(bat_Electricity)){
				check3=true;
				APP_LOGS.info("Label:"+get_bat_Electricity);
			}
			String get_bat_Electricity_value = Locators_ScoutSanity.batteryElectricityValue.getText();
			if(get_bat_Electricity_value.equalsIgnoreCase(bat_Electricity_value)){
				check4=true;
				APP_LOGS.info("Value:"+get_bat_Electricity_value);
			}
			if((check1)&&(check2)&&(check3)&&(check4)){
				check= true;
				APP_LOGS.info("Static parameters are displayed properly.");
				APP_LOGS.info("TC is Passed.");
				clickBtn(Locators_ScoutSanity.YES_btn_wifiScanResult);
				//selectYESButton();
				 SS1.assertTrue(check, " ");
			}else{
				APP_LOGS.info(get_bat_status+": "+check1+"\n"+get_bat_value+": "+check2+"\n"
						+get_bat_Electricity+": "+check3+"\n"+get_bat_Electricity_value+": "+check4);
				clickBtn(Locators_ScoutSanity.YES_btn_wifiScanResult);
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
			for(int i=0;i<7;i++){
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
				minWait_SonimCare();
			}
			//Locators_ScoutSanity.scrollToCacheMemory();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
			minWait_SonimCare();
			clickBtn(Locators_ScoutSanity.OK_btn_resultConfirmation_dialog);
			minWait_SonimCare();
			customWait(4000);
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
			minWait_SonimCare();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
			minWait_SonimCare();		
			String cachedDataValue = Locators_ScoutSanity.cachedMemory_0.getText();
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
		customWait(6000);	
		try {
			customWait(6000);
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
			minWait_SonimCare();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_LEFT);
			minWait_SonimCare();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
			minWait_SonimCare();		
			if(isElementExist(Locators_ScoutSanity.selfDiagnosisTitle)){
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
			minWait_SonimCare();
			if(isElementExist(Locators_ScoutSanity.wifi_State_link))
			{
				check1 = true;
				APP_LOGS.info("check1: "+check1);
				APP_LOGS.info("wifi_State_link Element is present");
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
				minWait_SonimCare();			
				if(isElementExist(Locators_ScoutSanity.alert))
				{
					check1_a =true;
					APP_LOGS.info("check1_a: "+check1_a);
					String getAlertText=Locators_ScoutSanity.alert.getText();
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
			minWait_SonimCare();			
			if(isElementExist(Locators_ScoutSanity.alert))
			{
				check2_a =true;
				APP_LOGS.info("check2_a: "+check2_a);
				String getAlertText=Locators_ScoutSanity.alert.getText();
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
			minWait_SonimCare();			
			if(isElementExist(Locators_ScoutSanity.alert))
			{
				check3_a =true;
				APP_LOGS.info("check3_a: "+check3_a);
				String getAlertText=Locators_ScoutSanity.alert.getText();
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
			minWait_SonimCare();			

			if(isElementExist(Locators_ScoutSanity.alert))
			{
				check4_a =true;
				APP_LOGS.info("check4_a: "+check4_a);
				String getAlertText=Locators_ScoutSanity.alert.getText();
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
			minWait_SonimCare();			

			if(isElementExist(Locators_ScoutSanity.alert))
			{
				check5_a =true;
				APP_LOGS.info("check5_a: "+check5_a);
				String getAlertText=Locators_ScoutSanity.alert.getText();
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
			minWait_SonimCare();			
			if(isElementExist(Locators_ScoutSanity.alert))
			{
				check6_a =true;
				APP_LOGS.info("check6_a: "+check6_a);
				String getAlertText=Locators_ScoutSanity.alert.getText();
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
			minWait_SonimCare();			
			if(isElementExist(Locators_ScoutSanity.alert))
			{
				check7_a =true;
				APP_LOGS.info("check7_a: "+check7_a);
				String getAlertText=Locators_ScoutSanity.alert.getText();
				APP_LOGS.info("Alert heading is: "+getAlertText);
				pressBackKey();
				minWait_SonimCare();
			}else{
				APP_LOGS.info("check7_a: "+check7_a);
				APP_LOGS.info("ip_address_link Alert is not present");
			}		
			pressDownKey();
			minWait_SonimCare();			
			minWait_SonimCare();				
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
			minWait_SonimCare();			
			if(isElementExist(Locators_ScoutSanity.alert))
			{
				check9_a =true;
				APP_LOGS.info("check9_a: "+check9_a);
				String getAlertText=Locators_ScoutSanity.alert.getText();
				APP_LOGS.info("Alert heading is: "+getAlertText);
				pressBackKey();
				minWait_SonimCare();
			}else{
				APP_LOGS.info("check9_a: "+check9_a);
				APP_LOGS.info("link_speed_link Alert is not present");
			}
			if((check1_a)&&(check2_a)&&(check3_a)&&(check4_a)&&(check5_a)&&(check6_a)&&(check7_a)
					&&(check9_a)){
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
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			SS3.fail();
		}		
		SS3.assertAll();
		minWait();
		aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_ENTER);
		customWait(6000);
		aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
		minWait_SonimCare();
		aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_LEFT);
		minWait_SonimCare();
		aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
		minWait_SonimCare();
		aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
		minWait();
		back();
	}


	public void validate_battery_links(String featureName) throws InterruptedException{
		/*
		 * Validates hyperlinks and alerts of battery feature screen.
		 */
		SoftAssert SB3 = new SoftAssert();
		try {
			minWait_SonimCare();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
			minWait_SonimCare();			
			if(isElementExist(Locators_ScoutSanity.alert))
			{
				check1_a =true;
				APP_LOGS.info("check1_a: "+check1_a);
				String getAlertText=Locators_ScoutSanity.alert.getText();
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
			if(isElementExist(Locators_ScoutSanity.alert))
			{
				check2_a =true;
				APP_LOGS.info("check2_a: "+check2_a);
				String getAlertText=Locators_ScoutSanity.alert.getText();
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
			if(isElementExist(Locators_ScoutSanity.alert))
			{
				check3_a =true;
				APP_LOGS.info("check3_a: "+check3_a);
				String getAlertText=Locators_ScoutSanity.alert.getText();
				APP_LOGS.info("Alert heading is: "+getAlertText);
				pressBackKey();
				minWait_SonimCare();
			}else{
				APP_LOGS.info("check3_a: "+check3_a);
				APP_LOGS.info("battery_status_link Alert is not present");
			}
			pressDownKey();
			minWait_SonimCare();
			pressDownKey();
			minWait_SonimCare();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
			minWait_SonimCare();			
			if(isElementExist(Locators_ScoutSanity.alert))
			{
				check5_a =true;
				APP_LOGS.info("check5_a: "+check5_a);
				String getAlertText=Locators_ScoutSanity.alert.getText();
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
			if(isElementExist(Locators_ScoutSanity.alert))
			{
				check6_a =true;
				APP_LOGS.info("check6_a: "+check6_a);
				String getAlertText=Locators_ScoutSanity.alert.getText();
				APP_LOGS.info("Alert heading is: "+getAlertText);
				pressBackKey();
				minWait_SonimCare();
			}else{
				APP_LOGS.info("check6_a: "+check6_a);
				APP_LOGS.info("battery_voltage_link Alert is not present");
			}
			pressDownKey();
			minWait_SonimCare();
			minWait_SonimCare();	
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
			if(isElementExist(Locators_ScoutSanity.alert))
			{
				check7_a =true;
				APP_LOGS.info("check7_a: "+check7_a);
				String getAlertText=Locators_ScoutSanity.alert.getText();
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
				selectNOButton(featureName);
				SB3.assertTrue(check, " ");
			}else{
				APP_LOGS.info("Test case failed");			
				selectNOButton(featureName);			
				SB3.fail();
			}
		} catch (NoSuchElementException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			SB3.fail();
		}	
		SB3.assertAll();
		aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
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
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
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
			if(isElementExist(Locators_ScoutSanity.alert)) {
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
			}	
		}
	}

	public void validate_hyperTexts_alert_selfDiagnosisPage() throws InterruptedException{
		/*
		 * Validates hyperlinks and alerts of self Diagnosis screen.
		 */
         SoftAssert Sv1 = new SoftAssert();
		try {
			minWait_SonimCare();
			if(isElementExist(Locators_ScoutSanity.model_link))
			{
				check1 = true;
				APP_LOGS.info("check1: "+check1);
				APP_LOGS.info("model_link Element is present");
				clickBtn(Locators_ScoutSanity.model_link);
				minWait_SonimCare();			
				if(isElementExist(Locators_ScoutSanity.alert))
				{
					check1_a =true;
					APP_LOGS.info("check1_a: "+check1_a);
					String getAlertText=Locators_ScoutSanity.alert.getText();
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

			if(isElementExist(Locators_ScoutSanity.alert))
			{
				check2 = true;
				check2_a =true;
				APP_LOGS.info("check2_a: "+check2_a);
				String getAlertText=Locators_ScoutSanity.alert.getText();
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
			if(isElementExist(Locators_ScoutSanity.alert))
			{
				check3 = true;
				check3_a =true;
				APP_LOGS.info("check3_a: "+check3_a);
				String getAlertText=Locators_ScoutSanity.alert.getText();
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
			if(isElementExist(Locators_ScoutSanity.alert))
			{
				check4 = true;
				check4_a =true;
				APP_LOGS.info("check4_a: "+check4_a);
				String getAlertText=Locators_ScoutSanity.alert.getText();
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
			if(isElementExist(Locators_ScoutSanity.alert))
			{
				check5 = true;
				check5_a =true;
				APP_LOGS.info("check5_a: "+check5_a);
				String getAlertText=Locators_ScoutSanity.alert.getText();
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
			if(isElementExist(Locators_ScoutSanity.alert))
			{
				check6 = true;
				check6_a =true;
				APP_LOGS.info("check6_a: "+check6_a);
				String getAlertText=Locators_ScoutSanity.alert.getText();
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
			if(isElementExist(Locators_ScoutSanity.alert))
			{
				check7 = true;
				check7_a =true;
				APP_LOGS.info("check7_a: "+check7_a);
				String getAlertText=Locators_ScoutSanity.alert.getText();
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
			if(isElementExist(Locators_ScoutSanity.alert))
			{
				check8 = true;
				check8_a =true;
				APP_LOGS.info("check8_a: "+check8_a);
				String getAlertText=Locators_ScoutSanity.alert.getText();
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
			if(isElementExist(Locators_ScoutSanity.alert))
			{
				check9 = true;
				check9_a =true;
				APP_LOGS.info("check9_a: "+check9_a);
				String getAlertText=Locators_ScoutSanity.alert.getText();
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
			if(isElementExist(Locators_ScoutSanity.alert))
			{
				check10 = true;
				check10_a =true;
				APP_LOGS.info("check10_a: "+check10_a);
				String getAlertText=Locators_ScoutSanity.alert.getText();
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
	}

	public void validate_No_btn_feature(String featureName) throws InterruptedException{
		SoftAssert Sv = new SoftAssert();
		try {
			customWait(5000);
			selectNOButton(featureName);

			if(isElementExist(Locators_ScoutSanity.alert)){
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
			clickBtn(Locators_ScoutSanity.continueBtn_Bat_scan);
		} catch (NoSuchElementException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Assert.fail();
		}			
	}


	public void validate_battery_service_history() throws InterruptedException{
		/*
		 * validates battery service history.
		 * Records static and dynamic values.
		 */
		SoftAssert Sv = new SoftAssert();
		try {
			customWait(4000);
			clickBtn(Locators_ScoutSanity.battery_service_History);
			minWait_SonimCare();		
			String getBat_Year_month_Info = Locators_ScoutSanity.battery_Month_Year_view.getText();
			if(isElementExist(Locators_ScoutSanity.battery_Month_Year_view)){
				check1 = true;
				APP_LOGS.info("Element found: "+getBat_Year_month_Info);
			}else{
				APP_LOGS.info("Bat year month info Element not found");
			}
			String get_total_no_charges = Locators_ScoutSanity.battery_Total_No_Charges.getText();
			if(isElementExist(Locators_ScoutSanity.battery_Total_No_Charges)){
				check2 = true;
				APP_LOGS.info("Element found: "+get_total_no_charges);
			}else{
				APP_LOGS.info("Bat total number of charges element is not found");
			}
			String get_total_no_charges_value= Locators_ScoutSanity.battery_Total_No_Charges_value.getText();
			if(isElementExist(Locators_ScoutSanity.battery_Total_No_Charges_value)){
				check3 = true;
				APP_LOGS.info("Element found: "+get_total_no_charges_value);			
			}else{
				APP_LOGS.info("Bat total no of charges value element is not found");
			}
			String get_max_temp_recorded = Locators_ScoutSanity.battery_Max_temp_recorded.getText();
			if(isElementExist(Locators_ScoutSanity.battery_Max_temp_recorded)){
				check4= true;
				APP_LOGS.info("Element found: "+get_max_temp_recorded);
			}else{
				APP_LOGS.info("Max battery temperature element is not found");
			}
			String get_max_temp_recorded_value = Locators_ScoutSanity.battery_Max_temp_recorded_value.getText();
			if(isElementExist(Locators_ScoutSanity.battery_Max_temp_recorded_value)){
				check5 = true;
				APP_LOGS.info("element is found"+get_max_temp_recorded_value);
			}else{
				APP_LOGS.info("bat_max_temp element is not found");
			}
			String getTotalChargeDuration = Locators_ScoutSanity.battery_total_charge_duration.getText();
			if(isElementExist(Locators_ScoutSanity.battery_total_charge_duration)){
				check6 = true;
				APP_LOGS.info("Element is found: "+ getTotalChargeDuration);
			}else{
				APP_LOGS.info("TotalCharge Duration element is not found");
			}
			String getTotalChargeDuration_Value = Locators_ScoutSanity.battery_total_charge_duration_value.getText();
			if(isElementExist(Locators_ScoutSanity.battery_total_charge_duration_value)){
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


	public void validate_batteryDiagnosis() throws InterruptedException{
		/*
		 * Validates battery diagnosis sub application screen.
		 * Validates against all static headings of the screen.
		 */
		SoftAssert Sv = new SoftAssert();
		try {
			customWait(4000);
			String getBatInfo = Locators_ScoutSanity.battery_Info.getText();
			if(isElementExist(Locators_ScoutSanity.battery_Info)){
				check1 = true;
				APP_LOGS.info("Element found: "+getBatInfo);
			}else{
				APP_LOGS.info("Bat info Element not found");
			}
			String getBatHealth = Locators_ScoutSanity.battery_health.getText();
			if(isElementExist(Locators_ScoutSanity.battery_health)){
				check2 = true;
				APP_LOGS.info("Element found: "+getBatHealth);
			}else{
				APP_LOGS.info("Bat health element is not found");
			}
			String getBatVoltage= Locators_ScoutSanity.battery_voltage.getText();
			if(isElementExist(Locators_ScoutSanity.battery_voltage)){
				check3 = true;
				APP_LOGS.info("Element found: "+getBatVoltage);
			}else{
				APP_LOGS.info("Bat voltage element is not found");
			}
			String getBatTemp = Locators_ScoutSanity.battery_temperature.getText();
			if(isElementExist(Locators_ScoutSanity.battery_temperature)){
				check4= true;
				APP_LOGS.info("Element found: "+getBatTemp);
			}else{
				APP_LOGS.info("Bat temperature element is not found");
			}
			if(isElementExist(Locators_ScoutSanity.battery_history_Layout)){
				check5 = true;
				APP_LOGS.info("Battery history layout element is found");
			}else{
				APP_LOGS.info("Battery history layout element is not found");
			}
			String getBatHistory = Locators_ScoutSanity.battery_service_History.getText();
			if(isElementExist(Locators_ScoutSanity.battery_service_History)){
				check6 = true;
				APP_LOGS.info("Battery service history element is found: "+ getBatHistory);
			}else{
				APP_LOGS.info("Battery service hostory element is not found");
			}
			String getBatReport = Locators_ScoutSanity.battery_view_detailedReport.getText();
			if(isElementExist(Locators_ScoutSanity.battery_view_detailedReport)){
				check7=true;
				APP_LOGS.info("Battery detailed report element is found: "+getBatReport);
			}else{
				APP_LOGS.info("Battery detailed report element is not found");
			}
			String getBatDiagnosisTitle = Locators_ScoutSanity.battery_diagnosis_title.getText();
			if(isElementExist(Locators_ScoutSanity.battery_diagnosis_title)){
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

	public void click_checkForUpdateBtn() throws InterruptedException, IOException{
		/*
		 * check for updates
		 */
		/*aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_7);
		aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_0);
		aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_6);
		aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_1);
		Runtime.getRuntime().exec("adb shell input keyevent --longpress KEYCODE_6");
		*/
		try {
			clickBtn(Locators_ScoutSanity.checkForUpdateBtn);		
			APP_LOGS.info("clicked for Updates");
			customWait(6000);
			if(isElementExistWithText("All applications are up to date.")) {
				clickBtnWithText("OK");
				APP_LOGS.error("No applications available for update");
				Assert.fail();
			}
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			Assert.fail();
		}		
	}


	public void installApp() throws InterruptedException, IOException{
		// Acknowledges the confirmation and installs.
		// Later and Ok button should be displayed in confirmation dialog.
		TouchAction t = new TouchAction(aDriver);
		try {
			
			customWait(6000);
			waituntilnew(Locators_ScoutSanity.appUpdaterAlert, 1000);
			for(int i=0;i<10;i++){
				if(isElementExist(Locators_ScoutSanity.appUpdaterAlert)){
					APP_LOGS.info("Confirmation alert is displayed succesfully.");
					aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_RIGHT);
					minWait();
					aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
					minWait();
					break;
				}else{
					waitUntilElementclickable(Locators_ScoutSanity.appUpdaterAlert);
					minWait();
					i++;
				}
			}
		} catch (NoSuchElementException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Assert.fail();
		}						
	}

	public void appUpdaterSuccessAlert() throws InterruptedException{
		// Wait until it success alert is displayed.

		try {
			
			customWait(5000);
//			waituntilnew(Locators_ScoutSanity.appUpdaterAlert, 100);
			for(int i=0;i<5;i++){
				if(isElementExist(Locators_ScoutSanity.appUpdaterAlert)){
					APP_LOGS.info("Success alert is displayed succesfully.");
					customWait(2000);
//					aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_RIGHT);
					customWait(1000);
					aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
					customWait(10000);
					aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
					/*customWait(2000);
					customWait(12000);
					aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
					customWait(2000);*/
					break;
				}else{
					waitUntilElementclickable(Locators_ScoutSanity.appUpdaterAlert);
					minWait();
					i++;
				}
			}
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			Assert.fail();
		}	
	}

	public void validate_succesfull_appInstallation(String app) throws InterruptedException{
		/*
		 * Validate Application installed in Apps in Native settings
		 */
		aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_HOME);
		customWait(1000);
		try {
			clickBtn(Locators_ScoutSanity.settingsIcon);
			minWait();
			APP_LOGS.info("Launched Settings icon sucessfully");          
			for (int iter = 1; iter <= 25; iter++) {
				if (isElementExist(Locators_ScoutSanity.nativeSettings_apps)){
					APP_LOGS.info("Apps displayed sucessfully");
					clickBtn(Locators_ScoutSanity.nativeSettings_apps);
					minWait();	
					enterTextToInputField(Locators_ScoutSanity.apps_homescreen, app);
					minWait();
					aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
					minWait();
					if(isElementExist(Locators_ScoutSanity.appInfo_appTitle))
					{
						check=true;
						APP_LOGS.info("App info page of selected app is displayed.");
						Assert.assertTrue(check);
						break;
					}else{
						APP_LOGS.info("App info page of selected app is not displayed.");
						Assert.fail();
						break;
					}
				} else {
					//APP_LOGS.info("Searching for programmable key: " + iter);
					aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
					continue;					
				}					
			}

		} catch (NoSuchElementException e) {
			e.printStackTrace();
			Assert.fail();
		}
	}

	public void uninstallAppFromSettings(String app) throws InterruptedException{
		/*
		 * Navigates to settings >> Apps >> Enters the string >> Uninstalls
		 */
		customWait(1000);
		aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_HOME);
		customWait(500);
		try {
			clickBtn(Locators_ScoutSanity.settingsIcon);
			minWait();
			APP_LOGS.info("Launched Settings icon sucessfully");          
			for (int iter = 1; iter <= 25; iter++) {
				if (isElementExist(Locators_ScoutSanity.nativeSettings_apps)){
					APP_LOGS.info("Apps displayed sucessfully");
					clickBtn(Locators_ScoutSanity.nativeSettings_apps);
					minWait();	
					enterTextToInputField(Locators_ScoutSanity.apps_homescreen, app);
					minWait();
					aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
					minWait();
					aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_MENU);
					customWait(500);
					aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
					minWait();
					aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_RIGHT);
					minWait();					
					aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
					customWait(4000);
					customWait(4000);
					APP_LOGS.info("Application is uninstalled succesfully.");
					break;					
				} else {
					//APP_LOGS.info("Searching for programmable key: " + iter);
					aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
					continue;					
				}
			}

		} catch (NoSuchElementException e) {
			e.printStackTrace();
			Assert.fail();
		}
	
	}
	
	public void navigateToSafeguard() throws InterruptedException, IOException {
		
		try {
			if (isElementExist(Locators_ScoutSanity.SG_Pin)) {
				enterPin();
			}
			
			minWait();
			
			navigateUsingText("SafeGuard");
			
			
			
		} catch (NoSuchElementException e) {
			
			minWait();
			navigateUsingText("SafeGuard");
		}		 
	}		
	
	public void navigateToDiagnosisScreen() {
			while (isElementExistWithText("Continue")) {
				navigateUsingText("Continue");
				APP_LOGS.info("Clicked on continue successfully");
			}
	}
	
}
