package nl.avans.min04sob.scrabble.core;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabasePool extends CorePool<Connection> implements Runnable {

	private static final String DSN = "jdbc:mysql://databases.aii.avans.nl:3306/tjmbrouw_db2";
	private static final String DRIVER = "com.mysql.jdbc.Driver";
	private static final String USER = "tjmbrouw";
	private static final String PASS = "8THMJ2S4";
	private static DatabasePool instance;

	public static DatabasePool getInstance() {
		if (instance == null) {
			instance = new DatabasePool();
			return instance;
		}
		return instance;
	}

	private DatabasePool() {
		super();
		new Thread(this);
	}

	protected Connection create() {
		try {
			return (DriverManager.getConnection(DSN, USER, PASS));
		} catch (SQLException e) {
			e.printStackTrace();
			return (null);
		}
	}

	public void expire(Connection o) {
		try {
			((Connection) o).close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public boolean validate(Connection o) {
		try {
			return (!((Connection) o).isClosed());
		} catch (SQLException e) {
			e.printStackTrace();
			return (false);
		}
	}

	@Override
	public void run() {
		try {
			Class.forName(DRIVER).newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
