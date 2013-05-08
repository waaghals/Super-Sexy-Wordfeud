package nl.avans.min04sob.scrabble.core;

import java.awt.GridBagLayout;

import javax.swing.JPanel;

public abstract class CorePanel extends JPanel implements CoreView {
	
	public CorePanel() {
		setVisible(true);
		setLayout(new GridBagLayout());
	}
}
