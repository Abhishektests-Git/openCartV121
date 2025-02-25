package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAccountPage extends BasePage{
	public MyAccountPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath="//h2[normalize-space()='My Account']") WebElement msgHEading;
	@FindBy(xpath="//a[@class='list-group-item'][normalize-space()='Logout']") WebElement btnLogout;
	
	public boolean isMyAccountPageExists() {
		try {
		return msgHEading.isDisplayed();
		}
		catch(Exception e) {
//			System.out.println(e.getMessage());
			return false;
		}
		
	}
	
	public void logout() {
		btnLogout.click();
	}
}
