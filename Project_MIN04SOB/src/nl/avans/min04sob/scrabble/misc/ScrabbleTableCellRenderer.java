package nl.avans.min04sob.scrabble.misc;

import java.awt.Color;
import java.awt.Component;
import java.awt.Point;
import java.net.URL;

import javax.swing.ImageIcon;
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
	private Color green;
	private Color lightGreen;
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
	
	private static final double BLEND_RATIO = 0.6;

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
		green = new Color(5, 95, 5);
		lightGreen = new Color(180, 255, 180);
	}
	

	protected ImageIcon createImageIcon(String path, String description) {
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

		Color tileForeground;
		Color tileBackground;
		Color multiplierForeground;
		Color multiplierBackground;

		Tile tile = (Tile) boardModel.getValueAt(row, col);

		int multiplier = boardModel.getMultiplier(new Point(row, col));

		switch (multiplier) {
		case BoardModel.DL:
			multiplierForeground = blue;
			multiplierBackground = lightBlue;
			break;
		case BoardModel.TL:
			multiplierForeground = lightBlue;
			multiplierBackground = blue;
			break;
		case BoardModel.DW:
			multiplierForeground = red;
			multiplierBackground = lightRed;
			break;
		case BoardModel.TW:
			multiplierForeground = lightRed;
			multiplierBackground = red;
			break;
		case BoardModel.STAR:
			multiplierForeground = Color.WHITE;
			multiplierBackground = Color.YELLOW;
			break;
		case BoardModel.EMPTY:
		default:
			multiplierForeground = Color.BLACK;
			multiplierBackground = Color.WHITE;
			break;
		}

		
		if (tile != null && !tile.isMutatable()) {
			//Tiles can't be moved, give them a beige color
			tileForeground = Color.BLACK;
			tileBackground = beige;
		} else if (tile == null) {
			//No tile is present, keep it the primairy color
			tileForeground = multiplierForeground;
			tileBackground = multiplierBackground;
		} else {
			//Tile is currently being put
			tileForeground = green;
			tileBackground = lightGreen;
		}

		Color foreground = blend(tileForeground, multiplierForeground,
				BLEND_RATIO);
		Color background = blend(tileBackground, multiplierBackground,
				BLEND_RATIO);

		c.setForeground(foreground);
		c.setBackground(background);
		return c;
		
	}

	private void setImages() {
		dlImage = createImageIcon(dlURL, "dl");
		tlImage = createImageIcon(tlURL, "tl");
		dwImage = createImageIcon(dwURL, "dw");
		twImage = createImageIcon(twURL, "tw");
		starImage = createImageIcon(starURL, "star");
	}

	/**
	 * Blend two colors.
	 * 
	 * @param first
	 *            First color to blend.
	 * @param second
	 *            Second color to blend.
	 * @param ratio
	 *            Blend ratio. 0.5 will give even blend, 1.0 will return first,
	 *            0.0 will return second and so on.
	 * @return Blended color.
	 */
	public static Color blend(Color first, Color second, double ratio) {
		float r = (float) ratio;
		float ir = (float) 1.0 - r;

		float rgb1[] = new float[3];
		float rgb2[] = new float[3];

		first.getColorComponents(rgb1);
		second.getColorComponents(rgb2);

		Color color = new Color(rgb1[0] * r + rgb2[0] * ir, rgb1[1] * r
				+ rgb2[1] * ir, rgb1[2] * r + rgb2[2] * ir);

		return color;
	}
}
