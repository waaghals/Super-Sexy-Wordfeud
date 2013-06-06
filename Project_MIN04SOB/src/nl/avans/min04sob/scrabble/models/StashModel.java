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
				
				String getTileValue =  "Select waarde FROM lettertype WHERE karakter = ? AND LetterSet_code = ?";
				Future<ResultSet> worker1 = Db.run(new Query(getTileValue).set(res.getString(5)).set("NL"));
				ResultSet tilewaarde = worker1.get();
				tilewaarde.next();
				tiles[i] = new Tile(res.getString(5), tilewaarde.getInt(1), Tile.MUTATABLE,
						res.getInt(4));
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
			res.next();
			int numRows = Query.getNumRows(res);
			
			
			if(numRows > 1){
				
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
	
	public void RemoveTileFromHand(int game_id, Tile tile){
		int turn_id= 0;
		String getTurn_id = "SELECT  MAX(`id`) AS `id` FROM `beurt` WHERE `spel_id` = ?";
		String removeTileFromHand = "DELETE FROM `letterbakje` WHERE `spel_id` = ? AND `letter_id` = ? AND `beurt_id` = ?";
		try {
			Future<ResultSet> worker1 = Db.run(new Query(getTurn_id).set(game_id));
			ResultSet res = worker1.get();
			if(res.next()){
				turn_id = res.getInt("id");
			}		
			Db.run(new Query(removeTileFromHand).set(game_id).set(tile.getTileId()).set(turn_id));
		} catch (SQLException | InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
		getRandomLetter(game_id, turn_id);
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub

	}

}
