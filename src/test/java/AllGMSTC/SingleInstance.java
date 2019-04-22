package AllGMSTC;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.BindException;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.util.Scanner;
import java.util.prefs.Preferences;

import javax.swing.JOptionPane;


import application.Main;
import application.MainController;
import javafx.application.Platform;

public class SingleInstance{
	
	public static File f;
	public static FileChannel channel;
	public static FileLock lock;
	
	
	
	public static void main(String[] args) {
		
		try {
			
			f=new File("sashi.lock");
			
			if(f.exists()) {
				
				f.delete();
			}
			channel=new RandomAccessFile(f, "rw").getChannel();
			lock=channel.tryLock();
			System.out.println(lock);
			
			if(lock == null) {
				
				channel.close();
				
			   
				System.exit(0);
			}
			
			Thread shutdown=new Thread(new Runnable() {
				
				@Override
				public void run() {
					unlock();
					
				}
			});
			
			Runtime.getRuntime().addShutdownHook(shutdown);
			
			
			
			while(true) {
				
			}
			
		}
		catch(IOException e) {
			throw new RuntimeException("Could not start process",e);
			
		}
	
			
	}
	public static void unlock() {
	 
		try {
			if(lock != null) {
				
				lock.release();
				channel.close();
				f.delete();
			}
			
		}
		catch(IOException e) {
			
			e.printStackTrace();
		}
		
		
	}
	
	
	
	
	
	
}