package nl.avans.min04sob.scrabble.views;

import java.beans.PropertyChangeEvent;

import javax.swing.JTable;
import javax.swing.table.TableModel;

import nl.avans.min04sob.scrabble.core.CoreView;

public class ScoreboardTable extends JTable implements CoreView {

	@Override
	public void modelPropertyChange(PropertyChangeEvent evt) {
		switch (evt.getPropertyName()) {
		case "scoreboardtable":
			revalidate();
			break;

		default:
			break;
		}
	}

	public ScoreboardTable() {
		super();
	}

	public ScoreboardTable(TableModel dm) {
		super(dm);
	}

}
