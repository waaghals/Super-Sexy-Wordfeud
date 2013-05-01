package nl.avans.min04sob.scrabble.models;

import java.util.ArrayList;

import nl.avans.min04sob.scrabble.core.Column;
import nl.avans.min04sob.scrabble.core.CoreTableModel;

public class ScoreboardTableModel extends CoreTableModel {

	public static final int NAME_COLUMN = 0;
	public static final int SCORE_COLUMN = 1;

	public ScoreboardTableModel() {
		ArrayList<Column> columns = new ArrayList<Column>();
		columns.add(new Column("Naam", String.class));
		columns.add(new Column("Score", Integer.class));
		setColumns(columns);
		
		addScore("Patrick", new Integer(1000));
		addScore("Patrick", new Integer(1000));
		addScore("Patrick", new Integer(1000));
		addScore("Patrick", new Integer(1000));
		addScore("Patrick", new Integer(1000));
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}

	@Override
	public void setValueAt(Object newValue, int rowIndex, int columnIndex) {
		Object oldValue = getValueAt(rowIndex, columnIndex);
		super.setValueAt(newValue, rowIndex, columnIndex);
		firePropertyChange("scoreboardtable", oldValue, newValue);
	}

	public void addScore(String player, Integer score) {
		int row = getRowCount();
		setValueAt(player, row, NAME_COLUMN);
		setValueAt(score, row, SCORE_COLUMN);
	}
}
