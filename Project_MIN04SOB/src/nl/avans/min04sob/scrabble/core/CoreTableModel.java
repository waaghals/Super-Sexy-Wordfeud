package nl.avans.min04sob.scrabble.core;

import java.util.ArrayList;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

public abstract class CoreTableModel extends CoreModel implements TableModel {

	protected Object[][] data;
	ArrayList<Column> columns = new ArrayList<Column>();

	public void initDataArray(int x, int y) {
		data = new Object[x][y];
	}

	@Override
	public int getRowCount() {
		return data.length;
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
		return data[rowIndex];
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

	public Object[][] getData() {
		int numRows = getRowCount();
		int numCols = getColumnCount();
		
		Object[][] tableData = new Object[numRows][numCols];
		for (int i = 0; i < numRows; i++)
			for (int j = 0; j < numCols; j++)
				tableData[i][j] = getValueAt(i, j);
		return tableData;
	}
}
