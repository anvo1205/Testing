package Testcase;

import static org.testng.Assert.assertTrue;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import Utility.Constants;

public class SingleTest extends BrowserStackTestNGTest{
  @Test
  public void test() throws InterruptedException {
		Constants.driver.get("https://infograph.venngage.beer/register");
		Constants.driver.manage().window().maximize();

		// Sign up
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MMddhhmmss");
		String dateAsString = simpleDateFormat.format(new Date());
		String email = "test" + dateAsString + "@autotest.com";

		Thread.sleep(2000);
		Constants.driver.findElement(By.id("user_first_name")).sendKeys("Test");
		Constants.driver.findElement(By.id("user_last_name")).sendKeys("Test");
		Constants.driver.findElement(By.id("user_email")).sendKeys(email);
		Constants.driver.findElement(By.id("user_password")).sendKeys("123456");
		Thread.sleep(2000);
		Constants.driver.findElement(By.id("btn_register")).click();
		String welcomeMsg = Constants.driver.findElement(By.xpath("//div[@class='styles__greeting--3psym']/h3")).getText();
		assertTrue(welcomeMsg.equals("Let's personalize your page, Test!"));
  }
}
