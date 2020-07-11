package pageObjects.liveguru;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import commons.AbstractPage;
import pageUIs.liveguru.MobilePageUI;

public class MobilePageObject extends AbstractPage {

	WebDriver driver;
	//private List<WebElement> productNames;

	public MobilePageObject(WebDriver driver) {
		super();
		this.driver = driver;
		//productNames = findElementsByXpath(driver, MobilePageUI.PRODUCT_NAMES);
	}

	public String getCostProduct(String productName) {
		String productLocatorFull = "";
		List<WebElement> productNames = findElementsByXpath(driver, MobilePageUI.PRODUCT_NAMES);
		for (WebElement element : productNames) {
			if (element.getAttribute("title").equals(productName)) {
				productLocatorFull = MobilePageUI.PRODUCT_FRONT + productName + MobilePageUI.PRODUCT_COST_END;
				break;
			}
		}
		if (productLocatorFull.equals("")) {
			return "Can't get product's cost";
		}
		waitElementVisiable(driver, productLocatorFull);
		return getElementText(driver, productLocatorFull);
	}

	public String getSuccessMsg() {
		waitElementVisiable(driver, MobilePageUI.SUCCESS_MSG);
		return getElementText(driver, MobilePageUI.SUCCESS_MSG);
	}
	public String getParentId() {
		return MobilePageUI.MOBILE_PAGE_ID;
	}

	public void clickToProduct(String productName) {
		String productLocatorFull = "";
		List<WebElement> productNames = findElementsByXpath(driver, MobilePageUI.PRODUCT_NAMES);
		for (WebElement element : productNames) {
			if (element.getAttribute("title").equals(productName)) {
				productLocatorFull = MobilePageUI.PRODUCT_FRONT + productName + MobilePageUI.PRODUCT_END;
				break;
			}
		}
		waitElementClickable(driver, productLocatorFull);
		clickToElement(driver, productLocatorFull);
	}

	public void clickAddToCartButton(String productName) {
		String productLocatorFull = "";
		List<WebElement> productNames = findElementsByXpath(driver, MobilePageUI.PRODUCT_NAMES);
		for (WebElement element : productNames) {
			if (element.getAttribute("title").equals(productName)) {
				productLocatorFull = MobilePageUI.PRODUCT_FRONT + productName + MobilePageUI.PRODUCT_BUTTON_ATC_END;
				break;
			}
		}
		waitElementClickable(driver, productLocatorFull);
		clickToElement(driver, productLocatorFull);

	}

	public void clickAddToCompareLink(String productName) {
		String productLocatorFull = "";
		List<WebElement> productNames = findElementsByXpath(driver, MobilePageUI.PRODUCT_NAMES);
		for (WebElement element : productNames) {
			System.out.println(element.getAttribute("title"));
			if (element.getAttribute("title").equals(productName)) {
				productLocatorFull = MobilePageUI.PRODUCT_FRONT + productName + MobilePageUI.PRODUCT_LINK_COMPARE_END;
				System.out.println(productName+"2");
				break;
			}
		}
		waitElementClickable(driver, productLocatorFull);
		clickToElement(driver, productLocatorFull);
	}

	public void clickCompareButton() {
		waitElementClickable(driver, MobilePageUI.COMPARE_BUTTON);
		clickToElement(driver, MobilePageUI.COMPARE_BUTTON);
	}

	public void switchToWindowCompare() {
		switchToWindowByID(driver, MobilePageUI.MOBILE_PAGE_ID);
	}


}
