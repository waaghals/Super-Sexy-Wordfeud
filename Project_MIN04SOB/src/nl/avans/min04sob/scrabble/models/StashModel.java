package nl.avans.min04sob.scrabble.models;


import java.sql.Array;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

import nl.avans.min04sob.scrabble.core.CoreModel;
import nl.avans.min04sob.scrabble.core.Dbconnect;

public class StashModel extends CoreModel {

	public StashModel() {
		
	}
	
	public Array getAllLetters() {
		Array letters = null;
		String query = "SELECT `lettertype_karkakter` FROM `letter`";
		try { 
			ResultSet dbResult = Dbconnect.select(query);
			letters = dbResult.getArray("lettertype_karkakter");
		}
		catch (SQLException sql) {
			sql.printStackTrace();
		}
		return letters;
	}
	
	public char getPlayerLetter(char letter, AccountModel user) {
		Array inhoud;
		String query = "SELECT `inhoud`, `account_naam` FROM `plankje` WHERE account_naam = '" 
				+ user.getUsername() 
				+ "'";
		try { 
			ResultSet dbResult = Dbconnect.select(query);
			inhoud = dbResult.getArray("inhoud");
			
		}
		catch (SQLException sql) {
			sql.printStackTrace();
		}
		return letter;
	}
	
	/*
	 * weet niet precies hoe dit moet
	 * 
	 * public void getRandomLetter() {
		String query = "SELECT `lettertype_karkakter` FROM `letter`";
		try {
			ResultSet dbResult = Dbconnect.select(query);
			Array letters = dbResult.getArray("lettertype_karkakter");
			Random randominteger = new Random();
			randominteger.nextInt();
		}
	}
	*/
	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

}
