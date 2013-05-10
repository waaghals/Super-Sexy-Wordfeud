package nl.avans.min04sob.scrabble.models;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;

import nl.avans.min04sob.scrabble.core.CoreModel;
import nl.avans.min04sob.scrabble.core.Dbconnect;

public class AccountModel extends CoreModel {

	private String username;
	private boolean isLoggedIn;
	
	public AccountModel(){
		username = "Anoniem";
		isLoggedIn = false;
	}

	public void registerAccount(String username, char[] password) {

		String query = "INSERT INTO `player` (`username`, `password`, `moderator`) VALUES ('"
				+ username + "','" + getHash(password) + "', 0);";
		try {
			Dbconnect.query(query);
		} catch (SQLException sql) {
			System.out.println(query);
			sql.printStackTrace();
		}
	}

	public void login(String username, char[] password) {

		try {
			ResultSet result = Dbconnect.select(
					"SELECT `username` FROM `player` WHERE `username` = '" + username
							+ "' AND `password` = '" + getHash(password) + "';");
			result.last();
			if (result.getRow() != 0) {
				// Correct username and pass
				username = result.getString(1);
				isLoggedIn = true;
				System.out.println(username);
				firePropertyChange("login", null, this);
			}
		} catch (SQLException sql) {
			System.out
					.println("SELECT username FROM account WHERE username = "
							+ username + " AND password = "
							+ password.toString() + ";");
			sql.printStackTrace();
		}
	}

	public boolean isLoggedIn() {
		return isLoggedIn;
	}

	public void logout() {
		username = "Anoniem";
		isLoggedIn = false;
		firePropertyChange("logout", null, this);
	}

	public String getUsername() {
		return username;
	}

	public boolean checkUsernameAvailable(String username) {
		try {
			ResultSet check = Dbconnect.select(
					"SELECT * FROM player WHERE username ='" + username + "';");
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

}
