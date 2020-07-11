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
	
}
