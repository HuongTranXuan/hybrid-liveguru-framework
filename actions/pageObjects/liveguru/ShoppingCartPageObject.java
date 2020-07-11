package pageObjects.liveguru;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;
import pageUIs.liveguru.ShoppingCartPageUI;

public class ShoppingCartPageObject extends AbstractPage {
	WebDriver driver; 
	
	public ShoppingCartPageObject(WebDriver driver) {
		super();
		this.driver = driver;
	}

	public void inputCouponCode(String couponCode) {
		waitElementVisiable(driver, ShoppingCartPageUI.COUPON_CODE);
		sendKeyToElement(driver, ShoppingCartPageUI.COUPON_CODE,couponCode );
	}

	public void clickToApplyLink() {
		waitElementClickable(driver, ShoppingCartPageUI.APPLY_LINK);
		clickToElement(driver, ShoppingCartPageUI.APPLY_LINK);
		
	}

	public String getCouponCodeInTotal() {
		waitElementVisiable(driver, ShoppingCartPageUI.DISCOUNT_CODE);
		return getElementText(driver, ShoppingCartPageUI.DISCOUNT_CODE);
	}

	public String getDiscountMoneyInTotal() {
		waitElementVisiable(driver, ShoppingCartPageUI.DISCOUNT_MONEY);
		return getElementText(driver, ShoppingCartPageUI.DISCOUNT_MONEY);
	}

	public String getSuccessMSG() {
		waitElementVisiable(driver, ShoppingCartPageUI.SUCCESS_MSG);
		return getElementText(driver, ShoppingCartPageUI.SUCCESS_MSG);
	}

}
