package nl.avans.min04sob.scrabble.models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import nl.avans.min04sob.scrabble.controllers.CompetitionController;
import nl.avans.min04sob.scrabble.core.CoreModel;
import nl.avans.min04sob.scrabble.core.Db;
import nl.avans.min04sob.scrabble.core.Query;

public class ChallengeModel extends CoreModel {

	public static final String STATE_ACCEPTED = "Accepted";
	public static final String STATE_REJECTED = "Rejected";
	public static final String STATE_UNKNOWN = "Unknown";
	public static final String STATE_REQUEST = "Request";
	public static final String STATE_PLAYING = "Playing";
	private final String selectQuery = "SELECT `ID`,`account_naam_uitdager` FROM `Spel` WHERE `account_naam_tegenstander` = ? AND `toestand_type` = ?";
	private ResultSet result;
	private String yourname;
	private ArrayList<String> challenge = new ArrayList<String>();
	private AccountModel accountModel;
	private CompetitionController competitionController;

	public ChallengeModel(AccountModel user) {
		accountModel = user;
		// competitionController = new CompetitionController(user);
		yourname = accountModel.getUsername();
	}

	public void controle(AccountModel challegendname)// uitdager
	{

		// / zorgt dat je iemand niet 2 x achterelkaar kunt uitdagne

		boolean error = false;

		String queryy = "SELECT COUNT(*)   FROM Spel ";

		try {
			Future<ResultSet> worker = Db.run(new Query(queryy));
			result = worker.get();

			result.next();

			if (result.getInt(1) > 0) {
				Future<ResultSet> newWorker = Db.run(new Query(selectQuery));
				try {
					result = newWorker.get();
				} catch (InterruptedException | ExecutionException e) {
					e.printStackTrace();
				}
				while (result.next()) {

					if (result.getString(7).equals(STATE_UNKNOWN)
							&& result.getString(4).equals(yourname)
							&& result.getString(5).equals(
									challegendname.getUsername())
							&& result.getString(3).equals(STATE_UNKNOWN)
							|| yourname.equals(challegendname.getUsername())) // hier
																				// ziet
																				// een
					// fout in
					{
						error = true;
						break;
					}
				}
			}

		} catch (InterruptedException | ExecutionException | SQLException e) {
			e.printStackTrace();
		}

		if (!error) {
			createChallenge(yourname, challegendname.getUsername());
		}

	}

	public void createChallenge(String Challengername, String challegendname)
	// uitdager
	{
		competitionController = new CompetitionController(accountModel);
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		String currentdate = dateFormat.format(date);

		String query = "INSERT INTO `Spel` (`Competitie_ID`,`Toestand_type`,`Account_naam_uitdager`,`Account_naam_tegenstander`,`moment_uitdaging`,`Reaktie_type`,`moment_reaktie`,`Bord_naam`,`LetterSet_naam`) VALUES (?,?,?,?,?,?,?,?,?)";
		try {
			Db.run(new Query(query).set(competitionController.getCompID())
					.set(STATE_REQUEST).set(Challengername).set(challegendname)
					.set(currentdate).set(STATE_UNKNOWN).set(currentdate)
					.set("standard").set("NL"));
		} catch (SQLException sql) {
			sql.printStackTrace();
		}
	}

	public void respondChallenge(String nameuitdager, boolean accepted)
			throws SQLException // uitdgedaagde

	{
		// where

		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		String currentdate = dateFormat.format(date);

		String query = "SELECT * FROM Spel WHERE `Account_naam_uitdager`=? AND `Account_naam_tegenstander`=?";
		Future<ResultSet> worker = Db.run(new Query(query).set(yourname).set(
				yourname)); // / VERANDER
		ResultSet resultset = null;
		try {
			resultset = worker.get();
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
		String query2 = "";

		if (accepted == true) {
			query2 = "UPDATE Spel SET `Toestand_type`=? ,  `Reaktie_type`=?,   `moment_reaktie`=?  WHERE `Account_naam_uitdager`=? AND `Account_naam_tegenstander`=? ;";
			Db.run(new Query(query2).set(STATE_PLAYING).set(STATE_ACCEPTED)
					.set(currentdate).set(nameuitdager).set(yourname));

		}

		else {
			query2 = "UPDATE Spel SET `Toestand_type`=? ,  `Reaktie_type`=?,   `moment_reaktie`=?  WHERE `Account_naam_uitdager`=? AND `Account_naam_tegenstander`=? ;";
			Db.run(new Query(query2).set(STATE_REQUEST).set(STATE_REJECTED)
					.set(currentdate).set(nameuitdager).set(yourname));
		}
		resultset.next();
		for (int index = 0; index < challenge.size(); index++) {
			String xx = resultset.getString(4);
			if (xx.equals(challenge.get(index))) {
				challenge.remove(index);
			}
		}
	}

	public String[] challengeArray() {
		String [] challenges = new String[0];
		int x = 0;
		try {
			Future<ResultSet> worker = Db.run(new Query(selectQuery).set(
					accountModel.getUsername()).set(STATE_REQUEST));
			ResultSet dbResult = worker.get();
			challenges = new String[Query.getNumRows(dbResult)];
			while (dbResult.next() && x < challenges.length) {
				challenges[x] = new String(dbResult.getInt("ID") + ", " + dbResult.getString("account_naam_uitdager"));
				x++;
			}
		} catch (SQLException | InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
		return challenges;
		
		/*
		 * CompetitionModel[] allComps = new CompetitionModel[0];
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
		*/
	}

	@Override
	public void update() {
		// // if your name = tegenstander if your naam = uitdager
		// /// receive challenge your name = challenged ///// ///// ///// /////
		// ///// ///// /////
		// /array list add alleen als challend= yourname;;
		

	}

}
