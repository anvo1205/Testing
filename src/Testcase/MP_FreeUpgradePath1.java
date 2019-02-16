package Testcase;

import org.testng.annotations.Test;

import Function.F_Editor;
import Function.F_Intercom_API;
import Function.F_Mixpanel_API;
import Function.F_TemplatePage;
import Interface.I_MyBrand;
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

import javax.swing.plaf.ActionMapUIResource;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.openqa.selenium.By;
import org.testng.annotations.AfterClass;

public class MP_FreeUpgradePath1 {

	public JSONArray mp_Events;
	public JSONArray intercom_Events;
	public O_Account acc;
	public O_Onboarding onb = new O_Onboarding("Self-Employed", "Fitness/Health", "Invitation");
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

		// Editor
		By btn_UpgradeBrandLogo = By.xpath("//div[@data-widget-type='brand-media-image']");
		By btn_BuzUpgradeBrandModal = By.xpath("//div[@id='upgrade_modal']//div[@class='modal-body']//button");
		By lnk_BuzUpgradeBrandLogo = By.xpath("//a[@href='/subscription/business']");
		By lnk_BuzUpgradeBrandcolor = By.xpath("//p/a[@href='/account/subscription/business']");
		By wid_3DColumnChart = By.xpath("//div[@data-widget-type='viz.widget.standard.3dcolumn']");
		By btn_PreUpgradeFromModal = By.xpath("//div[@id='preview_modal']//a");
		By wid_EuropeMap = By.id("editor_widget_maps_5");
		By wid_SquareleImageFrame = By.id("editor_widget_img_frames_3");
		By lnk_UpgradeFromShare = By.xpath("//div[@id='private_share_panel']//a");
		By lnk_PreUpgradeFromDownload = By.xpath("//div[@id='ReactSettingsExport']//a[@href='/account/subscription']");
		By lnk_BuzUpgradeFromDownload = By
				.xpath("//div[@id='ReactSettingsExport']//a[@href='/account/subscription/business']");
		By lnk_BuzUpgradeFromSettings = By.id("my_template_upgrade_btn");

		// My Brand Kit
		Utils.clickElement(F_Editor.menu_My_Brand_Kit);
		Utils.clickElement(btn_UpgradeBrandLogo);
		Thread.sleep(1500);
		Utils.clickElement(btn_BuzUpgradeBrandModal);
		Constants.driver.get(infographUrl);
		Utils.clickElement(F_Editor.menu_My_Brand_Kit);
		Utils.clickElement(lnk_BuzUpgradeBrandLogo);
		Constants.driver.get(infographUrl);
		Utils.clickElement(F_Editor.menu_My_Brand_Kit);
		Utils.clickElement(lnk_BuzUpgradeBrandcolor);
		// Charts
		Constants.driver.get(infographUrl);
		Utils.clickElement(F_Editor.menu_Charts);
		Utils.scrollToElement(wid_3DColumnChart);
		Utils.clickElement(wid_3DColumnChart);
		Utils.clickElement(btn_PreUpgradeFromModal);
		// Maps
		Constants.driver.get(infographUrl);
		Utils.clickElement(F_Editor.menu_Maps);
		Utils.scrollToElement(wid_EuropeMap);
		Utils.clickElement(wid_EuropeMap);
		Utils.clickElement(btn_PreUpgradeFromModal);
		// Image Frame
		Constants.driver.get(infographUrl);
		Utils.clickElement(F_Editor.menu_Image_Frames);
		Utils.scrollToElement(wid_SquareleImageFrame);
		Utils.clickElement(wid_SquareleImageFrame);
		Utils.clickElement(btn_PreUpgradeFromModal);
		// Share
		Constants.driver.get(infographUrl);
		Utils.clickElement(F_Editor.btn_Share_Group);
		Utils.clickElement(lnk_UpgradeFromShare);
		// Download
		Constants.driver.get(infographUrl);
		Utils.clickElement(F_Editor.btn_Download_Group);
		Thread.sleep(1000);
		Utils.clickElement(lnk_PreUpgradeFromDownload);
		Constants.driver.get(infographUrl);
		Utils.clickElement(F_Editor.btn_Download_Group);
		Thread.sleep(1000);
		Utils.clickElement(lnk_BuzUpgradeFromDownload);
		// Settings
		Constants.driver.get(infographUrl);
		Utils.clickElement(F_Editor.btn_Settings_Group);
		Thread.sleep(1000);
		Utils.clickElement(lnk_BuzUpgradeFromSettings);

