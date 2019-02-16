package Testcase;

import static org.testng.Assert.assertTrue;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import Function.EventPropertyException;
import Function.F_Editor;
import Function.F_Intercom_API;
import Function.F_Mixpanel_API;
import Function.F_MyAccount;
import Function.F_TemplatePage;
import Object.O_Account;
import Object.O_Onboarding;
import Object.O_Payment;
import Utility.Constants;
import Utility.Utils;

/* Description: This test will test all events from visit home page,
 * sign up as premium until download design as png
 * Test steps:
 ******* 1.Visit Home page 
 ******* 2. Go to quarterly premium sign up, fill in all information and click Sign up
 ******* 3. Complete onboarding survey
 ******* 4. Click on create from a template
 ******* 5. Click on PNG download
 ******* 6. Verify events generated
 */

public class MP_MPreSignUp2PNGDownload extends BrowserStackTestNGTest{
	
	public static JSONArray actualEvents;
	public static O_Account acc = new O_Account(Utils.generateRandomEmail("preM"));
	public static O_Onboarding onb = new O_Onboarding("Nonprofit/NGO", "Program Manager", "Poster");
	public static O_Payment payment = new O_Payment(Constants.MasterCard);
	public static List<String> selectedOnbTemplates = new ArrayList<String>();
	public static String createdTemplateId = "";
	public static String createdTemplateTitle = "";
	public String infographId = "";
	
	@BeforeClass
	public void beforeMethod() throws InterruptedException, IOException, JSONException, EventPropertyException {
//		Utils.Initialize();
		System.out.println(this.getClass().getName());
		System.out.println(acc.getEmail());
		Constants.driver.navigate().to(Constants.PreM_SignUp_Url);
		acc.Register();
		payment.upgrade(payment);
		O_Payment.clickContinueOnPremiumModal();
		selectedOnbTemplates = onb.completeOnboarding(onb);
		createdTemplateId = F_TemplatePage.getIdFirstTemplate();
		createdTemplateTitle = F_TemplatePage.getTitleFirstTemplate();
		F_TemplatePage.clickOnCreate1stTemplate();
		F_Editor.skipEditorTourGuide();
		infographId = Constants.driver.getCurrentUrl().replace("https://infograph.venngage.beer/edit/", "");
		F_Editor.downloadMultipageDesign("PNG");
		F_Editor.waitUntilDownloadComplete(Constants.Download_Destination + "/" + infographId + ".png", 30);
		
		//Cancel premium account
		Constants.driver.navigate().to(Constants.Subscriptions_Url);
		F_MyAccount.cancelSubscription("8", 3, "Venngage is great! I will come back shortly. Thanks!");
	}
	
	@Test(priority=0, enabled = true)
	public void verifyEventsGenerated() throws JSONException, IOException, InterruptedException
	{
		List<String> actEvents = new ArrayList<String>();
		List<String> expectedEvents = new LinkedList<String>
					(Arrays.asList("Visit Homepage", "Register", "Upgrade Premium Modal", "Upgrade Premium",
								   "Load Onboarding Survey", "Click Onboarding Survey Answer", "Click Onboarding Survey Answer",
								   "Click Onboarding Survey Answer", "Click Onboarding Survey Answer", "Click Onboarding Style Answer",
								   "Click Onboarding Style Answer", "Click Onboarding Style Answer", "Finish Onboarding Survey",
								   "Load My Templates Page", "Templates Tour Guide", "Click On Create From Templates",
								   "Finish Editor Tour Guide", "Download", "Click on Content Gate onboarding"
								   , "Click on Cancel Subscription", "Cancelled Premium"));
		// Call mixpanel api
		String jqlScript = F_Mixpanel_API.replaceJQLParams("", acc.getEmail());
		actualEvents = F_Mixpanel_API.callAPI(jqlScript, expectedEvents.size());
		for (int i = 0; i < actualEvents.length(); i++) {
			actEvents.add(actualEvents.getJSONObject(i).getString("name"));
		}
		assertTrue(F_Mixpanel_API.verifyGeneratedEventNames(expectedEvents, actEvents));
	}
	
	@Test(priority=1, enabled = true)
	public void e_ClickOnbSurveyAnwser() throws JSONException, EventPropertyException{
		String eventName = "Click Onboarding Survey Answer";
		String org = "nonprofit";
		String role = "progmngr";
		String goal = "posters";
		
		JSONObject event = F_Mixpanel_API.getEventByProperty(actualEvents, eventName, "organization");
		assertTrue(F_Mixpanel_API.verifyEventProperties(event, "organization", org));
		event = F_Mixpanel_API.getEventByProperty(actualEvents, eventName, "role");
		assertTrue(F_Mixpanel_API.verifyEventProperties(event, "role", role));
		event = F_Mixpanel_API.getEventByProperty(actualEvents, eventName, "goal");
		assertTrue(F_Mixpanel_API.verifyEventProperties(event, "goal", goal));
	}
	
	@Test(priority=2, enabled = true)
	public void e_ClickOnbStyleAnwser() throws JSONException, EventPropertyException, IOException {
		String eventName = "Click Onboarding Style Answer";
		JSONArray actual_Events = F_Mixpanel_API.getEventsSameName(actualEvents, eventName);
		List<String> actual_TemplateIds = new ArrayList<String>();
		for (int i = 0; i <actual_Events.length(); i++)
		{
			actual_TemplateIds.add(actual_Events.getJSONObject(i).getJSONObject("properties").getString("templateId").toString());
		}
		Collections.sort(selectedOnbTemplates);
		Collections.sort(actual_TemplateIds);
		assertTrue(F_Mixpanel_API.verifyGeneratedEventNames(selectedOnbTemplates, actual_TemplateIds));
	}
	
