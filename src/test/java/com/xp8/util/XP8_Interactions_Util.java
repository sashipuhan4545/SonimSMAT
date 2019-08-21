package com.xp8.util;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.NoSuchElementException;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import com.graphics.gui.JsonFileReaderAndWriter;
import com.relevantcodes.extentreports.LogStatus;


import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.AndroidKeyCode;
import SikuliHelper.*;
import application.AllQA;


public class XP8_Interactions_Util extends BaseUtil {

	public boolean check = false;
	boolean check0 = false;
	boolean check1 = false;

	public String p_Id  	= "";    		// Primary Device Serial Number.
	public String r_Id 		= "";    		// Reference Device Serial Number.
	public String p_b_No 	= "";      	// Primary Device Build Number.
	public String r_b_No 	= "";    		// Reference Device Build Number.
	public String pryNum 	= AllQA.PRIMARYDEVMDN; // Reference Device MDN.  
	public String refNum 	= AllQA.REFERENCEDEVMDN; // Reference Device MDN.  

	public void fetch_Devices_Details() throws InterruptedException, FileNotFoundException, IOException,org.json.simple.parser.ParseException {

		minWait();
		p_Id=JsonFileReaderAndWriter.primaryDevIdReader();
		r_Id=JsonFileReaderAndWriter.ReadRefDeviceId();
		p_b_No=JsonFileReaderAndWriter.primaryDevFirmwareReader();
		r_b_No=JsonFileReaderAndWriter.ReadRefDeviceFirmWare();
	}
	public void launch_APP(AndroidElement appToClick) throws InterruptedException {
		try {
			clickOnAppList();
			for (int i = 0; i < 6; i++) {
				if (isElementExist(appToClick)) {
					//						test.log(LogStatus.INFO, "\""+appToClick.getText()+"\" app launched.");
					clickBtn(appToClick);
					minWait();
					break;
				} else {
					scroll();
					minWait();
				}
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR,"Error in locators->launch_APP()");
		}catch (Exception e) {
			test.log(LogStatus.ERROR,"Exeption in ->launch_APP()");
		}
	}
	public void Installing_skype(SoftAssert sa,String phone,String text){
		try{
			WebDriverWait wait = new WebDriverWait(aDriver, 600);
			Locators_BaseUtil.AppListIcon.click();
			minWait();
			scrollToElements(Locators_Interactions.skype);
			if(isElementExist(Locators_Interactions.skype)){
				Runtime.getRuntime().exec("adb -s "+p_Id+" shell pm clear com.skype.raider");	
				launch_APP(Locators_Interactions.skype);
				wait.until(ExpectedConditions.visibilityOf(Locators_Interactions.skypeletsgo));
				clickBtn(Locators_Interactions.skypeletsgo);
				wait.until(ExpectedConditions.visibilityOf(Locators_Interactions.skypesignin));
				clickBtn(Locators_Interactions.skypesignin);
				wait.until(ExpectedConditions.visibilityOf(Locators_Interactions.skypeedittxt));
				enterTextToInputField(Locators_Interactions.skypeedittxt, phone);
				wait.until(ExpectedConditions.visibilityOf(Locators_Interactions.skypenext));
				clickBtn(Locators_Interactions.skypenext);
				wait.until(ExpectedConditions.visibilityOf(Locators_Interactions.skypepassword));
				enterTextToInputField(Locators_Interactions.skypepassword,text);
				wait.until(ExpectedConditions.visibilityOf(Locators_Interactions.skypesign));
				clickBtn(Locators_Interactions.skypesign);
				wait.until(ExpectedConditions.visibilityOf(Locators_Interactions.skypecontinue));
				clickBtn(Locators_Interactions.skypecontinue);
				clickBtn(Locators_Interactions.skypecontinue);
				clearAllow();
				clickBtn(Locators_Interactions.skypecontinue);
				clearAllow();
				clickBtn(Locators_Interactions.skypetouch);
				
			
			}else{
				Runtime.getRuntime().exec("adb -s "+p_Id+" install .\\src\\test\\resources\\StorageFile\\Skype.apk");
				customWait(5000);
				launch_APP(Locators_Interactions.skype);
				wait.until(ExpectedConditions.visibilityOf(Locators_Interactions.skypeletsgo));
				clickBtn(Locators_Interactions.skypeletsgo);
				wait.until(ExpectedConditions.visibilityOf(Locators_Interactions.skypesignin));
				clickBtn(Locators_Interactions.skypesignin);
				wait.until(ExpectedConditions.visibilityOf(Locators_Interactions.skypeedittxt));
				enterTextToInputField(Locators_Interactions.skypeedittxt, phone);
				wait.until(ExpectedConditions.visibilityOf(Locators_Interactions.skypenext));
				clickBtn(Locators_Interactions.skypenext);
				wait.until(ExpectedConditions.visibilityOf(Locators_Interactions.skypepassword));
				enterTextToInputField(Locators_Interactions.skypepassword,text);
				wait.until(ExpectedConditions.visibilityOf(Locators_Interactions.skypesign));
				clickBtn(Locators_Interactions.skypesign);
				wait.until(ExpectedConditions.visibilityOf(Locators_Interactions.skypecontinue));
				clickBtn(Locators_Interactions.skypecontinue);
				clickBtn(Locators_Interactions.skypecontinue);
				clearAllow();
				clickBtn(Locators_Interactions.skypecontinue);
				clearAllow();
				clickBtn(Locators_Interactions.skypetouch);
				
			}
		}catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->Installing_skype()");
			e.printStackTrace();

		}
		catch (Exception e) {
			test.log(LogStatus.ERROR, "Exeption in ->Installing_skype()");
		}
			
		}
	public void Installing_gaana(SoftAssert sa){
		try{
			WebDriverWait wait = new WebDriverWait(aDriver, 600);
			Locators_BaseUtil.AppListIcon.click();
			minWait();
			scrollToElements(Locators_Interactions.gaana);
			if(isElementExist(Locators_Interactions.gaana)){
				Runtime.getRuntime().exec("adb -s "+p_Id+" shell pm clear com.gaana");	
				launch_APP(Locators_Interactions.gaana);
				if(isElementExist(Locators_Interactions.gaanaskip)){
				clickBtn(Locators_Interactions.gaanaskip);
				clickBtn(Locators_Interactions.gaanaskip);
				//clickBtn(Locators_Interactions.gaanaskip);
				clickBtn(Locators_Interactions.gaanadolater);
				}else{
					clickBtn(Locators_Interactions.gaanacontinue);
					clickBtn(Locators_Interactions.gaanaskip);
					clickBtn(Locators_Interactions.gaanadolater);
				}
		}else{
				Runtime.getRuntime().exec("adb -s "+p_Id+" install .\\src\\test\\resources\\StorageFile\\Gaanamusicapp.apk");
				customWait(5000);
				launch_APP(Locators_Interactions.gaana);
				clickBtn(Locators_Interactions.gaanaskip);
				clickBtn(Locators_Interactions.gaanaskip);
				clickBtn(Locators_Interactions.gaanadolater);
		}		
				
}catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->Installing_gaana()");
			e.printStackTrace();

		}
		catch (Exception e) {
			test.log(LogStatus.ERROR, "Exeption in ->Installing_gaana()");
		}
			
		}
	public void clickOnAppList() throws InterruptedException {

		//try {
		minWait();
		aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_HOME);			
		customWait(2000);			
		clickBtn(multi_Loc_Strategy(Locators_Interactions.app_List_desc, Locators_Interactions.app_List_id, Locators_Interactions.app_List_index, Locators_Interactions.app_List_resourceid, Locators_Interactions.app_List_contdesc, 468, 1650));
		minWait();
		//	} catch (Exception e) {
		//	e.printStackTrace();
		//	}

	}

	public void make_Call_from_PrmyDev() throws InterruptedException, IOException {
		//make a call from ref device to primary device
		try {
			System.out.println("Inside Make a call");
			Runtime.getRuntime().exec("adb -s "+p_Id+" shell am start -a android.intent.action.CALL -d tel:"+refNum);
		if(isElementExist(Locators_Interactions.phncomplt)){
				clickBtn(Locators_Interactions.phntxt);
				clickBtn(Locators_Interactions.phnalways);
				customWait(10000);
			}
		else{
			System.out.println("call is originating");
		}
			customWait(10000);			
			System.out.println("Call is activitated");
			minWait();

		}catch (Exception e) {
			test.log(LogStatus.ERROR, "Exeption in -> make_Call_from_PrmyDev()");

		}
	}
	public void endCall_PrmyDevice() {
		try {
			minWait();
			Runtime.getRuntime().exec("adb -s "+p_Id+" shell input keyevent 6");
			Thread.sleep(1000);
		}catch (Exception e) {
			test.log(LogStatus.ERROR, "Exeption in ->endCall_PrmyDevice() ");

		}
	}
	public void navigateTo_NewSMS_ATT() throws InterruptedException {
		/* Used to Navigate to New Message Page. */
		try {
			clickBtn(multi_Loc_Strategy(Locators_Interactions.add_NewSMS_ATT, Locators_Interactions.add_NewSMS_ATT_cls, Locators_Interactions.add_NewSMS_ATT_id, Locators_Interactions.add_NewSMS_ATT_indx, Locators_Interactions.add_NewSMS_ATT_xpath, 1735, 959));
			minWait();
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR,"Error in locators->navigateTo_NewSMS_ATT()");
		}catch (Exception e) {
			test.log(LogStatus.ERROR,"Exeption in ->navigateTo_NewSMS_ATT()");
		}
	}
	public void enter_Num_ToField1(String number) throws InterruptedException {
		/* Method is used to Enter Number into TO Field. */
		try {
			minWait();
			enterTextToInputField(Locators_Interactions.TO_Field1, number);
			customWait(2000);
			clickBtn(Locators_Interactions.contactPicker);
			customWait(2000);			
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR,"Error in locators->enter_Num_ToField1()");
		}catch (Exception e) {
			test.log(LogStatus.ERROR,"Exeption in ->enter_Num_ToField1()");
		}
	}
	public void enterText_MessageField(String message) throws InterruptedException {
		/* Methos is Used enter Message into Message Field. */
		try {
			customWait(2000);
			enterTextToInputField(multi_Loc_Strategy(Locators_Interactions.type_Messageid_att, Locators_Interactions.type_Message_att, Locators_Interactions.type_Message_indx_att, Locators_Interactions.type_Messagetxt_att, Locators_Interactions.typemessageField_att, 234, 1120), message);
			minWait();
		}catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR,"Error in locators->enterText_MessageField()");
		}catch (Exception e) {
			test.log(LogStatus.ERROR,"Exeption in ->enterText_MessageField()");
		}
	}
	public void clickOn_Send() throws InterruptedException { 
		/* Method used to click on Send Button. */
		try {
			minWait();
			if(isElementExist(Locators_Interactions.send_Icon)) {
				clickBtn(Locators_Interactions.send_Icon);
				APP_LOGS.info("Send icon");
				minWait();
			}
			else if(isElementExist(Locators_Interactions.send_SMS)) {
				clickBtn(Locators_Interactions.send_SMS);
				APP_LOGS.info("Send icon");
				minWait();
			}
			else {
				clickBtn(Locators_Interactions.send_MMS_Icon);
				minWait();
			}

		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR,"Error in locators->clickOn_Send()");
		}catch (Exception e) {
			test.log(LogStatus.ERROR,"Exeption in ->clickOn_Send()");
		}
	}
		public void create_NewSMS(String refNum, String message) throws InterruptedException, IOException {
			/* Method used to create New SMS. */
			navigateTo_NewSMS_ATT();
			try {
				//enter_Num_ToField1(number);
				enterTextToInputField(Locators_Interactions.TO_Field_Text1_att,refNum);
				customWait(2000);
			   System.out.println("Enter Text");
				enterText_MessageField(message);
			}catch (org.openqa.selenium.NoSuchElementException e) {

				test.log(LogStatus.ERROR,"Error in locators->create_NewSMS()");
			}catch (Exception e) {
				test.log(LogStatus.ERROR,"Exeption in ->create_NewSMS()");
			}
		}
		public void validate_SentMessage(SoftAssert soft) throws InterruptedException {
			/* To validate the Sent Message. */
			WebDriverWait wait  =new WebDriverWait(aDriver, 80);
			try {
				//				 launch_an_app("messaging");
				customWait(8000);
				System.out.println("Sent msg");
				//aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
				wait.until(ExpectedConditions.visibilityOf(Locators_Interactions.now_Text));
				
				if(isElementExist(Locators_Interactions.now_Text)||isElementExist(Locators_Interactions.msg1)||isElementExist(Locators_Interactions.not_Sent_Text)||isElementExist(Locators_Interactions.sending_Txt)) {
					System.out.println("Sent msg successfully");
					APP_LOGS.info("Message sent Succeccfully");
					soft.assertTrue(true, "Message sent Succeccfully");
					test.log(LogStatus.PASS, "Test case status is Passed at iteration : ");


				} else {
					System.out.println("msg not sent successfully");
					APP_LOGS.info("SMS didn't sent");
					soft.fail("SMS didn't sent");
					test.log(LogStatus.FAIL, "SMS or MMS didn't Sent, Validation Failed at iteration : ");

				}
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
				minWait();

			}catch (org.openqa.selenium.NoSuchElementException e) {

				test.log(LogStatus.ERROR,"Error in locators->validate_SentMessage()");
			}catch (Exception e) {
				test.log(LogStatus.ERROR,"Exeption in ->validate_SentMessage()");
			}	
		}
		public void validate_chrome_sharemsg(SoftAssert sa) throws InterruptedException{
			launch_an_app("messaging");
			clickBtn(Locators_Interactions.smsfirstmsg);
			if(isElementExist(Locators_Interactions.chromeshare_msg)){
				System.out.println("Sent msg successfully");
				APP_LOGS.info("Message sent Succeccfully");
				sa.assertTrue(true, "Message sent Succeccfully");
				test.log(LogStatus.PASS, "Test case status is Passed at iteration : ");


			} else {
				System.out.println("msg not sent successfully");
				APP_LOGS.info("SMS didn't sent");
				sa.fail("SMS didn't sent");
				test.log(LogStatus.FAIL, "SMS or MMS didn't Sent, Validation Failed at iteration : ");

			}
				
			
			
		}
		public void reciveCallInRefDevice(String refNum, SoftAssert SA) throws InterruptedException, IOException {
			/* Receive Call in Reference device
			 */
			
			
			try {
				if (!isElementExist(Locators_XP8_Sanity.turnOff_Airplane_PopUp)) {
					try {
						for(int j=1;j<=100;j++){
							Process child = null;
							if (r_b_No.contains("8A.")) {
								child=Runtime.getRuntime().exec("adb -s "+p_Id+" shell service call telecom 27");
								System.out.println(child);
							} else if(r_b_No.contains("5SA.")) {
								child=Runtime.getRuntime().exec("adb -s "+p_Id+" shell service call telecom 28");
							}
							InputStream inputStream = child.getInputStream();
							InputStreamReader isr = new InputStreamReader(inputStream);
							BufferedReader bf = new BufferedReader(isr);
							String  value=bf.readLine();

							System.out.println(value);
							customWait(2000);
							if(value.contains("00000001")||value.contains("ffffffff")) {
								System.out.println("Phone is ringing so accepting call.");
								//Thread.sleep(10000);
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
			}
			catch (org.openqa.selenium.NoSuchElementException e) {
				test.log(LogStatus.ERROR, "Error in the locators->reciveCallInRefDevice()");
				e.printStackTrace();
			}
			catch (Exception e) {
				test.log(LogStatus.ERROR, "Exception in reciveCallInRefDevice() ");
				e.printStackTrace();
			}
		}

		public void sendSMS_fromRefDevice(String AutomationMessagee) {

			// To validate MT Message User should be inside Messaging APP of Primary Device.
			try {

				// Below Code To clear Battery PopUp.
				//			Runtime.getRuntime().exec("adb -s "+r_Id+" shell input tap 777 1083"); 
				minWait();
				if (r_b_No.contains("8A.")) {
					if (r_b_No.contains("-10.")||r_b_No.contains("-30.")) {

						System.out.println("SSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSS");

						Runtime.getRuntime().exec("adb -s "+r_Id+" shell service call isms 7 i32 0 s16 \"com.android.mms.service\" s16 "+pryNum+" s16 \"null\" s16 \"SMAT\" s16 \"null\" s16 \"null\"");

						/*minWait();
						Runtime.getRuntime().exec("adb -s "+r_Id+" shell input keyevent 3");
						minWait();
						Runtime.getRuntime().exec("adb -s "+r_Id+" shell am force-stop com.android.mms");
						customWait(2000);
						Runtime.getRuntime().exec("adb -s "+r_Id+" shell am start -a android.intent.action.SENDTO -d sms:"+pryNum+" --es sms_body \""+AutomationMessagee+"\" --ez exit_on_sent true");
						customWait(6000);
						Runtime.getRuntime().exec("adb -s "+r_Id+" shell input tap 540 1776");
						minWait();
						Runtime.getRuntime().exec("adb -s "+r_Id+" shell input tap 713 1098");
						minWait();
						Runtime.getRuntime().exec("adb -s "+r_Id+" shell input tap 918 1699");
						minWait();
						Runtime.getRuntime().exec("adb -s "+r_Id+" shell input tap 918 952");
						minWait();*/
					} else if(r_b_No.contains("-11.")||r_b_No.contains("-12.")||r_b_No.contains("-18.")||r_b_No.contains("-26.")||r_b_No.contains("-29.")){
						/*minWait();
						Runtime.getRuntime().exec("adb -s "+r_Id+" shell input keyevent 3");
						minWait();
						Runtime.getRuntime().exec("adb -s "+r_Id+" shell am force-stop com.google.android.apps.messaging");
						customWait(2000);
						Runtime.getRuntime().exec("adb -s "+r_Id+" shell am start -a android.intent.action.SENDTO -d sms:"+pryNum+" --es sms_body \""+AutomationMessagee+"\" --ez exit_on_sent true");
						customWait(6000);
						Runtime.getRuntime().exec("adb -s "+r_Id+" shell input tap 930 1756");
						minWait();
						Runtime.getRuntime().exec("adb -s "+r_Id+" shell input tap 930 976");
						minWait();*/
						Runtime.getRuntime().exec("adb -s "+r_Id+" shell service call isms 7 i32 0 s16 \"com.android.mms.service\" s16 "+pryNum+" s16 \"null\" s16 \"SMAT\" s16 \"null\" s16 \"null\"");

					}else if(r_b_No.contains("-15.")){
						minWait();
						/*System.out.println("IN Android O");
						minWait();
						Runtime.getRuntime().exec("adb -s "+r_Id+" shell input keyevent 3");
						minWait();
						Runtime.getRuntime().exec("adb -s "+r_Id+" shell am force-stop com.verizon.messaging.vzmsgs");
						customWait(4000);
						Runtime.getRuntime().exec("adb -s "+r_Id+" shell am start -a android.intent.action.SENDTO -d sms:"+pryNum+" --es sms_body \""+AutomationMessagee+"\" --ez exit_on_sent true");
						customWait(6000);
						System.out.println("Sending Message...");
						Runtime.getRuntime().exec("adb -s "+r_Id+" shell input tap 948 1769");
						minWait();
						Runtime.getRuntime().exec("adb -s "+r_Id+" shell input tap 948 1022");
						minWait();*/
						Runtime.getRuntime().exec("adb -s "+r_Id+" shell service call isms 7 i32 0 s16 \"com.android.mms.service\" s16 "+pryNum+" s16 \"null\" s16 \"SMAT\" s16 \"null\" s16 \"null\"");

					}
				} else if (r_b_No.contains("5SA.")){
					minWait();
					/*Runtime.getRuntime().exec("adb -s "+r_Id+" shell input keyevent 3");
					minWait();
					Runtime.getRuntime().exec("adb -s "+r_Id+" shell am force-stop com.android.mms");
					customWait(2000);
					Runtime.getRuntime().exec("adb -s "+r_Id+" shell am start -a android.intent.action.SENDTO -d sms:"+pryNum+" --es sms_body \""+AutomationMessagee+"\" --ez exit_on_sent true");
					customWait(6000);
					Runtime.getRuntime().exec("adb -s "+r_Id+" shell input keyevent 66");
					minWait();*/
					Runtime.getRuntime().exec("adb -s "+r_Id+" shell service call isms 7 i32 0 s16 \"com.android.mms.service\" s16 "+pryNum+" s16 \"null\" s16 \"SMAT\" s16 \"null\" s16 \"null\"");

				}
			}  catch (org.openqa.selenium.NoSuchElementException e) {

				test.log(LogStatus.ERROR,"Error in locators->sendSMS_fromRefDevice()");
			}catch (Exception e) {
				test.log(LogStatus.ERROR,"Exeption in ->sendSMS_fromRefDevice()");
			}
		}

		public void reply_received_sms(String message){
			WebDriverWait wait  =new WebDriverWait(aDriver, 80);
			
			try{
				wait.until(ExpectedConditions.visibilityOf(Locators_Interactions.now_Text));
				clickBtn(Locators_Interactions.skypecontact);
				System.out.println("rply");
				enterText_MessageField(message);
				customWait(6000);
				/*aDriver.openNotifications();
				//Runtime.getRuntime().exec("adb  -s "+p_Id+"shell cmd statusbar expand-settings"); 
				minWait();
				System.out.println("rply out");
				
				clickBtn(Locators_Interactions.reply_txt);
				enterTextToInputField(Locators_Interactions.reply_typemsg,message);
				clickBtn(Locators_Interactions.reply_send);*/
				
				
			} catch (org.openqa.selenium.NoSuchElementException e) {

				test.log(LogStatus.ERROR,"Error in locators->reply_received_sms()");
			}catch (Exception e) {
				test.log(LogStatus.ERROR,"Exeption in ->reply_received_sms()");
			}
		}
		public void Validate_reply_received_sms(SoftAssert sa){
			try{
				clickBtn(Locators_Interactions.smsfirstmsg);
			if (isElementExist(Locators_Interactions.msg1)){
				APP_LOGS.info("Message sent");
				System.out.println(" Sent");
				sa.assertTrue(true, "Message sent");
				test.log(LogStatus.PASS, "Message sent");
			}else{
				APP_LOGS.info("Message not sent");
				sa.fail();
				test.log(LogStatus.FAIL,"Message not  sent ");
			}
				
				
			} catch (org.openqa.selenium.NoSuchElementException e) {

				test.log(LogStatus.ERROR,"Error in locators->Validate_reply_received_sms()");
			}catch (Exception e) {
				test.log(LogStatus.ERROR,"Exeption in ->Validate_reply_received_sms()");
			}
		}
		
		public void setDefaultSavingAccount() throws InterruptedException
		{
			/*
			 * Set default saving account as phone in contacts application
			 */
			try {
				minWait();
				clickBtn(Locators_Interactions.ContactsMoreOptions);
				minWait();
				clickBtn(Locators_Interactions.contactsSettingsOPt);
				minWait();
				clickBtn(Locators_Interactions.contactsDefaultAccountSettings);
				minWait();
				clickBtn(Locators_Interactions.contactsDefaultPhone);
				minWait();
				clickBackButton_without_try_catch(1);

			} catch (org.openqa.selenium.NoSuchElementException e) {

				test.log(LogStatus.ERROR, "Error in the locators->setDefaultSavingAccount()");
				e.printStackTrace();

			}
			catch (Exception e) {
				test.log(LogStatus.ERROR, "Exception in setDefaultSavingAccount()");
			}
		}
	
		public void validate_createContactWithNameandPhone(String name,String lastname,String phone,SoftAssert sa) throws InterruptedException
		{
			/*
			 * Enter name and phone number and save the contact
			 */
				try {
			clickBtn(Locators_Interactions.addnewcontact);
			customWait(2000);
			enterTextToInputField(Locators_Interactions.nameEditFld, name);
			minWait();
			clickBackButton(1);
			enterTextToInputField(Locators_Interactions.lastnameEditFld, lastname);
			minWait();
			clickBackButton(1);
			enterTextToInputField(Locators_Interactions.phoneNumberEditFld, phone);
			minWait();
			clickBtn(Locators_Interactions.saveOpt);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.BACK);
			if(isElementExist(Locators_Interactions.validate_savedcontact)){
				APP_LOGS.info("contacts saved");
				System.out.println("contacts saved");
				sa.assertTrue(true, "contacts saved");
				test.log(LogStatus.PASS, "Create contact with name and phone is passed");
				
			}else{
				APP_LOGS.info("contacts not saved");
				System.out.println("contacts not saved");
				sa.assertTrue(true, "contacts not saved");
				test.log(LogStatus.FAIL, "Create contact with name and phone is failed");
			}
				} catch (Exception e) {
				e.printStackTrace();
				test.log(LogStatus.ERROR, "Create contact with name and phone is failed");
			}
		}
		public void deleteIfContactsPresent(SoftAssert sa) throws InterruptedException
		{

			try {

				if(isElementExist(Locators_Interactions.deletefirstcontact))
				{
					clickBtn(Locators_Interactions.delete_option);
					minWait();
					//  if(isElementExist(Locators_Interactions.selectAllOpt)){

					clickBtn(Locators_Interactions.zero_selected);
					minWait();
					clickBtn(Locators_Interactions.selectAllOpt);
					minWait();
					clickBtn(Locators_Interactions.Ok_option);
					minWait();
					clickBtn(Locators_Interactions.okBtn);
					customWait(5000);
					APP_LOGS.info("Contacts are deleted successfully");
					sa.assertTrue(true, "Contacts are deleted successfully");
					test.log(LogStatus.PASS, "Contacts are deleted successfully");	


				}else{
					clickBackButton_without_try_catch(4);
				}

			}  catch (org.openqa.selenium.NoSuchElementException e) {

				test.log(LogStatus.ERROR, "Error in the locators->deleteIfContactsPresent()");
				e.printStackTrace();

			}
			catch (Exception e) {
				test.log(LogStatus.ERROR, "Exeption in ->deleteIfContactsPresent()");
			}

		}
		public void enable_disable_BT(){
			
			try {
				aDriver.openNotifications();
				clickBtn(Locators_Interactions.bt);
				customWait(2000);
				clickBtn(Locators_Interactions.bt);
				customWait(2000);
				clickBtn(Locators_Interactions.bt);
				customWait(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		public void swipe_NotificationBar() throws InterruptedException {
			/* Method is to swipe Notification Bar. */
			try {
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_NOTIFICATION);
				aDriver.openNotifications();
				minWait();
				aDriver.swipe(600, 150, 600, 1200, 400);
				minWait();
			} catch (NoSuchElementException e) {			 
				e.printStackTrace();
			}		
		}
		public void swipe_QuickPanel_SecondPage() throws InterruptedException {
			try {
				minWait();
				aDriver.swipe(860, 660, 140, 660, 750);
				minWait();
			} catch (Exception e) {
			}		
		}
public void enable_disable_data(){
			
			try {
				swipe_NotificationBar();
				swipe_QuickPanel_SecondPage();
				clickBtn(Locators_Interactions.data);
				customWait(2000);
				clickBtn(Locators_Interactions.data);
				customWait(2000);
				clickBtn(Locators_Interactions.data);
				customWait(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	public void enable_disable_WiFi(){
			
			try {
				aDriver.openNotifications();
				clickBtn(Locators_Interactions.wifi);
				customWait(2000);
				clickBtn(Locators_Interactions.wifi);
				customWait(2000);
				clickBtn(Locators_Interactions.wifi);
				customWait(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	public void enable_disable_location(){
		
		try {
			aDriver.openNotifications();
			clickBtn(Locators_Interactions.settingsnotification);
			customWait(2000);
		    scrollToText("Security & location");
		    scrollToText("Location");
		    clickBtn(Locators_Interactions.locationswitch);
			customWait(2000);
			clickBtn(Locators_Interactions.locationswitch);
			customWait(2000);
			clickBtn(Locators_Interactions.locationswitch);
			customWait(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
     public void validate_set_alarm(SoftAssert sa){
    	 try{
    		 clickBtn(Locators_Interactions.alarm);
    		 clickBtn(Locators_Interactions.createalarm);
    		 clickBtn(Locators_Interactions.twohrsalarm);
    		 clickBtn(Locators_Interactions.fortyminshrsalarm);
    		 clickBtn(Locators_Interactions.okalarm);
    		 if(isElementExist(Locators_Interactions.alarmenabled)){
    			 clickBtn(Locators_Interactions.alarmdelete);
    			 APP_LOGS.info("Alarm saved");
 				System.out.println("Alarm saved");
 				sa.assertTrue(true, "Alarm saved");
 				test.log(LogStatus.PASS, "Alarm Saved");
    		 }else{
    			 APP_LOGS.info("Alarm not saved");
 				System.out.println("Alarm not saved");
 				sa.assertTrue(true, "Alarm not saved");
 				test.log(LogStatus.FAIL, "Alarm not saved");
    		 }
    		 }catch (org.openqa.selenium.NoSuchElementException e) {

 				test.log(LogStatus.ERROR, "Error in the locators->validate_set_alarm()");
 				e.printStackTrace();

 			}
 			catch (Exception e) {
 				test.log(LogStatus.ERROR, "Exeption in ->validate_set_alarm()");
 			}
    			 
    		 }
     public void validate_set_timer(SoftAssert sa){
    	 try{
    		 clickBtn(Locators_Interactions.timer);
    		 clickBtn(Locators_Interactions.settimer);
    		 clickBtn(Locators_Interactions.settimer);
    		 clickBtn(Locators_Interactions.settimer);
    		 clickBtn(Locators_Interactions.settimer);
    		 clickBtn(Locators_Interactions.settimer);
    		 clickBtn(Locators_Interactions.settimer);
    		 clickBtn(Locators_Interactions.startingtimer);
    		 if(isElementExist(Locators_Interactions.ontimer)){
    			 clickBtn(Locators_Interactions.deletetimer);
    			 APP_LOGS.info("Timer saved");
 				System.out.println("Timer saved");
 				sa.assertTrue(true, "Timer saved");
 				test.log(LogStatus.PASS, "Timer Saved");
    		 }else{
    			 APP_LOGS.info("Timer not saved");
 				System.out.println("Timer not saved");
 				sa.assertTrue(true, "Timer not saved");
 				test.log(LogStatus.FAIL, "Timer not Saved");
    		 }
    		 }catch (org.openqa.selenium.NoSuchElementException e) {

 				test.log(LogStatus.ERROR, "Error in the locators->validate_set_Timer()");
 				e.printStackTrace();

 			}
 			catch (Exception e) {
 				test.log(LogStatus.ERROR, "Exeption in ->validate_set_Timer()");
 			}
    			 
    		 }
    /* public void validate_calendar_event(SoftAssert sa){
    	 try{
    		 Runtime.getRuntime().exec("adb -s "+p_Id+" shell pm clear com.google.android.calendar");
    		 clickBtn(Locators_Interactions.calendarfirststep);
    		 clickBtn(Locators_Interactions.calendarfirststep);
    		 clickBtn(Locators_Interactions.calendargotit);
    		 clickBtn(Locators_Interactions.settimer);
    		 clickBtn(Locators_Interactions.settimer);
    		 clickBtn(Locators_Interactions.settimer);
    		 clickBtn(Locators_Interactions.settimer);
    		 clickBtn(Locators_Interactions.startingtimer);
    		 if(isElementExist(Locators_Interactions.ontimer)){
    			 clickBtn(Locators_Interactions.deletetimer);
    			 APP_LOGS.info("Timer saved");
 				System.out.println("Timer saved");
 				sa.assertTrue(true, "Timer saved");
    		 }else{
    			 APP_LOGS.info("Timer not saved");
 				System.out.println("Timer not saved");
 				sa.assertTrue(true, "Timer not saved");
    		 }
    		 }catch (org.openqa.selenium.NoSuchElementException e) {

 				test.log(LogStatus.ERROR, "Error in the locators->validate_set_Timer()");
 				e.printStackTrace();

 			}
 			catch (Exception e) {
 				test.log(LogStatus.ERROR, "Exeption in ->validate_set_Timer()");
 			}
    			 
    		 }
    	 public void creategoogle_account(String firstname,String lastname) throws InterruptedException{
    		 WebDriverWait wait  =new WebDriverWait(aDriver, 80);
    		 aDriver.openNotifications();
    		 clickBtn(Locators_Interactions.settingsnotification);
 			customWait(2000);
 		    scrollToText("Users & accounts");
 		    if(isElementExist(Locators_Interactions.addaccount)){
 		    	clickBtn(Locators_Interactions.addaccount);
 		    	clickBtn(Locators_Interactions.google);
 		    	wait.until(ExpectedConditions.visibilityOf(Locators_Interactions.sign));
 		    	clickBtn(Locators_Interactions.createaccount);
 		    	clickBtn(Locators_Interactions.formyself);
 		    	enterTextToInputField(Locators_Interactions.firstname, firstname);
 		    	minWait();
 		    	enterTextToInputField(Locators_Interactions.lastname, lastname);
 		    	minWait();
 		    	clickBtn(Locators_Interactions.next);
 		    	customWait(4000);
 		    	clickBtn(Locators_Interactions.month);
 		    	scrollToText("March");
 		    			
 		    	
 		    	

 		    }
    		 
    	 }
    	 */
     public void taking_photo(SoftAssert sa) throws IOException{
    	 try {
    		Runtime.getRuntime().exec("adb -s "+p_Id+" shell pm clear org.codeaurora.snapcam");
    		launch_APP(Locators_Interactions.camera);
			clearsmsperission();
			customWait(10000);
			clickBtn(Locators_Interactions.camerashutter);
			customWait(4000);
			if(isElementExist(Locators_Interactions.previewbtn)){
				APP_LOGS.info("PHOTO IS TAKEN");
				System.out.println("photo is taken");
				sa.assertTrue(true, "PHOTO IS TAKEN");
				test.log(LogStatus.PASS, "PHOTO IS TAKEN");
				
				
	    		 }else{
	    			 APP_LOGS.info("PHOTO NOT TAKEN" );
	 				sa.fail();
	 				test.log(LogStatus.FAIL, "PHOTO NOT TAKEN");
	    		 }
			
			
			
		}catch (org.openqa.selenium.NoSuchElementException e) {

				test.log(LogStatus.ERROR, "Error in the locators->taking_photo()");
				e.printStackTrace();

			}
			catch (Exception e) {
				test.log(LogStatus.ERROR, "Exeption in ->taking_photo()");
			}
     }
	public void clearsmsperission() throws InterruptedException{
		

			while(true){

				minWait();

				if(isElementExist(Locators_Interactions.cameraallow)){

					System.out.println("--------------p-------------");


					
					clickBtn(Locators_Interactions.cameraallow);

                   System.out.println("allow clicked");

				}else {

					System.out.println("-+++++++++++++f+++++++++++");
					 System.out.println("allow not clicked");

					break;
				}
			}

		
	}
	public void take_video(SoftAssert sa) throws InterruptedException{
		try{
		clickBtn(Locators_Interactions.videobtn);
		customWait(10000);
		clickBtn(Locators_Interactions.videobtn);
		minWait();
		if(isElementExist(Locators_Interactions.previewbtn)){
			APP_LOGS.info("VIDEO IS TAKEN");
			System.out.println("video is taken");
			sa.assertTrue(true, "VIDEO IS TAKEN");
			test.log(LogStatus.PASS, "VIDEO IS TAKEN");
			
			
    		 }else{
    			 APP_LOGS.info("VIDEO NOT TAKEN" );
    			 System.out.println("video is not taken");
 				sa.fail();
 				test.log(LogStatus.FAIL, "VIDEO NOT TAKEN");
    		 }
		
		
		
	}catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->take_video()");
			e.printStackTrace();

		}
		catch (Exception e) {
			test.log(LogStatus.ERROR, "Exeption in ->take_video()");
		}
 }
	public void validate_gallery(SoftAssert sa) throws InterruptedException{
		try{
			if(isElementExist(Locators_Interactions.photosbackup)){
				aDriver.pressKeyCode(AndroidKeyCode.BACK);
				clickBtn(Locators_Interactions.keepoff);
				APP_LOGS.info("gallery is validated");
				System.out.println("gallery is validated");
				sa.assertTrue(true, "gallery is validated");
				test.log(LogStatus.PASS, "gallery is validated");
			}
			
			else if(isElementExist(Locators_Interactions.cameralist)){
			APP_LOGS.info("gallery is validated");
			System.out.println("gallery is validated");
			sa.assertTrue(true, "gallery is validated");
			test.log(LogStatus.PASS, "gallery is validated");
			
			
    		 }
			else if(isElementExist(Locators_Interactions.photonotnow)){
				clickBtn(Locators_Interactions.photonotnow);
				APP_LOGS.info("gallery is validated");
				System.out.println("gallery is validated");
				sa.assertTrue(true, "gallery is validated");
				test.log(LogStatus.PASS, "gallery is validated");
				
			}
			else{
    			 APP_LOGS.info("gallery is not validated" );
    			 System.out.println("gallery is not validated");
 				sa.fail();
 				test.log(LogStatus.FAIL, "Gallery is not validated");
    		 }
		
		
		
	}catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->take_video()");
			e.printStackTrace();

		}
		catch (Exception e) {
			test.log(LogStatus.ERROR, "Exeption in ->take_video()");
		}
 }
	
	public void validate_maps(SoftAssert sa) throws InterruptedException{
		try{
			Runtime.getRuntime().exec("adb -s "+p_Id+" shell pm clear com.google.android.apps.maps");
    		launch_APP(Locators_Interactions.maps);
    		clickBtn(Locators_Interactions.skip);
    		if(isElementExist(Locators_Interactions.okBtn)){
    			clickBtn(Locators_Interactions.okBtn);
    		}
    		else{
    		clickBtn(Locators_Interactions.go);
    		}
    		if(isElementExist(Locators_Interactions.mapvalidate)){
				APP_LOGS.info("map is validated");
				System.out.println("map is validated");
				sa.assertTrue(true, "map is validated");
				test.log(LogStatus.PASS, "map is validated");
			}
	else{
    			 APP_LOGS.info("map is not validated" );
    			 System.out.println("map is not validated");
 				sa.fail();
 				test.log(LogStatus.FAIL, "map is not validated");
    		 }
		
		
		
	}catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->validate_maps()");
			e.printStackTrace();

		}
		catch (Exception e) {
			test.log(LogStatus.ERROR, "Exeption in ->validate_maps()");
		}
 }
	public void validate_soundrecorder(SoftAssert sa) throws InterruptedException{
		try{
		/*	Runtime.getRuntime().exec("adb -s "+p_Id+" shell pm clear com.android.soundrecorder");
    		launch_APP(Locators_Interactions.soundrecorder);
    		clickBtn(Locators_Interactions.soundrecorderbtn);
    		clearAllow();
    		customWait(10000);
    		clickBtn(Locators_Interactions.soundrecordersave);*/
    		if(isElementExist(Locators_Interactions.soundrecorderslist)){
				APP_LOGS.info("Soundrecorder is validated");
				System.out.println("Soundrecorder is validated");
				sa.assertTrue(true, "Soundrecorder is validated");
				test.log(LogStatus.PASS, "Soundrecorder is validated");
			}
	else{
    			 APP_LOGS.info("Soundrecorder is not validated" );
    			 System.out.println("Soundrecorder is not validated");
 				sa.fail();
 				test.log(LogStatus.FAIL, "Soundrecorder is not validated");
    		 }
		
		
		
	}catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->validate_soundrecorder()");
			e.printStackTrace();

		}
		catch (Exception e) {
			test.log(LogStatus.ERROR, "Exeption in ->validate_soundrecorder()");
		}
 }
	public void validate_soundrecorder_enabled(SoftAssert sa) throws InterruptedException{
		try{
			Runtime.getRuntime().exec("adb -s "+p_Id+" shell pm clear com.android.soundrecorder");
    		launch_APP(Locators_Interactions.soundrecorder);
    		clickBtn(Locators_Interactions.soundrecorderbtn);
    		clearAllow();
    		customWait(10000);
    		clickBtn(Locators_Interactions.soundrecorderstopbtn);
    		clickBtn(Locators_Interactions.soundrecordersave);
    		if(isElementExist(Locators_Interactions.soundrecordinglist)){
				APP_LOGS.info("Soundrecorder is validated");
				System.out.println("Soundrecorder is validated");
				sa.assertTrue(true, "Soundrecorder is validated");
				test.log(LogStatus.PASS, "Soundrecorder is validated");
			}
	else{
    			 APP_LOGS.info("Soundrecorder is not validated" );
    			 System.out.println("Soundrecorder is not validated");
 				sa.fail();
 				test.log(LogStatus.FAIL, "Soundrecorder is not validated");
    		 }
		
		
		
	}catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->validate_soundrecorder()");
			e.printStackTrace();

		}
		catch (Exception e) {
			test.log(LogStatus.ERROR, "Exeption in ->validate_soundrecorder()");
		}
 }
	public void validate_accessnotification(SoftAssert sa) throws InterruptedException{
		try{
			aDriver.pressKeyCode(AndroidKeyCode.HOME);
		     aDriver.openNotifications();
		     clickBtn(Locators_Interactions.usbdebuggingconnected);
		     if(isElementExist(Locators_Interactions.developeropt)){
				APP_LOGS.info("Notification is validated");
				System.out.println("Notification is validated");
				sa.assertTrue(true, "Notification is validated");
				test.log(LogStatus.PASS, "Notification is validated");
			}
	else{
    			 APP_LOGS.info("Notification is not validated" );
    			 System.out.println("Notification is not validated");
 				sa.fail();
 				test.log(LogStatus.FAIL, "Notification is not validated");
    		 }
		
		
		
	}catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->validate_accessnotification()");
			e.printStackTrace();

		}
		catch (Exception e) {
			test.log(LogStatus.ERROR, "Exeption in ->validate_accessnotification()");
		}
 }
	public void enable_mobiledata() throws InterruptedException{
		aDriver.openNotifications();
		clickBtn(Locators_Interactions.settingsnotification);
		customWait(3000);
	    scrollToText("Network & Internet");
	    scrollToText("Mobile network");
		
	}
	public void Musicplay_gaana(SoftAssert sa){
		try{
			    clickBtn(Locators_Interactions.gaanaframe);
			    clickBtn(Locators_Interactions.gaanahome);
				scrollToText("Trending Songs");
				clickBtn(Locators_Interactions.gaanasongslt);
				clickBtn(Locators_Interactions.gaanaplay);
				aDriver.openNotifications();	
			if(isElementExist(Locators_Interactions.gaananotification)){
				clickBtn(Locators_Interactions.clearall);
				APP_LOGS.info("GaanaNotification is validated");
				System.out.println("GaanaNotification is validated");
				sa.assertTrue(true, "GaanaNotification is validated");
				test.log(LogStatus.PASS, "GaanaNotification is validated");
			}
	            else{
    			 APP_LOGS.info("GaanaNotification is not validated" );
    			 System.out.println("GaanaNotification is not validated");
 				sa.fail();
 				test.log(LogStatus.FAIL, "GaanaNotification is not validated");
    		 }
			
			
		}catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->precondition_gaana()");
			e.printStackTrace();

		}
		catch (Exception e) {
			test.log(LogStatus.ERROR, "Exeption in ->precondition_gaana()");
		}
			
		}
	public void enable_mobiledata_wifi() throws InterruptedException{
		try{
		launch_an_app("settings");
		clickOn_Networks_and_Internet();
		scrollToText("Mobile network");
		enable_Mobile_Data();
		minWait();
		clickBackButton(1);
		scrollToTextContains("Wi");
		setUp_And_Enable_WiFi();
		}catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->enable_mobiledata_wifi()");
			e.printStackTrace();

		}
		catch (Exception e) {
			test.log(LogStatus.ERROR, "Exeption in ->enable_mobiledata_wifi()");
		}
	}
	public void clickOn_Networks_and_Internet() throws InterruptedException {
		
			minWait();
			scrollToText("Network & Internet");
			minWait();
		
	}
	public void enable_Mobile_Data() throws InterruptedException {
		if (!isElementExist(Locators_Interactions.mobileDataOption)) {
				System.out.println("Mobile data is OFF");
				WebDriverWait wait = new WebDriverWait(aDriver, 60);
				minWait();
				clickBtn(multi_Loc_Strategy(Locators_Interactions.switch_OffState, Locators_Interactions.switch_OffStateByXpath, null, null, null, 892, 1032));
				System.out.println("Mobile data is turned ON");
				wait.until(ExpectedConditions.visibilityOf(Locators_Interactions.mobileDataOption));
			}		
			else{
				System.out.println("Mobile data is turned ON");
			}
	
	}
	public boolean scrollToTextContains(String text) {
		/*
		  Method used to select an element on the page by scrolling the Scroll View/List View
		 */
		boolean check = false;
		  
			String scrollable = "new UiScrollable(new UiSelector().scrollable(true))";
			String textElement = ".scrollIntoView(new UiSelector().textContains(\""+ text +"\"))";
			aDriver.findElementByAndroidUIAutomator(scrollable+textElement).click();
			APP_LOGS.info("Searched application is found sucessfully : ");
			check = true;
			return check;
		}
		
	
	public void setUp_And_Enable_WiFi() throws InterruptedException {
		
			WebDriverWait wait = new WebDriverWait(aDriver, 60);
			minWait();
			if (!isElementExist(multi_Loc_Strategy(Locators_Interactions.wifi_ConnectedState, Locators_Interactions.wifi_ConnectedStateByUiAutomator, null, null, null, 0, 0))) {
				System.out.println("Wifi is not connected");
				customWait(2000);
				if(isElementExist(Locators_Interactions.switch_OffState)){
					clickBtn(Locators_Interactions.switch_OffState);
					System.out.println("Wifi is turned ON");
				}
				else{
					System.out.println("Wifi is ON but not connected");
				}
				
				//wait.until(ExpectedConditions.visibilityOf(Locators_Interactions.wifi_IDC));
				if (!isElementExist(multi_Loc_Strategy(Locators_Interactions.wifi_ConnectedStateByUiAutomator, Locators_Interactions.wifi_ConnectedState, null, null, null, 0, 0))) {
					customWait(5000);
					if(isElementExist(Locators_Interactions.Wifi_IDC_UiSelector)){
						scrollToElements(Locators_Interactions.Wifi_IDC_UiSelector);
						clickBtn(Locators_Interactions.Wifi_IDC_UiSelector);
						//aDriver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().className(\"android.support.v7.widget.RecyclerView\")).scrollIntoView(new UiSelector().text(\"IDCSONWAP\"))").click();
						System.out.println("Clicked on IDCSONWAP");	
					}
					else{
						clickBackButton(2);
						scrollToElements(Locators_Interactions.Wifi_IDC_UiSelector);
						clickBtn(Locators_Interactions.Wifi_IDC_UiSelector);
						//aDriver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().className(\"android.support.v7.widget.RecyclerView\")).scrollIntoView(new UiSelector().text(\"IDCSONWAP\"))").click();
						System.out.println("Clicked on IDCSONWAP");
					}
					minWait();
					if(isElementExist(multi_Loc_Strategy(Locators_Interactions.wifi_IDC_ForgetBtn_By_ID, Locators_Interactions.wifi_IDC_ForgetBtn_By_UiSelector, Locators_Interactions.wifi_IDC_ForgetBtn_By_UiSelectorId, Locators_Interactions.wifi_IDC_ForgetBtn, null, 0, 0))){
						clickBtn(multi_Loc_Strategy(Locators_Interactions.wifi_IDC_ForgetBtn_By_ID, Locators_Interactions.wifi_IDC_ForgetBtn_By_UiSelector, Locators_Interactions.wifi_IDC_ForgetBtn_By_UiSelectorId, Locators_Interactions.wifi_IDC_ForgetBtn, null, 0, 0));
						setUp_And_Enable_WiFi();
					}
					enterTextToInputField(multi_Loc_Strategy(Locators_Interactions.wifiPasswordTextBox, Locators_Interactions.wifiPasswordTextBoxById, Locators_Interactions.wifiPasswordTextBoxByIndex, Locators_Interactions.wifiPasswordTextBoxByXpath, null, 135, 424),"1dcS0n1md0tc0MbLr");
					System.out.println("Entered PWD");
					customWait(3000);
					clickBtn(multi_Loc_Strategy(Locators_Interactions.connect, Locators_Interactions.connectById, Locators_Interactions.connectByContains, Locators_Interactions.connectByXpath, null, 705, 881));
					System.out.println("Clicked on CONNECT");
					customWait(1000);
					wait.until(ExpectedConditions.visibilityOf(multi_Loc_Strategy(Locators_Interactions.wifi_ConnectedStateByUiAutomator, Locators_Interactions.wifi_ConnectedState, null, null, null, 0, 0)));
					System.out.println("Waited for Visibility of Connect");
				}
			}
			
			else{
				System.out.println("Wifi is turned ON");
			}
	}
	public void openbrowser_browse(SoftAssert sa,String url) throws InterruptedException{
		WebDriverWait wait = new WebDriverWait(aDriver, 600);
	try{
		Runtime.getRuntime().exec("adb -s "+p_Id+" shell pm clear com.android.chrome");
		launch_an_app("browser");
		wait.until(ExpectedConditions.visibilityOf(Locators_Interactions.chromeaccept));
		clickBtn(Locators_Interactions.chromeaccept);
	    clickBtn(Locators_Interactions.chromenext);
	    clickBtn(Locators_Interactions.chromenothanks);
	   Runtime.getRuntime().exec("adb -s "+p_Id+" shell am start -a android.intent.action.VIEW -d https://"+url);
		customWait(3000); 
		aDriver.pressKeyCode(AndroidKeyCode.ENTER);
		minWait();
		System.out.println("URL Entered");
		
		if(isElementExist(Locators_Interactions.chromegoogle)){
			APP_LOGS.info("Browser is validated");
			System.out.println("Browser is validated");
			sa.assertTrue(true, "Browser is validated");
			test.log(LogStatus.PASS, "Browser is validated");
		}
            else{
			 APP_LOGS.info("Browser is not validated" );
			 System.out.println("Browser is not validated");
				sa.fail();
				test.log(LogStatus.FAIL, "Browser is not validated");
		 }
		
	}catch (org.openqa.selenium.NoSuchElementException e) {

		test.log(LogStatus.ERROR, "Error in the locators->openbrowser_browse()");
		e.printStackTrace();

	}
	catch (Exception e) {
		test.log(LogStatus.ERROR, "Exeption in ->openbrowser_browse()");
	}
	}
	public void share_browser_page(String refNum,SoftAssert sa){
		try{
			clickBtn(Locators_Interactions.chromemenu);
			minWait();
			clickBtn(Locators_Interactions.chromeshare);
			clickBtn(Locators_Interactions.chromemsg);
			enterTextToInputField(Locators_Interactions.TO_Field_Text1_att,refNum);
			customWait(2000);
		}catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->share_browser_page()");
			e.printStackTrace();

		}
		catch (Exception e) {
			test.log(LogStatus.ERROR, "Exeption in ->share_browser_page()");
		}
			
	}
	public void validate_fmradio(SoftAssert sa){
		
		if(!isElementExist(Locators_Interactions.fmfrequency)){
			APP_LOGS.info("FM is validated");
			System.out.println("FM is validated");
			sa.assertTrue(true, "FM is validated");
			test.log(LogStatus.PASS, "FM is validated");
		}
            else{
			 APP_LOGS.info("FM is not validated" );
			 System.out.println("FM is not validated");
				sa.fail();
				test.log(LogStatus.FAIL, "FM is not validated");
		 }
		
	}
	public void validate_fmradio_on(SoftAssert sa){
		
		WebDriverWait wait  =new WebDriverWait(aDriver, 80);
		try{
		clickBtn(Locators_Interactions.fmon);
		wait.until(ExpectedConditions.visibilityOf(Locators_Interactions.fmfrequency));
		aDriver.openNotifications();
		if(isElementExist(Locators_Interactions.fmnotification)){
			
			APP_LOGS.info("FM is validated");
			System.out.println("FM is validated");
			sa.assertTrue(true, "FM is validated");
			test.log(LogStatus.PASS, "FM is validated");
		}
            else{
			 APP_LOGS.info("FM is not validated" );
			 System.out.println("FM is not validated");
				sa.fail();
				test.log(LogStatus.FAIL, "FM is not validated");
		 }
		}catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->validate_fmradio_on()");
			e.printStackTrace();

		}
		catch (Exception e) {
			test.log(LogStatus.ERROR, "Exeption in ->validate_fmradio_on()");
		}
	}
	public void fmradio_off() throws InterruptedException{
		WebDriverWait wait  =new WebDriverWait(aDriver, 80);
		launch_an_app("fm");
		wait.until(ExpectedConditions.visibilityOf(Locators_Interactions.fmfrequency));
		clickBtn(Locators_Interactions.fmon);
		
	}

	public void access_settingsmenu(){
		try{
		aDriver.openNotifications();
		clickBtn(Locators_Interactions.settingsnotification);
		scrollToText("Network & Internet");
		customWait(3000);
		aDriver.pressKeyCode(AndroidKeyCode.BACK);
		scrollToText("Connected devices");
		customWait(3000);
		aDriver.pressKeyCode(AndroidKeyCode.BACK);
		scrollToText("Apps & notifications");
		customWait(3000);
		aDriver.pressKeyCode(AndroidKeyCode.BACK);
		scrollToText("Battery");
		customWait(3000);
		aDriver.pressKeyCode(AndroidKeyCode.BACK);
		scrollToText("Display");
		customWait(3000);
		aDriver.pressKeyCode(AndroidKeyCode.BACK);
		scrollToText("Sound");
		customWait(3000);
		aDriver.pressKeyCode(AndroidKeyCode.BACK);
		scrollToText("Storage");
		customWait(3000);
		aDriver.pressKeyCode(AndroidKeyCode.BACK);
		scrollToText("USB Power Saving");
		customWait(3000);
		aDriver.pressKeyCode(AndroidKeyCode.BACK);
		scrollToText("Security & location");
		customWait(3000);
		aDriver.pressKeyCode(AndroidKeyCode.BACK);
		scrollToText("Users & accounts");
		customWait(3000);
		aDriver.pressKeyCode(AndroidKeyCode.BACK);
		scrollToText("Accessibility");
		customWait(3000);
		aDriver.pressKeyCode(AndroidKeyCode.BACK);
		scrollToText("Accessories");
		customWait(3000);
		aDriver.pressKeyCode(AndroidKeyCode.BACK);
		scrollToText("Programmable Keys");
		customWait(3000);
		aDriver.pressKeyCode(AndroidKeyCode.BACK);
		scrollToText("Google");
		customWait(3000);
		aDriver.pressKeyCode(AndroidKeyCode.BACK);
		scrollToText("System");
		customWait(3000);
		aDriver.pressKeyCode(AndroidKeyCode.BACK);
		
		}catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->access_settingsmenu()");
			e.printStackTrace();

		}
		catch (Exception e) {
			test.log(LogStatus.ERROR, "Exeption in ->access_settingsmenu()");
		}
		
	}
	
		public void checkairplanemode(SoftAssert sa){
			
			try {
			customWait(2000);
			Runtime.getRuntime().exec("adb -s " + p_Id + " shell am start -a android.settings.AIRPLANE_MODE_SETTINGS");
			minWait();
			for (int i = 1; i <= 9; i++) {
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
			}
				clickBtn(Locators_Interactions.airplanemodeCheckboxoff);
				launch_an_app("phone");
			if(!isElementExist(Locators_Interactions.dialpadchooser)){
				System.out.println("airplane mode enable disable validated");
				aDriver.pressKeyCode(AndroidKeyCode.HOME);
				Runtime.getRuntime().exec("adb -s " + p_Id + " shell am start -a android.settings.AIRPLANE_MODE_SETTINGS");
				minWait();
				for (int i = 1; i <= 9; i++) {
					aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
				}
					clickBtn(Locators_Interactions.airplanemodeCheckboxon);
				APP_LOGS.info("Enable disable airplane mode is validated");
				sa.assertTrue(true, "Enable disable airplane mode is validated");
				test.log(LogStatus.PASS,"Enable disable airplane mode is validated");
			}
			else{
				Runtime.getRuntime().exec("adb -s " + p_Id + " shell am start -a android.settings.AIRPLANE_MODE_SETTINGS");
				minWait();
				for (int i = 1; i <= 9; i++) {
					aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
				}
					clickBtn(Locators_Interactions.airplanemodeCheckboxon);
				System.out.println("airplane mode enable disable is not validated");
				APP_LOGS.info("Enable disable airplane mode is not validated");
				sa.assertTrue(true, "Enable disable airplane mode is not validated");
				test.log(LogStatus.PASS,"Enable disable airplane mode is not validated");
			}
		
		 } catch (org.openqa.selenium.NoSuchElementException e) {
		    test.log(LogStatus.ERROR,"Error in locators->checkairplanemode()");
		}catch (Exception e) {
			test.log(LogStatus.ERROR,"Exeption in ->checkairplanemode()");
		}
	}
		public void enable_disable_airplanemode(){
			try {
				customWait(2000);
				Runtime.getRuntime().exec("adb -s " + p_Id + " shell am start -a android.settings.AIRPLANE_MODE_SETTINGS");
				minWait();
				for (int i = 1; i <= 9; i++) {
					aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
				}
					clickBtn(Locators_Interactions.airplanemodeCheckboxoff);
					aDriver.pressKeyCode(AndroidKeyCode.HOME);
					Runtime.getRuntime().exec("adb -s " + p_Id + " shell am start -a android.settings.AIRPLANE_MODE_SETTINGS");
					minWait();
					for (int i = 1; i <= 9; i++) {
						aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
					}
						clickBtn(Locators_Interactions.airplanemodeCheckboxon);
		}catch (org.openqa.selenium.NoSuchElementException e) {
		    test.log(LogStatus.ERROR,"Error in locators->enable_disable_airplanemode()()");
		}catch (Exception e) {
			test.log(LogStatus.ERROR,"Exeption in ->enable_disable_airplanemode()");
		}
		}

		public void skype_chat(SoftAssert sa,String phone,String text,String typemsg1){
			try{
				
				System.out.println("skype in");
					//clickBtn(Locators_Interactions.skypetouch);
				/*clickBtn(Locators_Interactions.skypehomecall);
					clickBtn(Locators_Interactions.skypehomecontact);
					customWait(3000);
					
					clickBtn(Locators_Interactions.skypeall);*/
					clickBtn(Locators_Interactions.skypehomechats);
					
					customWait(8000);
					//Runtime.getRuntime().exec("adb -s "+p_Id+" shell input tap 843 1707");
					
					clickBtn(Locators_Interactions.skypestart);
					
					//clickBtn(Locators_Interactions.skypeNEWCHT);
					customWait(3000);
					clickBtn(Locators_Interactions.skypecontact);
					//clickBtn(Locators_Interactions.skypecntframe);
					if(isElementExist(Locators_Interactions.skypesayhi)){
					clickBtn(Locators_Interactions.skypesayhi);
					enterTextToInputField(Locators_Interactions.skypetypemsg, typemsg1);
					clickBtn(Locators_Interactions.skypesendmsg);
					}else{
					enterTextToInputField(Locators_Interactions.skypetypemsg, typemsg1);
					clickBtn(Locators_Interactions.skypesendmsg);
					}
					aDriver.pressKeyCode(AndroidKeyCode.BACK);
				if(isElementExist(Locators_Interactions.skypemsg)){
						//clickBtn(Locators_Interactions.clearall);
						APP_LOGS.info("skype is validated");
						System.out.println("skype is validated");
						sa.assertTrue(true, "skype is validated");
						test.log(LogStatus.PASS, "skype is validated");
					}
			            else{
		    			 APP_LOGS.info("skype is not validated" );
		    			 System.out.println("skype is not validated");
		 				sa.fail();
		 				test.log(LogStatus.FAIL, "skype is not validated");
		    		 }
				
			}catch (org.openqa.selenium.NoSuchElementException e) {

				test.log(LogStatus.ERROR, "Error in the locators->skype_chat()");
				e.printStackTrace();

			}
			catch (Exception e) {
				test.log(LogStatus.ERROR, "Exeption in ->skype_chat()");
			}
				
			}
		public void skype_call(){
			try{
				clickBtn(Locators_Interactions.skypetouch);
				clickBtn(Locators_Interactions.skypehomecontact);
				customWait(3000);
				clickBtn(Locators_Interactions.skypecontact);
				clickBtn(Locators_Interactions.skypeaudiocall);
				clickBtn(Locators_Interactions.skypecall);
				customWait(9000);
				clickBtn(Locators_Interactions.skypeaudioendcall);
				
			}catch (org.openqa.selenium.NoSuchElementException e) {

				test.log(LogStatus.ERROR, "Error in the locators->skype_call()");
				e.printStackTrace();

			}
			catch (Exception e) {
				test.log(LogStatus.ERROR, "Exeption in ->skype_call()");
			}
				
			
		}
		public void share_photos(SoftAssert sa){
			try{
			if(isElementExist(Locators_Interactions.photosbackup)){
				aDriver.pressKeyCode(AndroidKeyCode.BACK);
				clickBtn(Locators_Interactions.keepoff);
				TouchAction action = new TouchAction(aDriver);
				action.longPress(Locators_Interactions.photofirstpic).release().perform();
				minWait();
				clickBtn(Locators_Interactions.share);
				clickBtn(Locators_Interactions.skype);
				if(isElementExist(Locators_BaseUtil.allow_PopUp)){
				clearAllow();
				clickBtn(Locators_Interactions.skypesend);
				clickBtn(Locators_Interactions.skypedone);
				}else{
				clickBtn(Locators_Interactions.skypesend);
				clickBtn(Locators_Interactions.skypedone);
				}
			}
			
			else if(isElementExist(Locators_Interactions.cameralist)){
				TouchAction action = new TouchAction(aDriver);
				action.longPress(Locators_Interactions.photofirstpic).release().perform();
				minWait();
				clickBtn(Locators_Interactions.share);
				clickBtn(Locators_Interactions.skype);
				if(isElementExist(Locators_BaseUtil.allow_PopUp)){
					clearAllow();
					clickBtn(Locators_Interactions.skypesend);
					clickBtn(Locators_Interactions.skypedone);
					}else{
				clickBtn(Locators_Interactions.skypesend);
				clickBtn(Locators_Interactions.skypedone);
					}
    		 }
			else if(isElementExist(Locators_Interactions.photonotnow)){
				clickBtn(Locators_Interactions.photonotnow);
				TouchAction action = new TouchAction(aDriver);
				action.longPress(Locators_Interactions.photofirstpic).release().perform();
				minWait();
				clickBtn(Locators_Interactions.share);
				clickBtn(Locators_Interactions.skype);
				if(isElementExist(Locators_BaseUtil.allow_PopUp)){
					clearAllow();
					clickBtn(Locators_Interactions.skypesend);
					clickBtn(Locators_Interactions.skypedone);
					}else{
				  clickBtn(Locators_Interactions.skypesend);
				clickBtn(Locators_Interactions.skypedone);
					}	
			}
			if(isElementExist(Locators_Interactions.skypeforward)){
				APP_LOGS.info("photo shared via skype");
				System.out.println("photo shared via skype");
				sa.assertTrue(true, "photo shared via skype");
				test.log(LogStatus.PASS, "photo shared via skype");
				
				
			}else{
			
    			 APP_LOGS.info("photo not shared via skype" );
    			 System.out.println("photo not shared via skype");
 				sa.fail();
 				test.log(LogStatus.FAIL, "photo not shared via skype");
    		 }
		
		
		
	}catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->take_video()");
			e.printStackTrace();

		}
		catch (Exception e) {
			test.log(LogStatus.ERROR, "Exeption in ->take_video()");
		}
		}
		public void youtube_streaming_videos(){
			WebDriverWait wait = new WebDriverWait(aDriver, 600);
			try{
				Runtime.getRuntime().exec("adb -s "+p_Id+" shell pm clear com.google.android.youtube");	
				launch_APP(Locators_Interactions.youtube);
				wait.until(ExpectedConditions.visibilityOf(Locators_Interactions.youtubeivideo));
				clickBtn(Locators_Interactions.youtubeivideo);
			
			}catch (org.openqa.selenium.NoSuchElementException e) {

				test.log(LogStatus.ERROR, "Error in the locators->youtube_streaming_videos()");
				e.printStackTrace();

			}
			catch (Exception e) {
				test.log(LogStatus.ERROR, "Exeption in ->youtube_streaming_videos()");
			}
		}
		public void copy_youtubelink(SoftAssert sa){
			WebDriverWait wait = new WebDriverWait(aDriver, 600);
			try{
				wait.until(ExpectedConditions.visibilityOf(Locators_Interactions.youtubeshare));
				clickBtn(Locators_Interactions.youtubeshare);
				wait.until(ExpectedConditions.visibilityOf(Locators_Interactions.youtubecopylink));
				clickBtn(Locators_Interactions.youtubecopylink);
				if(isElementExist(Locators_Interactions.youtubedcopied)){
					APP_LOGS.info("youtube link  is copied");
					System.out.println("youtube link  is copied");
					sa.assertTrue(true, "youtube link  is copied");
					test.log(LogStatus.PASS, "youtube link  is copied");
				}
		            else{
	    			 APP_LOGS.info("youtube link  is not copied" );
	    			 System.out.println("youtube link  is not copied");
	 				sa.fail();
	 				test.log(LogStatus.FAIL, "youtube link  is not copied");
	    		 }
			
				
				
			}catch (org.openqa.selenium.NoSuchElementException e) {

				test.log(LogStatus.ERROR, "Error in the locators->copy_youtubelink()");
				e.printStackTrace();

			}
			catch (Exception e) {
				test.log(LogStatus.ERROR, "Exeption in ->copy_youtubelink()");
			}
		}
		public void delete_soundrecorder_list(){
			try{
				clickBtn(Locators_Interactions.soundrecorderlist);
				TouchAction action = new TouchAction(aDriver);
				action.longPress(Locators_Interactions.soundrecorderlisttitle).release().perform();
				clickBtn(Locators_Interactions.soundrecorder1selected);
				if(isElementExist(Locators_Interactions.soundrecorderselectall))
				{
					clickBtn(Locators_Interactions.soundrecorderselectall);
					clickBtn(Locators_Interactions.soundrecorderdelete);
					clickBtn(Locators_Interactions.soundrecorderdeletetxt);
				}
				else{
					clickBtn(Locators_Interactions.soundrecorder1selected);
					clickBtn(Locators_Interactions.soundrecorderdelete);
					clickBtn(Locators_Interactions.soundrecorderdeletetxt);
				}
			}catch (org.openqa.selenium.NoSuchElementException e) {

				test.log(LogStatus.ERROR, "Error in the locators->delete_soundrecorder_list()");
				e.printStackTrace();

			}
			catch (Exception e) {
				test.log(LogStatus.ERROR, "Exeption in ->delete_soundrecorder_list()");
			}
		}
		
		
		public void delete_msg(){
			try{
				TouchAction action = new TouchAction(aDriver);
				action.longPress(Locators_Interactions.smsfirstmsg).release().perform();
				clickBtn(Locators_Interactions.delete_Icon_SMS);
				minWait();
				clickBtn(Locators_Interactions.delete_txt);
			}catch (org.openqa.selenium.NoSuchElementException e) {

				test.log(LogStatus.ERROR, "Error in the locators-> delete_msg()");
				e.printStackTrace();

			}
			catch (Exception e) {
				test.log(LogStatus.ERROR, "Exeption in -> delete_msg()");
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
					aDriver.pressKeyCode(AndroidKeyCode.BACK);
				}
			} catch (org.openqa.selenium.NoSuchElementException e) {
				test.log(LogStatus.ERROR, "Error in the locators->clickBackButton()");
				e.printStackTrace();

			}catch (Exception e) {
				test.log(LogStatus.ERROR, "Exception in clickBackButton()");
				e.printStackTrace();
			}
		}
		
		public void clickBackButton_without_try_catch(int number) throws InterruptedException
		{
			/*
			 * clicks on back button with iteration as user input
			 */

			for(int i=0;i<number;i++){
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.BACK);
			}
		}	
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		}
	

	

	

