package nl.avans.min04sob.scrabble.models;

import java.awt.Point;
import java.sql.Array;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import nl.avans.min04sob.scrabble.core.CoreModel;
import nl.avans.min04sob.scrabble.core.Query;

public class CompetitionModel extends CoreModel {

	private int competitieID;
	private int ranking = 0;
	private String name = "name";
	
	private final String leaderboardQuery = "SELECT `account_naam`, `competitie_id`, `ranking` FROM `deelnemer` WHERE `competitie_id` = ? ORDER BY `ranking`";
	private final String joinQuery = "INSERT INTO `deelnemer` (`competitie_id`, `account_naam`, `ranking`) VALUES (?, ?, ?)";
	private final String removeQuery = "DELETE FROM `deelnemer` WHERE `competitie_id` =? AND `account_naam` =? ";
	private final String chatsToRemove = "SELECT `id` FROM `spel` WHERE (`Account_naam_uitdager` = ? OR `Account_naam_tegenstanders` = ?) AND `competitie_id` = ?";
	private final String removeChats = "DELETE FROM `chatregel` WHERE `spel_id` = ?";
	private final String removeScores = "DELETE FROM `beurt` WHERE `spel_id` = ?";
	private final String removeGames = "DELETE FROM `spel` WHERE (`Account_naam_uitdager` = ? OR `Account_naam_tegenstanders` = ?) AND `competitie_id` = ?";
	private final String createQuery = "INSERT INTO `competitie` (`account_naam_eigenaar`, `start`, `einde`, `omschrijving`) VALUES (?,?,?,?)";
	private final String removeCompetitionQuery = "DELETE FROM `competitie` WHERE `ID` = ?";
	private final String totalPlayedGamesQuery = " SELECT COUNT(*) FROM `spel` WHERE (`Account_naam_uitdager` = ? OR `Account_naam_tegenstanders` = ?) AND `Competitie_ID` = ? AND 'Toestand_type' = finished";
	private final String totalPointsQuery = "SELECT SUM(`score`) FROM `beurt` as `b` JOIN `spel` as `s` ON `b.spel_id` = `s.id` WHERE `Competitie_ID` = ? AND `Account_naam` = ?";
	private final String averagePointsQuery = "SELECT (SUM(`score`) / COUNT(DISTINCT `spel_id`)) as `avg` FROM `beurt` as `b` JOIN `spel` as `s` ON `s.id` = `b.spel_id` WHERE `Competitie_id` = ? AND `Account_naam` = ?";
	private final String gamefinished = "SELECT `spel_id` FROM `spel` WHERE (`Account_naam_uitdager` = ? OR `Account_naam_tegenstanders` = ?) AND `Competitie_ID` = ? AND 'Toestand_type' = finished";
	private final String amountWonLosedGamesQuery = "SELECT `account_naam`, SUM(score) FROM `spel` as `s` JOIN `beurt` as `b` ON `s.id` = `b.spel_id`  WHERE (`account_naam_uitdager` = ? OR `account_naam_tegenstander` = ?) AND `Toestand_type` = 'finished' AND `competitie_id` = ? AND `s.id` = ? GROUP BY `account_naam` ORDER BY 2 DESC";
	private final String bayesianAverageQuery = "";
	private final String query="SELECT `*` FROM `deelnemer`;";
	
	public CompetitionModel(int int1) {
		competitieID = int1;
	}

	@Override
	public void update() {
		// TODO Automatisch gegenereerde methodestub

	}

