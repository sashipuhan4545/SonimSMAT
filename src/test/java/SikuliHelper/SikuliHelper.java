/*package SikuliHelper;

import org.sikuli.script.FindFailed;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;

import com.xp5S.util.CommonConfig;

public class SikuliHelper extends CommonConfig{


	static Screen sc=new Screen();

	



	public static void clickByImage(String imagepath) {

		try { 

			Pattern p=new Pattern(imagepath).similar((float)0.7);

			sc.wait(p,10);
			sc.click(p);
		//	APP_LOGS.info("Sikuli -> : Clicked on the item sucesfully ");

		} catch (FindFailed e) {

			APP_LOGS.info("Sikuli -> : Did not find the Image on the screen ");

			e.printStackTrace();
		}
	}

	public static void getText(String text) {
		
		try {
			sc.findText(text);
		} catch (FindFailed e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	

	public static boolean waitTilImageVanishes(String imagepath,int timeInSeonds) {

		boolean chk=false;

		try {


			Pattern p=new Pattern(imagepath).similar((float)0.7);

			sc.wait(p,10); 
			if(sc.waitVanish(p,timeInSeonds)) {

				chk=true;
				APP_LOGS.info("Sikuli -> :Image Vanished sucesfully");


			}else {

				chk=false;
				APP_LOGS.info("Sikuli -> :Image Vanished unsucesfully ");

			}


		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return chk;

	}


	public static boolean isImageExistOnScreen(String imagePath) {

		boolean chk=false;

		try {
			Pattern p=new Pattern(imagePath).similar((float)0.7);

			sc.wait(p,10);

			if(sc.exists(p)!=null) {

				chk=true;
			}else {
				chk=false;
			}

		} catch (FindFailed e) {

			APP_LOGS.info("Sikuli -> :Image not present on the screen ");


		}
		return chk; 



	}

	public static void typeTextIntoTextBox(String imagepath,String text) {

		try {
			Pattern p=new Pattern(imagepath).similar((float)0.7);

			sc.wait(p,10);
			sc.type(p,text);

		} catch (FindFailed e) {
			APP_LOGS.info("Sikuli -> :Image not present on the screen so failed to type in the textbox");
			e.printStackTrace();
		}

	}

	public static void scroll(String imagepath,int postion,int direction) {
		try {

			Pattern p=new Pattern(imagepath).similar((float)0.7);

			sc.wait(p,10);

			sc.wheel(p,postion,direction);
		} catch (FindFailed e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	
	public static void scrollTillElementVisible(String imagepath) throws FindFailed {
		
		do {
			
			Pattern p=new Pattern(imagepath).similar((float)0.7);
	
			if(sc.exists(p)!= null) {
				
				break;

			}else {
				sc.wheel(1,1);

			}
			
			
		}while(true);
			
	}
	

}
*/