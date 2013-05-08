package nl.avans.min04sob.scrabble;

import java.sql.ResultSet;
import java.sql.SQLException;

import nl.avans.min04sob.scrabble.core.Dbconnect;

public class Dbconnecttestclass {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try{
			ResultSet check = Dbconnect.getInstance().select("Select * FROM account WHERE username='aaron';");
			if(check.next()){
				System.out.println(check.getString(3));
			}
		}catch(SQLException sql){
			
		}
	}

}
