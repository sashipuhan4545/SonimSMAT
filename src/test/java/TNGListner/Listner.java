package TNGListner;





import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.relevantcodes.extentreports.LogStatus;
import com.xp8.util.BaseUtil;

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

		
		System.out.println("Test cases failed .so taking screenshots");

        // Method method = null;


		onTestFailure="\n"+result.getName();
		
		/*try {
			
			String screenshot_path=BaseUtil.captureScreenshot(method.getName());
			String image= BaseUtil.test.addScreenCapture(screenshot_path);		
			BaseUtil.test.log(LogStatus.FAIL,result.getThrowable());
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		*/




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