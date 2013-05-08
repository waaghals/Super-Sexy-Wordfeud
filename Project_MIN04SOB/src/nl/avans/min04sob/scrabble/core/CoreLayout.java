package nl.avans.min04sob.scrabble.core;

import java.awt.GridBagLayout;
import java.util.Arrays;

public class CoreLayout extends GridBagLayout {
	
	public final static int CELL_WIDTH = 30;
	public final static int CELL_HEIGHT = 30;

	public CoreLayout(int numCols, int numRows) {
		columnWidths = new int[numCols];
		rowHeights = new int[numRows];
		Arrays.fill(columnWidths, CELL_WIDTH);
		Arrays.fill(rowHeights, CELL_HEIGHT);
	}
}
