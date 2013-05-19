package nl.avans.min04sob.scrabble.core;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

import javax.swing.JComponent;
import javax.swing.JTable;
import javax.swing.TransferHandler;

import nl.avans.min04sob.scrabble.models.Tile;

public class TileTranfserHandler extends TransferHandler implements
		Transferable {
	
	private static final DataFlavor flavors[] = { new DataFlavor(Tile.class,
			"Tile") };
	private Tile tile;

	public TileTranfserHandler() {
	}

	public Object getTransferData(DataFlavor flavor) {
		if (isDataFlavorSupported(flavor)) {
			return tile;
		}
		return null;
	}

	public DataFlavor[] getTransferDataFlavors() {
		return flavors;
	}

	public boolean isDataFlavorSupported(DataFlavor flavor) {
		return flavors[0].equals(flavor);
	}

	@Override
	public int getSourceActions(JComponent c) {
		return MOVE;
	}

	@Override
	protected Transferable createTransferable(JComponent source) {
		
		//Pure Magic
		tile = (Tile) ((JTable) source).getModel().getValueAt(
				((JTable) source).getSelectedRow(),
				((JTable) source).getSelectedColumn());
		return this;
	}

	@Override
	protected void exportDone(JComponent source, Transferable data, int action) {

		((JTable) source).getModel().setValueAt(new Tile(),
				((JTable) source).getSelectedRow(),
				((JTable) source).getSelectedColumn());

	}

	@Override
	public boolean canImport(TransferSupport support) {
		return true;
	}

	@Override
	public boolean importData(TransferSupport support) {
		JTable jt = (JTable) support.getComponent();
		try {
			jt.setValueAt(
					support.getTransferable().getTransferData(
							new DataFlavor(Tile.class,
									"Tile")), jt.getSelectedRow(),
					jt.getSelectedColumn());
		} catch (UnsupportedFlavorException ex) {
			ex.printStackTrace();

		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return super.importData(support);
	}
}
