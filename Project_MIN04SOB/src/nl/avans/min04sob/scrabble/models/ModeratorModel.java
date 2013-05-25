package nl.avans.min04sob.scrabble.models;

import java.sql.SQLException;

import nl.avans.min04sob.scrabble.core.CoreModel;
import nl.avans.min04sob.scrabble.core.Query;

public class ModeratorModel extends CoreModel{
	
	private AccountModel account;
	private final String findChallengerQuery = "SELECT 'Account_naam_uitdager' FROM 'spel'";
	private final String changeStatusChallenger = "INSERT INTO 'nieuwwoord' ('goedgekeurd_uitdager') VALUES(?)";
	private final String changeStatusTegenstander = "INSERT INTO 'nieuwwoord' ('goedgekeurd_tegenstander') VALUES(?)";

	
	public ModeratorModel()
	{
		account = new AccountModel();
	}
	
	public void changeStatus(int status){
		if(status == 1){	//accpeteren
			if (checkChallenger()){	//naar metode om te kijken of je uitdager bent
				//voeg de status in in tabel uitdager
				try {
					new Query(changeStatusChallenger).set(1).exec();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			else{
				//voeg bij tabel tegenstander de status in
				try {
					new Query(changeStatusTegenstander).set(1).exec();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		else {	//weigeren
			if (checkChallenger()){	//naar metode om te kijken of je uitdager bent
				//voeg de status in in tabel uitdager
				try {
					new Query(changeStatusChallenger).set(0).exec();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			else{
				//voeg bij tabel tegenstander de status in
				try {
					new Query(changeStatusTegenstander).set(0).exec();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}	
	}
	
	public boolean checkChallenger(){
		if(findChallengerQuery.equals(account.getUsername())){
			return true;
		}
		else{
			return false;
		}
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}
}
