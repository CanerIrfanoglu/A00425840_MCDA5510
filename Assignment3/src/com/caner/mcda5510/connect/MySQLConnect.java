package com.caner.mcda5510.connect;

import java.sql.Connection;
import java.sql.DriverManager;

import com.caner.mcda5510.logging.SimpleLogging;

public class MySQLConnect {

	static Connection connection = null;

	private MySQLConnect() {
		// Preventing instantiating new MySQLConnect
	}

	public static Connection setupConnection() {

		try {

			if (connection == null) {

				// This will load the MySQL driver, each DB has its own driver
				// Class.forName("com.mysql.jdbc.Driver");
				Class.forName("com.mysql.cj.jdbc.Driver");
				// Setup the connection with the DB

				connection = DriverManager.getConnection(
						"jdbc:mysql://localhost/transactoins?" + "user=root&password=guy1sql" + "&useSSL=false"
								+ "&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC");
			}

		} catch (Exception e) {
			SimpleLogging.LOGGER.warning("Error occured on establishing connection " + e.toString());
		}

		return connection;
	}

}
