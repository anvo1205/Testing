package Function;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.json.JSONException;
import org.json.JSONObject;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Customer;
import com.stripe.model.Invoice;
import com.stripe.model.Plan;
import com.stripe.model.Subscription;

import Utility.Constants;
import Utility.Utils;

public class F_Stripe_API {
	
	/**
	 * Return customer object based on invoiceId from Venngage webUI
	 * @param String: invoiceId
	 * @return JSONObject: customer
	 * @throws StripeException
	 * @throws JSONException
	 * */
	public static JSONObject getCustomerJSONObject(String invoiceId) throws StripeException, JSONException
	{
		Stripe.apiKey = Constants.Stripe_Secret_Key;
		Invoice i = Invoice.retrieve(invoiceId);
		String cusId = i.getCustomer();
		String cus = Customer.retrieve(cusId).toJson();
		return new JSONObject(cus);
	}
	
	/**
	 * Return subscriptions object based on invoiceId from Venngage webUI
	 * @param String: invoiceId
	 * @return JSONObject: subscriptions
	 * @throws StripeException
	 * @throws JSONException
	 * */
	public static JSONObject getSubscriptionJSONObject(String invoiceId) throws StripeException, JSONException
	{
		Stripe.apiKey = Constants.Stripe_Secret_Key;
		Invoice i = Invoice.retrieve(invoiceId);
		String subId = i.getSubscription();
		String sub = Subscription.retrieve(subId).toJson();
		return new JSONObject(sub);
	}
	
	/**
	 * This method will return pre-define plans from stripe.plans.json file
	 * @param String: planId (Business_Plan_1, Business_Plan_3, Business_Plan_12,
	 * Infographic_Plan_1, Infographic_Plan_3, Infographic_Plan_12,
	 * Education_Plan_1_1, Education_Plan_1_12)
	 * @return JSONObject: plan details
	 * @throws IOException 
	 * @throws JSONException 
	 * */
	public static JSONObject getPredefinedPlan(String planId) throws StripeException, IOException, JSONException
	{
		String filePath = Constants.Folder_TestData + Constants.File_StripeData;
		String ja = new String(Files.readAllBytes(Paths.get(filePath)));
		JSONObject plan = new JSONObject(ja).getJSONObject(planId);
		return plan;
	}
	
	/**
	 * This method will return pre-define plans from Stripe
	 * @param String: planId (Business_Plan_1, Business_Plan_3, Business_Plan_12,
	 * Infographic_Plan_1, Infographic_Plan_3, Infographic_Plan_12,
	 * Education_Plan_1_1, Education_Plan_1_12)
	 * @return JSONObject: plan details
	 * @throws IOException 
	 * @throws JSONException 
	 * */
	public static JSONObject getPredefinedPlanFromStripe(String planId) throws StripeException, IOException, JSONException
	{
		Stripe.apiKey = Constants.Stripe_Secret_Key;
		return new JSONObject(Plan.retrieve(planId).toJson());
	}
	
	/**
	 * This method will put expected values of customers into a customer object
	 * @param String: List of String variables
	 * @return JSONObject
	 * @throws JSONException 
	 * */
	public static JSONObject setExpectedCustomer(String createdDate, String email, String cardBrand
			, String expMonth, String expYear, String last4Digit, String cardHolderName) throws JSONException
	{
		JSONObject expectedCustomer = new JSONObject();
		expectedCustomer.put("created", createdDate);
		expectedCustomer.put("email", email);
		expectedCustomer.put("brand", cardBrand);
		expectedCustomer.put("exp_month", expMonth);
		expectedCustomer.put("exp_year", expYear);
		expectedCustomer.put("last4", last4Digit);
		expectedCustomer.put("name", cardHolderName);
		return expectedCustomer;
	}
	
	/**
	 * This method will get actual customer object from stripe,
	 * get keys and values need to be checked, put into a JSONObject
	 * @param String
	 * @return JSONObject
	 * @throws JSONException
	 * @throws StripeException
	 * */
	public static JSONObject getActualCustomer(String cusId) throws JSONException, StripeException
	{
		JSONObject customer = new JSONObject(Customer.retrieve(cusId).toJson());
		JSONObject actualCustomer = new JSONObject();
		String createdDate = Utils.getDateFromDateTime((Utils.convertUnixTimeToCalendarDate(customer.getLong("created"))));
		actualCustomer.put("created", createdDate);
		actualCustomer.put("email", customer.get("email"));
		JSONObject dataObject = customer.getJSONObject("sources").getJSONArray("data").getJSONObject(0);
		actualCustomer.put("brand", dataObject.get("brand"));
		actualCustomer.put("exp_month", dataObject.get("exp_month").toString());
		actualCustomer.put("exp_year", dataObject.get("exp_year").toString());
		actualCustomer.put("last4", dataObject.get("last4"));
		actualCustomer.put("name", dataObject.get("name"));
		return actualCustomer;
	}
	
