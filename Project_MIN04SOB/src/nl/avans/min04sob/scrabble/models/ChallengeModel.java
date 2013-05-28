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

import javax.swing.JFrame;

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
	private final String selectQuery = "SELECT * FROM `Spel`; "; 
	public ResultSet result;
	public String yourname=""; 
	public Timer timer = new Timer();
	public ArrayList <String> challenge = new ArrayList<String>();
	

	public ChallengeModel(String name) throws SQLException  
	{
		yourname=name;
	}

	public void controle(String Challengername,String  challegendname) throws SQLException//uitdager
	{
		///  zorgt dat je iemand niet 2 x achterelkaar kunt uitdagne
		boolean error = false;
		String queryy = "SELECT COUNT(*)   FROM Spel ";
		result =  new Query(queryy).select();
		if(result.getInt(1)>0)
		{
			result =  new Query(selectQuery) .select();
			while(result.next()){
				if(result.getString(7).equals(STATE_UNKNOWN)&&result.getString(5).equals(challegendname))
				{
					error = true;
					commandsToChallengeview("4");
					break;
				}
			}
		}
		if(error==false){
			createChallenge(Challengername, challegendname);
		}
	} 
		
	 
	public void createChallenge(String Challengername,String  challegendname) throws SQLException//uitdager
	{	
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		String currentdate = dateFormat.format(date);	
			//standard
	            
				String query = "INSERT INTO `Spel` (`Competitie_ID`,`Toestand_type`,`Account_naam_uitdager`,`Account_naam_tegenstander`,`moment_uitdaging`,`Reaktie_type`,`moment_reaktie`,`Bord_naam`,`LetterSet_naam`) VALUES (?,?,?,?,?,?,?,?,?)";
				try {
					new Query(query).set(2).set(STATE_REQUEST).set(Challengername).set(challegendname).set(currentdate).set(STATE_UNKNOWN).set(currentdate).set("standard").set("NL"). exec();
				} catch (SQLException sql) {
					System.out.println(query);
					sql.printStackTrace();
				} 
	}
	 
	@Override
	public void update() {
		//// if your name = tegenstander          if your naam = uitdager 	
 ///// receive challenge    your name = challenged ///// ///// ///// ///// ///// ///// /////
///array list add alleen als challend= yourname;;		
		try {
			ResultSet dbResult = new Query(selectQuery).select();
			while (result.next()){
				 if(!challenge.contains(dbResult.getString(4))){
					if(dbResult.getString(5).equals(yourname) &&dbResult.getString(3).equals(STATE_REQUEST)) {
					challenge.add(dbResult.getString(4));
					commandsToChallengeview("1");
					}
				 }
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		 
	}
	 
	public void respondChallenge(String nameuitdager,boolean accepted) throws SQLException // uitdgedaagde
	{
		//where
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		String currentdate = dateFormat.format(date);
		String query = "SELECT `*` FROM `Spel` WHERE  `Account_naam_uitdager`=? and `Account_naam_tegenstander` =? ;";
		ResultSet resultset = new Query(query).set(nameuitdager).set(yourname).select();
		String query2 ="";
		new Query(query).exec();
			 
			if(accepted==true){
				query2 = "UPDATE Spel SET Toestand_type=? ,  Reaktie_type=? ,   moment_reaktie=?  WHERE `Account_naam_uitdager` = 'nameuitdager' AND `Account_naam_tegenstander=`yourname` ;";
				new Query(query2).set(STATE_REJECTED).set(STATE_ACCEPTED).set( currentdate).exec(); 
			}
			else{
				query2 = "UPDATE Spel SET  ,  Reaktie_type=? ,   moment_reaktie=?  WHERE `Account_naam_uitdager` = 'nameuitdager' AND `Account_naam_tegenstander=`yourname` ;";
				  new Query(query2).set(STATE_ACCEPTED).set( currentdate).exec(); 
			}
			for(int index=0;index < challenge.size();index++ ){	
				String xx=   resultset.getString(4) ;
				if(xx.equals(challenge.get(index)))
				{
					challenge.remove(index);
				}
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
 
