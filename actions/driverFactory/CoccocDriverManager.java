package driverFactory;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CoccocDriverManager extends DriverManager {

	@Override
	protected void createDriver() {
		// TODO Auto-generated method stub
		WebDriverManager.chromedriver().driverVersion("83.0.4103.39").setup();
		ChromeOptions option = new ChromeOptions();
		option.setBinary("C:\\Users\\User\\AppData\\Local\\CocCoc\\Browser\\Application\\browser.exe");
		driver = new ChromeDriver(option);
	}

}
