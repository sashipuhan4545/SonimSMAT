package TNGListner;





import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import application.AllQA;

//Added Listner


public class Listner extends AllQA implements ITestListener{




	public String time1;
	public String time2;
	public long Diff;

	public static String onTestStart="";
	public static String onTestSucess="";
	public static String onTestFailure="";
	public static String onFinish="";
	public static String onStart="";
	public static String onTestSkipped="";
	public static String skip="";


	@Override
	public void onFinish(ITestContext result)
	{

      onFinish="TCFINISHED";
      
      System.out.println("Now Sending Email After finish");
      AllGMSTC.EmailAttachmentSender.main(null);


   }

	@Override
	public void onStart(ITestContext result) {






	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {


	}

	@Override
	public void onTestFailure(ITestResult result) {

		System.out.println("From TestNg test case got failed");

		/*String value=null;
		int exeResult=result.getStatus();
		if(exeResult==2) {
			value="FAIL";
		}*/


		onTestFailure="\n"+result.getName();

	//	onTestFailure="\n"+result.getName() +" ->  "+value;



	}

	@Override
	public void onTestSkipped(ITestResult result) {

      System.out.println();
		
		onTestSkipped="TCSKIPPED";


	}

	@Override
	public void onTestStart(ITestResult result) {

		onTestStart="\n" + "Current TC : " + "\n" +result.getName() +" ->  "+"In-Progress";


	}

	@Override
	public void onTestSuccess(ITestResult result) {


		System.out.println("Test cases passed->onTest sucess");

	/*	String value=null;
		int exeResult=result.getStatus();
		if(exeResult==1) {
			value="PASS";
		}*/

		onTestSucess="\n"+result.getName();

		//onTestSucess="\n"+result.getName() +" ->  "+value;

	}


}