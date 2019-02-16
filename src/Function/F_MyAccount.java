package Function;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import Interface.I_MyAccount;
import Interface.I_MyDesigns;
import Utility.Constants;
import Utility.Utils;

public class F_MyAccount {

	public static void goToManageProfile() throws InterruptedException {
		Utils.captureWebElement(I_MyDesigns.btn_Account).click();
		Thread.sleep(2000);
		Utils.waitUntilElementIsVisible(I_MyDesigns.lnk_MyAccount);
		Utils.captureWebElement(I_MyDesigns.lnk_MyAccount).click();
	}

	/*
	 * ***********************
	 * Change Email actions
	 *************************
	 **/
	public static void clickChangeEmail() {
		Utils.captureWebElement(I_MyAccount.btn_ChangeEmail).click();
	}

	public static void inputNewEmail(String newEmail) {
		Utils.inputValueIntoTextbox(I_MyAccount.txt_NewEmail, newEmail);
	}

	public static void inputConfirmEmail(String confirmEmail) {
		Utils.inputValueIntoTextbox(I_MyAccount.txt_ConfirmEmail, confirmEmail);
	}

	public static void clickSubmitChangeEmail() throws InterruptedException {
		Utils.captureWebElement(I_MyAccount.btn_SubmitEmailChange).click();
		Thread.sleep(2000);
	}

	public static void clickCancelChangeEmail() {
		Utils.captureWebElement(I_MyAccount.btn_CancelEmailChange).click();
	}

	public static boolean verifyEmailChangeSuccessMessage(String expectedMsg) {
		String msg = "";
		if (Utils.captureWebElement(I_MyAccount.lbl_EmailChangeSuccessMsg).isDisplayed()) {
			msg = Utils.captureWebElement(I_MyAccount.lbl_EmailChangeSuccessMsg).getText();
			return msg.equals(expectedMsg);
		} else {
			return false;
		}
	}

	public static void closeEmailSuccessMessage() throws InterruptedException {
		Utils.captureWebElement(I_MyAccount.btn_EmailChangeClose).click();
		Thread.sleep(2000);
	}

	public static boolean verifyEmailChangeSuccessful(String expectedEmail) {
		String actualEmail = Utils.captureWebElement(I_MyAccount.lbl_CurrentEmail).getText();
		return actualEmail.equalsIgnoreCase(expectedEmail);
	}

	public static boolean verifyEmailChangeErrorMessage(String expectedMsg) {
		String msg = "";
		if (Utils.captureWebElements(I_MyAccount.lbl_ChangeEmailErrorMsg).size() != 0) {
			msg = Utils.captureWebElement(I_MyAccount.lbl_ChangeEmailErrorMsg).getText();
			return msg.equals(expectedMsg);
		} else {
			return false;
		}
	}

	public static void changeEmail(String newEmail) throws InterruptedException {
		if (!Utils.captureWebElement(I_MyAccount.btn_ChangeEmail).isDisplayed()) {
			goToManageProfile();
		}
		clickChangeEmail();
		inputNewEmail(newEmail);
		inputConfirmEmail(newEmail);
		clickSubmitChangeEmail();
		closeEmailSuccessMessage();
	}

	/*
	 * ***********************
	 * Change Name actions
	 *************************
	 */
	public static void clickChangeName() throws InterruptedException {
		Utils.captureWebElement(I_MyAccount.btn_ChangeName).click();
		Thread.sleep(2000);
	}

	public static void inputNewFirstName(String newFirstName) {
		Utils.inputValueIntoTextbox(I_MyAccount.txt_NewFirstName, newFirstName);
	}

	public static void inputNewLastName(String newLastName) {
		Utils.inputValueIntoTextbox(I_MyAccount.txt_NewLastName, newLastName);
	}

	public static void clickSubmitChangeName() throws InterruptedException {
		Utils.captureWebElement(I_MyAccount.btn_SubmitNameChange).click();
		Thread.sleep(2000);
	}

	public static boolean verifyNameChangeSuccessMessage(String expectedMsg) {
		String msg = "";
		if (Utils.captureWebElement(I_MyAccount.lbl_NameChangeSuccessMsg).isDisplayed()) {
			msg = Utils.captureWebElement(I_MyAccount.lbl_NameChangeSuccessMsg).getText();
			return msg.equals(expectedMsg);
		} else {
			return false;
		}
	}

	public static void closeNameSuccessMessage() throws InterruptedException {
		Utils.captureWebElement(I_MyAccount.btn_NameChangeClose).click();
		Thread.sleep(2000);
	}

