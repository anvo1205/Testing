package Interface;

import org.openqa.selenium.By;

public class I_MyBrand {
	public static By btn_Upgrade = By.xpath("//div[@class='btn btn-lg upgrade__upgradeBusinessBtn--26LJE']");
	public static By btn_AddToMyBrandKit = By.xpath("//div[@id='brand__content--1gsv1']//div[@class='brand__modalButton--oGmor']");
	public static By btn_DoLater = By.xpath("//div[@id='brand__content--1gsv1']//div[@class='brand__dismiss--jaPDq']");
	public static By lbl_BrandTitle = By.xpath("//span[contains(@class,'brandNameText')]");
	public static By txt_BrandTitle = By.xpath("//input[contains(@class,'brandNameInput')]");
	public static By txt_UploadLogo = By.xpath("//form/input[@type='file']");
	public static By btn_AddColour = By.xpath("//div[@class='ColorPalette__addColorBtn--38VCJ']");
	public static By txt_Red = By.xpath("//span[text()='r']/preceding-sibling::input");
	public static By txt_Green = By.xpath("//span[text()='g']/preceding-sibling::input");
	public static By txt_Blue = By.xpath("//span[text()='b']/preceding-sibling::input");
	public static By btn_Save = By.xpath("//span[@class='styles__saveBtn--38ota styles__unSaved--3weim']");
	public static By ddl_HeaderFont = By.xpath("//div[@class='row brand__textsPanel--QZzk- brand__editable--2BzyV ']/div[@class='row brand__brandTitle--1wyXe']");
	public static By ddl_SubheaderFont = By.xpath("//div[@class='row brand__textsPanel--QZzk- brand__editable--2BzyV ']/div[@class='row '][1]");
	public static By ddl_BodyFont = By.xpath("//div[@class='row brand__textsPanel--QZzk- brand__editable--2BzyV ']/div[@class='row '][2]");
	public static By lnk_RequestFont = By.xpath("//div[text()='Request a Font']");
	public static By txt_FontUrl = By.xpath("//input[@placeholder='https://fonts.google.com/specimen/Roboto']");
	public static By txt_FontLanguage = By.xpath("//input[@placeholder='optional']");
	public static By btn_RequestFont = By.xpath("//div[text()='Request Font']");
	public static By btn_GenerateUpdateBrandTemplates = By.xpath("//button[@class='styles__root--3e70N brand__generateBtn--18Zg8']");
	public static By lbl_GenerateBrandTemplatesModal = By.id("styles__loading--kDH-V");
}
