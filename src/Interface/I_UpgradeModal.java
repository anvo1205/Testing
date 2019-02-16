package Interface;

import org.openqa.selenium.By;

public class I_UpgradeModal {
	public static By txt_cardHolderName = By.name("name");
	public static By txt_cardNum = By.name("number");
	public static By txt_cardExpMonth = By.name("month");
	public static By txt_cardExpYear = By.name("year");
	public static By txt_cardCVC = By.name("cvc");
	public static By btn_Upgrade = By.xpath("//div[@id='styles__modalContent--Eznb_']//div[@id='styles__root--3tkRc']");
	
	public static By btn_Continue_Pre_Modal = By.id("styles__redirectButton--gW4sh");
	public static By btn_Get_Started_Buz_Modal1 = By.xpath("//div[@id='styles__businessRedirect--3Ztgn']/div[text()='Get Started']");
	
}
