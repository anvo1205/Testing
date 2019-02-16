package Testcase;

import org.testng.annotations.Test;

import Function.EventPropertyException;
import Function.F_Editor;
import Function.F_Mixpanel_API;
import Function.F_StaticTemplates;
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

public class MP_StaticTemplateFree2Publish {
	public JSONArray actualEvents;
	public O_Account acc = new O_Account(Utils.generateRandomEmail("free"));
	public O_Onboarding onb = new O_Onboarding("Other", "IT/Engineering", "Infographic");
	public List<String> selectedOnbTemplates = new ArrayList<String>();
	public String templateId = "0eda5e68-1c06-42de-9d6c-23bce5d0815c";
	public boolean abUser = true;

	@BeforeClass
	public void beforeClass() throws InterruptedException {
		System.out.println(this.getClass().getName());
		Utils.Initialize();
		System.out.println(acc.getEmail());
		Constants.driver.navigate().to("https://venngage.beer/templates");
		Utils.clickElement(F_StaticTemplates.lnk_CatInfographics);
		Utils.clickElement(F_StaticTemplates.lnk_SubcatStatistical);
		Utils.moveMouseToElement(F_StaticTemplates.btn_SignUp(templateId));
		Utils.clickElement(F_StaticTemplates.btn_SignUp(templateId));
		acc.Register();
		selectedOnbTemplates = onb.completeOnboarding(onb);
		abUser = F_TemplatePage.getUserId() % 10 == 0;
		if (!abUser)
		{
			System.out.println(F_TemplatePage.getUserId());
			Utils.clickElement(I_TemplatePage.btn_CreateFromPreview);
		}
		F_Editor.skipEditorTourGuide();
		F_Editor.publishDesign("");
	}

