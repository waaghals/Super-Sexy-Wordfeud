package nl.avans.min04sob.scrabble.models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import nl.avans.min04sob.scrabble.core.CoreModel;
import nl.avans.min04sob.scrabble.core.Db;
import nl.avans.min04sob.scrabble.core.Query;

public class ModeratorModel extends CoreModel{
	
	private final String requestWord = "SELECT woord FROM woordenboek WHERE status = 'Pending' ORDER BY woord ASC";
	private final String acceptWord = "UPDATE woordenboek set status = 'Accepted' WHERE woord = ?";
	private final String deniedWord = "UPDATE woordenboek set status = 'Denied' WHERE woord = ?";
	
	public void acceptWord(String word){
		
		try {
			Db.run(new Query(acceptWord).set(word));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void denyWord(String word){
		try {
			Db.run(new Query(deniedWord).set(word));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public String[] getRequestedWordList(){
		ArrayList<String> words = new ArrayList<String>();
		try {
			Future<ResultSet> worker = Db.run(new Query(requestWord));
			ResultSet wordlist = worker.get();
			while(wordlist.next()){
				words.add(wordlist.getString(1));
			}
		} catch (SQLException | InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
		return words.toArray(new String[words.size()]);
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}
}
