/*package com.graphics.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import AllGMSTC.SingleInstance;

import java.awt.Font;
import java.awt.Frame;
import java.awt.LayoutManager;
import java.awt.Toolkit;

import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JComponent;

import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.BindException;
import java.net.ServerSocket;
import java.net.Socket;
import java.awt.event.ActionEvent;
import javax.swing.border.EtchedBorder;
import javax.swing.JPasswordField;
import javax.swing.JProgressBar;
import javax.swing.JRootPane;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.border.MatteBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.JScrollPane;
import java.awt.event.KeyAdapter;

public class LoginPage extends JFrame {

	private static JFrame frame;
	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;

	*//**
	 * Launch the application.
	 *//*
	
	public static void main(String[] args) {
		
		singleInstacne();
		
        EventQueue.invokeLater(new Runnable() {
			public void run() {
				
				
				try {
					LoginPage frame = new LoginPage();
					frame.setVisible(true);
				    UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel"); //Windows Look and feel     

				   
					

				} catch ( Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	*//**
	 * Create the application.
	 *//*



	public LoginPage() throws IOException {
		
		setIconImage(Toolkit.getDefaultToolkit().getImage("src/test/resources/images/sm.jpg"));
		//setBackground(new Color(153, 102, 255));


		setTitle("Login Window");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			
			public void windowClosing(WindowEvent e) {
				int dialogButton = JOptionPane.YES_NO_OPTION;
				int value = JOptionPane.showConfirmDialog(null,"Do you want to Exit?", "WARNING", dialogButton);
				if (value == 0) {
					System.exit(0);
				}
               
            }
		});


		setBounds(0, 0, 600, 400);

		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		int jFrameWidth = this.getSize().width;
		int jFrameHieght = this.getSize().height;

		int locX = (dim.width-jFrameWidth)/2;
		int locY = (dim.height-jFrameWidth)/2;

		this.setLocation(locX, locY);

		contentPane = new JPanel();
		//contentPane.setBackground(new Color(153, 255, 153));
		contentPane.setBackground(new Color(0, 255, 204));
		contentPane.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		setResizable(false);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setUndecorated(false);

		JPanel panel = new JPanel();
		panel.setBounds(148, 89, 424, 239);
		panel.setBackground(new Color(204, 204, 255));
		panel.setForeground(new Color(0, 204, 204));
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		contentPane.add(panel);
		panel.setLayout(null);

		final JLabel lblNewLabel = new JLabel("Username :\r\n");
		lblNewLabel.setBounds(96, 80, 165, 34);
		lblNewLabel.setIcon(new ImageIcon("src/test/resources/images/username.png"));
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 16));
		panel.add(lblNewLabel);


		textField = new JTextField();
		textField.setBounds(200, 85, 189, 26);
		textField.setFont(new Font("Tahoma", Font.PLAIN, 13));
		panel.add(textField);
		textField.setColumns(10);

		JLabel lblPassword = new JLabel(" Password :");
		lblPassword.setBounds(96, 136, 165, 34);
		lblPassword.setIcon(new ImageIcon("src/test/resources/images/password.png"));
		lblPassword.setFont(new Font("Times New Roman", Font.BOLD, 16));
		panel.add(lblPassword);

		passwordField = new JPasswordField();

		passwordField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {

				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					final String userName = textField.getText();
					final String passWord = passwordField.getText();

					if (userName.equals("sonim1") && passWord.equals("sonim1")
							|| userName.equals("sonim2") && passWord.equals("sonim2")) {
						
						JOptionPane.showMessageDialog(null, "Login Successful!", "INFORMATION",
								JOptionPane.INFORMATION_MESSAGE);
						ToolFrame.main(null);
						setVisible(false);
					} else if (userName.equals("") || passWord.equals("")) {
						JOptionPane.showMessageDialog(null, "Please enter username and password");
					} else {
						JOptionPane.showMessageDialog(null, "Invalid Username or Password!", "INFORMATION",
								JOptionPane.INFORMATION_MESSAGE);
						textField.setText("");
						passwordField.setText("");
					}

				}

			}
		});

		final JButton login = new JButton("Login");
		login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				final String userName = textField.getText();
				final String passWord = passwordField.getText();

				if (userName.equals("sonim1") && passWord.equals("sonim1")
						|| userName.equals("sonim2") && passWord.equals("sonim2")) {
					JOptionPane.showMessageDialog(null, "Login Successful!", "INFORMATION",
							JOptionPane.INFORMATION_MESSAGE);
					ToolFrame.main(null);
					setVisible(false);
				} else if (userName.equals("") || passWord.equals("")) {
					JOptionPane.showMessageDialog(null, "Please enter username and password");
				} else {
					JOptionPane.showMessageDialog(null, "Invalid username or password!", "INFORMATION",
							JOptionPane.INFORMATION_MESSAGE);
					textField.setText("");
					passwordField.setText("");
				}



			}
		});

		login.setBounds(200, 200, 82, 23);
		login.setBackground(new Color(50, 205, 50));
		login.setFont(new Font("Tahoma", Font.BOLD, 14));				



		passwordField.setBounds(200, 145, 189, 25);
		panel.add(passwordField);



		panel.add(login);	

		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBounds(292, 200, 86, 23);
		btnCancel.setBackground(new Color(51, 102, 255));
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int dialogButton = JOptionPane.YES_NO_OPTION;
				int value = JOptionPane.showConfirmDialog(null,"Do you want to Exit?", "WARNING", dialogButton);
				if (value == 0) {
					System.exit(0);
				}

			}
		});
		btnCancel.setFont(new Font("Tahoma", Font.BOLD, 14));
		panel.add(btnCancel);



		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setBounds(127, 21, 120, -4);
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setBounds(10, 11, 218, 74);
		lblNewLabel_3.setIcon(new ImageIcon("src/test/resources/images/Sonim_Logo.png"));
		contentPane.add(lblNewLabel_3);

		JLabel lblNewLabel_2 = new JLabel("S-MAT Login");
		lblNewLabel_2.setForeground(new Color(128, 0, 0));
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 26));
		lblNewLabel_2.setBounds(284, 21, 218, 43);
		contentPane.add(lblNewLabel_2);

	}

	*//**
	 * Initialize the contents of the frame.
	 *//*
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		

	}
	
	public static void singleInstacne() {
		
		Thread t=new Thread(new Runnable() {
			
		
			public void run() {
			
		SingleInstance st=new SingleInstance();
		st.main(null);

			}
		});
		t.start();
		
		
	}
	




}
*/