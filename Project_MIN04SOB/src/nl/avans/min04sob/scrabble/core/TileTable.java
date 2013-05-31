package nl.avans.min04sob.scrabble.core;

import java.awt.Point;
import java.awt.event.MouseEvent;

import javax.swing.JTable;

import nl.avans.min04sob.scrabble.models.Tile;

public class TileTable extends JTable {
	@Override
	public String getToolTipText(MouseEvent event) {
		Point p = event.getPoint();
		// Locate the renderer under the event location
		int colIndex = columnAtPoint(p);
		int rowIndex = rowAtPoint(p);
		
		Tile tile = (Tile) getModel().getValueAt(rowIndex, colIndex);
		if (tile != null) {
			return "Waarde: " + tile.getValue();
		}

		return "";
		
	}
}
