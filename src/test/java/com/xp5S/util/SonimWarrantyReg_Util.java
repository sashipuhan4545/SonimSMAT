package com.xp5S.util;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.text.DefaultEditorKit.CutAction;

import org.apache.commons.collections.map.StaticBucketMap;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.interactions.Actions;
import org.testng.asserts.SoftAssert;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.sun.jna.platform.win32.OaIdl.CURRENCY;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.AndroidKeyCode;

public class SonimWarrantyReg_Util extends BaseUtil{
	
	 public boolean check = false;

	 public void presence_Of_SonimWarrantyReg_In_ScoutApp() throws InterruptedException, IOException {
		
		APP_LOGS.info("************************** Starts ******************************"); 
	
		SoftAssert SA1=new SoftAssert();
		navigateToWarrantyRegApp();
		customWait(1000);
		try {
			clickBtn(Locators_WarrantyReg.SWR_Support_Tab);				
			APP_LOGS.info("Tapped on Support tab successfully");
			customWait(1000);	
			String fetch_sf_text=Locators_WarrantyReg.SWR_String_Name.getText();
			APP_LOGS.info("Fetching safeguard string text is" +fetch_sf_text);
			SA1.assertEquals(fetch_sf_text, "Warranty Registration");						
		}
		catch(NoSuchElementException ex){
			ex.printStackTrace();				
			APP_LOGS.info("Element is not present in the page");
			SA1.fail();
		}
		SA1.assertAll();
	 }
	 
	 public void launchScout() throws InterruptedException {
		 APP_LOGS.info("*****************************Common Method to launch Sonim Warranty Reg******************************");  
		 aDriver.startActivity("com.sonim.scout","com.sonim.scout.activities.MainActivity");
		 customWait(2000);
	 }
	 
	 public void navigateToWarrantyRegApp() throws InterruptedException, IOException {
		 
		 launchScout();
		 clickBtn(Locators_WarrantyReg.SWR_Support_Tab);
		 customWait(1000);
		 clickBtn(Locators_WarrantyReg.SWR_String_Name);
		 customWait(1000);
		 
		 try {
			clickBtn(Locators_WarrantyReg.SWR_OK_Btn);
		 } catch (Exception e) {
			e.printStackTrace();
		 }
		 /*
		 if(isElementExist(Locators_Warranty_Reg.SWR_Contacting_server_progress_dialog)) {
			 APP_LOGS.info("LaunchWarrantyRegApp: Progress dialog displayed on FG, So Waiting for 5 seconds");
			 customWait(6000);
			 
				if(isElementExist(Locators_Warranty_Reg.SWR_UnableTo_Fetch_RegistrationInforamtionFromServer)) {
					 APP_LOGS.info("Unable to fetch registration information from server present on FG");
					 clickBtn(Locators_Warranty_Reg.SWR_OK_Btn);					  
				}				 
				else if(isElementExist(Locators_Warranty_Reg.SWR_UnableTo_Fetch_RegistrationInforamtionFromServer)) {
				     APP_LOGS.info("Unable to fetch registration information from server present on FG");
				     clickBtn(Locators_Warranty_Reg.SWR_OK_Btn);
				     System.out.println("Execution has stopped as,Device is unable to fetch registration in information from server");
				     aDriver.quit();
				}
				else if(isElementExist(Locators_Warranty_Reg.SWR_Please_contact_Sonim_At)) {
				     customWait(1000);
				     Runtime.getRuntime().exec("adb shell pm clear com.sonimtech.warrantyregapp");
				     customWait(1000);
				     aDriver.startActivity("com.sonim.scout", "com.sonim.scout.activities.MainActivity");	 
				}
				else if(isElementExist(Locators_Warranty_Reg.SWR_No_internet_Connection)) {
				   	clickBtn(Locators_Warranty_Reg.SWR_OK_Btn);
				   	APP_LOGS.info("************No internet connection window display ***************");
				   	System.out.println("Execution has stopped as ,there is No internet connection available in the device!! ");
				   	aDriver.quit();			  
				}
		 }
		 else {
			 APP_LOGS.info("Device is already Registered.");
		 }*/
	 }

