package kongaprod.kongaOnline;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;
import java.util.Scanner;
import java.util.stream.Stream;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class SendMailSSLWithAttachment {

	public static void main(String[] args) throws Exception {

		SendMailSSLWithAttachment check = new SendMailSSLWithAttachment();

		// Create object of Property file
		Properties props = new Properties();

		// this will set host of server- you can change based on your requirement
		props.put("mail.smtp.host", "smtp.gmail.com");

		// set the port of socket factory
		props.put("mail.smtp.socketFactory.port", "465");

		// set socket factory
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

		// set the authentication to true
		props.put("mail.smtp.auth", "true");

		// set the port of SMTP server
		props.put("mail.smtp.port", "587");

		// This will handle the complete authentication
		Session session = Session.getDefaultInstance(props,

				new javax.mail.Authenticator() {

					protected PasswordAuthentication getPasswordAuthentication() {

						return new PasswordAuthentication("automatedtest@konga.com", "lpbrhtavkxkunieq");

					}

				});

		try {

			// Create object of MimeMessage class
			Message message = new MimeMessage(session);

			// Set the from address
			message.setFrom(new InternetAddress("automatedtest@konga.com"));

			// Set the recipient address
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("test@konga.com"));

			// Create object to add multimedia type content

			// Add the subject link
			message.setSubject("Automation Test Report");

//			String file = "C:\\Users\\Sulaimon.Buhari\\eclipse-workspace\\kongaOnline\\target\\surefire-reports\\kongaprod.kongaOnline.LoginAddToCartTest.txt";
//			Path path = Paths.get(file);
//			Stream<String> lines = Files.lines(path);
//			lines.forEach(s -> System.out.println(s));
//			lines.close();

			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
			LocalDateTime now = LocalDateTime.now();
			System.out.println(dtf.format(now));
			
			StringBuffer msgContent = new StringBuffer("");

			BodyPart messageBodyPart1 = new MimeBodyPart();
			File file = new File(
					"C:\\Users\\Sulaimon.Buhari\\eclipse-workspace\\kongaOnline\\target\\surefire-reports\\kongaprod.kongaOnline.LoginAddToCartTest.txt");
			BufferedReader br = new BufferedReader(new FileReader(file));
			String sttr;
			String strMessage;
			while ((sttr = br.readLine()) != null)

			{
				//sttr = sttr.concat(sttr);
				msgContent.append(sttr+ System.lineSeparator());
				System.out.println(sttr);

			}
			strMessage = msgContent.toString();
			System.out.println(strMessage);
			System.out.println("checking something..................");
			
			messageBodyPart1.setText("Please find attached and below the test report as at   "+ dtf.format(now) + System.lineSeparator() + strMessage );

			//messageBodyPart1.setText("Please find the test report today as at  " + " " + dtf.format(now) + "  "
			//		+ "in the attached document");

			// Create another object to add another content
			MimeBodyPart messageBodyPart2 = new MimeBodyPart();

			// Mention the file which you want to send
			String filename = "C:\\Users\\Sulaimon.Buhari\\eclipse-workspace\\kongaOnline\\target\\surefire-reports\\kongaprod.kongaOnline.LoginAddToCartTest.txt";

			// Create data source and pass the filename
			DataSource source = new FileDataSource(filename);

			// set the handler
			messageBodyPart2.setDataHandler(new DataHandler(source));

			// set the file
			messageBodyPart2.setFileName("Automation Report as at" + "   " + dtf.format(now));

			// Create object of MimeMultipart class
			Multipart multipart = new MimeMultipart();

			// add body part 1
			multipart.addBodyPart(messageBodyPart2);

			// add body part 2
			multipart.addBodyPart(messageBodyPart1);

			// set the content
			message.setContent(multipart);

			// finally send the email
			Transport.send(message);

			System.out.println("=====Email Sent=====");

		} catch (MessagingException e) {

			throw new RuntimeException(e);

		}

	}

	public String mess() throws Exception {

		File file = new File(
				"C:\\Users\\Sulaimon.Buhari\\eclipse-workspace\\kongaOnline\\target\\surefire-reports\\kongaprod.kongaOnline.LoginAddToCartTest.txt");
		BufferedReader br = new BufferedReader(new FileReader(file));
		String sttr;
		while ((sttr = br.readLine()) != null)

		{
			sttr.concat(sttr);
			System.out.println(sttr);

		}

		return sttr;

	}

	// mailMessage = sttr;

	// Set the body of email

	// Print the string

}
