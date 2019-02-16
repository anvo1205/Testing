package Interface;

import org.openqa.selenium.By;

public class I_Onboarding {
	
	public static By Orgnanization(String org)
	{
		By organization = null;
		switch(org)
		{
		case "Small Business":
			organization = By.id("onboarding-option-sm-business");
			break;
		case "Medium Business":
			organization = By.id("onboarding-option-med-business");
			break;
		case "Nonprofit/NGO":
			organization = By.id("onboarding-option-nonprofit");
			break;
		case "Government":
			organization = By.id("onboarding-option-govt");
			break;
		case "School/University":
			organization = By.id("onboarding-option-school");
			break;
		case "Enterprise":
			organization = By.id("onboarding-option-enterprise");
			break;
		case "Self-Employed":
			organization = By.id("onboarding-option-self-employed");
			break;
		default: organization = By.xpath("//div[@id='onboarding-option-other'][1]");
		}
		return organization;
	}
//	public static By opt_School = By.id("onboarding-option-school");
//	public static By opt_Gorvernment = By.id("onboarding-option-govt");
//	public static By opt_Enterprise = By.id("onboarding-option-enterprise");
//	public static By opt_Nonprofit = By.id("onboarding-option-nonprofit");
//	public static By opt_Self_Empployed = By.id("onboarding-option-self-employed");
//	public static By opt_Small_Business = By.id("onboarding-option-sm-business");
//	public static By opt_Medium_Business = By.id("onboarding-option-med-business");
//	public static By opt_Other_Org = By.xpath("//div[@id='onboarding-option-other'][1]");
	
	public static By Role(String role)
	{
		By r = null;
		switch(role)
		{
		case "Librarian":
			r = By.id("onboarding-option-lib");
			break;
		case "Teacher/Professor":
			r = By.id("onboarding-option-prof");
			break;
		case "Student":
			r = By.id("onboarding-option-student");
			break;
		case "Administrator":
			r = By.id("onboarding-option-admin");
			break;
		case "Researcher/Analyst":
			r = By.id("onboarding-option-researcher");
			break;
		case "Marketer/PR":
			r = By.id("onboarding-option-marketer");
			break;
		case "Tutor/TA":
			r = By.id("onboarding-option-tutor");
			break;
		case "Human Resources":
			r = By.id("onboarding-option-hr");
			break;
		case "Program Manager":
		case "Campaign/Program Manager":
			r = By.id("onboarding-option-progmngr");
			break;
		case "Knowledge Mgmt/Trainer":
			r = By.id("onboarding-option-knowlmgt");
			break;
		case "Executive":
		case "Owner/Executive":
		case "C-level/Executive":
			r = By.id("onboarding-option-exec");
			break;
		case "Operations/Finance":
			r = By.id("onboarding-option-finance");
			break;
		case "Sales/Account Managers":
			r = By.id("onboarding-option-sales");
			break;
		case "IT/Engineering":
			r = By.id("onboarding-option-it-eng");
			break;
		case "Analyst/BI":
			r = By.id("onboarding-option-analyst");
			break;
		case "Designer":
			r = By.id("onboarding-option-designer");
			break;
		case "Business Consultant":
			r = By.id("onboarding-option-bnsscons");
			break;
		case "Fitness/Health":
			r = By.id("onboarding-option-health");
			break;
		case "Real Estate Agent":
			r = By.id("onboarding-option-real-estate");
			break;
		case "Blogger/Writer":
			r = By.id("onboarding-option-blogger");
			break;
		case "Recruiter":
			r = By.id("onboarding-option-recruiter");
			break;
		case "Customer Service":
			r = By.id("onboarding-option-custservice");
			break;
		default: r = By.xpath("//div[@id='onboarding-option-other'][2]");
		}
		return r;
	}
//	public static By opt_Librarian = By.id("onboarding-option-lib");
//	public static By opt_Teacher = By.id("onboarding-option-prof");
//	public static By opt_Student = By.id("onboarding-option-student");
//	public static By opt_Admin = By.id("onboarding-option-admin");
//	public static By opt_Researcher = By.id("onboarding-option-researcher");
//	public static By opt_Marketer = By.id("onboarding-option-marketer");
//	public static By opt_Tutor = By.id("onboarding-option-tutor");
//	public static By opt_Other_Role = By.xpath("//div[@id='onboarding-option-other'][2]");
//	public static By opt_HR = By.id("onboarding-option-hr");
//	public static By opt_Program_Manager = By.id("onboarding-option-progrmngr");
//	public static By opt_Trainer = By.id("onboarding-option-knowlmgt");
//	public static By opt_Executive = By.id("onboarding-option-exec");
//	public static By opt_Finance = By.id("onboarding-option-finance");
//	public static By opt_Sales = By.id("onboarding-option-sales");
//	public static By opt_IT_Engineering = By.id("onboarding-option-it-eng");
//	public static By opt_Analyst = By.id("onboarding-option-analyst");
//	public static By opt_Designer = By.id("onboarding-option-designer");
//	public static By opt_Bus_Consultant = By.id("onboarding-option-bnsscons");
//	public static By opt_Health = By.id("onboarding-option-health");
//	public static By opt_Real_Estate = By.id("onboarding-option-real-estate");
//	public static By opt_Blogger = By.id("onboarding-option-blogger");
//	public static By opt_Recruiter = By.id("onboarding-option-recruiter");
//	public static By opt_Customer_Service = By.id("onboarding-option-custservice");
	
	public static By category(String catName)
	{
		return By.xpath("//span[text()='" + catName + "']//ancestor::div[@class='styles__horizontalThumbnailItem--2WMkc']");
	}
	
	//Templates
	public static By btn_Countinue = By.xpath("//button[text()='Continue']");
	public static By lbl_TemplateTourGuide = By.xpath("//div[@class='styles__root___i5NK6']");
	
	
	
	
	
	
}
