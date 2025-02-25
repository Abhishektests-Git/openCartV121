package testCase;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC_002_LoginTest extends BaseClass{
	@Test(groups={"sanity","master"})
	public void verifyLogin() {
		try {
		logger.info("*****Login Test started******");
		HomePage homePage=new HomePage(driver);
		homePage.clickMyAccount();
		homePage.clickLogin();
		
		LoginPage loginPage=new LoginPage(driver);
		loginPage.setEmail(prop.getProperty("email"));
		loginPage.setPassword(prop.getProperty("password"));
		loginPage.clickLogin();
		
		MyAccountPage myAccount=new MyAccountPage(driver);
		boolean targetPage=myAccount.isMyAccountPageExists();
		Assert.assertTrue(targetPage);
		
		myAccount.logout();
		}
		catch(Exception e) {
//			logger.debug();
			Assert.fail();
			System.out.println(e.getMessage());
		}
		logger.info("*****Login Test ended******");


	}
	
}
