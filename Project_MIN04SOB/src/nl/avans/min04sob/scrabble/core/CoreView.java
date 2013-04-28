package nl.avans.min04sob.scrabble.core;

import java.awt.FlowLayout;
import java.beans.PropertyChangeEvent;

import javax.swing.JFrame;

public abstract class CoreView extends JFrame {

	public CoreView(String title) {
		super(title);
		init();
	}

	public CoreView() {
		super();
		init();
	}

	private void init() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setLayout(new FlowLayout());
	}

	public abstract void modelPropertyChange(PropertyChangeEvent evt);
}
