package Testcase;

import static org.testng.Assert.assertTrue;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalTime;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import javax.imageio.ImageIO;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
//import org.json.simple.JSONObject;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import com.stripe.model.Customer;
import com.stripe.model.Invoice;
import com.stripe.model.Subscription;
import com.stripe.net.RequestOptions;
import com.testautomationguru.utility.CompareMode;
import com.testautomationguru.utility.PDFUtil;

import Function.F_Intercom_API;
import Function.F_Mixpanel_API;
import Function.F_Stripe_API;
import Object.O_Account;
import Utility.Constants;
import Utility.DbUtils;
import Utility.Utils;
import bsh.util.Util;
import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Test {

	public static void main(String[] args) throws Exception {
//		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
//		String dateAsString = simpleDateFormat.format(new Date());
//		String jqlScript = F_Mixpanel_API.replaceJQLParams("","Test0803044945@autotest.com");
//		JSONArray actualEvents = F_Mixpanel_API.callAPI(jqlScript, 19);
//		JSONArray event = F_Mixpanel_API.getEventsSameName(actualEvents, "Click Onboarding Style Answer");
//		System.out.println(event);
		
//		PDFUtil pdfUtil = new PDFUtil();
//		String file1 = "C:/John/Venngage/Download/long_design_pdf.pdf";
//		String file2 = "C:/John/Venngage/Download/long_design_pdf - Copy.pdf";

		// compares the pdf documents and returns a boolean
		// true if both files have same content. false otherwise.
		// Default is CompareMode.TEXT_MODE
//		pdfUtil.setCompareMode(CompareMode.VISUAL_MODE);
//		pdfUtil.compare(file1, file2);

		// compare the 3rd page alone
		//pdfUtil.compare(file1, file2, 3, 3);

		// compare the pages from 1 to 5
		//pdfUtil.compare(file1, file2, 1, 5);

		//if you need to store the result
//		pdfUtil.highlightPdfDifference(true);
//		pdfUtil.setImageDestinationPath("C:/John/Venngage/Download");
//		System.out.println(pdfUtil.compare(file1, file2));
		
		//Compare 2 images file (e.g. png)
//		String src = Constants.Download_Destination + "/single_page_png_1.png";
//		System.out.println(src);
//		String des = Constants.Download_Destination + "/single_page_png_1 - Copy.png";
//		System.out.println(des);
////		System.out.println(Utils.compareImages(src, des));
//		boolean result = Utils.getDifferenceImage(ImageIO.read(new File("C:/Users/John/Downloads/single_page_png_1 - Copy.png"))
//									, ImageIO.read((new File("C:/Users/John/Downloads/single_page_png_1.png"))));
//		System.out.println(result);
//		Utils.unzipFile(Constants.Download_Destination + "/single_page_png_hd.zip"
//							, Constants.Download_Destination + "/testUnzip");
//		Utils.unZipIt(Constants.Download_Destination + "/all_widgets_png.zip"
//							, Constants.Download_Destination + "/testUnzip");
//		Utils.unzip(new File(Constants.Download_Destination + "/all_widgets_png.zip")
//				, Constants.Download_Destination + "/testUnzip");
//		String text = "/infographic/thumbnail/fetch_brand_multipage/8471/9b9bbcf8-aa0e-4b5a-a5c9-6e053f3f8222.png?brand_template_id=353";
//		int index1 = text.lastIndexOf("/");
//		int index2 = text.lastIndexOf("?");
//		System.out.println(text.substring(index1 + 1, index2 - 4));
		//intercom api test
//		String createdTemplateId = "fe82a0f5-b602-4613-bcbc-250af8692c42";
//		String infographId = "b432b384-83cf-4b7a-b3a2-38fef5e519b0";
//		List<String> expectedEvents = Arrays.asList("create infographic");
//		JSONArray actualEvents = F_Intercom_API.callIntercomAPI("buzmem0914034909@autotest.com");
//		JSONObject metadata = actualEvents.getJSONObject(0).getJSONObject("metadata");
//		System.out.println(metadata);
//		System.out.println(metadata.get("template").toString());
//		System.out.println(metadata.get("infographic_id").toString());
//		System.out.println(metadata.get("type").toString());
		
//		Utils.unzipFile(Constants.Download_Destination + "/single_page_png_hd.zip", Constants.Download_Destination);
		
		//Create list of users
//		for (int i = 0; i < 10; i++)
//		{
//			O_Account acc = new O_Account(Utils.generateRandomEmail("test" + i));
//		}
//		
//		Utils.Initialize();
//		Constants.driver.get(Constants.Free_Sign_Up_Url);
//		String test = "this is for test=john";
//		int i = test.lastIndexOf("=");
//		String id = test.substring(i+ 1);
//		System.out.println(id);
//		Stripe.apiKey = Constants.Stripe_Secret_Key;
//		String invoiceId = "in_1DPWTTKNOllJIMsdNB4wctya";
//		Invoice i = Invoice.retrieve(invoiceId);
//		String chargeId = i.getCharge();
//		System.err.println(chargeId);
//		String cusId = i.getCustomer();
//		System.out.println(cusId);
//		String subId = i.getSubscription();
//		System.out.println(subId);
//		System.out.println("Subscription:\n" + sub.toJson());
//		JSONObject cus = F_Stripe_API.getCustomer(invoiceId);
//		String plan = cus.get("plan").toString();
//		System.out.println(cus);
//		System.out.println(plan);
//		JSONObject sub = new JSONObject(cus.getSubscriptions().toJson());
//		JSONObject stripeSub = sub.getJSONArray("data").getJSONObject(0);
//		System.out.println(stripeSub);
//		System.out.println(stripeSub.get("quantity"));
//		System.out.println(stripeSub.get("cancel_at_period_end"));
//		System.out.println(convertUnixDateToCalendarDate(stripeSub.getLong("created")));
//		System.out.println(convertUnixDateToCalendarDate(stripeSub.getLong("start")));
//		System.out.println(convertUnixDateToCalendarDate(stripeSub.getLong("current_period_end")));
//		System.out.println(convertUnixDateToCalendarDate(stripeSub.getLong("billing_cycle_anchor")));
//		System.out.println(stripeSub.get("billing"));
//		JSONObject items = stripeSub.getJSONObject("items");
//		System.out.println(items);
//		
//		JSONObject jo = new JSONObject(cus.toJson());
//		System.out.println(jo);
//		Long createdDate = jo.getLong("created");
//		System.out.println(createdDate);
//		Date d = convertUnixDateToCalendarDate(createdDate);
//		System.out.println(d);
//		Stripe.apiKey = Constants.Stripe_Secret_Key;
//		String sub = Subscription.retrieve("sub_DtzMG7o42OU2GE").toJson();
//		JSONObject sub1 = new JSONObject(sub);
//		System.out.println(sub1.get("canceled_at"));
//		YearMonth ym = YearMonth.of(2016, 2);
//		System.out.println(ym.lengthOfMonth());
//		Date d = Calendar.getInstance().getTime();
//		int year = Calendar.getInstance().get(Calendar.YEAR);
//		int month = Calendar.getInstance().get(Calendar.MONTH);
//		int monthLength = YearMonth.of(year, month + 1).lengthOfMonth();
//		System.out.println(monthLength);
//		String expectedEndDate = Utils.getDateFromDateTime((Utils.addDays(d, monthLength)));
//		System.out.println(expectedEndDate);
//		Date d = Calendar.getInstance().getTime();
//		Date newDate = calculateExpectedEndDateQuarterlyPlan(d);
//		System.out.println(newDate);
//		Date d = Calendar.getInstance().getTime();
//		Date d1 = calculateExpectedEndDateQuarterlyPlan(d);
//		System.out.println(calculateExpectedEndDateQuarterlyPlan(d1));
//		DbUtils.DBSetup();
//		String[][] email = DbUtils.DBReturnMatrixResult("select email, first_name, last_name  from users where first_name = 'An' order by id desc limit 10;");
//		for (int i = 0; i < email.length; i++)
//		{
//			for (int j = 0; j < email[i].length; j++)
//			{
//				System.out.println(email[i][j]);
//			}
//		}
//		DbUtils.DBCloseConnection();
		
//		String query = "select id, first_name, last_name, user_type, infograph_limit from users where id = '40357'";
//		DbUtils.DBSetup();
//		//User info in users table
//		String[][] userInfo = DbUtils.DBReturnMatrixResult(query);
//		assertTrue(userInfo[0][1].equals("Automation"));
//		assertTrue(userInfo[0][2].equals("Test"));
//		assertTrue(userInfo[0][3].equals("2"),"failed");
//		assertTrue(userInfo[0][4].equals("5"),"failed");
//		//onboarding table
//		query = "select q1, q2, q3 from onboarding where user_id = " + userInfo[0][0];
//		String[][] onbAnswer = DbUtils.DBReturnMatrixResult(query);
//		assertTrue(onbAnswer[0][0].equals("nonprofit"), "onb");
//		assertTrue(onbAnswer[0][1].equals("knowlmgt"));
//		assertTrue(onbAnswer[0][2].equals("posters"));
//		//subscriptions table
//		query = "select subscription, status, paused from subscriptions where user_id = " + userInfo[0][0];
//		String[][] sub = DbUtils.DBReturnMatrixResult(query);
//		assertTrue(sub[0][0].equals(Constants.Stripe_PreM_Plan), "subscription");
//		assertTrue(sub[0][1].equals("1"));
//		assertTrue(sub[0][2].equals("false"));
////		//stripe_subscriptions table
////		query = "select customer, plan, vg_plan_id, quantity, status from stripe_subscriptions where id = " + invoice.getSubscription();
////		String[][] sripeSub = DbUtils.DBReturnMatrixResult(query);
////		assertTrue(sripeSub[0][0].equals(invoice.getCustomer()), "subscription details");
////		assertTrue(sripeSub[0][1].equals(Constants.Stripe_PreM_Plan));
////		assertTrue(sripeSub[0][2].equals("1"));
////		assertTrue(sripeSub[0][3].equals("active"));
//		DbUtils.DBCloseConnection();
		System.out.println(F_Intercom_API.callIntercomAPI("an@venngage.com"));
	}
	
	
	
	
	public static Date calculateExpectedEndDateQuarterlyPlan(Date date)
	{
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int year = cal.get(Calendar.YEAR);
		int monthIndex = cal.get(Calendar.MONTH);
		for (int i = 0; i < 3; i++)
		{
			int monthLength = YearMonth.of(year, ++monthIndex).lengthOfMonth();
			System.out.println(monthIndex);
			System.out.println(monthLength);
			date = Utils.addDays(date, monthLength);
			if (monthIndex == 12)
			{
				monthIndex = 0;
				year++;
				
			}
		}
		return date;
	}
	
	public static String getInvoiceId()
	{
		String invoiceId = "";
		By lnk_BillingHistory = By.id("account-leftpanel-billing");
		Utils.clickElement(lnk_BillingHistory);
		By lnk_invoiceId = By.xpath("//div[@id='billing_profile']//tr[1]/td[4]/a");
		invoiceId = Utils.captureWebElement(lnk_invoiceId).getText();
		return invoiceId;
	}
	
	public static Date convertUnixDateToCalendarDate(long unixDate)
	{
	    return new Date(unixDate * 1000);
	}
	
	public static Date addDays(Date date, int days) {
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(date);
		gc.add(Calendar.DATE, days);
				
		return gc.getTime();
	}
	
	


}
