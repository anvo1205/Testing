package Testcase;

import org.testng.annotations.Test;

import Function.EventPropertyException;
import Function.F_Editor;
import Function.F_Intercom_API;
import Function.F_Mixpanel_API;
import Function.F_TemplatePage;
import Interface.I_TemplatePage;
import Object.O_Account;
import Object.O_Onboarding;
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

public class MP_FreeSignUp2Publish extends BrowserStackTestNGTest{
	public static JSONArray actualEvents;
	public static JSONArray actualEvents_Mem;
	public static O_Account acc = new O_Account(Utils.generateRandomEmail("free"));
	public static O_Onboarding onb = new O_Onboarding("Other", "IT/Engineering", "Invitation");
	public static List<String> selectedOnbTemplates = new ArrayList<String>();
	public String infographId = "";
	public String infographTitle = "Test";

	@Test(priority = 0, enabled = true)
	public void beforeClass() throws InterruptedException {
		System.out.println(this.getClass().getName());
//		Utils.Initialize();
		System.out.println(acc.getEmail());
		Constants.driver.navigate().to(Constants.Free_Sign_Up_Url);
		acc.Register();
		selectedOnbTemplates =  onb.completeOnboarding(onb);
		F_TemplatePage.selecteTemplateCatetory(I_TemplatePage.lnk_CatLayouts);
		F_TemplatePage.clickBlankCanvas();
		F_Editor.skipEditorTourGuide();
		infographId = Constants.driver.getCurrentUrl().replace("https://infograph.venngage.beer/edit/", "");
		F_Editor.publishDesign(infographTitle);
		F_Editor.clickEmbedCode();
	}

	@Test(priority = 0, enabled = true)
	public void verifyEventsGenerated() throws IOException, JSONException, InterruptedException {
		List<String> exp_Events = new LinkedList<String>(Arrays.asList("Visit Homepage", "Register",
				"Load Onboarding Survey", "Click Onboarding Survey Answer", "Click Onboarding Survey Answer",
				"Click Onboarding Survey Answer", "Click Onboarding Survey Answer", "Click Onboarding Style Answer",
				"Click Onboarding Style Answer", "Click Onboarding Style Answer", "Finish Onboarding Survey",
				"Load My Templates Page", "Click Navigation", "Click On Create From Blank", "Finish Editor Tour Guide",
				"Click On Publish", "Embed Code Click", "Click on Content Gate onboarding"));
		// Call mixpanel api
		String jqlScript = F_Mixpanel_API.replaceJQLParams("", acc.getEmail());
		actualEvents = F_Mixpanel_API.callAPI(jqlScript, exp_Events.size());

		List<String> act_Events = new ArrayList<String>();
		for (int i = 0; i < actualEvents.length(); i++) {
			act_Events.add(actualEvents.getJSONObject(i).getString("name"));
		}
		assertTrue(F_Mixpanel_API.verifyGeneratedEventNames(exp_Events, act_Events));
	}

	@Test(priority = 1, enabled = true)
	public void e_onbSurveyAnwser() throws JSONException {
		String eventName = "Click Onboarding Survey Answer";
		String org = "other";
		String role = "it-eng";
		String goal = "invitations";

		JSONObject event = F_Mixpanel_API.getEventByProperty(actualEvents, eventName, "organization");
		assertTrue(F_Mixpanel_API.verifyEventProperties(event, "organization", org));
		event = F_Mixpanel_API.getEventByProperty(actualEvents, eventName, "role");
		assertTrue(F_Mixpanel_API.verifyEventProperties(event, "role", role));
		event = F_Mixpanel_API.getEventByProperty(actualEvents, eventName, "goal");
		assertTrue(F_Mixpanel_API.verifyEventProperties(event, "goal", goal));
	}

	@Test(priority = 2, enabled = true)
	public void e_FinishOnbSurvey() throws JSONException {
		String eventName = "Finish Onboarding Survey";
		String org = "other";
		String role = "it-eng";
		String goal = "invitations";

		JSONObject event = F_Mixpanel_API.getEventByName(actualEvents, eventName);
		assertTrue(F_Mixpanel_API.verifyEventProperties(event, "organization", org));
		assertTrue(F_Mixpanel_API.verifyEventProperties(event, "role", role));
		assertTrue(F_Mixpanel_API.verifyEventProperties(event, "goal", goal));
		
	}

	@Test(priority = 3, enabled = true)
	public void e_ClickNavigation() throws JSONException {
		String eventName = "Click Navigation";
		String cat = "layouts";
		String subcat = "";

		JSONObject actualEvent = F_Mixpanel_API.getEventByName(actualEvents, eventName);
		assertTrue(F_Mixpanel_API.verifyEventProperties(actualEvent, "category", cat));
		assertTrue(F_Mixpanel_API.verifyEventProperties(actualEvent, "subcategory", subcat));
	}

	@Test(priority = 4, enabled = true)
	public void e_ClickOnCreateFromBlank() throws JSONException {
		String eventName = "Click On Create From Blank";
		String id = "blank";

		JSONObject actualEvent = F_Mixpanel_API.getEventByName(actualEvents, eventName);
		assertTrue(F_Mixpanel_API.verifyEventProperties(actualEvent, "id", id));
	}

	@Test(priority = 5, enabled = true)
	public void e_FinishEditorTourGuide() throws JSONException {
		String eventName = "Finish Editor Tour Guide";
		String step = "0";
		String version = "experiment-1";

		JSONObject actualEvent = F_Mixpanel_API.getEventByName(actualEvents, eventName);
		assertTrue(F_Mixpanel_API.verifyEventProperties(actualEvent, "step", step));
		assertTrue(F_Mixpanel_API.verifyEventProperties(actualEvent, "tourGuideVersion", version));
	}

	@Test(priority = 6, enabled = true)
	public void e_ClickOnPublish() throws JSONException {
		String eventName = "Click On Publish";

		JSONObject event = F_Mixpanel_API.getEventByName(actualEvents, eventName);
		assertTrue(F_Mixpanel_API.verifyEventProperties(event, "templateId", ""));
	}
	
	@Test(priority=7, enabled = true)
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
	
	@Test(priority = 8, enabled = true)
	public void intercomEvents() throws JSONException
	{
		List<String> expectedEvents = Arrays.asList("create infographic", "publish");
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
		assertTrue(F_Intercom_API.verifyEventProperty(e_Create, "template", "blank"));
		assertTrue(F_Intercom_API.verifyEventProperty(e_Create, "infographic_id", infographId));
		assertTrue(F_Intercom_API.verifyEventProperty(e_Create, "type", type));
		
		//Verify publish event
		JSONObject e_Publish = F_Intercom_API.getEventByName(actualEvents, "publish");
		try
		{
			assertTrue(F_Intercom_API.verifyEventProperty(e_Publish, "infographic_id", infographId));
		}
		catch (AssertionError e)
		{
			System.out.println("bug: https://trello.com/c/U30FM84H/2918-mixpanel-infographic-property-of-download-and-share-events-returns-pageidentifier-instead-of-actual-infographic-id");
		}
		assertTrue(F_Intercom_API.verifyEventProperty(e_Publish, "title", infographTitle));
	}

//	@AfterClass
//	public void afterClass() {
//		Constants.driver.quit();
//	}

}
