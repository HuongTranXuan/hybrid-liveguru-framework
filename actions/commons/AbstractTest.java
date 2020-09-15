package commons;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public abstract class AbstractTest {
	public WebDriver driver;
	protected WebDriver getBrowserDriver(String browserName) {
		Browser browser = Browser.valueOf(browserName.toUpperCase());
		switch (browser) {
		case FIREFOX:
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			break;
		case CHROME:
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			break;
		case EDGE:
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
			break;
		case COCCOC:
			WebDriverManager.chromedriver().driverVersion("83.0.4103.39").setup();
			ChromeOptions option = new ChromeOptions();
			option.setBinary("C:\\Users\\User\\AppData\\Local\\CocCoc\\Browser\\Application\\browser.exe");
			driver = new ChromeDriver(option);
			break;
		case IE: //dung 64 bit de sendkey nhanh (32 bit sendkey rat lau)
			WebDriverManager.iedriver().arch32().setup();
			driver = new InternetExplorerDriver();
			break;
		default:
			throw new RuntimeException("Please input your browser's name");
		}
		driver.get("http://live.demoguru99.com/");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		return driver;
		
	}
	
	
	protected WebDriver getBrowserDriver(String browserName, String appURL) {
		Browser browser = Browser.valueOf(browserName.toUpperCase());
		switch (browser) {
		case FIREFOX:
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			break;
		case CHROME:
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			break;
		case EDGE:
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
			break;
		case COCCOC:
			WebDriverManager.chromedriver().driverVersion("83.0.4103.39").setup();
			ChromeOptions option = new ChromeOptions();
			option.setBinary("C:\\Users\\User\\AppData\\Local\\CocCoc\\Browser\\Application\\browser.exe");
			driver = new ChromeDriver(option);
			break;
		case IE: //dung 64 bit de sendkey nhanh (32 bit sendkey rat lau)
			WebDriverManager.iedriver().arch32().setup();
			driver = new InternetExplorerDriver();
			break;
		default:
			throw new RuntimeException("Please input your browser's name");
		}

		driver.get(appURL);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		return driver;
		
	}
}
