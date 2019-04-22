package com.xp3.Utils;

import static org.testng.Assert.assertTrue;

import java.awt.AWTException;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.SocketException;
import java.util.UUID;

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
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import application.AllQA;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.AndroidKeyCode;

public class XP3_Device_Sanity_Util extends BaseUtil {

	public String p_Id  	= "";    					// Primary Device Serial Number.
	public String r_Id 		= "";    					// Reference Device Serial Number.
	public String p_b_No 	= "";      					// Primary Device Build Number.
	public String r_b_No 	= "";    					// Reference Device Build Number.
	public String pryNum 	= AllQA.PRIMARYDEVMDN; 		// Reference Device MDN.  
	public String refNum 	= AllQA.REFERENCEDEVMDN;	// Reference Device MDN.
	public String msg       = "This is an automation message. Kindly ignore";  //Template message used for SMS

	public void fetch_Devices_Details() throws InterruptedException, FileNotFoundException, IOException, ParseException {

		minWait();
		p_Id=JsonFileReaderAndWriter.primaryDevIdReader();
		r_Id=JsonFileReaderAndWriter.ReadRefDeviceId();
		p_b_No=JsonFileReaderAndWriter.primaryDevFirmwareReader();
		r_b_No=JsonFileReaderAndWriter.ReadRefDeviceFirmWare();
	}

	public Process p;
	public String startAdbLog() throws AWTException, InterruptedException, IOException {

		Thread.sleep(1000);
		String fileName = UUID.randomUUID().toString().replaceAll("-","");
		System.out.println(fileName);
		String path = "src/test/resources/adbLogs/"+fileName+".txt";
		try {
			Runtime.getRuntime().exec("adb -s "+p_Id+" logcat -c");
			Thread.sleep(1000);
			p=Runtime.getRuntime().exec("cmd /C \"adb -s "+p_Id+" logcat -v time>"+path);
			Thread.sleep(2000);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return fileName;
	}
	
	public void validateAppMenuLaunch(SoftAssert s) throws InterruptedException {
		/*
		 * validate App Launch Menu from Launcher
		 */
		boolean check=false;
		try {
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_HOME);
			minWait();
			test.log(LogStatus.INFO, "App Menu is clicked");
			minWait();
			//if(validate_presenceOfElement(Locators_DevSanity.settings, "Settings")) {
			if(isElementExist(Locators_DevSanity.settings)) {
				check = true;
				s.assertTrue(check, "Validation of App Menu Launch is PASS");
				test.log(LogStatus.PASS, "Validated Launch App Menu From Launcher");
			}
			else {
				s.fail();
				test.log(LogStatus.ERROR, "Launch App Menu From Launcher is not Validated");
			}
		} catch (Exception e) {
			s.fail();
			e.printStackTrace();
		}		
	}



