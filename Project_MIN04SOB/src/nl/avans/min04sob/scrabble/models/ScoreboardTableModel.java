package nl.avans.min04sob.scrabble.models;

import nl.avans.min04sob.scrabble.core.Column;
import nl.avans.min04sob.scrabble.core.CoreTableModel;

public class ScoreboardTableModel extends CoreTableModel {

	private String name;
	private Integer score;
	public static final int NAME_COLUMN = 0;
	public static final int SCORE_COLUMN = 1;

	public ScoreboardTableModel() {
		addColumn(new Column("Naam", String.class, NAME_COLUMN));
		addColumn(new Column("Score", Integer.class, SCORE_COLUMN));
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		switch (columnIndex) {
		case NAME_COLUMN:
		case SCORE_COLUMN:
			return false;

		default:
			return true;
		}
	}

	@Override
	public void setValueAt(Object newValue, int rowIndex, int columnIndex) {
		Object oldValue = getValueAt(rowIndex, columnIndex);
		ScoreboardTableModel row = (ScoreboardTableModel) getRow(rowIndex);
		boolean newRow = false;
		if (row == null) {
			row = new ScoreboardTableModel();
			newRow = true;
		}

		switch (columnIndex) {
		case NAME_COLUMN:
			row.setName((String) newValue);
			break;
		case SCORE_COLUMN:
			row.setScore((Integer) newValue);
			break;
		default:
			break;
		}

		if (newRow) {
			addRow(row);
		}

		firePropertyChange("scoreboardtable", oldValue, newValue);
	}

	public void addScore(String player, Integer score) {
		int row = getRowCount();
		setValueAt(player, row, NAME_COLUMN);
		setValueAt(score, row, SCORE_COLUMN);
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		ScoreboardTableModel row = (ScoreboardTableModel) getRow(rowIndex);
		if (row != null) {
			switch (columnIndex) {
			case NAME_COLUMN:
				return row.getName();

			case SCORE_COLUMN:
				return row.getScore();
			}
		}
		return null;
	}

	public String getName() {
		return name;
	}

	public Integer getScore() {
		return score;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setScore(Integer score) {
		this.score = score;
	}
}