	public static boolean verifyNameChangeSuccessful(String expectedName) {
		String actualName = Utils.captureWebElement(I_MyAccount.lbl_CurrentName).getText();
		return actualName.equalsIgnoreCase(expectedName);
	}

	public static boolean verifyNameChangeError(String expectedMsg) {
		String msg = "";
		if (Utils.captureWebElements(I_MyAccount.lbl_ChangeNameErrorMsg).size() != 0) {
			msg = Utils.captureWebElement(I_MyAccount.lbl_ChangeNameErrorMsg).getText();
			return msg.equals(expectedMsg);
		} else {
			return false;
		}
	}

	public static void changeName(String newFirstName, String newLastName) throws InterruptedException {
		if (Utils.captureWebElements(I_MyAccount.btn_ChangeName).size() == 0) {
			goToManageProfile();
		}
		clickChangeName();
		inputNewFirstName(newFirstName);
		inputNewLastName(newLastName);
		clickSubmitChangeName();
	}

	/*
	 * *************************
	 * Change Password actions
	 **************************
	 */
	public static void clickChangePassword() {
		Constants.driver.findElement(I_MyAccount.btn_ChangePassword).click();
	}

	public static void inputOldPassword(String oldPassword) {
		Utils.inputValueIntoTextbox(I_MyAccount.txt_OldPassword, oldPassword);
	}

	public static void inputNewPassword(String newPassword) {
		Utils.inputValueIntoTextbox(I_MyAccount.txt_NewPassword, newPassword);
	}

	public static void inputConfirmPassword(String confirmPassword) {
		Utils.inputValueIntoTextbox(I_MyAccount.txt_ConfirmPassword, confirmPassword);
	}

	public static void clickSubmitChangePassword() throws InterruptedException {
		Utils.captureWebElement(I_MyAccount.btn_SubmitPasswordChange).click();
		Thread.sleep(2000);
	}

	public static boolean verifyPasswordChangeSuccessMessage(String expectedMsg) {
		String msg = "";
		WebElement lblSucessMsg = Utils.captureWebElement(I_MyAccount.lbl_PasswordChangeSuccessMsg);
		if (lblSucessMsg.isDisplayed()) {
			msg = lblSucessMsg.getText();
			return msg.equals(expectedMsg);
		} else {
			return false;
		}
	}

	public static void closePasswordSuccessMessage() throws InterruptedException {
		Utils.captureWebElement(I_MyAccount.btn_PasswordChangeClose).click();
		Thread.sleep(5000);
	}

	public static void changePassword(String oldPassword, String newPassword) throws InterruptedException {
		if (Utils.captureWebElements(I_MyAccount.btn_ChangePassword).size() == 0) {
			goToManageProfile();
		}
		clickChangePassword();
		inputOldPassword(oldPassword);
		inputNewPassword(newPassword);
		inputConfirmPassword(newPassword);
		clickSubmitChangePassword();
		closePasswordSuccessMessage();
	}

	/*
	 * ************************
	 * Delete Account actions
	 **************************
	 */
	public static void clickDeleteAccount() {
		Utils.captureWebElement(I_MyAccount.btn_DeleteAccount).click();
	}

	public static void inputDeleteConfirmEmail(String confirmEmail) {
		Utils.captureWebElement(I_MyAccount.txt_ConfirmDeleteEmail).sendKeys(confirmEmail);
	}

	public static void clickDeleteMyAccount() {
		Utils.captureWebElement(I_MyAccount.btn_ConfirmDeleteAccount).click();
	}

	public static void DeleteAccount(String confirmEmail) {
		clickDeleteAccount();
		inputDeleteConfirmEmail(confirmEmail);
		clickDeleteMyAccount();
	}

	//Subscription
	public static void addBusinessMember(String email)
	{
		Constants.driver.get(Constants.Subscriptions_Url);
		Utils.captureWebElement(I_MyAccount.btn_AddMember).click();
		Utils.captureWebElement(I_MyAccount.btn_ConfirmAddMem).click();
		Utils.waitUntilElementIsVisible(I_MyAccount.btn_CloseAddMemSuccess);
		Utils.captureWebElement(I_MyAccount.btn_CloseAddMemSuccess).click();
		Utils.captureWebElement(I_MyAccount.lnk_MyTeam).click();
		Utils.inputValueIntoTextbox(I_MyAccount.txt_MemEmail, email);
		Utils.captureWebElement(I_MyAccount.btn_AddMemEmail).click();
	}
	
