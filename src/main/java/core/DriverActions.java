package core;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import utilities.Constants;
import utilities.DriverUtils;
import utilities.Report;

import java.util.ArrayList;
import java.util.List;

public class DriverActions {
	protected WebDriver driver;

	/**
	 * Initialize base class constructor with driver.
	 * 
	 * @param driver
	 */
	public DriverActions(WebDriver driver) {
		this.driver = driver;
	}

	/**
	 * Search Web element based on the type, and return the web element if present
	 * else return null if element is not present.
	 * 
	 * @param locator - xpath, id, name, link text, class name
	 * @return web element if present else null if element is not present.
	 */

	protected WebElement getWebElement(String locator) {

		List<WebElement> elements = getElements(locator);
		if (!elements.isEmpty()) {
			return elements.get(0);
		}

		driver.switchTo().defaultContent();
		elements = getElements(locator);

		if (!elements.isEmpty()) {
			return elements.get(0);
		} else {
			return null;
		}
	}

	/**
	 * Search and return the elements based on different locators.
	 * 
	 * @param locator
	 * @return the list of elements if present or empty list.
	 */
	protected List<WebElement> getElements(String locator) {

		List<WebElement> elements = new ArrayList<WebElement>();
		elements = driver.findElements(findBy(locator));

		return elements;
	}

	/**
	 * Create and return the Actions class
	 *
	 *
	 * @return the list of elements if present or empty list.
	 */
	protected Actions getActionsObject() {

		Actions action = new Actions(driver);
		return action;
	}

	/**
	 * Create and return the Javascript Executor class
	 *
	 *
	 * @return the list of elements if present or empty list.
	 */
	protected JavascriptExecutor getJavascriptExecutorObject() {

		JavascriptExecutor jse = ((JavascriptExecutor) driver);
		return jse;
	}


	/**
	 * @param locator     : web element to find
	 * @param locatorType : varArg parameter to pass locator types like xpath, id,
	 *                    css etc.
	 * @return the By method
	 */

	public By findBy(String locator, String... locatorType) {

		By elementLocator;
		if (locatorType.length == 0)
			elementLocator = By.xpath(locator);
		else {
			String selector = locatorType[0].replace(" ", "").toLowerCase();

			switch (selector) {

			case "xpath":
				elementLocator = By.xpath(locator);
				break;
			case "css":
				elementLocator = By.cssSelector(locator);
				break;
			case "name":
				elementLocator = By.name(locator);
				break;
			case "id":
				elementLocator = By.id(locator);
				break;
			case "tagname":
				elementLocator = By.tagName(locator);
				break;
			case "classname":
				elementLocator = By.className(locator);
				break;
			case "linktext":
				elementLocator = By.linkText(locator);
				break;
			case "partiallinktext":
				elementLocator = By.partialLinkText(locator);
				break;
			default:
				elementLocator = null;
				Report.info("Correct locator type is not provided");
				break;
			}
		}
		return elementLocator;
	}

	/**
	 * This method finds webElement based on elementType and Click on the element -
	 * POM flow
	 * 
	 * @param locator
	 * @param objectName
	 */
	protected void click(String locator, String objectName) {
		try {
			WebElement element = getWebElement(locator);
			if (element != null) {
				element.click();
				DriverUtils.waitImplicit(driver, Constants.WAIT_IN_SECONDS_5);
				Report.pass("'" + objectName + "'" + " is clicked");
			} else {
				Report.fail("'" + objectName + "' is not found");
			}
		} catch (StaleElementReferenceException e) {
			Report.fail("'" + objectName + "' is not found");
		} catch (WebDriverException e) {
			Report.fail("Unable to click " + "'" + objectName + "'");
		}
	}

	/**
	 * This method is used to set the value in text box - POM flow
	 * 
	 * @param locator    - element text box locator
	 * @param value      value to be set on text box
	 * @param objectName
	 */
	protected void setValue(String locator, String value, String objectName) {
		try {
			WebElement element = getWebElement(locator);
			if (element != null) {
				// Clear the text first in the text box.
				String textPresent = element.getAttribute("value");
				if (textPresent != null && !textPresent.isEmpty()) {
					while (element.getAttribute("value").length() != 0) {
						element.sendKeys(Keys.BACK_SPACE);
					}
				} else {
					textPresent = element.getText();
					if (textPresent != null && !textPresent.isEmpty())
						element.clear();
				}
				// Input the new text.
				element.sendKeys(value);
				DriverUtils.waitImplicit(driver, Constants.WAIT_IN_SECONDS_5);
				Report.pass("'" + value + "' text entered into " + objectName);
			} else {
				Report.fail("'" + objectName + "' is not found");
			}
		} catch (IllegalArgumentException e) {
			Report.fail("Invalid value " + value + " passed.");
		}
	}

	/**
	 * This method finds the WebElement and checks if the web element is visible or
	 * not - POM flow
	 * 
	 * @param locator    String element
	 * @param objectName - String value representing the name of the element.
	 * @return true if a web element is found, else return false
	 */
	protected boolean isVisible(String locator, String objectName) {
		boolean isVisible = false;
		WebElement element = getWebElement(locator);
		if (element != null) {
			isVisible = element.isDisplayed();
			if (isVisible)
				Report.info("'" + objectName + "' is visible.");
			else
				Report.info("'" + objectName + "' is not visible.");
		} else {
			Report.info("'" + objectName + "' is not present.");
		}
		return isVisible;
	}

	/**
	 * This method gets the WebElement for the given locator and returns the text
	 * value - POM flow
	 * 
	 * @param locator
	 * @param objectName
	 * @return the test for the element as a string.
	 */
	protected String getText(String locator, String objectName) {
		String text = "";
		WebElement webElement = getWebElement(locator);
		try {
			if (webElement != null) {
				text = webElement.getText();
				Report.pass("'" + objectName + "' is found");
			} else {
				Report.fail("'" + objectName + "' is not found");
			}
		} catch (StaleElementReferenceException e) {
			Report.error("'" + objectName + "' is not found");
		}
		return text != null ? text.trim() : text;
	}

}
