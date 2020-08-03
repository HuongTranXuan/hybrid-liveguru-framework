package pageObjects.liveguru;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;
import pageUIs.liveguru.CompareProductPageUI;

public class CompareProductPageObject extends AbstractPage{
	WebDriver driver;

	public CompareProductPageObject(WebDriver driver) {
		super();
		this.driver = driver;
	}
	
	public void closeWindowCompare(String parentID) {
		switchToWindowByID(driver, CompareProductPageUI.COMPARE_ID);
		closeAllWindowsWithoutParent(driver, parentID);
	}
	
	public String getTextPage() {
		waitElementVisiable(driver, CompareProductPageUI.COMPARE_PRODUCT);
		return getElementText(driver, CompareProductPageUI.COMPARE_PRODUCT);
	}

	public String getTextProductLeft() {
		waitElementVisiable(driver, CompareProductPageUI.PRODUCT_TEXT_LEFT);
		return getElementText(driver, CompareProductPageUI.PRODUCT_TEXT_LEFT);
	}

	public String getTextProductRight() {
		waitElementVisiable(driver, CompareProductPageUI.PRODUCT_TEXT_RIGHT);
		return getElementText(driver, CompareProductPageUI.PRODUCT_TEXT_RIGHT);
	}

	
}
