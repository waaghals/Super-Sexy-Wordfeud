package nl.avans.min04sob.scrabble.models;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import nl.avans.min04sob.scrabble.core.CoreModel;
import nl.avans.min04sob.scrabble.core.Dbconnect;

public class AccountModel extends CoreModel {

	private String username;
	private boolean isLoggedIn;

	public AccountModel() {
		username = "Anoniem";
		isLoggedIn = false;
	}

	public AccountModel(String username) {
		String query = "SELECT `naam` FROM `account` WHERE `naam` = '"
				+ username + "';";
		try {
			ResultSet dbResult = Dbconnect.select(query);

			this.username = dbResult.getString(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public AccountModel(String username, String password) {
		String query = "SELECT `naam` FROM `account` WHERE `naam` = '"
				+ username + "' AND `wachtwoord` = '" + new String(password)
				+ "';";

		try {
			ResultSet result = Dbconnect.select(query);
			result.last();
			if (result.getRow() != 0) {
				// Correct username and pass
				username = result.getString(1);
				isLoggedIn = true;
				firePropertyChange("login", null, this);
			} else {
				firePropertyChange("loginFailure", null, this);
			}
		} catch (SQLException sql) {
			sql.printStackTrace();
		}
	}

	public static void registerAccount(String username, char[] password) {

		String query = "INSERT INTO `account` (`naam`, `wachtwoord`) VALUES ('"
				+ username + "','" + new String(password) + "');";
		// "INSERT INTO `account` (`naam`, `wachtwoord`) VALUES ('" + username +
		// "','" + getHash(password) + "');";
		try {
			Dbconnect.query(query);
		} catch (SQLException sql) {
			System.out.println(query);
			sql.printStackTrace();
		}
	}

	public boolean isLoggedIn() {
		return isLoggedIn;
	}

	public void logout() {
		isLoggedIn = false;
		firePropertyChange("logout", null, this);
	}

	public String getUsername() {
		return username;
	}

	public static boolean checkUsernameAvailable(String username) {
		try {
			ResultSet check = Dbconnect
					.select("SELECT * FROM account WHERE naam ='" + username
							+ "';");
			if (check.next()) {
				return false;
			} else {
				return true;
			}
		} catch (SQLException sql) {
			return false;
		}
	}

	public void update() {

	}

	public boolean isModerator() {
		// TODO Automatisch gegenereerde methodestub
		return true;
	}

	private String getHash(char[] array) {
		MessageDigest sha1 = null;
		try {
			sha1 = MessageDigest.getInstance("SHA-1");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

		byte[] bytes = new byte[array.length];

		for (int i = 0; i < array.length; i++) {
			bytes[i] = (byte) array[i];
		}
		sha1.update(bytes);

		// Convert the byte hash to hex
		return new java.math.BigInteger(1, sha1.digest()).toString(16);
	}

	public GameModel[] getOpenGames() {
		String query = "SELECT `ID` FROM `spel` WHERE `Account_naam_uitdager` = '"
				+ username
				+ "' OR `Account_naam_tegenstander` = '"
				+ username
				+ "' AND `Toestand_type` = '" + GameModel.STATE_PLAYING + "'";
		try {
			ResultSet dbResult = Dbconnect.select(query);
			ArrayList<GameModel> games = new ArrayList<GameModel>();
			while (dbResult.next()) {
				games.add(new GameModel(dbResult.getInt(1), this));
				// Add a new game with the gameId for this account
			}
			return (GameModel[]) games.toArray();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
