package com.liveguru.frontend;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import pageObjects.liveguru.CompareProductPageObject;
import pageObjects.liveguru.HomePageObject;
import pageObjects.liveguru.MobilePageObject;
import pageObjects.liveguru.MyDashboardPageObject;
import pageObjects.liveguru.MyWishlistPageObject;
import pageObjects.liveguru.ProductDetailPageObject;
import pageObjects.liveguru.ProductReviewPageObject;
import pageObjects.liveguru.RegisterPageObject;
import pageObjects.liveguru.ShoppingCartPageObject;
import pageObjects.liveguru.TVPageObject;
import pageObjects.liveguru.WishlistSharingPageObject;

public class User_01_Shopping {
	WebDriver driver;
	HomePageObject homePage;
	RegisterPageObject registerPage;
	MyDashboardPageObject myDashboardPage;
	MobilePageObject mobilePage;
	ProductDetailPageObject productDetailPage;
	ShoppingCartPageObject shoppingCartPage;
	CompareProductPageObject comparePage;
	MyWishlistPageObject myWishlistPage;
	TVPageObject tvPage;
	WishlistSharingPageObject wishlistSharingPage;
	ProductReviewPageObject productReviewPage;
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
		}else if(browser.equals("edge")) {
			System.setProperty("webdriver.edge.driver", ".\\browserDrivers\\msedgedriver.exe");
			driver = new EdgeDriver();
		}
//		System.setProperty("webdriver.edge.driver", ".\\browserDrivers\\msedgedriver.exe");
//		driver = new EdgeDriver();
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

	@Test//(enabled = false)
	public void Shopping_02_Verify_User_Infomation_Is_Correct() {
		homePage.clickToMyAccountButton();
		myDashboardPage = new MyDashboardPageObject(driver);
		String userNameAndEmail = myDashboardPage.getUserNameAndEmail();
		Assert.assertTrue(userNameAndEmail.contains(firstname));
		Assert.assertTrue(userNameAndEmail.contains(lastname));
		Assert.assertTrue(userNameAndEmail.contains(email));

	}

	@Test//(enabled = false)
	public void Shopping_03_Compare_Cost_Of_Product_With_Detail_Page() {
		homePage.clickToMobileMenu();
		mobilePage = new MobilePageObject(driver);
		String costOfProduct = mobilePage.getCostProduct("Sony Xperia");
		mobilePage.clickToProduct("Sony Xperia");
		productDetailPage = new ProductDetailPageObject(driver);
		Assert.assertEquals(costOfProduct, productDetailPage.getCost());
		
	}

	@Test//(enabled = false)
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
		Assert.assertEquals(shoppingCartPage.getMoneyGrandTotal(), "$500.00");
		
	}

	@Test//(enabled = false)
	public void Shopping_05_Test_User_Add_More_Than_500_Items() {
		homePage.clickToMobileMenu();
		mobilePage = new MobilePageObject(driver);
		mobilePage.clickAddToCartButton("Sony Xperia");
		shoppingCartPage = new ShoppingCartPageObject(driver);
		shoppingCartPage.inputQualtyProduct("501");
		shoppingCartPage.clickButtonUpdate();
		Assert.assertTrue(shoppingCartPage.getErrorItemMsg().contains("The maximum quantity allowed for purchase is 500."));
		Assert.assertEquals(shoppingCartPage.getErrorMsgAddTooManyQty(), "Some of the products cannot be ordered in requested quantity.");
		shoppingCartPage.clickEmtyCartLink();
		Assert.assertEquals(shoppingCartPage.getErrorMsgEmptyCart(), "You have no items in your shopping cart.");
	}

	@Test//(enabled = false)
	public void Shopping_06_Test_User_Can_Compare_Two_Products() {
		homePage.clickToMobileMenu();
		mobilePage = new MobilePageObject(driver);
		mobilePage.clickAddToCompareLink("IPhone");
		Assert.assertEquals(mobilePage.getSuccessMsg(), "The product IPhone has been added to comparison list.");
		mobilePage.clickAddToCompareLink("Sony Xperia");
		Assert.assertEquals(mobilePage.getSuccessMsg(), "The product Sony Xperia has been added to comparison list.");
		
		mobilePage.clickCompareButton();
		mobilePage.switchToWindowCompare();
		comparePage = new CompareProductPageObject(driver);
		Assert.assertEquals(comparePage.getTextPage(), "COMPARE PRODUCTS");
		Assert.assertEquals(comparePage.getTextProductLeft(),"SONY XPERIA" );
		Assert.assertEquals(comparePage.getTextProductRight(),"IPHONE" );
		comparePage.closeWindowCompare(mobilePage.getParentId());
}

	@Test//(enabled = false)
	public void Shopping_07_Check_User_Can_Share_Wistlist_To_Other_People_Using_Email() {
		homePage.clickToTVMenu();
		tvPage = new TVPageObject(driver);
		tvPage.clickAddToWishlist("LG LCD");
		myWishlistPage = new MyWishlistPageObject(driver);
		Assert.assertEquals(myWishlistPage.getAddtoWishlistSuccessMsg(), "LG LCD has been added to your wishlist. Click here to continue shopping.");
		myWishlistPage.clickToShareWishlistButton();
		wishlistSharingPage = new WishlistSharingPageObject(driver);
		wishlistSharingPage.inputEmail(email);
		wishlistSharingPage.inputMessage("Please share wishlist!");
		wishlistSharingPage.clickToShareWLButton();
		Assert.assertEquals(myWishlistPage.getAddtoWishlistSuccessMsg(), "Your Wishlist has been shared.");
	}

	@Test
	public void Shopping_08_Test_User_Can_Add_Review() {
		homePage.clickToTVMenu();
		tvPage = new TVPageObject(driver);
		tvPage.clickToProductDetail("Samsung LCD");
		productDetailPage = new ProductDetailPageObject(driver);
		productDetailPage.clickAddReviewLink();
		productReviewPage = new ProductReviewPageObject(driver);
		productReviewPage.inputReview("");
		productReviewPage.inputSummaryReview("");
		productReviewPage.inputName("");
		productReviewPage.clickToSumitReviewButton();
		Assert.assertEquals(productReviewPage.getReviewRequireMsg(), "THIS IS A REQUIRED FIELD.");
		Assert.assertEquals(productReviewPage.getSummaryReviewRequireMsg(), "THIS IS A REQUIRED FIELD.");
		Assert.assertEquals(productReviewPage.getNameRequireMsg(), "THIS IS A REQUIRED FIELD.");
		productReviewPage.inputReview("Thoughts!!");
		productReviewPage.inputSummaryReview("Good!");
		productReviewPage.inputName(firstname);
		productReviewPage.clickToSumitReviewButton();
		
		Assert.assertEquals(productReviewPage.getAddReviewMsgSuccess() , "Your review has been accepted for moderation.");
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
	public void sleepSeconds(long timeout) {
		try {
			Thread.sleep(timeout*1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
