package nl.avans.min04sob.scrabble.models;


public class Tile {
	private String letter;
	private int value;
	private boolean mutatable;
	
	public Tile(){
		letter = "";
		value = 0;
		mutatable = true;
	}

	public Tile(String character, int charValue) {
		letter = character;
		value = charValue;
		mutatable = true;
	}

	public String getLetter() {
		return letter;
	}

	public void setLetter(String letter) {
		this.letter = letter;
	}

	public boolean isEmpty() {
		return letter.equals("");
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
	
	public int getValue(){
		return value;
	}
}
