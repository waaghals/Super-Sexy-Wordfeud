package nl.avans.min04sob.scrabble.models;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import nl.avans.min04sob.scrabble.core.CoreModel;
import nl.avans.min04sob.scrabble.core.DatabasePool;
import nl.avans.min04sob.scrabble.core.Event;
import nl.avans.min04sob.scrabble.core.Query;

public class AccountModel extends CoreModel {

	private String username;
	private boolean isLoggedIn;

	public AccountModel() {
		initialize();
	}

	public AccountModel(String username) {
		initialize();
		if (checkUsernameAvailable(username)) {
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

	public static void registerAccount(String username, char[] password, String role) {

		String query = "INSERT INTO `account` (`naam`, `wachtwoord`, `rol_type`) VALUES (?, ?, ?)";
		try {
			new Query(query).set(username).set(password).set(role).exec();
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

	public boolean isModerator() {
		String query = "SELECT `Rol_type` FROM `accountrol` WHERE `Account_naam` = ?";
		try {
			ResultSet rs = new Query(query).set(username).select();
			while (rs.next()) {
				if (rs.getString(1).equals("Moderator")) {
					return true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();

		}
		return false;
	}

	public ArrayList<GameModel> getOpenGames() {
		ArrayList<GameModel> games = new ArrayList<GameModel>();
		String query = "SELECT `ID` FROM `spel` WHERE ( `Account_naam_uitdager` = ? OR `Account_naam_tegenstander` = ?) AND `Toestand_type` = ?";
		try {
			ResultSet dbResult = new Query(query).set(username).set(username)
					.set(GameModel.STATE_PLAYING).select();
			while (dbResult.next()) {
				games.add(new GameModel(dbResult.getInt(1), this));
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
}
