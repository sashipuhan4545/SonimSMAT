package AllGMSTC;

import java.awt.image.BufferedImage;
import java.awt.image.DataBuffer;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.AndroidKeyCode;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.remote.MobileCapabilityType;


public class EXP {

	
		
	
		
		AndroidDriver<AndroidElement> driver;
	
	 	
		
		
		
		
		@BeforeSuite
		public void setup() throws MalformedURLException {
			
			System.out.println("0");


			DesiredCapabilities capabilities = new DesiredCapabilities();
			capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "XP8800");

			capabilities.setCapability(MobileCapabilityType.UDID,"9362c47a");
			//capabilities.setCapability("automationName", "UiAutomator2");
			capabilities.setCapability("orientation","PORTRAIT");
			capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "8.1.0");
			capabilities.setCapability("autoGrantPermissions", true);
			
			//	capabilities.setCapability("autoGrantPermissions", true);
			capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");

			capabilities.setCapability("appPackage", "com.android.launcher3");
			capabilities.setCapability("appActivity", "com.android.searchlauncher.SearchLauncher");
			

	       
		



			//aDriver = new AndroidDriver<AndroidElement>(new URL("http://"+URL_), capabilities);
			driver = new AndroidDriver<AndroidElement>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);

			driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
			
			
			
			
			
			
			
		}
		
		@BeforeTest
		public void t_config() {
			
			System.out.println("1111111111111111111111111111111");
			
			/*p=new Pages(driver);
			PageFactory.initElements(new AppiumFieldDecorator(driver), p);*/
			
		}
		
		
		
		@Test
		public void TC1() throws InterruptedException {
			
			
			driver.pressKeyCode(AndroidKeyCode.HOME);
			TimeUnit.SECONDS.sleep(10);
			
			//driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().resourceId(\"com.android.launcher3:id/apps_list_view\")).text(\"YouTube\")").click();
			driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().resourceId(\"com.android.launcher3:id/apps_list_view\").className(\"android.support.v7.widget.RecyclerView\")).scrollIntoView("
					+ "new UiSelector().descriptionContains(\"YouTube\"))");
			//driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().resourceId(\"com.android.launcher3:id/apps_list_view\").text(\"YouTube\"))").click();
			
			/*driver.findElementByAccessibilityId("Apps list").click();
			List<String> removePicsArgs = Arrays.asList("-lrt");
			Map<String, Object> removePicsCmd = ImmutableMap.of("command", "ls", "args", removePicsArgs);
			System.out.println((String)driver.executeScript("mobile: shell", removePicsCmd));
			
			*/
			
			
			/* driver.pressKey(new KeyEvent(AndroidKey.APP_SWITCH));
			 
			 Thread.sleep(2000);
			 
			 while(true) {
				 
			 scroll(558, 300, 558, 300);
			 
			 boolean value=driver.findElement(By.xpath("//android.widget.TextView[@text='CLEAR ALL']")).isDisplayed();
			 if(value) {
				 
				 driver.findElement(By.xpath("//android.widget.TextView[@text='CLEAR ALL']")).click();
				 break;
				 
			 }else {
				
				 continue;
			}
			 
			
		}
			 */
			 
			
			
			
		   /* driver.findElementByAccessibilityId("Apps list").click();
		    driver.pressKey(new KeyEvent(AndroidKey.APP_SWITCH));
		   Thread.sleep(2000);
		   
		   while(true) {
			   
			   try {
				if(Pages.clear_all.isDisplayed()) {
					   Pages.clear_all.click();
					   break;
				   }else {
					
					  
				}
			} catch (Exception e) {
				
				 
				   Thread.sleep(2000);
				   continue;
			
			}
		   }
		   
		   
	*/
		
		
			
			
		}
		
	  /* public void scroll(int sx,int sy,int ex,int ey) {
			
		   TouchAction t=new TouchAction(driver);
		   t.longPress(PointOption.point(sx,sy)).moveTo(PointOption.point(ex,ey)).release().perform();
			
			
			
		}*/
	   
	   public boolean isElementPresent(AndroidElement e) {
		   
		   boolean chk=false;
		   System.out.println("Inside isElement");

		   
		   if(e.isDisplayed()) {
			   
			   
			   chk=true;
			   
		   }
		return chk;
		   
		   
		   
	   }

		
		
		
		
		
		
		
		
		/*
		try {
			Pattern searchImage = new Pattern("C:\\Users\\sashi.p\\Desktop\\Data\\par_2.png").similar((float)0.9);
			String ScreenImage = "C:\\Users\\sashi.p\\Desktop\\Data\\MAI.png"; //In this case, the image you want to search
			Finder objFinder = null;
			Match objMatch = null;
			objFinder = new Finder(ScreenImage);
			objFinder.find(searchImage); //searchImage is the image you w	ant to search within ScreenImage
			int counter = 0;
			while(objFinder.hasNext())
			{
			    objMatch = objFinder.next(); //objMatch gives you the matching region.
			    System.out.println(objMatch);
			    counter++;
			}
			if(counter!=0)
			System.out.println("Match Found!");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
*/
		
	}
	
	
	
	



