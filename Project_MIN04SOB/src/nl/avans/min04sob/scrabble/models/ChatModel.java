package nl.avans.min04sob.scrabble.models;

import java.sql.Array;
import java.sql.ResultSet;
import java.sql.SQLException;

import nl.avans.min04sob.scrabble.core.CoreModel;
import nl.avans.min04sob.scrabble.core.Dbconnect;

public class ChatModel extends CoreModel{
private final int game_id , player_id;
int timelastmessage;
	public ChatModel(int game_id , int player_id){
		this.game_id = game_id;
		this.player_id = player_id;
		timelastmessage = 0;
	}
	public void send(String newmessage){
	
		try{
			//Dbconnect.getInstance().query("INSERT INTO chat('game_id','player_id') VALUES('"+game_id+"','"+player_id+"');");
			Dbconnect.getInstance().query("INSERT INTO chat('game_id','player_id','message','send_at') VALUES('"+this.game_id+"','"+this.player_id+"','"+"1"+",'"+"2"+"');");
		}catch(SQLException sql){
			
		}
	}
	public String newmessages(){
		String returnnewmessages = null;
		try {
			ResultSet newmessages = Dbconnect.getInstance().select("select player_id,message,send_at, from chat where game_id ="+this.game_id+" and send_at >"+this.timelastmessage+"");
			if(newmessages.wasNull()){
				return null;
			}else{
			
			
			boolean notonlastrow = true;
			while(notonlastrow){
				
			if(newmessages.getInt(1) == this.player_id){
				returnnewmessages = returnnewmessages + "/n you:" + newmessages.getString(2);
			}else{
				returnnewmessages = returnnewmessages + "/n oppenent:" + newmessages.getString(2);
			}
			if(newmessages.isLast()){
				this.timelastmessage = newmessages.getInt(3);
				notonlastrow = false;
				
			}
			}
					
			return returnnewmessages;
			}
					
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "error no connection to database.";
		
	}
}
