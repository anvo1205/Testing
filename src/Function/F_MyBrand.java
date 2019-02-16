package Function;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import Interface.I_MyBrand;
import Utility.Utils;

public class F_MyBrand {
	
	public static void clickAddToMyBrandKitModal()
	{
		Utils.clickElement(I_MyBrand.btn_AddToMyBrandKit);
	}
	
	public static void updateBrandTitle(String title) throws InterruptedException
	{
		Utils.clickElement(I_MyBrand.lbl_BrandTitle);
		Utils.inputValueIntoTextbox(I_MyBrand.txt_BrandTitle, title);
		Utils.captureWebElement(I_MyBrand.txt_BrandTitle).sendKeys(Keys.ENTER);
		Thread.sleep(1000);
	}
	
	public static void uploadLogo(String filePath)
	{
		Utils.captureWebElement(I_MyBrand.txt_UploadLogo).sendKeys(filePath);
	}
	
	public static void addColourByTextbox(String[] rgb) throws InterruptedException
	{
		Utils.clickElement(I_MyBrand.btn_AddColour);
		Utils.inputValueIntoTextbox(I_MyBrand.txt_Red, rgb[0]);
		Thread.sleep(1500);
		Utils.inputValueIntoTextbox(I_MyBrand.txt_Green, rgb[1]);
		Thread.sleep(1500);
		Utils.inputValueIntoTextbox(I_MyBrand.txt_Blue, rgb[2]);
		Thread.sleep(1500);
	}
	
	public static String clickCreateFromRandomBrandedTemplate() throws InterruptedException
	{
		String templateId = "";
		int ranColumn = Utils.generateRandomInteger(1, 3);
		By templates = By.xpath("//div[@class='styles__container--1Q61a ']/div["+ ranColumn + "]//div[@class='styles__thumbnail--2WykV']");
		int numTemplates = Utils.captureWebElements(templates).size();
		int ranTemplate = Utils.generateRandomInteger(1, numTemplates);
		By template = By.xpath("//div[@class='styles__container--1Q61a ']/div["+ ranColumn 
						+ "]//div[@class='styles__thumbnail--2WykV'][" + ranTemplate + "]");
		templateId = Utils.captureWebElement(template).findElement(By.tagName("img")).getAttribute("src");
		int index1 = templateId.lastIndexOf("/");
		int index2 = templateId.lastIndexOf("?");
		System.out.println("selected branded template: " + templateId.substring(index1 + 1, index2 - 4));
		By btn_Create = By.xpath("//div[@class='styles__container--1Q61a ']/div["+ ranColumn 
				+ "]//div[@class='styles__thumbnail--2WykV'][" + ranTemplate + "]//button");
		Utils.scrollToElement(btn_Create);
		Utils.moveMouseToElement(template);
		Utils.clickElement(btn_Create);
		
		return templateId.substring(index1 + 1, index2 - 4);
	}
	
	public static List<String> getFontList(By ddl, String tagName)
	{
		return Utils.getDropDownListItems(ddl, tagName);
	}

	public static void requestAFont(By ddl, String fontUrl, String language) throws InterruptedException
	{
		Utils.scrollToElement(By.xpath("//h2[text()='FONTS']"));
		Utils.clickElement(ddl);
		Utils.clickElement(I_MyBrand.lnk_RequestFont);
		Utils.inputValueIntoTextbox(I_MyBrand.txt_FontUrl, fontUrl);
		Utils.inputValueIntoTextbox(I_MyBrand.txt_FontLanguage, language);
		Utils.clickElement(I_MyBrand.btn_RequestFont);
		
	}
}
