package pageObjects.liveguru;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;
import pageUIs.liveguru.ProductReviewPageUI;

public class ProductReviewPageObject extends AbstractPage{
	WebDriver driver;

	public ProductReviewPageObject(WebDriver driver) {
		super();
		this.driver = driver;
	}


	public void clickToSumitReviewButton() {
		waitElementClickable(driver, ProductReviewPageUI.SUBMIT_BUTTON);
		clickToElement(driver, ProductReviewPageUI.SUBMIT_BUTTON);
	}

	public void inputSummaryReview(String summaryReview) {
		waitElementVisiable(driver, ProductReviewPageUI.SUMMARY_REVIEW_TEXTBOX);
		sendKeyToElement(driver, ProductReviewPageUI.SUMMARY_REVIEW_TEXTBOX, summaryReview);
	}

	public void inputReview(String review) {
		waitElementVisiable(driver, ProductReviewPageUI.REVIEW_TEXTBOX);
		sendKeyToElement(driver, ProductReviewPageUI.REVIEW_TEXTBOX, review);
		
	}

	public void inputName(String nickname) {
		waitElementVisiable(driver, ProductReviewPageUI.NICKNAME_TEXTBOX);
		sendKeyToElement(driver, ProductReviewPageUI.NICKNAME_TEXTBOX, nickname);
		
	}


	public String getAddReviewMsgSuccess() {
		waitElementVisiable(driver, ProductReviewPageUI.ADD_REVIEW_MSG_SUCCESS);
		return getElementText(driver, ProductReviewPageUI.ADD_REVIEW_MSG_SUCCESS);
	}

	
	public String getSummaryReviewRequireMsg() {
		
		waitElementVisiable(driver, ProductReviewPageUI.SUMMARY_REQUIRE_MSG_ERROR);
		return getElementText(driver, ProductReviewPageUI.SUMMARY_REQUIRE_MSG_ERROR);
	}

	public String getReviewRequireMsg() {
		waitElementVisiable(driver, ProductReviewPageUI.REVIEW_REQUIRE_MSG_ERROR);
		return getElementText(driver, ProductReviewPageUI.REVIEW_REQUIRE_MSG_ERROR);
	}

	public String getNameRequireMsg() {
		waitElementVisiable(driver, ProductReviewPageUI.NICKNAME_REQUIRE_MSG_ERROR);
		return getElementText(driver, ProductReviewPageUI.NICKNAME_REQUIRE_MSG_ERROR);
	}
	
}
