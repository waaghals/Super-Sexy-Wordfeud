package nl.avans.min04sob.scrabble.models;

import java.sql.ResultSet;
import java.sql.SQLException;

import nl.avans.min04sob.scrabble.core.CoreModel;
import nl.avans.min04sob.scrabble.core.Query;

public class ModeratorModel extends CoreModel{
	
	private AccountModel account;
	
	private final String findChallengerQuery = "SELECT 'Account_naam_uitdager' FROM `spel`";
	private final String checkFilledChallengeQuery = "SELECT 'goedgekeurd_uitdager' FROM 'nieuwwoord'";
	private final String checkFilledTegenstanderQuery = "SELECT 'goedgekeurd_tegenstander' FROM 'nieuwwoord'";
	private final String changeStatusChallenger = "INSERT INTO 'nieuwwoord' ('goedgekeurd_uitdager') VALUES(?)";
	private final String changeStatusTegenstander = "INSERT INTO 'nieuwwoord' ('goedgekeurd_tegenstander') VALUES(?)";
	
	private final String wordsWhoExist = "SELECT 'word','status' FROM 'woordenboek'";
	private final String addWord = "INSERT INTO 'woordenboek' ('woord','status') VALUES(?, 'pending')";
	
	
	public ModeratorModel()
	{
		account = new AccountModel();	
	}
	
	public void changeStatus(int status){
		/*if(status == 1){	//accpeteren
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
		*/	
		if(status == 1)
		{
			
		}
		else if(status == 0)
		{
			
		}
		addWord();//gaat naar methode om eventueel woord toe te voegen
	}
	
	public boolean checkChallenger(){
		if(findChallengerQuery.equals(account.getUsername())){
			return true;
		}
		else{
			return false;
		}
	}
	
	public void addWord(){
		/*try {
			ResultSet rs1 = new Query(checkFilledChallengeQuery).select();
			ResultSet rs2 = new Query(checkFilledTegenstanderQuery).select();
			if (rs1.getInt(1) == 1 && rs2.getInt(1) == 1){
				//hier moet query die zorgt dat het woord wordt toegevoegd aan de database
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		*/
		
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}
}
