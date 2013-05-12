package nl.avans.min04sob.scrabble.core;

import java.awt.BorderLayout;
import java.beans.PropertyChangeEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class CoreWindow extends JFrame implements CoreView {

	public CoreWindow() {
		initialize();
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

	public CoreWindow(String title) {
		super(title);
		initialize();
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

	public CoreWindow(String title, int closeAction) {
		super(title);
		initialize();
		setDefaultCloseOperation(closeAction);
	}

	public CoreWindow(int closeAction) {
		initialize();
		setDefaultCloseOperation(closeAction);
	}

	public void initialize() {
		setVisible(true);
		setLayout(new BorderLayout());
	}

	public void addTopPanel(JPanel panel){
		add(panel, BorderLayout.NORTH);
	}
	
	public void addCenterPanel(JPanel panel){
		add(panel, BorderLayout.CENTER);
	}
	
	public void addRightPanel(JPanel panel){
		add(panel, BorderLayout.EAST);
	}

	@Override
	public void modelPropertyChange(PropertyChangeEvent evt) {
		// TODO Automatisch gegenereerde methodestub
	}
}