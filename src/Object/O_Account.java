package Object;

import Interface.I_SignIn;
import Interface.I_SignUp;
import Utility.Utils;

public class O_Account {

	private String fName;
	private String lName;
	private String email;
	private String password;
	
	public O_Account(String fName, String lName, String email, String password) {
		this.fName = fName;
		this.lName = lName;
		this.email = email;
		this.password = password;
	}
	
	public O_Account(String email)
	{
		this.fName = "Automation";
		this.lName = "Test";
		this.email = email;
		this.password = "123456";
	}
	
	public O_Account(String email, String password)
	{
		this.fName = "Automation";
		this.lName = "Test";
		this.email = email;
		this.password = password;
	}
	
	public String getFirstName ()
	{
		return this.fName;
	}
	public String getLastName ()
	{
		return this.lName;
	}
	public String getEmail ()
	{
		return this.email;
	}
	public String getPassword ()
	{
		return this.password;
	}
	
	public void setFirtName(String fName)
	{
		this.fName = fName;
	}
	
	public void setLastName(String lName)
	{
		this.lName = lName;
	}
	
	public void setEmail(String email)
	{
		this.email = email;
	}
	
	public void setPassword(String pass)
	{
		this.password = pass;
	}
	
	/* Method name	: Register
	 * Description	: This method will input register information into Sign Up page 
	 * Accept		: void
	 * Return		: void
	 */	
	public void Register() throws InterruptedException
	{
		Utils.inputValueIntoTextbox(I_SignUp.txt_FirstName, this.fName);
		Utils.inputValueIntoTextbox(I_SignUp.txt_LastName, this.lName);
		Utils.inputValueIntoTextbox(I_SignUp.txt_Email, this.email);
		Utils.inputValueIntoTextbox(I_SignUp.txt_Password, this.password);
		Utils.captureWebElement(I_SignUp.btn_Register).click();
		Thread.sleep(2000);
	}
	
	public void SignIn() throws InterruptedException
	{
		Utils.inputValueIntoTextbox(I_SignIn.txt_Email, this.email);
		Utils.inputValueIntoTextbox(I_SignIn.txt_Password, this.password);
		Utils.byPassGoogleCapcha();
		Thread.sleep(2000);
	}

}
