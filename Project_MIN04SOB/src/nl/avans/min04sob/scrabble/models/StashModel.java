package nl.avans.min04sob.scrabble.models;

import java.sql.Array;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

import nl.avans.min04sob.scrabble.core.CoreModel;
import nl.avans.min04sob.scrabble.core.Query;

public class StashModel extends CoreModel {

	public StashModel() {

	}

	public Array getAllLetters() {
		Array letters = null;
		String query = "SELECT `lettertype_karkakter` FROM `letter`";
		try {
			ResultSet dbResult = Dbconnect.select(query);
			letters = dbResult.getArray("lettertype_karkakter");
		} catch (SQLException sql) {
			sql.printStackTrace();
		}
		return letters;
	}

	public Tile[] getPlayerLetters(AccountModel user, GameModel game) {
		String sexyQuery = "SELECT `lb`.`spel_id`             AS `Spel_ID`,
       `lb`.`beurt_id`            AS `Beurt_ID`,
       `b`.`account_naam`         AS `Account_naam`,
       `l`.`lettertype_karkakter` AS `letter`,
       `lt`.`waarde`              AS `waarde`
FROM   `letterbakjeletter` `lb`
       JOIN `letter` `l`
         ON `lb`.`spel_id` = `l`.`spel_id`
            AND `lb`.`letter_id` = `l`.`id`
       JOIN `beurt` `b`
         ON `lb`.`beurt_id` = `b`.`id`
       JOIN `spel` `s`
         ON `l`.`spel_id` = `s`.`id`
       RIGHT JOIN `lettertype` `lt`
               ON `lt`.`letterset_code` = `s`.`letterset_naam`
                  AND `l`.`lettertype_karkakter` = `lt`.`karkakter`
WHERE  `lt`.`letterset_code` = 'NL'
ORDER  BY `lb`.`beurt_id` ASC ";
		
		try { 
			ResultSet dbResult = new Query(query).set(user.getUsername()).set(game.getGameId()).select();
			dbResult.next();
			String chars[] = dbResult.getString(1).split(",");
			Tile[] tiles = new Tile[chars.length];
			for (int i = 0; i < tiles.length; i++) {
				tiles[i] = new Tile(chars[i]);
			}
			return tiles;
		}
		catch (SQLException sql) {
			sql.printStackTrace();
		}
		return null;
	}

	public void getRandomLetter() {
		String query = "SELECT `lettertype_karkakter` FROM `letter`";
		try {
			ResultSet dbResult = Dbconnect.select(query);
			Array letters = dbResult.getArray("lettertype_karkakter");
			Random randominteger = new Random();
			randominteger.nextInt();
		}
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub

	}

}
