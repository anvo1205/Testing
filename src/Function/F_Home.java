package Function;
import Interface.*;
import Utility.*;

public class F_Home{
	
	public static void clickTemplateLink()
	{
		Utils.captureWebElement(I_Home.lnk_Template).click();
	}
	
	/*
	 ******************
	 * Pricing menus
	 ******************
	 **/
	public static void selectPlansMenu()
	{
		Utils.moveMouseToElement(I_Home.lnk_Pricing);
		Utils.captureWebElement(I_Home.lnk_Plans).click();
	}
	
	public static void selectEducationMenu()
	{
		Utils.moveMouseToElement(I_Home.lnk_Pricing);
		Utils.captureWebElement(I_Home.lnk_Education).click();
	}
	
	public static void selectNonprofitMenu()
	{
		Utils.moveMouseToElement(I_Home.lnk_Pricing);
		Utils.captureWebElement(I_Home.lnk_Nonprofit).click();
	}
	
	/*
	 * ****************************
	 * Features menus
	 * @throws InterruptedException 
	 ******************************
	 **/
	
	public static void selectFeatureMenu()
	{
			Utils.captureWebElement(I_Home.lnk_Features).click();
	}
	public static void selectBusinessFeature()
	{
			Utils.moveMouseToElement(I_Home.lnk_Features);
			Utils.waitUntilElementIsVisible(I_Home.lnk_BusinessFeatures);
			Utils.captureWebElement(I_Home.lnk_BusinessFeatures).click();
	}
	
	public static void selectPremiumFeature()
	{
			Utils.moveMouseToElement(I_Home.lnk_Features);
			Utils.waitUntilElementIsVisible(I_Home.lnk_PremiumFeatures);
			Utils.captureWebElement(I_Home.lnk_PremiumFeatures).click();
	}
	
	public static void selectEducationFeature()
	{
		Utils.moveMouseToElement(I_Home.lnk_Features);
		Utils.waitUntilElementIsVisible(I_Home.lnk_EducationFeatures);
		Utils.captureWebElement(I_Home.lnk_EducationFeatures).click();
	}
	
	public static void selectInfographicsFeature() {
		Utils.moveMouseToElement(I_Home.lnk_Features);
		Utils.waitUntilElementIsVisible(I_Home.lnk_InfographicsFeatures);
		Utils.captureWebElement(I_Home.lnk_InfographicsFeatures).click();
	}
	
	public static void selectTimelineInfographicsFeature() {
		Utils.moveMouseToElement(I_Home.lnk_Features);
		Utils.waitUntilElementIsVisible(I_Home.lnk_TimelineInfographicsFeatures);
		Utils.captureWebElement(I_Home.lnk_TimelineInfographicsFeatures).click();
	}

	public static void selectChartsFeature() {
		Utils.moveMouseToElement(I_Home.lnk_Features);
		Utils.waitUntilElementIsVisible(I_Home.lnk_ChartsFeatures);
		Utils.captureWebElement(I_Home.lnk_ChartsFeatures).click();
	}
	
	
	
	
	// Sign-in link
	public static void clickSignInLink()
	{
		Utils.captureWebElement(I_Home.lnk_SignIn).click();
	}
	
	// Sign-up link
	public static void clickSingUpLink()
	{
		Utils.captureWebElement(I_Home.lnk_SignUp).click();
	}

	
}