	public static void addEduMember(String email)
	{
		Constants.driver.get(Constants.My_Team_Url);
		Utils.inputValueIntoTextbox(I_MyAccount.txt_MemEmail, email);
		Utils.captureWebElement(I_MyAccount.btn_AddMemEmail).click();
	}
	
	public static boolean verifyAddMemSucess(String email)
	{
		int isExist = Utils.captureWebElements(By.xpath("//tr[@data-email='" + email + "']")).size();
		if (isExist == 1)
		{
			return true;
		}
		else
		{
			System.out.println("Add member failed!");
			return false;
		}
	}
	
	public static boolean verifyPlanLogo(String expectedPlan)
	{
		String headerText = Utils.captureWebElement(I_MyAccount.lbl_Plan_Logo).getText();
		if (headerText.equals(expectedPlan))
		{
			System.out.println("Sign up as a " + expectedPlan + " team member successful!");
			return true;
		}
		else
		{
			System.out.println("Failed to sign up as " + expectedPlan);
			return false;
		}
	}
	
	//Billing History
	public static String getInvoiceId()
	{
		String invoiceId = "";
		By lnk_BillingHistory = By.xpath("//div[@id='styles__root--ezHiv' and text()='Billing History']");
		Utils.clickElement(lnk_BillingHistory);
		By lnk_invoiceId = By.xpath("//div[@id='billing_profile']//tr[1]/td[4]/a");
		invoiceId = Utils.captureWebElement(lnk_invoiceId).getText();
		return invoiceId;
	}
	
	//Cancel subscription
	public static void cancelSubscription(String rating, int optionNum, String feedback) throws InterruptedException
	{
		String planPeriod = Utils.captureWebElement(By
							.xpath("//div[@id='account__subscriptionDetails--2m21l']//td[@class='account__caps--25mYi']"))
							.getText();
		Utils.scrollToElement(I_MyAccount.btn_CancelMySubscription);
		Utils.clickElement(I_MyAccount.btn_CancelMySubscription);
		Utils.clickElement(I_MyAccount.btn_Rating(rating));
		Utils.clickElement(I_MyAccount.btn_Continue);
		switch (optionNum)
		{
		case 1:
			Utils.clickElement(I_MyAccount.btn_CancelOption1);
			if (planPeriod.contains("Yearly"))
			{
				Utils.inputValueIntoTextbox(I_MyAccount.txt_Feedback, feedback);
				Utils.clickElement(I_MyAccount.btn_CancelAccount);
			}
			else
			{
				Utils.clickElement(I_MyAccount.btn_StillCancel);
				Utils.clickElement(I_MyAccount.btn_StillCancel);
			}
			Utils.clickElement(I_MyAccount.btn_ConfirmCancel);
			break;
		case 2:
			Utils.clickElement(I_MyAccount.btn_CancelOption2);
			Utils.clickElement(I_MyAccount.btn_StillCancel);
			Utils.clickElement(I_MyAccount.btn_CancelAccount);
			Utils.clickElement(I_MyAccount.btn_ConfirmCancel);
			break;
		case 3:
			Utils.clickElement(I_MyAccount.btn_CancelOption3);
			Utils.inputValueIntoTextbox(I_MyAccount.txt_Feedback, feedback);
			Utils.clickElement(I_MyAccount.btn_CancelAccount);
			Utils.clickElement(I_MyAccount.btn_ConfirmCancel);
			break;
		case 4:
			Utils.clickElement(I_MyAccount.btn_CancelOption4);
			Utils.inputValueIntoTextbox(I_MyAccount.txt_Feedback, feedback);
			Utils.clickElement(I_MyAccount.btn_CancelAccount);
			Utils.clickElement(I_MyAccount.btn_ConfirmCancel);
			break;
		case 5:
			Utils.clickElement(I_MyAccount.btn_CancelOption5);
			Utils.inputValueIntoTextbox(I_MyAccount.txt_Feedback, feedback);
			Utils.clickElement(I_MyAccount.btn_CancelAccount);
			Utils.clickElement(I_MyAccount.btn_ConfirmCancel);
			break;
		case 6:
			Utils.clickElement(I_MyAccount.btn_CancelOption6);
			Utils.inputValueIntoTextbox(I_MyAccount.txt_Feedback, feedback);
			Utils.clickElement(I_MyAccount.btn_CancelAccount);
			Utils.clickElement(I_MyAccount.btn_ConfirmCancel);
			break;
		}
	}
	
}


