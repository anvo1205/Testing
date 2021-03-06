package Testcase;

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

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

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

/* Description: This test will test all events from visit home page, sign up as business until publish design
 * Test steps:
 ******* 1.Visit Home page 
 ******* 2. Go to monthly business sign up, fill in all information and click Sign up
 ******* 3. Complete onboarding survey
 ******* 4. Click on create from 1st template
 ******* 5. On Editor, publish design
 */

public class MP_BuzSignUp2Publish {

	public JSONArray actualEvents;
	public JSONArray expectedEvents;
	public O_Account acc = new O_Account(Utils.generateRandomEmail(""));
	public O_Onboarding onb = new O_Onboarding("Small Business", "Marketer/PR", "Presentation");
	public O_Payment payment = new O_Payment();
	public List<String> selectedOnbTemplates = new ArrayList<String>();
	public String templateId = "";
	public String title ="love actually poster";
	public String infographId = "";
	
	@BeforeClass
	public void beforeMethod() throws InterruptedException, IOException, JSONException, EventPropertyException {
		Utils.Initialize();
		System.out.println(this.getClass().getName());
		System.out.println(acc.getEmail());
		Constants.driver.navigate().to(Constants.BuzM_SignUp_Url);
		acc.Register();
		payment.upgrade(payment);
		O_Payment.clickGetStartedOnBrandKitModal1();
		selectedOnbTemplates = onb.completeOnboarding(onb);
		F_TemplatePage.searchTemplate(title);
		//title = F_TemplatePage.getTitleFirstTemplate();
		templateId = F_TemplatePage.getIdFirstTemplate();
		F_TemplatePage.clickOnCreate1stTemplate();
		F_Editor.skipEditorTourGuide();
		infographId = Constants.driver.getCurrentUrl().replace("https://infograph.venngage.beer/edit/", "");
		F_Editor.publishDesign("");
		
		//Cancel subscription
		Constants.driver.navigate().to(Constants.Subscriptions_Url);
		F_MyAccount.cancelSubscription("10", 1, "");
	}

	@AfterClass
	public void afterMethod() {
		Constants.driver.quit();
	}
	
	@Test(priority=0, enabled = false)
	public void e_EventNames() throws JSONException, IOException, InterruptedException
	{
		//Define list of expected events
		List<String> exp_Event_Names = new LinkedList<String>(Arrays.asList("Visit Homepage", "Register","Upgrade Business Modal", "Upgrade Business"
								  , "Brand Onboarding Modal", "Load Onboarding Survey", "Click Onboarding Survey Answer", "Click Onboarding Survey Answer"
								  , "Click Onboarding Survey Answer", "Click Onboarding Survey Answer", "Click Onboarding Style Answer"
								  , "Click Onboarding Style Answer", "Click Onboarding Style Answer", "Finish Onboarding Survey"
								  , "Load My Templates Page", "Templates Search", "Templates Tour Guide", "Click On Create From Templates"
								  , "Finish Editor Tour Guide", "Click On Publish", "Click on Content Gate onboarding"
								  , "Click on Cancel Subscription", "Cancelled Business"));
		//Calling mixpanel api
		String jqlScript = F_Mixpanel_API.replaceJQLParams("",acc.getEmail());
		actualEvents = F_Mixpanel_API.callAPI(jqlScript, exp_Event_Names.size());
		List<String> actual_Event_Names = new ArrayList<String>();
		for (int i = 0; i < actualEvents.length(); i++)
		{
			actual_Event_Names.add(actualEvents.getJSONObject(i).getString("name"));
		}
		try
		{
			assertTrue(F_Mixpanel_API.verifyGeneratedEventNames(exp_Event_Names, actual_Event_Names));
		}
		catch (AssertionError e)
		{
			System.out.println("bug: https://trello.com/c/5VTve9qn/173-mixpanel-template-search-event-is-generated-twice-after-searching-template-with-special-characters");
		}
	}
	
	@Test(priority=1, enabled = false)
	public void e_BrandOnbModal() throws JSONException {
		String eventName = "Brand Onboarding Modal";
		String step1 = "true";
		String buttonNum1 = "Get Started";

		JSONObject eventStep1 = F_Mixpanel_API.getEventByProperty(actualEvents, eventName, "Step 1");
		assertTrue(F_Mixpanel_API.verifyEventProperties(eventStep1, "Step 1", step1));
		assertTrue(F_Mixpanel_API.verifyEventProperties(eventStep1, "Button Number", buttonNum1));
	}
	
	@Test(priority=2, enabled = false)
	public void e_FinishOnbSurvey() throws JSONException {
		String eventName = "Finish Onboarding Survey";
		String org = "sm-business";
		String role = "marketer";
		String goal = "presentations";

		JSONObject event = F_Mixpanel_API.getEventByName(actualEvents, eventName);
		assertTrue(F_Mixpanel_API.verifyEventProperties(event, "organization", org));
		assertTrue(F_Mixpanel_API.verifyEventProperties(event, "role", role));
		assertTrue(F_Mixpanel_API.verifyEventProperties(event, "goal", goal));
	}
	
