package Function;

import org.openqa.selenium.By;


public class F_StaticTemplates {

	/* Interface */

	public static By lnk_CatInfographics = By.name("infographics");
	public static By lnk_SubcatStatistical = By.name("infographics-statistical");

	public static By btn_SignUp(String templateId) {
		return By.xpath(
				"//a[@href='https://infograph.venngage.beer/templates?ref=home&preview=" + templateId + "']/button");
	}

	public static By btn_SignUpFromPreview(String templateId) {
		return By.xpath("//a[@href='https://infograph.venngage.beer/templates?ref=home&preview=" + templateId
				+ "']/button[contains(@class,'preview__signUp')]");
	}

	public static By btn_Preview(String templateId) {
		return By.xpath("//a[@href='https://venngage.beer/templates/infographics/statistical?preview=" + templateId
				+ "']/button");
	}

	/* Function */
	
}
