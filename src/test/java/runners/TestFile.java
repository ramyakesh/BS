package runners;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.HomePage;
import testExecutor.DriverBase;
import utilities.CommonUtils;
import utilities.Report;

import java.util.ArrayList;
import java.util.List;

public class TestFile extends DriverBase {

    HomePage homePage;

    @BeforeClass
    public void init() {

		homePage = new HomePage(driver);
    }

    @Test
    public void Test1() {

        homePage.navigateToHome();
        homePage.searchForItem();
        homePage.applyFilters();
        homePage.getDetails();
    }


}

