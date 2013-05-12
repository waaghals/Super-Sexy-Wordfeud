package nl.avans.min04sob.scrabble.core;

import java.sql.*;

public class Dbconnect {

	private Connection conn = null;
	private static Dbconnect instance;

	private Dbconnect() {
	}

	public static Connection getInstance() {
		if (instance == null) {
			instance = new Dbconnect();
			instance.connect();
			return instance.conn;
		}

		if (instance.conn == null) {
			instance.connect();
			return instance.conn;
		}
		return instance.conn;
	}

	private void connect() {

		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			instance.conn = DriverManager.getConnection(
					"jdbc:mysql://databases.aii.avans.nl:3306/wordfeud",
					"tjmbrouw", "8THMJ2S4");

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	protected void disconnect() {
		try {
			conn.close();
			instance = null;

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static ResultSet select(String query) throws SQLException {
		Connection connection = getInstance();
		ResultSet result = null;

		Statement s = connection.createStatement();
		result = s.executeQuery(query);
		System.out.println("Running query: " + query);
		return result;
	}

	public static void query(String query) throws SQLException {
		Connection connection = getInstance();
		Statement s = connection.createStatement();
		s.executeUpdate(query);
	}
}
