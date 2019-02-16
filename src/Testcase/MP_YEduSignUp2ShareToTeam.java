package Testcase;

import org.testng.annotations.Test;

import Function.EventPropertyException;
import Function.F_Editor;
import Function.F_Intercom_API;
import Function.F_Mixpanel_API;
import Function.F_MyAccount;
import Function.F_MyHome;
import Function.F_SignIn;
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

public class MP_YEduSignUp2ShareToTeam {

	public JSONArray actualEvents;
	public JSONArray actualEvents_Mem;
	public O_Account acc = new O_Account(Utils.generateRandomEmail("eduowner"));
	public O_Onboarding onb = new O_Onboarding("School/University", "Teacher/Professor", "Presentation");
	public List<String> selectedOnbTemplates = new ArrayList<String>();
	public String createdTemplateId = "";
	public String createdTemplateTitle = "";
	public String infographId = "";

	public O_Account mem_Acc = new O_Account(Utils.generateRandomEmail("edumem"));
	public O_Onboarding mem_Onb = new O_Onboarding("School/University", "Student", "Poster");
	public O_Payment payment = new O_Payment(Constants.AmericanExpress2);
	public List<String> mem_SelectedOnbTemplates = new ArrayList<String>();
	public String mem_CreatedTemplateId = "";
	public String mem_CreatedTemplateTitle = "";
	public String mem_InfographId = "";

	@BeforeClass
	public void beforeClass() throws InterruptedException, IOException {
		Utils.Initialize();
		System.out.println(this.getClass().getName());
		System.out.println("Owner acc: " + acc.getEmail());
		System.out.println("Member acc: " + mem_Acc.getEmail());
		Constants.driver.navigate().to(Constants.EduY_SignUp_Url);
		acc.Register();
		payment.upgrade(payment);
		O_Payment.clickContinueOnPremiumModal();
		selectedOnbTemplates = onb.completeOnboarding(onb);
		createdTemplateId = F_TemplatePage.getIdFirstTemplate();
		createdTemplateTitle = F_TemplatePage.getTitleFirstTemplate();
		F_TemplatePage.clickOnCreate1stTemplate();
		F_Editor.skipEditorTourGuide();
		infographId = Constants.driver.getCurrentUrl().replace("https://infograph.venngage.beer/edit/", "");
		F_Editor.clickShareMenu();
		F_Editor.clickTeamShare();

		// Add edu member
		F_MyAccount.addEduMember(mem_Acc.getEmail());
		F_MyAccount.verifyAddMemSucess(mem_Acc.getEmail());
		F_MyHome.signOut();
		// Sign up as an edu member
		F_SignIn.clickSignUp();
		mem_Acc.Register();
		mem_SelectedOnbTemplates = mem_Onb.completeOnboarding(mem_Onb);
		// F_MyAccount.verifyPlanLogo("EDUCATION");
		mem_CreatedTemplateId = F_TemplatePage.getIdFirstTemplate();
		mem_CreatedTemplateTitle = F_TemplatePage.getTitleFirstTemplate();
		F_TemplatePage.clickOnCreate1stTemplate();
		F_Editor.finishEditorTourGuide();
		mem_InfographId = Constants.driver.getCurrentUrl().replace("https://infograph.venngage.beer/edit/", "");
		F_Editor.clickShareMenu();
		F_Editor.clickTeamShare();
	}

	@Test(priority = 1, enabled = true)
	public void verifyEventsGenerated_Owner() throws IOException, JSONException, InterruptedException {
		List<String> expectedEvents = new LinkedList<String>(Arrays.asList("Visit Homepage", "Register",
				"Upgrade Premium Modal", "Upgrade Education Modal", "Upgrade Education", "Load Onboarding Survey",
				"Click Onboarding Survey Answer", "Click Onboarding Survey Answer", "Click Onboarding Survey Answer",
				"Click Onboarding Survey Answer", "Click Onboarding Style Answer", "Click Onboarding Style Answer",
				"Click Onboarding Style Answer", "Finish Onboarding Survey", "Load My Templates Page",
				"Templates Tour Guide", "Click On Create From Templates", "Share", "Finish Editor Tour Guide",
				"Sign Out", "Click on Content Gate onboarding"));
		// Call mixpanel api
		String jqlScript = F_Mixpanel_API.replaceJQLParams("", acc.getEmail());
		actualEvents = F_Mixpanel_API.callAPI(jqlScript, expectedEvents.size());
		List<String> act_Events = new ArrayList<String>();
		for (int i = 0; i < actualEvents.length(); i++) {
			act_Events.add(actualEvents.getJSONObject(i).getString("name"));
		}
		assertTrue(F_Mixpanel_API.verifyGeneratedEventNames(expectedEvents, act_Events));
	}

