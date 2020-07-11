package com.liveguru.frontend;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import pageObjects.liveguru.HomePageObject;
import pageObjects.liveguru.MobilePageObject;
import pageObjects.liveguru.MyDashboardPageObject;
import pageObjects.liveguru.ProductDetailPageObject;
import pageObjects.liveguru.RegisterPageObject;
import pageObjects.liveguru.ShoppingCartPageObject;

public class User_01_Shopping {
	WebDriver driver;
	HomePageObject homePage;
	RegisterPageObject registerPage;
	MyDashboardPageObject myDashboardPage;
	MobilePageObject mobilePage;
	ProductDetailPageObject productDetailPage;
	ShoppingCartPageObject shoppingCartPage;
	String firstname = "", lastname = "", email = "";

	@Parameters("browser")
	@BeforeClass
	public void beforClass(String browser) {
		if (browser.equals("firefox")) {
			System.setProperty("webdriver.gecko.driver", ".\\browserDrivers\\geckodriver.exe");
			driver = new FirefoxDriver();
		} else if (browser.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver", ".\\browserDrivers\\chromedriver.exe");
			driver = new ChromeDriver();
		}
		firstname = "Hoai";
		lastname = "Dang";
		email = "hoai.dang" + randomNumber() + "@gmail.com";
	}

	@BeforeMethod
	public void beforeMethod() {
		driver.get("http://live.demoguru99.com/");
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		homePage = new HomePageObject(driver);
		//driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test
	public void Shopping_01_Register_Success_To_System() {
		homePage.clickToAccountLink();
		homePage.clickToRegisterLink();
		registerPage = new RegisterPageObject(driver);

		registerPage.inputToFirstnameTextbox(firstname);
		registerPage.inputToLastnameTextbox(lastname);
		registerPage.inputToEmailTextbox(email);
		registerPage.inputToPasswordTextbox("123123");
		registerPage.inputToConfirmPasswordTextbox("123123");
		registerPage.clickToRegisterButton("//button[@title='Register']");
		myDashboardPage = new MyDashboardPageObject(driver);
		Assert.assertEquals(myDashboardPage.getWelcomeSuccessMessage(),
				"Thank you for registering with Main Website Store.");

	}

	@Test
	public void Shopping_02_Verify_User_Infomation_Is_Correct() {
		homePage.clickToMyAccountButton();
		myDashboardPage = new MyDashboardPageObject(driver);
		String userNameAndEmail = myDashboardPage.getUserNameAndEmail();
		Assert.assertTrue(userNameAndEmail.contains(firstname));
		Assert.assertTrue(userNameAndEmail.contains(lastname));
		Assert.assertTrue(userNameAndEmail.contains(email));

	}

	@Test
	public void Shopping_03_Compare_Cost_Of_Product_With_Detail_Page() {
		homePage.clickToMobileMenu();
		mobilePage = new MobilePageObject(driver);
		String costOfProduct = mobilePage.getCostProduct("Sony Xperia");
		mobilePage.clickToProduct("Sony Xperia");
		System.out.println("Huong 2");
		productDetailPage = new ProductDetailPageObject(driver);
		Assert.assertEquals(costOfProduct, productDetailPage.getCost());
		
	}

	@Test
	public void Shopping_04_Check_Discount_Coupon_Work() {
		homePage.clickToMobileMenu();
		mobilePage = new MobilePageObject(driver);
		mobilePage.clickAddToCartButton("IPhone");
		shoppingCartPage = new ShoppingCartPageObject(driver);
		Assert.assertEquals(shoppingCartPage.getSuccessMSG(), "IPhone was added to your shopping cart.");
		shoppingCartPage.inputCouponCode("GURU50");
		shoppingCartPage.clickToApplyLink();
		Assert.assertEquals(shoppingCartPage.getSuccessMSG(), "Coupon code \"GURU50\" was applied.");
		Assert.assertEquals(shoppingCartPage.getCouponCodeInTotal(), "DISCOUNT (GURU50)");
		Assert.assertEquals(shoppingCartPage.getDiscountMoneyInTotal(), "-$25.00");
		
	}

	@Test
	public void Shopping_05_Test_User_Add_More_Than_500_Items() {

	}

	@Test
	public void Shopping_06_Test_User_Can_Compare_Two_Products() {

	}

	@Test
	public void Shopping_07_Check_User_Can_Share_Wistlist_To_Other_People_Using_Email() {

	}

	@Test
	public void Shopping_08_Test_User_Can_Add_Review() {

	}

	@Test
	public void Shopping_09_Test_User_Purchase_Product() {

	}

	@Test
	public void Shopping_10_Test_Search_Functionality() {

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	public int randomNumber() {
		Random random = new Random();
		return random.nextInt(1000) + 1000;
	}
}
