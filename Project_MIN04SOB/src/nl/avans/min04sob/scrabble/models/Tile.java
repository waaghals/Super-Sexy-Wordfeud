package nl.avans.min04sob.scrabble.models;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;

public class Tile implements Transferable {
	private String letter;
	private int value;
	private boolean mutatable;
	private int tileId;
	public final static boolean MUTATABLE = true;
	public final static boolean NOT_MUTATABLE = false;
	private final static DataFlavor flavors[] = { new DataFlavor(Tile.class,
			"Tile") };

	public Tile() {
		letter = "";
		value = 0;
		mutatable = true;
	}

	public Tile(String letter, int value, boolean mutatable,int id) {
		this.letter = letter;
		this.value = value;
		this.mutatable = mutatable;
		this.tileId = id;
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
	public int getTileId(){
		return this.tileId;
	}

	public void setEmpty(boolean empty) {
		if (empty) {
			letter = "";
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
