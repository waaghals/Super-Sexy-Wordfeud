package nl.avans.min04sob.scrabble.models;

import nl.avans.min04sob.scrabble.core.CoreModel;

public class GameModel extends CoreModel {

	private CompetitionModel competition;
	private AccountModel opponent;
	
	public GameModel(AccountModel opponent, CompetitionModel competition){
		this.opponent = opponent;
		this.competition = competition;
	}
	@Override
	public void update() {
		// TODO Automatisch gegenereerde methodestub

	}
	
	public String toString(){
		return competition.getName() + " " + opponent.getUsername();
	}

}