	 public String isDeviceRegistered() throws InterruptedException, IOException {
		 
		 APP_LOGS.info("******************************start******************************"); 
		 String status = null;
		 navigateToWarrantyRegApp();
		 
		 try {
			if(isElementExist(Locators_WarrantyReg.SWR_String_Register_Your_Device)) {
				APP_LOGS.info("isDeviceRegistered:Need to Registered the device");
				status=Locators_WarrantyReg.SWR_String_Register_Your_Device.getText();	
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
	 
 
	 public void presence_Of_Register_Option() throws InterruptedException, IOException {
		 
		 APP_LOGS.info("********** Start ****************");		 
		
		 SoftAssert SA2=new SoftAssert();
		 try {
			 if(isDeviceRegistered().equals("Register your device")) {
				 check=true;
				 SA2.assertTrue(check, "Register");
				 APP_LOGS.info("presence_Of_Register_Option:Register option is present in the device");
			 }
			 else {
				 APP_LOGS.info("presence_Of_Register_Option:Register option is not present in the device");
				 SA2.fail();
				 }
			 }
			 catch (NoSuchElementException e) {
				e.printStackTrace();
				APP_LOGS.info("presence_Of_Register_Option:No Such Element exist");
				SA2.fail();
			 }
		 APP_LOGS.info("**********End*******************************");
		 SA2.assertAll();
	}
	 
	//This Method is used to give the current date
	 public static void getCurrentDate() {
		 APP_LOGS.info("*********Gives the current Date************");
		 DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			Calendar cal = Calendar.getInstance();
			String date=(dateFormat.format(cal.getTime()));
			String[] date1=date.split("/");
			
			
	 }
	 
	 public void skip_AllFields_Press_SubmitBtn() throws InterruptedException, IOException {
		 
		 APP_LOGS.info("*******************Start*******************************");
		 	 
		 customWait(1000);		 
		 SoftAssert SA3=new SoftAssert();	
		   
			 if(isDeviceRegistered().equals("Register your device")) {
				 try {
					 customWait(1000);
					 clickBtn(Locators_WarrantyReg.SWR_Now);
					 customWait(1000);
					 customWait(2000);
					 //Dont enter any value in the test field and press on "submit" button and check
						 if(isElementExist(Locators_WarrantyReg.SWR_Submit)) {
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
				 catch (NoSuchElementException|NullPointerException e) {
					 e.printStackTrace();					 
					 APP_LOGS.info("Without entering Any value press submit button:No such Element found execption");
					 SA3.fail();
				 }				
			 }
			 else {
				    APP_LOGS.info("********************Device is Already Registered********************");
					System.out.println("*******Device is Already Registered,Need to Delete the device from server to run the Script*******");
					SA3.fail();
					aDriver.quit();	 
			 }
			 SA3.assertAll();
	 }
	 
	 public void enter_Country_Name_Press_SubmitBtn() throws InterruptedException, IOException {
		 
		 SoftAssert SA4=new SoftAssert();
		 		 
		 if(isDeviceRegistered().equals("Register your device")) {		 
			 try {
				 customWait(1000);
				 clickBtn(Locators_WarrantyReg.SWR_Now);
				 customWait(1000);
			     aDriver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().className(\"android.widget.ScrollView\")).scrollIntoView(new UiSelector().text(\"Enter Country\"))");  
			     customWait(1000);
				 enterTextToInputField(Locators_WarrantyReg.SWR_Country, "India");
				 customWait(1000);
				 clickBtn(Locators_WarrantyReg.SWR_Submit);
				 customWait(2000);
				 if(isElementExist(Locators_WarrantyReg.SWR_please_enter_first_Name)) {
					 check=true;
					 clickBtn(Locators_WarrantyReg.SWR_OK_Btn);
					 SA4.assertTrue(check, "Pass");
				 }
				 else {
					 SA4.fail();
				 }				 
			 }
			 catch(NoSuchElementException|NullPointerException e) {
				 e.printStackTrace();
				 SA4.fail();
			 }			 			 
		 }	 
		 else {
		    APP_LOGS.info("********************Device is Already Registered********************");
			System.out.println("*******Device is Already Registered,Need to Delete the device from server to run the Script*******");
			SA4.fail();
			aDriver.quit();		 
	 	}	
		SA4.assertAll();
	}
	 
	public void enter_Fname_Press_SubmitBtn() throws InterruptedException, IOException {
	
		SoftAssert SA5=new SoftAssert();
		
		 if(isDeviceRegistered().equals("Register your device")) {	 
			 clickBtn(Locators_WarrantyReg.SWR_Now);
			 enterTextToInputField(Locators_WarrantyReg.SWR_Enter_First_Name, "sonim");
			 customWait(1000);
			 clickBtn(Locators_WarrantyReg.SWR_Submit);
			 if(isElementExist(Locators_WarrantyReg.SWR_Please_Enter_Last_Name)) {
				 APP_LOGS.info("Please enter the Last name dialog dsiplayed ");
				 check=true;
				 clickBtn(Locators_WarrantyReg.SWR_OK_Btn);
				 SA5.assertTrue(check, "Pass ");
			 }
			 else {
				 SA5.fail();
			 }
		 }
		 else {		 
			APP_LOGS.info("********************Device is Already Registered********************");
			System.out.println("*******Device is Already Registered,Need to Delete the device from server to run the Script*******");
			SA5.fail();
			aDriver.quit();			 
		 }		
		 SA5.assertAll();
	}
	
	public void enter_Sname_Press_SubmitBtn() throws InterruptedException, IOException {
		
		SoftAssert SA6=new SoftAssert();
		
		 if(isDeviceRegistered().equals("Register your device")) {
			 clickBtn(Locators_WarrantyReg.SWR_Now);
			 enterTextToInputField(Locators_WarrantyReg.SWR_Enter_Last_Name, "sonim");
			 customWait(1000);
			 clickBtn(Locators_WarrantyReg.SWR_Submit);
			 customWait(1000);
			 if(isElementExist(Locators_WarrantyReg.SWR_Please_enter_Company_Name)) {
				 APP_LOGS.info("Please enter the company name dialog dsiplayed ");
				 check=true;
				 clickBtn(Locators_WarrantyReg.SWR_OK_Btn);
				 SA6.assertTrue(check, "");
			 }
			 else {				 
				 SA6.fail();
			 }			
		 }
		 else {			 
		    APP_LOGS.info("********************Device is Already Registered********************");
			System.out.println("*******Device is Already Registered,Need to Delete the device from server to run the Script*******");
			SA6.fail();
			aDriver.quit();
		}
		 SA6.assertAll();
	}
	
	public void enter_CompanyName_Press_SubmitBtn() throws InterruptedException, IOException {
		
		SoftAssert SA7=new SoftAssert();
		
		 if(isDeviceRegistered().equals("Register your device")) {
			 
			 clickBtn(Locators_WarrantyReg.SWR_Now);
			 enterTextToInputField(Locators_WarrantyReg.SWR_Enter_Company_Name, "Sonim Technology");
			 customWait(1000);
			 clickBtn(Locators_WarrantyReg.SWR_Submit);
			 customWait(1000);
			 if(isElementExist(Locators_WarrantyReg.SWR_Please_enter_Address_1)) {
				 check=true;
				 clickBtn(Locators_WarrantyReg.SWR_OK_Btn);
				 SA7.assertTrue(check, "");
			 }
			 else {
				 SA7.fail();
			 }			
		}
		else {	 
			APP_LOGS.info("********************Device is Already Registered********************");
			System.out.println("*******Device is Already Registered,Need to Delete the device from server to run the Script*******");
			SA7.fail();
			aDriver.quit();
		}
		SA7.assertAll();
	}
	
	public void enter_Address1_Press_SubmitBtn() throws InterruptedException, IOException {
		
		SoftAssert SA8=new SoftAssert();
		 	if(isDeviceRegistered().equals("Register your device")) {
		 		clickBtn(Locators_WarrantyReg.SWR_Now);
			 	enterTextToInputField(Locators_WarrantyReg.SWR_Enter_Address_1, "J P Nagar,3rd phase");
			 	customWait(1000);
			 	clickBtn(Locators_WarrantyReg.SWR_Submit);
			 
				 	if(isElementExist(Locators_WarrantyReg.SWR_Please_enter_City)) {
				 		check=true;
				 		clickBtn(Locators_WarrantyReg.SWR_OK_Btn);
				 		SA8.assertTrue(check, "");
					}
				 	else {
					 SA8.fail();
				 	}			 
		 	}
		 	else {			 
		 		APP_LOGS.info("********************Device is Already Registered********************");
		 		System.out.println("*******Device is Already Registered,Need to Delete the device from server to run the Script*******");
		 		SA8.fail();
		 		aDriver.quit();
		 	}
		 	SA8.assertAll();
	}
	
	public void enter_City_Press_SubmitBtn() throws InterruptedException, IOException {
		
		SoftAssert SA9=new SoftAssert();
		if(isDeviceRegistered().equals("Register your device")) {
			
			clickBtn(Locators_WarrantyReg.SWR_Now);
			aDriver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().className(\"android.widget.ScrollView\")).scrollIntoView(new UiSelector().text(\"Enter city\"))");
			enterTextToInputField(Locators_WarrantyReg.SWR_enter_City, "Banglore");
			customWait(1000);
			clickBtn(Locators_WarrantyReg.SWR_Submit);
			customWait(1000);
				 if(isElementExist(Locators_WarrantyReg.SWR_Please_Enter_State)) {
					 check=true;
					 clickBtn(Locators_WarrantyReg.SWR_OK_Btn);
					 SA9.assertTrue(check, "");
				 }
				 else {
					 SA9.fail();
				 }				 
			}
			else {
			    APP_LOGS.info("********************Device is Already Registered********************");
				System.out.println("*******Device is Already Registered,Need to Delete the device from server to run the Script*******");
				SA9.fail();
				aDriver.quit();
			}
		SA9.assertAll();
	}
	
	public void enter_StateName_PressSubmitBtn() throws InterruptedException, WebDriverException, IOException {
	
		 SoftAssert SA10=new SoftAssert();
		 if(isDeviceRegistered().equals("Register your device")) {
		
		     clickBtn(Locators_WarrantyReg.SWR_Now);
			 aDriver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().className(\"android.widget.ScrollView\")).scrollIntoView(new UiSelector().text(\"Enter state name\"))");
			 enterTextToInputField(Locators_WarrantyReg.SWR_Enter_State_Name, "Karnataka");
			 customWait(1000);
			 clickBtn(Locators_WarrantyReg.SWR_Submit);
			 
				if(isElementExist(Locators_WarrantyReg.SWR_Please_enter_zipcode)) {
					 check=true;
					 clickBtn(Locators_WarrantyReg.SWR_OK_Btn);
					 SA10.assertTrue(check, "");
				}
				else {
					SA10.fail();
				}			 
		 }
		 else {
			 APP_LOGS.info("********************Device is Already Registered********************");
			 System.out.println("*******Device is Already Registered,Need to Delete the device from server to run the Script*******");
			 SA10.fail();
			 aDriver.quit();
		}
		SA10.assertAll();
	}
	   
	public void enter_ZipCode_PressSubmitbtn() throws InterruptedException, IOException {
			
		SoftAssert SA11=new SoftAssert();
		
			if(isDeviceRegistered().equals("Register your device")) {				 
				 clickBtn(Locators_WarrantyReg.SWR_Now);			       
			     aDriver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().className(\"android.widget.ScrollView\")).scrollIntoView(new UiSelector().text(\"Enter zip code\"))");
			     	for(int i=1;i<=3;i++) {
			     		aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_POUND);
			     	}
			 	 enterTextToInputField(Locators_WarrantyReg.SWR_Enter_zip_Code, "560078");
			 	 customWait(1000);
				 clickBtn(Locators_WarrantyReg.SWR_Submit);
				   	if(isElementExist(Locators_WarrantyReg.SWR_Please_enter_emailid)) {
				   		check=true;
				   		clickBtn(Locators_WarrantyReg.SWR_OK_Btn);
						SA11.assertTrue(check, "Pass");
				   	}
				   	else {
				   		SA11.fail();
				   	}		   			 
		 }
		 else {	
		    APP_LOGS.info("********************Device is Already Registered********************");
			System.out.println("*******Device is Already Registered,Need to Delete the device from server to run the Script*******");
			SA11.fail();
			aDriver.quit();
		}
		SA11.assertAll();
	}
	
	public void enter_EmailId_Press_SubmitBtn() throws InterruptedException, IOException {
		
		SoftAssert SA12=new SoftAssert();
		if(isDeviceRegistered().equals("Register your device")) {
			clickBtn(Locators_WarrantyReg.SWR_Now);
			aDriver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().className(\"android.widget.ScrollView\")).scrollIntoView(new UiSelector().text(\"Enter email id\"))");
			customWait(1000);
			enterTextToInputField(Locators_WarrantyReg.SWR_Enter_Email_Id, "sonim");
			Runtime.getRuntime().exec("adb shell input keyevent --longpress KEYCODE_1");
			customWait(1000);
			clickBtn(Locators_WarrantyReg.SWR_At_the_Rate_Symbol);
			customWait(1000);
				for(int i=1; i<=3; i++ ) {
					minWait();
					aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_RIGHT);
				}
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_ENTER);
			Runtime.getRuntime().exec("adb shell input text gmail.com");			       
			customWait(1000);
			customWait(4000);
			customWait(2000);
			clickBtn(Locators_WarrantyReg.SWR_Submit);
			customWait(2000);
				if(isElementExist(Locators_WarrantyReg.SWR_Please_enter_phone_number)) {
					check=true;
					clickBtn(Locators_WarrantyReg.SWR_OK_Btn);
					SA12.assertTrue(check, "");
				}
				else {
					SA12.fail();
				}			
		}
		else {
			 APP_LOGS.info("********************Device is Already Registered********************");
				System.out.println("*******Device is Already Registered,Need to Delete the device from server to run the Script*******");
				SA12.fail();
				aDriver.quit();
		}	
		SA12.assertAll(); 
	}
	
	public void enter_PhoneNum_Press_SubmitBtn() throws InterruptedException, IOException {
		SoftAssert SA13=new SoftAssert();
		
		if(isDeviceRegistered().equals("Register your device")) {			
			 clickBtn(Locators_WarrantyReg.SWR_Now);
			 aDriver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().className(\"android.widget.ScrollView\")).scrollIntoView(new UiSelector().text(\"Enter phone number\"))");
			 customWait(1000);
		     enterTextToInputField(Locators_WarrantyReg.SWR_Enter_Phone_Number, "9989898989");
		     customWait(1000);
		     clickBtn(Locators_WarrantyReg.SWR_Submit);
		     customWait(1000);
		       if(isElementExist(Locators_WarrantyReg.SWR_Please_Enter_purchase_date)) {
		    	   check=true;
		    	   clickBtn(Locators_WarrantyReg.SWR_OK_Btn);
		    	   SA13.assertTrue(check, "");
		       }
		       else {		    	   
		    	   SA13.fail();
		       }		       
	    }
		else {
			APP_LOGS.info("********************Device is Already Registered********************");
			System.out.println("*******Device is Already Registered,Need to Delete the device from server to run the Script*******");
			SA13.fail();
			aDriver.quit();
		}		
		SA13.assertAll();
	}
		
	
	public void enter_PurchaseDate_Press_SubmitBtn() throws InterruptedException, IOException {
		SoftAssert sf =new SoftAssert();
		if(isDeviceRegistered().equals("Register your device")) {
			minWait();
			clickBtn(Locators_WarrantyReg.SWR_Now);
			customWait(2000);
			aDriver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().className(\"android.widget.ScrollView\")).scrollIntoView(new UiSelector().text(\"Select Date\"))").click();
			customWait(1000);
			clickBtn(Locators_WarrantyReg.SWR_ChooseBtn);
		}
		else {
			aDriver.quit();
		}		
	}
	
	public void enter_DealerName_Press_SubmitBtn() throws WebDriverException, InterruptedException, IOException {
	    
		SoftAssert SA15=new SoftAssert();
		if(isDeviceRegistered().equals("Register your device")) {
			try {
				clickBtn(Locators_WarrantyReg.SWR_Now);
			} catch (Exception e) {				
			}
			aDriver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().className(\"android.widget.ScrollView\")).scrollIntoView(new UiSelector().text(\"Enter Dealer Name\"))").sendKeys("Sonim Tech");
			customWait(1000);
		}
		else {
			aDriver.quit();
		}
	}
	
	public void addInvoice() throws InterruptedException, WebDriverException, IOException {
		
		if(isDeviceRegistered().equals("Register your device")) {
			clickBtn(Locators_WarrantyReg.SWR_Now);
			aDriver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().className(\"android.widget.ScrollView\")).scrollIntoView(new UiSelector().text(\"Choose a File\"))").click();;
	        customWait(1000);
	        clickBtn(Locators_WarrantyReg.SWR_Take_a_Pic);
	        customWait(2000);
	        clickBtn(Locators_WarrantyReg.SWR_PhotoCapture);
	        customWait(1000);
	        clickBtn(Locators_WarrantyReg.SWR_OK_Btn);	        
		}
		else {
			aDriver.quit();
		}
	}
	
	public void finalSubmit() {
		
		APP_LOGS.info("Here clicking the final submit button");
		clickBtn(Locators_WarrantyReg.SWR_Submit);	
	}
	
	public void clickOnReset() throws InterruptedException, IOException {
		navigateToWarrantyRegApp();
		customWait(3000);
		try {
			clickBtn(Locators_WarrantyReg.SWR_OK_Btn);
		} catch (Exception e) {
			e.printStackTrace();
		}
		clickBtn(Locators_WarrantyReg.SWR_Now);
		customWait(2000);
		clickBtn(Locators_WarrantyReg.SWR_RstBtn);
		minWait();		
	}
	
}