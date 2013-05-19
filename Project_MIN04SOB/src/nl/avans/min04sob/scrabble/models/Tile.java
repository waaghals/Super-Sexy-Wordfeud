package nl.avans.min04sob.scrabble.models;


public class Tile {
	private String letter;
	private boolean mutatable;

	public Tile(String letter) {
		this.letter = letter;
		mutatable = true;
	}

	public String getLetter() {
		return letter;
	}

	public void setLetter(String letter) {
		this.letter = letter;
	}

	public boolean isEmpty() {
		return letter == null;
	}

	public void setEmpty(boolean empty) {
		if(empty){
			letter = null;
		}
	}
	
	public boolean isMutatable(){
		return mutatable;
	}
	
	public void lock(){
		mutatable = false;
	}
	
	public String toString(){
		return letter;
	}
}
