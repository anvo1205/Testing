package Testcase;

import org.testng.annotations.Test;

import Function.F_Editor;
import Function.F_Intercom_API;
import Function.F_Mixpanel_API;
import Function.F_TemplatePage;
import Interface.I_TemplatePage;
import Object.O_Account;
import Object.O_Onboarding;
import Object.O_Payment;
import Utility.Constants;
import Utility.Utils;

import org.testng.annotations.BeforeClass;

import static org.testng.Assert.assertTrue;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.openqa.selenium.By;
import org.testng.annotations.AfterClass;

public class MP_FreeUpgradePath2 {
	public JSONArray mp_Events;
	public JSONArray intercom_Events;
	public O_Account acc;
	public O_Onboarding onb = new O_Onboarding("Self-Employed", "Blogger/Writer", "Infographic");
	public O_Payment payment = new O_Payment(Constants.VisaDebit);
	public List<String> selectedOnbTemplates = new ArrayList<String>();
	public String infographId = "";

	@BeforeClass
	public void beforeClass() throws InterruptedException {
		Utils.Initialize();
		System.out.println(this.getClass().getName());
		acc = new O_Account(Utils.generateRandomEmail("free"));
		System.out.println(acc.getEmail());
		Constants.driver.navigate().to(Constants.Free_Sign_Up_Url);
		acc.Register();
		selectedOnbTemplates = onb.completeOnboarding(onb);
		F_TemplatePage.selecteTemplateCatetory(I_TemplatePage.lnk_CatLayouts);
		F_TemplatePage.clickBlankCanvas();
		F_Editor.skipEditorTourGuide();
		String infographUrl = Constants.driver.getCurrentUrl();
		infographId = infographUrl.replace("https://infograph.venngage.beer/edit/", "");
		
		By cat_ShapeSymbolIcons = By.id("editor_widget__dropdown_0");
		By wid_Ricon_Floral_Icon = By.xpath("//div[@data-widget-type='ricon-floral']");
		By btn_UpgradeIconModal = By.xpath("//div[@id='upgrade_modal']//div[@class='modal-body']//button");
		
		// Icons
		Utils.clickElement(F_Editor.menu_Icons);
		Utils.clickElement(cat_ShapeSymbolIcons);
		Utils.scrollToElement(wid_Ricon_Floral_Icon);
		Utils.clickElement(wid_Ricon_Floral_Icon);
		Utils.clickElement(btn_UpgradeIconModal);
		
		//My Design
		By lnk_MyDesign = By.xpath("//a[@href='/infographics']");
		By img_FirstThumb = By.id("styles__root___238Qg");
		By btn_GearThumb = By.id("infographics-gear-options");
		By btn_MakeACopy = By.xpath("//span[@id='styles__deleteOnly___isDSC' and text()='Make a Copy']");
		By btn_SaveAsTemplate = By.xpath("//span[@id='styles__deleteOnly___isDSC' and text()='Save as Template']");
		By btn_UpgradeBuzFromMyDesign = By.id("styles__upgrade___q4Fp_");
		By btn_CloseBusinessPaymentModal = By.id("styles__closeButton--3M4UK");
		
		// Save As Template
		Thread.sleep(1000);
		Utils.clickElement(lnk_MyDesign);
		Utils.moveMouseToElement(img_FirstThumb);
		Utils.clickElement(btn_GearThumb);
		Utils.clickElement(btn_SaveAsTemplate);
		Thread.sleep(1000);
		Utils.clickElement(btn_UpgradeBuzFromMyDesign);
		Utils.clickElement(btn_CloseBusinessPaymentModal);
		
		//Copy
		Utils.clickElement(lnk_MyDesign);
		for (int i = 0; i < 5; i++)
		{
			Utils.moveMouseToElement(img_FirstThumb);
			Utils.clickElement(btn_GearThumb);
			Utils.clickElement(btn_MakeACopy);
		}
		Thread.sleep(1000);
		Utils.clickElement(btn_UpgradeBuzFromMyDesign);
		
		//Templates
		By lnk_Templates = 	By.xpath("//a[@href='/templates/recommended']");
		By btn_UpgradeExceedModal = By.id("styles__upgrade___2D1ES");
		By btn_PreviewFirstTemplate = By.xpath("//div[@class='styles__columns___6PuGM'][1]/div[@class='styles__root___2nWj-'][1]//button[text()='Preview']");
		
		Utils.clickElement(lnk_Templates);
		F_TemplatePage.clickOnCreate1stTemplate();
		Utils.clickElement(btn_UpgradeExceedModal);
		
		Utils.clickElement(lnk_Templates);
		Utils.moveMouseToElement(btn_PreviewFirstTemplate);
		Utils.clickElement(btn_PreviewFirstTemplate);
		Utils.clickElement(I_TemplatePage.btn_CreateFromPreview);
		Utils.clickElement(btn_UpgradeExceedModal);
		Thread.sleep(2000);
	}

