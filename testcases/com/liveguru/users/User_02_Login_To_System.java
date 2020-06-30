package com.liveguru.users;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class User_02_Login_To_System {

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

		System.out.println("browser: " + browser);
		// Open App Url
		driver.get("http://live.demoguru99.com/");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@BeforeMethod
	public void beforeMethod() {
		driver.findElement(By.xpath("//div[@class='footer']//a[contains(text(),'My Account')]")).click();

	}

	@Test
	public void Login_01_Empty_Email_And_Password() {
		driver.findElement(By.xpath("//button[@title='Login']")).click();

		Assert.assertEquals(driver.findElement(By.xpath("//div[@id='advice-required-entry-email']")).getText(),
				"This is a required field.");
		Assert.assertEquals(driver.findElement(By.xpath("//div[@id='advice-required-entry-pass']")).getText(),
				"This is a required field.");
	}

	@Test
	public void Login_02_Invaid_Email() {
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("hfkfh@123");
		driver.findElement(By.xpath("//button[@title='Login']")).click();

		Assert.assertEquals(driver.findElement(By.xpath("//div[@id='advice-validate-email-email']")).getText(),
				"Please enter a valid email address. For example johndoe@domain.com.");
	}

	@Test
	public void Login_03_Email_Not_Existing() {
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("hue.nguyenthi" + randomInteger() + "@gmail.com");
		driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("123123");
		driver.findElement(By.xpath("//button[@title='Login']")).click();

		Assert.assertEquals(
				driver.findElement(By.xpath("//span[contains(text(),'Invalid login or password.')]")).getText(),
				"Invalid login or password.");
	}

	@Test
	public void Login_04_Password_Less_Than_6_Character() {
		driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("123");
		driver.findElement(By.xpath("//button[@title='Login']")).click();

		Assert.assertEquals(driver.findElement(By.xpath("//div[@id='advice-validate-password-pass']")).getText(),
				"Please enter 6 or more characters without leading or trailing spaces.");
	}

	@Test
	public void Login_05_Password_Incorrect() {
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("hue.nguyenthi" + randomInteger() + "@gmail.com");
		driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("123123");
		driver.findElement(By.xpath("//button[@title='Login']")).click();

		Assert.assertEquals(
				driver.findElement(By.xpath("//span[contains(text(),'Invalid login or password.')]")).getText(),
				"Invalid login or password.");
	}

	@Test
	public void Login_06_Login_Successfull() {
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("automationfc.vn@gmail.com");
		driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("123123");
		driver.findElement(By.xpath("//button[@title='Login']")).click();

		Assert.assertEquals(
				driver.findElement(By.xpath("//strong[contains(text(),'Hello, Automation FC!')]")).getText(),
				"Hello, Automation FC!");
		Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(),'Automation FC')]")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[contains(.,'automationfc.vn@gmail.com')]")).isDisplayed());

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	public int randomInteger() {
		Random random = new Random();
		return random.nextInt(1000) + 1000;
	}
}