	@Test(priority = 2, enabled = true)
	public void e_OnbSurveyAnwser_Owner() throws JSONException {
		String eventName = "Click Onboarding Survey Answer";
		String org = "school";
		String role = "prof";
		String goal = "presentations";

		JSONObject event = F_Mixpanel_API.getEventByProperty(actualEvents, eventName, "organization");
		assertTrue(F_Mixpanel_API.verifyEventProperties(event, "organization", org));
		event = F_Mixpanel_API.getEventByProperty(actualEvents, eventName, "role");
		assertTrue(F_Mixpanel_API.verifyEventProperties(event, "role", role));
		event = F_Mixpanel_API.getEventByProperty(actualEvents, eventName, "goal");
		assertTrue(F_Mixpanel_API.verifyEventProperties(event, "goal", goal));
	}

	@Test(priority = 3, enabled = true)
	public void e_FinishOnbSurvey_Owner() throws JSONException {
		String eventName = "Finish Onboarding Survey";
		String org = "school";
		String role = "prof";
		String goal = "presentations";

		JSONObject event = F_Mixpanel_API.getEventByName(actualEvents, eventName);
		assertTrue(F_Mixpanel_API.verifyEventProperties(event, "organization", org));
		assertTrue(F_Mixpanel_API.verifyEventProperties(event, "role", role));
		assertTrue(F_Mixpanel_API.verifyEventProperties(event, "goal", goal));
	}

	@Test(priority = 4, enabled = true)
	public void e_ClickOnbStyleAnwser_Owner() throws JSONException, EventPropertyException, IOException {
		String eventName = "Click Onboarding Style Answer";
		JSONArray actual_Events = F_Mixpanel_API.getEventsSameName(actualEvents, eventName);
		List<String> actual_TemplateIds = new ArrayList<String>();
		for (int i = 0; i < actual_Events.length(); i++) {
			actual_TemplateIds
					.add(actual_Events.getJSONObject(i).getJSONObject("properties").getString("templateId").toString());
		}
		Collections.sort(selectedOnbTemplates);
		Collections.sort(actual_TemplateIds);
		assertTrue(F_Mixpanel_API.verifyGeneratedEventNames(selectedOnbTemplates, actual_TemplateIds));
	}

	@Test(priority = 5, enabled = true)
	public void e_ClickOnCreateFromTemplate_Owner() throws JSONException, EventPropertyException {
		String eventName = "Click On Create From Templates";
		String cat = "recommended";
		String multipage = "true";
		String plan = "free";
		String subcat = "";

		JSONObject event = F_Mixpanel_API.getEventByName(actualEvents, eventName);
		assertTrue(F_Mixpanel_API.verifyEventProperties(event, "category", cat));
		assertTrue(F_Mixpanel_API.verifyEventProperties(event, "id", createdTemplateId));
		assertTrue(F_Mixpanel_API.verifyEventProperties(event, "multipage", multipage));
		assertTrue(F_Mixpanel_API.verifyEventProperties(event, "template plan", plan));
		assertTrue(F_Mixpanel_API.verifyEventProperties(event, "subcategory", subcat));
		assertTrue(F_Mixpanel_API.verifyEventProperties(event, "title", createdTemplateTitle));
	}

	@Test(priority = 6, enabled = true)
	public void e_FinishEditorTourGuide() throws JSONException {
		String eventName = "Finish Editor Tour Guide";
		String step = "0";
		String tourGuideVersion = "experiment-1";

		JSONObject event = F_Mixpanel_API.getEventByName(actualEvents, eventName);
		assertTrue(F_Mixpanel_API.verifyEventProperties(event, "step", step));
		assertTrue(F_Mixpanel_API.verifyEventProperties(event, "tourGuideVersion", tourGuideVersion));
	}

