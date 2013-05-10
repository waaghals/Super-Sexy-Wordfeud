package nl.avans.min04sob.scrabble.models;

import java.sql.Array;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import nl.avans.min04sob.scrabble.core.CoreModel;
import nl.avans.min04sob.scrabble.core.Dbconnect;

public class ChatModel extends CoreModel {
	private final int gameId, playerId;
	private String timeLastMessage;

	public ChatModel(int gameId, int playerId) {
		this.gameId = gameId;
		this.playerId = playerId;
		timeLastMessage = "2000-1-1 00:00:00";
	}

	public void send(String newmessage) {

		try {
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date date = new Date();
			String currentdate = dateFormat.format(date);
			Dbconnect.getInstance().query(
					"INSERT INTO chat(game_id,player_id,message,send_at) VALUES('"
							+ this.gameId + "','" + this.playerId + "','"
							+ newmessage + "','" + currentdate + "');");
		} catch (SQLException sql) {
			sql.printStackTrace();
		}
	}

	public String newmessages() {
		String newMessages = "";
		try {
			ResultSet dbResult = Dbconnect.getInstance().select(
					"SELECT player_id, message, send_at FROM chat WHERE game_id = "
							+ this.gameId + " AND send_at > '"
							+ this.timeLastMessage + "' ORDER BY send_at ASC");
			while (dbResult.next()) {
				if (!(dbResult.getInt(1) == this.playerId)) {
					newMessages = newMessages + "oppenent : "
							+ dbResult.getString(2) + "\n";
				}
				timeLastMessage = dbResult.getString(3);
			}
			return newMessages;
		} catch (SQLException e) {
			e.printStackTrace();
			return "error no connection to database.";
		}
	}

	public String allMessagesFromStart() {

		String newMessages = "";
		try {
			ResultSet dbResult = Dbconnect.getInstance().select(
					"SELECT player_id, message, send_at FROM chat WHERE game_id = "
							+ this.gameId + " AND send_at > '"
							+ this.timeLastMessage + "' ORDER BY send_at ASC");
			while (dbResult.next()) {
				if (dbResult.getInt(1) == this.playerId) {
					newMessages = newMessages + "you : "
							+ dbResult.getString(2) + "\n";
				} else {
					newMessages = newMessages + "oppenent : "
							+ dbResult.getString(2) + "\n";
				}
				timeLastMessage = dbResult.getString(3);
			}
			return newMessages;
		} catch (SQLException e) {
			e.printStackTrace();
			return "error no connection to database.\n";
		}

	}

	@Override
	public void update() {
		// TODO Automatisch gegenereerde methodestub

	}
}