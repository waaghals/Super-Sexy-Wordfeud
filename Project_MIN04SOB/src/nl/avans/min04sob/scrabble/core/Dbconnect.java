package nl.avans.min04sob.scrabble.core;

import java.sql.Connection;
import java.sql.DriverManager;

public class Dbconnect {

	private Connection conn = null;
	private static Dbconnect instance;

	private Dbconnect() {
		connect();
	}

	public static Connection getInstance() {
		if (instance == null) {
			instance = new Dbconnect();

			return instance.conn;
		}

		if (instance.conn == null) {
			instance.connect();
			return instance.conn;
		}
		return instance.conn;
	}

	private void connect() {

		new Thread(new Runnable() {
			public void run() {
				try {

					Class.forName("com.mysql.jdbc.Driver").newInstance();
					instance.conn = DriverManager
							.getConnection(
									"jdbc:mysql://databases.aii.avans.nl:3306/tjmbrouw_db2",
									"tjmbrouw", "8THMJ2S4");

				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		}).start();

	}

	protected void disconnect() {
		try {
			conn.close();
			instance = null;

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
