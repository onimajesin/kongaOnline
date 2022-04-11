package kongaprod.kongaOnline;
//import kongaprod.kongaOnline.SendMailSSLWithAttachment;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.AfterClass;

import java.util.*;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsNot.not;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.base.Verify;

import junit.framework.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.Keys;
import java.util.concurrent.TimeUnit;

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

import java.net.MalformedURLException;
import java.net.URL;

public class LoginAddToCartTest {

	private static WebDriver driver;
	private static Map<String, Object> vars;
	static JavascriptExecutor js;

	@Before
	public void openBrowser() {
		System.setProperty("webdriver.chrome.driver", "C:\\Drivers\\chromedriver_win32\\chromedriver.exe");
//		ChromeOptions options = new ChromeOptions();
//		options.addArguments("headless");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		js = (JavascriptExecutor) driver;
		vars = new HashMap<String, Object>();
	}

	// public void setUp() {
	// driver = new ChromeDriver();
	// js = (JavascriptExecutor) driver;
	// vars = new HashMap<String, Object>();
	// }
	@After
	public void tearDown() {
		driver.quit();
		// Create object of Property file

	}

	// @AfterClass
	public static void sendEmail() {
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
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("sulaimon.buhari@konga.com"));

			// Add the subject link
			message.setSubject("Automation Test Report");

			// Create object to add multimedia type content
			BodyPart messageBodyPart1 = new MimeBodyPart();

			// Set the body of email
			messageBodyPart1.setText(
					"Hello, The test report as at the moment. Kindly check the attachment file to get status report on the automation test doene");

			// Create another object to add another content
			MimeBodyPart messageBodyPart2 = new MimeBodyPart();

			// Mention the file which you want to send
			String filename = "C:\\Users\\Sulaimon.Buhari\\eclipse-workspace\\kongaOnline\\target\\surefire-reports\\kongaprod.kongaOnline.LoginAddToCartTest.txt";

//			Scanner sc = null ;
//			sc= new Scanner(filename);
//	         String input;
//	         StringBuffer sb = new StringBuffer();
//	         while (sc.hasNextLine()) {
//	            input = sc.nextLine();
//	            sb.append(input+" ");
//	         }
//			

			// Create data source and pass the filename
			DataSource source = new FileDataSource(filename);

			// set the handler
			messageBodyPart2.setDataHandler(new DataHandler(source));

			// set the file
			messageBodyPart2.setFileName(" Test Automation Report");

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

	// new SendMailSSLWithAttachment();

	@SuppressWarnings("deprecation")

	@Test
	public void login() throws Exception {
		// driver.get("https://staging.konga.com/");
		driver.get("https://www.konga.com/");
		driver.manage().window().setSize(new Dimension(1062, 824));
		driver.findElement(By.linkText("Login / Signup")).click();
		driver.findElement(By.id("username")).click();
		// driver.findElement(By.id("username")).sendKeys("sulaimon.buhari@konga.com");
		driver.findElement(By.id("username")).sendKeys("teststore@konga.com");
		driver.findElement(By.id("password")).click();
		// driver.findElement(By.id("password")).sendKeys("password$1");
		driver.findElement(By.id("password")).sendKeys("loadtest123");
		driver.findElement(By.cssSelector(".\\_988cf_1aDdJ")).click();

		Thread.sleep(3000);

		WebElement loginButton = driver.findElement(By.cssSelector(".\\_12da4_1baq-"));
		// Assert.assertEquals(true, loginButton.isDisplayed());
		//assertTrue(loginButton.isDisplayed());
		assertTrue(loginButton.getText().equalsIgnoreCase("Login Successful"));
		System.out.println(loginButton.getText());

	}

	@Test
	public void siteAvailiablity() {
		driver.get("https://www.konga.com/");
		String title = driver.getTitle();
		System.out.println(title);
		// Verify.verify(title.equalsIgnoreCase("Buy Phones, Fashion, Electronics in
		// Nigeria_Konga Online Shopping | Konga Online Shopping"));
		assertTrue(title.equalsIgnoreCase(
				"Buy Phones, Fashion, Electronics in Nigeria_Konga Online Shopping | Konga Online Shopping"));
		// assertTrue("Buy Phones, Fashion, Electronics in Nigeria_Konga Online Shopping
		// | Konga Online Shopping", title.equalsIgnoreCase("##Buy Phones, Fashion,
		// Electronics in Nigeria_Konga Online Shopping | Konga Online Shopping"));
	}

