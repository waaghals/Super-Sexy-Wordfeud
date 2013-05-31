package nl.avans.min04sob.scrabble.core;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

import javax.swing.JComponent;
import javax.swing.JTable;
import javax.swing.TransferHandler;
import javax.swing.table.TableModel;

import nl.avans.min04sob.scrabble.models.Tile;

public class TileTranfserHandler extends TransferHandler implements
		Transferable {

	private static final DataFlavor flavors[] = { new DataFlavor(Tile.class,
			"Tile") };
	private Tile sourceTile;
	private JTable sourceTable;

	public TileTranfserHandler() {
	}

	@Override
	public Object getTransferData(DataFlavor flavor) {
		if (isDataFlavorSupported(flavor)) {
			return sourceTile;
		}
		return null;
	}

	@Override
	public DataFlavor[] getTransferDataFlavors() {
		return flavors;
	}

	@Override
	public boolean isDataFlavorSupported(DataFlavor flavor) {
		return flavors[0].equals(flavor);
	}

	@Override
	public int getSourceActions(JComponent c) {
		return MOVE;
	}

	@Override
	protected Transferable createTransferable(JComponent source) {
		sourceTable = (JTable) source;
		int row = sourceTable.getSelectedRow();
		int col = sourceTable.getSelectedColumn();

		sourceTile = (Tile) sourceTable.getModel().getValueAt(row, col);
		return this;
	}

	@Override
	protected void exportDone(JComponent source, Transferable data, int action) {
		JTable table = (JTable) source;
		int row = table.getSelectedRow();
		int col = table.getSelectedColumn();
		if(sourceTile != null){
			table.getModel().setValueAt(new Tile(), row, col);
		}

		table.clearSelection();
	}

	@Override
	public boolean canImport(TransferSupport support) {
		if(sourceTile != null && !sourceTile.isMutatable()){
			return false;
		}
		support.setShowDropLocation(true);
		JTable table = (JTable) support.getComponent();
		TableModel model = table.getModel();
		int row = table.getSelectedRow();
		int col = table.getSelectedColumn();
		if(row == -1 || col == -1){
			return true;
		}

		Tile tile = (Tile) model.getValueAt(row, col);
		
		if (tile == sourceTile) {
			return true;
		}
		if (tile == null) {
			return true;
		}
		return tile.isMutatable();
	}

	@Override
	public boolean importData(TransferSupport support) {
		if(!canImport(support)){
			return false;
		}
		JTable table = (JTable) support.getComponent();
		Tile newTile;
		try {

			newTile = (Tile) support.getTransferable().getTransferData(
					new DataFlavor(Tile.class, "Tile"));
		} catch (UnsupportedFlavorException | IOException e) {
			// TODO Automatisch gegenereerd catch-blok
			e.printStackTrace();
			return false;
		}

		TableModel model = table.getModel();
		int row = table.getSelectedRow();
		int col = table.getSelectedColumn();

		table.setValueAt(newTile, row, col);

		return super.importData(support);
	}
}
