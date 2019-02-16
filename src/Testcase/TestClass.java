package Testcase;

import static org.junit.Assert.*;

import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Customer;
import com.stripe.model.Invoice;
import com.stripe.model.Subscription;

import Function.EventPropertyException;
import Function.F_Mixpanel_API;
import Function.F_MyBrand;
import Function.F_MyHome;
import Function.F_SignIn;
import Interface.I_MyBrand;
import Interface.I_TemplatePage;
import Object.O_Account;
import Object.O_Onboarding;
import Object.O_Payment;
import Utility.Constants;
import Utility.ExcelUtils;
import Utility.Utils;
import bsh.util.Util;

public class TestClass {

	
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		System.setProperty(Constants.chromeDriver, Constants.chromeDriverPath);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		ChromeOptions options = new ChromeOptions();  
//		options.addArguments("--headless", "--disable-gpu", "--window-size=1920,1200","--ignore-certificate-errors");  
		Constants.driver = new ChromeDriver(options);
		Constants.wait = new WebDriverWait(Constants.driver, 15);
		Constants.driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
//		Constants.driver.get("https://infograph.venngage.beer");
		Constants.driver.get("https://infograph.venngage.beer/signin");
		Constants.driver.manage().window().maximize();
	}

	@After
	public void tearDown() throws Exception {
		Constants.driver.quit();
	}

	@Test
	public void Test() throws InterruptedException, StripeException
	{
		O_Account acc = new O_Account("teststatic@venngage.co");
		acc.SignIn();
		Constants.driver.get(Constants.Subscriptions_Url);
		String invoiceId = getInvoiceId();
		Subscription sub = getSubscription(invoiceId);
		System.out.println("Subscription: \n" + sub.toJson());
		Customer cus = getCustomer(invoiceId);
		System.out.println("Customer: " + cus.toJson());
		
	}
	
	public static String getInvoiceId()
	{
		String invoiceId = "";
		By lnk_BillingHistory = By.xpath("//div[@id='styles__root--ezHiv' and text()='Billing History']");
		Utils.clickElement(lnk_BillingHistory);
		By lnk_invoiceId = By.xpath("//div[@id='billing_profile']//tr[1]/td[4]/a");
		invoiceId = Utils.captureWebElement(lnk_invoiceId).getText();
		return invoiceId;
	}
	
	public static Customer getCustomer(String invoiceId) throws StripeException
	{
		Stripe.apiKey = Constants.Stripe_Secret_Key;
		Invoice i = Invoice.retrieve(invoiceId);
		String cusId = i.getCustomer();
		return Customer.retrieve(cusId);
	}
	
	public static Subscription getSubscription(String invoiceId) throws StripeException
	{
		Stripe.apiKey = Constants.Stripe_Secret_Key;
		Invoice i = Invoice.retrieve(invoiceId);
		String subId = i.getSubscription();
		return Subscription.retrieve(subId);
	}
}

	
