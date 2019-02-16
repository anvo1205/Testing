package Utility;

public class GeneralActions {
	
	/* Method name	: verifyUrlIsCorrect
	 * Description	: This method will get the current Url and compare with expected Url
	 * Accept		: String
	 * Return		: Boolean	
	 */
	public static boolean verifyUrlIsCorrect(String expectedUrl)
	{
		String currentURL = Constants.driver.getCurrentUrl();
		if (currentURL.matches(expectedUrl))
		{
			return true;
		}
		else
		{
			System.out.println("\nCurrent Url is incorrect!\n");
			return false;
		}
	}
}
