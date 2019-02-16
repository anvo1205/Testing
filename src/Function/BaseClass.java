package Function;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import data.Constants;

public class BaseClass {

	public static WebDriver driver;
	public static Properties pros;
	public static WebDriverWait wait;
	
	/**
	 * This is constructor to load the configure file
	 */
	public BaseClass(){
		try {
			pros = new Properties();
			FileInputStream configureFile = new FileInputStream(System.getProperty("user.dir")+ "/src/main/java/data/config.properties");
			pros.load(configureFile);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * This method will instantiate WebDriver object based on value
	 * from configure file, set timeout, maximize window
	 */
	public void init()
	{
		String platform = pros.getProperty("platform");
		String browser = pros.getProperty("browser");
				
		if (platform.equalsIgnoreCase("window"))
		{
			if(browser.equalsIgnoreCase("chrome"))
			{
				System.setProperty("webdriver.chrome.driver", Constants.CHROME_DRIVER_PATH_WINDOW);
				driver = new ChromeDriver();
			}
			else if(browser.equalsIgnoreCase("headless chrome"))
			{
				ChromeOptions options = new ChromeOptions();  
				options.addArguments("--headless", "--disable-gpu", "--ignore-certificate-errors"); 
				driver = new ChromeDriver(options);
			}
			else if(browser.equalsIgnoreCase("firefox"))
			{
				System.setProperty("webdriver.gecko.driver", Constants.FIREFOX_DRIVER_PATH_WINDOW);
				driver = new FirefoxDriver();
			}	
		}
		
		else if (platform.equalsIgnoreCase("mac"))
		{
			if(browser.equalsIgnoreCase("chrome"))
			{
				System.setProperty("webdriver.chrome.driver", Constants.CHROME_DRIVER_PATH_MAC);
				driver = new ChromeDriver();
			}	
			else if(browser.equalsIgnoreCase("firefox"))
			{
				System.setProperty("webdriver.gecko.driver", Constants.FIREFOX_DRIVER_PATH_MAC);
				driver = new FirefoxDriver();
			}	
		}
		
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(Constants.IMPLICIT_WAIT, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(Constants.IMPLICIT_WAIT, TimeUnit.SECONDS);
		wait = new WebDriverWait(driver, Constants.EXPLICIT_WAIT);
		
		driver.get(pros.getProperty("url"));
		
	}
}
