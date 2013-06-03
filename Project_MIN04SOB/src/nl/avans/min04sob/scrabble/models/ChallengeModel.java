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
import nl.avans.min04sob.scrabble.core.Event;
import nl.avans.min04sob.scrabble.core.Query;

public class ChallengeModel extends CoreModel {

	public static final String STATE_ACCEPTED = "Accepted";
	public static final String STATE_REJECTED = "Rejected";
	public static final String STATE_UNKNOWN = "Unknown";
	public static final String STATE_REQUEST = "Request";
	public static final String STATE_PLAYING = "Playing";
	private final String selectQuery = "SELECT `account_naam_uitdager` FROM `Spel` WHERE `account_naam_tegenstander` = ? AND `toestand_type` = ? AND `reaktie_type` = ?";
	private final String checkQuery = "SELECT * FROM `spel`";
	private final String countQuery = "SELECT COUNT(*) FROM Spel ";
	private ResultSet result;
	private String yourname;
	private ArrayList<String> challenge = new ArrayList<String>();
	private AccountModel accountModel;
	private CompetitionController competitionController;
	private boolean isDuplication = false;
	private int numChallenge;

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
					if ((result.getString(7).equals(STATE_UNKNOWN)
							&& result.getString(4).equals(challenger)

							&& result.getString(5).equals(opponent)
							&& result.getString(3).equals(STATE_REQUEST)
							&& result.getInt(2) == compID
							))
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
		String insertLetters = "INSERT INTO `letter` (`ID`,`Spel_ID`,`LetterType_LetterSet_Code`,`LetterType_Karakter`) VALUES (?,?,?,?,?,?)";
		String query = "INSERT INTO `Spel` (`Competitie_ID`,`Toestand_type`,`Account_naam_uitdager`,`Account_naam_tegenstander`,`moment_uitdaging`,`Reaktie_type`,`Bord_naam`,`LetterSet_naam`) VALUES (?,?,?,?,?,?,?,?)";
		String getSpelId = "SELECT Max(ID)FROM `spel` ";
		String getSortOfLetter_Amount = "SELECT `karakter` , `aantal`FROM `lettertype`WHERE `LetterSet_code` = ?";
		try {

			Db.run(new Query(query).set(compID)
					.set(STATE_REQUEST).set(challenger).set(opponent)
					.set(currentdate).set(STATE_UNKNOWN)
					.set("standard").set("NL"));
			Future<ResultSet> workerId = Db.run(new Query(getSpelId));
			ResultSet spelId = workerId.get();
			spelId.next();
			
			Future<ResultSet> worker = Db.run(new Query(getSortOfLetter_Amount).set("NL"));
			ResultSet res = worker.get();
			int idCounter = 0;
			/*while(res.next()){
				for(int counter = 0;counter < res.getInt("aantal"); counter++ ){
					Db.run(new Query(insertLetters).set(idCounter).set(spelId.getInt(0)).set("NL").set(res.getString(0)));
					idCounter++;
				}
			}*/
			
		} catch (Exception e) {
			e.printStackTrace();

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
			query2 = "UPDATE Spel SET `Toestand_type`=? ,  `Reaktie_type`=?,  } `moment_reaktie`=?  WHERE `Account_naam_uitdager`=? AND `Account_naam_tegenstander`=? ;";
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
		String[] challenges = new String[0];
		int x = 0;
		try {
			Future<ResultSet> worker = Db.run(new Query(selectQuery)
					.set(accountModel.getUsername()).set(STATE_REQUEST)
					.set(STATE_UNKNOWN));
			ResultSet dbResult = worker.get();
			challenges = new String[Query.getNumRows(dbResult)];
			while (dbResult.next() && x < challenges.length) {
				challenges[x] = new String(
						dbResult.getString("account_naam_uitdager"));
				x++;
			}
		} catch (SQLException | InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
		return challenges;
	}

	@Override
	public void update() {

		
		// // if your name = tegenstander if your naam = uitdager
		// /// receive challenge your name = challenged ///// ///// ///// /////
		// ///// ///// /////
		// /array list add alleen als challend= yourname;;

		int oldNumChallenge = numChallenge;
		numChallenge = challengeArray().length;
		firePropertyChange(Event.NEWCHALLENGE, oldNumChallenge, numChallenge);

	}

}
