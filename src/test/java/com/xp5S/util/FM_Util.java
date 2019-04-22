package com.xp5S.util;

import static org.testng.Assert.fail;

import java.io.IOException;

import org.openqa.selenium.NoSuchElementException;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.asserts.SoftAssert;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidKeyCode;




public class FM_Util extends BaseUtil{
	boolean check1 = false;
	boolean check2 = false;
	boolean check3 = false;
	boolean check4 = false;
	public boolean check = false;
	public SoftAssert softAssert;
	public ExtentTest StatusResult;
  
	//Launch FM App
	public void launchFM() throws InterruptedException {
		launchApp("FM_PACKAGE","FM_ACTIVITY");
		APP_LOGS.info("FM Radio Launched Succesfully");
	}

	//Validate String FM and Record Ic
	public void validateFMLaunch() throws InterruptedException {
		try {
			customWait(4000);
			if(isElementExist(Locators_FM.ChannelTxt)){
				check = true;
				APP_LOGS.info("FM Radio Channel Text is present.");				
				APP_LOGS.info("FM Radio Launched succesful.");
				Assert.assertTrue(check);
			}
			else {
				APP_LOGS.info("FM Radio is not  Launched unsuccesful.");
				Assert.fail();
			}
		}catch (org.openqa.selenium.NoSuchElementException e) {
			APP_LOGS.info("No Element Found.");
			e.printStackTrace();
			Assert.fail();
		}
	}


