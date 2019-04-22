package AllGMSTC;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import application.AllQA;
import javafx.application.Platform;

public class EmailAttachmentSender {


	static String recipient;

	public static void sendEmailWithAttachments(String host, String port,
			final String userName, final String password, String toAddress,
			String subject, String message, String[] attachFiles)
					throws AddressException, MessagingException {
		// sets SMTP server properties
		Properties properties = new Properties();
		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.port", port);
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.user", userName);
		properties.put("mail.password", password);

		// creates a new session with an authenticator
		Authenticator auth = new Authenticator() {
			public PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(userName, password);
			}
		};
		Session session = Session.getInstance(properties, auth);

		// creates a new e-mail message
		//Testing GIT
		Message msg = new MimeMessage(session);

		msg.setFrom(new InternetAddress(userName));
		String[] recipientList = recipient.split(",");
		//	InternetAddress[] toAddresses = { new InternetAddress(toAddress)};
		InternetAddress[] recipientAddress = new InternetAddress[recipientList.length];

		int counter = 0;
		for (String recipient : recipientList) {
			recipientAddress[counter] = new InternetAddress(recipient.trim());
			counter++;
		}


		msg.setRecipients(Message.RecipientType.TO, recipientAddress);
		msg.setSubject(subject);
		msg.setSentDate(new Date());

		// creates message part
		MimeBodyPart messageBodyPart = new MimeBodyPart();
		messageBodyPart.setContent(message, "text/html");

		// creates multi-part......
		Multipart multipart = new MimeMultipart();
		multipart.addBodyPart(messageBodyPart);

		// adds attachments
		if (attachFiles != null && attachFiles.length > 0) {
			for (String filePath : attachFiles) {
				MimeBodyPart attachPart = new MimeBodyPart();

				try {
					attachPart.attachFile(filePath);
				} catch (IOException ex) {
					ex.printStackTrace();
				}

				multipart.addBodyPart(attachPart);
			}
		}

		// sets the multi-part as e-mail's content
		msg.setContent(multipart);

		// sends the e-mail
		Transport.send(msg);

	}

	/**
	 * Test sending e-mail with attachments
	 */
	public static void main(String[] args){

		// SMTP info
		String host = "smtp.gmail.com";
		String port = "587";
		String mailFrom = "smatreports@gmail.com";
		String password = "sonim123";

		// message info

		Date today = Calendar.getInstance().getTime();
		recipient = AllQA.EMAILID;
		
		
		System.out.println("Email------------->"+recipient);

		//	String mailTo = "sashi.p@sonimtech.com";
		String subject = "SMAT Execution Report"+":"+today;
		String message = "Hi All, Please find the S-MAT execution report in the mail attachment.";

		// attachments
		String[] attachFiles = new String[1];
		attachFiles[0] =AllQA.EMAILREPORT;
		System.out.println("Report------------->"+attachFiles[0]);

		//attachFiles[1] = "C:\\Users\\sashi.p\\Desktop\\Dipu\\test.jpg";
		//attachFiles[2] = "C:\\Users\\sashi.p\\Desktop\\Dipu\\test.jpg";

		try {
			sendEmailWithAttachments(host, port, mailFrom, password, recipient,
					subject, message, attachFiles);


			System.out.println("Email sent.");
		} catch (Exception ex) {
			System.out.println("Could not send email.");
			ex.printStackTrace();
		}
	}
}