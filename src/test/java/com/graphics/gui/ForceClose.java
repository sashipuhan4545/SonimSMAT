/*package com.graphics.gui;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import javax.swing.JOptionPane;

import org.json.simple.parser.ParseException;

import com.xp5S.util.CommonConfig;
import com.android.ddmlib.IDevice;
import com.beust.testng.TestNG;

import TNGListner.BlockingDialog;

public class ForceClose {

	public static void BGProcess() throws IOException {

		Timer timer = new Timer(); 
		timer.schedule( new TimerTask() 
		{ 
			public void run() { 

				if(ToolFrame.resetAll.isEnabled()) {
					BlockingDialog.blocker.setVisible(false);

				}

			} 
		}, 0, 100*(100*1));


	}
	public static void BGProcess1() {

		Timer timer = new Timer(); 
		timer.schedule( new TimerTask() 
		{ 
			public void run() { 

				if(TNGListner.BlockingDialog.progress.isDisplayable()) {
					BlockingDialog.blocker.setVisible(false);

				}

			} 
		}, 0, 100*(100*1));

	}

	public static void showAdbMessage() throws InterruptedException, ExecutionException {
		
		
		ScheduledExecutorService scheduledExecutorService =
		        Executors.newScheduledThreadPool(5);

		ScheduledFuture scheduledFuture =
		    scheduledExecutorService.schedule(new Callable() {
		        public Object call() throws Exception {
		        	
		        	
		        	JOptionPane.showMessageDialog(null, "Please detect the device in 'CMD'");
		        	BlockingDialog.blocker.setVisible(false);
		            
		            return "sashi";
		        }
		    },
		    5,
		    TimeUnit.SECONDS);

		try {
			System.out.println("result = " + scheduledFuture.get());
		} catch (InterruptedException | ExecutionException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		scheduledExecutorService.shutdown();

		
	

}
}	

*/