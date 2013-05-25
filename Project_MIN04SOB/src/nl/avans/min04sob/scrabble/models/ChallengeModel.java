package nl.avans.min04sob.scrabble.models;

import java.lang.reflect.Array;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
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
	public ResultSet result;
	 
	public ArrayList <String> challengegegevens = new ArrayList<String>();
	 
	//   to do    spel id moet anders zijn dan column nummer

	public void controle(String Challengername,String  challegendname, String string) throws SQLException//uitdager
	{
		 result = new Query("SELECT `*`;").select();
		// hoe kom ik aan challenger name?
		

		//// need test break
		boolean error = false;
		String query = "SELECT `INT ID` FROM `Spel`;";
		 String query2 = "SELECT `Account_naam_uitdager` FROM `Spel`;";
			ResultSet dbResult2 =  new Query(query2) .select();
			ResultSet dbResult =  new Query(query) .select();
		 
		while(result.next())
		{
			ResultSet dbResult3 = new Query("SELECT `Reaktie ` FROM `Spel`; where(INT ID) values(index);").select();
			if(dbResult2.getString(7).equals(STATE_UNKNOWN) ||dbResult2.getString(7).equals(STATE_ACCEPTED) ||dbResult2.getString(1).equals(STATE_ACCEPTED))
			{
				error = true;
				commandsToChallengeview("4 ");
				break;
			} 
		}
		if(error==false)//overbodig misschien    hoe sterk is break?
		{
			createChallenge(  Challengername,   challegendname , string);
		}
	}
		public void createChallenge(String Challengername,String  challegendname,String id) throws SQLException//uitdager
		{	
		String query = "INSERT INTO `Spel` (`spelid`,`Toestand_type`,`Account_naam_uitdager` ) VALUES ( '"
				+ id
				+ STATE_REQUEST
				+ Challengername
				+ "')  ;";
		//replace
		try {
			new Query(query).exec();
		} catch (SQLException sql) {
			System.out.println(query);
			sql.printStackTrace();
		}
		 
		final Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			public void run() {
				String query1 = "SELECT `Reaktie_type` FROM `Spel`; where(ID INT) values()";
				try {
					ResultSet dbResult =  new Query(query1).select();
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
		 /// dit code wordt altijd geactiveerd.   om de 10 secondes
		//activatie index 1
		//
		ResultSet dbResult;
	 
		final Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			public void run() {
				int index = 0;
					String query = "SELECT `Account_naam_tegenstander ` FROM `Spel`; where(INT ID) values(index);"; //??
					String query2 = "SELECT `spel id ` FROM `Spel`; where(INT ID) values(index);";
					//voorkomt nog een uitnodiging van de zelfde persoon
					try {
						while (result.next())// of meer wiv
						{
						try {
							ResultSet dbResult =  new Query(query).select();
							ResultSet dbResult2 = new Query("SELECT `Reaktie ` FROM `Spel`; where(INT ID) values(index);").select();
							if(dbResult2.getString(7).equals(STATE_UNKNOWN) ||dbResult2.getString(7).equals(STATE_ACCEPTED) )
							{
								result.next();
							}
							if (dbResult.getString(index).equals("getplayer name  dus zijn eigen naam-_- )"))  // ///											// ????
							{
								challengegegevens.add(dbResult.getString(1)+" "+dbResult.getString(5));
								commandsToChallengeview("1");
								new Query("INSERT INTO `Spel` (`Reaktie`) values ( '"+STATE_UNKNOWN+ "' );").exec(); 
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
				query2 = "INSERT INTO `Spel` (`Toestand_type`,Reaktie) VALUES ('"
					+ STATE_PLAYING +  STATE_ACCEPTED+ " ') where(ID INT) VALUES ('" +  spelid
					+ "') ;";  
			}
			else{
				  query2 = "INSERT INTO `Spel` (`Toestand_type`,Reaktie) VALUES ('"
						+ STATE_PLAYING +  STATE_REJECTED+ " ') where(ID INT) VALUES ('" +  spelid
						+ "') ;";  
			}
			 
			for(int index=0;index < challengegegevens.size();index++ )
			{	
				/// PAS OP
				if(spelid.equals(challengegegevens.get(1)))
				{
					challengegegevens.remove(spelid);
				}
			}
				new Query(query).exec();
				new Query(query2).exec(); 
		}
	}
	public void commandsToChallengeview(String commando)
	{	
		 
	}
//// die onder wordt niet echt gebruikt
	public ArrayList<String> gegevens() 
	{		
		return challengegegevens;		
	}
	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}
	
	 
	
}