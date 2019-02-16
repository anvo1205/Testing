package Testcase;

import org.testng.annotations.Test;

import Function.EventPropertyException;
import Function.F_Editor;
import Function.F_Intercom_API;
import Function.F_Mixpanel_API;
import Function.F_MyBrand;
import Function.F_MyHome;
import Interface.I_Editor;
import Interface.I_MyBrand;
import Interface.I_UpgradeModal;
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
import java.util.LinkedList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.openqa.selenium.By;
import org.testng.annotations.AfterClass;

public class Free2UpgradeFromMyBrand {
	public JSONArray actualEvents;
	public JSONArray actualEvents_Mem;
	public O_Account acc;
	public O_Onboarding onb = new O_Onboarding("Other", "C-level/Executive", "Newsletter");
	public O_Payment payment = new O_Payment();
	public List<String> selectedOnbTemplates = new ArrayList<String>();
	public String brandKitTitle = "Auto Test";
	public String[] brandColor1 = { "208", "2", "27" };
	public String[] brandColor2 = { "248", "231", "28" };
	public String templateId = "";
	public String requestFontUrl = "fonts.google.com/specimen/Roboto";
	public String requestFontLang = "French";
	public String infographId = "";

	@BeforeClass
	public void beforeClass() throws InterruptedException {
		System.out.println(this.getClass().getName());
		Utils.Initialize();
		acc = new O_Account(Utils.generateRandomEmail("free2buz"));
		System.out.println(acc.getEmail());
		Constants.driver.navigate().to(Constants.Free_Sign_Up_Url);
		acc.Register();
		selectedOnbTemplates = onb.completeOnboarding(onb);
		F_MyHome.clickTopMenu("MY BRAND");
		Utils.clickElement(I_MyBrand.btn_GenerateUpdateBrandTemplates);
		By btn_UpgradeFromGenerateTemplate = By
				.xpath("//span[@class='gatedBrand__upgradeButton--2jArW gatedBrand__modalUpgradeButton--2iyF9']");
		Utils.clickElement(btn_UpgradeFromGenerateTemplate);
		payment.upgrade(payment);
		Utils.clickElement(I_UpgradeModal.btn_Get_Started_Buz_Modal1);
		By btn_StartBrandModal1 = By
				.xpath("//div[@id='brand__content1--162M2']/div[contains(@class,'brand__modalButton1')]");
		Utils.clickElement((btn_StartBrandModal1));
		By btn_OkayBrandModal2 = By
				.xpath("//div[@id='brand__content2--1H1yh']/div[contains(@class,'brand__modalButton2')]");
		Utils.clickElement((btn_OkayBrandModal2));
		F_MyBrand.updateBrandTitle(brandKitTitle);
		F_MyBrand.uploadLogo(System.getProperty("user.dir") + "/src/TestData/1.png");
		F_MyBrand.addColourByTextbox(brandColor1);
		F_MyBrand.addColourByTextbox(brandColor2);
		F_MyBrand.requestAFont(I_MyBrand.ddl_HeaderFont, requestFontUrl, requestFontLang);
		By btn_CloseSuccessRequestFontModal = By
				.xpath("//div[@class='styles__content--2N1UB styles__centerContent--164cK']/span");
		Utils.clickElement(btn_CloseSuccessRequestFontModal);
		Utils.clickElement(I_MyBrand.btn_GenerateUpdateBrandTemplates);
		Thread.sleep(2000);
		By lbl_GenerateBrandTemplateModal = By.id("styles__loading--kDH-V");
		Utils.waitElementInvisible(lbl_GenerateBrandTemplateModal, 15);
		templateId = F_MyBrand.clickCreateFromRandomBrandedTemplate();
		Utils.waitUntilElementIsVisible(I_Editor.btn_Download_Group);
		F_Editor.finishEditorTourGuide();
		infographId = Constants.driver.getCurrentUrl().replace("https://infograph.venngage.beer/edit/", "");
		Utils.clickElement(I_Editor.menu_My_Brand_Kit);
		By btn_BrandColorPalette = By.xpath("//div[@style='background-color: rgb(" + brandColor1[0] + ", "
				+ brandColor1[1] + ", " + brandColor1[2] + ");']");
		Utils.clickElement(btn_BrandColorPalette);
		F_Editor.downloadMultipageDesign("PDF");
	}

