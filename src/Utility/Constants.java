package Utility;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Constants {
	
	public static WebDriver driver;
	public static WebDriverWait wait;
	
	//Set up
	public static final String chromeDriver = "webdriver.chrome.driver";
	public static final String chromeDriverPath = "C:/John/Venngage/Automation/chromedriver_win32/chromedriver.exe";
	public static long chromeWait = 10;
	public static final String geckoDriver = "webdriver.gecko.driver";
	public static final String geckoDriverPath = "";
	public static final String Base_Url = "https://venngage.beer/";
	public static int TimeOut = 10;
	
	//Accounts
	public static final String export_User_Email = "exportallwidgets@autotest.com";
	
	//test url
	public static final String Free_Sign_Up_Url = "https://infograph.venngage.beer/register";
	public static final String Sign_In_Url = "https://infograph.venngage.beer/signin";
	public static final String BuzM_SignUp_Url = "https://infograph.venngage.beer/signup/business/monthly";
	public static final String BuzQ_SignUp_Url = "https://infograph.venngage.beer/signup/business/quarterly";
	public static final String BuzY_SignUp_Url = "https://infograph.venngage.beer/signup/business/yearly";
	public static final String PreM_SignUp_Url = "https://infograph.venngage.beer/signup/premium/monthly";
	public static final String PreQ_SignUp_Url = "https://infograph.venngage.beer/signup/premium/quarterly";
	public static final String PreY_SignUp_Url = "https://infograph.venngage.beer/signup/premium/yearly";
	public static final String EduM_SignUp_Url = "https://infograph.venngage.beer/signup/education/monthly";
	public static final String EduY_SignUp_Url = "https://infograph.venngage.beer/signup/education/yearly";
	public static final String My_Team_Url = "https://infograph.venngage.beer/account/team";
	public static final String Subscriptions_Url = "https://infograph.venngage.beer/account/subscription";
	public static final String Export_Long_Design_Url = "https://infograph.venngage.beer/edit/f7465fb9-91bd-4040-ab21-fe9d0ed48128"; 
	public static final String Export_All_Widgets_Url = "https://infograph.venngage.beer/edit/052aac90-237c-4572-b0e7-4b1140cf1e36"; 
	public static final String Export_Single_Page_Url = "https://infograph.venngage.beer/infograph/view/f1d3a1bb-5988-47d4-89d1-c45e6a56be42";
	public static final String Export_All_Fonts_Url = "https://infograph.venngage.beer/edit/867d3b36-e6d9-4af1-ad72-9fef01be68c2";
	
	//Payment
	public static final String Visa1 = "4242424242424242";
	public static final String Visa2 = "4012888888881881";
	public static final String VisaDebit = "4000056655665556";
	public static final String MasterCard = "5555555555554444";
	public static final String MasterDebit = "5200828282828210";
	public static final String MasterPrepaid = "5105105105105100";
	public static final String AmericanExpress1 = "378282246310005";
	public static final String AmericanExpress2 = "371449635398431";
	//Invalid
	public static final String CardDecline = "4000000000000002";
	public static final String InsufficientFund  = "4000000000009995";
	public static final String IncorrectCVC  = "4000000000000127";
	public static final String ExpiredCard  = "4000000000000069";
	public static final String ProcessingError  = "4000000000000119";
	public static final String IncorrectNumber  = "4242424242424241";
	public static final String InvalidCard  = "1234567890123456";
	public static final String AcceptedCardChargeFailed  = "4000000000000341";
	
	//user plans
	public static final String Stripe_PreM_Plan = "Infographic_Premium_1";
	public static final String Stripe_PreQ_Plan = "Infographic_Premium_3";
	public static final String Stripe_PreY_Plan = "Infographic_Premium_12";
	public static final String Stripe_BuzM_Plan = "Business_Plan_1";
	public static final String Stripe_BuzQ_Plan = "Business_Plan_3";
	public static final String Stripe_BuzY_Plan = "Business_Plan_12";
	public static final String Stripe_EduM_Plan = "Education_Plan_1_1";
	public static final String Stripe_EduY_Plan = "Education_Plan_1_12";
	
	//Charge amount
	public static final String Stripe_PreM_Amount = "1900";
	public static final String Stripe_PreQ_Amount = "4900";
	public static final String Stripe_PreY_Amount = "19000";
	public static final String Stripe_BuzM_Amount = "4900";
	public static final String Stripe_BuzQ_Amount = "12900";
	public static final String Stripe_BuzY_Amount = "46800";
	public static final String Stripe_EduM_Amount = "2900";
	public static final String Stripe_EduY_Amount = "9900";
	
	//Message
	public static final String SIGNUP_MISSING_EMAIL_ERROR = "The Email field must contain a valid email address.";
	public static final String SIGNUP_EMAIL_EXIST_MSG = "This email already exist";
	public static final String SIGNUP_INVALID_EMAIL_MSG = "Invalid Email";
	public static final String SIGNIN_NOT_EXIST_EMAIL_MSG = "This user doesn't exist";
	public static final String SIGNIN_INVALID_FORMAT_EMAIL_MSG = "The Email field must contain a valid email address.";
	public static final String SIGNIN_INCORRECT_PASSWORD_MSG = "The password is incorrect.";
	public static final String SIGNIN_INVALID_PASSWORD_MSG = "The Password field must be 6 to 40 characters in length.";
	public static final String CHANGE__EMAIL_SUCCESS_MSG = "Your email has been changed. Please Log back in with your new email";
	public static final String CHANGE_EXISTING_EMAIL_ERROR_MSG = "Email already exists";
	public static final String CHANGE_INVALID_EMAIL_ERROR_MSG = "Invalid Email";
	public static final String CHANGE_INCONSISTENT_EMAIL_ERROR_MSG = "Email Not Match";
	public static final String CHANGE_NAME_SUCCESS_MSG = "Your name has been changed.";
	public static final String CHANGE_NAME_ERROR_MSG = "Name must be between 2 to 40 characters";
	public static final String CHANGE_PASSWORD_SUCCESS_MSG = "Your Password has been changed.";
	
	//File path
	public static final String Download_Destination = "C:/Users/John/Downloads";
	public static final String Folder_TestData = System.getProperty("user.dir") + "\\src\\TestData\\";
	
	//File Name
	public static final String File_StripeData = "stripe.plans.json";
	
	
	public static String Png_Single_Page_Base_File_Path(int i)
	{
		return Constants.Download_Destination + "/single_page_png_" + i + ".png";
	}
	public static final String Pdf_Single_Page_Expected_File_Path = "C:/Users/John/Downloads/data/single_page_pdf.pdf";
	public static final String V_Pdf_Single_Page_Expected_File_Path = "C:/Users/John/Downloads/data/single_page_v_pdf.pdf";
	public static final String I_Pdf_Single_Page_Expected_File_Path = "C:/Users/John/Downloads/data/single_page_i_pdf.pdf";
	public static final String Pdf_Single_Page_Downloaded_File_Path = "C:/Users/John/Downloads/f1d3a1bb-5988-47d4-89d1-c45e6a56be42.pdf";
	
	public static final String Pdf_All_Widgets_Expected_File_Path = "C:/Users/John/Downloads/data/all_widgets_pdf.pdf";
	public static final String I_Pdf_All_Widgets_Expected_File_Path = "C:/Users/John/Downloads/data/all_widgets_interactive_pdf.pdf";
	public static final String Pdf_All_Widgets_Downloaded_File_Path = "C:/Users/John/Downloads/052aac90-237c-4572-b0e7-4b1140cf1e36.pdf";
	
	public static final String Pdf_Long_Design_Expected_File_Path = "C:/Users/John/Downloads/data/long_design_pdf.pdf";
	public static final String I_Pdf_Long_Design_Expected_File_Path = "C:/Users/John/Downloads/data/long_design_interactive_pdf.pdf";
	public static final String Pdf_Long_Design_Downloaded_File_Path = "C:/Users/John/Downloads/f7465fb9-91bd-4040-ab21-fe9d0ed48128.pdf";
	public static final String Png_Long_Design_Expected_File_Path = "C:/Users/John/Downloads/data/long_design_png.png";
	public static final String PngHd_Long_Design_Expected_File_Path = "C:/Users/John/Downloads/data/long_design_png_hd.png";
	public static final String Png_Long_Design_Downloaded_File_Path = "C:/Users/John/Downloads/f7465fb9-91bd-4040-ab21-fe9d0ed48128.png";
	
	public static final String Pdf_All_Fonts_File_Path = "C:/Users/John/Downloads/867d3b36-e6d9-4af1-ad72-9fef01be68c2.pdf";
	public static final String Pdf_All_Fonts_Expected_File_Path = "C:/Users/John/Downloads/data/all_fonts_pdf.pdf";
	public static final String I_Pdf_All_Fonts_Expected_File_Path = "C:/Users/John/Downloads/data/all_fonts_i_pdf.pdf";
	
	//Mixpanel
	public static final String MIX_USERS_URL = "https://mixpanel.com/login/?next=/report/1172920/explore#list/filter:(conjunction:and,filters:!()),sort_order:descending,sort_property:'$last_seen',sort_property_type:datetime";
	public static final String MIX_USERNAME = "an@venngage.com";
	public static final String MIX_PASSWORD = "venngage0705";
	public static final String MIX_BASED_URL = "https://mixpanel.com/api/2.0/jql";
	public static final String MIX_API_SECRET = "9a38b41998b97c1fbf01dabaa4565651";
	public static final String MIX_API_SECRET_ENCODED = "OWEzOGI0MTk5OGI5N2MxZmJmMDFkYWJhYTQ1NjU2NTE6";
	public static final String MIX_JQL_DATE_FORMAT = "yyyy-MM-dd";
	
	//intercom api
	public static final String Intercom_Api_Base_Url = "https://api.intercom.io/events";
	public static final String Intercom_Api_Token = "dG9rOmY3ZDg5MmVmXzFjMjlfNDU5Nl9iNTMwXzhlN2YyYTY2MmFkZToxOjA=";
	
	//Stripe api
	public static final String Stripe_Base_Url = "https://api.stripe.com";
	public static final String Stripe_Secret_Key = "sk_test_rnmhqApeSGkkTy9m8YnpcKAW";
	
	//xml config
	public static final String xml_Browser_Tagname = "browser";
	public static final String xml_Email_Tagname = "email";
	public static final String xml_Password_Tagname = "password";
	public static final String xml_File_Path = "C:/venngage.xml";
	public static final String xml_api_Url_Tagname = "apiurl";
	public static final String xml_api_Secret_Encoded_Tagname = "apisecretencoded";
	
}
