package nl.avans.min04sob.scrabble.core;

import java.util.ArrayList;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

public abstract class CoreTableModel extends CoreModel implements TableModel {

	private ArrayList<Object> datalist = new ArrayList<Object>();
	ArrayList<Column> columns = new ArrayList<Column>();

	@Override
	public int getRowCount() {
		return datalist.size();
	}

	@Override
	public int getColumnCount() {
		return columns.size();
	}

	@Override
	public String getColumnName(int columnIndex) {
		return columns.get(columnIndex).getName();
	}

	@Override
	public abstract boolean isCellEditable(int rowIndex, int columnIndex);

	@Override
	public abstract Object getValueAt(int rowIndex, int columnIndex);

	@Override
	public abstract void setValueAt(Object newValue, int rowIndex,
			int columnIndex);

	protected Object getRow(int rowIndex) {
		// Check out of bounds
		if (rowIndex + 1 > getRowCount()) {
			return null;
		}
		return datalist.get(rowIndex);
	}
	
	protected void addRow(Object newRow){
		datalist.add(newRow);
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		return columns.get(columnIndex).getClassType();
	}

	@Override
	public void addTableModelListener(TableModelListener l) {
		// Handled by CoreModel
	}

	@Override
	public void removeTableModelListener(TableModelListener l) {
		// Handled by CoreModel
	}

	public String[] getColumnNames() {
		String[] returnArray = new String[columns.size()];
		for (int i = 0; i < columns.size(); i++) {
			returnArray[i] = columns.get(i).getName();
		}
		return returnArray;
	}

	@SuppressWarnings("rawtypes")
	public Class[] getColumnClass() {
		Class[] returnArray = new Class[columns.size()];
		for (int i = 0; i < columns.size(); i++) {
			returnArray[i] = columns.get(i).getClassType();
		}
		return returnArray;
	}

	protected void addColumn(Column column) {
		columns.add(column);
	}
}
