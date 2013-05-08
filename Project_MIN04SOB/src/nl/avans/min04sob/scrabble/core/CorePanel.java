package nl.avans.min04sob.scrabble.core;

import javax.swing.JPanel;

public abstract class CorePanel extends JPanel implements CoreView {
	
	public CorePanel() {
		setVisible(true);
		setLayout(new CoreLayout(10, 10));
	}
	
	public CorePanel(int numCols, int numRows) {
		setVisible(true);
		setLayout(new CoreLayout(numCols, numRows));
	}
}
