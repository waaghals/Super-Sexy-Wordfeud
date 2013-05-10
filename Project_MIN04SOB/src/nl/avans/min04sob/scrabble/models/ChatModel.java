package nl.avans.min04sob.scrabble.models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import nl.avans.min04sob.scrabble.core.CoreModel;
import nl.avans.min04sob.scrabble.core.Dbconnect;

public class ChatModel extends CoreModel {
	private final int gameId, playerId;
	private String timeLastMessage;
	private ArrayList<String> messages;

	public ChatModel(int gameId, int playerId) {
		messages = new ArrayList<String>();
		this.gameId = gameId;
		this.playerId = playerId;
		timeLastMessage = "2000-1-1 00:00:00";
		getNewMessages();
	}

	public void send(String newmessage) {

		try {
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date date = new Date();
			String currentdate = dateFormat.format(date);
			Dbconnect.query(
					"INSERT INTO chat(game_id,player_id,message,send_at) VALUES('"
							+ this.gameId + "','" + this.playerId + "','"
							+ newmessage + "','" + currentdate + "');");
		} catch (SQLException sql) {
			sql.printStackTrace();
		}
	}

	private ArrayList<String> getNewMessages() {

		try {
			ResultSet dbResult = Dbconnect.select(
					"SELECT player_id, message, send_at FROM chat WHERE game_id = "
							+ this.gameId + " AND send_at > '"
							+ this.timeLastMessage + "' ORDER BY send_at ASC");
			while (dbResult.next()) {
				if (!(dbResult.getInt(1) == this.playerId)) {
					messages.add("oppenent : " + dbResult.getString(2) + "\n");
				} else {
					messages.add("you : " + dbResult.getString(2) + "\n");
				}
				timeLastMessage = dbResult.getString(3);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return messages;
	}
	
	public ArrayList<String> getMessages(){
		return messages;
	}

	@Override
	@SuppressWarnings("unchecked")
	public void update() {
		ArrayList<String> oldMessages = (ArrayList<String>) messages.clone();
		ArrayList<String> newMessages = getNewMessages();
		
		//Only keep the new messages
		newMessages.removeAll(oldMessages);
		
		firePropertyChange("chatupdate", null, newMessages);
	}
}