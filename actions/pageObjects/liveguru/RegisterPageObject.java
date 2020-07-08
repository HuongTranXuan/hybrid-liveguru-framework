package pageObjects.liveguru;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;
import pageUIs.liveguru.RegisterPageUI;

public class RegisterPageObject extends AbstractPage {
	WebDriver driver;

	public RegisterPageObject(WebDriver driver) {
		super();
		this.driver = driver;
	}

	public void clickToRegisterButton(String string) {
		waitElementClickable(driver, RegisterPageUI.REGISTER_BUTTON);
		clickToElement(driver, RegisterPageUI.REGISTER_BUTTON);
	}

	public String getRequiredErrorMessageAtFirstnameTextbox() {
		waitElementVisiable(driver, RegisterPageUI.REQUIRE_ERROR_MSG_FIRSTNAME_TEXTBOX);
		return getElementText(driver, RegisterPageUI.REQUIRE_ERROR_MSG_FIRSTNAME_TEXTBOX);
	}

	public String getRequiredErrorMessageAtLastnameTextbox() {
		waitElementVisiable(driver, RegisterPageUI.REQUIRE_ERROR_MSG_LASTNAME_TEXTBOX);
		return getElementText(driver, RegisterPageUI.REQUIRE_ERROR_MSG_LASTNAME_TEXTBOX);
	}

	public String getRequiredErrorMessageAtEmailTextbox() {
		waitElementVisiable(driver, RegisterPageUI.REQUIRE_ERROR_MSG_EMAIL_TEXTBOX);
		return getElementText(driver, RegisterPageUI.REQUIRE_ERROR_MSG_EMAIL_TEXTBOX);
	}

	public String getRequiredErrorMessageAtPasswordTextbox() {
		waitElementVisiable(driver, RegisterPageUI.REQUIRE_ERROR_MSG_PASSWORD_TEXTBOX);
		return getElementText(driver, RegisterPageUI.REQUIRE_ERROR_MSG_PASSWORD_TEXTBOX);
	}

	public String getRequiredErrorMessageAtConfirmPasswordTextbox() {
		waitElementVisiable(driver, RegisterPageUI.REQUIRE_ERROR_MSG_CONFIRM_PASSWORD_TEXTBOX);
		return getElementText(driver, RegisterPageUI.REQUIRE_ERROR_MSG_CONFIRM_PASSWORD_TEXTBOX);
	}

	public String getInvalidErrorMessageAtEmailTextbox() {
		waitElementVisiable(driver, RegisterPageUI.VALIDATE_ERROR_MSG_EMAIL);
		return getElementText(driver, RegisterPageUI.VALIDATE_ERROR_MSG_EMAIL);
	}

	public String getInvalidErrorMessagePasswordTextbox() {
		waitElementVisiable(driver, RegisterPageUI.VALIDATE_ERROR_MSG_PASSWORD);
		return getElementText(driver, RegisterPageUI.VALIDATE_ERROR_MSG_PASSWORD);
	}

	public String getInvalidErrorMessageConfirmPasswordTextbox() {
		waitElementVisiable(driver, RegisterPageUI.VALIDATE_ERROR_MSG_CONFIRM_PASSWORD);
		return getElementText(driver, RegisterPageUI.VALIDATE_ERROR_MSG_CONFIRM_PASSWORD);
	}

	public void inputToEmailTextbox(String emailAddress) {
		waitElementVisiable(driver, RegisterPageUI.EMAIL_TEXTBOX);
		sendKeyToElement(driver, RegisterPageUI.EMAIL_TEXTBOX, emailAddress);
	}

	public void inputToPasswordTextbox(String password) {
		waitElementVisiable(driver, RegisterPageUI.PASSWORD_TEXTBOX);
		sendKeyToElement(driver, RegisterPageUI.PASSWORD_TEXTBOX, password);

	}

	public void inputToConfirmPasswordTextbox(String confirmPass) {
		waitElementVisiable(driver, RegisterPageUI.CONFIRM_PASSWORD_TEXTBOX);
		sendKeyToElement(driver, RegisterPageUI.CONFIRM_PASSWORD_TEXTBOX, confirmPass);
	}

	public void inputToFirstnameTextbox(String firstname) {
		waitElementVisiable(driver, RegisterPageUI.FIRSTNAME_TEXTBOX);
		sendKeyToElement(driver, RegisterPageUI.FIRSTNAME_TEXTBOX, firstname);
	}

	public void inputToLastnameTextbox(String lastname) {
		waitElementVisiable(driver, RegisterPageUI.LASTNAME_TEXTBOX);
		sendKeyToElement(driver, RegisterPageUI.LASTNAME_TEXTBOX, lastname);
	}

}
