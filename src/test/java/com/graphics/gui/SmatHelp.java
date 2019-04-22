package com.graphics.gui;

import java.awt.Button;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Desktop;

public class SmatHelp {

	public static JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SmatHelp window = new SmatHelp();
					window.frame.setVisible(true);
					
				      

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @wbp.parser.entryPoint
	 */
	public SmatHelp() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setUndecorated(true);

		JLabel lblNewLabel = new JLabel("Welcome to S-MAT help:");
		lblNewLabel.setBounds(10, 11, 180, 17);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
		frame.getContentPane().add(lblNewLabel);

		Button button = new Button("Close");
		button.setBounds(178, 268, 70, 22);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();
			}
		});
		button.setFont(new Font("Dialog", Font.BOLD, 12));
		frame.getContentPane().add(button);

		JLabel lblNewLabel_1 = new JLabel("Documentation: ");
		lblNewLabel_1.setBounds(10, 52, 180, 17);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		frame.getContentPane().add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("For quick start and basic functionality, Please refer User manual.");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_2.setBounds(10, 75, 356, 34);
		frame.getContentPane().add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("Help:\r\n");
		lblNewLabel_3.setBounds(10, 156, 147, 17);
		lblNewLabel_3.setToolTipText("");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 13));
		frame.getContentPane().add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel("For any queries, please drop a mail to ");
		lblNewLabel_4.setBounds(10, 175, 264, 17);
		frame.getContentPane().add(lblNewLabel_4);

		JLabel lblNewLabel_5 = new JLabel("smathelp@gmail.com");
		lblNewLabel_5.setBounds(10, 192, 180, 17);
		frame.getContentPane().add(lblNewLabel_5);

		JLabel lblNewLabel_6 = new JLabel("");
		lblNewLabel_6.setIcon(new ImageIcon("src/test/resources/images/S.jpg"));
		lblNewLabel_6.setBounds(354, 0, 70, 98);
		frame.getContentPane().add(lblNewLabel_6);
		
		Button button_2 = new Button("User Manual ");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				 Desktop desktop = Desktop.getDesktop();
			     File dirToOpen = null;
			     try {
			         dirToOpen = new File("src/test/resources/DOCS/UM.pdf");
			         desktop.open(dirToOpen);
			     } catch (Exception ex) {
			         JOptionPane.showMessageDialog(null, "Pdf reader is not installed in your machine.");
			     }
				
			}
				
				
				
			
		});
		button_2.setFont(new Font("Dialog", Font.BOLD, 12));
		button_2.setBounds(10, 115, 128, 22);
		frame.getContentPane().add(button_2);
	}
}