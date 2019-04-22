 package com.xp5S.util;

import static org.testng.Assert.assertTrue;
import static org.testng.Assert.fail;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Random;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.ScreenOrientation;
import org.testng.asserts.SoftAssert;
import com.relevantcodes.extentreports.LogStatus;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidKeyCode;
import junit.framework.Assert;

public class Calculator_Util extends BaseUtil{

	public boolean check = false;
	boolean check0 = false;
	boolean check1 = false;
	boolean check2 = false;
	boolean check3 = false;
	boolean check4 = false;
	boolean check5 = false;
	boolean check6 = false;
	boolean check7 = false;
	boolean check8 = false;
	boolean check9 = false;
	boolean check10= false;

	boolean check11 = false;
	boolean check12 = false;
	boolean check13 = false;
	boolean check14 = false;
	boolean check15 = false;
	boolean check16 = false;
	boolean check17 = false;
	boolean check18 = false;
	boolean check19 = false;
	boolean check20= false;
	boolean check21= false;
	//public static AndroidDriver<AndroidElement> aDriver;
	public static AndroidDriver<MobileElement> mdriver;

	// generation of random numbers
	public static int randonClickOnCalculatorKeyboard() throws InterruptedException  {
		Random rn = new Random();
		int answer = rn.nextInt(9) + 1;
		return answer;
	}


