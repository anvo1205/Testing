package Interface;
import org.openqa.selenium.By;
public class I_LandingPage {

	/**********************
	 * Feature landing page
	 * ********************/
	public static By lbl_FeatureLandingPageTitle = By.xpath("//h1[text()='Choose the Perfect Data Visualization']");
	
	/**********************
	 * Business landing page
	 * ********************/
	public static By lbl_BusinessLandingPageTitle = By.xpath("//h1[text()='Venngage for Business']");
	
	/**********************
	 * Education landing page
	 * ********************/
	public static By lbl_EducationLandingPageTitle = By.xpath("//h1[text()='Venngage for Education']");

	/**********************
	 * Timelione Infographics landing page
	 * ********************/
	public static By lbl_TimelineInfographicsLandingPageTitle = By.xpath("//h1[text()='Create Information Packed Timeline Infographics With Venngage']");
}
