package Function;
import org.openqa.selenium.By;

import Interface.*;
import Utility.*;

public class F_LandingPage {
	/**********************
	 * Feature landing page
	 * ********************/
	public static boolean verifyFeatureLandingPageDisplay()
	{
		boolean isDisplay = Utils.captureWebElement(I_LandingPage.lbl_FeatureLandingPageTitle).isDisplayed();
		if (isDisplay)
		{
			return true;
		}
		else
		{
			System.out.println("\nFeature landing page is not displayed!\n");
			return false;
		}
	}
	
	public static void clickSingUpForFree(int buttonNumber){
		Utils.captureWebElement(By.id("CTA-premium-signup-" + buttonNumber)).click();
	}
	
	/**********************
	 * Business landing page
	 * ********************/
	public static boolean verifyBusinessLandingPageDisplay()
	{
		boolean isDisplay = Utils.captureWebElement(I_LandingPage.lbl_BusinessLandingPageTitle).isDisplayed();
		if (isDisplay)
		{
			return true;
		}
		else
		{
			System.out.println("\nBusiness landing page is not displayed\n");
			return false;
		}
	}
	
	public static void clickSignUpNow(int buttonNumber){
		Utils.captureWebElement(By.id("CTA-business-signup-" + buttonNumber)).click();
	}

	/**********************
	 * Education landing page
	 * ********************/
	
	public static boolean verifyEducationLandingPageDisplay() {
		boolean isDisplay = Utils.captureWebElement(I_LandingPage.lbl_EducationLandingPageTitle).isDisplayed();
		if (isDisplay)
		{
			return true;
		}
		else
		{
			System.out.println("\nEducation landing page is not displayed\n");
			return false;
		}
	}
	
	public static void clickStartFreeTrial(int buttonNumber){
		Utils.captureWebElement(By.id("CTA-education-signup-" + buttonNumber)).click();
	}

	/**********************
	 * Timeline Infographics landing page
	 * ********************/
	public static boolean verifyTimeLineInfographicsLandingPageDisplay() {
		boolean isDisplay = Utils.captureWebElement(I_LandingPage.lbl_TimelineInfographicsLandingPageTitle).isDisplayed();
		if (isDisplay)
		{
			return true;
		}
		else
		{
			System.out.println("\nTimeline Infographics landing page is not displayed\n");
			return false;
		}
	}

}
