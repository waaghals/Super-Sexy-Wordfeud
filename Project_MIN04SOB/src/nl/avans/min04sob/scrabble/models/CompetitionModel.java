package nl.avans.min04sob.scrabble.models;

import java.sql.Array;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import nl.avans.min04sob.scrabble.core.CoreModel;
import nl.avans.min04sob.scrabble.core.Query;

public class CompetitionModel extends CoreModel {

	private int competitieID; // competitie ID
	private int ranking = 0;
	private final String leaderboardQuery = "SELECT `account_naam`, `competitie_id`, `ranking` FROM `deelnemer` WHERE `competitie_id` = ? ORDER BY `ranking`";
	private final String joinQuery = "INSERT INTO `deelnemer` (`competitie_id`, `account_naam`, `ranking`) VALUES (?, ?, ?)";
	private final String removeQuery ="DELETE FROM `deelnemer` WHERE `competitie_id` = ? AND `account_naam` = ?  VALUES (?, ?)";

	public CompetitionModel(int int1) {
		// TODO Automatisch gegenereerde constructorstub
		competitieID = int1;
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

		try {
			ResultSet dbResult = new Query(leaderboardQuery).set(competitionID)
					.select();
			all.add(dbResult.getArray("account_naam"));
			all.add(dbResult.getArray("competitie_id"));
			all.add(dbResult.getArray("ranking"));
		} catch (SQLException sql) {
			sql.printStackTrace();
		}
		return all;
	}

	public void join(int competitionID, String username) {
		try {
			new Query(joinQuery).set(competitionID).set(username).set(ranking)
					.exec();
		} catch (SQLException sql) {
			sql.printStackTrace();
		}
	}
	
	public void remove(int competitionID, String username){
		try {
			new Query("DELETE FROM `deelnemer` WHERE `competitie_id` ="+ competitionID +" AND `account_naam` = " + username).exec();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
