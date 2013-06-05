package nl.avans.min04sob.scrabble.misc;

public class DuplicateCompetitionException extends Exception {
	public DuplicateCompetitionException(){
		super("Gebruiker heeft al een competitie");
	}
}
