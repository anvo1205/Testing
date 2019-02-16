package Interface;
import org.openqa.selenium.By;

public class I_MyAccount {
	//Top menu
	public static By lbl_Plan_Logo = By.xpath("//div[@class='logo-badge-text']");
	
	//Left navigation
	public static By link_Profile = By.xpath("//div[@id='styles__root--ezHiv' and text()='Profile']");
	public static By lnk_MyTeam = By.xpath("//div[@id='styles__root--ezHiv' and text()='My Team']");
	public static By lnk_Subsciption = By.xpath("//div[@id='styles__root--ezHiv' and text()='Subscription']");
	public static By lnk_BillingHistory = By.xpath("//div[@id='styles__root--ezHiv' and text()='Billing History']");
	public static By lnk_ReferAFriend = By.xpath("//div[@id='styles__root--ezHiv' and text()='Refer a Friend']");
	
	//Profile
	public static By lbl_CurrentEmail = By.id("current_email");
	public static By btn_ChangeEmail = By.id("change_email");
	public static By txt_NewEmail = By.xpath("//input[@name='newEmail']");
	public static By txt_ConfirmEmail = By.xpath("//input[@name='confirmEmail']");
	public static By btn_SubmitEmailChange = By.id("submit_email");
	public static By btn_CancelEmailChange = By.id("cancel_email_change");
	public static By lbl_ChangeEmailErrorMsg = By.id("new_email_error");
	public static By lbl_EmailChangeSuccessMsg = By.xpath("//div[@id='email-change-success-modal']//div[@class='modal-body']");
	public static By btn_EmailChangeClose = By.xpath("//div[@id='email-change-success-modal']//div[@class='modal-footer']/button");

	public static By lbl_CurrentName = By.id("current_name");
	public static By btn_ChangeName = By.id("change_name");
	public static By txt_NewFirstName = By.xpath("//input[@name='newFirstName']");
	public static By txt_NewLastName = By.xpath("//input[@name='newLastName']");
	public static By btn_SubmitNameChange = By.id("submit_name");
	public static By btn_CancelNameChange = By.id("cancel_name_change");
	public static By lbl_ChangeNameErrorMsg = By.id("new_last_name_error");
	public static By lbl_NameChangeSuccessMsg = By.xpath("//div[@id='name-change-success-modal']//div[@class='modal-body']");
	public static By btn_NameChangeClose = By.xpath("//div[@id='name-change-success-modal']//div[@class='modal-footer']/button");

	public static By btn_ChangePassword = By.id("change_password");
	public static By txt_OldPassword = By.xpath("//input[@name='oldPassword']");
	public static By txt_NewPassword = By.xpath("//input[@name='newPassword']");
	public static By txt_ConfirmPassword = By.xpath("//input[@name='confirmPassword']");
	public static By btn_SubmitPasswordChange = By.id("submit_password");
	public static By btn_CancelPasswordChange = By.id("cancel_password_change");
	public static By lbl_ChangeOldPasswordErrorMsg = By.id("old_password_error");
	public static By lbl_ChangeNewPasswordErrorMsg = By.id("new_password_error");
	public static By lbl_ChangeConfirmPasswordErrorMsg = By.id("confirm_password_error");
	public static By lbl_PasswordChangeSuccessMsg = By.xpath("//div[@id='password-success-modal']//div[@class='modal-body']");
	public static By btn_PasswordChangeClose = By.xpath("//div[@id='password-success-modal']//div[@class='modal-footer']/button");

	public static By btn_DeleteAccount = By.id("delete_account_btn");
	public static By txt_ConfirmDeleteEmail = By.id("confirm_email");
	public static By btn_ConfirmDeleteAccount = By.id("confirm_delete_btn");
	
	//Subscription
	public static By btn_AddMember = By.id("account__addMemberButton--B1OT0");
	public static By btn_ConfirmAddMem = By.xpath("//div[@class='account__modalButton--oneF4 account__modalConfirmButton--1W5rA']");
	public static By btn_CloseAddMemSuccess = By.id("styles__confirmButton--1ta5u");
	public static By btn_CancelMySubscription = By.id("account__cancelSubscriptionButton--uOkfo");
	public static By btn_Rating(String ratingNum)
	{
		return By.xpath("//input[@value='" + ratingNum + "']");
	}
	public static By btn_Continue = By.xpath("//button[@class='Cancellation__confirmBtn--2IaDq Cancellation__btn--1eYUF styles__enabled--BM2hh']");
	public static By btn_CancelOption1 = By.id("cancel-subscription-survey-1");
	public static By btn_CancelOption2 = By.id("cancel-subscription-survey-2");
	public static By btn_CancelOption3 = By.id("cancel-subscription-survey-3");
	public static By btn_CancelOption4 = By.id("cancel-subscription-survey-4");
	public static By btn_CancelOption5 = By.id("cancel-subscription-survey-5");
	public static By btn_CancelOption6 = By.id("cancel-subscription-survey-6");
	public static By btn_StillCancel = By.xpath("//div[@class='Cancellation__cancelOption--2QisI']");
	public static By btn_CancelAccount = By.xpath("//button[text()='Cancel Account']");
	public static By btn_ConfirmCancel = By.xpath("//button[@value='cancel']");
	public static By txt_Feedback = By.xpath("//div[@id='styles__feedbackContainer--1vYOU']//textarea");
	
	
	//My Team
	public static By txt_MemEmail = By.id("add_team_member_input");
	public static By btn_AddMemEmail = By.id("add_team_member_btn");
	
	
}