	public void validateLaunch_All_Applications(SoftAssert s) throws InterruptedException, IOException {
		//this method launch all applications one by one and validate

		try {
			//launch_an_app("browser");
			//customWait(3000);
			//aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_MENU);
			//minWait();
			//clickBtn(Locators_DevSanity.URLOption);
			//minWait();
			//aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			//minWait();
			//validate_launchofApp(Locators_DevSanity.UrlTxt, "Browser");

			launch_an_app("messaging");
			validate_launchofApp(s,Locators_DevSanity.messaging_Title, "Messaging");

			launch_an_app("contacts");
			if(isElementExist(Locators_DevSanity.contact_FindContacts)) {
				validate_launchofApp(s,Locators_DevSanity.contact_FindContacts, "Contacts");
			}
			else if(isElementExist(Locators_DevSanity.contacts)) {
				validate_launchofApp(s,Locators_DevSanity.contacts, "Contacts");
			}

			launch_an_app("phone");
			validate_launchofApp(s,Locators_DevSanity.phone_find, "Phone");

			launch_an_app("camera");
			switchCamera(s);

			launch_an_app("gallery");
			validate_launchofApp(s,Locators_DevSanity.GalleryAlbums, "Gallery");

			launch_an_app("settings");
			validate_launchofApp(s,Locators_DevSanity.settingsIcon, "Settings");

			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_HOME);
			minWait();
			clickBtn(Locators_BaseUtil.applications_icon);
			validate_launchofApp(s,Locators_Sanity.FileExplorer_App, "Applications");

			launch_an_app("music");
			validate_launchofApp(s,Locators_DevSanity.artists_music_player, "Music");

			launch_an_app("soundRecorder");
			validate_launchofApp(s,Locators_DevSanity.sound_Recorder, "Sound Recorder");

			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);

		} catch (Exception e) {
			e.printStackTrace();
			APP_LOGS.info("Exception in \"validateLaunch_All_Applications\"");
			test.log(LogStatus.ERROR,"No Such Element Found");
			s.fail();
		}	
	}

	public void validate_launchofApp(SoftAssert s,WebElement element, String elementName) throws InterruptedException {
		minWait();
		System.out.println("inside validatee");
		boolean check = false;
		try {
			customWait(6000);
			if (isElementExist(element)) {
				System.out.println(" inside ");
				check = true;
				APP_LOGS.info(elementName + " is found succesfully");
				test.log(LogStatus.PASS, elementName + " Application Successfully Launched ");
				s.assertTrue(check, "TestCase Passed");
			} else {
				check = false;
				APP_LOGS.info(elementName + " is not found");
				test.log(LogStatus.FAIL, elementName + " Application is not Launched");
				s.fail();
			}
		} catch (Exception e) {
			e.printStackTrace();
			APP_LOGS.info("Exception in \"validate_launchofApp\"");
			test.log(LogStatus.ERROR,"No Such Element Found");
			s.fail();
		}
	}

	public void switchCamera(SoftAssert s) throws InterruptedException {
		try {
			if(isElementExist(Locators_DevSanity.camera_Icon)) {
				validate_launchofApp(s,Locators_DevSanity.camera_Icon, "Camera");
			}
			else if (isElementExist(Locators_DevSanity.videocameraIcon)) {
				validate_launchofApp(s,Locators_DevSanity.videocameraIcon, "Camera");
			}
		} catch (NoSuchElementException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Assert.fail();
		}					
	}


	public void checkBasebandVersion(SoftAssert s) throws InterruptedException {
		
		String version;
		boolean check = false;
		try {
			minWait();
			scrollToText("System");
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
			minWait();
			for(int i=0; i<=8; i++){
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
			}
			//aDriver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true)).scrollToEnd(2)").click();
			minWait();
			scrollToText("About phone");
			minWait();
			scrollText("Baseband version");
			minWait();
			version = Locators_DevSanity.basebandVersion.getText();
			APP_LOGS.info("Baseband version string-"+ version);
			System.out.println("Version =" +version+ "Got from"+ JsonFileReaderAndWriter.primaryDevOperatorReader());
			if (version.equalsIgnoreCase(JsonFileReaderAndWriter.primaryDevOperatorReader())){
				check = true;
				APP_LOGS.info("Baseband version string displayed in device and fetched from device are same");
				s.assertTrue(check, "Baseband version strings match");
				test.log(LogStatus.PASS, "Baseband version matches.");
			}else {
				s.fail();
				test.log(LogStatus.FAIL, "Baseband version doesn't matches.");
			}
		}
		catch (Exception e){
			s.fail();
			test.log(LogStatus.FAIL, "Baseband versions don't match");
		}		
	}

	public void changeNetworkType(SoftAssert s) throws InterruptedException {

		boolean check=false;
		try {
			String selectedNetworkType;
			APP_LOGS.info("Inside changeNetworkType");
			minWait();
			scrollToText("Network & Internet");
			minWait();
			scrollToText("Mobile network");
			minWait();
			scrollText("Access Point Names");
			minWait();
			System.out.println("PNT :"+Locators_Sanity.preferredNetworkType.getText());
			selectedNetworkType = Locators_Sanity.selectedNetworkType.getText();
			System.out.println(selectedNetworkType);
			APP_LOGS.info(selectedNetworkType);

			clickBtn(Locators_Sanity.preferredNetworkType);
			minWait();
			clickBtn(Locators_Sanity.threeGoption);
			minWait();
			press_BackKey();
			minWait();
			scrollToText("Mobile network");
			minWait();
			scrollText("Access Point Names");
			minWait();
			System.out.println("before");
			selectedNetworkType = Locators_Sanity.selectedNetworkType.getText();
			System.out.println("after");
			System.out.println(selectedNetworkType);
			
			if (selectedNetworkType.equals("3G")) {
				check = true;
				APP_LOGS.info("3G network type is selected");
				s.assertTrue(check, "3G network changed successfully");
				test.log(LogStatus.PASS, "3G network changed successfully");
			} else {
				APP_LOGS.info("3G network type is not selected");
				s.assertFalse(check, "3G network change is not successful");
				test.log(LogStatus.FAIL, "3G network change is not successful");
			}
			minWait();
			clickBtn(Locators_Sanity.preferredNetworkType);
			minWait();
			clickBtn(Locators_Sanity.twoGoption);
			minWait();
			press_BackKey();
			minWait();
			scrollToText("Mobile network");
			minWait();
			scrollText("Access Point Names");
			minWait();
			if (Locators_Sanity.selectedNetworkType.getText().equals("2G")) {
				check = true;
				APP_LOGS.info("2G network type is selected");
				s.assertTrue(check, "2G network changed successfully");
				test.log(LogStatus.PASS, "2G network changed successfully");
			}	else {
				check = false;
				APP_LOGS.info("2G network type is not selected");
				s.assertFalse(check, "2G network change is not successful");
				test.log(LogStatus.FAIL, "2G network change is not successful");
			}			
			minWait();
			clickBtn(Locators_Sanity.preferredNetworkType);
			minWait();
			clickBtn(Locators_Sanity.LTEoption);
			minWait();
			
		}catch(Exception e) {
			APP_LOGS.error("Not able to determinte the network type");
			test.log(LogStatus.FAIL, "Not able to change the network type");
			minWait();
			scrollToText("Preferred network type");
			minWait();
			clickBtn(Locators_Sanity.LTEoption);
			customWait(2000);
			s.fail();
		}
	}

	public void changeNetworkTypeSP(SoftAssert s) throws InterruptedException {

		boolean check= false;
		int strlength=0;

		try {
			String selectedNetworkType;
			APP_LOGS.info("Inside changeNetworkTypeSP");
			scrollToText("Network & Internet");
			minWait();
			scrollToText("Mobile network");
			minWait();
			scrollText("Roaming");
			minWait();
			System.out.println(Locators_Sanity.preferredNetworkType.getText());
			selectedNetworkType = Locators_Sanity.selectedNetworkType.getText();
			System.out.println(selectedNetworkType);
			APP_LOGS.info(selectedNetworkType);

			scrollToText("Preferred network type");
			minWait();
			//selectFromList("3G");
			Locators_Sanity.LTEoption.click();
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.BACK);
			customWait(4000);
			scrollToText("Mobile network");
			minWait();
			scrollText("Roaming");
			minWait();
			System.out.println("before");
			selectedNetworkType = Locators_Sanity.selectedNetworkType.getText();
			System.out.println("after");
			System.out.println(selectedNetworkType);
			if (selectedNetworkType.equals("Preferred network mode: LTE/CDMA")) {
				check = true;
				APP_LOGS.info("LTE network type is selected");
				s.assertTrue(check, "LTE network changed successfully");
				test.log(LogStatus.PASS, "LTE network changed successfully");
			} else {
				check = false;
				APP_LOGS.info("LTE network type is not selected");
				s.assertFalse(check, "LTE network change is not successful");
				test.log(LogStatus.FAIL, "LTE network change is not successful");
			}

			scrollToText("Preferred network type");
			minWait();
			//selectFromList("3G");
			Locators_Sanity.threeGoption.click();
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.BACK);
			customWait(4000);
			scrollToText("Mobile network");
			minWait();
			scrollText("Roaming");
			minWait();
			System.out.println("before");
			selectedNetworkType = Locators_Sanity.selectedNetworkType.getText();
			System.out.println("after");
			System.out.println(selectedNetworkType);
			if (selectedNetworkType.equals("Preferred network mode: CDMA")) {
				check = true;
				APP_LOGS.info("CDMA network type is selected");
				s.assertTrue(check, "CDMA network changed successfully");
				test.log(LogStatus.PASS, "CDMA network changed successfully");
			}
			else {
				check = false;
				APP_LOGS.info("CDMA network type is not selected");
				s.assertFalse(check, "CDMA network change is not successful");
				test.log(LogStatus.FAIL, "CDMA network change is not successful");
			}
			scrollToText("Preferred network type");
			minWait();
			//selectFromList("2G");
			Locators_Sanity.twoGoption.click();
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.BACK);
			customWait(4000);
			scrollToText("Mobile network");
			minWait();
			scrollText("Roaming");
			minWait();

			if (Locators_Sanity.selectedNetworkType.getText().equals("Preferred network mode: GSM/UMTS")) {
				check = true;
				APP_LOGS.info("GSM network type is selected");
				s.assertTrue(check, "GSM network changed successfully");
				test.log(LogStatus.PASS, "GSM network changed successfully");
			}
			else {
				check = false;
				APP_LOGS.info("GSM network type is not selected");
				s.assertFalse(check, "GSM network change is not successful");
				test.log(LogStatus.FAIL, "GSM network change is not successful");
			}
			scrollToText("Preferred network type");
			minWait();
			//selectFromList("LTE (recommended)");
			Locators_Sanity.automaticOption.click();
		}
		catch(Exception e) {
			APP_LOGS.error("Not able to determinte the network type");
			test.log(LogStatus.FAIL, "Not able to change the network type");
			
			scrollToText("Preferred network type");
			minWait();
			//selectFromList("LTE (recommended)");
			Locators_Sanity.LTEoption.click();
			customWait(4000);
			s.fail();
		}
		customWait(6000);
	}

	public void navigateToApplication(String application) throws InterruptedException {

		try {
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_HOME);
			minWait();
			APP_LOGS.info("Inside navigateToApplication");
			scrollText(application);
			minWait();
			navigateUsingText(application);
			minWait();
		}
		catch(Exception e) {
			APP_LOGS.info("Exception in navigateToApplication");	
		}
	}

	public void MO_CALL_Sanity() throws IOException, InterruptedException, ParseException {

		APP_LOGS.info("This Method is used to Originate MO call");
		boolean check;
		SoftAssert MOCall = new SoftAssert();
		String Refdevid=JsonFileReaderAndWriter.ReadRefDeviceId();
		/*
		String mobnumber=AllQA.REFERENCEDEVMDN;
		JsonFileReaderAndWriter.RefMobileNumber(mobnumber);
		*/
		String Uid=JsonFileReaderAndWriter.primaryDevIdReader();
		System.out.println(Refdevid + Uid + refNum);
		for(int i=1;i<=1;i++) {
			
			Runtime.getRuntime().exec("adb -s "+Uid+" shell am start -a android.intent.action.CALL -d tel:"+refNum);
			Thread.sleep(10000);
			if (isElementExist(Locators_Sanity.dialingScreen)) {
				check = true;
				MOCall.assertTrue(check,"Call is initiated");
				APP_LOGS.info("Call is originated from primary device");
				test.log(LogStatus.INFO, "Call is originated from primary device");
			} else {
				check = true;
				MOCall.assertFalse(check,"Call is not initiated");
				APP_LOGS.info("Call is not originated from primary device");
				test.log(LogStatus.FAIL, "Call is not originated from primary device");
			}
			try {
				for(int j=1;j<=100;j++){
					String value=null;
					Process child=Runtime.getRuntime().exec("adb -s "+Refdevid+" shell service call telecom 30");
					InputStream lsOut = child.getInputStream();
					InputStreamReader r = new InputStreamReader(lsOut);
					BufferedReader in = new BufferedReader(r);
					value=in.readLine();
					System.out.println(value);
					System.out.println(Refdevid);
					if(value.contains("00000001")) {
						check = true;
						System.out.println("Phone is ringing");
						MOCall.assertTrue(check, "Call received in reference device");
						test.log(LogStatus.INFO, "MO CALL #"+i  +" : "+"  "+pryNum +"  --->  "+refNum);
						Thread.sleep(5000);

						Runtime.getRuntime().exec("adb -s "+Refdevid+" shell input keyevent 5");
						break;
					}
				}
				System.out.println("Disconnecting ");
				Thread.sleep(5000);
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_ENDCALL);
				Thread.sleep(3000); 

			}catch (Exception e) {

				System.out.println("Execption in call recevie ");
				Runtime.getRuntime().exec("adb -s "+Refdevid+" shell input keyevent 5");
				Thread.sleep(2000);
				MOCall.fail();
			}
		}
		MOCall.assertAll();
	}

	public void MT_CALL_Sanity() throws IOException, InterruptedException, ParseException {

		APP_LOGS.info("This Method is used to Receive a MT call");
		SoftAssert receiveCall = new SoftAssert();
		boolean check = false;
		String Refdevid=JsonFileReaderAndWriter.ReadRefDeviceId();
		/*
		String mobnumber=AllQA.REFERENCEDEVMDN;
		JsonFileReaderAndWriter.RefMobileNumber(mobnumber);
		 */
		String Uid=JsonFileReaderAndWriter.primaryDevIdReader();
		System.out.println(Refdevid + Uid + pryNum);
		for(int i=1;i<=1;i++)	{
			Runtime.getRuntime().exec("adb -s "+Refdevid+" shell am start -a android.intent.action.CALL -d tel:"+pryNum);
			Thread.sleep(10000);

			try {

				for(int j=1;j<=100;j++){
					String value=null;
					Process child=Runtime.getRuntime().exec("adb -s "+Uid+" shell service call telecom 30");
					InputStream lsOut = child.getInputStream();
					InputStreamReader r = new InputStreamReader(lsOut);
					BufferedReader in = new BufferedReader(r);
					value=in.readLine();
					System.out.println(value);
					System.out.println(Refdevid);
					if(value.contains("00000001")) {

						System.out.println("Phone is ringing");
						check = true;

						test.log(LogStatus.INFO, "MT CALL #"+i  +" : "+"  "+refNum +"  --->  "+pryNum);
						Thread.sleep(5000);

						Runtime.getRuntime().exec("adb -s "+Uid+" shell input keyevent 5");
						break;

					}


				}
				System.out.println("Disconnecting ");
				Thread.sleep(5000);
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_ENDCALL);
				Thread.sleep(3000); 

			}catch (Exception e) {

				System.out.println("Execption in call recevie ");

				Runtime.getRuntime().exec("adb -s "+Uid+" shell input keyevent 5");
				Thread.sleep(2000);
				receiveCall.fail();

			}
		}
		receiveCall.assertTrue(check, "Call is ringing");
		receiveCall.assertAll();
	}


	public void MT_CALL_REJECT_Sanity() throws IOException, InterruptedException, ParseException {

		APP_LOGS.info("This Method is used to Reject a MT call");

		boolean check = false;
		SoftAssert rejectCall = new SoftAssert();

		String Refdevid=JsonFileReaderAndWriter.ReadRefDeviceId();
		/*	String mobnumber=AllQA.REFERENCEDEVMDN;
					JsonFileReaderAndWriter.RefMobileNumber(mobnumber);*/
		String Uid=JsonFileReaderAndWriter.primaryDevIdReader();
		//System.out.println(Refdevid + Uid + AllQA.REFERENCEDEVMDN);
		for(int i=1;i<=1;i++)
		{
			Runtime.getRuntime().exec("adb -s "+Refdevid+" shell am start -a android.intent.action.CALL -d tel:"+pryNum);
			Thread.sleep(10000);

			try {

				for(int j=1;j<=100;j++){
					String value=null;
					Process child=Runtime.getRuntime().exec("adb -s "+Uid+" shell service call telecom 30");
					InputStream lsOut = child.getInputStream();
					InputStreamReader r = new InputStreamReader(lsOut);
					BufferedReader in = new BufferedReader(r);
					value=in.readLine();
					System.out.println(value);
					System.out.println(Refdevid);
					if(value.contains("00000001")) {
						check = true;
						System.out.println("Phone is ringing");

						test.log(LogStatus.INFO, "MT CALL REJECT#"+i  +" : "+"  "+refNum +"  --->  "+pryNum);
						Thread.sleep(5000);

						Runtime.getRuntime().exec("adb -s "+Uid+" shell input keyevent 6");
						break;

					}


				}
				System.out.println("Disconnecting ");
				Thread.sleep(5000);
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_ENDCALL);
				Thread.sleep(3000); 
				Runtime.getRuntime().exec("adb -s "+Refdevid+" shell input keyevent 6");

			}catch (Exception e) {

				System.out.println("Execption in call recevie ");

				Runtime.getRuntime().exec("adb -s "+Uid+" shell input keyevent 6");
				Thread.sleep(2000);
				Assert.fail();
			}
		}
		rejectCall.assertTrue(check, "");
		rejectCall.assertAll();
	}

	public void inCallFunctionality() throws InterruptedException, IOException, ParseException
	{
		/*
		 * Validates in call functionality
		 */
		try {
			minWait();
			initiateCall();
			//		Runtime.getRuntime().exec("adb -s "+p_Id+" shell input text "+ refNum);
			//		aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_CALL);
			customWait(10000);
			reciveCallInRefDevice(refNum);
			customWait(6000);
			speakerStatus();
			minWait();
			muteFunctionStatus();
			minWait();
			holdFunctionStatus();
			minWait();
			clickEndCallButton();
		}catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "Element Not Found");
		}
	}

	public void initiateCall() throws IOException, ParseException {
		/*
		 * Intiate MO call
		 */
		try {
			String Uid=JsonFileReaderAndWriter.primaryDevIdReader();
			Runtime.getRuntime().exec("adb -s "+Uid+" shell am start -a android.intent.action.CALL -d tel:"+refNum+"");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void reciveCallInRefDevice(String refNum) throws InterruptedException, IOException {
		/*
		 * Receive Call in Reference device
		 */
		boolean check = false;
		try {

			if (!isElementExist(Locators_DevSanity.turnOff_Airplane_PopUp)) {
				try {

					for(int j=1;j<=100;j++){

						Process child = null;
						if (r_b_No.contains("8A.")) {

							child=Runtime.getRuntime().exec("adb -s "+r_Id+" shell service call telecom 27");

						} else if(r_b_No.contains("3A.")) {

							child=Runtime.getRuntime().exec("adb -s "+r_Id+" shell service call telecom 30");
						}     
						InputStream lsOut = child.getInputStream();
						InputStreamReader r = new InputStreamReader(lsOut);
						BufferedReader in = new BufferedReader(r);
						String  value=in.readLine();
						System.out.println(value);
						if(value.contains("00000001")) {
							check = true;
							System.out.println("Phone is ringing so accepting call.");
							Thread.sleep(3000);
							Runtime.getRuntime().exec("adb -s "+r_Id+" shell input keyevent 5");
							break;
						}else {
							continue;
						}
					}
				}catch(Exception e) {
					Thread.sleep(2000);
					Runtime.getRuntime().exec("adb -s "+r_Id+" shell input keyevent 5");
					Thread.sleep(2000);
				}

			}
			APP_LOGS.info("Number dailed is: "+refNum);
		}catch (NoSuchElementException e) {
			APP_LOGS.info("Selected Page is not displayed.");
			e.printStackTrace();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_ENDCALL);
		}
	}

	public void speakerStatus() throws InterruptedException, IOException
	{
		/*
		 * Validates speaker status
		 */
		try {
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_UP);
			customWait(2000);
			validateInCallFunctionality("Speaker On","Entering state ActiveSpeakerRoute:");
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_UP);
			customWait(2000);
			validateInCallFunctionality("Speaker Off","Leaving state ActiveSpeakerRoute:");
		}catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "Element Not Found");
		}
	}

	public void validateInCallFunctionality(String function,String searchstring) throws InterruptedException, IOException
	{
		/*
		 * Validate call history
		 */
		SoftAssert SA = new SoftAssert();
		try {
			boolean value = searchString(searchstring, "XP3_DeviceSanity_007");
			if(value)
			{
				check = true;
				APP_LOGS.info("In call functionality "+ function +" verified successfully");
				test.log(LogStatus.INFO, "In call functionality "+ function +" verified successfully");
				SA.assertTrue(check, "In call functionality "+ function +" verified successfully");	
			}else {
				APP_LOGS.info("In call functionality "+ function +" not verified");
				test.log(LogStatus.ERROR, "In call functionality "+ function +" not verified");
				SA.fail("In call functionality "+ function +" not verified");
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_ENDCALL);
				minWait();
			}	
		}catch (NoSuchElementException e) {
			e.printStackTrace();
			APP_LOGS.info("Element Not Found");
			test.log(LogStatus.ERROR, "Element Not Found");
			SA.fail();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_ENDCALL);
		}
		SA.assertAll();
	}

	public void changeToNumberMode_SMS(WebElement element) throws InterruptedException {
		/*
		 * Change input field type to number
		 */
		try {
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
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void changeToAlphabetMode_SMS(WebElement element) throws InterruptedException {
		/*
		 * Change input field type to number
		 */
		try {
			minWait();
			element.sendKeys("abc");
			customWait(1500);
			String text = element.getText();
			System.out.println(text);
			if(!text.equals("abc")) {
				for (int i = 0; i < 4; i++) {
					aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_POUND);
					minWait();
				}
			}
			element.clear();
			/*for (int i=0; i<2; i++)
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_CLEAR);*/

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void inputData(String data) throws InterruptedException, IOException
	{
		/*
		 * Search contact with name
		 */
		try
		{
			customWait(2000);
			Runtime.getRuntime().exec("adb -s "+p_Id+" shell input text "+ data);
			customWait(6000);
		}
		catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "Element Not Found");

		}
	}

	public void muteFunctionStatus() throws InterruptedException, IOException
	{
		/*
		 * Validates mute status
		 */
		try {
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_LEFT);
			customWait(2000);
			validateInCallFunctionality("Mute","Message received: MUTE_ON=3001, arg1=0: ICA.m->CARSM.pM_MUTE_ON");
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_LEFT);
			customWait(2000);
			validateInCallFunctionality("Unmute","Message received: MUTE_OFF=3002, arg1=0: ICA.m->CARSM.pM_MUTE_OFF");
		}catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "Element Not Found");
		}
	}

	public void holdFunctionStatus() throws InterruptedException, IOException
	{
		/*
		 * Validates hold status
		 */
		try {
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_RIGHT);
			customWait(2000);
			validateInCallFunctionality("Hold","CallButtonPresenter - Putting the call on hold:");
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_RIGHT);
			customWait(2000);
			validateInCallFunctionality("Unhold","CallButtonPresenter - Removing the call from hold:");
		}catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "Element Not Found");
		}
	}

	public void clickEndCallButton()
	{
		/*
		 * clicks on End call button 
		 */
		try {
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_ENDCALL);
			minWait();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}


	public void sendSMSWhenInCall(SoftAssert s,String text) throws InterruptedException, IOException, ParseException
	{
		/*
		 * Send SMS with ongoing call
		 */
		try {
			initiateCall();
			customWait(10000);
			reciveCallInRefDevice(refNum);
			minWait();
			launch_an_app("messaging");
			minWait();
			clickMenuAndElement(Locators_DevSanity.newMessageOpt);
			minWait();
			changeToNumberMode_SMS(Locators_Sanity.toField_NewMessage);
			enterTextToInputField(Locators_Sanity.toField_NewMessage, refNum);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
			minWait();
			enterTextToInputField(Locators_Sanity.typeMessageOpt, text);
			minWait();
			clickMenuAndElement(Locators_Sanity.msg_send);
			customWait(5000);
			validateSendMessage(s);

		}catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "Element Not Found");
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_ENDCALL);
			minWait();
		}
	}

	public void clickMenuAndElement(WebElement element) throws InterruptedException
	{
		/*
		 * Click on menu button and clicks on element passed
		 */
		try
		{
			customWait(2000);
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_MENU);
			customWait(2000);
			element.click();
			minWait();
		}catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "Element Not Found");
		}
	}


	public void validateSendMessage(SoftAssert s) throws InterruptedException, IOException
	{
		/*
		 * Validates sent message
		 */
		try {
			if(isElementExist(Locators_Sanity.now_Text)){
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
				minWait();
				clickEndCallButton();
				minWait();
				clickEndCallButton();
				check = true;
				APP_LOGS.info("Message sent successfully");
				test.log(LogStatus.INFO, "Message sent successfully");
				s.assertTrue(check, "Message sent successfully");	
			}
			else {
				APP_LOGS.info("Message not sent");
				test.log(LogStatus.ERROR, "Message not sent");
				s.fail("Message not sent");
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_ENDCALL);
			}	
		}catch (Exception e) {
			e.printStackTrace();
			APP_LOGS.info("Element Not Found");
			test.log(LogStatus.ERROR, "Element Not Found");
			s.fail();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_ENDCALL);
		}
	
		aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
		minWait();
		aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_ENDCALL);
		minWait();
	}


	public void makeCallFromHistory() throws FileNotFoundException, IOException, ParseException, InterruptedException {
		/*
		 * Makes a phone call to the reference device 
		 */
		SoftAssert phoneCall = new SoftAssert();
		String call_state;
		boolean check;
		String Refdevid=JsonFileReaderAndWriter.ReadRefDeviceId();

		try {
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
			customWait(3000);
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_CALL);
			customWait(3000);
			APP_LOGS.info("Initiating the call");
			call_state = Locators_Sanity.dialingScreen.getText();
			minWait();
			if (call_state.equals("Dialing")) {
				check = true;
				phoneCall.assertTrue(check, "Call is initiated successfully");

			}
			else {
				check = true;
				phoneCall.assertFalse(check, "call is not initiated");
			}

			for(int j=1;j<=100;j++){
				String value=null;
				Process child=Runtime.getRuntime().exec("adb -s "+Refdevid+" shell service call telecom 30");
				InputStream lsOut = child.getInputStream();
				InputStreamReader r = new InputStreamReader(lsOut);
				BufferedReader in = new BufferedReader(r);
				value=in.readLine();
				System.out.println(value);
				System.out.println(Refdevid);
				if(value.contains("00000001")) {

					System.out.println("Phone is ringing");

					test.log(LogStatus.INFO, "MO CALL" +" : "+"  "+pryNum +"  --->  "+refNum);
					Thread.sleep(5000);

					check = true;
					phoneCall.assertTrue(check, "Call is ringing in reference device");
					Runtime.getRuntime().exec("adb -s "+Refdevid+" shell input keyevent 5");
					break;

				}


			}
			System.out.println("Disconnecting ");
			Thread.sleep(5000);
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_ENDCALL);
			Thread.sleep(3000); 

		}
		catch(Exception e) {
			System.out.println("Execption in call recevie ");

			Runtime.getRuntime().exec("adb -s "+Refdevid+" shell input keyevent 6");
			Thread.sleep(2000);
		}
	}

	public void AddContactFromHistory(String contactName) throws InterruptedException {
		/*
		 * Add contact from phone history 
		 */

		SoftAssert addContact = new SoftAssert();
		boolean check;
		String nameFirstEntry;

		try {
			APP_LOGS.info("Inside AddContactFromHistory");
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_HOME);
			minWait();
			navigateToApplication("Phone");
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_MENU);
			minWait();
			//selectFromList("Add to contact");
			Locators_Sanity.addContactPhone.click();
			customWait(2000);
			//selectFromList("Create new contact");
			Locators_Sanity.newContactPhone.click();
			//aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
			//minWait();
			//aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
			minWait();
			Locators_Sanity.contactName.sendKeys(contactName);
			customWait(6000);
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_MENU);
			minWait();
			//selectFromList("Save");
			Locators_Sanity.contact_Save.click();
			customWait(4000);
			nameFirstEntry = Locators_Sanity.nameFirstEntry.getText();
			if (nameFirstEntry.equalsIgnoreCase(contactName)) {
				check = true;
				addContact.assertTrue(check, "Contact Addition was successful");
				APP_LOGS.info("Contact added successfully");
				test.log(LogStatus.INFO, "Contact added successfully");
			}
			else {
				check = true;
				addContact.assertFalse(check, "Contact Addition was not successful");
				APP_LOGS.info("Contact was not added");
				test.log(LogStatus.FAIL, "Contact was not added");
			}
		}
		catch(Exception e) {
			e.printStackTrace();
			APP_LOGS.info("Contact addition was not successful-- Not able to find UI element");
			test.log(LogStatus.FAIL, "Contact was not added");
			Assert.fail();
		}
		addContact.assertAll();	
	}

	public boolean DeleteContact(String contactName) throws InterruptedException {
		/*
		 * Delete the contact from phonebook 
		 */

		boolean check;
		try {	
			APP_LOGS.info("Inside DeleteContact");
			navigateToApplication("Contacts");
			Locators_Sanity.contact_find.sendKeys(contactName.substring(0, 3));
			customWait(4000);
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_CLEAR);
			minWait();
			//selectFromList(contactName);
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
			minWait();
			if (isElementExist(Locators_DevSanity.permissionPopUp)){
				for (int i=0; i<=2; i++){
					minWait();
					clickBtn(Locators_DevSanity.allowButton);
					minWait();
				}
			}
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_MENU);
			minWait();
			//selectFromList("Delete");
			Locators_Sanity.contact_delete.click();
			minWait();
			Locators_Sanity.contact_DeleteBtn.click();
			minWait();
			check = true;
			APP_LOGS.info("Contact deleted successfully");
		}
		catch(Exception e) {
			check = false;
			APP_LOGS.info("Contact was not deleted");
			test.log(LogStatus.WARNING, "Contact not deleted");
		}
		return check;
	}

	public void sendSMSContact(String contactName, String message) throws InterruptedException {
		/*
		 * Used to send SMS to saved contact
		 */
		SoftAssert sendSMS = new SoftAssert();
		boolean check;

		try {
			APP_LOGS.info("Inside sendSMSContact");
			navigateToApplication("Messaging");
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_MENU);
			minWait();
			//selectFromList("New message");
			Locators_Sanity.New_msgOptn.click();
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_MENU);
			minWait();
			//selectFromList("Add Contact");
			Locators_Sanity.addContactOpt.click();
			minWait();			
		
			Locators_Sanity.contact_find.sendKeys(contactName);
			customWait(4000);
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_CLEAR);
			minWait();
			if (isElementExist(Locators_Sanity.PTTAppPageList)) {
				check = true;
				APP_LOGS.info("Contact search was successful");
				test.log(LogStatus.INFO, "Contact was found");
				sendSMS.assertTrue(check, "Contact found");
			}
			else {
				check = true;
				APP_LOGS.info("Contact search was not successful");
				test.log(LogStatus.INFO, "Contact was not found");
				sendSMS.assertFalse(check, "Contact not found");
			}
			//selectFromList(contactName);
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
			//aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_MENU);
			minWait();
			//selectFromList("Done");
			Locators_Sanity.doneOpt.click();
			minWait();
			//changeToAlphabetMode_SMS(Locators_Sanity.message_field);
			//minWait();
			Locators_Sanity.typeMsg_Field.sendKeys(message);
			customWait(5000);
			for (int i =1; i<5; i++) {
				Runtime.getRuntime().exec("adb -s "+p_Id+" shell input keyevent --longpress KEYCODE_1");
				minWait();
				minWait();
				for (int j=1; j<i+3; j++) {
					aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_RIGHT);
					minWait();
				}
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
				minWait();
				Runtime.getRuntime().exec("adb -s "+p_Id+" shell input keyevent --longpress KEYCODE_2");
				minWait();
			}
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_MENU);
			minWait();
			//selectFromList("Send");
			Locators_Sanity.msg_send.click();
			customWait(6000);
		}
		catch(Exception e) {
			check = true;
			APP_LOGS.info("SMS not send");
			test.log(LogStatus.INFO, "SMS was not sent");
			sendSMS.assertFalse(check, "SMS is not send");
		}
		sendSMS.assertAll();
	}


	public void deleteAllSMS() throws InterruptedException {
		/*
		 * Used to save message as draft and validate
		 */
		try {	
			APP_LOGS.info("Inside deleteAllSMS");
			if (!isElementExist(Locators_Sanity.noConversations)) {
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_MENU);
				minWait();
				Locators_Sanity.selectOptInMsg.click();
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_MENU);
				minWait();
				Locators_Sanity.deleteAllThreadsInMsg.click();
				minWait();
				Locators_Sanity.contact_DeleteBtn.click();
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
				minWait();
				APP_LOGS.info("Deleted all SMS successfully");
			}
		}
		catch(Exception e) {
			APP_LOGS.info("Messages not deleted");
			test.log(LogStatus.WARNING, "Messages are not deleted");
		}
	}

	public void saveMessageDraft(String message) throws InterruptedException {
		/*
		 * Used to save message as draft and validate
		 */
		SoftAssert draftSMS = new SoftAssert();
		boolean check;
		try {		
			APP_LOGS.info("Inside saveMessageDraft");
			deleteAllSMS();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_MENU);
			minWait();
			Locators_Sanity.New_msgOptn.click();
			minWait();
			changeToNumberMode_SMS(Locators_Sanity.toField_NewMessage);
			minWait();
			enterTextToInputField(Locators_Sanity.toField_NewMessage, refNum);
			customWait(4000);
			enterTextToInputField(Locators_Sanity.typeMsg_Field, message);
			customWait(4000);
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			minWait();
			if (isElementExist(Locators_Sanity.draft_Text)) {
				check = true;
				APP_LOGS.info("Message is saved as draft");
				test.log(LogStatus.PASS, "Message is saved in draft");
				draftSMS.assertTrue(check, "Message is saved in draft");
			}
			else {
				check = true;
				APP_LOGS.info("Message is not saved in drafts");
				test.log(LogStatus.FAIL, "Message is not saved in draft");
				draftSMS.assertFalse(check, "Message is not saved in draft");
			}
		}catch (Exception e) {
			check = true;
			APP_LOGS.info("Inside saveMessageDraft Exception");
			test.log(LogStatus.FAIL, "UI element not found");
			draftSMS.assertFalse(check, "Message is not saved in draft");
		}
		draftSMS.assertAll();
		aDriver.pressKeyCode(AndroidKeyCode.BACK);
		minWait();
		aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_HOME);
		minWait();
	}



	public void CallRejectWithSMS() throws IOException, InterruptedException, ParseException {
		/*
		 * Used to reject voice call with SMS
		 */

		APP_LOGS.info("This Method is used to Receive a MT call and reject with sms");


		String Refdevid=JsonFileReaderAndWriter.ReadRefDeviceId();
		String call_state;
		SoftAssert phoneCall = new SoftAssert();
		/*	String mobnumber=AllQA.REFERENCEDEVMDN;
					JsonFileReaderAndWriter.RefMobileNumber(mobnumber);*/
		String Uid=JsonFileReaderAndWriter.primaryDevIdReader();
		System.out.println(Refdevid + Uid + pryNum);
		try {
			for(int i=1;i<=1;i++)
			{

				Runtime.getRuntime().exec("adb -s "+Refdevid+" shell am start -a android.intent.action.CALL -d tel:"+pryNum);
				customWait(3000);
				APP_LOGS.info("Initiating the call");
				/*call_state = Locators_Sanity.dialingScreen.getText();
				minWait();
				if (call_state.equals("Dialing")) {
					check = true;
					phoneCall.assertTrue(check, "Call is initiated successfully");
					APP_LOGS.info("Call is initiated successfully");

				}
				else {
					check = false;
					phoneCall.assertFalse(check, "call is not initiated");
					APP_LOGS.info("Call is not initiated");
				}
				 */				Thread.sleep(10000);

				 for(int j=1;j<=100;j++){
					 String value=null;
					 Process child=Runtime.getRuntime().exec("adb -s "+Uid+" shell service call telecom 30");
					 InputStream lsOut = child.getInputStream();
					 InputStreamReader r = new InputStreamReader(lsOut);
					 BufferedReader in = new BufferedReader(r);
					 value=in.readLine();
					 System.out.println(value);
					 System.out.println(Refdevid);
					 if(value.contains("00000001")) {

						 System.out.println("Phone is ringing");
						 APP_LOGS.info("Call is ringing");
						 test.log(LogStatus.INFO, "MT CALL #"+i  +" : "+"  "+refNum +"  --->  "+pryNum);
						 minWait();
						 aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_MENU);
						 minWait();
						 Locators_Sanity.endCallmsg.click();
						 System.out.println("inside call reject with sms");
						 customWait(3000);
						 check = true;
						 phoneCall.assertTrue(check, "Phone was ringing and selected message for rejecting call");
						 //Runtime.getRuntime().exec("adb -s "+Uid+" shell input keyevent 5");

						 break;

					 }


				 }
				 navigateToApplication("Messaging");
				 minWait();
				 if (isElementExist(Locators_Sanity.endCallmsg) || isElementExist(Locators_Sanity.now_Text))
				 {
					 check = true;
					 APP_LOGS.info("Call reject message is send");
					 phoneCall.assertTrue(check, "Call Rejected with SMS");
					 System.out.println("inside true for messaging");
				 }
				 else
				 {
					 check = true;
					 APP_LOGS.info("Call reject message is not send");
					 phoneCall.assertFalse(check, "Call reject with SMS failed");
					 System.out.println("inside false for messaging");
				 }

			}
		}
		catch (Exception e) {
			check = true;
			System.out.println("Execption in call recevie ");
			Runtime.getRuntime().exec("adb -s "+Uid+" shell input keyevent 5");
			Thread.sleep(2000);
			phoneCall.assertFalse(check);
		}
		phoneCall.assertAll();
		aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_ENDCALL);
		minWait();
		aDriver.pressKeyCode(AndroidKeyCode.BACK);
		minWait();
		Runtime.getRuntime().exec("adb -s "+r_Id+" shell input keyevent 6");
		minWait();
	}		

	public boolean scrollToAndEnterText(String scrollTo,String stringToEnter) {
		/*
		  Method used to select an element on the page by scrolling the Scroll View/List View
		 */
		boolean check = false;
		try {  
			String scrollable = "new UiScrollable(new UiSelector().scrollable(true))";
			String textElement = ".scrollIntoView(new UiSelector().text(\""+ scrollTo +"\"))";
			aDriver.findElementByAndroidUIAutomator(scrollable+textElement).sendKeys(stringToEnter);
			APP_LOGS.info("Searched application is found sucessfully : ");
			check = true;
			return check;
		}
		catch(NoSuchElementException e) {
			return check;
		}
	}
	
	public void createContactAllFields(String name, String phoneticLast, String phoneticMiddle, String phoneticFirst, String nickName,
									   String company, String title, String phone, String SIP, String email, String address, String IM,
									   String website, String notes) throws InterruptedException {
		/*
		 * Used to create a contact with all fields filled.
		 */
		boolean check=false;
		SoftAssert createContact = new SoftAssert();
		APP_LOGS.info("Inside createContactAllFields");
		try {
			if(isElementExist(Locators_Sanity.Addcontacts)) {
				Locators_Sanity.Addcontacts.click();
				minWait();
			} else {
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_MENU);
				minWait();
				//selectFromList("Add Contact");
				Locators_Sanity.addContactOpt.click();
			}
			
			if(isElementExist(Locators_Sanity.changePhotoOpt)) {
				check = true;
				APP_LOGS.info("Add new contact page displayed");
				createContact.assertTrue(check, "Add contact page is opened");
				test.log(LogStatus.INFO, "Add new contact page displayed");
			} else {
				APP_LOGS.info("Add new contact page is not displayed");
				createContact.assertFalse(check, "Add contact page is not opened");
				test.log(LogStatus.FAIL, "Add new contact page is not displayed");
			}
			scrollToText("More fields");
			minWait();
			//scrollText("Name");
			minWait();
			scrollToAndEnterText("Name", name);
			//enterTextToInputField(Locators_Sanity.contactName, name);
			customWait(2000);
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_RIGHT);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
			minWait();
			scrollToAndEnterText("Phonetic last name", phoneticLast);
			customWait(2000);
			scrollToAndEnterText("Phonetic middle name", phoneticMiddle);
			customWait(2000);
			scrollToAndEnterText("Phonetic first name", phoneticFirst);
			customWait(2000);
			scrollToAndEnterText("Nickname", nickName);
			customWait(2000);
			scrollToAndEnterText("Company", company);
			customWait(2000);
			scrollToAndEnterText("Title", title);
			customWait(2000);
			scrollToAndEnterText("Phone", refNum);
			customWait(2000);
