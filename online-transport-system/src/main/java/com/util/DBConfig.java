package com.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public final class DBConfig {
	private static final Properties PROPERTIES = loadProperties();

	private DBConfig() {
	}

	public static Connection getConnection() throws SQLException {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			throw new SQLException("MySQL JDBC driver not found", e);
		}

		return DriverManager.getConnection(
				requiredProperty("db.url"),
				requiredProperty("db.user"),
				requiredProperty("db.password.env"));
	}

	private static Properties loadProperties() {
		Properties properties = new Properties();
		try (InputStream input = DBConfig.class.getClassLoader().getResourceAsStream("db.properties")) {
			if (input != null) {
				properties.load(input);
				return properties;
			}
		} catch (IOException e) {
			throw new ExceptionInInitializerError("Unable to read db.properties: " + e.getMessage());
		}

		try (InputStream input = new FileInputStream("db.properties")) {
			properties.load(input);
			return properties;
		} catch (IOException e) {
			throw new ExceptionInInitializerError("Unable to find db.properties: " + e.getMessage());
		}
	}

	private static String requiredProperty(String key) throws SQLException {
		String value = PROPERTIES.getProperty(key);
		if (value == null || value.trim().isEmpty()) {
			throw new SQLException("Missing database property: " + key);
		}
		return value.trim();
	}
}
