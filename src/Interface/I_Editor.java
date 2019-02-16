package Interface;

import org.openqa.selenium.By;

public class I_Editor {
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
}
