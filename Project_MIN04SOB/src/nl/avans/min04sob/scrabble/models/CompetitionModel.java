package nl.avans.min04sob.scrabble.models;

import java.sql.Array;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import nl.avans.min04sob.scrabble.core.CoreModel;
import nl.avans.min04sob.scrabble.core.Query;

public class CompetitionModel extends CoreModel {

	private int competitieID;
	private int ranking = 0;
	private String desc;
	private Date startDate;
	private Date endDate;
	private AccountModel owner;
	private final String leaderboardQuery = "SELECT `account_naam`, `competitie_id`, `ranking` FROM `deelnemer` WHERE `competitie_id` = ? ORDER BY `ranking`";
	private final String joinQuery = "INSERT INTO `deelnemer` (`competitie_id`, `account_naam`, `ranking`) VALUES (?, ?, ?)";
	private final String removeQuery = "DELETE FROM `deelnemer` WHERE `competitie_id` =? AND `account_naam` =? ";
	private final String createQuery = "INSERT INTO `competitie` (`account_naam_eigenaar`, `start`, `einde`, `omschrijving`) VALUES (?,?,?,?)";
	private final String totalPlayedGamesQuery = " SELECT COUNT(*) FROM `spel` WHERE (`Account_naam_uitdager` = ? OR `Account_naam_tegenstanders` = ?) AND `Competitie_ID` = ? AND 'Toestand_type' = finished";
	private final String totalPointsQuery = "SELECT SUM(score) FROM `beurt` as b JOIN `spel` as s ON `b.spel_id` = `s.id` WHERE `Competitie_ID` = ? AND `Account_naam` = ?";
	private final String averagePointsQuery = "SELECT (SUM(score) / COUNT(DISTINCT spel_id)) FROM `beurt` as b JOIN `spel` as s ON `s.id` = `b.spel_id` WHERE `Competitie_id` = ? AND `Account_naam` = ?";
	private final String gamefinished = "SELECT `spel_id` FROM `spel` WHERE (`Account_naam_uitdager` = ? OR `Account_naam_tegenstanders` = ?) AND `Competitie_ID` = ? AND 'Toestand_type' = finished";
	private final String amountWonLosedGamesQuery = "SELECT `account_naam`, SUM(score) FROM `spel` as s JOIN `beurt` as b ON `s.id` = `b.spel_id`  WHERE (`account_naam_uitdager` = ? OR `account_naam_tegenstander` = ?) AND `Toestand_type` = 'finished' AND `competitie_id` = ? AND `s.id` = ? GROUP BY `account_naam` ORDER BY 2 DESC";
	private final String bayesianAverageQuery = "";
	private final String getCompetitionData = "SELECT * FROM `competitie` WHERE `id` = ?";

	public CompetitionModel(int compId) {
		ResultSet res;
		try {
			res = new Query(getCompetitionData).set(compId).select();
			if (Query.getNumRows(res) == 1) {
				res.next();
				competitieID = compId;
				String ownerName = res.getString("Account_naam_eigenaar");
				owner = new AccountModel(ownerName);
				startDate = res.getDate("start");
				endDate = res.getDate("einde");
				desc = res.getString("omschrijving");
			}
		} catch (SQLException e) {
			e.printStackTrace();

		}

	}

	@Override
	public void update() {
		// TODO Automatisch gegenereerde methodestub

	}

	public String getDesc() {
		return desc;
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

	public void remove(int competitionID, String username) {
		try {
			new Query(removeQuery).set(competitionID).set(username).exec();
		} catch (SQLException sql) {
			sql.printStackTrace();
		}
	}

	public void createCompetition(String username, String omschrijving) {
		try {
			new Query(createQuery).set(username).set(new Date(2013, 5, 23))
					.set(new Date(2013, 6, 1)).set(omschrijving).exec();
			// eerste Date moet currentDay zijn
			// tweede Date moet meegegeven worden in een string door gebruiker
			// (en omgezet worden naar date)
			// of met die DateRangePanel?
		} catch (SQLException sql) {
			sql.printStackTrace();
		}
	}
}
