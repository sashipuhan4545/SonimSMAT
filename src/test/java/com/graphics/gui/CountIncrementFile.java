package com.graphics.gui;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class CountIncrementFile {




	public static int getCount(int Count) {

		int count = Count;
		try {
			if (!new File("src/test/resources/drivers/Increment.txt").exists())
				return 1;
			else {
				BufferedReader br = new BufferedReader(
						new FileReader(new File("src/test/resources/drivers/Count.txt")));
				String s = br.readLine();

				System.out.println(s);

				count = Integer.parseInt(s);
				System.out.println("String Count=" + count);

				br.close();
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (FileNotFoundException f) {
			// TODO Auto-generated catch block
			f.printStackTrace();
		} catch (IOException i) {
			// TODO Auto-generated catch block
			i.printStackTrace();
		}
		return count;
	}	

	public static void putCount(int count) {
		try {

			BufferedWriter bw = new BufferedWriter(new FileWriter(new File("src/test/resources/drivers/Count.txt")));
			bw.write(Integer.toString(count));
			bw.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	/* public void doMethod(int countValue) throws IOException {

	        int count = getCount(countValue);            
	        System.out.println("You are running this program " + count + " number of times");
	        count=count+3;
	        putCount(count);            
	    } */


}
