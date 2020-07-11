package pageObjects.liveguru;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;
import pageUIs.liveguru.MyDashboardPageUI;

public class MyDashboardPageObject extends AbstractPage {

	WebDriver driver;

	public MyDashboardPageObject(WebDriver driver) {
		super();
		this.driver = driver;
	}

	public String getUserNameAndEmail() {
		waitElementVisiable(driver, MyDashboardPageUI.USER_NAME_AND_EMAIL);
		return getElementText(driver, MyDashboardPageUI.USER_NAME_AND_EMAIL);
	}

//	public String getUserEmail() {
//		waitElementVisiable(driver, MyDashboardPageUI.USER_EMAIL);
//		return getElementText(driver, MyDashboardPageUI.USER_EMAIL);
//	}
	
	public String getWelcomeSuccessMessage() {
		waitElementVisiable(driver, MyDashboardPageUI.WELCOME_MSG);
		return getElementText(driver, MyDashboardPageUI.WELCOME_MSG);
	}

}
