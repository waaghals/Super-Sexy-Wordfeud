package nl.avans.min04sob.scrabble.models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

import nl.avans.min04sob.scrabble.core.CoreModel;
import nl.avans.min04sob.scrabble.core.Queries;
import nl.avans.min04sob.scrabble.core.Query;

public class StashModel extends CoreModel {

	public StashModel() {

	}

	public String[] getAllAvailableLetters() {
		String[] letters = null;
		String q = "SELECT `karakter` FROM `pot`";
		try {
			ResultSet res = new Query(q).select();
			int numRows  = Query.getNumRows(res);
			
			letters = new String[numRows];
			int i = 0;
			while(res.next()){
				letters[i] = res.getString(1);
				i++;		
			}
			
		} catch(Exception e){
			e.printStackTrace();
		}
		return letters;
	}

	public Tile[] getPlayerTiles(AccountModel user, GameModel game) {
		String username = user.getUsername();
		int gameId = game.getGameId();
		int numRows;
		try {
			ResultSet res = new Query(Queries.CURRENT_TILES).set(user.getUsername())
					.set(gameId).select();
			numRows = Query.getNumRows(res);

			Tile[] tiles = new Tile[numRows];
			int i = 0;
			while (res.next()) {
				String character = res.getString("letter");
				int charValue = res.getInt("waarde");
				tiles[i] = new Tile(character, charValue, Tile.MUTATABLE);
				i++;
			}

			return tiles;
		} catch (SQLException sql) {
			sql.printStackTrace();
		}
		return null;
	}

	public String getRandomLetter() {
		String letter = null;
		String q = "SELECT `karakter` FROM `pot`";
		try {
			ResultSet res = new Query(q).select();
			int numRows  = Query.getNumRows(res);
			
			String[] letters = new String[numRows];
			int i = 0;
			while(res.next()){
				letters[i] = res.getString(1);
				i++;		
			}
			
			Random randominteger = new Random();
			int r = randominteger.nextInt(letters.length);
			letter = letters[r];
			
		} catch(Exception e){
			e.printStackTrace();
		}
		return letter;
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub

	}

}
