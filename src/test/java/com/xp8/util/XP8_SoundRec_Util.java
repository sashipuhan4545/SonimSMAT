package com.xp8.util;

import java.io.IOException;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;




import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.AndroidKeyCode;



public class XP8_SoundRec_Util extends BaseUtil  {
	
	public boolean check = false;
	public SoftAssert softAssert;
	
	/*public void End() {
		driver.quit();
	}*/
	
	public void validateSoundRecorderLaunch() throws InterruptedException{
		/*
		 * Check the Recorder button with elements and initial duration
		 */
		try {
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
		catch (org.openqa.selenium.NoSuchElementException e) {
			APP_LOGS.info("Element not Found");
			e.printStackTrace();
		}
	}
	
	//Start Recorder
	public void recordSound() throws InterruptedException {
		try {
		Locators_SoundRec.recordBtn.click();
		minWait();
		selectRecordBtn();
		APP_LOGS.info("Pressed Record button sucessfully");
		minWait();
		Locators_SoundRec.stopBtn.click();
		APP_LOGS.info("Pressed Stop button sucessfully");
		minWait();
		}
		catch (org.openqa.selenium.NoSuchElementException e) {
			APP_LOGS.info("Element not Found");
			e.printStackTrace();
		}
	}
	
	//Selecting List Option after Launching Application
	public void selectListOptn() throws InterruptedException {
		try {
		Locators_SoundRec.listIcon.click();
		minWait();
		if(isElementExist(Locators_SoundRec.ListallowBtn)) {
		Locators_SoundRec.ListallowBtn.click();}
		minWait();
		longpress(4); 
		}
		catch (org.openqa.selenium.NoSuchElementException e) {
			APP_LOGS.info("Element not Found");
			e.printStackTrace();
		}
	}
	
	public void validateListPage() throws InterruptedException {
		deleteSavedFolder();
		validateSoundRecordDeletion();
	}
	
	public void DiscardSound() throws InterruptedException {
		try {
		minWait();
		Locators_SoundRec.DiscardBtn.click();
		APP_LOGS.info("Pressed Discard button sucessfully");
		minWait();
		}
		catch (org.openqa.selenium.NoSuchElementException e) {
			APP_LOGS.info("Element not Found");
			e.printStackTrace();
		}
	}
	  
	public void validateRecordedSoundDiscarded() throws InterruptedException {
		/*
		 * Delete the sound recorder and check it is present or not
		 */
		try {
		minWait();
		Locators_SoundRec.listIcon.click();
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
		}minWait();
		minWait();
		Locators_SoundRec.backBtn.click();
		}
		catch (org.openqa.selenium.NoSuchElementException e) {
			APP_LOGS.info("Element not Found");
			e.printStackTrace();
		}
	}
	
	//Pause and resume
	public void ValidatePauseAndResume() throws InterruptedException {
		boolean check1 = false;
		boolean check2 = false;
		try {
		Locators_SoundRec.recordBtn.click();
		minWait();
		selectRecordBtn();
		minWait();
		APP_LOGS.info("Pressed Record button sucessfully");
		Locators_SoundRec.PauseBtn.click();
		minWait();
		String PauseState = Locators_SoundRec.PauseText.getText();
		minWait();
		Locators_SoundRec.recordBtn.click();
		minWait();
		String ResumeState = Locators_SoundRec.RecordingText.getText();
		minWait();
		Locators_SoundRec.stopBtn.click();
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
		}
		catch (org.openqa.selenium.NoSuchElementException e) {
			APP_LOGS.info("Element not Found");
			e.printStackTrace();
		}
	}
	
