package nl.avans.min04sob.scrabble.models;

import java.sql.SQLException;

import nl.avans.min04sob.scrabble.core.CoreModel;
import nl.avans.min04sob.scrabble.core.Dbconnect;

public class ChallengeModel extends CoreModel {
/*/
	public static final String STATE_ACCEPTED = "Accepted";
	public static final String STATE_REJECTED = "Rejected";
	public static final String STATE_UNKNOWN = "Unknown";
	public static final String STATE_REQUEST = "Request";
	public static final String STATE_PLAYING = "Playing";
	public int index;
	@Override
	public void update() 
	{
		// TODO Automatisch gegenereerde methodestub
	}
	
	public void toChallenge(String Challengername)
	{
  
					String query = //+ int id  //"INSERT INTO `Spel` (//dbindex//`Toestand_type`,`Account_naam_uitdager`,`Reactie_type`) VALUES (//inddex//'" + STATE_REQUEST +    Challengername + STATE_UNKNOWN + "');";
					try {
						       Dbconnect.query(query);
					} 
					catch (SQLException sql) 
					{
							   System.out.println(query);
						       sql.printStackTrace();
					}
	}
	
	public void AcceptChallenge(String Challengedname)
	{
		//time event
		//pseudo where = dbindex=index ///
		//iets met select ofzo
		String query = "INSERT INTO `Spel` (`Toestand_type`,`Account_naam_tegenstander`,`Reactie_type`) VALUES ('" + STATE_PLAYING + Challengedname + STATE_ACCEPTED +" ');";
	
		try {
			       Dbconnect.query(query);
			} 
		catch (SQLException sql) 
		{
					System.out.println(query);
					sql.printStackTrace();
		}
	}
	
}
	/*/