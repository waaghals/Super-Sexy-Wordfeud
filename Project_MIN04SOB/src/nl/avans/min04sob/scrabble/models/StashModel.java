package nl.avans.min04sob.scrabble.models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import nl.avans.min04sob.scrabble.core.db.Db;
import nl.avans.min04sob.scrabble.core.db.Queries;
import nl.avans.min04sob.scrabble.core.db.Query;
import nl.avans.min04sob.scrabble.core.mvc.CoreModel;

public class StashModel extends CoreModel {
	private final String letterfrompot = "SELECT `karakter` FROM `pot`";

	public StashModel() {

	}

	public String[] getAllAvailableLetters() {
		String[] letters = null;
		String q = "SELECT `karakter` FROM `pot`";
		try {
			Future<ResultSet> worker = Db.run(new Query(q));
			ResultSet res = worker.get();
			int numRows = Query.getNumRows(res);

			letters = new String[numRows];
			int i = 0;
			while (res.next()) {
				letters[i] = res.getString(1);
				i++;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return letters;
	}

	public Tile[] getPlayerTiles(AccountModel user, GameModel game) {
		
		int gameId = game.getGameId();
		int numRows;
		try {
			Future<ResultSet> worker = Db.run(new Query(Queries.CURRENT_TILES)
					.set(user.getUsername()).set(gameId));
			ResultSet res = worker.get();
			numRows = Query.getNumRows(res);
			
			Tile[] tiles = new Tile[numRows];
			int i = 0;
			while (res.next()) {
				String character = res.getString("letter");
				int charValue = res.getInt("waarde");
				tiles[i] = new Tile(character, charValue, Tile.MUTATABLE,
						res.getInt("ID"));
				i++;
			}

			return tiles;
		} catch (SQLException | InterruptedException | ExecutionException sql) {
			sql.printStackTrace();
		}
		return null;
	}

	public Tile getRandomLetter(int spel_ID,int turnid) {

		Tile letter = null;
		String q = "SELECT L.Spel_ID AS Spel_ID, L.ID AS Letter_ID, L.LetterType_karakter AS Karakter FROM Letter L  WHERE L.ID NOT IN   (  SELECT Letter_ID    FROM gelegdeletter GL WHERE GL.Spel_ID = L.Spel_ID ) AND L.ID NOT IN ( SELECT Letter_ID  FROM letterbakjeletter LB  WHERE LB.Spel_ID = L.Spel_ID  AND LB.Beurt_ID IN  (SELECT MAX(Beurt_ID) FROM `letterbakjeletter` LX   JOIN `beurt` BX   ON LX.`Beurt_ID` = BX.`ID`   WHERE LX.`Spel_ID` = L.Spel_ID   GROUP BY BX.`Account_naam` ))AND Spel_ID  = ? ORDER BY L.Spel_ID, L.ID";
		try {
			Future<ResultSet> worker = Db.run(new Query(q).set(spel_ID));
			ResultSet res = worker.get();
			res.first();
			int numRows = Query.getNumRows(res);
			
			
			if(numRows > 0){
				
			Random randominteger = new Random();
			int r = randominteger.nextInt(numRows);
			
			for (int x = 0; x < r; x++) {
				res.next();
			}
			String getTileValue =  "Select waarde FROM lettertype WHERE karakter = ? AND LetterSet_code = ?";
			Future<ResultSet> worker1 = Db.run(new Query(getTileValue).set(res.getString(3)).set("NL"));
			ResultSet tilewaarde = worker1.get();
			tilewaarde.next();
			letter = new Tile(res.getString(3),tilewaarde.getInt(1),Tile.MUTATABLE,res.getInt(2));;
			String addlettertoplankje = " INSERT INTO `letterbakjeletter` (`Spel_ID` ,`Letter_ID` ,`Beurt_ID`)VALUES (?, ?, ?)";
			Db.run(new Query(addlettertoplankje).set(spel_ID).set(res.getInt(2)).set(turnid));
			//addTileToStash(spel_ID, letter);
			System.out.println("testttttttttttttttttttt");
			}
			

		} catch (Exception e) {
			e.printStackTrace();
		}
		return letter;
	}

	public boolean letterleft() {

		Future<ResultSet> worker;
		try {
			worker = Db.run(new Query(letterfrompot));
			ResultSet res = worker.get();

			int numRows = Query.getNumRows(res);
			if (numRows == 0) {
				return false;
			} else {
				return true;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	public void addTileToStash(int game_id, Tile tile){
		String q = "INSERT INTO `pot` (`spel_id`,`letter_id`,`karakter`) VALUES (?,?,?)";
		try {
			Db.run(new Query(q).set(game_id).set(tile.getTileId()).set(tile.toString()));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void addTilesToPlayerStash(int game_id){
		String q = "SELECT `letter_ID`,`karakter` FROM `pot` WHERE `Spel_ID` = ?";
		
		try {
			Future<ResultSet> worker = Db.run(new Query(q).set(game_id));
			ResultSet res = worker.get();
			int numRows = Query.getNumRows(res);
			
			res.first();
			if(numRows > 0){
				Random randomInteger = new Random();
				int r = randomInteger.nextInt(numRows);		
			for (int x = 0; x < r; x++) {
				res.next();
			}
			//tile uit pot halen in in spelers hand zetten
			}
			
		} catch (SQLException | InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub

	}

}
