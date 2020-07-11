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

	public void clickButtonUpdate() {
		waitElementVisiable(driver, ShoppingCartPageUI.BUTTON_UPDATE);
		waitElementClickable(driver, ShoppingCartPageUI.BUTTON_UPDATE);
		clickToElement(driver, ShoppingCartPageUI.BUTTON_UPDATE);
	}

	public void clickToApplyLink() {
		waitElementClickable(driver, ShoppingCartPageUI.APPLY_LINK);
		clickToElement(driver, ShoppingCartPageUI.APPLY_LINK);

	}
	
	public void clickEmtyCartLink() {
		waitElementClickable(driver, ShoppingCartPageUI.EMPTY_CART);
		clickToElement(driver, ShoppingCartPageUI.EMPTY_CART);
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

	public String getErrorItemMsg() {
		waitElementVisiable(driver, ShoppingCartPageUI.ITEM_MSG_ERROR);
		return getElementText(driver, ShoppingCartPageUI.ITEM_MSG_ERROR);
	}

	public String getErrorMsgAddTooManyQty() {
		waitElementVisiable(driver, ShoppingCartPageUI.ERROR_MSG);
		return getElementText(driver, ShoppingCartPageUI.ERROR_MSG);
	}
	public String getMoneyGrandTotal() {
		waitElementVisiable(driver, ShoppingCartPageUI.GRANT_TOTAL_MONEY);
		return getElementText(driver, ShoppingCartPageUI.GRANT_TOTAL_MONEY);
	}

	public String getErrorMsgEmptyCart() {
		waitElementVisiable(driver, ShoppingCartPageUI.MSR_ERROR_EMPTY_CART);
		return getElementText(driver, ShoppingCartPageUI.MSR_ERROR_EMPTY_CART);
	}

	public void inputQualtyProduct(String qty) {
		waitElementVisiable(driver, ShoppingCartPageUI.INPUT_QUALTY);
		sendKeyToElement(driver, ShoppingCartPageUI.INPUT_QUALTY, qty);
	}

	public void inputCouponCode(String couponCode) {
		waitElementVisiable(driver, ShoppingCartPageUI.COUPON_CODE);
		sendKeyToElement(driver, ShoppingCartPageUI.COUPON_CODE, couponCode);
	}





}
