package Object;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;

import Function.F_Onboarding;
import Interface.I_Onboarding;
import Utility.Utils;

public class O_Onboarding {
	private String org;
	private String role;
	private String category;
	
	public O_Onboarding()
	{
		this.org = "Small Business";
		this.role = "Marketer/PR";
		this.category = "Presentation";
	}
	
	public O_Onboarding(String org, String role, String category)
	{
		this.org = org;
		this.role = role;
		this.category = category;
	}

	public String getOrg() {
		return org;
	}

	public void setOrg(String org) {
		this.org = org;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
	
	public List<String> completeOnboarding(O_Onboarding o) throws InterruptedException
	{
		Utils.waitUntilElementIsVisible(I_Onboarding.Orgnanization(o.getOrg()));
		F_Onboarding.selectOrganization(o.getOrg());
		Utils.waitUntilElementIsVisible(I_Onboarding.Role(o.getRole()));
		Thread.sleep(1000);
		F_Onboarding.selectRole(o.getRole());
		Utils.waitUntilElementIsVisible(I_Onboarding.category(o.getCategory()));
		F_Onboarding.selectCategory(o.getCategory());
		List<String> templateId = select3Templates();
		F_Onboarding.clickContinue();
		Utils.waitUntilElementIsVisible(I_Onboarding.lbl_TemplateTourGuide);
		return templateId;
	}
	
	/*
	 * Method name : select3Templates 
	 * Description : This method is used to select 3 first templates from the list and return template ids
	 * Accept      : void
	 * Return      : void
	 */
	public List<String> select3Templates()
	{
		List<String> templateId = new ArrayList<String>();
		By img_Template = null;
		for (int i = 1; i < 4; i++)
		{
			img_Template = By.xpath("//div[@class='styles__answers--2hm__'][4]//div[@class='styles__verticalThumbnailColumn--1sa3i']["
																			+ i + "]/div[@class='styles__root--U-c1a'][1]");
			Utils.waitUntilElementIsVisible(img_Template);
			templateId.add(Utils.captureWebElement(img_Template)
					.findElement(By.tagName("img")).getAttribute("src")
					.replace("https://s3.amazonaws.com/yolo-thumbnails.venngage.com/template/small/", "")
					.replace(".png", ""));
			Utils.captureWebElement(img_Template).click();
		}
		return templateId;
	}
}
