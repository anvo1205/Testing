package Interface;
import org.openqa.selenium.By;


public class I_SignIn {
	public static By txt_Email = By.id("user_email");
	public static By txt_Password = By.id("user_password");
	public static By btn_SignIn = By.id("btn_login");
	public static By lbl_EmailError = By.id("user_email_error");
	public static By lbl_PasswordError = By.id("user_password_error");
	public static By lnk_Signup = By.xpath("//a[@href='/register']");
}
