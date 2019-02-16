package Function;

import java.io.File;
import java.io.IOException;
import java.util.Random;

import org.openqa.selenium.By;

import Interface.I_Editor;
import Utility.Utils;

public class F_Editor {
	
	/*
	 * Interface
	 */
	//Widgets
		public static By menu_My_Brand_Kit = By.id("editor_menu_brand_kit");
		public static By btn_Add_To_Brand_Kit = By.xpath("//a[@href='/brand']");
		
		public static By menu_Text = By.id("editor_menu_text");
		public static By wid_Text_Title = By.id("editor_widget_text_1");
		public static By wid_Text_Subtitle = By.id("editor_widget_text_2");
		public static By wid_Text_Normal = By.id("editor_widget_text_3");
		public static By wid_Text_Html = By.id("editor_widget_text_4");
		
		public static By menu_Icons = By.id("editor_menu_icons");
		public static By txt_Icons_Search = By.id("editor_widget_icon_search");
		public static By btn_Icons_Search = By.id("editor_widget_icon_submit");
		public static By btn_Icons_Style_All = By.id("icon-filter-all");
		public static By btn_Icons_Style_Mono = By.id("icon-filter-color");
		public static By btn_Icons_Style_Color = By.id("icon-filter-mono");
		
		public static By menu_Charts = By.id("editor_menu_charts");
		
		
		public static By menu_Maps = By.id("editor_menu_maps");
		
		
		public static By menu_Background = By.id("editor_menu_background");
		
		
		public static By menu_Image_Uploads = By.id("editor_menu_uploads");
		public static By menu_Photos = By.id("editor_menu_img");
		public static By btn_Upload_Image = By.id("user_media_upload_button");
		public static By btn_Browse_Image = By.id("upload_filename");
		public static By btn_Upload = By.xpath("//input[@value='Upload']");
		
		public static By txt_ImageSearch = By.id("image_search");
		
		
		public static By menu_Image_Frames = By.id("editor_menu_img_frames");
		
		
		public static By menu_Icon_Charts = By.id("editor_menu_icon_chart");
		
		
		public static By menu_Interactive = By.id("editor_menu_marketing");
		public static By wid_Content_Gating = By.id("content_gate_button");
		
		
		
		
		
		
		
		//Toolbar
		public static By btn_Snap_To_Guide = By.id("toggle_snapToGuide");
		public static By btn_Grid = By.id("toggle_grid_display");
		public static By ddl_Zoom = By.id("ReactZoomOption");
		public static By list_Zoom_Options = By.xpath("//div[@id='ReactZoomOption']//ul");
		public static By btn_Undo = By.id("page_undo");
		public static By btn_Redo = By.id("page_redo");
		public static By btn_Group_Widgets = By.id("group_control_container");
		public static By btn_Lock = By.id("lock");
		
		
		//Context toolbar
		
		//Pubish
		public static By btn_Publish_Group = By.id("other_controls_publish");
		public static By txt_Infographic_Title = By.id("publish_infographic_title");
		public static By opt_Publish = By.id("publish_to_community");
		public static By opt_Preview = By.id("btn_preview");
		public static By btn_Publish = By.id("publish_submit_btn");
		public static By txt_Embed_Code = By.id("textarea_embed");
		//Share
		public static By btn_Share_Group = By.id("other_controls_share");
		public static By lbl_Share_Link = By.id("private_share_link_field");
		public static By btn_Copy_Link = By.id("copy_private_link");
		public static By txt_Shared_Email = By.id("share_via_email");
		public static By btn_Send_Email = By.id("private_share_via_email_btn");
		public static By btn_Team_Share = By.id("slider_switch");
		public static By lnk_Share_Pinterest = By.xpath("//a[@title='Share on Pinterest']");
		public static By lnk_Share_Facebook = By.xpath("//a[@title='Share on Facebook']");
		public static By lnk_Share_Twitter = By.xpath("//a[@title='Share on Twitter']");
		public static By lnk_Share_LinkedIn = By.xpath("//a[@title='Share on LinkedIn']");
		//Download
		public static By btn_Download_Group = By.id("other_controls_download");
		public static By btn_PNG = By.xpath("//div[@id='ReactSettingsExport']//button[text()='PNG']");
		public static By btn_HD_PNG = By.xpath("//div[@id='ReactSettingsExport']//button[text()='PNG HD']");
		public static By btn_PDF = By.xpath("//div[@id='ReactSettingsExport']//button[text()='PDF']");
		public static By btn_I_PDF = By.xpath("//div[@id='ReactSettingsExport']//button[text()='Interactive PDF']");
		public static By btn_Close_Sigle_Page_Export_Modal = By.id("styles__closeButton___2gnVD");
		//Page size
		public static By btn_Settings_Group = By.id("other_controls_settings");
		public static By btn_Poitrait = By.xpath("//span[@class='flaticon-portrait normalized styles__closed___f6zvC']");
		public static By btn_Landscape = By.xpath("//span[@class='flaticon-landscape normalized styles__open___1bIjM']");
		public static By btn_Copy_Design = By.id("btn_save_as_new_infographic");
		public static By btn_Save_As_Template = By.id("btn_save_as_template");
		
