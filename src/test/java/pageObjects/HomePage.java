package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
public class HomePage extends BasePage{
	WebDriver driver;
//	constructor
	public HomePage(WebDriver driver) {
		// passing the driver to parent class (BasePage)		
		super(driver);
	}

//	locators
	@FindBy(xpath="//a[@title='My Account']") WebElement lnkMyAccount;
	@FindBy(xpath="//a[text()='Register']") WebElement lnkRegister;
	@FindBy(xpath="//a[text()='Login']") WebElement lnkLogin;

	
//actions
	public void clickMyAccount() {
		lnkMyAccount.click();
	}
	public void clickRegister() {
		lnkRegister.click();
	}
	public void clickLogin() {
		lnkLogin.click();
	}
}
