package testmains;

import nl.avans.min04sob.scrabble.controllers.LoginController;
import nl.avans.min04sob.scrabble.models.AccountModel;

public class TestLogin {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		LoginController lc = new LoginController(new AccountModel());

	}

}