	@Test(priority = 0, enabled = true)
	public void getAllEvents() throws IOException, JSONException, InterruptedException
	{
		String jqlScript = F_Mixpanel_API.replaceJQLParams("", acc.getEmail());
		mp_Events = F_Mixpanel_API.callAPI(jqlScript, 33);
		intercom_Events = F_Intercom_API.callIntercomAPI(acc.getEmail());
	}
	
	@Test(priority = 1, enabled = true)
	public void e_ClickOnUpgrade() throws JSONException, InterruptedException, IOException {
		String eventName = "Click On Upgrade";
		String btnID = "Premium Icon";
		JSONObject event = F_Mixpanel_API.getEventByName(mp_Events, eventName);
		assertTrue(F_Mixpanel_API.verifyEventProperties(event, "Button ID", btnID));
	}
	
	@Test(priority = 2, enabled = true)
	public void e_LoadMyDesignsPage() throws JSONException, InterruptedException, IOException {
		String eventName = "Load My Designs Page";
		String btnLocation = "Top Menu";
		String btnNum = "my designs";
		JSONArray events = F_Mixpanel_API.getEventsSameName(mp_Events, eventName);
		JSONObject event = F_Mixpanel_API.getEventByName(mp_Events, eventName);
		assertTrue(events.length() == 2 
				&& F_Mixpanel_API.verifyEventProperties(event, "Button Number", btnNum)
				&& F_Mixpanel_API.verifyEventProperties(event, "Button Location", btnLocation));
	}
	
	@Test(priority = 3, enabled = true)
	public void e_SaveAsTemplateFromMyDesigns() throws JSONException, InterruptedException, IOException {
		String eventName = "Save as Template from My Designs";
		String success = "false";
		JSONObject event = F_Mixpanel_API.getEventByName(mp_Events, eventName);
		assertTrue(F_Mixpanel_API.verifyEventProperties(event, "success", success));
	}
	
	@Test(priority = 4, enabled = true)
	public void e_ClickOnBusinessUpgrade() throws JSONException, InterruptedException, IOException {
		String eventName = "Click On Business Upgrade";
		String btnID = "Template Modal button";
		JSONObject event = F_Mixpanel_API.getEventByName(mp_Events, eventName);
		assertTrue(F_Mixpanel_API.verifyEventProperties(event, "Button ID", btnID));
	}
	
	@Test(priority = 5, enabled = true)
	public void e_SaveAsCopyFromMyDesigns() throws JSONException, InterruptedException, IOException {
		String eventName = "Save As Copy from My Designs";
		String count = "0";
		int length = F_Mixpanel_API.getEventsSameName(mp_Events, eventName).length();
		JSONObject event = F_Mixpanel_API.getEventByName(mp_Events, eventName);
		assertTrue(length == 5 && F_Mixpanel_API.verifyEventProperties(event, "count", count));
	}
	
	@Test(priority = 6, enabled = true)
	public void e_UpgradeFromOverLimitModalMyDesigns() throws JSONException, InterruptedException, IOException {
		String eventName = "Upgrade from Overlimit Modal My Designs";
		try {
			F_Mixpanel_API.getEventByName(mp_Events, eventName);
		} catch (JSONException e) {
			System.out.println("Event " + eventName + " does not exist!");
		}
	}
	
	@Test(priority = 7, enabled = true)
	public void intercom() throws JSONException, InterruptedException, IOException {
		String eventUpgrade = "Click On Upgrade";
		String eventBuzUpgrade = "Click On Business Upgrade";
		String btnID = "Premium Icon";
		String btnNum = "overlimit button";
		String btnIDBuz = "Template Modal button";
		JSONObject eIconUpgrade = F_Intercom_API.getEventByPropertyName(intercom_Events, eventUpgrade, "Button ID");
		assertTrue(F_Intercom_API.verifyEventProperty(eIconUpgrade, "Button ID", btnID));
		JSONObject eMyDesignsUpgrade = F_Intercom_API.getEventByPropertyName(intercom_Events, eventUpgrade, "Button Number");
		assertTrue(F_Intercom_API.verifyEventProperty(eMyDesignsUpgrade, "Button Number", btnNum));
		JSONObject eBuzUpgrade = F_Intercom_API.getEventByName(intercom_Events, eventBuzUpgrade);
		assertTrue(F_Intercom_API.verifyEventProperty(eBuzUpgrade, "Button ID", btnIDBuz));
	}

	@AfterClass
	public void afterClass() {
		Constants.driver.quit();
	}

}
