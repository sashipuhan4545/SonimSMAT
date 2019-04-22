package com.xp5S.util;

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

import application.AllQA;
import io.appium.java_client.android.AndroidKeyCode;
import junit.framework.Assert;

public class XP5S_SCOUT_SonimCare_Util extends BaseUtil {

	public boolean check = false;
	public boolean check1 = false;
	public boolean check2 = false;
	public boolean check3 = false;
	public boolean check4 = false;
	public boolean check5 = false;

	public String p_Id	 = "";								// Primary Device Serial Number.
	public String r_Id	 = "";								// Reference Device Serial Number.
	public String p_b_No = "";			   					// Primary Device Build Number.
	public String r_b_No = ""; 								// Reference Device Build Number.
	public String pryNum = AllQA.PRIMARYDEVMDN;	// Reference Device MDN. 
	public String refNum = AllQA.REFERENCEDEVMDN;	// Reference Device MDN.
	//Added comment to test

	public void fetch_Devices_Details() throws InterruptedException, FileNotFoundException, IOException, org.json.simple.parser.ParseException {
		/*
		 * fetching device details
		 */
		System.out.println("In Fetching");
		minWait();
		p_Id=JsonFileReaderAndWriter.primaryDevIdReader();
		r_Id=JsonFileReaderAndWriter.ReadRefDeviceId();
		p_b_No=JsonFileReaderAndWriter.primaryDevFirmwareReader();
		System.out.println(p_b_No);
		r_b_No=JsonFileReaderAndWriter.ReadRefDeviceFirmWare();
	}

	public static void takeScreenShotForOcrPngFormat(String imageName) {
		/*
		 * Take screenshot in png format 
		 */

		File scrFile = ((TakesScreenshot) CommonConfig.getDriver()).getScreenshotAs(OutputType.FILE);
		try {

			String ocrImageFilePath = System.getProperty("user.dir") + "\\src\\test\\resources\\OCR_FILES\\" +imageName+".png";
			FileUtils.copyFile(scrFile, new File(ocrImageFilePath));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("<-----------Exeption occurs in takeScreenShotsForOcr method----------->");
		}

	}

	public static void enlargeImage(String inputImagePath,String outputImagePath) throws IOException {
		/*
		 *   Enlarge Image 
		 */
		File input = new File(inputImagePath);
		BufferedImage image = ImageIO.read(input);

		BufferedImage resized = resize(image, 800, 600);

		File output = new File(outputImagePath);
		ImageIO.write(resized, "png", output);

	}

	private static BufferedImage resize(BufferedImage img, int height, int width) {
		/*
		 * Buffered Image Resize
		 */
		Image tmp = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
		BufferedImage resized = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2d = resized.createGraphics();
		g2d.drawImage(tmp, 0, 0, null);
		g2d.dispose();
		return resized;
	}

