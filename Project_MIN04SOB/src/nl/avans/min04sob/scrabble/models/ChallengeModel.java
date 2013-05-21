package nl.avans.min04sob.scrabble.models;

import java.lang.reflect.Array;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Timer;
import java.util.TimerTask;

 

import nl.avans.min04sob.scrabble.core.CoreModel;
 

public class ChallengeModel extends CoreModel {

	public static final String STATE_ACCEPTED = "Accepted";
	public static final String STATE_REJECTED = "Rejected";
	public static final String STATE_UNKNOWN = "Unknown";
	public static final String STATE_REQUEST = "Request";
	public static final String STATE_PLAYING = "Playing";
	public int spelid;

	public Timer timer = new Timer();
	TimerTask tt;

	@Override
	public void update() {
	}

	public void createChallenge(String Challengername)
	{
		int spelid = 0;// of 1
		while (1 < 2) {
			String query = "SELECT `INT ID` FROM `Spel`;";
			try {
				ResultSet dbResult = Dbconnect.select(query);
				if (dbResult.getArray(spelid) == null) {
					break;
				}
			} catch (SQLException sql) {
				sql.printStackTrace();
			}
			spelid++;
		}
		String query = "INSERT INTO `Spel` (`spelid`,`Toestand_type`,`Account_naam_uitdager`,`Reactie_type`) VALUES ( '"
				+ spelid
				+ STATE_REQUEST
				+ Challengername
				+ STATE_UNKNOWN
				+ "')  ;";
		try {
			Dbconnect.query(query);
		} catch (SQLException sql) {
			System.out.println(query);
			sql.printStackTrace();
		}
		final int sspelid = spelid;
		final Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			public void run() {
				String query = "SELECT `Reaktie_type` FROM `Spel`; where(ID INT) values()";
				try {
					ResultSet dbResult = Dbconnect.select(query);
					if (dbResult.getString(sspelid) == "Accepted") {
						acceptChallenge();
						timer.cancel();
					}
					if (dbResult.getString(sspelid) == "Rejected") {
						timer.cancel();
					}
				} catch (SQLException sql) {
					System.out.println(query);
					sql.printStackTrace();
				}
				// //rage
			}
		}, 0, 10000);
	}

	public void receiveChallenge() // / tegenstander
	{
		final int sspelid = spelid;
		final Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			public void run() {
				int index = 0;
				while (index < 10)// of meer wiv
				{
					String query = "SELECT `Reaktie_type` FROM `Spel`; where(INT ID) values(index)";
					try {
						ResultSet dbResult = Dbconnect.select(query);
						if (dbResult.getString(index) == "getplayer name-_-") // ///
																				// ????
						{
							// open gui

							// acceptatie
							acceptChallenge();
							break;
						}
					} catch (SQLException sql) {
						System.out.println(query);
						sql.printStackTrace();
					}
					index++;
				}
				// //rage
			}
		}, 0, 10000);
	}

	public void acceptChallenge() // uitdager
	{
		String query = "INSERT INTO `Spel` (`Toestand_type`) VALUES ('"
				+ STATE_PLAYING + " ') where(ID INT) VALUES ('" + spelid
				+ "') ;"; // insert or remove
		try {
			Dbconnect.query(query);
		} catch (SQLException sql) {
			System.out.println(query);
			sql.printStackTrace();
		}
	}

	public void  OnlinePlayers(int competitionID) {		
		ArrayList<Array> array = new ArrayList<Array>();		
		String query = "SELECT `account_naam` FROM `deelnemer`;";
		try {
			ResultSet dbResult = Dbconnect.select(query);
			array.addAll((Collection<? extends Array>) dbResult.getArray("account_naam"));		
		}
		catch (SQLException sql) 
		{			
		 sql.printStackTrace();		
		}		
		 
		}

}
