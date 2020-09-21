package driverFactory;

import org.openqa.selenium.ie.InternetExplorerDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class IEDriverManager extends DriverManager{

	@Override
	protected void createDriver() {
		// TODO Auto-generated method stub
		WebDriverManager.iedriver().arch32().setup();
		driver = new InternetExplorerDriver();
	}

}
