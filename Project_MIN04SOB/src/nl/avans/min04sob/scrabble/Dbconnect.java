package nl.avans.min04sob.scrabble;

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