	//FM toggle Switch Off
	public void turnOFF_FM() throws InterruptedException {
		  /*
		   * Select Radio off option
		   */
		  SoftAssert Sf = new SoftAssert();
		  try {
		   customWait(2000);
		   aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_MENU);
		   minWait();
		   for(int i=1; i<=8; i++) {
		    if(isElementExist(Locators_FM.RadioOff_Optn)) {
		     minWait();
		     clickBtn(Locators_FM.RadioOff_Optn);
		     APP_LOGS.info("FM Radio is Turn Off");
		    break;
		    }
		    else {
		     minWait();
		     aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
		     continue;
		    }
		   }   
		  }
		  catch (NoSuchElementException e) {
		   APP_LOGS.info("No Element Found.");
		   e.printStackTrace();
		   Sf.fail();
		  }
		  Sf.assertAll();
		 }

	//validate by Element I.e REcord Icon 
	public void validateOFF_FM() throws InterruptedException {
		try
		{
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_MENU);
			if(isElementExist(Locators_FM.OffFMImsg)){
				check = true;
				APP_LOGS.info("FM Radio Stopped/Off succesful.");
				Assert.assertTrue(check);
			}		
			else{
				APP_LOGS.info("FM Radio is not Turn Off, unsuccesful.");
				Assert.fail();
			}	minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
			
		}catch (org.openqa.selenium.NoSuchElementException e) {
			APP_LOGS.info("No Element Found.");
			e.printStackTrace();
			Assert.fail();		
		}
	}

	//Start and Stop Recording using Record Icon
	public void StartStopRecording() throws InterruptedException  {
		try{
			minWait();	
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_MENU);
			minWait();			
			clickBtn(Locators_FM.StartRecOptn);
			APP_LOGS.info("Recording is Started succesfully");
			minWait();
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_MENU);
			minWait();	
			clickBtn(Locators_FM.StopRecOptn);
			APP_LOGS.info("Recording is Stopped succesfully");
		}catch (org.openqa.selenium.NoSuchElementException e) {
			APP_LOGS.info("No Element Found.");
			e.printStackTrace();
			Assert.fail();
		}
	}

	//Validate using Adb Log captured
	public void validateRecording(String tcName) { 
		SoftAssert SA1 = new SoftAssert();

		try {
			boolean check1=searchString("Audio track drift time",tcName);
			APP_LOGS.info("Recording is Started validated");
			boolean check2=searchString("Audio track stopping",tcName);
			APP_LOGS.info("Recording is Stopped validated");
			boolean check3=searchString("/storage/emulated/0/FMRecording/FMRecording",tcName);
			APP_LOGS.info("Recording is saved in Storage validated");

			if((check1)&&(check2)&&(check3)){
				check= true;
				APP_LOGS.info("Start and Stop Recording is validated");
				SA1.assertTrue(check, " ");

			}else{
				APP_LOGS.info("");
				SA1.fail();
			}
		} catch (NoSuchElementException e) {
			APP_LOGS.info("No Element Found.");
			e.printStackTrace();
			SA1.fail();
		}
		SA1.assertAll();
	}



	//Select settings option from FM menu option
	public void selectSetting() throws InterruptedException {
		try{
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_MENU);
			APP_LOGS.info("Clicked Menu icon");
			minWait();
			for(int i=1; i<=5;i++)
			{	
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
				APP_LOGS.info("Scroll down");
				if (isElementExist(Locators_FM.SettgOptn)) {
					APP_LOGS.info("Settings Option Found is found.");
					minWait();
					clickBtn(Locators_FM.SettgOptn);
					APP_LOGS.info("Clicked settings icon");
					break;					
				} else {
					aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
					continue;					
				}
			}
		}catch (org.openqa.selenium.NoSuchElementException e) {
			APP_LOGS.info("No Element Found.");
			e.printStackTrace();
			Assert.fail();
		}
	}

	//Change recording timings and validate the set time by string
	public void validatePresenceSleepTimeDurtn() throws InterruptedException{
		try {
			minWait();	
			minWait();
			String newRecDurtn1=Locators_FM.FifteenminsOptn.getText();
			//		System.out.println(newRecDurtn1);
			if(newRecDurtn1.equals("15 minutes")) {
				check1 = true;
			}
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
			minWait();
			String newRecDurtn2=Locators_FM.ThirtyminsOptn.getText();
			//		System.out.println(newRecDurtn2);
			if(newRecDurtn2.equals("30 minutes")) {
				check2 = true;
			}
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
			minWait();
			String newRecDurtn3=Locators_FM.FortyFiveminsOptn.getText();
			//		System.out.println(newRecDurtn3);
			if(newRecDurtn3.equals("45 minutes")) {
				check3 = true;
			}

			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
			minWait();
			String newRecDurtn4=Locators_FM.onehrOptn.getText();
			//		System.out.println(newRecDurtn4);
			if(newRecDurtn4.equals("1 Hour")) {
				check4 = true;
			}

			if((check1)&&(check2)&&(check3)&&(check4)){
				check= true;
				APP_LOGS.info("New Record Duration is changed and Updated");
				Assert.assertTrue(check);

			}else{
				APP_LOGS.info(newRecDurtn1+": "+check1+ "\n"+newRecDurtn2+": "+check2+"\n"+newRecDurtn3+": "+check3+"\n"+
						newRecDurtn4);
				Assert.fail();
			}
		}catch (org.openqa.selenium.NoSuchElementException e) {
			APP_LOGS.info("No Element Found.");
			e.printStackTrace();
			Assert.fail();
		}
	}

	//Setting Sleep Time from FM menu
	public void sleepTimeSet() throws InterruptedException {
		try{
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_MENU);
			APP_LOGS.info("Clicked menu option");			
			clickBtn(Locators_FM.SleepOptn);
			APP_LOGS.info("Clicked sleep Option");								
		}catch (org.openqa.selenium.NoSuchElementException e) {
			APP_LOGS.info("Farheen --Error: No Element Found.");
			e.printStackTrace();
			Assert.fail();
		}
	}

	public void validateSleepSetNCancelSleep(String adblog) throws InterruptedException {
		/*
		 * Validate Sleep mode and stop sleep mode
		 */
		SoftAssert SA2 = new SoftAssert();
		try {
			clickBtn(Locators_FM.FifteenminsOptn);
			minWait();//cancel sleep mode
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_MENU);
			customWait(2000);
			if(isElementExist(Locators_FM.CancelSleepOptn)) {
				check1 = true;
				APP_LOGS.info("FM Radio is in Sleep Time Mode validated");
			}
			clickBtn(Locators_FM.CancelSleepOptn);
			customWait(2000);
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_MENU);
			minWait();
			if(isElementExist(Locators_FM.SleepOptn)) {
				check2 = true;
				APP_LOGS.info("FM Radio is Cancelled Sleep Time Mode validated");
			}
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			if((check1)&&(check2)){
				check= true;
				APP_LOGS.info("New Record Duration is changed and Updated");
				SA2.assertTrue(check, " ");
			}else{
				APP_LOGS.info(": "+check1+ "\n"+": "+check2+"\n");
				SA2.fail();
			}
		} catch (NoSuchElementException e) {
			APP_LOGS.info("Farheen --Error: No Element Found.");
			e.printStackTrace();
			SA2.fail();
		}
		SA2.assertAll();
	}

	//DefaultChannel As per the Country Target
	public void validateDefaultChannel() throws InterruptedException {
		try {
			minWait();
			String getDefaultBandName1=Locators_FM.DefaultRegnlBnd.getText();
			APP_LOGS.info("Got the Default band Channel Name");
			String RegionalBandName = "North America (87.5MHz To 108.0MHz In 200 Khz Steps)";
			if(getDefaultBandName1.equals(RegionalBandName)){
				check =true;
				Assert.assertTrue(check);
				APP_LOGS.info("Default Regional Band Name is Validated");

			}else {
				APP_LOGS.info("Default Regional Band Name is not Validated");
				Assert.fail();
			}
		}catch (org.openqa.selenium.NoSuchElementException e) {
			APP_LOGS.info("No Element Found.");
			e.printStackTrace();
			Assert.fail();
		}
	}

	public void changeSettingOptn() throws InterruptedException {
		//Change Default settings
		try {
			minWait();
			Locators_FM.RegionalBandOptn.click();
			minWait();
			if(isElementExist(Locators_FM.AlertMsg)) {
				APP_LOGS.info("Alert Msg is present");
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_RIGHT);
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_ENTER);
				minWait();
			}		
			Locators_FM.EuropeOptn.click();
			minWait();
			Locators_FM.AudioOutMode.click();
			minWait();
			Locators_FM.MonoAudioOpt.click();
			minWait();
			Locators_FM.AlternateFreqOpt.click();
			minWait();
			//			Locators_FM.RecDurtnOptn.click();
			minWait();
			//			Locators_FM.FifteenminsOptn.click();
			minWait();
			Locators_FM.FactryResetOpt.click();
			minWait();
			Locators_FM.OKOptn.click();
		}catch (org.openqa.selenium.NoSuchElementException e) {
			APP_LOGS.info("No Element Found.");
			e.printStackTrace();
		}
	}

	public void validateResortDefaultSettg() throws InterruptedException {
		//Validate Restore Default settings
		SoftAssert SA3 = new SoftAssert();
		try {
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_MENU);
			APP_LOGS.info("Clicked Menu icon");
			minWait();
			clickBtn(Locators_FM.SettgOptn);
			minWait();
			String getRegionalBnd = Locators_FM.DefaultRegnlBnd.getText();
			if(getRegionalBnd.equalsIgnoreCase("North America (87.5MHz To 108.0MHz In 200 Khz Steps)")){
				check1 = true;
				minWait();
			}
			String getAudioMode = Locators_FM.SteroTxt.getText();
			if(getAudioMode.equalsIgnoreCase("Stereo")){
				check2 = true;
				minWait();
			}
			String getAlternateFreq = Locators_FM.AutoSelectTxt.getText();
			if(getAlternateFreq.equalsIgnoreCase("Auto Select Enabled")){
				check3 = true;
				minWait();
			}
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			if((check1)&&(check2)&&(check3)){
				check=true;
				APP_LOGS.info("Resorted to Default Setting validated Sucessfully .");
				SA3.assertTrue(check, " ");
			}else{
				APP_LOGS.info(getRegionalBnd+": "+check1+"\n"+getAudioMode+": "+"\n"+getAlternateFreq+": "+"\n");
				SA3.fail();
			}
		}catch (org.openqa.selenium.NoSuchElementException e) {
			APP_LOGS.info("Farheen --Error: No Element Found.");
			e.printStackTrace();
			SA3.fail();
		}
		SA3.assertAll();
	}


	public void validatescanAllStations() throws InterruptedException {
		//SCan channels and validate
      SoftAssert SA4 = new SoftAssert();
		try {
			String getChannelName=Locators_FM.ChannelTxt.getText();
			String sChannel = getChannelName.substring(0, getChannelName.indexOf(".")+2);
			System.out.println(sChannel);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_MENU);
			APP_LOGS.info("Clicked Menu icon");
			minWait();
			clickBtn(Locators_FM.AutoScanOptn);
			APP_LOGS.info("ClickedAuto Scan");
			for(int i=1; i<=2; i++) {
				waituntilnew(Locators_FM.YesOpt,10);
				APP_LOGS.info("Channels are not found after wait 20 second" );
				if(isElementExist(Locators_FM.YesOpt)) {
					Locators_FM.YesOpt.click();
					minWait();					
					break;			
				}
				else{
					continue;	
				}
			}
			customWait(5000);			
				String getChannelName1=Locators_FM.ScanedsChannelTxt.getText();
				String sChannel1 = getChannelName1.substring(0, getChannelName1.indexOf(".")+2);
				System.out.println(sChannel1);
			System.out.println(sChannel1);
			if(sChannel.equals(sChannel1)){
				minWait();
				APP_LOGS.info("Channels are scanned and Saved unSuccessfully " );
				SA4.fail();						
			}
			else {
				minWait();
				check =true;
				APP_LOGS.info("Channels are scanned and Saved Successfully" );
				SA4.assertTrue(check, " ");
			}
		}catch (org.openqa.selenium.NoSuchElementException e) {
			APP_LOGS.info("No Element Found.");	
			e.printStackTrace();				
	     	SA4.fail("Signal Issue: Cannot scan channels");			
		}
		catch(NullPointerException e) {
			APP_LOGS.info("No Element Found.");		
			SA4.fail("Signal Issue: Cannot scan channels");		
		}
		SA4.assertAll();
	}
	

	public void deleteSavedChannel() throws InterruptedException {
		//Delete added Persets Channel/Station
		try {
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_MENU);
			APP_LOGS.info("Clicked Menu icon1");
			minWait();//Select persets
		    clickBtn(Locators_FM.Persets);
			//Select Remove 
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_MENU);
			APP_LOGS.info("Clicked Menu icon2");
			minWait();
		    clickBtn(Locators_FM.Remove_Persets);
			minWait();
		}catch (org.openqa.selenium.NoSuchElementException e) {
			APP_LOGS.info("No Element Found.");
			Assert.fail();
			e.printStackTrace();
		}
	}



	public void validatereplaceChannel() throws InterruptedException {
		//Validate replace channel by using forward button to change channel
		SoftAssert SA1=new SoftAssert();
		try {
			minWait();
			String getChannelName=Locators_FM.ChannelTxt.getText();
			String sChannel = getChannelName.substring(0, getChannelName.indexOf(".")+2);
			System.out.println(sChannel);
			//Forward button
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_RIGHT);
			minWait();
			APP_LOGS.info("Forward button to change channel");
			String getChannelName1=Locators_FM.ChannelTxt.getText();
			String sChannel1 = getChannelName1.substring(0, getChannelName1.indexOf(".")+2);
			System.out.println(sChannel1);
			if(sChannel.equals(sChannel1)){
				minWait();
				APP_LOGS.info("Saved Channel is not Replaced with other Channel");			
				SA1.fail();				
			}
			else {
				check =true;
				SA1.assertTrue(check, "Saved Channel is Replaced with other Channel");
				APP_LOGS.info("Saved Channel is Replaced with other Channel");
				SA1.assertTrue(check, " ");
			}
			SA1.assertAll();
		}
		catch(org.openqa.selenium.NoSuchElementException e) {
			APP_LOGS.info("Element not Found");
			e.printStackTrace();
		}
	}

	public void validateStartStopRecLoudSpeaker() throws InterruptedException {
		// validate Start /Stop recording when Radio in LoudSpeaker
		try {	
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_MENU);
			minWait();
			clickBtn(Locators_FM.FMRadioLoudSpeaker);
			customWait(1000);
			StartStopRecording();
			validateRecording("FM_Adb");
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_MENU);
			minWait();
			clickBtn(Locators_FM.FMRadioWiredHeadset);			
		}
		catch(org.openqa.selenium.NoSuchElementException e) {
			APP_LOGS.info("Element not Found");
			e.printStackTrace();
			Assert.fail();
		}
	}

	public void addExitPersetsChannel() throws InterruptedException {
	//Add channel to Persets
		try {
			//Add Channel 
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_MENU);
			APP_LOGS.info("Clicked Menu icon");
			minWait();//add Persets
			clickBtn(Locators_FM.AddPersets);
			minWait();	
			APP_LOGS.info("Channel is added to Persets");
		}
		catch(org.openqa.selenium.NoSuchElementException e) {
			APP_LOGS.info("Element not Found");
			e.printStackTrace();
			Assert.fail();
		}
	}

	public void validateExistingChannel() throws InterruptedException { 
		//Validate the Persets channel 
		SoftAssert S = new SoftAssert();
		try {
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_MENU);
			minWait();	
			clickBtn(Locators_FM.AddPersets);//Add to persets/save again
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_MENU);
			minWait();
			clickBtn(Locators_FM.Persets);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_MENU);
			minWait();
			if(isElementExist(Locators_FM.Remove_Persets)) {
				check = true;
				APP_LOGS.info("EXisting Channel is validated");
				S.assertTrue(check, " ");
			}
			else {
				S.fail();
			}
			minWait();
		    clickBtn(Locators_FM.Remove_Persets);
		    
		} catch (NoSuchElementException e) {
			APP_LOGS.info("Farheen --Error: No Elemenet Found ");
			e.printStackTrace();
		}
		S.assertAll();
	}
	
	
	public void validateRemaneSavedChannel() throws InterruptedException, IOException { 
		//Rename persets channel
		SoftAssert S = new SoftAssert();
		try {
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_MENU);
			minWait();
			clickBtn(Locators_FM.Persets);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_MENU);
			minWait();
			clickBtn(Locators_FM.Rename_Persets);
			
			for(int i=1; i<=3; i++) {
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_POUND);
			}
