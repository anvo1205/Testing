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
import Function.F_Mixpanel_API;
import Function.F_TemplatePage;
import Interface.I_Editor;
import Interface.I_TemplatePage;
import Object.O_Account;
import Object.O_Onboarding;
import Object.O_Payment;
import Utility.Constants;
import Utility.Utils;

public class MP_AddWidgets {

	public JSONArray actualEvents;
	public JSONArray expectedEvents;
	public String currentUrlEditor = "";
	public O_Account acc = new O_Account(Utils.generateRandomEmail("buz"));
	public boolean flag;

	@BeforeClass
	public void beforeMethod() throws InterruptedException, IOException, JSONException, EventPropertyException {
		Utils.Initialize();
		System.out.println(this.getClass().getName());
		System.out.println(acc.getEmail());
		O_Onboarding onb = new O_Onboarding("Small Business", "Marketer/PR", "Presentation");
		O_Payment payment = new O_Payment();
		Constants.driver.navigate().to("https://infograph.venngage.beer/signup/business/monthly");
		acc.Register();
		payment.upgrade(payment);
		O_Payment.clickGetStartedOnBrandKitModal1();
		onb.completeOnboarding(onb);
		F_TemplatePage.selecteTemplateCatetory(I_TemplatePage.lnk_CatLayouts);
		F_TemplatePage.clickBlankCanvas();
		F_Editor.skipEditorTourGuide();
		flag = F_Editor.verifyContentGatingTooltipDisplay();
		F_Editor.addTextWidget(I_Editor.wid_Text_Title);
		F_Editor.addIconWidgetBySearch("car", "", "ricon-steering-wheel-vehicle");
		F_Editor.addChartWidget("Funnel");
		F_Editor.addMapWidget("Asia Map");

	}

	@Test(priority = 0, enabled = true)
	public void verifyEventsGenerated() throws InterruptedException, IOException, JSONException {
		List<String> exp_Event_Names = new LinkedList<String>(Arrays.asList("Visit Homepage", "Register",
				"Upgrade Business Modal", "Upgrade Business", "Brand Onboarding Modal", "Load Onboarding Survey",
				"Click Onboarding Survey Answer", "Click Onboarding Survey Answer", "Click Onboarding Survey Answer",
				"Click Onboarding Survey Answer", "Click Onboarding Style Answer", "Click Onboarding Style Answer",
				"Click Onboarding Style Answer", "Finish Onboarding Survey", "Click On Create From Blank",
				"Finish Editor Tour Guide", "Create Widget", "Icon Search", "Create Widget", "Create Widget",
				"Create Widget", "Click Navigation", "Load My Templates Page"));
		if (flag) {
			exp_Event_Names.add("Show Tooltip Guide");
		}
		// Call mixpanel api
		String jqlScript = F_Mixpanel_API.replaceJQLParams("", acc.getEmail());
		actualEvents = F_Mixpanel_API.callAPI(jqlScript, exp_Event_Names.size());

		List<String> act_Events = new ArrayList<String>();
		for (int i = 0; i < actualEvents.length(); i++) {
			act_Events.add(actualEvents.getJSONObject(i).getString("name"));
		}
		assertTrue(F_Mixpanel_API.verifyGeneratedEventNames(exp_Event_Names, act_Events));
	}

	@Test(priority = 1, enabled = true)
	public void e_IconSearch() throws InterruptedException, IOException, JSONException {
		String eventName = "Icon Search";
		String query = "car";
		JSONObject event = F_Mixpanel_API.getEventByName(actualEvents, eventName);
		assertTrue(F_Mixpanel_API.verifyEventProperties(event, "query", query));
	}

	@Test(priority = 2, enabled = true)
	public void e_CreateWidget() throws InterruptedException, IOException, JSONException {
		String eventName = "Create Widget";
		List<String> expectedWidgets = Arrays.asList("viz.widget.standard.textv4", "ricon-steering-wheel-vehicle",
				"viz.widget.standard.funnel", "viz.widget.standard.asiamap");
		List<String> actualWidgets = new ArrayList<String>();
		JSONArray e_CreateWidget = F_Mixpanel_API.getEventsSameName(actualEvents, eventName);
		for (int i = 0; i < e_CreateWidget.length(); i++) {
			actualWidgets.add(e_CreateWidget.getJSONObject(i).getJSONObject("properties").remove("type").toString());
		}
		Collections.sort(expectedWidgets);
		Collections.sort(actualWidgets);
		assertTrue(F_Mixpanel_API.verifyGeneratedEventNames(expectedWidgets, actualWidgets));
	}

	@AfterClass
	public void afterMethod() {
		//Constants.driver.quit();
	}

}
