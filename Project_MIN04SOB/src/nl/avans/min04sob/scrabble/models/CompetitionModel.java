package nl.avans.min04sob.scrabble.models;

import java.sql.Array;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import nl.avans.min04sob.scrabble.core.CoreModel;
import nl.avans.min04sob.scrabble.core.Dbconnect;

public class CompetitionModel extends CoreModel {

	private int cID; // competitie ID
	
	
	public CompetitionModel(int int1) {
		// TODO Automatisch gegenereerde constructorstub
		cID = int1;
	}

	@Override
	public void update() {
		// TODO Automatisch gegenereerde methodestub

	}

	public String getName() {
		return "CompName";
	}

	
	public ArrayList<Array> getLeaderBoard(int competitionID) {
		ArrayList<Array> all = new ArrayList<Array>();

		String query = "SELECT `account_naam`, `competitie_id`, `ranking` FROM `deelnemer` WHERE `competitie_id` = '"
				+ competitionID + "' ORDER BY `ranking`";
		
		try {
			ResultSet dbResult = Dbconnect.select(query);
			all.add(dbResult.getArray("account_naam"));
			all.add(dbResult.getArray("competitie_id"));
			all.add(dbResult.getArray("ranking"));
		}
		catch (SQLException sql) {
			sql.printStackTrace();
		}
		return all;
	}
	
	public void join(int competitionID, String username) {
		String query = "INSERT INTO `deelnemer` (`competitie_id`, `account_naam`) VALUES ('" 
				+ competitionID + "','" + username + "');"; 
	
		try {
			Dbconnect.query(query);
		} catch (SQLException sql) {
			System.out.println(query);
			sql.printStackTrace();
		}
	}
}