//			Runtime.getRuntime().exec("adb shell input text 98");
			enterTextToInputField(Locators_FM.EditedRenameChannelTxt, "98");
			minWait();
			clickBtn(Locators_FM.OK_RenameOptn);
			minWait();
			String rename_ChannelTxt = Locators_FM.Remane_channel.getText();
			if(isElementExist(Locators_FM.Remane_channel)) {
				check = true;
				APP_LOGS.info("Element found: "+rename_ChannelTxt);
				APP_LOGS.info("Rename Channel is validated");
				S.assertTrue(check, " ");
			}
			else {
				APP_LOGS.info("Rename Channel is not validated");
				S.fail();			
			}
		/*	aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_ENTER);*/
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
		    
		} catch (NoSuchElementException e) {
			APP_LOGS.info("Farheen --Error: No Elemenet Found ");
			e.printStackTrace();
//			S.fail();
		}
		S.assertAll();
	}
	
	


	public void validateForwardAndBackBtn() throws InterruptedException {
		//Validate change channel with forward and Backward button 
		try {
			String getfrwdName1=Locators_FM.ChannelTxt.getText();
			//System.out.println(getfrwdName1);
			minWait();
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_RIGHT);
			minWait();
			String getfrwdName2=Locators_FM.ChannelTxt.getText();
			//System.out.println(getfrwdName2);
			if(getfrwdName1.equals(getfrwdName2)) {
				Assert.fail();
				APP_LOGS.info("Forward button changed the channel unsucessful");

			}else{
				check1 =true;
				APP_LOGS.info("Forward button changed the channel sucessfully");
			}minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_LEFT);
			String getfrwdName3=Locators_FM.ChannelTxt.getText();
			//System.out.println(getfrwdName3);
			if(getfrwdName1.equals(getfrwdName3)) {
				check2 =true;
				APP_LOGS.info("Backward button changed the channel sucessfully");
			}
			if((check1)&&(check2)){
				check= true;
				APP_LOGS.info("Forward button and Backward button changed the channel is validated");
				Assert.assertTrue(check);

			}else{
				APP_LOGS.info(getfrwdName2+": "+check1+ "\n"+getfrwdName3+": "+check2+"\n");
				Assert.fail();
			}
		}
		catch(org.openqa.selenium.NoSuchElementException e) {
			APP_LOGS.info("Element not Found");
			e.printStackTrace();
		}
	}

	public void validateForwardBackwrdStations() throws InterruptedException {
		//Validate change channel with forward and Backward button 
		try {
			String getfrwdName1=Locators_FM.ChannelTxt.getText();
			System.out.println("1=="+getfrwdName1);
			customWait(2000);
			aDriver.longPressKeyCode(22);
			//			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_RIGHT);
			customWait(8000);
			String getfrwdName2=Locators_FM.ChannelTxt.getText();
			System.out.println("2==="+getfrwdName2);
			if(getfrwdName1.equals(getfrwdName2)) {
				check1 =true;
				APP_LOGS.info("Forward button changed the channel unsucessful,Bcz of signal");
			}else{
				check1 =true;
				APP_LOGS.info("Forward button changed the channel sucessfully");
			}minWait();
			minWait();
			aDriver.longPressKeyCode(21);
			customWait(4000);
			String getfrwdName3=Locators_FM.ChannelTxt.getText();
			System.out.println("3===="+getfrwdName3);
			if(getfrwdName1.equals(getfrwdName3)) {
				check2 =true;
				APP_LOGS.info("Backward button changed the channel sucessfully");
			}
			if((check1)&&(check2)){
				check= true;
				APP_LOGS.info("Forward button and Backward button changed the channel is validated");
				Assert.assertTrue(check);
			}else{
				APP_LOGS.info(getfrwdName1+": "+check1+ "\n"+getfrwdName3+": "+check2+"\n");
				APP_LOGS.info("Forward button and Backward button changed the channel is not validated, because of poor signal");
				Assert.fail();
			}
		}
		catch(org.openqa.selenium.NoSuchElementException e) {
			APP_LOGS.info("Element not Found");
			e.printStackTrace();
			Assert.fail();
		}
	}

	
	public void switchToLoudSpeakerMode() throws InterruptedException {
		//Radio play in Loud Speaker Mode
		
		try {
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_MENU);
			minWait();
			clickBtn(Locators_FM.FMRadioLoudSpeaker);
		} catch (NoSuchElementException e) {
		
			e.printStackTrace();
		}
		
	}

	public void pushBackgrdFM() throws InterruptedException {		
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.HOME);
			APP_LOGS.info("Push the FM Radio into Background Sucessfully");
			customWait(5000);
	}

	public void validateFMOn(String adbLog) {
		check = searchString("select_devices for use case (play-fm)",adbLog);
		if(check) {
			APP_LOGS.info("FM is on when Push to background is validated");
			Assert.assertTrue(check);
		}
		else {
			APP_LOGS.info("FM is on when Push to background is unsuccessful");
			Assert.fail();
		}
	}


	public void launchNotification() throws InterruptedException  {
		try{
			minWait();
			minWait();
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.HOME);
			minWait();
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_MENU);
			minWait();
