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
		try{
			ResultSet pass = Dbconnect.getInstance().select("SELECT password FROM account WHERE username ="+username+"");
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
	
}