	//Selecting Allow for pop ups
	public void selectRecordBtn() throws InterruptedException {
		try {
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
		catch (org.openqa.selenium.NoSuchElementException e) {
			APP_LOGS.info("Element not Found");
			e.printStackTrace();
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
		Locators_SoundRec.backBtn.click();
		}
		catch (org.openqa.selenium.NoSuchElementException e) {
			APP_LOGS.info("Element not Found");
			e.printStackTrace();
		}
	}
	
	public void validateSoundRecording() throws InterruptedException {
		/*
		 * Check sound recording is taking place in Silent mode
		 */
		try {
		String RecTxt=Locators_SoundRec.RecordingText.getText();
		minWait();
		if(RecTxt.equalsIgnoreCase("Recording")){
			check=true;
			APP_LOGS.info("Recording Sound succesfully");
			Assert.assertTrue(check);	
		}else{
			APP_LOGS.info("Recording Sound unsuccesfully");
			Assert.fail();
		}minWait();
		Locators_SoundRec.stopBtn.click();
		minWait();
		Locators_SoundRec.DiscardBtn.click();
		minWait();
		}
		catch (org.openqa.selenium.NoSuchElementException e) {
			APP_LOGS.info("Element not Found");
			e.printStackTrace();
		}
	}
     
	public void validateRecordedSoundSaved() throws InterruptedException {
		/*
		 * check recording name is saved in recorded list after saving
		 */
		try {
		minWait();
		String SavedRecName=Locators_SoundRec.recordingName.getText();
		minWait();
		Locators_SoundRec.saveBtn.click(); 
		APP_LOGS.info("Pressed Save button sucessfully");
		minWait();
		String ListSavedRecName=Locators_SoundRec.saveBtnUnderRecList.getText();
		Locators_SoundRec.backBtn.click();
		if(SavedRecName.equalsIgnoreCase(ListSavedRecName)){
			check=true;
			APP_LOGS.info("Sound Record is saved succesfully");
			Assert.assertTrue(check);	
		}else{
			APP_LOGS.info("Sound Record is not saved, unsuccesful");
			Assert.fail();
		}
		}
		catch (org.openqa.selenium.NoSuchElementException e) {
			APP_LOGS.info("Element not Found");
			e.printStackTrace();
		}
	}
     
	//Delete Saved Recorded Sound
	public void deleteSavedRecorder() throws InterruptedException {
		try {
		Locators_SoundRec.listIcon.click();
		APP_LOGS.info("Pressed List Icon sucessfully");
		minWait();
		minWait();
		minWait();
		TouchAction action = new TouchAction(aDriver);
		action.longPress(Locators_SoundRec.firstEntry).release().perform();
		minWait();
		Locators_SoundRec.DeleteIcon.click();
		APP_LOGS.info("Pressed Delete Icon sucessfully");
		minWait();
		Locators_SoundRec.DeleteBtn.click();
		APP_LOGS.info("Pressed Delete Button sucessfully");
		Thread.sleep(2000);
		minWait();
		Locators_SoundRec.backBtn.click();
		}
		catch (org.openqa.selenium.NoSuchElementException e) {
			APP_LOGS.info("Element not Found");
			e.printStackTrace();
		}
	}
	
	public void deleteSavedFolder() throws InterruptedException {
		try {			
		Locators_SoundRec.listIcon.click();
		APP_LOGS.info("Pressed List Icon sucessfully");
		minWait();
		minWait();
		minWait();
		for(int i=1; i<=10; i++) {
			if(isElementExist(Locators_SoundRec.NorecordingMsg)) {		
			}else {
				TouchAction action = new TouchAction(aDriver);
				action.longPress(Locators_SoundRec.firstEntryFolder).release().perform();
				minWait();
				Locators_SoundRec.DeleteIcon.click();
				minWait();
				Locators_SoundRec.DeleteBtn.click();
				APP_LOGS.info("Pressed Delete Button sucessfully");
				Thread.sleep(2000);
			}
			minWait();
			Locators_SoundRec.backBtn.click();
		}
		
		}
		catch (org.openqa.selenium.NoSuchElementException e) {
			APP_LOGS.info("Element not Found");
			e.printStackTrace();
		}
	}
	
	public void validateSoundRecordDeletion() throws InterruptedException {
		try {
		minWait();
		Locators_SoundRec.listIcon.click();
		minWait();
		minWait();
		String getMsg = Locators_SoundRec.NorecordingMsg.getText();
		if(getMsg.equalsIgnoreCase("No recording")){
			check=true;
			APP_LOGS.info("Sound Record is deleted succesfully");
			Assert.assertTrue(check);

		}
		else if(isElementExist(Locators_SoundRec.RecordingListText)){
			check=true;
			APP_LOGS.info("Sound Record is deleted succesfully");
			Assert.assertTrue(check);

		}else{
			APP_LOGS.info("Sound Record  is not deleted succesfully");
			Assert.fail();
		}minWait();
		Locators_SoundRec.backBtn.click();
		}
		catch (org.openqa.selenium.NoSuchElementException e) {
			APP_LOGS.info("Element not Found");
			e.printStackTrace();
		}
	}
	
	//Edit Saved Recoreded Sound
	public void editSoundRecordName() throws InterruptedException {
		try {
		minWait();
		Locators_SoundRec.listIcon.click();
		minWait();
		TouchAction action = new TouchAction(aDriver);
		action.longPress(Locators_SoundRec.firstEntry).release().perform();
		minWait();
		Locators_SoundRec.EditIcon.click();
		minWait();
		Locators_SoundRec.recordingName.clear();
		minWait();
		enterTextToInputField(Locators_SoundRec.recordingName,"DemoSoundRecord");
		minWait();
		Locators_SoundRec.saveBtn.click();
		minWait();
		Locators_SoundRec.CloseBtn.click();	
		minWait();
		Locators_SoundRec.backBtn.click();
		}
		catch (org.openqa.selenium.NoSuchElementException e) {
			APP_LOGS.info("Element not Found");
			e.printStackTrace();
		}
	}
     
	public void validateSoundRecordEdit() throws InterruptedException {
		try {
		minWait();
		Locators_SoundRec.listIcon.click();
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
		Locators_SoundRec.backBtn.click();		
		}
		catch (org.openqa.selenium.NoSuchElementException e) {
			APP_LOGS.info("Element not Found");
			e.printStackTrace();
		}
	}
	
	//Adding Multiple sound Records
	public void addMultipleSoundRec() throws InterruptedException {
		try {
		for(int i=0; i<=2; i++){
			recordSound();
			minWait();
			Locators_SoundRec.recordingName.clear();
			minWait();
			enterTextToInputField(Locators_SoundRec.recordingName,"SampleSoundRecord"+i);
			minWait();
			Locators_SoundRec.saveBtn.click(); 
			APP_LOGS.info("Pressed Save button sucessfully");
			minWait();
			Locators_SoundRec.backBtn.click();
		}
		}
		catch (org.openqa.selenium.NoSuchElementException e) {
			APP_LOGS.info("Element not Found");
			e.printStackTrace();
		}
	}
	
	//Delete Sound Record using SelectAll Option
	public void deleteMulSoundRec() throws InterruptedException {
		try {
		minWait();
		Locators_SoundRec.listIcon.click();
		minWait();
		TouchAction action = new TouchAction(aDriver);
		action.longPress(Locators_SoundRec.firstEntry).release().perform();
		minWait();
		Locators_SoundRec.SelectDropDown.click();
		APP_LOGS.info("Selected Dropdown option sucessfully");
		minWait();
		Locators_SoundRec.SelectAllOpt.click();
		APP_LOGS.info("Selected All Sound Records sucessfully");
		minWait();
		Locators_SoundRec.DeleteIcon.click();
		minWait();
		Locators_SoundRec.DeleteBtn.click();
		minWait();
		Locators_SoundRec.backBtn.click();	
		}
		catch (org.openqa.selenium.NoSuchElementException e) {
			APP_LOGS.info("Element not Found");
			e.printStackTrace();
		}
		
	}
	
	//Selecting All Sound Recording from checkBox
	public void selectAllSoundRec() throws InterruptedException {
		try {
		minWait();
		Locators_SoundRec.listIcon.click();
		minWait();
		TouchAction action = new TouchAction(aDriver);
		action.longPress(Locators_SoundRec.firstEntry).release().perform();
		minWait();
		//List<AnroidElement> list=(List) aDriver.findElements(By.className(""));
		minWait();
		Locators_SoundRec.SelectDropDown.click();
		APP_LOGS.info("Selected Dropdown option sucessfully");
		minWait();
		Locators_SoundRec.SelectAllOpt.click();
		APP_LOGS.info("Selected All Sound Records sucessfully");
		minWait();
		}
		catch (org.openqa.selenium.NoSuchElementException e) {
			APP_LOGS.info("Element not Found");
			e.printStackTrace();
		}
	}
	
	//Deselect All Sound Recording from Deselct Option
	public void deslectAllSoundRec() throws InterruptedException {
		try {
		minWait();
		Locators_SoundRec.SelectDropDown.click();
		APP_LOGS.info("Selected Dropdown option sucessfully");
		minWait();
		Locators_SoundRec.DeSelectAllOpt.click();
		APP_LOGS.info("Deselected All Sound Records sucessfully");
		minWait();
		}
		catch (org.openqa.selenium.NoSuchElementException e) {
			APP_LOGS.info("Element not Found");
			e.printStackTrace();
		}
	}
	
	public void validateSelectAll() throws InterruptedException {
		try {
		minWait();
		if(isElementExist(Locators_SoundRec.DeleteIcon)&&isElementExist(Locators_SoundRec.ShareIcon)){
			check=true;
			APP_LOGS.info("Validayed Sound Record List is selected succesfully");
			Assert.assertTrue(check);
		}else{ 
		APP_LOGS.info("Sound Record List is not selected succesfully");
		Assert.fail();
		}minWait();
	}
	catch (org.openqa.selenium.NoSuchElementException e) {
		APP_LOGS.info("Element not Found");
		e.printStackTrace();
	}
		//Locators_SoundRec.backBtn.click();	
	}
	
	
	
	public void validateDeselectAll() throws InterruptedException {
		try {
		minWait();
		if(isElementExist(Locators_SoundRec.DeleteIcon)&&isElementExist(Locators_SoundRec.ShareIcon)){
			APP_LOGS.info("Sound Record List is not deselected succesfully");
			Assert.fail();
		}else{ check=true;
		APP_LOGS.info("Validated Sound Record List is deselected succesfully");
		Assert.assertTrue(check);
		}minWait();
		Locators_SoundRec.backBtn.click();	
		}
		catch (org.openqa.selenium.NoSuchElementException e) {
			APP_LOGS.info("Element not Found");
			e.printStackTrace();
		}
	}
	
	 
	public void validateMulDeleteSoundRec() throws InterruptedException {
		validateSoundRecordDeletion();
	}
	
	public void playSoundRecord() throws InterruptedException {
		try {
		minWait();
		SaveSoundRecord();
		minWait();
		Locators_SoundRec.listIcon.click();
		minWait();
		Locators_SoundRec.firstEntry.click();
		minWait();
		minWait();
		}
		catch (org.openqa.selenium.NoSuchElementException e) {
			APP_LOGS.info("Element not Found");
			e.printStackTrace();
		}
		
	}
	
	public void validateRecSoundPlay() throws InterruptedException {
		try {
		//Select recording to play from recording list
		if(isElementExist(Locators_SoundRec.PauseBtnList)) {
			check = true;
			APP_LOGS.info("Playing Recorded Sound succesful.");
			Assert.assertTrue(check);
		}
		else {
			APP_LOGS.info("Recorded Sound is not Played unsuccesful.");
			Assert.fail();
		}minWait();
		Locators_SoundRec.backBtn.click();	
		}
		catch (org.openqa.selenium.NoSuchElementException e) {
			APP_LOGS.info("Element not Found");
			e.printStackTrace();
		}
	}
	
	public void deviceSilentMode() throws InterruptedException {
		try {
		minWait();
		minWait();
		longpress(25);
		minWait();
		Locators_SoundRec.recordBtn.click();
		minWait();
		}
		catch (org.openqa.selenium.NoSuchElementException e) {
			APP_LOGS.info("Element not Found");
			e.printStackTrace();
		}
		
	}
	
	
	public void launchFilemanager() throws InterruptedException {
		 try {
			 launchApp("FILEMGR_PACKAGE","FILEMGR_ACTIVITY");
		 
		   customWait(2000);
		   minWait();
		   //customWait(2000);
		  Locators_SoundRec.SearchOptn.click();
		  minWait();
		  enterTextToInputField(Locators_SoundRec.SearchTxt,"SoundRecorder");
		  if(isElementExist(Locators_SoundRec.HistorySearch)){
		   Locators_SoundRec.HistorySearch.click();
		  }
		   minWait();
		 
		  Locators_SoundRec.SelectSoundRec.click();
			}
			catch (org.openqa.selenium.NoSuchElementException e) {
				APP_LOGS.info("Element not Found");
				e.printStackTrace();
			}
		 }
	
	public void validateDetailsSoundRec() throws InterruptedException {
		try {		
		if(isElementExist(Locators_SoundRec.SampleSoundRec)){
			check = true;
			String getSoundRecName= Locators_SoundRec.SampleSoundRec.getText();
			APP_LOGS.info("Sound Recorder Name is :"+getSoundRecName);
		}
		else{
			APP_LOGS.info("All Details of Sound Record is not Displayed");
			Assert.fail();
		}	  customWait(2000);
		minWait();
		logout();
		minWait();
		logout();
		minWait();
		logout();
		minWait();
		logout();
		}
		catch (org.openqa.selenium.NoSuchElementException e) {
			APP_LOGS.info("Element not Found");
			e.printStackTrace();
		}
	}
	
	
	public void validateCallInteraction() {		
		searchString("startService:1357","XP8_SR_012");
	}
	
	public void initialCall() throws InterruptedException, IOException {
		Runtime.getRuntime().exec("adb -s c1edac9c shell am start -a android.intent.action.CALL -d tel:555-5555");
		customWait(5000);
		aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_ENDCALL);
	}
	
	public void logout() throws InterruptedException {
		longpress(4);
		minWait();
	}
	
	public void startRecord() throws InterruptedException {
		Locators_SoundRec.recordBtn.click();
		minWait();
		
	}
	
	public void stopAdb() throws InterruptedException, IOException {
		  customWait(4000);
		  Runtime.getRuntime().exec("taskkill /IM cmd.exe");
		  APP_LOGS.info("Adb logs stopped succesfully. ");
		  customWait(2000);
		 }
	
	public void launchSounchRec() throws InterruptedException, IOException {
		Runtime.getRuntime().exec("cmd /c start /min cmd.exe /K \"adb shell am start -n com.android.soundrecorder/com.android.soundrecorder.SoundRecorder");
		customWait(5000);
		minWait();		
	}
	
}
