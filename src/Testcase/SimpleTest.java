package Testcase;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import Utility.Constants;

public class SimpleTest {
	@Test
	public void test() {
		
		//Initialize chrome driver
		System.setProperty(Constants.chromeDriver, Constants.chromeDriverPath);
		WebDriver driver = new ChromeDriver();
		WebDriverWait wait = new WebDriverWait(Constants.driver, 10);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.get(Constants.Free_Sign_Up_Url);
		driver.manage().window().maximize();
		
		//Sign up
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MMddhhmmss");
		String dateAsString = simpleDateFormat.format(new Date());
		String email = "test" + dateAsString + "@autotest.com";
		
		driver.findElement(By.id("user_first_name")).sendKeys("Test");
		driver.findElement(By.id("user_last_name")).sendKeys("Test");
		driver.findElement(By.id("user_email")).sendKeys(email);
		driver.findElement(By.id("user_password")).sendKeys("123456");
		driver.findElement(By.id("btn_register")).click();
	}
}
