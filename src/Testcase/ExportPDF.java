package Testcase;

import org.testng.annotations.Test;

import Function.F_Editor;
import Object.O_Account;
import Utility.Constants;
import Utility.PdfUtils;
import Utility.Utils;

import org.testng.annotations.BeforeClass;
import static org.testng.Assert.assertTrue;
import org.testng.annotations.AfterClass;

import java.io.IOException;

public class ExportPDF {
	
	@BeforeClass
	  public void beforeClass() throws InterruptedException {
		Utils.Initialize();
		O_Account acc = new O_Account(Constants.export_User_Email);
		  Constants.driver.get(Constants.Sign_In_Url);
		  acc.SignIn();
	  }
	
  @Test(priority = 1, enabled = true)
  public void exportSinglePageDesign() throws InterruptedException, IOException {
	  Constants.driver.get(Constants.Export_Single_Page_Url);
	  
	  //Download pdf
	  Utils.deleteFile(Constants.Pdf_Single_Page_Downloaded_File_Path);
	  F_Editor.downloadMultipageDesign("PDF");
	  F_Editor.waitUntilDownloadComplete(Constants.Pdf_Single_Page_Downloaded_File_Path, 60);
	  boolean result1 = PdfUtils.comparePdfContent(Constants.Pdf_Single_Page_Expected_File_Path
			  , Constants.Pdf_Single_Page_Downloaded_File_Path, true);
	  Utils.clickElement(F_Editor.btn_Close_Sigle_Page_Export_Modal);
	  
	  //Download vector pdf
	  Utils.deleteFile(Constants.Pdf_Single_Page_Downloaded_File_Path);
	  F_Editor.downloadMultipageDesign("Vector PDF");
	  F_Editor.waitUntilDownloadComplete(Constants.Pdf_Single_Page_Downloaded_File_Path, 60);
	  boolean result2 = PdfUtils.comparePdfContent(Constants.V_Pdf_Single_Page_Expected_File_Path
			  , Constants.Pdf_Single_Page_Downloaded_File_Path, true);
	  Utils.clickElement(F_Editor.btn_Close_Sigle_Page_Export_Modal);
	  
	  //Download interactive pdf
	  Utils.deleteFile(Constants.Pdf_Single_Page_Downloaded_File_Path);
	  F_Editor.downloadMultipageDesign("Interactive PDF");
	  F_Editor.waitUntilDownloadComplete(Constants.Pdf_Single_Page_Downloaded_File_Path, 60);
	  boolean result3 = PdfUtils.comparePdfContent(Constants.I_Pdf_Single_Page_Expected_File_Path
			  , Constants.Pdf_Single_Page_Downloaded_File_Path, true);
	  Utils.deleteFile(Constants.Pdf_Single_Page_Downloaded_File_Path);
	  
	  assertTrue(result1 && result2 && result3);
  }
  
  @Test(priority = 2, enabled = true)
  public void exportMultiPageDesign_AllWidgets() throws InterruptedException, IOException {
	  Constants.driver.get(Constants.Export_All_Widgets_Url);
	  
	  //Download pdf
	  Utils.deleteFile(Constants.Pdf_All_Widgets_Downloaded_File_Path);
	  F_Editor.downloadMultipageDesign("PDF");
	  F_Editor.waitUntilDownloadComplete(Constants.Pdf_All_Widgets_Downloaded_File_Path, 60);
	  boolean result1 = PdfUtils.comparePdfContent(Constants.Pdf_All_Widgets_Expected_File_Path
			  , Constants.Pdf_All_Widgets_Downloaded_File_Path, true);
	  System.out.println(result1);
	  
	  //Download interactive pdf
	  Utils.deleteFile(Constants.Pdf_All_Widgets_Downloaded_File_Path);
	  F_Editor.downloadMultipageDesign("Interactive PDF");
	  F_Editor.waitUntilDownloadComplete(Constants.Pdf_All_Widgets_Downloaded_File_Path, 60);
	  boolean result2 = PdfUtils.comparePdfContent(Constants.Pdf_All_Widgets_Downloaded_File_Path
			  					, Constants.I_Pdf_All_Widgets_Expected_File_Path, true);
	  Utils.deleteFile(Constants.Pdf_All_Widgets_Downloaded_File_Path);
	  System.out.println(result2);
	  
	  assertTrue(result1 && result2);
  }
  
  @Test(priority = 3, enabled = true)
  public void exportMultiPageDesign_Long_Pages() throws InterruptedException, IOException {
	  Constants.driver.get(Constants.Export_Long_Design_Url);
	  
	  //Download pdf
	  Utils.deleteFile(Constants.Pdf_Long_Design_Downloaded_File_Path);
	  F_Editor.downloadMultipageDesign("PDF");
	  F_Editor.waitUntilDownloadComplete(Constants.Pdf_Long_Design_Downloaded_File_Path, 60);
	  boolean result1 = PdfUtils.comparePdfContent(Constants.Pdf_Long_Design_Expected_File_Path
			  					, Constants.Pdf_Long_Design_Downloaded_File_Path, true);
	  
	  //Download interactive pdf
	  Utils.deleteFile(Constants.Pdf_Long_Design_Downloaded_File_Path);
	  F_Editor.downloadMultipageDesign("Interactive PDF");
	  F_Editor.waitUntilDownloadComplete(Constants.Pdf_Long_Design_Downloaded_File_Path, 60);
	  boolean result2 = PdfUtils.comparePdfContent(Constants.I_Pdf_Long_Design_Expected_File_Path
			  					, Constants.Pdf_Long_Design_Downloaded_File_Path, true);
	  Utils.deleteFile(Constants.Pdf_Long_Design_Downloaded_File_Path);
	  
	  assertTrue(result1 && result2);
  }
  
  @Test(priority = 4, enabled = true)
  public void exportAllFonts() throws InterruptedException, IOException {
	  Constants.driver.get(Constants.Export_All_Fonts_Url);
	  
	  //Download pdf
	  Utils.deleteFile(Constants.Pdf_All_Fonts_File_Path);
	  F_Editor.downloadMultipageDesign("PDF");
	  F_Editor.waitUntilDownloadComplete(Constants.Pdf_All_Fonts_File_Path, 60);
	  boolean result1 = PdfUtils.comparePdfContent(Constants.Pdf_All_Fonts_File_Path
			  					, Constants.Pdf_All_Fonts_Expected_File_Path, true);
	  
	  //Download interactive pdf
	  Utils.deleteFile(Constants.Pdf_All_Fonts_File_Path);
	  F_Editor.downloadMultipageDesign("Interactive PDF");
	  F_Editor.waitUntilDownloadComplete(Constants.Pdf_All_Fonts_File_Path, 60);
	  boolean result2 = PdfUtils.comparePdfContent(Constants.Pdf_All_Fonts_File_Path
			  					, Constants.I_Pdf_All_Fonts_Expected_File_Path, true);
	  Utils.deleteFile(Constants.Pdf_All_Fonts_File_Path);
	  
	  assertTrue(result1 && result2);
  }

  @AfterClass
  public void afterClass() {
	  Constants.driver.quit();
  }

}
