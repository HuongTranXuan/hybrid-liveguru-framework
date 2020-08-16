package commons;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public abstract class AbstractTest {
	public WebDriver driver;
	protected WebDriver getBrowserDriver(String browserName) {
		if (browserName.equals("firefox")) {
			System.setProperty("webdriver.gecko.driver", ".\\browserDrivers\\geckodriver.exe");
			driver = new FirefoxDriver();
		} else if (browserName.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver", ".\\browserDrivers\\chromedriver.exe");
			driver = new ChromeDriver();
		}else if(browserName.equals("edge")) {
			System.setProperty("webdriver.edge.driver", ".\\browserDrivers\\msedgedriver.exe");
			driver = new EdgeDriver();
		}else {
			throw new RuntimeException("Please input your browser's name");
		}
		return driver;
		
	}
}
