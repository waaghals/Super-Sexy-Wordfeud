package nl.avans.min04sob.scrabble.models;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import nl.avans.min04sob.scrabble.controllers.BoardController;
import nl.avans.min04sob.scrabble.core.CoreModel;
import nl.avans.min04sob.scrabble.core.DatabasePool;
import nl.avans.min04sob.scrabble.core.Event;
import nl.avans.min04sob.scrabble.core.Query;

public class AccountModel extends CoreModel {

	private String username;
	private boolean isLoggedIn;
	private final String availableCompetitionQuery = "SELECT DISTINCT(`competitie_id`) FROM `deelnemer` WHERE `competitie_id` <> (SELECT `competitie_id` FROM `deelnemer` WHERE `account_naam` = ?)";

	public AccountModel() {
		initialize();
	}

	public AccountModel(String username , boolean iamobservingthis) {
		initialize();
		if(!(iamobservingthis)){
			if (checkUsernameAvailable(username)) {
				this.username = username;
			}
		}else{
			this.username = username;
		}
	}
	
	

	public AccountModel(String username, char[] password) {
		initialize();
		login(username, password);
	}

	public void initialize() {
		username = "Onbekend";
		isLoggedIn = false;
	}

	public void login(String user, char[] password) {
		try {
			String query = "SELECT `naam` FROM `account` WHERE `naam` = ? AND `wachtwoord` = ?";
			ResultSet result = new Query(query).set(user).set(password)
					.select();

			if (Query.getNumRows(result) == 1) {
				result.next();
				username = result.getString(1);
				isLoggedIn = true;

				firePropertyChange(Event.LOGIN, null, this);
			} else {
				firePropertyChange(Event.LOGINFAIL, null, this);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void registerAccount(String username, char[] password, Role role) {

		String createAccount = "INSERT INTO `account` (`naam`, `wachtwoord` ) VALUES (?, ?)";
		String setRole = "INSERT INTO `accountrol` (`account_naam`, `rol_type`) VALUES (?, ?)";
		try {
			new Query(createAccount).set(username).set(password).exec();
			new Query(setRole).set(username).set(role).exec();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public boolean isLoggedIn() {
		return isLoggedIn;
	}

	public void logout() {
		isLoggedIn = false;
		firePropertyChange(Event.LOGOUT, null, this);
	}

	public String getUsername() {
		return username;
	}

	public void changePass(String newPass){
		String query = "UPDATE account SET wachtwoord =? WHERE naam=?;";
		try{
			new Query(query).set(newPass).set(username).exec();
		}catch(SQLException sql){
			sql.printStackTrace();
		}
		
	}
	
	public static boolean checkUsernameAvailable(String username) {
		String query = "SELECT * FROM account WHERE naam = ?";
		try {
			ResultSet check = new Query(query).set(username).select();
			return !check.first(); // If a first row exists, return true.
		} catch (SQLException sql) {
			return false;
		}
	}

	public void update() {

	}

	public boolean isRole(Role role) {
		String query = "SELECT `Rol_type` FROM `accountrol` WHERE `Account_naam` = ?";
		try {
			ResultSet rs = new Query(query).set(username).select();
			while (rs.next()) {
				if (rs.getString(1).equals(role.toString())) {
					return true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();

		}
		return false;
	}
	
	public String[] getAllCompetitions(){
		String[] allComps = new String[0];
		int x = 0;
		try {
			ResultSet dbResult = new Query("SELECT DISTINCT(`competitie_id`) FROM `deelnemer`").select();
			allComps = new String[Query.getNumRows(dbResult)];
			while(dbResult.next() && x < allComps.length){
				allComps[x] = new CompetitionModel(dbResult.getInt("competitie_id")).getDesc();
				x++;
			}
		} catch (SQLException sql) {
			sql.printStackTrace();
		}
		return allComps;		
	}
	
	public String[] getCompetitions(String username){
		String[] comp_desc = new String[0];
		int x = 0;
		try {
			ResultSet dbResult = new Query("SELECT `competitie_id` FROM `deelnemer` WHERE `account_naam` = ?").set(username).select();
			comp_desc = new String[Query.getNumRows(dbResult)];
			while(dbResult.next() && x < comp_desc.length){
				comp_desc[x] = new CompetitionModel(dbResult.getInt("competitie_id")).getDesc();
				x++;
			}
		} catch (SQLException sql) {
			sql.printStackTrace();
		}
		return comp_desc;
	}
	
	public String[] getAvailableCompetitions(String username){
		String[] comp_desc = new String[0];
		int x = 0;
		try {
			// deze query laat alleen de beschikbare competities zien die al minimaal 1 deelnemer heeft		
			ResultSet dbResult = new Query(availableCompetitionQuery).set(username).select();
			comp_desc = new String[Query.getNumRows(dbResult)];
			while(dbResult.next() && x < comp_desc.length){
				comp_desc[x] = new CompetitionModel(dbResult.getInt("competitie_id")).getDesc();
				x++;
			}
		} catch (SQLException sql) {
			sql.printStackTrace();
		}
		return comp_desc;
	}
	

	public ArrayList<GameModel> getOpenGames() {
		ArrayList<GameModel> games = new ArrayList<GameModel>();
		String query = "SELECT `ID` FROM `spel` WHERE ( `Account_naam_uitdager` = ? OR `Account_naam_tegenstander` = ?) AND `Toestand_type` = ?";
		try {
			ResultSet dbResult = new Query(query).set(username).set(username)
					.set(GameModel.STATE_PLAYING).select();
			while (dbResult.next()) {
				games.add(new GameModel(dbResult.getInt(1), this, new BoardModel(), false));
				// Add a new game with the gameId for this account
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return games;
	}
	public ArrayList<GameModel> getObserverAbleGames(){
		ArrayList<GameModel> games = new ArrayList<GameModel>();
		String query = "SELECT `ID` FROM `spel` WHERE NOT `Toestand_type` = ?";
		try {
			ResultSet dbResult = new Query(query).set(GameModel.STATE_REQUEST).select();
			while (dbResult.next()) {
				games.add(new GameModel(dbResult.getInt(1),this,new BoardModel(), true));
				// Add a new game with the gameId for this account
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return games;
	}

	public String toString() {
		return username;
	}
	
	public String getpass(){
		String query="SELECT wachtwoord FROM account WHERE naam=?";
		String pass = "";
		try{
			ResultSet check = new Query(query).set(username).select();
			check.next();
			pass = check.getString(1);
		}catch(SQLException sql){
			sql.printStackTrace();
		}
		return pass;
	}

	public int getChallengeCount() {
		// TODO Automatisch gegenereerde methodestub
		return 1;
	}
	
}
