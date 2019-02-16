package Function;


import org.openqa.selenium.By;

import Interface.I_Onboarding;
import Interface.I_TemplatePage;
import Utility.Utils;

public class F_Onboarding {
	public static void selectOrganization(String org)
	{
		Utils.captureWebElement(I_Onboarding.Orgnanization(org)).click();
	}
	
	public static void selectRole(String role)
	{
		Utils.captureWebElement(I_Onboarding.Role(role)).click();
	}
	
	public static void selectCategory(String cat)
	{
		Utils.captureWebElement(I_Onboarding.category(cat)).click();
	}
	public static void selectRandomTemplates(int numTemplates)
	{
		int ranNum = 0;
		int temCount = Utils.captureWebElements(By.xpath("//div[@class='styles__answers___2hm__'][4]//div[@class='styles__root___U-c1a']")).size();
		for (int i=0; i <numTemplates; i++)
		{
			ranNum = (int)(Math.random() * (temCount - 1) + 1);
			Utils.captureWebElement(By.xpath("//div[@class='styles__root___U-c1a'][" + ranNum +  "]")).click();
		}
	}
	
	public static void clickContinue()
	{
		Utils.captureWebElement(I_Onboarding.btn_Countinue).click();
		Utils.waitUntilElementIsVisible(I_TemplatePage.lbl_TemplateTourGuide);
	}
}