	public static int firstclick() throws InterruptedException{
		int first = randonClickOnCalculatorKeyboard();
		//Log.i(Constants.TAG, "First click" + String.valueOf(first));

		switch (first) {
		case 1:
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_1);
			break;
		case 2:
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_2);
			break;
		case 3:
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_3);
			break;
		case 4:
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_4);
			break;
		case 5:
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_5);
			break;
		case 6:
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_6);
			break;
		case 7:
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_7);
			break;
		case 8:
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_8);
			break;
		case 9:
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_9);
			break;
		case 10:
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_0);
			break;
		}return first;
	}

	public static int secondclick() throws InterruptedException{
		int second = randonClickOnCalculatorKeyboard();
		//Log.i(Constants.TAG, "First click" + String.valueOf(first));

		switch (second) {
		case 1:
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_1);
			break;
		case 2:
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_2);
			break;
		case 3:
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_3);
			break;
		case 4:
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_4);
			break;
		case 5:
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_5);
			break;
		case 6:
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_6);
			break;
		case 7:
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_7);
			break;
		case 8:
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_8);
			break;
		case 9:
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_9);
			break;
		case 10:
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_0);
			break;
		}return second;
	}

	public int gen() {
		Random r = new Random(System.currentTimeMillis());
		return 1000000000 + r.nextInt(2000000000);
	}



	public void launchCalculator() throws InterruptedException {
		launchApp("CALCULATOR_PACKAGE","CALCULATOR_ACTIVITY");
		minWait();
		APP_LOGS.info("Calculator Application Launched Succesfully");
	}


	public void validateCalculatorLaunch() throws InterruptedException {
		SoftAssert SA1= new SoftAssert();
		try {
			/*aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
			minWait();*/
			minWait();
			if(isElementExist(Locators_Calculator.calc_Dialog_Pad)){
				minWait();
				check = true;
				APP_LOGS.info("Calculator Launched validation is succesful.");
				//				Assert.assertTrue(check);
				SA1.assertTrue(check, "Calculator launched Successfully");
				
			}
			else {
				APP_LOGS.info("Calculator Launched validation is unsuccesful.");
				//				Assert.fail();
				SA1.fail("Calculator did not launched ");
			}
		}catch (org.openqa.selenium.NoSuchElementException e) {
			
		
			APP_LOGS.info("Farheen--Error: Element not found.");
			e.printStackTrace();
			SA1.fail();
		}
		
		SA1.assertAll();
		
	}




	public void basicOperationWithDecimalpt() throws InterruptedException, IOException {
		//This method perform operation with Decimal point
		try {
			//Decimal addition
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_0);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_STAR);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_4);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_UP);//add button
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_0);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_STAR);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_6);
			minWait();
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
			String currentNumberText1=Locators_Calculator.calc_Edit_text_field.getText();
			//		 System.out.println();
			if(currentNumberText1.equalsIgnoreCase("1")){
				check1 = true;
				APP_LOGS.info("Pass:Decimal addition of 2 numbers verified successfully.");}


			//Decimal subtraction
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_9);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_STAR);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_4);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);//subtraction button
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_7);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_STAR);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_4);
			minWait();
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
			String currentNumberText2=Locators_Calculator.calc_Edit_text_field.getText();
			//		 System.out.println();
			if(currentNumberText2.equalsIgnoreCase("2")){
				check2 = true;
				APP_LOGS.info("Pass:Decimal Subtraction of 2 numbers verified successfully.");
			}


			//Decimal multiplication
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_5);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_STAR);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_0);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_LEFT);//multiply button
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_0);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_STAR);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_6);
			minWait();
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
			String currentNumberText3=Locators_Calculator.calc_Edit_text_field.getText();
			//		 System.out.println();
			if(currentNumberText3.equalsIgnoreCase("3")){
				check3 = true;
				APP_LOGS.info("Pass:Decimal Multiplication of 2 numbers verified successfully.");
			}

			//Decimal division
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_9);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_STAR);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_6);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_RIGHT);//division button
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_1);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_STAR);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_2);
			minWait();
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
			String currentNumberText4=Locators_Calculator.calc_Edit_text_field.getText();
			//		 System.out.println();
			if(currentNumberText4.equalsIgnoreCase("8")){
				check4 = true;
				APP_LOGS.info("Pass:Decimal Division of 2 numbers verified successfully.");
			}

			if((check1)&&(check2)&&(check3)&&(check4)) {
				check= true;
				APP_LOGS.info("Decimal Functionality operations verified");
				Assert.assertTrue(check);
			}
			else{
				APP_LOGS.info(currentNumberText1+": "+check1+"\n"+currentNumberText2+": "+check2+"\n"+currentNumberText3+": "+check3+"\n"+currentNumberText4+": "+check4+
						"\n");
				Assert.fail();
			}	
		}catch (org.openqa.selenium.NoSuchElementException e) {
			APP_LOGS.info("Farheen--Error: Element not found.");
			e.printStackTrace();
		}	
	}



	public void validate_BasicOperationWithoutDecimalpt() throws InterruptedException {
		//This method perform operation with out Decimal point
		SoftAssert SA2 =new SoftAssert();
		try {
			//Addition
			minWait();
			int num1=firstclick();
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_UP);
			minWait();
			int num2=secondclick();
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
			int s=(num1+num2);			
			String currentNumberText1=Locators_Calculator.calc_Edit_text_field.getText();
			//	    System.out.println(currentNumberText1);
			APP_LOGS.info("Added 2 random number from calci is :"+ currentNumberText1);
			String sum1=String.valueOf(s);
			APP_LOGS.info("Added 2 random number is:"+ sum1);	
			//		System.out.println(sum1);
			if(currentNumberText1.equalsIgnoreCase(sum1)){
				check1 = true;
				APP_LOGS.info("Pass: Addition of 2 numbers verified successfully.");	
			}

			//Subtraction
			minWait();
			int num3=firstclick();
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
			minWait();
			int num4=secondclick();
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
			minWait();
			String currentNumberText2=Locators_Calculator.calc_Edit_text_field.getText();
			if(currentNumberText2.contains("minus"))
			{
				String ss = currentNumberText2.replace("minus", "-");
				currentNumberText2=ss;
			}
			System.out.println(currentNumberText2);
			int s1=(num3-num4);
			String sum2=String.valueOf(s1);
			APP_LOGS.info("Difference of 2 random number is:"+ sum2);	
			APP_LOGS.info("Difference of 2 random number from calci is :"+ currentNumberText2);
			//		System.out.println(sum2);
			if(currentNumberText2.contains(sum2)){
				check2 = true;
				APP_LOGS.info("Pass:Substraction of 2 numbers verified successfully.");
			}
			//multiplication
			minWait();
			int num5=firstclick();
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_LEFT);
			minWait();
			int num6=secondclick();
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
			minWait();
			String currentNumberText3=Locators_Calculator.calc_Edit_text_field.getText();
			int s3=(num5)*(num6);
			String sum3=String.valueOf(s3);
			APP_LOGS.info("Multiply 2 random number is:"+ sum3);	
			APP_LOGS.info("Multiply 2 random number from calci is :"+ currentNumberText3);
			//		System.out.println(sum3);
			if(currentNumberText3.equalsIgnoreCase(sum3)){
				check3 = true;
				APP_LOGS.info("Pass:Product of 2 numbers verified successfully.");
			}	

			//Division
			minWait();
			int num7=10;
			enterTextToInputField(Locators_Calculator.calc_Edit_text_field,"10/2");
			minWait();
			minWait();
			int num8=2;
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
			minWait();
			String currentNumberText4=Locators_Calculator.calc_Edit_text_field.getText();
			minWait();
			//		System.out.println(currentNumberText4);
			int s4=(num7/num8);
			String sum4=String.valueOf(s4);
			//		System.out.println(sum4);
			APP_LOGS.info("Division 2 random number is:"+ sum4);	
			APP_LOGS.info("Division 2 random number from calci is :"+ currentNumberText4);
			if(currentNumberText4.contains(sum4)){
				check4 = true;
				APP_LOGS.info("Pass:Division of 2 numbers verified successfully.");
			}


			if((check1)&&(check2)&&(check3)&&(check4)){
				check= true;
				APP_LOGS.info("Basic Functionality operations verified");
				//				Assert.assertTrue(check);
				SA2.assertTrue(check, "");
			}
			else{
				APP_LOGS.info(currentNumberText1+": "+check1+ "\n"+currentNumberText2+": "+check2+"\n"+currentNumberText3+": "+check3+"\n"+currentNumberText4+": "+check4+
						"\n");
				SA2.fail();
			}	
			customWait(3000);
		}catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.INFO, e.getAdditionalInformation());

			APP_LOGS.info("Farheen--Error: Element not found.");
			e.printStackTrace();
			SA2.fail();
		}	
		SA2.assertAll();
	}


	public void validate_BasicOperationWithZero() throws InterruptedException {
		SoftAssert SA3 = new SoftAssert();
		try {
			//Addition with zero
			int num1=firstclick();
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_UP);
			minWait();
			longpress(7);
			int num2=0;
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
			int s=(num1+num2);
			String currentNumberText1=Locators_Calculator.calc_Edit_text_field.getText();
			System.out.println(currentNumberText1);

			//		  Locators_Calculator.calc_Clear_btn.click();
			String sum1=String.valueOf(s);
			APP_LOGS.info("Addition of 2 random number is:"+ sum1);	
			APP_LOGS.info("Addition of 2 random number from calci is :"+ currentNumberText1);
			System.out.println(sum1);
			if(currentNumberText1.equalsIgnoreCase(sum1)){
				check1 = true;
				APP_LOGS.info("Pass: Addition of 2 numbers verified successfully.");	
			}

			//Subtraction with zero
			int num3=firstclick();
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
			minWait();
			longpress(7);
			int num4=0;
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
			String currentNumberText2=Locators_Calculator.calc_Edit_text_field.getText();
			//		  Locators_Calculator.calc_Clear_btn.click();
			int s1=(num3-num4);
			String sum2=String.valueOf(s1);
			APP_LOGS.info("Difference of 2 random number is:"+ sum2);	
			APP_LOGS.info("Difference of 2 random number from calci is :"+ currentNumberText2);
			if(currentNumberText2.equalsIgnoreCase(sum2)){
				check2 = true;
				APP_LOGS.info("Pass:Substraction of 2 numbers verified successfully.");
			}

			//multiplication with zero

			int num5=firstclick();
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_LEFT);
			minWait();
			longpress(7);
			int num6=0;
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
			String currentNumberText3=Locators_Calculator.calc_Edit_text_field.getText();
			int s3=(num5)*(num6);
			String sum3=String.valueOf(s3);
			APP_LOGS.info("Multiply of 2 random number is:"+ sum3);	
			APP_LOGS.info("Multiply of 2 random number from calci is :"+ currentNumberText3);
			if(currentNumberText3.equalsIgnoreCase(sum3)){
				check3 = true;
				APP_LOGS.info("Pass:Product of 2 numbers verified successfully.");
			}	

			//Division with zero
			String currentNumberText4="";
			double num7=firstclick();
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_RIGHT);
			minWait();
			longpress(7);
			double num8=0;
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
			currentNumberText4=Locators_Calculator.calc_Edit_text_field.getText();
			minWait();
			double s4=(num7/num8);
