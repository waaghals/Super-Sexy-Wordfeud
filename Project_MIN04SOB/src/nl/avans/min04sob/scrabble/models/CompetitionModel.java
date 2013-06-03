package nl.avans.min04sob.scrabble.models;

import java.sql.Array;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.sql.Date;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;


import nl.avans.min04sob.scrabble.core.CoreModel;
import nl.avans.min04sob.scrabble.core.Db;
import nl.avans.min04sob.scrabble.core.Queries;
import nl.avans.min04sob.scrabble.core.Query;

public class CompetitionModel extends CoreModel {

	private int competitieId;
	private String desc;
	private AccountModel owner;
	private Date start;
	private Date end;

	private final String leaderboardQuery = "SELECT `account_naam`, `competitie_id`, `ranking` FROM `deelnemer` WHERE `competitie_id` = ? ORDER BY `ranking`";
	private final String joinQuery = "INSERT INTO `deelnemer` (`competitie_id`, `account_naam`) VALUES (?, ?)";
	private final String removeQuery = "DELETE FROM `deelnemer` WHERE `competitie_id` =? AND `account_naam` =? ";
	private final String chatsToRemove = "SELECT `id` FROM `spel` WHERE (`Account_naam_uitdager` = ? OR `Account_naam_tegenstander` = ?) AND `competitie_id` = ?";
	private final String removeChats = "DELETE FROM `chatregel` WHERE `spel_id` = ?";
	private final String removeScores = "DELETE FROM `beurt` WHERE `spel_id` = ?";
	private final String removeGames = "DELETE FROM `spel` WHERE (`Account_naam_uitdager` = ? OR `Account_naam_tegenstander` = ?) AND `competitie_id` = ?";
	private final String createQuery = "INSERT INTO `competitie` (`account_naam_eigenaar`, `start`, `einde`, `omschrijving`) VALUES (?,?,?,?)";
	private final String removeCompetitionQuery = "DELETE FROM `competitie` WHERE `ID` = ?";
	private final String totalPlayedGamesQuery = " SELECT COUNT(*) FROM `spel` WHERE (`Account_naam_uitdager` = ? OR `Account_naam_tegenstander` = ?) AND `Competitie_ID` = ? AND 'Toestand_type' = ?";
	private final String totalPointsQuery = "SELECT SUM(`score`) as `score` FROM `beurt` JOIN `spel` ON `beurt`.`spel_id` = `spel`.`id` WHERE `Competitie_ID` = ? AND `Account_naam` = ?";
	private final String averagePointsQuery = "SELECT (SUM(`score`) / COUNT(DISTINCT `spel_id`)) as `avg` FROM `beurt` JOIN `spel` ON `spel`.`id` = `beurt`.`spel_id` WHERE `Competitie_id` = ? AND `Account_naam` = ?";
	private final String gamefinished = "SELECT `id` FROM `spel` WHERE (`Account_naam_uitdager` = ? OR `Account_naam_tegenstander` = ?) AND `Competitie_ID` = ? AND 'Toestand_type' = ?";
	private final String amountWonLosedGamesQuery = "SELECT `account_naam`, SUM(`score`) as `score` FROM `spel` JOIN `beurt` ON `spel`.`id` = `beurt`.`spel_id`  WHERE (`account_naam_uitdager` = ? OR `account_naam_tegenstander` = ?) AND `Toestand_type` = 'finished' AND `competitie_id` = ? AND `spel.id` = ? GROUP BY `account_naam` ORDER BY 2 DESC";
	private final String initQuery = "SELECT * FROM `competitie` WHERE id = ?";

	public CompetitionModel() {

	}

