package com.xp5S.util;

import java.io.IOException;

import org.openqa.selenium.NoSuchElementException;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import io.appium.java_client.android.AndroidKeyCode;

public class SoundRecorder_Util extends  BaseUtil{
	
	public boolean check = false;
	public SoftAssert softAssert;

	public void logout() throws InterruptedException {
		longpress(4);
		minWait();
	}

	public void startRecord() throws InterruptedException {
		
		try {
			Locators_SoundRec.recordBtn.click();
			minWait();
		} catch (NoSuchElementException e) {
			e.printStackTrace();
		}
	}

	public void stopAdb() throws InterruptedException, IOException {
		customWait(4000);
		Runtime.getRuntime().exec("taskkill /IM cmd.exe");
		APP_LOGS.info("Adb logs stopped succesfully. ");
		customWait(2000);
	}

	public void launchSounchRec() throws InterruptedException {
		launchApp("SOUNDRECORDER_PACKAGE","SOUNDRECORDER_ACTIVITY");
		minWait();	
	}

	public void validateSoundRecorderLaunch() throws InterruptedException{
		/*
		 * Check the Recorder button with elements and initial duration
		 */
		customWait(4000);
		if(isElementExist(Locators_SoundRec.PressRecordText) && isElementExist(Locators_SoundRec.initialTimeText) && isElementExist(Locators_SoundRec.recordBtn) ){

			check = true;
			APP_LOGS.info(" PressRecordText, initialTimeText and Record button is displayed. Sound Recorder Launch is sucessfully.");
			Assert.assertTrue(check);
		}else{
			APP_LOGS.info("PressRecordText, initialTimeText and Record button is not displayed.Sound Recorder Launch is unsuccesful.");
			Assert.fail();	
		}
	}

