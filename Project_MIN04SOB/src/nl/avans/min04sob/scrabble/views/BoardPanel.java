package nl.avans.min04sob.scrabble.views;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.DnDConstants;
import java.beans.PropertyChangeEvent;
import java.io.IOException;

import javax.swing.DropMode;
import javax.swing.JComponent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.TransferHandler;
import javax.swing.table.DefaultTableModel;

import nl.avans.min04sob.scrabble.core.CorePanel;

public class BoardPanel extends CorePanel {

	@Override
	public void modelPropertyChange(PropertyChangeEvent evt) {
		// TODO Auto-generated method stub

	}

	public BoardPanel() {
		String dataValues[][] = {
				{ "1", "TW", "", "", "DL", "", "", "", "TW", "", "", "", "",
						"", "", "TW" },
				{ "2", "", "DW", "", "", "", "TL", "", "", "", "TL", "", "",
						"", "DW", "" },
				{ "3", "", "", "DW", "", "", "", "DL", "", "DL", "", "", "",
						"DW", "", "" },
				{ "4", "DL", "", "", "DW", "", "", "", "DL", "", "", "", "DW",
						"", "", "DL" },
				{ "5", "", "", "", "", "DW", "", "", "", "", "", "DW", "", "",
						"", "" },
				{ "6", "", "TL", "", "", "", "TL", "", "", "", "TL", "", "",
						"", "TL", "" },
				{ "7", "", "", "DL", "", "", "", "DL", "", "DL", "", "", "",
						"DL", "", "" },
				{ "8", "TW", "", "", "DL", "", "", "", "*", "", "", "", "DL",
						"", "", "TW" },
				{ "9", "", "", "DL", "", "", "", "DL", "", "DL", "", "", "",
						"DL", "", "" },
				{ "10", "", "TL", "", "", "", "TL", "", "", "", "TL", "", "",
						"", "TL", "" },
				{ "11", "", "", "", "", "DW", "", "", "", "", "", "DW", "", "",
						"", "" },
				{ "12", "DL", "", "", "DW", "", "", "", "DL", "", "", "", "DW",
						"", "", "DL" },
				{ "13", "", "", "DW", "", "", "", "DL", "", "DL", "", "", "",
						"DW", "14", "" },
				{ "14", "", "DW", "", "", "", "TL", "", "", "", "TL", "", "",
						"", "DW", "" },
				{ "15", "TW", "", "", "DL", "", "", "", "TW", "", "", "", "DL",
						"", "", "TW" } };

		Character columnNames[] = { ' ', 'A', 'B', 'C', 'D', 'E', 'F', 'G',
				'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O' };

		// Create a new table instance
		JTable table = new JTable(dataValues, columnNames);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setColumnSelectionAllowed(true);
		table.setDragEnabled(true);
		table.setDropMode(DropMode.USE_SELECTION);
		table.setTransferHandler(new TransferHandler() {

			public int getSourceActions(JComponent c) {
				return DnDConstants.ACTION_COPY_OR_MOVE;
			}

			public Transferable createTransferable(JComponent comp) {
				JTable table = (JTable) comp;
				int row = table.getSelectedRow();
				int col = table.getSelectedColumn();

				String value = (String) table.getModel().getValueAt(row, col);
				StringSelection transferable = new StringSelection(value);
				table.getModel().setValueAt(null, row, col);
				return transferable;
			}

			public boolean canImport(TransferHandler.TransferSupport info) {
				if (!info.isDataFlavorSupported(DataFlavor.stringFlavor)) {
					return false;
				}

				return true;
			}

			public boolean importData(TransferSupport support) {

				if (!support.isDrop()) {
					return false;
				}

				if (!canImport(support)) {
					return false;
				}

				JTable table = (JTable) support.getComponent();
				DefaultTableModel tableModel = (DefaultTableModel) table
						.getModel();

				JTable.DropLocation dl = (JTable.DropLocation) support
						.getDropLocation();

				int row = dl.getRow();
				int col = dl.getColumn();

				String data;
				try {
					data = (String) support.getTransferable().getTransferData(
							DataFlavor.stringFlavor);
				} catch (UnsupportedFlavorException e) {
					return false;
				} catch (IOException e) {
					return false;
				}

				tableModel.setValueAt(data, row, col);

				return true;
			}

		});

		// Add the table to a scrolling pane
		JScrollPane scrollPane = new JScrollPane(table);
		add(scrollPane);
	}
}
