package nl.avans.min04sob.scrabble.models;

public enum Role {
	OBSERVER("observer"), 
	PLAYER("player"), 
	ADMINISTRATOR("administrator"), 
	MODERATOR("moderator");

	private String role;

	Role(String role) {
		this.role = role;
	}

	public String toString() {
		return role;
	}
}
