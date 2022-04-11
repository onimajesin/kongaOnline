package kongaprod.kongaOnline;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsNot.not;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
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
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Alert;
import org.openqa.selenium.Keys;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.net.MalformedURLException;
import java.net.URL;
public class RemoveItemCart {
	
  private WebDriver driver;
  private Map<String, Object> vars;
  JavascriptExecutor js;
  @Before
  public void setUp() {
	  System.setProperty("webdriver.chrome.driver", "C:\\Drivers\\chromedriver_win32\\chromedriver.exe");
//		ChromeOptions options = new ChromeOptions();
//		options.addArguments("headless");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		js = (JavascriptExecutor) driver;
		vars = new HashMap<String, Object>();
  }
  @After
  public void tearDown() {
    driver.quit();
  }
  @Test
  public void itemRemoveCart() {
    driver.get("https://www.konga.com/");
    driver.manage().window().setSize(new Dimension(1062, 824));
    driver.findElement(By.linkText("Login / Signup")).click();
    driver.findElement(By.id("username")).click();
    driver.findElement(By.id("username")).sendKeys("teststore@konga.com");
    driver.findElement(By.id("password")).click();
    driver.findElement(By.id("password")).sendKeys("loadtest123");
    driver.findElement(By.cssSelector(".\\_988cf_1aDdJ")).click();
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
    driver.findElement(By.cssSelector(".f6ed2_25oVd > .fdd83_39Iap > svg")).click();
    js.executeScript("window.scrollTo(0,1321.5999755859375)");
    driver.findElement(By.cssSelector(".bbe45_3oExY:nth-child(7) .\\_0a08a_3czMG")).click();
    {
      WebElement element = driver.findElement(By.cssSelector(".bbe45_3oExY:nth-child(10) .f5e10_VzEXF"));
      Actions builder = new Actions(driver);
      builder.moveToElement(element).perform();
    }
    {
      WebElement element = driver.findElement(By.tagName("body"));
      Actions builder = new Actions(driver);
      builder.moveToElement(element, 0, 0).perform();
    }
    driver.findElement(By.linkText("Proceed to Checkout")).click();
    js.executeScript("window.scrollTo(0,0)");
    driver.findElement(By.cssSelector("button:nth-child(1) > span")).click();
  }
}
