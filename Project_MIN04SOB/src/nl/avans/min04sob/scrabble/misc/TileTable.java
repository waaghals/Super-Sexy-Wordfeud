package nl.avans.min04sob.scrabble.misc;

import java.awt.Point;
import java.awt.event.MouseEvent;

import javax.swing.JTable;

import nl.avans.min04sob.scrabble.models.BoardModel;
import nl.avans.min04sob.scrabble.models.Tile;

public class TileTable extends JTable {
	private StringBuilder toolTip;

	@Override
	public String getToolTipText(MouseEvent event) {
		toolTip =  new StringBuilder();
		Point p = event.getPoint();
		// Locate the renderer under the event location
		int colIndex = columnAtPoint(p);
		int rowIndex = rowAtPoint(p);

		BoardModel model = (BoardModel) getModel();
		Tile tile = (Tile) model.getValueAt(rowIndex, colIndex);

		int multiplier = model.getMultiplier(new Point(rowIndex, colIndex));
		switch (multiplier) {
		case BoardModel.DL:
			toolTip.append("(DL) ");
			break;
		case BoardModel.TL:
			toolTip.append("(TL) ");
			break;
		case BoardModel.DW:
			toolTip.append("(DW) ");
			break;
		case BoardModel.TW:
			toolTip.append("(TW) ");
			break;
		}

		if (tile != null) {
			toolTip.append("Waarde:" + tile.getValue());
		}

		if(toolTip.length() == 0){
			return null;
		}
		
		return toolTip.toString();

	}
}