		//Multipage control
		
		//Chart settings
		
		//Editor Tour Guide
		public static By btn_Next = By.xpath("//button[@data-role='next']");
		public static By btn_Previous = By.xpath("//button[@data-role='prev']");
		public static By btn_Skip = By.xpath("//button[@title='End Tour']");
	
	
	
	/*
	 * Function
	 */
	//Editor tour guide
	public static void skipEditorTourGuide()
	{
			Utils.waitUntilElementIsVisible(By.id("step-0"));
			Utils.captureWebElement(By.xpath("//button[@title='End Tour']")).click();
	}
	
	public static void finishEditorTourGuide()
	{
		By btn_Next = null;
		for (int i = 0; i < 5; i++)
		{
			btn_Next = By.xpath("//div[@id='step-" + i + "']//button[@data-role='next']");
			Utils.waitUntilElementIsVisible(btn_Next);
			Utils.captureWebElement(btn_Next).click();
		}
		By btn_Done = By.xpath("//div[@id='step-5']//button[@data-role='end']");
		Utils.waitUntilElementIsVisible(btn_Done);
		Utils.captureWebElement(btn_Done).click();
	}
	
	//My Brand Kit
	
	public static void clickBrandColorPalette(String[] firstRGB)
	{
		By btn_BrandColorPalette = By.xpath("//div[@style='background-color: rgb(" + firstRGB[0] + ", " 
				+ firstRGB[1] + ", " + firstRGB[2] + ");']");
		Utils.clickElement(btn_BrandColorPalette);
	}
	
	//Text widget
	public static void addTextWidget(By element)
	{
		Utils.waitUntilElementIsVisible(menu_Text);
		if (!Utils.captureWebElement(wid_Text_Title).isDisplayed())
		{
			Utils.captureWebElement(menu_Text).click();
		}
			Utils.captureWebElement(wid_Text_Title).click();
	}
	
	public static void addTextWidgetByDragAndDrop(By element, int x, int y)
	{
		Utils.waitUntilElementIsVisible(menu_Text);
		if (!Utils.captureWebElement(wid_Text_Title).isDisplayed())
		{
			Utils.captureWebElement(menu_Text).click();
		}
		Utils.dragAndDropElement(element, x, y);
	}
		
	//Icon widget
	public static void searchIcons(String keyword)
	{
		if (!Utils.captureWebElement(txt_Icons_Search).isDisplayed())
		{
			Utils.captureWebElement(menu_Icons).click();
		}
		Utils.inputValueIntoTextbox(txt_Icons_Search, keyword);
		Utils.captureWebElement(btn_Icons_Search).clear();
	}
	
	public static void addIconWidgetBySearch(String keyword, String style, String iconType) throws InterruptedException
	{
		Utils.captureWebElement(menu_Icons).click();
		Utils.inputValueIntoTextbox(txt_Icons_Search, keyword);
		Utils.captureWebElement(btn_Icons_Search).click();
		Thread.sleep(2000);
		if(!style.isEmpty())
		{
			Utils.captureWebElement(By.id("icon-filter-" + style.toLowerCase())).click();
		}
		Utils.captureWebElement(By.xpath("//div[@data-widget-type='" + iconType + "']")).click();
	}
	
