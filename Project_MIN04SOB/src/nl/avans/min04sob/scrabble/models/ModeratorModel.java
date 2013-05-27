package nl.avans.min04sob.scrabble.models;

import java.sql.ResultSet;
import java.sql.SQLException;

import nl.avans.min04sob.scrabble.core.CoreModel;
import nl.avans.min04sob.scrabble.core.Query;

public class ModeratorModel extends CoreModel{
	
	private AccountModel account;
	
	/*private final String findChallengerQuery = "SELECT 'Account_naam_uitdager' FROM `spel`";
	private final String checkFilledChallengeQuery = "SELECT 'goedgekeurd_uitdager' FROM 'nieuwwoord'";
	private final String checkFilledTegenstanderQuery = "SELECT 'goedgekeurd_tegenstander' FROM 'nieuwwoord'";
	private final String changeStatusChallenger = "INSERT INTO 'nieuwwoord' ('goedgekeurd_uitdager') VALUES(?)";
	private final String changeStatusTegenstander = "INSERT INTO 'nieuwwoord' ('goedgekeurd_tegenstander') VALUES(?)";*/
	
	private final String wordsWhoExist = "SELECT 'word','status' FROM 'woordenboek'";
	private final String requestWord = "SELECT 'word' FROM 'woordenboek' WHERE status = 'Pending'";
	private final String acceptWord = "UPDATE 'woordenboek' set status = 'Accepted' WHERE woord = ?";
	private final String deniedWord = "UPDATE 'woordenboek' set status = 'Denied' WHERE woord = ?";
	
	public ModeratorModel()
	{
		account = new AccountModel();	
	}
	
	public void changeStatus(){
	
		
	}
	
	/*public boolean checkChallenger(){
		if(findChallengerQuery.equals(account.getUsername())){
			return true;
		}
		else{
			return false;
		}
	}*/
	
	public void acceptWord(String word){
		
		try {
			new Query(acceptWord).set(word).exec();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void deniedWord(String word){
		try {
			new Query(deniedWord).set(word).exec();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}
}
