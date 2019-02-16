package Function;

import Interface.I_SignUp;
import Utility.*;

public class F_SignUp {
	/****************Input user information*********************/
	public static boolean verifySignUpPageDisplay()
	{
		boolean isDisplay = Utils.captureWebElement(I_SignUp.lbl_SignUpPageTitle).isDisplayed();
		if (isDisplay)
		{
			return true;
		}
		else
		{
			System.out.println("Sign up page is not displayed");
			return false;
		}
	}
	
	public static void inputFirstName(String fName) {
		Utils.captureWebElement(I_SignUp.txt_FirstName).sendKeys(fName);
	}

	public static void inputLastName(String lName) {
		Utils.captureWebElement(I_SignUp.txt_LastName).sendKeys(lName);
	}

	public static void inputEmail(String email) {
		Utils.captureWebElement(I_SignUp.txt_Email).sendKeys(email);
	}

	public static void inputPassword(String password) {
		Utils.captureWebElement(I_SignUp.txt_Password).sendKeys(password);
	}

	public static void clickRegister() {
		Utils.captureWebElement(I_SignUp.btn_Register).click();
	}
	
	// Select organization
	public static void clickSelfEmployedOrganization() throws InterruptedException
	{
		Utils.captureWebElement(I_SignUp.lnk_SelfEmployedOrg).click();
		Thread.sleep(2000);
	}
	
	public static void clickOtherOrganization() throws InterruptedException
	{
		Utils.captureWebElement(I_SignUp.lnk_OthersOrg).click();
		Thread.sleep(2000);
	}
	
	// Select role
	public static void clickITEngineeringRole() throws InterruptedException
	{
		Utils.captureWebElement(I_SignUp.lnk_ITEngineeringRole).click();
		Thread.sleep(2000);
	}
	
	// Select template category
	public static void clickResumeCategory() throws InterruptedException
	{
		Utils.captureWebElement(I_SignUp.lnk_ResumeCategory).click();
		Thread.sleep(2000);
	}
	
	// Select 3 templates or more
	public static void selectResumes()
	{
		Utils.captureWebElement(I_SignUp.lnk_Resume1).click();
		Utils.captureWebElement(I_SignUp.lnk_Resume2).click();
		Utils.captureWebElement(I_SignUp.lnk_Resume3).click();
	}
	
	//Go to Design page
	public static void clickContinue() throws InterruptedException
	{
		Utils.captureWebElement(I_SignUp.btn_Countinue).click();
		Thread.sleep(10000);
	}
	
	public static void waitUntilCustomizeTemplateDialogDisappear() throws InterruptedException
	{
		int i = 0;
		Thread.sleep(2000);
		while (i != Constants.TimeOut)
		{
			if(Utils.captureWebElement(I_SignUp.fra_LoadingTemplate).isDisplayed())
			{
				Thread.sleep(1000);
				i++;
			}
			else
			{
				break;
			}
		}
	}

	//Verify Sign up result
	public static boolean verifySignUpSuccessfully(String successMsg) {
		String msg = "";
		if (Utils.captureWebElement(I_SignUp.lbl_GreetingMsg).isDisplayed()) {
			msg = Utils.captureWebElement(I_SignUp.lbl_GreetingMsg).getText();
			return msg.matches(successMsg);
		} 
		else {
			return false;
		}
	}
	
	//Verify selected template displays on recommended section
	public static boolean verifySelectedTemplateExist()
	{
		return Utils.captureWebElement(I_SignUp.lnk_Resume1).isDisplayed()
			&& Utils.captureWebElement(I_SignUp.lnk_Resume2).isDisplayed()
			&& Utils.captureWebElement(I_SignUp.lnk_Resume3).isDisplayed();
	}

	//Verify email error message display when input invalid information
	public static boolean verifyEmailErrorMessage(String errorMsg) {
		String error = "";
		if (Utils.captureWebElements(I_SignUp.lbl_EmailError).size() != 0) {
			error = Utils.captureWebElement(I_SignUp.lbl_EmailError).getText();
			return error.matches(errorMsg);
		} 
		else {
			return false;
		}
	}
}
