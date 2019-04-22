package com.xp3.Utils;

import static org.testng.Assert.assertTrue;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.testng.asserts.SoftAssert;

import com.graphics.gui.JsonFileReaderAndWriter;
import com.relevantcodes.extentreports.LogStatus;
import com.xp5S.util.CommonConfig;

import application.AllQA;
import io.appium.java_client.android.AndroidKeyCode;
import junit.framework.Assert;



public class XP3_SCOUT_Contact_Transfer_Util extends BaseUtil {

	public boolean check = false;
	public boolean check1 = false;
	public boolean check2 = false;
	public boolean check3 = false;
	public boolean check4 = false;


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
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void pushFiles() {
		/*
		 * delete pushed files in device
		 */
		try {
			Runtime.getRuntime().exec("adb -s "+ p_Id +" push src/test/resources/StorageFile/i475.csv /storage/emulated/0/Download");
			Runtime.getRuntime().exec("adb -s "+ p_Id +" push src/test/resources/StorageFile/i475.mdb /storage/emulated/0/Download");
			Runtime.getRuntime().exec("adb -s "+ p_Id +" push src/test/resources/StorageFile/i475.vcf /storage/emulated/0/Download");
			Runtime.getRuntime().exec("adb -s "+ p_Id +" push src/test/resources/StorageFile/i476.vcf /storage/emulated/0/Download");
		} catch (Exception e) {
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
	
	public void deleteExistingFiles() {
		/*
		 * check and delete's existing files in download folder in File explorer
		 */
		try {
			launch_an_app("fileExplorer");
			customWait(2000);
			//clickDownButton(2);
			clickDownButtonruntime(4);
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
			clickDownButton(2);
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
			customWait(2000);
			takeScreenShotForOcrPngFormat("Contact Transfer");
			Thread.sleep(2000);
			enlargeImage("src/test/resources/OCR_FILES/Contact Transfer.png", "src/test/resources/OCR_FILES/Contact Transfer.png");
			minWait();
			OCR.my_main.validate_Using_OCR("Contact Transfer.png");
			customWait(2000);
			Boolean value = searchStringOCR("N0 items", "ocr");
			if(!value) {
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_MENU);
				minWait();
				scrollText("Select");
				clickBtn(Locators_Contact_Transfer.select_option);
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_MENU);
				minWait();
				scrollText("Select all");
				clickBtn(Locators_Contact_Transfer.Select_all_option);
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_MENU);
				minWait();
				scrollText("Delete");
				clickBtn(Locators_Contact_Transfer.delete_option);
				minWait();
				clickBtn(Locators_Contact_Transfer.OK_Btn);
			}else {
				clickBackButton(4);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void enlargeImage(String inputImagePatgh,String outputImagePath) throws IOException {

		File input = new File(inputImagePatgh);
		BufferedImage image = ImageIO.read(input);

		BufferedImage resized = resize(image, 800, 600);

		File output = new File(outputImagePath);
		ImageIO.write(resized, "png", output);

	}

	private static BufferedImage resize(BufferedImage img, int height, int width) {
		Image tmp = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
		BufferedImage resized = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2d = resized.createGraphics();
		g2d.drawImage(tmp, 0, 0, null);
		g2d.dispose();
		return resized;
	}

	public void Launch_Contact_Transfer() throws InterruptedException, IOException{
		/*
		 * Launch's contact transfer application
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
			customWait(3000);
			navigateApps(Locators_Contact_Transfer.utilitiesTab,Locators_Contact_Transfer.ContactTransferTitle);
			if(isElementExist(Locators_Contact_Transfer.permission_pop_up)) {
				for(int i=0;i<3;i++) {
					minWait();
					clickBtn(Locators_Contact_Transfer.allow_btn);
					minWait();
				}
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {
			e.printStackTrace();
			sf.fail();
		}	
		sf.assertAll();
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
					clickBtn(Locators_Contact_Transfer.HomeBtn_Wizard);
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
			// TODO Auto-generated catch block
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

	public void validateTransferModesPresent() throws InterruptedException {
		/*
		 * Validating by elements for all transfer modes present 
		 */
		SoftAssert sf = new SoftAssert();
		try {	
			if(isElementExist(Locators_Contact_Transfer.BluetoothOptn)){
				check1=true;
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
				minWait();
			} if(isElementExist(Locators_Contact_Transfer.VCFOptn)){
				check2=true;
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
				minWait();
			} if(isElementExist(Locators_Contact_Transfer.MDBOptn)){
				check3=true;
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
				minWait();
			} if(isElementExist(Locators_Contact_Transfer.CSVOptn)){
				check4=true;
			}
			if((check1)&&(check2)&&(check3)&&(check4)) {
				check= true;
				APP_LOGS.info("All Transfer Mode Present");
				sf.assertTrue(check, "Validation is Pass");
				test.log(LogStatus.INFO,"All Transfer Modes are Present");
				test.log(LogStatus.PASS, "Test case status is Passed");
			}
			else{
				APP_LOGS.info(check1+"\n"+": "+check2+"\n"+": "+check3+"\n"+": "+check4+ "\n");
				test.log(LogStatus.FAIL, "Test case status is Failed");
				sf.fail();
			}	
			clickBackButton(2);
		}catch (NoSuchElementException e) {
			APP_LOGS.info("Error: Element not found.");
			e.printStackTrace();
			sf.fail();
		}
		sf.assertAll();
	}

	public void select_Transfer_Mode(String mode) throws InterruptedException{
		/*
		 * Selects the mode of transfer in contact transfer screen.
		 * Pass mode as parameter.
		 */
		try {
			if(mode.equalsIgnoreCase("Bluetooth")){
				minWait();
				clickDownButton(2);
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
				minWait();
				APP_LOGS.info("Selected BT option");
			}else if(mode.equalsIgnoreCase("mdb")){
				minWait();
				clickDownButton(3);
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
				minWait();
				APP_LOGS.info("Selected MBD option");
			}else if(mode.equalsIgnoreCase("vcf")){
				minWait();
				clickDownButton(4);
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
				minWait();
				APP_LOGS.info("Selected VCF option");
			}else if(mode.equalsIgnoreCase("csv")){
				minWait();
				clickDownButton(5);
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
				minWait();
				APP_LOGS.info("Selected CSV option");
			}
			
			if(isElementExist(Locators_Contact_Transfer.permission_pop_up)) {
				for(int i=0;i<3;i++) {
					minWait();
					clickBtn(Locators_Contact_Transfer.allow_btn);
					minWait();
				}
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {
			APP_LOGS.info("No element found");
			e.printStackTrace();
			Assert.fail();
		}
	}

	public void selectContactfileToImport() throws InterruptedException, IOException {
		/*
		 * Select file and choose all contact to import
		 */
		try {

			for(int i=0; i<4;i++) {
				minWait();
				clickDownButton(1);
				APP_LOGS.info(" Clicked Download folder");
			}
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_ENTER);
			minWait();
			clickDownButton(1);
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_ENTER);//Open Contacts file to be imported
			customWait(10000);
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_RIGHT);
			minWait();
			/*aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_RIGHT);
			minWait();*/
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);//selects All option
			clickDownButton(1);
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_LEFT);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);//selects All option
		}
		catch (NoSuchElementException e) {
			APP_LOGS.info("Error: Element not found.");
			e.printStackTrace();
			Assert.fail();
		}	
	}

	public void validateAllContactImport(String AdbTxt, String mode) throws InterruptedException, IOException {
		/*
		 * String validate Imported summary
		 */
		SoftAssert S = new SoftAssert();
		customWait(10000);
		check1 = searchString("Checkbox Imported Successfully",AdbTxt);
		minWait();
		if(check1) {
			check = true;
			S.assertTrue(check, "Validation is Pass");
			APP_LOGS.info("Contacts are imported via "+mode);
			test.log(LogStatus.INFO,"Contacts are imported via "+mode);
			test.log(LogStatus.PASS, "Test case status is Passed");
		}else {
			APP_LOGS.info("Failed to add "+mode+" contacts");
			test.log(LogStatus.ERROR,"Failed to add "+mode+" contacts");
			test.log(LogStatus.FAIL, "Test case status is Failed");
			S.fail();
		}
		S.assertAll();
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
	
	public void selectUFMIfileToImport() throws InterruptedException, IOException {
		/*
		 * Select UFMI file and choose all contact to import
		 */
		try {
			minWait();
			for(int i=0; i<4;i++) {
				minWait();
				clickDownButton(1);
				APP_LOGS.info(" Clicked Download folder");
			}
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_ENTER);
			minWait();
			clickDownButton(2);
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_ENTER);//Open Contacts file to be imported
			customWait(10000);
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_RIGHT);
			minWait();
			/*aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_RIGHT);
			minWait();*/
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);//selects All option
			clickDownButton(1);
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_LEFT);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);//selects All option
		}
		catch (NoSuchElementException e) {
			APP_LOGS.info("Error: Element not found.");
			e.printStackTrace();
			Assert.fail();
		}	
	}
	
	public void validateUFMIContactImport(String AdbTxt, String mode) throws InterruptedException, IOException {
		/*
		 * String validate Imported summary
		 */
		SoftAssert S = new SoftAssert();
		customWait(7000);
		customWait(5000);
		check1 = searchString("Checkbox Imported Successfully",AdbTxt);
		minWait();
		if(check1) {
			check = true;
			S.assertTrue(check, "Validation is Pass");
			APP_LOGS.info("UFMI Contacts imported successfully");
			test.log(LogStatus.INFO,"UFMI Contacts imported successfully");
			test.log(LogStatus.PASS, "Test case status is Passed");
		} else {
			APP_LOGS.info("Failed to add UFMI contacts");
			test.log(LogStatus.ERROR,"Failed to add UFMI contacts");
			test.log(LogStatus.FAIL, "Test case status is Failed");
			S.fail();
		}
		S.assertAll();
	}

	public void push_to_background_and_verify() throws InterruptedException, IOException{
		/*
		 * Push Contact Transfer to background,launch messaging and validate
		 */
		SoftAssert sf = new SoftAssert();
		try {
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_HOME);
			minWait();
			launch_an_app("messaging");
			customWait(2000);
			validate_launchofApp(Locators_Contact_Transfer.messaging_Title, "Messaging");
		} catch (org.openqa.selenium.NoSuchElementException e) {
			e.printStackTrace();
			sf.fail();
		}	
		sf.assertAll();
	}	

	public void validate_launchofApp(WebElement element, String elementName) throws InterruptedException {
		/*
		 * Launch's and validates launched app
		 */
		SoftAssert Sa = new SoftAssert();
		try {
			customWait(6000);
			if (isElementExist(element)) {
				check = true;
				APP_LOGS.info("Pushed Contact Transfer application to background and "+elementName + " is found succesfully");
				test.log(LogStatus.INFO, "Pushed Contact Transfer application to background and "+elementName + " Application sucessfully Launched ");
				Sa.assertTrue(check, "Tc Passed");
			} else {
				check = false;
				APP_LOGS.info("Failed to push Contact Transfer application to background");
				test.log(LogStatus.ERROR, elementName + "Failed to push Contact Transfer application to background");
				Sa.fail();
			}
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			APP_LOGS.info("No Such Element Found");
			test.log(LogStatus.ERROR,"No Such Element Found");
			Sa.fail();
		}
		Sa.assertAll();
	}
	
	public void launch_from_background_and_verify() throws InterruptedException, IOException{
		/*
		 * Launch contact transfer from recent apps and validate
		 */
		SoftAssert sf = new SoftAssert();
		try {
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_HOME);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_APP_SWITCH);
			customWait(1500);
			if(!isElementExist(Locators_BaseUtil.noRecentApp)) {
				if(isElementExist(Locators_Contact_Transfer.ContactTransferTitle)) {
					aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
					customWait(2000);
					validateLaunchScoutApp(Locators_Contact_Transfer.ContactTransferTitle, "Contact Transfer");
				}else {
					scrollToElementWithDpadUp(Locators_Contact_Transfer.ContactTransferTitle);
					aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
					customWait(2000);
					validateLaunchScoutApp(Locators_Contact_Transfer.ContactTransferTitle, "Contact Transfer");
				}
			}else {
				test.log(LogStatus.ERROR,"No Recent apps to launch");
				test.log(LogStatus.FAIL, "Test case status is Failed");
				sf.fail();
			}
			clickBackButton(2);
		} catch (org.openqa.selenium.NoSuchElementException e) {
			e.printStackTrace();
			sf.fail();
		}	
		sf.assertAll();
	}	

	public void scrollToElementWithDpadUp(WebElement element) {
		/*
		 * Clicks up button till element is available
		 */
		while(!isElementExist(element)) {
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_UP);
		}
	}
	
	public void validate_key_press() throws InterruptedException {
		/*
		 * validate press any key
		 */
		SoftAssert sf = new SoftAssert();
		try {
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_HOME);
			minWait();
			if(isElementExist(Locators_Contact_Transfer.messaging_Title)) {
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_APP_SWITCH);
				customWait(1500);
				aDriver.longPressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
				customWait(2000);
				takeScreenShotForOcrPngFormat("Contact Transfer");
				Thread.sleep(2000);
				OCR.my_main.validate_Using_OCR("Contact Transfer.png");
				customWait(2000);
				Boolean value = searchStringOCR("lmporllng contacts", "ocr");
				if(value) {
					check = true;
					APP_LOGS.info("Press any key while contacts transfer in progress validated successfully and contacts transfered succesfully");
					test.log(LogStatus.INFO, "Press any key while contacts transfer in progress validated successfully and contacts transfered succesfully");
					test.log(LogStatus.PASS, "Test case status is Passed");
					sf.assertTrue(check, "Tc Passed");
				}else {
					check = false;
					APP_LOGS.info("Failed to verify press any key when contacts in progress");
					test.log(LogStatus.ERROR, "Failed to verify press any key when contacts in progress");
					test.log(LogStatus.FAIL, "Test case status is Failed");
					sf.fail();
				}
			}
			clickBackButton(2);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void disableBluetooth() throws InterruptedException {
		/*
		 * Navigates to BT settings and disables Bluetooth
		 */
		try {
			clickBtn(Locators_Contact_Transfer.connectedDevices_oreo);
			minWait();
			clickBtn(Locators_Contact_Transfer.Bluetooth);
			minWait();
			if (isElementExist(Locators_Contact_Transfer.enabled)) {
				customWait(2000);
				clickBtn(Locators_Contact_Transfer.enabled);
				APP_LOGS.info("Feature is disabled");
				minWait();
				clickBackButton(3);
			}else {
				clickBackButton(3);
			}
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "Disable toggle : No Such Element Found");
			Assert.fail();
		}
	}
	
	public void enable_BT_and_verify_from_CT() throws InterruptedException {
		/*
		 * Enables BT from Contact transfer and verify
		 */
		SoftAssert sf = new SoftAssert();
		try {
			customWait(4000);
			//clickBtn(Locators_Contact_Transfer.Allow_BT_Btn);
			clickDownButton(1);
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_RIGHT);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
			minWait();
			if(isElementExist(Locators_Contact_Transfer.searchingBT)) {
				check = true;
				test.log(LogStatus.INFO, "BT enabled from Contact Transfer succesfully");
				sf.assertTrue(check, "Tc Passed");
			}else{
				test.log(LogStatus.ERROR, "Failed to enable Bluetooth from contact transfer");
				sf.fail();
			}
		}
		catch (NoSuchElementException e) {
			e.printStackTrace();
			sf.fail();
		}
	}

	public void scan_for_BT_devices_verify() throws InterruptedException {
		/*
		 * Clicks and validates scan button
		 */
		SoftAssert sf = new SoftAssert();
		try {
			customWait(10000);
			if(isElementExist(Locators_Contact_Transfer.scan_Button)) {
				clickBtn(Locators_Contact_Transfer.scan_Button);
				minWait();
				if(isElementExist(Locators_Contact_Transfer.searchingBT)) {
					check = true;
					test.log(LogStatus.INFO, "Scan option verified successfully");
					test.log(LogStatus.PASS, "Test case status is Passed");
					sf.assertTrue(check, "Tc Passed");
				}else{
					test.log(LogStatus.ERROR, "Failed to verify scan option");
					test.log(LogStatus.FAIL, "Test case status is Failed");
					sf.fail();
				}
			}else {
				test.log(LogStatus.ERROR, "Scan button not enabled");
				test.log(LogStatus.FAIL, "Test case status is Failed");
				sf.fail();
			}
			clickBackButton(2);
		}
		catch (NoSuchElementException e) {
			e.printStackTrace();
			Assert.fail();
		}
	}
	
	public void deny_and_verify() throws InterruptedException {
		/*
		 * validates deny option 
		 */
		SoftAssert sf = new SoftAssert();
		try {
			takeScreenShotForOcrPngFormat("Contact Transfer");
			Thread.sleep(2000);
			OCR.my_main.validate_Using_OCR("Contact Transfer.png");
			customWait(2000);
			Boolean value1 = searchStringOCR("Contact Transfer warns lo", "ocr");
			Boolean value2 = searchStringOCR("mm on aluemom", "ocr");
			if(value1 && value2) {
				clickDownButton(1);
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
				customWait(5000);
				takeScreenShotForOcrPngFormat("Contact Transfer");
				Thread.sleep(2000);
				OCR.my_main.validate_Using_OCR("Contact Transfer.png");
				customWait(2000);
				Boolean value3 = searchStringOCR(" will need to be", "ocr");
				Boolean value4 = searchStringOCR("enabled to useme ET", "ocr");
				Boolean value5 = searchStringOCR("Transfer feature.", "ocr");
				if(value3 && value4 && value5) {
					clickDownButton(1);
					aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
					check = true;
					test.log(LogStatus.INFO, "Deny and ok option verified successfully");
					test.log(LogStatus.PASS, "Test case status is Passed");
					sf.assertTrue(check, "Tc Passed");
				}else{
					test.log(LogStatus.ERROR, "Failed to verify Deny option");
					test.log(LogStatus.FAIL, "Test case status is Failed");
					sf.fail();
				}
			}else {
				test.log(LogStatus.ERROR, "Enable BT pop up not available");
				test.log(LogStatus.FAIL, "Test case status is Failed");
				sf.fail();
			}
			clickBackButton(3);
		}
		catch (NoSuchElementException e) {
			e.printStackTrace();
			Assert.fail();
		}
	}
	
	public void validateSearchfield(String contactName) throws InterruptedException, IOException {
		/*
		 * validates search field
		 */
		SoftAssert sf = new SoftAssert();
		try {
			minWait();
			for(int i=0; i<4;i++) {
				minWait();
				clickDownButton(1);
				APP_LOGS.info(" Clicked Download folder");
			}
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_ENTER);
			clickDownButton(2);
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_ENTER);//Open Contacts file to be imported
			customWait(2000);
			//	enterTextToInputField(Locators_Contact_Transfer.search_field, contactName);
			Runtime.getRuntime().exec("adb -s "+p_Id+" shell input text "+ contactName);
			customWait(2000);
			takeScreenShotForOcrPngFormat("Contact Transfer");
			minWait();
			enlargeImage("src/test/resources/OCR_FILES/Contact Transfer.png", "src/test/resources/OCR_FILES/Contact Transfer.png");
			Thread.sleep(2000);
			OCR.my_main.validate_Using_OCR("Contact Transfer.png");
			customWait(2000);
			Boolean value1 = searchStringOCR("S0uthernLlNC", "ocr");
			Boolean value2 = searchStringOCR("Work", "ocr");
			
		/*	clickDownButton(1);
			customWait(2000);
			System.out.println(Locators_Contact_Transfer.mdbContact.getText());
			if(isElementExist(Locators_Contact_Transfer.mdbContact)) {*/
			
			if(value1||value2) {
				check = true;
				test.log(LogStatus.INFO, "Search field validated successfully");
				sf.assertTrue(check, "Tc Passed");
			}else{
				test.log(LogStatus.ERROR, "Failed to validate search field");
				sf.fail();
			}
			/*aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_UP);
			minWait();*/
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_RIGHT);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
		}
		catch (NoSuchElementException e) {
			e.printStackTrace();
			Assert.fail();
		}
		sf.assertAll();
	}
	
	public void validateSelectAll() throws InterruptedException, IOException {
		/*
		 * validates select all option
		 */
		SoftAssert sf = new SoftAssert();
		try {
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_RIGHT);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
			minWait();
			customWait(5000);
			for(int i=0;i<7;i++) {
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
				minWait();
			}
			takeScreenShotForOcrPngFormat("Contact Transfer");
			Thread.sleep(2000);
		//	enlargeImage("src/test/resources/OCR_FILES/Contact Transfer.png", "src/test/resources/OCR_FILES/Contact Transfer.png");
			customWait(2000);
			OCR.my_main.validate_Using_OCR("Contact Transfer.png");
			customWait(2000);
			Boolean value1 = searchStringOCR("o|<(5)", "ocr");
			Boolean value2 = searchStringOCR("0K(5)", "ocr");
			
			/*System.out.println(Locators_Contact_Transfer.selectAll_checkbox.getText());
			if(isElementExist(Locators_Contact_Transfer.selectAll_checkbox)) {*/
			if(value1 || value2) {
			
	//		if(isElementExist(Locators_Contact_Transfer.selectAll_checkbox)) {
				check = true;
				test.log(LogStatus.INFO, "Select all option validated successfully");
				test.log(LogStatus.PASS, "Test case status is Passed");
				sf.assertTrue(check, "Tc Passed");
			}else{
				test.log(LogStatus.ERROR, "Failed to validate select all option");
				test.log(LogStatus.FAIL, "Test case status is Failed");
				sf.fail();
			}
			clickBackButton(4);
		}
		catch (NoSuchElementException e) {
			e.printStackTrace();
			Assert.fail();
		}
		sf.assertAll();
	}

	public void verify_Notification_bar_when_import_in_progress() throws InterruptedException, IOException {
		/*
		 * verifies notification bar when contact transfer in progress
		 */
		SoftAssert sf = new SoftAssert();
		try {
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_ENDCALL);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_MENU);
			minWait();
			clickBtn(Locators_Contact_Transfer.myNotification);
			minWait();
			takeScreenShotForOcrPngFormat("Contact Transfer");
			Thread.sleep(2000);
			OCR.my_main.validate_Using_OCR("Contact Transfer.png");
			customWait(2000);
			Boolean value1 = searchStringOCR("Importing con", "ocr");
			Boolean value2 = searchStringOCR("Status", "ocr");
			if(value1 && value2) {
				check = true;
				test.log(LogStatus.INFO, "Veirified when contact transfer in progress from notification bar successfully");
				sf.assertTrue(check, "Tc Passed");
			}else{
				test.log(LogStatus.ERROR, "Failed to Veirify when contact transfer in progress from notification bar");
				sf.fail();
			}
		}
		catch (NoSuchElementException e) {
			e.printStackTrace();
			Assert.fail();
		}
	}

	public void verify_succes_icon_in_Notification_bar() throws InterruptedException, IOException {
		/*
		 * verifies contact transfer success icon from notification bar
		 */
		SoftAssert sf = new SoftAssert();
		try {
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_ENDCALL);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_MENU);
			minWait();
			clickBtn(Locators_Contact_Transfer.myNotification);
			minWait();
			customWait(10000);
			takeScreenShotForOcrPngFormat("Contact Transfer");
			Thread.sleep(2000);
			OCR.my_main.validate_Using_OCR("Contact Transfer.png");
			customWait(2000);
			Boolean value1 = searchStringOCR("Completed conlac1imp..", "ocr");
			if(value1) {
				check = true;
				test.log(LogStatus.INFO, "Veirified contact import success from notification bar successfully");
				test.log(LogStatus.PASS, "Test case status is Passed");
				sf.assertTrue(check, "Tc Passed");
			}else{
				test.log(LogStatus.ERROR, "Failed to Veirify contact import success from notification bar");
				test.log(LogStatus.FAIL, "Test case status is Failed");
				sf.fail();
			}
		}
		catch (NoSuchElementException e) {
			e.printStackTrace();
			Assert.fail();
		}
	}
	
	public void deleteFiles() {
		/*
		 * Delete's pushed files
		 */
		try {
			Runtime.getRuntime().exec("adb -s "+ p_Id +" shell rm -r /storage/emulated/0/Download/i475.csv");
			Runtime.getRuntime().exec("adb -s "+ p_Id +" shell rm -r /storage/emulated/0/Download/i475.mdb");
			Runtime.getRuntime().exec("adb -s "+ p_Id +" shell rm -r /storage/emulated/0/Download/i475.vcf");
			Runtime.getRuntime().exec("adb -s "+ p_Id +" shell rm -r /storage/emulated/0/Download/i476.vcf");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
}
