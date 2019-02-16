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
import org.testng.annotations.AfterClass;

public class MP_QBuzSignUp2PNGHDDownload {

	public JSONArray actualEvents;
	public JSONArray actualEvents_Mem;
	public O_Account acc = new O_Account(Utils.generateRandomEmail("buzQ"));
	public O_Onboarding onb = new O_Onboarding("Enterprise", "Human Resources", "Report");
	public O_Payment payment = new O_Payment(Constants.MasterDebit);
	public List<String> selectedOnbTemplates = new ArrayList<String>();
	public String createdTemplateId = "";
	public String createdTemplateTitle = "";
	public String infographId = "";

	@BeforeClass
	public void beforeClass() throws InterruptedException {
		Utils.Initialize();
		System.out.println(this.getClass().getName());
		System.out.println(acc.getEmail());
		Constants.driver.navigate().to(Constants.BuzQ_SignUp_Url);
		acc.Register();
		payment.upgrade(payment);
		O_Payment.clickGetStartedOnBrandKitModal1();
		selectedOnbTemplates = onb.completeOnboarding(onb);
		createdTemplateId = F_TemplatePage.getIdFirstTemplate();
		createdTemplateTitle = F_TemplatePage.getTitleFirstTemplate();
		F_TemplatePage.clickOnCreate1stTemplate();
		F_Editor.skipEditorTourGuide();
		infographId = Constants.driver.getCurrentUrl().replace("https://infograph.venngage.beer/edit/", "");
		F_Editor.downloadMultipageDesign("PNG HD");
		F_Editor.waitUntilDownloadComplete(Constants.Download_Destination + "/" + infographId + ".png", 30);
		
		//Cancel business account
		Constants.driver.navigate().to(Constants.Subscriptions_Url);
		F_MyAccount.cancelSubscription("7", 4, "No complain about Venngage. Great experience!");
	}

	@Test(priority=0, enabled = true)
	public void verifyEventsGenerated() throws IOException, JSONException, InterruptedException {
		List<String> exp_Events = new LinkedList<String>(Arrays.asList("Visit Homepage", "Register",
				"Upgrade Business Modal", "Upgrade Business", "Brand Onboarding Modal", "Load Onboarding Survey",
				"Click Onboarding Survey Answer", "Click Onboarding Survey Answer", "Click Onboarding Survey Answer",
				"Click Onboarding Survey Answer", "Click Onboarding Style Answer", "Click Onboarding Style Answer",
				"Click Onboarding Style Answer", "Finish Onboarding Survey", "Load My Templates Page",
				"Templates Tour Guide", "Click On Create From Templates", "Finish Editor Tour Guide"
				, "Download", "Click on Content Gate onboarding", "Click on Cancel Subscription", "Cancelled Business"));

		// Call mixpanel api
		String jqlScript = F_Mixpanel_API.replaceJQLParams("", acc.getEmail());
		actualEvents = F_Mixpanel_API.callAPI(jqlScript, exp_Events.size());
		List<String> act_Events = new ArrayList<String>();
		for (int i = 0; i < actualEvents.length(); i++) {
			act_Events.add(actualEvents.getJSONObject(i).getString("name"));
		}
		assertTrue(F_Mixpanel_API.verifyGeneratedEventNames(exp_Events, act_Events));
	}
	
	@Test(priority=1, enabled = true)
	public void e_ClickOnbSurveyAnwser() throws JSONException, EventPropertyException{
		String eventName = "Click Onboarding Survey Answer";
		String org = "enterprise";
		String role = "hr";
		String goal = "reports";
		
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
		String org = "enterprise";
		String role = "hr";
		String goal = "reports";
		
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
		String type = "png hd";
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
		String plan = "business";
		
		JSONObject event = F_Mixpanel_API.getEventByName(actualEvents, eventName);
		assertTrue(F_Mixpanel_API.verifyEventProperties(event, "Button Number", buttonNum));
		assertTrue(F_Mixpanel_API.verifyEventProperties(event, "Plan", plan));
	}

	@Test(priority = 8, enabled = true)
	public void intercomEvents() throws JSONException
	{
		List<String> expectedEvents = Arrays.asList("upgrade business admin","create infographic"
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
		String downloadType = "hd_png";
		JSONObject e_Export = F_Intercom_API.getEventByName(actualEvents, "export");
		assertTrue(F_Intercom_API.verifyEventProperty(e_Export, "method", method));
		assertTrue(F_Intercom_API.verifyEventProperty(e_Export, "infographic_id", infographId));
		assertTrue(F_Intercom_API.verifyEventProperty(e_Export, "type", downloadType));
	}
	
	@AfterClass
	public void afterClass() {
		Constants.driver.quit();
	}

}
