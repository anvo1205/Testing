package Interface;
import org.openqa.selenium.By;

public class I_MyDesigns {
	public static By lnk_MyDesign = By.xpath("//a[@href='/infographics']");
	public static By lbl_MyDesigns = By.id("styles__nonFolder___1XnQQ");
	public static By btn_Add = By.xpath("//div[@class='styles__addBtn___1cQ-q']");
	public static By btn_Account = By.id("account-button");
	public static By ddl_Account = By.xpath("//li[@id='account-button']//span[@class='caret']");
	public static By lnk_MyAccount = By.xpath("//a[@href='/account/profile']//span[text()='My Account']");
	public static By lnk_SignOut = By.xpath("//a[@href='/logout']");
}
