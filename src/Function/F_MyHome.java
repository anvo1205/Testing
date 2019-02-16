package Function;

import org.openqa.selenium.By;

import Interface.I_MyDesigns;
import Utility.*;

public class F_MyHome {

	public static void clickAddButton() {
		Utils.captureWebElement(I_MyDesigns.btn_Add).click();
	}

	public static void clickMyDesigns() {
		Utils.captureWebElement(I_MyDesigns.lnk_MyDesign).click();
	}
	
	public static void clickTopMenu(String menuName)
	{
		Utils.clickElement(By.linkText(menuName.toUpperCase()));
	}

	public static void signOut() throws InterruptedException {
		Utils.captureWebElement(I_MyDesigns.ddl_Account).click();
		Thread.sleep(2000);
		Utils.captureWebElement(I_MyDesigns.lnk_SignOut).click();
	}

}
