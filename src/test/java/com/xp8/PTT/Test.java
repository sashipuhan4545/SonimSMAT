package com.xp8.PTT;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.concurrent.TimeUnit;

import com.graphics.gui.JsonFileReaderAndWriter;

public class Test {

	public static void main(String[] args) throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		
		
		//Runtime.getRuntime().exec("adb shell input keyevent --longpress KEYCODE_PTT;sleep 9;swipe 535 1098 560 1080 15000");	
		
		
		String id="902690cb";
		String cmd="adb -s "+id+" shell \"dumpsys telephony.registry | grep mCallState\"";

	    Process child=Runtime.getRuntime().exec(cmd);
	    InputStream lsOut = child.getInputStream();
		InputStreamReader r = new InputStreamReader(lsOut);
		BufferedReader in = new BufferedReader(r);
		String value=in.readLine();

       System.out.println(value);
		
		
		
	

		/*
		Runtime.getRuntime().exec("adb shell sleep 2");	
      
		Runtime.getRuntime().exec("adb shell input  swipe 535 1098 560 1080 15000");	*/
		


	}

}
