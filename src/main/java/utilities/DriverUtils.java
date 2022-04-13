package utilities;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

/**
 * All the Browser initialization and Browser utilities
 */

public class DriverUtils {

	public static WebDriver driver;
	public static final String AUTOMATE_USERNAME = "ramyakesha_noH6uX";
	public static final String AUTOMATE_ACCESS_KEY = "jAWGwjmyn4AqUUohhUKD";
	public static final String URL = "https://" + AUTOMATE_USERNAME + ":" + AUTOMATE_ACCESS_KEY + "@hub-cloud.browserstack.com/wd/hub";

	public static WebDriver initDriver(String browserName) {
		DesiredCapabilities caps = new DesiredCapabilities();
		caps.setCapability("os_version", "Big Sur");
		caps.setCapability("resolution", "1920x1080");
		caps.setCapability("build", "BStack Build Number test");
		caps.setCapability("os", "OS X");
		if (browserName.equalsIgnoreCase("chrome")) {


			caps.setCapability("browser", "Chrome");
			caps.setCapability("browser_version", "99.0");
			caps.setCapability("name", "BStack-[Java] Sample Test Chrome"); // test name


		} else if (browserName.equalsIgnoreCase("firefox")) {

			caps.setCapability("browser", "Firefox");
			caps.setCapability("browser_version", "latest");
			caps.setCapability("name", "BStack-[Java] Sample Test Firefox"); // test name


		} else if (browserName.equalsIgnoreCase("safari")) {


			caps.setCapability("browser", "Safari");
			caps.setCapability("browser_version", "14.1");
			caps.setCapability("name", "BStack-[Java] Sample Test safari"); // test name
		}

		try {
			driver = new RemoteWebDriver(new URL(URL), caps);
		} catch (Exception e)
		{

		}
		return driver;
	}

	/**
	 * Get the title of the window in focus.
	 * 
	 * @param driver
	 * @return title as a text.
	 */
	public static String getTitle(WebDriver driver) {

		return driver.getTitle() != null ? driver.getTitle().trim() : "";
	}

	/**
	 * Refreshes the current browser tab.
	 */
	public static void refreshPage(WebDriver driver) {

		driver.navigate().refresh();
	}

	/**
	 * Open browser with maximized window.
	 * 
	 * @param driver
	 */
	public static void maximizeWindow(WebDriver driver) {

		driver.manage().window().maximize();
	}

	/**
	 * Implicitly wait in seconds.
	 * 
	 * @param driver
	 * @param seconds - integer value for specifying seconds.
	 */
	public static void waitImplicit(WebDriver driver, int seconds) {
		driver.manage().timeouts().implicitlyWait(seconds, TimeUnit.SECONDS);
	}

	/**
	 * Explicit wait in seconds.
	 *
	 * @param driver
	 */
	public static WebDriverWait waitExplicit(WebDriver driver) {

		WebDriverWait wait = new WebDriverWait(driver,60);
		return wait;
	}

	/**
	 * Dynamic pageload in seconds
	 *
	 * @param seconds - integer value for specifying seconds.
	 */
	public static void dynamicPageLoad(int seconds)
	{
		try{
			Thread.sleep(seconds*1000);
		} catch (Exception e) {
			Report.fail("Failed in thread.sleep");
		}
	}

	/**
	 * Navigate to the given URL.
	 * 
	 * @param driver
	 * @param url - URL as a string.
	 */
	public static void navigateToUrl(WebDriver driver, String url) {
		driver.navigate().to(url);
		maximizeWindow(driver);
	}

	/**
	 * This method switches to the window with given title if it is not the current
	 * window.
	 * 
	 * @param driver
	 */
	public static void switchToWindow(WebDriver driver, String windowName) {
		try {
			if (!driver.getTitle().equalsIgnoreCase(windowName)) {
				driver.switchTo().window(windowName);
			}
			new WebDriverWait(driver, Constants.WAIT_IN_SECONDS_5).until(ExpectedConditions.titleIs(windowName));
		} catch (NoSuchWindowException | TimeoutException e) {
			Report.fail("Window switch failed. Window with title " + windowName + " could not be found.");
		}
	}

	/**
	 * This method switches to the recently opened tab on current browser
	 * 
	 * @param driver
	 */
	public static void switchToRecentTab(WebDriver driver) {
		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		try {
			driver.switchTo().window(tabs.get(tabs.size() - 1));
			Report.pass("Switched to recent tab -" + driver.getTitle());
		} catch (NoSuchWindowException e) {
			Report.fail("Window switch failed. No window handles found");
		}
	}

	/*
	 * This method is used for explicit wait condition
	 *
	 * @param xpath
	 */
	public static void waitForElement(String elementXpath) {
		WebDriverWait wait = new WebDriverWait(driver,30);

		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(elementXpath)));
	}

	/**
	 * This method closes the recently opened tab on current browser
	 * 
	 * @param driver
	 */
	public static void closeRecentTab(WebDriver driver) {
		DriverUtils.switchToRecentTab(driver);
		driver.close();
		DriverUtils.switchToDefaultWindow(driver);
	}

	/**
	 * This method closes the recently opened tab on current browser
	 * 
	 * @param driver
	 */
	public static void closeWindow(WebDriver driver, String windowName) {
		if (driver.getTitle().equalsIgnoreCase(windowName))
			driver.close();
		DriverUtils.switchToDefaultWindow(driver);
	}

	/**
	 * Switch to the main window.
	 *
	 * @param driver
	 */
	public static void switchToDefaultWindow(WebDriver driver) {
		for (String windowHandle : driver.getWindowHandles()) {
			if (driver.switchTo().window(windowHandle).getTitle().equals("Default text")) {
				Report.pass("Switched to default window");
				break;
			}
		}
	}

	/**
	 * This method gets the current browser tab url
	 * 
	 * @param driver
	 * @return current browser tab url
	 */
	public static String getBrowserURL(WebDriver driver) {

		return driver.getCurrentUrl();
	}


	/**
	 * This method gets the current browser tab url
	 *
	 * @param driver
	 * @return current browser tab url
	 */
	public static void takeScreenShot(WebDriver driver,String fileName) {
		try {
			TakesScreenshot screenShot =((TakesScreenshot)driver);
			File SrcFile=screenShot.getScreenshotAs(OutputType.FILE);

			File DestFile=new File(Constants.SCREENSHOT_PATH+fileName);

			FileUtils.copyFile(SrcFile, DestFile);
		} catch (Exception e) {
			Report.fail("Unable to take screenshot due to "+e.getMessage());
		}


	}

}
