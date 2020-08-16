package com.liveguru.users;


import org.testng.annotations.Test;

import commons.AbstractPage;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Level_02_Common_Function_Register_01 extends AbstractPage {

	WebDriver driver;

	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browser) {
		if (browser.equals("firefox")) {
			System.setProperty("webdriver.gecko.driver", ".\\browserDrivers\\geckodriver.exe");
			driver = new FirefoxDriver();
		} else if (browser.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver", ".\\browserDrivers\\chromedriver.exe");
			driver = new ChromeDriver();
		}

		openPageUrl(driver, "http://live.demoguru99.com/");
		setImplicitWait(driver, 20);
	}

	@BeforeMethod
	public void beforeMethod() {
		clickToElement(driver, "//div[@class='footer']//a[contains(text(),'My Account')]");
		clickToElement(driver, "//a[@title='Create an Account']");
	}

	@Test
	public void Register_01_Empty_Data() {
		clickToElement(driver, "//button[@title='Register']");

		// verify error message
		Assert.assertEquals(getElementText(driver, "//div[@id='advice-required-entry-firstname']"),
				"This is a required field.");
		Assert.assertEquals(getElementText(driver, "//div[@id='advice-required-entry-lastname']"),
				"This is a required field.");
		Assert.assertEquals(getElementText(driver, "//div[@id='advice-required-entry-email_address']"),
				"This is a required field.");
		Assert.assertEquals(getElementText(driver, "//div[@id='advice-required-entry-password']"),
				"This is a required field.");
		Assert.assertEquals(getElementText(driver, "//div[@id='advice-required-entry-confirmation']"),
				"This is a required field.");

	}

	@Test
	public void Register_02_Invalid_Email() {
		sendKeyToElement(driver, "//input[@id='email_address']", "email012.123@123");
		clickToElement(driver, "//button[@title='Register']");
		
		Assert.assertEquals(getElementText(driver, "//div[@id='advice-validate-email-email_address']"),
				"Please enter a valid email address. For example johndoe@domain.com.");
	}

	@Test
	public void Register_03_Password_Less_Than_6_Character() {
		sendKeyToElement(driver, "//input[@id='password']", "123");
		clickToElement(driver, "//button[@title='Register']");

		Assert.assertEquals(getElementText(driver,"//div[@id='advice-validate-password-password']"),
				"Please enter 6 or more characters without leading or trailing spaces.");
	}

	@Test
	public void Register_04_Confirm_Password_Not_Matching_With_Password() {
		sendKeyToElement(driver,"//input[@id='password']","123123");
		sendKeyToElement(driver,"//input[@id='confirmation']","321321");
		clickToElement(driver, "//button[@title='Register']");

		Assert.assertEquals(getElementText(driver,"//div[@id='advice-validate-cpassword-confirmation']"),
				"Please make sure your passwords match.");
	}

	@Test
	public void Register_05_Valid_Data() {
		sendKeyToElement(driver,"//input[@id='firstname']","Nam");
		sendKeyToElement(driver,"//input[@id='lastname']","Dang");
		sendKeyToElement(driver,"//input[@id='email_address']","nam.dang" + randomNumber() + "@gmail.com");
		sendKeyToElement(driver,"//input[@id='password']","123123");
		sendKeyToElement(driver,"//input[@id='confirmation']","123123");
		clickToElement(driver, "//button[@title='Register']");

		Assert.assertEquals(getElementText(driver,"//span[contains(text(),'Thank you for registering with Main Website Store.')]"), "Thank you for registering with Main Website Store.");
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	public void sleepSeconds(long timeSecond) {
		try {
			Thread.sleep(timeSecond * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public int randomNumber() {
		Random random = new Random();
		return random.nextInt(1000) + 1000;
	}
}
