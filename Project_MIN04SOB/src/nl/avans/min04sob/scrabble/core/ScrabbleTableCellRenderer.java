package nl.avans.min04sob.scrabble.core;

import java.awt.Color;
import java.awt.Component;
import java.awt.Point;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import nl.avans.min04sob.scrabble.models.BoardModel;
import nl.avans.min04sob.scrabble.models.Tile;

public class ScrabbleTableCellRenderer extends DefaultTableCellRenderer {

	private BoardModel boardModel;
	private Color red;
	private Color lightRed;
	private Color blue;
	private Color lightBlue;
	private Color beige;
	
	public ScrabbleTableCellRenderer(BoardModel model) {
		super();
		setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
		setVerticalAlignment(DefaultTableCellRenderer.CENTER);

		boardModel = model;
		
		red = new Color(227, 64, 91);
		lightRed = new Color(255, 179, 206);
		blue = new Color(8, 99, 240);
		lightBlue = new Color(191, 244, 233);
		beige = new Color(255, 255, 253);
	}

	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int col) {

		Component c = super.getTableCellRendererComponent(table, value,
				isSelected, hasFocus, row, col);

		int multiplier = boardModel.getMultiplier(new Point(row, col));
		switch (multiplier) {
		case BoardModel.DL:
			c.setBackground(lightBlue);
			c.setForeground(blue);
			break;
		case BoardModel.TL:
			c.setBackground(blue);
			c.setForeground(lightBlue);
			break;
		case BoardModel.DW:
			c.setBackground(lightRed);
			c.setForeground(red);
			break;
		case BoardModel.TW:
			c.setBackground(red);
			c.setForeground(lightRed);
			break;
		case BoardModel.STAR:
			c.setBackground(lightRed);
			c.setForeground(red);
			break;
		case BoardModel.EMPTY:
		default:
			c.setBackground(beige);
			c.setForeground(Color.BLACK);
			break;
		}
		
		Tile tile = (Tile) boardModel.getValueAt(row, col);
		if(!tile.isMutatable()){
			c.setEnabled(false);
		}
		return c;
	}
}
