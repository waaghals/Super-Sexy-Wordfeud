package nl.avans.min04sob.scrabble.misc;

import java.awt.Point;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

import javax.swing.JComponent;
import javax.swing.JTable;
import javax.swing.TransferHandler;
import javax.swing.table.TableModel;

import nl.avans.min04sob.scrabble.models.Tile;

public class TileTranfserHandler extends TransferHandler {

	private static final DataFlavor tileFlavor = new DataFlavor(Tile.class,
			"Tile");

	public TileTranfserHandler() {
	}

	@Override
	public boolean canImport(TransferSupport support) {
		Tile sourceTile;
		try {
			sourceTile = (Tile) support.getTransferable().getTransferData(
					tileFlavor);
			if (sourceTile != null && !sourceTile.isMutatable()) {
				System.out.println("Source tile not mutable or null");
				return false;
			}
			// support.setShowDropLocation(true);
			JTable table = (JTable) support.getComponent();
			TableModel model = table.getModel();
			int row = table.getSelectedRow();
			int col = table.getSelectedColumn();
			Tile tile = (Tile) model.getValueAt(row, col);

			if (tile == null) {
				System.out.println("Drop loc. is valid because it is null");
				return true;
			}

			if (tile.equals(sourceTile)) {
				System.out
						.println("Drop loc. is valid because it is the same as the source dest");
				return true;
			}
		} catch (UnsupportedFlavorException | IOException e) {
			// e.printStackTrace();
		}

		return false;
		// return tile.isMutatable();
	}

	@Override
	protected Transferable createTransferable(JComponent source) {
		JTable sourceTable = (JTable) source;
		int row = sourceTable.getSelectedRow();
		int col = sourceTable.getSelectedColumn();

		Tile sourceTile = (Tile) sourceTable.getModel().getValueAt(row, col);
		return sourceTile;
	}

	@Override
	protected void exportDone(JComponent source, Transferable data, int action) {
		Tile sourceTile;
		try {
			JTable table = (JTable) source;
			int sourceRow = table.getSelectedRow();
			int sourceCol = table.getSelectedColumn();
			TableModel model = table.getModel();
			if(data!=null)
			{
			sourceTile = (Tile) data.getTransferData(tileFlavor);
			if (sourceTile != null && sourceTile.isMutatable()) {
				System.out.println("Setting value at: R" + sourceRow + " C"
						+ sourceCol + " to null");
				model.setValueAt(null, sourceRow, sourceCol);
			} else {
				//Put the sourceTile Back
				model.setValueAt(sourceTile, sourceRow, sourceCol);
			}
			table.clearSelection();
			}
		} catch (UnsupportedFlavorException | IOException e) {
			// TODO Automatisch gegenereerd catch-blok
			e.printStackTrace();
		}
	}

	@Override
	public int getSourceActions(JComponent c) {
		return MOVE;
	}

	@Override
	public boolean importData(TransferSupport support) {
		if (!canImport(support)) {
			return false;
		}
		JTable table = (JTable) support.getComponent();
		Tile newTile;
		try {
			newTile = (Tile) support.getTransferable().getTransferData(
					tileFlavor);
		} catch (UnsupportedFlavorException | IOException e) {
			e.printStackTrace();
			return false;
		}

		TableModel model = table.getModel();
		int row = table.getSelectedRow();
		int col = table.getSelectedColumn();

		model.setValueAt(newTile, row, col);

		return super.importData(support);
	}
}
