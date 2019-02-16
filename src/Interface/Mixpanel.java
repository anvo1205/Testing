package Interface;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Utility.Constants;

public class Mixpanel {
	public static int waitTime = 15;
//	public static WebElement element = null;
	
	//Login page
	public static By txt_UserName = By.id("id_email");
	public static By txt_Password = By.id("id_password");
	public static By btn_Login = By.xpath("//input[@value='Log in']");
	
	//User page
	public static By txt_SearchUser = By.id("free_search_box");
	
	public static WebElement lnk_UserEmail(String userEmail)
	{
		WebDriverWait wait = new WebDriverWait(Constants.driver, waitTime);
		WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@title='" + userEmail + "']")));
		return element;
	}
	
	public static WebElement lbl_EventName(String eventName)
	{
		WebDriverWait wait = new WebDriverWait(Constants.driver, waitTime);
		WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(
										By.xpath("//div[@class='event_info has_properties']//span[@class='event_name' and text()='" + eventName + "']")));
		return element;
	}
		
		

}
