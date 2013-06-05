package nl.avans.min04sob.scrabble.core.mvc;

import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;

@SuppressWarnings("serial")
public abstract class CorePanel extends JPanel implements CoreView {
	
	public CorePanel() {
		setVisible(true);
		setLayout(new MigLayout());
	}
	
	@Override
	public String toString(){
		return this.getClass().getName();
	}
}
