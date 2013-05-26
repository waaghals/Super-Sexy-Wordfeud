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
import nl.avans.min04sob.scrabble.core.Event;
import nl.avans.min04sob.scrabble.core.Query;
import nl.avans.min04sob.scrabble.views.ChallengeView;

public class ChallengeModel extends CoreModel  {

	public static final String STATE_ACCEPTED = "Accepted";
	public static final String STATE_REJECTED = "Rejected";
	public static final String STATE_UNKNOWN = "Unknown";
	public static final String STATE_REQUEST = "Request";
	public static final String STATE_PLAYING = "Playing";
	private final String selectQuery = "SELECT `*` FROM `Spel`; "; 
	public ResultSet result;
	public String name=""; 
	public Timer timer = new Timer();
	public ArrayList <String> challenge = new ArrayList<String>();

	public ChallengeModel(String name)  
	{
		 this.name=name;
	}

	public void controle(String Challengername,String  challegendname, String string) throws SQLException//uitdager
	{
		result = new Query("SELECT `*`;").select();
		boolean error = false;
	 	ResultSet dbResult =  new Query(selectQuery) .select();
		while(result.next()){
			if(dbResult.getString(1).equals(string))
			{
				error = true;
				break;
			}
		}
		String query = "SELECT `Reaktie ` FROM `Spel`; where(INT ID) values(?);";
		ResultSet dbResult3 = new Query(query).set(string).select();
		if(dbResult3.getString(7).equals(STATE_UNKNOWN) ||dbResult3.getString(7).equals(STATE_ACCEPTED) ||dbResult3.getString(1).equals(STATE_ACCEPTED)){
			error = true;
			commandsToChallengeview("4 ");
		} 
		if(error==false){
			createChallenge(  Challengername,   challegendname , string);
		}
	}
	public void createChallenge(String Challengername,String  challegendname,final String id) throws SQLException//uitdager
	{	
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		String currentdate = dateFormat.format(date);		
		String query = "INSERT INTO `Spel` (`spelid`,`Toestand_type`,`Account_naam_uitdager` ) VALUES (?, ?, ?, ?)";
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
	 
	@Override
	public void update() {
		ResultSet dbResult;
		int index = 0;
		String query = "SELECT `*` FROM `Spel`; where(INT ID) values(?);"; //??
		//voorkomt nog een uitnodiging van de zelfde persoon
		try {
			while (result.next()){
			try {
					dbResult =  new Query(query).set(index).select();
				if(dbResult.getString(7).equals(STATE_UNKNOWN) ||dbResult.getString(7).equals(STATE_ACCEPTED) )
				{
					result.next();
				}
				if (dbResult.getString(5).equals(name))  // ///											// ????
				{
					challenge.add(dbResult.getString(1)+" "+dbResult.getString(5));
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
			e.printStackTrace();
		}
	}
	 
	public void respondChallenge(String gameid,String name,boolean accepted) throws SQLException // uitdgedaagde
	{
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		String currentdate = dateFormat.format(date);
		String query = "SELECT `*` FROM `Spel`; where(INT ID) = ? ;";
		ResultSet resultset = new Query(query).set(gameid).select();
		String query2 ="";
		 if(resultset.getString(5).equals(name)||resultset.getString(3).equals(STATE_REQUEST)){
			 commandsToChallengeview("4 ");
		 }
		 else{
			query = "DELETE FROM `Spel` (`Toestand_type`,Reaktie), where(ID INT) VALUES ('" + gameid
					+ "') ;"; 
			if(accepted==true){
				query2 = "INSERT INTO `Spel` (`Toestand_type`,`Reaktie`,`moment_reaktie`) VALUES ('"
					+ STATE_PLAYING +  STATE_ACCEPTED+currentdate+ " ') where(ID INT) VALUES ('" +  gameid
					+ "') ;";  
			}
			else{
				  query2 = "INSERT INTO `Spel` (`Toestand_type`,`Reaktie`,`moment_reaktie`) VALUES ('"
						+ STATE_PLAYING +  STATE_REJECTED+currentdate+ " ') where(ID INT) VALUES ('" +  gameid
						+ "') ;";  
			}
			 
			for(int index=0;index < challenge.size();index++ ){	
				String xx= resultset.getString(1)+" "+resultset.getString(5);
				if(xx.equals(challenge.get(index)))
				{
					challenge.remove(index);
				}
			}
			new Query(query).exec();
			new Query(query2).exec(); 
		}
	}
 
	public void commandsToChallengeview(String commando)
	{	
		firePropertyChange(null, null, commando);
	}
	
	public ArrayList<String> challengeArray() 
	{		
		return challenge;		
	}
}
 