	public CompetitionModel(int compId) {
		try {
			Future<ResultSet> worker = Db.run(new Query(initQuery).set(compId));
			ResultSet res = worker.get();
			if (Query.getNumRows(res) == 1) {
				res.next();
				competitieId = res.getInt("id");
				owner = new AccountModel(res.getString("account_naam_eigenaar"));

				start = res.getDate("start");
				end = res.getDate("einde");
				desc = res.getString("omschrijving");
			} else {
				throw new IllegalArgumentException("Invalid competition ID");
			}
		} catch (SQLException | InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
	}

	public int amountLost(int competitionID, String username) {
		ArrayList<Integer> spel_ids = new ArrayList<Integer>();
		int amountLost = 0;
		int amountWon = 0;
		try {
			Future<ResultSet> worker = Db.run(new Query(gamefinished)
					.set(username).set(username).set(competitionID)
					.set("finished"));
			ResultSet dbResult1 = worker.get();
			while (dbResult1.next()) {
				spel_ids.add(dbResult1.getInt("id"));
			}

			for (Integer id : spel_ids) {
				Future<ResultSet> worker1 = Db.run(new Query(
						amountWonLosedGamesQuery).set(username).set(username)
						.set(competitionID).set(id));
				ResultSet dbResult2 = worker1.get();
				if (dbResult2.next()) {
					if (dbResult2.getString(1).equals(username)) {
						amountWon++;
					} else {
						amountLost++;
					}
				}
			}
		} catch (SQLException | InterruptedException | ExecutionException sql) {
			sql.printStackTrace();
		}
		return amountLost;
	}

	// aantal wedstrijden gewonnen/ verloren
	public int amountWon(int competitionID, String username) {
		ArrayList<Integer> spel_ids = new ArrayList<Integer>();
		int amountWon = 0;
		int amountLost = 0;
		try {
			Future<ResultSet> worker = Db.run(new Query(gamefinished)
					.set(username).set(username).set(competitionID)
					.set("finished"));
			ResultSet dbResult1 = worker.get();
			while (dbResult1.next()) {
				spel_ids.add(dbResult1.getInt("id"));
			}

			for (Integer id : spel_ids) {
				Future<ResultSet> worker1 = Db.run(new Query(
						amountWonLosedGamesQuery).set(username).set(username)
						.set(competitionID).set(id));
				ResultSet dbResult2 = worker1.get();
				if (dbResult2.next()) {
					if (dbResult2.getString(1).equals(username)) {
						amountWon++;
					} else {
						amountLost++;
					}
				}
			}
		} catch (SQLException | InterruptedException | ExecutionException sql) {
			sql.printStackTrace();
		}
		return amountWon;
	}

	public int averagePoints(int competitionID, String username) {
		int average = 0;
		try {
			Future<ResultSet> worker = Db.run(new Query(averagePointsQuery)
					.set(competitionID).set(username));
			ResultSet dbResult = worker.get();
			if (dbResult.next()) {
				average = dbResult.getInt("avg");
			}
		} catch (SQLException | InterruptedException | ExecutionException sql) {
			sql.printStackTrace();
		}
		return average;

	}

	public void createCompetition(String username, String omschrijving) {
		try {
			Calendar cal = Calendar.getInstance();
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date date1 = new Date(cal.getTimeInMillis());
			String currentDate = dateFormat.format(date1);
			cal.add(Calendar.MONTH, 1);
			Date date2 = new Date(cal.getTimeInMillis());
			String endDate = dateFormat.format(date2);
			Db.run(new Query(createQuery).set(username).set(currentDate)
					.set(endDate).set(omschrijving));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// wordt niet gebruikt
/*	public void deleteCompetition(int competitionID) {
		boolean competition = false; // kijkt of de competitie eerst bestaat.

		try {
			Future<ResultSet> worker = Db.run(new Query(query));
			ResultSet res = worker.get();

			while (res.next()) {
				if (res.getString("competitie").equals(competitionID)) {
					competition = true;
					break;
				}
			}

			if (competition == true) {
				ArrayList<Integer> spel_ids = new ArrayList<Integer>();

				Date date = new Date();
				Future<ResultSet> worker1;

				worker1 = Db.run(new Query(
						"SELECT `einde FROM `competitie` WHERE `id` = ?")
						.set(competitionID));

				ResultSet dbResult = worker1.get();
				if (dbResult.next()) {
					date = dbResult.getDate("einde");
				}
				// if(vandaag voorbij einddatum is)
				if (date.compareTo(new Date()) > 0) {
					Future<ResultSet> worker11 = Db
							.run(new Query(
									"SELECT `id` FROM `spel` WHERE `competitie_id` = ?")
									.set(competitionID));
					ResultSet dbResult1 = worker11.get();
					while (dbResult1.next()) {
						spel_ids.add(dbResult.getInt("spel_id"));
						for (Integer id : spel_ids) {
							Db.run(new Query(removeChats).set(id));
							Db.run(new Query(removeScores).set(id));
						}
					}
					Db.run(new Query(
							"DELETE FROM `spel` WHERE `competitie_ID` = ?")
							.set(competitionID));
					Db.run(new Query(
							"DELETE FROM `deelnemer` WHERE `competitie_ID` = ?")
							.set(competitionID));
					Db.run(new Query(removeCompetitionQuery).set(competitionID));
				}

			}
		} catch (SQLException | InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
	}
*/
	public CompetitionModel[] getAllCompetitions() {
		CompetitionModel[] allComps = new CompetitionModel[0];
		int x = 0;
		try {
			Future<ResultSet> worker = Db.run(new Query(
					"SELECT DISTINCT(`competitie_id`) FROM `deelnemer`"));
			ResultSet dbResult = worker.get();
			allComps = new CompetitionModel[Query.getNumRows(dbResult)];
			while (dbResult.next() && x < allComps.length) {
				allComps[x] = new CompetitionModel(
						dbResult.getInt("competitie_id"));
				x++;
			}
		} catch (SQLException | InterruptedException | ExecutionException sql) {
			sql.printStackTrace();
		}
		return allComps;

	}

	public int getCompetitionID(String desc) {
		int id = 0;
		try {
			Future<ResultSet> worker = Db.run(new Query(
					"SELECT `id` FROM `competitie` WHERE `omschrijving` = ?")
					.set(desc));
			ResultSet dbResult = worker.get();
			while (dbResult.next()) {
				id = dbResult.getInt("id");
			}
		} catch (SQLException | InterruptedException | ExecutionException sql) {
			sql.printStackTrace();
		}
		return id;

	}

	public int getCompId() {
		return competitieId;
	}

	public String getDesc() {
		return desc;
	}

	public Date getEndData() {
		return end;
	}

	public ArrayList<Array> getLeaderBoard(int competitionID) {
		ArrayList<Array> all = new ArrayList<Array>();

		try {
			Future<ResultSet> worker = Db.run(new Query(leaderboardQuery)
					.set(competitionID));
			ResultSet dbResult = worker.get();
			all.add(dbResult.getArray("account_naam"));
			all.add(dbResult.getArray("competitie_id"));
			all.add(dbResult.getArray("ranking"));
		} catch (SQLException | InterruptedException | ExecutionException sql) {
			sql.printStackTrace();
		}
		return all;
	}

	public AccountModel getOwner() {
		return owner;
	}

	public Date getStartDate() {
		return start;
	}

	public AccountModel[] getUsersFromCompetition(int competition_id) {
		AccountModel[] accounts = new AccountModel[0];
		int x = 0;
		try {
			Future<ResultSet> worker = Db
					.run(new Query(
							"SELECT `account_naam` FROM `deelnemer` WHERE `competitie_id` = ?")
							.set(competition_id));
			ResultSet dbResult = worker.get();
			accounts = new AccountModel[Query.getNumRows(dbResult)];
			while (dbResult.next() && x < accounts.length) {
				accounts[x] = new AccountModel(
						dbResult.getString("account_naam"));
				x++;
			}
		} catch (SQLException | InterruptedException | ExecutionException sql) {
			sql.printStackTrace();
		}
		return accounts;
	}

	public void join(int competitionID, String username) {
		try {
			Db.run(new Query(joinQuery).set(competitionID).set(username));

		} catch (SQLException sql) {
			sql.printStackTrace();
		}
	}

	// wordt niet gebruikt
	public void remove(int competitionID, String username) {
		ArrayList<Integer> spel_ids = new ArrayList<Integer>();
		try {
			Future<ResultSet> worker = Db.run(new Query(chatsToRemove)
					.set(username).set(username).set(competitionID));
			ResultSet dbResult = worker.get();
			while (dbResult.next()) {
				spel_ids.add(dbResult.getInt("spel_id"));
				for (Integer id : spel_ids) {
					Db.run(new Query(removeChats).set(id));
					Db.run(new Query(removeScores).set(id));
				}
			}
			Db.run(new Query(removeGames).set(username).set(username)
					.set(competitionID));
			Db.run(new Query(removeQuery).set(competitionID).set(username));
		} catch (SQLException | InterruptedException | ExecutionException sql) {
			sql.printStackTrace();
		}
	}

	@Override
	public String toString() {
		return desc + " : " + owner;
	}

	public int totalPlayedGames(int competitionID, String username) {
		int totalGames = 0;
		try {
			Future<ResultSet> worker = Db.run(new Query(totalPlayedGamesQuery)
					.set(username).set(username).set(competitionID)
					.set("finished"));
			ResultSet dbResult = worker.get();
			if (dbResult.next()) {
				totalGames = dbResult.getInt("COUNT(*)");
			}
		} catch (SQLException | InterruptedException | ExecutionException sql) {
			sql.printStackTrace();
		}
		return totalGames;
	}

	public int totalPoints(int competitionID, String username) {
		int total = 0;
		try {
			Future<ResultSet> worker = Db.run(new Query(totalPointsQuery).set(
					competitionID).set(username));
			ResultSet dbResult = worker.get();
			if (dbResult.next()) {
				total = dbResult.getInt("score");
			}
		} catch (SQLException | InterruptedException | ExecutionException sql) {
			sql.printStackTrace();
		}
		return total;
	}

	@Override
	public void update() {
		

	}

	public ArrayList<Object[]> getRanking() {
		Object[] row = new Object[6];
		ArrayList<Object[]> data = new ArrayList<>();
		try {
			Future<ResultSet> worker = Db.run(new Query("SELECT * FROM `ranking`"));
			ResultSet rs = worker.get();
			while(rs.next()){
				int compId = rs.getInt("competitie_id");
				String accountName = rs.getString("account_naam");
				
				row[0] = accountName;
				row[1] = totalPlayedGames(compId, accountName);
				row[2] = totalPoints(compId, accountName);
				row[3] = averagePoints(compId, accountName);
				row[4] = amountWon(compId, accountName) + " / " + amountLost(compId, accountName);
				row[5] = rs.getString("bayesian_rating");
				data.add(row);
			}
		} catch (SQLException | InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
		return data;
	}
}
