package com.caner.mcda5510.logging;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Formatter;
import java.util.logging.SimpleFormatter;

import com.caner.mcda5510.helper.GetCurrentDT;

public class OutputLogging {

	public static final Logger LOGGER = Logger.getLogger(OutputLogging.class.getName());

	public static void main(String[] args) {

		System.setProperty("java.util.logging.config.file", "./logging.properties");

		// System.out.println(SimpleLogging.class.getClassLoader().getResource("logging.properties"));
		// System.out.println(System.getProperty("user.dir")); //Viewing current
		// directory
		Handler fileHandler = null;
		Formatter simpleFormatter = null;

		try {
			String dt = GetCurrentDT.getTime();
			fileHandler = new FileHandler("./Output/" + dt + ".txt");

			simpleFormatter = new SimpleFormatter();

			LOGGER.addHandler(fileHandler);

			fileHandler.setFormatter(simpleFormatter);

			fileHandler.setLevel(Level.ALL);
			LOGGER.setLevel(Level.ALL);
			LOGGER.config("Configuration done.");
			// System.out.println("Logger Configured");

		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