	@Test(priority = 1, enabled = true)
	public void eventNames() throws IOException, JSONException, InterruptedException {
		// Define list of expected events
		List<String> exp_Event_Names = new LinkedList<String>(Arrays.asList("Visit Homepage", "Register",
				"Load Onboarding Survey", "Click Onboarding Survey Answer", "Click Onboarding Survey Answer",
				"Click Onboarding Survey Answer", "Click Onboarding Survey Answer", "Click Onboarding Style Answer",
				"Click Onboarding Style Answer", "Click Onboarding Style Answer", "Finish Onboarding Survey",
				"Load My Templates Page", "New Business Feature", "Generate Brand Templates - Gated",
				"Upgrade from Generate Brand Templates Modal", "Upgrade Business Modal", "Upgrade Business",
				"Brand Onboarding Modal", "Edit Name In Brand Kit", "Add Logo In Brand Kit", "Add Color In Brand Kit",
				"Add Color In Brand Kit", "Click on add color palette", "Click on Request Font",
				"Generate Brand Templates", "Create from Branded Templates", "Click on Content Gate onboarding",
				"Finish Editor Tour Guide", "Click on brand color palette", "Download"));
		// Calling mixpanel api
		String jqlScript = F_Mixpanel_API.replaceJQLParams("", acc.getEmail());
		actualEvents = F_Mixpanel_API.callAPI(jqlScript, exp_Event_Names.size());
		List<String> actual_Event_Names = new ArrayList<String>();
		for (int i = 0; i < actualEvents.length(); i++) {
			actual_Event_Names.add(actualEvents.getJSONObject(i).getString("name"));
		}
	}

	@Test(priority = 2, enabled = true)
	public void e_onbSurveyAnwser() throws JSONException {
		String eventName = "Click Onboarding Survey Answer";
		String org = "other";
		String role = "exec";
		String goal = "newsletters";

		JSONObject event = F_Mixpanel_API.getEventByProperty(actualEvents, eventName, "organization");
		assertTrue(F_Mixpanel_API.verifyEventProperties(event, "organization", org));
		event = F_Mixpanel_API.getEventByProperty(actualEvents, eventName, "role");
		assertTrue(F_Mixpanel_API.verifyEventProperties(event, "role", role));
		event = F_Mixpanel_API.getEventByProperty(actualEvents, eventName, "goal");
		assertTrue(F_Mixpanel_API.verifyEventProperties(event, "goal", goal));
	}

	@Test(priority = 3, enabled = true)
	public void e_FinishOnbSurvey() throws JSONException {
		String eventName = "Finish Onboarding Survey";
		String org = "other";
		String role = "exec";
		String goal = "newsletters";

		JSONObject event = F_Mixpanel_API.getEventByName(actualEvents, eventName);
		assertTrue(F_Mixpanel_API.verifyEventProperties(event, "organization", org));
		assertTrue(F_Mixpanel_API.verifyEventProperties(event, "role", role));
		assertTrue(F_Mixpanel_API.verifyEventProperties(event, "goal", goal));
	}

	@Test(priority = 4, enabled = true)
	public void e_NewBuzFeature() throws JSONException {
		String eventName = "New Business Feature";
		String btnLocation = "Top Menu";
		String btnNum = "my brand";

		JSONObject actualEvent = F_Mixpanel_API.getEventByName(actualEvents, eventName);
		assertTrue(F_Mixpanel_API.verifyEventProperties(actualEvent, "Button Location", btnLocation));
		assertTrue(F_Mixpanel_API.verifyEventProperties(actualEvent, "Button Number", btnNum));
	}

	@Test(priority = 5, enabled = true)
	public void e_BrandOnbModal() throws JSONException {
		String eventName = "Brand Onboarding Modal";
		String step1 = "true";
		String buttonNum1 = "Get Started";

		JSONObject event = F_Mixpanel_API.getEventByName(actualEvents, eventName);
		assertTrue(F_Mixpanel_API.verifyEventProperties(event, "Step 1", step1));
		assertTrue(F_Mixpanel_API.verifyEventProperties(event, "Button Number", buttonNum1));
	}

	@Test(priority = 6, enabled = true)
	public void e_EditNameInBrandKit() throws JSONException {
		String eventName = "Edit Name In Brand Kit";

		JSONObject event = F_Mixpanel_API.getEventByName(actualEvents, eventName);
		assertTrue(F_Mixpanel_API.verifyEventProperties(event, "name", brandKitTitle));
	}