//			System.out.println(currentNumberText4);
			String sum4=String.valueOf(s4);
			APP_LOGS.info("Division of 2 random number is:"+ sum4);	
			APP_LOGS.info("Division of 2 random number from calci is :"+ currentNumberText4);
			if(currentNumberText4.equalsIgnoreCase(sum4)){
				check4 = true;
				APP_LOGS.info("Pass:Division of 2 numbers verified successfully.");
			}
			else if(sum4.equals("Infinity")){
				check4 = true;
				APP_LOGS.info("Pass:Division of 2 numbers verified successfully.");
			}

			if((check1)&&(check2)&&(check3)&&(check4)){
				check= true;
				APP_LOGS.info("Basic Functionality operations with Zero is verified");
				SA3.assertTrue(check, " ");
			}
			else{
				APP_LOGS.info(currentNumberText1+": "+check1+ "\n"+currentNumberText2+": "+check2+"\n"+currentNumberText3+": "+check3+"\n"+currentNumberText4+": "+check4+
						"\n");
				SA3.fail();
			}	
			customWait(3000);
		}catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.INFO, e.getAdditionalInformation());

			APP_LOGS.info("Farheen--Error: Element not found.");
			e.printStackTrace();
			SA3.fail();
		}	
		SA3.assertAll();
	}


	public void validateShortPressNum() throws InterruptedException{
		SoftAssert SA4 = new SoftAssert();
		try {
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_0);
			String Current_text0=Locators_Calculator.calc_Edit_text_field.getText();
			System.out.println(Current_text0);

			if (Current_text0.equals("0")) {
				check0 = true;
				APP_LOGS.info("Clicked on 0");
			}minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_CLEAR);


			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_1);
			String Current_text1=Locators_Calculator.calc_Edit_text_field.getText();
			System.out.println(Current_text1);

			if (Current_text1.equals("1")) {
				check1 = true;
				APP_LOGS.info("Clicked on 1");
			}minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_CLEAR);

			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_2);
			String Current_text2=Locators_Calculator.calc_Edit_text_field.getText();

			if (Current_text2.equals("2")) {
				check2 = true;
				APP_LOGS.info("Clicked on 2");
			}minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_CLEAR);

			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_3);
			String Current_text3=Locators_Calculator.calc_Edit_text_field.getText();

			if (Current_text3.equals("3")) {
				check3 = true;
				APP_LOGS.info("Clicked on 3");
			}
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_CLEAR);
			minWait();


			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_4);
			String Current_text4=Locators_Calculator.calc_Edit_text_field.getText();

			if (Current_text4.equals("4")) {
				check4 = true;
				APP_LOGS.info("Clicked on 4");
			}
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_CLEAR);

			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_5);
			String Current_text5=Locators_Calculator.calc_Edit_text_field.getText();

			if (Current_text5.equals("5")) {
				check5 = true;
				APP_LOGS.info("Clicked on 5");
			} 
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_CLEAR);


			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_6);
			String Current_text6=Locators_Calculator.calc_Edit_text_field.getText();

			if (Current_text6.equals("6")) {
				check6 = true;
				APP_LOGS.info("Clicked on 6");
			}
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_CLEAR);

			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_7);
			String Current_text7=Locators_Calculator.calc_Edit_text_field.getText();

			if (Current_text7.equals("7")) {
				check7 = true;
				APP_LOGS.info("Clicked on 7");
			}
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_CLEAR);

			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_8);
			String Current_text8=Locators_Calculator.calc_Edit_text_field.getText();

			if (Current_text8.equals("8")) {
				check8 = true;
				APP_LOGS.info("Clicked on 8");
			}
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_CLEAR);

			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_9);
			String Current_text9=Locators_Calculator.calc_Edit_text_field.getText();

			if (Current_text9.equals("9")) {
				check9 = true;
				APP_LOGS.info("Clicked on 9");
			}
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_CLEAR);
			minWait();
			if((check1)&&(check2)&&(check3)&&(check4)&&(check5)&&(check6)&&(check7)&&(check8)&&(check9))
			{
				check= true;
				APP_LOGS.info("Valid Search entries are displayed.");
				//				Assert.assertTrue(check);
				SA4.assertTrue(check, "");

			}
			else{
				APP_LOGS.info(Current_text0+": "+check0+ "\n"+Current_text1+": "+check1+"\n"+Current_text2+": "+check2+"\n"+Current_text3+": "+check3+
						"\n"+Current_text4+": "+check4+"\n"+Current_text5+": "+check5+"\n"+Current_text6+": "+check6+ "\n"+Current_text7+": "+check7+"\n"+Current_text8+": "
						+ ""+check8+"\n"+Current_text9+": "+check9+ "\n");
				//				Assert.fail();
				SA4.fail();
			}	
			minWait();
		}catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.INFO, e.getAdditionalInformation());

			APP_LOGS.info("Farheen--Error: Element not found.");
			e.printStackTrace();
			SA4.fail();
		}	
		SA4.assertAll();
	}

	public void validateShortPressAdvanceKey() throws InterruptedException {
		//Valiadte by short press of Advance Keys
		SoftAssert SA5= new SoftAssert();
		try {  
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_MENU);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_ENTER);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_UP);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_UP);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_LEFT);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_LEFT);
			minWait();

			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
			minWait();
			String Current_text10=Locators_Calculator.calc_Edit_text_field.getText();
			//		  System.out.println(Current_text10);
			if (Current_text10.contains("left")) {
				check10 = true;
				APP_LOGS.info("Clicked on (");
			}  minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_CLEAR);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_RIGHT);
			minWait(); 
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
			minWait();
			String Current_text11=Locators_Calculator.calc_Edit_text_field.getText();
			minWait();
			//		  System.out.println(Current_text11);
			if (Current_text11.contains("right")) {
				check11 = true;
				APP_LOGS.info("Clicked on )");
			}  minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_CLEAR);
			minWait();


			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_RIGHT);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
			minWait();
			String Current_text12=Locators_Calculator.calc_Edit_text_field.getText();
			//		  System.out.println(Current_text12);
			if (Current_text12.equalsIgnoreCase("natural logarithmarithmleft parenthesis")) {
				check12 = true;
				APP_LOGS.info("Clicked on natural logarithm");
			}
			minWait();for(int i=1; i<=3; i++) {
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_CLEAR);
				minWait();
			} 


			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
			minWait(); 
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
			minWait();
			String Current_text13=Locators_Calculator.calc_Edit_text_field.getText();
			//		  System.out.println(Current_text13);
			if (Current_text13.contains("sineleft")) {
				check13 = true;
				APP_LOGS.info("Clicked on Sine");
			}
			minWait();
			for(int i=1; i<=4; i++) {
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_CLEAR);
				minWait();
			}


			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_LEFT);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
			minWait();
			String Current_text14=Locators_Calculator.calc_Edit_text_field.getText();
			//		  System.out.println(Current_text14);
			if (Current_text14.contains("factorial")) {
				check14 = true;
				APP_LOGS.info("Clicked on factorial");
			}minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_CLEAR);
			minWait();


			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_LEFT);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
			minWait();
			String Current_text15=Locators_Calculator.calc_Edit_text_field.getText();
			//		  System.out.println(Current_text15);
			if (Current_text15.contains("power")) {
				check15 = true;
				APP_LOGS.info("Clicked on power");
			}
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_CLEAR);
			minWait();


			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
			minWait();
			String Current_text16=Locators_Calculator.calc_Edit_text_field.getText();
			//		  System.out.println(Current_text16);
			if (Current_text16.equalsIgnoreCase("pi")) {
				check16 = true;
				APP_LOGS.info("Clicked on pi");
			}
			minWait();
			minWait();


			if((check10)&&(check11)&&(check12))
			{  
				check= true;
				APP_LOGS.info("Valid Search entries are displayed.");
				//				Assert.assertTrue(check);
				SA5.assertTrue(check, " ");
			}else{
				APP_LOGS.info(Current_text10+": "+check10+ "\n"+Current_text11+": "+check11+"\n"+Current_text12+": "+check12+"\n"+Current_text13+": "+check13+
						"\n"+Current_text14+": "+check14+"\n"+Current_text15+": "+check15+"\n"+Current_text16+": "+check16+ "\n");
				//				Assert.fail();
				SA5.fail();
			}	
		}catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.INFO, e.getAdditionalInformation());

			APP_LOGS.info("Farheen--Error: Element not found.");
			e.printStackTrace();
			SA5.fail();
		}	
		SA5.assertAll();
	}



	public void validateLongPress() throws InterruptedException{
		//Validate by longPress of number keys
		SoftAssert SA6 = new SoftAssert();
		try {
			minWait();
			longpress(7);
			String Current_text0=Locators_Calculator.calc_Edit_text_field.getText();
			if (Current_text0.equals("0")) {
				check0 = true;
				APP_LOGS.info("Clicked on 0");
			}  minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_CLEAR);
			minWait();
			longpress(8);
			String Current_text1=Locators_Calculator.calc_Edit_text_field.getText();
			if (Current_text1.equals("1")) {
				check1 = true;
				APP_LOGS.info("Clicked on 1");
			}  minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_CLEAR);


			minWait();
			longpress(9);
			String Current_text2=Locators_Calculator.calc_Edit_text_field.getText();

			if (Current_text2.equals("2")) {
				check2 = true;
				APP_LOGS.info("Clicked on 2");
			}  minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_CLEAR);


			minWait();
			longpress(10);
			String Current_text3=Locators_Calculator.calc_Edit_text_field.getText();

			if (Current_text3.equals("3")) {
				check3 = true;
				APP_LOGS.info("Clicked on 3");
			}
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_CLEAR);

			minWait();
			longpress(11);
			String Current_text4=Locators_Calculator.calc_Edit_text_field.getText();

			if (Current_text4.equals("4")) {
				check4 = true;
				APP_LOGS.info("Clicked on 4");
			}
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_CLEAR);


			longpress(12);
			String Current_text5=Locators_Calculator.calc_Edit_text_field.getText();

			if (Current_text5.equals("5")) {
				check5 = true;
				APP_LOGS.info("Clicked on 5");
			} 
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_CLEAR);


			longpress(13);
			String Current_text6=Locators_Calculator.calc_Edit_text_field.getText();

			if (Current_text6.equals("6")) {
				check6 = true;
				APP_LOGS.info("Clicked on 6");
			}
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_CLEAR);


			longpress(14);
			String Current_text7=Locators_Calculator.calc_Edit_text_field.getText();

			if (Current_text7.equals("7")) {
				check7 = true;
				APP_LOGS.info("Clicked on 7");
			}
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_CLEAR);


			longpress(15);
			String Current_text8=Locators_Calculator.calc_Edit_text_field.getText();

			if (Current_text8.equals("8")) {
				check8 = true;
				APP_LOGS.info("Clicked on 8");
			}
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_CLEAR);


			longpress(16);
			String Current_text9=Locators_Calculator.calc_Edit_text_field.getText();
			if (Current_text9.equals("9")) {
				check9 = true;
				APP_LOGS.info("Clicked on 9");
			}
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_CLEAR);

			if((check1)&&(check2)&&(check3)&&(check4)){
				check= true;
				APP_LOGS.info("Valid Search entries are displayed.");
				//				Assert.assertTrue(check);
				SA6.assertTrue(check, " ");

			}else{
				APP_LOGS.info(Current_text0+": "+check0+ "\n"+Current_text1+": "+check1+"\n"+Current_text2+": "+check2+"\n"+Current_text3+": "+check3+
						"\n"+Current_text4+": "+check4+"\n"+Current_text5+": "+check5+"\n"+Current_text6+": "+check6+ "\n"+Current_text7+": "+check7+"\n"+Current_text8+": "
						+ ""+check8+"\n"+Current_text9+": "+check9+ "\n");
				//				Assert.fail();
				APP_LOGS.info("Farheen: Test case Failed");
				SA6.fail();
			}		
		}catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.INFO, e.getAdditionalInformation());

			APP_LOGS.info("Farheen--Error: Element not found.");
			e.printStackTrace();
			SA6.fail();
		}	
		SA6.assertAll();
	}
	
	public String convertDouble(String S1) {
		// Convert the whole num didgit to decimal point number
		String S2 ="";
		String currentNumberText1 ="";
		if(S1.contains("point")) {
			String ss= S1.replace("point", ".");			
			S1= ss;
			currentNumberText1 = S1;
		}
		else{					
			double n1 = Double.parseDouble(S1);
			currentNumberText1 = String.valueOf(n1);		
		}			  
		return currentNumberText1;	  
	}

	//validating calculated value from calci with 2 added, Subtraction, Multiply and Divide value Separately DecimalPoint Numbers

		public void validateBasicOperationWithDecimalPnt() throws InterruptedException {
			SoftAssert SA2 = new SoftAssert();
			try {
				//Decimal addition
			//validating calculated value from calci with added value
				minWait();
				int num1=firstclick();
				minWait();				
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_STAR);
				minWait();
				int num2=secondclick();		
				minWait();		  
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_UP);
				minWait();
			    minWait();
			    int num3=firstclick();
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_STAR);
				minWait();
				int num4=secondclick();			
			    aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
				String currentNumberText1_cal=Locators_Calculator.calc_Edit_text_field.getText();
	            String S1 = currentNumberText1_cal.replaceAll("[^a-zA-Z0-9]", "");
	            currentNumberText1_cal = S1;
				String currentNumberText1 = convertDouble(currentNumberText1_cal);
			
