package nl.avans.min04sob.scrabble.models;

public class MainScreenUserModel {

	private final String username;
	private final int playerid, moderator;
	
	public MainScreenUserModel(String username, int playerid, int moderator){
		this.username = username;
		this.playerid = playerid;
		this.moderator = moderator;
	}
	
	public String getUsername(){
		return username;
	}
	
	public int getPlayerId(){
		return playerid;
	}
	
	public int getModerator(){
		return moderator;
	}
}