	@Test(priority = 7, enabled = true)
	public void e_Share_Owner() throws JSONException {
		String eventName = "Share";
		String buttonNum = "Share with team";
		String location = "Share Panel";
		String by = "With Team";
		String page = "Editor";

		JSONObject event = F_Mixpanel_API.getEventByName(actualEvents, eventName);
		assertTrue(F_Mixpanel_API.verifyEventProperties(event, "Button Number", buttonNum));
		assertTrue(F_Mixpanel_API.verifyEventProperties(event, "Via", by));
		assertTrue(F_Mixpanel_API.verifyEventProperties(event, "Location", location));
		assertTrue(F_Mixpanel_API.verifyEventProperties(event, "Page", page));
		// assertTrue(F_Mixpanel_API.verifyEventProperties(event, "Infographic",
		// infographId));
		assertTrue(F_Mixpanel_API.verifyEventProperties(event, "templateId", createdTemplateId));
	}

	@Test(priority = 8, enabled = true)
	public void verifyEventsGenerated_Member() throws IOException, JSONException, InterruptedException {
		List<String> exp_Events_Mem = Arrays.asList("Register", "Load Onboarding Survey",
				"Click Onboarding Survey Answer", "Click Onboarding Survey Answer", "Click Onboarding Survey Answer",
				"Click Onboarding Survey Answer", "Click Onboarding Style Answer", "Click Onboarding Style Answer",
				"Click Onboarding Style Answer", "Finish Onboarding Survey", "Load My Templates Page","Templates Tour Guide",
				"Click On Create From Templates", "Share", "Finish Editor Tour Guide",
				"Click on Content Gate onboarding");
		String jqlScript = F_Mixpanel_API.replaceJQLParams("", mem_Acc.getEmail());
		actualEvents_Mem = F_Mixpanel_API.callAPI(jqlScript, exp_Events_Mem.size());
		List<String> act_Events_Mem = new ArrayList<String>();
		for (int i = 0; i < actualEvents_Mem.length(); i++) {
			act_Events_Mem.add(actualEvents_Mem.getJSONObject(i).getString("name"));
		}
		assertTrue(F_Mixpanel_API.verifyGeneratedEventNames(exp_Events_Mem, act_Events_Mem));
	}

	@Test(priority = 9, enabled = true)
	public void e_OnbSurveyAnwser_Mem() throws JSONException {
		String eventName = "Click Onboarding Survey Answer";
		String org = "school";
		String role = "student";
		String goal = "posters";

		JSONObject event = F_Mixpanel_API.getEventByProperty(actualEvents_Mem, eventName, "organization");
		assertTrue(F_Mixpanel_API.verifyEventProperties(event, "organization", org));
		event = F_Mixpanel_API.getEventByProperty(actualEvents_Mem, eventName, "role");
		assertTrue(F_Mixpanel_API.verifyEventProperties(event, "role", role));
		event = F_Mixpanel_API.getEventByProperty(actualEvents_Mem, eventName, "goal");
		assertTrue(F_Mixpanel_API.verifyEventProperties(event, "goal", goal));
	}

	@Test(priority = 10, enabled = true)
	public void e_FinishOnbSurvey_Mem() throws JSONException {
		String eventName = "Finish Onboarding Survey";
		String org = "school";
		String role = "student";
		String goal = "posters";

		JSONObject event = F_Mixpanel_API.getEventByName(actualEvents_Mem, eventName);
		assertTrue(F_Mixpanel_API.verifyEventProperties(event, "organization", org));
		assertTrue(F_Mixpanel_API.verifyEventProperties(event, "role", role));
		assertTrue(F_Mixpanel_API.verifyEventProperties(event, "goal", goal));
	}

	@Test(priority = 11, enabled = true)
	public void e_ClickOnbStyleAnwser_Mem() throws JSONException, EventPropertyException, IOException {
		String eventName = "Click Onboarding Style Answer";
		JSONArray actual_Events = F_Mixpanel_API.getEventsSameName(actualEvents_Mem, eventName);
		List<String> actual_TemplateIds = new ArrayList<String>();
		for (int i = 0; i < actual_Events.length(); i++) {
			actual_TemplateIds
					.add(actual_Events.getJSONObject(i).getJSONObject("properties").getString("templateId").toString());
		}
		Collections.sort(mem_SelectedOnbTemplates);
		Collections.sort(actual_TemplateIds);
		assertTrue(F_Mixpanel_API.verifyGeneratedEventNames(mem_SelectedOnbTemplates, actual_TemplateIds));
	}