//	             Calulating with entered click values for validation
			
				String s1= String.valueOf(num1);
				String s2= String.valueOf(num2);
				String s3= String.valueOf(num3);
				String s4= String.valueOf(num4);
				minWait();
				String ss1=s1+'.'+s2;
				String ss2=s3+'.'+s4;
				double sum1 = Double.parseDouble(ss1);
				double sum2 = Double.parseDouble(ss2);
				double total_sum1 = sum1+sum2;
				minWait();
				
				BigDecimal bigDecimal = new BigDecimal(total_sum1);
		        BigDecimal roundedWithScale = bigDecimal.setScale(1, BigDecimal.ROUND_HALF_UP);        
		              
				String cal_Add = String.valueOf(roundedWithScale);
				String str2 =String.valueOf(cal_Add);
				String AddSum_1 = str2.substring(0, str2.indexOf(".")+0);
				String AddSum_ex= (str2.substring(str2.indexOf(".")).substring(1));	
				String AddSum_2 =AddSum_ex.substring(0,1);
				minWait();		
				APP_LOGS.info("Addition of 2 decimalpoint numbers is:"+ AddSum_1 +AddSum_2);	
				APP_LOGS.info("Addition of 2 decimalpoint numbers from calci is :"+ currentNumberText1);
				if(currentNumberText1.contains(AddSum_1)&&(currentNumberText1.contains(AddSum_2)))
				{
					check1 = true;
					APP_LOGS.info("Pass:Decimal addition of 2 numbers verified successfully.");
				}
			
				
	            //Subtraction operation
				minWait();
				int num5=firstclick();
				minWait();		
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_STAR);
				minWait();
				int num6=secondclick();		
				minWait();
				String text_num2=Locators_Calculator.calc_Edit_text_field.getText();
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
				minWait();
			    minWait();
			    int num7=firstclick();
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_STAR);
				minWait();
				int num8=secondclick();			
			    aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
				String currentNumberText2_cal=Locators_Calculator.calc_Edit_text_field.getText();
				 String S2 = currentNumberText2_cal.replaceAll("[^a-zA-Z0-9 ]", "");
		          currentNumberText2_cal = S2;
				if(currentNumberText2_cal.contains("minus"))
				{
					String ss = currentNumberText2_cal.replace("minus", "-");
					currentNumberText2_cal=ss;				
				}			 
				String currentNumberText2 = convertDouble(currentNumberText2_cal);
				
