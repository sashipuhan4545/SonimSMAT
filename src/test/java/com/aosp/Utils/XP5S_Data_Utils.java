package com.aosp.Utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.concurrent.TimeUnit;

import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import com.graphics.gui.JsonFileReaderAndWriter;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.xp5S.util.BaseUtil;

import application.AllQA;
import application.MainController;
import application.SalesTeam;
import io.appium.java_client.android.AndroidKeyCode;
import javafx.application.Platform;


public class XP5S_Data_Utils extends BaseUtil{

	public boolean check=false;

	public static ExtentReports extent;

	public static ExtentTest test;




	public void MO_MT_CALLS() throws InterruptedException, FileNotFoundException, IOException, ParseException {
		//This will ask the user to enter reference device Id

		SoftAssert sf = new SoftAssert();


		String Refdevid=JsonFileReaderAndWriter.ReadRefDeviceId();
		System.out.println(Refdevid);
		String mobnumber=AllQA.REFERENCEDEVMDN;
		JsonFileReaderAndWriter.RefMobileNumber(mobnumber);

		String Uid=JsonFileReaderAndWriter.primaryDevIdReader();





		for(int i=1;i<=AllQA.NUMOFCALLS;i++)
		{
			try{


				Runtime.getRuntime().exec("adb -s "+Uid+" shell am start -a android.intent.action.CALL -d tel:"+AllQA.REFERENCEDEVMDN);
				Thread.sleep(10000);

				for(int j=1;j<=100;j++){

					Process child = null;
					if (JsonFileReaderAndWriter.ReadRefDeviceFirmWare().contains("-7.")) {

						child=Runtime.getRuntime().exec("adb -s "+Refdevid+" shell service call telecom 27");

					} else if(JsonFileReaderAndWriter.ReadRefDeviceFirmWare().contains("-8.")) {

						child=Runtime.getRuntime().exec("adb -s "+Refdevid+" shell service call telecom 29");
					}     

					//Process child=Runtime.getRuntime().exec("adb -s "+Refdevid+" shell service call telecom 27");
					InputStream lsOut = child.getInputStream();
					InputStreamReader r = new InputStreamReader(lsOut);
					BufferedReader in = new BufferedReader(r);
					String value=in.readLine();

					if(value.contains("00000001")) {

						System.out.println("MO_MT CALL phone is rining so acceping call made from primary");

						test.log(LogStatus.INFO, "DUT-->REF #"+i  +"     :     "+"  "+AllQA.PRIMARYDEVMDN +"     ===>>>     "+AllQA.REFERENCEDEVMDN);

						Runtime.getRuntime().exec("adb -s "+Refdevid+" shell input keyevent 5");
						break;

					}else if (value.contains("00000000")) {

						System.out.println("Phone is not Ringing ");

					}else {
						test.log(LogStatus.INFO, "MO CALL #"+i +"     :     Ref Phone is not Ringing because of network issue/Call has be landed . ");
						sf.fail();

					}


				}
				//	Thread.sleep(30000);
				TimeUnit.MINUTES.sleep(AllQA.CALLDURATION);

				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_ENDCALL);
				Thread.sleep(AllQA.CALLGAP*1000);  





				System.out.println("Now Executing for REF phone");
				Runtime.getRuntime().exec("adb -s "+Refdevid+" shell am start -a android.intent.action.CALL -d tel:"+AllQA.PRIMARYDEVMDN);
				Thread.sleep(10000);

				for(int j=1;j<=100;j++){

					Process child = null;
					if (JsonFileReaderAndWriter.primaryDevFirmwareReader().contains("-7.")) {

						child=Runtime.getRuntime().exec("adb -s "+Uid+" shell service call telecom 27");

					} else if(JsonFileReaderAndWriter.primaryDevFirmwareReader().contains("-8.")) {

						child=Runtime.getRuntime().exec("adb -s "+Uid+" shell service call telecom 29");
					}     



					//	Process child=Runtime.getRuntime().exec("adb -s "+Uid+" shell service call telecom 27");
					InputStream lsOut = child.getInputStream();
					InputStreamReader r = new InputStreamReader(lsOut);
					BufferedReader in = new BufferedReader(r);
					String value=in.readLine();
					System.out.println("------------------------------->"+value);
					if(value.contains("00000001")) {
						System.out.println("MT_MO CALL phone is rining so acceping call made from REf");


						test.log(LogStatus.INFO, "REF --> DUT #"+i  +"     :     "+"  "+ AllQA.REFERENCEDEVMDN  +"     ===>>>     "+AllQA.PRIMARYDEVMDN) ;

						Runtime.getRuntime().exec("adb -s "+Uid+" shell input keyevent 5");
						break;
						
						

					}else if (value.contains("00000000")) {
						
						System.out.println("Phone is not rining ");
						
					}else {
						test.log(LogStatus.INFO, "MO CALL #"+i +"     :     Primary Phone is not Ringing because of network issue/Call has be landed . ");
						sf.fail();

					}
				}
				//	Thread.sleep(30000);
				TimeUnit.MINUTES.sleep(AllQA.CALLDURATION);

				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_ENDCALL);
				Thread.sleep(AllQA.CALLGAP*1000); 
				continue;

			}catch(Exception e) {
				Runtime.getRuntime().exec("adb -s \""+Refdevid+"\" shell input keyevent 5");
				Thread.sleep(2000);
				continue;
			}

		}
		sf.assertAll();



	}



	public void MO() throws FileNotFoundException, IOException, ParseException, InterruptedException{


		APP_LOGS.info("S@shi:This Method is used to Originate MO call");

		SoftAssert sa=new SoftAssert();
		boolean checkCall;

		String Refdevid=JsonFileReaderAndWriter.ReadRefDeviceId();
		String mobnumber=AllQA.REFERENCEDEVMDN;
		JsonFileReaderAndWriter.RefMobileNumber(mobnumber);
		String Uid=JsonFileReaderAndWriter.primaryDevIdReader();



		for(int k=1;k<=AllQA.NUMOFCALLS;k++)
		{
			Runtime.getRuntime().exec("adb -s "+Uid+" shell am start -a android.intent.action.CALL -d tel:"+AllQA.REFERENCEDEVMDN);
			Thread.sleep(10000);
			checkCall = false;
			try {

				for(int j=1;j<=100;j++){

					Process child = null;

					if (JsonFileReaderAndWriter.ReadRefDeviceFirmWare().contains("-7.")) {

						child=Runtime.getRuntime().exec("adb -s "+Refdevid+" shell service call telecom 27");

					} else if(JsonFileReaderAndWriter.ReadRefDeviceFirmWare().contains("-8.")) {

						child=Runtime.getRuntime().exec("adb -s "+Refdevid+" shell service call telecom 29");
					}     

					InputStream lsOut = child.getInputStream();
					InputStreamReader r = new InputStreamReader(lsOut);
					BufferedReader in = new BufferedReader(r);
					String value=in.readLine();


					if(value.contains("00000001")) {
						System.out.println("Phone is ringing so accepting caall");
						checkCall = true;
						//						test.log(LogStatus.INFO, "MO CALL #"+k  +"     :     "+"  "+AllQA.PRIMARYDEVMDN +"     ===>>>     "+AllQA.REFERENCEDEVMDN);


						Runtime.getRuntime().exec("adb -s "+Refdevid+" shell input keyevent 5");
						break;


					}/*else if(value.contains("00000000")) {


					}else {
//						test.log(LogStatus.INFO, "MO CALL #"+k +"     :     Primary Phone is not Ringing because of network issue/Call has be landed . ");
						sa.fail();

					}*/
				}
				System.out.println(checkCall+"Call in which state");
				if(checkCall){
					test.log(LogStatus.INFO, "MO CALL #"+k  +"     :     "+"  "+AllQA.PRIMARYDEVMDN +"     ===>>>     "+AllQA.REFERENCEDEVMDN);

				}
				else{
					test.log(LogStatus.INFO, "MO CALL #"+k +"     :     Primary Phone is not Ringing because of network issue/Call has be landed . ");
				}

				//	Thread.sleep(30000);
				System.out.println("2 min timer started");
				TimeUnit.MINUTES.sleep(AllQA.CALLDURATION);
				System.out.println("2 min timer ended");

				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_ENDCALL);
				Thread.sleep(AllQA.CALLGAP*1000); 

			}catch(Exception e) {

				Runtime.getRuntime().exec("adb -s \""+Refdevid+"\" shell input keyevent 5");
				Thread.sleep(2000);
				continue;
			}
			sa.assertAll();

		}

	}

	public void MT() throws FileNotFoundException, IOException, ParseException, InterruptedException {



		APP_LOGS.info("S@shi:This Method is used to Receive MT call from Reference Device to Primary Device");
		String Refdevid=JsonFileReaderAndWriter.ReadRefDeviceId();
		String mobnumber=AllQA.REFERENCEDEVMDN;
		JsonFileReaderAndWriter.RefMobileNumber(mobnumber);
		String Uid=JsonFileReaderAndWriter.primaryDevIdReader();


		SoftAssert sa = new SoftAssert();
		for(int i=1;i<=AllQA.NUMOFCALLS;i++)
		{

			Runtime.getRuntime().exec("adb -s "+Refdevid+" shell am start -a android.intent.action.CALL -d tel:"+AllQA.PRIMARYDEVMDN);
			Thread.sleep(10000);

			try {

				for(int j=1;j<=100;j++){

					Process child = null;


					if (JsonFileReaderAndWriter.ReadRefDeviceFirmWare().contains("-7.")) {

						child=Runtime.getRuntime().exec("adb -s "+Uid+" shell service call telecom 27");

					} else if(JsonFileReaderAndWriter.ReadRefDeviceFirmWare().contains("-8.")) {

						child=Runtime.getRuntime().exec("adb -s "+Uid+" shell service call telecom 29");
					}     



					InputStream lsOut = child.getInputStream();
					InputStreamReader r = new InputStreamReader(lsOut);
					BufferedReader in = new BufferedReader(r);
					String value=in.readLine();
					if(value.contains("00000001")) {

						System.out.println("Phone is rining");

						test.log(LogStatus.INFO, "MT CALL #"+i  +"     :     "+"  "+AllQA.REFERENCEDEVMDN +"     ===>>>     "+AllQA.PRIMARYDEVMDN);

						Runtime.getRuntime().exec("adb -s "+Uid+" shell input keyevent 5");
						break;

					}else if(value.contains("00000000")) {

						System.out.println("Phone is not rining so TC fail");


						//sa.fail();
					}else {
						test.log(LogStatus.INFO, "MT CALL #"+i +"     :     Primary Phone is not Ringing because of network issue/Call has be landed . ");
						sa.fail();

					}


				}

				//	Thread.sleep(30000);
				TimeUnit.MINUTES.sleep(AllQA.CALLDURATION);
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_ENDCALL);
				Thread.sleep(AllQA.CALLGAP*1000);  
				continue;	

			}catch (Exception e) {

				System.out.println("saldsldjsad");
				Runtime.getRuntime().exec("adb -s "+Refdevid+" shell input keyevent 5");
				Thread.sleep(2000);
				continue;			
			}


		}
		sa.assertAll();


	}

	//THis method is used for XP5 device
	public void MO_XP5(int telecomCode) throws FileNotFoundException, IOException, ParseException, InterruptedException {


		SoftAssert sf = new SoftAssert();

		String Refdevid=JsonFileReaderAndWriter.ReadRefDeviceId();
		String Uid=JsonFileReaderAndWriter.primaryDevIdReader();



		System.out.println(AllQA.PRIMARYDEVMDN +"----"+ AllQA.REFERENCEDEVMDN);

		for(int i=1;i<=AllQA.NUMOFCALLS;i++)
		{
			Runtime.getRuntime().exec("adb -s "+Uid+" shell am start -a android.intent.action.CALL -d tel:"+AllQA.REFERENCEDEVMDN);
			Thread.sleep(10000);

			try {

				for(int j=1;j<=100;j++){


					Process child=Runtime.getRuntime().exec("adb -s "+Refdevid+" shell service call telecom "+telecomCode+"");
					InputStream lsOut = child.getInputStream();
					InputStreamReader r = new InputStreamReader(lsOut);
					BufferedReader in = new BufferedReader(r);
					String value=in.readLine();
					System.out.println(value);
					if(value.contains("00000001")) {

						System.out.println("Phone is rining");
						test.log(LogStatus.INFO, "MO CALL #"+i+"     :     "+""+AllQA.PRIMARYDEVMDN +"     ===>>>     "+AllQA.REFERENCEDEVMDN);


						Runtime.getRuntime().exec("adb -s \""+Refdevid+"\" shell input keyevent 5");
						sf.assertTrue(true, "");

						break;

					}else if(value.contains("00000000")) {

						System.out.println("Phone is not rining so TC fail");



					}else {
						test.log(LogStatus.INFO, "MO CALL #"+i +"     :     Primary Phone is not Ringing because of network issue/Call has be landed . ");
						sf.fail();

					}


				}
				//	Thread.sleep(30000);
				TimeUnit.MINUTES.sleep(AllQA.CALLDURATION);

				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_ENDCALL);
				Thread.sleep(AllQA.CALLGAP*1000);  
				continue;

			}catch (Exception e) {

				Runtime.getRuntime().exec("adb -s "+Refdevid+" shell input keyevent 6");
				Thread.sleep(2000);
				continue;
			}

		}
		sf.assertAll();

	}

	public void MT_XP5(int telecomCode) throws FileNotFoundException, IOException, ParseException, InterruptedException {




		SoftAssert sa = new SoftAssert();

		String Refdevid=JsonFileReaderAndWriter.ReadRefDeviceId();

		String Uid=JsonFileReaderAndWriter.primaryDevIdReader();



		for(int i=1;i<=AllQA.NUMOFCALLS;i++)
		{


			Runtime.getRuntime().exec("adb -s "+Refdevid+" shell am start -a android.intent.action.CALL -d tel:"+AllQA.PRIMARYDEVMDN);

			Thread.sleep(10000);

			try {

				for(int l=1;l<=100;l++){



					Process child=Runtime.getRuntime().exec("adb -s "+Uid+" shell service call telecom "+telecomCode+"");
					InputStream lsOut = child.getInputStream();
					InputStreamReader r = new InputStreamReader(lsOut);
					BufferedReader in = new BufferedReader(r);
					String value=in.readLine();
					System.out.println(value);
					if(value.contains("00000001")) {

						System.out.println("Phone is rining for MT-------------------");

						test.log(LogStatus.INFO, "MT CALL #"+i  +"     :     "+"  "+AllQA.REFERENCEDEVMDN +"     ===>>>     "+AllQA.PRIMARYDEVMDN);

						Runtime.getRuntime().exec("adb -s "+Uid+" shell input keyevent 5");

						break;

					}else if(value.contains("00000000")) {

						System.out.println("Phone is not rining so TC fail");


						//sa.fail();
					}else {
						test.log(LogStatus.INFO, "MT CALL #"+i +"     :     Primary Phone is not Ringing because of network issue/Call has be landed . ");
						sa.fail();

					}



				}

				//	Thread.sleep(30000);
				TimeUnit.MINUTES.sleep(AllQA.CALLDURATION);

				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_ENDCALL);
				Thread.sleep(AllQA.CALLGAP*1000);  
				continue;	

			}catch (Exception e) {

				e.printStackTrace();
				Runtime.getRuntime().exec("adb -s "+Refdevid+" shell input keyevent 5");
				Thread.sleep(2000);
				continue;			
			}


		}
		sa.assertAll();



	}


	public void MO_MT_XP5_CALLS(int telecomCode) throws InterruptedException, FileNotFoundException, IOException, ParseException {

		SoftAssert sf = new SoftAssert();

		String Refdevid=JsonFileReaderAndWriter.ReadRefDeviceId();


		String Uid=JsonFileReaderAndWriter.primaryDevIdReader();

		try {


			for(int i=1;i<=AllQA.NUMOFCALLS;i++)
			{
				Runtime.getRuntime().exec("adb -s "+Uid+" shell am start -a android.intent.action.CALL -d tel:"+AllQA.REFERENCEDEVMDN);
				Thread.sleep(10000);

				for(int j=1;j<=100;j++){

					Process child=Runtime.getRuntime().exec("adb -s "+Refdevid+" shell service call telecom "+telecomCode+"");
					InputStream lsOut = child.getInputStream();
					InputStreamReader r = new InputStreamReader(lsOut);
					BufferedReader in = new BufferedReader(r);
					String value=in.readLine();
					System.out.println(value);
					if(value.contains("00000001")) {

						test.log(LogStatus.INFO, "DUT-->REF #"+i  +"     :     "+" "+AllQA.PRIMARYDEVMDN +"     ===>>>     "+AllQA.REFERENCEDEVMDN);

						Runtime.getRuntime().exec("adb -s "+Refdevid+" shell input keyevent 5");
						sf.assertTrue(true, "");
						break;


					}else if(value.contains("00000000")) {

						//System.out.println("Phone is not rining so TC fail");


						//sa.fail();
					}else {

						test.log(LogStatus.INFO, "MO-MT CALL #"+i +"     :     Primary Phone is not Ringing because of network issue/Call has be landed . ");
						sf.fail();

					}



				}

				//	Thread.sleep(30000);
				TimeUnit.MINUTES.sleep(AllQA.CALLDURATION);

				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_ENDCALL);
				Thread.sleep(AllQA.CALLGAP*1000); 



				Runtime.getRuntime().exec("adb -s "+Refdevid+" shell am start -a android.intent.action.CALL -d tel:"+AllQA.PRIMARYDEVMDN);
				System.out.println("XP5 Refrence phone ");
				Thread.sleep(10000);


				for(int j=1;j<=100;j++){

					Process child=Runtime.getRuntime().exec("adb -s "+Uid+" shell service call telecom "+telecomCode+"");
					InputStream lsOut = child.getInputStream();
					InputStreamReader r = new InputStreamReader(lsOut);
					BufferedReader in = new BufferedReader(r);
					String value=in.readLine();
					if(value.contains("00000001")) {
						System.out.println("MT_MO CALL phone is rining so acceping call made from REf");


						test.log(LogStatus.INFO, "REF-->DUT #"+i  +"     :     "+"  "+ AllQA.REFERENCEDEVMDN  +"     ===>>>     "+AllQA.PRIMARYDEVMDN) ;

						Runtime.getRuntime().exec("adb -s "+Uid+" shell input keyevent 5");
						sf.assertTrue(true, "");
						break;

					} else if(value.contains("00000000")) {

						System.out.println("Phone is not rining so TC fail");


						//sa.fail();
					}else {
						test.log(LogStatus.INFO, "MT-MO CALL #"+i +"     :     Primary Phone is not Ringing because of network issue/Call has be landed . ");
						sf.fail();

					}


				}


				TimeUnit.MINUTES.sleep(AllQA.CALLDURATION);

				//Thread.sleep(30000);
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_ENDCALL);
				Thread.sleep(AllQA.CALLGAP*1000); 
				continue;
			}

		}catch(Exception e) {
			Runtime.getRuntime().exec("adb -s \""+Refdevid+"\" shell input keyevent 5");
			Thread.sleep(2000);
		}
		sf.assertAll();

	}



	public static void MO_CALL_Sanity() throws FileNotFoundException, IOException, ParseException, InterruptedException {

		APP_LOGS.info("S@shi:This Method is used to Originate MO call");


		String Refdevid=JsonFileReaderAndWriter.ReadRefDeviceId();
		String mobnumber=AllQA.REFERENCEDEVMDN;
		JsonFileReaderAndWriter.RefMobileNumber(mobnumber);
		String Uid=JsonFileReaderAndWriter.primaryDevIdReader();

		for(int i=1;i<=1;i++)
		{
			Runtime.getRuntime().exec("adb -s "+Uid+" shell am start -a android.intent.action.CALL -d tel:"+AllQA.REFERENCEDEVMDN);
			Thread.sleep(10000);

			try {

				for(int j=1;j<=100;j++){
					String value=null;
					Process child=Runtime.getRuntime().exec("adb -s "+Refdevid+" shell service call telecom 28");
					InputStream lsOut = child.getInputStream();
					InputStreamReader r = new InputStreamReader(lsOut);
					BufferedReader in = new BufferedReader(r);
					value=in.readLine();
					System.out.println(value);
					System.out.println(Refdevid);
					if(value.contains("00000001")) {

						System.out.println("Phone is rining");

						test.log(LogStatus.INFO, "MO CALL #"+i  +"     :     "+"  "+AllQA.PRIMARYDEVMDN +"     ===>>>     "+AllQA.REFERENCEDEVMDN);
						Thread.sleep(AllQA.CALLGAP*1000); 

						Runtime.getRuntime().exec("adb -s "+Refdevid+" shell input keyevent 5");
						break;

					}


				}
				System.out.println("Disconceting ");
				Thread.sleep(AllQA.CALLGAP*1000); 
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_ENDCALL);
				Thread.sleep(AllQA.CALLGAP*1000);  

			}catch (Exception e) {

				System.out.println("Execption in call recevie ");

				Runtime.getRuntime().exec("adb -s "+Refdevid+" shell input keyevent 5");
				Thread.sleep(2000);

			}
		}


	}

	public static void MO_CALL_XP8_Sanity() throws FileNotFoundException, IOException, ParseException, InterruptedException{

		System.out.println("MO_XP8");
		APP_LOGS.info("S@shi:This Method is used to Originate MO call");

		String Refdevid=JsonFileReaderAndWriter.ReadRefDeviceId();
		String mobnumber=AllQA.REFERENCEDEVMDN;
		JsonFileReaderAndWriter.RefMobileNumber(mobnumber);
		String Uid=JsonFileReaderAndWriter.primaryDevIdReader();


		for(int k=1;k<=1;k++)
		{
			Runtime.getRuntime().exec("adb -s "+Uid+" shell am start -a android.intent.action.CALL -d tel:"+AllQA.REFERENCEDEVMDN);
			Thread.sleep(AllQA.CALLGAP*1000); 

			try {

				for(int j=1;j<=100;j++){
					String value=null;
					Process child=Runtime.getRuntime().exec("adb -s "+Refdevid+" shell service call telecom 27");
					InputStream lsOut = child.getInputStream();
					InputStreamReader r = new InputStreamReader(lsOut);
					BufferedReader in = new BufferedReader(r);
					value=in.readLine();
					if(value.contains("00000001")) {
						System.out.println("Phone is ringing so accepting caall");

						test.log(LogStatus.INFO, "MO CALL #"+k  +"     :     "+" "+AllQA.PRIMARYDEVMDN +"     ===>>>     "+AllQA.REFERENCEDEVMDN);

						Thread.sleep(AllQA.CALLGAP*1000); 
						Runtime.getRuntime().exec("adb -s "+Refdevid+" shell input keyevent 5");
						break;
					}else {

						test.log(LogStatus.INFO, "MO CALL #"+k  +"     :     Reference Phone is not Ringing because of network issue . ");

					}



				}
				Thread.sleep(AllQA.CALLGAP*1000); 
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_ENDCALL);
				Thread.sleep(AllQA.CALLGAP*1000);  

			}catch(Exception e) {

				Runtime.getRuntime().exec("adb -s "+Refdevid+" shell input keyevent 6");
				Thread.sleep(2000);

			}
		}






	}


	/*
	 * 
	 * Below method are used for XP3 call performance
	 * 
	 */







}






