	@Test(priority=3, enabled = false)
	public void e_TemplatesSearch() throws JSONException {
		String eventName = "Templates Search";
		String locale = "en";

		JSONObject event = F_Mixpanel_API.getEventByName(actualEvents, eventName);
		assertTrue(F_Mixpanel_API.verifyEventProperties(event, "query", title));
		assertTrue(F_Mixpanel_API.verifyEventProperties(event, "locale", locale));
	}
	
	@Test(priority=4, enabled = false)
	public void e_ClickOnCreateFromTemplate() throws JSONException, EventPropertyException {
		String eventName = "Click On Create From Templates";
		String cat = "search";
		String multipage = "true";
		String plan = "free";
		String subcat = "";

		JSONObject event = F_Mixpanel_API.getEventByName(actualEvents, eventName);
		assertTrue(F_Mixpanel_API.verifyEventProperties(event, "category", cat));
		assertTrue(F_Mixpanel_API.verifyEventProperties(event, "id", templateId));
		assertTrue(F_Mixpanel_API.verifyEventProperties(event, "multipage", multipage));
		assertTrue(F_Mixpanel_API.verifyEventProperties(event, "template plan", plan));
		assertTrue(F_Mixpanel_API.verifyEventProperties(event, "subcategory", subcat));
		assertTrue(F_Mixpanel_API.verifyEventProperties(event, "title", title));
	}
	
	@Test(priority=5, enabled = false)
	public void e_FinishEditorTourGuide() throws JSONException, EventPropertyException {
		String eventName = "Finish Editor Tour Guide";
		String step = "0";
		String tourGuideVersion = "experiment-1";
		
		JSONObject event = F_Mixpanel_API.getEventByName(actualEvents, eventName);
		assertTrue(F_Mixpanel_API.verifyEventProperties(event, "step", step));
		assertTrue(F_Mixpanel_API.verifyEventProperties(event, "tourGuideVersion", tourGuideVersion));
	}
	
	@Test(priority=6, enabled = false)
	public void e_ClickOnPublish() throws JSONException, EventPropertyException {
		String eventName = "Click On Publish";
		JSONObject event = F_Mixpanel_API.getEventByName(actualEvents, eventName);
		assertTrue(F_Mixpanel_API.verifyEventProperties(event, "templateId", templateId));
	}
	
	@Test(priority=7, enabled = false)
	public void e_ClickOnbSurveyAnwser() throws JSONException, EventPropertyException {
		String eventName = "Click Onboarding Survey Answer";
		String org = "sm-business";
		String role = "marketer";
		String goal = "presentations";
		JSONObject event = F_Mixpanel_API.getEventByProperty(actualEvents, eventName, "organization");
		assertTrue(F_Mixpanel_API.verifyEventProperties(event, "organization", org));
		event = F_Mixpanel_API.getEventByProperty(actualEvents, eventName, "role");
		assertTrue(F_Mixpanel_API.verifyEventProperties(event, "role", role));
		event = F_Mixpanel_API.getEventByProperty(actualEvents, eventName, "goal");
		assertTrue(F_Mixpanel_API.verifyEventProperties(event, "goal", goal));
	}
	
	@Test(priority=8, enabled = false)
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
	
	@Test(priority=9, enabled = false)
	public void e_ClickCancelSubscription() throws JSONException, EventPropertyException, IOException {
		String eventName = "Click on Cancel Subscription";
		String buttonNum = "Cancel My Subscription";
		String plan = "Business";
		
		JSONObject event = F_Mixpanel_API.getEventByName(actualEvents, eventName);
		assertTrue(F_Mixpanel_API.verifyEventProperties(event, "Button Number", buttonNum));
		assertTrue(F_Mixpanel_API.verifyEventProperties(event, "Plan", plan));
	}
	
	@Test(priority = 10, enabled = false)
	public void intercomEvents() throws JSONException
	{
		List<String> expectedEvents = Arrays.asList("upgrade business admin" ,"create infographic"
													, "publish", "cancelled subscription");
		String type = "create infographic from template";
		JSONArray actualEvents = F_Intercom_API.callIntercomAPI(acc.getEmail());
		List<String> actualEventNames = new ArrayList<String>();
		for (int i = 0; i<actualEvents.length(); i++)
		{
			actualEventNames.add(actualEvents.getJSONObject(i).getString("event_name"));
		}
		assertTrue(F_Intercom_API.verifyListEventNames(expectedEvents, actualEventNames));
		
		//Verify create infographic event
		JSONObject e_Create = F_Intercom_API.getEventByName(actualEvents, "create infographic");
		assertTrue(F_Intercom_API.verifyEventProperty(e_Create, "template", templateId));
		assertTrue(F_Intercom_API.verifyEventProperty(e_Create, "infographic_id", infographId));
		assertTrue(F_Intercom_API.verifyEventProperty(e_Create, "type", type));
		
		//Verify publish event
		JSONObject e_Publish = F_Intercom_API.getEventByName(actualEvents, "publish");
		try
		{
			assertTrue(F_Intercom_API.verifyEventProperty(e_Create, "infographic_id", infographId));
		}
		catch (AssertionError e)
		{
			System.out.println("bug: https://trello.com/c/U30FM84H/2918-mixpanel-infographic-property-of-download-and-share-events-returns-pageidentifier-instead-of-actual-infographic-id");
		}
		assertTrue(F_Intercom_API.verifyEventProperty(e_Publish, "title", title));
	}

}
