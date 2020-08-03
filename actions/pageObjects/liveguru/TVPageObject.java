package pageObjects.liveguru;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import commons.AbstractPage;
import pageUIs.liveguru.MobilePageUI;
import pageUIs.liveguru.TVPageUI;

public class TVPageObject extends AbstractPage {
	WebDriver driver;

	public TVPageObject(WebDriver driver) {
		super();
		this.driver = driver;
	}

	public void clickAddToWishlist(String productName) {
		String productLocatorFull = "";
		List<WebElement> productNames = findElementsByXpath(driver, MobilePageUI.PRODUCT_NAMES);
		for (WebElement element : productNames) {
			if (element.getAttribute("title").equals(productName)) {
				productLocatorFull = TVPageUI.PRODUCT_FRONT + productName + TVPageUI.PRODUCT_END;
				break;
			}
		}
		waitElementClickable(driver, productLocatorFull);
		clickToElement(driver, productLocatorFull);

	}

	public void clickToProductDetail(String productName) {
		String productLocatorFull = TVPageUI.PRODUCT_FRONT + productName + TVPageUI.PRODUCT_NAME_END;
		waitElementClickable(driver, productLocatorFull);
		clickToElement(driver, productLocatorFull);
	}

}