//				  Calulating with entered click values for validation
				
				String s5= String.valueOf(num5);
				String s6= String.valueOf(num6);
				String s7= String.valueOf(num7);
				String s8= String.valueOf(num8);
				minWait();
				String ss5=s5+'.'+s6;
				String ss6=s7+'.'+s8;			
				double sum5 = Double.parseDouble(ss5);
				double sum6 =Double.parseDouble(ss6);
				double total_sum5 = sum5-sum6;
				 bigDecimal = new BigDecimal(total_sum5);
				 roundedWithScale = bigDecimal.setScale(1, BigDecimal.ROUND_HALF_UP);        
		              
				String cal_sum = String.valueOf(roundedWithScale);
				minWait();
				String str3 =String.valueOf(cal_sum);
				String SubSum1 = str3.substring(0, str3.indexOf(".")+0);
				String SubSum2_ex =(str3.substring(str3.indexOf(".")).substring(1));	
				String SubSum2 =SubSum2_ex.substring(0,1);
				minWait();
				APP_LOGS.info("Difference of 2 decimalpoint numbers is:"+ SubSum1 + SubSum2);	
				APP_LOGS.info("Difference of 2 decimalpoint numbers from calci is :"+ currentNumberText2);

				if(currentNumberText2.contains(SubSum1)&&(currentNumberText2.contains(SubSum2))){
					check2 = true;
					APP_LOGS.info("Pass:Decimal subtraction of 2 numbers verified successfully.");
					}
				//Decimal multiplication
				minWait();
				int num9=firstclick();
				minWait();					
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_STAR);
				minWait();
				int num10=secondclick();		
				minWait();
				String text_num3=Locators_Calculator.calc_Edit_text_field.getText();
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_LEFT);
				minWait();
			    minWait();
			    int num11=firstclick();
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_STAR);
				minWait();		
				int num12=secondclick();			
			    aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
				String currentNumberText3_cal=Locators_Calculator.calc_Edit_text_field.getText();
			    String S3 = currentNumberText3_cal.replaceAll("[^a-zA-Z0-9]", "");
			    currentNumberText3_cal = S3;
				String currentNumberText3 = convertDouble(currentNumberText3_cal);
