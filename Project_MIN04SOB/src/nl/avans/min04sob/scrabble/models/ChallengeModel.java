package nl.avans.min04sob.scrabble.models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import nl.avans.min04sob.scrabble.core.db.Db;
import nl.avans.min04sob.scrabble.core.db.Query;
import nl.avans.min04sob.scrabble.core.mvc.CoreModel;

public class ChallengeModel extends CoreModel {

	public static final String STATE_ACCEPTED = "Accepted";
	public static final String STATE_REJECTED = "Rejected";
	public static final String STATE_UNKNOWN = "Unknown";
	public static final String STATE_REQUEST = "Request";
	public static final String STATE_PLAYING = "Playing";
	public static final String STATE_FINISHED = "Finished";
	private final String checkQuery = "SELECT * FROM `spel`";
	private final String countQuery = "SELECT COUNT(*) FROM Spel ";
	private ResultSet result;
	private String yourname;
	private ArrayList<String> challenge = new ArrayList<String>();
	private AccountModel accountModel;
	private boolean isDuplication = false;

	public ChallengeModel(AccountModel user) {
		accountModel = user;
		// competitionController = new CompetitionController(user);
		yourname = accountModel.getUsername();
	}

	public void check(String challenger, String opponent, int compID)// uitdager
	{
		boolean error = false;
		try {
			Future<ResultSet> worker = Db.run(new Query(countQuery));
			result = worker.get();

			result.next();

			if (result.getInt(1) > 0) {
				Future<ResultSet> newWorker = Db.run(new Query(checkQuery));
				try {
					result = newWorker.get();
				} catch (InterruptedException | ExecutionException e) {
					e.printStackTrace();
				}
				while (result.next()) {
					if ((	result.getString(7).equals(STATE_UNKNOWN)
							&& result.getString(4).equals(challenger)
							&& result.getString(5).equals(opponent)
							&& result.getString(3).equals(STATE_REQUEST) 
							&& result.getInt(2) == compID)) {

						error = true;
						break;
					}
				}
			}

		} catch (InterruptedException | ExecutionException | SQLException e) {
			e.printStackTrace();
		}

		if (!error) {
			createChallenge(challenger, opponent, compID);
		}
		setDuplicatedChallenge(error);

	}

	private void setDuplicatedChallenge(boolean error) {
		isDuplication = error;
	}

	public boolean isDuplicatedChallenge() {
		return isDuplication;
	}

	private void createChallenge(String challenger, String opponent, int compID)
	// uitdager
	{
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		String currentdate = dateFormat.format(date);
		String insertLetters = "INSERT INTO `letter` (`ID`,`Spel_ID`,`LetterType_LetterSet_Code`,`LetterType_Karakter`) VALUES (?,?,?,?)";
		String query = "INSERT INTO `Spel` (`Competitie_ID`,`Toestand_type`,`Account_naam_uitdager`,`Account_naam_tegenstander`,`moment_uitdaging`,`Reaktie_type`,`Bord_naam`,`LetterSet_naam`) VALUES (?,?,?,?,?,?,?,?)";
		String getSpelId = "SELECT Max(ID)FROM `spel` ";
		String getSortOfLetter_Amount = "SELECT `karakter` , `aantal` FROM `lettertype`WHERE `LetterSet_code` = ?";
		try {

			Db.run(new Query(query).set(compID).set(STATE_REQUEST)
					.set(challenger).set(opponent).set(currentdate)
					.set(STATE_UNKNOWN).set("standard").set("NL"));
			// / SUPPPPER SEXY CODE BITCHESSSSSSSSSS JEROEN
			// met minder SEXY KLEINE CODE VERANDERING VAN PATRICK

			Future<ResultSet> workerId = Db.run(new Query(getSpelId));
			ResultSet spelId = workerId.get();
			spelId.next();

			Future<ResultSet> worker = Db.run(new Query(getSortOfLetter_Amount)
					.set("NL"));
			ResultSet res = worker.get();
			int idCounter = 0;

			while (res.next()) {

				for (int counter = 0; counter < res.getInt(2); counter++) {
					Db.run(new Query(insertLetters).set(idCounter)
							.set(spelId.getInt(1)).set("NL")
							.set(res.getString(1)));

					idCounter++;
				}
			}
			String addTurn = "INSERT INTO beurt(ID, spel_id, account_naam, score, aktie_type) VALUES(?, ?, ?, ?, ?)";
			Db.run(new Query(addTurn).set(1).set(spelId.getInt(1)).set(challenger).set(0).set("Begin"));
			Db.run(new Query(addTurn).set(2).set(spelId.getInt(1)).set(opponent).set(0).set("Begin"));
		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	public void respondChallenge(String[] compIdAccountName, boolean accepted)
			throws SQLException // uitgedaagde

	{
		
		// where
		String compId = compIdAccountName[0];
		String nameuitdager = compIdAccountName[1];
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		String currentdate = dateFormat.format(date);

		String query = "SELECT * FROM Spel WHERE `Account_naam_uitdager`=? AND `Account_naam_tegenstander`=? AND `competitie_id` = ?";
		Future<ResultSet> worker = Db.run(new Query(query).set(yourname).set(
				yourname).set(compId)); 
		ResultSet resultset = null;
		try {
			resultset = worker.get();
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
		String query2 = "";

		if (accepted == true) {
			query2 = "UPDATE Spel SET `Toestand_type`=? ,  `Reaktie_type`=?,   `moment_reaktie`=?  WHERE `Account_naam_uitdager`=? AND `Account_naam_tegenstander`=? AND `competitie_id` = ?";
			Db.run(new Query(query2).set(STATE_PLAYING).set(STATE_ACCEPTED)
					.set(currentdate).set(nameuitdager).set(yourname).set(compId));

		}

		else {
			query2 = "UPDATE Spel SET `Toestand_type`=? ,  `Reaktie_type`=?,   `moment_reaktie`=?  WHERE `Account_naam_uitdager`=? AND `Account_naam_tegenstander`=? AND `competitie_id` = ?";
			Db.run(new Query(query2).set(STATE_FINISHED).set(STATE_REJECTED)
					.set(currentdate).set(nameuitdager).set(yourname).set(compId));
		}
		resultset.next();
		for (int index = 0; index < challenge.size(); index++) {
			String xx = resultset.getString(4);
			if (xx.equals(challenge.get(index))) {
				challenge.remove(index);
			}
		}
	}

	@Override
	public void update() {

		// // if your name = tegenstander if your naam = uitdager
		// /// receive challenge your name = challenged ///// ///// ///// /////
		// ///// ///// /////
		// /array list add alleen als challend= yourname;;

	}

	public AccountModel[] getChallengeAblePlayers(int competition_id, String username) {
		AccountModel[] accounts = new AccountModel[0];
		int x = 0;
		try {
			Future<ResultSet> worker = Db
					.run(new Query(
							"SELECT `account_naam` FROM `deelnemer` WHERE `competitie_id` = ? AND `account_naam` NOT LIKE ? AND `account_naam` NOT IN (SELECT `account_naam_tegenstander` FROM `spel` WHERE `account_naam_uitdager` = ? AND `competitie_id` = ? AND `toestand_type` = ?)")
							.set(competition_id).set(username).set(username).set(competition_id).set(STATE_REQUEST));
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
	
}
