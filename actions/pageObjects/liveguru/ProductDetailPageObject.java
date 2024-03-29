package pageObjects.liveguru;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;
import pageUIs.liveguru.HomePageUI;
import pageUIs.liveguru.ProductDetailPageUI;

public class ProductDetailPageObject extends AbstractPage{
	WebDriver driver;
	 
	public ProductDetailPageObject(WebDriver driver) {
		super();
		this.driver = driver;
	}

	public String getCost() {
		waitElementVisiable(driver, ProductDetailPageUI.COST);
		return getElementText(driver,ProductDetailPageUI.COST);
	}

	public void clickAddReviewLink() {
		waitElementClickable(driver, ProductDetailPageUI.ADD_YOUR_REVIEW_LINK);
		clickToElement(driver, ProductDetailPageUI.ADD_YOUR_REVIEW_LINK);
	}

}
