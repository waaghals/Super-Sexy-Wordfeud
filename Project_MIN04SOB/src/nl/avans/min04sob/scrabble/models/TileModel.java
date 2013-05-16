package nl.avans.min04sob.scrabble.models;

import java.awt.Dimension;
import java.beans.PropertyChangeEvent;

import javax.swing.JTextField;

import nl.avans.min04sob.scrabble.core.CorePanel;

public class TileModel {
	private String letter;
	private boolean empty = false;

	public TileModel(String letter) {
		if (letter == null) {
			this.empty = true;
		} else {
			this.letter = letter;
		}

	}

	public String getLetter() {
		return letter;
	}

	public void setLetter(String letter) {
		this.letter = letter;
	}

	public boolean isEmpty() {
		return empty;
	}

	public void setEmpty(boolean empty) {
		this.empty = empty;
	}
}