	@Test(priority = 12, enabled = true)
	public void e_ClickOnCreateFromTemplate_Mem() throws JSONException, EventPropertyException {
		String eventName = "Click On Create From Templates";
		String cat = "recommended";
		String multipage = "true";
		String plan = "free";
		String subcat = "";

		JSONObject event = F_Mixpanel_API.getEventByName(actualEvents_Mem, eventName);
		assertTrue(F_Mixpanel_API.verifyEventProperties(event, "category", cat));
		assertTrue(F_Mixpanel_API.verifyEventProperties(event, "id", mem_CreatedTemplateId));
		assertTrue(F_Mixpanel_API.verifyEventProperties(event, "multipage", multipage));
		assertTrue(F_Mixpanel_API.verifyEventProperties(event, "template plan", plan));
		assertTrue(F_Mixpanel_API.verifyEventProperties(event, "subcategory", subcat));
		assertTrue(F_Mixpanel_API.verifyEventProperties(event, "title", mem_CreatedTemplateTitle));
	}

	@Test(priority = 13, enabled = true)
	public void e_FinishEditorTourGuide_Mem() throws JSONException {
		String eventName = "Finish Editor Tour Guide";
		String step = "5";
		String tourGuideVersion = "experiment-1";

		JSONObject event = F_Mixpanel_API.getEventByName(actualEvents_Mem, eventName);
		assertTrue(F_Mixpanel_API.verifyEventProperties(event, "step", step));
		assertTrue(F_Mixpanel_API.verifyEventProperties(event, "tourGuideVersion", tourGuideVersion));
	}

	@Test(priority = 14, enabled = true)
	public void e_Share_Mem() throws JSONException {
		String eventName = "Share";
		String buttonNum = "Share with team";
		String location = "Share Panel";
		String by = "With Team";
		String page = "Editor";

		JSONObject event = F_Mixpanel_API.getEventByName(actualEvents_Mem, eventName);
		assertTrue(F_Mixpanel_API.verifyEventProperties(event, "Button Number", buttonNum));
		assertTrue(F_Mixpanel_API.verifyEventProperties(event, "Via", by));
		assertTrue(F_Mixpanel_API.verifyEventProperties(event, "Location", location));
		assertTrue(F_Mixpanel_API.verifyEventProperties(event, "Page", page));
		// assertTrue(F_Mixpanel_API.verifyEventProperties(event, "Infographic",
		// infographId));
		assertTrue(F_Mixpanel_API.verifyEventProperties(event, "templateId", mem_CreatedTemplateId));
	}
	
	@Test(priority = 15, enabled = true)
	public void intercom_Owner() throws JSONException
	{
		List<String> expectedEvents = Arrays.asList("upgrade education admin","create infographic");
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
		
		//verify share event: no event generated for share with team?
//		JSONObject e_Share = F_Intercom_API.getEventByName(actualEvents, "share with team");
//		assertTrue(F_Intercom_API.verifyEventProperty(e_Share, "infographic_id", infographId));
	}
	
	@Test(priority = 16, enabled = true)
	public void intercom_Mem() throws JSONException
	{
		List<String> expectedEvents = Arrays.asList("create infographic");
		String type = "create infographic from template";
		JSONArray actualEvents = F_Intercom_API.callIntercomAPI(mem_Acc.getEmail());
		List<String> actualEventNames = new ArrayList<String>();
		for (int i = 0; i<actualEvents.length(); i++)
		{
			actualEventNames.add(actualEvents.getJSONObject(i).getString("event_name"));
		}
		assertTrue(F_Intercom_API.verifyListEventNames(expectedEvents, actualEventNames));
		
		//Verify create infographic event
		JSONObject event = F_Intercom_API.getEventByName(actualEvents, "create infographic");
		assertTrue(F_Intercom_API.verifyEventProperty(event, "template", mem_CreatedTemplateId));
		assertTrue(F_Intercom_API.verifyEventProperty(event, "infographic_id", mem_InfographId));
		assertTrue(F_Intercom_API.verifyEventProperty(event, "type", type));
		
		//verify share event
//		JSONObject e_Share = F_Intercom_API.getEventByName(actualEvents, "share with team");
//		assertTrue(F_Intercom_API.verifyEventProperty(e_Share, "infographic_id", mem_InfographId));
	}

	@AfterClass
	public void afterClass() {
		Constants.driver.quit();
	}

}
