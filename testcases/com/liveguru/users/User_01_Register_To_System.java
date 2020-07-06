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

public class User_01_Register_To_System {

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

		driver.get("http://live.demoguru99.com/");
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	}

	@BeforeMethod
	public void beforeMethod() {
		driver.findElement(By.xpath("//div[@class='footer']//a[contains(text(),'My Account')]")).click();;
		driver.findElement(By.xpath("//div[@class='footer']//a[contains(text(),'My Account')]")).click();

		driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
	}

	@Test
	public void Register_01_Empty_Data() {
		driver.findElement(By.xpath("//button[@title='Register']")).click();

		// verify error message
		Assert.assertEquals(driver.findElement(By.xpath("//div[@id='advice-required-entry-firstname']")).getText(),
				"This is a required field.");
		Assert.assertEquals(driver.findElement(By.xpath("//div[@id='advice-required-entry-lastname']")).getText(),
				"This is a required field.");
		Assert.assertEquals(driver.findElement(By.xpath("//div[@id='advice-required-entry-email_address']")).getText(),
				"This is a required field.");
		Assert.assertEquals(driver.findElement(By.xpath("//div[@id='advice-required-entry-password']")).getText(),
				"This is a required field.");
		Assert.assertEquals(driver.findElement(By.xpath("//div[@id='advice-required-entry-confirmation']")).getText(),
				"This is a required field.");

	}

	@Test
	public void Register_02_Invalid_Email() {
		driver.findElement(By.xpath("//input[@id='email_address']")).sendKeys("email012.123@123");
		driver.findElement(By.xpath("//button[@title='Register']")).click();

		Assert.assertEquals(driver.findElement(By.xpath("//div[@id='advice-validate-email-email_address']")).getText(),
				"Please enter a valid email address. For example johndoe@domain.com.");
	}

	@Test
	public void Register_03_Password_Less_Than_6_Character() {
		driver.findElement(By.xpath("//input[@id='password']")).sendKeys("123");
		driver.findElement(By.xpath("//button[@title='Register']")).click();

		Assert.assertEquals(driver.findElement(By.xpath("//div[@id='advice-validate-password-password']")).getText(),
				"Please enter 6 or more characters without leading or trailing spaces.");
	}

	@Test
	public void Register_04_Confirm_Password_Not_Matching_With_Password() {
		driver.findElement(By.xpath("//input[@id='password']")).sendKeys("123123");
		driver.findElement(By.xpath("//input[@id='confirmation']")).sendKeys("321321");
		driver.findElement(By.xpath("//button[@title='Register']")).click();

		Assert.assertEquals(
				driver.findElement(By.xpath("//div[@id='advice-validate-cpassword-confirmation']")).getText(),
				"Please make sure your passwords match.");
	}

	@Test
	public void Register_05_Valid_Data() {
		driver.findElement(By.xpath("//input[@id='firstname']")).sendKeys("Nam");
		driver.findElement(By.xpath("//input[@id='lastname']")).sendKeys("Dang");
		driver.findElement(By.xpath("//input[@id='email_address']"))
				.sendKeys("nam.dang" + randomNumber() + "@gmail.com");
		driver.findElement(By.xpath("//input[@id='password']")).sendKeys("123123");
		driver.findElement(By.xpath("//input[@id='confirmation']")).sendKeys("123123");
		driver.findElement(By.xpath("//button[@title='Register']")).click();

		Assert.assertEquals(driver
				.findElement(By.xpath("//span[contains(text(),'Thank you for registering with Main Website Store.')]"))
				.getText(), "Thank you for registering with Main Website Store.");
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