//			scrollText("SIP");
//			minWait();
//			System.out.println(SIP);
//			enterTextToInputField(Locators_Sanity.sipFld, SIP);
//			customWait(4000);
			scrollToAndEnterText("Email", email);
			customWait(2000);
			scrollToAndEnterText("Address", address);
			customWait(2000);
			scrollToAndEnterText("IM", IM);			
			customWait(2000);
			scrollToAndEnterText("Website", website);
			customWait(2000);
			scrollToAndEnterText("Notes", notes);
			customWait(2000);
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_MENU);
			minWait();
			clickBtn(Locators_DevSanity.contact_Save);
			customWait(4000);
			String contactName = Locators_Sanity.contact_Test.getText();
			if(name.equalsIgnoreCase(contactName)) {
				check = true;
				APP_LOGS.info("Contact added successfully with all fields");
				createContact.assertTrue(check, "Contact added successfully");
				test.log(LogStatus.PASS, "Contact addition with all fields is successful");
			} else {
				APP_LOGS.info("Contact addition failed");
				createContact.assertFalse(check, "Contact was not added");
				test.log(LogStatus.FAIL, "Contact addition failed");
			}
		}
		catch(Exception e) {
			APP_LOGS.info("Exception while adding a contact");
			createContact.fail();
			test.log(LogStatus.FAIL, "Contact not added");
		}
		aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
		minWait();
		aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_HOME);
		minWait();
		createContact.assertAll();
	}

	public void deleteContact(String name) throws InterruptedException  {
		/*
		 * Used to delete a contact and validate
		 */
		boolean check;
		SoftAssert deleteContact = new SoftAssert();
		try {
			APP_LOGS.info("Inside deleteContact");
			check = DeleteContact(name);
			deleteContact.assertTrue(check, "Check for contact deletion");
			check = searchForContact(name);
			if (check == false) {
				APP_LOGS.info("Contact search returned false");
				deleteContact.assertFalse(check, "Contact is deleted");
			}
			else {
				APP_LOGS.info("Contact search returned true");
				deleteContact.assertFalse(check, "Contact is not deleted");
				test.log(LogStatus.FAIL, "Contact is not deleted");
			}
		}
		catch(Exception e) {
			APP_LOGS.info("Exception occured while deleting and validating delete contact functionality");
			Assert.fail();
		}
		deleteContact.assertAll();
	}

	public boolean searchForContact(String contactName) {
		/*
		 * Used for searching a contact in phonebook
		 */
		boolean check;
		//SoftAssert searchContact = new SoftAssert();
		try {
			APP_LOGS.info("Inside searchContact");
			navigateToApplication("Contacts");
			Locators_Sanity.contact_find.sendKeys(contactName.substring(0, 3));
			customWait(4000);
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_CLEAR);
			minWait();
			if (isElementExist(Locators_Sanity.noContacts)) {
				check = false;
				APP_LOGS.info("No contacts exist for the searched string");
			}
			else {
				check = true;
				APP_LOGS.info("Contacts exist for the searched string");
			}
		}
		catch(Exception e) {
			check = false;
			APP_LOGS.info("Exception occured while searching for contact");
		}
		return check;
	}

	public boolean disableMobileData() throws InterruptedException {
		/*
		 * Used for disabling Mobile Data
		 */
		boolean check;
		String status;
		SoftAssert disableData = new SoftAssert();

		try {
			APP_LOGS.info("Inside disableMobileData");
			navigateToApplication("Settings");
			scrollToText("Network & Internet");
			minWait();
			scrollToText("Mobile network");
			minWait();
			status = Locators_Sanity.mobileDataSwitch.getText();
			if (status.equalsIgnoreCase("ON")) {
				Locators_Sanity.mobileDataSwitch.click();
				APP_LOGS.info("Disabling mobile data");
				minWait();
				if (isElementExist(Locators_Sanity.SSIDTxt)) {
					Locators_Sanity.OK_Btn.click();
					minWait();
				}
			}
			check = true;
			disableData.assertTrue(check, "Disabled mobile data");
		}
		catch(Exception e) {
			check = false;
			APP_LOGS.info("Exception occured while disabling mobile data");
			disableData.assertTrue(check, "Mobile data is not disabled");
		}
		disableData.assertAll();
		return check;
	}

	public boolean disableWifi() {
		/*
		 * Used for disabling Wifi
		 */
		boolean check;
		String status;

		try {
			APP_LOGS.info("Inside disableWifi");
			navigateToApplication("Settings");
			scrollToText("Network & Internet");
			minWait();
			scrollToText("Wi‑Fi");
			minWait();
			status = Locators_Sanity.switch_Title.getText();
			if (status.equalsIgnoreCase("ON")) {
				Locators_Sanity.switch_Title.click();
				APP_LOGS.info("Disabling Wifi");
				minWait();
			}
			check = true;
		}
		catch(Exception e) {
			check = false;
			APP_LOGS.info("Exception occured while disabling Wifi");
		}
		return check;
	}

	public void browserTest() throws InterruptedException {
		/*
		 * Used for browsing using browser and validating data/wifi network
		 */

		boolean check;
		SoftAssert browserTest = new SoftAssert();

		APP_LOGS.info("browserTest");
		navigateToApplication("Browser");
		minWait();
		if (isElementExist(Locators_Sanity.permissionPopUp)) {
			for (int i=0; i<5; i++) {
				Locators_Sanity.allowButton.click();
				minWait();
			}
		}
		if (isElementExist(Locators_Sanity.NoNewtwrkErroeMsg)) {
			Locators_Sanity.CANCEL_Button.click();
			minWait();
		}
		Locators_Sanity.DefaultUrlTxt.sendKeys("www.google.com");
		customWait(4000);
		aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
		customWait(5000);

	}

	public void enableBluetooth() throws InterruptedException {
		/*
		 * Used for enabling Bluetooth
		 */

		boolean check;
		String status;
		SoftAssert enableBluetooth = new SoftAssert();

		APP_LOGS.info("Inside enableBluetooth");
		try {
			navigateToApplication("Settings");
			scrollToText("Connected devices");
			minWait();
			scrollToText("Bluetooth");
			minWait();
			status = Locators_Sanity.switch_Title.getText();

			if (status.equalsIgnoreCase("OFF")) {
				Locators_Sanity.switch_Title.click();
				APP_LOGS.info("Enabling Bluetooth");
				minWait();
			}
			check = true;
			enableBluetooth.assertTrue(check, "Bluetooth is enabled");
		}
		catch(Exception e) {
			check = false;
			enableBluetooth.assertTrue(check, "Bluetooth is not enabled");
			APP_LOGS.info("Exception occured while enabling Bluetooth");
		}
		enableBluetooth.assertAll();
	}


	public boolean disableBluetooth() {
		/*
		 * Used for disabling Bluetooth
		 */
		boolean check;
		String status;
		SoftAssert disableBluetooth = new SoftAssert();

		APP_LOGS.info("Inside disableBluetooth");
		try {
			navigateToApplication("Settings");
			scrollToText("Connected devices");
			minWait();
			scrollToText("Bluetooth");
			minWait();
			status = Locators_Sanity.switch_Title.getText();
			if (status.equalsIgnoreCase("ON")) {
				Locators_Sanity.switch_Title.click();
				APP_LOGS.info("Disabling Bluetooth");
				minWait();
			}
			check = true;
			disableBluetooth.assertTrue(check, "Bluetooth disabled successfully");
		}
		catch(Exception e) {
			check = false;
			disableBluetooth.assertTrue(check, "Bluetooth is not disabled");
			APP_LOGS.info("Exception occured while disabling Bluetooth");
		}
		return check;
	}

	public void scanForDevices() throws InterruptedException {
		/*
		 * Used for scanning for Bluetooth devices
		 */

		boolean check;
		String status;
		SoftAssert scanBluetooth = new SoftAssert();

		try {
			APP_LOGS.info("Inside enableBluetooth");
			enableBluetooth();
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_MENU);
			minWait();


			//selectFromList("Pair new device");
			Locators_Sanity.pairNewDevice.click();
			customWait(10000);
			if (isElementExist(Locators_Sanity.bluetooth_Availabledevices)) {
				check = true;
				APP_LOGS.info("Bluetooth Scanning is successful");
				scanBluetooth.assertTrue(check, "Bluetooth scanning is successful");
			}
			else {
				check = true;
				APP_LOGS.info("Bluetooth Scanning is not successful");
				scanBluetooth.assertFalse(check, "Bluetooth scanning is not successful");
			}
		}
		catch(Exception e) {
			APP_LOGS.info("Exception occured in scanning bluetooth devices");
			test.log(LogStatus.FAIL, "Bluetooth device scanning failed");
			Assert.fail();
		}
		scanBluetooth.assertAll();
	}

	public void editAPN() {
		/*
		 * Used for editing APN
		 */
		boolean check;
		SoftAssert editAPN = new SoftAssert();
		String name, apn;

		APP_LOGS.info("Inside editAPN");
		try {
			navigateToApplication("Settings");
			scrollToText("Network & Internet");
			minWait();
			scrollToText("Mobile network");
			minWait();
			scrollToText("Access Point Names");
			minWait();
		}
		catch (Exception e) {

		}
	}


	public void validate_getDate_Time_TimeZone() throws InterruptedException {
		/*
		 * check the date ,time and timezone.
		 */
		boolean check1 = false, check2 = false, check3 = false;
		SoftAssert Sv = new SoftAssert();
		try {
			scrollToText("System");
			minWait();
			scrollToText("Date & time");
			minWait();
			if(Locators_Sanity.Autodate_time.isEnabled()){
				APP_LOGS.info("Automatic date and Time is enabled");
			}
			else {
				clickBtn(Locators_Sanity.Autodate_time);
			}
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
			minWait();	
			if(Locators_Sanity.Autotimezone.isEnabled()){
				APP_LOGS.info("Automatic date and Time is enabled");
			}
			else {
				clickBtn(Locators_Sanity.Autotimezone);
			}

			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
			minWait();	
			clickBtn(Locators_Sanity.setDate);
			minWait();	
			String get_Date = Locators_Sanity.Summary.getText();
			//			System.out.println(get_Date);


			if(isElementExist(Locators_Sanity.Summary)){
				check1 = true;
				APP_LOGS.info("Element found Date : "+get_Date);
				APP_LOGS.info("check1: "+check1);
			}else{
				APP_LOGS.info("Date element is not found");
				APP_LOGS.info("check1: "+check1);
			}
			minWait();	
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
			minWait();	
			clickBtn(Locators_Sanity.settime);
			minWait();	
			String get_Time = Locators_Sanity.Summary.getText();
			//			System.out.println(get_Time);
			minWait();	
			if(isElementExist(Locators_Sanity.Summary)){
				check2 = true;
				APP_LOGS.info("Element found Time : "+get_Time);
				APP_LOGS.info("check2: "+check2);
			}else{
				APP_LOGS.info("Time element is not found");
				APP_LOGS.info("check2: "+check2);
			}
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
			minWait();	
			clickBtn(Locators_Sanity.settimeZone);
			minWait();	
			String get_Timezone = Locators_Sanity.Summary.getText();
			//			System.out.println(get_Timezone);
			minWait();	
			if(isElementExist(Locators_Sanity.Summary)){
				check3 = true;
				APP_LOGS.info("Element found Time : "+get_Timezone);
				APP_LOGS.info("check3: "+check3);
			}else{
				APP_LOGS.info("Time element is not found");
				APP_LOGS.info("check3: "+check3);
			}
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			minWait();	

			if((check1)&&(check2)&&(check3)){
				check=true;
				APP_LOGS.info("All dynamic parameters are displayed");
				APP_LOGS.info("Test case is passed");
				Sv.assertTrue(check, "  ");
			}else{
				APP_LOGS.info("Test case failed");
				Sv.fail();
			}
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			Sv.fail();
		}
		Sv.assertAll();
	}

	public void disableData() throws InterruptedException {
		/*
		 * enable data
		 */
		try {
			//this perform disable WiFi
			launch_an_app("settings");
			minWait();

			//minWait();
			//aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			scrollToText("Network & Internet");
			customWait(2000);
			clickBtn(Locators_DevSanity.wifiButton);
			//customWait(2000);
			//scrollToText("Mobile network");
			disableFeature(Locators_DevSanity.enabled,Locators_DevSanity.disabled);
			//minWait();
			//enable data
			customWait(3000);
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			if(isElementExist(Locators_DevSanity.MobileNetwrks)) {
				minWait();
				clickBtn(Locators_DevSanity.MobileNetwrks);
				APP_LOGS.info("Selected Mobile Networks");
				minWait();				
				disableSwitch(Locators_DevSanity.disabled_Data,Locators_DevSanity.enabled_Data,Locators_DevSanity.switch_widget);
				minWait();
			}
			else {
				clickBtn(Locators_DevSanity.dataUsageOptn);
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
				minWait();
				if (!isElementExist(Locators_DevSanity.cellular_DataON)) {
					minWait();
					clickBtn(Locators_DevSanity.cellular_DataOFF);
					APP_LOGS.info("Switch is Enabled");                     
					minWait();
				}
				minWait();
			}
		} catch (Exception e) {
			APP_LOGS.info("No Element found.");
			test.log(LogStatus.ERROR, "No Such Element Found");
			Assert.fail();
			minWait();	
		}
	}

	public void validateBrowseNoInternet() throws InterruptedException, IOException {
		/*
		 * Validate while browsing google.com
		 */
		SoftAssert sf= new SoftAssert();
		try {
			customWait(3000);
			if (isElementExist(Locators_DevSanity.noNetworkTitle)){
				clickBtn(Locators_DevSanity.cancelButton);
				System.out.println("Inside cancel press");
				minWait();
				//launch_an_app("browser");
			}
			customWait(3000);
			//enterUrl("www.google.com");
			minWait();



			aDriver.pressKeyCode(AndroidKeyCode.MENU);
			minWait();
			clickBtn(Locators_DevSanity.URLOption);
			minWait();



			//	aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_CLEAR);
			//	minWait();
			enterTextToInputField(Locators_DevSanity.UrlTxt, "www.google.com");
			minWait();

			customWait(4000);
			//enterTextToInputField(Locators_DevSanity.DefaultUrlTxt,newurl);

			//inputData("www.google.com");
			//customWait(4000);
			APP_LOGS.info(" URl is entered is sucessful.");
			//minWait();
			//minWait();	 
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_ENTER);
			customWait(6000);
			APP_LOGS.info(" Search click is sucessful."); 

			if((isElementExist(Locators_DevSanity.webpageNotavailable))) {
				check =true;
				APP_LOGS.info("check: "+check);
				APP_LOGS.info("Browser is Launched sucessfully");
				test.log(LogStatus.INFO, "Webpage is not loading because data is not enabled.");
				sf.assertTrue(check, "Validation is Pass");
			}
			else{
				APP_LOGS.info("check: "+check);
				APP_LOGS.info("Browsing is Launched and internet is available");
				test.log(LogStatus.INFO, "Browse is not launhed and surf sucessful");
				sf.fail();
			}minWait();	
		} catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "No Such Element Found");
			sf.fail();
		}
		sf.assertAll();
	}

	public void validateBrowseInternet() throws InterruptedException, IOException {
		/*
		 * Validate while browsing google.com
		 */
		SoftAssert sf= new SoftAssert();
		boolean b = false, check = false;
		try {
			customWait(2000);
			enterUrl("www.google.com");
			//sf.assertTrue(b, "Url is entered properly");
			customWait(2000);
			if((isElementExist(Locators_DevSanity.SearchIcon_Google))||(isElementExist(Locators_DevSanity.Searchenter_Google))) {
				check =true;
				APP_LOGS.info("check: "+check);
				APP_LOGS.info("Browser is Launched sucessfully");
				test.log(LogStatus.INFO, "Browsing is Launched and Surf sucessfully");
				sf.assertTrue(check, "Validation is Pass");
			}
			else{
				APP_LOGS.info("check: "+check);
				APP_LOGS.info("Browse internet is not sucessful");
				test.log(LogStatus.INFO, "Browse is not launhed and surf sucessful");
				sf.fail();
			}minWait();	
		} catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "No Such Element Found");
			sf.fail();
		}
		sf.assertAll();
	}

	public boolean enterUrl(String newurl) throws InterruptedException, IOException, SocketException {
		/*
		 * enter url when data is enabled
		 */

		try {
			minWait();
			//aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_MENU);
			//minWait();
			//enterTextToInputField(Locators_DevSanity.UrlTxt, newurl);
			Locators_DevSanity.UrlTxt.sendKeys(newurl);
			//clickBtn(Locators_DevSanity.URLOption);
			customWait(4000);
			//enterTextToInputField(Locators_DevSanity.DefaultUrlTxt,newurl);

			//inputData(newurl);
			APP_LOGS.info(" URl is entered is sucessful.");
			//minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
			minWait();	 
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);

			//Runtime.getRuntime().exec("adb -s "+p_Id+" shell input keyevent 23");
			customWait(6000);
			APP_LOGS.info(" Search click is sucessful."); 
			return true;


		} catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "No Such Element Found");
			//Assert.fail();
			return false;
		}
	}


	/*public void enableData() throws InterruptedException {

	 * enable data

		try {
			//this perform disable WiFi
			launch_an_app("settings");
			minWait();
			//clickBtn(Locators_DevSanity.wifi);
			//minWait();
			//aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			scrollToText("Network & Internet");
			//customWait(2000);
			//scrollToText("Mobile network");
			//disableFeature(Locators_DevSanity.enabled,Locators_DevSanity.disabled);
			//minWait();
			//enable data
			minWait();
			if(isElementExist(Locators_DevSanity.MobileNetwrks)) {
				minWait();
				clickBtn(Locators_DevSanity.MobileNetwrks);
				APP_LOGS.info("Selected Mobile Networks");
				minWait();				
				enableSwitch(Locators_DevSanity.enabled_Data,Locators_DevSanity.disabled_Data,Locators_DevSanity.switch_widget);
				minWait();
			}
			else {
				clickBtn(Locators_DevSanity.dataUsageOptn);
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
				minWait();
				if (!isElementExist(Locators_DevSanity.cellular_DataON)) {
					minWait();
					clickBtn(Locators_DevSanity.cellular_DataOFF);
					APP_LOGS.info("Switch is Enabled");                     
					minWait();
				}
				minWait();
			}
		} catch (Exception e) {
			APP_LOGS.info("No Element found.");
			test.log(LogStatus.ERROR, "No Such Element Found");
			Assert.fail();
			minWait();	
		}
	}*/

	public void selectSettings(WebElement option) throws InterruptedException, IOException {
		/*
		 * select particular setting option
		 */
		SoftAssert Sa = new SoftAssert();
		try {
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_HOME);
			minWait();
			minWait();
			Runtime.getRuntime().exec("adb shell am start -n com.android.settings/com.android.settings.Settings");
			minWait();
			scrollToText("Network & Internet");
			minWait();
			APP_LOGS.info("Launched Settings icon sucessfully");
			for (int iter= 1; iter<= 23; iter++) {
				if (isElementExist(option)) {
					String getName= option.getText();
					APP_LOGS.info("Settings Option is displayed sucessfully" +getName);
					minWait();	
					clickBtn(option);
					break;					
				} else {
					aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
					continue;					
				}
			}
		} catch (Exception e) {
			APP_LOGS.info("No Element found.");
			minWait();	
			Sa.assertAll();
		}
		Sa.assertAll();
	}

	public void ON_Switch(String switch_To_ON) {
		try {
			minWait();
			aDriver.findElementByXPath("//*[contains(@text,'"+switch_To_ON+"')]/..//*[@text='OFF']").click();
			minWait();
		} catch (Exception e) {
		}
	}
	
	public void ConnectSecuredWifi() throws InterruptedException, IOException {
		/*
		 * Connect wifi with entering passkey
		 */
		try {
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_HOME);
			customWait(2000);
			clickBtn(Locators_Sanity.settingsIcon);
			minWait();
			scrollToText("Network & Internet");
			minWait();
			clickBtn(Locators_Sanity.wifi);
			minWait();
			ON_Switch("Off");
			customWait(9000);			
			if (!isElementExist(Locators_Sanity.connected_WiFi)) {
				minWait();
				scrollToText("IDCSONWAP");
				minWait();
				clickBtn(Locators_Sanity.wifi_show_Pswd);
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_UP);
				customWait(1000);
				for (int i = 1; i <= 3; i++) {
					minWait();
					aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_POUND);
					minWait();
				}
				minWait();
				Runtime.getRuntime().exec("adb -s " + p_Id + " shell input text 1");
				minWait();
				Runtime.getRuntime().exec("adb -s " + p_Id + " shell input text dcS");
				minWait();
				Runtime.getRuntime().exec("adb -s " + p_Id + " shell input text 0");
				minWait();
				Runtime.getRuntime().exec("adb -s " + p_Id + " shell input text n");
				minWait();
				Runtime.getRuntime().exec("adb -s " + p_Id + " shell input text 1");
				minWait();
				Runtime.getRuntime().exec("adb -s " + p_Id + " shell input text md");
				minWait();
				Runtime.getRuntime().exec("adb -s " + p_Id + " shell input text 0");
				minWait();
				Runtime.getRuntime().exec("adb -s " + p_Id + " shell input text tc");
				minWait();
				Runtime.getRuntime().exec("adb -s " + p_Id + " shell input text 0");
				minWait();
				Runtime.getRuntime().exec("adb -s " + p_Id + " shell input text MbLr");
				minWait();
				minWait();
				customWait(1000);
				String psswrd = Locators_Sanity.wifi_IDC_Psswd.getText();
				System.out.println(psswrd);
				clickBtn(Locators_Sanity.wifi_IDC_ConnectBtn);
				APP_LOGS.info("IDC available secured wifi is connected");
				customWait(4000);
			}
		} catch (NoSuchElementException e) {
			e.printStackTrace();
		}
	}

	public void validateWifiConnect() throws InterruptedException, IOException {
		/*
		 * Validate wifi connection
		 */
		SoftAssert sf = new SoftAssert();
		try {
			minWait();
			selectSettings(Locators_Sanity.wifi);
			customWait(4000);
			selectIDcwifi();
			minWait();
			if(isElementExist(Locators_Sanity.wifi_IDC_ForgetBtn)) {
				check = true;
				APP_LOGS.info("Connected to Secured Wifi succesfully.");
				sf.assertTrue(check, "Validation is Pass");
			}else{
				APP_LOGS.info("Connected to Secured Wifi is unsuccesful.");	
				sf.fail();
			}
			minWait();	
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			minWait();	
			minWait();	
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			minWait();	
		} catch (org.openqa.selenium.NoSuchElementException e) {
			APP_LOGS.info("No Element found.");
			sf.fail();
		}
		sf.assertAll();
	}

	public void selectIDcwifi() throws InterruptedException {
		/*
		 * Select IDC wifi which is available
		 */
		try {
			customWait(4000);
			for(int i=1; i<=50; i++) {
				if(isElementExist(Locators_Sanity.wifi_IDC)) {
					minWait();
					clickBtn(Locators_Sanity.wifi_IDC);
					APP_LOGS.info("IDC available secured wifi is Selected");
					break;
				}
				else {
					minWait();
					aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
					continue;
				}
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {
			APP_LOGS.info("No Element found.");
			minWait();	
		}
	}

	public void disconnectWifiifConnected() throws InterruptedException {
		/*
		 * Disconnect wifi if IDC wifi connected
		 */
		try {
			//selectIDcwifi();
			scrollToText("IDCSONWAP");
			customWait(4000);
			if (isElementExist(Locators_Sanity.wifi_IDC_ForgetBtn))
				clickBtn(Locators_Sanity.wifi_IDC_ForgetBtn);
			else
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			minWait();
			APP_LOGS.info("IDC available secured wifi is disconnected");
			customWait(4000);
		} catch (org.openqa.selenium.NoSuchElementException e) {
			APP_LOGS.info("No Element found.");
			minWait();
		}
	}

	public void navigateTo_APN() throws InterruptedException {
		/*
		 * This Method will Navigate to the APN's  
		 */
		try {
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_HOME);
			minWait();
			clickBtn(Locators_Sanity.settings);
			minWait();
			scrollToText("Network & Internet");
			minWait();

			if(isElementExist(Locators_Sanity.mobile_Networks)) {
				clickBtn(Locators_Sanity.mobile_Networks);
			}
			else {
				clickBtn(Locators_Sanity.moreOptn);
			}
			minWait();
			for (int i = 1; i <=10; i++) {
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
				Thread.sleep(1000);
			}
			minWait();
			if(isElementExist(Locators_Sanity.access_Point_Names)) {
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
				minWait();
				minWait();
			}

			//clickBtn(Locators_Sanity.access_Point_Names);
			minWait();
		} catch (Exception e) {
			APP_LOGS.info("No Element found.");
			minWait();	
			Assert.fail();
		}
	}

	public void validateEditionDeletionApn() throws InterruptedException {
		/*
		 * validate default APn should not able to edit and delete
		 */
		boolean check1 = false, check2 = false;
		SoftAssert sf = new SoftAssert();
		try {

			minWait();	
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_MENU);
			minWait();	
			minWait();	
			if(isElementExist(Locators_Sanity.edit)) {
				check1=true;
				APP_LOGS.info("check1: "+check1);
				APP_LOGS.info("Edition of Apn is displayed unsuccesfully");
			}else{
				check1=true;
				APP_LOGS.info("check1: "+check1);
				APP_LOGS.info("Edition of Apn is displayed succesfully");			
			}
			clickBtn(Locators_Sanity.edit);
			minWait();
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_MENU);
			minWait();
			minWait();
			if(isElementExist(Locators_Sanity.delete_APN)) {	
				check2=true;
				APP_LOGS.info("check2: "+check2);
				APP_LOGS.info("Deletion Apn is displayed unsuccesfully");
			}else{
				check2=true;
				APP_LOGS.info("check2: "+check2);
				APP_LOGS.info("Deletion Apn is displayed succesfully");
			}
			if((check1)&&(check2)){
				check=true;
				APP_LOGS.info("Validated sucessfully Edition and Deletion of Default APn");			
				sf.assertTrue(check, "Validation is Pass");
			}else{
				APP_LOGS.info("Validated is not sucessful Edition and Deletion of Default APn ");					
				sf.fail();
			}
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_MENU);	
			minWait();
			clickBtn(Locators_Sanity.RestoreDefault);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
		} catch (Exception e) {
			APP_LOGS.info("No Element found.");
			sf.fail();
		}
		sf.assertAll();
	}

	public void validateRestoreAPn() throws InterruptedException {
		/*
		 * Validate APN default /restore After and before retore Option 
		 */
		SoftAssert sf = new SoftAssert();
		try {
			String getApNTxt= null;
			String getApNTxtRestore= null;
			for(int i=1; i<=3; i++) {
				if(isElementExist(Locators_Sanity.SelectedApn)) {
					aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
					getApNTxt = Locators_Sanity.Txt_Apn.getText();
					System.out.println(getApNTxt);
				}
				else {
					aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_MENU);
					continue;
				}		
			}	
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_MENU);	
			minWait();
			clickBtn(Locators_Sanity.RestoreDefault);
			customWait(4000);
			customWait(4000);
			customWait(4000);
			getApNTxtRestore = Locators_Sanity.Txt_Apn.getText();
			System.out.println(getApNTxtRestore);
			if(getApNTxt.equalsIgnoreCase(getApNTxtRestore)) {
				check = true;
				APP_LOGS.info("Default Restore Apn is display sucessful");
				sf.assertTrue(check, "Validation is Pass");
			}
			else{
				APP_LOGS.info("Default Restore Apn is not Displayed");
				sf.fail();
			}	
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
		} catch (Exception e) {
			APP_LOGS.info("No Element found.");
			e.printStackTrace();
			sf.fail();
		}
		sf.assertAll();
	}

	public void validateselectHardKeys() throws InterruptedException, IOException {
		SoftAssert sf = new SoftAssert();
		boolean check1 = false, check2 = false, check3 = false, check4 = false;
		try {
			customWait(5000);
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_HOME);
			if(isElementExist(Locators_Sanity.homePge)) {
				check1=true;
				APP_LOGS.info("check1: "+check1);
				APP_LOGS.info("Home Key is pressed succesfully.");				
			}else{
				APP_LOGS.info("check1: "+check1);
				APP_LOGS.info("Home Key is not pressed sucessfully.");				
			}
			Runtime.getRuntime().exec("cmd /c start /min cmd.exe /K \"adb shell input keyevent --longpress KEYCODE_VOLUME_UP");
			minWait();
			customWait(5000);
			Runtime.getRuntime().exec("cmd /c start /min cmd.exe /K \"adb shell input keyevent --longpress KEYCODE_VOLUME_UP");
			//			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_VOLUME_UP);
			if(isElementExist(Locators_Sanity.volume_Up_Icon)) {
				check2=true;
				APP_LOGS.info("check2: "+check2);
				APP_LOGS.info("Volume Up Key is pressed is succesful.");			
			}else{
				check2=true;
				APP_LOGS.info("check2: "+check2);
				APP_LOGS.info("Volume Up Key is not pressed sucessfully");				
			}

			customWait(5000);
			for(int i=1; i<=7; i++) {
				minWait();
				Runtime.getRuntime().exec("cmd /c start /min cmd.exe /K \"adb shell input keyevent --longpress KEYCODE_VOLUME_DOWN");
			}

			//			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_VOLUME_DOWN);
			if(isElementExist(Locators_Sanity.volume_Down_Icon)) {
				check3=true;
				APP_LOGS.info("check3: "+check3);
				APP_LOGS.info("Volume down Key is pressed is succesful.");			
			}else{
				check3=true;
				APP_LOGS.info("check3: "+check3);
				APP_LOGS.info("Volume down Key is not pressed sucessfully");				
			}				
			customWait(5000);
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_MENU);
			minWait();
			if(isElementExist(Locators_Sanity.menu_Notification)) {
				check4=true;
				APP_LOGS.info("check4: "+check4);
				APP_LOGS.info("Home Key is pressed succesfully.");				
			}else{
				APP_LOGS.info("check4: "+check4);
				APP_LOGS.info("Home Key is not pressed sucessfully.");				
			}

			if((check1)&&(check2)&&(check3)&&(check4)){
				check=true;
				APP_LOGS.info("Validated Pressed Hard Key sucessfully ");			
				sf.assertTrue(check, "Validation is Pass");
			}else{
				APP_LOGS.info("Not Validated Pressed Hard Key sucessfully ");					
				sf.fail();
			}
		} catch (Exception e) {
			APP_LOGS.info("No Element found.");
			sf.fail();
		}
		sf.assertAll();
	}

	public void clearCallLogs() throws InterruptedException {
		try {
			if(isElementExist(Locators_Sanity.dailedNum)) {
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_MENU);
				APP_LOGS.info("Menu is clicked");
				minWait();
				for(int i=1; i<=10; i++) {
					if(isElementExist(Locators_Sanity.phone_DeleteCallLog)) {
						clickBtn(Locators_Sanity.phone_DeleteCallLog);
						minWait();
						clickBtn(Locators_Sanity.phone_ClearOptn);
						APP_LOGS.info(" Call logs cleared");
						minWait();
						break;
					}
					else {
						customWait(200);
						aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
						continue;
					}
				}
			}
			//aDriver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().className(\"android.widget.ScrollView\")).scrollIntoView(new UiSelector().text(\"Clear all call log\"))");  

		} catch (NoSuchElementException e) {
			APP_LOGS.info("No Element found.");
			e.printStackTrace();
			test.log(LogStatus.ERROR, "No Such Element Found");
		}
	}

	public void callHistoryDetails() throws InterruptedException, IOException	{
		/*
		 * Validates call history details.
		 */
		try {
			//==========outgoing call==========
			dailCallUsingDailPad(refNum);
			customWait(3000);
			endcall();
			customWait(2000);

			//==========Incoming call==========
			make_Call_from_RefDev();
			customWait(3000);
			recieve_Call_PrimaryDev();
			customWait(5000);
			endCall_RefDevice();
			customWait(3000);
			//==========Missed call===========
			make_Call_from_RefDev();
			customWait(5000);
			//endCall_RefDevice();
			missedCall_RefDev();
			customWait(3000);
			//==========declined call=========
			make_Call_from_RefDev();
			customWait(3000);
			endCall_PIDDevice();
			customWait(2000);
			endCall_RefDevice();

			minWait();
			//aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
			//minWait();
			navigateToApplication("Phone");
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
			minWait();
			validateCallHistoryDeclinedAndMissed();
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
			minWait();
			validateCallHistoryIncomingAndOutgoing();
		}catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "Element Not Found");
		}
	}

	public void dailCallUsingDailPad(String dailNum) throws InterruptedException, IOException {

		try {
			dailNumber(dailNum);

			try {
				for(int j=1;j<=100;j++){
					Process child = null;
					if (r_b_No.contains("8A.")) {
						child=Runtime.getRuntime().exec("adb -s "+r_Id+" shell service call telecom 27");
					} else if(r_b_No.contains("3A.")) {
						child=Runtime.getRuntime().exec("adb -s "+r_Id+" shell service call telecom 30");
					}					
					InputStream lsOut = child.getInputStream();
					InputStreamReader r = new InputStreamReader(lsOut);
					BufferedReader in = new BufferedReader(r);
					String  value=in.readLine();
					System.out.println(value);
					if(value.contains("00000001")) {
						System.out.println("Phone is ringing so accepting call.");
						Thread.sleep(3000);
						Runtime.getRuntime().exec("adb -s "+r_Id+" shell input keyevent 5");
						break;
					}else {
						continue;
					}
				}
			}catch(Exception e) {
				Thread.sleep(2000);
				Runtime.getRuntime().exec("adb -s "+r_Id+" shell input keyevent 5");
				Thread.sleep(2000);
			}

			APP_LOGS.info("Number dailed is: "+dailNum);
		}catch (NoSuchElementException e) {
			APP_LOGS.info("Selected Page is not displayed.");
			e.printStackTrace();
		}
	}

	public void make_Call_from_RefDev() throws InterruptedException, IOException {

		try {
			minWait();
			Runtime.getRuntime().exec("adb -s "+r_Id+" shell am start -a android.intent.action.CALL -d tel:"+pryNum);
			minWait();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void dailNumber(String dailNum) throws IOException, InterruptedException {
		minWait();
		launch_an_app("phone");
		customWait(2000);
		Runtime.getRuntime().exec("adb -s "+p_Id+" shell am start -a android.intent.action.CALL -d tel:"+AllQA.REFERENCEDEVMDN);
	}

	public void recieve_Call_PrimaryDev() throws IOException, InterruptedException {

		try {
			for(int j=1;j<=100;j++){
				Process child = null;
				if (p_b_No.contains("8A.")) {
					System.out.println("XP8");
					child=Runtime.getRuntime().exec("adb -s "+p_Id+" shell service call telecom 27");

				} else if(p_b_No.contains("3A.")) {
					System.out.println("XP3");
					child=Runtime.getRuntime().exec("adb -s "+p_Id+" shell service call telecom 30");
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
				}else {
					continue;
				}
			}
		}catch(Exception e) {
			Thread.sleep(2000);
			Runtime.getRuntime().exec("adb -s "+p_Id+" shell input keyevent 5");
			Thread.sleep(2000);
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

	public void missedCall_RefDev() throws InterruptedException, IOException {
		try {
			for(int j=1;j<=100;j++){
				Process child = null;
				if (p_b_No.contains("8A.")) {
					System.out.println("XP8");
					child=Runtime.getRuntime().exec("adb -s "+p_Id+" shell service call telecom 27");

				} else if(p_b_No.contains("3A.")) {
					System.out.println("XP3");
					child=Runtime.getRuntime().exec("adb -s "+p_Id+" shell service call telecom 30");
				}					
				InputStream lsOut = child.getInputStream();
				InputStreamReader r = new InputStreamReader(lsOut);
				BufferedReader in = new BufferedReader(r);
				String  value=in.readLine();
				System.out.println(value);
				minWait();
				if(value.contains("00000001")) {
					System.out.println("Phone is ringing so ending call in reference device.");

					Runtime.getRuntime().exec("adb -s "+r_Id+" shell input keyevent 6");
					break;
				}else {
					continue;
				}
			}
		}catch(Exception e) {
			Thread.sleep(2000);
			Runtime.getRuntime().exec("adb -s "+r_Id+" shell input keyevent 6");
			Thread.sleep(2000);
		}
	}


	public void validateCallHistoryDeclinedAndMissed() throws InterruptedException, IOException
	{
		/*
		 * Validate call history
		 */
		SoftAssert SA = new SoftAssert();
		try {
			if(isElementExist(Locators_Sanity.missedCall) && isElementExist(Locators_Sanity.declinedCall))
			{
				check = true;
				APP_LOGS.info("Call History with declined call and missed call verified sucessfully");
				test.log(LogStatus.INFO, "Call History with declined call and missed call verified sucessfully");
				SA.assertTrue(check, "Call History with declined call and missed call verified sucessfully");	
			}else {
				APP_LOGS.info("Call History with declined call and missed call not verified");
				test.log(LogStatus.ERROR, "Call History with declined call and missed call not verified");
				SA.fail("Call History with declined call and missed call not verified");
			}	
		}catch (NoSuchElementException e) {
			e.printStackTrace();
			APP_LOGS.info("Element Not Found");
			test.log(LogStatus.ERROR, "Element Not Found");
			SA.fail();
		}
		SA.assertAll();
	}

	public void endcall() throws InterruptedException {
		/*
		 * disconnect call
		 */
		Thread.sleep(5000);
		aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_ENDCALL);
		Thread.sleep(3000); 
	}

	public void validateCallHistoryIncomingAndOutgoing() throws InterruptedException, IOException
	{
		/*
		 * Validate call history.
		 */
		SoftAssert SA = new SoftAssert();
		try {
			if(isElementExist(Locators_Sanity.outgoingCall)&&isElementExist(Locators_Sanity.incomingCall)) {
				check = true;
				APP_LOGS.info("Call History with incoming call and outgoing call verified sucessfully");
				test.log(LogStatus.INFO, "Call History with incoming call and outgoing call verified sucessfully");
				SA.assertTrue(check, "Call History with incoming call and outgoing call verified sucessfully");	
			}else {
				APP_LOGS.info("Call History with incoming call and outgoing call not verified");
				test.log(LogStatus.ERROR, "Call History with incoming call and outgoing call not verified");
				SA.fail("Call History with incoming call and outgoing call not verified");
			}	
		}catch (NoSuchElementException e) {
			e.printStackTrace();
			APP_LOGS.info("Element Not Found");
			test.log(LogStatus.ERROR, "Element Not Found");
			SA.fail();
		}
		SA.assertAll();
	}

	public void endCall_PIDDevice() throws InterruptedException, IOException {

		try {
			for(int j=1;j<=100;j++){
				Process child = null;
				if (p_b_No.contains("8A.")) {
					System.out.println("XP8");
					child=Runtime.getRuntime().exec("adb -s "+p_Id+" shell service call telecom 27");

				} else if(p_b_No.contains("3A.")) {
					System.out.println("XP3");
					child=Runtime.getRuntime().exec("adb -s "+p_Id+" shell service call telecom 30");
				}					
				InputStream lsOut = child.getInputStream();
				InputStreamReader r = new InputStreamReader(lsOut);
				BufferedReader in = new BufferedReader(r);
				String  value=in.readLine();
				System.out.println(value);
				minWait();
				if(value.contains("00000001")) {
					System.out.println("Phone is ringing so declining call.");

					Runtime.getRuntime().exec("adb -s "+p_Id+" shell input keyevent 6");
					break;
				}else {
					continue;
				}
			}
		}catch(Exception e) {
			Thread.sleep(2000);
			Runtime.getRuntime().exec("adb -s "+r_Id+" shell input keyevent 6");
			Thread.sleep(2000);
		}
		try {
			minWait();
			Runtime.getRuntime().exec("adb -s "+p_Id+" shell input keyevent 6");
			Thread.sleep(1000);
		} catch (Exception e) {
		}
	}

	public void navigateTo_NewMessage() throws InterruptedException, IOException {
		SoftAssert sf = new SoftAssert();
		try {
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_MENU);
			minWait();
			clickBtn(Locators_Sanity.New_msgOptn);
			minWait();
		} catch (Exception e) {
			e.printStackTrace();
			sf.fail();
			test.log(LogStatus.ERROR, "Didn't navigated to New Message Window");
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
			clickBtn(Locators_Sanity.attachOthers);
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
			clickBtn(Locators_Sanity.photoCaptureIcon);
			customWait(2000);
			clickBtn(Locators_Sanity.OK);
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
			APP_LOGS.info("Attachment added sucessfully.");
			sf.assertTrue(check, "Attachment added sucessfully, validation PASS.");
		} else {
			sf.fail();
			test.log(LogStatus.ERROR, "Error in adding the Attachment to SMS.");
		}
		sf.assertAll();		
	}

	public void enter_ToField(String cellNo) throws InterruptedException {
		SoftAssert sf = new SoftAssert();
		try {
			changeToNumberMode();
			minWait();
			enterTextToInputField(Locators_Sanity.toField_NewMessage, cellNo);
			minWait();
		} catch (Exception e) {
			e.printStackTrace();
			sf.fail();
			test.log(LogStatus.ERROR, "Error in entering number to ToField");
		}
		sf.assertAll();
	}

	public void type_Message(String typeMessage) throws InterruptedException {
		SoftAssert sf = new SoftAssert();
		try {
			minWait();
			enterTextToInputField(Locators_Sanity.typeMsg_Field, typeMessage);
		} catch (Exception e) {
			e.printStackTrace();
			sf.fail();
			test.log(LogStatus.ERROR, "Error in Typing a Message.");
		}
		sf.assertAll();
	}

	public void send() throws InterruptedException {
		/*
		 * send sms 
		 */		
		customWait(2000);
		aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_MENU);
		minWait();
		aDriver.pressKeyCode(AndroidKeyCode.ENTER);		
		customWait(1000);
		aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);	
	}

	public void validateMsgSent(SoftAssert s) throws InterruptedException {
		/*
		 * validate sent message to new num and saved contact
		 */
		try {
			minWait();		
			customWait(8000);
			WebDriverWait wait = new WebDriverWait(aDriver, 40);
			wait.until(ExpectedConditions.visibilityOf(Locators_Sanity.now_Text));
			minWait();
			if (isElementExist(Locators_Sanity.now_Text)) {
				check=true;
				APP_LOGS.info("Message Sent sucessfully");
				s.assertTrue(check, "Validation is Pass");
				test.log(LogStatus.PASS, "Message sent successfully");
			} else {
				APP_LOGS.info("Message DIDN'T Sent");
				s.fail();
				test.log(LogStatus.ERROR, "Failed to send SMS,Validation Failed ");
			}
		} catch (Exception e) {
			APP_LOGS.info("No Element found.");
			e.printStackTrace();
			test.log(LogStatus.ERROR, " No Such Element Found");
			s.fail();
		}
	}

	public void press_BackKey() throws InterruptedException {
		minWait();
		aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
		minWait();
	}

	public void changeToNumberMode() throws InterruptedException {

		minWait();
		Locators_Sanity.toField_NewMessage.sendKeys("123");
		customWait(1500);
		String text = Locators_Sanity.toField_NewMessage.getText();
		System.out.println(text);
		if(!text.equals("123")) {
			for (int i = 0; i < 3; i++) {
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_POUND);
				minWait();
			}
		}
		Locators_Sanity.toField_NewMessage.clear();
	}


	public void deleteMsg() throws InterruptedException {
		/*
		 * Delete Selected Msg
		 */
		try {
			minWait();	
			clickMenuAndElement(Locators_Sanity.selectOptInMsg);
			minWait();
			clickMenuAndElement(Locators_Sanity.deleteAllThreadsInMsg);
			minWait();
			clickBtn(Locators_Sanity.contact_DeleteBtn);
			customWait(2000);
		} catch (org.openqa.selenium.NoSuchElementException e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "No Such Element Found");
			Assert.fail();
		}
	}

	public void validateDeleteSMS(String str) throws InterruptedException {
		//validate after deletion

		if (validate_presenceOfElement(Locators_Sanity.noConversations,"NoConversations")) {
			check = true;
			test.log(LogStatus.INFO, "Able to delete " +str +" verified");
			Assert.assertTrue(check);
		}
		else {
			test.log(LogStatus.ERROR, str+" is not able to delete");
			Assert.fail();
		}
	}

	public void disableCellularData() throws InterruptedException {
		try {
			minWait();
			scrollToText("Network & Internet");
			minWait();
			if(isElementExist(Locators_Sanity.MobileNetwrks)) {
				minWait();
				clickBtn(Locators_Sanity.MobileNetwrks);
				APP_LOGS.info("Selected Mobile Networks");
				minWait();
				disableFeature(Locators_Sanity.enabled_Data,Locators_Sanity.disabled_Data);
				minWait();
				clickBtn(Locators_Sanity.okBtn);
				minWait();
			} else {
				clickBtn(Locators_Sanity.dataUsageOptn);
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
				minWait();
				disableFeature(Locators_Sanity.cellular_DataON,Locators_Sanity.cellular_DataOFF);
				minWait();
				clickBtn(Locators_Sanity.okBtn);
				minWait();
			}
		} catch (Exception e) {
			APP_LOGS.info("No Element found.");
			minWait();
		}
	}

	public void disableCellularData_SP() throws InterruptedException {
		try {
			minWait();
			scrollToText("Network & Internet");
			minWait();
			if(isElementExist(Locators_Sanity.MobileNetwrks)) {
				minWait();
				clickBtn(Locators_Sanity.MobileNetwrks);
				APP_LOGS.info("Selected Mobile Networks");
				minWait();
				disableFeature(Locators_Sanity.enabled_DataSP,Locators_Sanity.disabled_DataSP);
				minWait();
				clickBtn(Locators_Sanity.okBtn);
				minWait();
			}
			else {
				clickBtn(Locators_Sanity.dataUsageOptn);
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
				minWait();
				disableFeature(Locators_Sanity.cellular_DataON,Locators_Sanity.cellular_DataOFF);
				minWait();
				clickBtn(Locators_Sanity.okBtn);
				minWait();
			}


		} catch (Exception e) {
			APP_LOGS.info("No Element found.");
			Assert.fail();
			minWait();	
		}
	}

	public void validateDisableEnableWiFi() throws InterruptedException, IOException {
		/*
		 * disable enable wiFi
		 */
		boolean check1 = false, check2 = false;
		SoftAssert sf = new SoftAssert();
		try {
			launch_an_app("settings");
			minWait();
			scrollToText("Network & Internet");
			minWait();
			clickBtn(Locators_Sanity.wifi);
			minWait();
			customWait(2000);
			disableFeature(Locators_Sanity.enabled,Locators_Sanity.disabled);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			launch_an_app("browser");
			customWait(4000);
			//Locators_Sanity.CANCEL_Button.click();
			//aDriver.pressKeyCode(AndroidKeyCode);
			//customWait(3000);
			//enterUrl("www.google.com");
			if(isElementExist(Locators_Sanity.NoNewtwrkErroeMsg)||isElementExist(Locators_Sanity.webpageNotavailable)||isElementExist(Locators_Sanity.CANCEL_Button)) {
				check1 =true;
				APP_LOGS.info("check1: "+check1);
				APP_LOGS.info("Disable the WiFi is sucessful");
				test.log(LogStatus.PASS, "Disable the WiFi is sucessful");
			}else {
				APP_LOGS.info("check1: "+check1);
				APP_LOGS.info("Disable the WiFi is not sucessful");
				test.log(LogStatus.FAIL," Disable the WiFi is not sucessful");
			}
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			minWait();
			launch_an_app("settings");
			minWait();
			scrollToText("Network & Internet");
			minWait();
			clickBtn(Locators_Sanity.wifi);
			minWait();
			enableFeature(Locators_Sanity.enabled,Locators_Sanity.disabled,Locators_Sanity.switch_Title);
			minWait();
			customWait(4000);
			clearRecentApps();
			launch_an_app("browser");
			customWait(4000);
			enterUrl("www.google.com");
			customWait(4000);
			if((isElementExist(Locators_Sanity.SearchIcon_Google))||(isElementExist(Locators_Sanity.Searchenter_Google))) {
				check2 =true;
				APP_LOGS.info("check2: "+check2);
				APP_LOGS.info("Enable the WiFi connection is sucessful");
				test.log(LogStatus.PASS, "Enable the WiFi connection is sucessful");
			}else{
				APP_LOGS.info("check2: "+check2);
				APP_LOGS.info("Enable WiFi connection is not sucessful");
				test.log(LogStatus.FAIL, "Enable WiFi connection is not sucessful");
			}

			if((check1) && (check2)) {
				check = true;
				APP_LOGS.info(" Turn ON and OFF feature is verified");
				test.log(LogStatus.PASS, " Browsing with WiFi feature is verified");
				sf.assertTrue(check, " ");
			}
			else {
				APP_LOGS.info(" check1"+check1+"check2"+check2);
				test.log(LogStatus.FAIL, "No Such Element Found");
				APP_LOGS.info(" Browsing with WiFi  is not verified");
				sf.fail();
			}
			minWait();	
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			minWait();	
			minWait();	
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			minWait();	
		} catch (SocketException e1) {
			e1.printStackTrace();

		}

		catch (Exception e) {
			APP_LOGS.info("No Element found.");
			sf.fail();
		}
		sf.assertAll();
	}

	public void back() throws InterruptedException {
		/* 
		 * perform back by back key
		 */
		minWait();	
		aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
		minWait();	
		aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
	}

	public void selectMoreOptions() throws InterruptedException, IOException, ParseException {
		/*
		 *select more option in settings
		 */	aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_HOME);
		 String Uid=JsonFileReaderAndWriter.primaryDevIdReader();
		 //		customWait(4000);
		 try {
			 Runtime.getRuntime().exec("adb -s "+Uid+" shell am start -n com.android.settings/com.android.settings.Settings");
			 minWait();
			 APP_LOGS.info("Launched Settings icon sucessfully");
			 for (int iter = 1; iter <= 9; iter++) {
				 if (isElementExist(Locators_Sanity.moreOptn)) {
					 APP_LOGS.info("More Option is displayed sucessfully");
					 minWait();	
					 clickBtn(Locators_Sanity.moreOptn);
					 break;					
				 } else {
					 aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
					 continue;					
				 }
			 }clickBtn(Locators_Sanity.date_time);
		 } catch (Exception e) {
			 APP_LOGS.info("More Option is not displayed.");
			 test.log(LogStatus.ERROR, "No Such Element Found");
			 minWait();	
			 Assert.fail();
		 }

	}

	public void validateEnabledisableFlightmode() throws InterruptedException {
		/*
		 * Validates enable and disable flight mode with phone settings
		 */	
		//		customWait(1000);

		boolean check1 = false, check2 = false;
		SoftAssert sf = new SoftAssert();
		try {
			if (isElementExist(Locators_Sanity.Flightmode)) {
				APP_LOGS.info("Airplane mode is displayed sucessfully");
				minWait();	
				clickBtn(Locators_Sanity.AiroplaneSwitch);
				minWait();	
			}
			if (Locators_Sanity.Flightmode.isEnabled()) {
				APP_LOGS.info("Airplane mode is enabled sucessfully");
				check1 = true;
				minWait();	
				clickBtn(Locators_Sanity.AiroplaneSwitch);
				minWait();	
				minWait();	
			}				
			if (Locators_Sanity.Flightmode.isSelected()) {
				minWait();	
				APP_LOGS.info("Airplane mode is enabled ");
			}
			else {
				minWait();	
				APP_LOGS.info("Airplane mode is disabled sucessfully");
				check2 = true;
				minWait();	
			}
			if((check1)&&(check2)){
				check= true;
				APP_LOGS.info("Enable and disable of Airplane mode with phone settings.");
				test.log(LogStatus.INFO, "Enable and disable of Airplane mode with phone settings validated sucessfully");
				APP_LOGS.info("TC is Passed.");
				sf.assertTrue(check, "Validation is Pass");
			}else{
				APP_LOGS.info("test case is failed");
				test.log(LogStatus.ERROR, "Enable and disable of Airplane mode with phone settings, Unsucessfully");
				sf.fail();
			}
			minWait();	
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			minWait();	
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
		} catch (Exception e) {
			APP_LOGS.info("No Element found.");
			e.printStackTrace();
			test.log(LogStatus.ERROR, "No Such Element Found");
			sf.fail();
		}
		sf.assertAll();
	}

	public void validateDisable(WebElement element) throws InterruptedException {
		/*
		 * Validate data roaming is enable 
		 */
		SoftAssert S1 = new SoftAssert();
		try {
			clickBtn(Locators_Sanity.OK_Btn);
			minWait();
			if(element.isEnabled()) {
				check = true;
				test.log(LogStatus.INFO, " Location is disabled");
				S1.assertTrue(check, " ");
			}
			else {
				test.log(LogStatus.ERROR, " Location is not disabled");
				S1.fail();
			}
		} catch (NoSuchElementException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			test.log(LogStatus.ERROR, "No Such Element Found");
			S1.fail();

		}
		S1.assertAll();
	}

	public void validateEnable(WebElement element) {
		/*
		 * Validate data roaming is enable 
		 */
		SoftAssert S1 = new SoftAssert();
		try {
			if(element.isEnabled()) {
				check = true;
				test.log(LogStatus.INFO, "Location is enabled");
				S1.assertTrue(check, " ");
			}
			else {
				test.log(LogStatus.ERROR, " Location is not enabled");
				S1.fail();
			}
		} catch (NoSuchElementException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			test.log(LogStatus.ERROR, "No Such Element Found");
			S1.fail();

		}
		S1.assertAll();
	}

	public void selectQuickSettingPanel() throws InterruptedException {
		/*
		 * Select quick Setting panel
		 */
		try {
			minWait();	
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_HOME);
			minWait();	
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			minWait();	
			for(int i=1; i<=8; i++) {
				if(isElementExist(Locators_Sanity.quickToggle)) {
					APP_LOGS.info("In Quick Settings");
					minWait();
					break;					
				} else {
					aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_LEFT);
					continue;					
				}	
			}
		} catch (Exception e) {
			APP_LOGS.info("More Option is not displayed.");
			test.log(LogStatus.ERROR, "No Such Element Found");
			minWait();	
			Assert.fail();
		}
	}

	public void validateQuickSetting(String feature) {

		/*
		 * validate enabling and disabling the wifi, bluetooth, and airplane mode.
		 */
		boolean check1 = false, check2 = false, check = false;
		SoftAssert quickSetting = new SoftAssert();
		String status;

		APP_LOGS.info("Inside validateQuickSetting"); 

		try {
			minWait();
			scrollText(feature);
			minWait();
			switch (feature) {
			case "Wi-Fi":
				status = Locators_Sanity.Wifi_Status.getText();
				System.out.println(status);
				minWait();
				if (status.equalsIgnoreCase("ON")) {
					APP_LOGS.info("Switch is Enabled initially"); 
					clickBtn(Locators_Sanity.Wifi_Status);
					customWait(3000);
					check1 = true;
				}
				else {
					APP_LOGS.info("Switch is disabled initially");
					clickBtn(Locators_Sanity.Wifi_Status);
					customWait(3000);
					check2 = true;
				}
				status = Locators_Sanity.Wifi_Status.getText();
				System.out.println(status);
				minWait();
				if (status.equalsIgnoreCase("ON")) {
					APP_LOGS.info("Switch is Enabled "); 
					clickBtn(Locators_Sanity.Wifi_Status);
					customWait(3000);
					check1 = true;
				}
				else {
					APP_LOGS.info("Switch is disabled ");
					clickBtn(Locators_Sanity.Wifi_Status);
					customWait(3000);
					check2 = true;
				}

				System.out.println("check 1 =" +check1+"check 2="+ check2);
				if (check1==true && check2==true) {
					check = true;
					APP_LOGS.info("Wifi is enabled and disabled "); 

				}
				quickSetting.assertTrue(check, "");
				break;



			case "Bluetooth":{
				status = Locators_Sanity.Bt_Status.getText();
				System.out.println(status);
				minWait();
				if (status.equalsIgnoreCase("ON")) {
					APP_LOGS.info("Switch is Enabled initially"); 
					clickBtn(Locators_Sanity.Bt_Status);
					customWait(3000);
					check1 = true;
				}
				else {
					APP_LOGS.info("Switch is disabled initially");
					clickBtn(Locators_Sanity.Bt_Status);
					customWait(3000);
					check2 = true;
				}
				status = Locators_Sanity.Bt_Status.getText();
				System.out.println(status);
				minWait();
				if (status.equalsIgnoreCase("ON")) {
					APP_LOGS.info("Switch is Enabled "); 
					clickBtn(Locators_Sanity.Bt_Status);
					customWait(3000);
					check1 = true;
				}
				else {
					APP_LOGS.info("Switch is disabled ");
					clickBtn(Locators_Sanity.Bt_Status);
					customWait(3000);
					check2 = true;
				}

				System.out.println("check 1 =" +check1+"check 2="+ check2);
				if (check1==true && check2==true) {
					check = true;
					APP_LOGS.info("Bluetoooth is enabled and disabled "); 

				}
				quickSetting.assertTrue(check, "");
				break;
			}

			case "Airplane mode":{
				status = Locators_Sanity.airplaneMode_Status.getText();
				System.out.println(status);
				minWait();
				System.out.println();
				if (status.equalsIgnoreCase("ON")) {
					APP_LOGS.info("Switch is Enabled initially"); 
					clickBtn(Locators_Sanity.airplaneMode_Status);
					customWait(3000);
					check1 = true;
				}
				else {
					APP_LOGS.info("Switch is disabled initially");
					clickBtn(Locators_Sanity.airplaneMode_Status);
					customWait(5000);
					check2 = true;
				}
				//selectQuickSettingPanel();
				//minWait();
				status = Locators_Sanity.airplaneMode_Status.getText();
				System.out.println(status);
				minWait();
				if (status.equalsIgnoreCase("ON")) {
					APP_LOGS.info("Switch is Enabled "); 
					clickBtn(Locators_Sanity.airplaneMode_Status);
					customWait(3000);
					check1 = true;
				}
				else {
					APP_LOGS.info("Switch is disabled ");
					clickBtn(Locators_Sanity.airplaneMode_Status);
					customWait(3000);
					check2 = true;
				}

				System.out.println("check 1 =" +check1+"check 2="+ check2);
				if (check1==true && check2==true) {
					check = true;
					APP_LOGS.info("Airplane mode is enabled and disabled "); 

				}
				quickSetting.assertTrue(check, "");
				customWait(7000);
				break;
			}
			}
		}

		catch(Exception e) {
			APP_LOGS.info("Element not found while enabling and disabling quick setting "); 
			Assert.fail();
			test.log(LogStatus.FAIL,"element not found");
		}
		quickSetting.assertAll();
	}

	public void validateEnableDisable(WebElement app,String str,WebElement enabled,WebElement disabled) throws InterruptedException {
		/*
		 * validate enabling and disabling the wifi, bluetooth, and airoplane mode.
		 */
		boolean check1 = false, check2 = false;
		SoftAssert sf = new SoftAssert();
		try {
			minWait();	
			clickBtn(app);
			minWait();
			if (!isElementExist(enabled)) {
				minWait();
				clickBtn(disabled);
				APP_LOGS.info("Switch is Enabled");                     
				minWait();
			}
			String get_enable = Locators_Sanity.enable.getText();	
			//			System.out.println(get_enable);
			minWait();	
			if(get_enable.equalsIgnoreCase("ON")) {
				check1 = true;
				APP_LOGS.info("enabled is sucessful");
			}minWait();	
			clickBtn(app);	
			minWait();
			if (!isElementExist(disabled)) {
				minWait();
				clickBtn(enabled);
				APP_LOGS.info("Switch is Disabled");                     
				minWait();
			}
			minWait();	
			String get_enable2 = Locators_Sanity.Wifi_Status.getText();	
			//			System.out.println(get_enable2);
			minWait();	
			if(get_enable2.equalsIgnoreCase("OFF")) {
				minWait();	
				check2 = true;
				APP_LOGS.info("Disabled is sucessful");
			}
			if((check1)&&(check2)){
				check= true;
				APP_LOGS.info(" Enabled and disabled with quick settings.");
				test.log(LogStatus.INFO, str + " Enabled and disabled with quick settings.");
				sf.assertTrue(check, "Validation is pass");
			}else{
				APP_LOGS.info("test case is failed");
				test.log(LogStatus.ERROR, " Validation Failed, Enable and disable with quick settings.");
				sf.fail();
			}
			minWait();	
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
		} catch (Exception e) {
			APP_LOGS.info("No Element found.");
			e.printStackTrace();
			test.log(LogStatus.ERROR, "No Such Element Found");
			sf.fail();			
		}
		sf.assertAll();
	}

	public void validate_phone_Charging() throws InterruptedException{
		//			navigate_to_NotificationScreen();
		//			ScrollToElement(Locators_Sanity.settings_frame, "Battery");
		try {
			minWait();
			for (int i=1; i<10; i++) {
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
				minWait();
			}
			minWait();
			clickBtn(Locators_Sanity.AboutPhone);
			minWait();
			clickBtn(Locators_Sanity.status);
			customWait(1000);
			if(isElementExist(Locators_Sanity.Charging_OverUSB)||isElementExist(Locators_Sanity.Full_OverUSB)){
				APP_LOGS.info("Charging over USB in Battery Status option is displayed");
				test.log(LogStatus.INFO, "Charging over USB in Battery Status option is displayed");
				check=true;
				Assert.assertTrue(check);
			}else{
				APP_LOGS.info("Charging over USB in Battery Status option NOT is displayed");
				test.log(LogStatus.ERROR, "Charging over USB in Battery Status option NOT is displayed");
				Assert.fail();
			}	
			back();
			minWait();
			back();

		} catch (NoSuchElementException e) {
			e.printStackTrace();
			APP_LOGS.info("No Element found.");
			Assert.fail();
		}
	}

	public void enableData() throws InterruptedException {
		/*
		 * enable data
		 */
		try {
			//this perform disable WiFi
			launch_an_app("settings");
			minWait();
			//clickBtn(Locators_DevSanity.wifi);
			//minWait();
			//aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			scrollToText("Network & Internet");
			//customWait(2000);
			//scrollToText("Mobile network");
			//disableFeature(Locators_DevSanity.enabled,Locators_DevSanity.disabled);
			//minWait();
			//enable data
			minWait();
			if(isElementExist(Locators_DevSanity.MobileNetwrks)) {
				minWait();
				clickBtn(Locators_DevSanity.MobileNetwrks);
				APP_LOGS.info("Selected Mobile Networks");
				minWait();				
				enableSwitch(Locators_DevSanity.enabled_Data,Locators_DevSanity.disabled_Data,Locators_DevSanity.switch_widget);
				minWait();
			} else {
				clickBtn(Locators_DevSanity.dataUsageOptn);
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
				minWait();
				if (!isElementExist(Locators_DevSanity.cellular_DataON)) {
					minWait();
					clickBtn(Locators_DevSanity.cellular_DataOFF);
					APP_LOGS.info("Switch is Enabled");
					minWait();
				}
				minWait();
			}
		} catch (Exception e) {
			APP_LOGS.info("No Element found.");
			test.log(LogStatus.ERROR, "No Such Element Found");
			minWait();
		}
	}

	public void enableData_SP() throws InterruptedException {
		/*
		 * enable data
		 */
		try {
			//this perform disable WiFi
			launch_an_app("settings");
			minWait();
			//clickBtn(Locators_DevSanity.wifi);
			//minWait();
			//aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			scrollToText("Network & Internet");
			//customWait(2000);
			//scrollToText("Mobile network");
			//disableFeature(Locators_DevSanity.enabled,Locators_DevSanity.disabled);
			//minWait();
			//enable data
			minWait();
			if(isElementExist(Locators_DevSanity.MobileNetwrks)) {
				minWait();
				clickBtn(Locators_DevSanity.MobileNetwrks);
				APP_LOGS.info("Selected Mobile Networks");
				minWait();				
				enableSwitch(Locators_Sanity.enabled_DataSP,Locators_Sanity.disabled_DataSP,Locators_DevSanity.switch_widget);
				minWait();
			}
			else {
				clickBtn(Locators_DevSanity.dataUsageOptn);
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
				minWait();
				if (!isElementExist(Locators_DevSanity.cellular_DataON)) {
					minWait();
					clickBtn(Locators_DevSanity.cellular_DataOFF);
					APP_LOGS.info("Switch is Enabled");                     
					minWait();
				}
				minWait();
			}
		} catch (Exception e) {
			APP_LOGS.info("No Element found.");
			test.log(LogStatus.ERROR, "No Such Element Found");
			Assert.fail();
			minWait();	
		}
	}

	public void enableAirplaneMode() {
		SoftAssert SA = new SoftAssert();
		try{
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_HOME);
			minWait();
			navigateUsingText("Settings");
			minWait();
			scrollToText("Network & Internet");
			minWait();
			scrollToText("Airplane mode");
			minWait();
		}catch(Exception e){
			SA.fail();
		}
	}

	public void validateAirplaneSB() throws InterruptedException, IOException, ParseException {
		/*
		 * Airplane enable and disable
		 */
		Boolean b = false, check1 = false, check2 = false;
		SoftAssert sf = new SoftAssert();
		String Uid=JsonFileReaderAndWriter.primaryDevIdReader();
		try {
			b = enterUrl("www.google.com");
			sf.assertTrue(b, "Url is entered properly");
			if (isElementExist(Locators_DevSanity.webpageNotavailable)) {
				check1 = true;
				APP_LOGS.info("flight mode is enabled sucessfully"+ check1);
				minWait();	
			}
			customWait(1000);
			//Runtime.getRuntime().exec("adb -s "+Uid+" shell am start -a android.settings.AIRPLANE_MODE_SETTINGS");
			//minWait();
			enableAirplaneMode();
			//disableSwitch(Locators_DevSanity.disabled_Airplane,Locators_DevSanity.enabled_Airplane,Locators_DevSanity.switch_widget );		
			customWait(9000);
			launch_an_app("browser");	
			customWait(2000);
			b = enterUrl("www.google.com");
			sf.assertTrue(b, "Url is entered properly");
			customWait(5000);
			if ((isElementExist(Locators_DevSanity.SearchIcon_Google))||(isElementExist(Locators_DevSanity.Searchenter_Google))) {
				APP_LOGS.info("flight mode is disabled sucessfully");
				check2 = true;
				minWait();	
			}			

			System.out.println("check1"+ check1 +"check2"+ check2);
			if((check1)&&(check2)){
				check= true;
				APP_LOGS.info("Enable and disable of Airplane mode with phone settings.");
				test.log(LogStatus.INFO, "Enable ansd disable of Airplane mode with phone settings validated Successful");
				APP_LOGS.info("TC is Passed.");
				sf.assertTrue(check, "Validation is Pass");
			}else{
				APP_LOGS.info("test case is failed");
				test.log(LogStatus.ERROR, "Enable ansd disable of Airplane mode with phone settings, Unsuccessful");
				sf.fail();
			}
			minWait();	
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			minWait();	
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
		} catch (Exception e) {
			APP_LOGS.info("No Element found.");
			e.printStackTrace();
			test.log(LogStatus.ERROR, "No Such Element Found");
			sf.fail();
		}
		sf.assertAll();
	}

	public void validateInternalStorage() throws InterruptedException {
		/*
		 * Validate internal storage
		 */

		boolean check1 = false, check2 = false;
		SoftAssert sf = new SoftAssert();
		try {
			minWait();
			if(isElementExist(Locators_Sanity.device_Storage)) {
				check1 = true;
				APP_LOGS.info("check1: " + check1);
				APP_LOGS.info("Internal_Storage_text Element is present");
				String getAlertText = Locators_Sanity.Storage_Optn.getText();
				APP_LOGS.info("internal_Storage_value_text: " + getAlertText);
			}else {
				APP_LOGS.info("check1: "+check1);
				APP_LOGS.info("Internal_Storage_text Element is not present");			
			}
			
			if(isElementExist(Locators_Sanity.total_Used_of_8GB)) {
				check2 = true;
				APP_LOGS.info("check2: " + check2);
				APP_LOGS.info("Internal_Storage_Available_bar Element is present");
				String getAlertText = Locators_Sanity.Available_Value_Txt.getText();
				APP_LOGS.info("internal_Storage_Available_value_text: " + getAlertText);
			}else {
				APP_LOGS.info("check2: "+check2);
				APP_LOGS.info("Internal_Storage_Available_bar Element is not present");			
			}
			
			if((check1)&&(check2)) {
				check=true;
				APP_LOGS.info("Internal Storage and available storage of the device is verified sucessfully.");							
				customWait(5000);
				test.log(LogStatus.PASS, "Internal Storage and available storage of the device is verified sucessfully.");
				sf.assertTrue(check, "Validation is Pass");
			}else {
				APP_LOGS.info("test case failed");			
				minWait();
				test.log(LogStatus.FAIL, " Internal Storage and available storage of the device is not verified.");
				sf.fail();
			}
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
		} catch (Exception e) {
			APP_LOGS.info("No Element found.");
			e.printStackTrace();
			sf.fail();
		}
		sf.assertAll();
	}		

	public void volumeUpKey() throws InterruptedException{
		/*
		 * Clicks volume button
		 */
		try {
			minWait();
			for(int i=0;i<9;i++){
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_VOLUME_UP);
			}
			validateVolumeKeys("Up", "writeEvent level_changed STREAM_SYSTEM 7");
		} catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "Element Not Found");
		}
	}

	public void validateVolumeKeys(String function,String searchstring) throws InterruptedException
	{
		/*
		 * validate volume keys up/down
		 */
		SoftAssert SA = new SoftAssert();
		try {
			boolean value = searchString(searchstring, "XP3_Sanity_034");
			minWait();
			if(value)
			{
				check = true;
				APP_LOGS.info("Volume Key "+ function +" verified sucessfully");
				test.log(LogStatus.INFO, "Volume Key "+ function +" verified sucessfully");
				SA.assertTrue(check, "Volume Key "+ function +" verified sucessfully");	
			}else {
				APP_LOGS.info("In call functionality "+ function +" not verified");
				test.log(LogStatus.ERROR, "In call functionality "+ function +" not verified");
				SA.fail("In call functionality "+ function +" not verified");
			}
		} catch (Exception e) {
			e.printStackTrace();
			APP_LOGS.info("Element Not Found");
			test.log(LogStatus.ERROR, "Element Not Found");
			SA.fail();
		}
	}

	public void navigateToPTTKey() throws InterruptedException
	{
		/*
		 * Navigate to programmable keys,clicks on Select PTT key App option
		 */
		try {
			minWait();
			scrollToText("System");
			minWait();
			scrollText("Programmable Key");
			minWait();
			clickBtn(Locators_Sanity.programmableKeyOpt);
			minWait();
			clickBtn(Locators_Sanity.selectPTTKeyOpt);
			minWait();
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR,"No Such Element Found");
		}						
	}

	public void selectApplicationAndLaunch() throws InterruptedException, IOException{
		/*
		 * select application for PTT key
		 */
		try {
			minWait();
			if(isElementExist(Locators_Sanity.contacts)){
				clickBtn(Locators_Sanity.contacts);
			}else{
				scrollText("Contacts");
				//scrollToElementWithDpadDown(Locators_Sanity.contacts);
				customWait(2000);
				clickBtn(Locators_Sanity.contacts);
				customWait(2000);
			}
			if(isElementExist(Locators_Sanity.PTTPopUp)){
				clickBtn(Locators_Sanity.okBtn);
				customWait(2000);
			}
			Runtime.getRuntime().exec("adb -s "+p_Id+" shell input keyevent --longpress KEYCODE_PTT");
			minWait();
		} catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR,"No Such Element Found");
		}
	}

	public void validateApplicationlaunch() throws InterruptedException
	{
		/*
		 * validate launched application 
		 */
		SoftAssert SA = new SoftAssert();
		try {
			customWait(2000);
			if (isElementExist(Locators_Sanity.contacts)) {
				check = true;
				APP_LOGS.info("Assigned contacts application launched succesfully");
				test.log(LogStatus.INFO,"Assigned contacts application launched succesfully");
				test.log(LogStatus.PASS, "Test case status is Passed");
				SA.assertTrue(check, "Assigned contacts application launched succesfully");

			} else {
				APP_LOGS.info("Application is not Launched");
				test.log(LogStatus.ERROR,"Application is not Launched");
				test.log(LogStatus.FAIL, "Test case status is Failed");
				SA.fail();
			}
		} catch (Exception e) {
			e.printStackTrace();
			APP_LOGS.info("Element Not Found");
			test.log(LogStatus.ERROR, "No Such Element Found");
			SA.fail();
		}
	}

	public void scrollToElementWithDpadDown(AndroidElement element)
	{
		/*
		 * Clicks down button till element is available
		 */
		do
		{
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
		}while(!isElementExist(element));
	}

	public void volumeDownKey() throws InterruptedException{
		/*
		 * Clicks volume button
		 */
		try {
			minWait();
			for(int i=0;i<8;i++){
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_VOLUME_DOWN);
			}
			validateVolumeKeys("Down", "writeEvent level_changed STREAM_SYSTEM 1");
		} catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "Element Not Found");
		}
	}

	public void validateHomeKey(SoftAssert s) throws InterruptedException, IOException
	{
		/*
		 * validate menu key by launching menu tray
		 */
		try {
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_HOME);
			minWait();
			validateKey(s,Locators_Sanity.settings, "Home");
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "No Such Element Found");
		}
	}

	public void validateGreen_Red_Key(SoftAssert s) throws InterruptedException, IOException
	{
		/*
		 * validate Green key and Red by Initiating call
		 */
		try {
			minWait();
			launch_an_app("phone");
			minWait();
			searchField(refNum);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_CALL);
			customWait(4000);
			validateKey(s,Locators_Sanity.dialingOpt, "Green");
			customWait(2000);
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_ENDCALL);
			minWait();
			validateKey(s,Locators_Sanity.recentCallsScreen, "Red");
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "No Such Element Found");
		}
	}

	public void searchField(String data) throws InterruptedException, IOException
	{
		/*
		 * Enters given data in search field
		 */
		customWait(4000);
		Runtime.getRuntime().exec("adb -s "+p_Id+" shell input text "+data);
		customWait(3000);
	}

	public void validateKey(SoftAssert s,AndroidElement element,String key) throws InterruptedException, IOException {
		/*
		 * validate menu key by launching menu tray.
		 */
		boolean check = false;
		try {
			if(isElementExist(element)){
				check = true;
				APP_LOGS.info(key+" key validated succesfully");
				test.log(LogStatus.PASS,key+" key validated succesfully");
				s.assertTrue(check, key+" key validated succesfully");
			}else if(isElementExist(Locators_Sanity.tools)){
				check = true;
				APP_LOGS.info(key+" key validated succesfully");
				test.log(LogStatus.PASS,key+" key validated succesfully");
				s.assertTrue(check, key+" key validated succesfully");
			}else {
				APP_LOGS.info("Failed to validate "+key+" key");
				test.log(LogStatus.FAIL,"Failed to validate "+key+" key");
				s.fail();
			}
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			APP_LOGS.info("Element Not Found");
			test.log(LogStatus.ERROR, "No Such Element Found");
			s.fail();
		}
	}

	public void validate_Menu_Back_Clear_Key(SoftAssert s) throws InterruptedException, IOException
	{
		/*
		 * validate Menu key,Back key and clear key in phone dialer application
		 */
		try {
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_MENU);
			minWait();
			validateKey(s,Locators_Sanity.sendMessageInDialer, "Menu");
			minWait();
			clickBtn(Locators_Sanity.sendMessageInDialer);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			minWait();
			validateKey(s,Locators_Sanity.recentCallsScreen, "Back");
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_CLEAR);
			minWait();
			validateKey(s,Locators_Sanity.deleteMsgPopUp,"Clear");
		} catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "No Such Element Found");
		}
	}

	public void validateRecentKey(SoftAssert s) throws InterruptedException, IOException
	{
		/*
		 * validate Recent key 
		 */
		try {
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_APP_SWITCH);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_MENU);
			minWait();
			validateKey(s,Locators_Sanity.removeAllOpt, "Recent");
			minWait();
			clickBtn(Locators_Sanity.removeAllOpt);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "No Such Element Found");
		}
	}


	public void validateNavigationKeys(SoftAssert s) throws InterruptedException, IOException
	{
		/*
		 * validate Recent key 
		 */
		try {
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_ENDCALL);
			minWait();

			do {
				minWait();
				Runtime.getRuntime().exec("adb -s "+p_Id+" shell input keyevent 21");
				minWait();
			} while (!isElementExist(Locators_Sanity.quickSettingsPage));
			minWait();
			validateLeftRightKey(Locators_Sanity.quickSettingsPage,Locators_Sanity.usbConnectedOpt, "Left-Navigation");
			minWait();

			do {
				minWait();
				Runtime.getRuntime().exec("adb -s "+p_Id+" shell input keyevent 22");
				minWait();
			} while (!isElementExist(Locators_Sanity.missedEventsOpt));
			minWait();
			validateKey(s,Locators_Sanity.missedEventsOpt, "Right-Navigation");
			minWait();
			launch_an_app("settings");
			minWait();
			scrollToText("Network & Internet");
			minWait();
			//clickBtn(Locators_Sanity.moreOptn);
			//minWait();
			for (int i=1; i<5; i++) {
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
				minWait();
			}
			validateKey(s,Locators_Sanity.tethering_Portable_Hotspot, "Down-Navigation");
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
			minWait();
			validateKey(s,Locators_Sanity.USBTetheringOpt, "Center-Navigation");
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			minWait();
			for (int i=1; i<3; i++) {
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_UP);
				minWait();
			}
			validateKey(s,Locators_Sanity.mobile_Networks, "Up-Navigation");
			minWait();
		} catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "No Such Element Found");
		}
	}

	public void validateLeftRightKey(AndroidElement element,AndroidElement element1,String key) throws InterruptedException, IOException
	{
		/*
		 * validate menu key by launching menu tray
		 */
		SoftAssert SA = new SoftAssert();
		try {
			if(isElementExist(element)||isElementExist(element1)){
				check = true;
				APP_LOGS.info(key+" key validated succesfully");
				test.log(LogStatus.INFO,key+" key validated succesfully");
				SA.assertTrue(check, key+" key validated succesfully");
			}else{
				APP_LOGS.info("Failed to validate "+key+" key");
				test.log(LogStatus.ERROR,"Failed to validate "+key+" key");
				SA.fail();
			}
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			APP_LOGS.info("Element Not Found");
			test.log(LogStatus.ERROR, "No Such Element Found");
			SA.fail();
		}
	}

	public void selectFMApp() throws InterruptedException {
		/*
		 * select Messaging application
		 */
		try {
			minWait();	
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_HOME);
			minWait();
			clickBtn(Locators_Sanity.Device_App);
			minWait();
			//		customWait(4000);
			clickBtn(Locators_Sanity.FM_App);
		} catch (org.openqa.selenium.NoSuchElementException e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "No Such Element Found");
			Assert.fail();
		}
	}

	public void turnOFF_FM() throws InterruptedException {
		try {
			customWait(3000);
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_MENU);
			for(int i=1;i<=8;i++) {
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
				minWait();
			}
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
			APP_LOGS.info("FM Radio is Turn Off");
		} 
		catch (org.openqa.selenium.NoSuchElementException e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "No Such Element Found");
			Assert.fail();
		}
	}

	public void validateOn_OFF_FM() throws InterruptedException {
		SoftAssert sf = new SoftAssert();
		try {
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_MENU);

			if(isElementExist(Locators_Sanity.OffFMImsg)){
				check = true;
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
				APP_LOGS.info("FM Radio Stopped/Off succesful.");
				test.log(LogStatus.INFO, "FM Radio Turned On/OFF succesfully.");
				sf.assertTrue(check, "Validation is Pass");
			} else {
				APP_LOGS.info("FM Radio is not Turn Off, unsuccesful.");
				test.log(LogStatus.ERROR, "FM Radio is not Turn Off/On, unsuccesful.");
				sf.fail();
			}
		} catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "No Such Element Found");
			sf.fail();			
		}
		sf.assertAll();
	}

	public void validateHomePageLoadedBrowser() throws InterruptedException, IOException {
		SoftAssert sf = new SoftAssert();
		try {
			launch_an_app("browser");
			customWait(2000);
			enterUrl("www.youtube.com");
			customWait(5000);
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_MENU);
			minWait();
			clickBtn(Locators_Sanity.HomePage_Browser);
			customWait(4000);
			if((isElementExist(Locators_Sanity.SearchIcon_Google))||(isElementExist(Locators_Sanity.Searchenter_Google)) || (isElementExist(Locators_Sanity.rogersHomePageBrowser)) || (isElementExist(Locators_Sanity.attHomePageBrowser)) || (isElementExist(Locators_Sanity.bellSearchHomePageBrowser)) || (isElementExist(Locators_Sanity.bellURLHomePageBrowser))) {
				check =true;
				APP_LOGS.info("check: "+check);
				APP_LOGS.info("HomePageLoaded sucessful");
				test.log(LogStatus.PASS, "HomePage Loaded is sucessful");
				sf.assertTrue(check, "Validation is Pass");
			} else if(isElementExist(Locators_Sanity.Hompage_ATT)) {
				check =true;
				APP_LOGS.info("check: "+check);
				APP_LOGS.info("HomePageLoaded is sucessful");
				test.log(LogStatus.PASS, "HomePage Loaded is sucessful");
				sf.assertTrue(check, "Validation is Pass");
			} else{
			 	APP_LOGS.info("check: "+check);
				APP_LOGS.info("HomePageLoaded is not sucessful");
				test.log(LogStatus.FAIL, "HomePage Loaded is not sucessful");
				sf.fail();
			}minWait();	
		} catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.FAIL, "No Such Element Found");
			sf.fail();
		}
		sf.assertAll();
	}

	public void validateBrowseWithWiFi() throws InterruptedException, IOException {
		/*
		 * disable enable wiFi
		 */
		SoftAssert sf = new SoftAssert();
		try {
			launch_an_app("settings");
			minWait();
			scrollToText("Network & Internet");
			minWait();
			//clickBtn(Locators_Sanity.wifi);
			scrollToText("Wi‑Fi");
			minWait();
			enableFeature(Locators_Sanity.enabled,Locators_Sanity.disabled,Locators_Sanity.switch_Title);
			minWait();
			customWait(4000);
			launch_an_app("browser");
			customWait(4000);
			enterUrl("www.google.com");
			customWait(4000);
			if((isElementExist(Locators_Sanity.SearchIcon_Google))||(isElementExist(Locators_Sanity.Searchenter_Google))) 
			{
				check =true;
				APP_LOGS.info("check: "+check);
				APP_LOGS.info("Enable the WiFi connection is sucessful");
				test.log(LogStatus.PASS, "Enable the WiFi connection is sucessful");
			}else{
				APP_LOGS.info("check: "+check);
				APP_LOGS.info("Enable WiFi connection is not sucessful");
				test.log(LogStatus.FAIL, "Enable WiFi connection is not sucessful");
			}
			minWait();	
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			minWait();	
			minWait();	
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			minWait();	
		} catch (org.openqa.selenium.NoSuchElementException e) {
			APP_LOGS.info("No Element found.");
			sf.fail();
		}
		sf.assertAll();
	}

	public void deleteSavedRecorder() throws InterruptedException {
		try {
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_RIGHT);
			if(!isElementExist(Locators_Sanity.noRecordingsOpt)){
				APP_LOGS.info("Pressed List Icon sucessfully");
				minWait();
				minWait();
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_MENU);
				for(int i=1; i<=3; i++) {
					aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
				}
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
				minWait();
				APP_LOGS.info("Pressed Delete Icon sucessfully");
				minWait();
				minWait();
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_RIGHT);
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
				APP_LOGS.info("Pressed Delete Button sucessfully");
				Thread.sleep(2000);
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			}else{
				clickBackButton(1);
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {
			APP_LOGS.info("No Element found.");
			minWait();	
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

	public void recordSound() throws InterruptedException {
		try {
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
			minWait();
			selectRecordBtn();
			APP_LOGS.info("Pressed Record button sucessfully");
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_LEFT);
			APP_LOGS.info("Pressed Stop button sucessfully");
			customWait(2000);
			//	Locators_Sanity.saveBtn.click();
		} catch (org.openqa.selenium.NoSuchElementException e) {
			APP_LOGS.info("No Element found.");
			minWait();	
			//				Assert.fail();
		}
	}

	public void validateRecordedSoundSaved() throws InterruptedException {
		/*
		 * check recording name is saved in recorded list after saving
		 */
		SoftAssert SA= new SoftAssert();
		try {
			minWait();
			String SavedRecName=Locators_Sanity.recordingName.getText();
			minWait();
			System.out.println(SavedRecName);
			Locators_Sanity.saveBtn.click(); 
			APP_LOGS.info("Pressed Save button sucessfully");
			minWait();
			String ListSavedRecName=Locators_Sanity.saveBtnUnderRecList.getText();
			System.out.println(ListSavedRecName);
			minWait();

			if(ListSavedRecName.contains("New record")){
				check= true;
				APP_LOGS.info("Recorded sound using Sound recorder and validated successfully");
				test.log(LogStatus.INFO, "Recorded sound using Sound recorder and validated successfully");
				SA.assertTrue(check, "Recorded sound using Sound recorder and validated successfully");	
			}else{
				APP_LOGS.info("test Case Failed");
				test.log(LogStatus.ERROR, "Failed to validate Sound recorder");
				SA.fail();
			}	aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
		}
		catch (org.openqa.selenium.NoSuchElementException e) {
			APP_LOGS.info("Element not Found");
			e.printStackTrace();
		}
	}

	public void cameraPic() throws IOException, InterruptedException, ParseException {
		/*
		 * Take camera picture via camera application.
		 */
		String Uid=JsonFileReaderAndWriter.primaryDevIdReader();

		try {
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_HOME);
			customWait(2000);
			navigateToApplication("Camera");
			customWait(3000);
			if(isElementExist(Locators_Sanity.photocameraIcon)) {
				clickBtn(Locators_Sanity.photocameraIcon);
			} else {
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_UP);
				minWait();
				clickBtn(Locators_Sanity.photocameraIcon);
			}
			minWait();
		} catch (NoSuchElementException e) {
			e.printStackTrace();
		}
	}

	public void validateBackCamera(String str, String TitleName,String fileName) throws InterruptedException, AWTException, IOException {
		/*
		 * Validate Gallery Folders from TitleName
		 */
		boolean check = false;
		SoftAssert s = new SoftAssert();
		try {
			customWait(3000);
			if(searchString(str,fileName)) {
				check = true;
				s.assertTrue(check, "Validation of Test case is PASS");
				APP_LOGS.info(TitleName + "Back Camera is Validated in Gallery");
				test.log(LogStatus.PASS, TitleName + ":Back Camera is Validated ");
			} else {
				APP_LOGS.info(TitleName + "Back Camera is unVerified in Gallery");
				test.log(LogStatus.FAIL, TitleName + ":Back Camera is unVerfied ");
				s.fail();
			}
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			s.fail();
		}
		s.assertAll();		
	}

	public void takeCameraVideo(int n) throws InterruptedException, IOException {
		/*
		 * Capture photo from camera and store in SDcard
		 */
		SoftAssert S1 = new SoftAssert();
		try {
			customWait(1000);
			aDriver.pressKeyCode(AndroidKeyCode.MENU);
			minWait();
			clickBtn(Locators_Sanity.SwitchToCameraOptn);
			customWait(2000);
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_UP);
			minWait();
			clickBtn(Locators_Sanity.videocameraIcon);
			for(int i=1; i<=n;i++) {
				customWait(8000);
			}
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
			customWait(1000);
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			minWait();
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			S1.fail();
		}
		S1.assertAll();
	}

	public void press_Enter() throws InterruptedException {
		/*
		 * enter key is pressed
		 */
		minWait();
		aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_ENTER);
		minWait();
		aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_ENTER);
		minWait();
	}

	public void selectRecordBtn() throws InterruptedException {

		for(int i=0; i<=2; i++) {
			if(isElementExist(Locators_Sanity.allowButton)){
				customWait(2000);
				Locators_Sanity.allowButton.click();
				APP_LOGS.info("Pressed Allow button sucessfully");
				minWait();
				APP_LOGS.info("Allow Button is displayed and Selected, succesful.");

			}else{
				APP_LOGS.info("Allow Button is not displayed.");

			}
		}

	}

	public void playMusic() throws InterruptedException{
		minWait();
		launch_an_app("music");
		aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
		minWait();
		clickBtn(Locators_Sanity.music);
		minWait();
		aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
		customWait(4000);
		aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
		minWait();
		aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);


	}

	public void validate_music_player() throws InterruptedException{
		launch_an_app("music");
		try {
			if(isElementExist(Locators_Sanity.musicIndicator)){
				APP_LOGS.info("Music played sucessfully.");
				Assert.assertTrue(true);
			}else{
				APP_LOGS.info("Music is not played.");
				Assert.fail();
			}
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			APP_LOGS.info("No Element found.");
			test.log(LogStatus.ERROR, " Battery option: No Such Element Found.");
			Assert.fail();
		}
	}

	public void copy_file_to_Folder() throws InterruptedException {
		try {
			minWait();
			launch_an_app("fileExplorer");
			minWait();
			clickBtn(Locators_Sanity.xp3800);
			APP_LOGS.info("Clicked on XP3800");
			customWait(2000);
			scrollText("SoundRecorder");
			if(isElementExist(Locators_Sanity.soundRecorder_FE)) {
				minWait();
				clickBtn(Locators_Sanity.soundRecorder_FE);
				minWait();
			} else {
				press_BackKey();
				minWait();
				clickBtn(Locators_Sanity.SD_card);
				minWait();
				scrollText("SoundRecorder");
				minWait();
				clickBtn(Locators_Sanity.soundRecorder_FE);
				minWait();
			}			
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_MENU);
			minWait();
			APP_LOGS.info("Clicked on Sound Recorder folder");
			minWait();
			clickBtn(Locators_Sanity.renameOption);
			customWait(2000);
			enterTextToInputField(Locators_Sanity.renameField, "test_file");
			customWait(5000);
			clickBtn(Locators_Sanity.OkBtn);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_MENU);
			minWait();
			clickBtn(Locators_Sanity.copyTo_option);				
			APP_LOGS.info("Clicked on copy to option");
			minWait();
			clickBtn(Locators_Sanity.xp3800);
			APP_LOGS.info("Clicked on XP3800");
			minWait();
			scrollText("Download");
			minWait();
			Locators_Sanity.download_folder.click();
			minWait();
			//aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
			//minWait();
			clickBtn(Locators_Sanity.copy_here);
			customWait(3000);
		} catch (NoSuchElementException e) {
			APP_LOGS.info("No Element found.");
			test.log(LogStatus.ERROR, "No Such Element Found");
			e.printStackTrace();
			Assert.fail();
		}
	}

	public void validate_file_copy(SoftAssert s) throws InterruptedException{

		try {
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			minWait();
			clickBtn(Locators_Sanity.xp3800);
			minWait();
			scrollText("Download");
			minWait();
			Locators_Sanity.download_folder.click();
			minWait();
			if(isElementExist(Locators_Sanity.test_file_txt)){
				APP_LOGS.info("test file is copied to destination folder sucessfully.");
				test.log(LogStatus.PASS, "test file is copied to destination folder sucessfully.");
				s.assertTrue(true, "Validation of Test Case is PASS");
			}else{
				APP_LOGS.info("test file is not copied to destination folder.");
				test.log(LogStatus.FAIL,"test file is not copied to destination folder.");
				s.fail();
			}
		} catch (Exception e) {
			APP_LOGS.info("No Element found.");
			test.log(LogStatus.ERROR, "No Such Element Found");
			e.printStackTrace();
			s.fail();
		}
	}

	public void checkOperatorAndlaunchCalendar() throws InterruptedException, IOException, ParseException, org.json.simple.parser.ParseException
	{
		/*
		 * check operator and launch calendar
		 */
		String XP5deviceModelNumber=JsonFileReaderAndWriter.primaryDevOperatorReader();
		try {
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_HOME);
			minWait();
			if(XP5deviceModelNumber.contains("5SA.0.1-04-7.1.2-26.")) {
				clickBtn(Locators_Sanity.applications);
				minWait();
				clickBtn(Locators_Sanity.calendar);
			}else {
				launch_an_app("calendar");
			}
		}catch (NoSuchElementException e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "Element Not Found");
		}
	}

	public void addCalendarEvent(String eventName,String location) throws InterruptedException, IOException
	{
		/*
		 * Add calendar Event
		 */
		try {
			minWait();
			deleteEvents();
			minWait();
			addEvent(eventName, location);
			minWait();
			clickMenuButton();
			if(isElementExist(Locators_Sanity.agendaOpt)) {
				clickBtn(Locators_Sanity.agendaOpt);
				minWait();
			}else {
				clickBackButton(1);
			}
			validateAddedEvent(eventName);
			minWait();
			deleteEvents();
		}catch (NoSuchElementException e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "Element Not Found");
		}
	}

	public void deleteEvents() throws InterruptedException, IOException
	{
		/*
		 * delete events
		 */
		try {
			clickMenuButton();
			if(isElementExist(Locators_Sanity.monthOpt)){
				clickBtn(Locators_Sanity.monthOpt);
				minWait();
				clickMenuButton();
			} else {
				clickBackButton(1);
				minWait();
				clickMenuButton();
			}
			if (Locators_Sanity.deleteEventsEnabled.isEnabled()) {
				clickBtn(Locators_Sanity.deleteEventsOpt);
				minWait();
				clickMenuAndElement(Locators_Sanity.selectAllOpt);
				minWait();
				clickMenuAndElement(Locators_Sanity.doneOpt);
				minWait();
				clickBtn(Locators_Sanity.OkBtn);
				customWait(2000);
			}else {
				clickBackButton(1);
			}
		}catch (NoSuchElementException e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "Element Not Found");
		}
	}


	public void addEvent(String eventName,String location ) throws InterruptedException, IOException
	{
		/*
		 * Add event and save
		 */
		try {
			clickMenuAndElement(Locators_Sanity.newEvent);
			minWait();
			enterTextToInputField(Locators_Sanity.eventName, eventName);
			minWait();
			enterTextToInputField(Locators_Sanity.locationFld, location);
			minWait();
			clickMenuAndElement(Locators_Sanity.saveOpt);
		}catch (NoSuchElementException e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "Element Not Found");
		}
	}

	public void validateAddedEvent(String eventName) throws InterruptedException, IOException
	{
		/*
		 * Validates Added event
		 */
		SoftAssert SA = new SoftAssert();
		try {
			String event = Locators_Sanity.createdEvent.getText();
			minWait();
			if(event.equals(eventName)){
				check = true;
				APP_LOGS.info("Event added in Calendar succesfully");
				test.log(LogStatus.INFO, "Event added in Calendar succesfully");
				SA.assertTrue(check, "Event added in Calendar succesfully");	
			}else{
				APP_LOGS.info("Failed to add event");
				test.log(LogStatus.INFO, "Failed to add event");
				SA.fail("Failed to add event");
			}
		}catch (NoSuchElementException e) {
			e.printStackTrace();
			APP_LOGS.info("Element Not Found");
			test.log(LogStatus.ERROR, "Element Not Found");
			SA.fail();
		}
		SA.assertAll();
	}

	public void clickMenuButton()
	{
		/*
		 * clicks on back button with iteration as user input
		 */
		try {
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_MENU);
			minWait();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void launchClock() throws InterruptedException, IOException
	{
		/*
		 * Launch clock application
		 */
		try {
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_HOME);
			minWait();
//			clickBtn(Locators_Sanity.tools);
//			minWait();
			clickBtn(Locators_Sanity.clock);
		}catch (NoSuchElementException e) {
			e.printStackTrace();
		}
	}

	public void addAlarm() throws InterruptedException, IOException	{
		/*
		 * Adds Alarm.
		 */
		try {
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_LEFT);
			minWait();
			clickMenuAndElement(Locators_Sanity.addOpt1);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_RIGHT);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_RIGHT);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
			minWait();
			clickBtn(Locators_Sanity.OkBtn);
			minWait();
		}catch (NoSuchElementException e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "Element Not Found");
		}
	}

	public void validateAddedAlarm() throws InterruptedException, IOException
	{
		/*
		 * Validates Added Alarm
		 */
		SoftAssert SA = new SoftAssert();
		try {
			if(isElementExist(Locators_Sanity.alarmSetPage)){
				check = true;
				clickBackButton(1);
				minWait();
				if(isElementExist(Locators_Sanity.alarmTodayOpt) && isElementExist(Locators_Sanity.alarmSwitchOn))
				{
					clickBtn(Locators_Sanity.alarmSwitchOn);
					APP_LOGS.info("Alarm added succesfully");
					test.log(LogStatus.INFO, "Alarm added succesfully");
					SA.assertTrue(check, "Alarm added succesfully");	
				}
			}else{
				APP_LOGS.info("Failed to add alarm");
				test.log(LogStatus.INFO, "Failed to add alarm");
				SA.fail("Failed to add alarm");
			}
		}catch (Exception e) {
			e.printStackTrace();
			APP_LOGS.info("Element Not Found");
			test.log(LogStatus.ERROR, "Element Not Found");
			SA.fail();
		}
		SA.assertAll();
	}

	public void snoozeTheAlarm() throws InterruptedException, IOException
	{
		/*
		 * Add alarm,Snooze and validate
		 */
		try {
			addAlarm();
			clickBackButton(1);
			minWait();
			waituntilnew(Locators_Sanity.alarmPage, 60000);
			minWait();
			if(isElementExist(Locators_Sanity.alarmTimeFld)) {
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_LEFT);
			}
			validateSnoozeAndDismiss(Locators_Sanity.alarmSwitchOn,Locators_Sanity.alarmTodayOpt,"Snoozed");
			minWait();
			clickBtn(Locators_Sanity.alarmSwitchOn);
		}catch (NoSuchElementException e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "Element Not Found");
		}
	}

	public void dismissTheAlarm() throws InterruptedException, IOException
	{
		/*
		 * Add Alarm,Dismiss and validate
		 */
		try {
			addAlarm();
			clickBackButton(1);
			minWait();
			waituntilnew(Locators_Sanity.alarmPage, 60000);
			minWait();
			if(isElementExist(Locators_Sanity.alarmTimeFld)) {
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_RIGHT);
			}
			customWait(2000);
			validateSnoozeAndDismiss(Locators_Sanity.alarmSwitchOff,Locators_Sanity.alarmTomorrowOpt,"Dismissed");
			minWait();
		}catch (NoSuchElementException e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "Element Not Found");
		}
	}

	public void validateSnoozeAndDismiss(WebElement element,WebElement elementday,String state) throws InterruptedException, IOException
	{
		/*
		 * Validates snooze and dismiss alarm
		 */
		SoftAssert SA = new SoftAssert();
		try {
			customWait(4000);
			if(isElementExist(elementday) && isElementExist(element))
			{
				check= true;
				APP_LOGS.info("Alarm "+state+" succesfully");
				test.log(LogStatus.INFO, "Alarm "+state+" succesfully");
				SA.assertTrue(check, "Alarm "+state+" succesfully");	
			}else {
				APP_LOGS.info("Alarm not "+state);
				test.log(LogStatus.INFO, "Alarm not "+state);
				SA.fail("Alarm not "+state);
			}
		}catch (NoSuchElementException e) {
			e.printStackTrace();
			APP_LOGS.info("Element Not Found");
			test.log(LogStatus.ERROR, "Element Not Found");
			SA.fail();
		}
		SA.assertAll();
	}

	public void validateChangeSleepTime() {
		/*
		 * Set sleep mode to maximum
		 */

		boolean check;
		SoftAssert changeSleepTime = new SoftAssert();
		String sleepTime;

		APP_LOGS.info("Inside validateChangeSleepTime");
		try {
			scrollToText("Display");
			customWait(1000);
			sleepTime = Locators_Sanity.Sleep_Summary.getText().substring(7, 16);
			System.out.println(sleepTime);
			APP_LOGS.info("sleepTime ="+ sleepTime);
			scrollToText("Sleep");
			minWait();
			scrollToText("10 minutes");
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			minWait();
			scrollToText("Display");
			minWait();
			sleepTime = Locators_Sanity.Sleep_Summary.getText().substring(6, 16);
			APP_LOGS.info("sleepTime ="+ sleepTime);
			if (sleepTime.equalsIgnoreCase("10 minutes")) {
				check = true;
				APP_LOGS.info("Changed sleep time to"+sleepTime);
				changeSleepTime.assertTrue(check, "");
			}
			else {
				check = true;
				APP_LOGS.info("Sleep time was not changed");
				changeSleepTime.assertFalse(check);
			}
			scrollToText("Sleep");
			minWait();
			scrollToText("30 minutes");
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			minWait();
			scrollToText("Display");
			minWait();
			sleepTime = Locators_Sanity.Sleep_Summary.getText().substring(6, 16);
			APP_LOGS.info("sleepTime ="+ sleepTime);
			if (sleepTime.equalsIgnoreCase("30 minutes")) {
				check = true;
				APP_LOGS.info("Changed sleep time to"+sleepTime);
				changeSleepTime.assertTrue(check, "");
			}
			else {
				check = true;
				APP_LOGS.info("Sleep time was not changed");
				changeSleepTime.assertFalse(check);
			}

		}
		catch(Exception e) {
			APP_LOGS.info("Element not found while changing sleeptime");
			Assert.fail();

		}
		changeSleepTime.assertAll();
	}

	public void validateChaneSleepTime() throws InterruptedException {
		/*
		 * Set sleep mode to maximum
		 */

		try {
			minWait();
			String getSleepSummary = Locators_DevSanity.Sleep_Summary.getText();
			System.out.println(getSleepSummary);
			customWait(2000);
			clickBtn(Locators_DevSanity.Sleep_Optn);
			minWait();
			for(int i=1; i<=2; i++) {
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_UP);
			}
			clickBtn(Locators_DevSanity.thirtyMinSleep_Optn);
			customWait(5000);
			String getSleepchange = Locators_DevSanity.Sleep_Summary.getText();
			System.out.println(getSleepchange);

			if(!getSleepchange.equals(getSleepSummary)) {
				check = true;
				test.log(LogStatus.INFO, "Sleep Time is changed successfully");
				Assert.assertTrue(check);
			}
			else {
				test.log(LogStatus.ERROR, "Sleep Time is not changed ");
				Assert.fail();
			}
			clickBtn(Locators_DevSanity.Sleep_Optn);
			minWait();

			for(int i=1; i<=8; i++) {
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
			}
			clickBtn(Locators_DevSanity.Ringtone_SilkyWay);
			test.log(LogStatus.INFO, " Sleep Mode is set to maximum");
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			test.log(LogStatus.ERROR, " No such Element Found");
		}		
	}

	public void composeMsg(String composedMsg, String newNum) throws InterruptedException, IOException {
		/*
		 * compose Msg with 145 character ie single page and send to new number or saved contact
		 * 
		 */
		SoftAssert Sa = new SoftAssert();
		try {
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_MENU);
			minWait();
			clickBtn(Locators_DevSanity.New_msgOptn);
			minWait();
			//Runtime.getRuntime().exec("adb shell am start -n com.android.mms/com.android.mms.ui.ComposeMessageActivity");
			customWait(1000);
			changeToNumberMode();
			minWait();
			enterTextToInputField(Locators_DevSanity.Type_NumField, newNum);
			minWait();
			if(isElementExist(Locators_DevSanity.Msg_Contact)) {
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_ENTER);
				minWait();
			}
			minWait();
			enterTextToInputField(Locators_DevSanity.typeMsg_Field, composedMsg);

		} catch (Exception e) {
			APP_LOGS.info("No Element found.");
			e.printStackTrace();
			test.log(LogStatus.ERROR, "No Such Element Found");
			Sa.fail();
		}
		Sa.assertAll();
	}

	public void delete_All_Threads() throws InterruptedException {
		/*
		 * Method is used to delete All the SMS Threads.
		 */
		SoftAssert sf = new SoftAssert();
		try {
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
			minWait();
			while (true) {
				if (isElementExist(Locators_DevSanity.first_sms_Thread)||isElementExist(Locators_DevSanity.first_sms_Thread1)) {
					aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
					minWait();
					aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_CLEAR);
					minWait();
					clickBtn(Locators_DevSanity.DELETE);
				} else {
					Reporter.log("No Threads Found");
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			sf.fail();
			test.log(LogStatus.ERROR, "Error in Deleting the Messages.");
		}
		sf.assertAll();
	}
	
	public SoftAssert softAssert() {
		SoftAssert s = new SoftAssert();
		return s;
	}

}

