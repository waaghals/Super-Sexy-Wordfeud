package nl.avans.min04sob.scrabble.models;

import java.sql.SQLException;

import nl.avans.min04sob.scrabble.core.CoreModel;
import nl.avans.min04sob.scrabble.core.Dbconnect;

public class ChallengeModel extends CoreModel {

	public static final String STATE_ACCEPTED = "Accepted";
	public static final String STATE_REJECTED = "Rejected";
	public static final String STATE_UNKNOWN = "Unknown";
	public static final String STATE_REQUEST = "Request";
	public static final String STATE_PLAYING = "Playing";
	@Override
	public void update() 
	{
		// TODO Automatisch gegenereerde methodestub
	}
	
	public void toChallenge(String Challengername)
	{
		String query = "INSERT INTO `Spel` (`Toestand_type`) VALUES ('" + STATE_REQUEST + " ');"; 
		String query2 =  "INSERT INTO `Spel` (`Account_naam_uitdager`) VALUES ('" + Challengername + " ');"; 
		String query3 = "INSERT INTO `Spel` (`Reactie_type`) VALUES ('" + STATE_UNKNOWN + " ');"; 
		try {
			       Dbconnect.query(query);
			       Dbconnect.query(query2);
			       Dbconnect.query(query3);
		} 
		catch (SQLException sql) 
		{
				   System.out.println(query);
			       System.out.println(query2);
			       System.out.println(query3);
			       sql.printStackTrace();
		}
	}
	
	public void AcceptChallenge(String Challengedname)
	{
		String query = "INSERT INTO `Spel` (`Toestand_type`) VALUES ('" + STATE_PLAYING + " ');"; 
		String query2 =  "INSERT INTO `Spel` (`Account_naam_tegenstander`) VALUES ('" + Challengedname + " ');"; 
		String query3 = "INSERT INTO `Spel` (`Reactie_type`) VALUES ('" + STATE_ACCEPTED + " ');"; 
		try {
			       Dbconnect.query(query);
			       Dbconnect.query(query2);
			       Dbconnect.query(query3);
			} 
		catch (SQLException sql) {
					System.out.println(query);
			       System.out.println(query2);
			       System.out.println(query3);
			       sql.printStackTrace();
		 }
	}

}
