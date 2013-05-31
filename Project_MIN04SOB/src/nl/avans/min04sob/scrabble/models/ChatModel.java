package nl.avans.min04sob.scrabble.models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import nl.avans.min04sob.scrabble.core.CoreModel;
import nl.avans.min04sob.scrabble.core.Event;
import nl.avans.min04sob.scrabble.core.Query;

public class ChatModel extends CoreModel {
	private final int gameId;
	private String timeLastMessage;
	private AccountModel account;
	private ArrayList<String> messages;
	private final String insertQuery = "INSERT INTO `chatregel` (`Account_naam`,`Spel_ID`, `datetime`, `bericht`) VALUES(?, ?, ?, ?)";
	private final String selectQuery = "SELECT `Account_naam`, `datetime`, `bericht` FROM `chatregel` WHERE `Spel_ID` = ? AND datetime > ? ORDER BY `datetime` ASC";

	public ChatModel(GameModel game, AccountModel user) {
		messages = new ArrayList<String>();
		gameId = game.getGameId();
		
		account = user;
		timeLastMessage = "2000-1-1 00:00:00";
		getNewMessages();

	}

	public ArrayList<String> getMessages() {
		return messages;
	}

	private ArrayList<String> getNewMessages() {

		try {
			ResultSet rs = new Query(selectQuery).set(gameId)
					.set(timeLastMessage).select();
			while (rs.next()) {
				String senderName = rs.getString(1);
				if (!(senderName.equals(account.getUsername()))) {
					messages.add(senderName + " : " + rs.getString(3) + "\n");
				} else {
					messages.add("you : " + rs.getString(3) + "\n");
				}
				timeLastMessage = rs.getString(2);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NullPointerException n){
			n.printStackTrace();
		}
		return messages;
	}

	public void send(String newMessage) {
		try {
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date date = new Date();
			String currentdate = dateFormat.format(date);
			new Query(insertQuery).set(account.getUsername()).set(gameId)
					.set(currentdate).set(newMessage).exec();
		} catch (SQLException sql) {
			sql.printStackTrace();
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	public void update() {
		ArrayList<String> oldMessages = (ArrayList<String>) messages.clone();
		ArrayList<String> newMessages = getNewMessages();

		// Only keep the new messages
		newMessages.removeAll(oldMessages);

		if (newMessages.size() > 0) {
			firePropertyChange(Event.CHATUPDATE, null, newMessages);
		}

	}
}