//			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_ENTER);
			clickBtn(Locators_FM.SG_MyNotification);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_ENTER);
			minWait();
			APP_LOGS.info("Clicked FM from notification succesfully");
		}
		catch(org.openqa.selenium.NoSuchElementException e) {
			APP_LOGS.info("Element not Found");
			e.printStackTrace();
		}
	}

	

	public void switchToHeadsetSpeakermode() throws InterruptedException {
		try {
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_MENU);
			minWait();
			Locators_FM.FMRadioLoudSpeaker.click();
			APP_LOGS.info("FM radio enable Speaker, succesfully");
			customWait(2000);
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_MENU);
			minWait();
			Locators_FM.FMRadioWiredHeadset.click();
			APP_LOGS.info("FM radio enable unMute, succesfully");
			minWait();
			APP_LOGS.info("FM radio enable Headset, succesfully");
		}
		catch(org.openqa.selenium.NoSuchElementException e) {
			APP_LOGS.info("Element not Found");
			e.printStackTrace();
			Assert.fail();
		}
	}

	public void validateModes(String adbLog) throws InterruptedException {
		minWait();
		check1 = searchString("hw_info_append_hw_type : device_name = speaker",adbLog);
		System.out.println(check1);
		check2 = searchString("enable_snd_device: snd_device(14: headphones)",adbLog);
		System.out.println(check2);
		customWait(5000);
		if(check1 && check2) {
			check = true;
			APP_LOGS.info("FM radio is in Mute Mode, succesfully");
			Assert.assertTrue(check);
		}
		else {
			APP_LOGS.info("FM radio is not in Mute Mode, succesfully");
			Assert.fail();
		}
	}


	public void volumeUpandDown() throws InterruptedException {
		try {
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_VOLUME_DOWN);
			APP_LOGS.info("FM Radio decrease volume Sucessfully");
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_VOLUME_UP);
			APP_LOGS.info("FM Radio increase volume Sucessfully");
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_VOLUME_DOWN);
			APP_LOGS.info("FM Radio decrease volume Sucessfully");
		}
		catch(org.openqa.selenium.NoSuchElementException e) {
			APP_LOGS.info("Element not Found");
			e.printStackTrace();
		}
	}

	public void validateVolumeUpDow(String adbLog) throws InterruptedException {
		check = searchString(" audio_extn_fm_set_parameters: set_fm_volume usecase",adbLog);
		customWait(5000);
		System.out.println(check);
		if(check) {
			APP_LOGS.info("Volume Up/Down FM Radio is validated");
			Assert.assertTrue(check);
		}
		else {
			APP_LOGS.info("Volume Up/Down FM Radio is unsuccessful");
			Assert.fail();
		}
	}
}
