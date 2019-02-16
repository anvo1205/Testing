package Interface;
import org.openqa.selenium.By;

public class I_SignUp {
	
	/***********User Information Interfaces**************/
	public static By lbl_SignUpPageTitle = By.xpath("//h1[text()='Sign up for free']");
	public static By txt_FirstName = By.id("user_first_name");
	public static By txt_LastName = By.id("user_last_name");
	public static By txt_Email = By.id("user_email");
	public static By txt_Password = By.id("user_password");
	public static By btn_Register = By.id("btn_register");
	public static By btn_Facebook = By.id("facebook");
	public static By btn_Google = By.id("google");
	public static By lnk_Login = By.linkText("Log in here");
	public static By lbl_EmailError = By.id("user_email_error");
	
	/***********Organization Selection Interfaces**************/
	public static By lbl_GreetingMsg = By.xpath("//div[contains(@class,'greeting')]");
	public static By lnk_SelfEmployedOrg = By.xpath("//div[contains(text(),'Self-Employed')]");
	public static By lnk_OthersOrg = By.xpath("//div[contains(text(),'Other')]");
	
	/***********Role Selection Interfaces**************/
	public static By lnk_ITEngineeringRole = By.xpath("//div[contains(text(),'IT/Engineering')]");
	
	/***********Category Selection Interfaces**************/
	public static By lnk_InfographicCategory = By.xpath("//div[@class='styles__horizontalThumbnailItem___2WMkc'][1]");
	public static By lnk_PresentationCategory = By.xpath("//div[@class='styles__horizontalThumbnailItem___2WMkc'][2]");
	public static By lnk_PosterCategory = By.xpath("//div[@class='styles__horizontalThumbnailItem___2WMkc'][3]");
	public static By lnk_ReportCategory = By.xpath("//div[@class='styles__horizontalThumbnailItem___2WMkc'][4]");
	public static By lnk_ResumeCategory = By.xpath("//div[@class='styles__horizontalThumbnailItem___2WMkc'][5]");
	
	
	/***********Template Selection Interfaces**************/
	public static By lnk_Resume1 = By.xpath("//div[@class='styles__verticalThumbnailColumn___1sa3i'][1]//div[@class='styles__root___U-c1a'][1]");
	public static By lnk_Resume2 = By.xpath("//div[@class='styles__verticalThumbnailColumn___1sa3i'][2]//div[@class='styles__root___U-c1a'][1]");
	public static By lnk_Resume3 = By.xpath("//div[@class='styles__verticalThumbnailColumn___1sa3i'][3]//div[@class='styles__root___U-c1a'][1]");
	public static By btn_Countinue = By.xpath("//button[text()='Continue']");
	
	/**************************Design Page********************/
	public static By fra_LoadingTemplate = By.id("fetchingStyles__loading___Btcgm");
	
}
