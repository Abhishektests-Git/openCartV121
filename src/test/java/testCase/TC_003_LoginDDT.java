package testCase;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

public class TC_003_LoginDDT extends BaseClass{
	@Test(dataProvider="LoginData",dataProviderClass=DataProviders.class, groups="dataDriven")
	public void verifyLoginDDT(String email,String password, String exp)
	{
		logger.info("*****  starting TC_003_LoginDDT test  *****");
		try{
		logger.info("*****Login Test started******");
		HomePage homePage=new HomePage(driver);
		homePage.clickMyAccount();
		homePage.clickLogin();
		
		LoginPage loginPage=new LoginPage(driver);
		loginPage.setEmail(email);
		loginPage.setPassword(password);
		loginPage.clickLogin();
		
		MyAccountPage myAccount=new MyAccountPage(driver);
		boolean targetPage=myAccount.isMyAccountPageExists();
		
		if(exp.equalsIgnoreCase("valid")) {
			if(targetPage) {
				myAccount.logout();
//				System.out.println( "valid success");
				Assert.assertTrue(targetPage);
			}
			else {
//				System.out.println("valid fail");
				Assert.assertTrue(false);
			}
			
			
		}
		else if(exp.equalsIgnoreCase("invalid")){
			if(!targetPage) {
//				System.out.println("invalid fail");
				Assert.assertTrue(true);

			}
			else {
				myAccount.logout();
//				System.out.println("invalid success");
				Assert.assertTrue(false);

			}
		}
	}
		catch(Exception e) {
			System.out.println(e.getMessage());
			Assert.fail();
		}
		logger.info("*****  Finishing TC_003_LoginDDT test  *****");

	}
}
