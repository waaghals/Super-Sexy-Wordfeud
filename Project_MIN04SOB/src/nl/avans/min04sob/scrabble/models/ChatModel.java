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
	private final int gameId;
	private String timeLastMessage;
	private AccountModel account;
	private ArrayList<String> messages;

	public ChatModel(int gameId, AccountModel user) {
		messages = new ArrayList<String>();
		this.gameId = gameId;
		System.out.println("ChatModel" + this.gameId);
		account = user;
		timeLastMessage = "2000-1-1 00:00:00";
		getNewMessages();
	}

	public void send(String newMessage) {

		try {
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date date = new Date();
			String currentdate = dateFormat.format(date);
			Dbconnect
					.query("INSERT INTO `chatregel` (`Account_naam`,`Spel_ID`, `datetime`, `bericht`) VALUES('"
							+ this.account.getUsername()
							+ "','"
							+ this.gameId
							+ "','"
							+ currentdate + "','" + newMessage + "');");
		} catch (SQLException sql) {
			sql.printStackTrace();
		}
	}

	private ArrayList<String> getNewMessages() {

		try {
			ResultSet dbResult = Dbconnect
					.select("SELECT `Account_naam`, `datetime`, `bericht` FROM `chatregel` WHERE `Spel_ID` = "
							+ gameId
							+ " AND datetime > '"
							+ timeLastMessage
							+ "' ORDER BY `datetime` ASC");
			while (dbResult.next()) {
				String senderName = dbResult.getString(1);
				if (!(senderName.equals(account.getUsername()))) {
					messages.add(senderName + " : " + dbResult.getString(3)
							+ "\n");
				} else {
					messages.add("you : " + dbResult.getString(3) + "\n");
				}
				timeLastMessage = dbResult.getString(2);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return messages;
	}

	public ArrayList<String> getMessages() {
		return messages;
	}

	@Override
	@SuppressWarnings("unchecked")
	public void update() {
		ArrayList<String> oldMessages = (ArrayList<String>) messages.clone();
		ArrayList<String> newMessages = getNewMessages();

		// Only keep the new messages
		newMessages.removeAll(oldMessages);

		if(newMessages.size() > 0){
			firePropertyChange("chatupdate", null, newMessages);
		}
		
	}
}