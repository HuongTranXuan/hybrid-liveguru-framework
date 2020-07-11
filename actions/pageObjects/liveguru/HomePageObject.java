package pageObjects.liveguru;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;
import pageUIs.liveguru.HomePageUI;

public class HomePageObject extends AbstractPage {
	private WebDriver driver;

	public HomePageObject(WebDriver driver) {
		super();
		this.driver = driver;
	}

	public void clickToMyAccountButton() {
		waitElementClickable(driver, HomePageUI.MY_ACCOUNT_LINK);
		clickToElement(driver, HomePageUI.MY_ACCOUNT_LINK);
	}

	public void clickToRegisterLink() {
		waitElementClickable(driver, HomePageUI.REGISTER_LINK);
		clickToElement(driver, HomePageUI.REGISTER_LINK);
	}

	public void clickToAccountLink() {
		waitElementClickable(driver, HomePageUI.ACCOUNT_MENU_LINK);
		clickToElement(driver, HomePageUI.ACCOUNT_MENU_LINK);
	}

	public void clickToMobileMenu() {
		waitElementClickable(driver, HomePageUI.ACCOUNT_MENU_LINK);
		clickToElement(driver, HomePageUI.MOBILE_LINK);
	}
}
