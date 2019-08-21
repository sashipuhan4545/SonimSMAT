/* -------------------------------------------Appium server loader class---------------------------------------------------------------------
 * 							Starts an appium server on ip address and port specified..
 * 
 * 
 */


package com.xp5S.util;

import java.io.File;
import java.io.IOException;
import java.math.RoundingMode;
import java.net.ServerSocket;
import java.net.URL;
import java.text.DecimalFormat;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import com.graphics.gui.JsonFileReaderAndWriter;

import application.AllQA;
import application.MainController;
import application.SalesTeam;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import javafx.application.Platform;

public class appiumService {

	private AppiumDriverLocalService service;
	private AppiumServiceBuilder builder;
	private DesiredCapabilities cap;
	private int port = 4723;
	protected static URL appiumServerURL;
	public static String primary,secondary;

	public static double TOTAL_NUM_OF_TESTCASES;

	public static double INCREMENT_VLAUE;
	public static double TESTCASES_INCREMENTER=1;


	@BeforeSuite	
	public void startServer() {

		System.out.println("<------------------------Started Appium Service---------------------------------->");
		//Set Capabilities
		cap = new DesiredCapabilities();
		cap.setCapability("noReset", false);
		//cap.setCapability("relaxed-security", true);

		//Build the Appium service
		builder = new AppiumServiceBuilder();
		builder.usingDriverExecutable(new File("C:\\Program Files\\nodejs\\node.exe"));
		builder.withAppiumJS(new File(System.getProperty("user.home")+"\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js"));
		builder.withLogFile(new File (System.getProperty("user.home")+"\\AppiumServerLogs.txt"));

		builder.withIPAddress("127.0.0.1");
		builder.usingPort(port);


	//	builder.withArgument("relaxed-security");
		builder.withArgument(GeneralServerFlag.SESSION_OVERRIDE);
		//builder.withArgument(GeneralServerFlag.r);
		//builder.withArgument(GeneralServerFlag.LOG_LEVEL);
		builder.withArgument(GeneralServerFlag.LOG_LEVEL,"error");

		//	if (!this.checkIfServerIsRunning(port)) {
		//Start the server with the builder
		service = AppiumDriverLocalService.buildService(builder);
		service.start();
		appiumServerURL = service.getUrl();

		//	}
		//		else {
		//			System.out.println("Appium Server already running on Port - " + port);
		//		}
	}

	//	@AfterSuite
	//	public void stopServer() {
	//		service.stop();
	//	}



	 
	

	@BeforeSuite
	public void profileSelectionForQAAndNonQA() {


		if(MainController.TEAM_BASED_LOGIN.contains("QA Team")) {

			// AllQA.PRIMARYDEVMDN=AllQA.PRIMARYDEVMDN;
			//AllQA.REFERENCEDEVMDN=AllQA.REFERENCEDEVMDN;
			System.out.println("Logged in As QA-team");
		}else {

			AllQA.PRIMARYDEVMDN=SalesTeam.PRIMARYDEVMDN;
			AllQA.REFERENCEDEVMDN=SalesTeam.REFERENCEDEVMDN;
			AllQA.NUMOFCALLS=SalesTeam.NUMOFCALLS;
			AllQA.CALLDURATION=SalesTeam.CALLDURATION;
			AllQA.CALLGAP=SalesTeam.CALLGAP;



			System.out.println(AllQA.PRIMARYDEVMDN +","+AllQA.REFERENCEDEVMDN +","+AllQA.NUMOFCALLS);	
		}

	}

	@BeforeSuite	
	public void deletedTestReports() {

		try {

			deleteAdbLogFiles();

			System.out.println("<-----------------------DELETING EXTENT REPORT----------------->");

			File dir = new File("src/test/resources/extentreport");
			if(dir.isDirectory() == false) 
			{
				System.out.println("Not a directory. Do nothing");
				return;
			}

			File[] listFiles = dir.listFiles();
			for(File file : listFiles)
			{
				System.out.println("Deleting "+file.getName());
				file.delete();
			}

		} catch (Exception e) {

			e.printStackTrace();

		}


	}

	//This Method is used to delete all adb logs before Test case execution starts
	public void deleteAdbLogFiles() {

		try {


			System.out.println("<-----------------------DELETING ADB LOG Files ----------------->");

			/*File Dir = new File(System.getProperty("user.home") +File.separator +"Desktop"+File.separator+"SMAT_REPORT_LOGS");
			Dir.delete();*/

			File dir = new File("src/test/resources/adbLogs");
			if(dir.isDirectory() == false) 
			{
				System.out.println("Not a directory. Do nothing");
				return;
			}

			File[] listFiles = dir.listFiles();
			for(File file : listFiles)
			{
				System.out.println("Deleting "+file.getName());
				file.delete();
			}
			
			
			

		} catch (Exception e) {

			e.printStackTrace();

		}


	}



	public boolean checkIfServerIsRunning(int port) {

		boolean isServerRunning = false;
		ServerSocket serverSocket;
		try {
			serverSocket = new ServerSocket(port);
			serverSocket.close();

		} catch (IOException e) {
			//If control comes here, then it means that the port is in use
			isServerRunning = true;

		} finally {
			serverSocket = null;
		}
		return isServerRunning;
	}




	@AfterMethod
	public void UpdateProgressIndicator() {


		try {

			System.out.println(TESTCASES_INCREMENTER);

			double percentagePerCase = (TESTCASES_INCREMENTER/TOTAL_NUM_OF_TESTCASES);
			DecimalFormat df = new DecimalFormat("#.##");
			df.setRoundingMode(RoundingMode.FLOOR);
			String num = df.format(percentagePerCase);
			double value=Double.parseDouble(num);
			INCREMENT_VLAUE=value;
			TESTCASES_INCREMENTER++;



		}catch (Exception e) {

			System.out.println("Exeption in UpdateProgressIndicator method");

		}


	}		

	@BeforeTest
	public void preProcessing_AfterEndOf_TestCase() {

		

		try {
			/*System.out.println("<------------Initialising the variabl values to its defaulr values----------->");
			
			AllQA.NUM_OF_CALL_ITER_UPDATE="";
			AllQA.NUM_OF_CALL_ITER="";
			AllQA.NUM_OF_CALL_ITER_INCREMENTOR="";
			AllQA.TOTAL_NUM_OF_TESTCASES=0;
			AllQA.INCREMENT_VLAUE=0;
			AllQA.TESTCASES_INCREMENTER=1;
			TNGListner.Listner.onFinish="";
			TNGListner.Listner.onTestSkipped="";
			TNGListner.Listner.onTestSucess="";
			TNGListner.Listner.onTestStart="";
			TNGListner.Listner.onTestFailure="";
			AllQA. CALL_COUNT=0;
			AllQA.NUM_OF_CALL_ITER_UPDATE=" ";
			AllQA.NUM_OF_CALL_ITER=" ";
			AllQA.NUM_OF_CALL_ITER_INCREMENTOR=" ";
			System.out.println("<------------Initialising finished----------->");*/

			System.out.println("precondtion for making the screen timer to 30 seconds");
			Runtime.getRuntime().exec("adb -s "+JsonFileReaderAndWriter.primaryDevIdReader()+" shell settings put system screen_off_timeout 1800000");



		}catch (Exception e) {
			
		}



	}










}