	/**
	 * This method will put expected values of subscription into a subscription object
	 * @param String: List of String variables
	 * @return JSONObject
	 * @throws JSONException 
	 * */
	public static JSONObject setExpectedSubscription(String startDate, String endDate, String cancelledAt, String cancelEndOfPeriod, JSONObject plan, String status) throws JSONException
	{
		JSONObject expectedSubscription = new JSONObject();
		expectedSubscription.put("billing", "charge_automatically");
		expectedSubscription.put("billing_cycle_anchor", startDate);
		if (!cancelledAt.isEmpty())
		{
			expectedSubscription.put("canceled_at", cancelledAt);
		}
		expectedSubscription.put("cancel_at_period_end", cancelEndOfPeriod);
		expectedSubscription.put("created", startDate);
		expectedSubscription.put("current_period_end", endDate);
		expectedSubscription.put("current_period_start", startDate);
		expectedSubscription.put("plan", plan);
		expectedSubscription.put("status", status);
		return expectedSubscription;
	}
	
	/**
	 * This method will get actual subscription object from stripe,
	 * get keys and values need to be checked, put into a JSONObject
	 * @param String
	 * @return JSONObject
	 * @throws JSONException
	 * @throws StripeException
	 * */
	public static JSONObject getActualSubscription(String subId) throws JSONException, StripeException
	{
		JSONObject subscription = new JSONObject(Subscription.retrieve(subId).toJson());
		JSONObject actualSubscription = new JSONObject();
		String startDate = Utils.getDateFromDateTime((Utils.convertUnixTimeToCalendarDate(subscription.getLong("current_period_start"))));
		String endDate = Utils.getDateFromDateTime((Utils.convertUnixTimeToCalendarDate(subscription.getLong("current_period_end"))));
		String canceledAt = null;
		if (!subscription.get("canceled_at").equals(null))
		{
			canceledAt = Utils.getDateFromDateTime((Utils.convertUnixTimeToCalendarDate(subscription.getLong("canceled_at"))));
		}
		actualSubscription.put("billing", subscription.get("billing"));
		actualSubscription.put("billing_cycle_anchor", startDate);
		actualSubscription.put("canceled_at", canceledAt);
		actualSubscription.put("cancel_at_period_end", subscription.get("cancel_at_period_end").toString());
		actualSubscription.put("created", startDate);
		actualSubscription.put("current_period_end", endDate);
		actualSubscription.put("current_period_start", startDate);
		JSONObject dataObject = subscription.getJSONObject("items").getJSONArray("data").getJSONObject(0);
		actualSubscription.put("plan", dataObject.get("plan"));
		actualSubscription.put("status", subscription.get("status"));
		return actualSubscription;
	}
	
	public static JSONObject setExpectedInvoice(String amount, String date, String endDate) throws JSONException
	{
		JSONObject expectedInvoice = new JSONObject();
		expectedInvoice.put("amount_due", amount);
		expectedInvoice.put("amount_paid", amount);
		expectedInvoice.put("attempt_count", 1);
		expectedInvoice.put("billing", "charge_automatically");
		expectedInvoice.put("date", date);
		expectedInvoice.put("paid", "true");
		expectedInvoice.put("period_end", endDate);
		expectedInvoice.put("period_start", date);
		expectedInvoice.put("subtotal", amount);
		expectedInvoice.put("total", amount);
		return expectedInvoice;
	}
	
	public static JSONObject getActualInvoice(String invoiceId) throws JSONException, StripeException
	{
		JSONObject invoice = new JSONObject(Invoice.retrieve(invoiceId).toJson());
		JSONObject actualInvoice = new JSONObject();
		actualInvoice.put("amount_due", invoice.get("amount").toString());
		actualInvoice.put("amount_paid", invoice.get("amount").toString());
		actualInvoice.put("attempt_count", invoice.get("attempt_count").toString());
		actualInvoice.put("billing", invoice.get("billing"));
		actualInvoice.put("date", Utils.convertUnixTimeToCalendarDate(invoice.getLong("date")));
		actualInvoice.put("paid", invoice.get("paid").toString());
		actualInvoice.put("period_end", Utils.convertUnixTimeToCalendarDate(invoice.getLong("period_end")));
		actualInvoice.put("period_start", Utils.convertUnixTimeToCalendarDate(invoice.getLong("period_start")));
		actualInvoice.put("subtotal", invoice.get("subtotal").toString());
		actualInvoice.put("total", invoice.get("total").toString());
		return actualInvoice;
	}
	
	public static boolean compareTwoJsonObjects(JSONObject expected, JSONObject actual)
	{
		if (expected.toString().equals(actual.toString()))
		{
			return true;
		}
		else
		{
				System.out.println("Actual object is different from expected object!");
				System.out.println("actual object: " + actual);
				System.out.println("expected object: " + expected);
			return false;
		}
	}

}
