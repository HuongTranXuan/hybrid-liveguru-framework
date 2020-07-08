package pageObjects.liveguru;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;
import pageUIs.liveguru.LoginPageUI;

public class LoginPageObject extends AbstractPage{
	WebDriver driver;
	
	public LoginPageObject(WebDriver driver) {
		super();
		this.driver = driver;
	}

	public void clickToCreateAnAccountButton() {
		waitElementClickable(driver, LoginPageUI.CREATE_AN_ACCOUNT_BUTTON);
		clickToElement(driver, LoginPageUI.CREATE_AN_ACCOUNT_BUTTON);
	}

	
}