	@Test(priority=3, enabled = true)
	public void e_FinishOnbSurvey() throws JSONException, EventPropertyException{
		String eventName = "Finish Onboarding Survey";
		String org = "nonprofit";
		String role = "progmngr";
		String goal = "posters";
		
		JSONObject event = F_Mixpanel_API.getEventByName(actualEvents, eventName);
		assertTrue(F_Mixpanel_API.verifyEventProperties(event, "organization", org));
		assertTrue(F_Mixpanel_API.verifyEventProperties(event, "role", role));
		assertTrue(F_Mixpanel_API.verifyEventProperties(event, "goal", goal));
	}
	
	@Test(priority = 4, enabled = true)
	public void e_ClickOnCreateFromTemplates() throws JSONException {
		String eventName = "Click On Create From Templates";
		String cat = "recommended";
		String multipage = "true";
		String plan = "free";
		String subcat = "";

		JSONObject event = F_Mixpanel_API.getEventByName(actualEvents, eventName);
		assertTrue(F_Mixpanel_API.verifyEventProperties(event, "category", cat));
		assertTrue(F_Mixpanel_API.verifyEventProperties(event, "multipage", multipage));
		assertTrue(F_Mixpanel_API.verifyEventProperties(event, "template plan", plan));
		assertTrue(F_Mixpanel_API.verifyEventProperties(event, "subcategory", subcat));
		assertTrue(F_Mixpanel_API.verifyEventProperties(event, "title", createdTemplateTitle));
	}

	@Test(priority = 5, enabled = true)
	public void e_FinishEditorTourGuide() throws JSONException {
		String eventName = "Finish Editor Tour Guide";
		String step = "0";
		String tourGuideVersion = "experiment-1";

		JSONObject event = F_Mixpanel_API.getEventByName(actualEvents, eventName);
		assertTrue(F_Mixpanel_API.verifyEventProperties(event, "step", step));
		assertTrue(F_Mixpanel_API.verifyEventProperties(event, "tourGuideVersion", tourGuideVersion));
	}
	
	@Test(priority=6, enabled = true)
	public void e_Download() throws JSONException, EventPropertyException{
		String eventName = "Download";
		String type = "png";
		String server = "Headless Chrome";
		String editor = "Multipage";
		
		JSONObject event = F_Mixpanel_API.getEventByName(actualEvents, eventName);
		assertTrue(F_Mixpanel_API.verifyEventProperties(event, "type", type));
		assertTrue(F_Mixpanel_API.verifyEventProperties(event, "editor", editor));
		assertTrue(F_Mixpanel_API.verifyEventProperties(event, "server", server));
		//assertTrue(F_Mixpanel_API.verifyEventProperties(event, "infographic", infographId));
		assertTrue(F_Mixpanel_API.verifyEventProperties(event, "templateId", createdTemplateId));
	}
	
	@Test(priority=7, enabled = true)
	public void e_ClickCancelSubscription() throws JSONException, EventPropertyException, IOException {
		String eventName = "Click on Cancel Subscription";
		String buttonNum = "Cancel My Subscription";
		String plan = "premium";
		
		JSONObject event = F_Mixpanel_API.getEventByName(actualEvents, eventName);
		assertTrue(F_Mixpanel_API.verifyEventProperties(event, "Button Number", buttonNum));
		assertTrue(F_Mixpanel_API.verifyEventProperties(event, "Plan", plan));
	}
	
	@Test(priority = 8, enabled = true)
	public void intercomEvents() throws JSONException
	{
		List<String> expectedEvents = Arrays.asList("upgrade premium","create infographic"
												  , "export", "cancelled subscription");
		JSONArray actualEvents = F_Intercom_API.callIntercomAPI(acc.getEmail());
		List<String> actualEventNames = new ArrayList<String>();
		for (int i = 0; i<actualEvents.length(); i++)
		{
			actualEventNames.add(actualEvents.getJSONObject(i).getString("event_name"));
		}
		assertTrue(F_Intercom_API.verifyListEventNames(expectedEvents, actualEventNames));
		
		//verify create infographic event
		String createtType = "create infographic from template";
		JSONObject e_Create = F_Intercom_API.getEventByName(actualEvents, "create infographic");
		assertTrue(F_Intercom_API.verifyEventProperty(e_Create, "template", createdTemplateId));
		assertTrue(F_Intercom_API.verifyEventProperty(e_Create, "infographic_id", infographId));
		assertTrue(F_Intercom_API.verifyEventProperty(e_Create, "type", createtType));
		
		//verify export event
		String method = "server";
		String downloadType = "png";
		JSONObject e_Export = F_Intercom_API.getEventByName(actualEvents, "export");
		assertTrue(F_Intercom_API.verifyEventProperty(e_Export, "method", method));
		assertTrue(F_Intercom_API.verifyEventProperty(e_Export, "infographic_id", infographId));
		assertTrue(F_Intercom_API.verifyEventProperty(e_Export, "type", downloadType));
	}
	
	@AfterClass
	public void tearDown()
	{
		Constants.driver.quit();
	}
	
}
