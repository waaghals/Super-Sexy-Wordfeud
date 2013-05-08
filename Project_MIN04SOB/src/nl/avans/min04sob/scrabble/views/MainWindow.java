package nl.avans.min04sob.scrabble.views;

import java.beans.PropertyChangeEvent;

import nl.avans.min04sob.scrabble.core.CoreWindow;

public class MainWindow extends CoreWindow {

	@Override
	public void modelPropertyChange(PropertyChangeEvent evt) {
		// TODO Auto-generated method stub

	}
	
	public MainWindow(){
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("Hoofdscherm");
	}

}
