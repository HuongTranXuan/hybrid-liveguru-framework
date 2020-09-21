package driverFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;



public class FirefoxDriverManager extends DriverManager{

	@Override
	protected void createDriver() {
		// TODO Auto-generated method stub
		WebDriverManager.firefoxdriver().setup();
		driver = new FirefoxDriver();
	}

}