	public String getName() {
		return name;
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
	
	public CompetitionModel[] getCompitions(String username){
		CompetitionModel[] comp_ids = new CompetitionModel[0];
		int x = 0;
		try {
			ResultSet dbResult = new Query("SELECT `competitie_id` FROM `deelnemer` WHERE `account_naam` = ?").set(username).select();
			comp_ids = new CompetitionModel[Query.getNumRows(dbResult)];
			while(dbResult.next() && x < comp_ids.length){
				comp_ids[x] = new CompetitionModel(dbResult.getInt("competitie_id"));
				x++;
			}
		} catch (SQLException sql) {
			sql.printStackTrace();
		}
		return comp_ids;
	}
	
	public void getUsersFromCompetition(int competition_id){
		ArrayList<AccountModel> accounts = new ArrayList<AccountModel>();
		try {
			ResultSet dbResult = new Query("SELECT `account_naam` FROM `deelnemer` WHERE `competitie_id` = ?").set(competition_id).select();
			while(dbResult.next()){
				accounts.add(new AccountModel(dbResult.getString("account_naam")));
			}
		} catch (SQLException sql) {
			sql.printStackTrace();
		}
	}

	public void join(int competitionID, String username) {
		try {
			// zorgt dat de deelnemer niet kan inschrijven omdat hij al ingeschreven is
			boolean ingeschreven =false;
			ResultSet dbResult = new Query(query).select();
			while(dbResult.next()){
				if(dbResult.getString("account_naam").equals(username)){
					ingeschreven=true;
					break;
				}
			}
			if(ingeschreven ==false){
			new Query(joinQuery).set(competitionID).set(username).set(ranking).exec();
			}
		} catch (SQLException sql) {
			sql.printStackTrace();
		}
	}

	public void remove(int competitionID, String username) {
		ArrayList<Integer> spel_ids = new ArrayList<Integer>();
		try {
			ResultSet dbResult = new Query(chatsToRemove).set(username).set(username).set(competitionID).select();
			while (dbResult.next()) {
				spel_ids.add(dbResult.getInt("spel_id"));
				for (Integer id : spel_ids) {
					new Query(removeChats).set(id).exec();
					new Query(removeScores).set(id).exec();
				}
			}
			new Query(removeGames).set(username).set(username).set(competitionID).exec();	
			new Query(removeQuery).set(competitionID).set(username).exec();
		} catch (SQLException sql) {
			sql.printStackTrace();
		}
	}

	public void createCompetition(String username, String eindDatum,
			String omschrijving) {
		try {
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date date1 = new Date();
			String currentdate = dateFormat.format(date1);
			Date date2;
			date2 = dateFormat.parse(eindDatum);
			String endDate = dateFormat.format(date2);
			new Query(createQuery).set(username).set(currentdate).set(endDate)
					.set(omschrijving).exec();
		} catch (SQLException | ParseException e) {
			e.printStackTrace();
		}
	}

	public void deleteCompetition(int competitionID) {
		boolean competition =false;  // kijkt of de competitie eerst bestaat.
		ResultSet res;
		try {
			res = new Query(query).select();
			while(res.next()){
				if(res.getString("competitie").equals(competitionID)){
					competition=true;
					break;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(competition==true){
		ArrayList<Integer> spel_ids = new ArrayList<Integer>();
		try {
			Date date = new Date();	
			ResultSet dbResult = new Query("SELECT `einde FROM `competitie` WHERE `id` = ?").set(competitionID).select();
			if (dbResult.next()) {
				date = dbResult.getDate("einde");
			}
			// if(vandaag voorbij einddatum is)
			if (date.compareTo(new Date()) > 0) {				
				ResultSet dbResult1 = new Query("SELECT `id` FROM `spel` WHERE `competitie_id` = ?").set(competitionID).select();
				while(dbResult1.next()){
					spel_ids.add(dbResult.getInt("spel_id"));
					for (Integer id : spel_ids) {
						new Query(removeChats).set(id).exec();
						new Query(removeScores).set(id).exec();
					}
				}
				new Query("DELETE FROM `spel` WHERE `competitie_ID` = ?").set(competitionID).exec();
				new Query("DELETE FROM `deelnemer` WHERE `competitie_ID` = ?").set(competitionID).exec();
				new Query(removeCompetitionQuery).set(competitionID).exec();
			}
		} catch (SQLException sql) {
			sql.printStackTrace();
		}
		}
	}

	// aantal wedstrijden gewonnen/ verloren
	public int amountWon(int competitionID, String username) {
		ArrayList<Integer> spel_ids = new ArrayList<Integer>();
		int amountWon = 0;
		int amountLost = 0;
		try {
			ResultSet dbResult1 = new Query(gamefinished).set(username)
					.set(username).set(competitionID).select();
			while (dbResult1.next()) {
				spel_ids.add(dbResult1.getInt("spel_id"));
			}

			for (Integer id : spel_ids) {
				ResultSet dbResult2 = new Query(amountWonLosedGamesQuery)
						.set(username).set(username).set(competitionID).set(id)
						.select();
				if (dbResult2.next()) {
					if (dbResult2.getString(1).equals(username)) {
						amountWon++;
					} else {
						amountLost++;
					}
				}
			}
		} catch (SQLException sql) {
			sql.printStackTrace();
		}
		return amountWon;
	}
	
	public int amountLost(int competitionID, String username) {
		ArrayList<Integer> spel_ids = new ArrayList<Integer>();
		int amountWon = 0;
		int amountLost = 0;
		try {
			ResultSet dbResult1 = new Query(gamefinished).set(username)
					.set(username).set(competitionID).select();
			while (dbResult1.next()) {
				spel_ids.add(dbResult1.getInt("spel_id"));
			}

			for (Integer id : spel_ids) {
				ResultSet dbResult2 = new Query(amountWonLosedGamesQuery)
						.set(username).set(username).set(competitionID).set(id)
						.select();
				if (dbResult2.next()) {
					if (dbResult2.getString(1).equals(username)) {
						amountWon++;
					} else {
						amountLost++;
					}
				}
			}
		} catch (SQLException sql) {
			sql.printStackTrace();
		}
		return amountLost;
	}

	public int totalPlayedGames(int competitionID, String username){
		int totalGames = 0;
		try {
			ResultSet dbResult = new Query(totalPlayedGamesQuery).set(username).set(username).set(competitionID).select();
			if(dbResult.next()){
				totalGames = dbResult.getInt("COUNT(*)");
			}
		} catch (SQLException sql) {
			sql.printStackTrace();
		}
		return totalGames;
	}
	
	public int totalPoints(int competitionID, String username){
		int total = 0;
		try {
			ResultSet dbResult = new Query(totalPointsQuery).set(competitionID).set(username).select();
			if(dbResult.next()){
				total = dbResult.getInt("SUM(score)");
			}
		} catch (SQLException sql) {
			sql.printStackTrace();
		}
		return total;
	}
	
	public int averagePoints(int competitionID, String username){
		int average = 0;
		try {
			ResultSet dbResult = new Query(averagePointsQuery).set(competitionID).set(username).select();
			if(dbResult.next()){
				average = dbResult.getInt("avg");
			}
		} catch (SQLException sql) {
			sql.printStackTrace();
		}
		return average;
		
	}
}