//				  Calulating with entered click values for validation
					
				String s9= String.valueOf(num9);
				String s10= String.valueOf(num10);
				String s11= String.valueOf(num11);
				String s12= String.valueOf(num12);
				minWait();
				String ss9=s9+'.'+s10;
				String ss10=s11+'.'+s12;
				double sum9 = Double.parseDouble(ss9);
				double sum10 =Double.parseDouble(ss10);
				double total_sum9 = sum9*sum10;
				bigDecimal = new BigDecimal(total_sum9);
		        roundedWithScale = bigDecimal.setScale(2, BigDecimal.ROUND_HALF_UP);        	              
				String cal_mul = String.valueOf(roundedWithScale);		
				minWait();		
				String str4 =String.valueOf(cal_mul);
				String MulSum1 = str4.substring(0, str4.indexOf(".")+0);
				String MulSum2_ex = (str4.substring(str4.indexOf(".")).substring(1));	
				String MulSum2 =MulSum2_ex.substring(0,1);
				minWait();
				APP_LOGS.info("Multiply 2 decimalpoint numbers is:"+ MulSum1 +  MulSum2);	
				APP_LOGS.info("Multiply of 2 decimalpoint numbers from calci is :"+ currentNumberText3);
				if(currentNumberText3.contains(MulSum1)&&(currentNumberText3.contains(MulSum2))){
					check3 = true;
					APP_LOGS.info("Pass:Decimal Multiplication of 2 numbers verified successfully.");
				}
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_CLEAR);


	        //    Division operation
				minWait();
				int num13=firstclick();
				minWait();					
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_STAR);
				minWait();
				int num14=secondclick();		
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_RIGHT);
				minWait();
			    minWait();
			    int num15=firstclick();
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_STAR);
				minWait();		
				int num16=secondclick();			
			    aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
				String currentNumberText4_cal=Locators_Calculator.calc_Edit_text_field.getText();
				 String S4 = currentNumberText4_cal.replaceAll("[^a-zA-Z0-9]", "");//Replace space
			    currentNumberText4_cal = S4;
				String currentNumberText4 = convertDouble(currentNumberText4_cal);