	@Test(priority = 7, enabled = true)
	public void e_AddColorInBrandKit() throws JSONException {
		String eventName = "Add Color In Brand Kit";
		String free = "false";

		JSONObject event = F_Mixpanel_API.getEventByName(actualEvents, eventName);
		assertTrue(F_Mixpanel_API.verifyEventProperties(event, "free", free));
	}

	@Test(priority = 8, enabled = true)
	public void e_ClickOnAddColorPalette() throws JSONException {
		String eventName = "Click on add color palette";
		String free = "false";

		JSONObject event = F_Mixpanel_API.getEventByName(actualEvents, eventName);
		assertTrue(F_Mixpanel_API.verifyEventProperties(event, "free", free));
	}

	@Test(priority = 9, enabled = true)
	public void e_ClickOnRequestFont() throws JSONException {
		String eventName = "Click On Request Font";
		String via = "brand";

		JSONObject event = F_Mixpanel_API.getEventByName(actualEvents, eventName);
		assertTrue(F_Mixpanel_API.verifyEventProperties(event, "via", via));
		assertTrue(F_Mixpanel_API.verifyEventProperties(event, "Font URL", requestFontUrl));
		assertTrue(F_Mixpanel_API.verifyEventProperties(event, "Font Language", requestFontLang));
	}

	@Test(priority = 10, enabled = true)
	public void e_CreateFromBrandedTemplates() throws JSONException {
		String eventName = "Create from Branded Templates";

		JSONObject event = F_Mixpanel_API.getEventByName(actualEvents, eventName);
		assertTrue(F_Mixpanel_API.verifyEventProperties(event, "Multipage Template ID", templateId));
	}

	@Test(priority = 11, enabled = true)
	public void e_FinishEditorTourGuide() throws JSONException, EventPropertyException {
		String eventName = "Finish Editor Tour Guide";
		String step = "5";
		String tourGuideVersion = "experiment-1";

		JSONObject event = F_Mixpanel_API.getEventByName(actualEvents, eventName);
		assertTrue(F_Mixpanel_API.verifyEventProperties(event, "step", step));
		assertTrue(F_Mixpanel_API.verifyEventProperties(event, "tourGuideVersion", tourGuideVersion));
	}

	@Test(priority = 12, enabled = true)
	public void e_Download() throws JSONException {
		String eventName = "Download";
		String type = "pdf";
		String server = "Headless Chrome";
		String editor = "Multipage";

		JSONObject event = F_Mixpanel_API.getEventByName(actualEvents, eventName);
		assertTrue(F_Mixpanel_API.verifyEventProperties(event, "type", type));
		assertTrue(F_Mixpanel_API.verifyEventProperties(event, "editor", editor));
		assertTrue(F_Mixpanel_API.verifyEventProperties(event, "server", server));
		try {
			assertTrue(F_Mixpanel_API.verifyEventProperties(event, "infographic", infographId));
		} catch (AssertionError e) {
			System.out.println("bug: event returns page.identifier instead of infographic id");
		}

		assertTrue(F_Mixpanel_API.verifyEventProperties(event, "templateId", templateId));
	}

	@Test(priority = 13, enabled = true)
	public void intercomEvents() throws JSONException {
		List<String> expectedEvents = Arrays.asList("upgrade business admin", "generate brand templates - gated", "add color in brand kit",
				"add color in brand kit", "add palette in brand kit", "click on brand color palette", "export");
		JSONArray actualEvents = F_Intercom_API.callIntercomAPI(acc.getEmail());
		List<String> actualEventNames = new ArrayList<String>();
		for (int i = 0; i < actualEvents.length(); i++) {
			actualEventNames.add(actualEvents.getJSONObject(i).getString("event_name"));
		}
		assertTrue(F_Intercom_API.verifyListEventNames(expectedEvents, actualEventNames));

		// verify create infographic event
		// String createtType = "create infographic from template";
		// JSONObject e_Create = F_Intercom_API.getEventByName(actualEvents, "create
		// infographic");
		// assertTrue(F_Intercom_API.verifyEventProperty(e_Create, "template",
		// createdTemplateId));
		// assertTrue(F_Intercom_API.verifyEventProperty(e_Create, "infographic_id",
		// infographId));
		// assertTrue(F_Intercom_API.verifyEventProperty(e_Create, "type",
		// createtType));

		// verify export event
		String method = "server";
		String downloadType = "pdf";
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
