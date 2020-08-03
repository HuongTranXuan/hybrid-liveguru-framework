package pageObjects.liveguru;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;
import pageUIs.liveguru.WishlistSharingPageUI;

public class WishlistSharingPageObject extends AbstractPage{
	WebDriver driver;

	public WishlistSharingPageObject(WebDriver driver) {
		super();
		this.driver = driver;
	}

	public void inputEmail(String email) {
		waitElementVisiable(driver, WishlistSharingPageUI.EMAIL_TEXTAREA);
		sendKeyToElement(driver, WishlistSharingPageUI.EMAIL_TEXTAREA, email);
	}

	public void inputMessage(String message) {
		waitElementVisiable(driver, WishlistSharingPageUI.MESSAGE_TEXTAREA);
		sendKeyToElement(driver, WishlistSharingPageUI.MESSAGE_TEXTAREA, message);
		
	}

	public void clickToShareWLButton() {
		waitElementClickable(driver, WishlistSharingPageUI.SHARE_WISHLIST_BUTTON);
		clickToElement(driver, WishlistSharingPageUI.SHARE_WISHLIST_BUTTON);
	}
	
}
