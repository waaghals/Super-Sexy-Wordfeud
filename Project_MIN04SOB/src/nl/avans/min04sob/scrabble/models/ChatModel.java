package nl.avans.min04sob.scrabble.models;

import java.sql.Array;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import nl.avans.min04sob.scrabble.core.CoreModel;
import nl.avans.min04sob.scrabble.core.Dbconnect;

public class ChatModel extends CoreModel{
private final int game_id , player_id;
private String timelastmessage;
	public ChatModel(int game_id , int player_id){
		this.game_id = game_id;
		this.player_id = player_id;
		timelastmessage = "2000-1-1 00:00:00";
	}
	public void send(String newmessage){
		
		try{
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date date = new Date();
			String currentdate = dateFormat.format(date);
			Dbconnect.getInstance().query("INSERT INTO chat(game_id,player_id,message,send_at) VALUES('"+this.game_id+"','"+this.player_id+"','"+newmessage+"','"+currentdate+"');");
		}catch(SQLException sql){
			
		}
	}
	public String newmessages(){
		String returnnewmessages = "";
		try {
			ResultSet newmessages = Dbconnect.getInstance().select("SELECT player_id, message, send_at FROM chat WHERE game_id = "+this.game_id+" AND send_at > '"+this.timelastmessage+"' ORDER BY send_at ASC");
			while(newmessages.next()){	
				if(!(newmessages.getInt(1) == this.player_id)){
					returnnewmessages = returnnewmessages + "oppenent : " + newmessages.getString(2)+"\n";
				}	
				timelastmessage = newmessages.getString(3);
			}
			return returnnewmessages;			
		} catch (SQLException e) {
			e.printStackTrace();
			return "error no connection to database.";
		}		
	}
}