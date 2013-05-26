package nl.avans.min04sob.scrabble.models;

import java.lang.reflect.Array;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Observable;
import java.util.Timer;
import java.util.TimerTask;
import nl.avans.min04sob.scrabble.controllers.ChallengeController;
import nl.avans.min04sob.scrabble.core.CoreModel;
import nl.avans.min04sob.scrabble.core.Query;
import nl.avans.min04sob.scrabble.views.ChallengeView;

public class ChallengeModel extends CoreModel  {
//  String query = "SELECT `Reaktie_type` FROM `Spel`; where(ID INT) values()";
	public static final String STATE_ACCEPTED = "Accepted";
	public static final String STATE_REJECTED = "Rejected";
	public static final String STATE_UNKNOWN = "Unknown";
	public static final String STATE_REQUEST = "Request";
	public static final String STATE_PLAYING = "Playing";
	private final String selectQuery = "SELECT `*`  FROM `Spel`; "; 
	public ResultSet result;
	public String naam="";
	public Timer timer = new Timer();
	public ArrayList <String> challengegegevens = new ArrayList<String>();

	public ChallengeModel(String iets)  
	{
		 naam=iets;
	}
	public void controle(String Challengername,String  challegendname, String string) throws SQLException//uitdager
	{
		result = new Query("SELECT `*`;").select();
		boolean error = false;
	 	ResultSet dbResult =  new Query(selectQuery) .select();

		while(result.next())
		{
			if(dbResult.getString(1).equals(string))
			{
				error = true;
				break;
			}
		}
		String query = "SELECT `Reaktie ` FROM `Spel`; where(INT ID) values(?);";
		ResultSet dbResult3 = new Query(query).set(string).select();
		if(dbResult3.getString(7).equals(STATE_UNKNOWN) ||dbResult3.getString(7).equals(STATE_ACCEPTED) ||dbResult3.getString(1).equals(STATE_ACCEPTED))
		{
			error = true;
			commandsToChallengeview("4 ");
		} 
		if(error==false)//overbodig misschien    hoe sterk is break?
		{
			createChallenge(  Challengername,   challegendname , string);
		}
	}
	public void createChallenge(String Challengername,String  challegendname,final String id) throws SQLException//uitdager
	{	
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		String currentdate = dateFormat.format(date);
			
		String query = "INSERT INTO `Spel` (`spelid`,`Toestand_type`,`Account_naam_uitdager` ) VALUES (?, ?, ?, ?)";
				 
		//replace
		try {
			new Query(query).set(id).set(STATE_REQUEST).set(Challengername).set( currentdate). exec();
		} catch (SQLException sql) {
			System.out.println(query);
			sql.printStackTrace();
		}
		final Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			public void run() {
				String query1 = "SELECT `*` FROM `Spel`; where(ID INT) values(?)";
				try {
					ResultSet dbResult =  new Query(query1).set(id).select();
					if (dbResult.getString(7).equals("Accepted")) {
						timer.cancel();
						commandsToChallengeview("3");
						//open gui response
					}
					if (dbResult.getString(7).equals("Rejected")) {
						commandsToChallengeview("2");
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
		ResultSet dbResult;
		final Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			public void run() {
				int index = 0;
					String query = "SELECT `*` FROM `Spel`; where(INT ID) values(?);"; //??
					 
					//voorkomt nog een uitnodiging van de zelfde persoon
					try {
						while (result.next())// of meer wiv
						{
						try {
							ResultSet dbResult =  new Query(query).set(index).select();
							 
							if(dbResult.getString(7).equals(STATE_UNKNOWN) ||dbResult.getString(7).equals(STATE_ACCEPTED) )
							{
								result.next();
							}
							if (dbResult.getString(5).equals(naam))  // ///											// ????
							{
								challengegegevens.add(dbResult.getString(1)+" "+dbResult.getString(5));
								commandsToChallengeview("1");
								String queryx = "INSERT INTO `Spel` (`Reaktie`) values (?);";
								new Query(queryx).set(STATE_UNKNOWN).exec(); 
							}
						 
						} catch (SQLException sql) {
							System.out.println(query);
							sql.printStackTrace();
						}
						index++;
}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			}}, 0, 10000); 
			 
			}
	 
	public void respondChallenge(String spelid,String name,boolean geaccepteerd) throws SQLException // uitdgedaagde
	{
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		String currentdate = dateFormat.format(date);
		String query = "SELECT `*` FROM `Spel`; where(INT ID) = ? ;";
		ResultSet res = new Query(query).set(spelid).select();
		String query2 ="";
		 if(res.getString(5).equals(name)||res.getString(3).equals(STATE_REQUEST))
		 {
			 commandsToChallengeview("4 ");
		 }
		 else
		 {
			query = "DELETE FROM `Spel` (`Toestand_type`,Reaktie), where(ID INT) VALUES ('" + spelid
					+ "') ;"; 
			if(geaccepteerd==true){
				query2 = "INSERT INTO `Spel` (`Toestand_type`,`Reaktie`,`moment_reaktie`) VALUES ('"
					+ STATE_PLAYING +  STATE_ACCEPTED+currentdate+ " ') where(ID INT) VALUES ('" +  spelid
					+ "') ;";  
			}
			else{
				  query2 = "INSERT INTO `Spel` (`Toestand_type`,`Reaktie`,`moment_reaktie`) VALUES ('"
						+ STATE_PLAYING +  STATE_REJECTED+currentdate+ " ') where(ID INT) VALUES ('" +  spelid
						+ "') ;";  
			}
			 
			for(int index=0;index < challengegegevens.size();index++ )
			{	
				String xx= res.getString(1)+" "+res.getString(5);
				if(xx.equals(challengegegevens.get(index)))
				{
					challengegegevens.remove(index);
				}
			}
			new Query(query ).exec();
				new Query(query2).exec(); 
		}
	}
	public void commandsToChallengeview(String commando)
	{	
		 	
	}

	public ArrayList<String> gegevens() 
	{		
		return challengegegevens;		
	}
	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}
}