//	            Calulating with entered click values for validation
	    		
				String s13= String.valueOf(num13);
				String s14= String.valueOf(num14);
				String s15= String.valueOf(num15);
				String s16= String.valueOf(num16);
				minWait();
				String ss13=s13+'.'+s14;
				String ss14=s15+'.'+s16;
				double sum13 = Double.parseDouble(ss13);
				double sum14 =Double.parseDouble(ss14);
				double total_sum13 = (sum13/sum14);
				
			    bigDecimal = new BigDecimal(total_sum13);
				roundedWithScale = bigDecimal.setScale(2, BigDecimal.ROUND_HALF_UP);        	              
				String cal_div = String.valueOf(roundedWithScale);	
				minWait();			
				String str5 =String.valueOf(cal_div);
				String DivSum1 = str5.substring(0, str5.indexOf(".")+0);//Fetching num before point
				String exact = (str5.substring(str5.indexOf(".")).substring(1));	//Fetching num after point		           
				String DivSum2 =exact.substring(0,1);			
				minWait();
				APP_LOGS.info("Division 2 decimalpoint numbers is:"+ DivSum1 +  DivSum2);	
				APP_LOGS.info("Division of 2 decimalpoint numbers from calci is :"+ currentNumberText4);
				if(currentNumberText4.contains(DivSum1)&&(currentNumberText4.contains(DivSum2))){
					check4 = true;
					APP_LOGS.info("Pass:Decimal Division of 2 numbers verified successfully.");
				}
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_CLEAR);

				if((check1)&&(check2)&&(check3)&&(check4)) {
					check= true;
					APP_LOGS.info("Decimal point Functionality operations verified");
					Assert.assertTrue(check);
				}
				else{
					APP_LOGS.info(currentNumberText1+": "+check1+"\n"+currentNumberText2+": "+check2+"\n"+currentNumberText3+": "+check3+
							"\n"+currentNumberText4+": "+check4+"\n");
					Assert.fail();
				}	
			}
			catch (Exception e) {
				APP_LOGS.info("Farheen--Error: Element not found.");
				e.printStackTrace();
				SA2.fail();
			}	
			SA2.assertAll();
		}
	
	public void navigateTo_AdvancePanel() throws InterruptedException {
		try {
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_MENU);
			minWait();
			clickBtn(Locators_Calculator.advanced_Panel);
			minWait();
		} catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.INFO, e.getAdditionalInformation());

			e.printStackTrace();
		}		
	}

	public void click_Number(int number) throws InterruptedException{
		minWait();
		switch (number) {
		case 0:
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_0);
			break;
		case 1:
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_1);
			break;
		case 2:
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_2);
			break;
		case 3:
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_3);
			break;
		case 4:
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_4);
			break;
		case 5:
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_5);
			break;
		case 6:
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_6);
			break;
		case 7:
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_7);
			break;
		case 8:
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_8);
			break;
		case 9:
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_9);
			break;		
		}
	}
	
	public void click_Division() throws InterruptedException {
		minWait();
		aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_RIGHT);
		minWait();		
	}
	
	public void click_DPAD_Center()throws InterruptedException {
		minWait();
		aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
		minWait();		
	}	
	
	public void validate_InfiniteResult() throws InterruptedException {
		SoftAssert SA1 = new SoftAssert();
		try {
			minWait();
			String result = Locators_Calculator.calc_Edit_text_field.getText();
//		System.out.println(result);
			if(result.contains("∞")) {
				check = true;
				SA1.assertTrue(check, "Test Case Status is PASS");	
			} else {
				APP_LOGS.info("validate_InfiniteResult Method Failed");
				SA1.fail();			
			}
		} catch (org.openqa.selenium.NoSuchElementException  e) {
			// TODO Auto-generated catch block
			test.log(LogStatus.INFO, e.getAdditionalInformation());

			e.printStackTrace();
			SA1.fail();
		}
		SA1.assertAll();
	}
	
	public void clickOn_Equal() throws InterruptedException {
		try {
			minWait();
			clickBtn(Locators_Calculator.equal);
			minWait();
		} catch (org.openqa.selenium.NoSuchElementException  e) {
			test.log(LogStatus.INFO, e.getAdditionalInformation());

			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	public void click_OnPowerIcon() throws InterruptedException {
		
		try {
			minWait();
			clickBtn(Locators_Calculator.power);
			minWait();
		} catch (org.openqa.selenium.NoSuchElementException  e) {
			// TODO Auto-generated catch block
			test.log(LogStatus.INFO, e.getAdditionalInformation());

			e.printStackTrace();
		}
	}
	
	protected int i,j;
	
	public void validate_PowerOperation() throws InterruptedException {
		SoftAssert sf = new SoftAssert();
		try {
			minWait();
			long result = 1;
			while (j != 0)	{
			    result *= i;
			    --j;
			}
			String result1 = String.valueOf(result);
			System.out.println(result);
			String answer = Locators_Calculator.calc_Edit_text_field.getText();
			System.out.println(answer);
			System.out.println(answer.equals(result1));
			
			if (answer.equals(result1)) {
				check=true;
				APP_LOGS.info("Power Operation Validation Pass");
				sf.assertTrue(check, "TestCase status is Pass");   	
			} else {
				sf.fail();
			}
		} catch (org.openqa.selenium.NoSuchElementException  e) {
			test.log(LogStatus.INFO, e.getAdditionalInformation());

			
			e.printStackTrace();
			sf.fail();
		}
		sf.assertAll();
	}
	
	public void clearResultScreen() throws InterruptedException {
		minWait();
		aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_CLEAR);
		minWait();
	}
	
	public void validate_PieValue() throws InterruptedException {
		SoftAssert sf = new SoftAssert();
		try {
			minWait();
			String result = Locators_Calculator.calc_Edit_text_field.getText().replaceAll(" ", "").replace("point", ".");
			System.out.println(result);
			check1=result.contains("3");
			check2=result.contains("14159");
			if (check1&&check2) {
				check=true;
				APP_LOGS.info("Power Operation Validation Pass");
				sf.assertTrue(check, "TestCase status is Pass");  
			} else {
				sf.fail();
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {
			// TODO Auto-generated catch block
			test.log(LogStatus.INFO, e.getAdditionalInformation());

			e.printStackTrace();
			sf.fail();
		}
		sf.assertAll();		
	}
	
	public void validate_ExponentialValue() throws InterruptedException {
		SoftAssert sf = new SoftAssert();
		try {
			minWait();
			String result = Locators_Calculator.calc_Edit_text_field.getText().replaceAll(" ", "").replace("point", ".");
			
			check1=result.contains("2");
			check2=result.contains("71828");
			if (check1 && check2) {
				check=true;
				APP_LOGS.info("Exponential Operation Validation Pass");
				sf.assertTrue(check, "TestCase status is Pass");  
			} else {
				sf.fail();
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {
			
			test.log(LogStatus.INFO, e.getAdditionalInformation());

			e.printStackTrace();
			sf.fail();
		}
		sf.assertAll();		
	}
	
	public void numbers_PowerOperation() throws InterruptedException {
		  i=firstclick();
		  minWait();
		  click_OnPowerIcon();
		  minWait();
		  j=secondclick();
		 
		 }



}

