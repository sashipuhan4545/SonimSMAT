package com.graphics.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.Timer;

import com.sun.jna.platform.WindowUtils;

public class DisappearingMessage  implements ActionListener{
	private final int ONE_SECOND = 1000;

	private Timer timer;
	public static  JFrame frame;
	private JLabel msgLabel;

	public DisappearingMessage (String str) 
	{

		frame = new JFrame ();
		frame.setTitle("Information");
		frame.pack();
		frame.setLocationRelativeTo(null);



		msgLabel = new JLabel (str, SwingConstants.CENTER);
		msgLabel.setPreferredSize(new Dimension(300, 100));

		//timer = new Timer (this.ONE_SECOND * seconds, this);
		// only need to fire up once to make the message box disappear
		//	timer.setRepeats(false);
	}

	/**
	 * Start the timer
	 */
	public void start () {




		// make the message box appear and start the timer
		frame.getContentPane().add(msgLabel, BorderLayout.CENTER);
		frame.pack();

		frame.setLocationRelativeTo(null); 
		frame.setVisible(true);


		//	timer.start();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		//	timer.stop();
		frame.dispose();

	}
	public static void dispose() {
		frame.dispose();
	}



	public static void main(String[] args,String message) {

		DisappearingMessage dm=new DisappearingMessage(message);

		dm.start();
	}


}



