package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage{
//Constructor
	public LoginPage(WebDriver driver) {
		super(driver);
	}
//	Locators

	@FindBy(xpath="//input[@id='input-email']") WebElement txtEmail;
	@FindBy(xpath="//input[@id='input-password']")	WebElement txtPwd;
	@FindBy(xpath="//input[@value='Login']")	WebElement	btnLogin;
//	Actions
	public void setEmail(String email) {
		txtEmail.sendKeys(email);
	}
	
	public void setPassword(String pwd) {
		txtPwd.sendKeys(pwd);
	}
	
	public void clickLogin() {
		btnLogin.click();
	}

}
