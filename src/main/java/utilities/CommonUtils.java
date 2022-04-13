package utilities;

import com.opencsv.CSVReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class CommonUtils {

	public static Properties loadProperties(String filePath) {

		FileReader reader = null;
		try {
			reader = new FileReader(filePath);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		Properties properties = new Properties();
		try {
			properties.load(reader);
			System.out.println(filePath);
		} catch (IOException e) {
			Report.fail("The properties file '" + filePath + "' is not found. Hence could not load properties.");

		}
		return properties;

	}

	public static String getPropertyFromConfig(String propName) {

		FileReader reader = null;
		try {
			reader = new FileReader(Constants.CONFIG_PROPERTIES_PATH);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		Properties properties = new Properties();
		try {
			properties.load(reader);

		} catch (IOException e) {
			Report.fail("The properties file '" + Constants.CONFIG_PROPERTIES_PATH + "' is not found. Hence could not load properties.");

		}
		return properties.getProperty(propName);

	}


}
