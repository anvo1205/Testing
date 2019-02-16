package Testcase;

import org.testng.annotations.Test;

import Object.O_Account;
import Utility.Constants;
import Utility.Utils;


public class GenerateAutoBrandOnbUsers {
  @Test
  public void generateUsers() throws InterruptedException {
	  for (int i = 0; i < 10; i++)
	  {
		  Utils.Initialize();
		  Constants.driver.get("https://infograph.venngage.com/signup");
		  O_Account acc = new O_Account(Utils.generateRandomEmail("autobrand_user" + i + "_"));
		  System.out.println(acc.getEmail());
		  acc.Register();
		  Constants.driver.quit();
	  }
  }
}
