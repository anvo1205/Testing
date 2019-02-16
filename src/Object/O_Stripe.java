package Object;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import com.stripe.model.Customer;
import com.stripe.model.Invoice;
import com.stripe.model.Subscription;

import Utility.Constants;

public class O_Stripe {
	private String email;
	private String cusId;
	private String subId;
	private String invoiceId;
	private String planName;
	private String amount;
	private String period;
	
	public O_Stripe(String email, String planName, String amount, String period, String invoiceId)
	{
		this.email = email;
		this.planName = planName;
		this.amount = amount;
		this.period = period;
		this.invoiceId = invoiceId;
	}
	
	public O_Stripe() {
		
	}

	public Invoice getInvoice(String invoiceId) throws StripeException
	{
		Stripe.apiKey = Constants.Stripe_Secret_Key;
		return Invoice.retrieve(invoiceId);
	}
	
	public Customer getCustomer(String cusId) throws StripeException
	{
		return Customer.retrieve(cusId);
	}
	
	public Subscription getSubscription(String subId) throws StripeException
	{
		return Subscription.retrieve(subId);
	}
	
	public Charge getCharge(String chargeId) throws StripeException
	{
		return Charge.retrieve(chargeId);
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCusId() {
		return cusId;
	}

	public void setCusId(String cusId) {
		this.cusId = cusId;
	}

	public String getSubId() {
		return subId;
	}

	public void setSubId(String subId) {
		this.subId = subId;
	}

	public String getInvoiceId() {
		return invoiceId;
	}

	public void setInvoiceId(String invoiceId) {
		this.invoiceId = invoiceId;
	}

	public String getPlanName() {
		return planName;
	}

	public void setPlanName(String planName) {
		this.planName = planName;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}
	
	public String getPeriod()
	{
		return period;
	}

	public void setPeriod(String period)
	{
		this.period = period;
	}


}
