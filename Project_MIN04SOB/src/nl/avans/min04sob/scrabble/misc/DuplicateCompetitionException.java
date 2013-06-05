package nl.avans.min04sob.scrabble.misc;

public class DuplicateCompetitionException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = -9150917004769576665L;

	public DuplicateCompetitionException(){
		super("Gebruiker heeft al een competitie");
	}
}
