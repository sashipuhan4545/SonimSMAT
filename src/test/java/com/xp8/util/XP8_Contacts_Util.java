package com.xp8.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.json.simple.parser.ParseException;
import org.testng.asserts.SoftAssert;

import com.graphics.gui.JsonFileReaderAndWriter;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import application.AllQA;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.AndroidKeyCode;
import junit.framework.Assert;

public class XP8_Contacts_Util extends BaseUtil {
	
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
			clickBtn(Locators_Contacts.app_List);
			minWait();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public void navigateToMoreOptions(String str) throws InterruptedException {
		//
		try {
			minWait();
			clickBtn(Locators_Contacts.ContactsMoreOptions);
			minWait();
			clickBtn(Locators_Contacts.contactsSettingsOPt);
			minWait();
			scrollToText(str);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
    public void importContacts() throws InterruptedException {
    	try {
    		minWait();
//    		clickBtn(Locators_Contacts.contactsImportSettings);
			minWait();
			clickBtn(Locators_Contacts.vcfFile);
			minWait();
			clickBtn(Locators_Contacts.PHONE_RadioBtn);
			minWait();
			if(isElementExist(Locators_Contacts.DownloadsOption)) {
				System.out.println("Already in Downloads");
			}else {
			clickBtn(Locators_Contacts.MoreOptions);
			minWait();			
			clickBtn(Locators_Contacts.DownloadsOption);
			minWait();
			}
			System.out.println("Clicking on VCF");
			clickBtn(Locators_Contacts.VcfFileOption);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}    	  	
    }
	
    public void exportContacts() throws InterruptedException {
    	//export contacts
    	try {
			minWait();
			clickBtn(Locators_Contacts.vcfFile);
			minWait();
			clickBtn(Locators_Contacts.PHONE_RadioBtn);
			minWait();
			clickBtn(Locators_Contacts.Save_Btn);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }
    public void deleteContacts() throws InterruptedException {
		try {
			minWait();
			System.out.println("IM in Contacts");
			if (isElementExist(Locators_Contacts.no_Contacts)) {
				APP_LOGS.info("No Contact"); 
				System.out.println("No Contact");
				minWait();
			} else {
			clickBtn(Locators_Contacts.deleteContactOptn1);
	        	minWait();
				clickBtn(Locators_Contacts.Selection_menu);
				minWait();
				if(isElementExist(Locators_Contacts.ALL_Selection_menu)) {
					clickBtn(Locators_Contacts.ALL_Selection_menu);
					minWait();
				}
				else {
					minWait();
					clickBtn(Locators_Contacts.one_Selection_menu);
				}
				clickBtn(Locators_Contacts.OKBtn1);
				minWait();
				clickBtn(Locators_Contacts.OKBtn);
				APP_LOGS.info("Contacts Deleted");
				minWait();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			test.log(LogStatus.ERROR, "No Such element Found");
		}
	}
	
    public void validateImportContacts() throws InterruptedException {
    	// validate Imported contacts 
    	SoftAssert sf = new SoftAssert();
    	try {
			if (!isElementExist(Locators_Contacts.no_Contacts)) {
				APP_LOGS.info("No Contact"); 
				check=true;
				APP_LOGS.info("Contacts Imported Succeccfully");
				System.out.println("Imported");
				sf.assertTrue(check, "TestCase Valiation is PASS");
				test.log(LogStatus.PASS,"Contacts Imported Successfully");
			} else {
				APP_LOGS.info("SMS didn't sent");
				sf.fail();
				test.log(LogStatus.FAIL,"Contacts didn't Imported");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			test.log(LogStatus.ERROR, "No Such element Found");
		}  
    	sf.assertAll();
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