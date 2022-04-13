package utilities;

import java.io.File;

public class Constants {


	public static final String URL = "https://www.amazon.in";
	public static final String PROJECT_PATH = System.getProperty("user.dir");



	public static final String CONFIG_PROPERTIES_PATH = PROJECT_PATH + File.separator+"config.properties";

	public static final String DRIVER_PATH_CHROME_MAC = PROJECT_PATH + File.separator + "drivers" + File.separator
			+ "chromedriver";

	public static final String searchName = "OnePlus 9";


	public static final String SCREENSHOT_PATH = PROJECT_PATH + File.separator + "screenshots"+File.separator;

	public static final int WAIT_IN_SECONDS_2 = 2;
	public static final int WAIT_IN_SECONDS_5 = 5;
	public static final int WAIT_IN_SECONDS_10 = 10;



}
