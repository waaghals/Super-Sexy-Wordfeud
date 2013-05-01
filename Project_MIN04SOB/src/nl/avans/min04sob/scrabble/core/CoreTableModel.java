package nl.avans.min04sob.scrabble.core;

import java.util.ArrayList;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

public abstract class CoreTableModel extends CoreModel implements TableModel {

	private ArrayList<ArrayList<Object>> datalist = new ArrayList<ArrayList<Object>>();
	private ArrayList<Column> columns;

	public void setColumns(ArrayList<Column> columns) {
		this.columns = columns;
	}

	@Override
	public int getRowCount() {
		return datalist.size();
	}

	@Override
	public int getColumnCount() {
		if (columns != null) {
			return columns.size();
		}

		if (getRowCount() > 0) {
			ArrayList<Object> v = datalist.get(0);
			return v.size();
		}

		return 0;
	}

	@Override
	public String getColumnName(int columnIndex) {
		return columns.get(columnIndex).getName();
	}

	@Override
	public abstract boolean isCellEditable(int rowIndex, int columnIndex);

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {

		if (columnIndex > getColumnCount()) {
			return null;
		}

		ArrayList<Object> row = getRow(rowIndex);
		if (row != null) {
			try {
				return row.get(columnIndex);
			} catch (IndexOutOfBoundsException e) {
				return null;
			}
		}
		return null;
	}

	@Override
	public void setValueAt(Object newValue, int rowIndex, int columnIndex) {
		if ((rowIndex < 0) || (columnIndex < 0)) {
			throw new IllegalArgumentException("Invalid row/column setting");
		}

		ArrayList<Object> row = getRow(rowIndex);
		if (row != null) {
			try {
				row.remove(columnIndex);
			} catch (IndexOutOfBoundsException e) {
			}
			row.add(columnIndex, newValue);
		} else {
			row = new ArrayList<Object>();
			row.add(columnIndex, newValue);
			datalist.add(row);
		}

	}

	private ArrayList<Object> getRow(int rowIndex) {
		if (getRowCount() == 0) {
			return null;
		}

		if (rowIndex + 1 > getRowCount()) {
			return null;
		}
		return datalist.get(rowIndex);
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		if (columns != null) {
			return columns.get(columnIndex).getClassType();
		}

		// Fallback to string
		return String.class;
	}

	@Override
	public void addTableModelListener(TableModelListener l) {
		// Handled by CoreModel
	}

	@Override
	public void removeTableModelListener(TableModelListener l) {
		// Handled by CoreModel
	}
}
