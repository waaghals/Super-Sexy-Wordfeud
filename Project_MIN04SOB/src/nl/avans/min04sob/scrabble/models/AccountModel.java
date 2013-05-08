package nl.avans.min04sob.scrabble.models;

import java.sql.ResultSet;
import java.sql.SQLException;

import nl.avans.min04sob.scrabble.core.CoreModel;
import nl.avans.min04sob.scrabble.core.Dbconnect;

public class AccountModel extends CoreModel {

	private String username;
	
	
	public AccountModel(){
		
	}
	
	public void registerAccount(String username, char[] password){
		//Dbconnect.getInstance().query("");
	}
	
	public void login(String username, char[] password){
		//TODO query aanpassen
		try{
			ResultSet pass = Dbconnect.getInstance().select("SELECT password FROM account WHERE username ="+username+";");
			pass.next();
			if(pass.getArray(1).equals(password)){
				this.username = username;
			}
		}catch(SQLException sql){
			
		}
	}
	
	public String getUsername(){
		return username;
	}
	
	public boolean checkUsernameAvailable(String username){
		//TODO query aanpassen
		try{
			ResultSet check = Dbconnect.getInstance().select("SELECT * FROM account WHERE username ="+username+";");
			if(check.next()){
				return false;
			}else{
				return true;
			}
		}catch(SQLException sql){
			return false;
		}
	}
	
	public void createAccount(String username, char[] password){
		//TODO query opstellen
		//Dbconnect.getInstance().query("INSERT INTO account(username,pa")
	}
}