		// Top menu
		By lnk_UpgradeTopMenu = By.xpath("//li/a[@href='/account/subscription']");
		Constants.driver.get("https://infograph.venngage.beer/infographics");
		Thread.sleep(1000);
		Utils.clickElement(lnk_UpgradeTopMenu);

		// My design
		By lnk_MyDesign = By.xpath("//a[@href='/infographics']");
		By btn_CreateFolder = By.id("CTA-create-new-folder");
		By btn_MyTemplates = By.id("leftpanel-my-templates");
		By btn_BuildYourTeam = By.id("leftpanel-build-your-team");
		By btn_UpgradeBuzFromMyDesign = By.id("styles__upgrade___q4Fp_");
		By btn_CloseBusinessPaymentModal = By.id("styles__closeButton--3M4UK");
		
		// Create Folder
		Utils.clickElement(lnk_MyDesign);
		Utils.clickElement(btn_CreateFolder);
		Thread.sleep(1000);
		Utils.clickElement(btn_UpgradeBuzFromMyDesign);
		Utils.clickElement(btn_CloseBusinessPaymentModal);
		// My template
		Utils.clickElement(lnk_MyDesign);
		Utils.clickElement(btn_MyTemplates);
		Thread.sleep(1000);
		Utils.clickElement(btn_UpgradeBuzFromMyDesign);
		Utils.clickElement(btn_CloseBusinessPaymentModal);
		
		// My team
		Utils.clickElement(lnk_MyDesign);
		Utils.clickElement(btn_BuildYourTeam);
		Thread.sleep(1000);
		Utils.clickElement(btn_UpgradeBuzFromMyDesign);
		Utils.clickElement(btn_CloseBusinessPaymentModal);

