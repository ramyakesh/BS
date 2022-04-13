package utilities;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.SkipException;


public class Report {

	public static void pass(String message) {
		Reporter.log("Pass: " + message+"<br>", true);
	}

	public static void fail(String message) {
		Reporter.log("Fail: " + message+" ", true);
		Assert.fail(message);
	}

	public static void info(String message) {
		Reporter.log("Info: " + message+"<br>",true);
	}

	public static void error(String message) {
		Reporter.log("Error: " + message+"<br>", true);
	}

	public static void log(String message) {
		Reporter.log(message);
	}

	public static void skip(String message) {
		throw new SkipException(message);
	}
}
