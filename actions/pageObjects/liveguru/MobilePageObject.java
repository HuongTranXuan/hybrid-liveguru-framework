package pageObjects.liveguru;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import commons.AbstractPage;
import pageUIs.liveguru.MobilePageUI;

public class MobilePageObject extends AbstractPage{

	WebDriver driver;
	private List<WebElement> productNames;
	public MobilePageObject(WebDriver driver) {
		super();
		this.driver = driver;
		productNames = findElementsByXpath(driver, MobilePageUI.PRODUCT_NAMES);
	}

	public String getCostProduct(String productName) {
		String productLocatorFull="";
		for (WebElement element : productNames) {
			if(element.getAttribute("title").equals(productName)) {
				productLocatorFull = MobilePageUI.PRODUCT_FRONT+productName+MobilePageUI.PRODUCT_COST_END;
				break;
			}
		}
		if(productLocatorFull.equals("")) {
			return "Can't get product's cost";
		}
		waitElementVisiable(driver, productLocatorFull);
		return getElementText(driver, productLocatorFull);
	}

	public void clickToProduct(String productName) {
		String productLocatorFull="";
		for (WebElement element : productNames) {
			if(element.getAttribute("title").equals(productName)) {
				productLocatorFull = MobilePageUI.PRODUCT_FRONT+productName+MobilePageUI.PRODUCT_END;
				break;
			}
		}
		waitElementClickable(driver, productLocatorFull);
		clickToElement(driver, productLocatorFull);
	}

	public void clickAddToCartButton(String productName) {
		String productLocatorFull="";
		for (WebElement element : productNames) {
			if(element.getAttribute("title").equals(productName)) {
				productLocatorFull = MobilePageUI.PRODUCT_FRONT+productName+MobilePageUI.PRODUCT_BUTTON_ATC_END;
				break;
			}
		}
		waitElementClickable(driver, productLocatorFull);
		clickToElement(driver, productLocatorFull);
		
	}

}
