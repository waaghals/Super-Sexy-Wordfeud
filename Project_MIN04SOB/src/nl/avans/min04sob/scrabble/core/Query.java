package nl.avans.min04sob.scrabble.core;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;

public class Query {

	private PreparedStatement statement;
	private ResultSet result;
	private int index;
	private DatabasePool pool;
	private Connection conn;

	public Query(String q) throws SQLException {
		pool = DatabasePool.getInstance();
		conn = pool.checkOut();
		result = null;
		index = 1;

		statement = conn.prepareStatement(q);
	}

	public Query set(String value) throws SQLException {
		statement.setString(index, value);
		index++;
		return this;
	}

	public Query set(int value) throws SQLException {
		statement.setInt(index, value);
		index++;
		return this;
	}

	public Query set(Date value) throws SQLException {
		statement.setDate(index, value);
		index++;
		return this;
	}

	public Query set(boolean value) throws SQLException {
		statement.setBoolean(index, value);
		index++;
		return this;
	}

	public Query set(Time value) throws SQLException {
		statement.setTime(index, value);
		index++;
		return this;
	}

	public Query set(char value) throws SQLException {
		statement.setString(index, value + ""); // Cast to string
		index++;
		return this;
	}

	public Query set(Blob value) throws SQLException {
		statement.setBlob(index, value); // Cast to string
		index++;
		return this;
	}

	public Query set(char[] value) throws SQLException {
		statement.setString(index, new String(value)); // Cast to string
		index++;
		return this;
	}

	public ResultSet select() {

		try {
			result = statement.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		pool.checkIn(conn); // Release the connection back to the pool
		return result;
	}

	public void exec() {

		try {
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		pool.checkIn(conn); // Release the connection back to the pool
	}

	public static int getNumRows(ResultSet res) {
		int numRows = 0;
		try {
			res.last();
			numRows = res.getRow();
			res.beforeFirst();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return numRows;
	}
}
