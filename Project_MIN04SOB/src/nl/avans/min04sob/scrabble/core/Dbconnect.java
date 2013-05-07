<<<<<<< HEAD
package nl.avans.min04sob.scrabble.core;

import java.sql.*;

public class Dbconnect {

	private static Connection conn = null;
	private static Dbconnect dbconnect = new Dbconnect();

	private Dbconnect(){	
	}
	
	public static Dbconnect getInstance() {
		return dbconnect;
	}

	private boolean connect() {

		try {
			Class.forName ("com.mysql.jdbc.Driver").newInstance ();
			dbconnect.conn = DriverManager.getConnection ("jdbc:mysql://atlas.waaghals.me:3306/scrable","min04sob","auJN9YFGEbx8rLBs");
			return true;

		} catch (Exception e) {
			System.out.println(e);
			return false;
		}

	}

	protected boolean disconnect() {
		try {
			conn.close();
			conn = null;
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	protected ResultSet select(String query) {
		ResultSet result = null;
		if(dbconnect.conn==null){
			dbconnect.connect();
		}
		try {
			Statement s = dbconnect.conn.createStatement();
			result = s.executeQuery(query);
		} catch (Exception e) {
			System.out.println("selectfail :" + e);
		}
		
		return result;

	}

	protected boolean query(String query) {
		if(dbconnect.conn==null){
			dbconnect.connect();
		}
		try {
			Statement s = dbconnect.conn.createStatement();
			int result = s.executeUpdate(query);
			return true;
		} catch (Exception ex) {
			System.out.println("insertfail" + ex);
			return false;
			
		}
	}
}
=======
package nl.avans.min04sob.scrabble.core;

import java.sql.*;

public class Dbconnect {

	private static Connection conn = null;
	private static Dbconnect dbconnect;

	private Dbconnect() {
	}

	public static Dbconnect getInstance() {
		if (dbconnect == null) {
			dbconnect = new Dbconnect();
			dbconnect.connect();
			return dbconnect;
		}
		return dbconnect;
	}

	private boolean connect() {

		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			dbconnect.conn = DriverManager.getConnection(
					"jdbc:mysql://databases.aii.avans.nl:3306/tjmbrouw_db2",
					"tjmbrouw", "8THMJ2S4");
			return true;

		} catch (Exception e) {
			System.out.println(e);
			return false;
		}

	}

	protected boolean disconnect() {
		try {
			conn.close();
			conn = null;
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public ResultSet select(String query) throws SQLException {
		ResultSet result = null;
		
		Statement s = dbconnect.conn.createStatement();
		result = s.executeQuery(query);

		return result;
	}

	public void query(String query) throws SQLException {
		Statement s = dbconnect.conn.createStatement();
		s.executeUpdate(query);

	}
}
>>>>>>> refs/remotes/origin/master