	public static void addIconWidgetByBrowseCategory(String cat, String iconType) throws InterruptedException
	{
		Utils.captureWebElement(menu_Icons).click();
		By category = By.xpath("//ul[@class='dropdown-menu icon-search']//span[@data-category-name='" + cat + "']");
		Utils.scrollToElement(category);
		Utils.captureWebElement(category).click();
		Thread.sleep(2000);
		
		By icon = By.xpath("//div[@data-widget-type='" + iconType + "']");
		Utils.scrollToElement(icon);
		Utils.captureWebElement(icon).click();
	}
	
	//Chart widget
	public static String mapChartNameToWidgetType(String name)
	{
		String type = "";
		switch(name)
		{
		case "Line":
			type = "viz.widget.standard.linegraph";
			break;
		case "Smooth Line":
			type = "viz.widget.standard.spline";
			break;
		case "Area":
			type = "viz.widget.standard.area";
			break;
		case "Stacked Area":
			type = "viz.widget.standard.stackedarea";
			break;
		case "Pie":
			type = "viz.widget.standard.pie";
			break;
		case "Half Pie":
			type = "viz.widget.standard.halfpiev2";
			break;
		case "Bar":
			type = "viz.widget.standard.bar";
			break;
		case "Stacked Bar":
			type = "viz.widget.standard.stackedbar";
			break;
		case "Column":
			type = "viz.widget.standard.column";
			break;
		case "Stacked Column":
			type = "viz.widget.standard.stackedcolumn";
			break;
		case "Multi Series":
			type = "viz.widget.standard.multi_series_chart";
			break;
		case "Scatterplot":
			type = "viz.widget.standard.scatterplot";
			break;
		case "Buble":
			type = "viz.widget.standard.ibubblechart";
			break;
		case "Stacked Buble":
			type = "viz.widget.standard.stackedBubblev2";
			break;
		case "Cloud Bubble (Beta)":
			type = "viz.widget.standard.cloudbubblechart";
			break;
		case "Word Cloud":
			type = "viz.widget.standard.d3wordcloudv2";
			break;
		case "Tree Map":
			type = "viz.widget.standard.treemapv2";
			break;
		case "3D Column":
			type = "viz.widget.standard.3dcolumn";
			break;
		case "3D Stacked":
			type = "viz.widget.standard.3dstackedcolumn";
			break;
		case "3D Pie":
			type = "viz.widget.standard.3dpie";
			break;
		case "Pyramid":
			type = "viz.widget.standard.pyramid";
			break;
		case "Funnel":
			type = "viz.widget.standard.funnel";
			break;
		case "Icon Column (Beta)":
			type = "viz.widget.standard.iconcolchartv2";
			break;
		case "Icon Bar (Beta)":
			type = "viz.widget.standard.iconbarchartv2";
			break;
		}
		return type;
	}
	public static void addChartWidget(String chartName) throws InterruptedException
	{
		By wid_Chart = By.xpath("//div[@data-widget-type='" + mapChartNameToWidgetType(chartName) + "']");
		Utils.scrollToElement(menu_Charts);
		Utils.captureWebElement(menu_Charts).click();
		Utils.scrollToElement(wid_Chart);
		Utils.captureWebElement(wid_Chart).click();
	}
	
	//Map widget
	public static String mapMapNameToWidgetType(String name)
	{
		String type = "";
		switch(name)
		{
		case "Canada":
			type = "viz.widget.standard.canadamapv3";
			break;
		case "United States":
			type = "viz.widget.standard.usmapv3";
			break;
		case "UK Map":
			type = "viz.widget.standard.ukmap";
			break;
		case "Australia Map":
			type = "viz.widget.standard.australiamap";
			break;
		case "Europe Map":
			type = "viz.widget.standard.europemap";
			break;
		case "Africa Map":
			type = "viz.widget.standard.africamap";
			break;
		case "Asia Map":
			type = "viz.widget.standard.asiamap";
			break;
		case "World Map":
			type = "viz.widget.standard.worldmapv3";
			break;
		}
		return type;
	}
	public static void addMapWidget(String mapName) throws InterruptedException
	{
		
		By wid_Map = By.xpath("//div[@data-widget-type='" + mapMapNameToWidgetType(mapName) + "']");
			Utils.scrollToElement(menu_Maps);
			Utils.captureWebElement(menu_Maps).click();
		Utils.scrollToElement(wid_Map);
		Utils.captureWebElement(wid_Map).click();
	}
	
	//Background
	public static void changeBackgroundColor(String[] rgb) throws InterruptedException
	{
		Utils.scrollToElement(menu_Background);
	}
	
