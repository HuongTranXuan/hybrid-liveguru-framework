package pageObjects.liveguru;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;
import pageUIs.liveguru.MyWishlistPageUI;

public class MyWishlistPageObject extends AbstractPage{
	WebDriver driver;

	public MyWishlistPageObject(WebDriver driver) {
		super();
		this.driver = driver;
	}

	public String getAddtoWishlistSuccessMsg() {
		waitElementVisiable(driver, MyWishlistPageUI.SUCCESS_MSG);
		return getElementText(driver, MyWishlistPageUI.SUCCESS_MSG);
	}

	public void clickToShareWishlistButton() {
		waitElementVisiable(driver, MyWishlistPageUI.SHARE_WISHLIST_BUTTON);
		clickToElement(driver, MyWishlistPageUI.SHARE_WISHLIST_BUTTON);
	}
	
}
