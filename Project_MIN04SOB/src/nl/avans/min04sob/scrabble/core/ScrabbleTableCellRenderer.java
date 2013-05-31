package nl.avans.min04sob.scrabble.core;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Point;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
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
	private ImageIcon dlImage;
	private ImageIcon tlImage;
	private ImageIcon dwImage;
	private ImageIcon twImage;
	private ImageIcon starImage;
	private String dlURL = "/images/dl.png";
	private String tlURL = "/images/tl.png";
	private String dwURL = "/images/dw.png";
	private String twURL = "/images/tw.png";
	private String starURL = "/images/star.png";
	

	public ScrabbleTableCellRenderer(BoardModel model) {
		super();
		
		setHorizontalAlignment(SwingConstants.CENTER);
		setVerticalAlignment(SwingConstants.CENTER);

		boardModel = model;

		red = new Color(227, 64, 91);
		lightRed = new Color(255, 179, 206);
		blue = new Color(8, 99, 240);
		lightBlue = new Color(191, 244, 233);
		beige = new Color(255, 255, 200);
	}

	protected ImageIcon createImageIcon(String path,
            String description) {
		URL imgURL = getClass().getResource(path);
		if (imgURL != null) {
			return new ImageIcon(imgURL, description);
		} else {
			System.err.println("Couldn't find file: " + path);
			return null;
		}
	}
	
	@Override
	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int col) {
		setImages();
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
		     //   c = new JLabel(dlImage);
		     //    boardModel.setValueAt(new Tile("DL", true), row, col);
		         break;
		       case BoardModel.TL:
		        c.setBackground(blue);
		         c.setForeground(lightBlue);
		        
		    //    c = new JLabel(tlImage);
		    //     boardModel.setValueAt(new Tile("TL", true), row, col);
		         break;
		       case BoardModel.DW:
		        c.setBackground(lightRed);
		        c.setForeground(red);
		        //c = new JLabel(dwImage);
		        // boardModel.setValueAt(new Tile("DW", true), row, col);
		         break;
		       case BoardModel.TW:
		        c.setBackground(red);
		        c.setForeground(lightRed);
		        //c = new JLabel(twImage);
		        // boardModel.setValueAt(new Tile("TW", true), row, col);
		         break;
		       case BoardModel.STAR:
		        c.setBackground(Color.WHITE);
		        c.setForeground(Color.GREEN);
		        //c = new JLabel(starImage);
		        // boardModel.setValueAt(new Tile("*", true), row, col);
		         break;
		       case BoardModel.EMPTY:
			default:
				c.setBackground(Color.WHITE);
				c.setForeground(Color.BLACK);
				//boardModel.setValueAt(new Tile(null, 0, Tile.MUTATABLE), row, col);
				break;
			}

		}

		return c;
	}
	
	private void setImages() {
			dlImage = createImageIcon(dlURL, "dl");
			tlImage = createImageIcon(tlURL, "tl");
			dwImage = createImageIcon(dwURL, "dw");
			twImage = createImageIcon(twURL, "tw");
			starImage = createImageIcon(starURL, "star");
	}
}
