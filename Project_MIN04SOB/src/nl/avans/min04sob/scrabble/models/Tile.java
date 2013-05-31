package nl.avans.min04sob.scrabble.models;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

public class Tile implements Transferable {
	private String letter;
	private int value;
	private boolean mutatable;
	public final static boolean MUTATABLE = true;
	public final static boolean NOT_MUTATABLE = false;
	private final static DataFlavor flavors[] = { new DataFlavor(Tile.class,
			"Tile") };

	public Tile() {
		letter = "";
		value = 0;
		mutatable = true;
	}

	@Deprecated
	public Tile(String character, boolean mutable) {
		letter = character;
		value = 0;
		this.mutatable = mutable;
	}

	@Deprecated
	public Tile(String character, int charValue) {
		letter = character;
		value = charValue;
		mutatable = true;
	}

	public Tile(String letter, int value, boolean mutatable) {
		this.letter = letter;
		this.value = value;
		this.mutatable = mutatable;
	}

	public String getLetter() {
		if (!isEmpty()) {
			return letter;
		} else {
			return "";
		}
	}

	public int getValue() {
		return value;
	}

	public boolean isEmpty() {
		return letter.equals("");
	}

	public boolean isMutatable() {
		return mutatable;
	}

	public void lock() {
		mutatable = false;
	}

	public void setEmpty(boolean empty) {
		if (empty) {
			letter = null;
		}
	}

	public void setLetter(String letter) {
		this.letter = letter;
	}

	@Override
	public String toString() {
		return letter;
	}

	@Override
	public Object getTransferData(DataFlavor flavor) {
		if (isDataFlavorSupported(flavor)) {
			return this;
		}
		return null;
	}

	@Override
	public DataFlavor[] getTransferDataFlavors() {
		return flavors;
	}

	@Override
	public boolean isDataFlavorSupported(DataFlavor value) {
		for (DataFlavor flavor : flavors) {
			if (flavor == value || value != null && value.equals(flavor)) {
				return true;
			}
		}
		return false;
	}

}
