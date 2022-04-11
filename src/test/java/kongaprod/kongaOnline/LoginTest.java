package kongaprod.kongaOnline;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import java.util.*;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import static org.junit.Assert.*;
import java.util.concurrent.TimeUnit;
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
import junit.framework.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.Keys;

import java.net.MalformedURLException;
import java.net.URL;

public class LoginTest {
	

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

	//public void setUp() {
	// driver = new ChromeDriver();
	// js = (JavascriptExecutor) driver;
	// vars = new HashMap<String, Object>();
	//}
	@After
	public void tearDown() {
	 driver.quit();
	}

	@SuppressWarnings("deprecation")
	
	
	//@Test
	public void login() throws Exception {	
	// driver.get("https://staging.konga.com/");
	 driver.get("https://www.konga.com/");
	 driver.manage().window().setSize(new Dimension(1062, 824));
	 driver.findElement(By.linkText("Login / Signup")).click();
	 driver.findElement(By.id("username")).click();
	 //driver.findElement(By.id("username")).sendKeys("sulaimon.buhari@konga.com");
	 driver.findElement(By.id("username")).sendKeys("teststore@konga.com");
	 driver.findElement(By.id("password")).click();
	 //driver.findElement(By.id("password")).sendKeys("password$1");
	 driver.findElement(By.id("password")).sendKeys("loadtest123");
	 driver.findElement(By.cssSelector(".\\_988cf_1aDdJ")).click();
	 
		Thread.sleep(2000);
		
	WebElement loginMessage = driver.findElement(By.cssSelector(".\\_12da4_1baq-"));
	Assert.assertEquals(true, loginMessage.isDisplayed());
	System.out.println(loginMessage.getText());
	}
}
