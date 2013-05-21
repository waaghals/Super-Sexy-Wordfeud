package nl.avans.min04sob.scrabble.models;

import java.lang.reflect.Array;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Observable;
import java.util.Timer;
import java.util.TimerTask;

 

import nl.avans.min04sob.scrabble.core.CoreModel;
import nl.avans.min04sob.scrabble.core.Query;
import nl.avans.min04sob.scrabble.views.ChallengeView;
 

public class ChallengeModel extends Observable  {
//  String query = "SELECT `Reaktie_type` FROM `Spel`; where(ID INT) values()";
	public static final String STATE_ACCEPTED = "Accepted";
	public static final String STATE_REJECTED = "Rejected";
	public static final String STATE_UNKNOWN = "Unknown";
	public static final String STATE_REQUEST = "Request";
	public static final String STATE_PLAYING = "Playing";
	public int spelid;
	public Timer timer = new Timer();
 
	public ChallengeModel()
	{
		this.addObserver(new ChallengeView());
	}
	public void createChallenge(String Challengername,String  challegendname)//uitdager
	{
		// hoe kom ik aan challenger name?
		int spelid = 0;// of 1
		while (spelid < 10) {
			String query = "SELECT `INT ID` FROM `Spel`;";
			try {
				ResultSet dbResult =  new Query(query) .select();
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
		//replace
		try {
			new Query(query);
		} catch (SQLException sql) {
			System.out.println(query);
			sql.printStackTrace();
		}
		final int sspelid = spelid;
		final Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			public void run() {
				String query1 = "SELECT `Reaktie_type` FROM `Spel`; where(ID INT) values()";
				try {
					ResultSet dbResult =  new Query(query1) .select();
					if (dbResult.getString(sspelid) == "Accepted") {
					 
						timer.cancel();
						commandsToChallengeview(3);
						//open gui response
					}
					if (dbResult.getString(sspelid) == "Rejected") {
						commandsToChallengeview(2);
						timer.cancel();
						 
					}
				} catch (SQLException sql) {
					System.out.println(query1);
					sql.printStackTrace();
				}
			}}, 0, 10000);
			}
	 
	public void receiveChallenge() // / tegenstander
	{
		 /// dit code wordt altijd geactiveerd.   om de 10 secondes
		//activatie index 1
		final int sspelid = spelid;
		final Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			public void run() {
				int index = 0;
				while (index < 10)// of meer wiv
				{
					String query = "SELECT `*` FROM `Spel`; where(INT ID) values(index)"; //??
					try {
						ResultSet dbResult =  new Query(query).select();
						if (dbResult.getString(index) == "getplayer name-_-") // ///											// ????
						{
							commandsToChallengeview(1);
							spelid=sspelid; 
							break;
						}
					} catch (SQLException sql) {
						System.out.println(query);
						sql.printStackTrace();
					}
					index++;
				}
			}}, 0, 10000); 
			}
	 
	public void acceptCallenge() // uitdgedaagde
	{
		String query = "INSERT INTO `Spel` (`Toestand_type`,Reaktie) VALUES ('"
				+ STATE_PLAYING +  STATE_ACCEPTED+ " ') where(ID INT) VALUES ('" + spelid
				+ "') ;"; // insert or remove
		try {
			new Query(query);
		 //spele
		} catch (SQLException sql) {
			System.out.println(query);
			sql.printStackTrace();
		}
	}
	public void declineCallenge() // uitdgedaagde
	{
		String query = "INSERT INTO `Spel` (Reaktie) VALUES ('"+
				STATE_REJECTED+ " ') where(ID INT) VALUES ('" + spelid
				+ "') ;"; // insert or remove
		try {
			new Query(query);
		 //spele
		} catch (SQLException sql) {
			System.out.println(query);
			sql.printStackTrace();
		}
	}
	
	public void commandsToChallengeview(int getal)
	{	
		this.setChanged();
		this.notifyObservers(getal);
	}
//// die onder wordt niet echt gebruikt
	public void  OnlinePlayers(int competitionID) {		
		ArrayList<Array> array = new ArrayList<Array>();		
		String query = "SELECT `account_naam` FROM `deelnemer`;";
		try {
			ResultSet dbResult = new Query(query).select();
			array.addAll((Collection<? extends Array>) dbResult.getArray("account_naam"));		
		}
		catch (SQLException sql) 
		{			
			sql.printStackTrace();		
		}		
	}
}


/// teestz
