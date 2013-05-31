package nl.avans.min04sob.scrabble.core;

import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;

public abstract class CorePanel extends JPanel implements CoreView {
	
	public CorePanel() {
		setVisible(true);
		setLayout(new MigLayout());
	}
	
	public String toString(){
		return this.getClass().getName();
	}
}
