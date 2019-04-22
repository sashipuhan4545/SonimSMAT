package com.graphics.gui;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.Timer;

public class DisappearDialog implements ActionListener
{
	private final int ONE_SECOND = 1000;

	private Timer timer;
	private static JFrame frame;
	private JLabel msgLabel;

	public DisappearDialog (String str, int seconds) 
	{
		frame = new JFrame ("Information");
		msgLabel = new JLabel (str, SwingConstants.CENTER);
		msgLabel.setPreferredSize(new Dimension(300, 100));
		frame.setUndecorated(true);

		timer = new Timer (this.ONE_SECOND * seconds, this);
		// only need to fire up once to make the message box disappear
		timer.setRepeats(false);
	}

	/**
	 * Start the timer
	 */
	public void start ()
	{
		// make the message box appear and start the timer
		frame.getContentPane().add(msgLabel, BorderLayout.CENTER);
		frame.pack();
		frame.setLocationRelativeTo(null); 
		frame.setVisible(true);

		timer.start();
	}

	/**
	 * Handling the event fired by the timer
	 */
	public void actionPerformed (ActionEvent event)
	{
		// stop the timer and kill the message box
		timer.stop();
		frame.dispose();
	}

	public static void dispose() {
		frame.dispose();
	}


	public static void main (String[] args,String text,int time)
	{
		DisappearDialog dm = new DisappearDialog(text, time);
		dm.start();
	}
}