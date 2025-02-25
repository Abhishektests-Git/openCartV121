package testCase;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC_001_AccountRegistrationTest extends BaseClass{
	
	
	
	@Test(groups={"regression","master"})
	public void Verify_account_registration() {
		logger.info("******* Starting TC_001_AccountRegistrationTest **********");
		
		HomePage homePage=new HomePage(driver);
		homePage.clickMyAccount();
		logger.info("******* clicked on MyAccountLink **********");

		homePage.clickRegister();
		logger.info("******* Clicked On RegisterLink **********");

		AccountRegistrationPage registrationPage=new AccountRegistrationPage(driver);
		logger.info("******* Providing customer details **********");

		registrationPage.setFirstName("Abhishek");
		registrationPage.setLastName("Kumar");
		registrationPage.setEmail(randomString()+"@gmail.com");
		registrationPage.setTelephone(randomNumber().toString());
		String password=randomString()+'*'+randomString();
		registrationPage.setPassword(password);
		registrationPage.setConfirmPassword(password);
		registrationPage.setNewsLetterOff();
		registrationPage.agree();
		registrationPage.clickContinue();
		String confMsg="Your Account Has Been Created!";
		if(confMsg.equals(registrationPage.getConfirmationMsg())) {
			Assert.assertTrue(true);
		}
		else {
			logger.error("Test failed");
			logger.debug("Debug logs");
			Assert.assertTrue(false);

		}
		
		
		logger.info("******* Finished TC_001_AccountRegistrationTest **********");

		
	}
}
