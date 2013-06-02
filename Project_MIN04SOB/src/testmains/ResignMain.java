package testmains;

import nl.avans.min04sob.scrabble.controllers.BoardController;
import nl.avans.min04sob.scrabble.controllers.ResignController;
import nl.avans.min04sob.scrabble.models.AccountModel;
import nl.avans.min04sob.scrabble.models.BoardModel;
import nl.avans.min04sob.scrabble.models.GameModel;

public class ResignMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new ResignController(new GameModel(5, new AccountModel(), new BoardModel(), false));
	}

}
