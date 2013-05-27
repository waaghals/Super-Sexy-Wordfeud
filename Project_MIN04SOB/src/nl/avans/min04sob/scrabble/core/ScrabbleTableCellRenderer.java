package nl.avans.min04sob.scrabble.core;

import java.awt.Color;
import java.awt.Component;
import java.awt.Point;

import javax.swing.JLabel;
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
		beige = new Color(255, 255, 200);
	}

	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int col) {

		Component c = super.getTableCellRendererComponent(table, value,
				isSelected, hasFocus, row, col);
		JLabel label = (JLabel) c;
		
		Tile tile = (Tile) boardModel.getValueAt(row, col);
		
		int multiplier = boardModel.getMultiplier(new Point(row, col));

		if (tile != null && !tile.isMutatable()) {
			c.setBackground(beige);
			c.setForeground(Color.BLACK);
		} else {

			switch (multiplier) {
			case BoardModel.DL:
				c.setBackground(lightBlue);
				c.setForeground(blue);
				boardModel.setValueAt(new Tile("DL", true), row, col);
				break;
			case BoardModel.TL:
				c.setBackground(blue);
				c.setForeground(lightBlue);
				boardModel.setValueAt(new Tile("TL", true), row, col);
				break;
			case BoardModel.DW:
				c.setBackground(lightRed);
				c.setForeground(red);
				boardModel.setValueAt(new Tile("DW", true), row, col);
				break;
			case BoardModel.TW:
				c.setBackground(red);
				c.setForeground(lightRed);
				boardModel.setValueAt(new Tile("TW", true), row, col);
				break;
			case BoardModel.STAR:
				c.setBackground(Color.WHITE);
				c.setForeground(Color.GREEN);
				boardModel.setValueAt(new Tile("*", true), row, col);
				break;
			case BoardModel.EMPTY:
			default:
				c.setBackground(Color.WHITE);
				c.setForeground(Color.BLACK);
				// boardModel.setValueAt(" ", row, col);
				break;
			}

		}

		return c;
	}
}
