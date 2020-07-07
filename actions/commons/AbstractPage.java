package commons;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class AbstractPage {
	private WebDriverWait explicitWait;
	private JavascriptExecutor jsExecutor;
	private Alert alertCurrent;
	private Select select;
	private long longTimeout = 30;
	private long shortTimeout = 10;
	private List<WebElement> elements;
	private WebElement element;
	private Actions action;

	// WebBrowser
	public void setImplicitWait(WebDriver driver, long timeout) {
		driver.manage().timeouts().implicitlyWait(timeout, TimeUnit.SECONDS);
	}
	
	public void openPageUrl(WebDriver driver, String urlPage) {
		driver.get(urlPage);
	}

	public String getCurrentUrl(WebDriver driver) {
		return driver.getCurrentUrl();
	}

	public String getPageTitle(WebDriver driver) {
		return driver.getTitle();
	}

	public String getPageSourceCode(WebDriver driver) {
		return driver.getPageSource();
	}

	public void back(WebDriver driver) {
		driver.navigate().back();
	}

	public void forward(WebDriver driver) {
		driver.navigate().forward();
	}

	public void refesh(WebDriver driver) {
		driver.navigate().forward();
	}

	public void acceptAlert(WebDriver driver) {
		waitAlertPresent(driver);
		alertCurrent = driver.switchTo().alert();
		alertCurrent.accept();
	}

	public void cancelAlert(WebDriver driver) {
		waitAlertPresent(driver);
		alertCurrent = driver.switchTo().alert();
		alertCurrent.dismiss();
	}

	public void sendKeyToAlert(WebDriver driver, String value) {
		waitAlertPresent(driver);
		alertCurrent = driver.switchTo().alert();
		alertCurrent.sendKeys(value);
	}

	public String getAlertText(WebDriver driver) {
		waitAlertPresent(driver);
		alertCurrent = driver.switchTo().alert();
		return alertCurrent.getText();
	}

	
	public void switchToWindowByID(WebDriver driver, String parentID) {
		Set<String> allWindow = driver.getWindowHandles();
		for (String runWindow : allWindow) {
			if (!runWindow.equals(parentID)) {
				driver.switchTo().window(runWindow);
				break;
			}
		}
	}

	public void switchToWindowByTitle(WebDriver driver, String title) {
		Set<String> allWindow = driver.getWindowHandles();
		for (String runWindow : allWindow) {
			driver.switchTo().window(runWindow);
			String currentTab = driver.getTitle();
			if (currentTab.equals(title)) {
				break;
			}
		}
	}

	public boolean closeAllWindowsWithoutParent(WebDriver driver, String parentId) {
		Set<String> allWindow = driver.getWindowHandles();
		for (String runWindow : allWindow) {
			if (!runWindow.equals(parentId)) {
				driver.switchTo().window(runWindow);
				driver.close();
			}
		}
		driver.switchTo().window(parentId);
		if ((driver.getWindowHandles().size() == 1) && (driver.getWindowHandle().equals(parentId)))
			return true;
		else
			return false;

	}
	// end WebBrowser

	// WebElement
	public WebElement findElementByXpath(WebDriver driver, String locator) {
		return driver.findElement(byXpath(locator));
	}

	public List<WebElement> findElementsByXpath(WebDriver driver, String locator) {
		return driver.findElements(byXpath(locator));
	}

	public void clickToElement(WebDriver driver, String locator) {
		element = findElementByXpath(driver, locator);
		element.click();
	}

	public By byXpath(String locator) {
		return By.xpath(locator);
	}

	public void sendKeyToElement(WebDriver driver, String locator, String value) {
		element = findElementByXpath(driver, locator);
		element.clear();
		element.sendKeys(value);
	}

	public void selectItemInDropDown(WebDriver driver, String locator, String itemValue) {
		select = new Select(findElementByXpath(driver, locator));
		select.selectByVisibleText(itemValue);
	}

	public String getSelectedItemInDropDown(WebDriver driver, String locator) {
		select = new Select(findElementByXpath(driver, locator));
		return select.getFirstSelectedOption().getText();
	}

	public boolean isDropdownMutiple(WebDriver driver, String locator) {
		select = new Select(findElementByXpath(driver, locator));
		return select.isMultiple();
	}

	public void selectItemInCustomDropdown(WebDriver driver, String parentPath, String childPath,
			String exspectedText) {
		explicitWait = new WebDriverWait(driver, 15);
		jsExecutor = (JavascriptExecutor) driver;
		findElementByXpath(driver, parentPath).click();
		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(byXpath(childPath)));
		elements = findElementsByXpath(driver, childPath);
		for (WebElement webElement : elements) {
			if (webElement.getText().equals(exspectedText)) {
				if (!webElement.isDisplayed()) {
					jsExecutor.executeScript("arguments[0].scrollIntoView(true)", webElement);
					sleepSeconds(2);
				}
				webElement.click();
				sleepSeconds(2);
				break;
			}
		}
	}

	public void selectMutipleItemInCustomDropdown(WebDriver driver, String parentPath, String childPath,
			String[] mutipleText) {
		explicitWait = new WebDriverWait(driver, longTimeout);
		jsExecutor = (JavascriptExecutor) driver;
		findElementByXpath(driver, parentPath).click();
		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(childPath)));
		List<WebElement> allChildElement = driver.findElements(By.xpath(childPath));
		List<WebElement> itemSelected = new ArrayList<WebElement>();
		for (WebElement webElement : allChildElement) {
			for (String textItemExspected : mutipleText) {
				if (textItemExspected.equals(webElement.getText())) {
					if (!webElement.isDisplayed()) {
						jsExecutor.executeScript("arguments[0].scrollIntoView(true);", webElement);
						sleepSeconds(2);
					}
					webElement.click();
					itemSelected.add(webElement);
				}
			}
			if (itemSelected.size() == mutipleText.length)
				break;
		}
	}

	public String getAttributeValue(WebDriver driver, String locator, String attributeName) {
		return findElementByXpath(driver, locator).getAttribute(attributeName);
	}

	public String getElementText(WebDriver driver, String locator) {
		return findElementByXpath(driver, locator).getText();
	}

	public int countElementNumber(WebDriver driver, String locator) {
		return findElementsByXpath(driver, locator).size();
	}

	public void checkToTheCheckbox(WebDriver driver, String locator) {
		element = findElementByXpath(driver, locator);
		if (!element.isSelected()) {
			element.click();
		}
	}

	public void unCheckToTheCheckbox(WebDriver driver, String locator) {
		element = findElementByXpath(driver, locator);
		if (element.isSelected()) {
			element.click();
		}
	}

	public boolean isElementDisplayed(WebDriver driver, String locator) {
		return findElementByXpath(driver, locator).isDisplayed();
	}

	public boolean isElementSelected(WebDriver driver, String locator) {
		return findElementByXpath(driver, locator).isSelected();
	}

	public boolean isElementEnabled(WebDriver driver, String locator) {
		return findElementByXpath(driver, locator).isEnabled();
	}

	public void switchToFrame(WebDriver driver, String locator) {
		driver.switchTo().frame(findElementByXpath(driver, locator));
	}

	public void switchToDefaultContent(WebDriver driver) {
		driver.switchTo().defaultContent();
	}

	public void hoverToElement(WebDriver driver, String locator) {
		action = new Actions(driver);
		action.moveToElement(findElementByXpath(driver, locator)).perform();
	}

	public void doubleClickElement(WebDriver driver, String locator) {
		action = new Actions(driver);
		action.doubleClick(findElementByXpath(driver, locator)).perform();
	}

	public void rightClickElement(WebDriver driver, String locator) {
		action = new Actions(driver);
		action.contextClick(findElementByXpath(driver, locator)).perform();
	}

	public void dragAndDrop(WebDriver driver, String locatorElementSource, String locatorElementTarget) {
		action = new Actions(driver);
		action.dragAndDrop(findElementByXpath(driver, locatorElementSource),
				findElementByXpath(driver, locatorElementTarget)).perform();
	}

	public void sendKeybroadToElement(WebDriver driver, String locator, Keys key) {
		action = new Actions(driver);
		action.sendKeys(findElementByXpath(driver, locator), key);
	}
	// End WebElement

	// jsExecutor

	public void highlightElement(WebDriver driver, String locator) {
		jsExecutor = (JavascriptExecutor) driver;
		element = findElementByXpath(driver, locator);
		String originalStyle = element.getAttribute("style");
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1],arguments[2])", element, "style",
				"border: 5px solid red; border-style: dashed;");
		sleepSeconds(2);
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1],arguments[2])", element, "style",
				originalStyle);
	}

	public void clickToElementByJS(WebDriver driver, String locator) {
		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].click();", findElementByXpath(driver, locator));
	}

	public void scrollToElement(WebDriver driver, String locator) {
		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", findElementByXpath(driver, locator));
	}

	public void sendKeyToElementByJS(WebDriver driver, String locator, String value) {
		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].setAttribute('value', '" + value + "')", findElementByXpath(driver, locator));
	}

	public void removeAttributeInDOM(WebDriver driver,String locator, String attributeRemove) {
		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');",  findElementByXpath(driver, locator));
	}

	public boolean isImageLoaded(WebDriver driver,String locator) {
		jsExecutor = (JavascriptExecutor) driver;
		boolean status = (boolean) jsExecutor.executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0 ",findElementByXpath(driver, locator));
		return status;
	}
	
	// End jsExecutor
	
	public void waitElementVisiable(WebDriver driver,String locator) {
		explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(byXpath(locator)));
	}
	public void waitElementInVisiable(WebDriver driver,String locator) {
		explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(byXpath(locator)));
	}
	public void waitAlertPresent(WebDriver driver) {
		explicitWait = new WebDriverWait(driver, 15);
		explicitWait.until(ExpectedConditions.alertIsPresent());
	}

	public void waitElementPresence(WebDriver driver,String locator) {
		explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.presenceOfElementLocated(byXpath(locator)));
	}
	public void waitElementClickable(WebDriver driver,String locator) {
		explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.elementToBeClickable(byXpath(locator)));
	}
	
	public void sleepSeconds(long timeout) {
		try {
			Thread.sleep(timeout * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
