package nl.avans.min04sob.scrabble.models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import nl.avans.min04sob.scrabble.core.CoreModel;
import nl.avans.min04sob.scrabble.core.Db;
import nl.avans.min04sob.scrabble.core.Event;
import nl.avans.min04sob.scrabble.core.Query;
import nl.avans.min04sob.scrabble.core.Worker;

public class AccountModel extends CoreModel {

	public static boolean checkUsernameAvailable(String username) {
		String query = "SELECT * FROM account WHERE naam = ?";
		try {
			Future<ResultSet> worker = Db.run(new Query(query).set(username));
			ResultSet rs = worker.get();
			return !rs.first(); // If a first row exists, return true.
		} catch (SQLException | InterruptedException | ExecutionException sql) {
			return false;
		}
	}
	public static void registerAccount(String username, char[] password, Role[] roles) {

		String createAccount = "INSERT INTO `account` (`naam`, `wachtwoord` ) VALUES (?, ?)";
		String setRole = "INSERT INTO `accountrol` (`account_naam`, `rol_type`) VALUES (?, ?)";
		try {
			
			Db.run(new Query(createAccount).set(username).set(password));
			for (Role role : roles) {
				Db.run(new Query(setRole).set(username).set(role));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	private String username;

	private boolean isLoggedIn;

	private final String availableCompetitionQuery = "SELECT `Competitie_ID` FROM `deelnemer` WHERE `Competitie_ID` NOT IN (SELECT `Competitie_ID` FROM `deelnemer` WHERE `Account_naam` = ?) GROUP BY `Competitie_ID";
	
	
	

	public AccountModel() {
		initialize();
	}

	public AccountModel(String username) {
		initialize();
		
			this.username = username;
		}

	public AccountModel(String username, char[] password) {
		initialize();
		login(username, password);
	}

	public void changePass(String newPass){
		String query = "UPDATE account SET wachtwoord =? WHERE naam=?;";
		try{
			
			Db.run(new Query(query).set(newPass).set(username));
		}catch(SQLException sql){
			sql.printStackTrace();
		}
		
	}

	public CompetitionModel[] getAvailableCompetitions(String username){
		CompetitionModel[] comp_desc = new CompetitionModel[0];
		int x = 0;
		try {
			// deze query laat alleen de beschikbare competities zien die al minimaal 1 deelnemer heeft		
			Future<ResultSet> worker = Db.run(new Query(availableCompetitionQuery).set(username));
			ResultSet rs = worker.get();
			comp_desc = new CompetitionModel[Query.getNumRows(rs)];
			while(rs.next() && x < comp_desc.length){
				comp_desc[x] = new CompetitionModel(rs.getInt("competitie_id"));
				x++;
			}
		} catch (SQLException | InterruptedException | ExecutionException sql) {
			sql.printStackTrace();
		}
		return comp_desc;
	}

	public int getChallengeCount() {
		// TODO Automatisch gegenereerde methodestub
		return 1;
	}

	public CompetitionModel[] getCompetitions(String username){
		CompetitionModel[] compDesc = new CompetitionModel[0];
		int x = 0;
		try {
			Future<ResultSet> worker = Db.run(new Query("SELECT `competitie_id` FROM `deelnemer` WHERE `account_naam` = ?").set(username));
			ResultSet dbResult = worker.get();
			compDesc = new CompetitionModel[Query.getNumRows(dbResult)];
			while(dbResult.next() && x < compDesc.length){
				compDesc[x] = new CompetitionModel(dbResult.getInt("competitie_id"));
				x++;
			}
		} catch (SQLException | InterruptedException | ExecutionException sql) {
			sql.printStackTrace();
		}
		return compDesc;
	}

	public ArrayList<GameModel> getObserverAbleGames(){
		ArrayList<GameModel> games = new ArrayList<GameModel>();
		String query = "SELECT DISTINCT `spel_id` FROM `beurt` JOIN `spel` ON `beurt`.`spel_id` = `spel`.`id` WHERE NOT `spel`.`toestand_type` = ?";
		try {
			Future<ResultSet> worker = Db.run(new Query(query).set(GameModel.STATE_REQUEST));
			ResultSet dbResult = worker.get();
			while (dbResult.next()) {
				games.add(new GameModel(dbResult.getInt(1),this,new BoardModel(), true));
				// Add a new game with the gameId for this account
			}

		} catch (SQLException | InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
		return games;
	}
	
	public ArrayList<GameModel> getOpenGames() {
		ArrayList<GameModel> games = new ArrayList<GameModel>();
		String query = "SELECT `ID` FROM `spel` WHERE ( `Account_naam_uitdager` = ? OR `Account_naam_tegenstander` = ?) AND `Toestand_type` = ?";
		try {
			Future<ResultSet> worker = Db.run(new Query(query).set(username).set(username)
					.set(GameModel.STATE_PLAYING));
			ResultSet dbResult = worker.get();
			while (dbResult.next()) {
				games.add(new GameModel(dbResult.getInt(1), this, new BoardModel(), false));
				// Add a new game with the gameId for this account
			}

		} catch (SQLException | InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
		return games;
	}

	public String getpass(){
		String query="SELECT wachtwoord FROM account WHERE naam=?";
		String pass = "";
		try{
			Future<ResultSet> worker = Db.run(new Query(query).set(username));
			ResultSet check = worker.get();
			check.next();
			pass = check.getString(1);
		}catch(SQLException | InterruptedException | ExecutionException sql){
			sql.printStackTrace();
		}
		return pass;
	}

	public String getUsername() {
		return username;
	}
	
	public void initialize() {
		username = "Onbekend";
		isLoggedIn = false;
	}
	
	public boolean isLoggedIn() {
		return isLoggedIn;
	}
	

	public boolean isRole(Role role) {
		String query = "SELECT `Rol_type` FROM `accountrol` WHERE `Account_naam` = ?";
		try {
			Future<ResultSet> worker = Db.run(new Query(query).set(username));
			
			//Do something else
			ResultSet rs = worker.get();
			while (rs.next()) {
				if (rs.getString(1).equalsIgnoreCase(role.toString())) {
					return true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();

		}
		return false;
	}
	public void login(String user, char[] password) {
		try {
			String query = "SELECT `naam` FROM `account` WHERE `naam` = ? AND `wachtwoord` = ?";
			Future<ResultSet> worker = Db.run(new Query(query).set(user).set(password));
			
			
			ResultSet result = worker.get();

			if (Query.getNumRows(result) == 1) {
				result.next();
				username = result.getString(1);
				isLoggedIn = true;

				firePropertyChange(Event.LOGIN, null, this);
			} else {
				firePropertyChange(Event.LOGINFAIL, null, this);
			}
		} catch (SQLException | InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
	}

	public void logout() {
		isLoggedIn = false;
		firePropertyChange(Event.LOGOUT, null, this);
	}
	
	@Override
	public String toString() {
		return username;
	}

	@Override
	public void update() {

	}
	public CompetitionModel[] getOwnedCompetitions() {
		try {
			String query = "SELECT `id` FROM  `competitie` 	WHERE  `Account_naam_eigenaar` =  ?";
			Future<ResultSet> worker = Db.run(new Query(query).set(username));
			ResultSet result = worker.get();
			int numRows = Query.getNumRows(result);
			CompetitionModel[] competitions = new CompetitionModel[numRows];
			while(result.next()){
				competitions[numRows-1] = new CompetitionModel(result.getInt(1));
				numRows--;
			}
		} catch (SQLException | InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
		return new CompetitionModel[0];
	}
	
}
