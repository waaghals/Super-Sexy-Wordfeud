package nl.avans.min04sob.scrabble.core.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import nl.avans.min04sob.scrabble.core.mvc.CorePool;

public class DatabasePool extends CorePool<Connection> {

	private static final String DSN = "jdbc:mysql://databases.aii.avans.nl:3306/wordfeud";
	private static final String DRIVER = "com.mysql.jdbc.Driver";
	private static final String USER = "tjmbrouw";
	private static final String PASS = "8THMJ2S4";
	private static DatabasePool instance;
	private int counter = 0;

	public static DatabasePool getInstance() {
		if (instance == null) {
			instance = new DatabasePool();
			return instance;
		}
		return instance;
	}

	private DatabasePool() {
		super();
		try {
			Class.forName(DRIVER).newInstance();
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected synchronized Connection create() {
		counter++;
		System.out.println("(Adding) Number of connections: " + counter);
		try {
			return (DriverManager.getConnection(DSN, USER, PASS));
		} catch (SQLException e) {
			e.printStackTrace();
			return (null);
		}
	}

	@Override
	public synchronized void expire(Connection o) {
		counter--;
		System.out.println("(Dropping) Number of connections: " + counter);
		try {
			o.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public synchronized boolean validate(Connection o) {
		try {
			return (!o.isClosed());
		} catch (SQLException e) {
			e.printStackTrace();
			return (false);
		}
	}

}