		// My Brand
		By lnk_MyBrand = By.xpath("//a[@href='/brand']");
		By btn_UpgradeFromBrandLogo = By.xpath("//div[@class='gatedBrand__hoverLogoContainer--1vRa5']"
				+ "//span[@class='gatedBrand__upgradeButton--2jArW']");
		By btn_UpgradeFromFont = By.xpath("//div[@class='gatedBrand__hoverFontsContainer--3cFu9']"
				+ "//span[@class='gatedBrand__upgradeButton--2jArW']");
		By btn_UpgradeFromGenerateTemplate = By
				.xpath("//span[@class='gatedBrand__upgradeButton--2jArW gatedBrand__modalUpgradeButton--2iyF9']");
		Utils.clickElement(lnk_MyBrand);
		Utils.moveMouseToElement(btn_UpgradeFromBrandLogo);
		Utils.clickElement(btn_UpgradeFromBrandLogo);
		Utils.clickElement(btn_CloseBusinessPaymentModal);
		Utils.clickElement(lnk_MyBrand);
		Utils.scrollToElement(btn_UpgradeFromFont);
		Utils.moveMouseToElement(btn_UpgradeFromFont);
		Utils.clickElement(btn_UpgradeFromFont);
		Utils.clickElement(btn_CloseBusinessPaymentModal);
		Utils.clickElement(lnk_MyBrand);
		Utils.clickElement(I_MyBrand.btn_GenerateUpdateBrandTemplates);
		Utils.clickElement(btn_UpgradeFromGenerateTemplate);
		Thread.sleep(1000);
	}
	
	@Test(priority = 0, enabled = true)
	public void getAllEvents() throws IOException, JSONException, InterruptedException
	{
		String jqlScript = F_Mixpanel_API.replaceJQLParams("", acc.getEmail());
		mp_Events = F_Mixpanel_API.callAPI(jqlScript, 50);
		intercom_Events = F_Intercom_API.callIntercomAPI(acc.getEmail());
	}

	@Test(priority = 1, enabled = true)
	public void e_ClickOnUpgrade() throws JSONException, InterruptedException, IOException {
		String eventName = "Click On Upgrade";
		String[] btnNum = { "viz.widget.standard.3dcolumn", "viz.widget.standard.europemap", "image-mask:square",
				"private share", "premium export button", "header button" };
		String preExportType = "Premium";
		String buzExportType = "Business";
		
		JSONArray actualEvents = F_Mixpanel_API.getEventsSameName(mp_Events, eventName);
		for (int i = 0; i < btnNum.length; i++) {
				assertTrue(F_Mixpanel_API.verifyEventByPropertyValue(actualEvents, "Button Number", btnNum[i]));
		}
		assertTrue(F_Mixpanel_API.verifyEventByPropertyValue(actualEvents, "type", preExportType));
		assertTrue(F_Mixpanel_API.verifyEventByPropertyValue(actualEvents, "type", buzExportType));
	}

	@Test(priority = 2, enabled = true)
	public void e_ClickOnBusinessUpgrade() throws JSONException, InterruptedException, IOException {
		String eventName = "Click On Business Upgrade";
		String[] btnID = { "business export button", "editor my templates button", "New Folder button",
				"Template Modal button", "Build Your Team button" };

		JSONArray actualEvents = F_Mixpanel_API.getEventsSameName(mp_Events, eventName);
		for (int i = 0; i < actualEvents.length(); i++) {
			assertTrue(F_Mixpanel_API.verifyEventByPropertyValue(actualEvents, "Button ID", btnID[i]));
		}
	}

	@Test(priority = 3, enabled = true)
	public void e_BrandKitUpgrade() throws JSONException, IOException, InterruptedException {
		String eventName[] = {"Open My Brand Kit Modal", "Click on Upgrade from my brand kit modal"
							, "Generate Brand Templates - Gated", "Upgrade from Generate Brand Templates Modal"};
		for (int i = 0; i < eventName.length; i++)
		{
			try {
				F_Mixpanel_API.getEventByName(mp_Events, eventName[i]);
			} catch (JSONException e) {
				System.out.println("Event " + eventName[i] + " does not exist!");
			}
		}
	}

	@Test(priority = 4, enabled = true)
	public void e_ClickOnUpgradeBusinessLink() throws JSONException, IOException, InterruptedException {
		String eventName = "Click on upgrade business link";
		JSONArray events = F_Mixpanel_API.getEventsSameName(mp_Events, eventName);
		assertTrue(events.length() == 2);
	}

	@Test(priority = 5, enabled = true)
	public void e_ClickOnUpgradeFromBrandKit() throws JSONException, IOException, InterruptedException {
		String eventName = "Click on Upgrade from Brand Kit";
		String src1 = "logos";
		String src2 = "fonts";

		JSONArray actualEvents = F_Mixpanel_API.getEventsSameName(mp_Events, eventName);
		assertTrue(F_Mixpanel_API.verifyEventByPropertyValue(actualEvents, "source", src1));
		assertTrue(F_Mixpanel_API.verifyEventByPropertyValue(actualEvents, "source", src2));
	}

	@Test(priority = 6, enabled = true)
	public void intercom_ClickOnUpgrade() throws JSONException, InterruptedException, IOException {
		String eventName = "Click On Upgrade";
		String[] btnNum = { "viz.widget.standard.3dcolumn", "viz.widget.standard.europemap", "image-mask:square",
				"private share", "premium export button", "header button" };
		
		for (int i = 0; i < btnNum.length; i++) {
				assertTrue(F_Intercom_API.verifyEventByPropertyValue(intercom_Events, eventName, "Button Number", btnNum[i]));
		}
	}
	
	@Test(priority = 7, enabled = true)
	public void intercom_ClickOnBusinessUpgrade() throws JSONException, InterruptedException, IOException {
		String eventName = "Click On Business Upgrade";
		String[] btnID = { "business export button", "editor my templates button", "New Folder button",
				"Template Modal button", "Build Your Team button" };

		for (int i = 0; i < btnID.length; i++) {
			assertTrue(F_Intercom_API.verifyEventByPropertyValue(intercom_Events, eventName, "Button ID", btnID[i]));
		}
	}

	@AfterClass
	public void afterClass() {
		Constants.driver.quit();
	}

}
