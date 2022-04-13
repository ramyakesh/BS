package testExecutor;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import utilities.Constants;
import utilities.DriverUtils;
import utilities.Report;

import static utilities.DriverUtils.waitImplicit;

/**
 *
 * All test pages should extend DriverBase class where driver instance and login and closing of driver will happen
 * here in this class
 *
 */
public class DriverBase {
	
	public WebDriver driver;
	/**
	 * Get the driver instance and login to product
	 * @param browser
	 */
	@BeforeTest
	@Parameters(value = { "browser" })
	protected void initDriver(String browser) {

			driver = DriverUtils.initDriver(browser);

			if (driver != null) {

				waitImplicit(driver, Constants.WAIT_IN_SECONDS_10);

				DriverUtils.navigateToUrl(driver, Constants.URL);
				waitImplicit(driver, Constants.WAIT_IN_SECONDS_10);

			} else {
				Report.fail("Driver is not initialized.");
			}
		
	}
	
	/**
	 * Quitting all the browsers that are open and all the tests are performed
	 */
	@AfterTest
	protected void quitDriver() {
		if (driver != null) {
			driver.quit();
			//Report.info("Driver instance is succesfully closed.");
		}
		else {
			Report.info("Driver is not existing.");
		}

	}


}