	@Test
	public void addToCart() throws Exception {
		// driver.get("https://staging.konga.com/");
		driver.get("https://www.konga.com/");
		driver.manage().window().setSize(new Dimension(1062, 824));
		driver.findElement(By.linkText("Login / Signup")).click();
		driver.findElement(By.id("username")).click();
		// driver.findElement(By.id("username")).sendKeys("sulaimon.buhari@konga.com");
		driver.findElement(By.id("username")).sendKeys("teststore@konga.com");
		driver.findElement(By.id("password")).click();
		// driver.findElement(By.id("password")).sendKeys("password$1");
		driver.findElement(By.id("password")).sendKeys("loadtest123");
		driver.findElement(By.cssSelector(".\\_988cf_1aDdJ")).click();

		Thread.sleep(3000);

		WebElement loginButton = driver.findElement(By.cssSelector(".\\_12da4_1baq-"));
		// Assert.assertEquals(true, loginButton.isDisplayed());
		assertTrue(loginButton.isDisplayed());
		System.out.println(loginButton.getText());

		{
			WebElement element = driver.findElement(By.cssSelector(".e69c3_LLZb4:nth-child(1) .f5e10_VzEXF"));
			Actions builder = new Actions(driver);
			builder.moveToElement(element).perform();
		}
		{
			WebElement element = driver.findElement(By.tagName("body"));
			Actions builder = new Actions(driver);
			builder.moveToElement(element, 0, 0).perform();
		}
		driver.findElement(By.cssSelector(".f6ed2_25oVd #jsSearchInput")).click();
		driver.findElement(By.cssSelector(".f6ed2_25oVd #jsSearchInput")).sendKeys("cases");
		driver.findElement(By.cssSelector(".f6ed2_25oVd > .fdd83_39Iap")).click();
		js.executeScript("window.scrollTo(0,690.4000244140625)");
		js.executeScript("window.scrollTo(0,856.7999877929688)");
		js.executeScript("window.scrollTo(0,1534.4000244140625)");
		// driver.findElement(By.cssSelector(".bbe45_3oExY:nth-child(9)
		// .\\_0a08a_3czMG")).isDisplayed()
		driver.findElement(By.cssSelector(".bbe45_3oExY:nth-child(9) .\\_0a08a_3czMG")).click();

		try {

			if (driver.findElement(By.linkText("Proceed to Checkout")).isDisplayed()) {
				String cartMessasge = driver.findElement(By.linkText("Proceed to Checkout")).getText();
				System.out.println("Add to Cart Succesfful ");
				System.out.println(cartMessasge);
//				driver.findElement(By.linkText("Proceed to Checkout")).click();
//				js.executeScript("window.scrollTo(0,0)");
//				driver.findElement(By.cssSelector("button:nth-child(1) > span")).click();
//				String removeCart = driver.findElement(By.className("_12da4_1baq-")).getText();
//				System.out.println(removeCart);
			}

		} catch (Exception exp2) {

		}

	}
	
	@Test
	public void removeCartItem() throws Exception {
		// driver.get("https://staging.konga.com/");
		driver.get("https://www.konga.com/");
		driver.manage().window().setSize(new Dimension(1062, 824));
		driver.findElement(By.linkText("Login / Signup")).click();
		driver.findElement(By.id("username")).click();
		// driver.findElement(By.id("username")).sendKeys("sulaimon.buhari@konga.com");
		driver.findElement(By.id("username")).sendKeys("teststore@konga.com");
		driver.findElement(By.id("password")).click();
		// driver.findElement(By.id("password")).sendKeys("password$1");
		driver.findElement(By.id("password")).sendKeys("loadtest123");
		driver.findElement(By.cssSelector(".\\_988cf_1aDdJ")).click();

		Thread.sleep(3000);

		WebElement loginButton = driver.findElement(By.cssSelector(".\\_12da4_1baq-"));
		// Assert.assertEquals(true, loginButton.isDisplayed());
		assertTrue(loginButton.isDisplayed());
		System.out.println(loginButton.getText());

		{
			WebElement element = driver.findElement(By.cssSelector(".e69c3_LLZb4:nth-child(1) .f5e10_VzEXF"));
			Actions builder = new Actions(driver);
			builder.moveToElement(element).perform();
		}
		{
			WebElement element = driver.findElement(By.tagName("body"));
			Actions builder = new Actions(driver);
			builder.moveToElement(element, 0, 0).perform();
		}
		driver.findElement(By.cssSelector(".f6ed2_25oVd #jsSearchInput")).click();
		driver.findElement(By.cssSelector(".f6ed2_25oVd #jsSearchInput")).sendKeys("cases");
		driver.findElement(By.cssSelector(".f6ed2_25oVd > .fdd83_39Iap")).click();
		js.executeScript("window.scrollTo(0,690.4000244140625)");
		js.executeScript("window.scrollTo(0,856.7999877929688)");
		js.executeScript("window.scrollTo(0,1534.4000244140625)");
		// driver.findElement(By.cssSelector(".bbe45_3oExY:nth-child(9)
		// .\\_0a08a_3czMG")).isDisplayed()
		driver.findElement(By.cssSelector(".bbe45_3oExY:nth-child(9) .\\_0a08a_3czMG")).click();

		try {

			if (driver.findElement(By.linkText("Proceed to Checkout")).isDisplayed()) {
				String cartMessasge = driver.findElement(By.linkText("Proceed to Checkout")).getText();
				System.out.println("Add to Cart Succesfful ");
				System.out.println(cartMessasge);
				driver.findElement(By.linkText("Proceed to Checkout")).click();
				js.executeScript("window.scrollTo(0,0)");
				driver.findElement(By.cssSelector("button:nth-child(1) > span")).click();
				String removeCart = driver.findElement(By.className("_12da4_1baq-")).getText();
				System.out.println(removeCart);
			}

		} catch (Exception exp2) {

		}

	}

}
