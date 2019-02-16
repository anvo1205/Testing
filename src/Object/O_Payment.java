package Object;

import java.util.Calendar;

import Interface.I_UpgradeModal;
import Utility.Constants;
import Utility.Utils;


public class O_Payment {
	private String cardHolderName;
	private String cardNum;
	private String cardExpMonth;
	private String cardExpYear;
	private String cardCVC;
	
	public O_Payment() {
		this.cardHolderName = "Test";
		this.cardNum = Constants.Visa1;
		this.cardExpMonth = "12";
		this.cardExpYear = String.valueOf((Calendar.getInstance().get(Calendar.YEAR) + 1));
		this.cardCVC = "123";
	}
	
	public O_Payment(String cardNum) {
		this.cardHolderName = "Test";
		this.cardNum = cardNum;
		this.cardExpMonth = "12";
		this.cardExpYear = String.valueOf((Calendar.getInstance().get(Calendar.YEAR) + 1));
		this.cardCVC = "123";
	}
	
	public void upgrade(O_Payment p)
	{
	Utils.waitUntilElementIsVisible(I_UpgradeModal.btn_Upgrade);
	Utils.captureWebElement(I_UpgradeModal.txt_cardHolderName).sendKeys(p.getCardHolderName());
	Utils.captureWebElement(I_UpgradeModal.txt_cardNum).sendKeys(p.getCardNum());
	Utils.selectDropDownListValue(I_UpgradeModal.txt_cardExpMonth, "option", p.getCardExpMonth());
	Utils.selectDropDownListValue(I_UpgradeModal.txt_cardExpYear, "option", p.getCardExpYear());
	Utils.captureWebElement(I_UpgradeModal.txt_cardCVC).sendKeys(p.cardCVC);;
	Utils.captureWebElement(I_UpgradeModal.btn_Upgrade).click();
	}
	
	public static void clickContinueOnPremiumModal()
	{
		Utils.waitUntilElementIsVisible(I_UpgradeModal.btn_Continue_Pre_Modal);
		Utils.captureWebElement(I_UpgradeModal.btn_Continue_Pre_Modal).click();
	}
	
	public static void clickGetStartedOnBrandKitModal1()
	{
		Utils.waitUntilElementIsVisible(I_UpgradeModal.btn_Get_Started_Buz_Modal1);
		Utils.captureWebElement(I_UpgradeModal.btn_Get_Started_Buz_Modal1).click();
	}

	public String getCardHolderName() {
		return cardHolderName;
	}

	public void setCardHolderName(String cardHolderName) {
		this.cardHolderName = cardHolderName;
	}

	public String getCardNum() {
		return cardNum;
	}

	public void setCardNum(String cardNum) {
		this.cardNum = cardNum;
	}

	public String getCardExpMonth() {
		return cardExpMonth;
	}

	public void setCardExpMonth(String cardExpMonth) {
		this.cardExpMonth = cardExpMonth;
	}

	public String getCardExpYear() {
		return cardExpYear;
	}

	public void setCardExpYear(String cardExpYear) {
		this.cardExpYear = cardExpYear;
	}

	public String getCardCVC() {
		return cardCVC;
	}

	public void setCardCVC(String cardCVC) {
		this.cardCVC = cardCVC;
	}
	
}
