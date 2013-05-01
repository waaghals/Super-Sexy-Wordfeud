package nl.avans.min04sob.scrabble.views;

import java.beans.PropertyChangeEvent;

import javax.swing.JTable;
import javax.swing.table.TableModel;

import nl.avans.min04sob.scrabble.core.CoreView;

public class ChatTable extends JTable implements CoreView {

	@Override
	public void modelPropertyChange(PropertyChangeEvent evt) {
		switch (evt.getPropertyName()) {
		case "chatmessage":
			revalidate();
			break;

		default:
			break;
		}
	}

	public ChatTable() {
		super();
	}

	public ChatTable(TableModel dm) {
		super(dm);
	}
}