	//Image Upload
	public static void uploadImage(String path, String photoName) throws InterruptedException, IOException
	{
		
			Utils.scrollToElement(menu_Image_Uploads);
			Utils.captureWebElement(menu_Image_Uploads).click();
		Utils.captureWebElement(btn_Upload_Image).click();
		Thread.sleep(2000);
		Utils.captureWebElement(btn_Browse_Image).sendKeys(path + "/" + photoName);
		Utils.captureWebElement(btn_Upload).click();
		
	}
	public static void addUserImage()
	{
		
	}
	
	//Photo
	public static void addPhoto(String mapName) throws InterruptedException
	{
		
		By wid_Map = By.xpath("//div[@data-widget-type='" + mapMapNameToWidgetType(mapName) + "']");
		Utils.scrollToElement(menu_Maps);
		Utils.captureWebElement(menu_Maps).click();
		Utils.scrollToElement(wid_Map);
		Utils.captureWebElement(wid_Map).click();
	}
	
	//Image Frame
	
	//Icon Charts
	
	//Interactive
	public static boolean verifyContentGatingTooltipDisplay()
	{
		String tooltipContent = "Generate leads with your visuals by enabling Content Gating!";
		By lbl_Tooltip = By.xpath("//div[@class='styles__tooltip___2PGV-']");
		if(Utils.captureWebElements(wid_Content_Gating).size() != 0 && Utils.captureWebElements(lbl_Tooltip).size() != 0)
		{
			return Utils.captureWebElement(lbl_Tooltip).getText().equals(tooltipContent);
		}
		else
		{
			System.out.println("Tooltip is not displayed!");
			return false;
		}
		
	}
	
	//Publish
	public static void publishDesign(String title)
	{
		Utils.captureWebElement(btn_Publish_Group).click();
		Utils.captureWebElement(opt_Publish).click();
		Utils.waitUntilElementIsVisible(btn_Publish);
		if (!title.isEmpty())
		{
			Utils.inputValueIntoTextbox(txt_Infographic_Title, title);
		}
		Utils.captureWebElement(btn_Publish).click();
	}
	
	public static void clickEmbedCode()
	{
		Utils.captureWebElement(txt_Embed_Code).click();
	}
	
	public static String getEmbedCode()
	{
		return Utils.captureWebElement(txt_Embed_Code).getText();
	}
	
	//Share
	public static void clickShareMenu()
	{
		Utils.captureWebElement(btn_Share_Group).click();
	}
	public static void clickCopyLink()
	{
		Utils.captureWebElement(btn_Copy_Link).click();
	}
	
	public static void shareDesignByEmail(String sharedEmail)
	{
		Utils.waitUntilElementIsVisible(btn_Share_Group);
		Utils.captureWebElement(btn_Share_Group).click();
		Utils.inputValueIntoTextbox(txt_Shared_Email, sharedEmail);
		Utils.captureWebElement(btn_Send_Email).click();
	}
	
	public static void clickTeamShare()
	{
		Utils.captureWebElement(btn_Team_Share).click();
	}
	
	public static void selectShareSocialNetworkOption(String option)
	{
		switch(option)
		{
		case "Pinterest":
			Utils.captureWebElement(lnk_Share_Pinterest).click();
			break;
		case "Facebook":
			Utils.captureWebElement(lnk_Share_Facebook).click();
			break;
		case "Twitter":
			Utils.captureWebElement(lnk_Share_Twitter).click();
			break;
		case "LinkedIn":
			Utils.captureWebElement(lnk_Share_LinkedIn).click();
			break;
		}
	}
	
	//Download
	public static void downloadMultipageDesign(String type)
	{
		By btn_Download_Option = By.xpath("//div[@id='ReactSettingsExport']//button[text()='" + type + "']");
		if (!Utils.captureWebElement(btn_Download_Option).isDisplayed())
		{
			Utils.clickElement(btn_Download_Group);
		}
		Utils.clickElement(btn_Download_Option);
	}
	
	public static void waitUntilDownloadComplete(String filePath, int timeOut) throws InterruptedException
	{
		File f = new File(filePath);
		int i = 1;
		do
		{
			Thread.sleep(1000);
			i++;
		}
		while ((i < timeOut + 1) && !f.exists());
	}

}