	public void clickBackButton(int number){
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


	public void Launch_SonimCare() throws InterruptedException, IOException{
		/*
		 * Launch's SafeGuard application
		 */
		SoftAssert sf = new SoftAssert();
		try {
			launch_an_app("scout");
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_LEFT);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_LEFT);
			customWait(2000);
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_RIGHT);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_RIGHT);
			customWait(2000);
			navigateApps(SCOUT_Locators_SonimCare.supportTab,SCOUT_Locators_SonimCare.SonimCare);
			if(isElementExist(SCOUT_Locators_SonimCare.permission_pop_up)) {
				for(int i=0;i<3;i++) {
					minWait();
					clickBtn(SCOUT_Locators_SonimCare.allow_btn);
					minWait();
				}
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {
			e.printStackTrace();
			sf.fail();
		}	
		sf.assertAll();
	}	

	public void navigateApps(WebElement tab,WebElement app) throws InterruptedException, IOException {
		/*
		 * Navigates to given element in SCOUT app
		 */
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

	public void validateLaunchScoutApp(WebElement appTitle,String appName) throws InterruptedException {
		/*
		 * validate Scout APP's Launch
		 */
		try {
			if(isElementExist(appTitle)) {
				check = true;
				customWait(1000);
				clickBackButton(1);
				test.log(LogStatus.INFO, appName+" is launched and verified ");
				assertTrue(check);
				if(appName.equals("SetUpWizard")) {
					clickBtn(SCOUT_Locators_SonimCare.HomeBtn_Wizard);
				}
				test.log(LogStatus.PASS, "Test case status is Passed");
			}
			else {
				test.log(LogStatus.ERROR, appName+" launch is unverified");
				Assert.fail();
				test.log(LogStatus.FAIL, "Test case status is Failed");
			}
			clickBackButton(2);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void validatePressence_of_AllOptions() {
		SoftAssert SA = new SoftAssert();
		try {
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
			minWait();
			if(isElementExist(SCOUT_Locators_SonimCare.Self_diagnosis_Test)) {
				check1=true;
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
				minWait();
			} if(isElementExist(SCOUT_Locators_SonimCare.Battery_Diagnosis_Test)) {
				check2=true;
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
				minWait();
			} if(isElementExist(SCOUT_Locators_SonimCare.Diagnosis_Report)) {
				check3=true;
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
				minWait();
			} if(isElementExist(SCOUT_Locators_SonimCare.Videos)) {
				check4=true;
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
				minWait();
			} if(isElementExist(SCOUT_Locators_SonimCare.Online_Troubleshooting)) {
				check5=true;
				minWait();
			}

			if((check1)&&(check2)&&(check3)&&(check4)&&(check5)) {
				check= true;
				APP_LOGS.info("All the Options are Present");
				SA.assertTrue(check, "All the Options are Present");
				test.log(LogStatus.INFO,"All the Options are Present");
				test.log(LogStatus.PASS,"Test case status is Passed");
			}
			else{
				APP_LOGS.info(check1+"\n"+": "+check2+"\n"+": "+check3+"\n"+": "+check4+ "\n"+": "+check5+ "\n");
				test.log(LogStatus.INFO,"All the Options are not Present");
				test.log(LogStatus.FAIL,"Test case status is Failed");
				SA.fail();
			}	
			clickBackButton(2);
		} catch (Exception e) {
			APP_LOGS.info("Element not Found");
			e.printStackTrace();
		}
		SA.assertAll();
	}

	public void navigate_and_validate_self_diagnosis_welcome_screen() {
		SoftAssert SA = new SoftAssert();
		try {
			minWait();
			clickBtn(SCOUT_Locators_SonimCare.Self_diagnosis_Test);
			minWait();
			if(isElementExist(SCOUT_Locators_SonimCare.Self_diagnosis)) {
				if(isElementExist(SCOUT_Locators_SonimCare.Self_diagnosis_welcome_text) && isElementExist(SCOUT_Locators_SonimCare.Self_diagnosis_info_text)) {
					check = true ;
					SA.assertTrue(check, "Self Diagnosis Welcome page validated successfully");
					test.log(LogStatus.INFO,"Self Diagnosis Welcome page validated successfully");
					test.log(LogStatus.PASS,"Test case status is Passed");
				}else {
					test.log(LogStatus.INFO,"Failed to validate Self Diagnosis Welcome page");
					test.log(LogStatus.FAIL,"Test case status is Failed");
					SA.fail();
				}
			}
			clickBackButton(2);
		} catch (Exception e) {
			APP_LOGS.info("Element not Found");
			e.printStackTrace();
		}
		SA.assertAll();
	}

	public void navigate_to_module(WebElement element,String text) {
		try {
			minWait();
			clickBtn(SCOUT_Locators_SonimCare.Self_diagnosis_Test);
			minWait();
			clickBtn(SCOUT_Locators_SonimCare.Continue);
			minWait();
			clickBtn(SCOUT_Locators_SonimCare.Continue);
			minWait();
			scrollText(text);
			minWait();
			clickBtn(element);
		} catch (Exception e) {
			APP_LOGS.info("Element not Found");
			e.printStackTrace();
		}
	}

	public void navigate_to_storageInfo(String text) {
		try {
			minWait();
			clickBtn(SCOUT_Locators_SonimCare.Self_diagnosis_Test);
			minWait();
			clickBtn(SCOUT_Locators_SonimCare.Continue);
			minWait();
			clickBtn(SCOUT_Locators_SonimCare.Continue);
			minWait();
			scrollText(text);
			minWait();
			for(int i=0;i<3;i++) {
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
			}
			aDriver.pressKeyCode(AndroidKeyCode.ENTER);
		} catch (Exception e) {
			APP_LOGS.info("Element not Found");
			e.printStackTrace();
		}
	}

	public void testWifiModule_and_validate() {
		SoftAssert SA = new SoftAssert();
		try {
			minWait();
			if(isElementExist(SCOUT_Locators_SonimCare.module_welcome_page)) {
				clickBtn(SCOUT_Locators_SonimCare.Continue);
				minWait();
				clickBtn(SCOUT_Locators_SonimCare.Scan_for_more_networks);
				customWait(10000);

				takeScreenShotForOcrPngFormat("SonimCare");
				Thread.sleep(2000);
				enlargeImage("src/test/resources/OCR_FILES/SonimCare.png", "src/test/resources/OCR_FILES/SonimCare.png");
				minWait();
				OCR.my_main.validate_Using_OCR("SonimCare.png");
				customWait(2000);
				Boolean value = searchStringOCR("Scan Results", "ocr");
				if(value) {
					select_yes_Btn();
					//	clickBtn(SCOUT_Locators_SonimCare.yes_Btn);
					if(isElementExist(SCOUT_Locators_SonimCare.Self_diagnosis)) {
						check = true ;
						SA.assertTrue(check, "Wi-Fi module validated successfully");
						test.log(LogStatus.INFO,"Wi-Fi module validated successfully");
						test.log(LogStatus.PASS,"Test case status is Passed");
					}else {
						test.log(LogStatus.INFO,"Failed to validate Wi-Fi module");
						test.log(LogStatus.FAIL,"Test case status is Failed");
						clearRecentApps();
						SA.fail();
					}
					clickBackButton(1);
					check_self_diagnosis_pop_up();
				}   
			}
		} catch (Exception e) {
			APP_LOGS.info("Element not Found");
			e.printStackTrace();
		}
		SA.assertAll();
	}

	public void testBluetoothModule_and_validate() {
		SoftAssert SA = new SoftAssert();
		try {
			minWait();
			if(isElementExist(SCOUT_Locators_SonimCare.module_welcome_page)) {
				clickBtn(SCOUT_Locators_SonimCare.Continue);
				minWait();
				//	clickBtn(SCOUT_Locators_SonimCare.Search_Bluetooth);
				aDriver.pressKeyCode(AndroidKeyCode.ENTER);
				customWait(10000);

				takeScreenShotForOcrPngFormat("SonimCare");
				Thread.sleep(2000);
				enlargeImage("src/test/resources/OCR_FILES/SonimCare.png", "src/test/resources/OCR_FILES/SonimCare.png");
				minWait();
				OCR.my_main.validate_Using_OCR("SonimCare.png");
				customWait(2000);
				Boolean value1 = searchStringOCR("Name", "ocr");
				Boolean value2 = searchStringOCR("Address", "ocr");
				if(value1 && value2) {
					select_yes_Btn();
					if(isElementExist(SCOUT_Locators_SonimCare.Self_diagnosis)) {
						check = true ;
						SA.assertTrue(check, "Bluetooth module validated successfully");
						test.log(LogStatus.INFO,"Bluetooth module validated successfully");
						test.log(LogStatus.PASS,"Test case status is Passed");
					}else {
						test.log(LogStatus.INFO,"Failed to validate Bluetooth module");
						test.log(LogStatus.FAIL,"Test case status is Failed");
						clearRecentApps();
						SA.fail();
					}
					clickBackButton(1);
					check_self_diagnosis_pop_up();
				}   
			}
		} catch (Exception e) {
			APP_LOGS.info("Element not Found");
			e.printStackTrace();
		}
		SA.assertAll();
	}

	public void select_yes_Btn() {
		try {
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_RIGHT);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_LEFT);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
			minWait();
		} catch (Exception e) {
			APP_LOGS.info("Element not Found");
			e.printStackTrace();
		}
	}

	public void select_no_Btn() {
		try {
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_RIGHT);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
			minWait();
		} catch (Exception e) {
			APP_LOGS.info("Element not Found");
			e.printStackTrace();
		}
	}

	public void test_BackCamera_Module_and_validate() {
		SoftAssert SA = new SoftAssert();
		try {
			minWait();
			if(isElementExist(SCOUT_Locators_SonimCare.module_welcome_page)) {
				clickBtn(SCOUT_Locators_SonimCare.Continue);
				customWait(2000);
				//	clickBtn(SCOUT_Locators_SonimCare.capture_btn);
				aDriver.pressKeyCode(AndroidKeyCode.ENTER);
				minWait();
				if(isElementExist(SCOUT_Locators_SonimCare.add_image)) {
					clickBtn(SCOUT_Locators_SonimCare.ok_Btn);
					customWait(3000);
					if(isElementExist(SCOUT_Locators_SonimCare.captured_image)) {
						select_yes_Btn();
						if(isElementExist(SCOUT_Locators_SonimCare.Self_diagnosis)) {
							check = true ;
							SA.assertTrue(check, "Back Camera module validated successfully");
							test.log(LogStatus.INFO,"Back Camera module validated successfully");
						}else {
							test.log(LogStatus.INFO,"Failed to validate Back Camera module");
							clearRecentApps();
							SA.fail();
						}
						clickBackButton(1);
						check_self_diagnosis_pop_up();
					}else {
						test.log(LogStatus.ERROR, "Add Image pop up not available");
						test.log(LogStatus.FAIL, "Test case status is Failed");
						SA.fail();
					}
				}   
			}
		} catch (Exception e) {
			APP_LOGS.info("Element not Found");
			e.printStackTrace();
		}
		SA.assertAll();
	}

	public void validate_add_image_cancel_functionality() {
		SoftAssert SA = new SoftAssert();
		try {
			minWait();
			clickBtn(SCOUT_Locators_SonimCare.Continue);
			customWait(2000);
			aDriver.pressKeyCode(AndroidKeyCode.ENTER);
			if(isElementExist(SCOUT_Locators_SonimCare.add_image)) {
				minWait();
				clickBtn(SCOUT_Locators_SonimCare.cancel_Btn);
				customWait(3000);
				if(isElementExist(SCOUT_Locators_SonimCare.module_welcome_page)) {
					check = true ;
					SA.assertTrue(check, "Back Camera module with Add Image cancel button functionlity validated successfully");
					test.log(LogStatus.INFO,"Back Camera module with Add Image cancel button functionlity validated successfully");
					test.log(LogStatus.PASS,"Test case status is Passed");
				}else{
					test.log(LogStatus.INFO,"Failed to validate Back Camera module with Add Image cancel button functionality");
					test.log(LogStatus.FAIL,"Test case status is Failed");
					clearRecentApps();
					SA.fail();
				}
				clickBackButton(2);
				check_self_diagnosis_pop_up();
			}
		} catch (Exception e) {
			APP_LOGS.info("Element not Found");
			e.printStackTrace();
		}
	}

	public void test_flashlight_Module_and_validate() {
		SoftAssert SA = new SoftAssert();
		try {
			minWait();
			if(isElementExist(SCOUT_Locators_SonimCare.module_welcome_page)) {
				clickBtn(SCOUT_Locators_SonimCare.Continue);
				minWait();
				clickBtn(SCOUT_Locators_SonimCare.flashlight_off_mode);
				customWait(5000);
				if(isElementExist(SCOUT_Locators_SonimCare.flashlight_on_mode)) {
					customWait(2000);
					clickBtn(SCOUT_Locators_SonimCare.flashlight_on_mode);
					customWait(3000);
					select_yes_Btn();
					if(isElementExist(SCOUT_Locators_SonimCare.Self_diagnosis)) {
						check = true ;
						SA.assertTrue(check, "FlashLight module validated successfully");
						test.log(LogStatus.INFO,"FlashLight module validated successfully");
						test.log(LogStatus.PASS,"Test case status is Passed");
					}else {
						test.log(LogStatus.INFO,"Failed to validate FlashLight module");
						test.log(LogStatus.FAIL,"Test case status is Failed");
						clearRecentApps();
						SA.fail();
					}
					clickBackButton(1);
					check_self_diagnosis_pop_up();
				}   
			}
		} catch (Exception e) {
			APP_LOGS.info("Element not Found");
			e.printStackTrace();
		}
		SA.assertAll();
	}

	public void test_display_Module_and_validate() {
		SoftAssert SA = new SoftAssert();
		try {
			minWait();
			if(isElementExist(SCOUT_Locators_SonimCare.module_welcome_page)) {
				clickBtn(SCOUT_Locators_SonimCare.Continue);
				minWait();
				if(isElementExist(SCOUT_Locators_SonimCare.display_info_page)) {
					for(int i=0;i<6;i++) {
						aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
						minWait();
					}
					if(isElementExist(SCOUT_Locators_SonimCare.display_confirmation_msg)) {
						customWait(3000);
						select_yes_Btn();
						if(isElementExist(SCOUT_Locators_SonimCare.Self_diagnosis)) {
							check = true ;
							SA.assertTrue(check, "Display module validated successfully");
							test.log(LogStatus.INFO,"Display module validated successfully");
							test.log(LogStatus.PASS,"Test case status is Passed");
						}else {
							test.log(LogStatus.INFO,"Failed to validate Display module");
							test.log(LogStatus.FAIL,"Test case status is Failed");
							clearRecentApps();
							SA.fail();
						}
						clickBackButton(1);
						check_self_diagnosis_pop_up();
					}else {
						test.log(LogStatus.INFO,"Display Test confirmation message not available");
						test.log(LogStatus.FAIL,"Test case status is Failed");
						clearRecentApps();
						SA.fail();
					}
				}   
			}
		} catch (Exception e) {
			APP_LOGS.info("Element not Found");
			e.printStackTrace();
		}
		SA.assertAll();
	}

	public void test_keypad_Module_and_validate() {
		SoftAssert SA = new SoftAssert();
		try {
			minWait();
			if(isElementExist(SCOUT_Locators_SonimCare.module_welcome_page)) {
				clickBtn(SCOUT_Locators_SonimCare.Continue);
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_MENU);
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_HOME);
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_UP);
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_LEFT);
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_RIGHT);
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_CLEAR);
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_CALL);
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_ENDCALL);
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_1);
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_2);
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_3);
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_4);
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_5);
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_6);
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_7);
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_8);
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_9);
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_0);
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_STAR);
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_POUND);
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_VOLUME_UP);
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_VOLUME_DOWN);
				customWait(5000);
				select_yes_Btn();
				if(isElementExist(SCOUT_Locators_SonimCare.Self_diagnosis)) {
					check = true ;
					SA.assertTrue(check, "FlashLight module validated successfully");
					test.log(LogStatus.INFO,"FlashLight module validated successfully");
					test.log(LogStatus.PASS,"Test case status is Passed");
				}else {
					test.log(LogStatus.INFO,"Failed to validate FlashLight module");
					test.log(LogStatus.FAIL,"Test case status is Failed");
					clearRecentApps();
					SA.fail();
				}
			}   
		} catch (Exception e) {
			APP_LOGS.info("Element not Found");
			e.printStackTrace();
		}
		SA.assertAll();
	}

	public void test_Battery_Module_and_validate() {
		SoftAssert SA = new SoftAssert();
		try {
			minWait();
			if(isElementExist(SCOUT_Locators_SonimCare.module_welcome_page)) {
				clickBtn(SCOUT_Locators_SonimCare.Continue);
				minWait();
				if(isElementExist(SCOUT_Locators_SonimCare.battery_info)) {
					customWait(3000);
					select_yes_Btn();
					if(isElementExist(SCOUT_Locators_SonimCare.Self_diagnosis)) {
						check = true ;
						SA.assertTrue(check, "Battery module validated successfully");
						test.log(LogStatus.INFO,"Battery module validated successfully");
						test.log(LogStatus.PASS,"Test case status is Passed");
					}else {
						test.log(LogStatus.INFO,"Failed to validate Battery module");
						test.log(LogStatus.FAIL,"Test case status is Failed");
						clearRecentApps();
						SA.fail();
					}
					clickBackButton(1);
					check_self_diagnosis_pop_up();
				}else {
					test.log(LogStatus.INFO,"Battery Info page not available");
					test.log(LogStatus.FAIL,"Test case status is Failed");
					clearRecentApps();
					SA.fail();
				}
			}
		} catch (Exception e) {
			APP_LOGS.info("Element not Found");
			e.printStackTrace();
		}
		SA.assertAll();
	}

	public void test_vibration_Module_and_validate() {
		SoftAssert SA = new SoftAssert();
		try {
			minWait();
			if(isElementExist(SCOUT_Locators_SonimCare.module_welcome_page)) {
				clickBtn(SCOUT_Locators_SonimCare.Continue);
				minWait();
				clickBtn(SCOUT_Locators_SonimCare.start_vibrate);
				customWait(2000);
				if(isElementExist(SCOUT_Locators_SonimCare.stop_vibrate)) {
					clickBtn(SCOUT_Locators_SonimCare.stop_vibrate);
					customWait(3000);
					select_yes_Btn();
					if(isElementExist(SCOUT_Locators_SonimCare.Self_diagnosis)) {
						check = true ;
						SA.assertTrue(check, "Vibration module validated successfully");
						test.log(LogStatus.INFO,"Vibration module validated successfully");
						test.log(LogStatus.PASS,"Test case status is Passed");
					}else {
						test.log(LogStatus.INFO,"Failed to validate Vibration module");
						test.log(LogStatus.FAIL,"Test case status is Failed");
						clearRecentApps();
						SA.fail();
					}
					clickBackButton(1);
					check_self_diagnosis_pop_up();
				}   
			}
		} catch (Exception e) {
			APP_LOGS.info("Element not Found");
			e.printStackTrace();
		}
		SA.assertAll();
	}

	public void check_self_diagnosis_pop_up() {
		try {
			if(isElementExist(SCOUT_Locators_SonimCare.self_diagnosis_pop_up)) {
				clickBtn(SCOUT_Locators_SonimCare.no_button);
				clickBackButton(2);
			}else {
				clickBackButton(3);
			}
		} catch (Exception e) {
			APP_LOGS.info("Element not Found");
			e.printStackTrace();
		}
	}

	public void test_clear_cache_memory_Module_and_validate() {
		SoftAssert SA = new SoftAssert();
		try {
			minWait();
			if(isElementExist(SCOUT_Locators_SonimCare.module_welcome_page)) {
				clickBtn(SCOUT_Locators_SonimCare.Continue);
				customWait(2000);
				scrollText("Cached data");
				minWait();
				clickBtn(SCOUT_Locators_SonimCare.cached_data);
				minWait();
				if(isElementExist(SCOUT_Locators_SonimCare.clear_cached_data)) {
					minWait();
					clickBtn(SCOUT_Locators_SonimCare.ok_Btn);
					customWait(5000);
					if(isElementExist(SCOUT_Locators_SonimCare.cleared_cached_data)) {
						clickBackButton(1);
						if(isElementExist(SCOUT_Locators_SonimCare.Self_diagnosis)) {
							check = true ;
							SA.assertTrue(check, "Cleared cache memory and validated successfully");
							test.log(LogStatus.INFO,"Cleared cache memory and module validated successfully");
							test.log(LogStatus.PASS,"Test case status is Passed");
						}else {
							test.log(LogStatus.INFO,"Failed to validate Clear cache memory module");
							test.log(LogStatus.FAIL,"Test case status is Failed");
							clearRecentApps();
							SA.fail();
						}
						clickBackButton(1);
						check_self_diagnosis_pop_up();
					}else {
						test.log(LogStatus.INFO,"Failed to clear cache memory");
						test.log(LogStatus.FAIL,"Test case status is Failed");
						clearRecentApps();
						SA.fail();
					}
				}else {
					test.log(LogStatus.INFO,"Clear cache option not available");
					test.log(LogStatus.FAIL,"Test case status is Failed");
					clearRecentApps();
					SA.fail();
				}
			}
		} catch (Exception e) {
			APP_LOGS.info("Element not Found");
			e.printStackTrace();
		}
		SA.assertAll();
	}

	public void test_Storage_Info_Module_and_validate() {
		SoftAssert SA = new SoftAssert();
		try {
			minWait();
			if(isElementExist(SCOUT_Locators_SonimCare.module_welcome_page)) {
				clickBtn(SCOUT_Locators_SonimCare.Continue);
				customWait(5000);
				if(isElementExist(SCOUT_Locators_SonimCare.storage_info_progress)) {
					customWait(2000);
					clickBackButton(1);
					if(isElementExist(SCOUT_Locators_SonimCare.Self_diagnosis)) {
						check = true ;
						SA.assertTrue(check, "Storage Info module validated successfully");
						test.log(LogStatus.INFO,"Storage Info module validated successfully");
						test.log(LogStatus.PASS,"Test case status is Passed");
					}else {
						test.log(LogStatus.INFO,"Failed to validate Storage Info module");
						test.log(LogStatus.FAIL,"Test case status is Failed");
						clearRecentApps();
						SA.fail();
					}
					clickBackButton(1);
					check_self_diagnosis_pop_up();
				}else {
					test.log(LogStatus.INFO,"Storage Info not available");
					test.log(LogStatus.FAIL,"Test case status is Failed");
					clearRecentApps();
					SA.fail();
				}
			}
		} catch (Exception e) {
			APP_LOGS.info("Element not Found");
			e.printStackTrace();
		}
		SA.assertAll();
	}

	public void validate_wifi_no_option() {
		SoftAssert SA = new SoftAssert();
		try {
			minWait();
			if(isElementExist(SCOUT_Locators_SonimCare.module_welcome_page)) {
				minWait();
				clickBtn(SCOUT_Locators_SonimCare.Continue);
				minWait();
				clickBtn(SCOUT_Locators_SonimCare.Scan_for_more_networks);
				customWait(10000);

				takeScreenShotForOcrPngFormat("SonimCare");
				Thread.sleep(2000);
				enlargeImage("src/test/resources/OCR_FILES/SonimCare.png", "src/test/resources/OCR_FILES/SonimCare.png");
				minWait();
				OCR.my_main.validate_Using_OCR("SonimCare.png");
				customWait(2000);
				Boolean value = searchStringOCR("Scan Results", "ocr");
				if(value) {
					select_no_Btn();
					if(isElementExist(SCOUT_Locators_SonimCare.no_option_confirmation_pop_up)) {
						clickBtn(SCOUT_Locators_SonimCare.ok_Btn);
						check = true ;
						SA.assertTrue(check, "Wi-Fi module with No option validated successfully");
						test.log(LogStatus.INFO,"Wi-Fi module with No option validated successfully");
						test.log(LogStatus.PASS,"Test case status is Passed");
					}else {
						test.log(LogStatus.INFO,"Failed to validate Wi-Fi module with No Option");
						test.log(LogStatus.FAIL,"Test case status is Failed");
						clearRecentApps();
						SA.fail();
					}
					clickBackButton(1);
					check_self_diagnosis_pop_up();
				}   
			}
		} catch (Exception e) {
			APP_LOGS.info("Element not Found");
			e.printStackTrace();
		}
		SA.assertAll();
	}

	public void validate_bluetooth_no_option() {
		SoftAssert SA = new SoftAssert();
		try {
			minWait();
			if(isElementExist(SCOUT_Locators_SonimCare.module_welcome_page)) {
				clickBtn(SCOUT_Locators_SonimCare.Continue);
				minWait();
				//	clickBtn(SCOUT_Locators_SonimCare.Search_Bluetooth);
				aDriver.pressKeyCode(AndroidKeyCode.ENTER);
				customWait(10000);

				takeScreenShotForOcrPngFormat("SonimCare");
				Thread.sleep(2000);
				enlargeImage("src/test/resources/OCR_FILES/SonimCare.png", "src/test/resources/OCR_FILES/SonimCare.png");
				minWait();
				OCR.my_main.validate_Using_OCR("SonimCare.png");
				customWait(2000);
				Boolean value1 = searchStringOCR("Name", "ocr");
				Boolean value2 = searchStringOCR("Address", "ocr");
				if(value1 && value2) {
					select_no_Btn();
					if(isElementExist(SCOUT_Locators_SonimCare.no_option_confirmation_pop_up)) {
						clickBtn(SCOUT_Locators_SonimCare.ok_Btn);
						check = true ;
						SA.assertTrue(check, "Bluetooth module with No option validated successfully");
						test.log(LogStatus.INFO,"Bluetooth module with No option validated successfully");
						test.log(LogStatus.PASS,"Test case status is Passed");
					}else {
						test.log(LogStatus.INFO,"Failed to validate Bluetooth module with No Option");
						test.log(LogStatus.FAIL,"Test case status is Failed");
						clearRecentApps();
						SA.fail();
					}
					clickBackButton(1);
					check_self_diagnosis_pop_up();
				}   
			}
		} catch (Exception e) {
			APP_LOGS.info("Element not Found");
			e.printStackTrace();
		}
		SA.assertAll();
	}

	public void validate_back_camera_no_option() {
		SoftAssert SA = new SoftAssert();
		try {
			minWait();
			if(isElementExist(SCOUT_Locators_SonimCare.module_welcome_page)) {
				clickBtn(SCOUT_Locators_SonimCare.Continue);
				customWait(2000);
				//	clickBtn(SCOUT_Locators_SonimCare.capture_btn);
				aDriver.pressKeyCode(AndroidKeyCode.ENTER);
				minWait();
				if(isElementExist(SCOUT_Locators_SonimCare.add_image)) {
					clickBtn(SCOUT_Locators_SonimCare.ok_Btn);
					customWait(3000);
					if(isElementExist(SCOUT_Locators_SonimCare.captured_image)) {
						select_no_Btn();
						if(isElementExist(SCOUT_Locators_SonimCare.no_option_confirmation_pop_up)) {
							clickBtn(SCOUT_Locators_SonimCare.ok_Btn);
							check = true ;
							SA.assertTrue(check, "Back Camera module with No option validated successfully");
							test.log(LogStatus.INFO,"Back Camera module with No option validated successfully");
							test.log(LogStatus.PASS,"Test case status is Passed");
						}else {
							test.log(LogStatus.INFO,"Failed to validate Back Camera module with No Option");
							test.log(LogStatus.FAIL,"Test case status is Failed");
							clearRecentApps();
							SA.fail();
						}
						clickBackButton(1);
						check_self_diagnosis_pop_up();
					}else {
						test.log(LogStatus.ERROR, "Add Image pop up not available");
						test.log(LogStatus.FAIL, "Test case status is Failed");
						SA.fail();
					}
				}   
			}
		} catch (Exception e) {
			APP_LOGS.info("Element not Found");
			e.printStackTrace();
		}
		SA.assertAll();
	}

	public void validate_flashlight_no_option() {
		SoftAssert SA = new SoftAssert();
		try {
			minWait();
			if(isElementExist(SCOUT_Locators_SonimCare.module_welcome_page)) {
				clickBtn(SCOUT_Locators_SonimCare.Continue);
				minWait();
				clickBtn(SCOUT_Locators_SonimCare.flashlight_off_mode);
				customWait(5000);
				if(isElementExist(SCOUT_Locators_SonimCare.flashlight_on_mode)) {
					customWait(2000);
					clickBtn(SCOUT_Locators_SonimCare.flashlight_on_mode);
					customWait(3000);
					select_no_Btn();
					if(isElementExist(SCOUT_Locators_SonimCare.no_option_confirmation_pop_up)) {
						clickBtn(SCOUT_Locators_SonimCare.ok_Btn);
						check = true ;
						SA.assertTrue(check, "Flashlight module with No option validated successfully");
						test.log(LogStatus.INFO,"Flashlight module with No option validated successfully");
						test.log(LogStatus.PASS,"Test case status is Passed");
					}else {
						test.log(LogStatus.INFO,"Failed to validate Flashlight module with No Option");
						test.log(LogStatus.FAIL,"Test case status is Failed");
						clearRecentApps();
						SA.fail();
					}
					clickBackButton(1);
					check_self_diagnosis_pop_up();
				}
			}   
		} catch (Exception e) {
			APP_LOGS.info("Element not Found");
			e.printStackTrace();
		}
		SA.assertAll();
	}

	public void validate_display_no_option() {
		SoftAssert SA = new SoftAssert();
		try {
			minWait();
			if(isElementExist(SCOUT_Locators_SonimCare.module_welcome_page)) {
				clickBtn(SCOUT_Locators_SonimCare.Continue);
				minWait();
				if(isElementExist(SCOUT_Locators_SonimCare.display_info_page)) {
					for(int i=0;i<6;i++) {
						aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
						minWait();
					}
					if(isElementExist(SCOUT_Locators_SonimCare.display_confirmation_msg)) {
						customWait(3000);
						select_no_Btn();
						if(isElementExist(SCOUT_Locators_SonimCare.no_option_confirmation_pop_up)) {
							clickBtn(SCOUT_Locators_SonimCare.ok_Btn);
							check = true ;
							SA.assertTrue(check, "Display module with No option validated successfully");
							test.log(LogStatus.INFO,"Display module with No option validated successfully");
							test.log(LogStatus.PASS,"Test case status is Passed");
						}else {
							test.log(LogStatus.INFO,"Failed to validate Display module with No Option");
							test.log(LogStatus.FAIL,"Test case status is Failed");
							clearRecentApps();
							SA.fail();
						}
						clickBackButton(1);
						check_self_diagnosis_pop_up();
					}else {
						test.log(LogStatus.INFO,"Display Test confirmation message not available");
						test.log(LogStatus.FAIL,"Test case status is Failed");
						clearRecentApps();
						SA.fail();
					}
				}   
			}
		} catch (Exception e) {
			APP_LOGS.info("Element not Found");
			e.printStackTrace();
		}
		SA.assertAll();
	}

	public void validate_battery_no_option() {
		SoftAssert SA = new SoftAssert();
		try {
			minWait();
			if(isElementExist(SCOUT_Locators_SonimCare.module_welcome_page)) {
				clickBtn(SCOUT_Locators_SonimCare.Continue);
				minWait();
				if(isElementExist(SCOUT_Locators_SonimCare.battery_info)) {
					customWait(3000);
					select_no_Btn();
					if(isElementExist(SCOUT_Locators_SonimCare.no_option_confirmation_pop_up)) {
						clickBtn(SCOUT_Locators_SonimCare.ok_Btn);
						check = true ;
						SA.assertTrue(check, "Battery module with No option validated successfully");
						test.log(LogStatus.INFO,"Battery module with No option validated successfully");
						test.log(LogStatus.PASS,"Test case status is Passed");
					}else {
						test.log(LogStatus.INFO,"Failed to validate Battery module with No Option");
						test.log(LogStatus.FAIL,"Test case status is Failed");
						clearRecentApps();
						SA.fail();
					}
					clickBackButton(1);
					check_self_diagnosis_pop_up();
				}else {
					test.log(LogStatus.INFO,"Battery Info page not available");
					test.log(LogStatus.FAIL,"Test case status is Failed");
					clearRecentApps();
					SA.fail();
				}
			}
		} catch (Exception e) {
			APP_LOGS.info("Element not Found");
			e.printStackTrace();
		}
		SA.assertAll();
	}

	public void validate_vibration_no_option() {
		SoftAssert SA = new SoftAssert();
		try {
			minWait();
			if(isElementExist(SCOUT_Locators_SonimCare.module_welcome_page)) {
				clickBtn(SCOUT_Locators_SonimCare.Continue);
				minWait();
				clickBtn(SCOUT_Locators_SonimCare.start_vibrate);
				customWait(2000);
				if(isElementExist(SCOUT_Locators_SonimCare.stop_vibrate)) {
					clickBtn(SCOUT_Locators_SonimCare.stop_vibrate);
					customWait(3000);
					select_no_Btn();
					if(isElementExist(SCOUT_Locators_SonimCare.no_option_confirmation_pop_up)) {
						clickBtn(SCOUT_Locators_SonimCare.ok_Btn);
						check = true ;
						SA.assertTrue(check, "Vibration module with No option validated successfully");
						test.log(LogStatus.INFO,"Vibration module with No option validated successfully");
						test.log(LogStatus.PASS,"Test case status is Passed");
					}else {
						test.log(LogStatus.INFO,"Failed to validate Vibration module with No Option");
						test.log(LogStatus.FAIL,"Test case status is Failed");
						clearRecentApps();
						SA.fail();
					}
					clickBackButton(1);
					check_self_diagnosis_pop_up();
				}   
			}
		} catch (Exception e) {
			APP_LOGS.info("Element not Found");
			e.printStackTrace();
		}
		SA.assertAll();
	}

	public boolean scrollText(String text) {
		/*
		Method used to scroll to an element in the scrollable view
		 */

		boolean check = false;
		try {		
			String scrollable = "new UiScrollable(new UiSelector().scrollable(true))";
			String textElement = ".scrollIntoView(new UiSelector().text(\""+ text +"\"))";
			aDriver.findElementByAndroidUIAutomator(scrollable+textElement);
			APP_LOGS.info("Searched application is found sucessfully : ");
			check = true;
			return check;
		}
		catch(NoSuchElementException e) {
			return check;
		}
	}


	public void navigate_and_validate_battery_diagnosis_welcome_screen() {
		SoftAssert SA = new SoftAssert();
		try {
			minWait();
			clickBtn(SCOUT_Locators_SonimCare.Battery_Diagnosis_Test);
			minWait();
			if(isElementExist(SCOUT_Locators_SonimCare.Battery_Diagnosis_Test)) {
				if(isElementExist(SCOUT_Locators_SonimCare.battery_diagnosis_welcome_screen) && isElementExist(SCOUT_Locators_SonimCare.battery_diagnosis_info_text)) {
					check = true ;
					SA.assertTrue(check, "Battery Diagnosis Welcome page validated successfully");
					test.log(LogStatus.INFO,"Battery Diagnosis Welcome page validated successfully");
					test.log(LogStatus.PASS,"Test case status is Passed");
				}else {
					test.log(LogStatus.INFO,"Failed to validate Battery Diagnosis Welcome page");
					test.log(LogStatus.FAIL,"Test case status is Failed");
					SA.fail();
				}
			}
			clickBackButton(2);
		} catch (Exception e) {
			APP_LOGS.info("Element not Found");
			e.printStackTrace();
		}
		SA.assertAll();
	}

	public void validate_battery_information() {
		SoftAssert SA = new SoftAssert();
		try {
			clickBtn(SCOUT_Locators_SonimCare.Battery_Diagnosis_Test);
			minWait();
			clickBtn(SCOUT_Locators_SonimCare.Continue);
			minWait();
			if(isElementExist(SCOUT_Locators_SonimCare.Battery_information) && isElementExist(SCOUT_Locators_SonimCare.Battery_progress_bar) && isElementExist(SCOUT_Locators_SonimCare.Battery_health) && isElementExist(SCOUT_Locators_SonimCare.Battery_voltage) && isElementExist(SCOUT_Locators_SonimCare.Battery_temperature) && isElementExist(SCOUT_Locators_SonimCare.Battery_service_history)) {
				check = true ;
				SA.assertTrue(check, "Pressence of all battery information validated successfully");
				test.log(LogStatus.INFO,"Pressence of all battery information validated successfully");
				test.log(LogStatus.PASS,"Test case status is Passed");
			}else {
				test.log(LogStatus.INFO,"Failed to validate Battery Information");
				test.log(LogStatus.FAIL,"Test case status is Failed");
				SA.fail();
			}
			clickBackButton(3);
		} catch (Exception e) {
			APP_LOGS.info("Element not Found");
			e.printStackTrace();
		}
	}

	public void validateBatteryServiceHistory() {
		SoftAssert SA = new SoftAssert();
		try {
			clickBtn(SCOUT_Locators_SonimCare.Battery_Diagnosis_Test);
			minWait();
			clickBtn(SCOUT_Locators_SonimCare.Continue);
			minWait();
			clickBtn(SCOUT_Locators_SonimCare.Battery_service_history);
			minWait();
			if(isElementExist(SCOUT_Locators_SonimCare.month_in_battery_diagnosis) && isElementExist(SCOUT_Locators_SonimCare.calendar_in_battery_diagnosis)) {
				String total_charges = SCOUT_Locators_SonimCare.Total_no_of_charges.getText();
				String charge_cycle = SCOUT_Locators_SonimCare.charge_cycle.getText();
				String max_temp_string = SCOUT_Locators_SonimCare.Max_temp_recorded.getText();
				String recorded_temp = SCOUT_Locators_SonimCare.temp_record.getText();
				String total_charge_duration = SCOUT_Locators_SonimCare.Total_Charge_Duration.getText();
				String charge_duration = SCOUT_Locators_SonimCare.charge_duration.getText();
				test.log(LogStatus.INFO, total_charges+":"+ charge_cycle);
				test.log(LogStatus.INFO, max_temp_string+":"+ recorded_temp);
				test.log(LogStatus.INFO, total_charge_duration+":"+ charge_duration);
				test.log(LogStatus.INFO, "Battery Service history validated successfully");
				test.log(LogStatus.PASS, "Test case status is Passed");
				SA.assertTrue(check, "Battery Service history validated successfully");
			}else {
				test.log(LogStatus.INFO,"Failed to validate Battery Service history");
				test.log(LogStatus.FAIL,"Test case status is Failed");
				SA.fail();
			}
			clickBackButton(3);
		} catch (Exception e) {
			APP_LOGS.info("Element not Found");
			e.printStackTrace();
		}
	}

	public void validate_diagnosis_report() {
		SoftAssert SA = new SoftAssert();
		try {
			clickBtn(SCOUT_Locators_SonimCare.Diagnosis_Report);
			minWait();
			if(isElementExist(SCOUT_Locators_SonimCare.Diagnosis_Report) && isElementExist(SCOUT_Locators_SonimCare.diagnosis_report_list)) {
				boolean value1 = validate_diagnosis_report_no_option();
				minWait();
				clickBtn(SCOUT_Locators_SonimCare.Diagnosis_Report);
				boolean value2 = validate_diagnosis_report_yes_option();
				if(value1 && value2) {
					check = true ;
					SA.assertTrue(check, "Diagnosis Report validated successfully");
					test.log(LogStatus.INFO,"Diagnosis Report validated successfully");
					test.log(LogStatus.PASS,"Test case status is Passed");
				}else {
					test.log(LogStatus.INFO,"Failed to validate Diagnosis Report");
					test.log(LogStatus.FAIL,"Test case status is Failed");
					SA.fail();
				}
			}else {
				test.log(LogStatus.INFO,"Diagnosis Report not generated");
				test.log(LogStatus.FAIL,"Test case status is Failed");
				SA.fail();
			}
		} catch (Exception e) {
			APP_LOGS.info("Element not Found");
			e.printStackTrace();
		}
	}

	public boolean validate_diagnosis_report_no_option() {
		SoftAssert SA= new SoftAssert();
		try {
			for(int i=0;i<8;i++) {
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
			}
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_ENTER);
			minWait();
			if(isElementExist(SCOUT_Locators_SonimCare.SonimCare)) {
				check = true ;
				SA.assertTrue(check, "Diagnosis Report with No Option validated successfully");
				test.log(LogStatus.INFO,"Diagnosis Report with No Option validated successfully");
			}else {
				check = false;
				test.log(LogStatus.INFO,"Failed to validate Diagnosis Report with No Option");
				SA.fail();
			}
		} catch (Exception e) {
			APP_LOGS.info("Element not Found");
			e.printStackTrace();
		}
		return check;
	}

	public boolean validate_diagnosis_report_yes_option() {
		SoftAssert SA= new SoftAssert();
		try {
			for(int i=0;i<8;i++) {
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
			}
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_LEFT);
			minWait(); 
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_ENTER);
			minWait();
			customWait(10000);
			if(isElementExist(SCOUT_Locators_SonimCare.SonimCare)) {
				check = true ;
				SA.assertTrue(check, "Diagnosis Report with Yes Option validated successfully");
				test.log(LogStatus.INFO,"Diagnosis Report with Yes Option validated successfully");
			}else {
				check = false;
				test.log(LogStatus.INFO,"Failed to validate Diagnosis Report with Yes Option");
				SA.fail();
			}
		} catch (Exception e) {
			APP_LOGS.info("Element not Found");
			e.printStackTrace();
		}
		return check;
	}

	public void OFF_Switch(String switch_To_OFF) {
		try {
			minWait();
			aDriver.findElementByXPath("//*[contains(@text,'"+switch_To_OFF+"')]/../..//*[@text='ON']").click();
			minWait();
		} catch (Exception e) {
		}
	}
	
	
	public void disableCellularData() throws InterruptedException {
		/*
		 * disable cellular data
		 */
		try {
			launch_an_app("settings");
			clickBtn(SCOUT_Locators_SonimCare.networkAndInternet);
			minWait();
			if(isElementExist(SCOUT_Locators_SonimCare.MobileNetwrks_oreo)) {
				minWait();
				clickBtn(SCOUT_Locators_SonimCare.MobileNetwrks_oreo);
				APP_LOGS.info("Selected Mobile Networks");
				minWait();
				if(isElementExist(SCOUT_Locators_SonimCare.enabled_Data_Oreo)) {
					OFF_Switch("Mobile data");
				}
			//	disableFeature(SCOUT_Locators_SonimCare.enabled_Data_Oreo,SCOUT_Locators_SonimCare.disabled_Data_Oreo);
				minWait();
				clickBtn(SCOUT_Locators_SonimCare.OK_Btn);
				minWait();
			}
			else {
				clickBtn(SCOUT_Locators_SonimCare.dataUsageOptn);
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
				minWait();
				if(isElementExist(SCOUT_Locators_SonimCare.enabled_Data_Oreo)) {
					OFF_Switch("Mobile data");
				}
			//	disableFeature(SCOUT_Locators_SonimCare.cellular_DataON,SCOUT_Locators_SonimCare.cellular_DataOFF);
				minWait();
				clickBtn(SCOUT_Locators_SonimCare.OK_Btn);
				minWait();
			}
			clickBackButton(3);
		} catch (org.openqa.selenium.NoSuchElementException e) {
			APP_LOGS.info("No Element found.");
			Assert.fail();
			minWait();	
		}

	}
	
	public void ConnectSecuredWifi() throws InterruptedException, IOException {
		/*
		 * Connect wifi with entering passkey
		 */
		try {
			launch_an_app("settings");
			clickBtn(SCOUT_Locators_SonimCare.networkAndInternet);
			customWait(2000);
			navigateToWifiOption();
			minWait();
			if(isElementExist(SCOUT_Locators_SonimCare.disabled)) {
				enableFeature(SCOUT_Locators_SonimCare.enabled,SCOUT_Locators_SonimCare.disabled,SCOUT_Locators_SonimCare.switch_Title);
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);		
				customWait(3000);
			}		
			else {
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);		    	
			}
			customWait(3000);
			String getSummary = SCOUT_Locators_SonimCare.summaryWIFI_oreo.getText();
			System.out.println(getSummary);
			minWait();
			if(getSummary.contains("Not connected")) {
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
				customWait(2000);
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
				customWait(2000);
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
				customWait(4000);
				scrollToText("IDCSONWAP");
				//	selectIDcwifi();      
				minWait();
				if(isElementExist(SCOUT_Locators_SonimCare.wifi_IDC_ForgetBtn)) {
					clickBtn(SCOUT_Locators_SonimCare.wifi_IDC_ForgetBtn);
					minWait();
					scrollToText("IDCSONWAP");
					//selectIDcwifi();    
				}
				clickBtn(SCOUT_Locators_SonimCare.wifi_show_Pswd);
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_UP);
				customWait(1000);

				changeToNumberMode_SMS(SCOUT_Locators_SonimCare.wifi_IDC_Psswd);
				customWait(9000);
				if(isElementExist(SCOUT_Locators_SonimCare.wifi_IDC)) {
					Runtime.getRuntime().exec("adb -s "+ p_Id +" shell input text 1");
					minWait();
					Runtime.getRuntime().exec("adb -s "+ p_Id +" shell input text dcS");
					minWait();
					Runtime.getRuntime().exec("adb -s "+ p_Id +" shell input text 0");
					minWait();
					Runtime.getRuntime().exec("adb -s "+ p_Id +" shell input text n");
					minWait();
					Runtime.getRuntime().exec("adb -s "+ p_Id +" shell input text 1");
					minWait();
					Runtime.getRuntime().exec("adb -s "+ p_Id +" shell input text md");
					minWait();
					Runtime.getRuntime().exec("adb -s "+ p_Id+" shell input text 0");
					minWait();
					Runtime.getRuntime().exec("adb -s "+ p_Id +" shell input text tc");
					minWait();
					Runtime.getRuntime().exec("adb -s "+ p_Id +" shell input text 0");
					minWait();
					Runtime.getRuntime().exec("adb -s "+ p_Id +" shell input text MbLr");
					minWait();
				}

				minWait();
				customWait(1000);
				String psswrd = SCOUT_Locators_SonimCare.wifi_IDC_Psswd.getText();
				System.out.println(psswrd);
				clickBtn(SCOUT_Locators_SonimCare.wifi_IDC_ConnectBtn);
				APP_LOGS.info("IDC available secured wifi is connected");
				customWait(4000);		    	
			}		    
		} 
		catch (NoSuchElementException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			test.log(LogStatus.ERROR, "NO Such Element Found");
			Assert.fail();
		}
	}
	
	public void navigateToWifiOption() throws InterruptedException, IOException {
		/*
		 * navigate to wifi option
		 */	
		try {
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			test.setDescription("Failure Reason: Element not found");
			Assert.fail();
		}
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
	
	public void changeToNumberMode_SMS(WebElement element) throws InterruptedException {
		/*
		 * Change input field type to number
		 */
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
	}





}
