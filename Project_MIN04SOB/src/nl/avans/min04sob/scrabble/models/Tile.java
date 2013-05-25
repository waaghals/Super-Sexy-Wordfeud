package nl.avans.min04sob.scrabble.models;


public class Tile {
	private String letter;
	private int value;
	private boolean mutatable;
	public final static boolean MUTATABLE = true;
	public final static boolean NOT_MUTATABLE = false;
	
	
	public Tile(){
		letter = "";
		value = 0;
		mutatable = true;
	}
	
	public Tile(String letter, int value, boolean mutatable) {
		this.letter = letter;
		this.value = value;
		this.mutatable = mutatable;
	}
	
	@Deprecated public Tile(String character, boolean mutable){
		letter = character;
		value = 0;
		this.mutatable = mutable;
	}

	@Deprecated public Tile(String character, int charValue) {
		letter = character;
		value = charValue;
		mutatable = true;
	}

	public String getLetter() {
		if(!isEmpty()){  
			return letter;
		}
		else{
			return "";
		}
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
