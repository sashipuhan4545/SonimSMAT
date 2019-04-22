package com.graphics.gui;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class ReturnTestResult {

	public static String getExecutionStatus() {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		PrintStream ps = new PrintStream(baos);
		PrintStream old = System.out;
		System.setOut(ps);
		System.out.println();
		System.out.flush();
		System.setOut(old);
		String value=baos.toString();
		return value;


	}

}
