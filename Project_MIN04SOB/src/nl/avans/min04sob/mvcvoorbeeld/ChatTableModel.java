package nl.avans.min04sob.mvcvoorbeeld;

import java.util.Date;

import nl.avans.min04sob.scrabble.core.Column;
import nl.avans.min04sob.scrabble.core.CoreTableModel;

public class ChatTableModel extends CoreTableModel {

	private String name;
	private String message;
	private Date time;
	public static final int NAME_COLUMN = 0;
	public static final int MESSAGE_COLUMN = 1;
	public static final int TIME_COLUMN = 2;
	
	public ChatTableModel() {
		addColumn(new Column("Naam", String.class, NAME_COLUMN));
		addColumn(new Column("Bericht", String.class, MESSAGE_COLUMN));
		addColumn(new Column("Tijd", Date.class, TIME_COLUMN));
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		switch (columnIndex) {
		case NAME_COLUMN:
		case MESSAGE_COLUMN:
		case TIME_COLUMN:
			return false;

		default:
			return true;
		}
	}

	@Override
	public void setValueAt(Object newValue, int rowIndex, int columnIndex) {
		Object oldValue = getValueAt(rowIndex, columnIndex);
		ChatTableModel row = (ChatTableModel) getRow(rowIndex);
		boolean newRow = false;
		if (row == null) {
			row = new ChatTableModel();
			newRow = true;
		}

		switch (columnIndex) {
		case NAME_COLUMN:
			row.setName((String) newValue);
			break;
		case MESSAGE_COLUMN:
			row.setMessage((String) newValue);
			break;
		case TIME_COLUMN:
			row.setTime((Date) newValue);
			break;
		default:
			break;
		}

		if (newRow) {
			addRow(row);
		}

		firePropertyChange("chatmessage", oldValue, newValue);
	}

	public void addMessage(String player, String message) {
		int row = getRowCount();
		setValueAt(player, row, NAME_COLUMN);
		setValueAt(message, row, MESSAGE_COLUMN);
		setValueAt(new Date(), row, TIME_COLUMN);
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		ChatTableModel row = (ChatTableModel) getRow(rowIndex);
		if (row != null) {
			switch (columnIndex) {
			case NAME_COLUMN:
				return row.getName();
			case MESSAGE_COLUMN:
				return row.getMessage();
			case TIME_COLUMN:
				return row.getTime();
			}
		}
		return null;
	}

	public String getName() {
		return name;
	}

	public String getMessage() {
		return message;
	}
	
	public Date getTime() {
		return time;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setTime(Date time) {
		this.time = time;
	}

}
