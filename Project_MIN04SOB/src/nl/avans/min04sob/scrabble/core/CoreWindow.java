package nl.avans.min04sob.scrabble.core;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JFrame;

public abstract class CoreWindow extends JFrame implements CoreView {

	public CoreWindow(){
		init();
	}

	public CoreWindow(String title) {
		super(title);
		init();
	}
	
	public void init() {
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		pack();
		setVisible(true);
	}
}