	@Test(priority = 0, enabled = true)
	public void verifyEventsGenerated() throws IOException, JSONException, InterruptedException {
		List<String> exp_Events = new LinkedList<String>(Arrays.asList("Load Static Templates Page", "Click Navigation","Click Navigation",
				"Click Sign Up From Templates", "Visit Homepage", "Register", "Load Onboarding Survey",
				"Click Onboarding Survey Answer", "Click Onboarding Survey Answer", "Click Onboarding Survey Answer",
				"Click Onboarding Survey Answer", "Click Onboarding Style Answer", "Click Onboarding Style Answer",
				"Click Onboarding Style Answer", "Finish Onboarding Survey", "Load My Templates Page",
				"Click On Create From Templates", "Templates Tour Guide", "Finish Editor Tour Guide",
				"Click On Publish", "Click on Content Gate onboarding"));
		if (abUser)
		{
			exp_Events.remove("Load My Templates Page");
			exp_Events.remove("Click On Create From Templates");
			exp_Events.remove("Templates Tour Guide");
		}
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
	public void e_LoadStaticTemplatesPage() throws JSONException
	{
		String eventName = "Load Static Templates Page";
		String searchVisible = "true";
		//String template_Cat_Test = "open";
		JSONObject event = F_Mixpanel_API.getEventByName(actualEvents, eventName);
		assertTrue(F_Mixpanel_API.verifyEventProperties(event, "search_visible", searchVisible));
		//assertTrue(F_Mixpanel_API.verifyEventProperties(event, "templates_categories_test", template_Cat_Test));
	}
	
	@Test(priority = 2, enabled = true)
	public void e_ClickNavigation() throws JSONException
	{
		String eventName = "Click Navigation";
		String cat = "infographics";
		List<String> expSubcats = Arrays.asList("","statistical");
		JSONArray events = F_Mixpanel_API.getEventsSameName(actualEvents, eventName);
		assertTrue(F_Mixpanel_API.verifyEventProperties(events.getJSONObject(0), "category", cat));
		assertTrue(F_Mixpanel_API.verifyEventProperties(events.getJSONObject(1), "category", cat));
		List<String> actualSubcats = new ArrayList<String>();
		actualSubcats.add(events.getJSONObject(0).getJSONObject("properties").remove("subcategory").toString());
		actualSubcats.add(events.getJSONObject(1).getJSONObject("properties").remove("subcategory").toString());
		assertTrue(F_Mixpanel_API.verifyGeneratedEventNames(expSubcats, actualSubcats));
	}
	
	@Test(priority = 3, enabled = true)
	public void e_ClickSignUpFromTemplates() throws JSONException
	{
		String eventName = "Click Sign Up From Templates";
		String plan = "free";
		String title = "Impact Cover";
		JSONObject event = F_Mixpanel_API.getEventByName(actualEvents, eventName);
		assertTrue(F_Mixpanel_API.verifyEventProperties(event, "template plan", plan));
		assertTrue(F_Mixpanel_API.verifyEventProperties(event, "title", title));
		assertTrue(F_Mixpanel_API.verifyEventProperties(event, "Template ID", templateId));
	}
	
	@Test(priority = 4, enabled = true)
	public void e_OnbSurveyAnwser() throws JSONException
	{
		String eventName = "Click Onboarding Survey Answer";
		String org = "other";
		String role = "it-eng";
		String goal = "infographics";
		
		JSONObject event = F_Mixpanel_API.getEventByProperty(actualEvents, eventName, "organization");
		assertTrue(F_Mixpanel_API.verifyEventProperties(event, "organization", org));
		event = F_Mixpanel_API.getEventByProperty(actualEvents, eventName, "role");
		assertTrue(F_Mixpanel_API.verifyEventProperties(event, "role", role));
		event = F_Mixpanel_API.getEventByProperty(actualEvents, eventName, "goal");
		assertTrue(F_Mixpanel_API.verifyEventProperties(event, "goal", goal));
	}
	
	@Test(priority = 5, enabled = true)
	public void e_FinishOnbSurvey() throws JSONException
	{
		String eventName = "Finish Onboarding Survey";
		String org = "other";
		String role = "it-eng";
		String goal = "infographics";
		String redirectEditor = "";
		if (abUser)
		{
			redirectEditor = "true";
		}
		else
		{
			redirectEditor = "false";
		}
		
		JSONObject event = F_Mixpanel_API.getEventByName(actualEvents, eventName);
		assertTrue(F_Mixpanel_API.verifyEventProperties(event, "organization", org));
		assertTrue(F_Mixpanel_API.verifyEventProperties(event, "role", role));
		assertTrue(F_Mixpanel_API.verifyEventProperties(event, "goal", goal));
		assertTrue(F_Mixpanel_API.verifyEventProperties(event, "redirect_editor", redirectEditor));
	}
	
	@Test(priority = 6, enabled = true)
	public void e_ClickOnCreateFromTemplates() throws JSONException
	{
		String eventName = "Click On Create From Templates";
		String createdFrom = "see-all-templates";
		String cat = "see all templates";
		String multipage = "true";
		String plan = "free";
		String subcat = "";
		String title = "Impact Cover";
		
		if (!abUser)
		{
			JSONObject event = F_Mixpanel_API.getEventByName(actualEvents, eventName);
			assertTrue(F_Mixpanel_API.verifyEventProperties(event, "created_from", createdFrom));
			assertTrue(F_Mixpanel_API.verifyEventProperties(event, "category", cat));
			assertTrue(F_Mixpanel_API.verifyEventProperties(event, "multipage", multipage));
			assertTrue(F_Mixpanel_API.verifyEventProperties(event, "plan", plan));
			assertTrue(F_Mixpanel_API.verifyEventProperties(event, "subcategory", subcat));
			assertTrue(F_Mixpanel_API.verifyEventProperties(event, "title", title));
		}
		else
		{
			System.out.println("AB test user. Skip this event");
		}
		
	}
	
	@Test(priority = 7, enabled = true)
	public void e_FinishEditorTourGuide() throws JSONException
	{
		String eventName = "Finish Editor Tour Guide";
		String step = "0";
		String tourGuideVersion = "experiment-1";
		
		JSONObject event = F_Mixpanel_API.getEventByName(actualEvents, eventName);
		assertTrue(F_Mixpanel_API.verifyEventProperties(event, "step", step));
		assertTrue(F_Mixpanel_API.verifyEventProperties(event, "tourGuideVersion", tourGuideVersion));
	}
	
	@Test(priority = 8, enabled = true)
	public void e_ClickOnPublish() throws JSONException
	{
		String eventName = "Click On Publish";
		
		JSONObject event = F_Mixpanel_API.getEventByName(actualEvents, eventName);
		assertTrue(F_Mixpanel_API.verifyEventProperties(event, "templateId", templateId));
	}
	
	@Test(priority=9, enabled = true)
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
	

	@AfterClass
	public void afterClass() {
		Constants.driver.quit();
	}

}