	//Selecting List Option after Launching Application
	public void selectListOptn() throws InterruptedException {
		try {
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_RIGHT);
			minWait();
			APP_LOGS.info("Recording List Pressed succesfully");
			if(isElementExist(Locators_SoundRec.ListallowBtn)) {
				Locators_SoundRec.ListallowBtn.click(); }
			minWait();
			longpress(4);
		} catch (NoSuchElementException e) {
			e.printStackTrace();
		}
	}

	public void validateListPage() throws InterruptedException {
		//deleteSavedFolder();
		validateSoundRecordDeletion();
	}

	//Delete the recordings saved in recording List
	public void validateSoundRecordDeletion() throws InterruptedException {
		
		try {
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_RIGHT);
			minWait();
			String getMsg = Locators_SoundRec.NorecordingMsg.getText();
			if(getMsg.equalsIgnoreCase("No recording")){
				check=true;
				APP_LOGS.info("Sound Record is deleted succesfully");
				Assert.assertTrue(check);

			}else{
				APP_LOGS.info("Sound Record  is not deleted succesfully");
				Assert.fail();
			}
			minWait();
		} catch (NoSuchElementException e) {			
			e.printStackTrace();
			String getMsg1 = Locators_SoundRec.recording_List.getText();
			if(getMsg1.equalsIgnoreCase("Recording list")){
				check=true;
				APP_LOGS.info("Sound Record is deleted succesfully");
				Assert.assertTrue(check);
			}else{
				APP_LOGS.info("Sound Record  is not deleted succesfully");
				Assert.fail();
			}
		}
		
	}

	//Start Recorder
	public void recordSound() throws InterruptedException {
		aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
		minWait();
		selectRecordBtn();
		APP_LOGS.info("Pressed Record button sucessfully");
		minWait();
		aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_LEFT);
		APP_LOGS.info("Pressed Stop button sucessfully");
	}

	//Selecting Allow for pop ups
	public void selectRecordBtn() throws InterruptedException {
		for(int i=0; i<=2; i++) {
			if(isElementExist(Locators_SoundRec.allowBtn)){
				customWait(2000);
				Locators_SoundRec.allowBtn.click();
				APP_LOGS.info("Pressed Allow button sucessfully");
				minWait();
				APP_LOGS.info("Allow Button is displayed and Selected, succesful.");

			}else{
				APP_LOGS.info("Allow Button is not displayed.");
			}
		}maxWait();	
	}


	public void validateRecordedSoundSaved() throws InterruptedException {
		/*
		 * check recording name is saved in recorded list after saving
		 */
		try {
			minWait();
			String SavedRecName=Locators_SoundRec.recordingName.getText();
			minWait();
			System.out.println(SavedRecName);
			Locators_SoundRec.saveBtn.click(); 
			APP_LOGS.info("Pressed Save button sucessfully");
			minWait();
			String ListSavedRecName=Locators_SoundRec.saveBtnUnderRecList.getText();
			System.out.println(ListSavedRecName);
			minWait();

			if(ListSavedRecName.contains("New record")){
				check=true;
				APP_LOGS.info("Sound Record is saved succesfully");
				Assert.assertTrue(check);	
			}else{
				APP_LOGS.info("Sound Record is not saved, unsuccesful");
				Assert.fail();
			}	aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			Assert.fail();
		}
	}
	
	
	  
		//Delete Saved Recorded Sound
		public void deleteSavedRecorder() throws InterruptedException {
			
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_RIGHT);
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
		}
		

		public void DiscardSound() throws InterruptedException {
			try {
				minWait();
				Locators_SoundRec.DiscardBtn.click();
				APP_LOGS.info("Pressed Discard button sucessfully");
				minWait();
			} catch (NoSuchElementException e) {
				e.printStackTrace();
			}
		}
		  
		public void validateRecordedSoundDiscarded() throws InterruptedException {
			/*
			 * Delete the sound recorder and check it is present or not
			 */
			try {
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_RIGHT);
				APP_LOGS.info("Pressed List Icon sucessfully");
				minWait();
				String getMsg = Locators_SoundRec.NorecordingMsg.getText();
				if(getMsg.equalsIgnoreCase("No recording")){
					check=true;
					APP_LOGS.info("Sound Record is discarded succesfully");
					Assert.assertTrue(check);

				}else{
					APP_LOGS.info("Sound Record  is not discarded succesfully");
					Assert.fail();
				}
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			} catch (NoSuchElementException e) {
				e.printStackTrace();
				String getMsg1 = Locators_SoundRec.recording_List.getText();
				if(getMsg1.equalsIgnoreCase("Recording list")){
					check=true;
					APP_LOGS.info("Sound Record is discarded succesfully");
					Assert.assertTrue(check);
				}else{
					APP_LOGS.info("Sound Record  is not discarded succesfully");
					Assert.fail();
				}
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			}
		}
		
		//Save Recorded Sound
		public void SaveSoundRecord() throws InterruptedException {
			try {
				minWait();
				Locators_SoundRec.recordingName.clear();
				minWait();
				enterTextToInputField(Locators_SoundRec.recordingName,"SampleSoundRecord");
				minWait();
				if(isElementExist(Locators_SoundRec.NameExist)) {
					Locators_SoundRec.DiscardBtn.click();
				}
				Locators_SoundRec.saveBtn.click(); 
				APP_LOGS.info("Pressed Save button sucessfully");
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			} catch (NoSuchElementException e) {
				e.printStackTrace();
			}
		}
		
		
		//Edit Saved Recoreded Sound
		public void editSoundRecordName() throws InterruptedException {
			try {
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_RIGHT);
				minWait();//Edit 
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_MENU);
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
				minWait();
				minWait();
				Locators_SoundRec.recordingName.clear();
				minWait();
				enterTextToInputField(Locators_SoundRec.recordingName,"DemoSoundRecord");
				minWait();
				Locators_SoundRec.saveBtn.click();
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			} catch (NoSuchElementException e) {
				e.printStackTrace();
			}
		}
	     
		public void validateSoundRecordEdit() throws InterruptedException {
			try {
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_RIGHT);
				minWait();
				String getEditedName = Locators_SoundRec.saveBtnUnderRecList.getText();
				if(getEditedName.equalsIgnoreCase("DemoSoundRecord")){
					check=true;
					APP_LOGS.info("Sound Record name is Edited succesfully");
					Assert.assertTrue(check);
				}else{
					APP_LOGS.info("Sound Record name is not Edited succesfully");
					Assert.fail();
				}minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			} catch (NoSuchElementException e) {				
				e.printStackTrace();
			}	
		}		
		
		//Adding Multiple sound Records
		public void addMultipleSoundRec() throws InterruptedException {
			try {
				for(int i=0; i<=2; i++){
					minWait();
					recordSound();
					minWait();
					Locators_SoundRec.recordingName.clear();
					minWait();
					enterTextToInputField(Locators_SoundRec.recordingName,"SampleSoundRecord"+i);
					minWait();
					Locators_SoundRec.saveBtn.click(); 
					APP_LOGS.info("Pressed Save button sucessfully");
					minWait();
					aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);	
				}
			} catch (NoSuchElementException e) {
				e.printStackTrace();
			}
		}
		
		//Delete Sound Record using SelectAll Option
		public void deleteMulSoundRec() throws InterruptedException {
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_RIGHT);
			minWait();
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_MENU);
			for(int i=1; i<=4; i++) {
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
			}
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
			minWait();
			APP_LOGS.info("Selected Dropdown option sucessfully");
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_MENU);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
			minWait();
			APP_LOGS.info("Selected All Sound Records sucessfully");
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_MENU);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_UP);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
			minWait();
			Locators_SoundRec.DeleteBtn.click();
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);	
		}
		
		public void validateMulDeleteSoundRec() throws InterruptedException {
			validateSoundRecordDeletion();
		}
		
		
		//Pause and resume
		public void ValidatePauseAndResume() throws InterruptedException {
			boolean check1 = false;
			boolean check2 = false;
			
			try {
				//Start Recording
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
				minWait();
				selectRecordBtn();
				minWait();
				APP_LOGS.info("Pressed Record button sucessfully");
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);//Pause recording
				minWait();
				minWait();
				String PauseState = Locators_SoundRec.PauseText.getText();
				minWait();
				System.out.println(PauseState);
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);//Restart recording
				minWait();
				String ResumeState = Locators_SoundRec.RecordingText.getText();
				minWait();
				System.out.println(ResumeState);
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_LEFT);
				minWait();
				DiscardSound();
				APP_LOGS.info("Pressed Stop button sucessfully");
				
				if(PauseState.equalsIgnoreCase("Pause")){
					check1=true;
					APP_LOGS.info("Sound Record is saved succesfully");
				}
				if(ResumeState.equalsIgnoreCase("Recording")){
					check2=true;
					APP_LOGS.info("Sound Record is Resume succesfully");
				}
				if((check1)&&(check2)){
					check= true;
					APP_LOGS.info("Pause and Resume succesfully.");
					Assert.assertTrue(check);
				}
				else{
					APP_LOGS.info(PauseState+": "+check1+ "\n"+ResumeState+": "+check2+"\n");
					Assert.fail();
				}
			} catch (NoSuchElementException e) {
				e.printStackTrace();
				Assert.fail();
			}
		}
		
		

		//Selecting All Sound Recording from checkBox
		public void selectAllSoundRec() throws InterruptedException {
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_RIGHT);
			minWait();
		
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_MENU);
			for(int i=1; i<=4; i++) {
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
			}
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
			minWait();
			APP_LOGS.info("Selected Dropdown option sucessfully");
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_MENU);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
			minWait();
			APP_LOGS.info("Selected All Sound Records sucessfully");
			minWait();
			/*selectCheckbox(Locators_SoundRec.CheckBox2);
			minWait();
			selectCheckbox(Locators_SoundRec.CheckBox3);
			minWait();
			selectCheckbox(Locators_SoundRec.CheckBox4);
			minWait();
			selectCheckbox(Locators_SoundRec.CheckBox5);*/
		}
		
		//Deselect All Sound Recording from Deselct Option
		public void deslectAllSoundRec() throws InterruptedException {
			
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
			APP_LOGS.info("Selected Dropdown option sucessfully");
			APP_LOGS.info("Deselected All Sound Records sucessfully");
			minWait();
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_MENU);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
		}
		
		public void validateSelectAll() throws InterruptedException {
			try {
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_MENU);
				minWait();
				if(isElementExist(Locators_SoundRec.DeSelectAllOpt)){
					check=true;
					APP_LOGS.info("Validated Sound Record List is selected succesfully");
					Assert.assertTrue(check);
				}else{ 
					APP_LOGS.info("Sound Record List is not selected succesfully");
					Assert.fail();
				}minWait();
			} catch (NoSuchElementException e) {
				e.printStackTrace();
			}
			//Locators_SoundRec.backBtn.click();	
		}
		
		
		
		public void validateDeselectAll() throws InterruptedException {
			minWait();
			if(isElementExist(Locators_SoundRec.SelectOpt)){
				APP_LOGS.info("Sound Record List is deselected succesfully");
				Assert.assertTrue(check);
			}else{ check=true;
				APP_LOGS.info("Validated Sound Record List is not deselected");
				Assert.fail();
			}
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
		}	
		
		
		public void playSoundRecord() throws InterruptedException {
			//Select recording to play from recording list
			minWait();
			SaveSoundRecord();
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_RIGHT);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
			minWait();
			minWait();
			
		}
		
		public void validateRecSoundPlay() throws InterruptedException {
			//check pause button present
			if(isElementExist(Locators_SoundRec.PlayIcon)) {
				check = true;
				APP_LOGS.info("Playing Recorded Sound succesfully.");
				Assert.assertTrue(check);
			}
			else {
				APP_LOGS.info("Recorded Sound is not Played unsuccesful.");
				Assert.fail();
			}minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
		}
		
		
		public void deviceSilentMode() throws InterruptedException, IOException {
			minWait();
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
//			longpress(25);
			Runtime.getRuntime().exec("cmd /C \"adb shell input keyevent --longpress KEYCODE_VOLUME_DOWN");
			minWait();
			
		}
		
		
		public void validateSoundRecording() throws InterruptedException {
			/*
			 * Check sound recording is taking place in Silent mode
			 */
			try {
				minWait();
				minWait();
				minWait();
				String RecTxt=Locators_SoundRec.RecordingText.getText();
				System.out.println(RecTxt);
				minWait();
				if(RecTxt.equalsIgnoreCase("Recording")){
					check=true;
					APP_LOGS.info("Recording Sound succesfully");
					Assert.assertTrue(check);	
				}else{
					APP_LOGS.info("Recording Sound unsuccesfully");
					Assert.fail();
				}
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_LEFT);
				minWait();
				Locators_SoundRec.DiscardBtn.click();
				minWait();
			} catch (NoSuchElementException e) {
				e.printStackTrace();
			}
		}
	/*	
	public void launchFilemanager() throws InterruptedException {
			try {
				launchApp("FILEMGR_PACKAGE","FILEMGR_ACTIVITY");
				customWait(2000);
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_ENTER);
				minWait();
				for(int i=1; i<=11; i++) {
					if(isElementExist(Locators_Sanity.SoundRecOptn)) {
						aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_ENTER);
						minWait();
						break;
					}
					else {
						minWait();
						aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
						minWait();
						continue;
					}
//				Locato
				}
			}catch (Exception e) {
				APP_LOGS.info("Element not Found");
				e.printStackTrace();
			}
		}
		*/
		
		public void validateDetailsSoundRec() throws InterruptedException {
			if(isElementExist(Locators_SoundRec.SampleSoundRec)){
				check = true;
				String getSoundRecName= Locators_SoundRec.SampleSoundRec.getText();
				APP_LOGS.info("Sound Recorder Name is :"+getSoundRecName);
			}
			else{
				APP_LOGS.info("All Details of Sound Record is not Displayed");
				Assert.fail();
			}	  customWait(2000);
		}
}
