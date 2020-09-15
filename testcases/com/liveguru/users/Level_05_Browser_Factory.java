package com.liveguru.users;

import org.testng.annotations.Test;

import commons.AbstractPage;
import commons.AbstractTest;
import pageObjects.liveguru.HomePageObject;
import pageObjects.liveguru.LoginPageObject;
import pageObjects.liveguru.MyDashboardPageObject;
import pageObjects.liveguru.RegisterPageObject;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Level_05_Browser_Factory extends AbstractTest{
	WebDriver driver;
	HomePageObject homePage;
	LoginPageObject loginPage;
	RegisterPageObject registerPage;
	MyDashboardPageObject myDashboardPage;

	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browser) {
		driver = getBrowserDriver(browser);
		driver.get("http://live.demoguru99.com/");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		homePage = new HomePageObject(driver);
	}

	@BeforeMethod
	public void beforeMethod() {
		homePage.clickToMyAccountButton();
		loginPage = new LoginPageObject(driver);
		loginPage.clickToCreateAnAccountButton();
		registerPage = new RegisterPageObject(driver);
	}

	@Test
	public void Register_01_Empty_Data() {
		
		registerPage.clickToRegisterButton("//button[@title='Register']");

		Assert.assertEquals(registerPage.getRequiredErrorMessageAtFirstnameTextbox(),"This is a required field.");
		Assert.assertEquals(registerPage.getRequiredErrorMessageAtLastnameTextbox(),"This is a required field.");
		Assert.assertEquals(registerPage.getRequiredErrorMessageAtEmailTextbox(),"This is a required field.");
		Assert.assertEquals(registerPage.getRequiredErrorMessageAtPasswordTextbox(),"This is a required field.");
		Assert.assertEquals(registerPage.getRequiredErrorMessageAtConfirmPasswordTextbox(),"This is a required field.");

	}

	@Test
	public void Register_02_Invalid_Email() {
		registerPage.inputToEmailTextbox("email012.123@123");
		registerPage.clickToRegisterButton("//button[@title='Register']");
		Assert.assertEquals(registerPage.getInvalidErrorMessageAtEmailTextbox(),"Please enter a valid email address. For example johndoe@domain.com.");
	}

	@Test
	public void Register_03_Password_Less_Than_6_Character() {
		registerPage.inputToPasswordTextbox("123");
		registerPage.clickToRegisterButton("//button[@title='Register']");
		Assert.assertEquals(registerPage.getInvalidErrorMessagePasswordTextbox(),"Please enter 6 or more characters without leading or trailing spaces.");
	}

	@Test
	public void Register_04_Confirm_Password_Not_Matching_With_Password() {
		registerPage.inputToPasswordTextbox("123123");
		registerPage.inputToConfirmPasswordTextbox("321321");
		registerPage.clickToRegisterButton("//button[@title='Register']");
		Assert.assertEquals(registerPage.getInvalidErrorMessageConfirmPasswordTextbox(),"Please make sure your passwords match.");
	}

	@Test
	public void Register_05_Valid_Data() {
		registerPage.inputToFirstnameTextbox("Nam");
		registerPage.inputToLastnameTextbox("Dang");
		registerPage.inputToEmailTextbox("nam.dang" + randomNumber() + "@gmail.com");
		registerPage.inputToPasswordTextbox("123123");
		registerPage.inputToConfirmPasswordTextbox("123123");
		registerPage.clickToRegisterButton("//button[@title='Register']");
		myDashboardPage = new MyDashboardPageObject(driver);
		Assert.assertEquals(myDashboardPage.getWelcomeSuccessMessage(),"Thank you for registering with Main Website Store.");
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
