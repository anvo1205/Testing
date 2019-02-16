package Function;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;

import Interface.I_TemplatePage;
import Utility.Constants;
import Utility.Utils;

public class F_TemplatePage {
	
	public static void searchTemplate(String keyword)
	{
		Utils.waitUntilElementIsVisible(I_TemplatePage.txt_Search);
		Utils.inputValueIntoTextbox(I_TemplatePage.txt_Search, keyword);
		Utils.clickElement(I_TemplatePage.btn_Search);
	}

	public static String getIdFirstTemplate()
	{
		String url = Utils.captureWebElement(By.xpath("//div[@class='styles__columns___6PuGM'][1]/div[@class='styles__root___2nWj-'][1]//a"))
				.getAttribute("href");
		int i = url.lastIndexOf("=");
		return url.substring(i + 1);
	}
	public static String getTitleFirstTemplate()
	{
		String[] levelNTitle = Utils.captureWebElement(By.xpath("//div[@class='styles__columns___6PuGM'][1]/div[@class='styles__root___2nWj-'][1]//div[@class='styles__title___2adG_']"))
					.getText().split("\n");
		return levelNTitle[1];
	}
	
	public static void clickOnCreate1stTemplate()
	{
		Utils.moveMouseToElement(By.xpath(
				"//div[@class='styles__columns___6PuGM'][1]/div[@class='styles__root___2nWj-'][1]//button[text()='Create']"));
		Utils.captureWebElement(By.xpath(
				"//div[@class='styles__columns___6PuGM'][1]/div[@class='styles__root___2nWj-'][1]//button[text()='Create']"))
				.click();
	}
	
	public static void selecteTemplateCatetory(By cat) throws InterruptedException {
		Thread.sleep(1000);
		Utils.clickElement(cat);
	}
	
	public static void clickBlankCanvas()
	{
		By img_Blank_Canvas = By.xpath("//img[@class='styles__blankSVG___1IGsw']");
		Utils.waitUntilElementIsVisible(img_Blank_Canvas);
		Utils.captureWebElement(img_Blank_Canvas).click();
	}
	
	public static boolean verifyCorrectTemplateCategoryDisplay(String categoryTitle) {
		
		String actualTitle = Utils.captureWebElement(I_TemplatePage.lbl_CategoryTitle).getText();
		if (actualTitle.contains(categoryTitle))
		{
			return true;
		}
		else
		{
			System.out.println("\nIncorrect Category Dispplays!\n");
			return false;
		}
	}
	
	public static int getUserId()
	{
		 String strUserId = ((JavascriptExecutor) Constants.driver).executeScript("return __vg_config.user.id;").toString();
		 return Integer.parseInt(strUserId);
	}

}
