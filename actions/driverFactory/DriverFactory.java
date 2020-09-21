package driverFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import commons.Browser;
import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {
	public static DriverManager getManagerDriver(String browserName) {
		DriverManager driverManager = null;
		Browser browser = Browser.valueOf(browserName.toUpperCase());
		switch (browser) {
		case FIREFOX:
			driverManager = new FirefoxDriverManager();
			break;
		case CHROME:
			driverManager = new ChromeDriverManager();
			break;
		case EDGE:
			driverManager = new EdgeDriverManager();
			break;
		case COCCOC:
			driverManager = new CoccocDriverManager();
			break;
		case IE: //dung 64 bit de sendkey nhanh (32 bit sendkey rat lau)
			driverManager = new IEDriverManager();
			break;
		default:
			throw new RuntimeException("Please input your browser's name");
		}
		return driverManager;
	}
}
