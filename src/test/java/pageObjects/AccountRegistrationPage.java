package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountRegistrationPage extends BasePage{
//	constructor
	public AccountRegistrationPage(WebDriver driver) {
		// passing the driver to parent class (BasePage)		
		super(driver);
	}

//	locators

@FindBy(xpath="//input[@id='input-firstname']") WebElement firstName;
@FindBy(xpath="//input[@id='input-lastname']")	WebElement lastName;
@FindBy(xpath="//input[@id='input-email']")		WebElement email;
@FindBy(xpath="//input[@id='input-telephone']")	WebElement telephone;
@FindBy(xpath="//input[@id='input-password']")	WebElement txtPassword;
@FindBy(xpath="//input[@id='input-confirm']")	WebElement txtConfirmPassword;
@FindBy(xpath="//input[@value='0']")			WebElement radioNewsLetter;
@FindBy(xpath="//input[@name='agree']")			WebElement chkdPolicy;
@FindBy(xpath="//input[@value='Continue']")		WebElement btnContinue;
@FindBy(xpath="//*[@id='content']/h1")			WebElement confirmationMessage;

//	actions
public void setFirstName(String FName) {
	firstName.sendKeys(FName);
}

public void setLastName(String LName) {
	lastName.sendKeys(LName);
}

public void setEmail(String Email) {
	email.sendKeys(Email);
}

public void setTelephone(String TPh) {
	telephone.sendKeys(TPh);
}

public void setPassword(String password) {
	txtPassword.sendKeys(password);
}

public void setConfirmPassword(String confPwd) {
	txtConfirmPassword.sendKeys(confPwd);
}

public void setNewsLetterOff() {
	radioNewsLetter.click();
}

public void agree() {
	chkdPolicy.click();
}

public void clickContinue() {
	btnContinue.click();
}

public String getConfirmationMsg() {
	try {
		return confirmationMessage.getText();
	}
	catch(Exception e) {
		return e.getMessage();
	}
}
}
