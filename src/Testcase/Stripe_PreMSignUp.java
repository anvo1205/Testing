package Testcase;

import org.testng.annotations.Test;

import com.stripe.exception.StripeException;
import com.stripe.model.Invoice;

import Function.F_MyAccount;
import Function.F_Stripe_API;
import Object.O_Account;
import Object.O_Onboarding;
import Object.O_Payment;
import Object.O_Stripe;
import Utility.Constants;
import Utility.Utils;

import org.testng.annotations.BeforeClass;
import static org.testng.Assert.assertTrue;

import java.io.IOException;
import java.util.Date;

import org.json.JSONException;
import org.json.JSONObject;
import org.testng.annotations.AfterClass;

public class Stripe_PreMSignUp {
	@BeforeClass
	public void beforeClass() {
		Utils.Initialize();
	}

	@Test
	public void stripeTest() throws InterruptedException, StripeException, JSONException, IOException {
		O_Account acc = new O_Account(Utils.generateRandomEmail("preM"));
		O_Onboarding onb = new O_Onboarding("Nonprofit/NGO", "Program Manager", "Poster");
		Date d = new Date();
		String expectedStartDate = Utils.getDateFromDateTime(d);
		String expectedEndDate = Utils.getDateFromDateTime((Utils.addDays(d, 30)));
		O_Payment payment = new O_Payment(Constants.MasterCard);
		O_Stripe str = new O_Stripe();
		System.out.println(acc.getEmail());
		Constants.driver.navigate().to(Constants.PreM_SignUp_Url);
		acc.Register();
		payment.upgrade(payment);
		O_Payment.clickContinueOnPremiumModal();
		onb.completeOnboarding(onb);
		
		//Get Invoice object
		Constants.driver.get(Constants.Subscriptions_Url);
		String invoiceId = F_MyAccount.getInvoiceId();
		Invoice i = str.getInvoice(invoiceId);
		
		//Get customer object
		String cusId = i.getCustomer();
		JSONObject cus = F_Stripe_API.getActualCustomer(cusId);
		//Set expected customer
		JSONObject expectedCus = F_Stripe_API.setExpectedCustomer(expectedStartDate, acc.getEmail(), "VisaCard"
				, payment.getCardExpMonth(), payment.getCardExpYear(), payment.getCardNum().substring(12), payment.getCardHolderName());
		
		F_Stripe_API.compareTwoJsonObjects(expectedCus, cus);
		
		//Get subscription object
		String subId = i.getSubscription();
		JSONObject sub = F_Stripe_API.getActualSubscription(subId);
		//Set expected subscription
		JSONObject plan = F_Stripe_API.getPredefinedPlan(Constants.Stripe_PreM_Plan);
		JSONObject expectedSub = F_Stripe_API.setExpectedSubscription(expectedStartDate, expectedEndDate, "", "false", plan, "active");
		
		F_Stripe_API.compareTwoJsonObjects(expectedSub, sub);
		
		//Cancel subscription then check subscription again
		//Cancel subscription
		Constants.driver.get(Constants.Subscriptions_Url);
		F_MyAccount.cancelSubscription("10", 1, "");
		//Wait some seconds for stripe updated
		Thread.sleep(10000);
		//Retrieve subscription object
		sub = F_Stripe_API.getActualSubscription(subId);
		//Set new expected subscription
		expectedSub = F_Stripe_API.setExpectedSubscription(expectedStartDate, expectedEndDate, expectedStartDate, "false", plan, "active");
		//Compare result
		F_Stripe_API.compareTwoJsonObjects(expectedSub, sub);
	}

	@AfterClass
	public void afterClass() {
		Constants.driver.quit();
	}

}
