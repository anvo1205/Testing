package Testcase;

import org.testng.annotations.Test;

import Function.F_Editor;
import Function.F_Intercom_API;
import Object.O_Account;
import Utility.Constants;
import Utility.Utils;

import org.testng.annotations.BeforeClass;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

import org.testng.annotations.AfterClass;

public class exportPNG {
  @Test
  public void PNG() throws InterruptedException, IOException {
	  Constants.driver.get(Constants.Export_Long_Design_Url);
	  //Download PNG
	  Utils.deleteFile(Constants.Png_Long_Design_Downloaded_File_Path);
	  F_Editor.downloadMultipageDesign("PNG");
	  F_Editor.waitUntilDownloadComplete(Constants.Png_Long_Design_Downloaded_File_Path, 60);
	  boolean result = Utils.compareTwoImages(Constants.Png_Long_Design_Downloaded_File_Path
				, Constants.Png_Long_Design_Expected_File_Path);
	  System.out.println(result);
	  
	  //Download PNG HD
	  Utils.deleteFile(Constants.Png_Long_Design_Downloaded_File_Path);
	  F_Editor.downloadMultipageDesign("PNG HD");
	  F_Editor.waitUntilDownloadComplete(Constants.Png_Long_Design_Downloaded_File_Path, 60);
	  boolean result2 = Utils.compareTwoImages(Constants.Png_Long_Design_Downloaded_File_Path
				, Constants.PngHd_Long_Design_Expected_File_Path);
	  System.out.println(result2);
  }
  
  
  @BeforeClass
	  public void beforeClass() throws InterruptedException {
		Utils.Initialize();
		O_Account acc = new O_Account(Constants.export_User_Email);
		  Constants.driver.get(Constants.Sign_In_Url);
		  acc.SignIn();
  }

  @AfterClass
  public void afterClass() {
	  Constants.driver.quit();
  }

}
