package nl.avans.min04sob.scrabble.core;

import java.awt.FlowLayout;
import java.awt.GraphicsConfiguration;
import java.awt.HeadlessException;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

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
		setVisible(true);
		setLayout(new FlowLayout());
	}
}
