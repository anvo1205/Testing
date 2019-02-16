package Function;

import org.openqa.selenium.JavascriptExecutor;

import Interface.I_MyDesigns;
import Interface.I_SignIn;
import Utility.*;

public class F_SignIn {
	public static void inputEmail(String email)
	{
		Utils.captureWebElement(I_SignIn.txt_Email).sendKeys(email);
	}
	
	public static void inputPassword(String password)
	{
		Utils.captureWebElement(I_SignIn.txt_Password).sendKeys(password);
	}
	
	public static void clickSignIn()
	{
		Utils.captureWebElement(I_SignIn.btn_SignIn).click();
	}
	
	public static void byPassGoogleCapcha()
	{
		((JavascriptExecutor) Constants.driver).executeScript("$('#g-recaptcha-response').val('test');");
		((JavascriptExecutor) Constants.driver).executeScript("$('#sign_in_form').submit();");
	}
	
	public static void clickSignUp()
	{
		Utils.waitUntilElementIsVisible(I_SignIn.lnk_Signup);
		Utils.captureWebElement(I_SignIn.lnk_Signup).click();
	}
	
	public static boolean verifySuccessfullySignIn()
	{
		return Utils.captureWebElement(I_MyDesigns.btn_Account).isDisplayed();
	}
	
	public static void SignIn(String email, String password) throws InterruptedException
	{
		inputEmail(email);
		inputPassword(password);
		byPassGoogleCapcha();
		//clickSignIn();
		Thread.sleep(2000);
	}
	
	public static boolean verifyEmailErrorMessage(String expectedMsg)
	{
		String msg = "";
		if (Constants.driver.findElements(I_SignIn.lbl_EmailError).size() != 0)
		{
			msg = Constants.driver.findElement(I_SignIn.lbl_EmailError).getText();
			return msg.matches(expectedMsg);
		}
		return false;
	}
	
	public static boolean verifyPasswordErrorMessage(String expectedMsg)
	{
		String msg = "";
		if (Constants.driver.findElements(I_SignIn.lbl_PasswordError).size() != 0)
		{
			msg = Constants.driver.findElement(I_SignIn.lbl_PasswordError).getText();
			return msg.matches(expectedMsg);
		}
		return false;
	}
}
