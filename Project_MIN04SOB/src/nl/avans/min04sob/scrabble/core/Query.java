package nl.avans.min04sob.scrabble.core;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Query {

	private String query;
	private Statement statement;
	private ResultSet result;

	public Query(String query) {
		this.query = query;
		Connection conn = Dbconnect.getInstance();
		try {
			statement = conn.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		result = null;
	}

	public ResultSet select() {
		new Thread(new Runnable() {
			public void run() {
				try {
					result = statement.executeQuery(query);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}).start();

		return result;
	}

	public void execute() {
		try {
			statement.execute(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
