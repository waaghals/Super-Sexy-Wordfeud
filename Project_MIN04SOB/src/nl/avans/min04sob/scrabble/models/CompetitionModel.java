package nl.avans.min04sob.scrabble.models;

import java.sql.Array;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import nl.avans.min04sob.scrabble.core.CoreModel;
import nl.avans.min04sob.scrabble.core.Query;

public class CompetitionModel extends CoreModel {

	private int competitieID; // competitie ID
	private int ranking = 0;
	private final String leaderboardQuery = "SELECT `account_naam`, `competitie_id`, `ranking` FROM `deelnemer` WHERE `competitie_id` = ? ORDER BY `ranking`";
	private final String joinQuery = "INSERT INTO `deelnemer` (`competitie_id`, `account_naam`, `ranking`) VALUES (?, ?, ?)";
	private final String removeQuery = "DELETE FROM `deelnemer` WHERE `competitie_id` =? AND `account_naam` =? ";
	private final String createQuery = "INSERT INTO `competitie` (`ID`, `account_naam_eigenaar`, `start`, `einde`, `omschrijving`) VALUES (?,?,?,?,?)";

	public CompetitionModel(int int1, String username, String omschrijving) {
		competitieID = int1;
		
		try {
			new Query(createQuery).set(competitieID).set(username).set(new Date(2013,5,23)).set(new Date(2013,6,1)).set(omschrijving).exec();
			// eerste Date moet currentDay zijn
			// tweede Date moet meegegeven worden in een string door gebruiker (en omgezet worden naar date)
		} catch (SQLException sql) {
			sql.printStackTrace();
		}
	
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
			new Query(removeQuery).set(competitionID).set(username).exec();
		} catch (SQLException sql) {
			sql.printStackTrace();
		}
	}
}
